package com.smepro.models;

public class edata_request_pl_pd {
	
	
	private int  id;
	private int edata_id;
	private String request_type;
	private String product_name;
    private int order_quantity;
    private String lot_batch;
    private String  requested_processes;
    private String related_departments;
    private String  request_date;
    private String requester;
    private String status;
    private String remarks;
    private String item_code;
    
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
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public String getLot_batch() {
		return lot_batch;
	}
	public void setLot_batch(String lot_batch) {
		this.lot_batch = lot_batch;
	}
	public String getRequested_processes() {
		return requested_processes;
	}
	public void setRequested_processes(String requested_processes) {
		this.requested_processes = requested_processes;
	}
	public String getRelated_departments() {
		return related_departments;
	}
	public void setRelated_departments(String related_departments) {
		this.related_departments = related_departments;
	}
	public String getRequest_date() {
		return request_date;
	}
	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
    
    
    
    
}



