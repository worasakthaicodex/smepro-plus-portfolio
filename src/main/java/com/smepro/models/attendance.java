package com.smepro.models;

public class attendance extends user {
	
	
	private int attendance_id; 
	private String attendance_date; 
	private String attendance_checkin; 
	private String attendance_checkout; 
	private int attendance_userid; 
	private String attendance_ip; 
	private String attendance_lat; 
	private String attendance_lon;
	private String attendance_file;
	private String attendance_file2;
	
	public int getAttendance_id() {
		return attendance_id;
	}
	public void setAttendance_id(int attendance_id) {
		this.attendance_id = attendance_id;
	}
	public String getAttendance_date() {
		return attendance_date;
	}
	public void setAttendance_date(String attendance_date) {
		this.attendance_date = attendance_date;
	}
	public String getAttendance_checkin() {
		return attendance_checkin;
	}
	public void setAttendance_checkin(String attendance_checkin) {
		this.attendance_checkin = attendance_checkin;
	}
	public String getAttendance_checkout() {
		return attendance_checkout;
	}
	public void setAttendance_checkout(String attendance_checkout) {
		this.attendance_checkout = attendance_checkout;
	}
	public int getAttendance_userid() {
		return attendance_userid;
	}
	public void setAttendance_userid(int attendance_userid) {
		this.attendance_userid = attendance_userid;
	}
	public String getAttendance_ip() {
		return attendance_ip;
	}
	public void setAttendance_ip(String attendance_ip) {
		this.attendance_ip = attendance_ip;
	}
	public String getAttendance_lat() {
		return attendance_lat;
	}
	public void setAttendance_lat(String attendance_lat) {
		this.attendance_lat = attendance_lat;
	}
	public String getAttendance_lon() {
		return attendance_lon;
	}
	public void setAttendance_lon(String attendance_lon) {
		this.attendance_lon = attendance_lon;
	}
	public String getAttendance_file() {
		return attendance_file;
	}
	public void setAttendance_file(String attendance_file) {
		this.attendance_file = attendance_file;
	}
	public String getAttendance_file2() {
		return attendance_file2;
	}
	public void setAttendance_file2(String attendance_file2) {
		this.attendance_file2 = attendance_file2;
	} 
	
	
	

}
