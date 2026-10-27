package com.smepro.models;

public class Measurement_rules {
	
	private int id;
	private int this_process;
	private int destination_process;
	private String title;
	private String this_closing_status;
	private String destination_closing_status;
	private String this_target;
	private String destination_column_operation;
	private int work_cycle;  //0 Trigger, 1 Daily
	private int rules;  //0 count , ( 1 sum, 2 avg, 3 max, 4 min )destination_column_operation
	private  Measurement_values values;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getThis_process() {
		return this_process;
	}
	public void setThis_process(int this_process) {
		this.this_process = this_process;
	}
	public int getDestination_process() {
		return destination_process;
	}
	public void setDestination_process(int destination_process) {
		this.destination_process = destination_process;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThis_closing_status() {
		return this_closing_status;
	}
	public void setThis_closing_status(String this_closing_status) {
		this.this_closing_status = this_closing_status;
	}
	public String getDestination_closing_status() {
		return destination_closing_status;
	}
	public void setDestination_closing_status(String destination_closing_status) {
		this.destination_closing_status = destination_closing_status;
	}
	public String getThis_target() {
		return this_target;
	}
	public void setThis_target(String this_target) {
		this.this_target = this_target;
	}
	public String getDestination_column_operation() {
		return destination_column_operation;
	}
	public void setDestination_column_operation(String destination_column_operation) {
		this.destination_column_operation = destination_column_operation;
	}
	public int getWork_cycle() {
		return work_cycle;
	}
	public void setWork_cycle(int work_cycle) {
		this.work_cycle = work_cycle;
	}
	public int getRules() {
		return rules;
	}
	public void setRules(int rules) {
		this.rules = rules;
	}
	public Measurement_values getValues() {
		return values;
	}
	public void setValues(Measurement_values values) {
		this.values = values;
	}
	
	
	
	

	
}
