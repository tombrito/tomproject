package tomPack.swing.filechooser;

import java.io.File;

import javax.swing.JFileChooser;

public class TomFileChooser extends JFileChooser {
    
    private static final long serialVersionUID = 5499474442213816724L;

    public int showOpenDialog() {
	return super.showOpenDialog(null);
    }
    
    public int showOpenDialog(File currentDirectory) {
	setCurrentDirectory(currentDirectory);
	return showOpenDialog();
    }

    public int showSaveDialog() {
        return super.showSaveDialog(null);
    }

    public int showSaveDialog(File currentDirectory) {
	setCurrentDirectory(currentDirectory);
        return super.showSaveDialog(null);
    }

}
