package tomPack.unitTest;

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

	/*
	 * Evaluate: make a WEntity2 that extends WObject and Externalizable
	 * 
	 * Save and load execute the same function of serialWrite and read. I can
	 * customize the serialization at any moment, if I need to create a second
	 * version of the class I can with no problems.
	 * 
	 * On the other hand, with these save and load I can change the structure
	 * and hierarchy of the classes at any moment without major problems.
	 */

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
	 * @param wpack
	 *            .WObject
	 * 
	 * @return <code>true</code> if the values are the same.
	 * 
	 * @deprecated use equals() method.
	 */
	@Deprecated
	public abstract boolean sameValues(TomEntity obj);

}
