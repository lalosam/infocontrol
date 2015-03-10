package com.esa.infocontrol.data;

import java.util.Map;

public class DataMapWrapper {
	
	Map<String, Object> dataMap = null;
	Map<String, ColumnMetaData> columnMetaData = null;
	
	public DataMapWrapper(Map<String, Object> dataMap, Map<String, ColumnMetaData> columnMetaData) {
		this.dataMap = dataMap;
		this.columnMetaData = columnMetaData;
	}

	

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}



	public Map<String, ColumnMetaData> getColumnMetaData() {
		return columnMetaData;
	}

	public void setColumnMetaData(Map<String, ColumnMetaData> columnMetaData) {
		this.columnMetaData = columnMetaData;
	}

	
	
}
