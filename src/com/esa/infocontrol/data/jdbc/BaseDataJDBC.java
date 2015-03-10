package com.esa.infocontrol.data.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.web.jsf.FacesContextUtils;

import com.esa.infocontrol.data.ColumnMetaData;
import com.esa.infocontrol.data.DataArrayWrapper;
import com.esa.infocontrol.data.DataMapWrapper;

public class BaseDataJDBC {
	
	private static Logger LOG = LoggerFactory.getLogger(BaseDataJDBC.class);
	
	public static DataMapWrapper getMapByNames(String dataSourceName, String query, MapSqlParameterSource params){
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		DataSource dataSource = ctx.getBean(dataSourceName, DataSource.class);
		return getMapByNames(dataSource, query, params);
	}
	
	public static DataMapWrapper getMapByNames(DataSource dataSource, String query, MapSqlParameterSource params){
		LOG.debug("QUERY: {}",query);
		if(params != null){
			LOG.debug("\tPARAMETERS: {}", params.getValues().toString());
		}		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query, params);
		SqlRowSetMetaData md = rs.getMetaData();
		LOG.debug("\tCOLUMNS: {}", Arrays.toString(md.getColumnNames()));
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, ColumnMetaData> columnMap = new HashMap<String, ColumnMetaData>();
		if(rs.next()){
			for(int i = 1; i <= md.getColumnCount(); ++i){
				columnMap.put(md.getColumnName(i),  new ColumnMetaData(md.getColumnName(i),md.getColumnType(i)));
				map.put(md.getColumnName(i), rs.getObject(i));
			}
		}
		return new DataMapWrapper(map, columnMap);
	}
	

	public static DataArrayWrapper getArrayByNames(String dataSourceName, String query, MapSqlParameterSource params){
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		DataSource dataSource = ctx.getBean(dataSourceName, DataSource.class);
		return getArrayByNames(dataSource, query, params);
	}
	
	public static DataArrayWrapper getArrayByNames(DataSource dataSource, String query, MapSqlParameterSource params){
		LOG.debug("QUERY: {}",query);
		if(params != null){
			LOG.debug("\tPARAMETERS: {}", params.getValues().toString());
		}
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query, params);
		SqlRowSetMetaData md = rs.getMetaData();
		LOG.debug("\tCOLUMNS: {}", Arrays.toString(md.getColumnNames()));
		List<Object[]> dataList = new ArrayList<Object[]>();
		ColumnMetaData[] columnMetaData = new ColumnMetaData[md.getColumnCount()];
		for(int i = 1; i <= md.getColumnCount(); ++i){
			columnMetaData[i-1] = new ColumnMetaData(md.getColumnName(i),md.getColumnType(i));
		}
		while(rs.next()){
			Object[] dataArray = new Object[md.getColumnCount()];
			for(int i = 1; i <= md.getColumnCount(); ++i){
				dataArray[i-1] = rs.getObject(i);
			}
			dataList.add(dataArray);
		}
		return new DataArrayWrapper(dataList, columnMetaData);
	}
}
