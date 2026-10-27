package com.smepro.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenStatus {
	
    private String name;
    private String doc;
    private Map<Integer,String> statusMap = new HashMap<>();


    public void setStatus(int step, String value) {
        statusMap.put(step, value);
    }
    public String getStatus(int step) {
        return statusMap.get(step);
    }
	public Map<Integer, String> getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(Map<Integer, String> statusMap) {
		this.statusMap = statusMap;
	}
	public String getName() {
		return name;
	}
    public void setName(String name) {
    	this.name = name; 
    	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	   
    
    
}
