package tomPack.swing;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public class TomAction extends AbstractAction {

    private static final long serialVersionUID = -1398491979352649127L;

    private final Method method;
    private final Object object;
    private String failMsg = "Falha ao executar ação";

    public TomAction(String methodName, Object object, String name, String failMsg, Icon icon) {
	super(name, icon);
	try {
	    this.method = object.getClass().getDeclaredMethod(methodName);
	} catch (Exception e) {
	    e.printStackTrace();
	    /*
	     * Throw a RuntimeException, so the app will not even be initilized,
	     * so i can correct a wrong method name before create a app dist.
	     */
	    throw new RuntimeException("Fail to create method " + object.getClass() + "." + methodName); //$NON-NLS-1$//$NON-NLS-2$
	}
	this.object = object;
	if (failMsg != null && !failMsg.isEmpty()) {
	    this.failMsg = failMsg;
	}
    }

    public void actionPerformed(ActionEvent ev) {
	try {
	    method.invoke(object);
	} catch (Exception e) {
	    e.printStackTrace();
	    Msg.fail(failMsg);
	}
    }

}
