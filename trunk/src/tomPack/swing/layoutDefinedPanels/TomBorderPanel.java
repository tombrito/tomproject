package tomPack.swing.layoutDefinedPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import tomPack.swing.TomPanel;

/**
 * A panel with convenience methods for {@link BorderLayout}.
 */
public class TomBorderPanel extends TomPanel {

    //
    // Constructors
    //

    public TomBorderPanel() {
	super();
	init();
    }

    public TomBorderPanel(boolean isDoubleBuffered) {
	super(isDoubleBuffered);
	init();
    }

    public TomBorderPanel(LayoutManager layout, boolean isDoubleBuffered) {
	super(layout, isDoubleBuffered);
	init();
    }

    //
    // 
    //
    
    private void init() {
	setBorderLayout();
    }

    public void addCenter(Component comp) {
	add(comp, BorderLayout.CENTER);
    }

    public void addSouth(Component comp) {
	add(comp, BorderLayout.SOUTH);
    }

    public void addNorth(Component comp) {
	add(comp, BorderLayout.NORTH);
    }

    public void addEast(Component comp) {
	add(comp, BorderLayout.EAST);
    }

    public void addWeast(Component comp) {
	add(comp, BorderLayout.WEST);
    }

}
