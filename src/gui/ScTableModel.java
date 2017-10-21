package gui;

import javax.swing.table.AbstractTableModel;

public class ScTableModel extends AbstractTableModel {

	private Object[][] data;
	
	public ScTableModel(int[][] rawData){
		data = new Object[5][5];
		data[0][0] = "Att\\Def-up";
		for(int i = 0; i <= 3; i++){
			data[0][i+1] = i;
			data[i+1][0] = i;
		}
		for(int i = 1; i <= 4;i++)
			for(int j=1; j<=4;j++)
				data[i][j] = rawData[i-1][j-1];
		
	}
	
	public ScTableModel(float[][] rawData) {
		data = new Object[5][5];
		data[0][0] = "Att\\Def-up";
		for(int i = 0; i <= 3; i++){
			data[0][i+1] = i;
			data[i+1][0] = i;
		}
		for(int i = 1; i <= 4;i++)
			for(int j=1; j<=4;j++)
				data[i][j] = rawData[i-1][j-1];
	}

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
		return data[rowIndex][columnIndex];
	}

}
