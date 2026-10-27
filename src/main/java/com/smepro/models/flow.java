package com.smepro.models;

public class flow {

	private int id; 
	private int edata_id; 
	private int flow_send; 
	private int flow_to; 
	private String flow_comment; 
	private String flow_date;
	private String flow_step; 
	private String flow_type; 
	
	private String send; 
	private String to; 
	
	
	
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
	public int getFlow_send() {
		return flow_send;
	}
	public void setFlow_send(int flow_send) {
		this.flow_send = flow_send;
	}
	public int getFlow_to() {
		return flow_to;
	}
	public void setFlow_to(int flow_to) {
		this.flow_to = flow_to;
	}
	public String getFlow_comment() {
		return flow_comment;
	}
	public void setFlow_comment(String flow_comment) {
		this.flow_comment = flow_comment;
	}
	public String getFlow_date() {
		return flow_date;
	}
	public void setFlow_date(String flow_date) {
		this.flow_date = flow_date;
	}
	public String getFlow_step() {
		return flow_step;
	}
	public void setFlow_step(String flow_step) {
		this.flow_step = flow_step;
	}
	public String getFlow_type() {
		return flow_type;
	}
	public void setFlow_type(String flow_type) {
		this.flow_type = flow_type;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	} 
	
	
	
}
