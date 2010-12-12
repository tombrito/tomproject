package tomPack.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tomPack.externalization.Messages;
import tomPack.swing.images.Images;

/**
 * A extended version of {@link TomDialog}.<br>
 * 
 * <p>
 * <strong>Warning:</strong> This class is not thread safe.
 * 
 * <p>
 * <strong>Warning:</strong> Serialized objects of this class will not be
 * compatible with future releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running the
 * same version of this class.
 * 
 * @see TomDialog
 * @see TomOptionPane
 * 
 * @author Tom Brito
 */
public class TomDialogYesNo extends TomDialog {

	/**
	 * Defines a KeyAdapater for {@link TomDialog}.
	 * 
	 * @version 2009/11/02
	 * @author Tom Brito
	 */
	class DialogKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			// ESCAPE Key is handled by super class key listener
			if (code == KeyEvent.VK_ENTER) {
				if (noAsDefault)
					cancel();
				else
					ok();
			} else if (code == KeyEvent.VK_S) {
				ok();
			} else if (code == KeyEvent.VK_N) {
				cancel();
			}
		}
	}

	private static final long serialVersionUID = 1L;

	/**
	 * A static fast-access way to show a dialog and get the result.
	 * 
	 * @param title
	 *            - the title of the dialog.
	 * @param question
	 *            - the question to be shown on the dialog.
	 * @return <code>true</code> if, and only if, the user choose Yes option at
	 *         the dialog.
	 */
	public static boolean show(String title, String question) {
		/*
		 * TODO Issue #93: at 2 dialogs - what's the advantage of this instead
		 * of SimpleDialogYesNo.show()?
		 */
		return show(title, question, false);
	}

	/**
	 * A static fast-access way to show a dialog and get the result.
	 * 
	 * @param title
	 *            - the title of the dialog.
	 * @param question
	 *            - the question to be shown on the dialog.
	 * @param noAsDefault
	 *            - if <code>true</code>, the No button will be the default
	 *            button.
	 * @return <code>true</code> if, and only if, the user choose Yes option at
	 *         the dialog.
	 */
	public static boolean show(String title, String question,
			boolean noAsDefault) {
		TomDialogYesNo dialog = new TomDialogYesNo(title, question, noAsDefault);
		dialog.setVisible(true);
		return dialog.isConfirm();
	}

	//
	// Initialization
	//

	protected final boolean noAsDefault;

	public TomDialogYesNo(String title, String question) {
		this(title, question, false);
	}

	public TomDialogYesNo(String title, String question,
			final boolean noAsDefault) {

		super(title);
		this.noAsDefault = noAsDefault;

		// Add full key listener
		FullKeyListener listener = new FullKeyListener(new DialogKeyAdapter());
		listener.addFullKeyListenerTo(this);

		// Create JLabel for question
		JPanel questionPane = new JPanel();
		questionPane.add(new JLabel(question));

		// Create buttons
		final JButton yes = createYesButton();
		final JButton no = createNoButton();
		JPanel btnsPane = new JPanel();
		btnsPane.add(yes);
		btnsPane.add(no);

		// Insert components on the dialog
		setLayout(new BorderLayout());
		add(questionPane, BorderLayout.NORTH);
		add(btnsPane, BorderLayout.CENTER);

		// Calls focus to "no" button
		if (noAsDefault) {
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent e) {
					super.componentShown(e);
					no.requestFocusInWindow();
				}
			});
		}

		pack();
		setLocationRelativeTo(null);
	}

	protected JButton createYesButton() {
		JButton yes = new JButton(
				Messages.getString("TomDialogYesNo.0"), Images.getYes()); //$NON-NLS-1$
		yes.setMnemonic('s');

		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		return yes;
	}

	protected JButton createNoButton() {
		JButton no = new JButton(
				Messages.getString("TomDialogYesNo.1"), Images.getNo()); //$NON-NLS-1$
		no.setMnemonic('n');

		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		return no;
	}

}
