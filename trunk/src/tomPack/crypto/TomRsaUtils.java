package tomPack.crypto;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.SerializationUtils;

import tomPack.TomHexUtils;

@SuppressWarnings("nls")
public class TomRsaUtils {

    public static String encrypt(SecretKey secretKey, RSAKey key) throws RsaException {
	return encrypt(secretKey.getEncoded(), key);
    }

    public static String encrypt(byte[] bytes, RSAKey key) throws RsaException {
	byte[] encryptedBytes = encryptAsByteArray(bytes, key);
	return Hex.encodeHexString(encryptedBytes);
    }

    public static byte[] encryptAsByteArray(SecretKey secretKey, RSAKey key) throws Exception {
	return encryptAsByteArray(SerializationUtils.serialize(secretKey), key);
    }

    public static byte[] encryptAsByteArray(byte[] bytes, RSAKey key) throws RsaException {
	try {
	    Cipher cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
	    cipher.init(Cipher.ENCRYPT_MODE, (Key) key);
	    byte[] encryptedBytes = cipher.doFinal(bytes);
	    return encryptedBytes;
	} catch (Exception e) {
	    throw new RsaException(e);
	}
    }

    /**
     * Generate a key pair with a key size of 1024.
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
	return generateKeyPair(1024);
    }

    /**
     * Generate a key pair with a given key.
     */
    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
	KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", "BC");
	keyPairGen.initialize(keySize);
	return keyPairGen.generateKeyPair();
    }

    public static RSAPublicKey generatePublicKey(String encoded) throws NoSuchAlgorithmException,
	    InvalidKeySpecException {
	return generatePublicKey(TomHexUtils.toHexBytes(encoded));
    }

    public static RSAPublicKey generatePublicKey(byte[] encodedKey) throws NoSuchAlgorithmException,
	    InvalidKeySpecException {
	X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encodedKey);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	return (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
    }

    public static RSAPrivateKey generatePrivateKey(String encoded) throws NoSuchAlgorithmException,
	    InvalidKeySpecException {
	return generatePrivateKey(TomHexUtils.toHexBytes(encoded));
    }

    public static RSAPrivateKey generatePrivateKey(byte[] encodedKey) throws NoSuchAlgorithmException,
	    InvalidKeySpecException {
	X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encodedKey);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	return (RSAPrivateKey) keyFactory.generatePrivate(pubKeySpec);
    }

}