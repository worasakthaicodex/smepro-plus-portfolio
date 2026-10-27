package com.smepro.enums;



public enum Tkey3 {
	
	 title("title.title")
	 ,batch("material.stock_name")
	 ,product("maindata.product")
	 ,customer("product.customer_name")
	 ,issue_type("fg.issue_type")
	 ,quantity("fg.quantity")
	 ,unit("fg.unit")
	 ,start("product.start_date")
	 ,data_nameadd("product.operator")
	 ;


	
    private final String label;

    Tkey3(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
