package com.smepro.models;

import java.lang.reflect.Field;

public class edata_stock implements Cloneable{

	private int id;
	private int stock_id;
	
	
	private int edata_id;
	
	private String stock_proceedings;
	private String stock_list;
	private String stock_note;
	private String  stock_unit;
	private String  stock_type;
	private String  stock_group;
	private String  stock_storage;
	private String  stock_date;
	private String  stock_pd;
	private String  stock_batch;
	private String  stock_lot;
	private String  stock_serial_number;
	private String  stock_status;
	private int stock_stockid;
	private String stock_received;
	private double issued_amount;
	
	
	
	public String getStock_support_dataflow() {
		return stock_support_dataflow;
	}
	public void setStock_support_dataflow(String stock_support_dataflow) {
		this.stock_support_dataflow = stock_support_dataflow;
	}
	private String stock_max;
	private String stock_min;
	private String stock_expdate;
	private String stock_support_dataflow;
	
	
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getStock_list() {
		return stock_list;
	}
	public void setStock_list(String stock_list) {
		this.stock_list = stock_list;
	}
	public String getStock_unit() {
		return stock_unit;
	}
	public void setStock_unit(String stock_unit) {
		this.stock_unit = stock_unit;
	}
	public String getStock_type() {
		return stock_type;
	}
	public void setStock_type(String stock_type) {
		this.stock_type = stock_type;
	}
	public String getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(String stock_storage) {
		this.stock_storage = stock_storage;
	}
	public String getStock_note() {
		return stock_note;
	}
	public void setStock_note(String stock_note) {
		this.stock_note = stock_note;
	}
	public String getStock_date() {
		return stock_date;
	}
	public void setStock_date(String stock_date) {
		this.stock_date = stock_date;
	}
	public String getStock_pd() {
		return stock_pd;
	}
	public void setStock_pd(String stock_pd) {
		this.stock_pd = stock_pd;
	}
	public String getStock_batch() {
		return stock_batch;
	}
	public void setStock_batch(String stock_batch) {
		this.stock_batch = stock_batch;
	}
	public String getStock_lot() {
		return stock_lot;
	}
	public void setStock_lot(String stock_lot) {
		this.stock_lot = stock_lot;
	}
	public String getStock_serial_number() {
		return stock_serial_number;
	}
	public void setStock_serial_number(String stock_serial_number) {
		this.stock_serial_number = stock_serial_number;
	}
	public String getStock_status() {
		return stock_status;
	}
	public void setStock_status(String stock_status) {
		this.stock_status = stock_status;
	}
	public int getStock_stockid() {
		return stock_stockid;
	}
	public void setStock_stockid(int stock_stockid) {
		this.stock_stockid = stock_stockid;
	}
	public String getStock_received() {
		return stock_received;
	}
	public void setStock_received(String stock_received) {
		this.stock_received = stock_received;
	}
	public String getStock_max() {
		return stock_max;
	}
	public void setStock_max(String stock_max) {
		this.stock_max = stock_max;
	}
	public String getStock_min() {
		return stock_min;
	}
	public void setStock_min(String stock_min) {
		this.stock_min = stock_min;
	}
	public String getStock_expdate() {
		return stock_expdate;
	}
	public void setStock_expdate(String stock_expdate) {
		this.stock_expdate = stock_expdate;
	}
	public double getIssued_amount() {
		return issued_amount;
	}
	public void setIssued_amount(double issued_amount) {
		this.issued_amount = issued_amount;
	}

	public String getStock_group() {
		return stock_group;
	}
	public void setStock_group(String stock_group) {
		this.stock_group = stock_group;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	public String getStock_proceedings() {
		return stock_proceedings;
	}
	public void setStock_proceedings(String stock_proceedings) {
		this.stock_proceedings = stock_proceedings;
	}
	@Override
	    public edata_stock clone() {
	        try {
	            return (edata_stock) super.clone(); // ✅ Clone ตัวเอง
	        } catch (CloneNotSupportedException e) {
	            throw new RuntimeException(e);
	        }
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
