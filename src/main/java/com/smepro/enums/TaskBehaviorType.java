package com.smepro.enums;

public enum TaskBehaviorType {
	
	FIXED_REPEAT("ดำเนินการซ้ำกับเป้าหมายเดิม"),
	NON_REPEAT_RANDOM("ดำเนินการป้าหมายแบบไม่ซ้ำ"),
	SUM_reference_category1("ยอดรวมของหมวดหมู่อ้างอิง 1"),
	SUM_reference_category2("ยอดรวมของหมวดหมู่อ้างอิง 2"),
	SUM_reference_category3("ยอดรวมของหมวดหมู่อ้างอิง 3")
	;
	

    private final String label;

    TaskBehaviorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

