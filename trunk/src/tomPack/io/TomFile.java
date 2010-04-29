package tomPack.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Make use of {@link TomFileUtils} to make file handling easier.
 */
public class TomFile extends File {

    public TomFile(File file) {
	super(file.getAbsolutePath());
    }

    public TomFile(String pathname) {
	super(pathname);
    }

    public TomFile(String parent, String child) {
	super(parent, child);
    }

    public TomFile(File parent, String child) {
	super(parent, child);
    }

    public TomFile(URI uri) {
	super(uri);
    }

    //
    // Instance methods
    //

    /**
     * Create the path, if it does not exist.
     */
    public void createPath() throws IOException {
	TomFileUtils.createPath(this);
    }

    /**
     * Create the file, if it does not exist.
     */
    public void createFile() throws IOException {
	TomFileUtils.createFile(this);
    }

    @Override public TomFile getParentFile() {
	return new TomFile(super.getParentFile().getAbsolutePath());
    }

    public String getExtension() {
	return TomFileUtils.getExtension(this);
    }

    public String getNameWithoutExtension() {
	return TomFileUtils.removeExtension(getName());
    }

}
