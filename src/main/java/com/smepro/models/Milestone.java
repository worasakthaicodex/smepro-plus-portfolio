package com.smepro.models;

public class Milestone {

	private int id; 
	private int project_id; 
	private String detail; 
	private String date_due; 
	private String date_alert; 
	private Double billing_amount; 
	private int user_work_id; 
	private String user_work_name; 
	private int status;
	
	private String processing_date; 
	private Double collected_payment; 

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDate_due() {
		return date_due;
	}
	public void setDate_due(String date_due) {
		this.date_due = date_due;
	}
	public String getDate_alert() {
		return date_alert;
	}
	public void setDate_alert(String date_alert) {
		this.date_alert = date_alert;
	}
	public Double getBilling_amount() {
		return billing_amount;
	}
	public void setBilling_amount(Double billing_amount) {
		this.billing_amount = billing_amount;
	}
	public int getUser_work_id() {
		return user_work_id;
	}
	public void setUser_work_id(int user_work_id) {
		this.user_work_id = user_work_id;
	}
	public String getUser_work_name() {
		return user_work_name;
	}
	public void setUser_work_name(String user_work_name) {
		this.user_work_name = user_work_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProcessing_date() {
		return processing_date;
	}
	public void setProcessing_date(String processing_date) {
		this.processing_date = processing_date;
	}
	public Double getCollected_payment() {
		return collected_payment;
	}
	public void setCollected_payment(Double collected_payment) {
		this.collected_payment = collected_payment;
	}
	
	
	
	
	
}
