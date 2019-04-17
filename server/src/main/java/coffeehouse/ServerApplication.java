package coffeehouse;

import java.io.*;

import coffeehouse.net.Server;

public class ServerApplication {
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("Insufficient arguments provides, usage: program <port>");
		}		

		int port = Integer.parseInt(args[0]);

		Server server = new Server(port);
		
		server.run();
		
		try {
			server.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
