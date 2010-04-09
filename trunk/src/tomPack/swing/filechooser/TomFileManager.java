package tomPack.swing.filechooser;

import java.io.File;

import lombok.Data;

/**
 * @deprecated use {@link TomFileChooser}.
 */
@Data
@Deprecated
public class TomFileManager {

    protected final TomFileChooser fc = new TomFileChooser();

    protected File currDir; // current selected directory
    protected File currFile; // current selected file

    public TomFileManager() {
	// all file extensions will be accepted
    }

    public TomFileManager(String ext, String description) {
	fc.setFileFilter(new TomFileFilter(ext, description));
    }

    /** Delegate to {@link TomFileChooser#showOpenDialog()}. */
    public File showOpenDialog() {
	return fc.showOpenDialog();
    }

    /** @return the selected File to write on. */
    public File showSaveDialog() {
	return fc.showSaveDialog();
    }

}
