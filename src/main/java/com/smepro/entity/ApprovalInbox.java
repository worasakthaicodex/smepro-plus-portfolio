package com.smepro.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ApprovalInbox — กล่อง Inbox สำหรับงานที่รอ Approve
 * ครอบคลุมทั้ง Phase Gate Approval, Timesheet Approval, Project Closure
 */
@Entity
@Table(name = "pp_approval_inbox")
public class ApprovalInbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ประเภทงานที่รออนุมัติ:
     * PHASE_GATE, TIMESHEET, PROJECT_CLOSURE, BUDGET_REALLOC, RESOURCE_REQUEST
     */
    @Column(name = "approval_type", length = 30, nullable = false)
    private String approvalType;

    /** ชื่อเรื่อง */
    @Column(name = "subject", length = 300)
    private String subject;

    /** รายละเอียด */
    @Column(name = "detail", columnDefinition = "TEXT")
    private String detail;

    /** ผู้ขออนุมัติ */
    @Column(name = "requested_by", length = 100)
    private String requestedBy;

    /** ผู้ที่ต้องอนุมัติ */
    @Column(name = "approver", length = 100)
    private String approver;

    /**
     * สถานะ: PENDING, APPROVED, REJECTED, DELEGATED
     */
    @Column(name = "status", length = 20)
    private String status = "PENDING";

    /** หมายเหตุจากผู้อนุมัติ */
    @Column(name = "approver_note", columnDefinition = "TEXT")
    private String approverNote;

    /** Reference ID ของ record ที่รออนุมัติ (เช่น phase_id, timesheet_id) */
    @Column(name = "ref_id")
    private Long refId;

    /** ความเร่งด่วน: LOW, NORMAL, HIGH, URGENT */
    @Column(name = "urgency", length = 20)
    private String urgency = "NORMAL";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    /* RELATIONSHIP */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getApprovalType() { return approvalType; }
    public void setApprovalType(String approvalType) { this.approvalType = approvalType; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getRequestedBy() { return requestedBy; }
    public void setRequestedBy(String requestedBy) { this.requestedBy = requestedBy; }
    public String getApprover() { return approver; }
    public void setApprover(String approver) { this.approver = approver; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getApproverNote() { return approverNote; }
    public void setApproverNote(String approverNote) { this.approverNote = approverNote; }
    public Long getRefId() { return refId; }
    public void setRefId(Long refId) { this.refId = refId; }
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
