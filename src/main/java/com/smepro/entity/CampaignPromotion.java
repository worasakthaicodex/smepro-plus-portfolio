package com.smepro.entity;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "erp_campaign_promotion")
public class CampaignPromotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campaign_name", nullable = false)
    private String campaignName;

    @Column(name = "offer_type", nullable = false)
    private String offerType;

    @Column(name = "discount_value")
    private BigDecimal discountValue;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "min_spend")
    private BigDecimal minSpend;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @Column(name = "target_redemptions")
    private Integer targetRedemptions;

    @Column(name = "est_revenue_per_redemption")
    private BigDecimal estRevenuePerRedemption;
    
    
 // เพิ่มฟิลด์เก็บสถานะ (เช่น "ACTIVE", "DRAFT", "ENDED")
    @Column(name = "status")
    private String status = "DRAFT"; 

    // เพิ่มฟิลด์เก็บจำนวนสิทธิ์ที่ถูกใช้ไปแล้ว
    @Column(name = "actual_redemptions")
    private Integer actualRedemptions = 0;

 @Column(name = "measurement_process_id")
 private Integer measurementProcessId;

    // เก็บผลการสมัครที่ถือว่าปิดการขายได้ (เลือกได้หลายอัน จะถูกเก็บเป็น String คั่นด้วยจุลภาค เช่น "1,2")
    @Column(name = "success_workflow_status_ids")
    private String successWorkflowStatusIds;
    
 // เพิ่มช่องเก็บรายได้จริง
    @Column(name = "actual_revenue")
    private BigDecimal actualRevenue = BigDecimal.ZERO;

    // เพิ่มช่องเก็บจำนวนผู้สนใจ (กลุ่มคนที่เข้าข่ายแต่ยังไม่ปิดการขาย)
    @Column(name = "interested_count")
    private Integer interestedCount = 0;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTargetAudience() {
		return targetAudience;
	}

	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	public BigDecimal getMinSpend() {
		return minSpend;
	}

	public void setMinSpend(BigDecimal minSpend) {
		this.minSpend = minSpend;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getTargetRedemptions() {
		return targetRedemptions;
	}

	public void setTargetRedemptions(Integer targetRedemptions) {
		this.targetRedemptions = targetRedemptions;
	}

	public BigDecimal getEstRevenuePerRedemption() {
		return estRevenuePerRedemption;
	}

	public void setEstRevenuePerRedemption(BigDecimal estRevenuePerRedemption) {
		this.estRevenuePerRedemption = estRevenuePerRedemption;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getActualRedemptions() {
		return actualRedemptions;
	}

	public void setActualRedemptions(Integer actualRedemptions) {
		this.actualRedemptions = actualRedemptions;
	}
	
	
	


	public Integer getMeasurementProcessId() {
		return measurementProcessId;
	}

	public void setMeasurementProcessId(Integer measurementProcessId) {
		this.measurementProcessId = measurementProcessId;
	}

	public String getSuccessWorkflowStatusIds() {
		return successWorkflowStatusIds;
	}

	public void setSuccessWorkflowStatusIds(String successWorkflowStatusIds) {
		this.successWorkflowStatusIds = successWorkflowStatusIds;
	}
	
	

	public BigDecimal getActualRevenue() {
		return actualRevenue;
	}

	public void setActualRevenue(BigDecimal actualRevenue) {
		this.actualRevenue = actualRevenue;
	}

	public Integer getInterestedCount() {
		return interestedCount;
	}

	public void setInterestedCount(Integer interestedCount) {
		this.interestedCount = interestedCount;
	}

	// หน้าจอ JSP จะแอบมาเรียกใช้ Method นี้ ผ่านคำสั่ง ${promo.formattedEndDate}
    public String getFormattedEndDate() {
        // ดึงค่าจาก private LocalDate endDate; ของคุณมาเช็ค
        if (this.endDate == null) {
            return "-"; 
        }
        // แปลงรูปแบบจาก 2026-06-30 ให้เป็น 30 Jun 2026
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy", java.util.Locale.ENGLISH); 
        return this.endDate.format(formatter);
    }

}