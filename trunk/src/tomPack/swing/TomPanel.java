package tomPack.swing;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.PopupMenu;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class TomPanel extends EasyLayoutPanel {

    //
    // Constructors
    //

    public TomPanel() {
	super();
    }

    public TomPanel(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
    }
    
    public TomPanel(LayoutManager layout) {
	super(layout);
    }

    public TomPanel(LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
    }

    //
    // Border
    //

    public void setEmptyBorder() {
	setBorder(BorderFactory.createEmptyBorder());
    }

    public void setEmptyBorder(int top, int left, int bottom, int right) {
	setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }

    //
    // Other methods
    //

    /**
     * If the object can be converted to a {@link Component}, convert and add.<br>
     * 
     * <ul>
     * <li> {@link Component} is added normally.
     * <li> {@link String} is converted into a {@link JLabel}.
     * <li> {@link Action} is converted into a {@link JButton}.
     * </ul>
     * 
     * @param objs
     *            - objects to be converted and added.
     */
    public void add(Object... objs) {
	for (Object obj : objs) {
	    if (obj instanceof String) {
		add(new JLabel((String) obj));
	    } else if (obj instanceof Action) {
		add(new JButton((Action) obj));
	    } else if (obj instanceof PopupMenu) {
		super.add((PopupMenu) obj);
	    } else if (obj instanceof Component) {
		super.add((Component) obj);
	    } else {
		System.err.println("Unsuported component: " + obj); //$NON-NLS-1$
	    }
	}
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

    @Override
    public void setSize(Dimension d) {
	super.setSize(d);
	setPreferredSize(d);
	setMaximumSize(d);
	setMinimumSize(d);
    }

}
