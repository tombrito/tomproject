package tomPack.swing.filechooser;

import java.io.File;

import javax.swing.JFileChooser;

import lombok.Data;
import tomPack.swing.filechooser.ExtensionFileFilter;
import tomPack.swing.filechooser.SimpleFileChooser;

@Data
public class TomFileManager {

    private final String ext;
    private final String description;

    private File currDir; // current selected directory
    private File currFile; // current selected file

    public File showOpenDialog() {
	SimpleFileChooser fc = new SimpleFileChooser();
	fc.setFileFilter(new ExtensionFileFilter(ext, description));

	fc.setCurrentDirectory(currDir);
	int result = fc.showOpenDialog();
	if (result == JFileChooser.APPROVE_OPTION) {
	    File selected = fc.getSelectedFile();
	    currFile = selected;
	    currDir = selected.getParentFile();
	    return currFile;
	}
	return null;
    }

    // TODO showSaveAsDialog, ask if override file, return boolean to save or
    // not

}
