package tomPack.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The components added to a container with this layout will be centered
 * horizontally and vertically.
 * 
 * http://www.experts-exchange.com/Programming/Languages/Java/Q_21068321.html
 */
public class CenterLayout implements LayoutManager {

    @SuppressWarnings("nls")
    public static void main(String[] args) {
	JFrame frm = new JFrame("Center Test");
	JPanel pnl = new JPanel();

	pnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	pnl.add(new JButton("Click Me"));
	pnl.add(new JLabel("Test Label"));
	pnl.add(new JTextField("Sample Text Field"));
	pnl.setBackground(Color.lightGray);

	frm.getContentPane().setLayout(new CenterLayout());
	frm.getContentPane().add(pnl);

	frm.setBounds(100, 100, 500, 400);
	frm.setVisible(true);
    }

    private Component getComponent(Container parent) {
	if (parent.getComponentCount() > 0) {
	    return parent.getComponent(0);
	} else {
	    throw new IllegalStateException("This layout assumes container only has one child component"); //$NON-NLS-1$
	}
    }

    public void addLayoutComponent(String name, Component comp) {
	// do nothig
    }

    public void removeLayoutComponent(Component comp) {
	// do nothig
    }

    public Dimension preferredLayoutSize(Container parent) {
	return getComponent(parent).getPreferredSize();
    }

    public Dimension minimumLayoutSize(Container parent) {
	return getComponent(parent).getMinimumSize();
    }

    public void layoutContainer(Container parent) {
	Component comp = getComponent(parent);
	Dimension size = comp.getPreferredSize();
	Dimension area = parent.getSize();

	Rectangle bounds = new Rectangle();
	bounds.width = size.width;
	bounds.height = size.height;
	bounds.x = (area.width - bounds.width) / 2;
	bounds.y = (area.height - bounds.height) / 2;

	comp.setBounds(bounds);
    }
}