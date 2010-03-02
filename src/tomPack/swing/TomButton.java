package tomPack.swing;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @version 2009/11/05
 * @author Tom Brito
 */
public class TomButton extends JButton {

    private static final long serialVersionUID = -3656834033489623832L;

    /* All JButton constructors delegate to this. */
    public TomButton(String text, Icon icon) {
	super(text, icon);
	// TODO set the min width to avoid the little "ok" button.
    }

}
