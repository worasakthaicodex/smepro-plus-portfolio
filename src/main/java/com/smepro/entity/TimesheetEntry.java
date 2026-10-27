package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * TimesheetEntry — บันทึกชั่วโมงทำงานรายวันต่อ Task
 */
@Entity
@Table(name = "pp_timesheet_entry")
public class TimesheetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ผู้บันทึก (username) */
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    /** วันที่ทำงาน */
    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    /** จำนวนชั่วโมงที่ทำงานวันนั้น */
    @Column(name = "hours_logged", precision = 5, scale = 2)
    private BigDecimal hoursLogged;

    /** หมายเหตุ / สิ่งที่ทำ */
    @Column(name = "work_note", columnDefinition = "TEXT")
    private String workNote;

    /**
     * สถานะ Timesheet:
     * DRAFT, SUBMITTED, APPROVED, REJECTED
     */
    @Column(name = "ts_status", length = 20)
    private String tsStatus = "DRAFT";

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_by", length = 100)
    private String approvedBy;

    
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /* RELATIONSHIPS */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wbs_id", nullable = false)
    private WbsElement wbsElement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public LocalDate getWorkDate() { return workDate; }
    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }
    public BigDecimal getHoursLogged() { return hoursLogged; }
    public void setHoursLogged(BigDecimal hoursLogged) { this.hoursLogged = hoursLogged; }
    public String getWorkNote() { return workNote; }
    public void setWorkNote(String workNote) { this.workNote = workNote; }
    public String getTsStatus() { return tsStatus; }
    public void setTsStatus(String tsStatus) { this.tsStatus = tsStatus; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    public LocalDateTime getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDateTime approvedAt) { this.approvedAt = approvedAt; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public WbsElement getWbsElement() { return wbsElement; }
    public void setWbsElement(WbsElement wbsElement) { this.wbsElement = wbsElement; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
