package com.smepro.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smepro.dao.attendanceDAO;
import com.smepro.dao.cmDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eflowDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.reportDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dto.AccountSummaryDTO;
import com.smepro.dto.BalanceSheetDTO;
import com.smepro.dto.DisbursementDTO;
import com.smepro.dto.FieldMetaDTO;
import com.smepro.dto.PayrollSlipDTO;
import com.smepro.dto.TrialBalanceDTO;
import com.smepro.dto.edataDTO;
import com.smepro.dto.inventoryDTO;
import com.smepro.dto.jobDTO;
import com.smepro.dto.materialDTO;
import com.smepro.dto.productionDTO;
import com.smepro.dto.search_workflowDTO;
import com.smepro.entity.CustomReports;
import com.smepro.entity.GamificationGoal;
import com.smepro.entity.ReportJoinConfig;
import com.smepro.entity.account_ledger;
import com.smepro.entity.assets;
import com.smepro.entity.attendance_record;
import com.smepro.entity.company_annual_plan;
import com.smepro.entity.deduction_record;
import com.smepro.entity.general_journal;
import com.smepro.entity.payroll_record;
import com.smepro.entity.shift_record;
import com.smepro.entity.workrule;
import com.smepro.entity.workrule_leave_log;
import com.smepro.enums.AnnualPlanType;
import com.smepro.enums.DB;
import com.smepro.enums.FGMovementType;
import com.smepro.enums.Frequency;
import com.smepro.enums.Reference_category;
import com.smepro.enums.SKMovementType;
import com.smepro.enums.Save_status;
import com.smepro.enums.Step;
import com.smepro.enums.Sys_status;
import com.smepro.enums.TaskBehaviorType;
import com.smepro.enums.Tkey1;
import com.smepro.enums.Tkey2;
import com.smepro.enums.Tkey3;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Asset_log;
import com.smepro.models.BuildIn;
import com.smepro.models.Extension;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.Feed;
import com.smepro.models.Fg;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Goal;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.Links;
import com.smepro.models.LinksSetting;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Management;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Myapp;
import com.smepro.models.Platform;
import com.smepro.models.PlatformConfig;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.StatusSummary;
import com.smepro.models.TabManagement;
import com.smepro.models.UseExtension;
import com.smepro.models.actionkeymap;
import com.smepro.models.attendance;
import com.smepro.models.bom;
import com.smepro.models.cm;
import com.smepro.models.condition;
import com.smepro.models.control_steps;
import com.smepro.models.datalink;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.department;
import com.smepro.models.drives;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_production_record;
import com.smepro.models.edata_register;
import com.smepro.models.eflow;
import com.smepro.models.eformfield;
import com.smepro.models.fgstock;
import com.smepro.models.fieldindex;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.groups;
import com.smepro.models.keymap;
import com.smepro.models.keymap2;
import com.smepro.models.map_key;
import com.smepro.models.notification;
import com.smepro.models.oee;
import com.smepro.models.other;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.product_log;
import com.smepro.models.product_master;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.purchase;
import com.smepro.models.qci;
import com.smepro.models.referencekeymap;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.reportsetting;
import com.smepro.models.showupload_pn;
import com.smepro.models.stock;
import com.smepro.models.stock_count;
import com.smepro.models.stock_log;
import com.smepro.models.stock_lot;
import com.smepro.models.sub_processes;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.models.wip;
import com.smepro.models.workflow;
import com.smepro.plugin.RecursiveJoinProcessor;
import com.smepro.repository.Account_ledgerRepository;
import com.smepro.repository.AssetsRepository;
import com.smepro.repository.Attendance_recordRepository;
import com.smepro.repository.Company_annual_planRepository;
import com.smepro.repository.Deduction_recordRepository;
import com.smepro.repository.GamificationGoalRepository;
import com.smepro.repository.General_journalRepository;
import com.smepro.repository.Payroll_recordRepository;
import com.smepro.repository.ReportJoinConfigRepository;
import com.smepro.repository.Shift_recordRepository;
import com.smepro.repository.WorkruleRepository;
import com.smepro.repository.Workrule_leave_logRepository;
import com.smepro.models.trace;
import com.smepro.models.customreport;

import com.smepro.rowmapper.edatamapper4;
import com.smepro.service.CustomReport;
import com.smepro.service.MainService;
import com.smepro.service.PlatformService;
import com.smepro.service.eformservice;
import com.smepro.service.filemanagerservice;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;
import com.smepro.service.workflowservice;
import com.smepro.enums.Traceability;
import com.smepro.enums.WorkruleType;
import com.smepro.enums.eformcolumntype;
import com.smepro.enums.eformsubcolumntype;



@Controller
@SessionAttributes({"Member_name","name","Member_id"})
public class workflowcontroller {

	
	private conditionDAO ConditionDAO;
	private  eformfieldDAO EformfieldDAO;
	private  otherservice otherservices;
	private  workflowDAO WorkflowDAO;
	private  eformservice Eformservice;
	private  eflowDAO EflowDAO;
	private  edataDAO EdataDAO;
	private userDAO UserDAO;
	private userservice Userservice;
	private notificationDAO NotificationDAO;
	private attendanceDAO AttendanceDAO;
	private upload_pnDAO Upload_pnDAO;
	private workflowservice Workflowservice;
	private fileDAO FileDAO;
	private darDAO DarDAO;
	private report_pnDAO Report_pnDAO;
	private taskDAO TaskDAO;
	private groupsDAO GroupsDAO;
	private departmentDAO DepartmentDAO;
	private cmDAO CmDAO;
	private reportDAO ReportDAO;
	
	
	
	private drivesDAO DrivesDAO;
	private folderDAO FolderDAO;
	
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	
	private filemanagerservice Filemanagerservice;
	private  MainService MainServices;

	
	private Payroll_recordRepository payroll_recordRepository;
	private Deduction_recordRepository deduction_recordRepository;
	private Attendance_recordRepository attendance_recordRepository;
	private Shift_recordRepository shift_recordRepository;
	private WorkruleRepository workruleRepository;
	private Workrule_leave_logRepository workrule_leave_logRepository;
	
	
	
	private Account_ledgerRepository account_ledgerRepository;
	private General_journalRepository general_journalRepository;
	private Company_annual_planRepository company_annual_planRepository;
	private AssetsRepository assetsRepository;
	
	private ReportJoinConfigRepository reportJoinConfigRepository;
	
	
	private PlatformService platformService;
    private  GamificationGoalRepository gamificationGoalRepository;
	
	 
	 
	
 	
	
    @Autowired
    public void setMyRepository(conditionDAO ConditionDAO,eformfieldDAO EformfieldDAO
    		,otherservice otherservices,workflowDAO WorkflowDAO,eflowDAO EflowDAO
    		,edataDAO EdataDAO,userDAO UserDAO,userservice Userservice,notificationDAO NotificationDAO
    		,attendanceDAO AttendanceDAO, upload_pnDAO Upload_pnDAO
    		,eformservice Eformservice
    		,workflowservice Workflowservice
    		,fileDAO FileDAO
    		,darDAO DarDAO
    		,report_pnDAO Report_pnDAO
    		,taskDAO TaskDAO
    		,folderDAO FolderDAO
    		,drivesDAO DrivesDAO
    		,filemanagerservice Filemanagerservice
    
    		,datasetDAO DatasetDAO
    		,dataset_dataDAO Dataset_dataDAO
    		,MainService MainServices
    		,groupsDAO GroupsDAO
    		,departmentDAO DepartmentDAO
    		,cmDAO CmDAO
    		,Payroll_recordRepository payroll_recordRepository
    		,Deduction_recordRepository deduction_recordRepository
    		,Attendance_recordRepository attendance_recordRepository
    		,Shift_recordRepository shift_recordRepository
    		,WorkruleRepository workruleRepository
    		,Workrule_leave_logRepository workrule_leave_logRepository
    		,Company_annual_planRepository company_annual_planRepository
			,AssetsRepository assetsRepository
			,ReportJoinConfigRepository reportJoinConfigRepository
			,PlatformService platformService
			,reportDAO ReportDAO
			,GamificationGoalRepository gamificationGoalRepository
    		){
    	


    	
        this.ConditionDAO = ConditionDAO;
        this.EformfieldDAO = EformfieldDAO;
        this.otherservices = otherservices;
        this.WorkflowDAO = WorkflowDAO;
        this.EflowDAO = EflowDAO;
        this.EdataDAO = EdataDAO;
        this.UserDAO = UserDAO;
        this.Userservice = Userservice;
        this.NotificationDAO = NotificationDAO;
        this.AttendanceDAO = AttendanceDAO;
        this.Upload_pnDAO = Upload_pnDAO;
        this.Eformservice = Eformservice;
        this.Workflowservice = Workflowservice;
        this.FileDAO = FileDAO;
        this.DarDAO = DarDAO;
        this.Report_pnDAO = Report_pnDAO;
        this.TaskDAO = TaskDAO;
        
        this.DrivesDAO = DrivesDAO;
        this.FolderDAO = FolderDAO;
        this.Filemanagerservice = Filemanagerservice;
        
        
        this.DatasetDAO = DatasetDAO;
        this.Dataset_dataDAO = Dataset_dataDAO;
        this.MainServices =MainServices; 
        this.GroupsDAO =GroupsDAO; 
        this.DepartmentDAO =DepartmentDAO;  
        this.CmDAO =CmDAO;  
        this.payroll_recordRepository =payroll_recordRepository;  
        
        this.deduction_recordRepository =deduction_recordRepository;  
        this.attendance_recordRepository =attendance_recordRepository;  
        this.shift_recordRepository =shift_recordRepository;  
        this.workruleRepository =workruleRepository;  
        this.workrule_leave_logRepository =workrule_leave_logRepository; 
        
        this.company_annual_planRepository =company_annual_planRepository; 
        this.assetsRepository =assetsRepository; 
        this.reportJoinConfigRepository =reportJoinConfigRepository; 
        this.platformService =platformService; 
        this.ReportDAO =ReportDAO; 
        this.gamificationGoalRepository =gamificationGoalRepository; 
    }

     
	private void System_idoc(ModelMap model) {
		
/*
	 	user users = UserDAO.getbyusername2(Userservice.getLoggedName());   
		List<workflow> workflows = WorkflowDAO.getallApp();
    	model.addAttribute("workflow",workflows); 
    	
    	
    	List<condition> conditions = ConditionDAO.getallGroup();
    	List<showupload_pn> upload_pns = Upload_pnDAO.getallby(users.getUser_name());
    	
    	
   

    	model.addAttribute("conditions",conditions); 
    	AtomicInteger i = new AtomicInteger(1);

    	
    	
  
    	
    	
    	
    	workflows.forEach(row->{
    		
    		System.out.println("row-----> "+row.getWorkflow_id());
    		int e = i.getAndIncrement();
    		List<condition> job =conditions.stream().filter(ft->Integer.valueOf(ft.getCondition_group()) == row.getWorkflow_id()).collect(Collectors.toList());
    
    		job.stream().forEach(jobs->{
    			
    		
    		
    			
    			System.out.println("jobs "+jobs.getCondition_id());
    			
    			 Optional<showupload_pn> Optional = upload_pns.stream()
				            .filter(u-> u.getUpload_pn_monitor() == jobs.getCondition_id() )
				            .findFirst();
    			
    			  if (Optional.isPresent()) {
    				  
    			
    				 
    				  
    				  showupload_pn upload_pnsdata = Optional.get();
    				  jobs.setCondition_name(upload_pnsdata.getUpload_pn_note());
    				  
    				  
			    			 Optional<edata> stepsOptional = edatas.stream()
							            .filter(c-> c.getData_eformid() == jobs.getCondition_id() && c.getData_eformrv()==  jobs.getCondition_rv())
							            .findFirst();
			    			 
			    			 
			    			   if (stepsOptional.isPresent()) {
			    				   edata newstep = stepsOptional.get();
			    					System.out.println("test "+newstep.getData_eformid());
			    				  
			    				
			    					
			    					
			    				   jobs.setAlert(newstep.getAlert() + prize);
			    				   
			    			   }else {
			    				   jobs.setAlert( prize);
			    			   }
			    			   
			    			   
			    		
			    			   jobs.setNew_status(1);
			    			   
			    			   System.out.println("show you "+jobs.getCondition_id());
			    			   
    			  }else {
    				  jobs.setNew_status(0);
    				   System.out.println("not show you "+jobs.getCondition_id());
    			  }
    			   
    		});
    		
    		 Optional<condition> New_status = job.stream()
			            .filter(j -> j.getNew_status() == 1 )
			            .findFirst();
    		 
    		 
    		  	if (New_status.isPresent()) {
		    			model.addAttribute("job"+e,job); 
			    		e++;
		   		  }else {
			   			row.setWorkflow_id(0);
		   		  }
    		
    	});
    
    	
    	
		int dars_add_count = DarDAO.CountFlow1(users.getUser_id());
		 model.addAttribute("dars_add_count",dars_add_count);
		 
		int dars_check_count = DarDAO.CountFlow2(users.getUser_id());
		 model.addAttribute("dars_check_count",dars_check_count);
				
			 
		int dars_app_count = DarDAO.CountFlow3(users.getUser_id());
		model.addAttribute("dars_app_count",dars_app_count);
		
		int rawedatas = EdataDAO.getallCountbyuser(users.getUser_name());
		int pending_count = Report_pnDAO.getPending(users.getUser_name());
		model.addAttribute("pending_count",pending_count +rawedatas); 
		 
		int project_count = TaskDAO.getnumbyProject(users);
		 model.addAttribute("project_count",project_count); 
		 
		 
		 
		 
		 int numproject = TaskDAO.getnumbyProjectNear(users);
		 model.addAttribute("numproject",numproject); 
		 */
		 
    }


	
	
	private void addNotificationAttributes(ModelMap model) {
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    List<notification> notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    int alert = NotificationDAO.countnotification(usersed.getUser_name(), 0);

	    model.addAttribute("notification", notifications);  
	    model.addAttribute("alert", alert);
	    model.addAttribute("Member_name", usersed.getUser_name());
	    model.addAttribute("Member_id",usersed.getUser_id());
	    model.addAttribute("user_img", usersed.getUser_img());
	    
    	System_idoc(model);
	}
	
	
				
	
				
	@RequestMapping(value="/master_product-workflow/{id}",method = RequestMethod.GET)
	public String master_product(ModelMap model,@PathVariable int id) throws IOException {
		
		
	 MainServices.addNotificationAttributes(model);
		 
	    product_master data = EdataDAO.getProduct_master(id);
	    
	    if( data.getProduct_details() != null) {
	    	String formatted = data.getProduct_details().replace("\n", "<br>");
	    	data.setProduct_details(formatted);
	    }
	    
		model.addAttribute("data",data);  
			
		
		 if(data != null) {
			 
			   product_log log = EdataDAO.getProduct_log(data.getId());
			 
			 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		   	 String type ="product";
			int cm_idrefer  = data.getId();
						 
						  
			List<cm> listcms1 = CmDAO.getall(cm_idrefer,type);
			model.addAttribute("listcms1",listcms1);
						  
			List<cm> listcms2 = CmDAO.getall(cm_idrefer,type,date);
			model.addAttribute("listcms2",listcms2);
						  
					      
					      
					      
			model.addAttribute("type",type); 
			model.addAttribute("cm_idrefer",cm_idrefer);  
	
	
		         if(log != null) {
							    List<file> files = FileDAO.getallEdata(log.getEdata_id());
						        model.addAttribute("files",files); 
		         }
		 }		    
		
		
		
		
		
		return "/app/views/master_product";
	}		
				
	
	
	
	@RequestMapping(value="/workflow",method = RequestMethod.GET)
	public String readpdf(ModelMap model) throws IOException {
		
		List<condition> conditions = ConditionDAO.getall();
		model.addAttribute("conditions",conditions); 
		
		List<workflow> workflows = WorkflowDAO.getall();
		model.addAttribute("workflows",workflows); 
		
		List<eflow> eflows =  EflowDAO.getall();
		model.addAttribute("eflows",eflows); 
		
		EflowDAO.clear();
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
        model.addAttribute("name", Userservice.getLoggedName());
        model.addAttribute("Member_name", users.getUser_name());
        List<notification> notifications = NotificationDAO.getallrefer(users.getUser_name());
        int alert = NotificationDAO.countnotification(users.getUser_name(), 0);
        model.addAttribute("notification", notifications);
        model.addAttribute("alert", alert);
   	 MainServices.addNotificationAttributes(model);
		
		return "/eformparts/workflowlist";
	}

	
	
	
	  @RequestMapping(value="/Traceability-workflow",method = RequestMethod.GET)    
	    public String Traceability(ModelMap model){ 
		  
		  
	
	    	List<workflow> workflows = WorkflowDAO.getallEformTracking();
	    	
	   		    	
	    	
	    	
	    	
	    	workflows.forEach(row->{
			 String[] tracking_step = row.getTracking_step().split(",");
			 if(row.getTracking_process_type() != null) {
				 
			 process load_process  = EdataDAO.getProcess(Integer.valueOf(row.getTracking_process_type()));
			 int edata_id = Integer.valueOf(row.getTracking_process_type());	 
			 List<process> data  = new ArrayList<process>();
			 for(String tracking : tracking_step) {
				 List<edata_production_record> process  =  EdataDAO.getTrackingProcess(tracking,edata_id); 
				 data = Filemanagerservice.getTracking(row,model,data,process,tracking);
			 }
			 
			 
			 row.setProcess(data);
			 }
	    	});

	    	model.addAttribute("workflows",workflows);
	    	
	
	    	
	    	/*
	    	
	    	List<workflow> updatedWorkflows = workflows.stream().map(row -> {
	    	    StringBuilder builder = new StringBuilder();
	    	    StringBuilder builder2 = new StringBuilder();

	    	  
	    	    if (row.getWorkflow_key2() != null) {
	    	   
	    	    	
	    	    	
	    			List<condition> stringConditions = ConditionDAO.getAllbylink(row.getWorkflow_id());
	    	        
	    			AtomicInteger i = new AtomicInteger(0);
	    			
	    			stringConditions.forEach(subrow->{
	    				 int status= 0;	
	    			      edata edatas = EdataDAO.getStatus(subrow.getDatalink_idrefer());

		    	            
	    			      if (i.getAndIncrement() != 0) {
		    	                builder.append(",");
		    	                builder2.append(",");
		    	            }

		    	            if ("Good".equals(edatas.getData_status())) {
		    	                status = 2;
		    	            } else if ("Normal".equals(edatas.getData_status())) {
		    	                status = 1;
		    	            } else if ("Bad".equals(edatas.getData_status())) {
		    	                status = -1;
		    	            } else {
		    	                status = 0;
		    	            }

		    	            upload_pn upload_pns = Upload_pnDAO.getbymonitor(subrow.getCondition_id());
		    	            
		    	            builder.append(status);
			    	            if(upload_pns != null) {
			    	            	builder2.append(upload_pns.getUpload_pn_note());
			    	            }else {
			    	            	builder2.append(subrow.getCondition_title());
			    	            }
	    				
	    			});
	    			row.setWorkflow_key1(builder2.toString());
	    	        row.setWorkflow_key4(builder.toString());
	    	    }

	    	    return row;
	    	}).collect(Collectors.toList());
	    	
	    	
	
	    	
	    	
	    	model.addAttribute("updatedWorkflows",updatedWorkflows);
	    	*/
	    	
	    	
	   	 MainServices.addNotificationAttributes(model);
	     	return "/workflow/Traceability";
	    }
	  
	
	  
	  @RequestMapping(value="/stepdetails-workflow/{id}",method = RequestMethod.GET)    
	    public String stepdetails(ModelMap model,@PathVariable int id){ 
		
		  
		  workflow workflows = WorkflowDAO.getbyid(id);
		  List<condition> conditions = ConditionDAO.getAllbylink(workflows.getWorkflow_id());
		  
		  
		  conditions.forEach(row->{
			  upload_pn upload_pns = Upload_pnDAO.getbymonitor(row.getCondition_id());
			   if(upload_pns != null) {
				   row.setCondition_title(upload_pns.getUpload_pn_note());
	            }
		  });
		  
		  
		  
		  model.addAttribute("conditions",conditions); 
		  
		  
		  
		  
		/*
	 		List<control_steps> steps = ConditionDAO.getAllOrder_managements(workflows.getWorkflow_id());
		
		    
		
		    steps.stream().forEach(row->{
		    	
		    	AtomicInteger i = new AtomicInteger(1);
		    	conditions.forEach(subrow->{
		    		
		    		 int currentStep = i.getAndIncrement(); 
		    		
		    		edata data = EdataDAO.getStatus(subrow.getCondition_id(),subrow.getCondition_rv()
		    				 ,row.getProduction_directive(),row.getBatch()); 
		    			
		    		if(data != null) {
		    		
		    			System.out.println(" DATA "+data.getData_status() );
		    			
		    			
		    			try {
		    				String dataStatus = (data.getData_status() != null) ? data.getData_status() : "-";
		    	            Method setStepMethod = row.getClass().getMethod("setStep_" + currentStep, String.class);
		    	            setStepMethod.invoke(row,dataStatus);
		    	        } catch (Exception e) {
		    	            e.printStackTrace();  // Handle any reflection exceptions
		    	        }
		    		
		    		}else {
		    			
		    			try {
		    	            Method setStepMethod = row.getClass().getMethod("setStep_" + currentStep, String.class);
		    	            setStepMethod.invoke(row,"blank");
		    	        } catch (Exception e) {
		    	            e.printStackTrace();  // Handle any reflection exceptions
		    	        }
		    			
		    		}
		    	        
		    	        
		    		
		    	});
		    });
		   
		    model.addAttribute("steps",steps);
		  */ 
	      return "/workflow/Traceability_stepdetails";
	    }
	
	  
	  @RequestMapping(value="/stepdetails2-workflow/{id}",method = RequestMethod.GET)    
	    public String stepdetails2(ModelMap model,@PathVariable int id){ 
		
		    

			 workflow workflows = WorkflowDAO.getbyid(id);

			 if(workflows.getTracking_process_type() != null) {
				 process load_process  = EdataDAO.getProcess(Integer.valueOf(workflows.getTracking_process_type()));
				 
				 List<edata_production_record> record  =  EdataDAO.getTrackingRecord(workflows.getTracking_process_type()); 
				 model.addAttribute("item_batch",record); 
				 
				 if(record.size() == 0) {
					 List<other> other  =  EdataDAO.getTrackingOther(workflows.getTracking_process_type()); 
					 model.addAttribute("item_batch",other); 
				 }
				 
				 
				 
					condition conditions = ConditionDAO.getbyid(load_process.getId_condition());
			    	model.addAttribute("conditions",conditions);  

			    	List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
			    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(load_process.getId());
			    	
			      	
			    	eformfields.stream().forEach(row->{
					    	Optional<keymap> data =	keymaps.stream()
					    			.filter(subrow-> Integer.valueOf(subrow.getEformkey())  == row.getField_id() )
					    			.filter(subrow-> subrow.getType().equals("create"))
					    			.filter(subrow-> !subrow.getEformkey().equals("0") && !subrow.getEformkey().equals("-") )
					    			.findFirst();
					    	
					    	
					    	
					    	
						    	if(data.isPresent()) {
						    		
						    		keymap keymap = data.get();
						    		row.setKeymap(keymap);
						    		
						    		List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(),keymap.getFilter_enum());
						    		row.setDataset_data(dataset_data);
						    		
						    		
						    	}
						    	
						    	
						    	
						    	
			    	}); 
			    	model.addAttribute("eformfields",eformfields);
				 
				 
			 }
			 
			 
			 
			
			 model.addAttribute("workflows",workflows); 
		
		  
	      return "/workflow/Traceability_stepdetails2";
	    }
	  
	  
	  
	  
	  
	 
	  

	  private static Object containsGrouping(edata row, int i) {
	        try {
	            // สร้างชื่อเมธอดตามค่า pd
	            String methodName = "getData_value" + i;
	            Method method = edata.class.getMethod(methodName);
	            return method.invoke(row);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	  
	  
	  
	  private static boolean containsAnyValue(edata row, List<String> values) {
	        if (values.size() <= 1) {
	            // ถ้ามีแค่ 1 ค่าใน List, ให้ตรวจสอบแค่ค่านั้น
	            return values.isEmpty() || containsValue(row, values.get(0));
	        } else {
	            // ถ้ามีมากกว่า 1 ค่าใน List, ต้องตรวจสอบให้ทุกค่าปรากฏในฟิลด์
	            for (String value : values) {
	                if (!containsValue(row, value)) {
	                    return false; // ถ้าค่าใดขาดไป ให้คืนค่า false ทันที
	                }
	            }
	            return true; // มีครบทุกค่า
	        }
	    }
	  
	  
	  
	  
	  private static boolean containsValue(edata row, String value) {
	        for (int i = 1; i <= 20; i++) {
	            try {
	                // ใช้การสะท้อน (Reflection) เพื่อเข้าถึง field data_value1 ถึง data_value20
	                String fieldValue = (String) edata.class.getMethod("getData_value" + i).invoke(row);
	                if (value.equals(fieldValue)) {
	                    return true;
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return false;
	    }
	  
	  
	  
	  @RequestMapping(value="/details-workflow/{id}",method = RequestMethod.GET)    
	    public String detailsworkflow(ModelMap model,@PathVariable int id){ 
		  workflow workflows = WorkflowDAO.getbyid(id);
		  List<condition> conditions = ConditionDAO.getallString(workflows);
		  model.addAttribute("conditions",conditions); 
	      return "/workflow/Traceability_details";
	    }
	  
	  
	  

	  @RequestMapping(value="/trace-workflow/{id}",method = RequestMethod.GET)    
	    public String trace(ModelMap model,@PathVariable int id){ 
		  
		  
		  condition conditions = ConditionDAO.getbyid(id); 
		  upload_pn upload_pns = Upload_pnDAO.getbymonitor(id);
			 MainServices.addNotificationAttributes(model);
		  
		  
		  model.addAttribute("upload_pns",upload_pns);
		  
		  
		  
		  List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
		
		  
		  eformfield product_no = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"product");
		 if(product_no != null) {
			 List<edata> stock = EdataDAO.getallGroupBy(conditions.getCondition_id(),conditions.getCondition_rv(),product_no.getField_postion()); 
			  model.addAttribute("stock",stock);
		 }
		 
		  
		  
		  eformfield lot_no = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"lot");
		  if(lot_no != null) {
			  List<edata> stock_lot = EdataDAO.getallGroupBy(conditions.getCondition_id(),conditions.getCondition_rv(),lot_no.getField_postion());
			  model.addAttribute("stock_lot",stock_lot);
			 }
			 
		

		
		  
		  
		
		 
		  model.addAttribute("eformfields",eformfields);
		  model.addAttribute("id",id);
		  
		
		  System.out.println("stock"+conditions.getCondition_id());
		  System.out.println("stock"+conditions.getCondition_rv());
		  
		  
		  
		  
	      return "/workflow/trace";
	    }
	
	    

	  @RequestMapping(value="/Traceabilitys-workflow",method = RequestMethod.POST)    
	    public String Traceabilitys(
	    		ModelMap model
	    		,@RequestParam(value = "product",required=false)  String product
	    		,@RequestParam(value = "lot",required=false)  String lot
	    		,@RequestParam(value = "date_start",required=false)  String date_start
	    		,@RequestParam(value = "date_end",required=false)  String date_end
	    		,@RequestParam String date_type
	    		,@RequestParam int id
	    		){ 
		  
		  
			 MainServices.addNotificationAttributes(model);
		  
          condition conditions = ConditionDAO.getbyid(id); 		  
		  upload_pn upload_pns = Upload_pnDAO.getbymonitor(id);
		  model.addAttribute("upload_pns",upload_pns);
		  
		  
		  model.addAttribute("date_type",date_type);
		  
		  
		  
		  
		  eformfield product_no = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"product");
		  eformfield lot_nos = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"lot");
		  
		  
		  
		  int productkey_no =0; 
		  int lot_no =0; 
		  if(product_no != null ) { productkey_no = product_no.getField_postion(); }
		  if(lot_nos != null ){ lot_no = lot_nos.getField_postion(); }
		  
		  
		  

		  List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
		 if(product_no != null) {
			 List<edata> stock = EdataDAO.getallGroupBy(conditions.getCondition_id(),conditions.getCondition_rv(),product_no.getField_postion()); 
			  model.addAttribute("stock",stock);
		 }
		
		  if(lot_nos != null) {
			  List<edata> stock_lot = EdataDAO.getallGroupBy(conditions.getCondition_id(),conditions.getCondition_rv(),lot_nos.getField_postion());
			  model.addAttribute("stock_lot",stock_lot);
			 }
		  model.addAttribute("eformfields",eformfields);
		  model.addAttribute("id",id);
		  
		  
		  
		  condition conditions2 = ConditionDAO.getbyids(conditions.getCalculation_port1());
		   	int port1 =0;
	    	int portrv1 =0;
	    	
			if(conditions2 != null) {
				port1 = conditions2.getCondition_id();
				portrv1= conditions2.getCondition_rv();
			}
			condition conditions3 = ConditionDAO.getbyids(conditions.getCalculation_port2());
			int port2 =0;
	    	int portrv2 =0;
	    	if(conditions3 != null) {
	    		port2 = conditions3.getCondition_id();
				portrv2= conditions3.getCondition_rv();
			}
		
		  
		  
		  
		  if(date_type.equals("Transaction")) {
		  
		
		  
		  upload_pn upload_pns2 = Upload_pnDAO.getbymonitor(conditions.getCalculation_port1());
		  model.addAttribute("upload_pns2",upload_pns2);
		  
		  
		  upload_pn upload_pns3 = Upload_pnDAO.getbymonitor(conditions.getCalculation_port2());
		  model.addAttribute("upload_pns3",upload_pns3);
		  
		  
		  
	
		  System.out.println("stock"+conditions.getCondition_id());
		  System.out.println("stock"+conditions.getCondition_rv());
		  
		 
		  eformfield Remaining =  EformfieldDAO.getCalposition("Remaining",conditions.getCondition_id(),conditions.getCondition_rv());
		  eformfield Due =  EformfieldDAO.getCalposition("Due",conditions.getCondition_id(),conditions.getCondition_rv());
		  eformfield Product =  EformfieldDAO.getCalposition("Product",conditions.getCondition_id(),conditions.getCondition_rv());
		  eformfield Lot =  EformfieldDAO.getCalposition("Lot",conditions.getCondition_id(),conditions.getCondition_rv());
		  
		  eformfield Add =  EformfieldDAO.getCalposition("Add",conditions.getCondition_id(),conditions.getCondition_rv());
		  eformfield Del =  EformfieldDAO.getCalposition("Del",conditions.getCondition_id(),conditions.getCondition_rv());
		  eformfield Order =  EformfieldDAO.getCalposition("Order",conditions.getCondition_id(),conditions.getCondition_rv());
		  eformfield Unit =  EformfieldDAO.getCalposition("Unit",conditions.getCondition_id(),conditions.getCondition_rv());
		  
	
		
			  int no = 0;
	    	  int productkey_no1 =0;
	    	  int lot_no1 =0;
	    	  int minus_no1 =0;
	    	  int plus_no1 =0;
	    	  int total_no1 =0;
	    	  int due_no1 =0;
	 
	    	  
	    	  
			if(conditions2 != null) {
				port1 = conditions2.getCondition_id();
				portrv1= conditions2.getCondition_rv();
			
				List<eformfield> mainefield1 =  EformfieldDAO.getall(conditions2.getCondition_id(), conditions2.getCondition_rv());
	    	
	    	  for(eformfield fields : mainefield1) {
	    		  	  no++; 
	    		  	  
	    		  	  
	    		 if(fields.getField_cal_type() != null) { 	  
	    			 
			    		  	if(fields.getField_cal_type().equals("product")) {
			    		  		
			    		  		productkey_no1 = no;
			    		  	}
			    		  	if(fields.getField_cal_type().equals("lot")) {
			    		  		lot_no1 = no;
			    		  		
			    		  	}
			    		  	if(fields.getField_cal_type().equals("minus")) {
			    		  		minus_no1 = no;
			    		  		
			    		  	}
			    		  	if(fields.getField_cal_type().equals("plus")) {
			    		  		plus_no1 = no;
			    		  	}
			    		  	if(fields.getField_cal_type().equals("diff")) {
			    		  		
			    		  		 total_no1 = no;
			    		    	 
			    		  	}
			    		
			    		  	
	    		 }
	    		  	
	    		  	
	    		  	
	    	  }
		  
			}
		  
		  
		  
		  
			
		
			  no = 0;
	    	  int productkey_no2 =0;
	    	  int lot_no2 =0;
	    	  int minus_no2 =0;
	    	  int plus_no2 =0;

	    	  int total_no2 =0;
	    	  int due_no2 =0;
	    	
	    	  
	    	  
			if(conditions3 != null) {
				
				port2 = conditions3.getCondition_id();
				portrv2= conditions3.getCondition_rv();	
				
			List<eformfield> mainefield2 =  EformfieldDAO.getall(conditions3.getCondition_id(), conditions3.getCondition_rv());
	    	
	    	  for(eformfield fields : mainefield2) {
	    		  	  no++; 
	    		  	  
	    		  	  
	    		 if(fields.getField_cal_type() != null) { 	  
	    			 
			    		  	if(fields.getField_cal_type().equals("product")) {
			    		  		
			    		  		productkey_no2 = no;
			    		  	}
			    		  	if(fields.getField_cal_type().equals("lot")) {
			    		  		lot_no2 = no;
			    		  		
			    		  	}
			    		  	if(fields.getField_cal_type().equals("minus")) {
			    		  		minus_no2 = no;
			    		  		
			    		  	}
			    		  	if(fields.getField_cal_type().equals("plus")) {
			    		  		plus_no2 = no;
			    		  	}
			    		  	if(fields.getField_cal_type().equals("diff")) {
			    		  		
			    		  		 total_no2 = no;
			    		    	 
			    		  	}
			    		 
			    		  	
	    		 }
	    		  	
	    		  	
	    		  	
	    	  }
		  
			}
		  
	
	     model.addAttribute("minus_no1",minus_no1);
	     model.addAttribute("plus_no1",plus_no1);
	     model.addAttribute("minus_no2",minus_no2);
	     model.addAttribute("plus_no2",plus_no2);
	     
	     
	     model.addAttribute("due_no1",due_no1);
	     model.addAttribute("total_no1",total_no1);
	     model.addAttribute("due_no2",due_no2);
	     model.addAttribute("total_no2",total_no2);
	     
	     
	     
		 model.addAttribute("Remaining",Remaining);
		 model.addAttribute("Due",Due);
		 model.addAttribute("Add",Add);
		 model.addAttribute("Del",Del);
		 model.addAttribute("Order",Order);
		 model.addAttribute("Unit",Unit);
		 
		 
	     model.addAttribute("port1",conditions.getCalculation_port1());
	     model.addAttribute("port2",conditions.getCalculation_port2());
	     
	     
	     

	     
		  List<edata> data = EdataDAO.getallTrace(
				  product,lot
				  ,date_start,date_end
				  ,port1,portrv1,port2,portrv2
				  ,productkey_no1,lot_no1,productkey_no2,lot_no2
				  );
		  
		  
			System.out.println("data size " +data.size());
		
		  model.addAttribute("data",data);
		  
		  System.out.println("id" + id);
		  

		  
		 
		  
		  
			model.addAttribute("productkey_no",productkey_no);
			model.addAttribute("lot_no",lot_no);
			model.addAttribute("date_start",date_start);
			model.addAttribute("date_end",date_end);
		  
			
			
			
			edata maindata = EdataDAO.getTrace(product,lot,productkey_no,lot_no,conditions.getCondition_id(), conditions.getCondition_rv());
			
			eformfield balance = null;
			if(maindata != null) {
				balance = EformfieldDAO.getByField_cal_type(maindata.getData_eformid(),maindata.getData_eformrv(),"balance");
			}
			if(balance != null) {
				  edata Maxbalanc = EdataDAO.getMaxbalance(maindata.getData_id(),balance.getField_postion());
				  model.addAttribute("Maxbalanc",Maxbalanc);
			 }
			
			 int receiveproduct_no = 0;
			 int receivelot_no = 0;
			 int receive_no = 0;
			 
			
			 
		
			
			int port = port1 > 0 ? port1 : port2;
			int portrv = port1 > 0 ? portrv1 : portrv2;
			

			
			 eformfield receiveproduct = EformfieldDAO.getByField_cal_type2(port,portrv,0,0,"product_received");
			 eformfield receivelot = EformfieldDAO.getByField_cal_type2(port,portrv,0,0,"lot_received");
			 eformfield receive = EformfieldDAO.getByField_cal_type2(port,portrv,0,0,"received");
			 
			if(receiveproduct != null ) { receiveproduct_no =receiveproduct.getField_postion(); }
			if(receivelot != null ) { receivelot_no =receivelot.getField_postion(); }
			if(receive != null ) { receive_no =receive.getField_postion(); }
			
			
			 
				System.out.println("receiveproduct_no "+receiveproduct_no);
				System.out.println("receivelot_no "+receivelot_no);
				System.out.println("receive_no "+receive_no);
			
			
			map_key map_keys  =  ConditionDAO.getMap_key(port);
			System.out.println("port "+port);
		
			
			if(map_keys != null ) {
				
				condition condition_received =  ConditionDAO.getbyid(map_keys.getMap_key_id_sub());
				eformfield use_received = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"use_received");
				
			
				
				if(use_received != null) {
				List<edata> received = new ArrayList<>();
				
				List<edata> received1 = EdataDAO.getReceivedtransaction(
						product,lot
						,receiveproduct_no,receivelot_no,receive_no
						,port,portrv
						);
			
				
				
				
				receiveproduct = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"product_received");
				 receivelot = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"lot_received");
				 receive = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"received");
				 
				if(receiveproduct != null ) { receiveproduct_no =receiveproduct.getField_postion(); }
				if(receivelot != null ) { receivelot_no =receivelot.getField_postion(); }
				if(receive != null ) { receive_no =receive.getField_postion(); }
				
				
				List<edata> use_received2 = EdataDAO.getReceivedtransaction(
							product,lot
							,receiveproduct_no,receivelot_no,use_received.getField_postion()
							,condition_received.getCondition_id(),condition_received.getCondition_rv()
							);
					
				
			if(received1 != null) {		
				for(edata  row: received1) {
					
					
					
					  edata edatas = new edata();
					  edatas.setData_id(row.getData_id());
					  edatas.setData_department(row.getData_department());
					  edatas.setData_value1(row.getData_value1());
					  edatas.setData_due(row.getData_due());
					  System.out.println("edatas "+row.getData_value1());
					  System.out.println("edatas "+row.getData_department());
					  
					if(row.getData_department() != null) {
						Optional<edata> energy = use_received2.stream()
							    .filter(datas -> Objects.nonNull(datas.getData_department()) && datas.getData_department().equals(row.getData_department()))
							    .findFirst();
						if (energy.isPresent()) { 
							 System.out.println("edatas "+row.getData_department());
							
							edata singleEnergy = energy.get();
							edatas.setData_value2(singleEnergy.getData_value1());
						}
					}

					  received.add(edatas);
					
				}
				model.addAttribute("received",received);
			}
			
				
				
				}else {
					
					List<edata> received = EdataDAO.getReceivedtransaction(
							product,lot
							,receiveproduct_no,receivelot_no,receive_no
							,port,portrv
							);
					model.addAttribute("received",received);
					model.addAttribute("map",0);
				}
				
				model.addAttribute("map",1);
					
			
			}else {
				
				List<edata> received = EdataDAO.getReceivedtransaction(
						product,lot
						,receiveproduct_no,receivelot_no,receive_no
						,port,portrv
						);
				model.addAttribute("received",received);
				model.addAttribute("map",0);
			}
			
			
			
			
			
			
			
		
			model.addAttribute("receive_no",receive_no);
			
			
			if(maindata  == null ){
			model.addAttribute("error","No data....");
			}
			model.addAttribute("maindata",maindata);
			
			
			
			
			
		  
	  }else { 
		  
		  
	
		  
		  
		edata maindata = EdataDAO.getTrace(product,lot,productkey_no,lot_no,conditions.getCondition_id(), conditions.getCondition_rv());
		
		eformfield balance = null;
		if(maindata != null) {
				balance = EformfieldDAO.getByField_cal_type(maindata.getData_eformid(),maindata.getData_eformrv(),"balance");
		}
		
		  if(balance != null) {
			  edata Maxbalanc = EdataDAO.getMaxbalance(maindata.getData_id(),balance.getField_postion());
			  model.addAttribute("Maxbalanc",Maxbalanc);
		  }
		
		 int receiveproduct_no = 0;
		 int receivelot_no = 0;
		 int receive_no = 0;
		 
		
		 
	
		
		int port = port1 > 0 ? port1 : port2;
		int portrv = port1 > 0 ? portrv1 : portrv2;
		

		
		 eformfield receiveproduct = EformfieldDAO.getByField_cal_type2(port,portrv,0,0,"product_received");
		 eformfield receivelot = EformfieldDAO.getByField_cal_type2(port,portrv,0,0,"lot_received");
		 eformfield receive = EformfieldDAO.getByField_cal_type2(port,portrv,0,0,"received");
		 
		if(receiveproduct != null ) { receiveproduct_no =receiveproduct.getField_postion(); }
		if(receivelot != null ) { receivelot_no =receivelot.getField_postion(); }
		if(receive != null ) { receive_no =receive.getField_postion(); }
		
		
		 
			System.out.println("receiveproduct_no "+receiveproduct_no);
			System.out.println("receivelot_no "+receivelot_no);
			System.out.println("receive_no "+receive_no);
		
		
		map_key map_keys  =  ConditionDAO.getMap_key(port);
		System.out.println("port "+port);
	
		
		if(map_keys != null ) {
			
			condition condition_received =  ConditionDAO.getbyid(map_keys.getMap_key_id_sub());
			eformfield use_received = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"use_received");
			
		
			
			if(use_received != null) {
			List<edata> received = new ArrayList<>();
			
			List<edata> received1 = EdataDAO.getReceived(
					product,lot
					,receiveproduct_no,receivelot_no,receive_no
					,port,portrv
					);
		
			
			
			
			receiveproduct = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"product_received");
			 receivelot = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"lot_received");
			 receive = EformfieldDAO.getByField_cal_type2(condition_received.getCondition_id(),condition_received.getCondition_rv(),0,0,"received");
			 
			if(receiveproduct != null ) { receiveproduct_no =receiveproduct.getField_postion(); }
			if(receivelot != null ) { receivelot_no =receivelot.getField_postion(); }
			if(receive != null ) { receive_no =receive.getField_postion(); }
			
			
			List<edata> use_received2 = EdataDAO.getReceived(
						product,lot
						,receiveproduct_no,receivelot_no,use_received.getField_postion()
						,condition_received.getCondition_id(),condition_received.getCondition_rv()
						);
				
			
		if(received1 != null) {		
			for(edata  row: received1) {
				
				
				
				  edata edatas = new edata();
				  edatas.setData_id(row.getData_id());
				  edatas.setData_department(row.getData_department());
				  edatas.setData_value1(row.getData_value1());
				  
				  System.out.println("edatas "+row.getData_value1());
				  System.out.println("edatas "+row.getData_department());
				  
				if(row.getData_department() != null) {
					Optional<edata> energy = use_received2.stream()
						    .filter(data -> Objects.nonNull(data.getData_department()) && data.getData_department().equals(row.getData_department()))
						    .findFirst();
					if (energy.isPresent()) { 
						 System.out.println("edatas "+row.getData_department());
						
						edata singleEnergy = energy.get();
						edatas.setData_value2(singleEnergy.getData_value1());
					}
				}

				  received.add(edatas);
				
			}
			model.addAttribute("received",received);
		}
		
			
			
			}else {
				
				List<edata> received = EdataDAO.getReceived(
						product,lot
						,receiveproduct_no,receivelot_no,receive_no
						,port,portrv
						);
				model.addAttribute("received",received);
				model.addAttribute("map",0);
			}
			
			model.addAttribute("map",1);
				
		
		}else {
			
			List<edata> received = EdataDAO.getReceived(
					product,lot
					,receiveproduct_no,receivelot_no,receive_no
					,port,portrv
					);
			model.addAttribute("received",received);
			model.addAttribute("map",0);
		}
		
		
		
		
		
		
		
	
		model.addAttribute("receive_no",receive_no);
		
		
		if(maindata  == null ){
		model.addAttribute("error","No data....");
		}
		model.addAttribute("maindata",maindata);
		
			}
		
		
		  
	
		
		
		
	      return "/workflow/trace";
	    }
	
	  
	  
	    @RequestMapping(value="/datalink-workflow",method = RequestMethod.GET)    
	    public String datalink(ModelMap model){ 
	    	
	   	 MainServices.addNotificationAttributes(model);
	    	
	    	List<workflow> workflows = WorkflowDAO.getall(0);
	    	model.addAttribute("workflow",workflows); 
	    	return "/workflow/main_datalink";
	    	
	    }
	    
	    
	    
	    @RequestMapping(value="/workflowsystem-workflow",method = RequestMethod.GET)    
	    public String workflowsystem(ModelMap model){ 
	    	
	   	 	MainServices.addNotificationAttributes(model);
	    	List<workflow> workflows = WorkflowDAO.getall(1);
	    	model.addAttribute("workflow",workflows); 
	    	return "/workflow/main_workflowsystem";
	    	
	    }
	    
	    
	    
	    
	    
	    
	    @RequestMapping(value="/datalinktraceability-workflow",method = RequestMethod.GET)    
	    public String datalinktraceability(ModelMap model){ 
	    	
	   	 MainServices.addNotificationAttributes(model);
	    	
	    	List<workflow> workflows = WorkflowDAO.getallTraceability();
	    	model.addAttribute("workflow",workflows); 
	    	return "/workflow/main_datalink2";
	    	
	    }
	    
	    
	   
	
	
	@RequestMapping(value="/neweflow-workflow",method = RequestMethod.GET)
	public String neweflow(ModelMap model) throws IOException {
		 MainServices.addNotificationAttributes(model);
		workflow workflows = new workflow();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime today = LocalDateTime.now();
		String date = today.format(formatter);
		
		workflows.setWorkflow_dateadd(date);
		workflows.setWorkflow_note("0");
		
		int id = WorkflowDAO.save(workflows);
	    return "redirect:/setting-workflow/"+id;
		
	}
	@RequestMapping(value="/neweflow2-workflow",method = RequestMethod.POST)
	public String neweflow2(
			@RequestParam String workflow_name,
			@RequestParam String workflow_app
			) {

		
		
		workflow workflows = new workflow();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime today = LocalDateTime.now();
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		String date = today.format(formatter);
		
		String workflow_key1 = "";
		
		
		switch (workflow_app) {
        case "Purchasing system":
        	workflow_key1 =  "";
            break;  
        case "Sub-drawdown system":
           	workflow_key1 =  "petty_cash_book";
        	
             break;         
        case "Withdrawal-clearance system":
         	workflow_key1 =  "requisition_form";
             break;         
        case "Stock Requisition":
        	workflow_key1 =  "inventory_registration,direct_goods_receipt,stock_requisition";
             break;   
        case "General system":
         	workflow_key1 =  "";
             break;   
        default:
            throw new IllegalArgumentException("Invalid datalink name: ");
		}	
		

		workflows.setWorkflow_type(1);
		workflows.setWorkflow_dateadd(date);
		workflows.setWorkflow_note("0");
		workflows.setWorkflow_idadd(usersed.getUser_id());
		workflows.setWorkflow_app(workflow_app);
		workflows.setWorkflow_key1(workflow_key1);
		workflows.setWorkflow_name(workflow_name);
		
		
		
		int id = WorkflowDAO.save(workflows);
	    return "redirect:/setting2-workflow/"+id;
		
	}

	@RequestMapping(value="/add-workflow",method = RequestMethod.GET)
	public String addworkflow(ModelMap model) throws IOException {
		 MainServices.addNotificationAttributes(model);
		int id = (int)otherservices.generateRandomnumber(9);
		 model.addAttribute("id",id); 
		return "/eformparts/addworkflow";
		
	}

	    
	@RequestMapping(value="/addkey-workflow/{formid}/{subkey}",method = RequestMethod.GET)
	public String addkey(ModelMap model,@RequestParam int id,@PathVariable int formid,@PathVariable int subkey) throws IOException {
	
		
		
		 MainServices.addNotificationAttributes(model);
		
		 eflow eflows = EflowDAO.getbyid(subkey);
		 if(eflows != null) {
			 
			 System.out.println("getEflow_key1 " + eflows.getEflow_key3());
			 
			 
			 if(eflows.getEflow_key1() != null) {
			 model.addAttribute("key1",eflows.getEflow_key1().isEmpty() ? "-": eflows.getEflow_key1());  
			 }
			 if(eflows.getEflow_key2() != null) {
				 model.addAttribute("key2",eflows.getEflow_key2().isEmpty()  ? "-": eflows.getEflow_key2());   
			 }
			 if(eflows.getEflow_key3() != null) {
				 model.addAttribute("key3",eflows.getEflow_key3().isEmpty()  ? "-": eflows.getEflow_key3());    
			 } 
			 if(eflows.getEflow_key4() != null) {
				 model.addAttribute("key4",eflows.getEflow_key4().isEmpty()  ? "-": eflows.getEflow_key4());    
			 }
			

			 
		 }else {
			 model.addAttribute("key1","-");  
			 model.addAttribute("key2","-");  
			 model.addAttribute("key3","-"); 
			 model.addAttribute("key4","-"); 
		 }
		 
	
		 
		 List<eformfield> eformfields = EformfieldDAO.getall(id, 0); 
		 model.addAttribute("eformfields",eformfields);  
		 
		 System.out.println("formid" + formid);
		 
		 workflow workflows = WorkflowDAO.getbyid(formid);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id);  
		 model.addAttribute("subkey",subkey);  
		 
		 
		 
		 
		return "/eformparts/addkey";
		
	}
	
	
	@RequestMapping(value="/del-workflow/{id}",method = RequestMethod.GET)
	public String delworkflow(@PathVariable int id){

		 WorkflowDAO.delete(id);
		 
		 
		 
		 WorkflowDAO.delinkall(id);
		 return "redirect:/datalink-workflow";
	}
	    
	@RequestMapping(value="/del2-workflow/{id}",method = RequestMethod.GET)
	public String delworkflow2(@PathVariable int id){

		 WorkflowDAO.delete(id);
		 
		 
		 
		 WorkflowDAO.delinkall(id);
		 return "redirect:/workflowsystem-workflow";
	}
	 
	@RequestMapping(value="/addsubkey-workflow",method = RequestMethod.GET)
	public String addsubkey(ModelMap model,@RequestParam int id) throws IOException {
		 MainServices.addNotificationAttributes(model);
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		return "/eformparts/workflows";
		
	}
	    
	
	@RequestMapping(value="/view-workflow/{id}/{type}",method = RequestMethod.GET)
	public String view(
			ModelMap model,
			@PathVariable int id,
			@PathVariable int type,
			HttpSession session,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 search_workflowDTO  searchs =  (search_workflowDTO) session.getAttribute("searchs");
		 search_workflowDTO  search =  (search_workflowDTO) session.getAttribute("search");
		 search.setLimit(1000);
		
		 
		 
		  condition conditions = ConditionDAO.getbyid(id);
		  
              
		  if(type == 1) {
			  List<edata>  edatas =  EdataDAO.material(conditions.getCondition_id(),conditions.getCondition_rv(),search);  
			  model.addAttribute("edatas",edatas); 
		  }
		  else{
			  List<edata>  edatas =  EdataDAO.production(conditions.getCondition_id(),conditions.getCondition_rv(),search);  
			  model.addAttribute("edatas",edatas); 
		  }  
			  

		  
		  
		  
		  
		  model.addAttribute("conditions",conditions); 
		  model.addAttribute("search",search); 
		  model.addAttribute("searchs",searchs);
		  
			return "/workflow/datalink";
		
	}
	   
	
	
	@RequestMapping(value="/viewreport-workflow/{id}",method = RequestMethod.GET)
	public String viewreport(
			ModelMap model,
			@PathVariable int id)
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 model.addAttribute("id",id);  
			return "/workflow/viewreport";
		
	}
	   
	@RequestMapping(value="/viewreports-workflow/{work_id}/{id}",method = RequestMethod.GET)
	public String viewreport(
			ModelMap model,
			@PathVariable int work_id,
			@PathVariable int id)
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 model.addAttribute("id",id);  
			return "/workflow/report";
		
	}
	
	@RequestMapping(value="/setting-workflow/{id}",method = RequestMethod.GET)
	public String settingworkflow(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  
		  
		List<condition> flow = ConditionDAO.getAllbylink(id);
		  
		
		  
		flow.stream().forEach(row->{
			  
		List<eformfield> eformfield =  eformfields.stream()
			  .filter(sub-> sub.getField_formid() == row.getCondition_id() )
			  .filter(sub-> sub.getField_formrv() == row.getCondition_rv() ).collect(Collectors.toList());
				row.setEformfields(eformfield);
			  
		  });
		  
		
		model.addAttribute("flow",flow); 
	  
		return "/workflow/settingtestback";
		
	}
	   
	@RequestMapping(value="/setting2-workflow/{id}",method = RequestMethod.GET)
	public String settingworkflow2(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 
		 String day = "TUESDAY";

	 
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  AtomicReference<Integer> notice = new AtomicReference<>(0);
		  
		  List<condition> flow = ConditionDAO.getAllbylink(id);

		  flow.stream().forEach(row -> {
			  
			  
			 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(row.getDatalink_idrefer());
			 
			 System.err.println("idrefer "+row.getDatalink_idrefer());
			 System.err.println("upload_pn "+upload_pn.getUpload_pn_id());
			 
			 
			 if(upload_pn.getUpload_pn_id() == 0) {
				 notice.set(notice.get() + 1);
			 }
			 
			 
		      List<eformfield> eformfield = eformfields.stream()
		          .filter(sub -> sub.getField_formid() == row.getCondition_id())
		          .filter(sub -> sub.getField_formrv() == row.getCondition_rv())
		          .collect(Collectors.toList());
		      row.setEformfields(eformfield);
		  });

		  int error = validateDatalinkName(flow);

		  model.addAttribute("notice",notice.get()); 
		  model.addAttribute("error",error); 
		  model.addAttribute("flow",flow); 

		return "/workflow/cplatform";
	}
	
	
	
	
	@RequestMapping(value="/settingapp-workflow/{id}",method = RequestMethod.GET)
	public String settingapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 
		 
		 List<workflow> workflow_all = WorkflowDAO.getalls(workflows.getWorkflow_id());
		 model.addAttribute("workflow_all",workflow_all);  
		 
		 
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  AtomicReference<Integer> notice = new AtomicReference<>(0);
		  
		  List<condition> flow = ConditionDAO.getAllbylink(id);

		  flow.stream().forEach(row -> {
			  
			  
			 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(row.getDatalink_idrefer());
			 
			 System.err.println("idrefer "+row.getDatalink_idrefer());
			 System.err.println("upload_pn "+upload_pn.getUpload_pn_id());
			 
			 
			 if(upload_pn.getUpload_pn_id() == 0) {
				 notice.set(notice.get() + 1);
			 }
			 
			 
		      List<eformfield> eformfield = eformfields.stream()
		          .filter(sub -> sub.getField_formid() == row.getCondition_id())
		          .filter(sub -> sub.getField_formrv() == row.getCondition_rv())
		          .collect(Collectors.toList());
		      row.setEformfields(eformfield);
		  });

		  int error = validateDatalinkName(flow);

		  
		  
		  List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		  
		  
		  
		  user creator = UserDAO.getbyId(workflows.getWorkflow_idadd(),0);
		  model.addAttribute("creator",creator); 
		  
		  Filemanagerservice.getRoot(model);
		  
		  
		  
		  
		  List<DB> database = Arrays.stream(DB.values())
				    .sorted(Comparator.comparing(Enum::name))
				    .collect(Collectors.toList());
			model.addAttribute("database", database);
		  
		  
		  
		  model.addAttribute("notice",notice.get()); 
		  model.addAttribute("error",error); 
		  model.addAttribute("flow",flow); 
	  
		return "/workflow/settingapp";
	}
	
	
	
	
	@RequestMapping(value="/logapp-workflow/{id}",method = RequestMethod.GET)
	public String logapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 List<edata> data  =  EdataDAO.getallworkflow(id);
		 
		 
		    List<edata> completionData = data.stream()
	                .filter(item -> "Completion".equals(item.getCurrent_step()))  // ฟิลเตอร์กรอง "Completion"
	                .collect(Collectors.toList());

	        // กรองข้อมูลที่ current_step != "Completion"
	        List<edata> nonCompletionData = data.stream()
	                .filter(item -> !"Completion".equals(item.getCurrent_step()))  // ฟิลเตอร์กรองที่ไม่ใช่ "Completion"
	                .collect(Collectors.toList());
		 
		 model.addAttribute("data1",nonCompletionData);  
		 model.addAttribute("data2",completionData);  
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
	  
		return "/workflow/logapp";
	}
	
	
	@RequestMapping(value="/monitoringapp-workflow/{id}",method = RequestMethod.GET)
	public String monitoringapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 List<edata> data  =  EdataDAO.getallworkflow(id);
		 
		 
		    List<edata> completionData = data.stream()
	                .filter(item -> "Completion".equals(item.getCurrent_step()))  // ฟิลเตอร์กรอง "Completion"
	                .collect(Collectors.toList());

	        // กรองข้อมูลที่ current_step != "Completion"
	        List<edata> nonCompletionData = data.stream()
	                .filter(item -> !"Completion".equals(item.getCurrent_step()))  // ฟิลเตอร์กรองที่ไม่ใช่ "Completion"
	                .collect(Collectors.toList());
		 
		 model.addAttribute("data1",nonCompletionData);  
		 model.addAttribute("data2",completionData);  
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
	  
		return "/workflow/monitoringapp";
	}
	
	
	@RequestMapping(value="/trackingapp-workflow/{id}",method = RequestMethod.GET)
	public String trackingapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 String version = System.getProperty("java.version");
	        System.out.println("Java version is: " + version);
		
		
		 MainServices.addNotificationAttributes(model);
		 
		 
	
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 String[] tracking_step = workflows.getTracking_step().split(",");
		 List<process> data  = new ArrayList<process>();
		 
		if(workflows.getTracking_process_type() != null) {
		
			 int edata_id = Integer.valueOf(workflows.getTracking_process_type());	
			 for(String tracking : tracking_step) {
				 List<edata_production_record> process  =  EdataDAO.getTrackingProcess(tracking,edata_id); 
				 Filemanagerservice.getTracking(workflows,model,data,process,tracking);
			 }	 
		}
		
		
		 model.addAttribute("data",data);  
		 model.addAttribute("workflows",workflows);  
		 
		 
		 
		 
		 workflow main_workflows = WorkflowDAO.getbyid(id);
		 String[] tracking_main_step = main_workflows.getTracking_main_step().split(",");
		 List<process> main_data  = new ArrayList<process>();
		 


	
		 Filemanagerservice.getMainTracking(workflows,model,main_data,null,tracking_main_step);
		

		
		 model.addAttribute("main_data",main_data);  
		 model.addAttribute("workflows",workflows);  
		 
		 
		 
		 
		 
		 
		 
		 
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
	  
		return "/workflow/trackingapp";
	}
	
	
	@RequestMapping(value="/customreport-workflow/{id}",method = RequestMethod.GET)
	public String customreport(
			ModelMap model,
			@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 
		 
		 List<process> data  = EdataDAO.getAllProcess(id);
		 
		 
		 model.addAttribute("data",data);  
		 model.addAttribute("workflows",workflows);  
		 
		 List<customreport> customreports =  WorkflowDAO.getAllCustomreport(id);
		 model.addAttribute("customreports",customreports);  
		 
		return "/workflow/customreport";
	}
	
	
	
	@RequestMapping(value="/mainreport-workflow/{id}",method = RequestMethod.GET)
	public String mainreport(
			ModelMap model,
			@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 List<process> data  = EdataDAO.getAllProcess(id);
		 model.addAttribute("data",data);  
		 model.addAttribute("workflows",workflows);  
		 
		 List<customreport> customreports =  WorkflowDAO.getAllCustomreport(id);
		 model.addAttribute("customreports",customreports);  
		 
		return "/workflow/mainreport";
	}
	
	
	
	
	
	@RequestMapping(value="/registrationmainreport-workflow/{id}",method = RequestMethod.GET)
	public String registrationmainreport(
			ModelMap model,
			@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 List<process> data  = EdataDAO.getAllProcess(id);
		 model.addAttribute("data",data);  
		 model.addAttribute("workflows",workflows);  
		 
		 List<customreport> customreports =  WorkflowDAO.getAllCustomreport(id);
		 model.addAttribute("customreports",customreports);  
		 
		 
		 dataset datasets  = DatasetDAO.getMain("Register_Type");
		 model.addAttribute("datasets",datasets);  
		 
		 
		 List<dataset_data> dataset_data = Dataset_dataDAO.getall(datasets.getDataset_id());

		 dataset_data.forEach(row->{
			 
			 int count  = EdataDAO.NumRegister(row.getDataset_data_title());
			 row.setDataset_data_id_refer(count);
			 
		 });
		 
		 model.addAttribute("dataset_data",dataset_data);  
		 
		 
		 
		return "/Subdata/mainreport_registration";
	}
	
	
	@RequestMapping(value="/registration-workflow/{id}/{dataset_data_id}",method = RequestMethod.GET)
	public String registrationview(
			ModelMap model
			,@PathVariable int id
			,@PathVariable int dataset_data_id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 List<process> data  = EdataDAO.getAllProcess(id);
		 model.addAttribute("data",data);  
		 model.addAttribute("workflows",workflows);  
		 
		 List<customreport> customreports =  WorkflowDAO.getAllCustomreport(id);
		 model.addAttribute("customreports",customreports);  
		 
		 
		 dataset datasets  = DatasetDAO.getMain("Register_Type");
		 model.addAttribute("datasets",datasets);  
		 
		 
		dataset_data dataset_data = Dataset_dataDAO.getbyid(dataset_data_id);
		List<registers> item = EdataDAO.getAllRegister(dataset_data.getDataset_data_title());
		
		model.addAttribute("item",item);  
		 
		 
		 
		return "/Subdata/registration";
	}
	
	
	
	@RequestMapping(value="/fg-workflow/{id}",method = RequestMethod.GET)
	public String viewfg(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 product_master data = EdataDAO.getProduct_master(id);
		model.addAttribute("data",data);  
			
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
		
		if(data != null ) {
			
			
		    product_log stock_log = EdataDAO.getProduct_log(data.getId());
		    if(stock_log != null) {
		    	List<String>img  = new ArrayList<String>(Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp"));
			    List<file> files = FileDAO.getallEdata(stock_log.getEdata_id());
		        model.addAttribute("files",files); 
		        
		        List<file> ex_files = files.stream().filter(rows-> img.contains(rows.getFile_type())).limit(5).collect(Collectors.toList());
		        model.addAttribute("ex_files",ex_files); 
		    }

			   if( data.getProduct_details() != null) {
			    	String formatted = data.getProduct_details().replace("\n", "<br>");
			    	data.setProduct_details(formatted);
			    }
			   
			   String key;
			    if (data.getProduct_code() != null) {
			        key = data.getProduct_code() + data.getProduct_daterun() + data.getProduct_id();
			    } else {
			        key = data.getProduct_daterun() + data.getProduct_id();
			    }
			    
			   List<Fg> fg =  EdataDAO.getAllFg(key);
			   model.addAttribute("fg",fg);
				
			   
			   
			   
				  

				    List<Adjustment_fg> adjustment_fg = EdataDAO.GetAFGSearch(key);
				    adjustment_fg.stream().forEach(row->{
				    	  List<file> subfiles = FileDAO.getallEdata(row.getEdata_id());  
				    	  row.setFile(subfiles);
				    	  
				    });
				    
				  
					 model.addAttribute("adjustment_fg",adjustment_fg); 
					 

					 String type ="product";
					 int cm_idrefer  = data.getId();
					 
					  
				      List<cm> listcms1 = CmDAO.getall(cm_idrefer,type);
				      model.addAttribute("listcms1",listcms1);
					  
					  List<cm> listcms2 = CmDAO.getall(cm_idrefer,type,date);
				      model.addAttribute("listcms2",listcms2);
					  
				      
				      
				      
					  model.addAttribute("type",type); 
					   model.addAttribute("cm_idrefer",cm_idrefer);  
					   
					   

					  List<production_list> production_list =  EdataDAO.getAllPL(key);
					  
					  production_list.forEach(row->{
						  
						  List<production_directive> production_directive =  EdataDAO.getAllPD(row.getPl_code());
						  row.setPd(production_directive);
					  });
					  
					  
					  model.addAttribute("production_list",production_list); 
					   
					  
					  
					  
					  
		    }
	
		 
		 List<process> process =  EdataDAO.findAllProcessAllowLooseLink();
		 process.stream().forEach(row->{
			 condition condition = ConditionDAO.getbyid(row.getId_condition());
			 List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
			 row.setEformfields(eformfields);
		 });
		 model.addAttribute("process",process);
	
		 model.addAttribute("LooseLinkByType","fg");
		 List<Looselink> Looselink =  EdataDAO.findAllLooseLinkByTypeAndTo("fg",id);
		 
		 model.addAttribute("key1",data.getProduct_names());
		 model.addAttribute("key2",data.getProduct_category());
		 
		 

		 
		 Looselink.stream().limit(10).forEach(row->{
			 
			 eformfield row1 = EformfieldDAO.getbycal(row.getLoose_link_scope1());
			 eformfield row2 = EformfieldDAO.getbycal(row.getLoose_link_scope2());
			 
			 if(row1 != null) { row.setLoose_link_scopename1(row1.getField_label()) ; }
			 if(row2 != null) { row.setLoose_link_scopename2(row2.getField_label()) ;}
		 });
		 
		 
		 model.addAttribute("Looselink",Looselink);
			
		return "/app/views/view_fg";
	}
	

	
	@PostMapping(value="/setdatausage-workflow")
	public String setdatausage(Looselink Looselinks,RedirectAttributes redirectAttributes) {
		
		 EdataDAO.saveLooseLink(Looselinks);
		 redirectAttributes.addFlashAttribute("TAB", "LooseLink");
		 
		return "redirect:/sk-workflow/"+Looselinks.getLoose_link_to();
	}

	
	

	
	
	@RequestMapping(value="/sk-workflow/{id}",method = RequestMethod.GET)
	public String viewsk(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 stock data = EdataDAO.getSk(id);
		model.addAttribute("data",data);  
			
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
		
		if(data != null ) {
			
			
			String details = (data.getStock_details() != null) ? data.getStock_details().replace("<br>", "\n") : "";
		    model.addAttribute("details",details);  
						
		    stock_log stock_log = EdataDAO.getStock_log(data.getId());
		    List<String>img  = new ArrayList<String>(Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp"));
		    
				    if(stock_log != null) {
					    List<file> files = FileDAO.getallEdata(stock_log.getEdata_id());
				        model.addAttribute("files",files); 
				        
				        List<file> ex_files = files.stream().filter(rows-> img.contains(rows.getFile_type())).limit(5).collect(Collectors.toList());
				        model.addAttribute("ex_files",ex_files); 
				    }
				    
				    
				    String key;
				    if (data.getStock_code() != null) {
				        key = data.getStock_code() + data.getStock_receipt_date() + data.getStock_id();
				    } else {
				        key = data.getStock_receipt_date() + data.getStock_id();
				    }
				    List<stock_lot> substock =  EdataDAO.getAllSk(key);	   
				    
				    
				    Double totalRemaining = substock.stream()
				    		.filter(row-> row.getStock_status().equals("IN_STOCK"))
				    	    .mapToDouble(stock_lot::getStock_remaining)
				    	    .sum();
				    
				    Double totalPrice = substock.stream()
				    		.filter(row-> row.getStock_status().equals("IN_STOCK"))
				    		.filter(row-> otherservices.DoubleValueOf(row.getPrice())  > 0)
				    	    .mapToDouble(row-> otherservices.DoubleValueOf(row.getPrice()))
				    	    .average().orElse(0.0);
				    
				    
				    model.addAttribute("substock",substock); 
				    model.addAttribute("totalRemaining",totalRemaining); 
				    model.addAttribute("totalPrice",totalPrice); 
				    
				    
				    List<Adjustment_sk> adjustment_sk = EdataDAO.GetASKSearch(key);
				    
				    adjustment_sk.stream().forEach(row->{
				    	  List<file> subfiles = FileDAO.getallEdata(row.getEdata_id());
					      
				    	  row.setFile(subfiles);
				    });
				    
				  
					 model.addAttribute("adjustment_sk",adjustment_sk); 
					 
					 List<Issue_sk> list_tracking_number = EdataDAO.GetTracking_numberSK();
					 model.addAttribute("list_tracking_number",list_tracking_number);
					 
					 String type ="stock";
					 int cm_idrefer  = data.getId();
					 
					  
				      List<cm> listcms1 = CmDAO.getall(cm_idrefer,type);
				      model.addAttribute("listcms1",listcms1);
					  
					  List<cm> listcms2 = CmDAO.getall(cm_idrefer,type,date);
				      model.addAttribute("listcms2",listcms2);
					  
				      
				      
				      
					  model.addAttribute("type",type); 
					   model.addAttribute("cm_idrefer",cm_idrefer);  
					  
		    }
	
		
		 List<process> process =  EdataDAO.findAllProcessAllowLooseLink();
		 process.stream().forEach(row->{
			 condition condition = ConditionDAO.getbyid(row.getId_condition());
			 List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
			 row.setEformfields(eformfields);
		 });
		 model.addAttribute("process",process);
		 model.addAttribute("LooseLinkByType","sk");
		 model.addAttribute("key1",(data.getStock_code()+data.getStock_receipt_date()+data.getStock_id()).replace("null","") );
		 model.addAttribute("key2",data.getStock_list());
		 List<Looselink> Looselink =  EdataDAO.findAllLooseLinkByTypeAndTo("sk",id);
		 
		 Looselink.stream().limit(10).forEach(row->{
			 
			 eformfield row1 = EformfieldDAO.getbycal(row.getLoose_link_scope1());
			 eformfield row2 = EformfieldDAO.getbycal(row.getLoose_link_scope2());
			 
			 if(row1 != null) { row.setLoose_link_scopename1(row1.getField_label()) ; }
			 if(row2 != null) { row.setLoose_link_scopename2(row2.getField_label()) ;}
		 });
		 
		 
		 model.addAttribute("Looselink",Looselink);
		 
		 
		 
		 
		 
			
		return "/app/views/view_sk";
	}
	
	
	
	
	@RequestMapping(value="/deleteLooselink-workflow/{sk_id}/{id}",method = RequestMethod.GET)
	public String viewregistration(@PathVariable int sk_id,@PathVariable int id,RedirectAttributes redirectAttributes) {
		 redirectAttributes.addFlashAttribute("TAB", "LooseLink");
		 EdataDAO.RemoveLooselink(id);
		return "redirect:/sk-workflow/"+sk_id;
	}
	
	
	@RequestMapping(value="/viewLooselink-workflow/{id}",method = RequestMethod.GET)
	public String viewLooselink(@PathVariable int id,ModelMap model	,HttpSession session) {
		
		Looselink Looselink =  EdataDAO.getLooselink(id);
		
		
		int processes_id = Integer.valueOf(Looselink.getLoose_link_table());
    	
   	 	process process = EdataDAO.getProcess(processes_id);
		 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
		 process.setSub_processes(sub_process);
		 workflow work  = WorkflowDAO.getbyid(process.getId_refer());
	    model.addAttribute("work",work);  
		 
		 
 
   	condition conditions = ConditionDAO.getbyid(process.getId_condition());
   	model.addAttribute("conditions",conditions);  

   	List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
   	List<keymap> keymaps = EdataDAO.getAllKeyMaps(processes_id);
   	
     	
   	eformfields.stream().forEach(row->{
		    	Optional<keymap> data =	keymaps.stream()
		    			.filter(subrow-> Integer.valueOf(subrow.getEformkey())  == row.getField_id() )
		    			.filter(subrow-> subrow.getType().equals("create"))
		    			.filter(subrow-> !subrow.getEformkey().equals("0") && !subrow.getEformkey().equals("-") )
		    			.findFirst();
	
			    	if(data.isPresent()) {
			    		
			    		keymap keymap = data.get();
			    		row.setKeymap(keymap);
			    		
			    		List<dataset_data> dataset_data  = Dataset_dataDAO.getall(keymap.getEnum_id(),keymap.getFilter_enum());
			    		row.setDataset_data(dataset_data);
			    		
			    		
			    	}
			    	
			    	
			    	
			    	
   	}); 
   	model.addAttribute("eformfields",eformfields);
    List<keymap> keymap =  Filemanagerservice.setKeymap(process.getSub_processes());
	   LoadApp LoadApp = new LoadApp();
	   LoadApp.setId(0);
	   LoadApp.setWorkflow(work);
	   LoadApp.setSession(session);
	   LoadApp.setProcess(process);
	   LoadApp.setSub_processes(process.getSub_processes().get(process.getSub_processes().size() - 1));
	   LoadApp.setModel(model);
	   LoadApp.setKeymap(keymap);
	   LoadApp.setCondition(conditions);
	   LoadApp.setWorkflow_refer("Looselink");
	   LoadApp.setLooselink(Looselink);
	   
	   System.out.println("Up LoadApp->");  
	   Filemanagerservice.LoadSubData(LoadApp);
		model.addAttribute("TABLE_DISPLAY","FULL");
		
	 return "/app/views/subdata";
	}
	
	
	
	@RequestMapping(value="/viewregistration-workflow/{id}",method = RequestMethod.GET)
	public String viewregistration(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 registers data = EdataDAO.getRegister(id);
		 model.addAttribute("data",data);  
		 
		 register_log registers_log = EdataDAO.getRegister_log(data.getId());
		 
		if(data != null) {
			  
			  
				LocalDateTime localDate = LocalDateTime.now();//For reference
		 	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 	String date = localDate.format(formatter);
	   	 	String type ="registers";
					 int cm_idrefer  = data.getId();
					 
					  
				      List<cm> listcms1 = CmDAO.getall(cm_idrefer,type);
				      model.addAttribute("listcms1",listcms1);
					  
					  List<cm> listcms2 = CmDAO.getall(cm_idrefer,type,date);
				      model.addAttribute("listcms2",listcms2);
					  
				      
				      
				      
					  model.addAttribute("type",type); 
					   model.addAttribute("cm_idrefer",cm_idrefer);  


						if(registers_log != null) {
						    List<file> files = FileDAO.getallEdata(registers_log.getEdata_id());
					        model.addAttribute("files",files); 
						}
				    }
		
		
		 List<process> process =  EdataDAO.findAllProcessAllowLooseLink();
		 process.stream().forEach(row->{
			 condition condition = ConditionDAO.getbyid(row.getId_condition());
			 List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
			 row.setEformfields(eformfields);
		 });
		 model.addAttribute("process",process);
	
		 model.addAttribute("LooseLinkByType","account");
		 model.addAttribute("key1",data.getRegister_name());
		 model.addAttribute("key2",data.getRegister_type());
		 
		 
		 List<Looselink> Looselink =  EdataDAO.findAllLooseLinkByTypeAndTo("account",id);
		 
		 Looselink.stream().limit(10).forEach(row->{
			 
			 eformfield row1 = EformfieldDAO.getbycal(row.getLoose_link_scope1());
			 eformfield row2 = EformfieldDAO.getbycal(row.getLoose_link_scope2());
			 
			 if(row1 != null) { row.setLoose_link_scopename1(row1.getField_label()) ; }
			 if(row2 != null) { row.setLoose_link_scopename2(row2.getField_label()) ;}
		 });
		 
		 
		 model.addAttribute("Looselink",Looselink);
			
		return "/app/views/view_account";
	}
	
	
	
	
	@RequestMapping(value="/oeereport-workflow/{id}",method = RequestMethod.GET)
	public String oeereport(
			ModelMap model
			,@PathVariable int id
			,@RequestParam(value = "filter",required=false) String filter
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
			List<oee> oee =  (List<oee>) Filemanagerservice.FakeTable("oee",filter);
			System.out.println(" oee "+oee.size());
			model.addAttribute("oee",oee);
			model.addAttribute("filter",filter);
		return "/Subdata/oeereport";
	}
	
	
	
	
	@RequestMapping(value="/wipreport-workflow/{id}",method = RequestMethod.GET)
	public String wipreport(
			ModelMap model
			,@PathVariable int id
			,@RequestParam(value = "filter",required=false) String filter
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
			List<wip> wip = (List<wip>) Filemanagerservice.FakeTable("wip",filter);
			System.out.println(" wip "+wip.size());
			model.addAttribute("wip",wip);
			model.addAttribute("filter",filter);
		return "/Subdata/wipreport";
	}
	
	

	@RequestMapping(value="/editcountstocksetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editcountstocksetting(
			@PathVariable int id,
			@PathVariable int workflow_id,
			ModelMap model
			) throws IOException {

		      reportsetting reportsetting = WorkflowDAO.getReportsetting(id);
		      model.addAttribute("reportsetting",reportsetting); 

		     if(reportsetting != null) {
				    process process =  EdataDAO.getProcess(reportsetting.getReportsetting_id_refer());
				    
				    if(process != null) {
				    	condition condition = ConditionDAO.getbyid(process.getId_condition());
				    	
				    	if(condition != null) {
					    	List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
					    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId());
					    	
					    	
					    	eformfields.stream()
					    	 .forEach(row->{
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
								    	} 	
						    	});    	
								 
					    	
							model.addAttribute("eformfields",eformfields); 
				    	}

				    }
		     } 
		     
		     workflow workflows = WorkflowDAO.getbyid(workflow_id);
			 model.addAttribute("workflows",workflows);

		
		      return "/Subdata/formcountstockreport";
	}
	
	
	
	
	
	
	@RequestMapping(value="/editwipsetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editwipsetting(
			@PathVariable int id,
			@PathVariable int workflow_id,
			ModelMap model
			) throws IOException {

		      reportsetting reportsetting = WorkflowDAO.getReportsetting(id);
		      model.addAttribute("reportsetting",reportsetting); 

		     if(reportsetting != null) {
				    process process =  EdataDAO.getProcess(reportsetting.getReportsetting_id_refer());
				    
				    if(process != null) {
				    	condition condition = ConditionDAO.getbyid(process.getId_condition());
				    	
				    	if(condition != null) {
					    	List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
					    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId());
					    	
					    	
					    	eformfields.stream()
					    	 .forEach(row->{
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
								    	} 	
						    	});    	
								 
					    	
							model.addAttribute("eformfields",eformfields); 
				    	}

				    }
		     } 
		     
		     workflow workflows = WorkflowDAO.getbyid(workflow_id);
			 model.addAttribute("workflows",workflows);

		
		      return "/Subdata/formwipreport";
	}
	
	
	
	
	@RequestMapping(value="/delwipsetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delwipsetting(
			@PathVariable int id,
			@PathVariable int workflow_id

			) throws IOException {

		      WorkflowDAO.DelReportsetting(id);
			 return "redirect:/wipsetting-workflow/"+workflow_id;
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/editdisbursementsetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editdisbursementsetting(
			@PathVariable int id,
			@PathVariable int workflow_id,
			ModelMap model
			) throws IOException {

		      reportsetting reportsetting = WorkflowDAO.getReportsetting(id);
		      model.addAttribute("reportsetting",reportsetting); 

		     if(reportsetting != null) {
				    process process =  EdataDAO.getProcess(reportsetting.getReportsetting_id_refer());
				    
				    if(process != null) {
				    	condition condition = ConditionDAO.getbyid(process.getId_condition());
				    	
				    	if(condition != null) {
					    	List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
					    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId());
					    	
					    	
					    	eformfields.stream()
					    	 .forEach(row->{
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
								    	} 	
						    	});    	
								 
					    	
							model.addAttribute("eformfields",eformfields); 
				    	}

				    }
		     } 
		     
		     workflow workflows = WorkflowDAO.getbyid(workflow_id);
			 model.addAttribute("workflows",workflows);

		
		      return "/Subdata/formdisbursementreport";
	}
	
	
	
	
	@RequestMapping(value="/deldisbursementsetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String deldisbursementsetting(
			@PathVariable int id,
			@PathVariable int workflow_id

			) throws IOException {

		      WorkflowDAO.DelReportsetting(id);
			 return "redirect:/disbursementsetting-workflow/"+workflow_id;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="/delcountstocksetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delcountstocksetting(
			@PathVariable int id,
			@PathVariable int workflow_id

			) throws IOException {

		      WorkflowDAO.DelReportsetting(id);
			 return "redirect:/countstocksetting-workflow/"+workflow_id;
	}
	
	
	
	@RequestMapping(value="/editoeesetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editoeesetting(
			@PathVariable int id,
			@PathVariable int workflow_id,
			ModelMap model
			) throws IOException {

		      reportsetting reportsetting = WorkflowDAO.getReportsetting(id);
		      model.addAttribute("reportsetting",reportsetting); 

		     if(reportsetting != null) {
				    process process =  EdataDAO.getProcess(reportsetting.getReportsetting_id_refer());
				    
				    if(process != null) {
				    	condition condition = ConditionDAO.getbyid(process.getId_condition());
				    	
				    	if(condition != null) {
					    	List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
					    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId());
					    	
					    	
					    	eformfields.stream()
					    	 .forEach(row->{
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
								    	} 	
						    	});    	
								 
					    	
							model.addAttribute("eformfields",eformfields); 
				    	}

				    }
		     } 
		     
		     workflow workflows = WorkflowDAO.getbyid(workflow_id);
			 model.addAttribute("workflows",workflows);

		
		      return "/Subdata/formoeereport";
	}
	
	
	
	
	@RequestMapping(value="/deloeesetting-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String deloeesetting(
			@PathVariable int id,
			@PathVariable int workflow_id

			) throws IOException {

		      WorkflowDAO.DelReportsetting(id);
			 return "redirect:/oeesetting-workflow/"+workflow_id;
	}
	
	
	
	
	@RequestMapping(value="/editsettingoee-workflow",method = RequestMethod.POST)
	public String editsettingoee(
			@RequestParam int workflow_id,
			reportsetting reportsettings
			)  {
		
		
		System.out.println(" workflow_id "+workflow_id);
		
		
		reportsetting reportsetting = WorkflowDAO.getReportsetting(reportsettings.getReportsetting_id());
			
		reportsetting.setReportsetting_date_wip(reportsettings.getReportsetting_date_wip());
		
		reportsetting.setReportsetting_downtime(reportsettings.getReportsetting_downtime());
		reportsetting.setReportsetting_planned_time(reportsettings.getReportsetting_planned_time());
		reportsetting.setReportsetting_speed(reportsettings.getReportsetting_speed());
		reportsetting.setReportsetting_total_count(reportsettings.getReportsetting_total_count());
		reportsetting.setReportsetting_performance(reportsettings.getReportsetting_performance());
		reportsetting.setReportsetting_good_count(reportsettings.getReportsetting_good_count());

		
		
			WorkflowDAO.saveReportsetting(reportsetting);
		 
		 return "redirect:/wipsetting-workflow/"+workflow_id;
	}
	

	

	@RequestMapping(value="/editsettingcountstock-workflow",method = RequestMethod.POST)
	public String editsettingcountstock(
			@RequestParam int workflow_id,
			reportsetting reportsettings
			)  {
		
		
		System.out.println(" workflow_id "+workflow_id);
		
		
		reportsetting reportsetting = WorkflowDAO.getReportsetting(reportsettings.getReportsetting_id());
			
		
			reportsetting.setReportsetting_batch(reportsettings.getReportsetting_batch());
			reportsetting.setReportsetting_stock_code(reportsettings.getReportsetting_stock_code());
			reportsetting.setReportsetting_stock_name(reportsettings.getReportsetting_stock_name());
			reportsetting.setReportsetting_total_system(reportsettings.getReportsetting_total_system());
			reportsetting.setReportsetting_total_real(reportsettings.getReportsetting_total_real());
			reportsetting.setReportsetting_date_wip(reportsettings.getReportsetting_date_wip());
			
			
			WorkflowDAO.saveReportsetting(reportsetting);
		 
		 return "redirect:/countstocksetting-workflow/"+workflow_id;
	}
	
	
	
	
	
	@RequestMapping(value="/editsettingwip-workflow",method = RequestMethod.POST)
	public String savesettingwip(
			@RequestParam int workflow_id,
			reportsetting reportsettings
			)  {
		
		
		System.out.println(" workflow_id "+workflow_id);
		
		
		reportsetting reportsetting = WorkflowDAO.getReportsetting(reportsettings.getReportsetting_id());
			
			reportsetting.setReportsetting_pd(reportsettings.getReportsetting_pd());
			reportsetting.setReportsetting_batch(reportsettings.getReportsetting_batch());
			reportsetting.setReportsetting_department(reportsettings.getReportsetting_department());
			reportsetting.setReportsetting_list(reportsettings.getReportsetting_list());
			reportsetting.setReportsetting_employee(reportsettings.getReportsetting_employee());
			reportsetting.setReportsetting_machine(reportsettings.getReportsetting_machine());
			reportsetting.setReportsetting_date_wip(reportsettings.getReportsetting_date_wip());
			reportsetting.setReportsetting_orders(reportsettings.getReportsetting_orders());
			reportsetting.setReportsetting_received_today(reportsettings.getReportsetting_received_today());
			reportsetting.setReportsetting_paid_today(reportsettings.getReportsetting_paid_today());
			WorkflowDAO.saveReportsetting(reportsetting);
		 
		 return "redirect:/wipsetting-workflow/"+workflow_id;
	}
	



	@RequestMapping(value="/editsettingdisbursement-workflow",method = RequestMethod.POST)
	public String editsettingdisbursement(
			@RequestParam int workflow_id,
			reportsetting reportsettings
			)  {
		
		
		System.out.println(" workflow_id "+workflow_id);
		
		
		reportsetting reportsetting = WorkflowDAO.getReportsetting(reportsettings.getReportsetting_id());
			
			reportsetting.setReportsetting_date_wip(reportsettings.getReportsetting_date_wip());
			reportsetting.setReportsetting_list(reportsettings.getReportsetting_list());
			reportsetting.setReportsetting_received_today(reportsettings.getReportsetting_received_today());
			reportsetting.setReportsetting_paid_today(reportsettings.getReportsetting_paid_today());
		
			reportsetting.setReportsetting_drawer(reportsettings.getReportsetting_drawer());
			reportsetting.setReportsetting_payer(reportsettings.getReportsetting_payer());
			
			
			WorkflowDAO.saveReportsetting(reportsetting);
		 
		 return "redirect:/disbursementsetting-workflow/"+workflow_id;
	}
	


	
	
	
	
	
	
	@RequestMapping(value="/savesettingcountstock-workflow",method = RequestMethod.POST)
	public String savesettingcountstock(
			@RequestParam int process_id,
			@RequestParam int workflow_id,
			@RequestParam String type
			)  {
		
		
		process process = EdataDAO.getProcess(process_id);
		
		List<sub_processes> sub_processes = EdataDAO.getAllSubProcess(process.getId());
		

		reportsetting reportsetting = new reportsetting();		
		reportsetting.setReportsetting_id_refer(process_id);
		reportsetting.setReportsetting_form_name(process.getName());
		reportsetting.setReportsetting_tables("stock_count");
		reportsetting.setReportsetting_type(type);
		
		sub_processes.stream()
	    .filter(row -> row.getAction() != null)
	    .findFirst()
	    .ifPresent(action -> 
	        reportsetting.setReportsetting_data(action.getAction())
	    );
		
		
		sub_processes.stream()
	    .filter(row -> row.getActivate_register_processing() != null)
	    .findFirst()
	    .ifPresent(action -> 
	        reportsetting.setReportsetting_data(action.getActivate_register_processing())
	    );

		 WorkflowDAO.saveReportsetting(reportsetting);
		 return "redirect:/countstocksetting-workflow/"+workflow_id;

	}
	
	
	
	
	@RequestMapping(value="/savesettingwip-workflow",method = RequestMethod.POST)
	public String savesettingwip(
			@RequestParam int process_id,
			@RequestParam int workflow_id,
			@RequestParam String type
			)  {
		
		
		process process = EdataDAO.getProcess(process_id);
		
		List<sub_processes> sub_processes = EdataDAO.getAllSubProcess(process.getId());
		

		reportsetting reportsetting = new reportsetting();		
		reportsetting.setReportsetting_id_refer(process_id);
		reportsetting.setReportsetting_form_name(process.getName());
		reportsetting.setReportsetting_tables("wip");
		reportsetting.setReportsetting_type(type);
		
		sub_processes.stream()
	    .filter(row -> row.getAction() != null)
	    .findFirst()
	    .ifPresent(action -> 
	        reportsetting.setReportsetting_data(action.getAction())
	    );

		 WorkflowDAO.saveReportsetting(reportsetting);
		 return "redirect:/wipsetting-workflow/"+workflow_id;

	}
	
	
	
	@RequestMapping(value="/savesettingoee-workflow",method = RequestMethod.POST)
	public String savesettingoee(
			@RequestParam int process_id,
			@RequestParam int workflow_id,
			@RequestParam String type
			)  {
		
		
		process process = EdataDAO.getProcess(process_id);
		
		List<sub_processes> sub_processes = EdataDAO.getAllSubProcess(process.getId());
		

		reportsetting reportsetting = new reportsetting();		
		reportsetting.setReportsetting_id_refer(process_id);
		reportsetting.setReportsetting_form_name(process.getName());
		reportsetting.setReportsetting_tables("oee");
		reportsetting.setReportsetting_type(type);
		
		sub_processes.stream()
	    .filter(row -> row.getAction() != null)
	    .findFirst()
	    .ifPresent(action -> 
	        reportsetting.setReportsetting_data(action.getAction())
	    );

		 WorkflowDAO.saveReportsetting(reportsetting);
		 return "redirect:/wipsetting-workflow/"+workflow_id;

	}
	
	
	
	
	
	@RequestMapping(value="/oeesetting-workflow/{id}",method = RequestMethod.GET)
	public String oeesetting(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 List<process> process =  EdataDAO.getAllProcess(id);
			 
			 process.stream().forEach(row->{
				 
					condition condition = ConditionDAO.getbyid(row.getId_condition());
					row.setCondition(condition);
			 });
			 
			 model.addAttribute("process",process); 
			
			
		workflow workflows = WorkflowDAO.getbyid(id);
	    model.addAttribute("workflows",workflows);
	    
	    
	     List<reportsetting> setting1 = WorkflowDAO.getAllReportsetting("oee","availability");
	      model.addAttribute("setting1",setting1); 
	    
	      List<reportsetting> setting2 = WorkflowDAO.getAllReportsetting("oee","performance");
		  model.addAttribute("setting2",setting2); 
		      
		  List<reportsetting> setting3 = WorkflowDAO.getAllReportsetting("oee","quality");
		 model.addAttribute("setting3",setting3);    
		      
		return "/Subdata/setoee";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/disbursementsetting-workflow/{id}",method = RequestMethod.GET)
	public String disbursementsetting(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 List<process> process =  EdataDAO.getAllProcess(id);
			 
			 process.stream().forEach(row->{
				 
					condition condition = ConditionDAO.getbyid(row.getId_condition());
					row.setCondition(condition);
			 });
			 
			 model.addAttribute("process",process); 
			
			
		workflow workflows = WorkflowDAO.getbyid(id);
	    model.addAttribute("workflows",workflows);
	    
	    
	     List<reportsetting> wipsetting1 = WorkflowDAO.getAllReportsetting("disbursement","receive");
	      model.addAttribute("wipsetting1",wipsetting1); 
	    
	      List<reportsetting> wipsetting2 = WorkflowDAO.getAllReportsetting("disbursement","payout");
		  model.addAttribute("wipsetting2",wipsetting2); 
		      
		      
		      
		return "/Subdata/setdisbursement";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/wipsetting-workflow/{id}",method = RequestMethod.GET)
	public String wipsetting(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 List<process> process =  EdataDAO.getAllProcess(id);
			 
			 process.stream().forEach(row->{
				 
					condition condition = ConditionDAO.getbyid(row.getId_condition());
					row.setCondition(condition);
			 });
			 
			 model.addAttribute("process",process); 
			
			
		workflow workflows = WorkflowDAO.getbyid(id);
	    model.addAttribute("workflows",workflows);
	    
	    
	     List<reportsetting> wipsetting1 = WorkflowDAO.getAllReportsetting("wip","receive");
	      model.addAttribute("wipsetting1",wipsetting1); 
	    
	      List<reportsetting> wipsetting2 = WorkflowDAO.getAllReportsetting("wip","payout");
		  model.addAttribute("wipsetting2",wipsetting2); 
		      
		      
		      
		return "/Subdata/setwip";
	}
	
	
	
	
	@RequestMapping(value="/systemproduction-workflow/{id}",method = RequestMethod.GET)
	public String systemproduction(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);
			
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
			
		return "/Subdata/systemproduction";
	}
	
	
	
	
	
	
	@RequestMapping(value="/systemhr-workflow/{id}",method = RequestMethod.GET)
	public String systemhr(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
		return "/Subdata/systemhr";
	}
	
	@RequestMapping(value="/systemfinance-workflow/{id}",method = RequestMethod.GET)
	public String systemfinance(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
		return "/Subdata/systemfinance";
	}
	
	

	
	@RequestMapping(value="/systemstock-workflow/{id}",method = RequestMethod.GET)
	public String systemstock(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
		return "/Subdata/systemstock";
	}
	
	
	
	@RequestMapping(value="/countstock-workflow/{id}",method = RequestMethod.GET)
	public String countstock(
			ModelMap model
			,@PathVariable int id
			,@RequestParam(value = "filter",required=false) String filter
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		

			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
			List<stock_count> stock_count = (List<stock_count>) Filemanagerservice.FakeTable("stock_count",filter);
			System.out.println(" stock_count "+stock_count.size());
			model.addAttribute("stock_count",stock_count);
		
			
			model.addAttribute("workflows",workflows);
			model.addAttribute("id",id);  
			model.addAttribute("filter",filter);
		return "/Subdata/countstockreport";
	}
	
	
	
	

	@RequestMapping(value="/countstocksetting-workflow/{id}",method = RequestMethod.GET)
	public String countstocksetting(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			model.addAttribute("id",id);  
			
			 List<process> process =  EdataDAO.getAllProcess(id);
			 
			 process.stream().forEach(row->{
				 
					condition condition = ConditionDAO.getbyid(row.getId_condition());
					row.setCondition(condition);
			 });
			 
			 model.addAttribute("process",process); 
			
			
		workflow workflows = WorkflowDAO.getbyid(id);
	    model.addAttribute("workflows",workflows);
	    
	    
	     List<reportsetting> setting1 = WorkflowDAO.getAllReportsetting("stock_count","record");
	      model.addAttribute("setting1",setting1); 
	    
	
		      
		      
		return "/Subdata/setcountstock";
	}
	
	
	
	
	
	
	
	
	
	

	
	@RequestMapping(value="/Inventories-workflow/{id}",method = RequestMethod.GET)
	public String Inventories(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
			List<stock_log> logs = EdataDAO.getAllStock_log();
			
			String register_type = logs.stream()
				    .map(stock_log::getStock_type).distinct()
				    .collect(Collectors.joining(",")); 
			
		    
		    
			List<stock> item =  EdataDAO.getAllStock(register_type);
			List<stock_lot> items =  EdataDAO.getAllSk();
			 model.addAttribute("count",item.size());
			
			
			item.stream().limit(1000).forEach(row->{
				
				 String key;
				    if (row.getStock_code() != null) {
				        key = row.getStock_code() + row.getStock_receipt_date() + row.getStock_id();
				    } else {
				        key = row.getStock_receipt_date() + row.getStock_id();
				    }
				
				    double total = items.stream()
				    	    .filter(sub -> sub.getStock_status().equals("IN_STOCK"))
				    	    .filter(sub -> sub.getStock_code().equals(key))
				    	    .mapToDouble(sub -> sub.getStock_remaining()) // หรือ field ที่เป็น double
				    	    .sum();

				    System.out.println(" total "+total);
				    
				    row.setStock_remaining(String.valueOf(total) );
			});
			
			 model.addAttribute("register_type",register_type);
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
			 model.addAttribute("item", item.stream().limit(1000).collect(Collectors.toList()));
			
		return "/Subdata/inventories";
	}
	
	
	

	
	
	
	
	
	


	
	
	
	

	
	@RequestMapping(value="/Inventories_FG-workflow/{id}",method = RequestMethod.GET)
	public String Inventories_FG(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);
		
		
		 MainServices.addNotificationAttributes(model);
		 
		 
		 
			List<product_log> register_logs = EdataDAO.getAllProduct_log();

			String register_category = register_logs.stream()
				    .map(product_log::getProduct_category).distinct() // ดึงค่า register_type
				    .collect(Collectors.joining(",")); 
		 
			model.addAttribute("register_category",register_category);
			List<product_master> item =  EdataDAO.getAllProduct(100000);
			
			model.addAttribute("count",item.size());
			model.addAttribute("item", item.stream().limit(1000).collect(Collectors.toList()));
			
		return "/Subdata/inventories_Fg";
	}
	
	
	
	
	@RequestMapping(value="/Movement-workflow/{id}",method = RequestMethod.GET)
	public String Movement(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		  
		
			 model.addAttribute("id",id);
		
		return "/Subdata/movement";
	}
	
	@RequestMapping(value="/deduction_hr-workflow/{id}",method = RequestMethod.GET)
	public String deduction_hr(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_deduction";
	}
	
	
	
	
	@RequestMapping(value="/Profit_finance-workflow/{id}",method = RequestMethod.GET)
	public String profit_finance(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		  
		  
		  
		  
		return "/Subdata/movement_search_profit_finance";
	}
	
	
	
	@RequestMapping(value="/workrule_hr-workflow/{id}",method = RequestMethod.GET)
	public String workrule_hr(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		  long total =  workruleRepository.count();
		  List<workrule> workrule =  workruleRepository.findTop500ByOrderByIdDesc();
		  model.addAttribute("workrule",workrule);
		  
		  List<String> workrule_type = workrule.stream()
				    .map(row-> row.getRuleType())     // ดึงเฉพาะ .type
				    .filter(Objects::nonNull)   // กรอง null ถ้ามี
				    .distinct()                 // เอาเฉพาะค่าที่ไม่ซ้ำ
				    .collect(Collectors.toList());
		  
		  model.addAttribute("workrule_type",workrule_type);
		  model.addAttribute("count",total);
		  
		return "/Subdata/workrule_hr";
	}
	
	
	
	

	
	@RequestMapping(value="/attendance_record_hr-workflow/{id}",method = RequestMethod.GET)
	public String attendance_record(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_attendance";
	}
	
	@RequestMapping(value="/shift_record_hr-workflow/{id}",method = RequestMethod.GET)
	public String shift_record_hr(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_shift_record_hr";
	}
	
	
	@RequestMapping(value="/balancesheet_finance-workflow/{id}",method = RequestMethod.GET)
	public String balancesheet_finance(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_balancesheet";
	}
	
	@RequestMapping(value="/balancesheet_finance_search-workflow",method = RequestMethod.POST)
	public String balancesheet_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  searchYear
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
			List<account_ledger> account_ledger= null;
			List<general_journal> general_journal = null;
			
			dataset dataset = DatasetDAO.getMain("Account_Code");
			List<dataset_data> account_code =  Dataset_dataDAO.getall(dataset.getDataset_id());
		
			account_ledger  = account_ledgerRepository.findByYearLike(searchYear);
			general_journal  = general_journalRepository.findByYearLike(searchYear);
			 model.addAttribute("search", searchYear);
		   	 
		 

			// เตรียมข้อมูลบัญชีหลัก (parent_code == null)
			List<dataset_data> mainAccounts = account_code.stream()
			    .filter(d -> d.getDataset_data_subtitle4() == null || d.getDataset_data_subtitle4().isBlank())
			    .collect(Collectors.toList());

			// เตรียม map รหัสบัญชี → ข้อมูลบัญชี
			Map<String, dataset_data> accountCodeMap = mainAccounts.stream()
			    .collect(Collectors.toMap(dataset_data::getDataset_data_title, d -> d));

			// เตรียม map รหัสบัญชีแม่ → DTO สำหรับรวมยอด
			Map<String, BalanceSheetDTO> balanceMap = new HashMap<>();

			// รวมจาก account_ledger
			for (account_ledger al : account_ledger) {
			    String code = al.getAl_account_code();
			    dataset_data d = accountCodeMap.get(code);
			    if (d == null) continue;

			    BalanceSheetDTO dto = balanceMap.computeIfAbsent(code, k -> {
			        BalanceSheetDTO b = new BalanceSheetDTO();
			        b.setCategory(d.getDataset_data_subtitle3());
			        b.setAccountCode(code);
			        b.setAccountName(d.getDataset_data_subtitle1());
			        b.setDebit(BigDecimal.ZERO);
			        b.setCredit(BigDecimal.ZERO);
			        b.setSummary(false);
			        return b;
			    });

			    dto.setDebit(dto.getDebit().add(otherservices.BigDecimalValueOf(al.getAl_debit())));
			    dto.setCredit(dto.getCredit().add(otherservices.BigDecimalValueOf(al.getAl_credit())));
			}

			// รวมจาก general_journal (ทำเหมือนกัน)
			for (general_journal gj : general_journal) {
			    String code = gj.getGj_account_code();
			    dataset_data d = accountCodeMap.get(code);
			    if (d == null) continue;

			    BalanceSheetDTO dto = balanceMap.computeIfAbsent(code, k -> {
			        BalanceSheetDTO b = new BalanceSheetDTO();
			        b.setCategory(d.getDataset_data_subtitle3());
			        b.setAccountCode(code);
			        b.setAccountName(d.getDataset_data_subtitle1());
			        b.setDebit(BigDecimal.ZERO);
			        b.setCredit(BigDecimal.ZERO);
			        b.setSummary(false);
			        return b;
			    });

			    dto.setDebit(dto.getDebit().add(otherservices.BigDecimalValueOf(gj.getGj_debit_amount())));
			    dto.setCredit(dto.getCredit().add(otherservices.BigDecimalValueOf(gj.getGj_credit_amount())));
			}

			// จัดกลุ่มตามหมวดหมู่
			Map<String, List<BalanceSheetDTO>> grouped = balanceMap.values().stream()
			    .collect(Collectors.groupingBy(BalanceSheetDTO::getCategory));

			
			Set<String> categories = account_code.stream()
				    .map(dataset_data::getDataset_data_subtitle3)
				    .filter(Objects::nonNull)
				    .collect(Collectors.toSet());
			
			
			// สร้างรายงาน list สุดท้าย พร้อมบรรทัดรวม
			List<BalanceSheetDTO> report = new ArrayList<>();
			for (String category : categories) {
			    List<BalanceSheetDTO> list = grouped.getOrDefault(category, Collections.emptyList());
			    BigDecimal totalDebit = list.stream().map(BalanceSheetDTO::getDebit).reduce(BigDecimal.ZERO, BigDecimal::add);
			    BigDecimal totalCredit = list.stream().map(BalanceSheetDTO::getCredit).reduce(BigDecimal.ZERO, BigDecimal::add);

			    // แทรกบรรทัดรวมก่อนขึ้นหมวด
			    BalanceSheetDTO summary = new BalanceSheetDTO();
			    summary.setCategory("รวม" + category);
			    summary.setAccountCode("");
			    summary.setAccountName("");
			    summary.setDebit(totalDebit);
			    summary.setCredit(totalCredit);
			    summary.setSummary(true);

			    report.add(summary);
			    report.addAll(list.stream()
			                     .sorted(Comparator.comparing(BalanceSheetDTO::getAccountCode))
			                     .collect(Collectors.toList()));
			}
			model.addAttribute("report", report);
			
		 return "/Subdata/balancesheet";
		    	 
	
	}
	
	
	
	

	@RequestMapping(value="/trialbalance_finance-workflow/{id}",method = RequestMethod.GET)
	public String trialbalance_finance(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_trialbalance";
	}
	
	
	@RequestMapping(value="/trialbalance_finance_search-workflow",method = RequestMethod.POST)
	public String trialbalance_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  searchYear
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
			List<account_ledger> account_ledger= null;
			List<general_journal> general_journal = null;
			
			dataset dataset = DatasetDAO.getMain("Account_Code");
			List<dataset_data> account_code =  Dataset_dataDAO.getall(dataset.getDataset_id());
		
			account_ledger  = account_ledgerRepository.findByYearLike(searchYear);
			general_journal  = general_journalRepository.findByYearLike(searchYear);
			 model.addAttribute("search", searchYear);
		   	 
		 

			// เตรียมข้อมูลบัญชีหลัก (parent_code == null)
			List<dataset_data> mainAccounts = account_code.stream()
			    .filter(d -> d.getDataset_data_subtitle4() == null || d.getDataset_data_subtitle4().isBlank())
			    .collect(Collectors.toList());

			Map<String, dataset_data> accountMap = account_code.stream()
				    .collect(Collectors.toMap(dataset_data::getDataset_data_title, d -> d));

				Map<String, TrialBalanceDTO> trialMap = new HashMap<>();

				// รวมจาก account_ledger
				for (account_ledger al : account_ledger) {
				    String accCode = al.getAl_account_code();
				    dataset_data acc = accountMap.get(accCode);
				    if (acc == null) continue;

				    trialMap.computeIfAbsent(accCode, k -> {
				        TrialBalanceDTO dto = new TrialBalanceDTO();
				        dto.setAccountCode(accCode);
				        dto.setAccountName(acc.getDataset_data_subtitle1());
				        return dto;
				    });

				    TrialBalanceDTO dto = trialMap.get(accCode);
				    dto.setDebit(dto.getDebit().add(otherservices.BigDecimalValueOf(al.getAl_debit())));
				    dto.setCredit(dto.getCredit().add(otherservices.BigDecimalValueOf(al.getAl_credit())));
				}

				// รวมจาก general_journal
				for (general_journal gj : general_journal) {
				    String accCode = gj.getGj_account_code();
				    dataset_data acc = accountMap.get(accCode);
				    if (acc == null) continue;

				    trialMap.computeIfAbsent(accCode, k -> {
				        TrialBalanceDTO dto = new TrialBalanceDTO();
				        dto.setAccountCode(accCode);
				        dto.setAccountName(acc.getDataset_data_subtitle1());
				        return dto;
				    });

				    TrialBalanceDTO dto = trialMap.get(accCode);
				    dto.setDebit(dto.getDebit().add(otherservices.BigDecimalValueOf(gj.getGj_debit_amount())));
				    dto.setCredit(dto.getCredit().add(otherservices.BigDecimalValueOf(gj.getGj_credit_amount())));
				}
				
				
				List<TrialBalanceDTO> trialList = new ArrayList<>(trialMap.values());
				trialList.sort(Comparator.comparing(TrialBalanceDTO::getAccountCode));

				BigDecimal totalDebit = trialList.stream()
				    .map(TrialBalanceDTO::getDebit)
				    .reduce(BigDecimal.ZERO, BigDecimal::add);

				BigDecimal totalCredit = trialList.stream()
				    .map(TrialBalanceDTO::getCredit)
				    .reduce(BigDecimal.ZERO, BigDecimal::add);

				// (Optional) เพิ่มบรรทัดรวม
				TrialBalanceDTO totalRow = new TrialBalanceDTO();
				totalRow.setAccountCode("Total");
				totalRow.setAccountName("Grand total");
				totalRow.setDebit(totalDebit);
				totalRow.setCredit(totalCredit);

				trialList.add(totalRow);
				model.addAttribute("report", trialList);
			
		 return "/Subdata/trialbalance";
		    	 
	
	}
	
	
	
	

	@RequestMapping(value="/generalaccount_finance-workflow/{id}",method = RequestMethod.GET)
	public String generalaccount_finance(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_generalaccount";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/generalaccount_finance_search-workflow",method = RequestMethod.POST)
	public String generalaccount_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  search
			,@RequestParam(defaultValue ="0",value = "Eyid",required=false) int Eyid 
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
		 if (search.matches("\\d{4}-\\d{2}")) {
			 
			 List<general_journal> general_journal = general_journalRepository.findByYearMonthLike(search);
			 model.addAttribute("report", general_journal);
			 model.addAttribute("id", id);
			 return "/Subdata/generalaccount";
		 }else {
			 
			 List<general_journal> general_journal = general_journalRepository.findByYearLike(search);
			 model.addAttribute("report", general_journal);
			 model.addAttribute("search", search);
			 model.addAttribute("id", id);
			 return "/Subdata/generalaccount";
		 }	    	 
		 
		 
		    	 
	
	}
	
	
	
	

	@RequestMapping(value="/accountledger_finance-workflow/{id}",method = RequestMethod.GET)
	public String accountledger_finance(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_accountledger";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/accountledger_finance_search-workflow",method = RequestMethod.POST)
	public String accountledger_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  search
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
		 if (search.matches("\\d{4}-\\d{2}")) {
			 
			 List<account_ledger> account_ledger = account_ledgerRepository.findByYearMonthLike(search);
			 model.addAttribute("report", account_ledger);
			 model.addAttribute("id", id);
			 return "/Subdata/generalaccount";
		 }else {
			 
			 List<account_ledger> account_ledger = account_ledgerRepository.findByYearLike(search);
			 model.addAttribute("report", account_ledger);
			 model.addAttribute("search", search);
			 model.addAttribute("id", id);
			 return "/Subdata/accountledger";
		 }	    	 
		 
		 
		    	 
	
	}
	
	
	@RequestMapping(value="/disbursement_finance-workflow/{id}",method = RequestMethod.GET)
	public String adisbursement(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_disbursement";
	}
	

	
	@RequestMapping(value="/disbursement_finance_search-workflow",method = RequestMethod.POST)
	public String disbursement_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  search
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
			 workflow workflows = WorkflowDAO.getbyid(id);
			 model.addAttribute("workflows",workflows);
			List<DisbursementDTO> report = (List<DisbursementDTO>) Filemanagerservice.FakeTable("disbursement",search);
			 model.addAttribute("report", report);
			 model.addAttribute("search", search);
			 model.addAttribute("id", id);
			 return "/Subdata/disbursement";
	
		    	 
	
	}
	
	
	
	
	@RequestMapping(value="/payroll_record_hr-workflow/{id}",method = RequestMethod.GET)
	public String payroll_record_hr(
			ModelMap model
			,@PathVariable int id
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		 
		  model.addAttribute("id",id);
		
		return "/Subdata/movement_search_payroll_record_hr";
	}
	

	
	@RequestMapping(value="/profit_finance_search-workflow",method = RequestMethod.POST)
	public String profit_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam(defaultValue ="-",value = "searchMonth",required=false) String  searchMonth
			,@RequestParam(defaultValue ="-",value = "searchYear",required=false) String  searchYear
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
			List<account_ledger> account_ledger= null;
			List<general_journal> general_journal = null;
			
			dataset dataset = DatasetDAO.getMain("Account_Code");
			List<dataset_data> account_code =  Dataset_dataDAO.getall(dataset.getDataset_id());
		 
		 if (!searchMonth.equals("-")) {
			 
			 account_ledger  = account_ledgerRepository.findByYearMonthLike(searchMonth);
			 general_journal  = general_journalRepository.findByYearMonthLike(searchMonth);
			 model.addAttribute("search", searchMonth);
			 
			 }else  if (!searchYear.equals("-")) {
				 
				 account_ledger  = account_ledgerRepository.findByYearLike(searchYear);
				 general_journal  = general_journalRepository.findByYearLike(searchYear);
				 model.addAttribute("search", searchYear);
			 }	    	 
		 

			// เตรียมข้อมูลบัญชีหลัก (parent_code == null)
			List<dataset_data> mainAccounts = account_code.stream()
			    .filter(d -> d.getDataset_data_subtitle4() == null || d.getDataset_data_subtitle4().isBlank())
			    .collect(Collectors.toList());

			// เตรียม map รหัสบัญชี → ข้อมูลบัญชี
			Map<String, dataset_data> accountMap = mainAccounts.stream()
			    .collect(Collectors.toMap(dataset_data::getDataset_data_title, d -> d));

			// เตรียม map รหัสบัญชีแม่ → DTO สำหรับรวมยอด
			Map<String, AccountSummaryDTO> summaryMap = new HashMap<>();

			// รวมจาก account_ledger
			for (account_ledger al : account_ledger) {
			    String accCode = al.getAl_account_code();
			    String parentCode = Optional.ofNullable(accountMap.get(accCode))
			                         .map(d -> d.getDataset_data_subtitle4())
			                         .orElse(accCode); // ถ้าไม่มี parent = ตัวมันเอง

			    dataset_data main = accountMap.get(parentCode);
			    if (main == null || main.getDataset_data_subtitle4() != null) continue; // ข้ามบัญชีย่อย

			    summaryMap.computeIfAbsent(parentCode, k -> {
			        AccountSummaryDTO dto = new AccountSummaryDTO();
			        dto.setAccountCode(main.getDataset_data_title());
			        dto.setAccountName(main.getDataset_data_subtitle1());
			        dto.setAccountType(main.getDataset_data_subtitle2());
			        return dto;
			    });

			    AccountSummaryDTO dto = summaryMap.get(parentCode);
			    dto.setTotalDebit(dto.getTotalDebit().add(otherservices.BigDecimalValueOf(al.getAl_debit())));
			    dto.setTotalCredit(dto.getTotalCredit().add(otherservices.BigDecimalValueOf(al.getAl_credit())));
			}

			// รวมจาก general_journal แบบเดียวกัน
			for (general_journal gj : general_journal) {
			    String accCode = gj.getGj_account_code();
			    String parentCode = Optional.ofNullable(accountMap.get(accCode))
			                         .map(d -> d.getDataset_data_subtitle4())
			                         .orElse(accCode);

			    dataset_data main = accountMap.get(parentCode);
			    if (main == null || main.getDataset_data_subtitle4() != null) continue;

			    summaryMap.computeIfAbsent(parentCode, k -> {
			        AccountSummaryDTO dto = new AccountSummaryDTO();
			        dto.setAccountCode(main.getDataset_data_title());
			        dto.setAccountName(main.getDataset_data_subtitle1());
			        dto.setAccountType(main.getDataset_data_subtitle2());
			        return dto;
			    });

			    AccountSummaryDTO dto = summaryMap.get(parentCode);
			    dto.setTotalDebit(dto.getTotalDebit().add(otherservices.BigDecimalValueOf(gj.getGj_debit_amount())));
			    dto.setTotalCredit(dto.getTotalCredit().add(otherservices.BigDecimalValueOf(gj.getGj_credit_amount())));
			}

			
			List<AccountSummaryDTO> report = new ArrayList<>(summaryMap.values());
			report.sort(Comparator.comparing(AccountSummaryDTO::getAccountCode));
			model.addAttribute("report", report);
			
		 return "/Subdata/profit_finance";
		    	 
	
	}
	
	
	

	
	@RequestMapping(value="/Detail_profit_finance_search-workflow/{account_id}/{search}/{id}",method = RequestMethod.POST)
	public String Detail_profit_finance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String account_id
			,@RequestParam String search
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
			List<account_ledger> account_ledger= null;
			List<general_journal> general_journal = null;
			
			dataset dataset = DatasetDAO.getMain("Account_Code");
			List<dataset_data> account_code =  Dataset_dataDAO.getall(dataset.getDataset_id());
		 
			 if (search.matches("\\d{4}-\\d{2}")) {
			 
			 account_ledger  = account_ledgerRepository.findByYearMonthLike(search);
			 general_journal  = general_journalRepository.findByYearMonthLike(search);
			 
			 }else   {
				 account_ledger  = account_ledgerRepository.findByYearLike(search);
				 general_journal  = general_journalRepository.findByYearLike(search);
			 }	    	 
			 
			// account_id คือ ID ของแม่ที่ต้องการ
			 List<dataset_data> selectedAccounts = account_code.stream()
			     .filter(d -> account_id.equals(d.getDataset_data_subtitle4()))  // subtitle4 = parent_code (แม่)
			     .collect(Collectors.toList());

			 Map<String, dataset_data> accountMap = account_code.stream()
					    .collect(Collectors.toMap(dataset_data::getDataset_data_title, d -> d));

					Map<String, AccountSummaryDTO> summaryMap = new HashMap<>();

					for (account_ledger al : account_ledger) {
					    String accCode = al.getAl_account_code();

					    // กรองเอาแค่ลูกที่อยู่ใน selectedAccounts เท่านั้น
					    boolean isSelected = selectedAccounts.stream()
					        .anyMatch(d -> d.getDataset_data_title().equals(accCode));
					    if (!isSelected) continue;

					    dataset_data acc = accountMap.get(accCode);

					    summaryMap.computeIfAbsent(accCode, k -> {
					        AccountSummaryDTO dto = new AccountSummaryDTO();
					        dto.setAccountCode(acc.getDataset_data_title());
					        dto.setAccountName(acc.getDataset_data_subtitle1());
					        dto.setAccountType(acc.getDataset_data_subtitle2());
					        dto.setAccountId(acc.getDataset_data_id());
					        return dto;
					    });

					    AccountSummaryDTO dto = summaryMap.get(accCode);
					    dto.setTotalDebit(dto.getTotalDebit().add(otherservices.BigDecimalValueOf(al.getAl_debit())));
					    dto.setTotalCredit(dto.getTotalCredit().add(otherservices.BigDecimalValueOf(al.getAl_credit())));
					}

					// ทำเหมือนกันกับ general_journal
					for (general_journal gj : general_journal) {
					    String accCode = gj.getGj_account_code();

					    boolean isSelected = selectedAccounts.stream()
					        .anyMatch(d -> d.getDataset_data_title().equals(accCode));
					    if (!isSelected) continue;

					    dataset_data acc = accountMap.get(accCode);

					    summaryMap.computeIfAbsent(accCode, k -> {
					        AccountSummaryDTO dto = new AccountSummaryDTO();
					        dto.setAccountCode(acc.getDataset_data_title());
					        dto.setAccountName(acc.getDataset_data_subtitle1());
					        dto.setAccountType(acc.getDataset_data_subtitle2());
					        dto.setAccountId(acc.getDataset_data_id());
					        return dto;
					    });

					    AccountSummaryDTO dto = summaryMap.get(accCode);
					    dto.setTotalDebit(dto.getTotalDebit().add(otherservices.BigDecimalValueOf(gj.getGj_debit_amount())));
					    dto.setTotalCredit(dto.getTotalCredit().add(otherservices.BigDecimalValueOf(gj.getGj_credit_amount())));
					}

					List<AccountSummaryDTO> report = new ArrayList<>(summaryMap.values());
					report.sort(Comparator.comparing(AccountSummaryDTO::getAccountCode));

	

				model.addAttribute("report", report);
				 model.addAttribute("account_id", account_id);
		 return "/Subdata/Detailprofit_finance";
		    	 
	
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/payroll_record_search-workflow",method = RequestMethod.POST)
	public String payroll_record_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  searchMonth
			,@RequestParam(defaultValue ="0",value = "Eyid",required=false) int Eyid 
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
		 if (searchMonth.matches("\\d{4}-\\d{2}")) {
			 
			int Year = Integer.valueOf(searchMonth.split("-")[0]);
			int Month = Integer.valueOf(searchMonth.split("-")[1]);		
 
		    List<payroll_record> payroll_record;
			
			 if(Eyid > 0) {
				 payroll_record =  payroll_recordRepository.findByEmployeeIdAndPayrollYearAndPayrollMonth(Eyid,Year,Month);
			

				 List<deduction_record> deduction =deduction_recordRepository.findByEmployeeIdAndDeductYearAndDeductMonth(Eyid,Year,Month);
				 List<attendance_record> attendance =attendance_recordRepository.findByEmployeeIdAndAttendanceYearAndAttendanceMonth(Eyid,Year,Month);
			
				 
				 if(payroll_record.size() > 0) {
					   model.addAttribute("payroll_record", payroll_record.get(0));
				 }
				
				   model.addAttribute("attendance", attendance);
				   model.addAttribute("deduction", deduction);
					
					
					model.addAttribute("searchMonth", searchMonth);
					model.addAttribute("id", id);
					return "/app/views/individual_payroll";				 	
				
				 	
			 }else {
				 payroll_record =payroll_recordRepository.findByPayrollYearAndPayrollMonth(Year,Month);
				 
				 List<PayrollSlipDTO> PayrollSlip = new ArrayList<PayrollSlipDTO>();
				 payroll_record.stream().forEach(row->{
					 
					 PayrollSlipDTO Slip = new PayrollSlipDTO();
					 
					 Slip.setEmployeeName(row.getEmployee_name());
					 Slip.setBaseSalary(new BigDecimal(row.getBase_salary()));
					 Slip.setOvertime(new BigDecimal(row.getOvertime_amount()));
					 Slip.setShiftBonus(new BigDecimal(row.getShift_bonus()));
					 Slip.setOtherAllowance(new BigDecimal(row.getOther_allowance()));
					 Slip.setTotalEarning(new BigDecimal(row.getTotal_earning()));
					 Slip.setTotalDeduction(new BigDecimal(row.getTotal_deduction()));
					 Slip.setNetPay(new BigDecimal(row.getNet_pay()));		
					 
					 PayrollSlip.add(Slip);
				 });
				 
					model.addAttribute("payroll_total", PayrollSlip);
					model.addAttribute("searchMonth", searchMonth);
					model.addAttribute("id", id);
					return "/Subdata/payroll";
				 
			 }
			 
		
		 }else {
			 
			 model.addAttribute("searchMonth", searchMonth);
			 model.addAttribute("id", id);
			 return "/Subdata/payroll";
		 }	    	 
		 
		 
		    	 
	
	}
	
	
	
	
	
	@RequestMapping(value="/detail_shift-workflow/{shiftid}/{id}",method = RequestMethod.GET)
	public String detail_shift(
			ModelMap model
			,@PathVariable int id
			,@PathVariable int  shiftid
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
	
		
			  shift_record shift_record = shift_recordRepository.findById(shiftid);
              if(shift_record.getId() > 0) {
            	  
            	  List<shift_record>  shift_records = shift_recordRepository.findAllByTempIdAndEmployeeNameIsNotNull(shift_record.getEdataId());
            	  if(shift_record.getEmployeeName() != null) {
            		  
            			 if (shift_records == null) {
            				 shift_records = new ArrayList<>();
         				}
            		  shift_records.add(0,shift_record);
            	  }
              }
			  
			  
		 model.addAttribute("shift_record", shift_record);
		 model.addAttribute("id", id);
		 return "/Subdata/Detailshift";
		    	 
	
	}
	
	
	@RequestMapping(value="/detail_workrule-workflow/{workflowid}/{id}",method = RequestMethod.GET)
	public String detail_workrule(
			ModelMap model
			,@PathVariable int id
			,@PathVariable int  workflowid
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
	
		
		    workrule workrule = workruleRepository.findById(workflowid);
              if(workrule.getId() > 0) {
            	  
            	  List<workrule>  workrules = null;
            	  
            	  if(workrule.getRuleType()  == WorkruleType.SHIFT.name()) {
            		  
            		  workrules = workruleRepository.findAllByTempIdAndShiftStartTimeIsNotNull(workrule.getEdataId());
            		
            		 if(workrule.getShiftStartTime() != null) {
            			 
            			 if (workrules == null) {
            				    workrules = new ArrayList<>();
            				}
            			  workrules.add(0,workrule);
            		 }  
            	  }
            	  
            	  else if(workrule.getRuleType()  == WorkruleType.HOLIDAY.name()) {
          
            		  workrules = workruleRepository.findAllByTempIdAndAnnualLeaveDaysIsNotNull(workrule.getEdataId());
              		
             		 if(workrule.getAnnualLeaveDays() != null) {
             			 
             			 if (workrules == null) {
             				    workrules = new ArrayList<>();
             				}
             			  workrules.add(0,workrule);
             		 }  
             		 
             		workrule_leave_log workrule_leave_log = workrule_leave_logRepository.findTop500ByWorkruleIdOrderByIdDesc(workrule.getId());
             		 model.addAttribute("workrule_leave_log",workrule_leave_log);
                  }
            	  else if(workrule.getRuleType()  == WorkruleType.DEDUCTION.name()) {
                      
            		  workrules = workruleRepository.findAllByTempIdAndThresholdMinutesIsNotNull(workrule.getEdataId());
              		
             		 if(workrule.getThresholdMinutes() != null) {
             			 
             			 if (workrules == null) {
             				    workrules = new ArrayList<>();
             				}
             			  workrules.add(0,workrule);
             		 }  
                  }
            	  else if(workrule.getRuleType()  == WorkruleType.TRAINING.name()) {
                      
            		  workrules = workruleRepository.findAllByTempIdAndCourseNameIsNotNull(workrule.getEdataId());
              		
             		 if(workrule.getCourseName() != null) {
             			 
             			 if (workrules == null) {
             				    workrules = new ArrayList<>();
             				}
             			  workrules.add(0,workrule);
             		 }  
                  }
    			  
         		 model.addAttribute("workrules",workrules);
              }
			  
      
              
         model.addAttribute("workrule",workrule);
		 model.addAttribute("id", id);
		 return "/Subdata/Detailworkrule";
		    	 
	
	}
	
	
	@RequestMapping(value="/shift_search-workflow",method = RequestMethod.POST)
	public String shift_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  searchMonth
			,@RequestParam(defaultValue ="0",value = "Eyid",required=false) int Eyid 
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
		 if (searchMonth.matches("\\d{4}-\\d{2}")) {
			 
			int Year = Integer.valueOf(searchMonth.split("-")[0]);
			int Month = Integer.valueOf(searchMonth.split("-")[1]);		
			
			
			/*
	     	  List<shift_record> shift_record = shift_recordRepository.findAllByEdataIdIsNotNullAndEdataIdNotAndShiftYearAndShiftMonth(0, Year, Month);
		  
			  shift_record.stream().forEach(row->{  
				  List<shift_record> subdata = shift_recordRepository.findAllByTempIdAndEmployeeNameIsNotNull(row.getEdataId());
				  int mainsize =  otherservices.haveValue(row.getEmployeeName()) == true ? 1 : 0;
				  row.setCount(mainsize + subdata.size());
			  });
			  */
		
			  List<shift_record> shift_record = shift_recordRepository.findAllByShiftYearAndShiftMonth(Year, Month);

			  Map<String, Long> countByTempId = shift_record.stream()
					    .filter(r -> r.getTempId() != null)
					    .collect(Collectors.groupingBy(
					        r -> r.getTempId(),     // ใช้ lambda แทน method reference
					        Collectors.counting()
					    ));
			  
			  shift_record.forEach(row -> {
				    long count = countByTempId.getOrDefault(row.getEdataId(), 0L);
				    int mainsize = otherservices.haveValue(row.getEmployeeName()) ? 1 : 0;
				    row.setCount((int)(count + mainsize));
				});
			  
			  
			  
			model.addAttribute("shift_record", shift_record);
		 }	    	 

		 model.addAttribute("searchMonth", searchMonth);
		 model.addAttribute("id", id);
		 return "/Subdata/shift";
		    	 
	
	}
	
	
	

	
	
	@RequestMapping(value="/deduction_search-workflow",method = RequestMethod.POST)
	public String deduction_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  searchMonth
			,@RequestParam(defaultValue ="0",value = "Eyid",required=false) int Eyid 
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
		 if (searchMonth.matches("\\d{4}-\\d{2}")) {
			 
			int Year = Integer.valueOf(searchMonth.split("-")[0]);
			int Month = Integer.valueOf(searchMonth.split("-")[1]);		
 
		    List<payroll_record> payroll_record;
			
			 if(Eyid > 0) {
				 payroll_record =  payroll_recordRepository.findByEmployeeIdAndPayrollYearAndPayrollMonth(Eyid,Year,Month);
			

				 List<deduction_record> deduction =deduction_recordRepository.findByEmployeeIdAndDeductYearAndDeductMonth(Eyid,Year,Month);
				 List<attendance_record> attendance =attendance_recordRepository.findByEmployeeIdAndAttendanceYearAndAttendanceMonth(Eyid,Year,Month);
			
				 

				   
					 if(payroll_record.size() > 0) {
						   model.addAttribute("payroll_record", payroll_record.get(0));
					 }
					
				   
				   model.addAttribute("attendance", attendance);
				   model.addAttribute("deduction", deduction);
					
					
					model.addAttribute("searchMonth", searchMonth);
					model.addAttribute("id", id);
					return "/app/views/individual_payroll";				 	
				
				 	
			 }else {
				 List<deduction_record> deduction =deduction_recordRepository.findByDeductYearAndDeductMonth(Year,Month);
				 
				   model.addAttribute("deduction", deduction);
					model.addAttribute("searchMonth", searchMonth);
					model.addAttribute("id", id);
					return "/Subdata/deduction";
				 
			 }
			 
		
		 }else {
			 
			 model.addAttribute("searchMonth", searchMonth);
			 model.addAttribute("id", id);
			 return "/Subdata/deduction";
		 }	    	 
		 
		 
		    	 
	
	}
	
	
	
	@RequestMapping(value="/attendance_search-workflow",method = RequestMethod.POST)
	public String attendance_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String  searchMonth
			,@RequestParam(defaultValue ="0",value = "Eyid",required=false) int Eyid 
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 
		 if (searchMonth.matches("\\d{4}-\\d{2}")) {
			 
			int Year = Integer.valueOf(searchMonth.split("-")[0]);
			int Month = Integer.valueOf(searchMonth.split("-")[1]);		
 
		    List<payroll_record> payroll_record;
			
			 if(Eyid > 0) {
				 payroll_record =  payroll_recordRepository.findByEmployeeIdAndPayrollYearAndPayrollMonth(Eyid,Year,Month);
			

				 List<deduction_record> deduction =deduction_recordRepository.findByEmployeeIdAndDeductYearAndDeductMonth(Eyid,Year,Month);
				 List<attendance_record> attendance =attendance_recordRepository.findByEmployeeIdAndAttendanceYearAndAttendanceMonth(Eyid,Year,Month);
			
				 
				 if(payroll_record.size() > 0) {
					   model.addAttribute("payroll_record", payroll_record.get(0));
				 }
				
				   
				   
				   
				   model.addAttribute("attendance", attendance);
				   model.addAttribute("deduction", deduction);
					
					
					model.addAttribute("searchMonth", searchMonth);
					model.addAttribute("id", id);
					return "/app/views/individual_payroll";				 	
				
				 	
			 }else {
				 List<attendance_record> attendance =attendance_recordRepository.findByAttendanceYearAndAttendanceMonth(Year,Month);
				   model.addAttribute("attendance", attendance);
					model.addAttribute("searchMonth", searchMonth);
					model.addAttribute("id", id);
					return "/Subdata/attendance";
				 
			 }
			 
		
		 }else {
			 
			 model.addAttribute("searchMonth", searchMonth);
			 model.addAttribute("id", id);
			 return "/Subdata/attendance";
		 }	    	 
		 
		 
		    	 
	
	}
	
	
	
	
	
	@RequestMapping(value="/transfer_search2-workflow",method = RequestMethod.POST)
	public String transfer_search2(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String search
			,@RequestParam String product_code
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 System.out.println(" product_code "+product_code);
	
		 List<Inventory_transfer_fg> Inventory_transfer_fg = EdataDAO.GetTFGSearch(search,product_code);
	
		
	
		 
		 List<stock> item = new ArrayList<>();
		 DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM");
		 List<LocalDate> datesToLoop = new ArrayList<>();

		 if (search.matches("\\d{4}-\\d{2}")) {
		     // กรณี yyyy-MM
		     YearMonth yearMonth = YearMonth.parse(search, yearMonthFormat);
		     LocalDate today = LocalDate.now();
		     int lengthOfMonth = yearMonth.lengthOfMonth();

		     for (int day = 1; day <= lengthOfMonth; day++) {
		         LocalDate date = yearMonth.atDay(day);
		         if (date.isAfter(today)) break;
		         datesToLoop.add(date);
		     }

		 } else {
		     // กรณีส่งวันเดียว yyyy-MM-dd หรือ ISO-8601
		     try {
		    	 LocalDate date = LocalDate.parse(search);  // << ใช้ตัวนี้แทน
		    	    datesToLoop.add(date);
		     } catch (Exception e) {
		         // ไม่ใช่รูปแบบวัน → ไม่ต้องทำอะไร
		     }
		 }
		 
		 

		 if (!datesToLoop.isEmpty()) {
			    LocalDate startDate = datesToLoop.get(0);
			    LocalDate endDate = datesToLoop.get(datesToLoop.size() - 1);

			    // กรองข้อมูลให้อยู่ในช่วงวันที่
			    List<Inventory_transfer_fg> filteredFG = Inventory_transfer_fg.stream()
			        .filter(row -> otherservices.IsStringDate(row.getTransfer_date()))
			        .filter(row -> {
			            LocalDate date = LocalDateTime.parse(row.getTransfer_date()).toLocalDate();
			            return !date.isBefore(startDate) && !date.isAfter(endDate);
			        })
			        .toList(); // หรือ .collect(Collectors.toList()) สำหรับ Java < 16

			    // ส่ง filteredFG ไปใช้งาน หรือ model.addAttribute(...)
			    model.addAttribute("item", filteredFG);
			} else {
			    model.addAttribute("item", List.of()); // ส่งค่าว่างถ้าไม่มีวันที่
			}
		 
		 
			 item.stream().forEach(row->{
				   List<file> subfiles = FileDAO.getallEdata(row.getEdata_id());
				   row.setFile(subfiles);
			 });
		 
	
		 

		    	model.addAttribute("id", id);
		    	model.addAttribute("product_code",product_code);
		    	 
		    	 
		return "/Subdata/transfer_search2";
	}
	
	
	
	
	
	@RequestMapping(value="/transfer_search-workflow",method = RequestMethod.POST)
	public String transfer_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String search
			,@RequestParam String stock_code
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 System.out.println(" stock_code "+stock_code);
	
		 List<Inventory_transfer_sk> Inventory_transfer_sk = EdataDAO.GetTSKSearch(search,stock_code);
	
		
	
		 
		 List<stock> item = new ArrayList<>();
		 DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM");
		 List<LocalDate> datesToLoop = new ArrayList<>();

		 if (search.matches("\\d{4}-\\d{2}")) {
		     // กรณี yyyy-MM
		     YearMonth yearMonth = YearMonth.parse(search, yearMonthFormat);
		     LocalDate today = LocalDate.now();
		     int lengthOfMonth = yearMonth.lengthOfMonth();

		     for (int day = 1; day <= lengthOfMonth; day++) {
		         LocalDate date = yearMonth.atDay(day);
		         if (date.isAfter(today)) break;
		         datesToLoop.add(date);
		     }

		 } else {
		     // กรณีส่งวันเดียว yyyy-MM-dd หรือ ISO-8601
		     try {
		    	 LocalDate date = LocalDate.parse(search);  // << ใช้ตัวนี้แทน
		    	    datesToLoop.add(date);
		     } catch (Exception e) {
		         // ไม่ใช่รูปแบบวัน → ไม่ต้องทำอะไร
		     }
		 }
		 
		 

		 // ✅ ใช้ loop เดียวแบบไม่ต้องแยก else
		 for (LocalDate date : datesToLoop) {
		   
			 List<Inventory_transfer_sk> SubSk = Inventory_transfer_sk.stream()
		         .filter(row -> otherservices.IsStringDate(row.getSkt_date()))
		         .filter(row -> {
		             LocalDateTime dt = LocalDateTime.parse(row.getSkt_date());
		             return dt.toLocalDate().equals(date);
		         })
		         .collect(Collectors.toList());

		     SubSk.forEach(rows -> {
		         stock stock = new stock();
		         stock.setStock_receipt_dateupdate(rows.getSkt_date());
		         stock.setStock_received(otherservices.StringValueOf(rows.getSkt_quantity()));
		         stock.setStock_unit(rows.getSkt_unit());
		         stock.setStock_code(rows.getSkt_stock_code());
		         stock.setStock_list(rows.getSkt_stock_name());
		         stock.setStock_batch(rows.getSkt_lot_batch());
		         stock.setMove_form(rows.getSkt_from_location());
		         stock.setMove_to(rows.getSkt_to_location());
		         stock.setStock_status(rows.getSkt_status());
		         stock.setDrawer(rows.getSkt_transferred_by());
		         stock.setPayer(rows.getSkt_approved_by());
		         stock.setTracking_number(rows.getSkt_tracking_number());		         
		         List<file> subfiles = FileDAO.getallEdata(rows.getEdata_id());
		         stock.setFile(subfiles);
		         stock.setEdata_id(rows.getEdata_id());
		         item.add(stock);
		     });
		   
		     System.out.println("date " + date);
		 }
		        Collections.sort(item, Comparator.comparing(stock::getStock_receipt_dateupdate));
		    	model.addAttribute("id", id);
		    	model.addAttribute("item",item);
		    	model.addAttribute("stock_code",stock_code);
		    	 
		    	 
		return "/Subdata/transfer_search";
	}

	
	
	
	@RequestMapping(value="/traceability_search-workflow",method = RequestMethod.POST)
	public String traceability_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String search
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


		 //จ่ายไปส่วยผลืตใดบ้าง 
		 List<Issue_sk> issueList = EdataDAO.GetISKSearch(search);
		 
		 
		 String lotBatchInSql = issueList.stream()
				    .map(Issue_sk::getSki_lot_batch2)
				    .filter(batch-> otherservices.haveValue(batch) == true )
				    .distinct()
				    .map(batch ->batch) // ใส่ single quote ให้พร้อมใช้ใน SQL
				    .collect(Collectors.joining(", "));
		 
		 
		 model.addAttribute("issueList",issueList);
		   	
		 
		 
		 List<Issue_sk> groupedList = issueList.stream()
		            .collect(Collectors.groupingBy(Issue_sk::getSki_lot_batch2))
		            .values().stream()
		            .map(list -> {
		                Issue_sk first = list.get(0);

		                Double totalQuantity = list.stream()
		                    .mapToDouble(Issue_sk::getSki_quantity)
		                    .sum();

		                Double roundedQuantity = Math.round(totalQuantity * 100.0) / 100.0;
		                
		                
		                // แปลง String เป็น LocalDateTime โดยกรอง null ออกก่อน
		                Optional<LocalDateTime> minDateTime = list.stream()
		                    .map(Issue_sk::getSki_date)
		                    .filter(dateStr ->  otherservices.IsStringDate(dateStr) != false)
		                    .map(dateStr -> LocalDateTime.parse(dateStr, formatter))
		                    .min(LocalDateTime::compareTo);

		                Optional<LocalDateTime> maxDateTime = list.stream()
		                    .map(Issue_sk::getSki_date)
		                    .filter(dateStr ->  otherservices.IsStringDate(dateStr) != false)
		                    .map(dateStr -> LocalDateTime.parse(dateStr, formatter))
		                    .max(LocalDateTime::compareTo);

		                // เลือกใช้ minDateTime เป็น string หรือจะเอา maxDateTime ก็ได้
		                String newDatemin = minDateTime
		                    .map(dt -> dt.format(formatter))
		                    .orElse("N/A");

		                String newDatemax = maxDateTime
			                    .map(dt -> dt.format(formatter))
			                    .orElse("N/A");
		                
		                
		                Issue_sk result = new Issue_sk();

		                String batch = first.getSki_lot_batch2() == null ? "N/A" :  first.getSki_lot_batch2();
		                result.setSki_lot_batch(batch);
		                result.setSki_quantity(roundedQuantity);
		                result.setSki_date(newDatemin+" - "+newDatemax);
		                result.setSki_tracking_number(first.getSki_tracking_number());
		                // ถ้ามีฟิลด์อื่นๆ ก็ copy ตามต้องการ
		                // result.setOtherField(first.getOtherField());

		                return result;
		            })
		            .collect(Collectors.toList());

		 
		 groupedList.forEach(row->{
			 System.out.println(" lot_batch "+row.getSki_lot_batch2());
			 System.out.println(" quantity "+row.getSki_quantity());
			 System.out.println(" date "+row.getSki_date());
		 });
		 
		 model.addAttribute("pdList",groupedList);
		 
		 
		 System.out.println(" lotBatchInSql "+lotBatchInSql);
		 
		 
		 //FG ที่เกี่ยวข้อง
		 List<Fg> fgList = EdataDAO.GetFGSearch(lotBatchInSql);
		 model.addAttribute("fgList",fgList);
		  
		 
		 
		 System.out.println(" fgList "+fgList.size());
		 
	

		 List<Issue_fg> plList = EdataDAO.GetIFGSearch(lotBatchInSql);
		
		 List<Issue_fg> customerList = plList.stream()
		            .collect(Collectors.groupingBy(Issue_fg::getIssued_lot_batch))
		            .values().stream()
		            .map(list -> {
		            	Issue_fg first = list.get(0);

		                Double totalQuantity = list.stream()
		                    .mapToDouble(Issue_fg::getIssued_quantity)
		                    .sum();

		                Double roundedQuantity = Math.round(totalQuantity * 100.0) / 100.0;
		                
		                
		                // แปลง String เป็น LocalDateTime โดยกรอง null ออกก่อน
		                Optional<LocalDateTime> minDateTime = list.stream()
		                    .map(Issue_fg::getIssue_date)
		                    .filter(dateStr ->  otherservices.IsStringDate(dateStr) != false)
		                    .map(dateStr -> LocalDateTime.parse(dateStr, formatter))
		                    .min(LocalDateTime::compareTo);

		                Optional<LocalDateTime> maxDateTime = list.stream()
		                    .map(Issue_fg::getIssue_date)
		                    .filter(dateStr ->  otherservices.IsStringDate(dateStr) != false)
		                    .map(dateStr -> LocalDateTime.parse(dateStr, formatter))
		                    .max(LocalDateTime::compareTo);

		                // เลือกใช้ minDateTime เป็น string หรือจะเอา maxDateTime ก็ได้
		                String newDatemin = minDateTime
		                    .map(dt -> dt.format(formatter))
		                    .orElse("N/A");

		                String newDatemax = maxDateTime
			                    .map(dt -> dt.format(formatter))
			                    .orElse("N/A");
		                
		                
		                Issue_fg result = new Issue_fg();

		                String batch = first.getIssued_lot_batch() == null ? "N/A" :  first.getIssued_lot_batch();
		                result.setIssued_lot_batch(batch);
		                result.setIssued_quantity(roundedQuantity);
		                result.setIssue_date(newDatemin+" - "+newDatemax);
		                result.setIssue_date(newDatemin+" - "+newDatemax);
		                result.setIssued_customer_code(first.getIssued_lot_batch());
		                result.setIssued_customer_name(first.getIssued_customer_name());
		                result.setIssued_product_name(first.getIssued_product_name());
		                result.setIssued_unit(first.getIssued_unit());
		                // ถ้ามีฟิลด์อื่นๆ ก็ copy ตามต้องการ
		                // result.setOtherField(first.getOtherField());

		                return result;
		            })
		            .collect(Collectors.toList());
		 

		 model.addAttribute("customerList",customerList);

		 
		 
		    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime today = LocalDateTime.now();
			String date = today.format(formatters);
			 model.addAttribute("date",date);
			 model.addAttribute("search",search);
			 
		return "/Subdata/traceability_search";
	}


	@RequestMapping(value="/Movement_search-workflow",method = RequestMethod.POST)
	public String Movement_search(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String search
			,@RequestParam String stock_code
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 System.out.println(" stock_code "+stock_code);
	
		 List<Goods_receipt_sk> goods_receipt_sk = EdataDAO.GetGSKSearch(search,stock_code);
		 List<Issue_sk> issue_sk   = EdataDAO.GetISkSearch(search,stock_code);
		
	
		 
		 List<stock> item = new ArrayList<>();
		 DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM");
		 List<LocalDate> datesToLoop = new ArrayList<>();

		 if (search.matches("\\d{4}-\\d{2}")) {
		     // กรณี yyyy-MM
		     YearMonth yearMonth = YearMonth.parse(search, yearMonthFormat);
		     LocalDate today = LocalDate.now();
		     int lengthOfMonth = yearMonth.lengthOfMonth();

		     for (int day = 1; day <= lengthOfMonth; day++) {
		         LocalDate date = yearMonth.atDay(day);
		         if (date.isAfter(today)) break;
		         datesToLoop.add(date);
		     }

		 } else {
		     // กรณีส่งวันเดียว yyyy-MM-dd หรือ ISO-8601
		     try {
		    	 LocalDate date = LocalDate.parse(search);  // << ใช้ตัวนี้แทน
		    	    datesToLoop.add(date);
		     } catch (Exception e) {
		         // ไม่ใช่รูปแบบวัน → ไม่ต้องทำอะไร
		     }
		 }
		 
		 

		 // ✅ ใช้ loop เดียวแบบไม่ต้องแยก else
		 for (LocalDate date : datesToLoop) {
		   
			 List<Goods_receipt_sk> SubSk = goods_receipt_sk.stream()
		         .filter(row -> otherservices.IsStringDate(row.getSkg_received_date()))
		         .filter(row -> {
		             LocalDateTime dt = LocalDateTime.parse(row.getSkg_received_date());
		             return dt.toLocalDate().equals(date);
		         })
		         .collect(Collectors.toList());

		     SubSk.forEach(rows -> {
		    	 
		    	
		    	 
		    	 
		         stock stock = new stock();
		         stock.setStock_receipt_dateupdate(rows.getSkg_received_date());
		         stock.setStock_received(otherservices.StringValueOf(rows.getSkg_quantity_received()));
		         stock.setStock_unit(rows.getSkg_unit());
		         stock.setStock_code(rows.getSkg_stock_code());
		         stock.setStock_list(rows.getSkg_stock_name());
		         stock.setStock_batch(rows.getSkg_lot_batch());
		         stock.setDrawer(rows.getSkg_received_by());
		         stock.setStock_remaining(otherservices.StringValueOf(rows.getSkg_remaining()));
		         stock.setTracking_number(rows.getSkg_tracking_number());
		         
		         List<file> subfiles = FileDAO.getallEdata(rows.getEdata_id());
		         stock.setFile(subfiles);
		         stock.setEdata_id(rows.getEdata_id());
		         item.add(stock);
		     });

		     List<Issue_sk> Subissue_sk = issue_sk.stream() 
		         .filter(row -> otherservices.IsStringDate(row.getSki_date()))
		         .filter(row -> {
		             LocalDateTime dt = LocalDateTime.parse(row.getSki_date());
		             return dt.toLocalDate().equals(date);
		         })
		         .collect(Collectors.toList());

		     Subissue_sk.forEach(rows -> {
		    	 
		    	
		         stock stock = new stock();
		         stock.setStock_receipt_dateupdate(rows.getSki_date());
		         stock.setStock_quantity(otherservices.StringValueOf(rows.getSki_quantity()));
		         stock.setStock_unit(rows.getSki_unit());
		         stock.setStock_code(rows.getSki_stock_code());
		         stock.setStock_list(rows.getSki_stock_name());
		         stock.setStock_batch(rows.getSki_lot_batch());
		         stock.setStock_batch2(rows.getSki_lot_batch2());
		         stock.setDrawer(rows.getSki_by());
		         stock.setPayer(rows.getSki_approved_by());
		         stock.setStock_remaining(otherservices.StringValueOf(rows.getSki_remaining()));
		         stock.setTracking_number(rows.getSki_tracking_number());
		         List<file> subfiles = FileDAO.getallEdata(rows.getEdata_id());
		         stock.setFile(subfiles);
		         stock.setEdata_id(rows.getEdata_id());
		         item.add(stock);
		         
		     });
		     
		     
		     
		     System.out.println("date " + date);
		 }
		        Collections.sort(item, Comparator.comparing(stock::getStock_receipt_dateupdate));
		    	model.addAttribute("id", id);
		    	model.addAttribute("item",item);
		    	model.addAttribute("stock_code",stock_code);
		    	 
		    	 
		return "/Subdata/movement_search";
	}

	
	
	
	@RequestMapping(value="/Movement_search2-workflow",method = RequestMethod.POST)
	public String Movement_search2(
			ModelMap model
			,@RequestParam int id
			,@RequestParam String search
			,@RequestParam String product_code
			) throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		 
		 System.out.println(" product_code "+product_code);
	
		 List<Goods_receipt_fg> goods_receipt_fg = EdataDAO.GetGFGSearch(search,product_code);
		 List<Issue_fg> issue_fg   = EdataDAO.GetIFGSearch(search,product_code);
		
	
		 
		 List<fgstock> item = new ArrayList<>();
		 DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM");
		 List<LocalDate> datesToLoop = new ArrayList<>();

		 if (search.matches("\\d{4}-\\d{2}")) {
		     // กรณี yyyy-MM
		     YearMonth yearMonth = YearMonth.parse(search, yearMonthFormat);
		     LocalDate today = LocalDate.now();
		     int lengthOfMonth = yearMonth.lengthOfMonth();

		     for (int day = 1; day <= lengthOfMonth; day++) {
		         LocalDate date = yearMonth.atDay(day);
		         if (date.isAfter(today)) break;
		         datesToLoop.add(date);
		     }

		 } else {
		     // กรณีส่งวันเดียว yyyy-MM-dd หรือ ISO-8601
		     try {
		    	 LocalDate date = LocalDate.parse(search);  // << ใช้ตัวนี้แทน
		    	    datesToLoop.add(date);
		     } catch (Exception e) {
		         // ไม่ใช่รูปแบบวัน → ไม่ต้องทำอะไร
		     }
		 }
		 
		 

		 // ✅ ใช้ loop เดียวแบบไม่ต้องแยก else
		 for (LocalDate date : datesToLoop) {
		   
			 List<Goods_receipt_fg> SubSk = goods_receipt_fg.stream()
		         .filter(row -> otherservices.IsStringDate(row.getReceipt_date()))
		         .filter(row -> {
		             LocalDateTime dt = LocalDateTime.parse(row.getReceipt_date());
		             return dt.toLocalDate().equals(date);
		         })
		         .collect(Collectors.toList());

		     SubSk.forEach(rows -> {
	
		         fgstock fgstock = new fgstock();
		         fgstock.setFg_receipt_dateupdate(rows.getReceipt_date());
		         fgstock.setFg_received(otherservices.StringValueOf(rows.getGoods_receipt_quantity()));
		         fgstock.setFg_unit(rows.getGoods_receipt_unit());
		         fgstock.setFg_code(rows.getGoods_receipt_customer_code());
		         fgstock.setFg_list(rows.getGoods_receipt_customer_name());
		         fgstock.setFg_batch(rows.getGoods_receipt_lot_batch());
		         fgstock.setDrawer(rows.getReceived_by());
		         fgstock.setFg_remaining(otherservices.StringValueOf(rows.getGoods_receipt_remaining()));
	
		         
		         List<file> subfiles = FileDAO.getallEdata(rows.getEdata_id());
		         fgstock.setFile(subfiles);
		         fgstock.setEdata_id(rows.getEdata_id());
		         item.add(fgstock);
		     });

		     List<Issue_fg> Subissue_sk = issue_fg.stream() 
		         .filter(row -> otherservices.IsStringDate(row.getIssue_date()))
		         .filter(row -> {
		             LocalDateTime dt = LocalDateTime.parse(row.getIssue_date());
		             return dt.toLocalDate().equals(date);
		         })
		         .collect(Collectors.toList());

		     Subissue_sk.forEach(rows -> {
		    	 
		    	
		    	 fgstock fgstock = new fgstock();
		    	 fgstock.setFg_receipt_dateupdate(rows.getIssue_date());
		         fgstock.setFg_quantity(otherservices.StringValueOf(rows.getIssued_quantity()));
		         fgstock.setFg_unit(rows.getIssued_unit());
		         fgstock.setFg_code(rows.getIssued_product_code());
		         fgstock.setFg_list(rows.getIssued_product_name());
		         fgstock.setFg_batch(rows.getIssued_lot_batch());

		         fgstock.setDrawer(rows.getIssued_by());
		         fgstock.setPayer(rows.getApproved_by());
		         fgstock.setFg_remaining(otherservices.StringValueOf(rows.getIssued_remaining()));
		         List<file> subfiles = FileDAO.getallEdata(rows.getEdata_id());
		         fgstock.setFile(subfiles);
		         fgstock.setEdata_id(rows.getEdata_id());
		         item.add(fgstock);
		         
		     });
		     
		     
		     
		     System.out.println("date " + date);
		 }
		        Collections.sort(item, Comparator.comparing(fgstock::getFg_receipt_dateupdate));
		    	model.addAttribute("id", id);
		    	model.addAttribute("item",item);
		    	model.addAttribute("product_code",product_code);
		    	 
		    	 
		return "/Subdata/movement_search2";
	}

	
	

	@RequestMapping(value="/savereportcustom-workflow",method = RequestMethod.POST)
	public String savereportcustom(
			customreport customreports
			)  {
		 WorkflowDAO.saveCustomreport(customreports);
		 return "redirect:/customreport-workflow/"+customreports.getReport_app();
	}
	
	
	@RequestMapping(value="/editreportcustom-workflow",method = RequestMethod.POST)
	public String editreportcustom(
			customreport customreports
			)  {
		 WorkflowDAO.UpdateCustomreport(customreports);

		
		 return "redirect:/settingcustomerreport-workflow/"+customreports.getReport_app()+"/"+customreports.getId();
	}
	
	
	@RequestMapping(value="/editjoinreportcustom-workflow",method = RequestMethod.POST)
	public String editjoinreportcustom(
			ReportJoinConfig join,
			 RedirectAttributes redirectAttributes,
			 @RequestParam int report_id,
			 @RequestParam int report_app
			)  {
		
		
		  ReportJoinConfig existing = reportJoinConfigRepository.findById(join.getId())
			    .orElseThrow(() -> new RuntimeException("ไม่พบข้อมูล"));
		

		    if (join.getKey1Table1() != null) existing.setKey1Table1(join.getKey1Table1());
		    if (join.getKey1Table2() != null) existing.setKey1Table2(join.getKey1Table2());

		    if (join.getKey2Table1() != null) existing.setKey2Table1(join.getKey2Table1());
		    if (join.getKey2Table2() != null) existing.setKey2Table2(join.getKey2Table2());

		    if (join.getKey3Table1() != null) existing.setKey3Table1(join.getKey3Table1());
		    if (join.getKey3Table2() != null) existing.setKey3Table2(join.getKey3Table2());
		  
			reportJoinConfigRepository.save(existing);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		
		 return "redirect:/settingcustomerreport-workflow/"+report_app+"/"+report_id;
	}
	
	
	@RequestMapping(value="/deletejoinreportcustom-workflow",method = RequestMethod.POST)
	public String deletejoinreportcustom(
			ReportJoinConfig join,
			 RedirectAttributes redirectAttributes,
			 @RequestParam int report_id,
			 @RequestParam int report_app
			)  {
		
			reportJoinConfigRepository.delete(join);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		
		 return "redirect:/settingcustomerreport-workflow/"+report_app+"/"+report_id;
	}
	
	
	

	@RequestMapping(value="/editreportcustoms-workflow",method = RequestMethod.POST)
	public String editreportcustoms(
			 RedirectAttributes redirectAttributes,
			customreport customreports
			)  {
		 	WorkflowDAO.UpdateCustomreports(customreports);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		
			
		 return "redirect:/settingcustomerreport-workflow/"+customreports.getReport_app()+"/"+customreports.getId();
	}
	
	
	@RequestMapping(value="/savejoinreportcustom-workflow",method = RequestMethod.POST)
	public String savejoinreportcustom(
			 RedirectAttributes redirectAttributes,
			 ReportJoinConfig Join,
			 @RequestParam int reportSettingId,
			customreport customreports
			)  {
		
		
	
		   	 customreport customreport = WorkflowDAO.getCustomreport(reportSettingId);
		   	 CustomReports Report = new CustomReports();
		   	 Report.setId(customreport.getId());
		   	 Join.setCustomReports(Report); // <-- ต้อง set object นี้ให้ JPA ใช้ตอน save

		
			reportJoinConfigRepository.save(Join);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		
		 return "redirect:/settingcustomerreport-workflow/"+customreports.getReport_app()+"/"+Join.getReportSettingId();
	}
	
	
	
	
	
	
	@RequestMapping(value="/editreportcustom2-workflow",method = RequestMethod.POST)
	public String editreportcustom2(
			customreport customreports
			)  {
		 WorkflowDAO.saveCustomreport(customreports);

		
		 return "redirect:/settingcustomerreport2-workflow/"+customreports.getReport_app()+"/"+customreports.getId();
	}
	
	
	
	@RequestMapping(value="/deletecustomerreport-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String saveaddcustomer(
			ModelMap model,
			@PathVariable int id,
			@PathVariable int workflow_id
			)  {
		 WorkflowDAO.DelCustomreport(id);
		 return "redirect:/customreport-workflow/"+workflow_id;
	}
	
	
	@RequestMapping(value="/viewcustomerreport-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String viewcustomreport(
			ModelMap model,
			@PathVariable int id,
			@PathVariable int workflow_id,
			@RequestParam(defaultValue ="0",value = "iframe",required=false) String iframe
			)  {
		
		
		
		 customreport customreports = WorkflowDAO.getCustomreport(id);
		 model.addAttribute("customreports",customreports);  
		
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 model.addAttribute("workflows",workflows);
		 
		 if(customreports.getReport_table() > 0) {
			 getCustomReport(customreports,model,customreports.getDisplay());
		 }
		 
		 model.addAttribute("iframe",iframe);
	
		  long reportJoinConfig = reportJoinConfigRepository.countByReportSettingId(customreports.getId());       
		 if(reportJoinConfig > 0) {
			 return "/workflow/viewjoincustomreport";
		 }else {
				
				return "/workflow/viewcustomreport";
		 }
		 
	
	}
	
	
	
		private static boolean isValid(String value) {
		    return value != null && !value.equals("-") && !value.equals("0");
		}
		
	private void getCustomReport(customreport customreports, ModelMap model,int display) {
		 process process  = EdataDAO.getProcess(customreports.getReport_table());
		 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
		 
		 
		 Optional<sub_processes> action = sub_process.stream()
				    .filter(row -> {
				        String a1 = row.getAction();
				        String a2 = row.getActivate_reference_continue();
				        String a3 = row.getActivate_reference_modify();
				        String a4 = row.getActivate_reference_only();
				        return isValid(a1) || isValid(a2) || isValid(a3) || isValid(a4);
				    })
				    .findFirst();
	  

		 
		   if(action.isPresent()) {
			   sub_processes  subprocess = action.get();
			   
			   String type = null;
			   
			   if (isValid(subprocess.getAction())) {
			        type = subprocess.getAction();
			    } else if (isValid(subprocess.getActivate_reference_continue())) {
			        type = subprocess.getActivate_reference_continue();
			    } else if (isValid(subprocess.getActivate_reference_modify())) {
			        type = subprocess.getActivate_reference_modify();
			    } else if (isValid(subprocess.getActivate_reference_only())) {
			        type = subprocess.getActivate_reference_only();
			    }
				 
			   
			   
			   List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId());
				condition conditions = ConditionDAO.getbyid(process.getId_condition());
		    	model.addAttribute("conditions",conditions);  
		    	List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
		      	
		    	Iterator<eformfield> iterator = eformfields.iterator();

		    	while (iterator.hasNext()) {
		    	    eformfield row = iterator.next();
		    	    
		    	    Optional<keymap> data = keymaps.stream()
		    	        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
		    	        .filter(subrow -> subrow.getType().equals("create"))
		    	        .filter(subrow -> !subrow.getEformkey().equals("0") && !subrow.getEformkey().equals("-"))
		    	        .findFirst();

		    	    if (data.isPresent()) {
		    	        keymap keymap = data.get();

		    	        // ลบ row ทิ้งถ้า keymap.getMainkey() เป็น "procurement_grandtotal" หรือ "procurement_grandtotal2"
		    	        if (keymap.getMainkey().equals("procurement_grandtotal") || keymap.getMainkey().equals("procurement_grandtotal2")) {
		    	            iterator.remove();  // ลบ row นี้ออกจาก eformfields
		    	        } else {
		    	            row.setKeymap(keymap);
		    	            List<dataset_data> dataset_data = Dataset_dataDAO.getall(keymap.getEnum_id(), keymap.getFilter_enum());
		    	            row.setDataset_data(dataset_data);
		    	        }
		    	    }
		    	}
		    	

		    	
		    	
		     System.out.println(" eformfields "+eformfields);
		    	
		   
		      	if(display == 1) {}  
		  

		  
				
					   
		    		customreports.setEformfield(eformfields);	    	
					    	
		          	List<String> autoid = new ArrayList<String>(); 
		        	sub_process.stream()
					.filter(row -> row.getAutoiddatacolumn() != null)
					.forEach(row->{
						autoid.add(row.getAutoiddatacolumn());
						
					});
		    		
		        	
		        	// binning ข้อมูล
		        	
		        	
		      
		        	 CustomReport CustomReport = new CustomReport.Builder()
		        			    .columnValue(customreports.getBinning_value())
		        			    .columnDate(customreports.getBinning_date())
		        			    .operation(customreports.getBinning_operation())
		        			    .group(customreports.getBinning_group())
		        			    .title(customreports.getBinning_title())
		        			    .binning(customreports.getBinning_range())
		        			    .build();
		        
	
		  List<ReportJoinConfig> reportJoinConfig = reportJoinConfigRepository.findByReportSettingId(customreports.getId());       
		  if(reportJoinConfig.size() > 0) {
			  try {       	 
				   Map<String, List<?>> transactionMap = new LinkedHashMap<>();
				   Map<String, List<edata>> edataMap = new LinkedHashMap<>();
				   Map<String, List<keymap>> keymapChainMap =  new LinkedHashMap<>();
				
		
				   
				   // เตรียม root
				   String table1 = Filemanagerservice.getTable(process.getId());
				   
				   customreport sqlfilter = new customreport();
				   sqlfilter.Filter(customreports);
				 
				   
				   transactionMap.put(table1, Filemanagerservice.getTransaction(process.getId(),sqlfilter,null, type));
				   edataMap.put(table1, Filemanagerservice.getEdata(process.getId(),null, type));

				   for (ReportJoinConfig config : reportJoinConfig) {
				       String my_table = config.getTable1Name().split("_")[0];
				       process my_process = EdataDAO.getProcess(Integer.valueOf(my_table));
				       if (my_process == null) {
				           System.err.println("Process null for table1: " + my_table);
				           continue;
				       }
				       String tableA = Filemanagerservice.getTable(my_process.getId());

				       String my_table2 = config.getTable2Name().split("_")[0];
				       process my_process2 = EdataDAO.getProcess(Integer.valueOf(my_table2));
				       if (my_process2 == null) {
				           System.err.println("Process null for table2: " + my_table2);
				           continue;
				       }
				       String tableB = Filemanagerservice.getTable(my_process2.getId());

				       List<keymap> parentkeymaps = EdataDAO.getAllKeyMaps(process.getId());
				       if (parentkeymaps == null || parentkeymaps.isEmpty()) {
				           System.err.println("Keymap not found for: " + tableA);
				           continue;
				       }
				     //  keymapChainMap.put(tableA, parentkeymaps);

				       // ดึงข้อมูลแม่
				       List<?> parentTransaction = transactionMap.get(tableA);
				       if (parentTransaction == null || parentTransaction.isEmpty()) {
				           System.err.println("No parent data for table: " + tableA);
				           continue;
				       }

				       // ถ้ายังไม่โหลดลูก
				       if (!transactionMap.containsKey(tableB)) {
				           List<?> childData = Filemanagerservice.getTransaction(my_process2.getId(),null, null, tableB);
				           List<edata> childEdata = Filemanagerservice.getEdata(my_process2.getId(),null, type);
				           if (childData == null || childData.isEmpty()) {
				        	   
				        	   System.err.println(" child my_process " + my_process2.getId());

				        	   
				               System.err.println("No child data for table: " + tableB);
				               continue;
				           }
				           transactionMap.put(tableB, childData);
				           edataMap.put(tableB, childEdata);
				           
					       List<keymap> parentkeymaps2 = EdataDAO.getAllKeyMaps(my_process2.getId());
					       if (parentkeymaps2 == null || parentkeymaps2.isEmpty()) {
					           System.err.println("Keymap not found for: " + tableB);
					           continue;
					       }
					       keymapChainMap.put(tableB, parentkeymaps2);
				       }

				       List<?> childTransaction = transactionMap.get(tableB);
				       if (childTransaction == null || childTransaction.isEmpty()) {
				           System.err.println("Child transaction empty for: " + tableB);
				           continue;
				       }

				       // Join
				       Filemanagerservice.JoinTable(parentTransaction, childTransaction, config);
				   }

				   List<String> chainFields = keymapChainMap.entrySet().stream()
						    .flatMap(e -> e.getValue().stream())
						    .filter(k -> otherservices.IntegerValueOf( k.getEformkey()) > 0)
						    .map(keymap::getMainkey)
						    .collect(Collectors.toList());
				   
				   List<String> mainFields = eformfields.stream()
						    .filter(field -> field.getKeymap() != null)
						    .map(field -> field.getKeymap().getMainkey())
						    .collect(Collectors.toList());
	
				   List<String> extraFields = List.of( "year", "target", "month1", "month2", "month3", "month4", "month5", "month6", "month7", "month8", "month9", "month10", "month11", "month12");
				   List<String> extraFields2 = List.of( "count","summarize", "variance", "standard_deviation", "rsd", "title", "binning", "frequency");
				   RecursiveJoinProcessor processor = new RecursiveJoinProcessor();
				   List<Object> tableData = (List<Object>) transactionMap.get(table1);
				   
				   List<Map<String, String>> resultTable = processor.buildData(tableData, mainFields, chainFields,extraFields,extraFields2);

			
				   List<FieldMetaDTO> fieldsToPrint = new ArrayList<>();
				   List<FieldMetaDTO> uniqueFields =  processor.AddFieldsToPrint(otherservices,fieldsToPrint,eformfields,keymapChainMap,extraFields,extraFields2);
				   

				   
				   /*
				   for (Map<String, String> row : resultTable) {
					    for (FieldMetaDTO field : fieldsToPrint) {
					        String raw = row.getOrDefault(field.getKey(), "");
					        System.out.print(field.getKey() + " : " + raw + " \t");
					    }
					    System.out.println();
					}
				   */

				   
				   
				   resultTable = Filemanagerservice.applyPivotAndEDA(resultTable,customreports,CustomReport,type);
				   
				 	model.addAttribute("fields",eformfields);
					model.addAttribute("resultTable",resultTable);
					model.addAttribute("fieldsToPrint",uniqueFields); 
				   
				
				} catch(Exception e) {
				   System.err.println("Error: " + e.getMessage());
				   e.printStackTrace();
				}
		  }else {
			  
			 List<String> exclude = List.of("PL", "PD", "production_record", "QCI", "OTHER", "PR", "PO", "GR", "BOM", "PS");
			
			 if (!exclude.contains(type)) {
				 	List<Map<String, String>> transaction =   Filemanagerservice.getTransactionMap(process.getId(),customreports,CustomReport,type);
					 model.addAttribute("eformfields",eformfields);
				} else {
					List<?> transaction =   Filemanagerservice.getTransaction(process.getId(),customreports,CustomReport,type);
					 model.addAttribute("eformfields",eformfields);
				}
		
	
			List<edata> edata =   Filemanagerservice.getEdata(process.getId(),customreports,type);
			
			 model.addAttribute("edata",edata);
			 model.addAttribute("eformfields",eformfields);
			
			
				   
		  }
		   
		   
		   model.addAttribute("autoid",autoid); 
		    		
		   }
		
	}




	private <T> List<T> applyPivotAndEDA(List<T> data, CustomReport CustomReport,customreport customreports) {
		
	
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

		 
	    return data;
	}




	@RequestMapping(value="/settingcustomerreport-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String settingcustomerreport(
			ModelMap model,
			@PathVariable int id,
			@PathVariable int workflow_id) 
					throws IOException {
		
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 model.addAttribute("workflows",workflows);   
		 
		 customreport customreport = WorkflowDAO.getCustomreport(id);
		 model.addAttribute("customreport",customreport);   
		 
		 model.addAttribute("id",id);  
		 
		 List<process> data  = EdataDAO.getAllProcess(workflow_id);
		 model.addAttribute("data",data);  
		 
		 List<company_annual_plan> annual_plan  =  company_annual_planRepository.findTop500ByOrderByIdDesc();
		 model.addAttribute("annual_plan",annual_plan);  
		 
		 process process =  EdataDAO.getProcess(customreport.getReport_table());
		 if(process != null) {
			 
			 
		    	condition condition = ConditionDAO.getbyid(process.getId_condition());
		    	
		    	if(condition != null) {
			    	List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(),condition.getCondition_rv()); 
			    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId_condition());
			    	
			    	
			    	eformfields.stream()
			    	 .forEach(row->{
			    		Optional<keymap> datas = keymaps.stream()
			    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
			    			    .filter(subrow -> "create".equals(subrow.getType()))
			    			    .findFirst()
			    			    .or(() -> keymaps.stream() // ถ้าไม่มี "action" ให้หาค่าที่ตรงกับ Field_id โดยไม่สนใจ action_type
			    			        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
			    			        .findFirst());
					    	
						    	if(datas.isPresent()) {
						    		keymap keymap = datas.get();
						    		row.setKeymap(keymap);	
						    	} 	
				    	});    	
						 
			    	
					model.addAttribute("eformfields",eformfields); 
		    	}
		    	
		    	

		    	 List<process> process1 =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
		    			 
		    	 	process1.stream().forEach(row->{
		    				
		    					condition conditioned = ConditionDAO.getbyid(row.getId_condition());
		    					row.setCondition(conditioned);
		    					
		    					if(row.getProcess_join() > 0) {
		    				    process join = EdataDAO.getProcess(row.getProcess_join());
		    				    row.setNote("Join ->"+join.getName());
		    					}
		    			 });
		    			 
		    			 model.addAttribute("process1",process1); 
		    			  
		    			 
		    			 
		    	List<process> process2 =  EdataDAO.getAllProcessJoin(workflows.getWorkflow_id());
		    			 
		    			 process2.stream().forEach(row->{
		    					workflow join = WorkflowDAO.getbyid(row.getId_refer());
		    				    row.setNote(join.getWorkflow_name());
		    			 });
		    			 
		    			 model.addAttribute("process2",process2); 

		    }
		 
		 
		 List<ReportJoinConfig> Join =  reportJoinConfigRepository.findByReportSettingId((long)id);
		 model.addAttribute("Join",Join); 
		 model.addAttribute("process",process);  
			return "/workflow/settingcustomerreport";
	     }
	
	
	
	
	

	@RequestMapping(value="/editjoincustomerreport-workflow/{workflow_id}/{joinid}",method = RequestMethod.GET)
	public String editjoincustomerreport(
			ModelMap model,
			@PathVariable int workflow_id,
			@PathVariable long joinid) 
					throws IOException {
		
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 model.addAttribute("workflows",workflows);   
		 
		 ReportJoinConfig settingJoin =  reportJoinConfigRepository.findById(joinid).orElse(null);
		 
		String my_table =  settingJoin.getTable2Name().split("_")[0];
		process my_process =  EdataDAO.getProcess(Integer.valueOf(my_table));
		condition my_conditioned = ConditionDAO.getbyid(my_process.getId_condition());
		
		
		List<eformfield> my_eformfields = EformfieldDAO.getall(my_conditioned.getCondition_id(),my_conditioned.getCondition_rv()); 
		List<keymap> my_keymaps = EdataDAO.getAllKeyMaps(my_process.getId());
		
		

		
		my_eformfields.stream()
    	 .forEach(row->{
    		Optional<keymap> datas = my_keymaps.stream()
    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
    			    .filter(subrow -> "create".equals(subrow.getType()))
    			    .findFirst()
    			    .or(() -> my_keymaps.stream() // ถ้าไม่มี "action" ให้หาค่าที่ตรงกับ Field_id โดยไม่สนใจ action_type
    			        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
    			        .findFirst());
		    	
			    	if(datas.isPresent()) {
			    		keymap keymap = datas.get();
			    		row.setKeymap(keymap);	
			    	} 	
	    	});    	
		

		 my_eformfields.forEach(field -> field.setField_label(settingJoin.getTable2Name()+"."+field.getField_label()));
		 
		 model.addAttribute("settingJoin",settingJoin);   
		 model.addAttribute("my_eformfields",my_eformfields);   
		 
		 
		 List<ReportJoinConfig> Join =  reportJoinConfigRepository.findByReportSettingId(settingJoin.getReportSettingId());
			
		     String my_table1 =  settingJoin.getTable1Name().split("_")[0];
		    process mainprocess =  EdataDAO.getProcess(Integer.valueOf(my_table1));
			condition main_conditioned = ConditionDAO.getbyid(mainprocess.getId_condition());
			List<eformfield> main_eformfields = EformfieldDAO.getall(main_conditioned.getCondition_id(),main_conditioned.getCondition_rv()); 
			
			List<keymap> main_keymaps = EdataDAO.getAllKeyMaps(mainprocess.getId());
			main_eformfields.stream()
	    	 .forEach(row->{
	    		Optional<keymap> datas = main_keymaps.stream()
	    			    .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			    .filter(subrow -> "create".equals(subrow.getType()))
	    			    .findFirst()
	    			    .or(() -> main_keymaps.stream() // ถ้าไม่มี "action" ให้หาค่าที่ตรงกับ Field_id โดยไม่สนใจ action_type
	    			        .filter(subrow -> Integer.valueOf(subrow.getEformkey()) == row.getField_id())
	    			        .findFirst());
			    	
				    	if(datas.isPresent()) {
				    		keymap keymap = datas.get();
				    		row.setKeymap(keymap);	
				    	} 	
		    	});  
			
			
			main_eformfields.forEach(field -> field.setField_label(settingJoin.getTable1Name()+"_"+mainprocess.getName()+"."+field.getField_label()));
			
		
		 
		 model.addAttribute("main_eformfields",main_eformfields);   
		 return "/workflow/editjoincustomerreport";
	     }
	
	
	
	
	
	
	

	@RequestMapping(value="/settingcustomerreport2-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String settingcustomerreport2(
			ModelMap model,
			@PathVariable int id,
			@PathVariable int workflow_id) 
					throws IOException {
		
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 model.addAttribute("workflows",workflows);   
		 
		 customreport customreport = WorkflowDAO.getCustomreport(id);
		 
		 List<String> customreport_level = new ArrayList<>();
		 
		 
		 if(customreport.getLevel() != null) {
			 customreport_level.addAll(Arrays.asList(customreport.getLevel().split(",")));
			 model.addAttribute("customreport_level",customreport_level);    
		 }
		
		 
		 
		 
		 model.addAttribute("customreport",customreport);   
		 
		 
		 model.addAttribute("id",id);  
		 
		 List<process> data  = EdataDAO.getAllProcess(workflow_id);
		 model.addAttribute("data",data);  
		 
		 
		 if(customreport.getReport_table() > 0) {
			 getCustomReport(customreport,model,customreport.getDisplay());
		 }
		 

		 
		 
			return "/workflow/settingcustomerreport2";
	     }
	
	
	
	
	
	
	@RequestMapping(value="/traceabilityapp-workflow/{id}",method = RequestMethod.GET)
	public String traceabilityapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);

		 workflow workflows = WorkflowDAO.getbyid(id);
		 List<workflow> all_workflows = WorkflowDAO.getallApp();
		 
		List<trace> traces = WorkflowDAO.getAllTrace(id);
		 
		
		 model.addAttribute("traces",traces);   
		 model.addAttribute("all_workflows",all_workflows);  
		 model.addAttribute("workflows",workflows);  
		 
		return "/workflow/traceabilityapp";
	}
	
	
	
	@RequestMapping(value="/savetraceability-workflow",method = RequestMethod.POST)
	public String savetraceability(trace traces) 
				{
		 WorkflowDAO.saveTrace(traces);
		 return "redirect:/traceabilityapp-workflow/"+traces.getTrace_app();
	}
	
	@RequestMapping(value="/deltrace-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String deltrace(@PathVariable int workflow_id,@PathVariable int id) 
				{
		  WorkflowDAO.DelTrace(id);
		 return "redirect:/traceabilityapp-workflow/"+workflow_id;
			}
	
	

	@RequestMapping(value="/edittrace-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String edittrace(
			ModelMap model,@PathVariable int workflow_id,@PathVariable int id) {
	
		 MainServices.addNotificationAttributes(model);

		 System.out.println("------------1");
		 
		 List<workflow> all_workflows = WorkflowDAO.getallApp();
		 model.addAttribute("all_workflows",all_workflows);  
		 
		 trace traces = WorkflowDAO.getTrace(id);
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 List<process> process =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
		 model.addAttribute("workflows",workflows); 
		 model.addAttribute("traces",traces); 
		 
		 model.addAttribute("process",process);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
		 dataset datasets  = DatasetDAO.getMain("Processes");
		 List<dataset_data> Process = Dataset_dataDAO.getall(datasets.getDataset_id());
		 model.addAttribute("Process",Process);  
	  
	     List<FGMovementType> FGMovement = Arrays.asList(FGMovementType.values());
		 model.addAttribute("FGMovement", FGMovement);
		 
		  List<SKMovementType> SKMovement = Arrays.asList(SKMovementType.values());
		 model.addAttribute("SKMovement", SKMovement);
		 
		return "/workflow/edittrace";
	}
	
	
	@RequestMapping(value="/settingtraceability-workflow/{id}",method = RequestMethod.GET)
	public String settingtraceability(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
	
		 model.addAttribute("workflows",workflows); 
		 
		 

		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
	  
		return "/workflow/settingtraceabilityapp";
	}
	
	
	
	
	@RequestMapping(value="/tracking-workflow/{id}",method = RequestMethod.GET)
	public String tracking(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);

		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 String[] tracking_step = workflows.getTracking_step().split(",");
		 List<process> data  = new ArrayList<process>();
		 
		if(workflows.getTracking_process_type() != null) {
			 int edata_id = Integer.valueOf(workflows.getTracking_process_type());
			 for(String tracking : tracking_step) {
				 List<edata_production_record> process  =  EdataDAO.getTrackingProcess(tracking,edata_id); 
				 data = Filemanagerservice.getTracking(workflows,model,data,process,tracking);
			 }	 
		}
		
		
		 model.addAttribute("data",data);  
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
	  
		return "/workflow/tracking";
	}
	
	
	
	
	
	@RequestMapping(value="/settingtrackingapp-workflow/{id}",method = RequestMethod.GET)
	public String settingtrackingapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 List<process> process =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
		 model.addAttribute("workflows",workflows); 
		 
		 
		 model.addAttribute("process",process);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
	
		 dataset datasets  = DatasetDAO.getMain("Processes");
		 List<dataset_data> Process = Dataset_dataDAO.getall(datasets.getDataset_id());
		 model.addAttribute("Process",Process);  
	  
		return "/workflow/settingtrackingapp";
	}

	@RequestMapping(value="/settingtrackingapp-workflow",method = RequestMethod.POST)
	public String settingtracks(
			workflow workflow,
			@RequestParam int workflow_id
			) 
					throws IOException {

		
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 workflows.setTracking_status(workflow.getTracking_status());
		 workflows.setTracking_step(workflow.getTracking_step());
		 workflows.setTracking_type(workflow.getTracking_type());
		 workflows.setTracking_process_type(workflow.getTracking_process_type());
		 
		 workflows.setTracking_main_status(workflow.getTracking_main_status());
		 workflows.setTracking_main_step(workflow.getTracking_main_step());
		 workflows.setTracking_main_type(workflow.getTracking_main_type());
		 
		 
		 WorkflowDAO.update(workflows);


		 return "redirect:/settingtrackingapp-workflow/"+workflow_id;
	}

	@RequestMapping(value="/settingtraceabilityapp-workflow",method = RequestMethod.POST)
	public String settingtrackingapp(
			workflow workflow,
			@RequestParam int workflow_id
			) 
					throws IOException {

		
		 workflow workflows = WorkflowDAO.getbyid(workflow_id);
		 workflows.setTraceability_status(workflow.getTraceability_status());
		 WorkflowDAO.update(workflows);

		 return "redirect:/traceabilityapp-workflow/"+workflow_id;
	}

	
	
	

	
	
	@RequestMapping(value="/workload-workflow/{id}",method = RequestMethod.GET)
	public String workload(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  AtomicReference<Integer> notice = new AtomicReference<>(0);
		  
		  List<condition> flow = ConditionDAO.getAllbylink(id);

		  flow.stream().forEach(row -> {
			  
			  
			 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(row.getDatalink_idrefer());
			 
			 System.err.println("idrefer "+row.getDatalink_idrefer());
			 System.err.println("upload_pn "+upload_pn.getUpload_pn_id());
			 
			 
			 if(upload_pn.getUpload_pn_id() == 0) {
				 notice.set(notice.get() + 1);
			 }
			 
			 
		      List<eformfield> eformfield = eformfields.stream()
		          .filter(sub -> sub.getField_formid() == row.getCondition_id())
		          .filter(sub -> sub.getField_formrv() == row.getCondition_rv())
		          .collect(Collectors.toList());
		      row.setEformfields(eformfield);
		  });

		  int error = validateDatalinkName(flow);

		  
		  

		  
		  
		  
		  user creator = UserDAO.getbyId(workflows.getWorkflow_idadd(),0);
		  model.addAttribute("creator",creator); 
		  
		  
			 List<process> process =  EdataDAO.getAllProcessBytype(workflows.getWorkflow_id(),0);


			 System.out.println(" process "+process.size());
			 
			 process.stream().forEach(row->{
				 List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(row.getId());
				 List<policies> Policies =  EdataDAO.getAllMainPolicies(row.getId());
				 row.setPolicies(Policies);
				 row.setSub_processes(sub_processes);
			 });
			 
			 
			 
			  List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);
			 
			 model.addAttribute("process",process); 
			 
			 List<groups> groupss = GroupsDAO.getall(0);
			 model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
			
			
		String tempId = UUID.randomUUID().toString(); 
		model.addAttribute("group_id",tempId);  
			
			
			 
		  model.addAttribute("notice",notice.get()); 
		  model.addAttribute("error",error); 
		  model.addAttribute("flow",flow); 
	  
		return "/workflow/workload";
	}
	
	
	
	
	
	@RequestMapping(value="/indexscope-workflow/{id}",method = RequestMethod.GET)
	public String indexscope(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	 
		  
		  
			 List<process> process =  EdataDAO.getAllProcessBytype(workflows.getWorkflow_id(),2);
			 model.addAttribute("process",process); 
			 
			 
			 process.stream().forEach(row->{
				  List<UseExtension> extension = EdataDAO.getallUseExtension(row.getId());
			 	  row.setExtension(extension);
			 });
			 
			 List<Extension> extension =  EdataDAO.getAllExtensionBytype("register");
			 model.addAttribute("extension",extension);

	  
		return "/workflow/scope";
	}
	
	
	@RequestMapping(value="/Managementscope-workflow/{id}",method = RequestMethod.GET)
	public String Managementscope(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	 
		  
		  
		List<process> process =  EdataDAO.getAllProcessBytype(workflows.getWorkflow_id(),1);
		 process.stream().forEach(row->{
			  List<Management> managements = EdataDAO.getallManagement(row.getId());
		 	  row.setManagements(managements);
		 });
		 
	
		  model.addAttribute("process",process); 
		  

		  
		Platform platform = new Platform();
		platform.setApp_workflow_id(id);
		
		LoadApp loadApp = new LoadApp();
		
		platformService.initiate(loadApp).LoadComponent(platform).link().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);	 


		
	
	  
		return "/workflow/Managementscope";
	}
	
	
	

	@RequestMapping(value="/delextension-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delextension(
			@PathVariable int workflow_id,
			@PathVariable int id) {
				EdataDAO.removeUseExtension(id);
		return "redirect:/indexscope-workflow/"+workflow_id;
	}
	
	
	@RequestMapping(value="/delmanagements-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delmanagements(
			@PathVariable int workflow_id,
			@PathVariable int id) {
				EdataDAO.removeManagements(id);
		return "redirect:/Managementscope-workflow/"+workflow_id;
	}
	
	
	
	
	
	@RequestMapping(value="/editextension-workflow/{workflow_id}/{id}/{process_id}",method = RequestMethod.GET)
	public String delextension(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int id
					,@PathVariable int process_id
					){
						
	    	process process =  EdataDAO.getProcess(process_id);
	    	model.addAttribute("process",process);
	    	
			workflow workflows = WorkflowDAO.getbyid(workflow_id);
			model.addAttribute("workflows",workflows);  
		
			
			UseExtension  useextension =  EdataDAO.getUseExtension(id);
			model.addAttribute("useextension",useextension);  
			
			Extension  extension =  EdataDAO.getExtension(useextension.getExtension_id());
			model.addAttribute("extension",extension);  
			

			List<condition> conditions = ConditionDAO.getall();
			model.addAttribute("conditions",conditions); 
			
			if(useextension.getEform_id() > 0) {
				condition condition = ConditionDAO.getbyid(useextension.getEform_id());
				 List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(), condition.getCondition_rv());
				 model.addAttribute("eformfields",eformfields); 
			}else if(useextension.getUse_process_id()  > 0){
				
				 process Useprocess =  EdataDAO.getProcess(useextension.getUse_process_id());
				 condition condition = ConditionDAO.getbyid(Useprocess.getId_condition());
				 List<eformfield> eformfields = EformfieldDAO.getall(condition.getCondition_id(), condition.getCondition_rv());
				 model.addAttribute("eformfields",eformfields); 
			}

			  List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);

			 
			 List<groups> groupss = GroupsDAO.getall(0);
			 model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
				
			List<process> processall =  EdataDAO.findAllProcessAllowLooseLink();
			model.addAttribute("processall",processall); 

			List<dataset> datasets  = DatasetDAO.getallNotmain();
			model.addAttribute("datasets",datasets); 
			  
				
			List<ExtensionParameter> ExtensionParameter = EdataDAO.getallExtensionParameterByuseextension(useextension.getId());
			if(ExtensionParameter.size() > 0) {
			model.addAttribute("ExtensionParameter",ExtensionParameter); 
			}	
				
				return "/workflow/useextension";
			}

	
	
	

	@RequestMapping(value="/editmanagements-workflow/{workflow_id}/{id}/{process_id}",method = RequestMethod.GET)
	public String editmanagements(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int id
					,@PathVariable int process_id
					){
						
	    	process process =  EdataDAO.getProcess(process_id);
	    	model.addAttribute("process",process);
	    	
			workflow workflows = WorkflowDAO.getbyid(workflow_id);
			model.addAttribute("workflows",workflows);  
		
			
			Management  management =  EdataDAO.getManagement(id);
			model.addAttribute("management",management);  
			
		
			  List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);

			 
			 List<groups> groupss = GroupsDAO.getall(0);
			 model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
				
			List<process> processall =  EdataDAO.findAllProcessAllowLooseLink();
			model.addAttribute("processall",processall); 

			List<dataset> datasets  = DatasetDAO.getallNotmain();
			model.addAttribute("datasets",datasets); 
			  
				
		
				return "/workflow/managements";
			}

	
	@RequestMapping(value="/documentbox-workflow/{workflow_id}/{management_id}/{process_id}",method = RequestMethod.GET)
	public String documentbox(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int management_id
					,@PathVariable int process_id
					){
		
			List<TabManagement> tab  =  EdataDAO.getallTabManagement(management_id);
			model.addAttribute("tab",tab); 
			model.addAttribute("workflow_id",workflow_id); 
			
			
			process process =  EdataDAO.getProcess(process_id);
	    	model.addAttribute("process",process);    	
			model.addAttribute("process_id",process_id); 
			
			Management  management =  EdataDAO.getManagement(management_id);
			model.addAttribute("management",management);  
			model.addAttribute("management_id",management_id);  
			
			
			
			dataset dataset = DatasetDAO.getMain("Workflow_status");
			List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
			model.addAttribute("dataset_data", dataset_data);
			
			
			process chainprocess = EdataDAO.getProcess(management.getProcess_id());
			condition Chain_Condition = ConditionDAO.getbyid(chainprocess.getId_condition());
			List<eformfield> eformfields = EformfieldDAO.getall(Chain_Condition.getCondition_id(),Chain_Condition.getCondition_rv()); 
		
			
			
			int i = 0;
		 	for(eformfield row : eformfields) {
		 		i++;
		 		row.setField_cal_type("M-");
		 		row.setField_datarefercols("data_value"+i);
		 		System.out.println(" Field_id "+row.getField_id());
		 	}


			List<BuildIn> BuildIn = EformfieldDAO.getallBuildIn(management_id); 
			
	
			for(BuildIn row : BuildIn) {
				
				eformfield field = new eformfield();
				field.setField_label(row.getBuildin_title());
				field.setField_cal_type("S-");
				field.setField_id(row.getId());
				field.setField_datarefercols("columns_value"+row.getTocolumn());
				eformfields.add(field);
				
			}
			model.addAttribute("eformfields", eformfields);
			
					return "/workflow/documentbox";
		
			}		
	
	
	
	
	
	
	
	@RequestMapping(value="/aggregatedmanagements-workflow/{workflow_id}/{management_id}/{process_id}",method = RequestMethod.GET)
	public String aggregatedmanagements(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int management_id
					,@PathVariable int process_id
					){
		
		

			model.addAttribute("workflow_id",workflow_id); 
			
			
			process process =  EdataDAO.getProcess(process_id);
	    	model.addAttribute("process",process);    	
			model.addAttribute("process_id",process_id); 
			
			Management  management =  EdataDAO.getManagement(management_id);
			model.addAttribute("management",management);  
			model.addAttribute("management_id",management_id);  
			
			
			
			dataset dataset = DatasetDAO.getMain("Workflow_status");
			List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
			model.addAttribute("dataset_data", dataset_data);
			
			
			process chainprocess = EdataDAO.getProcess(management.getProcess_id());
			condition Chain_Condition = ConditionDAO.getbyid(chainprocess.getId_condition());
			List<eformfield> eformfields = EformfieldDAO.getall(Chain_Condition.getCondition_id(),Chain_Condition.getCondition_rv()); 
		
			
			
			int i = 0;
		 	for(eformfield row : eformfields) {
		 		i++;
		 		row.setField_cal_type("M-");
		 		row.setField_datarefercols("data_value"+i);
		 		System.out.println(" Field_id "+row.getField_id());
		 	}


			List<BuildIn> BuildIn = EformfieldDAO.getallBuildIn(management_id); 
			
			for(BuildIn row : BuildIn) {
				eformfield field = new eformfield();
				field.setField_label(row.getBuildin_title());
				field.setField_cal_type("S-");
				field.setField_id(row.getId());
				field.setField_datarefercols("columns_value"+row.getTocolumn());
				eformfields.add(field);
				
			}
			model.addAttribute("eformfields", eformfields);
			model.addAttribute("BuildIn", BuildIn);
					return "/workflow/aggregatedmanagements";
		
			}		
	
	
	
	@PostMapping(value="/saveAggregated-workflow")
	public String saveAggregated(BuildIn buildIn,@RequestParam int process_id,@RequestParam int workflow_id,@RequestParam int management_id){
		

				buildIn.setId_refer(management_id);
				buildIn.setProcess_id(process_id);
				ReportDAO.saveBuildIn(buildIn);
		
				return "redirect:/aggregatedmanagements-workflow/"+workflow_id+"/"+management_id+"/"+process_id;
		
		}
	
	
	
	
	@PostMapping(value="/savedocumentbox-workflow")
	public String savedocumentbox(TabManagement Tab,@RequestParam int process_id,@RequestParam int workflow_id,@RequestParam int management_id){
		

				Tab.setTab_process_id(process_id);
				Tab.setTab_management_id(management_id);
				ReportDAO.saveTabManagement(Tab);
		
				return "redirect:/documentbox-workflow/"+workflow_id+"/"+management_id+"/"+process_id;
		
		}
	
	
	
	@RequestMapping(value="/editdocumentbox-workflow/{workflow_id}/{management_id}/{process_id}/{id}",method = RequestMethod.GET)
	public String editdocumentbox(
				 ModelMap model
				,@PathVariable int workflow_id
				,@PathVariable int management_id
				,@PathVariable int process_id
				,@PathVariable int id
				){
	
	
					TabManagement  Tab = ReportDAO.getTabManagement(id);
					model.addAttribute("Tab",Tab); 
					model.addAttribute("workflow_id",workflow_id); 
					model.addAttribute("management_id",management_id); 
					model.addAttribute("process_id",process_id); 
	
					
					
					process process =  EdataDAO.getProcess(process_id);
			    	model.addAttribute("process",process);    	
					model.addAttribute("process_id",process_id); 
					
					Management  management =  EdataDAO.getManagement(management_id);
					model.addAttribute("management",management);  
					model.addAttribute("management_id",management_id);  
					
					
					
					dataset dataset = DatasetDAO.getMain("Workflow_status");
					List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
					model.addAttribute("dataset_data", dataset_data);
					
					
					process chainprocess = EdataDAO.getProcess(management.getProcess_id());
					condition Chain_Condition = ConditionDAO.getbyid(chainprocess.getId_condition());
					List<eformfield> eformfields = EformfieldDAO.getall(Chain_Condition.getCondition_id(),Chain_Condition.getCondition_rv()); 
				
					
					
					int i = 0;
				 	for(eformfield row : eformfields) {
				 		i++;
				 		row.setField_cal_type("M-");
				 		row.setField_datarefercols("data_value"+i);
				 		
				 	}


					List<BuildIn> BuildIn = EformfieldDAO.getallBuildIn(management_id); 
					
			
					for(BuildIn row : BuildIn) {
			
						eformfield field = new eformfield();
						field.setField_label(row.getBuildin_title());
						field.setField_cal_type("S-");
						field.setField_id(row.getId());
						field.setField_datarefercols("columns_value"+row.getTocolumn());
						eformfields.add(field);
						
					}
					
					
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
				 	
					
					
					model.addAttribute("eformfields", eformfields);
					
					
					
					
					
					
					List<process> processIndex =  EdataDAO.getAllProcessBytype(workflow_id,2);
			    	model.addAttribute("processIndex",processIndex); 
					
					
					
					
					
					return "/workflow/editdocumentbox";
			
		}
	
	
	@RequestMapping(value="/editAggregated-workflow/{workflow_id}/{management_id}/{process_id}/{id}",method = RequestMethod.GET)
	public String editAggregated(
				 ModelMap model
				,@PathVariable int workflow_id
				,@PathVariable int management_id
				,@PathVariable int process_id
				,@PathVariable int id
				){
	
	
					model.addAttribute("workflow_id",workflow_id); 
					model.addAttribute("management_id",management_id); 
					model.addAttribute("process_id",process_id); 
	
					
					
					process process =  EdataDAO.getProcess(process_id);
			    	model.addAttribute("process",process);    	
					model.addAttribute("process_id",process_id); 
					
					Management  management =  EdataDAO.getManagement(management_id);
					model.addAttribute("management",management);  
					model.addAttribute("management_id",management_id);  
					
					
					
					dataset dataset = DatasetDAO.getMain("Workflow_status");
					List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
					model.addAttribute("dataset_data", dataset_data);
					
					
			
					BuildIn BuildIn = ReportDAO.getBuildIn(id); 
					model.addAttribute("BuildIn",BuildIn);  
			
					
					 List<Measurement_rules> Rules =  ReportDAO.getMeasurement_rules();
					model.addAttribute("Rules",Rules);  
					
					return "/workflow/editaggregatedmanagements";
			
		}
	
	
	
	@PostMapping(value="/deletedocumentbox-workflow")
	public String deletedocumentbox(@RequestParam int process_id,@RequestParam int workflow_id,@RequestParam int management_id,@RequestParam int id) {
		 	ReportDAO.deleteTabManagement(id);
		   return "redirect:/documentbox-workflow/"+workflow_id+"/"+management_id+"/"+process_id;
	}

	@PostMapping(value="/deleteAggregated-workflow")
	public String deleteAggregated(@RequestParam int process_id,@RequestParam int workflow_id,@RequestParam int management_id,@RequestParam int id) {
		 	ReportDAO.deleteBuildIn(id);
			return "redirect:/aggregatedmanagements-workflow/"+workflow_id+"/"+management_id+"/"+process_id;
	}


	

	@RequestMapping(value="/editManagementscope-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editManagementscope(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int id
					){
						
	    	process process =  EdataDAO.getProcess(id);
	    	model.addAttribute("process",process);
	    	
	    
	    	
	    	
			workflow workflows = WorkflowDAO.getbyid(workflow_id);
			model.addAttribute("workflows",workflows);  
		
		    Myapp app =  EdataDAO.getMyapp("Production");
			model.addAttribute("app", app);
			
			 List<process> allProcess =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
				model.addAttribute("allProcess", allProcess);
			

			// 1. ตรวจสอบเบื้องต้นว่า String ไม่เป็น null หรือว่าง
			String registerStr = app.getRegister();
			String enumTypeStr = app.getEnum_type();

			if (registerStr != null && enumTypeStr != null) {
			    String[] registerArray = registerStr.split(",");
			    String[] registerTypeArray = enumTypeStr.split(",");

			    // 2. ค้นหา Index
			    int index = IntStream.range(0, registerArray.length)
			            .filter(i -> registerArray[i].equals(process.getRegister()))
			            .findFirst()
			            .orElse(-1);

			    // 3. ตรวจสอบว่าพบ index และ index นั้นมีอยู่ใน registerTypeArray จริง
			    if (index != -1 && index < registerTypeArray.length) {
			        
			        // 4. ดึงข้อมูล Dataset และเช็ค null
			        dataset datasets = DatasetDAO.getMain(registerTypeArray[index]);
			        
			        if (datasets != null) {
			            List<dataset_data> dataset_datas = Dataset_dataDAO.getall(datasets.getDataset_id());
			            model.addAttribute("dataset_datas", dataset_datas);
			        } else {
			            // กรณีไม่พบข้อมูลใน Database
			            model.addAttribute("dataset_datas", new ArrayList<>()); 
			        }
			        
			    } else {
			        // กรณีหา Register ไม่เจอ หรือ Index เกินขนาด Array
			        model.addAttribute("dataset_datas", new ArrayList<>());
			    }
			} else {
			    // กรณีข้อมูลจาก App เป็น Null
			    model.addAttribute("dataset_datas", new ArrayList<>());
			}
			
			
			List<user> userss = UserDAO.getall(0,0);
			model.addAttribute("userall", userss);

			 
			List<groups> groupss = GroupsDAO.getall(0);
			model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
				
	
				
				return "/workflow/editmanagementscope";
			}
	
	
	@RequestMapping(value="/saveeditmanagementscope-workflow",method = RequestMethod.POST)
	public String saveeditmanagementscope(RedirectAttributes redirectAttributes,UseExtension extension,@RequestParam int workflow_id,process process) {
			
			EdataDAO.editIndexScope(process);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/editManagementscope-workflow/"+workflow_id+"/"+process.getId();
	}
	
	@RequestMapping(value="/editindexscope-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editindexscope(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int id
					){
						
	    	process process =  EdataDAO.getProcess(id);
	    	model.addAttribute("process",process);
	    	
	    
	    	
	    	
			workflow workflows = WorkflowDAO.getbyid(workflow_id);
			model.addAttribute("workflows",workflows);  
		
		    Myapp app =  EdataDAO.getMyapp("Production");
			model.addAttribute("app", app);
			
			 List<process> allProcess =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
				model.addAttribute("allProcess", allProcess);
			

			// 1. ตรวจสอบเบื้องต้นว่า String ไม่เป็น null หรือว่าง
			String registerStr = app.getRegister();
			String enumTypeStr = app.getEnum_type();

			if (registerStr != null && enumTypeStr != null) {
			    String[] registerArray = registerStr.split(",");
			    String[] registerTypeArray = enumTypeStr.split(",");

			    // 2. ค้นหา Index
			    int index = IntStream.range(0, registerArray.length)
			            .filter(i -> registerArray[i].equals(process.getRegister()))
			            .findFirst()
			            .orElse(-1);

			    // 3. ตรวจสอบว่าพบ index และ index นั้นมีอยู่ใน registerTypeArray จริง
			    if (index != -1 && index < registerTypeArray.length) {
			        
			        // 4. ดึงข้อมูล Dataset และเช็ค null
			        dataset datasets = DatasetDAO.getMain(registerTypeArray[index]);
			        
			        if (datasets != null) {
			            List<dataset_data> dataset_datas = Dataset_dataDAO.getall(datasets.getDataset_id());
			            model.addAttribute("dataset_datas", dataset_datas);
			        } else {
			            // กรณีไม่พบข้อมูลใน Database
			            model.addAttribute("dataset_datas", new ArrayList<>()); 
			        }
			        
			    } else {
			        // กรณีหา Register ไม่เจอ หรือ Index เกินขนาด Array
			        model.addAttribute("dataset_datas", new ArrayList<>());
			    }
			} else {
			    // กรณีข้อมูลจาก App เป็น Null
			    model.addAttribute("dataset_datas", new ArrayList<>());
			}
			
			
			List<user> userss = UserDAO.getall(0,0);
			model.addAttribute("userall", userss);

			 
			List<groups> groupss = GroupsDAO.getall(0);
			model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
				
	
				
				return "/workflow/editindexscope";
			}


	
	
	
	@RequestMapping(value="/viewindexscope-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String viewindexscope(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int id
					){
						
	    	process process =  EdataDAO.getProcess(id);
	    	model.addAttribute("process",process);
	    	
			workflow workflows = WorkflowDAO.getbyid(workflow_id);
			model.addAttribute("workflows",workflows);  
		
		    Myapp app =  EdataDAO.getMyapp("Production");
			model.addAttribute("app", app);
			List<user> userss = UserDAO.getall(0,0);
			model.addAttribute("userall", userss);

			
			
			
			 
			List<groups> groupss = GroupsDAO.getall(0);
			model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
				
	
				
				return "/workflow/viewindexscope";
			}

	
	
	@RequestMapping(value="/saveeditindexscope-workflow",method = RequestMethod.POST)
	public String saveextension2(RedirectAttributes redirectAttributes,UseExtension extension,@RequestParam int workflow_id,process process) {
			
			EdataDAO.editIndexScope(process);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/editindexscope-workflow/"+workflow_id+"/"+process.getId();
	}
	
	
	
	
	@RequestMapping(value="/editviewindexscope-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editviewindexscope(
					 ModelMap model
					,@PathVariable int workflow_id
					,@PathVariable int id
					){
						
	    	process process =  EdataDAO.getProcess(id);
	    	model.addAttribute("process",process);
	    	
			workflow workflows = WorkflowDAO.getbyid(workflow_id);
			model.addAttribute("workflows",workflows);  
		
		    Myapp app =  EdataDAO.getMyapp("Production");
			model.addAttribute("app", app);
			List<user> userss = UserDAO.getall(0,0);
			model.addAttribute("userall", userss);

			
			List<registerkey> Registerkey = EdataDAO.getRegisterkeyByDoc(process.getRegister());
			model.addAttribute("Registerkey",Registerkey); 
			 
			List<groups> groupss = GroupsDAO.getall(0);
			model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
				
	
				
				return "/workflow/editviewindexscope";
			}
	
	@RequestMapping(value="/editviewindexscopes-workflow",method = RequestMethod.POST)
	public String editviewindexscopes(RedirectAttributes redirectAttributes,UseExtension extension,@RequestParam int workflow_id,process process) {
			
			EdataDAO.editViewIndexScope(process);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/editviewindexscope-workflow/"+workflow_id+"/"+process.getId();
	}
	


	@RequestMapping(value="/saveextension2-workflow",method = RequestMethod.POST)
	public String saveextension2(RedirectAttributes redirectAttributes,UseExtension extension,@RequestParam int workflow_id) {
			
		  	if(extension.getUse_process_id() > 0) {
		  		
		  		process Process  = EdataDAO.getProcess(extension.getUse_process_id());
		  		if(Process  == null) {
		  			System.out.println("Error : /saveextension2-workflow : Use_process_id = null ");
		  		}
		  		extension.setEform_id(Process.getId_condition());
		  	}

			
			EdataDAO.save_extension(extension);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/editextension-workflow/"+workflow_id+"/"+extension.getId()+"/"+extension.getProcess_id();
	}
	
	
	
	@RequestMapping(value="/savemanagement2-workflow",method = RequestMethod.POST)
	public String savemanagement2(RedirectAttributes redirectAttributes,Management management,@RequestParam int workflow_id) {
			
			EdataDAO.save_management(management);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/editmanagements-workflow/"+workflow_id+"/"+management.getId()+"/"+management.getManagement_id();
	}
	
	
	
	@RequestMapping(value="/saveuseextension-workflow",method = RequestMethod.POST)
	public String saveextension2(
			RedirectAttributes redirectAttributes
			,@RequestParam int workflow_id
			,@RequestParam int extension_id
			,@RequestParam int process_id
			,ExtensionParameter ExtensionParameter
			) {
		ExtensionParameter.setUse_extension_id(extension_id);
		
		
			EdataDAO.SaveExtensionParameter(ExtensionParameter);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/editextension-workflow/"+workflow_id+"/"+extension_id+"/"+process_id;
	}
	

	

	
	@RequestMapping(value="/goals-workflow/{id}",method = RequestMethod.GET)
	public String goals(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
			MainServices.addNotificationAttributes(model);
			
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 

	      
	

		  
		 
		  
		  user creator = UserDAO.getbyId(workflows.getWorkflow_idadd(),0);
		  model.addAttribute("creator",creator); 
		  
	
		  
			 List<process> process =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
			 
			 
			 process.stream().forEach(row->{
				 List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(row.getId());
				 
				 sub_processes.stream()
				    .filter(r -> "Create".equals(r.getStep()))
				    .findFirst()
				    .ifPresent(create -> {
				    	if ( otherservices.haveValue(create.getAction()) ) {
				            row.setTable(create.getAction());
				    	 } else if ( otherservices.haveValue(create.getAction()) ) { 
				            row.setTable(create.getAction());
				        }
				    });
				  
				 List<GamificationGoal> Goal =  gamificationGoalRepository.getAllGoalByprocesses(row.getId());
				 row.setGoal(Goal);
				 row.setSub_processes(sub_processes);
				 
			 });
			 

			 
			 model.addAttribute("process",process); 
			 

			
	  
		return "/workflow/goals";
	}
	
	
	
	@RequestMapping(value="/KPI_Measurement_Rules-workflow",method = RequestMethod.GET)
	public String KPI_Measurement_Rules(
			ModelMap model) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			
		 
		 List<Measurement_rules> Measurement_rules = EdataDAO.getAllMeasurement_rules();
		 model.addAttribute("Measurement_rules",Measurement_rules); 
		 List<process> process =  EdataDAO.getAllProcess();
		 model.addAttribute("process",process); 
	
			return "/workflow/KPI_Measurement_Rules";
		}
		
	
	
	@RequestMapping(value="/dashboard-workflow/{id}",method = RequestMethod.GET)
	public String dashboard(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
			
		 
		 List<Measurement_rules> Measurement_rules = EdataDAO.getAllMeasurement_rules();
		 model.addAttribute("Measurement_rules",Measurement_rules); 
		 
		 
		 
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  AtomicReference<Integer> notice = new AtomicReference<>(0);
		  
		  List<condition> flow = ConditionDAO.getAllbylink(id);

		  flow.stream().forEach(row -> {
			  
			  
			 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(row.getDatalink_idrefer());
			 
			 System.err.println("idrefer "+row.getDatalink_idrefer());
			 System.err.println("upload_pn "+upload_pn.getUpload_pn_id());
			 
			 
			 if(upload_pn.getUpload_pn_id() == 0) {
				 notice.set(notice.get() + 1);
			 }
			 
			 
		      List<eformfield> eformfield = eformfields.stream()
		          .filter(sub -> sub.getField_formid() == row.getCondition_id())
		          .filter(sub -> sub.getField_formrv() == row.getCondition_rv())
		          .collect(Collectors.toList());
		      row.setEformfields(eformfield);
		  });

		  int error = validateDatalinkName(flow);

		  
		  

		  
		  
		  
		  user creator = UserDAO.getbyId(workflows.getWorkflow_idadd(),0);
		  model.addAttribute("creator",creator); 
		  
		  
			 List<process> process =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
			 
			 
			 process.stream().forEach(row->{
				 List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(row.getId());
				 List<policies> Policies =  EdataDAO.getAllMainPolicies(row.getId());
				 row.setPolicies(Policies);
				 row.setSub_processes(sub_processes);
			 });
			 
			 
			 
			  List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);
			 
			 model.addAttribute("process",process); 
			 
			 List<groups> groupss = GroupsDAO.getall(0);
			 model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
			
			
		String tempId = UUID.randomUUID().toString(); 
		model.addAttribute("group_id",tempId);  
			
			
			 
		  model.addAttribute("notice",notice.get()); 
		  model.addAttribute("error",error); 
		  model.addAttribute("flow",flow); 
	  
		return "/workflow/dashboard";
	}
	
	
	
   
	
	@RequestMapping(value="/integrationsapp-workflow/{id}",method = RequestMethod.GET)
	public String integrationsapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  AtomicReference<Integer> notice = new AtomicReference<>(0);
		  
		  List<condition> flow = ConditionDAO.getAllbylink(id);

		  flow.stream().forEach(row -> {
			  
			  
			 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(row.getDatalink_idrefer());
			 
			 System.err.println("idrefer "+row.getDatalink_idrefer());
			 System.err.println("upload_pn "+upload_pn.getUpload_pn_id());
			 
			 
			 if(upload_pn.getUpload_pn_id() == 0) {
				 notice.set(notice.get() + 1);
			 }
			 
			 
		      List<eformfield> eformfield = eformfields.stream()
		          .filter(sub -> sub.getField_formid() == row.getCondition_id())
		          .filter(sub -> sub.getField_formrv() == row.getCondition_rv())
		          .collect(Collectors.toList());
		      row.setEformfields(eformfield);
		  });

		  int error = validateDatalinkName(flow);

		  
		  
		  List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		  
		  
		 List<process> process =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
		 
		 process.stream().forEach(row->{
			
				condition condition = ConditionDAO.getbyid(row.getId_condition());
				row.setCondition(condition);
				
				if(row.getProcess_join() > 0) {
			    process join = EdataDAO.getProcess(row.getProcess_join());
				    if(join != null) {
				    row.setNote("Join ->"+join.getName());
				    }
				}
		 });
		 
		 model.addAttribute("process",process); 
		  
		 
		 
		 List<process> process2 =  EdataDAO.getAllProcessJoin(workflows.getWorkflow_id());
		 
		 process2.stream().forEach(row->{
				workflow join = WorkflowDAO.getbyid(row.getId_refer());
				
				if(join != null) {
					   row.setNote(join.getWorkflow_name());
				}
			 
		 });
		 
		 model.addAttribute("process2",process2); 
		 
		 
		  
		  user creator = UserDAO.getbyId(workflows.getWorkflow_idadd(),0);
		  model.addAttribute("creator",creator); 
		  
		  
		  model.addAttribute("notice",notice.get()); 
		  model.addAttribute("error",error); 
		  model.addAttribute("flow",flow); 
	  
		return "/workflow/integrations";
	}

	
	
	
	@RequestMapping(value="/saveMeasurement_rules-workflow",method = RequestMethod.POST)
	public String saveextension(RedirectAttributes redirectAttributes,Measurement_rules Measurement_rules) {
			
			EdataDAO.save_Measurement_rules(Measurement_rules);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/KPI_Measurement_Rules-workflow";
	}
	
	
	
	@RequestMapping(value="/saveextension-workflow",method = RequestMethod.POST)
	public String saveextension(RedirectAttributes redirectAttributes,UseExtension extension,@RequestParam int workflow_id) {
			
			EdataDAO.save_extension(extension);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/indexscope-workflow/"+workflow_id;
	}
	
	
	@RequestMapping(value="/savemanagement-workflow",method = RequestMethod.POST)
	public String savemanagement(RedirectAttributes redirectAttributes,Management management,@RequestParam int workflow_id) {
			
			EdataDAO.save_management(management);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/Managementscope-workflow/"+workflow_id;
	}
	
	
	
	
	
	@RequestMapping(value="/saveprocess-workflow",method = RequestMethod.POST)
	public String saveprocess(RedirectAttributes redirectAttributes,process process) {
			EdataDAO.save_process(process);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/integrationsapp-workflow/"+process.getId_refer();
	}
	
	
	@RequestMapping(value="/setuplayout-workflow",method = RequestMethod.POST)
	public String setuplayout(
			RedirectAttributes redirectAttributes
			,@RequestParam int id_refer
			,@RequestParam String field_id
			,@RequestParam String input_size
			) {
		
		
		EformfieldDAO.UpdateAll(field_id,input_size); 
		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/integrationsapp-workflow/"+id_refer;
	}
	
	
	
	
	
	@RequestMapping(value="/savesubprocess-workflow",method = RequestMethod.POST)
	public String savesubprocess(RedirectAttributes redirectAttributes,sub_processes sub_processes,@RequestParam int workflow_id) {
        EdataDAO.save_subprocess(sub_processes);

        redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/setworkflowapp-workflow/"+workflow_id;
	}
	
	
	
	
	
	@RequestMapping(value="/stepset-workflow/{step}",method = RequestMethod.GET)
	public String stepset(@PathVariable int step,@RequestParam int id,ModelMap model) {

		
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows); 
		 
		 Filemanagerservice.getAppProperty(workflows,step,model);

		 
		 
		return "/app/sub/step";
		
	}
	
	
	@RequestMapping(value="/setdatasource-workflow/{step}/{app_workflow_id}",method = RequestMethod.GET)
	public String setdatasource(
			@PathVariable int app_workflow_id,
			@PathVariable int step,
			ModelMap model) {
		Platform platform = new Platform();
		platform.setApp_workflow_id(app_workflow_id);
		platform.setApp_step(step);
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).Property(platform).source().load();
		Map<String, Object> data = platformService.getModelData();
	    model.addAllAttributes(data);
		return "/app/sub/source";
	}
	
	@RequestMapping(value="/savedatasource-workflow",method = RequestMethod.POST)
	public String savedatasource(
			RedirectAttributes redirectAttributes
			,PlatformConfig platformConfig
			) {
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).applyConfig(platformConfig).source().save();
		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/setformsworkflowapp-workflow/"+platformConfig.getWorkflow_id();
	}
	
	

	@RequestMapping(value="/setstepaction-workflow/{step}/{app_workflow_id}",method = RequestMethod.GET)
	public String setstepaction(
			@PathVariable int app_workflow_id,
			@PathVariable int step,
			ModelMap model) {


		Platform platform = new Platform();
		platform.setApp_workflow_id(app_workflow_id);
		platform.setApp_step(step);
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).Property(platform).action().load();
		Map<String, Object> data = platformService.getModelData();
	    model.addAllAttributes(data);
		return "/app/sub/actionstep";
	}
	
	@RequestMapping(value="/setprocessaction-workflow/{step}/{app_workflow_id}",method = RequestMethod.GET)
	public String setprocessaction(
			@PathVariable int app_workflow_id,
			@PathVariable int step,
			ModelMap model) {


		Platform platform = new Platform();
		platform.setApp_workflow_id(app_workflow_id);
		platform.setApp_step(step);
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).Property(platform).action().load();
		Map<String, Object> data = platformService.getModelData();
	    model.addAllAttributes(data);
		return "/app/sub/actionprocess";
	}
	
	
	
	@RequestMapping(value="/savestepinfo-workflow",method = RequestMethod.POST)
	public String savestepinfo(
			RedirectAttributes redirectAttributes
			,PlatformConfig platformConfig
			) {

		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).applyConfig(platformConfig).display().save();
		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/setformsworkflowapp-workflow/"+platformConfig.getWorkflow_id();
	}

	
	@RequestMapping(value="/setstepinfo-workflow/{step}/{app_workflow_id}",method = RequestMethod.GET)
	public String setstepinfo(
			@PathVariable int app_workflow_id,
			@PathVariable int step,
			ModelMap model) {


		Platform platform = new Platform();
		platform.setApp_workflow_id(app_workflow_id);
		platform.setApp_step(step);
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).Property(platform).action().load();
		Map<String, Object> data = platformService.getModelData();
	    model.addAllAttributes(data);
		return "/app/sub/actioninfo";
	}
	
	
	
	@RequestMapping(value="/savestepprocess-workflow",method = RequestMethod.POST)
	public String savestepprocess(
			RedirectAttributes redirectAttributes
			,PlatformConfig platformConfig
			) {
		LoadApp loadApp = new LoadApp();

		platformService.initiate(loadApp).applyConfig(platformConfig).display().save();
		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/setformsworkflowapp-workflow/"+platformConfig.getWorkflow_id();
	}

	
	

	
	
	@RequestMapping(value="/savestepaction-workflow",method = RequestMethod.POST)
	public String savestepaction(
			RedirectAttributes redirectAttributes
			,PlatformConfig platformConfig
			) {

		
		System.out.println(" platformConfig "+platformConfig.getKeymap().toString());
		platformConfig.setType("create");
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).applyConfig(platformConfig).action().save();
		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/setformsworkflowapp-workflow/"+platformConfig.getWorkflow_id();
	}

	
	
	
	
	
	
	
	@RequestMapping(value="/keymap-workflow",method = RequestMethod.POST)
	public String keymapworkflow(
			RedirectAttributes redirectAttributes
			,keymap keymap
			,keymap2 keymap2
			,actionkeymap actionkeymap
			,referencekeymap referencekeymap
			,@RequestParam int sub_processes_id
			,sub_processes sub_processes
			,@RequestParam int processe_id
			,@RequestParam int workflow_id
			,@RequestParam(required=false,defaultValue ="-",value = "activate_register") String activate_register
			,@RequestParam(required=false,defaultValue ="-",value = "activate_action") String activate_action
			,@RequestParam(required=false,defaultValue ="0",value = "activate_autoid")  int activate_autoid
			,@RequestParam(required=false,defaultValue ="0",value = "activate_subdata")  int activate_subdata
			,@RequestParam(required=false,defaultValue ="0",value = "activate_checking_list")  int activate_checking_list
			,@RequestParam(required=false,defaultValue ="0",value = "activate_editlist")  int activate_editlist
			,@RequestParam(required=false,defaultValue ="0",value = "activate_autorid")  int activate_autorid
			,@RequestParam(required=false,defaultValue ="-",value = "activate_clear") String activate_clear
	
			
			,@RequestParam(required=false,defaultValue ="0",value = "endaction_list")  String endaction_list
			,@RequestParam(required=false,defaultValue ="0",value = "activate_endaction")  int activate_endaction
			
			,@RequestParam(required=false,defaultValue ="-",value = "activate_reference_only") String activate_reference_only
			,@RequestParam(required=false,defaultValue ="-",value = "activate_hierarchical_reference") String activate_hierarchical_reference
			,@RequestParam(required=false,defaultValue ="-",value = "activate_reference_modify") String activate_reference_modify
			,@RequestParam(required=false,defaultValue ="-",value = "activate_reference_continue") String activate_reference_continue
			,@RequestParam(required=false,defaultValue ="-",value = "activate_register_processing") String activate_register_processing
			,@RequestParam(required=false,defaultValue ="-",value = "activate_document_reference") String activate_document_reference
			
			,@RequestParam(required=false,defaultValue ="0",value = "batch_code_generation") String batch_code_generation
			,@RequestParam(required=false,defaultValue ="-",value = "prefix") String prefix
			
			,@RequestParam(required=false,value = "visibility") String visibility
			) {
		
		
		


		System.out.println("activate_clear "+activate_clear);
		System.out.println("activate_register_processing "+activate_register_processing);
		
		
		
			List<String> references = List.of(
			    activate_reference_only,
			    activate_hierarchical_reference,
			    activate_reference_modify,
			    activate_reference_continue,
			    activate_document_reference
			);

			// หาตัวที่ไม่ใช่ "-"
			int activeIndex = -1;
			for (int i = 0; i < references.size(); i++) {
			    if (!references.get(i).equals("-")) {
			        activeIndex = i;
			        break;
			    }
			}

			// Reset ค่าทุกตัว ยกเว้นตัวที่ถูกเลือก
		// (activeIndex == 0) ? activate_reference_only : "-";
			//activate_hierarchical_reference = (activeIndex == 1) ? activate_hierarchical_reference : "-";
			//activate_reference_modify = (activeIndex == 2) ? activate_reference_modify : "-";
		//	activate_reference_continue = (activeIndex == 3) ? activate_reference_continue : "-";
			//activate_document_reference = (activeIndex == 4) ? activate_document_reference : "-";
		
			String action = "-";
			int action_type = 0;
			
			if(!activate_register.equals("-")  ) {
				action = activate_register;
				action_type = 1;
			}
			else if(!activate_action.equals("-")) {
				action = activate_action;
				action_type = 2;
			   }
			  
		
		
		   
		 

		 
		   
		   System.out.println("keymap "+keymap.getFilter_enum());
	
  
		   
		   
		   
		   sub_processes sub_processes_save = EdataDAO.getSubProcess(sub_processes_id);
		
		   /*
		   sub_processes_save.setMaindatacolumn(sub_processes.getMaindatacolumn());
		   sub_processes_save.setFiltermain(sub_processes.getFiltermain());
		   sub_processes_save.setSubdatacolumn(sub_processes.getSubdatacolumn());
		   sub_processes_save.setFiltersub(sub_processes.getFiltersub());
		   sub_processes_save.setSubdatacolumn1(sub_processes.getSubdatacolumn1());
		   sub_processes_save.setFiltersub1(sub_processes.getFiltersub1());
		   sub_processes_save.setSubdatacolumn2(sub_processes.getSubdatacolumn2());
		   sub_processes_save.setFiltersub2(sub_processes.getFiltersub2());
		   sub_processes_save.setSubdatacolumn3(sub_processes.getSubdatacolumn3());
		   sub_processes_save.setFiltersub3(sub_processes.getFiltersub3());
		   sub_processes_save.setSubdatacolumn4(sub_processes.getSubdatacolumn4());
		   sub_processes_save.setFiltersub4(sub_processes.getFiltersub4());
		   sub_processes_save.setSubdatacolumn5(sub_processes.getSubdatacolumn5());
		   sub_processes_save.setFiltersub5(sub_processes.getFiltersub5());
		   
		   sub_processes_save.setInput_db1(sub_processes.getInput_db1());
		   sub_processes_save.setInput_db2(sub_processes.getInput_db2());
		   sub_processes_save.setInput_db3(sub_processes.getInput_db3());
		   sub_processes_save.setInput_db4(sub_processes.getInput_db4());
		   sub_processes_save.setInput_db5(sub_processes.getInput_db5());
		   sub_processes_save.setInput_db6(sub_processes.getInput_db6());
		   sub_processes_save.setInput_db7(sub_processes.getInput_db7());
		   */
		   
		   
		   sub_processes_save.setAction(action);
		   sub_processes_save.setAction_type(action_type);
		   
	
		   sub_processes_save.setActivate_autoid(activate_autoid);
		   sub_processes_save.setActivate_subdata(activate_subdata);
		   sub_processes_save.setAutoiddatacolumn(sub_processes.getAutoiddatacolumn());
		   sub_processes_save.setCode_app(sub_processes.getCode_app());
		   sub_processes_save.setActivate_checking_list(sub_processes.getActivate_checking_list());
		   sub_processes_save.setChecking_list(sub_processes.getChecking_list());
		   sub_processes_save.setActivate_editlist(sub_processes.getActivate_editlist());
		   sub_processes_save.setEdit_list(sub_processes.getEdit_list());   
		   sub_processes_save.setActivate_editlist(sub_processes.getActivate_editlist());
		   sub_processes_save.setEdit_list(sub_processes.getEdit_list());
		   sub_processes_save.setActivate_autorid(sub_processes.getActivate_autorid());
		   sub_processes_save.setCode_rid(sub_processes.getCode_rid());
		 
		   sub_processes_save.setAction_batch_insert(sub_processes.getAction_batch_insert());
		   sub_processes_save.setActivate_clear(sub_processes.getActivate_clear());
		   sub_processes_save.setAction_custom_clear(sub_processes.getAction_custom_clear());
		   sub_processes_save.setAction_batch_clear(sub_processes.getAction_batch_clear());
		   sub_processes_save.setActivate_reference_only(sub_processes.getActivate_reference_only());
		   sub_processes_save.setActivate_hierarchical_reference(sub_processes.getActivate_hierarchical_reference());
		   sub_processes_save.setActivate_reference_modify(sub_processes.getActivate_reference_modify());
		   sub_processes_save.setActivate_reference_continue(sub_processes.getActivate_reference_continue());
		   sub_processes_save.setAction_batch_modify(sub_processes.getAction_batch_modify());
		   sub_processes_save.setAction_batch_continue(sub_processes.getAction_batch_continue());
		   sub_processes_save.setActivate_register_processing(sub_processes.getActivate_register_processing());
		   sub_processes_save.setActivate_document_reference(sub_processes.getActivate_document_reference());
		   sub_processes_save.setAction_batch_dreference(sub_processes.getAction_batch_dreference()); 
		   sub_processes_save.setBatch_code_generation(sub_processes.getBatch_code_generation());
		   sub_processes_save.setPrefix(sub_processes.getPrefix()); 
		   
		   sub_processes_save.setEndaction_list(endaction_list);
		   sub_processes_save.setActivate_endaction(activate_endaction); 
	
		
		   
		   
		   EdataDAO.remove_keyMap_all(sub_processes_id);
		   
		   
		   if(		!activate_register.equals("-")
				   ||  !activate_register_processing.equals("-") 
				   ||   !activate_document_reference.equals("-")  
				   ) {
			   
			   keymap.setProcesses_id(processe_id);
			   keymap.setSub_processes_id(sub_processes_id);
			   EdataDAO.save_keymap(keymap);
		   }
		   
		   
		   /*
		   
		   if(!activate_reference_only.equals("-") 
				   || !activate_hierarchical_reference.equals("-") 
				   ||   !activate_reference_modify.equals("-") 
				   ||   !activate_reference_continue.equals("-") 
				
				   ) {   
			 
		
			   System.out.println("save 2 "+activate_hierarchical_reference);
			   System.out.println("keymap2 "+keymap2.getMainkey2());
			   
			   
			   
			    keymap2.setProcesses_id(processe_id);
			    keymap2.setSub_processes_id(sub_processes_id);
				keymap2.setMainkey2(referencekeymap.getReferencemainkey());
				keymap2.setEformkey2(referencekeymap.getReferenceeformkey());
				keymap2.setChange_format2(referencekeymap.getReferencechange_format());
				keymap2.setEnum_id2(referencekeymap.getReferenceenum_id());
				keymap2.setFilter_enum2(referencekeymap.getReferencefilter_enum());
				keymap2.setType2(referencekeymap.getReference_type());
				
			    EdataDAO.save_keymap(keymap2);
			    
		   }	
		 
		   
		   if(!activate_clear.equals("-")) {
				   
					keymap.setMainkey(actionkeymap.getActionmainkey());
					keymap.setEformkey(actionkeymap.getActioneformkey());
					keymap.setChange_format(actionkeymap.getActionchange_format());
					keymap.setEnum_id(actionkeymap.getActionenum_id());
					keymap.setFilter_enum(actionkeymap.getActionfilter_enum());
				    EdataDAO.save_keymap(keymap);
			   }	
		   if(!activate_action.equals("-")) {
			   
				keymap.setMainkey(actionkeymap.getActionmainkey());
				keymap.setEformkey(actionkeymap.getActioneformkey());
				keymap.setChange_format(actionkeymap.getActionchange_format());
				keymap.setEnum_id(actionkeymap.getActionenum_id());
				keymap.setFilter_enum(actionkeymap.getActionfilter_enum());
			    EdataDAO.save_keymap(keymap);
		   } 
		   
		   
		   
		   
		   
		 
			if(activate_clear.equals("-") ) {
				   sub_processes_save.setAction_custom_clear(null);
				   sub_processes_save.setAction_batch_clear(null);
			}
		   if(activate_action.equals("-") ) {
			   sub_processes_save.setAction_batch_insert(null);
		   }
		   if(activate_reference_modify.equals("-") ) {
			   sub_processes_save.setAction_batch_modify(null);
		   }
		   if(activate_reference_continue.equals("-") ) {
			   sub_processes_save.setAction_batch_continue(null);
		   }
		   if(activate_hierarchical_reference.equals("-") ) {
			   sub_processes_save.setAction_batch_hierarchical(null);
		   }
		   if(activate_document_reference.equals("-") ) {
			   sub_processes_save.setAction_batch_dreference(null);
		   }
		   
		   
		  
		   
		   
		   if(activate_autoid == 0) {
			   
			   sub_processes_save.setAutoiddatacolumn(null);
			   sub_processes_save.setCode_app(null);	   
		   }
		   if(activate_subdata == 0) {
			   sub_processes_save.setMaindatacolumn(null);
			   sub_processes_save.setFiltermain(null);
			   
			   
			   sub_processes_save.setSubdatacolumn1(null);
			   sub_processes_save.setFiltersub1(null);
	
			   sub_processes_save.setSubdatacolumn(null);
			   sub_processes_save.setFiltersub(null);
			   
			   sub_processes_save.setSubdatacolumn2(null);
			   sub_processes_save.setFiltersub2(null);
			   
			   sub_processes_save.setSubdatacolumn3(null);
			   sub_processes_save.setFiltersub3(null);
			   
			   sub_processes_save.setSubdatacolumn4(null);
			   sub_processes_save.setFiltersub4(null);
			   
			   sub_processes_save.setSubdatacolumn5(null);
			   sub_processes_save.setFiltersub5(null);
			   
			   sub_processes_save.setInput_db1(null);
			   sub_processes_save.setInput_db2(null);
			   sub_processes_save.setInput_db3(null);
			   sub_processes_save.setInput_db4(null);
			   sub_processes_save.setInput_db5(null);
			   sub_processes_save.setInput_db6(null);
			   sub_processes_save.setInput_db7(null);
			   
			   
		   }
		   if(activate_checking_list == 0) {
			   sub_processes_save.setChecking_list(null);
		   }
		   if(activate_editlist == 0) {
			   sub_processes_save.setEdit_list(null);
		   }
		  */  

		   EdataDAO.save_subprocess(sub_processes_save);
		   EdataDAO.SetProcessVisibility(processe_id,visibility); 	
		   
		   
		   
		   

		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		return "redirect:/setformsworkflowapp-workflow/"+workflow_id;
	}
	
	

	@RequestMapping(value="/setlink-workflow/{id}",method = RequestMethod.GET)
	public String setlink(
			ModelMap model,
			@PathVariable int id)
					throws IOException {
	
		MainServices.addNotificationAttributes(model);
		Platform  platform = new Platform();
		platform.setApp_workflow_id(id);
		LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).Property(platform).workflow().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		model.addAttribute("id",id); 
		
		
		
		
		return "/workflow/setlink";
	}
	
	@RequestMapping(value="/setcalculation-workflow/{id_refer}/{workflow_id}",method = RequestMethod.GET)
	public String setcalculation2(
			ModelMap model
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			)throws IOException {
		
		
			//Row_calculation  data = WorkflowDAO.getRow_calculationByidrefer(id_refer);
			//model.addAttribute("data",data); 
			model.addAttribute("workflow_id",workflow_id); 
			model.addAttribute("id_refer",id_refer); 
		
			
			Platform platform = new Platform();
			platform.setApp_workflow_id(workflow_id);
			platform.setApp_processes_id(id_refer);	
			LoadApp loadApp = new LoadApp();
		platformService.initiate(loadApp).LoadComponent(platform).input().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		
		
		return "/workflow/calculation";
		
	}
	@RequestMapping(value="/setcalculation-workflow/{id_refer}/{workflow_id}/{id}",method = RequestMethod.GET)
	public String setcalculation2(
			ModelMap model
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			,@PathVariable int id
			)throws IOException {
		
		
			Row_calculation  data = WorkflowDAO.getRow_calculationByid(id);
			model.addAttribute("data",data); 
			model.addAttribute("workflow_id",workflow_id); 
			model.addAttribute("id_refer",id_refer); 
		
			Platform platform = new Platform();
			platform.setApp_workflow_id(workflow_id);
			platform.setApp_processes_id(id_refer);
			LoadApp loadApp = new LoadApp();		
		platformService.initiate(loadApp).LoadComponent(platform).input().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		
		
		return "/workflow/calculation";
		
	}
	
	
	
	
	@RequestMapping(value="/savesetcalculation-workflow",method = RequestMethod.POST)
	public String savesetcalculation(
			Row_calculation data
			,@RequestParam int workflow_id
			)throws IOException {

		WorkflowDAO.saveRow_calculation(data);
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	

	@RequestMapping(value="/delcalculation-workflow/{id}/{workflow_id}",method = RequestMethod.GET)
	public String delcalculation(
			@PathVariable int workflow_id
			,@PathVariable int id
			)throws IOException {
		
		Row_calculation  data = WorkflowDAO.getRow_calculationByid(id);
		WorkflowDAO.DelRow_calculation(data);
		
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	
	
	
	//---------------------
	
	
	@RequestMapping(value="/settablecalculation-workflow/{id_refer}/{workflow_id}",method = RequestMethod.GET)
	public String settablecalculation(
			ModelMap model
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			)
					throws IOException {


		model.addAttribute("workflow_id",workflow_id); 
		model.addAttribute("id_refer",id_refer); 
	
		Platform platform = new Platform();
		platform.setApp_workflow_id(workflow_id);
		platform.setApp_processes_id(id_refer);	
		
		LoadApp loadApp = new LoadApp();		
		platformService.initiate(loadApp).LoadComponent(platform).input().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		

		return "/workflow/tablecalculation";
	}
	
	@RequestMapping(value="/settablecalculation-workflow/{id_refer}/{workflow_id}/{id}",method = RequestMethod.GET)
	public String settablecalculation2(
			ModelMap model
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			,@PathVariable int id
			)throws IOException {
		
		
			Footer_calculation  data = WorkflowDAO.getFooter_calculationByid(id);
			model.addAttribute("data",data); 
			model.addAttribute("workflow_id",workflow_id); 
			model.addAttribute("id_refer",id_refer); 
		
			Platform platform = new Platform();
			platform.setApp_workflow_id(workflow_id);
			platform.setApp_processes_id(id_refer);	
			
			LoadApp loadApp = new LoadApp();		
		platformService.initiate(loadApp).LoadComponent(platform).input().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		
		
		return "/workflow/tablecalculation";
		
	}
	
	
	@RequestMapping(value="/savetablecalculation-workflow",method = RequestMethod.POST)
	public String savetablecalculation(
			Footer_calculation data
			,@RequestParam int workflow_id
			)throws IOException {

		WorkflowDAO.saveFooterCalculation(data);
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	@RequestMapping(value="/deltablecalculation-workflow/{id}/{workflow_id}",method = RequestMethod.GET)
	public String deltablecalculation(
			@PathVariable int workflow_id
			,@PathVariable int id
			)throws IOException {
		
		Footer_calculation  data = WorkflowDAO.getFooter_calculationByid(id);
		WorkflowDAO.DelFooter_calculation(data);
		
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	//---------------------
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/setcalculationlinks-workflow/{id_refer}/{workflow_id}/{id}",method = RequestMethod.GET)
	public String setcalculationlinks(
			ModelMap model,
			@PathVariable int id
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			)
					throws IOException {

		model.addAttribute("workflow_id",workflow_id); 
		model.addAttribute("id_refer",id); 
		
		
		Platform platform = new Platform();
		platform.setApp_workflow_id(workflow_id);
		platform.setApp_processes_id(id_refer);	
		LoadApp loadApp = new LoadApp();		
		platformService.initiate(loadApp).LoadComponent(platform).feed(id).load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		
		return "/workflow/calculationlinks";
	}
	
	
	@RequestMapping(value="/setcalculationlinks-workflow/{id_refer}/{workflow_id}/{id}/{calculation_id}",method = RequestMethod.GET)
	public String setcalculationlinks2(
			ModelMap model,
			@PathVariable int id
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			,@PathVariable int calculation_id
			){
		Rollup_calculation  calculation = WorkflowDAO.getRollup_calculationByid(calculation_id);
		model.addAttribute("data",calculation); 

		
		Platform platform = new Platform();
		platform.setApp_workflow_id(workflow_id);
		platform.setApp_processes_id(id_refer);	
		LoadApp loadApp = new LoadApp();	
		platformService.initiate(loadApp).LoadComponent(platform).feed(id).load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		
		return "/workflow/calculationlinks";
	}
	
	
	
	@RequestMapping(value="/savecalculationlinks-workflow",method = RequestMethod.POST)
	public String savecalculationlinks(
			 Rollup_calculation data
			,@RequestParam int workflow_id
			)throws IOException {

		WorkflowDAO.saveRollupCalculation(data);
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	@RequestMapping(value="/delcalculationlinks-workflow/{id}/{workflow_id}",method = RequestMethod.GET)
	public String delcalculationlinks(
			@PathVariable int workflow_id
			,@PathVariable int id
			)throws IOException {
		
		Rollup_calculation  data = WorkflowDAO.getRollup_calculationByid(id);
		WorkflowDAO.DelRollup_calculation(data);
		
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	
	@RequestMapping(value="/setlinksline-workflow/{id_refer}/{workflow_id}",method = RequestMethod.GET)
	public String setlinksline(
			ModelMap model
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			)
					throws IOException {
		

		model.addAttribute("workflow_id",workflow_id); 
		model.addAttribute("id_refer",id_refer); 
	
		
		Platform platform = new Platform();
		platform.setApp_workflow_id(workflow_id);
		platform.setApp_processes_id(id_refer);	
		LoadApp loadApp = new LoadApp();	
		platformService.initiate(loadApp).LoadComponent(platform).link().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		return "/workflow/linksline";
	}
	
	@RequestMapping(value="/setlinksline-workflow/{id_refer}/{workflow_id}/{id}",method = RequestMethod.GET)
	public String setlinksline2(
			ModelMap model
			,@PathVariable int id_refer
			,@PathVariable int id
			,@PathVariable int workflow_id
			){
		

		Links  data = WorkflowDAO.getLinksByid(id);
		model.addAttribute("data",data); 
		model.addAttribute("workflow_id",workflow_id); 
		model.addAttribute("id_refer",id_refer); 
	
		
		Platform platform = new Platform();
		platform.setApp_workflow_id(workflow_id);
		platform.setApp_processes_id(id_refer);	
		LoadApp loadApp = new LoadApp();	
		platformService.initiate(loadApp).LoadComponent(platform).link().load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		return "/workflow/linksline";
	}
	
	
	
	
	@RequestMapping(value="/savelinksline-workflow",method = RequestMethod.POST)
	public String savelinksline(
			 Links data
			,@RequestParam int workflow_id
			)throws IOException {

		WorkflowDAO.saveLinks(data);
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	@RequestMapping(value="/dellinksline-workflow/{id}/{workflow_id}",method = RequestMethod.GET)
	public String dellinksline(
			@PathVariable int workflow_id
			,@PathVariable int id
			)throws IOException {
		
		Links  data = WorkflowDAO.getLinksByid(id);
		WorkflowDAO.DelLinks(data);
		
		return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	
	

	
	
	
	@RequestMapping(value="/setfeels-workflow/{id_refer}/{workflow_id}/{id}",method = RequestMethod.GET)
	public String setfeels(
			ModelMap model
			,@PathVariable int id
			,@PathVariable int id_refer
			,@PathVariable int workflow_id
			
			)
					throws IOException {

		process process =  EdataDAO.getProcess(id_refer);
		model.addAttribute("process",process); 
		
		model.addAttribute("workflow_id",workflow_id); 
		model.addAttribute("id_refer",id_refer); 
	
		
		Platform platform = new Platform();
		platform.setApp_workflow_id(workflow_id);
		platform.setApp_processes_id(id_refer);	
		LoadApp loadApp = new LoadApp();	
		platformService.initiate(loadApp).LoadComponent(platform).feed(id).load();
		Map<String, Object> datas = platformService.getModelData();
		model.addAllAttributes(datas);
		
		
		return "/workflow/feels";
	}
	
	
	
	@RequestMapping(value="/savesetfeels-workflow",method = RequestMethod.POST)
	public String savesetfeels(
			@RequestParam int workflow_id
			,process process
			
			) {
		EdataDAO.setFeels(process);
		return "redirect:/setlink-workflow/"+workflow_id;
	}
	
	
	
	@RequestMapping(value="/setlinktolink-workflow/{id}/{workflow_id}",method = RequestMethod.GET)
	public String setlinktolink(
			ModelMap model
			,@PathVariable int id
			,@PathVariable int workflow_id
			)throws IOException {

		process process =  EdataDAO.getProcess(id);
		model.addAttribute("process",process); 
		model.addAttribute("workflow_id",workflow_id); 
		
		dataset enumMain = DatasetDAO.getMain("Workflow_status");
		List<dataset_data> enumData =  Dataset_dataDAO.getall(enumMain.getDataset_id()); 
		model.addAttribute("enumData", enumData);
		
		
		
		return "/workflow/setlinktolink";
	}
	
	
	
	@RequestMapping(value="/savelinktolink-workflow",method = RequestMethod.POST)
	public String savelinktolink(
			ModelMap model
			,@RequestParam int id
			,@RequestParam int link_to
			,@RequestParam int workflow_id
			,@RequestParam String relationship
			,@RequestParam String data_transfer
			,@RequestParam String integrity
			,@RequestParam(defaultValue ="-",value = "current_status",required=false) String current_status
			) {

			EdataDAO.setRelationship(id,link_to,relationship,data_transfer,integrity,current_status);
			return "redirect:/setlink-workflow/"+workflow_id;
		
	}
	
	

	@RequestMapping(value="/setworkflowapp-workflow/{id}",method = RequestMethod.GET)
	public String setworkflowapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		MainServices.addNotificationAttributes(model);
		Platform  platform = new Platform();
		platform.setApp_workflow_id(id);
		LoadApp loadApp = new LoadApp();	
		platformService.initiate(loadApp).Property(platform).workflow().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		model.addAttribute("id",id); 
		return "/workflow/workflow";
	}
	
	
	
	@RequestMapping(value="/docworkflowapp-workflow",method = RequestMethod.GET)
	public String docworkflowapp() {
		
		return "/workflow/doc";
	}
	
	
	@RequestMapping(value="/setformsworkflowapp-workflow/{id}",method = RequestMethod.GET)
	public String setformsworkflowapp(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	 MainServices.addNotificationAttributes(model);
		
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  AtomicReference<Integer> notice = new AtomicReference<>(0);
		  
		  List<condition> flow = ConditionDAO.getAllbylink(id);

		  flow.stream().forEach(row -> {
			  
			  
			 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(row.getDatalink_idrefer());
			 
			 System.err.println("idrefer "+row.getDatalink_idrefer());
			 System.err.println("upload_pn "+upload_pn.getUpload_pn_id());
			 
			 
			 if(upload_pn.getUpload_pn_id() == 0) {
				 notice.set(notice.get() + 1);
			 }
			 
			 
		      List<eformfield> eformfield = eformfields.stream()
		          .filter(sub -> sub.getField_formid() == row.getCondition_id())
		          .filter(sub -> sub.getField_formrv() == row.getCondition_rv())
		          .collect(Collectors.toList());
		      row.setEformfields(eformfield);
		  });

		  int error = validateDatalinkName(flow);

		  
		  
		  List<user> userss = UserDAO.getall(0,0);
		  model.addAttribute("userall", userss);
		  
		  
		  
		  user creator = UserDAO.getbyId(workflows.getWorkflow_idadd(),0);
		  model.addAttribute("creator",creator); 
		  
		  
			 List<process> process =  EdataDAO.getAllProcessBytype(workflows.getWorkflow_id(),0);
			 process.stream().forEach(row->{
				 List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(row.getId());
				 row.setSub_processes(sub_processes);
			 });
			 
			 model.addAttribute("process",process); 
			 

			 
			 
		  List<Step> Steptype = Arrays.asList(Step.values());
		  model.addAttribute("Steptype", Steptype);
		  model.addAttribute("notice",notice.get()); 
		  model.addAttribute("error",error); 
		  model.addAttribute("flow",flow); 
	  
		return "/workflow/eform";
	}
	
	
	
	 public  int validateDatalinkName(List<condition> eformfield) {
	        if (eformfield.isEmpty()) return 0;

	        String[] purchase = {"purchase_requisition", "purchase_order", "goods_receipt"};
	        String[] purchase2 = {"purchase_requisition", "purchase_order"};
	        String[] requisition = {"requisition_form"};
	        String[] pettycashbook = {"petty_cash_book"};
	        String[] stockrequisition = {"inventory_registration","stock_requisition","direct_goods_receipt"};

	        List<String> datalinkNames = eformfield.stream()
	                .map(condition::getDatalink_name)
	                .collect(Collectors.toList());

	        if (datalinkNames.size() < 1 || datalinkNames.size() > 3) return 1;

	        List<String[]> validSets = Arrays.asList(requisition, pettycashbook, stockrequisition, purchase2, purchase);
	        
	        for (String[] validSet : validSets) {
	            if (matchesSet(datalinkNames, validSet)) {
	                return 0;
	            }
	        }
	        return 1;
	    }

	    private static boolean matchesSet(List<String> datalinkNames, String[] validSet) {
	        Set<String> validSetSet = new HashSet<>(Arrays.asList(validSet));
	        Set<String> datalinkSet = new HashSet<>(datalinkNames);
	        return datalinkSet.size() == validSetSet.size() && validSetSet.containsAll(datalinkSet);
	    }
	
	
	
	
	@RequestMapping(value="/rm-workflow",method = RequestMethod.GET)
	public String rmworkflow(ModelMap model) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 System.out.println("------------1");
		 
		return "/workflow/rm";
		
	}
	
	
	
	@RequestMapping(value="/diagram-workflow/{id}",method = RequestMethod.GET)
	public String diagram(
			ModelMap model,
			@PathVariable int id,
			@RequestParam(defaultValue ="0",value = "flowid",required=false) int flowid) 
					throws IOException {
	
		 MainServices.addNotificationAttributes(model);
		
		 
		 System.out.println("------------1");
		 
		 
		 workflow workflows = WorkflowDAO.getbyid(id);
		 model.addAttribute("workflows",workflows);  
		 model.addAttribute("id",id); 
		 System.out.println("------------2");
		 
	      List<condition> conditionadds = ConditionDAO.getAllNotRefer();
	      List<condition> conditionadd =  conditionadds.stream()
	    		  .filter(row->row.getJoin_type() <= 0)
	    		  .collect(Collectors.toList());
	      model.addAttribute("conditionadd",conditionadd); 
	      
	      
	      
		 List<eformfield> eformfields = EformfieldDAO.getall(); 
		  model.addAttribute("eformfields",eformfields); 
	      
	    
		
		  
		  
		List<condition> flow = ConditionDAO.getAllbylink(id);
		  
		
		  
		flow.stream().forEach(row->{
			  
		List<eformfield> eformfield =  eformfields.stream()
			  .filter(sub-> sub.getField_formid() == row.getCondition_id() )
			  .filter(sub-> sub.getField_formrv() == row.getCondition_rv() ).collect(Collectors.toList());
				row.setEformfields(eformfield);
			  
		  });
		  
		
		model.addAttribute("flow",flow); 
	  
		return "/workflow/diagram";
		
	}
	
	
	
	
	

	@RequestMapping(value="/property-workflow/{id}/{workflow_id}",method = RequestMethod.GET)
	public String property(
			ModelMap model
			,@PathVariable int id
			,@PathVariable int workflow_id
			) {
		 MainServices.addNotificationAttributes(model);
	 	condition conditions  =  ConditionDAO.getbyid(id);
	 	List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
	 	workflow workflows = WorkflowDAO.getbyid(workflow_id);
	 	
	 	if(workflows != null) {
	 		List<condition> Associated =  ConditionDAO.getallString(workflows);
	 		model.addAttribute("Associated",Associated); 
	 	}
 	
	 	fieldindex key = WorkflowDAO.geteformfield(workflow_id,id);
	 	
	 	if(key.getFieldindex_id() < 1) {
	 		Workflowservice.SetPropertyAuto(workflows,id);
	 		key = WorkflowDAO.geteformfield(workflow_id,id);
	 	}
	 
	 	System.out.println(" key "+key.getField_index_idcondition() );
	 	
	 	
	 	model.addAttribute("key",key); 
	 	model.addAttribute("eformfields",eformfields); 
	 	model.addAttribute("Traceability",Traceability.values()); 

			return "/workflow/key";
		}
	
	
	@RequestMapping(value="/save-workflow",method = RequestMethod.POST)
	public String property(fieldindex fieldindexs,RedirectAttributes redirectAttributes) {
		
		 System.out.println("workflow property "+fieldindexs);
		
		 
		 
			if(WorkflowDAO.updatefieldindexs(fieldindexs) > 0 ) {
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
			}else {
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Sys_Failed.name());	
			}
			return "redirect:/property-workflow/"+fieldindexs.getField_index_idcondition()+"/"+fieldindexs.getFieldindex_idrefer()+"/";
		}
	
	
	
	@RequestMapping(value="/newTraceability-workflow",method = RequestMethod.POST)
	public String saveProduction(ModelMap model,search_workflowDTO search,HttpSession session,control_steps  control_steps) {
		
		
		
	 System.out.println(" --> "+search.getSearch_product());
		
	 control_steps  product = ConditionDAO.getCustomer(search.getSearch_batch());

	

	 
	 if(product == null) {
		  product = ConditionDAO.getProduct(search.getSearch_batch());
	 }
		 model.addAttribute("product",product); 
	
	 
	 
	    search_workflowDTO searchs = new search_workflowDTO();
		 int id = search.getSearch_id();	

		 
		 
	    List<trace> trace =  WorkflowDAO.getAllTrace(id);
	
	    String stpe1 = "title.title,material.stock_name,maindata.batch,fg.quantity,fg.unit,material.supplier_name,material.batch_material,product.start_date,product.operator";
	  
	    if(control_steps.getStep_1() != null ) {
	    	stpe1 = control_steps.getStep_1();
	    }
	    
	    String[] key1 = {
	    	    "title",
	    	    "material",
	    	    "batch",
	    	    "quantity",
	    	    "unit",
	    	    "supplier",
	    	    "batch_material",
	    	    "start",
	    	    "data_nameadd"
	    	};
	    


	    
	    Set<String> stpe1Set = new HashSet<>(Arrays.asList(stpe1.split(",")));
	    String[] Set1 = new HashSet<>(Arrays.asList(stpe1.split(","))).toArray(String[]::new); 
	    String[] filteredKey1 = Arrays.stream(key1)
	    	    .filter(k -> {
	    	        try {
	    	            Tkey1 enumValue = Tkey1.valueOf(k);
	    	            return stpe1Set.contains(enumValue.getLabel());
	    	        } catch (IllegalArgumentException e) {
	    	            return false;
	    	        }
	    	    })
	    	    .toArray(String[]::new); 
	    
	    
	    
	    
	    String stpe2 = "title.title,material.stock_name,maindata.pd,product.equipment,data.quality_control,product.inspection_result,data.deviations,product.start_date,product.end_date,data.good_production_output,product.operator";  
	   
	    if(control_steps.getStep_2() != null ) {
	    	stpe2 = control_steps.getStep_2();
	    }
	    
	    String[] key2 = {
	    	    "title",
	    	    "batch",
	    	    "production_directive",
	    	    "equipment",
	    	    "quality_control",
	    	    "inspection_result",
	    	    "deviations",
	    	    "start",
	    	    "end",
	    	    "good_production_output",
	    	    "data_nameadd"
	    	};
	    
	    
	    Set<String> stpe2Set = new HashSet<>(Arrays.asList(stpe2.split(",")));
	    String[] Set2 = new HashSet<>(Arrays.asList(stpe2.split(","))).toArray(String[]::new); 
	    String[] filteredKey2 = Arrays.stream(key2)
	    	    .filter(k -> {
	    	        try {
	    	            Tkey2 enumValue = Tkey2.valueOf(k);
	    	            return stpe2Set.contains(enumValue.getLabel());
	    	        } catch (IllegalArgumentException e) {
	    	            return false;
	    	        }
	    	    })
	    	    .toArray(String[]::new); 
	    
	    
	    
	    String stpe3 = "title.title,material.stock_name,maindata.product,product.customer_name,fg.issue_type,fg.quantity,fg.unit,product.start_date,product.operator";  
	  
	    if(control_steps.getStep_3() != null ) {
	    	stpe3 = control_steps.getStep_3();
	    }
	    String[] key3 = {
	    	    "title",
	    	    "batch",
	    	    "product",
	    	    "customer",
	    	    "issue_type",
	    	    "quantity",
	    	    "unit",
	    	    "start",
	    	    "data_nameadd"
	    	};
	    

	    Set<String> stpe3Set = new HashSet<>(Arrays.asList(stpe3.split(",")));
	    String[] Set3 = new HashSet<>(Arrays.asList(stpe3.split(","))).toArray(String[]::new); 
	    
	    String[] filteredKey3 = Arrays.stream(key3)
	    	    .filter(k -> {
	    	        try {
	    	            Tkey3 enumValue = Tkey3.valueOf(k);
	    	            return stpe3Set.contains(enumValue.getLabel());
	    	        } catch (IllegalArgumentException e) {
	    	            return false;
	    	        }
	    	    })
	    	    .toArray(String[]::new); 
	    
	    
	    
	    
	    
	    trace.forEach(row->{
	    	System.out.println(" row "+row.getTrace_name());
	    	System.out.println(" sub process "+row.getTrace_processes());
	    	String sub_process = row.getTrace_processes();

	    	//Rawmaterial Details ,Production Details ,Finish Product Details
	    
	    	
	    	if(row.getTrace_key() == null) {
	    		row.setKey(filteredKey1);
	    		row.setKey2(Set1);
	    	}else if(row.getTrace_key().equals("Rawmaterial Details")) {
	    		row.setKey(filteredKey1);
	    		row.setKey2(Set1);
	    	}
	    	else if(row.getTrace_key().equals("Production Details")) {
	    		row.setKey(filteredKey2);
	    		row.setKey2(Set2);
	    	}
	    	else {
	    		row.setKey(filteredKey3);
	    		row.setKey2(Set3);
	    	}
	    	
	    	
	    	List<edata> data = EdataDAO.getAllTraceability(sub_process,row.getTrace_app(),search);
	    	
	    	data.stream().forEach(rows->{
	    		if(rows.getData_id() > 0) {
	    		 List<file> files = FileDAO.getallEdata(rows.getData_id());
	    		 rows.setFile(files);
	    		}
	    	});
	   

	    	row.setData(data);
	    });
	    model.addAttribute("trace",trace); 

	    
	    
	    
	List<control_steps> batch = ConditionDAO.getAllOrder_management_batch();
		
	    Set<Object> seen2 = new HashSet<>();
		List<control_steps> batchs =  batch.stream()
				.filter(row-> otherservices.StringValueNull(row.getBatch()) != null)
		        .filter(row -> seen2.add(row.getBatch()) )
				.collect(Collectors.toList());
		
		
		
	    model.addAttribute("batch",batchs); 

		List<control_steps> materials = ConditionDAO.getAllMaterial(id);
	    model.addAttribute("materials",materials); 
	    

	    
	    Set<Object> seen = new HashSet<>();
	    List<control_steps> pds = batch.stream()
	        .filter(row -> row.getProduction_directive() != null)
	        .filter(row -> seen.add(row.getProduction_directive()))
	        .collect(Collectors.toList());
	    

	    ConditionDAO.setControl_steps(control_steps);
	    
		 model.addAttribute("id",control_steps.getStep()); 
	    model.addAttribute("pds",pds); 
		model.addAttribute("search_status",1); 
		model.addAttribute("search",search); 
					
		
	     List<Tkey1> key1s = Arrays.asList(Tkey1.values());
	     model.addAttribute("key1", key1s);
			 
	     List<Tkey2> key2s = Arrays.asList(Tkey2.values());
	     model.addAttribute("key2", key2s);
	    
	     List<Tkey3> key3s = Arrays.asList(Tkey3.values());
	     model.addAttribute("key3", key3s);
		
		
		
		return "/workflow/Production";
	}
	
	

	private List<edata> SearchData(search_workflowDTO search, List<edata> data,String sub_process) {
		
		
		
		String start = otherservices.DefaultValueString(search.getSearch_date());
		String end = otherservices.DefaultValueString(search.getSearch_date_end());
		String production_directive = otherservices.DefaultValueString(search.getSearch_pd()); 
		String batch_material = otherservices.DefaultValueString(search.getSearch_batch_material());
		String batch =  otherservices.DefaultValueString(search.getSearch_batch());
		
		System.out.println(" batch "+batch);
		System.out.println(" start "+start);
		System.out.println(" end "+end);
		
		
		return 	data.stream()
				  .filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
				  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
                  .filter(row -> {
                	    if (start.equals("-")) return true;
                	    if (otherservices.IsStringDate(row.getStart()) == false) return true;

                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
                	    LocalDate searchDate = LocalDate.parse(start);

                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
                	})
                  .filter(row -> {
                	    if (end.equals("-")) return true;

                	    String endTarget = row.getEnd();
                	    if (endTarget == null || !otherservices.IsStringDate(endTarget)) {
                	        endTarget = row.getStart();
                	        if (endTarget == null || !otherservices.IsStringDate(endTarget)) return true;
                	    }

                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
                	    LocalDate searchDate = LocalDate.parse(end);

                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
                	})
		
			    .collect(Collectors.toList());
	    
		

		
	}









	public void serach(ModelMap model,ArrayList<String>  m_pd,int finish_size,int production_size,int material_size,List<datalink> chan, search_workflowDTO search ) {
		 finish(model,m_pd,finish_size,production_size,material_size,chan,search);
	}
	
	
	
	
	
	
	private void material(ModelMap model, ArrayList<String> m_pd, int finish_size, int production_size,
			int material_size, List<datalink> chan, search_workflowDTO search) {
		
		
		 AtomicReference<Integer> size = new AtomicReference<>();
		 List<datalink> material = chan.stream().filter(row->row.getDatalink_name().equals("rawmaterial") ).collect(Collectors.toList());
		 material.stream().forEach(main->{
				condition conditions  =  ConditionDAO.getbyid(main.getDatalink_idrefer());
				List<edata>  sub_material =  EdataDAO.material(conditions.getCondition_id(),conditions.getCondition_rv(),search);  
				
				
				if (size.get() != null) {
					size.set(size.get() + sub_material.size());
					}else {
						size.set(sub_material.size());
					}
				
				
				sub_material.stream().forEach(row->{
					List<file> file = FileDAO.getallfile_edata(row.getData_id());
					row.setFile(file);
					
					if(search.getSearch_pd().equals("-")) {
						m_pd.add(row.getProduction_directive());
					}
					
				});
				 main.setCondition(conditions.getCondition_title());
				 main.setEdatas(sub_material);
			 });	
		 
		     System.out.println("material m_pd Size: " + m_pd.size());
		 
			if(m_pd.size() > 0) {
				  HashSet<String> uniqueSet = new HashSet<>(m_pd);
				  ArrayList<String>  m_pd1 = new ArrayList<>(uniqueSet);
				  search.setSearch_pd( String.join(",", m_pd1) );
			 }
		 
		 
		 
		      
		  	if (size.get() != null) {
		  		material_size =  size.get();
	            System.out.println("Material Size: " + material_size);
	        } else {
	            System.out.println("Material Size is null");
	        }
			
		      
		      System.out.println("production_size "+production_size);
		      System.out.println("material_size "+material_size);
		      
		      if(production_size == 0  && material_size > 0 ) { 
		    	  
		    	
	
		    	  
		    	  
		    	  System.err.println("Try again."+chan.size());
		    	  
		    	  production(model,m_pd,finish_size,production_size,material_size,chan,search);
		      }
		      
			 model.addAttribute("material",material); 
		
	}



	private void production(ModelMap model, ArrayList<String> m_pd, int finish_size, int production_size,
			int material_size, List<datalink> chan, search_workflowDTO search) {
	

		
		
		 AtomicReference<Integer> size = new AtomicReference<>();
			System.err.println("production----- -->"+chan.size());
		 
	
		 List<datalink> production = chan.stream().filter(row->row.getDatalink_name().equals("production") ).collect(Collectors.toList());
			System.err.println("production----- -->"+production.size());
		 
		 production.stream().forEach(main->{
				condition conditions  =  ConditionDAO.getbyid(main.getDatalink_idrefer());
				
		
				List<edata>  sub_production =  EdataDAO.production(conditions.getCondition_id(),conditions.getCondition_rv(),search);  
				
				
				if (size.get() != null) {
					size.set(size.get() + sub_production.size());
					}else {
						size.set(sub_production.size());
					}
				
				sub_production.stream().forEach(row->{
					List<file> file = FileDAO.getallfile_edata(row.getData_id());
					row.setFile(file);
					
					if(search.getSearch_pd().equals("-")) {
						m_pd.add(row.getProduction_directive());
					}
				});
				
		
						 main.setCondition(conditions.getCondition_title());
						 main.setEdatas(sub_production);
				
			 });	
		 
			if(m_pd.size() > 0) {
				  HashSet<String> uniqueSet = new HashSet<>(m_pd);
				  ArrayList<String>  m_pd1 = new ArrayList<>(uniqueSet);
				  search.setSearch_pd( String.join(",", m_pd1) );
			 }
			
			
			
	
			 
			if (size.get() != null) {
				production_size = size.get();
	            System.out.println("production_size: " + material_size);
	        } else {
	            System.out.println("production_size is null");
	        }
			
			 
			 if(material_size == 0 ) {
				 material(model,m_pd,finish_size,production_size,material_size,chan,search);
			 }
			 if(finish_size == 0  && production_size > 0) {
				 finish(model,m_pd,finish_size,production_size,material_size,chan,search);
			 }
			 
			 
			 model.addAttribute("production",production); 
			 
	}
			 
			 




	private void finish(ModelMap model, ArrayList<String> m_pd, int finish_size, int production_size, int material_size,
		List<datalink> chan, search_workflowDTO search) {
		
		
		AtomicReference<Integer> size = new AtomicReference<>();
		List<datalink> finish = chan.stream().filter(row->row.getDatalink_name().equals("finish_product") ).collect(Collectors.toList());
		finish.stream().forEach(main->{
			
			
			condition conditions  =  ConditionDAO.getbyid(main.getDatalink_idrefer());
			List<edata>  sub_finish =  EdataDAO.production(conditions.getCondition_id(),conditions.getCondition_rv(),search);  
			
			if (size.get() != null) {
			size.set(size.get() + sub_finish.size());
			}else {
				size.set(sub_finish.size());	
			}
			
			sub_finish.stream().forEach(row->{
				List<file> file = FileDAO.getallfile_edata(row.getData_id());
				row.setFile(file);
				
				if(search.getSearch_pd().equals("-")) {
					m_pd.add(row.getProduction_directive());
				}
				
			});
			
		
			 main.setCondition(conditions.getCondition_title());
			 main.setEdatas(sub_finish);
			 
			 
		 });	
		
		if(m_pd.size() > 0) {
			  HashSet<String> uniqueSet = new HashSet<>(m_pd);
			  ArrayList<String>  m_pd1 = new ArrayList<>(uniqueSet);
			  search.setSearch_pd( String.join(",", m_pd1) );
		 }
		
		
		if (size.get() != null) {
			finish_size = size.get();
            System.out.println("finish_size: " + material_size);
        } else {
            System.out.println("finish_size is null");
        }
		
		
	
		model.addAttribute("finish",finish); 
		
		if(production_size == 0) {
		production(model,m_pd,finish_size,production_size,material_size,chan,search);
		}
		
	}









	@RequestMapping(value="/viewProduction-workflow/{id}",method = RequestMethod.GET)
	public String viewProduction(
			ModelMap model,
			@PathVariable int id) {

		 control_steps   control_steps = ConditionDAO.getControl_steps(id);
		 model.addAttribute("control_steps",control_steps);
		
		List<control_steps> batch = ConditionDAO.getAllOrder_management_batch();
		
	    Set<Object> seen2 = new HashSet<>();
		List<control_steps> batchs =  batch.stream()
				.filter(row-> otherservices.StringValueNull(row.getBatch()) != null)
		        .filter(row -> seen2.add(row.getBatch()) )
				.collect(Collectors.toList());
		
		
		
	    model.addAttribute("batch",batchs); 

		List<control_steps> materials = ConditionDAO.getAllMaterial(id);
	    model.addAttribute("materials",materials); 
	    

	    
	    Set<Object> seen = new HashSet<>();
	    List<control_steps> pds = batch.stream()
	        .filter(row -> row.getProduction_directive() != null)
	        .filter(row -> seen.add(row.getProduction_directive()))
	        .collect(Collectors.toList());
	    
	    
	    
	    
	    model.addAttribute("pds",pds); 
	
	    
	     List<Tkey1> key1 = Arrays.asList(Tkey1.values());
	     model.addAttribute("key1", key1);
			 
	     List<Tkey2> key2 = Arrays.asList(Tkey2.values());
	     model.addAttribute("key2", key2);
	    
	     List<Tkey3> key3 = Arrays.asList(Tkey3.values());
	     model.addAttribute("key3", key3);
	    
	     
		 model.addAttribute("id",id); 
		return "/workflow/Production"; 
				 
		
	}
	
	
	
	
	

	 
	 
	
	
	@RequestMapping(value="/add-workflow",method = RequestMethod.POST)
	public String saveworkflow(ModelMap model,workflow workflows
			,@RequestParam int ids
			,@RequestParam(value = "workflow_eforms",required=false) List<String> workflow_eforms
			,@RequestParam(value = "eflow_key1",required=false) List<String> eflow_key1
			,@RequestParam(value = "eflow_key2",required=false) List<String> eflow_key2
			,@RequestParam(value = "eflow_key3",required=false) List<String> eflow_key3
			,@RequestParam(value = "eflow_key4",required=false) List<String> eflow_key4
			,@RequestParam(value = "workflow_names",required=false) List<String> workflow_names
			){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime today = LocalDateTime.now();
		String date = today.format(formatter);
		workflows.setWorkflow_dateadd(date);
		

		int id = WorkflowDAO.save(workflows);
		if(workflow_eforms != null) {
		 EflowDAO.set(workflow_eforms,workflow_names,id,eflow_key1,eflow_key2,eflow_key3,eflow_key4);
		}
		
		
		workflow workflowdata = WorkflowDAO.getbyid(id);
		model.addAttribute("workflow",workflowdata);  
		model.addAttribute("ids",ids);  

		return "/eformparts/saveworkflow";
		
	}
	

	
	
	
	@RequestMapping(value="/edit-workflow",method = RequestMethod.POST)
	public String editworkflow(
			RedirectAttributes redirectAttributes
			,workflow workflows
			) {

		
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
				WorkflowDAO.update(workflows);
				return "redirect:/setting-workflow/"+workflows.getWorkflow_id();
	}
	
	
	
	@RequestMapping(value="/edit2-workflow",method = RequestMethod.POST)
	public String editworkflow2(
			RedirectAttributes redirectAttributes
			,workflow workflows
			) {

		        workflow workflowsave = WorkflowDAO.getbyid(workflows.getWorkflow_id());
		        workflowsave.setWorkflow_idadmin(workflows.getWorkflow_idadmin());
		        workflowsave.setWorkflow_name(workflows.getWorkflow_name());
				WorkflowDAO.update(workflowsave);
				
				
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
				return "redirect:/settingapp-workflow/"+workflows.getWorkflow_id();
	}
	 
	
	
	@RequestMapping(value="/installation-workflow",method = RequestMethod.POST)
	public String installation(
			RedirectAttributes redirectAttributes
			,workflow workflows
			) {

		   
		
		        String location =   Filemanagerservice.getLocationName(workflows.getWorkflow_folder());
		        workflow workflowsave = WorkflowDAO.getbyid(workflows.getWorkflow_id());
		        workflowsave.setWorkflow_folder(workflows.getWorkflow_folder());
		        workflowsave.setWorkflow_location(location);
		        workflowsave.setWorkflow_maindb(workflows.getWorkflow_maindb());
		        workflowsave.setWorkflow_subdb(workflows.getWorkflow_subdb());
		        workflowsave.setWorkflow_subdb2(workflows.getWorkflow_subdb2());
		        workflowsave.setWorkflow_subdb3(workflows.getWorkflow_subdb3());
		        workflowsave.setWorkflow_join(workflows.getWorkflow_join());
		        
				WorkflowDAO.update(workflowsave);
				
				
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
				return "redirect:/settingapp-workflow/"+workflows.getWorkflow_id();
	}
	 
	
	
	
	@RequestMapping(value="/integrationsave-workflow",method = RequestMethod.POST)
	public String integrationsave(
			RedirectAttributes redirectAttributes
			,workflow workflows
			) {

				workflow workflowsave = WorkflowDAO.getbyid(workflows.getWorkflow_id());
				workflowsave.setWorkflow_key1(workflows.getWorkflow_key1());
				WorkflowDAO.update(workflowsave);
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
				return "redirect:/integrationsapp-workflow/"+workflows.getWorkflow_id();
	}

	
	@RequestMapping(value="/delprocess-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delprocess(
			RedirectAttributes redirectAttributes
		   ,@PathVariable int workflow_id
		   ,@PathVariable int id
			) {

			EdataDAO.remove_process(id);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
			return "redirect:/integrationsapp-workflow/"+workflow_id;
	}
	
	
	@RequestMapping(value="/editprocess-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editprocess(
			@PathVariable int workflow_id
		   ,@PathVariable int id
		   ,ModelMap model
			) {
			process this_process = EdataDAO.getProcess(id);
		 model.addAttribute("this_process",this_process); 
		 model.addAttribute("workflow_id",workflow_id); 
		 
		    workflow workflows  = WorkflowDAO.getbyid(workflow_id);
		    model.addAttribute("workflows",workflows); 
		 
		    
		    List<process> process =  EdataDAO.getAllProcess(workflows.getWorkflow_id());
			 
			 process.stream().forEach(row->{
				 
					condition condition = ConditionDAO.getbyid(row.getId_condition());
					row.setCondition(condition);
			 });
			 
			 model.addAttribute("process",process); 
		    
				condition conditions = ConditionDAO.getbyid(this_process.getId_condition());
			   List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
				model.addAttribute("eformfields",eformfields); 
					        
			 
			 
			 List<process> process2 =  EdataDAO.getAllProcessJoin(workflows.getWorkflow_id());
			 
			 process2.stream().forEach(row->{
					workflow join = WorkflowDAO.getbyid(row.getId_refer());
				    row.setNote(join.getWorkflow_name());
			 });
			 
			 model.addAttribute("process2",process2); 
			 
			 
			 
			 
			    List<condition> conditionadds = ConditionDAO.getAllNotRefer();
			      List<condition> conditionadd =  conditionadds.stream()
			    		  .filter(row->row.getJoin_type() <= 0)
			    		  .collect(Collectors.toList());
			      model.addAttribute("conditionadd",conditionadd); 
			      
			      

				        
			      
			      
		    
			return "/workflow/editprocess";
		}
		
	

	
	
	@RequestMapping(value="/delpolicies-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delpolicies(
			RedirectAttributes redirectAttributes
		   ,@PathVariable int workflow_id
		   ,@PathVariable String id
			) {

			EdataDAO.DelPolicies_item(id);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
			return "redirect:/workload-workflow/"+workflow_id;
	}
	
	
	
	@RequestMapping(value="/editpolicies-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String editpolicies(
			RedirectAttributes redirectAttributes
		   ,@PathVariable int workflow_id
		   ,@PathVariable String id
		    ,ModelMap model
			) {
		
		
		
		    List<policies> policies = EdataDAO.getAllGroupPolicies(id);
		    model.addAttribute("policies",policies); 
		    model.addAttribute("workflow_id",workflow_id); 
		    
		    
		    List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(policies.get(0).getProcess());
		  
		    
		    
		    sub_processes.stream().forEach(row->{
					 policies Policies =  EdataDAO.getPolicies(row.getId());
					 row.setPolicies(Policies);
				 });
		    model.addAttribute("sub_processes",sub_processes); 
		    
		    workflow workflows  = WorkflowDAO.getbyid(workflow_id);
		    model.addAttribute("workflows",workflows); 
		    

			 
			  List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);

			 
			 List<groups> groupss = GroupsDAO.getall(0);
			 model.addAttribute("groupss",groupss); 
			 
			List<Step> Steptype = Arrays.asList(Step.values());
			model.addAttribute("Steptype", Steptype);
			
			
			  List<department>  departments = DepartmentDAO.getall();
			  model.addAttribute("departments",departments); 
			
		    
		    
		    
		    
		    
			return "workflow/policies";
		}
	
	

	
	
	@RequestMapping(value="/delsubprocess-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
	public String delsubprocess(
			RedirectAttributes redirectAttributes
		   ,@PathVariable int workflow_id
		   ,@PathVariable int id
			) {

			EdataDAO.remove_subprocess(id);
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
			return "redirect:/setworkflowapp-workflow/"+workflow_id;
	}
	
	
	
	

	@RequestMapping(value="/setkey-workflow",method = RequestMethod.POST)
	public String setkey(
			@RequestParam(value = "key1",required=false) String key1
			,@RequestParam(value = "key2",required=false) String key2
			,@RequestParam(value = "key3",required=false) String key3
			,@RequestParam(value = "key4",required=false) String key4
			,@RequestParam(value = "key4",required=false) int id
			) {
		
		
		eflow eflows = new eflow();
		eflows.setEflow_key1(key1);
		eflows.setEflow_key2(key2);
		eflows.setEflow_key3(key3);
		eflows.setEflow_key4(key4);
		eflows.setEflow_id(id);
		EflowDAO.update(eflows);
		
		
		return "/eformparts/editworkflow";
		
	}
	 
	
	
	@RequestMapping(value="/search-workflow",method = RequestMethod.POST)
	public String search(ModelMap model
			,@RequestParam int ids
			,@RequestParam(value = "key1",required=false) String eflow_key1
			,@RequestParam(value = "keytitle5",required=false) String keytitle5
			,@RequestParam(value = "key5",required=false) String eflow_key5
			,@RequestParam(value = "dt_date",required=false) String dt_date

			
			,@RequestParam(value = "purchase_order",required=false) String purchase_order
			,@RequestParam(value = "keyno_purchase_order",required=false) String keyno_purchase_order
			,@RequestParam(value = "customer",required=false) String customer
			,@RequestParam(value = "keyno_customer",required=false) String keyno_customer
			,@RequestParam(value = "product",required=false) String product
			,@RequestParam(value = "keyno_product",required=false) String keyno_product
			,@RequestParam(value = "lot",required=false) String lot
			,@RequestParam(value = "keyno_lot",required=false) String keyno_lot
			,@RequestParam(value = "material",required=false) String material
			,@RequestParam(value = "keyno_material",required=false) String keyno_material
			,@RequestParam(value = "lot_material",required=false) String lot_material
			,@RequestParam(value = "keyno_lot_material",required=false) String keyno_lot_material
			,@RequestParam(value = "wip",required=false) String wip
			,@RequestParam(value = "keyno_wip",required=false) String keyno_wip
			,@RequestParam(value = "lot_wip",required=false) String lot_wip
			,@RequestParam(value = "keyno_lot_wip",required=false) String keyno_lot_wip
			
			,@RequestParam(value = "keytitle_purchase_order",required=false) String keytitle_purchase_order
			,@RequestParam(value = "keytitle_customer",required=false) String keytitle_customer
			,@RequestParam(value = "keytitle_product",required=false) String keytitle_product
			,@RequestParam(value = "keytitle_lot",required=false) String keytitle_lot
			,@RequestParam(value = "keytitle_material",required=false) String keytitle_material
			,@RequestParam(value = "keytitle_lot_material",required=false) String keytitle_lot_material
			,@RequestParam(value = "keytitle_wip",required=false) String keytitle_wip
			,@RequestParam(value = "keytitle_lot_wip",required=false) String keytitle_lot_wip
			,@RequestParam(value = "keytitle_date",required=false) String keytitle_date
			,@RequestParam int flowid
			,@RequestParam String flowtype
			){
		
		
		
		
		
		
		
		
		

		workflow workflows = WorkflowDAO.getbyid(ids);
		model.addAttribute("workflows",workflows); 
		
		
		String workflowString = workflows.getWorkflow_key1();
	
	 	
	 	
	 	
	 	
		model.addAttribute("dt_date",dt_date); 
		
		model.addAttribute("keytitle_date",keytitle_date); 
		model.addAttribute("keytitle_purchase_order",keytitle_purchase_order); 
		model.addAttribute("purchase_order",purchase_order); 
		
		model.addAttribute("keytitle_customer",keytitle_customer); 
		model.addAttribute("customer",customer); 
		
		model.addAttribute("keytitle_product",keytitle_product); 
		model.addAttribute("product",product); 
		
		model.addAttribute("keytitle_lot",keytitle_lot); 
		model.addAttribute("lot",lot); 
		
		model.addAttribute("keytitle_material",keytitle_material); 
		model.addAttribute("material",material); 
		
		model.addAttribute("keytitle_lot_material",keytitle_lot_material); 
		model.addAttribute("lot_material",lot_material); 
		
		model.addAttribute("keytitle_wip",keytitle_wip); 
		model.addAttribute("wip",wip); 
		
		model.addAttribute("keytitle_lot_wip",keytitle_lot_wip); 
		model.addAttribute("lot_wip",lot_wip); 


	

		  
		  
	
		if ( workflows.getWorkflow_note()  != null ) {
			
	
			String dateStart = null;
			String dateEnd= null;
			  if (dt_date != null && !dt_date.isEmpty() ) {
				  
				  if (dt_date.contains("to")) {
					  String[] dateParts = dt_date.split(" to ");
			             dateStart = dateParts[0];
			             dateEnd = dateParts[1];
				  }else {
					  dateStart =  dt_date;
					  dateEnd = dt_date;
					  
				  }
				
		        }	
			
			  

		
		map_key map_key;  
	 	int eformid;
   	 
	 	List<String>  StringJoin = new ArrayList<String>();
		List<String>  Stringfaketable = new ArrayList<String>();
		List<String> workflowList = new ArrayList<String>();
		List<Integer>  formid = new ArrayList<Integer>();
		condition conditions;
		
		if(flowtype.equals("forward")) {
			 map_key = ConditionDAO.getMap_key(flowid);
			 eformid =   map_key.getMap_key_id_main();
			Stringfaketable.add(String.valueOf(map_key.getMap_key_id_main()));
			conditions =  ConditionDAO.getbyid(map_key.getMap_key_id_main()); 
			workflowList.add(conditions.getCondition_name());
			formid.add(map_key.getMap_key_id_main());
			 System.out.println("****************map_key " +map_key.getMap_key_id_main());
        	 System.out.println("****************map_key name  " +conditions.getCondition_name());  
			
		}else {
			map_key = ConditionDAO.getMap_keysub(flowid);	
			eformid =   map_key.getMap_key_id_sub();
			Stringfaketable.add(String.valueOf(map_key.getMap_key_id_sub()));
		}
		
		int level = 0;
		
		
		
		
		
	 	edataDTO edataDTO = new edataDTO();

		edataDTO.setData_eformid(eformid);
		edataDTO.setData_eformrv(0);
		

		edataDTO.setPurchase_order(purchase_order);
		edataDTO.setKeyno_purchase_order(keyno_purchase_order);
		
		edataDTO.setCustomer(customer);
		edataDTO.setKeyno_customer(keyno_customer);
		
		edataDTO.setProduct(product);
		edataDTO.setKeyno_product(keyno_product);
		
		edataDTO.setLot(lot);
		edataDTO.setKeyno_lot(keyno_lot);
		
		edataDTO.setMaterial(material);
		edataDTO.setKeyno_material(keyno_material);
		
		edataDTO.setLot_material(lot_material);
		edataDTO.setKeyno_lot_material(keyno_lot_material);
		
		
		edataDTO.setWip(wip);
		edataDTO.setKeyno_wip(keyno_wip);
		
		edataDTO.setLot_wip(lot_wip);
		edataDTO.setKeyno_lot_wip(keyno_lot_wip);
		
		
		edataDTO.setKey5(eflow_key5);
		edataDTO.setDate_start(dateStart);
		edataDTO.setDate_end(dateEnd);
	
    
    	 if(flowtype.equals("forward")) {
    		 
						
					   	  
							
							
					 	  
							
							 StringBuilder queryBuilderchain = new StringBuilder();
							
					        while (map_key != null && level <= 1) {
					        	
					        	 level++;
					        	 System.out.println("level" +level);
					        
					          if(map_key != null) {
					        	  
					        
					        	  
								        	  		StringBuilder queryBuilder = new StringBuilder();
								        	  		queryBuilder.setLength(0);
								        	  		int chain  = 0;
										            if( !StringUtils.isBlank(map_key.getMap_key_main1()) && !StringUtils.isBlank(map_key.getMap_key_sub1()) ) {	
										            	
										            	queryBuilder.append(" edata F").append(map_key.getMap_key_id_main()).append(" ON ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main1());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub1());
										            	chain  = 1;
										            }
										            if( !StringUtils.isBlank(map_key.getMap_key_main2()) && !StringUtils.isBlank(map_key.getMap_key_sub2()) && chain > 0) {	
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main2());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub2());
										            	chain  = 2;
										            }
										            if( !StringUtils.isBlank(map_key.getMap_key_main3()) && !StringUtils.isBlank(map_key.getMap_key_sub3()) && chain > 1) {	
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main3());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub3());
										            	chain  = 3;
										            }
										            if( !StringUtils.isBlank(map_key.getMap_key_main4()) && !StringUtils.isBlank(map_key.getMap_key_sub4()) && chain > 2) {	
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main4());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub4());
										            
										            }
										            if( chain > 0) {	
										            	
										            	queryBuilder.append(queryBuilderchain.toString());
										            	queryBuilderchain.setLength(0);
										            	
										            	queryBuilderchain.append(" JOIN ").append(queryBuilder.toString());
										            	
											        	Stringfaketable.add(String.valueOf(map_key.getMap_key_id_sub())); 
											        	conditions = ConditionDAO.getbyid(map_key.getMap_key_id_sub()); 
											        	workflowList.add(conditions.getCondition_name());
											        	
											           	queryBuilder.append(" WHERE ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_eformid").append(" = '").append(conditions.getCondition_id()).append("'");
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_eformrv").append(" = '").append(conditions.getCondition_rv()).append("'");
										            
										            	
										            	
										            	StringJoin.add(queryBuilder.toString());
										            	
										            	formid.add(map_key.getMap_key_id_sub());
										                System.out.println("****************map_key " +map_key.getMap_key_id_sub());
										          	    System.out.println("****************map_key name  " +conditions.getCondition_name());  
										            }
										          
										        	
										            map_key = ConditionDAO.getMap_key(map_key.getMap_key_id_sub());  
										            if(map_key == null) { level= 15;}
					            }
					            
					           
					        }
					        Collections.reverse(workflowList);
					        Collections.reverse(formid);
    		 
    	 }else {
    		 
					    	
							
							formid.add(map_key.getMap_key_id_sub());
					   	    conditions = ConditionDAO.getbyid(map_key.getMap_key_id_sub()); 
							workflowList.add(conditions.getCondition_name());
							
							
							 StringBuilder queryBuilderchain = new StringBuilder();
							
					        while (map_key != null && level <= 15) {
					        	
					        	 level++;
					        	 System.out.println("level" +level);
					        
					          if(map_key != null) {
					        	  
					        	  System.out.println("****************map_key " +map_key.getMap_key_id());
					        	  
								        	  		StringBuilder queryBuilder = new StringBuilder();
								        	  		queryBuilder.setLength(0);
								        	  		int chain  = 0;
										            if( !StringUtils.isBlank(map_key.getMap_key_main1()) && !StringUtils.isBlank(map_key.getMap_key_sub1()) ) {	
										            	
										            	queryBuilder.append(" edata F").append(map_key.getMap_key_id_sub()).append(" ON ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub1());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main1());
										            	chain  = 1;
										            }
										            if( !StringUtils.isBlank(map_key.getMap_key_main2()) && !StringUtils.isBlank(map_key.getMap_key_sub2()) && chain > 0) {	
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub2());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main2());
										            	chain  = 2;
										            }
										            if( !StringUtils.isBlank(map_key.getMap_key_main3()) && !StringUtils.isBlank(map_key.getMap_key_sub3()) && chain > 1) {	
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub3());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main3());
										            	chain  = 3;
										            }
										            if( !StringUtils.isBlank(map_key.getMap_key_main4()) && !StringUtils.isBlank(map_key.getMap_key_sub4()) && chain > 2) {	
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_sub()).append(".").append("data_value").append(map_key.getMap_key_sub4());
										            	queryBuilder.append(" = ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_value").append(map_key.getMap_key_main4());
										            
										            }
										            if( chain > 0) {	
										            	
										            	queryBuilder.append(queryBuilderchain.toString());
										            	queryBuilderchain.setLength(0);
										            	queryBuilderchain.append(" JOIN ").append(queryBuilder.toString());

										          
											        	Stringfaketable.add(String.valueOf(map_key.getMap_key_id_main())); 
											        	conditions = ConditionDAO.getbyid(map_key.getMap_key_id_main()); 
										          	    workflowList.add(conditions.getCondition_name());
										          	    
										            	queryBuilder.append(" WHERE ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_eformid").append(" = '").append(conditions.getCondition_id()).append("'");
										            	queryBuilder.append(" AND ");
										            	queryBuilder.append("F").append(map_key.getMap_key_id_main()).append(".").append("data_eformrv").append(" = '").append(conditions.getCondition_rv()).append("'");
										            
										            	
										              	StringJoin.add(queryBuilder.toString());
										            }
										          
										            
										            
										        
									            	formid.add(map_key.getMap_key_id_main());
										        
										        	
										            map_key = ConditionDAO.getMap_keysub(map_key.getMap_key_id_main());  
										            if(map_key == null) { level= 15;}
					            }
					            
					           
					        }
					    	
    	 }

        //Collections.reverse(workflowList);
	 	model.addAttribute("workflowList",workflowList); 
        
	 	
	 

        System.out.println("Stringfaketable "+Stringfaketable);
        System.out.println("workflowList "+workflowList);
        System.out.println("formid "+formid);
	  	
    	edataDTO.setJointable(Stringfaketable);
    	edataDTO.setJoin(StringJoin);
        List<edata> edatas = EdataDAO.getall(edataDTO);
        model.addAttribute("formid", formid);

      if(edatas.size() > 0) {
		    	for (int i = 0; i < formid.size(); i++) {
		    		int e= i;
			        List<edata> filteredEdatas = edatas.stream()
			        	    .filter(edata -> edata.getData_eformid() == formid.get(e))
			        	    .collect(Collectors.toList());
			        
			        
			        Set<Integer> seenEformids = new HashSet<>();
			        List<edata> edataList = filteredEdatas.stream().filter(r -> seenEformids.add(r.getData_eformid())).collect(Collectors.toList());
			        model.addAttribute("edatas"+e, edataList);
			        	    
		    	}	
      }
    	
        	
			
			
		}
		
		
		return "/workflow/datalink";
		
	}
	
	 @RequestMapping(value="/rawdata-workflow/{condition_id}",method = RequestMethod.GET)    
	    public String rawdata(ModelMap model,@PathVariable int condition_id){ 
	    	
	    
	    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	MainServices.addNotificationAttributes(model);

	    	 
	    	 
	    	 condition conditions = ConditionDAO.getbyid(condition_id);
	    	 upload_pn upload_pn = Upload_pnDAO.getbyConditionsId(conditions.getCondition_id());
	   
	    	 
	    	 model.addAttribute("upload_pn",upload_pn);
	    	 
	      	 user usersed_app = UserDAO.getbyname(upload_pn.getUpload_pn_app());
	      	 model.addAttribute("usersed_app",usersed_app);

	    	 List<edata> edatas = EdataDAO.getallexample(conditions.getCondition_id(), conditions.getCondition_rv());
	    	 
	    	 model.addAttribute("edatas",edatas); 

	    	 
			

	    	return "/app/rawdata";
	    }
	
	 
		@RequestMapping(value="/addsublink-workflow",method = RequestMethod.POST)
		public String addsublink(
				datalink datalinks
				,@RequestParam int datalink_idrefer
				) {
			
			datalinks.setDatalink_idrefer(datalink_idrefer);

			WorkflowDAO.save(datalinks);			
			return "redirect:/setting-workflow/"+datalinks.getDatalink_workflow();
			}
		
		
		
		@RequestMapping(value="/savepolicies-workflow",method = RequestMethod.POST)
		public String savepolicies(
				policies policies
				,@RequestParam int workflow_id
				) {
			
				//EdataDAO.DelPolicies_item(policies.getSub_processes());	
				EdataDAO.save_policies(policies);
				
			return "redirect:/workload-workflow/"+workflow_id;
			}
		
		@RequestMapping(value="/editpolicies-workflow",method = RequestMethod.POST)
		public String editpolicies(
				policies policies
				,@RequestParam int workflow_id
				,RedirectAttributes redirectAttributes
				) {
				EdataDAO.DelPolicies_item(policies.getGroup_id());	
				EdataDAO.save_policies(policies);
				
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
			return "redirect:/workload-workflow/"+workflow_id;
			}
		
		
		
		@RequestMapping(value="/addsublink2-workflow",method = RequestMethod.POST)
		public String addsublink2(
				datalink datalinks
				,@RequestParam int datalink_idrefer
				,RedirectAttributes redirectAttributes
				) {
			
			datalinks.setDatalink_idrefer(datalink_idrefer);

			WorkflowDAO.save(datalinks);		
			
			redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
			return "redirect:/setting2-workflow/"+datalinks.getDatalink_workflow();
			}
		
		
		
		
		@RequestMapping(value="/addsublinkall-workflow",method = RequestMethod.POST)
		public String addsublink(
				@RequestParam String datalink_name,
				@RequestParam int datalink_workflow,
				@RequestParam String datalink_idrefer,
				@RequestParam String key1,
				@RequestParam String key2,
				@RequestParam String key3
				) {
				WorkflowDAO.delinkall(datalink_workflow);
				WorkflowDAO.saveLinkAll(datalink_name,datalink_idrefer,datalink_workflow,key1,key2,key3);	
				return "redirect:/setting-workflow/"+datalink_workflow;
			}
		
		
		
		
		@RequestMapping(value="/rowmapper-workflow",method = RequestMethod.POST)
		public String rowmapper(
				@RequestParam List<String> field_cal_type,
				@RequestParam List<String> field_cal_type2,
				@RequestParam List<Integer> field_id,
				@RequestParam int datalink_workflow,
				RedirectAttributes redirectAttributes
				) {
			
			List<eformfield> eformfields = new ArrayList<eformfield>(); 
			
			int index = 0;
			for(int id : field_id) {
				eformfield eformfield = new eformfield();
				eformfield.setField_id(id);
				eformfield.setField_cal_type(field_cal_type.get(index));
				eformfield.setField_cal_type2(field_cal_type2.get(index));
				index++;
				eformfields.add(eformfield);
			}
			
			if(eformfields.size() > 0 ) {
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
				EformfieldDAO.update(eformfields);
			}
			
			
			
			
			
				
				return "redirect:/setting-workflow/"+datalink_workflow;
		}
		
		
	 
		@RequestMapping(value="/deldatalink-workflow/{condition_id}/{workflow_id}",method = RequestMethod.GET)
		public String deldatalink(
				@PathVariable int condition_id,
				@PathVariable int workflow_id
				) {
			
			WorkflowDAO.delink(condition_id);	
			return "redirect:/setting-workflow/"+workflow_id;
			}
		 
		
		
			@RequestMapping(value="/deldatalink2-workflow/{condition_id}/{workflow_id}",method = RequestMethod.GET)
			public String deldatalink2(
					@PathVariable int condition_id,
					@PathVariable int workflow_id
					) {
				
				WorkflowDAO.delink(condition_id);	
				return "redirect:/setting2-workflow/"+workflow_id;
				}
			
	 
		
		@RequestMapping(value="/editkey-workflow/{condition_id}/{workflow_id}",method = RequestMethod.GET)
		public String editkey(
				@PathVariable int condition_id,
				@PathVariable int workflow_id,
				ModelMap model
				) {
			
			 condition conditions = ConditionDAO.getbyid(condition_id);
			 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
			  model.addAttribute("eformfields",eformfields); 
			  
			  
			  List<eformcolumntype> columntype = Arrays.stream(eformcolumntype.values())
					    .sorted(Comparator.comparing(Enum::name))
					    .collect(Collectors.toList());
				model.addAttribute("columntype", columntype);
				
				
				
				
	    		return "/app/editkey";
			}
		
		
		
		

		@RequestMapping(value="/savegoal-workflow",method = RequestMethod.POST)
		public String savegoals2(GamificationGoal goal,@RequestParam int workflow_id) {

				gamificationGoalRepository.save(goal);
			   return "redirect:/goals-workflow/"+workflow_id;
		  }
		
		
		
		@RequestMapping(value="/isActivegoals-workflow/{workflow_id}/{id}/{active}",method = RequestMethod.GET)
		public String isActivegoals(@PathVariable int workflow_id,@PathVariable long id,@PathVariable int active) {
	
				    gamificationGoalRepository.findById(id).ifPresent(goal -> {
				        goal.setStatus(active);                // อัปเดตค่าสถานะ (0 หรือ 1)
				        gamificationGoalRepository.save(goal); // บันทึกลง Database
				    });
			   return "redirect:/goals-workflow/"+workflow_id;
		  }
		
		
		@RequestMapping(value="/editgoals-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
		public String editgoals(@PathVariable int workflow_id,@PathVariable long id,ModelMap model) {
		    
			 workflow workflows = WorkflowDAO.getbyid(workflow_id);
			 model.addAttribute("workflows",workflows);  
			 
			 Optional<GamificationGoal> goal =  gamificationGoalRepository.findById(id);
			 if(goal.isPresent()) {
				 model.addAttribute("goal", goal.get());
				 
				 
				condition this_conditions = ConditionDAO.getbyid(goal.get().getEformId());
			    List<eformfield> this_eformfields = EformfieldDAO.getall(this_conditions.getCondition_id(),this_conditions.getCondition_rv()); 
			    model.addAttribute("this_eformfields", this_eformfields);
				 
				dataset dataset = DatasetDAO.getMain("Workflow_status");
				List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
				model.addAttribute("dataset_data", dataset_data);
				
				  List<UseExtension> extension = EdataDAO.getallUseExtension();
					model.addAttribute("extension", extension);
			 }

			  List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);
			  
			  
			  
			   return "/workflow/editgoal";
		  }
		
		
		
		@RequestMapping(value="/deleteMeasurementrules-workflow/{id}",method = RequestMethod.GET)
		public String deleteMeasurementrules(@PathVariable int id) {
		    
					EdataDAO.deleteMeasurement_rules(id);
				  return "redirect:/KPI_Measurement_Rules-workflow";
		  }
		
		
		
		
		@RequestMapping(value="/editMeasurementrules-workflow/{id}",method = RequestMethod.GET)
		public String editMeasurementrules(@PathVariable int id,ModelMap model) {
		    
				Measurement_rules rules =  EdataDAO.getMeasurement_rules(id);
				model.addAttribute("rules", rules);
				
				process this_process  =  EdataDAO.getProcess(rules.getThis_process());
				model.addAttribute("this_process", this_process);
				
				
				dataset dataset = DatasetDAO.getMain("Workflow_status");
				List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
				model.addAttribute("dataset_data", dataset_data);
				
				
				condition this_conditions = ConditionDAO.getbyid(this_process.getId_condition());
		    	List<eformfield> this_eformfields = EformfieldDAO.getall(this_conditions.getCondition_id(),this_conditions.getCondition_rv()); 
		    	model.addAttribute("this_eformfields", this_eformfields);
				
		    	
		    
				
				process destination_process  =  EdataDAO.getProcess(rules.getDestination_process());
				model.addAttribute("destination_process", destination_process);

				condition destination_conditions = ConditionDAO.getbyid(destination_process.getId_condition());
		    	List<eformfield> destination_eformfields = EformfieldDAO.getall(destination_conditions.getCondition_id(),destination_conditions.getCondition_rv()); 
		    	model.addAttribute("destination_eformfields", this_eformfields);
				
				
				
			   return "/workflow/Measurementrules";
		  }
		
		
		
		@RequestMapping(value="/updateMeasurementrules-workflow",method = RequestMethod.POST)
		public String updateMeasurementrules(Measurement_rules rules,RedirectAttributes redirectAttributes) {

			  EdataDAO.save_Measurement_rules(rules);
				redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
			  return "redirect:/editMeasurementrules-workflow/"+rules.getId();
			
		}
		
		
		
	
		
		
		
		@RequestMapping(value="/viewpolicies-workflow/{workflow_id}/{id}",method = RequestMethod.GET)
		public String viewpolicies(
				RedirectAttributes redirectAttributes
			   ,@PathVariable int workflow_id
			   ,@PathVariable String id
			    ,ModelMap model
				) {
			
			
			
			    List<policies> policies = EdataDAO.getAllGroupPolicies(id);
			    model.addAttribute("policies",policies); 
			    model.addAttribute("workflow_id",workflow_id); 
			    

			    List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(policies.get(0).getProcess());
			    model.addAttribute("sub_processes",sub_processes); 
			    
			    
			    sub_processes.stream().forEach(row->{
						 policies Policies =  EdataDAO.getPolicies(row.getId());
						 row.setPolicies(Policies);
					 });
			    
			    
			    workflow workflows  = WorkflowDAO.getbyid(workflow_id);
			    model.addAttribute("workflows",workflows); 
			    

				 
				  List<user> userss = UserDAO.getall(0,0);
				  model.addAttribute("userall", userss);

				 
				 List<groups> groupss = GroupsDAO.getall(0);
				 model.addAttribute("groupss",groupss); 
				 
				List<Step> Steptype = Arrays.asList(Step.values());
				model.addAttribute("Steptype", Steptype);
				
				
				  List<department>  departments = DepartmentDAO.getall();
				  model.addAttribute("departments",departments); 
				
			    
				
				  model.addAttribute("type","view"); 
				 
			    
			    
			    
				return "workflow/policies";
			}
		
		
		
		
		@RequestMapping(value="/budget_finance-workflow/{id}",method = RequestMethod.GET)
		public String budget_finance(
				ModelMap model
				,@PathVariable int id
				) throws IOException {
		
			 MainServices.addNotificationAttributes(model);
			 
			 List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);
			 
			  model.addAttribute("id",id);
			
			  long total = company_annual_planRepository.count();
			  List<company_annual_plan> annual_plan =  company_annual_planRepository.findTop500ByOrderByIdDesc();
			  model.addAttribute("annual_plan",annual_plan);
			  
			  List<String> annual_type = annual_plan.stream()
					    .map(row-> row.getAnnualType())     // ดึงเฉพาะ .type
					    .filter(Objects::nonNull)   // กรอง null ถ้ามี
					    .distinct()                 // เอาเฉพาะค่าที่ไม่ซ้ำ
					    .collect(Collectors.toList());
			  
			  model.addAttribute("annual_type",annual_type);
			  model.addAttribute("annual_plan",annual_plan);
			  model.addAttribute("count",total); 
			return "/Subdata/budget_finance";
		}
		
		
		@RequestMapping(value="/detail_budget-workflow/{budgetid}/{id}",method = RequestMethod.GET)
		public String detail_budget(
				ModelMap model
				,@PathVariable int id
				,@PathVariable int  budgetid
				) throws IOException {
		
			 MainServices.addNotificationAttributes(model);

			 company_annual_plan annual_plan = company_annual_planRepository.findById(budgetid);
			 
			 
			 
	
		
			    if(annual_plan.getId() > 0) {
	            	  
	            	  List<company_annual_plan>  annual_plans = null;
	            	  
	            	  if(annual_plan.getAnnualType()  == AnnualPlanType.JOB_COUNT.name()) {
	            		  
	            		  annual_plans = company_annual_planRepository.findAllByTempIdAndTotalJobsIsNotNull(annual_plan.getEdataId());
	            		
	            		 if(annual_plan.getTotalJobs() > 0 ) {
	            			 
	            			 if (annual_plans == null) {
	            				 annual_plans = new ArrayList<>();
	            				}
	            			 annual_plans.add(0,annual_plan);
	            		 }  
	            	  }
	            	  
	            	  else if(annual_plan.getAnnualType()  == AnnualPlanType.PERSON_COUNT.name()) {
	          
	            		  annual_plans = company_annual_planRepository.findAllByTempIdAndMaxPersonnelIsNotNull(annual_plan.getEdataId());
	              		
	             		 if(annual_plan.getMaxPersonnel() > 0) {
	             			 
	             			 if (annual_plans == null) {
	             				annual_plans = new ArrayList<>();
	             				}
	             			annual_plans.add(0,annual_plan);
	             		 }  
	             		 
	             	
	                  }
	            	  else if(annual_plan.getAnnualType()  == AnnualPlanType.PRODUCTION_OUTPUT.name()) {
	                      
	            		  annual_plans = company_annual_planRepository.findAllByTempIdAndProductionTargetUnitsIsNotNull(annual_plan.getEdataId());
	              		
	             		 if(annual_plan.getProductionTargetUnits() != null) {
	             			 
	             			 if (annual_plans == null) {
	             				annual_plans = new ArrayList<>();
	             				}
	             			annual_plans.add(0,annual_plan);
	             		 }  
	                  }
	            	  else  if(annual_plan.getAnnualType()  == AnnualPlanType.EXPENSE.name()) {
	                      
	            		  annual_plans = company_annual_planRepository.findAllByTempIdAndAnnualBudgetIsNotNull(annual_plan.getEdataId());
	              		
	             		 if(annual_plan.getAnnualBudget() != null) {
	             			 
	             			 if (annual_plans == null) {
	             				annual_plans = new ArrayList<>();
	             				}
	             			   annual_plans.add(0,annual_plan);
	             		 }  
	             	else  if(annual_plan.getAnnualType()  == AnnualPlanType.MACHINE_EFFICIENCY.name()) {
		                      
	             		annual_plans = company_annual_planRepository.findAllByTempIdAndMachineryIdIsNotNull(annual_plan.getEdataId());
		              		
		             		 if(annual_plan.getMachineryId() != null) {
		             			 
		             			 if (annual_plans == null) {
		             				annual_plans = new ArrayList<>();
		             				}
		             			annual_plans.add(0,annual_plan);
		             		 }  
		            }
	              	else  if(annual_plan.getAnnualType()  == AnnualPlanType.GENERAL.name()) {
	                      
	              		annual_plans = company_annual_planRepository.findAllByTempIdAndGeneralTargetIsNotNull(annual_plan.getEdataId());
	              		
	             		 if(annual_plan.getGeneralTarget() != null) {
	             			 
	             			 if (annual_plans == null) {
	             				annual_plans = new ArrayList<>();
	             				}
	             			annual_plans.add(0,annual_plan);
	             		 } 
	              	}
		
				 
			 
			    }
	            	  model.addAttribute("annual_plans",annual_plans);
			 
			    }
			
	         model.addAttribute("annual_plan",annual_plan);
			 model.addAttribute("id", id);
			 return "/Subdata/Detail_budget";
			    	 
		
		}

		@RequestMapping(value="/asset_finance-workflow/{id}",method = RequestMethod.GET)
		public String asset_finance(
				ModelMap model
				,@PathVariable int id
				) throws IOException {
		
			 MainServices.addNotificationAttributes(model);
			 
			 List<user> userss = UserDAO.getall(0,0);
			  model.addAttribute("userall", userss);
			 
			  model.addAttribute("id",id);
			
			  
			  List<assets> asset =  assetsRepository.findTop500ByOrderByIdDesc();
			  model.addAttribute("asset",asset);
			  
			  List<String> asset_type = asset.stream()
					    .map(row-> row.getAssetType())     // ดึงเฉพาะ .type
					    .filter(Objects::nonNull)   // กรอง null ถ้ามี
					    .distinct()                 // เอาเฉพาะค่าที่ไม่ซ้ำ
					    .collect(Collectors.toList());
			  
			  model.addAttribute("asset_type",asset_type);
			  
			  
			return "/Subdata/asset_finance";
		}
		
		@RequestMapping(value="/detail_asset-workflow/{assetid}/{id}",method = RequestMethod.GET)
		public String detail_asset(
				ModelMap model
				,@PathVariable int id
				,@PathVariable int  assetid
				) throws IOException {
		
			 MainServices.addNotificationAttributes(model);
			 assets asset = assetsRepository.findById(assetid);

			 
			if(asset != null) {
				 LocalDateTime localDate = LocalDateTime.now();//For reference
			   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			   	 String date = localDate.format(formatter);
				
			 String type ="assets";
			 int cm_idrefer  = asset.getId();
			 
			  
		      List<cm> listcms1 = CmDAO.getall(cm_idrefer,type);
		      model.addAttribute("listcms1",listcms1);
			  
			  List<cm> listcms2 = CmDAO.getall(cm_idrefer,type,date);
		      model.addAttribute("listcms2",listcms2);
			  
		      
		      
		      
			  model.addAttribute("type",type); 
			  model.addAttribute("cm_idrefer",cm_idrefer);  


			  Asset_log assets_log = EdataDAO.getAssets_log(cm_idrefer);
			
			   if(assets_log != null) {
				    List<file> files = FileDAO.getallEdata(assets_log.getEdata_id());
			        model.addAttribute("files",files); 
				   
			   }

			
			}
			 
			 
	         model.addAttribute("asset",asset);
			 model.addAttribute("id", id);
			 return "/Subdata/Detail_asset";
			    	 
		
		}
		
		
		
		
		
		
		
	
}//class end
