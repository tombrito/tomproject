package tomPack;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Defines a entity object for JLadder.
 * <p>
 * Consider use {@link java.io.Externalizable} instead of this. But do not
 * override the Swing components equals method!
 * 
 * @version 2010/03/02
 * @author Tom Brito
 */
public interface TomEntity {
	
	// Evaluate: make a WEntity2 that extends WObject and Externalizable

	// Save e load executam a mesma função do serialWrite e read.
	// Posso personalizar a serialização a qualquer momento, se for necessario
	// criar uma segunda versao da classe posso criar sem problemas.

	// Por outro lado, com estes save e load posso mudar a estrutura e
	// hierarquia das classes a qualquer momento sem maiores problemas.

	/**
	 * Save the object's data to the Output object.
	 */
	public abstract void save(ObjectOutput out) throws IOException;

	/**
	 * Load the object's data from the Input object.
	 */
	public abstract void load(ObjectInput in) throws IOException,
			ClassNotFoundException;

	/**
	 * Compare the values of two {@link TomEntity}s.<br>
	 * Differ from {@link Object#equals(Object)} method cause is not used by the
	 * java swing components, so, you can do our own rules for the equality.
	 * 
	 * @param wpack.WObject
	 * @return <code>true</code> if the values are the same.
	 */
	// TODO created by a mistake. Use equals() method.
	public abstract boolean sameValues(TomEntity obj);

}
