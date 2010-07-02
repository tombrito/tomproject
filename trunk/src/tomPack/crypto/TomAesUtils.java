package tomPack.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import tomPack.DecoderException;
import tomPack.TomHexUtils;
import tomPack.io.TomIOUtils;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

@SuppressWarnings("nls")
public class TomAesUtils {

    public static final String defaultCharsetName = "UTF-8";

    private static final String algorithm = "AES";

    public static SecretKey createAesKey(String encoded) throws DecoderException {
	return createAesKey(TomHexUtils.decodeHex(encoded));
    }

    public static SecretKey createAesKey(byte[] encoded) throws NoSuchAlgorithmException, InvalidKeySpecException {
	SecretKeySpec keySpec = new SecretKeySpec(encoded, algorithm);
	SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
	return factory.generateSecret(keySpec);
    }

    public static SecretKey generateAesKey() throws NoSuchAlgorithmException {
	KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
	return keygen.generateKey();
    }

    //
    // Encryption
    //

    public static String encrypt(String data, SecretKey key) throws AesEncryptionException,
	    UnsupportedEncodingException {
	return encrypt(data.getBytes(defaultCharsetName), key);
    }

    public static String encrypt(byte[] data, SecretKey key) throws AesEncryptionException {
	byte[] base64Encoded = Base64.encode(data).getBytes();
	InputStream in = new ByteArrayInputStream(base64Encoded);
	OutputStream out = new ByteArrayOutputStream();
	encrypt(key, in, out);
	return out.toString();
    }

    // See http://www.exampledepot.com/egs/javax.crypto/DesFile.html
    // Used internally by this class, after encode in Base64
    private static void encrypt(SecretKey key, InputStream in, OutputStream out) throws AesEncryptionException {

	// Buffer used to transport the bytes from one stream to another
	byte[] buf = new byte[1024];

	try {
	    Cipher cipher = Cipher.getInstance(algorithm);
	    cipher.init(Cipher.ENCRYPT_MODE, key);

	    // Bytes written to out will be encrypted
	    // Note that the out is given as parameter, so the new out has the
	    // old one
	    out = new CipherOutputStream(out, cipher);

	    // Read in the cleartext bytes and write to out to encrypt
	    int numRead = 0;
	    while ((numRead = in.read(buf)) >= 0) {
		out.write(buf, 0, numRead);
	    }
	    // FIXME must be closed by caller
	    TomIOUtils.close(out);
	} catch (Exception e) {
	    throw new AesEncryptionException(e);
	}
    }

    //
    // Decryption
    //

    public static String decryptString(SecretKey key, InputStream in) throws Exception {
	byte[] decodedBytes = decrypt(key, in);
	return new String(decodedBytes, defaultCharsetName);
    }

    public static byte[] decrypt(SecretKey key, InputStream in) throws AesDecryptionException, Base64DecodingException {
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	decrypt(key, in, out);
	String base64Encoded = out.toString();
	return Base64.decode(base64Encoded.getBytes());
    }

    private static void decrypt(SecretKey key, InputStream in, OutputStream out) throws AesDecryptionException {

	// Buffer used to transport the bytes from one stream to another
	byte[] buf = new byte[1024];

	try {
	    Cipher dcipher = Cipher.getInstance(algorithm);
	    dcipher.init(Cipher.DECRYPT_MODE, key);

	    // Bytes read from in will be decrypted
	    in = new CipherInputStream(in, dcipher);

	    // Read in the decrypted bytes and write the cleartext to out
	    int numRead = 0;
	    while ((numRead = in.read(buf)) >= 0) {
		out.write(buf, 0, numRead);
	    }
	    // FIXME must be closed by caller
	    out.close();
	} catch (Exception e) {
	    throw new AesDecryptionException(e);
	}
    }

}
