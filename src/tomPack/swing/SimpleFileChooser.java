package tomPack.swing;

import java.io.File;

import javax.swing.JFileChooser;

public class SimpleFileChooser extends JFileChooser {
    
    private static final long serialVersionUID = 5499474442213816724L;

    public int showOpenDialog() {
	return super.showOpenDialog(null);
    }
    
    public int showOpenDialog(File currentDirectory) {
	setCurrentDirectory(currentDirectory);
	return showOpenDialog();
    }

}
