package com.smepro.models;

public class notification {
	
	
	private int notification_id;
	private String notification_alert;
	private String notification_iduser;
	private String notification_iduser2;
	private String notification_type;
	private int notification_read;
	private String notification_date;
	private String notification_refer;
	private String notification_note;
	
	
	private String notification_department_id;
	private String notification_position_id;
	private int notification_process_id;
	
	
	public int getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(int notification_id) {
		this.notification_id = notification_id;
	}
	public String getNotification_alert() {
		return notification_alert;
	}
	public void setNotification_alert(String notification_alert) {
		this.notification_alert = notification_alert;
	}
	public String getNotification_iduser() {
		return notification_iduser;
	}
	public void setNotification_iduser(String notification_iduser) {
		this.notification_iduser = notification_iduser;
	}
	public String getNotification_iduser2() {
		return notification_iduser2;
	}
	public void setNotification_iduser2(String notification_iduser2) {
		this.notification_iduser2 = notification_iduser2;
	}
	public String getNotification_type() {
		return notification_type;
	}
	public void setNotification_type(String notification_type) {
		this.notification_type = notification_type;
	}
	public int getNotification_read() {
		return notification_read;
	}
	public void setNotification_read(int notification_read) {
		this.notification_read = notification_read;
	}
	public String getNotification_date() {
		return notification_date;
	}
	public void setNotification_date(String notification_date) {
		this.notification_date = notification_date;
	}
	public String getNotification_refer() {
		return notification_refer;
	}
	public void setNotification_refer(String notification_refer) {
		this.notification_refer = notification_refer;
	}
	public String getNotification_note() {
		return notification_note;
	}
	public void setNotification_note(String notification_note) {
		this.notification_note = notification_note;
	}
	public String getNotification_department_id() {
		return notification_department_id;
	}
	public void setNotification_department_id(String notification_department_id) {
		this.notification_department_id = notification_department_id;
	}
	public String getNotification_position_id() {
		return notification_position_id;
	}
	public void setNotification_position_id(String notification_position_id) {
		this.notification_position_id = notification_position_id;
	}
	public int getNotification_process_id() {
		return notification_process_id;
	}
	public void setNotification_process_id(int notification_process_id) {
		this.notification_process_id = notification_process_id;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"notification [notification_id=%s, notification_alert=%s, notification_iduser=%s, notification_iduser2=%s, notification_type=%s, notification_read=%s, notification_date=%s, notification_refer=%s, notification_note=%s]",
				notification_id, notification_alert, notification_iduser, notification_iduser2, notification_type,
				notification_read, notification_date, notification_refer, notification_note);
	}
	
	
	
}
