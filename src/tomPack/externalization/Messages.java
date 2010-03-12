package tomPack.externalization;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Messages language handler. Used internally.
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class Messages {

    private static final String BUNDLE_NAME = "tomPack.externalization.messages_"; //$NON-NLS-1$

    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME + Locale.getDefault());

    public static String getString(String key) {
	try {
	    return RESOURCE_BUNDLE.getString(key);
	} catch (MissingResourceException e) {
	    return '!' + key + '!';
	}
    }

}