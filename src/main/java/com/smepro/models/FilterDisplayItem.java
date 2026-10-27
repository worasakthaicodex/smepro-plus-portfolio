package com.smepro.models;

import java.util.Map;

public class FilterDisplayItem {
	private String type;        // เก็บว่าตัวนี้คือ "RULE" (ข้อมูล) หรือ "OPERATOR" (ตัวเชื่อม)
    private String operator;    // เก็บค่า "AND" หรือ "OR" (ถ้าเป็น OPERATOR)
    private Map<String, Object> ruleData; // เก็บข้อมูล JSON ทั้งก้อน (ถ้าเป็น RULE)

    // Constructor เปล่า
    public FilterDisplayItem() {}

    // Constructor สำหรับสร้างตัวเชื่อม (AND/OR)
    public FilterDisplayItem(String operator) {
        this.type = "OPERATOR";
        this.operator = operator;
    }

    // Constructor สำหรับสร้างข้อมูล (Rule)
    public FilterDisplayItem(Map<String, Object> ruleData) {
        this.type = "RULE";
        this.ruleData = ruleData;
    }

    // Getters & Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
    
    public Map<String, Object> getRuleData() { return ruleData; }
    public void setRuleData(Map<String, Object> ruleData) { this.ruleData = ruleData; }

    
    
	@Override
	public String toString() {
		return String.format("FilterDisplayItem [type=%s, operator=%s, ruleData=%s]", type, operator, ruleData);
	}
    
    
    
}
