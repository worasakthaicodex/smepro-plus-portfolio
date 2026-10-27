package com.smepro.models;

import java.util.List;

public class commitment {
    private String name;
    private List<registerkey> actionkeys;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<registerkey> getActionkeys() {
		return actionkeys;
	}
	public void setActionkeys(List<registerkey> actionkeys) {
		this.actionkeys = actionkeys;
	}
    
}
