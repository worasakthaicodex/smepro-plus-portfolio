package com.smepro.models;

public class user_role {

	private int user_role_id;
	private String username;
	private String role;
	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return String.format("user_role [user_role_id=%s, username=%s, role=%s]", user_role_id, username, role);
	}

	
	
	
}
