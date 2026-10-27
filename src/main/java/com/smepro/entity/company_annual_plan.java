package com.smepro.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smepro.models.department;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "company_annual_plan")
public class company_annual_plan {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "annualPlanId", unique = true)
	private int annualPlanId;
	
	
	private String title;
	
	private String annualType;
	
	private String annual_plan_code;
	private String annual_dateadd;
	private int annual_year;
	private String department;  

	@Column(name = "machinery_id")
	private String machineryId;
	private String  max_downtime_minutes;
	private String min_machine_speed;
	
	@Column(name = "production_target_units")
	private String productionTargetUnits;
	private String production_target_revenue;
	
	
	@Column(name = "general_target")
	private String generalTarget;  
	
	
	@Column(name = "annual_budget")
	private String annualBudget;  
	
	@Column(name = "total_jobs")
	private int totalJobs;
	
	@Column(name = "max_personnel")
	private int maxPersonnel;
	

	
	

	private String notes;
	private String status;
	
	
	@Column(name = "edata_id")
	private int edataId;
	private String tempId;
	private String  doc;
	
	
	@Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnnualPlanId() {
		return annualPlanId;
	}

	public void setAnnualPlanId(int annualPlanId) {
		this.annualPlanId = annualPlanId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnnualType() {
		return annualType;
	}

	public void setAnnualType(String annualType) {
		this.annualType = annualType;
	}

	public String getAnnual_plan_code() {
		return annual_plan_code;
	}

	public void setAnnual_plan_code(String annual_plan_code) {
		this.annual_plan_code = annual_plan_code;
	}

	public String getAnnual_dateadd() {
		return annual_dateadd;
	}

	public void setAnnual_dateadd(String annual_dateadd) {
		this.annual_dateadd = annual_dateadd;
	}

	public int getAnnual_year() {
		return annual_year;
	}

	public void setAnnual_year(int annual_year) {
		this.annual_year = annual_year;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMachineryId() {
		return machineryId;
	}

	public void setMachineryId(String machineryId) {
		this.machineryId = machineryId;
	}

	public String getMax_downtime_minutes() {
		return max_downtime_minutes;
	}

	public void setMax_downtime_minutes(String max_downtime_minutes) {
		this.max_downtime_minutes = max_downtime_minutes;
	}

	public String getMin_machine_speed() {
		return min_machine_speed;
	}

	public void setMin_machine_speed(String min_machine_speed) {
		this.min_machine_speed = min_machine_speed;
	}

	public String getProductionTargetUnits() {
		return productionTargetUnits;
	}

	public void setProductionTargetUnits(String productionTargetUnits) {
		this.productionTargetUnits = productionTargetUnits;
	}

	public String getProduction_target_revenue() {
		return production_target_revenue;
	}

	public void setProduction_target_revenue(String production_target_revenue) {
		this.production_target_revenue = production_target_revenue;
	}

	public String getGeneralTarget() {
		return generalTarget;
	}

	public void setGeneralTarget(String generalTarget) {
		this.generalTarget = generalTarget;
	}

	public String getAnnualBudget() {
		return annualBudget;
	}

	public void setAnnualBudget(String annualBudget) {
		this.annualBudget = annualBudget;
	}

	public int getTotalJobs() {
		return totalJobs;
	}

	public void setTotalJobs(int totalJobs) {
		this.totalJobs = totalJobs;
	}

	public int getMaxPersonnel() {
		return maxPersonnel;
	}

	public void setMaxPersonnel(int maxPersonnel) {
		this.maxPersonnel = maxPersonnel;
	}


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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


	  
}
