package tomPack.swing.filechooser;


public class TomDirChooser extends TomFileChooser {

    private static final long serialVersionUID = -6191189902994159959L;

    public TomDirChooser() {
	setFileSelectionMode(DIRECTORIES_ONLY);
    }

}
