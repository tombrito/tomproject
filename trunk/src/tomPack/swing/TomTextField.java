package tomPack.swing;

import javax.swing.JTextField;

import tomPack.TomDocument;

/**
 * Extension of {@link JTextField} with some utilities.
 * 
 * @see TomDocument
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class TomTextField extends JTextField {

    private static final long serialVersionUID = 4691528418447887461L;

    //
    // Static creators
    //

    /**
     * @return a default {@link TomTextField}.
     */
    @Deprecated
    public static TomTextField createSimpleTextField() {
	return new TomTextField();
    }

    /**
     * @return a CAPITAL LETTER text field.
     * @deprecated use {@link #setCaps(boolean)}
     */
    @Deprecated
    public static TomTextField createCapitalTextField() {
	TomDocument doc = TomDocument.createCapitalDocument();
	return new TomTextField(doc);
    }

    /**
     * @return a numbers-only text field.
     * @deprecated use {@link TomNumericalField}.
     */
    @Deprecated
    public static TomTextField createNumericalTextField() {
	TomDocument doc = TomDocument.createNumericalDocument();
	return new TomTextField(doc);
    }

    //
    // Attributes
    //

    private final TomDocument doc;

    //
    // Constructors
    //

    public TomTextField() {
	this(null, 0);
    }

    public TomTextField(int columns) {
	this(null, columns);
    }

    protected TomTextField(TomDocument doc) {
	this(doc, 0);
	// solve the problems with the high in some layouts
	setMaximumSize(getPreferredSize());
    }

    protected TomTextField(TomDocument doc, int columns) {
	super(columns);
	if (doc == null) {
	    doc = TomDocument.createTomDocument();
	}
	this.doc = doc;
	setDocument(doc);
    }

    //
    // Other methods
    //

    public boolean isEmpty() {
	return getText().isEmpty();
    }

    public void setMaxLength(int maxLenth) {
	doc.setMaxLength(maxLenth);
    }

    public void setCaps(boolean caps) {
	doc.setCaps(caps);
    }

    protected void setNumbersOnly(boolean numbersOnly) {
	doc.setNumbersOnly(numbersOnly);
    }

    protected TomDocument getTomDocument() {
	return doc;
    }

    @Override
    public String toString() {
	String str = getText();
	return (str == null) ? "" : str; //$NON-NLS-1$
    }

}
