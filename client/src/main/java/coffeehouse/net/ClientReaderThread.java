package coffeehouse.net;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;


public class ClientReaderThread extends Thread {
	private Socket socket;
	private InputStream inStream;
	
	public ClientReaderThread(Socket _socket, InputStream in) {
		socket = _socket;
		inStream = in;
	}

	public void run() 
    {
		Scanner scanner = new Scanner(inStream);
		
		while(!socket.isClosed() && scanner.hasNextLine()) {
			String msg = scanner.nextLine();
			
			System.out.println(msg);
		}
		
		scanner.close();
    }
}
