package com.smepro.controller;




import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smepro.validation.mainvalidation;
import com.smepro.dao.fileDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.memberDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.roleDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.models.file;
import com.smepro.models.member;
import com.smepro.models.notification;
import com.smepro.models.report_pn;
import com.smepro.models.showlog;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.service.MainService;
import com.smepro.service.fileservice;
import com.smepro.service.memberservice;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;






@Controller
@SessionAttributes({"name","userrole"})
public class usercontroller {
	

	
	@Autowired
	userservice Userservice;
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	group_membersDAO Group_membersDAO;
	
	@Autowired
	roleDAO RoleDAO;
	
	@Autowired
	logDAO LogDAO;
	
	@Autowired
	report_pnDAO Report_pnDAO;
	
	@Autowired
	upload_pnDAO Upload_pnDAO;
	
	@Autowired
	notificationDAO NotificationDAO;
	
	@Autowired
	mainvalidation Mainvalidation;
	
	@Autowired
	otherservice otherservices;
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	fileservice Fileservice;
	

	@Autowired
	MainService MainServices;
	
	
	@RequestMapping(value="/changetheme/{type}" , method = RequestMethod.GET)
	public String changetheme(@PathVariable String type,HttpServletResponse response) {	 
		

		System.out.println("Cookie");
		Cookie theme = new Cookie("theme",type);
		theme.setMaxAge(60*60);
		theme.setSecure(true);
		theme.setHttpOnly(true);
		theme.setPath("/"); 
		response.addCookie(theme);
		
		return "redirect:/"; 
	}
	
	@RequestMapping(value="/profile" , method = RequestMethod.GET)
	//@ResponseBody
	public String showprofile(ModelMap model,@RequestParam(value = "save",required=false) String save) {	 
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		
		
		MainServices.addNotificationAttributes(model);
		if(users.getAuth() == null || users.getAuth().isEmpty()){

			String auth =  String.valueOf(otherservices.generateRandomnumber(4));
 			UserDAO.setbyAuth(auth, users.getUser_name());
			users = UserDAO.getbyusername2(Userservice.getLoggedName());

		}
		

		List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		model.addAttribute("save",save);
	   model.addAttribute("user",users);   	
		return "profile";
	}

	
	@RequestMapping(value="/unsetprofile" , method = RequestMethod.GET)
	public String unsetprofile() {	 
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		UserDAO.setbyAuth(null, users.getUser_name());
		return "redirect:/profile?save=success"; 
	}


	@RequestMapping(value="/resetprofileimage-profile" , method = RequestMethod.GET)
	public String resetprofileimage(HttpSession session) {	 
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		

		String paths;
	   	File fileToDelete;
	   	boolean success;
	   	
	    ServletContext contexts = session.getServletContext(); 
	     paths = contexts.getRealPath("/filesuploads/user/"); 
	     fileToDelete = new File(paths + File.separator + users.getUser_img());
	     
	     if(!fileToDelete.exists()) {
			   
			     success = fileToDelete.delete();
			
	     }
	     
	 	UserDAO.resetprofileimage(users.getUsername());
		return "redirect:/profile?save=success"; 
	}


	 @RequestMapping(value="/addprofileimage-profile",method=RequestMethod.POST)  
	    public String saveimage( @RequestParam CommonsMultipartFile file,  
	           HttpSession session) throws Exception{  
		
		 user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 ServletContext context = session.getServletContext();
		 
		 
		 
		
		 
		long totalsize =  Fileservice.printSystemSize(session);
		 
		 
		String UPLOAD_DIRECTORY ="/filesuploads/user/"; 
	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	  //String filename = (users.getUser_name()+".jpg").replace(" ","_");
	    
	    
	    System.out.print("filename "+filename);
	    
	    long imageFileSize = file.getSize();
	    
	    
	    if(totalsize > imageFileSize) {

		   	File fileToDelete;
		   	boolean success;
		   	

		     fileToDelete = new File(path + File.separator + users.getUser_img());
		     
		     if(!fileToDelete.exists()) {
				     success = fileToDelete.delete();
				     if(success) {
				    		UserDAO.resetprofileimage(users.getUsername());
				     }
		     }
	    	
			    String Size = Fileservice.printFileSize(imageFileSize);
			    System.out.print(file.getContentType());
			    
			    
			    byte[] bytes = file.getBytes();  
			    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
			         new File(path + File.separator + filename)));  
			    stream.write(bytes);  
			    stream.flush();  
			    stream.close();  
			    
			    UserDAO.setprofileimage(users.getUsername(),filename);
			    return "redirect:/profile?save=success"; 
			    
			    
	    }else {
		    return "redirect:/profile?save=errorSystemSizeover"; 
	    }
	           

 
 }  
	
	
	
	@RequestMapping(value="/edit-profile" , method = RequestMethod.POST)
	//@ResponseBody
	public String editprofile(user users) {		    
		UserDAO.update(users,Userservice.getLoggedName());
		Upload_pnDAO.changename(users.getUser_name());
		Upload_pnDAO.changenameapp(users.getUser_name());
		return "redirect:/profile?save=success"; 

	}

    
	@RequestMapping(value="/editusername-profile" , method = RequestMethod.POST)
	//@ResponseBody
	public String editusername(user users,ModelMap model) {	
		
		if(Userservice.getLoggedName() != users.getUsername()) {
			
			if(!UserDAO.isuserExists(users.getUsername())) {
				Group_membersDAO.updateusername(users.getUsername(),Userservice.getLoggedName());
				RoleDAO.setusername(users.getUsername(),Userservice.getLoggedName());
				UserDAO.updateusername(users,Userservice.getLoggedName());		

				
				
			}else {
				
				List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
				int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
				
				model.addAttribute("error","Exists"); 
				user users2 = UserDAO.getbyusername2(users.getUser_name());  
				model.addAttribute("user",users2);   	
				return "profile";
			}
			
			
		}
		
		
		
		return "redirect:/login"; 

	}

	
	@RequestMapping(value="/editpassword-profile" , method = RequestMethod.POST)
	//@ResponseBody
	public String editpassword(@RequestParam String oldpassword,@RequestParam String newpassword,ModelMap model) {	
		
		user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());   
		
			
		if(Userservice.doPasswordsMatch(oldpassword,users2.getPassword())) {
		
			if(Userservice.doPasswordsMatch(newpassword,users2.getPassword())){
				return "redirect:/login"; 
			
			}else if(!Mainvalidation.checkNameLength(newpassword)) {
	    		
	    		
	    		model.addAttribute("error2", "passwordlength");
	    		return "profile";
	    	}else if(!Mainvalidation.isAlphaNumeric(newpassword)) {
	    		
	    		
	    		model.addAttribute("error", "passwordpattern");
				return "/profile";
	    	}else {
	    		UserDAO.setnewpassword(newpassword,users2.getUser_id()); 	
				return "redirect:/login"; 
			}
			
					
	
		}else {
		
			
			
			model.addAttribute("error2","passwordnotmatch"); 
			 
			model.addAttribute("user",users2);   	
			return "profile";
		}
		
		

	}

	
	
	
	

  
    
    
    @RequestMapping(value="/notification",method = RequestMethod.GET)    
    public String notification(ModelMap model){ 
    	MainServices.addNotificationAttributes(model);
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());    
    	List<showlog> logs = LogDAO.getallbyidadd(users.getUser_id());
    	
    	List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
		List<notification>  allnotifications = NotificationDAO.getallrefer2(users.getUser_name());
    	model.addAttribute("allnotification",allnotifications); 
    	return "notification";
    } 
    
    
    @RequestMapping(value="/readall-notification",method = RequestMethod.GET)    
    public String readall(ModelMap model){ 
    	MainServices.addNotificationAttributes(model);
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());    
    	NotificationDAO.readall(users.getUser_name());
    	return "redirect:/notification";
    } 
    
    
   
    @RequestMapping(value="/notification/{id}",method = RequestMethod.GET)    
    public String notification(ModelMap model,@PathVariable int id){ 
    	MainServices.addNotificationAttributes(model);
    	user users = UserDAO.getbyusername2(Userservice.getLoggedName());    
    	List<showlog> logs = LogDAO.getallbyidadd(users.getUser_id());
    	
    	List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
		int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);


    	notification  notifications2 = NotificationDAO.getbyId(id,users.getUser_name());
    	NotificationDAO.setview(id);
    	
    	report_pn report_pns = Report_pnDAO.getbyIds(Integer.parseInt(notifications2.getNotification_refer()));
    	upload_pn upload_pn = Upload_pnDAO.getbyId(Integer.parseInt(notifications2.getNotification_refer()));
    	
    	model.addAttribute("notification2",notifications2);
    	model.addAttribute("report_pn",report_pns);
    	model.addAttribute("upload_pn",upload_pn);
    	return "shownotification";
    } 

}
