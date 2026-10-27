package com.smepro.service;

import org.springframework.stereotype.Service;

@Service
public class webmeterservice {
           
	public String checktype(String type) {
		
		if(type.equals("tree-webmeter")) {
			
			return "webmeterTree";
		
		}else if(type.equals("webmeter")) {
			
			return "webmeterall";
		}
		
		
		
		else {
			return "webmeterfire";
		}
		
	}
	
	
	
	
	
}
