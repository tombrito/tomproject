package tomPack.swing.filechooser;

/**
 * Extension of {@link TomFileChooser} for directories.
 * 
 * @author Tom Brito
 */
public class TomDirChooser extends TomFileChooser {

	private static final long serialVersionUID = -6191189902994159959L;

	public TomDirChooser() {
		setFileSelectionMode(DIRECTORIES_ONLY);
	}

}
