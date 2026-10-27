package com.smepro.enums;

public enum SKMovementType {
	
	   RECEIPT_SK("รับเข้า SK"),
	   TRANSFER_SK("ย้ายที่เก็บ SK"),
	   ADJUST_SK("ปรับสต็อก SK"),
	   ISSUE_SK("เบิกออก SK");


    private final String label;

    SKMovementType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



