package com.smepro.dto;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smepro.models.folder;
import com.smepro.models.upload_pn;
import com.smepro.models.user;


public class savefileDTO {
	
	
	 private  String filename;
	 private String newfilename;
	 private String typereal;
	 private String folder_type;
	 private long size;
	 private String size_string;
	 private  int folder_id;
	 private int report_pnid;
	 private String date;
	 private String pathfull;
	 private String username;
	 private String dateend;
	 private int file_ticketid;
	 private  int file_step;
	 private  int file_edata;
	 private  int  file_indexapp;
	 private  String file_tag;

	 
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getNewfilename() {
		return newfilename;
	}
	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}
	public String getTypereal() {
		return typereal;
	}
	public void setTypereal(String typereal) {
		this.typereal = typereal;
	}
	public String getFolder_type() {
		return folder_type;
	}
	public void setFolder_type(String folder_type) {
		this.folder_type = folder_type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}
	public int getReport_pnid() {
		return report_pnid;
	}
	public void setReport_pnid(int report_pnid) {
		this.report_pnid = report_pnid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPathfull() {
		return pathfull;
	}
	public void setPathfull(String pathfull) {
		this.pathfull = pathfull;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDateend() {
		return dateend;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	public String getSize_string() {
		return size_string;
	}
	public void setSize_string(String size_string) {
		this.size_string = size_string;
	}
	public int getFile_step() {
		return file_step;
	}
	public void setFile_step(int file_step) {
		this.file_step = file_step;
	}
	public int getFile_edata() {
		return file_edata;
	}
	public void setFile_edata(int file_edata) {
		this.file_edata = file_edata;
	}
	public String getFile_tag() {
		return file_tag;
	}
	public void setFile_tag(String file_tag) {
		this.file_tag = file_tag;
	}
	public int getFile_indexapp() {
		return file_indexapp;
	}
	public void setFile_indexapp(int file_indexapp) {
		this.file_indexapp = file_indexapp;
	}
	public int getFile_ticketid() {
		return file_ticketid;
	}
	public void setFile_ticketid(int file_ticketid) {
		this.file_ticketid = file_ticketid;
	}

	
	 
	 
}
