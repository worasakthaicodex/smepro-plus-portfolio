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

@Table(name = "account_ledger")
public class account_ledger implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

	private String al_source_type; 
	private String title; 
	private String al_source_id; 
	
	@Column(name = "al_entry_date")
	private String alEntryDate; 
	
	private String al_account_code; 
	private String al_debit; 
	private String al_credit; 
	private String al_description; 
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



	public String getAl_source_type() {
		return al_source_type;
	}



	public void setAl_source_type(String al_source_type) {
		this.al_source_type = al_source_type;
	}



	public String getAl_source_id() {
		return al_source_id;
	}



	public void setAl_source_id(String al_source_id) {
		this.al_source_id = al_source_id;
	}




	public String getAlEntryDate() {
		return alEntryDate;
	}



	public void setAlEntryDate(String alEntryDate) {
		this.alEntryDate = alEntryDate;
	}



	public String getAl_account_code() {
		return al_account_code;
	}



	public void setAl_account_code(String al_account_code) {
		this.al_account_code = al_account_code;
	}



	public String getAl_debit() {
		return al_debit;
	}



	public void setAl_debit(String al_debit) {
		this.al_debit = al_debit;
	}



	public String getAl_credit() {
		return al_credit;
	}



	public void setAl_credit(String al_credit) {
		this.al_credit = al_credit;
	}



	public String getAl_description() {
		return al_description;
	}



	public void setAl_description(String al_description) {
		this.al_description = al_description;
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
