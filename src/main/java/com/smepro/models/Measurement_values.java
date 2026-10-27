package com.smepro.models;

public class Measurement_values {
	
	private int id;
	private int measurement_rules_id; //ซ้ำได้
	private int edata_id; //ห้ามซ้ำใน measurement_rules_id เดียวกัน
	private String percentage_value0;
	private String percentage_value10;
	private String percentage_value20;
	private String percentage_value30;
	private String percentage_value40;
	private String percentage_value50;
	private String percentage_value60;
	private String percentage_value70;
	private String percentage_value80;
	private String percentage_value90;
	private String percentage_value100;
	private String current_value;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMeasurement_rules_id() {
		return measurement_rules_id;
	}
	public void setMeasurement_rules_id(int measurement_rules_id) {
		this.measurement_rules_id = measurement_rules_id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getPercentage_value0() {
		return percentage_value0;
	}
	public void setPercentage_value0(String percentage_value0) {
		this.percentage_value0 = percentage_value0;
	}
	public String getPercentage_value10() {
		return percentage_value10;
	}
	public void setPercentage_value10(String percentage_value10) {
		this.percentage_value10 = percentage_value10;
	}
	public String getPercentage_value20() {
		return percentage_value20;
	}
	public void setPercentage_value20(String percentage_value20) {
		this.percentage_value20 = percentage_value20;
	}
	public String getPercentage_value30() {
		return percentage_value30;
	}
	public void setPercentage_value30(String percentage_value30) {
		this.percentage_value30 = percentage_value30;
	}
	public String getPercentage_value40() {
		return percentage_value40;
	}
	public void setPercentage_value40(String percentage_value40) {
		this.percentage_value40 = percentage_value40;
	}
	public String getPercentage_value50() {
		return percentage_value50;
	}
	public void setPercentage_value50(String percentage_value50) {
		this.percentage_value50 = percentage_value50;
	}
	public String getPercentage_value60() {
		return percentage_value60;
	}
	public void setPercentage_value60(String percentage_value60) {
		this.percentage_value60 = percentage_value60;
	}
	public String getPercentage_value70() {
		return percentage_value70;
	}
	public void setPercentage_value70(String percentage_value70) {
		this.percentage_value70 = percentage_value70;
	}
	public String getPercentage_value80() {
		return percentage_value80;
	}
	public void setPercentage_value80(String percentage_value80) {
		this.percentage_value80 = percentage_value80;
	}
	public String getPercentage_value90() {
		return percentage_value90;
	}
	public void setPercentage_value90(String percentage_value90) {
		this.percentage_value90 = percentage_value90;
	}
	public String getPercentage_value100() {
		return percentage_value100;
	}
	public void setPercentage_value100(String percentage_value100) {
		this.percentage_value100 = percentage_value100;
	}
	public String getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(String current_value) {
		this.current_value = current_value;
	}
	
	
	
}
