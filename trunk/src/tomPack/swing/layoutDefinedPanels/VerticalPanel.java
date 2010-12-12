package tomPack.swing.layoutDefinedPanels;

import java.awt.LayoutManager;

import tomPack.swing.TomPanel;

public class VerticalPanel extends TomPanel {

	//
	// Constructors
	//

	public VerticalPanel() {
		super();
		init();
	}

	public VerticalPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}

	public VerticalPanel(LayoutManager layout, boolean isDoubleBuffered) {
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
