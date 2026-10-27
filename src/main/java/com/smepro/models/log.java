package com.smepro.models;

public class log  extends file{
	private int log_id;
	private int log_id_add;
	private String log_date;
	private String log_time;
	private String log_step;
	private String log_refer;
	private String log_folder;
	private String log_note;
	private String log_file;
	private String log_dive;
	private String log_due;
	private int log_flow;
	private String log_comment;
	private int log_darrefer;
	private String log_readtime;
	private String log_datestart;
	private String log_dateend;
	private String log_timestart;
	private String log_timeend;
	
	private String log_app;
	private int log_plan;
	private int log_code;
	
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public int getLog_id_add() {
		return log_id_add;
	}
	public void setLog_id_add(int log_id_add) {
		this.log_id_add = log_id_add;
	}
	public String getLog_date() {
		return log_date;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public String getLog_time() {
		return log_time;
	}
	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}
	public String getLog_step() {
		return log_step;
	}
	public void setLog_step(String log_step) {
		this.log_step = log_step;
	}
	public String getLog_refer() {
		return log_refer;
	}
	public void setLog_refer(String log_refer) {
		this.log_refer = log_refer;
	}
	public String getLog_folder() {
		return log_folder;
	}
	public void setLog_folder(String log_folder) {
		this.log_folder = log_folder;
	}
	public String getLog_note() {
		return log_note;
	}
	public void setLog_note(String log_note) {
		this.log_note = log_note;
	}
	public String getLog_file() {
		return log_file;
	}
	public void setLog_file(String log_file) {
		this.log_file = log_file;
	}
	public String getLog_dive() {
		return log_dive;
	}
	public void setLog_dive(String log_dive) {
		this.log_dive = log_dive;
	}
	public String getLog_due() {
		return log_due;
	}
	public void setLog_due(String log_due) {
		this.log_due = log_due;
	}
	public int getLog_flow() {
		return log_flow;
	}
	public void setLog_flow(int log_flow) {
		this.log_flow = log_flow;
	}
	public String getLog_comment() {
		return log_comment;
	}
	public void setLog_comment(String log_comment) {
		this.log_comment = log_comment;
	}
	public int getLog_darrefer() {
		return log_darrefer;
	}
	public void setLog_darrefer(int log_darrefer) {
		this.log_darrefer = log_darrefer;
	}
	public String getLog_datestart() {
		return log_datestart;
	}
	public void setLog_datestart(String log_datestart) {
		this.log_datestart = log_datestart;
	}
	public String getLog_dateend() {
		return log_dateend;
	}
	public void setLog_dateend(String log_dateend) {
		this.log_dateend = log_dateend;
	}
	public String getLog_timestart() {
		return log_timestart;
	}
	public void setLog_timestart(String log_timestart) {
		this.log_timestart = log_timestart;
	}
	public String getLog_timeend() {
		return log_timeend;
	}
	public void setLog_timeend(String log_timeend) {
		this.log_timeend = log_timeend;
	}
	public String getLog_readtime() {
		return log_readtime;
	}
	public void setLog_readtime(String log_readtime) {
		this.log_readtime = log_readtime;
	}
	public String getLog_app() {
		return log_app;
	}
	public void setLog_app(String log_app) {
		this.log_app = log_app;
	}
	public int getLog_plan() {
		return log_plan;
	}
	public void setLog_plan(int log_plan) {
		this.log_plan = log_plan;
	}
	public int getLog_code() {
		return log_code;
	}
	public void setLog_code(int log_code) {
		this.log_code = log_code;
	}
	
	@Override
	public String toString() {
		return String.format(
				"log [log_id=%s, log_id_add=%s, log_date=%s, log_time=%s, log_step=%s, log_refer=%s, log_folder=%s, log_note=%s, log_file=%s, log_dive=%s, log_due=%s, log_flow=%s, log_comment=%s, log_darrefer=%s, log_readtime=%s, log_datestart=%s, log_dateend=%s, log_timestart=%s, log_timeend=%s, log_app=%s]",
				log_id, log_id_add, log_date, log_time, log_step, log_refer, log_folder, log_note, log_file, log_dive,
				log_due, log_flow, log_comment, log_darrefer, log_readtime, log_datestart, log_dateend, log_timestart,
				log_timeend, log_app);
	}
	
	
	
}
