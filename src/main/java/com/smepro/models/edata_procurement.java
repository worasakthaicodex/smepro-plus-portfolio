package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import com.smepro.dao.Identifiable;

public class edata_procurement implements Identifiable  {

	private int id = 0;
	private String tempId;
	private String title;
	private int edata_id;
	private int id_refer = 0;
	private int id_refer_by = 0;
	private String doc;
	private int check = 0;
	private String action_type;
	private String procurement_itemid;
	private String procurement_itemname;
	private Double  procurement_quantityrequested;
	private Double  procurement_estimatedprice;
	private String procurement_unit;
	private String procurement_requesteddate;
	private String procurement_documenttype;
	private String procurement_documentnumber;

	
	private Double  procurement_linetotal;
	private Double  procurement_grandtotal;
	
	private Double  procurement_linetotal2;
	private Double  procurement_grandtotal2;
	
	private Double  procurement_quantity_ordered;
	private Double  procurement_unit_price;
	private String procurement_payment_status;
	private String procurement_delivery_date;
	private String procurement_supplier_name;
	private String procurement_supplier_address;
	private String procurement_supplier_id;
	private String procurement_type;
	private String procurement_status  ="Create";
	
	
	private String received_date;
	private String tracking_number;
	private String inspection_status;
	private String damage_report;
	private String storage_location;
	private Double quantity_received;
	private String doc_refer;
	
	private int count;
	

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

	
	
	
	
	public edata_procurement() {
		this.procurement_quantityrequested = 0.00000001;
		this.procurement_estimatedprice = 0.00000001;
		this.procurement_linetotal = 0.00000001;
		this.procurement_grandtotal = 0.00000001;
		this.procurement_linetotal2 = 0.00000001;
		this.procurement_grandtotal2 = 0.00000001;
		this.procurement_quantity_ordered =0.00000001;
		this.procurement_unit_price = 0.00000001;
		this.quantity_received = 0.00000001;
	}
	
	
	@Override
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProcurement_itemid() {
		return procurement_itemid;
	}
	public void setProcurement_itemid(String procurement_itemid) {
		this.procurement_itemid = procurement_itemid;
	}
	public String getProcurement_itemname() {
		return procurement_itemname;
	}
	
	
	
	public void setProcurement_itemname(String procurement_itemname) {
		this.procurement_itemname = procurement_itemname;
	}
	
	
	public Double getProcurement_quantityrequested() {
		return procurement_quantityrequested;
	}
	
	public void setProcurement_quantityrequested(String procurement_quantityrequested) {
		
		 if(procurement_quantityrequested == null) {
			 procurement_quantityrequested = "0.00000001";
		 }
		 else if(procurement_quantityrequested.isEmpty() || procurement_quantityrequested.isBlank()) {
			 procurement_quantityrequested = "0.0";
		 }

		this.procurement_quantityrequested = Double.valueOf(procurement_quantityrequested);

	}
	
	public Double getProcurement_estimatedprice() {
		return procurement_estimatedprice;
	}

	
	public void setProcurement_estimatedprice(String procurement_estimatedprice) {
		
		 if(procurement_estimatedprice == null) {
			 procurement_estimatedprice = "0.00000001";
		 }
		 else if(procurement_estimatedprice.isEmpty() || procurement_estimatedprice.isBlank()) {
			 procurement_estimatedprice = "0.0";
		 }
		
		 
		this.procurement_estimatedprice = Double.valueOf(procurement_estimatedprice);
		
		
		
	}
	
	
	public String getProcurement_unit() {
		return procurement_unit;
	}
	public void setProcurement_unit(String procurement_unit) {
		this.procurement_unit = procurement_unit;
	}
	public String getProcurement_requesteddate() {
		return procurement_requesteddate;
	}
	public void setProcurement_requesteddate(String procurement_requesteddate) {
		this.procurement_requesteddate = procurement_requesteddate;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getProcurement_documenttype() {
		return procurement_documenttype;
	}
	public void setProcurement_documenttype(String procurement_documenttype) {
		this.procurement_documenttype = procurement_documenttype;
	}
	public String getProcurement_documentnumber() {
		return procurement_documentnumber;
	}
	public void setProcurement_documentnumber(String procurement_documentnumber) {
		this.procurement_documentnumber = procurement_documentnumber;
	}
	
	
	
	public Double getProcurement_linetotal2() {
		return procurement_linetotal2;
	}
	public void setProcurement_linetotal2(String procurement_linetotal2) {
		
		 if(procurement_linetotal2 == null) {
			 procurement_linetotal2 = "0.00000001";
		 }
		 else if(procurement_linetotal2.isEmpty() || procurement_linetotal2.isBlank()) {
			 procurement_linetotal2 = "0.0";
		 }
		
		this.procurement_linetotal2 = Double.valueOf(procurement_linetotal2);
	}
	
	
	public Double getProcurement_grandtotal2() {
		return procurement_grandtotal2;
	}
	public void setProcurement_grandtotal2(String procurement_grandtotal2) {
		
		 if(procurement_grandtotal2 == null) {
			 procurement_grandtotal2 = "0.00000001";
		 }
		 else if(procurement_grandtotal2.isEmpty() || procurement_grandtotal2.isBlank()) {
			 procurement_grandtotal2 = "0.0";
		 }

		this.procurement_grandtotal2 =  Double.valueOf(procurement_grandtotal2);
	}

	public Double getProcurement_linetotal() {
		return procurement_linetotal;
	}
	public void setProcurement_linetotal(String procurement_linetotal) {
		
		 if(procurement_linetotal == null) {
			 procurement_linetotal = "0.00000001";
		 }
		 else if(procurement_linetotal.isEmpty() || procurement_linetotal.isBlank()) {
			 procurement_linetotal = "0.0";
		 }
		
		

		this.procurement_linetotal   = Double.valueOf(procurement_linetotal);
	}

	public Double getProcurement_grandtotal() {
		return procurement_grandtotal;
	}
	public void setProcurement_grandtotal(String procurement_grandtotal) {
		
		 if(procurement_grandtotal == null) {
			 procurement_grandtotal = "0.00000001";
		 }
		 else if(procurement_grandtotal.isEmpty() || procurement_grandtotal.isBlank()) {
			 procurement_grandtotal = "0.0";
		 }

		this.procurement_grandtotal  = Double.valueOf(procurement_grandtotal);
	}
	
	
	public void setProcurement_quantityrequested(Double procurement_quantityrequested) {
		this.procurement_quantityrequested = procurement_quantityrequested;
	}
	public void setProcurement_estimatedprice(Double procurement_estimatedprice) {
		this.procurement_estimatedprice = procurement_estimatedprice;
	}
	public Double getProcurement_quantity_ordered() {
		return procurement_quantity_ordered;
	}
	public void setProcurement_quantity_ordered(String procurement_quantity_ordered) {
		
		 if(procurement_quantity_ordered == null) {
			 procurement_quantity_ordered = "0.00000001";
		 }
		 else if(procurement_quantity_ordered.isEmpty() || procurement_quantity_ordered.isBlank()) {
			 procurement_quantity_ordered = "0.0";
		 }
		
		
		this.procurement_quantity_ordered  = Double.valueOf(procurement_quantity_ordered);
	}

	
	public Double getProcurement_unit_price() {
		return procurement_unit_price;
	}
	public void setProcurement_unit_price(String procurement_unit_price) {
		
		 if(procurement_unit_price == null) {
			 procurement_unit_price = "0.0000001";
		 }
		 else if(procurement_unit_price.isEmpty() || procurement_unit_price.isBlank()) {
			 procurement_unit_price = "0.0";
		 }

		this.procurement_unit_price  = Double.valueOf(procurement_unit_price);
	}
	
	
	
	public String getProcurement_payment_status() {
		return procurement_payment_status;
	}
	public void setProcurement_payment_status(String procurement_payment_status) {
		this.procurement_payment_status = procurement_payment_status;
	}
	public String getProcurement_delivery_date() {
		return procurement_delivery_date;
	}
	public void setProcurement_delivery_date(String procurement_delivery_date) {
		this.procurement_delivery_date = procurement_delivery_date;
	}
	public String getProcurement_supplier_name() {
		return procurement_supplier_name;
	}
	public void setProcurement_supplier_name(String procurement_supplier_name) {
		this.procurement_supplier_name = procurement_supplier_name;
	}
	public String getProcurement_supplier_address() {
		return procurement_supplier_address;
	}
	public void setProcurement_supplier_address(String procurement_supplier_address) {
		this.procurement_supplier_address = procurement_supplier_address;
	}
	public String getProcurement_supplier_id() {
		return procurement_supplier_id;
	}
	public void setProcurement_supplier_id(String procurement_supplier_id) {
		this.procurement_supplier_id = procurement_supplier_id;
	}
	public String getProcurement_type() {
		return procurement_type;
	}
	public void setProcurement_type(String procurement_type) {
		this.procurement_type = procurement_type;
	}
	public String getProcurement_status() {
		return procurement_status;
	}
	public void setProcurement_status(String procurement_status) {
		this.procurement_status = procurement_status;
	}
	public String getReceived_date() {
		return received_date;
	}
	public void setReceived_date(String received_date) {
		this.received_date = received_date;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	public String getInspection_status() {
		return inspection_status;
	}
	public void setInspection_status(String inspection_status) {
		this.inspection_status = inspection_status;
	}
	public String getDamage_report() {
		return damage_report;
	}
	public void setDamage_report(String damage_report) {
		this.damage_report = damage_report;
	}
	public String getStorage_location() {
		return storage_location;
	}
	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}
	public Double getQuantity_received() {
		return quantity_received;
	}
	public void setQuantity_received(String quantity_received) {

		
		 if(quantity_received == null) {
			 quantity_received = "0.00000001";
		 }
		 else if(quantity_received.isEmpty() || quantity_received.isBlank()) {
			 quantity_received = "0.0";
		 }
		
		 
		this.quantity_received = Double.valueOf(quantity_received);
		
		
		
	}
	public void setQuantity_received(Double quantity_received) {
		this.quantity_received = quantity_received;
	}
	public void setProcurement_linetotal(Double procurement_linetotal) {
		this.procurement_linetotal = procurement_linetotal;
	}
	public void setProcurement_grandtotal(Double procurement_grandtotal) {
		this.procurement_grandtotal = procurement_grandtotal;
	}
	public void setProcurement_linetotal2(Double procurement_linetotal2) {
		this.procurement_linetotal2 = procurement_linetotal2;
	}
	public void setProcurement_grandtotal2(Double procurement_grandtotal2) {
		this.procurement_grandtotal2 = procurement_grandtotal2;
	}
	public void setProcurement_quantity_ordered(Double procurement_quantity_ordered) {
		this.procurement_quantity_ordered = procurement_quantity_ordered;
	}
	public void setProcurement_unit_price(Double procurement_unit_price) {
		this.procurement_unit_price = procurement_unit_price;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public int getId_refer_by() {
		return id_refer_by;
	}
	public void setId_refer_by(int id_refer_by) {
		this.id_refer_by = id_refer_by;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	
	
	public String getDoc_refer() {
		return doc_refer;
	}
	public void setDoc_refer(String doc_refer) {
		this.doc_refer = doc_refer;
	}
	

	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return String.format(
				"edata_procurement [id=%s, tempId=%s, edata_id=%s, id_refer=%s, id_refer_by=%s, doc=%s, procurement_itemid=%s, procurement_itemname=%s, procurement_quantityrequested=%s, procurement_estimatedprice=%s, procurement_unit=%s, procurement_requesteddate=%s, procurement_documenttype=%s, procurement_documentnumber=%s, procurement_linetotal=%s, procurement_grandtotal=%s, procurement_linetotal2=%s, procurement_grandtotal2=%s, procurement_quantity_ordered=%s, procurement_unit_price=%s, procurement_payment_status=%s, procurement_delivery_date=%s, procurement_supplier_name=%s, procurement_supplier_address=%s, procurement_supplier_id=%s, procurement_type=%s, procurement_status=%s, received_date=%s, tracking_number=%s, inspection_status=%s, damage_report=%s, storage_location=%s, quantity_received=%s]",
				id, tempId, edata_id, id_refer, id_refer_by, doc, procurement_itemid, procurement_itemname,
				procurement_quantityrequested, procurement_estimatedprice, procurement_unit, procurement_requesteddate,
				procurement_documenttype, procurement_documentnumber, procurement_linetotal, procurement_grandtotal,
				procurement_linetotal2, procurement_grandtotal2, procurement_quantity_ordered, procurement_unit_price,
				procurement_payment_status, procurement_delivery_date, procurement_supplier_name,
				procurement_supplier_address, procurement_supplier_id, procurement_type, procurement_status,
				received_date, tracking_number, inspection_status, damage_report, storage_location, quantity_received);
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


