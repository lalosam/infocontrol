package com.esa.infocontrol.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
	
	public static UserDetails getUserDetail(){
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return (UserDetails) principal;
	}
	
	public static List<String> getAuthorities(){
		UserDetails ud = getUserDetail();
		Collection<? extends GrantedAuthority> authorities = ud.getAuthorities();
		List<String> result = new ArrayList<String>(); 
		for(GrantedAuthority auth: authorities){
			result.add(auth.getAuthority());
		}
		return result;
	}
	
	

}
