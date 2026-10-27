package com.smepro.entity;

import javax.persistence.*;

@Entity
@Table(name = "spikedog_actionplan")
public class SpikeDogActionplan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idreport")
    private Integer idreport; // เชื่อมกับ idreport จาก URL

    @Column(name = "issue_topic", length = 500)
    private String issueTopic; // ปัญหา / หัวข้อ

    @Column(name = "impact")
    private Double impact; // Impact (ความถี่ / ผลกระทบ)

    @Column(name = "cumulative_percent")
    private Double cumulativePercent; // % สะสม

    @Column(name = "action_plan_details", columnDefinition = "TEXT")
    private String actionPlanDetails; // แผนดำเนินการ (Action Plan)

    @Column(name = "owner", length = 255)
    private String owner; // ผู้รับผิดชอบ

 // ==========================================
    // ส่วนที่เพิ่มใหม่: ผลการดำเนินงาน
    // ==========================================

    @Column(name = "status", length = 50)
    private String status; // สถานะ (Not Started, In Progress, Completed, etc.)

    @Column(name = "progress")
    private Integer progress; // ความคืบหน้า 0-100 %

    @Column(name = "target_date", length = 20)
    private String targetDate; // กำหนดเสร็จ (เก็บเป็น String yyyy-MM-dd ให้รับค่าง่ายๆ)

    @Column(name = "actual_date", length = 20)
    private String actualDate; // วันที่เสร็จจริง

    @Column(name = "result_remarks", columnDefinition = "TEXT")
    private String resultRemarks; // สรุปผลลัพธ์ / หมายเหตุ

    // ==========================================
    // อย่าลืม Generate Getters and Setters สำหรับฟิลด์ใหม่ด้วยนะครับ!
    // ==========================================
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this. progress = progress; }

    public String getTargetDate() { return targetDate; }
    public void setTargetDate(String targetDate) { this.targetDate = targetDate; }

    public String getActualDate() { return actualDate; }
    public void setActualDate(String actualDate) { this.actualDate = actualDate; }

    public String getResultRemarks() { return resultRemarks; }
    public void setResultRemarks(String resultRemarks) { this.resultRemarks = resultRemarks; }
    
    
    // ==========================================
    // Getters and Setters
    // ==========================================
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdreport() { return idreport; }
    public void setIdreport(Integer idreport) { this.idreport = idreport; }

    public String getIssueTopic() { return issueTopic; }
    public void setIssueTopic(String issueTopic) { this.issueTopic = issueTopic; }

    public Double getImpact() { return impact; }
    public void setImpact(Double impact) { this.impact = impact; }

    public Double getCumulativePercent() { return cumulativePercent; }
    public void setCumulativePercent(Double cumulativePercent) { this.cumulativePercent = cumulativePercent; }

    public String getActionPlanDetails() { return actionPlanDetails; }
    public void setActionPlanDetails(String actionPlanDetails) { this.actionPlanDetails = actionPlanDetails; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
}
