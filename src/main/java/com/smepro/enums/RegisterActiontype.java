package com.smepro.enums;

public enum RegisterActiontype {
	
	CREATE_REQUEST("ขอสร้าง"),
	UPDATE_REQUEST("ขออัพเดต"),
	DELETE_REQUEST("ขอลบ");


    private final String label;

    RegisterActiontype(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

