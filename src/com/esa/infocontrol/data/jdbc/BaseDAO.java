package com.esa.infocontrol.data.jdbc;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.jsf.FacesContextUtils;

import com.esa.infocontrol.data.DataArrayWrapper;
import com.esa.infocontrol.data.DataMapWrapper;

public class BaseDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseDAO.class);
	
	static final Map<String, String> queries = new HashMap<String, String>();
	static final String dataSourceName =  "baseData";
	static DataSource dataSource = null;
	
	static
	{
		ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		dataSource = ctx.getBean(dataSourceName, DataSource.class);
		queries.put("qry_user_by_username", "select * from users where username = :username");
		queries.put("qry_users", "select name, username, enabled from users");
		queries.put("qry_menu", "select * from menu where enabled =1 and authority in (:authorities) order by parent_id, seq asc");
		queries.put("qry_queries", "SELECT query FROM queries where name = :name");
	}
	
	
	public static DataMapWrapper getMapNamed(String queryName, MapSqlParameterSource params){
		String query = getQuery(queryName);
		return BaseDataJDBC.getMapByNames(dataSource, query, params);
	}

	public static DataArrayWrapper getArrayNamed(String queryName, MapSqlParameterSource params){
		String query = getQuery(queryName);
		return BaseDataJDBC.getArrayByNames(dataSource, query, params);
	}
	
	private static String getQuery(String queryName) {
		if(queryName.charAt(0) == '$'){
			queryName = queryName.substring(1);
			LOG.debug("Getting query named: {}", queryName);
			MapSqlParameterSource params = new MapSqlParameterSource("name",queryName);
			DataArrayWrapper data = BaseDAO.getArrayNamed("qry_queries", params);
			StringBuilder sb = new StringBuilder();
			for(Object[] objArray: data.getDataArray()){
				sb.append(objArray[0]);
				sb.append(" ");
			}
			return sb.toString();
		}else{
			return queries.get(queryName);
		}
	}
}
