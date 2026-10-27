package com.smepro.models;

public class Outcome {

	private int id;
	private int project_id;
	private String expected_results;
	private String success_indicators;
	private String metric;
	private int task;
	private int process;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpected_results() {
		return expected_results;
	}
	public void setExpected_results(String expected_results) {
		this.expected_results = expected_results;
	}
	public String getSuccess_indicators() {
		return success_indicators;
	}
	public void setSuccess_indicators(String success_indicators) {
		this.success_indicators = success_indicators;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public int getTask() {
		return task;
	}
	public void setTask(int task) {
		this.task = task;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	
	
	
}
