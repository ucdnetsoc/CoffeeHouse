package coffeehouse.net;

public enum PacketType {
	AUTH, CLIENT_MESSAGE, SERVER_MESSAGE;

	public Class<? extends Packet> getPacketType() {
		switch (this) {
		case AUTH:
			return AuthPacket.class;
		default:
			throw new IllegalArgumentException("Unhandled PacketType " + this.name());
		}
	}
}