package com.smepro.dto;

public class dormitoryDTO {
	private int dormitory_id   = 0;
	private String dormitory_name ="";


	public int getDormitory_id() {
		return dormitory_id;
	}

	public void setDormitory_id(int dormitory_id) {
		this.dormitory_id = dormitory_id;
	}

	public String getDormitory_name() {
		return dormitory_name;
	}

	public void setDormitory_name(String dormitory_name) {
		this.dormitory_name = dormitory_name;
	}

	
	
	@Override
	public String toString() {
		return String.format("dormitoryDTO [dormitory_id=%s, dormitory_name=%s]", dormitory_id, dormitory_name);
	}

	
	
}
