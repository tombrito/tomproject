package tomPack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import tomPack.swing.Msg;

import junit.framework.TestCase;

/**
 * Abstract class extends TestCase to test {@link TomEntity} objects.
 * 
 * @version 2009/11/04
 * @author Tom Brito
 */
public abstract class TomEntityTestCase extends TestCase {

    private final File file = new File("TestCaseOutput"); //$NON-NLS-1$

    private void write(TomEntity entity) {
	try {
	    final FileOutputStream fout = new FileOutputStream(file);
	    final ObjectOutputStream out = new ObjectOutputStream(fout);
	    entity.save(out);
	    out.close();
	    fout.close();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}
    }

    private TomEntity read() {
	try {
	    final FileInputStream fin = new FileInputStream(file);
	    final ObjectInputStream in = new ObjectInputStream(fin);
	    TomEntity entity = createEntityForTest();
	    entity.load(in);
	    return entity;
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}
	return null;
    }

    @Override
    protected void setUp() {
	try {
	    super.setUp();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}

	Msg.setTestMode(true);
	Debug.setDebugMode(true);
	doPersistenceTest();
    }

    @Override
    protected void tearDown() {
	// test data persistence after the test
	doPersistenceTest();
	file.deleteOnExit(); // TODO why do not delete on exit?

	try {
	    super.tearDown();
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	}
    }

    public abstract TomEntity createEntityForTest();

    /**
     * Returns the {@link TomEntity} that is current been tested.
     */
    public abstract TomEntity getWEntity();

    public abstract void setWEntity(TomEntity entity);

    /**
     * Used to access a private and protected fields.
     * 
     * @param field
     * @return
     * @throws Exception
     */
    public static <T> T getField(Object obj, Class<?> c, String fieldName) {
	try {
	    Field field = c.getDeclaredField(fieldName);
	    field.setAccessible(true);
	    return (T) field.get(obj);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	    return null;
	}
    }

    /**
     * 
     * @see #getMethod(Class, String, Class...)
     * @param obj
     * @param c
     * @param methodName
     * @return
     */
    public static <T> T invokeMethod(Object obj, Class<?> c, String methodName) {
	try {
	    Method method = c.getDeclaredMethod(methodName);
	    method.setAccessible(true);
	    return (T) method.invoke(obj);
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	    return null;
	}
    }

    /**
     * 
     * @see #invokeMethod(Object, Class, String)
     * @param c
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Class<?> c, String methodName, Class<?>... parameterTypes) {
	try {
	    Method method = c.getDeclaredMethod(methodName, parameterTypes);
	    method.setAccessible(true);
	    return method;
	} catch (Exception e) {
	    e.printStackTrace();
	    fail();
	    return null;
	}
    }

    public Object getField(Class<?> c, String fieldName) {
	return getField(getWEntity(), c, fieldName);
    }

    /**
     * @see #invokeMethod(Object, Class, String)
     * @param c
     * @param methodName
     * @return
     */
    public Object getMethod(Class<?> c, String methodName) {
	return invokeMethod(getWEntity(), c, methodName);
    }

    /**
     * Test the consistence of the persistence of an {@link TomEntity}.
     * <p>
     * The entity to write will be get from the {@link #getWEntity()} method,
     * and the entity to load will be get from {@link #createEntityForTest()}
     * method.
     * <p>
     * After write and read, this method calls the
     * {@link TomObject#sameValues(TomObject)} to compare the data.
     */
    protected final void doPersistenceTest() {
	TomEntity currentEntity = getWEntity();
	write(currentEntity);
	TomEntity loadedEntity = read();
	assertTrue(currentEntity.sameValues(loadedEntity));
	setWEntity(loadedEntity);
    }

    //
    // Tests
    //

    public final void testPersistence() {
	// setUp and tearDown will automatically test persistence when run this
	// test.
    }

}
