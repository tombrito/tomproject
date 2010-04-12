package tomPack.unitTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lombok.Cleanup;

/**
 * @version 2009/11/05
 * @author Tom Brito
 */
public class TomUnitTestUtils {

    private static String testFileName = "Test"; //$NON-NLS-1$

    public static void writeManyOnTestFile(Object... obj) {
	try {
	    @Cleanup
	    final FileOutputStream fout = new FileOutputStream(testFileName);
	    @Cleanup
	    final ObjectOutputStream out = new ObjectOutputStream(fout);
	    for (Object o : obj) {
		out.writeObject(o);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 
     * @param num
     *            - number of objects to read
     * @return
     */
    public static ArrayList<Object> readManyFromTestFile(int num) {
	ArrayList<Object> obj = new ArrayList<Object>();
	try {
	    @Cleanup
	    final FileInputStream fin = new FileInputStream(testFileName);
	    @Cleanup
	    final ObjectInputStream in = new ObjectInputStream(fin);
	    for (int i = 0; i < num; i++) {
		obj.add(in.readObject());
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return obj;
    }

    public static void writeOnTestFile(Object obj) {
	try {
	    @Cleanup
	    final FileOutputStream fout = new FileOutputStream(testFileName);
	    @Cleanup
	    final ObjectOutputStream out = new ObjectOutputStream(fout);
	    out.writeObject(obj);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static Object readFromTestFile() {
	Object obj = null;
	try {
	    @Cleanup
	    final FileInputStream fin = new FileInputStream(testFileName);
	    @Cleanup
	    final ObjectInputStream in = new ObjectInputStream(fin);
	    obj = in.readObject();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return obj;
    }

}
