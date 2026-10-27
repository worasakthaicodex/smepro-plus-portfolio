package com.smepro.models;

import java.util.List;

public class registerkey {

	
	private int id;
	private int app_id;
    private String mainkey;
    private String type;
    private String title;
    private int required;
    private String source;
    private  keymap keymap;
    private String doc;
    private String action_type;
    private String keygroups;
    
    
    
    private List<dataset_data> tag;
    private int enum_id;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getMainkey() {
		return mainkey;
	}
	public void setMainkey(String mainkey) {
		this.mainkey = mainkey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRequired() {
		return required;
	}
	public void setRequired(int required) {
		this.required = required;
	}
	public List<dataset_data> getTag() {
		return tag;
	}
	public void setTag(List<dataset_data> tag) {
		this.tag = tag;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getEnum_id() {
		return enum_id;
	}
	public void setEnum_id(int enum_id) {
		this.enum_id = enum_id;
	}
	public keymap getKeymap() {
		return keymap;
	}
	public void setKeymap(keymap keymap) {
		this.keymap = keymap;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getKeygroups() {
		return keygroups;
	}
	public void setKeygroups(String keygroups) {
		this.keygroups = keygroups;
	}
	
	
}
