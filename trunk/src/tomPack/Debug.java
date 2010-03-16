package tomPack;

/**
 * Class used for debug messages.
 * 
 * @version 2009/08/25
 * @author Tom Brito
 */
public class Debug {

    /**
     * Debug mode, if true, show the debug msgs. <br>
     * Must be initialized on constructor.
     */
    public static Boolean DEBUG_MODE = false;

    /**
     * Prints the parameter message only if the {@link Debug#DEBUG_MODE} is
     * enabled.
     * 
     * @param msg
     *            - the message to display.
     */
    public static void println(Object obj) {
	if (Debug.DEBUG_MODE) {
	    System.out.println(obj.toString());
	}
    }

    public static void print(Object obj) {
	if (Debug.DEBUG_MODE) {
	    System.out.print(obj.toString());
	}
    }

    /**
     * Assure only the first exception will be printed.<br>
     * You do not need to read thousands of exceptions before you correct the
     * first one.
     */
    private static boolean print = true;

    public static void equalsFail(Object expected, Object actual) {
	if (Debug.DEBUG_MODE) {
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