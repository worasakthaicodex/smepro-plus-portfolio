package com.smepro.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "product_master")
public class Register_product{

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "product_id", unique = true)
	private int productId;
	
	@Column(name = "product_code")
	private int productCode;
	
	@Column(name = "product_category")
	private int productCategory;
	
	@Column(name = "product_daterun")
	private int productDateadd;
	
	@Column(name = "product_names")
	private int productNames;
	

	private String product_details;
	private String unit;
    private String status;
    private String created_date;
    private String last_updated;
    
    
    private String product_type;
    private String product_dimensions;
    private String product_expiration_date;
    private String product_selling_price;
    private String product_cost_price;
    private String product_wholesale_price;
    
    
    
	 
	@Column(name = "edata_id")
	private int edataId;
	private String tempId;
	
   @Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;
	

}


