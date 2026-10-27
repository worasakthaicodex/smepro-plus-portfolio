package com.smepro.models;

import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Filemanager {
	
	
	 protected StringJoiner joiner = new StringJoiner(",");
	 protected StringJoiner joiner2 = new StringJoiner(",");
	 protected StringJoiner joiner3 = new StringJoiner(",");
	 	
	 	
	 protected String NameFile;
	 protected String RealNameFile;
	 	
	 protected	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 	
	 	
	 protected String Pathfull;
	 protected int file_status = 1;
	 protected int Folder_id2;
	 protected long imageFileSize2;
	 protected String Size2;

	 protected int Folder_id3;
	 protected long imageFileSize3;
	 protected String Size3;
	    
	    
	 protected double percent;
	 protected long imageFileSize4;
	 protected String Size4;
	 protected int drives_id;
	 
	 
	 
		public void UpdateNameFile() {
			NameFile = joiner.toString();
		}
		public void UpdateAllRealnameFile() {
			RealNameFile= joiner3.toString();
		}
		public void setAllNameFile(String NameFile) {
			this.NameFile = NameFile;
		}
		public void setAllRealnameFile(String RealNameFile) {
			this.RealNameFile = RealNameFile;
		}
		public String getAllNameFile() {
			return NameFile;
		}
		public String getAllRealnameFile() {
			return RealNameFile;
		}
		public String getPathfull() {
			return Pathfull;
		}
		public int getFile_status() {
			return file_status;
		}

}
