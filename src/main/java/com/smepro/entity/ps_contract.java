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
@Table(name = "ps_contract")
public class ps_contract implements Identifiable  {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	private String psc_refer_id;
	private String psc_vendor_id;
	private String psc_vendor_name;
	private String psc_contract_date;
	private String psc_start_date;
	private String psc_end_date;
	private String psc_total_value;
	private String psc_terms_and_conditions;
	private String psc_data_nameadd;
	private String psc_approved_by;
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


	public String getPsc_refer_id() {
		return psc_refer_id;
	}


	public void setPsc_refer_id(String psc_refer_id) {
		this.psc_refer_id = psc_refer_id;
	}


	public String getPsc_vendor_id() {
		return psc_vendor_id;
	}


	public void setPsc_vendor_id(String psc_vendor_id) {
		this.psc_vendor_id = psc_vendor_id;
	}


	public String getPsc_vendor_name() {
		return psc_vendor_name;
	}


	public void setPsc_vendor_name(String psc_vendor_name) {
		this.psc_vendor_name = psc_vendor_name;
	}


	public String getPsc_contract_date() {
		return psc_contract_date;
	}


	public void setPsc_contract_date(String psc_contract_date) {
		this.psc_contract_date = psc_contract_date;
	}


	public String getPsc_start_date() {
		return psc_start_date;
	}


	public void setPsc_start_date(String psc_start_date) {
		this.psc_start_date = psc_start_date;
	}


	public String getPsc_end_date() {
		return psc_end_date;
	}


	public void setPsc_end_date(String psc_end_date) {
		this.psc_end_date = psc_end_date;
	}


	public String getPsc_total_value() {
		return psc_total_value;
	}


	public void setPsc_total_value(String psc_total_value) {
		this.psc_total_value = psc_total_value;
	}


	public String getPsc_terms_and_conditions() {
		return psc_terms_and_conditions;
	}


	public void setPsc_terms_and_conditions(String psc_terms_and_conditions) {
		this.psc_terms_and_conditions = psc_terms_and_conditions;
	}


	public String getPsc_data_nameadd() {
		return psc_data_nameadd;
	}


	public void setPsc_data_nameadd(String psc_data_nameadd) {
		this.psc_data_nameadd = psc_data_nameadd;
	}


	public String getPsc_approved_by() {
		return psc_approved_by;
	}


	public void setPsc_approved_by(String psc_approved_by) {
		this.psc_approved_by = psc_approved_by;
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
