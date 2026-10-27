package com.smepro.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.smepro.service.WorkSystem;
import com.smepro.dao.actionplanDAO;
import com.smepro.dao.cmDAO;
import com.smepro.dao.commentDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.dataeformDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.e_reportDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.formDAO;
import com.smepro.dao.group_authoritiesDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.jobDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.memberDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.reportDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.typeDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dao.Identifiable;
import com.smepro.dao.IdocDAO;
import com.smepro.dao.LoadApp_Component;
import com.smepro.dao.Register_Component;
import com.smepro.dto.GamificationWidgetDTO;
import com.smepro.dto.MultipleModelsDTO;
import com.smepro.dto.UploadResponDTO;
import com.smepro.dto.driverDTO;
import com.smepro.dto.fileDTO;
import com.smepro.dto.folderDTO;
import com.smepro.dto.savefileDTO;
import com.smepro.entity.HelpDeskTicket;
import com.smepro.entity.Policy;
import com.smepro.entity.PolicyMember;
import com.smepro.entity.SpikeDogActionplan;
import com.smepro.entity.UserSetting;
import com.smepro.entity.account_ledger;
import com.smepro.enums.Dataset;
import com.smepro.enums.PolicyType;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.enums.eformcolumntype;
import com.smepro.enums.notif;
import com.smepro.models.*;
import com.smepro.repository.Account_ledgerRepository;
import com.smepro.repository.BiReportSettingRepository;
import com.smepro.repository.CampaignPromotionRepository;
import com.smepro.repository.CustomizeTableHeadersRepository;
import com.smepro.repository.HelpDeskRepository;
import com.smepro.repository.PolicyMemberRepository;
import com.smepro.repository.PolicyRepository;
import com.smepro.repository.SpikeDogActionplanRepository;
import com.smepro.repository.UserSettingRepository;
import com.smepro.service.AppInboxService;
import com.smepro.service.BuildInService;
import com.smepro.service.FlowServices;
import com.smepro.service.GamificationService;
import com.smepro.service.GoalService;
import com.smepro.service.LogService;
import com.smepro.service.MainService;
import com.smepro.service.PathManager;
import com.smepro.service.PlatformService;
import com.smepro.service.PolicyService;
import com.smepro.service.ProgressService;
import com.smepro.service.RepositoryService;
import com.smepro.service.RevisionService;
import com.smepro.service.ScheduleService;
import com.smepro.service.TaskService;
import com.smepro.service.UploadPlanManager;
import com.smepro.service.dateservice;
import com.smepro.service.eformservice;
import com.smepro.service.filemanagerservice;
import com.smepro.service.fileservice;
import com.smepro.service.memberservice;
import com.smepro.service.msofficeservice;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;
import com.smepro.entity.BiReportSetting;
import com.smepro.entity.CampaignPromotion;
import com.smepro.entity.CustomizeTableHeaders;





@Controller
@SessionAttributes({"Member_name","name","user_img","Member_id","USERNAME"})
public class filemanagercontroller {
	
   	@Value("${storage.path}")
   	private String storagePath; // ตัวนี้จะเปลี่ยนตามเครื่องอัตโนมัติ (C: หรือ D: ตาม properties)

	private userservice Userservice;
	private fileservice Fileservice;
	private eformfieldDAO EformfieldDAO;
	
	private edataDAO EdataDAO;
	private cmDAO CmDAO; 
	private taskDAO TaskDAO; 
	private actionplanDAO ActionplanDAO;
	private holidayDAO HolidayDAO;
	private notificationDAO NotificationDAO;
	private reportDAO ReportDAO;
	private formDAO FormDAO;
	private eformservice eformservices;
	private filemanagerservice Filemanagerservice;
	private  FlowServices flowService;
	private UploadPlanManager UploadPlanManagers;
	private  PathManager pathManager;
	private  IdocDAO IdocDAOs;
	private  otherservice Otherservice;
	private  ScheduleService scheduleService;
	
	
	private Account_ledgerRepository account_ledgerRepository;
	private PolicyRepository policyRepository;
	
	private PolicyMemberRepository policyMemberRepository;
	
	private  PlatformService platformService;
	private  HelpDeskRepository helpDeskRepository;
	private UserSettingRepository userSettingRepository;
	private GamificationService gamificationService;
    private BiReportSettingRepository BiReportRepository;
	
	
	private  MainService MainServices;
	private  jobDAO JobDAO;
	private RepositoryService repositoryService;
	private  GoalService goalService;
	private  PolicyService policyService;
	private  ProgressService progressService;
	
	
	private  TaskService taskService;
	private  AppInboxService appInboxService;
	private  LogService logService;
	private  IdocDAO idocDAO;
	
	private  RevisionService revisionService;
	private Register_Component register_Component;
	private BuildInService buildInService;
	private CustomizeTableHeadersRepository customizeTableHeadersRepository;
	private SpikeDogActionplanRepository actionplanRepository;
	
	private CampaignPromotionRepository campaignPromotionRepository;
	
 
    @Autowired
    public void setMyRepository(
    		userservice Userservice,
    		fileservice Fileservice,
    		eformfieldDAO EformfieldDAO,
    		edataDAO EdataDAO,
    		cmDAO CmDAO,
    		taskDAO TaskDAO,
    		actionplanDAO ActionplanDAO,
    		holidayDAO HolidayDAO,
    		notificationDAO NotificationDAO,
    		reportDAO ReportDAO,
    		formDAO FormDAO
    		,eformservice eformservices
    		,filemanagerservice Filemanagerservice
    		,FlowServices flowService
    		,UploadPlanManager UploadPlanManagers
    		,PathManager pathManager
    		,MainService MainServices
    		,IdocDAO IdocDAOs
    		,jobDAO JobDAO
      		,otherservice Otherservice
      		,Account_ledgerRepository account_ledgerRepository
      		,PolicyRepository policyRepository
      		,RepositoryService repositoryService
      		,PolicyMemberRepository policyMemberRepository
      		,GoalService goalService
    		,PolicyService policyService
    		,ProgressService progressService
    		,TaskService taskService
    		,AppInboxService appInboxService
    		,PlatformService platformService
    		,LogService logService
    		,IdocDAO idocDAO
    		,ScheduleService scheduleService
    		,Register_Component register_Component
    		,RevisionService revisionService
    		,HelpDeskRepository helpDeskRepository
    		,UserSettingRepository userSettingRepository
    		,BuildInService buildInService
    		,CustomizeTableHeadersRepository customizeTableHeadersRepository
    		,GamificationService gamificationService
    		, BiReportSettingRepository BiReportRepository
    		, SpikeDogActionplanRepository actionplanRepository
    		, CampaignPromotionRepository campaignPromotionRepository
    		) {
		this.campaignPromotionRepository = campaignPromotionRepository;
    	 this.revisionService = revisionService;
        this.Userservice = Userservice;
        this.Fileservice = Fileservice;
        this.EformfieldDAO = EformfieldDAO;
        this.EdataDAO = EdataDAO;
        this.CmDAO = CmDAO;
        this.TaskDAO = TaskDAO;
        this.ActionplanDAO =ActionplanDAO; 
        this.HolidayDAO =HolidayDAO; 
        this.NotificationDAO =NotificationDAO; 
        this.ReportDAO =ReportDAO; 
        this.FormDAO =FormDAO; 
        this.eformservices =eformservices; 
        this.Filemanagerservice =Filemanagerservice; 
        this.flowService =flowService; 
        this.UploadPlanManagers =UploadPlanManagers; 
        this.pathManager =pathManager; 
        this.MainServices =MainServices; 
        this.IdocDAOs =IdocDAOs; 
        this.JobDAO =JobDAO; 
        this.Otherservice =Otherservice; 
        this.policyRepository =policyRepository; 
        this.repositoryService =repositoryService; 
        this.account_ledgerRepository =account_ledgerRepository; 
        this.policyMemberRepository =policyMemberRepository; 
        this.goalService =goalService; 
        this.policyService =policyService; 
        this.progressService =progressService; 
        this.taskService =taskService; 
        this.appInboxService =appInboxService; 
        this.platformService =platformService; 
        this.logService =logService; 
        this.idocDAO =idocDAO; 
        this.scheduleService =scheduleService; 
        this.register_Component =register_Component; 
        this.helpDeskRepository =helpDeskRepository; 
        this.userSettingRepository =userSettingRepository; 
        this.buildInService  = buildInService; 
        this.customizeTableHeadersRepository  = customizeTableHeadersRepository; 
        this.gamificationService  = gamificationService; 
        this.BiReportRepository  = BiReportRepository; 
        this.actionplanRepository  = actionplanRepository; 
    }
	
 
  
    
    
    
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	drivesDAO DrivesDAO;	
	
	@Autowired
	folderDAO FolderDAO;	

	@Autowired
	typeDAO TypeDAO;	
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	groupsDAO GroupsDAO;

	@Autowired
	group_authoritiesDAO Group_authoritiesDAO;
	
	@Autowired
	upload_pnDAO Upload_pnDAO;
	
	@Autowired
	darDAO DarDAO;
	
	@Autowired
	logDAO LogDAO;
	
	@Autowired
	upload_pnDAO Upload_pnDAODAO;
	
	@Autowired
	group_membersDAO Group_membersDAO;
	

	
	@Autowired
	report_pnDAO Report_pnDAO;	
	
	@Autowired
	departmentDAO DepartmentDAO;	
	
	@Autowired
	dateservice Dateservice;

	@Autowired
	msofficeservice msofficeservices;
	
	@Autowired
	commentDAO CommentDAO;
	
	@Autowired
	conditionDAO ConditionDAO;
	
	@Autowired
	otherservice otherservices;
	
	@Autowired
	datasetDAO DatasetDAO;
	
	@Autowired
	dataeformDAO DataeformDAO;
	
	@Autowired
	e_reportDAO E_reportDAO;
	
	@Autowired
	workflowDAO WorkflowDAO;
	
	@Autowired
	dataset_dataDAO Dataset_dataDAO;
	



	


	
	
	@RequestMapping(value="/filemanager")
	public String showprofile(ModelMap model,HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
		 ArrayList lists = new ArrayList();
		for (SimpleGrantedAuthority permission : list) {
		     lists.add(permission.getAuthority());
		}
		
		
		List<drives> drivess = DrivesDAO.getall();    
		

		MainServices.addNotificationAttributes(model);
		 
		
		 driverDTO driverDTOs = new driverDTO();
		 folderDTO folderDTOs = new folderDTO();
		 List<purchase> purchaseDTO = new ArrayList<purchase>();
		 
		 session.setAttribute("driverDTOs", driverDTOs);
		 session.setAttribute("folderDTOs", folderDTOs);
		 
		 
		 
		 session.setAttribute("purchaseDTO", purchaseDTO);
			 
			 


		 File disk = new File(System.getProperty("user.dir"));
	  	 long freeSpace = disk.getFreeSpace();
	    
			 
	 	model.addAttribute("freeSpace",freeSpace);	 
	  	model.addAttribute("show","drives");
		model.addAttribute("drives",drivess);   	
	    model.addAttribute("show","drives");
	    model.addAttribute("permission",lists);

	    
	    
	    BigDecimal var1 = new BigDecimal("128.45"); // ใส่ค่าเป็น String เพื่อป้องกันความคลาดเคลื่อน
        BigDecimal var2 = new BigDecimal("1.00");
        BigDecimal total = var1.subtract(var2);
		System.out.println("Double -->"+total);
		return "filemanager";
	}

	

	
	
	@RequestMapping(value="/root-filemanager")
	public String root(ModelMap model) {
		List<drives> drivess = DrivesDAO.getall();
 		List<folder> folders = FolderDAO.getalls2();	
 		List<groups> Groups =   GroupsDAO.getall(0);
 		
 		model.addAttribute("Groups",Groups);
	    model.addAttribute("drivess",drivess);
	    model.addAttribute("folders",folders);
	    
		return "/root2";
	}
	
	@RequestMapping(value="/alltask-filemanager/{duepn}",method = RequestMethod.GET)
	public String alltask(ModelMap model, @PathVariable int duepn) {
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		List<task> Task1 = TaskDAO.getallstatus(duepn,1,users.getUser_name(),users.getUser_id());
  		model.addAttribute("task1",Task1);

  		List<task> Task2 = TaskDAO.getallstatus(duepn,2,users.getUser_name(),users.getUser_id());
  		model.addAttribute("task2",Task2);
	
		   return "/ajax/alltask";
	}
	
	

	@RequestMapping(value="/addtask-filemanager",method = RequestMethod.POST)
	public String addtask(ModelMap model
			,task tasks
			,@RequestParam int group
			, @RequestParam int id
			,@RequestParam int step) {
		
		
		
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
	   	user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
	   	
	   	
		tasks.setTask_idadd(users.getUser_id());
		tasks.setTask_date(date);
		tasks.setTask_status(1);
		tasks.setTask_idrefer(id);
		
		TaskDAO.save(tasks);
	    
	    return "redirect:/projectmanager-filemanager?id="+id+"&&step=0&&group=1";
	   
	}
	
	
	@RequestMapping(value="/saveresources-filemanager",method = RequestMethod.POST)
	public String saveresources(ModelMap model,
			Use_resources use_resources, @RequestParam  int upload_pn_id
			) {
		
		
			Upload_pnDAO.saveUse_resources(use_resources);
			List<Resource> resource = Upload_pnDAO.getAllResourceUse(use_resources.getTask_id());
		    model.addAttribute("resource",resource); 
		    model.addAttribute("upload_pn_id",upload_pn_id); 
			
	    return "ajax/setresources";
	   
	}
	
	@RequestMapping(value="/fileapp-filemanager/{item_id}/{process_id}",method = RequestMethod.GET)
	public String fileapp(ModelMap model,@PathVariable  int item_id,@PathVariable  int process_id) {

    		  Platform platform = new Platform();
    			platform.setApp_processes_id(process_id);
    			platform.setApp_item_id(item_id);
    			LoadApp   LoadApp =   new LoadApp();
    			
    			platformService.initiate(LoadApp).startAppIndex(platform).fileAll().load();
    			Map<String, Object> data = platformService.getModelData();
    			model.addAllAttributes(data);
    			model.addAttribute("item_id",item_id); 
    			
    	    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	model.addAttribute("USERNAME",usersed.getUsername()); 
    			
	    	return "app/upload";
	}
	
	
	
	
	
	
	@RequestMapping(value="/deleteresources-filemanager",method = RequestMethod.POST)
	public String deleteresources(ModelMap model,
			Use_resources use_resources, @RequestParam  int upload_pn_id
			) {
		
		
			Upload_pnDAO.deleteUse_resources(use_resources);
			List<Resource> resource = Upload_pnDAO.getAllResourceUse(use_resources.getTask_id());
		    model.addAttribute("resource",resource); 
		    model.addAttribute("upload_pn_id",upload_pn_id); 
			
	    return "ajax/setresources";
	   
	}
	
	@RequestMapping(value="/viewkeyactivitys-filemanager/{id}",method = RequestMethod.GET)
	public String viewkeyactivitys(ModelMap model, @PathVariable  int id
			) {
			
		  
	    Keyactivity key_activity = Upload_pnDAO.getKeyactivity(id);
	    model.addAttribute("key_activity",key_activity); 
	    
	    
	    List<task> tasks = TaskDAO.getallKeyactivityId(key_activity.getId());

	  
	    model.addAttribute("tasks",tasks); 
	    
		
	    return "task/viewkeyactivitys";
	   
	}
	
	@RequestMapping(value="/viewbudget-filemanager/{id}",method = RequestMethod.GET)
	public String viewbudget(ModelMap model, @PathVariable  int id
			) {
			
	    MasterBudget budget = Upload_pnDAO.getMasterBudget(id);
	    model.addAttribute("budget",budget); 
	    
	    List<task> tasks = TaskDAO.getallMasterBudgetId(budget.getId());
	    model.addAttribute("tasks",tasks); 
	    
	    return "task/viewbudget";
	   
	}

	
	
	
	@RequestMapping(value="/viewresource-filemanager/{id}/{upload_pn_id}",method = RequestMethod.GET)
	public String viewresource(ModelMap model
			, @PathVariable int id, @PathVariable int upload_pn_id) {
	
		
		Use_resources resource_use = Upload_pnDAO.getResourceUse(id);
	      model.addAttribute("resource_use",resource_use);
		  
		List<Resource> resource = Upload_pnDAO.getAllResource(upload_pn_id);
		model.addAttribute("resource",resource); 
		 
		  
	    return "ajax/formresources";
	   
	}
	
	
	
	@RequestMapping(value="/setresources-filemanager",method = RequestMethod.POST)
	public String setresources(ModelMap model,
			Use_resources use_resources , @RequestParam  int upload_pn_id) {
		
		
			Upload_pnDAO.saveUse_resources(use_resources);
			List<Resource> resource = Upload_pnDAO.getAllResourceUse(use_resources.getTask_id());
		    model.addAttribute("resource",resource); 
		    model.addAttribute("upload_pn_id",upload_pn_id); 
	    return "ajax/setresources";
	   
	}
	@RequestMapping(value="/showresources-filemanager",method = RequestMethod.GET)
	public String showresources(ModelMap model, @RequestParam int id) {
		
		Resource resource = Upload_pnDAO.getUse_resources(id);
	    model.addAttribute("resource",resource); 
	    return "ajax/setresources";
	   
	}

	
	
	
	
	@RequestMapping(value="/app_submitted-filemanager",method = RequestMethod.POST)
	public String app_submitted(actionplan actionplan, RedirectAttributes redirectAttributes) {
		
		    ActionplanDAO.setSubmission( actionplan.getActionplan_id(),actionplan.getSubmission());
		   return "redirect:/task-filemanager/"+actionplan.getActionplan_idrfer();
	}
	
	
	
	
	
	@RequestMapping(value="/checktask-filemanager/{id}",method = RequestMethod.GET)
	public String checktask(ModelMap model, @PathVariable int id) {
		   

		actionplan actionplan = ActionplanDAO.getByid(id);
		task tasked =  TaskDAO.getbyid(actionplan.getActionplan_idrfer());
	     model.addAttribute("tasked",tasked);
		 
		 
		if(actionplan != null){
			
			List<file> files2 = FileDAO.getallactionplan(actionplan.getActionplan_id());
			actionplan.setFile(files2);
			
			 if( Dateservice.Criteria_Upload(actionplan.getActionplan_dateadd(),tasked.getTask_dateend() ) == true) {
				 model.addAttribute("timeremaining",1);
			 }else {
				 model.addAttribute("timeremaining",0);
				 
			 }
			 List<Risk> risks = Upload_pnDAO.getAllRisk(tasked.getTask_idrefer());
			 model.addAttribute("risks",risks);
			 
			 
				if(actionplan.getActionplan_problem() != null) {
					List<Integer> problem = Arrays.stream(actionplan.getActionplan_problem().split(","))
					        .map(Integer::parseInt)
					        .collect(Collectors.toList());
					model.addAttribute("problem",problem);
					
					String actionplan_problem = risks.stream().filter(row-> problem.contains(row.getId())).map(row->row.getTitle()).collect(Collectors.joining(","));
					
					if(problem.contains(0)){
						model.addAttribute("problem_scope_of",1);
					}
					
					actionplan.setActionplan_problem(actionplan_problem);
				}
			 
			 
		}
		
		model.addAttribute("actionplan",actionplan);
		
		
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
		  List<cm> listcms1 = CmDAO.getall(id,"actionplan");
	      model.addAttribute("listcms1",listcms1);
		  
		  List<cm> listcms2 = CmDAO.getall(id,"actionplan",date);
	      model.addAttribute("listcms2",listcms2);
		
	      List<Problem> problem_list = TaskDAO.getallProblemByActionplanId(actionplan.getActionplan_id());
	      model.addAttribute("problem_list",problem_list);

	      
		return "task/viewtask";
	}
	
	
	
	@PostMapping("/addproblems-filemanager")
	public String addproblems(ModelMap model,Problem Problems) {
		
		task tasked =  TaskDAO.getbyid(Problems.getTask_id());
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		
		
		Problems.setProject_id(tasked.getTask_idrefer());
		Problems.setOwner_id(users.getUser_id());
		Problems.setOwner(users.getUser_name());
		
		TaskDAO.saveProblems(Problems);
		
		List<Problem> problem_list = TaskDAO.getallProblemByActionplanId(Problems.getActionplan_id());
		model.addAttribute("problem_list",problem_list);
		
		
		 List<Risk> risks = Upload_pnDAO.getAllRisk(tasked.getTask_idrefer());
		 model.addAttribute("risks",risks);
		
		return "task/problems_list";
		
	}
	
	
	@PostMapping("/deleteproblems-filemanager")
	public String deleteproblems(ModelMap model,Problem Problems) {
		

		TaskDAO.deleteProblems(Problems);
		
		 List<Problem> problem_list = TaskDAO.getallProblemByActionplanId(Problems.getActionplan_id());
		 model.addAttribute("problem_list",problem_list);
		
		
		 List<Risk> risks = Upload_pnDAO.getAllRisk(Problems.getTask_id());
		 model.addAttribute("risks",risks);
		
		return "task/problems_list";
		
	}
	
	
	
	
	@GetMapping("/editproblems-filemanager/{id}")
	public String editproblems(ModelMap model,@PathVariable int id) {

		
		Problem problem = TaskDAO.getProblemById(id);
		model.addAttribute("problem",problem);
		System.out.println(" problem "+problem.getTask_id());
		
		task tasked =  TaskDAO.getbyid(problem.getTask_id());
		

		
		List<Risk> risks = Upload_pnDAO.getAllRisk(tasked.getTask_idrefer());
		 
		 model.addAttribute("risks",risks);
		
		
		return "task/formproblems_list";
		
	}
	@GetMapping("/addproblems-filemanager/{id}")
	public String addproblems(ModelMap model,@PathVariable int id) {

		task tasked =  TaskDAO.getbyid(id);
		 List<Risk> risks = Upload_pnDAO.getAllRisk(tasked.getTask_idrefer());
		 model.addAttribute("risks",risks);
		
		return "task/formproblems_list";
	}
	
	
	
	
	@RequestMapping(value="/commenttask-filemanager/{id}",method = RequestMethod.GET)
	public String commenttask(ModelMap model, @PathVariable int id) {
		   

		actionplan actionplan = ActionplanDAO.getByid(id);
		task tasked =  TaskDAO.getbyid(actionplan.getActionplan_idrfer());
		
		
		if(actionplan != null){
			
			List<file> files2 = FileDAO.getallactionplan(actionplan.getActionplan_id());
			actionplan.setFile(files2);
			
			 if( Dateservice.Criteria_Upload(actionplan.getActionplan_dateadd(),tasked.getTask_dateend() ) == true) {
				 model.addAttribute("timeremaining",1);
			 }else {
				 model.addAttribute("timeremaining",0);
				 
			 }
			 List<Risk> risks = Upload_pnDAO.getAllRisk(tasked.getTask_idrefer());
				if(actionplan.getActionplan_problem() != null) {
					List<Integer> problem = Arrays.stream(actionplan.getActionplan_problem().split(","))
					        .map(Integer::parseInt)
					        .collect(Collectors.toList());
					model.addAttribute("problem",problem);
					
					String actionplan_problem = risks.stream().filter(row-> problem.contains(row.getId())).map(row->row.getTitle()).collect(Collectors.joining(","));
					
					if(problem.contains(0)){
						model.addAttribute("problem_scope_of",1);
					}
					
					actionplan.setActionplan_problem(actionplan_problem);
				}
			 
			 
		}
		
		model.addAttribute("actionplan",actionplan);
		
		
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
		  List<cm> listcms1 = CmDAO.getall(id,"actionplan");
	      model.addAttribute("listcms1",listcms1);
		  
		  List<cm> listcms2 = CmDAO.getall(id,"actionplan",date);
	      model.addAttribute("listcms2",listcms2);
		
	      

	      
		return "task/commenttask";
	}
	
	
	
	@RequestMapping(value="/problemstask-filemanager/{id}",method = RequestMethod.GET)
	public String problemstask(ModelMap model, @PathVariable int id) {
		   

		actionplan actionplan = ActionplanDAO.getByid(id);
		task tasked =  TaskDAO.getbyid(actionplan.getActionplan_idrfer());
		
		
		if(actionplan != null){
			 List<Problem> problem_list = TaskDAO.getallProblemByActionplanId(actionplan.getActionplan_id());
			 model.addAttribute("problem_list",problem_list);
			 
		}
		
		model.addAttribute("actionplan",actionplan);
		model.addAttribute("tasked",tasked);
	      

	      
		return "task/problemtask";
	}
	
	
	
	
	@RequestMapping(value="/task-filemanager/{id}",method = RequestMethod.GET)
	public String addtask(ModelMap model, @PathVariable int id) {
		
		
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
	   	 
	
	   	 
		
		task tasked =  TaskDAO.getbyid(id);
		model.addAttribute("tasks",tasked);
		
	     List<Progress> progress = 	progressService.initiate().forTask(tasked).get();
	     model.addAttribute("progress",progress);
		
		
		
		
		 int timeremaining = 1;
		 if( Dateservice.Criteria_Upload(date,tasked.getTask_dateend() ) == false) {
			 timeremaining = 0;
		 }
		 
		 
		 
		 model.addAttribute("timeremaining",timeremaining);
		
		if(tasked.getTask_idadd() > 0 ) {
			user useradd = UserDAO.getbyId2(tasked.getTask_idadd());
			model.addAttribute("useradd",useradd);
		}
		
		if(tasked.getTask_note() != null && !tasked.getTask_note().isEmpty()) {
			model.addAttribute("note", tasked.getTask_note().replace("\n", "<br>"));
		}
		
		
		
		 int late =  timeremaining;
		 System.out.println(" late "+late);
		 
		List<actionplan> actionplans = ActionplanDAO.getAll(id);
		actionplans.stream().forEach(row->{
			
			 List<Problem> Problems = TaskDAO.getallProblemByActionplanId(row.getActionplan_id());
			 long count_problems = Problems.stream().filter(rows -> rows.getStatus() == 1).count();
			 row.setCount_problems(count_problems);
			 
			 if(row.getActionplan_dateadd() == null && late == 0) {
				 row.setActionplan_result("late");
			 }
			 System.out.println(" Actionplan_dateadd "+row.getActionplan_dateadd());
			 
			 if( Dateservice.Criteria_Upload(row.getActionplan_dateadd(),tasked.getTask_dateend() ) == false) {
				 row.setActionplan_result("late");
			 }
			

			List<file> files2 = FileDAO.getallactionplan(row.getActionplan_id());
			row.setFile(files2);
		});

		
		
		List<actionplan> allplan  = new ArrayList<actionplan>(); 
		if(tasked.getTask_iduser() != null) {
			List<String> iduser  = new ArrayList<String>(Arrays.asList(tasked.getTask_iduser().split(","))); 
			
			for(String name : iduser) {
				
				Optional<actionplan> dataplan =   actionplans.stream().filter(row->row.getActionplan_responsible().equals(name)).findFirst();
				
				if(dataplan.isPresent()) {
					actionplan plan = dataplan.get();
					allplan.add(plan);
				}else {
					actionplan plan = new actionplan();
					plan.setActionplan_responsible(name);
					
					 if(late == 0) {
						 plan.setActionplan_result("late");
					 }	
					allplan.add(plan);
				}
				
			}
			
			model.addAttribute("allplan",allplan);
		}
		
		
		
    	upload_pn upload_pns = Upload_pnDAO.getbyId(tasked.getTask_idrefer());
    	upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll((int)upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
    	List<user> user_all =  UserDAO.findAll();
    	
    	model.addAttribute("upload_pns",upload_pns);
		model.addAttribute("users",upload_pnsall);
		model.addAttribute("user_all",user_all);
		model.addAttribute("folder",upload_pns.getUpload_pn_idrefer());
	
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		model.addAttribute("Member_name",usersed.getUser_name());
		model.addAttribute("Member_id",usersed.getUser_id());
		
		
		
		folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	  
	    
	    String Pathfull;
	    String Pathfulls;
	    drives drivess;
	    int drives_id;

	    
	    if(mainfolders != null) {
	    drives_id= mainfolders.getFolder_idrefer();	
	    drivess  = DrivesDAO.getbyId(drives_id);
	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
	    }else {
	    	
    	drives_id = folders.getFolder_idrefer();
    	drivess  = DrivesDAO.getbyId(drives_id);
    	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
	    }
	    model.addAttribute("Pathfulls",Pathfulls);
		
		List<file> files = FileDAO.getalltask(tasked.getTask_id());
		model.addAttribute("files",files);
		
		
		   List<Outcome> outcomes = Upload_pnDAO.getAllOutcome(upload_pns.getUpload_pn_id());
		    model.addAttribute("outcomes",outcomes); 
		   
		    List<Keyactivity> keyactivitys = Upload_pnDAO.getAllKeyactivity(upload_pns.getUpload_pn_id());
		    model.addAttribute("keyactivitys",keyactivitys); 
		    
		    
		    List<Risk> risks = Upload_pnDAO.getAllRisk(upload_pns.getUpload_pn_id());
		    model.addAttribute("risks",risks); 
		    
		    List<MasterBudget> budgets = Upload_pnDAO.getAllBudget(upload_pns.getUpload_pn_id());
		    model.addAttribute("budgets",budgets); 
		    
		    List<Milestone> milestones = Upload_pnDAO.getAllMilestone(upload_pns.getUpload_pn_id());
		    model.addAttribute("milestones",milestones); 
		
		    List<Resource> resource = Upload_pnDAO.getAllResource(upload_pns.getUpload_pn_id());
		    model.addAttribute("resource",resource); 

		    List<Resource> resourceUse = Upload_pnDAO.getAllResourceUse(tasked.getTask_id());
		  
		    
		    BigDecimal Usetotal = resourceUse.stream()
		    	    .map(r -> BigDecimal.valueOf(r.getActual()).multiply(r.getactualCost()))
		    	    .reduce(BigDecimal.ZERO, BigDecimal::add);
		    
		    TaskDAO.setTotalCost(id,Usetotal);
		    model.addAttribute("resourceUse",resourceUse); 
		    
		    
		    

		actionplan actionplan = ActionplanDAO.getByIdreferAndUser_id(id,usersed.getUser_id());
		if(actionplan != null){
			
			
			 List<Problem> myproblem_list = TaskDAO.getallProblemByActionplanId(actionplan.getActionplan_id());
			 model.addAttribute("countmyproblems",myproblem_list.size());
			
			
	
			if(actionplan.getActionplan_problem() != null) {
				List<Integer> problem = Arrays.stream(actionplan.getActionplan_problem().split(","))
				        .map(Integer::parseInt)
				        .collect(Collectors.toList());
				model.addAttribute("problem",problem);
				
				String actionplan_problem = risks.stream().filter(row-> problem.contains(row.getId())).map(row->row.getTitle()).collect(Collectors.joining(","));
				
				if(problem.contains(0)){
					model.addAttribute("problem_scope_of",1);
				}
				
				actionplan.setActionplan_problem(actionplan_problem);
			}
			
			List<file> files2 = FileDAO.getallactionplan(actionplan.getActionplan_id());
			actionplan.setFile(files2);
			
		
	
			  List<cm> listcms1 = CmDAO.getall(actionplan.getActionplan_id(),"actionplan");
		      model.addAttribute("listcms1",listcms1);
			  
			  List<cm> listcms2 = CmDAO.getall(actionplan.getActionplan_id(),"actionplan",date);
		      model.addAttribute("listcms2",listcms2);
			 
		}
		model.addAttribute("actionplan",actionplan);
		
		int num = ActionplanDAO.numbyidrefer(id);
		TaskDAO.setalert(num,0,id);
		
		actionplan total = ActionplanDAO.Total(id);
		model.addAttribute("total",total);
		
		
	 
		
	   if(tasked.getOutcome() != null) { 
		   
		   System.out.println("Outcome "+tasked.getOutcome());
		   List<String> taskedoutcomes =   new ArrayList<String>(Arrays.asList( tasked.getOutcome().split(",")));
		   List<Outcome> outcomes_plan = outcomes.stream().filter(row-> taskedoutcomes.contains(String.valueOf(row.getId()))).collect(Collectors.toList());
		   model.addAttribute("outcomes_plan",outcomes_plan);  
	   }
	  
		   if(tasked.getBudget_source1() > 0) {  
			   List<MasterBudget> budget_plan1 = budgets.stream().filter(row-> tasked.getBudget_source1() == row.getId()).collect(Collectors.toList());
			   model.addAttribute("budget_plan1",budget_plan1);  
		   }
		
		   
		 
		   
		   
	    return "task";
	}
	
	
	
	
	

	@RequestMapping(value="/subactionplan-filemanager/{id}",method = RequestMethod.GET)
	public String subactionplan(ModelMap model, @PathVariable int id) {
		
		actionplan actionplan = ActionplanDAO.getByid(id);
		model.addAttribute("actionplan",actionplan);
		List<actionplan> actionplans = ActionplanDAO.getalls(id);
		model.addAttribute("actionplans",actionplans);
		
		task tasked =  TaskDAO.getbyid(actionplan.getActionplan_idrfer());
		model.addAttribute("tasks",tasked);
		
		upload_pn upload_pns = Upload_pnDAO.getbyId(tasked.getTask_idrefer());
    	upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
		model.addAttribute("users",upload_pnsall);
		model.addAttribute("folder",upload_pns.getUpload_pn_idrefer());
		
		
		   return "/ajax/actionplan";
	}
	
	
	
	
	@RequestMapping(value="/deltask-filemanager/{id}",method = RequestMethod.GET)
	public String deltask(ModelMap model, @PathVariable int id,HttpSession session) {
		task tasks =  TaskDAO.getbyid(id);
		TaskDAO.delete(id);
		model.addAttribute("tasks",tasks);
		
		int tasks_id = tasks.getTask_idrefer();
		

		String paths;
	   	File fileToDelete;
	   	boolean success;
	    ServletContext contexts = session.getServletContext(); 
	    
	    List<actionplan> plan =  ActionplanDAO.getall(id);
	    
	    for(actionplan plans : plan) { 
	    	
	   	List<file> files = FileDAO.getallactionplan(plans.getActionplan_id());
	     for(file filesed : files) {
				    	 
					     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
					     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
					     success = fileToDelete.delete();
					     if(success) {
					     	 FileDAO.delete(filesed.getFile_id());
					     }
					     
	     		}
	     
	 		ActionplanDAO.delete(plans.getActionplan_id());
	     
	 		
	 		 List<actionplan> plan2 =  ActionplanDAO.getalls(plans.getActionplan_id());
	 		 for(actionplan plans2 : plan2) { 
	 			 
	 			List<file> subfiles = FileDAO.getallsubactionplan(plans2.getActionplan_id());
			     for(file filesed2 : subfiles) {
			    	 
				     paths = contexts.getRealPath("/filesuploads/" + filesed2.getFile_path()); 
				     fileToDelete = new File(paths + File.separator + filesed2.getFile_name());
				     success = fileToDelete.delete();
				     if(success) {
				    	 FileDAO.delete(filesed2.getFile_id());
				     }
					
			     }
	 			 
	 		 }
	 		 
	 		 
	 		 
	 		
	 		
		}
		     
		     

	    return "redirect: /projectmanager-filemanager?id="+tasks_id+"&&step=0&&group=1";
		   
	}
	
	
	
	
	
	
	@RequestMapping(value="/delsubactionplan-filemanager/{idrfer}/{id}",method = RequestMethod.GET)
	public String delsubactionplan(
			ModelMap model, 
			@PathVariable int id, 
			@PathVariable int idrfer,
			HttpSession session) {

		
		
		
		
		String paths;
	   	File fileToDelete;
	   	boolean success;
	    ServletContext contexts = session.getServletContext(); 
	    
	    
	   	file files = FileDAO.getbyactionplans(id);
	 
	    	if(files != null) { 
			     paths = contexts.getRealPath("/filesuploads/" + files.getFile_path()); 
			     fileToDelete = new File(paths + File.separator + files.getFile_name());
			     success = fileToDelete.delete();
			     if(success) {
			    	 FileDAO.delete(files.getFile_id());
			     }
	    	}
	 
		ActionplanDAO.delete(id);
		
		int num = ActionplanDAO.numbyidrefer2(idrfer);
		ActionplanDAO.setcm(num,idrfer);
		
		
		return "redirect:/subactionplan-filemanager/"+idrfer;
	}
	
	

	@RequestMapping(value="/delactionplan-filemanager/{task_id}/{id}",method = RequestMethod.GET)
	public String delactionplan(
			ModelMap model, 
			@PathVariable int id, 
			@PathVariable int task_id,
			HttpSession session) {

		
		String paths;
	   	File fileToDelete;
	   	boolean success;
	    ServletContext contexts = session.getServletContext(); 
	    
		
	    
	    
	   	List<file> files = FileDAO.getallactionplan(id);
	     for(file filesed : files) {
	    	 
		     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
		     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
		     success = fileToDelete.delete();
		     
			 folder folders =   FolderDAO.getbytype(filesed.getFile_path());
			 Filemanagerservice.UpdateMemorySize(folders,filesed.getFile_size(),0);
		     
		     if(success) {
		     	 FileDAO.delete(filesed.getFile_id());
		     }
			
	     }

	     List<actionplan> plan = ActionplanDAO.getalls(id);
	     for(actionplan filesed : plan) {
	    	 
		     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
		     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
		     success = fileToDelete.delete();
		     if(success) {
		    	 FileDAO.delete(filesed.getFile_id());
		     }
			
	     }

		ActionplanDAO.delete(id);
		ActionplanDAO.deleteall(id); 
		int num = ActionplanDAO.numbyidrefer(task_id);
		TaskDAO.setcm(num,task_id);  
	     
	     
	     
		ActionplanDAO.delete(id);
		return "redirect:/task-filemanager/"+task_id;
	}
	
	
	@RequestMapping(value="/edittask-filemanager/{id}",method = RequestMethod.GET)
	public String edittask(ModelMap model, @PathVariable int id) {
		
		
		task tasks =  TaskDAO.getbyid(id);
		
		if(tasks.getOutcome() != null) {
	        List<String> tasksoutcome = new ArrayList<String>(Arrays.asList(tasks.getOutcome().split(",")));
	        model.addAttribute("tasksoutcome",tasksoutcome);
		}
		model.addAttribute("tasks",tasks);
		
	    List<Outcome> outcomes = Upload_pnDAO.getAllOutcome(tasks.getTask_idrefer());
	    model.addAttribute("outcomes",outcomes); 
	   
	    
	    List<Keyactivity> keyactivitys = Upload_pnDAO.getAllKeyactivity(tasks.getTask_idrefer());
	    model.addAttribute("keyactivitys",keyactivitys); 
	    
	    
	    List<Risk> risks = Upload_pnDAO.getAllRisk(tasks.getTask_idrefer());
	    model.addAttribute("risks",risks); 
	    
	    List<MasterBudget> budgets = Upload_pnDAO.getAllBudget(tasks.getTask_idrefer());
	    model.addAttribute("budgets",budgets); 
		
	    List<Milestone> milestones = Upload_pnDAO.getAllMilestone(tasks.getTask_idrefer());
	    model.addAttribute("milestones",milestones); 
	    
		
		   return "/ajax/task";
	}
	
	
	
	
	@RequestMapping(value="/edittask-filemanager",method = RequestMethod.POST)
	public String edittasks(ModelMap model,task tasks) {
		
		if(tasks.getBudget_source1() < 1) { tasks.setTask_budget1(0.0); }
		if(tasks.getBudget_source2() < 1) { tasks.setTask_budget2(0.0); }
		if(tasks.getBudget_source3() < 1) { tasks.setTask_budget3(0.0); }
		
		
		TaskDAO.update(tasks);
		return "redirect:/task-filemanager/"+tasks.getTask_id();
	}
	
	
	@RequestMapping(value="/increaseprogress-filemanager",method = RequestMethod.POST)
	public String increaseprogress(task tasks) {
	
		TaskDAO.increaseProgress(tasks);
		return "redirect:/task-filemanager/"+tasks.getTask_id();
	}
	
	
	
	
	@RequestMapping(value="/addteamtask-filemanager",method = RequestMethod.POST)
	public String addteamtask(ModelMap model,task tasks) {
		
		TaskDAO.setteam(tasks);
		 return "redirect:/savetask-filemanager/"+tasks.getTask_id();
	}
	
	
	@RequestMapping(value="/setsubmission-filemanager/{id}",method = RequestMethod.GET)
	public String setsubmission(ModelMap model,@PathVariable int id) {


		actionplan actionplans = ActionplanDAO.getByid(id);
		
		
		task tasked =  TaskDAO.getbyid(actionplans.getActionplan_idrfer());
		model.addAttribute("tasks",tasked);
		
		
		
		ActionplanDAO.setSubmission(id,actionplans.getActionplan_idrfer());
		
		List<file> files2 = FileDAO.getallactionplan(id);
		actionplans.setFile(files2);

		model.addAttribute("actionplan",actionplans);
		
		return "redirect:/task-filemanager/"+tasked.getTask_id();
	}

	@RequestMapping(value="/setpoinactionplan-filemanager/{id}/{poin}",method = RequestMethod.GET)
	public String setpoinactionplan(ModelMap model,@PathVariable int id,@PathVariable String poin) {
		
		ActionplanDAO.setPoin(poin,id);

		
		
		
		actionplan actionplans = ActionplanDAO.getByid(id);
		
		
		task tasked =  TaskDAO.getbyid(actionplans.getActionplan_idrfer());
		model.addAttribute("tasks",tasked);
		
		List<file> files2 = FileDAO.getallactionplan(id);
		actionplans.setFile(files2);

	model.addAttribute("actionplan",actionplans);
		
		return "/ajax/subtask2";
	}

	
	
	
	
	@RequestMapping(value="/endtask-filemanager/{task_id}/{status}",method = RequestMethod.GET)
	public String endtask(ModelMap model,@PathVariable int task_id,@PathVariable int status) {
		TaskDAO.setstatus(task_id, status);
		 return "redirect:/task-filemanager/"+task_id;
	}
	
	
	
	@RequestMapping(value="/delfileactionplan-filemanager/{actionplan_id}/{id}",method = RequestMethod.GET)
	public String delfileactionplan(
			ModelMap model,
			@PathVariable int actionplan_id,
			@PathVariable int id,
			HttpSession session) {
		
		
		
		String paths;
	   	File fileToDelete;
	   	boolean success;
	   	
	   	file filesed = FileDAO.getbyId(id);
	    ServletContext contexts = session.getServletContext(); 
	     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
	     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
	     success = fileToDelete.delete();
	     
	
	     if(success) {
	    	
	    	 folder folders =   FolderDAO.getbytype(filesed.getFile_path());
	    	 Filemanagerservice.UpdateMemorySize(folders,filesed.getFile_size(),0);
	    	 FileDAO.delete(id);
	     }
	     model.addAttribute("id",id);
	     return "ajax/del";

	}
	
	
	@RequestMapping(value="/delfiletask-filemanager/{task_id}/{id}",method = RequestMethod.GET)
	public String delfiletask(
			ModelMap model,
			@PathVariable int task_id,
			@PathVariable int id,
			HttpSession session) {
		
		
		
		String paths;
	   	File fileToDelete;
	   	boolean success;
	   	
	   	file filesed = FileDAO.getbyId(id);
	    ServletContext contexts = session.getServletContext(); 
	     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
	     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
	     success = fileToDelete.delete();
	     if(success) {
	    	 FileDAO.delete(id);
	     }
		

	     return "redirect:/savetask-filemanager/"+task_id;
	}
	
	
	@RequestMapping(value="/delfilereport-filemanager/{report_id}/{id}",method = RequestMethod.GET)
	public String delfilereport(
			ModelMap model,
			@PathVariable int report_id,
			@PathVariable int id,
			HttpSession session) {
		
				String paths;
			   	File fileToDelete;
			   	boolean success;
			   	
			   	file filesed = FileDAO.getbyId(id);
			    ServletContext contexts = session.getServletContext(); 
			     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
			     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
			     success = fileToDelete.delete();
			     if(success) {
			    	 FileDAO.delete(id);
			     }

	     return "redirect:/report-filemanager?id="+report_id;
	}
	
	

	
	@RequestMapping(value="/addactionplan-filemanager", method=RequestMethod.POST)
	public String addactionplan(ModelMap model
			,@RequestParam  (required=false,name="actionplan_expenses",defaultValue="0") String actionplan_expenses
			,@RequestParam  (required=false,name="actionplan_payment",defaultValue="0") String actionplan_payment
			
			,@RequestParam  (required=false,name="actionplan_problem") String actionplan_problem
			,@RequestParam  (required=false,name="actionplan_solutions") String actionplan_solutions
			,@RequestParam  (required=false,name="actionplan_result") String actionplan_result
			,@RequestParam String actionplan_comment
			,@RequestParam int task_id
			,@RequestParam int folder
			,@RequestParam CommonsMultipartFile[] files,  
	         HttpSession session
	         ,log logs) throws Exception{ 
		
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 String dateend = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		 DateTimeFormatter formatte3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	  	LocalDateTime localDate = LocalDateTime.now();//For reference
		String date = localDate.format(formatter);
		String datetime = localDate.format(formatte3);
		 String time = localDate.format(formatter2);
		
		
		actionplan actionplan = new actionplan();
		actionplan.setActionplan_comment(actionplan_comment);
		actionplan.setActionplan_dateadd(date);
		actionplan.setActionplan_idrfer(task_id);
		actionplan.setActionplan_responsible(users.getUser_name());
		actionplan.setActionplan_user_id(users.getUser_id());
		actionplan.setActionplan_expenses(actionplan_expenses);
		actionplan.setActionplan_payment(actionplan_payment);
		
		actionplan.setActionplan_problem(actionplan_problem);
		actionplan.setActionplan_solutions(actionplan_solutions);
		actionplan.setActionplan_result(actionplan_result);
		
		
		int actionplanid = ActionplanDAO.save(actionplan);
		int num = ActionplanDAO.numbyidrefer(task_id);
		task tasks = TaskDAO.getbyid(task_id);
		
		TaskDAO.setalert(num,1,task_id);
		
		
		long imageFileSize_total = 0; 
		for (CommonsMultipartFile file : files) {
			 imageFileSize_total = imageFileSize_total + file.getSize();
		   }
       
		folder folders = FolderDAO.getbyId(folder);
		boolean diskspace =  Filemanagerservice.CheckMemorySize(folders,imageFileSize_total);
		
		if(!diskspace) {
				notification  notifications = new notification();
	 			notifications.setNotification_iduser(users.getUser_name());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("⚠ คำเตือน: พื้นที่ดิสก์เหลือน้อย!ไม่เพียงพอ");
				notifications.setNotification_note("");
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_refer("0");
				notifications.setNotification_type("⚠ Warning: Low disk space! Less than remaining. (System Error)");
				NotificationDAO.save(notifications);  
		}
		
	    user userapp = UserDAO.getbyId(tasks.getTask_idadd(), 0);
		notification  notifications = new notification();
		notifications.setNotification_iduser(userapp.getUser_name());
		notifications.setNotification_iduser2(null);
		notifications.setNotification_alert("คุณมีกิจกรรมโครงการรอดำเนินการ");
		notifications.setNotification_note(" โดย "+users.getUser_name());
		notifications.setNotification_date(datetime);
		notifications.setNotification_read(0);
		notifications.setNotification_refer(String.valueOf(task_id));
		notifications.setNotification_type("You have pending project activities.");
		NotificationDAO.save(notifications);   
		
		try {
						if (files != null && files.length > 0 && diskspace) {
						for (CommonsMultipartFile file : files) {
						if(file != null && file.getSize() > 0 ) {


                		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
      				   	String cleanPath = storagePath.replace("file:///", ""); 

      				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
      				   	String path = cleanPath +  folders.getFolder_type(); 
					    
					    
					    String filenameoriginal = file.getOriginalFilename();  
					    String filename = Fileservice.assertEquals(file.getContentType());
					    long imageFileSize = file.getSize();
					    String Size = Fileservice.printFileSize(imageFileSize);
					    
					   // System.out.print(imageFileSize);
					    if(filename == null) {
						 	   model.addAttribute("error","error 08"); 
					 	        return "error";
						    }  
					    
					    String Pathfull = null;
					    String Pathfull2 = null;
					    
				     if(filename != null) {
				    	 
				   
				  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
				  	    int drives_id;
				  	    drives_id = folders.getFolder_idrefer();
				  	    if(mainfolders != null) {
				  	    drives_id= mainfolders.getFolder_idrefer();	
				  	    }
				  	    drives drivess  = DrivesDAO.getbyId(drives_id);
				            
				  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
				  	    if(imageFileSize > totals) {
				  	    	
				  	            model.addAttribute("error","error 02"); 
				  	            return "error";
				  	    }
				  	    
				  	    file sum = FileDAO.getsum(folders.getFolder_type());
					    long imageFileSize2 = sum.getFile_size() +imageFileSize;
					    String Size2 = Fileservice.printFileSize(imageFileSize2);
					    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
					    
					  
					    
					    if(mainfolders != null) {
					    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
				    	    long imageFileSize3 = sum2.getFolder_use();
				    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
				    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
				    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
				    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
					    }else {
					    	
				        	drives_id = folders.getFolder_idrefer();
				        	drivess  = DrivesDAO.getbyId(drives_id);
				        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
				        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
				    	    }
				    	          	    
					    
					    
				    	folder sum3 = FolderDAO.getsum(drives_id);
					    long imageFileSize3 = sum3.getFolder_use();
					    double percent;
					    if(imageFileSize3 > 0) {
					       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
					    }else {
					    	 percent =0;	
					    }
					    String Size4 = Fileservice.printFileSize(imageFileSize3);
					   
				  	    
				  	    
					 
					    
				    	 
				 	    byte[] bytes = file.getBytes();  
				 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				 	         new File(path + File.separator + filename)));  
				 	    stream.write(bytes);  
				 	    stream.flush();  
				 	    stream.close();  
				 	  
				 	    
				 	
				 	    
				 	    
				 	    FileDAO.savesubtask(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
				 	    		, folders.getFolder_id(),null,actionplanid,date,Pathfull,users.getUsername(),dateend);       
				    
				 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
					   	if(log_old != null) {
					   		logs.setLog_datestart(log_old.getLog_date());
					   		logs.setLog_timestart(log_old.getLog_time());
					   	}
						logs.setLog_id_add(users.getUser_id());
						logs.setLog_step("Task");
						logs.setLog_date(date);
						logs.setLog_due("");
						logs.setLog_time(time);
						logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
						logs.setLog_refer(String.valueOf(actionplanid));
						logs.setLog_folder(Pathfull);
						logs.setLog_file(filename);
						logs.setLog_dive(drivess.getDrives_drive());
						LogDAO.save(logs);
				
					     		}
							} 
							}
						 } else {
					            model.addAttribute("error", "No files uploaded");
					            return "error";
					        }
						
						Filemanagerservice.UpdateMemorySize(folders,imageFileSize_total,1);
						
					
						
						
						
						
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "Upload failed");
	        return "error";
	    }
	
		
		
		
	    return "redirect:/task-filemanager/"+task_id;
  
	}
	

	

	
    @RequestMapping(value="/savesubfileapp-filemanager/{edata_id}", method=RequestMethod.GET)
	public String savesubfileapp(ModelMap model
			,@PathVariable int edata_id
	         ) { 
    	
    	           System.out.println(" edata_id "+edata_id);
					edata edata = EdataDAO.getbyid(edata_id);
					model.addAttribute("edatas",edata); 
    	            List<file> files = FileDAO.getallEdata(edata_id);
    	            model.addAttribute("files",files); 
    	 			return "/app/windows/file";
    		}
	
    
    
    
    @RequestMapping(value="/unfile_app-filemanager/{id}/{edata_id}", method=RequestMethod.GET)
   	public String unfile_app(ModelMap model
   			,@PathVariable int edata_id
   			,@PathVariable int id
   			,HttpSession session
   	         ) { 
       	
		    	String paths;
			   	File fileToDelete;
			   	boolean success;
			   	
			   	file filesed = FileDAO.getbyId(id);
			    ServletContext contexts = session.getServletContext(); 
			    
	       		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
				   	String cleanPath = storagePath.replace("file:///", ""); 

				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
				   	String path = cleanPath + filesed.getFile_path();
			    
		
			     
			     
			     fileToDelete = new File(path + File.separator + filesed.getFile_name());
			     success = fileToDelete.delete();
			     if(success) {
			    	 
			    	 
			    	    FileDAO.delete(id);

			 	       folder folder = FolderDAO.getbytype(filesed.getFile_path());
			 	       folder mainFolder = (folder != null) ? FolderDAO.getbyId(folder.getFolder_idrefer()) : null;
			 	       Filemanagerservice.updateFolderUsage(folder);
			 	       
			 	       if (mainFolder != null) {
			 	    	  Filemanagerservice.updateFolderUsage(mainFolder);
			 	       }

			    	    Filemanagerservice.UpdateMemoryDrivesSize();
			    	    
			     	}
    	
       
    		return "redirect:/savesubfileapp-filemanager/"+edata_id;
       		}
    
    
    
    

    @RequestMapping(value="/unfile_appindex-filemanager/{id}/{item_id}/{process_id}", method=RequestMethod.GET)
   	public String unfile_appindex(ModelMap model
   			,@PathVariable int item_id
   			,@PathVariable int process_id
   			,@PathVariable int id
   			,HttpSession session
   	         ) { 
       	
		    	String paths;
			   	File fileToDelete;
			   	boolean success;
			   	
			   	file filesed = FileDAO.getbyId(id);
			    ServletContext contexts = session.getServletContext(); 
			     paths = contexts.getRealPath("/filesuploads/" + filesed.getFile_path()); 
			     fileToDelete = new File(paths + File.separator + filesed.getFile_name());
			     success = fileToDelete.delete();
			     if(success) {
			    	 
			    	 
			    	    FileDAO.delete(id);

			 	       folder folder = FolderDAO.getbytype(filesed.getFile_path());
			 	       folder mainFolder = (folder != null) ? FolderDAO.getbyId(folder.getFolder_idrefer()) : null;
			 	       Filemanagerservice.updateFolderUsage(folder);
			 	       
			 	       if (mainFolder != null) {
			 	    	  Filemanagerservice.updateFolderUsage(mainFolder);
			 	       }

			    	    Filemanagerservice.UpdateMemoryDrivesSize();
			    	    
			     	}
    	
       
			     return "redirect:/fileapp-filemanager/"+item_id+"/"+process_id;
       		}
    
    
    
    
    
    
	@RequestMapping(value="/uploadfile_indexapp-filemanager", method=RequestMethod.POST)
	public String uploadfile_indexapp(ModelMap model
			,@RequestParam int file_indexapp
			,@RequestParam int process_id
			,@RequestParam CommonsMultipartFile[] files,  
	         HttpSession session
	         ,log logs
	         ,@RequestParam String file_tag
	     	,@RequestParam String folder
	         ) throws Exception{ 

		System.out.println(" test 1");
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 String dateend = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		 DateTimeFormatter formatte3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	  	LocalDateTime localDate = LocalDateTime.now();//For reference
		String date = localDate.format(formatter);
		String datetime = localDate.format(formatte3);
		 String time = localDate.format(formatter2);

		
		long imageFileSize_total = 0; 
		for (CommonsMultipartFile file : files) {
			 imageFileSize_total = imageFileSize_total + file.getSize();
		   }
		System.out.println(" folder "+folder);
		folder folders = FolderDAO.getbytype(folder);
		boolean diskspace =  Filemanagerservice.CheckMemorySize(folders,imageFileSize_total);
		
		
		System.out.println(" test 2");
		
		if(!diskspace) {
				notification  notifications = new notification();
	 			notifications.setNotification_iduser(users.getUser_name());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("⚠ คำเตือน: พื้นที่ดิสก์เหลือน้อย!ไม่เพียงพอ");
				notifications.setNotification_note("");
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_refer("0");
				notifications.setNotification_type("⚠ Warning: Low disk space! Less than remaining. (System Error)");
				NotificationDAO.save(notifications);  
		}
		
		System.out.println(" test 3");
		
		try {
						if (files != null && files.length > 0 && diskspace) {
						for (CommonsMultipartFile file : files) {
						if(file != null && file.getSize() > 0 ) {

	
						  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
      				   	String cleanPath = storagePath.replace("file:///", ""); 

      				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
      				   	String path = cleanPath +  folders.getFolder_type(); 
					    
					    
					    
					    String filenameoriginal = file.getOriginalFilename();  
					    
					    String filename = Fileservice.assertEquals(file.getContentType());
					    long imageFileSize = file.getSize();
					    String Size = Fileservice.printFileSize(imageFileSize);
					    
					   // System.out.print(imageFileSize);
					    if(filename == null) {
						 	   model.addAttribute("error","error 08"); 
					 	        return "error";
						    }  
					    
					    String Pathfull = null;
					    String Pathfull2 = null;
					    
				     if(filename != null) {
				    	 
				   
				  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
				  	    int drives_id;
				  	    drives_id = folders.getFolder_idrefer();
				  	    if(mainfolders != null) {
				  	    drives_id= mainfolders.getFolder_idrefer();	
				  	    }
				  	    drives drivess  = DrivesDAO.getbyId(drives_id);
				            
				  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
				  	    if(imageFileSize > totals) {
				  	    	
				  	            model.addAttribute("error","error 02"); 
				  	            return "error";
				  	    }
				  	    
				  	    file sum = FileDAO.getsum(folders.getFolder_type());
					    long imageFileSize2 = sum.getFile_size() +imageFileSize;
					    String Size2 = Fileservice.printFileSize(imageFileSize2);
					    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
					    
					  
					    
					    if(mainfolders != null) {
					    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
				    	    long imageFileSize3 = sum2.getFolder_use();
				    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
				    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
				    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
				    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
					    }else {
					    	
				        	drives_id = folders.getFolder_idrefer();
				        	drivess  = DrivesDAO.getbyId(drives_id);
				        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
				        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
				    	    }
				    	          	    
					    
					    
				    	folder sum3 = FolderDAO.getsum(drives_id);
					    long imageFileSize3 = sum3.getFolder_use();
					    double percent;
					    if(imageFileSize3 > 0) {
					       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
					    }else {
					    	 percent =0;	
					    }
					    String Size4 = Fileservice.printFileSize(imageFileSize3);
					   
				  	    
				  	    
					 
					    
				    	 
				 	    byte[] bytes = file.getBytes();  
				 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				 	         new File(path + File.separator + filename)));  
				 	    stream.write(bytes);  
				 	    stream.flush();  
				 	    stream.close();  
				 	  
				 	    
				 	      savefileDTO savefile =  new savefileDTO();
					 	 
					 	 savefile.setFilename(filename);
					 	 savefile.setNewfilename(filenameoriginal);
					 	 savefile.setTypereal(file.getContentType());
					 	 savefile.setFolder_type(folders.getFolder_type());
					 	 savefile.setSize(file.getSize());
					 	 savefile.setSize_string(Size);
					 	 savefile.setFolder_id(folders.getFolder_id());
					 	 savefile.setReport_pnid(0);
					 	 savefile.setDate(date);
					 	 savefile.setPathfull(Pathfull);
					 	 savefile.setUsername(users.getUsername());
					 	 savefile.setDateend(dateend);
					 	 savefile.setFile_step(0);
					 	 savefile.setFile_indexapp(file_indexapp);
					     savefile.setFile_tag(file_tag);
					 	 
					  	 FileDAO.save(savefile);    
		
					  	System.out.println(" test 4");
		 
				    
				 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
					   	if(log_old != null) {
					   		logs.setLog_datestart(log_old.getLog_date());
					   		logs.setLog_timestart(log_old.getLog_time());
					   	}
						logs.setLog_id_add(users.getUser_id());
						logs.setLog_step("App");
						logs.setLog_date(date);
						logs.setLog_due("");
						logs.setLog_time(time);
						logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
						logs.setLog_refer(String.valueOf(file_indexapp));
						logs.setLog_folder(Pathfull);
						logs.setLog_file(filename);
						logs.setLog_dive(drivess.getDrives_drive());
						LogDAO.save(logs);
				
					     		}
							} 
							}
						 } else {
					            model.addAttribute("error", "No files uploaded");
					            return "error";
					        }
						
						Filemanagerservice.UpdateMemorySize(folders,imageFileSize_total,1);
						
					
						System.out.println(" test 5");
						
						
						
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "Upload failed");
	        return "error";
	    }
	
		
		System.out.println(" test 6");
		
	    return "redirect:/fileapp-filemanager/"+file_indexapp+"/"+process_id;
  
	}
	

    
    
    
    
	
	@RequestMapping(value="/uploadfile_app-filemanager", method=RequestMethod.POST)
	public String uploadfile_app(ModelMap model
			,@RequestParam int edata_id
			,@RequestParam CommonsMultipartFile[] files,  
	         HttpSession session
	         ,log logs
	         ,@RequestParam String file_tag
	     	,@RequestParam String folder
	         ) throws Exception{ 
		
		
		edata edata = EdataDAO.getbyid(edata_id);
		
		System.out.println(" test 1");
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 String dateend = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		 DateTimeFormatter formatte3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	  	LocalDateTime localDate = LocalDateTime.now();//For reference
		String date = localDate.format(formatter);
		String datetime = localDate.format(formatte3);
		 String time = localDate.format(formatter2);

		
		long imageFileSize_total = 0; 
		for (CommonsMultipartFile file : files) {
			 imageFileSize_total = imageFileSize_total + file.getSize();
		   }
		System.out.println(" folder "+folder);
		folder folders = FolderDAO.getbytype(folder);
		boolean diskspace =  Filemanagerservice.CheckMemorySize(folders,imageFileSize_total);
		
		
		System.out.println(" test 2");
		
		if(!diskspace) {
				notification  notifications = new notification();
	 			notifications.setNotification_iduser(users.getUser_name());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("⚠ คำเตือน: พื้นที่ดิสก์เหลือน้อย!ไม่เพียงพอ");
				notifications.setNotification_note("");
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_refer("0");
				notifications.setNotification_type("⚠ Warning: Low disk space! Less than remaining. (System Error)");
				NotificationDAO.save(notifications);  
		}
		
		System.out.println(" test 3");
		
		try {
						if (files != null && files.length > 0 && diskspace) {
						for (CommonsMultipartFile file : files) {
						if(file != null && file.getSize() > 0 ) {

				
					    
					    
					    
						  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
      				   	String cleanPath = storagePath.replace("file:///", ""); 

      				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
      				   	String path = cleanPath +  folders.getFolder_type(); 
					    
					    String filenameoriginal = file.getOriginalFilename();  
					    String filename = Fileservice.assertEquals(file.getContentType());
					    long imageFileSize = file.getSize();
					    String Size = Fileservice.printFileSize(imageFileSize);
					    
					   // System.out.print(imageFileSize);
					    if(filename == null) {
						 	   model.addAttribute("error","error 08"); 
					 	        return "error";
						    }  
					    
					    String Pathfull = null;
					    String Pathfull2 = null;
					    
				     if(filename != null) {
				    	 
				   
				  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
				  	    int drives_id;
				  	    drives_id = folders.getFolder_idrefer();
				  	    if(mainfolders != null) {
				  	    drives_id= mainfolders.getFolder_idrefer();	
				  	    }
				  	    drives drivess  = DrivesDAO.getbyId(drives_id);
				            
				  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
				  	    if(imageFileSize > totals) {
				  	    	
				  	            model.addAttribute("error","error 02"); 
				  	            return "error";
				  	    }
				  	    
				  	    file sum = FileDAO.getsum(folders.getFolder_type());
					    long imageFileSize2 = sum.getFile_size() +imageFileSize;
					    String Size2 = Fileservice.printFileSize(imageFileSize2);
					    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
					    
					  
					    
					    if(mainfolders != null) {
					    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
				    	    long imageFileSize3 = sum2.getFolder_use();
				    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
				    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
				    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
				    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
					    }else {
					    	
				        	drives_id = folders.getFolder_idrefer();
				        	drivess  = DrivesDAO.getbyId(drives_id);
				        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
				        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
				    	    }
				    	          	    
					    
					    
				    	folder sum3 = FolderDAO.getsum(drives_id);
					    long imageFileSize3 = sum3.getFolder_use();
					    double percent;
					    if(imageFileSize3 > 0) {
					       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
					    }else {
					    	 percent =0;	
					    }
					    String Size4 = Fileservice.printFileSize(imageFileSize3);
					   
				  	    
				  	    
					 
					    
				    	 
				 	    byte[] bytes = file.getBytes();  
				 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				 	         new File(path + File.separator + filename)));  
				 	    stream.write(bytes);  
				 	    stream.flush();  
				 	    stream.close();  
				 	  
				 	    
				 	      savefileDTO savefile =  new savefileDTO();
					 	 
					 	 savefile.setFilename(filename);
					 	 savefile.setNewfilename(filenameoriginal);
					 	 savefile.setTypereal(file.getContentType());
					 	 savefile.setFolder_type(folders.getFolder_type());
					 	 savefile.setSize(file.getSize());
					 	 savefile.setSize_string(Size);
					 	 savefile.setFolder_id(folders.getFolder_id());
					 	 savefile.setReport_pnid(0);
					 	 savefile.setDate(date);
					 	 savefile.setPathfull(Pathfull);
					 	 savefile.setUsername(users.getUsername());
					 	 savefile.setDateend(dateend);
					 	 savefile.setFile_step(0);
					 	 savefile.setFile_edata(edata_id);
					     savefile.setFile_tag(file_tag);
					 	 
					  	 FileDAO.save(savefile);    
		
					  	System.out.println(" test 4");
		 
				    
				 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
					   	if(log_old != null) {
					   		logs.setLog_datestart(log_old.getLog_date());
					   		logs.setLog_timestart(log_old.getLog_time());
					   	}
						logs.setLog_id_add(users.getUser_id());
						logs.setLog_step("App");
						logs.setLog_date(date);
						logs.setLog_due("");
						logs.setLog_time(time);
						logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
						logs.setLog_refer(String.valueOf(edata_id));
						logs.setLog_folder(Pathfull);
						logs.setLog_file(filename);
						logs.setLog_dive(drivess.getDrives_drive());
						LogDAO.save(logs);
				
					     		}
							} 
							}
						 } else {
					            model.addAttribute("error", "No files uploaded");
					            return "error";
					        }
						
						Filemanagerservice.UpdateMemorySize(folders,imageFileSize_total,1);
						
					
						System.out.println(" test 5");
						
						
						
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "Upload failed");
	        return "error";
	    }
	
		
		System.out.println(" test 6");
		
	    return "redirect:/savesubfileapp-filemanager/"+edata_id;
  
	}
	

	
	@RequestMapping(value="/editactionplan-filemanager", method=RequestMethod.POST)
	public String editactionplan(ModelMap model
			,@RequestParam String actionplan_comment
			,@RequestParam  (required=false,name="actionplan_expenses",defaultValue="0") String actionplan_expenses
			,@RequestParam  (required=false,name="actionplan_payment",defaultValue="0") String actionplan_payment
			
			,@RequestParam  (required=false,name="actionplan_problem") String actionplan_problem
			,@RequestParam  (required=false,name="actionplan_solving") String actionplan_solutions
			,@RequestParam  (required=false,name="actionplan_result") String actionplan_result
			,@RequestParam int task_id
			,@RequestParam int actionplan_id
			,@RequestParam int folder
			,@RequestParam(required = false) CommonsMultipartFile[] files,  
	         HttpSession session
	         ,log logs) { 
		
		
	     System.err.println(" 0-> ");
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 String dateend = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	  	LocalDateTime localDate = LocalDateTime.now();//For reference
		String date = localDate.format(formatter);
		 String time = localDate.format(formatter2);
		
		actionplan oldactionplan = ActionplanDAO.getByid(actionplan_id);
		 
		 
        System.err.println(" 1-> ");
		
		actionplan actionplan = new actionplan();
		actionplan.setActionplan_comment(actionplan_comment);
		actionplan.setActionplan_dateadd(date);
		actionplan.setActionplan_result(oldactionplan.getActionplan_result());
		actionplan.setActionplan_idrfer(task_id);
		actionplan.setActionplan_responsible(users.getUser_name());
		actionplan.setActionplan_expenses(actionplan_expenses);
		actionplan.setActionplan_payment(actionplan_payment);
		actionplan.setActionplan_id(actionplan_id);
		actionplan.setSubmission(oldactionplan.getSubmission());
		actionplan.setActionplan_problem(actionplan_problem);
		actionplan.setActionplan_solutions(actionplan_solutions);
		actionplan.setActionplan_result(actionplan_result);
		
		
		
		if(oldactionplan.getSubmission() == -1) {
			actionplan.setSubmission(-2);
		}
		
		
		int actionplanid = ActionplanDAO.update(actionplan);
		int num = ActionplanDAO.numbyidrefer(task_id);
		TaskDAO.setalert(num,1,task_id);
		
			task tasks = TaskDAO.getbyid(task_id);

		     System.err.println(" 2-> ");
			
		   user userapp = UserDAO.getbyId(tasks.getTask_idadd(), 0);
			notification  notifications = new notification();
			notifications.setNotification_iduser(userapp.getUser_name());
			notifications.setNotification_iduser2(null);
			notifications.setNotification_alert("คุณมีกิจกรรมโครงการรอดำเนินการ");
			notifications.setNotification_note(" โดย "+users.getUser_name());
			notifications.setNotification_date(date+" "+time);
			notifications.setNotification_read(0);
			notifications.setNotification_refer(String.valueOf(task_id));
			notifications.setNotification_type("You have pending project activities.");
			NotificationDAO.save(notifications); 
		
		     System.err.println(" 3-> ");

				try {
						if (files != null && files.length > 0) {
						for (CommonsMultipartFile file : files) {
						if(file != null && file.getSize() > 0 ) {
							
					
						
				        folder folders = FolderDAO.getbyId(folder);
					  
	
					    
					    
						  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
      				   	String cleanPath = storagePath.replace("file:///", ""); 

      				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
      				   	String path = cleanPath +  folders.getFolder_type(); 
					    
					    
					    String filenameoriginal = file.getOriginalFilename();  
					    String filename = Fileservice.assertEquals(file.getContentType());
					    long imageFileSize = file.getSize();
					    String Size = Fileservice.printFileSize(imageFileSize);
					    
					   // System.out.print(imageFileSize);
					    if(filename == null) {
						 	   model.addAttribute("error","error 08"); 
					 	        return "error";
						    }  
					    
					    String Pathfull = null;
					    String Pathfull2 = null;
					    
				     if(filename != null) {
				    	 
				   
				  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
				  	    int drives_id;
				  	    drives_id = folders.getFolder_idrefer();
				  	    if(mainfolders != null) {
				  	    drives_id= mainfolders.getFolder_idrefer();	
				  	    }
				  	    drives drivess  = DrivesDAO.getbyId(drives_id);
				            
				  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
				  	    if(imageFileSize > totals) {
				  	    	
				  	            model.addAttribute("error","error 02"); 
				  	            return "error";
				  	    }
				  	    
				  	    file sum = FileDAO.getsum(folders.getFolder_type());
					    long imageFileSize2 = sum.getFile_size() +imageFileSize;
					    String Size2 = Fileservice.printFileSize(imageFileSize2);
					    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
					    
					  
					    
					    if(mainfolders != null) {
					    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
				    	    long imageFileSize3 = sum2.getFolder_use();
				    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
				    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
				    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
				    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
					    }else {
					    	
				        	drives_id = folders.getFolder_idrefer();
				        	drivess  = DrivesDAO.getbyId(drives_id);
				        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
				        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
				    	    }
				    	          	    
					    
					    
				    	folder sum3 = FolderDAO.getsum(drives_id);
					    long imageFileSize3 = sum3.getFolder_use();
					    double percent;
					    if(imageFileSize3 > 0) {
					       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
					    }else {
					    	 percent =0;	
					    }
					    String Size4 = Fileservice.printFileSize(imageFileSize3);
					    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
				  	    
				  	    
				    	 
				 	    byte[] bytes = file.getBytes();  
				 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				 	         new File(path + File.separator + filename)));  
				 	    stream.write(bytes);  
				 	    stream.flush();  
				 	    stream.close();  
				 	  
				 	    
				 	
				 	    
				 	    
				 	    FileDAO.savesubtask(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
				 	    		, folders.getFolder_id(),null,actionplan_id,date,Pathfull,users.getUsername(),dateend);       
				    
				 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
					   	if(log_old != null) {
					   		logs.setLog_datestart(log_old.getLog_date());
					   		logs.setLog_timestart(log_old.getLog_time());
					   	}
						logs.setLog_id_add(users.getUser_id());
						logs.setLog_step("Task");
						logs.setLog_date(date);
						logs.setLog_due("");
						logs.setLog_time(time);
						logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
						logs.setLog_refer(String.valueOf(actionplanid));
						logs.setLog_folder(Pathfull);
						logs.setLog_file(filename);
						logs.setLog_dive(drivess.getDrives_drive());
						LogDAO.save(logs);
				
					     		}
							} 
							}
						 } else {
							
					            model.addAttribute("error", "No files uploaded");
					          
					        }
	    } catch (Exception e) {

	        
	        System.err.println("e "+e);
	        
	        model.addAttribute("error", "Upload failed");
	        return "error";
	    }
	
			     System.err.println(" 4-> ");

	
	    return "redirect:/task-filemanager/"+task_id;
  
	}
	
	
	
	
	
	@RequestMapping(value="/allusertask-filemanager/{id}",method = RequestMethod.GET)
	public String setplan(ModelMap model,@PathVariable int id) {
		
	 	upload_pn upload_pn = Upload_pnDAO.getbyId(id);
	    model.addAttribute("upload_pn",upload_pn);
	   	 List<UploadResponDTO> responList = new ArrayList<>();
	   	 
	   	 
	   	 List<Goal> GoalList = WorkflowDAO.getAllGoalByprocessesAnaTable(id,"upload_pn");
	   	 
	   	 
	   	 
	   	 
	    if(upload_pn.getId_policy() == 0 ) {
	    	

	    String[] parts = upload_pn.getUpload_pn_respon().split(",");
	    	 for (String row : parts) {
	    		 
	    	     String respon_name = row.trim();
	    	     UploadResponDTO  Respon = new UploadResponDTO();
	    	     
	    	     
	    	     GoalList.stream().filter(data->data.getResponsible().equals(respon_name)).findFirst().ifPresent(rows->{
	    	    	 int IsUploads =WorkflowDAO.countUpload_log(rows.getId(),rows.getResponsible());
	    	    	 int MissedUploads =WorkflowDAO.countMissed_uploads(rows.getId(),rows.getResponsible());
	    	    	 int IsUploadAll =WorkflowDAO.countEdata(rows.getProcesses_id(),rows.getResponsible());
	    	    	 int IsUploadLast = 0;
	    	    	 
	    	    	 if(IsUploadAll > (IsUploads + MissedUploads)) {
	    	    		 IsUploadLast = IsUploadAll - (IsUploads + MissedUploads);
	    	    		 Respon.setIsUploadsLast(IsUploadLast);
	    	    	 }
	    	    	 Respon.setId(rows.getId());
	    	         Respon.setIsUploads(IsUploads);
	    	         Respon.setMissedUploads(MissedUploads);
	    	     });
	    	     
	    	     
	    	   
	    	     Respon.setRespon(respon_name);
	    	
	    	     responList.add(Respon);
  
	    	 }
	   
	    
	    	
	    }else {
		   	 	 Policy policy  = policyRepository.findById(upload_pn.getId_policy());
		    	 for (PolicyMember row : policy.getMembers()) {
		    	     String respon_name = row.getEmployeeName();
		    	     UploadResponDTO  Respon = new UploadResponDTO();
		    	     
		    	     
		    	     
		    	     GoalList.stream().filter(data->data.getResponsible().equals(respon_name)).findFirst().ifPresent(rows->{
		    	    	 int IsUploads =WorkflowDAO.countUpload_log(rows.getId(),rows.getResponsible());
		    	    	 int MissedUploads =WorkflowDAO.countMissed_uploads(rows.getId(),rows.getResponsible());
		    	    	 int IsUploadAll =WorkflowDAO.countEdata(rows.getProcesses_id(),rows.getResponsible());
		    	    	 int IsUploadLast = 0;
		    	    	 
		    	    	 if(IsUploadAll > (IsUploads + MissedUploads)) {
		    	    		 IsUploadLast = IsUploadAll - (IsUploads + MissedUploads);
		    	    		 Respon.setIsUploadsLast(IsUploadLast);
		    	    	 }
		    	    	 
		    	    	 Respon.setId(rows.getId());
		    	         Respon.setIsUploads(IsUploads);
		    	         Respon.setMissedUploads(MissedUploads);
		    	     });
		    	     
		    	     Respon.setRespon(respon_name);
		    	     responList.add(Respon);
		    	 }
	    	
	    }
	    
	    
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
        String USER_GROUP = "guest";
        
	   	if(upload_pn.getId_policy() > 0) {
		   	 	 Policy policy  = policyRepository.findById(upload_pn.getId_policy());
		   	 	 String name = policy.getMembers().stream().map(row-> row.getEmployeeName()).limit(5).collect(Collectors.joining(","));
		   	 	upload_pn.setUpload_pn_respon(name);
		   	 	 
		   	 	USER_GROUP = policyService.getcheckAccessByPolicy(upload_pn,usersed,policy);
		   	 	model.addAttribute("USER_GROUP",USER_GROUP);
		   	 	
	   	}else {
	   		USER_GROUP = policyService.getcheckAccess(upload_pn,usersed);
	   	 	model.addAttribute("USER_GROUP",USER_GROUP); 
	   	}
	    
	    
	    model.addAttribute("id",id);
	    model.addAttribute("responList",responList);
		return "task/allusertask";
	}
	
	
	
	@RequestMapping(value="/logupload-filemanager/{goal_id}/{id}",method = RequestMethod.GET)
	public String setplan(ModelMap model,@PathVariable int goal_id,@PathVariable int id) {
		
	 	upload_pn upload_pn = Upload_pnDAO.getbyId(id);
	    model.addAttribute("upload_pn",upload_pn);

	   	 
	   	 
	   	 Goal MyGoal =WorkflowDAO.getGoal(goal_id);
	   	 
	   	 UploadResponDTO  Respon = new UploadResponDTO();
	   	 int IsUploads =WorkflowDAO.countUpload_log(MyGoal.getId(),MyGoal.getResponsible());
    	 int MissedUploads =WorkflowDAO.countMissed_uploads(MyGoal.getId(),MyGoal.getResponsible());
    	 int IsUploadAll =WorkflowDAO.countEdata(MyGoal.getProcesses_id(),MyGoal.getResponsible());
    	 int IsUploadLast = 0;
    	 
    	 if(IsUploadAll > (IsUploads + MissedUploads)) {
    		 IsUploadLast = IsUploadAll - (IsUploads + MissedUploads);
    		 Respon.setIsUploadsLast(IsUploadLast);
    	 }
    	 Respon.setId(MyGoal.getId());
         Respon.setIsUploads(IsUploads);
         Respon.setMissedUploads(MissedUploads);
	     Respon.setRespon(MyGoal.getResponsible()); 
	    	
	   	 
	   	 List<upload_log> ListIsUploads =WorkflowDAO.findAllUpload_log(MyGoal.getId(),MyGoal.getResponsible());
	   	 List<missed_uploads> ListMissedUploads =WorkflowDAO.findAllMissed_uploads(MyGoal.getId(),MyGoal.getResponsible());
	   	 List<edata> ListIsUploadAll =WorkflowDAO.findAllEdata(MyGoal.getProcesses_id(),MyGoal.getResponsible());
	     
	   	 List<Integer> remove1 = ListIsUploads.stream().map(row -> row.getEdata_id() ).collect(Collectors.toList());
	   	 List<Integer> remove2 = ListMissedUploads.stream().map(row -> row.getEdata_id() ).collect(Collectors.toList());
	   	 
	   	ListIsUploadAll.removeIf(row-> remove1.contains(row.getData_id()) || remove2.contains(row.getData_id()));
	   	 
	    model.addAttribute("Respon",Respon);
	    model.addAttribute("ListIsUploads",ListIsUploads);
	    model.addAttribute("ListMissedUploads",ListMissedUploads);
	    model.addAttribute("ListIsUploadAll",ListIsUploadAll);
	    model.addAttribute("MyGoal",MyGoal);
	    
	    
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
        String USER_GROUP = "guest";
        
	   	if(upload_pn.getId_policy() > 0) {
		   	 	 Policy policy  = policyRepository.findById(upload_pn.getId_policy());
		   	 	 String name = policy.getMembers().stream().map(row-> row.getEmployeeName()).limit(5).collect(Collectors.joining(","));
		   	 	upload_pn.setUpload_pn_respon(name);
		   	 	 
		   	 	USER_GROUP = policyService.getcheckAccessByPolicy(upload_pn,usersed,policy);
		   	 	model.addAttribute("USER_GROUP",USER_GROUP);
		   	 	
	   	}else {
	   		USER_GROUP = policyService.getcheckAccess(upload_pn,usersed);
	   	 	model.addAttribute("USER_GROUP",USER_GROUP); 
	   	}
	    
	    
	    
		return "task/usertask";
	}
	
	
	@RequestMapping(value="/setplan-filemanager",method = RequestMethod.POST)
	public String setplan(ModelMap model,upload_pn upload_pns,@RequestParam int step,@RequestParam int group) {
		
		Upload_pnDAO.setSetting(upload_pns);
		
		if(upload_pns.getUpload_pn_allpermissions() == 0 ) {
			Upload_pnDAO.setSettingAll(upload_pns);
		}
		
		
		return "redirect:/duepn-filemanager?id="+upload_pns.getUpload_pn_id()+"&&step="+step+"&&group="+group;
	}
	
	
	
	@RequestMapping(value="/savesubtask-filemanager/{id}",method = RequestMethod.GET)
	public String savesubtask(ModelMap model,@PathVariable int id) {
		
		List<actionplan> actionplans = ActionplanDAO.getAll(id);
			
			actionplans.stream().forEach(row->{
				List<file> files2 = FileDAO.getallactionplan(row.getActionplan_id());
				row.setFile(files2);
			});

		model.addAttribute("actionplans",actionplans);

		return "/ajax/subtask";
	}

	
	@RequestMapping(value="/savesubtask2-filemanager/{id}",method = RequestMethod.GET)
	public String savesubtask2(ModelMap model,@PathVariable int id) {
		
		actionplan actionplans = ActionplanDAO.getByid(id);
		List<file> files2 = FileDAO.getallactionplan(id);
		actionplans.setFile(files2);
		
		model.addAttribute("actionplans",actionplans);
		
		
		
		
		task tasked =  TaskDAO.getbyid(actionplans.getActionplan_idrfer());
		model.addAttribute("tasks",tasked);
		
		

		return "/ajax/subtask3";
	}

	
	@RequestMapping(value="/saveactionplan2-filemanager/{id}",method = RequestMethod.GET)
	public String saveactionplan2(ModelMap model,@PathVariable int id) {
		
		actionplan actionplans = ActionplanDAO.getByids(id);
		model.addAttribute("actionplans",actionplans);

		return "/ajax/actionplan2";
	}
	
	
	@RequestMapping(value="/addsubactionplan-filemanager", method=RequestMethod.POST)
	public String addactionplan(ModelMap model
			,@RequestParam String actionplan_comment
			,@RequestParam int id_refer
			,@RequestParam int folder
			,@RequestParam int task_id
			,@RequestParam CommonsMultipartFile file,  
	         HttpSession session
	         ,log logs) throws Exception{ 
		

		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 String dateend = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	  	LocalDateTime localDate = LocalDateTime.now();//For reference
		String date = localDate.format(formatter);
		 String time = localDate.format(formatter2);
		
		
		actionplan actionplan = new actionplan();
		actionplan.setActionplan_comment(actionplan_comment);
		actionplan.setActionplan_dateadd(date);
		actionplan.setActionplan_idrfer(id_refer);
		actionplan.setActionplan_responsible(users.getUser_name());
		int actionplanid = ActionplanDAO.save2(actionplan);
	
		
		System.out.println(" 555555555555555555");
		
		
		actionplan actionplans = ActionplanDAO.getByid(id_refer);
		notification  notifications = new notification();
		notifications.setNotification_iduser(actionplans.getActionplan_responsible());
		notifications.setNotification_iduser2(null);
		notifications.setNotification_alert("คุณมีกิจกรรมโครงการรอดำเนินการ");
		notifications.setNotification_note(" โดย "+users.getUser_name());
		notifications.setNotification_date(date+" "+time);
		notifications.setNotification_read(0);
		notifications.setNotification_refer(String.valueOf(task_id));
		notifications.setNotification_type("You have pending project activities.");
		NotificationDAO.save(notifications);   
	    
		

		int num = ActionplanDAO.numbyidrefer2(id_refer);
		ActionplanDAO.setcm(num,id_refer);
		
		
		
		if(file != null && file.getSize() > 0) {
			
	
		
        folder folders = FolderDAO.getbyId(folder);
	  

	    
		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
		   	String cleanPath = storagePath.replace("file:///", ""); 

		   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
		   	String path = cleanPath +  folders.getFolder_type(); 
	    
	    
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);
	    
	   // System.out.print(imageFileSize);
	    if(filename == null) {
		 	   model.addAttribute("error","error 08"); 
	 	        return "error";
		    }  
	    
	    String Pathfull = null;
	    String Pathfull2 = null;
	    
	    
	    
	
	    
	    
     if(filename != null) {
    	 
   
  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	    int drives_id;
  	    drives_id = folders.getFolder_idrefer();
  	    if(mainfolders != null) {
  	    drives_id= mainfolders.getFolder_idrefer();	
  	    }
  	    drives drivess  = DrivesDAO.getbyId(drives_id);
            
  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
  	    if(imageFileSize > totals) {
  	    	
  	            model.addAttribute("error","error 02"); 
  	            return "error";
  	    }
  	    
  	    file sum = FileDAO.getsum(folders.getFolder_type());
	    long imageFileSize2 = sum.getFile_size() +imageFileSize;
	    String Size2 = Fileservice.printFileSize(imageFileSize2);
	    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
	    
	  
	    
	    if(mainfolders != null) {
	    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
    	    long imageFileSize3 = sum2.getFolder_use();
    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
	    }else {
	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
    	    }
    	          	    
	    
	    
    	folder sum3 = FolderDAO.getsum(drives_id);
	    long imageFileSize3 = sum3.getFolder_use();
	    double percent;
	    if(imageFileSize3 > 0) {
	       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
	    }else {
	    	 percent =0;	
	    }
	    String Size4 = Fileservice.printFileSize(imageFileSize3);
	    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
  	    
  	    
    	 
 	    byte[] bytes = file.getBytes();  
 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 	         new File(path + File.separator + filename)));  
 	    stream.write(bytes);  
 	    stream.flush();  
 	    stream.close();  
 	  
 	    
 	
 	    
 	    
 	    FileDAO.savesubtask2(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
 	    		, folders.getFolder_id(),null,actionplanid,date,Pathfull,users.getUsername(),dateend);       
 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
	   	if(log_old != null) {
	   		logs.setLog_datestart(log_old.getLog_date());
	   		logs.setLog_timestart(log_old.getLog_time());
	   	}
 	    
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("Task");
		logs.setLog_date(date);
		logs.setLog_due("");
		logs.setLog_time(time);
		logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
		logs.setLog_refer(String.valueOf(actionplanid));
		logs.setLog_folder(Pathfull);
		logs.setLog_file(filename);
		logs.setLog_dive(drivess.getDrives_drive());
		LogDAO.save(logs);
		
		
		
		
		
		
    }
     
	}
	
	    return "redirect:/subactionplan-filemanager/"+id_refer;
  
	}
	
	
	
	
	

	@RequestMapping(value="/editsubactionplan-filemanager", method=RequestMethod.POST)
	public String editsubactionplan(ModelMap model
			,@RequestParam String actionplan_comment
			,@RequestParam  (required=false,name="actionplan_expenses",defaultValue="0") String actionplan_expenses
			,@RequestParam  (required=false,name="actionplan_payment",defaultValue="0") String actionplan_payment
			,@RequestParam int id_refer
			,@RequestParam int folder
			,@RequestParam int task_id
			,@RequestParam int actionplan_id
			,@RequestParam(required = false) int del  
			,@RequestParam(required = false) CommonsMultipartFile file,  
	         HttpSession session
	         ,log logs) throws Exception{ 
		
		
		System.out.print("dd " + actionplan_expenses);
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 String dateend = null;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	  	LocalDateTime localDate = LocalDateTime.now();//For reference
		String date = localDate.format(formatter);
		 String time = localDate.format(formatter2);
		
		
		actionplan actionplan = new actionplan();
		actionplan.setActionplan_comment(actionplan_comment);
		actionplan.setActionplan_dateadd(date);
		actionplan.setActionplan_idrfer(id_refer);
		actionplan.setActionplan_id(actionplan_id);
		actionplan.setActionplan_responsible(users.getUser_name());
		actionplan.setActionplan_expenses(actionplan_expenses);
		actionplan.setActionplan_payment(actionplan_payment);
		
		int actionplanid = ActionplanDAO.update(actionplan);
	
		
		int num = ActionplanDAO.numbyidrefer2(id_refer);
		ActionplanDAO.setcm(num,id_refer);
		
		
		
		String paths;
	   	File fileToDelete;
	   	boolean success;
	    ServletContext contexts = session.getServletContext(); 

	    
	    
		if(del != 0 ) {
			
			   
		   	file files = FileDAO.getbyactionplans(actionplan_id);
			     
		   	if(files != null) {
			   	paths = contexts.getRealPath("/filesuploads/" + files.getFile_path()); 
				     fileToDelete = new File(paths + File.separator + files.getFile_name());
				     success = fileToDelete.delete();
				     if(success) {
				     	 FileDAO.delete(files.getFile_id());
				     	}
		   	}
		}
		
		
		
		
		if(file != null && file.getSize() > 0) {
			
	
		    
		    
		   	file files = FileDAO.getbyactionplans(actionplan_id);
			     
		   	if(files != null) {
			   	paths = contexts.getRealPath("/filesuploads/" + files.getFile_path()); 
				     fileToDelete = new File(paths + File.separator + files.getFile_name());
				     success = fileToDelete.delete();
				     if(success) {
				     	 FileDAO.delete(files.getFile_id());
				     	}
		   	}	
			
		
        folder folders = FolderDAO.getbyId(folder);
	  

		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
		   	String cleanPath = storagePath.replace("file:///", ""); 

		   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
		   	String path = cleanPath +  folders.getFolder_type(); 
	    
	    
	    
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);
	    
	   // System.out.print(imageFileSize);
	    if(filename == null) {
		 	   model.addAttribute("error","error 08"); 
	 	        return "error";
		    }  
	    
	    String Pathfull = null;
	    String Pathfull2 = null;
	    
     if(filename != null) {
    	 
   
  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	    int drives_id;
  	    drives_id = folders.getFolder_idrefer();
  	    if(mainfolders != null) {
  	    drives_id= mainfolders.getFolder_idrefer();	
  	    }
  	    drives drivess  = DrivesDAO.getbyId(drives_id);
            
  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
  	    if(imageFileSize > totals) {
  	    	
  	            model.addAttribute("error","error 02"); 
  	            return "error";
  	    }
  	    
  	    file sum = FileDAO.getsum(folders.getFolder_type());
	    long imageFileSize2 = sum.getFile_size() +imageFileSize;
	    String Size2 = Fileservice.printFileSize(imageFileSize2);
	    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
	    
	  
	    
	    if(mainfolders != null) {
	    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
    	    long imageFileSize3 = sum2.getFolder_use();
    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
	    }else {
	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
    	    }
    	          	    
	    
	    
    	folder sum3 = FolderDAO.getsum(drives_id);
	    long imageFileSize3 = sum3.getFolder_use();
	    double percent;
	    if(imageFileSize3 > 0) {
	       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
	    }else {
	    	 percent =0;	
	    }
	    String Size4 = Fileservice.printFileSize(imageFileSize3);
	    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
  	    
  	    
    	 
 	    byte[] bytes = file.getBytes();  
 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 	         new File(path + File.separator + filename)));  
 	    stream.write(bytes);  
 	    stream.flush();  
 	    stream.close();  
 	  
 	    
 	
 	    
 	    
 	    FileDAO.savesubtask2(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
 	    		, folders.getFolder_id(),null,actionplan_id,date,Pathfull,users.getUsername(),dateend);       
    
 	    

    }
     
	}
	
	    return "redirect:/saveactionplan2-filemanager/"+actionplan_id;
  
	}
	
	
	
	
	@RequestMapping(value="/savetask-filemanager/{id}",method = RequestMethod.GET)
	public String savetasks(ModelMap model,@PathVariable int id) {
		
		task tasked =  TaskDAO.getbyid(id);
		model.addAttribute("tasks",tasked);
		
		
		if(tasked.getTask_idadd() > 0 ) {
			user useradd = UserDAO.getbyId2(tasked.getTask_idadd());
			model.addAttribute("useradd",useradd);
		}
		
		if(tasked.getTask_note() != null && !tasked.getTask_note().isEmpty()) {
			model.addAttribute("note", tasked.getTask_note().replace("\n", "<br>"));
		}
		
		
		
    	upload_pn upload_pns = Upload_pnDAO.getbyId(tasked.getTask_idrefer());
    	upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
    	
    	
		model.addAttribute("users",upload_pnsall);
		model.addAttribute("folder",upload_pns.getUpload_pn_idrefer());
	
		
		folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	  
	    
	    String Pathfull;
	    String Pathfulls;
	    drives drivess;
	    int drives_id;

	    
	    if(mainfolders != null) {
	    drives_id= mainfolders.getFolder_idrefer();	
	    drivess  = DrivesDAO.getbyId(drives_id);
	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
	    }else {
	    	
    	drives_id = folders.getFolder_idrefer();
    	drivess  = DrivesDAO.getbyId(drives_id);
    	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
	    }
	    model.addAttribute("Pathfulls",Pathfulls);
		
		List<file> files = FileDAO.getalltask(tasked.getTask_id());
		model.addAttribute("files",files);
		
		List<actionplan> actionplans = ActionplanDAO.getall(id);
		model.addAttribute("actionplans",actionplans);
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		model.addAttribute("Member_id",usersed.getUser_id());
		
		
		return "/ajax/savetask";
	}
	
	
	
	

	@RequestMapping(value = "/addfiletask", method = RequestMethod.POST)
	@ResponseBody
	public String addfiletask(
	        @RequestParam int folder,
	        @RequestParam int task_id,
	        @RequestParam CommonsMultipartFile file,
	        HttpServletRequest request,
	        HttpSession session) throws Exception {

	    try {
	        if (file == null || file.isEmpty()) {
	            return "{\"status\":\"error\",\"message\":\"ไม่พบไฟล์\"}";
	        }

	        // กำหนด path บันทึก
	        String savePath = request.getServletContext().getRealPath("/filesuploads/idoc");
	        File dir = new File(savePath);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        // ตั้งชื่อไฟล์ — ใช้ชื่อเดิมจาก Android (yyyyMMdd_HHmmss_ชื่อ.m4a)
	        String fileName   = file.getOriginalFilename();
	        String targetPath = savePath + File.separator + fileName;

	        // บันทึกไฟล์
	        file.transferTo(new File(targetPath));

	        System.out.println("[iDoc] บันทึกไฟล์: " + targetPath
	                + " | folder=" + folder + " | task_id=" + task_id);

	        return "{\"status\":\"success\",\"file\":\"" + fileName + "\"}";

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";
	    }
	}
	
	
	
	

	@RequestMapping(value="/addfiletask-filemanager", method=RequestMethod.POST)
	public String addfiletask(ModelMap model
			,@RequestParam int folder
			,@RequestParam int task_id
			,@RequestParam CommonsMultipartFile file,  
	         HttpSession session
	         ,log logs) throws Exception{ 
		
		
        folder folders = FolderDAO.getbyId(folder);
	
	  	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());

	
		
		
		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
		   	String cleanPath = storagePath.replace("file:///", ""); 

		   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
		   	String path = cleanPath +  folders.getFolder_type(); 
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);
	    
	   // System.out.print(imageFileSize);
	    if(filename == null) {
		 	   model.addAttribute("error","error 08"); 
	 	        return "error";
		    }  
	    
	    String Pathfull = null;
	    String Pathfull2 = null;
	    
     if(filename != null) {
    	 
   
  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	    int drives_id;
  	    drives_id = folders.getFolder_idrefer();
  	    if(mainfolders != null) {
  	    drives_id= mainfolders.getFolder_idrefer();	
  	    }
  	    drives drivess  = DrivesDAO.getbyId(drives_id);
            
  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
  	    if(imageFileSize > totals) {
  	    	
  	            model.addAttribute("error","error 02"); 
  	            return "error";
  	    }
  	    
  	    file sum = FileDAO.getsum(folders.getFolder_type());
	    long imageFileSize2 = sum.getFile_size() +imageFileSize;
	    String Size2 = Fileservice.printFileSize(imageFileSize2);
	    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
	    
	  
	    
	    if(mainfolders != null) {
	    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
    	    long imageFileSize3 = sum2.getFolder_use();
    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
	    }else {
	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
    	    }
    	          	    
	    
	    
    	folder sum3 = FolderDAO.getsum(drives_id);
	    long imageFileSize3 = sum3.getFolder_use();
	    double percent;
	    if(imageFileSize3 > 0) {
	       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
	    }else {
	    	 percent =0;	
	    }
	    String Size4 = Fileservice.printFileSize(imageFileSize3);
	    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
  	    
  	    
    	 
 	    byte[] bytes = file.getBytes();  
 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 	         new File(path + File.separator + filename)));  
 	    stream.write(bytes);  
 	    stream.flush();  
 	    stream.close();  
 	  
 	    
 	   String dateend = null;
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
  	     LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
 	    
 	    
 	    FileDAO.savetask(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
 	    		, folders.getFolder_id(),null,task_id,date,Pathfull,users.getUsername(),dateend);       
    
 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
	   	if(log_old != null) {
	   		logs.setLog_datestart(log_old.getLog_date());
	   		logs.setLog_timestart(log_old.getLog_time());
	   	}
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("Supporting Documents");
		logs.setLog_date(date);
		logs.setLog_due("");
		logs.setLog_time(time);
		logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
		logs.setLog_refer(String.valueOf(task_id));
		logs.setLog_folder(Pathfull);
		logs.setLog_file(filename);
		logs.setLog_dive(drivess.getDrives_drive());
		LogDAO.save(logs);
		
    }
     
     return "redirect:/savetask-filemanager/"+task_id;
  
	}
	
	
	
	
	
	
	
	

	

	@RequestMapping(value="/addfilereport-filemanager", method=RequestMethod.POST)
	public String addfilereport(ModelMap model
			,@RequestParam int folder
			,@RequestParam int report_id
			,@RequestParam CommonsMultipartFile file,  
	         HttpSession session
	         ,log logs) throws Exception{ 
		
		
        folder folders = FolderDAO.getbyId(folder);
	
	  	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());

	
		
		
		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
		   	String cleanPath = storagePath.replace("file:///", ""); 

		   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
		   	String path = cleanPath +  folders.getFolder_type(); 
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);
	    
	   // System.out.print(imageFileSize);
	    if(filename == null) {
		 	   model.addAttribute("error","error 08"); 
	 	        return "error";
		    }  
	    
	    String Pathfull = null;
	    String Pathfull2 = null;
	    
     if(filename != null) {
    	 
   
  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	    int drives_id;
  	    drives_id = folders.getFolder_idrefer();
  	    if(mainfolders != null) {
  	    drives_id= mainfolders.getFolder_idrefer();	
  	    }
  	    drives drivess  = DrivesDAO.getbyId(drives_id);
            
  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
  	    if(imageFileSize > totals) {
  	    	
  	            model.addAttribute("error","error 02"); 
  	            return "error";
  	    }
  	    
  	    file sum = FileDAO.getsum(folders.getFolder_type());
	    long imageFileSize2 = sum.getFile_size() +imageFileSize;
	    String Size2 = Fileservice.printFileSize(imageFileSize2);
	    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
	    
	  
	    
	    if(mainfolders != null) {
	    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
    	    long imageFileSize3 = sum2.getFolder_use();
    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
    	    Pathfull2 = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();
	    }else {
	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
        	Pathfull2 =drivess.getDrives_drive()+"/"+folders.getFolder_type()+"?id="+folders.getFolder_id();	 	
    	    }
    	          	    
	    
	    
    	folder sum3 = FolderDAO.getsum(drives_id);
	    long imageFileSize3 = sum3.getFolder_use();
	    double percent;
	    if(imageFileSize3 > 0) {
	       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
	    }else {
	    	 percent =0;	
	    }
	    String Size4 = Fileservice.printFileSize(imageFileSize3);
	    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
  	    
  	    
    	 
 	    byte[] bytes = file.getBytes();  
 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 	         new File(path + File.separator + filename)));  
 	    stream.write(bytes);  
 	    stream.flush();  
 	    stream.close();  
 	  
 	    
 	   String dateend = null;
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
  	     LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
 	    
 	    
 	    FileDAO.savereport(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
 	    		, folders.getFolder_id(),null,report_id,date,Pathfull,users.getUsername(),dateend);       
    
 	    

		
    }
     
     return "redirect:/report-filemanager?id="+report_id;
  
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/rootgroups-filemanager")
	@ResponseBody
	public String rootgroups(ModelMap model, @RequestParam int id) {
		
 		List<group_authorities> auth =  Group_authoritiesDAO.getall(id, 0);
 		StringJoiner joiner = new StringJoiner(",");
 		for (group_authorities groupAuth : auth) {
 		    joiner.add(groupAuth.getAuthority());
 		}
 		String result = joiner.toString();
		return result;
	}
	
	
	
	@RequestMapping(value="/root2-filemanager")
	public String root2(ModelMap model) {
		
		List<drives> drivess = DrivesDAO.getall();
 		List<folder> folders = FolderDAO.getall2();	
 		
	    model.addAttribute("drivess",drivess);
	    model.addAttribute("folders",folders);
		return "/root2";
	}

	
	
	@RequestMapping(value="/connector-filemanager")
	public String connector(ModelMap model) {
	
	    List<showupload_pn> upload_pns = Upload_pnDAO.getalls2();
	    List<showupload_pn> upload_pns2 = Upload_pnDAO.getalls3();
	    List<showupload_pn> upload_pns3 = Upload_pnDAO.getalls4();
	    List<showupload_pn> upload_pns4 = Upload_pnDAO.getalls5();
	    model.addAttribute("upload_pn",upload_pns);   	
	    model.addAttribute("upload_pn2",upload_pns2); 
	    model.addAttribute("upload_pn3",upload_pns3); 
	    model.addAttribute("upload_pn4",upload_pns4); 
		return "/eformparts/connector";
	}

	
	


	 @RequestMapping(value="/fullsearch",method = RequestMethod.POST)    
	    public String fullsearch( 
	    		@RequestParam(value = "data",required=false)  String data,ModelMap model
	    		,@RequestParam(value = "datestart",required=false) String datestart
	    		,@RequestParam(value = "dateend",required=false) String dateend
	    		) throws Exception
	 {
		  
		 
		 
		 System.out.println("datestart : " +datestart);
		 System.out.println("dateend : " +dateend);
		 
		 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		   group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
		   
			List<file> files = null;
			List<file> files2 = null;
			List<file> files3 = null;
			List<file> files4 = null;
		if(data != null && !data.isBlank()) {
			
	
		   if(datestart != ""   && dateend != "" ) {
			   files  =  FileDAO.search(data,datestart,dateend);
			   files2 = FileDAO.getall2(data,datestart,dateend);
			   files3 = FileDAO.search2(data,datestart,dateend);
			   files4  =  FileDAO.search3(data,datestart,dateend);
		   }else {
			   
			   
			  System.out.println("not null");
			  files  =  FileDAO.search(data); 
			  files2 = FileDAO.getall2(data);
			  files3 = FileDAO.search2(data);
			  files4 = FileDAO.search3(data);
		   }
		  
		}	
			List<showupload_pn> uploadlist = Upload_pnDAO.getall2(); 
			model.addAttribute("uploadlist",uploadlist);  
			
		 
		 
			 MainServices.addNotificationAttributes(model);
		 
			 model.addAttribute("data",data);
			 model.addAttribute("datestart",datestart);
			 model.addAttribute("dateend",dateend);
		 model.addAttribute("file",files);
		 model.addAttribute("file2",files2);
		 model.addAttribute("file3",files3);
		 model.addAttribute("file4",files4);
		 model.addAttribute("groups",group_memberss.getGroup_id());
			return "fullsearch";
	 }
	 
	
	
	

	 @RequestMapping(value="/fullsearch",method = RequestMethod.GET)    
	    public String fullsearchs() throws Exception
	 {
			return "fullsearch";
	 }
	 
	 
	 
	
	@RequestMapping(value="/filemove-filemanager")
	public String filemove(ModelMap model,@RequestParam int id) {

		
		file file = FileDAO.getbyId(id);  
		 MainServices.addNotificationAttributes(model);
		
 		group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
		groups jd = GroupsDAO.getbyIds(group_memberss.getGroup_id());

		
		
 		List<drives> drivess = DrivesDAO.getallbyjd(group_memberss.getGroup_id());
 		List<folder> folders = FolderDAO.getallbyjd(group_memberss.getGroup_id());	
 		
 		model.addAttribute("file",file);
		model.addAttribute("drivess",drivess);
 		model.addAttribute("folders",folders);
		return "filemove";
	}

	
	
	
	
	@RequestMapping(value="/editcharts-filemanager", method=RequestMethod.POST)
	public String editcharts(
			e_report e_reports
			,@RequestParam int upload_pn_id
			,@RequestParam int group
			,@RequestParam int id
			,@RequestParam int step
			)  {
	
	     E_reportDAO.update(e_reports);
	     return "redirect:/duepn-filemanager?id="+id+"&&step="+step+"&&group="+group;
	}
	
	
	@RequestMapping(value="/filemove-filemanager", method=RequestMethod.POST)
	public String filemovesave(ModelMap model,@RequestParam int id,@RequestParam String mainfolder,HttpSession session) throws IOException {

		
		file files = FileDAO.getbyId(id);
           
 
	    
		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
	   	String cleanPath = storagePath.replace("file:///", ""); 

	   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
	   	String path = cleanPath + files.getFile_path(); 
	 	String path2 = cleanPath +  mainfolder; 
	    
	
	    folder folders1 = FolderDAO.getbytype(files.getFile_path());
	    
	    folder folders = FolderDAO.getbytype(mainfolder);
	    Path fileToMovePath = Paths.get(path + File.separator + files.getFile_name());
	    Path targetPath = Paths.get(path2 + File.separator + files.getFile_name());
	    String Pathfull;
	    String Pathfull2;
	    drives drivess;
	    
	    
	    
	    // path back
	    drives olddrives;
	    folder oldfolders = FolderDAO.getbyId(files.getFile_folder());
	    folder oldmainfolders = FolderDAO.getbyId(oldfolders.getFolder_idrefer());
	    int olddrives_id;
	    if(oldmainfolders != null) {
	    olddrives_id= oldmainfolders.getFolder_idrefer();	
	    olddrives  = DrivesDAO.getbyId(olddrives_id);
	    Pathfull2 = olddrives.getDrives_drive()+"/"+oldmainfolders.getFolder_type()+"/"+oldfolders.getFolder_type()+"?id="+oldfolders.getFolder_id();
	    }else {
	    	
	    olddrives_id = folders.getFolder_idrefer();
	    olddrives  = DrivesDAO.getbyId(olddrives_id);
    	Pathfull2 =olddrives.getDrives_drive()+"/"+oldfolders.getFolder_type()+"?id="+oldfolders.getFolder_id();	
	    }
	    // path back
	    
	    
	    
	    
	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
	  
	    
	    
	    int drives_id;

	    
	    if(mainfolders != null) {
	    drives_id= mainfolders.getFolder_idrefer();	
	    drivess  = DrivesDAO.getbyId(drives_id);
	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();

	    }else {
	    	
    	drives_id = folders.getFolder_idrefer();
    	drivess  = DrivesDAO.getbyId(drives_id);
    	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
   
	    	
	    }
	    
	    
          
	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
	    if(files.getFile_size() > totals) {
	    	
	    	 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		   	 String datetime = localDate2.format(formatter3);
		   	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	
	    	
		     	notification  notifications = new notification();
	 			notifications.setNotification_iduser(users.getUser_name());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("⚠ คำเตือน: พื้นที่ดิสก์เหลือน้อย!ไม่เพียงพอ");
				notifications.setNotification_note("");
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_refer("0");
				notifications.setNotification_type("⚠ Warning: Low disk space! Less than remaining. (System Error)");
				NotificationDAO.save(notifications);  
				 return "redirect:/filemanager/"+Pathfull2;
	    }
	
	    
	    

        Files.move(fileToMovePath, targetPath);       	    
		FileDAO.move(id,mainfolder,Pathfull,folders.getFolder_id());
		
		Filemanagerservice.UpdateMemorySize(folders1,files.getFile_size(),0);
		Filemanagerservice.UpdateMemorySize(folders,files.getFile_size(),1);
	    


	    
	    return "redirect:/filemanager/"+Pathfull2;
	}

	
	
	@RequestMapping(value="/setinfo/{Folder}", method=RequestMethod.GET)
	public String setinfo(
			ModelMap model,
			@RequestParam int id, 
			@PathVariable String Folder
			,HttpSession session
			) {
		
		
		folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
		
		folderDTOs.setFolder_id(id);
		folderDTOs.setFoldername(Folder);
		List<upload_pn> upload_pns  = Upload_pnDAO.getall(id);
		folder folders = FolderDAO.getbyId(id);
		
		
		List<workflow> Installed  = WorkflowDAO.getByFolder(folders.getFolder_type());
		String programs = Installed.stream()
			    .map(workflow::getWorkflow_name)
			    .collect(Collectors.joining(", "));
		
		session.setAttribute("programs", programs);
		session.setAttribute("folderDTOs", folderDTOs);
		

		List<group_authorities> ACCESS =  Group_authoritiesDAO.getalltype(Folder);
		model.addAttribute("ACCESS",ACCESS); 
		
		   
  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	    int drives_id;
  	    drives_id = folders.getFolder_idrefer();
  	    if(mainfolders != null) {
  	    drives_id= mainfolders.getFolder_idrefer();	
  	    }
  	    drives drivess  = DrivesDAO.getbyId(drives_id);
  	    String Pathfull ="-";

	    if(mainfolders != null) {

    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
    	  
	    }else {

        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
   	
    	}
		
		
		List<showlog> views =  LogDAO.getallfolder(Pathfull);
		model.addAttribute("views",views); 
		
		
	

		model.addAttribute("idfolder",id); 
		model.addAttribute("upload_pn",upload_pns); 
		model.addAttribute("folder",folders); 
		model.addAttribute("folders",Folder); 
		
		
		 MainServices.addNotificationAttributes(model);
		
		
		return "setinfo";
	}


	@RequestMapping(value="/close_project-filemanager", method=RequestMethod.GET)
	public String setinfo(ModelMap model
			,@RequestParam int id

			) {
		
		 Upload_pnDAO.setstate(1,id);
		 Upload_pnDAO.setRun(2,id);
		 return "redirect:/project-filemanager";
	}
	
	
	
	@RequestMapping(value="/run_project-filemanager", method=RequestMethod.GET)
	public String run_project(ModelMap model
			,@RequestParam int id

			) {
		List<task> task = TaskDAO.getall(id);
		TaskDAO.saveoriginal_task(task);
		 Upload_pnDAO.setRun(1,id);
		 return "redirect:/projectmanager-filemanager?id="+id+"&&step=0&&group=1";
	}
	
	
	
	@RequestMapping(value="/checkupload_pn-filemanager", method=RequestMethod.GET)
	public String setinfo(ModelMap model
			,@RequestParam int ids
			, @RequestParam int state
			,@RequestParam int id
			, @RequestParam String Folder
			) {
		
		 Upload_pnDAO.setstate(state,ids);
		 return "redirect:/setinfo/"+Folder+"?id="+id;
	}
	
	@RequestMapping(value="/stateupload_pn-filemanager", method=RequestMethod.GET)
	public String stateupload_pn(ModelMap model
			,@RequestParam int ids
			, @RequestParam int state
			) {
		
		 Upload_pnDAO.setstate(state,ids);
		 return "redirect:/pntask-filemanager?group=1";
	}
	

	@RequestMapping(value="/addupload_pn-filemanager/{idfolder}")
	public String upload_pn(ModelMap model,@PathVariable int idfolder) {	 
		 MainServices.addNotificationAttributes(model);
		
		
		folder folders = FolderDAO.getbyId(idfolder);
		String authorities = "UPLOAD-"+folders.getFolder_type();
		List<user> users = UserDAO.getallgroup(authorities);
	
		
		List<department>  departments = DepartmentDAO.getall();
		
		List<condition> conditions = ConditionDAO.getall();
		model.addAttribute("conditions",conditions); 
		model.addAttribute("idfolder",idfolder); 
		
		model.addAttribute("department",departments);  
		model.addAttribute("users",users);  
		return "upload_pn";
		
		
		
	}
	@RequestMapping(value="/adddrives-filemanager")
	public String adddrives(ModelMap model) {	
		
		 MainServices.addNotificationAttributes(model);
		
		return "formdrive";
	}
	
	
	
	@RequestMapping(value="/addfolder-filemanager")
	public String addfolder(ModelMap model,@RequestParam int id,HttpSession session) {	
		
		  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
		
	  	 drives drivess = DrivesDAO.getbyId(id);
		 driverDTOs.setDrives_id(drivess.getDrives_id());
		 driverDTOs.setDrives_drive(drivess.getDrives_drive());
		 
		 session.setAttribute("driverDTOs", driverDTOs);
		   
   
		 MainServices.addNotificationAttributes(model);
		
		return "formfolder";
	}
	
	@RequestMapping(value="/addsubfolder-filemanager")
	public String addsubfolder(ModelMap model,@RequestParam int id,@RequestParam int id2,HttpSession session) {
		
		 driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
		 folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
		 
		 drives drivess = DrivesDAO.getbyId(id);
		 driverDTOs.setDrives_id(drivess.getDrives_id());
		 driverDTOs.setDrives_drive(drivess.getDrives_drive());
		 
		 if(id2 > 0) {
		 folder folder = FolderDAO.getbyId(id);
		 folderDTOs.setFolder_id(folder.getFolder_id());
		 folderDTOs.setFolder_name(folder.getFolder_type());
		 }
		 
		 MainServices.addNotificationAttributes(model);
		
		return "formsubfolder";
	}
	
	
	
	@RequestMapping(value="/addfile-filemanager")
	public String addfile(ModelMap model,@RequestParam int id,@RequestParam int level,HttpSession session) {	 
		List<upload_pn> upload_pns  = Upload_pnDAO.getall(id);
		folder floders = FolderDAO.getbyId(id);
 	     LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
		
	   	folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
		folderDTOs.setFolder_subid2(id);
		folderDTOs.setSubfoldername2(floders.getFolder_type());
		
		 MainServices.addNotificationAttributes(model);
		 
		model.addAttribute("idrefer",id);
		model.addAttribute("due",date);
		 model.addAttribute("upload_pn",upload_pns); 
		 model.addAttribute("level",level); 
		return "formupload";
	}
	
	
	
	
	
	@RequestMapping(value="/editdocument-filemanager/{ids}/{uploadid}")
	public String editdocument(ModelMap model,@PathVariable int ids,@PathVariable int uploadid,HttpSession session) {	
		
		LogDAO.setplan(uploadid,ids);
		upload_pn uploads = Upload_pnDAO.getbyId(uploadid);
		int id = uploads.getUpload_pn_idrefer();
		
		List<upload_pn> upload_pns  = Upload_pnDAO.getallbyid(uploadid);

	

		 MainServices.addNotificationAttributes(model);
		
		
		if(uploads.getUpload_pn_monitor() > 0 && uploads.getUpload_pn_monitor() < 100000000) {
			condition conditions = ConditionDAO.getbyid((int)uploads.getUpload_pn_monitor());
			 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
			 model.addAttribute("eformfields",eformfields);  
	
			 
				
		}
		
		
		 edata edatas = EdataDAO.getbyid(ids);
		 
		 
		 List<file> file  = FileDAO.getallEdata(edatas.getData_id()); 
		 System.err.println(" ++++ "+file.size());
		 edatas.setFile(file);
		 model.addAttribute("edatas",edatas); 
		 model.addAttribute("reportid",edatas.getReport_id()); 
		 
		 
	
		model.addAttribute("idrefer",id);
		model.addAttribute("upload_pn",upload_pns); 
		model.addAttribute("upload",uploads); 
		//model.addAttribute("due",logs.getLog_date()); 
		//model.addAttribute("logs",logs); 
	

		
		return "/ajax/editfile";
	}
	
	
	

	@RequestMapping(value="/editdocument2-filemanager/{ids}/{uploadid}")
	public String editdocument2(ModelMap model,@PathVariable int ids,@PathVariable int uploadid,HttpSession session) {	
		

		edata edatas = EdataDAO.getbyid(ids);
		upload_pn uploads = Upload_pnDAO.getbyId(uploadid);
		int id = uploads.getUpload_pn_idrefer();
		
		List<upload_pn> upload_pns  = Upload_pnDAO.getallbyid(uploadid);
	
		
		 MainServices.addNotificationAttributes(model);
		

			condition conditions = ConditionDAO.getbyid((int)uploads.getUpload_pn_monitor());
			
			if(conditions != null) {
			 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
			 model.addAttribute("eformfields",eformfields);  
			}
			 model.addAttribute("edatas",edatas); 
			 
				model.addAttribute("reportid",edatas.getReport_id()); 
	
		
	
		model.addAttribute("idrefer",id);
		model.addAttribute("upload_pn",upload_pns); 
		model.addAttribute("upload",uploads); 

		return "/ajax/editfile2";
	}
	
	
	
	@RequestMapping(value="/addfiles-filemanager")
	public String addfiles(ModelMap model,@RequestParam int id,@RequestParam int level
			,@RequestParam int upload_pn
			,@RequestParam String due
			,HttpSession session
			) {	 
		List<upload_pn> upload_pns  = Upload_pnDAO.getall(id);
		folder folders = FolderDAO.getbyId(id);
		
		folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
	    driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
		
		
		if(folders.getFolder_idrefer() >= 100000){
		
			   drives driver = DrivesDAO.getbyId(folders.getFolder_idrefer());
			   driverDTOs.setDrives_id(driver.getDrives_id());
			   driverDTOs.setDrives_drive(driver.getDrives_drive());
		}else {
			
			folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
			   drives driver = DrivesDAO.getbyId(mainfolders.getFolder_idrefer());
			   driverDTOs.setDrives_id(driver.getDrives_id());
			   driverDTOs.setDrives_drive(driver.getDrives_drive());	
		}
		
		
		upload_pn uploads = Upload_pnDAO.getbyId(upload_pn);
		
		folderDTOs.setFolder_subid2(id);
		folderDTOs.setSubfoldername2(folders.getFolder_type());
		
		
		 MainServices.addNotificationAttributes(model);
		
		if(uploads.getUpload_pn_monitor() > 0 ) {
			
			
			
			condition conditions = ConditionDAO.getbyid((int)uploads.getUpload_pn_monitor());
			 List<eformfield> eformfields = EformfieldDAO.getall(uploads.getUpload_pn_monitor(), 0);
			 List<edata> edatas = EdataDAO.getall(uploads.getUpload_pn_monitor(),conditions.getCondition_rv());
			 
			 
			 model.addAttribute("edatas",edatas);  
			 model.addAttribute("eformfields",eformfields);  
			 

			List<dataset> datasets  = DatasetDAO.getall();
			model.addAttribute("datasets",datasets);  
			model.addAttribute("condition",conditions);  
			model.addAttribute("planid",uploads.getUpload_pn_id());  
			model.addAttribute("eformid",uploads.getUpload_pn_monitor()); 
			model.addAttribute("eformrv",conditions.getCondition_rv()); 
			model.addAttribute("dateplan",uploads.getUpload_pn_date()); 
		    model.addAttribute("plantype",uploads.getUpload_pn_type()); 
		}
		
		  session.setAttribute("driverDTOs", driverDTOs);
		  session.setAttribute("folderDTOs", folderDTOs);
	
		model.addAttribute("idrefer",id);
		model.addAttribute("due",due);
		model.addAttribute("upload_pn",upload_pns); 
		model.addAttribute("upload",uploads);
		model.addAttribute("level",level); 
		return "formupload2";
	}
	
	
	@RequestMapping(value="/addfile-filemanager", method=RequestMethod.POST)
	public String addfile(ModelMap model,@RequestParam int id
			,@RequestParam CommonsMultipartFile file,  
	           HttpSession session,log logs
	           ,@RequestParam int level) throws Exception{ 
		
		

	
	  	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());

	
		 
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType(),filenameoriginal.substring(filenameoriginal.lastIndexOf(".")));
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);

	    
	    
	   // System.out.print(imageFileSize);
	    if(filename == null) {
		 	   model.addAttribute("error","error 08"); 
	 	        return "error";
		    }  
	    
	 	folder folders = FolderDAO.getbyId(id);
	 	
	 	
	 	
		String Pathfull = Filemanagerservice.getLocationName(folders.getFolder_type());
		String Pathfull2 = Filemanagerservice.getLocationPath(folders.getFolder_type());
		 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
		 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	   	 String datetime = localDate2.format(formatter3);
		
		
	    if(filename != null) {
	    	
	    fileDTO fileDTOs = new fileDTO();
	    fileDTOs.setFile1(file);
	    fileDTOs.setFolders(folders);
	    
	    
		boolean  CheckMemorySize  =  Filemanagerservice.CheckMemorySize(fileDTOs);


			if(CheckMemorySize == true) {
			
				   String dateend = null;
				   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
			  	     LocalDateTime localDate = LocalDateTime.now();//For reference
				   	 String date = localDate.format(formatter);
				   	 String time = localDate.format(formatter2);
			 	    
				   	 /*
				 	String UPLOAD_DIRECTORY ="/filesuploads/"+ folders.getFolder_type(); 
				    ServletContext context = session.getServletContext();  
				    String path = context.getRealPath(UPLOAD_DIRECTORY);  
				    */
				    
		

				   	// ... ภายใน Method ที่บันทึกไฟล์ ...
				  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
				   	String cleanPath = storagePath.replace("file:///", ""); 

				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
				   	String path = cleanPath + folders.getFolder_type();

				   	File dir = new File(path);

				   	// 3. เช็คว่ามีโฟลเดอร์นี้อยู่จริงไหม (ต้องสร้างรอไว้ก่อนที่เครื่อง/Server)
				   	if (!dir.exists() || !dir.isDirectory()) {
				   	    // ถ้าไม่มี folder_type นี้อยู่จริง ให้ Error ไปเลย ไม่สร้างใหม่ให้
				   	    System.err.println("❌ ERROR: Folder ไม่ถูกต้องหรือไม่ได้สร้างรอไว้: " + path);
				   	    throw new FileNotFoundException("ระบบไม่พบโฟลเดอร์ปลายทางที่กำหนดไว้ในเงื่อนไข: " + path);
				   	}
				    
				    
				     byte[] bytes = file.getBytes();  
				 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				 	         new File(path + File.separator + filename)));  
				 	    stream.write(bytes);  
				 	    stream.flush();  
				 	    stream.close();  
				   	 
			 	    
			 	    FileDAO.save2(filename,filenameoriginal,file.getContentType(),folders.getFolder_type(), imageFileSize,Size
			 	    		,folders.getFolder_id(),null,"0",date,Pathfull,users.getUsername(),dateend);       
			    
			 	 	log log_old = LogDAO.getLotOld(users.getUser_id());
				   	if(log_old != null) {
				   		logs.setLog_datestart(log_old.getLog_date());
				   		logs.setLog_timestart(log_old.getLog_time());
				   	}  
					logs.setLog_id_add(users.getUser_id());
					logs.setLog_step("UPLOAD");
					logs.setLog_date(date);
					logs.setLog_due("");
					logs.setLog_time(time);
					logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + filename +date);
					logs.setLog_refer("0");
					logs.setLog_folder(Pathfull);
					logs.setLog_file(filename);
					LogDAO.save(logs);
					
					
				    Filemanagerservice.UpdateMemorySize(folders,imageFileSize,1);
			}else {
				notification  notifications = new notification();
 	 			notifications.setNotification_iduser(users.getUser_name());
 				notifications.setNotification_iduser2(null);
 				notifications.setNotification_alert("⚠ คำเตือน: พื้นที่ดิสก์เหลือน้อย!ไม่เพียงพอ");
 				notifications.setNotification_note("");
 				notifications.setNotification_date(datetime);
 				notifications.setNotification_read(0);
 				notifications.setNotification_refer("0");
 				notifications.setNotification_type("⚠ Warning: Low disk space! Less than remaining. (System Error)");
 				NotificationDAO.save(notifications);  
			}
		
		}

		if(level == 2 ) {
			   return "redirect:/filemanager/"+Pathfull2+"?id="+id;
		}else {
			   return "redirect:/filemanager/"+Pathfull2+"?id="+id;
		}
		
  
	}
	
	
		
	@RequestMapping(value="/addfiles-filemanager", method=RequestMethod.POST)
	public String addfiles(ModelMap model,@RequestParam int id,
			@RequestParam String upload_pn,
			@RequestParam int upload_pn_ids,
			@RequestParam String due,
			@RequestParam (required=false) CommonsMultipartFile file,  
			edata edatas,  
	           HttpSession session,log logs
	           ,@RequestParam int level) throws Exception{ 
		
	
	
	 	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 upload_pn upload_pns = Upload_pnDAO.getbyId(upload_pn_ids);
		 folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
		 
		 
			System.out.println("bug1");
		 report_pn report_pns = Report_pnDAO.getbyId2(upload_pns.getUpload_pn_id(),due);
			System.out.println("bug2");
		 
		 
		 String dateend = null;
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	     LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
	   	 String newfilename2 =null;
	     
	 	System.out.println("bug3");
		 
	     

		if(file.getSize()  > 0) {
		
		 	System.out.println("bug4");
			int upload_pnid =  Integer.parseInt(upload_pn.split("-")[0]);
			int upload_pnsubid =  Integer.parseInt(upload_pn.split("-")[1]);
		
		
	 
		
		
	
	    
		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
	   	String cleanPath = storagePath.replace("file:///", ""); 

	   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
	   	String path = cleanPath +   folders.getFolder_type(); 
	    
	    
	    String filenameoriginal = file.getOriginalFilename();  
	    
	    String fileExtension = filenameoriginal.substring(filenameoriginal.lastIndexOf("."));
	    String filename = Fileservice.assertEquals(file.getContentType(),fileExtension);
	    String typereal = file.getContentType(); 
	    
	    if(file.getContentType().equals("application/octet-stream") ) {
	    	if(!fileExtension.equals(".pdf")){
	    		typereal = "image/jpg";
	    	}
	    }
	    
	    
	 	System.out.println("bug4" +file.getContentType());
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);
	    
	    
	    
	    if(filename == null) {
	 	   model.addAttribute("error","error 08"); 
 	        return "error";
	    }
    	 
    
  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
  	    int drives_id;
  	    drives_id = folders.getFolder_idrefer();
  	    if(mainfolders != null) {
  	    drives_id= mainfolders.getFolder_idrefer();	
  	    }
  	    drives drivess  = DrivesDAO.getbyId(drives_id);
            
  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
  	    if(imageFileSize > totals) {
  	    	
  	            model.addAttribute("error","error 02"); 
  	            return "error";
  	    }
  	    
  	    file sum = FileDAO.getsum(folders.getFolder_type());
	    long imageFileSize2 = sum.getFile_size() +imageFileSize;
	    String Size2 = Fileservice.printFileSize(imageFileSize2);
	    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
	    String Pathfull;
	    
	    if(mainfolders != null) {
	    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
    	    long imageFileSize3 = sum2.getFolder_use();
    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
	    }else {
	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
    	    	
    	    }
    	          	    
	    
	    
    	folder sum3 = FolderDAO.getsum(drives_id);
	    long imageFileSize3 = sum3.getFolder_use();
	    double percent;
	    if(imageFileSize3 > 0) {
	       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
	    }else {
	    	 percent =0;	
	    }
	    String Size4 = Fileservice.printFileSize(imageFileSize3);
	    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
  	    
  	    
    	 
 	    byte[] bytes = file.getBytes();  
 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 	         new File(path + File.separator + filename)));  
 	    stream.write(bytes);  
 	    stream.flush();  
 	    stream.close();  
 	  
 	    
  
	     
	  
	     if(Integer. valueOf(upload_pns.getUpload_pn_end()) == 1 ) {
	   	      LocalDateTime localDate2 = LocalDateTime.now().plusMonths(6);
	   	     dateend = localDate2.format(formatter);
	     }
	    else if(Integer. valueOf(upload_pns.getUpload_pn_end()) == 1) {
	      	 	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(1);
	      	     dateend = localDate2.format(formatter);
   	 }  
	     else if(Integer. valueOf(upload_pns.getUpload_pn_end()) == 3 ) {
	   	    	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(3); 
	   	    	  dateend = localDate2.format(formatter);
	     }
	     else if(Integer. valueOf(upload_pns.getUpload_pn_end()) ==4 ) {
		   	 	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(5); 
		   	      dateend = localDate2.format(formatter);
	     }  
	     

 	    

 	   if(upload_pnsubid == 1 ) {
 		  newfilename2 = upload_pns.getUpload_pn_name();
 		  
    	}
    	else if(upload_pnsubid == 2 ) {
    		newfilename2 = upload_pns.getUpload_pn_name2();
    	}
    	else if(upload_pnsubid == 3 ) {
    		newfilename2 = upload_pns.getUpload_pn_name3();
    	}
    	else if(upload_pnsubid == 4 ) {
    		newfilename2 = upload_pns.getUpload_pn_name4();
    	}
    	else if(upload_pnsubid == 5 ) {
    		newfilename2 = upload_pns.getUpload_pn_name5();
    	}
    	
 	    
 	     int report_pnid = 0;
 	    String newfilename = newfilename2+" โดย "+users.getUser_name();
 	    
 	    
 	    if(upload_pns.getUpload_pn_namefile() == 1) {
 	    	newfilename = " โดย "+users.getUser_name();
 	    }else if(upload_pns.getUpload_pn_namefile() == 3) {
 	    	newfilename = filenameoriginal;
 	    }else if(upload_pns.getUpload_pn_namefile() ==4) {
 	    	newfilename = newfilename2;
 	    }
 	    
 	    
 	    if(report_pns != null) {
 	    	 report_pnid =report_pns.getReport_pn_id();
 	    	 Report_pnDAO.setdateadd(report_pnid,date);
 	    }
 	    
	 	System.out.println("bug5"); 
 	    FileDAO.save2(filename, newfilename, 
 	    		typereal,folders.getFolder_type(), imageFileSize,Size, folders.getFolder_id()
 	    		,null,String.valueOf(report_pnid),date,Pathfull,users.getUsername(),dateend);       
 	   
 	   

		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("UPLOAD");
		logs.setLog_date(date);
		logs.setLog_due(due);
		logs.setLog_time(time);
		logs.setLog_note("อัพโหลดไฟล์โดยตรงชื่อเอกสาร  " + newfilename2 +date);
		logs.setLog_refer(String.valueOf(upload_pns.getUpload_pn_id()));
		logs.setLog_folder(Pathfull);
		logs.setLog_file(filename);
		logs.setLog_dive(drivess.getDrives_drive());
		int idlogs = LogDAO.saves(logs);
		
		System.out.println(filename);
		
		if(!newfilename2.isBlank()) { edatas.setData_filetype(newfilename2);}
		edatas.setData_filepath(String.valueOf(idlogs));

	}
		
	  
	if(report_pns != null) {	
					Date date1 = myFormat.parse(due);
				    Date date2 = myFormat.parse(date);
					int diff = Dateservice.getDiffDays(date1,date2);
			
					int status =0;
					if(date != due) {
						
						if(diff >1) {
							diff = diff-1;
							status =1;
						}else {
							diff =0;
							
						}
						 Report_pnDAO.setStatus(report_pns.getReport_pn_id(),2,status,diff);
					}else {
						 Report_pnDAO.setStatus(report_pns.getReport_pn_id(),2,0,0);
					}
					
					if(report_pns.getReport_pn_total()+1 <=  report_pns.getReport_pn_target()) {
				      Report_pnDAO.settotal(report_pns.getReport_pn_id());
					}
				      
				      
		                  
							if(upload_pns.getUpload_pn_type() != 2  ) {
								 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
								 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							   	 String datetime = localDate2.format(formatter3);
							   
								notification  notifications = new notification();
								notifications.setNotification_iduser(upload_pns.getUpload_pn_app());
								notifications.setNotification_iduser2(null);
								notifications.setNotification_alert("คุณมีเอกสารบันทึกคุณภาพ --"+newfilename2+" รอการตรวจสอบ");
								notifications.setNotification_note("ผู้รับผิดชอบ : "+upload_pns.getUpload_pn_respon());
								notifications.setNotification_date(datetime);
								notifications.setNotification_read(0);
								notifications.setNotification_refer(Integer.toString(report_pns.getReport_pn_id()));
								notifications.setNotification_type("Job queue");
								NotificationDAO.save(notifications);  
							}
		  }
		

 	System.out.println("bug6"); 
	   if(edatas.getData_planid() >  0) {
		   
		   edatas.setData_idadd(users.getUser_id());
		   edatas.setData_nameadd(users.getUser_name());
		   edatas.setData_dateadd(date+" "+time);
		   EdataDAO.save(edatas);
	   }
	   
	 
	    return "redirect:/addfiles-filemanager?id="+id+"&&upload_pn="+upload_pn_ids+"&&level="+level+"&&due="+due;
			
  }
	
	
	
	
	
	
	
	
		private boolean verification(ModelMap model,Register_main register_main,int Workflow_id) {

			 boolean verification = true;  
							if(register_main.getProduct() != null  &&  register_main.getAction_product().equals("verification")  ) {
				
									 verification =  ConditionDAO.getbycontrol_steps(register_main.getProduct(),"product",Workflow_id);
									model.addAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_PD.name()); 
								 }
							
							
							if(register_main.getBatch() != null  &&  register_main.getAction_batch().equals("verification")  ) {
								 verification =  ConditionDAO.getbycontrol_steps(register_main.getBatch(),"batch",Workflow_id);
									model.addAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_BATCH.name()); 
			
								}

								 if(register_main.getProduction_directive() != null && register_main.getAction_production_directive().equals("verification") ) {
									
									 
									  verification =  ConditionDAO.getbycontrol_steps(register_main.getProduction_directive(),"production_directive",Workflow_id);
						
										model.addAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_production_Null.name()); 
						
							    }
								 if(register_main.getMaterial() != null  && register_main.getAction_material().equals("verification")) {
									  verification =  ConditionDAO.getbycontrol_steps(register_main.getProduction_directive(),"material",Workflow_id);
	
										model.addAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_material_Null.name()); 
								
							    }
								 if(register_main.getCustomer() != null  && register_main.getAction_customer().equals("verification")) {
									  verification =  ConditionDAO.getbycontrol_steps(register_main.getProduction_directive(),"customer",Workflow_id);
									 	
										model.addAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_customer_Null.name()); 
							    }
								 if(register_main.getPart() != null  && register_main.getAction_customer().equals("verification")) {
									  verification =  ConditionDAO.getbycontrol_steps(register_main.getProduction_directive(),"part",Workflow_id);
									 	
										model.addAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_customer_Null.name()); 
							    }
								 
								 return verification;
							
		}
	
	
	
	
	
	int loop = 0;
	@RequestMapping(value="/addfiles2-filemanager", method=RequestMethod.POST)
	public String addfiles2(ModelMap model,
			@RequestParam int id,
			@RequestParam int upload_pn_ids,
			@RequestParam (required=false,name="due") String due,
			edata edatas,  
			fileDTO fileDTOs,  
			@RequestParam  (required=false) int log_id,
			@RequestParam  (required=false) String form_status,
			
			
			
			Register_main register_main,
			
			
			/*
			@RequestParam  (required=false,name="production_directive") String production_directive,
			@RequestParam  (required=false,name="product") String product,
			@RequestParam  (required=false,name="product_category") String product_category,
			@RequestParam  (required=false,name="unit") String unit,
			@RequestParam  (required=false,name="batch") String batch,
			@RequestParam  (required=false,name="material") String material,
			@RequestParam  (required=false,name="customer") String customer,
			@RequestParam  (required=false,name="part") String part,
			@RequestParam  (required=false,name="action_production_directive",defaultValue="-") String action_production_directive,
			@RequestParam  (required=false,name="action_batch",defaultValue="-") String action_batch,
			@RequestParam  (required=false,name="action_material",defaultValue="-") String action_material,
			@RequestParam  (required=false,name="action_customer",defaultValue="-") String action_customer,
			@RequestParam  (required=false,name="action_product",defaultValue="-") String action_product,
			@RequestParam  (required=false,name="action_part",defaultValue="-") String action_part,
			*/

			@RequestParam  (required=false,defaultValue = "0") int register,
			RedirectAttributes redirectAttributes,
	        HttpSession session,
	        log logs,
	        HttpServletRequest request
	       ,@RequestParam int level, 
	       edata_stock edata_stock 
			) throws Exception{ 

		    
		
	
          //เช็คข้อผิดพลาด----------------
					user users = UserDAO.getbyusername2(Userservice.getLoggedName());
					upload_pn upload_pns = Upload_pnDAO.getbyId(upload_pn_ids);
				 if(upload_pns == null) {
					 			return "/ajax/not";
				 	}else {
				 		 model.addAttribute("condition_id",upload_pns.getUpload_pn_monitor()); 
				 	}
			 	 folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
				 	if(folders == null) {
				 		return "redirect:/pntask-filemanager?group=1";
				 	} 
		//------------------------  
		  
				
				 	
				 	
		 //เช็คค่าหลัก----------------	 	
			 workflow workflow =  WorkflowDAO.getApp(edatas.getData_eformid());
			 if(workflow != null) { 
				 
				 
				 boolean verification =  verification(model,register_main,workflow.getWorkflow_id()); 
			 
				  if(verification == false) {
						
					 	return "ajax/404";
				    }
			 }
		 //------------------------   
			 
			 
			 
		 
		//กระบวนการจัดเตรียมข้อมูล -----------------------------------------
	    driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");	 
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formattered = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	     LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
	   	 String datetime = localDate.format(formattered);
	   	 

	   	 
	   	 
	   	fileDTOs.setDate(date);
	   	fileDTOs.setSession(session);
	   	fileDTOs.setUpload_pn(upload_pns);
	   	fileDTOs.setUser(users);
	   	fileDTOs.setFolders(folders);

	   
		condition condition =  ConditionDAO.getbyid(edatas.getData_eformid());
		model.addAttribute("condition",condition); 
		
		  int ck =  EdataDAO.saveCheck(users.getUser_id(),datetime);

		  
		if(edatas.getData_id() > 0) {
	   		edata edata = EdataDAO.getbyid(edatas.getData_id());
			Filemanagerservice.setAllNameFile(edatas.getData_filetype());
			Filemanagerservice.setAllRealnameFile(edata.getLog_file());
			log_id = edata.getLog_id();
			
			
			   edatas.setData_idadd( edata.getData_idadd());
			   edatas.setData_nameadd(edata.getData_nameadd());
			   edatas.setData_dateadd(edata.getData_dateadd());
			   edatas.setData_due(edata.getData_due());   
			
			
			model.addAttribute("status_action","edit"); 
			
			
			
			
	   	}else{
			   edatas.setData_idadd(users.getUser_id());
			   edatas.setData_nameadd(users.getUser_name());
			   edatas.setData_dateadd(date+" "+time);
			   edatas.setData_due(due);
			   
		   }	 
	    ///---------------------------	
	
		
		//เช็กไม่มีการเพิ่ม 2 ครั้ง------------------------------------- 
		if(ck == 0) {
				//เพิ่มข้อมูล------------------------------------- 
			   edatas.setData_dateplan("-"); 
			   edatas.setData_department(upload_pns.getUpload_pn_department());
			   
			   
			   int eformId =  EdataDAO.save(edatas);
			   
			   
			   if(edatas.getData_id()  < 1 ) {
				   edatas.setData_id(eformId); 
				   edata_stock.setEdata_id(eformId); 
				   }else {
					edata_stock.setEdata_id(edatas.getData_id());	   
					edatas.setTempId(String.valueOf(edatas.getData_id()));
					
			   }
			   model.addAttribute("tempId",edatas.getData_id());  
			  
			   //---------------------------------------------
			   // ลงทะเบียนข้อมูลหลัก-------------------------------------
			    if(workflow != null) {  Filemanagerservice.Register_main(register_main,workflow.getWorkflow_id(),eformId);}
				//---------------------------------------------
			 
		    	datalink key = WorkflowDAO.getDataLinkById(edatas.getData_eformid());
		    	
		    	
		    	
		    	/*
		    	WorkSystemdata WorkSystemdata = new WorkSystemdata();
		    	WorkSystemdata.setEdata_stock(edata_stock);
		    	WorkSystemdata.setDatalink_name(key.getDatalink_name());
		    	
		    	SubData subdatas = new SubData();
		    	subdatas.setTempId(edatas.getTempId());
		    	
		    	WorkSystemdata.setSubdata(subdatas);
		    	WorkSystemdata.setSession(session);
		    	
		    	//เพื่ม APP ได้เรื่อยๆๆ
			    Filemanagerservice.Register(key.getDatalink_name(),WorkSystemdata,redirectAttributes);  
			    */
			
				    	
			   
		} 
			   
			   
	   	
		//บันทึกข้อมูล------------------------------------- 
		fileDTOs.setEdata_id(edatas.getData_id());
	   	Filemanagerservice.Filemanager(fileDTOs);
	    String newfilenames2 = Filemanagerservice.getAllNameFile();
		String filenames = Filemanagerservice.getAllRealnameFile();
		String Pathfull = Filemanagerservice.getPathfull();
		int file_status = Filemanagerservice.getFile_status();
	 
	
	     model.addAttribute("log_id",log_id); 
		
	     
	     int idlogs;
		
		if(file_status == 1 ) {	
					 	log log_old = LogDAO.getLotOld(users.getUser_id());
					   	if(log_old != null) {
					   		logs.setLog_datestart(log_old.getLog_date());
					   		logs.setLog_timestart(log_old.getLog_time());
					   	}
						logs.setLog_id_add(users.getUser_id());
						logs.setLog_step("UPLOAD");
						logs.setLog_date(date);
						logs.setLog_due(due);
						logs.setLog_time(time);
						logs.setLog_note(null);
						logs.setLog_refer(String.valueOf(upload_pns.getUpload_pn_id()));
						logs.setLog_folder(Pathfull);
						logs.setLog_file(filenames);
						
						if(driverDTOs != null) {
							logs.setLog_dive(driverDTOs.getDrives_drive());
						}
						logs.setLog_plan(upload_pns.getUpload_pn_id());
						logs.setLog_id(log_id);
						idlogs = LogDAO.saves(logs);
						edatas.setData_filetype(newfilenames2);
						edatas.setData_filepath(String.valueOf(idlogs));		
						
			 }else {
				 	log log_old = LogDAO.getLotOld(users.getUser_id());
				   	if(log_old != null) {
				   		logs.setLog_datestart(log_old.getLog_date());
				   		logs.setLog_timestart(log_old.getLog_time());
				   	}
				 logs.setLog_id_add(users.getUser_id());
					logs.setLog_step("UPLOAD");
					logs.setLog_date(date);
					logs.setLog_due(due);
					logs.setLog_time(time);
					logs.setLog_refer(String.valueOf(upload_pns.getUpload_pn_id()));
					logs.setLog_id(log_id);
					idlogs = LogDAO.saves(logs);
					edatas.setData_filepath(String.valueOf(idlogs));
			 }
	   log logsave = LogDAO.getbyId(idlogs);
	   if(logsave.getLog_file() != null) {model.addAttribute("logsave",logsave); }
	   //---------------------------------------------	   
	   
	   
	   
		//เเสดงผลค่า-------------------------------------  
	   
	   System.err.print("edatas "+edatas.getData_id());
	   
	   edata edatasave=   EdataDAO.getbyid(edatas.getData_id());
		
			   
	   
	   List<eformfield> eformfields = EformfieldDAO.getall(upload_pns.getUpload_pn_monitor(), 0); 
	   model.addAttribute("eformfields",eformfields);  
 
	   List<file> file = FileDAO.getallEdata(edatasave.getData_id());
	   edatasave.setFile(file);
	   model.addAttribute("edatasave",edatasave); 
	   model.addAttribute("Member_name",users.getUser_name()); 
	   model.addAttribute("idadd",users.getUser_id()); 
	   model.addAttribute("upload_pn",upload_pns); 
	   //---------------------------------------------	 
	   
	   
	   if(form_status != null) { 
		 	redirectAttributes.addFlashAttribute("edata_old", edatas);
		 	redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
			return "redirect:/editdocumentapp-filemanager/"+edatasave.getData_id()+"/"+upload_pn_ids;
	   }else {
		   return "/ajax/savefile";	 
	   }
	
	   
	   
	 
  }
	

	
	@RequestMapping(value="/savesubtable-filemanager", method=RequestMethod.POST)
	public String savesubtable(
			ModelMap model
			,Platform platform
			,RedirectAttributes redirectAttributes
			,HttpSession session
			){ 

			LoadApp   LoadApp =   new LoadApp();
		Object SubData = session.getAttribute("SubData"+platform.getApp_tempId());
	    int eformId = platformService.initiate(LoadApp)
	    		.setEntity(null,platform)
	    		.child(SubData)
	    		.save();
	    	redirectAttributes.addFlashAttribute("save","save");
	    	return "redirect:/subtable-filemanager/"+eformId+"/"+platform.getApp_processes_id()+"/"+platform.getApp_entity_type();

		}
	
	
	
	
	@RequestMapping(value="/saveapp-filemanager", method=RequestMethod.POST)
	public String saveapp(
			ModelMap model
			,Platform platform
			,RedirectAttributes redirectAttributes
			,HttpSession session
	        ,MultipleModelsDTO multipleModelsDTO 
			){ 

		    try {
				LoadApp   LoadApp =   new LoadApp();

			   int eformId = platformService.initiate(LoadApp).setEntity(multipleModelsDTO,platform).save();
			    redirectAttributes.addFlashAttribute("save","save");
				return "redirect:/editdocumentapp-filemanager/"+eformId+"/"+platform.getApp_processes_id();
		    }catch(Exception e) {
		    	System.out.println("Exception : "+e);
		    	return null;
		    }
				
			
		}
	
	
	
	
	
	@RequestMapping(value="/Levenshtein-filemanager/{id}",method = RequestMethod.GET)
	public String Levenshtein(ModelMap model,@PathVariable int id){

		List<levenshtein> levenshtein  = Dataset_dataDAO.getAllLevenshteins(id);
		 model.addAttribute("levenshtein",levenshtein); 
		return  "app/windows/levenshtein";
		
	}   
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/steplog-filemanager/{id}/{id_refer}",method = RequestMethod.GET)
	public String steplog(ModelMap model,@PathVariable int id,@PathVariable int id_refer){

	   	List<flow> log = WorkflowDAO.getAllFlow(id);
    	log.forEach(row->{
    		user send = UserDAO.getbyId(row.getFlow_send(), 0);
    		if(send!= null) { row.setSend(send.getUser_name());}
    		
    		
    		user to = UserDAO.getbyId(row.getFlow_to(), 0);
    		if(to != null) {row.setTo(to.getUser_name());}
    		
    	});
    	
    	 Idoc flow =  idocDAO.getDocument(id);
    	
    	 model.addAttribute("log",log); 
    	 model.addAttribute("flow",flow); 
    	 model.addAttribute("id_refer",id_refer); 
		return  "app/windows/step";
		
	}   


	
	@RequestMapping(value="/auditLog-filemanager/{Code}",method = RequestMethod.GET)
	public String auditLog(ModelMap model,@PathVariable String  Code){

		List<Audit> audit = EdataDAO.getAllAudit(Code);
		 model.addAttribute("audit",audit); 
		return  "app/windows/auditLog";
	}   
	@RequestMapping(value="/versioning-filemanager/{id_refer}/{process_id}",method = RequestMethod.GET)
	public String versioning(ModelMap model,@PathVariable int  id_refer,@PathVariable int  process_id){

		
		
		List<DocumentEffect> versioning = EdataDAO.getAllDocumentEffect(id_refer);
		 model.addAttribute("versioning",versioning); 
		 model.addAttribute("process_id",process_id);
		return  "app/windows/versioning";
	}   
	
	
	
	
	@RequestMapping(value="/Comparedata-filemanager/{edata_id}/{id_refer}",method = RequestMethod.GET)
	public String Comparedata(ModelMap model,@PathVariable int id_refer,@PathVariable int edata_id){

		
		
		edata edatas = EdataDAO.getbyid(edata_id);
		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		platform.setEdata(edatas);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).loadPlatForm(platform).load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		

		return  "app/windows/Comparedata";
		

		
	} 
	
	
	@RequestMapping(value="/Comparedata2-filemanager/{data_type}/{id}/{id_refer}",method = RequestMethod.GET)
	public String Comparedata2(ModelMap model,@PathVariable int id,@PathVariable int id_refer,@PathVariable String data_type){

		
	
		Platform platform = new Platform();
		platform.setApp_item_id(id);
		platform.setApp_item_id2(id_refer);
		platform.setApp_entity_type(data_type);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).initPlatformComponents(platform).Comparedata();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		

		return  "app/windows/Comparedata";
		

		
	} 
	
	
	
	
	
	@RequestMapping(value="/setwindow-filemanager/{id_refer}",method = RequestMethod.GET)
	public String setwindow(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).tabForm().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		
		return  "app/windows/setwindow";
		
	}   
	
	
	
	@RequestMapping(value="/setsetRenamefile-filemanager/{id_refer}",method = RequestMethod.GET)
	public String setsetRenamefile(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).workFlowSetting().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		model.addAttribute("config_type","renamefile");
		return  "app/windows/setWorkflowAndField";
	} 
	
	
	
	
	@RequestMapping(value="/setExtension-filemanager/{id_refer}",method = RequestMethod.GET)
	public String setExtension(ModelMap model,@PathVariable int id_refer){

		process process=  EdataDAO.getProcess(id_refer);
		List<process> appprocess  = EdataDAO.getAllProcessBytype(2);
		model.addAttribute("appprocess",appprocess);
		model.addAttribute("process",process);
		
		if(process.getUse_index_app() > 0) {
			  List<UseExtension> extension = EdataDAO.getallUseExtension(process.getUse_index_app());
			  model.addAttribute("extension",extension);
		}
		
		
		
		model.addAttribute("config_type","extension");
		return  "app/windows/setWorkflowAndField";
	} 
	
	
	
	@RequestMapping(value="/saveconfigExtension-filemanager",method = RequestMethod.POST)
	public String saveconfigExtension(process process){

		System.out.println(" process "+process.getId());
		System.out.println(" Use_index_app "+process.getUse_index_app());

		
		EdataDAO.saveconfigExtension(process);
		return  "ajax/save";
	} 
	
	
	
	
	@RequestMapping(value="/savesetRenamefile-filemanager",method = RequestMethod.POST)
	public String setRenamefile(
			@RequestParam (required=false) int renamefile,
			@RequestParam int process_id){
		
		
		
		PlatformConfig platform = new PlatformConfig();
		platform.setProcesse_id(process_id);
		process process = new process();
		process.setId(process_id);
		process.setRenamefile(renamefile);
		platform.setProcess(process);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyConfig(platform).saveRenamefile();
		return  "ajax/save";
		
	}   
	
	

	@RequestMapping(value="/setRevision-filemanager/{id_refer}",method = RequestMethod.GET)
	public String setRevision(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).workFlowSetting().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		model.addAttribute("config_type","revision");
		return  "app/windows/setWorkflowAndField";
	} 
	@RequestMapping(value="/saveRevision-filemanager",method = RequestMethod.POST)
	public String saveRevision(
			@RequestParam (required=false) String closingStatusCanRevise,
			@RequestParam (required=false) String closingStatusAfterRevise
			,@RequestParam (required=false) String closingStatusAfterAdd
			,@RequestParam (required=false) int canReviseConfirmed
			,@RequestParam int process_id){
		
		
		
		PlatformConfig platform = new PlatformConfig();
		platform.setProcesse_id(process_id);
		process process = new process();
		process.setId(process_id);
		process.setCanReviseConfirmed(canReviseConfirmed);
		process.setClosingStatusAfterRevise(closingStatusAfterRevise);
		process.setClosingStatusCanRevise(closingStatusCanRevise);
		process.setClosingStatusAfterAdd(closingStatusAfterAdd);
		
		
		
		platform.setProcess(process);
		
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyConfig(platform).saveConfigRequestRevision();

		
		return  "ajax/save";
		
	}   
	
	
	
	
	
	
	
	@RequestMapping(value="/setworkflow-filemanager/{id_refer}",method = RequestMethod.GET)
	public String setworkflow(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).workFlowSetting().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		model.addAttribute("config_type","workflow");
		return  "app/windows/setWorkflowAndField";
		

		
	} 
	
	@RequestMapping(value="/configDataset-filemanager/{id_refer}",method = RequestMethod.GET)
	public String configDataset(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).workFlowSetting().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);

		model.addAttribute("config_type","dataset");
		
		return  "app/windows/setWorkflowAndField";

		
	} 
	
	@RequestMapping(value="/configDataAccessInterface-filemanager/{id_refer}",method = RequestMethod.GET)
	public String configDataAccessInterface(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).workFlowSetting().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);

		model.addAttribute("config_type","dataaccessinterface");
		
		return  "app/windows/setWorkflowAndField";
		
	} 
	
	@RequestMapping(value="/saveconfigDataAccessInterface-filemanager",method = RequestMethod.POST)
	public String saveconfigDataAccessInterface(@RequestParam String data_access_interface,@RequestParam String field_id,@RequestParam int process_id){
		
		
		LoadApp   LoadApp =   new LoadApp();
		PlatformConfig platform = new PlatformConfig();
		platform.setConfig(data_access_interface);
		platform.setConfig_id(field_id);
		platform.setProcesse_id(process_id);
		platformService.initiate(LoadApp).applyConfig(platform).saveAddDataAccessInterface();

		
		return  "ajax/save";
		
	}   
	
	
	
	@RequestMapping(value="/QueryChain-filemanager",method = RequestMethod.GET)
	public String QueryChain(ModelMap mod){
		/*
		LoadApp   LoadApp =   new LoadApp();
		PlatformReport platform = new PlatformReport();
		platform.setApp_item_id(id);
		platformService.initiate(LoadApp).startAppReport(platform).prepareQueryChain().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		*/
		return  "app/JerryMouseBI/querychain";
		
	}   
	
	
	@RequestMapping(value="/saveQuickQueryChain-filemanager",method = RequestMethod.POST)
	public String saveQuickQueryChain(ModelMap model,@RequestParam int process_id,@RequestParam int config_id,QuickQuery quickQuery){
		
		
		PlatformReport platform = new PlatformReport();
		platform.setQuickQuery(quickQuery);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).startAppReport(platform).saveQuickQuery();

		
		return  "app/windows/querychain";
		
	}   
	
	@RequestMapping(value="/saveClosing_status-filemanager",method = RequestMethod.POST)
	public String saveClosing_status(
			@RequestParam (required=false) String closing_status,
			@RequestParam (required=false) String closing_status_expired,
			@RequestParam (required=false,defaultValue="0") int expires_in_days,
			@RequestParam (required=false,defaultValue="0") int is_acknowledge_required,
			
			@RequestParam (required=false,defaultValue="0") int can_refuse_and_return,
			@RequestParam (required=false,defaultValue="-") String is_attachment_required_to_close,
			@RequestParam (required=false,defaultValue="-") String closing_evidence_type,
			@RequestParam (required=false,defaultValue="-") String sub_process_expired,
			@RequestParam int process_id){
		

		
		
		PlatformConfig platform = new PlatformConfig();
		platform.setConfig(closing_status);
		
		platform.setClosing_status_expired(closing_status_expired);
		platform.setExpires_in_days(expires_in_days);
		platform.setIs_acknowledge_required(is_acknowledge_required);
		platform.setCan_refuse_and_return(can_refuse_and_return);
		platform.setIs_attachment_required_to_close(is_attachment_required_to_close);
		platform.setClosing_evidence_type(closing_evidence_type);
		platform.setSub_process_expired(sub_process_expired);
		
		platform.setProcesse_id(process_id);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyConfig(platform).saveClosing_status();

		
		return  "ajax/save";
		
	}   
	
	
	@RequestMapping(value="/saveAddconfigDataset-filemanager",method = RequestMethod.POST)
	public String saveAddconfigDataset(@RequestParam String allow_adding_items,@RequestParam String field_id,@RequestParam int process_id){
		
		
		
		PlatformConfig platform = new PlatformConfig();
		platform.setConfig(allow_adding_items);
		platform.setConfig_id(field_id);
		platform.setProcesse_id(process_id);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyConfig(platform).saveAddconfigDataset();

		
		return  "ajax/save";
		
	}   
	
	

	@RequestMapping(value="/saveUseformPrint-filemanager",method = RequestMethod.POST)
	public String saveUseformPrint(UseformPrint UseformPrint,@RequestParam int data_id){
		  
			user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			UseformPrint.setUser_id_select(users.getUser_id());
			UseformPrint.setUse_status(0);
			
			EdataDAO.saveUseformPrint(UseformPrint);
			return "redirect:/setprint-filemanager/"+UseformPrint.getProcess_id()+"/"+data_id;
	}   
	


	
	
		
		
	@RequestMapping(value="/QuickQueryChain-filemanager",method = RequestMethod.GET)
	public String QuickQueryChain(){
		
		return  "app/JerryMouseBI/QuickQueryChain";
		
	}   
	

	
	
	@RequestMapping(value="/QuickQueryChain2-filemanager/{process_id}/{segmentation_id}",method = RequestMethod.GET)
	public String QuickQueryChain2(ModelMap model,@PathVariable int process_id,@PathVariable int segmentation_id){
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		SetSegmentView SegmentView = EdataDAO.getSetSegmentView(process_id,usersed);
		
		System.out.println(" SegmentView "+SegmentView.getId());
		
		model.addAttribute("process_id",process_id); 
		model.addAttribute("segmentation_id",segmentation_id); 
		
	 	model.addAttribute("SegmentView",SegmentView); 
		return  "app/JerryMouseBI/QuickQueryChain2";
		
	}   
	
	
	@RequestMapping(value="/visuals-filemanager",method = RequestMethod.GET)
	public String visuals(){
		
		return  "app/JerryMouseBI/visuals";
		
	}   
	
	
	
	@RequestMapping(value="/setprint-filemanager/{id_refer}/{data_id}",method = RequestMethod.GET)
	public String setprint(ModelMap model,@PathVariable int id_refer,@PathVariable int data_id){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).input().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);

    	List<FormPrint> FormPrint = EdataDAO.getAllFormPrint(id_refer);
 	   	model.addAttribute("listForm",FormPrint); 
 	   	
 	   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
 	   UseformPrint UseformPrint = EdataDAO.getUseformPrint(id_refer,users.getUser_id());
	   	model.addAttribute("UseformPrint",UseformPrint); 
	  	model.addAttribute("data_id",data_id); 
		return  "app/windows/setprint";
		
	}   
	
	@RequestMapping(value="/addtab-filemanager/{id_refer}",method = RequestMethod.GET)
	public String addtab(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).tabFormItem().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		

		return  "app/windows/addtab";
		
	}   
	
	
	@RequestMapping(value="/addInputHiddenVisibility-filemanager/{id_refer}",method = RequestMethod.GET)
	public String addInputHiddenVisibility(ModelMap model,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);	
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).tabFormItem().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);		
		

		dataset datasets  = DatasetDAO.getMain("Action_type");
		List<dataset_data> Action_type = Dataset_dataDAO.getall(datasets.getDataset_id());
		model.addAttribute("Action_type",Action_type);
		 
		
		
		return  "app/windows/addInputHiddenVisibility";
		
	}   
	
	
	
	
	@RequestMapping(value="/editstaticsnapshot-filemanager/{id}/{id_refer}",method = RequestMethod.GET)
	public String editstaticsnapshot(ModelMap model,@PathVariable int id,@PathVariable int id_refer){

		
		
		
		
		
		
		PlatformReport platform2 = new PlatformReport();
		platform2.setProcess_refer(id_refer);
		platform2.setApp_processes_id(id_refer);
		platform2.setFrom(id);
		platform2.setFrom_type("Profile");
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).startAppReport(platform2).prepareReport().load();
		Map<String, Object> data2 = platformService.getModelData();
		model.addAttribute("Config", data2.get("Config"));
		
		
		
		Platform platform = new Platform();
		platform.setApp_processes_id(id_refer);
		platform.setApp_item_id(id);
		LoadApp   LoadApp2 =   new LoadApp();
		platformService.initiate(LoadApp2).startAppIndex(platform).requireSegmentation().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		
		
		
	
		return  "app/windows/cl";
		
	}   
	
	
	
	
	@RequestMapping(value="/addprofile-filemanager/{id_refer}",method = RequestMethod.GET)
	public String addprofile(ModelMap model,@PathVariable int id_refer){

		dataset  dataset1 = DatasetDAO.getMain("Register_Type");
		List<dataset_data> Register_Type = Dataset_dataDAO.getall(dataset1.getDataset_id());
		model.addAttribute("Register_Type", Register_Type);
		
		dataset  dataset2 = DatasetDAO.getMain("Register_groups");
		List<dataset_data> Register_groups = Dataset_dataDAO.getall(dataset2.getDataset_id());
		model.addAttribute("Register_groups", Register_groups);
		
		dataset  dataset3 = DatasetDAO.getMain("Register_status");
		List<dataset_data> Register_status = Dataset_dataDAO.getall(dataset3.getDataset_id());
		model.addAttribute("Register_status", Register_status);
		model.addAttribute("id_refer", id_refer);
	
		
		dataset  dataset4 = DatasetDAO.getMain("Party_type");
		List<dataset_data> Party_type = Dataset_dataDAO.getall(dataset4.getDataset_id());
		model.addAttribute("Party_type", Party_type);
		
		dataset  dataset5 = DatasetDAO.getMain("Business_category");
		List<dataset_data> Business_category = Dataset_dataDAO.getall(dataset5.getDataset_id());
		model.addAttribute("Business_category", Business_category);
		
		dataset  dataset6 = DatasetDAO.getMain("Region");
		List<dataset_data> Region = Dataset_dataDAO.getall(dataset6.getDataset_id());
		model.addAttribute("Region", Region);
		

		
		
		dataset  dataset8 = DatasetDAO.getMain("Register_title");
		List<dataset_data> Register_title = Dataset_dataDAO.getall(dataset8.getDataset_id());
		model.addAttribute("Register_title", Register_title);
	
		
		
		return  "app/windows/addProfile";
		
	}   
	
	
	
	@RequestMapping(value="/Cloneprofile-filemanager/{id}/{process_id}",method = RequestMethod.GET)
	public String Cloneprofile(ModelMap model,HttpServletResponse response,@PathVariable int id,@PathVariable int process_id) throws Exception{
		
		registers item = EdataDAO.getRegister(id);
		model.addAttribute("item", item);
		
		edata_register edata_registers = new edata_register();
		BeanUtils.copyProperties(item,edata_registers);
		edata_registers.setRegister_name(item.getRegister_name()+"_Clone");
		edata_registers.setCustomerCode(null);
			
			try {
				EdataDAO.SaveMainRegister(edata_registers, "REG-");
		        return "redirect:/indexapp-filemanager/" + process_id;
		    } catch (Exception e) {
		        try {
		            // ส่ง HTTP 404 กลับไปแทน 200
		            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ข้อมูลซ้ำ หรือไม่พบข้อมูล");
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		        return null; // หรือ return ชื่อหน้า view ก็ได้ แต่ status จะเป็น 404 แล้ว
		    }
	} 
	
	
	@RequestMapping(value="/editprofile-filemanager/{id}/{process_id}",method = RequestMethod.GET)
	public String editprofile(ModelMap model,HttpServletResponse response,@PathVariable int process_id,@PathVariable int id) {
		
		dataset  dataset1 = DatasetDAO.getMain("Register_Type");
		List<dataset_data> Register_Type = Dataset_dataDAO.getall(dataset1.getDataset_id());
		model.addAttribute("Register_Type", Register_Type);
		
		dataset  dataset2 = DatasetDAO.getMain("Register_groups");
		List<dataset_data> Register_groups = Dataset_dataDAO.getall(dataset2.getDataset_id());
		model.addAttribute("Register_groups", Register_groups);
		
		dataset  dataset3 = DatasetDAO.getMain("Register_status");
		List<dataset_data> Register_status = Dataset_dataDAO.getall(dataset3.getDataset_id());
		model.addAttribute("Register_status", Register_status);
		model.addAttribute("id_refer", process_id);
	

		
		dataset  dataset4 = DatasetDAO.getMain("Party_type");
		List<dataset_data> Party_type = Dataset_dataDAO.getall(dataset4.getDataset_id());
		model.addAttribute("Party_type", Party_type);
		
		dataset  dataset5 = DatasetDAO.getMain("Business_category");
		List<dataset_data> Business_category = Dataset_dataDAO.getall(dataset5.getDataset_id());
		model.addAttribute("Business_category", Business_category);
		
		dataset  dataset6 = DatasetDAO.getMain("Region");
		List<dataset_data> Region = Dataset_dataDAO.getall(dataset6.getDataset_id());
		model.addAttribute("Region", Region);
		

		
		dataset  dataset8 = DatasetDAO.getMain("Register_title");
		List<dataset_data> Register_title = Dataset_dataDAO.getall(dataset8.getDataset_id());
		model.addAttribute("Register_title", Register_title);
		
		
		registers item = EdataDAO.getRegister(id);
		model.addAttribute("item", item);
		

		
		
		return  "app/windows/addProfile";
	
	} 
	
	
    @ResponseBody
	@RequestMapping(value="/fulladdresssearchajax-filemanage",method = RequestMethod.POST)
	public Object fulladdresssearchajax(@RequestParam(required =false) String search ) {
	
		dataset  dataset8 = DatasetDAO.getMain("Fulladdress");
		List<dataset_data> Register_title = Dataset_dataDAO.getallSearch(dataset8.getDataset_id(), search);
		
		System.out.println(" search "+search);
		
		System.out.println(" size "+Register_title.size());
		
		return scheduleService.createDataset_data(Register_title);

	}
		

	
	
	@RequestMapping(value="/deleteprofile-filemanager/{id}/{process_id}",method = RequestMethod.GET)
	public String deleteprofile(HttpServletResponse response,@PathVariable int id,@PathVariable int process_id){
		try {
			process process = EdataDAO.getProcess(process_id);
			
		        EdataDAO.DeleteMainRegister(id);
		        if(process.getALLOW_DELETE() == 1) {}

	        return "redirect:/indexapp-filemanager/" + process_id;
	    } catch (Exception e) {
	        try {
	            // ส่ง HTTP 404 กลับไปแทน 200
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ข้อมูลซ้ำ หรือไม่พบข้อมูล");
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        return null; // หรือ return ชื่อหน้า view ก็ได้ แต่ status จะเป็น 404 แล้ว
	    }
	}
	

	
	
	@RequestMapping(value="/saveprofile-filemanager",method = RequestMethod.POST)
	public String saveprofile(HttpServletResponse response,@RequestParam int process_id,edata_register edata_registers) throws Exception{
		try {
		
			 Integer[]  id_refer =  EdataDAO.SaveMainRegister(edata_registers, "REG-");
			 
			 edata_registers.setId(id_refer[1]);
			EdataDAO.SaveRegisterDirect(edata_registers);
	        return "redirect:/indexapp-filemanager/" + process_id;
	        
	    } catch (Exception e) {
	        try {
	            // ส่ง HTTP 404 กลับไปแทน 200
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ข้อมูลซ้ำ หรือไม่พบข้อมูล");
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        return null; // หรือ return ชื่อหน้า view ก็ได้ แต่ status จะเป็น 404 แล้ว
	    }
	
	}   
	@RequestMapping(value="/updateprofile-filemanager",method = RequestMethod.POST)
	public String updateprofile(HttpServletResponse response,@RequestParam int process_id,edata_register edata_registers) throws Exception{
		try {
			
			EdataDAO.SaveRegisterDirect(edata_registers);
	        EdataDAO.UpdateMainRegister(edata_registers,edata_registers.getRegister_status());
	        return "redirect:/profileapp-filemanager/" + edata_registers.getId()+"/"+process_id;
	    } catch (Exception e) {
	        System.err.println(e);
	        try {
	            // ส่ง HTTP 404 กลับไปแทน 200
	            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ข้อมูลซ้ำ หรือไม่พบข้อมูล");
	        } catch (IOException e1) {
	            e1.printStackTrace();
	            
	       
	        }
	        return null; // หรือ return ชื่อหน้า view ก็ได้ แต่ status จะเป็น 404 แล้ว
	    }
	}   
	
	
	
	
	
	
	
	@RequestMapping(value="/vieweditstaticsnapshot-filemanager/{id}/{id_refer}",method = RequestMethod.GET)
	public String vieweditstaticsnapshot(ModelMap model,@PathVariable int id,@PathVariable int id_refer){

		Platform platform = new Platform();
		platform.setApp_item_id(id);
		platform.setApp_processes_id(id_refer);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).startAppIndex(platform).requireSegmentation().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		
		model.addAttribute("cl_type_call",1);
	
		return  "app/windows/cl";
		
	}   
	
	
	@RequestMapping(value="/setstaticsnapshot-filemanager/{id_refer}/{edata_id}",method = RequestMethod.GET)
	public String setstaticsnapshot(ModelMap model,@PathVariable int id_refer,@PathVariable int edata_id){

		
		Segmentation segmentation = EdataDAO.getSegmentationByEdataid(edata_id);
		if(edata_id == 0 ) {
			Platform platform = new Platform();
			platform.setApp_processes_id(id_refer);
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).startAppIndex(platform).requireSegmentation().load();
			Map<String, Object> data = platformService.getModelData();
			model.addAllAttributes(data);
			return  "app/windows/cl";
		}
		
		
		if(segmentation == null ) {

			Platform platform = new Platform();
			platform.setApp_processes_id(id_refer);
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).startAppIndex(platform).requireSegmentation().load();
			Map<String, Object> data = platformService.getModelData();
			model.addAllAttributes(data);
			
			
			   edata  edatas = EdataDAO.getbyid(edata_id);
			   if(edatas != null) {
				   segmentation = new Segmentation();
				   segmentation.setName(edatas.getData_title());
					model.addAttribute("segmentation",segmentation);
			   }
			model.addAttribute("edata_id",edata_id);
			return  "app/windows/cl";
		}
		return  "redirect:/editstaticsnapshot-filemanager/"+segmentation.getId()+"/"+id_refer;
	
		
		
		

		
	}   
	
	@RequestMapping(value="/savesegmentation-filemanager",method = RequestMethod.POST)
	public String savesegmentation(Segmentation segmentation){
		
		
			Platform platform = new Platform();
			platform.setApp_processes_id(segmentation.getProcess_id());
			platform.setSegmentation(segmentation);
			LoadApp   LoadApp =   new LoadApp();
			LoadApp LoadApp2 = platformService.initiate(LoadApp).startAppIndex(platform).saveCl();
			
			return  "redirect:/editstaticsnapshot-filemanager/"+LoadApp2.getSaveId()+"/"+segmentation.getProcess_id();
		
	}   
	
	
	@RequestMapping(value="/delsegmentation-filemanager/{id}/{id_refer}",method = RequestMethod.GET)
	public String delsegmentation(@PathVariable int id,@PathVariable int id_refer){
		
			Platform platform = new Platform();
			platform.setApp_processes_id(id_refer);
			platform.setApp_item_id(id);
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).startAppIndex(platform).deleteSegmentation();
			return  "redirect:/segmentation-filemanager/"+id_refer;
		
	}   
	
	
	
	@RequestMapping(value="/addquery-filemanager/{id_refer}/{app_model}",method = RequestMethod.GET)
	public String addquery(ModelMap model,@PathVariable int id_refer,@PathVariable String app_model){

		PlatformReport platform = new PlatformReport();
		platform.setApp_id_refer(id_refer);
		platform.setApp_model(app_model);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).startAppReport(platform).prepareFilterCriteria().load();
		
		
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		
		return  "app/JerryMouseBI/addquery";
	}   
	
	
	@RequestMapping(value="/savefiltercriteria-filemanager",method = RequestMethod.POST)
	public String savefiltercriteria(FilterCriteria filterCriteria,@RequestParam int from){

		PlatformReport platform = new PlatformReport();
		platform.setFilterCriteria(filterCriteria);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).startAppReport(platform).saveFilterCriteria();

		return  "redirect:/QueryChain-filemanager/"+from;
	}   
	
	
	
	
	@RequestMapping(value="/addFilterChain-filemanager",method = RequestMethod.GET)
	public String addFilterChain(ModelMap model){

		
		PlatformReport platform = new PlatformReport();

		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).startAppReport(platform).prepareCreateQueryChain().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
		
		return  "app/JerryMouseBI/addFilterChain";
		
	}   
	
	
	@RequestMapping(value="/addFilterChain-filemanager",method = RequestMethod.POST)
	public String addFilterChain(@RequestParam (required=false) String tables,@RequestParam int process_id ){

		
		if(tables != null ) {

			if(tables.contains("-")) {
				FilterChain FilterChain = new FilterChain(); 
				
				String FilterChaintables = tables.split("-")[2];
				String Groups = tables.split("-")[1];
				String Type = tables.split("-")[0];
				
				if (FilterChaintables != null && FilterChaintables.matches("\\d+")) {
					FilterChain.setTables( Integer.valueOf(FilterChaintables) );
					FilterChain.setType(Type);
					FilterChain.setId_refer(process_id);
					FilterChain.setQuery_text("-");
					FilterChain.setGroups(Groups);
					PlatformReport platform = new PlatformReport();
					platform.setFilterChain(FilterChain);
					LoadApp   LoadApp =   new LoadApp();
					platformService.initiate(LoadApp).startAppReport(platform).saveFilterChain();
				}
			}
		}

		return  "redirect:/QueryChain-filemanager/"+process_id;
		
	}   
	
	
	
	@RequestMapping(value="/removeFilterChain-filemanager",method = RequestMethod.POST)
	public String removeFilterChain(
			 @RequestParam (required=false) String FilterChain
			,@RequestParam int process_id ){

		
				if(FilterChain != null ) {
		
							PlatformReport platform = new PlatformReport();
							platform.setRemove_string_id(FilterChain);
							LoadApp   LoadApp =   new LoadApp();
							platformService.initiate(LoadApp).startAppReport(platform).deleteFilterChain();
				}   
							return  "redirect:/QueryChain-filemanager/"+process_id;
				
				
	}   
	
	@RequestMapping(value="/usetab-filemanager",method = RequestMethod.POST)
	public String usetab(ModelMap model,@RequestParam int process_id,@RequestParam (required=false,defaultValue="0") int tabform ){


		PlatformUserSettings platformUserSettings = new PlatformUserSettings();
		platformUserSettings.setProcesses_id(process_id);
		platformUserSettings.setAction_id(tabform);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).useTabForm().save();
		return  "ajax/save";
		
	}   
	
	@RequestMapping(value="/usesubtable-filemanager",method = RequestMethod.POST)
	public String usesubtable(ModelMap model,@RequestParam int process_id
			,@RequestParam (required=false,defaultValue="0") String subtable_editmode 
			,@RequestParam (required=false,defaultValue="0") String subtable_viewmode 
			){

       System.out.println(" process_id "+process_id);
		PlatformUserSettings platformUserSettings = new PlatformUserSettings();
		platformUserSettings.setProcesses_id(process_id);
	
		process process = new process();
		process.setId(process_id);
		process.setSubtable_editmode(subtable_editmode);
		process.setSubtable_viewmode(subtable_viewmode);
		platformUserSettings.setProcess(process);
		LoadApp   LoadApp =   new LoadApp();
		
		platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).saveConfigSubTable();
		return  "ajax/save";
		
	}   
	
	@RequestMapping(value="/usevalid-filemanager",method = RequestMethod.POST)
	public String usevalid(ModelMap model,@RequestParam int process_id,@RequestParam (required=false,defaultValue="0") int used_valid ){


		PlatformUserSettings platformUserSettings = new PlatformUserSettings();
		platformUserSettings.setProcesses_id(process_id);
		platformUserSettings.setAction_id(used_valid);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).useValid().save();
		return  "ajax/save";
		
	}   
	
	@RequestMapping(value="/useattachment-filemanager",method = RequestMethod.POST)
	public String useattachment(ModelMap model,@RequestParam int process_id,@RequestParam (required=false,defaultValue="0") int used_attachment ){


		PlatformUserSettings platformUserSettings = new PlatformUserSettings();
		platformUserSettings.setProcesses_id(process_id);
		platformUserSettings.setAction_id(used_attachment);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).useAttachment().save();
		return  "ajax/save";
		
	}   
	
	
	
	
	@RequestMapping(value="/edittab-filemanager",method = RequestMethod.POST)
	public String addtab(ModelMap model,@RequestParam int process_id,@RequestParam (required=false) String remove_id ){

		
		Platform platform = new Platform();
		platform.setApp_processes_id(process_id);
		
		if(remove_id != null) {
			
			String action_id = remove_id.split(",")[0];
			PlatformUserSettings platformUserSettings = new PlatformUserSettings();
			platformUserSettings.setProcesses_id(process_id);
			platformUserSettings.setAction_id(Integer.valueOf(action_id));
			
			
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).tabFormItem().load();
			Map<String, Object> data = platformService.getModelData();
			model.addAllAttributes(data);
			LoadApp   LoadApp2 =   new LoadApp();
			platformService.initiate(LoadApp2).LoadComponent(platform).tabFormItem().load();
			model.addAllAttributes(data);
			
		}
		else {
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).LoadComponent(platform).tabFormItem().load();
			Map<String, Object> data = platformService.getModelData();
			model.addAllAttributes(data);
		}
		return  "app/windows/addtab";
		
	}   
	
	
	
	@RequestMapping(value="/saveaddtab-filemanager",method = RequestMethod.POST)
	public String saveaddtab(ModelMap model,@RequestParam int process_id,Tabform tabform){

		PlatformUserSettings platformUserSettings = new PlatformUserSettings();
		platformUserSettings.setProcesses_id(process_id);
		platformUserSettings.setTabform(tabform);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).tabForm().save();


		return  "redirect:/setwindow-filemanager/"+process_id;
		
	}   
	@RequestMapping(value="/removetab-filemanager",method = RequestMethod.POST)
	public String removetab(ModelMap model,@RequestParam int process_id,@RequestParam (required=false) String remove_id ){

		
		if(remove_id != null) {
			PlatformUserSettings platformUserSettings = new PlatformUserSettings();
			platformUserSettings.setProcesses_id(process_id);
			platformUserSettings.setRemove_id(remove_id);
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).tabForm().delete();
		}
		
		
		return  "redirect:/setwindow-filemanager/"+process_id;
		
	}   
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/editInputHiddenVisibility-filemanager",method = RequestMethod.POST)
	public String editInputHiddenVisibility(ModelMap model,@RequestParam int process_id,@RequestParam (required=false) String remove_id ){

		
		Platform platform = new Platform();
		platform.setApp_processes_id(process_id);
		
		dataset datasets  = DatasetDAO.getMain("Action_type");
		List<dataset_data> Action_type = Dataset_dataDAO.getall(datasets.getDataset_id());
		model.addAttribute("Action_type",Action_type);
		
		
		if(remove_id != null) {
			
			String action_id = remove_id.split(",")[0];
			PlatformUserSettings platformUserSettings = new PlatformUserSettings();
			platformUserSettings.setProcesses_id(process_id);
			platformUserSettings.setAction_id(Integer.valueOf(action_id));
			
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).inputHiddenVisibilityItem().load();
			Map<String, Object> data = platformService.getModelData();
			model.addAllAttributes(data);
			LoadApp   LoadApp2 =   new LoadApp();
			platformService.initiate(LoadApp2).LoadComponent(platform).tabFormItem().load();
			model.addAllAttributes(data);
			
		}
		else {
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).LoadComponent(platform).tabFormItem().load();
			Map<String, Object> data = platformService.getModelData();
			model.addAllAttributes(data);
		}
		return  "app/windows/addInputHiddenVisibility";
		
	}   
	
	
	
	@RequestMapping(value="/saveInputHiddenVisibility-filemanager",method = RequestMethod.POST)
	public String saveInputHiddenVisibility(ModelMap model,@RequestParam int process_id,InputHiddenVisibility InputHiddenVisibility){

		PlatformUserSettings platformUserSettings = new PlatformUserSettings();
		platformUserSettings.setProcesses_id(process_id);
		platformUserSettings.setInputHiddenVisibility(InputHiddenVisibility);
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).saveInputHiddenVisibility();


		return  "redirect:/setwindow-filemanager/"+process_id;
		
	}   
	@RequestMapping(value="/removeInputHiddenVisibility-filemanager",method = RequestMethod.POST)
	public String removeInputHiddenVisibility(ModelMap model,@RequestParam int process_id,@RequestParam (required=false) String remove_id ){

		
		if(remove_id != null) {
			PlatformUserSettings platformUserSettings = new PlatformUserSettings();
			platformUserSettings.setProcesses_id(process_id);
			platformUserSettings.setRemove_id(remove_id);
			LoadApp   LoadApp =   new LoadApp();
			platformService.initiate(LoadApp).applyUserSettings(platformUserSettings).deleteInputHiddenVisibility();
		}
		
		
		return  "redirect:/setwindow-filemanager/"+process_id;
		
	}   
	
	
	
	
	
	
	
	@RequestMapping(value="/flowdoc-filemanager/{id}/{processes_id}",method = RequestMethod.GET)
	public String flowdoc(ModelMap model,@PathVariable int id,@PathVariable int processes_id){

		
		
		try {
			

			Platform platform = new Platform();
			
			platform.setApp_item_id(id);
			platform.setApp_processes_id(processes_id);
			
			UserDAO.getbyusername2(Userservice.getLoggedName());
			policies policies = Userservice.getPolicies(processes_id);
			System.out.println(" policies "+policies.toString());
			
			
			
			
		   	LoadApp   LoadApp =   new LoadApp();
			  platformService.initiate(LoadApp).workflow(platform).load();
			  Map<String, Object> data = platformService.getModelData();
			  model.addAllAttributes(data);
			  
			  

			  
				model.addAttribute("id", id);
				model.addAttribute("processes_id", processes_id);
			  
			  
			  return  "app/windows/flow";
			
		}catch(Exception e) {
			
			  return  null;
		}
		
		
	}   
	
	
	

	@RequestMapping(value="/Ticketflowdoc-filemanager/{id}/{processes_id}",method = RequestMethod.GET)
	public String Ticketflowdoc(ModelMap model,@PathVariable int id,@PathVariable int processes_id){

		
		
		try {
			

			Platform platform = new Platform();
			
			platform.setApp_item_id(id);
			platform.setApp_processes_id(processes_id);
			
			UserDAO.getbyusername2(Userservice.getLoggedName());
			policies policies = Userservice.getPolicies(processes_id);
			System.out.println(" policies "+policies.toString());
		   	LoadApp   LoadApp =   new LoadApp();
			  platformService.initiate(LoadApp).workflow(platform).load();
			  Map<String, Object> data = platformService.getModelData();
			  model.addAllAttributes(data);
				model.addAttribute("id", id);
				model.addAttribute("processes_id", processes_id);
				
			  return  "app/windows/ticket";
			
		}catch(Exception e) {
			
			  return  null;
		}
		
		
	}   
	
	

	 @RequestMapping(value="/Ticketflowdoc-filemanager",method = RequestMethod.POST)    
	    public String Ticketflowdoc( 
	    		@RequestParam int to
	    		,@RequestParam int send
	    		,@RequestParam int app_processes_id
	    		,@RequestParam int step
	    		,@RequestParam int flow_id
	    		,@RequestParam int data_id	
	    		,@RequestParam int  workflow_ticket_edit
	      		,@RequestParam(value="appproved",required =false,defaultValue="0") int appproved
	      		,@RequestParam(value="remark",required =false,defaultValue="-") String remark
	     		,@RequestParam(value="remarks_rejected",required =false,defaultValue="-") String remarks_rejected
	       		,@RequestParam(value="closing_status",required =false,defaultValue="-") String closing_status
	    		,ModelMap model
	    		,HttpServletResponse response 
	    		) throws Exception
				
	 {
		 
		 
			Platform platform = new Platform();
			platform.setApp_item_id(data_id);
			platform.setApp_workflow_id(flow_id);
			platform.setApp_processes_id(app_processes_id);
			platform.setWorkflow_to(to);
			platform.setWorkflow_send(send);
			platform.setWorkflow_step(step);
			platform.setWorkflow_appproved(appproved);
			platform.setWorkflow_ticket_edit(workflow_ticket_edit);
			platform.setWorkflow_closing_status(closing_status);
			platform.setWorkflow_remark(remark);
			platform.setWorkflow_remarks_rejected(remarks_rejected);
			
			
		LoadApp   LoadApp =   new LoadApp();	
		LoadApp LoadApp2 =  platformService.initiate(LoadApp).workflow(platform).send();
		model.addAttribute("Http_status_code",LoadApp2.getHttp_status_code());
		model.addAttribute("Callback",LoadApp2.getCallback());
		
		if(LoadApp2.getHttp_status_code() == 200) {
			response.setStatus(HttpServletResponse.SC_CREATED); 
		}
	    return "app/callback";
		
	
	 }
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/workflowaccess-filemanager/{id}/{processes_id}",method = RequestMethod.GET)
	public String workflowaccess(ModelMap model,@PathVariable int id,@PathVariable int processes_id){
		
		Platform platform = new Platform();
		
		platform.setApp_item_id(id);
		platform.setApp_processes_id(processes_id);
		
		UserDAO.getbyusername2(Userservice.getLoggedName());
		policies policies = Userservice.getPolicies(processes_id);
		System.out.println(" policies "+policies.toString());
		
		
		
		
		LoadApp   LoadApp =   new LoadApp();
		  platformService.initiate(LoadApp).workflow(platform).load();
		  Map<String, Object> data = platformService.getModelData();
		  model.addAllAttributes(data);
		  
			model.addAttribute("id", id);
			model.addAttribute("processes_id", processes_id);
		  return  "app/windows/workflowaccess";
		
	}   
	
	
	@GetMapping("/link-filemanager/{app_processes_id}/{process_join}")
	public String linkdoc(
			ModelMap model
			,@PathVariable int app_processes_id
			,@PathVariable int process_join
			){ 


		 model.addAttribute("app_processes_id",app_processes_id); 
		 model.addAttribute("process_join",process_join); 
		return "app/windows/link";

		}
	
	@GetMapping("/Chooselinkapp-filemanager/{app_processes_id}/{process_join}")
	public String Chooselinkapp(
			ModelMap model
			,@PathVariable int app_processes_id
			,@PathVariable int process_join
			){ 
		LoadApp   LoadApp =   new LoadApp();
		  platformService.initiate(LoadApp).loadRelation(process_join).load();
		  Map<String, Object> data = platformService.getModelData();
		  model.addAllAttributes(data);
		  
			 model.addAttribute("app_processes_id",app_processes_id); 
			
		return "app/windows/choose";

		}
	
	
	
	
	
	
	
	@RequestMapping(value="/editdocument-filemanager", method=RequestMethod.POST)
	public String editdocument(
			ModelMap model,
			@RequestParam int id,
			@RequestParam int logid,
			@RequestParam String upload_pn,
			@RequestParam String due,
			@RequestParam (required=false) CommonsMultipartFile file,  
			dataeform dataeform,  
	        HttpSession session,log logs
	           ,@RequestParam int level) throws Exception{ 
		
	 	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());
	 	int upload_pnid =  Integer.parseInt(upload_pn.split("-")[0]);
		int upload_pnsubid =  Integer.parseInt(upload_pn.split("-")[1]);
		upload_pn upload_pns = Upload_pnDAO.getbyId(upload_pnid);
	    report_pn report_pns = Report_pnDAO.getbyId2(upload_pns.getUpload_pn_id(),due);
	    
	    
	    
		  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
		  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
	    
		if(file != null) {
			

					if(id != folderDTOs.getFolder_subid2()){
						   model.addAttribute("error","error 01"); 
				  	        return "error";
					 }
			
	
					  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
				   	String cleanPath = storagePath.replace("file:///", ""); 

				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
				   	String path = cleanPath +  folderDTOs.getSubfoldername2(); 
				    
				    
				    String filenameoriginal = file.getOriginalFilename(); 
				    System.out.println();
				    
				    String filename = Fileservice.assertEquals(file.getContentType());
				    
				  
				    
				    long imageFileSize = file.getSize();
				    String Size = Fileservice.printFileSize(imageFileSize);
				    
				    System.out.println("fileSize "+Size);
			
			    	 
			    	folder folders = FolderDAO.getbytype(folderDTOs.getSubfoldername2());  	    
			  	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
			  	    int drives_id;
			  	    drives_id = folders.getFolder_idrefer();
			  	    if(mainfolders != null) {
			  	    drives_id= mainfolders.getFolder_idrefer();	
			  	    }
			  	    drives drivess  = DrivesDAO.getbyId(drives_id);
			            
			  	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
			  	    if(imageFileSize > totals) {
			  	    	
			  	            model.addAttribute("error","error 02"); 
			  	            return "error";
			  	    }
			  	    
			  	    file sum = FileDAO.getsum(folderDTOs.getSubfoldername2());
				    long imageFileSize2 = sum.getFile_size() +imageFileSize;
				    String Size2 = Fileservice.printFileSize(imageFileSize2);
				    FolderDAO.setuse(folders.getFolder_id(),imageFileSize2,Size2);
				    String Pathfull;
				    
				    if(mainfolders != null) {
				    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
			    	    long imageFileSize3 = sum2.getFolder_use();
			    	    String Size3 = Fileservice.printFileSize(imageFileSize3);
			    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize3,Size3);
			    	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
				    }else {
				    	
			        	drives_id = folders.getFolder_idrefer();
			        	drivess  = DrivesDAO.getbyId(drives_id);
			        	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
			    	    	
			    	    }
			    	          	    
				    
				    
			    	folder sum3 = FolderDAO.getsum(drives_id);
				    long imageFileSize3 = sum3.getFolder_use();
				    double percent;
				    if(imageFileSize3 > 0) {
				       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
				    }else {
				    	 percent =0;	
				    }
				    String Size4 = Fileservice.printFileSize(imageFileSize3);
				    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
			  	    
			  	    
			    	 
			 	    byte[] bytes = file.getBytes();  
			 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
			 	         new File(path + File.separator + filename)));  
			 	    stream.write(bytes);  
			 	    stream.flush();  
			 	    stream.close();  
			 	  
			 	    
			  	      String dateend = null;
				   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
				     LocalDateTime localDate = LocalDateTime.now();//For reference
				     
				  
				     if(Integer. valueOf(upload_pns.getUpload_pn_end()) == 1 ) {
				   	      LocalDateTime localDate2 = LocalDateTime.now().plusMonths(6);
				   	     dateend = localDate2.format(formatter);
				     }
				    else if(Integer. valueOf(upload_pns.getUpload_pn_end()) == 1) {
				      	 	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(1);
				      	     dateend = localDate2.format(formatter);
			   	 }  
				     else if(Integer. valueOf(upload_pns.getUpload_pn_end()) == 3 ) {
				   	    	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(3); 
				   	    	  dateend = localDate2.format(formatter);
				     }
				     else if(Integer. valueOf(upload_pns.getUpload_pn_end()) ==4 ) {
					   	 	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(5); 
					   	      dateend = localDate2.format(formatter);
				     }  
				     
				 
			 	    
			 	    
			 	    
			
				   	 String date = localDate.format(formatter);
				   	 String time = localDate.format(formatter2);
			 	    
			 	  
			 	   
			 	    
			 	    String newfilename2 =null;
			 	   if(upload_pnsubid == 1 ) {
			 		  newfilename2 = upload_pns.getUpload_pn_name();
			    	}
			    	else if(upload_pnsubid == 2 ) {
			    		newfilename2 = upload_pns.getUpload_pn_name2();
			    	}
			    	else if(upload_pnsubid == 3 ) {
			    		newfilename2 = upload_pns.getUpload_pn_name3();
			    	}
			    	else if(upload_pnsubid == 4 ) {
			    		newfilename2 = upload_pns.getUpload_pn_name4();
			    	}
			    	else if(upload_pnsubid == 5 ) {
			    		newfilename2 = upload_pns.getUpload_pn_name5();
			    	}
			    	
			 	    
			 	     int report_pnid = 0;
			 	    String newfilename = newfilename2+" โดย "+users.getUser_name();
			 	    
			 	    
			 	    if(upload_pns.getUpload_pn_namefile() == 1) {
			 	    	newfilename = " โดย "+users.getUser_name();
			 	    }else if(upload_pns.getUpload_pn_namefile() == 3) {
			 	    	newfilename = filenameoriginal;
			 	    }else if(upload_pns.getUpload_pn_namefile() ==4) {
			 	    	newfilename = newfilename2;
			 	    }
			 	    
			 	    
			 	    if(report_pns != null) {
			 	    	 report_pnid =report_pns.getReport_pn_id();
			 	    	 Report_pnDAO.setdateadd(report_pnid,date);
			 	    }
			 	    
			 	    
			 	    FileDAO.save2(filename, newfilename, 
			 	    		file.getContentType(),folderDTOs.getSubfoldername2(), imageFileSize,Size, folderDTOs.getFolder_subid2()
			 	    		,null,String.valueOf(report_pnid),date,Pathfull,users.getUsername(),dateend);       
			
					logs.setLog_folder(Pathfull);
					logs.setLog_id(logid);
					logs.setLog_file(filename);
					logs.setLog_dive(driverDTOs.getDrives_drive());
					int idlogs = LogDAO.update(logs);
			 	   
		}

	
	   if(dataeform.getDataeform_condition_id() >  0) {
		    DataeformDAO.update(dataeform);
	   }
	    
	    return "redirect:/lohpn-filemanager?idrefer="+upload_pnid+"&&id="+report_pns.getReport_pn_id();
			
  }
	

	@RequestMapping(value="/addupload_pn-filemanager", method=RequestMethod.POST)
	public String adduploadpnsave(upload_pn upload_pns
			,@RequestParam List<String> upload_pn_respon
			,report_pn report_pns
			,@RequestParam List<String> upload_pn_app,
			@RequestParam int idfolder
			,@RequestParam String upload_pn_setdue2
			,@RequestParam String upload_pn_setdue
			) {
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		pathManager.run();
		pathManager.Path(idfolder);
    	
   		upload_pns.setUpload_pn_respon(String.join(",",upload_pn_respon));
		upload_pns.setUpload_pn_app(String.join(",",upload_pn_app));
		upload_pns.setUpload_pn_idadd(users.getUser_id());
		upload_pns.setUpload_pn_idrefer(idfolder);
		upload_pns.setUpload_pn_storage(pathManager.getPathfull());
		upload_pns.setUpload_pn_monitor(upload_pns.getUpload_pn_monitor());
		int id = Upload_pnDAO.save(upload_pns);
    	
		
		Plans plan =new Plans();
		plan.setUsers(String.join(",",upload_pn_respon));
		plan.setUser_app(String.join(",",upload_pn_app));
		plan.setPlan_id(id);
		
		
			if(upload_pns.getUpload_pn_type() == 0) {
				
				
				plan.setType(upload_pns.getUpload_pn_due());
				plan.setWeek( upload_pns.getUpload_pn_setdue2() );
				plan.setDay_in_year( Integer.valueOf(upload_pns.getUpload_pn_setdue()) );
				UploadPlanManagers.createUploadPlans(plan);
				
				
				
			}else if(upload_pns.getUpload_pn_type() == 1) {
				plan.setType(4);
				plan.setDate_due(upload_pns.getUpload_pn_datedue());
				UploadPlanManagers.createUploadPlans(plan);
			}
		
		folder folder  = FolderDAO.getbyId(idfolder);
		return "redirect:/setinfo/"+folder.getFolder_type()+"?id="+folder.getFolder_id();
		
	}
	
	
	
	
	

	
	/*
	LocalDateTime today = LocalDateTime.now();//For reference
	LocalDateTime endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-");
	DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd");
	String dates = today.format(formatter);
	String month = today.format(formatter2);
	String lastDay = endOfMonth.format(formatter3);
	String date;
	*/
	


//if(upload_pns.getUpload_pn_monitor() == 0) {
//long monitor = otherservices.generateRandomnumber(9);
//}


/*
if(upload_pns.getUpload_pn_monitor() > 0) {
	condition conditions = ConditionDAO.getbyid((int)upload_pns.getUpload_pn_monitor());
	condition port = ConditionDAO.getbyport((int)upload_pns.getUpload_pn_monitor());
	
	if(conditions.getCalculation_port1() > 0 || conditions.getCalculation_port2() > 0 
			|| conditions.getCalculation_port3() > 0 || conditions.getCalculation_port4() > 0 ) {
		upload_pns.setUpload_pn_category("E-FORM DATA");
	}
	else if(port != null) {
		upload_pns.setUpload_pn_category("E-FORM Transactions");
	}
	else  {
		upload_pns.setUpload_pn_category("E-FORM");
	}	
	
	
}else {
	upload_pns.setUpload_pn_category("Work plan");
	
}





	if(upload_pns.getUpload_pn_due() == 0) {
	date = dates;
}else {
	
	if(Integer.parseInt(lastDay)  < upload_pns.getUpload_pn_due()) {
		 date = month + lastDay;
	}else {
		date = month + upload_pns.getUpload_pn_due();
	}

}
	*/
	
	
	
	
	
	
/*
	int  target = 1;

if( otherservices.DataStatus(upload_pns.getUpload_pn_name2())  ) {	
		target++;
	}
if( otherservices.DataStatus(upload_pns.getUpload_pn_name3())  ) {	
		target++;
	}
if( otherservices.DataStatus(upload_pns.getUpload_pn_name4())  ) {	
		target++;
	}
if( otherservices.DataStatus(upload_pns.getUpload_pn_name5())  ) {	
		target++;
	}

	if(upload_pns.getUpload_pn_type() == 0) {
		
		
		
		if(!upload_pns.getUpload_pn_setdue().equals("0")) {
			 LocalDateTime DayOfWeeks = null;
			 
			 
			LocalDateTime Week = LocalDateTime.now().plusWeeks(1);//For reference
			 
		  		if(upload_pns.getUpload_pn_setdue().equals("W-1")) {
		  			DayOfWeeks = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		  			}
		  		else if(upload_pns.getUpload_pn_setdue().equals("W-2")) {
		  			DayOfWeeks = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).plusDays(1);
		  			}
				else if(upload_pns.getUpload_pn_setdue().equals("W-3")) {
					DayOfWeeks = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).plusDays(2);	  				
						  			}
				else if(upload_pns.getUpload_pn_setdue().equals("W-4")) {
					DayOfWeeks = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).plusDays(3);		
					}
				else if(upload_pns.getUpload_pn_setdue().equals("W-5")) {
					DayOfWeeks = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).plusDays(4);	
				}
				else if(upload_pns.getUpload_pn_setdue().equals("W-6")) {
					DayOfWeeks = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).plusDays(5);	
				}
				else if(upload_pns.getUpload_pn_setdue().equals("W-7")) {
					DayOfWeeks = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).plusDays(6);	
				}else {
					
					int dateset = Integer.valueOf(upload_pns.getUpload_pn_setdue());  
					DayOfWeeks = today.withDayOfMonth(dateset);
					
				}
		  		
		  		if(DayOfWeeks != null) {
		  			date = DayOfWeeks.format(formatter);
		  		}
		  		
		  		
		}
		

	report_pns.setReport_pn_date(date);   
	    report_pns.setReport_pn_target(target);
	    report_pns.setReport_pn_total(0);
	    report_pns.setReport_pn_idrefer(id);
	    report_pns.setReport_pn_app(upload_pns.getUpload_pn_app());
	    report_pns.setReport_pn_department(upload_pns.getUpload_pn_department());
	    report_pns.setReport_pn_respon(upload_pns.getUpload_pn_respon());
	    report_pns.setReport_pn_status(3);
	    Report_pnDAO.save2(report_pns);
	}
	
	if(upload_pns.getUpload_pn_type() == 1) {
	report_pns.setReport_pn_date(upload_pns.getUpload_pn_datedue());   
	    report_pns.setReport_pn_target(target);
	    report_pns.setReport_pn_total(0);
	    report_pns.setReport_pn_idrefer(id);
	    report_pns.setReport_pn_app(upload_pns.getUpload_pn_app());
	    report_pns.setReport_pn_department(upload_pns.getUpload_pn_department());
	    report_pns.setReport_pn_respon(upload_pns.getUpload_pn_respon());
	    report_pns.setReport_pn_status(3);
	    Report_pnDAO.save2(report_pns);
	}
	
	
	if(upload_pns.getUpload_pn_type() == 2) {
		report_pns.setReport_pn_date(dates);   
   	    report_pns.setReport_pn_target(1);
   	    report_pns.setReport_pn_total(0);
   	    report_pns.setReport_pn_idrefer(id);
   	    report_pns.setReport_pn_app(upload_pns.getUpload_pn_app());
   	    report_pns.setReport_pn_department(upload_pns.getUpload_pn_department());
   	    report_pns.setReport_pn_respon(upload_pns.getUpload_pn_respon());
   	    report_pns.setReport_pn_status(3);
   	    Report_pnDAO.save2(report_pns);
  	}
	
	
*/


	
	
	
	
	@RequestMapping(value="/adddrives-filemanager", method=RequestMethod.POST)
	public String adddrivessave(drives drivess,ModelMap model) {
		 type types = TypeDAO.getbytype("Drive", 0);
       
		 if(types != null) {
			 
			 
			   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			   group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
			   Group_authoritiesDAO.save("READ-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("DELETE-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("EDIT-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("SET-" + types.getType_name(), group_memberss.getGroup_id());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
		updatedAuthorities.add(new SimpleGrantedAuthority("READ-" + types.getType_name()));
		updatedAuthorities.add(new SimpleGrantedAuthority("DELETE-" + types.getType_name()));
		updatedAuthorities.add(new SimpleGrantedAuthority("EDIT-" + types.getType_name()));
		updatedAuthorities.add(new SimpleGrantedAuthority("SET-" + types.getType_name()));
		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
			   
			   
			   
			    long imageFileSize = drivess.getDrives_size();
			    String Size = Fileservice.printFileSize(imageFileSize);
			 
			 DrivesDAO.save(drivess,types.getType_name(),Size);
			 TypeDAO.update(types.getType_name(),1);
			 
		
			 
			 
			
			 return "redirect:/filemanager";
		 }else {
			   model.addAttribute("error","error 01"); 
	  	        return "error";
		 }
		 
		
    
	}

	@RequestMapping(value="/addfolder-filemanager", method=RequestMethod.POST)
	public String addfoldersave(
			folder folders
			,ModelMap model
			,HttpSession session
			) {
		
		  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
		  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
		
		
		 type types = TypeDAO.getbytype("Folder", 0);

		 if(types != null) {
			   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			   group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
			   Group_authoritiesDAO.save("READ-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("DELETE-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("EDIT-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("SET-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("CHECK-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("APP-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("UPLOAD-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("DOWNLOAD-" + types.getType_name(), group_memberss.getGroup_id());
			   Group_authoritiesDAO.save("DELETEITEM-" + types.getType_name(), group_memberss.getGroup_id());
	   
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
				updatedAuthorities.add(new SimpleGrantedAuthority("READ-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("DELETE-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("EDIT-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("SET-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("CHECK-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("APP-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("UPLOAD-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("DOWNLOAD-" + types.getType_name()));
				updatedAuthorities.add(new SimpleGrantedAuthority("DELETEITEM-" + types.getType_name()));
		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
			 
			 
			 FolderDAO.save(folders,driverDTOs.getDrives_id(),types.getType_name());
			 TypeDAO.update(types.getType_name(),1);
			 model.addAttribute("Drivesname",folderDTOs.getDrivesname()); 
			 return "redirect:/filemanager/"+ driverDTOs.getDrives_drive()+ "?id="+driverDTOs.getDrives_id();
		 }else {
			 model.addAttribute("error","error 01"); 
	  	        return "error";
		 }
	}
	
	
	
	
	
    @RequestMapping(value="/addsubfolder-filemanager", method=RequestMethod.POST)
	public String addsubfoldersave(folder folders,ModelMap model,HttpSession session) {
				 type types = TypeDAO.getbytype("Folder", 0);

				 
				  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
				  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
				 
				 
				 if(types != null) {
					 
					   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
					   group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
					   Group_authoritiesDAO.save("READ-" + types.getType_name(), group_memberss.getGroup_id());
					   Group_authoritiesDAO.save("DELETE-" + types.getType_name(), group_memberss.getGroup_id());
					   Group_authoritiesDAO.save("EDIT-" + types.getType_name(), group_memberss.getGroup_id());
					   Group_authoritiesDAO.save("SET-" + types.getType_name(), group_memberss.getGroup_id());
						Authentication auth = SecurityContextHolder.getContext().getAuthentication();
						List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
						updatedAuthorities.add(new SimpleGrantedAuthority("READ-" + types.getType_name()));
						updatedAuthorities.add(new SimpleGrantedAuthority("DELETE-" + types.getType_name()));
						updatedAuthorities.add(new SimpleGrantedAuthority("EDIT-" + types.getType_name()));
						updatedAuthorities.add(new SimpleGrantedAuthority("SET-" + types.getType_name()));
					 
						Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
						SecurityContextHolder.getContext().setAuthentication(newAuth);
						
						
					 FolderDAO.save(folders,folderDTOs.getFolder_id(),types.getType_name());
					 TypeDAO.update(types.getType_name(),1);
					 
					 return "redirect:/filemanager/"+driverDTOs.getDrives_drive()+"/"+ folderDTOs.getFolder_name()+"?id=" + folderDTOs.getFolder_id();
				 }else {
					 model.addAttribute("error","error 01"); 
			  	     return "error";
						
				
				 }
				 		
	}
	
    
    
	@RequestMapping(value="/editdrives-filemanager/{Drives}" ,  method=RequestMethod.GET)    
	public String edituser(@RequestParam int id,ModelMap model){
	    drives drivess = DrivesDAO.getbyId(id);   
	
	    MainServices.addNotificationAttributes(model);
		
		model.addAttribute("drives", drivess);
		return "formdrive";   
	}     
    
	
	@RequestMapping(value="/editupload_pn-filemanager" ,  method=RequestMethod.GET)    
	public String editupload_pn(@RequestParam int id,ModelMap model){
		
		 user usersadd = UserDAO.getbyusername2(Userservice.getLoggedName());  
		
		upload_pn upload_pns = Upload_pnDAO.getbyId(id,usersadd.getUser_id());  
	    List<String> respon;
	    List<String> app;
	    
	    MainServices.addNotificationAttributes(model);
		List<department>  departments = DepartmentDAO.getall();
		
		 respon = Arrays.asList(upload_pns.getUpload_pn_respon().split(","));
		 app = Arrays.asList(upload_pns.getUpload_pn_app().split(","));
		
        folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
		String authorities = "UPLOAD-"+folders.getFolder_type();
		List<user> users = UserDAO.getallgroup(authorities);
		
		
	  	PolicyType policyType = PolicyType.valueOf("TASK");
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    List<Policy> mypolicy = policyRepository.findAllByTypeAndCreateBy(policyType, usersed.getUser_id());
		model.addAttribute("mypolicy",mypolicy); 
		
		
		List<condition> conditions = ConditionDAO.getall();
		model.addAttribute("conditions",conditions); 
		

		model.addAttribute("idfolder",folders.getFolder_id());   
		model.addAttribute("department",departments);   
		model.addAttribute("users",users);  
		model.addAttribute("respon", respon);
		model.addAttribute("app", app);
		model.addAttribute("upload_pn", upload_pns);
		return "upload_pn";   
	}    
	
    @RequestMapping(value="/editupload_pn-filemanager",method = RequestMethod.POST)    
    public String editsave(  upload_pn upload_pns
    		,@RequestParam(required = false) String upload_pn_respon
    		,@RequestParam String upload_pn_app,report_pn report_pns
    		,@RequestParam String upload_pn_setdue2
    		,@RequestParam int selectby
    		){ 
    	

    	upload_pns.setUpload_pn_app(String.join(",",upload_pn_app));
    	


    	
    	LocalDateTime today = LocalDateTime.now();//For reference
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String dates = today.format(formatter);

    	
    	
    	
    	Report_pnDAO.deletebyreport(upload_pns.getUpload_pn_id(),dates);
    	
    	
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		if(upload_pn_respon != null) {
   		upload_pns.setUpload_pn_respon(String.join(",",upload_pn_respon));
		}
   		
   		upload_pns.setUpload_pn_app(upload_pn_app);
		upload_pns.setUpload_pn_idadd(users.getUser_id());
		upload_pns.setUpload_pn_storage(pathManager.getPathfull());
		upload_pns.setUpload_pn_monitor(upload_pns.getUpload_pn_monitor());
    	Upload_pnDAO.update(upload_pns);    
		
    	goalService.createGoalByUploadPlans(upload_pns);
    	


    	return "redirect:/pntask-filemanager?group=1";
    }    

    
    
    @RequestMapping(value="/editdrives-filemanager",method = RequestMethod.POST)    
    public String editsave(  drives drivess , log logs){ 
    	
    	 drives drivesold = DrivesDAO.getbyId(drivess.getDrives_id());
    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
		
		
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("EDIT");
		logs.setLog_date(date);
		logs.setLog_time(time);
		logs.setLog_note("เเก้ไขข้อมูลจาก Drives ชื่อเดิม "+ drivesold.getDrives_name() + " ขนาดเดิม " + drivesold.getDrives_size2()  + "เป็นชื่อ "+ drivess.getDrives_name() + " ขนาด " + drivess.getDrives_size2());
		logs.setLog_refer(Integer.toString(drivess.getDrives_id()));
		logs.setLog_folder(drivess.getDrives_name());
		LogDAO.save(logs);
    	
    	
    	
    	long imageFileSize = drivess.getDrives_size();
		String Size = Fileservice.printFileSize(imageFileSize);
		drivess.setDrives_size2(Size);
    	DrivesDAO.update(drivess);    
    	return "redirect:/filemanager";
    }    
    
 
    @RequestMapping(value="/editsubdrives-filemanager",method = RequestMethod.POST)    
    public String editsubsave(  drives drivess,HttpSession session){ 
    	
    	  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
    	  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
    	
    	DrivesDAO.update(drivess);    
		 return "redirect:/filemanager/"+driverDTOs.getDrives_drive()+"/"+ folderDTOs.getFolder_name()+"?id=" + folderDTOs.getFolder_id();
			
    }    
    
    
    
	@RequestMapping(value="/editfolder-filemanager/{Folder}" ,  method=RequestMethod.GET)    
	public String editfolder(@RequestParam int id,ModelMap model,@PathVariable String Folder){
		folder folders = FolderDAO.getbyId(id);
		
		 MainServices.addNotificationAttributes(model);
		
	      model.addAttribute("folders", folders);
		   model.addAttribute("myfolder",Folder); 
	       return "formfolder";   
	}     
    

    @RequestMapping(value="/editfolder-filemanager/{Folder}",method = RequestMethod.POST)    
    public String editfoldersave(  folder folders,log logs,HttpSession session){    	
    	
  	  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");

    	
        folder foldersold = FolderDAO.getbyId(folders.getFolder_id()); 
   	    folder mainfolders = FolderDAO.getbyId(foldersold.getFolder_idrefer());
   	 
   	    
   
   	    int drives_id;
   	    drives_id = foldersold.getFolder_idrefer();
   	    
   	    
   	    
   	    if(mainfolders != null) {
   	    drives_id= mainfolders.getFolder_idrefer();	
   	    }
   	    drives drivess  = DrivesDAO.getbyId(drives_id);
             


 	    String Pathfull;
 	    
 	    if(mainfolders != null) {

     	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
 	    }else {
 	    	
         	drives_id = foldersold.getFolder_idrefer();
         	drivess  = DrivesDAO.getbyId(drives_id);
         	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
     	    	
     	    }
         

	  
         
     	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
		
		
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("EDIT");
		logs.setLog_date(date);
		logs.setLog_time(time);
		logs.setLog_note("เเก้ไขข้อมูลจาก Folder ชื่อเดิม "+ foldersold.getFolder_name() + " ชื่อใหม่ " + folders.getFolder_name());
		logs.setLog_refer(Integer.toString(foldersold.getFolder_id()));
		logs.setLog_folder(Pathfull);
		LogDAO.save(logs);
    	
    	FolderDAO.update(folders);    
    	
    	
    	 return "redirect:/filemanager/"+ driverDTOs.getDrives_drive()+ "?id="+driverDTOs.getDrives_id();
			
    }    
    
	@RequestMapping(value="/editsubfolder-filemanager/{Folder}" ,  method=RequestMethod.GET)    
	public String editsubfolder(@RequestParam int id,ModelMap model,@PathVariable String Folder){
		folder folders = FolderDAO.getbyId(id); 
		
		 MainServices.addNotificationAttributes(model);

	      model.addAttribute("folders", folders);
		   model.addAttribute("myfolder",Folder); 
	       return "formsubfolder";   
	}     
    

    @RequestMapping(value="/editsubfolder-filemanager/{Folder}",method = RequestMethod.POST)    
    public String editsubfoldersave(  folder folders,log logs){   
    	
        folder foldersold = FolderDAO.getbyId(folders.getFolder_id()); 
	    folder mainfolders = FolderDAO.getbyId(foldersold.getFolder_idrefer());
   	    int drives_id;
   	    drives_id = folders.getFolder_idrefer();
   	    if(mainfolders != null) {
   	    drives_id= mainfolders.getFolder_idrefer();	
   	    }
   	    drives drivess  = DrivesDAO.getbyId(drives_id);
             

   	    

 	    String Pathfull;
 	    
 	    if(mainfolders != null) {

     	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
 	    }else {
 	    	
         	drives_id = folders.getFolder_idrefer();
         	drivess  = DrivesDAO.getbyId(drives_id);
         	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
     	    	
     	    }

        
        
        
     	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
		
		
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("EDIT");
		logs.setLog_date(date);
		logs.setLog_time(time);
		logs.setLog_note("เเก้ไขข้อมูลจาก Folder ชื่อเดิม "+ foldersold.getFolder_name() + " ชื่อใหม่ " + folders.getFolder_name());
		logs.setLog_refer(Integer.toString(foldersold.getFolder_id()));
		logs.setLog_folder(Pathfull);
		LogDAO.save(logs);
    	
    	
    	FolderDAO.update(folders);    
    	 return "redirect:/filemanager/"+drivess.getDrives_drive()+"/"+ mainfolders.getFolder_type()+"?id=" + mainfolders.getFolder_id();
  		
			
			
    }    
    
    
    
    
    
    @RequestMapping(value="/deletefolder-filemanager/{Folder}/{id}",method = RequestMethod.GET)    
    public String deletesfolder(@PathVariable int id,@PathVariable String Folder , log logs,HttpSession session,ModelMap model){  
    	
    	
  	    driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
    	folder folders = FolderDAO.getbyId(id);
    	
    	
    	boolean app_use = WorkflowDAO.getUsed(folders.getFolder_type());
     	if(app_use) {
    		 model.addAttribute("error","error 07"); 
    		 return "error";
    	 }
    	 
    	
    	
 	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
   	    int drives_id;
   	    drives_id = folders.getFolder_idrefer();
   	    if(mainfolders != null) {
   	    drives_id= mainfolders.getFolder_idrefer();	
   	    }
   	    drives drivess  = DrivesDAO.getbyId(drives_id);
             

   	    

 	    String Pathfull;
 	    
 	    if(mainfolders != null) {

     	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
 	    }else {
 	    	
         	drives_id = folders.getFolder_idrefer();
         	drivess  = DrivesDAO.getbyId(drives_id);
         	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
     	    	
     	    }

    	 
    	 
    	 
    	 //del all file and name file to  log
    	 
    	 List<file> files = FileDAO.getall(id);
    	 List<folder> Folders = FolderDAO.getall(id);
    	 String path;
    	 File fileToDelete;
    	 boolean success;
    	 
    	 
    		 for(file delfile : files) {
    			 ServletContext context = session.getServletContext(); 
    		     path = context.getRealPath("/filesuploads/" + delfile.getFile_path()); 
    		     fileToDelete = new File(path + File.separator + delfile.getFile_name());
    		     success = fileToDelete.delete();
    		     if(success) {
    		    	
    		    	 FileDAO.delete(delfile.getFile_id());
    		     }
    		 }
    
    		 for(folder delfolder : Folders) { 
    			 
    			 List<file> files2 = FileDAO.getall(delfolder.getFolder_id());
        		 for(file delfile : files2) {
        			 ServletContext context = session.getServletContext(); 
        		     path = context.getRealPath("/filesuploads/" + delfile.getFile_path()); 
        		     fileToDelete = new File(path + File.separator + delfile.getFile_name());
        		     success = fileToDelete.delete();
        		     if(success) {
        		    	 FileDAO.delete(delfile.getFile_id());
        		    	
        		     }
        		 }
    			 
    	    	 TypeDAO.update(delfolder.getFolder_type(),0);
    	    	 Group_authoritiesDAO.deleteforfoldertype(delfolder.getFolder_type());
    	    	 FolderDAO.delete(delfolder.getFolder_id());    
    	    	 
    		 }
    		 
    		 
    		 
    	 
      	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
     	 LocalDateTime localDate = LocalDateTime.now();//For reference
 	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
 	   	 String date = localDate.format(formatter);
 	   	 String time = localDate.format(formatter2);
 		
 		
 		logs.setLog_id_add(users.getUser_id());
 		logs.setLog_step("DELETE");
 		logs.setLog_date(date);
 		logs.setLog_time(time);
 		logs.setLog_note("ลบ Folder" + folders.getFolder_name());
 		logs.setLog_refer(Integer.toString(folders.getFolder_id()));
 		logs.setLog_folder(Pathfull);
 		LogDAO.save(logs);
    	 

    	 
    	 TypeDAO.update(folders.getFolder_type(),0);
    	 FolderDAO.delete(id);    
    	 Group_authoritiesDAO.deleteforfoldertype(folders.getFolder_type());
		 return "redirect:/filemanager/"+ driverDTOs.getDrives_drive()+ "?id="+driverDTOs.getDrives_id();
			
    }      
    
    @RequestMapping(value="/deletesubfolder-filemanager/{Folder}/{id}",method = RequestMethod.GET)    
    public String deletessubfolder(@PathVariable int id,@PathVariable String Folder,log logs,HttpSession session){  
    	 folder folders = FolderDAO.getbyId(id);
 	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
   	    int drives_id;
   	    drives_id = folders.getFolder_idrefer();
   	    if(mainfolders != null) {
   	    drives_id= mainfolders.getFolder_idrefer();	
   	    }
   	    drives drivess  = DrivesDAO.getbyId(drives_id);
                
   	    driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
	    folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
   	    

 	    String Pathfull;
 	    
 	    if(mainfolders != null) {

     	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
 	    }else {
 	    	
         	drives_id = folders.getFolder_idrefer();
         	drivess  = DrivesDAO.getbyId(drives_id);
         	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
     	    	
     	    }
       	 List<file> files = FileDAO.getall(id);
    	 String path;
    	 File fileToDelete;
    	 boolean success;
    	 
    		 for(file delfile : files) {
    		

    			 ServletContext context = session.getServletContext(); 
    		     path = context.getRealPath("/filesuploads/" + delfile.getFile_path()); 
    		     fileToDelete = new File(path + File.separator + delfile.getFile_name());
    		     success = fileToDelete.delete();
    		     if(success) {
    		    	 FileDAO.delete(delfile.getFile_id());
    		     }
    		
    		 }
    	 
    	 
    	 
    	 
       	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
     	 LocalDateTime localDate = LocalDateTime.now();//For reference
 	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
 	   	 String date = localDate.format(formatter);
 	   	 String time = localDate.format(formatter2);
 		
 		
 		logs.setLog_id_add(users.getUser_id());
 		logs.setLog_step("DELETE");
 		logs.setLog_date(date);
 		logs.setLog_time(time);
 		logs.setLog_note("ลบ Folder" + folders.getFolder_name());
 		logs.setLog_refer(Integer.toString(folders.getFolder_id()));
 		logs.setLog_folder(Pathfull);
 		LogDAO.save(logs);
    	 TypeDAO.update(folders.getFolder_type(),0);
    	 FolderDAO.delete(id);    
    	 return "redirect:/filemanager/"+driverDTOs.getDrives_drive()+"/"+ folderDTOs.getFolder_name()+"?id=" + folderDTOs.getFolder_id();
 		
			
    }      
    
    
    @RequestMapping(value="/deleteupload_pn-filemanager/{id}",method = RequestMethod.GET)    
    public String deleteupload_pn(@PathVariable int id,HttpSession session){  
    	 
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
    	
		  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
	   	 
    	Report_pnDAO.deletebyupload_pn(id, date);
    	Upload_pnDAO.delete(id);    
    	 return "redirect:/setinfo/"+folderDTOs.getFoldername()+"?id="+folderDTOs.getFolder_id();
    }    
   
    
    
    
    @RequestMapping(value="/deletemainupload_pn-filemanager/{id}",method = RequestMethod.GET)    
    public String deletemainupload_pn(@PathVariable int id,HttpSession session){  
    	 
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
    	

		  user users = UserDAO.getbyusername2(Userservice.getLoggedName());   
		  
		 int del =  Upload_pnDAO.delete(id,users.getUser_id());
		  if(del == 1) {
			  WorkflowDAO.StopGoal(id);
		  }
    
    	  
    	 return "redirect:/pntask-filemanager?group=1";
    }    
   
    
    
    
    @RequestMapping(value="/deletedrives-filemanager/{id}",method = RequestMethod.GET)    
    public String deletes(@PathVariable int id , log logs,ModelMap model){  
    	 drives drivess = DrivesDAO.getbyId(id);
    	 
    	 int num  = FolderDAO.numfolder(id);
    	 
    	 if(num > 0 ) {
    		 model.addAttribute("error","error 07"); 
    		 return "error";
    	 }
    	 
    	 
       	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
     	 LocalDateTime localDate = LocalDateTime.now();//For reference
 	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
 	   	 String date = localDate.format(formatter);
 	   	 String time = localDate.format(formatter2);
 		
 		
 		logs.setLog_id_add(users.getUser_id());
 		logs.setLog_step("DELETE");
 		logs.setLog_date(date);
 		logs.setLog_time(time);
 		logs.setLog_note("ลบ Drives" + drivess.getDrives_name());
 		logs.setLog_refer(Integer.toString(drivess.getDrives_id()));
 		logs.setLog_folder(drivess.getDrives_name());
 		LogDAO.save(logs);
    
    	 TypeDAO.update(drivess.getDrives_drive(),0);
    	 DrivesDAO.delete(id);    
    	 return "redirect:/filemanager/";
    }    
   
    
    @RequestMapping(value="/filemanager/{Drives}",method = RequestMethod.GET)  
    public String show(@PathVariable String Drives , ModelMap model , @RequestParam int id,HttpSession session,HttpServletRequest request ){ 
    	
		   	 
		    	  request.getSession().removeAttribute("prev");
		
		  	    // บันทึก Referrer ไว้ใน session (ถ้ามี)
		  	    String referer = "/filemanager/"+Drives+"?id="+id;
		  	    if (referer != null) {
		  	        request.getSession().setAttribute("prev", referer);
		  	    }
  	   
    	
	    	 drives drivess = DrivesDAO.getbyId(id);
	 	   
	 	    if(!drivess.getDrives_drive().equals(Drives)) {
	 	    	 model.addAttribute("error","error 05"); 
	  	         return "error";
	 	    }
    	
	 		  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");

	 	    
    	
		   List<folder> folders = FolderDAO.getalls(id);
		   driverDTOs.setDrives_id(id);
		   driverDTOs.setDrives_drive(Drives);
		   
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			 ArrayList lists = new ArrayList();
			for (SimpleGrantedAuthority permission : list) {
			     lists.add(permission.getAuthority());
			}
			
			 MainServices.addNotificationAttributes(model);
			
			  session.setAttribute("driverDTOs", driverDTOs);
			 
			 
			  model.addAttribute("permission",lists);
		   model.addAttribute("folders",folders); 
		   model.addAttribute("show","folders"); 
		   model.addAttribute("Drives", driverDTOs.getDrives_drive()); 
		   model.addAttribute("Drivesname", drivess.getDrives_name()); 
		   model.addAttribute("Drivesid", driverDTOs.getDrives_id()); 
		   model.addAttribute("name", Userservice.getLoggedName());
    	   return "filemanager";
    }    
    
    
    @RequestMapping(value="/filemanager/{Drives}/{Folders}",method = RequestMethod.GET)   
    public String show2(@PathVariable String Drives , ModelMap model 
    		, @RequestParam int id 
    		, @PathVariable String Folders
    		,HttpSession session
    		,HttpServletRequest request){    
		 
    	
    	 request.getSession().removeAttribute("prev");

 	    // บันทึก Referrer ไว้ใน session (ถ้ามี)
 	    String referer = "/filemanager/"+Drives+"/"+Folders+"?id="+id;
 	    if (referer != null) {
 	        request.getSession().setAttribute("prev", referer);
 	    }
 	   
    	
    	
           folder myroot = FolderDAO.getbyId(id);
    	   
    	   if(!myroot.getFolder_type().equals(Folders)) {
    		   model.addAttribute("error","error 05"); 
	  	         return "error";
    	   }
    	   
    	  
    	   
    	   
    		  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
    	   
    	   List<folder> folders =  FolderDAO.getalls(id);
		   List<file> files = FileDAO.getall(id);
		   folderDTOs.setFolder_id(id);
		   folderDTOs.setFolder_name(Folders);
		   folder mainfolders = FolderDAO.getbyId(id);
		   drives drivess = DrivesDAO.getbyId(mainfolders.getFolder_idrefer());
		   
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			 ArrayList lists = new ArrayList();
			for (SimpleGrantedAuthority permission : list) {
			     lists.add(permission.getAuthority());
			}
			

			group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
			groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
			model.addAttribute("myjd",groupss.getGroup_name());
			
			
			 MainServices.addNotificationAttributes(model);
			
			folder oldfolder = FolderDAO.getbytype(Folders);
			  session.setAttribute("folderDTOs", folderDTOs);
			
			  model.addAttribute("permission",lists);
		   model.addAttribute("folders",folders); 
		   model.addAttribute("show","subfolders"); 
		   model.addAttribute("show2","file"); 
		   model.addAttribute("Drives", drivess.getDrives_drive()); 
		   model.addAttribute("Folders",oldfolder.getFolder_type()); 
		   model.addAttribute("Drivesname",drivess.getDrives_name()); 
		   model.addAttribute("Foldername",mainfolders.getFolder_name()); 
		   model.addAttribute("Drivesid", drivess.getDrives_id()); 
		   model.addAttribute("MFoldersid", 0); 
		   model.addAttribute("Foldersid", mainfolders.getFolder_id()); 
		   model.addAttribute("uploadpnid", id); 
		   model.addAttribute("myroot", myroot); 
		   model.addAttribute("file",files); 
		   model.addAttribute("level",2); 
		   model.addAttribute("name", Userservice.getLoggedName());
		   model.addAttribute("Folderstype",Folders); 
    	   return "filemanager";
    }   

    
    
    @RequestMapping(value="/filemanager/{Drives}/{Folders0}/{Folders}",method = RequestMethod.GET)   
    public String show3(@PathVariable String Drives 
    		, ModelMap model 
    		, @RequestParam int id 
    		, @PathVariable String Folders, @PathVariable String Folders0
    		,HttpSession session
    		,HttpServletRequest request
    		){ 
    	
      
      	
		   	 request.getSession().removeAttribute("prev");
		
			    // บันทึก Referrer ไว้ใน session (ถ้ามี)
			    String referer = "/filemanager/"+Drives+"/"+Folders0+"/"+Folders+"?id="+id;
			    if (referer != null) {
			        request.getSession().setAttribute("prev", referer);
			    }
			   
		   	
    	
    	
        	
        	
    	  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
    	  
    	   folder myroot = FolderDAO.getbyId(id);
    	   
    	   if(!myroot.getFolder_type().equals(Folders)) {
    		   
    		   model.addAttribute("error","error 05"); 
 	            return "error";
    	   }
    	  
		   List<folder> folders =  FolderDAO.getalls(id);
		    List<file> files = FileDAO.getall(id);
		   folderDTOs.setFolder_subid(id);
		   folderDTOs.setSubfoldername(Folders);
		   
		   
		   folder subfolders = FolderDAO.getbyId(id);
		   folder mainfolders = FolderDAO.getbyId(subfolders.getFolder_idrefer());
		   drives drivess = DrivesDAO.getbyId(mainfolders.getFolder_idrefer()); 
		   
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			 ArrayList lists = new ArrayList();
			for (SimpleGrantedAuthority permission : list) {
			     lists.add(permission.getAuthority());
			}
			
			 MainServices.addNotificationAttributes(model);
			

			group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
			groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
			model.addAttribute("myjd",groupss.getGroup_name());
			
			
			 folder oldfolder = FolderDAO.getbytype(Folders0);
			
			  session.setAttribute("folderDTOs", folderDTOs);
			
			  model.addAttribute("permission",lists);
		   model.addAttribute("folders",folders); 
		   model.addAttribute("show","subfolders"); 
		   model.addAttribute("shows","subfolderss"); 
		   model.addAttribute("show2","file"); 
		   model.addAttribute("Drivesname",drivess.getDrives_name()); 
		   model.addAttribute("Foldername",mainfolders.getFolder_name());
		   model.addAttribute("Subfoldername",subfolders.getFolder_name());
		   model.addAttribute("Drives", drivess.getDrives_drive()); 
		   model.addAttribute("Drivesid", drivess.getDrives_id()); 
		   model.addAttribute("Folders", oldfolder.getFolder_type()); 
		   model.addAttribute("Foldersid", oldfolder.getFolder_id()); 
		   model.addAttribute("MFoldersid", mainfolders.getFolder_id()); 
		   model.addAttribute("file",files); 
		   model.addAttribute("uploadpnid", id);
		   model.addAttribute("level",3); 
		   model.addAttribute("Folderstype",Folders); 
		   model.addAttribute("name", Userservice.getLoggedName());
		   model.addAttribute("myroot", myroot); 
		   
    	   return "filemanager";
    }     
    
    
    @RequestMapping(value="/deleteinfo-filemanager/{Drives}/{Folders}/{id}",method = RequestMethod.GET)    
    public String deleteinfo(@PathVariable int id,@RequestParam int level ,HttpSession session,HttpServletRequest request){  
    	
    	
    	
    	     file files = FileDAO.getbyId(id);
		     ServletContext context = session.getServletContext(); 
		     String path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
		     File fileToDelete = new File(path + File.separator + files.getFile_name());
		   
		     
		    
		     File f = new File(path + File.separator + files.getFile_name());
		     if(f.exists() && !f.isDirectory()) { 
		    	  boolean success = fileToDelete.delete();
				     if(success) {
				    	  FileDAO.delete2(id);   
				    	  
						
				     }
		     }else {
		    	
		    	
				     FileDAO.delete(id);
		     }
		     
		     folder folders =   FolderDAO.getbyId(files.getFile_folder());
		     Filemanagerservice.UpdateMemorySize(folders,files.getFile_size(),0);
		


		     String prevPage = (String) request.getSession().getAttribute("prev");
		     System.out.println(" prevPage" +prevPage);
		     
		     return "redirect:" + (prevPage != null ? prevPage : "/defaultPage");
    }   
    
    @RequestMapping(value="/setfromprint-filemanager/{process_id}/{data_id}",method = RequestMethod.GET)    
    public String setfromprint( @PathVariable int process_id , @PathVariable int data_id ,ModelMap model){ 
    	
    	List<FormPrint> FormPrint = EdataDAO.getAllFormPrint(process_id);
 	   	model.addAttribute("listForm",FormPrint); 
	     
		Platform platform = new Platform();
		platform.setApp_processes_id(process_id);	
		
		LoadApp   LoadApp =   new LoadApp();
		platformService.initiate(LoadApp).LoadComponent(platform).input().load();
		Map<String, Object> data = platformService.getModelData();
		model.addAllAttributes(data);
   		model.addAttribute("data_id",data_id);
    	return "app/windows/config/setfromprint";
    }
    
    @RequestMapping(value="/importformprint-filemanager",method = RequestMethod.POST)    
    public String setfromprint(FormPrint formPrint,@RequestParam int remove_id,@RequestParam int data_id){ 
 		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	if(remove_id > 0) {
   
    		EdataDAO.removeFormPrint(remove_id,users.getUser_id());
    	}
    	formPrint.setId_add(users.getUser_id());
    	EdataDAO.saveFormPrint(formPrint);
    	return "redirect:/setprint-filemanager/"+formPrint.getProcess_id()+"/"+data_id;
    }
    
    
    
    
    @RequestMapping(value="/fileinfo-filemanager/{Drives}/{Folders}/",method = RequestMethod.GET)    
    public String fileinfo( @RequestParam int id , ModelMap model ,log logs,@PathVariable String Folders){ 
    	
    	
    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
	   	
	 	 file datafiles = null;
	   	 file files = FileDAO.getbyId(id);
		
	   	 if(files.getFile_shotcut() != null && files.getFile_parent() > 0) {
	   		datafiles = FileDAO.getbyId(files.getFile_parent());
	   	 }else {
	   		datafiles = files;
	   	 }
	   	 
	   	 
	   	 
	 	//GET PATH
			
			folder folders = FolderDAO.getbytype(files.getFile_path());   	
			folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
 	    String Pathfull;
 	    String Pathfulls;
 	    drives drivess;
 	    int drives_id;
 	    if(mainfolders != null) {
 	    drives_id= mainfolders.getFolder_idrefer();	
 	    drivess  = DrivesDAO.getbyId(drives_id); 
 	    }else {
     	drives_id = folders.getFolder_idrefer();
     	drivess  = DrivesDAO.getbyId(drives_id);		
 	    }
			
			//
	   	 
	   	 
	   	 
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("READ");
		logs.setLog_date(date);
		logs.setLog_time(time);
		logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
		logs.setLog_refer(Integer.toString(id));
		logs.setLog_folder(files.getFile_path_full());
		LogDAO.save(logs);
    	
    	
		
    	
    	   List<showdar> dars = DarDAO.getallbyfile(datafiles.getFile_no());
    	   
    	   
    
    	   
    	   List<file> fileold = FileDAO.getallexpiredbyfileno(datafiles.getFile_no());
    	   List<file> shortcut = FileDAO.getallshortcut(datafiles.getFile_id());
    	   List<showlog> logsed = LogDAO.getallbyidrefer(id);
    	   model.addAttribute("shortcut",shortcut);
    	   model.addAttribute("log",logsed);
    	   
    	   MainServices.addNotificationAttributes(model);
   		
   		   
   		   
   		   model.addAttribute("fileold",fileold); 
   		   
    	   model.addAttribute("dar",dars); 
    	   model.addAttribute("file",files);
    	   model.addAttribute("Drives", drivess.getDrives_drive());

    	  // model.addAttribute("log",logsed); 
    	   
           
    	   return "fileinfo"; 		
    }    
    
    
    @RequestMapping(value="/showpdf-filemanager/{Drives}/{Folders}/",method = RequestMethod.GET)    
    public String showpdf( @RequestParam int id , ModelMap model ,log logs,@PathVariable String Folders,@PathVariable String Drives){ 
    	
    	
    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
	   	 file files = FileDAO.getbyId(id);
		
	   	if( !LogDAO.check(id,date,time) ) {
	   	 
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("READ");
		logs.setLog_date(date);
		logs.setLog_time(time);
		logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
		logs.setLog_refer(Integer.toString(id));
		logs.setLog_folder(files.getFile_path_full());
		LogDAO.save(logs);
	   	}
    	
    	  
    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
    	   
    	   MainServices.addNotificationAttributes(model);
   		    model.addAttribute("path",Folders);
    	   model.addAttribute("dar",dars); 
    	   model.addAttribute("file",files);
    	   model.addAttribute("id",id);
    	   model.addAttribute("Drives",Drives);
    	   return "/other/pdfview"; 		
    }    
    
    
    
    
    @RequestMapping(value="/list-filemanager",method = RequestMethod.GET)    
    public String list(ModelMap model){ 
    	
    	List<file> files = FileDAO.getall2();
    	
    	 MainServices.addNotificationAttributes(model);
		
    	model.addAttribute("file",files); 
    	return "filelist";
    }
    

    @RequestMapping(value="/attendance-filemanager",method = RequestMethod.GET)    
    public String attendance(ModelMap model,@RequestParam(value = "MONTH",required=false,defaultValue="-") String month){ 
    	
    	
    	List<user> users = UserDAO.getall();
  		model.addAttribute("users",users);
  		
  		
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM");
	  	LocalDateTime localDate1 = LocalDateTime.now();
	   	
	 	String Start = null;
	 	String End = null;
	 	
     
       /*
      	List<log> logs = LogDAO.getPage(Start,End,OFFSET);
        int totalPages = LogDAO.getNum(Start,End);
   		model.addAttribute("log",logs);
	    model.addAttribute("totalPages", totalPages);
		


	    model.addAttribute("OFFSET",month);
	    model.addAttribute("type", month); // ส่ง ID กลับไปด้วย
	    */
	    MainServices.addNotificationAttributes(model);
    	return "time2";
	    
	   
    }
    
    
    @RequestMapping(value="/logattendance-filemanager",method = RequestMethod.GET)    
    public String logattendance(ModelMap model,@RequestParam String type,@RequestParam(value = "OFFSET",required=false,defaultValue="1") int OFFSET){ 
    	

	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	
	 	String Start = null;
	 	String End = null;
	 	
       if(type.equals("month")) {
    	   
	       	LocalDateTime localDate1 = LocalDateTime.now().minusMonths(1);
	    	LocalDateTime localDate2 = LocalDateTime.now();
	    	Start = localDate1.format(formatter);
	    	End = localDate2.format(formatter);
       }
       else if(type.equals("year")) {
	       	LocalDateTime localDate1 = LocalDateTime.now().minusYears(1);
	    	LocalDateTime localDate2 = LocalDateTime.now();
	    	Start = localDate1.format(formatter);
	    	End = localDate2.format(formatter); 	   
		}
       else{
	       	LocalDateTime localDate1 = LocalDateTime.now().minusDays(1);
	    	LocalDateTime localDate2 = LocalDateTime.now();
	    	Start = localDate1.format(formatter);
	    	End = localDate2.format(formatter);    
		 }

      	List<log> logs = LogDAO.getPage(Start,End,OFFSET);
        int totalPages = LogDAO.getNum(Start,End);
   		model.addAttribute("log",logs);
	    model.addAttribute("totalPages", totalPages);
		


	    model.addAttribute("OFFSET",OFFSET);
	    model.addAttribute("type", type); // ส่ง ID กลับไปด้วย
	    
	    MainServices.addNotificationAttributes(model);
    	return "logtime";
	    
	   
    }
   

    
    
    
    @RequestMapping(value="/attendance2-filemanager",method = RequestMethod.GET)    
    public String attendance2(ModelMap model){ 
    	
    	List<file> files = FileDAO.getall2();
    	
    	 MainServices.addNotificationAttributes(model);
		
    	model.addAttribute("file",files); 
    	return "time2";
    }
    
    @RequestMapping(value="/idoc-filemanager",method = RequestMethod.GET)    
    public String idoc(ModelMap model){ 
    	
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
	
    	
    	
    	upload_pn upload_pn_date = Upload_pnDAO.StatusCount(0,0,users);
    	model.addAttribute("upload_pn_date",upload_pn_date);
    	
    	upload_pn upload_pn_week = Upload_pnDAO.StatusCount(0,1,users);
    	model.addAttribute("upload_pn_week",upload_pn_week);
    	
    	upload_pn upload_pn_month = Upload_pnDAO.StatusCount(0,2,users);
    	model.addAttribute("upload_pn_month",upload_pn_month);
    	
    	

    	MainServices.addNotificationAttributes(model);   	
    	return "filepn_idoc";
    	
    	
    	
    	
    	
    	
    	
    	/*
    	
    	group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
		groups jd = GroupsDAO.getbyIds(group_memberss.getGroup_id());
		
		
    	List<file> files = FileDAO.getallidoc(jd.getId());
    	model.addAttribute("file",files); 
    	
    	
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
	   	 String year = localDate.format(formatter);
	   	 
	   	 
    
    	int num_login =  LogDAO.countLoginYear(year,users.getUser_id());
    	model.addAttribute("num_login",num_login);
    
    	
    	
    	
    	int num_daily_targrt =  Report_pnDAO.countDailyYeardue(year,users.getUser_name(),0);
    	model.addAttribute("num_daily_targrt",num_daily_targrt);
    	
    	int num_weekly_targrt =  Report_pnDAO.countDailyYeardue(year,users.getUser_name(),1);
    	model.addAttribute("num_weekly_targrt",num_weekly_targrt);
    	
    	int num_monthly_targrt =  Report_pnDAO.countDailyYeardue(year,users.getUser_name(),2);
    	model.addAttribute("num_monthly_targrt",num_monthly_targrt);
    	
    	
    	int num_daily =  Report_pnDAO.countDailyYear(year,users.getUser_name(),0);
    	model.addAttribute("num_daily",num_daily);
    	
    	int num_weekly =  Report_pnDAO.countDailyYear(year,users.getUser_name(),1);
    	model.addAttribute("num_weekly",num_weekly);
    	
    	int num_monthly =  Report_pnDAO.countDailyYear(year,users.getUser_name(),2);
    	model.addAttribute("num_monthly",num_monthly);
    	
    	
    	
    	int num_annually_targrt =  Report_pnDAO.countDailyYeardue2(year,users.getUser_name());
    	model.addAttribute("num_annually_targrt",num_annually_targrt);
    	int num_annually =  Report_pnDAO.countDailyYear2(year,users.getUser_name());
    	model.addAttribute("num_annually",num_annually);

    	List<report_pn> rank =  Report_pnDAO.getrank(year);
    	
   
    	int  ranks1= 0;
    	int  ranks2= 0;
    	int  numranks1= 0;
    	int  numranks2= 0;
    	
    	
    	for(report_pn ranks : rank) {

    		numranks1++;
    		if(ranks.getReport_pn_respon().equals(users.getUser_name())) {
    			ranks1 = numranks1;
    		}
    		System.out.print(" respon " + ranks.getReport_pn_respon());
			System.out.println(" rank " + ranks.getReport_pn_total());
    	}
    	
    	
    	List<report_pn> rank2 =  Report_pnDAO.getrank2(year);
    	
    	   
    	
    	for(report_pn ranksed : rank2) {

    		numranks2++;
    		if(ranksed.getReport_pn_respon().equals(users.getUser_name())) {
    			ranks2 = numranks2;
    		}
    		System.out.print(" responed " + ranksed.getReport_pn_respon());
			System.out.println(" ranked " + ranksed.getReport_pn_total());
    	}

    	
    	
    	model.addAttribute("ranks1",ranks1 == 0  ?  "-":ranks1);
    	model.addAttribute("ranks2",ranks2== 0  ?  "-":ranks2);
    	
    	
    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
    	 MainServices.addNotificationAttributes(model);
		model.addAttribute("groups",group_memberss.getGroup_id());
		
		
    	List<showupload_pn> upload_pn = Upload_pnDAO.getallby(usersed.getUser_name());
    	model.addAttribute("upload_pn",upload_pn);
    	
    	
    	List<report_pn> report_pn = Report_pnDAO.getallresponandapp(usersed.getUser_name(),year);
    	model.addAttribute("report_pn",report_pn);
    		
       	List<task> task = TaskDAO.getallresponandapp(usersed.getUser_name(),usersed.getUser_id(),year);
    	model.addAttribute("task",task);
    	
    	List<holiday> holiday = HolidayDAO.getall(year);
    	model.addAttribute("holidays",holiday);
    	
        model.addAttribute("name", Userservice.getLoggedName());
        model.addAttribute("Member_name", users.getUser_name());
        */

        
        
    	//return "idoc";
    }
    
    
    
    @RequestMapping(value="/listpn2-filemanager",method = RequestMethod.GET)    
    public String list_file_upload_pn2(ModelMap model,@RequestParam int due,@RequestParam int type){ 
    	
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());  

    	List<upload_pn> upload_pns = Upload_pnDAO.getByDue_Type(due, type,users);
		model.addAttribute("upload_pns",upload_pns);	
		MainServices.addNotificationAttributes(model);
    	return "list_filepn2";
    }
    
    @RequestMapping(value="/detailspn2-filemanager",method = RequestMethod.GET)    
    public String detailspn2(ModelMap model,@RequestParam int id,@RequestParam(value = "OFFSET",required=false,defaultValue="1") int OFFSET){ 
    	
    	
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	
    	upload_pn upload_pns = Upload_pnDAO.getbyId(id);
    	model.addAttribute("upload_pns",upload_pns);
    	
    	List<report_pn> report_pns = Report_pnDAO.getPage(id,OFFSET,users);
    	List<report_pn> totalPages = Report_pnDAO.getall2(id,users);
		model.addAttribute("report_pn",report_pns);
		
		
	    model.addAttribute("reports",report_pns);
	    model.addAttribute("totalPages", totalPages.size());
	    model.addAttribute("OFFSET",OFFSET);
	    model.addAttribute("id", id); // ส่ง ID กลับไปด้วย
	    
	    MainServices.addNotificationAttributes(model);
    	return "list_report_pns";
    }
   
    
    
    
    @RequestMapping(value="/pntask-filemanager",method = RequestMethod.GET)    
    public String pntask(ModelMap model,@RequestParam(value = "group",required=false) int group){ 
    	
    
        user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
    	MainServices.addNotificationAttributes(model);
		
	
		
		
	
		jobview jobviews = null;
		if(Upload_pnDAO.isjobviewExists(usersed.getUser_name())) {
			 jobviews = Upload_pnDAO.getView(usersed.getUser_name());
		}else {
			jobview jobview = new jobview();
			jobview.setJobview_tab(0);
			jobview.setJobview_user_name(usersed.getUser_name());
			Upload_pnDAO.saveview(jobview);
			
			jobviews = Upload_pnDAO.getView(usersed.getUser_name());
		}
		
		
		model.addAttribute("jobview_type",jobviews.getJobview_type()); 
		model.addAttribute("jobview_category",jobviews.getJobview_category()); 
		model.addAttribute("jobview_app",jobviews.getJobview_app()); 
		
		
		 //jobviews = Upload_pnDAO.getView(usersed.getUser_name());
		
		 List<upload_pn> app =   Upload_pnDAO.getGroup();
	
		 int Pending = Report_pnDAO.getPending(usersed.getUser_name());
		
		 
			model.addAttribute("pending",Pending); 
			model.addAttribute("app",app); 
		

			
			
			
			
		List<upload_pn> workallraw = Upload_pnDAO.getallbywork(usersed.getUser_name(),usersed.getUser_id());	

		workallraw.stream().forEach(row->{
			
			 
		         String USER_GROUP = "guest";
		         
		    	if(row.getId_policy() > 0) {
			   	 	 Policy policy  = policyRepository.findById(row.getId_policy());
			   	 	 USER_GROUP = policyService.getcheckAccessByPolicy(row,usersed,policy);
		    	}else {
		    		USER_GROUP = policyService.getcheckAccess(row,usersed);
		    	}
		    	
		    	if(USER_GROUP.equals("uploader") || USER_GROUP.equals("admin")) {
		    		
		    		 Goal MyGoal =WorkflowDAO.getFindGoalPN(row.getUpload_pn_id(),usersed.getUser_name());
		    		 UploadpnTurn  turn = Upload_pnDAO.findUploadpnTurn(row.getUpload_pn_id(),usersed.getUser_name());
		    		 int MissedUploads =WorkflowDAO.countMissed_uploads(MyGoal.getId(),usersed.getUser_name());
		    		 row.setUpload_pn_alert(MissedUploads);
		    		 row.setTurn(turn.getTurn());
		    	
		    	}else {
		    		 int MissedUploads =WorkflowDAO.countGoalByprocessesAnaTableByMissed_uploads(row.getUpload_pn_id(),"upload_pn");
		    		 row.setUpload_pn_alert(MissedUploads);
		    	}
		    	
			
		
			
		});


		 List<upload_pn>  workall = workallraw.stream().filter(row->row.getUpload_pn_status() == 0).collect(Collectors.toList());
		 
		workall.sort((row1, row2) -> Integer.compare(row2.getUpload_pn_alert(), row1.getUpload_pn_alert())); // จัดเรียงตาม Upload_pn_alert (เรียงจากมากไปหาน้อย)
		

		model.addAttribute("workall",workall); 

		
		
			
		model.addAttribute("Member_name",usersed.getUser_name());
		model.addAttribute("Member_id",usersed.getUser_id());
		model.addAttribute("group",group); 

		
    	
    	return "filepn1";
    }
     
    
    
    
    
    
    @RequestMapping(value="/pn2-filemanager",method = RequestMethod.GET)    
    public String listupload_pn2(ModelMap model
    		, @RequestParam(value = "type",required=false)  int type
    		, @RequestParam(value = "group",required=false)  int group){ 
    	
		LocalDateTime todays = LocalDateTime.now();//For reference
	    DateTimeFormatter formatters2 = DateTimeFormatter.ofPattern("yyyy");
	    String year = todays.format(formatters2);
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    
	    if(group == 0) {
	    	List<showupload_pn> upload_pn = Report_pnDAO.getalltype(year,type);
	      	model.addAttribute("upload_pn",upload_pn); 
	    }else {
	    	List<showupload_pn> upload_pn = Report_pnDAO.getalltype(year,type,usersed.getUser_name());
	      	model.addAttribute("upload_pn",upload_pn); 
	    }
    	

    	
	    
	    
	    
	    
	    
	    
	    
    	
	    MainServices.addNotificationAttributes(model);
		

		report_pn  reportC = Report_pnDAO.getallstatus(year);
		
		
		System.out.println(reportC + "reportC");
		
		if(reportC != null) {
		model.addAttribute("totalerror",reportC.getReport_pn_sendstatus()); 
		model.addAttribute("totalerror2",reportC.getReport_pn_statuserror()); 
		}else {
			model.addAttribute("totalerror",0); 
			model.addAttribute("totalerror2",0);	
		}
		

  
    	return "filepns";
    }
    
    
    

    @RequestMapping(value="/getlot-filemanager",method = RequestMethod.POST)    
    public String getlot(ModelMap model
    		,@RequestParam(defaultValue = "0") int formid
    		,@RequestParam(defaultValue = "0") int field
    		,@RequestParam(defaultValue = "-") String value
    		,@RequestParam(defaultValue = "0") long product_no
    		,@RequestParam(defaultValue = "0") long amount_no
    		,@RequestParam(defaultValue = "0") int conditionid
    		){ 
    	
    
    	
    	
    	
    	condition conditions = ConditionDAO.getbyids(formid);
    
    	
    	
    	
    	
    	
    	
    	
    	if(conditions != null) {
    		
    		System.out.println("step 4 ");
    		condition conditions2 = ConditionDAO.getbyids(conditionid);
    		List<eformfield> mainefield =  EformfieldDAO.getall(conditions2.getCondition_id(), conditions2.getCondition_rv());
        	  int no = 0;
        	  int productkey_no =0;
        	  int data_no =0;
        	  for(eformfield fields : mainefield) {
        		  	  no++; 
        		  	  
        		  	  
        		 if(fields.getField_cal_type() != null) { 	  
        			 
    		    		  	if(fields.getField_cal_type().equals("product")) {
    		    		  		
    		    		  		productkey_no = no;
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("lot")) {
    		    		  		
    		    		  		
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("minus")) {
    		    		  		data_no = no;
    		    		  		
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("update")) {
    		    		  		
    		    		  		
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("plus")) {
    			
    		    		  	}
    		    		  	
        		 }
        		  	
        	      	
        		  	
        	  }
        	
        	  
    	
    	System.out.println("step 1 ");
    	List<edata> subedatas = EdataDAO.getalllot(conditions.getCondition_id(),conditions.getCondition_rv(),value,product_no);
    	model.addAttribute("subedatas",subedatas);  
    	System.out.println("subedatas " + subedatas.size());
    	
    	
    	
    	System.out.println("step 2");
    	eformfield key1 = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"order");
    	eformfield key2 = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"total");
    	
    	
    	
    	System.out.println("step 3 ");
        if(key1 != null) {
		 	edata orders = EdataDAO.getCal(conditions.getCondition_id(),conditions.getCondition_rv(),value,product_no,key1.getField_postion());
		 	
		 	EdataDAO.setOrder(orders.getData_id(),key1.getField_postion());
			model.addAttribute("orders",orders.getData_value1());  

        }
        
        if(key2 != null) {
        	edata totals = EdataDAO.getCal(conditions.getCondition_id(),conditions.getCondition_rv(),value,product_no,key2.getField_postion());
        	model.addAttribute("totals",totals.getData_value1());    
        	
 
        	
        	
        	edata reserve = EdataDAO.getReserve(conditions2.getCondition_id(),conditions2.getCondition_rv(),value,productkey_no,data_no);
        	if(reserve != null) {model.addAttribute("reserve",reserve.getData_value1());} 
        }
	 
      
    		System.out.println("step 5 "); 
    		  	  
	 	edata amounts = EdataDAO.getAmount(conditions2.getCondition_id(),conditions2.getCondition_rv(),value,productkey_no,null,0,amount_no);
	   if(amounts != null) {
		   
		   System.out.println("amounts no "+amount_no); 
		   model.addAttribute("amounts",amounts.getData_value1()); 
	   }else {
		   model.addAttribute("amounts",0); 
	   } 
	 	
		System.out.println("step 6 "); 
	 	
	 	
	 	model.addAttribute("field",field);  
    	}
	

	 	
    			return "/eformparts/lot";
    			
       }
    
    
    
    @RequestMapping(value="/setproject-filemanager",method = RequestMethod.POST)    
    public String setproject(upload_pn upload_pns){ 
    	Upload_pnDAO.setProject(upload_pns);
    	return "redirect:/duepn-filemanager?id="+upload_pns.getUpload_pn_id()+"&&step=0&&group=1";
    }
    
    
    @RequestMapping(value="/getlot2-filemanager",method = RequestMethod.POST)    
    public String getlot2(ModelMap model
    		,@RequestParam(defaultValue = "0") int formid
    		,@RequestParam(defaultValue = "0") int field
    		,@RequestParam String value
    		,@RequestParam String value2
    		,@RequestParam(defaultValue = "0") long product_no
    		,@RequestParam(defaultValue = "0") long amount_no
    		,@RequestParam(defaultValue = "0") int conditionid
    		){ 
    	
    	condition conditions = ConditionDAO.getbyids(formid);
    	
 
    	if(conditions != null) {
    		
    		condition conditions2 = ConditionDAO.getbyids(conditionid);
    		List<eformfield> mainefield =  EformfieldDAO.getall(conditions2.getCondition_id(), conditions2.getCondition_rv());
        	  int no = 0;
        	  int productkey_no =0;
        	  int lot_no =0;
        		long data_no =0;
        	  for(eformfield fields : mainefield) {
        		  	  no++; 
        		  	  
        		  	  
        		 if(fields.getField_cal_type() != null) { 	  
        			 
    		    		  	if(fields.getField_cal_type().equals("product")) {
    		    		  		
    		    		  		productkey_no = no;
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("lot")) {
    		    		  		lot_no = no;
    		    		  		
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("minus")) {
    		    		  		
    		    		  		data_no= no;
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("update")) {
    		    		  		
    		    		  		data_no= no;
    		    		  	}
    		    		  	if(fields.getField_cal_type().equals("plus")) {
    		    		  		data_no= no;
    		    		  	}
    		    		  	
        		 }
        		  	
        		  	
        		  	
        	  }	
    		
        	  System.out.println("data_no"  +data_no);
    	edata reserve = EdataDAO.getReserve(conditions2.getCondition_id(),conditions2.getCondition_rv(),value,value2,productkey_no,lot_no,data_no);
    	if(reserve != null) {model.addAttribute("reserve",reserve.getData_value1());} 
    		

      	eformfield key1 = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions2.getCondition_rv(),"order");
    	eformfield key2 = EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions2.getCondition_rv(),"balance");
    	
    	
    	
    	
        if(key1 != null) {
        	
			edata orders = EdataDAO.getCal(conditions.getCondition_id(),conditions.getCondition_rv(),value,product_no,value2,field,key1.getField_postion());
			
			  System.out.println("orders"  +orders);
			
			model.addAttribute("orders",orders.getData_value1());  
			//EdataDAO.setOrder(orders.getData_id(),key1.getField_postion());
        }
        
        if(key2 != null) {
        	  System.out.println("key2"  +key2);	
         	edata totals = EdataDAO.getCal(conditions.getCondition_id(),conditions.getCondition_rv(),value,product_no,value2,field,key2.getField_postion());
        	model.addAttribute("totals",totals.getData_value1());    
        }
	 
    	
	 	
    	
    		  	  

	 	edata amounts = EdataDAO.getAmount(conditions2.getCondition_id(),conditions2.getCondition_rv(),value,productkey_no,value2,lot_no,amount_no);
	
	 	if(amounts != null) {
	 		
	 		
	 	 	model.addAttribute("amounts",amounts.getData_value1()); 
	 	}else {
	 	 	model.addAttribute("amounts",0); 
	 	}
	 	
    	}
	 	
    		return "/eformparts/cal";
    			
       }
    
    
    
    
    
    
    
        
    @RequestMapping(value="/duepn-filemanager",method = RequestMethod.GET)    
    public String duepn(ModelMap model,@RequestParam int id
    		,@RequestParam(value = "idoc",required=false) String idoc
    		,@RequestParam(value = "save",required=false) String save
    		,@RequestParam(value = "step",required=false) String step
    		,@RequestParam(value = "group",required=false) int group
    		){ 
    	
    
    	
    	
    	System.out.println("bug 1");
    	
    	LocalDateTime localDate2 = LocalDateTime.now().minusDays(10);//For reference
    	LocalDateTime localDate3 = LocalDateTime.now().minusMonths(1);//For reference
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM");
	   	 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM");
	   	 String date = localDate.format(formatter);
	   	 String date2 = localDate2.format(formatter);
         upload_pn upload_pns = Upload_pnDAO.getbyId(id);
    	
    	
        
         user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
         String USER_GROUP = "guest";
         
    	if(upload_pns.getId_policy() > 0) {
	   	 	 Policy policy  = policyRepository.findById(upload_pns.getId_policy());
	   	 	 String name = policy.getMembers().stream().map(row-> row.getEmployeeName()).limit(5).collect(Collectors.joining(","));
	   	 	 upload_pns.setUpload_pn_respon(name);
	   	 	 
	   	 	USER_GROUP = policyService.getcheckAccessByPolicy(upload_pns,usersed,policy);
	   	 	model.addAttribute("USER_GROUP",USER_GROUP);
	   	 	
    	}else {
    		USER_GROUP = policyService.getcheckAccess(upload_pns,usersed);
    	 	model.addAttribute("USER_GROUP",USER_GROUP); 
    	}
    
    	
     	List<report_pn> report_pns2 = null;
    	Upload_pnDAO.setActive(id,date);

    	if(usersed == null) {model.addAttribute("error","user_null"); return "filepn2";	}
    
    	

    	
    	
        List<report> reportall = ReportDAO.getall(id);
        model.addAttribute("reportall",reportall); 
        
        
        
    
    	
    	
    	if(upload_pns == null) {
    		Report_pnDAO.deletebyreport(id);
    		model.addAttribute("error","report_null"); 
    		return "filepn2";
    	}
    	
    	System.out.println("bug 2");
    	
		if(upload_pns.getUpload_pn_monitor() > 0 ) {
			
			
			System.out.println("bug 3");
			condition conditions = ConditionDAO.getbyids((int)upload_pns.getUpload_pn_monitor());
			
			workflow app = null;
			
			if(conditions != null) {
				app =  WorkflowDAO.getApp(conditions.getCondition_id());
			}

		

			 
	
			
			if(conditions != null) {
				form form = FormDAO.getbyids(conditions.getCondition_id());
				model.addAttribute("form",form); 
			}
			
			if(conditions == null) {
				model.addAttribute("error","condition_null"); 
				
				System.out.println("state " +id);
				
				 Upload_pnDAO.setstate(1,id);
				return "filepn2";
			}
			
			
			

	
			
			 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
				model.addAttribute("condition",conditions);  
				model.addAttribute("eformrv",conditions.getCondition_rv()); 
			 model.addAttribute("eformfields",eformfields);  
		}
			
			
		

				List<file> file = FileDAO.getall(upload_pns.getUpload_pn_idrefer());
				List<edata> edatas  = EdataDAO.getall_USER_GROUP(upload_pns.getUpload_pn_id(),USER_GROUP,usersed.getUser_id());
				
				System.out.println(" file "+file.size());
				
				edatas.stream().forEach(row->{
					List<file> files = file.stream().filter(sub->sub.getFile_edata() ==row.getData_id()  ).collect(Collectors.toList());
					System.out.println(row.getData_id()+" file****  "+files.size());
					
					row.setFile(files);
					
				});
				
				 model.addAttribute("edatas",edatas);  	

			  
			    	 
		

	  		 


			 System.out.println("bugss 4");

			List<dataset> datasets  = DatasetDAO.getall();
			
			System.out.println("bug 5");
			model.addAttribute("datasets",datasets);  
			model.addAttribute("planid",upload_pns.getUpload_pn_id());  
			model.addAttribute("eformid",upload_pns.getUpload_pn_monitor()); 
			model.addAttribute("dateplan",upload_pns.getUpload_pn_date()); 
		    model.addAttribute("plantype",upload_pns.getUpload_pn_type()); 
		 
		    
		    /*
			upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
			model.addAttribute("upload_pnsall",upload_pnsall);  
		    */
		    
	
    	
    	
	
		folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
		
		if(folders != null) {
			
			UploadpnTurn  turn = Upload_pnDAO.findUploadpnTurn(upload_pns.getUpload_pn_id(),usersed.getUser_name());
			model.addAttribute("turn",turn); 
			
			
			
		/*
		

    	
		if(upload_pns.getUpload_pn_monitor() > 0 && upload_pns.getUpload_pn_monitor() < 100000000 ) {
		
			
			if(group ==  2) {
				List<report_pn> report_pns = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,1,usersed.getUser_name());
		    	report_pns2 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2,usersed.getUser_name());
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,-1,usersed.getUser_name());	
		    	model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
			
			}else if(group ==  3) {
				List<report_pn> report_pns = Report_pnDAO.getall2(id,(int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,1,usersed.getUser_name());
		    	report_pns2 = Report_pnDAO.getall2(id,(int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2,usersed.getUser_name());
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2(id,(int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,-1,usersed.getUser_name());	
		    	model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
				}
			else {
		    	List<report_pn> report_pns = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,1);
		    	report_pns2 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2);
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,-1);
		    	model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
		    	
		    	//System.out.println("report_pns" + report_pns);
		    	
			}
	    	
	    	
	    	List<report_pn> respon = Report_pnDAO.getallrespon((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date);
	    	List<report_pn> respon2 = Report_pnDAO.getallapp((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date);
	
		    model.addAttribute("respon",respon);
		    model.addAttribute("respon2",respon2);
		    
		    
		    e_report e_reports =  E_reportDAO.getby(usersed.getUser_id(),(int)upload_pns.getUpload_pn_monitor());
			    if(e_reports ==  null) {
			    	 e_report e_reportnew = new e_report();
			    	 e_reportnew.setE_report_conditionid((int)upload_pns.getUpload_pn_monitor());
			    	 e_reportnew.setE_report_idadd(usersed.getUser_id());
			    	 e_reportnew.setColumn_label("data_due");
			    	 e_reportnew.setLine_label("data_due");


			    	 E_reportDAO.save(e_reportnew);
			    	 e_report e_reports2 =  E_reportDAO.getby(usersed.getUser_id(),(int)upload_pns.getUpload_pn_monitor());
			    	 model.addAttribute("e_report",e_reports2); 
			    }else {
			    	
			    	 model.addAttribute("e_report",e_reports); 
			    }
		
			
			    
			  
			    
			    
		}else {
			
			if(group ==  2 || group ==  3) {
				List<report_pn> report_pns = Report_pnDAO.getall2(id,date,1,usersed.getUser_name());
		    	report_pns2 = Report_pnDAO.getall2(id,date,2,usersed.getUser_name());
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2(id,date,-1,usersed.getUser_name());
				model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
			}
		
			else {
				List<report_pn> report_pns = Report_pnDAO.getall2(id,date,1);
		    	 report_pns2 = Report_pnDAO.getall2(id,date,2);
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2(id,date,-1);
				model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
			}
	    	
	    	
	    	List<report_pn> respon = Report_pnDAO.getallrespon(id,date);
	    	List<report_pn> respon2 = Report_pnDAO.getallapp(id,date);
	    	List<report_pn> dateplan = Report_pnDAO.getdateplan(id,1,usersed.getUser_name());	
	    	
		    model.addAttribute("dateplan",dateplan); 
		    model.addAttribute("respon",respon);
		    model.addAttribute("respon2",respon2);

	    	
		}	
	
	    	 
    	
    	
    	
		
		System.out.println("bug6");
		
			

		
		if(folders.getFolder_idrefer() >= 100000){
			model.addAttribute("level",1);  	
		}else {
			model.addAttribute("level",2);  	
		}
		
	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
    	  
	    
	    String Pathfull;
	    drives drivess;
	    int drives_id;

	    
	    if(mainfolders != null) {
	    drives_id= mainfolders.getFolder_idrefer();	
	    drivess  = DrivesDAO.getbyId(drives_id);
	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
	    }else {
	    	
    	drives_id = folders.getFolder_idrefer();
    	drivess  = DrivesDAO.getbyId(drives_id);
    	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
    		
	    }
	    System.out.println("bug 7");
	    
	    
	    
	    System.out.println("bug 8");
	    
	    
	  showupload_pn next = Upload_pnDAO.getallnext(usersed.getUser_name(),id);
	  showupload_pn back = Upload_pnDAO.getallback(usersed.getUser_name(),id);
	  
	     model.addAttribute("next",next);
	    model.addAttribute("back",back);
	    
	      model.addAttribute("Pathfull",Pathfull);
     */
	  
	  
	  
	  
      List<cm> listcms1 = CmDAO.getall(id,"upload_pn");
      model.addAttribute("listcms1",listcms1);
	  
	  List<cm> listcms2 = CmDAO.getall(id,"upload_pn",date);
      model.addAttribute("listcms2",listcms2);
	  

      
  	if(group == 3) {
	 	
  	  List<showlog> logs = LogDAO.getallbypn2(upload_pns.getUpload_pn_id(),usersed.getUser_name());
      model.addAttribute("log",logs); 
	}else {
		  List<showlog> logs = LogDAO.getallbypn(upload_pns.getUpload_pn_id());
	      model.addAttribute("log",logs); 
	} 
      
  	
  		LocalDate currentDate = LocalDate.now();
  		List<task> Task = TaskDAO.getall(id,usersed.getUser_name(),usersed.getUser_id());
  		
  	
  		
  		Task.stream().forEach(row->{

  			 LocalDate startDate = LocalDate.parse(row.getTask_datestart());
             if (!startDate.isAfter(currentDate) && row.getTask_status() == 1) {
            	row.setTask_alert(1);
             } else {
                 System.out.println("Task start date (" + startDate + ") is after the current date (" + currentDate + ").");
             }
  		});
  		
		 model.addAttribute("task",Task);  
  		
  		
  		
  		 if(usersed.getUser_name().equals(upload_pns.getUpload_pn_app())) {
			 model.addAttribute("access",1);  
		 }
	  
  		 
  	
  		 
	 

	    
	    List<report_pn> dateplan = Report_pnDAO.getdateplan(upload_pns.getUpload_pn_id(),1,usersed.getUser_name());
	    
	    
	    model.addAttribute("dateplan",dateplan); 
	    
	    
	    
		model.addAttribute("Member_name",usersed.getUser_name());
		model.addAttribute("Member_id",usersed.getUser_id());
	    model.addAttribute("idrefer",upload_pns.getUpload_pn_idrefer());
	  
    	model.addAttribute("upload_pn",upload_pns); 
    	model.addAttribute("save",save); 
    	model.addAttribute("idoc",idoc); 
    	model.addAttribute("group",group); 
    	model.addAttribute("id",id); 
    	model.addAttribute("step",step); 
    	 model.addAttribute("date2",date2);  
    	
    	 
    	 MainServices.addNotificationAttributes(model);
	

    	 
    	 
    	return "filepn2";
    
		}else {
			
			Upload_pnDAO.delete(id);
			
			return "filepn2";
			//return "overview";
		}
    	
   

    }
    
    
    @RequestMapping(value="/saveresourcesmanager-filemanager",method = RequestMethod.POST)    
    public String saveresourcesmanager(Resource resource){ 
    	
    	System.err.print("555");
    	
    	Upload_pnDAO.saveResource(resource);
    	return "redirect:/resourcesmanager-filemanager/"+resource.getResourceType()+"/"+resource.getTaskId();
    }
    
    
    
    
    @RequestMapping(value="/addresourcesmanager-filemanager/{type}/{upload_id}",method = RequestMethod.GET)    
    public String addresourcesmanager(ModelMap model,@PathVariable String type,@PathVariable int upload_id	){ 
    	
    	

    	
    	
   	 	model.addAttribute("type",type);
   	 	model.addAttribute("upload_id",upload_id);
    	return "ajax/form_resource";
    }
    
    
    
    
    @RequestMapping(value="/resourcesmanager-filemanager/{type}/{upload_id}",method = RequestMethod.GET)    
    public String resourcesmanager(ModelMap model,@PathVariable String type,@PathVariable int upload_id	){ 
    	
    	
    	System.err.print("type "+type);
    	System.err.print("upload_id "+upload_id);
    	
    	
    	
    	List<Resource> resource = 	Upload_pnDAO.getAllResourceBytypeAndTaskId(type,upload_id);
    	model.addAttribute("resource",resource);
    	
   	 	model.addAttribute("type",type);
   	 model.addAttribute("upload_id",upload_id);
    	return "ajax/resourcesmanager";
    }

    
   
    @RequestMapping(value="/taskview-filemanager/{upload_id}/{view}",method = RequestMethod.GET)    
    public String taskview(@PathVariable int upload_id,@PathVariable int view	,RedirectAttributes redirectAttributes){ 
    	
    	  user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
    	  Taskview taskview =   Upload_pnDAO.getTaskview(upload_id,usersed.getUser_id());
    	  
    	  
    	  System.err.println(" taskview "+taskview);
    	  
	    	  if(taskview == null) {
	    		  Upload_pnDAO.saveTaskview(upload_id,usersed.getUser_id(),view);
	    	  }else {
	    		  Upload_pnDAO.setTaskview(taskview,view);
	    	  }
	    	  redirectAttributes.addFlashAttribute("TAB","3");
    		return "redirect:/projectmanager-filemanager?id="+upload_id+"&&step=0&&group=1";
    }
    
    
    @RequestMapping(value="/projectmanager-filemanager",method = RequestMethod.GET)    
    public String projectmanager(ModelMap model,@RequestParam int id
    		,@RequestParam(value = "idoc",required=false) String idoc
    		,@RequestParam(value = "save",required=false) String save
    		,@RequestParam(value = "step",required=false) String step
    		,@RequestParam(value = "group",required=false) int group
    		){ 
    	
    
    	
    	
    	System.out.println("bug 1");
    	
    	LocalDateTime localDate2 = LocalDateTime.now().minusDays(10);//For reference
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	   	 String date = localDate.format(formatter);
	   	 String date2 = localDate2.format(formatter);
         upload_pn upload_pns = Upload_pnDAO.getbyId(id);
    	
    	
        
         user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
         String USER_GROUP = "guest";
       	 PolicyType policyType = PolicyType.valueOf("PROJECT");
    	 List<Policy> mypolicy = policyRepository.findAllByTypeAndCreateByFetchMembers(policyType, usersed.getUser_id());
    	 model.addAttribute("mypolicy",mypolicy);  
  
         
    	if(upload_pns.getId_policy() > 0) {
	   	 	 Policy policy  = policyRepository.findById(upload_pns.getId_policy());
	   	 	 String name = policy.getMembers().stream().map(row-> row.getEmployeeName()).limit(5).collect(Collectors.joining(","));
	   	 	 upload_pns.setUpload_pn_respon(name);
	   	 	 
	   	 	USER_GROUP = policyService.getcheckAccessByPolicy(upload_pns,usersed,policy);
	   	 	model.addAttribute("USER_GROUP",USER_GROUP);
	   	 	
	   	 	
    	}else {
    		USER_GROUP = policyService.getcheckAccess(upload_pns,usersed);
    	 	model.addAttribute("USER_GROUP",USER_GROUP); 
    	}
    
  


			List<dataset> datasets  = DatasetDAO.getall();
			
			System.out.println("bug 5");
			model.addAttribute("datasets",datasets);  
			model.addAttribute("planid",upload_pns.getUpload_pn_id());  
			model.addAttribute("eformid",upload_pns.getUpload_pn_monitor()); 
			model.addAttribute("dateplan",upload_pns.getUpload_pn_date()); 
		    model.addAttribute("plantype",upload_pns.getUpload_pn_type()); 
		 
	
	
		folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
		
		if(folders != null) {
			

	  
      List<cm> listcms1 = CmDAO.getall(id,"upload_pn");
      model.addAttribute("listcms1",listcms1);
	  
	  List<cm> listcms2 = CmDAO.getall(id,"upload_pn",date);
      model.addAttribute("listcms2",listcms2);
	  


  	
  		taskService.initiate()
  		.loadAllTask(id).exposeAs("task").UpdateStatus()
  		.loadKeyactivity(id).exposeAs("keyactivitys2")
  		.Progress(upload_pns.getProject_start(),upload_pns.getProject_end()).exposeAs("Progress")
  		.loadTaskview(upload_pns.getUpload_pn_id(),usersed).exposeAs("taskview")
  		.loadKeyactivity(id).exposeAs("keyactivitys")
  		.countAllTask().exposeAs("all_task")
  		.countPendingTask().exposeAs("pending_task");
  		
  		
  		Map<String, Object> data = taskService.getModelData();
  		model.addAllAttributes(data);
  		
  		
  		
  		

	  
	    int problem_all = TaskDAO.getCountProblemByProjectId(upload_pns.getUpload_pn_id());
	    int problem_success = TaskDAO.getCountProblemByProjectId(upload_pns.getUpload_pn_id(),2);
	    model.addAttribute("problem_all",problem_all); 
	    model.addAttribute("problem_success",problem_success); 
	    
	 
  		
  		
  		 if(usersed.getUser_name().equals(upload_pns.getUpload_pn_app())) {
			 model.addAttribute("access",1);  
		 }
	  
  		 
  	
  	
	    
	    List<report_pn> dateplan = Report_pnDAO.getdateplan(upload_pns.getUpload_pn_id(),1,usersed.getUser_name());
	    model.addAttribute("dateplan",dateplan); 
	    
	    
	    List<Outcome> outcomes = Upload_pnDAO.getAllOutcome(upload_pns.getUpload_pn_id());
	    model.addAttribute("outcomes",outcomes); 
	   
	
	    List<Risk> risks = Upload_pnDAO.getAllRisk(upload_pns.getUpload_pn_id());
	    model.addAttribute("risks",risks); 
	    
	    List<MasterBudget> budgets = Upload_pnDAO.getAllBudget(upload_pns.getUpload_pn_id());
	    
  	    List<task> Tasks = taskService.getTasks();
	    budgets.stream().forEach(row->{
	    	
	    	Double totals =  Tasks.stream().filter(rows-> row.getId() == rows.getBudget_source1() ).mapToDouble(rows-> rows.getUse_budget()).sum();
	    	row.setActual(totals);
	    	row.setVariance(row.getBudget() -  totals);
	    	
	    });
	    
	    List<user> user_all =  UserDAO.findAll();
		model.addAttribute("user_all",user_all);
	    model.addAttribute("budgets",budgets); 
	    
	    List<Milestone> milestones = Upload_pnDAO.getAllMilestone(upload_pns.getUpload_pn_id());
	    model.addAttribute("milestones",milestones); 
	    
	    
	
    	List<Resource> resource1 = 	Upload_pnDAO.getAllResourceBytypeAndTaskId("Labor",upload_pns.getUpload_pn_id());
    	List<Resource> resource2 = 	Upload_pnDAO.getAllResourceBytypeAndTaskId("Equipment",upload_pns.getUpload_pn_id());
    	List<Resource> resource3 = 	Upload_pnDAO.getAllResourceBytypeAndTaskId("Material",upload_pns.getUpload_pn_id());
    	List<Resource> resource4 = 	Upload_pnDAO.getAllResourceBytypeAndTaskId("Facility",upload_pns.getUpload_pn_id());
	    
    	BigDecimal LaborEstimatedCost = resource1.stream()
    		    .map(row -> row.getEstimatedCost()) // Stream<BigDecimal>
    		    .filter(Objects::nonNull)          // ป้องกัน null
    		    .reduce(BigDecimal.ZERO, BigDecimal::add); // รวมค่า
    	BigDecimal EquipmentmatedCost = resource2.stream()
    		    .map(row -> row.getEstimatedCost()) // Stream<BigDecimal>
    		    .filter(Objects::nonNull)          // ป้องกัน null
    		    .reduce(BigDecimal.ZERO, BigDecimal::add); // รวมค่า
    	BigDecimal MaterialmatedCost = resource3.stream()
    		    .map(row -> row.getEstimatedCost()) // Stream<BigDecimal>
    		    .filter(Objects::nonNull)          // ป้องกัน null
    		    .reduce(BigDecimal.ZERO, BigDecimal::add); // รวมค่า
    	BigDecimal FacilityEstimatedCost = resource4.stream()
    		    .map(row -> row.getEstimatedCost()) // Stream<BigDecimal>
    		    .filter(Objects::nonNull)          // ป้องกัน null
    		    .reduce(BigDecimal.ZERO, BigDecimal::add); // รวมค่า
    	
    	model.addAttribute("LaborEstimatedCost",LaborEstimatedCost);
    	model.addAttribute("EquipmentmatedCost",EquipmentmatedCost);
    	model.addAttribute("MaterialmatedCost",MaterialmatedCost);
    	model.addAttribute("FacilityEstimatedCost",FacilityEstimatedCost);
    	
    	model.addAttribute("LaborCount",resource1.size());
    	model.addAttribute("EquipmentCount",resource2.size());
    	model.addAttribute("MaterialCount",resource3.size());
    	model.addAttribute("FacilityCount",resource4.size());
    	
    	
		model.addAttribute("Member_name",usersed.getUser_name());
		model.addAttribute("Member_id",usersed.getUser_id());
	    model.addAttribute("idrefer",upload_pns.getUpload_pn_idrefer());
    	model.addAttribute("upload_pn",upload_pns); 
    	model.addAttribute("save",save); 
    	model.addAttribute("idoc",idoc); 
    	model.addAttribute("group",group); 
    	model.addAttribute("id",id); 
    	model.addAttribute("step",step); 
    	 model.addAttribute("date2",date2);  
    	
    	 
    	 MainServices.addNotificationAttributes(model);
	
    	 
 
    	 
    	 return "projectmanager";
    
		}
   
		return "projectmanager";
    }
    
    
    @PostMapping("/addbudget-filemanager")
    public String addBudget(MasterBudget Budget,RedirectAttributes RedirectAttributes){ 
   	
   		Upload_pnDAO.saveBudget(Budget);
   		RedirectAttributes.addFlashAttribute("TAB","2");
   	return "redirect:/projectmanager-filemanager?id="+Budget.getProject_id()+"&&step=0&&group=1";
   }
   
    @PostMapping("/deletebudget-filemanager")
    public String deletebudget(MasterBudget Budget,RedirectAttributes RedirectAttributes){ 
   		Upload_pnDAO.deleteBudget(Budget);
   		RedirectAttributes.addFlashAttribute("TAB","2");
   		return "redirect:/projectmanager-filemanager?id="+Budget.getProject_id()+"&&step=0&&group=1";
   }
   
    
    @PostMapping("/addmilestone-filemanager")
    public String addMilestone(Milestone milestone,RedirectAttributes RedirectAttributes){ 
	   	
	   		Upload_pnDAO.saveMilestone(milestone);
	   		RedirectAttributes.addFlashAttribute("TAB","2");
	   	return "redirect:/projectmanager-filemanager?id="+milestone.getProject_id()+"&&step=0&&group=1";
    }
    
    
   
    @PostMapping("/deletemilestone-filemanager")
    public String deletemilestone(Milestone milestone,RedirectAttributes RedirectAttributes){ 
	   		Upload_pnDAO.deleteMilestone(milestone);
	   		RedirectAttributes.addFlashAttribute("TAB","2");
	   		return "redirect:/projectmanager-filemanager?id="+milestone.getProject_id()+"&&step=0&&group=1";
   }
    
    @GetMapping("/viewmilestone-filemanager/{id}")
    public String viewmilestone(@PathVariable int id,ModelMap model){ 
	   		
    	    Milestone milestone =  Upload_pnDAO.getMilestone(id);
    	    model.addAttribute("milestone",milestone); 
    	    
    	    List<user> user_all =  UserDAO.findAll();
    		model.addAttribute("user_all",user_all);
	   		return "task/formmilestone";
   }
    
    

   
    
    
    
    
    
    @PostMapping("/addrisk-filemanager")
    public String addrisk(Risk risk,RedirectAttributes RedirectAttributes){ 
   	
   		Upload_pnDAO.saveRisk(risk);
   		RedirectAttributes.addFlashAttribute("TAB","2");
   	return "redirect:/projectmanager-filemanager?id="+risk.getProject_id()+"&&step=0&&group=1";
   }
   
    @PostMapping("/deleterisks-filemanager")
    public String deleteRisks(Risk risk,RedirectAttributes RedirectAttributes){ 
   		Upload_pnDAO.deleteRisk(risk);
   		RedirectAttributes.addFlashAttribute("TAB","2");
   		return "redirect:/projectmanager-filemanager?id="+risk.getProject_id()+"&&step=0&&group=1";
   }
   
    
    
    
    @PostMapping("/savekeyactivity-filemanager")
     public String savekeyactivity(Keyactivity keyactivity,RedirectAttributes RedirectAttributes){ 
    	
    		Upload_pnDAO.saveKeyactivity(keyactivity);
    		RedirectAttributes.addFlashAttribute("TAB","2");
    	return "redirect:/projectmanager-filemanager?id="+keyactivity.getProject_id()+"&&step=0&&group=1";
    }
    
    @PostMapping("/deletekeyactivity-filemanager")
    public String deletekeyactivity(Keyactivity keyactivity,RedirectAttributes RedirectAttributes){ 
   		Upload_pnDAO.deleteKeyactivity(keyactivity);
   		RedirectAttributes.addFlashAttribute("TAB","2");
   		return "redirect:/projectmanager-filemanager?id="+keyactivity.getProject_id()+"&&step=0&&group=1";
   }
   
    
    
    
    @PostMapping("/appoutcome-filemanager")
     public String appoutcome(Outcome outcome,RedirectAttributes RedirectAttributes){ 
    	
    		Upload_pnDAO.saveOutcome(outcome);
    		RedirectAttributes.addFlashAttribute("TAB","2");
    	return "redirect:/projectmanager-filemanager?id="+outcome.getProject_id()+"&&step=0&&group=1";
    }
    
    @PostMapping("/deleteoutcome-filemanager")
    public String deleteoutcome(Outcome outcome,RedirectAttributes RedirectAttributes){ 
   		Upload_pnDAO.deleteOutcome(outcome);
   		RedirectAttributes.addFlashAttribute("TAB","2");
   		return "redirect:/projectmanager-filemanager?id="+outcome.getProject_id()+"&&step=0&&group=1";
   }
   
    
    
    @RequestMapping(value="/overview-filemanager",method = RequestMethod.GET)    
    public String overview(ModelMap model,@RequestParam int id
    		,@RequestParam(value = "idoc",required=false) String idoc
    		,@RequestParam(value = "save",required=false) String save
    		,@RequestParam(value = "step",required=false) String step
    		,@RequestParam(value = "group",required=false) int group
    		){ 
    	
    
   
    	System.out.println("bug 1");
    	
    	LocalDateTime localDate2 = LocalDateTime.now().minusDays(10);//For reference
    	LocalDateTime localDate3 = LocalDateTime.now().minusMonths(1);//For reference
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM");
	   	 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM");
	   	 String date = localDate.format(formatter);
	   	 String date2 = localDate2.format(formatter);
    	upload_pn upload_pns = Upload_pnDAO.getbyId(id);
     	List<report_pn> report_pns2 = null;
    	
    	Upload_pnDAO.setActive(id,date);
    	
    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
    	if(usersed == null) {model.addAttribute("error","user_null"); return "filepn2";	}
    
    	

    	
    	
        List<report> reportall = ReportDAO.getall(id);
        model.addAttribute("reportall",reportall); 
        
        
        
    
    	
    	
    	if(upload_pns == null) {
    		Report_pnDAO.deletebyreport(id);
    		model.addAttribute("error","report_null"); 
    		return "overview";
    	}
    	
    	System.out.println("bug 2");
    	
		if(upload_pns.getUpload_pn_monitor() > 0 ) {
			
			
			System.out.println("bug 3");
			condition conditions = ConditionDAO.getbyids((int)upload_pns.getUpload_pn_monitor());
			
			workflow app = null;
			
			if(conditions != null) {
				app =  WorkflowDAO.getApp(conditions.getCondition_id());
			}

		    
	
			
			if(conditions != null) {
				form form = FormDAO.getbyids(conditions.getCondition_id());
				model.addAttribute("form",form); 
			}
			
			if(conditions == null) {
				model.addAttribute("error","condition_null"); 
				
				System.out.println("state " +id);
				
				 Upload_pnDAO.setstate(1,id);
				return "overview";
			}
			
			
			

	
			
			 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
				model.addAttribute("condition",conditions);  
				model.addAttribute("eformrv",conditions.getCondition_rv()); 
			 model.addAttribute("eformfields",eformfields);  
		}
			
			
		
		
		
		
		
		List<edata> edatas = null;
			 int error = 0;
			if(group == 3) {
				 	
				
				List<file> file = FileDAO.getall(upload_pns.getUpload_pn_idrefer());
				edatas = EdataDAO.getall(upload_pns.getUpload_pn_id());
				
				System.out.println(" file "+file.size());
				
				edatas.stream().forEach(row->{
					List<file> files = file.stream().filter(sub->sub.getFile_edata() ==row.getData_id()  ).collect(Collectors.toList());
					System.out.println(row.getData_id()+" file****  "+files.size());
					
					row.setFile(files);
					
				});
				
				 model.addAttribute("edatas",edatas);  	

			      if(edatas.size() < 1) {
			    	  error =1;
			    	 }
			    	 
			}else {
				
				List<file> file = FileDAO.getall(upload_pns.getUpload_pn_idrefer());
				edatas = EdataDAO.getall(upload_pns.getUpload_pn_id());
				System.out.println(" ****file "+file.size());
				edatas.stream().forEach(row->{
					List<file> files = file.stream().filter(sub->sub.getFile_edata() ==row.getData_id()).collect(Collectors.toList());
					
					System.out.println(row.getData_id()+" file****  "+files.size());
					row.setFile(files);
					
				});
				
				
				model.addAttribute("edatas",edatas);  
				 
				  if(edatas.size() < 1) {
					  error =1;
			    	 }
			}
 

	  		 


			 System.out.println("bugss 4");

			List<dataset> datasets  = DatasetDAO.getall();
			
			System.out.println("bug 5");
			model.addAttribute("datasets",datasets);  
			model.addAttribute("planid",upload_pns.getUpload_pn_id());  
			model.addAttribute("eformid",upload_pns.getUpload_pn_monitor()); 
			model.addAttribute("dateplan",upload_pns.getUpload_pn_date()); 
		    model.addAttribute("plantype",upload_pns.getUpload_pn_type()); 
		 
		    
		    
			upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
			model.addAttribute("upload_pnsall",upload_pnsall);  
		    
		    
	
    	
    	
	
		folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
	
		
		
		if(folders != null) {
    	
		if(upload_pns.getUpload_pn_monitor() > 0 && upload_pns.getUpload_pn_monitor() < 100000000 ) {
		
			
			if(group ==  2) {
				List<report_pn> report_pns = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,1,usersed.getUser_name());
		    	report_pns2 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2,usersed.getUser_name());
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,-1,usersed.getUser_name());	
		    	model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
			
			}else if(group ==  3) {
				List<report_pn> report_pns = Report_pnDAO.getall2(id,(int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,1,usersed.getUser_name());
		    	report_pns2 = Report_pnDAO.getall2(id,(int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2,usersed.getUser_name());
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2(id,(int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,-1,usersed.getUser_name());	
		    	model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
				}
			else {
		    	List<report_pn> report_pns = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,1);
		    	report_pns2 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2);
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,-1);
		    	model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
		    	
		    	//System.out.println("report_pns" + report_pns);
		    	
			}
	    	
	    	
	    	List<report_pn> respon = Report_pnDAO.getallrespon((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date);
	    	List<report_pn> respon2 = Report_pnDAO.getallapp((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date);
	
		    model.addAttribute("respon",respon);
		    model.addAttribute("respon2",respon2);
		    
		    
		    e_report e_reports =  E_reportDAO.getby(usersed.getUser_id(),(int)upload_pns.getUpload_pn_monitor());
			    if(e_reports ==  null) {
			    	 e_report e_reportnew = new e_report();
			    	 e_reportnew.setE_report_conditionid((int)upload_pns.getUpload_pn_monitor());
			    	 e_reportnew.setE_report_idadd(usersed.getUser_id());
			    	 e_reportnew.setColumn_label("data_due");
			    	 e_reportnew.setLine_label("data_due");


			    	 E_reportDAO.save(e_reportnew);
			    	 e_report e_reports2 =  E_reportDAO.getby(usersed.getUser_id(),(int)upload_pns.getUpload_pn_monitor());
			    	 model.addAttribute("e_report",e_reports2); 
			    }else {
			    	
			    	 model.addAttribute("e_report",e_reports); 
			    }
		
			
			    
			  
			    
			    
		}else {
			
			if(group ==  2 || group ==  3) {
				List<report_pn> report_pns = Report_pnDAO.getall2(id,date,1,usersed.getUser_name());
		    	report_pns2 = Report_pnDAO.getall2(id,date,2,usersed.getUser_name());
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2(id,date,-1,usersed.getUser_name());
				model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
			}
		
			else {
				List<report_pn> report_pns = Report_pnDAO.getall2(id,date,1);
		    	 report_pns2 = Report_pnDAO.getall2(id,date,2);
		    	List<report_pn> report_pns3 = Report_pnDAO.getall2(id,date,-1);
				model.addAttribute("report_pn",report_pns);
		    	model.addAttribute("report_pn2",report_pns2);
		    	model.addAttribute("report_pn3",report_pns3);
			}
	    	
	    	
	    	List<report_pn> respon = Report_pnDAO.getallrespon(id,date);
	    	List<report_pn> respon2 = Report_pnDAO.getallapp(id,date);
	    	List<report_pn> dateplan = Report_pnDAO.getdateplan(id,1,usersed.getUser_name());	
	    	
		    model.addAttribute("dateplan",dateplan); 
		    model.addAttribute("respon",respon);
		    model.addAttribute("respon2",respon2);

	    	
		}	
	
	    	 
    	
    	
    	
		
		System.out.println("bug6");
		
			

		
		if(folders.getFolder_idrefer() >= 100000){
			model.addAttribute("level",1);  	
		}else {
			model.addAttribute("level",2);  	
		}
		
	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
    	  
	    
	    String Pathfull;
	    drives drivess;
	    int drives_id;

	    
	    if(mainfolders != null) {
	    drives_id= mainfolders.getFolder_idrefer();	
	    drivess  = DrivesDAO.getbyId(drives_id);
	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
	    }else {
	    	
    	drives_id = folders.getFolder_idrefer();
    	drivess  = DrivesDAO.getbyId(drives_id);
    	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
    		
	    }
	    System.out.println("bug 7");
	    
	    
	    
	    System.out.println("bug 8");
	    
	    
	  showupload_pn next = Upload_pnDAO.getallnext(usersed.getUser_name(),id);
	  showupload_pn back = Upload_pnDAO.getallback(usersed.getUser_name(),id);

	  
	  
	  
	  
      List<cm> listcms1 = CmDAO.getall(id,"upload_pn");
      model.addAttribute("listcms1",listcms1);
	  
	  List<cm> listcms2 = CmDAO.getall(id,"upload_pn",date);
      model.addAttribute("listcms2",listcms2);
	  

      
  	if(group == 3) {
	 	
  	  List<showlog> logs = LogDAO.getallbypn2(upload_pns.getUpload_pn_id(),usersed.getUser_name());
      model.addAttribute("log",logs); 
	}else {
		  List<showlog> logs = LogDAO.getallbypn(upload_pns.getUpload_pn_id());
	      model.addAttribute("log",logs); 
	} 
      
  	
  		LocalDate currentDate = LocalDate.now();
  		List<task> Task = TaskDAO.getall(id);
  		List<task> original_task = TaskDAO.getalloriginal(id);
  		
  		
  		
  	
  		
  		Task.stream().forEach(row->{

  			 LocalDate startDate = LocalDate.parse(row.getTask_datestart());
  			 
  			 
  			actionplan Total = ActionplanDAO.Total(row.getTask_id());
  			if(Total != null) {
  	  			row.setTask_expenses(Total.getActionplan_expenses());
  	  			row.setTask_real_payment(Total.getActionplan_payment());
  			}else {
  	  			row.setTask_expenses("0");
  	  			row.setTask_real_payment("0");
  			}
  			

  			
  			
             if (startDate.isAfter(currentDate) && row.getTask_status() == 1) {
            	 row.setTask_status(0);
              } 
  		});
  		
		 model.addAttribute("task",Task);  
		 model.addAttribute("original_task",original_task);  
  		
  		
  		 if(usersed.getUser_name().equals(upload_pns.getUpload_pn_app())) {
			 model.addAttribute("access",1);  
		 }
	  
  		 
  	
  		 
	    model.addAttribute("next",next);
	    model.addAttribute("back",back);

	    
	    List<report_pn> dateplan = Report_pnDAO.getdateplan(upload_pns.getUpload_pn_id(),1,usersed.getUser_name());
	    
	    
	    model.addAttribute("dateplan",dateplan); 
	    
	    
	    
		model.addAttribute("Member_name",usersed.getUser_name());
		model.addAttribute("Member_id",usersed.getUser_id());
	    model.addAttribute("idrefer",upload_pns.getUpload_pn_idrefer());
	    model.addAttribute("Pathfull",Pathfull);
    	model.addAttribute("upload_pn",upload_pns); 
    	model.addAttribute("save",save); 
    	model.addAttribute("idoc",idoc); 
    	model.addAttribute("group",group); 
    	model.addAttribute("id",id); 
    	model.addAttribute("step",step); 
    	 model.addAttribute("date2",date2);  
    	
    	 
    	 MainServices.addNotificationAttributes(model);
	

    	 
    	 
    	return "overview";
    	 
		}else {
			
		}
 
    	return "overview";
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(value="/report-filemanager",method = RequestMethod.GET)    
    public String listreport(ModelMap model, @RequestParam int id){ 
    
    	
    	 MainServices.addNotificationAttributes(model);

		
		report report = ReportDAO.getbyid(id);
		model.addAttribute("report",report); 
		
		
		upload_pn upload_pns = Upload_pnDAO.getbyId(report.getReport_id_refer());
		model.addAttribute("upload_pn",upload_pns); 
		
 
	if(upload_pns.getUpload_pn_monitor() > 0  && upload_pns.getUpload_pn_monitor() < 100000000) {
			
			
			System.out.println("bug 3");
			condition conditions = ConditionDAO.getbyids((int)upload_pns.getUpload_pn_monitor());
			
			if(conditions == null) {
				model.addAttribute("error","condition_null"); 
				return "filepn2";
			}
			
			 List<eformfield> eformfields = EformfieldDAO.getall(upload_pns.getUpload_pn_monitor(), 0);
			 
			 
			 condition subconditions = ConditionDAO.getbysub(conditions.getCondition_id());
			 model.addAttribute("subconditions",subconditions);  
			 if(subconditions != null ) {
				 
				 
			
				 
				 List<eformfield> eformfields2 = EformfieldDAO.getall(subconditions.getCondition_id(), 0);
				 model.addAttribute("eformfields2",eformfields2);  
				 
				 
			 }
			 
			
				 	
				 List<edata> edatas = EdataDAO.getallreport(report.getReport_data());
				 model.addAttribute("edatas",edatas);  	
		
			 
			 
			 
			 
			 
			 model.addAttribute("eformfields",eformfields);  
			 System.out.println("bugss 4");

			List<dataset> datasets  = DatasetDAO.getall();
			
			System.out.println("bug 5");
			model.addAttribute("datasets",datasets);  
			
			model.addAttribute("tag","ที่ปรึกษา");  
			model.addAttribute("start",report.getReport_start());  
			model.addAttribute("end",report.getReport_end());  
			
			
			if(report.getReport_month() != null) {
				model.addAttribute("month",Dateservice.MonthConvertTh(report.getReport_month()));  
			}
			
			
		
			model.addAttribute("condition",conditions);  
			model.addAttribute("planid",upload_pns.getUpload_pn_id());  
			model.addAttribute("eformid",upload_pns.getUpload_pn_monitor()); 
			model.addAttribute("eformrv",conditions.getCondition_rv()); 
			model.addAttribute("dateplan",upload_pns.getUpload_pn_date()); 
		    model.addAttribute("plantype",upload_pns.getUpload_pn_type()); 
		 
		    
		    
		    upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
			model.addAttribute("upload_pnsall",upload_pnsall);  
		    
			folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
			List<file> files = FileDAO.getallreport(report.getReport_id());
			model.addAttribute("folder",folders.getFolder_id());
			model.addAttribute("files",files);
		}
		
		
		
    	return "ajax/report";
    }
    
    
    
    
    
    @RequestMapping(value="/delreport-filemanager",method = RequestMethod.GET)    
    public String delreport(ModelMap model, @RequestParam int id){ 
    
    	report report = ReportDAO.getbyid(id);
    	int id_refer = report.getReport_id_refer();
    	ReportDAO.delete(id);
  
    	return "redirect:/duepn-filemanager?id="+id_refer+"&&step=0&&group=3";
    }
    

    
    @RequestMapping(value="/addreport-filemanager",method = RequestMethod.GET)    
    public String addlistreport(ModelMap model, @RequestParam int id){ 
    
    	
    	 MainServices.addNotificationAttributes(model);

		
		

    	LocalDateTime localDate3 = LocalDateTime.now().minusMonths(1);//For reference
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM");
		 String date3 = localDate3.format(formatter2);
	   	 String fors = localDate.format(formatter2);
	
		upload_pn upload_pns = Upload_pnDAO.getbyId(id);
		model.addAttribute("upload_pn",upload_pns); 
		report report = ReportDAO.getbyidrefer(upload_pns.getUpload_pn_id());
		model.addAttribute("report",upload_pns); 
		model.addAttribute("id",id); 
		

	if(upload_pns.getUpload_pn_monitor() > 0  && upload_pns.getUpload_pn_monitor() < 100000000) {
			
			
			System.out.println("bug 3");
			condition conditions = ConditionDAO.getbyids((int)upload_pns.getUpload_pn_monitor());
			
			   model.addAttribute("monitor1",conditions.getCondition_id());  
			
			
			if(conditions == null) {
				model.addAttribute("error","condition_null"); 
				return "filepn2";
			}
			
			 List<eformfield> eformfields = EformfieldDAO.getall(upload_pns.getUpload_pn_monitor(), 0);
			 
			 
			 condition subconditions = ConditionDAO.getbysub(conditions.getCondition_id());
			 model.addAttribute("subconditions",subconditions);  
			
			 upload_pn upload_pns2 = null;
			 List<edata> edatas =null;  
			  
			 if(subconditions != null ) {

				 List<eformfield> eformfields2 = EformfieldDAO.getall(subconditions.getCondition_id(), 0);
				 model.addAttribute("eformfields2",eformfields2);  
 
			 }else {
			 
			 
			    condition database1 = ConditionDAO.getbyport(conditions.getCondition_id(),1);
			    condition  database2 = null;
			  
			    System.out.println("step : 1");
			    
			    int monitor = 0;
				    
				    if(database1 == null) {
				    	database2 = ConditionDAO.getbyport(conditions.getCondition_id(),2);
	
				    }
				    
				    if(database1 != null || database2 != null) {
				    	if(database1 != null) {
				    		 monitor =database1.getCalculation_port2();
				    	}
				    	if(database2 != null) {
				    		monitor= database2.getCalculation_port1();
				    	}
				    }
			    
			    
			    if(monitor > 0) {
			    	
			    	  System.out.println("step : 2");
			    	  System.out.println("monitor : "+monitor);
			    	  
			    	  
			    	 upload_pns2 = Upload_pnDAO.getbymonitor(monitor);
			    	 condition conditions2 = ConditionDAO.getbyid(monitor);
			    	   model.addAttribute("monitor2",monitor);  
			    	 


					 
			    }
			    

			 }
			    
			 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			 report_mapping mappings  = ConditionDAO.getMap(conditions.getCondition_id(),usersed.getUser_id());
			    if(upload_pns2 != null && mappings != null) {
			    	
			        	 List<eformfield> eformfields3 = EformfieldDAO.getall(mappings.getMapping_id_map(), 0);

			        	 int i = 0;
			        	 int size = eformfields.size();
			        	 
			        	// report_mapping mappings  = ConditionDAO.getMap(conditions.getCondition_id(),usersed.getUser_id());
			        	 if(mappings.getMapping_key1() != null) { 
		
			        		if(  Integer.valueOf(mappings.getMapping_key1()) >  size ) {
			        			 eformfield eformfieldsed = new eformfield();
			        			 eformfieldsed.setField_label(eformfields3.get(0).getField_label());
			        			 eformfields.add(eformfieldsed);
			        		}
			        	 }
			        	 if(mappings.getMapping_key2() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key2()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(1).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key3() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key3()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(2).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key4() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key4()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(3).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key5() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key5()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(4).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key6() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key6()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(5).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key7() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key7()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(6).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key8() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key8()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(7).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key9() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key9()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(8).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key10() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key10()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(9).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 
			      
			        	 
			        	 
			        	 edatas = EdataDAO.getallreportmap(upload_pns.getUpload_pn_id(),upload_pns2.getUpload_pn_id(),date3+"-01",date3+"-31",eformfields,eformfields3,mappings);	
						 model.addAttribute("edatas",edatas);  
						 
						 System.out.println("mappings .....");


			    }else {
			    	 edatas = EdataDAO.getallreport(upload_pns.getUpload_pn_id(),date3+"-26",fors+"-25");	
			    }
				 model.addAttribute("edatas",edatas);  	
		
			 
			 
			 
			 
			 
			 model.addAttribute("eformfields",eformfields);  
			 System.out.println("bugss 4");

			List<dataset> datasets  = DatasetDAO.getall();
			
			System.out.println("bug 5");
			model.addAttribute("datasets",datasets);  
			
			model.addAttribute("tag","ที่ปรึกษา");  
			model.addAttribute("start",date3+"-01");  
			model.addAttribute("end",date3+"-XX");  
			
			
			if(report != null) {
				if(report.getReport_month() != null) {
					model.addAttribute("month",Dateservice.MonthConvertTh(report.getReport_month()));  
				}
			}
			model.addAttribute("Member_name",usersed.getUser_name());
			model.addAttribute("condition",conditions);  
			model.addAttribute("planid",upload_pns.getUpload_pn_id());  
			model.addAttribute("eformid",upload_pns.getUpload_pn_monitor()); 
			model.addAttribute("eformrv",conditions.getCondition_rv()); 
			model.addAttribute("dateplan",upload_pns.getUpload_pn_date()); 
		    model.addAttribute("plantype",upload_pns.getUpload_pn_type()); 
		    model.addAttribute("id",id); 
		    
		    
		    upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
			model.addAttribute("upload_pnsall",upload_pnsall);  
		    
			
			folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
			
			
			if(report != null) {
			List<file> files = FileDAO.getallreport(report.getReport_id());
			model.addAttribute("folder",folders.getFolder_id());
			model.addAttribute("files",files);
			}
			
			
		}
		
		
		
    	return "ajax/addreport";
    }
    
    
    
    
    
    @RequestMapping(value="/addreportsearch-filemanager",method = RequestMethod.POST)    
    public String addreportsearch(
    		ModelMap model
    		, @RequestParam(required = false) String start
    		, @RequestParam(required = false) String end
    		, @RequestParam int id
    		){ 
    
    	
    	 MainServices.addNotificationAttributes(model);

		
		

    	LocalDateTime localDate3 = LocalDateTime.now().minusMonths(1);//For reference
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM");
		 String date3 = localDate3.format(formatter2);
	   	 String fors = localDate.format(formatter2);
	
		upload_pn upload_pns = Upload_pnDAO.getbyId(id);
		model.addAttribute("upload_pn",upload_pns); 
		report report = ReportDAO.getbyidrefer(upload_pns.getUpload_pn_id());
		model.addAttribute("report",upload_pns); 
		model.addAttribute("id",id); 
		

	if(upload_pns.getUpload_pn_monitor() > 0  && upload_pns.getUpload_pn_monitor() < 100000000) {
			
			
			System.out.println("bug 3");
			condition conditions = ConditionDAO.getbyids((int)upload_pns.getUpload_pn_monitor());
			
			   model.addAttribute("monitor1",conditions.getCondition_id());  
			
			
	
			
			 List<eformfield> eformfields = EformfieldDAO.getall(upload_pns.getUpload_pn_monitor(), 0);
			 
			 
			 condition subconditions = ConditionDAO.getbysub(conditions.getCondition_id());
			 model.addAttribute("subconditions",subconditions);  
			
			 upload_pn upload_pns2 = null;
			 List<edata> edatas =null;  
			  
			 if(subconditions != null ) {

				 List<eformfield> eformfields2 = EformfieldDAO.getall(subconditions.getCondition_id(), 0);
				 model.addAttribute("eformfields2",eformfields2);  
 
			 }else {
			 
			 
			    condition database1 = ConditionDAO.getbyport(conditions.getCondition_id(),1);
			    condition  database2 = null;
			  
			    System.out.println("step : 1");
			    
			    int monitor = 0;
				    
				    if(database1 == null) {
				    	database2 = ConditionDAO.getbyport(conditions.getCondition_id(),2);
	
				    }
				    
				    if(database1 != null || database2 != null) {
				    	if(database1 != null) {
				    		 monitor =database1.getCalculation_port2();
				    	}
				    	if(database2 != null) {
				    		monitor= database2.getCalculation_port1();
				    	}
				    }
			    
			    
			    if(monitor > 0) {
			    	
			    	  System.out.println("step : 2");
			    	  System.out.println("monitor : "+monitor);
			    	  
			    	  
			    	 upload_pns2 = Upload_pnDAO.getbymonitor(monitor);
			    	 condition conditions2 = ConditionDAO.getbyid(monitor);
			    	   model.addAttribute("monitor2",monitor);  
			    	 


					 
			    }
			    

			 }
			    
			 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());     
			 report_mapping mappings  = ConditionDAO.getMap(conditions.getCondition_id(),usersed.getUser_id());
			    if(upload_pns2 != null && mappings != null) {
			    	
			        	 List<eformfield> eformfields3 = EformfieldDAO.getall(mappings.getMapping_id_map(), 0);

			        	 int i = 0;
			        	 int size = eformfields.size();
			        	 
			        	// report_mapping mappings  = ConditionDAO.getMap(conditions.getCondition_id(),usersed.getUser_id());
			        	 if(mappings.getMapping_key1() != null) { 
		
			        		if(  Integer.valueOf(mappings.getMapping_key1()) >  size ) {
			        			 eformfield eformfieldsed = new eformfield();
			        			 eformfieldsed.setField_label(eformfields3.get(0).getField_label());
			        			 eformfields.add(eformfieldsed);
			        		}
			        	 }
			        	 if(mappings.getMapping_key2() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key2()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(1).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key3() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key3()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(2).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key4() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key4()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(3).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key5() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key5()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(4).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key6() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key6()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(5).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key7() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key7()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(6).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key8() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key8()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(7).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key9() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key9()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(8).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 if(mappings.getMapping_key10() != null) { 
			        			
				        		if(  Integer.valueOf(mappings.getMapping_key10()) >  size ) {
				        			 eformfield eformfieldsed = new eformfield();
				        			 eformfieldsed.setField_label(eformfields3.get(9).getField_label());
				        			 eformfields.add(eformfieldsed);
				        		}
				        	 }
				        	 
			        	 
			      
			        	 
			        	 
			        	 edatas = EdataDAO.getallreportmap(upload_pns.getUpload_pn_id(),upload_pns2.getUpload_pn_id(),start,end,eformfields,eformfields3,mappings);	
						 model.addAttribute("edatas",edatas);  
						 
						 System.out.println("mappings .....");


			    }else {
			    	 edatas = EdataDAO.getallreport(upload_pns.getUpload_pn_id(),start,end);	
			    }
				 model.addAttribute("edatas",edatas);  	
		
			 
			 
			 
			 
			 
			 model.addAttribute("eformfields",eformfields);  
			 System.out.println("bugss 4");

			List<dataset> datasets  = DatasetDAO.getall();
			
			System.out.println("bug 5");
			model.addAttribute("datasets",datasets);  
			
			model.addAttribute("tag","ที่ปรึกษา");  
			model.addAttribute("start",start);  
			model.addAttribute("end",end);  
			
			
			if(report != null) {
				if(report.getReport_month() != null) {
					model.addAttribute("month",Dateservice.MonthConvertTh(report.getReport_month()));  
				}
			}
			model.addAttribute("Member_name",usersed.getUser_name());
			model.addAttribute("condition",conditions);  
			model.addAttribute("planid",upload_pns.getUpload_pn_id());  
			model.addAttribute("eformid",upload_pns.getUpload_pn_monitor()); 
			model.addAttribute("eformrv",conditions.getCondition_rv()); 
			model.addAttribute("dateplan",upload_pns.getUpload_pn_date()); 
		    model.addAttribute("plantype",upload_pns.getUpload_pn_type()); 
		    model.addAttribute("id",id); 
		    
		    
		    upload_pn upload_pnsall = Upload_pnDAO.getbyIdandAll(upload_pns.getUpload_pn_id(),upload_pns.getUpload_pn_type());
			model.addAttribute("upload_pnsall",upload_pnsall);  
		    
			
			folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
			
			
			if(report != null) {
			List<file> files = FileDAO.getallreport(report.getReport_id());
			model.addAttribute("folder",folders.getFolder_id());
			model.addAttribute("files",files);
			}
			
			
		}
		
		
		
    	return "ajax/addreport";
    }
    
    
   
    
    
    @RequestMapping(value="/mapreport-filemanager/{id}/{id2}",method = RequestMethod.GET)    
    public String mapreport(ModelMap model,@PathVariable int id,@PathVariable int id2){ 
    	
    	  
    	 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
    	
    	 if(!ConditionDAO.checkmap(id,usersed.getUser_id())) {
    		 
    		 report_mapping report_mappings = new report_mapping();
    		 report_mappings.setMapping_id_add(usersed.getUser_id());
    		 report_mappings.setMapping_id_refer(id);
    		 report_mappings.setMapping_id_map(id2);
    		 
    		 ConditionDAO.saveMap(report_mappings);
    		 
    		 
    		 
    	 }
    	
    	  report_mapping mappings  = ConditionDAO.getMap(id,usersed.getUser_id());
    	  
    	  System.out.println(mappings.getMapping_id_refer() +"--------------- "+mappings.getMapping_id_map());
    	  
    	List<eformfield> eformfields = EformfieldDAO.getall(mappings.getMapping_id_refer(), 0);
		model.addAttribute("eformfields",eformfields);  
			 
		List<eformfield> eformfields2 = EformfieldDAO.getalls(mappings.getMapping_id_map(), 0);
		model.addAttribute("eformfields2",eformfields2);  
    	  
		condition conditions1 = ConditionDAO.getbyids(mappings.getMapping_id_refer());
		condition conditions2 = ConditionDAO.getbyids(mappings.getMapping_id_map());
		
		model.addAttribute("conditions1",conditions1);  
		model.addAttribute("conditions2",conditions2);  
		
		model.addAttribute("mapping_id",mappings.getMapping_id());  
		model.addAttribute("key1",mappings.getMapping_key1());  
		model.addAttribute("key2",mappings.getMapping_key2()); 
		model.addAttribute("key3",mappings.getMapping_key3()); 
		model.addAttribute("key4",mappings.getMapping_key4()); 
		model.addAttribute("key5",mappings.getMapping_key5()); 
		model.addAttribute("key6",mappings.getMapping_key6()); 
		model.addAttribute("key7",mappings.getMapping_key7()); 
		model.addAttribute("key8",mappings.getMapping_key8()); 
		model.addAttribute("key9",mappings.getMapping_key9()); 
		model.addAttribute("key10",mappings.getMapping_key10()); 
		
		
		model.addAttribute("id",id);  
		model.addAttribute("id2",id2);  
    		return "ajax/mapreport";
    }
    
    
    
    @RequestMapping(value="/savereports-filemanager",method = RequestMethod.POST)    
    public String reportsaves(ModelMap model,report_mapping report_mappings){ 
    	
    	ConditionDAO.updateMap(report_mappings);
    	  return "redirect://mapreport-filemanager/"+report_mappings.getMapping_id_refer()+"/"+report_mappings.getMapping_id_map();
    }
    
    
    @RequestMapping(value="/mapkey-filemanager",method = RequestMethod.POST)    
    public String mapkey(ModelMap model,report_mapping report_mappings,int id,int id2){ 
    	
    	
    	List<eformfield> eformfields = EformfieldDAO.getall(id, 0);
    	int step = eformfields.size();
    	
     	if(report_mappings.getMapping_key1() != null) {
    		if( report_mappings.getMapping_key1().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key1(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key2() != null) {
    		if( report_mappings.getMapping_key2().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key2(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key3() != null) {
    		if( report_mappings.getMapping_key3().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key3(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key4() != null) {
    		if( report_mappings.getMapping_key4().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key4(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key5() != null) {
    		if( report_mappings.getMapping_key5().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key5(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key6() != null) {
    		if( report_mappings.getMapping_key6().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key6(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key7() != null) {
    		if( report_mappings.getMapping_key7().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key7(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key8() != null) {
    		if( report_mappings.getMapping_key8().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key8(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key9() != null) {
    		if( report_mappings.getMapping_key9().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key9(String.valueOf(step));
	    			}		
    			}
    	}
    	if(report_mappings.getMapping_key10() != null) {
    		if( report_mappings.getMapping_key10().equals("0")  ) {
    				
    				step++;
    				if(step <= 20) { 
    					report_mappings.setMapping_key10(String.valueOf(step));
	    			}		
    			}
    	}
     	
     	
     	
    	
    	
    	   ConditionDAO.updateMap(report_mappings);
    	 
    	   
    	   
      	 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
     	
     
     	
     	  report_mapping mappings  = ConditionDAO.getMap(id,usersed.getUser_id());
     	  
     	  System.out.println(mappings.getMapping_id_refer() +"--------------- "+mappings.getMapping_id_map());
     	  

 		List<eformfield> eformfields2 = EformfieldDAO.getalls(mappings.getMapping_id_map(), 0);
 		model.addAttribute("eformfields2",eformfields2);  
     	  
 		condition conditions1 = ConditionDAO.getbyids(mappings.getMapping_id_refer());
 		condition conditions2 = ConditionDAO.getbyids(mappings.getMapping_id_map());
 		
 		model.addAttribute("conditions1",conditions1);  
 		model.addAttribute("conditions2",conditions2);  
 		
 		model.addAttribute("mapping_id",mappings.getMapping_id());  
 		model.addAttribute("key1",mappings.getMapping_key1());  
 		model.addAttribute("key2",mappings.getMapping_key2()); 
 		model.addAttribute("key3",mappings.getMapping_key3()); 
 		model.addAttribute("key4",mappings.getMapping_key4()); 
 		model.addAttribute("key5",mappings.getMapping_key5()); 
 		model.addAttribute("key6",mappings.getMapping_key6()); 
 		model.addAttribute("key7",mappings.getMapping_key7()); 
 		model.addAttribute("key8",mappings.getMapping_key8()); 
 		model.addAttribute("key9",mappings.getMapping_key9()); 
 		model.addAttribute("key10",mappings.getMapping_key10()); 
 		
 		
 		model.addAttribute("id",id);  
 		model.addAttribute("id2",id2);  
     		return "ajax/mapreport";
    	   
    	   
    }
    
    
    
    
    @RequestMapping(value="/savereport-filemanager",method = RequestMethod.POST)    
    public String reportsave(ModelMap model,report reports){ 
    	
    	  ReportDAO.update(reports);
    	  return "redirect:/report-filemanager?id="+reports.getReport_id();
    }
    
    
    
    
    
    

    @RequestMapping(value="/addreportsearchsave-filemanager",method = RequestMethod.POST)    
    public String addreportsearchsave(
    		ModelMap model
    		,@RequestParam(required = false) String start
    		,@RequestParam(required = false) String end
    		,@RequestParam int id
    		,@RequestParam (required = false) String importid
    		,@RequestParam String report_name
    		){ 
    
    	
            user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
      		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 			LocalDateTime today = LocalDateTime.now();
 			String date = today.format(formatter2);
      	
 			
 			report reportsed = ReportDAO.getbyidrefer(id);
 			
	    	report reports = new report();
	    	reports.setReport_date(date);
	    	reports.setReport_start(start);
	    	reports.setReport_end(end);
	    	reports.setReport_id_refer(id);
	    	reports.setReport_name(report_name);
	    	reports.setReport_by(usersed.getUser_name());
	    	reports.setReport_data(importid);
	    	
	    	if(reportsed != null) { reports.setReport_report(reportsed.getReport_report()); }
	    	
	    	ReportDAO.save(reports);
		
	    	model.addAttribute("save","success"); 
    	    return "ajax/addreport";
    }
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(value="/expired-filemanager",method = RequestMethod.GET)    
    public String listupload_pnexpired(ModelMap model){ 
    	List<file> files = FileDAO.getallexpired();
    	
    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);

    	model.addAttribute("file",files); 
    	return "filelistexpired";
    }
    
    
    @RequestMapping(value="/lohpn-filemanager",method = RequestMethod.GET)    
    public String lohpn(ModelMap model , @RequestParam int id , @RequestParam int idrefer,@RequestParam(value = "comment_id",required = false) String comment_id,@RequestParam(value = "idoc",required=false) String idoc){ 
    	upload_pn upload_pns = Upload_pnDAO.getbyId(idrefer);
    	report_pn report_pns = Report_pnDAO.getbyIds(id);
    	
    	
    	List<showlog> logs = LogDAO.getallbypn(idrefer,report_pns.getReport_pn_date());
    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		folder folders = FolderDAO.getbyId(upload_pns.getUpload_pn_idrefer());
		
		if(folders.getFolder_idrefer() >= 100000){
			model.addAttribute("level",1);  	
		}else {
			model.addAttribute("level",2);  	
		}
		
	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
    	  
	    
	    String Pathfull;
	    drives drivess;
	    int drives_id;

	    
	    if(mainfolders != null) {
	    drives_id= mainfolders.getFolder_idrefer();	
	    drivess  = DrivesDAO.getbyId(drives_id);
	    Pathfull = drivess.getDrives_name()+"/"+mainfolders.getFolder_name()+"/"+folders.getFolder_name();
	    }else {
	    	
    	drives_id = folders.getFolder_idrefer();
    	drivess  = DrivesDAO.getbyId(drives_id);
    	Pathfull =drivess.getDrives_name()+"/"+folders.getFolder_name();	
	    	
	    }
		
	    if(comment_id != null) {
	    	
	    	comment  comment = CommentDAO.get(comment_id);
	    	model.addAttribute("comment",comment);
	    }
	    
	    List<comment> comments  = CommentDAO.getall("record",id);
	    
	    model.addAttribute("id",id);
	    model.addAttribute("idrefer",idrefer);
	    
	    
	    model.addAttribute("comments",comments);
	    model.addAttribute("Pathfull",Pathfull);
		model.addAttribute("Member_name",usersed.getUser_name());
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		model.addAttribute("upload_pn",upload_pns);  
		model.addAttribute("report_pn",report_pns); 
    	model.addAttribute("log",logs); 
    	model.addAttribute("idoc",idoc); 
    	return "logpn";
    	
    } 
    
    
	 @RequestMapping(value="/deletefile-filemanager/{id}",method = RequestMethod.GET)    
	    public String deletesfile( HttpSession session,@PathVariable int id,@RequestParam int level) throws Exception
	 { 
		 
		 
		  driverDTO driverDTOs = (driverDTO) session.getAttribute("driverDTOs");
		  folderDTO folderDTOs = (folderDTO) session.getAttribute("folderDTOs");
		 
		     file files = FileDAO.getbyId(id);
		     ServletContext context = session.getServletContext(); 
		     String path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
		     File fileToDelete = new File(path + File.separator + files.getFile_name());
		     
		     
		     File f = new File(path + File.separator + files.getFile_name());
		     if(f.exists() && !f.isDirectory()) { 
		    	 boolean success = fileToDelete.delete();
			     if(success) {
			    	 FileDAO.delete(id);
			     }
		     }else {
		    	
		    	 
		    	
			     FileDAO.delete(id);
		     }
		     
		    
		     
		     
		     
		     
		     // update space
		     String mainfolder = files.getFile_path();
		     folder folders =   FolderDAO.getbytype(mainfolder);
		     folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
		     int drives_id;
			 drives drivess;
			    
			    if(mainfolders != null) {
			    drives_id= mainfolders.getFolder_idrefer();	
			    drivess  = DrivesDAO.getbyId(drives_id);

			    }else {
			    	
		    	drives_id = folders.getFolder_idrefer();
		    	drivess  = DrivesDAO.getbyId(drives_id);
			    }
		     
		     file sum = FileDAO.getsum(mainfolder);
			    long imageFileSize = sum.getFile_size();
			    String Size2 = Fileservice.printFileSize(imageFileSize);
			    FolderDAO.setuse(folders.getFolder_id(),imageFileSize,Size2);
			    if(mainfolders != null) {
			    	folder sum2 = FolderDAO.getsum(mainfolders.getFolder_id());
		    	    long imageFileSize2 = sum2.getFolder_use();
		    	    String Size3 = Fileservice.printFileSize(imageFileSize2);
		    	    FolderDAO.setuse(mainfolders.getFolder_id(),imageFileSize2,Size3);
		    	  
			    }        	    
		    	folder sum3 = FolderDAO.getsum(drives_id);
			    long imageFileSize3 = sum3.getFolder_use();
			    double percent;
			    if(imageFileSize3 > 0) {
			       percent =  imageFileSize3 * 100 / drivess.getDrives_size();
			    }else {
			    	 percent =0;	
			    }
			    String Size4 = Fileservice.printFileSize(imageFileSize3);
			    DrivesDAO.setuse(drives_id,imageFileSize3,Size4,percent);
			    // update space
			    
			    
			    report_pn report_pns = Report_pnDAO.getbyId(Integer.parseInt(files.getFile_idpn()));
			    int total = report_pns.getReport_pn_total()  ==  0  ?  0  : 1 ;
				Report_pnDAO.unsettotal(total,Integer.parseInt(files.getFile_idpn()));
		     
		
		 
		    
		     
   		if(level == 2 ) {
			   return "redirect:/filemanager/"+driverDTOs.getDrives_drive()+"/"+ folderDTOs.getFolder_name()+"?id=" + folderDTOs.getFolder_id();
		     }else {
			   return "redirect:/filemanager/"+driverDTOs.getDrives_drive()+"/"+folderDTOs.getFolder_name()+"/"+ folderDTOs.getSubfoldername()+"?id=" + folderDTOs.getFolder_subid();
		   }
   		
	 }
   		
	
	 
	 
	 @Transactional(rollbackFor  = { SQLException.class })
	 @RequestMapping(value="/appdocument-filemanager",method = RequestMethod.POST)    
	    public String appdocument( 
	    		@RequestParam int id,
	    		@RequestParam String note,
	    		@RequestParam(value = "data_value1",required=false) String data_value1,
	    		@RequestParam(value = "data_value2",required=false) String data_value2,
	    		@RequestParam(value = "data_value3",required=false) String data_value3,
	    		@RequestParam(value = "data_value4",required=false) String data_value4,
	    		@RequestParam(value = "data_value5",required=false) String data_value5,
	    		@RequestParam(value = "data_value6",required=false) String data_value6,
	    		@RequestParam(value = "data_value7",required=false) String data_value7,
	    		@RequestParam(value = "data_value8",required=false) String data_value8,
	    		@RequestParam(value = "data_value9",required=false) String data_value9,
	    		@RequestParam(value = "data_value10",required=false) String data_value10,
	    		@RequestParam(value = "data_value11",required=false) String data_value11,
	    		@RequestParam(value = "data_value12",required=false) String data_value12,
	    		@RequestParam(value = "data_value13",required=false) String data_value13,
	    		@RequestParam(value = "data_value14",required=false) String data_value14,
	    		@RequestParam(value = "data_value15",required=false) String data_value15,
	    		@RequestParam(value = "data_value16",required=false) String data_value16,
	    		@RequestParam(value = "data_value17",required=false) String data_value17,
	    		@RequestParam(value = "data_value18",required=false) String data_value18,
	    		@RequestParam(value = "data_value19",required=false) String data_value19,
	    		@RequestParam(value = "data_value20",required=false) String data_value20,
	    		@RequestParam(value = "data_title1",required=false) String data_title1,
	    		@RequestParam(value = "data_title2",required=false) String data_title2,
	    		@RequestParam(value = "data_title3",required=false) String data_title3,
	    		@RequestParam(value = "data_title4",required=false) String data_title4,
	    		@RequestParam(value = "data_title5",required=false) String data_title5,
	    		@RequestParam(value = "data_title6",required=false) String data_title6,
	    		@RequestParam(value = "data_title7",required=false) String data_title7,
	    		@RequestParam(value = "data_title8",required=false) String data_title8,
	    		@RequestParam(value = "data_title9",required=false) String data_title9,
	    		@RequestParam(value = "data_title10",required=false) String data_title10,
	    		@RequestParam(value = "data_title11",required=false) String data_title11,
	    		@RequestParam(value = "data_title12",required=false) String data_title12,
	    		@RequestParam(value = "data_title13",required=false) String data_title13,
	    		@RequestParam(value = "data_title14",required=false) String data_title14,
	    		@RequestParam(value = "data_title15",required=false) String data_title15,
	    		@RequestParam(value = "data_title16",required=false) String data_title16,
	    		@RequestParam(value = "data_title17",required=false) String data_title17,
	    		@RequestParam(value = "data_title18",required=false) String data_title18,
	    		@RequestParam(value = "data_title19",required=false) String data_title19,
	    		@RequestParam(value = "data_title20",required=false) String data_title20,
	    		@RequestParam(value = "data_status",required=false) String data_status,
	    		@RequestParam(value = "app",required=false) String apps,
	    		ModelMap model) throws Exception
	 {
		 
		 
		//*****Set Element process *****
		 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
		 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 LocalDateTime localDate = LocalDateTime.now();//For reference	 
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("HH:mm:ss");
		 String time = localDate2.format(formatter5);
		 String date = localDate.format(formatter);
	   	 String datetime = localDate2.format(formatter3);
		 
	     
		 
	   	 	//*****Select process *****
		   user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		   EdataDAO.setapp(id,users.getUser_name(),2,data_status,note,date);
	       edata edata = EdataDAO.getbyid(id);
	       UploadPlanManagers.Approve(edata);
		
		   
	       List<file> file  = FileDAO.getallEdata(edata.getData_id()); 
	       edata.setFile(file);
	       model.addAttribute("edatasave",edata);
		   
		

		   
		   
		   
		   
		   List<eformfield> eformfields = EformfieldDAO.getall(edata.getData_eformid(),edata.getData_eformrv()); 
		   model.addAttribute("eformfields",eformfields);
		   
     
	 
			return "/ajax/app";	
	 }
	 
	 
	 
	 
	 @RequestMapping(value="/applldocument-filemanager/{id}/{step}/{group}",method = RequestMethod.GET)    
	    public String applldocument( @PathVariable int id,@PathVariable int step,@PathVariable int group) throws Exception
	 {
		 
		 	LocalDateTime localDate = LocalDateTime.now();//For reference
		 	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 	String date = localDate.format(formatter);
		    upload_pn upload_pns = Upload_pnDAO.getbyId(id);
		    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    List<report_pn> report_pns2;
		  
		    
		   if(upload_pns.getUpload_pn_monitor() > 0 && upload_pns.getUpload_pn_monitor() < 100000000 ) {

				if(group ==  2 || group ==  3) {
				
			    	report_pns2 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2,usersed.getUser_name());
				}else {

			    	report_pns2 = Report_pnDAO.getall2((int)upload_pns.getUpload_pn_monitor(),upload_pns.getUpload_pn_type(),date,2);
				}

			}else {
				
				if(group ==  2 || group ==  3) {
			    	report_pns2 = Report_pnDAO.getall2(id,date,2,usersed.getUser_name());

				}else {
			    	report_pns2 = Report_pnDAO.getall2(id,date,2);
	
				}
		    	
			}	
		   
		   
		   for(report_pn report_pns : report_pns2) {
			   
						   DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
							LocalDateTime todaylast = LocalDateTime.now().minusDays(1);
							LocalDateTime today = LocalDateTime.now();
							String datelast = todaylast.format(formatter2);
							String dated = today.format(formatter2);
							LogDAO.setapp(report_pns.getReport_pn_idrefer());
						if(report_pns != null) {	
					
							if(!dated.equals(report_pns.getReport_pn_date())) {
								
								if(!datelast.equals(report_pns.getReport_pn_date())) {
									
									
									SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
								    Date date1 = myFormat.parse(datelast);
								    Date date2 = myFormat.parse(report_pns.getReport_pn_date());
									int diff = Dateservice.getDiffDays(date1,date2);
									
							 	   Report_pnDAO.setappstatus(report_pns.getReport_pn_id(),diff,date);	
							 	    }else {
							 	    	 Report_pnDAO.setStatus(report_pns.getReport_pn_id(),3,date);
							 	    }
								
								
							 }else {
								 Report_pnDAO.setStatus(report_pns.getReport_pn_id(), 3,date);
							 }
                              
						}	

		}	
		   return "redirect:/duepn-filemanager?id="+id+"&&step="+step+"&&group="+group;
	
	
		  
	 }
	 
	 
	 
	 
	 

	 @RequestMapping(value="/addcm-filemanager",method = RequestMethod.POST)    
	    public String addcm( 
	    		@RequestParam int cm_idrefer,
	    		@RequestParam String cm_title,
	    		@RequestParam String type,
	    		ModelMap model
	    		)
	 {
		 
		      user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		 	 LocalDateTime localDate = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		      
		      
		      cm cms = new cm(); 
		      cms.setCm_date(date);
		      cms.setCm_idadd(users.getUser_id());
		      cms.setCm_idrefer(cm_idrefer);
		      cms.setCm_title(cm_title);
		      cms.setType(type);
		      CmDAO.save(cms); 
		      
		      List<cm> listcms = CmDAO.getall(cm_idrefer,type,date);
		      model.addAttribute("listcms",listcms);
		    
		      int total = CmDAO.getnum(cm_idrefer); 
		      model.addAttribute("total",total);
			return "/ajax/cm";	
	 }
	 
	 
	 
	 

	 @RequestMapping(value="/delcm-filemanager/{id}/{type}",method = RequestMethod.GET)    
	    public String detcm( 
	    		@PathVariable int id,
	    		@PathVariable String type,
	    		ModelMap model)
	 {
		    cm  cms =  CmDAO.getbyid(id);
		    CmDAO.delete(id,type);
		    model.addAttribute("id",id);
		    
		    int total = CmDAO.getnum(cms.getCm_idrefer()); 
		    model.addAttribute("total",total);
		    
			return "/ajax/delcm";	
	 }
	 	 
	 
	 @RequestMapping(value="/rejectdocument-filemanager",method = RequestMethod.POST)    
	    public String reject( 
	    		@RequestParam int id,
	    		@RequestParam String note,
	    		@RequestParam(value = "app",required=false) String apps,
	    		ModelMap model) throws Exception
	 {
		   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		    EdataDAO.setapp(id,users.getUser_name(),-1,"");
			edata edatas = EdataDAO.getbyid(id);
	
		  
		   
		   
	
		   upload_pn upload_pns = Upload_pnDAO.getbyId(edatas.getData_planid());
		   
		   List<eformfield> eformfields = EformfieldDAO.getall(upload_pns.getUpload_pn_monitor(), 0); 
		   model.addAttribute("eformfields",eformfields);
		   
		   
			 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 
			 LocalDateTime localDate = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 
			 String date = localDate.format(formatter);
		   	 String datetime = localDate2.format(formatter3);
		   
		  

				notification  notifications = new notification();
				notifications.setNotification_iduser(edatas.getData_nameadd());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("เอกสารบันทึกคุณภาพ --"+upload_pns.getUpload_pn_name()+" ถูกปฏิเสธเนื่องจาก"+note);
				notifications.setNotification_note("ผู้ดูเเล : "+edatas.getData_nameadd());
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_refer(Integer.toString(upload_pns.getUpload_pn_id()));
				notifications.setNotification_type("Job queue error");
				NotificationDAO.save(notifications);   
		  
		
			if(edatas != null) {
	
				
				   model.addAttribute("edata_id",edatas.getData_id());
				   model.addAttribute("edatasave",edatas);
			}
			model.addAttribute("log_id",id);
			
	
		  
		    
		    cm cms = new cm(); 
		    cms.setCm_date(date);
		    cms.setCm_idadd(users.getUser_id());
		    cms.setCm_idrefer(upload_pns.getUpload_pn_id());
		    cms.setCm_title(note);
		    CmDAO.save(cms); 
		    

		    System.out.println("Run .... .... ... ");
		    
		    
			if(apps  != null) {
				if(apps.equals("apps")) {
				  return "redirect:/app-filemanager/"+edatas.getData_eformid();
				}
			}
		    
			return "/ajax/app";	
	 }
	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value="/undosend-filemanager/{id}/{processes_id}/{app_workflow_id}",method = RequestMethod.GET)
		public String undosend(
				@PathVariable int id,
				@PathVariable int processes_id,
				@PathVariable int app_workflow_id
				){
			
			Platform platform = new Platform();
			
			platform.setApp_item_id(id);
			platform.setApp_processes_id(processes_id);
			platform.setApp_workflow_id(app_workflow_id);
			
			
			
			UserDAO.getbyusername2(Userservice.getLoggedName());
			policies policies = Userservice.getPolicies(processes_id);
			System.out.println(" policies "+policies.toString());
			LoadApp   LoadApp =   new LoadApp();
			LoadApp LoadApp2 =  platformService.initiate(LoadApp).workflow(platform).recall();
			  

			  return  "redirect:/editdocumentapp-filemanager/"+id+"/"+processes_id;
			
		}   
	 
	 
	 
	 @RequestMapping(value="/flowdoc-filemanager",method = RequestMethod.POST)    
	    public String flowdoc( 
	    		@RequestParam int to
	    		,@RequestParam int send
	    		,@RequestParam int app_processes_id
	    		,@RequestParam int step
	    		,@RequestParam int flow_id
	    		,@RequestParam int data_id
	    		,@RequestParam String  workflow_clear
	      		,@RequestParam(value="appproved",required =false,defaultValue="0") int appproved
	      		,@RequestParam(value="date_end",required =false,defaultValue="-") String date_end
	      		,@RequestParam(value="remark",required =false,defaultValue="-") String remark
	     		,@RequestParam(value="remarks_rejected",required =false,defaultValue="-") String remarks_rejected
	       		,@RequestParam(value="closing_status",required =false,defaultValue="-") String closing_status
	    		,ModelMap model
	    		,HttpServletResponse response 
	    		) throws Exception
				
	 {
		 
		 
			Platform platform = new Platform();
			platform.setApp_item_id(data_id);
			platform.setApp_workflow_id(flow_id);
			platform.setApp_processes_id(app_processes_id);
			platform.setWorkflow_to(to);
			platform.setWorkflow_send(send);
			platform.setWorkflow_step(step);
			platform.setWorkflow_appproved(appproved);
			platform.setWorkflow_closing_status(closing_status);
			platform.setWorkflow_remark(remark);
			platform.setWorkflow_clear(workflow_clear);
			platform.setWorkflow_remarks_rejected(remarks_rejected);
			platform.setWorkflow_date_end(date_end);
			
			
			
		LoadApp   LoadApp =   new LoadApp();	
		LoadApp LoadApp2 =  platformService.initiate(LoadApp).workflow(platform).send();
		model.addAttribute("Http_status_code",LoadApp2.getHttp_status_code());
		model.addAttribute("Callback",LoadApp2.getCallback());
		
		if(LoadApp2.getHttp_status_code() == 200) {
			response.setStatus(HttpServletResponse.SC_CREATED); 
		}
	    return "app/callback";
		
	
	 }
	 

	
	 
	 

	 
	 
	 
	 

	 
	 
	 
	 @RequestMapping(value="/unfileinfos-filemanager/{id}",method = RequestMethod.GET)    
	    public String unfileinfos( @PathVariable int id,HttpSession session) throws Exception
	 {
		 

		 	log logs =  LogDAO.getbyId(id);
		   
		   	String path;
		   	File fileToDelete;
		   	boolean success;
		   	file files = FileDAO.getbynamefile(logs.getLog_file());
		   	
		   	
		    ServletContext context = session.getServletContext(); 
		     path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
		     fileToDelete = new File(path + File.separator + logs.getLog_file());
		     success = fileToDelete.delete();
		     if(success) {
		    	 FileDAO.deleteforfilename(logs.getLog_file());
		    	
		     }
		   
		 	logs.setLog_folder(null);
			logs.setLog_id(logs.getLog_id());
			logs.setLog_file(null);
			logs.setLog_dive(null);
			int idlogs = LogDAO.update(logs);
		     
		     
		   upload_pn upload_pns = Upload_pnDAO.getbyId(Integer.parseInt(logs.getLog_refer()));
		   report_pn report_pns = Report_pnDAO.getbyId2(upload_pns.getUpload_pn_id(),logs.getLog_due());
		   return "redirect:/lohpn-filemanager?idrefer="+logs.getLog_refer()+"&&id="+report_pns.getReport_pn_id();
	 }
 
	 
	 
	 @RequestMapping(value="/undocument-filemanager/{id}",method = RequestMethod.GET)    
	    public String undocument( @PathVariable int id,HttpSession session,ModelMap model) throws Exception
	 {
		 

		 	List<file> file = FileDAO.getallEdata(id);
		   	String path;
		   	File fileToDelete;
		   	boolean success;

			   	for(file files : file) {
							  if(files != null) { 	
						    ServletContext context = session.getServletContext(); 
							     path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
							     fileToDelete = new File(path + File.separator + files.getFile_name());
							     success = fileToDelete.delete();
							     if(success) {
							    	 FileDAO.delete(files.getFile_id());
							    	
							     }
						   	}
				}

		     edata edatas = EdataDAO.getbyid(id);
		     report_pn  report_pns= Report_pnDAO.getbyIds(edatas.getReport_id());
	
		     
		     if( report_pns != null) {
		    	 	Report_pnDAO.setStatus(report_pns.getReport_pn_id(),1,0,0);
		    	 	Report_pnDAO.delete(report_pns.getReport_pn_id());   
		    	 	flowService.del(edatas.getData_id());
		     } 

		     EdataDAO.delete(id);
		     
		     model.addAttribute("edata_id",id);
		   return "/ajax/file";
	 }
    
	 
	 
	
	  

	 @RequestMapping(value="/search-filemanager",method = RequestMethod.POST)    
	    public String search( @RequestParam String data,ModelMap model) throws Exception
	 {
		   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		   group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
		 List<file> files  =  FileDAO.search(data);
		 model.addAttribute("file",files);
		 model.addAttribute("groups",group_memberss.getGroup_id());
		 return "search";
	 }
	 
	 
		@RequestMapping(value="/setauthorities-filemanager/{id}", method=RequestMethod.GET)
		public String setauthorities(ModelMap model,@PathVariable int id,@RequestParam(value = "save",required=false) String save) {
			
			user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
			int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);  
			folder folders = FolderDAO.getbyId(id);
			
			List<groups> groupss = GroupsDAO.getall(0);
			List<group_authorities> authoritiess = Group_authoritiesDAO.getallfoldertype(folders.getFolder_type()); 
          
			
			System.out.println(authoritiess);
			 model.addAttribute("save", save);
			 model.addAttribute("folder", folders);
			 model.addAttribute("groups", groupss);
			 model.addAttribute("authoritiess",authoritiess);
			return "setauthorities";
		}

		@RequestMapping(value="/setauthorities-filemanager", method=RequestMethod.POST)
		public String saveauthorities(ModelMap model,@RequestParam int folderid
				,@RequestParam("id") List<String> id
				,@RequestParam(value = "ADD",required=false) List<String> auth_add
				,@RequestParam(value ="EDIT",required=false) List<String> auth_edit
				,@RequestParam(value ="CHECK",required=false) List<String> auth_check
				,@RequestParam(value ="APP",required=false) List<String> auth_app
				,@RequestParam(value ="READ",required=false) List<String> auth_read
				,@RequestParam(value ="UPLOAD",required=false) List<String> auth_upload
				,@RequestParam(value ="DOWNLOAD",required=false) List<String> auth_download
				,@RequestParam(value ="SET",required=false) List<String> auth_set
				,@RequestParam(value ="DELETE",required=false) List<String> auth_del
				,@RequestParam(value ="DELETEITEM",required=false) List<String> auth_delitem
				
				
				) {
			
			folder folders = FolderDAO.getbyId(folderid);
			String Group_id = String.join(",",id);

				 Group_authoritiesDAO.deleteauthoritiesbyfolder(Group_id,folders.getFolder_type()); 
				if(auth_add != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_add);   }
				if(auth_edit != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_edit); }
				if(auth_check != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_check); }
				if(auth_app != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_app); }
				if(auth_read != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_read); }
				if(auth_upload != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_upload); }
				if(auth_download != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_download); }
				if(auth_set != null) {Group_authoritiesDAO.setauthoritiesbyfolder(auth_set); }
				if(auth_del != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_del); }
				if(auth_delitem != null) { Group_authoritiesDAO.setauthoritiesbyfolder(auth_delitem); }
		
				
			
			 return "redirect:/setauthorities-filemanager/"+folderid+"?save=success";
		}
	 
		
		
		
		@PreAuthorize("hasAnyAuthority('APP-DAR')")
	    @RequestMapping(value="/mainfileinfo-filemanager",method = RequestMethod.GET)    
	    public String mainfileinfo(@RequestParam int id 
	    		, ModelMap model ,log logs,
	    		@RequestParam String Folders){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 file files = FileDAO.getbyId(id);
			
		 	logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(id));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
			folder folders = FolderDAO.getbytype(files.getFile_path());   	
			folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	 
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
			
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	    	
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(id);
	    	   
	    	   
	    	   List<file> fileold = FileDAO.getallexpiredbyfileno(files.getFile_no());
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   
	    	   System.out.println("shortcut" +files.getFile_id());
	    	   
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("fileold",fileold);
	    	   
	    	   
	    	   model.addAttribute("log",logsed);
	   		   
	   		   
	    	   
	   		   
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Drives", drivess.getDrives_drive());
	    	   return "fileinfo"; 		
	    }    
	 
		
	    @RequestMapping(value="/subfileinfo-filemanager",method = RequestMethod.GET)    
	    public String subfileinfo(@RequestParam int id , ModelMap model ,log logs,@RequestParam String Folders){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	 group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
	    	 groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	 
	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 file files = FileDAO.getbyId(id);
		  
		   	 
		   	 System.out.println(2);
		   	
		   	
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(id));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
			
			 System.out.println(3);
	    	
			 String namefolder;
			 if(files.getFile_shotcut() == null){
				 namefolder =files.getFile_path();
			 }else {
				 namefolder =files.getFile_shotcut();
		
			 }
			 
			
			folder folders = FolderDAO.getbytype(namefolder);
    	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    		 System.out.println(4);
    		 
    		 
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	 
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	   		 
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(id);
	    	   List<file> fileold = FileDAO.getallexpiredbyfileno(files.getFile_no());
	    		 System.out.println(5);   
	    	   
	    	   
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("log",logsed);
	   		   
	   		   
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		   model.addAttribute("myjd",groupss.getGroup_name());
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Pathfull",Pathfull);
	    	   model.addAttribute("Pathfulls",Pathfulls);
	    	   model.addAttribute("Drives",drivess.getDrives_drive());
	    	   model.addAttribute("fileold",fileold);
	    	   
	    	   
	    	   
	    	   return "fileinfo"; 	 		
	    }    
	 
		
	    @RequestMapping(value="/subfileinfos-filemanager",method = RequestMethod.GET)    
	    public String subfileinfos(@RequestParam String file , ModelMap model ,log logs){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	 group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
	    	 groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	 
	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 file files = FileDAO.getbynamefile(file);
			
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(files.getFile_id()));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
			
			folder folders = FolderDAO.getbytype(files.getFile_path());
    	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
			
	    	
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	   		 
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(files.getFile_id());
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("log",logsed);
	   		   
	   		   
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		   model.addAttribute("myjd",groupss.getGroup_name());
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Pathfull",Pathfull);
	    	   model.addAttribute("Pathfulls",Pathfulls);
	    	   return "fileinfo4"; 		
	    }    
	    
	    
	    @RequestMapping(value="/renderbyapp-filemanager",method = RequestMethod.GET)    
	    public String renderbyapp(ModelMap model,log logs,@RequestParam int file_edata){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	 group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
	    	 groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	 
	    	


	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 
		   	 
		   	 file files = FileDAO.getbyId(file_edata);
			
		   
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(files.getFile_id()));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
			
			folder folders = FolderDAO.getbytype(files.getFile_path());
    	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
			
	    	
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	   		 
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(files.getFile_id());
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("log",logsed);
	   		   
	    	   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		   model.addAttribute("myjd",groupss.getGroup_name());
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Pathfull",Pathfull);
	    	   model.addAttribute("Pathfulls",Pathfulls);
	    	   return "app/fileshow"; 		
	    }  
	    
	    
	    
	    @RequestMapping(value="/renderload-filemanager",method = RequestMethod.GET)    
	    public String renderload(ModelMap model,log logs,@RequestParam int file_edata){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	 group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
	    	 groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	 
	    	


	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 file files = FileDAO.getbyId(file_edata);
			
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(files.getFile_id()));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
			
			folder folders = FolderDAO.getbytype(files.getFile_path());
    	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
			
	    	
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	   		 
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(files.getFile_id());
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("log",logsed);
	   		   
	    	   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		   model.addAttribute("myjd",groupss.getGroup_name());
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Pathfull",Pathfull);
	    	   model.addAttribute("Pathfulls",Pathfulls);
	    	   return "fileshow2"; 		
	    }  
	    
	    
	    
	    @RequestMapping(value="/render-filemanager",method = RequestMethod.GET)    
	    public String render(ModelMap model,log logs,@RequestParam int file_edata){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	 group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
	    	 groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	 
	    	


	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 file files = FileDAO.getbyId(file_edata);
			
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(files.getFile_id()));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
			
			folder folders = FolderDAO.getbytype(files.getFile_path());
    	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
			
	    	
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	   		 
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(files.getFile_id());
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("log",logsed);
	   		   
	    	   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		   model.addAttribute("myjd",groupss.getGroup_name());
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Pathfull",Pathfull);
	    	   model.addAttribute("Pathfulls",Pathfulls);
	    	   return "fileshow"; 		
	    }  
	    
	    
	    
	    
	    
	    @RequestMapping(value="/render2-filemanager",method = RequestMethod.GET)    
	    public String render2(@RequestParam int file , ModelMap model,log logs){ 
	    	
	    	
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    	 group_members group_memberss =Group_membersDAO.getbyId(Userservice.getLoggedName());
	    	 groups groupss  = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	 
	   

	    	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		   	 file files = FileDAO.getbyId(file);
			
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(files.getFile_id()));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
			
			folder folders = FolderDAO.getbytype(files.getFile_path());
    	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
      	  
    	    
    	    String Pathfull;
    	    String Pathfulls;
    	    drives drivess;
    	    int drives_id;

    	    
    	    if(mainfolders != null) {
    	    drives_id= mainfolders.getFolder_idrefer();	
    	    drivess  = DrivesDAO.getbyId(drives_id);
    	    Pathfull = drivess.getDrives_drive()+"/"+mainfolders.getFolder_type()+"/"+folders.getFolder_type();
    	    Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
    	    }else {
    	    	
        	drives_id = folders.getFolder_idrefer();
        	drivess  = DrivesDAO.getbyId(drives_id);
        	Pathfull =drivess.getDrives_drive()+"/"+folders.getFolder_type();	
        	Pathfulls =drivess.getDrives_drive()+"/"+folders.getFolder_type();		
    	    }
			
			
	    	
	    	  
	    	   List<showdar> dars = DarDAO.getallbyfile(files.getFile_no());
	    	   
	    		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
	    		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
	   		 
	    	   List<file> shortcut = FileDAO.getallshortcut(files.getFile_id());
	    	   List<showlog> logsed = LogDAO.getallbyidrefer(files.getFile_id());
	    	   model.addAttribute("shortcut",shortcut);
	    	   model.addAttribute("log",logsed);
	   		   
	    	   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("notification",notifications);  
	   		   model.addAttribute("alert",alert);
	   		   model.addAttribute("myjd",groupss.getGroup_name());
	    	   model.addAttribute("dar",dars); 
	    	   model.addAttribute("file",files);
	    	   model.addAttribute("Pathfull",Pathfull);
	    	   model.addAttribute("Pathfulls",Pathfulls);
	    	   return "fileshow"; 		
	    }  
	    
	    
	    
	    
	    @RequestMapping(value="/Eform-filemanager/{logid}/{Condition_id}",method = RequestMethod.GET)
		public String Eformshow(
				ModelMap model
				,@PathVariable int logid
				,@PathVariable int Condition_id) {
	    
	    	condition conditions = ConditionDAO.getbyid(Condition_id);
			dataeform  dataeforms = DataeformDAO.getbycondition(conditions.getCondition_id(),logid);
			model.addAttribute("eformdata",dataeforms);  
			model.addAttribute("conditions",conditions);  
			return  "eformshow";
		}
		
	    
		
		@PreAuthorize("hasAnyAuthority('APP-DAR')")
	    @RequestMapping(value="/renew-filemanager",method = RequestMethod.GET)    
	    public String renew(@RequestParam int id,log logs,@RequestParam String Folders){ 
	    	
		
	    	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
	    	 LocalDateTime localDate = LocalDateTime.now();
	    	 LocalDateTime renewDate = LocalDateTime.now().plusMonths(3);
	    	 
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	 String date = localDate.format(formatter);
		   	 String time = localDate.format(formatter2);
		     String renew = renewDate.format(formatter);
		     file files = FileDAO.getbyId(id);
		   	 FileDAO.renew(id,renew);
			
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("RENEW");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note("ต่ออายุเอกสารคุณภาพ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(id));
			logs.setLog_folder(files.getFile_path_full());
			LogDAO.save(logs);
	    	
	    
	    	return "redirect:/expired-filemanager";	
	    }    
	 
		  @RequestMapping(value="/reportuser-filemanager",method = RequestMethod.GET)    
		    public String reportuser(ModelMap model){ 
		    	
				user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
				int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
		    	
				 List<user> userss = UserDAO.getall(0,0);
				model.addAttribute("user", userss);
					
					
			  	return "reportuserlist";	
		    }    
		    
		

	    @RequestMapping(value="/pnall-filemanager",method = RequestMethod.GET)    
	    public String pnall(ModelMap model){ 
	    	
			user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
			int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);
	    	
			List<showupload_pn> upload_pns  = Upload_pnDAO.getallfull();
			model.addAttribute("upload_pn",upload_pns);
			
			
		  	return "pnall";	
	    }    
	    
	    
	 
	    @RequestMapping(value="/showreportuser-filemanager",method = RequestMethod.GET)    
	    public String showreportuser(ModelMap model,@RequestParam int id){ 
	    	
			user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
			int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);
			

			user member = UserDAO.getbyId2(id);  
			model.addAttribute("member",member);  
			model.addAttribute("id",id);  
		
			
			if(member != null) {
				
				
			List<showupload_pn> upload_pn = Upload_pnDAO.getallby(member.getUser_name());
			model.addAttribute("upload_pn",upload_pn);  
			
			
			
			 LocalDateTime today = LocalDateTime.now();//For reference
			 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy");
			 String datestart = today.format(formatter2)+"-01-01";
			 String dateend = today.format(formatter2)+"-12-31";
			List<report_pn> report_success = Report_pnDAO.getallreportbydateandsuccess(datestart,dateend,member.getUser_name());
			List<report_pn> report_error = Report_pnDAO.getallreportbydateanderror(datestart,dateend,member.getUser_name());
			model.addAttribute("report_success",report_success);  
			model.addAttribute("report_error",report_error);  
			
			
			
			List<showlog> logs =  LogDAO.getallbyuserid(member.getUser_id());
			model.addAttribute("logs",logs); 
			
			}
			
			
			
		  	return "reportuser";	
	    }    
	    
	    
	    
	    
		@PreAuthorize("hasAnyAuthority('APP-DAR')")
	    @RequestMapping(value="/savetimereadpdf-filemanager/{Drives}/{Folders}/{id}/{time}",method = RequestMethod.GET)    
	    public String savetimereadpdf(@PathVariable String Drives,@PathVariable String Folders,@PathVariable int id,@PathVariable int time){ 
	    	
	    	 
			log logs = new log();
			user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
	    	LocalDateTime localDate = LocalDateTime.now();//For reference
		   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		   	String date = localDate.format(formatter);
		   	String times = localDate.format(formatter2);
		   	file files = FileDAO.getbyId(id);
			
			
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("READ");
			logs.setLog_date(date);
			logs.setLog_time(times);
			logs.setLog_note("เปิดดูไฟล์ " + files.getFile_name_original());
			logs.setLog_refer(Integer.toString(id));
			logs.setLog_folder(files.getFile_path_full());
			logs.setLog_readtime( Dateservice.Convert(time) );
			LogDAO.save(logs);
	    	
	    
	    	return "redirect:/showpdf-filemanager/"+Drives+"/"+Folders+"/?id="+id;	
	    }    
	 
	    
		
		
	
		
		   @RequestMapping(value="/bill-filemanager",method = RequestMethod.GET)    
		    public String bill(ModelMap model,@RequestParam int id){ 
			   
			   edata edata = EdataDAO.getbyid(id);
			   model.addAttribute("edata",edata); 
			   edata edata2  = null;
			   
			   if(edata != null) {
				    edata2 = EdataDAO.getbyid(edata.getData_join());
			   }
		
			   
			  form forms = FormDAO.getbyids(edata.getData_eformid());
		      model.addAttribute("forms",forms); 
			  
				if(edata2 != null) {
					
						String tag1 = eformservices.getdata(edata,edata2,forms.getForm_tag1());
						 String tag2 = eformservices.getdata(edata,edata2,forms.getForm_tag2());
						 String tag3 = eformservices.getdata(edata,edata2,forms.getForm_tag3());
						 String tag4 = eformservices.getdata(edata,edata2,forms.getForm_tag4());
						 String tag5 = eformservices.getdata(edata,edata2,forms.getForm_tag5());
						 String tag6 = eformservices.getdata(edata,edata2,forms.getForm_tag6());
						 String tag7 = eformservices.getdata(edata,edata2,forms.getForm_tag7());
						 String tag8 = eformservices.getdata(edata,edata2,forms.getForm_tag8());
						 String tag9 = eformservices.getdata(edata,edata2,forms.getForm_tag9());
						 String tag10 = eformservices.getdata(edata,edata2,forms.getForm_tag10());
						 String tag11 = eformservices.getdata(edata,edata2,forms.getForm_tag11());
						 String tag12 = eformservices.getdata(edata,edata2,forms.getForm_tag12());
						 String tag13 = eformservices.getdata(edata,edata2,forms.getForm_tag13());
						 String tag14 = eformservices.getdata(edata,edata2,forms.getForm_tag14());
						 String tag15 = eformservices.getdata(edata,edata2,forms.getForm_tag15());
						 String tag16 = eformservices.getdata(edata,edata2,forms.getForm_tag16());
						 String tag17 = eformservices.getdata(edata,edata2,forms.getForm_tag17());
						 String tag18 = eformservices.getdata(edata,edata2,forms.getForm_tag18());
						 String tag19 = eformservices.getdata(edata,edata2,forms.getForm_tag19());
						 String tag20 = eformservices.getdata(edata,edata2,forms.getForm_tag20());
						 String tag21 = eformservices.getdata(edata,edata2,forms.getForm_tag21());
						 String tag22 = eformservices.getdata(edata,edata2,forms.getForm_tag22());
						 String tag23 = eformservices.getdata(edata,edata2,forms.getForm_tag23());
						 String tag24 = eformservices.getdata(edata,edata2,forms.getForm_tag24());
						 String tag25 = eformservices.getdata(edata,edata2,forms.getForm_tag25());
						 String tag26 = eformservices.getdata(edata,edata2,forms.getForm_tag26());
						 String tag27 = eformservices.getdata(edata,edata2,forms.getForm_tag27());
						 String tag28 = eformservices.getdata(edata,edata2,forms.getForm_tag28());;
						 String tag29 = eformservices.getdata(edata,edata2,forms.getForm_tag29());
						 String tag30 = eformservices.getdata(edata,edata2,forms.getForm_tag30());
						 String tag31 = eformservices.getdata(edata,edata2,forms.getForm_tag31());
						 String tag32 = eformservices.getdata(edata,edata2,forms.getForm_tag32());
						 String tag33 = eformservices.getdata(edata,edata2,forms.getForm_tag33());
						 String tag34 = eformservices.getdata(edata,edata2,forms.getForm_tag34());
						 String tag35 = eformservices.getdata(edata,edata2,forms.getForm_tag35());
						 String tag36 = eformservices.getdata(edata,edata2,forms.getForm_tag36());
						 String tag37 = eformservices.getdata(edata,edata2,forms.getForm_tag37());
						 String tag38 = eformservices.getdata(edata,edata2,forms.getForm_tag38());
						 String tag39 = eformservices.getdata(edata,edata2,forms.getForm_tag39());
						 String tag40 = eformservices.getdata(edata,edata2,forms.getForm_tag40());
				 
				 model.addAttribute("tag1",tag1); 
				 model.addAttribute("tag2",tag2); 
				 model.addAttribute("tag3",tag3); 
				 model.addAttribute("tag4",tag4); 
				 model.addAttribute("tag5",tag5); 
				 model.addAttribute("tag6",tag6); 
				 model.addAttribute("tag7",tag7); 
				 model.addAttribute("tag8",tag8); 
				 model.addAttribute("tag9",tag9); 
				 model.addAttribute("tag10",tag10); 
				 model.addAttribute("tag11",tag11); 
				 model.addAttribute("tag12",tag12); 
				 model.addAttribute("tag13",tag13); 
				 model.addAttribute("tag14",tag14); 
				 model.addAttribute("tag15",tag15); 
				 model.addAttribute("tag16",tag16); 
				 model.addAttribute("tag17",tag17); 
				 model.addAttribute("tag18",tag18); 
				 model.addAttribute("tag19",tag19); 
				 model.addAttribute("tag20",tag20); 
				 model.addAttribute("tag21",tag21); 
				 model.addAttribute("tag22",tag22); 
				 model.addAttribute("tag23",tag23); 
				 model.addAttribute("tag24",tag24); 
				 model.addAttribute("tag25",tag25); 
				 model.addAttribute("tag26",tag26); 
				 model.addAttribute("tag27",tag27); 
				 model.addAttribute("tag28",tag28); 
				 model.addAttribute("tag29",tag29); 
				 model.addAttribute("tag30",tag30); 
				 model.addAttribute("tag31",tag31); 
				 model.addAttribute("tag32",tag32);
				 model.addAttribute("tag33",tag33); 
				 model.addAttribute("tag34",tag34); 
				 model.addAttribute("tag35",tag35); 
				 model.addAttribute("tag36",tag36); 
				 model.addAttribute("tag37",tag37); 
				 model.addAttribute("tag38",tag38); 
				 model.addAttribute("tag39",tag39); 
				 model.addAttribute("tag40",tag40); 
				 
				}else {
					
					 String tag1 = eformservices.getdata(edata,forms.getForm_tag1());
					 String tag2 = eformservices.getdata(edata,forms.getForm_tag2());
					 String tag3 = eformservices.getdata(edata,forms.getForm_tag3());
					 String tag4 = eformservices.getdata(edata,forms.getForm_tag4());
					 String tag5 = eformservices.getdata(edata,forms.getForm_tag5());
					 String tag6 = eformservices.getdata(edata,forms.getForm_tag6());
					 String tag7 = eformservices.getdata(edata,forms.getForm_tag7());
					 String tag8 = eformservices.getdata(edata,forms.getForm_tag8());
					 String tag9 = eformservices.getdata(edata,forms.getForm_tag9());
					 String tag10 = eformservices.getdata(edata,forms.getForm_tag10());
					 String tag11 = eformservices.getdata(edata,forms.getForm_tag11());
					 String tag12 = eformservices.getdata(edata,forms.getForm_tag12());
					 String tag13 = eformservices.getdata(edata,forms.getForm_tag13());
					 String tag14 = eformservices.getdata(edata,forms.getForm_tag14());
					 String tag15 = eformservices.getdata(edata,forms.getForm_tag15());
					 String tag16 = eformservices.getdata(edata,forms.getForm_tag16());
					 String tag17 = eformservices.getdata(edata,forms.getForm_tag17());
					 String tag18 = eformservices.getdata(edata,forms.getForm_tag18());
					 String tag19 = eformservices.getdata(edata,forms.getForm_tag19());
					 String tag20 = eformservices.getdata(edata,forms.getForm_tag20());
					 String tag21 = eformservices.getdata(edata,forms.getForm_tag21());
					 String tag22 = eformservices.getdata(edata,forms.getForm_tag22());
					 String tag23 = eformservices.getdata(edata,forms.getForm_tag23());
					 String tag24 = eformservices.getdata(edata,forms.getForm_tag24());
					 String tag25 = eformservices.getdata(edata,forms.getForm_tag25());
					 String tag26 = eformservices.getdata(edata,forms.getForm_tag26());
					 String tag27 = eformservices.getdata(edata,forms.getForm_tag27());
					 String tag28 = eformservices.getdata(edata,forms.getForm_tag28());;
					 String tag29 = eformservices.getdata(edata,forms.getForm_tag29());
					 String tag30 = eformservices.getdata(edata,forms.getForm_tag30());
					 String tag31 = eformservices.getdata(edata,forms.getForm_tag31());
					 String tag32 = eformservices.getdata(edata,forms.getForm_tag32());
					 String tag33 = eformservices.getdata(edata,forms.getForm_tag33());
					 String tag34 = eformservices.getdata(edata,forms.getForm_tag34());
					 String tag35 = eformservices.getdata(edata,forms.getForm_tag35());
					 String tag36 = eformservices.getdata(edata,forms.getForm_tag36());
					 String tag37 = eformservices.getdata(edata,forms.getForm_tag37());
					 String tag38 = eformservices.getdata(edata,forms.getForm_tag38());
					 String tag39 = eformservices.getdata(edata,forms.getForm_tag39());
					 String tag40 = eformservices.getdata(edata,forms.getForm_tag40());
					 
					 model.addAttribute("tag1",tag1); 
					 model.addAttribute("tag2",tag2); 
					 model.addAttribute("tag3",tag3); 
					 model.addAttribute("tag4",tag4); 
					 model.addAttribute("tag5",tag5); 
					 model.addAttribute("tag6",tag6); 
					 model.addAttribute("tag7",tag7); 
					 model.addAttribute("tag8",tag8); 
					 model.addAttribute("tag9",tag9); 
					 model.addAttribute("tag10",tag10); 
					 model.addAttribute("tag11",tag11); 
					 model.addAttribute("tag12",tag12); 
					 model.addAttribute("tag13",tag13); 
					 model.addAttribute("tag14",tag14); 
					 model.addAttribute("tag15",tag15); 
					 model.addAttribute("tag16",tag16); 
					 model.addAttribute("tag17",tag17); 
					 model.addAttribute("tag18",tag18); 
					 model.addAttribute("tag19",tag19); 
					 model.addAttribute("tag20",tag20); 
					 model.addAttribute("tag21",tag21); 
					 model.addAttribute("tag22",tag22); 
					 model.addAttribute("tag23",tag23); 
					 model.addAttribute("tag24",tag24); 
					 model.addAttribute("tag25",tag25); 
					 model.addAttribute("tag26",tag26); 
					 model.addAttribute("tag27",tag27); 
					 model.addAttribute("tag28",tag28); 
					 model.addAttribute("tag29",tag29); 
					 model.addAttribute("tag30",tag30); 
					 model.addAttribute("tag31",tag31); 
					 model.addAttribute("tag32",tag32);
					 model.addAttribute("tag33",tag33); 
					 model.addAttribute("tag34",tag34); 
					 model.addAttribute("tag35",tag35); 
					 model.addAttribute("tag36",tag36); 
					 model.addAttribute("tag37",tag37); 
					 model.addAttribute("tag38",tag38); 
					 model.addAttribute("tag39",tag39); 
					 model.addAttribute("tag40",tag40); 
				}
				 
				 
				 if(forms.getForm_id() == 1 ) {
					 return "/form/bill";
				 }else {
					 return "/form/bill2";
				 }
		    }    
		    
	    
	    
		   
		   
		   
		   
		   
		   
		   
		   
		   @RequestMapping(value="/pnsearch-filemanager",method = RequestMethod.GET)    
		    public String pnsearch(
		    		ModelMap model
		    		,@RequestParam(required = false) int tab
		    		,@RequestParam(required = false) int jobid
		    		,@RequestParam(required = false) String jobview_type
		    		,@RequestParam(required = false) String jobview_category
		    		,@RequestParam(required = false) String jobview_app
		    		){ 
		    	
				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());  
			

				
				jobview jobview = new jobview();
				
				System.out.println("tab" + tab);
				System.out.println("jobid" + jobid);
				System.out.println("jobview_type" + jobview_type);
				
				
				jobview.setJobview_tab(tab);
				jobview.setJobview_id(jobid);
				jobview.setJobview_type(jobview_type.isBlank() ?  null : jobview_type);
				jobview.setJobview_category(jobview_category.isBlank() ?  null : jobview_category);
				jobview.setJobview_app(jobview_app.isBlank() ?  null : jobview_app);
				
				Upload_pnDAO.setview(jobview);
				jobview jobviews = Upload_pnDAO.getView(usersed.getUser_name());
				
				
				if(jobviews.getJobview_tab() == 0) {
					List<showupload_pn> uploadpn = Upload_pnDAO.getall(usersed.getUser_name(),jobviews);
					model.addAttribute("uploadpn",uploadpn); 
					model.addAttribute("uploadtype",1); 
				}
				else if(jobviews.getJobview_tab() == 1) {
					List<showupload_pn> uploadpn = Upload_pnDAO.getall2(usersed.getUser_name(),jobviews);
					model.addAttribute("uploadpn",uploadpn);  
					model.addAttribute("uploadtype",1); 
				}
				else if(jobviews.getJobview_tab() == 2) {
					List<showupload_pn> uploadpn = Upload_pnDAO.getall1s(usersed.getUser_name(),jobviews);
					model.addAttribute("uploadpn",uploadpn); 
					model.addAttribute("uploadtype",1); 
				}
				else if(jobviews.getJobview_tab() == 3) {
					List<showupload_pn> uploadpn = Upload_pnDAO.getalleds(usersed.getUser_name(), jobviews);
					model.addAttribute("uploadpn",uploadpn); 
					model.addAttribute("uploadtype",0); 
				}
				else {
					List<showupload_pn> uploadpn = Upload_pnDAO.getalled(usersed.getUser_name(),jobviews);
					model.addAttribute("uploadpn",uploadpn); 
					model.addAttribute("uploadtype",0); 
				}
				
				

				
				model.addAttribute("jobviews",jobviews); 
				
				
				
			  	return "/form/pnsearch";	
		    }    
		    
		   
		   
		   
		   @RequestMapping(value="/stock-filemanager/{id}",method = RequestMethod.GET)    
		    public String stock(
		    		ModelMap model
		    		,@PathVariable int id
		    		){ 
		   
			   edata stock = EdataDAO.getbyid(id);
			   model.addAttribute("stock",stock);
			   
			   upload_pn upload_pns = Upload_pnDAO.getbyId(stock.getData_planid());
			   condition port =  ConditionDAO.getbyid((int)upload_pns.getUpload_pn_monitor());
		  	    String products = null;
				 String lots = null;
			   
			   
			
			   
			   System.out.println("port1 "+port.getCalculation_port1() );
			   System.out.println("port2 "+port.getCalculation_port2() );
			 
			 condition conditions = null;
			 
			   if(port.getCalculation_port1() > 0) {
				   conditions =  ConditionDAO.getbyid(port.getCalculation_port1());
			   }
			   else if(port.getCalculation_port2() > 0) {
				   conditions =  ConditionDAO.getbyid(port.getCalculation_port2());
			   }
			   
			 

			 
				LocalDateTime localDate3 = LocalDateTime.now().minusYears(2);
			
			   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM");
			
			   	 String fors = localDate3.format(formatter2);
				 String date3 = localDate3.format(formatter2); 
			 

				
				

				
				   model.addAttribute("monitor1",conditions.getCondition_id());  
				

				  if(conditions != null) {
				   
				System.out.println("test 1");	  
					  
				   
				 upload_pn upload_pns1 = Upload_pnDAO.getbymonitor(conditions.getCondition_id());  
				 List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(), 0);
				 model.addAttribute("eformfields",eformfields);
				 
				 
				 condition subconditions = ConditionDAO.getbysub(conditions.getCondition_id());
				 model.addAttribute("subconditions",subconditions);  
				
				 upload_pn upload_pns2 = null;
				 List<edata> edatas =null;  
				  
				 if(subconditions != null ) {

					 
					 System.out.println("test 2.1");	 
					 
					 List<eformfield> eformfields2 = EformfieldDAO.getall(subconditions.getCondition_id(), 0);
					 model.addAttribute("eformfields2",eformfields2);  
	 
				 }else {
				 
					 System.out.println("test 2.2");	 
				    condition database1 = ConditionDAO.getbyport(conditions.getCondition_id(),1);
				    condition  database2 = null;
				  
				    System.out.println("step : 1");
				    
				    int monitor = 0;
					    
					    if(database1 == null) {
					    	database2 = ConditionDAO.getbyport(conditions.getCondition_id(),2);
		
					    }
					    
					    if(database1 != null || database2 != null) {
					    	if(database1 != null) {
					    		 monitor =database1.getCalculation_port2();
					    	}
					    	if(database2 != null) {
					    		monitor= database2.getCalculation_port1();
					    	}
					    }
				    
				    
				    if(monitor > 0) {
				    	
				    	  System.out.println("step : 2");
				    	  System.out.println("monitor : "+monitor);
				    	  
				    	  
				    	 upload_pns2 = Upload_pnDAO.getbymonitor(monitor);
				    	 condition conditions2 = ConditionDAO.getbyid(monitor);
				    	   model.addAttribute("monitor2",monitor);  
				        List<eformfield> eformfields3 = EformfieldDAO.getall(conditions2.getCondition_id(), 0);
				  		model.addAttribute("eformfields3",eformfields3);  
				  	  	 model.addAttribute("conditions2",conditions2); 

				  	  	System.out.println("conditions2 ....." + conditions2);
				    }
				    

				 }
				    
				    
	
				    if(upload_pns2 != null) {
				    	
						 System.out.println("test 3");	 
				    	    int no_product2 = 0;
				    	    int no_lot2 = 0;
				  
				    	    
						    eformfield product2  = EformfieldDAO.getlabel("product", (int)upload_pns2.getUpload_pn_monitor());
						    eformfield lot2  = EformfieldDAO.getlabel("lot", (int)upload_pns2.getUpload_pn_monitor());
						    
						    if(product2 != null) {no_product2 =product2.getField_postion(); }
						    if(lot2 != null ) { no_lot2 =lot2.getField_postion();}
						    

							  
							if(product2 != null) { products = eformservices.getstock(stock, product2.getField_field_no());      }
							if(lot2 != null) { lots = eformservices.getstock(stock, lot2.getField_field_no2());      }
							   

				        	 List<edata> edatas2 = EdataDAO.getallproduct(upload_pns2.getUpload_pn_id(),products,lots,no_product2,no_lot2);
				        	 model.addAttribute("edatas2",edatas2); 

				    }
				    	
				    
				    
				    if(upload_pns1 != null) { 
				    	 System.out.println("test 4");	 
				    	   int no_product3 = 0;
				    	    int no_lot3 = 0;
						    eformfield product3  = EformfieldDAO.getlabel("product", (int)upload_pns1.getUpload_pn_monitor());
						    eformfield lot3  = EformfieldDAO.getlabel("lot",(int) upload_pns1.getUpload_pn_monitor());
						    if(product3 != null) {no_product3 =product3.getField_postion(); }
						    if(lot3 != null ) { no_lot3 =lot3.getField_postion();}
						    
						    
							if(product3 != null) { products = eformservices.getstock(stock, product3.getField_field_no());      }
							if(lot3 != null) { lots = eformservices.getstock(stock, lot3.getField_field_no2());      }
						    
							System.out.println("product3 " +product3);		
							System.out.println("upload_pns1 " +upload_pns1.getUpload_pn_id());		
							System.out.println("products "+products);	
							System.out.println("lots "+lots);	
							System.out.println("no_product"+no_product3);	
							System.out.println("no_lot3 "+no_lot3);	
							
				    	edatas = EdataDAO.getallproduct(upload_pns1.getUpload_pn_id(),products,lots,no_product3,no_lot3); 
				    	
						System.out.println("edatas "+edatas);	
				    	}
				   	
				    
					 model.addAttribute("edatas",edatas);  	
				 	 model.addAttribute("conditions",conditions);  	
				 
				 	 System.out.println("upload_pns1 ....." + upload_pns1.getUpload_pn_id());
				 
				

				  } 
				
			
	
			 
			   
			  	return "/stock";	
		    }    
		    
		   
		   @RequestMapping(value="/closestock-filemanager/{id}",method = RequestMethod.GET)    
		    public String closestock(ModelMap model,@PathVariable int id){ 
			   
			   EdataDAO.closestock(id);
			   return "redirect:/stock-filemanager/"+id;
			   
		   }
		  
		   @RequestMapping(value="/openstock-filemanager/{id}",method = RequestMethod.GET)    
		    public String openstock(ModelMap model,@PathVariable int id){ 

			   EdataDAO.openstock(id);
			   return "redirect:/stock-filemanager/"+id;
			   
		   }
		  
		   
		   @RequestMapping(value="/turnin-filemanager/{id}",method = RequestMethod.GET)    
		    public String turnin(@PathVariable int id,@RequestParam(value = "app",required=false) String app){ 

			   
				 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 
				DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("HH:mm:ss");
				String time = localDate2.format(formatter5);
				 
				 
				 String date = localDate.format(formatter);
			   	 String datetime = localDate2.format(formatter3);
			   	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			   
			   	 
			   	 Upload_pnDAO.turn(1,id,usersed.getUser_name());
			   	 EdataDAO.setTurnFlow(id);
			   	
			   	
				upload_pn upload_pn = Upload_pnDAO.getbyId(id);

			   
			    notification  notifications = new notification();
	 			notifications.setNotification_iduser(usersed.getUser_name());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("คุณได้อัพโหลดเอกสาร");
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_type("Job queue all");
				notifications.setNotification_refer(String.valueOf(upload_pn.getUpload_pn_id()));
				NotificationDAO.save(notifications);

			   
				return "redirect:/duepn-filemanager?id="+id+"&&step=0&&group=3";
			   
			  
			   
		   }
		  
		   @RequestMapping(value="/undoturn-filemanager/{id}",method = RequestMethod.GET)    
		    public String undoturn(@PathVariable int id,@RequestParam(value = "app",required=false) String app){ 

			 	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				   
			   	 
			   	 Upload_pnDAO.turn(1,id,usersed.getUser_name());
			   	 EdataDAO.setUnTurnFlow(id);
			   	 upload_pn upload_pn = Upload_pnDAO.getbyId(id);
				
			   	 
			   	 
				 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 
				 DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("HH:mm:ss");
				 String time = localDate2.format(formatter5);	 
				 String date = localDate.format(formatter);
			   	 String datetime = localDate2.format(formatter3);
			   	 
			   	 
				    notification  notifications = new notification();
		 			notifications.setNotification_iduser(usersed.getUser_name());
					notifications.setNotification_iduser2(null);
					notifications.setNotification_alert("คุณได้ยกเลิกการอัพโหลดเอกสาร");
					notifications.setNotification_date(datetime);
					notifications.setNotification_read(0);
					notifications.setNotification_type("Job queue all");
					notifications.setNotification_refer(String.valueOf(upload_pn.getUpload_pn_id()));
					NotificationDAO.save(notifications);
			   	 
			   	 
				 return "redirect:/duepn-filemanager?id="+id+"&&step=0&&group=3";
			    
		   }
		   
		   
		   @RequestMapping(value="/delreport-filemanager/{id}/{plan}",method = RequestMethod.GET)    
		    public String delreport(ModelMap model,@PathVariable int id,@PathVariable int plan){ 

			   Report_pnDAO.deletes(id);
			   return "redirect:/duepn-filemanager?id="+plan+"&&step=0&&group=3";
			   
		   }
		   
		    @RequestMapping(value="/indexapp-filemanager/{id}",method = RequestMethod.GET)
						public String indexapp(ModelMap model,@PathVariable int id,@RequestParam(required =false) String from 
								) throws Exception { 
		    	
		    						Platform platform = new Platform();
		    						platform.setApp_processes_id(id);
		    						LoadApp   LoadApp =   new LoadApp();	
		    						platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireAllIndex().load();
		    						Map<String, Object> data = platformService.getModelData();
		    						model.addAllAttributes(data);
		    						model.addAttribute("from",from); 
		    						
		    						System.out.println("Test 55555");
		    						
		    						long countOpen =     helpDeskRepository.countByStatus("OPEN");
		    						model.addAttribute("countOpen",countOpen); 
		    						
		    						
		    						dataset  dataset1 = DatasetDAO.getMain("Register_Type");
		    						List<dataset_data> Register_Type = Dataset_dataDAO.getall(dataset1.getDataset_id());
		    						model.addAttribute("Register_Type", Register_Type);
		    						
		    						dataset  dataset2 = DatasetDAO.getMain("Register_groups");
		    						List<dataset_data> Register_groups = Dataset_dataDAO.getall(dataset2.getDataset_id());
		    						model.addAttribute("Register_groups", Register_groups);
		    						
		    						dataset  dataset3 = DatasetDAO.getMain("Register_status");
		    						List<dataset_data> Register_status = Dataset_dataDAO.getall(dataset3.getDataset_id());
		    						model.addAttribute("Register_status", Register_status);

		    					
		    						
		    						dataset  dataset4 = DatasetDAO.getMain("Party_type");
		    						List<dataset_data> Party_type = Dataset_dataDAO.getall(dataset4.getDataset_id());
		    						model.addAttribute("Party_type", Party_type);
		    						
		    						dataset  dataset5 = DatasetDAO.getMain("Business_category");
		    						List<dataset_data> Business_category = Dataset_dataDAO.getall(dataset5.getDataset_id());
		    						model.addAttribute("Business_category", Business_category);
		    						
		    						dataset  dataset6 = DatasetDAO.getMain("Region");
		    						List<dataset_data> Region = Dataset_dataDAO.getall(dataset6.getDataset_id());
		    						model.addAttribute("Region", Region);
		    						

		    						
		    						
		    						dataset  dataset8 = DatasetDAO.getMain("Register_title");
		    						List<dataset_data> Register_title = Dataset_dataDAO.getall(dataset8.getDataset_id());
		    						model.addAttribute("Register_title", Register_title);
		    					
		    						
				    				return "app/windows/indexapp";
			}
		    
		    
		    @RequestMapping(value="/indexAppFullSearch-filemanager", method = RequestMethod.POST)
		    public String indexAppFullSearch(
		            ModelMap model,
		            @RequestParam int process_id,
		            registers register
		            ) { 
		        
		        // 1. ค้นหาข้อมูล Data
		        FullResult exampleData = EdataDAO.searchRegistersDirect(register, 500, 1);
		        model.addAttribute("exampleData", exampleData.getRegisters());
		        
		        // 2. ดึง Process และ Listview
		        process process = EdataDAO.getProcess(process_id);
		        model.addAttribute("process", process);
		        model.addAttribute("registerType", "Profile");
		        
		        
		        String listview = process.getListview(); // เช่น "2,3"
		        // แปลง Listview เป็น List<String> เพื่อให้ค้นหาง่ายขึ้น
		        List<String> listviewIds = new ArrayList<>();
		        if (listview != null && !listview.isEmpty()) {
		            listviewIds = Arrays.asList(listview.split(","));
		        }
		        
		        // 3. ดึง Heads ทั้งหมดมา
		        List<registerkey> heads = EdataDAO.getAllRegisterkey(3, "register");
		        
		        // 4. สร้าง List ใหม่เพื่อเก็บเฉพาะ Heads ที่ผ่านเงื่อนไข
		        List<registerkey> filteredHeads = new ArrayList<>();
		        
		        // ใช้ BeanWrapper ของ Spring เพื่ออ่านค่าจาก Object register แบบ Dynamic ด้วย String (mainkey)
		        BeanWrapper registerWrapper = PropertyAccessorFactory.forBeanPropertyAccess(register);
		        
		        for (registerkey head : heads) {
		            String headIdStr = String.valueOf(head.getId());
		            
		            // เงื่อนไขที่ 1: ถ้า ID อยู่ใน Listview ให้แสดงเลย
		            if (listviewIds.contains(headIdStr)) {
		                filteredHeads.add(head);
		                continue; // ข้ามไปทำรอบต่อไป
		            }
		            
		            // เงื่อนไขที่ 2: ถ้าไม่อยู่ใน Listview ให้เช็คว่ามีค่าส่งมาจาก form (register object) หรือไม่
		            String mainKey = head.getMainkey(); // เช่น "register_groups"
		            
		            // เช็คว่าใน Object register มีฟิลด์ชื่อนี้ให้ดึงค่าหรือไม่
		            if (mainKey != null && !mainKey.isEmpty() && registerWrapper.isReadableProperty(mainKey)) {
		                Object value = registerWrapper.getPropertyValue(mainKey); // ดึงค่าจากฟิลด์นั้น
		                
		                if (value != null) {
		                    // เช็คกรณีเป็น String (Text) และไม่ได้ว่างเปล่า
		                    if (value instanceof String && !((String) value).trim().isEmpty()) {
		                        filteredHeads.add(head);
		                    } 
		                    // เช็คกรณีเป็น List (Multiple Select) และไม่ได้ว่างเปล่า
		                    else if (value instanceof List && !((List<?>) value).isEmpty()) {
		                        filteredHeads.add(head);
		                    }
		                    // เช็คกรณีเป็น Array
		                    else if (value.getClass().isArray() && java.lang.reflect.Array.getLength(value) > 0) {
		                        filteredHeads.add(head);
		                    }
		                }
		            }
		        }
		        
		        // 5. ส่งเฉพาะ filteredHeads กลับไปที่หน้าจอ
		        model.addAttribute("heads", filteredHeads);
		        return "app/windows/indexapp_data";
		    }
		    
		    @RequestMapping(value="/indexappsearch-filemanager",method = RequestMethod.POST)
			public String indexappsearch(
					ModelMap model
					,@RequestParam int id
					,@RequestParam(required =false) String from 
					,@RequestParam(required =false) String search 
					) { 
	
						Platform platform = new Platform();
						platform.setApp_processes_id(id);
						platform.setSearch(search);
						LoadApp   LoadApp =   new LoadApp();	
						platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireAllIndex().load();
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
						model.addAttribute("from",from); 
							
	    				return "app/windows/indexapp_data";
	    		}
		    
	
		    
		    @ResponseBody
		    @RequestMapping(value="/indexappsearchajax-filemanage",method = RequestMethod.POST)
			public Object indexappsearchajax(@RequestParam(required =false) String search ,@RequestParam(required =false) String type 
					) { 			
		    	
		    			    Map<String, Object> modelData = new HashMap<String, Object>();
							process processes = WorkflowDAO.getProcessByIndex(type);
							Platform platform2 = new Platform();
							platform2.setApp_processes_id(processes.getId());
							platform2.setSearch(search);
							
							LoadApp  subLoadApp  = new  LoadApp();
							subLoadApp.setPlatform(platform2);
							subLoadApp.setProcess(processes);
							subLoadApp.getProcess().setShow_limit(100);
							subLoadApp.getProcess().setLimit_head(1);
							
							register_Component.initiate(subLoadApp,modelData).requireAllIndex().load();
							
							if(modelData.get("exampleData") != null ) {
								
								if(type.equals("Profile")) { 
									List<registers> exampleData  =  (List<registers>) modelData.get("exampleData");
									return scheduleService.createRegisterDataJson(exampleData);
								}
								else if(type.equals("Product")) { 
									List<product_master> exampleData  =  (List<product_master>) modelData.get("exampleData");
									return scheduleService.createProduct_masterJson(exampleData);
								}
						
								
								
								
							}
						
			
						     return new ArrayList<>(); 
	    	}
		    
		
		    
		    @ResponseBody
		    @RequestMapping(value="/segmentationforuse-filemanager",method = RequestMethod.GET)
			public Object segmentationforuse(){ 			
		    	
		    			    Map<String, Object> modelData = new HashMap<String, Object>();
		    				List<Segmentation> exampleData  =  EdataDAO.getallSegmentation();
							return scheduleService.createSegmentationJson(exampleData);
	    	}
		    
		
		    
		    
		    
		    
		    @RequestMapping(value="/segmentation-filemanager/{id}",method = RequestMethod.GET)
			public String segmentation(ModelMap model,@PathVariable int id,@RequestParam(required =false) String from 
					) { 
	
						Platform platform = new Platform();
						platform.setApp_processes_id(id);
						LoadApp   LoadApp =   new LoadApp();	
						platformService.initiate(LoadApp).startAppIndex(platform).requireAllSegmentation().load();
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
						model.addAttribute("from",from); 
						
						
						
	    				return "app/windows/segmentation";
	    		}
		    
		    
		    @RequestMapping(value="/transactionflow-filemanager/{id}",method = RequestMethod.GET)
		 			public String transactionflow(ModelMap model,@PathVariable int id,@RequestParam(required =false) String from 
		 					) { 
		 	
						    	Platform platform = new Platform();
								platform.setApp_processes_id(id);
								LoadApp   LoadApp =   new LoadApp();	
								
								platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireAuditIndex().load();
								Map<String, Object> data = platformService.getModelData();
								model.addAllAttributes(data);
								model.addAttribute("from",from); 
									
								return "app/windows/indexapp_data";
		 	    		}
		    @RequestMapping(value="/HelpDeskprofile-filemanager/{id}",method = RequestMethod.GET)
 			public String HelpDeskprofile(ModelMap model,@PathVariable int id,@RequestParam(required =false) String from 
 					) { 
 	
				    	Platform platform = new Platform();
						platform.setApp_processes_id(id);
						LoadApp   LoadApp =   new LoadApp();	
						
						platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireHelpDeskprofile().load();
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
						model.addAttribute("from",from); 
							
						return "app/windows/indexapp_data";
 	    		}
		    
		    @RequestMapping(value="/GroupingCategorization-filemanager/{id}",method = RequestMethod.GET)
 			public String GroupingCategorization(ModelMap model,@PathVariable int id,@RequestParam(required =false) String from 
 					) { 
 	
				    	Platform platform = new Platform();
						platform.setApp_processes_id(id);		
						LoadApp   LoadApp =   new LoadApp();	
						platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireGroupingIndex().load();
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
						model.addAttribute("from",from); 
							
						return "app/windows/GroupingCategorization";
 	    		}
 		    
		    @RequestMapping(value="/acknowledge-filemanager/{process_id}/{data_id}/{status}/{step}",method = RequestMethod.GET)
	 			public String acknowledge(
	 					ModelMap model,
	 					@PathVariable int process_id,
	 					@PathVariable int step,
	 					@PathVariable int data_id,
	 					@PathVariable int status
	 					) { 
		    	
		    	 
				Platform platform = new Platform();
				platform.setApp_item_id(data_id);
				platform.setApp_item_id2(status);
				platform.setApp_processes_id(process_id);
				platform.setWorkflow_step(step);
				
				
				LoadApp   LoadApp =   new LoadApp();	
				platformService.initiate(LoadApp).workflow(platform).Acknowledge();
		    	
		    	
		
			                  return "redirect:/editdocumentapp-filemanager/"+data_id+"/"+process_id;
	 	    		}
		  
		    @RequestMapping(value="/FollowUp-filemanager/{process_id}/{data_id}/{status}",method = RequestMethod.GET)
	 			public String FollowUp(
	 					ModelMap model,
	 					@PathVariable int process_id,
	 					@PathVariable int data_id,
	 					@PathVariable int status
	 					
	 					) { 
			                  EdataDAO.setFollowUp(data_id,status);
							return "redirect:/editdocumentapp-filemanager/"+data_id+"/"+process_id;
	 	    		}
		  
		    
		    @RequestMapping(value="/DataUpdateRequests-filemanager/{id}",method = RequestMethod.GET)
	 			public String DataUpdateRequests(ModelMap model,@PathVariable int id,@RequestParam(required =false) String from 
	 					) { 
	 	
					    	Platform platform = new Platform();
							platform.setApp_processes_id(id);		
							LoadApp   LoadApp =   new LoadApp();	
							platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireGroupingIndex().load();
							Map<String, Object> data = platformService.getModelData();
							model.addAllAttributes(data);
							model.addAttribute("from",from); 
								
							return "app/windows/DataUpdateRequests";
	 	    		}
	 		    
		    
		    
		    
		    @RequestMapping(value="/GroupingCategorization-filemanager/{id}/{type}",method = RequestMethod.GET)
	 			public String GroupingCategorization(ModelMap model,@PathVariable int id,@PathVariable String type,@RequestParam(required =false) String from 
	 					) { 
	 	
					    	Platform platform = new Platform();
							platform.setApp_processes_id(id);		
							platform.setGrouping(type);
							LoadApp   LoadApp =   new LoadApp();	
							
							
							platformService.initiate(LoadApp).startAppIndex(platform).renderIndexLayout().requireGroupingIndex().load();
							Map<String, Object> data = platformService.getModelData();
							model.addAllAttributes(data);
							model.addAttribute("from",from); 
							model.addAttribute("Grouping",type); 
							return "app/windows/GroupingCategorization";
	 	    		}
	 		    
    
		    
		    
		    
		    @RequestMapping(value="/managementapp-filemanager/{id}",method = RequestMethod.GET)
			public String managementapp(ModelMap model,@PathVariable int id,@RequestParam(required =false,defaultValue="0") int thisapp 
					) { 
		    	
		    	
		    	try {
		    		
		    	   	Platform platform = new Platform();
					platform.setApp_processes_id(id);
					platform.setApp_item_id(thisapp);
					LoadApp   LoadApp =   new LoadApp();	
					
					
					platformService.initiate(LoadApp).appManagement(platform).chainApp().load();
					Map<String, Object> data = platformService.getModelData();

					model.addAllAttributes(data);
	    			return "app/windows/management";
		    		
		    	}catch(Exception e) {
		    		System.err.println(e);
		    		return "null";
		    	}
			 
	    		}
		    
		    
		    @RequestMapping(value="/markAsRead-filemanager/{id}",method = RequestMethod.GET)
					public String markAsRead(ModelMap model,@PathVariable int id
							) { 
		    				NotificationDAO.setview(id);
		    				return "ajax/save";
		    }
		    
		    
		    @RequestMapping(value="/tabmanagement-filemanager/{id}",method = RequestMethod.GET)
			public String tabmanagement(ModelMap model,@PathVariable int id
					,@RequestParam(required =false,defaultValue="0") int thistab 
					,@RequestParam(required =false,defaultValue="0") int thisapp 
					) { 
		    	
			    	Platform platform = new Platform();
					platform.setApp_processes_id(id);
					platform.setApp_step(thistab);
					platform.setApp_item_id(thisapp);

				   	LoadApp   LoadApp =   new LoadApp();		
					platformService.initiate(LoadApp).appManagement(platform).chainAppData().load();
					Map<String, Object> data = platformService.getModelData();

					model.addAllAttributes(data);
					
					user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			    	NotificationDAO.readAll(String.valueOf(usersed.getUser_id()),Userservice.getPoliciesString(),id);
			    	model.addAttribute("PoliciesString",Userservice.getPoliciesString()); 
					
					
					
					System.out.println(" tabmanagement ");
					
	    			return "app/windows/tabmanagement";
	    		}
		    

		    
		    
		    @RequestMapping(value="/profileapp-filemanager/{id}/{process_id}",method = RequestMethod.GET)
					public String profileapp(
							ModelMap model,@PathVariable String id,
							@PathVariable int process_id,
							@RequestParam(required =false) String from 
							,@RequestParam(required =false,defaultValue="0") int Tray 
							) { 
		    	

					    	  try {
								    		  
								    		  Platform platform = new Platform();
								    			platform.setApp_processes_id(process_id);
								    			platform.setEdata_from(from);
								    			
								    			
								    			if (id.matches("\\d+")) {
								    		        // เป็นตัวเลข
								    		        int idInt = Integer.parseInt(id); // แปลงได้ปลอดภัย
								    				platform.setApp_item_id(idInt);
								    				
								    				long countOpen = helpDeskRepository.countByRefIdAndStatus(idInt, "OPEN");
								    		        model.addAttribute("countOpen", countOpen);
								    		    } else {
								    		        // เป็นข้อความ
								    		    	
								    		    	registers register = EdataDAO.getProfileByCode(id);
								    		    	platform.setApp_item_id(register.getId());
								    		    	
								    				long countOpen = helpDeskRepository.countByRefIdAndStatus(register.getId(), "OPEN");
								    		        model.addAttribute("countOpen", countOpen);
								    		    }
								    			
								    			
								    	LoadApp   LoadApp =   new LoadApp();			
									
										platformService.initiate(LoadApp).startAppIndex(platform).requireIndex().load();
										Map<String, Object> data = platformService.getModelData();
										model.addAllAttributes(data);
										model.addAttribute("from",from); 
										model.addAttribute("Tray",Tray); 
								
										return "app/windows/profile";
					    		  
					    		  
					    	  }catch(Exception e) {
					    		  
					    		  System.err.println(e);
					    			return null;
					    	  }
								    			
			    			
			    			
			    		}
		    
		    
		    @RequestMapping(value="/productapp-filemanager/{id}/{process_id}",method = RequestMethod.GET)
			public String productapp(ModelMap model,@PathVariable int id,@PathVariable int process_id,@RequestParam(required =false) String from 
					) { 
    	
			    			Platform platform = new Platform();
			    			platform.setApp_processes_id(process_id);
			    			platform.setApp_item_id(id);
			    			platform.setEdata_from(from);
			    			
			    	LoadApp   LoadApp =   new LoadApp();		
					platformService.initiate(LoadApp).startAppIndex(platform).requireIndex().load();
					Map<String, Object> data = platformService.getModelData();
					model.addAllAttributes(data);
					model.addAttribute("from",from); 
    	
    	
	    				return "app/windows/product";
	    		}
    
		    
		    
		    
		    
		    
		    
		    @RequestMapping(value="/Syncdataindexapp-filemanager/{edata_id}/{index_id}/{process_id}",method = RequestMethod.GET)
			public String Syncdataindexapp(ModelMap model
					,@PathVariable int index_id
					,@PathVariable int process_id
					,@PathVariable String  edata_id 
					) { 
    	
			    			Platform platform = new Platform();
			    			platform.setApp_processes_id(process_id);
			    			platform.setApp_item_id(index_id);
			    			platform.setEdata_from(edata_id);
			    			
			    	LoadApp   LoadApp =   new LoadApp();		
					platformService.initiate(LoadApp).startAppIndex(platform).FullSync().load();
					Map<String, Object> data = platformService.getModelData();
					model.addAllAttributes(data);
					
			
					String index =  ((process) data.get("process")).getRegister();
					
					if(index.equals("Profile")) {
						return "app/windows/profileFullSync";
					}
					
					return "app/windows/productFullSync";
	    				
	    				
	    		}
		    
		    @RequestMapping(value="/UnSyncdataindexapp-filemanager/{edata_id}/{index_id}/{process_id}",method = RequestMethod.GET)
		 			public String UnSyncdataindexapp(ModelMap model
		 					,@PathVariable int index_id
		 					,@PathVariable int process_id
		 					,@PathVariable String  edata_id 
		 					) { 
		    	
					    	Platform platform = new Platform();
			    			platform.setApp_processes_id(process_id);
			    			platform.setApp_item_id(index_id);
			    			platform.setEdata_from(edata_id);
						   	LoadApp   LoadApp =   new LoadApp();		
			    			
		    				platformService.initiate(LoadApp).startAppIndex(platform).FullSync().load();
		    				Map<String, Object> data = platformService.getModelData();
							model.addAllAttributes(data);
							

							String index =  ((process) data.get("process")).getRegister();
							if(index.equals("Profile")) {
								return"app/windows/profileUnFullSync";
							}
							
		    				return"app/windows/productUnFullSync";
		 	    		}
		 		    
		    
		    
		    
		    @RequestMapping(value="/shortprofileapp-filemanager/{id}",method = RequestMethod.GET)
			public String shortprofileapp(ModelMap model,@PathVariable String id,@RequestParam(required =false) String from 
					) { 
    	
		    	
		    				registers Register = register_Component.getProfileByCode(id);
		    				process processes = WorkflowDAO.getProcessByIndex("Profile");
		    	
		    				if(processes != null) {
		    				
					    			Platform platform = new Platform();
					    			platform.setApp_processes_id(processes.getId());
					    			platform.setApp_item_id(Register.getId());
					    			platform.setEdata_from(from);
					    		   	LoadApp   LoadApp =   new LoadApp();	
		    			
									platformService.initiate(LoadApp).startAppIndex(platform).requireIndex().load();
									Map<String, Object> data = platformService.getModelData();
									model.addAllAttributes(data);
									model.addAttribute("from",from); 
									model.addAttribute("shortprofile",1); 
		    	
			    				return "app/windows/profile";
		    				}else {
		    					return "ajax/save";
		    				}
	    		}

		    
		    

		    @RequestMapping(value="/profileCalenderapp-filemanager/{id}/{process_id}",method = RequestMethod.GET)
			public String profileCalenderapp(ModelMap model,@PathVariable int id,@PathVariable int process_id
					) { 
    	
	    				return "app/windows/calendar";
	    		}

		    
		    @RequestMapping(value="/extension-filemanager/{id}/{register_id}/{processes_id}",method = RequestMethod.GET)
						public String extension(ModelMap model,@PathVariable int id,@PathVariable String register_id
								,@PathVariable int processes_id
								,@RequestParam(required =false) String from 
								,@RequestParam(required =false,defaultValue="0") int Tray 
								) { 
		    		
		         	int idInt =0;
		    	
				    	if (register_id.matches("\\d+")) {
		    		        // เป็นตัวเลข
		    		        idInt = Integer.parseInt(register_id); // แปลงได้ปลอดภัย
	
		    		    } else {
		    		        // เป็นข้อความ
		    		    	registers register = EdataDAO.getProfileByCode(register_id);
		    		        idInt = register.getId();
		    		    }
		    			
		    	
		    	
						    	Platform platform = new Platform();
						    	platform.setApp_item_id(idInt);
						    	platform.setExtension_process(id);
						    	platform.setApp_processes_id(processes_id);
						    	
						    	LoadApp   LoadApp =   new LoadApp();	
						    	
								platformService.initiate(LoadApp).extension(platform).index().load();
								
								
								
								Map<String, Object> data = platformService.getModelData();
								model.addAllAttributes(data);
								model.addAttribute("Tray",Tray); 
								model.addAttribute("from",from); 
				    			return "app/windows/extension";
				    		}
		    
		    
		    @RequestMapping(value="/saveappbyextension-filemanager",method = RequestMethod.POST)
			public String saveappbyextension(
					edata edata
					,@RequestParam int extension_id 
					,@RequestParam(required =false) String file_patch 
					,@RequestParam(required =false) String Tray 
					,@RequestParam(required =false) CommonsMultipartFile[] files
				    ,HttpSession session
				    ,ModelMap model
					) { 
		    	
		    	
		    
		    	Platform platform = new Platform();
		    	platform.setEdata(edata);
		    	platform.setFiles(files);
		    	platform.setExtension_process(extension_id); 
	
		    	if(file_patch != null)  {
		    		folder folders =  FolderDAO.getbytype(file_patch);
		    		String uploadDir = session.getServletContext().getRealPath("/filesuploads/" + folders.getFolder_type());
		    		platform.setPatch(folders);
		    		platform.setRealPath(uploadDir);
		    	}
		    			System.out.println(" edata "+edata.getReference());
		    			
		    			LoadApp   LoadApp =   new LoadApp();	
		    			
		    			platformService.initiate(LoadApp).extension(platform).save();	
		    			model.addAttribute("Tray",Tray); 
		    			
		    			
	    				return "redirect:/extension-filemanager/"+extension_id+"/"+edata.getId_refer()+"/"+edata.getProcesses_id()+"?from="+edata.getReference();
	    		}

		    
		    
		    @RequestMapping(value="/deldataextension-filemanager/{id}",method = RequestMethod.GET)
		 			public String deldataextension(edata edata,@PathVariable int id ) { 
		    					
		    
		 		    			Platform platform = new Platform();
		 		    			edata.setData_id(id);
		 		    			platform.setEdata(edata);

		 		   			LoadApp   LoadApp =   new LoadApp();	
		 		    			
		 		    			platformService.initiate(LoadApp).extension(platform).delete();	
		 	    				return "app/windows/save";
		 	    		}
		    
		    @RequestMapping(value="/cancelDeleteByextension-filemanager/{id}",method = RequestMethod.GET)
 			public String cancelDeleteByextension(edata edata,@PathVariable int id ) { 
    					
    
 		    			Platform platform = new Platform();
 		    			edata.setData_id(id);
 		    			platform.setEdata(edata);
	 		   			LoadApp   LoadApp =   new LoadApp();	
 		    			platformService.initiate(LoadApp).extension(platform).cancelDelete();	
 	    				return "app/windows/save";
 	    		}
    
		    @RequestMapping(value="/editdataextension-filemanager/{id}/{id_refer}/{processes_id}/{extension_process}",method = RequestMethod.GET)
 			public String editdataextension(ModelMap model,edata edata,@PathVariable int id
 					,@PathVariable int extension_process
 					,@PathVariable int processes_id,@PathVariable int id_refer ) { 
    					

	    				Platform platform = new Platform();
	    				edata.setData_id(id);
	    				platform.setEdata(edata);
	    			 	platform.setExtension_process(extension_process);
				    	platform.setApp_processes_id(processes_id);
				    	platform.setApp_item_id(id_refer);
						LoadApp   LoadApp =   new LoadApp();	
	    				platformService.initiate(LoadApp).extension(platform).show().index().load();	
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
	    				
	    				return "app/windows/extension";

 	    		}
		    
		    
		    
		    
		    
		    @RequestMapping(value="/analytics-filemanager/{id}",method = RequestMethod.GET)
			public String reportapp(ModelMap model,@PathVariable int id) { 	
				List<BiReportSetting> reports = BiReportRepository.findAll();  	
				model.addAttribute("Listreports",reports); 
				

		 				return "app/SpikeDog/analytics";
			   }
		    

			@RequestMapping(value="/SpikeDogQuickQueryChain-filemanager",method = RequestMethod.GET)
			public String SpikeDogQuickQueryChain(){
				return  "app/SpikeDog/QuickQueryChain2";
			}   
			


			// ==========================================
			// 1. หน้าเพิ่ม Action Plan
			// ==========================================
			@GetMapping(value="/addSpikeDogActionplan-filemanager/{idreport}")
			public String addSpikeDogActionplan(@PathVariable int idreport, ModelMap model){
			    // ❌ ของเดิม: model.addAttribute("idreport","idreport");
			    // ✅ แก้ไข: ต้องส่งค่าตัวแปร ไม่ใช่ String
			    model.addAttribute("idreport", idreport); 
			    
			    // ส่ง Object ว่างไปเผื่อไว้ให้หน้า JSP (จะได้ใช้ร่วมกับหน้า Edit ได้โดยไม่ Error)
			    model.addAttribute("actionplanData", new SpikeDogActionplan()); 
			    return "app/SpikeDog/addSpikeDogActionplan";	
			}  

			// ==========================================
			// 2. บันทึกข้อมูล (รับ POST)
		
			// ==========================================
			@PostMapping(value="/saveSpikeDogActionplan-filemanager")
			public String saveSpikeDogActionplan(SpikeDogActionplan actionplan){
			    actionplanRepository.save(actionplan);
			    
			    // ❌ ของเดิม: actionplanData.get().getIdreport() (ซึ่งไม่มีตัวแปรนี้)
			    // ✅ แก้ไข: ดึง idreport ออกมาจาก object ที่เพิ่งรับ/เซฟ เข้ามาเลย
			    return "redirect:/allSpikeDogActionplan-filemanager/" + actionplan.getIdreport();
			}   

			// ==========================================
			// 3. หน้าแก้ไขข้อมูล
			// ==========================================
			@GetMapping(value="/editSpikeDogActionplan-filemanager/{idreport}/{id}")
			public String editSpikeDogActionplan(@PathVariable int idreport, @PathVariable int id, ModelMap model){
			    // ❌ ของเดิม: ลืมรับ @PathVariable int idreport
			    Optional<SpikeDogActionplan> actionplanData = actionplanRepository.findById(id);
			    
			    if(actionplanData.isPresent()){
			        // ✅ ส่งข้อมูลที่หาเจอไปวาดในหน้าฟอร์ม
			        model.addAttribute("actionplanData", actionplanData.get()); 
			    }
			    model.addAttribute("idreport", idreport);
			    return "app/SpikeDog/addSpikeDogActionplan";
			}   

				// ==========================================
				// 4. ลบข้อมูล
				// ==========================================
				@GetMapping(value="/deleteSpikeDogActionplan-filemanager/{id}")
				public String deleteSpikeDogActionplan(@PathVariable int id){
				    Optional<SpikeDogActionplan> actionplanData = actionplanRepository.findById(id);
				    
				    if(actionplanData.isPresent()){
				        // ✅ ต้องเก็บ idreport ไว้ก่อนลบ เพื่อเอาไปทำ Redirect
				        int idreport = actionplanData.get().getIdreport(); 
				        
				        // ❌ ของเดิม: ActionplanRepository.delete(actionplanData); (ลบ Optional ไม่ได้)
				        // ✅ แก้ไข: ต้อง .get() เพื่อเอาตัว Entity ออกมาให้มันลบ
				        actionplanRepository.delete(actionplanData.get()); 
				        
				        return "redirect:/allSpikeDogActionplan-filemanager/" + idreport;
				    }
				    return "redirect:/"; // กันเหนียวกรณีหาไอดีไม่เจอ
				}   

		
			@GetMapping(value="/allSpikeDogActionplan-filemanager/{idreport}")
			public String allSpikeDogActionplan(@PathVariable int idreport, ModelMap model){
			    // ❌ ของเดิม: ก๊อปชื่อ Method deleteSpikeDogActionplan มาซ้ำ
			    // ✅ แก้ไข: เปลี่ยนชื่อเป็น allSpikeDogActionplan 
			    
			    // ❌ ของเดิม: model.addAttribute("listActionplanData","listActionplanData");
			    // ✅ แก้ไข: ส่ง list จริงๆ เข้าไปไม่ใช่ String
			    List<SpikeDogActionplan> listActionplanData = actionplanRepository.findByIdreportOrderByIdDesc(idreport); 
			    model.addAttribute("listActionplanData", listActionplanData);
			    model.addAttribute("idreport", idreport); // เผื่อเอาไปใช้ทำปุ่มกลับ
			    
			    return "app/SpikeDog/allSpikeDogActionplan";
			}
		    
		    
		    @RequestMapping(value="/addAnalytics-filemanager",method = RequestMethod.GET)
		 			public String addAnalytics(ModelMap model) { 
		    	
		    	
		 		 				return "app/SpikeDog/addAnalytics";
		 			   }
		    
				    @RequestMapping(value="/editAnalytics-filemanager/{id}", method = RequestMethod.GET)
				    public String editAnalytics(@PathVariable("id") Long id, ModelMap model) { 
				        
				        // 1. ค้นหาข้อมูลรายงานเดิมจาก Database
				        Optional<BiReportSetting> reportData = BiReportRepository.findById(id);
				        
				        if(reportData.isPresent()) {
				            // 2. ถ้าเจอข้อมูล ให้ส่งกลับไปที่หน้าจอ 
				            // 🌟 สำคัญ: ต้องตั้งชื่อ attribute ว่า "report" เพราะในหน้า JSP เราเรียกใช้ ${report.reportName} ฯลฯ
				            model.addAttribute("report", reportData.get());
				        }
				        
				        // 3. เรียกใช้หน้าฟอร์มตัวเดียวกันกับตอน Add
				        return "app/SpikeDog/addAnalytics";
				    }
		    
		    @RequestMapping(value="/saveAnalytics-filemanager",method = RequestMethod.POST)
		 			public String saveAnalytics(BiReportSetting reportSetting) { 
		    					BiReportSetting savedReport = BiReportRepository.save(reportSetting);
		 		 				return "redirect:/ShowReportanalytics-filemanager/"+savedReport.getId();
		 			   }
		    
	
			 @RequestMapping(value = "/deleteAnalytics-filemanager/{id}", method = RequestMethod.GET)
			 public String deleteAnalytics(@PathVariable("id") Long id) {
			     BiReportRepository.deleteById(id);
			     return "redirect:/analytics-filemanager/9900";
			 }
			    

		    @RequestMapping(value="/ShowReportanalytics-filemanager/{id}",method = RequestMethod.GET)
					public String ShowReportanalytics(ModelMap model,@PathVariable long id) { 	
		    	
		    			Optional<BiReportSetting> reports = BiReportRepository.findById(id);
		    			model.addAttribute("reports",reports); 
		    			
		    			if(reports.isPresent()) {
		    		        model.addAttribute("reports", reports.get()); 
		    		        
		    		        List<BiReportSetting> relatedReports = BiReportRepository.findTop10ByCategoryOrderByIdDesc(reports.get().getCategory());
		    		        model.addAttribute("relatedReports", relatedReports); 
		    		    } 
						List<SpikeDogActionplan> listActionplanData = actionplanRepository.findByIdreportOrderByIdDesc((int)id);
						model.addAttribute("listActionplanData",listActionplanData);
						
					    model.addAttribute("idreport", id); 
		    			
					    
					    
					    
					    
				 				return "app/SpikeDog/ShowReportanalytics";
					   }
				    
		    

		    @RequestMapping(value="/reportapp-filemanager/{id}",method = RequestMethod.GET)
					public String reportapp(ModelMap model,@PathVariable int id
							,@RequestParam(required=false,defaultValue="0") int from
							,@RequestParam(required=false) String from_type
							,@RequestParam(required=false,defaultValue="0") int process_refer
							) { 
				    			
				    			PlatformReport platform = new PlatformReport();
				    		
				    			if(from != 0 && from_type != null) {
					    			platform.setFrom(from);
					    			platform.setFrom_type(from_type);
				
					    	
					    		
				    			}
				    			platform.setApp_processes_id(id);
				    			platform.setProcess_refer(process_refer);

				    			LoadApp   LoadApp =   new LoadApp();	
				    			
				    			platformService.initiate(LoadApp).startAppReport(platform).prepareReport().load();
				    			Map<String, Object> data = platformService.getModelData();
								model.addAllAttributes(data);
				    
					    		return "app/windows/reportapp";
			    		}
		    

		    @ResponseBody
		    @RequestMapping(value="/getdatareport-filemanager/execute",method = RequestMethod.POST, produces = "application/json; charset=UTF-8" )
			public String getdatareport(ModelMap model,@RequestBody String cheeseJsonPayload) { 
		    			
		    	    System.out.println("jsonPayload "+cheeseJsonPayload);
		    	
		    	
		    			PlatformReport platform = new PlatformReport();
						LoadApp   LoadApp =   new LoadApp();	
						platform.setEdata_from(cheeseJsonPayload);
						LoadApp.setPlatformReport(platform);
		    			return platformService.initiate(LoadApp).startAppReport(platform).requireDataJson();
	    		}
		    
		    
		    @ResponseBody
		    @RequestMapping(value="/showdata-drilldown-filemanager", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
		    public String showDrillDownData(ModelMap model,
			            @RequestParam(value = "id_refer") String idRefer,
			            @RequestParam(value = "chain_id") String chainId,
			            @RequestParam(value = "cheeseJsonPayload") String cheeseJsonPayload) { 
	
		    	
		    	
			    System.out.println("jsonPayload "+cheeseJsonPayload);
    			PlatformReport platform = new PlatformReport();
				LoadApp   LoadApp =   new LoadApp();	
				platform.setEdata_from(cheeseJsonPayload);
				platform.setApp_tempId(idRefer+"_"+chainId);
				
				LoadApp.setPlatformReport(platform);
    			return platformService.initiate(LoadApp).startAppReport(platform).requireDataJsondrilldown();
		    	
			   
		    }
		    
		    
		    @RequestMapping(value="/SyncBack-filemanager",method = RequestMethod.GET)
			public String SyncBack(ModelMap model) { 
		    	
				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	List<Segmentation> ListSegmentation = EdataDAO.getallSegmentationByid_addAndNotUse(usersed.getUser_id());
		    	model.addAttribute("ListSegmentation",ListSegmentation); 
		    			return  "app/JerryMouseBI/SyncBack";
	    		}
		
		    
		    @RequestMapping(value="/SyncBack2-filemanager/{segmentation_id}/{process_id}",method = RequestMethod.GET)
					public String SyncBack2(ModelMap model,@PathVariable int segmentation_id,@PathVariable int process_id) { 
				    	
						user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				    	List<Segmentation> ListSegmentation = EdataDAO.getallSegmentationByid_addAndNotUse(usersed.getUser_id());
				    	model.addAttribute("ListSegmentation",ListSegmentation); 
				    	
				      	model.addAttribute("segmentation_id",segmentation_id); 
				      	model.addAttribute("process_id",process_id); 
				    			return  "app/JerryMouseBI/SyncBack2";
			    		}
				
		    
		    
		    
		    @RequestMapping(value="/saveSyncBack-filemanager",method = RequestMethod.POST)
				public String saveSyncBack(Segmentation segmentation) { 
				    	
		    			PlatformReport platform = new PlatformReport();
				    	platform.setSegmentation(segmentation);
						LoadApp   LoadApp =   new LoadApp();	
			   			platformService.initiate(LoadApp).startAppReport(platform).SyncBackSegmentation();
			    			return  "ajax/save";
		    		}
			    
		    @RequestMapping(value="/copySyncBack-filemanager/{source_id}/{process_refer}",method = RequestMethod.GET)
			public String copySyncBack(@PathVariable int source_id,@PathVariable int process_refer) { 
			    	
		    			Segmentation segmentation = new Segmentation();
		    			segmentation.setId(source_id);
		    			PlatformReport platform = new PlatformReport();
				    	platform.setSegmentation(segmentation);
				    	LoadApp   LoadApp =   new LoadApp();	
			   			platformService.initiate(LoadApp).startAppReport(platform).copySyncBackSegmentation();
			 			return  "redirect:/segmentation-filemanager/"+process_refer;
	    		}
		    
		    
		    

		    @RequestMapping(value="/unSyncBack-filemanager/{source_id}/{process_refer}",method = RequestMethod.GET)
				public String unSyncBack(@PathVariable int source_id,@PathVariable int process_refer) { 
				    	
			    			Segmentation segmentation = new Segmentation();
			    			segmentation.setId(source_id);
			    			PlatformReport platform = new PlatformReport();
					    	platform.setSegmentation(segmentation);
					    	LoadApp   LoadApp =   new LoadApp();	
				   			platformService.initiate(LoadApp).startAppReport(platform).unSyncBackSegmentation();
			    			return  "redirect:/editstaticsnapshot-filemanager/"+source_id+"/"+process_refer;
		    		}
			    
		 
	
		    @RequestMapping(value="/setupFilterChain-filemanager/{id}/{process_refer}",method = RequestMethod.GET)
			public String setupFilterChain(ModelMap model,@PathVariable int id,@PathVariable int process_refer
					) { 			
				
    			PlatformReport platform = new PlatformReport();
    			platform.setApp_id_refer(process_refer);
    			platform.setProcess_refer(process_refer);
    			platform.setApp_item_id(id);
    			
		    	LoadApp   LoadApp =   new LoadApp();	
    			
    				platformService.initiate(LoadApp).startAppReport(platform).requireQueryChain().load();
    				Map<String, Object> data = platformService.getModelData();
					model.addAllAttributes(data);
					
    				return "app/windows/report/FilterChain";
		    	}
		    
		    
		    
		    

		    @RequestMapping(value="/setproperty-filemanager",method = RequestMethod.GET)
			public String setproperty() { 			

    				return "app/JerryMouseBI/setproperty";
		    	}
		    
		    
		    
		    @RequestMapping(value="/setPropertyReport-filemanager",method = RequestMethod.POST)
					public String setPropertyReport(ModelMap model,PrepareReport PrepareReport
							) { 			
		    				ReportDAO.updatePropertyReport(PrepareReport);
		    				return "redirect:/setproperty-filemanager/"+PrepareReport.getId()+"/"+PrepareReport.getProcess_id();
				    	}
		    
		    @RequestMapping(value="/saveSetupFilterChain-filemanager/{id}/{config_id}/{process_refer}",method = RequestMethod.GET)
					public Object saveSetupFilterChain(ModelMap model,@PathVariable int id,@PathVariable int process_refer,@PathVariable int config_id
							) { 			
						
			    			PlatformReport platform = new PlatformReport();
			    			platform.setApp_id_refer(process_refer);
			    			platform.setProcess_refer(process_refer);
			    			platform.setApp_item_id(id);
			    			
			    			LoadApp   LoadApp =   new LoadApp();	
			    			
			    			platformService.initiate(LoadApp).startAppReport(platform).setActiveQueryChain();
		    				return "ajax/save";
				    	}
		    
		    
		    @ResponseBody
		    @RequestMapping(value="/reportjsonapp-filemanager/{id}",method = RequestMethod.GET)
			public Object reportjsonapp(ModelMap model,@PathVariable int id
					) { 			
		    	       List<Calendar> Calendar = Upload_pnDAO.getAllCalendarByIndexId(id);
		    			return scheduleService.createCalendarDataJson(Calendar);
	    	}
		    
		    @ResponseBody
		    @RequestMapping(value="/Calendarjsonapp-filemanager",method = RequestMethod.GET)
			public Object Calendarjsonapp(ModelMap mode
					) { 			
		    	
		    			user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	       List<Calendar> Calendar = Upload_pnDAO.getAllCalendarBynames(usersed.getUser_name());
		    			return scheduleService.createCalendarDataJson(Calendar);
	    	}
		    
		    
		    
		    @GetMapping("/apppop-filemanager/{id}")
		    @ResponseBody
		    public ResponseEntity<Map<String, String>> getFileData(@PathVariable int id) {
		        
		        Edata_maindata maindata = EdataDAO.getEdatamainData(id);
		        
		        
		        
		        // กรณีที่ไม่พบข้อมูลในระบบ ให้ส่งสถานะ 404 Not Found กลับไป
		        if (maindata == null) {
		        	System.err.println(" Edata_maindata null");
		            return ResponseEntity.notFound().build(); 
		        }
		        // กรณีพบข้อมูล ให้สร้าง Map และส่งคืนค่า
		        edata data = EdataDAO.getbyid(id);
		        System.err.println(" id  "+id);
		    	System.err.println(" getProcessId  "+data.getProcesses_id());

		        
		        process process = EdataDAO.getProcess(data.getProcesses_id());
		        
		        Map<String, String> responseData = new HashMap<>();
		        responseData.put("data-id", String.valueOf(process.getId()));
		        responseData.put("data-name", process.getName());
		        responseData.put("data-link", "/editdocumentapp-filemanager/" + id+"/");
		        responseData.put("data-process_refer", "1E");
		        responseData.put("data-logo", "/resources/app-assets/images/icons/APP/" + process.getProcess_logo());
		        
		        // ส่งสถานะ 200 OK พร้อมข้อมูล JSON
		        return ResponseEntity.ok(responseData);
		    }
		    
		    
		    
		    @ResponseBody
		    @RequestMapping(value="/CalendarAlljsonapp-filemanager",method = RequestMethod.GET)
			public Object CalendarAlljsonapp(ModelMap mode
					) { 			

		    	       List<Calendar> Calendar = Upload_pnDAO.getAllCalendar();
		    			return scheduleService.createCalendarDataJson(Calendar);
	    	}
		    
		    

		    
		    
		    @RequestMapping(value="/checkCalendar-filemanager/{id}",method = RequestMethod.GET)
				public String checkCalendar(ModelMap mode,@PathVariable int id
						) { 			
		    				Calendar Calendar = Upload_pnDAO.getCalendar(id);
		    				if(Calendar.getIndex_id() > 0) {
		    					EdataDAO.setEx20("-",Calendar.getIndex_id() );
		    				}
		    				Upload_pnDAO.updateCalendarStatus(id);
			    			return "ajax/save";
		    	}
			    
		
		    
		    
		    
		    @ResponseBody
		    @RequestMapping(value="/reportjsonappAll-filemanager",method = RequestMethod.GET)
			public Object reportjsonappAll(ModelMap model
					) { 			
		    	       List<Calendar> Calendar = Upload_pnDAO.getAllCalendarIndex();
		    			return scheduleService.createCalendarDataJson(Calendar);
	    	}
		    
		    
		    @RequestMapping(value="/suballtask-filemanager/{id}",method = RequestMethod.GET)    
		    public String listupload_pn2(ModelMap model, @PathVariable  int id){ 
		    	
		    	
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	upload_pn upload_pns = Upload_pnDAO.getbyId(id);
		    	model.addAttribute("upload_pns",upload_pns); 
				
			
		    	jobview jobviews = null;
				if(Upload_pnDAO.isjobviewExists(usersed.getUser_name())) {
					 jobviews = Upload_pnDAO.getView(usersed.getUser_name());
				}else {
					jobview jobview = new jobview();
					jobview.setJobview_tab(0);
					jobview.setJobview_user_name(usersed.getUser_name());
					Upload_pnDAO.saveview(jobview);
					
					jobviews = Upload_pnDAO.getView(usersed.getUser_name());
				}
			
			
				List<showupload_pn> upload_pn = Upload_pnDAO.getallbymonitor1((int)upload_pns.getUpload_pn_monitor(),jobviews);
				model.addAttribute("upload_pn",upload_pn); 
		    	
				
				model.addAttribute("Member_name",usersed.getUser_name());
				model.addAttribute("Member_id",usersed.getUser_id());
				MainServices.addNotificationAttributes(model);
		    	return "/ajax/suballtask";
			}
		    
		    
		    
		    @RequestMapping(value="/CustomizeTableHeaders-filemanager/{processes_id}",method = RequestMethod.GET)    
		    public String CustomizeTableHeaders(ModelMap model, @PathVariable  int processes_id){ 
		    	
		    	List<CustomizeTableHeaders>  TableHeaders = customizeTableHeadersRepository.findAll();
		    	
		    	// 👇--- ก๊อปปี้ 4 บรรทัดนี้ไปวางเพื่อทดสอบดูใน Console ---👇
		        if (!TableHeaders.isEmpty()) {
		            System.out.println("====== ทดสอบดึงข้อมูลแถวแรก ======");
		            System.out.println("ID: " + TableHeaders.get(0).getId());
		            System.out.println("Column Name: " + TableHeaders.get(0).getColumnName());
		            System.out.println("Customize Name: " + TableHeaders.get(0).getCustomizeName());
		        }
		        // 👆------------------------------------------------👆
		    	
		    	model.addAttribute("Tab",TableHeaders);
		  	  return  "app/windows/indexset";
		    }
		    
		    
		    @RequestMapping(value="/AutomatedGrading-filemanager/{processes_id}",method = RequestMethod.GET)    
		    public String AutomatedGrading(ModelMap model, @PathVariable  int processes_id){ 
		    	
		    	process process = EdataDAO.getProcess(processes_id);
		  	  model.addAttribute("process",process);
		    	
		     	List<CustomizeTableHeaders>  TableHeaders = customizeTableHeadersRepository.findAll();
		      	model.addAttribute("Tab",TableHeaders);
		      	
		  	  List<UseExtension> extension = EdataDAO.getallUseExtension(processes_id);
			  model.addAttribute("extension",extension);
		      	
		        dataset dataset = DatasetDAO.getMain("Workflow_status");
		        List<dataset_data> WorkflowType = Dataset_dataDAO.getall(dataset.getDataset_id());
		        model.addAttribute("Workflow_status", WorkflowType);
			  
			  
		  	  return  "app/windows/AutomatedGrading";
		    }
		    
		    @RequestMapping(value="/updateAutoGrade-filemanager",method = RequestMethod.POST)    
		    public String updateAutoGrade(process process){ 
		    	EdataDAO.setAutomatedGrading(process);
		  	  return  "/ajax/save";
		    }
		    
		    
		    @PostMapping("/updateCustomizeTableHeaders-filemanager")
		    public ResponseEntity<?> updateHeaders(@RequestBody List<CustomizeTableHeaders> postData) {
		        
		        // วนลูปเพื่ออัปเดตข้อมูลทีละรายการ
		        for (CustomizeTableHeaders incomingData : postData) {
		            
		            // ค้นหาข้อมูลเดิมจาก Database ด้วย ID
		            Optional<CustomizeTableHeaders> existingOpt = customizeTableHeadersRepository.findById(incomingData.getId());
		            
		            if (existingOpt.isPresent()) {
		                CustomizeTableHeaders existing = existingOpt.get();
		                
		                // อัปเดตเฉพาะช่อง customizeName ตามที่ผู้ใช้กรอกมา
		                existing.setCustomizeName(incomingData.getCustomizeName());
		                
		                // type และ columnName ไม่ต้อง set ใหม่ เพราะใช้ค่าเดิมเสมอ
		                
		                // บันทึกลง Database
		                customizeTableHeadersRepository.save(existing);
		            }
		        }

		        return ResponseEntity.ok(Map.of("status", "success", "message", "อัปเดตข้อมูลเรียบร้อยแล้ว"));
		    }
		
		    
		    

		    @RequestMapping(value="/ShortcutCl-filemanager/{data_id}/{processes_id}/{clcode}/{thistab}/{nextTab}",method = RequestMethod.GET)    
		    public String ShortcutCl(ModelMap model, @PathVariable  int data_id, @PathVariable  int processes_id, @PathVariable  int clcode, @PathVariable  int thistab, @PathVariable  int nextTab){ 
		    	
		    	
		       	Platform platform = new Platform();
		    	platform.setApp_processes_id(processes_id);
		    	platform.setApp_item_id(clcode);
		    
		    	
		    	platform.setTab_id(thistab);
		    	LoadApp   LoadApp =   new LoadApp();	
		    	platformService.initiate(LoadApp).startAppIndex(platform).shortCutCL().load();
		    	Map<String, Object> data = platformService.getModelData();
				model.addAllAttributes(data);
				model.addAttribute("data_id",data_id);
				model.addAttribute("clcode",clcode);
				
				process next = EdataDAO.getProcess(nextTab);
				model.addAttribute("nextTab",next);
				
				
		      	return "/app/ShortcutCl";
		    }
		    
		    
		    
		    
		    @RequestMapping(value="/ShortcutClOnerow-filemanager/{data_id}/{processes_id}/{clcode}/{thistab}/{id}/{nextTab}",method = RequestMethod.GET)    
		    public String ShortcutClOnerow(ModelMap model
		    		, @PathVariable  int data_id
		    		, @PathVariable  int processes_id
		    		, @PathVariable  int clcode
		    		, @PathVariable  int thistab
		    		, @PathVariable  int id
		    		, @PathVariable  int nextTab
		    		
		    		
		    		){ 

		       	Platform platform = new Platform();
		    	platform.setApp_processes_id(processes_id);
		    	platform.setApp_item_id(clcode);
		    	platform.setApp_item_id2( id);
		    	platform.setTab_id(thistab);
		    	LoadApp   LoadApp =   new LoadApp();	
		    	platformService.initiate(LoadApp).startAppIndex(platform).shortCutCL().load();
		    	Map<String, Object> data = platformService.getModelData();
				model.addAllAttributes(data);
				model.addAttribute("data_id",data_id);
				model.addAttribute("clcode",clcode);
				
				
				process next = EdataDAO.getProcess(nextTab);
				model.addAttribute("nextTab",next);
				
		      	return "/app/ShortcutClOnerow";
		    }
		    
		    
		    @RequestMapping(value="/pn-filemanager",method = RequestMethod.GET)    
		    public String listupload_pn(ModelMap model,@RequestParam(value = "group",required=false) int group){ 
		    	
		    
				    	List<upload_pn> upload_pns = Upload_pnDAO.getallByActivate();
				    	upload_pns.stream().forEach(row->{
				    	    	System.out.println(" row "+row.getUpload_pn_id());
				    	    	report_pn report_pn = Report_pnDAO.StatusCount(row.getUpload_pn_id());
				    	    
					    	    	if(report_pn != null) {
						    	    	row.setUpload_pn_send(report_pn.getReport_pn_appstatus());
						    	    	row.setUpload_pn_unsend(report_pn.getReport_pn_target() - report_pn.getReport_pn_appstatus());    	
						    	    	
						    	    	
						    	    	row.setUpload_pn_late( report_pn.getReport_pn_appstatus() - report_pn.getReport_pn_statussccess());
						    	    	row.setUpload_pn_ontime(report_pn.getReport_pn_statussccess());
						    	    	
						    	    	
						    	    	System.out.println(" appstatus "+report_pn.getReport_pn_appstatus());
						    	    	System.out.println(" statussccess "+report_pn.getReport_pn_statussccess());
						    	    	
					    	    	}
				    	});
				    	
				    	
				    	Upload_pnDAO.updatebatch(upload_pns);
		    	
				    	
				    	upload_pn upload_pn_date = Upload_pnDAO.StatusCount(0,0);
				    	model.addAttribute("upload_pn_date",upload_pn_date);
				    	
				    	upload_pn upload_pn_week = Upload_pnDAO.StatusCount(0,1);
				    	model.addAttribute("upload_pn_week",upload_pn_week);
				    	
				    	upload_pn upload_pn_month = Upload_pnDAO.StatusCount(0,2);
				    	model.addAttribute("upload_pn_month",upload_pn_month);
				    	
				    	
				    	account_ledger account_ledger = new account_ledger();
				    	account_ledger.setAl_account_code("test");
				    	account_ledgerRepository.save(account_ledger);
				    	
				    	
				    	MainServices.addNotificationAttributes(model);   	
		    	return "filepn";
		    }
		    
		    
		    
		   
		    
		    @RequestMapping(value="/project-filemanager",method = RequestMethod.GET)    
		    public String listproject(ModelMap model){ 
		    	
		    	
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	String accessList = repositoryService.accessList(usersed);
		    	List<upload_pn> project = Upload_pnDAO.getallbyProject(accessList,usersed.getUser_id());
		    	
		    	
		    	project.stream().forEach(row->{
		    		int unsend = TaskDAO.getnum(row.getUpload_pn_id(),1,usersed);
		    		int send = TaskDAO.getnum(row.getUpload_pn_id(),2,usersed);
		    		row.setUpload_pn_unsend(unsend);
		    		row.setUpload_pn_send(send);
		    		
		    		
		    		int unsendnow = TaskDAO.getnumnow(row.getUpload_pn_id(),1,usersed);
		    		int sendnow = TaskDAO.getnumnow(row.getUpload_pn_id(),2,usersed);
		    		row.setUpload_pn_unsendnow(unsendnow);
		    		row.setUpload_pn_sendnow(sendnow);
		    		
		    		task start = TaskDAO.getStart(row.getUpload_pn_id());
		    		task  end = TaskDAO.getEnd(row.getUpload_pn_id());
		    		
		       		if(start != null) {row.setUpload_pn_date(start.getTask_datestart());}
		       		if(end != null) {row.setUpload_pn_dateend(end.getTask_dateend());}
			    	
		    		
		    	});
		    	
				model.addAttribute("project",project);
				MainServices.addNotificationAttributes(model);   	
		    	return "project";
		    }
		 
		    
		    @RequestMapping(value="/projects-filemanager",method = RequestMethod.GET)    
		    public String listprojects(ModelMap model){ 
		    	
		    	
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	
		    
		    	
		 
		    
		    	List<upload_pn> project = Upload_pnDAO.getallbyProject(usersed.getUser_name());
		    	
		    	project.stream().forEach(row->{
		    		int unsend = TaskDAO.getnum(row.getUpload_pn_id(),1);
		    		int send = TaskDAO.getnum(row.getUpload_pn_id(),2);
		    		row.setUpload_pn_unsend(unsend);
		    		row.setUpload_pn_send(send);
		    		
		    		
		    		int unsendnow = TaskDAO.getnumnow(row.getUpload_pn_id(),1);
		    		int sendnow = TaskDAO.getnumnow(row.getUpload_pn_id(),2);
		    		row.setUpload_pn_unsendnow(unsendnow);
		    		row.setUpload_pn_sendnow(sendnow);
		    		task start = TaskDAO.getStart(row.getUpload_pn_id());
		    		task  end = TaskDAO.getEnd(row.getUpload_pn_id());
		    		
		       		if(start != null) {row.setUpload_pn_date(start.getTask_datestart());}
		       		if(end != null) {row.setUpload_pn_dateend(end.getTask_dateend());}
			    	
		    		
		    	});
		    	
				model.addAttribute("project",project);
				MainServices.addNotificationAttributes(model);   	
		    	return "projects";
		    }

		    
		    
		    @RequestMapping(value="/projectslog-filemanager",method = RequestMethod.GET)    
		    public String projectslog(ModelMap model){ 
		    	
		    	
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	
		    
		    	
		 
		    
		    	List<upload_pn> project = Upload_pnDAO.getallbyProjects(usersed.getUser_name());
		    	
		    	project.stream().forEach(row->{
		    		int unsend = TaskDAO.getnum(row.getUpload_pn_id(),1);
		    		int send = TaskDAO.getnum(row.getUpload_pn_id(),2);
		    		row.setUpload_pn_unsend(unsend);
		    		row.setUpload_pn_send(send);
		    		
		    		
		    		int unsendnow = TaskDAO.getnumnow(row.getUpload_pn_id(),1);
		    		int sendnow = TaskDAO.getnumnow(row.getUpload_pn_id(),2);
		    		row.setUpload_pn_unsendnow(unsendnow);
		    		row.setUpload_pn_sendnow(sendnow);
		    		task start = TaskDAO.getStart(row.getUpload_pn_id());
		    		task  end = TaskDAO.getEnd(row.getUpload_pn_id());
		    		
		       		if(start != null) {row.setUpload_pn_date(start.getTask_datestart());}
		       		if(end != null) {row.setUpload_pn_dateend(end.getTask_dateend());}
			    	
		    		
		    	});
		    	
				model.addAttribute("project",project);
				MainServices.addNotificationAttributes(model);   	
		    	return "projects";
		    }
		    
		    
		    
		    
		    
		    
		    @RequestMapping(value="/listpn-filemanager",method = RequestMethod.GET)    
		    public String list_file_upload_pn(ModelMap model,@RequestParam int due,@RequestParam int type){ 
		    	
		    
   
		    	List<upload_pn> upload_pns = Upload_pnDAO.getByDue_Type(due, type);
				model.addAttribute("upload_pns",upload_pns);	
				MainServices.addNotificationAttributes(model);
		    	return "list_filepn";
		    }
		    
		    
		    
		    @RequestMapping(value="/detailspn-filemanager",method = RequestMethod.GET)    
		    public String detailspn(ModelMap model,@RequestParam int id,@RequestParam(value = "OFFSET",required=false,defaultValue="1") int OFFSET){ 
		    	
		    	upload_pn upload_pns = Upload_pnDAO.getbyId(id);
		    	model.addAttribute("upload_pns",upload_pns);
		    	
		    	List<report_pn> report_pns = Report_pnDAO.getPage(id,OFFSET);
		    	List<report_pn> totalPages = Report_pnDAO.getall2(id);
				model.addAttribute("report_pn",report_pns);
				
				
			    model.addAttribute("reports",report_pns);
			    model.addAttribute("totalPages", totalPages.size());
			    model.addAttribute("OFFSET",OFFSET);
			    model.addAttribute("id", id); // ส่ง ID กลับไปด้วย
			    
			    MainServices.addNotificationAttributes(model);
		    	return "list_report_pn";
		    }
		   
		    
		    
		    
		    
		    
		    @RequestMapping(value="/details_pn_due-filemanager",method = RequestMethod.GET)    
		    public String detailspn(ModelMap model,@RequestParam String type,@RequestParam(value = "OFFSET",required=false,defaultValue="1") int OFFSET){ 
		    	
		   	 LocalDateTime localDate = LocalDateTime.now().minusDays(10);
		   	 LocalDateTime localDate2 = LocalDateTime.now();
		   	 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			 String date_start = localDate.format(formatter);
			 String date_end = localDate2.format(formatter);

	    	
		    	
		    List<report_pn> report_pns = Report_pnDAO.getPageWeeK(date_start,date_end,OFFSET);
		    List<report_pn> totalPages = Report_pnDAO.getallreportbydate(date_start,date_end);
			model.addAttribute("report_pn",report_pns);
		    	
				
			model.addAttribute("reports",report_pns);
			model.addAttribute("totalPages", totalPages.size());
			model.addAttribute("OFFSET",OFFSET);
			model.addAttribute("type", type); // ส่ง ID กลับไปด้วย
			    
			MainServices.addNotificationAttributes(model);
		    	return "list_report_pn2";
		    }
		   
		    
		    
		    
		    
		    @RequestMapping(value="/details_pn_due2-filemanager",method = RequestMethod.GET)    
		    public String detailspn2(ModelMap model,@RequestParam String type,@RequestParam(value = "OFFSET",required=false,defaultValue="1") int OFFSET){ 
		    	
		   	 LocalDateTime localDate = LocalDateTime.now().minusDays(10);
		   	 LocalDateTime localDate2 = LocalDateTime.now();
		   	 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			 String date_start = localDate.format(formatter);
			 String date_end = localDate2.format(formatter);

	    	
		    	
		    List<report_pn> report_pns = Report_pnDAO.getPageWeeK(date_start,date_end,OFFSET);
		    List<report_pn> totalPages = Report_pnDAO.getallreportbydate(date_start,date_end);
			model.addAttribute("report_pn",report_pns);
		    	
				
			model.addAttribute("reports",report_pns);
			model.addAttribute("totalPages", totalPages.size());
			model.addAttribute("OFFSET",OFFSET);
			model.addAttribute("type", type); // ส่ง ID กลับไปด้วย
			    
			MainServices.addNotificationAttributes(model);
		    	return "list_report_pns";
		    }
		   
		    
		    
		    
		   
		    
		    @RequestMapping(value="/pn-filemanager/{id}",method = RequestMethod.GET)    
		    public String show_upload_pn(ModelMap model,@PathVariable(value = "id",required=false) int id){ 
		    	
		    	return "filepn3";
		    }
		    
		    
		    
		    public void SetApp(app app,ModelMap model) throws ParseException {
		    			  
				 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("HH:mm:ss");
				 String time = localDate2.format(formatter5);
				 String date = localDate.format(formatter);
			   	 String datetime = localDate2.format(formatter3);
		    	
		    	
				      report_pn report_pns = Report_pnDAO.getbyId2(app.getUpload_pn_id(),app.getLog().getLog_due());
				     if(app.getDue() > 0) {
						   
						   DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
							LocalDateTime todaylast = LocalDateTime.now().minusDays(1);
							LocalDateTime today = LocalDateTime.now();
							String datelast = todaylast.format(formatter2);
							String dated = today.format(formatter2);
							LogDAO.setapp(report_pns.getReport_pn_idrefer());
						if(report_pns != null) {	
					
							if(!dated.equals(report_pns.getReport_pn_date())) {
								
								if(!datelast.equals(report_pns.getReport_pn_date())) {
									
									
									SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
								    Date date1 = myFormat.parse(datelast);
								    Date date2 = myFormat.parse(report_pns.getReport_pn_date());
									int diff = Dateservice.getDiffDays(date1,date2);
									
							 	   Report_pnDAO.setappstatus(report_pns.getReport_pn_id(),diff,date);	
							 	    }else {
							 	    	 Report_pnDAO.setStatus(report_pns.getReport_pn_id(),3,date);
							 	    }
								
								
							 }else {
								 Report_pnDAO.setStatus(report_pns.getReport_pn_id(), 3,date);
							 }
		                    
						}	

						
				   }else{
						if(report_pns != null) {	
				    	 Report_pnDAO.setStatus(report_pns.getReport_pn_id(),3);
						}
				   }
				   
					model.addAttribute("log_id",app.getLog().getLog_id());
					
				    if(app.getLog().getLog_file() != null ) {
				    	
				 	  
				    	model.addAttribute("logsave",app.getLog());
				    }

				    if(!app.getNote().isBlank()) {
					    cm cms = new cm(); 
					    cms.setCm_date(date);
					    cms.setCm_idadd(app.getUser_id());
					    cms.setCm_idrefer(app.getUpload_pn_id());
					    cms.setCm_title(app.getNote());
					    CmDAO.save(cms); 
				    }
				    
		    }
		    

		    
		    public void EdataProcess(process process,ModelMap model)  {
  			  

		    	 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("HH:mm:ss");
				 String time = localDate2.format(formatter5);
				 String date = localDate.format(formatter);
			   	 String datetime = localDate2.format(formatter3);
		
		    	eformfield balance_before = EformfieldDAO.getByField_cal_type(process.getEdata().getData_eformid(),process.getEdata().getData_eformrv(),"balance_before");
		    	eformfield balance_after = EformfieldDAO.getByField_cal_type(process.getEdata().getData_eformid(),process.getEdata().getData_eformrv(),"balance_after");
		    	
		    	eformfield defective_production_output = EformfieldDAO.getByField_cal_type(process.getEdata().getData_eformid(),process.getEdata().getData_eformrv(),"defective_production_output");
		    	
		  
		    
		   
		    	edata edatanot = process.getEdatanot();
		    	log logs = process.getLog();
		    	int result= 1;
				if(process.getEdata().getData_cal_type() != null) {
					
			
					
					int cal_condition = process.getEdata().getData_cal_conditionid();
					edata edatamain =  EdataDAO.getbyid(cal_condition);

				  	eformfield order = EformfieldDAO.getByField_cal_type(edatamain.getData_eformid(),edatamain.getData_eformrv(),"order");
			    	eformfield due = EformfieldDAO.getByField_cal_type(edatamain.getData_eformid(),edatamain.getData_eformrv(),"due");
			    	eformfield balance = EformfieldDAO.getByField_cal_type(edatamain.getData_eformid(),edatamain.getData_eformrv(),"balance");
			    	
			    	
				 	eformfield total_defective_production = EformfieldDAO.getByField_cal_type(edatamain.getData_eformid(),edatamain.getData_eformrv(),"total_defective_production");
				 
					
					
			    	
					
					edata Statement = EdataDAO.getbyid(cal_condition);
					
					
					Statement.setId_refer(Statement.getData_id());
		
					Statement.setData_dateadd(datetime);
		
					EdataDAO.savelog(Statement);
					
					long cal_no = balance.getField_postion();
					
							if(process.getEdata().getData_cal_type().equals("update")) {
							
								long cal_no2 =process.getEdata().getData_cal_no2();
								String cal_value = process.getEdata().getData_cal();
								
						
								if(cal_no > 0 ){EdataDAO.setcal_update(cal_value,cal_no,cal_condition);}
								if(cal_no2 > 0 ){EdataDAO.setcal_update(cal_value,cal_no2,cal_condition);}
								
							}
							
							if(process.getEdata().getData_cal_type().equals("plus")) {
								
							
								
								
								
								long cal_no2 = process.getEdata().getData_cal_no2();
								
								String cal_value = process.getEdata().getData_cal();
						
								
								
							
								
								edata realtotal = EdataDAO.getTotal(cal_no,cal_condition);
								//plus data
								if(cal_no > 0 ){ 
									EdataDAO.setcal_plus(cal_value,cal_no,cal_condition);  
									}
								if(cal_no2 > 0  && cal_no2 != cal_no  ){ 
									
									EdataDAO.setcal_plus(cal_value,cal_no2,cal_condition);  
									}
								//plus data
							
								
								
								if(total_defective_production != null && defective_production_output != null) {
									
									System.out.println("----- eformid------" +edatamain.getData_eformid());
									System.out.println("----- eformrv------"+edatamain.getData_eformrv());
									System.out.println("----- postion------"+defective_production_output.getField_postion());
									
							
									
									edata defective_production_outputs = EdataDAO.getSum(process.getEdata().getData_eformid(),process.getEdata().getData_eformrv(),defective_production_output.getField_postion());
									System.out.println("----- total_defective_production------" +defective_production_outputs.getData_value1());
									System.out.println("----- total_defective_production------" +total_defective_production.getField_postion());
							
									
									EdataDAO.setTotal(datetime,defective_production_outputs.getData_value1(),total_defective_production.getField_postion(),cal_condition);
									
									
									System.out.println("----- setTotal------");
									
								}
								
								
								
						
									if(balance_before != null) {
										System.out.println("----- balance_before------");
										edatanot = eformservices.SetDataPosition(edatanot,realtotal.getData_value1(), balance_before.getField_postion());
									}
									
									if(balance_after != null) {
										System.out.println("-----balance_after-----");
										
										double total_before  = Double.valueOf( realtotal.getData_value1());
										double with  = Double.valueOf(cal_value);
										String total_after = String.valueOf( total_before +  with);
										
										edatanot = eformservices.SetDataPosition(edatanot,total_after, balance_after.getField_postion());
										edatanot.setLog(edatanot.toString());
									}
								
								
								
							
								
								
								
							}
							
							
							
							
							if(process.getEdata().getData_cal_type().equals("minus")) {
								
						
								long cal_no2 = process.getEdata().getData_cal_no2();
								String cal_value = process.getEdata().getData_cal();
								
						

								//minus data
								edata realtotal = EdataDAO.getTotal(cal_no,cal_condition);
								
								
								if(cal_no > 0 ){	
									
									result = EdataDAO.setcal_minus(cal_value,cal_no,cal_condition);
									System.out.println("result " + result);
								}
								if(cal_no2 > 0 && cal_no2 != cal_no ){		
									
									result = EdataDAO.setcal_minus(cal_value,cal_no2,cal_condition);
									System.out.println("result " + result);
								}
								//minus data
								
								
								if(total_defective_production != null && defective_production_output != null) {
									edata defective_production_outputs = EdataDAO.getSum(process.getEdata().getData_eformid(),process.getEdata().getData_eformrv(),defective_production_output.getField_postion());
								
									EdataDAO.setTotal(datetime,defective_production_outputs.getData_value1(),total_defective_production.getField_postion(),cal_condition);
									
								}
								
								
								
								
								if(balance_before != null) {
									edatanot = eformservices.SetDataPosition(edatanot,realtotal.getData_value1(), balance_before.getField_postion());
								}
								
								if(balance_after != null) {
								
									double total_before  = Double.valueOf( realtotal.getData_value1());
									double with  = Double.valueOf(cal_value);
									String total_after = String.valueOf( total_before -  with);
									edatanot = eformservices.SetDataPosition(edatanot,total_after, balance_after.getField_postion());
									edatanot.setLog(edatanot.toString());
								}

								
						
							}
							
							
							if(due != null && order != null && balance != null ) {
								EdataDAO.setdue(order.getField_postion(),balance.getField_postion(),due.getField_postion(),cal_condition);
							}
							
							
							
							
							Statement = EdataDAO.getbyid(cal_condition);
							Statement.setId_refer(Statement.getData_id());
							Statement.setData_dateadd(datetime);
							EdataDAO.savelog(Statement);
							
							
						    	if(edatanot != null) { 
								EdataDAO.update(edatanot);
								}	
							
							
							if(result == 1) {
								
								LogDAO.setflow(process.getId(),1,process.getNote());
								logs =  LogDAO.getbyId(process.getId());
								
							
							}else {
								 model.addAttribute("result", 0);
							}
							 
							 
							
		 		}else {
		 			
		 			           
		 			
		 			
		 			
				 			 LogDAO.setflow(process.getId(),1,process.getNote());
							 logs =  LogDAO.getbyId(process.getId());
						if(edatanot != null) {  EdataDAO.update(edatanot); }	 
							
		 		}	
				
				
				
				
				
				
				if(process.getEdata() != null && process.getUpload_pn().getUpload_pn_send1() > 0 && process.getLogflow() != 1 ) {
					
					upload_pn  upload = Upload_pnDAO.getbyId(process.getUpload_pn().getUpload_pn_send1());
					//Upload_pnDAO.setstate(1, upload_pns.getUpload_pn_send1());
				
					
				
					
			    
				if(process.getEdata() != null && process.getCondition() != null) {
					condition subconditions = ConditionDAO.getbyid(process.getCondition().getCondition_sub());
					
					
					if(subconditions != null) {
						
						
						report_pn  report_pn = null;
						report_pn = Report_pnDAO.getbyId2(upload.getUpload_pn_id(),date);
						if(report_pn == null) {
								 
						
								 
								report_pn report_pned = new report_pn();
								report_pned.setReport_pn_date(date);   
								report_pned.setReport_pn_target(1);
								report_pned.setReport_pn_total(0);
								report_pned.setReport_pn_idrefer(upload.getUpload_pn_id());
								report_pned.setReport_pn_app(upload.getUpload_pn_app());
								report_pned.setReport_pn_department(upload.getUpload_pn_department());
								report_pned.setReport_pn_respon(upload.getUpload_pn_respon());
								report_pned.setReport_pn_status(1);
						   	    Report_pnDAO.save2(report_pned);
						   	    
						   	 report_pn = Report_pnDAO.getbyId2(upload.getUpload_pn_id(),date);
							 } 
							
							
							
							
							
					
						
						
						
						logs.setLog_id_add(0);
						logs.setLog_step("UPLOAD");
						logs.setLog_date(date);
						logs.setLog_due(null);
						logs.setLog_time(time);
						logs.setLog_note(null);
						
						if(upload != null) {
							logs.setLog_refer(String.valueOf(report_pn.getReport_pn_id()));
						}
						
						logs.setLog_folder(null);
						logs.setLog_file(null);
						logs.setLog_dive(null);
						logs.setLog_flow(-2);
						logs.setLog_plan(upload.getUpload_pn_id());
						
						int idlogs = LogDAO.saves(logs);
						
					
						System.out.println("savejoin 1");
						
					
						 
						   
						   
							
						
							 
							 
								edata rowedata =  new edata();
								rowedata.setData_join(process.getEdata().getData_id());
								rowedata.setData_planid(upload.getUpload_pn_id());
								rowedata.setData_dateadd(date);
								rowedata.setData_eformid(subconditions.getCondition_id());
								rowedata.setData_eformrv(subconditions.getCondition_rv());
								rowedata.setData_filepath(String.valueOf(idlogs));
								rowedata.setDar_nameadd(process.getUser().getUser_name());
								rowedata.setReport_id(report_pn.getReport_pn_id());
								EdataDAO.savejoin(rowedata);
								
					
						
						if(report_pn != null) {
								notification  notifications = new notification();
					 			notifications.setNotification_iduser(report_pn.getReport_pn_respon());
								notifications.setNotification_iduser2(null);
								notifications.setNotification_alert("คุณมีเอกสารที่ต้องอัพโหลด -- ชื่อเอกสาร : " + upload.getUpload_pn_note() + " กำหนดอัพโหลด : " +date);
								notifications.setNotification_date(datetime);
								notifications.setNotification_read(0);
								notifications.setNotification_type("Job queue");
								notifications.setNotification_refer(String.valueOf(report_pn.getReport_pn_id()));
								NotificationDAO.save(notifications);
								Report_pnDAO.setalert(report_pn.getReport_pn_id(),1);
						}
						
					}
						
						
					}
			    
					}
			    
			    
			    
				
			    
			
			    
				edata edatas = EdataDAO.getbyfilepath(process.getId());
				 List<file> file  = FileDAO.getallEdata(edatas.getData_id()); 
				 edatas.setFile(file);
				 
				 
				if(edatas != null) {
					   model.addAttribute("edata_id",edatas.getData_id());
					   model.addAttribute("edatasave",edatas);
				} 
			    System.out.println("Run .... .... ... ");
			
				    
		    }
		    
		    
		    
		    
		    
		    @RequestMapping(value="/cleardocument-filemanager/{data_id}/{upload_pn_id}/{transition}",method = RequestMethod.GET)    
		    public String cleardocument(@PathVariable int data_id,@PathVariable int upload_pn_id,@PathVariable String transition	,RedirectAttributes redirectAttributes){ 
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		    	 edata edata = EdataDAO.getbyid(data_id);
		    	 upload_pn upload_pns = Upload_pnDAO.getbyId(upload_pn_id);
		    	 user send = UserDAO.getbyname(upload_pns.getUpload_pn_app());
		    	 
		    	 if(usersed != null) {
		    		 
		    		    datalink key = WorkflowDAO.getDataLinkById(edata.getData_eformid());
		    		    
		    		    Transition Transition_data = new Transition(transition,"-");
		    		    
		    		  
		    		    
		    		    
		    		    int to_clear = Optional.ofNullable(upload_pns.getUpload_pn_clearing())
		    	                .filter(s -> !s.trim().isEmpty()) // เช็คว่าค่าไม่ใช่ค่าว่าง
		    	                .map(Integer::valueOf)
		    	                .orElse(edata.getData_idadd());
		    		    
		    		    if(transition.equals("to_review_cleared")){

		    		    	 try {
				    		    	user user =  UserDAO.getbyname(upload_pns.getUpload_pn_app());
				    		    	Transition_data.setTo(String.valueOf(user.getUser_id()));
				    		    	
		    		        }catch(Exception  e) { }	
		    		    }    
		    		     
		    		  Transition_data.setClear(String.valueOf(to_clear));
		    		  
		    		  
		    		  
		    		//  Filemanagerservice.ApproveApp(edata.getData_id(),key.getDatalink_name(),Transition_data,redirectAttributes);	
		 
		    	 }
		    	
		    	 return "redirect:/app-filemanager/"+edata.getData_eformid();
		    }
		    
		    
		    
		    
		    @RequestMapping(value="/senddocument-filemanager/{data_id}/{process_id}/{sub_processes}/{next}",method = RequestMethod.GET)    
		    public String senddoc(@PathVariable int data_id
		    		,@PathVariable int process_id,
		    		@PathVariable int sub_processes
		    		,@PathVariable String next
		    		,RedirectAttributes redirectAttributes
		    		){ 
		    	
		    	
		    				try {
			       				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			       				Idoc flows =  flowService.getDocument(data_id);
			       				process process = EdataDAO.getProcess(process_id);
			       				List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
						    	process.setSub_processes(sub_process);
			       				
					    		edata edata = EdataDAO.getbyid(data_id);
					    		
					    		
						    	user recipient =  flowService.getRecipient(sub_processes,sub_process,flows.getCreator());
						    	workflow work =  WorkflowDAO.getbyid(process.getId_refer());
						    	 Myapp app = EdataDAO.getMyapp(work.getWorkflow_maindb());
						    	
						    	
	
						    	LoadApp LoadApp = new LoadApp();
						    	LoadApp.setWorkflow(work);
						    	LoadApp.setEdata(edata);
						    	LoadApp.setProcess(process);
						    	LoadApp.setSub_processes(sub_process.get(0));
								LoadApp.setMyapp(app);
								
						   		Transition Transition = new Transition();
								user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
								Transition.setTransition_from(users.getUser_id());
								Transition.setTransition_to(recipient.getUser_id());
								LoadApp.setTransition(Transition);
						    	Filemanagerservice.FlowApp(LoadApp);
	
	
							 	redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());	
						    	 
					 
						    }catch(Exception e) {
				    		redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Sys_Failed.name());	
				    	}
		    	 return "redirect:/app-filemanager/"+process_id;
		    	}
		    
		    
		 
		    
		    
		   

		    @RequestMapping(value="/app-filemanager/{process_id}",method = RequestMethod.GET)    
		    public String apptask(ModelMap model,@PathVariable int process_id
		    		,HttpServletRequest request
		    		,HttpSession session
		    		,@RequestParam(required=false) String boxs
		    		){ 
		

			    process process = EdataDAO.getProcess(process_id);
				model.addAttribute("process",process);
				
				 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				 

		
				Optional<sub_processes> data_step =  sub_process.stream().filter(row-> 
				row.getProcesse_id() == process.getId()).filter(row-> row.getStep().equals("Create")).findFirst();
				 
				 if(data_step.isPresent()) {
					 sub_processes step = data_step.get();
					 policies Policies = Userservice.getPoliciesStep(step.getId());
					 System.out.println(" Policies -->" +Policies.getName()); 
					 
					 System.out.println(" Policies  getProcess-->" +Policies.getId()); 
					 
					 
					 model.addAttribute("Policies_Create",Policies.getProcess());
				 }

				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 LocalDateTime localDate = LocalDateTime.now();//For reference	
				 String date = localDate.format(formatter);
				 model.addAttribute("datenow",date);
				 

				 workflow flow = WorkflowDAO.getbyid(process.getId_refer());
				 model.addAttribute("flow",flow);
			
				 
	
			
				 appInboxService.initiate()
				 .loadProcesses(process_id);
				 
				 model.addAttribute("boxs",boxs); 
		
				 if(boxs == null || boxs == "" ){
					 appInboxService.filterDraft().count();
				 }
				 else if(boxs.equals("in")) {
					 appInboxService.filterInbox().count();
				 }
				 else if(boxs.equals("sends")) {
					 appInboxService.filterSends().count();
				 }
				 else if(boxs.equals("refer")) {
					 appInboxService.filterRefer().count();
				 }
				 else if(boxs.equals("completion")) {
					 appInboxService.filterPublished().count();
				 }
				 else if(boxs.equals("in_refer")) {
					appInboxService.filterIn_refer().count();
				 }

				 
					
			

				 Map<String, Object> data = appInboxService.getModelData();
			     model.addAllAttributes(data);
			  		

				 
		    	return "/app/windows/app";
		    }
		    
		    
		    @RequestMapping(value="/boxtask-filemanager/{process_id}/{box}",method = RequestMethod.GET)    
		    public String boxtask(ModelMap model,@PathVariable int process_id,@PathVariable String box,HttpServletRequest request,HttpSession session){ 
		    	

		       	MainServices.addNotificationAttributes(model);
		       	
		       	
		       	
		      
		       	
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		
	
		    	
		    	
			    process process = EdataDAO.getProcess(process_id);
				model.addAttribute("process",process);
				
				 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				 

				 
				Optional<sub_processes> data_step =  sub_process.stream().filter(row-> 
				row.getProcesse_id() == process.getId()).filter(row-> row.getStep().equals("Create")).findFirst();
				 
				 if(data_step.isPresent()) {
					 sub_processes step = data_step.get();
					 policies Policies = Userservice.getPoliciesStep(step.getId());
					 System.out.println(" Policies -->" +Policies.getName()); 
					 
					 System.out.println(" Policies  getProcess-->" +Policies.getId()); 
					 
					 
					 model.addAttribute("Policies_Create",Policies.getProcess());
				 }

				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 LocalDateTime localDate = LocalDateTime.now();//For reference	
				 String date = localDate.format(formatter);
				 model.addAttribute("datenow",date);
				 

				 workflow flow = WorkflowDAO.getbyid(process.getId_refer());
				 model.addAttribute("flow",flow);
				 
				 
				  
				 appInboxService.filterDraft().count();
				 
				 Map<String, Object> data = appInboxService.getModelData();
			     model.addAllAttributes(data);
			  		

				 
				List<transaction_send> transaction_sends = EdataDAO.getAllTransaction_send(process.getProcess_join(),"Send",Userservice.getPoliciesString());
				 model.addAttribute("transaction_sends",transaction_sends); 
				List<transaction_send> transaction_sends2 = EdataDAO.getAllTransaction_send(process.getId(),"Send",Userservice.getPoliciesString());
				 model.addAttribute("transaction_sends2",transaction_sends2); 
				 List<transaction_send> transaction_receives = EdataDAO.getAllTransaction_send(process.getProcess_join(),"Receive",Userservice.getPoliciesString());
				 model.addAttribute("transaction_receives",transaction_receives); 
				 
				 
				 
				 
				 
				 System.out.println(" usersed "+usersed.getUser_id());
				 
			

				 
		    	return "/app/windows/app";
		    }
		    
		   
		    
		    
		    
		
		    
		    @RequestMapping(value="/inboxrefer-filemanager/{process_id}",method = RequestMethod.GET)
			public String inboxrefer(
					ModelMap model,@PathVariable int process_id
					) {	
		    	

		    	 String Policies = Userservice.getPoliciesString();
				 List<transaction_send> transaction_sends = EdataDAO.getAllTransaction_send(process_id,"SEND",Policies);
				 model.addAttribute("transaction_sends",transaction_sends); 
		    	return "app/sub/inboxrefer";
		    	
		    	
			}
		    
		    
		    

				    
		    
		    
		    
		    

		    @RequestMapping(value="/showdata-filemanager/{id}",method = RequestMethod.GET)    
		    public String showdata(
		    		ModelMap model,
		    		@PathVariable int id,
		    		HttpSession session,
		    		RedirectAttributes redirectAttributes
		    		){ 
		    
		    	 edata edatas = EdataDAO.getbyid(id);
		    	 model.addAttribute("edatas",edatas);  
					
		    	

		    	
		    	int processes_id = edatas.getProcesses_id();
		    	
		    	 process process = EdataDAO.getProcess(processes_id);
				 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				 process.setSub_processes(sub_process);
				 workflow work  = WorkflowDAO.getbyid(process.getId_refer());
			
				 
				 
		  
		    	condition conditions = ConditionDAO.getbyid(process.getId_condition());
		    	model.addAttribute("conditions",conditions);  

		
		    	
		       List<keymap> keymap =  Filemanagerservice.setKeymap(process.getSub_processes());
		 	   LoadApp LoadApp = new LoadApp();
			   LoadApp.setId(0);
			   LoadApp.setWorkflow(work);
			   LoadApp.setSession(session);
			   LoadApp.setProcess(process);
			   LoadApp.setSub_processes(process.getSub_processes().get(process.getSub_processes().size() - 1));
			   LoadApp.setModel(model);
			   LoadApp.setKeymap(keymap);
			   LoadApp.setEdata(edatas);
			   LoadApp.setCondition(conditions);

			   LoadApp.setTempId(String.valueOf(edatas.getData_id()));
			   System.out.println("Up LoadApp->");  
			   
			   List<doc> docs = EdataDAO.getDoc(edatas.getData_id());
			   
			   if(docs.size() > 0) {
				   model.addAttribute("doc",docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()); 
			   }else {
				   model.addAttribute("doc",0); 
			   }
			 
			   
			   
			   
			   Filemanagerservice.LoadAppViewMode(LoadApp);
		    	
			   model.addAttribute("view_type",1); 
			
			   
			   
			   switch (work.getWorkflow_maindb()) {
			      
		        case "Production":
		        	 return "/app/hierarchical_referencedocs";
					
		        default:	
		        	 return "/app/referencedocs";
		      }	
		    	
		    }
		
		
		    @RequestMapping(value="/appRevision-filemanager/{data_id}/{process_id}",method = RequestMethod.GET)    
		    public String appRevision(@PathVariable  int data_id,@PathVariable int process_id) throws Exception {
		    	
		    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 	    
		    		int edata_id = revisionService.executeRevision(data_id, process_id,usersed);
		    		
		    		
		    		return "redirect:/editdocumentapp-filemanager/"+edata_id+"/"+process_id;
		    }
		    		
		    		

		    @RequestMapping(value="/appform-filemanager/{app_processes_id}",method = RequestMethod.GET)    
		    public String appform(
		    		ModelMap model
		    		,@PathVariable int app_processes_id
		    		,HttpSession session
		    		,RedirectAttributes redirectAttributes
		    		,@RequestParam(required=false,name="id_refer") String id_refer
		    		){ 
		    	
		    	
		    	
		    	//platform
		    	Platform platform = new Platform();
		    	platform.setApp_processes_id(app_processes_id);
		    	
		    	LoadApp   LoadApp =   new LoadApp();	
		    	platformService.initiate(LoadApp).loadPlatForm(platform).showFormNewFile();
		    	
		    	
		    	Map<String, Object> data = platformService.getModelData();
				model.addAllAttributes(data);
				model.addAttribute("id_refer",0); 
		
				if(id_refer != null) {
					model.addAttribute("id_refer",id_refer); 
					model.addAttribute("refer_title","เอกสารที่เลือกปัจจุบัน"); 
				}
				
				
		    	 //process process = EdataDAO.getProcess(app_processes_id);
				// model.addAttribute("process",process); 

		    	//condition conditions = ConditionDAO.getbyid(process.getId_condition()); 
			//	model.addAttribute("conditions",conditions); 
		    	
		    	/*
				 List<Links> listLinks = WorkflowDAO.getLinksByidrefer(process.getId());
				Optional<Links> data =  listLinks.stream().filter(row->row.getRelationship() != null).findFirst();
				 if(data.isPresent()) {
					 Links datas =  data.get(); 
					 model.addAttribute("join",datas.getLink_to()); 
				 }
				 */
				 
				 //action  = Profile,product
				 
				 
				 

			
				return "/app/windows/newfile";
			
		    	}
		  
		    
		    
		    
		    
		    
		    @RequestMapping(value="/renewfile-filemanager/{edata_id}",method = RequestMethod.GET)    
		    public String renewfile(ModelMap model,@PathVariable int edata_id){ 	
					    	edata edata = EdataDAO.getbyid(edata_id);
					    	model.addAttribute("edata",edata); 
							return "/app/windows/renewfile";
			
		    	}
		  
		    
		    @RequestMapping(value="/renewfilesave-filemanager",method = RequestMethod.POST)    
		    public String renewfilesave(
		    		@RequestParam String name_file,
		    		@RequestParam int  processes_id,
		    		@RequestParam int id
		    		){ 	
					    	 EdataDAO.renewfile(name_file,id);
				  			return "redirect:/editdocumentapp-filemanager/"+id+"/"+processes_id;
			
		    	}
		  
		    
		    
		    
		    @RequestMapping(value="/newfile-filemanager",method = RequestMethod.POST)    
		    public String newfile(
		    		Platform platform
		    		){ 
		    	
		    	    
		    	System.out.println(" 555 "+platform.getEdata().getData_eformid());
		    	LoadApp   LoadApp =   new LoadApp();	
		    		int	eformId = platformService.initiate(LoadApp).newFile(platform).save();
		    		
		    		System.out.print(" eformId "+eformId);
		    		
		    		if(eformId > 0) {
		    			return "redirect:/editdocumentapp-filemanager/"+eformId+"/"+platform.getApp_processes_id();
		    		}else {
		    			return "redirect:/app-filemanager/"+platform.getApp_processes_id();
		    		}
		    		
			  		
		    	}
		    
		    
		    @RequestMapping(value="/newfile2-filemanager",method = RequestMethod.POST)    
		    public String newfile2(
		    		@RequestParam String name_file,
		    		@RequestParam String customerCode,
		    		@RequestParam int relation_id,
		    		@RequestParam int app_processes_id,
		    		@RequestParam int tab_id
		    		){ 

		    	
		     		Platform platform = new Platform();
		     		platform.setName_file(name_file);
		     		platform.setApp_processes_id(app_processes_id);
		     		platform.setRelation_id(relation_id);
		     		platform.setTab_id(tab_id);
		     		
		     	 	process process = EdataDAO.getProcess(app_processes_id);
			    	condition conditions = ConditionDAO.getbyid(process.getId_condition());
		     		edata edata = new edata();
		     		edata.setData_eformid(conditions.getCondition_id());
		     		edata.setData_eformrv(conditions.getCondition_rv());
		     		edata.setCustomer(customerCode);
		     		platform.setEdata(edata);
		     		
		     		System.out.println(" 555 "+platform.getEdata().getData_eformid());
		     		System.out.println(" 555 "+platform.getEdata().getData_eformid());
		     		
		     		
		     		
		     		LoadApp   LoadApp =   new LoadApp();	
		    		int	eformId = platformService.initiate(LoadApp).newFile(platform).save();
		    		
		    		System.out.print(" eformId "+eformId);
		    		
		    		if(eformId > 0) {
		    			return "redirect:/editdocumentapp-filemanager/"+eformId+"/"+platform.getApp_processes_id();
		    		}else {
		    			return "redirect:/app-filemanager/"+platform.getApp_processes_id();
		    		}
		    		
			  		
		    	}
		    
		    
		    

		@RequestMapping(value="/subtable-filemanager/{id}/{process_id}/{type}",method = RequestMethod.GET)
		public String subtable(ModelMap model
						,@PathVariable int id
						,@PathVariable int process_id
						,@PathVariable  String type
						,HttpSession session
						,RedirectAttributes redirectAttributes
						) {	
			
					Platform platform = new Platform();
					platform.setApp_processes_id(process_id);
					edata Edata = new edata();
					Edata.setData_id(id);
					platform.setEdata(Edata);
					platform.setApp_entity_type(type);
			
			
					Object SubData = session.getAttribute("SubData"+id);
					LoadApp   LoadApp =   new LoadApp();	
					LoadApp app = platformService.initiate(LoadApp).loadPlatForm(platform).child(SubData).load();
					
					MainServices.addNotificationAttributes(model);
					Map<String, Object> data = platformService.getModelData();
					model.addAllAttributes(data);
				
					session.removeAttribute("SubData"+id);
					session.setAttribute("SubData"+id, app.getActionbatch());
					
				
					
					
					
					
				  	return "/app/windows/subtable";
				}
				
				
		@RequestMapping(value="/addsubtable-filemanager/{id}/{process_id}",method = RequestMethod.GET)
		public String addsubtable(ModelMap model
						,@PathVariable int id
						,@PathVariable int process_id,
						@ModelAttribute("edata_old") edata edataOld,
						@RequestParam(required=false,name="error") String error
						,HttpSession session
						,RedirectAttributes redirectAttributes
						) {	
			
			
					Platform platform = new Platform();
					platform.setApp_processes_id(process_id);
					edata Edata = new edata();
					Edata.setData_id(id);
					platform.setEdata(Edata);
					
					LoadApp   LoadApp =   new LoadApp();	
					platformService.initiate(LoadApp).loadPlatForm(platform).childForm().load();
					MainServices.addNotificationAttributes(model);
					Map<String, Object> data = platformService.getModelData();
					model.addAllAttributes(data);
					
	  	            
				  	return "/app/windows/formsubtable";
				}
				   
			    
			    
			    
			    
			    
			    
		    
		    
		    @RequestMapping(value="/setfollowup-filemanager/{id}",method = RequestMethod.POST)
		 			public String setfollowup(
		 					 @RequestParam int edata_id
		 					 ,@RequestParam String data_dateplan
		 					) {	
		    	
		    
			    	EdataDAO.SetDatePlanByDataId(edata_id,data_dateplan);
			    	return "/ajax/save";
		    	}
		    
		    
		    @RequestMapping(value="/additem-filemanager",method = RequestMethod.POST)
			public  <T> String additem(ModelMap model
					 ,edata_procurement edata_procurements
					,RedirectAttributes redirectAttributes
					,HttpSession session
				    ,Platform platform
				    ,MultipleModelsDTO multipleModelsDTO 
				        
					) {	
		    	
		    	
		    	
		      	MainServices.addNotificationAttributes(model);
			 	Object Actionbatch =  session.getAttribute("SubData"+platform.getApp_tempId());
	     		LoadApp   LoadApp =   new LoadApp();	
			    LoadApp app = 	platformService.initiate(LoadApp).setEntity(multipleModelsDTO,platform).stageToBatchForSave(Actionbatch);
				
				
				session.setAttribute("SubData"+platform.getApp_tempId(),otherservices.ObjectListClone(app.getActionbatch()));
				Object Actionbatchs =  session.getAttribute("SubData"+platform.getApp_tempId());
			
				LoadApp   LoadApp2 =   new LoadApp();	
				platformService.initiate(LoadApp2)
				.loadPlatForm(platform)
				.hasChanges(Actionbatchs)
				.load();

				
				Map<String, Object> data = platformService.getModelData();
				model.addAllAttributes(data);
				return "/app/windows/subtable";
		    	
			}
		    
		    
		    
		   
				    
		    
		    @RequestMapping(value="/titem-filemanager/{id_refer}/{process}",method = RequestMethod.GET)
			public <T> String titem(
					ModelMap model
					,@PathVariable int process
					,@PathVariable int id_refer
					,HttpSession session
					) {	
	    	
		    
		      	List<?> SubDataDTO = (List<?>) session.getAttribute("SubData" + id_refer);
		
		      	
		    	if (SubDataDTO != null) {
		    	    if (!SubDataDTO.isEmpty()) {
		    	        Object firstItem = SubDataDTO.get(0);
		    	        
		    	        if (firstItem instanceof edata_procurement) {
		    	        	List<edata_procurement> data  = (List<edata_procurement>) SubDataDTO;
		    	        	
		    	        	data.forEach(row->{
		    	        		row.setProcurement_type("Clear");
		    	        		row.setProcurement_quantity_ordered( String.valueOf(row.getProcurement_quantityrequested()) );
		    	        		row.setProcurement_unit_price(String.valueOf(row.getProcurement_estimatedprice()));
		    	        	});

		    	        	EdataDAO.SaveEdata_procurements_item(data,id_refer);
		    	        }
		    	    }
		    	    
		    		
		    	}
		    		
		      	return "redirect:/editdocumentapp-filemanager/"+id_refer+"/"+process;
			}
		    
		    
		    
		    
		   
		    
		    @RequestMapping(value="/addreferitem-filemanager",method = RequestMethod.POST)
			public String addreferitem(
					@RequestParam(required=false,name="selectedValues") String selectedValues,
					@RequestParam int processes_id,
					@RequestParam int edata_id,
					@RequestParam String edata_type,
					HttpSession session
					) {	
		     	switch (edata_type) {
			      
				case "edata_procurement":
			    	EdataDAO.CopyEdata_procurement(edata_id,selectedValues);
					break;
				case "production_directive":
			    	EdataDAO.CopyProduction_directivet(edata_id,selectedValues);
					break;	
		        default:
		      }	
		    
		    	return "redirect:/editdocumentapp-filemanager/"+edata_id+"/"+processes_id;
			}
		    
		    
		    
		    
		   
		    @RequestMapping(value="/import_reference-filemanager",method = RequestMethod.POST)
			public String import_reference(
					@RequestParam(required=false,name="selectedIds") String selectedIds,
					@RequestParam int processes_id,
					@RequestParam int edata_id,
					@RequestParam String refer_type,
					HttpSession session
					) {	

		    	
		    		EdataDAO.SaveReference(edata_id,selectedIds);
		    		
		    		System.out.println(" selectedIds "+selectedIds);
		    		
		    		
		    	     if(selectedIds.isBlank()) { 
		    	    	 return "redirect:/editdocumentapp-filemanager/"+edata_id+"/"+processes_id;
		    	     }
		    		
		    	     
		    	 	List<keymap> keymaps = EdataDAO.getAllKeyMaps(processes_id);
		    	 	
		 
		    	 	List<String> transferredColumn = keymaps.stream()
		    	 			.filter(row-> row.getType().equals("hierarchical_reference"))
		    	 			.filter(row-> Otherservice.IntegerValueOf(row.getEformkey()) > 0)
		    	 			.map(row-> String.valueOf(row.getMainkey())).collect(Collectors.toList());
		    	 	
	
		    	     
		    	     
		    		
					List<doc> docs = EdataDAO.getDoc_in_set(selectedIds);
		    	 	switch (refer_type) {
				      
					case "edata_procurement_batch_continue":	
						
			
						
						System.out.println(" docs "+docs.size());
						
						docs.forEach(row->{
							
							List<edata_procurement> procurement = EdataDAO.getAllEdata_procurements_itemstatus(String.valueOf(row.getDoc_idrefer()),"Send");
							
							String  selectedValues = 
									 procurement.stream()
									.map(rows-> String.valueOf(rows.getId()))
									.collect(Collectors.joining(","));
							
							System.out.println(" selectedValues "+selectedValues);
							System.out.println(" Doc_idrefer "+row.getDoc_idrefer());
							System.out.println(" procurement "+procurement.size());
							    	EdataDAO.CopyEdata_procurement(edata_id,selectedValues);
						});

						break;
					case "edata_register_processing":	
						Filemanagerservice.Import_Rprocessing(docs,edata_id,transferredColumn);
						break;
					case "production_directive":	
						Filemanagerservice.Import_production_directive(docs,edata_id,transferredColumn);
						break;
					case "edata_production_record":	
					   Filemanagerservice.Import_edata_production_record(docs,edata_id,transferredColumn);
						break;
					case "production_list":	
						Filemanagerservice.Import_production_list(docs,edata_id,transferredColumn);
						break;
					case "other":	
						Filemanagerservice.Import_other(docs,edata_id,transferredColumn);
						break;	
						
					case "Issue_fg":	
						Filemanagerservice.Import_Issue_fg(docs,edata_id,transferredColumn);
						break;	
					case "Inventory_transfer_fg":	
						Filemanagerservice.Import_Inventory_transfer_fg(docs,edata_id,transferredColumn);
						break;	
					case "Goods_receipt_fg":	
						Filemanagerservice.Import_Goods_receipt_fg(docs,edata_id,transferredColumn);
						break;	
					case "Adjustment_fg":	
						Filemanagerservice.Import_Adjustment_fg(docs,edata_id,transferredColumn);
						break;	
						
					case "Issue_sk":	
						Filemanagerservice.Import_Issue_sk(docs,edata_id,transferredColumn);
						break;	
					case "Inventory_transfer_sk":	
						Filemanagerservice.Import_Inventory_transfer_sk(docs,edata_id,transferredColumn);
						break;	
					case "Goods_receipt_sk":	
						Filemanagerservice.Import_Goods_receipt_sk(docs,edata_id,transferredColumn);
						break;	
					case "Adjustment_sk":	
						Filemanagerservice.Import_Adjustment_sk(docs,edata_id,transferredColumn);
						break;		
						
			        default:
			      }	
		    	
		    		
		    	return "redirect:/editdocumentapp-filemanager/"+edata_id+"/"+processes_id;
			}
		    
		    
		    
		    
		    
		    @RequestMapping(value="/attachdocuments-filemanager/{processes_id}/{edata_id}/{doc_id}/{type}",method = RequestMethod.GET)
			public String attachdocuments(
					@PathVariable int processes_id
					,@PathVariable  int doc_id
					,@PathVariable  int edata_id
					,@PathVariable  String type
					) {	

		    		EdataDAO.SaveAttachdocuments(edata_id,doc_id,type);

		    	return "redirect:/editdocumentapp-filemanager/"+edata_id+"/"+processes_id;
			}
		    
		    
		    
		    
		    
		    
		  
		    @RequestMapping(value="/loadreference-filemanager/{doc}",method = RequestMethod.GET)
				public String loadreference(
						@PathVariable String doc
						,ModelMap model
						,HttpSession session
						,@RequestParam(required=false,name="view_type") int view_type
						) {	
		    	
		    	doc = doc.split("-")[0]; 
		    	List<doc> docs = EdataDAO.getDoc(doc);
		    	edata edatas = EdataDAO.getbyid(docs.get(0).getDoc_idrefer());	
				 model.addAttribute("edatas",edatas);  
				 model.addAttribute("view_type",view_type);
				 
		    	System.err.println("doc "+doc);
		    	System.err.println("edatas "+edatas.getProcesses_id());

		    	
		    	
		    	int processes_id = edatas.getProcesses_id();
		    	
		    	 process process = EdataDAO.getProcess(processes_id);
				 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				 process.setSub_processes(sub_process);
				 workflow work  = WorkflowDAO.getbyid(process.getId_refer());
			
				 
				 
		  
		    	condition conditions = ConditionDAO.getbyid(process.getId_condition());
		    	model.addAttribute("conditions",conditions);  

		    	List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv()); 
		    	List<keymap> keymaps = EdataDAO.getAllKeyMaps(processes_id);
		    	
		      	
		    	
		    	
		       List<keymap> keymap =  Filemanagerservice.setKeymap(process.getSub_processes());
		 	   LoadApp LoadApp = new LoadApp();
			   LoadApp.setId(0);
			   LoadApp.setWorkflow(work);
			   LoadApp.setSession(session);
			   LoadApp.setProcess(process);
			   LoadApp.setSub_processes(process.getSub_processes().get(process.getSub_processes().size() - 1));
			   LoadApp.setModel(model);
			   LoadApp.setKeymap(keymap);
			   LoadApp.setEdata(edatas);
			   LoadApp.setTempId(String.valueOf(edatas.getData_id()));
			   System.out.println("Up LoadApp->");  
			   LoadApp.setCondition(conditions);
			
			   Filemanagerservice.LoadAppViewMode(LoadApp);
		    	
			   
			   switch (work.getWorkflow_maindb()) {
			      
		        case "Production":
				   	model.addAttribute("reference_type","Production");
		        	 return "/app/hierarchical_referencedocs";
					
		        default:	
		        	 return "/app/referencedocs";
		      }	
   
	}
		    
		    
	
		    @RequestMapping(value="/loadattach-filemanager/{attach_type}/{attachdocuments}/{edata_id}/{type}",method = RequestMethod.GET)
				public String loadattach(
						@PathVariable String attach_type
						,@PathVariable int attachdocuments
						,@PathVariable int edata_id
						,@PathVariable String type
						,ModelMap model
						,HttpSession session
						) {	
		    	
		    	
		  
		      	edata subedatas = EdataDAO.getbyid(edata_id);	
                 int mainedata = 0;
		    	  switch (attach_type) {

			        case "Bom":
			        	 bom bom = EdataDAO.getBomId(attachdocuments);
			          	 List<bom> boms = EdataDAO.getAllEdata_bom_item_group(bom.getEdata_id());
			        	 model.addAttribute("boms",boms);  
			        	 model.addAttribute("bom",bom);  
			        	 
			        	 
			        	 if(type.equals("production_list")) {
			        		 production_list production_list = 	 EdataDAO.geteData_pl(edata_id);
			        		 if(production_list != null) {
			        	       	 model.addAttribute("total",production_list.getOrder_quantity()); 
			        		 }
			     
			        	 }
			        	 
			        	 
			        	 mainedata = bom.getEdata_id();
			        	 
			     		break; 
			        case "Ps":
			        	production_standard ps = EdataDAO.getPsId(attachdocuments);

			        	 model.addAttribute("ps",ps);  
			        	 mainedata = ps.getEdata_id();
			        	 
			     		break; 
			        	 
			        default:	
			        	
			      }	
	
		    	
		    	edata edatas = EdataDAO.getbyid(mainedata);	
	
				 model.addAttribute("edatas",edatas);  
		    	System.err.println("edatas "+edatas.getProcesses_id());

		    	
		    	
		    	int processes_id = edatas.getProcesses_id();
		    	
		    	 process process = EdataDAO.getProcess(processes_id);
				 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				 process.setSub_processes(sub_process);
				 workflow work  = WorkflowDAO.getbyid(process.getId_refer());
			
				 
				 
		  
		    	condition conditions = ConditionDAO.getbyid(process.getId_condition());
		    	model.addAttribute("conditions",conditions);  

		    	
		       List<keymap> keymap =  Filemanagerservice.setKeymap(process.getSub_processes());
		 	   LoadApp LoadApp = new LoadApp();
			   LoadApp.setId(0);
			   LoadApp.setWorkflow(work);
			   LoadApp.setSession(session);
			   LoadApp.setProcess(process);
			   LoadApp.setSub_processes(process.getSub_processes().get(process.getSub_processes().size() - 1));
			   LoadApp.setModel(model);
			   LoadApp.setKeymap(keymap);
			   LoadApp.setEdata(edatas);
			   LoadApp.setSubedata(subedatas);
			   LoadApp.setTempId(String.valueOf(edatas.getData_id()));
			   LoadApp.setCondition(conditions);
		
			   
			   System.out.println("Up LoadApp->");  
			   Filemanagerservice.LoadAppViewMode(LoadApp);
		    	
			   	model.addAttribute("edata_id",edata_id);  
			   switch (attach_type) {

		        case "Bom":
		        	 return "/app/Bom_referencedocs";
		        case "Ps":
		        	 return "/app/Ps_referencedocs"; 	 
		        default:	
		        	 return "/app/referencedocs";
		      }	
   
	}
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    @RequestMapping(value="/loadhierarchical-filemanager/{doc_join}/{workflow_refer}",method = RequestMethod.GET)
						public String loadhierarchical(
								@PathVariable int doc_join
								,@PathVariable String workflow_refer
								,ModelMap model
								,HttpSession session
								) {	
		    	

		   
				    	
				    	
				    	int processes_id = doc_join;
				    	
				    	 process process = EdataDAO.getProcess(doc_join);
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
						    			.filter(subrow-> subrow.getType().equals("hierarchical_reference"))
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
					   
					   //LoadApp.setEdata(edatas);
					 //  LoadApp.setTempId(String.valueOf(edatas.getData_id()));
					  
					   //LoadApp.setDoc_join(doc_join);
					   LoadApp.setWorkflow_refer(workflow_refer);
					   
					   
					   System.out.println("Up LoadApp->");  
					   Filemanagerservice.LoadSubData(LoadApp);
				    	
					   
					   switch (work.getWorkflow_maindb()) {
					      
				        case "Production":
				        	 return "/app/views/subdata";
				        case "Stock_Warehouse":
				        	 return "/app/views/subdata";	
				        default:	
				        	 return "/app/referencedocs";
				      }	
		   
			}
				    
		    
		    
		    
		    
		    @RequestMapping(value="/loadattachs-filemanager/{attach_type}/{attachdocuments}/{id}/{total}",method = RequestMethod.GET)
			public String loadattachs(
					@PathVariable int attachdocuments
					,@PathVariable String attach_type
					,@PathVariable int id
					,@PathVariable Double total
					,ModelMap model
					,HttpSession session
					) {	
	
		    	
		   	         bom bom = null;
		    	   int mainedata = 0;
		    	   switch (attach_type) {

			        case "Bom":
			        	 bom boms = EdataDAO.getBomId(attachdocuments);
			        	 mainedata = boms.getEdata_id();
			        	 
			          	 bom = EdataDAO.getBomId(id);
			        	 bom.setBom_base_quantity(total);
			        	 
			        	 
			        	 
			        	 
			     		break;
			        default:	
			        	
			      }	
	
		    	
		    	edata edatas = EdataDAO.getbyid(mainedata);	
		    	
		    	int processes_id = edatas.getProcesses_id();
		    	
		    	 process process = EdataDAO.getProcess(processes_id);
				 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
				 process.setSub_processes(sub_process);
				 workflow work  = WorkflowDAO.getbyid(process.getId_refer());
			
				 
				 
		  
		    	condition conditions = ConditionDAO.getbyid(process.getId_condition());
		    	model.addAttribute("conditions",conditions);  

		    
	
		 	   LoadApp LoadApp = new LoadApp();
			   LoadApp.setModel(model);
			   LoadApp.setWorkflow(work);
			   LoadApp.setBom(bom);
			   LoadApp.setEdata(edatas);
			   LoadApp.setProcess(process);
			   LoadApp.setCondition(conditions);
			   LoadApp.setSubaction(attach_type);

			   Filemanagerservice.LoadSubData(LoadApp);
		    	
			   
			   switch (attach_type) {
			      
		        case "Bom":
		        	 return "/app/views/bomsubdata";
					
		        default:	
		        	 return "/app/referencedocs";
		      }	

		    }
	    
		    
		    
		    @RequestMapping(value="/edititem-filemanager/{item_id}/{tempId}/{process_id}",method = RequestMethod.GET)
			public <T> String edititem(
					ModelMap model,
					@PathVariable int item_id,
					@PathVariable int tempId,
					@PathVariable int process_id
					,HttpSession session
					) {	
		    	

				Platform platform = new Platform();
				platform.setApp_processes_id(process_id);
				edata Edata = new edata();
				Edata.setData_id(tempId);
				platform.setEdata(Edata);
				
		
		    	
		    	
					 	Object Actionbatch =  session.getAttribute("SubData"+tempId);
						LoadApp   LoadApp =   new LoadApp();	
						
						platformService.initiate(LoadApp)
						.loadPlatForm(platform)
						.childForm(Actionbatch,item_id)
						.load();
				
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
						
					  	return "/app/windows/formsubtable";
	    	
		    }
		    
		    
		    
		    @RequestMapping(value="/edititem2-filemanager/{item_id}/{id_refer}",method = RequestMethod.GET)
			public <T> String edititem2(
					ModelMap model,
					@PathVariable int item_id,
					@PathVariable String id_refer,
					@RequestParam(required=false,name="del") String del
					,HttpSession session
					) {	
		    	
		    	
		    	
		    	List<?> SubDataDTO = (List<?>) session.getAttribute("SubDataclear"+id_refer);
		    	
		    	if (SubDataDTO != null) {
		    	    if (!SubDataDTO.isEmpty()) {
		    	        Object firstItem = SubDataDTO.get(0);
		    	        
		    	        if (firstItem instanceof edata_procurement) {
		    	        	
		    	        	
		    	            System.out.println("พบอ็อบเจ็กต์ประเภท edata_procurement");
		    	            List<edata_procurement> SubDataDTOS = (List<edata_procurement>) SubDataDTO;

		    	        	Optional<edata_procurement> New_status =  SubDataDTOS.stream().filter(dto->dto.getId() == item_id).findFirst();
		    	          	
				    		if (New_status.isPresent()) {
				    			edata_procurement item = New_status.get();
				    			model.addAttribute("item",item);
				    			model.addAttribute("type","edata_procurement");
				    		}
						
		    	  
		    	        } else if (firstItem instanceof edata_register) {
		    	        	
		    	        	
		    	            System.out.println("พบอ็อบเจ็กต์ประเภท edata_register");
		    	            List<edata_register> SubDataDTOS = (List<edata_register>) SubDataDTO;

		    	        	Optional<edata_register> New_status =  SubDataDTOS.stream().filter(dto->dto.getId() == item_id).findFirst();
		    	          	
				    		if (New_status.isPresent()) {
				    			edata_register item = New_status.get();
				    			model.addAttribute("item",item);
				    			model.addAttribute("type","edata_register");
				    		}
						
		    	            
		    	        } else {
		    	            System.out.println("พบอ็อบเจ็กต์ประเภทอื่น: " + firstItem.getClass().getName());
		    	        }
		    	    }
		    	}
		    	
		  
		    	
		    	
		 
	    	
	    	
	    	
	    	
			
				
				return "/app/subdatas";
	    	
		    }
		    
		    
		    
		    @RequestMapping(value="/delitem-filemanager",method = RequestMethod.POST)
				public <T> String delitem(
						ModelMap model
						,HttpSession session
						,Platform platform
						) {	

			 		Object Actionbatch =  session.getAttribute("SubData"+platform.getApp_tempId());
			 		LoadApp   LoadApp =   new LoadApp();
			 		LoadApp app = platformService.initiate(LoadApp).stageToBatchForDelete(Actionbatch,platform);
			 	
					session.setAttribute("SubData"+platform.getApp_tempId(),otherservices.ObjectListClone(app.getActionbatch()));
					Object Actionbatchs =  session.getAttribute("SubData"+platform.getApp_tempId());
			 	
			 		LoadApp   LoadApp2 =   new LoadApp();
				 	platformService.initiate(LoadApp2).loadPlatForm(platform)
				 	.hasChanges(Actionbatchs).load();
				 	
				 	
					Map<String, Object> data = platformService.getModelData();
					model.addAllAttributes(data);
		    	
					return "/app/windows/subtable";
				}
		    
			
		    
		    
		    @RequestMapping(value="/delitem2-filemanager/{item_id}/{id_refer}",method = RequestMethod.GET)
						public <T> String delitem2(
								ModelMap model,
								@PathVariable int item_id,
								@PathVariable String id_refer,
								@RequestParam(required=false,name="del") String del
								,HttpSession session
								) {	
				    	
				    	
				    	
				
					    	List<?> SubDataDTO = (List<?>) session.getAttribute("SubDataclear"+id_refer);
			
					    	if (SubDataDTO != null) {
					    	    if (!SubDataDTO.isEmpty()) {
					    	        Object firstItem = SubDataDTO.get(0);
					    	        
					    	        if (firstItem instanceof edata_procurement) {
					    	            System.out.println("พบอ็อบเจ็กต์ประเภท edata_procurement");
					    	            List<edata_procurement> SubDataDTOS = (List<edata_procurement>) SubDataDTO;
			
					    	            SubDataDTOS.removeIf(subData -> subData.getId() == item_id); 
					    	            System.out.print("test del size "+SubDataDTOS.size());
					    	            
					    	  
					    	        } else if (firstItem instanceof edata_register) {
					    	            System.out.println("พบอ็อบเจ็กต์ประเภท edata_register");
					    	            List<edata_register> SubDataDTOS = (List<edata_register>) SubDataDTO;
					    	            
					    	        
					    	            SubDataDTOS.removeIf(subData ->subData.getId() == item_id);
					    	            System.out.print("test del size "+SubDataDTOS.size());
					    	            
			
					    	        } else {
					    	            System.out.println("พบอ็อบเจ็กต์ประเภทอื่น: " + firstItem.getClass().getName());
					    	        }
					    	    }
					    	}
					    	
					    	
					    	
							model.addAttribute("delitem",item_id);
							return "/app/subdatas";
						}
		    
		    
		 
		    

		    
		    @RequestMapping(value="/ChooseFile-filemanager",method = RequestMethod.GET)
				public String ChooseFile(ModelMap model
						) { 
		    	
		    				return "app/windows/FileExplorer";
		    		}
		    
		    
		    @RequestMapping(value="/workflow_db-filemanager/{process_id}/{type}/{tables}/{filters}",method = RequestMethod.GET)
			public String workflow_db(ModelMap model,@PathVariable int process_id,@PathVariable String type,@PathVariable int filters
					,@PathVariable String tables
					,@RequestParam(required=false,name="edata_id") String edata_id
					) { 	
		    			
		    	model.addAttribute("process_id",process_id);
		    	model.addAttribute("tables",tables);
		    	model.addAttribute("filters",filters);
		    	model.addAttribute("edata_id",edata_id);
		    	model.addAttribute("type",type);

		    	
		    	
		    	process process = EdataDAO.getProcess(process_id);
		    	model.addAttribute("process",process);
		    	model.addAttribute("tables",tables);
		    	
		    	
		    	
		    	
		    	List<process> all_process = EdataDAO.getAllProcessJoin(process.getId_refer());
		    	
		    	List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process_id);
		    
		    	
		    	
		    	
		    	AtomicReference<String> filter = new AtomicReference<>();
		    	sub_process.stream().filter(row-> row.getStep().equals("Create")).forEach(row->{
		    		
		    		if(filters == 1) {       filter.set(row.getFiltermain()); }
		    		else if(filters == 2) {   filter.set(row.getFiltersub());  }
		    		else if(filters == 3) {   filter.set(row.getFiltersub1());  }
		    		else if(filters == 4) {  filter.set(row.getFiltersub2());  }
		    		else if(filters == 5) {  filter.set(row.getFiltersub3());  }
		    		else if(filters == 6) {  filter.set(row.getFiltersub4());  }
		    		else if(filters == 7) {  filter.set(row.getFiltersub5());  }
		    		
		    	});
		    	
		    	
		    	String ids = all_process.stream()
		                .map(p -> String.valueOf(p.getId_refer())) // แปลง int เป็น String
		                .distinct()      
		                .collect(Collectors.joining(","));
		    	
		    	System.out.println(" process_id "+process.getId_refer());	
		    	System.out.println(" all_process "+all_process.size());	
		    	
				    	switch (type) {
					      
						case "Procurement_SupplyChain":
							model.addAttribute("type",type);
							
							
								    if(tables.equals("stock")) {
								       	Filemanagerservice.LoadStock(model,process_id,ids);
		                            	return "app/windows/db";
								
								    }else if(tables.equals("register")){
								    	Filemanagerservice.LoadRegister(model,process_id,ids,filter.toString(),null);
								    	return "app/windows/db";
								    }
									
						
						case "Sales_Customer":
							
							model.addAttribute("type",type);
							Filemanagerservice.LoadRegister(model,process_id,ids,filter.toString(),null);
							return "app/windows/db";
								
						case "Stock_Warehouse":
							
							
								 model.addAttribute("type",type);
	                            if(tables.equals("stock")) {
	                            	Filemanagerservice.LoadSK(model,process_id,ids,filter.toString(),null);
	                            	return "app/windows/db";
	                            }
	                            else if(tables.equals("FG")) {
	                            	System.out.println(" tables "+tables);
	    							Filemanagerservice.LoadFG(model,process_id,null);
	    						
	    							return "app/windows/db";
	                             }
	                            else if(tables.equals("product")) {
	                	        	Filemanagerservice.LoadProduct_master(model,process_id,ids,filter.toString(),null);
	                	        	return "app/windows/db";
	                             }
	                            
                            
						
							
							
				        case "Production":
				        	
				        	
				        	
				        	  if(tables.equals("register")) {
				        			Filemanagerservice.LoadRegister(model,process_id,ids,filter.toString(),null);
				        			return "app/windows/db";
				        		  
				        	  }else {
				        		  Filemanagerservice.LoadProduct_master(model,process_id,ids,filter.toString(),null);
								
									return "app/windows/db";
				        	  }
				        	
				        	
				        	
				     
				        
				        case "production_list":
				        	
				        	
				        	Filemanagerservice.LoadBom(model,process_id,null);
				        	  model.addAttribute("edata_id",edata_id);
				       	    model.addAttribute("process_id",process_id);
							return "app/db/Bom_DB";	
							
							
							
				        case "qci":
				        	Filemanagerservice.LoadPs(model,process_id,null);
				        	  model.addAttribute("edata_id",edata_id);
				       	    model.addAttribute("process_id",process_id);
							return "app/db/PS_DB";	
				        default:	
				        	return "app/db/DB";
				        	
				        	
				      }	
				
		   }  
		    
		    
		   
		    
		    
		    
		    

		    @RequestMapping(value="/removememberpolicy-filemanager/{policy_id}/{id}",method = RequestMethod.GET)
					public String removememberpolicy(RedirectAttributesModelMap rediract,@PathVariable long policy_id,@PathVariable long id) { 
		    	
		    	 Policy policy  = policyRepository.findById(policy_id);
		    	 
	
		    	  PolicyMember memberToRemove = null;
		    	    for (PolicyMember member : policy.getMembers()) {
		    	        if (member.getId() == id) {
		    	            memberToRemove = member;
		    	            break;
		    	        }
		    	    }

		    	    // ถ้าไม่เจอ member ที่จะลบ
		    	    if (memberToRemove == null) {
		    	        throw new RuntimeException("PolicyMember not found with ID: " + id);
		    	    }

		    	    // ลบออกจาก List ของ Policy
		    	    policy.getMembers().remove(memberToRemove);

		    	    // Save policy ใหม่ Hibernate จะลบ record ให้อัตโนมัติ (orphanRemoval ทำงานตรงนี้)
		    	    policyRepository.save(policy);
		    	 
		    	 
		    	 
		         rediract.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		    	 return "redirect:/showpolicy-filemanager/"+policy_id;
		    }	
		    
		    
		    

		    @RequestMapping(value="/user_db-filemanager/{policy_id}",method = RequestMethod.GET)
					public String user_db(ModelMap model,@PathVariable long policy_id) { 
		    		
		
		    	List<user> item =  UserDAO.findAll();
		    	Policy policy  = policyRepository.findById(policy_id);
		    	Set<Integer> idsToRemove = policy.getMembers().stream()
		    			    .map(PolicyMember::getIdEmployee)
		    			    .collect(Collectors.toSet());
		    	
		    	System.out.println(" idsToRemove "+idsToRemove.toString());
		    	
		    	item.removeIf(u -> idsToRemove.contains(u.getUser_id()));
		    	
		        List<groups> Groups =  GroupsDAO.getall(0);
		        List<department> Department =  DepartmentDAO.getall();
		    	
		    	System.out.println(" user_db ");

		    	 model.addAttribute("item",item);
		    	 model.addAttribute("position",Groups);
		    	 model.addAttribute("department",Department);
		    	 model.addAttribute("policy_id",policy_id);
		    	return "app/db/User_DB";
		    }	
		    
		    
		
		    @PostMapping(value="/import_user_policy-filemanager")
			public String import_user_policy(
					@RequestParam(required = false) List<String> selectedIds
					,@RequestParam long policy_id
					,RedirectAttributesModelMap rediract
					) { 
    	
		    	if(selectedIds == null) { 
			    		rediract.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Sys_Failed.name());
			    		return "redirect:/showpolicy-filemanager/"+policy_id;
		    		}
		    	System.out.println("policy_id "+policy_id );
		    	
		    	 Policy policy  = policyRepository.findById(policy_id);
		    	 policy =  (Policy) repositoryService.syncData(selectedIds, policy);
		    	 policyRepository.save(policy);
		    	 
		         rediract.addFlashAttribute(Sys_status.Sys_Save.name(),Save_status.Save_Sucess.name());
		    	 return "redirect:/showpolicy-filemanager/"+policy_id;
		    }	
    
		    
		    @RequestMapping(value="/search_user_dbs-filemanager",method = RequestMethod.POST)
				public String search_user_dbs(
						ModelMap model
						,@RequestParam long policy_id
						,@RequestParam(required = false) String position
						,@RequestParam(required = false) String department
						,@RequestParam(required = false) String register_name
						) { 	
		    	
		    	
		    	
		     	List<user> item =  UserDAO.getSearch(register_name,position,department);
		     	
		    	Policy policy  = policyRepository.findById(policy_id);
		    	Set<Integer> idsToRemove = policy.getMembers().stream()
		    			    .map(PolicyMember::getIdEmployee)
		    			    .collect(Collectors.toSet());
		    	
		    	System.out.println(" idsToRemove "+idsToRemove.toString());
		    	
		    	item.removeIf(u -> idsToRemove.contains(u.getUser_id()));
		     	
		        List<groups> Groups =  GroupsDAO.getall(0);
		        List<department> Department =  DepartmentDAO.getall();
		    	
		    	System.out.println(" user_db ");
		    	 model.addAttribute("policy_id",policy_id);
		    	 model.addAttribute("item",item);
		    	 model.addAttribute("position",Groups);
		    	 model.addAttribute("department",Department);
		    	return "app/db/User_DB";
		    }
		    
		    
		    
		    @RequestMapping(value="/search_workflow_dbs-filemanager",method = RequestMethod.POST)
			public String search_workflow_dbs(
					ModelMap model
					,search_workflow_db search_workflow_db
					) { 	
		    			
		      	model.addAttribute("process_id",search_workflow_db.getProcess_id());
		    	model.addAttribute("tables",search_workflow_db.getTables());
		    	model.addAttribute("filters",search_workflow_db.getFilters());
		    	model.addAttribute("edata_id",search_workflow_db.getEdata_id());
		    	model.addAttribute("type",search_workflow_db.getType());
		    	int process_id = search_workflow_db.getProcess_id();
		    	int filters = search_workflow_db.getFilters();
		      	int edata_id = search_workflow_db.getEdata_id();
		      	String tables = search_workflow_db.getTables();
		      	String type = search_workflow_db.getType();
		    	
		      	System.out.println("process_id "+process_id);
		      	
		      	
		      	
		    	process process = EdataDAO.getProcess(process_id);
		    	
		    	List<process> all_process = EdataDAO.getAllProcessJoin(process.getId_refer());
		    	
		    	List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process_id);
		    
		    	
		    	
		    	
		    	AtomicReference<String> filter = new AtomicReference<>();
		    	sub_process.stream().filter(row-> row.getStep().equals("Create")).forEach(row->{
		    		
		    		if(filters == 1) {       filter.set(row.getFiltermain()); }
		    		else if(filters == 2) {   filter.set(row.getFiltersub());  }
		    		else if(filters == 3) {   filter.set(row.getFiltersub1());  }
		    		else if(filters == 4) {  filter.set(row.getFiltersub2());  }
		    		else if(filters == 5) {  filter.set(row.getFiltersub3());  }
		    		else if(filters == 6) {  filter.set(row.getFiltersub4());  }
		    		else if(filters == 7) {  filter.set(row.getFiltersub5());  }
		    		
		    	});
		    	
		    	
		    	String ids = all_process.stream()
		                .map(p -> String.valueOf(p.getId_refer())) // แปลง int เป็น String
		                .distinct()      
		                .collect(Collectors.joining(","));
		    	
		    	System.out.println(" process_id "+process.getId_refer());	
		    	System.out.println(" all_process "+all_process.size());	
		    	
				    	switch (type) {
					      
						case "Procurement_SupplyChain":
							model.addAttribute("type",type);
							
							
								    if(tables.equals("stock")) {
								       	Filemanagerservice.LoadStock(model,process_id,ids);
		                            	return "app/db/Stock_Warehouse_DB";
								
								    }else if(tables.equals("register")){
								    	Filemanagerservice.LoadRegister(model,process_id,ids,filter.toString(),search_workflow_db);
										return "app/db/Procurement_SupplyChain_DB";
								    }
									
						
						case "Sales_Customer":
							
							model.addAttribute("type",type);
							Filemanagerservice.LoadRegister(model,process_id,ids,filter.toString(),search_workflow_db);
							return "app/db/Customer_DB";
								
						case "Stock_Warehouse":
							
							
								 model.addAttribute("type",type);
	                            if(tables.equals("stock")) {
	                            	Filemanagerservice.LoadSK(model,process_id,ids,filter.toString(),search_workflow_db);
	                            	return "app/db/STOCK_DB";
	                            }
	                            else if(tables.equals("FG")) {
	                            	System.out.println(" tables "+tables);
	    							Filemanagerservice.LoadFG(model,process_id,search_workflow_db);
	    							return "app/db/FG_DB";		
	                             }
	                            else if(tables.equals("product")) {
	                	        	Filemanagerservice.LoadProduct_master(model,process_id,ids,filter.toString(),search_workflow_db);
	    							return "app/db/Production_DB";
	                             }
	                            
                            
						
							
							
				        case "Production":
				        	
				        	
				        	
				        	  if(tables.equals("register")) {
				        			Filemanagerservice.LoadRegister(model,process_id,ids,filter.toString(),search_workflow_db);
									return "app/db/Register_DB";
				        		  
				        	  }else {
				        		  Filemanagerservice.LoadProduct_master(model,process_id,ids,filter.toString(),search_workflow_db);
									return "app/db/Production_DB";
				        		  
				        	  }
				        	
				        	
				        	
				     
				        
				        case "production_list":
				        	
				        	
				        	Filemanagerservice.LoadBom(model,process_id,search_workflow_db);
				        	  model.addAttribute("edata_id",edata_id);
				       	    model.addAttribute("process_id",process_id);
							return "app/db/Bom_DB";	
							
							
							
				        case "qci":
				        	Filemanagerservice.LoadPs(model,process_id,search_workflow_db);
				        	  model.addAttribute("edata_id",edata_id);
				       	    model.addAttribute("process_id",process_id);
							return "app/db/PS_DB";	
				        default:	
				        	return "app/db/DB";
				        	
				        	
				      }	
				
		   }  
		    
		    
		    
		    
		    
		    
		    
		    
		    
			@RequestMapping(value="/loadalldocumentapp-filemanager/{data_id}/{processid}/{loadall}",method = RequestMethod.GET)
			public String loadalldocumentapp(
					 @PathVariable int data_id
					,@PathVariable int processid
					,@PathVariable int loadall
					) {	
			
					EdataDAO.setLoadAll(data_id,loadall);
				   return "redirect:/editdocumentapp-filemanager/"+data_id+"/"+processid+"/";
				}
			
			
			@RequestMapping(value="/notiapp-filemanager/{processid}/{processAll}",method = RequestMethod.GET)
			public String notiapp(
					 ModelMap model,
					 @PathVariable int processid,
					 @PathVariable String processAll
					) {	
				
				    processAll +=","+processid; 
					user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 	    
			    	List<notification> notifications = NotificationDAO.getallrefer(String.valueOf(usersed.getUser_id()) , Userservice.getPoliciesString(), processAll);
			    	
			    	
			    	System.out.println("notifications "+notifications.size());
			    	
			    	
					model.addAttribute("notifications",notifications); 
				   return "app/notiapp";
				}
			
		    
			
			@RequestMapping(value="/changeClosingStatus-filemanager/{data_id}/{Tabid}",method = RequestMethod.GET)
			public String changeClosingStatus(
					 ModelMap model,
					 @PathVariable int data_id,
					 @PathVariable int Tabid
					) {	
			
					edata edata = EdataDAO.getbyid(data_id);
					buildInService.executeBuildIn(edata);
					
					Idoc doc = idocDAO.getDocument(data_id);
					
					TabManagement  Tab = ReportDAO.getTabManagement(Tabid);
					
					List<String> status = new ArrayList<String>(Arrays.asList( Tab.getShortcut_change_closing_status().split(",")));
					
					model.addAttribute("status",status);  
					
					
					model.addAttribute("edata",edata); 
					model.addAttribute("doc",doc); 
					
					
					
				   return "app/changeClosingStatus";
				}
		    
			
			
			
			
			@RequestMapping(value="/changeClosingStatusSave-filemanager",method = RequestMethod.POST)
			public String changeClosingStatus(
					@RequestParam String status,
					@RequestParam String from_status,
					@RequestParam int process_id,
					 @RequestParam int id
					) {	
		
				
				PlatformReport platform = new PlatformReport();
				platform.setApp_item_id(id);
				platform.setWorkflow_status_close(status);
				platform.setWorkflow_status_close_from(from_status);
				platform.setApp_processes_id(process_id);
				
				
				System.out.println(" platform "+id);
				System.out.println(" Workflow_status_close_from "+from_status);
				System.out.println(" Workflow_status_close "+status);
				
				
		 		LoadApp   LoadApp =   new LoadApp();
				platformService.initiate(LoadApp).startAppReport(platform).ChangeWorkflowStatusClose();
				

				   return "app/appRefresh";
				}
		    
			
			
			
			
			@RequestMapping(value="/editdocumentapp-filemanager/{id}/{process_id}",method = RequestMethod.GET)
			public String editdocumentapp(
					 ModelMap model
					,@PathVariable int id
					,@PathVariable int process_id
					,@RequestParam(required=false,name="error") String error
					,HttpSession session
					,RedirectAttributes redirectAttributes
					,@RequestParam(required=false) String boxs
					,@RequestParam(required=false) String process_refer
					) {	

				
				    session.removeAttribute("SubData");
				    MainServices.addNotificationAttributes(model);
				    
				    
					Platform platform = new Platform();
					platform.setApp_processes_id(process_id);
					edata Edata = new edata();
					Edata.setData_id(id);
					platform.setEdata(Edata);
				    
					LoadApp   LoadApp =   new LoadApp();
					
				platformService.initiate(LoadApp).loadPlatForm(platform).data().load();
				 Map<String, Object> data = platformService.getModelData();
			  	model.addAllAttributes(data);
					model.addAttribute("boxs",boxs); 
					
					
					user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			    	NotificationDAO.readAll(String.valueOf(usersed.getUser_id()),Userservice.getPoliciesString(),process_id);
			    	model.addAttribute("PoliciesString",Userservice.getPoliciesString()); 
			    	
			    	
			    	model.addAttribute("process_refer",process_refer); 
					return "/app/windows/form";
				}
			
			
			
			
			@RequestMapping(value="/viewRelationsDoc-filemanager/{relations_id}",method = RequestMethod.GET)
			public String viewRelationsDoc(ModelMap model,@PathVariable int relations_id) {	

							edata edatas = EdataDAO.getbyid(relations_id);

							Platform platform = new Platform();
							platform.setApp_processes_id(edatas.getProcesses_id());
							platform.setEdata(edatas);
					 		LoadApp   LoadApp =   new LoadApp();
							platformService.initiate(LoadApp).loadPlatForm(platform).docProperty().load();
							Map<String, Object> data = platformService.getModelData();
						    model.addAllAttributes(data);
					
					
					return "/app/windows/docproperty";
				}
			
			@RequestMapping(value="/segmentationView-filemanager/{process_id}",method = RequestMethod.GET)
			public String segmentationView(ModelMap model,@PathVariable int process_id) {	
				 
				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				SetSegmentView process = EdataDAO.getSetSegmentView(process_id,usersed);
				
				
		    	System.out.println(" process_id "+process.getProcess_id());
		    	System.out.println(" id_add "+process.getId_add());
		     	System.out.println(" segmentStatus "+process.getSegmentStatus());
		     	System.out.println(" segmentView "+process.getSegmentView());
				
					model.addAttribute("process_id",process_id); 
					model.addAttribute("process",process); 
					return "/app/windows/segmentationView";
				}
			
			@RequestMapping(value="/saveSegmentationView-filemanager",method = RequestMethod.POST)
			public String saveSegmentationView(
					@RequestParam int process_id
					,@RequestParam  (required=false,name="segmentStatus",defaultValue="-") String segmentStatus
					,@RequestParam  (required=false,name="segmentView",defaultValue="0") int segmentView
					,@RequestParam  (required=false,name="query_file",defaultValue="") String query_file
					) {	

				user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				
						PlatformConfig platform = new PlatformConfig();
						process process  = new  process();
						platform.setProcesse_id(process_id);
						
						SetSegmentView setSegmentView = new SetSegmentView();
						setSegmentView.setSegmentStatus(segmentStatus);
						setSegmentView.setSegmentView(segmentView);
						setSegmentView.setProcess_id(process_id);
						setSegmentView.setId_add(usersed.getUser_id());
						setSegmentView.setQuery_file(query_file);
						
			
						
						process.setId(process_id);
						process.setSetSegmentView(setSegmentView);
						platform.setProcess(process);
						
				 		LoadApp   LoadApp =   new LoadApp();
						platformService.initiate(LoadApp).applyConfig(platform).saveSegmentationView();

				
					return "redirect:/segmentation-filemanager/"+process_id;
				}
			
		
			
			
			
			@RequestMapping(value="/undocumentapp-filemanager",method = RequestMethod.POST)    
			    public String undocumentapp( @RequestParam int app_edata_id
			    		,@RequestParam int app_processes_id,HttpSession session,ModelMap model) throws Exception
			 {
				 
				
				System.out.println(" undocumentapp data_id "+ app_edata_id);
				System.out.println(" undocumentapp app_processes_id "+ app_processes_id);
				
				   	String path;
				   	File fileToDelete;
				   	boolean success;
				   	
				 
					List<file> logParts = FileDAO.getallEdata(app_edata_id);
					   	for (file files : logParts) {
			
					  if(files != null) { 	
						  		ServletContext context = session.getServletContext(); 
						     path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
						     fileToDelete = new File(path + File.separator + files.getFile_name());
						     success = fileToDelete.delete();
						     if(success) {
						    	 FileDAO.delete(files.getFile_id());
						     }
					   		}
						}
				
				    edata edatas = EdataDAO.getbyid(app_edata_id);
				    EdataDAO.delete(app_edata_id);
				//    EdataDAO.del(app_edata_id);
				    flowService.del(app_edata_id);
				   
				   return "redirect:/app-filemanager/"+app_processes_id;
			 }
		    
 
				    @RequestMapping(value="/appcenter-filemanager",method = RequestMethod.GET)
				    public String appcenter( ModelMap model){
						
				    	
					    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 	    
					  
					    
				        UserSetting userSetting = userSettingRepository.findByUserId(usersed.getUser_id());
					    model.addAttribute("userSetting", userSetting);  
	
					    model.addAttribute("Member_name", usersed.getUser_name());
					    model.addAttribute("Member_id",usersed.getUser_id());
					    model.addAttribute("user_img", usersed.getUser_img());
					    model.addAttribute("USERNAME", usersed.getUsername());
				    	
				    	
					   	List<workflow> workflows = WorkflowDAO.getallApp();
						String Policies=   Userservice.getPoliciesString();
						  List<notification> notifications = NotificationDAO.getallPolicies(Policies);
						    model.addAttribute("notification", notifications);  
						   model.addAttribute("Policies", Policies);

							System.out.println("notification "+notifications.size());
						System.out.println("Policies "+Policies);
						
						workflows.forEach(row->{
							
							 List<process> process =  EdataDAO.getAllProcess(row.getWorkflow_id());
							 process.removeIf(rows ->{
								 policies policies =   Userservice.getPolicies(rows.getId());
								  return policies.getId() == 0 && rows .getProgram_type() == 0; 
							 });
							 
							 process.stream().forEach(subrow->{
								 
								 long totalalert = 0;
								 long alert = 	 notifications.stream().filter(rows -> rows.getNotification_process_id() == subrow.getId()).count();
								 totalalert = alert;
								 
								 if(subrow.getProgram_type() == 2) {
										long countOpen = helpDeskRepository.countByprocessIdAndStatus(subrow.getId(), "OPEN");
										totalalert = countOpen + totalalert;
								 }
								 subrow.setAlert((int)totalalert);
								 
							 });
							 row.setProcess(process);
				    	});
						model.addAttribute("workflow",workflows); 
						
						

					    
				    	return "/app/appcenter";
				    }


				    @RequestMapping(value="/mywidget-filemanager", method = RequestMethod.GET)
				    @ResponseBody  // <-- พระเอกของเราอยู่ตรงนี้ครับ! 🌟
				    public GamificationWidgetDTO getMyWidgetData() {
				        user usersed = UserDAO.getbyusername(Userservice.getLoggedName());     
				        
				        // สำคัญ: ต้องใส่ prefix "user_" ให้ตรงกับที่บันทึกลง Database ด้วยนะครับ
				        String loggedInUserId = usersed.getUser_id()+"_" + usersed.getUser_name();
				        String loggedInUserName = usersed.getUser_name() + " (" + usersed.getGroup_name() + ")"; 
				        
				        System.out.println("mywidget ++++ : " + loggedInUserId);
				        
				        return gamificationService.getWidgetDataForUser(loggedInUserId, loggedInUserName);
				    }
				    
				    
				    @RequestMapping(value="/mystats-filemanager", method = RequestMethod.GET)
				    @ResponseBody
				    public Map<String, Object> getMyStats() {
				    	
				        user usersed = UserDAO.getbyusername(Userservice.getLoggedName());     
				    	
				        String loggedInUserId = usersed.getUser_id()+"_" + usersed.getUser_name();
				        return gamificationService.getUserStatsOverview(loggedInUserId);
				    }
				    
				    
				    @RequestMapping(value="/setmarkasread-filemanager/{process_id}",method = RequestMethod.GET)    
				    public String setmarkasread(
				    		ModelMap model,
				    		@PathVariable int process_id,
				    		@RequestParam int id,
				    		@RequestParam int alert,
				    		@RequestParam int type
				    		){ 
				    	
				    	
				    	if(type == 1) {
				    		EdataDAO.getIdoc_mark(id,alert);
				    	}else if(type == 2) {
				    		EdataDAO.getTransaction_send_mark(id,alert);
				    	}
				    	
				    	
				    	   return "redirect:/markasread-filemanager/"+process_id;
				    }
				    
				    @RequestMapping(value="/markasread-filemanager/{process_id}",method = RequestMethod.GET)    
				    public String markasread(ModelMap model,@PathVariable int process_id){ 
				    	

				       	MainServices.addNotificationAttributes(model);
				       	
				       	
				       	
				      
				       	
				    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				
			
				    	
				    	
					    process process = EdataDAO.getProcess(process_id);
						model.addAttribute("process",process);
						
						 List<sub_processes> sub_process = EdataDAO.getAllSubProcess(process.getId());
						 
						 
					
						 
						 
						 
						Optional<sub_processes> data_step =  sub_process.stream().filter(row-> row.getStep().equals("Create")).findFirst();
						 
						 if(data_step.isPresent()) {
							 sub_processes step = data_step.get();
							 policies Policies = Userservice.getPoliciesStep(step.getId());
							 System.out.println(" Policies -->" +Policies.getName()); 
							 
							 model.addAttribute("Policies_Create",Policies.getProcess());
						 }


						 workflow flow = WorkflowDAO.getbyid(process.getId_refer());
						 model.addAttribute("flow",flow);
						 

						 List<edata> rawedatas = EdataDAO.getallProcesses(process_id);
						 
						 rawedatas.forEach(row->{
							 user usersed_add = UserDAO.getbyusername2(Userservice.getLoggedName());
							 row.setData_nameadd(usersed_add.getUser_name());
							 
						 });

				
						 List<edata> draft = rawedatas.stream()
								    .filter(d -> Objects.equals(d.getCurrent_step(), "Create") && d.getCreator() == usersed.getUser_id())
								    .collect(Collectors.toList());
						 model.addAttribute("draft",draft); 
						
						 
						 
						List<transaction_send> transaction_sends = EdataDAO.getAllTransaction_send(process.getProcess_join(),"Send",Userservice.getPoliciesString());
						 model.addAttribute("transaction_sends",transaction_sends); 
						 
				
						 
						 
						 System.out.println(" usersed "+usersed.getUser_id());
						 
						 List<edata> reviewed = rawedatas.stream()
								    .filter(d -> (   Objects.equals(d.getCurrent_step(),"Review") || Objects.equals(d.getCurrent_step(),"Approval") || Objects.equals(d.getCurrent_step(), "Clear")  ) 
								    		&& d.getReceiver() == usersed.getUser_id()   )
								    .collect(Collectors.toList());
						 model.addAttribute("reviewed",reviewed); 
						 
						 
	
						 
		
						
					
				     	return "/app/markasread";
				    }
				    
				    
				    @RequestMapping(value="/footprint-filemanager/{process_id}",method = RequestMethod.GET)    
				    public String footprint(ModelMap model,@PathVariable int process_id){ 
				    	
						MainServices.addNotificationAttributes(model);
						
						
						 process process =  EdataDAO.getProcess(process_id);
						 workflow workflows = WorkflowDAO.getbyid(process.getId_refer());
						 List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(process.getId());
							user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
						 System.out.println(" sub_processes "+sub_processes.size());
						 
						 
						 sub_processes.stream()
						    .filter(r -> "Create".equals(r.getStep()))
						    .findFirst()
						    .ifPresent(create -> {
						        if ( otherservices.haveValue(create.getAction()) ) {
						        
						        	
						        } else if ( otherservices.haveValue(create.getAction()) ) { 

						       	 System.out.println(" Activate_register ");
						        	
						        
						       	    if( create.getAction().equals("product")) {
							        	List<product_log> item =  EdataDAO.getAllProduct_logByidAdd(process_id,usersed.getUser_id());
							        	model.addAttribute("item",item); 
							        	model.addAttribute("type","product"); 
						       	    }else {
							        	List<register_log> item =  EdataDAO.getAllRegister_logByidAdd(process_id,usersed.getUser_id());
							        	model.addAttribute("item",item); 
							        	model.addAttribute("type","register"); 
						       	    }
						   
						        	
						        }
						        
						        
						    });
						   	model.addAttribute("process_id",process_id); 
					    	model.addAttribute("workflows",workflows); 
					    	model.addAttribute("process",process); 
					    	model.addAttribute("usersed_name",usersed.getUser_name()); 
	
						
				    	return "/app/views/footprint";
				    }
				    
				    
				    
				    @RequestMapping(value="/ImportCsvSubTable-filemanager", method=RequestMethod.POST)
				    public String ImportCsvSubTable(
				            @RequestParam CommonsMultipartFile file,
				            @RequestParam String data_id, 
				            @RequestParam int process_id,
				            @RequestParam String edata_type,
				            @RequestParam String action_batch_insert,
				            @RequestParam int SkipFirstRow,
				            @RequestParam int SkipFirstColumn,
				            @RequestParam String Split,
				            HttpSession session) {
				        
				        String sessionname = "SubData";
				        List<Object> SubDataDTO = (List<Object>) session.getAttribute(sessionname + data_id);

				  
				        Map<String, Class<?>> typeMap = Map.of(
				            "edata_procurement", edata_procurement.class,
				            "bom", bom.class,
				            "production_standard", production_standard.class,
				            "other", other.class,
				            "production_directive", production_directive.class,
				            "production_list", production_list.class
				        );

				        Class<?> clazz = typeMap.get(edata_type);
				        if (clazz == null) {
				            throw new RuntimeException("ไม่รู้จักประเภท: " + edata_type);
				        }

				        if (SubDataDTO == null) {
				            SubDataDTO = new ArrayList<>();
				        }

				        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
				            List<String> fieldNames = Arrays.asList(action_batch_insert.split(","));
				            String line;
				            int row = 0;
				            int maxRows = 255;
				            int currentRow = 0;
				            
				            while ((line = reader.readLine()) != null) {
				                row++;
				                currentRow++;
				                
				                if (row <= SkipFirstRow) continue;

				                String[] columns = line.split(Split, -1); // -1 ทำให้ได้ค่าว่างครบ
				                Object instance = clazz.getDeclaredConstructor().newInstance();

				                for (int i = SkipFirstColumn; i < columns.length && (i - SkipFirstColumn) < fieldNames.size(); i++) {
				                    String fieldName = fieldNames.get(i - SkipFirstColumn).trim();
				                    String value = columns[i].trim();
				                    Field field = clazz.getDeclaredField(fieldName);
				                    field.setAccessible(true);
				                    
				                    Object convertedValue = Filemanagerservice.convertToFieldType(field.getType(), value);
				                    field.set(instance, convertedValue);
				                }

				                // set tempId และ processId
				                try {
				                    Field tempIdField = clazz.getDeclaredField("tempId");
				                    tempIdField.setAccessible(true);
				                    tempIdField.set(instance, data_id);

				                    Field processIdField = clazz.getDeclaredField("processId");
				                    processIdField.setAccessible(true);
				                    processIdField.set(instance, process_id);
				                } catch (NoSuchFieldException e) {
				                    // หากไม่มี tempId หรือ processId ก็ข้ามไป
				                }

				                SubDataDTO.add(instance);
				                if (currentRow - SkipFirstRow > maxRows) break;
				            }

				        } catch (Exception e) {
				            e.printStackTrace();
				            throw new RuntimeException("Error processing CSV: " + e.getMessage());
				        }

				        session.setAttribute(sessionname + data_id, SubDataDTO);
				        return "redirect:/editdocumentapp-filemanager/" + data_id + "/" + process_id;
				    }
				    
				    
				    
				    @RequestMapping(value="/addtask-filemanager",method = RequestMethod.GET)    
				    public String addtask(ModelMap model){
				    	
				    	MainServices.addNotificationAttributes(model);
				    	Filemanagerservice.getRoot(model);
				    	return "/form/settingtask";
				    } 
				    	
				    
				    @RequestMapping(value="/addproject-filemanager",method = RequestMethod.GET)    
				    public String addproject(ModelMap model){
				    	
				    	MainServices.addNotificationAttributes(model);
				    	Filemanagerservice.getRoot(model);
				    	return "/form/settingproject";
				    } 
				    	
				    
				    
				     
				    @GetMapping(value="/listpolicy-filemanager/{type}")
				    public String listpolicy(ModelMap model,@PathVariable String type){
				    	
				    
				    	  PolicyType policyType = PolicyType.valueOf(type.toUpperCase());
				    	
				    	MainServices.addNotificationAttributes(model);
				    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				    	List<Policy> mypolicy = policyRepository.findAllByTypeAndCreateByFetchMembers(policyType, usersed.getUser_id());
				    	model.addAttribute("mypolicy",mypolicy); 
				    	return "/policy/policy";
				    } 
				     
				    
				    @GetMapping(value="/addpolicy-filemanager/{type}")
				    public String addpolicy(ModelMap model,@PathVariable String type){
				    	
				    	MainServices.addNotificationAttributes(model);
				    	Filemanagerservice.getRoot(model);
				    	
				    	
				    	model.addAttribute("type",type);
				    	return "/policy/form_policy";
				    } 	
				    
				   
			
				    @GetMapping(value="/showpolicy-filemanager/{id}")
				    public String showpolicy(ModelMap model,@PathVariable long id){
				    	
				    	MainServices.addNotificationAttributes(model);
				
				    	
				    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				    	Policy policy = policyRepository.findByIdAndCreateBy(id, usersed.getUser_id());
				    	model.addAttribute("type",policy.getType());
				    	model.addAttribute("policy",policy);
				    	return "/policy/data_policy";
				    } 
				    
				    
				    
				    
				    @GetMapping(value="/editpolicy-filemanager/{id}")
				    public String editpolicy(ModelMap model,@PathVariable long id){
				    	
				    	MainServices.addNotificationAttributes(model);
				       	Filemanagerservice.getRoot(model);
				    	
				    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				    	Policy policy = policyRepository.findByIdAndCreateBy(id, usersed.getUser_id());
				    	model.addAttribute("type",policy.getType());
				    	model.addAttribute("policy",policy);
				    	
				    	
				    	
				    	return "/policy/form_policy";
				    } 
				    
				    
				    @PostMapping("/deletepolicy-filemanager")
				    public String deletepolicy(@Valid Policy policy, BindingResult result, RedirectAttributes redirectAttributes,    @RequestParam String type ) {
				    
				    	if (result.hasErrors()) {
				            redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Sys_Failed.name());
				            return "redirect:/listpolicy-filemanager/" + (policy != null ? policy.getType() : "");
				        }
				    	
		
				    	
				        try {
				        	policyRepository.delete(policy);
				        	boolean isDeleted = !policyRepository.existsById( policy.getId());
				           
				        	System.out.println(" isDeleted "+isDeleted);
				        	
				            if (isDeleted) {
				            	 redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Save_Sucess.name());
				            } else {
				            	 
				                redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Sys_Failed.name());
				            }
				        } catch (Exception e) {

				            redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Sys_Failed.name());
				            // Log exception here
				        }
				        return "redirect:/listpolicy-filemanager/" +type;
				    }
				    
				    
				    
				    
				    @PostMapping("/savepolicy-filemanager")
				    public String savepolicy(@Valid Policy policy, BindingResult result, RedirectAttributes redirectAttributes) {
				    
				    	if (result.hasErrors()) {
				            redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Sys_Failed.name());
				            return "redirect:/listpolicy-filemanager/" + (policy != null ? policy.getType() : "");
				        }

				        try {
				        	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());
				        	policy.setCreateBy(usersed.getUser_id());
				            Policy saved = policyRepository.save(policy);
				            if (saved != null) {
				                redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Save_Sucess.name());
				            } else {
				                redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Sys_Failed.name());
				            }
				        } catch (Exception e) {
				            redirectAttributes.addFlashAttribute(Sys_status.Sys_Save.name(), Save_status.Sys_Failed.name());
				            // Log exception here
				        }
				        return "redirect:/listpolicy-filemanager/" + policy.getType();
				    }
				    
				    
				    
				    
				    
				    @RequestMapping(value="/addtasksave-filemanager",method = RequestMethod.POST)    
				    public String addtasksave(  
				    		@RequestParam String upload_pn_note
				    		,@RequestParam int workflow_folder){

				    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 LocalDateTime localDate = LocalDateTime.now();//For reference	
					 String date = localDate.format(formatter);

			
					 	pathManager.Path(workflow_folder);
				    	
					    System.out.println(" Pathfull "+pathManager.getPathfull());
					    
					    
				    	upload_pn  upload_pn = new upload_pn();
				    	upload_pn.setUpload_pn_idadd(usersed.getUser_id());
				    	upload_pn.setUpload_pn_note(upload_pn_note);
				    	upload_pn.setUpload_pn_idrefer(workflow_folder);
				    	upload_pn.setUpload_pn_name("-");
				    	upload_pn.setUpload_pn_date(date);
				    	upload_pn.setUpload_pn_app("-");
				    	upload_pn.setUpload_pn_respon("-");
				    	upload_pn.setUpload_pn_end("0");
				    	upload_pn.setUpload_pn_setdue("0");
				    	upload_pn.setUpload_pn_storage(pathManager.getPathfull());
				    	
				    	int upload_pn_id = Upload_pnDAO.save(upload_pn);
				    	
				    	
				        return "redirect:/editupload_pn-filemanager?id=" + upload_pn_id;
				    } 
				    	
				    
				    
				    @RequestMapping(value="/addprojectsave-filemanager",method = RequestMethod.POST)    
				    public String addprojectsave(  
				    		@RequestParam String upload_pn_note
				    		,@RequestParam int workflow_folder){

				    	user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					 LocalDateTime localDate = LocalDateTime.now();//For reference	
					 String date = localDate.format(formatter);

			
					 	pathManager.Path(workflow_folder);
				    	
					    System.out.println(" Pathfull "+pathManager.getPathfull());
					    
					    
				    	upload_pn  upload_pn = new upload_pn();
				    	upload_pn.setUpload_pn_idadd(usersed.getUser_id());
				    	upload_pn.setUpload_pn_note(upload_pn_note);
				    	upload_pn.setUpload_pn_idrefer(workflow_folder);
				    	upload_pn.setUpload_pn_name("-");
				    	upload_pn.setUpload_pn_date(date);
				    	upload_pn.setUpload_pn_app("-");
				    	upload_pn.setUpload_pn_respon("-");
				    	upload_pn.setUpload_pn_end("0");
				    	upload_pn.setUpload_pn_setdue("0");
				      	upload_pn.setUpload_pn_type(3);
				    	upload_pn.setUpload_pn_storage(pathManager.getPathfull());
				    	
				    	int upload_pn_id = Upload_pnDAO.save(upload_pn);
				    	
				    	
				    	return "redirect:/projectmanager-filemanager?id="+upload_pn_id+"&&step=0&&group=1";
				    } 
				    	
				    
				    
				    
				    @RequestMapping(value="/editproject-filemanager",method = RequestMethod.POST)    
				    public String editproject(upload_pn upload_pn,RedirectAttributes redirectAttributes ){
				    	
				    	
				    	System.out.println(" Id_policy "+upload_pn.getId_policy());
				    	
				    	
				    	Upload_pnDAO.updateProject(upload_pn);
				    	redirectAttributes.addFlashAttribute("TAB","2");
				    	
				    	return "redirect:/projectmanager-filemanager?id="+upload_pn.getUpload_pn_id()+"&&step=0&&group=1";
				    }
				    
				    
				   
				    @RequestMapping(value="/qcompro-filemanager/{workflow}",method = RequestMethod.GET)    
				    public String qcompro(ModelMap model
				    		,@PathVariable int workflow
				    		,@RequestParam  (required=false,name="data_id",defaultValue="0") int data_id
				    		,@RequestParam  (required=false,name="useformPrintId",defaultValue="0") int useformPrintId
				    		){ 
				    	
				    	System.out.println("Qcompro");
				    	System.out.println(data_id+"  **** "+useformPrintId);
				    	
				    	
						    	if(data_id > 0 &&  useformPrintId > 0) {
						    		
						    	
						    		
						    		
								    UseformPrint UseformPrint = EdataDAO.getUseformPrint(useformPrintId);
									Platform platform = new Platform();
									platform.setApp_processes_id(UseformPrint.getProcess_id());
									platform.setUseformPrint(UseformPrint);
									
									edata Edata = new edata();
									Edata.setData_id(data_id);
									platform.setEdata(Edata);
									
							 		LoadApp   LoadApp =   new LoadApp();
									platformService.initiate(LoadApp).loadPlatForm(platform)
									.data().register().promotion().paymentterms().load();
									
									Map<String, Object> data = platformService.getModelData();
								    model.addAllAttributes(data);
								    
									 FormPrint FormPrints = EdataDAO.getFormPrint(UseformPrint.getForm_id());
									    if(FormPrints != null) {
									    	
									    	System.out.println(" url = "+FormPrints.getForm_link());
									    	model.addAttribute("url",FormPrints.getForm_link());
									    }
										
								    
						    	}
				       	return "/app/sub/print_tool";
				    }
					
				    @RequestMapping(value="/calendar-filemanager/{process_id}",method = RequestMethod.GET)    
				    public String calendar(ModelMap model
				    		,@PathVariable int process_id
				    		,@RequestParam (required=false,name="edata_id",defaultValue="0") int edata_id
				    		,@RequestParam (required=false,name="index_id",defaultValue="0") String index_id
							,@RequestParam(required =false,defaultValue="0") int Tray 
				    		){ 
				    	
				    	
				    	
				       	int idInt =0;
				    	
				    	if (index_id.matches("\\d+")) {
		    		        // เป็นตัวเลข
		    		        idInt = Integer.parseInt(index_id); // แปลงได้ปลอดภัย
	
		    		    } else {
		    		        // เป็นข้อความ
		    		    	registers register = EdataDAO.getProfileByCode(index_id);
		    		        idInt = register.getId();
		    		    }
		    			
		    	
				    	
				    	
				    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
				    	
				    	if(idInt  > 0) {
				    		List<Calendar> Calendar = Upload_pnDAO.getAllCalendarBynameAndIndexid(users.getUser_name(),idInt);
					    	model.addAttribute("Calendar",Calendar);
				    	}else {
				    		List<Calendar> Calendar = Upload_pnDAO.getAllCalendarByname(users.getUser_name());
					    	model.addAttribute("Calendar",Calendar);
				    	}
				    	
				    	model.addAttribute("Tray",Tray);
				      	model.addAttribute("index_id",idInt);
				    	model.addAttribute("edata_id",edata_id);
				    	model.addAttribute("process_id",process_id);

				       	return "/app/windows/maincalendar";
				    }
				    
				    
				    
				    
				    
				    @RequestMapping(value="/allcalendar-filemanager",method = RequestMethod.GET)    
				    public String allcalendar(ModelMap model){ 
				    	model.addAttribute("process_id",99000);
				       	return "/app/windows/allmaincalendar";
				    }
				    
				    
				    
				    
				    
				    
				    
				    @RequestMapping(value="/delCalendar-filemanager",method = RequestMethod.POST)    
				    public String delCalendar(ModelMap model
							,@RequestParam  int  id
							,@RequestParam  int  process_id
							,@RequestParam (required=false,name="edata_id",defaultValue="0") int edata_id
							,@RequestParam (required=false,name="index_id",defaultValue="0") int index_id
							,@RequestParam(required =false,defaultValue="0") int Tray 
				    		){ 
				     Upload_pnDAO.deleteCalendar(id);
				       	return "redirect:/calendar-filemanager/"+process_id+"?index_id="+index_id+"&&Tray="+Tray;
				    }
				    
				    @RequestMapping(value="/saveCalendar-filemanager",method = RequestMethod.POST)    
				    public String saveCalendar(ModelMap model,	
					    	Calendar calendar 
							,@RequestParam (required=false,name="date",defaultValue="-") String date
							,@RequestParam  int  process_id
							,@RequestParam (required=false,name="edata_id",defaultValue="0") int edata_id
							,@RequestParam (required=false,name="index_id",defaultValue="0") int index_id
							,@RequestParam(required =false,defaultValue="0") int Tray 
				    		){ 
				    	
				    	user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
				    	
				    	if(!date.equals("-") ) {
				    		
				    	   	String[] parts = date.split("-");
				    	 // แปลงจาก String เป็น int เพื่อนำไปใช้งาน
				    	   	int year = Integer.parseInt(parts[0]);  // ได้ 2025
				    	   	int month = Integer.parseInt(parts[1]); // ได้ 1
				    	   	int day = Integer.parseInt(parts[2]);   // ได้ 11

				    	   	// นำไปตั้งค่าใน object calendar ของคุณ
	
				    	   	
				    	   	String To_name = "-";
				    	   	if(index_id > 0) {
				    	   		registers registers = EdataDAO.getRegister(index_id);
				    	   		EdataDAO.setEx20(date,index_id);
				    	   		
				    	   		if(registers != null) {   
				    	   			To_name = registers.getRegister_name();
				    	   		}
				    	   	}
				    	   	
				    	   	
				    	   	calendar.setYear(year);
				    	   	calendar.setMonth(month); 
				    	   	calendar.setDay(day);
							calendar.setEform_id(edata_id);
							calendar.setIndex_id(index_id);
							calendar.setTo_name(To_name);
							calendar.setBy_name(users.getUser_name());
							calendar.setBy_NameId(String.valueOf(users.getUser_id()));
							calendar.setStatus(0);
							EdataDAO.saveCalendar(calendar);
				    		
				    	}
				 
				    
				       	return "redirect:/calendar-filemanager/"+process_id+"?index_id="+index_id+"&&Tray="+Tray;
				    }
				    
				    
				    
				    @RequestMapping(value="/rowmapper-filemanager",method = RequestMethod.GET)    
				    public String rowmapper(ModelMap model){ 
				    	
				    	List<process> process = EdataDAO.getAllProcessBytype(0);
				    	model.addAttribute("process",process);
				       	return "/app/windows/rowmapper";
				    }
				    @RequestMapping(value="/transformdata-filemanager",method = RequestMethod.GET)    
				    public String transformdata(ModelMap model){ 
				    	
				    		
				       	return "/app/windows/transformdata";
				    }
				    
				    
				    @RequestMapping(value="/loadmodel-filemanager/{model}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")    
				    @ResponseBody // 🌟 สำคัญมาก: เพื่อให้คืนค่ากลับไปที่หน้าจอตรงๆ ไม่ไปหาไฟล์ View
				    public String loadmodel(@PathVariable String model) { 
				        
				        StringBuilder result = new StringBuilder();
				        
				        switch (model) {
				            case "Profile" -> {
				                edata_register registers = new edata_register();
				                // เรียกใช้ head() ที่คืนค่าเป็น List<String> หรือ String คั่นด้วยคอมม่า
				                List<String> cols = registers.head(); 
				                result.append(String.join(",", cols));
				            }
				            case "Product" -> {
				                edata_product registers = new edata_product();
				                // เรียกใช้ head() ที่คืนค่าเป็น List<String> หรือ String คั่นด้วยคอมม่า
				                List<String> cols = registers.head(); 
				                result.append(String.join(",", cols));
				            }
				            case "Extension" -> {
				            	String head ="id_refer,reference,reference_oldsys,data_dateadd,data_nameadds,data_value1,data_value2,data_value3,data_value4,data_value5"
				            			+ ",data_value6,data_value7,data_value8,data_value9,data_value10"
				            			+ ",data_value11,data_value12,data_value13,data_value14,data_value15,data_value16,data_value17,data_value18,data_value19,data_value20";
				                result.append(head);
				            }
				            case "WorkFlow" -> {
				            	String head ="eform_id,reference_oldsys,customer_oldsys,sale_oldsys,qttotal_oldsys,current_step,date,creator,sender,receiver,refer_user,date_update,closing_status"
				            			+ ",remarks,data_nameadd,data_namesend,data_namereceiver,current_step_int";
				                result.append(head);
				            }
				            case "Files" -> {
				            	String head ="file_indexapp,file_tag,file_name,file_name_original,file_type,file_size,file_size2,file_path_external";
				                result.append(head);
				            }
				            
		
				            default -> {
				                // 🌟 ท่าไม้ตาย: เช็คว่า String ที่ส่งมา เป็น "ตัวเลขล้วนๆ" ใช่หรือไม่?
				                if (model.matches("\\d+")) {
				                	try {
				                        process myprocess = EdataDAO.getProcess(Integer.valueOf(model));
				                        if (myprocess != null) {
				                            condition condition = ConditionDAO.getbyid(myprocess.getId_condition());
				                            
				                            if (condition != null) {
				                                List<eformfield> fieldList = EformfieldDAO.getall(condition.getCondition_id(), condition.getCondition_rv());
				                                
				                                if (fieldList != null && !fieldList.isEmpty()) {
				                                    // 1. ปั้นหัวคอลัมน์จาก Dynamic Form
				                                    String dynamicHead = fieldList.stream()
				                                            .map(eformfield::getField_label) 
				                                            .collect(Collectors.joining(","));
				                                    
				                                    // 🌟 2. ชุดเกราะ "เติมตูด" สำหรับแปลงค่า Backend
				                                    String tailColumns = "data_idadd,data_nameadd,id_oldsys,id_customer_oldsys,id_product_oldsys,id_refer_oldsys,data_title,sub_data_id,revision";
				                                    
				                                    // 3. เอาหัวตาราง + คอมม่า + ตูด มาประกอบร่างกัน!
				                                    result.append(dynamicHead).append(",").append(tailColumns);
				                                    
				                                    System.out.println("✅ Generated Dynamic Header (with OldSys Tails) Successfully!");
				                                    
				                                } else {
				                                    System.out.println("⚠️ Warning: No fields found for condition ID -> " + condition.getCondition_id());
				                                }
				                            }
				                        }
				                    } catch (Exception e) {
				                        System.out.println("❌ Error loading dynamic header for model: " + model);
				                        e.printStackTrace();
				                    }
				                } else {
				                    // ถ้าไม่ใช่ทั้งคำสั่งหลัก และไม่ใช่ตัวเลข (เช่น ส่งคำว่า "Test" มา)
				                    System.out.println("⚠️ Warning: Unknown model parameter -> " + model);
				                }
				            }
				        }
				        
				        return result.toString(); // พ่นชื่อคอลัมน์ออกมาที่หน้าจอเลย
				    }
				    
				    @RequestMapping(value="/formMassOperationsImport-filemanager/{type}/{process_id}", method = RequestMethod.GET) 
				    public String formMassOperationsImport(@PathVariable String  type,@PathVariable int  process_id,ModelMap model) {
				    	model.addAttribute("type",type);
			         	return "/app/windows/formMassOperationsImport";
				    }
				    
				    
				    @RequestMapping(value="/formMassOperationsExtensionImport-filemanager/{process_id}", method = RequestMethod.GET) 
				    public String formMassOperationsExtensionImport(@PathVariable int  process_id,ModelMap model) {
				    	
				    	Platform platform  = new Platform();
				    	platform.setApp_processes_id(process_id);
				    	LoadApp   LoadApp =   new LoadApp();
						platformService.initiate(LoadApp).startAppIndex(platform).requireExtension().load();
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
			
				    	
			         	return "/app/windows/formMassOperationsExtensionImport";
				    }
				    
				    @RequestMapping(value="/formMassOperationsSystemImport-filemanager/{process_id}", method = RequestMethod.GET) 
				    public String formMassOperationsSystemImport(@PathVariable int  process_id,ModelMap model) {
				    	
				    	Platform platform  = new Platform();
				    	platform.setApp_processes_id(process_id);
				    	LoadApp   LoadApp =   new LoadApp();
				    	
						platformService.initiate(LoadApp).startAppIndex(platform).requireExtension().load();
						Map<String, Object> data = platformService.getModelData();
						model.addAllAttributes(data);
			
				    	List<process> process_all = EdataDAO.getAllProcessBytype(0);
				    	model.addAttribute("process_all",process_all);
				     	model.addAttribute("process_id",process_id);
				    	
			         	return "/app/windows/formMassOperationsSystemImport";
				    }
				   
			
				    
				    @RequestMapping(value="/MassOperationsImport-filemanager/{models}", method = RequestMethod.POST) 
				    public String uploadCsvAndMapToModel(@PathVariable String models
				    		, @RequestParam("file") MultipartFile file
				    		,@RequestParam  (required=false,name="extension",defaultValue="0") int extension
				    		,@RequestParam  (required=false,name="process_id",defaultValue="0") int process_id
				    		,@RequestParam  (required=false,name="app_process",defaultValue="0") String app_process
				    		,ModelMap model) {
							        
				    	
				    			Platform platform = new Platform();
				    			platform.setApp_entity_type(models);
				    			
				    			platform.setFile(file);
								
						 		LoadApp   LoadApp =   new LoadApp();
				    			switch (models) {
				    				case "Profile" ->{
				    					platformService.initiate(LoadApp).startAppIndex(platform).MassOperationsImport().callback();
				    					Map<String, Object> data = platformService.getModelData();
						    			model.addAllAttributes(data);
				    				}
				    				case "Product" ->{
				    					platformService.initiate(LoadApp).startAppIndex(platform).MassOperationsImport().callback();
				    					Map<String, Object> data = platformService.getModelData();
						    			model.addAllAttributes(data);
				    				}
				    				case "Files" ->{
				    					platformService.initiate(LoadApp).startAppIndex(platform).MassOperationsFilesImport().callback();
				    					Map<String, Object> data = platformService.getModelData();
						    			model.addAllAttributes(data);
				    				}
				    				case "Extension" ->{
				    					platform.setApp_item_id(extension);		
				    					platform.setApp_processes_id(process_id);
				    					
				    					platformService.initiate(LoadApp).startAppIndex(platform).MassOperationsExtensionImport().callback();
				    					Map<String, Object> data = platformService.getModelData();
						    			model.addAllAttributes(data);
				    				}	
				    				case "Process" ->{
					    					if(app_process.equals("Workflow")) {
							    					platform.setApp_processes_id(process_id);
							    					platformService.initiate(LoadApp).appManagement(platform).MassOperationsWorkflowImport().callback();
							    					Map<String, Object> data = platformService.getModelData();
									    			model.addAllAttributes(data);
					    					}else {
					    						platform.setApp_processes_id(Integer.valueOf(app_process));
						    					platformService.initiate(LoadApp).appManagement(platform).MassOperationsProcessImport().callback();
						    					Map<String, Object> data = platformService.getModelData();
								    			model.addAllAttributes(data);
					    						
					    					}
				    				}	
				    				
				    			}
				    			
				    	
						        return "/app/windows/MassOperationsImport";
				 
				    }
				    
				    
		
				    
				    @RequestMapping(value="/listHelpDesk-filemanager/Profile/{item_id}/{process_id}", method = RequestMethod.GET)
				    public String listHelpDesk(ModelMap model, @PathVariable("item_id") int item_id, @PathVariable("process_id") int process_id) {
				        
				    	
				    	process process = EdataDAO.getProcess(process_id);
						model.addAttribute("process",process);
				    	
						long countOpen = helpDeskRepository.countByRefIdAndStatus(item_id, "OPEN");
				        model.addAttribute("countOpen", countOpen);
						
				        // ดึงรายการ Ticket ของลูกค้านี้
				        List<HelpDeskTicket> tickets = helpDeskRepository.findByRefIdOrderByIdDesc(item_id); // ต้องสร้าง method นี้ใน Repo
				        
				           for(HelpDeskTicket t : tickets) {
				               List<file> files = FileDAO.getallFile_ticketid(t.getId().intValue());
				               t.setAttachedFiles(files);
				           }
				        

				        model.addAttribute("tickets", tickets);
				        model.addAttribute("item_id", item_id);
				        model.addAttribute("process_id", process_id);
				        
				        return "app/listHelpDesk";
				    }
				    
				    

				    @RequestMapping(value="/HelpDesk-filemanager/Profile/{item_id}/{process_id}", method = RequestMethod.GET)
				    public String helpDeskForm(ModelMap model, 
				                               @PathVariable("item_id") int item_id, 
				                               @PathVariable("process_id") int process_id,
				                               @RequestParam(value="ticket_id", required=false) Long ticket_id) {

				    	
				    	process process = EdataDAO.getProcess(process_id);
						workflow work  = WorkflowDAO.getbyid(process.getId_refer());
						model.addAttribute("file_patch",work.getWorkflow_folder());
						model.addAttribute("process",process);
				    	
				        // 2.1 ดึง Dropdown
				        dataset dataset = DatasetDAO.getMain("Action_type");
				        List<dataset_data> actionType = Dataset_dataDAO.getall(dataset.getDataset_id());
				        model.addAttribute("Action_type", actionType);

				        // 2.2 ดึงข้อมูลลูกค้า (Header)
				        registers profile = EdataDAO.getRegister(item_id);
				        model.addAttribute("profile", profile);

				        // 2.3 ถ้าแก้ไข ให้ดึงข้อมูลเก่า
				        HelpDeskTicket ticket = new HelpDeskTicket();
				        if(ticket_id != null) {
				            ticket = helpDeskRepository.findById(ticket_id).orElse(new HelpDeskTicket());
				            
				            ticket.setAttachedFiles(FileDAO.getallFile_ticketid(ticket_id.intValue()));
				            
				            model.addAttribute("oldFiles", FileDAO.getallFile_ticketid(ticket_id.intValue()));
				        }
				        model.addAttribute("ticket", ticket);

				        model.addAttribute("process_id", process_id);
				        model.addAttribute("item_id", item_id);

				        return "app/HelpDeskFrom";
				    }
				    
				    
				    
				    
				    
				    @RequestMapping(value="/saveHelpDesk", method=RequestMethod.POST)
				    public String saveHelpDesk(ModelMap model,
				            @ModelAttribute HelpDeskTicket ticket, // รับค่า Form
				            @RequestParam(value = "files", required = false) CommonsMultipartFile[] files, // รับไฟล์
				            @RequestParam String folder, // รับค่า hidden
				            @RequestParam String file_tag, // รับค่า hidden
				            @RequestParam int process_id,
				            @RequestParam int item_id, // ref_id
				            HttpSession session) {
				        
				        try {
				            // 1. เตรียมข้อมูล User และ เวลา (Date/Time) ให้พร้อมก่อน
				            user users = UserDAO.getbyusername2(Userservice.getLoggedName());
				            
				            LocalDateTime localDate = LocalDateTime.now();
				            String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				            String time = localDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

				            // 2. จัดการ Ticket (แยกเคส เพิ่ม vs แก้ไข เพื่อไม่ให้ข้อมูลหาย)
				            HelpDeskTicket ticketToSave;
				            
				            if(ticket.getId() == null) {
				                // === กรณีเพิ่มใหม่ (NEW) ===
				                ticket.setCreatedBy(users.getUser_name());
				                ticket.setCreatedId(users.getUser_id());
				                ticket.setRefId(item_id); 
				                ticket.setStatus("OPEN");
				                ticket.setCreatedAt(localDate); // สมมติว่ามี field นี้
				                ticket.setProcessId(process_id);
				                ticketToSave = ticket;
				            } else {
				                // === กรณีแก้ไข (EDIT) ===
				                // ดึงข้อมูลเก่ามากันเหนียว
				                HelpDeskTicket oldTicket = helpDeskRepository.findById(ticket.getId()).orElse(null);
				                
				                if(oldTicket != null) {
				                    // อัปเดตเฉพาะค่าที่มาจากฟอร์ม (ค่าอื่นใช้ของเดิม)
				                    oldTicket.setRequestTopic(ticket.getRequestTopic());
				                    oldTicket.setDescription(ticket.getDescription());
				                    oldTicket.setPriority(ticket.getPriority());
				                    oldTicket.setProcessId(process_id);
				                    oldTicket.setUpdateId(users.getUser_id());
				                    oldTicket.setUpdateAt(localDate); // สมมติว่ามี field นี้
				                    
				                    // ถ้ามีส่ง status หรือ resultDocNo มาค่อยแก้
				                    if(ticket.getStatus() != null) oldTicket.setStatus(ticket.getStatus());
				                    if(ticket.getResultDocNo() != null) oldTicket.setResultDocNo(ticket.getResultDocNo());
				                    if(ticket.getAdminNote() != null) oldTicket.setAdminNote(ticket.getAdminNote());
				                    
				                    // *สำคัญ* ref_id, createdBy จะไม่ถูกทับเป็น null
				                    ticketToSave = oldTicket;
				                } else {
				                    // กรณีหาไม่เจอจริงๆ (fallback)
				                    ticket.setRefId(item_id);
				                    ticketToSave = ticket;
				                }
				            }
				            
				            // Save Ticket ลง DB
				            HelpDeskTicket savedTicket = helpDeskRepository.save(ticketToSave);
				            
				            // เอา ID มาใช้เป็น file_indexapp / file_ticketid
				            int file_ticketid = savedTicket.getId().intValue(); 

				            

				 		 DateTimeFormatter formatte3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				 		String datetime = localDate.format(formatte3);
		
				 		 
				 		   if(!ticketToSave.getStatus().equals("OPEN")) {
					  		String note = "เอกสาร   :  "+ticketToSave.getRequestTopic() +" RefId :"+ticketToSave.getRefId();
					  		note += "ระบบ  :  HelpDesk";
					  		note += "กระบวนการ  :  ขอเเก้ไขข้อมูล";
					 		note += "ผล  :  "+ticketToSave.getStatus();
					  		
					   	 		notification  notifications = new notification();
					 			notifications.setNotification_iduser(String.valueOf(ticketToSave.getCreatedId()));
								notifications.setNotification_alert("มีการตอบกับคำขอดำเนินการของคุณ");
								notifications.setNotification_date(datetime);
								notifications.setNotification_read(0);
								notifications.setNotification_note(note);
								notifications.setNotification_type("HelpDesk");
								notifications.setNotification_process_id(0);
								NotificationDAO.save(notifications);
				 		   }else {
				 		
							  		String note = "เอกสาร   :  "+ticketToSave.getRequestTopic() +" RefId :"+ticketToSave.getRefId();
							  		note += "ระบบ  :  HelpDesk";
							  		note += "กระบวนการ  :  ขอเเก้ไขข้อมูล";
							 		note += "ผล  :  "+ticketToSave.getStatus();
							  		
							   	 		notification  notifications = new notification();
							 			notifications.setNotification_iduser(String.valueOf(ticketToSave.getCreatedId()));
										notifications.setNotification_alert("ได้รับคำขอดำเนินการของคุณเเล้ว");
										notifications.setNotification_date(datetime);
										notifications.setNotification_read(0);
										notifications.setNotification_note(note);
										notifications.setNotification_type("HelpDesk");
										notifications.setNotification_process_id(0);
										NotificationDAO.save(notifications);
						 		
				 		   }
				            
				            // 3. Logic อัปโหลดไฟล์ (จัดเต็มตามเดิม)
				            if (files != null && files.length > 0) {
				                
				                System.out.println(" have files ");
				                
				                long imageFileSize_total = 0; 
				                for (CommonsMultipartFile file : files) { imageFileSize_total += file.getSize(); }

				                folder folders = FolderDAO.getbytype(folder); // folder="HelpDesk"
				                boolean diskspace = Filemanagerservice.CheckMemorySize(folders, imageFileSize_total);

				                if(!diskspace) {
				                    notification notifications = new notification();
				                    notifications.setNotification_alert("⚠ พื้นที่ดิสก์ไม่พอ!");
				                    notifications.setNotification_iduser(users.getUser_name()); // เพิ่มตาม Log
				                    notifications.setNotification_date(date + " " + time);
				                    NotificationDAO.save(notifications);  
				                    return "error"; 
				                }

				                // วนลูปบันทึกไฟล์
				                if (diskspace) {
				                    System.out.println(" have diskspace ");
				                    
				                    for (CommonsMultipartFile file : files) {
				                        if(file != null && file.getSize() > 0 ) {
				                            
				                        
				                            
				                  		  // 1. ล้างค่าโปรโตคอลออก (ให้เหลือแต่ D:/... หรือ C:/...)
				        				   	String cleanPath = storagePath.replace("file:///", ""); 

				        				   	// 2. ประกอบ Path กับประเภทโฟลเดอร์ (เช่น FolderA, user)
				        				   	String path = cleanPath +  folders.getFolder_type(); 
				                            
				                            
				                            String filename = Fileservice.assertEquals(file.getContentType()); 
				                            String originalName = file.getOriginalFilename();
				                            String saveName = originalName; // หรือจะ gen ชื่อใหม่ตรงนี้

				                            byte[] bytes = file.getBytes();  
				                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(  
				                                    new File(path + File.separator + saveName)));  
				                            stream.write(bytes);  
				                            stream.flush(); stream.close();  

				                            // 3.2 Save File ลง DB
				                            savefileDTO savefile = new savefileDTO();
				                            savefile.setFilename(saveName);
				                            savefile.setNewfilename(originalName);
				                            savefile.setFile_ticketid(file_ticketid); // <--- ID Ticket
				                            savefile.setFolder_id(folders.getFolder_id());
				                            savefile.setFolder_type(folder);
				                            savefile.setPathfull(path + "/" + saveName); 
				                            savefile.setDate(date);
				                            savefile.setUsername(users.getUser_name());
				                            savefile.setFile_tag(file_tag);
				                            savefile.setSize(file.getSize());
				                            savefile.setTypereal(file.getContentType());
				                            savefile.setSize_string(Fileservice.printFileSize(file.getSize()));
				                            
				                            System.out.println(" have save ");
				                            FileDAO.save(savefile);    

				                            // 3.3 Save Log (ใส่ Date/Time ครบ)
				                            log logs = new log();
				                            logs.setLog_id_add(users.getUser_id());
				                            logs.setLog_step("HelpDesk");
				                            logs.setLog_note("แนบไฟล์ HelpDesk #" + file_ticketid + " (" + originalName + ")");
				                            logs.setLog_refer(String.valueOf(file_ticketid));
				                            logs.setLog_date(date); // <--- ไม่ตัดแล้วครับ
				                            logs.setLog_time(time); // <--- ไม่ตัดแล้วครับ
				                            LogDAO.save(logs);
				                        }
				                    }
				                    // Update พื้นที่รวม
				                    Filemanagerservice.UpdateMemorySize(folders, imageFileSize_total, 1);
				                }
				            }

				            return "redirect:/listHelpDesk-filemanager/Profile/" + item_id + "/" + process_id;

				        } catch (Exception e) {
				            e.printStackTrace();
				            model.addAttribute("error", "Error Saving: " + e.getMessage());
				            return "error";
				        }
				    }

				 // เพิ่ม Method นี้ใน HelpDeskController
				    @RequestMapping(value="/deleteFileHelpDesk", method=RequestMethod.POST)
				    @ResponseBody // ส่ง Text กลับไปบอก JS ว่าเสร็จแล้ว ไม่ต้องเปลี่ยนหน้า
				    public String deleteFileHelpDesk(@RequestParam int file_id, HttpSession session) {
				        try {
				            // 1. ดึงข้อมูลไฟล์มาก่อน เพื่อหา path ลบไฟล์จริง
				            file f = FileDAO.getbyId(file_id); // สมมติว่าใน FileDAO มี method นี้
				            
				            if(f != null) {
				                // 2. ลบไฟล์จริงออกจาก Disk (ถ้าต้องการ)
				              String fullPath = f.getFile_path_full(); // หรือ path ที่คุณเก็บ
				                File physicalFile = new File(fullPath);
				                if(physicalFile.exists()) {
				                    physicalFile.delete();
				                }
				 

				                // 3. ลบ Record ออกจาก Database
				                FileDAO.delete(file_id); // สมมติว่า DAO มีฟังก์ชัน delete
				                LocalDateTime localDate = LocalDateTime.now();
				                String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				                String time = localDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
				                // (Optional) เก็บ Log การลบ
				                user users = UserDAO.getbyusername2(Userservice.getLoggedName());
				                log logs = new log();
				                logs.setLog_id_add(users.getUser_id());
				                logs.setLog_step("HelpDesk");
				                logs.setLog_note("ลบไฟล์ ID: " + file_id);
				                logs.setLog_date(date);
	                            logs.setLog_time(time);
				                LogDAO.save(logs);
				            }
				            
				            return "success";
				        } catch (Exception e) {
				            e.printStackTrace();
				            return "error";
				        }
				    }
				    
				    
				    
				    
				    
				    
				    
				    
				 // เพิ่ม Method นี้ใน HelpDeskController
				    @RequestMapping(value="/delHelpDesk-filemanager/{ticket_id}/{item_id}/{process_id}", method=RequestMethod.GET)
				    public String deleteHelpDesk(ModelMap model,
				                                 @PathVariable("ticket_id") Long ticket_id,
				                                 @PathVariable("item_id") int item_id,
				                                 @PathVariable("process_id") int process_id) {
				        try {
				            user users = UserDAO.getbyusername2(Userservice.getLoggedName());

				            // เตรียมเวลาสำหรับ Log
				            LocalDateTime localDate = LocalDateTime.now();
				            String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				            String time = localDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

				        
				            List<file> attachedFiles = FileDAO.getallFileIndexapp(ticket_id.intValue());
				            if(attachedFiles != null) {
				                for(file f : attachedFiles) {
				                     
				                     // --- จุดที่แก้: เช็คก่อนว่า Path ไม่เป็น Null ---
				                     if(f.getFile_path_full() != null && !f.getFile_path_full().isEmpty()) {
				                         try {
				                             File physicalFile = new File(f.getFile_path_full());
				                             if(physicalFile.exists()) {
				                                 physicalFile.delete(); // ลบไฟล์จริงในเครื่อง
				                             }
				                         } catch (Exception ex) {
				                             // ถ้าลบไฟล์จริงไม่ได้ (เช่นไฟล์ไม่อยู่แล้ว) ให้ปล่อยผ่าน
				                             System.out.println("Cannot delete physical file: " + ex.getMessage());
				                         }
				                     }
				                     
				                     // ลบ Record ใน Database (อันนี้ต้องลบเสมอ ไม่ว่าไฟล์จริงจะมีไหม)
				                     FileDAO.delete(f.getFile_id()); // หรือ method ลบของคุณ
				                }
				            }
				 

				            // 2. ลบตัว Ticket ออกจาก DB
				            helpDeskRepository.deleteById(ticket_id);

				            // 3. เก็บ Log การลบ
				            log logs = new log();
				            logs.setLog_id_add(users.getUser_id());
				            logs.setLog_step("HelpDesk");
				            logs.setLog_note("ลบรายการ HelpDesk ID: " + ticket_id);
				            logs.setLog_refer(String.valueOf(ticket_id));
				            logs.setLog_date(date); // วันที่
				            logs.setLog_time(time); // เวลา
				            LogDAO.save(logs);

				        } catch (Exception e) {
				            e.printStackTrace();
				            // ถ้า Error อาจจะ redirect ไปหน้า error หรือกลับหน้าเดิม
				        }

				        // 4. วนกลับไปหน้า List เดิม
				        return "redirect:/listHelpDesk-filemanager/Profile/" + item_id + "/" + process_id;
				    }
				    
				  
				    
				 // 1. หน้าแสดงการตั้งค่า (GET)
				    @RequestMapping(value="/Setting-filemanager", method=RequestMethod.GET)
				    public String Settingapp(ModelMap model) {
				        
				        user users = UserDAO.getbyusername2(Userservice.getLoggedName());
				        
				        // ดึง Setting ของ User
				        UserSetting setting = userSettingRepository.findByUserId(users.getUser_id());
				        if (setting == null) {
				            setting = new UserSetting();
				        }
				        model.addAttribute("setting", setting);

				        // ส่งรายชื่อรูปภาพพื้นหลังที่มีให้เลือก (System Wallpapers)
				        List<String> systemWallpapers = new ArrayList<>();
				        systemWallpapers.add("anniespratt.jpg");
				        systemWallpapers.add("boliviainteligente.jpg");
				        systemWallpapers.add("jejo_jose.jpg");
				        systemWallpapers.add("marsumilae.jpg");
				        
				        systemWallpapers.add("1.webp");
				        systemWallpapers.add("2.webp");
				        systemWallpapers.add("3.jpg");
				        systemWallpapers.add("4.jpg");
				        systemWallpapers.add("cat.webp");
				        
				        model.addAttribute("systemWallpapers", systemWallpapers);

				        return "app/settingapp";
				    }
				    
				    
				 // 2. บันทึกการตั้งค่า (POST)
				    @RequestMapping(value="/saveSettingApp", method=RequestMethod.POST)
				    public String saveSettingApp(
				            @RequestParam(value = "selectedBg", required = false) String selectedBg, // รับชื่อรูปที่เลือก
				            @RequestParam(value = "hiddenApps", required = false) String hiddenApps) { // รับ ID แอพที่ซ่อน
				        
				        try {
				            user users = UserDAO.getbyusername2(Userservice.getLoggedName());

				            // หา Setting เดิม หรือ สร้างใหม่
				            UserSetting setting = userSettingRepository.findByUserId(users.getUser_id());
				            if (setting == null) {
				                setting = new UserSetting();
				                setting.setUserId(users.getUser_id());
				            }

				            // 1. บันทึกรูปพื้นหลัง
				            // ถ้ามีการเลือกรูปใหม่มา ให้บันทึก Path
				            if (selectedBg != null && !selectedBg.isEmpty()) {
				                // บันทึกเป็น Path เต็ม เพื่อให้เรียกใช้ง่าย
				                setting.setBackgroundImage("/app/system/background/" + selectedBg);
				            }

				            // 2. บันทึกแอพที่ซ่อน
				            setting.setHiddenApps(hiddenApps);

				            userSettingRepository.save(setting);

				        } catch (Exception e) {
				            e.printStackTrace();
				            return "error";
				        }
				        return "redirect:/appcenter-filemanager";
				    }
				    
				    
					 @RequestMapping(value="/customer_insight-filemanager/{appid}",method = RequestMethod.GET)
						public String customer_insight(ModelMap model,@PathVariable int appid) { 	
									model.addAttribute("appid",appid); 
									
									// 1. ดึงข้อมูลลูกค้าตามเกรด
							        setCustomerGradeData(model);
							        
							        // 2. ดึงข้อมูลการเคลื่อนไหวเกรด (YTD)
							        setGradeMovementData(model);
							        
							     // 3. ความถี่การติดต่อ
							        setContactFrequencyData(model);
							        
							        // 4. Dashboard
							        setFoQtCustomerSummaryData(model);
									
							        
									user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
									SetSegmentView SegmentView = EdataDAO.getSetSegmentView(55,usersed);
									
								 	model.addAttribute("SegmentView",SegmentView); 
							        
					 				return "app/customer_insight";
						   }
					 
					 
					 private void setFoQtCustomerSummaryData(ModelMap model) {

						    // ** TODO: เปลี่ยน ID ให้ตรงกับระบบจริง **
						    int foDocumentId = 110;
						    int qtDocumentId = 113;
						    int contactExtId = 5;
						    int yearCount    = 5;

						    List<Map<String, Object>> rawList =
						            EdataDAO.getFoQtCustomerSummaryByGroupAndYear(foDocumentId, qtDocumentId, contactExtId, yearCount);

						    model.addAttribute("foQtSummaryList", rawList != null ? rawList : new ArrayList<>());
						}

					 private void setFoCustomerSummaryData(ModelMap model) {

						    // ** TODO: เปลี่ยน ID ให้ตรงกับระบบจริง **
						    int foDocumentId = 110;  // document_id ของ FO
						    int contactExtId = 5;    // extension_id ของการติดต่อลูกค้า
						    int yearCount    = 5;    // ย้อนหลัง 5 ปี

						    List<Map<String, Object>> rawList =
						            EdataDAO.getFoCustomerSummaryByGroupAndYear(foDocumentId, contactExtId, yearCount);

						    // โยน raw list ลง model → JSP วนลูปเอาเอง
						    model.addAttribute("foCustomerSummaryList", rawList != null ? rawList : new ArrayList<>());
						}

					 
					 private void setAgingAndDocsData(ModelMap model) {
					        // ** TODO: เปลี่ยน ID ให้ตรงกับระบบจริง **
					        int contactProcessId = 55; // Request action
					        int contactExtId = 5;
					        String contactDateCol = "data_value2";
					        int foProcessId = 58; // รหัส Process ของ FO
					        int qtProcessId = 60; // รหัส Process ของ QT

					        List<Map<String, Object>> agingList = EdataDAO.getCustomerAgingAndDocsSummary(contactProcessId, contactExtId, contactDateCol, foProcessId, qtProcessId);

					        // ตัวแปรเก็บยอด (C = Customer, F = FO, Q = QT)
					        int c3M = 0, f3M = 0, q3M = 0;
					        int c3_6M = 0, f3_6M = 0, q3_6M = 0;
					        int c6_9M = 0, f6_9M = 0, q6_9M = 0;
					        int c9_12M = 0, f9_12M = 0, q9_12M = 0;
					        int c24M = 0, f24M = 0, q24M = 0;

					        if (agingList != null) {
					            for (Map<String, Object> row : agingList) {
					                String bucket = String.valueOf(row.get("aging_bucket"));
					                int custCount = Integer.parseInt(row.get("total_customers").toString());
					                int foCount = Integer.parseInt(row.get("total_fo").toString());
					                int qtCount = Integer.parseInt(row.get("total_qt").toString());

					                switch (bucket) {
					                    case "<3M":   c3M = custCount; f3M = foCount; q3M = qtCount; break;
					                    case "3-6M":  c3_6M = custCount; f3_6M = foCount; q3_6M = qtCount; break;
					                    case "6-9M":  c6_9M = custCount; f6_9M = foCount; q6_9M = qtCount; break;
					                    case "9-12M": c9_12M = custCount; f9_12M = foCount; q9_12M = qtCount; break;
					                    case ">24M":  c24M = custCount; f24M = foCount; q24M = qtCount; break;
					                }
					            }
					        }

					        // โยนค่าลง Model
					        model.addAttribute("c3M", c3M); model.addAttribute("f3M", f3M); model.addAttribute("q3M", q3M);
					        model.addAttribute("c3_6M", c3_6M); model.addAttribute("f3_6M", f3_6M); model.addAttribute("q3_6M", q3_6M);
					        model.addAttribute("c6_9M", c6_9M); model.addAttribute("f6_9M", f6_9M); model.addAttribute("q6_9M", q6_9M);
					        model.addAttribute("c9_12M", c9_12M); model.addAttribute("f9_12M", f9_12M); model.addAttribute("q9_12M", q9_12M);
					        model.addAttribute("c24M", c24M); model.addAttribute("f24M", f24M); model.addAttribute("q24M", q24M);
					        
					        
					     // 🌟 1. หาผลรวมของลูกค้าทั้งหมดในนี้
					        int totalAgingCustomers = c3M + c3_6M + c6_9M + c9_12M + c24M;

					        // 🌟 2. คำนวณหา % เพื่อเอาไปทำความกว้างหลอด (เช็คหาร 0 กันเหนียว)
					        int p3M = totalAgingCustomers > 0 ? (int) Math.round((c3M * 100.0) / totalAgingCustomers) : 0;
					        int p3_6M = totalAgingCustomers > 0 ? (int) Math.round((c3_6M * 100.0) / totalAgingCustomers) : 0;
					        int p6_9M = totalAgingCustomers > 0 ? (int) Math.round((c6_9M * 100.0) / totalAgingCustomers) : 0;
					        int p9_12M = totalAgingCustomers > 0 ? (int) Math.round((c9_12M * 100.0) / totalAgingCustomers) : 0;
					        int p24M = totalAgingCustomers > 0 ? (int) Math.round((c24M * 100.0) / totalAgingCustomers) : 0;

					        // โยนค่าจำนวนลูกค้าและเอกสารลง Model (ของเดิม)
					        model.addAttribute("c3M", c3M+0); model.addAttribute("f3M", f3M); model.addAttribute("q3M", q3M);
					        model.addAttribute("c3_6M", c3_6M+0); model.addAttribute("f3_6M", f3_6M); model.addAttribute("q3_6M", q3_6M);
					        model.addAttribute("c6_9M", c6_9M+0); model.addAttribute("f6_9M", f6_9M); model.addAttribute("q6_9M", q6_9M);
					        model.addAttribute("c9_12M", c9_12M+0); model.addAttribute("f9_12M", f9_12M); model.addAttribute("q9_12M", q9_12M);
					        model.addAttribute("c24M", c24M+0); model.addAttribute("f24M", f24M); model.addAttribute("q24M", q24M);

					        // 🌟 3. โยนค่าเปอร์เซ็นต์ความกว้างหลอดลง Model ด้วย
					        model.addAttribute("p3M", p3M+0);
					        model.addAttribute("p3_6M", p3_6M+0);
					        model.addAttribute("p6_9M", p6_9M+0);
					        model.addAttribute("p9_12M", p9_12M+0);
					        model.addAttribute("p24M", p24M+0);
					    }
					 
					 
					 
					 
					 
					// =========================================================
					    // Helper Method สำหรับความถี่การติดต่อ
					    // =========================================================
					// =========================================================
					 private void setContactFrequencyData(ModelMap model) {
					     int processId = 55; 
					     int extensionId = 5; 
					     String dateColumn = "data_value2"; 

					     // 1. ดึงข้อมูลความถี่ (ลูกค้าทั้งหมด) - ส่ง false
					     List<Map<String, Object>> allFreqList = EdataDAO.getContactFrequencySummary(processId, extensionId, dateColumn, false);
					     calculateAndSetFrequency(model, allFreqList, "contact");

					     // 2. ดึงข้อมูลความถี่ (เฉพาะลูกค้าเก่า A, B, C) - ส่ง true
					     List<Map<String, Object>> oldFreqList = EdataDAO.getContactFrequencySummary(processId, extensionId, dateColumn, true);
					     calculateAndSetFrequency(model, oldFreqList, "oldContact");
					 }

					 // Method ย่อยสำหรับคำนวณและยัดใส่ ModelMap
					 private void calculateAndSetFrequency(ModelMap model, List<Map<String, Object>> freqList, String prefix) {
					     int count0 = 0, count1 = 0, count2 = 0, count3 = 0;
					     int totalCustomers = 0;
					     int totalContacts = 0;

					     if (freqList != null) {
					         for (Map<String, Object> row : freqList) {
					             String group = String.valueOf(row.get("frequency_group"));
					             int customers = Integer.parseInt(row.get("total_customers").toString());
					             int exactContacts = Integer.parseInt(row.get("exact_contacts").toString());
					             
					             totalCustomers += customers;
					             totalContacts += exactContacts;

					             switch (group) {
					                 case "0": count0 = customers; break;
					                 case "1": count1 = customers; break;
					                 case "2": count2 = customers; break;
					                 case "3": count3 = customers; break;
					             }
					         }
					     }

					     double avg = 0.0;
					     if (totalCustomers > 0) {
					         avg = (double) totalContacts / totalCustomers;
					     }
					     String avgStr = String.format("%.1f", avg);

					     // โยนค่าให้หน้าจอ JSP ตาม prefix ที่ส่งมา (เช่น contact0 หรือ oldContact0)
					     model.addAttribute(prefix + "0", count0);
					     model.addAttribute(prefix + "1", count1);
					     model.addAttribute(prefix + "2", count2);
					     model.addAttribute(prefix + "3", count3);
					     model.addAttribute(prefix + "Avg", avgStr);
					 }




					// =========================================================
					    // Helper Method สำหรับการปรับเกรด (YTD)
					    // =========================================================
					    private void setGradeMovementData(ModelMap model) {
					        List<Map<String, Object>> movementList = EdataDAO.getGradeMovementSummary();
					        
					        int gradeUp = 0;
					        int gradeDown = 0;

					        if (movementList != null) {
					            for (Map<String, Object> row : movementList) {
					                if (row.get("movement_type") != null) {
					                    String type = row.get("movement_type").toString();
					                    int count = Integer.parseInt(row.get("total_count").toString());
					                    
					                    if ("UP".equals(type)) {
					                        gradeUp = count;
					                    } else if ("DOWN".equals(type)) {
					                        gradeDown = count;
					                    }
					                }
					            }
					        }

					        // โยนค่าให้หน้าจอ JSP
					        model.addAttribute("gradeUp", gradeUp);
					        model.addAttribute("gradeDown", gradeDown);
					    }





					    @RequestMapping(value="/customer_insightRaw-filemanager/{process_id}/{mode}", method = RequestMethod.GET)
					    public String customer_insightRaw_Dynamic(
					            ModelMap model,
					            @PathVariable int process_id,
					            @PathVariable String mode
					            ) { 
					        
					        // 0. จำลอง Object registers สำหรับตั้งเงื่อนไข
					        registers register = new registers();
					        register.setRegister_type("ลูกค้า"); // ล็อค Base Condition ไว้ว่าเป็นลูกค้า
					        
					        // ---------------------------------------------------------
					        // จัดการเงื่อนไขตาม {mode} ที่ส่งมาจากหน้าจอ
					        // ---------------------------------------------------------
					        switch (mode.toUpperCase()) {
					            // --- กลุ่มที่ 1: ค้นหาตามเกรด A, B, C, NEW, NONE ---
					            case "A":  
					            case "B":
					            case "C":
					            case "NEW":
					            case "NONE":
					                register.setRegister_grade(mode);
					                break;

					            // --- กลุ่มที่ 2: เกรดขึ้น / ลง (Register Processing) ---
					            case "GRADE_UP":
					            case "GRADE_DOWN":
					            	register.setRegister_grade(mode);
					                register.setSearchMode(mode); 
					                register.setExtension_column_19("SHOW_COLUMN"); 
					                break;

					            // --- กลุ่มที่ 3: ความถี่การติดต่อ (Request action) ---
					            case "CONTACT_0":
					            case "CONTACT_1":
					            case "CONTACT_2":
					            case "CONTACT_3":
					            case "OLD_CONTACT_0":
					            case "OLD_CONTACT_1":
					            case "OLD_CONTACT_2":
					            case "OLD_CONTACT_3":
					    	        // =========================================================
					            	register.setReqProcessId("55");          // edata.process_id ที่ส่งมา
					            	register.setReqExtId("5");                  // edata.extension_id ที่ส่งมา
					            	register.setReqDateCol ("data_value2");       // ช่องที่เก็บวันที่ติดต่อ (ไว้เช็คว่าคุยปีนี้)
					            	register.setSearchMode(mode); 
					                register.setExtension_column_1("SHOW_COLUMN");
					                break;
					            case "AGING_3M":
					            case "AGING_3_6M":
					            case "AGING_6_9M":
					            case "AGING_9_12M":
					            case "AGING_24M":
					                register.setSearchMode(mode); 
					                // ยืมช่อง 21 ไว้โชว์ข้อความ "FO:3 | QT:2"
					                register.setExtension_column_19("SHOW_COLUMN"); 
					                break;     
					            // --- กลุ่มที่ 4: แสดงทั้งหมด (ไม่ต้อง set อะไรเพิ่ม) ---
					            case "ALL":
					                break;

					            default:
					                break;
					        }

					        // =========================================================
					        // 1. ค้นหาข้อมูล Data (ตัวแปร register ถูกยัดไส้เงื่อนไขมาเรียบร้อยแล้ว)
					        // =========================================================
					        FullResult exampleData = EdataDAO.searchRegistersDirect(register, 500, 1);
					        model.addAttribute("exampleData", exampleData.getRegisters());
					        
					        // 2. ดึง Process และ Listview
					        process process = EdataDAO.getProcess(process_id);
					        model.addAttribute("process", process);
					        model.addAttribute("registerType", "Profile");
					        
					        String listview = process.getListview(); 
					        List<String> listviewIds = new ArrayList<>();
					        if (listview != null && !listview.isEmpty()) {
					            listviewIds = Arrays.asList(listview.split(","));
					        }
					        
					        // 3. ดึง Heads ทั้งหมดมา
					        List<registerkey> heads = EdataDAO.getAllRegisterkey(3, "register");
					        List<registerkey> filteredHeads = new ArrayList<>();
					        
					        // 4. BeanWrapper คัดกรองคอลัมน์
					        BeanWrapper registerWrapper = PropertyAccessorFactory.forBeanPropertyAccess(register);
					        
					        for (registerkey head : heads) {
					            String headIdStr = String.valueOf(head.getId());
					            if (listviewIds.contains(headIdStr)) {
					                filteredHeads.add(head);
					                continue; 
					            }
					            
					            String mainKey = head.getMainkey(); 
					            if (mainKey != null && !mainKey.isEmpty() && registerWrapper.isReadableProperty(mainKey)) {
					                Object value = registerWrapper.getPropertyValue(mainKey); 
					                if (value != null) {
					                    if (value instanceof String && !((String) value).trim().isEmpty()) {
					                        filteredHeads.add(head);
					                    } 
					                    else if (value instanceof List && !((List<?>) value).isEmpty()) {
					                        filteredHeads.add(head);
					                    }
					                    else if (value.getClass().isArray() && java.lang.reflect.Array.getLength(value) > 0) {
					                        filteredHeads.add(head);
					                    }
					                }
					            }
					        }
					        
					        if (mode != null && mode.toUpperCase().startsWith("CONTACT_")) {
					            registerkey commHead = new registerkey();
					            commHead.setMainkey("extension_column_1"); // อ้างอิงฟิลด์ที่จะรับค่ามาแสดง
					            commHead.setTitle("ประวัติการสื่อสาร"); // ชื่อหัวคอลัมน์ที่จะโชว์บนจอ
					            filteredHeads.add(commHead);
					        }
					        if (mode != null && mode.toUpperCase().startsWith("AGING_")) {
					            registerkey docHead = new registerkey();
					            docHead.setMainkey("extension_column_19"); 
					            docHead.setTitle("สรุปเอกสาร (FO/QT)"); 
					            filteredHeads.add(docHead);
					        }
					        if (mode != null && mode.toUpperCase().startsWith("GRADE_")) {
					            registerkey docHead = new registerkey();
					            docHead.setMainkey("extension_column_19"); 
					            docHead.setTitle("การปรับเกรด"); 
					            filteredHeads.add(docHead);
					        }
					        // 5. ส่งเฉพาะ filteredHeads กลับไปที่หน้าจอ Data List
					        model.addAttribute("heads", filteredHeads);
					        
					        return "app/windows/indexapp_data";
					    }


					private void setCustomerGradeData(ModelMap model) {
						// 1. เรียก Method จาก edataDAO
					    List<Map<String, Object>> gradeList = EdataDAO.getCustomerGradeSummary(); 

					    // 2. ประกาศตัวแปรเก็บยอดรวมแต่ละเกรด
					    int totalCustomers = 0;
					    int gradeA = 0, gradeB = 0, gradeC = 0, gradeNew = 0, gradeNone = 0;

					    // 3. วนลูปเพื่อแยก Data เก็บเข้าตัวแปร
					    if (gradeList != null) {
					        for (Map<String, Object> row : gradeList) {
					            // ดึงชื่อเกรดมาทำเป็นตัวใหญ่ป้องกัน error
					            String grade = String.valueOf(row.get("grade")).toUpperCase();
					            int count = Integer.parseInt(row.get("total_count").toString());
					            
					            totalCustomers += count; // บวกยอดทั้งหมด
					            
					            switch (grade) {
					                case "A": gradeA = count; break;
					                case "B": gradeB = count; break;
					                case "C": gradeC = count; break;
					                case "NEW": gradeNew = count; break;
					                case "NONE": gradeNone = count; break;
					            }
					        }
					    }
					    // 4. โยนตัวแปรทั้งหมดเข้า ModelMap เพื่อไปแสดงผลใน JSP
					    model.addAttribute("totalCustomers", totalCustomers);
					    model.addAttribute("gradeA", gradeA);
					    model.addAttribute("gradeB", gradeB);
					    model.addAttribute("gradeC", gradeC);
					    model.addAttribute("gradeNew", gradeNew);
					    model.addAttribute("gradeNone", gradeNone);
						
					}
					
					
					
					
					
					
					
					
					// ===============================================================
					// 📦 Controller.java — เพิ่ม endpoint นี้
					// รับ: /customer_insightRaw-filemanager/{appid}/FO_EXPORT?group=XX&year=XXXX&aging=XX
					// ส่งออก: CSV file
					// ===============================================================
					 
					@RequestMapping(value = "/customer_insightRaw-filemanager/{appid}/FO_EXPORT", method = RequestMethod.GET)
					public void foCustomerExport(
					        HttpServletResponse response,
					        @PathVariable int appid,
					        @RequestParam(value = "group",  defaultValue = "") String group,
					        @RequestParam(value = "year",   defaultValue = "0") int year,
					        @RequestParam(value = "aging",  defaultValue = ">24M") String aging) {
					 
					    // ** TODO: ปรับให้ตรงระบบจริง **
					    int foDocumentId = 110;
					    int contactExtId = 5;
					 
					    // -------------------------------------------------------
					    // 1. ดึงข้อมูล
					    // -------------------------------------------------------
					    List<Map<String, Object>> dataList =
					            EdataDAO.getFoCustomerDetailForExport(group, year, aging, foDocumentId, contactExtId);
					 
					    // -------------------------------------------------------
					    // 2. ตั้งค่า HTTP Response เป็น CSV
					    // -------------------------------------------------------
					    response.setContentType("text/csv; charset=UTF-8");
					 
					    // ชื่อไฟล์ : FO_กลุ่ม_ปี_aging.csv
					    String safeGroup = group.replaceAll("[^a-zA-Z0-9ก-๙]", "_");
					    String filename  = "FO_" + safeGroup + "_" + year + "_" + aging.replace("<", "lt").replace(">", "gt") + ".csv";
					    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					 
					    // BOM สำหรับ Excel เปิดภาษาไทยได้ถูกต้อง
					    try {
					        response.getOutputStream().write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
					 
					    // -------------------------------------------------------
					    // 3. เขียน CSV
					    // -------------------------------------------------------
					    try (PrintWriter writer = new PrintWriter(
					            new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8))) {
					 
					        // Header
					        writer.println("code,name,group,status,history");
					 
					        // Rows
					        if (dataList != null) {
					            for (Map<String, Object> row : dataList) {
					                writer.println(
					                    csvEscape(row.get("code"))    + "," +
					                    csvEscape(row.get("name"))    + "," +
					                    csvEscape(row.get("group"))   + "," +
					                    csvEscape(row.get("status"))  + "," +
					                    csvEscape(row.get("history"))
					                );
					            }
					        }
					 
					        writer.flush();
					 
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
					}
					 
					// -------------------------------------------------------
					// Helper: ครอบด้วย "" และ escape " ใน CSV
					// -------------------------------------------------------
					private String csvEscape(Object value) {
					    if (value == null) return "";
					    String s = value.toString().replace("\"", "\"\""); // escape double quote
					    return "\"" + s + "\"";
					}			
					
			
					
					@RequestMapping(value = "/addcalllog", method = RequestMethod.POST)
					public String addCallLog(
					        @RequestParam String phone_number,
					        @RequestParam String employee_name,
					        @RequestParam String direction,
					        @RequestParam String start_time,
					        @RequestParam String end_time,
					        @RequestParam long   duration_seconds
					) {
					    try {
					        int idRefer = EdataDAO.findIdByPhone(phone_number);

					        
					        if(idRefer > 0) {
					            EdataDAO.insertCallLog(
							            employee_name,
							            phone_number,
							            duration_seconds,
							            start_time,
							            idRefer
							        );
					        	
					        }
					  

					        return "ok";

					    } catch (Exception e) {
					        System.err.println("addCallLog error: " + e.getMessage());
					        return "error: " + e.getMessage();
					    }
					}
}









