package com.smepro.models;

public class policies {
	
	
	private int id;
	private String group_id;
	private String	name;
	private int	sub_processes;
	private int	process;
	private String	respon;
	private String	max_approval_amount;
	private String	sub_processes_string;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSub_processes() {
		return sub_processes;
	}
	public void setSub_processes(int sub_processes) {
		this.sub_processes = sub_processes;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public String getRespon() {
		return respon;
	}
	public void setRespon(String respon) {
		this.respon = respon;
	}
	public String getMax_approval_amount() {
		return max_approval_amount;
	}
	public void setMax_approval_amount(String max_approval_amount) {
		this.max_approval_amount = max_approval_amount;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getSub_processes_string() {
		return sub_processes_string;
	}
	public void setSub_processes_string(String sub_processes_string) {
		this.sub_processes_string = sub_processes_string;
	}
	@Override
	public String toString() {
		return String.format(
				"policies [id=%s, group_id=%s, name=%s, sub_processes=%s, process=%s, respon=%s, max_approval_amount=%s, sub_processes_string=%s]",
				id, group_id, name, sub_processes, process, respon, max_approval_amount, sub_processes_string);
	}
	
	
	
	
	

}
