package tomPack.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import tomPack.TomHexUtils;
import tomPack.io.TomIOUtils;

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

    //
    // Encryption
    //

    public static String encrypt(String data, SecretKey key) throws AesEncryptionException {
	return encrypt(TomHexUtils.toHexBytes(data), key);
    }

    public static String encrypt(byte[] data, SecretKey key) throws AesEncryptionException {
	InputStream in = new ByteArrayInputStream(data);
	OutputStream out = new ByteArrayOutputStream();
	encrypt(key, in, out);
	return out.toString();
    }

    // See http://www.exampledepot.com/egs/javax.crypto/DesFile.html
    public static void encrypt(SecretKey key, InputStream in, OutputStream out) throws AesEncryptionException {

	// Buffer used to transport the bytes from one stream to another
	byte[] buf = new byte[1024];

	try {
	    Cipher ecipher = Cipher.getInstance("AES/CBC/NoPadding");
	    ecipher.init(Cipher.ENCRYPT_MODE, key);

	    // Bytes written to out will be encrypted
	    out = new CipherOutputStream(out, ecipher);

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

    public static void decrypt(SecretKey key, InputStream in, OutputStream out) throws AesDecryptionException {

	// Buffer used to transport the bytes from one stream to another
	byte[] buf = new byte[1024];

	try {
	    Cipher dcipher = Cipher.getInstance("AES/CBC/NoPadding");
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
