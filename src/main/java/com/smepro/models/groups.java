package com.smepro.models;

public class groups {

	private int id;
	private String group_name;
	private String group_department;
	private int group_department_id;
	private String group_level;
	private int type;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getGroup_level() {
		return group_level;
	}
	public void setGroup_level(String group_level) {
		this.group_level = group_level;
	}
	public String getGroup_department() {
		return group_department;
	}
	public void setGroup_department(String group_department) {
		this.group_department = group_department;
	}
	public int getGroup_department_id() {
		return group_department_id;
	}
	public void setGroup_department_id(int group_department_id) {
		this.group_department_id = group_department_id;
	}
	@Override
	public String toString() {
		return String.format("groups [id=%s, group_name=%s, group_department=%s, group_level=%s, type=%s]", id,
				group_name, group_department, group_level, type);
	}

	
	
}
