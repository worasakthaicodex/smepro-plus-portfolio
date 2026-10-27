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
@Table(name = "edata_workrule")
public class edata_workrule implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String workrule_proceedings;
	private String rule_name;
	private String rule_type;
	private String position_name;
	private String workrule_status;
	private String annual_leave_days;
	private String sick_leave_days;
	private String min_service_months;
	private String carry_forward_allowed;
	private String carry_forward_max_days;
	private String every_months;
	private String add_annual_days;
	private String add_sick_days;
	private String max_annual_days;
	private String max_sick_days;
	private String threshold_minutes;
	private String deduct_per_interval;
	private String interval_minutes;
	private String max_monthly_deduction;
	private String absent_deduction_per_day;
	private String work_hours;
	private String break_hours;
	private String overtime_start_after_hours;
	private String shift_start_time;
	private String shift_end_time;
	private String shift_bonus_per;
	private String rotation_interval_days;
	private String rotation_sequence;
	private String course_name;
	private String duration_hours;
	private String description;
	private String mandatory;
	private String validity_months;
	private String training_type;
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


		public String getWorkrule_proceedings() {
			return workrule_proceedings;
		}


		public void setWorkrule_proceedings(String workrule_proceedings) {
			this.workrule_proceedings = workrule_proceedings;
		}


		public String getRule_name() {
			return rule_name;
		}


		public void setRule_name(String rule_name) {
			this.rule_name = rule_name;
		}


		public String getRule_type() {
			return rule_type;
		}


		public void setRule_type(String rule_type) {
			this.rule_type = rule_type;
		}


		public String getPosition_name() {
			return position_name;
		}


		public void setPosition_name(String position_name) {
			this.position_name = position_name;
		}


		public String getWorkrule_status() {
			return workrule_status;
		}


		public void setWorkrule_status(String workrule_status) {
			this.workrule_status = workrule_status;
		}


		public String getAnnual_leave_days() {
			return annual_leave_days;
		}


		public void setAnnual_leave_days(String annual_leave_days) {
			this.annual_leave_days = annual_leave_days;
		}


		public String getSick_leave_days() {
			return sick_leave_days;
		}


		public void setSick_leave_days(String sick_leave_days) {
			this.sick_leave_days = sick_leave_days;
		}


		public String getMin_service_months() {
			return min_service_months;
		}


		public void setMin_service_months(String min_service_months) {
			this.min_service_months = min_service_months;
		}


		public String getCarry_forward_allowed() {
			return carry_forward_allowed;
		}


		public void setCarry_forward_allowed(String carry_forward_allowed) {
			this.carry_forward_allowed = carry_forward_allowed;
		}


		public String getCarry_forward_max_days() {
			return carry_forward_max_days;
		}


		public void setCarry_forward_max_days(String carry_forward_max_days) {
			this.carry_forward_max_days = carry_forward_max_days;
		}


		public String getEvery_months() {
			return every_months;
		}


		public void setEvery_months(String every_months) {
			this.every_months = every_months;
		}


		public String getAdd_annual_days() {
			return add_annual_days;
		}


		public void setAdd_annual_days(String add_annual_days) {
			this.add_annual_days = add_annual_days;
		}


		public String getAdd_sick_days() {
			return add_sick_days;
		}


		public void setAdd_sick_days(String add_sick_days) {
			this.add_sick_days = add_sick_days;
		}


		public String getMax_annual_days() {
			return max_annual_days;
		}


		public void setMax_annual_days(String max_annual_days) {
			this.max_annual_days = max_annual_days;
		}


		public String getMax_sick_days() {
			return max_sick_days;
		}


		public void setMax_sick_days(String max_sick_days) {
			this.max_sick_days = max_sick_days;
		}


		public String getThreshold_minutes() {
			return threshold_minutes;
		}


		public void setThreshold_minutes(String threshold_minutes) {
			this.threshold_minutes = threshold_minutes;
		}


		public String getDeduct_per_interval() {
			return deduct_per_interval;
		}


		public void setDeduct_per_interval(String deduct_per_interval) {
			this.deduct_per_interval = deduct_per_interval;
		}


		public String getInterval_minutes() {
			return interval_minutes;
		}


		public void setInterval_minutes(String interval_minutes) {
			this.interval_minutes = interval_minutes;
		}


		public String getMax_monthly_deduction() {
			return max_monthly_deduction;
		}


		public void setMax_monthly_deduction(String max_monthly_deduction) {
			this.max_monthly_deduction = max_monthly_deduction;
		}


		public String getAbsent_deduction_per_day() {
			return absent_deduction_per_day;
		}


		public void setAbsent_deduction_per_day(String absent_deduction_per_day) {
			this.absent_deduction_per_day = absent_deduction_per_day;
		}


		public String getWork_hours() {
			return work_hours;
		}


		public void setWork_hours(String work_hours) {
			this.work_hours = work_hours;
		}


		public String getBreak_hours() {
			return break_hours;
		}


		public void setBreak_hours(String break_hours) {
			this.break_hours = break_hours;
		}


		public String getOvertime_start_after_hours() {
			return overtime_start_after_hours;
		}


		public void setOvertime_start_after_hours(String overtime_start_after_hours) {
			this.overtime_start_after_hours = overtime_start_after_hours;
		}


		public String getShift_start_time() {
			return shift_start_time;
		}


		public void setShift_start_time(String shift_start_time) {
			this.shift_start_time = shift_start_time;
		}


		public String getShift_end_time() {
			return shift_end_time;
		}


		public void setShift_end_time(String shift_end_time) {
			this.shift_end_time = shift_end_time;
		}


		public String getShift_bonus_per() {
			return shift_bonus_per;
		}


		public void setShift_bonus_per(String shift_bonus_per) {
			this.shift_bonus_per = shift_bonus_per;
		}


		public String getRotation_interval_days() {
			return rotation_interval_days;
		}


		public void setRotation_interval_days(String rotation_interval_days) {
			this.rotation_interval_days = rotation_interval_days;
		}


		public String getRotation_sequence() {
			return rotation_sequence;
		}


		public void setRotation_sequence(String rotation_sequence) {
			this.rotation_sequence = rotation_sequence;
		}


		public String getCourse_name() {
			return course_name;
		}


		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}


		public String getDuration_hours() {
			return duration_hours;
		}


		public void setDuration_hours(String duration_hours) {
			this.duration_hours = duration_hours;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public String getMandatory() {
			return mandatory;
		}


		public void setMandatory(String mandatory) {
			this.mandatory = mandatory;
		}


		public String getValidity_months() {
			return validity_months;
		}


		public void setValidity_months(String validity_months) {
			this.validity_months = validity_months;
		}


		public String getTraining_type() {
			return training_type;
		}


		public void setTraining_type(String training_type) {
			this.training_type = training_type;
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
