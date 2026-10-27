package com.smepro.models;

public class wip {

	private int id;
	private String pd;
	private String batch;
	private String department;
	private String line;
	private String list;
	private String employee;
	private String machine;
	private String date_wip;
	private Double orders;
	private Double received_today;
	private Double paid_today;	
	private Double received_accumulated;
	private Double pending_received;
	private Double paid_accumulated;
	private Double pending_paid;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getDate_wip() {
		return date_wip;
	}
	public void setDate_wip(String date_wip) {
		this.date_wip = date_wip;
	}
	public Double getOrders() {
		return orders;
	}
	public void setOrders(Double orders) {
		this.orders = orders;
	}
	public Double getReceived_today() {
		return received_today;
	}
	public void setReceived_today(Double received_today) {
		this.received_today = received_today;
	}
	public Double getPaid_today() {
		return paid_today;
	}
	public void setPaid_today(Double paid_today) {
		this.paid_today = paid_today;
	}
	public Double getReceived_accumulated() {
		return received_accumulated;
	}
	public void setReceived_accumulated(Double received_accumulated) {
		this.received_accumulated = received_accumulated;
	}
	public Double getPending_received() {
		return pending_received;
	}
	public void setPending_received(Double pending_received) {
		this.pending_received = pending_received;
	}
	public Double getPaid_accumulated() {
		return paid_accumulated;
	}
	public void setPaid_accumulated(Double paid_accumulated) {
		this.paid_accumulated = paid_accumulated;
	}
	public Double getPending_paid() {
		return pending_paid;
	}
	public void setPending_paid(Double pending_paid) {
		this.pending_paid = pending_paid;
	}
	
	
	
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return String.format(
				"wip [id=%s, pd=%s, batch=%s, department=%s, list=%s, employee=%s, machine=%s, date_wip=%s, orders=%s, received_today=%s, paid_today=%s, received_accumulated=%s, pending_received=%s, paid_accumulated=%s, pending_paid=%s, getId()=%s, getPd()=%s, getBatch()=%s, getDepartment()=%s, getList()=%s, getEmployee()=%s, getMachine()=%s, getDate_wip()=%s, getOrders()=%s, getReceived_today()=%s, getPaid_today()=%s, getReceived_accumulated()=%s, getPending_received()=%s, getPaid_accumulated()=%s, getPending_paid()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				id, pd, batch, department, list, employee, machine, date_wip, orders, received_today, paid_today,
				received_accumulated, pending_received, paid_accumulated, pending_paid, getId(), getPd(), getBatch(),
				getDepartment(), getList(), getEmployee(), getMachine(), getDate_wip(), getOrders(),
				getReceived_today(), getPaid_today(), getReceived_accumulated(), getPending_received(),
				getPaid_accumulated(), getPending_paid(), getClass(), hashCode(), super.toString());
	}
	
	
	
	
	
	
	
}
