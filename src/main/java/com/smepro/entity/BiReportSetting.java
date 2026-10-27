package com.smepro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bi_report_settings")
public class BiReportSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_name", nullable = false)
    private String reportName;

    // 🌟 เพิ่มฟิลด์หมวดหมู่ตรงนี้
    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "report_url", length = 1000)
    private String reportUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_type")
    private String iconType;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getReportUrl() { return reportUrl; }
    public void setReportUrl(String reportUrl) { this.reportUrl = reportUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIconType() { return iconType; }
    public void setIconType(String iconType) { this.iconType = iconType; }
}