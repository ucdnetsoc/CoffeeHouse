package coffeehouse.net;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import coffeehouse.util.IOUtils;

public class Client implements Closeable {
	private Socket socket;
	
	private Thread readThread;
	private Writer writer;
	
	public Client(String serverHost, int serverPort) throws UnknownHostException, IOException
	{
		socket = new Socket(serverHost, serverPort);

		readThread = new Thread(new ClientSocketReader(this, socket));

		readThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.err.println("Uncaught " + e + " in client read thread");
				e.printStackTrace();
				IOUtils.closeQuietly(Client.this);
			}
		});
		writer = new OutputStreamWriter(socket.getOutputStream());
		
		readThread.start();
	}
	
	
	@Override
	public void close() throws IOException {
		
		if(readThread != null && readThread.isAlive()) {
			readThread.interrupt();
		}
		
		if(socket != null) {
			socket.close();
		}
	}


	public boolean isOpen() {
		return !socket.isClosed();
	}
	
	public void sendPacket(Packet p) {
		p.writeJson(writer);
		try {
			writer.flush();
		} catch (IOException e) {
			System.err.println("Socket dropped due to following error: ");
			e.printStackTrace();
			
			IOUtils.closeQuietly(this);
		}
	}


	public void sendMessage(String message) {
		sendPacket(new ClientMessagePacket(message));
	}
}
