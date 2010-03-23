package tomPack.swing.table;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tomPack.externalization.Messages;

/**
 * An extension of JTable with some more resources.
 * 
 * @see TomTableModel
 * 
 * @version 2009/11/05
 * @author Tom Brito
 */
public class TomTable extends JTable implements Cloneable {

    private static final long serialVersionUID = 76564082602133170L;

    /** Default constructor. */
    public TomTable() {
	this(null);
    }

    /**
     * Constructor with defined number of rows and columns.
     * 
     * @param rows
     *            - Default number of <code>rows</code>.
     * @param columns
     *            - Default number of columns.
     */
    public TomTable(int rows, int columns) {
	this(new DefaultTableModel(rows, columns));
    }

    /**
     * DefaultTableModel constructor. All constructors redirect to this one.
     * 
     * @param model
     *            - DefaultTableModel for JTable.
     */
    public TomTable(TableModel model) {
	super(model);
	setRowSelectionAllowed(false);
	setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    @Override
    public TomTable clone() throws CloneNotSupportedException {
	return (TomTable) super.clone();
    }

    public void focus(int row, int col) {
	changeSelection(row, col, false, false);
    }

    public void focus(int firstRow, int firstCol, int lastRow, int lastCol) {
	focus(firstRow, firstCol);
	changeSelection(lastRow, lastCol, false, true);
    }

    /**
     * Return the next column, from selected column, equivalent to
     * (getSelectedColumn() + 1). <br>
     * WARNING: For consistence reasons, do not check if the next column exists.
     * 
     * @return the next column, from selected column.
     */
    public int getNextColumn() {
	return (getSelectedColumn() + 1);
    }

    /**
     * Return the previous column, from selected column, equivalent to
     * (getSelectedColumn() - 1). <br>
     * WARNING: For consistence reasons, do not check if the previous column
     * exists.
     * 
     * @return previous column, from selected column.
     */
    public int getPreviousColumn() {
	return (getSelectedColumn() - 1);
    }

    /**
     * <b>HINT!<br>
     * In most cases, like cells iteration, prefer to use
     * {@link JTable#getColumnCount()} for less {@link TomTable} tighten.</b>
     * <p>
     * Returns the index of the last column.
     */
    public int getLastColumn() {
	return (getColumnCount() - 1);
    }

    /**
     * <b>HINT!<br>
     * In most cases, like cells iteration, prefer to use
     * {@link JTable#getRowCount()} for less {@link TomTable} tighten.</b>
     * <p>
     * Returns the index of the last row.
     */
    public int getLastRow() {
	return (getRowCount() - 1);
    }

    /**
     * Return the next row, from selected row, equivalent to (getSelectedRow() +
     * 1).
     * <p>
     * <b>WARNING: For consistence reasons, do not check if the next row
     * exists.</b>
     * 
     * @return the next row, from selected row.
     */
    public int getNextRow() {
	return (getSelectedRow() + 1);
    }

    /**
     * Return the previous row, from selected row, equivalent to
     * (getSelectedRow() + 1). <br>
     * WARNING: For consistence reasons, do not check if the previous row
     * exists.
     * 
     * @return the previous row, from selected row.
     */
    public int getPreviousRow() {
	return (getSelectedRow() - 1);
    }

    /**
     * The preferred column is the selected column. If no column is selected,
     * the preferred column is the first column.
     * 
     * @return the selected or first column.
     */
    public int getPreferredColumn() {
	int col = getSelectedColumn();
	return (col < 0) ? 0 : col;
    }

    /**
     * The preferred row is the selected row. If no row is selected, the
     * preferred row is the first row.
     * 
     * @return the selected or first row.
     */
    public int getPreferredRow() {
	int row = getSelectedRow();
	return (row < 0) ? 0 : row;
    }

    /**
     * Verifica se a coluna atual é a de número <code>num</code>.
     * 
     * @param num
     *            : Número da coluna para verificar.
     * @return <code>true</code> se for a coluna numero <code>num</code>.
     */
    public boolean isSelectedColumn(int num) {
	return (getSelectedColumn() == num);
    }

    /**
     * Verifica se a linha atual é a de número <code>num</code>.
     * 
     * @param num
     *            : Número da linha para verificar.
     * @return <code>true</code> se for a linha numero <code>num</code>.
     */
    public boolean isSelectedRow(int num) {
	return (getSelectedRow() == num);
    }

    /**
     * Verifica se a linha atual é a última linha da tabela.
     * 
     * @return <code>true</code> se a linha atual for a última linha da tabela.
     */
    public boolean isLastRowSelected() {
	return isLastRow(getSelectedRow());
    }

    /**
     * Verifica se a linha de número <code>num</code> é a última linha da
     * tabela.
     * 
     * @param num
     *            : Número da linha para verificar.
     * @return <code>true</code> se for a última linha da tabela.
     */
    public boolean isLastRow(int num) {
	return (getLastRow() == num);
    }

    /**
     * Verifica se a coluna atual é a última coluna da tabela.
     * 
     * @return <code>true</code> se a coluna atual for a última coluna da
     *         tabela.
     */
    public boolean isLastColumnSelected() {
	return isLastCol(getSelectedColumn());
    }

    /**
     * Verifica se a coluna de número <code>num</code> é a última coluna da
     * tabela.
     * 
     * @param num
     *            : Número da coluna para verificar.
     * @return <code>true</code> se for a última coluna da tabela.
     */
    public boolean isLastCol(int columnIndex) {
	return (getLastColumn() == columnIndex);
    }

    @Override
    public String toString() {
	return (Messages.getString("TomTable.0") + getRowCount() + "][" + getColumnCount() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    @Override
    public void tableChanged(TableModelEvent e) {
	if (e != null) {
	    if (e.getType() == TableModelEvent.DELETE) {
		int firstRow = e.getFirstRow();
		int lastRow = e.getLastRow();
		tableCellsDeleted(firstRow, lastRow);
	    }
	    if (e.getType() == TableModelEvent.INSERT) {
		int firstRow = e.getFirstRow();
		int lastRow = e.getLastRow();
		tableCellsInserted(firstRow, lastRow);
	    }
	}
	super.tableChanged(e);
    }

    /**
     * Called wherever cells are inserted on TableModel.
     * 
     * @param firstRow
     *            - the first inserted row from the table
     * @param lastRow
     *            - the last inserted row from the table
     */
    protected void tableCellsInserted(int firstRow, int lastRow) {
	// provided for subclasses use
    }

    /**
     * Called wherever cells are deleted on TableModel.
     * 
     * @param firstRow
     *            - the first deleted row from the table
     * @param lastRow
     *            - the last deleted row from the table
     */
    protected void tableCellsDeleted(int firstRow, int lastRow) {
	// provided for subclasses use
    }

    public boolean confirmRemoveSelectedRow() {
	if (getSelectedRow() < 0) {
	    return false;
	}
	int result = JOptionPane.showConfirmDialog(null,
		Messages.getString("TomTable.3"), Messages.getString("TomTable.4"), //$NON-NLS-1$ //$NON-NLS-2$
		JOptionPane.YES_NO_OPTION);
	if (result == JOptionPane.YES_OPTION) {
	    return true;
	}
	return false;
    }

    public boolean confirmRemoveSelectedColumn() {
	if (getSelectedColumn() < 0) {
	    return false;
	}
	int result = JOptionPane.showConfirmDialog(null, Messages.getString("TomTable.5"), //$NON-NLS-1$
		Messages.getString("TomTable.6"), JOptionPane.YES_NO_OPTION); //$NON-NLS-1$
	if (result == JOptionPane.YES_OPTION) {
	    return true;
	}
	return false;
    }

}
