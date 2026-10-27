package com.smepro.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.smepro.entity.assets;
import com.smepro.entity.company_annual_plan;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.entity.sales_followup;
import com.smepro.entity.workrule;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Step;
import com.smepro.enums.Stock_status;
import com.smepro.models.Idoc;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.Links;
import com.smepro.models.LoadApp;
import com.smepro.models.Myapp;
import com.smepro.models.PlatformConfig;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.UseExtension;
import com.smepro.models.commitment;
import com.smepro.models.condition;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.eformfield;
import com.smepro.models.keymap;
import com.smepro.models.process;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.repository.AssetsRepository;
import com.smepro.repository.Company_annual_planRepository;
import com.smepro.repository.Edata_annual_planRepository;
import com.smepro.repository.Edata_assetsRepository;
import com.smepro.repository.Edata_workruleRepository;
import com.smepro.repository.ProductRepository;
import com.smepro.repository.RegisterRepository;
import com.smepro.repository.StockRepository;
import com.smepro.repository.WorkruleRepository;
import com.smepro.service.AppComponent;
import com.smepro.service.AppInboxService;
import com.smepro.service.FlowServices;
import com.smepro.service.Uploadfileservice;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Calendar;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Extension;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.FileInfo;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.dto.SourceDTO;
import  com.smepro.entity.Register_list;
import  com.smepro.entity.Register_stock;
import  com.smepro.entity.Register_product;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Extension_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	private Edata_assetsRepository Edata_assets;
	private Edata_annual_planRepository Edata_annual_plan;
	private Edata_workruleRepository Edata_workrule;
	private AppInboxService appInboxService;
	
	private AssetsRepository AssetsRepository;
	private Company_annual_planRepository Annual_planRepository;
	private WorkruleRepository WorkruleRepository;
	
	private Uploadfileservice Uploadfileservices;
	
	
	
	private StockRepository StockRepository;
	private RegisterRepository RegisterRepository;
	private ProductRepository ProductRepository;
	private AppComponent appComponent;
	private Production Productions;
	private eformfieldDAO EformfieldDAO;
	private otherservice Otherservice;
	
	
	private State States = State.NONE;
	

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
	    		Edata_assetsRepository Edata_assets,
	    		Edata_annual_planRepository Edata_annual_plan,
	    		Edata_workruleRepository Edata_workrule,
	    		
	    		AssetsRepository AssetsRepository,
	    		Company_annual_planRepository Annual_planRepository,
	    		WorkruleRepository WorkruleRepository,
	    		
	    		StockRepository StockRepository,
	    		RegisterRepository RegisterRepository,
	    		ProductRepository ProductRepository,
	    		AppComponent appComponent,
	    		Production Productions,
	    		eformfieldDAO EformfieldDAO,
	    		AppInboxService appInboxService,
	    		Uploadfileservice Uploadfileservices,
	    		otherservice Otherservice
	    		
	    		
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
	        this.Edata_annual_plan = Edata_annual_plan;
	        this.Edata_workrule = Edata_workrule;
	        
	        this.AssetsRepository = AssetsRepository;
	        this.Annual_planRepository = Annual_planRepository;
	        this.WorkruleRepository = WorkruleRepository;
	        this.StockRepository = StockRepository;
	        this.RegisterRepository = RegisterRepository;
	        this.ProductRepository = ProductRepository;
	        this.appComponent = appComponent;
	        this.Productions = Productions;
	        this.EformfieldDAO = EformfieldDAO;
	        this.appInboxService = appInboxService;
	        this.Uploadfileservices = Uploadfileservices;
	        this.Otherservice = Otherservice;
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
	private List<String> ability = new ArrayList<String>();
	
	private  Map<String, Object> modelData = new HashMap<>();
	private  Map<String, Object> modelDatas; 
	List<registerkey> heads = new ArrayList<registerkey>();
	List<eformfield> eformfields = new ArrayList<eformfield>();

	
	LoadApp LoadApp = new LoadApp();
	private int step;
	boolean drawTable;

	public Extension_Component initiate(LoadApp loadApp, Map<String, Object> modelDatas) {
		this.modelDatas = modelDatas;
		this.modelData.clear();
		this.heads.clear();
		this.eformfields.clear();
		this.drawTable = true;
		this.LoadApp = loadApp;
		this.step = 0;
		return this;

	}

	public  Extension_Component index(){
		
		UseExtension  useextension =  EdataDAO.getUseExtension(LoadApp.getPlatform().getExtension_process());
		
  	    drawForm();
	   drawTable(useextension);
	   drawLinkProcess(useextension);
		return this;
	}
	


	public  Extension_Component prepareConfig(){
		drawForm();
		return this;
	}
	
	public  Extension_Component requireExampleData(){
		drawForm();
		exampleData();
		return this;
	}
	public  Extension_Component requireExampleDataNper(){
		drawForm();
		exampleDataNper();
		return this;
	}
	
	
	
	public  Extension_Component item(){
		loadItem();
		loadExtension();
		return this;
	}
	
	public  Extension_Component show(){
		showEdata();
		return this;
	}
	public  Extension_Component cl(){

		return this;
	}
	

	public void load() {
		modelDatas.putAll(modelData);
	}
	
	public void save() {
		

		
		int  eformId = saveEdata();
		updateLatestData(this.LoadApp.getEdata().getReference());
		autoUpdateLatestData(eformId);
		resultSegment(eformId);
		saveFiles(eformId);
		saveCalendar(eformId);
	}



	



	public void delete() {
		deleteEdata();
	}
	public void cancelDelete() {
		cancelDeleteEdata();	
	}
	
	
	
	


	private int saveEdata() {
		
		if(this.LoadApp.getEdata() == null) {return 0;}
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		LoadApp.getEdata().setData_nameadd(usersed.getUser_name());
		LoadApp.getEdata().setData_idadd(usersed.getUser_id());
		return  EdataDAO.saves(this.LoadApp.getEdata());
	}
	private void saveFiles(int eformId) {
		// TODO Auto-generated method stub
		
		
		Uploadfileservices.initiate(LoadApp).upload();
		String fileid = Uploadfileservices.getFileInfo().stream()
		        .map(row -> String.valueOf(row.getFile_id()))
		        .collect(Collectors.joining(","));
		
		this.LoadApp.getEdata().setData_id(eformId);
		this.LoadApp.getEdata().setData_filepath(fileid);
		EdataDAO.saves(this.LoadApp.getEdata());
		
	}

	private void saveCalendar(int eformId) {
		
		UseExtension  useextension =  EdataDAO.getUseExtension(LoadApp.getPlatform().getExtension_process());
		List<ExtensionParameter> ExtensionParameter = EdataDAO.getallExtensionParameterAndeformfieldByuseextension(useextension.getId());

		
		Calendar calendar = new Calendar();
		
		for(ExtensionParameter Parameter : ExtensionParameter ) {
			
			if(Parameter.getParameter_keyname().equals("schedule_date")) {
				ReflectionValue(calendar,this.LoadApp.getEdata(),Parameter.getField_postion(),"schedule_date");
			}
			else if(Parameter.getParameter_keyname().equals("schedule_start_alert")) {
				
			}
			else if(Parameter.getParameter_keyname().equals("schedule_title")) {
				ReflectionValue(calendar,this.LoadApp.getEdata(),Parameter.getField_postion(),"schedule_title");
			}
			else if(Parameter.getParameter_keyname().equals("schedule_time_start")) {
							
			}
			else if(Parameter.getParameter_keyname().equals("schedule_time_end")) {
				
			}
		}
		
		if(calendar.getEvent() != null && calendar.getYear() > 0) {
			calendar.setEform_id(eformId);
			calendar.setIndex_id(this.LoadApp.getEdata().getId_refer());
			calendar.setTo_name(this.LoadApp.getEdata().getCustomer());
			calendar.setBy_name(this.LoadApp.getEdata().getData_nameadd());
			calendar.setProcess_id(0);
			EdataDAO.saveCalendar(calendar);
		}
		
		
		
		

	}

private void deleteEdata() {
	    
	    // 1. เตรียม Format วันที่ปัจจุบันสำหรับ Soft Delete
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String currentDateStr = localDate.format(formatter); // วันที่ลบ (วันนี้)
	    
	    // 2. ดึงข้อมูล
	    edata checkdel = EdataDAO.getbyid(this.LoadApp.getEdata().getData_id());
	    String rawDateString = checkdel.getData_dateadd(); // ค่าดิบ เช่น "2025-01-11 14:30:00"

	    System.out.println("Original Date String: " + rawDateString);

	    // 🚀 เพิ่มเงื่อนไขเช็ก null ตรงนี้
	    if (rawDateString == null || rawDateString.trim().isEmpty()) {
	        System.out.println("Case: Date is NULL or Empty -> Soft Delete");
	        EdataDAO.softDelete(this.LoadApp.getEdata().getData_id(), currentDateStr);
	    } else {
	        // -----------------------------------------------------------
	        // *** ถ้าไม่ null: ตัดเวลาทิ้ง ถ้ามี ***
	        // -----------------------------------------------------------
	        String dateStringOnly;
	        
	        if (rawDateString.contains(" ")) {
	            // ถ้ามีช่องว่าง (มีเวลา) ให้หั่นแล้วเอาตัวแรก
	            dateStringOnly = rawDateString.split(" ")[0]; 
	        } else if (rawDateString.length() >= 10) {
	             // หรือใช้วิธีตัดคำเอา 10 ตัวแรก (yyyy-MM-dd)
	            dateStringOnly = rawDateString.substring(0, 10);
	        } else {
	            dateStringOnly = rawDateString; // กรณีไม่มีเวลาอยู่แล้ว หรือรูปแบบแปลกๆ
	        }
	        // -----------------------------------------------------------

	        try {
	        	// 3. แปลง String เป็น LocalDate
	            java.time.LocalDate dataDate = java.time.LocalDate.parse(dateStringOnly);
	            
	            // 4. หา date ปัจจุบัน
	            java.time.LocalDate today = java.time.LocalDate.now();

	            // 🌟 สร้างจุดตัด (Threshold): ย้อนหลังไป 2 เดือนจากวันนี้
	            java.time.LocalDate twoMonthsAgo = today.minusMonths(2);

	            // 5. เปรียบเทียบ: เช็คว่าวันที่ของข้อมูล "เก่ากว่า 2 เดือนที่แล้ว" หรือไม่?
	            if (dataDate.isBefore(twoMonthsAgo)) {
	                // ---------------------------------------------------------
	                // CASE: อดีต (เมื่อวาน หรือ นานมาแล้ว) -> เก็บประวัติ (Soft Delete)
	                // ---------------------------------------------------------
	                System.out.println("Case: Past Date (History) -> Soft Delete");
	                EdataDAO.softDelete(this.LoadApp.getEdata().getData_id(), currentDateStr);      
	                
	            } else {
	                // ---------------------------------------------------------
	                // CASE: วันนี้ หรือ อนาคต -> ลบทิ้งได้เลย (Hard Delete)
	                // ---------------------------------------------------------
	                System.out.println("Case: Today or Future -> Hard Delete");
	                EdataDAO.delete(this.LoadApp.getEdata().getData_id());
	            }
	        } catch (java.time.format.DateTimeParseException e) {
	             // ถ้าแปลงวันที่ไม่สำเร็จ (รูปแบบผิดเพี้ยนหนักมาก) ให้ Soft Delete กันเหนียวไว้ก่อน
	             System.out.println("Error parsing date. Fallback to Soft Delete.");
	             EdataDAO.softDelete(this.LoadApp.getEdata().getData_id(), currentDateStr);
	        }
	    }
	    
	    // ลบ Calendar เสมอ
	    EdataDAO.deleteCalendar(this.LoadApp.getEdata().getData_id());
	}
	
	
	
	private void cancelDeleteEdata() {
		EdataDAO.cancelDelete(this.LoadApp.getEdata().getData_id());
	}
	
	
	
	private void showEdata() {
		edata  edata =  EdataDAO.getbyid(this.LoadApp.getEdata().getData_id());
	
		System.out.println(" edata " +edata.getData_id());
		
		this.modelData.put("edata", edata);
	}

	private void loadExtension() {
		if(this.LoadApp.getProcess() == null) { return; }
		List<UseExtension>  useextension =  EdataDAO.getallUseExtension(this.LoadApp.getProcess().getId());
		this.modelData.put("useextension", useextension);
	}


	private void loadItem() {
		// TODO Auto-generated method stub
		
		if(this.LoadApp.getProcess().getRegister() == null) { return; }
		if(this.LoadApp.getPlatform().getApp_item_id() ==  0 ) { return; }

		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" -> Productions.getRegister(this.LoadApp,this.modelData);
		}	
		
		
	}


	private void exampleData() {
		List<edata>  edatalist =   EdataDAO.getAllByExtension(LoadApp.getPlatform());
		this.modelData.put("edatalist", edatalist);
		
		System.out.println(" exampleData edatalist -> " +edatalist.size());
	}
	
	private void exampleDataNper() {
		List<edata>  edatalist =   EdataDAO.getAllByExtensionNper(LoadApp.getPlatform());
		this.modelData.put("edatalist", edatalist);
		
		System.out.println(" exampleData edatalist -> " +edatalist.size());
	}
	
	
	
	private void loadItemCl() {
		// TODO Auto-generated method stub
		
	}
	
	

	private void drawForm() {
		// TODO Auto-generated method stub
		UseExtension  useextension =  EdataDAO.getUseExtension(LoadApp.getPlatform().getExtension_process());
		this.modelData.put("useextension", useextension);
		
		System.out.println(" drawForm ");
		System.out.println(" useextension  "+LoadApp.getPlatform().getExtension_process());
		System.out.println(" Eform_id "+useextension.getEform_id());
		
		
		if(useextension.getEform_id() == 0) { 
			boolean drawTable = false;
			return;
		}
		
		condition conditions = ConditionDAO.getbyid(useextension.getEform_id());
		
		System.out.println(" conditions "+conditions.getCondition_id());
		
		this.eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
	
		eformfields.stream().forEach(row->{
			List<dataset_data> dataset_data  = Dataset_dataDAO.getall(row.getField_datareferid()) ;
    		row.setDataset_data(dataset_data);
    		
    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf(row.getField_datareferid()));
    		row.setDataset(dataset);
		});

	
		
		
		List<eformfield> raw_eformfields = new ArrayList<>(eformfields);
		
		this.modelData.put("raw_eformfields", raw_eformfields);
		this.modelData.put("eformfields", eformfields);
		
	}

	private void drawTable(UseExtension useextension) {
		

		if(this.drawTable== false) { return; }
		if(this.LoadApp.getProcess()== null) { return; }
		if(useextension.getExtension_id() == 2 ) {return;}
		
		
	//	String[] heads =  this.LoadApp.getProcess().getListview().split(",");

		this.heads = EdataDAO.getRegisterkeyByListview(LoadApp.getProcess().getListview());
		this.modelData.put("heads", heads);
		this.modelData.put("drawTable", true);
	
				
				List<edata>  edatalist =   EdataDAO.getAllByExtension(LoadApp.getProcess().getId(),LoadApp.getPlatform().getApp_item_id(),LoadApp.getPlatform().getExtension_process());
				
				// 1. เตรียมค่าวันที่ปัจจุบันไว้เทียบ
				java.time.LocalDate today = java.time.LocalDate.now();
		
				if(edatalist.size() > 0) {
				// 2. วนลูปเช็คเงื่อนไข
				edatalist.stream().forEach(item -> {
				    try {
				        // --- ส่วนแปลงวันที่ (Safe Parsing) ---
				        String rawDate = item.getData_dateadd();
				        String dateStringOnly = (rawDate != null && rawDate.contains(" ")) 
				                                ? rawDate.split(" ")[0] 
				                                : rawDate;
				        
				        java.time.LocalDate itemDate = java.time.LocalDate.parse(dateStringOnly);
				        
				        // --- ส่วนเช็คเงื่อนไข (Logic) ---
				        // เงื่อนไข: "ถ้าวันที่มากกว่าปัจจุบัน (อนาคต) หรือ เป็นรายการที่ Soft Delete ไปแล้ว"
				        boolean isFuture = itemDate.isBefore(today);
				        boolean isSoftDeleted = (item.getSoft_delete() != null && !item.getSoft_delete().equals("")); 
		
				        if (isFuture && !isSoftDeleted) {
				            item.setAlert(1); // กำหนดให้แจ้งเตือน
				        } else {
				            item.setAlert(0); // ปกติ
				        }
		
				    } catch (Exception e) {
				        // กันเหนียว: ถ้าวันที่ผิด format หรือเป็น null ให้เป็น 0 ไปก่อน
				        System.err.println("Date parse error id: " + item.getData_id());
				        item.setAlert(0);
				    }
				});
	
				
		this.modelData.put("edatalist", edatalist);

				}	
		
		
		
	 	 
	}

	
	private void drawLinkProcess(UseExtension useextension) {
		

		String code = getIndex(useextension);
		
		System.out.println(" drawLinkProcess-----> "+LoadApp.getPlatform().getExtension_process());
		List<ExtensionParameter> ExtensionParameter = EdataDAO.getallExtensionParameterByuseextension(useextension.getId());
		linkAbility(ExtensionParameter);
		this.modelData.put("useextension", useextension);
		if(useextension.getUse_process_id()== 0) { return; }
		this.modelData.put("linkprocess", 1);
		
		appInboxService.initiate().loadProcessesBylink(useextension,ExtensionParameter,code);
		
		
		Map<String, Object> data = appInboxService.getModelData();
		this.modelData.putAll(data);
		
		
		
	}


	private String getIndex(UseExtension useextension) {
		if(useextension == null ) {return null;}
		
		Extension  extension =  EdataDAO.getExtension(useextension.getExtension_id());
		
		switch (extension.getExtension_type()) {
			case "register"->{
			     registers item = EdataDAO.getRegister(LoadApp.getPlatform().getApp_item_id());
				this.modelData.put("CustomerText",item.getRegister_name());
				return item.getRegister_code()+item.getRegister_daterun()+"-"+item.getRegister_id();
			}
		
		}
		return null;
	}

	private void linkAbility(List<ExtensionParameter> extensionParameter) {
		System.out.println(" extensionParameter-----> "+extensionParameter.size());
		
		
		extensionParameter.stream().filter(row-> row.getParameter_keyname().equals("file_name") ).findFirst().ifPresent(row->{
			  fileExtensionAbility(row);
		});
		extensionParameter.stream().filter(row-> row.getParameter_keyname().equals("schedule_date") 
				||  row.getParameter_keyname().equals("schedule_start_alert")).forEach(row->{
			  dateExtensionAbility(row);
		});
		extensionParameter.stream().filter(row-> row.getParameter_keyname().equals("data_insert") ).findFirst().ifPresent(row->{
			  date_insertExtensionAbility(row);
		});
	
	}


	private void date_insertExtensionAbility(ExtensionParameter row) {

	   	 LocalDateTime localDate = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		 String date_insert = localDate.format(formatter);
		 this.modelData.put("date_insert",date_insert);
		
		eformfields.stream().filter(rows->rows.getField_id() == Integer.valueOf(row.getParameter_column()) )
		.findFirst().ifPresent(rows->{
			rows.setField_type("auto_date");
			
		});
	}

	private void fileExtensionAbility(ExtensionParameter row) {
		// TODO Auto-generated method stub
		
		
		System.out.println(" heads-----> "+eformfields.size());
		
		
		eformfields.stream().filter(rows->rows.getField_id() == Integer.valueOf(row.getParameter_column()) )
		.findFirst().ifPresent(rows->{
			rows.setField_cal_type("file");
			this.modelData.put("files",rows);
		});
		
		this.modelData.put("fileExtensionAbility",row.getParameter_column());
	}

	private void dateExtensionAbility(ExtensionParameter row) {
		eformfields.stream().filter(rows->rows.getField_id() == Integer.valueOf(row.getParameter_column()) )
		.findFirst().ifPresent(rows->{
			rows.setField_type("date");
			this.modelData.put("schedule_date",row.getParameter_type());
		});
	}
	
	//Save Extension  and Update  resultSegment
	private void resultSegment(int eformId) {
		
    	
    	System.out.println("resultSegment ...1");
		
		   if (LoadApp.getPlatform().getExtension_process() == 0) return;
		    UseExtension useextension = EdataDAO.getUseExtension(LoadApp.getPlatform().getExtension_process());
		    if(useextension.getAuto_update_segment() == 0 )  return;
		    
	    	System.out.println("resultSegment ..2");
		    
		    
		    edata edataObj = LoadApp.getEdata();
		    int extension_id = edataObj.getExtension_id();
		    int from = Integer.valueOf(edataObj.getReference());
		    System.out.println("resultSegment ..from "+from);
		    
		    if(from == 0)return;
		    
		    sales_followup sf = Productions.getSales_followupByEdata(from);
		    
		    if(sf == null)return;
	    	System.out.println("resultSegment ..3");
	    	String   Segment =   sf.getSegmentation_profile_static_snapshot();
	    	
	      	System.out.println("resultSegment ..4");
	    	int  total_call = EdataDAO.countExtensionAndIdrefer(extension_id,from,0);
	    	int total_call_unique =  EdataDAO.countExtensionAndIdrefer(extension_id,from,1);

	    	System.out.println("resultSegment ..5");
	    	
	    	
	       	System.out.println("resultSegment ..total_call "+total_call);
	    	
	       	System.out.println("resultSegment ..total_call "+total_call_unique);
	       	System.out.println("resultSegment ..Segment "+Segment);
	       	
	       	
	    	EdataDAO.updateSegment(Segment,total_call,total_call_unique);
	    	
	    	edata edatas =  EdataDAO.getbyid(from);
	    	Productions.UseSegmentation(edatas,"Flow");
	}
	
	
	
	
	private void autoUpdateLatestData(int eformId) {
	    if (LoadApp.getPlatform().getExtension_process() == 0) return;

	    UseExtension useextension = EdataDAO.getUseExtension(LoadApp.getPlatform().getExtension_process());

	    // ตรวจสอบ Null
	    if (useextension.getAuto_update_parameter_column() == null || 
	        useextension.getAuto_update_tocolumn() == null) {
	        return;
	    }
	    
	    if (useextension.getAuto_update_tocolumn().equals("0")) { return; }
	    

	     System.out.println(" Auto_update "+useextension.getAuto_update_parameter_column()); 
	    
	    
	    edata edataObj = LoadApp.getEdata();
	    String[] columns = useextension.getAuto_update_parameter_column().split(",");
	    
	    StringBuilder resultBuilder = new StringBuilder();

	    for (String colIndex : columns) {
	        try {
	            // สร้างชื่อ field เช่น "data_value" + "1" = "data_value1"
	            String fieldName = "data_value" + colIndex.trim();
	            
	            // ใช้ Reflection ดึง field จาก class edata
	            Field field = edataObj.getClass().getDeclaredField(fieldName);
	            field.setAccessible(true); // ปลดล็อคให้เข้าถึง private field ได้
	            
	            Object value = field.get(edataObj);
	            
	            if (value != null) {
	                if (resultBuilder.length() > 0) resultBuilder.append(" ");
	                resultBuilder.append(value.toString());
	            }
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            // กรณีหาชื่อคอลัมน์ไม่เจอ หรือเข้าถึงไม่ได้
	            System.err.println("ไม่พบฟิลด์หรือดึงข้อมูลไม่ได้: data_value" + colIndex);
	        }
	    }

	    String finalResult = resultBuilder.toString();
	    System.out.println("ผลลัพธ์รวม: " + finalResult);
	    
	    
	    String tocolumns = useextension.getAuto_update_tocolumn();
	    registers Registers = EdataDAO.getRegister(edataObj.getId_refer());
	    
	       try {
	            // สร้างชื่อ field เช่น "data_value" + "1" = "data_value1"
	            String fieldName = "extension_column_" + tocolumns;
	            
	            // ใช้ Reflection ดึง field จาก class edata
	            Field field = Registers.getClass().getDeclaredField(fieldName);
	            field.setAccessible(true); // ปลดล็อคให้เข้าถึง private field ได้
	            field.set(Registers, finalResult);
	            
	            
	            EdataDAO.updateExtensionRegister(Registers);
	     
	        } catch (NoSuchFieldException | IllegalAccessException e) {

	        }
	    
	    
	    // หลังจากนี้คุณสามารถนำ finalResult ไป save ลง useextension.getAuto_update_tocolumn() ต่อไป
	}
	
	
	
	
	private void ReflectionValue(Calendar calendar, edata edata, int fieldPosition, String type) {
	    try {
	        // ชื่อ field เช่น data_value1
	        String fieldName = "data_value" + fieldPosition;

	        // หา method เช่น getData_value1()
	        String getterName = "get" + 
	                Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

	        Method getter = edata.getClass().getMethod(getterName);

	        // ดึงค่าจริง ๆ ออกมา
	        Object value = getter.invoke(edata);

	        if(value == null) return;

	        // ถ้าเป็น date -> แยกลง calendar
	        if(type.equals("schedule_date")) {
	        	
	            // รองรับทั้ง String หรือ java.util.Date
	            if(value instanceof String str) {
	                // แปลง String → Date
	                LocalDate localDate = LocalDate.parse(str); // รูปแบบ yyyy-MM-dd
	                calendar.setYear(localDate.getYear());
	                calendar.setMonth(localDate.getMonthValue()); 
	                calendar.setDay(localDate.getDayOfMonth());
	            }
	        }else if(type.equals("schedule_title")) {
	            if(value instanceof String str) {
	                calendar.setEvent(str);
	            }
	        }
	        
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void updateLatestData(String eformId) {
	    // 1. ดักค่าว่างก่อนเลย
	    if (eformId == null || eformId.trim().isEmpty()) {
	        return; 
	    }

	    try {
	        // 2. ลองแปลงเป็นตัวเลข
	        int parsedId = Integer.parseInt(eformId.trim());
	        
	        // 3. ถ้าแปลงสำเร็จ ค่อยส่งไปให้ DAO อัปเดต
	        EdataDAO.updateLatestData(parsedId);
	        
	    } catch (NumberFormatException e) {
	        // 4. ถ้าแปลงไม่ได้ (เช่นเป็นตัวอักษร) ก็ไม่ต้องทำอะไร ออกจากฟังก์ชันไปเลย
	        return;
	    }
	}

	
}
