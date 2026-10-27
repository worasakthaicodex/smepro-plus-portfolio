package com.smepro.enums;

public enum Followup{

	    IN_PROGRESS("ระหว่างดำเนินการ"),
	    NEAR_DUE("ใกล้ถึงกำหนด"),
	    NOT_PLANNED("ยังไม่อยู่ในแผนดำเนินการ"),
	    OVERDUE("ล่าช้ากว่าแผนดำเนินการ");

	    private final String label;

	    Followup(String label) {
	        this.label = label;
	    }

	    public String getLabel() {
	        return label;
	    }
	    
}