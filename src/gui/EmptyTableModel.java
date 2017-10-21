package gui;

import javax.swing.table.AbstractTableModel;

public class EmptyTableModel extends AbstractTableModel {
	
	public EmptyTableModel(){}
	

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return "";
	}

}
