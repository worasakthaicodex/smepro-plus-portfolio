package com.smepro.models;

public class FieldChange {
	private String fieldName; // ชื่อฟิลด์
    private String oldValue;  // ค่าเดิม (Register)
    private String newValue;  // ค่าใหม่ (EdataRegister)

    public FieldChange(String fieldName, String oldValue, String newValue) {
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Getters Setters...
    public String getFieldName() { return fieldName; }
    public String getOldValue() { return oldValue; }
    public String getNewValue() { return newValue; }
}
