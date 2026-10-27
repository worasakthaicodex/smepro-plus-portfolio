package com.smepro.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class rolesecurity {
	
	
	
	public String Check_authorities(String authorities) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(authorities))) {
			return "true";
	    }
		return "false";
		
	}
	
	
	public String Check_nav() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPPEROWNER"))) {
			return "Bar.jspf";
	    }else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPPERADMIN"))) {
	    	return "Bar.jspf";
	    }else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_OWNER"))) {
	    	return "Barvender.jspf";
	    }else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
	    	return "Barvender.jspf";
	    }else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
	    	return "Baruser.jspf";
	    }
		
		
		
		return null;
		
	}
	
}
