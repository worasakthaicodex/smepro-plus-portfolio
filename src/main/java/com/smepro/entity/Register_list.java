package com.smepro.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "register")
public class Register_list {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "register_id", unique = true)
	private int registerId;
	
	@Column(name = "register_code")
	private int registerCode;
	
	@Column(name = "register_type")
	private int registerType;
	
	@Column(name = "register_daterun")
	private int registerDateadd;
	
	@Column(name = "register_name")
	private int registerName;
	

	 private String register_groups; 
	 private String register_address; 
	 private String register_note;
	 private int register_poin;
	 
	 private String contact_person;
	 private String phone_number;
	 private String mobile_number;
	 private String website;
	 private String social;
	 private String tax_id;
	 private String branch_code;
	 private String business_type;
	 private String payment_terms;	 
	 private String credit_limit;	 	 
	 private String bank_account;		  
	 private String preferred_currency;	
		 
		 
		  
		  
		  
		  
		 
		@Column(name = "edata_id")
		private int edataId;
		private String tempId;
		
	    @Column(name = "created_at", updatable = false, insertable = false)
		private Timestamp createdAt;

		@Column(name = "updated_at", insertable = false)
		private Timestamp updatedAt;
		
	
}

