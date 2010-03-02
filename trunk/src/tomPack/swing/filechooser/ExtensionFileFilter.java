package tomPack.swing.filechooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter {
    
    private String extension;
    private String description;

    public ExtensionFileFilter(String extension, String description) {
	this.extension = extension;
	this.description = description;
    }

    @Override
    public boolean accept(File f) {
	return f.isDirectory() || f.getName().endsWith(extension);
    }

    @Override
    public String getDescription() {
	return description;
    }
    
}
