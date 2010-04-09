package tomPack.swing.table;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * {@link TomTable} that works only with {@link DefaultTableModel}, and have the
 * delegations to {@link DefaultTableModel} methods.
 */
public class TypedTable<T> extends TomTable {

    private static final long serialVersionUID = 1793819982167790132L;

    // cannot be protected (see the setter)
    private DefaultTableModel model;

    // ****************************************************
    // * Overrides methods
    // ****************************************************

    @Override
    public DefaultTableModel getModel() {
	return model;
    }

    @Override
    public void setModel(TableModel dataModel) {
	if (!(dataModel instanceof DefaultTableModel)) {
	    String err = "Just DefaultTableModel supported."; //$NON-NLS-1$
	    throw new UnsupportedOperationException(err);
	}
	this.model = (DefaultTableModel) dataModel;
	super.setModel(dataModel);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof TypedTable<?>) {
	    TypedTable<?> table = (TypedTable<?>) obj;
	    return model.equals(table.model);
	}
	return false;
    }

    @Override
    public int hashCode() {
	return model.hashCode();
    }

    // ****************************************************
    // * DefaultTableModel delegation
    // ****************************************************

    /**
     * @param columnName
     * @param columnData
     * @see javax.swing.table.DefaultTableModel#addColumn(java.lang.Object, java.lang.Object[])
     */
    public void addColumn(Object columnName, T[] columnData) {
	model.addColumn(columnName, columnData);
    }

    /**
     * @param columnName
     * @param columnData
     * @see javax.swing.table.DefaultTableModel#addColumn(java.lang.Object, java.util.Vector)
     */
    public void addColumn(Object columnName, Vector<T> columnData) {
	model.addColumn(columnName, columnData);
    }

    /**
     * @param columnName
     * @see javax.swing.table.DefaultTableModel#addColumn(java.lang.Object)
     */
    public void addColumn(Object columnName) {
	model.addColumn(columnName);
    }

    /**
     * @param rowData
     * @see javax.swing.table.DefaultTableModel#addRow(java.lang.Object[])
     */
    public void addRow(T[] rowData) {
	model.addRow(rowData);
    }

    /**
     * @param rowData
     * @see javax.swing.table.DefaultTableModel#addRow(java.util.Vector)
     */
    public void addRow(Vector<T> rowData) {
	model.addRow(rowData);
    }

    /**
     * @param columnName
     * @return
     * @see javax.swing.table.AbstractTableModel#findColumn(java.lang.String)
     */
    public int findColumn(String columnName) {
	return model.findColumn(columnName);
    }

    /**
     * @param row
     * @param column
     * @see javax.swing.table.AbstractTableModel#fireTableCellUpdated(int, int)
     */
    public void fireTableCellUpdated(int row, int column) {
	model.fireTableCellUpdated(row, column);
    }

    /**
     * @param e
     * @see javax.swing.table.AbstractTableModel#fireTableChanged(javax.swing.event.TableModelEvent)
     */
    public void fireTableChanged(TableModelEvent e) {
	model.fireTableChanged(e);
    }

    /**
     * 
     * @see javax.swing.table.AbstractTableModel#fireTableDataChanged()
     */
    public void fireTableDataChanged() {
	model.fireTableDataChanged();
    }

    /**
     * @param firstRow
     * @param lastRow
     * @see javax.swing.table.AbstractTableModel#fireTableRowsDeleted(int, int)
     */
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
	model.fireTableRowsDeleted(firstRow, lastRow);
    }

    /**
     * @param firstRow
     * @param lastRow
     * @see javax.swing.table.AbstractTableModel#fireTableRowsInserted(int, int)
     */
    public void fireTableRowsInserted(int firstRow, int lastRow) {
	model.fireTableRowsInserted(firstRow, lastRow);
    }

    /**
     * @param firstRow
     * @param lastRow
     * @see javax.swing.table.AbstractTableModel#fireTableRowsUpdated(int, int)
     */
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
	model.fireTableRowsUpdated(firstRow, lastRow);
    }

    /**
     * 
     * @see javax.swing.table.AbstractTableModel#fireTableStructureChanged()
     */
    public void fireTableStructureChanged() {
	model.fireTableStructureChanged();
    }

    /**
     * @return
     * @see javax.swing.table.DefaultTableModel#getDataVector()
     */
    @SuppressWarnings("unchecked")
    public Vector<T> getDataVector() {
	return model.getDataVector();
    }

    /**
     * @return
     * @see javax.swing.table.AbstractTableModel#getTableModelListeners()
     */
    public TableModelListener[] getTableModelListeners() {
	return model.getTableModelListeners();
    }

    /**
     * @param row
     * @param column
     * @return
     * @see javax.swing.table.DefaultTableModel#getValueAt(int, int)
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getValueAt(int row, int column) {
	return (T) model.getValueAt(row, column);
    }

    /**
     * @param row
     * @param rowData
     * @see javax.swing.table.DefaultTableModel#insertRow(int, java.lang.Object[])
     */
    public void insertRow(int row, T[] rowData) {
	model.insertRow(row, rowData);
    }

    /**
     * @param row
     * @param rowData
     * @see javax.swing.table.DefaultTableModel#insertRow(int, java.util.Vector)
     */
    public void insertRow(int row, Vector<T> rowData) {
	model.insertRow(row, rowData);
    }

    /**
     * @param start
     * @param end
     * @param to
     * @see javax.swing.table.DefaultTableModel#moveRow(int, int, int)
     */
    public void moveRow(int start, int end, int to) {
	model.moveRow(start, end, to);
    }

    /**
     * @param event
     * @see javax.swing.table.DefaultTableModel#newDataAvailable(javax.swing.event.TableModelEvent)
     */
    public void newDataAvailable(TableModelEvent event) {
	model.newDataAvailable(event);
    }

    /**
     * @param e
     * @see javax.swing.table.DefaultTableModel#newRowsAdded(javax.swing.event.TableModelEvent)
     */
    public void newRowsAdded(TableModelEvent e) {
	model.newRowsAdded(e);
    }

    /**
     * @param row
     * @see javax.swing.table.DefaultTableModel#removeRow(int)
     */
    public void removeRow(int row) {
	model.removeRow(row);
    }

    /**
     * @param event
     * @see javax.swing.table.DefaultTableModel#rowsRemoved(javax.swing.event.TableModelEvent)
     */
    public void rowsRemoved(TableModelEvent event) {
	model.rowsRemoved(event);
    }

    /**
     * @param columnCount
     * @see javax.swing.table.DefaultTableModel#setColumnCount(int)
     */
    public void setColumnCount(int columnCount) {
	model.setColumnCount(columnCount);
    }

    /**
     * @param newIdentifiers
     * @see javax.swing.table.DefaultTableModel#setColumnIdentifiers(java.lang.Object[])
     */
    public void setColumnIdentifiers(Object[] newIdentifiers) {
	model.setColumnIdentifiers(newIdentifiers);
    }

    /**
     * @param columnIdentifiers
     * @see javax.swing.table.DefaultTableModel#setColumnIdentifiers(java.util.Vector)
     */
    public void setColumnIdentifiers(Vector<?> columnIdentifiers) {
	model.setColumnIdentifiers(columnIdentifiers);
    }

    /**
     * @param dataVector
     * @param columnIdentifiers
     * @see javax.swing.table.DefaultTableModel#setDataVector(java.lang.Object[][], java.lang.Object[])
     */
    public void setDataVector(T[][] dataVector, Object[] columnIdentifiers) {
	model.setDataVector(dataVector, columnIdentifiers);
    }

    /**
     * @param dataVector
     * @param columnIdentifiers
     * @see javax.swing.table.DefaultTableModel#setDataVector(java.util.Vector, java.util.Vector)
     */
    public void setDataVector(Vector<T> dataVector, Vector<?> columnIdentifiers) {
	model.setDataVector(dataVector, columnIdentifiers);
    }

    /**
     * @param rowCount
     * @see javax.swing.table.DefaultTableModel#setNumRows(int)
     */
    public void setNumRows(int rowCount) {
	model.setNumRows(rowCount);
    }

    /**
     * @param rowCount
     * @see javax.swing.table.DefaultTableModel#setRowCount(int)
     */
    public void setRowCount(int rowCount) {
	model.setRowCount(rowCount);
    }

    /**
     * @param aValue
     * @param row
     * @param column
     * @see javax.swing.table.DefaultTableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    public void setValueAt(Object aValue, int row, int column) {
	// TODO check if need override this method
	model.setValueAt(aValue, row, column);
    }

    // ****************************************************
    // * Other methods goes down here
    // ****************************************************

}
