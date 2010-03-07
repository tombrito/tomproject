package tomPack.swing;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class TomPanel extends JPanel {

    public TomPanel() {
	super();
    }

    public TomPanel(LayoutManager mgr) {
	this();
	setLayout(mgr);
    }

    public void addCentered(JComponent component) {
	addCentered(component, true, true);
    }

    /**
     * Add the component centered int the specified axis.<br>
     * Consider using {@link CenterLayout}.
     */
    public void addCentered(JComponent component, boolean centerX, boolean centerY) {
	if (centerX) {
	    component.setAlignmentX(0.5f);
	}
	if (centerY) {
	    component.setAlignmentY(0.5f);
	}
	add(component);
    }

    public void setWaitCursor() {
	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public void setDefaultCursor() {
	setCursor(Cursor.getDefaultCursor());
    }

    public void setBoxLayoutY() {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void setSize(Dimension d) {
	super.setSize(d);
	setPreferredSize(d);
	setMaximumSize(d);
	setMinimumSize(d);
    }

}
