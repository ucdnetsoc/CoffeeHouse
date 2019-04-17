package coffeehouse.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

import com.google.gson.stream.JsonReader;

import coffeehouse.util.IOUtils;

public class ClientHandler implements Runnable, Closeable {

	private Socket socket;
	private Reader reader;
	private JsonReader jsonReader;
	
	private Writer writer;
	
	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		
		this.reader = new InputStreamReader(socket.getInputStream());
		this.jsonReader = new JsonReader(this.reader);
		
		this.writer = new OutputStreamWriter(socket.getOutputStream());
	}
	
	private void handlePacket(Packet packet) {
		switch(packet.getType()) {
		case AUTH: {
			AuthPacket authPacket = (AuthPacket) packet;
			System.out.println("New user: " + authPacket.getUsername());
			break;
		}
		case CLIENT_MESSAGE: {
			ClientMessagePacket clientMessagePacket = (ClientMessagePacket) packet;
			System.out.println("Message: " + clientMessagePacket.getMessage());
			break;
		}
		default:
			throw new IllegalArgumentException("Unhandled packet type: " + packet.getType());
		}
	}

	// Handles reading from socket
	@Override
	public void run() {
		
		try {
			while(!socket.isInputShutdown()) {
				Packet packet = Packet.readPacket(jsonReader);
				
				System.out.println(" found! Packet type is " + packet.getType());
				
				handlePacket(packet);
			}
		} catch(Exception e) {
			System.err.println("Session with socket " + socket + " was closed due to exception following exception.");
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(socket);
		}
	}
	
	public boolean isOpen() {
		return !socket.isClosed();
	}

	@Override
	public void close() throws IOException {
		jsonReader.close();
		reader.close();
		writer.close();
		socket.close();
	}
	
}
