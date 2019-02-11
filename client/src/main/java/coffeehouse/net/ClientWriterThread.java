package coffeehouse.net;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class ClientWriterThread extends Thread {
	private Socket socket;
	private OutputStream outStream;
	
	public ClientWriterThread(Socket _socket, OutputStream out) {
		socket = _socket;
		outStream = out;
	}
	public void run() 
    { 
		Scanner scanner = new Scanner(System.in);
		PrintStream ps = new PrintStream(outStream);
		
		ps.println("Hello world!");
		
		while(!socket.isClosed() && scanner.hasNextLine()) {
			String msg = scanner.nextLine();
			
			ps.println(msg);
		}
		
		scanner.close();
    }
}
