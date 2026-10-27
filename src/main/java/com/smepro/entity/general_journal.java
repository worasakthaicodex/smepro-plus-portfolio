package com.smepro.entity;

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

@Entity

@Table(name = "general_journal")
public class general_journal implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column(name = "gj_journal_date")
	private String gjJournalDate; 
	

	
	private String gj_reference_type;
	private String  gj_reference_id;
	private String  gj_description;
	private String gj_account_code;
	private String gj_account_name;
	private String gj_debit_amount;
	private String gj_credit_amount;
	
	
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






	public String getGjJournalDate() {
		return gjJournalDate;
	}






	public void setGjJournalDate(String gjJournalDate) {
		this.gjJournalDate = gjJournalDate;
	}






	public String getGj_reference_type() {
		return gj_reference_type;
	}



	public void setGj_reference_type(String gj_reference_type) {
		this.gj_reference_type = gj_reference_type;
	}



	public String getGj_reference_id() {
		return gj_reference_id;
	}



	public void setGj_reference_id(String gj_reference_id) {
		this.gj_reference_id = gj_reference_id;
	}



	public String getGj_description() {
		return gj_description;
	}



	public void setGj_description(String gj_description) {
		this.gj_description = gj_description;
	}



	public String getGj_account_code() {
		return gj_account_code;
	}



	public void setGj_account_code(String gj_account_code) {
		this.gj_account_code = gj_account_code;
	}



	public String getGj_account_name() {
		return gj_account_name;
	}



	public void setGj_account_name(String gj_account_name) {
		this.gj_account_name = gj_account_name;
	}



	public String getGj_debit_amount() {
		return gj_debit_amount;
	}



	public void setGj_debit_amount(String gj_debit_amount) {
		this.gj_debit_amount = gj_debit_amount;
	}



	public String getGj_credit_amount() {
		return gj_credit_amount;
	}



	public void setGj_credit_amount(String gj_credit_amount) {
		this.gj_credit_amount = gj_credit_amount;
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


}
