package com.smepro.models;

import java.util.List;

import com.smepro.dao.Identifiable;

public class fgstock implements Identifiable {
	

	
	private int id;
	private int fg_id;
	private String fg_details;
	private String fg_remaining;
	private String fg_price;
	private String fg_receipt_date;
	private String fg_list;
	private String fg_quantity;
	private String fg_received;
	private String fg_type;
	private String fg_group;
	
	private String fg_unit;
	private String fg_storage;
	private String fg_receipt_dateupdate;
	private String fg_payout;
	private String fg_pd;
	private String fg_batch;
	private String fg_batch2;
	private int  fg_eform;
	private String tracking_number;
	private String fg_max;
	private String fg_min;
	private String fg_expdate;
	private String fg_support_dataflow;
	private String fg_code;
	
	private String drawer;
	private String payer;
	
	private String  fg_status;
	
	private String  move_form;
	private String  move_to;
	private int  edata_id;
	private List<file>  file;
	
	

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
	public int getFg_id() {
		return fg_id;
	}
	public void setFg_id(int fg_id) {
		this.fg_id = fg_id;
	}
	public String getFg_details() {
		return fg_details;
	}
	public void setFg_details(String fg_details) {
		this.fg_details = fg_details;
	}
	public String getFg_remaining() {
		return fg_remaining;
	}
	public void setFg_remaining(String fg_remaining) {
		this.fg_remaining = fg_remaining;
	}
	public String getFg_price() {
		return fg_price;
	}
	public void setFg_price(String fg_price) {
		this.fg_price = fg_price;
	}
	public String getFg_receipt_date() {
		return fg_receipt_date;
	}
	public void setFg_receipt_date(String fg_receipt_date) {
		this.fg_receipt_date = fg_receipt_date;
	}
	public String getFg_list() {
		return fg_list;
	}
	public void setFg_list(String fg_list) {
		this.fg_list = fg_list;
	}
	public String getFg_quantity() {
		return fg_quantity;
	}
	public void setFg_quantity(String fg_quantity) {
		this.fg_quantity = fg_quantity;
	}
	public String getFg_received() {
		return fg_received;
	}
	public void setFg_received(String fg_received) {
		this.fg_received = fg_received;
	}
	public String getFg_type() {
		return fg_type;
	}
	public void setFg_type(String fg_type) {
		this.fg_type = fg_type;
	}
	public String getFg_group() {
		return fg_group;
	}
	public void setFg_group(String fg_group) {
		this.fg_group = fg_group;
	}
	public String getFg_unit() {
		return fg_unit;
	}
	public void setFg_unit(String fg_unit) {
		this.fg_unit = fg_unit;
	}
	public String getFg_storage() {
		return fg_storage;
	}
	public void setFg_storage(String fg_storage) {
		this.fg_storage = fg_storage;
	}
	public String getFg_receipt_dateupdate() {
		return fg_receipt_dateupdate;
	}
	public void setFg_receipt_dateupdate(String fg_receipt_dateupdate) {
		this.fg_receipt_dateupdate = fg_receipt_dateupdate;
	}
	public String getFg_payout() {
		return fg_payout;
	}
	public void setFg_payout(String fg_payout) {
		this.fg_payout = fg_payout;
	}
	public String getFg_pd() {
		return fg_pd;
	}
	public void setFg_pd(String fg_pd) {
		this.fg_pd = fg_pd;
	}
	public String getFg_batch() {
		return fg_batch;
	}
	public void setFg_batch(String fg_batch) {
		this.fg_batch = fg_batch;
	}
	public String getFg_batch2() {
		return fg_batch2;
	}
	public void setFg_batch2(String fg_batch2) {
		this.fg_batch2 = fg_batch2;
	}
	public int getFg_eform() {
		return fg_eform;
	}
	public void setFg_eform(int fg_eform) {
		this.fg_eform = fg_eform;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	public String getFg_max() {
		return fg_max;
	}
	public void setFg_max(String fg_max) {
		this.fg_max = fg_max;
	}
	public String getFg_min() {
		return fg_min;
	}
	public void setFg_min(String fg_min) {
		this.fg_min = fg_min;
	}
	public String getFg_expdate() {
		return fg_expdate;
	}
	public void setFg_expdate(String fg_expdate) {
		this.fg_expdate = fg_expdate;
	}
	public String getFg_support_dataflow() {
		return fg_support_dataflow;
	}
	public void setFg_support_dataflow(String fg_support_dataflow) {
		this.fg_support_dataflow = fg_support_dataflow;
	}
	public String getFg_code() {
		return fg_code;
	}
	public void setFg_code(String fg_code) {
		this.fg_code = fg_code;
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
	public String getFg_status() {
		return fg_status;
	}
	public void setFg_status(String fg_status) {
		this.fg_status = fg_status;
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
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	


}
