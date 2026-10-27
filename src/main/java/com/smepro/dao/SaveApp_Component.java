package com.smepro.dao;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.smepro.dto.MultipleModelsDTO;
import com.smepro.entity.sales_followup;
import com.smepro.enums.Actiontype;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Idoc;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.LoadApp;
import com.smepro.models.Relations;
import com.smepro.models.TabManagement;
import com.smepro.models.batch_code;
import com.smepro.models.batchformat;
import com.smepro.models.bom;
import com.smepro.models.condition;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.eformfield;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.process;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.qci;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.repository.Sales_followupRepository;
import com.smepro.service.BuildInService;
import com.smepro.service.FlowServices;
import com.smepro.service.LogService;
import com.smepro.service.PlatformService;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;



@Component
public class SaveApp_Component {

	
	
	
	private edataDAO EdataDAO;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	private otherservice Otherservice;
	
	private Machinery_Factory Machinery_Factorys;
	private Sales_Customer Sales_Customers;
	private Finance_Accounting Finance_Accountings;
	private HR_Workforce HR_Workforces;
	private Procurement_SupplyChain Procurement_SupplyChains;
	private Production Productions;
	private Stock_Warehouse Stock_Warehouses;
	private LogService logService;
	private BuildInService buildInService;
	private Sales_followupRepository  Sales_followupRepository;
	
	private IdocDAO idocDAO;
	private reportDAO ReportDAO;
	private eformfieldDAO EformfieldDAO;

	
	private SaveState State = SaveState.NONE;
	LoadApp LoadApp = new LoadApp();
	private boolean error = false;
	
	
	public enum SaveState {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}


	
	  @Autowired
	  private void setMyRepository(
	    		edataDAO EdataDAO,
	    		userDAO UserDAO,
	    		upload_pnDAO Upload_pnDAO,
	    		userservice Userservice,
	    		conditionDAO ConditionDAO,
	    		datasetDAO DatasetDAO,
	    		dataset_dataDAO Dataset_dataDAO,
	    		workflowDAO WorkflowDAO,
	    		otherservice Otherservice,
	    		Machinery_Factory Machinery_Factorys,
	    		Sales_Customer Sales_Customers,
	    		Finance_Accounting Finance_Accountings,
	    		HR_Workforce HR_Workforces,
	    		Procurement_SupplyChain Procurement_SupplyChains,
	    		Production Productions,
	    		Stock_Warehouse Stock_Warehouses,
	    		LogService logService,
	    		BuildInService buildInService,
	    		IdocDAO idocDAO,
	    		reportDAO ReportDAO,
	    		Sales_followupRepository  Sales_followupRepository,
	    		eformfieldDAO EformfieldDAO
	    		
	    		) { 
	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;
	        this.Upload_pnDAO = Upload_pnDAO;
	        this.Userservice = Userservice;
	        this.ConditionDAO = ConditionDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.Dataset_dataDAO = Dataset_dataDAO;
	        this.WorkflowDAO = WorkflowDAO;
	        this.Otherservice = Otherservice;
	        this.Machinery_Factorys = Machinery_Factorys;
	        this.Sales_Customers = Sales_Customers;
	        this.Finance_Accountings = Finance_Accountings;
	        this.HR_Workforces = HR_Workforces;
	        this.Procurement_SupplyChains = Procurement_SupplyChains;
	        this.Productions = Productions;
	        this.Stock_Warehouses = Stock_Warehouses;
	        this.logService = logService;
	        this.buildInService = buildInService;
	        this.Sales_followupRepository = Sales_followupRepository;
	        this.idocDAO = idocDAO;
	        this.ReportDAO = ReportDAO;
	        this.EformfieldDAO = EformfieldDAO;
	    }

	
	  
	private int id;
	private String db;
	private String subdb;
	private ModelMap model;
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
	private int child;
	private  Map<String, Object> modelData;

	 
	
	

	 /**
	  * Initializes and resets all related states or configurations of the {@code PlatformService}.
	  * <p>
	  * This method should be called **exactly once** at the start of the service lifecycle
	  * to ensure a clean initial state before any operations are performed.
	  * </p>
	  * <p>
	  * Calling this method multiple times is not recommended, as it is intended only
	  * for the first-time initialization (e.g., when the application starts or the service
	  * is freshly created).
	  * </p>
	 * @param modelData 
	  *
	  * @return the current {@code PlatformService} instance after initialization
	  */
	public  SaveApp_Component initiate(LoadApp LoadApp, Map<String, Object> modelData){
		 this.modelData = modelData;
		 this.LoadApp = LoadApp;
		 this.child = 0;
		 this.error = false;
		 return this;
	 }

	public SaveApp_Component createErrorState(String string) {
	
		 this.error = true;
		
		 return this;
	}

	
    /**
     * Stages the given entity or model for a future save operation by adding it to the current batch.
     * <p>
     * This method should be called after {@code setEntity()} has been executed,
     * ensuring that all entity and platform data have been properly initialized.
     * Instead of immediately persisting the data to the database, it queues or stores
     * the provided object into an internal batch (Actionbatch) for later processing.
     * </p>
     * @param <T>
     *
     * @param object the entity or model to be staged for a future save operation
     * @return the current {@link LoadApp} instance containing the updated batch state
     */
	public  <T> LoadApp stageToBatchForSave(Object object ){
		
		    if(object == null) {
		    	object = new ArrayList<T>();
		    }
		
		  this.LoadApp.setActionbatch(object);
		   stageToSave(object);
		  return  this.LoadApp;
	 }
	
	
	public  <T> SaveApp_Component child(Object object ){
		
	    if(object == null) {
	    	object = new ArrayList<T>();
	    }
	    child = 1;
	    this.LoadApp.setActionbatch(object);
	  
	    return  this;
	}

	
	
	private void stageToSave(Object object) {
		// TODO Auto-generated method stub
	  	switch (this.LoadApp.getApp_model()) {
			case "boms." -> Productions.Batch_inserthasChanges(this.LoadApp,"bom",this.modelData);
			case "production_standards." -> Productions.Batch_inserthasChanges(this.LoadApp,"production_standard",this.modelData);
			case "production_lists." -> Productions.Batch_inserthasChanges(this.LoadApp,"production_list",this.modelData);
			case "production_directives." -> Productions.Batch_inserthasChanges(this.LoadApp,"production_directive",this.modelData);
			case "others." -> Productions.Batch_inserthasChanges(this.LoadApp,"other",this.modelData);
		
	  	}	
			  
	}
	
	
	/**
	 * Executes the save or update operation to persist data into the database.
	 * <p>
	 * This method should be called after {@code setEntity()} has been executed,
	 * ensuring that all entity and platform data have been properly set.
	 * It performs the actual database operation (insert or update)
	 * and returns the result status or affected record count.
	 * </p>
	 *
	 * @return an integer indicating the result of the save operation
	 *         (e.g., number of affected rows or status code)
	 */
	public  int save(){

			if(this.error == true) { return 0; }
		
		   if(child == 1) {
			   SaveBatch();
		   }else {
			   doSave();
			   SaveBatch();
			   addRelation();
			  doBuildIn();
			changeColseStatus();
		   }
		    return  this.LoadApp.getEdata().getData_id();
	}

		
		private void doSave() {
			System.err.println("doSave()-> Data_id  "+this.LoadApp.getEdata().getData_id()  );
			System.err.println("doSave()-> Proccee id  "+this.LoadApp.getEdata().getProcesses_id());
			if(this.LoadApp.getEdata().getProcesses_id() == 0) {
				System.err.println("ERROR doSave()-> Proccee id  "+this.LoadApp.getEdata().getProcesses_id());
				// 2. โยน Exception ออกไปเลยเพื่อหยุดการทำงาน
			    throw new IllegalArgumentException("Process ID cannot be 0");
			}
		
			
			
				boolean newfile =  this.LoadApp.getEdata().getData_id() == 0 ? true : false;
				int  eformId =  EdataDAO.saves(this.LoadApp.getEdata());
			    edata edatasave =  EdataDAO.getbyid(eformId);
			    this.LoadApp.setEdata(edatasave);
		    	SaveApp(this.LoadApp); 
			
		}
		
		private void SaveBatch() {
			

			EdataDAO.savesBatch(this.LoadApp.getBatchEdatas(),String.valueOf(LoadApp.getEdata().getData_id()));
			/*
		  	switch (this.LoadApp.getApp_model()) {
				case "boms." -> Productions.SaveActionbatchdata(this.LoadApp,"bom",this.modelData);
				case "production_standards." -> Productions.SaveActionbatchdata(this.LoadApp,"production_standard",this.modelData);
				case "production_lists." -> Productions.SaveActionbatchdata(this.LoadApp,"production_list",this.modelData);
				case "production_directives." -> Productions.SaveActionbatchdata(this.LoadApp,"production_directive",this.modelData);
				case "others." -> Productions.SaveActionbatchdata(this.LoadApp,"other",this.modelData);
			
		  	}	
			*/
		}
		
		
		
		private void SaveApp(LoadApp LoadApp) {
			
			System.err.println("SaveApp -> "+this.LoadApp.getApp_model() );
		  // 	Productions.Save(LoadApp);
		   	

			 if(this.LoadApp.getApp_model() == null) { return;}
			
			switch (this.LoadApp.getApp_model()) {
				case "edata_registers." -> Productions.Save_Registration_Request(this.LoadApp,"register",this.modelData);
				case "edata_products." -> Productions.Save_Registration_Request(this.LoadApp,"product",this.modelData);
				case "sales_followups." ->{
					
						//ถ้่าสร้างผ่าน ทางลัดมันจะไม่มี  Sales_followup เเนบมาไปสร้างไว้ซะ
						AutoMainData(this.LoadApp,"Sales_followup");
						
						Productions.Save_Action(this.LoadApp,"Sales_followup",this.modelData);
				} 
			}	
			
			if(this.LoadApp.getBatchEdata() != null) {
				switch (this.LoadApp.getApp_model()) {
				case "sales_followups." -> Productions.saveAllSales_followup(this.LoadApp,"Sales_followup",this.modelData);
			}	
			}
		
		
		}
		


		private void getRelation() {
           //
		}

		private void addRelation() {
				// TODO Auto-generated method stub
	
			System.out.println("Relation_id "+this.LoadApp.getPlatform().getRelation_id() );
			
			
			try{
				
				if(this.LoadApp.getPlatform().getRelation_id() == 0) { 
					
					   Relations Node  = EdataDAO.getRelations(LoadApp.getEdata().getData_id());

						edata relationData = EdataDAO.getbyid(Node.getRelations_id());		
						EdataDAO.LinKName(this.LoadApp.getEdata(),relationData,Node.getId());
						
						return; 

				
				}

				edata relationData = EdataDAO.getbyid(this.LoadApp.getPlatform().getRelation_id());		
				
				System.out.println("getRelation_id "+this.LoadApp.getPlatform().getRelation_id());
				System.out.println("relationData "+relationData.getProcesses_id());
				
				process processes = EdataDAO.getProcess(relationData.getProcesses_id());
				this.LoadApp.getEdata().setProcesses_id(processes.getId());
				
				EdataDAO.LinK(this.LoadApp.getEdata(),relationData);
				//สำคัญกับไปหาลูกด้วย
				RelationFeed(this.LoadApp.getEdata(),relationData); 
				
			}catch(Exception e) {
				System.err.println("relationExceptionData "+ e);
				logService.notice(401,"Error (Validation / Missing field) : Processes OR Relation_id can be null", "SaveApp_Component/addRelation",0);
			}
		}

		private boolean checkCardinality(process processes, edata relationData) {
			// TODO Auto-generated method stub
			
			if(processes.getRelationship() == null) { return false;}
			if(processes.getRelationship().equals("unlimited")) { return true;}
		
			int num = EdataDAO.countLinK(relationData.getDoc());
			if(num > 0) { return false;}
			
			return true;
		}
		
		

		private void doBuildIn() {
	        System.out.println(" doBuildIn ");
			buildInService.executeBuildIn(this.LoadApp.getEdata());
		
		}
		private void changeColseStatus() {
			

			if(LoadApp.getPlatform().getRelation_id()== 0) { return;}
			
			
			if(LoadApp.getPlatform().getTab_id() == 0) {
				
			
							
				edata subedata = EdataDAO.getbyid(LoadApp.getPlatform().getRelation_id());
				if(subedata == null) { return;}
				
				System.out.println("Processes  "+subedata.getProcesses_id());
				
				process processes = EdataDAO.getProcess(subedata.getProcesses_id());
				if(!Otherservice.haveValue(processes.getShortcut_newdoc_change_closing_status())) { return;}
				
				idocDAO.end(subedata.getData_id(),processes.getShortcut_newdoc_change_closing_status(),"-","-", "-"); 
				Idoc idoced = idocDAO.getDocument(subedata.getData_id());
				String status = idoced.getClosing_status()+" TO "+processes.getShortcut_newdoc_change_closing_status();
				logService.action(200,"Success (Change Close Status) :"+status, "SaveApp_Component/changeColseStatus",subedata.getData_id());
				
				
			}else {
			TabManagement tabmanagement = ReportDAO.getTabManagement(LoadApp.getPlatform().getTab_id());
			
			if(!Otherservice.haveValue(tabmanagement.getShortcut_newdoc_change_closing_status())) { return;}
			
			edata subedata = EdataDAO.getbyid(LoadApp.getPlatform().getRelation_id());
			if(subedata == null) { return;}

			idocDAO.end(subedata.getData_id(),tabmanagement.getShortcut_newdoc_change_closing_status(),"-","-","-"); 

			
			Idoc idoced = idocDAO.getDocument(subedata.getData_id());
			String status = idoced.getClosing_status()+" TO "+tabmanagement.getShortcut_newdoc_change_closing_status();
			logService.action(200,"Success (Change Close Status) :"+status, "SaveApp_Component/changeColseStatus",subedata.getData_id());
			}		
			
			
		
			
			
		}
		
		private void AutoMainData(LoadApp loadApp,String type) {

			switch (type) {
					case "Sales_followup"->{
						
						if(loadApp.getSales_followup() != null) {
								return;
						}
						
						sales_followup Sales_followup =  new sales_followup();
						Sales_followup.setCustomerCode(loadApp.getEdata().getCustomer());
						Sales_followup.setEdataId(loadApp.getEdata().getId());
						
						
						
						loadApp.setSales_followup(Sales_followup);
						
						
					}
			}
			
			
		}
	

		private void RelationFeed(edata edata, edata relationData) {
			
				Edata_maindata  maindata  = EdataDAO.getEdatamainData( relationData.getData_id());
				if(maindata == null ) {return;}

						switch (maindata.getEntity()) {
								case "sales_followup"->{
									
									System.out.println("RelationFeed Work...");
									System.out.println("RelationFeed save...");
									
									 List<keymap> keymaps = EdataDAO.getAllKeyMaps(edata.getProcesses_id());
									
									 	sales_followup followup = Sales_followupRepository.findById(maindata.getMain_data_id());
									 	String docrefer = relationData.getDoc();
									 	String Sale = followup.getSalesperson();
										String customerCode = followup.getCustomerCode();
										String promotionCode = followup.getPromotionCode();
									 	List<eformfield> eformfields_Main   = EformfieldDAO.getall(edata.getData_eformid(), edata.getData_eformrv());
									    
									 	String docrefer2="";
									 	String Sale2="";	
										String customerCode2="";	
										String promotionCode2="";	
									 	for(eformfield  rows: eformfields_Main) { 
										 		
										 		Optional<keymap> keymapOpt = keymaps.stream()
										                .filter(k -> Integer.parseInt(k.getEformkey()) == rows.getField_id())
										                .filter(k -> "create".equals(k.getType()))
										                .findFirst() .or(() -> keymaps.stream()
										                        .filter(k -> Integer.parseInt(k.getEformkey()) == rows.getField_id())
										                        .findFirst());
									
										        if (keymapOpt.isPresent()) {
										        	
										        	System.out.println("keymapOpt isPresent...");
										        	
														        keymap keymap = keymapOpt.get();
														        System.out.println("Eformkey..."+keymap.getEformkey());
														        
														        
																if(keymap.getMainkey().equals("salesperson")) {
																	Sale2= "data_value"+rows.getField_postion();        		
																 }
																if(keymap.getMainkey().equals("customerCode")) {
																	customerCode2= "data_value"+rows.getField_postion();        		
																 }
																if(keymap.getMainkey().equals("promotionCode")) {
																	promotionCode2= "data_value"+rows.getField_postion();        		
																 }
										        	}
									 	}
									 	

									 	mapDynamicValue(edata, Sale2,Sale);
									 	mapDynamicValue(edata, customerCode2,customerCode);
									 	mapDynamicValue(edata, promotionCode2,promotionCode);
									 	

									 	System.out.println("Sale..."+Sale);
									 	System.out.println("customerCode..."+customerCode);
									 	System.out.println("promotionCode..."+promotionCode);
									 	System.out.println("docrefer2..."+docrefer2);
									 	System.out.println("Sale2..."+Sale2);
									 	System.out.println("customerCode2..."+customerCode2);
									 	System.out.println("promotionCode2..."+promotionCode2);
									 	
									 	
									 	
										System.out.println("RelationFeed save...");
									 	EdataDAO.save(edata);
									 	
								}	
						}
				
						
			}
			
			
			
		public void mapDynamicValue(Object targetObj, String fieldName, Object value) {
		    // ป้องกันกรณีส่งค่า null หรือชื่อฟิลด์ว่าง
		    if (targetObj == null || fieldName == null || fieldName.isEmpty()) return;

		    try {
		        // ใช้ Reflection ดึง Field ตามชื่อที่ส่งมา (เช่น "docrefer2")
		        Field field = targetObj.getClass().getDeclaredField(fieldName);
		        
		        field.setAccessible(true); // ปลดล็อค private access
		        field.set(targetObj, value); // นำค่า value ไปใส่ใน field นั้นของ targetObj
		        
		    } catch (NoSuchFieldException e) {
		        System.err.println("❌ ไม่พบคอลัมน์: " + fieldName + " ใน Class " + targetObj.getClass().getSimpleName());
		    } catch (IllegalAccessException e) {
		        System.err.println("❌ เข้าถึงคอลัมน์ไม่ได้: " + fieldName);
		    } catch (Exception e) {
		        System.err.println("❌ เกิดข้อผิดพลาดอื่น: " + e.getMessage());
		    }
		}
			
			


		
		
	
}
