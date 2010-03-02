package tomPack.swing.table;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import tomPack.Debug;
import tomPack.Row;
import tomPack.TomEntity;
import tomPack.TomUtils;

/**
 * Table Model to handle Strings.
 * <p>
 * The advantages of using this class instead of {@link DefaultTableModel} are:
 * <li>This class extends {@link TomTableModel}.
 * <li>This class implements {@link TomEntity}.
 * 
 * @see TomTableTransferHandler
 * 
 * @version 2010/03/02
 * @author Tom Brito
 */
public class DefaultTomTableModel extends TomTableModel implements TomEntity {

    private static final long serialVersionUID = 1L;

    private List<Row> rows = null;

    private int columnsSize;

    //
    // Initialization
    //

    /**
     * Construtor default para serialização. Construtor com acesso público,
     * porém em classe com acesso default (pacote).
     */
    public DefaultTomTableModel(int initialColumnCount) {
	this.columnsSize = initialColumnCount;
	rows = new ArrayList<Row>();
	addRow();
    }

    //
    // WEntity
    //

    public void save(ObjectOutput out) throws IOException {
	out.writeObject(rows.size());
	out.writeObject(columnsSize);
	for (int r = 0; r < rows.size(); r++) {
	    Row row = rows.get(r);
	    for (int c = 0; c < columnsSize; c++) {
		String cell = row.get(c);
		out.writeObject(cell);
	    }
	}
    }

    public void load(ObjectInput in) throws IOException, ClassNotFoundException {
	rows.clear();
	Integer rowsSize = (Integer) in.readObject();
	columnsSize = (Integer) in.readObject();
	for (int r = 0; r < rowsSize; r++) {
	    Row row = new Row();
	    for (int c = 0; c < columnsSize; c++) {
		row.add((String) in.readObject());
	    }
	    rows.add(row);
	}
    }

    public boolean sameValues(TomEntity obj) {

	if (!(obj instanceof DefaultTomTableModel)) {
	    Debug.equalsFail(DefaultTomTableModel.class, obj.getClass());
	    return false;
	}

	DefaultTomTableModel model = (DefaultTomTableModel) obj;

	if (rows.size() != model.rows.size()) {
	    Debug.equalsFail(rows.size(), model.rows.size());
	    return false;
	}

	for (int i = 0; i < rows.size(); i++) {
	    Row linha1 = rows.get(i);
	    Row linha2 = model.rows.get(i);
	    if (linha1.size() != linha2.size()) {
		Debug.equalsFail(linha1.size(), linha2.size());
		return false;
	    }
	    for (int j = 0; j < linha1.size(); j++) {
		if (!TomUtils.equals(linha1.get(j), linha2.get(j))) {
		    Debug.equalsFail(linha1.get(j), linha2.get(j));
		    return false;
		}
	    }
	}

	return true;
    }

    //
    // Other methods
    //

    protected List<Row> getRows() {
	return rows;
    }

    public boolean isCellEditable(int row, int col) {
	return true;
    }

    /**
     * Retorna o numero de colunas no modelo
     * 
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
	return columnsSize;
    }

    /**
     * Retorna o numero de linhas existentes no modelo
     * 
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
	return rows.size();
    }

    /**
     * Obtem o valor na linha e coluna
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public String getValueAt(int rowIndex, int columnIndex) {
	return rows.get(rowIndex).get(columnIndex);
    }

    /**
     * Seta o valor na linha e coluna
     * 
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object value, int row, int col) {
	rows.get(row).set(col, value.toString());
	fireTableCellUpdated(row, col);
    }

    /**
     * Adiciona uma linha com dados completa. Para adicionar dados a uma célula
     * utilize o método setValueAt;
     */
    public synchronized void addRow() {
	addRow(rows.size());
    }

    public synchronized void addRow(int index) {
	if (index == rows.size() - 1) {
	    addRow();
	    return;
	}
	Row row = new Row();
	for (int col = 0; col < getColumnCount(); col++) {
	    row.add(""); //$NON-NLS-1$
	}
	if (index == rows.size()) {
	    rows.add(row);
	} else {
	    rows.add(index, row);
	}
	fireTableDataChanged();
    }

    public void removeRow(int row) {
	rows.remove(row);
	fireTableDataChanged();
    }

    public void addColumn() {
	addColumn(columnsSize);
    }

    public void addColumn(int index) {
	for (Row row : rows) {
	    if (index == columnsSize) {
		row.add(""); //$NON-NLS-1$
	    } else {
		row.add(index, ""); //$NON-NLS-1$
	    }
	}
	columnsSize++;
	fireTableStructureChanged();
    }

    public void removeColumn(int index) {
	for (Row row : rows) {
	    row.remove(index);
	}
	columnsSize--;
	fireTableStructureChanged();
    }

}
