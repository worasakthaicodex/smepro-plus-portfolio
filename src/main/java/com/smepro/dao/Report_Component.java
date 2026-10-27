package com.smepro.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
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
import com.smepro.models.Platform;
import com.smepro.models.PlatformConfig;
import com.smepro.models.PrepareReport;
import com.smepro.models.QuickQuery;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.Segmentation;
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
import com.smepro.service.LogService;
import com.smepro.service.PlatformService;
import com.smepro.service.Uploadfileservice;
import com.smepro.service.userservice;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Calendar;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.FileInfo;
import com.smepro.models.FilterChain;
import com.smepro.models.FilterCriteria;
import com.smepro.models.FilterDisplayItem;
import com.smepro.models.FilterRule;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_sk;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.smepro.dto.SourceDTO;
import  com.smepro.entity.Register_list;
import  com.smepro.entity.Register_stock;
import  com.smepro.entity.Register_product;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Report_Component {

	

	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private reportDAO ReportDAO;
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
	private LogService logService;
	
	
	
	
	private Uploadfileservice Uploadfileservices;
	private MessageSource messageSource;
	
	
	private StockRepository StockRepository;
	private RegisterRepository RegisterRepository;
	private ProductRepository ProductRepository;
	private AppComponent appComponent;
	private Production Productions;
	private eformfieldDAO EformfieldDAO;
	private PlatformService PlatformServices;
	private IdocDAO idocDAO;
	
	private State States = State.NONE;
	private List<Integer> allIds = new  ArrayList<Integer>();
	
	
	
	
	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}

	
	
	
	
	


	@Autowired
	public void setMyRepository(
	    		edataDAO EdataDAO,
	    		userDAO UserDAO,
	    		reportDAO ReportDAO,
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
	    		PlatformService PlatformServices,
	    		MessageSource messageSource,
	    		IdocDAO idocDAO,
	    		 LogService logService

	    	
	    		
	    		
	    		) { 
	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;
	        this.ReportDAO = ReportDAO;
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
	        this.PlatformServices = PlatformServices;
	        this.messageSource = messageSource;
	        this.idocDAO = idocDAO;
	        this.logService = logService;
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
	private  Map<String, Object> modelData2 = new HashMap<>();
	private  Map<String, Object> modelDatas; 
	PrepareReport config;
	List<eformfield> eformfields = new ArrayList<eformfield>();
	private FilterChain  queryChain;
	private List<FilterChain>  queryChains =  new ArrayList<FilterChain>();;
	
	
	LoadApp LoadApp = new LoadApp();
	private int calldata = 0;
	boolean drawTable;
	private int countData = 0;
	
	
	public Report_Component initiate(LoadApp loadApp, Map<String, Object> modelDatas) {
		this.modelDatas = modelDatas;
		this.modelData2.putAll(modelDatas);
		this.modelData.clear();
		this.config  = null;
		this.eformfields.clear();
		this.drawTable = true;
		this.LoadApp = loadApp;
		this.calldata = 0;
		this.countData = 0;
		this.queryChain  = null;
		this.queryChains.clear();
		this.allIds.clear();
		return this;

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
	public Report_Component prepareReport() {	
	
		loadReportConfig();
		loadReportData(0);
		return this;
		
	}
	public void setActiveQueryChain() {
		
		setEnabledActiveQueryChain();
	}
	

	public  Report_Component prepareQueryChain(){
		loadReportConfig();
		loadAlltableBySyncData();
		loadAllQueryChain();
		return this;
	}
	

	public  Report_Component prepareCreateQueryChain(){
		
		List<process>  useprocess =  EdataDAO.findAllProcessAllowLooseLink();
		this.modelData.put("useprocess", useprocess);
		
		List<UseExtension>  useextension =  EdataDAO.getallUseExtension();
		this.modelData.put("useextension", useextension);
		
		List<process>  useregister =  EdataDAO.getAllProcessBytype(2);
		this.modelData.put("useregister", useregister);
		
		
		return this;
	}
	

	public Report_Component prepareFilterCriteria() {

		String type  =LoadApp.getPlatformReport().getApp_model();
		int id_refer =LoadApp.getPlatformReport().getApp_id_refer();
				switch(type) {
						case "Profile"->{
								process process = EdataDAO.getProcess(id_refer);
								List<registerkey> heads2  = EdataDAO.getRegisterkeyByListview(process.getListview2());
								ShowTableRegisterDetails(heads2);
								
								
								
								
								List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
								
								eformfield field  =  new eformfield();
								field.setField_label("extension_column_1");
								field.setField_type("text");
								eformhead2.add(field);
								
							    field  =  new eformfield();
								field.setField_label("extension_column_2");
								field.setField_type("text");
								eformhead2.add(field);	
								
							    field  =  new eformfield();
								field.setField_label("extension_column_3");
								field.setField_type("text");
								eformhead2.add(field);	

							    field  =  new eformfield();
								field.setField_label("extension_column_4");
								field.setField_type("text");
								eformhead2.add(field);	
								
							    field  =  new eformfield();
								field.setField_label("extension_column_5");
								field.setField_type("text");
								eformhead2.add(field);	
								
								this.modelData.put("eformhead2",eformhead2);
								this.modelData.put("Fullhead",heads2);
						
						}
						case "extension"->{
									condition conditions = ConditionDAO.getbyid(id_refer);
									List<eformfield>  eformhead =  EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
									eformhead.stream().forEach(row->{
										List<dataset_data> dataset_data  = Dataset_dataDAO.getall(row.getField_datareferid()) ;
							    		row.setDataset_data(dataset_data);
							    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf(row.getField_datareferid()));
							    		row.setDataset(dataset);
									});
									List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
									
									eformfield field  =  new eformfield();
									field.setField_label("data_idadd");
									field.setField_type("text");
									eformhead2.add(field);
									
								    field  =  new eformfield();
									field.setField_label("extension_id");
									field.setField_type("text");
									eformhead2.add(field);	
									
								    field  =  new eformfield();
									field.setField_label("id_refer");
									field.setField_type("text");
									eformhead2.add(field);	
									
									
									ShowTableHeaderDetails(eformhead);
									this.modelData.put("eformhead2",eformhead2);
									this.modelData.put("eformhead",eformhead);
						}
						case "program"->{
							process process = EdataDAO.getProcess(id_refer);
							condition conditions = ConditionDAO.getbyid(process.getId_condition());
							List<eformfield>  eformhead =  EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
							eformhead.stream().forEach(row->{
								List<dataset_data> dataset_data  = Dataset_dataDAO.getall(row.getField_datareferid()) ;
					    		row.setDataset_data(dataset_data);
					    		dataset dataset = DatasetDAO.getbyid( Integer.valueOf(row.getField_datareferid()));
					    		row.setDataset(dataset);
							});
							
							List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
						
							eformfield  field  =  new eformfield();
							
							field.setField_label("closing_status");
							field.setField_type("select_multiple");
							dataset dataset = DatasetDAO.getMain("Workflow_status");
							List<dataset_data> dataset_data  = Dataset_dataDAO.getall(dataset.getDataset_id()) ;
							
							String combinedTitles = dataset_data.stream()
								    .map(data -> data.getDataset_data_title()) // หรือใช้ Method Reference: dataset_data::getDataset_data_title
								    .collect(Collectors.joining(", "));
							field.setDisplay(combinedTitles);
							eformhead2.add(field);
							
							field  =  new eformfield();
							field.setField_label("current_step");
							field.setField_type("select_multiple");
							field.setDisplay("Create,Review,Approval,Proceed,Completion");
							eformhead2.add(field);
							
							field  =  new eformfield();
							field.setField_label("creator");
							field.setField_type("text");
							eformhead2.add(field);
							
							field  =  new eformfield();
							field.setField_label("sender");
							field.setField_type("text");
							eformhead2.add(field);
							
							
							field  =  new eformfield();
							field.setField_label("receiver");
							field.setField_type("text");
							eformhead2.add(field);
							
							
							field  =  new eformfield();
							field.setField_label("date");
							field.setField_type("date");
							eformhead2.add(field);
							
							
							field  =  new eformfield();
							field.setField_label("date_end");
							field.setField_type("date");
							eformhead2.add(field);
							
				
							
							field  =  new eformfield();
							field.setField_label("remarks");
							field.setField_type("text");
							eformhead2.add(field);
							
							
							
							ShowTableHeaderDetails(eformhead);
							this.modelData.put("eformhead",eformhead);
							this.modelData.put("eformhead2",eformhead2);
						}
				case "calendar"->{
					
					List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
					
					eformfield  field  =  new eformfield();
					field.setField_label("status");
					field.setField_type("select");
					field.setDisplay("0,1");
					eformhead2.add(field);
					
					
					    field  =  new eformfield();
						field.setField_label("alert");
						field.setField_type("select");
						field.setDisplay("0,1");
						eformhead2.add(field);
				
					    field  =  new eformfield();
						field.setField_label("date_event");
						field.setField_type("date");
						eformhead2.add(field);	
						
					    field  =  new eformfield();
						field.setField_label("by_name_id");
						field.setField_type("text");
						eformhead2.add(field);	
						
					    field  =  new eformfield();
						field.setField_label("index_id");
						field.setField_type("text");
						eformhead2.add(field);	
						
						
					this.modelData.put("eformhead2",eformhead2);
					
				}
				case "user"->{
					
List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
					
					eformfield  field  =  new eformfield();
					field.setField_label("user_name");
					field.setField_type("text");
	
					eformhead2.add(field);
					
					
					    field  =  new eformfield();
						field.setField_label("user_id");
						field.setField_type("text");
						eformhead2.add(field);
				
						
						
						
					this.modelData.put("eformhead2",eformhead2);
					
					
				}
				case "department"->{
					List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
					eformfield  field  =  new eformfield();
					field.setField_label("name");
					field.setField_type("text");
	
					eformhead2.add(field);
					    field  =  new eformfield();
						field.setField_label("id");
						field.setField_type("text");
						eformhead2.add(field);
				
						
						
						
					this.modelData.put("eformhead2",eformhead2);
				}
				case "customerInsight"->{
					
					List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
					eformfield  field  =  new eformfield();
					field.setField_label("closing_probability");
					field.setField_type("number");
					eformhead2.add(field);
					
					
					    field  =  new eformfield();
						field.setField_label("strategy");
						field.setField_type("text");
						eformhead2.add(field);
				
					    field  =  new eformfield();
						field.setField_label("customer_characteristics");
						field.setField_type("text");
					eformhead2.add(field);
						
				    field  =  new eformfield();
					field.setField_label("capital");
					field.setField_type("text");
				eformhead2.add(field);
					
					
			    field  =  new eformfield();
				field.setField_label("horsepower");
				field.setField_type("text");
			eformhead2.add(field);
				
		    field  =  new eformfield();
			field.setField_label("headcount");
			field.setField_type("text");
		eformhead2.add(field);
			

					this.modelData.put("eformhead2",eformhead2);
				}
				
				case "salesPredictionData"->{
					
					List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
					eformfield  field  =  new eformfield();
					field.setField_label("closing_probability");
					field.setField_type("number");
					eformhead2.add(field);
					
					
					    field  =  new eformfield();
						field.setField_label("category");
						field.setField_type("select_multiple");
						field.setDisplay("ที่ปรึกษา,อบรม,One Stop Service");
						eformhead2.add(field);
				
					    field  =  new eformfield();
						field.setField_label("course_name");
						field.setField_type("text");
					eformhead2.add(field);
						
				    field  =  new eformfield();
					field.setField_label("status");
					field.setField_type("select_multiple");
					field.setDisplay("closed_lost,closed_won");
					eformhead2.add(field);
					
					
			    field  =  new eformfield();
				field.setField_label("total_price");
				field.setField_type("number");
			eformhead2.add(field);
				

			

					this.modelData.put("eformhead2",eformhead2);
				}
		case "helpDesk"->{
					
					List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
					
					eformfield  field  =  new eformfield();
					field.setField_label("request_topic");
					field.setField_type("select_multiple");
					field.setDisplay("Change Status,Update,Delete,Insert");
					eformhead2.add(field);
					
					
					    field  =  new eformfield();
						field.setField_label("status");
						field.setField_type("select");
						field.setDisplay("OPEN,COMPLETED");
						eformhead2.add(field);
				
					    field  =  new eformfield();
						field.setField_label("created_at");
						field.setField_type("date");
						eformhead2.add(field);	
						
						
					    field  =  new eformfield();
						field.setField_label("update_at");
						field.setField_type("date");
						eformhead2.add(field);	
						
					    field  =  new eformfield();
						field.setField_label("created_id");
						field.setField_type("text");
						eformhead2.add(field);	
						
						
						
						
					    field  =  new eformfield();
						field.setField_label("update_id");
						field.setField_type("text");
						eformhead2.add(field);	
						
						
					this.modelData.put("eformhead2",eformhead2);
					
				}
		case "documentEffect"->{
			
			List<eformfield>  eformhead2 = new ArrayList<eformfield>(); 
			
			eformfield  field  =  new eformfield();
			field.setField_label("data_type");
			field.setField_type("select");
			field.setDisplay("product,register");
			eformhead2.add(field);
			
			
			    field  =  new eformfield();
				field.setField_label("type");
				field.setField_type("select_multiple");
				field.setDisplay("effect.INSERT,effect.UPDATE,effect.PENDING_DELETE,effect.CHANGE_STATUS");
				eformhead2.add(field);
		
			    field  =  new eformfield();
				field.setField_label("created_at");
				field.setField_type("date");
				eformhead2.add(field);	
				
			    field  =  new eformfield();
				field.setField_label("id_add");
				field.setField_type("text");
				eformhead2.add(field);	
				

			this.modelData.put("eformhead2",eformhead2);
			
		}
				
				
				
			}
				return this;
	}
	
	public String requireDataJsondrilldown() {        
	    
	    String cheeseJsonPayload = LoadApp.getPlatformReport().getEdata_from();
	    String temp = LoadApp.getPlatformReport().getApp_tempId();
	    String idRefer = "";
	    String chainId = "";

	    // 1. แกะ idRefer และ chainId ออกมา
	    if (temp != null && temp.contains("_")) {
	        String[] parts = temp.split("_", 2); 
	        idRefer = parts[0]; 
	        chainId = parts[1]; 
	    } else if (temp != null) {
	        idRefer = temp; 
	    }

	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode rootNode = mapper.readTree(cheeseJsonPayload);
	        JsonNode filterChainArray = rootNode.get("FilterChain");
	        
	        if (filterChainArray == null || !filterChainArray.isArray()) return "[]";

	        // 🌟 ดึงข้อมูลตารางแม่ (Index 0) ไว้เช็คเงื่อนไขการเชื่อมต่อ
	        JsonNode mainRowNode = filterChainArray.get(0);
	        String mainGroup = mainRowNode.has("groups") ? mainRowNode.get("groups").asText() : "";

	        // ==========================================
	        // 2. 🔍 ค้นหา "ตารางลูก" ที่เรากด Drill-down เข้ามา
	        // ==========================================
	        JsonNode targetSubRow = null;
	        for (JsonNode subRow : filterChainArray) {
	        	
	        	if (subRow.has("extension_id") && subRow.get("extension_id").asText().equals(chainId)) {
	                targetSubRow = subRow; break; 
	            }
	        	else if (subRow.has("tables") && subRow.get("tables").asText().equals(chainId)) {
	                targetSubRow = subRow; break;
	            }  
	        }

	        if (targetSubRow == null) {
	            System.out.println("❌ ไม่เจอข้อมูล Chain ID: " + chainId);
	            return "[]"; 
	        }

	        String subGroup = targetSubRow.get("groups").asText();
	        int targetTableId = targetSubRow.has("tables") ? targetSubRow.get("tables").asInt() : 0;
	        String typeName = targetSubRow.has("type") ? targetSubRow.get("type").asText() : "Data";

	        // ==========================================
	        // 3. 🌟 ดึง Headlist ของ "ตารางลูก" มาเตรียมไว้
	        // ==========================================
	        List<?> heads = null;
	        if ("Profile".equalsIgnoreCase(subGroup) || "Product".equalsIgnoreCase(subGroup)) {
	            process process = EdataDAO.getProcess(targetTableId);
	            if (process != null) {
	                heads = EdataDAO.getRegisterkeyByListview(process.getListview2());
	            }
	        } 
	        else if ("extension".equalsIgnoreCase(subGroup) || "program".equalsIgnoreCase(subGroup)) {
	            condition conditions = ConditionDAO.getbyid(targetTableId);
	            if (conditions != null) {
	                heads = EformfieldDAO.getall(conditions.getCondition_id(), conditions.getCondition_rv());
	            }
	        }

	        // ==========================================
	        // 4. ⚡ สร้าง SQL สำหรับ Drill-down
	        // ==========================================
	        // ดึงชื่อตาราง และดักเผื่อว่า resolveTableName ไม่รู้จัก calendar
	        String tableName = resolveTableName(subGroup); 
	        if ("calendar".equalsIgnoreCase(subGroup)) tableName = "calendar"; 

	        StringBuilder sql = new StringBuilder();
	        
	        // 🌟 ท่าไม้ตาย: ถ้าเป็น calendar ให้จำลองตารางเพื่อต่อวันที่ date_event รอไว้เลย
	        if ("calendar".equalsIgnoreCase(subGroup)) {
	            sql.append("SELECT SUB_TBL.* FROM ( \n");
	            sql.append("  SELECT *, CONCAT(year, '-', LPAD(month, 2, '0'), '-', LPAD(day, 2, '0')) AS date_event \n");
	            sql.append("  FROM ").append(tableName).append(" \n");
	            sql.append(") SUB_TBL WHERE 1=1 \n");
	        } else {
	            sql.append("SELECT SUB_TBL.* FROM ").append(tableName).append(" SUB_TBL WHERE 1=1 \n");
	        }
	     // ==========================================
	        // --- 🌟 ประมวลผล JSON และเชื่อมตาราง (แบบไม่พึ่ง if-else) ---
	        // ==========================================
	        String subJsonOutPut = targetSubRow.has("json_out_put") ? targetSubRow.get("json_out_put").asText() : "";
	        
	        if (!subJsonOutPut.isEmpty()) {
	            
	            // 1. กรองเอาแถวที่เป็น "End" ออกไปก่อน (โค้ดเดิมของคุณ ทำได้ดีมากครับ)
	            try {
	                ObjectMapper tempMapper = new ObjectMapper();
	                JsonNode arrNode = tempMapper.readTree(subJsonOutPut);
	                ArrayNode cleanArrNode = tempMapper.createArrayNode();
	                for (JsonNode item : arrNode) {
	                    String cond = item.has("condition") ? item.get("condition").asText() : "";
	                    if (!"End".equalsIgnoreCase(cond)) {
	                        cleanArrNode.add(item); 
	                    }
	                }
	                subJsonOutPut = tempMapper.writeValueAsString(cleanArrNode);
	            } catch (Exception e) {}

	            // 2. 🚀 ดึง extension_id และ subTableId เพื่อส่งให้ parse (พารามิเตอร์ 4 ตัว)
	            String subTableId = targetSubRow.has("tables") ? targetSubRow.get("tables").asText() : "0";
	            String TableId = targetSubRow.has("extension_id") ? targetSubRow.get("extension_id").asText() : subTableId;
	            
	            Map<String, String> parsedSql = parseJsonOutputToSql(subJsonOutPut, "SUB_TBL", TableId);
	            
	            // 3. 🚀 ดึงคอลัมน์เชื่อมต่อ (เช่น SUB_TBL.data_idadd) มาจาก JSON
	            String groupKeyCol = parsedSql.getOrDefault("GROUP_KEY", "SUB_TBL.id_refer");
	            
	            // 4. 🚀 ประกอบร่างเชื่อมตาราง จบในบรรทัดเดียว! (เช่น SUB_TBL.data_idadd = '107')
	            sql.append(" AND ").append(groupKeyCol).append(" = '").append(idRefer).append("' \n");
	            
	            // 5. 🚀 เติมเงื่อนไข Filter อื่นๆ (เช่น extension_id = 5) ตามมา
	            if (parsedSql.containsKey("WHERE") && !parsedSql.get("WHERE").trim().isEmpty()) {
	                sql.append(" ").append(parsedSql.get("WHERE")).append(" \n");
	            }
	            
	        } else {
	            // กรณีไม่มี JSON หน้าเว็บส่งมา (Fallback กันเหนียว)
	            sql.append(" AND SUB_TBL.id_refer = '").append(idRefer).append("' \n");
	        }


	        System.out.println("Drill-down SQL: \n" + sql.toString());
	        List<Map<String, Object>> dbRows = EdataDAO.executeDynamicQuery(sql.toString());

	        // ==========================================
	        // 5. 🛠️ เริ่มกระบวนการปั้น JSON (คัดเฉพาะคอลัมน์ Heads)
	        // ==========================================
	        List<String> displayHeaders = new ArrayList<>();
	        List<String> lookupKeys = new ArrayList<>();
	        String idKey = "data_id"; 

	        // 🌟 ตั้งค่าหัวตารางให้ Calendar โดยเฉพาะ (เพราะมันไม่ได้ดึงจากฐานข้อมูลปกติ)
	        if ("calendar".equalsIgnoreCase(subGroup)) {
	            idKey = "index_id"; 
	            displayHeaders.addAll(Arrays.asList("กิจกรรม (Event)", "วันที่ (Date Event)","รหัสอ้างอิง (Index ID)", "รหัสผู้ใช้ (By Name ID)", "สถานะ (Status)", "แจ้งเตือน (Alert)"));
	            lookupKeys.addAll(Arrays.asList("event", "date_event","index_id", "by_name_id", "status", "alert"));
	        } 
	        // 🌟 ตั้งค่าหัวตารางให้ Calendar โดยเฉพาะ (เพราะมันไม่ได้ดึงจากฐานข้อมูลปกติ)
	        else if ("documentEffect".equalsIgnoreCase(subGroup)) {
	            displayHeaders.addAll(Arrays.asList("ประเภทการดำเนินการ", "รายการ","ประเภททะเบียน", "วันที่ดำเนินการ", "ผู้ดำเนินการ"));
	            lookupKeys.addAll(Arrays.asList("type", "title","data_type", "created_at", "id_add"));
	        } 
	        
	        // 🌟 ตั้งค่าหัวตารางให้ Calendar โดยเฉพาะ (เพราะมันไม่ได้ดึงจากฐานข้อมูลปกติ)
	        else if ("helpDesk".equalsIgnoreCase(subGroup)) {
	            displayHeaders.addAll(Arrays.asList("ประเภทคำขอ", "คำขอ","รหัสอ้างอิง (Index ID)", "รหัสผู้ใช้ (By Name ID)", "ผู้สร้าง", "ผู้รับเรื่อง","บันทึกเพิ่มเติ่มผู้รับเรื่อง","วันที่ขอ","วันที่ดำเนินการ"));
	            lookupKeys.addAll(Arrays.asList("request_topic", "description","ref_id", "by_name_id", "created_id", "update_id","admin_note","created_at","update_at"));
	        } 
	        
	        
	        else if (heads != null && !heads.isEmpty()) {
	            if (heads.get(0) instanceof registerkey) {
	                idKey = "id"; 
	                for (registerkey k : (List<registerkey>) heads) {
	                    displayHeaders.add(translate(k.getTitle())); 
	                    lookupKeys.add(k.getMainkey());
	                }
	            } else if (heads.get(0) instanceof eformfield) {
	                for (eformfield f : (List<eformfield>) heads) {
	                    displayHeaders.add(f.getField_label());
	                    lookupKeys.add("data_value" + f.getField_postion());
	                }
	            }
	        } else {
	            displayHeaders.add("ID"); lookupKeys.add("id");
	        }

	        // --- เตรียมก้อน JSON Section ---
	        Map<String, Object> jsonSection = new LinkedHashMap<>();
	        jsonSection.put("headers", displayHeaders);
	        jsonSection.put("tables", typeName);
	        jsonSection.put("id_refer", targetTableId);

	        List<Map<String, Object>> cleanData = new ArrayList<>();

	        // --- วนลูปจับคู่ Data กับ Header ---
	        for (Map<String, Object> dbRow : dbRows) {
	            Map<String, Object> rowMap = new LinkedHashMap<>();
	            // ดึง ID
	            rowMap.put("id", dbRow.getOrDefault(idKey, dbRow.get("id")));

	            for (int k = 0; k < displayHeaders.size(); k++) {
	                String dbColumnName = lookupKeys.get(k);
	                rowMap.put(displayHeaders.get(k), dbRow.get(dbColumnName));
	            }
	            cleanData.add(rowMap);
	        }

	        jsonSection.put("total_json", dbRows.size());
	        jsonSection.put("total_data", cleanData.size());
	        jsonSection.put("data", cleanData);
	        
	        List<Map<String, Object>> resultList = new ArrayList<>();
	        resultList.add(jsonSection);

	        return mapper.writeValueAsString(resultList);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "[]";
	    }
	}
	
	public String requireDataJson() {        
        
        if(LoadApp.getPlatformReport().getEdata_from() == null || LoadApp.getPlatformReport().getEdata_from().trim().isEmpty()) { 
            return "[]"; 
        }
        
        String cheeseJsonStr = LoadApp.getPlatformReport().getEdata_from();
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            // 1. แกะ JSON เอา mainRow
            JsonNode rootNode = mapper.readTree(cheeseJsonStr);
            JsonNode filterChainArray = rootNode.get("FilterChain");
            if (filterChainArray == null || !filterChainArray.isArray() || filterChainArray.size() == 0) {
                return "[]";
            }
            
            JsonNode mainRowNode = filterChainArray.get(0);
            FilterChain mainRow = mapper.treeToValue(mainRowNode, FilterChain.class);
            JsonNode prepareReport = rootNode.get("PrepareReport");
            boolean isTrendMode = prepareReport != null && prepareReport.has("report_mode") && "trend".equalsIgnoreCase(prepareReport.get("report_mode").asText());
            // ==========================================
            // 🌟 ดึง Headlist จาก Database ตามกลุ่ม (groups)
            // ==========================================
            String mainGroup = mainRow.getGroups();
            int tableId = mainRow.getTables(); 
            List<?> heads = null;
  
            if ("Profile".equalsIgnoreCase(mainGroup) || "Product".equalsIgnoreCase(mainGroup)) {
                process process = EdataDAO.getProcess(tableId);
                if (process != null) {
                	List<registerkey>  head= EdataDAO.getRegisterkeyByListview(process.getListview2());

                	registerkey field  =  new registerkey();
					field.setTitle("extension_column_1");
					head.add(field);
			      	field  =  new registerkey();
					field.setTitle("extension_column_2");
					head.add(field);			
			     	field  =  new registerkey();
					field.setTitle("extension_column_3");
					head.add(field);
			     	field  =  new registerkey();
					field.setTitle("extension_column_4");
					head.add(field);
			     	field  =  new registerkey();
					field.setTitle("extension_column_5");
					head.add(field);
					
					heads = head;
                    
                }
            } 
            else if ("extension".equalsIgnoreCase(mainGroup) || "program".equalsIgnoreCase(mainGroup)) {
            	
            	 process process = EdataDAO.getProcess(tableId);
                condition conditions = ConditionDAO.getbyid(process.getId_condition());
                if (conditions != null) {
                	 heads = EformfieldDAO.getall(conditions.getCondition_id(), conditions.getCondition_rv());
                	   	List<eformfield>  head = EformfieldDAO.getall(conditions.getCondition_id(), conditions.getCondition_rv());
                        
                        
                        eformfield field  =  new eformfield();
    					field.setField_label("current_step");
    					field.setField_type("select_multiple");
    					field.setDisplay("Create,Review,Approval,Proceed,Completion");
    					head.add(field);
    					
    					field  =  new eformfield();
    					field.setField_label("creator");
    					field.setField_type("text");
    					head.add(field);
    					
    					field  =  new eformfield();
    					field.setField_label("sender");
    					field.setField_type("text");
    					head.add(field);
    					
    					
    					field  =  new eformfield();
    					field.setField_label("receiver");
    					field.setField_type("text");
    					head.add(field);
    					
    					
    					field  =  new eformfield();
    					field.setField_label("date");
    					field.setField_type("date");
    					head.add(field);
    					
    					field  =  new eformfield();
    					field.setField_label("date_end");
    					field.setField_type("date_end");
    					head.add(field);
    					
    					
    					field  =  new eformfield();
    					field.setField_label("closing_status");
    					field.setField_type("text");
    					head.add(field);
    					
    					
    					field  =  new eformfield();
    					field.setField_label("remarks");
    					field.setField_type("text");
    					head.add(field);
    					
    					field  =  new eformfield();
    					field.setField_label("process_id");
    					field.setField_type("text");
    					head.add(field);
    					heads = head;
                 
                }
            }
       
         
            
            
            // ==========================================
            // 2. ⚡ สร้าง SQL และไปดึงข้อมูลดิบจาก DAO
            // ==========================================
            String generatedSql = generateDynamicSQL(cheeseJsonStr);
            System.out.println("generatedSql \n" + generatedSql);
            
            List<Map<String, Object>> dbRows = EdataDAO.executeDynamicQuery(generatedSql);
  
            // ==========================================
            // 3. เริ่มกระบวนการปั้น JSON
            // ==========================================
            List<String> displayHeaders = new ArrayList<>();
            List<String> lookupKeys = new ArrayList<>();
            String idKey = "data_id"; 
  
         // เริ่มลูปที่ i = 1 (ข้ามตารางแม่ที่ index 0)
            for (int i = 1; i < filterChainArray.size(); i++) {
                
                // ดึง Node ของตารางลูกแต่ละตัวออกมา
                JsonNode subNode = filterChainArray.get(i);
                
                // ดึงค่า display_columns ออกมาเป็น String (ถ้าไม่มีจะคืนค่าว่าง "")
                String displayColsStr = subNode.path("display_columns").asText("");

                if (!displayColsStr.trim().isEmpty()) {
                    // หั่นคอลัมน์ที่ User เลือกไว้ (เช่น "customerCode,register_name")
                    String[] cols = displayColsStr.split(",");
                    
                    for (String col : cols) {
                        String cleanCol = col.trim();
                        if (!cleanCol.isEmpty()) {
                            
                            // 🚀 1. เพิ่มชื่อสำหรับแสดงบนหัวตาราง (UI)
                            // สามารถใส่ชื่อตารางนำหน้าได้เพื่อให้ User ไม่งง เช่น "Join: " + cleanCol
                            displayHeaders.add(cleanCol); 

                            // 🚀 2. เพิ่มคีย์สำหรับงัดข้อมูลจาก ResultSet
                            // คีย์นี้ต้องตรงกับที่เรา AS ไว้ในคำสั่ง SQL (ซึ่งเราตั้งไว้ตรงกันเป๊ะ)
                            lookupKeys.add(cleanCol); 
                        }
                    }
                }
            }
            
            
            // 🌟 จัดการ Header ของตารางแม่
            if (heads != null && !heads.isEmpty()) {
                if (heads.get(0) instanceof registerkey) {
                    idKey = "id"; 
                    for (registerkey k : (List<registerkey>) heads) {
                        String labelName = k.getTitle();
                    	 List<String> specialFields = Arrays.asList("extension_column_1", "extension_column_2", "extension_column_3", "extension_column_4", "extension_column_5");
                    	
                        displayHeaders.add(translate(k.getTitle())); 
                        if (labelName != null && specialFields.contains(labelName.toLowerCase())) {
                            lookupKeys.add(labelName.toLowerCase()); // เช่น ใส่คำว่า "date" หรือ "creator" ลงไปค้นหาตรงๆ
                        } 
                        else {
                            lookupKeys.add(k.getMainkey());
                        }
                        
                    }
                } else if (heads.get(0) instanceof eformfield) {
                	// 🌟 สร้าง List ของชื่อฟิลด์พิเศษ (ทำนอกลูปเพื่อประสิทธิภาพ)
                    List<String> specialFields = Arrays.asList("creator", "sender", "receiver", "date", "date_end", "current_step", "closing_status","remarks", "process_id");

                    for (eformfield f : (List<eformfield>) heads) {
                        String labelName = f.getField_label();
                        displayHeaders.add(labelName);
                        
                        // 🚀 ดักจับ: ถ้าเป็นฟิลด์พิเศษ ให้ใช้ชื่อตรงๆ 
                        if (labelName != null && specialFields.contains(labelName.toLowerCase())) {
                            lookupKeys.add(labelName.toLowerCase()); // เช่น ใส่คำว่า "date" หรือ "creator" ลงไปค้นหาตรงๆ
                        } 
                        // 🚀 ถ้าเป็นฟิลด์ปกติของ eform ให้ใช้ data_value นำหน้าเหมือนเดิม
                        else {
                            lookupKeys.add("data_value" + f.getField_postion());
                        }
                    }
                }
            } 
            // 🚀 จัดการ Header กรณีตารางแม่เป็น User 
            else if ("user".equalsIgnoreCase(mainGroup)) {
                idKey = "user_id"; 
                displayHeaders.add("User Name"); 
                lookupKeys.add("user_name");     
            }
  
         // 🌟 1. สร้าง Map ไว้เก็บ Config ว่าคอลัมน์ไหนต้องทำเป็น Link ไปตารางไหน
            Map<String, String> summaryLinkConfig = new HashMap<>();

            // =========================================================================
            // 🚀 2. ดึงการตั้งค่ากราฟจาก PrepareReport ตรงๆ เลย!
            // =========================================================================
            String summaryFunc = prepareReport != null ? prepareReport.path("summary_func").asText("") : "";
            String summaryName = prepareReport != null ? prepareReport.path("summary_name").asText("Summary_Col") : "Summary_Col";
            String trendDateCol = prepareReport != null ? prepareReport.path("trend_date_column").asText("") : "";
            String groupByCol = prepareReport != null ? prepareReport.path("group_by_column").asText("") : "";

            // 🚀 [เพิ่มใหม่] หา ID ของตารางลูก (Sub-table) เพื่อส่งเป้าหมายให้ Drill-down
            String drillTargetTableId = String.valueOf(tableId); // Default เป็นตารางแม่ไว้ก่อน
            if (filterChainArray.size() > 1) {
                JsonNode firstSubRow = filterChainArray.get(1); // เอาตารางลูกอันแรกมาใช้
                drillTargetTableId = firstSubRow.has("tables") ? firstSubRow.get("tables").asText() : drillTargetTableId;
                
                // ถ้าเป็น extension ต้องใช้ extension_id
                if (firstSubRow.has("groups") && "extension".equalsIgnoreCase(firstSubRow.get("groups").asText())) {
                    if (firstSubRow.has("extension_id") && !firstSubRow.get("extension_id").asText().isEmpty()) {
                        drillTargetTableId = firstSubRow.get("extension_id").asText();
                    }
                }
            }

            // 🚀 ถ้ามีฟังก์ชันคำนวณ (SUM, COUNT) แปลว่าเป็นโหมดสรุปผล! สร้างหัวตารางทันที
            if (!summaryFunc.isEmpty()) {
                
                // เปิดคอลัมน์ "วันที่"
                if (!trendDateCol.isEmpty() && !lookupKeys.contains("Trend_Date")) {
                    displayHeaders.add("วันที่ (Trend Axis)");
                    lookupKeys.add("Trend_Date");
                }
                
                // เปิดคอลัมน์ "หมวดหมู่"
                if (!groupByCol.isEmpty() && !lookupKeys.contains(groupByCol)) {
                    displayHeaders.add("หมวดหมู่");
                    lookupKeys.add(groupByCol);
                }
                
                // เปิดคอลัมน์ "ตัวเลขผลรวม"
                if (!lookupKeys.contains(summaryName)) {
                    displayHeaders.add(summaryName);
                    lookupKeys.add(summaryName);
                    
                    // 🚀 [หมัดฮุก!] ผูก Link ไปที่ตารางลูก! (drillTargetTableId)
                    summaryLinkConfig.put(summaryName, drillTargetTableId);
                }
            }

            // =========================================================================
            // 🌟 3. ลูปเช็คเฉพาะโหมด HAVING แบบเก่า (เผื่อมีคอลัมน์นับจำนวนเดิมค้างอยู่)
            // =========================================================================
            for (int i = 1; i < filterChainArray.size(); i++) {
                JsonNode subRow = filterChainArray.get(i);
                String subJsonOutPut = subRow.has("json_out_put") ? subRow.get("json_out_put").asText() : "";
                String targetTableId = subRow.has("tables") ? subRow.get("tables").asText() : ""; 
                
                if (subRow.has("groups") && "extension".equalsIgnoreCase(subRow.get("groups").asText())) {
                    if (subRow.has("extension_id") && !subRow.get("extension_id").asText().isEmpty()) {
                        targetTableId = subRow.get("extension_id").asText();
                    }
                }
                
                Map<String, String> parsedCheck = parseJsonOutputToSql(subJsonOutPut, "CHECK","");
                if (parsedCheck != null && parsedCheck.containsKey("HAVING") && !parsedCheck.get("HAVING").isEmpty()) {
                    String countColumnName = subRow.has("type") ? subRow.get("type").asText() : "Count_Col";
                    if (!lookupKeys.contains(countColumnName)) {
                        displayHeaders.add(countColumnName);
                        lookupKeys.add(countColumnName);
                        summaryLinkConfig.put(countColumnName, targetTableId); 
                    }
                }
            }

            // จัดการ Map โครงสร้าง JSON
            Map<String, Object> jsonSection = new LinkedHashMap<>();
            jsonSection.put("headers", displayHeaders);

            List<Map<String, Object>> cleanData = new ArrayList<>();

            // วนลูปจับคู่ Data กับ Header
            for (Map<String, Object> dbRow : dbRows) {
                Map<String, Object> rowMap = new LinkedHashMap<>();
                
                // ดึง ID แม่
                String currentMainId = String.valueOf(dbRow.get(idKey)); 
                rowMap.put("id", currentMainId);
                rowMap.put("id_refer", dbRow.getOrDefault("id_refer", "")); 

                // 🚀 (เราไม่ต้อง Loop หา trendDateCol ด้านในอีกแล้ว เพราะดึงไว้แล้วด้านบน!) 

                for (int k = 0; k < displayHeaders.size(); k++) {
                    String headerName = displayHeaders.get(k);
                    String dbColumnName = lookupKeys.get(k);
                    Object rawValue = dbRow.get(dbColumnName);
                    
                 // 🌟 1. ดึงค่าวันที่ และ หมวดหมู่ (ถ้ามี)
                    String trendDateValue = dbRow.containsKey("Trend_Date") && dbRow.get("Trend_Date") != null 
                                            ? dbRow.get("Trend_Date").toString() : "";
                                            
                    String groupByValue = !groupByCol.isEmpty() && dbRow.containsKey(groupByCol) && dbRow.get(groupByCol) != null 
                                          ? dbRow.get(groupByCol).toString() : "";

                    // 🌟 2. ประกอบร่างแบบ Dynamic! (แยกเป็น 2 ตัวแปร)
                    String dataFilterValue = trendDateValue.isEmpty() ? "" : trendDateValue + "_" + trendDateCol;
                    String dataCategoryValue = groupByValue.isEmpty() ? "" : groupByValue + "_" + groupByCol;
                    
                    if (summaryLinkConfig.containsKey(headerName) && rawValue != null && !rawValue.toString().equals("0")) {
                        String targetTable = summaryLinkConfig.get(headerName); 

                        // 🌟 3. ฝังลงไปทั้ง data-filter และ data-category
                        String clickableLink = "<a href=\"javascript:void(0);\" " +
                                "data-filter=\"" + dataFilterValue + "\" " +
                                "data-category=\"" + dataCategoryValue + "\" " +
                                "onclick=\"openDrillDownData('" + targetTable + "', '" + currentMainId + "', this)\" " +
                                "style=\"color: #0078d7; text-decoration: underline; cursor: pointer !important; font-weight: bold;\">" + 
                                rawValue + "</a>";
                        
                        rowMap.put(headerName, clickableLink); // ใส่แบบปุ่มกดได้
                        
                    } else {
                        rowMap.put(headerName, rawValue); // ใส่แบบตัวเลข/ตัวหนังสือธรรมดา
                    }
                }
                cleanData.add(rowMap);
            }
            
            jsonSection.put("tables", mainRow.getType());
            jsonSection.put("id_refer", mainRow.getTables());
            jsonSection.put("id_config", mainRow.getId_refer());

            jsonSection.put("total_json", dbRows.size());
            jsonSection.put("total_data", cleanData.size());
            jsonSection.put("data", cleanData);
            
            List<Map<String, Object>> resultList = new ArrayList<>();
            resultList.add(jsonSection);
  
            // 4. แปลงร่างสุดท้ายเป็น String เพื่อส่งกลับ
            return mapper.writeValueAsString(resultList);
  
        } catch (Exception e) {
            e.printStackTrace();
            return "[]"; 
        }
}
		

	public Report_Component requireQueryChain() {		
		loadReportConfig();
		loadAllQueryChain();
		return this;
	}
	
	
	public void deleteFilterChain() {
		doDeleteFilterChain();
	}


	public void SyncBackSegmentation() {
		doSyncBackSegmentation();
	}
	public void unSyncBackSegmentation() {
		doUnSyncBackSegmentation();
	}
	public void copySyncBackSegmentation() {
		doCopySyncBackSegmentation();
	}


	public void load(){
		modelDatas.putAll(modelData);
		modelDatas.putAll(modelData2);
	}
	
	public void save(){
		
	}
	public void saveFilterCriteria() {
		doSaveFilterCriteria();
	}
	public void saveFilterChain() {
		doSaveFilterChain();
		
	}
	
	public void saveQuickQuery() {
		dosaveQuickQuery();
		
	}
	
	public void ChangeWorkflowStatusClose() {
		doChangeWorkflowStatusClose();
		
		
	}

	

	private void loadReportData(int type) {
		
		switch(type) {
			case 1 ->syncData();
		}
	}

	private void loadReportConfig() {
		
		if(this.LoadApp.getPlatformReport().getApp_item_id() > 0) {
			config = ReportDAO.getPrepareReportById(this.LoadApp.getPlatformReport().getApp_item_id());
			this.modelData.put("Config",config);
		}else {
			if(this.LoadApp.getPlatformReport().getFrom() == 0) {return;}
			config = ReportDAO.getPrepare(this.LoadApp.getPlatformReport());
			this.modelData.put("Config",config);
		}
	}
	
	
	private void syncData() {
		
	
		if(this.LoadApp.getPlatformReport() == null ) { return; }
		System.out.println(" syncData1 ");
		if(this.LoadApp.getProcess_refer() == null ) { return; }
		System.out.println(" syncData2 ");
		
	    if(config == null ) { return; }
		System.out.println(" syncData3 ");
	    if(config.getSource_type() == null ) { return; }
		System.out.println(" syncData4 ");
	    
		this.LoadApp.setProcess(this.LoadApp.getProcess_refer());
		LoadApp.getProcess().setShow_limit(200);
		
		switch(config.getSource_type()) {
			case "Profile" ->Productions.getAllRegister(this.LoadApp,this.modelData);
		}
		
	}

	
	private void loadAllQueryChain() {
		
		queryChains = ReportDAO.getPrepareQueryChain(config);
		AtomicBoolean callAppLoaded = new AtomicBoolean(true);

		int loaddata = this.calldata;
		
		queryChains.forEach(row->{
			

			FilterCriteria filtercriteria =  ReportDAO.getFilter_criteria(row.getId());
			if(filtercriteria != null) {
				row.setQuery_text(filtercriteria.getJson_out_put());
				row.setReadableText(convertJsonToReadableText(filtercriteria.getJson_out_put()));
				
				 List<FilterDisplayItem> DisplayItem =  getFilterDisplayList(filtercriteria.getJson_out_put());
			
				 ReplaceDisplayList(DisplayItem,row.getId());
				 
				 row.setFilterDisplayItem(DisplayItem);

				
			}

			if(loaddata == 1) {
					CallApp(row.getGroups(), row.getTables(),row.getId(),callAppLoaded.get());
		            callAppLoaded.set(false);
			}
				 	
	
		});
		
		filterFirstChainData();
		this.modelData.put("queryChains",queryChains);
	}







	private void loadQueryChain() {
		if(this.LoadApp.getPlatformReport().getApp_id_refer() > 0) {
			
			queryChain = ReportDAO.getPrepareQueryChainById(this.LoadApp.getPlatformReport().getApp_id_refer());
			CallApp(queryChain.getGroups(),queryChain.getTables(),queryChain.getId(),false);
			
			this.modelData.put("from",queryChain.getId_refer());
			this.modelData.put("ConfigProcess_id",queryChain.getTables());
		}	
		
	}
	
	
	private void CallApp(String groups, int Process_id, int id, boolean first) {

		switch(groups) {
			case "Profile"->CallAppProfile(Process_id,id,first);
			case "extension"->CallAppExtension(Process_id,id,first);
		}
	}

	private void CallAppProfile(int Process_id, int id, boolean first) {
		
		
			System.out.println("CallAppProfile  Process_id "+Process_id);
		
			Platform platform2 = new Platform();
			platform2.setApp_processes_id(Process_id);
			platform2.setReportconfig(config);
			
			if(config != null && config.getSync_back_not_unique_active() == 0) {
				
				//ถ้าเคยถูกใช้เเล้ว ห้ามใช้ซ้ำจนกว่าจะ inaction
				
				List<Segmentation> SegmentationList = EdataDAO.getallSegmentation();
				List<Integer> notWithinId = SegmentationList.stream()
					    .map(Segmentation::getSegmentation_member_id)      // 1. ดึง String เช่น "1,2,3"
					    .filter(Objects::nonNull)                        // 2. ป้องกัน NullPointerException
					    .flatMap(s -> Arrays.stream(s.split(",")))       // 3. แยก String ด้วย , แล้วแตกออกเป็น Stream ย่อย
					    .map(String::trim)                               // 4. ตัดช่องว่าง (ถ้ามี)
					    .filter(s -> !s.isEmpty())                       // 5. กรองค่าว่างออก
					    .map(Integer::valueOf)                           // 6. แปลง String เป็น Integer
					    .distinct()                                      // 7. เอาตัวซ้ำออก (Performance Win!)
					    .collect(Collectors.toList());
				platform2.setNotWithinId(notWithinId);
	    	}
			
			
		
			if(calldata == 1) {
				LoadApp Loadapp = new LoadApp();
				
				PlatformServices.initiate(Loadapp).startAppIndex(platform2)
				.renderIndexLayout().requireAllIndex().load();
				
				Map<String, Object> data2 = PlatformServices.getModelData();
				
				if(data2.get("exampleData") != null && data2.get("Fullhead") != null) {
					
					if(data2.get("countData") != null) {
						countData = (int) data2.get("countData");
					}
					
					
					
					System.out.println("CallAppProfile calldata");
					
					List<registers> exampleData  =  (List<registers>) data2.get("exampleData");
					
					System.out.println("CallAppProfile exampleData "+exampleData.size());
					
					
					if(first) {
						allIds = exampleData.stream()
							    .map(registers::getId) // สมมติว่า getId คืนค่าเป็น Integer
							    .distinct()            // ตัดตัวซ้ำออก (สำคัญมาก ช่วยลดของ)
							    .collect(Collectors.toList());
						
	
						
					}
					
					
					if(config.getQuerychains_enabled() == 1) {  
						
						System.out.println("CallAppProfile enabled ");
						
						doFilter(exampleData,id,data2.get("Fullhead"),1);
					}
					
					data2.remove("exampleData");
					
				}			
				
			
				this.modelData.putAll(data2);
				
			}else {
				System.out.println("CallAppProfile not calldata");
				
				LoadApp Loadapp = new LoadApp();
				PlatformServices.initiate(Loadapp).startAppIndex(platform2)
				.renderIndexLayout()
				.ShowTableHeaderDetails().load();
				Map<String, Object> data2 = PlatformServices.getModelData();
				this.modelData.putAll(data2);
			
			}
		
	}





	private void loadAlltableBySyncData() {
		// TODO Auto-generated method stub
		
	}

	private void loadFilterCriteria() {
		FilterCriteria filtercriteria =  ReportDAO.getFilter_criteria(this.LoadApp.getPlatformReport().getApp_id_refer());
		
		if(filtercriteria != null) {
			this.modelData.put("filtercriteria",filtercriteria);
		}
		
	}

	private void prepareTable() {
		// TODO Auto-generated method stub
		
	}

	private void doSaveFilterCriteria() {
		ReportDAO.saveFilterCriteria(this.LoadApp.getPlatformReport().getFilterCriteria());
		
	}

	private void doSaveFilterChain() {
		
		System.out.println( " doSaveFilterChain ");
		ReportDAO.saveQueryChain(this.LoadApp.getPlatformReport().getFilterChain());
	}
	
	

	
	
	
	
	
	
	private void doFilter(Object exampleData, int id, Object heads, int type) {

		Optional<FilterChain> query = queryChains.stream().filter(row -> row.getId() == id).findFirst();

		String jsonQuery = null;
		FilterChain data = new FilterChain();

		if (query.isPresent()) {
			data = query.get();
			jsonQuery = data.getQuery_text();
		}

		// 🎯 [แก้ไขตรงนี้] ดักจับค่า null, ค่าว่าง, หรือเครื่องหมาย "-" 
		// หรือถ้าจะให้ชัวร์ ดักจับว่ามันไม่ได้เริ่มด้วย '[' (เพราะเราคาดหวัง JSON Array)
		if (jsonQuery == null || jsonQuery.trim().isEmpty() || jsonQuery.trim().equals("-")) {
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		if (!(exampleData instanceof List)) {
			return;
		}

		List<?> dataList = (List<?>) exampleData;
		System.out.println(" doFilter dataList " + dataList.size());

		try {
			// 1. แปลง JSON เป็น List ของ Rules
			// ตอนนี้บรรทัดนี้จะปลอดภัยแล้ว เพราะเราดัก String ขยะเอาไว้ข้างบนแล้ว
			List<FilterRule> rules = objectMapper.readValue(jsonQuery, new TypeReference<List<FilterRule>>() {});

			if (rules == null || rules.isEmpty()) {
				// System.out.println(" rules null");
				return;
			}

			// --- STEP 1: แยก Rule กอง Filter กับกอง End ออกจากกัน ---
			// กอง Filter: เอาไว้ใช้ใน .filter()
			List<FilterRule> filterRules = rules.stream()
					.filter(r -> !"End".equalsIgnoreCase(r.getCondition()))
					.collect(Collectors.toList());

			// กอง Organize: เอาไว้ใช้ Sort/Limit ทีหลัง
			List<FilterRule> endRules = rules.stream()
					.filter(r -> "End".equalsIgnoreCase(r.getCondition()))
					.collect(Collectors.toList());

			
			/*
			// --- STEP 2: กรองข้อมูล (แก้ตรงนี้!) ---
			// เปลี่ยนจาก rules เป็น filterRules เพื่อไม่ให้ logic ของ End มาปน
			List<?> filteredResult = dataList.stream()
					.filter(item -> evaluateRules(item, filterRules, id)) 
					.collect(Collectors.toList());
			 */
			
			
			// --- STEP 2: กรองข้อมูล (แก้ตรงนี้!) ---
			List<?> filteredResult;
			// --- STEP 2: กรองข้อมูล (แก้ตรงนี้!) ---
			List<Object> validItems = new ArrayList<>();
			boolean isEdata = !dataList.isEmpty() && hasField(dataList.get(0), "id_refer");

			if (isEdata) {
				
				// ⚡ บังคับ Cast เป็น List<Object> ก่อนเข้า stream 
				@SuppressWarnings("unchecked")
				Map<Object, List<Object>> groupedById = ((List<Object>) dataList).stream()
				        .collect(Collectors.groupingBy(
				                item -> getFieldValue(item, "id_refer"), 
				                LinkedHashMap::new, 
				                Collectors.toList()
				        ));

			    for (List<Object> group : groupedById.values()) {
			        // ส่งไปประเมินทั้งแก๊ง 5 คน
			        if (evaluateGroupRules(group, filterRules, id)) {
			            validItems.addAll(group); // ถ้าแก๊งผ่าน เอาเข้า List ทั้ง 5 คน
			        }
			    }
			} else {
			    // ⚡ กรณี register: ถือซะว่ามันเป็นแก๊งที่มีสมาชิกแค่ 1 คน
			    for (Object item : dataList) {
			        // สร้าง List ชั่วคราวที่มีแค่ item เดียว เพื่อใช้ฟังก์ชัน Group ให้เหมือนกัน
			        if (evaluateGroupRules(Collections.singletonList(item), filterRules, id)) {
			            validItems.add(item);
			        }
			    }
			}

			filteredResult = validItems;
			
			
			
			
			// --- STEP 3: ทำการ Sort / Group (Post-Processing) ---
			if (!endRules.isEmpty()) {
				applySortAndGroup(filteredResult, endRules);
			}

			// 3. เอา List ใหม่ที่กรองแล้วไปใช้งาน
			this.modelData.put("exampleData", filteredResult);

			if (type == 0) {
				queryChains.stream().filter(row -> row.getId() == id).findFirst().ifPresent(row -> {
					row.setDatalist(filteredResult);
					row.setDatalist2(exampleData); // เก็บค่า original ไว้
					row.setHeadlist(heads);
				});
			} else {
				queryChains.stream().filter(row -> row.getId() == id).findFirst().ifPresent(row -> {
					row.setDatalist(filteredResult);
					row.setHeadlist(heads);
				});
			}

		} catch (Exception e) {
			// เปลี่ยนจากการปล่อยให้ StackTrace รก Log เป็นการ Print แจ้งเตือนเบาๆ
			System.err.println("JSON Parse Error in doFilter (ID: " + id + "): " + e.getMessage());
		}
	}
	
	
	
	// ฟังก์ชันช่วยเช็กว่า Object นี้มี Field ชื่อนี้ไหม
	private boolean hasField(Object item, String fieldName) {
	    try {
	        item.getClass().getDeclaredField(fieldName);
	        return true;
	    } catch (NoSuchFieldException e) {
	        return false;
	    }
	}


private boolean evaluateGroupRules(List<Object> group, List<FilterRule> rules, int id) {
    // 1. ดึงข้อมูล QuickQuery และ Update Rules (ลอจิกเดิมของคุณเลยครับ)
    List<QuickQuery> quickQueryItems = ReportDAO.getQuickQuery(id);
    Map<String, String> quickQueryMap = quickQueryItems.stream()
            .collect(Collectors.toMap(QuickQuery::getType, QuickQuery::getField, (oldVal, newVal) -> newVal));

    if (!quickQueryMap.isEmpty()) {
        for (FilterRule rule : rules) {
            String colId = rule.getColumnId();
            if (colId != null && quickQueryMap.containsKey(colId)) {
                rule.setValue(quickQueryMap.get(colId)); 
            }
        }
    }

    // 2. จัดกลุ่ม Rules
    Map<Integer, List<FilterRule>> rulesByGroup = rules.stream()
            .collect(Collectors.groupingBy(FilterRule::getGroup, LinkedHashMap::new, Collectors.toList()));

    // 3. วนลูปเช็คทีละกลุ่ม (OR Logic)
    for (List<FilterRule> groupRules : rulesByGroup.values()) {
        boolean isGroupPass = evaluateGroupLinear(group, groupRules); // ส่งไปประเมินแบบแก๊ง
        if (isGroupPass) {
            return true; 
        }
    }
    return false;
}

private boolean evaluateGroupLinear(List<Object> group, List<FilterRule> rules) {
    Boolean result = null;
    for (FilterRule rule : rules) {
        // ⚡ เรียกใช้ checkGroupCondition แทน checkCondition ธรรมดา!
        boolean currentVal = checkGroupCondition(group, rule); 

        String condition = rule.getCondition();
        if (result == null || "Start".equalsIgnoreCase(condition)) {
            result = currentVal;
        } else if ("AND".equalsIgnoreCase(condition)) {
            result = result && currentVal;
        } else if ("OR".equalsIgnoreCase(condition)) {
            result = result || currentVal;
        }
    }
    return result != null ? result : false;
}
 // ⚡ ฟังก์ชันใหม่: ตรวจสอบยกแก๊ง (List<Object>) ต่อ 1 Rule
    private boolean checkGroupCondition(List<Object> group, FilterRule rule) {
        String op = rule.getOperation();
        
        // กำหนดว่า Operation ไหนเป็น "กฎเหล็ก" (ตก 1 คน = ตกทั้งแก๊ง)
        boolean isStrictRule = op.equals("not_find_in_set") || 
                               op.equals("3_months_passed") || 
                               op.equals("6_months_passed") || 
                               op.equals("12_months_passed");

        if (isStrictRule) {
            // 🔴 กฎเหล็ก (ALL): ทุกคนในแก๊งต้องผ่าน
            for (Object item : group) {
                if (!checkCondition(item, rule)) {
                    return false; // เจอคนแหกกฎ 1 คน เตะทิ้งทั้งกลุ่ม!
                }
            }
            return true; // รอดหมดทุกคน ถือว่าผ่าน
            
        } else {
            // 🟢 กฎยืดหยุ่น (ANY): ขอแค่มี 1 คนผ่าน ก็ถือว่าทั้งแก๊งรอด
            for (Object item : group) {
                if (checkCondition(item, rule)) {
                    return true; // เจอคนผ่านปุ๊บ ให้กลุ่มนี้ผ่านเลย ไม่ต้องเช็กคนอื่นต่อ!
                }
            }
            return false; // หาจนครบแล้ว ไม่มีใครถูกเลย ถือว่าตก
        }
    }
    
    
    // ฟังก์ชันตรวจสอบเงื่อนไขรายตัว (Reflection & Operation)
    private boolean checkCondition(Object item, FilterRule rule) {
        try {
            // Reflection: ดึงค่าจาก field ชื่อเดียวกับ column_id
            Field field = item.getClass().getDeclaredField(rule.getColumnId());
            field.setAccessible(true);
            Object fieldValue = field.get(item);

         // --- ปรับจุดที่ 1: อย่าเปลี่ยน null เป็น "" ---
            String valToCheck = (fieldValue != null) ? fieldValue.toString() : null;
            String targetVal = rule.getValue();

            // --- ปรับจุดที่ 2: Logic หัวใจสำคัญ ---
            // ถ้าค่าใน Object เป็น null (หมายถึงยังไม่เคยบันทึกรายการนี้) 
            // ให้คืนค่า true เพื่อ "เก็บ" รายการนี้ไว้ใน list (ไม่โดนกรองออก)
            if (valToCheck == null  && !rule.getOperation().equals("Is null")) { return false;  }

            
            
            // Operation Switch Case
            switch (rule.getOperation()) {
                case "=":
                	System.out.println(" valToCheck :"+valToCheck+" targetVal :"+targetVal + " equals:"+valToCheck.equals(targetVal));
                    if(targetVal.isBlank()) { return true;}
                    return valToCheck.equals(targetVal);
                case "!=":
                    if(targetVal.isBlank()) { return true;}
                    return !valToCheck.equals(targetVal);
                case "%link%":
                    if(targetVal.isBlank()) { return true;}
                  	System.out.println(" valToCheck :"+valToCheck+" targetVal :"+targetVal + " equals:"+valToCheck.equals(targetVal));
                    return valToCheck.contains(targetVal);
                // เพิ่ม case อื่นๆ เช่น >, < ตามต้องการ (ต้องแปลง type ก่อน)
                case ">":
                    if(targetVal.isBlank()) { return true;}
                    return compareValues(fieldValue, targetVal) > 0;
                case "<":
                    if(targetVal.isBlank()) { return true;}
                    return compareValues(fieldValue, targetVal) < 0;
                case ">=":
                    if(targetVal.isBlank()) { return true;}
                    return compareValues(fieldValue, targetVal) >= 0;
                case "<=":
                    if(targetVal.isBlank()) { return true;}
                    return compareValues(fieldValue, targetVal) <= 0;
                case "Is null":
                    return valToCheck == null ;
                case "Is not null":
                    return  valToCheck != null;
                case "Is empty":
                    return targetVal.isBlank();
                case "Is not empty":
                    return !targetVal.isBlank();
                case "link%": 
                    if(targetVal.isBlank()) { return true;}
                    return valToCheck.startsWith(targetVal);

                // 2. Ends With (ลงท้ายด้วย)
                case "%link":
                    if(targetVal.isBlank()) { return true;}
                    return valToCheck.endsWith(targetVal);

                // 3. In List (เป็นหนึ่งในรายการที่ระบุ)
                case "find_in_set":
                    if(targetVal.equals("-")) { return true;}
                    if (targetVal == null || targetVal.isEmpty()) return false;
                    
                    String[] options = targetVal.split("\\|");
                    for (String opt : options) {
                        // trim() ตัดช่องว่างหัวท้ายทิ้ง เผื่อส่งมาแบบ "Thai, Eng"
                        if (valToCheck.equals(opt.trim())) {
                            return true; // เจอแล้ว return true เลย
                        }
                    }
                    return false; // หาจนจบแล้วไม่เจอ
                    
                    // 3. In List (เป็นหนึ่งในรายการที่ระบุ)
                case "not_find_in_set":
                    if(targetVal.equals("-")) { return true;}
                    if (targetVal == null || targetVal.isEmpty()) return false;
                    
                    String[] options2 = targetVal.split("\\|");
                    for (String opt : options2) {

                    	
                        if (valToCheck.equals(opt.trim())) {
                      
                            return false; // หาจนจบแล้วไม่เจอ
                        }
                    }
      
                    return true; // เจอแล้ว return true เลย
                case "3_months_passed":
                	 System.err.println("Field not found: "+valToCheck+" : isMonthsPassed ");
                	 return isMonthsPassedBetween(valToCheck, 3, 6);
                	//return isMonthsPassed(valToCheck, 3);
                case "6_months_passed":
                	return isMonthsPassedBetween(valToCheck, 6, 9);
                	//return isMonthsPassed(valToCheck, 6);  
                case "9_months_passed":
                	return isMonthsPassedBetween(valToCheck,9, 12);
                	//return isMonthsPassed(valToCheck, 12);
                case "12_months_passed":
                	return isMonthsPassedBetween(valToCheck,12, 9999);
                default	:
                //	return isMonthsPassed(valToCheck, 12);
               	 System.err.println("Field not found:default");
              
                    return false;
            }
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
        	System.err.println("Field not found: " + rule.getColumnId() + " -> Keeping item");
            return true;
        }
    }
	
    
    /**
     * ตรวจสอบว่าเวลาผ่านไปแล้วอยู่ในช่วงเดือนที่กำหนดหรือไม่ (minMonths ถึง maxMonths)
     * @param valToCheck วันที่ที่ต้องการตรวจสอบ (Format: yyyy-MM-dd)
     * @param minMonths จำนวนเดือนเริ่มต้นที่ต้องผ่านไปแล้ว (รวมค่านี้)
     * @param maxMonths จำนวนเดือนสูงสุด (ไม่รวมค่านี้)
     * @return boolean true ถ้าอายุอยู่ในช่วง [minMonths, maxMonths)
     */
    public static boolean isMonthsPassedBetween(String valToCheck, int minMonths, int maxMonths) {
        try {
            LocalDate checkDate = LocalDate.parse(valToCheck);
            LocalDate now = LocalDate.now();

            // 1. หาขอบเขตเวลา
            LocalDate minTargetDate = checkDate.plusMonths(minMonths);
            LocalDate maxTargetDate = checkDate.plusMonths(maxMonths);

            // 2. ปัจจุบันต้อง "เลยหรือเท่ากับ" minTargetDate และ "ต้องไม่ถึง" maxTargetDate
            boolean isPassedMin = !now.isBefore(minTargetDate); // >= minTargetDate
            boolean isBeforeMax = now.isBefore(maxTargetDate);  // < maxTargetDate

            return isPassedMin && isBeforeMax;

        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }
    
    
 // คืนค่า int: ติดลบถ้า column น้อยกว่า, 0 ถ้าเท่ากัน, บวกถ้า column มากกว่า
    private int compareValues(Object actualValue, String targetValueStr) {
        try {
            // 1. กรณีเป็น ตัวเลข (Integer, Long, Double, BigDecimal)
            if (actualValue instanceof Number) {
                double v1 = ((Number) actualValue).doubleValue();
                double v2 = Double.parseDouble(targetValueStr);
                return Double.compare(v1, v2);
            }

            // 2. กรณีเป็น วันที่ (LocalDate) เช่น 2023-12-31
            if (actualValue instanceof LocalDate) {
                LocalDate d1 = (LocalDate) actualValue;
                // ต้องตกลง format ใน json ให้ตรงกัน เช่น yyyy-MM-dd
                LocalDate d2 = LocalDate.parse(targetValueStr, DateTimeFormatter.ISO_LOCAL_DATE); 
                return d1.compareTo(d2);
            }

            // 3. กรณีเป็น วันที่และเวลา (LocalDateTime)
            if (actualValue instanceof LocalDateTime) {
                LocalDateTime dt1 = (LocalDateTime) actualValue;
                // สมมติ json ส่งมาแบบ ISO เช่น 2023-12-31T10:15:30
                LocalDateTime dt2 = LocalDateTime.parse(targetValueStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return dt1.compareTo(dt2);
            }

            // 4. กรณีเป็น String (เทียบตามตัวอักษร)
            return actualValue.toString().compareTo(targetValueStr);
            
        } catch (Exception e) {
            System.err.println("Compare Error: " + e.getMessage());
            return 0; // แปลงไม่ได้ หรือ Type ไม่ตรง ถือว่าเท่ากัน (หรือ handle ตามต้องการ)
        }
    }
    
    
    /**
     * ตรวจสอบว่าเวลาผ่านไปแล้วกี่เดือน โดยเริ่มนับจากวันที่ที่ระบุ
     * @param valToCheck วันที่ที่ต้องการตรวจสอบ (Format: yyyy-MM-dd)
     * @param months จำนวนเดือนที่ต้องผ่านไป
     * @return boolean true ถ้าผ่านมาแล้วตามจำนวนเดือนที่กำหนด
     */
    public static boolean isMonthsPassed(String valToCheck, int months) {
        try {
            // 1. ตรวจสอบว่าเป็นวันที่ (LocalDate) หรือไม่
            LocalDate checkDate = LocalDate.parse(valToCheck);
            LocalDate now = LocalDate.now();

            // 2. คำนวณวันที่เป้าหมาย (วันที่เริ่ม + จำนวนเดือน)
            LocalDate targetDate = checkDate.plusMonths(months);

            // 3. ตรวจสอบว่าปัจจุบัน "เลย" หรือ "เท่ากับ" วันที่เป้าหมายแล้วหรือยัง
            return now.isAfter(targetDate) || now.isEqual(targetDate);

        } catch (DateTimeParseException | NullPointerException e) {
            // กรณี Format ผิดหรือไม่ใช่ค่าวันที่ จะคืนค่า false
            return false;
        }
    }


	@SuppressWarnings({ "unchecked", "rawtypes" })
    private void applySortAndGroup(List<?> dataList, List<FilterRule> endRules) {
        
        // สร้าง Comparator ตัวหลัก
        Comparator<Object> comparator = null;

        for (FilterRule rule : endRules) {
            
            // สร้างตัวเปรียบเทียบสำหรับ Column นั้นๆ
            Comparator<Object> currentComparator = (o1, o2) -> {
                try {
                    Object v1 = getFieldValue(o1, rule.getColumnId());
                    Object v2 = getFieldValue(o2, rule.getColumnId());
                    
                    // Handle Null: ให้ null ไปอยู่ท้ายสุด หรือบนสุดตามต้องการ
                    if (v1 == null && v2 == null) return 0;
                    if (v1 == null) return 1;
                    if (v2 == null) return -1;

                    // เปรียบเทียบ (รองรับทั้ง String, Number, Date ที่ implement Comparable)
                    if (v1 instanceof Comparable) {
                        return ((Comparable) v1).compareTo(v2);
                    } else {
                        return v1.toString().compareTo(v2.toString());
                    }
                } catch (Exception e) {
                    return 0;
                }
            };

            // เช็ค Operation เพื่อดูว่าจะเรียงจากน้อยไปมาก หรือ มากไปน้อย
            if ("DESC".equalsIgnoreCase(rule.getValue()) || "ASC".equalsIgnoreCase(rule.getValue())) {
                currentComparator = currentComparator.reversed();
            }
            
            // กรณี Operation เป็น "Group"
            // ในเชิง List การ Group มักหมายถึงการ Sort ให้ค่าเหมือนกันอยู่ติดกัน
            // เพื่อให้ UI ไป merge cell ได้ง่าย
            else if ("Group".equalsIgnoreCase(rule.getOperation())) {
                // ทำงานเหมือน ASC ปกติ (เพื่อให้ข้อมูลกลุ่มเดียวกันอยู่ติดกัน)
                // หรือถ้าต้องการ Logic พิเศษใส่ตรงนี้
            }

            // ผสม Comparator (Chaining) กรณีมีหลายตัว เช่น Order by Name ASC, Type DESC
            if (comparator == null) {
                comparator = currentComparator;
            } else {
                comparator = comparator.thenComparing(currentComparator);
            }
        }

        // สั่ง Sort ข้อมูลใน List เดิมทันที
        if (comparator != null) {
            dataList.sort(comparator);
        }
    }

    // Helper: ดึงค่าจาก Field (ใช้ Reflection)
    private Object getFieldValue(Object item, String fieldName) {
        try {
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(item);
        } catch (Exception e) {
            return null;
        }
    }
    
    
	private void doPrepareCreateQueryChain() {
		
		if(config == null) {	return;	}

		switch(config.getSource_type()) {
			case "Profile"->CallAppProfileExtensions(config.getProcess_id());
		
		}
		
	}

	private void CallAppProfileExtensions(int Process_id) {
		
		System.out.println("CallAppProfile  Process_id "+Process_id);
	
			Platform platform2 = new Platform();
			platform2.setApp_processes_id(Process_id);
			LoadApp Loadapp = new LoadApp();
			PlatformServices.initiate(Loadapp).startAppIndex(platform2).requireExtension().load();
			Map<String, Object> data2 = PlatformServices.getModelData();
			this.modelData.putAll(data2);
			
		}

		private void doDeleteFilterChain() {
			ReportDAO.deleteQueryChain(this.LoadApp.getPlatformReport().getRemove_string_id());
		}

    
		private void CallAppExtension(int Extension_id, int id, boolean first) {
	
			
			System.out.println(" CallAppExtension Extension_id "+ Extension_id);
			
			if(calldata == 1) {
				
				UseExtension UseExtension = EdataDAO.getUseExtension(Extension_id);
				
				Platform platform = new Platform();
		    	platform.setExtension_process(Extension_id);
		    	platform.setWithinId(allIds);
		    	platform.setRelation_id(UseExtension.getUse_process_id());
		    	
		    	
		    	
		    	
				LoadApp Loadapp = new LoadApp();
		    	PlatformServices.initiate(Loadapp).extension(platform).requireExampleDataNper().load();
		    	Map<String, Object> data2 = PlatformServices.getModelData();

				if(data2.get("eformfields") != null && data2.get("edatalist") != null) {
					List<eformfield> eformhead  =  (List<eformfield>) data2.get("eformfields");

				
					
					ShowTableHeaderDetails(eformhead);
					this.modelData.put("eformhead",eformhead);
					
					
					
					
					List<edata>  edata  =  (List<edata>) data2.get("edatalist");
					
					System.out.println("CallAppExtension  edatalist "+edata.size());
					
					
					
					if(first) {
						allIds = edata.stream()
								.map(e -> ((edata) e).getData_id()) // สมมติว่า getId คืนค่าเป็น Integer
							    .distinct()            // ตัดตัวซ้ำออก (สำคัญมาก ช่วยลดของ)
							    .collect(Collectors.toList());
					}
					
					
					doFilter(edata,id,eformhead,0);
					this.modelData.put("edatalist",edata);
					
				}	
				
			}else {
				
				// eformhead
				
				Platform platform = new Platform();
		    	platform.setExtension_process(Extension_id);
				LoadApp Loadapp = new LoadApp();
		    	PlatformServices.initiate(Loadapp).extension(platform).prepareConfig().load();
		    	Map<String, Object> data2 = PlatformServices.getModelData();

				
				if(data2.get("eformfields") != null) {
					List<eformfield> eformhead  =  (List<eformfield>) data2.get("eformfields");
					ShowTableHeaderDetails(eformhead);
					this.modelData.put("eformhead",eformhead);
					
				}	
			}
			
			
	    	
		}
		
		
		private void ShowTableHeaderDetails(List<eformfield> eformhead) {
			if(eformhead.size() < 1) {return; }

			eformhead.stream().forEach(row->{
				
			//	System.out.println(" lable " + row.getField_label());
				
				if(row.getKeymap() != null) {

					if(row.getKeymap().getChange_format().equals("NUMBER")) {
						row.setField_type("number");	
					}else if(row.getKeymap().getChange_format().equals("Enum") && Integer.valueOf(row.getKeymap().getEnum_id())  >  0) {	
						
						dataset  dataset = DatasetDAO.getbyid(Integer.valueOf(row.getKeymap().getEnum_id()));
						List<dataset_data> dataset_data  = Dataset_dataDAO.getall(dataset.getDataset_id());
						
						if(dataset_data.size() > 10) {
							String dataset_dataStr = dataset_data.stream().map(rows-> rows.getDataset_data_title()).limit(25).collect(Collectors.joining(","));
				    		row.setDisplay(dataset_dataStr);
				    		row.setField_type("select");
						}else {
							
			
							
							String dataset_dataStr = dataset_data.stream().map(rows-> rows.getDataset_data_title()).collect(Collectors.joining(","));
				    		row.setDisplay(dataset_dataStr);
				    		row.setField_type("select_multiple");
						}
						
					}
					else {
						row.setField_type("text");	
					}
					
					
					
				} else {

						if(row.getField_type().equals("number")) {
							row.setField_type("number");	
						}
						else if(row.getField_type().equals("select") && row.getField_datareferid() >  0) {
							
				
							dataset  dataset = DatasetDAO.getbyid(row.getField_datareferid());
							List<dataset_data> dataset_data  = Dataset_dataDAO.getall(dataset.getDataset_id());
							String dataset_dataStr = dataset_data.stream().map(rows-> rows.getDataset_data_title()).limit(25).collect(Collectors.joining(","));
				    		row.setField_type("select");
				    		row.setDisplay(dataset_dataStr);
					
						}else if(row.getField_type().equals("checkbox") && row.getField_datareferid() >  0) {	
							
							dataset  dataset = DatasetDAO.getbyid(row.getField_datareferid());
							List<dataset_data> dataset_data  = Dataset_dataDAO.getall(dataset.getDataset_id());
							String dataset_dataStr = dataset_data.stream().map(rows-> rows.getDataset_data_title()).limit(25).collect(Collectors.joining(","));
							row.setDisplay(dataset_dataStr);
				    		row.setField_type("select_multiple");
							
						}
						else if(row.getField_type().equals("date")) {
							row.setField_type("date");	
						}
						else {
							row.setField_type("text");	
						}
				}
				
			});
			return;
		}
		
		public String getQueryChainsAsJson() {
		    if (queryChains == null || queryChains.isEmpty()) { return null; }

		    int limit = 200; // หรือค่าจาก Config
		    if (config != null) limit = config.getSync_back_limit();

		    ObjectMapper mapper = new ObjectMapper();

		    // -----------------------------------------------------
		    // 🟢 PHASE 1: หา "ผู้ที่ได้รับเลือก" (เริ่มจากล่างสุด ย้อนขึ้นบน)
		    // -----------------------------------------------------
		    
		    // Map นี้เอาไว้เก็บ "บัตรผ่าน" ว่าแต่ละชั้น ID ไหนได้ไปต่อบ้าง
		    Map<Integer, Set<String>> allowedIdsMap = new HashMap<>();
		    
		    // Set นี้เอาไว้จำว่า "ชั้นข้างล่าง ต้องการพ่อแม่ ID ไหนบ้าง?"
		    Set<String> wantedParentIds = new HashSet<>(); 

		    // วนลูปจาก "ล่างสุด" -> "บนสุด"
		    for (int i = queryChains.size() - 1; i >= 0; i--) {
		        FilterChain row = queryChains.get(i);
		        List<?> rawDataList = (row.getDatalist() instanceof List) ? (List<?>) row.getDatalist() : new ArrayList<>();
		        
		        System.out.print(" rawDataList  size"+rawDataList.size());
		        
		        Set<String> currentLevelAllowed = new HashSet<>();
		        Set<String> nextWantedParents = new HashSet<>(); // เตรียมไว้สำหรับรอบถัดไป (ชั้นที่สูงกว่า)
		        
		        // เช็คว่าใช้ Key อะไร (id หรือ data_id)
		        String idKey = "data_id";
		        if (row.getHeadlist() instanceof List && !((List<?>) row.getHeadlist()).isEmpty()) {
		             if (((List<?>) row.getHeadlist()).get(0) instanceof registerkey) idKey = "id";
		        }

		        int count = 0;

		        // วนลูปข้อมูลในชั้นนี้
		        for (Object rawObj : rawDataList) {
		            Map<String, Object> data = mapper.convertValue(rawObj, Map.class);
		            String myId = String.valueOf(data.get(idKey));
		            String parentId = (data.get("id_refer") != null) ? String.valueOf(data.get("id_refer")) : null;

		            boolean keep = false;

		            if (i == queryChains.size() - 1) {
		                // 🎯 จุดสำคัญ: ถ้าเป็นชั้นล่างสุด (Last Level)
		                // ให้ตัดเอาแค่ Limit แรกเลย (เช่น 200 คนล่าสุด)
		                if (count < limit) {
		                    keep = true;
		                    count++;
		                }
		            } else {
		                // 🎯 ถ้าเป็นชั้นอื่น (พ่อ/ปู่)
		                // ให้เก็บเฉพาะคนที่ "ลูกข้างล่างต้องการ" (อยู่ใน wantedParentIds)
		                if (wantedParentIds.contains(myId)) {
		                    keep = true;
		                }
		            }

		            if (keep) {
		                currentLevelAllowed.add(myId);     // 1. ตัวเองได้ไปต่อ
		                if (parentId != null) {
		                    nextWantedParents.add(parentId); // 2. สั่งจองตัวพ่อแม่ไว้ด้วย
		                }
		            }
		        }
		        
		        // บันทึกผลสอบของชั้นนี้
		        allowedIdsMap.put(i, currentLevelAllowed);
		        
		        // ส่งรายการพ่อแม่ที่ต้องการ ขึ้นไปชั้นถัดไป
		        wantedParentIds = nextWantedParents;
		    }

		    // -----------------------------------------------------
		    // 🔵 PHASE 2: สร้าง JSON (เริ่มจากบน ลง ล่าง ตามปกติ)
		    // -----------------------------------------------------
		    
		    List<Map<String, Object>> resultList = new ArrayList<>();

		    for (int i = 0; i < queryChains.size(); i++) {
		        FilterChain row = queryChains.get(i);
		        Map<String, Object> jsonSection = new LinkedHashMap<>();
		        
		        // ... (Code ส่วน Header ของท่านเหมือนเดิมเป๊ะๆ) ...
		        List<String> displayHeaders = new ArrayList<>();
		        List<String> lookupKeys = new ArrayList<>();
		        String idKey = "data_id";
		        // ... (ใส่ Logic Header เดิมตรงนี้) ...
		        List<?> heads = (List<?>) row.getHeadlist();
		        if (heads != null && !heads.isEmpty()) {
		             if (heads.get(0) instanceof registerkey) {
		                 idKey = "id";
		                 for(registerkey k : (List<registerkey>)heads) {
		                     displayHeaders.add(translate(k.getTitle()));
		                     lookupKeys.add(k.getMainkey());
		                 }
		             } else {
		                 for(eformfield f : (List<eformfield>)heads) {
		                     displayHeaders.add(f.getField_label());
		                     lookupKeys.add("data_value"+f.getField_postion());
		                 }
		             }
		        }
		        
		        jsonSection.put("headers", displayHeaders);
		        jsonSection.put("tables", row.getType());
		        jsonSection.put("id_refer", row.getTables());
		        jsonSection.put("id_config", row.getId_refer());

		        // กรองข้อมูลเข้า JSON ตามโพย (allowedIdsMap)
		        List<Map<String, Object>> cleanData = new ArrayList<>();
		        Set<String> validIds = allowedIdsMap.get(i); // ดึงบัตรผ่านของชั้นนี้มาดู

		        if (row.getDatalist() instanceof List) {
		            List<?> rawDataList = (List<?>) row.getDatalist();
		            jsonSection.put("total_json", rawDataList.size()); // Total ดิบ

		            for (Object rawObj : rawDataList) {
		                Map<String, Object> data = mapper.convertValue(rawObj, Map.class);
		                String myId = String.valueOf(data.get(idKey));

		                // ✅ เช็คง่ายๆ: ถ้ามีชื่อในใบผ่าน ก็เอาลง JSON เลย
		                if (validIds != null && validIds.contains(myId)) {
		                    Map<String, Object> rowMap = new LinkedHashMap<>();
		                    rowMap.put("id", data.get(idKey));
		                    rowMap.put("id_refer", data.get("id_refer"));
		                    
		                    for(int k=0; k<displayHeaders.size(); k++) {
		                        rowMap.put(displayHeaders.get(k), data.get(lookupKeys.get(k)));
		                    }
		                    cleanData.add(rowMap);
		                }
		            }
		        }

		        jsonSection.put("total_data", cleanData.size()); // Total ที่ตัดแล้ว
		        jsonSection.put("data", cleanData);
		        resultList.add(jsonSection);
		    }

		    try {
		        return mapper.writeValueAsString(resultList);
		    } catch (Exception e) {
		        return "[]";
		    }
		}
		
		
		
		
		public String getQueryChainsAsJson2() {
			
			if(queryChains == null ) {return null;}
			
			int limit = LoadApp.getPlatform().getReportconfig().getSync_back_limit();
			
		    List<Map<String, Object>> resultList = new ArrayList<>();

		    IntStream.range(0, queryChains.size()).forEach(index -> {
		    	FilterChain row = queryChains.get(index);
		        Map<String, Object> jsonSection = new LinkedHashMap<>();

		        // ... (ส่วนหา Size เหมือนเดิม) ...

		        List<String> displayHeaders = new ArrayList<>();
		        List<String> dataLookupKeys = new ArrayList<>();
		        
		        // ตัวแปรสำหรับกำหนดว่าจะดึง ID ด้วย key ไหน ("id" หรือ "data_id")
		        // ค่า Default กันเหนียว
		        String idKeyToUse = "data_id"; 

		        // --- ส่วนจัดการ Header แบบ Dynamic Type ---
		        List<?> rawHeads = (row.getHeadlist() instanceof List) ? (List<?>) row.getHeadlist() : null;

		        if (rawHeads != null && !rawHeads.isEmpty()) {
		            Object firstItem = rawHeads.get(0);

		            if (firstItem instanceof registerkey) {
		                // >>> เป็น keymap
		                idKeyToUse = "id"; // keymap น่าจะใช้ 'id'
		                
		                for (registerkey h : (List<registerkey>) rawHeads) {
		                 	String translatedLabel = translate(h.getTitle());
		                    displayHeaders.add(translatedLabel);
		                    dataLookupKeys.add(h.getMainkey());
		                }

		            } else if (firstItem instanceof eformfield) {
		                // >>> เป็น eformfield
		                idKeyToUse = "data_id"; // eformfield น่าจะใช้ 'data_id'
		                
		                for (eformfield h : (List<eformfield>) rawHeads) {
		                    displayHeaders.add(h.getField_label());
		                    dataLookupKeys.add("data_value" + h.getField_postion());
		                }
		            }
		        }
		        
		        jsonSection.put("headers", displayHeaders);
		        jsonSection.put("tables", row.getType());
		        jsonSection.put("id_refer", row.getTables());
		        jsonSection.put("id_config", row.getId_refer());
		        jsonSection.put("total_data", countData);
		        
		        
		     // --- ส่วนจัดการ Data (แก้ใหม่) ---
		        List<Map<String, Object>> cleanedDataList = new ArrayList<>();
		        
		        // 1. ประกาศ ObjectMapper (ปกติ Spring มีให้อยู่แล้ว หรือ new ใหม่ก็ได้)
		        ObjectMapper mapper = new ObjectMapper(); 

		        if (row.getDatalist() instanceof List) {
		        	
		        	
		        	
		            // เปลี่ยนจาก List<Map<...>> เป็น List<?> เพราะเรารู้แล้วว่าข้างในอาจเป็น Object อื่น
		            List<?> rawDataList = (List<?>) row.getDatalist();

		            jsonSection.put("total_json", rawDataList.size());
		            
		            for (Object rawObj : rawDataList) {
		                // 2. แปลง Object (เช่น registers) ให้เป็น Map ทันที
		                // ถ้ามันเป็น Map อยู่แล้ว มันก็แคสให้ ถ้าเป็น Object มันจะแปลง field เป็น key ให้
		                Map<String, Object> rawData = mapper.convertValue(rawObj, Map.class);
		                
		                Map<String, Object> cleanRow = new LinkedHashMap<>();
		                
		                // ตอนนี้ rawData เป็น Map แน่นอนแล้ว เรียก .get() ได้สบายใจ
		                cleanRow.put("id", rawData.get(idKeyToUse)); 

		             // 2. --- เพิ่มตรงนี้ครับ: ใส่ id_refer ---
		                // ดึงออกมาจาก Map ได้เลย ถ้าไม่มีค่า มันจะเป็น null ให้เอง
		                cleanRow.put("id_refer", rawData.get("id_refer"));
		                
		                for (int i = 0; i < displayHeaders.size(); i++) {
		                    // ระวังเรื่อง Case Sensitive นิดนึงนะครับ (id vs Id) 
		                    // แต่ปกติ Jackson จะแปลง field เป็น camelCase ให้
		                    cleanRow.put(displayHeaders.get(i), rawData.get(dataLookupKeys.get(i)));
		                }
		                cleanedDataList.add(cleanRow);
		            }
		        }
		        jsonSection.put("data", cleanedDataList);
		        resultList.add(jsonSection);
		    });

		    try {
		        return new ObjectMapper().writeValueAsString(resultList);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return "[]";
		    }
		}
		
		private int getSafeSize(Object listObj) {
		    if (listObj != null && listObj instanceof List) {
		        return ((List<?>) listObj).size();
		    }
		    return 0; // ถ้าเป็น null หรือไม่ใช่ List ให้ตอบ 0
		}
		
		
	

		// สร้างฟังก์ชันช่วยแปลภาษา (ใส่ไว้ใน Class เดียวกัน)
		private String translate(String code) {
		    try {
		        // พยายามแปลตาม Locale ปัจจุบัน (เช่น TH, EN)
		        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
		    } catch (Exception e) {
		        // ถ้าหาคำแปลไม่เจอ ให้คืนค่า code เดิมกลับไป (เช่น "register.name")
		        return code;
		    }
		}


		
		private void filterFirstChainData() {
		    if (this.queryChains == null || this.queryChains.size() < 2) {
		        return;
		    }

		
		    
		    ObjectMapper mapper = new ObjectMapper();
		    Set<String> activeIds = new HashSet<>();          // เก็บ ID ที่กรอง "ผ่าน" เงื่อนไข
		    Set<String> allChildHistoricalIds = new HashSet<>(); // เก็บ ID "ทั้งหมด" ที่เคยมีประวัติในลูก
		    
		    boolean isFilterActive = false; 

		    // -------------------------------------------------------
		    // STEP 1: วนลูป Table ลูก (Index 1 เป็นต้นไป)
		    // -------------------------------------------------------
		    for (int i = 1; i < this.queryChains.size(); i++) {
		        FilterChain row = this.queryChains.get(i);
		        
		        // --- ส่วนที่ 1: ดึง ID ทั้งหมดจาก Datalist2 (ตัวเต็มก่อนกรอง) ---
		        if (row.getDatalist2() instanceof List) {
		            List<?> fullList = (List<?>) row.getDatalist2();
		            for (Object item : fullList) {
		                Map<String, Object> map = mapper.convertValue(item, Map.class);
		                Object referVal = map.get("id_refer"); 
		                if (referVal != null) {
		                    allChildHistoricalIds.add(String.valueOf(referVal));
		                }
		            }
		        }

		        // --- ส่วนที่ 2: ดึง ID ที่ "ผ่านเงื่อนไข" จาก Datalist (ตัวที่กรองแล้ว) ---
		        if (row.getStatus() == 1) {
		            isFilterActive = true; 
		            if (row.getDatalist() instanceof List) {
		                List<?> filteredList = (List<?>) row.getDatalist();
		                
		    
		                
		                for (Object item : filteredList) {
		                    Map<String, Object> map = mapper.convertValue(item, Map.class);
		                    Object referVal = map.get("id_refer"); 
		                    if (referVal != null) {
		                        activeIds.add(String.valueOf(referVal));
		                    }
		                }
		            }
		        }
		    }

		    // -------------------------------------------------------
		    // STEP 2: กรอง Table แม่ (Index 0)
		    // -------------------------------------------------------
		    if (!isFilterActive) {
		        return; 
		    }

		    FilterChain firstRow = this.queryChains.get(0);
		    if (firstRow.getDatalist() instanceof List) {
		        List<?> originalList = (List<?>) firstRow.getDatalist();
		        List<Object> finalFilteredList = new ArrayList<>(); 

		        for (Object item : originalList) {
		            Map<String, Object> map = mapper.convertValue(item, Map.class);
		            Object idVal = map.get("id");
		            if (idVal == null) idVal = map.get("data_id");

		            if (idVal != null) {
		                String idStr = String.valueOf(idVal);
		                
		                // --- หัวใจสำคัญของ Logic ---
		                // 1. เช็คว่าคนนี้ "เคยมีประวัติในลูกไหม"
		                boolean hasHistory = allChildHistoricalIds.contains(idStr);
		                
		                // 2. เช็คว่าคนนี้ "กรองผ่านเงื่อนไขไหม"
		                boolean passedFilter = activeIds.contains(idStr);

		                // เงื่อนไขการเก็บ: (ไม่มีประวัติเลย) OR (มีประวัติและต้องกรองผ่าน)
		                if (!hasHistory || passedFilter) {
		                    finalFilteredList.add(item); 
		                }
		            }
		        }
		        firstRow.setDatalist(finalFilteredList);
		    }
		}
		
		
		
		
		
		
		private void setEnabledActiveQueryChain() {
			if(this.LoadApp.getPlatformReport().getApp_item_id() == 0) { return;}
			ReportDAO.setEnabledActiveQueryChain(this.LoadApp.getPlatformReport().getApp_item_id());
			
		}

		private String convertJsonToReadableText(String jsonStr) {
		    if (jsonStr == null || jsonStr.trim().isEmpty()) return "";

		    try {
		        ObjectMapper mapper = new ObjectMapper();
		        List<Map<String, Object>> list = mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});

		        if (list.isEmpty()) return "";

		        // 1. จัดกลุ่มตาม key "group" (ใช้ TreeMap เพื่อให้เรียงลำดับกลุ่ม 1, 2, 3...)
		        Map<String, List<Map<String, Object>>> grouped = list.stream()
		                .collect(Collectors.groupingBy(
		                        m -> String.valueOf(m.getOrDefault("group", "1")), 
		                        TreeMap::new, 
		                        Collectors.toList()
		                ));

		        List<String> groupStrings = new ArrayList<>();

		        // 2. วนลูปสร้างข้อความทีละกลุ่ม
		        for (Map.Entry<String, List<Map<String, Object>>> entry : grouped.entrySet()) {
		            List<Map<String, Object>> items = entry.getValue();
		         // 1. สร้าง StringBuilder รอไว้
		            StringBuilder groupBuffer = new StringBuilder();

		            for (int i = 0; i < items.size(); i++) {
		                Map<String, Object> item = items.get(i);
		                
		                // ดึงค่าต่างๆ
		                String col = (String) item.get("column_tag");
		                String op = (String) item.get("operation");
		                Object valObj = item.get("value");
		                String val = (valObj != null) ? String.valueOf(valObj) : "";

		                // --- ดึงตัวเชื่อม (AND/OR) ---
		                // ถ้าไม่มีค่า หรือเป็น Start ให้ถือว่าเป็น AND ไปก่อน (หรือจะเป็นค่าว่างก็ได้แล้วแต่ Logic)
		                Object condObj = item.get("condition");
		                String currentJoin = (condObj != null) ? String.valueOf(condObj) : "AND";

		                // แก้คำว่า Start ให้เป็น AND (ถ้าจำเป็น)
		                if ("Start".equalsIgnoreCase(currentJoin)) {
		                    currentJoin = "AND"; 
		                }

		                // --- สร้างคำพูด (Readable Text) ---
		                String readable;
		                if (op.toLowerCase().contains("null") || op.toLowerCase().contains("empty")) {
		                    readable = String.format("'%s' %s ", col, op);
		                } else if (op.equalsIgnoreCase("Start")) {
		                    readable = String.format("'%s' Starts with '%s'", col, val);
		                } else {
		                    readable = String.format("'%s' %s '%s'", col, op, val);
		                }

		                // ********** จุดสำคัญ (Logic การต่อคำ) **********
		                if (i == 0) {
		                    // ตัวแรก ใส่ข้อความลงไปเลย
		                    groupBuffer.append(readable);
		                } else {
		                    // ตัวถัดไป ให้เอา Condition ของบรรทัดนี้ มาแปะข้างหน้าก่อน
		                    // ผลลัพธ์จะเป็น:  ... [ข้อความเก่า] + " OR " + [ข้อความใหม่]
		                    groupBuffer.append(" ").append(currentJoin).append(" ").append(readable);
		                }
		            }

		            // จบ Loop ก็ได้ข้อความครบเลย ไม่ต้อง String.join แล้ว
		            String joinedGroup = groupBuffer.toString();
		            
		            // ถ้ามีหลายกลุ่ม ให้ใส่วงเล็บเพื่อให้ดูง่าย: ( A AND B )
		            if (grouped.size() > 1) {
		                groupStrings.add("( " + joinedGroup + " )");
		            } else {
		                groupStrings.add(joinedGroup);
		            }
		        }

		        // 3. เชื่อมแต่ละกลุ่มด้วย OR
		        return String.join(" OR ", groupStrings);

		    } catch (Exception e) {
		        e.printStackTrace();
		        return ""; // ถ้า Error ให้คืนค่าว่าง หรือคืน json เดิมไปก่อน
		    }
		}
		
		
		
		
		// เปลี่ยน Return Type เป็น List<FilterDisplayItem> ตามที่ต้องการ
		public List<FilterDisplayItem> getFilterDisplayList(String jsonStr) {
		    List<FilterDisplayItem> resultList = new ArrayList<>();

		    // เช็คค่าว่าง กันพัง
		    if (jsonStr == null || jsonStr.trim().isEmpty()) {
		        return resultList;
		    }

		    try {
		        ObjectMapper mapper = new ObjectMapper();
		        
		        // 1. แปลง JSON String ให้เป็น List<Map> ก่อน (เพื่อเก็บทุก field ไว้ตามเดิม)
		        List<Map<String, Object>> rawList = mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});

		        if (rawList.isEmpty()) return resultList;

		        // 2. จัดกลุ่มตาม "group" (ใช้ TreeMap เพื่อให้เรียงกลุ่ม 1, 2, 3... อัตโนมัติ)
		        Map<String, List<Map<String, Object>>> grouped = rawList.stream()
		                .collect(Collectors.groupingBy(
		                        m -> String.valueOf(m.getOrDefault("group", "1")), 
		                        TreeMap::new, 
		                        Collectors.toList()
		                ));

		        int groupCount = 0;

		        // 3. วนลูปทีละกลุ่ม
		        for (Map.Entry<String, List<Map<String, Object>>> entry : grouped.entrySet()) {
		            List<Map<String, Object>> itemsInGroup = entry.getValue();

		            // --- Logic แทรก OR (ระหว่างกลุ่ม) ---
		            // ถ้าไม่ใช่กลุ่มแรก ให้ใส่ OR คั่นก่อน
		            if (groupCount > 0) {
		                resultList.add(new FilterDisplayItem("OR"));
		            }

		         // 4. วนลูปไอเทมในกลุ่มนั้น
		            for (int i = 0; i < itemsInGroup.size(); i++) {
		                Map<String, Object> item = itemsInGroup.get(i);

		                // --- Logic แทรก Operator (ในกลุ่มเดียวกัน) ---
		                if (i > 0) {
		                    // 1. ดึงค่า condition ออกมาจาก JSON ก่อน
		                    Object condObj = item.get("condition");
		                    String conditionVal = (condObj != null) ? String.valueOf(condObj) : "AND";

		                    // 2. เช็คว่าเป็น OR หรือไม่ (ถ้าเป็น Start หรือค่าอื่น ให้ Default เป็น AND)
		                    if ("OR".equalsIgnoreCase(conditionVal)) {
		                        resultList.add(new FilterDisplayItem("OR"));
		                    } else {
		                        resultList.add(new FilterDisplayItem("AND"));
		                    }
		                }

		                // ใส่ข้อมูลจริง (Rule)
		                resultList.add(new FilterDisplayItem(item));
		            }
		            
		            groupCount++;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        // กรณี Error จะ Return List เปล่า หรือจัดการตามเหมาะสม
		    }
		    
		    
		    System.out.println(" resultList "+resultList.toString());

		    return resultList;
		}




		private void dosaveQuickQuery() {
			
			if(this.LoadApp.getPlatformReport().getQuickQuery() == null) { return; }
			
			String  Type = this.LoadApp.getPlatformReport().getQuickQuery().getType();
			System.out.println(Type);
			
			ReportDAO.saveQuickQuery(this.LoadApp.getPlatformReport().getQuickQuery());
			
		
			
		}

		
		private void ReplaceDisplayList(List<FilterDisplayItem> displayItem, int id) {
		    // 1. ดึงข้อมูล QuickQuery
		    List<QuickQuery> quickQueryItems = ReportDAO.getQuickQuery(id);

		    // 2. สร้าง Map เพื่อใช้ค้นหา (Key=type, Value=field)
		    Map<String, String> replaceMap = quickQueryItems.stream()
		            .collect(Collectors.toMap(
		                QuickQuery::getType, 
		                QuickQuery::getField, 
		                (oldVal, newVal) -> newVal
		            ));

		    // 3. วนลูป displayItem
		    displayItem.stream().forEach(row -> {
		        
		        // เช็คก่อนว่า ruleData ไม่ใช่ null (ข้ามพวก OPERATOR)
		        if (row.getRuleData() != null) {
		            
		            // ดึง column_id ออกมาจาก Map (ต้อง Cast เป็น String)
		            String colId = (String) row.getRuleData().get("column_id");

		            // ถ้ามี colId นี้อยู่ในรายการที่จะแทนที่
		            if (colId != null && replaceMap.containsKey(colId)) {
		                
		                String newValue = replaceMap.get(colId);

		                // ยัดค่าใหม่กลับเข้าไปใน Map ด้วย key "value"
		                row.getRuleData().put("value", newValue);
		            }
		        }
		    });
		}



		private void doSyncBackSegmentation() {
			
			if(this.LoadApp.getPlatformReport().getSegmentation() == null) { return; }
			
			LocalDateTime localDate = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 String date_edit = localDate.format(formatter);
			 this.LoadApp.getPlatformReport().getSegmentation().setDate_edit(date_edit);
			
			ReportDAO.saveSyncBackSegmentation(this.LoadApp.getPlatformReport().getSegmentation());
			
		}
		
	private void doUnSyncBackSegmentation() {
			
			if(this.LoadApp.getPlatformReport().getSegmentation() == null) { return; }
			
			LocalDateTime localDate = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 String date_edit = localDate.format(formatter);
			 this.LoadApp.getPlatformReport().getSegmentation().setDate_edit(date_edit);
			
			ReportDAO.doUnSyncBackSegmentation(this.LoadApp.getPlatformReport().getSegmentation());
			
		}
		
	private void doCopySyncBackSegmentation() {
		
		ReportDAO.copySegmentation(this.LoadApp.getPlatformReport().getSegmentation());
		
	}
	
	
		private void doChangeWorkflowStatusClose() {
			if(this.LoadApp.getPlatformReport().getWorkflow_status_close() == null) { return; }	
			
			idocDAO.end(this.LoadApp.getPlatformReport().getApp_item_id(), this.LoadApp.getPlatformReport().getWorkflow_status_close());
			String status = this.LoadApp.getPlatformReport().getWorkflow_status_close_from()+" TO "+this.LoadApp.getPlatformReport().getWorkflow_status_close();
			logService.action(200,"Success (Change Close Status ) :"+status, "Report_Component/doChangeWorkflowStatusClose",this.LoadApp.getPlatformReport().getApp_item_id());
		}



		public void  ShowTableRegisterDetails(List<registerkey>  heads2) {
	
			if(heads2.size() < 1) {return; }
			

			heads2.stream().forEach(row->{
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
		
		}

		// =======================================================
				// 1. ฟังก์ชันสร้าง SQL (ฉบับจรวด 🚀 รองรับ Summary Columns และ Filter ปกติ)
				// =======================================================
				public String generateDynamicSQL(String cheeseJson) {
				    try {
				        ObjectMapper mapper = new ObjectMapper();
				        JsonNode rootNode = mapper.readTree(cheeseJson);
				        JsonNode prepareReport = rootNode.get("PrepareReport");
				        JsonNode filterChain = rootNode.get("FilterChain");

				        if (filterChain == null || !filterChain.isArray() || filterChain.size() == 0) {
				            return "";
				        }

				        // ดึงโหมดรายงาน
				        String reportMode = prepareReport.has("report_mode") ? prepareReport.get("report_mode").asText() : "normal";
				        boolean isTrendMode = "trend".equalsIgnoreCase(reportMode); 
				        StringBuilder selectClause = new StringBuilder("SELECT MAIN_TBL.*");
				        StringBuilder fromClause = new StringBuilder();
				        StringBuilder whereClause = new StringBuilder("WHERE 1=1 \n");

				        // ==========================================
				        // STEP 1: จัดการ MAIN TABLE (Index 0)
				        // ==========================================
				        JsonNode mainRow = filterChain.get(0);
				        String mainGroup = mainRow.get("groups").asText();
				        String mainTableName = resolveTableName(mainGroup);

				        if ("register".equalsIgnoreCase(mainTableName)) {
				            fromClause.append("FROM ( \n");
				            fromClause.append("  SELECT *, CONCAT(register_code, register_daterun,'-',register_id) AS full_reg_code \n");
				            fromClause.append("  FROM ").append(mainTableName).append(" \n");
				            fromClause.append(") MAIN_TBL \n");
				        } else {
				            fromClause.append("FROM ").append(mainTableName).append(" MAIN_TBL \n");
				        }

				        if ("program".equalsIgnoreCase(mainGroup)) {
				            fromClause.append("LEFT JOIN idoc MAIN_IDOC ON MAIN_IDOC.eform_id = MAIN_TBL.data_id \n");
				            selectClause.append(", MAIN_IDOC.creator, MAIN_IDOC.sender, MAIN_IDOC.receiver, MAIN_IDOC.current_step, MAIN_IDOC.closing_status, MAIN_IDOC.date, MAIN_IDOC.date_end, MAIN_IDOC.remarks");
				        }
				        
				        

				        String mainJsonOutPut = mainRow.has("json_out_put") ? mainRow.get("json_out_put").asText() : "";
				        
			
			            String subTableIds = mainRow.has("tables") ? mainRow.get("tables").asText() : "0";
			            String TableIds = mainRow.has("extension_id") ? mainRow.get("extension_id").asText() : subTableIds; 
				        Map<String, String> mainParsedSql = parseJsonOutputToSql(mainJsonOutPut, "MAIN_TBL",TableIds);
				        
	
				        
				        whereClause.append(" ").append(mainParsedSql.get("WHERE")).append("\n");

				        int notUniqueActive = prepareReport.has("sync_back_not_unique_active") ? prepareReport.get("sync_back_not_unique_active").asInt() : 0;
				       
				        if (notUniqueActive == 0 && "register".equalsIgnoreCase(mainTableName)) {
				            whereClause.append("  AND NOT EXISTS ( \n");
				            whereClause.append("      SELECT 1 FROM segmentation \n");
				            whereClause.append("      WHERE status != 0 \n");
				            whereClause.append("      AND FIND_IN_SET(MAIN_TBL.id, segmentation_member_id) > 0 \n");
				            whereClause.append("  ) \n");
				        }

				        // ==========================================
				        // STEP 2: จัดการตัวกรอง / Summary (Index 1 ถึง N)
				        // ==========================================
				        for (int i = 1; i < filterChain.size(); i++) {
				            JsonNode subRow = filterChain.get(i);
				            
				         // 🚀 ถ้า status ไม่ใช่ 1 (ถูกปิดไว้) ให้ข้ามการสร้าง SQL ตัวกรองนี้ไปเลย!
				            int status = subRow.has("status") ? subRow.get("status").asInt() : 1;
				            if (status == 0) {
				                continue; 
				            }
				            
				            
				            
				            String subGroup = subRow.get("groups").asText();
				            String subTableName = resolveTableName(subGroup);
				            
				            String subTableId = subRow.has("tables") ? subRow.get("tables").asText() : "0";
				            String extension_id = subRow.has("extension_id") ? subRow.get("extension_id").asText() : subTableId; 
			
				            String subJsonOutPut = subRow.has("json_out_put") ? subRow.get("json_out_put").asText() : "";
				            
				
				            Map<String, String> parsedSql = parseJsonOutputToSql(subJsonOutPut, "SUB_TBL",extension_id);
				         // 🚀 2. [เพิ่ม 2 บรรทัดนี้ต่อท้ายเลย!] ดึง Key สำหรับทำ JOIN ออกมาจาก parsedSql
			                String groupKeyCol = parsedSql.getOrDefault("GROUP_KEY", "SUB_TBL.id_refer"); 
			                String mainLinkCol = parsedSql.getOrDefault("MAIN_LINK", "MAIN_TBL.id");
				            
				            
				         // 🚀 [เพิ่มใหม่] โค้ดทะลวงกล่อง json_out_put เพื่อหา profile_id 
				            String profileIdField = "data_value1"; // ค่าเริ่มต้น
				            try {
				                if (!subJsonOutPut.isEmpty()) {
				                    JsonNode innerNodes = mapper.readTree(subJsonOutPut);
				                    // เช็คว่ามีตัวลูกไหม และตัวลูกตัวแรกมี profile_id ซ่อนอยู่หรือเปล่า
				                    if (innerNodes.isArray() && innerNodes.size() > 0 && innerNodes.get(0).has("profile_id")) {
				                        profileIdField = innerNodes.get(0).get("profile_id").asText();
				                    }
				                }
				            } catch (Exception e) {
				                // ถ้าแกะไม่ออก ก็ปล่อยผ่าน ใช้ data_value1
				            }
				            
				            
				            boolean hasHaving = !parsedSql.get("HAVING").isEmpty();
				            String aggAlias = "SUB_AGG_" + i; 

				            // 🌟 เรียกใช้เครื่องยนต์ Trend จากฟังก์ชันที่แยกออกไป
				            Map<String, String> trendInfo = extractTrendLogic(subJsonOutPut, subGroup);
				         // 🚀 ดึงค่าให้ตรงกับ Key ใน JSON เป๊ะๆ (ใช้ prepareReport ดึงตรงๆ เลยชัวร์สุด)
				            String trendDateCol = prepareReport != null ? prepareReport.path("trend_date_column").asText("") : "";
				            String trendFormat  = prepareReport != null ? prepareReport.path("trend_format").asText("DAY") : "DAY";

				            // (ส่วน dateFilterSql ถ้าคุณมี logic สร้างไว้ก่อนหน้า ก็ใช้ของเดิมได้เลยครับ)
				            String dateFilterSql = trendInfo != null ? trendInfo.getOrDefault("dateFilterSql", "") : "";
				            
				            // 🌟 ตรวจสอบว่าใน JSON ของกลุ่มนี้ มีการสั่ง Summary หรือไม่?
				            boolean isSummaryColumn = !reportMode.equalsIgnoreCase("filter_list") && !reportMode.equalsIgnoreCase("normal");


				            // 🌟 สร้างเงื่อนไขแยกให้ชัดเจน
				            boolean isSummaryMode = reportMode.equalsIgnoreCase("summary") || reportMode.equalsIgnoreCase("trend");
				            boolean isCustomJoin = subRow.has("join_type") && !subRow.path("join_type").asText("").isEmpty(); 

				            // ดึงค่า Join & Display (เตรียมไว้ใช้ถ้าเข้าเงื่อนไข isCustomJoin)
				            String dynamicJoinType = isCustomJoin ? subRow.path("join_type").asText() : "";
				            String dynamicMasterKey = subRow.has("join_master_key") ? subRow.path("join_master_key").asText() : "";
				            String dynamicChildKey = subRow.has("join_child_key") ? subRow.path("join_child_key").asText() : "";
				            String displayColsStr = subRow.has("display_columns") ? subRow.path("display_columns").asText() : "";
				            
				            
				            if (isSummaryMode) {
				                // 🟢 กลุ่ม 1: สร้างคอลัมน์ Summary & Trend (LEFT JOIN) 🟢
			
				            	String summaryColName = prepareReport != null && !prepareReport.path("summary_name").asText("").isEmpty() 
				            	                        ? prepareReport.path("summary_name").asText() 
				            	                        : "Summary_Col_" + i;
				                String aggFunctionStr = extractAggFunction(subJsonOutPut); 

				                String groupByCol = prepareReport != null ? prepareReport.path("group_by_column").asText("") : "";

				                String trendSelectInjection = "";
				                String trendGroupByParts = ""; // ใช้เก็บก้อนที่จะไปต่อท้าย GROUP BY

				                // --- 🌟 ส่วนที่ 1: จัดการแกนวันที่ (Trend Mode) ---
				                if (trendDateCol != null && !trendDateCol.isEmpty()) {
				                    String timeFormatSql = trendDateCol;
				                    if ("DAY".equalsIgnoreCase(trendFormat)) timeFormatSql = "DATE(SUB_TBL." + trendDateCol + ")";
				                    else if ("MONTH".equalsIgnoreCase(trendFormat)) timeFormatSql = "DATE_FORMAT(SUB_TBL." + trendDateCol + ", '%Y-%m')";
				                    else if ("YEAR".equalsIgnoreCase(trendFormat)) timeFormatSql = "YEAR(SUB_TBL." + trendDateCol + ")";

				                    trendSelectInjection += ", " + timeFormatSql + " AS trend_axis ";
				                    trendGroupByParts += ", " + timeFormatSql; 

				                    // ดึงวันที่ออกไปโชว์ในตารางหลัก
				                    selectClause.append(", \n  ").append(aggAlias).append(".trend_axis AS `Trend_Date`");
				                }

				                // --- 🚀 🌟 ส่วนที่ 2: จัดการแยกกลุ่มเส้นกราฟ (Group By Column) ---
				                if (groupByCol != null && !groupByCol.isEmpty()) {
				                    // ฉีดเข้า SELECT ของ Sub Query
				                    trendSelectInjection += ", SUB_TBL." + groupByCol + " AS category_axis ";
				                    
				                    // ฉีดเข้า GROUP BY ของ Sub Query
				                    trendGroupByParts += ", SUB_TBL." + groupByCol; 
				                    
				                    // ดึงผลลัพธ์ออกไปแสดงผลในตารางหลัก (เพื่อให้ Javascript จับไปแยกสีเส้นกราฟ)
				                    selectClause.append(", \n  ").append(aggAlias).append(".category_axis AS `").append(groupByCol).append("`");
				                }

				                // ประกอบร่าง GROUP BY ขั้นสุดท้าย
				                String trendGroupByInjection = "   GROUP BY group_key" + trendGroupByParts + " \n";

				                // ดึงค่ายอดรวม (SUM/COUNT) ออกไปโชว์ในตารางหลัก
				                selectClause.append(", \n  IFNULL(").append(aggAlias).append(".summary_val, 0) AS `").append(summaryColName).append("`");
				                
				                
				                // --- 🌟 ส่วนที่ 3: ประกอบร่าง Sub Query ---
				                fromClause.append(" LEFT JOIN ( \n");

				                // 🚀 กฎพิเศษ: program ชน program
				                if (mainGroup.equals("program") && subGroup.equals("program")) {
				                    fromClause.append("   SELECT REL.main_id AS group_key").append(trendSelectInjection).append(", ").append(aggFunctionStr).append(" AS summary_val \n");
				                    fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                    fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); 
				                    fromClause.append("   INNER JOIN relations REL ON REL.relations_id = SUB_TBL.data_id \n"); 
				                } else {
				                    fromClause.append("   SELECT ").append(groupKeyCol).append(" AS group_key").append(trendSelectInjection).append(", ").append(aggFunctionStr).append(" AS summary_val \n");
				                    fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                    if (subGroup.equalsIgnoreCase("program")) {
				                        fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); 
				                    }
				                }
				                
				                fromClause.append("   WHERE 1=1 \n");

				                if (!dateFilterSql.isEmpty()) {
				                    fromClause.append(dateFilterSql);
				                }
				                if (parsedSql.containsKey("WHERE") && !parsedSql.get("WHERE").trim().isEmpty()) {
				                    fromClause.append("   ").append(parsedSql.get("WHERE")).append(" \n");
				                }

				                // ใส่คำสั่ง GROUP BY ที่ประกอบร่างเอาไว้แล้ว
				                fromClause.append(trendGroupByInjection);
				                fromClause.append(" ) ").append(aggAlias).append(" ON ");

				                // 🚀 กฎพิเศษตอน ON: program ชน program
				                if (mainGroup.equals("program") && subGroup.equals("program")) {
				                    fromClause.append("MAIN_TBL.data_id = ").append(aggAlias).append(".group_key \n");
				                } else {
				                    fromClause.append(mainLinkCol).append(" = ").append(aggAlias).append(".group_key \n");
				                }

				            } else if (isCustomJoin) {
				            	// 🚀 🟢 กลุ่ม 4: Custom Join แบบมีระบบแปลงร่างคอลัมน์
				                fromClause.append(" ").append(dynamicJoinType).append(" ( \n");
				                
				                // 🌟 1. จัดการ Child Key (ป้องกัน Error: id_refer ไม่มีในตาราง register)
				                String selectChildCol = !dynamicChildKey.isEmpty() ? dynamicChildKey : groupKeyCol;
				                String childKeySql = "SUB_TBL." + selectChildCol;
				                
				                // แปลงร่าง customerCode หรือดัก fallback ของ id_refer
				                if (selectChildCol.equalsIgnoreCase("customerCode") || selectChildCol.equalsIgnoreCase("full_reg_code") || selectChildCol.equalsIgnoreCase("register_code")) {
				                    childKeySql = "CONCAT(SUB_TBL.register_code, SUB_TBL.register_daterun, '-', SUB_TBL.register_id)";
				                } else if (selectChildCol.equalsIgnoreCase("id_refer") && subTableName.equalsIgnoreCase("register")) {
				                    childKeySql = "SUB_TBL.id"; // ตาราง register ไม่มี id_refer ให้ใช้ id แทน
				                }
				                
				                // SELECT คีย์ที่จะเอาไว้ JOIN
				                fromClause.append("   SELECT ").append(childKeySql).append(" AS group_key ");
				                
				                // 🌟 2. จัดการ Display Columns (ถ้าเลือก customerCode มา ต้องแปลงเป็น CONCAT ด้วย)
				                if (!displayColsStr.isEmpty()) {
				                    String[] cols = displayColsStr.split(",");
				                    for (String col : cols) {
				                        String displaySql = "SUB_TBL." + col;
				                        
				                        // แปลงร่างถ้าเป็นคอลัมน์พิเศษ
				                        if (col.equalsIgnoreCase("customerCode") || col.equalsIgnoreCase("full_reg_code") || col.equalsIgnoreCase("register_code")) {
				                            displaySql = "CONCAT(SUB_TBL.register_code, SUB_TBL.register_daterun, '-', SUB_TBL.register_id)";
				                        }
				                        
				                        fromClause.append(", ").append(displaySql).append(" AS ").append(col);
				                        
				                        // ส่งไปให้ SELECT ของตารางแม่ (MAIN_TBL) ดึงไปโชว์
				                        selectClause.append(", \n  ").append(aggAlias).append(".").append(col).append(" AS `").append(col).append("`");
				                    }
				                }
				                fromClause.append(" \n");
				                
				                // FROM ตารางลูก
				                fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                if (subGroup.equalsIgnoreCase("program")) {
				                    fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); 
				                }
				                
				                // WHERE ของตารางลูก
				                fromClause.append("   WHERE 1=1 \n");
				                if (parsedSql.containsKey("WHERE") && !parsedSql.get("WHERE").trim().isEmpty()) {
				                    fromClause.append("   ").append(parsedSql.get("WHERE")).append("\n");
				                }
				                
				                fromClause.append(" ) ").append(aggAlias).append(" ON ");
				                
				                // 🌟 3. จัดการ Master Key (เผื่อตารางแม่ก็ใช้ customerCode ในการเชื่อม)
				                if (!dynamicMasterKey.isEmpty() && !dynamicChildKey.isEmpty()) {
				                    String masterKeySql = "MAIN_TBL." + dynamicMasterKey;
				                    
				                    if (dynamicMasterKey.equalsIgnoreCase("customerCode") || dynamicMasterKey.equalsIgnoreCase("full_reg_code") || dynamicMasterKey.equalsIgnoreCase("register_code")) {
				                        masterKeySql = "CONCAT(MAIN_TBL.register_code, MAIN_TBL.register_daterun, '-', MAIN_TBL.register_id)";
				                    }
				                    
				                    fromClause.append(masterKeySql).append(" = ").append(aggAlias).append(".group_key \n");
				                } else {
				                    fromClause.append(mainLinkCol).append(" = ").append(aggAlias).append(".group_key \n");
				                }
				            } else if (hasHaving) {
				                // 🟢 กลุ่ม 2: นับจำนวนแบบมี HAVING (INNER JOIN) 🟢
				                String typeName = subRow.has("type") ? subRow.get("type").asText() : "Count_Col_" + i;
				                typeName = typeName.replace("`", ""); 

				                selectClause.append(", \n  ").append(aggAlias).append(".cnt_val AS `").append(typeName).append("`");
				                fromClause.append(" INNER JOIN ( \n");

				                // 🚀 กฎพิเศษ: program ชน program
				                if (mainGroup.equals("program") && subGroup.equals("program")) {
				                    fromClause.append("   SELECT REL.main_id AS group_key, COUNT(*) AS cnt_val \n");
				                    fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                    fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); // กฎ 1
				                    fromClause.append("   INNER JOIN relations REL ON REL.relations_id = SUB_TBL.data_id \n"); // กฎ 2
				                } else {
				                    fromClause.append("   SELECT ").append(groupKeyCol).append(" AS group_key, COUNT(*) AS cnt_val \n");
				                    fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                    if (subGroup.equalsIgnoreCase("program")) {
				                        fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); // กฎ 1
				                    }
				                }
				                fromClause.append("   WHERE 1=1 \n");

				                if (parsedSql.containsKey("WHERE") && !parsedSql.get("WHERE").trim().isEmpty()) {
				                    fromClause.append("   ").append(parsedSql.get("WHERE")).append("\n");
				                }
				                
				                fromClause.append("   GROUP BY group_key \n");
				                
				                if (parsedSql.containsKey("HAVING") && !parsedSql.get("HAVING").trim().isEmpty()) {
				                    fromClause.append("   ").append(parsedSql.get("HAVING")).append("\n");
				                }

				                fromClause.append(" ) ").append(aggAlias).append(" ON ");

				                // 🚀 กฎพิเศษตอน ON: program ชน program
				                if (mainGroup.equals("program") && subGroup.equals("program")) {
				                    fromClause.append("MAIN_TBL.data_id = ").append(aggAlias).append(".group_key \n");
				                } else {
				                    fromClause.append(mainLinkCol).append(" = ").append(aggAlias).append(".group_key \n");
				                }

				            } else {
				                // 🟢 กลุ่ม 3: กรองธรรมดา INNER JOIN DISTINCT 🟢
				                String existAlias = "EXIST_FILTER_" + i;
				                fromClause.append(" INNER JOIN ( \n");

				                // 🚀 กฎพิเศษ: program ชน program
				                if (mainGroup.equals("program") && subGroup.equals("program")) {
				                    fromClause.append("   SELECT DISTINCT REL.main_id AS group_key \n");
				                    fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                    fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); // กฎ 1
				                    fromClause.append("   INNER JOIN relations REL ON REL.relations_id = SUB_TBL.data_id \n"); // กฎ 2
				                } else {
				                    fromClause.append("   SELECT DISTINCT ").append(groupKeyCol).append(" AS group_key \n");
				                    fromClause.append("   FROM ").append(subTableName).append(" SUB_TBL \n");
				                    if (subGroup.equalsIgnoreCase("program")) {
				                        fromClause.append("   INNER JOIN idoc SUB_IDOC ON SUB_IDOC.eform_id = SUB_TBL.data_id \n"); // กฎ 1
				                    }
				                }
				                fromClause.append("   WHERE 1=1 \n");

				                if (parsedSql.containsKey("WHERE") && !parsedSql.get("WHERE").trim().isEmpty()) {
				                    fromClause.append("   ").append(parsedSql.get("WHERE")).append("\n");
				                }

				                fromClause.append(" ) ").append(existAlias).append(" ON ");

				                // 🚀 กฎพิเศษตอน ON: program ชน program
				                if (mainGroup.equals("program") && subGroup.equals("program")) {
				                    fromClause.append("MAIN_TBL.data_id = ").append(existAlias).append(".group_key \n");
				                } else {
				                    fromClause.append(mainLinkCol).append(" = ").append(existAlias).append(".group_key \n");
				                }
				            }
				        }

				        // ==========================================
				        // STEP 3: ประกอบร่างทั้งหมดเข้าด้วยกัน
				        // ==========================================
				        StringBuilder finalSql = new StringBuilder();
				        finalSql.append(selectClause.toString()).append("\n")
				                .append(fromClause.toString()).append("\n")
				                .append(whereClause.toString()).append("\n");

				        int syncBackRandom = prepareReport.has("sync_back_random") ? prepareReport.get("sync_back_random").asInt() : 0;
				        if (syncBackRandom == 1) {
				            finalSql.append("ORDER BY RAND() \n");
				        }
				        
				        int limit = prepareReport.has("sync_back_limit") ? prepareReport.get("sync_back_limit").asInt() : 60;
				        finalSql.append("LIMIT ").append(limit);

				        String resultSql = finalSql.toString();

				        String realDateConcat = "CONCAT(%s.year, '-', LPAD(%s.month, 2, '0'), '-', LPAD(%s.day, 2, '0'))";
				        resultSql = resultSql.replace("SUB_TBL.date_event", String.format(realDateConcat, "SUB_TBL", "SUB_TBL", "SUB_TBL"));
				        resultSql = resultSql.replace("MAIN_TBL.date_event", String.format(realDateConcat, "MAIN_TBL", "MAIN_TBL", "MAIN_TBL"));

				        return resultSql;

				    } catch (Exception e) {
				        e.printStackTrace();
				        return "ERROR_GENERATING_SQL: " + e.getMessage();
				    }
				}
		
		
		
		
		// =======================================================
		// 🚀 ฟังก์ชันดึงเงื่อนไขวันที่สำหรับ Trend (แยกออกมาให้โค้ดคลีน)
		// =======================================================
		private Map<String, String> extractTrendLogic(String subJsonOutPut, String subGroup) {
		    Map<String, String> result = new HashMap<>();
		    result.put("trendDateCol", "");
		    result.put("trendColumnSql", "");
		    result.put("dateFilterSql", "");

		    if (subJsonOutPut == null || subJsonOutPut.isEmpty()) return result;

		    try {
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode arrNode = mapper.readTree(subJsonOutPut);

		        String trendDateCol = "";
		        
		        // 🌟 รอบที่ 1: กวาดหา trend_date_col ก่อนเลย
		        for (JsonNode item : arrNode) {
		            if (item.has("trend_date_col") && !item.get("trend_date_col").asText().isEmpty()) {
		                trendDateCol = item.get("trend_date_col").asText();
		                
		                // 🌟 [เพิ่มบรรทัดนี้] เก็บ trend_format ออกมาด้วย (ถ้าไม่มีให้ Default เป็น DAY)
		                String tFormat = item.has("trend_format") ? item.get("trend_format").asText() : "DAY";
		                result.put("trendFormat", tFormat);
		                
		                break; // เจอแล้วหยุดเลย
		            }
		        }
		        if (trendDateCol.isEmpty()) return result;
		        result.put("trendDateCol", trendDateCol);

		        // 🌟 รอบที่ 2: ค่อยกวาดหาวันที่ Start / End (ตอนนี้เรารู้ชื่อคอลัมน์แล้ว)
		        String userStartDateStr = "";
		        String userEndDateStr = "";
		        for (JsonNode item : arrNode) {
		            if (item.has("column_tag") && item.has("operation") && item.has("value")) {
		                String colTag = item.get("column_tag").asText();
		                String op = item.get("operation").asText();
		                String val = item.get("value").asText();

		                if (colTag.equals(trendDateCol) && val.matches("\\d{4}-\\d{2}-\\d{2}")) {
		                    if (op.equals("=>") || op.equals(">=") || op.equals(">")) {
		                        userStartDateStr = val;
		                    } else if (op.equals("<=") || op.equals("=<") || op.equals("<")) {
		                        userEndDateStr = val;
		                    }
		                }
		            }
		        }

		        // เริ่มคำนวณวัน
		        java.time.LocalDate today = java.time.LocalDate.now();
		        java.time.LocalDate startDate = null;
		        java.time.LocalDate endDate = null;
		        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");

		        try {
		            if (!userStartDateStr.isEmpty()) startDate = java.time.LocalDate.parse(userStartDateStr, formatter);
		            if (!userEndDateStr.isEmpty()) endDate = java.time.LocalDate.parse(userEndDateStr, formatter);
		        } catch (Exception e) {}

		        // กฎ 4 ข้อของการคุมเวลา (Max 60 วัน)
		        if (startDate == null && endDate == null) {
		            endDate = today; startDate = today.minusDays(30);
		        } else if (startDate != null && endDate != null) {
		            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
		            if (daysBetween > 60) startDate = endDate.minusDays(60); 
		        } else if (startDate != null && endDate == null) {
		            endDate = startDate.plusDays(60);
		            if (endDate.isAfter(today)) endDate = today;
		        } else if (startDate == null && endDate != null) {
		            startDate = endDate.minusDays(30);
		        }

		        // แปลงตารางปฏิทิน
		        String trendColumnSql = "SUB_TBL." + trendDateCol;
		        if ("calendar".equalsIgnoreCase(subGroup) && "date_event".equalsIgnoreCase(trendDateCol)) {
		            trendColumnSql = "CONCAT(SUB_TBL.year, '-', LPAD(SUB_TBL.month, 2, '0'), '-', LPAD(SUB_TBL.day, 2, '0'))";
		        }
		        result.put("trendColumnSql", trendColumnSql);

		        // 🛠️ แก้ไขแล้ว: สร้าง SQL กรอกวันที่แค่บรรทัดเดียว (ไม่ทับกัน)
		        String dateFilterSql = String.format("   AND DATE(%s) >= '%s' AND DATE(%s) <= '%s' \n", 
		                                trendColumnSql, startDate.toString(), 
		                                trendColumnSql, endDate.toString());
		        result.put("dateFilterSql", dateFilterSql);

		    } catch (Exception e) {}

		    return result;
		}
		
		
		// -----------------------------------------------------
		// 🛠️ เครื่องมือช่วยแยกดึงชื่อคอลัมน์และคำสั่งจาก JSON (แบบหยาบ)
		// -----------------------------------------------------
		private String extractSummaryName(String jsonStr, int index) {
		    try {
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode arr = mapper.readTree(jsonStr);
		        for (JsonNode item : arr) {
		            if (item.has("summary_name") && !item.get("summary_name").asText().isEmpty()) {
		                return item.get("summary_name").asText();
		            }
		        }
		    } catch (Exception e) {}
		    return "Summary_Col_" + index;
		}

		// -----------------------------------------------------
		// 🛠️ เครื่องมือช่วยดึงฟังก์ชันสรุปผล (Agg Functions)
		// -----------------------------------------------------
		private String extractAggFunction(String jsonStr) {
		    try {
		        ObjectMapper mapper = new ObjectMapper();
		        JsonNode arr = mapper.readTree(jsonStr);
		        for (JsonNode item : arr) {
		            if (item.has("agg_func") && !item.get("agg_func").asText().isEmpty()) {
		                String func = item.get("agg_func").asText().toUpperCase();
		                String col = item.has("target_col") ? item.get("target_col").asText() : "";
		                
		                // 1. นับจำนวนธรรมดา (COUNT)
		                if ("COUNT".equals(func)) {
		                    // ถ้าไม่ระบุคอลัมน์ ให้นับทุกแถว COUNT(*)
		                    return col.isEmpty() ? "COUNT(*)" : "COUNT(SUB_TBL." + col + ")";
		                }
		                
		                // 2. 🌟 นับจำนวนแบบไม่ซ้ำ (D_COUNT = Distinct Count)
		                if ("D_COUNT".equals(func)) {
		                    if (!col.isEmpty()) {
		                        // 🚀 ความฉลาดพิเศษ: ถ้าคอลัมน์เป้าหมายมีคำว่า date (เช่น data_dateadd) 
		                        // ให้ใช้ DATE() ครอบเพื่อตัดเวลาทิ้ง จะได้นับแบบ "1 วัน = 1 นับ" ตามที่คุณต้องการ
		                        if (col.toLowerCase().contains("date")) {
		                            return "COUNT(DISTINCT DATE(SUB_TBL." + col + "))";
		                        }
		                        // แต่ถ้าเป็นคอลัมน์ปกติ (เช่น ชื่อคน, ID) ก็นับไม่ซ้ำแบบปกติ
		                        return "COUNT(DISTINCT SUB_TBL." + col + ")";
		                    }
		                    return "COUNT(DISTINCT SUB_TBL.id)"; // Fallback ถ้าดันลืมเลือกคอลัมน์
		                }
		                
		                // 3. ฟังก์ชันอื่นๆ (SUM, AVG, MAX, MIN)
		                if (!col.isEmpty()) {
		                    return func + "(SUB_TBL." + col + ")";
		                }
		            }
		        }
		    } catch (Exception e) {
		        // ถ้าแกะ JSON พัง ให้ข้ามไปใช้ค่า Default
		    }
		    return "COUNT(*)"; // Default กันระบบพัง
		}
		// =======================================================
		// 2. ฟังก์ชันหาชื่อตาราง
		// =======================================================
		// =======================================================
		// 2. ฟังก์ชันหาชื่อตาราง
		// =======================================================
		private String resolveTableName(String groupName) {
		    if ("Profile".equalsIgnoreCase(groupName)) return "register";
		    if ("Product".equalsIgnoreCase(groupName)) return "product_master";
		    
		    // 🌟 เพิ่ม 5 ตารางใหม่เข้าสารบบ
		    if ("Calendar".equalsIgnoreCase(groupName)) return "calendar";
		    if ("User".equalsIgnoreCase(groupName)) return "users";
		    if ("Department".equalsIgnoreCase(groupName)) return "department";
		    if ("CustomerInsight".equalsIgnoreCase(groupName)) return "customer_insight";
		    if ("SalesPredictionData".equalsIgnoreCase(groupName)) return "sales_prediction_data";
		    if ("DocumentEffect".equalsIgnoreCase(groupName)) return "document_effect";
		    if ("HelpDesk".equalsIgnoreCase(groupName)) return "help_desk_tickets";
		    return "edata"; // default 
		}


		
		
		
		
		// =======================================================
		// 3. ฟังก์ชันเครื่องจักรแปลภาษา (ฉบับสมบูรณ์ กรองค่าว่างทิ้ง)
		// =======================================================
		private Map<String, String> parseJsonOutputToSql(String jsonStr, String tableAlias, String idRefer) {
		    Map<String, String> sqlParts = new HashMap<>();
		    sqlParts.put("WHERE", "");
		    sqlParts.put("HAVING", "");

		    if (jsonStr == null || jsonStr.trim().isEmpty()) return sqlParts;

		    // 🌟 รายชื่อคอลัมน์ของ workflow (idoc) - เติม "date" เรียบร้อยแล้ว
		    List<String> idocColumns = Arrays.asList("creator", "sender", "receiver", "current_step", "closing_status", "date", "date_end","remarks");

		    try {
		        ObjectMapper mapper = new ObjectMapper();
		        List<Map<String, Object>> list = mapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});

		        if (list.isEmpty()) return sqlParts;

		        Map<String, List<Map<String, Object>>> grouped = list.stream()
		                .collect(Collectors.groupingBy(
		                        m -> String.valueOf(m.getOrDefault("group", "1")),
		                        TreeMap::new,
		                        Collectors.toList()
		                ));

		        List<String> whereGroups = new ArrayList<>();
		        List<String> havingGroups = new ArrayList<>(); 

		        for (Map.Entry<String, List<Map<String, Object>>> entry : grouped.entrySet()) {
		            List<Map<String, Object>> items = entry.getValue();
		            StringBuilder whereBuffer = new StringBuilder();
		            StringBuilder havingBuffer = new StringBuilder(); 

		            for (int i = 0; i < items.size(); i++) {
		                Map<String, Object> item = items.get(i);
		                
		                String col = (String) item.get("column_id"); 
		                String op = (String) item.get("operation");
		                Object valObj = item.get("value");
		                
		                // 🚀 trim() เผื่อกรณี User เคาะ Spacebar เล่นๆ มา
		                String val = (valObj != null) ? String.valueOf(valObj).replace("'", "''").trim() : "";

		                String currentJoin = (String) item.getOrDefault("condition", "AND");
		                if ("Start".equalsIgnoreCase(currentJoin)) currentJoin = "AND";

		                op = (op != null) ? op.toLowerCase() : "";
		                boolean isHaving = false; 

		                // 🛑 [ดักค่าว่าง] ถ้าไม่ได้กรอกค่ามา ให้ข้าม (Ignore) กฎนี้ไปเลย!
		                // ข้อยกเว้น: คำสั่งตระกูล null, empty หรือ months_passed ที่ไม่ต้องพึ่งตัวแปร val
		                if (val.isEmpty() && !op.contains("null") && !op.contains("empty") && !op.contains("months_passed")) {
		                    continue; 
		                }

		                // 🌟 ตรวจสอบว่าเป็นคอลัมน์ของ idoc หรือไม่?
		                boolean isIdocCol = (col != null && idocColumns.contains(col.toLowerCase()));
		                String actualCol = isIdocCol ? "IDOC_TBL." + col : tableAlias + "." + col;
		                
		                // ====================================================
		                // 🚀 ฟีเจอร์พิเศษ 1: ประกอบร่างวันที่ให้ Calendar กลางอากาศ
		                // ====================================================
		                if ("date_event".equalsIgnoreCase(col)) {
		                    actualCol = "DATE(CONCAT(" + tableAlias + ".year, '-', LPAD(" + tableAlias + ".month, 2, '0'), '-', LPAD(" + tableAlias + ".day, 2, '0')))";
		                }

		                String condExpr = ""; 
		                String sqlCondition = ""; // 🚀 <--- เติมบรรทัดนี้ลงไปเลยครับ!
		                // 🚀 ถ้าเจอคำเหล่านี้ ให้เปลี่ยนจากข้อความเป็นตัวเลขที่ส่งมาจาก JSON นอก
		                if (val.equals("#this_extension_id") || val.equals("#this_tables")) {
		                    val = idRefer; 
		                }
		                
		                // 🚀 ดักจับค่าพิเศษ register_code เพื่อแปลงร่างเป็น CONCAT
		                if (actualCol.endsWith(".register_code") || actualCol.endsWith(".registerCode") || actualCol.endsWith(".customerCode")) {
		                    actualCol = "CONCAT(" + tableAlias + ".register_code, " + tableAlias + ".register_daterun, '-', " + tableAlias + ".register_id)";
		                }
		                
		                // 🚀 ดักจับเงื่อนไขการเชื่อมต่อตาราง (Relation Link)
		                if (val.startsWith("#this_")) {
		                    String mainColName = "";
		                    if (val.equals("#this_user")) mainColName = "user_id";
		                    else if (val.equals("#this_register_id")) mainColName = "id";
		                    else if (val.equals("#this_register_code")) mainColName = "full_reg_code";
		                    else mainColName = val.replace("#this_", ""); 

		                    sqlParts.put("GROUP_KEY", actualCol); 
		                    sqlParts.put("MAIN_LINK", "MAIN_TBL." + mainColName); 
		                    continue; // 🔥 ข้ามไปเลย ไม่เอาไปใส่ใน WHERE
		                }
		                
		                // ====================================================
		                // 🚀 ฟีเจอร์พิเศษ 2: ดักจับ course_name
		                // ====================================================
		                if ("course_name".equalsIgnoreCase(col) && (op.equals("=") || op.equals("=="))) {
		                    condExpr = actualCol + " LIKE '%" + val + "%'";
		                }
		                else if (op.equalsIgnoreCase("is_empty")) {
		                    // เผื่อ Database เก็บเป็น NULL ด้วย จะได้ครอบคลุม
		                    condExpr = "( " + actualCol + " = '' OR " + actualCol + " IS NULL )";
		                }
		                else if (op.equals("(c)>=")) {
		                    condExpr = "COUNT(*) >= " + val;
		                    isHaving = true;
		                } 
		                else if (op.equals("(c)<=")) {
		                    condExpr = "COUNT(*) <= " + val;
		                    isHaving = true;
		                }
		                // 🚀 กลุ่มวันที่ (Date)
		                else if (op.equalsIgnoreCase("(d)=>")) {
		                    condExpr = "DATE(" + actualCol + ") >= '" + val + "'";
		                } 
		                else if (op.equalsIgnoreCase("(d)<=")) {
		                    condExpr = "DATE(" + actualCol + ") <= '" + val + "'";
		                } 
		                // 🚀 กลุ่มเดือน (Month) 
		                else if (op.equalsIgnoreCase("(m)=>") || op.equalsIgnoreCase("(m)<=")) {
		                    String monthVal = val;
		                    if (monthVal.contains("-") || monthVal.contains("/")) {
		                        monthVal = monthVal.replace("/", "-"); 
		                        String[] parts = monthVal.split("-");
		                        if (parts.length == 3) {
		                            try { monthVal = String.valueOf(Integer.parseInt(parts[1])); } 
		                            catch (Exception e) {}
		                        }
		                    }
		                    if (op.equalsIgnoreCase("(m)=>")) condExpr = "MONTH(" + actualCol + ") >= " + monthVal;
		                    else condExpr = "MONTH(" + actualCol + ") <= " + monthVal;
		                }
		                else if (op.contains("null")) {
		                    condExpr = actualCol + (op.contains("not") ? " IS NOT NULL" : " IS NULL");
		                } 
		                else if (op.equals("find_in_set")) {
		                    // รูปแบบที่ถูกต้อง: FIND_IN_SET(คอลัมน์, 'ค่า1,ค่า2,ค่า3')
		                    condExpr = "FIND_IN_SET(" + actualCol + ", '" + val + "') > 0";
		                }
		                else if (op.equals("not_find_in_set")) {
		                    // ทำงานตรงข้ามกันคือให้เท่ากับ 0
		                    condExpr = "FIND_IN_SET(" + actualCol + ", '" + val + "') = 0";
		                }
		                else if (op.equals("link%")) { condExpr = actualCol + " LIKE '" + val + "%'"; } 
		                else if (op.equals("%link")) { condExpr = actualCol + " LIKE '%" + val + "'"; } 
		                else if (op.equals("%link%")) { condExpr = actualCol + " LIKE '%" + val + "%'"; } 
		        
		                /*
		                else if (op.contains("months_passed")) {
		                    String months = op.split("_")[0]; 
		                    String validDateFilter = actualCol + " REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}'";
		                    if (whereBuffer.length() == 0) whereBuffer.append(validDateFilter);
		                    else whereBuffer.append(" ").append(currentJoin).append(" ").append(validDateFilter);

		                    condExpr = "MAX(DATE(LEFT(" + actualCol + ", 10))) <= DATE_SUB(NOW(), INTERVAL " + months + " MONTH)";
		                    isHaving = true; 
		                }
		                */
		                
		                else if (op.contains("months_passed")) {
		                    
		                    // 1. ตรวจสอบ Format วันที่ใน WHERE ให้ชัวร์ก่อน
		                    String validDateFilter = actualCol + " REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}'";
		                    if (whereBuffer.length() == 0) whereBuffer.append(validDateFilter);
		                    else whereBuffer.append(" ").append(currentJoin).append(" ").append(validDateFilter);

		                    // 2. สร้างเงื่อนไข HAVING แบบมีขอบเขต (Min-Max) ไม่ให้ยอดซ้อนทับกัน
		                    String maxDateCol = "MAX(DATE(LEFT(" + actualCol + ", 10)))";
		                    
		                    if (op.equals("3_months_passed")) {
		                        // มากกว่า 3 เดือน แต่ไม่ถึง 6 เดือน
		                        condExpr = maxDateCol + " <= DATE_SUB(NOW(), INTERVAL 3 MONTH) AND " + maxDateCol + " > DATE_SUB(NOW(), INTERVAL 6 MONTH)";
		                    } 
		                    else if (op.equals("6_months_passed")) {
		                        // มากกว่า 6 เดือน แต่ไม่ถึง 12 เดือน
		                        condExpr = maxDateCol + " <= DATE_SUB(NOW(), INTERVAL 6 MONTH) AND " + maxDateCol + " > DATE_SUB(NOW(), INTERVAL 12 MONTH)";
		                    } 
		                    else if (op.equals("9_months_passed")) {
		                        // มากกว่า 12 เดือน แต่ไม่ถึง 24 เดือน
		                        condExpr = maxDateCol + " <= DATE_SUB(NOW(), INTERVAL 9 MONTH) AND " + maxDateCol + " > DATE_SUB(NOW(), INTERVAL 12 MONTH)";
		                    } 
		                    else if (op.equals("12_months_passed")) {
		                        // มากกว่า 12 เดือน แต่ไม่ถึง 24 เดือน
		                        condExpr = maxDateCol + " <= DATE_SUB(NOW(), INTERVAL 12 MONTH) AND " + maxDateCol + " > DATE_SUB(NOW(), INTERVAL 24 MONTH)";
		                    } 
		                    else if (op.equals("24_months_passed")) {
		                        // 24 เดือนขึ้นไป
		                        condExpr = maxDateCol + " <= DATE_SUB(NOW(), INTERVAL 24 MONTH)";
		                    } 
		                    else {
		                        // Fallback: เผื่อมีค่าอื่นๆ หลุดเข้ามา ให้หั่นตัวเลขหน้า _months_passed แบบเดิม
		                        String months = op.split("_")[0];
		                        condExpr = maxDateCol + " <= DATE_SUB(NOW(), INTERVAL " + months + " MONTH)";
		                    }
		                    
		                    isHaving = true; 
		                }
		                
		                else {
		                    String sqlOp = op.equals("=>") ? ">=" : op.toUpperCase();
		                    condExpr = actualCol + " " + sqlOp + " '" + val + "'";
		                }

		                // 🌟 ท่าไม้ตาย: หุ้มคอลัมน์ idoc ด้วย EXISTS
		                if (isIdocCol && !isHaving) {
		                    sqlCondition = "EXISTS (SELECT 1 FROM idoc IDOC_TBL WHERE IDOC_TBL.eform_id = " + tableAlias + ".data_id AND " + condExpr + ")";
		                } else {
		                    sqlCondition = condExpr;
		                }

		                if (isHaving) {
		                    if (havingBuffer.length() == 0) havingBuffer.append(sqlCondition);
		                    else havingBuffer.append(" ").append(currentJoin).append(" ").append(sqlCondition);
		                } else {
		                    if (whereBuffer.length() == 0) whereBuffer.append(sqlCondition);
		                    else whereBuffer.append(" ").append(currentJoin).append(" ").append(sqlCondition);
		                }
		            }

		            if (whereBuffer.length() > 0) whereGroups.add("( " + whereBuffer.toString() + " )");
		            if (havingBuffer.length() > 0) havingGroups.add("( " + havingBuffer.toString() + " )");
		        }

		        if (!whereGroups.isEmpty()) {
		            sqlParts.put("WHERE", "AND ( " + String.join(" OR ", whereGroups) + " ) ");
		        }
		        if (!havingGroups.isEmpty()) {
		            sqlParts.put("HAVING", "HAVING ( " + String.join(" OR ", havingGroups) + " ) ");
		        }

		        return sqlParts;

		    } catch (Exception e) {
		        System.err.println("Error parsing JSON to SQL: " + e.getMessage());
		        return sqlParts; 
		    }
		}
}
