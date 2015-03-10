package com.esa.infocontrol.data;

public class ColumnMetaData {
	
	private int columnType;
	private String columnName;
	
	public ColumnMetaData(String columnName, int columnType) {
		this.columnType = columnType;
		this.columnName = columnName;
	}

	public int getColumnType() {
		return columnType;
	}

	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public String toString() {
		return "ColumnMetaData [columnType=" + columnType + ", columnName="
				+ columnName + "]";
	}
	
	
	
	
	

}
