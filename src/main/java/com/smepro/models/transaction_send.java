package com.smepro.models;

import java.util.List;

import com.smepro.dto.DocDTO;

public class transaction_send {

	
	private int id;
	private int process_id;
	private String send_type;
	private String receiver_type;
	private int created_by;
	private String send_to;
	private String created_at;
	private String status;
	private String comment;
	private int edata_id;
	
	private String data_nameadd;
	private String data_value1;
	private String data_value2;
	private String data_value3;
	
	private String data_title;
	private int alert;
	
	private List<DocDTO> doc;
	
	
	
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	public String getReceiver_type() {
		return receiver_type;
	}
	public void setReceiver_type(String receiver_type) {
		this.receiver_type = receiver_type;
	}
	public String getSend_to() {
		return send_to;
	}
	public void setSend_to(String send_to) {
		this.send_to = send_to;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getData_nameadd() {
		return data_nameadd;
	}
	public void setData_nameadd(String data_nameadd) {
		this.data_nameadd = data_nameadd;
	}
	public String getData_value1() {
		return data_value1;
	}
	public void setData_value1(String data_value1) {
		this.data_value1 = data_value1;
	}
	public String getData_value2() {
		return data_value2;
	}
	public void setData_value2(String data_value2) {
		this.data_value2 = data_value2;
	}
	public String getData_value3() {
		return data_value3;
	}
	public void setData_value3(String data_value3) {
		this.data_value3 = data_value3;
	}
	public String getData_title() {
		return data_title;
	}
	public void setData_title(String data_title) {
		this.data_title = data_title;
	}
	public int getAlert() {
		return alert;
	}
	public void setAlert(int alert) {
		this.alert = alert;
	}
	public List<DocDTO> getDoc() {
		return doc;
	}
	public void setDoc(List<DocDTO> doc) {
		this.doc = doc;
	}

	
	
}
