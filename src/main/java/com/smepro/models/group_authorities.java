package com.smepro.models;

import java.util.List;

public class group_authorities {

	private int id;
	private String authority;
	private List<String> permissions;
	private int group_id;
	private int type;
	private int main;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
		
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMain() {
		return main;
	}
	public void setMain(int main) {
		this.main = main;
	}
	
	
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toString() {
		return String.format("group_authorities [id=%s, authority=%s, permissions=%s, group_id=%s, type=%s, main=%s]",
				id, authority, permissions, group_id, type, main);
	}

	
	
	
}
