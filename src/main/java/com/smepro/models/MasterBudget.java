package com.smepro.models;

public class MasterBudget {

	private int id;
	private int project_id;
	private String detail;
	private String category;
	private Double budget;
	private Double actual;
	private Double variance;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public Double getActual() {
		return actual;
	}
	public void setActual(Double actual) {
		this.actual = actual;
	}
	public Double getVariance() {
		return variance;
	}
	public void setVariance(Double variance) {
		this.variance = variance;
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