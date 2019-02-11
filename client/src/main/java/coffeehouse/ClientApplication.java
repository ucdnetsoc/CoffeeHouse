package coffeehouse;
import coffeehouse.net.Client;
import java.net.UnknownHostException;

public class ClientApplication
{
	public static void main(String[] args)
	{
		try {
			Client client = new Client("192.168.0.10", 3000);
			client.waitForThreads();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
