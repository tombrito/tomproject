package tomPack.swing;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Simple abstract transfer handler. Contains some default setting for simple
 * transfers.
 * 
 * @see TransferHandler
 * 
 * @version 2009/11/26
 * @author Tom Brito
 */
public abstract class TomTransferHandler extends TransferHandler {

    private static final long serialVersionUID = 8447770143212171658L;

    protected final DataFlavor flavor;

    public TomTransferHandler(DataFlavor flavor) {
	this.flavor = flavor;
    }

    //
    // Methods
    //

    protected abstract Transferable createTransferable(JComponent comp);

    public int getSourceActions(JComponent c) {
	return COPY_OR_MOVE;
    }

    public abstract boolean importData(TransferSupport info);

    public boolean importData(JComponent c, Transferable t) {
	throw new UnsupportedOperationException("Please, use importData(TransferSupport)"); //$NON-NLS-1$
    }

    public boolean canImport(JComponent c, DataFlavor[] flavors) {
	for (DataFlavor flavor : flavors) {
	    if (flavor.equals(this.flavor)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public boolean canImport(TransferSupport support) {
	boolean canImport = super.canImport(support);
	// if can import, show the drop location
	support.setShowDropLocation(canImport);
	return canImport;
    }

}
