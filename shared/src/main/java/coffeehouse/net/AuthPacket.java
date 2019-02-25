package coffeehouse.net;

public class AuthPacket extends Packet {
	
	private String username;
	
	public AuthPacket(String username) {
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}

	@Override
	public PacketType getType() {
		return PacketType.AUTH;
	}

}
