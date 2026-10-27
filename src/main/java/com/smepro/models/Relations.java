package com.smepro.models;

import java.util.List;

public class Relations {
	
	private int id;
	private int main_id;
	private int relations_id;
	
	private int parent;
	private int tree;
	
	private String main_doc_code;
	private String relations_doc_code;
	private String main_doc_name;
	private String relations_doc_name;
	private String profile_code;
	private String profile_name;
	private int processes_id;
	private String closing_status;
	
	
	private List<Relations> Relations;
	private Relations Relation;
	
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
	public int getRelations_id() {
		return relations_id;
	}
	public void setRelations_id(int relations_id) {
		this.relations_id = relations_id;
	}
	public String getMain_doc_code() {
		return main_doc_code;
	}
	public void setMain_doc_code(String main_doc_code) {
		this.main_doc_code = main_doc_code;
	}
	public String getRelations_doc_code() {
		return relations_doc_code;
	}
	public void setRelations_doc_code(String relations_doc_code) {
		this.relations_doc_code = relations_doc_code;
	}
	public String getMain_doc_name() {
		return main_doc_name;
	}
	public void setMain_doc_name(String main_doc_name) {
		this.main_doc_name = main_doc_name;
	}
	public String getRelations_doc_name() {
		return relations_doc_name;
	}
	public void setRelations_doc_name(String relations_doc_name) {
		this.relations_doc_name = relations_doc_name;
	}
	public String getProfile_code() {
		return profile_code;
	}
	public void setProfile_code(String profile_code) {
		this.profile_code = profile_code;
	}
	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public List<Relations> getRelations() {
		return Relations;
	}
	public void setRelations(List<Relations> relations) {
		Relations = relations;
	}
	public Relations getRelation() {
		return Relation;
	}
	public void setRelation(Relations relation) {
		Relation = relation;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getTree() {
		return tree;
	}
	public void setTree(int tree) {
		this.tree = tree;
	}
	public int getProcesses_id() {
		return processes_id;
	}
	public void setProcesses_id(int processes_id) {
		this.processes_id = processes_id;
	}
	public String getClosing_status() {
		return closing_status;
	}
	public void setClosing_status(String closing_status) {
		this.closing_status = closing_status;
	}
	
	
	
	
}