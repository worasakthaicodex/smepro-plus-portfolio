package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ProjectClosure — บันทึกการปิดโครงการ
 */
@Entity
@Table(name = "pp_project_closure")
public class ProjectClosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** สรุปผลโครงการ */
    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    /** Lessons Learned */
    @Column(name = "lessons_learned", columnDefinition = "TEXT")
    private String lessonsLearned;

    /** งบประมาณที่ใช้จริงรวม */
    @Column(name = "final_cost", precision = 15, scale = 2)
    private BigDecimal finalCost;

    /** วันที่ปิดโครงการจริง */
    @Column(name = "actual_close_date")
    private LocalDate actualCloseDate;

    /**
     * ผลลัพธ์: SUCCESS, PARTIAL_SUCCESS, FAILED, CANCELLED
     */
    @Column(name = "outcome", length = 20)
    private String outcome;

    /** ผู้อนุมัติปิดโครงการ */
    @Column(name = "closed_by", length = 100)
    private String closedBy;

    /**
     * สถานะ Closure:
     * DRAFT, SUBMITTED, APPROVED
     */
    @Column(name = "closure_status", length = 20)
    private String closureStatus = "DRAFT";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /* RELATIONSHIP */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", unique = true)
    private Project project;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getLessonsLearned() { return lessonsLearned; }
    public void setLessonsLearned(String lessonsLearned) { this.lessonsLearned = lessonsLearned; }
    public BigDecimal getFinalCost() { return finalCost; }
    public void setFinalCost(BigDecimal finalCost) { this.finalCost = finalCost; }
    public LocalDate getActualCloseDate() { return actualCloseDate; }
    public void setActualCloseDate(LocalDate actualCloseDate) { this.actualCloseDate = actualCloseDate; }
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public String getClosedBy() { return closedBy; }
    public void setClosedBy(String closedBy) { this.closedBy = closedBy; }
    public String getClosureStatus() { return closureStatus; }
    public void setClosureStatus(String closureStatus) { this.closureStatus = closureStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
