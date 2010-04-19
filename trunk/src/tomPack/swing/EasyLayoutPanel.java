package tomPack.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * A {@link JPanel} with layout convenience methods.
 */
public class EasyLayoutPanel extends JPanel {

    public LayoutManager setBorderLayout() {
	LayoutManager layout = new BorderLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setBorderLayout(int hgap, int vgap) {
	LayoutManager layout = new BorderLayout(hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setBoxLayoutX() {
	LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setBoxLayoutY() {
	LayoutManager layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setCardLayout() {
	LayoutManager layout = new CardLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setCardLayout(int hgap, int vgap) {
	LayoutManager layout = new CardLayout(hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setFlowLayout() {
	LayoutManager layout = new FlowLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setFlowLayout(int align) {
	LayoutManager layout = new FlowLayout(align);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setFlowLayout(int align, int hgap, int vgap) {
	LayoutManager layout = new FlowLayout(align, hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridBagLayout() {
	LayoutManager layout = new GridBagLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridLayout() {
	LayoutManager layout = new GridLayout();
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridLayout(int rows, int cols) {
	LayoutManager layout = new GridLayout(rows, cols);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGridLayout(int rows, int cols, int hgap, int vgap) {
	LayoutManager layout = new GridLayout(rows, cols, hgap, vgap);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setGroupLayout() {
	LayoutManager layout = new GroupLayout(this);
	setLayout(layout);
	return layout;
    }

    public LayoutManager setSpringLayout() {
	LayoutManager layout = new SpringLayout();
	setLayout(layout);
	return layout;
    }

    /** Set {@link CenterLayout} as layout manager. */
    public void setCenterLayout() {
	setLayout(new CenterLayout());
    }

}
