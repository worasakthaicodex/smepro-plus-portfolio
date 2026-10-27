package com.smepro.models;

import java.math.BigDecimal;

public class Use_resources {

	private int id;
	private int actual;
	private int plannedQty;
    private BigDecimal actualCost;
    private BigDecimal estimatedCost;
    
	private int resources_id;
	private int task_id;
	
    private String name;
    private String description;
    private String resourceType; // Labor, Material, Equipment, Facility
    private String category;
    private String unit;



	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActual() {
		return actual;
	}
	public void setActual(int actual) {
		this.actual = actual;
	}
	public int getResources_id() {
		return resources_id;
	}
	public void setResources_id(int resources_id) {
		this.resources_id = resources_id;
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
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
	public int getPlannedQty() {
		return plannedQty;
	}
	public void setPlannedQty(int plannedQty) {
		this.plannedQty = plannedQty;
	}
	public BigDecimal getActualCost() { return actualCost; }
	public void setActualCost(BigDecimal actual_cost) { this.actualCost = actual_cost; }

    public BigDecimal getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(BigDecimal estimatedCost) { this.estimatedCost = estimatedCost; }
	
}
