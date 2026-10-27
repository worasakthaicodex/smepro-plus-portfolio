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
@Table(name = "maintenance_requests")
public class maintenance_requests implements Identifiable  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	
	private String mr_record_type;
	  private String mr_machine_id;
	  private String mr_request_date;
	  private String mr_priority;
	  private String mr_maintenance_type;
	  private String mr_maintenance_details;
	  private String mr_reported_by;
	  private String mr_notes;
	  private String mr_assigned_to;
	  private String mr_result_status;
	  private String mr_result_descriptions;
	  private String mr_completion_date;
	  private String mr_downtime_minutes;
	  private String mr_parts_used;
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

		
		public String getMr_record_type() {
			return mr_record_type;
		}


		public void setMr_record_type(String mr_record_type) {
			this.mr_record_type = mr_record_type;
		}


		public String getMr_machine_id() {
			return mr_machine_id;
		}


		public void setMr_machine_id(String mr_machine_id) {
			this.mr_machine_id = mr_machine_id;
		}


		public String getMr_request_date() {
			return mr_request_date;
		}


		public void setMr_request_date(String mr_request_date) {
			this.mr_request_date = mr_request_date;
		}


		public String getMr_priority() {
			return mr_priority;
		}


		public void setMr_priority(String mr_priority) {
			this.mr_priority = mr_priority;
		}


		public String getMr_maintenance_type() {
			return mr_maintenance_type;
		}


		public void setMr_maintenance_type(String mr_maintenance_type) {
			this.mr_maintenance_type = mr_maintenance_type;
		}


		public String getMr_maintenance_details() {
			return mr_maintenance_details;
		}


		public void setMr_maintenance_details(String mr_maintenance_details) {
			this.mr_maintenance_details = mr_maintenance_details;
		}


		public String getMr_reported_by() {
			return mr_reported_by;
		}


		public void setMr_reported_by(String mr_reported_by) {
			this.mr_reported_by = mr_reported_by;
		}


		public String getMr_notes() {
			return mr_notes;
		}


		public void setMr_notes(String mr_notes) {
			this.mr_notes = mr_notes;
		}


		public String getMr_assigned_to() {
			return mr_assigned_to;
		}


		public void setMr_assigned_to(String mr_assigned_to) {
			this.mr_assigned_to = mr_assigned_to;
		}


		public String getMr_result_status() {
			return mr_result_status;
		}


		public void setMr_result_status(String mr_result_status) {
			this.mr_result_status = mr_result_status;
		}


		public String getMr_result_descriptions() {
			return mr_result_descriptions;
		}


		public void setMr_result_descriptions(String mr_result_descriptions) {
			this.mr_result_descriptions = mr_result_descriptions;
		}


		public String getMr_completion_date() {
			return mr_completion_date;
		}


		public void setMr_completion_date(String mr_completion_date) {
			this.mr_completion_date = mr_completion_date;
		}


		public String getMr_downtime_minutes() {
			return mr_downtime_minutes;
		}


		public void setMr_downtime_minutes(String mr_downtime_minutes) {
			this.mr_downtime_minutes = mr_downtime_minutes;
		}


		public String getMr_parts_used() {
			return mr_parts_used;
		}


		public void setMr_parts_used(String mr_parts_used) {
			this.mr_parts_used = mr_parts_used;
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
