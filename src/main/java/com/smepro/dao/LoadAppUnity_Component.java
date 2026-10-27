package com.smepro.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.google.common.util.concurrent.AtomicDouble;
import com.smepro.dto.DocDTO;
import com.smepro.entity.sales_followup;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Followup;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.FieldChange;
import com.smepro.models.Goal;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Idoc;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.SubData;
import com.smepro.models.bom;
import com.smepro.models.dataset_data;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_production_record;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.edata_stock;
import com.smepro.models.eformfield;
import com.smepro.models.flow;
import com.smepro.models.keymap;
import com.smepro.models.levenshtein;
import com.smepro.models.other;
import com.smepro.models.process;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.qci;
import com.smepro.models.registers;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.upload_log;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.service.FlowServices;
import com.smepro.service.LogService;
import com.smepro.service.PlatformService;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class LoadAppUnity_Component {

	
	private  Map<String, Object> modelData;
	
	
	private edataDAO EdataDAO;
	private eformfieldDAO EformfieldDAO;
	
	
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	private jobDAO JobDAO;
	private otherservice otherservices;
	private State States = State.NONE;
	
	private Machinery_Factory Machinery_Factorys;
	private Sales_Customer Sales_Customers;
	private Finance_Accounting Finance_Accountings;
	private HR_Workforce HR_Workforces;
	private Procurement_SupplyChain Procurement_SupplyChains;
	private Production Productions;
	private Stock_Warehouse Stock_Warehouses;
	private LogService logService;
	
	
	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}

	
	  @Autowired
	public void setMyRepository(
	    		edataDAO EdataDAO,
	    		userDAO UserDAO,
	    		upload_pnDAO Upload_pnDAO,
	    		userservice Userservice,
	    		conditionDAO ConditionDAO,
	    		FlowServices flowServices,
	    		datasetDAO DatasetDAO,
	    		dataset_dataDAO Dataset_dataDAO,
	    		workflowDAO WorkflowDAO,
	    		jobDAO JobDAO,
	    		otherservice otherservices,
	    		Machinery_Factory Machinery_Factorys,
	    		Sales_Customer Sales_Customers,
	    		Finance_Accounting Finance_Accountings,
	    		HR_Workforce HR_Workforces,
	    		Procurement_SupplyChain Procurement_SupplyChains,
	    		Production Productions,
	    		Stock_Warehouse Stock_Warehouses,
	    		eformfieldDAO EformfieldDAO,
	    		 LogService logService
	    		) { 
		  
		 

		  
	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;
	        this.Upload_pnDAO = Upload_pnDAO;
	        this.flowServices = flowServices;
	        this.Userservice = Userservice;
	        this.ConditionDAO = ConditionDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.Dataset_dataDAO = Dataset_dataDAO;
	        this.WorkflowDAO = WorkflowDAO;
	        
	        this.JobDAO = JobDAO;
	        this.otherservices = otherservices;
	        
	        this.Machinery_Factorys = Machinery_Factorys;
	        this.Sales_Customers = Sales_Customers;
	        this.Finance_Accountings = Finance_Accountings;
	        this.HR_Workforces = HR_Workforces;
	        this.Procurement_SupplyChains = Procurement_SupplyChains;
	        this.Productions = Productions;
	        this.Stock_Warehouses = Stock_Warehouses;
	        this.EformfieldDAO = EformfieldDAO;
	        this.logService = logService;
	    }

	  
	  
	
	private int id;
	private String db;
	private String subdb;
	private String maindata;
	private String filtermain;
	private String subdata;
	private String filtersub;
	private String maindatacolumn;
	private String autoiddatacolumn;
	private String subdatacolumn;
	private List<keymap> keymaps;
	private  Idoc docs;
	private user users;
	private sub_processes subprocesses;
	private List<String> ability = new ArrayList<String>();
	private LoadApp app = new LoadApp();
	
	
	
	
		public  LoadAppUnity_Component initiate( Map<String, Object> modelData){
			 this.modelData  = modelData;
			 return this;
		 }
		
		
		public LoadAppUnity_Component LoadTransaction() {
			 System.err.println("LoadApp_Component ->LoadTransaction "); 
			 
			 System.err.println("LoadApp_Component ->edata "+this.app.getEdata()); 
			 
			LoadTransactions(this.app);
			return  this;
		}


		public LoadAppUnity_Component LoadFollowup() {
			
			 System.err.println("LoadApp_Component ->LoadFollowup "); 
			
			
			user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			List<edata> datefollow =  EdataDAO.getDateFollow(usersed.getUser_id());
		
			exposeAs("datefollow",datefollow);
			

			
			return  this;
		}

		
		public LoadAppUnity_Component LoadCompare() {
			
			List<FieldChange> diffList = Productions.LoadCompare(this.app);
			exposeAs("diffList",diffList);
			return  this;
		}
		
		
		

		public LoadAppUnity_Component  LoadData(LoadApp LoadApp) {
			

				clear();
				System.err.println("LoadApp -> "+LoadApp);
				
			    System.err.println("LoadApp -> ");
				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				
				LoadApp.setUser(usersed);
				
				
				process processes =  LoadApp.getProcess();
				
				System.err.println("LoadApp processes-> "+processes.toString());
				
				
				List<sub_processes> status =  processes.getSub_processes();
				
			
				
				
				workflow workflow =  LoadApp.getWorkflow();

				
				
				setDb(workflow.getWorkflow_maindb());
				setSubdb(workflow.getWorkflow_subdb());
			
				
				setKeymaps(LoadApp.getKeymap());
				setUsers(usersed);
				
				sub_processes sub_processes = new sub_processes();
				 Idoc Idocs = null;
				 String nextstpe = "End_Process";
				 int nextstpe_id = 0;
				 
				 
				 
				if (LoadApp.getEdata() != null) {
					
					setId(LoadApp.getEdata().getData_id());
					
					
				    System.err.println("LoadApp -> getEdata");

				    // ตรวจสอบและสร้าง Workflow ถ้ายังไม่มี
				    Idocs = flowServices.checkAndCreateWorkflow(LoadApp.getEdata().getData_id(), usersed.getUser_id());
				    System.err.println("LoadApp -> getEdata -> Idocs " + Idocs.getId());
				    setDocs(Idocs);

				        
				    System.err.println("LoadApp -> getEdata -> Idocs->nextstpe 1 ");
				    
				    // กำหนดค่า sub_processes ตามขั้นตอนปัจจุบัน
				    int currentStep = Idocs.getCurrent_step_int();
				    if (currentStep >= 0 && currentStep < status.size()) {
				        sub_processes = status.get(currentStep);
				        System.err.println("LoadApp -> getEdata -> Idocs->nextstpe 2 ");
				        
				    } else {
				        sub_processes = status.get(0); // fallback กรณี index ผิดพลาด
				        System.err.println("LoadApp -> getEdata -> Idocs->nextstpe 3 ");
				    }
				    
				    
				    if(status.size() > currentStep+1) {
				    	nextstpe = status.get(currentStep+1).getStep();
				    	nextstpe_id= status.get(currentStep+1).getId();
				    	
				    	 System.err.println("LoadApp -> getEdata -> Idocs->nextstpe 4 ");
				    }
				    
				    
				
				    
				    System.err.println("LoadApp -> getEdata -> Idocs->nextstpe ");
				    
				} else if (!status.isEmpty()) {
				    // ถ้าไม่มี LoadApp แต่ status มีค่า ให้ใช้ค่าแรกสุด
				    sub_processes = status.get(0);

				    if(status.size() > 0) {
				    	nextstpe = status.get(1).getStep();
				    	nextstpe_id = status.get(1).getId();
				   
				    }
				    
				    
				    // หา Autoiddatacolumn ถ้ามี
				    Optional<sub_processes> autoIdColumnOpt = status.stream()
				        .filter(row -> row.getAutoiddatacolumn() != null)
				        .findFirst();
				    autoIdColumnOpt.ifPresent(condition -> setAutoiddatacolumn(condition.getAutoiddatacolumn()));
				}

				// อัปเดต model attribute
				if (sub_processes != null) {
					
					
				    exposeAs("Nextstpe_id",nextstpe_id);
				    exposeAs("Nextstpe",nextstpe);
				    exposeAs("Flow","flow");
				    exposeAs("Appstep", sub_processes.getStep());
				    exposeAs("sub_processes", sub_processes);
				    exposeAs("Appstep_no", (LoadApp.getEdata() != null) ? Idocs.getCurrent_step_int() : 0);
				    
				    

				    // กำหนดค่าที่เกี่ยวข้องกับ sub_processes
				    setMaindata(sub_processes.getMaindatacolumn());
				    setFiltermain(sub_processes.getFiltermain());
				    setSubdata(sub_processes.getSubdatacolumn());
				    setFiltersub(sub_processes.getFiltersub());
				    setMaindatacolumn(sub_processes.getMaindatacolumn());
				    setSubdatacolumn(sub_processes.getSubdatacolumn());
				    setSubprocesses(sub_processes);

				    LoadApp.setSub_processes(sub_processes);
				}
				
				
				  exposeAs("all_processes",processes.getSub_processes());
			
			     this.app = LoadApp;
			     Load_other_processes(this.app);	
			     
			     return this;
				  
			}
		
		

		
		
		
		
		
		
		
		
			private void Load_other_processes(LoadApp LoadApp) {
				
				
				if (LoadApp.getEdata() == null) { return; }

				switch (LoadApp.getSub_processes().getStep()) {
		        case "Review":
		        	
		        	 System.err.println("LoadApp -> Review");
		       
		        	//EditingAuthority();	
				        	
				        	
		            break;    
		        case "Production":
	
		            break;   
		        default:
				}	
				
	
		}
	
		
			public LoadAppUnity_Component LoadAutoid() {
			
				
				try {
					
					
					//DisplayAutoid
				 	List<doc> docs = EdataDAO.getDoc(this.app.getEdata().getData_id());
		        
				 	int i = 1;
				 	for(doc doc : docs) { 
				 		
				 		String rv ="";
				 		String DOC ="";
				 		if(doc.getDoc_rv() > 0) { rv ="-R"+doc.getDoc_rv();  }
				 		if(i == 1) {
				 			DOC = doc.getDoc_type()+doc.getDoc_date()+"-"+doc.getDoc_autoid();
				 			exposeAs("Code_app",DOC);
				 		}else {
				 			DOC = doc.getDoc_type()+doc.getDoc_date()+"-"+doc.getDoc_autoid()+rv;
				 			exposeAs("Code_app2",DOC);
				 		}
				 		i++;	
				 		
				 		exposeAs("DOC_ID",DOC);
				 	}
				 	
				 	String DOC_ID = "DOC"; 
				 	if(docs.size() == 0) {
				 	 	
				 	 	exposeAs("Code_app","Auto-generated");
				 	 	
				 	 	if(this.app.getEdata() != null) {
				 	 		DOC_ID ="ID"+this.app.getEdata().getData_id();
				 	 	}
	
						exposeAs("DOC_ID",DOC_ID);
						exposeAs("Code_app2","Auto-generated");
					 	
				 	}
				 	if(docs.size() == 1) {
						exposeAs("Code_app2","Auto-generated");
				 	}
				
				 	
		
				}catch(Exception e) {
					exposeAs("Code_app","Auto-generated");
				}
	
				  return this;
			}

			
			
			
			
			public <T> LoadAppUnity_Component  LoadLog() {
							
			
				   	List<flow> log = WorkflowDAO.getAllFlow(id);
			    	log.forEach(row->{
			    		user send = UserDAO.getbyId(row.getFlow_send(), 0);
			    		if(send!= null) { row.setSend(send.getUser_name());}
			    		
			    		
			    		user to = UserDAO.getbyId(row.getFlow_to(), 0);
			    		if(to != null) {row.setTo(to.getUser_name());}
			    		
			    	});
			    		
			    
			    	
			    	exposeAs("log",log);
			    	
					return this;			
			}
			
			public <T> LoadAppUnity_Component  LoadGoal() {
				
				
				
				if(this.app.getEdata() == null) { return this; }
				
				
				Goal goals = WorkflowDAO.getFindGoal(this.app.getProcess().getId(),this.app.getUser().getUser_name());

				
		    	exposeAs("goals",goals);
		    	
					if(goals != null) {   
							     	if(goals.getFile_tag() != null) { 
							     		
							     		String dbValue = goals.getFile_tag();	
							     		
							     		
							     		
							     		String inputValue = this.app.getFile()
							     				.stream().filter(tag-> tag.getFile_tag() != null)
							     				.map(f -> f.getFile_tag().trim()).distinct()
							     				.collect(Collectors.joining(",")); 
							     		boolean isSame = otherservices.normalize(dbValue).equals(otherservices.normalize(inputValue));
							     		
							     		if(isSame) {
							     			EdataDAO.setCheckFile(this.app.getEdata().getData_id(),1);
							     	
							     			  exposeAs("files_tage",1);
							     			 
							     		}else {
							     			EdataDAO.setCheckFile(this.app.getEdata().getData_id(),0);
							     			
							     			 exposeAs("files_tage",0);
							     		}
							     		

							     		exposeAs("dbValue",goals.getFile_tag());
							     		
							     	}			
							     	
							     	upload_log upload_log = JobDAO.getResultGoal(goals.getId());	

							     	exposeAs("upload_log",upload_log);
							     	
							     	
					}
				return this;			
			}
		
			
			
				public <T> LoadAppUnity_Component  LoadLevenshtein() {
				
					if(this.app.getEdata() == null) { return this; }
					
					System.out.println(" LoadLevenshtein ");
					
					
					List<levenshtein> levenshtein  = Dataset_dataDAO.getAllLevenshteins(this.app.getEdata().getData_id());

			    	exposeAs("levenshtein",levenshtein);
					
					long count = levenshtein.stream()
			                .filter(l -> l.getDistance() > 75)  // กรอง distance ที่ต่ำกว่า 5
			                .count(); 
					
					exposeAs("levenshtein_dangers",count);
					
					return this;			
					
					
					
				}
		
			
			
			
			public <T> LoadAppUnity_Component  Loadbatchdata() {
				
				
				List<edata> subDataList = EdataDAO.getAllSubData(this.app.getEdata().getData_id());
				if(subDataList.size() > 0) {
					exposeAs("subDataList",subDataList);
				}
				
				 if( this.app.getApp_model() == null) {
					 System.out.println(" Loadbatchdata model  "+this.app.getApp_model() );
						return this;
						
				 }
			  
				
				
				switch (this.app.getApp_model()) {
					case "boms." -> Productions.LoadBatch_insert(this.app,"bom",this.modelData);
					case "production_lists." -> Productions.LoadBatch_insert(this.app,"production_list",this.modelData);
					case "production_directives." -> Productions.LoadBatch_insert(this.app,"production_directive",this.modelData);
					case "others." -> Productions.LoadBatch_insert(this.app,"other",this.modelData);
					case "production_standards." -> Productions.LoadBatch_insert(this.app,"production_standard",this.modelData);
				}	
				
				/*
				
				if(this.app.getSub_processes() == null ) { return this; }
				//if(this.app.getModel() == null ) { return this; }
			//	if(this.app.getSession() == null) { return this; }
					
				
				System.out.println("000----------------------Loadbatchdata");
				
				 System.out.println("000----------------------Loadbatchdata");
				
				Batch_modify(this.app);
				Batch_continue(this.app);
				Batch_clear(this.app);
				Batch_insert(this.app);
				Batch_dreference(this.app);
					
				System.out.println("app "+this.app.toString());
				*/
				return this;
				
			}
			
			
			
	
			
			
			
	
			
		public <T> LoadAppUnity_Component LoadbatchdatahasChanges() {
				
			switch (this.app.getApp_model()) {
				case "boms." -> Productions.LoadhasChanges(this.app,"bom",this.modelData);
				case "production_lists." -> Productions.LoadhasChanges(this.app,"production_list",this.modelData);
				case "production_directives." -> Productions.LoadhasChanges(this.app,"production_directive",this.modelData);
				case "others." -> Productions.LoadhasChanges(this.app,"other",this.modelData);
				case "production_standards." -> Productions.LoadhasChanges(this.app,"production_standard",this.modelData);
			}	
				return this;
		
			}
		
			
			



		public <T> void LoadbatchSubdata(LoadApp LoadApp) {
				
				if(LoadApp.getProcess() == null ) { return; }
				if(LoadApp.getWorkflow() == null ) { return; }

		
				 	switch (LoadApp.getWorkflow().getWorkflow_maindb()) {
					case "Production":
						
						
						System.out.println(" Production ->LoadbatchSubdata" );
						System.out.println(" Production ->Activate_action  "+LoadApp.getActivate_action());
						
						
						SubBatchBom(LoadApp);
						SubBatchPD(LoadApp);
						SubBatchEPR(LoadApp);
					    SubBatchQCI(LoadApp);
						SubBatchOther(LoadApp);
						
					
						
						
						
						
						

						break;
					case "Stock_Warehouse":
						
						System.out.println(" Stock_Warehouse ->Stock_Warehouse" );
						System.out.println(" Stock_Warehouse ->Activate_action  "+LoadApp.getProcessing_count());
						
						
						SubBatchIssue_fg(LoadApp);
						SubBatchInventory_transfer_fg(LoadApp);
						SubBatchGoods_receipt_fg(LoadApp);
						SubBatchAdjustment_fg(LoadApp);
						
						SubBatchIssue_sk(LoadApp);
						SubBatchInventory_transfer_sk(LoadApp);
						SubBatchGoods_receipt_sk(LoadApp);
						SubBatchAdjustment_sk(LoadApp);
						
						
						break;
					case "production_record":
						break;	
						
						
			        default:	
			        	System.err.println(" LoadbatchSubdata ->not found" );
			        	return;
			      }	
					
					
		
			}
	
	
	public <T> void LoadbatchSubAttachdocuments(LoadApp LoadApp) {
		
		
				 	if(LoadApp.getBom() != null) {
				 	
				 		SubBatchBom(LoadApp);
				 	}	
				
			}
	
	
	
				private void SubBatchAdjustment_sk(LoadApp LoadApp) {
					if(LoadApp.getActivate_action()  == null) { return; }
					if(!LoadApp.getActivate_action().equals("Adjustment_sk")) { return; }
					
			
					    		List<Adjustment_sk> item_batch2 =  EdataDAO.geteAllAdjustment_sk(LoadApp.getWorkflow_refer());
					    		FilterLooselink(LoadApp,item_batch2);
					    	
					    		exposeAs("item_batch",item_batch2);
					}
				
					private void SubBatchAdjustment_fg(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Adjustment_fg")) { return; }
						
				
						    		List<Adjustment_fg> item_batch2 =  EdataDAO.geteAllAdjustment_fg(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						    	
						    		exposeAs("item_batch",item_batch2);
						}
	
					private void SubBatchInventory_transfer_sk(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Inventory_transfer_sk")) { return; }
						
	
						    		List<Inventory_transfer_sk> item_batch2 =  EdataDAO.geteAllInventory_transfer_sk(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						   
						    		exposeAs("item_batch",item_batch2);
						}
					private void SubBatchInventory_transfer_fg(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Inventory_transfer_fg")) { return; }
						
	
						    		List<Inventory_transfer_fg> item_batch2 =  EdataDAO.geteAllInventory_transfer_fg(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						  	
						    		exposeAs("item_batch",item_batch2);
						}
					

					private void SubBatchIssue_sk(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Issue_sk")) { return; }
						
	
						    		List<Issue_sk> item_batch2 =  EdataDAO.geteAllIssue_sk(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						    	
						    		exposeAs("item_batch",item_batch2);
						}
					private void SubBatchIssue_fg(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Issue_fg")) { return; }
						
	
						    		List<Issue_fg> item_batch2 =  EdataDAO.geteAllIssue_fg(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						    		
						    		exposeAs("item_batch",item_batch2);
						}
					private void SubBatchGoods_receipt_sk(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Goods_receipt_sk")) { return; }
						
	
						    		List<Goods_receipt_sk> item_batch2 =  EdataDAO.geteAllGoods_receipt_sk(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						    
						    		exposeAs("item_batch",item_batch2);
						}

					private void SubBatchGoods_receipt_fg(LoadApp LoadApp) {
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("Goods_receipt_fg")) { return; }
						
	
						    		List<Goods_receipt_fg> item_batch2 =  EdataDAO.geteAllGoods_receipt_fg(LoadApp.getWorkflow_refer());
						    		FilterLooselink(LoadApp,item_batch2);
						    
						    		exposeAs("item_batch",item_batch2);
						}
	
					
					
					
					
				private void SubBatchBom(LoadApp LoadApp) {
			
					if(LoadApp.getSubaction() == null) { return; }
					if(!LoadApp.getSubaction().equals("Bom")) { return; }
					
					//if(LoadApp.getEdata().getAttach_type() == null) { return; }
					
			
					
					
					System.out.println("Edata  "+LoadApp.getEdata().getId());
					System.out.println("SubBatchBom  "+LoadApp.getEdata().getAttach_type());
					System.out.println("SubBatchBom  ");
	
					
					System.out.println("Edata_id  "+LoadApp.getBom().getEdata_id());
					System.out.println("category  "+LoadApp.getBom().getBom_category());
					
					
					System.out.println("getTempId  "+LoadApp.getBom().getTempId());
					System.out.println("getBom_category  "+LoadApp.getBom().getBom_category());
					
					
				    Double totals = LoadApp.getBom().getBom_base_quantity(); // จำนวนที่ต้องการผลิต
				    List<bom> item_batch = EdataDAO.getAllEdata_bom_item_category(
				    		LoadApp.getBom().getTempId(), 
				    		LoadApp.getBom().getBom_category()
				    );
			
				    
					System.out.println("SubBatchBom size "+item_batch.size() ); 
				    
				    if (item_batch.size() > 0) {
				        item_batch.forEach(row -> {
				            double totalBaseQty = row.getBom_base_quantity() * totals;
				            double totalWastage = row.getBom_wastage() * totals;
				            double totalPrice = (totalBaseQty + totalWastage) * row.getBom_price();
			
				            double formattedTotals = Math.round(totalPrice * 100.0) / 100.0;
				            row.setBom_total_cost(formattedTotals);
			
				            // Optional: ตั้งค่าค่าใหม่ให้ row ถ้าต้องการเก็บ base และ wastage ที่คูณแล้ว
				            row.setBom_base_quantity(totalBaseQty);
				            row.setBom_wastage(totalWastage);
				        });
			
				        double grandtotal = item_batch.stream()
				            .mapToDouble(e -> 
				                (e.getBom_base_quantity() + e.getBom_wastage()) * e.getBom_price()
				            )
				            .sum();
			
				        BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
				            .setScale(2, RoundingMode.HALF_UP);
			
				     	exposeAs("grandtotal", Grandtotal);
				        FilterLooselink(LoadApp,item_batch);	
				     
				        exposeAs("item_batch", item_batch);
				    }
				}
	
	
				
					private void SubBatchQCI(LoadApp LoadApp) {
					
					if(LoadApp.getActivate_action() == null) { return; }
					if(!LoadApp.getActivate_action().equals("QCI")) { return; }
					
	
				    		List<qci> item_batch =  EdataDAO.geteAllQCI(LoadApp.getWorkflow_refer());
				    		FilterLooselink(LoadApp,item_batch);	
				    	   
				    	    exposeAs("item_batch",item_batch);
				    	
						}
					
					

					private void SubBatchOther(LoadApp LoadApp) {
						
						
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("OTHER")) { return; }
						
						
						 System.err.println("LoadApp ->getAction_count-> "+getDocs().getEform_id()); 
						 System.err.println("LoadApp ->Workflow-> "+LoadApp.getWorkflow_refer()); 
					    
					
					    			
						    		List<other> item_batch2 =  EdataDAO.geteAllOther(LoadApp.getWorkflow_refer());
						    		
				
						    		// ใช้ ConcurrentMap เพื่อรองรับการใช้งานในหลาย thread ได้ด้วย (ถ้จำเป็น)
						    		Map<String, AtomicDouble> totalOutputMap = new HashMap<>();
						    		Map<String, AtomicDouble> totalDefectiveMap = new HashMap<>();
						    		

				
						    		
					
						    	     
						    	     
						    		item_batch2.forEach(row -> {
						    			String step =  otherservices.DefaultValueString(row.getLot_batch());
						    	         Double Order = otherservices.DoubleValueOf(row.getOrder_quantity());
						    	         
						    		    // Get หรือสร้าง AtomicInteger ใหม่ถ้าไม่มีใน Map
						    		    AtomicDouble  outputSum = totalOutputMap.computeIfAbsent(step, k -> new AtomicDouble (0));
						    		    AtomicDouble  defectiveSum = totalDefectiveMap.computeIfAbsent(step, k -> new AtomicDouble (0));

						    		    // สะสมค่าตาม process_step
						    		    Double totalOutput = outputSum.addAndGet(row.getReceived_today());
						    		    Double totalDefective = defectiveSum.addAndGet(row.getPaid_today());

						    		    row.setReceived_accumulated(totalOutput);
						    		    row.setPaid_accumulated(totalDefective);

						    		    // คำนวณ outstanding แบบรายขั้นตอน (ใช้ order_quantity ของแถวนั้น)
						    		    Double pending_received = Order  - totalOutput;
						    		    Double pending_paid = Order - totalOutput;
						    		    row.setPending_received(pending_received);
						    		    row.setPending_paid(pending_paid);
						    	
						    		});
						    		FilterLooselink(LoadApp,item_batch2);
						    		
						    	    exposeAs("item_batch",item_batch2);
					}
					
	
					
					private void SubBatchEPR(LoadApp LoadApp) {
						
						if(LoadApp.getActivate_action()  == null) { return; }
						if(!LoadApp.getActivate_action().equals("production_record")) { return; }
						
						List<edata_production_record> item_batch2 =  EdataDAO.geteAllproduction_records(LoadApp.getWorkflow_refer());
			    		
						
			    		// ใช้ ConcurrentMap เพื่อรองรับการใช้งานในหลาย thread ได้ด้วย (ถ้จำเป็น)
			    		Map<String, AtomicInteger> totalOutputMap = new HashMap<>();
			    		Map<String, AtomicInteger> totalDefectiveMap = new HashMap<>();
			    		
			    		item_batch2.forEach(row -> {
			    		    String step = row.getProcess_step();

			    		    // Get หรือสร้าง AtomicInteger ใหม่ถ้าไม่มีใน Map
			    		    AtomicInteger outputSum = totalOutputMap.computeIfAbsent(step, k -> new AtomicInteger(0));
			    		    AtomicInteger defectiveSum = totalDefectiveMap.computeIfAbsent(step, k -> new AtomicInteger(0));

			    		    // สะสมค่าตาม process_step
			    		    int totalOutput = outputSum.addAndGet(row.getActual_output());
			    		    int totalDefective = defectiveSum.addAndGet(row.getDefective_output());

			    		    row.setTotal_output(totalOutput);
			    		    row.setTotal_defective(totalDefective);

			    		    // คำนวณ outstanding แบบรายขั้นตอน (ใช้ order_quantity ของแถวนั้น)
			    		    int outstanding = row.getOrder_quantity() - totalOutput;
			    		    row.setOutstanding(outstanding);
			    	
			    		});
			    		
			    		FilterLooselink(LoadApp,item_batch2);
			    		
			    		   exposeAs("item_batch",item_batch2);
			    		
			    		
					}	
					
				
				
			
				private List<DocDTO>  SubDataPD(LoadApp LoadApp) {
					
					List<production_directive> item_batch = SubBatchPD(LoadApp);
				
					List<DocDTO> docs= new ArrayList<DocDTO>();
				
					item_batch.stream().forEach(row->{
						DocDTO doc = new DocDTO();
						doc.setDoc_name(row.getProduct_name());
						doc.setDoc_id(row.getId());
						docs.add(doc);
					});
			    	return docs;
				}

				private List<production_directive>  SubBatchPD(LoadApp LoadApp) {
					
					//if(LoadApp.getActivate_action()  == null) { return; }
					//if(!LoadApp.getActivate_action().equals("PD")) { return; }
					        
				
				    		List<production_directive> item_batch =  EdataDAO.geteAllPD(LoadApp.getWorkflow_refer());
				    		item_batch.forEach(row->{
				    				edata_production_record totalrecord =  EdataDAO.getSumProduction_records(LoadApp.getWorkflow_refer());
				    				row.setActual_output(String.valueOf(totalrecord.getTotal_output()));
				    				row.setDefective_output( String.valueOf(totalrecord.getTotal_defective()));
				    		});
				    			
				    		FilterLooselink(LoadApp,item_batch);	
				    	    exposeAs("item_batch",item_batch);
				    	 return item_batch;
				}
				
				
				
				
				private void Batch_dreferencehasChanges(LoadApp LoadApp) {
					
					if(LoadApp.getBatch_insert()  == null) { return; }
					if(LoadApp.getBatch_insert().getAction_batch_dreference() == null) { return; }
				
					 System.out.println("001----------------------Batch_dreference");
					
					 
					if(
							LoadApp.getBatch_insert().getAction_batch_dreference().contains("bom_description")
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("bom_base_quantity")
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("bom_base_unit")
							) {
						 System.out.println("001----------------------Batch_dreference 2");
						 
							 
						    if(LoadApp.getBom()  == null) { return; }
							
							  if(LoadApp.getBom().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getBom().setId(randomNegative);
							}
							
						Batch_process(LoadApp,"bom");
						
						

						
					}
					else if(
							LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_string1") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_string2") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_number1") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_number2") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("ps_id") 
							) {
						
						 System.out.println("001----------------------Batch_insert 2");
						 
				
						  if(LoadApp.getProduction_standard()  == null) { return; }
							if(LoadApp.getProduction_standard().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getProduction_standard().setId(randomNegative);
							}
						 
						Batch_process(LoadApp,"production_standard");
					}
				
			
					
			
				}
				
				
				
				
				
				
				private void Batch_dreference(LoadApp LoadApp) {
					
			
					
					
					if(LoadApp.getBatch_insert()  == null) { return; }
					if(LoadApp.getBatch_insert().getAction_batch_dreference() == null) { return; }
				
					 System.out.println("001----------------------Batch_dreference");
					
					 
					if(
							LoadApp.getBatch_insert().getAction_batch_dreference().contains("bom_description")
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("bom_base_quantity")
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("bom_base_unit")
							) {
						 System.out.println("001----------------------Batch_dreference 2");
						 
		
						  System.out.println("001----------------------Batch_dreference DisplayActionbatch");
						    DisplayActionbatch(LoadApp,"bom");
						    System.out.println("001----------------------Batch_dreference bom");
							 
						    if(LoadApp.getBom()  == null) { return; }
							
							  if(LoadApp.getBom().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getBom().setId(randomNegative);
							}
							  System.out.println("001----------------------bom Batch_process");
						Batch_process(LoadApp,"bom");
					}
					else if(
							LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_string1") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_string2") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_number1") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("title_number2") 
							|| LoadApp.getBatch_insert().getAction_batch_dreference().contains("ps_id") 
							) {
						
						 System.out.println("001----------------------Batch_insert 2");
						 
						  DisplayActionbatch(LoadApp,"production_standard");
						 
						  if(LoadApp.getProduction_standard()  == null) { return; }
							if(LoadApp.getProduction_standard().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getProduction_standard().setId(randomNegative);
							}
						 
						Batch_process(LoadApp,"production_standard");
					}
				
			
					
			
				}
				
				
				private <T> void FilterLooselink(LoadApp LoadApp, List<T> item_batch2) {
				    if (LoadApp.getLooselink() == null) { 
				        return; 
				    }

				    List<keymap> keymaps = EdataDAO.getAllKeyMaps(LoadApp.getProcess().getId());

				    eformfield eformfield1 = EformfieldDAO.findById(LoadApp.getLooselink().getLoose_link_scope1());
				    Optional<keymap> keymap1 = keymaps.stream()
				        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == eformfield1.getField_id())
				        .findFirst();

				    eformfield eformfield2 = EformfieldDAO.findById(LoadApp.getLooselink().getLoose_link_scope2());
				    Optional<keymap> keymap2 = keymaps.stream()
				        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == eformfield2.getField_id())
				        .findFirst();

				    String key1 = LoadApp.getLooselink().getLoose_link_key1();
				    String key2 = LoadApp.getLooselink().getLoose_link_key2();

				    String scope1 = keymap1.map(k -> k.getMainkey())
				                            .orElse("data_value" + eformfield1.getField_postion());

				    String scope2 = keymap2.map(k -> k.getMainkey())
				                            .orElse("data_value" + eformfield2.getField_postion());

				    // กรอง list
				    List<T> filtered = item_batch2.stream().filter(item -> {
				        try {
				            boolean match = true;

				            if (scope1 != null && !scope1.isEmpty() && key1 != null && !key1.isEmpty()) {
				                Field field1 = item.getClass().getDeclaredField(scope1);
				                field1.setAccessible(true);
				                Object value1 = field1.get(item);
				                match &= Objects.equals(value1, key1);
				            }

				            if (scope2 != null && !scope2.isEmpty() && key2 != null && !key2.isEmpty()) {
				                Field field2 = item.getClass().getDeclaredField(scope2);
				                field2.setAccessible(true);
				                Object value2 = field2.get(item);
				                match &= Objects.equals(value2, key2);
				            }

				            return match;

				        } catch (NoSuchFieldException | IllegalAccessException e) {
				            e.printStackTrace();
				            return false;
				        }
				    }).collect(Collectors.toList());

				    // อัพเดตผลกรองกลับเข้า item_batch2
				    item_batch2.clear();
				    item_batch2.addAll(filtered);
				}
				
				
				private void  LoadFields(LoadApp LoadApp) {
					/*
					  	List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
				    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(processes_id);
				    	
				      	
				    	eformfields.stream().forEach(row->{
						    	Optional<keymap> data =	keymaps.stream().filter(subrow-> Integer.valueOf(subrow.getEformkey())  == row.getField_id() ).findFirst();
						    	
							    	if(data.isPresent()) {
							    		
							    		keymap keymap = data.get();
							    		row.setKeymap(keymap);
							    		
							    		List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(),keymap.getFilter_enum());
							    		row.setDataset_data(dataset_data);
							    		
							    		
							    	}
							    	
							    	
							    	
							    	
				    	}); 
				    	model.addAttribute("subeformfields",eformfields);
				*/
				}
				
				
				
				
				/*
				private void Batch_inserthasChanges(LoadApp LoadApp) {
					
					if(LoadApp.getBatch_insert()  == null) { return; }
					if(LoadApp.getBatch_insert().getAction_batch_insert() == null) { return; }
					

					 switch (LoadApp.getWorkflow().getWorkflow_maindb()) {
					case "Production":
						//Productions.Batch_inserthasChanges(LoadApp,this.modelData);
						break; 	 
					case "Procurement_SupplyChain":
						Procurement_SupplyChains.Batch_inserthasChanges(LoadApp);
						break; 	 
					case "Machinery_Factory":	  
						Machinery_Factorys.Batch_inserthasChanges(LoadApp);
						 break; 	 
					case "Sales_Customer":	  
						Sales_Customers.Batch_inserthasChanges(LoadApp);
						 break; 	 
					case "Finance_Accounting":	  
						Finance_Accountings.Batch_inserthasChanges(LoadApp);
						 break; 	 
					case "HR_Workforce":	  
						HR_Workforces.Batch_inserthasChanges(LoadApp);
						 break; 	 
					case "Stock_Warehouse":	  
						Stock_Warehouses.Batch_inserthasChanges(LoadApp);
						 break; 
			        default:
			        	
					}
					
					
					 
					 
			
			
					 System.out.println("000----------------------Batch_insert");
					
					if(LoadApp.getBatch_insert().getAction_batch_insert().contains("procurement_itemname")) {
						 System.out.println("000----------------------Batch_insert 2");

							if(LoadApp.getEdata_procurements()  == null) { return; }
						 
						 
							if(LoadApp.getEdata_procurements().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getEdata_procurements().setId(randomNegative);
								Batch_process(LoadApp,"procurement");
							}
						
					}
					else if(LoadApp.getBatch_insert().getAction_batch_insert().contains("product_names")) {
						 System.out.println("000----------------------Batch_insert 3");
						 
						 
					
							if(LoadApp.getEdata_products()  == null) { return; }
						 
							if(LoadApp.getEdata_products().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getEdata_products().setId(randomNegative);
							}
						 
						Batch_process(LoadApp,"edata_product");
					}
			
					
					 System.out.println("getType " + LoadApp.getType());
					
					if(LoadApp.getType() == null ){ return; }

					if(LoadApp.getType().equals("production_list")) {
						
						System.out.println("getProduction_lists ----- " +LoadApp.getProduction_lists());
					  
					
						if(LoadApp.getProduction_lists()  == null) { return; }
						
						System.out.println("000----------------------Batch_insert 4");
							if(LoadApp.getProduction_lists().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getProduction_lists().setId(randomNegative);
							}
						 
						Batch_process(LoadApp,"production_list");
				
					
					
					}
					else if(LoadApp.getType().equals("production_directive")) {
						 System.out.println("000----------------------Batch_insert 5");
						 
						  
							if(LoadApp.getProduction_directives()  == null) { return; }
						 
							if(LoadApp.getProduction_directives().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getProduction_directives().setId(randomNegative);
							}
						Batch_process(LoadApp,"production_directive");
						
						
					}
					else if(LoadApp.getType().equals("other")) {
						 System.out.println("000----------------------Batch_insert 5");
						 
						   
							if(LoadApp.getOther()  == null) { return; }
						 
							if(LoadApp.getOther().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getOther().setId(randomNegative);
							}
						Batch_process(LoadApp,"other");
						
						
						
					}
					
					
					else if(LoadApp.getType().equals("bom")) {
						 System.out.println("000----------------------Batch_insert 5");
						 
						   
							if(LoadApp.getBom()  == null) { return; }
						 
							if(LoadApp.getBom().getId() == 0 ) {
								Random random = new Random();
						        int randomNegative = -random.nextInt(100);
								LoadApp.getBom().setId(randomNegative);
							}
						Batch_process(LoadApp,"bom");
						
						
						
					}
				
					
					
					
					
					
					System.out.println(" Type "+LoadApp.getType());
				}

					*/
				
				
				
				

				private void Batch_continue(LoadApp LoadApp) {
					if(LoadApp.getBatch_continue()  == null) { return; }
					if(LoadApp.getBatch_continue().getAction_batch_continue() == null) { return; }
				
					 System.out.println("000----------------------Batch_continue");
					
					if(LoadApp.getBatch_continue().getAction_batch_continue().contains("quantity_received")) {
						 System.out.println("000----------------------Batch_continue 2");
						Batch_process(LoadApp,"procurement");
					}
	
				}
		

			
			private void Batch_modify(LoadApp LoadApp) {
				if(LoadApp.getBatch_modify()  == null) { return; }
				if(LoadApp.getBatch_modify().getAction_batch_modify() == null) { return; }
			
				 System.out.println("000----------------------Batch_modify");
				
				if(LoadApp.getBatch_modify().getAction_batch_modify().contains("procurement_itemname")) {
					 System.out.println("000----------------------Batch_modify 2");
					Batch_process(LoadApp,"procurement");
				}

			}
	
/*
			private void Batch_insert(LoadApp LoadApp) {
				
				
				if(LoadApp.getBatch_insert()  == null) { return; }
				if(LoadApp.getBatch_insert().getAction_batch_insert() == null) { return; }
				if(LoadApp.getType() == null ){ return; }
				
			
				 switch (LoadApp.getWorkflow().getWorkflow_maindb()) {
				case "Production":
				//	Productions.LoadBatch_insert(LoadApp,this.modelData);
					break; 	 
				case "Procurement_SupplyChain":
					Procurement_SupplyChains.LoadBatch_insert(LoadApp);
					break; 	 
				case "Machinery_Factory":	  
					Machinery_Factorys.LoadBatch_insert(LoadApp);
					 break; 	 
				case "Sales_Customer":	  
					Sales_Customers.LoadBatch_insert(LoadApp);
					 break; 	 
				case "Finance_Accounting":	  
					Finance_Accountings.LoadBatch_insert(LoadApp);
					 break; 	 
				case "HR_Workforce":	  
					HR_Workforces.LoadBatch_insert(LoadApp);
					 break; 	 
				case "Stock_Warehouse":	  
					Stock_Warehouses.LoadBatch_insert(LoadApp);
					 break; 
		        default:
		        	
				}
			
				// DisplayActionbatch(LoadApp,"edata_product");
	
			
			
				
				
				
				System.out.println(" Type "+LoadApp.getType());
			}
*/	
			
			
			private void Batch_clear(LoadApp LoadApp) {
				
				if(LoadApp.getBatch_clear()  == null) { return; }
				if(LoadApp.getBatch_clear().getAction_batch_clear() == null) { return; }
				
				
				if(LoadApp.getBatch_clear().getAction_batch_clear().contains("procurement_itemname")) {
					Batch_process(LoadApp,"procurement_clear");
				}
			
				
			}



			private <T> void Batch_process(LoadApp LoadApp, String tag) {
				
				 System.out.println("000----------------------Batch_process");
				 System.out.println("000----------------------Batch_process tag "+tag);
				
					 T objects = null;
					 String object_type = "";
					 String tempId = "";
					

					 System.out.println("000----------------------Batch_process Start tag"+tag);
					 switch (tag) {
						case "procurement":
				
					if(LoadApp.getEdata_product()  == null) { return; }		
							
					System.out.println("000----------------------Batch_process 2");
					double total =0.0;
					Double Doubletotal =0.0;
					
					String formattedTotal ="0.0";	
					
					
					
					
					if(LoadApp.getEdata_procurements().getProcurement_estimatedprice() > 0) {
						total = LoadApp.getEdata_procurements().getProcurement_estimatedprice() * LoadApp.getEdata_procurements().getProcurement_quantityrequested();
						formattedTotal = String.format("%.2f", total);
						LoadApp.getEdata_procurements().setProcurement_linetotal(formattedTotal);
						objects =  (T) LoadApp.getEdata_procurements();
						tempId = LoadApp.getEdata_procurements().getTempId();
						
					}else if(LoadApp.getEdata_procurements().getProcurement_quantity_ordered() > 0) {
						total = LoadApp.getEdata_procurements().getProcurement_quantity_ordered() * LoadApp.getEdata_procurements().getProcurement_unit_price();
						formattedTotal = String.format("%.2f", total);
						LoadApp.getEdata_procurements().setProcurement_linetotal2(formattedTotal);
						objects =  (T) LoadApp.getEdata_procurements();
						tempId = LoadApp.getEdata_procurements().getTempId();
					}	
					LoadActionbatch(LoadApp,objects,tag,tempId);
							  break; 
					
						
						case "bom":
					
							
						
							Doubletotal =  ( LoadApp.getBom().getBom_base_quantity() + LoadApp.getBom().getBom_wastage()) * LoadApp.getBom().getBom_price();
							Double formattedTotals = Math.round(Doubletotal * 100.0) / 100.0;
							LoadApp.getBom().setBom_total_cost(formattedTotals);
							
							objects =  (T) LoadApp.getBom();
							tempId = LoadApp.getBom().getTempId();
							LoadActionbatch(LoadApp,objects,tag,tempId);
							
							  break; 
							  
						case "production_standard":
							
							objects =  (T) LoadApp.getProduction_standard();
							tempId = LoadApp.getProduction_standard().getTempId();
							LoadActionbatch(LoadApp,objects,tag,tempId);
							
							  break;   
								 
						case "production_directive":
											
							objects =  (T) LoadApp.getProduction_directives();
							tempId = LoadApp.getProduction_directives().getTempId();
							LoadActionbatch(LoadApp,objects,tag,tempId);		
							break;   	  

						case "production_list":
							
							objects =  (T) LoadApp.getProduction_lists();
							tempId = LoadApp.getProduction_lists().getTempId();
							LoadActionbatch(LoadApp,objects,tag,tempId);
							
							  break;   	  		  
							  
							  
						case "other":
							
							objects =  (T) LoadApp.getOther();
							tempId = LoadApp.getOther().getTempId();
							LoadActionbatch(LoadApp,objects,tag,tempId);
							
							  break;   	  
				        default:
				        	
						}
					 
						  
			}
			
			













			private <T> void DisplayActionbatch(LoadApp LoadApp, String string) {
				
		
				
						
						
						  if (string.equals("procurement")) {
							  
								System.out.println("-----------------getId_refer_by procurement");
							  
							  List<T> Actionbatch  = new ArrayList<T>();
							 // session.removeAttribute("SubData"+LoadApp.getTempId());
							  
							  List<edata_procurement> SubDataDTOS = EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId(),"Create");
							  if(SubDataDTOS.size() > 0) {
	
								 SubDataDTOS.forEach(row->{
									
								
									 
									 if(row.getId_refer_by() > 0) {
										 List<doc> docs = EdataDAO.getDoc(row.getId_refer_by());
										
										 if(docs.size() > 0) {
										 String rv ="";
										 if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
										 row.setDoc(docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv);
										 }else {
											 row.setDoc("ID"+row.getId_refer_by());
										 }
									 }
									 
									  if(row.getProcurement_quantityrequested() > 0.0) {
										
										    double total = row.getProcurement_quantityrequested() * row.getProcurement_estimatedprice();
										    String formattedTotal = String.format("%.2f", total);
										    row.setProcurement_linetotal(formattedTotal);
									
									  }
									  
									  if(row.getProcurement_quantity_ordered() > 0.0) {
										  
										    double total = row.getProcurement_quantity_ordered() * row.getProcurement_unit_price();
										    String formattedTotal = String.format("%.2f", total);
										    row.setProcurement_linetotal2(formattedTotal);
										    
										    if(row.getProcurement_quantity_ordered() > row.getProcurement_quantityrequested() ) {
										    	row.setCheck(1);
										    	System.out.println("-----------------quantity_ordered "+row.getProcurement_quantity_ordered());
										    	System.out.println("-----------------quantityrequested "+row.getProcurement_quantityrequested());
											  }
										    
									  }
		
									  if(row.getQuantity_received() > 0.0) {
										  
							
										    if(row.getQuantity_received() > row.getProcurement_quantity_ordered()  ) {
										    	row.setCheck(1);
										 
											  }
										    
									  }
									  
									  
									    
									    System.err.println("unit_price -->"+row.getProcurement_unit_price());
									    System.err.println("quantity_ordered -->"+ row.getProcurement_quantity_ordered());    
									 
									   
									
									    
									    Actionbatch.add((T) row);
								 });
								 
							
								
								 
							
								 
								 
							  }
							  
							  
				
									 
											  double grandtotal = SubDataDTOS.stream()
													    .mapToDouble(e -> {
													        double quantity = e.getProcurement_quantity_ordered() > 0.001
													                            ? e.getProcurement_quantity_ordered() 
													                            : e.getProcurement_quantityrequested();
													        double price = e.getProcurement_quantity_ordered() > 0.001 
													                        ? e.getProcurement_unit_price() 
													                        : e.getProcurement_estimatedprice();
													        return quantity * price;
													    })
													    .sum();
											 BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
													    .setScale(2, RoundingMode.HALF_UP); 
												exposeAs("grandtotal",Grandtotal);
									
									  
							
								System.out.println(" grandtotal --->"+Grandtotal);
								 System.out.println(" item --->"+Actionbatch.size());
								 
							//	session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
								exposeAs("item",Actionbatch);
							    
						  }
						  
						  else  if (string.equals("procurement_clear")) {
							  List<T> Actionbatch  = new ArrayList<T>();
							 // session.removeAttribute("SubDataclear"+LoadApp.getTempId());
							  System.out.println("getTempId == "+LoadApp.getTempId());
							  
							  
							  List<edata_procurement> SubDataDTOS = EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId(),"Clear");
							  if(SubDataDTOS.size() > 0) {
								 System.out.println("SubDataDTOSclear.size() == "+SubDataDTOS.get(0).getId());
								 
								 
								 SubDataDTOS.forEach(row->{
									 double total = row.getProcurement_unit_price() * row.getProcurement_quantity_ordered();
									    String formattedTotal = String.format("%.2f", total);
									    row.setProcurement_linetotal2(formattedTotal);
									    Actionbatch.add((T) row);
									    
								 });
								 
							
								 
								
								
							  }
							  
								 double grandtotal2 = SubDataDTOS.stream()
										    .mapToDouble(e -> e.getProcurement_unit_price() *   e.getProcurement_quantity_ordered()) // คำนวณ unit * price
										    .sum(); // รวมค่าทั้งหมด
							  
								 BigDecimal Grandtotal2 = BigDecimal.valueOf(grandtotal2)
										    .setScale(2, RoundingMode.HALF_UP); 
								 
								 
								//session.setAttribute("SubDataclear"+LoadApp.getTempId(),Actionbatch);
							
								exposeAs("item2",Actionbatch);
								exposeAs("grandtotal2",Grandtotal2);
								
						  }
						  else  if (string.equals("bom")) {
							  List<T> Actionbatch  = new ArrayList<T>();
							 // session.removeAttribute("SubData"+LoadApp.getTempId());
							  
							  List<bom> SubDataDTOS = EdataDAO.getAllEdata_bom_item(LoadApp.getTempId());
							  Actionbatch.addAll((List<T>) SubDataDTOS); 
							  
								 System.out.println(" item --->"+Actionbatch.size());
								 
								 
								 
								 double grandtotal = SubDataDTOS.stream()
										    .mapToDouble(e -> ( e.getBom_base_quantity() + e.getBom_wastage()) *   e.getBom_price()) // คำนวณ unit * price
										    .sum(); // รวมค่าทั้งหมด
							  
								 BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
										    .setScale(2, RoundingMode.HALF_UP); 
								 
								 exposeAs("grandtotal",Grandtotal);
								 
								//session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
				
								 exposeAs("item",Actionbatch);
								 LoadApp.setActionbatch(Actionbatch);
								 
							
						
							    
						  }
						  else  if (string.equals("production_standard")) {
							  List<T> Actionbatch  = new ArrayList<T>();
							//  session.removeAttribute("SubData"+LoadApp.getTempId());
							  
							  List<production_standard> SubDataDTOS = EdataDAO.getAllEdata_production_standard_item(LoadApp.getTempId());
							  Actionbatch.addAll((List<T>) SubDataDTOS); 
							  
								 System.out.println(" item --->"+Actionbatch.size());
								 
							//	session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
		
								 exposeAs("item",Actionbatch);
							    
						  }
						  else  if (string.equals("production_list")) {
							 
							  
							  List<T> Actionbatch  = new ArrayList<T>();
							 // session.removeAttribute("SubData"+LoadApp.getTempId());
							  
							  List<production_list> SubDataDTOS = EdataDAO.getAllEdata_pl_item(LoadApp.getTempId());
							  Actionbatch.addAll((List<T>) SubDataDTOS); 
							  
						
								System.out.println(" item --->"+Actionbatch.size());
								//session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
							//	model.addAttribute("item",Actionbatch);	
								
								 exposeAs("item",Actionbatch);
							    
						  }
						  else  if (string.equals("production_directive")) {
							  List<T> Actionbatch  = new ArrayList<T>();
							//  session.removeAttribute("SubData"+LoadApp.getTempId());
							  
							  List<production_directive> SubDataDTOS = EdataDAO.getAllEdata_pd_item(LoadApp.getTempId());
							  Actionbatch.addAll((List<T>) SubDataDTOS); 
							  
								 System.out.println(" item --->"+Actionbatch.size());
								 
							//	session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
								 exposeAs("item",Actionbatch);
							    
						  }
						  else  if (string.equals("other")) {
							  List<T> Actionbatch  = new ArrayList<T>();
							 // session.removeAttribute("SubData"+LoadApp.getTempId());
							  
							  List<other> SubDataDTOS = EdataDAO.getAllEdata_other_item(LoadApp.getTempId());
							  Actionbatch.addAll((List<T>) SubDataDTOS); 
							  
								 System.out.println(" item --->"+Actionbatch.size());
								 
							//	session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
								 exposeAs("item",Actionbatch);
							    
						  }
			}

			     


			private <T> void LoadActionbatch(LoadApp LoadApp, T objects,String object_type,String tempId) {
				
				HttpSession session = LoadApp.getSession();
			
	            int indexToRemove =-1;
				
				  if (object_type.equals("procurement")) {
					  
						List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);

						if (Actionbatch == null) {  
							Actionbatch = new ArrayList<T>();
						}
					  
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท edata_procurement");
	    	            List<edata_procurement> SubDataDTOS = (List<edata_procurement>) Actionbatch;
	    	            edata_procurement  item_id = (edata_procurement) objects;
	    	            
	                    if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            edata_procurement  real_data  = null;
	    
	    	            for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                     real_data  =  SubDataDTOS.get(i);   
	    	                    break;
	    	                }
	    	            }
	    	            
	    	                   
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 
	    	          
	    	            
	    	            
		    	   		 SubDataDTOS.forEach(row->{
							
							 // System.out.println("id_refer -->"+row.getId_refer()); 
							 
								 if(row.getId_refer_by() > 0) {
									 List<doc> docs = EdataDAO.getDoc(row.getId_refer_by());
									
									 if(docs.size() > 0) {
									 String rv ="";
									 if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
									 row.setDoc(docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv);
									 }else {
										 row.setDoc("ID"+row.getId_refer_by());
									 }
								 }
							 
							 
							  if(row.getProcurement_quantityrequested() > 0.0) {
									
								    double total = row.getProcurement_quantityrequested() * row.getProcurement_estimatedprice();
								    String formattedTotal = String.format("%.2f", total);
								    row.setProcurement_linetotal(formattedTotal);
							
							  }
							  if(row.getProcurement_quantity_ordered() > 0.0) {
								  
								    double total = row.getProcurement_quantity_ordered() * row.getProcurement_unit_price();
								    String formattedTotal = String.format("%.2f", total);
								    row.setProcurement_linetotal2(formattedTotal);
								    
								    if(row.getProcurement_quantity_ordered() > row.getProcurement_quantityrequested() ) {
								    	row.setCheck(1);
									  }
								    
							  }
							  if(row.getQuantity_received() > 0.0) {
								  
									
								    if(row.getQuantity_received() > row.getProcurement_quantity_ordered()  ) {
								    	row.setCheck(1);
								 
									  }
								    
							  }
							  
							 
							
							    
						
						
						 });
						 
	    	            
	    	            
		    	   		 Actionbatch = (List<T>) SubDataDTOS;  
		    	   		 
		    	   		 
		    	   		 if(real_data != null) {
		    	   		System.err.println(" real_data "+real_data.toString());
		    	   		System.err.println(" item_id "+item_id.toString()); 
		    	   		 }
		    	   		
		    	   		 T newobjects =   (T) updateObjectFields(real_data,item_id);	
		    	    	 edata_procurement  item_ids = (edata_procurement) newobjects;
		    	   		 
		    	   		 
		    	
		    	    		System.err.println(" item_id "+item_ids.toString());
		    	   		 
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) newobjects); 
						  }else {
								 Actionbatch.add((T) newobjects); 
						  }
						  
				
						session.setAttribute("SubData"+tempId,Actionbatch);
						 exposeAs("item",Actionbatch);
	    	            
						  double grandtotal = SubDataDTOS.stream()
								    .mapToDouble(e -> {
								        double quantity = e.getProcurement_quantity_ordered() > 0.001
								                            ? e.getProcurement_quantity_ordered() 
								                            : e.getProcurement_quantityrequested();
								        double price = e.getProcurement_quantity_ordered() > 0.001
								                        ? e.getProcurement_unit_price() 
								                        : e.getProcurement_estimatedprice();
								        return quantity * price;
								    })
								    .sum();
						 
						 
						 BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
								    .setScale(2, RoundingMode.HALF_UP); 
						  
						 
					
						 exposeAs("grandtotal",Grandtotal);
					  	
	    	            
				  }else  if (object_type.equals("procurement_clear")) { 
					  
					  
						List<T> Actionbatch = (List<T>) session.getAttribute("SubDataclear"+tempId);

						if (Actionbatch == null) {  
							Actionbatch = new ArrayList<T>();
						}
					  
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท edata_procurement");
	    	            List<edata_procurement> SubDataDTOS = (List<edata_procurement>) Actionbatch;
	    	            edata_procurement  item_id = (edata_procurement) objects;
	    	            
	    	            if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            
	    
	    	            for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                    break;
	    	                }
	    	            }
	    	            
	    	            
	    	            
	    	            
	    	            
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 
	    	            
		    	       	 SubDataDTOS.forEach(row->{
							 double total = row.getProcurement_unit_price() * row.getProcurement_quantity_ordered();
							    String formattedTotal = String.format("%.2f", total);
							    row.setProcurement_linetotal2(formattedTotal);
							
							    
						 });
	    	            
	    	            Actionbatch = (List<T>) SubDataDTOS;  
	    	            
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) objects); 
						  }else {
								 Actionbatch.add((T) objects); 
						  }
						  
				
						//session.setAttribute("SubDataclear"+tempId,Actionbatch);
				
						exposeAs("item2",Actionbatch);
					  	
						 double grandtotal2 = SubDataDTOS.stream()
								    .mapToDouble(e -> e.getProcurement_unit_price() *   e.getProcurement_quantity_ordered()) // คำนวณ unit * price
								    .sum(); // รวมค่าทั้งหมด
					  
						 BigDecimal Grandtotal2 = BigDecimal.valueOf(grandtotal2)
								    .setScale(2, RoundingMode.HALF_UP); 
					
						exposeAs("grandtotal2",Grandtotal2);
						//session.setAttribute("SubDataclear"+LoadApp.getTempId(),Actionbatch);
	    	        }
				  
				  
				  
				  else  if (object_type.equals("bom")) { 
					  
					  
						List<T> Actionbatch = (List<T>) LoadApp.getActionbatch();

						if (Actionbatch == null) {  
							Actionbatch = new ArrayList<T>();
						}
					  
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท bom");
	    	            List<bom> SubDataDTOS = (List<bom>) Actionbatch;
	    	            
	    	            
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท bom size "+SubDataDTOS.size());
	    	            
	    	            bom  item_id = (bom) objects;
	    	            
	    	            if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            
	    
	    	            for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                    break;
	    	                }
	    	            }
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 

	    	            Actionbatch = (List<T>) SubDataDTOS;  
	    	            
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) objects); 
						  }else {
								 Actionbatch.add((T) objects); 
						  }
		
					  	
					  	exposeAs("item",Actionbatch);
					  	LoadApp.setActionbatch(Actionbatch);
					  	
						//session.setAttribute("SubData"+tempId,Actionbatch);
						
					    System.out.println("พบอ็อบเจ็กต์ประเภท bom tempIdsize "+Actionbatch.size());
						
	    	        }
				  
				  else  if (object_type.equals("production_standard")) { 
					  
					  
						List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);

						if (Actionbatch == null) {  
							Actionbatch = new ArrayList<T>();
						}
					  
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท production_standard");
	    	            List<production_standard> SubDataDTOS = (List<production_standard>) Actionbatch;
	    	            bom  item_id = (bom) objects;
	    	            
	    	            if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            
	    
	    	            for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                    break;
	    	                }
	    	            }
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 

	    	            Actionbatch = (List<T>) SubDataDTOS;  
	    	            
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) objects); 
						  }else {
								 Actionbatch.add((T) objects); 
						  }
						//session.setAttribute("SubData"+tempId,Actionbatch);
					  
					//	session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
					  	
					  	
						exposeAs("item",Actionbatch);
	    	        }
				    
				  
				  else  if (object_type.equals("other")) { 
					  
					  
						List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);

						if (Actionbatch == null) {  
							Actionbatch = new ArrayList<T>();
						}
					  
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท other");
	    	            List<other> SubDataDTOS = (List<other>) Actionbatch;
	    	            other  item_id = (other) objects;
	    	            
	    	            if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            
	    
	    	            for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                    break;
	    	                }
	    	            }
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 

	    	            Actionbatch = (List<T>) SubDataDTOS;  
	    	            
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) objects); 
						  }else {
								 Actionbatch.add((T) objects); 
						  }
						//session.setAttribute("SubData"+tempId,Actionbatch);
					 
						exposeAs("item",Actionbatch);
						//session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
	    	        }
				  
					 
				  
				  
				  else  if (object_type.equals("production_list")) { 
					  
					  
						List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);

				        System.out.println("tempId "+tempId);

						
						if (Actionbatch == null) {  
							
							  System.out.println("Actionbatch null");
							Actionbatch = new ArrayList<T>();
						}
				        System.out.println("พบอ็อบเจ็กต์ประเภท production_list");
				      	  System.out.println("Actionbatch size "+Actionbatch.size());
						
					  
	    	    
	    	            List<production_list> SubDataDTOS = (List<production_list>) Actionbatch;
	    	            production_list  item_id = (production_list) objects;
	    	            
	    	            if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            
	    	      	  System.out.println("Actionbatch id "+id);
	    	         
	    	    
	    	             for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                    break;
	    	                }
	    	            }
	    	            
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 
	    	          
	    	            Actionbatch = (List<T>) SubDataDTOS;  
	    	            
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) objects); 
						  }else {
								 Actionbatch.add((T) objects); 
						  }
		    	 

	    	            
	    	      	  System.out.println("Actionbatch size "+Actionbatch.size());
	    	            
					
	    	         	exposeAs("item",Actionbatch);
						session.setAttribute("SubData"+tempId,Actionbatch);
						
						
						Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);
						 System.out.println("->Actionbatch size "+Actionbatch.size());
	    	        }
				  
		
				  else  if (object_type.equals("production_directive")) { 
					  
					  
						List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);

						if (Actionbatch == null) {  
							Actionbatch = new ArrayList<T>();
						}
					  
	    	            System.out.println("พบอ็อบเจ็กต์ประเภท production_directive");
	    	            List<production_directive> SubDataDTOS = (List<production_directive>) Actionbatch;
	    	            production_directive  item_id = (production_directive) objects;
	    	            
	    	            if(item_id == null) {return;}
	    	            
	    	            int id =  item_id.getId();
	    	            
	    
	    	            for (int i = 0; i < SubDataDTOS.size(); i++) {
	    	                if (SubDataDTOS.get(i).getId() == id) {
	    	                    indexToRemove = i;
	    	                    break;
	    	                }
	    	            }
	    	            SubDataDTOS.removeIf(subData -> subData.getId() == id ); 

	    	            Actionbatch = (List<T>) SubDataDTOS;  
	    	            
		    	  		  if(indexToRemove > -1 ) {
								 Actionbatch.add(indexToRemove,(T) objects); 
						  }else {
								 Actionbatch.add((T) objects); 
						  }
						//session.setAttribute("SubData"+tempId,Actionbatch);
						//session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
					  	
					  	
						exposeAs("item",Actionbatch);
	    	        }
				  
				  
				  
				  
		
			}



			
			
			private static <T> T updateObjectFields(T target, T source) {
			    if (target == null) {
			        return source;
			    }

			    Class<?> clazz = target.getClass();
			    for (Field field : clazz.getDeclaredFields()) {
			        try {
			            field.setAccessible(true);

			            // ตรวจสอบว่าชื่อฟิลด์เป็น 'id_refer' หรือไม่
			            if (!field.getName().equals("id_refer")) {
			                Object value = field.get(source);
			                if (value != null) {
			                    // ตรวจสอบว่าค่าคือ 0.0000001 หรือใกล้เคียงกัน (โดยใช้ tolerance)
			                    if (value instanceof Double && Math.abs((Double) value - 0.0000001) < 1e-7) {
			                        // ข้ามการบันทึกค่า หากค่าใกล้เคียงกับ 0.0000001
			                        System.err.println(" ข้ามการบันทึกหากค่าคือ  0.0000001 หรือใกล้เคียงกัน");
			                        continue;  // ข้ามการบันทึกค่า
			                    } else {
			                        field.set(target, value); // บันทึกค่าปกติ
			                    }
			                }
			            }
			        } catch (IllegalAccessException ignored) {}
			    }
			    return target; // ส่งค่า target กลับมา
			}
			
			
			
			
			private LoadApp LoadTransactions(LoadApp LoadApp) {
				

				if(LoadApp.getEdata() == null) {return LoadApp;}
				
				  
				 if( LoadApp.getApp_model()== null) {
					 System.out.println(" LoadTransactions model  "+LoadApp.getApp_model());
					 return LoadApp;
				 }
			  
			
				  int eformId = LoadApp.getEdata().getData_id();
				 switch (LoadApp.getWorkflow().getWorkflow_maindb()) {
				 
					case "Stock_Warehouse":
							switch (LoadApp.getApp_model()) {
						    		case "edata_stocks." -> transactionexposeAs(EdataDAO.geteData_stock(eformId),"edata_stock");
						    		case "edata_products." -> transactionexposeAs(EdataDAO.geteData_product(eformId),"edata_register");
						    		case "Issue_fg." -> transactionexposeAs(EdataDAO.getIssue_fg(eformId),"Issue_fg");
						    		case "Inventory_transfer_fgs." -> transactionexposeAs(EdataDAO.getInventory_transfer_fg(eformId),"Inventory_transfer_fg");
						    		case "Goods_receipt_fgs." -> transactionexposeAs(EdataDAO.getGoods_receipt_fg(eformId),"Goods_receipt_fg");
						    		case "Adjustment_fgs." -> transactionexposeAs(EdataDAO.getAdjustment_fg(eformId),"Adjustment_fg");
						    		case "Issue_sks." -> transactionexposeAs(EdataDAO.getIssue_sk(eformId),"Issue_sk");
						    		case "Inventory_transfer_sks." -> transactionexposeAs(EdataDAO.getInventory_transfer_sk(eformId),"Inventory_transfer_sk");
						    		case "Goods_receipt_sks." -> transactionexposeAs(EdataDAO.getGoods_receipt_sk(eformId),"Goods_receipt_sk");
						    		case "Adjustment_sks." -> transactionexposeAs(EdataDAO.getAdjustment_sk(eformId),"Adjustment_sk");
							}	
						  break; 
					case "Production":	  
					      System.err.println("LoadTransactions ->Production "); 
					      System.err.println("LoadTransactions ->Production model "+LoadApp.getApp_model()); 
					    	switch (LoadApp.getApp_model()) {
					    		case "edata_registers." -> transactionexposeAs(EdataDAO.geteData_register(eformId),"edata_register");
				    			case "edata_products." -> transactionexposeAs(EdataDAO.geteData_product(eformId),"edata_register");
					    		case "boms." -> transactionexposeAs(EdataDAO.getBom(eformId),"bom");
					    		case "qcis." -> transactionexposeAs(EdataDAO.getQci(eformId),"qci");
					    		case "others." -> transactionexposeAs(EdataDAO.getBom(eformId),"other",LoadApp);
					    		case "production_standards." -> transactionexposeAs(EdataDAO.getProduction_standard(eformId),"production_standard");
					    		case "production_lists." -> transactionexposeAs(EdataDAO.getProduction_lists(eformId),"production_list");
					    		case "production_directives." -> transactionexposeAs(EdataDAO.getProduction_directives(eformId),"production_directive");
					    		case "edata_production_records." -> transactionexposeAs(EdataDAO.getEdata_production_records(eformId),"edata_production_record",LoadApp);
					    		case "sales_followups." -> transactionexposeAs(Productions.getSales_followupByEdata(eformId),"sales_followup",LoadApp);
					    		default-> 	logService.notice(404,"Error : App_model cannot be null", "LoadAppUnity_Component/LoadTransactions",LoadApp.getEdata().getData_id());
					    		
					    		
					    	}	
						 break; 
					case "Procurement_SupplyChain":
						Procurement_SupplyChains.LoadTransactions(LoadApp,this.modelData);
						break; 	 
					case "Machinery_Factory":	  
						Machinery_Factorys.LoadTransactions(LoadApp,this.modelData);
						 break; 	 
					case "Sales_Customer":	  
						Sales_Customers.LoadTransactions(LoadApp,this.modelData);
						 break; 	 
					case "Finance_Accounting":	  
						Finance_Accountings.LoadTransactions(LoadApp,this.modelData);
						 break; 	 
					case "HR_Workforce":	  
						HR_Workforces.LoadTransactions(LoadApp,this.modelData);
						 break; 	 
			        default:
			        	
					}
				
				 
				 return LoadApp;
			}
		
			
			
			 private void transactionexposeAs(Object object, String string) {
			    
				 	exposeAs("transaction", object);
					exposeAs("edata_type",string);
					exposeAs("refer_type",string);
			}
			 private void transactionexposeAs(Object object, String edata_type,LoadApp loadApp) {
				    

				   if(edata_type.contains("edata_production_record")) {
					   processTransactionEPR(object,loadApp);
				   }
				   else if(edata_type.contains("other")) {
					   processTransactionOther(object,loadApp);
				   }
				 
				 	exposeAs("transaction", object);
					exposeAs("edata_type",edata_type);
					exposeAs("refer_type",edata_type);
					
					loadApp.setTransitionObject(object);
					
					
					if(object != null) {
						RenameEdata(object,edata_type,loadApp);
					}
					
					
			
			}



			private void RenameEdata(Object object, String edata_type, LoadApp loadApp) {
					switch (edata_type) {
						case "sales_followup" ->{
							
					
							sales_followup sale = (sales_followup)object;
							
							 System.out.println(" sale "+sale.getId());
							
							loadApp.setSales_followup(sale);
							RenameEdataSale(loadApp);
						}
					}
				
			}
			
			private void RenameEdataSale(LoadApp LoadApp) {

				   System.out.println(" LoadApp Unity RenameEdataSale ");
					  
				   int id_refer = 0;
				   String name = null;
					     registers regiters  = EdataDAO.getProfileByCode(  LoadApp.getSales_followup().getCustomerCode());
					     if(regiters != null) {
					    	 id_refer = regiters.getId();
					    	 name = regiters.getRegister_name();
					     }
						
					  if (LoadApp.getProcess().getRenamefile() == 1) {
					      
					      // แบบที่ 1: ProcessName + Customer + Service
					      String newname = joinNames(
					          LoadApp.getProcess().getName(),
					          name,
					          LoadApp.getSales_followup().getServices_offered()
					      );
					      
					      System.out.println("newname " +newname);
					      
					      EdataDAO.setTitleAndIdrefer(LoadApp.getEdata().getData_id(), newname,id_refer);
	
					  } else if (LoadApp.getProcess().getRenamefile() == 2) {
					      
					      // แบบที่ 2: หัวชื่อเก่า + Customer
					      
					      // 1. แกะหัวชื่อเก่า (เช่น "ขอเพิ่ม_dfdddfd" -> เอาแค่ "ขอเพิ่ม")
					      String oldname = LoadApp.getEdata().getData_title();
					      String headerPrefix = "";
					      
					      if (oldname != null && oldname.contains("_")) {
					          headerPrefix = oldname.split("_")[0]; // เอาคำหน้าสุด
					      } else {
					          headerPrefix = oldname; // ถ้าไม่มี _ เลย ก็เอามาทั้งดุ้น
					      }
					      
					      // 2. เอาหัวมาต่อกับชื่อลูกค้า (ProcessName ไม่ใช้แล้ว ตามโจทย์)
					      String newname = joinNames(
					          headerPrefix,
					          name
					      );
					      
					      EdataDAO.setTitleAndIdrefer(LoadApp.getEdata().getData_id(), newname,id_refer);
					  }
					     
			}
			public String joinNames(String... parts) {
			    java.util.StringJoiner joiner = new java.util.StringJoiner("_");
			    for (String part : parts) {
			        if (part != null && !part.trim().isEmpty() && !part.equalsIgnoreCase("null") && !part.equalsIgnoreCase("undefined")) {
			            joiner.add(part.trim());
			        }
			    }
			    return joiner.toString();
			}


			private void processTransactionOther(Object object,LoadApp loadApp) {
   	    		
 
   	            if(object == null) { return;}
   	            List<other> item_batch3 =  EdataDAO.geteAllOther(loadApp.getEdata().getReference());
		    		
   	         // ดึง process_step จาก transaction
   	            String step2 =  otherservices.DefaultValueString(((other) object).getReference_code());
   	            Double Order = otherservices.DoubleValueOf(((other) object).getOrder_quantity());
   	            // รวมยอดเก่าที่ process_step ตรงกัน
   	            Double totalOutput2 = 0.0;
   	            Double totalDefective2 = 0.0;
   	  
   	        
   	            
   	            
   	            System.out.println("transaction " +object.toString());
   	            

   	            for (other row : item_batch3) {
   	            	
   	            String Reference = 	otherservices.DefaultValueString(row.getReference_code());
   	                if (step2.equals(Reference)) {
   	                	totalOutput2 +=  otherservices.DoubleValueOf(row.getReceived_today()) ;
   	                	totalDefective2 += otherservices.DoubleValueOf(row.getPaid_today());
   	                }
   	            }

   	            // เซ็ตกลับเข้า transaction
   	            ((other) object).setReceived_accumulated(totalOutput2);
   	            ((other) object).setPaid_accumulated(totalDefective2);

   	            // คำนวณ outstanding
   	            Double pending_received = Order - totalOutput2;
   	            Double pending_paid = Order  - totalDefective2;
   	            
   	            ((other) object).setPending_received(pending_received);
   	            ((other) object).setPending_paid(pending_paid);
   	     
   	    }
			





			private void processTransactionEPR(Object object,LoadApp loadApp) {
			
				if(object == null) { return;}
 	            if(loadApp.getEdata().getReference() == null) { return;}
 	            List<edata_production_record> item_batch2 =  EdataDAO.geteAllproduction_records(loadApp.getEdata().getReference());
		    		
 	         // ดึง process_step จาก transaction
 	            String step = ((edata_production_record) object).getProcess_step()  == null ? "-" : ((edata_production_record) object).getProcess_step();

 	            // รวมยอดเก่าที่ process_step ตรงกัน
 	            int totalOutput = 0;
 	            int totalDefective = 0;

 	            for (edata_production_record row : item_batch2) {
 	                if (step.equals(row.getProcess_step())) {
 	                    totalOutput += row.getActual_output();
 	                    totalDefective += row.getDefective_output();
 	                }
 	            }


 	            // เซ็ตกลับเข้า transaction
 	            ((edata_production_record) object).setTotal_output(totalOutput);
 	            ((edata_production_record) object).setTotal_defective(totalDefective);

 	            // คำนวณ outstanding
 	            int outstanding = ((edata_production_record) object).getOrder_quantity() - totalOutput;
 	            ((edata_production_record) object).setOutstanding(outstanding);

 	            System.out.println("getEdata_production_records end ");
				
			}


			public void Relation(LoadApp loadApp) {
				 
				 System.out.println(" Relation ");
				 String Policies = Userservice.getPoliciesString(); 
				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				usersed.setAuth(Policies);
				 
				 process process = EdataDAO.getProcess(loadApp.getRelation_id());
				 
				 if(process == null) { return;}
				 
				 List<edata>  relationList = EdataDAO.getallProcessesRelation(loadApp.getRelation_id(),process.getCurrent_status(),usersed);
				 
				 
				 
				 
				 System.out.println(" Relation  = "+relationList.size());
				 exposeAs("relationList",relationList);
				
				 
			}

			private List<DocDTO> loadSubdata(LoadApp loadApp, String receiver_type, int edata_id) {
				
				 System.out.println(" receiver_type  = "+receiver_type);
				
				 loadApp.setWorkflow_refer(String.valueOf(edata_id));
				 
					if(receiver_type.equals("PD")) {
						return SubDataPD(loadApp);
					}
					
					return null;
			}

			
			
			
		
			
			
			
			
			public Idoc getDocs() {
				return docs;
			}
			public user getUsers() {
				return users;
			}

			
			private void clear() {
			    setDocs(null);
				
			}
			
			public LoadApp getLoad() {
		        return app;
		    }

			public String getDb() {
				return db;
			}


			public void setDb(String db) {
				this.db = db;
			}


			public String getSubdb() {
				return subdb;
			}


			public void setSubdb(String subdb) {
				this.subdb = subdb;
			}


			
			public String getMaindata() {
				return maindata;
			}


			public void setMaindata(String maindata) {
				this.maindata = maindata;
			}


			public String getFiltermain() {
				return filtermain;
			}


			public void setFiltermain(String filtermain) {
				this.filtermain = filtermain;
			}


			public String getSubdata() {
				return subdata;
			}


			public void setSubdata(String subdata) {
				this.subdata = subdata;
			}


			public String getFiltersub() {
				return filtersub;
			}


			public void setFiltersub(String filtersub) {
				this.filtersub = filtersub;
			}

			public String getMaindatacolumn() {
				return maindatacolumn;
			}



			public void setMaindatacolumn(String maindatacolumn) {
				this.maindatacolumn = maindatacolumn;
			}



			public String getSubdatacolumn() {
				return subdatacolumn;
			}



			public void setSubdatacolumn(String subdatacolumn) {
				this.subdatacolumn = subdatacolumn;
			}
			
			public void setKeymaps(List<keymap> keymaps) {
				this.keymaps = keymaps;
				
			}


			public void setDocs(Idoc docs) {
				this.docs = docs;
			}
			
			public Idoc getDocs(Idoc docs) {
				return docs;
				
			}
			
			private void setUsers(user usersed) {
				this.users = usersed;
				
			}


			public String getAutoiddatacolumn() {
				return autoiddatacolumn;
			}


			protected List<String> getAbility() {
				return ability;
			}


			public sub_processes getSubprocesses() {
				return subprocesses;
			}


			public void setSubprocesses(sub_processes subprocesses) {
				this.subprocesses = subprocesses;
			}


			public int getId() {
				return id;
			}


			public void setId(int id) {
				this.id = id;
			}


			public void setAutoiddatacolumn(String autoiddatacolumn) {
				this.autoiddatacolumn = autoiddatacolumn;
			}

			 public Map<String, Object> getModelData() {
			        return modelData;
			    }
			 public void exposeAs(String attribute,Object list) {
			         modelData.put(attribute, list);
			 }


			



	
}
