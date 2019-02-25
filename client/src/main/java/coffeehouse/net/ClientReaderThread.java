package coffeehouse.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

public class ClientReaderThread extends Thread {
	private Socket socket;
	private InputStream inStream;

	public ClientReaderThread(Socket _socket, InputStream in) {
		socket = _socket;
		inStream = in;
	}

	public void run() {
		Reader reader = new InputStreamReader(inStream);

		while (!socket.isClosed()) {
			Packet message = Packet.readPacket(reader);

			System.out.println("Message: " + message.getType());
		}

		try {
			reader.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
