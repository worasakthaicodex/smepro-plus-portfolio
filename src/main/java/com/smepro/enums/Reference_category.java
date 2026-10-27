package com.smepro.enums;

public enum Reference_category {
	
	product_names("ชื่อสินค้า"),
	lot_batch("เลขการผลิต"),
	line("สายการผลิต"),
	process_step("ขั้นตอนการทำงาน"),
	department("แผนก"),
	machine_id("รหัสเครื่องจักร"),
	register_name("ชื่อทะเบียน"),
	stock_list("ชื่อวัตถุดิบ"),
	;
	

    private final String label;

    Reference_category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

