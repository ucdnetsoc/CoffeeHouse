package coffeehouse.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

public class ClientSocketReader implements Runnable {
	private Client client;
	
	private Socket socket;
	private Reader reader;

	public ClientSocketReader(Client client, Socket socket) throws IOException {
		this.client = client;
		
		this.socket = socket;
		this.reader = new InputStreamReader(socket.getInputStream());
	}

	public void run() {
		try {
			while (!socket.isClosed()) {
				Packet message = Packet.readPacket(reader);
	
				System.out.println("Message: " + message.getType());
			}
		} catch (BadPacketException e) {
			System.err.println();
		} finally {
			try {
				reader.close();
				socket.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
