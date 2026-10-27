package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class Issue_sk implements Identifiable {

	
	private int id;
 	private int edata_id;
 	private String doc_code;
 	private String tempId;
	private String title;
	private String  status;
	private int count;
	
 	private String ski_date;
 	private String ski_type;
 	private String ski_destination;
 	private String ski_by;
 	private String ski_approved_by;
 	private String ski_remark;
 	private String ski_pd_code;
 	private String ski_lot_batch;
 	private String ski_lot_batch2;
 	
 	private String ski_stock_code;
	private String ski_stock_name;
	private String ski_tracking_number;
	private String ski_supplier_code;
 	private Double ski_quantity;
	private Double ski_remaining;
 	private Double ski_withdrawal;	
	private String ski_unit;
	private String ski_supplier_name;

	

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

	public String getSki_date() {
		return ski_date;
	}

	public void setSki_date(String ski_date) {
		this.ski_date = ski_date;
	}

	public String getSki_type() {
		return ski_type;
	}

	public void setSki_type(String ski_type) {
		this.ski_type = ski_type;
	}

	public String getSki_destination() {
		return ski_destination;
	}

	public void setSki_destination(String ski_destination) {
		this.ski_destination = ski_destination;
	}

	public String getSki_by() {
		return ski_by;
	}

	public void setSki_by(String ski_by) {
		this.ski_by = ski_by;
	}

	public String getSki_approved_by() {
		return ski_approved_by;
	}

	public void setSki_approved_by(String ski_approved_by) {
		this.ski_approved_by = ski_approved_by;
	}

	public String getSki_remark() {
		return ski_remark;
	}

	public void setSki_remark(String ski_remark) {
		this.ski_remark = ski_remark;
	}

	public String getSki_pd_code() {
		return ski_pd_code;
	}

	public void setSki_pd_code(String ski_pd_code) {
		this.ski_pd_code = ski_pd_code;
	}

	public String getSki_lot_batch() {
		return ski_lot_batch;
	}

	public void setSki_lot_batch(String ski_lot_batch) {
		this.ski_lot_batch = ski_lot_batch;
	}

	public String getSki_stock_code() {
		return ski_stock_code;
	}

	public void setSki_stock_code(String ski_stock_code) {
		this.ski_stock_code = ski_stock_code;
	}

	public String getSki_stock_name() {
		return ski_stock_name;
	}

	public void setSki_stock_name(String ski_stock_name) {
		this.ski_stock_name = ski_stock_name;
	}

	public String getSki_tracking_number() {
		return ski_tracking_number;
	}

	public void setSki_tracking_number(String ski_tracking_number) {
		this.ski_tracking_number = ski_tracking_number;
	}

	public String getSki_supplier_code() {
		return ski_supplier_code;
	}

	public void setSki_supplier_code(String ski_supplier_code) {
		this.ski_supplier_code = ski_supplier_code;
	}

	public Double getSki_quantity() {
		return ski_quantity;
	}

	public void setSki_quantity(Double ski_quantity) {
		this.ski_quantity = ski_quantity;
	}

	public String getSki_unit() {
		return ski_unit;
	}

	public void setSki_unit(String ski_unit) {
		this.ski_unit = ski_unit;
	}

	public String getSki_supplier_name() {
		return ski_supplier_name;
	}

	public void setSki_supplier_name(String ski_supplier_name) {
		this.ski_supplier_name = ski_supplier_name;
	}

	public Double getSki_withdrawal() {
		return ski_withdrawal;
	}

	public void setSki_withdrawal(Double ski_withdrawal) {
		this.ski_withdrawal = ski_withdrawal;
	}

	public String getSki_lot_batch2() {
		return ski_lot_batch2;
	}

	public void setSki_lot_batch2(String ski_lot_batch2) {
		this.ski_lot_batch2 = ski_lot_batch2;
	}

	public Double getSki_remaining() {
		return ski_remaining;
	}

	public void setSki_remaining(Double ski_remaining) {
		this.ski_remaining = ski_remaining;
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
