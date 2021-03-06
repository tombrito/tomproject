package tomPack.swing.layoutDefinedPanels;

import java.awt.LayoutManager;

import tomPack.swing.TomPanel;

public class HorizontalPanel extends TomPanel {

	//
	// Constructors
	//

	public HorizontalPanel() {
		super();
		init();
	}

	public HorizontalPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}

	public HorizontalPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		init();
	}

	//
	//
	//

	private void init() {
		setBoxLayoutY();
	}

}
