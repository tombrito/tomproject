package tomPack;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

@SuppressWarnings("nls")
public class TomHexUtils {

    public static final String defaultCharsetName = "UTF-8";

    /**
     * Convenience method for "new BigInteger(hexString, 16).toByteArray()".
     */
    public static byte[] hexStringToHexBytes(String hexString) {
	return new BigInteger(hexString, 16).toByteArray();
    }

    /**
     * Convenience method for {@link Hex#encodeHexString(byte[])}.
     */
    public static String toHexString(byte[] bytes) {
	return Hex.encodeHexString(bytes);
    }

    public static String encodeHexString(String data) throws UnsupportedEncodingException {
	return Hex.encodeHexString(data.getBytes(defaultCharsetName));
    }

    public static String decodeHex(String data) throws DecoderException, UnsupportedEncodingException {
	return new String(Hex.decodeHex(data.toCharArray()), defaultCharsetName);
    }

}
