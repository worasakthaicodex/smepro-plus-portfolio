package com.smepro.dto;

public class memberDTO {
	
	private int id  = 0;
	private String name = null;
	private String date = null;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return String.format("memberDTO [id=%s, name=%s, date=%s]", id, name, date);
	} 
	
	
	
}
