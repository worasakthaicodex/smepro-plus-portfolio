package com.smepro.enums;


public enum Regkey {
	Label("ป้ายกำกับ"),
    Enum("ชุดข้อมูล"),
    Register("ชุดข้อมูลหลัก")
	;
    private final String label;

    Regkey(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
