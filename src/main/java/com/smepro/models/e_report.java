package com.smepro.models;

public class e_report {

	private int e_report_id;
	private int e_report_idadd;
	private int e_report_conditionid;
	private String line_label;
	private String line_data;
	private int line_target;
	private String column_label;
	private String column_data;
	private int column_target;
	private String pie_label;
	private String pie_data;
	private int status;
	
	public int getE_report_id() {
		return e_report_id;
	}
	public void setE_report_id(int e_report_id) {
		this.e_report_id = e_report_id;
	}
	public int getE_report_idadd() {
		return e_report_idadd;
	}
	public void setE_report_idadd(int e_report_idadd) {
		this.e_report_idadd = e_report_idadd;
	}
	public int getE_report_conditionid() {
		return e_report_conditionid;
	}
	public void setE_report_conditionid(int e_report_conditionid) {
		this.e_report_conditionid = e_report_conditionid;
	}
	public String getLine_label() {
		return line_label;
	}
	public void setLine_label(String line_label) {
		this.line_label = line_label;
	}
	public String getLine_data() {
		return line_data;
	}
	public void setLine_data(String line_data) {
		this.line_data = line_data;
	}
	public int getLine_target() {
		return line_target;
	}
	public void setLine_target(int line_target) {
		this.line_target = line_target;
	}
	public String getColumn_label() {
		return column_label;
	}
	public void setColumn_label(String column_label) {
		this.column_label = column_label;
	}
	public String getColumn_data() {
		return column_data;
	}
	public void setColumn_data(String column_data) {
		this.column_data = column_data;
	}
	public int getColumn_target() {
		return column_target;
	}
	public void setColumn_target(int column_target) {
		this.column_target = column_target;
	}
	public String getPie_label() {
		return pie_label;
	}
	public void setPie_label(String pie_label) {
		this.pie_label = pie_label;
	}
	public String getPie_data() {
		return pie_data;
	}
	public void setPie_data(String pie_data) {
		this.pie_data = pie_data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"e_report [e_report_id=%s, e_report_idadd=%s, e_report_conditionid=%s, line_label=%s, line_data=%s, line_target=%s, column_label=%s, column_data=%s, column_target=%s, pie_label=%s, pie_data=%s]",
				e_report_id, e_report_idadd, e_report_conditionid, line_label, line_data, line_target, column_label,
				column_data, column_target, pie_label, pie_data);
	}
	
	
	
	
}
