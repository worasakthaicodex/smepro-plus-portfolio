package com.smepro.models;

public class batchformat {

    private int  id;
    private String  code_type;
    private String  format_string;
    private String  required_fields; 
    private String  autoid_fields;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	public String getFormat_string() {
		return format_string;
	}
	public void setFormat_string(String format_string) {
		this.format_string = format_string;
	}
	public String getRequired_fields() {
		return required_fields;
	}
	public void setRequired_fields(String required_fields) {
		this.required_fields = required_fields;
	}
	public String getAutoid_fields() {
		return autoid_fields;
	}
	public void setAutoid_fields(String autoid_fields) {
		this.autoid_fields = autoid_fields;
	} 
 
	
	
}
