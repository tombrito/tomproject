package tomPack.crypto;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.SerializationUtils;

import tomPack.swing.Msg;

@SuppressWarnings("nls")
public class TomRsaUtils {

	private static final String algorithm = "RSA";

	// private static final String transformation =
	// "RSA/ECB/OAEPWithSHA1AndMGF1Padding";
	private static final String transformation = "RSA";

	public static String encrypt(SecretKey secretKey, RSAKey key)
			throws RsaException {
		return encrypt(secretKey.getEncoded(), key);
	}

	public static String encrypt(byte[] bytes, RSAKey key) throws RsaException {
		byte[] encryptedBytes = encryptAsByteArray(bytes, key);
		return Hex.encodeHexString(encryptedBytes);
	}

	public static byte[] encryptAsByteArray(SecretKey secretKey, RSAKey key)
			throws Exception {
		return encryptAsByteArray(SerializationUtils.serialize(secretKey), key);
	}

	public static byte[] encryptAsByteArray(byte[] bytes, RSAKey key)
			throws RsaException {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
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
	public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException {
		return generateKeyPair(1024);
	}

	/**
	 * Generate a key pair with a given key.
	 */
	public static RsaKeyPair generateKeyPair(int keySize)
			throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
		keyPairGen.initialize(keySize);
		return new RsaKeyPair(keyPairGen.generateKeyPair());
	}

	public static RSAPublicKey generatePublicKey(String modulus,
			String publicExponent) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		return generatePublicKey(new BigInteger(modulus), new BigInteger(
				publicExponent));
	}

	public static RSAPublicKey generatePublicKey(BigInteger modulus,
			BigInteger publicExponent) throws NoSuchAlgorithmException,
			InvalidKeySpecException {
		// X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encodedKey);
		// PKCS8EncodedKeySpec pubKeySpec = new PKCS8EncodedKeySpec(encodedKey);
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(modulus,
				publicExponent);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	}

	public static RSAPrivateKey generatePrivateKey(String encoded)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		return generatePrivateKey(encoded.getBytes());
	}

	public static RSAPrivateKey generatePrivateKey(byte[] encodedKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		Msg.debug(TomRsaUtils.class, ArrayUtils.toString(encodedKey));

		/*
		 * The following line throws InvalidKeySpecException: Only
		 * RSAPrivate(Crt)KeySpec and PKCS8EncodedKeySpec supported for RSA
		 * private keys
		 */
		// X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encodedKey);
		PKCS8EncodedKeySpec pubKeySpec = new PKCS8EncodedKeySpec(encodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return (RSAPrivateKey) keyFactory.generatePrivate(pubKeySpec);
	}

	public static void main(String[] args) throws Exception {
		RsaKeyPair keyPair = generateKeyPair();
		RSAPrivateKey key = keyPair.getPrivate();
		String encoded = new String(Hex.encodeHex(key.getEncoded()));
		// byte[] encoded = key.getEncoded();

		byte[] decoded = Hex.decodeHex(encoded.toCharArray());
		RSAPrivateKey k2 = generatePrivateKey(decoded);

		System.out.println(decoded.equals(key.getEncoded()));
		System.out.println(ArrayUtils.toString(decoded));
		System.out.println(ArrayUtils.toString(k2.getEncoded()));

		System.out.println("The end");

	}

}
