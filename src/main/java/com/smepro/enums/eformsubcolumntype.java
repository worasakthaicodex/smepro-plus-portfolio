package com.smepro.enums;

public enum eformsubcolumntype {
	
	
	product_received("การระบุว่าเป็นการผลิตหรือการจัดเก็บสิ่งใด"),
	lot_received("การเเยก LOT"),  
	use_received("การใช้ยอดรับไป"),  
	handover_received("การส่งต่อยอดที่รับมาไปอีกทอดนึ่ง"),  
	received("ยอดรับ");  // ทำให้รู้ว่าใครเบิกอะไรไปบ้าง;


    private final String label;

    eformsubcolumntype(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



