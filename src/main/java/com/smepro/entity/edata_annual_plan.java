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
import com.smepro.models.department;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "edata_annual_plan")
public class edata_annual_plan implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	private String annual_plan_proceedings;
	private String title;
	private String annual_type;
	private String annual_plan_code;
	private String annual_dateadd;
	private int annual_year;
	private String department;
	private String annual_budget;
	private String general_target;
	private int max_personnel;                 
	private String production_target_units;
	private String production_target_revenue;
	private String max_downtime_minutes;
	private String min_machine_speed;
	private int total_jobs;
	private String machinery_id;
	private String notes;
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


			public String getAnnual_plan_code() {
				return annual_plan_code;
			}


			public void setAnnual_plan_code(String annual_plan_code) {
				this.annual_plan_code = annual_plan_code;
			}


			public String getAnnual_dateadd() {
				return annual_dateadd;
			}


			public void setAnnual_dateadd(String annual_dateadd) {
				this.annual_dateadd = annual_dateadd;
			}


			public int getAnnual_year() {
				return annual_year;
			}


			public void setAnnual_year(int annual_year) {
				this.annual_year = annual_year;
			}


			public String getDepartment() {
				return department;
			}


			public void setDepartment(String department) {
				this.department = department;
			}


			public String getAnnual_budget() {
				return annual_budget;
			}


			public void setAnnual_budget(String annual_budget) {
				this.annual_budget = annual_budget;
			}


			public String getGeneral_target() {
				return general_target;
			}


			public void setGeneral_target(String general_target) {
				this.general_target = general_target;
			}


			public int getMax_personnel() {
				return max_personnel;
			}


			public void setMax_personnel(int max_personnel) {
				this.max_personnel = max_personnel;
			}


			public String getProduction_target_units() {
				return production_target_units;
			}


			public void setProduction_target_units(String production_target_units) {
				this.production_target_units = production_target_units;
			}


			public String getProduction_target_revenue() {
				return production_target_revenue;
			}


			public void setProduction_target_revenue(String production_target_revenue) {
				this.production_target_revenue = production_target_revenue;
			}


			public String getMax_downtime_minutes() {
				return max_downtime_minutes;
			}


			public void setMax_downtime_minutes(String max_downtime_minutes) {
				this.max_downtime_minutes = max_downtime_minutes;
			}


			public String getMin_machine_speed() {
				return min_machine_speed;
			}


			public void setMin_machine_speed(String min_machine_speed) {
				this.min_machine_speed = min_machine_speed;
			}


			public int getTotal_jobs() {
				return total_jobs;
			}


			public void setTotal_jobs(int total_jobs) {
				this.total_jobs = total_jobs;
			}


			public String getMachinery_id() {
				return machinery_id;
			}


			public void setMachinery_id(String machinery_id) {
				this.machinery_id = machinery_id;
			}


			public String getNotes() {
				return notes;
			}


			public void setNotes(String notes) {
				this.notes = notes;
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


			public String getAnnual_plan_proceedings() {
				return annual_plan_proceedings;
			}


			public void setAnnual_plan_proceedings(String annual_plan_proceedings) {
				this.annual_plan_proceedings = annual_plan_proceedings;
			}


			public String getTitle() {
				return title;
			}


			public void setTitle(String title) {
				this.title = title;
			}


			public String getAnnual_type() {
				return annual_type;
			}


			public void setAnnual_type(String annual_type) {
				this.annual_type = annual_type;
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
