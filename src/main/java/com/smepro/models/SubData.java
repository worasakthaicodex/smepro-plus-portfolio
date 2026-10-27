package com.smepro.models;

import java.util.List;
import java.util.Optional;

public class SubData {


	private String  tempId;
	private String  datalink_name;
	private String  id;
	private String  description;
	private String  type;
	private String  qty;
	private String  unit;
	private int  id_refer;
	private String  actual_price;
	private String  received_qty;
	private String  issued_qty;
	private String  issued_amount;
	private String  status;
	
	
	private int  id_cleared_by;
	private int  id_stock;
	private String  pd;
	private String  batch;
	private String  date_action;
	private String  requester;
	
	
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	
	
	public String getDatalink_name() {
		return datalink_name;
	}
	public void setDatalink_name(String datalink_name) {
		this.datalink_name = datalink_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		
		
		String Optional_qty = Optional.ofNullable(qty)
	                .filter(s -> !s.trim().isEmpty()) // เช็คว่าค่าไม่ใช่ค่าว่าง
	                .orElse("0.0");
		
		this.qty = Optional_qty;
	}
	
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getActual_price() {
		return actual_price;
	}
	public void setActual_price(String actual_price) {
		this.actual_price = actual_price;
	}
	public String getReceived_qty() {
		return received_qty;
	}
	public void setReceived_qty(String received_qty) {

		String Optional_received_qty = Optional.ofNullable(received_qty)
                .filter(s -> !s.trim().isEmpty()) // เช็คว่าค่าไม่ใช่ค่าว่าง
                .orElse("0.0");
		
		
		this.received_qty = Optional_received_qty;
		
	}
	public String getIssued_qty() {
		return issued_qty;
	}
	public void setIssued_qty(String issued_qty) {
		
		String Optional_issued_qty = Optional.ofNullable(issued_qty)
                .filter(s -> !s.trim().isEmpty()) // เช็คว่าค่าไม่ใช่ค่าว่าง
                .orElse("0.0");
		
		
		this.issued_qty = Optional_issued_qty;
	}
	
	
	
	public String getIssued_amount() {
		return issued_amount;
	}
	public void setIssued_amount(String issued_amount) {
		this.issued_amount = issued_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId_cleared_by() {
		return id_cleared_by;
	}
	public void setId_cleared_by(int id_cleared_by) {
		this.id_cleared_by = id_cleared_by;
	}
	public int getId_stock() {
		return id_stock;
	}
	public void setId_stock(int id_stock) {
		this.id_stock = id_stock;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDate_action() {
		return date_action;
	}
	public void setDate_action(String date_action) {
		this.date_action = date_action;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	
	
	
	/*
	 * 
	 * CREATE TABLE sub_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255),  -- คำอธิบายสินค้า
    type ENUM('PR', 'PO', 'RECEIVE', 'ISSUE') NOT NULL,  -- ประเภทของรายการ
    qty INT NOT NULL,  -- จำนวน
    unit VARCHAR(50),  -- หน่วย
    id_refer INT,  -- อ้างอิงไปยัง PR, PO, Receiving หรือ Issuing
    estimated_price DECIMAL(10,2),  -- ราคาประมาณการ (ใช้กับ PR)
    actual_price DECIMAL(10,2),  -- ราคาจริง (ใช้กับ PO)
    received_qty INT DEFAULT 0,  -- จำนวนที่รับ (ใช้กับ Receiving)
    received_by VARCHAR(255),  -- ผู้รับสินค้า (ใช้กับ Receiving)
    issued_qty INT DEFAULT 0,  -- จำนวนที่เบิก (ใช้กับ Issuing)
    issued_amount INT DEFAULT 0,  -- จำนวนที่จ่าย (ใช้กับ Issuing)
    remaining_qty INT DEFAULT 0,  -- จำนวนคงเหลือ (ใช้กับ Issuing)
    status ENUM('PENDING', 'APPROVED', 'CLEARED') DEFAULT 'PENDING', -- สถานะ
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
	 * 
	 * 
	 * 
	description	ทุกประเภท	คำอธิบายของสินค้า
	type	ทุกประเภท	ใช้ระบุว่าเป็น PR, PO, RECEIVE หรือ ISSUE
	qty	PR, PO	จำนวนสินค้ารวม
	unit	ทุกประเภท	หน่วยของสินค้า
	id_refer	ทุกประเภท	ใช้อ้างอิง ID ของ PR, PO, Receiving หรือ Issuing
	estimated_price	PR	ราคาประมาณการของ PR
	actual_price	PO	ราคาจริงของ PO
	received_qty	RECEIVE	จำนวนที่รับเข้าสต็อก
	received_by	RECEIVE	ชื่อผู้รับสินค้า
	issued_qty	ISSUE	จำนวนที่เบิก
	issued_amount	ISSUE	จำนวนที่จ่ายออกจากคลัง
	remaining_qty	ISSUE	จำนวนคงเหลือหลังจากเบิก
	status	ทุกประเภท	สถานะ PENDING (รอดำเนินการ), APPROVED (อนุมัติแล้ว), CLEARED (ใช้ไปแล้ว)
	*/
	
	
	
	
	
	
	
	
}
