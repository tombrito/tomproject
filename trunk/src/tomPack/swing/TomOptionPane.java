package tomPack.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * A simplified version of {@link JOptionPane}.
 * 
 * @author Tom Brito
 */
public class TomOptionPane {

    /** Delegate to {@link TomDialogYesNo}. */
    public static TomDialogYesNo createNoYesDialog(String title, String question, boolean noAsDefault) {

	/*
	 * TODO Issue #93: at 2 dialogs - what's the advantage of this instead
	 * of TomDialogYesNo.show()?
	 */

	return new TomDialogYesNo(title, question, noAsDefault);
    }

    public static boolean showOkCancelDialog(String title, Object[] fields, final Component initialFocused) {
	return showBooleanDialog(title, fields, initialFocused, JOptionPane.OK_CANCEL_OPTION,
		JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION);
    }

    public static boolean showYesNoDialog(String title, Object[] fields, final Component initialFocused) {
	return showBooleanDialog(title, fields, initialFocused, JOptionPane.YES_NO_OPTION,
		JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
    }

    /*
     * Works just fine at home. See at work.
     */
    @SuppressWarnings("nls")
    public static void main(String[] args) {
	System.out.println("start");
	JTextField tf = new JTextField();
	Object[] fields = new Object[] { "Test", tf };
	showOkCancelDialog("Title", fields, tf);
	System.out.println("end");
    }

    public static boolean showBooleanDialog(String title, Object[] fields, final Component initialFocused,
	    int dialogType, int confirmOption, int negateOption) {

	JOptionPane optionPane = new JOptionPane(fields, JOptionPane.PLAIN_MESSAGE, dialogType, null, null);

	JDialog dialog = optionPane.createDialog(title);

	if (initialFocused == null) {
	    for (Object field : fields) {
		if (field instanceof Component) {
		    Component comp = (Component) field;
		    if (comp.isFocusable()) {
			comp.requestFocusInWindow();
			break;
		    }
		}
	    }
	}
	if (initialFocused != null) {
	    dialog.addWindowListener(new WindowAdapter() {
		@Override
		public void windowActivated(WindowEvent ev) {
		    Timer timer = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
			    initialFocused.requestFocusInWindow();
			}
		    });
		    timer.setRepeats(false);
		    timer.start();
		}
	    });
	}

	dialog.setVisible(true);
	dialog.dispose();

	// Check result
	int result = negateOption;
	try {
	    result = ((Integer) optionPane.getValue()).intValue();
	} catch (Exception e) {
	    // JOptionPane.UNINITIALIZED_VALUE, ignore
	}

	return (result == confirmOption);
    }

    protected int result;

    private TomOptionPane(int result) {
	this.result = result;
    }

    /**
     * @see JOptionPane possible results.
     * @return
     */
    public int getResult() {
	return result;
    }

}
