package tomPack.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;

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
	if (!exists() && !mkdirs()) {
	    throw new IOException("Fail to create path: " + this); //$NON-NLS-1$
	}
    }
    
    /**
     * Create the file, if it does not exist.
     */
    public void createFile() throws IOException {
	if (!exists() && !createNewFile()) {
	    throw new IOException("Fail to create file: " + this); //$NON-NLS-1$
	}
    }
    
    @Override public TomFile getParentFile() {
	return new TomFile(super.getParentFile().getAbsolutePath());
    }
    
    public String getExtension() {
	    String name = getName();
	    return name.substring(name.lastIndexOf(".") + 1); //$NON-NLS-1$
    }
    
}
