package coffeehouse.net;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket socket;
	
	private OutputStream serverOut;
	private InputStream serverIn;
	
	private Thread readThread, writeThread;
	
	public Client(String serverHost, int serverPort) throws UnknownHostException
	{
		try {
			socket = new Socket(serverHost, serverPort);
			
			serverOut = socket.getOutputStream();
			serverIn = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		readThread = new ClientReaderThread(socket, serverIn);
		writeThread = new ClientWriterThread(socket, serverOut);
		
		readThread.start();
		writeThread.start();
		
	}
	
	public void waitForThreads() {
		try {
			readThread.join();
			writeThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			socket.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
