package tomPack;

import java.math.BigInteger;

public class TomHexUtils {

    /**
     * Convenience method for "new BigInteger(hexString, 16).toByteArray()".
     */
    public static byte[] hexToBytes(String hexString) {
	return new BigInteger(hexString, 16).toByteArray();
    }

}
