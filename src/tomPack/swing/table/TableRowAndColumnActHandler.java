package tomPack.swing.table;

import java.awt.event.KeyListener;

/**
 * Handler for {@link TableRowsAndColumnsActions}.
 * 
 * @author Tom Brito
 */
public interface TableRowAndColumnActHandler {

	/**
	 * Add a new row to the table.
	 */
	void addRow();

	/**
	 * Remove selected row from the table.
	 */
	void removeRow();

	/**
	 * Add a new column to the table.
	 */
	void addColumn();

	/**
	 * Remove selected column from the table.
	 */
	void removeColumn();

	/**
	 * Event for close the table dialog.
	 */
	void close();

	/**
	 * Listen keys to handle accelerators (as DEL key to remove a row)
	 * 
	 * @param keyListener
	 *            the {@link KeyListener} to be added.
	 */
	void addKeyListener(KeyListener keyListener);

	boolean isLastRowSelected();

}
