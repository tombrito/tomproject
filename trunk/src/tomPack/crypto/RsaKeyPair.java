package tomPack.crypto;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * As {@link KeyPair} is final and cannot be extended, this class composes and
 * delegates {@link KeyPair} methods using RSA keys.
 */
public class RsaKeyPair {

	private KeyPair keyPair;

	public RsaKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	//
	// KeyPair delegations
	//

	@Override
	public int hashCode() {
		return keyPair.hashCode();
	}

	@Override
	public String toString() {
		return keyPair.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return keyPair.equals(obj);
	}

	public RSAPrivateKey getPrivate() {
		return (RSAPrivateKey) keyPair.getPrivate();
	}

	public RSAPublicKey getPublic() {
		return (RSAPublicKey) keyPair.getPublic();
	}

	//
	// KeyPair delegations
	//

}
