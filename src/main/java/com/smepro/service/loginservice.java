package com.smepro.service;
import org.springframework.stereotype.Service;


@Service
public class loginservice {

	public boolean checkvalidateuser(String user,String password) {
		
		if(user.equals("worasak") && password.equals("12345")) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
