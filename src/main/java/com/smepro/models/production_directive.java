package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class production_directive  implements Identifiable {

	
	private int id;
	private int edata_id;
	private String pd_code;
	private String pl_code;
	private String lot_batch;
	private String product_code;
	private String product_name;
	private String process_step;
	private String department;
	private Integer quantity;
	private String start_date;
	private String end_date;
	private String status;
	private String responsible_person;
	private String remarks;
	private String unit;
	private String sub_unit;
	private String line;
	private String actual_output;
	private String defective_output;
	private String item_code;
 	private String tempId;
	private int check = 0;
 	private String plan_time;
 	private int  id_refer;
 	
	private int count;
	
	private String  doc;
	
	private Integer order_quantity = 0;

	
	
	
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

	public String getPd_code() {
		return pd_code;
	}

	public void setPd_code(String pd_code) {
		this.pd_code = pd_code;
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

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProcess_step() {
		return process_step;
	}

	public void setProcess_step(String process_step) {
		this.process_step = process_step;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getActual_output() {
		return actual_output;
	}

	public void setActual_output(String actual_output) {
		this.actual_output = actual_output;
	}

	public String getDefective_output() {
		return defective_output;
	}

	public void setDefective_output(String defective_output) {
		this.defective_output = defective_output;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
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

	public String getPlan_time() {
		return plan_time;
	}

	public void setPlan_time(String plan_time) {
		this.plan_time = plan_time;
	}

	public int getId_refer() {
		return id_refer;
	}

	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
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

	public Integer getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(Integer order_quantity) {
		this.order_quantity = order_quantity;
	}

	public String getSub_unit() {
		return sub_unit;
	}

	public void setSub_unit(String sub_unit) {
		this.sub_unit = sub_unit;
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


