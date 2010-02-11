package tomPack;

import javax.swing.JTextField;

/**
 * Extension of {@link JTextField} with some utilities.
 * 
 * @see SimpleDocument
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class SimpleTextField extends JTextField {

    private static final long serialVersionUID = 4691528418447887461L;

    //
    // Static creators
    //

    /**
     * @return a default {@link SimpleTextField}.
     */
    public static SimpleTextField createTomTextField() {
	return new SimpleTextField();
    }

    /**
     * @return a CAPITAL LETTER text field.
     */
    public static SimpleTextField createCapitalTextField() {
	SimpleDocument doc = SimpleDocument.createCapitalDocument();
	return new SimpleTextField(doc);
    }

    /**
     * @return a numbers-only text field.
     */
    public static SimpleTextField createNumericalTextField() {
	// TODO create a subclass NumericalTextField with getValue() method
	// returning a int
	SimpleDocument doc = SimpleDocument.createNumericalDocument();
	return new SimpleTextField(doc);
    }

    //
    // Attributes
    //

    private SimpleDocument doc;

    //
    // Constructors
    //

    protected SimpleTextField() {
	this(null);
    }

    protected SimpleTextField(SimpleDocument doc) {
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

    @Override
    public String toString() {
	String str = getText();
	return (str == null) ? "" : str; //$NON-NLS-1$
    }

}
