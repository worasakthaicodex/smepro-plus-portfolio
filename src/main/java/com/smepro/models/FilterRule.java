package com.smepro.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilterRule {
   
	@JsonProperty("id")
	private int id;
    
    @JsonProperty("column_id")
    private String columnId; // ชื่อ field ใน java class
    
    private String condition; // Start, AND, OR
    
    @JsonProperty("column_tag")
    private String columnTag;
    
    private String operation; // =, !=, >, <, contains
    
    private String value;
    private String valueselected;
    private String type;

    @JsonProperty("group")
    private int group; // เพิ่มตัวนี้เข้ามา
    
    // Getters and Setters ...
    public int getId() { return id; }
    public int getGroup() { return group; }
    public String getColumnId() { return columnId; }
    public String getCondition() { return condition; }
    public String getOperation() { return operation; }
    public String getValue() { return value; }
    public String getValueselected() { return valueselected; }
    public String getType() { return type; }
	public void setValue(String value) {
		this.value = value;
	}
    
    
    
    
}
