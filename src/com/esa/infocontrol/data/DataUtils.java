package com.esa.infocontrol.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataUtils {
	
	static Logger LOG = LoggerFactory.getLogger(DataUtils.class);
	
	public static void printDataWrapper(DataArrayWrapper wrapper, boolean printMetaData){
		if(printMetaData){
			printMetaDataArray(wrapper.getColumnMetaData());
		}
		List<Object[]> data = wrapper.getDataArray();
		LOG.debug("ROWS: {}", data.size());
		for(Object[] row : data){
			LOG.debug(Arrays.toString(row));
		}
	}
	
	public static void printDataWrapper(DataMapWrapper wrapper, boolean printMetaData){
		if(printMetaData){
			printMetaDataMap(wrapper.getColumnMetaData());
		}
		Map<String, Object> data = wrapper.getDataMap();
		for(Entry<String, Object> item : data.entrySet()){
			LOG.debug(item.getKey() + " -> " + item.getValue());
		}
	}
	
	public static void printMetaDataMap(Map<String, ColumnMetaData> metaData){
		for(ColumnMetaData column: metaData.values()){
			LOG.debug(column.toString());
		}
	}
	
	public static void printMetaDataArray(ColumnMetaData[] metaData){
		for(ColumnMetaData column: metaData){
			LOG.debug(column.toString());
		}
	}
	

}
