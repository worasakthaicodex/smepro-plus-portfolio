package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class production_list implements Identifiable  {
	
	
	  private int  id;
	  private int edata_id;
	  private String pl_code;
	  private String po_code;
	  private String lot_batch;
	  private String product_code;
	  private String product_name;
	  private Integer order_quantity =0;
	  private String start_date;
	  private String end_date;
	  private String processes;
	  private String status;
	  private String responsible_person;
	  private String production_location;
	  private String customer_code;
	  private String customer_name;
	  private String unit;
	  private String item_code;
	  private int count;
	  private String  doc;
	  private String tempId; 
		private int check = 0;
		private int  id_refer;
		  private String department;
		  private String sub_unit;	
		
		private List<production_directive> pd;	
		  
		
     private Integer sub_order_quantity =0;
		
 	

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


	public String getPl_code() {
		return pl_code;
	}
	public void setPl_code(String pl_code) {
		this.pl_code = pl_code;
	}
	public String getLot_batch() {
		return lot_batch;
	}
	public void setLot_batch(String lot_batch) {
		this.lot_batch = lot_batch;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(Integer order_quantity) {
		this.order_quantity = order_quantity;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getProcesses() {
		return processes;
	}
	public void setProcesses(String processes) {
		this.processes = processes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponsible_person() {
		return responsible_person;
	}
	public void setResponsible_person(String responsible_person) {
		this.responsible_person = responsible_person;
	}
	public String getProduction_location() {
		return production_location;
	}
	public void setProduction_location(String production_location) {
		this.production_location = production_location;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getPo_code() {
		return po_code;
	}
	public void setPo_code(String po_code) {
		this.po_code = po_code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public Integer getSub_order_quantity() {
		return sub_order_quantity;
	}
	public void setSub_order_quantity(Integer sub_order_quantity) {
		this.sub_order_quantity = sub_order_quantity;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSub_unit() {
		return sub_unit;
	}
	public void setSub_unit(String sub_unit) {
		this.sub_unit = sub_unit;
	}
	
	
	
	
	public List<production_directive> getPd() {
		return pd;
	}
	public void setPd(List<production_directive> pd) {
		this.pd = pd;
	}
	@Override
	public String toString() {
		return String.format(
				"production_list [id=%s, edata_id=%s, pl_code=%s, po_code=%s, lot_batch=%s, product_code=%s, product_name=%s, order_quantity=%s, start_date=%s, end_date=%s, processes=%s, status=%s, responsible_person=%s, production_location=%s, customer_code=%s, customer_name=%s, unit=%s, item_code=%s, count=%s, doc=%s, tempId=%s, check=%s, id_refer=%s, department=%s, sub_unit=%s, sub_order_quantity=%s, getEdata_id()=%s, getId()=%s, getPl_code()=%s, getLot_batch()=%s, getProduct_name()=%s, getOrder_quantity()=%s, getStart_date()=%s, getEnd_date()=%s, getProcesses()=%s, getStatus()=%s, getResponsible_person()=%s, getProduction_location()=%s, getCustomer_code()=%s, getUnit()=%s, getCustomer_name()=%s, getProduct_code()=%s, getItem_code()=%s, getPo_code()=%s, getCount()=%s, getDoc()=%s, getTempId()=%s, getCheck()=%s, getId_refer()=%s, getSub_order_quantity()=%s, getDepartment()=%s, getSub_unit()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				id, edata_id, pl_code, po_code, lot_batch, product_code, product_name, order_quantity, start_date,
				end_date, processes, status, responsible_person, production_location, customer_code, customer_name,
				unit, item_code, count, doc, tempId, check, id_refer, department, sub_unit, sub_order_quantity,
				getEdata_id(), getId(), getPl_code(), getLot_batch(), getProduct_name(), getOrder_quantity(),
				getStart_date(), getEnd_date(), getProcesses(), getStatus(), getResponsible_person(),
				getProduction_location(), getCustomer_code(), getUnit(), getCustomer_name(), getProduct_code(),
				getItem_code(), getPo_code(), getCount(), getDoc(), getTempId(), getCheck(), getId_refer(),
				getSub_order_quantity(), getDepartment(), getSub_unit(), getClass(), hashCode(), super.toString());
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
