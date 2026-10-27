package com.smepro.entity;

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
@Table(name = "attendance_record")
public class attendance_record implements Identifiable  {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "employee_code")
	private String employeeCode;
	
	
	@Column(name = "attendance_year")
	private int attendanceYear;
	
	@Column(name = "attendance_month")
	private int attendanceMonth;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	
	
	
	
	
	@Column(name = "attendance_date")
	private String attendance_date;
	
	private String  check_in_time;
	private String  check_out_time;
	private String work_hours;      
	private String late_minutes;
	private String  overtime_hours;
	private int  cut_off_flag;
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
	
}
