package tomPack.swing;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author Tom Brito
 */
public class SimpleFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static SimpleFrame wFrameInstance = null;

    /**
     * Retorna o objeto stático JFrame configurado
     */
    public static SimpleFrame getInstance() {
	if (wFrameInstance == null) {
	    wFrameInstance = new SimpleFrame();
	}
	return wFrameInstance;
    }

    /**
     * Create and initialize a default WFrame.
     */
    public SimpleFrame() {
	this("", null); //$NON-NLS-1$
    }

    /**
     * Create and initialize a frame with specified contentPane.
     * 
     * @param contentPane
     */
    public SimpleFrame(Container contentPane) {
	this("", contentPane); //$NON-NLS-1$
    }

    /**
     * Create and initialize a frame with specified name.
     * 
     * @param name
     */
    public SimpleFrame(String name) {
	this(name, null);
    }

    /**
     * Create and initialize a frame.
     * 
     * @param name
     *            - the default frame's name.
     * @param contentPane
     *            - the content pane for the frame to show.
     */
    public SimpleFrame(String name, Container contentPane) {
	super(name);
	if (contentPane != null)
	    setContentPane(contentPane);
	pack();
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setSize(new Dimension(800, 600));
	setLocationRelativeTo(null);
    }

    @Override
    public void setSize(Dimension d) {
	super.setSize(d);
	setLocationRelativeTo(null);
    }

    @Override
    public void setSize(int width, int height) {
	super.setSize(width, height);
	setLocationRelativeTo(null);
    }

}
