package com.smepro.models;

public class group_members {
	private int id;
	private String username;
	private int group_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	@Override
	public String toString() {
		return String.format("group_members [id=%s, username=%s, group_id=%s]", id, username, group_id);
	}
	
	
	
}
