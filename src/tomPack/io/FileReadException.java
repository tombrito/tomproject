package tomPack.io;

import java.io.File;
import java.util.List;

import lombok.Getter;

/**
 * Exception for file reading fails.
 */
public class FileReadException extends Exception {

    @Getter private List<File> files;

    public FileReadException() {}

    public FileReadException(File... files) {
	for (File file : files) {
	    this.files.add(file);
	}
    }

    public FileReadException(Exception e) {
	super(e);
    }

    public FileReadException(Exception e, File... files) {
	super(e);
	for (File file : files) {
	    this.files.add(file);
	}
    }

}
