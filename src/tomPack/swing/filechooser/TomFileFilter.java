package tomPack.swing.filechooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;

public class TomFileFilter extends FileFilter {

    protected final List<String> extensionList = new ArrayList<String>();
    protected final String description;

    public TomFileFilter(String extension, String description) {
	this(description, new String[] { extension });
    }

    public TomFileFilter(String description, String... extensions) {
	this.description = description;
	for (String ext : extensions) {
	    extensionList.add(ext);
	}
    }

    @Override
    public boolean accept(File f) {
	if (f.isDirectory()) {
	    return true;
	}
	String name = f.getName();
	for (String ext : extensionList) {
	    if (name.endsWith(ext)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public String getDescription() {
	return description;
    }

}
