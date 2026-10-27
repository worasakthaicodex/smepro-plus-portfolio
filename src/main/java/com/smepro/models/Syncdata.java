package com.smepro.models;

public class Syncdata {

	private int id;
	private int edata_id;
	private String index_type; //Profile,Product
	private int index_id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getIndex_type() {
		return index_type;
	}
	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}
	public int getIndex_id() {
		return index_id;
	}
	public void setIndex_id(int index_id) {
		this.index_id = index_id;
	}
	
	
}
