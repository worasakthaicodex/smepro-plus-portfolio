package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * WbsElement ใช้แทนทั้ง WBS Node (Group/Phase) และ Task (Activity)
 * แยกด้วย field "elementType"
 * รองรับ Tree structure แบบ self-referencing (parent-child)
 */
@Entity
@Table(name = "pp_wbs_element")
public class WbsElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** รหัส WBS/Task เช่น WBS-1.0, TSK-101 */
    @Column(name = "element_code", length = 30)
    private String elementCode;

    /** ชื่อ WBS/Task */
    @Column(name = "element_name", nullable = false, length = 300)
    private String elementName;

    /** ประเภท: WBS (โฟลเดอร์/กลุ่ม), TASK (งานจริง) */
    @Column(name = "element_type", length = 10)
    private String elementType; // WBS | TASK

    /** ระดับใน tree (Level 0 = root, 1, 2, ...) */
    @Column(name = "level_no")
    private Integer levelNo = 0;

    /** ลำดับใน level เดียวกัน */
    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    /** รายละเอียด */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /** ผู้รับผิดชอบ */
    @Column(name = "assignee", length = 100)
    private String assignee;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /** จำนวนชั่วโมงที่วางแผน */
    @Column(name = "planned_hours", precision = 8, scale = 2)
    private BigDecimal plannedHours;

    /** จำนวนชั่วโมงจริง (คำนวณจาก Timesheet) */
    @Column(name = "actual_hours", precision = 8, scale = 2)
    private BigDecimal actualHours = BigDecimal.ZERO;

    /** งบประมาณของ WBS/Task นี้ */
    @Column(name = "budget_amount", precision = 15, scale = 2)
    private BigDecimal budgetAmount;

    /** ต้นทุนจริง (คำนวณจาก Actual) */
    @Column(name = "actual_cost", precision = 15, scale = 2)
    private BigDecimal actualCost = BigDecimal.ZERO;

    /** ความคืบหน้า 0-100 */
    @Column(name = "progress")
    private Integer progress = 0;

    /**
     * สถานะ:
     * NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED
     */
    @Column(name = "status", length = 20)
    private String status = "NOT_STARTED";

    /**
     * ความสำคัญ (สำหรับ Task):
     * LOW, MEDIUM, HIGH, CRITICAL
     */
    @Column(name = "priority", length = 20)
    private String priority;

    /** หมวดหมู่งาน: DEVELOPMENT, DESIGN, BUG, TESTING, MEETING */
    @Column(name = "task_category", length = 30)
    private String taskCategory;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* =========================================
       RELATIONSHIPS
       ========================================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id")
    private ProjectPhase phase;

    /** Parent WBS (null = root) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private WbsElement parent;

    /** Children WBS/Tasks */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<WbsElement> children = new ArrayList<>();

    /** Timesheet entries ที่ log ต่อ Task นี้ */
    @OneToMany(mappedBy = "wbsElement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimesheetEntry> timesheetEntries = new ArrayList<>();

    /** Material / BOM ที่ใช้ใน Task นี้ */
    @OneToMany(mappedBy = "wbsElement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MaterialResource> materialResources = new ArrayList<>();

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

    public String getElementCode() { return elementCode; }
    public void setElementCode(String elementCode) { this.elementCode = elementCode; }

    public String getElementName() { return elementName; }
    public void setElementName(String elementName) { this.elementName = elementName; }

    public String getElementType() { return elementType; }
    public void setElementType(String elementType) { this.elementType = elementType; }

    public Integer getLevelNo() { return levelNo; }
    public void setLevelNo(Integer levelNo) { this.levelNo = levelNo; }

    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAssignee() { return assignee; }
    public void setAssignee(String assignee) { this.assignee = assignee; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public BigDecimal getPlannedHours() { return plannedHours; }
    public void setPlannedHours(BigDecimal plannedHours) { this.plannedHours = plannedHours; }

    public BigDecimal getActualHours() { return actualHours; }
    public void setActualHours(BigDecimal actualHours) { this.actualHours = actualHours; }

    public BigDecimal getBudgetAmount() { return budgetAmount; }
    public void setBudgetAmount(BigDecimal budgetAmount) { this.budgetAmount = budgetAmount; }

    public BigDecimal getActualCost() { return actualCost; }
    public void setActualCost(BigDecimal actualCost) { this.actualCost = actualCost; }

    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this.progress = progress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getTaskCategory() { return taskCategory; }
    public void setTaskCategory(String taskCategory) { this.taskCategory = taskCategory; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public ProjectPhase getPhase() { return phase; }
    public void setPhase(ProjectPhase phase) { this.phase = phase; }

    public WbsElement getParent() { return parent; }
    public void setParent(WbsElement parent) { this.parent = parent; }

    public List<WbsElement> getChildren() { return children; }
    public void setChildren(List<WbsElement> children) { this.children = children; }

    public List<TimesheetEntry> getTimesheetEntries() { return timesheetEntries; }
    public void setTimesheetEntries(List<TimesheetEntry> timesheetEntries) { this.timesheetEntries = timesheetEntries; }

    public List<MaterialResource> getMaterialResources() { return materialResources; }
    public void setMaterialResources(List<MaterialResource> materialResources) { this.materialResources = materialResources; }
}
