package com.smepro.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.smepro.entity.assets;
import com.smepro.entity.company_annual_plan;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
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
import com.smepro.models.Segmentation;
import com.smepro.models.SetSegmentView;
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
import com.smepro.models.file;
import com.smepro.models.keymap;
import com.smepro.models.process;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.repository.AssetsRepository;
import com.smepro.repository.CampaignPromotionRepository;
import com.smepro.repository.Company_annual_planRepository;
import com.smepro.repository.Edata_annual_planRepository;
import com.smepro.repository.Edata_assetsRepository;
import com.smepro.repository.Edata_workruleRepository;
import com.smepro.repository.ProductRepository;
import com.smepro.repository.RegisterRepository;
import com.smepro.repository.StockRepository;
import com.smepro.repository.WorkruleRepository;
import com.smepro.service.AppComponent;
import com.smepro.service.FlowServices;
import com.smepro.service.userservice;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.entity.CampaignPromotion;
import com.smepro.entity.CustomizeTableHeaders;
import com.smepro.models.DocumentEffect;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_sk;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smepro.dto.SourceDTO;
import  com.smepro.entity.Register_list;
import  com.smepro.entity.Register_stock;
import  com.smepro.entity.Register_product;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Register_Component {

	
	
	
	private edataDAO EdataDAO;
	private reportDAO ReportDAO;
	
	
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
	private MessageSource messageSource;
	
	private AssetsRepository AssetsRepository;
	private Company_annual_planRepository Annual_planRepository;
	private WorkruleRepository WorkruleRepository;
	

	private StockRepository StockRepository;
	private RegisterRepository RegisterRepository;
	private ProductRepository ProductRepository;
	private AppComponent appComponent;
	private Production Productions;
	private eformfieldDAO EformfieldDAO;
	private fileDAO FileDAO;
	private CampaignPromotionRepository campaignPromotionRepository;
	
	
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
	    		MessageSource messageSource,
	    		reportDAO ReportDAO,
	    		fileDAO FileDAO,
	    		 eformfieldDAO EformfieldDAO, 
	    		CampaignPromotionRepository campaignPromotionRepository
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
	        this.messageSource = messageSource;
	        this.ReportDAO = ReportDAO;
	        this.EformfieldDAO = EformfieldDAO;
	        this.FileDAO = FileDAO;
	        this.campaignPromotionRepository = campaignPromotionRepository;
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
	List<registerkey> heads = new ArrayList<registerkey>();
	List<registerkey> heads2 = new ArrayList<registerkey>();
	
	
	
	
	public Register_Component initiate(LoadApp loadApp, Map<String, Object> modelDatas) {
		this.modelDatas = modelDatas;
		this.modelData.clear();
		this.LoadApp = loadApp;
		this.step = 0;
		heads.clear();
		heads2.clear();
		return this;

	}

	public void deleteSegmentation() {
		removeSegmentationByid();
		
	}
	
	public LoadApp saveCl() {
				if(LoadApp.getPlatform().getSegmentation() == null ) { return LoadApp;}
				
				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			   	LocalDateTime today = LocalDateTime.now();//For reference
		    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	String dates = today.format(formatter);
				 
		    	
		    	System.out.println(" Segmentation "+LoadApp.getPlatform().getSegmentation().getId());
				 
			 if( LoadApp.getPlatform().getSegmentation().getId()  > 0 ) {
				 
				 LoadApp.getPlatform().getSegmentation().setId_edit(usersed.getUser_id());
				 LoadApp.getPlatform().getSegmentation().setDate_edit(dates);
				 
			 }else {
			
				 LoadApp.getPlatform().getSegmentation().setId_add(usersed.getUser_id());
				 LoadApp.getPlatform().getSegmentation().setDate_add(dates);
			 }
		
			   
			int saveId = EdataDAO.SaveSegmentation(LoadApp.getPlatform().getSegmentation());
			LoadApp.setSaveId(saveId);
			return LoadApp;
		
	}
	

	public  Register_Component renderIndexLayout(){
		drawTable();
		drawSearchTool();
		return this;
	}
	
	
	public Register_Component fileAll() {
		if(this.LoadApp.getPlatform().getApp_item_id() ==  0 ) { return this; }
		
           List<file> files = FileDAO.getallFileIndexapp(this.LoadApp.getPlatform().getApp_item_id());
           this.modelData.put("files",files);
   	    	return this;
	}
	
	public  Register_Component requireAllIndex(){
		

		
		exampleData();
		return this;
	}
	public  Register_Component requireAuditIndex(){
		auditData();
		return this;
	}
	public Register_Component requireHelpDeskprofile() {
		HelpDeskData();
		return this;
	}

	public Register_Component shortCutCL() {
		
		loadshortCutCL();
		return this;
	}


	public  Register_Component requireGroupingIndex(){
		
		dataset  dataset3 = DatasetDAO.getMain("Register_status");
		List<dataset_data> Register_status = Dataset_dataDAO.getall(dataset3.getDataset_id());
		this.modelData.put("Register_status",Register_status);
		
		groupingData();
		return this;
	}
	
	public  Register_Component requireExtension(){
		loadExtension();
		return this;
	}


	public  Register_Component requireIndex(){
		loadItem();
		loadExtension();

		return this;
	}
	

	public  Register_Component requireAllSegmentation(){
		getAllSegmentationByProcessid();
		return this;
	}
	
	public  Register_Component requireSegmentation(){
		getSegmentationByid();
		return this;
	}
	

	public Register_Component FullSync() {
		
		if(this.LoadApp.getProcess().getRegister().equals("Profile") || this.LoadApp.getProcess().getRegister().equals("Product")) {
			loadItem();
			if(this.LoadApp.getPlatform().getEdata_from() != null) {
				int edata_from_id = Integer.valueOf(this.LoadApp.getPlatform().getEdata_from());
				edata edata_from = EdataDAO.getbyid(edata_from_id);
				this.modelData.put("edata_from", edata_from);

			}
		}
		return this;

	}

	
	
	public Register_Component ShowTableHeaderDetails() {
		if(this.heads2.size() < 1) {return this; }
		

		this.heads2.stream().forEach(row->{
			if(row.getType().equals("NUMBER")) {
				row.setType("number");	
			}
			else if(row.getType().equals("Enum")) {
				
				dataset  dataset = DatasetDAO.getMain(row.getSource());
				List<dataset_data> dataset_data  = Dataset_dataDAO.getall(dataset.getDataset_id());
				
				if(dataset_data.size() > 10) {
					String dataset_dataStr = dataset_data.stream().map(rows-> rows.getDataset_data_title()).limit(25).collect(Collectors.joining(","));
		    		row.setAction_type(dataset_dataStr);
		    		row.setType("select");
				}else {
					String dataset_dataStr = dataset_data.stream().map(rows-> rows.getDataset_data_title()).collect(Collectors.joining(","));
		    		row.setAction_type(dataset_dataStr);
		    		row.setType("select_multiple");
				}
				
	 
			}
			else {
				row.setType("text");	
			}
			
		});
		return this;
	}



	public registers getProfileByCode(String code) {
		registers registers =Productions.getProfileByCode(code);
		return registers;
	}

	public Register_Component MassOperationsImport() {
		// TODO Auto-generated method stub
		doMassOperationsImport();
		return this;
	}
	public Register_Component MassOperationsFilesImport() {
		// TODO Auto-generated method stub
		FilesImport();
		return this;
	}
	
	public Register_Component MassOperationsExtensionImport() {
		// TODO Auto-generated method stub
		ExtensionImport();
		return this;
	}
	
	
	
	private void FilesImport() { 
	    MultipartFile file = this.LoadApp.getPlatform().getFile();

	    if (file == null || file.isEmpty()) {
	        this.modelData.put("Callback", "File is empty");
	        return;
	    }

	    // ==========================================
	    // 1. 🗺️ เตรียมแผนที่: โหลด Register ID (ID ใหม่)
	    // ==========================================
	    List<registers> allCustomers = EdataDAO.getAllOldSys(); 
	    Map<String, Integer> customerIdMap = new HashMap<>(); 
	    
	    for (registers reg : allCustomers) {
	        if (reg.getId_oldsys() != null) {
	            customerIdMap.put(reg.getId_oldsys().trim(), reg.getRegister_id());
	        }
	    }

	    List<file> fileList = new ArrayList<>(); 

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
	        
	        // 🌟 อ่าน Header บรรทัดแรก เพื่อสร้างแผนที่ตำแหน่งคอลัมน์
	        String headerLine = br.readLine();
	        if (headerLine == null) return;
	        
	        // ล้าง BOM และ Quote ออก
	        headerLine = headerLine.replace("\uFEFF", "").replace("\"", "");
	        String[] headers = headerLine.split(",");
	        
	        // 🗺️ Map ชื่อคอลัมน์ -> เลข Index (เช่น "file_name" -> 2)
	        Map<String, Integer> headerIndexMap = new HashMap<>();
	        for (int i = 0; i < headers.length; i++) {
	            headerIndexMap.put(headers[i].trim(), i);
	        }
	        
	        String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
	        String dataLine;

	        // ==========================================
	        // 2. ⚔️ Loop CSV: ดึงค่าตามชื่อคอลัมน์
	        // ==========================================
	        while ((dataLine = br.readLine()) != null) {
	            if (dataLine.trim().isEmpty()) continue;
	            
	            String[] val = dataLine.split(splitRegex, -1);
	            
	            // 🛠️ Helper: ฟังก์ชันดึงค่าแบบปลอดภัย (ไม่ต้องกลัว IndexOutBound)
	            // เราจะดึงโดยใช้ "ชื่อคอลัมน์" แทนตัวเลข
	            String oldAppId = getVal(val, headerIndexMap, "file_indexapp");
	            
	            // 🌟 เช็คแม่
	            if (customerIdMap.containsKey(oldAppId)) {
	                
	                file f = new file();
	                
	                // 1. Swap ID
	                int newRegisterId = customerIdMap.get(oldAppId);
	                f.setFile_indexapp(newRegisterId); 

	                // 2. ดึงค่าตามชื่อ Header (สลับที่ก็ไม่กลัว)
	                f.setFile_tag(getVal(val, headerIndexMap, "file_tag"));
	                f.setFile_name(getVal(val, headerIndexMap, "file_name"));
	                f.setFile_name_original(getVal(val, headerIndexMap, "file_name_original"));
	                f.setFile_type(getVal(val, headerIndexMap, "file_type"));
	                f.setFile_path_external(getVal(val, headerIndexMap, "file_path_external"));
	                
	                
	                // 3. จัดการ file_size (String -> Long)
	                String sizeStr = getVal(val, headerIndexMap, "file_size");
	                long size = 0;
	                try {
	                    // กันเหนียว: ลบ comma, trim, ถ้าว่างให้เป็น 0
	                    if (!sizeStr.isEmpty()) {
	                        size = Long.parseLong(sizeStr.replace(",", "").trim());
	                    }
	                } catch (NumberFormatException e) {
	                    size = 0; // ถ้าแปลงไม่ได้ ให้เป็น 0
	                }
	                f.setFile_size(size); // ⚠️ Model พี่ต้องรับ Long นะครับ

	                // 4. Hardcode Path
	                f.setFile_path("external");
	                
	                fileList.add(f);
	            }
	        }

	        // ==========================================
	        // 3. 🚀 Save ลง DB
	        // ==========================================
	        if (!fileList.isEmpty()) {
	            FileDAO.saveAllFilesBatch(fileList); 
	        }

	        this.modelData.put("Callback", "Success: Imported " + fileList.size() + " files.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        this.modelData.put("Callback", "Error: " + e.getMessage());
	    }
	}

	// 🛠️ Helper Method: เอาไว้ดึงค่าจาก Array โดยใช้ชื่อคอลัมน์ (วางไว้นอก method FilesImport)
	private String getVal(String[] row, Map<String, Integer> map, String colName) {
	    if (map.containsKey(colName)) {
	        int index = map.get(colName);
	        if (index < row.length) {
	            return row[index].replace("\"", "").trim();
	        }
	    }
	    return ""; // ถ้าหาไม่เจอ หรือ Index เกิน ให้คืนค่าว่าง
	}
	private void doMassOperationsImport() {
		
		if(this.LoadApp.getPlatform().getFile() == null) {return;}
		
		switch  (this.LoadApp.getPlatform().getApp_entity_type()) {
			case "Profile" ->ProfileImport();
			case "Product" ->ProductImport();
		}

		
	}

	
	
private void ExtensionImport() {
		
		MultipartFile	file = 	this.LoadApp.getPlatform().getFile();

        if (file.isEmpty()) {
            this.modelData.put("Callback","File is empty");
            return;
        }

        List<edata> edataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            
            // 1. อ่านบรรทัดแรก (หัวคอลัมน์ของ CSV)
            String headerLine = br.readLine();
            if (headerLine == null) {
                this.modelData.put("Callback","CSV is empty");
                return;
            }

            // 🌟 เพิ่มโค้ดบรรทัดนี้: ลบอักขระล่องหน BOM ทิ้งไปให้หมด
            headerLine = headerLine.replace("\uFEFF", "");
            
            // 🌟 แก้ไขที่ 1: ลบเครื่องหมาย " ออกจากบรรทัดหัวคอลัมน์ให้หมดก่อนค่อย split
            headerLine = headerLine.replace("\"", "");
            
            
            String[] csvHeaders = headerLine.split(","); 
            for (int i = 0; i < csvHeaders.length; i++) {
                csvHeaders[i] = csvHeaders[i].trim(); // ล้างช่องว่าง
            }

         // 2. กำหนดหัวคอลัมน์มาตรฐานแบบตายตัว (ชัวร์สุด ไม่รวนแน่นอน)
            String headStr = "id_refer,reference,reference_oldsys,data_dateadd,data_nameadds,data_value1,data_value2,data_value3,data_value4,data_value5"
                           + ",data_value6,data_value7,data_value8,data_value9,data_value10"
                           + ",data_value11,data_value12,data_value13,data_value14,data_value15,data_value16,data_value17,data_value18,data_value19,data_value20";
            
            // แตก String เป็น Array แล้วแปลงเป็น List<String> ทันที
            List<String> modelCols = Arrays.asList(headStr.split(","));

            // 3. สร้าง Mapping (เช็คว่าหัวคอลัมน์ตรงกันไหม)
            Map<Integer, String> columnIndexToFieldName = new HashMap<>();
            for (int i = 0; i < csvHeaders.length; i++) {
                if (modelCols.contains(csvHeaders[i])) {
                    columnIndexToFieldName.put(i, csvHeaders[i]);
                }
            }

            // แจ้งเตือนดูว่า Mapping ได้กี่คอลัมน์ (ถ้าเป็น 0 แปลว่าชื่อหัวไม่ตรงกันเลย)
            System.out.println("Mapped Columns count: " + columnIndexToFieldName.size());

            // 🌟 Regex พิเศษ: ให้แยกด้วย (,) แต่ต้องเป็น (,) ที่ไม่ได้อยู่ภายใต้เครื่องหมาย (" ")
            String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

            // 4. อ่านข้อมูลทีละบรรทัด แล้วจับยัดใส่ Model
            String dataLine;
            while ((dataLine = br.readLine()) != null) {
                
                // ข้ามบรรทัดว่าง
                if (dataLine.trim().isEmpty()) continue;

                // 🌟 แก้ไขที่ 2: ใช้ Regex พิเศษในการหั่นข้อมูล
                String[] dataValues = dataLine.split(splitRegex, -1); 
                
                edata edatas = new edata();
                
                // วนลูปเฉพาะคอลัมน์ที่ Mapping ไว้แล้ว
                for (Map.Entry<Integer, String> entry : columnIndexToFieldName.entrySet()) {
                    int colIndex = entry.getKey();
                    String fieldName = entry.getValue();
                    
                    if (colIndex < dataValues.length) {
                        // 🌟 แก้ไขที่ 3: ลบเครื่องหมาย " ออกจากข้อมูลจริงก่อนยัดลง Model
                        String valueToInsert = dataValues[colIndex].replace("\"", "").trim();
                        
                        injectValueToModel(edatas, fieldName, valueToInsert);
                    }
                }
                edataList.add(edatas);
            }
            
            System.out.println("========== DEBUG: เช็คข้อมูลก่อน SAVE (20 รายการแรก) ==========");
            edataList.stream().limit(20).forEach(row -> {
                System.out.println("----------------------------------------");
                System.out.println("Data_value1         : [" + row.getData_value1()+ "]");
                System.out.println("Data_value2         : [" + row.getData_value2() + "]");
                System.out.println("Id_refer    : [" + row.getId_refer() + "]");

            });
            System.out.println("===============================================================");
            
    
            
  
List<registers> registerList = EdataDAO.getAllOldSys();
            
            // ใช้ Map<Integer, Integer> เพื่อความเร็วสูงสุดในการค้นหาหลักแสนแถว
            Map<Integer, Integer> oldToNewIdMap = new HashMap<>();
            
	            for (registers reg : registerList) {
	                // เช็คก่อนว่า id_oldsys ไม่ใช่ null หรือค่าว่าง
	                if (reg.getId_oldsys() != null && !reg.getId_oldsys().trim().isEmpty()) {
	                    try {
	                        // 🌟 แปลง id_oldsys (String) ให้กลายเป็นตัวเลข (int)
	                        int oldSysId = Integer.parseInt(reg.getId_oldsys().trim()); 
	                        
	                        if (oldSysId > 0 && reg.getId() > 0) {
	                            oldToNewIdMap.put(oldSysId, reg.getId());
	                        }
	                    } catch (NumberFormatException ex) {
	                        // 🛡️ ดักจับ Error: กรณี id_oldsys เป็นตัวอักษร เช่น "A001" หรือ "-"
	                        // โปรแกรมจะข้ามแถวนี้ไปเลยโดยไม่ทำให้ระบบตายครับ
	                    }
	                }
	            }
	
	            // 3. วนลูป edataList เพื่อเปลี่ยน id_refer เดิม ให้กลายเป็น id ใหม่
	            for (edata e : edataList) {
	                int oldIdRefer = e.getId_refer(); // ดึงค่าแบบ int
	                
	                // ค้นหาด้วย int ตรงๆ ถ้าเจอก็สวมร่าง id ใหม่เลย!
	                if (oldIdRefer > 0 && oldToNewIdMap.containsKey(oldIdRefer)) {
	                    e.setId_refer(oldToNewIdMap.get(oldIdRefer)); 
	                } else {
	                    // (Optional) ถ้าหาไม่เจอจริงๆ อยากให้เซ็ตเป็น 0 ไว้ ก็ทำได้ครับ
	                    // e.setId_refer(0);
	                }
	            }
            
            
            // 4. เมื่อเปลี่ยน ID เสร็จหมดแล้ว ค่อยส่งไป Save แบบ Batch
            int extension_id = LoadApp.getPlatform().getApp_item_id();
            int processes_id = LoadApp.getPlatform().getApp_processes_id();

            EdataDAO.SaveAllExtension(edataList, extension_id, processes_id);
            
            this.modelData.put("Callback","Success: Processed " + edataList.size() + " rows and mapped to List< edata>.");

            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            this.modelData.put("Callback","Error: " + e.getMessage());
            return;
        }
	}
	
	
	
	
	private void ProfileImport() {
		
		MultipartFile	file = 	this.LoadApp.getPlatform().getFile();

        if (file.isEmpty()) {
            this.modelData.put("Callback","File is empty");
            return;
        }

        List<edata_register> registerList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            
            // 1. อ่านบรรทัดแรก (หัวคอลัมน์ของ CSV)
            String headerLine = br.readLine();
            if (headerLine == null) {
                this.modelData.put("Callback","CSV is empty");
                return;
            }

            // 🌟 เพิ่มโค้ดบรรทัดนี้: ลบอักขระล่องหน BOM ทิ้งไปให้หมด
            headerLine = headerLine.replace("\uFEFF", "");
            
            // 🌟 แก้ไขที่ 1: ลบเครื่องหมาย " ออกจากบรรทัดหัวคอลัมน์ให้หมดก่อนค่อย split
            headerLine = headerLine.replace("\"", "");
            
            
            String[] csvHeaders = headerLine.split(","); 
            for (int i = 0; i < csvHeaders.length; i++) {
                csvHeaders[i] = csvHeaders[i].trim(); // ล้างช่องว่าง
            }

            // 2. ดึงหัวคอลัมน์ที่เป็นมาตรฐานจาก Model ของเรา
            edata_register dummyModel = new edata_register();
            List<String> modelCols = dummyModel.head(); 

            // 3. สร้าง Mapping (เช็คว่าหัวคอลัมน์ตรงกันไหม)
            Map<Integer, String> columnIndexToFieldName = new HashMap<>();
            for (int i = 0; i < csvHeaders.length; i++) {
                if (modelCols.contains(csvHeaders[i])) {
                    columnIndexToFieldName.put(i, csvHeaders[i]);
                }
            }

            // แจ้งเตือนดูว่า Mapping ได้กี่คอลัมน์ (ถ้าเป็น 0 แปลว่าชื่อหัวไม่ตรงกันเลย)
            System.out.println("Mapped Columns count: " + columnIndexToFieldName.size());

            // 🌟 Regex พิเศษ: ให้แยกด้วย (,) แต่ต้องเป็น (,) ที่ไม่ได้อยู่ภายใต้เครื่องหมาย (" ")
            String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

            // 4. อ่านข้อมูลทีละบรรทัด แล้วจับยัดใส่ Model
            String dataLine;
            while ((dataLine = br.readLine()) != null) {
                
                // ข้ามบรรทัดว่าง
                if (dataLine.trim().isEmpty()) continue;

                // 🌟 แก้ไขที่ 2: ใช้ Regex พิเศษในการหั่นข้อมูล
                String[] dataValues = dataLine.split(splitRegex, -1); 
                
                edata_register registerEntry = new edata_register();
                
                // วนลูปเฉพาะคอลัมน์ที่ Mapping ไว้แล้ว
                for (Map.Entry<Integer, String> entry : columnIndexToFieldName.entrySet()) {
                    int colIndex = entry.getKey();
                    String fieldName = entry.getValue();
                    
                    if (colIndex < dataValues.length) {
                        // 🌟 แก้ไขที่ 3: ลบเครื่องหมาย " ออกจากข้อมูลจริงก่อนยัดลง Model
                        String valueToInsert = dataValues[colIndex].replace("\"", "").trim();
                        
                        injectValueToModel(registerEntry, fieldName, valueToInsert);
                    }
                }
                registerList.add(registerEntry);
            }

            System.out.println("========== DEBUG: เช็คข้อมูลก่อน SAVE (20 รายการแรก) ==========");
            registerList.stream().limit(20).forEach(row -> {
                System.out.println("----------------------------------------");
                System.out.println("Name         : [" + row.getRegister_name() + "]");
                System.out.println("Type         : [" + row.getRegister_type() + "]");
                System.out.println("Party Type   : [" + row.getParty_type() + "]");
                System.out.println("Id_old Sys  : [" + row.getId_oldsys()+ "]");
                System.out.println("Phone        : [" + row.getPhone_number() + "]");
                System.out.println("Tax ID       : [" + row.getTax_id() + "]");
                System.out.println("Address      : [" + row.getRegister_address() + "]"); // ลองปริ้นท์ที่อยู่ดูว่าคอมม่าไม่ทำข้อมูลพัง
            });
            System.out.println("===============================================================");
            
    
            
            Productions.SaveAllMainProfile(registerList,"REG");
            this.modelData.put("Callback","Success: Processed " + registerList.size() + " rows and mapped to List<edata_register>.");

            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            this.modelData.put("Callback","Error: " + e.getMessage());
            return;
        }
	}
	
	
	
	private void ProductImport() {
	    MultipartFile file = this.LoadApp.getPlatform().getFile();

	    if (file == null || file.isEmpty()) {
	        this.modelData.put("Callback", "File is empty");
	        return;
	    }

	    List<edata_product> productList = new ArrayList<>();

	    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
	        
	        // 1. อ่านบรรทัดแรก (หัวคอลัมน์ของ CSV)
	        String headerLine = br.readLine();
	        if (headerLine == null) {
	            this.modelData.put("Callback", "CSV is empty");
	            return;
	        }

	        // 🌟 ลบอักขระล่องหน BOM และเครื่องหมาย " ออกจากบรรทัดหัวคอลัมน์
	        headerLine = headerLine.replace("\uFEFF", "").replace("\"", "");
	        
	        String[] csvHeaders = headerLine.split(","); 
	        for (int i = 0; i < csvHeaders.length; i++) {
	            csvHeaders[i] = csvHeaders[i].trim(); // ล้างช่องว่าง
	        }

	        // 2. ดึงหัวคอลัมน์ที่เป็นมาตรฐานจาก Model ของ edata_product
	        edata_product dummyModel = new edata_product();
	        List<String> modelCols = dummyModel.head(); // ตรวจสอบให้ชัวร์ว่า Model นี้มี Method head()

	        // 3. สร้าง Mapping (เช็คว่าหัวคอลัมน์ตรงกันไหม)
	        Map<Integer, String> columnIndexToFieldName = new HashMap<>();
	        for (int i = 0; i < csvHeaders.length; i++) {
	            if (modelCols.contains(csvHeaders[i])) {
	                columnIndexToFieldName.put(i, csvHeaders[i]);
	            }
	        }

	        System.out.println("Mapped Columns count: " + columnIndexToFieldName.size());

	        // 🌟 Regex พิเศษ: แบ่ง (,) ยกเว้น (,) ที่อยู่ใน (" ")
	        String splitRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

	        // 4. อ่านข้อมูลทีละบรรทัด แล้วจับยัดใส่ Model edata_product
	        String dataLine;
	        while ((dataLine = br.readLine()) != null) {
	            
	            if (dataLine.trim().isEmpty()) continue;

	            String[] dataValues = dataLine.split(splitRegex, -1); 
	            
	            edata_product productEntry = new edata_product(); // เปลี่ยนเป็น edata_product
	            
	            for (Map.Entry<Integer, String> entry : columnIndexToFieldName.entrySet()) {
	                int colIndex = entry.getKey();
	                String fieldName = entry.getValue();
	                
	                if (colIndex < dataValues.length) {
	                    // ลบเครื่องหมาย " ออกจากข้อมูลจริงก่อนยัดลง Model
	                    String valueToInsert = dataValues[colIndex].replace("\"", "").trim();
	                    
	                    // 💉 ตรวจสอบ Method นี้ด้วยว่ารองรับ edata_product แล้วหรือยัง
	                    injectValueToModel(productEntry, fieldName, valueToInsert);
	                }
	            }
	            productList.add(productEntry);
	        }

	        System.out.println("========== DEBUG: เช็คข้อมูล Product ก่อน SAVE (20 รายการแรก) ==========");
	        productList.stream().limit(20).forEach(row -> {
	            System.out.println("----------------------------------------");
	            // ⚠️ แก้ไข Getter ด้านล่างนี้ให้ตรงกับตัวแปรจริงใน edata_product ของพี่นะครับ
	            // ผมสมมติชื่อ Getter เบื้องต้นให้ก่อน
	            System.out.println("Product Name : [" + row.getProduct_names() + "]");
	            System.out.println("Product Code : [" + row.getProductCode() + "]");
	            System.out.println("Category     : [" + row.getProduct_category() + "]");
	            System.out.println("Id_old Sys  : [" + row.getId_oldsys()+ "]");
	        });
	        System.out.println("===============================================================");
	        
	        
	        
	
	        
	        // 5. บันทึกลง Database (เปลี่ยน Method ใน Productions ให้รับ List<edata_product> ด้วยนะครับ)
	        Productions.SaveAllMainProduct(productList, "PROD"); // เปลี่ยน Type เป็น PROD หรืออะไรตามที่ระบบกำหนด
	        
	        this.modelData.put("Callback", "Success: Processed " + productList.size() + " rows and mapped to List<edata_product>.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        this.modelData.put("Callback", "Error: " + e.getMessage());
	    }
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
	
    public void callback() {
		modelDatas.putAll(modelData);
	}

	public void load() {
		modelDatas.putAll(modelData);
	}


	private void loadExtension() {
		if(this.LoadApp.getProcess() == null) { return; }
		List<UseExtension>  useextension =  EdataDAO.getallUseExtension(this.LoadApp.getProcess().getId());
		this.modelData.put("useextension", useextension);
	}


	private void loadItem() {
		// TODO Auto-generated method stub

		if(this.LoadApp.getProcess().getRegister() == null) { return ; }
		if(this.LoadApp.getPlatform().getApp_item_id() ==  0 ) { return; }

		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" ->  Productions.getRegister(this.LoadApp,this.modelData);
			case "Product" ->  Productions.getProduct(this.LoadApp,this.modelData);
		}	
		
	
	}


	
	
	
	


	private void exampleData() {
		// TODO Auto-generated method stub
		if(this.LoadApp.getProcess().getRegister() == null) { return; }
		
		System.out.println(" exampleData ");
		
		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" -> Productions.getAllRegister(this.LoadApp,this.modelData);
			case "Product" -> Productions.getAllProduct(this.LoadApp,this.modelData);
		}	
		
	}
	private void auditData() {
		// TODO Auto-generated method stub
		if(this.LoadApp.getProcess().getRegister() == null) { return; }
		
		System.out.println(" exampleData ");
		
		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" -> Productions.getAuditRegister(this.LoadApp,this.modelData);
			case "Product" -> Productions.getAuditProduct(this.LoadApp,this.modelData);
		}	
		
	}
	
	private void HelpDeskData() {
		// TODO Auto-generated method stub
		if(this.LoadApp.getProcess().getRegister() == null) { return; }
		
		System.out.println(" exampleData ");
		
		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" -> Productions.getHelpDeskData(this.LoadApp,this.modelData);
		}	
		
	}
	
	
	
	
	
	
	private void groupingData() {
		// TODO Auto-generated method stub
		if(this.LoadApp.getProcess().getRegister() == null) { return; }
		
		System.out.println(" exampleData ");
		
		switch (this.LoadApp.getProcess().getRegister()) {
			case "Profile" -> Productions.getGroupingRegister(this.LoadApp,this.modelData);
			case "Product" -> Productions.getGroupingProduct(this.LoadApp,this.modelData);
		}	
		
	}
	


	private void getAllSegmentationByProcessid() {
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		
		SetSegmentView view = EdataDAO.getSetSegmentView(LoadApp.getProcess().getId(),usersed);
	
	

		LoadApp.getProcess().setSetSegmentView(view);
		List<Segmentation> ListSegmentation = EdataDAO.getAllSegmentation(LoadApp.getProcess(),usersed);
		
		
		this.modelData.put("ListSegmentation", ListSegmentation);
		
	}

	private void getSegmentationByid() {
		

		Segmentation segmentation = EdataDAO.getSegmentationById(LoadApp.getPlatform().getApp_item_id());
		
        if(segmentation != null) {
        	user Useradd = UserDAO.getbyId(segmentation.getId_add(), 0);
        	 if(Useradd != null) { segmentation.setUser_add(Useradd.getUser_name());}
        	user Useredit = UserDAO.getbyId(segmentation.getId_add(), 0);
           	 if(Useredit != null) { segmentation.setUser_edit(Useredit.getUser_name());}
           	 
           	 
      	   List<CampaignPromotion>Promotion =  campaignPromotionRepository.findByTargetAudienceAndStatus("CUSTOM_CONDITIONS","ACTIVE");
		   this.modelData.put("Promotion", Promotion);
		   
		   if(segmentation.getPromotion() > 0) {
			   Optional<CampaignPromotion> userPromotion =  campaignPromotionRepository.findById((long)segmentation.getPromotion() );
				   if(userPromotion.isPresent()) {
				   this.modelData.put("userPromotion", userPromotion.get());
				   }
		   }
        }
		
    	this.modelData.put("user", segmentation);
		this.modelData.put("segmentation", segmentation);
		
	
		   
	}


	private void removeSegmentationByid() {
		EdataDAO.RemoveSegmentationById(LoadApp.getPlatform().getApp_item_id());
		
	}

	
	private void drawTable() {

	
		if(this.LoadApp.getProcess()== null) { return; }
		if(this.LoadApp.getProcess().getListview() == null) { return; }

		
		System.out.println(" getListview-> "+this.LoadApp.getProcess().getId());
		
		heads = EdataDAO.getRegisterkeyByListview(LoadApp.getProcess().getListview());
		this.modelData.put("heads",heads);
		
		 heads2 = EdataDAO.getRegisterkeyByListview(LoadApp.getProcess().getListview2());
		this.modelData.put("Fullhead",heads2);
		
		System.out.println(" heads-> "+heads.size());
		
		//registerkey
	 	 
	}

	private void drawSearchTool() {
		this.modelData.put("test",111);
		
	}

	
	
	private void loadshortCutCL() {
		// หัวตารางเพื่อกรองเเละจัดการดู
		// โหลดรายชื่อ
		
		List<CustomizeTableHeaders>  Heads= new ArrayList<CustomizeTableHeaders>();

		
		setupHeaders(Heads,"register_name","register.name");
		setupHeaders(Heads,"register_type","register.type");
		setupHeaders(Heads,"register_groups","register.groups");
		setupHeaders(Heads,"register_address","register.address");
		setupHeaders(Heads,"register_note","register.note");
		setupHeaders(Heads,"contact_person","register.contact_person");
		setupHeaders(Heads,"phone_number","register.phone_number");
		setupHeaders(Heads,"mobile_number","register.mobile_number");
		setupHeaders(Heads,"website","register.website");
		setupHeaders(Heads,"social","register.social");
		setupHeaders(Heads,"tax_id","register.tax_id");
		setupHeaders(Heads,"branch_code","register.branch_code");
		setupHeaders(Heads,"business_type","register.business_type");
		setupHeaders(Heads,"payment_terms","register.payment_terms");
		setupHeaders(Heads,"credit_limit","register.credit_limit");
		setupHeaders(Heads,"bank_account","register.bank_account");
		setupHeaders(Heads,"preferred_currency","register.preferred_currency");

		List<CustomizeTableHeaders>  clHead = WorkflowDAO.getCustomizeTableHeadersBytype("register");
		Segmentation segmentation = EdataDAO.getSegmentationById(LoadApp.getPlatform().getApp_item_id());
		
		Heads.addAll(clHead);
		filterCustomizeTableHeaders(Heads);
		
		this.modelData.put("clHead",Heads);
		
		Productions.getAllRegisterSegmentation(this.LoadApp,this.modelData,segmentation);
		
	}


	private void filterCustomizeTableHeaders(List<CustomizeTableHeaders> heads) {
	    // 1. Safety check
	    if (LoadApp.getPlatform().getTab_id() == 0) { return; }
	    
	    TabManagement tab = ReportDAO.getTabManagement(LoadApp.getPlatform().getTab_id());
		this.modelData.put("thisTab",tab);

	    
	    // 2. Handle potential nulls from the DAO or the column string
	    if (tab == null || tab.getShortcut_segment_column() == null) { return; }

	    // 3. Convert array to a Set for O(1) lookup and use .contains()
	    Set<String> allowedColumns = new HashSet<>(Arrays.asList(tab.getShortcut_segment_column().split(",")));

	    // 4. Filter the list
	    heads.removeIf(header -> !allowedColumns.contains(header.getColumnName()));
	}
	
	
	public void setupHeaders(List<CustomizeTableHeaders>  Heads,String Column,String translated) {
		
		Locale locale = LocaleContextHolder.getLocale();
		CustomizeTableHeaders  CustomizeHeaders = new CustomizeTableHeaders();
	    // ดึงคำแปลตาม Locale ปัจจุบัน
	    String translatedName = messageSource.getMessage(translated, null, locale);
	    
	    CustomizeHeaders.setColumnName(Column);
	    CustomizeHeaders.setCustomizeName(translatedName);
	    Heads.add(CustomizeHeaders);
	}







}
