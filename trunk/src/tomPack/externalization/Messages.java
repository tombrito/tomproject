package tomPack.externalization;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import tomPack.swing.Msg;

/**
 * Messages language handler. Used internally.
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class Messages {

    private static final String BUNDLE_NAME = "tomPack.externalization.messages"; //$NON-NLS-1$

    private static ResourceBundle RESOURCE_BUNDLE;

    static {
	try {
	    RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	} catch (Exception e) {
	    e.printStackTrace();
	    RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.US);
	}
    }

    private Messages() {
    }

    public static String getString(String key) {
	try {
	    return RESOURCE_BUNDLE.getString(key);
	} catch (MissingResourceException e) {
	    return '!' + key + '!';
	}
    }

    @SuppressWarnings("nls")
    public static void main(String[] args) {
	System.out.println("Messages test");
	Msg.inf("test"); //$NON-NLS-1$
    }
}
