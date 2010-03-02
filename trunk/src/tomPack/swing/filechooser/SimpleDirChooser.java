package tomPack.swing.filechooser;


public class SimpleDirChooser extends TomFileChooser {

    private static final long serialVersionUID = -6191189902994159959L;

    public SimpleDirChooser() {
	setFileSelectionMode(DIRECTORIES_ONLY);
    }

}
