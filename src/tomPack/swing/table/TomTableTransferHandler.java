package tomPack.swing.table;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import tomPack.swing.TomTransferHandler;

/**
 * Implementation of {@link TomTransferHandler} for {@link JTable}.
 */
public class TomTableTransferHandler extends TomTransferHandler {

	private static final long serialVersionUID = 8825883306831727325L;

	/** List of cells [row, column]. */
	protected List<Point> cellMatrix = new ArrayList<Point>();

	public TomTableTransferHandler() {
		super(DataFlavor.stringFlavor);
	}

	@Override
	protected Transferable createTransferable(JComponent comp) {
		JTable table = (JTable) comp;

		// get rows and columns
		int[] rows = table.getSelectedRows();
		int[] columns = table.getSelectedColumns();
		cellMatrix.clear();
		for (int r : rows) {
			for (int c : columns) {
				cellMatrix.add(new Point(r, c));
			}
		}

		StringBuilder buff = new StringBuilder();

		for (int r : rows) {
			for (int c : columns) {
				Object val = table.getValueAt(r, c);
				buff.append(val == null ? "" : val.toString()); //$NON-NLS-1$
				buff.append("</col>"); // columns separator char //$NON-NLS-1$
			}
			buff.append("\n"); // rows separator char //$NON-NLS-1$
		}

		return new StringSelection(buff.toString());
	}

	@Override
	public boolean importData(TransferSupport info) {
		if (!info.isDrop()) {
			return false;
		}

		JTable table = (JTable) info.getComponent();
		TableModel tableModel = table.getModel();
		JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
		int firstTargetRow = dl.getRow();
		int firstTargetColumn = dl.getColumn();

		// Get the data that is being dropped.
		Transferable t = info.getTransferable();
		String data;
		try {
			data = (String) t.getTransferData(flavor);
		} catch (Exception e) {
			return false;
		}

		// Wherever there is a newline in the incoming data,
		// break it into separated rows.
		String[] rowsValues = data.split("\n"); //$NON-NLS-1$

		// Import rows
		for (int i = 0; i < rowsValues.length; i++) {

			// Wherever there is a "|" in the incoming data,
			// break it into separated columns.
			String[] columnsValues = rowsValues[i].split("</col>"); //$NON-NLS-1$

			// Import columns
			for (int j = 0; j < columnsValues.length; j++) {
				String value = columnsValues[j];
				if (value == null)
					value = ""; //$NON-NLS-1$
				int targetRow = firstTargetRow + i;
				int targetColumn = firstTargetColumn + j;
				tableModel.setValueAt(value, targetRow, targetColumn);

				// If is moving from this table to this table, avoid erase data
				// after at exportDone method
				cellMatrix.remove(new Point(targetRow, targetColumn));
			}
		}
		return true;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		if (action != MOVE) {
			return;
		}
		JTable table = (JTable) source;
		for (Point p : cellMatrix) {
			table.setValueAt("", p.x, p.y); //$NON-NLS-1$
		}
	}

}
