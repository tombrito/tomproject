package tomPack.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class TomToolBar extends JToolBar {
    
    @Override
    public JButton add(Action a) {
        JButton btn = new JButton(a);
        btn.setText(null);
        btn.setFocusable(false);
        // as is a toolbat button, use the small icon
        btn.setIcon((Icon) a.getValue(Action.SMALL_ICON));
        super.add(btn);
        return btn;
    }

}
