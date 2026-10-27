package com.smepro.controller;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smepro.dao.roleDAO;
import com.smepro.dao.commentDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.group_authoritiesDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.models.comment;
import com.smepro.models.dar;
import com.smepro.models.department;
import com.smepro.models.drives;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.group_authorities;
import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.models.log;
import com.smepro.models.notification;
import com.smepro.models.report_pn;
import com.smepro.models.report_pn_pdf;
import com.smepro.models.user;
import com.smepro.models.showlog;
import com.smepro.service.MainService;
import com.smepro.service.userservice;
import com.smepro.validation.mainvalidation;






@Controller
@SessionAttributes({"name","userrole","Member_id"})
public class logcontroller {
	

	@Autowired
	userservice Userservice;
	

	@Autowired
	groupsDAO GroupsDAO;
	
	@Autowired
	group_authoritiesDAO  Group_authoritiesDAO;
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	mainvalidation Mainvalidation;
	
	@Autowired
	group_membersDAO Group_membersDAO;

	@Autowired
	drivesDAO DrivesDAO;
	
	@Autowired
	folderDAO FolderDAO;
	
	@Autowired
	logDAO LogDAO;
	
	
	@Autowired
	roleDAO RoleDAO;
	
	@Autowired
	notificationDAO NotificationDAO;

	@Autowired
	departmentDAO DepartmentDAO;	
	
	
	@Autowired
	report_pnDAO Report_pnDAO;
	
	@Autowired
	darDAO DarDAO;
	
	@Autowired
	commentDAO CommentDAO;
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	MainService MainServices;
    
    
    
    @RequestMapping(value="/reportrecord-log",method = RequestMethod.GET)    
    public String reportrecord(ModelMap model){  
    	
	    
    	
    	LocalDateTime todays = LocalDateTime.now();
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy");
	    String years = todays.format(formatters);
	
	     
		   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
			List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
			int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);

			 
			List<report_pn> rawreport = Report_pnDAO.rawreport(years);
			model.addAttribute("rawreport",rawreport);
			
			
			List<report_pn> respon =  Report_pnDAO.getallrespon(years);
			List<report_pn> app =  Report_pnDAO.getallapp(years);
			model.addAttribute("respon",respon);
			model.addAttribute("app",app);
			
			MainServices.addNotificationAttributes(model);
			
    	return "log/reportrecord";  
	
	
   }  
    
    
    
    
    
    @RequestMapping(value="/reportrecords-log",method = RequestMethod.POST)    
    public String reportrecords(
    		ModelMap model,
    		@RequestParam(required = false) String datestart,
    		@RequestParam(required = false) String dateend,
    		@RequestParam(required = false) String respon,
    		@RequestParam(required = false) String app,
    		@RequestParam(required = false) int type,
    		@RequestParam(required = false) String sort
    		){  
    	
	   

    	LocalDateTime todays = LocalDateTime.now();//For reference
	    DateTimeFormatter formatters3 = DateTimeFormatter.ofPattern("yyyy");
	    String years = todays.format(formatters3);
	

		List<report_pn> rawreport = Report_pnDAO.rawreportfull(datestart,dateend,respon,app,type,sort);
		model.addAttribute("rawreport",rawreport);
    	
		List<report_pn> respons =  Report_pnDAO.getallrespon(years);
		List<report_pn> apps =  Report_pnDAO.getallapp(years);
		model.addAttribute("respon",respons);
		model.addAttribute("app",apps);
		
		model.addAttribute("datestart",datestart);
		model.addAttribute("dateend",dateend);
		model.addAttribute("respons",respon);
		model.addAttribute("apps",app);
		model.addAttribute("type",type);
		model.addAttribute("sort",sort);

		MainServices.addNotificationAttributes(model);
		
		
    	return "log/reportrecord";  
	
   }  
    
    
    
    
	
    @RequestMapping(value="/commentrecord2-log",method = RequestMethod.POST)    
    public String commentrecord2(ModelMap model,comment comments){  
    	
    	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
    	
    	user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	
    	comments.setComment_name(users2.getUser_name());
    	comments.setComment_date(date);
    	
    	if(comments.getComment_id() > 0) {
    		CommentDAO.edit(comments);
    	}else {
    		CommentDAO.save(comments);
    	}
    	

	return "redirect:/distribution-log?refer="+comments.getComment_idrefer();     
	
	
   }  
    
    
    @RequestMapping(value="/delcomment2-log",method = RequestMethod.GET)    
    public String delcomment(@RequestParam int comment_id,@RequestParam int refer){  
    	CommentDAO.delete(comment_id);
    	return "redirect:/distribution-log?refer="+refer;      

   }   
    
    @RequestMapping(value="/delcomment-log",method = RequestMethod.GET)    
    public String delcomment(@RequestParam int comment_id,@RequestParam int upload_pn_idrefer,@RequestParam int upload_pn_id){  
    	CommentDAO.delete(comment_id);
    	return "redirect:/lohpn-filemanager?idrefer="+upload_pn_idrefer+"&&id="+upload_pn_id;       

   }   
    
    
    @PreAuthorize("hasAuthority('READ-UE')")  
	    @RequestMapping(value="/log",method = RequestMethod.GET)    
	    public String log(ModelMap model){  
    	
    	
    	
	     List<showlog> logs =  LogDAO.getall2();
	     
	
			
	    model.addAttribute("log", logs);
	    
	    
	   MainServices.addNotificationAttributes(model);
	    
	    	return "log/alllog";     
	   }   

    
   
    
    
	
	@RequestMapping(value="/darlog-log")
	public String logdar(ModelMap model) {
		
		
		   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
				int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
				
		List<dar> unfinished =  DarDAO.getdarunfinished();
		
	    int dartotal = DarDAO.gettotal();
	    int dartotal_unfinished = DarDAO.gettotalunfinished();
		
		model.addAttribute("dartotal",dartotal);
		model.addAttribute("dartotal_unfinished",dartotal_unfinished);
		model.addAttribute("unfinished",unfinished);
		return "log/logdar";
	}
	
	
	@RequestMapping(value="/filelog-log")
	public String filelog(ModelMap model) {
		   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
				int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
	    List<log> doc= LogDAO.getuseronreadfiledownload();
	   model.addAttribute("doc",doc);
		return "log/logdownload";
	}
	
	@RequestMapping(value="/recordslog-log")
	public String recordslog(ModelMap model) throws ParseException {
		 
		
		user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
		List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
		int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
				
				
		   LocalDateTime today = LocalDateTime.now();//For reference
		    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM");
		    DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy");
		    LocalDateTime todaylast = LocalDateTime.now().minusDays(30);//For reference
		    SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
		    
		    
		    String datestart = today.format(formatter2);
		    String dateend = today.format(formatter2);
		    
		    String month = today.format(formatter3);
		    String year = today.format(formatter4);
		    
		    Date startDate = Format.parse(datestart);
	   	    Calendar start = Calendar.getInstance();
	   	    start.setTime(startDate);
		    
			List<report_pn> report_pns = Report_pnDAO.getallreportbydate(datestart);
			List<report_pn> report_pnsmonth = Report_pnDAO.getallreportbymonth(month);
			List<report_pn> report_pnsweek = Report_pnDAO.getallreportbyweek(start.WEEK_OF_YEAR);
			List<report_pn> report_pnsyear = Report_pnDAO.getallreportbyyear(year);
			
			
			model.addAttribute("report_pn",report_pns);
			model.addAttribute("report_pnsmonth",report_pnsmonth);
			model.addAttribute("report_pnsweek",report_pnsweek);
			model.addAttribute("report_pnsyear",report_pnsyear);
			
			
			
		return "log/logrecords";
	}	    
		
	
	
	
	@RequestMapping(value="/recordslog2-log")
	public String recordslog2(ModelMap model) throws ParseException {
		 
		
		   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
				int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
		
				
			
			
			
			
		return "log/logrecords2";
	}	    
			
	
	
	
	@RequestMapping(value="/recordslog-log",method = RequestMethod.POST)
	public String recordslog(ModelMap model,@RequestParam(required = false) String date) throws ParseException {
		
		
		        user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
				int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
			
		
			Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(date);  		
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			DateFormat dateFormat2 = new SimpleDateFormat("mm");  
			DateFormat dateFormat3 = new SimpleDateFormat("yyyy");  
			SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
			
			String dates= dateFormat.format(date1);  	
			String month = dateFormat.format(date1); 	
			String year = dateFormat.format(date1); 
			
		    Date startDate = Format.parse(dates);
	   	    Calendar start = Calendar.getInstance();
	   	    start.setTime(startDate);
		    
			List<report_pn> report_pns = Report_pnDAO.getallreportbydate(dates);
			List<report_pn> report_pnsmonth = Report_pnDAO.getallreportbymonth(month);
			List<report_pn> report_pnsweek = Report_pnDAO.getallreportbyweek(start.WEEK_OF_YEAR);
			List<report_pn> report_pnsyear = Report_pnDAO.getallreportbyyear(year);
			
			
			model.addAttribute("report_pn",report_pns);
			model.addAttribute("report_pnsmonth",report_pnsmonth);
			model.addAttribute("report_pnsweek",report_pnsweek);
			model.addAttribute("report_pnsyear",report_pnsyear);
			model.addAttribute("dates",dates);
			
			return "log/logrecords";
			
			
			
		
	}	    
	
	

	@RequestMapping(value="/recordslog2-log",method = RequestMethod.POST)
	public String recordslog2(ModelMap model,@RequestParam(required = false) String datestart,@RequestParam(required = false) String dateend) throws ParseException {
		
		
		        user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
				int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
			
		
			if(datestart != null &&  dateend != null ) {
			Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(datestart);  		
			Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse(dateend);  	
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			
			String dates= dateFormat.format(date1);  	
			String dates2= dateFormat.format(date2);  
		    
			List<report_pn> report_pns = Report_pnDAO.getallreportbydate(dates,dates2);

			
			model.addAttribute("report_pn",report_pns);
			model.addAttribute("dates",dates+"-"+dates2);

			return "log/logrecords2";
			}else {
				return "redirect:/recordslog2-log";     
			}
			
			
		
	}	    
	
	
	@RequestMapping(value="/distribution-log",method = RequestMethod.GET)
	public String distribution(ModelMap model,@RequestParam int refer,@RequestParam(value = "comment_id",required = false) String comment_id) {
	
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
		
	    if(comment_id != null) {
	    	
	    	comment  comment = CommentDAO.get(comment_id);
	    	model.addAttribute("comment",comment);
	    }
	    
	    List<comment> comments  = CommentDAO.getall("log",refer);
	    model.addAttribute("comments",comments);

	    file files = FileDAO.getbyId(refer);
	    
		
		List<log> doc= LogDAO.getallreadfileshortcut(files.getFile_parent());
		List<log> log= LogDAO.getallreadfileshortcutall(files.getFile_parent());
		
		 model.addAttribute("id",refer);
		 model.addAttribute("doc",doc);
		 model.addAttribute("log",log);
		return "accesshistory";
	}
	
	@RequestMapping(value="/datadistribution-log",method = RequestMethod.GET)
	public String datadistribution(ModelMap model,@RequestParam int refer) {
	
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
		List<log> log= LogDAO.getallreadfileshortcutall2(refer);
		
		 model.addAttribute("id",refer);
		 model.addAttribute("log",log);
		return "accesshistory2";
	}
	
	
	
	
	
	@RequestMapping(value="/distributionlog-log")
	public String distributionlog(ModelMap model) {
	
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);

		 List<log> doc= LogDAO.getallreadfile();
		    
		 model.addAttribute("doc",doc);
		
		return "log/distribution";
	}
	
	
	  @RequestMapping(value="/webscrape",method = RequestMethod.GET)    
	    public String webscrape(){  
	    
		  System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		  final String url = "https://directory.brcgs.com/site/1481901";
		 
		  try{
			  
		
			  // ChromeOptions options = new ChromeOptions();
			  // options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			  //  options.setExperimentalOption("useAutomationExtension", false);
			  // WebDriver driver = new ChromeDriver(options);
			 //driver.get(url);
			 
			 //System.out.println(driver.getPageSource());
			  final Document  document  = Jsoup.connect(url).get();
			System.out.println(document.outerHtml());
			  
			  
			  
		  }catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  
		  return "log/distribution";
	   }   
	    
	
	
}