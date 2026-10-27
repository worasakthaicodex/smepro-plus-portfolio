package com.smepro.dto;

public class folderDTO {
	private int folder_id   = 0;
	private int folder_subid   = 0;
	private String folder_name ="";
	private String drivesname ="";
	private String foldername ="";
	private String subfoldername ="";
	private int folder_subid2   = 0;
	private String subfoldername2 ="";
	
	
	
	public int getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}
	public int getFolder_subid() {
		return folder_subid;
	}
	public void setFolder_subid(int folder_subid) {
		this.folder_subid = folder_subid;
	}
	public String getFolder_name() {
		return folder_name;
	}
	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}
	public String getDrivesname() {
		return drivesname;
	}
	public void setDrivesname(String drivesname) {
		this.drivesname = drivesname;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public String getSubfoldername() {
		return subfoldername;
	}
	public void setSubfoldername(String subfoldername) {
		this.subfoldername = subfoldername;
	}
	public int getFolder_subid2() {
		return folder_subid2;
	}
	public void setFolder_subid2(int folder_subid2) {
		this.folder_subid2 = folder_subid2;
	}
	public String getSubfoldername2() {
		return subfoldername2;
	}
	public void setSubfoldername2(String subfoldername2) {
		this.subfoldername2 = subfoldername2;
	}
	
	
	
	@Override
	public String toString() {
		return "folderDTO [folder_id=" + folder_id + ", folder_subid=" + folder_subid + ", folder_name=" + folder_name
				+ ", drivesname=" + drivesname + ", foldername=" + foldername + ", subfoldername=" + subfoldername
				+ ", folder_subid2=" + folder_subid2 + ", subfoldername2=" + subfoldername2 + "]";
	}
	
	
}
