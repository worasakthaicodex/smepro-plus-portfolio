package com.smepro.enums;

public enum Frequency {
	
	
	IRREGULAR("ไม่กำหนดเวลาแน่นอน"),
	EVERY_DAY("ทุกวัน"),
	EVERY_MONTH("ทุกเดือน"),
	EVERY_WEEK("ทุกสัปดาห์"),
	EVERY_YEAR("ทุกปี");


    private final String label;

    Frequency(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



