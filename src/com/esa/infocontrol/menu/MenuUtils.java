package com.esa.infocontrol.menu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuUtils {
	
	private static Logger LOG = LoggerFactory.getLogger(MenuUtils.class);
	
	public static List<MenuItem> createTreeMenu(List<Object[]> data){
		LOG.debug("Processing menus data. . .");
		Map<Integer, MenuItem> menuMap = new HashMap<Integer, MenuItem>(data.size());
		List<MenuItem> parentMenus = new LinkedList<MenuItem>();
		for(Object[] menuData:data){
			MenuItem item = new MenuItem(menuData);
			menuMap.put(item.getId(), item);
			if(item.getParent() == 0){
				parentMenus.add(item);
			}else{
				MenuItem parent = menuMap.get(item.getParent());
				parent.addChild(item);
			}	
		}
		return parentMenus;
	} 

}
