package com.smepro.enums;

public enum notif {
	
	reject_app("ไม่อนุมัติเอกสาร application"),
	reject_plan("ไม่อนุมัติเอกสาร แผนการติดตามเอกสาร");


    private final String label;

    notif(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



