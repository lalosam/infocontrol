package com.esa.infocontrol.mbean;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.esa.infocontrol.data.DataArrayWrapper;
import com.esa.infocontrol.data.DataUtils;
import com.esa.infocontrol.data.jdbc.BaseDAO;
import com.esa.infocontrol.menu.MenuItem;
import com.esa.infocontrol.menu.MenuUtils;
import com.esa.infocontrol.security.SecurityUtils;



@ManagedBean
@SessionScoped
public class LeftMenuMB {
	
	Logger LOG = LoggerFactory.getLogger(LeftMenuMB.class);
     
    private MenuModel model;
    
    @ManagedProperty("#{statusMB}")
    private StatusMB statusMB;
 
    @PostConstruct
    public void init() {
    	LOG.debug("Creating a HeaderMB bean");
        model = new DefaultMenuModel();
        List<String> authorities = SecurityUtils.getAuthorities();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorities", authorities);
        DataArrayWrapper data = BaseDAO.getArrayNamed("qry_menu", params);
        DataUtils.printDataWrapper(data, true);
        List<MenuItem> parentMenus = MenuUtils.createTreeMenu(data.getDataArray());
        for(MenuItem menu :  parentMenus ){
        	model.addElement(menu.getElement());
        }
    }
    
    public void setStatusMB(StatusMB statusMB){
    	this.statusMB = statusMB;
    }
 
    public MenuModel getModel() {
        return model;
    }   
     
    public void command() {
    	LOG.debug("MenuItem event triggered");
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        for(Entry<String, String> entry: params.entrySet()){
        	LOG.debug("{} -> {}", entry.getKey(), entry.getValue());
        }
        addMessage("Menu event triggered", "<b>TYPE:</b> " + params.get("type") + "<br/><b>ACTION:</b> "  + params.get("action"));
        statusMB.setCurrentModule(params.get("type"));
        statusMB.setAction(params.get("action"));
        statusMB.setUrlModule(params.get("type")+ ".xhtml");
    }
    
    public void save() {
        addMessage("Success", "Data saved");
    }
     
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}