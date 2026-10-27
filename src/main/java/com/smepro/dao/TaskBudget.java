package com.smepro.dao;

public class TaskBudget {
	
	private int id;
	private int master_budget_id;
	private int project_id;
	private String detail;
	private Double budget;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaster_budget_id() {
		return master_budget_id;
	}
	public void setMaster_budget_id(int master_budget_id) {
		this.master_budget_id = master_budget_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}

	
	
	
}
