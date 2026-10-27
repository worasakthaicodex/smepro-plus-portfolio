package com.smepro.enums;



public enum Tkey1 {
	
	
	 title("title.title")
	 ,material("material.stock_name")
	 ,batch("maindata.batch")
	 ,quantity("fg.quantity")
	 ,unit("fg.unit")
	 ,supplier("material.supplier_name")
	 ,batch_material("material.batch_material")
	 ,start("product.start_date")
	 ,data_nameadd("product.operator")
	 ;

	


   
	
    private final String label;

    Tkey1(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
