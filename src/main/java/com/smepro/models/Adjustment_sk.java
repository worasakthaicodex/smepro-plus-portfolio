package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

public class Adjustment_sk {

	
	private int id;
 	private int edata_id;
 	private String doc_code;
 	private String tempId;
	private String title;
	private String  status;
	private int count;
	
 	private String ska_date;
 	private String ska_reason;
 	private String ska_remark;
 	private String ska_type;
 	private String ska_location;

 	private String ska_stock_code;
	private String ska_stock_name;
	private String ska_tracking_number;
	private String ska_pd;
	private String ska_lot_batch;
 	private Double ska_quantity;	
	private String ska_unit;
	
	private String data_nameadd;
	private String  ska_approved_by;
	private List<file> file;
	private Double ska_remaining;	
	
	
	
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
	public String getSka_date() {
		return ska_date;
	}
	public void setSka_date(String ska_date) {
		this.ska_date = ska_date;
	}
	public String getSka_reason() {
		return ska_reason;
	}
	public void setSka_reason(String ska_reason) {
		this.ska_reason = ska_reason;
	}
	public String getSka_remark() {
		return ska_remark;
	}
	public void setSka_remark(String ska_remark) {
		this.ska_remark = ska_remark;
	}
	public String getSka_type() {
		return ska_type;
	}
	public void setSka_type(String ska_type) {
		this.ska_type = ska_type;
	}
	public String getSka_location() {
		return ska_location;
	}
	public void setSka_location(String ska_location) {
		this.ska_location = ska_location;
	}
	public String getSka_stock_code() {
		return ska_stock_code;
	}
	public void setSka_stock_code(String ska_stock_code) {
		this.ska_stock_code = ska_stock_code;
	}
	public String getSka_stock_name() {
		return ska_stock_name;
	}
	public void setSka_stock_name(String ska_stock_name) {
		this.ska_stock_name = ska_stock_name;
	}
	public String getSka_tracking_number() {
		return ska_tracking_number;
	}
	public void setSka_tracking_number(String ska_tracking_number) {
		this.ska_tracking_number = ska_tracking_number;
	}
	public String getSka_lot_batch() {
		return ska_lot_batch;
	}
	public void setSka_lot_batch(String ska_lot_batch) {
		this.ska_lot_batch = ska_lot_batch;
	}
	public Double getSka_quantity() {
		return ska_quantity;
	}
	public void setSka_quantity(Double ska_quantity) {
		this.ska_quantity = ska_quantity;
	}
	public String getSka_unit() {
		return ska_unit;
	}
	public void setSka_unit(String ska_unit) {
		this.ska_unit = ska_unit;
	}
	public String getSka_pd() {
		return ska_pd;
	}
	public void setSka_pd(String ska_pd) {
		this.ska_pd = ska_pd;
	}
	public String getData_nameadd() {
		return data_nameadd;
	}
	public void setData_nameadd(String data_nameadd) {
		this.data_nameadd = data_nameadd;
	}
	public String getSka_approved_by() {
		return ska_approved_by;
	}
	public void setSka_approved_by(String ska_approved_by) {
		this.ska_approved_by = ska_approved_by;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public Double getSka_remaining() {
		return ska_remaining;
	}
	public void setSka_remaining(Double ska_remaining) {
		this.ska_remaining = ska_remaining;
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
