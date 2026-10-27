package com.smepro.enums;


public enum Traceability{
	
	general("ทั่วไป"),
	preparation_material("การเตรียมวัตถุดิบ"),
	material("การรับวัตถุดิบ"),
	production("กระบวนการผลิต"),
	use_material("การใช้วัตถุดิบ"),
	issue_request("ใบเบิก"),
	purchase_requisition("การขอซื้อ"),
	purchase_order("การสั่งซื้อ"),
	goods_receipt("ใบรับสินค้า"),
	fg("สินค้าสำเร็จรูป"),
	requisition_form("ใบเบิก"),
	petty_cash_book("สมุดบันทึกเงินสดย่อย");
	
	
	
	
	
    private final String label;
    Traceability (String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
   
    
}
