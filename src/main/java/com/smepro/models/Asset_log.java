package com.smepro.models;

public class Asset_log {

	
	private int id;
	private int workflow_id;
	private int id_add;
	private String asset_type;
	private String created_at;
	private String asset_group;
	private int process_id;
	private int id_refer;
	
	private int  edata_id;

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

	public String getAsset_type() {
		return asset_type;
	}

	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getAsset_group() {
		return asset_group;
	}

	public void setAsset_group(String asset_group) {
		this.asset_group = asset_group;
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

	public int getEdata_id() {
		return edata_id;
	}

	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}

	
	
}
