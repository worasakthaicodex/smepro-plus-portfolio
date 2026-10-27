package com.smepro.models;

public class search_workflow_db {

	private int process_id;
	private String tables;
	private String type;
	private int filters;
	private int edata_id;
	
	
	private String register_type;
	private String register_groups;
	private String register_name;
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	public int getFilters() {
		return filters;
	}
	public void setFilters(int filters) {
		this.filters = filters;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getRegister_type() {
		return register_type;
	}
	public void setRegister_type(String register_type) {
		this.register_type = register_type;
	}
	public String getRegister_groups() {
		return register_groups;
	}
	public void setRegister_groups(String register_groups) {
		this.register_groups = register_groups;
	}
	public String getRegister_name() {
		return register_name;
	}
	public void setRegister_name(String register_name) {
		this.register_name = register_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}



