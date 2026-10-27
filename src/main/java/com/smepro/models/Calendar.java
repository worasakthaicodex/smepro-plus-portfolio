package com.smepro.models;

public class Calendar {

	private int id;
	private int year;
	private int month;
	private int day;
	private String event;
	
	private String by_name;
	private String to_name;
	private String by_NameId;
	private int  process_id;
	
	private String startTime = "08:00";
	private String endTime= "18:00";
	private int index_id;
	private int eform_id;
	private int status;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getIndex_id() {
		return index_id;
	}
	public void setIndex_id(int index_id) {
		this.index_id = index_id;
	}
	public int getEform_id() {
		return eform_id;
	}
	public void setEform_id(int eform_id) {
		this.eform_id = eform_id;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getBy_name() {
		return by_name;
	}
	public void setBy_name(String by_name) {
		this.by_name = by_name;
	}
	public String getTo_name() {
		return to_name;
	}
	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}
	public String getBy_NameId() {
		return by_NameId;
	}
	public void setBy_NameId(String by_NameId) {
		this.by_NameId = by_NameId;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
