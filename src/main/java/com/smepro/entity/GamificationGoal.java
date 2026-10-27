package com.smepro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamification_goal")
public class GamificationGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName; // เช่น "ทำยอดขายประจำเดือน", "เคลียร์เอกสารไม่ให้ค้าง"
    private String responsible; // พนักงานที่รับผิดชอบ

    // --- การจับคู่ข้อมูล (Mapping) ---
    private int eformId; // ดึงข้อมูลมาจากฟอร์มไหน เช่น "FORM_QT", "FORM_FO"
    private int  processId; // ดึงข้อมูลมาจากฟอร์มไหน เช่น "FORM_QT", "FORM_FO"
    private String targetColumn; // คอลัมน์ที่เก็บค่าที่ต้องการ เช่น "value15" (ยอดเงิน), หรือ "count" (นับจำนวนเอกสาร)
    private String conditionStatus; // สถานะที่ต้องการ เช่น "ปิดการขายได้"
    
    // --- รูปแบบเป้าหมาย (Goal Settings) ---
    private String goalType; // ประเภทเป้าหมาย: COUNT (นับจำนวน), SUM (รวมยอด), EXPIRY (เช็คหมดอายุ)
    private Double targetValue; // เป้าหมายที่ต้องทำให้ถึง เช่น 500000 (ยอดขาย) หรือ 10 (จำนวนครั้ง)
    private String frequency; // EVERY_DAY, EVERY_MONTH
    
    // --- รางวัลและบทลงโทษ (Gamification) ---
    private Integer xpReward; // แต้มที่จะได้ถ้าทำสำเร็จ (เช่น +50)
    private Integer penaltyXp; // แต้มที่จะโดนหักถ้าทำพลาด/หมดอายุ (เช่น -10)
    
    @Column(name = "set_to", length = 20)
    private String setto; // ระบุวันเป้าหมาย เช่น "ศ", "LAST", "12-31"
    
 // --- สถานะการใช้งาน ---
    @Column(name = "status", nullable = false, columnDefinition = "integer default 0")
    private int status = 0; // 0 = ปิดใช้งาน (Inactive), 1 = เปิดใช้งาน (Active)
    
    
 // 0 = นับจากเอกสารหลัก, > 0 = นับจากส่วนขยาย (ระบุ ID ส่วนขยาย)
    @Column(name = "extension_id", columnDefinition = "integer default 0")
    private int extensionId = 0;
    
 // 0 = นับเฉพาะโปรเซสปัจจุบัน (Local), 1 = นับรวมทุกโปรเซสที่มีส่วนขยายนี้ (Global)
    @Column(name = "is_global", columnDefinition = "integer default 0")
    private int isGlobal = 0;
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public int getEformId() {
		return eformId;
	}
	public void setEformId(int eformId) {
		this.eformId = eformId;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTargetColumn() {
		return targetColumn;
	}
	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}
	public String getConditionStatus() {
		return conditionStatus;
	}
	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}
	public String getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}
	public Double getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(Double targetValue) {
		this.targetValue = targetValue;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Integer getXpReward() {
		return xpReward;
	}
	public void setXpReward(Integer xpReward) {
		this.xpReward = xpReward;
	}
	public Integer getPenaltyXp() {
		return penaltyXp;
	}
	public void setPenaltyXp(Integer penaltyXp) {
		this.penaltyXp = penaltyXp;
	}
	public String getSetto() {
		return setto;
	}
	public void setSetto(String setto) {
		this.setto = setto;
	}
	public int getExtensionId() {
		return extensionId;
	}
	public void setExtensionId(int extensionId) {
		this.extensionId = extensionId;
	}
	public int getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(int isGlobal) {
		this.isGlobal = isGlobal;
	}
    
    
    
}