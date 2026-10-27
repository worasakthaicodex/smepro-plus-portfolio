package com.smepro.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "report_join_config")
public class ReportJoinConfig {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_id", length = 100)
    private String reportId;

    @Column(name = "join_order")
    private Integer joinOrder;

    @Column(name = "table1_name",  length = 100)
    private String table1Name;

    @Column(name = "table2_name",  length = 100)
    private String table2Name;

    @Column(name = "key1_table1",  length = 100)
    private String key1Table1;

    @Column(name = "key1_table2",length = 100)
    private String key1Table2;

    @Column(name = "key2_table1", length = 100)
    private String key2Table1;

    @Column(name = "key2_table2", length = 100)
    private String key2Table2;

    @Column(name = "key3_table1", length = 100)
    private String key3Table1;

    @Column(name = "key3_table2", length = 100)
    private String key3Table2;

    @Column(name = "join_type", length = 10)
    private String joinType = "LEFT"; // ค่า default

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "description", length = 255)
    private String description;
    
    
    // เชื่อมกับ ReportSetting (Foreign Key)
    @ManyToOne
    @JoinColumn(name = "report_setting_id")
    private CustomReports customReports;
    
    
    @Column(name = "report_setting_id", insertable = false, updatable = false)
    private Long reportSettingId;
    
    @Column(name = "edata_id")
	private int edataId;
	private String tempId;
	
    @Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Integer getJoinOrder() {
		return joinOrder;
	}

	public void setJoinOrder(Integer joinOrder) {
		this.joinOrder = joinOrder;
	}

	public String getTable1Name() {
		return table1Name;
	}

	public void setTable1Name(String table1Name) {
		this.table1Name = table1Name;
	}

	public String getTable2Name() {
		return table2Name;
	}

	public void setTable2Name(String table2Name) {
		this.table2Name = table2Name;
	}

	public String getKey1Table1() {
		return key1Table1;
	}

	public void setKey1Table1(String key1Table1) {
		this.key1Table1 = key1Table1;
	}

	public String getKey1Table2() {
		return key1Table2;
	}

	public void setKey1Table2(String key1Table2) {
		this.key1Table2 = key1Table2;
	}

	public String getKey2Table1() {
		return key2Table1;
	}

	public void setKey2Table1(String key2Table1) {
		this.key2Table1 = key2Table1;
	}

	public String getKey2Table2() {
		return key2Table2;
	}

	public void setKey2Table2(String key2Table2) {
		this.key2Table2 = key2Table2;
	}

	public String getKey3Table1() {
		return key3Table1;
	}

	public void setKey3Table1(String key3Table1) {
		this.key3Table1 = key3Table1;
	}

	public String getKey3Table2() {
		return key3Table2;
	}

	public void setKey3Table2(String key3Table2) {
		this.key3Table2 = key3Table2;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CustomReports getCustomReports() {
		return customReports;
	}

	public void setCustomReports(CustomReports customReports) {
		this.customReports = customReports;
	}

	public Long getReportSettingId() {
		return reportSettingId;
	}

	public void setReportSettingId(Long reportSettingId) {
		this.reportSettingId = reportSettingId;
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
