package tomPack.swing.table;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * {@link TomTable} that works only with {@link DefaultTableModel}, and have
 * the delegations to {@link DefaultTableModel}, {@link TableColumnModel} and
 * {@link ListSelectionModel}.
 * 
 * @version 2009/11/13
 * @author Tom Brito
 */
public class TomStringTable extends TomTable {

    private static final long serialVersionUID = 1793819982167790132L;

    // TODO better use DefaultPoweredTableModel?
    private DefaultTableModel model;

    private TableColumnModel columnModel;

    private ListSelectionModel selectionModel;

    // ****************************************************
    // * DefaultTableModel delegation
    // ****************************************************

    public void addColumn(Object columnName, Object[] columnData) {
	model.addColumn(columnName, columnData);
    }

    public void addColumn(Object columnName, Vector columnData) {
	model.addColumn(columnName, columnData);
    }

    public void addColumn(Object columnName) {
	model.addColumn(columnName);
    }

    public void addRow(Object[] rowData) {
	model.addRow(rowData);
    }

    public void addRow(Vector rowData) {
	model.addRow(rowData);
    }

    public void addTableModelListener(TableModelListener l) {
	model.addTableModelListener(l);
    }

    public boolean equals(Object obj) {
	return model.equals(obj);
    }

    @Override
    public int hashCode() {
	return model.hashCode();
    }

    public int findColumn(String columnName) {
	return model.findColumn(columnName);
    }

    public void fireTableCellUpdated(int row, int column) {
	model.fireTableCellUpdated(convertRowIndexToModel(row), convertColumnIndexToModel(column));
    }

    public void fireTableChanged(TableModelEvent e) {
	model.fireTableChanged(e);
    }

    public void fireTableDataChanged() {
	model.fireTableDataChanged();
    }

    public void fireTableStructureChanged() {
	model.fireTableStructureChanged();
    }

    public Vector getDataVector() {
	return model.getDataVector();
    }

    public TableModelListener[] getTableModelListeners() {
	return model.getTableModelListeners();
    }

    // TODO convertRowIndexToModel ?
    // public void insertRow(int row, Object[] rowData) {
    // model.insertRow(convertRowIndexToModel(row), rowData);
    // }

    // TODO convertRowIndexToModel ?
    // public void insertRow(int row, Vector rowData) {
    // model.insertRow(row, rowData);
    // }

    // TODO convertRowIndexToModel ?
    // public void moveRow(int start, int end, int to) {
    // model.moveRow(start, end, to);
    // }

    public void newDataAvailable(TableModelEvent event) {
	model.newDataAvailable(event);
    }

    public void newRowsAdded(TableModelEvent e) {
	model.newRowsAdded(e);
    }

    public void removeRow(int row) {
	model.removeRow(convertRowIndexToModel(row));
    }

    public void removeTableModelListener(TableModelListener l) {
	model.removeTableModelListener(l);
    }

    public void rowsRemoved(TableModelEvent event) {
	model.rowsRemoved(event);
    }

    public void setColumnCount(int columnCount) {
	model.setColumnCount(columnCount);
    }

    public void setColumnIdentifiers(Object[] newIdentifiers) {
	model.setColumnIdentifiers(newIdentifiers);
    }

    public void setColumnIdentifiers(Vector columnIdentifiers) {
	model.setColumnIdentifiers(columnIdentifiers);
    }

    public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers) {
	model.setDataVector(dataVector, columnIdentifiers);
    }

    public void setDataVector(Vector dataVector, Vector columnIdentifiers) {
	model.setDataVector(dataVector, columnIdentifiers);
    }

    /** Delegate to {@link DefaultTableModel#setNumRows(int)} */
    public void setNumRows(int rowCount) {
	model.setNumRows(rowCount);
    }

    public void setRowCount(int rowCount) {
	model.setRowCount(rowCount);
    }

    // ****************************************************
    // * Column model delegated
    // ****************************************************

    public void addColumnModelListener(TableColumnModelListener x) {
	columnModel.addColumnModelListener(x);
    }

    public TableColumn getColumn(int columnIndex) {
	return columnModel.getColumn(columnIndex);
    }

    public int getColumnIndex(Object columnIdentifier) {
	return columnModel.getColumnIndex(columnIdentifier);
    }

    public int getColumnIndexAtX(int position) {
	return columnModel.getColumnIndexAtX(position);
    }

    public int getColumnMargin() {
	return columnModel.getColumnMargin();
    }

    public Enumeration<TableColumn> getColumns() {
	return columnModel.getColumns();
    }

    public int getTotalColumnWidth() {
	return columnModel.getTotalColumnWidth();
    }

    public void removeColumnModelListener(TableColumnModelListener x) {
	columnModel.removeColumnModelListener(x);
    }

    public void setColumnMargin(int newMargin) {
	columnModel.setColumnMargin(newMargin);
    }

    // ****************************************************
    // * Selection model delegated
    // ****************************************************

    public void addListSelectionListener(ListSelectionListener x) {
	selectionModel.addListSelectionListener(x);
    }

    public void addSelectionInterval(int index0, int index1) {
	selectionModel.addSelectionInterval(index0, index1);
    }

    public int getAnchorSelectionIndex() {
	return selectionModel.getAnchorSelectionIndex();
    }

    public int getLeadSelectionIndex() {
	return selectionModel.getLeadSelectionIndex();
    }

    public int getMaxSelectionIndex() {
	return selectionModel.getMaxSelectionIndex();
    }

    public int getMinSelectionIndex() {
	return selectionModel.getMinSelectionIndex();
    }

    public int getSelectionMode() {
	return selectionModel.getSelectionMode();
    }

    public boolean getValueIsAdjusting() {
	return selectionModel.getValueIsAdjusting();
    }

    public void insertIndexInterval(int index, int length, boolean before) {
	selectionModel.insertIndexInterval(index, length, before);
    }

    public boolean isSelectedIndex(int index) {
	return selectionModel.isSelectedIndex(index);
    }

    public boolean isSelectionEmpty() {
	return selectionModel.isSelectionEmpty();
    }

    public void removeIndexInterval(int index0, int index1) {
	selectionModel.removeIndexInterval(index0, index1);
    }

    public void removeListSelectionListener(ListSelectionListener x) {
	selectionModel.removeListSelectionListener(x);
    }

    public void removeSelectionInterval(int index0, int index1) {
	selectionModel.removeSelectionInterval(index0, index1);
    }

    public void setAnchorSelectionIndex(int index) {
	selectionModel.setAnchorSelectionIndex(index);
    }

    public void setLeadSelectionIndex(int index) {
	selectionModel.setLeadSelectionIndex(index);
    }

    public void setSelectionInterval(int index0, int index1) {
	selectionModel.setSelectionInterval(index0, index1);
    }

    public void setValueIsAdjusting(boolean valueIsAdjusting) {
	selectionModel.setValueIsAdjusting(valueIsAdjusting);
    }

    // ****************************************************
    // * Other methods
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
    public void setColumnModel(TableColumnModel columnModel) {
	this.columnModel = columnModel;
	super.setColumnModel(columnModel);
    }

    @Override
    public void setSelectionModel(ListSelectionModel newModel) {
	this.selectionModel = newModel;
	super.setSelectionModel(newModel);
    }

}
