package com.smepro.models;

import java.util.List;

public class Plans {

	
	private int type;
	private int plan_id;
	private int day_in_year;
	private String users;
	private String week = "W-1";
	private List<holiday> holiday;
	private String user_app;
	private String date_due;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public List<holiday> getHoliday() {
		return holiday;
	}
	public void setHoliday(List<holiday> holiday) {
		this.holiday = holiday;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public int getDay_in_year() {
		return day_in_year;
	}
	public void setDay_in_year(int day_in_year) {
		this.day_in_year = day_in_year;
	}
	public String getUser_app() {
		return user_app;
	}
	public void setUser_app(String user_app) {
		this.user_app = user_app;
	}
	public String getDate_due() {
		return date_due;
	}
	public void setDate_due(String date_due) {
		this.date_due = date_due;
	}
	
	

	
	
	
}
