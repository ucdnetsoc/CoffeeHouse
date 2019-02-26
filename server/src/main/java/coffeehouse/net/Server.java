package coffeehouse.net;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable, Closeable {
	
	private List<ClientHandler> clients;
	private List<Thread> clientThreads;
	
	private ServerSocket serverSocket;
	
	public Server(int port) {
		clients = new ArrayList<ClientHandler>();
		clientThreads = new ArrayList<Thread>();
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public void run() {
		while(!serverSocket.isClosed()) {
			try {
				Socket socket = serverSocket.accept();
				
				ClientHandler client = new ClientHandler(socket);
				Thread clientThread = new Thread(client);
				clients.add(client);
				clientThreads.add(clientThread);
				
				clientThread.start();
			} catch(IOException e) {
				/// TODO: What to do here?
			}
		}
		
	}

	@Override
	public void close() throws IOException {
		for(ClientHandler client : clients) {
			client.close();
		}
		
		if(serverSocket != null) {
			serverSocket.close();
		}
	}
	
}
