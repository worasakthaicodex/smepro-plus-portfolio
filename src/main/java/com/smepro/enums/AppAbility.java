package com.smepro.enums;

public enum AppAbility {
	
	register_stock("ลงทะเบียนสต๊อก"),
	update_register_stock("ปรับปรุงทะเบียนสต๊อก"),
	register_list("ลงทะเบียนรายชื่อ"),
	update_register_list("ปรับปรุงทะเบียนรายชื่อ"),
	register_product("ลงทะเบียนสินค้า"),
	update_register_product("ปรับปรุงทะเบียนสินค้า"),
	register_assets("ลงทะเบียนสินทรัพย์"),
	update_register_assets("ปรับปรุงทะเบียนสินทรัพย์"),
	register_annual_plan("ลงทะเบียนแผนประจำปี"),
	update_register_annual_plan("ปรับปรุงทะเบียนแผนประจำปี"),
	register_workrule("ลงทะเบียนระเบียนงาน"),
	update_register_workrule("ปรับปรุงทะเบียนระเบียนงาน"),
	
	
	set_poin("ให้คะเเนนรายชื่อ"),
	set_income_stock("รับเข้าสต๊อก"),
	
	issued_fg("การเบิก FG ออกจากสต็อกเพื่อขายหรือใช้"),
	adjustment_fg("การปรับปรุงสต็อก เช่น กรณีสูญหายหรือเสียหาย"),
	goods_receipt_fg("เมื่อ FG ถูกโอนจากกระบวนการผลิต (Work Order Completion) เข้าสู่สต็อก"),
	inventory_transfer_fg("การย้าย FG ระหว่างตำแหน่งจัดเก็บหรือโกดัง"),
	
	issued_sk("การเบิก"),
	adjustment_sk("การปรับปรุงสต็อก"),
	goods_receipt_sk("รับเข้าสู่สต็อก"),
	inventory_transfer_sk("การย้าย")
	;
	
	
	
	
	
    private final String label;

    AppAbility(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



