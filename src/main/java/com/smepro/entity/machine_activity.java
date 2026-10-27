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
@Table(name = "machine_activity")
public class machine_activity implements Identifiable  {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ma_record_type;
	private String ma_machine_id;
	private String ma_event_date;
	private String ma_start_time;
	private String ma_end_time;
	private String ma_production_line;
	private String ma_maintenance_type;
	private String ma_maintenance_details;
	private String ma_operator_name;
	private String ma_course;
	private String ma_training_trainer;
	private String ma_training_result;
	private String ma_notes;
	private String ma_title;
	
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


		public String getMa_record_type() {
			return ma_record_type;
		}


		public void setMa_record_type(String ma_record_type) {
			this.ma_record_type = ma_record_type;
		}


		public String getMa_machine_id() {
			return ma_machine_id;
		}


		public void setMa_machine_id(String ma_machine_id) {
			this.ma_machine_id = ma_machine_id;
		}


		public String getMa_event_date() {
			return ma_event_date;
		}


		public void setMa_event_date(String ma_event_date) {
			this.ma_event_date = ma_event_date;
		}


		public String getMa_start_time() {
			return ma_start_time;
		}


		public void setMa_start_time(String ma_start_time) {
			this.ma_start_time = ma_start_time;
		}


		public String getMa_end_time() {
			return ma_end_time;
		}


		public void setMa_end_time(String ma_end_time) {
			this.ma_end_time = ma_end_time;
		}


		public String getMa_production_line() {
			return ma_production_line;
		}


		public void setMa_production_line(String ma_production_line) {
			this.ma_production_line = ma_production_line;
		}


		public String getMa_maintenance_type() {
			return ma_maintenance_type;
		}


		public void setMa_maintenance_type(String ma_maintenance_type) {
			this.ma_maintenance_type = ma_maintenance_type;
		}


		public String getMa_maintenance_details() {
			return ma_maintenance_details;
		}


		public void setMa_maintenance_details(String ma_maintenance_details) {
			this.ma_maintenance_details = ma_maintenance_details;
		}


		public String getMa_operator_name() {
			return ma_operator_name;
		}


		public void setMa_operator_name(String ma_operator_name) {
			this.ma_operator_name = ma_operator_name;
		}


		public String getMa_course() {
			return ma_course;
		}


		public void setMa_course(String ma_course) {
			this.ma_course = ma_course;
		}


		public String getMa_training_trainer() {
			return ma_training_trainer;
		}


		public void setMa_training_trainer(String ma_training_trainer) {
			this.ma_training_trainer = ma_training_trainer;
		}


		public String getMa_training_result() {
			return ma_training_result;
		}


		public void setMa_training_result(String ma_training_result) {
			this.ma_training_result = ma_training_result;
		}


		public String getMa_notes() {
			return ma_notes;
		}


		public void setMa_notes(String ma_notes) {
			this.ma_notes = ma_notes;
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


		public int getEdataId() {
			return edataId;
		}


		public void setEdataId(int edataId) {
			this.edataId = edataId;
		}


		public String getMa_title() {
			return ma_title;
		}


		public void setMa_title(String ma_title) {
			this.ma_title = ma_title;
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
