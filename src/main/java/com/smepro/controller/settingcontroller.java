package com.smepro.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smepro.models.user;
import com.smepro.service.GmailTextMsgSendWithoutSSL;
import com.smepro.service.MainService;
import com.smepro.dao.userDAO;
import com.smepro.service.userservice;
import com.smepro.ai.AiBrainBuilderService;
import com.smepro.dao.backupDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.mysqlJobDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.setmailDAO;
import com.smepro.dao.settingDAO;
import com.smepro.models.backup;
import com.smepro.models.notification;
import com.smepro.models.setmail;
import com.smepro.models.setting;
import com.smepro.models.holiday;




@Controller  
public class settingcontroller {
    
	@Autowired
	userservice Userservice;
	
	@Autowired
	setmailDAO SetmailDAO;  
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	notificationDAO NotificationDAO;
	
	
	@Autowired
	backupDAO BackupDAO;
	
	@Autowired
	holidayDAO HolidayDAO;
	
	@Autowired
	settingDAO SettingDAO;  
	
	@Autowired
	GmailTextMsgSendWithoutSSL gmailTextMsgSendWithoutSSL;
	
	@Autowired
	 MainService MainServices;
	
	@Autowired
	mysqlJobDAO MysqlJobDAO;
	
	@Autowired
	AiBrainBuilderService aiBrainBuilderService;
	
	
	@RequestMapping(value="/setting" ,  method=RequestMethod.GET)    
    public String mainsetting(ModelMap model){ 
		
		
		MainServices.addNotificationAttributes(model);
        setmail setmails =SetmailDAO.getbyId(1);
      	 model.addAttribute("setmail", setmails);
      	 
			List<notification>  notifications = NotificationDAO.getallrefer(Userservice.getLoggedName());
			int alert =  NotificationDAO.countnotification(Userservice.getLoggedName(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);  
      	 
		 return "setting"; 
    }
	
	
	@RequestMapping(value="/holiday-setting" ,  method=RequestMethod.GET)    
    public String holiday(ModelMap model,@RequestParam(required =false)  String holiday_year){ 
		
		
		MainServices.addNotificationAttributes(model);
		if(holiday_year == null) {
			LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		   	holiday_year = localDate.format(formatter);
		}
		
		setting settings = SettingDAO.getbyid();
		
		List<holiday> holiday1 = HolidayDAO.getall(holiday_year,"public");
		List<holiday> holiday2 = HolidayDAO.getall(holiday_year,"company");
		
		
		
		
		model.addAttribute("holiday_year",holiday_year); 
		model.addAttribute("holiday1",holiday1); 
		model.addAttribute("holiday2",holiday2); 
		model.addAttribute("settings",settings); 
		return "formholiday"; 
    }
	
	@RequestMapping(value="/copyholiday-setting/{holiday_year}" ,  method=RequestMethod.GET)    
    public String copyholiday(ModelMap model,@PathVariable  int holiday_year){ 
		
		HolidayDAO.copy(String.valueOf(holiday_year-1),String.valueOf(holiday_year));
		return "redirect:/holiday-setting";
    }
	
	
	
	
	
	
	@RequestMapping(value="/holiday-setting" ,  method=RequestMethod.POST)    
    public String holiday(
    		ModelMap model
    		,@RequestParam(required = false) List<String> holiday_date
    		,@RequestParam(required = false) List<String> holiday_name
    		,@RequestParam(required = false)  String holiday_year
    		,@RequestParam(required = false)  List<String> holiday_type
    		,@RequestParam(required = false, defaultValue = "0")  int holiday_notsun
    		){ 
		
		
		SettingDAO.setholiday(holiday_notsun);
		HolidayDAO.delete(holiday_year);
		
		
		if(holiday_date != null) {
			HolidayDAO.savebatch(holiday_date,holiday_name,holiday_type,holiday_year);
		}
		
		
		return "redirect:/holiday-setting";
    }
	
	
	
	
	@RequestMapping(value="/version-setting" ,  method=RequestMethod.GET)    
    public String version(ModelMap model){ 
   
		
			aiBrainBuilderService.buildBrain(100);
		
      	 
			List<notification>  notifications = NotificationDAO.getallrefer(Userservice.getLoggedName());
			int alert =  NotificationDAO.countnotification(Userservice.getLoggedName(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);  
      	 
		 return "version"; 
    }
	
	@RequestMapping(value="/backup-setting" ,  method=RequestMethod.GET)    
    public String backup(ModelMap model){ 
   
		MainServices.addNotificationAttributes(model);
			List<notification>  notifications = NotificationDAO.getallrefer(Userservice.getLoggedName());
			int alert =  NotificationDAO.countnotification(Userservice.getLoggedName(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);  
			List<backup>  backups = BackupDAO.getall();
			model.addAttribute("backup",backups);  
			
			///ฝากไว้ก่
			MysqlJobDAO.createAutoPurgeEventEdataSoftDelete();
			
		 return "backup"; 
    }
	
    @RequestMapping(value="/testmail-setting",method = RequestMethod.GET)    
    public String testmail(ModelMap model,setmail setmails,user users) throws MessagingException{       
    		MainServices.addNotificationAttributes(model);
    	    user User = UserDAO.getbyusername2(Userservice.getLoggedName());  
    	    setmail mailconfig =  SetmailDAO.getbyId(1);
    		setmails.setUsername(mailconfig.getUsername());
    		setmails.setPassword(mailconfig.getPassword());
    		setmails.setHostname(mailconfig.getHostname());
    		setmails.setStarttls_port(mailconfig.getStarttls_port());
    		setmails.setStarttls(mailconfig.isStarttls());
    		setmails.setAuth(mailconfig.isAuth());
    		setmails.setUrl(mailconfig.getUrl());
    		setmails.setSupport(mailconfig.getSupport());
    		users.setUser_email(mailconfig.getEmailtest());
    		users.setUser_name("Test Email");
    		
    		String sendmail = gmailTextMsgSendWithoutSSL.sendGmailendWithoutSSL(setmails,users,0);
    		
    		List<notification>  notifications = NotificationDAO.getallrefer(Userservice.getLoggedName());
    		int alert =  NotificationDAO.countnotification(Userservice.getLoggedName(),0);
    		model.addAttribute("notification",notifications);  
    		model.addAttribute("alert",alert);  
         	 model.addAttribute("setmail", setmails);
    		model.addAttribute("sendmail", sendmail);
            return "setting";  
    } 
	
    @RequestMapping(value="/editsetmail-setting" ,  method=RequestMethod.POST)    
    public String postsetmail(ModelMap model,setmail setmails){  
    	
   
    		SetmailDAO.update(setmails,1);
    

    	return "redirect:/setting"; 
    }  
	
	
}
