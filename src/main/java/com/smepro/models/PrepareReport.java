package com.smepro.models;

public class PrepareReport {

	private int id;
	private int  source_type_method;
	private int  source_id;
	private int  process_id;
	private String  source_type;
	private String  source_name;
	private int  querychains_enabled;
	
	

	private int sync_back_limit;
	private int sync_back_random;
	private int sync_back_not_unique_active;
	
	private int data_info_show_row;
	private int data_info_showsample_total;
	private int data_info_showtotal_row;
	
	
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSource_type_method() {
		return source_type_method;
	}
	public void setSource_type_method(int source_type_method) {
		this.source_type_method = source_type_method;
	}
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public String getSource_type() {
		return source_type;
	}
	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}
	public int getQuerychains_enabled() {
		return querychains_enabled;
	}
	public void setQuerychains_enabled(int querychains_enabled) {
		this.querychains_enabled = querychains_enabled;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public int getSync_back_limit() {
		return sync_back_limit;
	}
	public void setSync_back_limit(int sync_back_limit) {
		this.sync_back_limit = sync_back_limit;
	}
	public int getSync_back_random() {
		return sync_back_random;
	}
	public void setSync_back_random(int sync_back_random) {
		this.sync_back_random = sync_back_random;
	}
	public int getData_info_show_row() {
		return data_info_show_row;
	}
	public void setData_info_show_row(int data_info_show_row) {
		this.data_info_show_row = data_info_show_row;
	}
	public int getData_info_showsample_total() {
		return data_info_showsample_total;
	}
	public void setData_info_showsample_total(int data_info_showsample_total) {
		this.data_info_showsample_total = data_info_showsample_total;
	}
	public int getData_info_showtotal_row() {
		return data_info_showtotal_row;
	}
	public void setData_info_showtotal_row(int data_info_showtotal_row) {
		this.data_info_showtotal_row = data_info_showtotal_row;
	}
	public int getSync_back_not_unique_active() {
		return sync_back_not_unique_active;
	}
	public void setSync_back_not_unique_active(int sync_back_not_unique_active) {
		this.sync_back_not_unique_active = sync_back_not_unique_active;
	}

	
	
}
