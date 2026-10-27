package com.smepro.enums;

public enum AnnualPlanType {
	
	   JOB_COUNT("จำนวนงาน"),
	    PERSON_COUNT("จำนวนคน"),
	    PRODUCTION_OUTPUT("ผลผลิต"),
	    EXPENSE("ค่าใช้จ่าย"),
	    MACHINE_EFFICIENCY("ประสิทธิภาพเครื่องจักร"),
	    GENERAL("ทั่วๆไป");
	
	
    private final String label;

    AnnualPlanType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



