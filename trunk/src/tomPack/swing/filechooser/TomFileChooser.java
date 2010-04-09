package tomPack.swing.filechooser;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;
import tomPack.Debug;
import tomPack.externalization.Messages;

/**
 * A powered {@link JFileChooser}. The facilities include remember the last
 * selected directory, and provide methods to show dialogs and return the
 * {@link File} instead of the int result of the dialog.
 */
public class TomFileChooser extends JFileChooser {

    // current directory and its getter and setter are provided by super class

    @Getter
    protected File currentFile;

    @Getter
    @Setter
    protected boolean askBeforeOverrideFile = true;

    //
    // Initialization
    //

    public TomFileChooser() {
	// all file extensions will be accepted
    }

    public TomFileChooser(String ext, String description) {
	setFileFilter(new TomFileFilter(ext, description));
    }

    //
    // Getters / Setters
    //

    /**
     * Set current file, and set the current directory to the new current file's
     * parent directory.
     */
    public void setCurrentFile(File currentFile) {
	this.currentFile = currentFile;
	if (currentFile != null) {
	    setCurrentDirectory(currentFile.getParentFile());
	}
    }

    //
    // Open dialog
    //
    
    @Override
    public int showOpenDialog(Component parent) throws HeadlessException {
	int result = super.showOpenDialog(parent);
	if (result == JFileChooser.APPROVE_OPTION) {
	    setCurrentFile(getSelectedFile());
	}
	return result;
    }

    /**
     * Set the current directory and delegate to {@link #showOpenDialog()}.
     * 
     * @return delegated result {@link #showOpenDialog()}.
     */
    public File showOpenDialog(File currentDirectory) {
	setCurrentDirectory(currentDirectory);
	return showOpenDialog();
    }

    /** @return the selected File to open or null. */
    public File showOpenDialog() {
	int result = showOpenDialog((Component) null);
	return (result == JFileChooser.APPROVE_OPTION) ? currentFile : null;
    }

    //
    // Save dialog
    //

    @Override
    public int showSaveDialog(Component parent) throws HeadlessException {
	int result = super.showSaveDialog(parent);
	if (result == JFileChooser.APPROVE_OPTION) {
	    setCurrentFile(getSelectedFile());
	    if (askBeforeOverrideFile && (currentFile != null) && currentFile.exists()) {
		String msg = Messages.getString("TomFileChooser.0"); //$NON-NLS-1$
		String title = Messages.getString("TomFileChooser.1"); //$NON-NLS-1$
		int ok = JOptionPane.showConfirmDialog(this, msg, title, JOptionPane.YES_NO_CANCEL_OPTION);
		if (ok == JOptionPane.CANCEL_OPTION) {
		    return JFileChooser.CANCEL_OPTION;
		}
		if (ok == JOptionPane.NO_OPTION) {
		    return showSaveDialog(parent);
		}
	    }
	}
	return result;
    }

    /**
     * Set current directory and delegate to {@link #showSaveDialog()}.
     */
    public File showSaveDialog(File currentDirectory) {
	setCurrentDirectory(currentDirectory);
	return showSaveDialog();
    }

    /** @return the selected File to write on. */
    public File showSaveDialog() {
	int result = showSaveDialog((Component) null);
	return (result == JFileChooser.APPROVE_OPTION) ? currentFile : null;
    }

    public static void main(String[] args) {
	System.out.println("begin"); //$NON-NLS-1$
	Debug.setDebugMode(true);
	new TomFileChooser().showSaveDialog();
	System.out.println("end"); //$NON-NLS-1$
    }

}
