package tomPack.swing;

import javax.swing.JFileChooser;

public class SimpleFileChooser extends JFileChooser {
    
    private static final long serialVersionUID = 5499474442213816724L;

    public int showOpenDialog() {
	return showOpenDialog(null);
    }

}
