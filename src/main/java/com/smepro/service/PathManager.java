package com.smepro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.drivesDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.models.drives;
import com.smepro.models.file;
import com.smepro.models.folder;



@Service
public class PathManager {
	
	
	
		private  folderDAO FolderDAO;
		private  drivesDAO DrivesDAO;
		private  fileDAO FileDAO;
		private  fileservice Fileservice;
		private int idfolder;
		private String Pathfull;
		
		
	    @Autowired
	    public void setMyRepository(
	    		folderDAO FolderDAO
	    		,drivesDAO DrivesDAO
	    		,fileDAO FileDAO
	    		,fileservice Fileservice
	    		) {
	        this.FolderDAO =FolderDAO; 
	        this.DrivesDAO =DrivesDAO; 
	        this.FileDAO =FileDAO; 
	        this.Fileservice =Fileservice; 
	    }
		
 
	
				public void run() {
					    setIdfolder(0);
					    setPathfull(null);
				}
				
	
		    public void Path(int idfolder) {
		
				 	String Pathfull;
					folder folders = FolderDAO.getbyId(idfolder);
					folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
					    int drives_id;
					    drives_id = folders.getFolder_idrefer();
					    if(mainfolders != null) {
					    drives_id= mainfolders.getFolder_idrefer();	
					    }
					    drives drivess  = DrivesDAO.getbyId(drives_id);  
					    long totals = drivess.getDrives_size() - drivess.getDrives_use();
					    file sum = FileDAO.getsum(folders.getFolder_type());
					   
					    
					    if(mainfolders != null) {
					    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
				 	    long imageFileSize3 = sum2.getFolder_use();
				 	    String Size3 = Fileservice.printFileSize(imageFileSize3);
				 	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
				 	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
					    }else {
					    	
				     	drives_id = folders.getFolder_idrefer();
				     	drivess  = DrivesDAO.getbyId(drives_id);
				     	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
				 	    	
				 	    }
		     
					    setIdfolder(idfolder);
					    setPathfull(Pathfull);
	
		}
	
	
	
	
	
	public int getIdfolder() {
		return idfolder;
	}
	private void setIdfolder(int idfolder) {
		this.idfolder = idfolder;
	}
	public String getPathfull() {
		return Pathfull;
	}
	private void setPathfull(String pathfull) {
		Pathfull = pathfull;
	}
		    
}
