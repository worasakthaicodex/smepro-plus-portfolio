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
@Table(name = "shift_record")
public class shift_record  implements Identifiable  {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "employee_code")
	private String employeeCode;
	
	
	@Column(name = "shift_year")
	private int shiftYear;
	
	@Column(name = "shift_month")
	private int shiftMonth;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;

	private String shift_date;
	private String shift_id;
	private String shift_name;
	private String shift_old;
	
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




	public String getEmployeeCode() {
		return employeeCode;
	}




	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}




	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public int getShiftYear() {
		return shiftYear;
	}


	public void setShiftYear(int shiftYear) {
		this.shiftYear = shiftYear;
	}


	public int getShiftMonth() {
		return shiftMonth;
	}


	public void setShiftMonth(int shiftMonth) {
		this.shiftMonth = shiftMonth;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getShift_date() {
		return shift_date;
	}


	public void setShift_date(String shift_date) {
		this.shift_date = shift_date;
	}


	public String getShift_id() {
		return shift_id;
	}


	public void setShift_id(String shift_id) {
		this.shift_id = shift_id;
	}


	public String getShift_name() {
		return shift_name;
	}


	public void setShift_name(String shift_name) {
		this.shift_name = shift_name;
	}


	public String getShift_old() {
		return shift_old;
	}


	public void setShift_old(String shift_old) {
		this.shift_old = shift_old;
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
	
	  
	  
	
}
