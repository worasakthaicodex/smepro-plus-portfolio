package com.smepro.models;

public class oee {

	private int id;
	private String pd;
	private String batch;
	private String line;
	private String machine;
	private String date_oee;
	private String month_oee;
	
	
	private int  downtime;
	private Double  planned_time;
	private int availability;
	
	private int  speed;
	private int  total_count; // รวมทั้งดีและเสีย
	private Double  operating_time; //(Planned Production Time  - Downtime)
	private Double  performance; //(Planned Production Time  - Downtime)
	
	 
	private int  good_count; // จำนวนชิ้นงานที่ใช้ได้จริง (ไม่เสีย)
	private int quality;
	
	
	private int oee; // จำนวนชิ้นงานที่ใช้ได้จริง (ไม่เสีย)


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


	public String getLine() {
		return line;
	}


	public void setLine(String line) {
		this.line = line;
	}


	public String getMachine() {
		return machine;
	}


	public void setMachine(String machine) {
		this.machine = machine;
	}


	public String getDate_oee() {
		return date_oee;
	}


	public void setDate_oee(String date_oee) {
		this.date_oee = date_oee;
	}


	public String getMonth_oee() {
		return month_oee;
	}


	public void setMonth_oee(String month_oee) {
		this.month_oee = month_oee;
	}


	public int getDowntime() {
		return downtime;
	}


	public void setDowntime(int downtime) {
		this.downtime = downtime;
	}


	public Double getPlanned_time() {
		return planned_time;
	}


	public void setPlanned_time(Double planned_time) {
		this.planned_time = planned_time;
	}


	public int getAvailability() {
		return availability;
	}


	public void setAvailability(int availability) {
		this.availability = availability;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getTotal_count() {
		return total_count;
	}


	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}


	public Double getOperating_time() {
		return operating_time;
	}


	public void setOperating_time(Double operating_time) {
		this.operating_time = operating_time;
	}


	public Double getPerformance() {
		return performance;
	}


	public void setPerformance(Double performance) {
		this.performance = performance;
	}


	public int getGood_count() {
		return good_count;
	}


	public void setGood_count(int good_count) {
		this.good_count = good_count;
	}


	public int getQuality() {
		return quality;
	}


	public void setQuality(int quality) {
		this.quality = quality;
	}


	public int getOee() {
		return oee;
	}


	public void setOee(int oee) {
		this.oee = oee;
	}



	
	
	
	
}
