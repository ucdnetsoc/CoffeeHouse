package coffeehouse.net;

import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public abstract class Packet {

	public static class PacketAdapter extends TypeAdapter<Packet> {
		@Override
		public void write(JsonWriter out, Packet value) throws IOException {
			if (value == null) {
				out.nullValue();
			} else {
				out.beginObject();

				out.name("type");
				out.value(value.getType().name());

				out.name("message");

				new Gson().toJson(value, value.getClass(), out);

				out.endObject();
			}
		}

		@Override
		public Packet read(JsonReader in) throws IOException {

			if (in.peek() == JsonToken.NULL) {
				return null;
			}

			PacketType type = null;
			Packet p = null;
			JsonObject message = null;

			in.beginObject();

			Gson gson = new Gson();

			while (!in.peek().equals(JsonToken.END_OBJECT)) {
				String key = in.nextName();
				if (key.equals("type")) {
					type = PacketType.valueOf(in.nextString());
				} else if (key.equals("message")) {
					if (type != null) {
						p = gson.fromJson(in, type.getPacketType()); // We already know the type, may aswell deserialize
																		// immediately.
					} else {
						message = gson.fromJson(in, JsonObject.class); // type isn't yet known, copy json object for
																		// later parsing.
					}
				} else {
					/// TODO: Should we throw? Or just ignore it.
					throw new IllegalArgumentException("Unexpected field " + in.nextName() + " in packet.");
				}
			}

			in.endObject();

			if (p == null && type != null && message != null) {
				p = gson.fromJson(message, type.getPacketType());
			}

			return p;
		}

	}

	public static Packet readPacket(String json) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Packet.class, new PacketAdapter()).create();

		return gson.fromJson(json, Packet.class);
	}
	
	public static Packet readPacket(JsonReader jsonReader) throws BadPacketException {

		Gson gson = new GsonBuilder().registerTypeAdapter(Packet.class, new PacketAdapter()).create();

		try {
			return gson.fromJson(jsonReader, Packet.class);
		} catch(JsonSyntaxException e) {
			throw new BadPacketException(e);
		} catch(JsonIOException e) {
			throw new BadPacketException(e);
		}
	}

	public abstract PacketType getType();

	public String toJson() {
		Gson gson = new GsonBuilder().registerTypeAdapter(Packet.class, new PacketAdapter()).create();

		return gson.toJson(this, Packet.class);
	}

	public void writeJson(Writer writer) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Packet.class, new PacketAdapter()).create();

		gson.toJson(this, Packet.class, writer);
	}

}
