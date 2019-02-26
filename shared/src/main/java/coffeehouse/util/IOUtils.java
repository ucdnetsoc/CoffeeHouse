package coffeehouse.util;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	// Helpful util function to close a function without caring about IOException
	public static void closeQuietly(Closeable obj) {
		if(obj != null) {
			try {
				obj.close();
			} catch(IOException e) {
				
			}
		}
	}
}
