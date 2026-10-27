package com.smepro.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * MaterialResource — วัสดุ/ทรัพยากรที่ใช้ใน Task (BOM)
 */
@Entity
@Table(name = "pp_material_resource")
public class MaterialResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** รหัสวัสดุ */
    @Column(name = "material_code", length = 50)
    private String materialCode;

    /** ชื่อวัสดุ */
    @Column(name = "material_name", nullable = false, length = 300)
    private String materialName;

    /** หมวดหมู่: HARDWARE, SOFTWARE, SERVICE, CONSUMABLE */
    @Column(name = "category", length = 30)
    private String category;

    /** หน่วย เช่น EA, KG, SET, HOUR */
    @Column(name = "unit", length = 20)
    private String unit;

    /** จำนวนที่วางแผน */
    @Column(name = "planned_qty", precision = 10, scale = 2)
    private BigDecimal plannedQty;

    /** จำนวนจริงที่ใช้ */
    @Column(name = "actual_qty", precision = 10, scale = 2)
    private BigDecimal actualQty = BigDecimal.ZERO;

    /** ราคาต่อหน่วย */
    @Column(name = "unit_price", precision = 12, scale = 2)
    private BigDecimal unitPrice;

    /** มูลค่ารวม (plannedQty * unitPrice) คำนวณอัตโนมัติ */
    @Column(name = "total_amount", precision = 15, scale = 2)
    private BigDecimal totalAmount;

    /**
     * สถานะ:
     * PLANNED, ORDERED, DELIVERED, USED
     */
    @Column(name = "status", length = 20)
    private String status = "PLANNED";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /* RELATIONSHIPS */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wbs_id")
    private WbsElement wbsElement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        // Auto-calculate total
        if (plannedQty != null && unitPrice != null) {
            totalAmount = plannedQty.multiply(unitPrice);
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMaterialCode() { return materialCode; }
    public void setMaterialCode(String materialCode) { this.materialCode = materialCode; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public BigDecimal getPlannedQty() { return plannedQty; }
    public void setPlannedQty(BigDecimal plannedQty) { this.plannedQty = plannedQty; }
    public BigDecimal getActualQty() { return actualQty; }
    public void setActualQty(BigDecimal actualQty) { this.actualQty = actualQty; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public WbsElement getWbsElement() { return wbsElement; }
    public void setWbsElement(WbsElement wbsElement) { this.wbsElement = wbsElement; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}
