package com.smepro.models;

import java.util.List;

public class drives {

	private int drives_id;
	private String drives_name;
	private long drives_size;
	private String drives_drive;
	private String drives_size2;
	private long drives_use;
	private String drives_use2;
	private double drives_usedisplay;
	private List<folder> folder;
	private long drives_remain;
	private String  drives_remain2;
	
	
	public int getDrives_id() {
		return drives_id;
	}
	public void setDrives_id(int drives_id) {
		this.drives_id = drives_id;
	}
	public String getDrives_name() {
		return drives_name;
	}
	public void setDrives_name(String drives_name) {
		this.drives_name = drives_name;
	}
	public long getDrives_size() {
		return drives_size;
	}
	public void setDrives_size(long drives_size) {
		this.drives_size = drives_size;
	}

	public String getDrives_drive() {
		return drives_drive;
	}
	public void setDrives_drive(String string) {
		this.drives_drive = string;
	}	
	public String getDrives_size2() {
		return drives_size2;
	}
	public void setDrives_size2(String drives_size2) {
		this.drives_size2 = drives_size2;
	}
	public long getDrives_use() {
		return drives_use;
	}
	public void setDrives_use(long drives_use) {
		this.drives_use = drives_use;
	}
	public String getDrives_use2() {
		return drives_use2;
	}
	public void setDrives_use2(String drives_use2) {
		this.drives_use2 = drives_use2;
	}
	public double getDrives_usedisplay() {
		return drives_usedisplay;
	}
	public void setDrives_usedisplay(double drives_usedisplay) {
		this.drives_usedisplay = drives_usedisplay;
	}
	

	public long getDrives_remain() {
		return drives_remain;
	}
	public void setDrives_remain(long drives_remain) {
		this.drives_remain = drives_remain;
	}
	public String getDrives_remain2() {
		return drives_remain2;
	}
	public void setDrives_remain2(String drives_remain2) {
		this.drives_remain2 = drives_remain2;
	}
	public List<folder> getFolder() {
		return folder;
	}
	public void setFolder(List<folder> folder) {
		this.folder = folder;
	}
	@Override
	public String toString() {
		return String.format(
				"drives [drives_id=%s, drives_name=%s, drives_size=%s, drives_drive=%s, drives_size2=%s, drives_use=%s, drives_use2=%s, drives_usedisplay=%s]",
				drives_id, drives_name, drives_size, drives_drive, drives_size2, drives_use, drives_use2,
				drives_usedisplay);
	}
	
	
	
	
}
