package com.smepro.validation;
import org.springframework.stereotype.Service;

@Service
public class mainvalidation {

	public boolean checkNameLength(String name) {
		
		if(name.length() > 5) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean isAlphaNumeric(String s){
	    String pattern= "^[a-zA-Z0-9]*$";
	    return s.matches(pattern);
	}	
	
	
}
