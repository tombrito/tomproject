package tomPack.swing;

public class TomNumericalField extends TomTextField {

    private static final long serialVersionUID = -5345336767099372835L;

    public TomNumericalField() {
	setNumbersOnly(true);
    }

    @Override
    protected void setNumbersOnly(boolean numbersOnly) {
	if (!numbersOnly) {
	    throw new IllegalArgumentException("Trying to make a numerical field non-numerical."); //$NON-NLS-1$
	}
    }

    public int getValue() {
	String text = getText();
	return (text.isEmpty()) ? 0 : Integer.valueOf(text).intValue();
    }

    // TODO Issue #88: create TomNumericalField.setMaxValue method

    public static void main(String[] args) {
	// FIXME Issue 104: TomNumericalField - "numbers only" is not working
	TomMainFrame f = new TomMainFrame();
	f.setSize(200, 200);
	f.restore();
	f.getContentPane().add(new TomNumericalField());
	f.setVisible(true);
    }

}
