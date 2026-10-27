package com.smepro.models;

import java.util.List;

public class FullResult {
	
	
	private int totalCount;
    private List<registers> registers;
    private List<product_master> product_master;
    
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<registers> getRegisters() {
		return registers;
	}
	public void setRegisters(List<registers> registers) {
		this.registers = registers;
	}
	public List<product_master> getProduct_master() {
		return product_master;
	}
	public void setProduct_master(List<product_master> product_master) {
		this.product_master = product_master;
	}


}
