package com.smepro.models;

public class comment {
	
	private int comment_id;
	private int comment_idrefer;
	private String comment_comment;
	private String comment_name;
	private String comment_date;
	private String comment_note;
	private String comment_type;
	
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getComment_comment() {
		return comment_comment;
	}
	public void setComment_comment(String comment_comment) {
		this.comment_comment = comment_comment;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_note() {
		return comment_note;
	}
	public void setComment_note(String comment_note) {
		this.comment_note = comment_note;
	}

	public String getComment_type() {
		return comment_type;
	}
	public void setComment_type(String comment_type) {
		this.comment_type = comment_type;
	}
	public int getComment_idrefer() {
		return comment_idrefer;
	}
	public void setComment_idrefer(int comment_idrefer) {
		this.comment_idrefer = comment_idrefer;
	}
	
	
	@Override
	public String toString() {
		return String.format(
				"comment [comment_id=%s, comment_idrefer=%s, comment_comment=%s, comment_name=%s, comment_date=%s, comment_note=%s, comment_type=%s]",
				comment_id, comment_idrefer, comment_comment, comment_name, comment_date, comment_note, comment_type);
	}

	
	

	
	
}
