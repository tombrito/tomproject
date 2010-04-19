package tomPack.swing;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TomFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static TomFrame wFrameInstance = null;

    /**
     * Retorna o objeto st�tico JFrame configurado
     */
    public static TomFrame getInstance() {
	if (wFrameInstance == null) {
	    wFrameInstance = new TomFrame();
	}
	return wFrameInstance;
    }

    /**
     * Create and initialize a default WFrame.
     */
    public TomFrame() {
	this("", null); //$NON-NLS-1$
    }

    /**
     * Create and initialize a frame with specified contentPane.
     * 
     * @param contentPane
     */
    public TomFrame(Container contentPane) {
	this("", contentPane); //$NON-NLS-1$
    }

    /**
     * Create and initialize a frame with specified name.
     * 
     * @param name
     */
    public TomFrame(String name) {
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
    public TomFrame(String name, Container contentPane) {
	super(name);
	if (contentPane != null)
	    setContentPane(contentPane);
	pack();
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
	Dimension d = new Dimension(width, height);
	setPreferredSize(d);
	setMinimumSize(d);
	setMaximumSize(d);
	setLocationRelativeTo(null);
    }
    
    public void setPreferredSize(int width, int height) {
	Dimension d = new Dimension(width, height);
        super.setPreferredSize(d);
    }
    
    @Override
    public void pack() {
        super.pack();
        setLocationRelativeTo(null);
    }

    public void setWaitCursor() {
	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public void setDefaultCursor() {
	setCursor(Cursor.getDefaultCursor());
    }

}
