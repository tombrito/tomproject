package tomPack;

import java.math.BigInteger;

import org.apache.commons.codec.binary.Hex;

@SuppressWarnings("nls")
public class TomHexUtils {

	public static final String defaultCharsetName = "UTF-8";

	/**
	 * Convenience method for "new BigInteger(hexString, 16).toByteArray()".
	 * 
	 * @deprecated use {@link #decodeHex(String)}.
	 */
	@Deprecated
	public static byte[] hexStringToHexBytes(String hexString) {
		return new BigInteger(hexString, 16).toByteArray();
	}

	/**
	 * Convenience method for {@link Hex#encodeHexString(byte[])}.
	 * 
	 * @deprecated use {@link #encodeHexString(String)}
	 */
	@Deprecated
	public static String toHexString(byte[] bytes) {
		return Hex.encodeHexString(bytes);
	}

	public static String encodeHexString(String data) throws EncoderException {
		try {
			return Hex.encodeHexString(data.getBytes(defaultCharsetName));
		} catch (Exception e) {
			throw new EncoderException(e);
		}
	}

	public static String decodeHex(String data) throws DecoderException {
		try {
			return new String(Hex.decodeHex(data.toCharArray()),
					defaultCharsetName);
		} catch (Exception e) {
			throw new DecoderException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		String data = "my data";
		String encrypted = encodeHexString(data);
		String decrypted = decodeHex(encrypted);
		System.out.println(decrypted);
	}

}
