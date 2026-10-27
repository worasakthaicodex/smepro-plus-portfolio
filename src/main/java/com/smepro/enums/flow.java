package com.smepro.enums;

public enum flow {
	
	Receiver("ผู้รับ"),
	Sender("ผู้ส่ง"),
	Creator("ผู้สร้าง"),
	to_review("ส่งตรวจ"),
	reject("ไม่อนุมัติ"),
	to_review_edit("ส่งเอกสารเเก้ไขไปตรวจ"),
	to_cleared("ส่งไปเครียร์"),
	publish("เสร็จสิ้น");


    private final String label;

    flow(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



