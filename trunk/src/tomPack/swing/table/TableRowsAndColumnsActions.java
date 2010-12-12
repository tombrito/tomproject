package tomPack.swing.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import tomPack.externalization.Messages;

/**
 * Actions used to edit a table' rows and columns (add and remove).
 * 
 * @author Tom Brito
 */
public class TableRowsAndColumnsActions {

	// TODO Issue #91: TableRowsAndColumnsActions organization

	private final TableRowAndColumnActHandler handler;

	private final boolean editableColumns;

	public TableRowsAndColumnsActions(
			final TableRowAndColumnActHandler handler, boolean editableColumns) {
		this.handler = handler;
		this.editableColumns = editableColumns;

		// Accelerators:
		handler.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ev) {
				int key = ev.getKeyCode();
				// either defined at buttons name
				if (key == KeyEvent.VK_INSERT) {
					addRow();
				}
				if (key == KeyEvent.VK_ENTER && handler.isLastRowSelected()) {
					addRow();
					ev.consume();
				}
				// either defined at buttons name
				if (key == KeyEvent.VK_DELETE) {
					removeRow();
				}
				super.keyPressed(ev);
			}
		});
	}

	public Action getAddRowAct() {
		return new AbstractAction(Messages.getString("TableRowsAndColumnsActions.0")) { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				addRow();
			}
		};
	}

	public Action getRemoveRowAct() {
		return new AbstractAction(Messages.getString("TableRowsAndColumnsActions.1")) { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				removeRow();
			}
		};
	}

	public Action getAddColumnAct() {
		return new AbstractAction(Messages.getString("TableRowsAndColumnsActions.2")) { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				addColumn();
			}
		};
	}

	public Action getRemoveColumnAct() {
		return new AbstractAction(Messages.getString("TableRowsAndColumnsActions.3")) { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				removeColumn();
			}
		};
	}

	public Action getCloseAct() {
		// "esc" close windows automatic, as the window is a JDialog
		return new AbstractAction(Messages.getString("TableRowsAndColumnsActions.4")) { //$NON-NLS-1$
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		};
	}

	public void addRow() {
		handler.addRow();
	}

	public void removeRow() {
		handler.removeRow();
	}

	public void addColumn() {
		handler.addColumn();
	}

	public void removeColumn() {
		handler.removeColumn();
	}

	public void close() {
		handler.close();
	}

	/**
	 * Return a list of the buttons that perform the actions.
	 * 
	 * @return {@link List} of {@link JButton}s.
	 */
	public List<Component> getOptions() {
		List<Component> options = new ArrayList<Component>();
		options.add(new JButton(getAddRowAct()));
		options.add(new JButton(getRemoveRowAct()));
		if (editableColumns) {
			options.add(new JButton(getAddColumnAct()));
			options.add(new JButton(getRemoveColumnAct()));
		}
		options.add(new JButton(getCloseAct()));
		for (Component op : options) {
			op.setFocusable(false);
		}
		return options;
	}

}
