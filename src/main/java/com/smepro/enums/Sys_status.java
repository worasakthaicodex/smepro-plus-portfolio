package com.smepro.enums;


public enum Sys_status {
    Sys_Save("สถานะการทำงานสำเร็จ"),
    Sys_Error("สถานะการทำงานผิดพลาด"),
    Sys_Error_TYPE("รายละเอียดสถานะการทำงานผิดพลาด")
    ;

    private final String label;

    Sys_status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    
    
    
    
}
