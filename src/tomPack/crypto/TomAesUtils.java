package tomPack.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import tomPack.TomHexUtils;

@SuppressWarnings("nls")
public class TomAesUtils {

    public static SecretKey createAesKey(String encoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
	return createAesKey(TomHexUtils.toHexBytes(encoded));
    }
    
    public static SecretKey createAesKey(byte[] encoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
	SecretKeySpec keySpec = new SecretKeySpec(encoded, "AES");
	SecretKeyFactory factory = SecretKeyFactory.getInstance("AES");
	return factory.generateSecret(keySpec);
    }

    public static SecretKey generateAesKey() throws NoSuchAlgorithmException {
	KeyGenerator keygen = KeyGenerator.getInstance("AES");
	return keygen.generateKey();
    }

}
