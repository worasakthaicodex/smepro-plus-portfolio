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
@Table(name = "hr_action")
public class hr_action implements Identifiable  {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  private String title;
	  private int  employee_id;
	  private String employee_name;
	  private String event_type;


	  private String action_date;
	  private String end_date;                  


	  private String reason;
	  private String description;



	 private String original_shift_name;
	 private String requested_shift_name;
	 private String shift_date;

	  private String leave_type;
	  private String leave_days_start;
	  private String leave_days_end;
	  private String total_days;
	  private String total_hour;
	  private String total_minute;
	  private String training_name;
	  private String training_result;
	  private String training_score;
	  private String training_course;
	  private String training_course_code;
	  private String training_hour;
	  

	  private String evaluation_round;
	  private String kpi_score ;
	  private String behavior_score;
	  private String total_score;
	  private String promotion_recommendation;
	  private String salary_adjustment;


	  private String check_in_time;
	  private String check_out_time;
	  private String work_hours;
	  private String late_minutes;
	  private String overtime_hours;


	  private String old_salary;
	  private String new_salary;


	  private String  old_position;
	  private String new_position;
	  private String old_department;
	  private String new_department;
	  private String  change_type;


	  private String discipline_type;
	  private String severity_level;
	  private String  discipline_expire_date;


	  private String submission_type;     
	  private String submission_ref;
	  private String submitted_amountprivate;
	  
	  
	  private int cut_off_flag;
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


		public int getEmployee_id() {
			return employee_id;
		}


		public void setEmployee_id(int employee_id) {
			this.employee_id = employee_id;
		}


		public String getEmployee_name() {
			return employee_name;
		}


		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}


		public String getEvent_type() {
			return event_type;
		}


		public void setEvent_type(String event_type) {
			this.event_type = event_type;
		}


		public String getAction_date() {
			return action_date;
		}


		public void setAction_date(String action_date) {
			this.action_date = action_date;
		}


		public String getEnd_date() {
			return end_date;
		}


		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}


		public String getReason() {
			return reason;
		}


		public void setReason(String reason) {
			this.reason = reason;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public String getOriginal_shift_name() {
			return original_shift_name;
		}


		public void setOriginal_shift_name(String original_shift_name) {
			this.original_shift_name = original_shift_name;
		}


		public String getRequested_shift_name() {
			return requested_shift_name;
		}


		public void setRequested_shift_name(String requested_shift_name) {
			this.requested_shift_name = requested_shift_name;
		}


		public String getShift_date() {
			return shift_date;
		}


		public void setShift_date(String shift_date) {
			this.shift_date = shift_date;
		}


		public String getLeave_type() {
			return leave_type;
		}


		public void setLeave_type(String leave_type) {
			this.leave_type = leave_type;
		}


		public String getLeave_days_start() {
			return leave_days_start;
		}


		public void setLeave_days_start(String leave_days_start) {
			this.leave_days_start = leave_days_start;
		}


		public String getLeave_days_end() {
			return leave_days_end;
		}


		public void setLeave_days_end(String leave_days_end) {
			this.leave_days_end = leave_days_end;
		}


		public String getTotal_days() {
			return total_days;
		}


		public void setTotal_days(String total_days) {
			this.total_days = total_days;
		}


		public String getTotal_hour() {
			return total_hour;
		}


		public void setTotal_hour(String total_hour) {
			this.total_hour = total_hour;
		}


		public String getTotal_minute() {
			return total_minute;
		}


		public void setTotal_minute(String total_minute) {
			this.total_minute = total_minute;
		}


		public String getTraining_name() {
			return training_name;
		}


		public void setTraining_name(String training_name) {
			this.training_name = training_name;
		}


		public String getTraining_result() {
			return training_result;
		}


		public void setTraining_result(String training_result) {
			this.training_result = training_result;
		}


		public String getTraining_score() {
			return training_score;
		}


		public void setTraining_score(String training_score) {
			this.training_score = training_score;
		}


		public String getTraining_course() {
			return training_course;
		}


		public void setTraining_course(String training_course) {
			this.training_course = training_course;
		}


		public String getTraining_course_code() {
			return training_course_code;
		}


		public void setTraining_course_code(String training_course_code) {
			this.training_course_code = training_course_code;
		}


		public String getTraining_hour() {
			return training_hour;
		}


		public void setTraining_hour(String training_hour) {
			this.training_hour = training_hour;
		}


		public String getEvaluation_round() {
			return evaluation_round;
		}


		public void setEvaluation_round(String evaluation_round) {
			this.evaluation_round = evaluation_round;
		}


		public String getKpi_score() {
			return kpi_score;
		}


		public void setKpi_score(String kpi_score) {
			this.kpi_score = kpi_score;
		}


		public String getBehavior_score() {
			return behavior_score;
		}


		public void setBehavior_score(String behavior_score) {
			this.behavior_score = behavior_score;
		}


		public String getTotal_score() {
			return total_score;
		}


		public void setTotal_score(String total_score) {
			this.total_score = total_score;
		}


		public String getPromotion_recommendation() {
			return promotion_recommendation;
		}


		public void setPromotion_recommendation(String promotion_recommendation) {
			this.promotion_recommendation = promotion_recommendation;
		}


		public String getSalary_adjustment() {
			return salary_adjustment;
		}


		public void setSalary_adjustment(String salary_adjustment) {
			this.salary_adjustment = salary_adjustment;
		}


		public String getCheck_in_time() {
			return check_in_time;
		}


		public void setCheck_in_time(String check_in_time) {
			this.check_in_time = check_in_time;
		}


		public String getCheck_out_time() {
			return check_out_time;
		}


		public void setCheck_out_time(String check_out_time) {
			this.check_out_time = check_out_time;
		}


		public String getWork_hours() {
			return work_hours;
		}


		public void setWork_hours(String work_hours) {
			this.work_hours = work_hours;
		}


		public String getLate_minutes() {
			return late_minutes;
		}


		public void setLate_minutes(String late_minutes) {
			this.late_minutes = late_minutes;
		}


		public String getOvertime_hours() {
			return overtime_hours;
		}


		public void setOvertime_hours(String overtime_hours) {
			this.overtime_hours = overtime_hours;
		}


		public String getOld_salary() {
			return old_salary;
		}


		public void setOld_salary(String old_salary) {
			this.old_salary = old_salary;
		}


		public String getNew_salary() {
			return new_salary;
		}


		public void setNew_salary(String new_salary) {
			this.new_salary = new_salary;
		}


		public String getOld_position() {
			return old_position;
		}


		public void setOld_position(String old_position) {
			this.old_position = old_position;
		}


		public String getNew_position() {
			return new_position;
		}


		public void setNew_position(String new_position) {
			this.new_position = new_position;
		}


		public String getOld_department() {
			return old_department;
		}


		public void setOld_department(String old_department) {
			this.old_department = old_department;
		}


		public String getNew_department() {
			return new_department;
		}


		public void setNew_department(String new_department) {
			this.new_department = new_department;
		}


		public String getChange_type() {
			return change_type;
		}


		public void setChange_type(String change_type) {
			this.change_type = change_type;
		}


		public String getDiscipline_type() {
			return discipline_type;
		}


		public void setDiscipline_type(String discipline_type) {
			this.discipline_type = discipline_type;
		}


		public String getSeverity_level() {
			return severity_level;
		}


		public void setSeverity_level(String severity_level) {
			this.severity_level = severity_level;
		}


		public String getDiscipline_expire_date() {
			return discipline_expire_date;
		}


		public void setDiscipline_expire_date(String discipline_expire_date) {
			this.discipline_expire_date = discipline_expire_date;
		}


		public String getSubmission_type() {
			return submission_type;
		}


		public void setSubmission_type(String submission_type) {
			this.submission_type = submission_type;
		}


		public String getSubmission_ref() {
			return submission_ref;
		}


		public void setSubmission_ref(String submission_ref) {
			this.submission_ref = submission_ref;
		}


		public String getSubmitted_amountprivate() {
			return submitted_amountprivate;
		}


		public void setSubmitted_amountprivate(String submitted_amountprivate) {
			this.submitted_amountprivate = submitted_amountprivate;
		}


		public int getCut_off_flag() {
			return cut_off_flag;
		}


		public void setCut_off_flag(int cut_off_flag) {
			this.cut_off_flag = cut_off_flag;
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


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
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
