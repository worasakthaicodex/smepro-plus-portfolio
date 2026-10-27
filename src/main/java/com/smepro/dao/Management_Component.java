package com.smepro.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.Column;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

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
import com.smepro.models.Management;
import com.smepro.models.Myapp;
import com.smepro.models.PlatformConfig;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.TabManagement;
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
import com.smepro.models.notification;
import com.smepro.models.process;
import com.smepro.models.product_master;
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
import com.smepro.repository.Sales_followupRepository;
import com.smepro.repository.StockRepository;
import com.smepro.repository.WorkruleRepository;
import com.smepro.service.AppComponent;
import com.smepro.service.BuildInService;
import com.smepro.service.FlowServices;
import com.smepro.service.Workflow_Component;
import com.smepro.service.userservice;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.BuildIn;
import com.smepro.models.EdataAppManagement;
import com.smepro.models.EdataReportbuildIn;
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
public class Management_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private eformfieldDAO EformfieldDAO;
	private workflowDAO WorkflowDAO;
	private Edata_assetsRepository Edata_assets;
	private Edata_annual_planRepository Edata_annual_plan;
	private Edata_workruleRepository Edata_workrule;
	private Sales_followupRepository salesFollowupRepository;
	
	private AssetsRepository AssetsRepository;
	private Company_annual_planRepository Annual_planRepository;
	private WorkruleRepository WorkruleRepository;
	

	private StockRepository StockRepository;
	private RegisterRepository RegisterRepository;
	private ProductRepository ProductRepository;
	private AppComponent appComponent;
	private Production Productions;
	private 	notificationDAO NotificationDAO;
	private IdocDAO IdocDAO;
	private BuildInService buildInService;
	
	private Workflow_Component Workflow_Components;
	
	
	
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
	    		notificationDAO NotificationDAO,
	    		 IdocDAO IdocDAO,
	    		 Sales_followupRepository salesFollowupRepository,
	    		 BuildInService buildInService,
	    		 Workflow_Component Workflow_Components
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
	        this.NotificationDAO = NotificationDAO;
	        this.IdocDAO = IdocDAO;
	        this.salesFollowupRepository = salesFollowupRepository;
	        
	        this.buildInService = buildInService;
	        this.Workflow_Components = Workflow_Components;
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
	private  Map<String, Object> modelDatas ;
	LoadApp LoadApp = new LoadApp();
	private int step;
	private List<eformfield> eformfields = new ArrayList<eformfield>();
	private condition Chain_Condition = new condition();
	List<TabManagement>  tab = new ArrayList<TabManagement>();
	List<Management>  appSelect  = new ArrayList<Management>();
	
	
	
	public Management_Component initiate(LoadApp loadApp, Map<String, Object> modelDatas) {
		this.modelDatas = modelDatas;
		this.modelData.clear();
		this.eformfields.clear();
		this.tab.clear();
		this.appSelect.clear();
		this.LoadApp = loadApp;
		this.Chain_Condition  = new condition(); 
		
		
		this.step = 0;
		return this;

	}

	
	public  Management_Component chainApp(){

	
		
		drawAppSelect();
		drawTab();
		loadHeads();
		filterHeads();
		loadData();
		return this;
	}

	public  Management_Component chainAppData(){
		

		
		drawAppSelect();
		drawTab();
		loadHeads();
		filterHeads();
		loadData();
		return this;
	}

	public  Management_Component Setview(){
		loadHeads();
		return this;
	}
	
	



	private void drawSearchTool() {
		// TODO Auto-generated method stub
		
	}


	public  Management_Component item(){
		loadItem();
		loadExtension();
		return this;
	}
	
	
	public void load() {
		modelDatas.putAll(modelData);
	}
	  public void callback() {
			modelDatas.putAll(modelData);
		}

	
	public Management_Component MassOperationsWorkflowImport() {
		workflowImport();
		return this;
	}
	
	public Management_Component MassOperationsProcessImport() {

		System.out.println("1 MassOperationsProcessImport "+this.LoadApp.getProcess().getId_condition());
		
		
		 condition condition = ConditionDAO.getbyid(this.LoadApp.getProcess().getId_condition());

		 this.LoadApp.setCondition(condition);
		 
		List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv());
		
		
		System.out.println("2 MassOperationsProcessImport "+this.LoadApp.getProcess().getId());
		List<keymap> keymap = EdataDAO.getAllKeyMaps(this.LoadApp.getProcess().getId());
		
		eformfields.forEach(row ->{
			Optional<keymap> data = keymap.stream()
    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
    			    .filter(subrow -> "create".equals(subrow.getType()))
    			    .filter(subrow -> Integer.valueOf(subrow.getRegisterkey_id())  > 0)
    			    .findFirst();

			    	if(data.isPresent()) {
			    			keymap keymaps = data.get();
			    			row.setKeymap(keymaps);
			    			}
		});
		
		System.out.println("2 MassOperationsProcessImport "+keymap.size());
		
		
		
		Optional<keymap> d =   keymap.stream().filter(row-> Integer.valueOf( row.getRegisterkey_id())> 0).findFirst();
		if( d.isPresent()) {
			keymap	map = d.get();
			registerkey Registerkey = EdataDAO.getRegisterkey(map.getRegisterkey_id());
			
			System.out.println(" doc "+Registerkey.getDoc());
				switch (Registerkey.getDoc()) {
					case "Sales followup"->SalesfollowupImport(eformfields);
				
				}
		}
		return this;
	}
	
	
	
	
	private void SalesfollowupImport(List<eformfield> Eformfields) {

	    MultipartFile file = this.LoadApp.getPlatform().getFile();



	    System.out.println("🚀 Start SalesfollowupImport (Split Flow Mode)");



	    if (file == null || file.isEmpty()) {

	        this.modelData.put("Callback", "File is empty");

	        return;

	    }



	    // ==========================================

	    // 🛠️ PREPARE COMMON: เตรียมของส่วนกลางก่อนลงสมรภูมิ

	    // ==========================================

	    int processId = this.LoadApp.getProcess().getId();

	    int conditionId = this.LoadApp.getCondition().getCondition_id();

	    int conditionRv = this.LoadApp.getCondition().getCondition_rv();



	    // 1. โหลด Customers (สำหรับ Main Flow)

	    List<registers> allCustomers = EdataDAO.getAllOldSys();

	    Map<String, registers> customerMap = new HashMap<>();

	    for (registers reg : allCustomers) {

	        if (reg.getId_oldsys() != null) customerMap.put(reg.getId_oldsys().trim(), reg);

	    }



	    // 🌟 2. โหลด Products (สำหรับ Mini Boss 1 - Revision Flow)

	    List<product_master> allProducts = EdataDAO.getAllProdOldSys(); // ⚠️ อย่าลืมสร้าง Method นี้ใน DAO

	    Map<String, product_master> productMap = new HashMap<>();

	    if (allProducts != null) {

	        for (product_master prod : allProducts) {

	            if (prod.getProduct_names() != null) productMap.put(prod.getProduct_names(), prod);

	        }

	    }



	    // 3. กางแผนที่ Eformfields & Keymap

	    Map<String, eformfield> labelMap = new HashMap<>();

	    Map<String, eformfield> mainkeyMap = new HashMap<>();

	    for (eformfield f : Eformfields) {

	        labelMap.put(f.getField_label().trim(), f);

	        if (f.getKeymap() != null && f.getKeymap().getMainkey() != null) {

	            mainkeyMap.put(f.getKeymap().getMainkey().trim(), f);

	        }

	    }



	    // 🌟 4. เตรียม List 2 เส้นทางหลัก

	    List<edata> mainFlowList = new ArrayList<>();      // เส้นทางบอสใหญ่ (ไป Idoc/Sales ด้วย)

	    List<edata> revisionFlowList = new ArrayList<>();  // เส้นทางบอสเล็ก (ไป edata อย่างเดียว)

	    

	    // List คู่ขนานสำหรับ Main Flow

	    List<Idoc> idocList = new ArrayList<>();

	    List<sales_followup> salesList = new ArrayList<>();



	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

	        

	        String headerLine = br.readLine().replace("\uFEFF", "").replace("\"", "");

	        String[] csvHeaders = headerLine.split(",");

	        for (int i = 0; i < csvHeaders.length; i++) csvHeaders[i] = csvHeaders[i].trim();

	        

	        String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

	        String dataLine;



	        // ==========================================

	        // ⚔️ PHASE 1: อ่าน CSV สกัดข้อมูลแบบลูปเดียวจบ!

	        // ==========================================

	        while ((dataLine = br.readLine()) != null) {

	            if (dataLine.trim().isEmpty()) continue;

	            

	            String[] dataValues = dataLine.split(splitRegex, -1);

	            

	            // สร้าง Object รอรับค่าทุกตัว

	            edata myEdata = new edata();

	            Idoc myIdoc = new Idoc();

	            sales_followup mySales = new sales_followup(); 



	            // ตัวแปรเช็คสถานะการแยกร่าง

	            String subDataId = ""; 

	            int revision = 0;

	            String idCustomerOldsys = "";



	            for (int i = 0; i < dataValues.length; i++) {

	                if (i >= csvHeaders.length) break;

	                String headerName = csvHeaders[i];

	                String value = dataValues[i].replace("\"", "").trim();



	                // 📌 งาน 1.1: ดักจับ Field พิเศษ

	                if (headerName.equalsIgnoreCase("id_oldsys")) {

	                    myEdata.setId_oldsys(value);

	                    myIdoc.setReference_oldsys(value);

	                }

	                else if (headerName.equalsIgnoreCase("sub_data_id")) subDataId = value; // 🌟 คีย์แยกร่าง

	                else if (headerName.equalsIgnoreCase("revision")) revision = value.isEmpty() ? 0 : Integer.parseInt(value);

	                else if (headerName.equalsIgnoreCase("id_customer_oldsys")) { idCustomerOldsys = value; myEdata.setId_customer_oldsys(value); }

	                else if (headerName.equalsIgnoreCase("id_product_oldsys")) myEdata.setId_product_oldsys(value);

	                else if (headerName.equalsIgnoreCase("data_idadd")) myEdata.setData_idadd(value.isEmpty() ? 0 : Integer.parseInt(value));

	                else if (headerName.equalsIgnoreCase("data_nameadd")) myEdata.setData_nameadd(value);

	                else if (headerName.equalsIgnoreCase("data_title")) myEdata.setData_title(value);

	                else if (headerName.equalsIgnoreCase("id_refer_oldsys")) {

	               

	                myEdata.setId_refer_oldsys(value);

	                System.out.println("Id_refer_oldsys "+value);

	                }

	                
	                
	                
	                // 📌 งาน 1.2: ยัด Data Value ตามปกติ (ทำงานพร้อมกันทั้ง Edata และ Sales!)
	                else {
	                    eformfield fieldConfig = labelMap.get(headerName);
	                    if (fieldConfig != null) {
	                        // ลง Edata
	                        injectDataValue(myEdata, fieldConfig.getField_postion(), value);
	                        
	                        // ลง Sales_followup (ยึดตามโค้ดที่รันผ่าน)
	                        if (fieldConfig.getKeymap() != null) {
	                            String mKey = fieldConfig.getKeymap().getMainkey();
	                            if (mKey != null && !mKey.trim().isEmpty()) {
	                                injectValueToModel(mySales, mKey.trim(), value);
	                            }
	                        }
	                    }
	                }
	            } // ปิดลูปคอลัมน์

	            
	            
	            
	            // 📌 งาน 2: Set ค่าพื้นฐาน & แปลงค่า Customer
	            myEdata.setData_eformid(conditionId);
	            myEdata.setData_eformrv(conditionRv);
	            myEdata.setRevision(revision); // ⚠️ อย่าลืมเพิ่ม field นี้ใน Model edata (Transient ก็ได้)

	            if (!idCustomerOldsys.isEmpty() && customerMap.containsKey(idCustomerOldsys)) {
	                registers reg = customerMap.get(idCustomerOldsys);
	                
				             // ----------------------------------------------------
				                // 🎯 1. จัดการ Customer Name
				                // ----------------------------------------------------
				                eformfield nameField = mainkeyMap.get("customer_name");
				                if (nameField != null) {
				                    String custName = reg.getRegister_name();
				                    
				                    // ยัดลง Edata
				                    injectDataValue(myEdata, nameField.getField_postion(), custName);
				                    
				                    // 🔥 ยัดลง Sales ด้วย! (เช็ค Mainkey ก่อนยัด)
				                    if (nameField.getKeymap() != null && nameField.getKeymap().getMainkey() != null) {
				                        injectValueToModel(mySales, nameField.getKeymap().getMainkey().trim(), custName);
				                    }
				                }
				                
				                // ----------------------------------------------------
				                // 🎯 2. จัดการ Customer Code
				                // ----------------------------------------------------
				                eformfield codeField = mainkeyMap.get("customerCode");
				                if (codeField != null) {
				                    String fullCode = reg.getRegister_code() + reg.getRegister_daterun() + "-" + reg.getRegister_id();
				                    
				                    // ยัดลง Edata
				                    injectDataValue(myEdata, codeField.getField_postion(), fullCode);
				                    
				                    // 🔥 ยัดลง Sales ด้วย! (เช็ค Mainkey ก่อนยัด)
				                    if (codeField.getKeymap() != null && codeField.getKeymap().getMainkey() != null) {
				                        injectValueToModel(mySales, codeField.getKeymap().getMainkey().trim(), fullCode);
				                    }
				                }
	            }

	            // ======================================================
	            // ⚔️ PHASE 2: แยกทางเดิน (Split Flow)
	            // ======================================================
	            if (subDataId != null && !subDataId.trim().isEmpty() && !subDataId.trim().equals("0")) {
	                // 🔴 เส้นทาง B: Mini Boss 1 (Revision)
	                // เก็บ subDataId ไว้ในช่อง id_refer_oldsys เพื่อเอาไว้หาตัวแม่ทีหลัง
	                myEdata.setId_refer_oldsys(subDataId); 
	                revisionFlowList.add(myEdata);

	            } else {
	                // 🟢 เส้นทาง A: Main Flow (บอสใหญ่)
	                mainFlowList.add(myEdata);
	                idocList.add(myIdoc);
	                salesList.add(mySales);
	            }
	        } // ปิดลูปอ่าน CSV แต่ละบรรทัด

	        // ==========================================
	        // 🚀 PHASE 3: EXECUTE สั่งรบทีละกองทัพ
	        // ==========================================
	        
	        // 1. กองทัพ Main (บอสใหญ่เดิม)
	        if (!mainFlowList.isEmpty()) {
	            System.out.println("🟢 Processing Main Flow: " + mainFlowList.size() + " records");
	            ProcessMainFlow(mainFlowList, idocList, salesList, processId, conditionId, conditionRv);
	        }

	        // 2. กองทัพ Revision (บอสเล็ก 1)
	        if (!revisionFlowList.isEmpty()) {
	            System.out.println("🔴 Processing Revision Flow: " + revisionFlowList.size() + " records");
	            ProcessRevisionFlow(revisionFlowList, productMap, mainkeyMap, processId);
	        }

	        this.modelData.put("Callback", "Success: Processed Main=" + mainFlowList.size() + ", Revision=" + revisionFlowList.size());

	    } catch (Exception e) {
	        e.printStackTrace();
	        this.modelData.put("Callback", "Error: " + e.getMessage());
	    }
	}

	// ====================================================================================
	// 🟢 METHOD: ProcessMainFlow (จัดการเซฟข้อมูลตัวแม่ + Sales + IDOC + Relation)
	// ====================================================================================
	private void ProcessMainFlow(List<edata> edataList, List<Idoc> idocList, List<sales_followup> salesList, int processId, int conditionId, int conditionRv) {
	    
	    // 1. Save & Get ID
	    List<Integer> savedIds = EdataDAO.SaveAllEdataAndGetIds(edataList, processId);

	    // 2. Set ID to Children
	    for (int i = 0; i < edataList.size(); i++) {
	        int newId = savedIds.get(i);
	        edataList.get(i).setData_id(newId);
	        
	        Idoc doc = idocList.get(i);
	        doc.setEform_id(newId);
	        doc.setDocument_id(conditionId);
	        doc.setDocument_rv(conditionRv);
	        doc.setReference_oldsys(edataList.get(i).getId_oldsys()); // ถ้าต้องเซ็ตเพิ่ม
	        
	        
	        
	        
	        sales_followup sales = salesList.get(i);
	        sales.setEdataId(newId);
	        sales.setProcessId(processId);
	    }

	    // 3. Save Children
	    IdocDAO.UpdateIdocBatch(idocList);
	    List<sales_followup> savedSalesList = salesFollowupRepository.saveAll(salesList); 

	    // 4. แทรกงานเชื่อม edata_maindata
	    if (savedSalesList != null && !savedSalesList.isEmpty()) {
	        List<Object[]> batchArgs = new ArrayList<>();
	        for (int i = 0; i < savedSalesList.size(); i++) {
	            int edataId = edataList.get(i).getData_id();
	            int mainId = savedSalesList.get(i).getId(); 
	            batchArgs.add(new Object[] { edataId, mainId, "sales_followup" });
	        }
	        EdataDAO.saveEdatamainDataBatch(batchArgs);
	    }

	    
	 // 5. สร้างตัวเชื่อม Relations (id_refer_oldsys -> ตัวแม่)
	    Set<String> parentOldSysIds = new HashSet<>();
	    for (edata e : edataList) {
	        if (e.getId_refer_oldsys() != null && !e.getId_refer_oldsys().trim().isEmpty()) {
	            parentOldSysIds.add(e.getId_refer_oldsys().trim());
	        }
	    }

	    if (!parentOldSysIds.isEmpty()) {
	        List<edata> parentList = EdataDAO.getEdataByOldSysIds(new ArrayList<>(parentOldSysIds));
	        Map<String, edata> dbParentMap = new HashMap<>();
	        for (edata p : parentList) {
	            dbParentMap.put(p.getId_oldsys(), p);
	        }

	        List<edata> mainList = new ArrayList<>();
	        List<edata> linkList = new ArrayList<>();

	        for (edata currentEdata : edataList) {
	            String referOldSys = currentEdata.getId_refer_oldsys();
	            if (referOldSys != null && !referOldSys.trim().isEmpty()) {
	                edata parentEdata = dbParentMap.get(referOldSys.trim());
	                
	                if (parentEdata != null) {
	                    // 🔥 สลับให้ถูกต้องตรงนี้ครับ!
	                    mainList.add(currentEdata);  // ตัวมันเอง (ตัวลูกที่เพิ่งเข้ามา)
	                    linkList.add(parentEdata);   // ตัวที่ถูกอ้างอิงถึง (ตัวแม่ที่ดึงมาจาก DB)
	                }
	            }
	        }

	        if (!mainList.isEmpty()) {
	            EdataDAO.LinkBatch(mainList, linkList); 
	        }
	    }
	    
	    
	}

	// ====================================================================================
	// 🔴 METHOD: ProcessRevisionFlow (จัดการตัวลูก เอาเฉพาะ Revision ล่าสุด)
	// ====================================================================================
	private void ProcessRevisionFlow(List<edata> revList, Map<String, product_master> productMap, Map<String, eformfield> mainkeyMap, int processId) {
	    
	    // 1. Filter เอาเฉพาะ Revision ล่าสุด (Group by sub_data_id)
	    Map<String, edata> latestRevMap = new HashMap<>();
	    
	    for (edata current : revList) {
	        String parentKey = current.getId_refer_oldsys(); // ค่า sub_data_id ที่ฝากไว้
	        
	        if (!latestRevMap.containsKey(parentKey)) {
	            latestRevMap.put(parentKey, current);
	        } else {
	            // ถ้าตัวใหม่ revision เยอะกว่าตัวเก่า ให้เอาตัวใหม่ทับ
	            if (current.getRevision() > latestRevMap.get(parentKey).getRevision()) {
	                latestRevMap.put(parentKey, current);
	            }
	        }
	    }
	    
	    List<edata> finalRevList = new ArrayList<>(latestRevMap.values());

	    // 2. แมป Product 
	    for (edata e : finalRevList) {
	        String prodOldId = e.getId_product_oldsys();
	        if (prodOldId != null && productMap.containsKey(prodOldId)) {
	            product_master prod = productMap.get(prodOldId);
	            
	            eformfield nameField = mainkeyMap.get("product_names");
	            if (nameField != null) injectDataValue(e, nameField.getField_postion(), prod.getProduct_names());
	            
	            eformfield codeField = mainkeyMap.get("productCode");
	            if (codeField != null) injectDataValue(e, codeField.getField_postion(), prod.getProduct_code() + prod.getProduct_daterun() + "-" + prod.getProduct_id());
	        }
	    }

	    // 3. วิ่งหา ID แม่จาก DB (เพื่อเอามาใส่ tempId)
	    Set<String> parentOldSysIds = latestRevMap.keySet();
	    if (!parentOldSysIds.isEmpty()) {
	        List<edata> parentFromDb = EdataDAO.getEdataByOldSysIds(new ArrayList<>(parentOldSysIds));
	        Map<String, Integer> parentIdMap = new HashMap<>();
	        
	        for (edata p : parentFromDb) {
	            parentIdMap.put(p.getId_oldsys(), p.getData_id());
	        }

	        for (edata e : finalRevList) {
	            String subDataId = e.getId_refer_oldsys(); 
	            if (parentIdMap.containsKey(subDataId)) {
	                e.setTempId(String.valueOf(parentIdMap.get(subDataId))); 
	            } else {
	                System.out.println("⚠️ Orphan Revision: Parent not found for sub_data_id=" + subDataId);
	            }
	        }
	    }

	    // 4. Save ลง edata อย่างเดียว
	    if (!finalRevList.isEmpty()) {
	        EdataDAO.SaveAllEdataOnly(finalRevList, processId); // ⚠️ เมธอดสำหรับเซฟ Edata เปล่าๆ ไม่มี IDOC/Sales
	    }
	}
	
	
	private void SalesfollowupImport2(List<eformfield> Eformfields) {
	    MultipartFile file = this.LoadApp.getPlatform().getFile();

		System.out.println("2 SalesfollowupImport ");
	    
	    
	    if (file == null || file.isEmpty()) {
	        this.modelData.put("Callback", "File is empty");
	        return;
	    }

	    // ==========================================
	    // 🛠️ PREPARE: เตรียมของก่อนลงสมรภูมิ
	    // ==========================================
	    int processId = this.LoadApp.getProcess().getId();
	    int conditionId = this.LoadApp.getCondition().getCondition_id();
	    int conditionRv = this.LoadApp.getCondition().getCondition_rv();

	    // 1. ดึงลูกค้า (Register) มารอไว้ใน RAM
	    List<registers> allCustomers = EdataDAO.getAllOldSys(); // ⚠️ แก้ชื่อ Method ตาม DAO ของพี่
	    Map<String, registers> customerMap = new HashMap<>();
	    for (registers reg : allCustomers) {
	        if (reg.getId_oldsys() != null) customerMap.put(reg.getId_oldsys().trim(), reg);
	    }

	    // 2. กางแผนที่ Eformfields 
	    Map<String, eformfield> labelMap = new HashMap<>();   // หาด้วย Label (หัว CSV)
	    Map<String, eformfield> mainkeyMap = new HashMap<>(); // หาด้วย Mainkey (สำหรับ Customer & Sales)
	    for (eformfield f : Eformfields) {
	        labelMap.put(f.getField_label().trim(), f);
	        if (f.getKeymap() != null && f.getKeymap().getMainkey() != null) {
	            mainkeyMap.put(f.getKeymap().getMainkey().trim(), f);
	        }
	    }

	    // 3. เตรียม List คู่ขนาน (Index ต้องตรงกันเป๊ะ)
	    List<edata> edataList = new ArrayList<>();
	    List<Idoc> idocList = new ArrayList<>();
	    List<sales_followup> salesList = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
	        
	        String headerLine = br.readLine().replace("\uFEFF", "").replace("\"", "");
	        String[] csvHeaders = headerLine.split(",");
	        for (int i = 0; i < csvHeaders.length; i++) csvHeaders[i] = csvHeaders[i].trim();
	        
	        String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	        String dataLine;

	        // ==========================================
	        // ⚔️ PHASE 1: งาน 1, 2, 3 (สกัดข้อมูล & แมปปิ้ง)
	        // ==========================================
	        while ((dataLine = br.readLine()) != null) {
	            if (dataLine.trim().isEmpty()) continue;
	            
	            String[] dataValues = dataLine.split(splitRegex, -1);
	            edata myEdata = new edata();
	            Idoc myIdoc = new Idoc();
	            sales_followup mySales = new sales_followup(); 

	            String idCustomerOldsys = ""; // เอาไว้พักค่า Customer

	            for (int i = 0; i < dataValues.length; i++) {
	                if (i >= csvHeaders.length) break;
	                String headerName = csvHeaders[i];
	                String value = dataValues[i].replace("\"", "").trim();

	                // 📌 งาน 1.1: ดักจับ "ตูด" ลง Edata ตรงๆ
	                if (headerName.equalsIgnoreCase("id_oldsys")) {
	                    myEdata.setId_oldsys(value);
	                    myIdoc.setReference_oldsys(value); // เผื่อ Idoc ใช้ด้วย
	                }
	                else if (headerName.equalsIgnoreCase("id_customer_oldsys")) { idCustomerOldsys = value; myEdata.setId_customer_oldsys(value);}
	                else if (headerName.equalsIgnoreCase("id_product_oldsys")) myEdata.setId_product_oldsys(value);
	                else if (headerName.equalsIgnoreCase("id_refer_oldsys")) myEdata.setId_refer_oldsys(value);
	                else if (headerName.equalsIgnoreCase("data_title")) myEdata.setData_title(value);
	                else if (headerName.equalsIgnoreCase("data_idadd")) {
	                    myEdata.setData_idadd(value.isEmpty() ? 0 : Integer.parseInt(value));
	                }
	                else if (headerName.equalsIgnoreCase("data_nameadd")) myEdata.setData_nameadd(value);
	          
	                
	                
	                else {
	                    // 📌 งาน 1.2: สกัดลง edata (data_value1-30) ตาม Label
	                    eformfield fieldConfig = labelMap.get(headerName);
	                    if (fieldConfig != null) {
	                        int pos = fieldConfig.getField_postion();
	                        injectDataValue(myEdata, pos, value); 
	                        
	                        // 📌 งาน 3: Copy ลง sales_followup ตาม Mainkey
	                        if (fieldConfig.getKeymap() != null) {
	                            String mKey = fieldConfig.getKeymap().getMainkey();
	                            if (mKey != null && !mKey.isEmpty()) {
	                            	injectValueToModel(mySales, mKey, value);
	                            }
	                        }
	                    }
	                }
	            }

	            // 📌 งาน 2: แปลงค่าเป็นไอดีจริง (Customer)
	            if (!idCustomerOldsys.isEmpty() && customerMap.containsKey(idCustomerOldsys)) {
	               registers reg = customerMap.get(idCustomerOldsys);
	                
	                eformfield nameField = mainkeyMap.get("customer_name");
	                if (nameField != null) injectDataValue(myEdata, nameField.getField_postion(), reg.getRegister_name());
	                
	                eformfield codeField = mainkeyMap.get("customerCode");
	                if (codeField != null) {
	                    String fullCode = reg.getRegister_code() + reg.getRegister_daterun() + "-" + reg.getRegister_id();
	                    injectDataValue(myEdata, codeField.getField_postion(), fullCode);
	                }
	            }
	            myEdata.setData_eformid(conditionId);
	            myEdata.setData_eformrv(conditionRv);
	
	            
	            // เก็บเข้า List คู่ขนาน
	            edataList.add(myEdata);
	            idocList.add(myIdoc);
	            salesList.add(mySales);
	        }

	        // ==========================================
	        // ⚔️ PHASE 2: บันทึกลง Database (แจก ID)
	        // ==========================================
	        if (edataList.isEmpty()) {
	            this.modelData.put("Callback", "No data to process.");
	            return;
	        }

	        // 1. Save edata แบบ Batch และดึง ID ที่เพิ่งรันกลับมา (⚠️ พี่ต้องมี Method นี้ใน DAO)
	        List<Integer> savedEdataIds = EdataDAO.SaveAllEdataAndGetIds(edataList, processId);

	        // 2. แจกจ่าย ID ให้ลูกๆ
	        for (int i = 0; i < edataList.size(); i++) {
	            int newEdataId = savedEdataIds.get(i);
	            edataList.get(i).setData_id(newEdataId); // เซ็ตให้ตัวเองด้วย เผื่อใช้ตอน Link

	            Idoc doc = idocList.get(i);
	            doc.setEform_id(newEdataId);
	            doc.setDocument_id(conditionId);
	            doc.setDocument_rv(conditionRv);
	            doc.setReference_oldsys( edataList.get(i).getId_oldsys());
	            
	            sales_followup sales = salesList.get(i);
	            sales.setEdataId(newEdataId);
	            sales.setProcessId(processId);
	        }

	        // 3. Save ลูกๆ ลง DB
	        IdocDAO.UpdateIdocBatch(idocList);
	        salesFollowupRepository.saveAll(salesList);
	        
	        
	        // ==========================================
	        // ⚔️ PHASE 3: งานที่ 4 - สร้างตัวเชื่อม Relations
	        // ==========================================
	        Set<String> parentOldSysIds = new HashSet<>();
	        for (edata e : edataList) {
	            if (e.getId_refer_oldsys() != null && !e.getId_refer_oldsys().trim().isEmpty()) {
	                parentOldSysIds.add(e.getId_refer_oldsys().trim());
	            }
	        }

	        if (!parentOldSysIds.isEmpty()) {
	            // ดึงตัวแม่จาก DB มาล่วงหน้า (⚠️ สร้าง Method นี้ใน EdataDAO ให้รับ List<String> ไป IN Query)
	            List<edata> parentList = EdataDAO.getEdataByOldSysIds(new ArrayList<>(parentOldSysIds));
	            Map<String, edata> dbParentMap = new HashMap<>();
	            for (edata p : parentList) {
	                dbParentMap.put(p.getId_oldsys(), p);
	            }

	         // 🌟 สร้าง List 2 ก้อนมารอรับ ตัวแม่ (Main) และ ตัวลูก (Link)
	            List<edata> mainList = new ArrayList<>();
	            List<edata> linkList = new ArrayList<>();

	            // วนลูปจับคู่
	            for (edata currentEdata : edataList) {
	                String referOldSys = currentEdata.getId_refer_oldsys();
	                
	                if (referOldSys != null && !referOldSys.isEmpty()) {
	                    edata parentEdata = dbParentMap.get(referOldSys.trim());
	                    
	                    if (parentEdata != null) {
	                        // จับใส่ List คู่ขนานกัน (Index จะตรงกันเป๊ะ)
	                        mainList.add(parentEdata);
	                        linkList.add(currentEdata); 
	                    }
	                }
	            }

	            // 🌟 ส่ง List 2 ก้อนไปให้ DAO จัดการเซฟทีเดียว (คลีนๆ ไม่รก!)
	            if (!mainList.isEmpty()) {
	                EdataDAO.LinkBatch(mainList, linkList); 
	            }
	            
	            
	        }

	        this.modelData.put("Callback", "Success: Defeated Boss! Processed " + edataList.size() + " records.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        this.modelData.put("Callback", "Error: " + e.getMessage());
	    }
	}
	
	
	
	
	
	

	private void workflowImport() {
	    MultipartFile file = this.LoadApp.getPlatform().getFile();

	    if (file == null || file.isEmpty()) {
	        this.modelData.put("Callback", "File is empty");
	        return;
	    }

	    List<Idoc> idocList = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
	        
	        // 1. อ่าน Header เพื่อระบุตำแหน่งคอลัมน์
	        String headerLine = br.readLine();
	        if (headerLine == null) {
	            this.modelData.put("Callback", "CSV is empty");
	            return;
	        }

	        // ล้าง BOM และ " ออกจากหัวข้อ
	        headerLine = headerLine.replace("\uFEFF", "").replace("\"", "");
	        String[] csvHeaders = headerLine.split(",");
	        
	        // สร้าง Map เพื่อจำว่า Column Index ไหน คือ Field อะไร
	        Map<Integer, String> colMap = new HashMap<>();
	        for (int i = 0; i < csvHeaders.length; i++) {
	            colMap.put(i, csvHeaders[i].trim());
	        }

	        // 🌟 Regex แบ่งข้อมูล (รองรับคอมม่าในฟิลด์ "149,149")
	        String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	        
	        // 2. วนลูปอ่าน Data และยัดเข้า List ตรงๆ
	        String dataLine;
	        while ((dataLine = br.readLine()) != null) {
	            if (dataLine.trim().isEmpty()) continue;

	            String[] dataValues = dataLine.split(splitRegex, -1);
	            Idoc idoc = new Idoc();
	            
	            for (int i = 0; i < dataValues.length; i++) {
	                if (colMap.containsKey(i)) {
	                    String fieldName = colMap.get(i);
	                    String value = dataValues[i].replace("\"", "").trim();
	                    
	                    // 💉 ฉีดข้อมูลเข้า Idoc ตามชื่อฟิลด์จาก Header
	                    injectValueToModel(idoc, fieldName, value);
	                }
	            }
	            idocList.add(idoc);
	        }

	        // ==========================================
	        // 3. บันทึกลง Database (ตัวตั้งต้น)
	        // ==========================================
	        if (!idocList.isEmpty()) {
	            IdocDAO.SaveAllIdocExtension(idocList);
	            this.modelData.put("Callback", "Success: Imported " + idocList.size() + " records.");
	        } else {
	            this.modelData.put("Callback", "No data found in file.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        this.modelData.put("Callback", "Error: " + e.getMessage());
	    }
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
		// TODO Auto-generated method stub
		if(this.LoadApp.getProcess().getRegister() == null) { return; }
		
		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" -> Productions.getAllRegister(this.LoadApp,this.modelData);
		}	
		
	}


	private void drawTable() {
		if(this.LoadApp.getProcess().getListview() == null) { return; }

	//	String[] heads =  this.LoadApp.getProcess().getListview().split(",");

		List<registerkey> heads = EdataDAO.getRegisterkeyByListview(LoadApp.getProcess().getListview());
		this.modelData.put("heads", heads);
		System.out.println(" -> "+heads.size());
		
		//registerkey
	 	 
	}

	private void drawAppSelect() {
		
		   user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 	    
			appSelect =  EdataDAO.getallManagement(LoadApp.getProcess().getId());
			
			// 1. ดึงค่า Policies ของ User ปัจจุบันออกมาก่อน (เช่น "user_1,d_2")
			String policies = Userservice.getPoliciesString();
			Set<String> userPolicySet = new HashSet<>(Arrays.asList(policies.split(",")));

			// 2. กรองรายการใน appSelect (ตัดอันที่ไม่เข้าเงื่อนไขทิ้ง)
			appSelect.removeIf(item -> {
			    String userAccess = item.getUser_access(); // เช่น "user_1,user_2"
			    if (userAccess == null || userAccess.isEmpty()) {
			        return true; // ถ้าโปรแกรมไม่ได้ระบุสิทธิ์ไว้ ตัดทิ้งไปก่อนเพื่อความปลอดภัย
			    }

			    // แตกสิทธิ์ที่กำหนดไว้ในแต่ละแถวออกเป็นรายการ
			    List<String> requiredAccess = Arrays.asList(userAccess.split(","));
			    
			    // ตรวจสอบว่ามีสิทธิ์ตัวไหนของ User ตรงกับสิทธิ์ที่แถวนี้ต้องการไหม (Any Match)
			    boolean hasAccess = requiredAccess.stream()
			            .anyMatch(accessId -> userPolicySet.contains(accessId.trim()));
			            
			    // ถ้าไม่มีสิทธิ์ที่ตรงกันเลย (hasAccess เป็น false) ให้ return true เพื่อสั่ง removeIf ตัดทิ้ง
			    return !hasAccess; 
			});

			// 3. ใส่ตัวแปรที่กรองแล้วเข้า Model
			this.modelData.put("appSelect", appSelect);
			
			
			if(LoadApp.getThisapp() == null && appSelect.size() > 0) {
				LoadApp.setThisapp(appSelect.get(0));
				
			}
			
			if(LoadApp.getThisapp() != null) {
				process thisTabprocess = EdataDAO.getProcess(LoadApp.getThisapp().getProcess_id());
				this.modelData.put("thisApp",LoadApp.getThisapp());
				this.modelData.put("thisTabprocess",thisTabprocess);
				
			}
			
			
			
			
			// สมมติว่าเรามี ID ของ Tab ปัจจุบัน (ดึงมาจากตัวแปร tab หรือที่ส่งมาจากหน้าบ้าน)
			int currentAppId = LoadApp.getThisapp().getId(); // <--- ใส่ ID ของ Tab ปัจจุบันที่ต้องการเทียบตรงนี้


			// วนลูปหาในรายการทั้งหมด
			for (int i = 0; i < appSelect.size(); i++) {
			    // 1. เช็คว่า ID ตรงกับตัวปัจจุบันไหม
			    if (appSelect.get(i).getId() == currentAppId) {
			        // 2. ถ้าเจอแล้ว ให้เช็คว่า "มีตัวถัดไปไหม" (ต้องไม่ใช่ตัวสุดท้ายของลิสต์)
			        if (i + 1 < appSelect.size()) {
			        	Management nextApp = appSelect.get(i + 1); // ดึงตัวถัดไป
			        	process nextTabprocess = EdataDAO.getProcess(nextApp.getProcess_id());
			  
			            this.modelData.put("nextTabprocess",nextTabprocess);
			        }
			      
			 
			    }    
			    
			    List<notification> notifications = NotificationDAO.getallrefer(String.valueOf(usersed.getUser_id()) ,Userservice.getPoliciesString(),String.valueOf( appSelect.get(i).getProcess_id()) );
			    int e = i;
			    long alert = 	 notifications.stream().filter(rows -> rows.getNotification_process_id() == appSelect.get(e).getId()).count();
			appSelect.get(i).setAlert((int)alert);
			}


			
			System.out.println(" Thisapp Process_id "+LoadApp.getThisapp().getProcess_id());
			
			
			process chainprocess = EdataDAO.getProcess(LoadApp.getThisapp().getProcess_id());
			Chain_Condition = ConditionDAO.getbyid(chainprocess.getId_condition());
			
		  String idapp = 	appSelect.stream().map(row-> String.valueOf( row.getProcess_id())).collect(Collectors.joining(","));
		  idapp += ","+LoadApp.getProcess().getId();
		  
		  EdataDAO.setReferenceProcess(idapp,LoadApp.getProcess().getId());
		  
		  
			int alert = NotificationDAO.countnotification(String.valueOf(usersed.getUser_id()),Userservice.getPoliciesString(),idapp,0);
			this.modelData.put("alert",alert);		
	}


	private void drawTab() {
		tab =  EdataDAO.getallTabManagement(LoadApp.getThisapp().getId());
		List<TabManagement> allTab =  EdataDAO.getallTabManagementByTabProcessId(LoadApp.getProcess().getId());
		
		//System.out.println(" drawTab getTab_id "+LoadApp.getTab_id());
		if(LoadApp.getTab_id() == null && tab.size() > 0) {
			
			LoadApp.setTab_id(tab.get(0));
		}

		if(LoadApp.getTab_id() != null) {
			this.modelData.put("thisTab",LoadApp.getTab_id());
	
		}
		
		 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 	    
		 usersed.setAuth(Userservice.getPoliciesString());
		 
		tab.stream().forEach(row->{
				int box_count = EdataDAO.getAllApp_ManagementCount(LoadApp.getThisapp().getProcess_id(),row,usersed) ;
				row.setBox_total(box_count);
				System.out.println(" management_id "+row.getTab_management_id());
				System.out.println(" box_count "+box_count);
		});

		
	
		
		
		
	
		System.out.println(" appSelect "+appSelect.size());
		
	

		
		this.modelData.put("tabManagement", tab);
			
	}

	private void loadHeads() {
			if(LoadApp.getThisapp() == null) {	return;	}
			if(tab == null) {	return;	}
			if(LoadApp.getTab_id() == null) {	return;	}
			if(LoadApp.getTab_id().getTab_columns() == null) {	return;	}
			
			
			
			
	
		 	eformfields = EformfieldDAO.getall(Chain_Condition.getCondition_id(),Chain_Condition.getCondition_rv()); 
		 	
		 	
		 	
			eformfield fields = new eformfield();
			fields.setField_label("ชื่อเอกสาร*");
			fields.setField_cal_type("T-");
			fields.setField_id(-1);
			fields.setField_datarefercols("data_title");
			eformfields.add(fields);
			
			fields = new eformfield();
			fields.setField_label("วันที่อัพเดต*");
			fields.setField_cal_type("T-");
			fields.setField_id(-2);
			fields.setField_datarefercols("data_dateupdate");
			eformfields.add(fields);
			
			
			fields = new eformfield();
			fields.setField_label("สถานะเอกสาร*");
			fields.setField_cal_type("T-");
			fields.setField_id(-3);
			fields.setField_datarefercols("closing_status");
			eformfields.add(fields);
			
			fields = new eformfield();
			fields.setField_label("การรับเอกสาร*");
			fields.setField_cal_type("T-");
			fields.setField_id(-4);
			fields.setField_datarefercols("acknowledge");
			eformfields.add(fields);
			
			fields = new eformfield();
			fields.setField_label("รหัสเอกสาร*");
			fields.setField_cal_type("T-");
			fields.setField_id(-5);
			fields.setField_datarefercols("doc");
			eformfields.add(fields);
		 	
			fields = new eformfield();
			fields.setField_label("ขั้นตอนเอกสาร*");
			fields.setField_cal_type("T-");
			fields.setField_id(-6);
			fields.setField_datarefercols("current_step");
			eformfields.add(fields);
		 	
		 	
		 	System.out.println("Chain_Condition --> "+LoadApp.getThisapp().getProcess_id());
		 // 1. เตรียม Set ของ ID ที่ต้องการเก็บไว้
		 	Set<String> tabColumnSet = new HashSet<>();
		 	if (LoadApp.getTab_id().getTab_columns() != null) {
		 	    // ใช้ trim() เผื่อกรณีใน DB มีช่องว่าง เช่น "1, 2, 3"
		 	    for (String id : LoadApp.getTab_id().getTab_columns().split(",")) {
		 	        tabColumnSet.add(id.trim());
		 	    }
		 	}
	

	
		 // 1. ลบทุกอย่างใน eformfields (กลุ่ม M-) ที่ไม่อยู่ใน Set นี้ออกก่อนเป็นอันดับแรก!
		 	eformfields.removeIf(row -> 
		    !tabColumnSet.contains("T-" + row.getField_id()) && 
		    !tabColumnSet.contains("M-" + row.getField_id())
		);

		 	// 2. วนลูปเซ็ตค่าลำดับ data_value ใหม่ให้เฉพาะตัวที่เหลืออยู่จริงๆ
		 	int i = 0;
		 	for (eformfield row : eformfields) {


		 		
		 		if(row.getField_cal_type() == null) {
		 	    row.setField_cal_type("M-");
		 	    row.setField_datarefercols("data_value" + row.getField_postion());
		 		}
		 	}

		 	// 3. ดึงข้อมูล BuildIn (กลุ่ม S-) และกรองเอาเฉพาะตัวที่ถูกเลือก
		 	List<BuildIn> buildInList = EformfieldDAO.getallBuildIn(LoadApp.getThisapp().getId()); 
		 	for (BuildIn row : buildInList) {
		 	    // 🔥 เช็คก่อนว่า S-{id} ถูกเลือกไว้ในหน้าจอหรือไม่
		 	    if (tabColumnSet.contains("S-" + row.getId())) {
		 	        eformfield field = new eformfield();
		 	        field.setField_label(row.getBuildin_title());
		 	        field.setField_cal_type("S-");
		 	        field.setField_id(row.getId());
		 	        field.setField_datarefercols("columns_value" + row.getTocolumn());
		 	        
		 	        eformfields.add(field); // แอดเข้า List เฉพาะตัวที่ผ่านเงื่อนไข
		 	        System.out.println("Added S- Field_id: " + row.getId() + " as columns_value" + row.getTocolumn());
		 	    }
		 	}
		
			this.modelData.put("heads", eformfields);
	}


	private void filterHeads() {

		if(tab == null) {	return;	}
		if(LoadApp.getTab_id() == null) {	return;	}
		if(LoadApp.getTab_id().getTab_columns() == null) {	return;	}
		
	    List<String> refOrder = List.of(
	    	LoadApp.getTab_id().getTab_columns().split(",")
	    );

	    // 1️⃣ สร้าง orderMap ให้เสร็จก่อน
	    Map<String, Integer> orderMap = new HashMap<>();
	    for (int i = 0; i < refOrder.size(); i++) {
	        orderMap.put(refOrder.get(i), i);
	    }

	    // 2️⃣ ค่อย sort
	    eformfields.sort(
	        Comparator.comparingInt(f -> {
	            String key = f.getField_cal_type() + f.getField_id();
	            return orderMap.getOrDefault(key, Integer.MAX_VALUE);
	        })
	    );

	   
	}

	private void loadData() {
		if(LoadApp.getThisapp() == null) {	return;	}
		
		System.out.println("Chain_Condition Condition_id "+Chain_Condition.getCondition_id());
		System.out.println("Chain_Condition Condition_rv "+Chain_Condition.getCondition_rv());
	 	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	 	
	 	usersed.setAuth(Userservice.getPoliciesString());
	 	
	 	
		List<EdataAppManagement> edataAppManagement = EdataDAO.getAllApp_Management(Chain_Condition.getCondition_id(),Chain_Condition.getCondition_rv(),LoadApp.getTab_id(),usersed);
		
		Iterator<EdataAppManagement> iterator = edataAppManagement.iterator();
		int count = 0;

		while (iterator.hasNext() && count < 20) {
			EdataAppManagement rows = iterator.next();
		    
		    // โลจิกเดิมของคุณ
		    edata newEdata = new edata();
		    BeanUtils.copyProperties(rows, newEdata);
		    newEdata.setProcessId(rows.getProcesses_id());
		    newEdata.setProcesses_id(rows.getProcesses_id());
		    buildInService.executeBuildIn(newEdata);
		    
		    // สมมติว่า DocExpires return ค่าเป็น boolean
		    boolean isDocValid = DocExpires(rows, rows.getProcesses_id(), Chain_Condition.getCondition_id(), Chain_Condition.getCondition_rv());
		    
		    // ถ้า DocExpires เป็น false ให้ลบ rows นี้ออกจาก edataAppManagement ทันที
		    if (!isDocValid) {
		        iterator.remove(); 
		    }
		    
		    count++;
		}

		
		System.out.println("edataAppManagement size  "+edataAppManagement.size());
		
		
		this.modelData.put("edataAppManagement", edataAppManagement);

		
		
	}
	
	
	
	private int countData(TabManagement tabManagement) {
		if(LoadApp.getThisapp() == null) {	return 0;	}
		
		
			int count = EdataDAO.getCountApp_Management(Chain_Condition.getCondition_id(),Chain_Condition.getCondition_rv(),tabManagement);
			return count;	
		}

	/**
     * ตัวช่วยยัดค่า String เข้าไปใน Model ผ่าน Setter Method (เช่น ชื่อคอลัมน์ "status" -> เรียก method "setStatus")
     */
	private void injectValueToModel(Object targetObject, String fieldName, String value) {
	    try {
	        // สร้างชื่อ Method ให้เป็น Setter เช่น ถ้า fieldName คือ "id_refer" จะเป็น "setId_refer"
	        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	        
	        try {
	            // 🌟 ด่านที่ 1: ลองหา Setter ที่รับค่าเป็น String ก่อน
	            Method stringSetter = targetObject.getClass().getMethod(setterName, String.class);
	            
	            // ========================================================
	            // 🔥 [เพิ่มใหม่] ตรวจสอบและแอบหั่น String ก่อนยัดลง Model
	            // ========================================================
	            if (value != null) {
	                try {
	                    // วิ่งไปดูสเปคของตัวแปรนี้ใน Class
	                    Field field = targetObject.getClass().getDeclaredField(fieldName);
	                    int maxLength = 255; // ตั้งค่า Default ของ Hibernate เอาไว้กันเหนียว
	                    
	                    // ถ้ามีการใส่ @Column(length=...) ไว้ ก็ให้ดึงค่านั้นมาใช้
	                    if (field.isAnnotationPresent(Column.class)) {
	                        maxLength = field.getAnnotation(Column.class).length();
	                    }
	                    
	                    // ถ้ายาวเกินโควต้า หั่นตูดทิ้งทันที!
	                    if (value.length() > maxLength) {
	                        // System.out.println("✂️ หั่นคำ: " + fieldName + " จาก " + value.length() + " เหลือ " + maxLength);
	                        value = value.substring(0, maxLength);
	                    }
	                } catch (NoSuchFieldException ne) {
	                    // เผื่อกรณีหาชื่อ Field ไม่เจอเป๊ะๆ (เช่น ตัวเล็กตัวใหญ่ไม่ตรง) 
	                    // ให้เซฟตี้ตัวเองด้วยการตัดที่ 255 ไว้ก่อนเลย
	                    if (value.length() > 255) {
	                        value = value.substring(0, 255);
	                    }
	                }
	            }
	            // ========================================================

	            // สั่งยัดค่า (ที่ถูกหั่นแล้ว) ลงไป!
	            stringSetter.invoke(targetObject, value);
	            
	        } catch (NoSuchMethodException e) {
	            // 🌟 ด่านที่ 2: ถ้าหา String ไม่เจอ ให้ลองหา Setter ที่รับค่าเป็น int
	            try {
	                Method intSetter = targetObject.getClass().getMethod(setterName, int.class);
	                
	                // แปลง String เป็น int ก่อนยัด
	                int intValue = 0;
	                if (value != null && !value.trim().isEmpty()) {
	                    intValue = Integer.parseInt(value.trim()); // แปลงตัวเลข
	                }
	                
	                intSetter.invoke(targetObject, intValue);
	                
	            } catch (NoSuchMethodException ex) {
	                System.out.println("Warning: Setter not found for field " + fieldName + " (tried both String and int)");
	            } catch (NumberFormatException ex) {
	                // ดักจับกรณี CSV มีตัวอักษรขยะปนมาในช่องตัวเลข
	                System.out.println("Warning: Invalid number format for field " + fieldName + " with value [" + value + "]");
	            }
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Error setting value for field " + fieldName + ": " + e.getMessage());
	    }
	}


	
	// Helper 1: สกัดค่าลง edata ตามตำแหน่ง 1-30
	private void injectDataValue(edata e, int position, String value) {
	    switch (position) {
	        case 1 -> e.setData_value1(value);
	        case 2 -> e.setData_value2(value);
	        case 3 -> e.setData_value3(value);
	        case 4 -> e.setData_value4(value);
	        case 5 -> e.setData_value5(value);
	        case 6 -> e.setData_value6(value);
	        case 7 -> e.setData_value7(value);
	        case 8 -> e.setData_value8(value);
	        case 9 -> e.setData_value9(value);
	        case 10 -> e.setData_value10(value);
	        case 11 -> e.setData_value11(value);
	        case 12 -> e.setData_value12(value);
	        case 13 -> e.setData_value13(value);
	        case 14 -> e.setData_value14(value);
	        case 15 -> e.setData_value15(value);
	        case 16 -> e.setData_value16(value);
	        case 17 -> e.setData_value17(value);
	        case 18 -> e.setData_value18(value);
	        case 19 -> e.setData_value19(value);
	        case 20 -> e.setData_value20(value);
	        case 21 -> e.setData_value21(value);
	        case 22 -> e.setData_value22(value);
	        case 23 -> e.setData_value23(value);
	        case 24 -> e.setData_value24(value);
	        case 25 -> e.setData_value25(value);
	        case 26 -> e.setData_value26(value);
	        case 27 -> e.setData_value27(value);
	        case 28 -> e.setData_value28(value);
	        case 29 -> e.setData_value29(value);
	        case 30 -> e.setData_value30(value);
	        default -> System.out.println("Warning: Invalid field_postion " + position);
	    }
	}
	
	private boolean DocExpires(EdataAppManagement rows,int Process_id,int documentId,	int documentrv){
		
		int eformId = rows.getData_id();
		process currentProcess = EdataDAO.getProcess(Process_id);
		Idoc document = IdocDAO.getDocument(eformId,documentId,documentrv); 
      String docDateStr = document.getDate(); // เช่น "2026-03-27 12:49" หรือ "2026-03-27"

                   if(document.getExpired() == 1) {
                	   return true;
                   }

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
			
			            if(daysPassed > currentProcess.getExpires_in_days()) {
			            	 Workflow_Components.setDocExpires(rows.getData_id(),currentProcess.getClosing_status_expired(),currentProcess.getSub_process_expired());
			                 return false;
			            }
			        
			    }
			      return true;
	}
	
	
}
