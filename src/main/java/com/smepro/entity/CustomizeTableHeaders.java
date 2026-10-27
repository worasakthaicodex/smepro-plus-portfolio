package com.smepro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customize_table_headers") // กำหนดชื่อ Table ใน Database
public class CustomizeTableHeaders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // สำหรับ Auto Increment
    private int id;

    @Column(name = "type")
    private String type;

    // ปรับชื่อตัวแปรให้เป็น camelCase ตามหลัก Java Naming Convention
    @Column(name = "column_name") 
    private String columnName;

    @Column(name = "customize_name")
    private String customizeName;

    @Column(name = "column_for_use")
    private String columnForUse;

    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCustomizeName() {
		return customizeName;
	}

	public void setCustomizeName(String customizeName) {
		this.customizeName = customizeName;
	}

	public String getColumnForUse() {
		return columnForUse;
	}

	public void setColumnForUse(String columnForUse) {
		this.columnForUse = columnForUse;
	}

    
    
    
}

 
