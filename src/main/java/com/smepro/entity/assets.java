package com.smepro.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "assets")
public class assets {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "assetId", unique = true)
	private int assetId;
	
	private String  assetCode;
	private String  assetDateAdd;
	
	
	@Column(name = "asset_name")
	private String  assetName;
	
	@Column(name = "asset_type")
	private String assetType;
	
	private String  asset_group;
	private String asset_description;
	private String acquisition_date;
	private String acquisition_cost;
	private String useful_life_years;
	private String depreciation_method;
	private String residual_value;
	private String accumulated_depreciation;
	private String book_value;
	private String  doc;
	private String  assetStatus;
	
	@Column(name = "edata_id")
	private int edataId;
	private String tempId;
	
    @Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getAssetDateAdd() {
		return assetDateAdd;
	}

	public void setAssetDateAdd(String assetDateAdd) {
		this.assetDateAdd = assetDateAdd;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAsset_group() {
		return asset_group;
	}

	public void setAsset_group(String asset_group) {
		this.asset_group = asset_group;
	}

	public String getAsset_description() {
		return asset_description;
	}

	public void setAsset_description(String asset_description) {
		this.asset_description = asset_description;
	}

	public String getAcquisition_date() {
		return acquisition_date;
	}

	public void setAcquisition_date(String acquisition_date) {
		this.acquisition_date = acquisition_date;
	}

	public String getAcquisition_cost() {
		return acquisition_cost;
	}

	public void setAcquisition_cost(String acquisition_cost) {
		this.acquisition_cost = acquisition_cost;
	}

	public String getUseful_life_years() {
		return useful_life_years;
	}

	public void setUseful_life_years(String useful_life_years) {
		this.useful_life_years = useful_life_years;
	}

	public String getDepreciation_method() {
		return depreciation_method;
	}

	public void setDepreciation_method(String depreciation_method) {
		this.depreciation_method = depreciation_method;
	}

	public String getResidual_value() {
		return residual_value;
	}

	public void setResidual_value(String residual_value) {
		this.residual_value = residual_value;
	}

	public String getAccumulated_depreciation() {
		return accumulated_depreciation;
	}

	public void setAccumulated_depreciation(String accumulated_depreciation) {
		this.accumulated_depreciation = accumulated_depreciation;
	}

	public String getBook_value() {
		return book_value;
	}

	public void setBook_value(String book_value) {
		this.book_value = book_value;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public int getEdataId() {
		return edataId;
	}

	public void setEdataId(int edataId) {
		this.edataId = edataId;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAssetStatus() {
		return assetStatus;
	}

	public void setAssetStatus(String assetStatus) {
		this.assetStatus = assetStatus;
	}
	
	  
}
