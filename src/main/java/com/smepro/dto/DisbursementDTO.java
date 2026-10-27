package com.smepro.dto;

public class DisbursementDTO {

	
	private String list;
	private String dates;
	private Double received;
	private Double paid;
	private Double remain;
	private String drawer;
	private String payer;
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public Double getReceived() {
		return received;
	}
	public void setReceived(Double received) {
		this.received = received;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	public String getDrawer() {
		return drawer;
	}
	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public Double getRemain() {
		return remain;
	}
	public void setRemain(Double remain) {
		this.remain = remain;
	}
	
	
}
