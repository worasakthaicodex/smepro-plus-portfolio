package com.smepro.models;

public class Goal {
	
	    private int id;   
	    private String name; 
	    private int processes_id; 
	    private String reference_category; // เช่น Line, Product
	    private String reference_category2; // เช่น Line, Product
	    private String reference_category3;
	    private String activity_type;         
	    private int quantity_per_action;   // เช่น 1, 2
	    private String taskBehaviortypes;      // เช่น ตรวจสอบ, อัพโหลด
	    private String frequency;          // เช่น รายปี, รายเดือน
	    private int alert_before_days;     // เช่น 1, 2
	    private String setto;     // เช่น 1, 2
	    private String file_tag;           // เช่น ภาพประกอบ 1, 2
	    private String start_time;         // เช่น 7.00, 8.00
	    private String end_time;           // เช่น 17.00, 20.00
	    private String start_date;  
	    private String end_date;  
	    private String responsible;
	    private String tables;
	    private int status;
	    
	    
	    
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getProcesses_id() {
			return processes_id;
		}
		public void setProcesses_id(int processes_id) {
			this.processes_id = processes_id;
		}
		public String getReference_category() {
			return reference_category;
		}
		public void setReference_category(String reference_category) {
			this.reference_category = reference_category;
		}
		public String getReference_category2() {
			return reference_category2;
		}
		public void setReference_category2(String reference_category2) {
			this.reference_category2 = reference_category2;
		}
		public String getReference_category3() {
			return reference_category3;
		}
		public void setReference_category3(String reference_category3) {
			this.reference_category3 = reference_category3;
		}
		public String getActivity_type() {
			return activity_type;
		}
		public void setActivity_type(String activity_type) {
			this.activity_type = activity_type;
		}
		public int getQuantity_per_action() {
			return quantity_per_action;
		}
		public void setQuantity_per_action(int quantity_per_action) {
			this.quantity_per_action = quantity_per_action;
		}
		public String getTaskBehaviortypes() {
			return taskBehaviortypes;
		}
		public void setTaskBehaviortypes(String taskBehaviortypes) {
			this.taskBehaviortypes = taskBehaviortypes;
		}
		public String getFrequency() {
			return frequency;
		}
		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}
		public int getAlert_before_days() {
			return alert_before_days;
		}
		public void setAlert_before_days(int alert_before_days) {
			this.alert_before_days = alert_before_days;
		}
		public String getSetto() {
			return setto;
		}
		public void setSetto(String setto) {
			this.setto = setto;
		}
		public String getFile_tag() {
			return file_tag;
		}
		public void setFile_tag(String file_tag) {
			this.file_tag = file_tag;
		}
		public String getStart_time() {
			return start_time;
		}
		public void setStart_time(String start_time) {
			this.start_time = start_time;
		}
		public String getEnd_time() {
			return end_time;
		}
		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}
		public String getStart_date() {
			return start_date;
		}
		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}
		public String getResponsible() {
			return responsible;
		}
		public void setResponsible(String responsible) {
			this.responsible = responsible;
		}
		public String getTables() {
			return tables;
		}
		public void setTables(String tables) {
			this.tables = tables;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getEnd_date() {
			return end_date;
		}
		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}
		
	    
	    
	 
	    
	 

}
