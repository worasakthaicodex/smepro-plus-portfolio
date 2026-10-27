package com.smepro.models;

import java.util.List;

import com.smepro.dao.Identifiable;

public class Fg implements Identifiable {

	private int id;
	private String lot_batch;
	private String product_code;
	private String product_name;
	private String customer_code;
	private String customer_name;
 	private Double quantity;	
	private Double work_order;	
	private String unit;
 	private String location;
	private String fg_status;
 	
	

	private List<Object> join1;
	private List<Object> join2;



	private int processId;


	@Override
	public void setJoin1(List<Object> join1) {
		this.join1 = join1;
	}

	public List<Object> getJoin1() {
		return join1;
	}
	@Override
	public void setJoin2(List<Object> join2) {
		this.join2 = join2;
	}

	public List<Object> getJoin2() {
		return join2;
	}
	
	
	
	  @Override
	    public int getProcessId() {
	        return this.processId;
	 }
	
	    public void setProcessId(int processId) {
	        this.processId = processId;
	 }
		
	
	
	
	@Override
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLot_batch() {
		return lot_batch;
	}
	public void setLot_batch(String lot_batch) {
		this.lot_batch = lot_batch;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getFg_status() {
		return fg_status;
	}
	public void setFg_status(String fg_status) {
		this.fg_status = fg_status;
	}
	public Double getWork_order() {
		return work_order;
	}
	public void setWork_order(Double work_order) {
		this.work_order = work_order;
	}

 	
 	
	
 	
 	
	
}
