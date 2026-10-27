package com.smepro.enums;

public enum Actiontype {
	
	Pass("ผ่าน"),
	Reject("ไม่ผ่าน");


    private final String label;

    Actiontype(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



