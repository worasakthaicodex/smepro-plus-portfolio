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
@Table(name = "request_for_quotation")
public class request_for_quotation implements Identifiable {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	private String  rfq_request_date;
	private String  rfq_project_name;
	private String  rfq_bidder_id;
	private String  rfq_name_of_bidder;
	private String  rfq_bid_price;
	private String  rfq_discount;
	private String  rfq_net_bid;
	private String  rfq_note;
	private String  rfq_remarks;
	private String  rfq_data_nameadd;
	private String  rfq_approved_by;
	@Column(name = "edata_id")
	private int edataId;
	private String tempId; 
	
	 

	public void setId(int id) {
		this.id = id;
	}

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

	    public String getRfq_request_date() {
			return rfq_request_date;
		}


		public void setRfq_request_date(String rfq_request_date) {
			this.rfq_request_date = rfq_request_date;
		}


		public String getRfq_project_name() {
			return rfq_project_name;
		}


		public void setRfq_project_name(String rfq_project_name) {
			this.rfq_project_name = rfq_project_name;
		}


		public String getRfq_bidder_id() {
			return rfq_bidder_id;
		}


		public void setRfq_bidder_id(String rfq_bidder_id) {
			this.rfq_bidder_id = rfq_bidder_id;
		}


		public String getRfq_name_of_bidder() {
			return rfq_name_of_bidder;
		}


		public void setRfq_name_of_bidder(String rfq_name_of_bidder) {
			this.rfq_name_of_bidder = rfq_name_of_bidder;
		}


		public String getRfq_bid_price() {
			return rfq_bid_price;
		}


		public void setRfq_bid_price(String rfq_bid_price) {
			this.rfq_bid_price = rfq_bid_price;
		}


		public String getRfq_discount() {
			return rfq_discount;
		}


		public void setRfq_discount(String rfq_discount) {
			this.rfq_discount = rfq_discount;
		}


		public String getRfq_net_bid() {
			return rfq_net_bid;
		}


		public void setRfq_net_bid(String rfq_net_bid) {
			this.rfq_net_bid = rfq_net_bid;
		}


		public String getRfq_note() {
			return rfq_note;
		}


		public void setRfq_note(String rfq_note) {
			this.rfq_note = rfq_note;
		}


		public String getRfq_remarks() {
			return rfq_remarks;
		}


		public void setRfq_remarks(String rfq_remarks) {
			this.rfq_remarks = rfq_remarks;
		}


		public String getRfq_data_nameadd() {
			return rfq_data_nameadd;
		}


		public void setRfq_data_nameadd(String rfq_data_nameadd) {
			this.rfq_data_nameadd = rfq_data_nameadd;
		}


		public String getRfq_approved_by() {
			return rfq_approved_by;
		}


		public void setRfq_approved_by(String rfq_approved_by) {
			this.rfq_approved_by = rfq_approved_by;
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
