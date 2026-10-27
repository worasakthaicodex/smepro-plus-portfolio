package com.smepro.dto;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smepro.models.folder;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.models.log;

public class fileDTO {
	 private  String oldfile1;
	 private String oldfile2;
	 private String oldfile3;
	 private String oldfile4;
	 private String oldfile5;
	 private  String oldfilename1;
	 private String oldfilename2;
	 private String oldfilename3;
	 private String oldfilename4;
	 private String oldfilename5;
	 
	 private CommonsMultipartFile file1;
	 private CommonsMultipartFile file2;
	 private CommonsMultipartFile file3;
	 private CommonsMultipartFile file4;
	 private CommonsMultipartFile file5;
	 private  int report_pnid;
	 private  upload_pn upload_pn;
	 private  folder folders;
	 private  HttpSession session;
	 private  log log;
	 
	 private String  date;
	 private user user;
	 private  int edata_id;
	 
	 
	  public String getOldfile1() {
		return oldfile1;
	}
	public void setOldfile1(String oldfile1) {
		this.oldfile1 = oldfile1;
	}
	public String getOldfile2() {
		return oldfile2;
	}
	public void setOldfile2(String oldfile2) {
		this.oldfile2 = oldfile2;
	}
	public String getOldfile3() {
		return oldfile3;
	}
	public void setOldfile3(String oldfile3) {
		this.oldfile3 = oldfile3;
	}
	public String getOldfile4() {
		return oldfile4;
	}
	public void setOldfile4(String oldfile4) {
		this.oldfile4 = oldfile4;
	}
	public String getOldfile5() {
		return oldfile5;
	}
	public void setOldfile5(String oldfile5) {
		this.oldfile5 = oldfile5;
	}
	public String getOldfilename1() {
		return oldfilename1;
	}
	public void setOldfilename1(String oldfilename1) {
		this.oldfilename1 = oldfilename1;
	}
	public String getOldfilename2() {
		return oldfilename2;
	}
	public void setOldfilename2(String oldfilename2) {
		this.oldfilename2 = oldfilename2;
	}
	public String getOldfilename3() {
		return oldfilename3;
	}
	public void setOldfilename3(String oldfilename3) {
		this.oldfilename3 = oldfilename3;
	}
	public String getOldfilename4() {
		return oldfilename4;
	}
	public void setOldfilename4(String oldfilename4) {
		this.oldfilename4 = oldfilename4;
	}
	public String getOldfilename5() {
		return oldfilename5;
	}
	public void setOldfilename5(String oldfilename5) {
		this.oldfilename5 = oldfilename5;
	}
	public CommonsMultipartFile getFile1() {
		return file1;
	}
	public void setFile1(CommonsMultipartFile file1) {
		this.file1 = file1;
	}
	public CommonsMultipartFile getFile2() {
		return file2;
	}
	public void setFile2(CommonsMultipartFile file2) {
		this.file2 = file2;
	}
	public CommonsMultipartFile getFile3() {
		return file3;
	}
	public void setFile3(CommonsMultipartFile file3) {
		this.file3 = file3;
	}
	public CommonsMultipartFile getFile4() {
		return file4;
	}
	public void setFile4(CommonsMultipartFile file4) {
		this.file4 = file4;
	}
	public CommonsMultipartFile getFile5() {
		return file5;
	}
	public void setFile5(CommonsMultipartFile file5) {
		this.file5 = file5;
	}
	public int getReport_pnid() {
		return report_pnid;
	}
	public void setReport_pnid(int report_pnid) {
		this.report_pnid = report_pnid;
	}
	public upload_pn getUpload_pn() {
		return upload_pn;
	}
	public void setUpload_pns(upload_pn upload_pn) {
		this.upload_pn = upload_pn;
	}
	public folder getFolders() {
		return folders;
	}
	public void setFolders(folder folders) {
		this.folders = folders;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setUpload_pn(upload_pn upload_pn) {
		this.upload_pn = upload_pn;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public log getLog() {
		return log;
	}
	public void setLog(log log) {
		this.log = log;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	 
	 
	 
}
