package com.smepro.models;

public class UseformPrint {
	
	private int id;
	private int form_id;
	private int process_id;
	private int use_status; // 0 ไม่ได้เลือกไว้ 1 เลือกไว้เป็นตั้งต้น
	private int user_id_select;  //  0 เลือกถ้าไม่มีของตน > 0 ของตนเอง
	private int payment_terms_column;
	private String promotion_column;
	private String register_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getForm_id() {
		return form_id;
	}
	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}
	public int getProcess_id() {
		return process_id;
	}
	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
	public int getUse_status() {
		return use_status;
	}
	public void setUse_status(int use_status) {
		this.use_status = use_status;
	}
	public int getUser_id_select() {
		return user_id_select;
	}
	public void setUser_id_select(int user_id_select) {
		this.user_id_select = user_id_select;
	}
	public int getPayment_terms_column() {
		return payment_terms_column;
	}
	public void setPayment_terms_column(int payment_terms_column) {
		this.payment_terms_column = payment_terms_column;
	}
	public String getPromotion_column() {
		return promotion_column;
	}
	public void setPromotion_column(String promotion_column) {
		this.promotion_column = promotion_column;
	}
	public String getRegister_id() {
		return register_id;
	}
	public void setRegister_id(String register_id) {
		this.register_id = register_id;
	}

	
}
