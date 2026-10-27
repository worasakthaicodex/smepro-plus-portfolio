package com.smepro.models;

public class report_pn extends showupload_pn{
	private int report_pn_id;
	private String report_pn_date;
	private int report_pn_idrefer;
	private int report_pn_target;
	private int report_pn_total;
	private String report_pn_respon;
	private String report_pn_app;
	private int report_pn_status;
	private String report_pn_department;
	private int report_pn_appstatus;
	private int report_pn_sendstatus;
	private int report_pn_statussccess;
	
	private int report_pn_app_statussccess;
	
	private int report_pn_statuserror;
	private String report_pn_dateupload;
	private String report_pn_datecheck;
	private long upload_pn_monitor;
	private int upload_pn_alert;
	private int report_pn_type;
	
	private String report_pn_note;
	
	
	public int getReport_pn_id() {
		return report_pn_id;
	}
	public void setReport_pn_id(int report_pn_id) {
		this.report_pn_id = report_pn_id;
	}
	public String getReport_pn_date() {
		return report_pn_date;
	}
	public void setReport_pn_date(String report_pn_date) {
		this.report_pn_date = report_pn_date;
	}
	public int getReport_pn_idrefer() {
		return report_pn_idrefer;
	}
	public void setReport_pn_idrefer(int report_pn_idrefer) {
		this.report_pn_idrefer = report_pn_idrefer;
	}
	public int getReport_pn_target() {
		return report_pn_target;
	}
	public void setReport_pn_target(int report_pn_target) {
		this.report_pn_target = report_pn_target;
	}
	public int getReport_pn_total() {
		return report_pn_total;
	}
	public void setReport_pn_total(int report_pn_total) {
		this.report_pn_total = report_pn_total;
	}
	public String getReport_pn_respon() {
		return report_pn_respon;
	}
	public void setReport_pn_respon(String report_pn_respon) {
		this.report_pn_respon = report_pn_respon;
	}
	public String getReport_pn_app() {
		return report_pn_app;
	}
	public void setReport_pn_app(String report_pn_app) {
		this.report_pn_app = report_pn_app;
	}
	public int getReport_pn_status() {
		return report_pn_status;
	}
	public void setReport_pn_status(int report_pn_status) {
		this.report_pn_status = report_pn_status;
	}
	public String getReport_pn_department() {
		return report_pn_department;
	}
	public void setReport_pn_department(String report_pn_department) {
		this.report_pn_department = report_pn_department;
	}
	public int getReport_pn_appstatus() {
		return report_pn_appstatus;
	}
	public void setReport_pn_appstatus(int report_pn_appstatus) {
		this.report_pn_appstatus = report_pn_appstatus;
	}
	public int getReport_pn_sendstatus() {
		return report_pn_sendstatus;
	}
	public void setReport_pn_sendstatus(int report_pn_sendstatus) {
		this.report_pn_sendstatus = report_pn_sendstatus;
	}
	public int getReport_pn_statussccess() {
		return report_pn_statussccess;
	}
	public void setReport_pn_statussccess(int report_pn_statussccess) {
		this.report_pn_statussccess = report_pn_statussccess;
	}
	public int getReport_pn_statuserror() {
		return report_pn_statuserror;
	}
	public void setReport_pn_statuserror(int report_pn_statuserror) {
		this.report_pn_statuserror = report_pn_statuserror;
	}
	public String getReport_pn_dateupload() {
		return report_pn_dateupload;
	}
	public void setReport_pn_dateupload(String report_pn_dateupload) {
		this.report_pn_dateupload = report_pn_dateupload;
	}
	public String getReport_pn_datecheck() {
		return report_pn_datecheck;
	}
	public void setReport_pn_datecheck(String report_pn_datecheck) {
		this.report_pn_datecheck = report_pn_datecheck;
	}


	public int getUpload_pn_alert() {
		return upload_pn_alert;
	}
	public void setUpload_pn_alert(int upload_pn_alert) {
		this.upload_pn_alert = upload_pn_alert;
	}

	public int getReport_pn_type() {
		return report_pn_type;
	}
	public void setReport_pn_type(int report_pn_type) {
		this.report_pn_type = report_pn_type;
	}

	public long getUpload_pn_monitor() {
		return upload_pn_monitor;
	}
	public void setUpload_pn_monitor(long upload_pn_monitor) {
		this.upload_pn_monitor = upload_pn_monitor;
	}

	public int getReport_pn_app_statussccess() {
		return report_pn_app_statussccess;
	}
	public void setReport_pn_app_statussccess(int report_pn_app_statussccess) {
		this.report_pn_app_statussccess = report_pn_app_statussccess;
	}
	
	
	
	public String getReport_pn_note() {
		return report_pn_note;
	}
	public void setReport_pn_note(String report_pn_note) {
		this.report_pn_note = report_pn_note;
	}
	@Override
	public String toString() {
		return String.format(
				"report_pn [report_pn_id=%s, report_pn_date=%s, report_pn_idrefer=%s, report_pn_target=%s, report_pn_total=%s, report_pn_respon=%s, report_pn_app=%s, report_pn_status=%s, report_pn_department=%s, report_pn_appstatus=%s, report_pn_sendstatus=%s, report_pn_statussccess=%s, report_pn_statuserror=%s, report_pn_dateupload=%s, report_pn_datecheck=%s]",
				report_pn_id, report_pn_date, report_pn_idrefer, report_pn_target, report_pn_total, report_pn_respon,
				report_pn_app, report_pn_status, report_pn_department, report_pn_appstatus, report_pn_sendstatus,
				report_pn_statussccess, report_pn_statuserror, report_pn_dateupload, report_pn_datecheck);
	}

	
}
