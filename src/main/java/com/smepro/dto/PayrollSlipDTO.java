package com.smepro.dto;

import java.math.BigDecimal;

import com.smepro.entity.payroll_record;

public class PayrollSlipDTO {
    private String employeeName;
    private BigDecimal baseSalary;
    private BigDecimal overtime;
    private BigDecimal shiftBonus;
    private BigDecimal otherAllowance;
    private BigDecimal totalEarning;
    private BigDecimal totalDeduction;
    private BigDecimal netPay;
    
    
    
  
    
    
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
		
		
	}
	public BigDecimal getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}
	public BigDecimal getOvertime() {
		return overtime;
	}
	public void setOvertime(BigDecimal overtime) {
		this.overtime = overtime;
	}
	public BigDecimal getShiftBonus() {
		return shiftBonus;
	}
	public void setShiftBonus(BigDecimal shiftBonus) {
		this.shiftBonus = shiftBonus;
	}
	public BigDecimal getOtherAllowance() {
		return otherAllowance;
	}
	public void setOtherAllowance(BigDecimal otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	public BigDecimal getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(BigDecimal totalEarning) {
		this.totalEarning = totalEarning;
	}
	public BigDecimal getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(BigDecimal totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public BigDecimal getNetPay() {
		return netPay;
	}
	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}
    

    
}
