package com.smepro.dto;

import java.math.BigDecimal;

public class BalanceSheetDTO {
	
    private String category;     // หมวดหมู่ (Assets, Liabilities, Equity)
    private String accountCode;  // รหัสบัญชี
    private String accountName;  // ชื่อบัญชี
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;  // จะคำนวณ = debit - credit หรือแล้วแต่สูตร
    private boolean isSummary;   // แท็กว่าเป็นบรรทัดรวม
   
    
    
    // getters setters
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public boolean isSummary() {
		return isSummary;
	}
	public void setSummary(boolean isSummary) {
		this.isSummary = isSummary;
	}
    
    
    
    
}
