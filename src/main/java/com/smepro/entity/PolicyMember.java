package com.smepro.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "policy_member")
public class PolicyMember {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	
    @Column(name = "id_employee")
    private int idEmployee;  // FK column

    
    @Column(name = "employee_name")
    private String employeeName;  
    
    @Column(name = "employee_postion")
    private String employeePostion;  
    
    @Column(name = "employee_department")
    private String employeeDepartment; 
    
    
	@ManyToOne
	@JoinColumn(name = "id_policy")
	private Policy policy;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getIdEmployee() {
		return idEmployee;
	}


	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeePostion() {
		return employeePostion;
	}


	public void setEmployeePostion(String employeePostion) {
		this.employeePostion = employeePostion;
	}


	public String getEmployeeDepartment() {
		return employeeDepartment;
	}


	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}


	public Policy getPolicy() {
		return policy;
	}


	public void setPolicy(Policy policy) {
		this.policy = policy;
	}



	


	
	   
	   
	   
}
