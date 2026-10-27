package com.smepro.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "quest_log")
public class QuestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "goal_id", nullable = false)
    private Long goalId; // รหัสภารกิจที่ไปผูกกับ GamificationGoal

    @Column(name = "user_name", nullable = false)
    private String userName; // รหัสพนักงาน หรือ Username ที่ทำภารกิจนี้

    @Column(name = "action_date", nullable = false)
    private String actionDate; // วันที่เป้าหมายกำหนด (date_due) เก็บเป็น String ตามโครงสร้างเดิมของคุณ

    @Column(name = "xp_earned", nullable = false)
    private Integer xpEarned; // แต้มที่ได้รับ (ถ้าล้มเหลว/หมดอายุ ให้ส่งค่าติดลบ เช่น -10)

    @Column(name = "status", length = 50)
    private String status; // สถานะการทำภารกิจ เช่น SUCCESS, PARTIAL, FAILED, PENALTY (โดนหักแต้ม)

    @Column(name = "completion_percentage")
    private Integer completionPercentage; // ทำสำเร็จกี่เปอร์เซ็นต์ (เช่น 100, 50, 0)

    @Column(name = "remark", columnDefinition = "TEXT")
    private String remark; // คำอธิบายเพื่อโชว์ในหน้าจอผู้ใช้ เช่น "ได้รับแต้มจากใบเสนอราคา QT-2603-001" หรือ "ถูกหักแต้มเนื่องจาก FO หมดอายุ"

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // เวลาที่ระบบประมวลผลให้แต้มจริงๆ
    
    
    private Double actualResult; // เวลาที่ระบบประมวลผลให้แต้มจริงๆ
    private Double targetValue; // เวลาที่ระบบประมวลผลให้แต้มจริงๆ

    // --- สร้าง Timestamp อัตโนมัติก่อน Save ลง Database ---
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

  
    // ==========================================
    // Getters and Setters 
    // (หากใช้ @Data ของ Lombok ก็ลบส่วนนี้ทิ้งได้เลยครับ)
    // ==========================================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGoalId() { return goalId; }
    public void setGoalId(Long goalId) { this.goalId = goalId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getActionDate() { return actionDate; }
    public void setActionDate(String actionDate) { this.actionDate = actionDate; }

    public Integer getXpEarned() { return xpEarned; }
    public void setXpEarned(Integer xpEarned) { this.xpEarned = xpEarned; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(Integer completionPercentage) { this.completionPercentage = completionPercentage; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }


	public Double getActualResult() {
		return actualResult;
	}


	public void setActualResult(Double actualResult) {
		this.actualResult = actualResult;
	}


	public Double getTargetValue() {
		return targetValue;
	}


	public void setTargetValue(Double targetValue) {
		this.targetValue = targetValue;
	}


}