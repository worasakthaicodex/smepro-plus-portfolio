package com.smepro.enums;



public enum DB {
	
	
	 Stock_Warehouse("ใช้จัดเก็บและบริหารสินค้าคงคลังทุกประเภท")
	,Production("ใช้บันทึกและติดตามกระบวนการผลิต")
	,Machinery_Factory("ใช้บันทึกทรัพยากรเครื่องจักรและโรงงาน")
	,Procurement_SupplyChain("ใช้บันทึกวัตถุดิบและสินค้าเข้าจากภายนอก")
	,Sales_Customer("ใช้ทึกข้อมูลลูกค้าและการขาย")
	,Finance_Accounting("ใช้บันทึกการควบคุมงบประมาณและการเงิน")
	,HR_Workforce("ใช้บันทึกข้อมูลการบริหารพนักงานและการทำงาน");

	
	
	
	
    private final String label;

    DB(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}


