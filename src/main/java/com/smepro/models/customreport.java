package com.smepro.models;

import java.util.List;

public class customreport {

	private int id;
	private String report_name;
	private String report_description;
	private int report_table;
	private int report_app;
	private int type;
	private String level;
	private String analysis;
	private String filter;
	private String axis_x;
	private String axis_y;
	private int status;
	private String filter_data;
	private String date_group;
	private String filter_date;
	List<eformfield> eformfield;
	
    
	private String condition_choice1;     
	private String filter_column1;
	private String operation1;
	private String condition_value1;

	private String condition_choice2;     
	private String filter_column2;
	private String operation2;
	private String condition_value2;
	
	private String condition_choice3;     
	private String filter_column3;
	private String operation3;
	private String condition_value3;
	private String  theme;
	private int display;
	
	private int binning;
	private String binning_range;
	private String binning_title;
	private String binning_group;
	private String binning_date;
	private String binning_operation;
	private String binning_value;
	
    public int planId;
    public String department;
    public String machineryId;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getReport_description() {
		return report_description;
	}
	public void setReport_description(String report_description) {
		this.report_description = report_description;
	}
	public int getReport_table() {
		return report_table;
	}
	public void setReport_table(int report_table) {
		this.report_table = report_table;
	}
	public int getReport_app() {
		return report_app;
	}
	public void setReport_app(int report_app) {
		this.report_app = report_app;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getAxis_x() {
		return axis_x;
	}
	public void setAxis_x(String axis_x) {
		this.axis_x = axis_x;
	}
	public String getAxis_y() {
		return axis_y;
	}
	public void setAxis_y(String axis_y) {
		this.axis_y = axis_y;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFilter_data() {
		return filter_data;
	}
	public void setFilter_data(String filter_data) {
		this.filter_data = filter_data;
	}
	public String getDate_group() {
		return date_group;
	}
	public void setDate_group(String date_group) {
		this.date_group = date_group;
	}
	public List<eformfield> getEformfield() {
		return eformfield;
	}
	public void setEformfield(List<eformfield> eformfield) {
		this.eformfield = eformfield;
	}
	public String getFilter_date() {
		return filter_date;
	}
	public void setFilter_date(String filter_date) {
		this.filter_date = filter_date;
	}
	public String getCondition_choice3() {
		return condition_choice3;
	}
	public void setCondition_choice3(String condition_choice3) {
		this.condition_choice3 = condition_choice3;
	}
	public String getFilter_column3() {
		return filter_column3;
	}
	public void setFilter_column3(String filter_column3) {
		this.filter_column3 = filter_column3;
	}
	public String getOperation3() {
		return operation3;
	}
	public void setOperation3(String operation3) {
		this.operation3 = operation3;
	}
	public String getCondition_value3() {
		return condition_value3;
	}
	public void setCondition_value3(String condition_value3) {
		this.condition_value3 = condition_value3;
	}
	public String getCondition_choice1() {
		return condition_choice1;
	}
	public void setCondition_choice1(String condition_choice1) {
		this.condition_choice1 = condition_choice1;
	}
	public String getFilter_column1() {
		return filter_column1;
	}
	public void setFilter_column1(String filter_column1) {
		this.filter_column1 = filter_column1;
	}
	public String getOperation1() {
		return operation1;
	}
	public void setOperation1(String operation1) {
		this.operation1 = operation1;
	}
	public String getCondition_value1() {
		return condition_value1;
	}
	public void setCondition_value1(String condition_value1) {
		this.condition_value1 = condition_value1;
	}
	public String getCondition_choice2() {
		return condition_choice2;
	}
	public void setCondition_choice2(String condition_choice2) {
		this.condition_choice2 = condition_choice2;
	}
	public String getFilter_column2() {
		return filter_column2;
	}
	public void setFilter_column2(String filter_column2) {
		this.filter_column2 = filter_column2;
	}
	public String getOperation2() {
		return operation2;
	}
	public void setOperation2(String operation2) {
		this.operation2 = operation2;
	}
	public String getCondition_value2() {
		return condition_value2;
	}
	public void setCondition_value2(String condition_value2) {
		this.condition_value2 = condition_value2;
	}
	public int getBinning() {
		return binning;
	}
	public void setBinning(int binning) {
		this.binning = binning;
	}
	public String getBinning_range() {
		return binning_range;
	}
	public void setBinning_range(String binning_range) {
		this.binning_range = binning_range;
	}
	public String getBinning_title() {
		return binning_title;
	}
	public void setBinning_title(String binning_title) {
		this.binning_title = binning_title;
	}
	public String getBinning_group() {
		return binning_group;
	}
	public void setBinning_group(String binning_group) {
		this.binning_group = binning_group;
	}
	public String getBinning_date() {
		return binning_date;
	}
	public void setBinning_date(String binning_date) {
		this.binning_date = binning_date;
	}
	public String getBinning_operation() {
		return binning_operation;
	}
	public void setBinning_operation(String binning_operation) {
		this.binning_operation = binning_operation;
	}
	public String getBinning_value() {
		return binning_value;
	}
	public void setBinning_value(String binning_value) {
		this.binning_value = binning_value;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMachineryId() {
		return machineryId;
	}
	public void setMachineryId(String machineryId) {
		this.machineryId = machineryId;
	}
	
	
	
	
	
	
	
	
	public void Filter(customreport customreports) {
		// TODO Auto-generated method stub
		  
		this.filter_column1 = customreports.getFilter_column1();
		this.filter_column2 = customreports.getFilter_column2();
		this.filter_column3 = customreports.getFilter_column3();
		
		this.operation1 = customreports.getOperation1();
		this.operation2 = customreports.getOperation2();
		this.operation3 = customreports.getOperation3();
		
		
		this.condition_choice1 = customreports.getCondition_choice1();
		this.condition_choice2 = customreports.getCondition_choice2();
		this.condition_choice3 = customreports.getCondition_choice3();
		
		
		this.condition_value1 = customreports.getCondition_value1();
		this.condition_value2 = customreports.getCondition_value2();
		this.condition_value3 = customreports.getCondition_value3();
		

	}

	
	
	
	
	/*
	Group By  Top/Bottom นับจำนวน (Count) หรือ Count พร้อม Min/Max
	Comparison
*/






}
