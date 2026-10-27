package com.smepro.enums;

public enum FGMovementType {
	
	   RECEIPT_FG("รับเข้า FG"),
	   TRANSFER_FG("ย้ายที่เก็บ FG"),
	   ADJUST_FG("ปรับสต็อก FG"),
	   ISSUE_FG("เบิกออก FG");


    private final String label;

    FGMovementType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



