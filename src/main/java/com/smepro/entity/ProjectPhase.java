package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pp_project_phase")
public class ProjectPhase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ชื่อ Phase เช่น Phase 1: Infrastructure */
    @Column(name = "phase_name", nullable = false, length = 200)
    private String phaseName;

    /** รหัส Phase เช่น PH-01 */
    @Column(name = "phase_code", length = 30)
    private String phaseCode;

    /** ลำดับ Phase */
    @Column(name = "sequence_no")
    private Integer sequenceNo;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /** งบประมาณ Phase */
    @Column(name = "budget", precision = 15, scale = 2)
    private BigDecimal budget;

    /** ความคืบหน้า (%) */
    @Column(name = "progress")
    private Integer progress = 0;

    /**
     * สถานะ Gate Approval:
     * PENDING, APPROVED, REJECTED, IN_REVIEW
     */
    @Column(name = "gate_status", length = 20)
    private String gateStatus = "PENDING";

    /** ผู้อนุมัติ Phase Gate */
    @Column(name = "approver", length = 100)
    private String approver;

    /** วันที่อนุมัติ */
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    /** หมายเหตุ Approval */
    @Column(name = "approval_note", columnDefinition = "TEXT")
    private String approvalNote;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /* =========================================
       RELATIONSHIPS
       ========================================= */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WbsElement> wbsElements = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ===== Getters & Setters =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPhaseName() { return phaseName; }
    public void setPhaseName(String phaseName) { this.phaseName = phaseName; }

    public String getPhaseCode() { return phaseCode; }
    public void setPhaseCode(String phaseCode) { this.phaseCode = phaseCode; }

    public Integer getSequenceNo() { return sequenceNo; }
    public void setSequenceNo(Integer sequenceNo) { this.sequenceNo = sequenceNo; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }

    public Integer getProgress() { return progress; }
    public void setProgress(Integer progress) { this.progress = progress; }

    public String getGateStatus() { return gateStatus; }
    public void setGateStatus(String gateStatus) { this.gateStatus = gateStatus; }

    public String getApprover() { return approver; }
    public void setApprover(String approver) { this.approver = approver; }

    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }

    public String getApprovalNote() { return approvalNote; }
    public void setApprovalNote(String approvalNote) { this.approvalNote = approvalNote; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public List<WbsElement> getWbsElements() { return wbsElements; }
    public void setWbsElements(List<WbsElement> wbsElements) { this.wbsElements = wbsElements; }
}
