package com.smepro.entity;

import java.sql.Timestamp;
import java.util.List;

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
@Table(name = "stock")
public class Register_stock {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "stock_id", unique = true)
	private int stockId;
	
	@Column(name = "stock_code")
	private String stockCode;
	
	@Column(name = "stock_type")
	private String stockType;
	
	@Column(name = "stock_list")
	private String stockList;
	
	@Column(name = "stock_receipt_date")
	private String stockReceiptDate;
	
	
	
	private String stock_details;
	private String stock_remaining;
	private String stock_price;
	private String stock_quantity;
	private String stock_received;
	private String stock_group;
	
	private String stock_unit;
	private String stock_storage;
	private String stock_receipt_dateupdate;
	private String stock_payout;
	private String stock_pd;
	private String stock_batch;
	private String stock_batch2;
	private int  stock_eform;
	private String tracking_number;
	private String stock_max;
	private String stock_min;
	private String stock_expdate;
	private String stock_support_dataflow;

	
	
	@Column(name = "edata_id")
	private int edataId;
	private String tempId;
	
    @Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;
	

}
