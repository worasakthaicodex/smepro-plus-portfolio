package com.smepro.models;


public class reportsetting  {
	
	
	private int reportsetting_id;
	private int reportsetting_id_refer;
	private String reportsetting_pd;
	private String reportsetting_batch;
	private String reportsetting_department;
	private String reportsetting_list;
	private String reportsetting_employee;
	private String reportsetting_machine;
	private String reportsetting_date_wip;
	private String reportsetting_orders;
	private String reportsetting_received_today;
	private String reportsetting_paid_today;
	private String reportsetting_form_name;
	private String reportsetting_type;
	private String reportsetting_tables;
	private String reportsetting_data;
	private String reportsetting_line;
	
	
	private String  reportsetting_downtime;
	private String  reportsetting_planned_time;
	private String reportsetting_availability;
	private String  reportsetting_speed;
	private String  reportsetting_total_count; // รวมทั้งดีและเสีย
	private String  reportsetting_operating_time; //(Planned Production Time  - Downtime)
	private String  reportsetting_performance; //(Planned Production Time  - Downtime)
	private String  reportsetting_good_count; // จำนวนชิ้นงานที่ใช้ได้จริง (ไม่เสีย)
	private String reportsetting_quality;
	private String reportsetting_oee; // จำนวนชิ้นงานที่ใช้ได้จริง (ไม่เสีย)

	
	private String reportsetting_stock_code;
	private String reportsetting_stock_name;
	private String reportsetting_total_system;
	private String reportsetting_total_real;
	
	
	private String reportsetting_drawer;
	private String reportsetting_payer;
	
	
	public int getReportsetting_id() {
		return reportsetting_id;
	}
	public void setReportsetting_id(int reportsetting_id) {
		this.reportsetting_id = reportsetting_id;
	}
	public int getReportsetting_id_refer() {
		return reportsetting_id_refer;
	}
	public void setReportsetting_id_refer(int reportsetting_id_refer) {
		this.reportsetting_id_refer = reportsetting_id_refer;
	}
	public String getReportsetting_pd() {
		return reportsetting_pd;
	}
	public void setReportsetting_pd(String reportsetting_pd) {
		this.reportsetting_pd = reportsetting_pd;
	}
	public String getReportsetting_batch() {
		return reportsetting_batch;
	}
	public void setReportsetting_batch(String reportsetting_batch) {
		this.reportsetting_batch = reportsetting_batch;
	}
	public String getReportsetting_department() {
		return reportsetting_department;
	}
	public void setReportsetting_department(String reportsetting_department) {
		this.reportsetting_department = reportsetting_department;
	}
	public String getReportsetting_list() {
		return reportsetting_list;
	}
	public void setReportsetting_list(String reportsetting_list) {
		this.reportsetting_list = reportsetting_list;
	}
	public String getReportsetting_employee() {
		return reportsetting_employee;
	}
	public void setReportsetting_employee(String reportsetting_employee) {
		this.reportsetting_employee = reportsetting_employee;
	}
	public String getReportsetting_machine() {
		return reportsetting_machine;
	}
	public void setReportsetting_machine(String reportsetting_machine) {
		this.reportsetting_machine = reportsetting_machine;
	}
	public String getReportsetting_date_wip() {
		return reportsetting_date_wip;
	}
	public void setReportsetting_date_wip(String reportsetting_date_wip) {
		this.reportsetting_date_wip = reportsetting_date_wip;
	}
	public String getReportsetting_orders() {
		return reportsetting_orders;
	}
	public void setReportsetting_orders(String reportsetting_orders) {
		this.reportsetting_orders = reportsetting_orders;
	}
	public String getReportsetting_received_today() {
		return reportsetting_received_today;
	}
	public void setReportsetting_received_today(String reportsetting_received_today) {
		this.reportsetting_received_today = reportsetting_received_today;
	}
	public String getReportsetting_paid_today() {
		return reportsetting_paid_today;
	}
	public void setReportsetting_paid_today(String reportsetting_paid_today) {
		this.reportsetting_paid_today = reportsetting_paid_today;
	}
	public String getReportsetting_form_name() {
		return reportsetting_form_name;
	}
	public void setReportsetting_form_name(String reportsetting_form_name) {
		this.reportsetting_form_name = reportsetting_form_name;
	}
	public String getReportsetting_type() {
		return reportsetting_type;
	}
	public void setReportsetting_type(String reportsetting_type) {
		this.reportsetting_type = reportsetting_type;
	}
	public String getReportsetting_tables() {
		return reportsetting_tables;
	}
	public void setReportsetting_tables(String reportsetting_tables) {
		this.reportsetting_tables = reportsetting_tables;
	}
	public String getReportsetting_data() {
		return reportsetting_data;
	}
	public void setReportsetting_data(String reportsetting_data) {
		this.reportsetting_data = reportsetting_data;
	}
	public String getReportsetting_line() {
		return reportsetting_line;
	}
	public void setReportsetting_line(String reportsetting_line) {
		this.reportsetting_line = reportsetting_line;
	}
	public String getReportsetting_downtime() {
		return reportsetting_downtime;
	}
	public void setReportsetting_downtime(String reportsetting_downtime) {
		this.reportsetting_downtime = reportsetting_downtime;
	}
	public String getReportsetting_planned_time() {
		return reportsetting_planned_time;
	}
	public void setReportsetting_planned_time(String reportsetting_planned_time) {
		this.reportsetting_planned_time = reportsetting_planned_time;
	}
	public String getReportsetting_availability() {
		return reportsetting_availability;
	}
	public void setReportsetting_availability(String reportsetting_availability) {
		this.reportsetting_availability = reportsetting_availability;
	}
	public String getReportsetting_speed() {
		return reportsetting_speed;
	}
	public void setReportsetting_speed(String reportsetting_speed) {
		this.reportsetting_speed = reportsetting_speed;
	}
	public String getReportsetting_total_count() {
		return reportsetting_total_count;
	}
	public void setReportsetting_total_count(String reportsetting_total_count) {
		this.reportsetting_total_count = reportsetting_total_count;
	}
	public String getReportsetting_operating_time() {
		return reportsetting_operating_time;
	}
	public void setReportsetting_operating_time(String reportsetting_operating_time) {
		this.reportsetting_operating_time = reportsetting_operating_time;
	}
	public String getReportsetting_performance() {
		return reportsetting_performance;
	}
	public void setReportsetting_performance(String reportsetting_performance) {
		this.reportsetting_performance = reportsetting_performance;
	}
	public String getReportsetting_good_count() {
		return reportsetting_good_count;
	}
	public void setReportsetting_good_count(String reportsetting_good_count) {
		this.reportsetting_good_count = reportsetting_good_count;
	}
	public String getReportsetting_quality() {
		return reportsetting_quality;
	}
	public void setReportsetting_quality(String reportsetting_quality) {
		this.reportsetting_quality = reportsetting_quality;
	}
	public String getReportsetting_oee() {
		return reportsetting_oee;
	}
	public void setReportsetting_oee(String reportsetting_oee) {
		this.reportsetting_oee = reportsetting_oee;
	}
	public String getReportsetting_stock_code() {
		return reportsetting_stock_code;
	}
	public void setReportsetting_stock_code(String reportsetting_stock_code) {
		this.reportsetting_stock_code = reportsetting_stock_code;
	}
	public String getReportsetting_stock_name() {
		return reportsetting_stock_name;
	}
	public void setReportsetting_stock_name(String reportsetting_stock_name) {
		this.reportsetting_stock_name = reportsetting_stock_name;
	}
	public String getReportsetting_total_system() {
		return reportsetting_total_system;
	}
	public void setReportsetting_total_system(String reportsetting_total_system) {
		this.reportsetting_total_system = reportsetting_total_system;
	}
	public String getReportsetting_total_real() {
		return reportsetting_total_real;
	}
	public void setReportsetting_total_real(String reportsetting_total_real) {
		this.reportsetting_total_real = reportsetting_total_real;
	}
	public String getReportsetting_drawer() {
		return reportsetting_drawer;
	}
	public void setReportsetting_drawer(String reportsetting_drawer) {
		this.reportsetting_drawer = reportsetting_drawer;
	}
	public String getReportsetting_payer() {
		return reportsetting_payer;
	}
	public void setReportsetting_payer(String reportsetting_payer) {
		this.reportsetting_payer = reportsetting_payer;
	}
	
	

	
	
	
	
}




