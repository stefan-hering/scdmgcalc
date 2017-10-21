package gui;

import javax.swing.table.AbstractTableModel;

public class ScTossTableModel extends AbstractTableModel {

	private Object[][][] data;
	private int shield;
	
	public ScTossTableModel(int[][][] rawData, int shield){
		this.shield = shield;
		data = new Object[5][5][4];
		data[0][0][shield] = "Att\\Def-up";
		for(int i = 0; i <= 3; i++){
			data[0][i+1][shield] = i;
			data[i+1][0][shield] = i;
		}
		for(int i = 1; i <= 4;i++)
			for(int j=1; j<=4;j++)
				data[i][j][shield] = rawData[i-1][j-1][shield];
		
	}
	
	public ScTossTableModel(float[][][] rawData, int shield) {
		this.shield = shield;
		data = new Object[5][5][4];
		data[0][0][shield] = "Att\\Def-up";
		for(int i = 0; i <= 3; i++){
			data[0][i+1][shield] = i;
			data[i+1][0][shield] = i;
		}
		for(int i = 1; i <= 4;i++)
			for(int j=1; j<=4;j++)
				data[i][j][shield] = rawData[i-1][j-1][shield];
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
		return data[rowIndex][columnIndex][shield];
	}

}
