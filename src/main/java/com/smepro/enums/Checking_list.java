package com.smepro.enums;

public enum Checking_list {
	
	checking_register_stock("ข้อมูลการตรวจการขอขึ้นทะเบียนสต๊อก")
	
	;


    private final String label;

    Checking_list(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



