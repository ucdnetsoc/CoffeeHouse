package coffeehouse.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

import com.google.gson.stream.JsonReader;

import coffeehouse.util.IOUtils;

public class ClientSocketReader implements Runnable {
	private Client client;
	
	private Socket socket;
	private Reader reader;
	private JsonReader jsonReader;

	public ClientSocketReader(Client client, Socket socket) throws IOException {
		this.client = client;
		
		this.socket = socket;
		this.reader = new InputStreamReader(socket.getInputStream());
		this.jsonReader = new JsonReader(this.reader);
	}

	public void run() {
		try {
			while (!socket.isClosed()) {
				Packet message = Packet.readPacket(jsonReader);
	
				System.out.println("Message: " + message.getType());
			}
		} catch (BadPacketException e) {
			System.err.println("Packet closed due to following error: ");
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(socket);
		}
	}
}
