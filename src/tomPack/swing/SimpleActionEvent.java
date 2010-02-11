package tomPack.swing;

import java.awt.event.ActionEvent;

/**
 * An simplest version of {@link ActionEvent}.
 * 
 * @see ActionEvent
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class SimpleActionEvent extends ActionEvent {

	private static final long serialVersionUID = -1094996820318320550L;

	public SimpleActionEvent(Object source) {
		super(source, ActionEvent.ACTION_PERFORMED, null);
	}

}
