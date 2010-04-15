package tomPack;

import java.io.Serializable;
import java.security.Key;

import javax.crypto.Cipher;

import org.apache.commons.lang3.SerializationUtils;

public class TomCryptoUtils {
    
    public static byte[] encryptRsa(Serializable data, Key key) throws Exception {
	return encryptRsa(SerializationUtils.serialize(data), key);
    }

    public static byte[] encryptRsa(byte[] bytes, Key key) throws Exception {
	Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC"); //$NON-NLS-1$ //$NON-NLS-2$
	cipher.init(Cipher.ENCRYPT_MODE, key);
	byte[] encryptedBytes = cipher.doFinal(bytes);
	return encryptedBytes;
    }

}
