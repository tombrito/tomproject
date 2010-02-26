package tomPack.swing;

import javax.swing.JTextField;

import tomPack.SimpleDocument;

/**
 * Extension of {@link JTextField} with some utilities.
 * 
 * @see SimpleDocument
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
	SimpleDocument doc = SimpleDocument.createCapitalDocument();
	return new TomTextField(doc);
    }

    /**
     * @return a numbers-only text field.
     * @deprecated use {@link TomNumericalField}.
     */
    @Deprecated
    public static TomTextField createNumericalTextField() {
	// TODO create a subclass NumericalTextField with getValue() method
	// returning a int
	SimpleDocument doc = SimpleDocument.createNumericalDocument();
	return new TomTextField(doc);
    }

    //
    // Attributes
    //

    private SimpleDocument doc;

    //
    // Constructors
    //

    public TomTextField() {
	this(null, 0);
    }
    
    public TomTextField(int columns) {
	this(null, columns);
    }

    protected TomTextField(SimpleDocument doc) {
	this(doc, 0);
    }
	
    protected TomTextField(SimpleDocument doc, int columns) {
	super(columns);
	if (doc == null) {
	    doc = SimpleDocument.createTomDocument();
	}
	this.doc = doc;
	setDocument(doc);
    }

    //
    // Other methods
    //

    protected SimpleDocument getTomDocument() {
	return doc;
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
    
    @Override
    public String toString() {
	String str = getText();
	return (str == null) ? "" : str; //$NON-NLS-1$
    }

}
