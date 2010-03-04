package tomPack.swing;

import javax.swing.JPasswordField;

public class TomPasswordField extends JPasswordField {

    private static final long serialVersionUID = 3735026530089373798L;

    public String getPasswordStr() {
	return String.valueOf(getPassword());
    }

    public boolean isPassword(String value) {
	return getPasswordStr().equals(value);
    }

}
