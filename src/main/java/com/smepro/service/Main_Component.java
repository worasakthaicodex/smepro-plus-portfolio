package com.smepro.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.smepro.dao.App_Component;
import com.smepro.dao.ApproveApp_Component;
import com.smepro.dao.Extension_Component;
import com.smepro.dao.Finance_Accounting;
import com.smepro.dao.ForwardApp_Component;
import com.smepro.dao.HR_Workforce;
import com.smepro.dao.LoadApp_Component;
import com.smepro.dao.Machinery_Factory;
import com.smepro.dao.Management_Component;
import com.smepro.dao.Procurement_SupplyChain;
import com.smepro.dao.Production;
import com.smepro.dao.Property_Component;
import com.smepro.dao.ReceiveApp_Component;
import com.smepro.dao.RecordApp_Component;
import com.smepro.dao.Register_Component;
import com.smepro.dao.Report_Component;
import com.smepro.dao.Sales_Customer;
import com.smepro.dao.SaveApp_Component;
import com.smepro.dao.Stock_Warehouse;
import com.smepro.service.WorkSystem;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dto.MultipleModelsDTO;
import com.smepro.dto.driverDTO;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Dataset;
import com.smepro.enums.Save_status;
import com.smepro.enums.Stock_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.BatchEdata;
import com.smepro.models.DocumentEffect;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Group_label;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Management;
import com.smepro.models.Platform;
import com.smepro.models.PlatformConfig;
import com.smepro.models.PlatformReport;
import com.smepro.models.PlatformUserSettings;
import com.smepro.models.Relations;
import com.smepro.models.SubData;
import com.smepro.models.TabManagement;
import com.smepro.models.Transition;
import com.smepro.models.WorkSystemdata;
import com.smepro.models.bom;
import com.smepro.models.condition;
import com.smepro.models.control_steps;
import com.smepro.models.datalink;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.edatas;
import com.smepro.models.eformfield;
import com.smepro.models.file;
import com.smepro.models.flow;
import com.smepro.models.keymap;
import com.smepro.models.loadmaindata;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.purchase;
import com.smepro.models.registerkey;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.plugin.AbilityProcessor;
import com.smepro.dto.AppBlueprint;

import com.smepro.service.FlowServices;
import com.smepro.service.ProgressService;
import com.smepro.service.userservice;




@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public  class Main_Component   {

	 private  Map<String, Object> modelData;
	 
    private  App_Component App_Components;
    private  Property_Component Property_Components;
    private  SaveApp_Component SaveApp_Components;
    private  LoadApp_Component LoadApp_Components;
    private  ApproveApp_Component ApproveApp_Components;
    private  RecordApp_Component RecordApp_Components;
    private  ForwardApp_Component ForwardApp_Components;
    private  ReceiveApp_Component ReceiveApp_Components;
    private  Workflow_Component Workflow_Components;
    private  Register_Component Register_Components;
	private Extension_Component Extension_Components;
	private Management_Component Management_Components;
	private Report_Component Report_Components;
	
	
	
	
	private edataDAO EdataDAO;
	private userDAO UserDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private workflowDAO WorkflowDAO;
	private State States = State.NONE;
	private fileDAO FileDAO;
	private filemanagerservice Filemanagerservice;
	private eformfieldDAO EformfieldDAO;
	private AppComponent appComponent;
	private LogService logService;
	
	private BuildInService buildInService;
	
	
	
	// 1. สร้าง List เปล่าๆ มารอรับของ (เราใช้ List<Object> เพราะ Generic)
    List<Object> entityList = new ArrayList<>();
    List<BatchEdata> batchEdataList = new ArrayList<>();
	
	

    // 1. ตัวแปรเก็บ Cache โดยใช้ process_id เป็น Key
    private static final Map<Integer, AppBlueprint> blueprintCache = new ConcurrentHashMap<>();
    
    // Dataset ตัวนี้เป็น Global คงที่ โหลดครั้งเดียวพอ
    private static List<dataset> globalDatasets = null; 
    
    
	
	LoadApp LoadApp = new LoadApp();
	
	
	public enum State {
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
	    		
	    		LoadApp_Component LoadApp_Components,
	    		ApproveApp_Component ApproveApp_Components,
	    		RecordApp_Component RecordApp_Components,
	    		App_Component App_Components,
	    		SaveApp_Component SaveApp_Components,
	    		ForwardApp_Component ForwardApp_Components,
	    		ReceiveApp_Component ReceiveApp_Components,
	    	
	    		fileDAO FileDAO,
	    		filemanagerservice Filemanagerservice,
	    		Property_Component Property_Components,
	    		eformfieldDAO EformfieldDAO,
	    		Workflow_Component Workflow_Components,
	    		AppComponent appComponent,
	    		Register_Component Register_Components,
	    		Extension_Component Extension_Components,
	    		Management_Component Management_Components,
	    		Report_Component Report_Components,
	    		LogService logService,
	    		BuildInService buildInService
	    		) { 

	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;
	        this.Userservice = Userservice;
	        this.ConditionDAO = ConditionDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.WorkflowDAO = WorkflowDAO;
	        this.App_Components = App_Components;
	        this.LoadApp_Components = LoadApp_Components;
	        this.ApproveApp_Components = ApproveApp_Components;
	        this.RecordApp_Components = RecordApp_Components;
	        this.SaveApp_Components = SaveApp_Components;
	        this.ForwardApp_Components = ForwardApp_Components;
	        this.ReceiveApp_Components = ReceiveApp_Components;
	        this.FileDAO = FileDAO;
	        this.Filemanagerservice = Filemanagerservice;
	        this.Property_Components = Property_Components;
	        this.EformfieldDAO = EformfieldDAO;
	        this.Workflow_Components = Workflow_Components;
	        this.appComponent = appComponent;
	        this.Register_Components = Register_Components;
	        this.Extension_Components = Extension_Components;
	        this.Management_Components = Management_Components;
	        this.Report_Components = Report_Components;
	        this.logService = logService;
	        this.buildInService = buildInService;
	        
	    }


	 
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
		 * @param loadApp 
		  *
		  * @return the current {@code PlatformService} instance after initialization
		  */
		public  Main_Component initiate(LoadApp loadApp, Map<String, Object> modelData){
			this.LoadApp = loadApp;
			 this.modelData = modelData;
			 this.entityList.clear();
			 this.batchEdataList.clear();
			 return this;
		 }
		 
		

		/**
		 * Loads and prepares platform data for display or further processing.
		 * <p>
		 * This method acts as the entry point when the system needs to retrieve
		 * and assemble all required data components for a given process.
		 * Unlike a simple query, {@code loadPlatform} performs multiple data
		 * retrieval and initialization steps to ensure the platform is fully
		 * ready for use.
		 * </p>
		 *
		 * @param process_id the identifier of the current process or workflow
		 * @param edata_id the identifier of the target data entity to load
		 * @return a {@code LoadApp_Component} instance containing all prepared data
		 */
		public  LoadApp_Component loadPlatForm(Platform platform){
			  PlatForm(platform);
		      return LoadApp_Components;
		 }
		
		/**
		 * Loads and prepares platform data for display or further processing.
		 * <p>
		 * This method acts as the entry point when the system needs to retrieve
		 * and assemble all required data components for a given process.
		 * Unlike a simple query, {@code loadPlatform} performs multiple data
		 * retrieval and initialization steps to ensure the platform is fully
		 * ready for use.
		 * </p>
		 *
		 * @param process_id the identifier of the current process or workflow
		 * @param edata_id the identifier of the target data entity to load
		 * @return a {@code LoadApp_Component} instance containing all prepared data
		 */
		public  LoadApp_Component initPlatformComponents(Platform platform){
			PlatformComponents(platform);
		      return LoadApp_Components;
		 }
		
		private  Main_Component PlatformComponents(Platform platform){
			
			 this.LoadApp.setPlatform(platform);
			 LoadApp_Components.initiate(this.LoadApp,modelData);
			 return this;
		 } 
		

		
		
		/**
		 * Loads and prepares platform data for display or further processing.
		 * <p>
		 * This method acts as the entry point when the system needs to retrieve
		 * and assemble all required data components for a given process.
		 * Unlike a simple query, {@code loadPlatform} performs multiple data
		 * retrieval and initialization steps to ensure the platform is fully
		 * ready for use.
		 * </p>
		 *
		 * @param process_id the identifier of the current process or workflow
		 * @param edata_id the identifier of the target data entity to load
		 * @return a {@code LoadApp_Component} instance containing all prepared data
		 */
		public  LoadApp_Component loadPlatForm(int process_id){
			
			
			process process = EdataDAO.getProcess(process_id);
			List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
			process.setSub_processes(sub_process);
			exposeAs("process",process);
			
			
			 return LoadApp_Components;
		 }
		
		
	
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param relation_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  LoadApp_Component loadRelation(int relation_id){  
			
			
				 this.LoadApp.setProcessing_type("Relation");
				 this.LoadApp.setRelation_id(relation_id);
				 
				return  LoadApp_Components.initiate(this.LoadApp,modelData);
		 }
		
	     
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param process_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Register_Component startAppIndex(Platform platform){  
			   
			
				process process = EdataDAO.getProcess(platform.getApp_processes_id());
				exposeAs("process",process);
				
				
				if(process != null) {
					System.out.println(" process "+process.getId_refer());
					workflow work  = WorkflowDAO.getbyid(process.getId_refer());
					exposeAs("file_patch",work.getWorkflow_folder());
					System.out.println(" work "+work.getWorkflow_folder());
					Userservice.setPoliciesAccessApp(process);
				}   
				
			
				this.LoadApp.setPlatform(platform);
				this.LoadApp.setProcess(process);
				return  Register_Components.initiate(this.LoadApp,modelData);
		 }
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param process_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Management_Component appManagement(Platform platform){  
			   
				process process = EdataDAO.getProcess(platform.getApp_processes_id());
				exposeAs("process",process);
				
				if(platform.getApp_item_id() > 0) {
					Management  thisapp =  EdataDAO.getManagement(platform.getApp_item_id());
					this.LoadApp.setThisapp(thisapp);
				}
				
				if(platform.getApp_step() > 0) {
					
					
					System.out.println(" getApp_stepdraw ");
					
					TabManagement  tab =  EdataDAO.getTabManagement(platform.getApp_step());
					this.LoadApp.setTab_id(tab);
				}
				
				
				
				this.LoadApp.setPlatform(platform);
				this.LoadApp.setProcess(process);
				Userservice.setPoliciesAccessApp(process);
				
				return  Management_Components.initiate(this.LoadApp,modelData);
		 }
		
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param process_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Extension_Component extension(Platform platform){  
			   
			

			
				process process = EdataDAO.getProcess(platform.getApp_processes_id());
				exposeAs("process",process);
				
				exposeAs("item_id",platform.getApp_item_id());
				exposeAs("from_process",0);
				
				if(process != null) {
					System.out.println(" process "+process.getId_refer());
					workflow work  = WorkflowDAO.getbyid(process.getId_refer());
					exposeAs("file_patch",work.getWorkflow_folder());
					System.out.println(" work "+work.getWorkflow_folder());
				}   
			

				
				this.LoadApp.setPlatform(platform);
				this.LoadApp.setProcess(process);
				this.LoadApp.setEdata(platform.getEdata());
				
				
				return  Extension_Components.initiate(this.LoadApp,modelData);
		 }
		
		
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param process_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Report_Component startAppReport(PlatformReport platform){  
			   
			
			System.out.println(" startAppReport ");
			
		
				process process = EdataDAO.getProcess(platform.getApp_processes_id());
				exposeAs("App_processes_id",platform.getApp_processes_id());
				
		
				if(platform.getProcess_refer() > 0) {
					process process_refer = EdataDAO.getProcess(platform.getProcess_refer());
					exposeAs("process_refer",process_refer);
					platform.setProcess_refer_name(process_refer.getName());
					this.LoadApp.setProcess_refer(process_refer);
					
				}
				
				if(platform.getApp_id_refer() > 0) {
					
					System.out.println("App_id_refer "+platform.getApp_id_refer());
					exposeAs("app_id_refer",platform.getApp_id_refer());
				}
				
				if(platform.getApp_item_id() > 0) {
					
					exposeAs("item_id",platform.getApp_item_id());
					exposeAs("from_process",0);
				}
				
			
				
				
				if(process != null) {
					System.out.println(" process "+process.getId_refer());
					workflow work  = WorkflowDAO.getbyid(process.getId_refer());
					exposeAs("file_patch",work.getWorkflow_folder());
					System.out.println(" work "+work.getWorkflow_folder());
				}   
				
				this.LoadApp.setPlatformReport(platform);
				this.LoadApp.setProcess(process);
				this.LoadApp.setEdata(platform.getEdata());
				
				System.out.println(" startAppReport -> Report_Components");
				
				return  Report_Components.initiate(this.LoadApp,modelData);
		 }
		
	

		    private Main_Component PlatForm(Platform platform) {
		        user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		        
		        int process_id = platform.getApp_processes_id();
		        int edata_id = platform.getEdata() == null ? 0 : platform.getEdata().getData_id();

		        // =====================================================================
		        // STEP 1: SMART VERSION CACHING (หัวใจของความเร็ว)
		        // =====================================================================
		        // ดึงแค่ Process มาดูก่อนว่า Version อะไร (Query นี้เบามาก)
		        process currentProcess = EdataDAO.getProcess(process_id);
		        String currentVersion = currentProcess.getVersion(); // สมมติว่าดึง getVersion() ได้
		        if (currentVersion == null || currentVersion.trim().isEmpty()) {
		            currentVersion = "1.0"; // หรือ "0" ตามมาตรฐานระบบของคุณ
		        }
		        
		        AppBlueprint blueprint = blueprintCache.get(process_id);

		        // เช็คว่าไม่มี Cache หรือ Version เปลี่ยนแปลงไปจากเดิม
		        if (blueprint == null || !blueprint.version.equals(currentVersion)) {
		            System.out.println("🔥 โหลด Blueprint ใหม่สำหรับ Process: " + process_id + " (Version: " + currentVersion + ")");
		            
		            // โหลดประกอบร่างใหม่ทั้งหมด
		            currentProcess.setSub_processes(EdataDAO.getAllSubProcess(currentProcess.getId()));
		            
		            workflow newWork = WorkflowDAO.getbyid(currentProcess.getId_refer());
		            newWork.setWorkflow_maindb("Production");
		            
		            condition newCond = ConditionDAO.getbyid(currentProcess.getId_condition());
		            List<keymap> newKeymap = Filemanagerservice.setKeymap(currentProcess.getSub_processes());
		            
		            // แพ็คลงกล่องแล้วโยนเข้า Cache
		            blueprint = new AppBlueprint(currentVersion, currentProcess, newWork, newCond, newKeymap);
		            blueprintCache.put(process_id, blueprint);
		        }

		        // ใช้งานข้อมูลจาก Blueprint (ไม่ว่าจะมาจาก Cache หรือโหลดใหม่ ก็พร้อมใช้แล้ว)
		        exposeAs("process", blueprint.processData);
		        exposeAs("work", blueprint.workData);
		        exposeAs("conditions", blueprint.condData);
		        exposeAs("condition_id", blueprint.condData.getCondition_id());
		        
		        // Dataset จัดการแบบ Global Cache (ถ้าว่างให้โหลด ถ้ามีแล้วดึงจาก Memory)
		        if (globalDatasets == null) {
		            globalDatasets = DatasetDAO.getall();
		        }
		        exposeAs("datasets", globalDatasets);


		        // =====================================================================
		        // STEP 2: DYNAMIC LOADING (ส่วนที่ต้องโหลดตามรายเคส)
		        // =====================================================================
		        policies Policies = Userservice.getPolicies(currentProcess.getId());
		        exposeAs("Policies", Policies); 
		        
		        List<file> files = null;

		        if (edata_id > 0) {
		            edata edatas = EdataDAO.getbyid(edata_id);
		            
		            
			         files = FileDAO.getallEdata(edatas);
			        exposeAs("files", files);
		            
		            buildInService.executeBuildIn(edatas);
		            exposeAs("edatas", edatas);
		            
		            docPoperty(edata_id);

		            Idoc docs = Workflow_Components.checkAndCreateWorkflow(
		                    blueprint.processData.getSub_processes().get(0).getId(), 
		                    edatas.getData_id(), 
		                    edatas.getData_eformid(), 
		                    edatas.getData_eformrv(), 
		                    usersed.getUser_id()
		            );
		            
		            
		            String docDateStr = docs.getDate(); // เช่น "2026-03-27 12:49" หรือ "2026-03-27"

		            if (docDateStr != null && !docDateStr.isEmpty() && currentProcess.getExpires_in_days() > 0) {
		                
		                // 1. ตัดเอาแค่วันที่ (ตัดตรงช่องว่างแรก หรือเอาแค่ 10 ตัวอักษรแรก)
		                String onlyDate = docDateStr.split(" ")[0]; 
		                // หรือจะใช้ String onlyDate = docDateStr.substring(0, 10); ก็ได้ผลเหมือนกันครับ

		                // 2. แปลง String เป็น LocalDate
		                LocalDate docDate = LocalDate.parse(onlyDate);
		                
		                // 3. ดึงวันที่ปัจจุบัน
		                LocalDate currentDate = LocalDate.now().minusDays(1);
		                
		                // 4. หาจำนวนวันที่ต่างกัน 
		                // (เอา docDate เป็นตัวตั้ง เทียบกับปัจจุบัน)
		                long daysPassed = ChronoUnit.DAYS.between(docDate, currentDate);
		                
		                // แสดงผล
		                System.out.println("วันที่เอกสารเต็มๆ: " + docDateStr);
		                System.out.println("วันที่ตัดมาคำนวณ: " + onlyDate);
		                System.out.println("วันที่ปัจจุบัน: " + currentDate.toString());
		                System.out.println("ผ่านมาแล้ว: " + daysPassed + " วัน");
		                
		                // ส่งค่าไปใช้งานต่อที่หน้าจอ
		                exposeAs("daysPassed", daysPassed);
		                
			                if(daysPassed > currentProcess.getExpires_in_days()) {
			                	 Workflow_Components.setDocExpires(edatas.getData_id(),currentProcess.getClosing_status_expired(),currentProcess.getSub_process_expired());
			                }
		                
		            }
		            
		            
		            
		            
		            
		            exposeAs("flow", docs);
		            
		            if (docs.getReceiver() > 0) {
		                user userwork = UserDAO.getbyId2(docs.getReceiver());
		                exposeAs("userwork", userwork);
		            }
		            
		            Optional<sub_processes> data = blueprint.processData.getSub_processes().stream()
		                    .filter(row -> row.getId() == docs.getCurrent_step_int())
		                    .findFirst();
		            if (data.isPresent()) {
		                this.LoadApp.setSub_processes(data.get());
		                exposeAs("thisSub_processes", data.get());
		            }
		            
		            exposeAs("docs", docs);
		            Userservice.setPermission(docs);
		            exposeAs("tempId", edatas.getData_id());
		            
		            this.LoadApp.setEdata(edatas);
		            this.LoadApp.setTempId(String.valueOf(edatas.getData_id()));
		            
		        } else {
		            System.out.println("IDOC:/Main_Component/PlatForm : edata_id == 0");
		        }

		        // =====================================================================
		        // STEP 3: โหลดเข้า LoadApp
		        // =====================================================================
		        this.LoadApp.setId(0);
		        this.LoadApp.setFile(files);
		        this.LoadApp.setCondition(blueprint.condData);
		        this.LoadApp.setWorkflow(blueprint.workData);
		        this.LoadApp.setProcess(blueprint.processData);
		        this.LoadApp.setKeymap(blueprint.keymapData);
		        this.LoadApp.setProcessing_type("PlatForm");
		        this.LoadApp.setPlatform(platform);
		        
		        if (this.LoadApp.getApp_model() == null) {
		            if (platform.getApp_entity_type() != null) {
		                this.LoadApp.setApp_model(platform.getApp_entity_type());
		            } else {
		                Edata_maindata entity_type = EdataDAO.getEdatamainData(edata_id);
		                if (entity_type != null) {
		                    this.LoadApp.setApp_model(entity_type.getEntity() + "s.");
		                }
		            }
		        }
		        
		        LoadApp_Components.initiate(this.LoadApp, modelData);
		        
		        return this;
		    }
	
		
		
		
		
		

		private void docPoperty(int edata_id) {
			 doc  mydoc = EdataDAO.findDoc(edata_id);
				exposeAs("mydoc",mydoc);
				 
				Relations relations = EdataDAO.getRelations(edata_id);
				if(relations != null) {
					doc  mydocrefer = EdataDAO.findDoc(relations.getRelations_id());
					exposeAs("mydocrefer",mydocrefer);
				}
				
				 DocumentEffect DocumentEffect = EdataDAO.getDocumentEffect(edata_id);
				 exposeAs("DocumentEffect",DocumentEffect);		
				
		}



		public  SaveApp_Component newFile(Platform platform){
			
		  	user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		  	
		  		//platform.getEdata().getEdata_index_action_type()
		  	
			    this.LoadApp.setEdata(platform.getEdata());
				this.LoadApp.getEdata().setData_nameadd(users.getUser_name());
				this.LoadApp.getEdata().setData_idadd(users.getUser_id());
				this.LoadApp.getEdata().setProcesses_id(platform.getApp_processes_id());
				this.LoadApp.getEdata().setData_title(platform.getName_file());
				this.LoadApp.getEdata().setProcessId(platform.getApp_processes_id());
				
		
				
			
				System.out.println(" processes_id "+platform.getApp_sub_processes_id());
				
				
			process processes = EdataDAO.getProcess(platform.getApp_processes_id());
			this.LoadApp.setProcess(processes);
			
			
			if(processes == null){
				logService.notice(400,"Error (Missing Config) : processes  cannot be null", "SaveApp_Component/newFile",0);
				return SaveApp_Components.createErrorState("Config Missing: Sub Process not found");
			}
	
			
			List<sub_processes> sub_process = EdataDAO.getAllSubProcess(processes.getId());
			
			
			if(sub_process != null) {
				
				System.out.println(" LoadComponent2 "); 
				List<keymap> keymap = EdataDAO.getAllKeyMap(sub_process.get(0).getId());
				Optional<keymap> data  = keymap.stream().findFirst();
				 
				 if(data.isPresent()) {
					 keymap keymaps = data.get();
					 registerkey Registerkey = EdataDAO.getRegisterkey(keymaps.getRegisterkey_id());
					 String appmodel = appComponent.getAppModel(Registerkey,keymaps);
					 
						System.out.println(" appmodel  ="+appmodel); 
					 
					 this.LoadApp.setApp_model(appmodel);
				 }
				 
					System.out.println(" LoadComponent3 "); 
			}else {
				
				logService.notice(400,"Error (Missing Config) : App_model  cannot be null", "SaveApp_Component/newFile",0);
				return SaveApp_Components.createErrorState("Config Missing: Sub Process not found");
			}
			
			
			if(sub_process.size() > 0 && sub_process.get(0).getAction() == null) {

				System.out.println(" sub_process *** "+sub_process.size());
				logService.notice(400,"Error (Missing Config) : processes  cannot be null", "SaveApp_Component/newFile",0);
				return SaveApp_Components.createErrorState("Config Missing: Sub Process not found");
			}
			                 
		
			
			sub_processes sub_processes = EdataDAO.getSubProcess(platform.getApp_sub_processes_id());
			if(sub_processes != null) {
				this.LoadApp.setProcess(processes);
			}
		
			
			workflow  workflow = WorkflowDAO.getbyid(processes.getId_refer());
			List<keymap> keymap = EdataDAO.getAllKeyMap(platform.getApp_processes_id());

				this.LoadApp.setTempId(platform.getApp_tempId());
				this.LoadApp.setId(0);	 
				this.LoadApp.setWorkflow(workflow);
				this.LoadApp.setSub_processes(sub_processes);
				this.LoadApp.setKeymap(keymap);
				this.LoadApp.setPlatform(platform);

			return SaveApp_Components.initiate( this.LoadApp, this.modelData);
		}
		
		
		
		/**
		 * Prepares the entity and platform for saving or updating data.
		 * <p>
		 * This method sets the required values from the provided {@code MultipleModelsDTO}
		 * and {@code Platform} before performing a save or update operation.
		 * It should be called prior to any persistence action to ensure all entity
		 * references and platform contexts are correctly initialized.
		 * </p>
		 *
		 * @param dto the data transfer object containing entity values
		 * @param platform the platform context associated with this operation
		 * @return the current {@code SaveApp_Component} instance after entity setup
		 */
		public  SaveApp_Component setEntity(MultipleModelsDTO dto, Platform platform){

	
	   	user users = UserDAO.getbyusername2(Userservice.getLoggedName());
	    this.LoadApp.setEdata(platform.getEdata());
		this.LoadApp.getEdata().setData_nameadd(users.getUser_name());
		this.LoadApp.getEdata().setData_idadd(users.getUser_id());
		this.LoadApp.getEdata().setProcessId(platform.getApp_processes_id());
		this.LoadApp.setItem_id(platform.getApp_tempitemid());
		
		if(this.LoadApp.getBatchEdatas() != null) {
			this.LoadApp.getBatchEdatas().clear();
		}
		
		
		if(platform.getBatchEdata() != null) {
			 System.err.println("BatchEdata ->BatchEdata");	
			this.LoadApp.setBatchEdata(platform.getBatchEdata());
		}
		 System.err.println("saveapp ->ck->0");	
		 this.LoadApp.getEdata().setProcesses_id(platform.getApp_processes_id());
		
		
		 

		 System.err.println("saveapp ->ck->EdataDAO saves");
		process processes = EdataDAO.getProcess(platform.getApp_processes_id());
		
		condition Condition = ConditionDAO.getbyid(processes.getId_condition());
		
		sub_processes sub_processes = EdataDAO.getSubProcess(platform.getApp_sub_processes_id());
		workflow  workflow = WorkflowDAO.getbyid(processes.getId_refer());
		List<keymap> keymap = EdataDAO.getAllKeyMap(platform.getApp_processes_id());
		
	

		this.LoadApp.setTempId(platform.getApp_tempId());
		this.LoadApp.setId(0);	 
		this.LoadApp.setWorkflow(workflow);
		this.LoadApp.setProcess(processes);
		this.LoadApp.setSub_processes(sub_processes);
		this.LoadApp.setKeymap(keymap);
		this.LoadApp.setCondition(Condition);	 
		this.LoadApp.setPlatform(platform);
		mapEntity(this.LoadApp);
		mapListEntity(this.LoadApp);
		
		
			   //SaveApp(LoadApp);
			 //  model.addAttribute("tempId",edatasave.getData_id());  
			   
	      return SaveApp_Components.initiate( this.LoadApp, this.modelData);
	 }

		
		
		
		
		
   

		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 * @param <T>
		 * @param platform 
		 * @param actionbatch 
		 *
		 * @param relation_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  <T> LoadApp stageToBatchForDelete(Object actionbatch, Platform platform){  
		
			    List<T> list =  (List<T>)actionbatch;
			    list.remove(platform.getApp_item_id());
				this.LoadApp.setActionbatch(list);
				return   this.LoadApp;
		 }
		
    
	
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 * @param <T>
		 * @param platform 
		 * @param actionbatch 
		 *
		 * @param relation_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Property_Component applyUserSettings(PlatformUserSettings platformUserSettings){  
		
			
			
			this.LoadApp.setPlatformUserSettings(platformUserSettings);
			process processes=  EdataDAO.getProcess(platformUserSettings.getProcesses_id());
			this.LoadApp.setProcess(processes);
			
				return   Property_Components.initiate( this.LoadApp, this.modelData);
		 }
		
		
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 * @param <T>
		 * @param platform 
		 * @param actionbatch 
		 *
		 * @param relation_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Property_Component Property(Platform platform){  
		
				workflow workflows = WorkflowDAO.getbyid(platform.getApp_workflow_id());
				exposeAs("workflows",workflows); 
				this.LoadApp.setWorkflow(workflows);
				 
				 
				 
				if(platform.getApp_step() > 0) {
					
					sub_processes sub_processes =  EdataDAO.getSubProcess(platform.getApp_step());
					exposeAs("sub_processes",sub_processes); 
					process processes=  EdataDAO.getProcess(sub_processes.getProcesse_id());
				 	exposeAs("processes",processes); 
					condition conditions = ConditionDAO.getbyid(processes.getId_condition());
					List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(), conditions.getCondition_rv());
					exposeAs("eformfields",eformfields); 
				 	this.LoadApp.setSub_processes(sub_processes);
				 	
				 	
				}else {
					
					process processes=  EdataDAO.getProcess(platform.getApp_processes_id());
				 	exposeAs("processes",processes); 
				}
		
				
				
				
				
				return   Property_Components.initiate( this.LoadApp, this.modelData);
		 }
		
		

		public Property_Component applyConfig(PlatformConfig platformConfig) {
			// TODO Auto-generated method stub
			
			sub_processes sub_processes =  EdataDAO.getSubProcess(platformConfig.getSub_processes_id());
			
			
			if(!platformConfig.getActivate_register().equals("-")) {
				sub_processes.setAction_type(1);  
				sub_processes.setAction(platformConfig.getActivate_register()); 
			}
			if(!platformConfig.getActivate_action().equals("-")) { 
				sub_processes.setAction_type(3);
				sub_processes.setAction(platformConfig.getActivate_action()); 
			}
			if(!platformConfig.getActivate_autoid().equals("-")) { 
				sub_processes.setActivate_autoid( Integer.valueOf(platformConfig.getActivate_autoid()));
				sub_processes.setAutoiddatacolumn( platformConfig.getAutoiddatacolumn());
				sub_processes.setCode_app(platformConfig.getCode_app());
			}
			

			
			this.LoadApp.setSub_processes(sub_processes);
			
			process processes=  EdataDAO.getProcess(platformConfig.getProcesse_id());
		
			this.LoadApp.setProcess(processes);
			
			if(platformConfig.getKeymap() != null) {
				platformConfig.getKeymap().setProcesses_id(platformConfig.getProcesse_id());
				platformConfig.getKeymap().setSub_processes_id(platformConfig.getSub_processes_id());
				platformConfig.getKeymap().setType(platformConfig.getType());
				this.LoadApp.setMap(platformConfig.getKeymap());
			}
			
			
			this.LoadApp.setConfig(platformConfig);
			
			return   Property_Components.initiate( this.LoadApp, this.modelData);
		}

		

		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param relation_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  LoadApp_Component LoadComponent(Platform platform){  
			
				
			   if(platform.getApp_processes_id() > 0) {
				   
				int process_id = platform.getApp_processes_id();
		
					process process = EdataDAO.getProcess(process_id);
					condition conditions = ConditionDAO.getbyid(process.getId_condition()); 
					exposeAs("process",process); 
					exposeAs("conditions",conditions); 
					
					List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
					process.setSub_processes(sub_process);
					exposeAs("sub_process",sub_process); 
					
					this.LoadApp.setProcess(process);	
					this.LoadApp.setCondition(conditions);
					exposeAs("allsub_process",sub_process); 
			  
					workflow workflows = WorkflowDAO.getbyid(process.getId_refer());
					exposeAs("workflows",workflows); 
					this.LoadApp.setWorkflow(workflows);
					
					List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
					exposeAs("eformfields",eformfields); 
					
					List<Group_label> listgroup_label =  ConditionDAO.getGroup_label();
					exposeAs("listgroup_label",listgroup_label); 
					
			   
			   }else {
					workflow workflows = WorkflowDAO.getbyid(platform.getApp_workflow_id());
					exposeAs("workflows",workflows); 
					this.LoadApp.setWorkflow(workflows);
			   }
				
				
			
			
			  
				
				System.out.println(" LoadComponent "); 
				
				
				return  LoadApp_Components.initiate(this.LoadApp,modelData);
		 }
		
		
		
		
		/**
		 * Loads and prepares related data linked to the specified relation identifier.
		 * <p>
		 * This method retrieves additional or dependent information associated
		 * with the main entity or process. It is typically used to load relational
		 * data that has been linked or referenced by the current platform context.
		 * </p>
		 *
		 * @param relation_id the identifier of the related data to be loaded
		 * @return a {@code LoadApp_Component} containing the prepared related data
		 */
		public  Workflow_Component workflow(Platform platform){  
			
				process process = EdataDAO.getProcess(platform.getApp_processes_id());
				condition conditions = ConditionDAO.getbyid(process.getId_condition()); 
				edata edatas = EdataDAO.getbyid(platform.getApp_item_id());
				exposeAs("edatas",edatas); 
				
				List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				process.setSub_processes(sub_process);
				
				workflow workflows = WorkflowDAO.getbyid(process.getId_refer());
				exposeAs("workflows",workflows); 
				this.LoadApp.setWorkflow(workflows);
				
				
				
				
				this.LoadApp.setProcess(process);	
				this.LoadApp.setCondition(conditions);
				this.LoadApp.setEdata(edatas);
				this.LoadApp.setPlatform(platform);
				
				System.out.println(" LoadComponent "); 

				exposeAs("process",process); 
				
				
				System.out.println(" Workflow_step =  "+platform.getWorkflow_step()); 
				
				if(platform.getWorkflow_step() > 0) {
					sub_processes subprocess = EdataDAO.getSubProcess(platform.getWorkflow_step());
					this.LoadApp.setSub_processes(subprocess);
				}
				

				if(sub_process != null) {
					
					System.out.println(" LoadComponent2 "); 
					List<keymap> keymap = EdataDAO.getAllKeyMap(sub_process.get(0).getId());
					Optional<keymap> data  = keymap.stream().findFirst();
					 
					 if(data.isPresent()) {
						 keymap keymaps = data.get();
						 registerkey Registerkey = EdataDAO.getRegisterkey(keymaps.getRegisterkey_id());
						 String appmodel = appComponent.getAppModel(Registerkey,keymaps);
						 
							System.out.println(" appmodel  ="+appmodel); 
						 
						 this.LoadApp.setApp_model(appmodel);
					 }
					 
						System.out.println(" LoadComponent3 "); 
				}
	
				
				return  Workflow_Components.initiate(this.LoadApp,modelData);
		 }
		
		
		
		
		
		
		

	@Transactional(rollbackFor = Exception.class)
	public void FlowApp(LoadApp LoadApp) {

			System.err.println("FlowApp -> ");
			ApproveApp_Components.Flow(LoadApp);
			ReceiveApp(LoadApp);
	}
		
		
		
	//ApproveApp

    @Transactional(rollbackFor = Exception.class)
	public void ApproveApp(LoadApp LoadApp) {
		

		
		if(LoadApp.getApprove().getActiontype().equals("Pass")) {
		//ออกรหัสเอกสาร
		ApproveApp_Components.IssueCode(LoadApp);
		ApproveApp_Components.ApproveData(LoadApp);
		ApproveApp_Components.ApproveBatchData(LoadApp);
			
			

			ApproveApp_Components.Flow(LoadApp);
		}else {
			ApproveApp_Components.BackFlow(LoadApp);
		}
		


		ReceiveApp(LoadApp);
		CompletionApp(LoadApp);

		
	}



	public void ForwardApp(LoadApp LoadApp) {
		
		if (LoadApp.getSub_processes().getActivate_endaction() == 0) { return; }
		if (LoadApp.getSub_processes().getEndaction_list() == null) { return; }
		if(LoadApp.getSub_processes().getEndaction_list().contains("forward")){	
			System.out.println(" Forward Running ...........");
			ForwardApp_Components.SendTransaction(LoadApp);
	    }
		
	      
	}
	

	
	public void CompletionApp(LoadApp LoadApp) {
		if (LoadApp.getEdata() == null) { return; }
		
		/*
		Idoc flow =  flowServices.getDocument(LoadApp.getEdata().getData_id());
			if(flow.getCurrent_step().equals("Completion")){
			
				System.out.println(" CompletionApp Running ...........");
				System.out.println(" getSub_processes ID  ..........." + LoadApp.getSub_processes().getId());
		
				
				RecordApp(LoadApp);
				ForwardApp(LoadApp);
				
		
				
				   
		  }
		  */
	}



	public void ReceiveApp(LoadApp LoadApp) {
		
		if (LoadApp.getProcess() == null) { return; }
		if (LoadApp.getProcess().getSub_processes() == null) { return; }
		
		if (LoadApp.getProcess() == null) { return; }
		
		 List<sub_processes> sub_processes = LoadApp.getProcess().getSub_processes();
		
		
			//มี การ  reference ไหม
		   Optional<sub_processes> receive = sub_processes.stream()
			.filter(row->
			row.getActivate_reference_only() == null  
			&& row.getActivate_hierarchical_reference() == null
		    && row.getActivate_reference_continue() == null
		    && row.getActivate_reference_modify() == null)
		    .findFirst();
		   
			  if(!receive.isPresent()) {
				  return;
			  } 
	
	    		//หาว่าเคยผ่าน Create มายัง
	    	Optional<sub_processes> step_receive = sub_processes.stream()
	    		  .filter(row->row.getId() <= LoadApp.getSub_processes().getId() )
	    		  .filter(row->row.getStep().equals("Create") )
	    		  .findFirst();
	    		  if(step_receive.isPresent()) {
	    			  ReceiveApp_Components.ReceiveTransaction(LoadApp);
	    	} 
	    	 
	
		 	
	
	}





	public void Follow_upApp(com.smepro.models.LoadApp LoadApp) {
		// TODO Auto-generated method stub
		
	}

	private String getState() {
		return States.toString();
	}
	 public Map<String, Object> getModelData() {
	        return modelData;
	    }
	 private void exposeAs(String attribute,Object list) {
	         modelData.put(attribute, list);
	 }

	  private void mapEntity(LoadApp loadApp) {
			
		  String model = loadApp.getPlatform().getApp_model();
		  
		  System.out.println(" mapEntity model  "+model);
			 if( model== null) {
				 return;
			 }
		  loadApp.setApp_model(model);
		  Object entity = createEntity(model);
		  setEntityToLoadApp(loadApp, model, entity);
		  setEntity(loadApp, entity);
	}


		private void setEntity(LoadApp loadApp, Object data) {
		
			    List<eformfield> eformfields =
			            EformfieldDAO.getall(
			                loadApp.getCondition().getCondition_id(),
			                loadApp.getCondition().getCondition_rv()
			            );
		
			    List<keymap> keymaps =
			            EdataDAO.getAllKeyMaps(loadApp.getProcess().getId());
		
			    edata edata = loadApp.getEdata();
		
			    int i = 1;
			    
			    Object value = getFieldValue(edata, "data_id");
			    setFieldValue(data,"edata_id", value);
			    
			    value= getFieldValue(edata, "customer");
			    setFieldValue(data,"customer_name", value);
			    
			    
			    
			    for (eformfield row : eformfields) {
		
			        Optional<keymap> keymapOpt = keymaps.stream()
			                .filter(k -> Integer.parseInt(k.getEformkey()) == row.getField_id())
			                .filter(k -> "create".equals(k.getType()))
			                .findFirst()
			                .or(() -> keymaps.stream()
			                        .filter(k -> Integer.parseInt(k.getEformkey()) == row.getField_id())
			                        .findFirst());
		
			        if (keymapOpt.isPresent()) {
			            keymap keymap = keymapOpt.get();
		
			            value = getFieldValue(edata, "data_value" + row.getField_postion());
			            
			            System.out.println(" ------------------------------------------ ");
			            System.out.println(" Field_postion  "+row.getField_postion());
			            System.out.println(" Field_id  "+row.getField_id());
			            System.out.println(" Field_name  "+row.getField_label());
			            System.out.println(" value  "+value);
			            System.out.println(" Mainkey "+keymap.getMainkey());
			            System.out.println(" ------------------------------------------ ");
			            
			            if (value != null) {
			                setFieldValue(data, keymap.getMainkey(), value);
			            }
			        }
		
			        i++;
			    }
			    
			    
			}
			private void setFieldValue(Object target, String fieldName, Object value) {
				    try {
				        Field field = target.getClass().getDeclaredField(fieldName);
				        field.setAccessible(true);
			
				        // รองรับ type แปลงพื้นฐาน
				        Object convertedValue = value;
				        field.set(target, convertedValue);
			
				    } catch (NoSuchFieldException e) {
				        // field ไม่มี → ข้าม
				    } catch (IllegalAccessException e) {
				        throw new RuntimeException(e);
				    }
				}
			private Object getFieldValue(Object target, String fieldName) {
			    try {
			        Field field = target.getClass().getDeclaredField(fieldName);
			        field.setAccessible(true);
			        return field.get(target);
			    } catch (NoSuchFieldException | IllegalAccessException e) {
			        return null;
			    }
			}
			
			
			private Object createEntity(String modelName) {
				// ตัด . ทิ้ง
				String clean = modelName.replace(".", "");

				// เช่น edata_registers → edata_register
				String className = clean.endsWith("s")
						? clean.substring(0, clean.length() - 1)
						: clean;

				// 1. กำหนดรายชื่อ Package ที่ต้องการค้นหา
				String[] packages = {
					"com.smepro.models.",
					"com.smepro.entity."
				};
		        
				for (String basePackage : packages) {
					String fullClassName = basePackage + className;
					try {
						Class<?> clazz = Class.forName(fullClassName);
						return clazz.getDeclaredConstructor().newInstance();
						
					} catch (ClassNotFoundException e) {
						// ถ้าหาคลาสใน Package นี้ไม่เจอ ก็ลอง Package ถัดไป
						continue; 
					} catch (Exception e) {
						// จัดการ Exception อื่น ๆ ที่เกิดขึ้นระหว่างการสร้าง Instance
						throw new RuntimeException("Error creating entity for class: " + fullClassName, e);
					}
				}

				// ถ้าวนซ้ำครบทุก Package แล้วยังหาไม่เจอ
				throw new RuntimeException("Cannot find class for model: " + modelName 
		                                   + " in packages: models or entity.");
			}
			
			
			private void setEntityToLoadApp(LoadApp loadApp, String modelName, Object entity) {
			    try {
			        String clean = modelName.replace(".", "");
			        String fieldName = clean.endsWith("s")
			                ? clean.substring(0, clean.length() - 1)
			                : clean;

			        Method setter = loadApp.getClass().getMethod(
			                "set" + capitalize(fieldName),
			                entity.getClass()
			        );

			        setter.invoke(loadApp, entity);

			    } catch (Exception e) {
			        throw new RuntimeException(e);
			    }
			}
			private String capitalize(String text) {
			    return text.substring(0, 1).toUpperCase() + text.substring(1);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			private void mapListEntity(LoadApp loadApp) {
				
				if( loadApp.getBatchEdata() == null) { return;}
				
			    String model = loadApp.getPlatform().getApp_model();
			    
				 if( model== null) {
					 System.out.println(" mapListEntity model  "+model);
					 return;
				 }
			    
			    // 2. ส่งไปเติมของ ใส่ List (Logic การแตก CSV อยู่ในนี้)
			    setListEntity(loadApp, this.entityList, model);
			    
			    // 3. ยัด List กลับเข้า LoadApp (Setter ต้องรองรับ List)
			    setListToLoadApp(loadApp, model, this.entityList);
			}
			
			
			private void setListEntity(LoadApp loadApp, List<Object> targetList, String modelName) {
				if (targetList != null) targetList.clear(); // เทของเก่าในกล่องเดิมทิ้ง
			    // 1. ดึง Config
			    List<eformfield> eformfields = EformfieldDAO.getall(
			            loadApp.getCondition().getCondition_id(),
			            loadApp.getCondition().getCondition_rv()
			    );
			    List<keymap> keymaps = EdataDAO.getAllKeyMaps(loadApp.getProcess().getId());
			    BatchEdata sourceEdata = loadApp.getBatchEdata();
			

			    Class<?> entityClass = findEntityClass(modelName); 
			    if (entityClass == null) {
			        throw new RuntimeException("Cannot find class for model: " + modelName);
			    }

			    // ---------------------------------------------------------
			    // STEP 2: เตรียมข้อมูล (Pre-process Data)
			    // ---------------------------------------------------------
			    Map<Integer, String[]> dataCache = new HashMap<>();
			    int maxRows = 0;
			    for (int i = 1; i <= 30; i++) {
			        Object rawVal = getFieldValue(sourceEdata, "data_value" + i);
			        if (rawVal != null) {
			            String[] splitValues = rawVal.toString().split(",", -1);
			            dataCache.put(i, splitValues);
			            if (splitValues.length > maxRows) maxRows = splitValues.length;
			        }
			    }

			    // ---------------------------------------------------------
			    // STEP 2.5: เตรียม TempID และ Method Setter (ทำครั้งเดียว)
			    // ---------------------------------------------------------
			    // 1. แปลง ID เป็น String รอไว้ (ใช้เหมือนกันทั้ง Entity และ BatchEdata)
			    String tempIdValue = String.valueOf(loadApp.getEdata().getData_id());

			    // 2. หา Method setTempId ของ Entity Class
			    Method setEntityTempId = null;
			    try {
			        setEntityTempId = entityClass.getMethod("setTempId", String.class);
			    } catch (NoSuchMethodException e) {
			        // ไม่เจอ Method ใน Entity ก็ปล่อยผ่าน (แต่อาจจะ log เตือน)
			    }


			    // ---------------------------------------------------------
			    // STEP 3: วนลูป (Loop Rows)
			    // ---------------------------------------------------------
			    try {
			        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
			            
			            // สร้าง Object
			            Object entity = entityClass.getDeclaredConstructor().newInstance();
			            BatchEdata newBatchRow = new BatchEdata();
			            copyBaseBatchData(sourceEdata, newBatchRow); 

			            // =======================================================
			            // [จุดที่เพิ่ม] ยัด tempId ใส่ทั้งคู่
			            // =======================================================
			            
			            // 1. ยัดใส่ Entity (Dynamic)
			            if (setEntityTempId != null) {
			                setEntityTempId.invoke(entity, tempIdValue);
			            }

			            // 2. ยัดใส่ BatchEdata (Static Class)
			            // (สมมติ BatchEdata มี method setTempId(String) ถ้าไม่มีให้ใช้ setFieldValue helper)
			            newBatchRow.setTempId(tempIdValue); 
			            // หรือถ้าใช้ Helper: setFieldValue(newBatchRow, "tempId", tempIdValue);

			            // =======================================================

			            boolean hasData = false; 
			            int colIndex = 1;
			            for (eformfield row : eformfields) {
			                String[] values = dataCache.get(colIndex);
			                String val = (values != null && rowIndex < values.length) ? values[rowIndex].trim() : "";
			                
			                if (values != null && rowIndex >= values.length && values.length > 0) val = ""; // Handle array bounds

			                if (val != null && !val.isEmpty()) {
			                    hasData = true; 
			                    
			                    // Map ลง Entity
			                    Optional<keymap> keymapOpt = keymaps.stream()
			                            .filter(k -> Integer.parseInt(k.getEformkey()) == row.getField_id())
			                            .findFirst();
			                    if (keymapOpt.isPresent()) {
			                         setFieldValue(entity, keymapOpt.get().getMainkey(), val);
			                    }
			                    
			                    // Map ลง BatchEdata
			                    setFieldValue(newBatchRow, "data_value" + colIndex, val);
			                }
			                colIndex++;
			            }
			            
			            if (hasData) {
			                targetList.add(entity);
			                batchEdataList.add(newBatchRow);
			            }
			        }
			        
			        // อย่าลืม set List กลับเข้า loadApp
			        loadApp.setBatchEdatas(batchEdataList);

			    } catch (Exception e) {
			        throw new RuntimeException("Error mapping list entity", e);
			    }
			}
			// Method ช่วย copy ข้อมูลพื้นฐานที่ไม่ใช่ data_value1-30
			private void copyBaseBatchData(BatchEdata source, BatchEdata target) {
			    target.setData_idadd(source.getData_idadd());
			    target.setData_nameadd(source.getData_nameadd());
			    target.setProcesses_id(source.getProcesses_id());
			    target.setData_title(source.getData_title());
			    target.setId_refer(source.getId_refer());
			    target.setReference(source.getReference());
			    target.setExtension_id(source.getExtension_id());
			    target.setData_eformid(source.getData_eformid());
			    target.setData_eformrv(source.getData_eformrv());
			    // ... copy field อื่นๆ ที่จำเป็น (ยกเว้น data_value1-30)
			}
			
			
			private Class<?> findEntityClass(String modelName) {
			    String clean = modelName.replace(".", "");
			    String className = clean.endsWith("s") 
			            ? clean.substring(0, clean.length() - 1) 
			            : clean;

			    String[] packages = {
			        "com.smepro.models.",
			        "com.smepro.entity."
			    };

			    for (String basePackage : packages) {
			        try {
			            return Class.forName(basePackage + className);
			        } catch (ClassNotFoundException e) {
			            continue; 
			        }
			    }
			    return null;
			}
			
			
			
			
			
			
			private void setListToLoadApp(LoadApp loadApp, String modelName, List<Object> entityList) {
			    try {
			        String clean = modelName.replace(".", "");
			        String fieldName = clean.endsWith("s")
			                ? clean.substring(0, clean.length() - 1)
			                : clean;

			     // -----------------------------------------------------------
			     // [ส่วนที่ 1] ยัด tempId ใส่ทุกตัวใน List
			     // -----------------------------------------------------------
			     if (entityList != null && !entityList.isEmpty()) {
			         try {
			             // 1. ดึงค่า ID (int) มาแปลงเป็น String ทันที
			             int rawId = loadApp.getEdata().getData_id();
			             String tempIdValue = String.valueOf(rawId); // แปลง int -> String ตรงนี้!

			             // 2. หา Method setTempId จาก Class
			             Class<?> entityClass = entityList.get(0).getClass();
			             Method setTempIdMethod = null;

			             for (Method m : entityClass.getMethods()) {
			                 // หา setter ชื่อ "setTempId" ที่รับ parameter เป็น String
			                 if (m.getName().equals("setTempId") 
			                         && m.getParameterCount() == 1
			                         && m.getParameterTypes()[0].equals(String.class)) { // เช็คให้ชัวร์ว่ารับ String
			                     setTempIdMethod = m;
			                     break;
			                 }
			             }

			             // 3. ถ้าเจอ Method ก็วนลูปยัดค่า String ใส่เข้าไป
			             if (setTempIdMethod != null) {
			                 for (Object entity : entityList) {
			                     setTempIdMethod.invoke(entity, tempIdValue);
			                 }
			             } else {
			                  System.out.println("Warning: setTempId(String) not found in " + entityClass.getSimpleName());
			             }

			         } catch (Exception ex) {
			             System.err.println("Cannot set tempId: " + ex.getMessage());
			         }
			     }

			        // -----------------------------------------------------------
			        // [ส่วนที่ 2] โค้ดเดิมของคุณ (หา Method Setter ของ LoadApp)
			        // -----------------------------------------------------------
			        String methodName = "setList" + capitalize(fieldName);
			        Method setter = null;
			        for (Method m : loadApp.getClass().getMethods()) {
			            if (m.getName().equals(methodName) && m.getParameterCount() == 1) {
			                if (List.class.isAssignableFrom(m.getParameterTypes()[0])) {
			                    setter = m;
			                    break;
			                }
			            }
			        }

			        // -----------------------------------------------------------
			        // [ส่วนที่ 3] Invoke (เอา List ที่ยัด tempId แล้ว ใส่กลับเข้า loadApp)
			        // -----------------------------------------------------------
			        if (setter != null) {
			            setter.invoke(loadApp, entityList);
			        } else {
			            throw new RuntimeException("Setter method not found: " + methodName);
			        }

			        System.out.println("setListToLoadApp " + methodName + " size: " + (entityList != null ? entityList.size() : 0));

			    } catch (Exception e) {
			        throw new RuntimeException(e);
			    }
			}
			

	}




