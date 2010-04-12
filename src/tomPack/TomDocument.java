package tomPack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import tomPack.swing.TomTextField;

/**
 * Extension of {@link PlainDocument} with some utilities.
 * 
 * @version 2009/11/02
 * @author Tom Brito
 */
public class TomDocument extends PlainDocument {

    private static final long serialVersionUID = 1L;

    /**
     * @return a default {@link TomTextField}.
     */
    public static TomDocument createTomDocument() {
	return new TomDocument();
    }

    /**
     * @return a CAPITAL LETTER text field.
     * @deprecated use {@link #setCaps(boolean)}
     */
    @Deprecated
    public static TomDocument createCapitalDocument() {
	TomDocument doc = new TomDocument();
	doc.setCaps(true);
	return doc;
    }

    /**
     * @return a numbers-only text field.
     * @deprecated use {@link #setNumbersOnly(boolean)}
     */
    @Deprecated
    public static TomDocument createNumericalDocument() {
	TomDocument doc = new TomDocument();
	doc.setNumbersOnly(true);
	return doc;
    }

    //
    //
    //

    protected boolean caps = false;

    protected int maxLength;

    protected boolean numbersOnly = false;

    //
    //
    //

    private TomDocument() {
	// use static methods to create
    }

    //
    //
    //

    /**
     * Check if the CAPITAL LETTER is on/off.
     */
    public boolean isCaps() {
	return caps;
    }

    /**
     * Turn the CAPITAL LETTER on/off.
     */
    public void setCaps(boolean caps) {
	this.caps = caps;
    }

    /**
     * Returns the maximum of digits this text field allows.
     */
    public int getMaxLenth() {
	return maxLength;
    }

    /**
     * Sets the maximum of digits this text field allows. If zero, do not apply
     * a limit of digits.
     * 
     * @param maxLenth
     */
    public void setMaxLength(int maxLenth) {
	this.maxLength = maxLenth;
    }

    public boolean isNumbersOnly() {
	return numbersOnly;
    }

    public void setNumbersOnly(boolean numbersOnly) {
	this.numbersOnly = numbersOnly;
    }

    // TODO Issue #89: TomDocument.insertString shouldn't be using a filter?
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

	if ((maxLength > 0) && getLength() >= maxLength) {
	    return;
	}

	if (caps) {
	    str = str.toUpperCase();
	}

	if (numbersOnly) {
	    // anything not (^) between 0 e 9.
	    Pattern p = Pattern.compile("[^0-9]"); //$NON-NLS-1$
	    Matcher m = p.matcher(str);
	    if (m.find()) {
		return;
	    }

	}

	super.insertString(offs, str, a);
    }

}