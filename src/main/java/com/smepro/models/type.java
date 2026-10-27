package com.smepro.models;

public class type {
	private int type_id ;
	private String type_name;
	private int type_status ;
	private String type_type;
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public int getType_status() {
		return type_status;
	}
	public void setType_status(int type_status) {
		this.type_status = type_status;
	}
	public String getType_type() {
		return type_type;
	}
	public void setType_type(String type_type) {
		this.type_type = type_type;
	}
	@Override
	public String toString() {
		return String.format("type [type_id=%s, type_name=%s, type_status=%s, type_type=%s]", type_id, type_name,
				type_status, type_type);
	}
	
	
}
