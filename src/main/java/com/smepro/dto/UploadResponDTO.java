package com.smepro.dto;

public class UploadResponDTO {

	
	private int id ;
	private String  respon;
	private int isUploads ;
	private  int missedUploads;
	private  int isUploadsLast;
	
	
	public String getRespon() {
		return respon;
	}

	public void setRespon(String respon) {
		this.respon = respon;
	}

	public int getIsUploads() {
		return isUploads;
	}

	public void setIsUploads(int isUploads) {
		this.isUploads = isUploads;
	}

	public int getMissedUploads() {
		return missedUploads;
	}

	public void setMissedUploads(int missedUploads) {
		this.missedUploads = missedUploads;
	}

	public int getIsUploadsLast() {
		return isUploadsLast;
	}

	public void setIsUploadsLast(int isUploadsLast) {
		this.isUploadsLast = isUploadsLast;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
