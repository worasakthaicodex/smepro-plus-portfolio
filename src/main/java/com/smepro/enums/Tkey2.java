package com.smepro.enums;



public enum Tkey2 {
	
	 title("title.title")
	 ,batch("material.stock_name")
	 ,production_directive("maindata.pd")
	 ,equipment("product.equipment")
	 ,quality_control("data.quality_control")
	 ,inspection_result("product.inspection_result")
	 ,deviations("data.deviations")
	 ,start("product.start_date")
	 ,end("product.end_date")
	 ,good_production_output("data.good_production_output")
	 ,data_nameadd("product.operator")
	 ;


	
    private final String label;

    Tkey2(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
