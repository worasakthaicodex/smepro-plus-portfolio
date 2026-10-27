package com.smepro.models;

public class report_pn_pdf {
	private String user_name;
	private int report_pn_total;
	private int report_pn_appstatus;
	private int report_pn_sendstatus;
	private int report_pn_statuserror;
	
	private int report_pn_target;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getReport_pn_total() {
		return report_pn_total;
	}
	public void setReport_pn_total(int report_pn_total) {
		this.report_pn_total = report_pn_total;
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
	public int getReport_pn_target() {
		return report_pn_target;
	}
	public void setReport_pn_target(int report_pn_target) {
		this.report_pn_target = report_pn_target;
	}
	public int getReport_pn_statuserror() {
		return report_pn_statuserror;
	}
	public void setReport_pn_statuserror(int report_pn_statuserror) {
		this.report_pn_statuserror = report_pn_statuserror;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"report_pn_pdf [user_name=%s, report_pn_total=%s, report_pn_appstatus=%s, report_pn_sendstatus=%s, report_pn_statuserror=%s, report_pn_target=%s]",
				user_name, report_pn_total, report_pn_appstatus, report_pn_sendstatus, report_pn_statuserror,
				report_pn_target);
	}
}
