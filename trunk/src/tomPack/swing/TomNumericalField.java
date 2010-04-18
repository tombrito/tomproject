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
	return Integer.valueOf(getText()).intValue();
    }

    // TODO Issue #88: create TomNumericalField.setMaxValue method

}