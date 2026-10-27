package com.smepro.models;

/*
1.การดึงข้อมูลสำคัญออกมาจาก workflow 
	
สิ่งที่ทำได้
	-ขอวันที่ เพื่อ ดึงข้อมูล 2 เเบบ วันที่เอกสารอยู่ในระบบ,วันที่ส้ราง,วันที่จาก log 
	-วันที่ จาก log workflow
	-วันที่ idoc  เทียบวันที่ใน workflow กับ 
	-ขอสถานะ ล่าสุด
	-ขอชื่อพนักงานที่เกี่ยวข้อง
	-ขอตำเเหน่งงานของพนักงาน
สิ่งที่ต้องการ
	-ขออะไร 
	-เก็บไว้ไหน
	-process_id อะไรที่จะใช้
รูปเเบบการทำงาน 
	-ทุกครั้งที่เรียก	
	
2.การดึงข้อมูลสำคัญออกมาจาก segmentation  โดยอ้างอิง columns_from กับ segmentation เพื่อเรียก

สิ่งที่ทำได้
	-ขอปัจจัยที่ segmentation เลือก
	-ขอจำนวนรายชื่อ 
สิ่งที่ต้องการ
	-ขออะไร
	-เก็บไว้ไหน
	-process_id อะไรที่จะใช้
รูปเเบบการทำงาน 
	-ครั้งเดียวเมื้อถึง ช่วงที่ current_step กำหนด 

	

3.การดึงข้อมูลสำคัญออกมาจาก โปรโมชั่น  โดยอ้างอิง columns_from กับ โปรโมชั่น  เพื่อเรียก
	-ข้ามก่อน

4.ขอข้อมูลที่ที่ รายงานพื้นหลัง  Measurement_rules
	สิ่งที่ทำได้
		-ขอข้อมูล
	สิ่งที่ต้องการ
		-Measurement_rules ไหน
		-เก็บไว้ไหน
		-process_id อะไรที่จะใช้
	รูปเเบบการทำงาน 
		-ทุกครั้งที่เรียก	

	
5.ขอข้อมูลที่ Index
	สิ่งที่ทำได้
		-ขอข้อมูล
	สิ่งที่ต้องการ
		-คอลั้มอะไรที่ต้องการ
		-เก็บไว้ไหน
		-process_id อะไรที่จะใช้
	รูปเเบบการทำงาน 
		-ครั้งเดียวเมื้อถึง ช่วงที่ current_step กำหนด 


ภาพรวม

-กำหนดรูปเเบบ ของ  BuildIn
-เริ่มใช้ทุกครั้ง*** ตามรูปเเบบการทำงาน
-1 BuildIn ขอได้ 1 ค่าส่งเข้าไป
-ทุกอย่างหยุดการทำงาน เมื้อ closing_status ออกจากที่กำหนด

*/	
	

public class BuildIn {
	
	// --- 1. Identity (ระบุตัวตน) ---
    private int id;                 // PK
    private int id_refer;           //management_id
    private int process_id;         // ผูกกับ process ไหน
    private String buildin_title;            // ชื่อตั้งเอง (เช่น "ดึงยอดรวมบิล", "ดึงวันที่สร้าง")
    private String buildin_note;            // ชื่อตั้งเอง (เช่น "ดึงยอดรวมบิล", "ดึงวันที่สร้าง")
    private int measurement_rules_id;
    // --- 2. Controller (กำหนดจังหวะทำงาน) ---
    private int source_type;        // 1=Workflow, 2=Segment, 4=Measure, 5=Index
    private String current_step;    // (สำหรับกลุ่ม B) ทำงานที่ Step ไหน 
    private String closing_status_stop; // (Stop Condition) ถ้าเจอสถานะนี้ใน list ให้หยุดทำงาน (เช่น เสร็จสิ้น, ยกเลิก)

    // --- 3. Source (เอาข้อมูลมาจากไหน) ---
    // ใช้ field นี้เก็บค่าต่างกันตาม source_type
    // Type 1 (Workflow): เก็บ keyword เช่น "create_date", "last_actor", "status_name"
    // Type 2 (Segment): เก็บชื่อ column ต้นทาง เช่น "customer_segment_group"
    // Type 4 (Measure): เก็บ ID ของ Measurement_rules (เช่น "105")
    // Type 5 (Index): เก็บชื่อ column ต้นทาง
    private String source_param;
    private int tocolumn;
    
    
    // --- 4. การเชื่อม EX
    // source_targetColumn : ที่เเสดง ID หรือ Code ลูกค้า
    // keeptargetColumn  : ไปรวบรวมข้อความเหล่านั้นมา
    // Type 5 (Index): เก็บชื่อ column ต้นทาง
    private String  targetColumn;
    private int   ex_process_id;    
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_refer() {
		return id_refer;
	}

	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}

	public int getProcess_id() {
		return process_id;
	}

	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	public String getBuildin_title() {
		return buildin_title;
	}

	public void setBuildin_title(String buildin_title) {
		this.buildin_title = buildin_title;
	}

	public int getSource_type() {
		return source_type;
	}

	public void setSource_type(int source_type) {
		this.source_type = source_type;
	}

	public String getCurrent_step() {
		return current_step;
	}

	public void setCurrent_step(String current_step) {
		this.current_step = current_step;
	}

	public String getClosing_status_stop() {
		return closing_status_stop;
	}

	public void setClosing_status_stop(String closing_status_stop) {
		this.closing_status_stop = closing_status_stop;
	}

	public String getSource_param() {
		return source_param;
	}

	public void setSource_param(String source_param) {
		this.source_param = source_param;
	}

	public int getTocolumn() {
		return tocolumn;
	}

	public void setTocolumn(int tocolumn) {
		this.tocolumn = tocolumn;
	}

	public String getBuildin_note() {
		return buildin_note;
	}

	public void setBuildin_note(String buildin_note) {
		this.buildin_note = buildin_note;
	}

	public int getMeasurement_rules_id() {
		return measurement_rules_id;
	}

	public void setMeasurement_rules_id(int measurement_rules_id) {
		this.measurement_rules_id = measurement_rules_id;
	}

	public String getTargetColumn() {
		return targetColumn;
	}

	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}

	public int getEx_process_id() {
		return ex_process_id;
	}

	public void setEx_process_id(int ex_process_id) {
		this.ex_process_id = ex_process_id;
	}


	
	
	
}

