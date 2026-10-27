package com.smepro.models;

public class TaskOutcome {
	
	private int id;
	private int master_outcome_id;
	private int project_id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaster_outcome_id() {
		return master_outcome_id;
	}
	public void setMaster_outcome_id(int master_outcome_id) {
		this.master_outcome_id = master_outcome_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	
	
	
}


/*
Personnel (ค่าแรง)

Materials (วัสดุอุปกรณ์)

Equipment (เครื่องจักร/อุปกรณ์)

Travel (เดินทาง)

Miscellaneous (เบ็ดเตล็ด)

*/