package com.smepro.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lowagie.text.DocumentException;
import com.smepro.config.mainconfig.SessionListener;
import com.smepro.dao.attendanceDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eflowDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.settingDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dto.memberDTO;
import com.smepro.models.Log_login;
import com.smepro.models.dar;
import com.smepro.models.darsum;
import com.smepro.models.datapdf;
import com.smepro.models.dataset;
import com.smepro.models.docexpire;
import com.smepro.models.drives;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.log;
import com.smepro.models.notification;
import com.smepro.models.purchase;
import com.smepro.models.registerkey;
import com.smepro.models.report_pn;
import com.smepro.models.report_pn_pdf;
import com.smepro.models.setting;
import com.smepro.models.showlog;
import com.smepro.models.showupload_pn;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.models.attendance;
import com.smepro.models.condition;
import com.smepro.service.MainService;
import com.smepro.service.PDFExporter;
import com.smepro.service.PathManager;
import com.smepro.service.dateservice;
import com.smepro.service.eformservice;
import com.smepro.service.fileservice;
import com.smepro.service.otherservice;
import com.smepro.service.qrcodeservice;
import com.smepro.service.userservice;
import com.smepro.service.workflowservice;


@SessionAttributes({"Member_name","SESSION-COUNTER","Member_id","Auth"})
@Controller
public class logoutcontroller {
	
	
	@Autowired
	logDAO LogDAO;
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	userservice Userservice;
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	groupsDAO GroupsDAO;
	
	@Autowired
	drivesDAO DrivesDAO;
	
	@Autowired
	folderDAO FolderDAO;
	
	@Autowired
	fileservice Fileservice;
	
	@Autowired
	dateservice dateservices;
	
	
	@Autowired
	notificationDAO NotificationDAO;
	
	@Autowired
	report_pnDAO Report_pnDAO;
	
	@Autowired
	darDAO DarDAO;
	
	@Autowired
	upload_pnDAO Upload_pnDAO;
	
	@Autowired
	conditionDAO ConditionDAO;
	
	
	@Autowired
	qrcodeservice qrcodeservices;
	
	@Autowired
	attendanceDAO AttendanceDAO;
	
	@Autowired
	workflowDAO WorkflowDAO;
	
	
	@Autowired
	edataDAO EdataDAO;
	
	@Autowired
	datasetDAO DatasetDAO;
	
	
	@Autowired
	settingDAO SettingDAO;
	
	memberDTO memberDTOs = new  memberDTO();
	
	
	private  taskDAO TaskDAO;
	
	private  MainService MainServices;
	
	
    @Autowired
    public void setMyRepository(conditionDAO ConditionDAO,eformfieldDAO EformfieldDAO
    		,otherservice otherservices,workflowDAO WorkflowDAO,eflowDAO EflowDAO
    		,edataDAO EdataDAO,userDAO UserDAO,userservice Userservice,notificationDAO NotificationDAO
    		,attendanceDAO AttendanceDAO, upload_pnDAO Upload_pnDAO
    		,eformservice Eformservice
    		,workflowservice Workflowservice
    		,fileDAO FileDAO
    		,taskDAO TaskDAO
    		,MainService MainServices
    		){
        this.ConditionDAO = ConditionDAO;
        this.WorkflowDAO = WorkflowDAO;
        this.EdataDAO = EdataDAO;
        this.UserDAO = UserDAO;
        this.Userservice = Userservice;
        this.NotificationDAO = NotificationDAO;
        this.AttendanceDAO = AttendanceDAO;
        this.Upload_pnDAO = Upload_pnDAO;
        this.FileDAO = FileDAO;
        this.TaskDAO = TaskDAO;
        this.MainServices =MainServices; 
        
    }
	
      
    
    
    




	private void System_idoc(ModelMap model) {
		
/*
	 	user users = UserDAO.getbyusername2(Userservice.getLoggedName());   
		List<workflow> workflows = WorkflowDAO.getallApp();
    	model.addAttribute("workflow",workflows); 
    	
    	
    	List<condition> conditions = ConditionDAO.getallGroup();
    	List<showupload_pn> upload_pns = Upload_pnDAO.getallby(users.getUser_name());
    	
    	
    	
    	
       	List<purchase> allpr =  EdataDAO.getPurchaseAllForClear();
	     final int datapr =allpr.size();

	 	System.out.println("datapr "+datapr);

    	model.addAttribute("conditions",conditions); 
    	AtomicInteger i = new AtomicInteger(1);
    	List<edata> edatas = EdataDAO.getCountStatus(users.getUser_id());
    	
    	
  
    	
    	
    	
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
    				  
    			
    				   int prize =  0;
	    			   if(jobs.getCondition_titlerefer().equals("purchase_order")) {
	    				   prize =datapr;  
	    			   }
    				  
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

	@RequestMapping(value = "/keep_alive", method = RequestMethod.GET)
	public String keep_alive() {
		return "ajax/save";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response ,@RequestParam(value = "language",required=false,defaultValue="th") String  language ,log logs) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
	
		if (auth != null) {
			 user users = UserDAO.getbyusername2(Userservice.getLoggedName());    
			 
			if(users != null) { 
			 LocalDateTime localDate = LocalDateTime.now();//For reference
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	    	 String date = localDate.format(formatter);
	    	 String time = localDate.format(formatter2);
			
	    	 	log log_old = LogDAO.getLotOld(users.getUser_id());
	    	   	if(log_old != null) {
	    	   		logs.setLog_datestart(log_old.getLog_date());
	    	   		logs.setLog_timestart(log_old.getLog_time());
	    	   	}
			logs.setLog_id_add(users.getUser_id());
			logs.setLog_step("LOGOUT");
			logs.setLog_date(date);
			logs.setLog_time(time);
			logs.setLog_note(null);
			logs.setLog_refer(null);
			logs.setLog_folder(null);
			
			
			LogDAO.save(logs);
			memberDTOs.setId(0);
			}
			
			new SecurityContextLogoutHandler().logout(request, response, auth);
			request.getSession().invalidate();

	        // ลบ Remember-Me Cookie (หากมี)
	        Cookie rememberMeCookie = new Cookie("remember-me", null);
	        rememberMeCookie.setPath("/");
	        rememberMeCookie.setMaxAge(0); // ตั้งให้หมดอายุทันที
	        response.addCookie(rememberMeCookie);
			
			
		}
		
		
		
		
		
		
		return "redirect:/login?language="+ language;
	}

	
	@RequestMapping(value="/")
	public String homepages(ModelMap model, Authentication authentication ,log logs,HttpSession session) throws Exception {
		
		

	
		 System_idoc(model);
		
		 user users = UserDAO.getbyusername2(Userservice.getLoggedName());   
		 if(memberDTOs.getId() == 0) { 
		 
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
	   	 String time = localDate.format(formatter2);
	   	 
	   	 
	   	log log_old = LogDAO.getLotOld(users.getUser_id());
	   	
	   	if(log_old != null) {
	   		logs.setLog_datestart(log_old.getLog_date());
	   		logs.setLog_timestart(log_old.getLog_time());
	   	}
	   	
	   	
	   	
		logs.setLog_id_add(users.getUser_id());
		logs.setLog_step("LOGIN");
		logs.setLog_date(date);
		logs.setLog_time(time);
		logs.setLog_note(null);
		logs.setLog_refer(null);
		logs.setLog_folder(null);
		LogDAO.save(logs);
		 }
		memberDTOs.setId(users.getUser_id());
		int QM = FileDAO.getsumfile("Quality Manual (QM)");
		int QP = FileDAO.getsumfile("Quality Procedure (QP)");
		int WI = FileDAO.getsumfile("Work Instruction (WI)");
		int SD = FileDAO.getsumfile("Supporting Data (SD)");
		int FO = FileDAO.getsumfile("Form (FO)");
		int Conditionid =ConditionDAO.numactivate();
		FO+=Conditionid;
		
		int usertotal = UserDAO.getsum();
		
		//SessionListener counter = (SessionListener) session.getAttribute("SESSION-COUNTER");
		//int usertotal = counter.getActiveSessionNumber();
		
		int usergroup = GroupsDAO.getsum();
		drives drives = DrivesDAO.getsumuse();
	
		folder folders = FolderDAO.getsumuse();
		
		
		String Size3 ="-";
		
		long total_space = 0;
		long total_use = 0;
		long total_remain = 0;
		 File disk = new File(System.getProperty("user.dir"));
	  	 long freeSpace = disk.getFreeSpace();
	 	long diffs = 0;
		double usagePercent =0.0;
		
		
		List<drives> drivesed = DrivesDAO.getall();    
		for(drives drived : drivesed) {
			total_use = total_use+ drived.getDrives_use();
			total_space = total_space+drived.getDrives_size();
			total_remain = total_remain+drived.getDrives_remain();
		}
		
		if(total_remain > freeSpace) {
			diffs = total_remain -freeSpace;
			Size3 = Fileservice.printFileSize(diffs);
			
			
			usagePercent = (diffs * 100.0) / total_remain;
			int diffsp = (int)usagePercent;
			
			  model.addAttribute("usagePercent",diffsp); 
		}
	
		
		
		
		
		String Size = Fileservice.printFileSize(drives.getDrives_size());
		String Size2 = Fileservice.printFileSize(drives.getDrives_use());
		
		
		  long imageFileSize3 = drives.getDrives_use();
		    double percent;
		    if(imageFileSize3 > 0 && drives.getDrives_size() > 0) {
		       percent =  imageFileSize3 * 100 / drives.getDrives_size();
		    }else {
		    	 percent =0;	
		    }
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
		    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDateTime today = LocalDateTime.now();//For reference


		 
		 
		 
		 
		    
		LocalDateTime todays = LocalDateTime.now();//For reference
		LocalDateTime localDate = LocalDateTime.now().minusDays(7);
		LocalDateTime localTime = LocalDateTime.now().minusMinutes(30);
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
	    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
	    String datess = todays.format(formatters);
	    String datess2 = localDate.format(formatters);
	    String Time = localTime.format(formatterTime);
 
	    
	    
	    DateTimeFormatter formatters2 = DateTimeFormatter.ofPattern("yyyy");
	    DateTimeFormatter formatters3 = DateTimeFormatter.ofPattern("MM");
	    String year = todays.format(formatters2);
	    String month = todays.format(formatters3);

	
	    //Chart Radar
	    Date startDate = Format.parse(datess);
   	    Calendar start = Calendar.getInstance();
   	    start.setTime(startDate);
	    
	    
	    
	    report_pn  RadarD = Report_pnDAO.getallRadarD(year,0,datess);
	    report_pn  RadarW = Report_pnDAO.getallRadarW(year,1,start.WEEK_OF_YEAR);
	    report_pn  RadarM = Report_pnDAO.getallRadarM(year,2,month);
	    report_pn  RadarY = Report_pnDAO.getallRadarY(year);
	    
	    
	    model.addAttribute("RadarD",RadarD);
	    model.addAttribute("RadarW",RadarW); 
	    model.addAttribute("RadarM",RadarM);
	    model.addAttribute("RadarY",RadarY);
	    
	    
	    int online = LogDAO.getuseronline(datess,Time);
	    model.addAttribute("online",online);
        int Sumlog = 0;
       /*
       
       List<log> timeavg = LogDAO.gettimeavg(datess);
        ArrayList<String> timestampsList = new ArrayList<String>();
        ArrayList<String> Logids = new ArrayList<String>();
        
        for(log row : timeavg) {
        	 timestampsList.add(row.getLog_note());
        	 
        	 if(row.getLog_datestart().equals(row.getLog_dateend())) {
        		 Logids.add(String.valueOf(row.getLog_id_add()));
        	 }
        	
        	 
    		System.out.println("time " + row.getLog_datestart()+" "+row.getLog_time()+"-"+row.getLog_dateend()+" "+row.getLog_refer()+" time diff " + row.getLog_note()+" id " + row.getLog_id_add());
         }
        
       
        
	    List<showlog>  logpn = LogDAO.getalldate(datess,"UPLOAD");
	    
		List<showlog> logs1 =  LogDAO.getall2();

	
		
		
		
		int seconds = 0;
		long tm = 0;
		String timeString = "00:00:00";
        for (String tmp : timestampsList){
        	
        	try {
        	LocalTime time = LocalTime.parse(tmp);
        	
        	System.out.println(time.toSecondOfDay());
        	

        	seconds = seconds + time.toSecondOfDay();
       
        	
            
        	}catch(Exception e) {}
        }
      
        
   
        Long  usersize = Logids.stream().distinct().count();
        
        if(seconds > 0) {
        //int totalseconds =  seconds / usersize.intValue();
        int totalseconds =  seconds / timestampsList.size();
        int hours = totalseconds / 3600;
        int minutes = (totalseconds % 3600) / 60;
        int secondss = totalseconds % 60;
        timeString = String.format("%02d:%02d:%02d", hours, minutes, secondss);
        }
   */
	    LocalDateTime todaylast = LocalDateTime.now();//For reference
	    String datestart = today.format(formatter2);
	    String dateend = todaylast.format(formatter2);

	    
		List<report_pn>  reportC = Report_pnDAO.getallreportchart(datess2,datess);
		

	
		
	
		List<folder> foldersuse = FolderDAO.getuse();
		
		
		int expiredqm = FileDAO.getexpired("Quality Manual (QM)");
		int expiredqp = FileDAO.getexpired("Quality Procedure (QP)");
		int expiredwi = FileDAO.getexpired("Work Instruction (WI)");
		int expiredsd = FileDAO.getexpired("Supporting Data (SD)");
		int expiredfo = FileDAO.getexpired("Form (FO)");
		
		//System.out.println("QM" + expiredqm);
		
		List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		

		model.addAttribute("foldersuse",foldersuse);
		
		model.addAttribute("expiredqm",expiredqm);
		model.addAttribute("expiredqp",expiredqp);
		model.addAttribute("expiredwi",expiredwi);
		model.addAttribute("expiredsd",expiredsd);
		model.addAttribute("expiredfo",expiredfo);
		model.addAttribute("reportC",reportC);
		//model.addAttribute("logpn",logpn);
		

	
   
		//model.addAttribute("log", logs1);    
		model.addAttribute("Member_id",users.getUser_id());
        model.addAttribute("drives_percent", percent);    
		model.addAttribute("drives_use", Size2);
		model.addAttribute("drives_sysuse", Size3);
		model.addAttribute("drives_total", Size);
		model.addAttribute("usergroup", usergroup);
		model.addAttribute("usertotal", usertotal);
		model.addAttribute("doctotal_qm", QM);
		model.addAttribute("doctotal_qp", QP);
		model.addAttribute("doctotal_wi", WI);
		model.addAttribute("doctotal_sd", SD);
		model.addAttribute("doctotal_fo", FO);
		//model.addAttribute("doctotal",timeString);
		model.addAttribute("Member_name",users.getUser_name());
		
		
		
		 List<file> files = FileDAO.getallbydateold(datess);
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
    		     
	    		  	log log_old = LogDAO.getLotOld(users.getUser_id());
	    		   	if(log_old != null) {
	    		   		logs.setLog_datestart(log_old.getLog_date());
	    		   		logs.setLog_timestart(log_old.getLog_time());
	    		   	} 
	    		     
    	  			logs.setLog_id_add(2);
        			logs.setLog_step("DELETE");
        			logs.setLog_date(datess);
        			logs.setLog_time("00:00");
        			logs.setLog_note("ทำการลบไฟล์ที่หมดอายุชื่อไฟล์ "+delfile.getFile_name_original()+" วันหมดอายุ "+delfile.getFile_dateend());
        			logs.setLog_refer(null);
        			logs.setLog_folder(null);
        			LogDAO.save(logs);
    		 }
		
		
  
    		 
    		  List<dar> darunfinished = DarDAO.getalltotalunfinished();	 
    		  model.addAttribute("darunfinished",darunfinished); 
    		  
    		  
      		  int darrequest1 = DarDAO.gettotalbytype(year,"request1");
      		  int darrequest2 = DarDAO.gettotalbytype(year,"request2");
      		  int darrequest3 = DarDAO.gettotalbytype(year,"request3");
      		  int darrequest4 = DarDAO.gettotalbytype(year,"request4");
      		  
      		  
    		  model.addAttribute("darrequest1",darrequest1);
    		  model.addAttribute("darrequest2",darrequest2);
    		  model.addAttribute("darrequest3",darrequest3); 
    		  model.addAttribute("darrequest4",darrequest4);
    		 
    		  List<showupload_pn> userupload = Upload_pnDAO.useruploadjob();	 
    		  model.addAttribute("userupload",userupload); 
    		  List<showupload_pn> userapp = Upload_pnDAO.userappjob();	 
    		  model.addAttribute("userapp",userapp); 
    		  
	
    		
    		  
    		  
		return "index";
     
	}
	
	@RequestMapping(value="/document")
	public String document(ModelMap model, Authentication authentication ,log logs,HttpSession session) {

		
		
		
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
	    int dartotal = DarDAO.gettotal();
	    int dartotal_unfinished = DarDAO.gettotalunfinished();
	   
	    
		LocalDateTime todays = LocalDateTime.now();//For reference
		LocalDateTime todays2 = LocalDateTime.now().minusYears(1);//For reference
		LocalDateTime todays3 = LocalDateTime.now().minusYears(2);//For reference
		LocalDateTime todays4 = LocalDateTime.now().plusYears(1);//For reference
		LocalDateTime todays5 = LocalDateTime.now().plusYears(2);//For reference
		
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter formatters2 = DateTimeFormatter.ofPattern("MM");
	    DateTimeFormatter formatters3 = DateTimeFormatter.ofPattern("yyyy");
	    String datess = todays.format(formatters);
	    String months = todays.format(formatters2);
	    String years = todays.format(formatters3);
	    String years2 = todays2.format(formatters3);
	    String years3 = todays3.format(formatters3);
	    String years4 = todays4.format(formatters3);
	    String years5 = todays5.format(formatters3);
	    
	    List<log> maxs = LogDAO.getuseronreadfilemaxD(datess);
	    List<log> maxs2 = LogDAO.getuseronreadfilemaxM(months,years);
	    List<log> maxs3 = LogDAO.getuseronreadfilemaxY(years);
	    
	    List<log> mins= LogDAO.getuseronreadfileminD(datess);
	    List<log> mins2= LogDAO.getuseronreadfileminM(months,years);
	    List<log> mins3= LogDAO.getuseronreadfileminY(years);
	    
	    
		String dates[] = new String[10];
		String total[] = new String[10];
		String total2[] = new String[10];
		String total3[] = new String[10];
		String total4[] = new String[10];
		String target[] = new String[10];
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDateTime today = LocalDateTime.now();//For reference
		
		  String datetoday = today.format(formatter2);
	    
	    model.addAttribute("target", target);   
		model.addAttribute("total", total); 

		 for(int i = 0;i<10;i++) {
			 
				
			  String date2 = today.format(formatter);
			  String date3 = today.format(formatter2);
			  report_pn report_pns = Report_pnDAO.getsum(date3);
			  dates[i] = date2;
			
			  total[i] =Integer.toString(report_pns.getReport_pn_total());
			  
			  //ส่งไม่ตรง
			  total2[i]=Integer.toString(report_pns.getReport_pn_sendstatus() - report_pns.getReport_pn_statussccess());
			
			 // จำนวนบันทึกที่ส่งตามเวลา
			  total3[i]=Integer.toString(report_pns.getReport_pn_statussccess() );
			  
			  //  จำนวนบันทึกที่ขาดส่ง
			  total4[i]=Integer.toString(report_pns.getReport_pn_target() - report_pns.getReport_pn_sendstatus());
			  target[i] =Integer.toString(report_pns.getReport_pn_target());

			 // System.out.println(date2);
			  today =  today.minusDays(1);
		 }   
			model.addAttribute("total2", total2); 
			model.addAttribute("total3", total3); 
			model.addAttribute("total4", total4); 
			model.addAttribute("date", dates);

	    
		
			
			
		List<user>  user_login =UserDAO.getRandom();
		user_login.stream().forEach(row->{
			
			List<showlog> timeavgday = LogDAO.gettimebyleveldate(datetoday,row.getUser_id());
			row.setLogDay(timeavgday);
			
			List<showlog> timeavgmonth = LogDAO.gettimebylevelmonth(years,months,row.getUser_id());
			row.setLogMonth(timeavgmonth);
			
			List<showlog> timeavgyear = LogDAO.gettimebylevelyear(years,row.getUser_id());
			row.setLogYear(timeavgyear);
			
		});
		model.addAttribute("user_login", user_login); 

	    
	    int doc= LogDAO.numuseronreadfiledownload(months,years);

        int darmonth1 = DarDAO.getmaindar(1,years);    
        int darmonth2 = DarDAO.getmaindar(2,years);  
        int darmonth3 = DarDAO.getmaindar(3,years);  
        int darmonth4 = DarDAO.getmaindar(4,years);  
        int darmonth5 = DarDAO.getmaindar(5,years);  
        int darmonth6 = DarDAO.getmaindar(6,years);  
        int darmonth7 = DarDAO.getmaindar(7,years);  
        int darmonth8 = DarDAO.getmaindar(8,years);  
        int darmonth9 = DarDAO.getmaindar(9,years);  
        int darmonth10 = DarDAO.getmaindar(10,years);  
        int darmonth11 = DarDAO.getmaindar(11,years);  
        int darmonth12 = DarDAO.getmaindar(12,years);  
	    
        darsum  subdarrequest1 = DarDAO.getsubdar("request1",years);   
        darsum  subdarrequest2 = DarDAO.getsubdar("request2",years);   
        darsum  subdarrequest3 = DarDAO.getsubdar("request3",years);   
        darsum  subdarrequest4 = DarDAO.getsubdar("request4",years);   
        
        int daryear1 = DarDAO.getmaindar(years);    
        int daryear2 = DarDAO.getmaindar(years2);  
        int daryear3 = DarDAO.getmaindar(years3);  
        
        darsum  subdarrequests1 = DarDAO.getsubdar("request1",years,years2,years3);   
        darsum  subdarrequests2 = DarDAO.getsubdar("request2",years,years2,years3);   
        darsum  subdarrequests3 = DarDAO.getsubdar("request3",years,years2,years3);   
        darsum  subdarrequests4 = DarDAO.getsubdar("request4",years,years2,years3);   
        
        
        
        
        docexpire docyear1 = FileDAO.docexpireyear(years);
        docexpire docyear2 = FileDAO.docexpireyear(years4);
        docexpire docyear3 = FileDAO.docexpireyear(years5);
        
        
        
        //Top 5 Delays
		List<report_pn_pdf> sendstatus = Report_pnDAO.getreport_pn_sendstatus(months,years);
		model.addAttribute("sendstatus",sendstatus);
		List<report_pn_pdf> sendstatus2 = Report_pnDAO.getreport_pn_sendstatus(years);
		model.addAttribute("sendstatus2",sendstatus2);
		

		
		//Top 5 Reports Missing Submission
		List<report_pn_pdf> statuserror = Report_pnDAO.getreport_pn_statuserror(months,years);
		model.addAttribute("statuserror",statuserror);
		List<report_pn_pdf> statuserror2 = Report_pnDAO.getreport_pn_statuserror(years);
		model.addAttribute("statuserror2",statuserror2);
        
		
		
        model.addAttribute("docyear1",docyear1);
        model.addAttribute("docyear2",docyear2);
        model.addAttribute("docyear3",docyear3);

        model.addAttribute("request1",subdarrequest1);
        model.addAttribute("request2",subdarrequest2);
        model.addAttribute("request3",subdarrequest3);
        model.addAttribute("request4",subdarrequest4);
        
        model.addAttribute("requests1",subdarrequests1);
        model.addAttribute("requests2",subdarrequests2);
        model.addAttribute("requests3",subdarrequests3);
        model.addAttribute("requests4",subdarrequests4);
        
        model.addAttribute("daryear1",daryear1);
        model.addAttribute("daryear2",daryear2);
        model.addAttribute("daryear3",daryear3);
        
        model.addAttribute("years",years);
        model.addAttribute("years2",years2);
        model.addAttribute("years3",years3);
        model.addAttribute("years4",years4);
        model.addAttribute("years5",years5);
        
        
        model.addAttribute("darmonth1",darmonth1);
        model.addAttribute("darmonth2",darmonth2);
        model.addAttribute("darmonth3",darmonth3);
        model.addAttribute("darmonth4",darmonth4);
        model.addAttribute("darmonth5",darmonth5);
        model.addAttribute("darmonth6",darmonth6);
        model.addAttribute("darmonth7",darmonth7);
        model.addAttribute("darmonth8",darmonth8);
        model.addAttribute("darmonth9",darmonth9);
        model.addAttribute("darmonth10",darmonth10);
        model.addAttribute("darmonth11",darmonth11);
        model.addAttribute("darmonth12",darmonth12);

        
        
        
	    model.addAttribute("datenow",datess);
	 
	
	    
	    model.addAttribute("doc",doc);

	    model.addAttribute("maxs",maxs);
	    model.addAttribute("maxs2",maxs2);
	    model.addAttribute("maxs3",maxs3);
	    
	    model.addAttribute("mins",mins);
	    model.addAttribute("mins2",mins2);
	    model.addAttribute("mins3",mins3);
	    
	    
	    model.addAttribute("dartotal",dartotal);
	    model.addAttribute("dartotal_unfinished",dartotal_unfinished);
	    

	   Log_login pieday1 = LogDAO.getLoginDate(datess,"06:00","09:00");
	   Log_login pieday2 = LogDAO.getLoginDate(datess,"09:01","11:00");
	   Log_login pieday3 = LogDAO.getLoginDate(datess,"11:01","13:00");
	   Log_login pieday4 = LogDAO.getLoginDate(datess,"13:01","15:00");
	   Log_login pieday5 = LogDAO.getLoginDate(datess,"05:01","18:00"); 
	   
	
	   Log_login piemonth1 = LogDAO.getLoginMonth(months,years,"06:00","09:00");
	   Log_login piemonth2 = LogDAO.getLoginMonth(months,years,"09:01","11:00");
	   Log_login piemonth3 = LogDAO.getLoginMonth(months,years,"11:01","13:00");
	   Log_login piemonth4 = LogDAO.getLoginMonth(months,years,"13:01","15:00");
	   Log_login piemonth5 = LogDAO.getLoginMonth(months,years,"15:01","18:00"); 
	   
	   
	   Log_login pieyear1 = LogDAO.getLoginYear(years,"06:00","09:00");
	   Log_login pieyear2 = LogDAO.getLoginYear(years,"09:01","11:00");
	   Log_login pieyear3 = LogDAO.getLoginYear(years,"11:01","13:00");
	   Log_login pieyear4 = LogDAO.getLoginYear(years,"13:01","15:00");
	   Log_login pieyear5 = LogDAO.getLoginYear(years,"15:01","18:00"); 
	
	   
	    model.addAttribute("pieday1",pieday1);
	    model.addAttribute("pieday2",pieday2);
	    model.addAttribute("pieday3",pieday3);
	    model.addAttribute("pieday4",pieday4);
	    model.addAttribute("pieday5",pieday5);
	    
	    
	    model.addAttribute("piemonth1",piemonth1);
	    model.addAttribute("piemonth2",piemonth2);
	    model.addAttribute("piemonth3",piemonth3);
	    model.addAttribute("piemonth4",piemonth4);
	    model.addAttribute("piemonth5",piemonth5);

	    
	    model.addAttribute("pieyear1",pieyear1);
	    model.addAttribute("pieyear2",pieyear2);
	    model.addAttribute("pieyear3",pieyear3);
	    model.addAttribute("pieyear4",pieyear4);
	    model.addAttribute("pieyear5",pieyear5); 
	    
	     
	    
	    LocalDateTime newtoday = LocalDateTime.now();//For reference
	    String nows = newtoday.format(formatter2);
	    String nows2 = newtoday.format(formatters2);
	    String nows3 = newtoday.format(formatters3);
	    report_pn now_report_pns = Report_pnDAO.getsum(nows);
	    report_pn now_report_pns2 = Report_pnDAO.getsummonth(nows2,nows3);
	    report_pn now_report_pns3 = Report_pnDAO.getsumyear(nows3);
	    
	    
	    model.addAttribute("now_percent",0);
	    if(now_report_pns.getReport_pn_target() > 0) {
	    int now_percent =  (now_report_pns.getReport_pn_sendstatus() * 100 ) / now_report_pns.getReport_pn_target();
	    model.addAttribute("now_percent",now_percent);
	    }
	    
	    
	    model.addAttribute("now_percent2",0);
	    if(now_report_pns2.getReport_pn_target() > 0) {
	    int now_percent2 =  (now_report_pns2.getReport_pn_sendstatus() * 100 ) / now_report_pns2.getReport_pn_target();
	    model.addAttribute("now_percent2",now_percent2);
	    }
	    
	    
	    model.addAttribute("now_percent3",0);
	    if(now_report_pns3.getReport_pn_target() > 0) {
	    int now_percent3 =  (now_report_pns3.getReport_pn_sendstatus() * 100 ) / now_report_pns3.getReport_pn_target();
	    model.addAttribute("now_percent3",now_percent3);
	    }
	    
	    

	    
	    int countlevel1  =  UserDAO.countlevel("Labour");
	    int countlevel2  =  UserDAO.countlevel("Seniour Labour");
	    int countlevel3  =  UserDAO.countlevel("Supervisor");
	    int countlevel4  =  UserDAO.countlevel("Manager");
	    int countlevel5  =  UserDAO.countlevel("Director");
	    model.addAttribute("countlevel1",countlevel1);
	    model.addAttribute("countlevel2",countlevel2);
	    model.addAttribute("countlevel3",countlevel3);
	    model.addAttribute("countlevel4",countlevel4);
	    model.addAttribute("countlevel5",countlevel5);
	  
	    
	    
	    
	    int loglevel1  =  LogDAO.countlevel(years,"Labour");
	    int loglevel2  =  LogDAO.countlevel(years,"Seniour Labour");
	    int loglevel3  =  LogDAO.countlevel(years,"Supervisor");
	    int loglevel4  =  LogDAO.countlevel(years,"Manager");
	    int loglevel5  =  LogDAO.countlevel(years,"Director");
	    model.addAttribute("loglevel1",loglevel1);
	    model.addAttribute("loglevel2",loglevel2);
	    model.addAttribute("loglevel3",loglevel3);
	    model.addAttribute("loglevel4",loglevel4);
	    model.addAttribute("loglevel5",loglevel5);
	    
	    
	    
		return "index2";
		
	}
	
	


	
	
	
	@RequestMapping(value="/listreport")
	public String listreport(ModelMap model) {
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference	 
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
	   	 String date = localDate.format(formatter);
	   	 int year = Integer.parseInt(date);

	   	user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
			int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);
	   	 
	   	 
	   	model.addAttribute("year",year);
	   	model.addAttribute("year1",year-1);
	   	model.addAttribute("year2",year-2);
		return "listreport";
	}
	
	@RequestMapping(value="/export/pdf/month/{month}")
	@ResponseBody
	public void exportToPDFmonth(HttpServletResponse response, @PathVariable String month) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        
    	LocalDateTime todays = LocalDateTime.now();
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
	    String year = todays.format(formatters);


      
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Data_"+month+ ".pdf";
        response.setHeader(headerKey, headerValue);
        
        
        String header ="ผู้รับผิดชอบ,เป้าหมาย,อัพโหลด,วันที่บกพร่อง,ตรวจสอบล่าช้า,ที่ขาดการอัพโหลด";
        String title ="รายงานการขาดส่งรายงานประจำเดือน :  "+month+" ปี "+year;
		List<report_pn_pdf> report_pns = Report_pnDAO.getreporttopdf(month,year);
		List<datapdf> Listdata =  new ArrayList<>();

		System.out.println(report_pns);   
		
		for(report_pn_pdf data : report_pns) {
			
			datapdf  dataraw = new datapdf();	
			dataraw.setData1(data.getUser_name());
			dataraw.setData2(String.valueOf(data.getReport_pn_target()));
			dataraw.setData3(String.valueOf(data.getReport_pn_total()));
			dataraw.setData4(String.valueOf(data.getReport_pn_statuserror()));
			dataraw.setData5(String.valueOf(data.getReport_pn_appstatus()));
			dataraw.setData6(String.valueOf(data.getReport_pn_sendstatus()));
			Listdata.add(dataraw);
		}
		

	
        PDFExporter exporter = new PDFExporter(Listdata,header,title);
        exporter.export(response);
        
        
        
  
	}	
		
	@RequestMapping(value="/export/pdf/year/{year}")
	@ResponseBody
	public void exportToPDFyear(HttpServletResponse response, @PathVariable String year) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        



      
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Data_" +year+ ".pdf";
        response.setHeader(headerKey, headerValue);
        
        
        String header ="ผู้รับผิดชอบ,เป้าหมาย,อัพโหลด,วันที่บกพร่อง,ตรวจสอบล่าช้า,ที่ขาดการอัพโหลด";
        String title ="รายงานการขาดส่งรายงานประจำ :  "+year;
		List<report_pn_pdf> report_pns = Report_pnDAO.getreporttopdf(year);
		List<datapdf> Listdata =  new ArrayList<>();

		System.out.println(report_pns);   
		
		for(report_pn_pdf data : report_pns) {
			
			datapdf  dataraw = new datapdf();	
			dataraw.setData1(data.getUser_name());
			dataraw.setData2(String.valueOf(data.getReport_pn_target()));
			dataraw.setData3(String.valueOf(data.getReport_pn_total()));
			dataraw.setData4(String.valueOf(data.getReport_pn_statuserror()));
			dataraw.setData5(String.valueOf(data.getReport_pn_appstatus()));
			dataraw.setData6(String.valueOf(data.getReport_pn_sendstatus()));
			Listdata.add(dataraw);
		}
		

        
		
	    PDFExporter exporter = new PDFExporter(Listdata,header,title);
        exporter.export(response);
        
        
        
  
	}
		
	
	@RequestMapping(value="/Attendance")
	public String Attendance(HttpServletResponse response,ModelMap model
			,@CookieValue(value = "Auth", defaultValue = "error") String Auth 
			,@RequestParam(required = false) String error
			,@RequestParam(required = false) String lat
			,@RequestParam(required = false) String lon
			) 
					throws DocumentException, IOException, WriterException {
		
		
		System.out.println("Auth .."+Auth);

		if(Auth != null  && !Auth.equals("error")) {
			
			LocalDateTime localDate = LocalDateTime.now();//For reference
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		    String date = localDate.format(formatter);
		 	 String time = localDate.format(formatter2);
			
			
		    user users = UserDAO.getbyusername(Auth);    
			String token = qrcodeservices.generateToken(Auth);
			//String link = "http://tracejob.trueddns.com:52891/inout-Attendance?token="+ token+"&&lat="+lat+"&&lon="+lon;
			String link = "https://tracejob.trueddns.com:52891/inout-Attendance?token="+ token+"&&lat="+lat+"&&lon="+lon;
			System.out.println(link);

			int width = 300;
			int height = 300;
			BitMatrix bitMatrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
			String qrCodeBytes = Base64.getEncoder().encodeToString(outputStream.toByteArray());
			
			
			attendance attendances = AttendanceDAO.getbyId(users.getUser_id(),date);
			List<attendance> list_attendances = AttendanceDAO.getall(users.getUser_id());
			
			
			model.addAttribute("attendances", attendances);
			model.addAttribute("list_attendances", list_attendances);
		    model.addAttribute("qrCodeBytes", qrCodeBytes);
		    model.addAttribute("users", users);
		    model.addAttribute("lat", lat);
		    model.addAttribute("lon", lon);
		    model.addAttribute("date", date+" " +time);
		   
			
		}
		
	   

		// สร้าง QR code
		//BitMatrix bitMatrix = new MultiFormatWriter().encode(link, BarcodeFormat.QR_CODE, width, height);
		//ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		//MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
	    //byte[] qrCodeBytes = outputStream.toByteArray();
		//response.setContentType("image/png");
		//response.getOutputStream().write(qrCodeBytes);

		return "attendance";
		
	}
	
	
	@RequestMapping(value="/SetAuth-Attendance/{Auth}")
	public String SetAuth(@PathVariable String Auth,ModelMap model,HttpServletResponse response) {
		
	
	    user users = UserDAO.getbyAuth(Auth);   
	    
	    if(users != null) {
			Cookie auth = new Cookie("Auth",users.getUsername());
			auth.setMaxAge(3600*24);
			auth.setSecure(true);
			auth.setHttpOnly(true);
			auth.setPath("/"); 
			response.addCookie(auth);
			
			return "redirect:/Attendance";
	    }else {
	    	
	    	return "redirect:/Attendance?error=error";
	    }
	    
	    
		
	}
	
	
	@RequestMapping(value="/inouts-Attendance",method = RequestMethod.POST)
	public String inouts(@RequestParam String username
			,HttpServletRequest request
			,@RequestParam String lat
			,@RequestParam String lon
			,@RequestParam(required = false) CommonsMultipartFile file
			 ,HttpSession session
			) throws IOException {
		
		
		

		    LocalDateTime localDate = LocalDateTime.now();//For reference
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	    	 String date = localDate.format(formatter);
	    	 String time = localDate.format(formatter2);
	    	 String ip = qrcodeservices.getClientIpAddress(request);
		    user users = UserDAO.getbyusername(username);    
		    attendance Attendances = AttendanceDAO.getbyId(users.getUser_id(),date);

		    
			String UPLOAD_DIRECTORY ="/filesuploads/Attendance"; 
		    ServletContext context = session.getServletContext();  
		    String path = context.getRealPath(UPLOAD_DIRECTORY);  
		    String filename = Fileservice.assertEquals("image/jpg");

		    
	
		    
		    if(filename != null) {
		    	    
		 	    byte[] bytes = file.getBytes();  
		 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
		 	         new File(path + File.separator + filename)));  
		 	    stream.write(bytes);  
		 	    stream.flush();  
		 	    stream.close();  
		    }
		     
	    
		    
		    attendance attendance = new attendance();
		    attendance.setAttendance_checkin(time);
		    attendance.setAttendance_date(date);
		    attendance.setAttendance_ip(ip);
		    attendance.setAttendance_lat(lat);
		    attendance.setAttendance_lon(lon);
		    attendance.setAttendance_userid(users.getUser_id());
		    attendance.setAttendance_file(filename);
		    
		    
		    
		    String paths;
		   	File fileToDelete;
		   	boolean success;
		    ServletContext contexts = session.getServletContext(); 

		    

		
		    
		    
		    if(Attendances == null ) {
		    	AttendanceDAO.checkin(attendance);
		    }else {
		    	
		   	 if(Attendances.getAttendance_file() != null) {
				   	paths = contexts.getRealPath("/filesuploads/Attendance/" + Attendances.getAttendance_file()); 
					     fileToDelete = new File(paths + File.separator + Attendances.getAttendance_file());
					     success = fileToDelete.delete();
			 }
		    	
		    	
		    	 attendance.setAttendance_checkout(time);
		    	 attendance.setAttendance_file2(filename);
		    	 attendance.setAttendance_id(Attendances.getAttendance_id());
		    	AttendanceDAO.checkout(attendance);
		    }
		   
		    
	
		
		    return "redirect:/Attendance?lat="+lat+"&&lon="+lon;
	}
	
	

	@RequestMapping(value="/Unset-Attendance")
	public String Unset(ModelMap model,@CookieValue(value = "Auth", defaultValue = "error") String Auth,HttpServletResponse response) {
		
		
		if(Auth != null && !Auth.equals("error")) {
			Cookie auth = new Cookie("Auth",Auth);
			auth.setMaxAge(0);  // หรือตั้งค่าเป็นติดลบ
			auth.setSecure(true);
			auth.setHttpOnly(true);
			auth.setPath("/");
			response.addCookie(auth);
			
		}
		
	    return "redirect:/Attendance";
		
	}
	@RequestMapping(value="/Unck-Attendance")
	public String Unck(ModelMap model
			,@CookieValue(value = "Auth", defaultValue = "error") String Auth
			,HttpSession session
			) {
		
		
		if(Auth != null && !Auth.equals("error")) {
			 LocalDateTime localDate = LocalDateTime.now();
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		   	 String date = localDate.format(formatter);
		     user users = UserDAO.getbyusername(Auth);    
		  
		     attendance Attendance = AttendanceDAO.getbyId(users.getUser_id(),date);
		     
		     
		     String paths;
			   	File fileToDelete;
			   	boolean success;
			    ServletContext contexts = session.getServletContext(); 

			    System.out.println("Attendance" + Attendance.getAttendance_file());

				 if(Attendance.getAttendance_file() != null) {
					   	paths = contexts.getRealPath("/filesuploads/Attendance/"+Attendance.getAttendance_file()); 
					  
						     fileToDelete = new File(paths);
						     success = fileToDelete.delete();
				 }
				 if(Attendance.getAttendance_file2() != null) {
					   	paths = contexts.getRealPath("/filesuploads/Attendance/"+Attendance.getAttendance_file2()); 
						     fileToDelete = new File(paths);
						     success = fileToDelete.delete();
				 }
				 
				 
			AttendanceDAO.delete(users.getUser_id(),date);
	
		}
			return "redirect:/Attendance";
		
	}
	
	@RequestMapping(value="/inout-Attendance")
	public String SetAttendance(@RequestParam String token
			,HttpServletRequest request
			,@RequestParam String lat
			,@RequestParam String lon) {
		
		
		if (!qrcodeservices.isTokenExpired(token)) {
			
		    LocalDateTime localDate = LocalDateTime.now();//For reference
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	    	 String date = localDate.format(formatter);
	    	 String time = localDate.format(formatter2);
	    	   String ip = qrcodeservices.getClientIpAddress(request);

		    String username = qrcodeservices.getUsernameFromToken(token);
		    user users = UserDAO.getbyusername(username);    
		    
		
		    
		    attendance Attendances = AttendanceDAO.getbyId(users.getUser_id(),date);

		    
		    attendance attendance = new attendance();
		    attendance.setAttendance_checkin(time);
		    attendance.setAttendance_date(date);
		    attendance.setAttendance_ip(ip);
		    attendance.setAttendance_lat(lat);
		    attendance.setAttendance_lon(lon);
		    attendance.setAttendance_userid(users.getUser_id());
		    
		    
		    if(Attendances == null ) {
		    	AttendanceDAO.checkin(attendance);
		    }else {
		    	
		    	 attendance.setAttendance_checkout(time);
		    	 attendance.setAttendance_id(Attendances.getAttendance_id());
		    	AttendanceDAO.checkout(attendance);
		    }
		   
		    
		} 
		
		 return "saveattendance";
	}
	
		@RequestMapping(value="/update")
		public String update() {
			System.err.print("Update .....");
			
			
			
			 String  sqls = " ALTER TABLE setting ADD COLUMN version INT";
			 EdataDAO.CreateTable(sqls);
			
			 sqls = " Update setting set version = 1";
			 EdataDAO.CreateTable(sqls);
			 
			 
			 
			 
			setting setting  = SettingDAO.getbyid();
			int version =setting.getVersion();
			
			
			if(version  == 1) {
				
				System.err.print("Update version 2.....");
				String sql = "CREATE TABLE edata_stock ("
		                    + "stock_id INT PRIMARY KEY AUTO_INCREMENT, "
		                    + "edata_id INT, "
		                    + "stock_list VARCHAR(100), "
		                    + "stock_note VARCHAR(100), "
		                    + "stock_unit VARCHAR(100), "
		                    + "stock_type VARCHAR(100), "
						   + "stock_product VARCHAR(100), "
						   + "stock_date VARCHAR(100), "
						   + "stock_pd VARCHAR(100), "
						   + "stock_batch VARCHAR(100), "
						   + "stock_lot VARCHAR(100), "
						   + "stock_serial_number VARCHAR(100), "
						   + "stock_status VARCHAR(100), "
						   + "stock_received VARCHAR(100), "
						   + "stock_stockid INT, "
						    + "stock_max VARCHAR(100)," 
						    + "stock_min VARCHAR(100)," 
						    + "stock_expdate VARCHAR(100)," 
						    + "stock_support_dataflow TEXT," 
		                    + "stock_storage VARCHAR(100) )";
				EdataDAO.CreateTable(sql);
				

				


				sql = "CREATE TABLE ir ("
	                    + "ir_id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "ir_date VARCHAR(100),"
	                    + "ir_idrefer INT )";
			
			   EdataDAO.CreateTable(sql);
			   
			   
			   
				sql = "CREATE TABLE dgr ("
	                    + "dgr_id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "dgr_date VARCHAR(100),"
	                    + "dgr_idrefer INT )";
			
			   EdataDAO.CreateTable(sql);
			   
			   
				sql = "CREATE TABLE sr ("
	                    + "sr_id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "sr_date VARCHAR(100),"
	                    + "sr_idrefer INT )";
			
			   EdataDAO.CreateTable(sql);
			   
			   
			    sql = "CREATE TABLE stock ("
					    + "id INT PRIMARY KEY AUTO_INCREMENT, "
					    + "stock_id INT INT, "
					    + "stock_details VARCHAR(255), "
					    + "stock_remaining VARCHAR(100), "
					    + "stock_price VARCHAR(100), "
					    + "stock_receipt_date VARCHAR(100), "
					    + "stock_list VARCHAR(100), "
					    + "stock_quantity VARCHAR(100), "
					    + "stock_received VARCHAR(100), "
					    + "stock_type VARCHAR(100), "
					    + "stock_unit VARCHAR(100), "
					    + "stock_storage VARCHAR(100), "
					    + "stock_receipt_dateupdate VARCHAR(100), "
					    + "stock_payout VARCHAR(100), "
					    + "stock_pd VARCHAR(100), "
					    + "stock_batch VARCHAR(100)," 
					    + "stock_status VARCHAR(100)," 
					    + "stock_max VARCHAR(100)," 
					    + "stock_min VARCHAR(100)," 
					    + "stock_expdate VARCHAR(100)," 
					    + "stock_code VARCHAR(100)," 
					    + "stock_workflow INT "
					    + ");";
			   EdataDAO.CreateTable(sql);
			   
			   sql = " ALTER TABLE stock ADD CONSTRAINT unique_key  UNIQUE (stock_list,stock_type,stock_workflow,stock_status,stock_pd,stock_batch);";
			   EdataDAO.CreateTable(sql);

			   
			   
			   
			   sql = " ALTER TABLE workflow ADD COLUMN workflow_idadmin INT,  ADD COLUMN workflow_idadd INT,ADD COLUMN  workflow_app VARCHAR(100)"
			   		+ ",ADD COLUMN  workflow_folder VARCHAR(100),ADD COLUMN  workflow_location VARCHAR(100),ADD COLUMN  workflow_maindb VARCHAR(100),ADD COLUMN  workflow_subdb VARCHAR(100);";
			   EdataDAO.CreateTable(sql);
			
			   
				sql = "CREATE TABLE process ("
			                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
			                    + "name VARCHAR(100),"
			                    + "relatedpeople VARCHAR(100),"
			                    + "description VARCHAR(100),"
			                    + "id_condition INT,"
			                    + "id_refer INT"
			                    
			                    + " )";
					
				EdataDAO.CreateTable(sql);
			   


				sql = "CREATE TABLE sub_processes ("
	                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "step VARCHAR(100),"
	                    + "description VARCHAR(100),"
	                    + "maindatacolumn VARCHAR(100),"
	                    + "filtermain VARCHAR(100),"
	                    + "subdatacolumn VARCHAR(100),"
	                    + "filtersub VARCHAR(100),"
	                    + "processe_id INT"
	                    + " )";
			

				
				EdataDAO.CreateTable(sql);

			   
				sql = "CREATE TABLE keymap ("
	                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "sub_processes_id INT,"
	                    + "processes_id INT,"
	                    + "mainkey VARCHAR(100),"
	                    + "eformkey VARCHAR(100),"
	                    + "change_format VARCHAR(100),"
	                    + "enum_id INT,"
	                    + "filter_enum VARCHAR(100))";
			
	        	EdataDAO.CreateTable(sql);
				

	        	sql = "CREATE TABLE myapp ("
	                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "movement VARCHAR(100),"
	                    + "register VARCHAR(100),"
	                    + "appname VARCHAR(100))";
			
	        	EdataDAO.CreateTable(sql);
	        	
	        	
	        	

	        	sql = "CREATE TABLE registerkey ("
	                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
	                    + "app_id INT,"
	                    + "required INT,"
	                    + "mainkey VARCHAR(100),"
	                    + "source VARCHAR(100),"
	                    + "type VARCHAR(100))";
			
	        	EdataDAO.CreateTable(sql);	
	        	
	        	


				
				
				dataset dataset = new dataset();
				dataset.setDataset_name("Unit");
				dataset.setDataset_type("Main");
				dataset.setDataset_1("หน่วยนับ");
				dataset.setDataset_idadd(1);
				DatasetDAO.save(dataset);
				
	
				dataset.setDataset_name("Storage");
				dataset.setDataset_type("Main");
				dataset.setDataset_1("สถานที่จัดเก็บ");
				dataset.setDataset_idadd(1);
				DatasetDAO.save(dataset);
				
				dataset.setDataset_name("Stock_Type");
				dataset.setDataset_type("Main");
				dataset.setDataset_1("ประเภท");
				dataset.setDataset_idadd(1);
				DatasetDAO.save(dataset);
		
				dataset.setDataset_name("Drawer");
				dataset.setDataset_type("Main");
				dataset.setDataset_1("ผู้ขอเบิก");
				dataset.setDataset_idadd(1);
				DatasetDAO.save(dataset);
				
				
				SettingDAO.UpVersion();
			}
			
			
			return "update";
			
		}
		

		
		
}