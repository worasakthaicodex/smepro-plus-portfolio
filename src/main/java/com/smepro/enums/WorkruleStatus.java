package com.smepro.enums;

public enum WorkruleStatus {
    ACTIVE("ใช้งาน"),
    INACTIVE("ไม่ใช้งาน"),
    PENDING_APPROVAL("รออนุมัติ"),
    REJECTED("ไม่อนุมัติ"),
    ARCHIVED("เก็บถาวร");

    private final String label;

    WorkruleStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
