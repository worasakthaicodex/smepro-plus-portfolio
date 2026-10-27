package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class Inventory_transfer_sk implements Identifiable {

	
	private int id;
 	private int edata_id;
 	private String doc_code;
 	private String tempId;
	private String title;
	private String  status;
	private int count;
	
 	private String skt_date;
	private String skt_status;
 	private String skt_from_location;
 	private String skt_to_location;
 	private String skt_transferred_by;
 	private String skt_tracking_number;
 	private String skt_remark;

 	private String skt_pd_code;
	private String skt_lot_batch;
	private String skt_stock_code;
	private String skt_stock_name;
 	private Double skt_quantity;	
	private String skt_unit;
	private String skt_approved_by;
	

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
	
	
	public String getSkt_status() {
		return skt_status;
	}
	public void setSkt_status(String skt_status) {
		this.skt_status = skt_status;
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
	public String getSkt_date() {
		return skt_date;
	}
	public void setSkt_date(String skt_date) {
		this.skt_date = skt_date;
	}
	public String getSkt_from_location() {
		return skt_from_location;
	}
	public void setSkt_from_location(String skt_from_location) {
		this.skt_from_location = skt_from_location;
	}
	public String getSkt_to_location() {
		return skt_to_location;
	}
	public void setSkt_to_location(String skt_to_location) {
		this.skt_to_location = skt_to_location;
	}
	public String getSkt_transferred_by() {
		return skt_transferred_by;
	}
	public void setSkt_transferred_by(String skt_transferred_by) {
		this.skt_transferred_by = skt_transferred_by;
	}
	public String getSkt_remark() {
		return skt_remark;
	}
	public void setSkt_remark(String skt_remark) {
		this.skt_remark = skt_remark;
	}
	public String getSkt_pd_code() {
		return skt_pd_code;
	}
	public void setSkt_pd_code(String skt_pd_code) {
		this.skt_pd_code = skt_pd_code;
	}
	public String getSkt_lot_batch() {
		return skt_lot_batch;
	}
	public void setSkt_lot_batch(String skt_lot_batch) {
		this.skt_lot_batch = skt_lot_batch;
	}
	public String getSkt_stock_code() {
		return skt_stock_code;
	}
	public void setSkt_stock_code(String skt_stock_code) {
		this.skt_stock_code = skt_stock_code;
	}
	public String getSkt_stock_name() {
		return skt_stock_name;
	}
	public void setSkt_stock_name(String skt_stock_name) {
		this.skt_stock_name = skt_stock_name;
	}
	public Double getSkt_quantity() {
		return skt_quantity;
	}
	public void setSkt_quantity(Double skt_quantity) {
		this.skt_quantity = skt_quantity;
	}
	public String getSkt_unit() {
		return skt_unit;
	}
	public void setSkt_unit(String skt_unit) {
		this.skt_unit = skt_unit;
	}
	public String getSkt_approved_by() {
		return skt_approved_by;
	}
	public void setSkt_approved_by(String skt_approved_by) {
		this.skt_approved_by = skt_approved_by;
	}
	public String getSkt_tracking_number() {
		return skt_tracking_number;
	}
	public void setSkt_tracking_number(String skt_tracking_number) {
		this.skt_tracking_number = skt_tracking_number;
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
