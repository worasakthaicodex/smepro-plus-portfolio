package com.smepro.models;

public class RelationsItems {
	
	private int id;
	private int main_id;
	private String main_subtable_id;
	private String relations_subtable_id;
	private String main_subtable_name;
	private String relations_subtable_name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMain_id() {
		return main_id;
	}
	public void setMain_id(int main_id) {
		this.main_id = main_id;
	}
	public String getMain_subtable_id() {
		return main_subtable_id;
	}
	public void setMain_subtable_id(String main_subtable_id) {
		this.main_subtable_id = main_subtable_id;
	}
	public String getRelations_subtable_id() {
		return relations_subtable_id;
	}
	public void setRelations_subtable_id(String relations_subtable_id) {
		this.relations_subtable_id = relations_subtable_id;
	}
	public String getMain_subtable_name() {
		return main_subtable_name;
	}
	public void setMain_subtable_name(String main_subtable_name) {
		this.main_subtable_name = main_subtable_name;
	}
	public String getRelations_subtable_name() {
		return relations_subtable_name;
	}
	public void setRelations_subtable_name(String relations_subtable_name) {
		this.relations_subtable_name = relations_subtable_name;
	}
	
	
}
