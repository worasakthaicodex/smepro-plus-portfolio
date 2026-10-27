package com.smepro.models;

public class Audit {

	private long id;
	private String audit_type;
	private String audit_code;
	private String created_at;
	private long edata_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAudit_type() {
		return audit_type;
	}
	public void setAudit_type(String audit_type) {
		this.audit_type = audit_type;
	}
	public String getAudit_code() {
		return audit_code;
	}
	public void setAudit_code(String audit_code) {
		this.audit_code = audit_code;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public long getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(long edata_id) {
		this.edata_id = edata_id;
	}
	
	
	
	
}
