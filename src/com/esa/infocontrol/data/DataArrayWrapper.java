package com.esa.infocontrol.data;

import java.util.List;

public class DataArrayWrapper {
	
	List<Object[]> dataArray = null;
	ColumnMetaData[] columnMetaData = null;
	
	public DataArrayWrapper(List<Object[]> dataArray, ColumnMetaData[] columnMetaData) {
		super();
		this.dataArray = dataArray;
		this.columnMetaData = columnMetaData;
	}
	
	public List<Object[]> getDataArray() {
		return dataArray;
	}
	public void setDataArray(List<Object[]> dataArray) {
		this.dataArray = dataArray;
	}

	public ColumnMetaData[] getColumnMetaData() {
		return columnMetaData;
	}

	public void setColumnMetaData(ColumnMetaData[] columnMetaData) {
		this.columnMetaData = columnMetaData;
	}
	
	
	
	

}
