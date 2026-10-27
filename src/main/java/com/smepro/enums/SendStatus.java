package com.smepro.enums;

public enum SendStatus {
	
	SEND("ส่ง"),
	REJECTED("ถูกปฏิเสธ");


    private final String label;

    SendStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



