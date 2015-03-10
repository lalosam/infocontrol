package com.esa.infocontrol.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean(name="statusMB")
@SessionScoped
public class StatusMB {
	
	Logger LOG = LoggerFactory.getLogger(StatusMB.class);
			
	String currentModule;
	String urlModule = "initial.xhtml";
	String action;
	
	public String getCurrentModule() {
		return currentModule;
	}
	public void setCurrentModule(String currentModule) {
		this.currentModule = currentModule;
	}
	public String getUrlModule() {
		return urlModule;
	}
	public void setUrlModule(String urlModule) {
		this.urlModule = urlModule;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String logout(){
		LOG.debug("LOGOUT requested");
		return "success";
	}
	
}
