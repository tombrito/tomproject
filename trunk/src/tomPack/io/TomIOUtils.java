package tomPack.io;

import java.io.Closeable;
import java.io.IOException;

public class TomIOUtils {

	/**
	 * Try to close the Closable object, and print the stack trace if an
	 * exception is raised.
	 * 
	 * Useful to close a set of streams without throw an exception.
	 */
	public static void close(Closeable c) {
		if (c != null)
			try {
				c.close();
			} catch (IOException e) {
				// log the exception (if you are using System.out as log)
				e.printStackTrace();
			}
	}

}
