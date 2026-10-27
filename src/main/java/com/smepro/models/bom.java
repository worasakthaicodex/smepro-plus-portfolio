package com.smepro.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class bom implements Identifiable,Serializable {
	

	
	private int id;
	private int edata_id;
	private String bom_id;
	private String tempId; 
	private  String title;
	private  String bom_code;
	private  String bom_name;
	private String bom_refer;
	private String bom_category;
	private String bom_description;
	private Double bom_base_quantity;
	private Double bom_wastage;
	private String bom_base_unit;
	private Double bom_price;
	private Double bom_total_cost;
	private int bom_version;
	private String bom_note;
	private String bom_status;
	private String  status;
	private String  bom_product_code;
	private String  bom_product_name;
	private String  doc;
	private String  check;
	private String  id_refer;
	private int count;
	private Double bom_quantity;
	
	
	public bom(){
		this.bom_wastage = 0.0;
		this.bom_base_quantity = 0.0;
		this.bom_price = 0.0;
		this.bom_total_cost = 0.0;
		this.bom_quantity = 0.0;
	}

	

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


	public String getBom_id() {
		return bom_id;
	}


	public void setBom_id(String bom_id) {
		this.bom_id = bom_id;
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


	public String getBom_code() {
		return bom_code;
	}


	public void setBom_code(String bom_code) {
		this.bom_code = bom_code;
	}


	public String getBom_name() {
		return bom_name;
	}


	public void setBom_name(String bom_name) {
		this.bom_name = bom_name;
	}


	public String getBom_refer() {
		return bom_refer;
	}


	public void setBom_refer(String bom_refer) {
		this.bom_refer = bom_refer;
	}


	public String getBom_category() {
		return bom_category;
	}


	public void setBom_category(String bom_category) {
		this.bom_category = bom_category;
	}


	public String getBom_description() {
		return bom_description;
	}


	public void setBom_description(String bom_description) {
		this.bom_description = bom_description;
	}


	public Double getBom_base_quantity() {
		return bom_base_quantity;
	}


	public void setBom_base_quantity(Double bom_base_quantity) {
		this.bom_base_quantity = bom_base_quantity;
	}


	public Double getBom_wastage() {
		return bom_wastage;
	}


	public void setBom_wastage(Double bom_wastage) {
		this.bom_wastage = bom_wastage;
	}


	public String getBom_base_unit() {
		return bom_base_unit;
	}


	public void setBom_base_unit(String bom_base_unit) {
		this.bom_base_unit = bom_base_unit;
	}


	public Double getBom_price() {
		return bom_price;
	}


	public void setBom_price(Double bom_price) {
		this.bom_price = bom_price;
	}


	public Double getBom_total_cost() {
		return bom_total_cost;
	}


	public void setBom_total_cost(Double bom_total_cost) {
		this.bom_total_cost = bom_total_cost;
	}


	public int getBom_version() {
		return bom_version;
	}


	public void setBom_version(int bom_version) {
		this.bom_version = bom_version;
	}


	public String getBom_note() {
		return bom_note;
	}


	public void setBom_note(String bom_note) {
		this.bom_note = bom_note;
	}


	public String getBom_status() {
		return bom_status;
	}


	public void setBom_status(String bom_status) {
		this.bom_status = bom_status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getBom_product_code() {
		return bom_product_code;
	}


	public void setBom_product_code(String bom_product_code) {
		this.bom_product_code = bom_product_code;
	}


	public String getBom_product_name() {
		return bom_product_name;
	}


	public void setBom_product_name(String bom_product_name) {
		this.bom_product_name = bom_product_name;
	}


	public String getDoc() {
		return doc;
	}


	public void setDoc(String doc) {
		this.doc = doc;
	}


	public String getCheck() {
		return check;
	}


	public void setCheck(String check) {
		this.check = check;
	}


	public String getId_refer() {
		return id_refer;
	}


	public void setId_refer(String id_refer) {
		this.id_refer = id_refer;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public Double getBom_quantity() {
		return bom_quantity;
	}


	public void setBom_quantity(Double bom_quantity) {
		this.bom_quantity = bom_quantity;
	}
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return String.format(
				"bom [bom_name=%s, bom_refer=%s, bom_category=%s, bom_description=%s, bom_base_quantity=%s, bom_wastage=%s, bom_base_unit=%s, bom_price=%s, bom_total_cost=%s, bom_version=%s, bom_note=%s, bom_status=%s, status=%s, bom_product_code=%s, bom_product_name=%s, doc=%s, check=%s, id_refer=%s, count=%s, bom_quantity=%s, join1=%s, join2=%s, processId=%s, getJoin1()=%s, getJoin2()=%s, getProcessId()=%s, getId()=%s, getEdata_id()=%s, getBom_id()=%s, getTempId()=%s, getTitle()=%s, getBom_code()=%s, getBom_name()=%s, getBom_refer()=%s, getBom_category()=%s, getBom_description()=%s, getBom_base_quantity()=%s, getBom_wastage()=%s, getBom_base_unit()=%s, getBom_price()=%s, getBom_total_cost()=%s, getBom_version()=%s, getBom_note()=%s, getBom_status()=%s, getStatus()=%s, getBom_product_code()=%s, getBom_product_name()=%s, getDoc()=%s, getCheck()=%s, getId_refer()=%s, getCount()=%s, getBom_quantity()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				bom_name, bom_refer, bom_category, bom_description,
				bom_base_quantity, bom_wastage, bom_base_unit, bom_price, bom_total_cost, bom_version, bom_note,
				bom_status, status, bom_product_code, bom_product_name, doc, check, id_refer, count, bom_quantity,
				join1, join2, processId, getJoin1(), getJoin2(), getProcessId(), getId(), getEdata_id(), getBom_id(),
				getTempId(), getTitle(), getBom_code(), getBom_name(), getBom_refer(), getBom_category(),
				getBom_description(), getBom_base_quantity(), getBom_wastage(), getBom_base_unit(), getBom_price(),
				getBom_total_cost(), getBom_version(), getBom_note(), getBom_status(), getStatus(),
				getBom_product_code(), getBom_product_name(), getDoc(), getCheck(), getId_refer(), getCount(),
				getBom_quantity(), getClass(), hashCode(), super.toString());
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
