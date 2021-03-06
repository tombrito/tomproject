package tomPack.swing.table;

import javax.swing.table.AbstractTableModel;

import tomPack.unitTest.TomEntity;

/**
 * A extension of {@link AbstractTableModel} with some new utilities
 * implemented.
 * 
 * @author Tom Brito
 * 
 * @see TomTable
 */
public abstract class TomTableModel extends AbstractTableModel implements
		TomEntity {

	private static final long serialVersionUID = -8707412394448256273L;

	/**
	 * Returns the index of the last column.
	 */
	public int getLastColumn() {
		return (getColumnCount() - 1);
	}

	/**
	 * Returns the index of the last row.
	 */
	public int getLastRow() {
		return (getRowCount() - 1);
	}

	/** Returns the index of the last but X column. */
	public int getLastButXColumn(int x) {
		return (getLastColumn() - x);
	}

	/** Returns the index of the last but X row. */
	public int getLastButXRow(int x) {
		return (getLastRow() - x);
	}

	/** Returns true if the parameter row is the last row index of this model. */
	public boolean isLastRow(int row) {
		return (row == getLastRow());
	}

	/**
	 * Returns true if the parameter column is the last column index of this
	 * model.
	 */
	public boolean isLastColumn(int column) {
		return (column == getLastColumn());
	}

}
