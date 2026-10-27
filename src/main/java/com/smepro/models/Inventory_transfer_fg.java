package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class Inventory_transfer_fg implements Identifiable {

	
	private int id;
 	private int edata_id;
 	private String doc_code;
 	private String tempId;
	private String title;
	private String  status;
	private int count;
	
 	private String transfer_date;
 	private String transfer_from_location;
 	private String transfer_to_location;
 	private String transferred_by;
 	private String transfer_remark;

 	private String transfer_pd_code;
	private String transfer_lot_batch;
	private String transfer_product_code;
	private String transfer_product_name;
 	private Double transfer_quantity;	
	private Double fg_remaining;
	private String transfer_unit;
	private String fg_status;
	private String fg_approved_by;
	private List<file> file;
	

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
	public String getTransfer_date() {
		return transfer_date;
	}
	public void setTransfer_date(String transfer_date) {
		this.transfer_date = transfer_date;
	}
	public String getTransfer_from_location() {
		return transfer_from_location;
	}
	public void setTransfer_from_location(String transfer_from_location) {
		this.transfer_from_location = transfer_from_location;
	}
	public String getTransfer_to_location() {
		return transfer_to_location;
	}
	public void setTransfer_to_location(String transfer_to_location) {
		this.transfer_to_location = transfer_to_location;
	}
	public String getTransferred_by() {
		return transferred_by;
	}
	public void setTransferred_by(String transferred_by) {
		this.transferred_by = transferred_by;
	}
	public String getTransfer_remark() {
		return transfer_remark;
	}
	public void setTransfer_remark(String transfer_remark) {
		this.transfer_remark = transfer_remark;
	}
	public String getTransfer_pd_code() {
		return transfer_pd_code;
	}
	public void setTransfer_pd_code(String transfer_pd_code) {
		this.transfer_pd_code = transfer_pd_code;
	}
	public String getTransfer_lot_batch() {
		return transfer_lot_batch;
	}
	public void setTransfer_lot_batch(String transfer_lot_batch) {
		this.transfer_lot_batch = transfer_lot_batch;
	}
	public String getTransfer_product_code() {
		return transfer_product_code;
	}
	public void setTransfer_product_code(String transfer_product_code) {
		this.transfer_product_code = transfer_product_code;
	}
	public String getTransfer_product_name() {
		return transfer_product_name;
	}
	public void setTransfer_product_name(String transfer_product_name) {
		this.transfer_product_name = transfer_product_name;
	}
	public Double getTransfer_quantity() {
		return transfer_quantity;
	}
	public void setTransfer_quantity(Double transfer_quantity) {
		this.transfer_quantity = transfer_quantity;
	}
	public String getTransfer_unit() {
		return transfer_unit;
	}
	public void setTransfer_unit(String transfer_unit) {
		this.transfer_unit = transfer_unit;
	}
	public String getFg_status() {
		return fg_status;
	}
	public void setFg_status(String fg_status) {
		this.fg_status = fg_status;
	}
	public String getFg_approved_by() {
		return fg_approved_by;
	}
	public void setFg_approved_by(String fg_approved_by) {
		this.fg_approved_by = fg_approved_by;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public Double getFg_remaining() {
		return fg_remaining;
	}
	public void setFg_remaining(Double fg_remaining) {
		this.fg_remaining = fg_remaining;
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
