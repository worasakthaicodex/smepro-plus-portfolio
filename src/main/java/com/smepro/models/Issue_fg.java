package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class Issue_fg implements Identifiable {

	
	private int id;
 	private int edata_id;
 	private String doc_code;
 	private String tempId;
	private String title;
	private String  status;
	private int count;
	
 	private String issue_date;
 	private String issue_type;
 	private String issue_customer_code;
 	private String issue_customer;
 	private String issue_destination;
 	private String issued_by;
 	private String approved_by;
 	private String issued_remark;

 	private String issued_pd_code;
	private String issued_lot_batch;
	private String issued_product_code;
	private String issued_product_name;
 	private Double issued_quantity;	
	private String issued_unit;
	
	private String issued_customer_code;
	private String issued_customer_name;
	private Double issued_remaining;
	
	

	private List<Object> join1;
	private List<Object> join2;



	 private int processId;


	@Override
	public void setJoin1(List<Object> join1) {
		this.join1 = join1;
	}

	public List<Object> getJoin1() {
		return join1;
	}
	@Override
	public void setJoin2(List<Object> join2) {
		this.join2 = join2;
	}

	public List<Object> getJoin2() {
		return join2;
	}
	

	  @Override
	    public int getProcessId() {
	        return this.processId;
	 }

	    public void setProcessId(int processId) {
	        this.processId = processId;
	 }
		
	
	@Override
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getDoc_code() {
		return doc_code;
	}
	public void setDoc_code(String doc_code) {
		this.doc_code = doc_code;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getIssue_destination() {
		return issue_destination;
	}
	public void setIssue_destination(String issue_destination) {
		this.issue_destination = issue_destination;
	}
	public String getIssued_by() {
		return issued_by;
	}
	public void setIssued_by(String issued_by) {
		this.issued_by = issued_by;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getIssued_remark() {
		return issued_remark;
	}
	public void setIssued_remark(String issued_remark) {
		this.issued_remark = issued_remark;
	}
	public String getIssued_pd_code() {
		return issued_pd_code;
	}
	public void setIssued_pd_code(String issued_pd_code) {
		this.issued_pd_code = issued_pd_code;
	}
	public String getIssued_lot_batch() {
		return issued_lot_batch;
	}
	public void setIssued_lot_batch(String issued_lot_batch) {
		this.issued_lot_batch = issued_lot_batch;
	}
	public String getIssued_product_code() {
		return issued_product_code;
	}
	public void setIssued_product_code(String issued_product_code) {
		this.issued_product_code = issued_product_code;
	}
	public String getIssued_product_name() {
		return issued_product_name;
	}
	public void setIssued_product_name(String issued_product_name) {
		this.issued_product_name = issued_product_name;
	}
	public Double getIssued_quantity() {
		return issued_quantity;
	}
	public void setIssued_quantity(Double issued_quantity) {
		this.issued_quantity = issued_quantity;
	}
	public String getIssued_unit() {
		return issued_unit;
	}
	public void setIssued_unit(String issued_unit) {
		this.issued_unit = issued_unit;
	}
	public String getIssue_customer_code() {
		return issue_customer_code;
	}
	public void setIssue_customer_code(String issue_customer_code) {
		this.issue_customer_code = issue_customer_code;
	}
	public String getIssue_customer() {
		return issue_customer;
	}
	public void setIssue_customer(String issue_customer) {
		this.issue_customer = issue_customer;
	}
	public String getIssued_customer_code() {
		return issued_customer_code;
	}
	public void setIssued_customer_code(String issued_customer_code) {
		this.issued_customer_code = issued_customer_code;
	}
	public String getIssued_customer_name() {
		return issued_customer_name;
	}
	public void setIssued_customer_name(String issued_customer_name) {
		this.issued_customer_name = issued_customer_name;
	}
	public Double getIssued_remaining() {
		return issued_remaining;
	}
	public void setIssued_remaining(Double issued_remaining) {
		this.issued_remaining = issued_remaining;
	}
	
	
	public void reset() {
	    for (Field field : this.getClass().getDeclaredFields()) {
	        field.setAccessible(true);
	        Class<?> type = field.getType();
	        try {
	            if (type.isPrimitive()) {
	                if (type == boolean.class) field.setBoolean(this, false);
	                else if (type == byte.class) field.setByte(this, (byte)0);
	                else if (type == short.class) field.setShort(this, (short)0);
	                else if (type == int.class) field.setInt(this, 0);
	                else if (type == long.class) field.setLong(this, 0L);
	                else if (type == float.class) field.setFloat(this, 0f);
	                else if (type == double.class) field.setDouble(this, 0d);
	                else if (type == char.class) field.setChar(this, '\u0000');
	            } else {
	                field.set(this, null);
	            }
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }
	}
	

}
