package tomPack.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.PopupMenu;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class TomPanel extends JPanel {

    //
    // Constructors
    //

    public TomPanel() {
	super();
    }

    public TomPanel(LayoutManager mgr) {
	this();
	setLayout(mgr);
    }

    //
    // Layout methods
    //

    public LayoutManager setBorderLayout() {
	LayoutManager layout = new BorderLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setBorderLayout(int hgap, int vgap) {
	LayoutManager layout = new BorderLayout(hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setBoxLayoutX() {
	LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setBoxLayoutY() {
	LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setCardLayout() {
	LayoutManager layout = new CardLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setCardLayout(int hgap, int vgap) {
	LayoutManager layout = new CardLayout(hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setFlowLayout() {
	LayoutManager layout = new FlowLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setFlowLayout(int align) {
	LayoutManager layout = new FlowLayout(align);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setFlowLayout(int align, int hgap, int vgap) {
	LayoutManager layout = new FlowLayout(align, hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridBagLayout() {
	LayoutManager layout = new GridBagLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridLayout() {
	LayoutManager layout = new GridLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridLayout(int rows, int cols) {
	LayoutManager layout = new GridLayout(rows, cols);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridLayout(int rows, int cols, int hgap, int vgap) {
	LayoutManager layout = new GridLayout(rows, cols, hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGroupLayout(Container host) {
	LayoutManager layout = new GroupLayout(host);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setSpringLayout() {
	LayoutManager layout = new SpringLayout();
	setLayout(layout);
	return layout;
    }

    //
    // Other methods
    //

    public void add(Object... objs) {
	for (Object obj : objs) {
	    if (obj instanceof String) {
		add(new JLabel((String) obj));
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
