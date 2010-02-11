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
 * A extended version of {@link SimpleDialog}.<br>
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
 * @see SimpleDialog
 * @see SimpleOptionPane
 * 
 * @version 2009/11/18
 * @author Tom Brito
 */
public class SimpleDialogYesNo extends SimpleDialog {

    /**
     * Defines a KeyAdapater for {@link SimpleDialog}.
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
	// TODO what's the advantage of this instead of
	// SimpleOptionPane.showYesNoDialog()?
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
    public static boolean show(String title, String question, boolean noAsDefault) {
	SimpleDialogYesNo dialog = new SimpleDialogYesNo(title, question, noAsDefault);
	dialog.setVisible(true);
	return dialog.getResult();
    }

    //
    // Initialization
    //

    final boolean noAsDefault;

    public SimpleDialogYesNo(String title, String question) {
	this(title, question, false);
    }

    public SimpleDialogYesNo(String title, String question, final boolean noAsDefault) {

	super(title);
	this.noAsDefault = noAsDefault;

	// Add full key listener
	FullKeyListener listener = new FullKeyListener(new DialogKeyAdapter());
	listener.addFullKeyListenerTo(this);

	// Cria JLabel para pergunta
	JPanel questionPane = new JPanel();
	questionPane.add(new JLabel(question));

	// Cria botões
	final JButton yes = createYesButton();
	final JButton no = createNoButton();
	JPanel btnsPane = new JPanel();
	btnsPane.add(yes);
	btnsPane.add(no);

	// Insere componentes na dialog
	setLayout(new BorderLayout());
	add(questionPane, BorderLayout.NORTH);
	add(btnsPane, BorderLayout.CENTER);

	// Chama foco no botão Não
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
	JButton yes = new JButton(Messages.getString("TomDialogYesNo.0"), Images.getYes()); //$NON-NLS-1$
	yes.setMnemonic('s');

	yes.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ok();
	    }
	});
	return yes;
    }

    protected JButton createNoButton() {
	JButton no = new JButton(Messages.getString("TomDialogYesNo.1"), Images.getNo()); //$NON-NLS-1$
	no.setMnemonic('n');

	no.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		cancel();
	    }
	});
	return no;
    }

}
