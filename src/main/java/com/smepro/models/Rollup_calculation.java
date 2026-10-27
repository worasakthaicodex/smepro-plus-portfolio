package com.smepro.models;

public class Rollup_calculation {

	
	private int id;
	private int id_refer;
	private String rollup_name;
	
	private String condition_column1;
	private String condition_operation1;
	private String condition_as_to1;
	
	private String condition2;
	private String condition_column2;
	private String condition_operation2;
	private String condition_as_to2;
	
	private String condition3;
	private String condition_column3;
	private String condition_operation3;
	private String condition_as_to3;
	
	private String condition4;
	private String condition_column4;
	private String condition_operation4;
	private String condition_as_to4;
	
	
	
	private String column1;
	private String operation1;
	private String as_to1;
	
	private String column2;
	private String operation2;
	private String as_to2;
	
	
	private String column3;
	private String operation3;
	private String as_to3;
	
	
	private Double number_variable1 = 0.0;
	private Double number_variable2= 0.0;
	private Double number_variable3= 0.0;
	private Double number_variable4= 0.0;
	private Double number_variable5= 0.0;
	
	private String text_variable1;
	private String text_variable2;
	private String text_variable3;
	private String text_variable4;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getRollup_name() {
		return rollup_name;
	}
	public void setRollup_name(String rollup_name) {
		this.rollup_name = rollup_name;
	}
	public String getCondition_column1() {
		return condition_column1;
	}
	public void setCondition_column1(String condition_column1) {
		this.condition_column1 = condition_column1;
	}
	public String getCondition_operation1() {
		return condition_operation1;
	}
	public void setCondition_operation1(String condition_operation1) {
		this.condition_operation1 = condition_operation1;
	}
	public String getCondition_as_to1() {
		return condition_as_to1;
	}
	public void setCondition_as_to1(String condition_as_to1) {
		this.condition_as_to1 = condition_as_to1;
	}
	public String getCondition_column2() {
		return condition_column2;
	}
	public void setCondition_column2(String condition_column2) {
		this.condition_column2 = condition_column2;
	}
	public String getCondition_operation2() {
		return condition_operation2;
	}
	public void setCondition_operation2(String condition_operation2) {
		this.condition_operation2 = condition_operation2;
	}
	public String getCondition_as_to2() {
		return condition_as_to2;
	}
	public void setCondition_as_to2(String condition_as_to2) {
		this.condition_as_to2 = condition_as_to2;
	}
	public String getCondition_column3() {
		return condition_column3;
	}
	public void setCondition_column3(String condition_column3) {
		this.condition_column3 = condition_column3;
	}
	public String getCondition_operation3() {
		return condition_operation3;
	}
	public void setCondition_operation3(String condition_operation3) {
		this.condition_operation3 = condition_operation3;
	}
	public String getCondition_as_to3() {
		return condition_as_to3;
	}
	public void setCondition_as_to3(String condition_as_to3) {
		this.condition_as_to3 = condition_as_to3;
	}
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getOperation1() {
		return operation1;
	}
	public void setOperation1(String operation1) {
		this.operation1 = operation1;
	}
	public String getAs_to1() {
		return as_to1;
	}
	public void setAs_to1(String as_to1) {
		this.as_to1 = as_to1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getOperation2() {
		return operation2;
	}
	public void setOperation2(String operation2) {
		this.operation2 = operation2;
	}
	public String getAs_to2() {
		return as_to2;
	}
	public void setAs_to2(String as_to2) {
		this.as_to2 = as_to2;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getOperation3() {
		return operation3;
	}
	public void setOperation3(String operation3) {
		this.operation3 = operation3;
	}
	public String getAs_to3() {
		return as_to3;
	}
	public void setAs_to3(String as_to3) {
		this.as_to3 = as_to3;
	}
	public Double getNumber_variable1() {
		return number_variable1;
	}
	public void setNumber_variable1(Double number_variable1) {
		this.number_variable1 = number_variable1;
	}
	public Double getNumber_variable2() {
		return number_variable2;
	}
	public void setNumber_variable2(Double number_variable2) {
		this.number_variable2 = number_variable2;
	}
	public Double getNumber_variable3() {
		return number_variable3;
	}
	public void setNumber_variable3(Double number_variable3) {
		this.number_variable3 = number_variable3;
	}
	public Double getNumber_variable4() {
		return number_variable4;
	}
	public void setNumber_variable4(Double number_variable4) {
		this.number_variable4 = number_variable4;
	}
	public Double getNumber_variable5() {
		return number_variable5;
	}
	public void setNumber_variable5(Double number_variable5) {
		this.number_variable5 = number_variable5;
	}
	public String getText_variable1() {
		return text_variable1;
	}
	public void setText_variable1(String text_variable1) {
		this.text_variable1 = text_variable1;
	}
	public String getText_variable2() {
		return text_variable2;
	}
	public void setText_variable2(String text_variable2) {
		this.text_variable2 = text_variable2;
	}
	public String getText_variable3() {
		return text_variable3;
	}
	public void setText_variable3(String text_variable3) {
		this.text_variable3 = text_variable3;
	}
	public String getText_variable4() {
		return text_variable4;
	}
	public void setText_variable4(String text_variable4) {
		this.text_variable4 = text_variable4;
	}
	public String getCondition2() {
		return condition2;
	}
	public void setCondition2(String condition2) {
		this.condition2 = condition2;
	}
	public String getCondition3() {
		return condition3;
	}
	public void setCondition3(String condition3) {
		this.condition3 = condition3;
	}
	public String getCondition4() {
		return condition4;
	}
	public void setCondition4(String condition4) {
		this.condition4 = condition4;
	}
	public String getCondition_column4() {
		return condition_column4;
	}
	public void setCondition_column4(String condition_column4) {
		this.condition_column4 = condition_column4;
	}
	public String getCondition_operation4() {
		return condition_operation4;
	}
	public void setCondition_operation4(String condition_operation4) {
		this.condition_operation4 = condition_operation4;
	}
	public String getCondition_as_to4() {
		return condition_as_to4;
	}
	public void setCondition_as_to4(String condition_as_to4) {
		this.condition_as_to4 = condition_as_to4;
	}
	
	
	


}
