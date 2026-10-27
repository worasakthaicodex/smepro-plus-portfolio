package com.smepro.entity;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smepro.dao.Identifiable;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "fa_invoices")
public class fa_invoices implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fi_invoice_type;
	private String fi_invoice_date;
	private String fi_vendor_customer_id;
	private String fi_vendor_customer_name;
	private String fi_list;
	private String fi_price;
	private String fi_quantity;
	private String fi_unit;
	private String fi_discount;
	private String fi_amount_total;
	private String fi_amount_vat;
	private String fi_amount_net;
	private String fi_status;
	private String fi_due_date;
	private String fi_data_nameadd;
	private String fi_approved_by;
	@Column(name = "edata_id")
	private int edataId;
	private String tempId; 
	private String title; 
	private String  doc;
	
	@Transient
	private String  check;
	@Transient
	private String  id_refer;
	@Transient
	private int count;
	
	@Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;
	
	  
	
	@Transient
	private List<Object> join1;
	@Transient
	private List<Object> join2;


  @Column(name = "processId", columnDefinition = "int default 0")
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
	        return this.id;
	 }

		
		public String getFi_invoice_type() {
			return fi_invoice_type;
		}


		public void setFi_invoice_type(String fi_invoice_type) {
			this.fi_invoice_type = fi_invoice_type;
		}


		public String getFi_invoice_date() {
			return fi_invoice_date;
		}


		public void setFi_invoice_date(String fi_invoice_date) {
			this.fi_invoice_date = fi_invoice_date;
		}


		public String getFi_vendor_customer_id() {
			return fi_vendor_customer_id;
		}


		public void setFi_vendor_customer_id(String fi_vendor_customer_id) {
			this.fi_vendor_customer_id = fi_vendor_customer_id;
		}


		public String getFi_vendor_customer_name() {
			return fi_vendor_customer_name;
		}


		public void setFi_vendor_customer_name(String fi_vendor_customer_name) {
			this.fi_vendor_customer_name = fi_vendor_customer_name;
		}


		public String getFi_list() {
			return fi_list;
		}


		public void setFi_list(String fi_list) {
			this.fi_list = fi_list;
		}


		public String getFi_quantity() {
			return fi_quantity;
		}


		public void setFi_quantity(String fi_quantity) {
			this.fi_quantity = fi_quantity;
		}


		public String getFi_discount() {
			return fi_discount;
		}


		public void setFi_discount(String fi_discount) {
			this.fi_discount = fi_discount;
		}


		public String getFi_amount_total() {
			return fi_amount_total;
		}


		public void setFi_amount_total(String fi_amount_total) {
			this.fi_amount_total = fi_amount_total;
		}


		public String getFi_amount_vat() {
			return fi_amount_vat;
		}


		public void setFi_amount_vat(String fi_amount_vat) {
			this.fi_amount_vat = fi_amount_vat;
		}


		public String getFi_amount_net() {
			return fi_amount_net;
		}


		public void setFi_amount_net(String fi_amount_net) {
			this.fi_amount_net = fi_amount_net;
		}


		public String getFi_status() {
			return fi_status;
		}


		public void setFi_status(String fi_status) {
			this.fi_status = fi_status;
		}


		public String getFi_due_date() {
			return fi_due_date;
		}


		public void setFi_due_date(String fi_due_date) {
			this.fi_due_date = fi_due_date;
		}


		public String getFi_data_nameadd() {
			return fi_data_nameadd;
		}


		public void setFi_data_nameadd(String fi_data_nameadd) {
			this.fi_data_nameadd = fi_data_nameadd;
		}


		public String getFi_approved_by() {
			return fi_approved_by;
		}


		public void setFi_approved_by(String fi_approved_by) {
			this.fi_approved_by = fi_approved_by;
		}


	

		public int getEdataId() {
			return edataId;
		}


		public void setEdataId(int edataId) {
			this.edataId = edataId;
		}


		public String getTempId() {
			return tempId;
		}


		public void setTempId(String tempId) {
			this.tempId = tempId;
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


		public Timestamp getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}


		public Timestamp getUpdatedAt() {
			return updatedAt;
		}


		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}


		public void setId(int id) {
			this.id = id;
		}

		
		
		

		public String getFi_price() {
			return fi_price;
		}


		public void setFi_price(String fi_price) {
			this.fi_price = fi_price;
		}


		public String getFi_unit() {
			return fi_unit;
		}


		public void setFi_unit(String fi_unit) {
			this.fi_unit = fi_unit;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
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
