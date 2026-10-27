package com.smepro.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smepro.dao.Extension_Component;
import com.smepro.dao.Finance_Accounting;
import com.smepro.dao.HR_Workforce;
import com.smepro.dao.Machinery_Factory;
import com.smepro.dao.Procurement_SupplyChain;
import com.smepro.dao.Production;
import com.smepro.dao.Sales_Customer;
import com.smepro.dao.Stock_Warehouse;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.FileInfo;
import com.smepro.models.LoadApp;
import com.smepro.models.drives;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.notification;
import com.smepro.models.user;

@Service
public class Uploadfileservice {

	   private 	int fileLogId = 0;
	   private 	String error;
	   private 	LoadApp loadApp;
	   private 	List<FileInfo> FileInfos = new ArrayList<FileInfo>();
	   
	   
	   private 	fileservice Fileservice;
	   private 	folderDAO FolderDAO;
	   private 	drivesDAO DrivesDAO;
	   private 	fileDAO FileDAO;
	   private 	userDAO UserDAO;
	   private 	userservice Userservice;
	   private boolean status;
	   
	   
	   
	   

	   @Autowired
		public void setMyRepository(
				   	fileservice Fileservice,
				   	folderDAO FolderDAO,
				   	drivesDAO DrivesDAO,
				   	fileDAO FileDAO,
				   	userDAO UserDAO,
				   	userservice Userservice
		    		) { 
		        this.Fileservice = Fileservice;
		        this.FolderDAO = FolderDAO;
		        this.DrivesDAO = DrivesDAO;
		        this.FileDAO = FileDAO;
		        this.UserDAO = UserDAO;
		        this.Userservice = Userservice;
		    }

		
	   
		public Uploadfileservice initiate(LoadApp loadApp) {
			this.loadApp = loadApp;
			FileInfos.clear();
			return this;
		}
		
		public void upload() {
			
			if(loadApp.getPlatform().getFiles() == null) {	return; }
			
			hasEnoughStorage();
			FileInfo();
			writeFileUploadLog();
		}
	
		public boolean CheckMemorySize(folder folders,long imageFileSize ){
			
	  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());

	  	    int drives_id = folders.getFolder_idrefer();
	  	    if(mainfolders != null) {
	  	    drives_id= mainfolders.getFolder_idrefer();	
	  	    }
	  	    drives drivess  = DrivesDAO.getbyId(drives_id);
	            
	  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();

	  	    File disk = new File(System.getProperty("user.dir"));
	  	    long freeSpace = disk.getFreeSpace();
	  	    System.out.println("Free Space: " + freeSpace + " bytes");
	  	    
	  	    if(imageFileSize > totals) {
  	            return false;
	  	    }
	  	    if(imageFileSize > freeSpace) {
	  	        return false;
	  	    }
	  	    
	  	    
	  	  return true; 	    
	  	    
		} 
		public boolean status() {
			return this.status;
		}
	        
		public List<FileInfo> getFileInfo() {
			return FileInfos;
		}
		public String getError() {
			return error;
		}

		private void FileInfo() {
			
			if(this.status == false) {	return; }
			if(loadApp.getPlatform().getPatch() == null) {	return; }
			if(loadApp.getPlatform().getRealPath() == null) {	return; }
			
			 LocalDateTime localDate = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = localDate.format(formatter);
			
			
			CommonsMultipartFile[] files = loadApp.getPlatform().getFiles();

			String uploadDir = loadApp.getPlatform().getRealPath();
					
			
			for (CommonsMultipartFile file : files) {	
			
				FileInfo FileInfo = new FileInfo();
			    String filenameoriginal = file.getOriginalFilename();  
			    String filename = Fileservice.assertEquals(file.getContentType());
			    long imageFileSize = file.getSize();
			    String Size = Fileservice.printFileSize(imageFileSize);
			    
			    if(filename != null) {
				    FileInfo.setFile_name_original(filenameoriginal);
				    FileInfo.setFile_name(filename);
				    FileInfo.setFile_size(imageFileSize);
				    FileInfo.setFile_size_display(Size);
				    FileInfo.setFile_type(file.getContentType());
				    FileInfo.setFile(file);
				    FileInfo.setFile_date(date);
				    FileInfos.add(FileInfo);
			    }
			
			}	
			
		}
		
		
		private void hasEnoughStorage() {
	
			if(loadApp.getPlatform().getPatch() == null) {	this.status = false; return; }
			
			
			CommonsMultipartFile[] files = loadApp.getPlatform().getFiles();
			long totalSize = Arrays.stream(files).mapToLong(CommonsMultipartFile::getSize).sum();
			folder folders = loadApp.getPlatform().getPatch();
			boolean diskspace = CheckMemorySize(folders,totalSize);
			
			if(!diskspace) {
					this.status = false;
				    
					/*
					notification  notifications = new notification();
		 			notifications.setNotification_iduser(users.getUser_name());
					notifications.setNotification_iduser2(null);
					notifications.setNotification_alert("⚠ คำเตือน: พื้นที่ดิสก์เหลือน้อย!ไม่เพียงพอ");
					notifications.setNotification_note("");
					notifications.setNotification_date(datetime);
					notifications.setNotification_read(0);
					notifications.setNotification_refer("0");
					notifications.setNotification_type("⚠ Warning: Low disk space! Less than remaining. (System Error)");
					NotificationDAO.save(notifications);  
					*/
					return;
			}
			this.status = true;
		}
		
		private void writeFileUploadLog() {
		
			FileInfos.stream().forEach(row -> {
			    try {
			        String path = loadApp.getPlatform().getRealPath();

			        byte[] bytes = row.getFile().getBytes();  
			        try (BufferedOutputStream stream = new BufferedOutputStream(
			                new FileOutputStream(new File(path + File.separator + row.getFile_name())))) {
			            stream.write(bytes);
			        }
			        
			        row.setFile_path(loadApp.getPlatform().getPatch().getFolder_type());
			        int file_id = FileDAO.saveByFileInfo(row); 	 
			        sumDir(file_id);
			        row.setFile_id(file_id);
			  

			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			});

		}

	



	
		
		private void sumDir(int file_id) {
			
			folder folders = loadApp.getPlatform().getPatch();
			file imageFileSize = FileDAO.getsum(folders.getFolder_type());
		    String Size = Fileservice.printFileSize(imageFileSize.getFile_size());
		    FolderDAO.setuse(folders.getFolder_id(),imageFileSize.getFile_size(),Size);
		    StringBuilder patchString =  new StringBuilder(); 
		    patchString.append("/"+folders.getFolder_name());
		    sumUpperDir(patchString);
		  
			user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		    FileDAO.setPop(patchString.toString(),users,file_id);
			
		}
		private void sumUpperDir(StringBuilder patchString) {
		    folder mainfolders = FolderDAO.getbyId( loadApp.getPlatform().getPatch().getFolder_idrefer());
			
		    if(mainfolders != null) {
		    	folder sum = FolderDAO.getsum(mainfolders.getFolder_id());
	    	    long imageFileSize = sum.getFolder_use();
	    	    String Size = Fileservice.printFileSize(imageFileSize);
	    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize,Size);
	    	    
	    	    patchString.insert(0, "/"+mainfolders.getFolder_name()); 
	    	    
	    	    sumDisk(mainfolders,patchString);
		    }else {
		        sumDisk(loadApp.getPlatform().getPatch(),patchString);
		    	
		    }   
			
		}
		private void sumDisk(folder folder, StringBuilder patchString) {
			
			
				drives drives = DrivesDAO.getbyId(folder.getFolder_idrefer());

	    		long imageFileSize = 0;
				folder sum2 = FolderDAO.getsum(drives.getDrives_id());
				imageFileSize = imageFileSize+ sum2.getFolder_use();
				
				List<folder> subfoder = FolderDAO.getall(drives.getDrives_id());
				
				for(folder data : subfoder) {
					folder sums = FolderDAO.getsum(data.getFolder_id());
					imageFileSize = imageFileSize+ sums.getFolder_use();
				}
				
	
			    double percent;
			    if(imageFileSize > 0) {
			       percent =  imageFileSize * 100 / drives.getDrives_size();
			    }else {
			    	 percent =0;	
			    }
			    
				
			    long remain =drives.getDrives_size() - imageFileSize;
			    
			    
				String Size = Fileservice.printFileSize(imageFileSize);
				String Size2 = Fileservice.printFileSize(remain);
			    DrivesDAO.setuse(drives.getDrives_id(),imageFileSize,Size,remain,Size2,percent);

	    	    patchString.insert(0,drives.getDrives_name()); 
		}
		
		
}
