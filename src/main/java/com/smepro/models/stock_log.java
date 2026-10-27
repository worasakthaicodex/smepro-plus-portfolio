package com.smepro.models;

public class stock_log {

	
	private int id;
	private int workflow_id;
	private int id_add;
	private int process_id;
	private int id_refer;
	private int edata_id;

	private String stock_group;
	private String stock_type;
	private String created_at;
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
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getStock_group() {
		return stock_group;
	}
	public void setStock_group(String stock_group) {
		this.stock_group = stock_group;
	}
	public String getStock_type() {
		return stock_type;
	}
	public void setStock_type(String stock_type) {
		this.stock_type = stock_type;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	
	
	
	
}
