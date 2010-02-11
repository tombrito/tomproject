package tomPack.swing;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class SimplePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    public SimplePanel() {
	setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

}
