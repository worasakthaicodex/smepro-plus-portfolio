package com.smepro.dto;

public class driverDTO {
	private int drives_id;
	private String drives_name;
	private int drives_size;
	private String drives_drive;
	
	
	
	public int getDrives_id() {
		return drives_id;
	}
	public void setDrives_id(int drives_id) {
		this.drives_id = drives_id;
	}
	public String getDrives_name() {
		return drives_name;
	}
	public void setDrives_name(String drives_name) {
		this.drives_name = drives_name;
	}
	public int getDrives_size() {
		return drives_size;
	}
	public void setDrives_size(int drives_size) {
		this.drives_size = drives_size;
	}

	public String getDrives_drive() {
		return drives_drive;
	}
	public void setDrives_drive(String string) {
		this.drives_drive = string;
	}
	
	
	@Override
	public String toString() {
		return String.format("drives [drives_id=%s, drives_name=%s, drives_size=%s, drives_drive=%s]", drives_id,
				drives_name, drives_size, drives_drive);
	}
	
	
}
