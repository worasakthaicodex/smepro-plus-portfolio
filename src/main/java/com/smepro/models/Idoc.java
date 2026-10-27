package com.smepro.models;

import java.time.LocalDate;

public class Idoc {
	
	private int id;
	private int eform_id;
	private int document_id;
	private int document_rv;
	private String current_step;
	private String date;
	private String date_update;
	private String remarks;
	private String remarks_rejected;
	private String remarks_rejected2;
	private String closing_status;
	private int creator;
	private int sender;
	private int receiver;
	private String cleared;
	private int current_step_int;
	private int alert;
	private int request_to_edit;
	
	
	private String refer_user;
	private String reference_oldsys;
	private String customer_oldsys;
	private String sale_oldsys;
	private String qttotal_oldsys;
	private String closing_status_rejected;

	private String data_nameadd;
	private String data_namesend;
	private String data_namereceiver;
	
	private int expired;

	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getEform_id() {
		return eform_id;
	}
	public void setEform_id(int eform_id) {
		this.eform_id = eform_id;
	}
	public int getDocument_id() {
		return document_id;
	}
	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}
	public int getDocument_rv() {
		return document_rv;
	}
	public void setDocument_rv(int document_rv) {
		this.document_rv = document_rv;
	}
	public String getCurrent_step() {
		return current_step;
	}
	public void setCurrent_step(String current_step) {
		this.current_step = current_step;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks_rejected() {
		return remarks_rejected;
	}
	public void setRemarks_rejected(String remarks_rejected) {
		this.remarks_rejected = remarks_rejected;
	}
	public String getRemarks_rejected2() {
		return remarks_rejected2;
	}
	public void setRemarks_rejected2(String remarks_rejected2) {
		this.remarks_rejected2 = remarks_rejected2;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getReceiver() {
		return receiver;
	}
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	public String getCleared() {
		return cleared;
	}
	public void setCleared(String cleared) {
		this.cleared = cleared;
	}
	public int getCurrent_step_int() {
		return current_step_int;
	}
	public void setCurrent_step_int(int current_step_int) {
		this.current_step_int = current_step_int;
	}
	public int getAlert() {
		return alert;
	}
	public void setAlert(int alert) {
		this.alert = alert;
	}
	public String getClosing_status() {
		return closing_status;
	}
	public void setClosing_status(String closing_status) {
		this.closing_status = closing_status;
	}
	public String getRefer_user() {
		return refer_user;
	}
	public void setRefer_user(String refer_user) {
		this.refer_user = refer_user;
	}
	public String getReference_oldsys() {
		return reference_oldsys;
	}
	public void setReference_oldsys(String reference_oldsys) {
		this.reference_oldsys = reference_oldsys;
	}
	public String getCustomer_oldsys() {
		return customer_oldsys;
	}
	public void setCustomer_oldsys(String customer_oldsys) {
		this.customer_oldsys = customer_oldsys;
	}
	public String getSale_oldsys() {
		return sale_oldsys;
	}
	public void setSale_oldsys(String sale_oldsys) {
		this.sale_oldsys = sale_oldsys;
	}
	public String getQttotal_oldsys() {
		return qttotal_oldsys;
	}
	public void setQttotal_oldsys(String qttotal_oldsys) {
		this.qttotal_oldsys = qttotal_oldsys;
	}
	public String getDate_update() {
		return date_update;
	}
	public void setDate_update(String date_update) {
		this.date_update = date_update;
	}
	public String getData_nameadd() {
		return data_nameadd;
	}
	public void setData_nameadd(String data_nameadd) {
		this.data_nameadd = data_nameadd;
	}
	public String getData_namesend() {
		return data_namesend;
	}
	public void setData_namesend(String data_namesend) {
		this.data_namesend = data_namesend;
	}
	public String getData_namereceiver() {
		return data_namereceiver;
	}
	public void setData_namereceiver(String data_namereceiver) {
		this.data_namereceiver = data_namereceiver;
	}
	public String getClosing_status_rejected() {
		return closing_status_rejected;
	}
	public void setClosing_status_rejected(String closing_status_rejected) {
		this.closing_status_rejected = closing_status_rejected;
	}
	public int getExpired() {
		return expired;
	}
	public void setExpired(int expired) {
		this.expired = expired;
	}
	public int getRequest_to_edit() {
		return request_to_edit;
	}
	public void setRequest_to_edit(int request_to_edit) {
		this.request_to_edit = request_to_edit;
	}

	
	
	
	
}
