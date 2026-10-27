package com.smepro.enums;

public enum Dataset {
	
	Unit("หน่วยนับ")
	,Storage("พื้นที่จัดเก็บ")
	,Stock_Type("ประเภท")
	,Drawer("ผู้ขอเบิก");
	

    private final String label;

    Dataset(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



