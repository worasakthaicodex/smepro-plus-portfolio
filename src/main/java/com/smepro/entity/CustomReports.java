package com.smepro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "customreport")
public class CustomReports {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Transient
    public int planId;
	@Transient
    public String department;
	@Transient
    public String machineryId;
	
	
    // ความสัมพันธ์กับ Join Config
    @OneToMany(mappedBy = "customReports", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportJoinConfig> joinConfigs;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public int getPlanId() {
		return planId;
	}


	public void setPlanId(int planId) {
		this.planId = planId;
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


	public List<ReportJoinConfig> getJoinConfigs() {
		return joinConfigs;
	}


	public void setJoinConfigs(List<ReportJoinConfig> joinConfigs) {
		this.joinConfigs = joinConfigs;
	}
    
    
    
}
