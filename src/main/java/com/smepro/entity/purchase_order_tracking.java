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
@Table(name = "purchase_order_tracking")
public class purchase_order_tracking implements Identifiable  {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	private String  pot_tracking_status;
	private String  pot_purchase_order_number;
	private String  pot_vendor_id;
	private String  pot_vendor_name;
	private String 	pot_order_date;
	private String  pot_expected_delivery_date;
	private String  pot_actual_delivery_date;
	private String 	pot_remarks;
	private String  pot_data_nameadd;
	private String 	pot_approved_by;
	private String 	pot_name;
	
	
	@Column(name = "edata_id")
	private int edataId;
	private String tempId; 
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

		  
		public String getPot_name() {
			return pot_name;
		}
		public void setPot_name(String pot_name) {
			this.pot_name = pot_name;
		}
		public String getPot_tracking_status() {
			return pot_tracking_status;
		}

		public void setPot_tracking_status(String pot_tracking_status) {
			this.pot_tracking_status = pot_tracking_status;
		}







		public String getPot_purchase_order_number() {
			return pot_purchase_order_number;
		}







		public void setPot_purchase_order_number(String pot_purchase_order_number) {
			this.pot_purchase_order_number = pot_purchase_order_number;
		}







		public String getPot_vendor_id() {
			return pot_vendor_id;
		}







		public void setPot_vendor_id(String pot_vendor_id) {
			this.pot_vendor_id = pot_vendor_id;
		}







		public String getPot_vendor_name() {
			return pot_vendor_name;
		}







		public void setPot_vendor_name(String pot_vendor_name) {
			this.pot_vendor_name = pot_vendor_name;
		}







		public String getPot_order_date() {
			return pot_order_date;
		}







		public void setPot_order_date(String pot_order_date) {
			this.pot_order_date = pot_order_date;
		}







		public String getPot_expected_delivery_date() {
			return pot_expected_delivery_date;
		}







		public void setPot_expected_delivery_date(String pot_expected_delivery_date) {
			this.pot_expected_delivery_date = pot_expected_delivery_date;
		}







		public String getPot_actual_delivery_date() {
			return pot_actual_delivery_date;
		}







		public void setPot_actual_delivery_date(String pot_actual_delivery_date) {
			this.pot_actual_delivery_date = pot_actual_delivery_date;
		}







		public String getPot_remarks() {
			return pot_remarks;
		}







		public void setPot_remarks(String pot_remarks) {
			this.pot_remarks = pot_remarks;
		}







		public String getPot_data_nameadd() {
			return pot_data_nameadd;
		}







		public void setPot_data_nameadd(String pot_data_nameadd) {
			this.pot_data_nameadd = pot_data_nameadd;
		}







		public String getPot_approved_by() {
			return pot_approved_by;
		}







		public void setPot_approved_by(String pot_approved_by) {
			this.pot_approved_by = pot_approved_by;
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
