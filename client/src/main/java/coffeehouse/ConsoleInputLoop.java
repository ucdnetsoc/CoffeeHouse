package coffeehouse;

import java.util.Scanner;

import coffeehouse.net.AuthPacket;
import coffeehouse.net.Client;

public class ConsoleInputLoop implements Runnable {

	private Client client;
	
	public ConsoleInputLoop(Client client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		
		try(Scanner scanner = new Scanner(System.in)) {
			
			System.out.println("Please enter your username: ");
			String username = scanner.nextLine();
			
			client.sendPacket(new AuthPacket(username));
			
			while(client.isOpen() && scanner.hasNextLine()) {
				
				String message = scanner.nextLine();
				
				client.sendMessage(message);
			}
		}
		
	}
	
	
}
