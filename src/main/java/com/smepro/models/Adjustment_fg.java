package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

public class Adjustment_fg {

	
	private int id;
 	private int edata_id;
 	private String doc_code;
 	private String tempId;
	private String title;
	private String  status;
	private int count;
	
 	private String adjustment_date;
 	private String adjustment_reason;
 	private String adjustment_remark;
 	private String adjustment_type;
 	private String adjustment_location;

 	private String adjustment_pd_code;
	private String adjustment_lot_batch;
	private String adjustment_product_code;
	private String adjustment_product_name;
 	private Double adjustment_quantity;	
	private String adjustment_unit;
	private String adjustment_approved_by;
	private String data_nameadd;
	private Double adjustment_remaining;	
	
	
	private  List<file> file;
	
	
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
	public String getAdjustment_date() {
		return adjustment_date;
	}
	public void setAdjustment_date(String adjustment_date) {
		this.adjustment_date = adjustment_date;
	}
	public String getAdjustment_reason() {
		return adjustment_reason;
	}
	public void setAdjustment_reason(String adjustment_reason) {
		this.adjustment_reason = adjustment_reason;
	}
	public String getAdjustment_remark() {
		return adjustment_remark;
	}
	public void setAdjustment_remark(String adjustment_remark) {
		this.adjustment_remark = adjustment_remark;
	}
	public String getAdjustment_type() {
		return adjustment_type;
	}
	public void setAdjustment_type(String adjustment_type) {
		this.adjustment_type = adjustment_type;
	}
	public String getAdjustment_location() {
		return adjustment_location;
	}
	public void setAdjustment_location(String adjustment_location) {
		this.adjustment_location = adjustment_location;
	}
	public String getAdjustment_pd_code() {
		return adjustment_pd_code;
	}
	public void setAdjustment_pd_code(String adjustment_pd_code) {
		this.adjustment_pd_code = adjustment_pd_code;
	}
	public String getAdjustment_lot_batch() {
		return adjustment_lot_batch;
	}
	public void setAdjustment_lot_batch(String adjustment_lot_batch) {
		this.adjustment_lot_batch = adjustment_lot_batch;
	}
	public String getAdjustment_product_code() {
		return adjustment_product_code;
	}
	public void setAdjustment_product_code(String adjustment_product_code) {
		this.adjustment_product_code = adjustment_product_code;
	}
	public String getAdjustment_product_name() {
		return adjustment_product_name;
	}
	public void setAdjustment_product_name(String adjustment_product_name) {
		this.adjustment_product_name = adjustment_product_name;
	}
	public Double getAdjustment_quantity() {
		return adjustment_quantity;
	}
	public void setAdjustment_quantity(Double adjustment_quantity) {
		this.adjustment_quantity = adjustment_quantity;
	}
	public String getAdjustment_unit() {
		return adjustment_unit;
	}
	public void setAdjustment_unit(String adjustment_unit) {
		this.adjustment_unit = adjustment_unit;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public String getAdjustment_approved_by() {
		return adjustment_approved_by;
	}
	public void setAdjustment_approved_by(String adjustment_approved_by) {
		this.adjustment_approved_by = adjustment_approved_by;
	}
	public String getData_nameadd() {
		return data_nameadd;
	}
	public void setData_nameadd(String data_nameadd) {
		this.data_nameadd = data_nameadd;
	}
	public Double getAdjustment_remaining() {
		return adjustment_remaining;
	}
	public void setAdjustment_remaining(Double adjustment_remaining) {
		this.adjustment_remaining = adjustment_remaining;
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
