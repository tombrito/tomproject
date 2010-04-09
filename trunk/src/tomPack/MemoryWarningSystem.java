package tomPack;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryNotificationInfo;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.ArrayList;
import java.util.Collection;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.swing.JOptionPane;

import tomPack.externalization.Messages;

/**
 * This class is used to throw a warning in case of low avaiable memory for the
 * JVM.
 * 
 * @see #initMemoryWarningSystem()
 * 
 * @author http://www.javaspecialists.eu/archive/Issue092.html
 * @author Tom Brito
 * 
 * @version 2009/11/06
 */
public class MemoryWarningSystem {

    public interface Listener {
	public void memoryUsageLow(long usedMemory, long maxMemory);
    }

    // ****************************************************
    // * Static attributes and methods
    // ****************************************************

    protected static final MemoryPoolMXBean tenuredGenPool = findTenuredGenPool();

    /**
     * Initialize the memory warning system with a default listener that shows a
     * message dialog if more then 70% of the available memory is in use.<br>
     * Yet, you can set another percentage value using
     * {@link #setPercentageUsageThreshold(double)} method.
     * <p>
     * As alternative to this method, you can create a new instance of
     * {@link MemoryWarningSystem}, set the percentage usage threshold and add
     * your own listener to specify the behaviour.
     */
    public static void initMemoryWarningSystem() {
	setPercentageUsageThreshold(0.7);

	MemoryWarningSystem mws = new MemoryWarningSystem();
	mws.addListener(new MemoryWarningSystem.Listener() {
	    public void memoryUsageLow(long usedMemory, long maxMemory) {
		double percentageUsed = ((double) usedMemory) / maxMemory;
		System.err.println(Messages.getString("Main.10")); //$NON-NLS-1$
		System.err.println(Messages.getString("Main.11") + percentageUsed); //$NON-NLS-1$
		System.err.flush();
		String msg = Messages.getString("Main.12") //$NON-NLS-1$
			+ Messages.getString("Main.13"); //$NON-NLS-1$
		JOptionPane.showMessageDialog(null, msg, Messages.getString("Main.14"), //$NON-NLS-1$
			JOptionPane.WARNING_MESSAGE);
	    }
	});
    }

    /**
     * Set the percentage to fire a warning to the listeners.
     * 
     * @param percentage
     *            a double value percentage between 0.0 (0%) and 1.0 (100%) to
     *            be the warning-fire value.
     */
    public static void setPercentageUsageThreshold(double percentage) {
	if (percentage <= 0.0 || percentage > 1.0) {
	    throw new IllegalArgumentException("Percentage not in range"); //$NON-NLS-1$
	}
	long maxMemory = tenuredGenPool.getUsage().getMax();
	long warningThreshold = (long) (maxMemory * percentage);
	tenuredGenPool.setUsageThreshold(warningThreshold);
    }

    /**
     * Tenured Space Pool can be determined by it being of type HEAP and by it
     * being possible to set the usage threshold.
     */
    private static MemoryPoolMXBean findTenuredGenPool() {
	for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
	    /*
	     * TODO I don't know whether this approach is better, or whether we
	     * should rather check for the pool name "Tenured Gen"?
	     * (javaspecialists)
	     */
	    if (pool.getType() == MemoryType.HEAP && pool.isUsageThresholdSupported()) {
		return pool;
	    }
	}
	throw new AssertionError("Could not find tenured space"); //$NON-NLS-1$
    }

    // ****************************************************
    // * Instance attributes and methods
    // ****************************************************

    protected final Collection<Listener> listeners = new ArrayList<Listener>();

    public MemoryWarningSystem() {
	MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
	NotificationEmitter emitter = (NotificationEmitter) mbean;
	emitter.addNotificationListener(new NotificationListener() {
	    public void handleNotification(Notification n, Object hb) {
		if (n.getType().equals(MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED)) {
		    long maxMemory = tenuredGenPool.getUsage().getMax();
		    long usedMemory = tenuredGenPool.getUsage().getUsed();
		    for (Listener listener : listeners) {
			listener.memoryUsageLow(usedMemory, maxMemory);
		    }
		}
	    }
	}, null, null);
    }

    public boolean addListener(Listener listener) {
	return listeners.add(listener);
    }

    public boolean removeListener(Listener listener) {
	return listeners.remove(listener);
    }

}
