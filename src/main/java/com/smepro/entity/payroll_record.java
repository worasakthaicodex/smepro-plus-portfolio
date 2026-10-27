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
@Table(name = "payroll_record")
public class payroll_record implements Identifiable  {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@Column(name = "payroll_year")
	private int payrollYear;
	
	@Column(name = "payroll_month")
	private int payrollMonth;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	
	
	private String payment;
	private String payroll_status;  
	private String employee_name;
	private String base_salary;
	private String overtime_amount;
	private String shift_bonus;
	private String other_allowance;
	private String total_earning;
	private String total_deduction;
	private String deduct_late;
	private String deduct_absent;
	private String deduct_loan;
	private String deduct_social_security;
	private String deduct_other;
	private String net_pay;
	private String note;
	
	
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


	public int getPayrollYear() {
		return payrollYear;
	}


	public void setPayrollYear(int payrollYear) {
		this.payrollYear = payrollYear;
	}


	public int getPayrollMonth() {
		return payrollMonth;
	}


	public void setPayrollMonth(int payrollMonth) {
		this.payrollMonth = payrollMonth;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getPayroll_status() {
		return payroll_status;
	}


	public void setPayroll_status(String payroll_status) {
		this.payroll_status = payroll_status;
	}


	public String getEmployee_name() {
		return employee_name;
	}


	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}


	public String getBase_salary() {
		return base_salary;
	}


	public void setBase_salary(String base_salary) {
		this.base_salary = base_salary;
	}


	public String getOvertime_amount() {
		return overtime_amount;
	}


	public void setOvertime_amount(String overtime_amount) {
		this.overtime_amount = overtime_amount;
	}


	public String getShift_bonus() {
		return shift_bonus;
	}


	public void setShift_bonus(String shift_bonus) {
		this.shift_bonus = shift_bonus;
	}


	public String getOther_allowance() {
		return other_allowance;
	}


	public void setOther_allowance(String other_allowance) {
		this.other_allowance = other_allowance;
	}


	public String getTotal_earning() {
		return total_earning;
	}


	public void setTotal_earning(String total_earning) {
		this.total_earning = total_earning;
	}


	public String getTotal_deduction() {
		return total_deduction;
	}


	public void setTotal_deduction(String total_deduction) {
		this.total_deduction = total_deduction;
	}


	public String getDeduct_late() {
		return deduct_late;
	}


	public void setDeduct_late(String deduct_late) {
		this.deduct_late = deduct_late;
	}


	public String getDeduct_absent() {
		return deduct_absent;
	}


	public void setDeduct_absent(String deduct_absent) {
		this.deduct_absent = deduct_absent;
	}


	public String getDeduct_loan() {
		return deduct_loan;
	}


	public void setDeduct_loan(String deduct_loan) {
		this.deduct_loan = deduct_loan;
	}


	public String getDeduct_social_security() {
		return deduct_social_security;
	}


	public void setDeduct_social_security(String deduct_social_security) {
		this.deduct_social_security = deduct_social_security;
	}


	public String getDeduct_other() {
		return deduct_other;
	}


	public void setDeduct_other(String deduct_other) {
		this.deduct_other = deduct_other;
	}


	public String getNet_pay() {
		return net_pay;
	}


	public void setNet_pay(String net_pay) {
		this.net_pay = net_pay;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
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
