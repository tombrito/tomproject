package tomPack.crypto;

import java.io.Serializable;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.SerializationUtils;

public class TomRsaUtils {

    public static String encryptAsString(Serializable data, Key key) throws RsaException {
	return encryptAsString(SerializationUtils.serialize(data), key);
    }

    public static String encryptAsString(byte[] bytes, Key key) throws RsaException {
	byte[] encryptedBytes = TomRsaUtils.encryptAsByteArray(bytes, key);
	return Hex.encodeHexString(encryptedBytes);
    }

    public static byte[] encryptAsByteArray(Serializable data, Key key) throws Exception {
	return encryptAsByteArray(SerializationUtils.serialize(data), key);
    }

    public static byte[] encryptAsByteArray(byte[] bytes, Key key) throws RsaException {
	try {
	    Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC"); //$NON-NLS-1$ //$NON-NLS-2$
	    cipher.init(Cipher.ENCRYPT_MODE, key);
	    byte[] encryptedBytes = cipher.doFinal(bytes);
	    return encryptedBytes;
	} catch (Exception e) {
	    throw new RsaException(e);
	}
    }

    /**
     * Convenience method for:
     * 
     * <pre>
     * java.security.KeyPairGenerator.getInstance(&quot;RSA&quot;, &quot;BC&quot;).generateKeyPair()
     * </pre>
     */
    @SuppressWarnings("nls")
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
	return KeyPairGenerator.getInstance("RSA", "BC").generateKeyPair();
    }

}
