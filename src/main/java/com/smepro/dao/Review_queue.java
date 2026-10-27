package com.smepro.dao;


public class Review_queue {
	
	private long id;
	private int plan_id;
	private String status;
	private int reviewer_id;
	private String reviewer_date;
	private int id_add;
	private String trun_date;
	private int trun;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getReviewer_id() {
		return reviewer_id;
	}
	public void setReviewer_id(int reviewer_id) {
		this.reviewer_id = reviewer_id;
	}
	public String getReviewer_date() {
		return reviewer_date;
	}
	public void setReviewer_date(String reviewer_date) {
		this.reviewer_date = reviewer_date;
	}
	public int getId_add() {
		return id_add;
	}
	public void setId_add(int id_add) {
		this.id_add = id_add;
	}
	public String getTrun_date() {
		return trun_date;
	}
	public void setTrun_date(String trun_date) {
		this.trun_date = trun_date;
	}
	public int getTrun() {
		return trun;
	}
	public void setTrun(int trun) {
		this.trun = trun;
	}


	
}
