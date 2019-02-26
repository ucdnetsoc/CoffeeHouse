package coffeehouse.net;

import com.google.gson.JsonIOException;

public class BadPacketException extends Exception {
	public BadPacketException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 4496171768411252792L;
	
}
