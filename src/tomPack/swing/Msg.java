package tomPack.swing;

import javax.swing.JOptionPane;

import lombok.Getter;

import tomPack.Debug;
import tomPack.externalization.Messages;

/**
 * Show messages to the user on graphical mode.
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class Msg {

    /** Used for JUnit Test Cases. */
    @Getter
    private static boolean testMode = false;
    
    public static void setTestMode(boolean testMode) {
	Msg.testMode = testMode;
    }

    private static String lastDebugMsg = ""; //$NON-NLS-1$

    /**
     * Mostra a mensagem em formato <i>Debug</i> na GUI e no console. Mas
     * <b>apenas</b> se o atributo <code>Main.DEBUG</code> estiver como
     * <code>true</code>.
     * 
     * @param msg
     *            : Mensagem a ser exibida.
     */
    public static void debug(Class<?> classe, String msg) {
	if (!Debug.isDebugMode()) {
	    return;
	}
	if (!lastDebugMsg.equals(msg)) {
	    lastDebugMsg = msg;
	    String className = classe.getSimpleName();
	    String debugMsg = ("DEBUG <" + className + "> - " + msg); //$NON-NLS-1$ //$NON-NLS-2$
	    System.out.println(debugMsg);
	}
    }

    /**
     * Mostra a mensagem em formato <i>Erro</i> na GUI e no console.
     * 
     * @param msg
     *            : Mensagem a ser exibida.
     */
    public static void error(Exception e) {
	Debug.println(Messages.getString("Msg.3")); //$NON-NLS-1$
	Debug.println(e.getMessage());
	e.printStackTrace();
	if (!Msg.testMode) {
	    try {
		String msg = (e.getMessage() + Messages.getString("Msg.4")); //$NON-NLS-1$
		JOptionPane.showMessageDialog(null, msg, Messages.getString("Msg.5"), //$NON-NLS-1$
			JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e2) {
		e.printStackTrace();
	    }
	}
    }

    public static void fail(String msg) {
	fail(null, msg);
    }

    /**
     * Mostra a mensagem em formato <i>Erro</i> na GUI apenas.
     * 
     * @param msg
     *            : Mensagem a ser exibida.
     */
    public static void fail(String title, String msg) {
	if (title == null)
	    title = Messages.getString("Msg.6"); //$NON-NLS-1$
	if (!Msg.testMode) {
	    Debug.println(Messages.getString("Msg.7") + msg); //$NON-NLS-1$
	    try {
		msg += (Messages.getString("Msg.8")); //$NON-NLS-1$
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Mostra a mensagem em formato <i>Atenção</i> na GUI e no console.
     */
    public static void warning(String msg) {
	warning(Messages.getString("Msg.9"), msg); //$NON-NLS-1$
    }

    /**
     * Mostra a mensagem em formato <i>Atenção</i> na GUI e no console.
     */
    public static void warning(String title, String msg) {
	if (!Msg.testMode) {
	    Debug.println(Messages.getString("Msg.10") + msg); //$NON-NLS-1$
	    try {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Mostra a mensagem em formato <i>Informação</i> na GUI e no console.
     * 
     * @param msg
     *            : Mensagem a ser exibida.
     */
    public static void inf(String msg) {
	inf(null, msg);
    }

    public static void inf(String title, String msg) {
	if (!Msg.testMode) {
	    if (title == null)
		title = Messages.getString("Msg.11"); //$NON-NLS-1$
	    Debug.println(Messages.getString("Msg.12") + msg); //$NON-NLS-1$
	    try {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

}
