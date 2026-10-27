package com.smepro.enums;

public enum WorkruleType {
   
	
	SHIFT("ตารางกะ"),
    HOLIDAY("วันหยุด"),
    DEDUCTION("การหัก"),
    TRAINING("แผนอบรม");

    private final String label;

    WorkruleType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}