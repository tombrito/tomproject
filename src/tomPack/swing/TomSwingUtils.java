package tomPack.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import tomPack.Debug;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * Utilities for Graphical User Interfaces.
 * 
 * @author Tom Brito
 */
public class TomSwingUtils {

    // ****************************************************
    // * Data Transfer
    // ****************************************************

    /**
     * Delegate to {@link TransferHandler#getCutAction()} actionPerformed()
     * method.
     */
    public static void cut(Object obj) {
	TransferHandler.getCutAction().actionPerformed(new TomActionEvent(obj));
    }

    /**
     * Delegate to {@link TransferHandler#getCopyAction()} actionPerformed()
     * method.
     */
    public static void copy(Object obj) {
	TransferHandler.getCopyAction().actionPerformed(new TomActionEvent(obj));
    }

    /**
     * Delegate to {@link TransferHandler#getPasteAction()} actionPerformed()
     * method.
     */
    public static void paste(Object obj) {
	TransferHandler.getPasteAction().actionPerformed(new TomActionEvent(obj));
    }

    // ****************************************************
    // * Application Initialization
    // ****************************************************

    public static void initLaf(LookAndFeel laf) {
	initLaf(laf.getClass().getName());
    }

    public static void initLaf(String laf) {

	// winLAF considerations:
	// - JTable serialization (not really a problem).
	// - very small buttons at JToolBar

	// metalLAF considerations:
	// - Save and Open Dialogs fail some times.
	// - Don't have the default behavior for ALT to select menus.
	// - ATL button does not works properly in Windows environment
	// - JFileChooser does not works properly in Windows environment

	// nimbusLAF considerations:
	// - Renderization problems with tables and tool bar's buttons colors
	// (gloogle it to see yourself).
	// - ATL button does not works properly in Windows environment
	// - JFileChooser does not works properly in Windows environment

	try {
	    UIManager.setLookAndFeel(laf);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /** Set the current system {@link LookAndFeel} as default. */
    public static void initSystemLaf() {
	initLaf(UIManager.getSystemLookAndFeelClassName());
    }

    public static void initNimbusLaf() {
	initLaf(new NimbusLookAndFeel());
    }

    public static void initWindowsLaf() {
	initLaf(new WindowsLookAndFeel());
    }

    public static void initMetalLaf() {
	initLaf(new MetalLookAndFeel());
    }

    /**
     * Use the {@link Translator#translate()} to translate, and the laf methods
     * to init laf. Preferred LAFs are:
     * 
     * <li>{@link #initSystemLaf()} <li>{@link #initNimbusLaf()}
     */
    @Deprecated
    public static void configUIManager() {
	Translator.translate();
	if (UIManager.getLookAndFeel() instanceof MetalLookAndFeel) {
	    UIManager.put("ToolTip.background", new ColorUIResource(Color.YELLOW)); //$NON-NLS-1$
	}
    }

    /**
     * Set the default output (System.out) and error output (System.err) to a
     * new file inside the specified path.
     * 
     * @param path
     *            a existing directory path, ending with a
     *            {@link File#separator}.
     * @throws FileNotFoundException
     *             if don't find the specified path.
     */
    public static void initErrorLogFile(String path) throws Exception {
	if (Debug.isDebugMode()) {
	    return; // use default console output
	}
	String filename = ""; //$NON-NLS-1$
	Calendar calendar = Calendar.getInstance();
	filename += "" + calendar.get(Calendar.YEAR); //$NON-NLS-1$
	filename += "-" + (calendar.get(Calendar.MONTH) + 1); //$NON-NLS-1$
	filename += "-" + calendar.get(Calendar.DAY_OF_MONTH); //$NON-NLS-1$

	File dir = new File(path);
	if (!dir.exists()) {
	    boolean created = dir.mkdir();
	    if (!created) {
		throw new Exception("Could not create dir: " + dir); //$NON-NLS-1$
	    }
	}
	if (!path.endsWith(File.separator)) {
	    path += File.separator;
	}

	int todaysFileCount = 0;
	File f = new File(path + filename);
	while (f.exists()) {
	    f = new File(path + filename + "-" + todaysFileCount++); //$NON-NLS-1$
	}

	PrintStream fout = new PrintStream(f);
	System.setOut(fout);
	System.setErr(fout);
    }

    // ****************************************************
    // * Other methods
    // ****************************************************

    /**
     * Returns a {@link JPanel} with the specified label above the specified
     * component.<br>
     * Yet, set {@link JLabel#setLabelFor(Component)}, to help assistive
     * technology (search in the java tutorial for more info).
     */
    public static TomPanel createLabeledComponent(JLabel label, Component comp) {
	GridLayout layout = new GridLayout(2, 1);
	TomPanel panel = new TomPanel(layout);
	panel.add(label);
	panel.add(comp);
	label.setLabelFor(comp); // to help assistive technology
	return panel;
    }

    /**
     * Calculate the X axis's start position, based on the Graphics object
     * metrics, to print the string centered.
     * 
     * @return X axis's start position to print the specified text centered.
     * @param g
     *            Graphics where the text will be printed.
     * @param text
     *            the text to be printed.
     */
    public static int getCenterStart(Graphics g, String text) {
	FontMetrics metrics = g.getFontMetrics();
	int recWidth = g.getClipBounds().width;
	int strWidth = metrics.stringWidth(text);
	int startX = (recWidth - strWidth) / 2;
	return startX;
    }

    public static void setWaitCursor(Component c) {
	c.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    public static void setDefaultMouseCursor(Component c) {
	c.setCursor(Cursor.getDefaultCursor());
    }

    public static void alignComponents(Container container, float alignmentX, float alignmentY) {
	for (Component component : container.getComponents()) {
	    if (component instanceof JComponent) {
		JComponent jc = (JComponent) component;
		jc.setAlignmentX(alignmentX);
		jc.setAlignmentY(alignmentY);
	    }
	}
    }

    public static TomPanel createInputPanelH(String name, int inputSize) {
	TomPanel panel = new TomPanel();

	JLabel label = new JLabel(name);

	TomTextField textField = new TomTextField(inputSize);
	textField.setColumns(inputSize);

	panel.add(label);
	panel.add(textField);
	label.setLabelFor(textField); // to help assistive technology

	return panel;
    }

    public static TomPanel createInputPanelV(String name, int inputSize) {
	return createLabeledComponent(new JLabel(name), new TomTextField(inputSize));
    }

    @SuppressWarnings("nls")
    public static void main(String[] args) {
	TomPanel panel = new TomPanel();
	panel.add(createInputPanelV("Name", 10));
	panel.add(createInputPanelV("Address", 30));

	TomMainFrame frame = new TomMainFrame(panel);
	frame.setVisible(true);
    }

    public static void setInitialFocused(Window window, final Component initialFocused) {
	if ((window != null) && (initialFocused != null)) {
	    window.addWindowListener(new WindowAdapter() {
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
    }

}
