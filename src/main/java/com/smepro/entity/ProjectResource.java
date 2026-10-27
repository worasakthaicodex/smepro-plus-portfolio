package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/* =========================================
   ProjectResource — สมาชิก / Resource ในโครงการ
   ========================================= */
@Entity
@Table(name = "pp_project_resource")
public class ProjectResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ชื่อ Resource */
    @Column(name = "resource_name", nullable = false, length = 100)
    private String resourceName;

    /** ตัวย่อ Avatar เช่น SA, PM */
    @Column(name = "avatar_initials", length = 5)
    private String avatarInitials;

    /** สี Avatar (hex) */
    @Column(name = "avatar_color", length = 10)
    private String avatarColor;

    /** บทบาท เช่น Project Manager, Solutions Architect */
    @Column(name = "role", length = 100)
    private String role;

    /** ประเภท: INTERNAL, EXTERNAL, CONTRACTOR */
    @Column(name = "resource_type", length = 20)
    private String resourceType;

    /** อัตราต่อชั่วโมง (สำหรับคำนวณ Cost) */
    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    /** % capacity ที่ถูก assign ให้โครงการนี้ (0-100+) */
    @Column(name = "capacity_pct")
    private Integer capacityPct;

    /** วันที่เริ่มต้น assign */
    @Column(name = "assign_start")
    private LocalDate assignStart;

    /** วันที่สิ้นสุด assign */
    @Column(name = "assign_end")
    private LocalDate assignEnd;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }
    public String getAvatarInitials() { return avatarInitials; }
    public void setAvatarInitials(String avatarInitials) { this.avatarInitials = avatarInitials; }
    public String getAvatarColor() { return avatarColor; }
    public void setAvatarColor(String avatarColor) { this.avatarColor = avatarColor; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public BigDecimal getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(BigDecimal hourlyRate) { this.hourlyRate = hourlyRate; }
    public Integer getCapacityPct() { return capacityPct; }
    public void setCapacityPct(Integer capacityPct) { this.capacityPct = capacityPct; }
    public LocalDate getAssignStart() { return assignStart; }
    public void setAssignStart(LocalDate assignStart) { this.assignStart = assignStart; }
    public LocalDate getAssignEnd() { return assignEnd; }
    public void setAssignEnd(LocalDate assignEnd) { this.assignEnd = assignEnd; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
