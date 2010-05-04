package tomPack.swing;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A extended version of {@link JDialog}, with fast-keys to yes (s - sim) and no
 * (n - n�o).
 * 
 * <p>
 * Adapted From: JavaTip 69: Press Escape to close your Java dialog windows
 * http://www.javaworld.com/javaworld/javatips/javatip69/EscapeDialog.java.txt
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
 * @see TomOptionPane
 * @see TomDialogYesNo
 * 
 * @author Tom Brito
 */
public class TomDialog extends JDialog {

    protected class OKAct extends AbstractAction {
	private static final long serialVersionUID = -1509070577508136349L;

	public OKAct(String name) {
	    super(name);
	}

	public void actionPerformed(ActionEvent e) {
	    ok();
	}
    }

    protected class CancelAct extends AbstractAction {
	private static final long serialVersionUID = -9160278828912035221L;

	public CancelAct(String name) {
	    super(name);
	}

	public void actionPerformed(ActionEvent e) {
	    cancel();
	}
    }

    /**
     * Defines a KeyAdapater for {@link TomDialog}.
     * 
     * @author Tom Brito
     */
    class DialogKeyAdapter extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {

	    /*
	     * TODO Issue #90: TomDialog should not exit if press 'esc' while
	     * editing a JComboBox.
	     * 
	     * This, for while, don't justify remove the escape handle of
	     * TomDialog.
	     */
	    int code = e.getKeyCode();
	    if (code == KeyEvent.VK_ESCAPE)
		cancel();
	}
    }

    // ****************************************************
    // * Attributes
    // ****************************************************

    private static final long serialVersionUID = 1L;

    /**
     * Result of the dialog. True if OK, false if user cancel or close.
     */
    private boolean result = false;

    // ****************************************************
    // * Constructors
    // ****************************************************

    public TomDialog(JFrame owner, String title) {
	super(owner, title, true);
	init();
    }

    public TomDialog(JDialog owner, String title) {
	super(owner, title, true);
	init();
    }

    public TomDialog(String title) {
	this((JFrame) null, title);
    }

    public TomDialog(JFrame owner, String title, JPanel centerPanel, JButton... optionsButtons) {
	this(owner, title);
	initView(centerPanel, optionsButtons);
    }

    public TomDialog(JDialog owner, String title, JPanel centerPanel, JButton... optionsButtons) {
	this(owner, title);
	initView(centerPanel, optionsButtons);
    }

    public TomDialog(String title, JPanel centerPanel, JButton... optionsButtons) {
	this(title);
	initView(centerPanel, optionsButtons);
    }

    // ****************************************************
    // * View initialization
    // ****************************************************

    private void init() {
	setResizable(false); // by default, dialogs shouldn't be resizable
	FullKeyListener listener = new FullKeyListener(new DialogKeyAdapter());
	listener.addFullKeyListenerTo(this);
    }

    public void initView(JPanel centerPanel, JButton... optionsButtons) {

	// Table
	JScrollPane tableScrollPane = new JScrollPane();
	tableScrollPane.setViewportView(centerPanel);
	tableScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
	tableScrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

	setLayout(new BorderLayout());
	add(tableScrollPane);

	// Options
	if (optionsButtons.length > 0) {
	    JPanel optionsPane = new JPanel();
	    for (Component op : optionsButtons) {
		optionsPane.add(op);
	    }
	    add(optionsPane, BorderLayout.SOUTH);
	}

	pack();
    }

    // ****************************************************
    // * Other methods
    // ****************************************************

    @Override
    public void setSize(Dimension d) {
	setSize(d.width, d.height);
    }

    @Override
    public void setSize(int width, int height) {
	super.setSize(width, height);
	Dimension d = new Dimension(width, height);
	setPreferredSize(d);
	setMaximumSize(d);
	setMinimumSize(d);
	setLocationRelativeTo(null);
    }

    @Override
    public void pack() {
	super.pack();
	setLocationRelativeTo(null);
    }

    public JButton getOkButton(String name) {
	return new JButton(new OKAct(name));
    }

    public JButton getCancelButton(String name) {
	return new JButton(new CancelAct(name));
    }

    @Override
    public void setVisible(boolean visible) {
	if (visible) {
	    result = false; // reset
	}
	super.setVisible(visible);
    }

    /**
     * Returns the result of the dialog box. <br>
     * <code>true</code> if user press the OK button, <code>false</code> if user
     * cancel or close.
     * 
     * @deprecated use the more intuitive name isConfirm (better readability for
     *             if-blocks)
     */
    @Deprecated
    public boolean getResult() {
	return result;
    }

    public boolean isConfirm() {
	return getResult();
    }

    /** Set the result of the dialog. */
    protected void setResult(boolean result) {
	this.result = result;
    }

    /** Confirm the dialog. */
    protected void ok() {
	yes();
    }

    /** Cancel the dialog. */
    protected void cancel() {
	no();
    }

    protected void yes() {
	result = true;
	setVisible(false);
    }

    protected void no() {
	result = false;
	setVisible(false);
    }

    public void setWaitCursor() {
	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public void setDefaultCursor() {
	setCursor(Cursor.getDefaultCursor());
    }

    public void setPreferredSize(int i, int j) {
	super.setPreferredSize(new Dimension(i, j));
    }

}
