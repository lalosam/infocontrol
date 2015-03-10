package com.esa.infocontrol.mbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.esa.infocontrol.data.DataArrayWrapper;
import com.esa.infocontrol.data.DataMapWrapper;
import com.esa.infocontrol.data.DataUtils;
import com.esa.infocontrol.data.jdbc.BaseDAO;
import com.esa.infocontrol.security.SecurityUtils;

@ManagedBean
@SessionScoped
public class HeaderMB {

	Logger LOG = LoggerFactory.getLogger(HeaderMB.class);
	
	private String name;
	private String department;
	private String username;
	
	public HeaderMB() {
		LOG.debug("Creating a HeaderMB bean");
		username = SecurityUtils.getUserDetail().getUsername();
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		DataMapWrapper data = BaseDAO.getMapNamed("qry_user_by_username", params);
		name = (String)data.getDataMap().get("name");
		MapSqlParameterSource params1 = new MapSqlParameterSource("username", username);
		DataArrayWrapper data1 = BaseDAO.getArrayNamed("$menu_items", params1);
		DataUtils.printDataWrapper(data1, true);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUsername() {
		return username;
	}

}
