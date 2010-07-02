package tomPack.swing;

import java.awt.Container;

public class TomMainFrame extends TomFrame {

    private static final long serialVersionUID = -3842665837457703762L;

    public TomMainFrame() {
	this("", null); //$NON-NLS-1$
    }

    public TomMainFrame(String name) {
	this(name, null);
    }

    public TomMainFrame(Container contentPane) {
	this("", contentPane); //$NON-NLS-1$
    }

    public TomMainFrame(String name, Container contentPane) {
	super(name, contentPane);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	maximize();
    }
    
}
