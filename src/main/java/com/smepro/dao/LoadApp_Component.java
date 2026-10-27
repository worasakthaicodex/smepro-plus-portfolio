package com.smepro.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.google.common.util.concurrent.AtomicDouble;
import com.smepro.entity.sales_followup;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Followup;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Edata_maindata;
import com.smepro.models.FieldChange;
import com.smepro.models.Goal;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Idoc;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.Links;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.SubData;
import com.smepro.models.bom;
import com.smepro.models.dataset;
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
import com.smepro.models.product_master;
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
import com.smepro.repository.Sales_followupRepository;
import com.smepro.service.FlowServices;
import com.smepro.service.PlatformService;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;



@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class LoadApp_Component {

	
	private  Map<String, Object> modelData;
	
	
	private edataDAO EdataDAO;
	private eformfieldDAO EformfieldDAO;
    private  App_Component App_Components;
    private  LoadAppUnity_Component LoadAppUnity_Components;
	
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
	private Sales_followupRepository Sales_followupRepository;
	
	
	
	
	// สร้างตัวแปร Global ไว้เก็บ Cache (อยู่นอก Method หรือสร้าง Class แยกก็ได้)
	// Key = "ProcessID_Version" (เช่น "101_v1.5")
	// Value = ข้อมูล modelData ก้อนยักษ์
	private static final Map<String, Map<String, Object>> APP_CONFIG_CACHE = new ConcurrentHashMap<>();
	
	private int child = 0;
	
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
	    		LoadAppUnity_Component LoadAppUnity_Components,
	    		App_Component App_Components,
	    		Sales_followupRepository Sales_followupRepository
	    		) { 
		  
	        this.Sales_followupRepository = Sales_followupRepository;
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
	        this.LoadAppUnity_Components = LoadAppUnity_Components;
	        this.App_Components = App_Components;
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
	private LoadApp LoadApp = new LoadApp();
	
	
	
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
	  *
	  * @return the current {@code PlatformService} instance after initialization
	  */
		public  LoadApp_Component initiate(LoadApp loadApp,Map<String, Object> modelData){
			  this.LoadApp = loadApp;
			   this.modelData = modelData;
			   this.child = 0;
			   
			 return this;
		 }
		
		public  <T> LoadApp_Component child(Object object ){
			
			 if(object == null)  { object = new ArrayList<>(); }
		
			 this.child = 1;
			 this.LoadApp.setActionbatch(object);
			 
		 return this;
		 }
		
		 public  LoadApp_Component childForm(){
			 this.child = 2;
			 return this;
		 }

		 public  LoadApp_Component tabForm(){
			 this.child = 8;
			 drawTabForm();
			 return this;
		 }
	

		 
		 
		public  LoadApp_Component hasChanges(Object object){
			 this.child = 3;
			 this.LoadApp.setActionbatch(object);
			 return this;
		 }
		 
		 public  <T> LoadApp_Component childForm(Object object, int item_id){
			
			 if(object != null) {
				 List<T> list = (List<T>) object;
				 if(list.size() >= item_id) {  
					 Object Transaction = list.get(item_id); 
					 System.out.println("Transaction "+Transaction.toString());
					 modelData.put("app_item_id", item_id);
					 modelData.put("transaction", Transaction); 
					 this.LoadApp.setItem_id(String.valueOf(item_id));
					 }
			 }
			 this.child = 4;
			 return this;
		 }	
		 public  LoadApp_Component input(){
			 this.child = 5;
			 return this;
		 }
		 public  LoadApp_Component link(){
			 this.child = 6;
			 return this;
		 }
		 public  LoadApp_Component feed(int id){
			 this.child = 7;
			 System.out.println(" id "+id); 
			 this.LoadApp.setFeed(id);
			 return this;
		 } 
		
		 public  LoadApp_Component data() {
			 drawTabForm();
			 return this;
		 } 
		 
		 public  LoadApp_Component tabFormItem(){
			 this.child = 8;
			 drawTabFormItem();
			 return this;
		 }
		 
			public LoadApp_Component docProperty() {
				
				 getDocProperty();
				 this.child = 8;
				 return this;
			}


		public LoadApp_Component workFlowSetting() {

			 this.child = 5;
			 loadConFigWorkFlow();
			 return this;
		}
		 
		 
		public void showFormNewFile() {
			
			
			 List<Links> listLinks = WorkflowDAO.getLinksByidrefer(this.LoadApp.getProcess().getId());
				Optional<Links> data =  listLinks.stream().filter(row->row.getRelationship() != null).findFirst();
				 if(data.isPresent()) {
					 Links datas =  data.get(); 
					 modelData.put("join", datas.getLink_to());
				 }
			
				 
				 
				 boolean hasIndex = this.LoadApp.getProcess()
						    .getSub_processes()
						    .stream()
						    .map(sub_processes::getAction) // ดึงเฉพาะ Action ออกมา
						    .filter(Objects::nonNull)   // กรองตัวที่เป็น null ออกไปก่อน
						    .anyMatch(action -> "Profile,Product".contains(action));
				if(hasIndex) {
					modelData.put("hasIndex",1);
				}
		}
		
		
		

		/**
		 * Displays or prepares the complete set of data retrieved from {@code loadPlatForm} OR {@code loadRelation}.
		 * <p>
		 * This method is responsible for presenting the fully assembled data,
		 * including all related entities or components associated with the platform process.
		 * It should be called after {@code loadPlatForm()} OR {@code loadRelation} to ensure that all dependencies
		 * and contextual data are properly initialized.
		 * </p>
		 */
		 public LoadApp  load(){
			 
			 if(this.LoadApp.getProcessing_type() != null) {	 
				   if(this.LoadApp.getProcessing_type().equals("Relation")) {
					   Relation(this.LoadApp);
					   return this.LoadApp;
				   }
			 }
			   
			   
			   switch(this.child) {
				   case 1 -> LoadSubData(this.LoadApp);
				   case 2 -> LoadFormSubData(this.LoadApp);
				   case 3 -> LoadApphasChanges(this.LoadApp);
				   case 4 -> LoadFormSubDataForm(this.LoadApp);
				   case 5 -> LoadInput(this.LoadApp);
				   case 6 -> LoadLink(this.LoadApp);
				   case 7 -> LoadFeed(this.LoadApp);
			   	   case 8 -> Noting();
				   default -> LoadApp(this.LoadApp);
			   }  
		   
		   
		   
		   
		   
		   		return this.LoadApp;
		 } 
	  

		private Object Noting() {
			// TODO Auto-generated method stub
			return null;
		}

		private void Relation(LoadApp loadApp) {
			 LoadAppUnity_Components.initiate(this.modelData).Relation(LoadApp);
		}


		private void LoadApp(LoadApp LoadApp)  {
			 

			
			// ---------------------------------------------------------
		    // 🚀 PART 1: Static Config (ทำ Caching ตาม Version)
		    // ---------------------------------------------------------
		    

		       // โหลดหนักๆ
	        App_Components.initiate(this.modelData).unity(LoadApp);
	        Map<String, Object> heavyData = App_Components.getModelData();
	        if (heavyData != null) {
	            this.modelData.putAll(heavyData);
	        }
			
			 LoadAppUnity_Components.initiate(this.modelData)
			 .LoadData(LoadApp)
			 .LoadTransaction()
			 .LoadFollowup()
			 .LoadAutoid()
			 .LoadLog()
			 .LoadGoal()
			 .Loadbatchdata()
			 .LoadLevenshtein()
			 .LoadCompare();
			  modelData.putAll(LoadAppUnity_Components.getModelData());
			 
			 
			// ClearApp(LoadApp);  
			 
			
		}


	 //LoadApp

		private void LoadApphasChanges(LoadApp LoadApp)  {
		 
			App_Components.initiate(this.modelData).subset(LoadApp);
			LoadAppUnity_Components.initiate(this.modelData)
			 .LoadData(LoadApp)
			 .LoadTransaction()
			 .LoadAutoid()
			 .LoadbatchdatahasChanges();
	}

	

		private void LoadAppViewMode(LoadApp LoadApp) {

		App_Components.unity(LoadApp);
		LoadAppUnity_Components.LoadData(LoadApp).LoadTransaction().LoadAutoid().Loadbatchdata();

	}
	

		private void LoadSubData(LoadApp LoadApp) {
			
		System.out.println("LoadSubData ");	
		App_Components.initiate(this.modelData).subset(LoadApp);
		LoadAppUnity_Components.initiate(this.modelData)
		 .LoadData(LoadApp)
		 .LoadTransaction()
		 .LoadAutoid()
		 .Loadbatchdata();
		System.out.println("app "+this.LoadApp.toString());

		}
		
		
		
		private void LoadFormSubData(LoadApp LoadApp) {
			App_Components.initiate(this.modelData).subset(LoadApp);
			LoadAppUnity_Components.initiate(this.modelData).LoadData(LoadApp).LoadTransaction().LoadAutoid();
		}

		private void LoadFormSubDataForm(LoadApp LoadApp) {
			App_Components.initiate(this.modelData).subset(LoadApp);
			LoadAppUnity_Components.initiate(this.modelData).LoadData(LoadApp);
		}
		
		private void LoadInput(LoadApp LoadApp) {
			App_Components.initiate(this.modelData).LoadInputOnly(LoadApp);
		}
		private void LoadLink(LoadApp LoadApp) {

			App_Components.initiate(this.modelData).LoadLinkOnly(LoadApp);
		}
		private void LoadFeed(LoadApp LoadApp) {
			App_Components.initiate(this.modelData).LoadFeed(LoadApp);
		}
		 private void drawTabForm() {
			 App_Components.initiate(this.modelData).LoadTabForm(LoadApp);
			
		}

		 
		 private void drawTabFormItem() {
			 App_Components.initiate(this.modelData).LoadTabFormItem(LoadApp);
			
		}
		 
		 

			public void Comparedata() {
				List<FieldChange> diffList = Productions.LoadCompareVersioning(LoadApp);
				this.modelData.put("diffList", diffList); 
				
			}


		private void loadConFigWorkFlow() {
				dataset enumMain = DatasetDAO.getMain("Workflow_status");
				List<dataset_data> enumData =  Dataset_dataDAO.getall(enumMain.getDataset_id());
				this.modelData.put("closing_status", enumData);
				
				
				
		}

		public LoadApp_Component  register() {
			if(LoadApp.getPlatform().getUseformPrint() == null) {	return this;}
			if(LoadApp.getPlatform().getUseformPrint().getRegister_id() == null) {return this;}
			
			try {
			    // 1. ดึงชื่อคอลัมน์ออกมาก่อน (เช่น "data_value5")
			    String columnName = LoadApp.getPlatform().getUseformPrint().getRegister_id();
			    
			    // 2. ดึง Object edata ออกมา
			    edata edataObj = LoadApp.getEdata();

			    // 3. ใช้ Reflection เข้าถึง Field ตามชื่อที่อยู่ในตัวแปร columnName
			    java.lang.reflect.Field field = edataObj.getClass().getDeclaredField(columnName);
			    
			    // 4. เปิดสิทธิ์ให้เข้าถึง (กรณี Field เป็น private)
			    field.setAccessible(true);
			    
			    // 5. ดึงค่าออกมาเป็น String
			    String customerCode = (String) field.get(edataObj);
			    
			    registers register   = Productions.getProfileByCode(customerCode.trim());
				this.modelData.put("register", register);
			    System.out.println("ค่าที่ดึงได้: " + customerCode);

			} catch (NoSuchFieldException | IllegalAccessException e) {
				
				
			    System.out.println("ค่าที่ดึงได้: customerCode = null" );
			    registers register   = new registers();
				this.modelData.put("register", register);
			}
			
			return this;
		}

		public LoadApp_Component promotion() {
			// TODO Auto-generated method stub
			return this;
		}

		public LoadApp_Component paymentterms() {
			
			System.out.println(" paymentterms "); // debug ดูค่า

			
		    if (LoadApp.getPlatform().getUseformPrint() == null) { return this; }
		    
		    
		    Edata_maindata  maindata= EdataDAO.getEdatamainData(LoadApp.getEdata().getData_id());
		    if(maindata == null) { return this; }
		    

		    String type = "ชำระเงินก่อนเริ่มดำเนินกิจกรรม ไม่เกิน 7 วัน";
		    if(maindata.getEntity().equals("sales_followup") ) {
		    	
		    	sales_followup  followup  = Sales_followupRepository.findByEdataId(LoadApp.getEdata().getData_id());
		    	LoadApp.setTransitionObject(followup);

		    	String amountStr = followup.getPayment_terms();
				    	if (amountStr != null ) {
				    		type =amountStr;
 		    }

		    

		    
		    Object transitionObj = LoadApp.getTransitionObject();

		    // 3. [สำคัญ] เช็คว่า Object นี้ เป็น instance ของ class sales_followup จริงไหม?
		    // - ถ้า transitionObj เป็น null -> instanceof จะได้ false -> เข้าเงื่อนไข -> return this (หยุด)
		    // - ถ้าเป็น Class อื่น (ตั้งค่าผิด) -> instanceof จะได้ false -> เข้าเงื่อนไข -> return this (หยุด)
		    if (!(transitionObj instanceof sales_followup)) {
		        // (Optional) อาจจะ Log เตือนไว้หน่อยก็ได้ครับว่าทำไมถึงหยุด
		        System.out.println("Warning: TransitionObject is not sales_followup");
		        return this; 
		    }
		    sales_followup Sales_followup = (sales_followup) LoadApp.getTransitionObject();
		    

		    /*test data
		    Sales_followup.setNet_amount("10000");
		    Sales_followup.setMain_type_service("อบรม"); 
		    Sales_followup.setUnit_total(2); 
		    Sales_followup.setMain_unit_type("MD"); 
		    */
		    
			System.out.println(" paymentterms sales_followup "); // debug ดูค่า
		    
			System.out.println(" getNet_amount"+Sales_followup.getNet_amount() ); // debug ดูค่า
			System.out.println(" getMain_type_service"+Sales_followup.getMain_type_service() ); // debug ดูค่า
			System.out.println("getMain_unit_type"+Sales_followup.getMain_unit_type() ); // debug ดูค่า
			System.out.println(" getUnit_total"+Sales_followup.getUnit_total() ); // debug ดูค่า
			
		 // ... หลังจากบรรทัด sales_followup Sales_followup = (sales_followup) transitionObj; ...

		    // ==================================================
		    
		    
		    // VALIDATION: ตรวจสอบข้อมูลจำเป็นก่อนเริ่มทำงาน
		    // ==================================================
		    if (Sales_followup.getNet_amount() == null || Sales_followup.getNet_amount().trim().isEmpty()) {return this;}

		    // ==================================================
		    // จบการตรวจสอบ เริ่มคำนวณด้านล่าง
		    
			System.out.println(" // VALIDATION: ตรวจสอบข้อมูลจำเป็นก่อนเริ่มทำงาน "); // debug ดูค่า
		    
		    
		    int myTotal =0;
		    try {
		        // 1. พยายามแปลงข้อความเป็นตัวเลขจำนวนเต็ม
			    myTotal = Integer.valueOf(Sales_followup.getUnit_total()) ; 
		        
		        // 2. เช็คว่าต้องมากกว่า 0
		        if (myTotal <= 0) {
		        	myTotal =0;
		        }
	

		    } catch (NumberFormatException e) {
		        // 4. ถ้าแปลงไม่ได้ (เช่น มีตัวอักษรปน) ระบบจะมาตกที่นี่
		        // แจ้งเตือน: "กรุณากรอกเฉพาะตัวเลขเท่านั้น"
		    }
		    
		    
	
		    String qtType = "MD"; 
		 
	  

		   
		    // ใช้ BigDecimal เพื่อความแม่นยำของการคำนวณเงิน
		    BigDecimal net = new BigDecimal(Sales_followup.getNet_amount());
		    
		    // เช็คภาษาปัจจุบันจาก Spring (th หรือ en)
		    Locale locale = LocaleContextHolder.getLocale();
		    boolean isThai = "th".equalsIgnoreCase(locale.getLanguage());

		    // ตัวจัดรูปแบบตัวเลข (ใส่คอมม่า)
		    DecimalFormat df = new DecimalFormat("#,##0.00");
		    DecimalFormat dfNoDec = new DecimalFormat("#,##0");

		    StringBuilder html = new StringBuilder();

		    

      
		    String footerText = getFooterText(Sales_followup.getPayment_terms() , isThai);

		    // ==========================================
		    // เริ่ม Logic การสร้าง HTML ตาม Type
		    // ==========================================
		    switch (type) {
		    case "ชำระเงินก่อนเริ่มดำเนินกิจกรรม ไม่เกิน 7 วัน": // จ่ายเต็ม 100% ก่อนเริ่ม ไม่เกิน 7 วัน
	            // ส่วนหัวข้อตัวหนาตามที่ขอ
	            html.append(isThai 
	                ? "<b>ชำระเงินก่อนเริ่มดำเนินกิจกรรม ไม่เกิน 7 วัน</b>" 
	                : "<b>Payment before starting activities, not exceeding 7 days</b>")
	                .append("<br>");

	            // เพิ่มรายละเอียดบรรทัดที่ 1 (ยอดเงิน 100%)
	            html.append("1. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(net))
	                .append(isThai ? " บาท(รวม vat) 100%" : " Baht (inc. VAT) 100%")
	                .append("<br>");
	            break;
		        case "เเบ่งจ่าย 3 งวด": // แบ่งจ่าย 3 งวด (40/40/20)
		            BigDecimal p1_40 = net.multiply(new BigDecimal("0.40"));
		            BigDecimal p2_40 = net.multiply(new BigDecimal("0.40"));
		            BigDecimal p3_20 = net.multiply(new BigDecimal("0.20"));

		            html.append(isThai ? "*** การชำระเงินเเบ่งออกเป็น 3 งวดดังนี้ ***<br>" : "*** Payment is divided into 3 installments as follows: ***<br>");
		            
		            // งวดที่ 1
		            html.append("1. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p1_40))
		                .append(isThai ? " บาท(รวม vat) 40% ก่อนเริ่มดำเนินกิจกรรม<br>" : " Baht (inc. VAT) 40% Before starting activities.<br>");
		            
		            // งวดที่ 2
		            html.append("2. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p2_40))
		                .append(isThai ? " บาท(รวม vat) 40% " : " Baht (inc. VAT) 40% ");
		            
		            // Logic แทรก: ระหว่างดำเนินกิจกรรม Manday (PHP: if($mytotal > 1 and $row2['qt_type'] == "MD"))
		            if (myTotal > 1 && "MD".equalsIgnoreCase(qtType)) {
		                String halfMD = dfNoDec.format(myTotal / 2);
		                html.append(isThai ? "ระหว่างดำเนินกิจกรรม Manday ที่ " + halfMD : "During Manday activity No. " + halfMD);
		            }
		            html.append("<br>");

		            // งวดที่ 3
		            html.append("3. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p3_20))
		                .append(isThai ? " บาท(รวม vat) 20% " : " Baht (inc. VAT) 20% ").append(footerText).append("<br>");
		            break;

		        case "เเบ่งจ่าย 2 งวด": // แบ่งจ่าย 2 งวด (50/50)
		            BigDecimal p_50 = net.divide(new BigDecimal("2")); // หรือ multiply 0.5

		            html.append(isThai ? "*** การชำระเงินเเบ่งออกเป็น 2 งวดดังนี้ ***<br>" : "*** Payment is divided into 2 installments as follows: ***<br>");
		            
		            html.append("1. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_50))
		                .append(isThai ? " บาท(รวม vat) 50% ก่อนเริ่มดำเนินกิจกรรม<br>" : " Baht (inc. VAT) 50% Before starting activities.<br>");
		            
		            html.append("2. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_50))
		                .append(isThai ? " บาท(รวม vat) 50% " : " Baht (inc. VAT) 50% ").append(footerText).append("<br>");
		            break;

		        case "เเบ่งจ่าย 2 งวด  (70/30)": // แบ่งจ่าย 2 งวด (70/30)
		            BigDecimal p_70 = net.multiply(new BigDecimal("0.70"));
		            BigDecimal p_30 = net.multiply(new BigDecimal("0.30"));

		            html.append(isThai ? "*** การชำระเงินเเบ่งออกเป็น 2 งวดดังนี้ ***<br>" : "*** Payment is divided into 2 installments as follows: ***<br>");
		            
		            html.append("1. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_70))
		                .append(isThai ? " บาท(รวม vat) 70% ก่อนเริ่มดำเนินกิจกรรม<br>" : " Baht (inc. VAT) 70% Before starting activities.<br>");
		            
		            html.append("2. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_30))
		                .append(isThai ? " บาท(รวม vat) 30% " : " Baht (inc. VAT) 30% ").append(footerText).append("<br>");
		            break;

		        case "เเบ่งจ่าย 4 งวด": // แบ่งจ่าย 4 งวด (25% ทั้งหมด)
		            BigDecimal p_25 = net.multiply(new BigDecimal("0.25"));

		            // หมายเหตุ: ตาม PHP เขียน header ว่า 2 งวด แต่ไส้ในมี 4 ข้อ ผมแก้เป็น 4 งวดให้ถูกต้องตามตรรกะ
		            html.append(isThai ? "*** การชำระเงินเเบ่งออกเป็น 4 งวดดังนี้ ***<br>" : "*** Payment is divided into 4 installments as follows: ***<br>");
		            
		            for (int i = 1; i <= 3; i++) {
		                html.append(i).append(". ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_25))
		                    .append(isThai ? " บาท(รวม vat) 25% ก่อนเริ่มดำเนินกิจกรรม<br>" : " Baht (inc. VAT) 25% Before starting activities.<br>");
		            }
		            
		            html.append("4. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_25))
		                .append(isThai ? " บาท(รวม vat) 25% " : " Baht (inc. VAT) 25% ").append(footerText).append("<br>");
		            break;

		        case "เเบ่งจ่าย 4 งวด ( แบบไตรมาส )": // แบ่งจ่าย 4 งวด (แบบไตรมาส)
		            BigDecimal p_25_q = net.multiply(new BigDecimal("0.25"));

		            html.append(isThai ? "*** การชำระเงินเเบ่งออกเป็น 4 งวดดังนี้ ***<br>" : "*** Payment is divided into 4 installments as follows: ***<br>");
		            
		            // งวด 1
		            html.append("1. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_25_q))
		                .append(isThai ? " บาท(รวมvat)25% ก่อนเริ่มดำเนินกิจกรรม Mandayที่ 1-3<br>" : " Baht(inc. VAT)25% Before starting Manday activities 1-3<br>");
		            // งวด 2
		            html.append("2. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_25_q))
		                .append(isThai ? " บาท(รวมvat)25% ก่อนเริ่มดำเนินกิจกรรม Mandayที่ 4-6<br>" : " Baht(inc. VAT)25% Before starting Manday activities 4-6<br>");
		            // งวด 3
		            html.append("3. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_25_q))
		                .append(isThai ? " บาท(รวมvat)25% ก่อนเริ่มดำเนินกิจกรรม Mandayที่ 7-9<br>" : " Baht(inc. VAT)25% Before starting Manday activities 7-9<br>");
		            // งวด 4
		            html.append("4. ").append(isThai ? "จำนวน " : "Amount ").append(df.format(p_25_q))
		                .append(isThai ? " บาท(รวมvat)25%ก่อนเริ่มดำเนินกิจกรรม Mandayที่10-13<br>" : " Baht(inc. VAT)25% Before starting Manday activities 10-13<br>");
		            break;
		    }
		    
		    
		    
		    this.modelData.put("PaymenttermsText", html.toString());

		    // นำ HTML ที่ได้ไป set ใส่ Object ปลายทาง (สมมติว่ามี field ชื่อ setPaymentTermsHTML)
		    // หรือถ้า method นี้ต้อง return ค่าอื่นก็ปรับตามเหมาะสม แต่ตามโจทย์ให้ return this
		    // Sales_followup.setPaymentConditionHTML(html.toString()); 
		    
		    // System.out.println(html.toString()); // debug ดูค่า
			}
		    return this;

		}
		
		
		private String getFooterText(String serviceType, boolean isThai) {

             if(serviceType == null) { return " ";}
		    // *หมายเหตุ: การใช้ Map แบบ simple จะเช็ค Case Sensitive (ตัวเล็กใหญ่ต้องตรงเป๊ะ)
		    // ถ้าจะเอา ignoreCase ต้องวนลูปแบบวิธีที่ 1 จะยืดหยุ่นกว่าครับ

		    if (serviceType.equals("ก่อนส่งมอบงาน" )) {
		        return isThai ? "ก่อนส่งมอบงาน" : "Before delivery";
		    } else {
		        return isThai ? "เมื่อสิ้นสุดการทำงาน MD สุดท้าย" : "Upon completion of the last MD";
		    }
		}

		 
		private void getDocProperty() {
				Edata_maindata main =  EdataDAO.getEdatamainData(LoadApp.getEdata().getData_id());
				
				System.out.println("getDocProperty  Data_id" +LoadApp.getEdata().getData_id());
				
				if(main == null) { return;}
				
				switch (main.getEntity()) {
					case  "sales_followup"->{						
						sales_followup followup  = Productions.getSales_followupByid(main.getMain_data_id());
						
						if(followup == null) { return;}
						
						String customerCode = followup.getCustomerCode();
						String productCode = followup.getProductCode();
						
						product_master  product =  EdataDAO.getProduct_masterCode(productCode);
						registers  register =  EdataDAO.getProfileByCode(customerCode);
						
						this.modelData.put("register", register);
						this.modelData.put("product", product);
						this.modelData.put("followup", followup);
						
					
					}
				
			}
		}

	

		
}
