package com.smepro.enums;

public enum Stock_status {
	
	IN_STOCK("มีสินค้าในสต็อกพร้อมใช้งาน")
	,OUT_OF_STOCK("สินค้าหมด ไม่มีของในสต็อก")
	,LOW_STOCK("สต็อกใกล้หมด")
	,HEIGHT_STOCK("สต็อกมากเกินไป")
	,RESERVED("สินค้าถูกจองไว้ ยังไม่ได้เบิก")
	,DAMAGED("สินค้าเสียหาย ไม่สามารถใช้งานได้")
	,EXPIRED("สินค้าหมดอายุ ไม่สามารถใช้งานได้")
	
	
	,PENDING_RECEIPT("สินค้าอยู่ระหว่างรอรับเข้า")
	,PENDING_ISSUE("มีคำขอเบิกสินค้า รอการอนุมัติหรือจัดเตรียม")
	,RETURNED("สินค้าถูกคืนจากลูกค้า หรือส่งกลับเข้าโกดัง")
	,HOLD("สินค้าถูกระงับการใช้งาน อาจเกิดจากปัญหาหรือรอตรวจสอบ")
	,DISCONTINUED("สินค้าถูกยกเลิก")
	,IN_PRODUCTION("อยู่ระหว่างการผลิต ยังไม่เข้าสต็อก")
	,WAITING_FOR_QC("รอตรวจสอบคุณภาพก่อนรับเข้าสต็อก")
	,QC_REJECTED("ไม่ผ่านการตรวจสอบคุณภาพ ไม่สามารถใช้ได้")
	,REWORK("ต้องปรับปรุงหรือแก้ไขก่อนใช้งาน")
	,READY_TO_SHIP("พร้อมส่งออกจากคลังสินค้า")
	,SHIPPED("ส่งออกจากคลังแล้ว")
	,DELIVERED("สินค้าส่งถึงลูกค้าเรียบร้อย")
	,LOST_IN_TRANSIT("สูญหายระหว่างการขนส่ง")
	;


    private final String label;

    Stock_status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}



