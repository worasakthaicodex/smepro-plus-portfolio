package com.smepro.models;

import java.util.List;

public class stock {
	
	
	private List<stock_lot>  stock_lot;
	
	private int  ir_id; 
	private String  ir_date;
	private int  ir_idrefer;

	private int  dgr_id; 
	private String  dgr_date;
	private int  dgr_idrefer;
	
	
	private int  sr_id; 
	private String  sr_date;
	private int  sr_idrefer;
	
	
	private int id;
	private int stock_id;
	private String stock_details;
	private String stock_remaining;
	private String stock_price;
	private String stock_receipt_date;
	private String stock_list;
	private String stock_quantity;
	private String stock_received;
	private String stock_type;
	private String stock_group;
	
	private String stock_unit;
	private String stock_storage;
	private String stock_receipt_dateupdate;
	private String stock_payout;
	private String stock_pd;
	private String stock_batch;
	private String stock_batch2;
	private int  stock_eform;
	private String tracking_number;
	private String stock_max;
	private String stock_min;
	private String stock_expdate;
	private String stock_support_dataflow;
	private String stock_code;
	
	private String drawer;
	private String payer;
	
	private String  stock_status;
	
	private String  move_form;
	private String  move_to;
	private int  edata_id;
	private List<file>  file;
	
	public String getStock_status() {
		return stock_status;
	}
	public void setStock_status(String stock_status) {
		this.stock_status = stock_status;
	}
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public String getStock_details() {
		return stock_details;
	}
	public void setStock_details(String stock_details) {
		this.stock_details = stock_details;
	}
	public String getStock_remaining() {
		return stock_remaining;
	}
	public void setStock_remaining(String stock_remaining) {
		this.stock_remaining = stock_remaining;
	}
	public String getStock_price() {
		return stock_price;
	}
	public void setStock_price(String stock_price) {
		this.stock_price = stock_price;
	}
	public String getStock_receipt_date() {
		return stock_receipt_date;
	}
	public void setStock_receipt_date(String stock_receipt_date) {
		this.stock_receipt_date = stock_receipt_date;
	}
	public String getStock_list() {
		return stock_list;
	}
	public void setStock_list(String stock_list) {
		this.stock_list = stock_list;
	}
	public String getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(String stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	public String getStock_received() {
		return stock_received;
	}
	public void setStock_received(String stock_received) {
		this.stock_received = stock_received;
	}
	public String getStock_type() {
		return stock_type;
	}
	public void setStock_type(String stock_type) {
		this.stock_type = stock_type;
	}
	public String getStock_unit() {
		return stock_unit;
	}
	public void setStock_unit(String stock_unit) {
		this.stock_unit = stock_unit;
	}
	public String getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(String stock_storage) {
		this.stock_storage = stock_storage;
	}
	public String getStock_receipt_dateupdate() {
		return stock_receipt_dateupdate;
	}
	public void setStock_receipt_dateupdate(String stock_receipt_dateupdate) {
		this.stock_receipt_dateupdate = stock_receipt_dateupdate;
	}
	public String getStock_payout() {
		return stock_payout;
	}
	public void setStock_payout(String stock_payout) {
		this.stock_payout = stock_payout;
	}
	public String getStock_pd() {
		return stock_pd;
	}
	public void setStock_pd(String stock_pd) {
		this.stock_pd = stock_pd;
	}
	public String getStock_batch() {
		return stock_batch;
	}
	public void setStock_batch(String stock_batch) {
		this.stock_batch = stock_batch;
	}
	public int getIr_id() {
		return ir_id;
	}
	public void setIr_id(int ir_id) {
		this.ir_id = ir_id;
	}
	public String getIr_date() {
		return ir_date;
	}
	public void setIr_date(String ir_date) {
		this.ir_date = ir_date;
	}
	public int getIr_idrefer() {
		return ir_idrefer;
	}
	public void setIr_idrefer(int ir_idrefer) {
		this.ir_idrefer = ir_idrefer;
	}
	public int getStock_eform() {
		return stock_eform;
	}
	public void setStock_eform(int stock_eform) {
		this.stock_eform = stock_eform;
	}
	public String getStock_max() {
		return stock_max;
	}
	public void setStock_max(String stock_max) {
		this.stock_max = stock_max;
	}
	public String getStock_min() {
		return stock_min;
	}
	public void setStock_min(String stock_min) {
		this.stock_min = stock_min;
	}
	public String getStock_expdate() {
		return stock_expdate;
	}
	public void setStock_expdate(String stock_expdate) {
		this.stock_expdate = stock_expdate;
	}
	public String getStock_support_dataflow() {
		return stock_support_dataflow;
	}
	public void setStock_support_dataflow(String stock_support_dataflow) {
		this.stock_support_dataflow = stock_support_dataflow;
	}
	public int getDgr_id() {
		return dgr_id;
	}
	public void setDgr_id(int dgr_id) {
		this.dgr_id = dgr_id;
	}
	public String getDgr_date() {
		return dgr_date;
	}
	public void setDgr_date(String dgr_date) {
		this.dgr_date = dgr_date;
	}
	public int getDgr_idrefer() {
		return dgr_idrefer;
	}
	public void setDgr_idrefer(int dgr_idrefer) {
		this.dgr_idrefer = dgr_idrefer;
	}
	public int getSr_id() {
		return sr_id;
	}
	public void setSr_id(int sr_id) {
		this.sr_id = sr_id;
	}
	public String getSr_date() {
		return sr_date;
	}
	public void setSr_date(String sr_date) {
		this.sr_date = sr_date;
	}
	public int getSr_idrefer() {
		return sr_idrefer;
	}
	public void setSr_idrefer(int sr_idrefer) {
		this.sr_idrefer = sr_idrefer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStock_code() {
		return stock_code;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	public String getStock_group() {
		return stock_group;
	}
	public void setStock_group(String stock_group) {
		this.stock_group = stock_group;
	}
	public List<stock_lot> getStock_lot() {
		return stock_lot;
	}
	public void setStock_lot(List<stock_lot> stock_lot) {
		this.stock_lot = stock_lot;
	}
	public String getDrawer() {
		return drawer;
	}
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getMove_form() {
		return move_form;
	}
	public void setMove_form(String move_form) {
		this.move_form = move_form;
	}
	public String getMove_to() {
		return move_to;
	}
	public void setMove_to(String move_to) {
		this.move_to = move_to;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	public String getStock_batch2() {
		return stock_batch2;
	}
	public void setStock_batch2(String stock_batch2) {
		this.stock_batch2 = stock_batch2;
	}
	
	
	

}
