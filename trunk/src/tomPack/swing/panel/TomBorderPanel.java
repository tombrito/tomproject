package tomPack.swing.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import tomPack.swing.TomPanel;

/**
 * A panel with convenience methods for {@link BorderLayout}.
 */
public class TomBorderPanel extends TomPanel {
    
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
