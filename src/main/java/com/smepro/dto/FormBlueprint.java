package com.smepro.dto;

public class FormBlueprint {
			    String version;
			    
			    // ชิ้นส่วนที่ 1: Property
			    public boolean hasPropertyBatch = false;
			    public   Object propertyBatchData; // **เปลี่ยน Object เป็น Type จริงของคุณ เช่น List<Property>
			
			    // ชิ้นส่วนที่ 2: Input
			    public boolean hasInput = false;
			    public    Object inputData;         // **เปลี่ยน Type ให้ตรงกับของจริง
			    public   Object inputBatchData;    // **เปลี่ยน Type ให้ตรงกับของจริง
			
			    // ชิ้นส่วนที่ 3: Ability
			    public   boolean hasAbility = false;
			    public   Object abilityData;       // **เปลี่ยน Type ให้ตรงกับของจริง
			
			    public FormBlueprint(String version) {
			        this.version = version;
			    }
}