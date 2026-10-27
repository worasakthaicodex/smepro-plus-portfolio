package com.smepro.models;

public class product_log extends product_master {

	
	private int id;
	private int workflow_id;
	private int id_add;
	private String product_category;
	private String created_at;
	private String type;
	private int edata_id;
	private int process_id;
	
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorkflow_id() {
		return workflow_id;
	}
	public void setWorkflow_id(int workflow_id) {
		this.workflow_id = workflow_id;
	}
	public int getId_add() {
		return id_add;
	}
	public void setId_add(int id_add) {
		this.id_add = id_add;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	

	
	
	
}



