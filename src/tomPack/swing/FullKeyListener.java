package tomPack.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyListener;

/**
 * Add a listener to a {@link Component} and all of its childrens, recursively.
 * 
 * @version 2009/11/26
 * @author Adapted From: JavaTip 69: Press Escape to close your Java dialog
 *         windows
 *         http://www.javaworld.com/javaworld/javatips/javatip69/EscapeDialog
 *         .java.txt
 * @author Tom Brito
 */
public class FullKeyListener implements ContainerListener {

    // The following function is recursive and is intended for internal use
    // only. It is private to prevent anyone calling it from other classes
    // The function takes a Component as an argument and adds this Dialog as
    // a
    // KeyListener to it.
    // Besides it checks if the component is actually a Container and if it
    // is,
    // there are 2 additional things to be done to this Container :
    // 1 - add this Dialog as a ContainerListener to the Container
    // 2 - call this function recursively for every child of the Container.

    private KeyListener listener;

    public FullKeyListener(KeyListener l) {
	this.listener = l;
    }

    /**
     * Add Key and Container Listener recursively to target {@link Component}.
     * 
     * @param c
     *            - the parent component to start add the listeners
     */
    public void addFullKeyListenerTo(Component c) {

	/*
	 * To be on the safe side, try to remove KeyListener first just in case
	 * it has been added before. If not, it won't do any harm
	 */
	c.removeKeyListener(listener);
	// Add KeyListener to the Component passed as an argument
	c.addKeyListener(listener);

	if (c instanceof Container) {

	    // Component c is a Container. The following cast is safe.
	    Container cont = (Container) c;

	    /*
	     * To be on the safe side, try to remove ContainerListener first
	     * just in case it has been added before. If not, it won't do any
	     * harm
	     */
	    cont.removeContainerListener(this);
	    // Add ContainerListener to the Container.
	    cont.addContainerListener(this);

	    // Get the Container's array of children Components.
	    Component[] children = cont.getComponents();

	    // For every child repeat the above operation.
	    for (int i = 0; i < children.length; i++) {
		addFullKeyListenerTo(children[i]);
	    }
	}
    }

    // The following function is the same as the function above with the
    // exception that it does exactly the opposite - removes this Dialog
    // from the listener lists of Components.

    /**
     * Remove Key and Container Listener recursively.
     */
    public void removeFullKeyListenerFrom(Component c) {

	c.removeKeyListener(listener);

	if (c instanceof Container) {

	    Container cont = (Container) c;

	    cont.removeContainerListener(this);

	    Component[] children = cont.getComponents();

	    for (int i = 0; i < children.length; i++) {
		removeFullKeyListenerFrom(children[i]);
	    }
	}
    }

    //
    // ContainerListener interface
    //

    // This function is called whenever a Component or a Container is added
    // to
    // another Container belonging to this Dialog
    public void componentAdded(ContainerEvent e) {
	addFullKeyListenerTo(e.getChild());
    }

    // This function is called whenever a Component or a Container is
    // removed
    // from another Container belonging to this Dialog
    public void componentRemoved(ContainerEvent e) {
	removeFullKeyListenerFrom(e.getChild());
    }

}