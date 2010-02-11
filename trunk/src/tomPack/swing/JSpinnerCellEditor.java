package tomPack.swing;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

/**
 * An alternative to {@link DefaultCellEditor}, using here a {@link JSpinner} as
 * the editor.
 * 
 * @version 10/10/2009
 * @author Tom Brito
 */
public class JSpinnerCellEditor extends AbstractCellEditor implements
		TableCellEditor, TreeCellEditor {

	private static final long serialVersionUID = 1L;

	private JSpinner spinner;

	public JSpinnerCellEditor(JSpinner spinner) {
		this.spinner = spinner;
	}

	public Object getCellEditorValue() {
		return spinner.getValue();
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return spinner;
	}

	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
		return spinner;
	}

}
