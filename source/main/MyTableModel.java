package ims.main;

import javax.swing.table.*;

public class MyTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
	private Object[][] data;
	
	//Constructor as in Table class
	public MyTableModel(Object[][] data, String[] columnNames)
	{
		this.columnNames = columnNames;
		this.data = data;
	}
	
	//Default Constructor
	public MyTableModel()
	{
		super();
	}
	
	
	//Implementation of getColumnCount()
	public int getColumnCount()
	{
		return columnNames.length;
	}
	
	
	//Implementation of getRowCount()
	public int getRowCount()
	{
		return data.length;
	}
	
	
	//Implementation of getValueAt(int, int)
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}
	
	
	//Implementation of isCellEditable(int, int)
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
	
	
	//Implementation of getColumnName(int)
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
}
