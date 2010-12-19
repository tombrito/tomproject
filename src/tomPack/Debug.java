package tomPack;

/**
 * Class used for debug messages.
 * 
 * @author Tom Brito
 */
public class Debug {

	/**
	 * Debug mode, if true, show the debug msgs. <br>
	 * Must be initialized on constructor.
	 */
	private static boolean debugMode = false;

	public static boolean isDebugMode() {
		return debugMode;
	}

	public static void setDebugMode(boolean b) {
		debugMode = b;
		if (debugMode) {
			System.out.println("DEBUG MODE ON"); //$NON-NLS-1$
		} else {
			System.out.println("DEBUG MODE OFF"); //$NON-NLS-1$
		}
	}

	/**
	 * Prints the parameter message only if the {@link Debug#debugMode} is
	 * enabled.
	 * 
	 * @param msg
	 *            - the message to display.
	 */
	@SuppressWarnings("rawtypes")
	public static void println(Object obj) {
		if (Debug.debugMode) {
			if (obj instanceof Iterable) {
				printIterable((Iterable) obj);
			} else {
				System.out.println(obj.toString());
			}
		}
	}

	@SuppressWarnings("nls")
	private static void printIterable(Iterable<?> iterable) {
		System.out.println("{");
		for (Object obj : iterable) {
			System.out.println("  " + obj);
		}
		System.out.println("}");
	}

	public static void print(Object obj) {
		if (Debug.debugMode) {
			System.out.print(obj.toString());
		}
	}

	/**
	 * Assure only the first exception will be printed.<br>
	 * You do not need to read thousands of exceptions before you correct the
	 * first one.
	 */
	protected static boolean print = true;

	public static void equalsFail(Object expected, Object actual) {
		if (Debug.debugMode) {
			synchronized (Debug.class) {
				if (print) {
					print = false;
					String msg = "Expected [" + expected + "] but was [" //$NON-NLS-1$ //$NON-NLS-2$
							+ actual + "]"; //$NON-NLS-1$
					Exception e = new Exception(msg);
					e.printStackTrace();
				}
			}
		}
	}

}
