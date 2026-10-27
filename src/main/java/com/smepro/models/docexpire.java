package com.smepro.models;

public class docexpire {

	private int year;
	private String type;
	private int total_QM;
	private int total_QP;
	private int total_WI;
	private int total_SD;
	private int total_FO;
	
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTotal_QM() {
		return total_QM;
	}
	public void setTotal_QM(int total_QM) {
		this.total_QM = total_QM;
	}
	public int getTotal_QP() {
		return total_QP;
	}
	public void setTotal_QP(int total_QP) {
		this.total_QP = total_QP;
	}
	public int getTotal_WI() {
		return total_WI;
	}
	public void setTotal_WI(int total_WI) {
		this.total_WI = total_WI;
	}
	public int getTotal_SD() {
		return total_SD;
	}
	public void setTotal_SD(int total_SD) {
		this.total_SD = total_SD;
	}
	public int getTotal_FO() {
		return total_FO;
	}
	public void setTotal_FO(int total_FO) {
		this.total_FO = total_FO;
	}
	@Override
	public String toString() {
		return String.format(
				"docexpire [year=%s, type=%s, total_QM=%s, total_QP=%s, total_WI=%s, total_SD=%s, total_FO=%s]", year,
				type, total_QM, total_QP, total_WI, total_SD, total_FO);
	}
	
	
	
	
}
