package tomPack.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
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
    
    public void setBorderLayout() {
	setLayout(new BorderLayout());
    }
    
    public void setBorderLayout(int hgap, int vgap) {
	setLayout(new BorderLayout(hgap, vgap));
    }
    
    public void setBoxLayoutX() {
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }
    
    public void setBoxLayoutY() {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    public void setCardLayout() {
	setLayout(new CardLayout());
    }
    
    public void setCardLayout(int hgap, int vgap) {
	setLayout(new CardLayout(hgap, vgap));
    }
    
    public void setFlowLayout() {
	setLayout(new FlowLayout());
    }
    
    public void setFlowLayout(int align) {
	setLayout(new FlowLayout(align));
    }
    
    public void setFlowLayout(int align, int hgap, int vgap) {
	setLayout(new FlowLayout(align, hgap, vgap));
    }
    
    public void setGridBagLayout() {
	setLayout(new GridBagLayout());
    }
    
    public void setGridLayout() {
	setLayout(new GridLayout());
    }
    
    public void setGridLayout(int rows, int cols) {
	setLayout(new GridLayout(rows, cols));
    }
    
    public void setGridLayout(int rows, int cols, int hgap, int vgap) {
	setLayout(new GridLayout(rows, cols, hgap, vgap));
    }
    
    public void setGroupLayout(Container host) {
	setLayout(new GroupLayout(host));
    }
    
    public void setSpringLayout() {
	setLayout(new SpringLayout());
    }
    
    //
    // Other methods
    //

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
