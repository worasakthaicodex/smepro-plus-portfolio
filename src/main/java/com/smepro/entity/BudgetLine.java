package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * BudgetLine — รายการงบประมาณ แยกตาม WBS/Cost Type
 * เช่น Labor, Material, Equipment, External Service
 */
@Entity
@Table(name = "pp_budget_line")
public class BudgetLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** หมวดต้นทุน: LABOR, MATERIAL, EQUIPMENT, SERVICE, OTHER */
    @Column(name = "cost_type", length = 30)
    private String costType;

    /** รายละเอียดรายการ */
    @Column(name = "description", length = 300)
    private String description;

    /** งบประมาณที่ตั้งไว้ */
    @Column(name = "planned_amount", precision = 15, scale = 2)
    private BigDecimal plannedAmount;

    /** ต้นทุนจริง (ACWP) */
    @Column(name = "actual_amount", precision = 15, scale = 2)
    private BigDecimal actualAmount = BigDecimal.ZERO;

    /** Commitment / PO ที่ยังไม่เกิดจริง */
    @Column(name = "commitment_amount", precision = 15, scale = 2)
    private BigDecimal commitmentAmount = BigDecimal.ZERO;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    /** เชื่อมกับ WBS node ที่เกี่ยวข้อง (optional) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wbs_id")
    private WbsElement wbsElement;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCostType() { return costType; }
    public void setCostType(String costType) { this.costType = costType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPlannedAmount() { return plannedAmount; }
    public void setPlannedAmount(BigDecimal plannedAmount) { this.plannedAmount = plannedAmount; }
    public BigDecimal getActualAmount() { return actualAmount; }
    public void setActualAmount(BigDecimal actualAmount) { this.actualAmount = actualAmount; }
    public BigDecimal getCommitmentAmount() { return commitmentAmount; }
    public void setCommitmentAmount(BigDecimal commitmentAmount) { this.commitmentAmount = commitmentAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public WbsElement getWbsElement() { return wbsElement; }
    public void setWbsElement(WbsElement wbsElement) { this.wbsElement = wbsElement; }
}
