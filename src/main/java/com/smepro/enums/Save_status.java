package com.smepro.enums;


public enum Save_status {
	Save_Sucess("บันทึกสำเร็จ"),
    Sys_Failed("บันทึกไม่สำเร็จ"),
	Sys_Failed_PD_BATCH("บันทึกไม่สำเร็จ ไม่พบค่า PD หรือ Batch"),
	Sys_Failed_customer_Null("บันทึกไม่สำเร็จ ไม่พบลูกค้ารายนี้"),
	Sys_Failed_PD("บันทึกไม่สำเร็จ ไม่พบค่า PD"),
	Sys_Failed_BATCH("บันทึกไม่สำเร็จ ไม่พบค่า BATCH"),
	Sys_Failed_production_Null("บันทึกไม่สำเร็จ ไม่พบค่า production"),
	Sys_Failed_material_Null("บันทึกไม่สำเร็จ ไม่พบค่า material"),
	Sys_Failed_User_clearing_Null("ไม่พบค่า clearing ถูกกำหนดในเเผนงาน"),
	Sys_Failed_Stock_Duplicate("บันทึกไม่สำเร็จ Stock ต้องไม่มีการซ้ำ"),
	Sys_Failed_Register_Duplicate("บันทึกไม่สำเร็จ register ต้องไม่มีการซ้ำ"),
	Sys_Failed_BATCH_Duplicate("บันทึกไม่สำเร็จ BATCH ต้องไม่มีการซ้ำ"),
	Sys_Failed_Stock("บันทึกไม่สำเร็จ ไม่พบค่า Stock"),
	Sys_Failed_Stock_total("บันทึกไม่สำเร็จ ไม่พบค่า Stock หรือ จำนวนคงเหลือน้อยกว่าที่ต้องการ"),
	
	Sys_Failed_Workrule("บันทึกไม่สำเร็จ ไม่พบค่า Workrule"),
	Sys_Failed_Workrule_Duplicate("บันทึกไม่สำเร็จ Workrule ต้องไม่มีการซ้ำ"),
	
	Sys_Failed_Assets("บันทึกไม่สำเร็จ ไม่พบค่า Assets"),
	Sys_Failed_Assets_Duplicate("บันทึกไม่สำเร็จ Assets ต้องไม่มีการซ้ำ"),
	
	Sys_Failed_Annual_plan("บันทึกไม่สำเร็จ ไม่พบค่า Annual_plan"),
	Sys_Failed_Annual_plan_Duplicate("บันทึกไม่สำเร็จ Annual_plan ต้องไม่มีการซ้ำ");
	
	
    private final String label;

    Save_status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
