package com.smepro.models;

import java.util.List;

public class folder {
	private int folder_id ;
	private String folder_name;
	private String folder_type;
	private int folder_idrefer;
	private long folder_use;
	private String folder_use2;
	private int folder_type2;
	
	private long folder_doc;
	private long folder_work;
	private List<folder> folder;
	
	
	
	
	public int getFolder_id() {
		return folder_id;
	}
	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}
	public String getFolder_name() {
		return folder_name;
	}
	public void setFolder_name(String folder_name) {
		this.folder_name = folder_name;
	}
	public String getFolder_type() {
		return folder_type;
	}
	public void setFolder_type(String folder_type) {
		this.folder_type = folder_type;
	}
	public int getFolder_idrefer() {
		return folder_idrefer;
	}
	public void setFolder_idrefer(int folder_idrefer) {
		this.folder_idrefer = folder_idrefer;
	}

	public long getFolder_use() {
		return folder_use;
	}
	public void setFolder_use(long folder_use) {
		this.folder_use = folder_use;
	}
	public String getFolder_use2() {
		return folder_use2;
	}
	public void setFolder_use2(String folder_use2) {
		this.folder_use2 = folder_use2;
	}
	public long getFolder_doc() {
		return folder_doc;
	}
	public void setFolder_doc(long folder_doc) {
		this.folder_doc = folder_doc;
	}
	public long getFolder_work() {
		return folder_work;
	}
	public void setFolder_work(long folder_work) {
		this.folder_work = folder_work;
	}
	public int getFolder_type2() {
		return folder_type2;
	}
	public void setFolder_type2(int folder_type2) {
		this.folder_type2 = folder_type2;
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
				"folder [folder_id=%s, folder_name=%s, folder_type=%s, folder_idrefer=%s, folder_use=%s, folder_use2=%s]",
				folder_id, folder_name, folder_type, folder_idrefer, folder_use, folder_use2);
	}
	
	
	
}
