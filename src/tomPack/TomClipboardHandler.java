package tomPack;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;

/**
 * @version 2009/12/12
 * @author Tom Brito
 */
public class TomClipboardHandler {

    public static void copy(Transferable contents) {
	Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
	c.setContents(contents, null);
    }

    public static Object getTransferData(DataFlavor flavor) throws Exception {
	Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
	if (!c.isDataFlavorAvailable(flavor)) {
	    return null;
	}
	return c.getContents(null).getTransferData(flavor);
    }

    @SuppressWarnings("nls")
    public static void main(String[] args) throws Exception {
	MyTransferableTest a1 = new MyTransferableTest();
	MyTransferableTest a2 = new MyTransferableTest();
	a1.i = 8;
	TomClipboardHandler.copy(a1);
	a2 = (MyTransferableTest) TomClipboardHandler.getTransferData(MyTransferableTest.flavor);
	System.out.println("Copy from a1.i=" + a1.i);
	System.out.println("Paste to a2.i=" + a2.i);
    }

}

@SuppressWarnings("nls")
class MyTransferableTest implements Transferable, Serializable {

    private static final long serialVersionUID = 1L;

    static DataFlavor flavor = new DataFlavor(MyTransferableTest.class, MyTransferableTest.class.getName());

    int i = 7;

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
	System.out.println("Getting transfer data..");
	return this;
    }

    public DataFlavor[] getTransferDataFlavors() {
	System.out.println("Getting transfer data flavors.");
	return new DataFlavor[] { flavor };
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
	System.out.println("Informming data support:" + flavor);
	return (flavor.equals(flavor));
	// Em alguns testes feitos, o retorno deste método não fez diferença
	// alguma.
    }

    @Override
    public String toString() {
	return getClass().getName() + "[" + i + "]";
    }

}