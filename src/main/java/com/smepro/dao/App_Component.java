package com.smepro.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;


import com.smepro.enums.Actiontype;
import com.smepro.enums.AppAbility;
import com.smepro.models.FieldConfig;
import com.smepro.models.Group_label;
import com.smepro.models.Idoc;
import com.smepro.models.InputHiddenVisibility;
import com.smepro.models.Links;
import com.smepro.models.LoadApp;
import com.smepro.models.Platform;
import com.smepro.models.Relations;
import com.smepro.models.Segmentation;
import com.smepro.models.Tabform;
import com.smepro.models.condition;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.department;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_stock;
import com.smepro.models.eformfield;
import com.smepro.models.keymap;
import com.smepro.models.process;
import com.smepro.models.product_master;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.service.AppComponent;
import com.smepro.service.AppInboxService;
import com.smepro.service.FlowServices;
import com.smepro.service.LogService;
import com.smepro.service.PlatformService;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class App_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	private otherservice Otherservice;
	private eformfieldDAO EformfieldDAO;
	private departmentDAO DepartmentDAO;
	
	private Machinery_Factory Machinery_Factorys;
	private Sales_Customer Sales_Customers;
	private Finance_Accounting Finance_Accountings;
	private HR_Workforce HR_Workforces;
	private Procurement_SupplyChain Procurement_SupplyChains;
	private Production Productions;
	private Stock_Warehouse Stock_Warehouses;
	private LogService logService;
	private AppComponent appComponent;
	private State States = State.NONE;
	private  Map<String, Object> modelData ;
	private Register_Component Register_Components;
	
	
	

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
	    		otherservice Otherservice,
	    		eformfieldDAO EformfieldDAO,
	    		departmentDAO DepartmentDAO,
	    		Machinery_Factory Machinery_Factorys,
	    		Sales_Customer Sales_Customers,
	    		Finance_Accounting Finance_Accountings,
	    		HR_Workforce HR_Workforces,
	    		Procurement_SupplyChain Procurement_SupplyChains,
	    		Production Productions,
	    		Stock_Warehouse Stock_Warehouses,
	    		LogService logService,
	    		AppComponent appComponent,
	    		Register_Component Register_Components
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
	        this.Otherservice = Otherservice;
	        this.EformfieldDAO = EformfieldDAO;
	        this.DepartmentDAO = DepartmentDAO;
	        
	        this.Machinery_Factorys = Machinery_Factorys;
	        this.Sales_Customers = Sales_Customers;
	        this.Finance_Accountings = Finance_Accountings;
	        this.HR_Workforces = HR_Workforces;
	        this.Procurement_SupplyChains = Procurement_SupplyChains;
	        this.Productions = Productions;
	        this.Stock_Warehouses = Stock_Warehouses;
	        this.logService = logService;
	        this.appComponent = appComponent;
	        this.Register_Components = Register_Components;
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
	private String appmodel;
	
	
	private List<keymap> keymaps;
	private  Idoc docs;
	private user users;
	private sub_processes subprocesses;

	private sub_processes action_batch_insert;
	private sub_processes custom_clear;
	private sub_processes action_batch_clear;

	

	// 1. สร้างล็อกเกอร์เก็บ "ตะกร้า Map" แยกตาม Process และ Version
    private static final Map<String, Map<String, Object>> uiMapCache = new ConcurrentHashMap<>();
	
	
		public App_Component initiate(Map<String, Object> modelData) {
			this.modelData = modelData;
			return this;
		}


	

	
	
	
	public LoadApp ability(LoadApp LoadApp) {
		   
		  try {	
			  
			  
			  
			  List<keymap> Keymap_alls = EdataDAO.getAllKeyMaps(LoadApp.getProcess().getId());
			  
			  
			  //ร้องขอ Subport data
			 /*
			 Optional<sub_processes> Request_Subport_data  =  LoadApp.getProcess().getSub_processes().stream().filter(row-> row.getChecking_list() != null).findFirst();
			 if(Request_Subport_data.isEmpty()) {  return LoadApp; }
			//  Request Subport data 
			*/
			  
			 System.out.println(" ---------> Have Ability ");
			  
						switch (LoadApp.getWorkflow().getWorkflow_maindb()) {
						
						case "Stock_Warehouse":
							Stock_Warehouses.getAbility(LoadApp,Keymap_alls);				
						break;  
						case "Procurement_SupplyChain":
							Procurement_SupplyChains.getAbility(LoadApp,Keymap_alls);
							break;  
						case "Production":
							Productions.getAbility(LoadApp,Keymap_alls);
							break;  
						case "Machinery_Factory":	
							Machinery_Factorys.getAbility(LoadApp,Keymap_alls);
							break; 
						case "Sales_Customer":	
							Sales_Customers.getAbility(LoadApp,Keymap_alls);
							break; 
						case "Finance_Accounting":	
							Finance_Accountings.getAbility(LoadApp,Keymap_alls);
							break; 
						case "HR_Workforce":	
							HR_Workforces.getAbility(LoadApp,Keymap_alls);
							break; 
						default:
    
						}
						
						
						
		  }catch(Exception  e) {
	      	  System.err.println("ERR -> "+ e);
	      	  States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
	       }	
		  
		  
			DisplayAbility(LoadApp);
		    return LoadApp;

	}
	
	
	
	protected void DisplayAbility(LoadApp LoadApp) {
	
		LoadApp.getProcess().getSub_processes()
		.stream()
		.filter(row-> row.getChecking_list() != null)
		.findFirst().ifPresent(row-> exposeAs("ability1",1));
		
		

		
		   System.out.println("Ability " +LoadApp.getAbility());
		   
		
		
			if (LoadApp.getAbility().contains(AppAbility.register_stock.toString())) {
				
				 exposeAs("formability1","Stock");
				
			} 
			if (LoadApp.getAbility().contains(AppAbility.register_list.toString())) {
			
				 exposeAs("formability1","List of names");
				
			} 
			if (LoadApp.getAbility().contains(AppAbility.register_product.toString())) {
		
				 exposeAs("formability1","Product");
				
				   System.out.println("Ability " +LoadApp.getAbility());
			} 
			else {
			    System.out.println("ไม่มีค่า register_stock");
			}
	
	}

public LoadApp unity(LoadApp loadApp) {

        if(loadApp.getProcess() == null || loadApp.getProcess().getSub_processes() == null ) { 
            return loadApp;
        }

        int processId = loadApp.getProcess().getId();
        String version = loadApp.getProcess().getVersion() != null ? loadApp.getProcess().getVersion() : "1.0";
        String cacheKey = "UI_MAP_" + processId + "_v" + version;

        int test = 0;
        
        
        Map<String, Object> cachedUI = uiMapCache.get(cacheKey);
        if (cachedUI != null && !cachedUI.isEmpty()  && test == 0)  { 
            System.out.println("⚡ เทค่า UI จาก Cache จำนวน " + cachedUI.size() + " รายการ ลง modelData");
            
            // =========================================================
            // 🔍 โซน Debug: แกะกล่องดูทีละ Key ว่าข้างในเป็นอะไร?
            // =========================================================
            System.out.println("--- 📦 เปิดกล่อง Cache ---");
            for (Map.Entry<String, Object> entry : cachedUI.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                
                String valInfo = "null";
                if (val != null) {
                    valInfo = "Type: " + val.getClass().getSimpleName();
                    
                    // ถ้าเป็น List หรือ Set ให้โชว์ว่ามีกี่ตัว
                    if (val instanceof java.util.Collection) {
                        valInfo += " (Size: " + ((java.util.Collection<?>)val).size() + ")";
                    } 
                    // ถ้าเป็น Map ซ้อน Map ให้โชว์ว่ามีกี่ Key
                    else if (val instanceof java.util.Map) {
                        valInfo += " (Size: " + ((java.util.Map<?,?>)val).size() + ")";
                    } 
                    // ถ้าเป็น String โชว์ข้อความตัดสั้นๆ
                    else if (val instanceof String) {
                        String str = (String) val;
                        valInfo += " [Value: " + (str.length() > 50 ? str.substring(0, 50) + "..." : str) + "]";
                    }
                }
                
                System.out.println("   🔑 Key: [" + key + "] => " + valInfo);
            }
            System.out.println("--------------------------");
            
            
         // 1. ดึง eformfields (ที่เป็นร่างโคลน หรือเพิ่งสร้างเสร็จ) ออกมาจาก modelData
            List<eformfield> currentEformfields = ( List<eformfield>) cachedUI.get("eformfields");
            if (currentEformfields != null) {
                filterHeads(currentEformfields, loadApp.getTabForm());
            }

            // ยัดลง Map ปลายทาง
            this.modelData.putAll(cachedUI); 
        } else {
            System.out.println("🏗️ วาด UI ใหม่...");
            
            // 1. ถ่ายรูป Key/Value เดิมไว้ก่อน
            Map<String, Object> beforeState = new HashMap<>(this.modelData);

            // 2. รันให้มันพ่นของลง this.modelData
            LoadSetup(loadApp);
            LoadPropertyBatch(loadApp);
            IsCleared(loadApp);
            doLoadInput(loadApp);
            Loadautoid(loadApp);
            LoadInputDB(loadApp);
            LoadInputBatch(loadApp);
            ability(loadApp);

            // 3. กวาดของใหม่
            Map<String, Object> snapshotUI = new HashMap<>();
            for (Map.Entry<String, Object> entry : this.modelData.entrySet()) {
                String key = entry.getKey();
                Object newVal = entry.getValue();
                Object oldVal = beforeState.get(key);
                
                // ถ้าค่าเปลี่ยนไปจากเดิม หรือเป็นของใหม่ที่เพิ่งงอกมา
                if (newVal != null && newVal != oldVal) {
                    snapshotUI.put(key, newVal);
                }
            }
            
            
            
            // เช็คตาข่าย! ดูว่ากวาดอะไรติดมาบ้าง
            System.out.println("📸 ดักจับ UI ลง Cache ได้: " + snapshotUI.keySet());
            
            uiMapCache.put(cacheKey, snapshotUI);
        }

        alwaysLoadInputDB(loadApp);
        LoadDocRefer(loadApp);

        return loadApp; // ส่งกลับหล่อๆ 555
    }



		public LoadApp subset(LoadApp LoadApp) {
			if(LoadApp.getProcess().getSub_processes() == null ) { return LoadApp; }
			LoadSetup(LoadApp);
			LoadPropertyBatch(LoadApp);
	 		LoadInputBatch(LoadApp);
	 		LoadInputDB(LoadApp);
	 		ability(LoadApp);
	        return LoadApp;
	}
	
		public LoadApp LoadInput(LoadApp LoadApp) {
				doLoadInput(LoadApp);
		        return LoadApp;
		}
				
		
		
	public LoadApp LoadInputOnly(LoadApp LoadApp) {
		System.out.print(" LoadInputOnly ----> ");
		
			LoadInputAndInputLink(LoadApp);
	        return LoadApp;
	}
		
	
	public LoadApp LoadLinkOnly(LoadApp LoadApp) {
	
			LoadLink(LoadApp);
	        return LoadApp;
	}
	
	
	public LoadApp LoadFeed(LoadApp LoadApp) {
		
		LoadFeeds(LoadApp);
        return LoadApp;
	
	
	
	}

	public LoadApp LoadTabForm(LoadApp LoadApp) {
			
			LoadTabForms(LoadApp);
			LoadInputHiddenVisibility(LoadApp);
	        return LoadApp;
		}



	public LoadApp LoadTabFormItem(LoadApp LoadApp) {
			LoadTabFormItemData(LoadApp);
			
			LoadEformfield(LoadApp);
	        return LoadApp;
		}
		


		private void LoadLink(LoadApp loadApp) {
			
			  int myprocess = 0;
			  if(loadApp.getProcess() != null) { myprocess = loadApp.getProcess().getId(); }
			
			
				 List<process> process =  EdataDAO.getAllProcess(loadApp.getWorkflow().getWorkflow_id(),myprocess);
				 
				 process.stream().forEach(row->{
					
						condition condition = ConditionDAO.getbyid(row.getId_condition());
						row.setCondition(condition);
				 });
				 
				 exposeAs("Linkprocess",process); 
				  
				 List<process> process2 =  EdataDAO.getAllProcessJoin(loadApp.getWorkflow().getWorkflow_id());
				 
				 process2.stream().forEach(row->{
						workflow join = WorkflowDAO.getbyid(row.getId_refer());
					    row.setNote(join.getWorkflow_name());
				 });
				 
				 exposeAs("Linkprocess2",process2); 
		}
		
		
		private void LoadFeeds(LoadApp loadApp) {
		
			Links  Link =  WorkflowDAO.getLinksByid(loadApp.getFeed());
			
			System.out.println(" LoadFeeds ");
			
			if(Link == null) { return;}

			System.out.println(" Link "+Link.getLink_to());
			
			process Feeds = EdataDAO.getProcess( Integer.valueOf(Link.getLink_to()) );
			condition Feedsconditions = ConditionDAO.getbyid(Feeds.getId_condition()); 
			
			
			List<eformfield> eformfields = EformfieldDAO.getall(Feedsconditions.getCondition_id(),Feedsconditions.getCondition_rv()); 
	    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(Feeds.getId());
	    	
	    	
	    	Iterator<eformfield> iterator = eformfields.iterator();
	    	while (iterator.hasNext()) {
	    	   
	    		eformfield row = iterator.next();
	    		Optional<keymap> data = keymaps.stream()
	    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			    .filter(subrow -> "create".equals(subrow.getType()))
	    			    .findFirst()
	    			    .or(() -> keymaps.stream() // ถ้าไม่มี "action" ให้หาค่าที่ตรงกับ Field_id โดยไม่สนใจ action_type
	    			        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			        .findFirst());

				    	if(data.isPresent()) {
				    		keymap keymap = data.get();
				    		row.setKeymap(keymap);
				    	}else {
				    		iterator.remove();
		    	            continue; // ไปยัง row ถัดไป
				    	}	
				    	
	    	}
	     	exposeAs("Feedseformfields",eformfields);
			
	     	LoadInputAndInputLink(loadApp);
		
		}


	
		private void LoadInputAndInputLink(LoadApp loadApp) {
			
		   	List<eformfield> eformfields = EformfieldDAO.getall(loadApp.getCondition().getCondition_id(),loadApp.getCondition().getCondition_rv()); 
	    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(loadApp.getProcess().getId());
	    	 
	    	List<FieldConfig> FieldConfig = EformfieldDAO.getallFieldConfigByprocess_id(loadApp.getProcess().getId());
	    	
	    	
	    	Iterator<eformfield> iterator = eformfields.iterator();
	    	while (iterator.hasNext()) {
	    		
	    		
	    		
	    		
	    	   
	    		eformfield row = iterator.next();
	    		
	    		
	    		Optional<FieldConfig> field_configs = FieldConfig.stream().filter(rows-> rows.getId_refer() == row.getField_id()).findFirst();
	    		if(field_configs.isPresent()) {
	    			FieldConfig field_config = field_configs.get();
	    			row.setAllow_adding_items(field_config.getAllow_adding_items());
	    			row.setData_access_interface(field_config.getData_access_interface());
	    			
	    			System.out.println("Id_refer "+ field_config.getId_refer());
	    			System.out.println("Data_access_interface "+field_config.getData_access_interface());
	    			
	    		}
	    		
	    		
	    		Optional<keymap> data = keymaps.stream()
	    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			    .filter(subrow -> "create".equals(subrow.getType()))
	    			    .findFirst()
	    			    .or(() -> keymaps.stream() // ถ้าไม่มี "action" ให้หาค่าที่ตรงกับ Field_id โดยไม่สนใจ action_type
	    			        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			        .findFirst());

				    	if(data.isPresent()) {
				    		keymap keymap = data.get();
				    		row.setKeymap(keymap);
				    	}else {
				    		iterator.remove();
		    	            continue; // ไปยัง row ถัดไป
				    	}	
				    	
	    	}
	     	exposeAs("eformfields",eformfields);
		}

		
			private void LoadEformfield(LoadApp loadApp) {
			   	List<eformfield> eformfields = EformfieldDAO.getall(loadApp.getCondition().getCondition_id(),loadApp.getCondition().getCondition_rv()); 
			 	exposeAs("eformfields",eformfields);
			}

		private void LoadInputBatch(LoadApp loadApp) {

			System.out.println(" LoadInput ---->" );
	    	List<eformfield> eformfields = EformfieldDAO.getall(loadApp.getCondition().getCondition_id(),loadApp.getCondition().getCondition_rv()); 
	    	List<String> mainkeyList = new  ArrayList<String>(Arrays.asList("Register_Type,Stock_Type,Product_Type,FG_Type,Wip_Type,WorkCenter_Type,Machine_Type,Asset_Type,Accountcode_Type"));
	  
	    	System.err.println(" eformfields "+eformfields.size());
	    	
	    	
	    
	    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(loadApp.getProcess().getId());
	    	
	    	List<String> input_db = new ArrayList<>();
	    	
	    	String visibility = loadApp.getProcess().getVisibility();
	    	Set<Integer> visibleIds = null;
	    	if (visibility != null && !visibility.isBlank()) {
	    	    visibleIds = Arrays.stream(visibility.split(","))
	    	        .map(String::trim)
	    	        .map(Integer::valueOf)
	    	        .collect(Collectors.toSet());
	    	    
	    	    Set<Integer> streamvisibleIds = visibleIds;
	    	    eformfields = eformfields.stream().filter(row -> streamvisibleIds == null || !streamvisibleIds.contains(row.getField_id())).collect(Collectors.toList()); 
	    	}
	    	
	    	
	    	List<String> batchInputs = loadApp.getBatch_input() == null ? 
	    			new ArrayList<String>() :  Arrays.asList(loadApp.getBatch_input().split(","));
	    	
	    	registerkey  Registerkey = null;
	    	Iterator<eformfield> iterator = eformfields.iterator();
	    	while (iterator.hasNext()) {
	    	   
	    		eformfield row = iterator.next();
	    		Optional<keymap> data = keymaps.stream()
	    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			    .filter(subrow -> "create".equals(subrow.getType()))
	    			    .findFirst()
	    			    .or(() -> keymaps.stream() // ถ้าไม่มี "action" ให้หาค่าที่ตรงกับ Field_id โดยไม่สนใจ action_type
	    			        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			        .findFirst());

				    	if(data.isPresent()) {
				    		keymap keymap = data.get();
				    		
				    		 if (!batchInputs.contains(keymap.getMainkey())) {
				    	            iterator.remove();
				    	            continue; // ไปยัง row ถัดไป
				    	        }

				    		
				    		
				    		if(Registerkey == null) {
				    			Registerkey = EdataDAO.getRegisterkey(keymap.getRegisterkey_id());
				    			appmodel = appComponent.getAppModel(Registerkey,keymap);
				    		}
				    		
				    		System.out.println("Registerkey2 run "+keymap.getRegisterkey_id());
				    		
				    		
				    		
				    		row.setKeymap(keymap);
				    		if(mainkeyList.contains(keymap.getMainkey())) {
				    			
				    			
				    			
				    			List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(), Otherservice.StringValueNull(loadApp.getProcess().getEnums())) ;
					    		row.setDataset_data(dataset_data);
					    		
					    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf( keymap.getEnum_id()));
					    		row.setDataset(dataset);
					    		
				    		}else {
				    		
				    			
				    			List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(),null) ;
					    		row.setDataset_data(dataset_data);
				    			
					    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf( keymap.getEnum_id()));
					    		row.setDataset(dataset);
				    		}

				    		
				    		
				    		if(keymap.getChange_format().equals("DB_User") 
				    				|| keymap.getChange_format().equals("DB_Department") 
				    				|| keymap.getChange_format().equals("Segmentation_Profile_Static_snapshot")
				    				) {
				    			
				    			input_db.add(keymap.getChange_format());
				    		}
				    		if(keymap.getChange_format().equals("Mandatory Reference Key") || keymap.getChange_format().equals("Mandatory Reference Key(AT)")) {
				    			row.setData_access_interface(1); 
				    		}
				    	}else {
				    	    iterator.remove();
		    	            continue; // ไปยัง row ถัดไป
				    	} 	
				    	
	    	}
	    	

	    	
	    	loadApp.getInput_db().addAll(input_db);

	    	System.err.println(" appmodel2 "+appmodel);
	      	filterReplaceDB(eformfields,loadApp);
	      	filterHeadsSubData(eformfields);
	    	exposeAs("subeformfields",eformfields);
	     	exposeAs("appmodel",appmodel);
		}


		
		
		
		
	
	private void doLoadInput(LoadApp loadApp) {

				System.out.println(" LoadInput ---->" );
		    	List<eformfield> eformfields = EformfieldDAO.getall(loadApp.getCondition().getCondition_id(),loadApp.getCondition().getCondition_rv()); 
		    
		    	List<FieldConfig> FieldConfig = EformfieldDAO.getallFieldConfigByprocess_id(loadApp.getProcess().getId());
		    	
		    	
		    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(loadApp.getProcess().getId());
		    	
		    	List<String> input_db = new ArrayList<>();
		    	
		    	String visibility = loadApp.getProcess().getVisibility();
		    	Set<Integer> visibleIds = null;
		    	if (visibility != null && !visibility.isBlank()) {
		    	    visibleIds = Arrays.stream(visibility.split(","))
		    	        .map(String::trim)
		    	        .map(Integer::valueOf)
		    	        .collect(Collectors.toSet());
		    	    
		    	    Set<Integer> streamvisibleIds = visibleIds;
		    	    eformfields = eformfields.stream().filter(row -> streamvisibleIds == null || !streamvisibleIds.contains(row.getField_id())).collect(Collectors.toList()); 
		    	}
		    	
		    	
		    	List<String> batchInputs = loadApp.getBatch_input() == null ?  
		    			new ArrayList<String>():  Arrays.asList(loadApp.getBatch_input().split(","));
		    	
		    	List<String> mainkeyList = new  ArrayList<String>(Arrays.asList("Register_Type,Stock_Type,Product_Type,FG_Type,Wip_Type,WorkCenter_Type,Machine_Type,Asset_Type,Accountcode_Type"));
		    	
		    	
		    	registerkey  Registerkey = null;
		    	Iterator<eformfield> iterator = eformfields.iterator();
		    	while (iterator.hasNext()) {
		    	   
		    		eformfield row = iterator.next();
		    		
		    		
		    		Optional<FieldConfig> field_configs = FieldConfig.stream().filter(rows-> rows.getId_refer() == row.getField_id()).findFirst();
		    		if(field_configs.isPresent()) {
		    			FieldConfig field_config = field_configs.get();
		    			row.setAllow_adding_items(field_config.getAllow_adding_items());
		    			row.setData_access_interface(field_config.getData_access_interface());
		    			
		    			System.out.println("Id_refer "+ field_config.getId_refer());
		    			System.out.println("Data_access_interface "+field_config.getData_access_interface());
		    			
		    		}
		    		
		    		
		    		Optional<keymap> data = keymaps.stream()
		    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
		    			    .filter(subrow -> "create".equals(subrow.getType()))
		    			    .filter(subrow -> Integer.valueOf(subrow.getRegisterkey_id())  > 0)
		    			    .findFirst();

					    	if(data.isPresent()) {
					    		
					    		
					    		
					    		keymap keymap = data.get();
					    		
					    	System.out.println(" keymap "+keymap.getId());
					    		
					    		
					    		 if (batchInputs.contains(keymap.getMainkey())) {
					    	            iterator.remove();
					    	            continue; // ไปยัง row ถัดไป
					    	        }

					    		
					    		
					    		if(Registerkey == null) {
					    			Registerkey = EdataDAO.getRegisterkey(keymap.getRegisterkey_id());
					    			appmodel = appComponent.getAppModel(Registerkey,keymap);
					    		}
					    		
					    		System.out.println("Registerkey run "+keymap.getRegisterkey_id());
					    		
					    		
					    		
					    		row.setKeymap(keymap);
					    		if(mainkeyList.contains(keymap.getMainkey())) {
					    			List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(), Otherservice.StringValueNull(loadApp.getProcess().getEnums())) ;
						    		row.setDataset_data(dataset_data);
						    		
						    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf( keymap.getEnum_id()));
						    		row.setDataset(dataset);
						    		
					    		}else {
					    			List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(),null) ;
						    		row.setDataset_data(dataset_data);
					    			
						    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf( keymap.getEnum_id()));
						    		row.setDataset(dataset);
					    		}

					    		
					    		
					    		if(keymap.getChange_format().equals("DB_User") 
					    				|| keymap.getChange_format().equals("DB_Department")
					    				|| keymap.getChange_format().equals("Segmentation_Profile_Static_snapshot")
					    				) {
					    			input_db.add(keymap.getChange_format());
					    			
					    			System.out.println("DB_User");
					    		}
					    	
					    		
					    		
					    		
					    		
					    	}else {
					    		
					    		if(row.getField_type().equals("select") || row.getField_type().equals("checkbox") ) {
					    			List<dataset_data> dataset_data  = Dataset_dataDAO.getall(row.getField_datareferid()) ;
						    		row.setDataset_data(dataset_data);
						    		
						    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf(row.getField_datareferid()));
						    		row.setDataset(dataset);
					    		}
					    		
					    		
					    	} 	
					    	
		    	}
		    	

		    	
		    	 System.err.println(" appmodel "+appmodel);
		    	loadApp.setInput_db(input_db);
		    	loadApp.setApp_model(appmodel);

		    	
		      	 System.err.println(" Input_db size "+loadApp.getInput_db().size());
		    	
		    	exposeAs("eformfields",eformfields);
		     	exposeAs("appmodel",appmodel);

		     	
		     	
		     	
		     	filterDisplay(eformfields,loadApp);
		     	filterHidden(eformfields,loadApp);
		     	filterHeads(eformfields,loadApp.getTabForm());
		     	filterReplaceDB(eformfields,loadApp);
			     
		    	exposeAs("TabForms",loadApp.getTabForm());
			}









	






	







		private void LoadInputDB(LoadApp loadApp) {
			
			if(loadApp.getInput_db() == null) {
				System.err.println(" LoadFormfields ต้องมาก่อน LoadInputDB ");
				return;
			}
	 	
			
			System.out.println(" LoadFormfields getInput_db ");
			System.out.println(" LoadFormfields getInput_db "+loadApp.getInput_db().size());
			List<String> inputs = loadApp.getInput_db();

			System.out.println(" inputs  "+inputs);
			
			
				if (inputs.contains("DB_Department")) {
					List<department>  db_department = DepartmentDAO.getall();
					
					
					 exposeAs("db_department",db_department);
					
				}
				if (inputs.contains("DB_User")) {
			    	List<user> db_users = UserDAO.getall();
			    	
			    	 exposeAs("db_users",db_users);
				}
			

		}

	
		
		private void alwaysLoadInputDB(LoadApp loadApp) {
		
			if(loadApp.getInput_db() == null) {
				System.err.println(" LoadFormfields ต้องมาก่อน LoadInputDB ");
				return;
			}
			System.out.println(" LoadFormfields getInput_db ");
			System.out.println(" LoadFormfields getInput_db "+loadApp.getInput_db().size());
			List<String> inputs = loadApp.getInput_db();
			System.out.println(" inputs  "+inputs);
			
					if (inputs.contains("Segmentation_Profile_Static_snapshot")) {
				    	 List<Segmentation> segmentation_profile_static_snapshot = EdataDAO.getallSegmentationByTypeAndNotUse(0,loadApp.getEdata().getData_id());
				    	 exposeAs("segmentation_profile_static_snapshot",segmentation_profile_static_snapshot);

					}
		}
		
		
	public void Loadautoid(LoadApp LoadApp) {
		
		
        List<String> autoid = new ArrayList<String>(); 
		LoadApp.getProcess().getSub_processes().stream()
		.filter(row -> row.getAutoiddatacolumn() != null)
		.forEach(row->{
			autoid.add(row.getAutoiddatacolumn());
			
		});


		exposeAs("autoid",autoid.toString());
		
		
		Optional<sub_processes> batch_gens =  LoadApp.getProcess().getSub_processes()
				.stream().filter(row -> Otherservice.haveValue(row.getBatch_code_generation()) == true ).findFirst();

		if(batch_gens.isPresent()) {
				exposeAs("batch_gens","Auto-generated");
		}
		
	}
	
	
	
	
	
	private void LoadDocRefer(LoadApp LoadApp) {
		
		try {
			Relations Node  = EdataDAO.getTopRoot(LoadApp.getEdata().getData_id());

			if(Node == null ) {	return;	}


			
			List<Relations> Node2  = EdataDAO.getRelationschild(Node.getRelations_id());
			Node2.stream().forEach(row->{
				List<Relations> Node3  = EdataDAO.getRelationschild(row.getMain_id());
				row.setRelations(Node3);
			});

			Node.setRelations(Node2);
			exposeAs("Node",Node); 
		}catch(Exception e) {
			
		}
	}


	
	
	
	
	private void reference_only(LoadApp loadApp) {
		  List<doc> SameNode  = EdataDAO.getSameDoc(loadApp.getEdata().getReference(),loadApp.getEdata().getData_id());
	 	  exposeAs("SameNode",SameNode);
	 	  exposeAs("Node","only");
		}
	
	private void reference_continue(LoadApp loadApp) {
			// TODO Auto-generated method stub
		
		

	 	   doc ParentNode  = EdataDAO.findDoc_in_set(loadApp.getEdata().getReference());
	 	   ParentNode.setDoc_type(loadApp.getEdata().getReference());
	 	   exposeAs("ParentNode",ParentNode);
	 	  
	 	   if(ParentNode != null) {
	 		    edata Node2 = EdataDAO.getbyid(ParentNode.getDoc_idrefer());
	 		    if(Node2 != null ) {
	 		    	
	 		    	if(Node2.getReference() != null) {
		 		    	 doc  RootNode   = EdataDAO.findDoc_in_set(Node2.getReference());
		 		    	 RootNode.setDoc_type(Node2.getReference());
		 		    	 exposeAs("RootNode",RootNode);
	 		    	}
	 		    }   
	 	   }
	 	
	 	   
	 	  List<doc> SameNode  = EdataDAO.getSameDoc(loadApp.getEdata().getReference(),loadApp.getEdata().getData_id());
	 	  exposeAs("SameNode",SameNode);
	 	  exposeAs("Node","ference_continue");
			
		}
	
	private void reference_modify(LoadApp loadApp) {
			// TODO Auto-generated method stub
			
		      List<doc> ParentNode  = EdataDAO.getDoc_in_set(loadApp.getEdata().getReference());
			  exposeAs("ParentNode",ParentNode);
			  
			  if(ParentNode.size() == 0) {
				  ParentNode  =EdataDAO.getSameDoc(loadApp.getEdata().getReference(),0);
				  exposeAs("ParentNode",ParentNode);
			  }

			  exposeAs("Node","modify");
		}
	
	
	private void hierarchical_reference(LoadApp loadApp) {
			// TODO Auto-generated method stub
			
		System.out.println(" hierarchical_reference ->");
		
		
	 	   doc ParentNode  = EdataDAO.findDoc_in_set(loadApp.getEdata().getReference());
	 	   ParentNode.setDoc_type(loadApp.getEdata().getReference());
	 	   exposeAs("ParentNode",ParentNode);
	 	  
	 	   if(ParentNode != null) {
	 		    edata Node2 = EdataDAO.getbyid(ParentNode.getDoc_idrefer());
	 		    if(Node2 != null ) {
	 		    	
	 		    	if(Node2.getReference() != null) {
		 		    	 doc  RootNode   = EdataDAO.findDoc_in_set(Node2.getReference());
		 		    	 RootNode.setDoc_type(Node2.getReference());
		 		    	 exposeAs("RootNode",RootNode);
	 		    	}
	 		    }   
	 	   }
	 	
	 	   
	 	  List<doc> SameNode  = EdataDAO.getSameDoc(loadApp.getEdata().getReference(),loadApp.getEdata().getData_id());
	 	  exposeAs("SameNode",SameNode);
	 	   
	 	   
	 	   exposeAs("Node","hierarchical");
		}



private void LoadDocJoin(LoadApp LoadApp) {
		
		
	StringBuilder buider  = new StringBuilder();
	StringBuilder buider2  = new StringBuilder();

		List<process> join = EdataDAO.getProcess_join(LoadApp.getProcess().getId());
	
	
		
		  System.err.println(" join -->"+join.size());
		join.forEach(row->{
				buider.append(row.getName()).append(",");
				buider2.append(row.getId()).append(",");
		});
		//doc_join
		
	
		
		exposeAs("doc_join",buider.toString());
		exposeAs("doc_join_id",buider2.toString());
		
		
		  System.err.println(" doc_join -->"+buider.toString());
		  System.err.println(" doc_join_id -->"+buider2.toString());
	}

	
	

	private void LoadAllSupportdata(LoadApp LoadApp) {
		
		
		//กรณี Clear จะไม่ไปปรับเเก้ของ เดิมจึงต้องเเยกกัน
		
		
		if(LoadApp.getSub_processes() == null ) { return; }
		
		List<sub_processes> sub_processes = LoadApp.getProcess().getSub_processes();
		String step = LoadApp.getSub_processes().getStep();
		loadIfPresent(sub_processes, step, row -> row.getMaindatacolumn() != null, LoadApp);
		loadIfPresent(sub_processes, step, row -> row.getSubdatacolumn() != null, LoadApp);
		loadIfPresent(sub_processes, step, row -> row.getSubdatacolumn1() != null, LoadApp);
		loadIfPresent(sub_processes, step, row -> row.getSubdatacolumn2() != null, LoadApp);
		loadIfPresent(sub_processes, step, row -> row.getSubdatacolumn3() != null, LoadApp);
		loadIfPresent(sub_processes, step, row -> row.getSubdatacolumn4() != null, LoadApp);
		loadIfPresent(sub_processes, step, row -> row.getSubdatacolumn5() != null, LoadApp);
	

	}



	private void loadIfPresent(List<sub_processes> list, String step, Predicate<sub_processes> condition, LoadApp LoadApp) {
	    list.stream()
	        .filter(condition)
	        .filter(row -> row.getStep().equals(step))
	        .findFirst()
	        .ifPresent(data -> loadsupportdata(LoadApp, data));
	    

	}


	private void IsCleared(LoadApp LoadApp) {
		
		if(LoadApp.getSub_processes() == null ) { return; }
		

		List<sub_processes> sub_processes = LoadApp.getProcess().getSub_processes();
		  //หาว่าเคยผ่าน clear มายัง
		   Optional<sub_processes> step_clear = sub_processes.stream()
		  .filter(row->row.getId() <= LoadApp.getSub_processes().getId() )
		  .filter(row->row.getStep().equals("Clear") )
		  .findFirst();
		  
		  if(step_clear.isPresent()) {
				exposeAs("stepclear",1);
		  }
	}



	public LoadApp LoadPropertyBatch(LoadApp LoadApp) {



		List<sub_processes> sub_processes = LoadApp.getProcess().getSub_processes();
	
		List<String> actionBatchList = new ArrayList<>();

	  if(LoadApp.getAction_count() > 0) {
		  Optional<sub_processes> batch_insert = sub_processes.stream().filter(row->row.getAction_batch_insert() != null ).findFirst();
		  if(batch_insert.isPresent()) { 
			  sub_processes data1 = batch_insert.get();
			  actionBatchList.addAll(Arrays.asList(data1.getAction_batch_insert().split(",")));
			  
			  LoadApp.setBatch_input(data1.getAction_batch_insert());
			  exposeAs("action_batch_insert",data1.getAction_batch_insert());
			  LoadApp.setBatch_insert(data1);
		  }else {
			  
			  Optional<sub_processes> batch_dreference = sub_processes.stream().filter(row->row.getAction_batch_dreference() != null ).findFirst();
			  if(batch_dreference.isPresent()) { 
				  sub_processes data1 = batch_dreference.get();
				  LoadApp.setBatch_input(data1.getAction_batch_dreference());
				  actionBatchList.addAll(Arrays.asList(data1.getAction_batch_dreference().split(",")));

				  exposeAs("action_batch_insert",data1.getAction_batch_dreference());
				  LoadApp.setBatch_insert(data1);
			  }
		  }
		  
	  }	
	  else if(LoadApp.getModify_count() > 0) {
		  
		  System.err.println(" batch_modify -->");
		  
		  Optional<sub_processes> batch_modify = sub_processes.stream().filter(row->row.getAction_batch_modify() != null ).findFirst();
		  if(batch_modify.isPresent()) { 
			  sub_processes datar1 = batch_modify.get();
			  exposeAs("action_batch_modify",datar1.getAction_batch_modify());
			  System.err.println(" batch_modify -->"+datar1.getAction_batch_modify());
			  
			  
			  actionBatchList.addAll(Arrays.asList(datar1.getAction_batch_modify().split(",")));
			  
			  LoadApp.setBatch_modify(datar1);
		  }	
	  }			
	 else if(LoadApp.getContinue_count() > 0) {

		  Optional<sub_processes> batch_continue = sub_processes.stream().filter(row->row.getAction_batch_continue() != null ).findFirst();
		  if(batch_continue.isPresent()) { 
			  sub_processes datar2 = batch_continue.get();

			  exposeAs("action_batch_continue",datar2.getAction_batch_continue());
			  
			  LoadApp.setBatch_continue(datar2);
			  
			  actionBatchList.addAll(Arrays.asList(datar2.getAction_batch_continue().split(",")));
		  }
		 
		  
	 }	
	  	  
	  Optional<sub_processes> custom_clear = sub_processes.stream().filter(row->row.getAction_custom_clear() != null ).findFirst();
	  if(custom_clear.isPresent()) {
		  sub_processes data2 = custom_clear.get();
	
		  LoadApp.setCustom_clear(data2);
			exposeAs("action_custom_clear",data2.getAction_custom_clear());
		  actionBatchList.addAll(Arrays.asList(data2.getAction_custom_clear()));
	  }
	  Optional<sub_processes> batch_clear = sub_processes.stream().filter(row->row.getAction_batch_clear() != null ).findFirst();
	  if(batch_clear.isPresent()) { 
		  sub_processes data3 = batch_clear.get();
	
		  
			exposeAs("action_batch_clear",data3.getAction_batch_clear());
		  LoadApp.setBatch_clear(data3);
		  
		  actionBatchList.addAll(Arrays.asList(data3.getAction_batch_clear()));
	  }  
	  
		exposeAs("actionBatchList",actionBatchList);
		return LoadApp;
	  
	}



	private LoadApp LoadSetup(LoadApp LoadApp) {
		
		
		
		  System.out.println(" unity --> LoadSetup");
		  
		  long register_count = LoadApp.getProcess().getSub_processes().stream()
				    .filter(row -> {
				        String reg = row.getAction();
				        return reg != null && !reg.equals("-") && !reg.equals("0");
				    })
				    .count();

				long action_count = LoadApp.getProcess().getSub_processes().stream()
				    .filter(row -> {
				        String reg = row.getAction();
				        return reg != null && !reg.equals("-") && !reg.equals("0");
				    })
				    .count();
	
				long action_count2 = LoadApp.getProcess().getSub_processes().stream()
					    .filter(row -> {
					        String reg = row.getActivate_document_reference();
					        return reg != null && !reg.equals("-") && !reg.equals("0");
					    })
					    .count();
				
				
				
		long continue_count = LoadApp.getProcess().getSub_processes().stream()
				.filter(row -> row.getActivate_reference_continue() != null)
				.count();
		
		long modify_count = LoadApp.getProcess().getSub_processes().stream()
				.filter(row -> row.getActivate_reference_modify() != null)
				.count();
		
		long hierarchical_count = LoadApp.getProcess().getSub_processes().stream()
				.filter(row -> row.getActivate_hierarchical_reference() != null)
				.count();
		
		long reference_only = LoadApp.getProcess().getSub_processes().stream()
				.filter(row -> row.getActivate_reference_only() != null)
				.count();
		
		long processing_count = LoadApp.getProcess().getSub_processes().stream()
				.filter(row -> row.getActivate_register_processing() != null)
				.count();

		  System.err.println(" modify_count -->"+modify_count);
		
		
		  
			if(action_count > 0) {
				Optional<sub_processes> Activateaction = LoadApp.getProcess().getSub_processes().stream()
					    .filter(row -> {
					        String reg = row.getAction();
					        return reg != null && !reg.equals("-") && !reg.equals("0");
					    }).findFirst();
				
				if(Activateaction.isPresent()) {
					sub_processes Activate_action = Activateaction.get();
					LoadApp.setActivate_action(Activate_action.getAction());
				}
				
		
			}
			  
			if(processing_count > 0) {
				Optional<sub_processes> Activateaction = LoadApp.getProcess().getSub_processes().stream()
					    .filter(row -> {
					        String reg = row.getActivate_register_processing();
					        return reg != null && !reg.equals("-") && !reg.equals("0");
					    }).findFirst();
				
				if(Activateaction.isPresent()) {
					sub_processes Activate_action = Activateaction.get();
					LoadApp.setActivate_action(Activate_action.getActivate_register_processing());
				}
				
		
			}	
			
			
			
			
		  
		
		LoadApp.setRegister_count(register_count);
		LoadApp.setAction_count(action_count + action_count2);
		LoadApp.setContinue_count(continue_count);
		LoadApp.setModify_count(modify_count);
		LoadApp.setHierarchical_count(hierarchical_count);
		LoadApp.setReference_only(reference_only);
		LoadApp.setProcessing_count(processing_count);
		
		

		  exposeAs("continue_count",continue_count);
		  exposeAs("modify_count",modify_count);
		  exposeAs("reference_only",reference_only);
		  exposeAs("hierarchical_count",hierarchical_count);
	
		

		
		return LoadApp;
	}

	
	

	private Object loadautoid(String autoiddatacolumn2) {
		// TODO Auto-generated method stub
		return null;
	}



	public void loadsupportdata(LoadApp LoadApp, sub_processes data) {
	
	System.out.println(" input_supportdata7 "+data.getSubdatacolumn5());
		
		
		//โหลด input refer
       if(data.getMaindatacolumn() != null) {
    	   LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_maindb(),data.getFiltermain());
    
    		  exposeAs("input_supportdata1",data.getMaindatacolumn());
    		  exposeAs("input_db1",data.getInput_db1());
    	   
    	   
    		System.out.println(" input_db1 "+data.getInput_db1());
    		
    	   
    	   
       }
		
       if(data.getSubdatacolumn() != null) {
    		LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_maindb(),data.getFiltersub());
    		
    		  exposeAs("input_supportdata2",data.getSubdatacolumn());
    		  exposeAs("input_db2",data.getInput_db2());
    		
    		
       }	
       if(data.getSubdatacolumn1() != null) {
      		LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_maindb(),data.getFiltersub1());
  		  exposeAs("input_supportdata3",data.getSubdatacolumn1());
  		  exposeAs("input_db3",data.getInput_db3());
         }
       if(data.getSubdatacolumn2() != null) {
   		LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_subdb(),data.getFiltersub2());
   		
   		exposeAs("input_supportdata4",data.getSubdatacolumn2());
		 exposeAs("input_db4",data.getInput_db4());
   		
      }
       if(data.getSubdatacolumn3() != null) {
      		LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_subdb(),data.getFiltersub3());
      	
      		exposeAs("input_supportdata5",data.getSubdatacolumn3());
      		exposeAs("input_db5",data.getInput_db5());
         }
       if(data.getSubdatacolumn4() != null) {
      		LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_subdb2(),data.getFiltersub4());
      
      		
      		exposeAs("input_supportdata6",data.getSubdatacolumn4());
      		exposeAs("input_db6",data.getInput_db6());
         }
          if(data.getSubdatacolumn5() != null) {
         		LoadSupport(LoadApp,LoadApp.getWorkflow().getWorkflow_subdb2(),data.getFiltersub5());

         		exposeAs("input_supportdata7",data.getSubdatacolumn5());
          		exposeAs("input_db7",data.getInput_db7());
         		
            }
          
   		//โหลดinput refer
       
       
 
       
	}
	
	
	
	public void LoadTabForms(LoadApp LoadApp) {

		
		System.out.println("LoadTabForms/TabForms "+LoadApp.getProcess().getTabform() );
		
	
		   List<eformfield> eformfields =
		            EformfieldDAO.getall(
		            		LoadApp.getCondition().getCondition_id(),
		            		LoadApp.getCondition().getCondition_rv()
		            );
		
		
			System.out.println("LoadTabForms/Condition "+LoadApp.getCondition().getCondition_id());
		
			List<Tabform> TabForm =  EdataDAO.getTabForm(LoadApp.getCondition().getCondition_id(),LoadApp.getCondition().getCondition_rv(),eformfields);
			
			if(TabForm.size() == 0) {return; }
			
			TabForm.stream().forEach(row->{
				   if( LoadApp.getSub_processes() == null) {
						row.setTab_show(0);
				   } else if(row.getTab_step() == LoadApp.getSub_processes().getId()) {
						row.setTab_show(1);	
						row.setTab_access(0);
					}else {
						row.setTab_show(0);
					}
			});
			
			// เช็คว่ามี tab_show = 1 ไหม
			boolean hasShow = TabForm.stream()
			        .anyMatch(row -> row.getTab_show() == 1);

			// ถ้าไม่มีเลย → ตั้งตัวแรก
			if (!hasShow && !TabForm.isEmpty()) {
				exposeAs("TabFormStart",1);
			}else {
				exposeAs("TabFormStart",0);
			}
			
			System.out.println("Have  TabForm  "+TabForm.size());
			
	
			LoadApp.setTabForm(TabForm);
			exposeAs("TabForm",TabForm);
		}
	
	
	
		private void LoadInputHiddenVisibility(LoadApp LoadApp) {

			   List<eformfield> eformfields =
			            EformfieldDAO.getall(
			            		LoadApp.getCondition().getCondition_id(),
			            		LoadApp.getCondition().getCondition_rv()
			            );

				System.out.println("Condition "+LoadApp.getCondition().getCondition_id());
				List<InputHiddenVisibility> Hidden =  EdataDAO.getInputHiddenVisibility(LoadApp.getProcess().getId(),eformfields);
				exposeAs("InputVisibility",Hidden);
				
				LoadApp.setInputHiddenVisibility(Hidden);
			
		}



	
	
	private void LoadSupport(LoadApp LoadApp,String db, String filter){
		

		switch (db) {
        case "Stock_Warehouse":
        	LoadSupportStock(LoadApp,filter);
            break;    
        case "Production":
        	LoadSupportProduction(LoadApp,filter);
            break;   
        default:
           
    }	

}

	private void LoadSupportProduction(LoadApp LoadApp,String filter) {
		
	}


	private void LoadSupportStock(LoadApp LoadApp,String filter) {
		
		List<stock> stock_card =  EdataDAO.getStock_cardAll(filter); 

		exposeAs("stock_card",stock_card);
		
	}

	public sub_processes getAction_batch_insert() {
		return action_batch_insert;
	}



	public void setAction_batch_insert(sub_processes action_batch_insert) {
		this.action_batch_insert = action_batch_insert;
	}



	public sub_processes getCustom_clear() {
		return custom_clear;
	}



	public void setCustom_clear(sub_processes custom_clear) {
		this.custom_clear = custom_clear;
	}



	public sub_processes getAction_batch_clear() {
		return action_batch_clear;
	}



	public void setAction_batch_clear(sub_processes action_batch_clear) {
		this.action_batch_clear = action_batch_clear;
	}

	 public Map<String, Object> getModelData() {
	        return modelData;
	    }
	 public void exposeAs(String attribute,Object list) {
	         modelData.put(attribute, list);
	 }


	 
	 
	 
	 private void filterHidden(List<eformfield> eformfields, LoadApp loadApp) {
		    // ป้องกันกรณี loadApp หรือ Edata เป็น null
		    if (loadApp == null || loadApp.getEdata() == null) return;

		    String targetActionType =String.valueOf(loadApp.getEdata().getEdata_index_action_type());
		    List<InputHiddenVisibility> hiddenList = loadApp.getInputHiddenVisibility();

		    if (hiddenList == null) return;

		    // ค้นหาแถวที่มี action_type ตรงกัน
		    hiddenList.stream()
		        .filter(h -> targetActionType.equals(h.getAction_type()))
		        .findFirst()
		        .ifPresent(match -> {
		            // ดึงค่า "55,12,65" มาแยกเป็น List
		            String formColumn = match.getForm_column();
		            if (formColumn != null && !formColumn.isEmpty()) {
		                List<String> hiddenIds = Arrays.asList(formColumn.split(","));

		                // วนลูปเช็ค ID ใน eformfields
		                eformfields.forEach(row -> {
		                    // สมมติว่า ID ของ eformfield เก็บอยู่ใน field_id (และเป็น String หรือ Integer)
		                    // เปลี่ยน String.valueOf(row.getField_id()) ให้ตรงกับชื่อ method id ของคุณ
		                    if (hiddenIds.contains(String.valueOf(row.getField_id()))) {
		                        row.setField_type("hidden");
		                    }
		                });
		            }
		        });
		}
	 
	 
	 

		 private void filterDisplay(List<eformfield> eformfields, LoadApp loadApp) {
			 
			 if( loadApp.getSub_processes() == null) { 
				 System.out.println(" App_Component / filterDisplay loadApp.getSub_processes() =   "+loadApp.getSub_processes() );
				 
				 return;
				 
			 }
			 
			 
			    String p1 = loadApp.getSub_processes().getProhibited1();
			    String p2 = loadApp.getSub_processes().getProhibited2();
	
			    // เช็คว่ามีค่าส่งมาหรือไม่ (ไม่ null และไม่ว่าง)
			    boolean hasP1 = p1 != null && !p1.trim().isEmpty();
			    boolean hasP2 = p2 != null && !p2.trim().isEmpty();
	
			    if (hasP1) {
			        // CASE 1: Blacklist (มีในรายการ -> เป็น readonly)
			        Set<String> blackSet = Arrays.stream(p1.split(","))
			                .map(String::trim)
			                .collect(Collectors.toSet());
	
			        eformfields.stream()
			            .filter(f -> blackSet.contains(String.valueOf(f.getField_id()))) // ถ้า "เจอ" ใน list
			            .forEach(f -> f.setDisplay("readonly")); // จับ set readonly
	
			    } else if (hasP2) {
			        // CASE 2: Whitelist (ไม่อยู่ในรายการ -> เป็น readonly หมด)
			        Set<String> whiteSet = Arrays.stream(p2.split(","))
			                .map(String::trim)
			                .collect(Collectors.toSet());
	
			        eformfields.stream()
			            .filter(f -> !whiteSet.contains(String.valueOf(f.getField_id()))) // ถ้า "ไม่เจอ" ใน list (สังเกตเครื่องหมาย !)
			            .forEach(f -> f.setDisplay("readonly")); // จับ set readonly (เพราะถือว่าเป็นตัวอื่นที่ไม่ได้อนุญาต)
			    }
			}
	 
	 

		 private void filterHeads(List<eformfield> eformfields, List<Tabform> TabForm) {
			    
			    if (TabForm == null || eformfields == null) { return; }
			    
			    System.out.println("TabForm: " + TabForm.size());

			    // 1. ตั้งค่า data_value ให้ eformfields (เรียงตามลำดับ Index)
			    IntStream.range(0, eformfields.size()).forEach(i -> eformfields.get(i).setField_cal_type("data_value" + (i + 1)));

			    // 2. ดึงข้อมูล Group ทั้งหมด และสร้าง Master Map
			    List<Group_label> allGroupLabels = WorkflowDAO.allGroupLabels();   
			    Map<Integer, Group_label> masterGroupMap = allGroupLabels.stream()
			            .collect(Collectors.toMap(Group_label::getId, g -> g));

			    // 3. สร้าง Master Map ของ Field
			    Map<String, eformfield> masterFieldMap = eformfields.stream()
			            .collect(Collectors.toMap(
			                f -> String.valueOf(f.getField_id()), // ใช้ ID อย่างเดียว
			                f -> f,
			                (existing, replacement) -> existing 
			            ));

			    // 4. วนลูปจัดการ TabForm เพื่อดึง Field มาจัดเข้า Group
			    TabForm.stream()
			        .filter(row -> row.getForm_column() != null && !row.getForm_column().trim().isEmpty())
			        .forEach(row -> {
			            
			            // Step A: หา eformfields ทั้งหมดที่อยู่ใน Tab นี้ตามลำดับ
			            List<eformfield> fieldsInThisTab = Arrays.stream(row.getForm_column().split(","))
			                .map(String::trim)              
			                .map(masterFieldMap::get) // หยิบโดยใช้ ID เพียวๆ
			                .filter(Objects::nonNull)       
			                .collect(Collectors.toList());  

			            // สร้าง title ที่เป็นชื่อ label ต่อกันด้วย comma
			            String replacedColumnTitle = fieldsInThisTab.stream()
			                .map(eformfield::getField_label)
			                .collect(Collectors.joining(","));
			            row.setForm_column_title(replacedColumnTitle);

			            // Step B: จับ eformfields ที่อยู่ใน Tab นี้ มา "จัดกลุ่ม" ตาม group_label_id
			            Map<Integer, List<eformfield>> fieldsGroupedById = fieldsInThisTab.stream()
			                .collect(Collectors.groupingBy(eformfield::getGroup_label_id));

			            // Step C: สร้าง List ของ Group_labels เฉพาะที่มีฟิลด์อยู่ใน Tab นี้
			            List<Group_label> groupsForThisTab = new ArrayList<>();

			            for (Map.Entry<Integer, List<eformfield>> entry : fieldsGroupedById.entrySet()) {
			                Integer groupId = entry.getKey();
			                List<eformfield> fieldsForThisGroup = entry.getValue(); // ฟิลด์ที่ตรงกับ Group นี้

			                Group_label originalGroup = masterGroupMap.get(groupId);
			                
			                if (originalGroup != null) {
			                    // **จุดสำคัญ**: Clone Object Group_label ขึ้นมาใหม่
			                    Group_label newGroupForTab = new Group_label();
			                    newGroupForTab.setId(originalGroup.getId());
			                    newGroupForTab.setGroup_label_name(originalGroup.getGroup_label_name());
			                    // ... (ถ้ามี property อื่นใน Group_label เช่น icon, description ก็ copy มาใส่ตรงนี้ด้วยครับ) ...

			                    // ยัดฟิลด์ที่จัดกลุ่มแล้วเข้า Group ที่ Clone มา
			                    newGroupForTab.setEformfields(fieldsForThisGroup); 
			                    
			                    groupsForThisTab.add(newGroupForTab);
			                }
			            }

			            // Step D: ยัดกลุ่มทั้งหมดที่แยกแล้ว เข้าไปใน Tabform 
			            // (แทนของเดิมที่ยัด Field ใส่ Tabform ตรงๆ)
			            row.setGroup_label(groupsForThisTab);
			            
			            System.out.println("Row ID: " + row.getId() + " | Fields mapped: " + fieldsInThisTab.size() + " | Groups created: " + groupsForThisTab.size());
			        });
			}
	
		private void filterReplaceDB(List<eformfield> eformfields, LoadApp loadApp) {
			
			
			
			eformfields.stream().filter(row-> row.getKeymap() != null ).filter(row->row.getKeymap().getChange_format().equals("Mandatory Reference Key") || row.getKeymap().getChange_format().equals("Mandatory Reference Key(AT)") )
			.forEach(row->{
				switch(row.getKeymap().getMainkey()) {
					case "customerCode" ->{
						if(row.getData_access_interface() == 1) {
							fastDB("Profile",loadApp.getSub_processes().getFiltermain(),row);
						}else {
							ReplaceDB("Profile",row);
						}
						
					}
					case "productCode" ->{
						if(row.getData_access_interface() == 1) {
							fastDB("Product",loadApp.getSub_processes().getFiltermain(),row);
						}else {
							ReplaceDB("Product",row);
						}
						
					}
					
				
				}
				
			});
			
		}


		private void filterHeadsSubData(List<eformfield> eformfields) {

			IntStream.range(0, eformfields.size()).forEach(i -> eformfields.get(i).setField_cal_type("data_value" + (i + 1)));
		}
			
			
			
		private void ReplaceDB(String Type ,eformfield eformfields) {
				// TODO Auto-generated method stub
				process processes = WorkflowDAO.getProcessByIndex(Type);
				if(processes != null) {	
					eformfields.setProcess(processes);
				}
		}

		private void fastDB(String Type, String filtermain, eformfield eformfields) {
			// TODO Auto-generated method stub
			
			LoadApp subLoadApp = new LoadApp();
			/*
				switch(Type){
					case "Profile" ->{
						process processes = WorkflowDAO.getProcessByIndex(Type);
						Platform platform2 = new Platform();
						platform2.setApp_processes_id(processes.getId());
						subLoadApp.setPlatform(platform2);
						subLoadApp.setProcess(processes);
						subLoadApp.getProcess().setShow_limit(15000);
						subLoadApp.getProcess().setLimit_head(1);
						
						Register_Components.initiate(subLoadApp,modelData).requireAllIndex().load();
						
						if(modelData.get("exampleData") != null ) {
							List<registers> exampleData  =  (List<registers>) modelData.get("exampleData");
							exposeAs("fastDB_Profile",exampleData);
						}
						
					}
					case "Product" ->{
						process processes = WorkflowDAO.getProcessByIndex(Type);
						Platform platform2 = new Platform();
						platform2.setApp_processes_id(processes.getId());
						subLoadApp.setPlatform(platform2);
						subLoadApp.setProcess(processes);
						subLoadApp.getProcess().setShow_limit(15000);
						subLoadApp.getProcess().setLimit_head(1);
						
						Register_Components.initiate(subLoadApp,modelData).requireAllIndex().load();
						
						if(modelData.get("exampleData") != null ) {
							List<product_master> exampleData  =  (List<product_master>) modelData.get("exampleData");
							exposeAs("fastDB_Product",exampleData);
						}
						
					}
					
					
				}
				*/
		}


		private void LoadTabFormItemData(LoadApp loadApp) {
		// TODO Auto-generated method stub
		
		}







	
}
