package com.smepro.models;

public class DocumentEffect {
	
	private int id;
	private int edata_id;
	private int id_refer;
	
	private int id_new;
	private int id_old;
	private int id_add;
	
	private String data_type;
	private String title;
	private String type;
	private String  created_at;
	private String  date_add;
	
	

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
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getId_new() {
		return id_new;
	}
	public void setId_new(int id_new) {
		this.id_new = id_new;
	}
	public int getId_old() {
		return id_old;
	}
	public void setId_old(int id_old) {
		this.id_old = id_old;
	}
	public int getId_add() {
		return id_add;
	}
	public void setId_add(int id_add) {
		this.id_add = id_add;
	}
	public String getDate_add() {
		return date_add;
	}
	public void setDate_add(String date_add) {
		this.date_add = date_add;
	}
	
	
	
	
}
