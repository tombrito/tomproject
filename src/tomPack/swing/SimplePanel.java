package tomPack.swing;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SimplePanel extends JPanel {

	private static final long serialVersionUID = 1L;

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
