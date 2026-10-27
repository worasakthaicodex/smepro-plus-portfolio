package com.smepro.service;


import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smepro.dao.Finance_Accounting;
import com.smepro.dao.HR_Workforce;
import com.smepro.dao.Identifiable;
import com.smepro.dao.Machinery_Factory;
import com.smepro.dao.Procurement_SupplyChain;
import com.smepro.dao.Production;
import com.smepro.dao.Sales_Customer;
import com.smepro.dao.Stock_Warehouse;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.group_authoritiesDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dto.DisbursementDTO;
import com.smepro.dto.JoinTransactionDTO;
import com.smepro.dto.MultipleModelsDTO;
import com.smepro.dto.fileDTO;
import com.smepro.dto.savefileDTO;
import com.smepro.entity.ReportJoinConfig;
import com.smepro.entity.account_ledger;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.entity.employee_compensation;
import com.smepro.entity.employee_discipline;
import com.smepro.entity.employee_shift;
import com.smepro.entity.fa_invoices;
import com.smepro.entity.fa_payments;
import com.smepro.entity.hr_action;
import com.smepro.entity.machine_activity;
import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.request_for_quotation;
import com.smepro.entity.sales_followup;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Register_main;
import com.smepro.models.StatusSummary;
import com.smepro.models.TenStatus;
import com.smepro.models.Transition;
import com.smepro.models.WorkSystemdata;
import com.smepro.models.bom;
import com.smepro.models.commitment;
import com.smepro.models.condition;
import com.smepro.models.control_steps;
import com.smepro.models.customreport;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.doc;
import com.smepro.models.drives;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_production_record;
import com.smepro.models.edata_register_processing;
import com.smepro.models.eformfield;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.group_authorities;
import com.smepro.models.keymap;
import com.smepro.models.loadmaindata;
import com.smepro.models.oee;
import com.smepro.models.other;
import com.smepro.models.process;
import com.smepro.models.product_log;
import com.smepro.models.product_master;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.qci;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.reportsetting;
import com.smepro.models.search_workflow_db;
import com.smepro.models.stock;
import com.smepro.models.stock_count;
import com.smepro.models.stock_log;
import com.smepro.models.stock_lot;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.models.wip;
import com.smepro.models.workflow;
import com.smepro.repository.Fa_paymentsRepository;
import com.smepro.simplifyjava.SafeSplitter;
import com.smepro.simplifyjava.SqlBuilderDTO;
import com.smepro.simplifyjava.SqlBuilderService;
import com.smepro.simplifyjava.SqlServices;
import com.smepro.simplifyjava.TableClassResolver;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Fg;
import com.smepro.models.Filemanager;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Myapp;



@Service
public class filemanagerservice extends  Filemanager {


		
		private fileservice Fileservice;
		private fileDAO FileDAO;
		private conditionDAO conditionDAO;
		private drivesDAO DrivesDAO;
		private folderDAO FolderDAO;
		private  PlatformService PlatformService;
		
		private userDAO UserDAO;
		private userservice Userservice;
		private  group_authoritiesDAO Group_authoritiesDAO;
		
		private edataDAO EdataDAO;
		private dataset_dataDAO Dataset_dataDAO;
		private datasetDAO DatasetDAO;
		private eformfieldDAO EformfieldDAO;
		private otherservice Otherservice;
		private workflowDAO WorkflowDAO;
		private Fa_paymentsRepository fa_paymentsRepository;
		private SqlServices sqlServices;
		private SqlBuilderService sqlBuilderService;
		
		

		
	    @Autowired
	    public void setMyRepository(
	    		fileservice Fileservice
	    		,fileDAO FileDAO
	    		,conditionDAO conditionDAO
	    		,drivesDAO DrivesDAO
	    		,PlatformService PlatformService
	    		,folderDAO FolderDAO
	    		,userservice Userservice
	    		,group_authoritiesDAO Group_authoritiesDAO 
	    		
	    		,edataDAO EdataDAO 
	    		,dataset_dataDAO Dataset_dataDAO 
	    		,datasetDAO DatasetDAO 
	    		,eformfieldDAO EformfieldDAO 
	    		,otherservice Otherservice
	    		,workflowDAO WorkflowDAO
	    		,Fa_paymentsRepository fa_paymentsRepository,
	    		SqlServices sqlServices,
	    		SqlBuilderService sqlBuilderService
	    		) { 
	    	
	    	
	    	
	    	
	        this.Fileservice = Fileservice;
	        this.FileDAO = FileDAO;
	        this.FolderDAO = FolderDAO;
	        this.DrivesDAO =DrivesDAO; 
	        this.conditionDAO =conditionDAO; 
	        this.Userservice =Userservice; 
	        this.Group_authoritiesDAO =Group_authoritiesDAO; 
	        this.EdataDAO =EdataDAO; 
	        this.Dataset_dataDAO =Dataset_dataDAO; 
	        this.DatasetDAO =DatasetDAO; 
	        this.EformfieldDAO =EformfieldDAO; 
	        this.PlatformService =PlatformService; 
	        this.Otherservice =Otherservice; 
	        this.WorkflowDAO =WorkflowDAO; 
	        this.fa_paymentsRepository =fa_paymentsRepository; 
	        this.sqlServices = sqlServices;
	        this.sqlBuilderService =sqlBuilderService;
	    }
	
	    
		public enum ApproveState {
			  NONE,SUCCESS, INVALID_TYPE;
		}
		public enum InventoryState {
			  NONE,SUCCESS, INVALID_TYPE;
		} 
	    
		
		
		   /**
	     * Method สำหรับดึงค่า upload_pn ที่เกี่ยวข้อง<br>
	     * ปกติ upload_pn จะมี 3 เเบบ <br>
	     * 1.เเบบ app <br>
	     * 2.เเบบ task<br>
	     * 3.เเบบไม่ระบุ<br
	     * เเบบ app เเละ task จะมีการระบุ ID <br>
	     * 
	     * 
	     * 
	     * @param long shift_id , String tenantId
	     * @return List<Shift>
	     * @throws 
	     * @since V1.00
	     * @author Worasak
		 * @throws IOException 
	     */ 
		public void Filemanager(fileDTO fileDTOs) throws IOException {
		
			 joiner = new StringJoiner(",");
			 joiner2 = new StringJoiner(",");
			 joiner3 = new StringJoiner(",");
			
			folder folders = fileDTOs.getFolders();
			if(CheckMemorySize(fileDTOs) == false) {
			 		return;
			}
			
			String UPLOAD_DIRECTORY ="/filesuploads/"+ folders.getFolder_type(); 
		    ServletContext context = fileDTOs.getSession().getServletContext();  
		    String path = context.getRealPath(UPLOAD_DIRECTORY);  
			
		    
		    if(fileDTOs.getFile1() == null && fileDTOs.getFile2()  == null  
		    		&& fileDTOs.getFile3()  == null && fileDTOs.getFile4()  == null  && fileDTOs.getFile5()  == null ) {
		    		file_status=0;
		    		Deletefile(fileDTOs);
					UpdateNameFile();
					UpdateAllRealnameFile();
		    		return;
		    }    
		    

			
			CommonsMultipartFile[] files = {fileDTOs.getFile1(), fileDTOs.getFile2(), fileDTOs.getFile3(), fileDTOs.getFile4(), fileDTOs.getFile5()};
			
			
			int i = 0;
			for (CommonsMultipartFile file : files) {
				i++;
			    if (file != null && file.getSize() > 0) {
			    	
			    	
				    String filenameoriginal = file.getOriginalFilename();  
				    String fileExtension = filenameoriginal.substring(filenameoriginal.lastIndexOf("."));
				    String filename = Fileservice.assertEquals(file.getContentType(),fileExtension);
				    String typereal = file.getContentType(); 
				    
				    
				    
				    if(file.getContentType().equals("application/octet-stream") ) {
				    	if(!fileExtension.equals(".pdf")){
				    		typereal = "image/jpg";
				    	}
				    } 
				    
				    
				    String Size = Fileservice.printFileSize(file.getSize());
				    byte[] bytes = file.getBytes();  
				    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				 	         new File(path + File.separator + filename)));  
				 	    stream.write(bytes);  
				 	    stream.flush();  
				 	    stream.close();  
				 	    
				 	   String newfilename = Rename(fileDTOs,filenameoriginal,filename,i);
				 	   String dateend = CheckDateEnd(fileDTOs);
				 	   
				 	   
				 	   
				 	 savefileDTO savefile =  new savefileDTO();
				 	 
				 	 savefile.setFilename(filename);
				 	 savefile.setNewfilename(newfilename);
				 	 savefile.setTypereal(typereal);
				 	 savefile.setFolder_type(folders.getFolder_type());
				 	 savefile.setSize(file.getSize());
				 	 savefile.setSize_string(Size);
				 	 savefile.setFolder_id(folders.getFolder_id());
				 	 savefile.setReport_pnid(fileDTOs.getReport_pnid());
				 	 savefile.setDate(fileDTOs.getDate());
				 	 savefile.setPathfull(Pathfull);
				 	 savefile.setUsername(fileDTOs.getUser().getUsername());
				 	 savefile.setDateend(dateend);
				 	 savefile.setFile_step(i);
				 	 savefile.setFile_edata(fileDTOs.getEdata_id());
				  	 FileDAO.save(savefile);    
				 
					 
					 joiner.add(newfilename);
					 joiner2.add(Pathfull);
					 joiner3.add(filename);
					   
					       
			    }
			    
			    
			    
			    
			}
		
			setuse(fileDTOs);
			Deletefile(fileDTOs);
			UpdateNameFile();
			UpdateAllRealnameFile();
		}
	
		
		
			private String Rename(fileDTO fileDTOs,String filenameoriginal,String newfilename2,int i) {
				
				upload_pn upload_pns =fileDTOs.getUpload_pn();
				user users =  fileDTOs.getUser();
				String  filename = null;
			
				
				switch(i) {
				    case 1:
				     filename = upload_pns.getUpload_pn_name();
				        break;
				    case 2:
				        filename = upload_pns.getUpload_pn_name2();
				        break;
				    case 3:
				        filename = upload_pns.getUpload_pn_name3();
				        break;
				    case 4:
				        filename = upload_pns.getUpload_pn_name4();
				        break;
				    case 5:
				        filename = upload_pns.getUpload_pn_name5();
				        break;
				    default:
				        break;
				}
				
				String  newfilename;
				switch(upload_pns.getUpload_pn_namefile()) {
			    case 1:
			    	newfilename = " โดย "+users.getUser_name();
			        break;
			    case 2:
			    	newfilename = filename+" โดย "+users.getUser_name();
			        break;
			    case 3:
			    	newfilename = filenameoriginal;
			        break;
			    case 4:
			    	newfilename = newfilename2;
			        break;
			    default:
			    	newfilename = filename+" โดย "+users.getUser_name();
			        break;
				}
			
			 	    return newfilename;
			}    
			
			
			private String CheckDateEnd(fileDTO fileDTOs) {
				
				upload_pn upload_pns =fileDTOs.getUpload_pn();
				String dateend;
				LocalDateTime localDate2;
				
				switch(Integer.valueOf(upload_pns.getUpload_pn_end())) {
			    case 1:
			   	     localDate2 = LocalDateTime.now().plusMonths(6);
				   	  dateend = localDate2.format(formatter);
			        break;
			    case 2:
			    	localDate2 = LocalDateTime.now().plusYears(1);
			    	dateend = localDate2.format(formatter);
			        break;
			    case 3:
			    	localDate2 = LocalDateTime.now().plusYears(3); 
		   	    	 dateend = localDate2.format(formatter);
			        break;
			    case 4:
			    	 localDate2 = LocalDateTime.now().plusYears(5); 
			   	     dateend = localDate2.format(formatter);
			        break;
	
			    default:
			    	dateend = null;
			        break;
				}
				
				return dateend;
			}   	  
			     


			private void Deletefile(fileDTO fileDTOs) {
				
				String[] files = {fileDTOs.getOldfile1(),fileDTOs.getOldfile2(),fileDTOs.getOldfile3(),fileDTOs.getOldfile4(),fileDTOs.getOldfile5()};
				
				String oldfilename;
				String Oldfile;
				int i = 0;
				for (String file : files) {
					i++;
					
				    if (file != null ) {


				    	switch(i) {
					    case 1:
					    	oldfilename = fileDTOs.getOldfilename1();
					    	Oldfile = fileDTOs.getOldfile1();
					        break;
					    case 2:
					    	oldfilename = fileDTOs.getOldfilename2();
					    	Oldfile = fileDTOs.getOldfile2();
					        break;
					    case 3:
					    	oldfilename = fileDTOs.getOldfilename3();
					    	Oldfile = fileDTOs.getOldfile3();
					        break;
					    case 4:
					    	oldfilename = fileDTOs.getOldfilename4();
					    	Oldfile = fileDTOs.getOldfile4();
					        break;
					    case 5:
					    	oldfilename = fileDTOs.getOldfilename5();
					    	Oldfile = fileDTOs.getOldfile5();
					        break;	
					    default:
					    	oldfilename = fileDTOs.getOldfilename1();
					    	Oldfile = fileDTOs.getOldfile1();
					        break;
						}
				    	
				    	
				    	  if(oldfilename.equals("del")) {
							  System.out.println("oldfile1" + file);
							   	String paths;
							   	File fileToDelete;
							   	boolean success;
							   	file filesed = FileDAO.getbynamefile(file);
							    ServletContext contexts = fileDTOs.getSession().getServletContext(); 
							     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
							     fileToDelete = new File(paths + File.separator + Oldfile);
							    
							     
							     
							 	folder folders = FolderDAO.getbytype(filesed.getFile_path());
							    UpdateMemorySize(folders,filesed.getFile_size(),0);
							     
							     success = fileToDelete.delete();
							     if(success) {
							    	 FileDAO.deleteforfilename(file);
							     }
							     
						
						  }else {
							  joiner.add(oldfilename);
							  joiner3.add(file);	
						  }
				    	
				    	
				    }
				}
				
			
				
			}
		 	    

		 	   
		public String getLocationName(String folder_type){
				
		    String Pathfulls ="";
			
			  try {
					folder folders = FolderDAO.getbytype(folder_type);
				    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
			  	  
				    
		
				    drives drivess;
				    int drives_id;

				    
				    if(mainfolders != null) {
					    drives_id= mainfolders.getFolder_idrefer();	
					    drivess  = DrivesDAO.getbyId(drives_id);
					    Pathfull = "IDOC:/"+drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
					    Pathfulls ="IDOC:/"+drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
				    }else {
				    	
				    	drives_id = folders.getFolder_idrefer();
				    	drivess  = DrivesDAO.getbyId(drives_id);
				    	Pathfull ="IDOC:/"+drivess.getDrives_name()+"/"+folders.getFolder_name();	
				    	Pathfulls ="IDOC:/"+drivess.getDrives_name()+"/"+folders.getFolder_name();		
				    }

		 	        }catch(Exception  e) {
		 
		 	       }		

				return Pathfulls;
		}
		
		
		public String getLocationPath(String folder_type){
			
		    String Pathfulls ="";
			
			  try {
					folder folders = FolderDAO.getbytype(folder_type);
				    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
			  	  
				    
		
				    drives drivess;
				    int drives_id;

				    
				    if(mainfolders != null) {
					    drives_id= mainfolders.getFolder_idrefer();	
					    drivess  = DrivesDAO.getbyId(drives_id);
					    Pathfull = "/"+drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
					    Pathfulls =drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
				    }else {
				    	
				    	drives_id = folders.getFolder_idrefer();
				    	drivess  = DrivesDAO.getbyId(drives_id);
				    	Pathfull ="/"+drivess.getDrives_drive()+"/"+folders.getFolder_type();	
				    	Pathfulls= drivess.getDrives_drive()+"/"+folders.getFolder_type();		
				    }

		 	        }catch(Exception  e) {
		 
		 	       }		

				return Pathfulls;
		}
	
		public boolean CheckMemorySize(fileDTO fileDTOs){
			
			CommonsMultipartFile file1 = fileDTOs.getFile1();
			CommonsMultipartFile file2 = fileDTOs.getFile2();
			CommonsMultipartFile file3 = fileDTOs.getFile3();
			CommonsMultipartFile file4 = fileDTOs.getFile4();
			CommonsMultipartFile file5 = fileDTOs.getFile5();
			long imageFileSize =0;
			folder folders = fileDTOs.getFolders();
			
			
				CommonsMultipartFile[] files = {file1, file2, file3, file4, file5};
	
				for (CommonsMultipartFile file : files) {
				    if (file != null && file.getSize() > 0) {
				        imageFileSize += file.getSize();
				    }
				}
					
					
			  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());

			  	    drives_id = folders.getFolder_idrefer();
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
		
		
			public boolean CheckMemorySize(folder folders,long imageFileSize ){
			
			  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());

			  	    drives_id = folders.getFolder_idrefer();
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
		
		
		
		
		
		private void setuse(fileDTO fileDTOs) {
			CommonsMultipartFile file1 = fileDTOs.getFile1();
			CommonsMultipartFile file2 = fileDTOs.getFile2();
			CommonsMultipartFile file3 = fileDTOs.getFile3();
			CommonsMultipartFile file4 = fileDTOs.getFile4();
			CommonsMultipartFile file5 = fileDTOs.getFile5();
			long imageFileSize =0;
			folder folders = fileDTOs.getFolders();
		
			
				CommonsMultipartFile[] files = {file1, file2, file3, file4, file5};
	
				for (CommonsMultipartFile file : files) {
				    if (file != null && file.getSize() > 0) {
				        imageFileSize += file.getSize();
				    }
				}
				UpdateMemorySize(folders,imageFileSize,1);
		}
		
		
	public void UpdateMemorySize(folder folders,long Size,int type){
			
		 long imageFileSize = folders.getFolder_use();
		 
		 if(type == 1) {
			 imageFileSize = imageFileSize + Size;
		 }else {
			 imageFileSize = imageFileSize - Size; 
		 }
		 
		 
		 String Sizes = Fileservice.printFileSize(imageFileSize);
		 FolderDAO.setuse(folders.getFolder_id(),imageFileSize,Sizes);
		 UpdateMemoryDrivesSize();
		} 
		


		public void updateFolderUsage(folder folder) {
		    if (folder == null) return;
	
		    file sum = FileDAO.getsum(folder.getFolder_type());
		    long size = sum.getFile_size();
		    String readableSize = Fileservice.printFileSize(size);
	
		    FolderDAO.setuse(folder.getFolder_id(), size, readableSize);
		}


	public void UpdateMemoryDrivesSize(){
		List<drives> drivesed = DrivesDAO.getall();    
		
	
		
	
	
		drivesed.stream().forEach(row->{
			
			
					long imageFileSize = 0;
					folder sum2 = FolderDAO.getsum(row.getDrives_id());
					imageFileSize = imageFileSize+ sum2.getFolder_use();
					
					List<folder> subfoder = FolderDAO.getall(row.getDrives_id());
					
					for(folder data : subfoder) {
						folder sums = FolderDAO.getsum(data.getFolder_id());
						imageFileSize = imageFileSize+ sums.getFolder_use();
					}
					
		
				    double percent;
				    if(imageFileSize > 0) {
				       percent =  imageFileSize * 100 / row.getDrives_size();
				    }else {
				    	 percent =0;	
				    }
				    
					
				    long remain =row.getDrives_size() - imageFileSize;
				    
				    
 				    String Size = Fileservice.printFileSize(imageFileSize);
 				    String Size2 = Fileservice.printFileSize(remain);
				    DrivesDAO.setuse(row.getDrives_id(),imageFileSize,Size,remain,Size2,percent);
					
				});
		
	
		} 
		
		
	
	
		public void Register_main(Register_main register_main, int flow_id, int eformId) {
			Register_main_product(register_main,flow_id,eformId); 
	    	Register_main_production_directive(register_main,flow_id,eformId);  
	    	Register_main_materiale(register_main,flow_id,eformId);  
	    	Register_main_customer(register_main,flow_id,eformId);  
	    	Register_main_part(register_main,flow_id,eformId);  
		}	
		
		
		
		private void Register_main_product(Register_main register_main, int flow_id, int eformId) {

			 if(register_main.getProduct() != null  &&   register_main.getAction_product().equals("register")  ) {
			    	control_steps control_steps  =new control_steps();
					control_steps.setStep(flow_id);
					control_steps.setEdata_id(eformId);
				 	conditionDAO.delControl_steps(eformId,"product");
				 	conditionDAO.setControl_steps(control_steps,"product");
			 }
			 
		}			 
			 
		
		private void Register_main_production_directive(Register_main register_main,int flow_id, int eformId) {	 
			
			
			 if(register_main.getProduction_directive() != null && ( register_main.getAction_production_directive().equals("register") ||  register_main.getAction_batch().equals("register") )) {
				 
				 conditionDAO.delControl_steps(eformId,"production_directive");
				 
				 
				 int product_id =0;
				 control_steps products =  conditionDAO.getProduct(register_main.getProduct());
				 
				 if(products != null) {
				 product_id = products.getId();
				 }
				 
			     control_steps control_steps  =new control_steps();
			     control_steps.setStep(flow_id);
				 control_steps.setEdata_id(eformId);
				 control_steps.setProduct_id(product_id);
				 conditionDAO.setControl_steps(control_steps,"order_management");

			 	 System.out.println(" eformId " +eformId);
		    }
			 
		   } 
			 
			 
			 
				private void Register_main_materiale(Register_main register_main,int flow_id, int eformId) {	  
						 if(register_main.getMaterial() != null  && register_main.getAction_material().equals("register")) {
							 
							 conditionDAO.delControl_steps(eformId,"material");
							 
							 
						     control_steps control_steps  =new control_steps();
						     control_steps.setStep(flow_id);
							 control_steps.setEdata_id(eformId);
							 conditionDAO.setControl_steps(control_steps,"material");
			
						 	 System.out.println(" eformId " +eformId);
					    }
				   } 
			 
			 
			private void Register_main_customer(Register_main register_main,int flow_id, int eformId) {		 
					 if(register_main.getCustomer() != null  && register_main.getAction_customer().equals("register")) {
						 
						 conditionDAO.delControl_steps(eformId,"customer");
						 
					     control_steps control_steps  =new control_steps();
					     control_steps.setStep(flow_id);
						 control_steps.setEdata_id(eformId);
						 conditionDAO.setControl_steps(control_steps,"customer");
		
					 	 System.out.println(" eformId " +eformId);
				    }
			 }		 
					 
			
			
			private void Register_main_part(Register_main register_main,int flow_id, int eformId) {	 
					 if(register_main.getPart() != null  && register_main.getAction_customer().equals("part")) {
						 
						 conditionDAO.delControl_steps(eformId,"part");
						 
					     control_steps control_steps  =new control_steps();
					     control_steps.setStep(flow_id);
						 control_steps.setEdata_id(eformId);
						 conditionDAO.setControl_steps(control_steps,"part");
		
					 	 System.out.println(" eformId " +eformId);
				    } 
			}
		
		
		
		
		

		
		
		
			
		public void LoadApphasChanges(LoadApp LoadApp) {
			//PlatformService.LoadApphasChanges(LoadApp); 
		}
		public void LoadApp(LoadApp LoadApp) {
			//PlatformService.LoadApp(LoadApp); 	   
		}
		public void FlowApp(LoadApp LoadApp) {
			//PlatformService.FlowApp(LoadApp); 	
		}
		public void LoadAppViewMode(LoadApp LoadApp) {
			//PlatformService.LoadSubData(LoadApp); 	    	
		}
		public void LoadSubData(LoadApp LoadApp) {
			//PlatformService.LoadSubData(LoadApp); 	    
		}
		public void SaveApp(LoadApp LoadApp) {
		//	PlatformService.SaveApp(LoadApp); 	
		}
		public void ApproveApp(LoadApp LoadApp) {
			//PlatformService.ApproveApp(LoadApp); 
		}
	



	

		public void getRoot(ModelMap model) {
			
			
			
			
			  List<drives> drivess = DrivesDAO.getall();
			  List<folder> folders = FolderDAO.getall2();	
	
			  List<String> Permission = Userservice.getLoggedUserPermissionNames();
			  
			  //getbyAuth
			  drivess.removeIf(drive -> {
				    String permissionToCheck = "READ-" + drive.getDrives_drive();
				    boolean hasPermission = Permission.contains(permissionToCheck);
				    
				    if (hasPermission) {
				        // ถ้ามี permission ที่ตรงกัน
				        List<folder> mainfolders = folders.stream()
				            .filter(folder -> folder.getFolder_idrefer() == drive.getDrives_id())
				            .collect(Collectors.toList());
				        drive.setFolder(mainfolders);

				        mainfolders.removeIf(mainfolder -> {
				        	
				        	String   permissionToCheck2 = "SET-" + mainfolder.getFolder_type();
				        	boolean  hasPermission2 = Permission.contains(permissionToCheck2);
						   if (hasPermission2) {
							   
							   List<folder> subfolders = folders.stream()
						                .filter(folder -> folder.getFolder_idrefer() == mainfolder.getFolder_id())
						                .collect(Collectors.toList());

							   subfolders.removeIf(subfolder -> {  
									String   permissionToCheck3 = "SET-" + subfolder.getFolder_type();
						        	boolean  hasPermission3 = Permission.contains(permissionToCheck3);
								   if (hasPermission3) {
							           mainfolder.setFolder(subfolders);
									   return false;  // ไม่ลบ drive นี้  
								   }else {
									   return true;  // ลบ drive นี้
								   }
								   
							   });         
							   return false;  // ไม่ลบ drive นี้
						   } else {
						        return true;  // ลบ drive นี้
						    }
				        	
				 
				        });
				        
				        return false;  // ไม่ลบ drive นี้
				    } else {
				        return true;  // ลบ drive นี้
				    }
				});
			  
			/*  
			  for(String name : Permission) {
				System.out.println("name ->"+name);  
			  }
			  */
			  
			  

			  model.addAttribute("drivess",drivess); 
			 
			  
		}
		
		
		public void getAppProperty(workflow workflows,int sub_processesid,ModelMap model) {
					
			
			             Myapp app =  EdataDAO.getMyapp("Production");
						
			             System.err.println("db->"+workflows.getWorkflow_maindb());
			             
			             
			             if(app != null) {
			            	 
			            	 System.err.println("app->");
			            	 
			              	 String type1 = SafeSplitter.split(app.getEnum_type(),",").get(0);
			            	 String type2 = SafeSplitter.split(app.getEnum_type(),",").get(1);
			            	 String type3 = SafeSplitter.split(app.getEnum_type(),",").get(2);
			            	 String table1 =  SafeSplitter.split(app.getRegister(),",").get(0);
			            	 String table2 =  SafeSplitter.split(app.getRegister(),",").get(1);
			            	 String table3 =  SafeSplitter.split(app.getRegister(),",").get(2);
			            	 
				             dataset app_dataset = DatasetDAO.getMain(type1);
							 List<dataset_data> app_dataset_data = Dataset_dataDAO.getall(app_dataset.getDataset_id());
							 app.setDataset_data(app_dataset_data);
							 app.setTables(table1);
							 System.err.println("app->2");
							 System.err.println("table2 "+table2);
							 
							 
							 if(table2 != "") {
								      dataset app_dataset2 = DatasetDAO.getMain(type2);
									 List<dataset_data> app_dataset_data2 = Dataset_dataDAO.getall(app_dataset2.getDataset_id());
									 app.setDataset_data2(app_dataset_data2);
									 app.setTables2(table2);
							 }
							 if(table3 != "") {
							      dataset app_dataset3 = DatasetDAO.getMain(type3);
								 List<dataset_data> app_dataset_data3 = Dataset_dataDAO.getall(app_dataset3.getDataset_id());
								 app.setDataset_data3(app_dataset_data3);
								 app.setTables3(table3);
						 } 
			             }
						 
			             Myapp subapp =  EdataDAO.getMyapp(workflows.getWorkflow_subdb());
			             
			             if(subapp != null) {
			            	 System.err.println("app->subapp");
			            	 
			            	 
			              	 String type1 = SafeSplitter.split(subapp.getEnum_type(),",").get(0);
			            	 String type2 = SafeSplitter.split(subapp.getEnum_type(),",").get(1);
			            	 
			            	 String table1 =  SafeSplitter.split(subapp.getRegister(),",").get(0);
			            	 String table2 =  SafeSplitter.split(subapp.getRegister(),",").get(1);
			            	 
			            	
			            	 
			              	 System.err.println("app->subapp");
			            	 
				             dataset app_dataset = DatasetDAO.getMain(type1);
							 List<dataset_data> app_dataset_data = Dataset_dataDAO.getall(app_dataset.getDataset_id());
							 subapp.setDataset_data(app_dataset_data);
							 subapp.setTables(table1);
							 
							 
							 
							 
							 if(table2 != "") {
								 dataset app_dataset2 = DatasetDAO.getMain(type2);
								 List<dataset_data> app_dataset_data2 = Dataset_dataDAO.getall(app_dataset2.getDataset_id());
								 subapp.setDataset_data2(app_dataset_data2);
								 subapp.setTables2(table2);
							 }
							 
							 
							 
			             }
						 

			             
			             	Myapp subapp2 =  EdataDAO.getMyapp(workflows.getWorkflow_subdb2());
			             
			                if(subapp2 != null) {
				            	 
			                 	 
				              	 String type1 = SafeSplitter.split(subapp2.getEnum_type(),",").get(0);
				            	 String type2 = SafeSplitter.split(subapp2.getEnum_type(),",").get(1);
				            	 
				            	 String table1 =  SafeSplitter.split(subapp2.getRegister(),",").get(0);
				            	 String table2 =  SafeSplitter.split(subapp2.getRegister(),",").get(1);
				            	 
				            	 
				            	 
				              	 System.err.println("app->subapp");
				            	 
					             dataset app_dataset = DatasetDAO.getMain(type1);
								 List<dataset_data> app_dataset_data = Dataset_dataDAO.getall(app_dataset.getDataset_id());
								 subapp2.setDataset_data(app_dataset_data);
								 subapp2.setTables(table1);
								 
								 if(table2 != null) {
									 dataset app_dataset2 = DatasetDAO.getMain(type2);
									 List<dataset_data> app_dataset_data2 = Dataset_dataDAO.getall(app_dataset2.getDataset_id());
									 subapp2.setDataset_data2(app_dataset_data2);
									 subapp2.setTables2(table2);
								 } 
				           }
			             
			             
			        	 model.addAttribute("app",app); 
			        	 model.addAttribute("subapp",subapp); 
			        	 model.addAttribute("subapp2",subapp2); 
			        	 
			             
						    sub_processes sub_processes =  EdataDAO.getSubProcess(sub_processesid);
							 model.addAttribute("sub_processes",sub_processes); 
							
					
						 
						 if(app != null) { 
							 
							 System.err.println("app->subapp->registerkeys");
							 
							 List<registerkey> registerkeys =  EdataDAO.getAllRegisterkey(app.getId(),"register"); 
							 List<registerkey> actionkeys =  EdataDAO.getAllRegisterkey(app.getId(),"action"); 
							 List<registerkey> clearkeys =  EdataDAO.getAllRegisterkey(app.getId(),"clear"); 
							 
							 
							 
							 System.err.println("app->subapp->registerkeys->Keyid = "+sub_processes.getId()); 
							 
							 List<keymap> keymap = EdataDAO.getAllKeyMap(sub_processes.getId());
						
							 /*
							 System.err.println("app->subapp->registerkeys->KeySize = "+keymap.size()); 
							 System.err.println("app->subapp->registerkeys->num"+registerkeys.size());
							 System.err.println("app->subapp->registerkeys->keymap->num"+keymap.size());
							 */
							 
							 
							 registerkeys.stream().forEach(row->{
								 dataset dataset = DatasetDAO.getMain(row.getSource());
								if(dataset != null) {
									 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
									 Optional<keymap> data =  keymap.stream().filter(sub-> sub.getMainkey().equals(row.getMainkey())).findFirst();
									if(data.isPresent()) {
										 keymap  keymaps = data.get();
										 row.setKeymap(keymaps);
									}	 
									  row.setTag(dataset_data);
									  row.setEnum_id(dataset.getDataset_id());
								}
							
							 });
							 
							 
							 
								try { 	
									String[] commitment = app.getCommitment().split(",");
									List<commitment> commitments = new ArrayList<commitment>();
									for(String  datas: commitment) {
										
										commitment commit = new commitment();
										
										List<registerkey> key = actionkeys.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
										key.forEach(row->{
											 dataset dataset = DatasetDAO.getMain(row.getSource());
											if(dataset != null) {
												 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
												 Optional<keymap> data =  keymap.stream()
														 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
														 .filter(sub-> sub.getType().equals("create"))
														 .findFirst();
												if(data.isPresent()) {
													 keymap  keymaps = data.get();
													 row.setKeymap(keymaps);
												}	 
												  row.setTag(dataset_data);
												  row.setEnum_id(dataset.getDataset_id());
											}
										
										 });
										 
										 commit.setName(datas);
										 commit.setActionkeys(key);
										 commitments.add(commit);
									}
		
									 model.addAttribute("commitment",app.getCommitment()); 
									 model.addAttribute("commitments",commitments); 
									}catch(Exception e) {
								
							} 
							 
							 
							 
							
							
							
							try { 
								String[] clear = app.getClear().split(",");
								List<commitment> clears = new ArrayList<commitment>();
								for(String  datas: clear) {
									
									commitment commit = new commitment();
									
									List<registerkey> key = clearkeys.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
									key.forEach(row->{
										 Optional<keymap> data =  keymap.stream()
												 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
												 .filter(sub-> sub.getType().equals("clear"))
												 .findFirst();
											if(data.isPresent()) {
												 keymap  keymaps = data.get();
												 row.setKeymap(keymaps);
											}	
										
											
										
										 dataset dataset = DatasetDAO.getMain(row.getSource());
										if(dataset != null) {
											 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
											 
											  row.setTag(dataset_data);
											  row.setEnum_id(dataset.getDataset_id());
										}
									
									 });
									 
									 commit.setName(datas);
									 commit.setActionkeys(key);
									 clears.add(commit);
								}
	
								
							
								 model.addAttribute("clear",app.getClear()); 
								 model.addAttribute("clears",clears); 
						}catch(Exception e) {
							
						} 
							
							
							
							
							process processes=  EdataDAO.getProcess(sub_processes.getProcesse_id());
						  	 model.addAttribute("processes",processes); 
							
							
							reference(app,model,keymap);
							clear(app,model,keymap);
							create(app,model,keymap);
							
							
							
							
							
							
							 
							 model.addAttribute("registerkeys",registerkeys); 
							 model.addAttribute("actionkeys",actionkeys); 
			
						
							 condition conditions = conditionDAO.getbyid(processes.getId_condition());
							 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(), conditions.getCondition_rv());
							  model.addAttribute("eformfields",eformfields); 
							  
							  
							  
						 }
			 }
		
	
		  private void create(Myapp app, ModelMap model, List<keymap> keymap) {
		    	 List<registerkey> processing =  EdataDAO.getAllRegisterkey(app.getId(),"clear_register"); 
		    	 Register_processing(processing,app,model,keymap);
		    	 
		    	 List<registerkey> registers =  EdataDAO.getAllRegisterkey(app.getId(),"register"); 
		    	 Register(registers,app,model,keymap);
		    	 
		    	 
		    	 List<registerkey> document_references =  EdataDAO.getAllRegisterkey(app.getId(),"document_reference"); 
		    	 Document_reference(document_references,app,model,keymap);
		     }
		
		 
		  private void Document_reference(List<registerkey> document_references, Myapp app, ModelMap model,
				List<keymap> keymap) {
				try { 
					String[] dreference = app.getDocument_reference().split(",");
					List<commitment> Dreferences = new ArrayList<commitment>();
					for(String  datas: dreference) {
						
						commitment commit = new commitment();
						
						List<registerkey> key = document_references.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
						key.forEach(row->{
							//System.out.println(" key-> "+row.getAction_type());
							
							 Optional<keymap> data =  keymap.stream()
									 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
									 .filter(sub-> sub.getType().equals("create"))
									 .findFirst();
								if(data.isPresent()) {
								
									
									 keymap  keymaps = data.get();
									 row.setKeymap(keymaps);
									System.out.print(" key->data "+keymaps.getEformkey());
								}	 
							
							 dataset dataset = DatasetDAO.getMain(row.getSource());
							if(dataset != null) {
								 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
								  row.setTag(dataset_data);
								  row.setEnum_id(dataset.getDataset_id());
							}
						
						 });
						 
						 commit.setName(datas);
						 commit.setActionkeys(key);
						 Dreferences.add(commit);
					}

					
				
					 model.addAttribute("Dreference", app.getDocument_reference()); 
					 model.addAttribute("Dreferences",Dreferences); 
			}catch(Exception e) {
				
			} 
			
		}



		private void Register(List<registerkey> registers, Myapp app, ModelMap model, List<keymap> keymap) {
				try { 
					String[] register = app.getRegister().split(",");
					List<commitment> Registers = new ArrayList<commitment>();
					
				  
					   
					for(String  datas: register) {
						
						commitment commit = new commitment();
						
						List<registerkey> key = registers.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
						

						
						
						key.forEach(row->{
							//System.out.println(" key-> "+row.getAction_type());
							
							 Optional<keymap> data =  keymap.stream()
									 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
									 .filter(sub-> sub.getType().equals("create"))
									 .findFirst();
								if(data.isPresent()) {
								
									
									 keymap  keymaps = data.get();
									 row.setKeymap(keymaps);
									System.out.print(" key->data "+keymaps.getEformkey());
								}	 
							
							 dataset dataset = DatasetDAO.getMain(row.getSource());
							if(dataset != null) {
								 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
								  row.setTag(dataset_data);
								  row.setEnum_id(dataset.getDataset_id());
							}
						
						 });
						 System.out.println(" commit  Name "+datas);
						 System.out.println(" commit Actionkeys "+key);
						 commit.setName(datas);
						 commit.setActionkeys(key);
						 Registers.add(commit);
					}

					
				
					 model.addAttribute("Register", app.getRegister()); 
					 model.addAttribute("Registers",Registers); 
					 model.addAttribute("registerkey_id",registers.get(0).getId());
			}catch(Exception e) {
				 System.out.println(e);
			}  
				
			
		}



		private void clear(Myapp app, ModelMap model, List<keymap> keymap) {
		    	 List<registerkey> clear_registers =  EdataDAO.getAllRegisterkey(app.getId(),"clear_register"); 
		    	 Clear_registers(clear_registers,app,model,keymap);
		    	 
		     }
		
		
		     private void reference(Myapp app, ModelMap model, List<keymap> keymap) {
		    	
		    			 
		    	 List<registerkey> reference_modify =  EdataDAO.getAllRegisterkey(app.getId(),"reference_modify"); 
		    	 List<registerkey> reference_continues =  EdataDAO.getAllRegisterkey(app.getId(),"reference_continue"); 
		    	 List<registerkey> reference_onlys =  EdataDAO.getAllRegisterkey(app.getId(),"reference_only"); 
		    	 List<registerkey> hierarchical_references =  EdataDAO.getAllRegisterkey(app.getId(),"hierarchical_reference"); 
		    	 
		    	 Reference_modify(reference_modify,app,model,keymap);
		    	 Reference_continue(reference_continues,app,model,keymap);
		    	 Reference_only(reference_onlys,app,model,keymap);
		    	 Hierarchical_only(hierarchical_references,app,model,keymap);
		     }


		     private void Hierarchical_only(List<registerkey> hierarchical_references, Myapp app, ModelMap model,
					List<keymap> keymap) {

				    	 try { 
								String[] hierarchical_only = app.getHierarchical_reference().split(",");
								List<commitment> Hierarchical_onlys = new ArrayList<commitment>();
								for(String  datas: hierarchical_only) {
									
									commitment commit = new commitment();
									
									List<registerkey> key = hierarchical_references.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
									key.forEach(row->{
										
										 Optional<keymap> data =  keymap.stream()
												 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
												 .filter(sub-> sub.getType().equals("hierarchical_reference"))
												 .findFirst();
											if(data.isPresent()) {
												 keymap  keymaps = data.get();
												 if(keymaps.getFilter_enum() == null) { keymaps.setFilter_enum("-");}
												 row.setKeymap(keymaps);
											}	
										
										 dataset dataset = DatasetDAO.getMain(row.getSource());
										if(dataset != null) {
											 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
											 
											  row.setTag(dataset_data);
											  row.setEnum_id(dataset.getDataset_id());
										}
									
									 });
									 
									 commit.setName(datas);
									 commit.setActionkeys(key);
									 Hierarchical_onlys.add(commit);
								}

								
							
								 model.addAttribute("Hierarchical", app.getHierarchical_reference()); 
								 model.addAttribute("Hierarchicals",Hierarchical_onlys); 
						}catch(Exception e) {
							
						} 
				
			}



			private void Reference_only(List<registerkey> reference_onlys, Myapp app, ModelMap model,
					List<keymap> keymap) {
			    	 try { 
							String[] reference_only = app.getReference_continue().split(",");
							List<commitment> Ronlys = new ArrayList<commitment>();
							for(String  datas: reference_only) {
								
								commitment commit = new commitment();
								
								List<registerkey> key = reference_onlys.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
								key.forEach(row->{
									
									 Optional<keymap> data =  keymap.stream()
											 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
											 .filter(sub-> sub.getType().equals("reference_only"))
											 .findFirst();
										if(data.isPresent()) {
											 keymap  keymaps = data.get();
											 if(keymaps.getFilter_enum() == null) { keymaps.setFilter_enum("-");}

											 row.setKeymap(keymaps);
										}	
									
									 dataset dataset = DatasetDAO.getMain(row.getSource());
									if(dataset != null) {
										 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
										 
										  row.setTag(dataset_data);
										  row.setEnum_id(dataset.getDataset_id());
									}
								
								 });
								 
								 commit.setName(datas);
								 commit.setActionkeys(key);
								 Ronlys.add(commit);
							}

							
						
							 model.addAttribute("Ronly", app.getReference_continue()); 
							 model.addAttribute("Ronlys",Ronlys); 
					}catch(Exception e) {
						
					} 
				
			}



			private void Register_processing(List<registerkey> processings, Myapp app, ModelMap model, List<keymap> keymap) {
					
					try { 
						String[] clear_register = app.getClear_register().split(",");
						List<commitment> Clear_registers = new ArrayList<commitment>();
						for(String  datas: clear_register) {
							
							commitment commit = new commitment();
							
							List<registerkey> key = processings.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
							key.forEach(row->{
								//System.out.println(" key-> "+row.getAction_type());
								
								 Optional<keymap> data =  keymap.stream()
										 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
										 .filter(sub-> sub.getType().equals("create"))
										 .findFirst();
									if(data.isPresent()) {
									
										
										 keymap  keymaps = data.get();
										 if(keymaps.getFilter_enum() == null) { keymaps.setFilter_enum("-");}
										 row.setKeymap(keymaps);
										
										 
										 
										System.out.print(" key->data "+keymaps.getEformkey());
									}	 
								
								 dataset dataset = DatasetDAO.getMain(row.getSource());
								if(dataset != null) {
									 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
									  row.setTag(dataset_data);
									  row.setEnum_id(dataset.getDataset_id());
								}
							
							 });
							 
							 commit.setName(datas);
							 commit.setActionkeys(key);
							 Clear_registers.add(commit);
						}

						
					
						 model.addAttribute("Register_Processing", app.getClear_register()); 
						 model.addAttribute("Register_Processings",Clear_registers); 
				}catch(Exception e) {
					
				} 
					
					
				}
			     
		
		     
		     
		     private void Reference_continue(List<registerkey> reference_continues, Myapp app, ModelMap model,
					List<keymap> keymap) {
		    	 try { 
						String[] reference_continue = app.getReference_continue().split(",");
						List<commitment> Rcontinues = new ArrayList<commitment>();
						for(String  datas: reference_continue) {
							
							commitment commit = new commitment();
							
							List<registerkey> key = reference_continues.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
							key.forEach(row->{
								
								 Optional<keymap> data =  keymap.stream()
										 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
										 .filter(sub-> sub.getType().equals("reference_continue"))
										 .findFirst();
									if(data.isPresent()) {
										 keymap  keymaps = data.get();
										 if(keymaps.getFilter_enum() == null) { keymaps.setFilter_enum("-");}
										 row.setKeymap(keymaps);
									}	
								
								 dataset dataset = DatasetDAO.getMain(row.getSource());
								if(dataset != null) {
									 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
									 
									  row.setTag(dataset_data);
									  row.setEnum_id(dataset.getDataset_id());
								}
							
							 });
							 
							 commit.setName(datas);
							 commit.setActionkeys(key);
							 Rcontinues.add(commit);
						}

						
					
						 model.addAttribute("Rcontinue", app.getReference_continue()); 
						 model.addAttribute("Rcontinues",Rcontinues); 
				}catch(Exception e) {
					
				} 
					
				
			}


		     
		     
		     

			private void Reference_modify(List<registerkey> reference_modifys, Myapp app, ModelMap model, List<keymap> keymap) {
				
				try { 
					String[] reference_modify = app.getReference_modify().split(",");
					List<commitment> Rmodifys = new ArrayList<commitment>();
					for(String  datas: reference_modify) {
						
						commitment commit = new commitment();
						
						List<registerkey> key = reference_modifys.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
						key.forEach(row->{
							//System.out.println(" key-> "+row.getAction_type());
							
							 Optional<keymap> data =  keymap.stream()
									 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
									 .filter(sub-> sub.getType().equals("reference_modify"))
									 .findFirst();
								if(data.isPresent()) {
								
									
									 keymap  keymaps = data.get();
									 if(keymaps.getFilter_enum() == null) { keymaps.setFilter_enum("-");}
									 row.setKeymap(keymaps);
									 
									System.out.print(" key->data "+keymaps.getEformkey());
								}	 
							
							 dataset dataset = DatasetDAO.getMain(row.getSource());
							if(dataset != null) {
								 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
								  row.setTag(dataset_data);
								  row.setEnum_id(dataset.getDataset_id());
							}
						
						 });
						 
						 commit.setName(datas);
						 commit.setActionkeys(key);
						 Rmodifys.add(commit);
					}

					
				
					 model.addAttribute("Rmodify", app.getReference_modify()); 
					 model.addAttribute("Rmodifys",Rmodifys); 
			}catch(Exception e) {
				
			} 
				
				
			}
		     
			
			
	private void Clear_registers(List<registerkey> clear_registers, Myapp app, ModelMap model, List<keymap> keymap) {
				
				try { 
					String[] clear_register = app.getClear_register().split(",");
					List<commitment> Clear_registers = new ArrayList<commitment>();
					for(String  datas: clear_register) {
						
						commitment commit = new commitment();
						
						List<registerkey> key = clear_registers.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
						key.forEach(row->{
							//System.out.println(" key-> "+row.getAction_type());
							
							 Optional<keymap> data =  keymap.stream()
									 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
									 .filter(sub-> sub.getType().equals("clear_register"))
									 .findFirst();
								if(data.isPresent()) {
								
									
									 keymap  keymaps = data.get();
									 if(keymaps.getFilter_enum() == null) { keymaps.setFilter_enum("-");}
									 row.setKeymap(keymaps);
									System.out.print(" key->data "+keymaps.getEformkey());
								}	 
							
							 dataset dataset = DatasetDAO.getMain(row.getSource());
							if(dataset != null) {
								 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
								  row.setTag(dataset_data);
								  row.setEnum_id(dataset.getDataset_id());
							}
						
						 });
						 
						 commit.setName(datas);
						 commit.setActionkeys(key);
						 Clear_registers.add(commit);
					}

					
				
					 model.addAttribute("Clear_register", app.getClear_register()); 
					 model.addAttribute("Clear_registers",Clear_registers); 
			}catch(Exception e) {
				
			} 
				
				
			}
		     
		

			public void LoadProduct_master(ModelMap model,int id_refer,String allowed_id,String filter, search_workflow_db search_workflow_dbs) {
		
				try {	
				process process = EdataDAO.getProcess(id_refer);
				List<product_log> register_logs = EdataDAO.getAllProduct_log();

				String register_category = register_logs.stream()
					    .map(product_log::getProduct_category).distinct() // ดึงค่า register_type
					    .collect(Collectors.joining(",")); 
		
				
				
				
				if ( Otherservice.StringValueNull(filter) != null ) { 
					
					
					System.out.println(" filter Not null " );
					
					
							Set<String> validTypes = Arrays.stream(register_category.split(","))
		                               .map(String::trim)
		                               .collect(Collectors.toSet());
	
							String filtered = Arrays.stream(filter.split(","))
		                        .map(String::trim)
		                        .filter(validTypes::contains)
		                        .collect(Collectors.joining(","));
							
							register_category = filtered;
					
							
					
							
					}
				
				int limit = 20;
                
			     if(search_workflow_dbs != null) {
			    	 
			    		System.out.println(" search_workflow_dbs Not null " );
			    	 
			    	 
			    	 limit = 1000000;
			    	 
			 		List<product_master> item =  EdataDAO.getAllProduct(register_category,limit);

	
			
						String type = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_type());
						String name = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_name().trim());
						
						
						System.out.println(" item "+item.size() );
						
					
						
						
						
						Predicate<product_master> baseFilter = row -> type.equals("-") || type.equals(row.getProduct_category());

						Predicate<product_master> strongMatch = row -> name.equals("-") ||
						        Otherservice.calculateSimilarity(row.getProduct_names(), name) >= 80 ||
						        name.equals(row.getProduct_code() + row.getProduct_daterun() + row.getProduct_id());

						Predicate<product_master> weakMatch = row -> name.equals("-") ||
						        Otherservice.calculateSimilarity(row.getProduct_names(), name) > 25 ||
						        name.equals(row.getProduct_code() + row.getProduct_daterun() + row.getProduct_id());

						List<product_master> items = item.stream()
						        .filter(baseFilter.and(strongMatch))
						        .limit(20)
						        .collect(Collectors.toList());

						if (items.isEmpty()) {
						    items = item.stream()
						            .filter(baseFilter.and(weakMatch))
						            .limit(20)
						            .collect(Collectors.toList());
						}
						
						
						
						
					
						model.addAttribute("item",items); 
			     }else {
			    	 System.out.println(" search_workflow_dbs  null " );

			 		List<product_master> item =  EdataDAO.getAllProduct(register_category,limit);
					model.addAttribute("item",item); 
			     }

				
				
				
				
			
		
					model.addAttribute("register_category",register_category); 
		
				}catch(Exception e) {}
		 }
			
			
			public void LoadBom(ModelMap model,int id_refer, search_workflow_db search_workflow_dbs) {
				
				try {	

				
	
					int limit = 20;
                    
				     if(search_workflow_dbs != null) {
				    	 
				    		System.out.println(" search_workflow_dbs Not null " );
				    	 
				    	 
				    	 limit = 1000000;
				    	 
				    		List<bom> item =  EdataDAO.getAllBom(limit);
							String name = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_name());
							
							
							List<bom> items =	item.stream()
							 .filter(row -> name.equals("-") 
									 || Otherservice.calculateSimilarity(row.getBom_name(), name) > 25
									 || Otherservice.calculateSimilarity(row.getBom_product_name(), name) > 25
									 )
							 .limit(20).collect(Collectors.toList());
							model.addAttribute("item",items); 
				     }else {
				    	 System.out.println(" search_workflow_dbs  null " );

				    		List<bom> item =  EdataDAO.getAllBom(limit);
				    	 model.addAttribute("item",item); 
				     }


					
					
				}catch(Exception e) {}
		 }
			
			
			
			
		public void LoadPs(ModelMap model,int id_refer, search_workflow_db search_workflow_dbs) {
	
					
					int limit = 20;
                    
				     if(search_workflow_dbs != null) {
				    	 
				    		System.out.println(" search_workflow_dbs Not null " );
				    	 
				    	 
				    	 limit = 1000000;
				    	 
				    		List<production_standard> item =  EdataDAO.getAllPs(limit);
						    JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();
							String groups = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_groups());
							String type = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_type());
							String name = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_name());
							
							
							List<production_standard> items =	item.stream()
							 .filter(row -> name.equals("-") || similarity.apply(row.getProduct_name(),name) > 0.5)
							 .limit(20).collect(Collectors.toList());
							model.addAttribute("item",items); 
				     }else {
				    	 System.out.println(" search_workflow_dbs  null " );

				    		List<production_standard> item =  EdataDAO.getAllPs(limit);
				    	 model.addAttribute("item",item); 
				     }

					
			 }
				

					public void LoadStock(ModelMap model,int id_refer,String allowed_id) {
			
			    		try {	
			    		process process = EdataDAO.getProcess(id_refer);
						List<stock_log> logs = EdataDAO.getAllStock_log(process.getId_refer(),allowed_id);
						
						
						String type = logs.stream()
							    .map(stock_log::getStock_type).distinct()
							    .collect(Collectors.joining(",")); 
						
						String group = logs.stream()
							    .map(stock_log::getStock_group).distinct() // ดึงค่า register_type
							    .collect(Collectors.joining(",")); 	
						

					    	List<stock> item = EdataDAO.getAllStock(type);
							model.addAttribute("item",item); 
			
							model.addAttribute("type",type); 
							model.addAttribute("group",group); 
							
					
							
			    		}catch(Exception e) {}
			     }
			
			
					public void LoadFG(ModelMap model,int id_refer, search_workflow_db search_workflow_dbs) {
					
							int limit = 20;
		                    
						     if(search_workflow_dbs != null) {
						    	 
						    		System.out.println(" search_workflow_dbs Not null " );
						    	 
						    	 
						    	 limit = 1000000;
						    	 
						    		List<Fg> item =  EdataDAO.getAllFg(limit);
								    JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();
									String groups = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_groups());
									String type = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_type());
									String name = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_name());
									
									
									List<Fg> items =	item.stream()
									 .filter(row -> name.equals("-") || similarity.apply(row.getProduct_name(),name) > 0.5)
									 .limit(20).collect(Collectors.toList());
									model.addAttribute("item",items); 
						     }else {
						    	 System.out.println(" search_workflow_dbs  null " );

						    		List<Fg> item =  EdataDAO.getAllFg(limit);
						    	 model.addAttribute("item",item); 
						     }

							
							
							
			        }
			
					
					
					public void LoadSK(ModelMap model,int id_refer,String allowed_id,String filter, search_workflow_db search_workflow_dbs) {
					
						try {
							process process = EdataDAO.getProcess(id_refer);
							List<stock_log> logs = EdataDAO.getAllStock_log(process.getId_refer(),allowed_id);
							
							String register_type = logs.stream()
								    .map(stock_log::getStock_type).distinct()
								    .collect(Collectors.joining(",")); 
							
							System.out.println("type "+register_type);
							
		
						
							
							
							

							if ( Otherservice.StringValueNull(filter) != null ) { 
												
												
												System.out.println(" filter Not null " );
												
												
														Set<String> validTypes = Arrays.stream(register_type.split(","))
									                               .map(String::trim)
									                               .collect(Collectors.toSet());
								
														String filtered = Arrays.stream(filter.split(","))
									                        .map(String::trim)
									                        .filter(validTypes::contains)
									                        .collect(Collectors.joining(","));
														
														register_type = filtered;
												
												}

							
							
							
							int limit = 20;
							List<stock_lot> stock_lot =  EdataDAO.getAllSk();
							
							
						     if(search_workflow_dbs != null) {
						    	 
						    		System.out.println(" search_workflow_dbs Not null " );
						    	 
						    	 
						    	 limit = 1000000;
						    	 
				
						    	  	List<stock> item =  EdataDAO.getAllStock(register_type,limit);
								    JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

									String name = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_name());
									
									
									List<stock> items =	item.stream()
									 .filter(row -> name.equals("-") || similarity.apply(row.getStock_list(),name) > 0.5)
									 .limit(20).collect(Collectors.toList());
									
									items.stream().forEach(row->{
										
										 String key;
										    if (row.getStock_code() != null) {
										        key = row.getStock_code() + row.getStock_receipt_date() + row.getStock_id();
										    } else {
										        key = row.getStock_receipt_date() + row.getStock_id();
										    }
										
										List<stock_lot> subitems =   stock_lot.stream()
												.filter(sub-> sub.getStock_code().equals(key))
												.collect(Collectors.toList());
										row.setStock_lot(subitems);
									});
									
									
									model.addAttribute("item",items); 
						     }else {
						    	 System.out.println(" search_workflow_dbs  null " );

						    		List<stock> item =  EdataDAO.getAllStock(register_type,limit);
						    	   
						    		item.stream().forEach(row->{
										
										 String key;
										    if (row.getStock_code() != null) {
										        key = row.getStock_code() + row.getStock_receipt_date() + row.getStock_id();
										    } else {
										        key = row.getStock_receipt_date() + row.getStock_id();
										    }
										
										List<stock_lot> subitems =   stock_lot.stream()
												.filter(sub-> sub.getStock_code().equals(key))
												.collect(Collectors.toList());
										row.setStock_lot(subitems);
									});
						    	   
						    	 model.addAttribute("item",item); 
						     }
							
							
							}catch(Exception e) {
								System.err.println(e);
								
							}
		           }
					
					
		
		
			public void LoadRegister(ModelMap model,int id_refer,String allowed_id,String filter, search_workflow_db search_workflow_dbs) {

				
			
				
		    		try {	
		    		process process = EdataDAO.getProcess(id_refer);
					List<register_log> register_logs = EdataDAO.getAllRegister_log(process.getId_refer(),allowed_id);
					
					
					register_logs.forEach(row->{
						System.out.println(" type "+row.getRegister_type() );
						
					});
					System.out.println(" filter r  "+filter );
					System.out.println(" register_logs "+register_logs.size() );
					
					
					String register_type = register_logs.stream()
						    .map(register_log::getRegister_type).distinct() // ดึงค่า register_type
						    .collect(Collectors.joining(",")); 
					
					String register_group = register_logs.stream()
						    .map(register_log::getRegister_group).distinct() // ดึงค่า register_type
						    .collect(Collectors.joining(",")); 	
					
	
					System.out.println(" register_type r  "+register_type );
					
					if ( Otherservice.StringValueNull(filter) != null ) { 
						
						
						System.out.println(" filter Not null " );
						
						
								Set<String> validTypes = Arrays.stream(register_type.split(","))
			                               .map(String::trim)
			                               .collect(Collectors.toSet());
		
								String filtered = Arrays.stream(filter.split(","))
			                        .map(String::trim)
			                        .filter(validTypes::contains)
			                        .collect(Collectors.joining(","));
								
								register_type = filtered;
						
						}
					
                       int limit = 20;
                       
				     if(search_workflow_dbs != null) {
				    	 
				    		System.out.println(" search_workflow_dbs Not null " );
				    	 
				    	 
				    	 limit = 1000000;
				    	 
				    	      List<registers> item =  EdataDAO.getAllRegister(register_type,limit);
						    JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();
							String groups = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_groups());
							String type = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_type());
							String name = Otherservice.DefaultValueString(search_workflow_dbs.getRegister_name());
							
							
							List<registers> items =	item.stream()
							.filter(row-> groups.equals("-") || groups.equals(row.getRegister_groups()) )
							.filter(row-> type.equals("-") || type.equals(row.getRegister_type()) )
							 .filter(row -> name.equals("-") || similarity.apply(row.getRegister_name(),name) > 0.5)
							 .limit(20).collect(Collectors.toList());
							model.addAttribute("item",items); 
				     }else {
				    	 System.out.println(" search_workflow_dbs  null " );

				    	   List<registers> items =  EdataDAO.getAllRegister(register_type,limit);
				    	 model.addAttribute("item",items); 
				     }
				     
				     
						
						
		
						
				    

				        
	
						
						model.addAttribute("register_type",register_type); 
						model.addAttribute("register_group",register_group); 
		    		}catch(Exception e) {}
		     }



			public List<keymap> setKeymap(List<sub_processes> sub_processes) {
				// TODO Auto-generated method stub
				List<keymap>  keymap = null;
				for(int i =0; i < 5; i++) {
					keymap = EdataDAO.getAllKeyMap(sub_processes.get(i).getId());
					if(keymap.size() > 0) {
						i =6;
					}
				}
				return keymap;
			}



			public void Import_Rprocessing(List<doc> docs, int edata_id, List<String> transferredColumn) {
				
				edata edatas = EdataDAO.getbyid(docs.get(0).getDoc_idrefer());
				edata_procurement edata_procurements = EdataDAO.geteData_procurement(docs.get(0).getDoc_idrefer());
				
				if(edata_procurements.getProcurement_supplier_id() != null) {
					
					edata_register_processing register_processing = EdataDAO.geteData_register_processing(edata_id);
					List<doc> docsmain = EdataDAO.getDoc(edata_id);
				
					if(register_processing != null) {
								
							
							if(docsmain.size() > 0) {
								String rv ="";
								if(docsmain.get(0).getDoc_rv() > 0) { rv ="-R"+docsmain.get(0).getDoc_rv();  }
								register_processing.setDoc_refer(docsmain.get(0).getDoc_type()+docs.get(0).getDoc_date()+docsmain.get(0).getDoc_autoid()+rv);
							}
							register_processing.setSupplier_id(edata_procurements.getProcurement_supplier_id());
							register_processing.setSupplier_name(edata_procurements.getProcurement_supplier_name());

							
							EdataDAO.SaveRegister_processing(register_processing);
					}
					
					
				}else {
					
					List<doc> docsmained = EdataDAO.getDoc_in_set(edatas.getReference());
					if(docsmained.size() > 0) {
						
						System.err.println(" docsmained "+docsmained.size());
						System.err.println(" docsmained idrefer "+docsmained.get(0).getDoc_idrefer());
						
						
								edata_procurement edata_procurements2 = EdataDAO.geteData_procurement(docsmained.get(0).getDoc_idrefer());
							
								edata_register_processing register_processing = EdataDAO.geteData_register_processing(edata_id);
								
								System.err.println(" edata_id ---> "+edata_id);
								
								if(register_processing != null) {
									
									System.err.println(" main_procurements ---> ");
									
									if(edata_procurements2.getProcurement_supplier_id() != null) {
										
										System.err.println(" edata_procurements2 ---> "+edata_procurements2.getProcurement_supplier_id());
										
											List<doc> docsmain2 = EdataDAO.getDoc(edata_procurements2.getEdata_id());
											
											System.err.println(" docsmain2 ---> "+edata_procurements2.getEdata_id());
											
											if(docsmain2.size() > 0) {
												String rv ="";
												if(docsmain2.get(0).getDoc_rv() > 0) { rv ="-R"+docsmain2.get(0).getDoc_rv();  }
												register_processing.setDoc_refer(docsmain2.get(0).getDoc_type()+docs.get(0).getDoc_date()+docsmain2.get(0).getDoc_autoid()+rv);
											}
											
									
											register_processing.setSupplier_id(edata_procurements2.getProcurement_supplier_id());
											register_processing.setSupplier_name(edata_procurements2.getProcurement_supplier_name());

												EdataDAO.SaveRegister_processing(register_processing);
											}
								}
					}
				}
				
			}


			public void Import_other(List<doc> docs, int edata_id, List<String> transferredColumn) {
				
		        System.out.println("--->Import_other edata_id "+edata_id);
		        String rv ="";
		        
		    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
		 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
		 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());

				other others = EdataDAO.geteData_other(edata_id);
				
				if(transferredColumn.contains("order_quantity")) { 	others.setOrder_quantity(Otherservice.DoubleValueOf(pd.getOrder_quantity())); }
				if(transferredColumn.contains("unit")) { others.setUnit(pd.getUnit());}
				if(transferredColumn.contains("reference_code")) { others.setReference_code(DOC);}
				
				EdataDAO.SaveOther(others);
	
			}
	
	
	
		public void Import_production_directive(List<doc> docs, int edata_id, List<String> transferredColumn) {
			
			        System.out.println("--->Import_production_directive edata_id "+edata_id);
			
			        
			        String rv ="";
			    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
			 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
			 		
					production_list pl = EdataDAO.geteData_pl(docs.get(0).getDoc_idrefer());
					production_directive pd = EdataDAO.geteData_pd(edata_id);
					if(transferredColumn.contains("lot_batch")) { 	pd.setLot_batch(pl.getLot_batch()); }
					if(transferredColumn.contains("product_name")) { 		pd.setProduct_name(pl.getProduct_name()); }
					if(transferredColumn.contains("product_code")) { 	pd.setProduct_code(pl.getProduct_code()); }
					if(transferredColumn.contains("order_quantity")) { pd.setQuantity(pl.getOrder_quantity()); }
					if(transferredColumn.contains("lot_batch")) { 	pd.setLot_batch(pl.getLot_batch());}
					if(transferredColumn.contains("processes")) { 	pd.setProcess_step(pl.getProcesses()); }
					if(transferredColumn.contains("order_quantity")) { 	pd.setOrder_quantity(pl.getOrder_quantity()); }
					if(transferredColumn.contains("item_code")) { 	pd.setItem_code(pl.getItem_code()); }
					if(transferredColumn.contains("responsible_person")) { 		pd.setResponsible_person(pl.getResponsible_person()); }
					if(transferredColumn.contains("start_date")) { 	pd.setStart_date(pl.getStart_date()); }
					if(transferredColumn.contains("end_date")) { 		pd.setEnd_date(pl.getEnd_date()); }
					if(transferredColumn.contains("pl_code")) { 	pd.setPl_code(DOC); }
					if(transferredColumn.contains("sub_unit")) { pd.setSub_unit(pl.getSub_unit()); }
					if(transferredColumn.contains("unit")) { 	pd.setUnit(pl.getUnit()); }
					if(transferredColumn.contains("department")) { 	pd.setDepartment(pl.getDepartment()); }		
					EdataDAO.SaveProduction_directives(pd);

		}
		
		
		
		
				
			public void Import_production_list(List<doc> docs, int edata_id, List<String> transferredColumn) {
					/*
					edata edatas = EdataDAO.getbyid(docs.get(0).getDoc_idrefer());
					production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
					*/
			}		
						
			public void Import_edata_production_record(List<doc> docs, int edata_id, List<String> transferredColumn) {
			
				
				   System.out.println("---> filemanagerservice.Import_edata_production_record");
				production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
				edata_production_record pr = EdataDAO.geteEdata_production_record(edata_id);

				
			    String rv ="";
		    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
		 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
				
		 		if(transferredColumn.contains("product_code")) { 	pr.setProduct_code(pd.getProduct_code()); }
		 		if(transferredColumn.contains("product_name")) { 	pr.setProduct_name(pd.getProduct_name()); }
		 		if(transferredColumn.contains("lot_batch")) { 	pr.setLot_batch(pd.getLot_batch()); }
		 		if(transferredColumn.contains("item_code")) { 		pr.setItem_code(pd.getItem_code()); }
		 		if(transferredColumn.contains("process_step")) { 	pr.setProcess_step(pd.getProcess_step()); }
		 		
		 		if(transferredColumn.contains("order_quantity")) { 	
		 			pr.setOrder_quantity(pd.getOrder_quantity());
		 		}
		 		
	
		 		if(transferredColumn.contains("line")) { 		pr.setLine(pd.getLine()); }
		 		if(transferredColumn.contains("sub_unit")) { 		pr.setUnit_of_measure(pd.getSub_unit());}
		 		if(transferredColumn.contains("reference_code")) { 	pr.setReference_code(DOC); }
		 		if(transferredColumn.contains("department")) { 		pr.setDepartment(pd.getDepartment()); }
		 		if(transferredColumn.contains("line")) { pr.setLine(pd.getLine()); }
		 		if(transferredColumn.contains("item_code")) { 		pr.setItem_code(pd.getItem_code());}
		 		

				EdataDAO.SaveEdata_production_records(pr);
				
		}


			
			public void Import_Issue_sk(List<doc> docs, int edata_id, List<String> transferredColumn) {
			
		        System.out.println("--->Import_other edata_id "+edata_id);
		        System.out.println("--->transferredColumn "+transferredColumn.toString());
		        String rv ="";
		        
		    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
		 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
		 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());

		 		Issue_sk data = EdataDAO.getIssue_sk(edata_id);
				
		 		if(transferredColumn.contains("pl_code")) { 	data.setSki_pd_code(DOC); }
		 		if(transferredColumn.contains("lot_batch")) { 	data.setSki_lot_batch(pd.getLot_batch()); }
		
				EdataDAO.Issue_SK(data);
	
			}

			
			
			
				public void Import_Issue_fg(List<doc> docs, int edata_id, List<String> transferredColumn) {
							
					        System.out.println("--->Import_other edata_id "+edata_id);
					        String rv ="";
					        
					    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
					 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
					 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
			
					 		Issue_fg data = EdataDAO.getIssue_fg(edata_id);
							
					 		
					 		if(transferredColumn.contains("product_code")) { 	data.setIssued_product_code(pd.getProduct_code()); }
					 		if(transferredColumn.contains("product_name")) { 	data.setIssued_product_name(pd.getProduct_name()); }
					 		if(transferredColumn.contains("lot_batch")) { 	data.setIssued_lot_batch(pd.getLot_batch()); }
					 		if(transferredColumn.contains("reference_code")) { 	data.setIssued_pd_code(DOC); }
							EdataDAO.Issue_FG(data,edata_id);
				
						}
	
				public void Import_Inventory_transfer_sk(List<doc> docs, int edata_id, List<String> transferredColumn) {
					
			        System.out.println("--->Import_other edata_id "+edata_id);
			        String rv ="";
			        
			    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
			 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
			 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
			
			 		Inventory_transfer_sk data = EdataDAO.getInventory_transfer_sk(edata_id);
					
			 		if(transferredColumn.contains("pd_code")) { 	data.setSkt_pd_code(DOC); }
			 		if(transferredColumn.contains("lot_batch")) { 	data.setSkt_lot_batch(pd.getLot_batch()); }
			 	
					EdataDAO.Transfer_SK(data,edata_id);
		
			
				}
				
				
						public void Import_Inventory_transfer_fg(List<doc> docs, int edata_id, List<String> transferredColumn) {
							
					        System.out.println("--->Import_other edata_id "+edata_id);
					        String rv ="";
					        
					    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
					 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
					 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
					
					 		Inventory_transfer_fg data = EdataDAO.getInventory_transfer_fg(edata_id);
							
					 		
					 		if(transferredColumn.contains("product_code")) { 	data.setTransfer_product_code(pd.getProduct_code()); }
					 		if(transferredColumn.contains("product_name")) { 	data.setTransfer_product_name(pd.getProduct_name()); }
					 		if(transferredColumn.contains("lot_batch")) { 	data.setTransfer_lot_batch(pd.getLot_batch()); }
					 		if(transferredColumn.contains("reference_code")) { 	data.setTransfer_pd_code(DOC); }
							EdataDAO.Transfer_FG(data,edata_id);
				
					
						}
	
						
							public void Import_Goods_receipt_sk(List<doc> docs, int edata_id, List<String> transferredColumn) {
							
					        System.out.println("--->Import_other edata_id "+edata_id);
					        String rv ="";
					        
					    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
					 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
					 		
					 		
					 		
					 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
					 
					 	
					 		Goods_receipt_sk data = EdataDAO.getGoods_receipt_sk(edata_id);
							
					 		if(transferredColumn.contains("lot_batch")) { 	data.setSkg_lot_batch(pd.getLot_batch()); }

							EdataDAO.Receipt_SK(data,edata_id);
					
						}
	
						
						
						public void Import_Goods_receipt_fg(List<doc> docs, int edata_id, List<String> transferredColumn) {
							
					        System.out.println("--->Import_other edata_id "+edata_id);
					        String rv ="";
					        
					    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
					 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
					 		
					 		
					 		
					 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
					 		edata pd_edata = EdataDAO.getbyid(pd.getEdata_id());
					 		List<doc> maindocs = EdataDAO.getDoc_in_set(pd_edata.getReference());
					 		production_list pl = EdataDAO.geteData_pl(maindocs.get(0).getDoc_idrefer());
					 		Goods_receipt_fg data = EdataDAO.getGoods_receipt_fg(edata_id);
							
					 		
					 		if(transferredColumn.contains("product_code")) { 	data.setGoods_receipt_product_code(pd.getProduct_code()); }
					 		if(transferredColumn.contains("product_name")) { 	data.setGoods_receipt_product_name(pd.getProduct_name()); }
					 	
					 		if(pl != null) { 
						 		if(transferredColumn.contains("customer_code")) { 	data.setGoods_receipt_customer_code(pl.getCustomer_code()); }
						 		if(transferredColumn.contains("customer_name")) { 	data.setGoods_receipt_customer_name(pl.getCustomer_name()); }
					 		}
					 		
					 		
					 		if(transferredColumn.contains("lot_batch")) { 	data.setGoods_receipt_lot_batch(pd.getLot_batch()); }
					 		if(transferredColumn.contains("pd_code")) { 	data.setGoods_receipt_pd_code(DOC); }
							EdataDAO.Receipt_FG(data,edata_id);
					
						}
	
						public void Import_Adjustment_sk(List<doc> docs, int edata_id, List<String> transferredColumn) {
							
					        System.out.println("--->Import_other edata_id "+edata_id);
					        String rv ="";
					        
					    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
					 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
					 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
					

					 		Adjustment_sk data = EdataDAO.getAdjustment_sk(edata_id);
							
					 		if(transferredColumn.contains("lot_batch")) { 	data.setSka_lot_batch(pd.getLot_batch()); }
							EdataDAO.Adjustment_SK(data,edata_id);
					
						}
						
						
						
					public void Import_Adjustment_fg(List<doc> docs, int edata_id, List<String> transferredColumn) {
						
				        System.out.println("--->Import_other edata_id "+edata_id);
				        String rv ="";
				        
				    	if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
				 		String DOC = docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv;
				 		production_directive pd = EdataDAO.geteData_pd(docs.get(0).getDoc_idrefer());
				

				 		Adjustment_fg data = EdataDAO.getAdjustment_fg(edata_id);
						
				 		
				 		if(transferredColumn.contains("product_code")) { 	data.setAdjustment_pd_code(pd.getProduct_code()); }
				 		if(transferredColumn.contains("product_name")) { 	data.setAdjustment_product_name(pd.getProduct_name()); }
				 		if(transferredColumn.contains("lot_batch")) { 	data.setAdjustment_lot_batch(pd.getLot_batch()); }
				 		if(transferredColumn.contains("reference_code")) { 	data.setAdjustment_pd_code(DOC); }
						EdataDAO.Adjustment_FG(data,edata_id);
				
					}
			
			
			
			
			
			
			
			
			
			
			public List<process> getMainTracking(workflow workflows, ModelMap model, List<process> data, List<edata_production_record> process, String[] tracking_main_step) {
				
				
				
			
			
				
				List<TenStatus> chainData = new ArrayList<>();

				// ขั้นตอนแรก
				int firstProcess = Integer.parseInt(tracking_main_step[0]);
				List<transaction_send> roots = EdataDAO.getAllTransaction_send(firstProcess, 10);

				for (transaction_send root : roots) {
				    TenStatus chainMap = new TenStatus();
				    chainMap.setName(root.getData_value1() + " " + root.getData_value2());
				    chainMap.setDoc(root.getData_value1());
				    chainMap.setStatus(1, root.getStatus());   // ใช้ method ไดนามิก

				    // เตรียม parentId สำหรับขั้นถัดไป
				    String parentId = root.getData_value1();

				    // วนลูปขั้นที่เหลือ (ข้าม index 0)
				    for (int i = 1; i < tracking_main_step.length; i++) {
				        int processId = Integer.parseInt(tracking_main_step[i]);

				        // ดึงลูก ตาม parentId
				        List<transaction_send> subs = EdataDAO.getAllTransaction_send(processId, parentId, 10);


				        
				        if (subs.isEmpty()) {
				        	
			
				       	 String prevStatus = chainMap.getStatus(i);
				       	 
				       	 if(prevStatus.equals("End")) {
				       	  chainMap.setStatus(i + 1, prevStatus);
				       	 }else {
				       		 
					         List<edata> doc = EdataDAO.getallProcesses(processId, parentId, 10);
				             
				             if(doc.size() > 0) {
				            	  chainMap.setStatus(i + 1, "Working");
				             }else {
				            	  chainMap.setStatus(i + 1, "UNKNOWN");
				             }
				             
				       		 
				       	 }
				       	 
				       	 
				       	 
			        	  
			        	    break;  // จบสายนี้
				        }

				        // ถ้าเหลือสถานะที่ยัง pending
				        boolean anyPending = subs.stream()
				                                 .anyMatch(s -> !"Receive".equals(s.getStatus()));
				        if (anyPending) {
				            chainMap.setStatus(i + 1, "Pending");
				        }else {
				        	 // ถ้าลูกทั้งหมดเสร็จแล้ว
					        chainMap.setStatus(i + 1, "End");
				        	 break;  // เสร็จเเล้ว → จบสายนี้
				        }
    
				        
			
				        // เตรียม parentId ใหม่ เพื่อไปรอบถัดไป (เอาตาม needs)
				        parentId = subs.stream()
				        	    .map(transaction_send::getData_value1)
				        	    .distinct()
				        	    .collect(Collectors.joining(","));
				        // แล้วไป loop ต่อ
				    }

				    chainData.add(chainMap);
				}
				
				
			
				System.out.println(" chainData "+chainData.size());
				
				for (String pidStr : tracking_main_step) {
				 process main = EdataDAO.getProcess(Integer.valueOf(pidStr));		
				 data.add(main);
				}
				
			
				
				model.addAttribute("chainData", chainData);
					 
		
					
	 
			
				 
				 
				return data;
			}
			
		
	
			
			

			public List<process> getTracking(workflow workflows, ModelMap model, List<process> data, List<edata_production_record> process, String tracking) {

				 process main = new process();
				 main.setName(tracking);
				
				
				 if(workflows.getTracking_type().equals("Max")) {
					 
					 System.out.println("Max ");

					// สร้าง 7 วันย้อนหลัง
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					List<String> last7Days = new ArrayList<>();
					for (int i = 6; i >= 0; i--) {
					    last7Days.add(LocalDate.now().minusDays(i).format(formatter));
					}

					model.addAttribute("last7Days", last7Days);

					// Map<Date, MostFrequentStatus>
					Map<String, String> mostFrequentStatusByDate = last7Days.stream().collect(Collectors.toMap(
					    date -> date,
					    date -> {
					        // ดึง record เฉพาะที่ start_date_time ตรงกับวันนั้น
					        List<edata_production_record> recordsOfDay = process.stream()
					            .filter(r -> {
					                if (r.getStart_date_time() == null) return false;
					                String recordDate = r.getStart_date_time().substring(0, 10);
					                return date.equals(recordDate);
					            })
					            .collect(Collectors.toList());

					        // Map status -> count (รวม UNKNOWN ถ้า status == null)
					        Map<String, Long> statusCount = recordsOfDay.stream()
					            .map(r -> r.getStatus() != null ? r.getStatus() : "UNKNOWN")
					            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

					        // ✅ เพิ่ม "UNKNOWN2" เฉพาะกรณีในวันนั้นมี record ที่ start_date_time == null
					        long unknown2Count = process.stream()
					            .filter(r -> r.getStart_date_time() == null)
					            .count();

					        if (unknown2Count > 0) {
					            statusCount.put("UNKNOWN2", unknown2Count);
					        }

					        // หาสถานะที่พบบ่อยที่สุด
					        return statusCount.entrySet().stream()
					            .max(Map.Entry.comparingByValue())
					            .map(Map.Entry::getKey)
					            .orElse("UNKNOWN");
					    },
					    (a, b) -> b,
					    LinkedHashMap::new
					));

					// สร้าง summary list
					List<StatusSummary> summaryList = mostFrequentStatusByDate.entrySet().stream()
					    .map(entry -> new StatusSummary(entry.getKey(), entry.getValue()))
					    .collect(Collectors.toList());

					main.setStatusSummary(summaryList);
					data.add(main);
				 }else if(workflows.getTracking_type().equals("Important")) {
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 List<String> last7Days = new ArrayList<>();

					 for (int i = 6; i >= 0; i--) {
					     last7Days.add(LocalDate.now().minusDays(i).format(formatter));
					 }

					 model.addAttribute("last7Days", last7Days);

					 // Map<Date, LatestStatus>
					 Map<String, String> latestStatusByDate = last7Days.stream().collect(Collectors.toMap(
					     date -> date,
					     date -> {
					         // กรองเฉพาะ record ที่ start_date_time ตรงกับวันนั้น
					         List<edata_production_record> recordsOfDay = process.stream()
					             .filter(r -> {
					                 if (r.getStart_date_time() == null) return false;
					                 String recordDate = r.getStart_date_time().substring(0, 10);
					                 return date.equals(recordDate);
					             })
					             .sorted(Comparator.comparing(edata_production_record::getId).reversed())
					             .collect(Collectors.toList());

					         // ถ้าว่างหมด แสดงว่าไม่มีข้อมูลเลยในวันนั้น
					         if (recordsOfDay.isEmpty()) {
					             return "UNKNOWN2";
					         }

					         // เช็คว่าใน list นั้นมี status เป็น "Bad" ไหม
					         Optional<edata_production_record> badStatus = recordsOfDay.stream()
					             .filter(r -> "Bad".equals(r.getStatus()))
					             .findFirst();

					         if (badStatus.isPresent()) {
					             return "Bad";
					         }

					         // ถ้าไม่มี Bad ก็เอา record แรกสุดมา แล้วเช็คว่า status null ไหม
					         edata_production_record latest = recordsOfDay.get(0);
					         return latest.getStatus() != null ? latest.getStatus() : "UNKNOWN";
					     },
					     (a, b) -> b,
					     LinkedHashMap::new
					 ));

					 List<StatusSummary> summaryList = latestStatusByDate.entrySet().stream()
					     .map(entry -> new StatusSummary(entry.getKey(), entry.getValue()))
					     .collect(Collectors.toList());

					 main.setStatusSummary(summaryList);
					 data.add(main);
				 
				 
			 }else {
					 
					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					    List<String> last7Days = new ArrayList<>();
					  //  for (int i = 0; i < 7; i++) {
					    
					    for (int i = 6; i >= 0; i--) {
					    
					        last7Days.add(LocalDate.now().minusDays(i).format(formatter));
					    }

					    model.addAttribute("last7Days", last7Days);

					    // Map<Date, LatestStatus>
					    Map<String, String> latestStatusByDate = last7Days.stream().collect(Collectors.toMap(
					    	    date -> date,
					    	    date -> {
					    	        return process.stream()
					    	            .filter(r -> {
					    	                if (r.getStart_date_time() == null) return false;
					    	                String recordDate = r.getStart_date_time().substring(0, 10);
					    	                return date.equals(recordDate);
					    	            })
					    	            .sorted(Comparator.comparing(edata_production_record::getId).reversed())
					    	            .findFirst()
					    	            .map(r -> r.getStatus() != null ? r.getStatus() : "UNKNOWN")
					    	            .orElse("UNKNOWN2");
					    	    },
					    	    (a, b) -> b,
					    	    LinkedHashMap::new // 💡 ใช้ LinkedHashMap เพื่อคงลำดับ
					    	));
					    List<StatusSummary> summaryList = latestStatusByDate.entrySet().stream()
					        .map(entry -> new StatusSummary(entry.getKey(), entry.getValue()))
					        .collect(Collectors.toList());
					    main.setStatusSummary(summaryList);

					    data.add(main);
				 }
				return data;
				 
				
			}		
			
			
          		
			
			
			public List<?> FakeTable(String Type,String filter) {
						  switch (Type){
				            case "wip":
				            	return WIP_FakeTable(filter);   
				            case "oee":
				            	return OEE_FakeTable(filter);   
				            case "stock_count":
				            	return SC_FakeTable(filter); 
				       
				            default:
				                throw new IllegalArgumentException("Invalid FakeTable name: ");
					}
				}

		
			private List<DisbursementDTO> DT_FakeTable(String filter) {
				
				
				List<DisbursementDTO> disbursement = new ArrayList<DisbursementDTO>();
				DT_LoadBalance(disbursement,"receive",filter);	
				DT_LoadBalance(disbursement,"payout",filter);
				
				
				List<DisbursementDTO> data = new ArrayList<DisbursementDTO>();
				if(filter == null) {
					data = DT_Organize_Main(disbursement);
				}else {
					data = DT_Organize(disbursement,filter);
				}

				return  data;
		}
			
		
			
			
			
			private List<stock_count> SC_FakeTable(String filter) {

				System.out.println(" SC_FakeTable ");
				
				List<stock_count> stock_counts = new ArrayList<stock_count>();
				SC_LoadBalance(stock_counts,"record");	
				return  stock_counts;
		}
			
		
		
			
			private List<wip> WIP_FakeTable(String filter) {
					
			
					List<wip> wips = new ArrayList<wip>();
					WIP_LoadBalance(wips,"receive");	
					WIP_LoadBalance(wips,"payout");
					
					
					List<wip> data = new ArrayList<wip>();
					if(filter == null) {
						data = WIP_Organize_Main(wips);
					}else {
						data = WIP_Organize(wips,filter);
					}

					return  data;
			}
				
			
			
			
			
			private List<oee> OEE_FakeTable(String filter) {
				
				
				List<oee> oees = new ArrayList<oee>();
				OEE_LoadBalance(oees,"availability");	
				OEE_LoadBalance(oees,"performance");
				OEE_LoadBalance(oees,"quality");
				
				List<oee> data = new ArrayList<oee>();
				if(filter == null) {
					data = OEE_Organize_Main(oees);
				}else {
					data = OEE_Organize(oees,filter);
				}


				return  data;
		}
			
			
			private List<oee> OEE_Organize(List<oee> oees, String filter) {
			    String[] filters = filter.split("_");
			    String machineFilter = filters.length > 0 ? filters[0] : null;
			    String monthFilter = filters.length > 1 ? filters[1] : null;

			    return oees.stream()
			        .filter(row -> machineFilter == null || row.getMachine().equals(machineFilter))
			        .filter(row -> monthFilter == null || row.getDate_oee().startsWith(monthFilter))
			        .limit(500)
			        .collect(Collectors.toList());
			}
			
			
			
			private List<oee> OEE_Organize_Main(List<oee> oees ) {


				Map<String, oee> result = oees.stream()
					    .collect(Collectors.groupingBy(
					        o -> o.getMonth_oee() + "_" + o.getMachine(), // Group by month and machine
					        Collectors.collectingAndThen(
					            Collectors.toList(),
					            list -> {
					                oee sample = list.get(0); // ใช้ตัวอย่างจากกลุ่ม

					                int totalDowntime = list.stream().mapToInt(oee::getDowntime).sum();
					                double totalPlannedTime = list.stream().mapToDouble(oee::getPlanned_time).sum();
					                int totalCount = list.stream().mapToInt(oee::getTotal_count).sum();
					                int totalGoodCount = list.stream().mapToInt(oee::getGood_count).sum();

					                double operatingTime = totalPlannedTime - totalDowntime;
					                double availability = (totalPlannedTime > 0) ? (operatingTime / totalPlannedTime) * 100 : 0;

					                // ค่า speed (ใช้ค่าแรก หรือค่าเฉลี่ยก็ได้)
					                double averageSpeed = list.stream().mapToInt(oee::getSpeed).average().orElse(0);

					                // performance: actual vs ideal
					                // ในที่นี้สมมุติว่า idealCycleTime = 1 / speed → ถ้าชิ้น/ชม = 60 → 1 นาทีต่อชิ้น = 60 วินาทีต่อชิ้น
					                // performance = (totalCount * idealCycleTime) / operatingTime
					                double performance = (operatingTime > 0 && averageSpeed > 0)
					                    ? ((double) totalCount / (averageSpeed * (operatingTime / 60.0))) * 100
					                    : 0;

					                double quality = (totalCount > 0) ? ((double) totalGoodCount / totalCount) * 100 : 0;
					                double oeeValue = (availability * performance * quality) / 10000;

					                oee combined = new oee();
					                combined.setDate_oee(sample.getDate_oee());
					                combined.setMonth_oee(sample.getMonth_oee());
					                combined.setMachine(sample.getMachine());

					                combined.setDowntime(totalDowntime);
					                combined.setPlanned_time(totalPlannedTime);
					                combined.setOperating_time(operatingTime);
					                combined.setAvailability((int) availability);

					                combined.setSpeed((int) averageSpeed);
					                combined.setTotal_count(totalCount);
					                combined.setPerformance(performance);

					                combined.setGood_count(totalGoodCount);
					                combined.setQuality((int) quality);

					                combined.setOee((int) oeeValue);

					                return combined;
					            }
					        )
					    ));

				List<oee> combinedList = new ArrayList<>(result.values());
			
				return  combinedList.stream().limit(500).collect(Collectors.toList());
				
			}


			
			private List<oee> OEE_LoadBalance(List<oee> oees,String type) {	
				
				
				System.out.println("-----> filemanagerservice.OEE_LoadBalance ");
				
				List<reportsetting> reportsettings  = WorkflowDAO.getAllReportsetting("oee",type);
				
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime today = LocalDateTime.now();
				String date = today.format(formatter);
				
				reportsettings.forEach(row->{
					
					if(row.getReportsetting_data() == null) { return;}
					
					
					List<?> data = new ArrayList<>();
					if(row.getReportsetting_data().equals("OTHER")) {
						data = EdataDAO.geteAllOtherByProcess(row.getReportsetting_id_refer());
					}else if(row.getReportsetting_data().equals("pd")) {
						data = EdataDAO.geteAllPDByProcess(row.getReportsetting_id_refer());
					}else {
						 return;
					}

					
					data.forEach(subrow->{
						oee adddata = new oee();
	
						Integer downtimeValue = Otherservice.getIntegerByReflection(subrow, row.getReportsetting_downtime());
				    	adddata.setDowntime(downtimeValue);
				    	
				       	Double planned_timeValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_planned_time());
				       	adddata.setPlanned_time(planned_timeValue);
				    	
				       	Integer speedValue = Otherservice.getIntegerByReflection(subrow, row.getReportsetting_speed());
				       	adddata.setSpeed(speedValue);
				    	
				       	Integer total_countValue = Otherservice.getIntegerByReflection(subrow, row.getReportsetting_total_count());
				       	adddata.setTotal_count(total_countValue);
				       	
				      	Double operating_timeValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_operating_time());
				       	adddata.setOperating_time(operating_timeValue);

				       	
				    	Integer good_countValue = Otherservice.getIntegerByReflection(subrow, row.getReportsetting_good_count());
				       	adddata.setSpeed(good_countValue);
					
				       	String date_wipValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_date_wip());
				       	adddata.setDate_oee(date_wipValue == null ? date : date_wipValue);
				       	
				    	
				       	oees.add(adddata);
					
					});
			
					
				});
			
			    return oees;
			
			}
			
			
			
			
			
			

				private List<wip> WIP_LoadBalance(List<wip> wips,String type) {	
					
					System.out.println("-----> filemanagerservice.WIP_LoadBalance ");
					
					
					List<reportsetting> reportsettings  = WorkflowDAO.getAllReportsetting("wip",type);
					
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime today = LocalDateTime.now();
					String date = today.format(formatter);
					
					reportsettings.forEach(row->{
						
						if(row.getReportsetting_data() == null) { return;}
						
						
						List<?> data = new ArrayList<>();
						if(row.getReportsetting_data().equals("OTHER")) {
							data = EdataDAO.geteAllOtherByProcess(row.getReportsetting_id_refer());
						}else if(row.getReportsetting_data().equals("pd")) {
							data = EdataDAO.geteAllPDByProcess(row.getReportsetting_id_refer());
						}else {
							 return;
						}

						
						data.forEach(subrow->{
							wip wip = new wip();
		
					    	String pdValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_pd());
					    	wip.setPd(pdValue);
					    	
					       	String batchValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_batch());
					    	wip.setBatch(batchValue);
					    	
					       	String departmentValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_department());
					    	wip.setDepartment(departmentValue);
					    	
					       	String listValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_list());
					    	wip.setList(listValue);
					    	
					       	String employeeValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_employee());
					    	wip.setEmployee(employeeValue);
					    	
					       	String machineValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_machine());
					    	wip.setMachine(machineValue);
					    	
					       	String date_wipValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_date_wip());
					    	wip.setDate_wip(date_wipValue == null ? date : date_wipValue);
					    	
					    	Double ordersValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_orders());
					    	wip.setOrders(ordersValue);
					    	
					    	Double received_todayValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_received_today());
					    	wip.setReceived_today(received_todayValue);
					    	
					       	Double paid_todayValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_paid_today());
					    	wip.setPaid_today(paid_todayValue);
					    	
					    	
					    	wips.add(wip);
						
						});
				
						
					});
				
				    return wips;
				
				}
				
				
			
				private List<wip> WIP_Organize_Main(List<wip> wips) {
					List<wip> result = wips.stream()
						    .collect(Collectors.groupingBy(w -> {
						        String batch = (w.getBatch() == null || w.getBatch().isBlank()) ? "ไม่ระบุ" : w.getBatch();
						        String pd = (w.getPd() == null || w.getPd().isBlank()) ? "ไม่ระบุ" : w.getPd();
						        return batch + "|" + pd;
						    }))
						    .values().stream()
						    .flatMap(group -> group.stream().limit(1)) // 2 แถวแรกของแต่ละกลุ่ม
						    .collect(Collectors.toList());
					return  result;
				}	
				
				
				private List<wip> WIP_Organize(List<wip> wips,String filter ) {


					
					
					System.out.println(" WIP_Organize run..."+wips.size());
					
					// TODO Auto-generated method stub
			
					
					
					
					
					System.out.println(" WIP_Organize groupingBy end...");
					
					
					
	
					Map<String, wip> result = wips.stream()

					    // ถ้า filter มีค่าให้กรอง ถ้าไม่มีก็ผ่านหมด
					    .filter(w -> {
					        if (filter == null || filter.isBlank()) {
					            return true; // ไม่กรอง ใช้ทั้งหมด
					        }
					        
							String[] filters = filter.split("_");
							String batchFilter = filters.length > 0 ? filters[0] : null;
							String pdFilter = filters.length > 1 ? filters[1] : null;

					        boolean matchBatch = true;
					        if (batchFilter != null) {
					            if ("N/A".equals(batchFilter)) {
					                matchBatch = w.getBatch() == null || w.getBatch().isBlank();
					            } else {
					                matchBatch = batchFilter.equals(w.getBatch());
					            }
					        }

					        boolean matchPd = true;
					        if (pdFilter != null) {
					            if ("N/A".equals(pdFilter)) {
					                matchPd = w.getPd() == null || w.getPd().isBlank();
					            } else {
					                matchPd = pdFilter.equals(w.getPd());
					            }
					        }

					        return matchBatch && matchPd;
					    })

					    // groupingBy เดิม
					    .collect(Collectors.groupingBy(
					        wip::getDate_wip,
					        Collectors.collectingAndThen(
					            Collectors.toList(),
					            list -> {
					                wip sample = list.get(0);
					                Double totalReceived = list.stream().mapToDouble(wip::getReceived_today).sum();
					                Double totalPaid = list.stream().mapToDouble(wip::getPaid_today).sum();

					                wip combined = new wip();
					                combined.setDate_wip(sample.getDate_wip());
					                combined.setReceived_today(totalReceived);
					                combined.setPaid_today(totalPaid);

					                Function<Function<wip, String>, String> firstNonEmpty = getter ->
					                    list.stream()
					                        .map(getter)
					                        .filter(val -> val != null && !val.isBlank())
					                        .findFirst()
					                        .orElse(null);

					                Function<Function<wip, Double>, Double> firstNonNullDouble = getter ->
					                    list.stream()
					                        .map(getter)
					                        .filter(Objects::nonNull)
					                        .findFirst()
					                        .orElse(null);

					                combined.setOrders(firstNonNullDouble.apply(wip::getOrders));
					                combined.setPd(firstNonEmpty.apply(wip::getPd));
					                combined.setBatch(firstNonEmpty.apply(wip::getBatch));
					                combined.setDepartment(firstNonEmpty.apply(wip::getDepartment));
					                
					                combined.setMachine(firstNonEmpty.apply(wip::getMachine));
					                combined.setEmployee(firstNonEmpty.apply(wip::getEmployee));
					                combined.setLine(firstNonEmpty.apply(wip::getLine));
					                combined.setList(firstNonEmpty.apply(wip::getList));
					                
					                return combined;
					            }
					        )
					    ));

					
					
					
					
					
					
					List<wip> combinedList = new ArrayList<>(result.values());
					
			
					
					// 1. เรียงลำดับตาม date_wip
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					combinedList.sort(Comparator.comparing(w -> 
					    LocalDate.parse(w.getDate_wip(), formatter)
					));

					// 2. คำนวณค่าที่สะสม
					double receivedTotal = 0;
					double paidTotal = 0;

					for (wip w : combinedList) {
					    receivedTotal += w.getReceived_today();
					    paidTotal += w.getPaid_today();

					    w.setReceived_accumulated(receivedTotal);
					    w.setPaid_accumulated(paidTotal);
					    
					    if(w.getOrders() > 0.0) {
						    w.setPending_received(w.getOrders() -  receivedTotal );
						    w.setPending_paid(w.getOrders() -  paidTotal);
					    }
					    
					    }
					
					return  combinedList.stream().limit(500).collect(Collectors.toList());
					
				}


				
				
				
				
				
				private List<stock_count> SC_LoadBalance(List<stock_count> stock_counts,String type) {	
					
					List<reportsetting> reportsettings  = WorkflowDAO.getAllReportsetting("stock_count",type);
					
	
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime today = LocalDateTime.now();
					String date = today.format(formatter);
					
					reportsettings.forEach(row->{
						
						if(row.getReportsetting_data() == null) { return;}
						
						
						List<?> data = new ArrayList<>();
						if(row.getReportsetting_data().equals("OTHER")) {
							data = EdataDAO.geteAllOtherByProcess(row.getReportsetting_id_refer());
						}else if(row.getReportsetting_data().equals("Adjustment_sk")) {
							
							System.out.println("SC_LoadBalance Adjustment_sk ");
							
							data = EdataDAO.geteAllSKByProcess(row.getReportsetting_id_refer());
						}else {
							 return;
						}

						
						data.forEach(subrow->{
							stock_count Reflectiondata = new stock_count();
		
					    	String dataValue1 = Otherservice.getStringByReflection(subrow, row.getReportsetting_batch());
					    	Reflectiondata.setBatch(dataValue1);
					    	
					    	String dataValue2 = Otherservice.getStringByReflection(subrow, row.getReportsetting_stock_code());
					    	Reflectiondata.setStock_code(dataValue2);
					    	
					    	String dataValue3 = Otherservice.getStringByReflection(subrow, row.getReportsetting_stock_name());
					    	Reflectiondata.setStock_name(dataValue3);
					    	
					    	Double dataValue4 = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_total_system());
					    	Reflectiondata.setTotal_system(dataValue4);
					    	
					    	Double dataValue5 = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_total_real());
					    	Reflectiondata.setTotal_real(dataValue5);
					    	
					    	
					    	String dataValue6 = Otherservice.getStringByReflection(subrow, row.getReportsetting_date_wip());
					    	Reflectiondata.setDate_add(dataValue6);
					    	
					    	String dataValue7 = Otherservice.getStringByReflection(subrow,"data_nameadd");
					    	Reflectiondata.setName_add(dataValue7);
					
					  

					    	stock_counts.add(Reflectiondata);
						
						});
				
						
					});
				
				    return stock_counts;
				
				}
				
				
				
				
				
				
				
				
				
				
				
				
				

				private List<DisbursementDTO> DT_LoadBalance(List<DisbursementDTO> DisbursementDTO,String type,String search) {	
					
					List<reportsetting> reportsettings  = WorkflowDAO.getAllReportsetting("disbursement",type);
					
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDateTime today = LocalDateTime.now();
					String date = today.format(formatter);
					
					reportsettings.forEach(row->{
						
						if(row.getReportsetting_data() == null) { return;}
						
						
						List<?> data = new ArrayList<>();
						if(row.getReportsetting_data().equals("OTHER")) {
							data = EdataDAO.geteAllOtherByProcess(row.getReportsetting_id_refer());
						}else if(row.getReportsetting_data().equals("fa_payments")) {
						
							 if (search.matches("\\d{4}-\\d{2}")) {
								 data = fa_paymentsRepository.findByProcessIdAndYearMonth(row.getReportsetting_id_refer(),search);
							 }else {
								 data = fa_paymentsRepository.findByProcessIdAndYear(row.getReportsetting_id_refer(),search);
							 }
						
						}else {
							 return;
						}

						
						data.forEach(subrow->{
							DisbursementDTO Disbursement = new DisbursementDTO();
		

					    	
					       	String listValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_list());
					       	Disbursement.setList(listValue);
					    	
					       	String date_wipValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_date_wip());
					       	Disbursement.setDates(date_wipValue);
					    	
					    	Double received_todayValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_received_today());
					    	Disbursement.setReceived(received_todayValue);
					    	
					       	Double paid_todayValue = Otherservice.getDoubleByReflection(subrow, row.getReportsetting_paid_today());
					       	Disbursement.setPaid(paid_todayValue);
					    	
					       	String drawerValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_drawer());
					       	Disbursement.setDrawer(drawerValue);
					       	
					       	String payerValue = Otherservice.getStringByReflection(subrow, row.getReportsetting_payer());
					       	Disbursement.setPayer(payerValue);
					       	
					       	
					       	DisbursementDTO.add(Disbursement);
						
						});
				
					
					});
				
				    return DisbursementDTO;
				
				}
				
				
			
				private List<DisbursementDTO> DT_Organize_Main(List<DisbursementDTO> DisbursementDTO) {
					return  DisbursementDTO;
				}	
				
				
				private List<DisbursementDTO> DT_Organize(List<DisbursementDTO> disbursementDTOList,String filter ) {

					 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

					    // ล้างรายการที่ไม่มีวันที่หรือ format ผิด
					    disbursementDTOList.removeIf(w -> {
					        try {
					            return w.getDates() == null || w.getDates().isBlank()
					                   || LocalDate.parse(w.getDates().trim(), formatter) == null;
					        } catch (Exception e) {
					            return true;
					        }
					    });

					    // เรียงลำดับ:
					    // 1. วันที่ขึ้นก่อน
					    // 2. ภายในวันเดียวกัน → received ขึ้นก่อน paid
					    disbursementDTOList.sort(
					        Comparator
					            .comparing((DisbursementDTO w) -> LocalDate.parse(w.getDates().trim(), formatter))
					            .thenComparing(w -> w.getReceived() > 0 ? 0 : 1)
					    );

					    // คำนวณยอดสะสม
					    double receivedTotal = 0;
					    double paidTotal = 0;

					    for (DisbursementDTO w : disbursementDTOList) {
					        receivedTotal += w.getReceived();
					        paidTotal += w.getPaid();

					        w.setRemain(receivedTotal - paidTotal);
					    }

					    // ตัดแค่ 500 รายการแรก (ถ้ามีมาก)
					    return disbursementDTOList.stream().limit(500).collect(Collectors.toList());
					
				}


				
				
				
				
				
				
				
				public void setEntity(MultipleModelsDTO dto, LoadApp entity) {

					
				
					
					
				    if (dto.getAccount_ledgers() != null) entity.setAccount_ledger(dto.getAccount_ledgers());
				    if (dto.getFa_payments() != null) entity.setFa_payments(dto.getFa_payments());
				    if (dto.getHr_actions() != null) entity.setHr_action(dto.getHr_actions());
				    if (dto.getFa_invoices() != null) entity.setFa_invoices(dto.getFa_invoices());
				    if (dto.getEdata_workrules() != null) entity.setEdata_workrule(dto.getEdata_workrules());
				    if (dto.getEdata_assets() != null) entity.setEdata_assets(dto.getEdata_assets());
				    if (dto.getEdata_annual_plans() != null) entity.setEdata_annual_plan(dto.getEdata_annual_plans());
				    if (dto.getMachine_activitys() != null) entity.setMachine_activity(dto.getMachine_activitys());
				    if (dto.getPs_contracts() != null) entity.setPs_contract(dto.getPs_contracts());
				    if (dto.getPurchase_order_trackings() != null) entity.setPurchase_order_tracking(dto.getPurchase_order_trackings());
				    if (dto.getSales_followups() != null) entity.setSales_followup(dto.getSales_followups());
				    if (dto.getRequest_for_quotations() != null) entity.setRequest_for_quotation(dto.getRequest_for_quotations());
				    if (dto.getMaintenance_requests() != null) entity.setMaintenance_requests(dto.getMaintenance_requests());
				    if (dto.getEdata_stocks() != null) entity.setEdata_stock(dto.getEdata_stocks());
				    if (dto.getEdata_registers() != null) entity.setEdata_register(dto.getEdata_registers());
				    if (dto.getEdata_procurements() != null) entity.setEdata_procurements(dto.getEdata_procurements());
				    if (dto.getEdata_register_processings() != null) entity.setEdata_register_processing(dto.getEdata_register_processings());
				    if (dto.getEdata_products() != null) entity.setEdata_product(dto.getEdata_products());
				    if (dto.getEdata_production_records() != null) entity.setEdata_production_records(dto.getEdata_production_records());
				    if (dto.getProduction_directives() != null) entity.setProduction_directives(dto.getProduction_directives());
				    if (dto.getProduction_lists() != null) entity.setProduction_lists(dto.getProduction_lists());
				    if (dto.getQcis() != null) entity.setQci(dto.getQcis());
				    if (dto.getBoms() != null) entity.setBom(dto.getBoms());
				    if (dto.getOthers() != null) entity.setOther(dto.getOthers());
				    if (dto.getProduction_standards() != null) entity.setProduction_standard(dto.getProduction_standards());
				    if (dto.getGoods_receipt_fgs() != null) entity.setGoods_receipt_fg(dto.getGoods_receipt_fgs());
				    if (dto.getAdjustment_fgs() != null) entity.setAdjustment_fg(dto.getAdjustment_fgs());
				    if (dto.getInventory_transfer_fgs() != null) entity.setInventory_transfer_fg(dto.getInventory_transfer_fgs());
				    if (dto.getIssue_fgs() != null) entity.setIssue_fg(dto.getIssue_fgs());
				    if (dto.getGoods_receipt_sks() != null) entity.setGoods_receipt_sk(dto.getGoods_receipt_sks());
				    if (dto.getAdjustment_sks() != null) entity.setAdjustment_sk(dto.getAdjustment_sks());
				    if (dto.getInventory_transfer_sks() != null) entity.setInventory_transfer_sk(dto.getInventory_transfer_sks());
				    if (dto.getIssue_sks() != null) entity.setIssue_sk(dto.getIssue_sks());
				}
				
				
				public String getTable(int process_id) {	
					  
					String my_type = "-";
			  		 List<sub_processes> my_sub_process = EdataDAO.getAllSubProcess(process_id);
	        		 
	        		 
	        		 Optional<sub_processes> my_action = my_sub_process.stream()
	        				    .filter(row -> {
	        				        String a1 = row.getAction();
	        				        String a2 = row.getActivate_reference_continue();
	        				        String a3 = row.getActivate_reference_modify();
	        				        String a4 = row.getActivate_reference_only();
	        				        return isValid(a1) || isValid(a2) || isValid(a3) || isValid(a4);
	        				    })
	        				    .findFirst();

	      		   if(my_action.isPresent()) {
	      			   sub_processes  my_subprocess = my_action.get();
	      			   
	      			
		      			   
		      			   if (isValid(my_subprocess.getAction())) {
		      				   my_type = my_subprocess.getAction();
		      			    } else if (isValid(my_subprocess.getActivate_reference_continue())) {
		      			    	my_type = my_subprocess.getActivate_reference_continue();
		      			    } else if (isValid(my_subprocess.getActivate_reference_modify())) {
		      			    	my_type = my_subprocess.getActivate_reference_modify();
		      			    } else if (isValid(my_subprocess.getActivate_reference_only())) {
		      			    	my_type = my_subprocess.getActivate_reference_only();
		      			    }
	      		   }
				return my_type; 
				}
				
				private static boolean isValid(String value) {
				    return value != null && !value.equals("-") && !value.equals("0");
				}



				public List<edata> getEdata(int id, customreport customreports, String type) {
					
					if(customreports == null) {
						customreports = new customreport();
					}
					 
					
					List<edata> edata = null;
					switch (type) {
			        case "PL":
			        	edata = EdataDAO.getallEdataPL(id,customreports);
			            break;  
			        case "PD":
			        	edata = EdataDAO.getallEdataPD(id,customreports);
			             break;         
			        case "production_record":
			        	edata = EdataDAO.getallEPR(id,customreports);
			             break;         
			        case "QCI":
			        	edata = EdataDAO.getallEdataQCI(id,customreports);
			             break;   
			        case "OTHER":
			        	edata = EdataDAO.getallEdataOther(id,customreports);
			             break;  
			        case "PR":
			        	edata = EdataDAO.getallEdataPR(id,customreports);
			             break;         
			        case "PO":
			        	edata = EdataDAO.getallEdataPR(id,customreports);
			             break;        
			        case "GR":
			        	edata = EdataDAO.getallEdataPR(id,customreports);
			             break;      
			        case "BOM":
			        	edata = EdataDAO.getallEdataBOM(id,customreports);
			             break;              
			        case "PS":
			        	edata = EdataDAO.getallEdataPS(id,customreports);
			            break;  
			        default:
			        	edata = EdataDAO.getallEdataALLType(id,customreports,type);
			        	 break;  
					}	
	   
					return edata;
				}



				public List<?> getTransaction(int id, customreport customreports, CustomReport CustomReport, String type) {
					
					
					 System.out.println(" getTransaction "+type);


					if(customreports == null) {
						customreports = new customreport();
					}
					 

					List<?> transaction = null;
					
					switch (type) {
			        case "PL":
			        	transaction = applyPivotAndEDA(EdataDAO.getAllPL(id,customreports),CustomReport,customreports);
			            break;  
			        case "PD":
			        	transaction  = applyPivotAndEDA(EdataDAO.getAllPD(id,customreports),CustomReport,customreports);			
			             break;         
			        case "production_record":
			        	transaction  = applyPivotAndEDA(EdataDAO.getAllEdata_production_records(id,customreports),CustomReport,customreports);	
			             break;         
			        case "QCI":
			        	transaction  = applyPivotAndEDA(EdataDAO.getAllQCI(id,customreports),CustomReport,customreports);				        	
			             break;   
			        case "OTHER":
			        	transaction  = applyPivotAndEDA(EdataDAO.getAllOther(id,customreports),CustomReport,customreports);	
			             break;  
			        case "PR":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllPR(id,customreports),CustomReport,customreports);	
			             break;         
			        case "PO":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllPR(id,customreports),CustomReport,customreports);	
			             break;        
			        case "GR":
			          	transaction  = applyPivotAndEDA( EdataDAO.getAllPR(id,customreports),CustomReport,customreports);	
			             break;      
			        case "BOM":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllBOM(id,customreports),CustomReport,customreports);	
			             break;              
			        case "PS":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllPS(id,customreports),CustomReport,customreports);	
			            break;   
			            
			        case "Goods_receipt_fg":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllGFG(id,customreports),CustomReport,customreports);	
			            break;   
			        case "Issue_fg":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllIFG(id,customreports),CustomReport,customreports);	
			            break;   
			        case "Adjustment_fg":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllAFG(id,customreports),CustomReport,customreports);	
			            break;   
			        case "Inventory_transfer_fg":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllITFG(id,customreports),CustomReport,customreports);	
			            break;   
			        case "Goods_receipt_sk":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllGSK(id,customreports),CustomReport,customreports);	
			            break;   
			        case "Issue_sk":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllISK(id,customreports),CustomReport,customreports);	
			            break;   
			        case "Adjustment_sk":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllASK(id,customreports),CustomReport,customreports);	
			            break;         
			        case "Inventory_transfer_sk":
			          	transaction  = applyPivotAndEDA(EdataDAO.getAllITSK(id,customreports),CustomReport,customreports);	
			            break;  
			            
			        default:
			        	 break;  
					}	
					
					
					return transaction;
				}
				
				
				
				public List<Map<String, String>> getTransactionMap(int id, customreport customreports, CustomReport CustomReport, String type) {
					
					
					 System.out.println(" getTransaction "+type);
					
					SqlBuilderDTO SqlBuilderDTO = new  SqlBuilderDTO();
					 Class<?> clazz = null;
					if(customreports == null) {
						customreports = new customreport();
					}else {
						
					    SqlBuilderDTO.setType1(customreports.getCondition_choice1());
				        SqlBuilderDTO.setType2(customreports.getCondition_choice2());
				        SqlBuilderDTO.setType3(customreports.getCondition_choice3());
				        
				        SqlBuilderDTO.setSearch_by1(customreports.getFilter_column1());
				        SqlBuilderDTO.setSearch_by2(customreports.getFilter_column2());
				        SqlBuilderDTO.setSearch_by3(customreports.getFilter_column3());
				        
				        
				        SqlBuilderDTO.setSearch_type1(customreports.getOperation1());
				        SqlBuilderDTO.setSearch_type2(customreports.getOperation2());
				        SqlBuilderDTO.setSearch_type3(customreports.getOperation3());
				        
				        SqlBuilderDTO.setKeyword1(customreports.getCondition_value1());
				        SqlBuilderDTO.setKeyword1(customreports.getCondition_value2());
				        SqlBuilderDTO.setKeyword1(customreports.getCondition_value3());
				        
				        TableClassResolver TABLE_CLASS_MAP = new TableClassResolver();
				        clazz = TABLE_CLASS_MAP.resolve(type);

				     
					}

					List<Map<String, String>> transactions = null;
					

			        	 if (clazz == null) {
			        		 return transactions;  
					     }
			        	
			            String sql =sqlBuilderService
			            .BuilderString()
			            .SELECT("*")
			            .FROM(type)
			            .ProcessId(id)
			            .FullSearch(SqlBuilderDTO).build();
			             List<?>	transaction  = sqlServices.getCustomSql(clazz,sql,10000);
			        	
			           transactions  = applyCustomPivotAndEDA(transaction,CustomReport,customreports);
			           return transactions;
				}
				
				
				
				
				public <T> List<Map<String, String>> applyCustomPivotAndEDA(List<T> rawResult, CustomReport CustomReport,customreport customreports) {
								
					List<Map<String, String>> edata = convertToMapList(rawResult);
						if (customreports  !=  null && CustomReport !=  null) {
									
									
									FilterResult(edata, customreports);
									
									if (customreports.getBinning() == 1) {
										 edata = CustomReport.PivotUtils(edata);
										 return edata;		 
									}else if (customreports.getBinning() == 2) {
										 
										 if(customreports.getBinning_range() != null && customreports.getBinning_title() != null) {
											 String[] range =  customreports.getBinning_range().split(",");
											 String[] title =  customreports.getBinning_title().split(",");
											 if(range.length == title.length ) {   
												 edata = CustomReport.EDAUtils(edata); 
											     return edata;	 
											 }
										 } 
							 }
								
					}
						
						
				    return edata;
				}

				public List<Map<String, String>> convertToMapList(List<?> data) {
				    List<Map<String, String>> result = new ArrayList<>();

				    for (Object obj : data) {
				        Map<String, String> map = new LinkedHashMap<>();

				        for (Field field : obj.getClass().getDeclaredFields()) {
				            field.setAccessible(true);
				            try {
				                Object value = field.get(obj);
				                map.put(field.getName(), value != null ? value.toString() : "");
				            } catch (IllegalAccessException ignored) {}
				        }

				        result.add(map);
				    }

				    return result;
				}
				
				
				
				
				public <T> List<T> applyPivotAndEDA(List<T> data, CustomReport CustomReport,customreport customreports) {
					
	
					if (customreports  !=  null && CustomReport !=  null) {
	
							 if (customreports.getBinning() == 1) {
								 data = CustomReport.PivotUtil(data);
								 return data;
							 }else if (customreports.getBinning() == 2) {
								 if(customreports.getBinning_range() != null && customreports.getBinning_title() != null) {
									 String[] range =  customreports.getBinning_range().split(",");
									 String[] title =  customreports.getBinning_title().split(",");
									 if(range.length == title.length ) {   
										 data = CustomReport.EDAUtil(data); 
									     return data;	 
									 }
								 }
						    }

					  } 
				    return data;
				}


				
				public List<Map<String, String>>  applyPivotAndEDA(List<Map<String, String>> edata, customreport customreports, CustomReport CustomReport, String type) {
				
					
					if (customreports.getFilter() != null && !customreports.getAnalysis().equals("-") ) {
				 		FilterResult(edata,customreports);
					}
					
					
					 System.out.println(" applyPivotAndEDA "+customreports.getBinning());
					
					if (customreports.getBinning() == 1) {
							 edata = CustomReport.PivotUtils(edata);
							 return edata;		 
				}else if (customreports.getBinning() == 2) {
							 
							 if(customreports.getBinning_range() != null && customreports.getBinning_title() != null) {
								 String[] range =  customreports.getBinning_range().split(",");
								 String[] title =  customreports.getBinning_title().split(",");
								 if(range.length == title.length ) {   
									 edata = CustomReport.EDAUtils(edata); 
								     return edata;	 
								 }
							 } 
				 }
					
					
						 return edata;
						 
				}

			
			


				private void FilterResult(List<Map<String, String>> edata, customreport customreports) {
				   
					String groupBy = customreports.getFilter(); // เช่น product_code,lot_batch
				    String keyToAggregate = customreports.getFilter_data(); // เช่น summarize
				    String aggType = customreports.getAnalysis(); // เช่น Sum, Avg, Max, Min

				    if (groupBy == null || keyToAggregate == null || aggType == null) return;

				   
				    
				    String dateField = customreports.getFilter_date();
				    String dateGroup = customreports.getDate_group();

				    if (dateField != null && dateGroup != null && !dateGroup.equals("-")) {
				        for (Map<String, String> row : edata) {
				            String originalDate = row.get(dateField);
				            String groupedDate = convertDateByGroup(originalDate, dateGroup);
				            row.put(dateField, groupedDate); // แทนค่าด้วย format ใหม่
				        }
				        
				        groupBy = groupBy+","+dateField;
				    }
				    
				   
				    
				    List<String> groupFields = Arrays.stream(groupBy.split(","))
				            .map(String::trim)
				            .filter(s -> !s.isEmpty())
				            .collect(Collectors.toList());

				    List<Map<String, String>> result = null;

				    switch (aggType.toLowerCase()) {
				        case "sum":
				            result = sumGroup(edata, groupFields, keyToAggregate);
				            break;
				        case "average":
				            result = avgGroup(edata, groupFields, keyToAggregate);
				            break;
				        case "minimum":
				            result = minGroup(edata, groupFields, keyToAggregate);
				            break;
				        case "maximum":
				            result = maxGroup(edata, groupFields, keyToAggregate);
				            break;
				        case "count":
				        	 keyToAggregate = "count";
				            result = countGroup(edata, groupFields, keyToAggregate);
				            break;
				        default:
				            return; // ไม่รองรับประเภทนี้
				    }

				    
	
		        		

				    
				    
				    
				    if (result != null) {
				        edata.clear();
				        edata.addAll(result);
				    }
				}

				private List<Map<String, String>> sumGroup(List<Map<String, String>> data, List<String> groupFields, String targetField) {
				    return groupByFirstRowAndAggregate(data, groupFields, targetField, "sum");
				}

				private List<Map<String, String>> avgGroup(List<Map<String, String>> data, List<String> groupFields, String targetField) {
				    return groupByFirstRowAndAggregate(data, groupFields, targetField, "avg");
				}

				private List<Map<String, String>> minGroup(List<Map<String, String>> data, List<String> groupFields, String targetField) {
				    return groupByFirstRowAndAggregate(data, groupFields, targetField, "min");
				}

				private List<Map<String, String>> maxGroup(List<Map<String, String>> data, List<String> groupFields, String targetField) {
				    return groupByFirstRowAndAggregate(data, groupFields, targetField, "max");
				}
				private List<Map<String, String>> countGroup(List<Map<String, String>> data, List<String> groupFields, String targetField) {
				    return groupByFirstRowAndAggregate(data, groupFields, targetField, "count");
				}
				
				
				
				private List<Map<String, String>> groupByFirstRowAndAggregate(
					    List<Map<String, String>> data,
					    List<String> groupFields,
					    String targetField,
					    String aggType
					) {
					    Map<String, Map<String, String>> groupedFirstRow = new LinkedHashMap<>();
					    Map<String, List<Double>> groupedValues = new HashMap<>();

					    for (Map<String, String> row : data) {
					        // สร้าง group key เช่น "P2025061||B-2506165"
					        String groupKey = groupFields.stream()
					                .map(f -> row.getOrDefault(f, ""))
					                .collect(Collectors.joining("||"));

					        // เก็บแถวแรกของแต่ละกลุ่ม
					        groupedFirstRow.putIfAbsent(groupKey, new LinkedHashMap<>(row));

					        // แปลงค่าที่จะ aggregate ให้เป็น Double
					        double value = 0.0;
					        try {
					            value = Double.parseDouble(row.getOrDefault(targetField, "0"));
					        } catch (Exception ignored) {}

					        // เก็บค่าเพื่อ aggregate ทีหลัง
					        groupedValues
					            .computeIfAbsent(groupKey, k -> new ArrayList<>())
					            .add(value);
					    }

					    // สร้างผลลัพธ์
					    List<Map<String, String>> result = new ArrayList<>();
					    for (String groupKey : groupedFirstRow.keySet()) {
					        Map<String, String> row = groupedFirstRow.get(groupKey);
					        List<Double> values = groupedValues.getOrDefault(groupKey, List.of());

					        double resultValue = 0.0;
					        switch (aggType.toLowerCase()) {
					            case "sum":
					                resultValue = values.stream().mapToDouble(Double::doubleValue).sum();
					                break;
					            case "avg":
					                resultValue = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
					                break;
					            case "min":
					                resultValue = values.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
					                break;
					            case "max":
					                resultValue = values.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
					                break;
					            case "count":
					                resultValue = values.size(); // ← ✅ นับจำนวนแถวในกลุ่ม
					                break;
					        }

					        
					        DecimalFormat df = new DecimalFormat("#.##"); // ทศนิยม 2 ตำแหน่ง (ปัดเศษ)
					        row.put(targetField, df.format(resultValue));
					        
					        // ใส่ค่าที่ aggregate แล้ว กลับไปที่ field เดิม
					        //row.put(targetField, String.valueOf(resultValue));
					        result.add(row);
					    }

					    return result;
					}
				
				
				
				private String convertDateByGroup(String dateStr, String groupType) {
				    if (dateStr == null || dateStr.isBlank()) return "";

				    try {
				        LocalDateTime dateTime = LocalDateTime.parse(dateStr); // ISO-8601 format
				        switch (groupType) {
				            case "DAY":
				                return dateTime.toLocalDate().toString(); // yyyy-MM-dd
				            case "MONTH":
				                return dateTime.getYear() + "-" + String.format("%02d", dateTime.getMonthValue());
				            case "YEAR":
				                return String.valueOf(dateTime.getYear());
				            default:
				                return dateStr;
				        }
				    } catch (DateTimeParseException e) {
				        return dateStr; // กรณี parse ไม่ได้ ก็คืนเดิมไว้ก่อน
				    }
				}
				
				
				public void JoinTable(List<?> transaction,
						List<?> my_transaction, ReportJoinConfig config) {
					
					
					System.out.println(" transaction "+transaction.size());
					System.out.println(" my_transaction "+my_transaction.size());
					
					String mainKey1 =  Otherservice.splitToString(config.getKey1Table1(),"-",1);
					String subKey1 = Otherservice.splitToString(config.getKey1Table2(),"-",1);
					String mainKey2 = Otherservice.splitToString(config.getKey2Table1(),"-",1); 
					String subKey2 = Otherservice.splitToString(config.getKey2Table2(),"-",1);
					String mainKey3 = Otherservice.splitToString(config.getKey3Table1(),"-",1); 
					String subKey3 = Otherservice.splitToString(config.getKey3Table2(),"-",1);

					for (Object mainRow : transaction) {
						
						
						
						
					    List<?> matchedList = my_transaction.stream()
					        .filter(subRow -> {
					            boolean match = true;

					            if (mainKey1 != null && subKey1 != null) {
					                Object mainVal = getFieldValueByGetter(mainRow, mainKey1);
					                Object subVal = getFieldValueByGetter(subRow, subKey1);
					                match &= Objects.equals(mainVal, subVal);
					            }
					            if (mainKey2 != null && subKey2 != null) {
					                Object mainVal = getFieldValueByGetter(mainRow, mainKey2);
					                Object subVal = getFieldValueByGetter(subRow, subKey2);
					                match &= Objects.equals(mainVal, subVal);
					            }
					            if (mainKey3 != null && subKey3 != null) {
					                Object mainVal = getFieldValueByGetter(mainRow, mainKey3);
					                Object subVal = getFieldValueByGetter(subRow, subKey3);
					                match &= Objects.equals(mainVal, subVal);
					            }

					            return match;
					        })
					        .collect(Collectors.toList());

					    System.out.println(" matchedList "+matchedList.size());
					    
						    if (mainRow instanceof Identifiable joinable) {
						    	
							    System.out.println(" getId "+joinable.getId());
							    List<Object> result = matchedList.stream().collect(Collectors.toList());
						    	
						    	if(joinable.getJoin1() == null) {
						    		joinable.setJoin1(result);
						    	}else {
						    		joinable.setJoin2(result);
						    	}
						    	
					
						    }
					}
					
					
				}



				public void getModel(List<?> transaction, List<edata> edata, ModelMap model) {
				 	model.addAttribute("transaction",transaction); 
				 	model.addAttribute("edata",edata); 
				}
				
				public static Object getFieldValueByGetter(Object obj, String fieldName) {
				    try {
				        String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
				        Method method = obj.getClass().getMethod(methodName);
				        return method.invoke(obj);
				    } catch (Exception e) {
				        throw new RuntimeException("Cannot call getter: " + fieldName, e);
				    }
				}



				public void getModel(List<?> transaction, List<edata> edata, Map<String, List<keymap>> keymapChainMap, ModelMap model) {
				 	model.addAttribute("transaction",transaction); 
				 	model.addAttribute("edata",edata); 
				 	model.addAttribute("keymapChainMap",keymapChainMap); 
				}
				
				
				
				
				
				public Object convertToFieldType(Class<?> type, String value) {
				    if (type == String.class) {
				        return value;
				    } else if (type == int.class || type == Integer.class) {
				        return value.isEmpty() ? 0 : Integer.parseInt(value);
				    } else if (type == double.class || type == Double.class) {
				        return value.isEmpty() ? 0.0 : Double.parseDouble(value);
				    } else if (type == boolean.class || type == Boolean.class) {
				        return Boolean.parseBoolean(value);
				    } else if (type == Date.class) {
				        try {
				            return new SimpleDateFormat("yyyy-MM-dd").parse(value);
				        } catch (ParseException e) {
				            return null;
				        }
				    }
				    return null;
				}



			
			
				
				
				
				
				

}






