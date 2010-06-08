package tomPack.swing;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import tomPack.externalization.Messages;

public class TomAction extends AbstractAction {

    private final Method method;
    private final Object object;
    private String failMsg = Messages.getString("TomAction.0"); //$NON-NLS-1$

    //
    // Constructors
    //

    /**
     * If use this constructor, must override {@link #tryExecute()} to execute
     * the action.
     */
    public TomAction(String name) {
	this(name, (String) null);
    }

    /**
     * If use this constructor, must override {@link #tryExecute()} to execute
     * the action.
     */
    public TomAction(String name, String failMsg) {
	this(name, failMsg, (Icon) null);
    }

    /**
     * If use this constructor, must override {@link #tryExecute()} to execute
     * the action.
     */
    public TomAction(String name, String failMsg, Icon icon) {
	super(name);
	method = null;
	object = null;
	setFailMsg(failMsg);
	setIcon(icon);
    }

    public TomAction(String methodName, Object object, String name) {
	super(name);
	try {
	    method = object.getClass().getDeclaredMethod(methodName);
	    method.setAccessible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	    /*
	     * Throw a RuntimeException, so the app will not even be initilized,
	     * so i can correct a wrong method name before create a app dist.
	     */
	    throw new RuntimeException("Fail to create method " + object.getClass() + "." + methodName); //$NON-NLS-1$//$NON-NLS-2$
	}
	this.object = object;
    }

    public TomAction(String methodName, Object object, String name, String failMsg, Icon icon) {
	this(methodName, object, name);
	setFailMsg(failMsg);
	setIcon(icon);
    }

    //
    // Action putValue methods
    //

    public void setAcceleratorKey(Object newValue) {
	putValue(ACCELERATOR_KEY, newValue);
    }

    public void setActionCommandKey(Object newValue) {
	putValue(ACTION_COMMAND_KEY, newValue);
    }

    public void setDisplayedMnemonicIndexKey(Object newValue) {
	putValue(DISPLAYED_MNEMONIC_INDEX_KEY, newValue);
    }

    public void setLargeIconKey(Object newValue) {
	putValue(LARGE_ICON_KEY, newValue);
    }

    public void setMnemonicKey(Object newValue) {
	putValue(MNEMONIC_KEY, newValue);
    }

    public void setSelectedKey(Object newValue) {
	putValue(SELECTED_KEY, newValue);
    }

    public void setDefault(Object newValue) {
	putValue(DEFAULT, newValue);
    }

    public void setLongDescription(Object newValue) {
	putValue(LONG_DESCRIPTION, newValue);
    }

    public void setName(Object newValue) {
	putValue(NAME, newValue);
    }

    public void setShortDescription(Object newValue) {
	putValue(SHORT_DESCRIPTION, newValue);
    }

    public void setSmallIcon(Object newValue) {
	putValue(SMALL_ICON, newValue);
    }

    //
    // Other methods
    //

    public void actionPerformed(ActionEvent ev) {
	try {
	    if (method != null) {
		method.invoke(object);
	    } else {
		tryExecute();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    Msg.fail(failMsg);
	}
    }

    /**
     * This method must be overridden by subclasses.
     */
    protected void tryExecute() throws Exception {}

    public void setFailMsg(String failMsg) {
	if (failMsg != null && !failMsg.isEmpty()) {
	    this.failMsg = failMsg;
	}
    }

    public void setIcon(Object icon) {
	setSmallIcon(icon);
	setLargeIconKey(icon);
    }

}
