package com.smepro.models;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileInfo {

	
	 private int file_id;
	 private String file_name_original;
	 private String file_name;
	 private long file_size;
	 private String file_size_display;
	 private String file_type;
	 private CommonsMultipartFile file;
	 private String file_path;
	 private String file_date;
	 
	 
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_name_original() {
		return file_name_original;
	}
	public void setFile_name_original(String file_name_original) {
		this.file_name_original = file_name_original;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getFile_size_display() {
		return file_size_display;
	}
	public void setFile_size_display(String file_size_display) {
		this.file_size_display = file_size_display;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_date() {
		return file_date;
	}
	public void setFile_date(String file_date) {
		this.file_date = file_date;
	}

	 
	 
	 
}
