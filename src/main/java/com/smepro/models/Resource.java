package com.smepro.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Resource {
	
	  private Long id;
	    private String name;
	    private String description;
	    private String resourceType; // Labor, Material, Equipment, Facility
	    private String category;
	    private String unit;
	    private Long taskId;
	    
	    private BigDecimal quantityAvailable;
	    private BigDecimal quantityRequired;
	    private BigDecimal quantityAllocated;
	    private BigDecimal quantityToProcure;
	    private String usageStatus; // Available, InUse, UnderMaintenance, Unavailable

	    private BigDecimal estimatedCost;
	    private BigDecimal totalCost;
	    private BigDecimal lastPurchasePrice;
	    private String supplier;

	    private String pastProjects;
	    private BigDecimal performanceRating;
	    private String remarks;
	    private String maintenanceHistory;

	    private BigDecimal utilizationRate;
	    private Integer downtime;  // หน่วยเป็นชั่วโมง
	    private BigDecimal onTimeRate;

	    private Long projectId;
		private int actual;
		private int plannedQty;
	    private BigDecimal actualCost;
		
		

	    // Dynamic Reference
	    private Long idRefer;
	    private int use_id;
	    private String typeRefer;
	    private String tableRefer;

	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    
	    
	    // ===== Getters & Setters =====
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public String getResourceType() { return resourceType; }
	    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

	    public String getCategory() { return category; }
	    public void setCategory(String category) { this.category = category; }

	    public String getUnit() { return unit; }
	    public void setUnit(String unit) { this.unit = unit; }

	    public BigDecimal getQuantityAvailable() { return quantityAvailable; }
	    public void setQuantityAvailable(BigDecimal quantityAvailable) { this.quantityAvailable = quantityAvailable; }

	    public BigDecimal getQuantityRequired() { return quantityRequired; }
	    public void setQuantityRequired(BigDecimal quantityRequired) { this.quantityRequired = quantityRequired; }

	    public BigDecimal getQuantityAllocated() { return quantityAllocated; }
	    public void setQuantityAllocated(BigDecimal quantityAllocated) { this.quantityAllocated = quantityAllocated; }

	    public BigDecimal getQuantityToProcure() { return quantityToProcure; }
	    public void setQuantityToProcure(BigDecimal quantityToProcure) { this.quantityToProcure = quantityToProcure; }

	    public String getUsageStatus() { return usageStatus; }
	    public void setUsageStatus(String usageStatus) { this.usageStatus = usageStatus; }

	    public BigDecimal getEstimatedCost() { return estimatedCost; }
	    public void setEstimatedCost(BigDecimal estimatedCost) { this.estimatedCost = estimatedCost; }

	    public BigDecimal getTotalCost() { return totalCost; }
	    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }

	    public BigDecimal getLastPurchasePrice() { return lastPurchasePrice; }
	    public void setLastPurchasePrice(BigDecimal lastPurchasePrice) { this.lastPurchasePrice = lastPurchasePrice; }

	    public String getSupplier() { return supplier; }
	    public void setSupplier(String supplier) { this.supplier = supplier; }

	    public String getPastProjects() { return pastProjects; }
	    public void setPastProjects(String pastProjects) { this.pastProjects = pastProjects; }

	    public BigDecimal getPerformanceRating() { return performanceRating; }
	    public void setPerformanceRating(BigDecimal performanceRating) { this.performanceRating = performanceRating; }

	    public String getRemarks() { return remarks; }
	    public void setRemarks(String remarks) { this.remarks = remarks; }

	    public String getMaintenanceHistory() { return maintenanceHistory; }
	    public void setMaintenanceHistory(String maintenanceHistory) { this.maintenanceHistory = maintenanceHistory; }

	    public BigDecimal getUtilizationRate() { return utilizationRate; }
	    public void setUtilizationRate(BigDecimal utilizationRate) { this.utilizationRate = utilizationRate; }

	    public Integer getDowntime() { return downtime; }
	    public void setDowntime(Integer downtime) { this.downtime = downtime; }

	    public BigDecimal getOnTimeRate() { return onTimeRate; }
	    public void setOnTimeRate(BigDecimal onTimeRate) { this.onTimeRate = onTimeRate; }

	    public Long getProjectId() { return projectId; }
	    public void setProjectId(Long projectId) { this.projectId = projectId; }

	    public Long getTaskId() { return taskId; }
	    public void setTaskId(Long taskId) { this.taskId = taskId; }

	    public Long getIdRefer() { return idRefer; }
	    public void setIdRefer(Long idRefer) { this.idRefer = idRefer; }

	    public String getTypeRefer() { return typeRefer; }
	    public void setTypeRefer(String typeRefer) { this.typeRefer = typeRefer; }

	    public String getTableRefer() { return tableRefer; }
	    public void setTableRefer(String tableRefer) { this.tableRefer = tableRefer; }

	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	    public LocalDateTime getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
		
	    
	    public int getActual() {
			return actual;
		}
		public void setActual(int actual) {
			this.actual = actual;
		}
		public int getUse_id() {
			return use_id;
		}
		public void setUse_id(int use_id) {
			this.use_id = use_id;
		}
	    
		public int getPlannedQty() {
			return plannedQty;
		}
		public void setPlannedQty(int plannedQty) {
			this.plannedQty = plannedQty;
		}
		public BigDecimal getactualCost() { return actualCost; }
		public void setActualCost(BigDecimal actual_cost) { this.actualCost = actual_cost; }
	    
}
