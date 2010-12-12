package tomPack.swing.table;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * This class delegates the methods from {@link TableModel},
 * {@link TableColumnModel} and {@link ListSelectionModel} to the respective
 * objects.<br>
 * If the method is already defined in {@link JTable}, it is not overriden.
 * 
 * @author Tom Brito
 */
public class DelegatorTable extends JTable {

	// this guys cannot be protected due to super.set() call needed
	private TableModel model;
	private TableColumnModel columnModel;
	private ListSelectionModel selectionModel;

	//
	// Constructors
	//

	public DelegatorTable(TableModel model) {
		super(model);
	}

	public DelegatorTable(int numRows, int numColumns) {
		super(numRows, numColumns);
	}

	public DelegatorTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}

	public DelegatorTable(TableModel model, TableColumnModel columnModel) {
		super(model, columnModel);
	}

	public DelegatorTable(Vector<?> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
	}

	public DelegatorTable(TableModel model, TableColumnModel columnModel,
			ListSelectionModel selectionModel) {
		super(model, columnModel, selectionModel);
	}

	//
	// Setter overrides (needed for correct delegation)
	//

	@Override
	public void setModel(TableModel dataModel) {
		this.model = dataModel;
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

	//
	// TableModel delegations
	//

	/**
	 * @param l
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener l) {
		model.addTableModelListener(l);
	}

	/**
	 * @param l
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener l) {
		model.removeTableModelListener(l);
	}

	//
	// ColumnModel delegations
	//

	/**
	 * @param x
	 * @see javax.swing.table.TableColumnModel#addColumnModelListener(javax.swing.event.TableColumnModelListener)
	 */
	public void addColumnModelListener(TableColumnModelListener x) {
		columnModel.addColumnModelListener(x);
	}

	/**
	 * @param columnIndex
	 * @return
	 * @see javax.swing.table.TableColumnModel#getColumn(int)
	 */
	public TableColumn getColumn(int columnIndex) {
		return columnModel.getColumn(columnIndex);
	}

	/**
	 * @param columnIdentifier
	 * @return
	 * @see javax.swing.table.TableColumnModel#getColumnIndex(java.lang.Object)
	 */
	public int getColumnIndex(Object columnIdentifier) {
		return columnModel.getColumnIndex(columnIdentifier);
	}

	/**
	 * @param xPosition
	 * @return
	 * @see javax.swing.table.TableColumnModel#getColumnIndexAtX(int)
	 */
	public int getColumnIndexAtX(int xPosition) {
		return columnModel.getColumnIndexAtX(xPosition);
	}

	/**
	 * @return
	 * @see javax.swing.table.TableColumnModel#getColumnMargin()
	 */
	public int getColumnMargin() {
		return columnModel.getColumnMargin();
	}

	/**
	 * @return
	 * @see javax.swing.table.TableColumnModel#getColumns()
	 */
	public Enumeration<TableColumn> getColumns() {
		return columnModel.getColumns();
	}

	/**
	 * @return
	 * @see javax.swing.table.TableColumnModel#getTotalColumnWidth()
	 */
	public int getTotalColumnWidth() {
		return columnModel.getTotalColumnWidth();
	}

	/**
	 * @param x
	 * @see javax.swing.table.TableColumnModel#removeColumnModelListener(javax.swing.event.TableColumnModelListener)
	 */
	public void removeColumnModelListener(TableColumnModelListener x) {
		columnModel.removeColumnModelListener(x);
	}

	/**
	 * @param newMargin
	 * @see javax.swing.table.TableColumnModel#setColumnMargin(int)
	 */
	public void setColumnMargin(int newMargin) {
		columnModel.setColumnMargin(newMargin);
	}

	//
	// SelectionModel delegations
	//

	/**
	 * @param x
	 * @see javax.swing.ListSelectionModel#addListSelectionListener(javax.swing.event.ListSelectionListener)
	 */
	public void addListSelectionListener(ListSelectionListener x) {
		selectionModel.addListSelectionListener(x);
	}

	/**
	 * @param index0
	 * @param index1
	 * @see javax.swing.ListSelectionModel#addSelectionInterval(int, int)
	 */
	public void addSelectionInterval(int index0, int index1) {
		selectionModel.addSelectionInterval(index0, index1);
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#getAnchorSelectionIndex()
	 */
	public int getAnchorSelectionIndex() {
		return selectionModel.getAnchorSelectionIndex();
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#getLeadSelectionIndex()
	 */
	public int getLeadSelectionIndex() {
		return selectionModel.getLeadSelectionIndex();
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#getMaxSelectionIndex()
	 */
	public int getMaxSelectionIndex() {
		return selectionModel.getMaxSelectionIndex();
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#getMinSelectionIndex()
	 */
	public int getMinSelectionIndex() {
		return selectionModel.getMinSelectionIndex();
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#getSelectionMode()
	 */
	public int getSelectionMode() {
		return selectionModel.getSelectionMode();
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#getValueIsAdjusting()
	 */
	public boolean getValueIsAdjusting() {
		return selectionModel.getValueIsAdjusting();
	}

	/**
	 * @param index
	 * @param length
	 * @param before
	 * @see javax.swing.ListSelectionModel#insertIndexInterval(int, int,
	 *      boolean)
	 */
	public void insertIndexInterval(int index, int length, boolean before) {
		selectionModel.insertIndexInterval(index, length, before);
	}

	/**
	 * @param index
	 * @return
	 * @see javax.swing.ListSelectionModel#isSelectedIndex(int)
	 */
	public boolean isSelectedIndex(int index) {
		return selectionModel.isSelectedIndex(index);
	}

	/**
	 * @return
	 * @see javax.swing.ListSelectionModel#isSelectionEmpty()
	 */
	public boolean isSelectionEmpty() {
		return selectionModel.isSelectionEmpty();
	}

	/**
	 * @param index0
	 * @param index1
	 * @see javax.swing.ListSelectionModel#removeIndexInterval(int, int)
	 */
	public void removeIndexInterval(int index0, int index1) {
		selectionModel.removeIndexInterval(index0, index1);
	}

	/**
	 * @param x
	 * @see javax.swing.ListSelectionModel#removeListSelectionListener(javax.swing.event.ListSelectionListener)
	 */
	public void removeListSelectionListener(ListSelectionListener x) {
		selectionModel.removeListSelectionListener(x);
	}

	/**
	 * @param index0
	 * @param index1
	 * @see javax.swing.ListSelectionModel#removeSelectionInterval(int, int)
	 */
	public void removeSelectionInterval(int index0, int index1) {
		selectionModel.removeSelectionInterval(index0, index1);
	}

	/**
	 * @param index
	 * @see javax.swing.ListSelectionModel#setAnchorSelectionIndex(int)
	 */
	public void setAnchorSelectionIndex(int index) {
		selectionModel.setAnchorSelectionIndex(index);
	}

	/**
	 * @param index
	 * @see javax.swing.ListSelectionModel#setLeadSelectionIndex(int)
	 */
	public void setLeadSelectionIndex(int index) {
		selectionModel.setLeadSelectionIndex(index);
	}

	/**
	 * @param index0
	 * @param index1
	 * @see javax.swing.ListSelectionModel#setSelectionInterval(int, int)
	 */
	public void setSelectionInterval(int index0, int index1) {
		selectionModel.setSelectionInterval(index0, index1);
	}

	/**
	 * @param valueIsAdjusting
	 * @see javax.swing.ListSelectionModel#setValueIsAdjusting(boolean)
	 */
	public void setValueIsAdjusting(boolean valueIsAdjusting) {
		selectionModel.setValueIsAdjusting(valueIsAdjusting);
	}

}
