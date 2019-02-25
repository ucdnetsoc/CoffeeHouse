package coffeehouse.net;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriterThread extends Thread {
	private Socket socket;
	private OutputStream outStream;

	public ClientWriterThread(Socket _socket, OutputStream out) {
		socket = _socket;
		outStream = out;
	}

	public void run() {
		Scanner scanner = new Scanner(System.in);
		Writer writer = new OutputStreamWriter(outStream);
		
		Packet authPacket = new AuthPacket("myusername");
		authPacket.writeJson(writer);

		while (!socket.isClosed() && scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			Packet packet = new ClientMessagePacket(line);
			
			packet.writeJson(writer);
			
			System.out.println(line);
		}

		scanner.close();
	}
}
