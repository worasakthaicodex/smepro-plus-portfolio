package com.smepro.models;

public class app {
	
	
	private int upload_pn_id;
	private int due;
	private String note;
	private int user_id;
	
	
	private com.smepro.models.log log;
	
	
	public int getUpload_pn_id() {
		return upload_pn_id;
	}
	public int getDue() {
		return due;
	}
	public void setDue(int due) {
		this.due = due;
	}
	public void setUpload_pn_id(int upload_pn_id) {
		this.upload_pn_id = upload_pn_id;
	}
	public com.smepro.models.log getLog() {
		return log;
	}
	public void setLog(com.smepro.models.log log) {
		this.log = log;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
}
