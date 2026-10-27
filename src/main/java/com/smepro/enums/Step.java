package com.smepro.enums;



public enum Step {
	Create("กรอกข้อมูล / สร้างรายการใหม่")
	,Review("การตรวจสอบ")
    ,Approval("การตัดสินใจ")
    ,Proceed("ดำเนินการต่อ")
    ,Completion("จบกระบวนการ")
    
    //,Follow_up("การติดตามผล")
    //,Record("ถ้ามีการเก็บข้อมูลถาวร")
    //,Forward("ส่งเอกสารไปยังระบบอื่น")
    // ,Receive("รับเอกสารหรือข้อมูลจากระบบอื่น")
    ;

	
	
	
	
    private final String label;

    Step(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
