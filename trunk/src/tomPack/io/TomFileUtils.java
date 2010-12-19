package tomPack.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * Utilities for {@link File} handling.
 * 
 * @see TomFile
 */
public class TomFileUtils {

	public static String getNameWithoutExtension(File file) {
		return removeExtension(file.getName());
	}

	/**
	 * Return the given filename without the extension.
	 */
	public static String removeExtension(String filename) {
		String name = filename;
		return name.substring(0, name.lastIndexOf(".")); //$NON-NLS-1$
	}

	public static String getExtension(File file) {
		return getExtension(file.getName());
	}

	public static String getExtension(String filename) {
		String name = filename;
		return name.substring(name.lastIndexOf(".") + 1); //$NON-NLS-1$
	}

	/**
	 * Create the path, if it does not exist.
	 */
	public static void createPath(String pathName) throws IOException {
		createPath(new File(pathName));
	}

	/**
	 * Create the path, if it does not exist.
	 */
	public static void createPath(File path) throws IOException {
		if (!path.exists() && !path.mkdirs()) {
			throw new IOException("Fail to create path: " + path); //$NON-NLS-1$
		}
	}

	/**
	 * Create the file, if it does not exist.
	 */
	public static void createFile(String filename) throws IOException {
		createFile(new File(filename));
	}

	/**
	 * Create the file, if it does not exist.
	 */
	public static void createFile(File file) throws IOException {
		if (!file.exists()) {
			File path = file.getParentFile();
			createPath(path);
			if (!file.createNewFile()) {
				throw new IOException("Fail to create file: " + file); //$NON-NLS-1$
			}
		}
	}

	public static void copyFile(File inputFile, File targetDir)
			throws IOException {
		copyFile(inputFile, targetDir, inputFile.getName());
	}

	public static void copyFile(File inputFile, File targetDir, String newName)
			throws IOException {
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(new File(targetDir, newName));
		IOUtils.copy(in, out);
	}

	/**
	 * Copy the files to the target directory.
	 * <p>
	 * WARNING: Try to use the full name of the files, to avoid problems.
	 */
	public static void copyFiles(List<File> files, File destDir)
			throws IOException {
		for (File file : files) {
			copyFile(file, destDir);
		}
	}

}
