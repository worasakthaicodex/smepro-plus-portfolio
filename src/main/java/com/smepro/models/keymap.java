package com.smepro.models;

import java.util.List;

public class keymap {

	private int id;
	private int sub_processes_id;
	private int processes_id;
	private String mainkey;
	private String eformkey;
	private String change_format;
	private String enum_id;
	private String filter_enum;
	private String type;
	private String title;
	private String registerkey_id;
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSub_processes_id() {
		return sub_processes_id;
	}
	public void setSub_processes_id(int sub_processes_id) {
		this.sub_processes_id = sub_processes_id;
	}
	public int getProcesses_id() {
		return processes_id;
	}
	public void setProcesses_id(int processes_id) {
		this.processes_id = processes_id;
	}
	public String getMainkey() {
		return mainkey;
	}
	public void setMainkey(String mainkey) {
		this.mainkey = mainkey;
	}
	public String getEformkey() {
		return eformkey;
	}
	public void setEformkey(String eformkey) {
		this.eformkey = eformkey;
	}
	public String getChange_format() {
		return change_format;
	}
	public void setChange_format(String change_format) {
		this.change_format = change_format;
	}
	public String getEnum_id() {
		return enum_id;
	}
	public void setEnum_id(String enum_id) {
		this.enum_id = enum_id;
	}
	public String getFilter_enum() {
		return filter_enum;
	}
	public void setFilter_enum(String filter_enum) {
		this.filter_enum = filter_enum;
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
	public String getRegisterkey_id() {
		return registerkey_id;
	}
	public void setRegisterkey_id(String registerkey_id) {
		this.registerkey_id = registerkey_id;
	}
	@Override
	public String toString() {
		return String.format(
				"keymap [id=%s, sub_processes_id=%s, processes_id=%s, mainkey=%s, eformkey=%s, change_format=%s, enum_id=%s, filter_enum=%s, type=%s, title=%s, registerkey_id=%s]",
				id, sub_processes_id, processes_id, mainkey, eformkey, change_format, enum_id, filter_enum, type, title,
				registerkey_id);
	}
	
	
}
