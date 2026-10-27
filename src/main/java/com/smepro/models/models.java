package com.smepro.models;

import java.util.Objects;

public class models {
   
	
	private int id;
	private String name;
	private String date;
	private boolean  isActive;
	
	
	
	
	public models(String name, String date, boolean isActive) {
		super();
		this.name = name;
		this.date = date;
		this.isActive = isActive;
	}
	public models(int id, String name, String date, boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.isActive = isActive;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return String.format("models [id=%s, name=%s, date=%s, isActive=%s]", id, name, date, isActive
				);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, id, isActive, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		models other = (models) obj;
		return Objects.equals(date, other.date) && id == other.id && isActive == other.isActive
				&& Objects.equals(name, other.name);
	}

	
	 
	
	
	
}
