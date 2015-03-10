package com.esa.infocontrol.menu;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuItem {
	
	private Logger LOG = LoggerFactory.getLogger(MenuItem.class);

	private List<MenuItem> children = new ArrayList<MenuItem>();
	private int id;
	private int parent;
	private int seq;
	private String label;
	private String type;
	private String action;
	
	public MenuItem(){
		super();
	}
	
	public MenuItem(Object[] data) {
		super();
		id = Integer.parseInt(data[0].toString());
		parent = Integer.parseInt(data[1].toString());
		seq = Integer.parseInt(data[2].toString());
		label = data[3].toString();
		type = data[6].toString();
		action = (String)data[7];
	}

	public void addChild(MenuItem item) {
		children.add(item);
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public MenuElement getElement() {
		LOG.debug("Processing MenuItem \"{}\" as \"{}\"", getLabel(), getType());
		if(this.getType().equals("menu")){
			DefaultSubMenu element = new DefaultSubMenu(label);
			for(MenuItem child: children){
				LOG.debug("Adding child \"{}\"", child.getLabel());
				element.addElement(child.getElement());
			}
			return element;
		}else{
			DefaultMenuItem element = new DefaultMenuItem(label);
			element.setAjax(true);
			element.setPartialSubmit(true);
			element.setCommand("#{leftMenuMB.command}");
			element.setParam("type", this.getType());
			element.setParam("action", this.getAction());
			element.setUpdate(":globalForm:messages,:moduleContent");
			element.setIcon("ui-icon-home");
			return element;
		}
	}

	
	
}
	