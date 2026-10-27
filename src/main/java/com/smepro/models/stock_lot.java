package com.smepro.models;

public class stock_lot {

	private int stock_id;
	private String stock_code;
	
	private String  stock_name;
	private String  supplier_code;
	private String  supplier_name;
	private String  lot_batch;
	private Double  stock_remaining;
	private String  stock_storage;
	private String  price;
	private String  stock_status;
	private String  tracking_number;
	
	

	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public String getStock_code() {
		return stock_code;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getSupplier_code() {
		return supplier_code;
	}
	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getLot_batch() {
		return lot_batch;
	}
	public void setLot_batch(String lot_batch) {
		this.lot_batch = lot_batch;
	}
	public Double getStock_remaining() {
		return stock_remaining;
	}
	public void setStock_remaining(Double stock_remaining) {
		this.stock_remaining = stock_remaining;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(String stock_storage) {
		this.stock_storage = stock_storage;
	}
	public String getStock_status() {
		return stock_status;
	}
	public void setStock_status(String stock_status) {
		this.stock_status = stock_status;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	
	
	
	
	
	
}
