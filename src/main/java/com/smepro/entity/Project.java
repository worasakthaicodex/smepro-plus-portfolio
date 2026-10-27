package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pp_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** รหัสโครงการ เช่น PRJ-26-001 (Auto-generate หรือกำหนดเอง) */
    @Column(name = "project_code", unique = true, length = 30)
    private String projectCode;

    /** ชื่อโครงการ */
    @Column(name = "project_name", nullable = false, length = 200)
    private String projectName;

    /** ประเภทโครงการ: CAPEX, BILLABLE, IT_RD, MAINTENANCE */
    @Column(name = "project_type", length = 30)
    private String projectType;

    /** วัตถุประสงค์ / ขอบเขต */
    @Column(name = "objective", columnDefinition = "TEXT")
    private String objective;

    /** Priority: LOW, MEDIUM, HIGH, CRITICAL */
    @Column(name = "priority", length = 20)
    private String priority;

    /** Cost Center สำหรับ Settlement */
    @Column(name = "cost_center", length = 30)
    private String costCenter;

    /** Project Manager (ชื่อ user หรือ reference ไปยัง user table) */
    @Column(name = "project_manager", length = 100)
    private String projectManager;

    /** Sponsor / Client */
    @Column(name = "sponsor", length = 100)
    private String sponsor;

    /** วันเริ่มต้นโครงการ */
    @Column(name = "start_date")
    private LocalDate startDate;

    /** วันสิ้นสุดโครงการ */
    @Column(name = "end_date")
    private LocalDate endDate;

    /** สกุลเงิน: THB, USD, EUR */
    @Column(name = "currency", length = 10)
    private String currency;

    /** งบประมาณรวม */
    @Column(name = "budget_limit", precision = 15, scale = 2)
    private BigDecimal budgetLimit;

    /** ความคืบหน้ารวม (%) คำนวณจาก Task */
    @Column(name = "overall_progress")
    private Integer overallProgress = 0;

    /**
     * สถานะโครงการ:
     * PLANNING, ON_TRACK, AT_RISK, DELAYED, COMPLETED, ARCHIVED, PIPELINE
     */
    @Column(name = "status", length = 20)
    private String status = "PLANNING";

    /** appid ของระบบ (เชื่อมกับ application context) */
    @Column(name = "app_id")
    private Integer appId;

    /** วันที่สร้าง */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /** วันที่แก้ไขล่าสุด */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* =========================================
       RELATIONSHIPS
       ========================================= */

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectPhase> phases = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WbsElement> wbsElements = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectResource> resources = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BudgetLine> budgetLines = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // ===== Getters & Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProjectCode() { return projectCode; }
    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getProjectType() { return projectType; }
    public void setProjectType(String projectType) { this.projectType = projectType; }

    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getCostCenter() { return costCenter; }
    public void setCostCenter(String costCenter) { this.costCenter = costCenter; }

    public String getProjectManager() { return projectManager; }
    public void setProjectManager(String projectManager) { this.projectManager = projectManager; }

    public String getSponsor() { return sponsor; }
    public void setSponsor(String sponsor) { this.sponsor = sponsor; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public BigDecimal getBudgetLimit() { return budgetLimit; }
    public void setBudgetLimit(BigDecimal budgetLimit) { this.budgetLimit = budgetLimit; }

    public Integer getOverallProgress() { return overallProgress; }
    public void setOverallProgress(Integer overallProgress) { this.overallProgress = overallProgress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getAppId() { return appId; }
    public void setAppId(Integer appId) { this.appId = appId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public List<ProjectPhase> getPhases() { return phases; }
    public void setPhases(List<ProjectPhase> phases) { this.phases = phases; }

    public List<WbsElement> getWbsElements() { return wbsElements; }
    public void setWbsElements(List<WbsElement> wbsElements) { this.wbsElements = wbsElements; }

    public List<ProjectResource> getResources() { return resources; }
    public void setResources(List<ProjectResource> resources) { this.resources = resources; }

    public List<BudgetLine> getBudgetLines() { return budgetLines; }
    public void setBudgetLines(List<BudgetLine> budgetLines) { this.budgetLines = budgetLines; }
}
