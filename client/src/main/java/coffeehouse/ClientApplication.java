package coffeehouse;

import coffeehouse.net.Client;
import coffeehouse.util.IOUtils;

import java.net.UnknownHostException;

public class ClientApplication {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Insufficient arguments provided, usage: program <ip> <port>");
			return;
		}

		String ip = args[0];
		int port = Integer.parseInt(args[1]);
		
		Client client;
		
		try {
			client = new Client(ip, port);
		} catch (UnknownHostException e) {
			System.err.println("Host not recognised.");
			return;
		}

		new ConsoleInputLoop(client).run();		
		
		IOUtils.closeQuietly(client);

	}
}
