package coffeehouse.net;

public enum PacketType {
	AUTH, CLIENT_MESSAGE, SERVER_MESSAGE;

	public Class<? extends Packet> getPacketType() {
		switch (this) {
		case AUTH:
			return AuthPacket.class;
		case CLIENT_MESSAGE:
			return ClientMessagePacket.class;
		case SERVER_MESSAGE:
			//return ServerMessagePacket.class;
		default:
			throw new IllegalArgumentException("Unhandled PacketType " + this.name());
		}
	}
}