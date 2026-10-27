package com.smepro.enums;



public enum CoreFields {
	 Data_Lookup("ค้นหาข้อมูล")
	,Doc_Code_Generator("สร้างรหัสเอกสาร")
    ,Entry_Table("ตารางรายการ")
    ,Split_Items("แยกรายการ")
    ;

	
	
	
	
    private final String label;

    CoreFields(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
