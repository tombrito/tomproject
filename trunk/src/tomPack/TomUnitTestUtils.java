package tomPack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @version 2009/11/05
 * @author Tom Brito
 */
public class TomUnitTestUtils {

    // TODO separete tests from WUtil methods
    private static String testFileName = "Test.jl"; //$NON-NLS-1$

    public static void writeManyOnTestFile(Object... obj) {
	try {
	    final FileOutputStream fout = new FileOutputStream(testFileName);
	    final ObjectOutputStream out = new ObjectOutputStream(fout);
	    for (Object o : obj) {
		out.writeObject(o);
	    }
	    out.close();
	    fout.close();
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
	    final FileInputStream fin = new FileInputStream(testFileName);
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
	    final FileOutputStream fout = new FileOutputStream(testFileName);
	    final ObjectOutputStream out = new ObjectOutputStream(fout);
	    out.writeObject(obj);
	    out.close();
	    fout.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static Object readFromTestFile() {
	Object obj = null;
	try {
	    final FileInputStream fin = new FileInputStream(testFileName);
	    final ObjectInputStream in = new ObjectInputStream(fin);
	    obj = in.readObject();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return obj;
    }

}
