package tomPack;

import java.math.BigInteger;

import org.apache.commons.codec.binary.Hex;

public class TomHexUtils {

    /**
     * Convenience method for "new BigInteger(hexString, 16).toByteArray()".
     */
    public static byte[] toHexBytes(String hexString) {
	return new BigInteger(hexString, 16).toByteArray();
    }

    /**
     * Convenience method for {@link Hex#encodeHexString(byte[])}.
     */
    public static String toHexString(byte[] bytes) {
	return Hex.encodeHexString(bytes);
    }

}
