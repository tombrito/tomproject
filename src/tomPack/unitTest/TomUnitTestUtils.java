package tomPack.unitTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author Tom Brito
 */
public class TomUnitTestUtils {

	private static String testFileName = "Test"; //$NON-NLS-1$

	public static void writeManyOnTestFile(Object... obj) {
		try {
			final FileOutputStream fout = new FileOutputStream(testFileName);
			final ObjectOutputStream out = new ObjectOutputStream(fout);
			try {
				for (Object o : obj) {
					out.writeObject(o);
				}
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param num
	 *            Number of objects to read.
	 * @return
	 */
	public static ArrayList<Object> readManyFromTestFile(int num) {
		ArrayList<Object> obj = new ArrayList<Object>();
		try {
			final FileInputStream fin = new FileInputStream(testFileName);
			final ObjectInputStream in = new ObjectInputStream(fin);
			try {
				for (int i = 0; i < num; i++) {
					obj.add(in.readObject());
				}
			} finally {
				in.close();
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
			try {
				out.writeObject(obj);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object readFromTestFile() {
		Object obj = null;
		try {
			final FileInputStream fin = new FileInputStream(testFileName);
			final ObjectInputStream in = new ObjectInputStream(fin);
			try {
				obj = in.readObject();
			} finally {
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
