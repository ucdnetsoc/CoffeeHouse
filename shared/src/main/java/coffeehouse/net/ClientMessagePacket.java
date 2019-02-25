package coffeehouse.net;

public class ClientMessagePacket extends Packet {

	private String message;
	
	public ClientMessagePacket(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public PacketType getType() {
		return PacketType.CLIENT_MESSAGE;
	}
	
}
