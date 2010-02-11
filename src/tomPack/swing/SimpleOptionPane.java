package tomPack.swing;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * A simplified version of {@link JOptionPane}.
 * 
 * @author Tom Brito
 */
public class SimpleOptionPane {

	/**
	 * Delegato to {@link SimpleDialogYesNo}.
	 */
	public static SimpleDialogYesNo createNoYesDialog(String title,
			String question, boolean noAsDefault) {

		/*
		 * TODO what's the advantage of this instead of
		 * SimpleDialogYesNo.show()?
		 */

		return new SimpleDialogYesNo(title, question, noAsDefault);
	}

	public static boolean showOkCancelDialog(String title, Object[] fields,
			final Component initialFocused) {
		return showBooleanDialog(title, fields, initialFocused,
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.OK_OPTION,
				JOptionPane.CANCEL_OPTION);
	}

	public static boolean showYesNoDialog(String title, Object[] fields,
			final Component initialFocused) {
		return showBooleanDialog(title, fields, initialFocused,
				JOptionPane.YES_NO_OPTION, JOptionPane.YES_OPTION,
				JOptionPane.NO_OPTION);
	}

	public static boolean showBooleanDialog(String title, Object[] fields,
			final Component initialFocused, int dialogType, int confirmOption,
			int negateOption) {

		JOptionPane optionPane = new JOptionPane(fields,
				JOptionPane.PLAIN_MESSAGE, dialogType, null,
				null);

		JDialog dialog = optionPane.createDialog(title);

		if (initialFocused != null) {
			// Call focus to initialFocused component after show dialog
			dialog.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent e) {
					super.componentShown(e);
					initialFocused.requestFocusInWindow();
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

	int result;

	private SimpleOptionPane(int result) {
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
