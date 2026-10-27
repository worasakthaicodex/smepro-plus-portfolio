package com.smepro.models;

public class LinksSetting {
	
	private int id;
	private int id_refer;
	private String  allowed_link_relation;
	private String  allowed_use_data;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getAllowed_link_relation() {
		return allowed_link_relation;
	}
	public void setAllowed_link_relation(String allowed_link_relation) {
		this.allowed_link_relation = allowed_link_relation;
	}
	public String getAllowed_use_data() {
		return allowed_use_data;
	}
	public void setAllowed_use_data(String allowed_use_data) {
		this.allowed_use_data = allowed_use_data;
	}
	
	
	

}
