package com.smepro.models;

public class backup {
	
	private int backup_id;
	private String backup_date;
	private String backup_service;
	private String backup_note;
	private String backup_name;
	public int getBackup_id() {
		return backup_id;
	}
	public void setBackup_id(int backup_id) {
		this.backup_id = backup_id;
	}
	public String getBackup_date() {
		return backup_date;
	}
	public void setBackup_date(String backup_date) {
		this.backup_date = backup_date;
	}
	public String getBackup_service() {
		return backup_service;
	}
	public void setBackup_service(String backup_service) {
		this.backup_service = backup_service;
	}
	public String getBackup_note() {
		return backup_note;
	}
	public void setBackup_note(String backup_note) {
		this.backup_note = backup_note;
	}
	public String getBackup_name() {
		return backup_name;
	}
	public void setBackup_name(String backup_name) {
		this.backup_name = backup_name;
	}
	@Override
	public String toString() {
		return String.format("backup [backup_id=%s, backup_date=%s, backup_service=%s, backup_note=%s, backup_name=%s]",
				backup_id, backup_date, backup_service, backup_note, backup_name);
	}
	
	

	
	
}
