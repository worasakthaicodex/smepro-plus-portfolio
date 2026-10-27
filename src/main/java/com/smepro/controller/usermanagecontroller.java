package com.smepro.controller;




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.attendanceDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.group_authoritiesDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.userDAO;
import com.smepro.models.UserCode;
import com.smepro.models.attendance;
import com.smepro.models.department;
import com.smepro.models.document_no;
import com.smepro.models.drives;
import com.smepro.models.folder;
import com.smepro.models.group_authorities;
import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.models.notification;
import com.smepro.models.user;
import com.smepro.models.showlog;
import com.smepro.service.EmployeeService;
import com.smepro.service.MainService;
import com.smepro.service.userservice;
import com.smepro.validation.mainvalidation;






@Controller
@SessionAttributes({"name","userrole"})
public class usermanagecontroller {
	

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
	upload_pnDAO Upload_pnDAO;
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	attendanceDAO AttendanceDAO;
	
	@Autowired
	MainService MainServices;
	
	@Autowired
	EmployeeService employeeService;
	
	
	
	   @PreAuthorize("hasAuthority('READ-UE')")
	   @RequestMapping(value="/department-usermanage" ,  method=RequestMethod.GET)    
	    public String department(ModelMap model){
		   MainServices.addNotificationAttributes(model);
		    List<department>  departments = DepartmentDAO.getall();
		
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			 ArrayList lists = new ArrayList();
			for (SimpleGrantedAuthority permission : list) {
			     lists.add(permission.getAuthority());
			}
			
			   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
				int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);

			
			
			model.addAttribute("permission",lists);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);
			model.addAttribute("department",departments);
	        return "department";    
	    }    
	
	
	
	   @PreAuthorize("hasAuthority('READ-UE')")
	   @RequestMapping(value="/role-usermanage" ,  method=RequestMethod.GET)    
	    public String role(ModelMap model){
		   MainServices.addNotificationAttributes(model);
		     List<groups> groupss = GroupsDAO.getall(0);
		   
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Collection<SimpleGrantedAuthority> list = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			 ArrayList lists = new ArrayList();
			for (SimpleGrantedAuthority permission : list) {
			     lists.add(permission.getAuthority());
			}
		   
			
			   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
				int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
			
			
			model.addAttribute("permission",lists);
			model.addAttribute("groups", groupss);
	        return "role_and_permissions";    
	    }    

	   @PreAuthorize("hasAuthority('ADD-UE')")
	    @RequestMapping(value="/addrole-usermanage" , method=RequestMethod.GET) 
		public String addrole(ModelMap model) {
		   MainServices.addNotificationAttributes(model);
		   List<department>  departments = DepartmentDAO.getall();
		   
		   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
			int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);
			
			model.addAttribute("department",departments);
	    	return "formgroups";
		}
	   
	   @PreAuthorize("hasAuthority('ADD-UE')")
	    @RequestMapping(value="/addrole-usermanage" , method=RequestMethod.POST) 
			public String save( groups groupss) { 
		    	 GroupsDAO.save(groupss,0);
		    	 return "redirect:/role-usermanage";
	    }
	    
	   
	   
	   
	   @PreAuthorize("hasAuthority('ADD-UE')")
	    @RequestMapping(value="/adddepartment-usermanage" , method=RequestMethod.GET) 
		public String adddepartment(ModelMap model) {
		   
			
		   MainServices.addNotificationAttributes(model);
		   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
			List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
			int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
			model.addAttribute("notification",notifications);  
			model.addAttribute("alert",alert);
			
			
	    	return "formdepartment";
		}
	   
	   @PreAuthorize("hasAuthority('ADD-UE')")
	    @RequestMapping(value="/adddepartment-usermanage" , method=RequestMethod.POST) 
			public String savedepartment( department departments) { 
		   			DepartmentDAO.save(departments);
		    	 return "redirect:/department-usermanage";
	    }
	   
	    @PreAuthorize("hasAuthority('EDIT-UE')")
		   @RequestMapping(value="/editdepartment-usermanage" ,  method=RequestMethod.GET)    
		    public String editdepartment(@RequestParam int id,ModelMap model){
	    	  department departments = DepartmentDAO.getbyId(id); 
			    
				
			   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
				int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
				
			    model.addAttribute("department", departments);
		        return "formdepartment";    
		    }    
		  
	        @PreAuthorize("hasAuthority('EDIT-UE')")
		    @RequestMapping(value="/editdepartment-usermanage",method = RequestMethod.POST)    
		    public String editsavedepartment( department departments){  
	        	DepartmentDAO.update(departments);    
		    	 return "redirect:/department-usermanage";  
		    }  
	        
	        @PreAuthorize("hasAuthority('DELETE-UE')")
		    @RequestMapping(value="/deletedepartment-usermanage/{id}",method = RequestMethod.GET)    
		    public String deletesdepartment(@PathVariable int id){    
	        	DepartmentDAO.delete(id);    
		    	 return "redirect:/department-usermanage";
		    }  

	   
	   
	   
	   
	   
	        @PreAuthorize("hasAuthority('EDIT-UE')")
		   @RequestMapping(value="/editrole-usermanage" ,  method=RequestMethod.GET)    
		    public String edituser(@RequestParam int id,ModelMap model){
	        	
	        	MainServices.addNotificationAttributes(model);
			    groups groupss = GroupsDAO.getbyId(id,0); 
			    List<department>  departments = DepartmentDAO.getall();
			  
			    
				
				   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
					int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
					
					
				model.addAttribute("department",departments);
			    model.addAttribute("groups", groupss);
		        return "formgroups";    
		    }    
		  
	        @PreAuthorize("hasAuthority('EDIT-UE')")
		    @RequestMapping(value="/editrole-usermanage",method = RequestMethod.POST)    
		    public String editsave(  groups groupss){  
		    	GroupsDAO.update(groupss,0);    
		    	 return "redirect:/role-usermanage";  
		    }  
	        
	        @PreAuthorize("hasAuthority('DELETE-UE')")
		    @RequestMapping(value="/deleterole-usermanage/{id}",method = RequestMethod.GET)    
		    public String deletesuser(@PathVariable int id){    
		    	GroupsDAO.delete(id,0);    
		    	 return "redirect:/role-usermanage";
		    }  

	        @PreAuthorize("hasAuthority('SET-UE')")
		    @RequestMapping(value="/setrole-usermanage" ,  method=RequestMethod.GET)    
		    public String set(@RequestParam int id,ModelMap model,@RequestParam(value = "save",required=false) String save){
	        	
	        	MainServices.addNotificationAttributes(model);
		    	groups groupss = GroupsDAO.getbyId(id, 0);
		    	
		    	List<drives> drivess = DrivesDAO.getall();
		 		List<folder> folders = FolderDAO.getall2();	
		    	List<group_authorities> group_authoritiess = Group_authoritiesDAO.getall(id,0);
		    	
				
				   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
					int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
				
		    	 model.addAttribute("authoritiess", group_authoritiess);
		    	 model.addAttribute("role", groupss);
		    	 model.addAttribute("drivess",drivess);
		     	 model.addAttribute("folders",folders);
		     	 model.addAttribute("save",save);
		    	 
		         return "setrole-usermanage";    
		    }    
		  
	        @PreAuthorize("hasAuthority('SET-UE')")
		    @RequestMapping(value="/setrole-usermanage",method = RequestMethod.POST)    
		    public String setsave(@RequestParam("permissions") List<String> authoritys , group_authorities group_authoritiess){  
		    	Group_authoritiesDAO.delete(group_authoritiess.getGroup_id(),0);
		    	Group_authoritiesDAO.set(authoritys,group_authoritiess,0);    
		        return "redirect:/setrole-usermanage?id=" + group_authoritiess.getGroup_id()+"&&save=success";    
		    } 
		    
	        @PreAuthorize("hasAuthority('READ-UE')")
		    @RequestMapping(value="/user-usermanage" , method = RequestMethod.GET)
			public String usermanage(ModelMap model) {
				
	        	MainServices.addNotificationAttributes(model);
				
				    user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
					int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
				
				
			  List<user> userss = UserDAO.getall(0,0);
				model.addAttribute("user", userss);
				return "usermanage2";   
			}
	        
	        
	        @PreAuthorize("hasAuthority('READ-UE')")
		    @RequestMapping(value="/userfull-usermanage" , method = RequestMethod.GET)
			public String userfull(ModelMap model) {
				
				
	        	MainServices.addNotificationAttributes(model);
				    user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
					int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
				
				
			  List<user> userss = UserDAO.getall(0,0);
				model.addAttribute("user", userss);
				return "usermanage2";   
			}
	         
	        
		   
	        @PreAuthorize("hasAuthority('ADD-UE')")
		    @RequestMapping(value="/add-usermanage" , method=RequestMethod.GET) 
		  	public String add(ModelMap model) { 
	        	
	        	MainServices.addNotificationAttributes(model);
				   user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users.getUser_name());
					int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
				
					
					List<groups> groupss = GroupsDAO.getall(0);
					model.addAttribute("groups",groupss);
					
					List<UserCode> UserCode = UserDAO.findAllUserCode();
					
					if(UserCode.size() > 0) {
						
						user user = new user();
						user.setUsername("#AUTO_USER");
						user.setPassword("#AUTO_PASSWORD");
						model.addAttribute("user",user);
					}

					
		      	 return "formusers2";  
		  	}
		    
	        @PreAuthorize("hasAuthority('ADD-UE')")
		    @RequestMapping(value="/add-usermanage" , method=RequestMethod.POST) 
		  	public String addsave(ModelMap model,@RequestParam String username,@RequestParam String password,user users,@RequestParam int group_id) { 
		   
	        	List<groups> groupss = GroupsDAO.getall(0);
				model.addAttribute("groups",groupss);

			
				if(username.equals("#AUTO_USER")) {
						username = employeeService.CreateEmployeeID(username,group_id);
				}
				if(password.equals("#AUTO_PASSWORD")) {
					  password = employeeService.CreateEmployeePassword(password,group_id);
				}
				
				
		    	if(!Mainvalidation.checkNameLength(username)) {
		    		
					
					   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
						List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
						int alert =  NotificationDAO.countnotification(users.getUser_name(),0);
						model.addAttribute("notification",notifications);  
						model.addAttribute("alert",alert);
					
						
						
		    		model.addAttribute("Error", "usernamelength");
					return "/formusers2";
		    	}
		    	
		    	if(!Mainvalidation.checkNameLength(password)) {
		    		
					
					   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
						List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
						int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
						model.addAttribute("notification",notifications);  
						model.addAttribute("alert",alert);
		    		model.addAttribute("Error", "passwordlength");
					return "/formusers2";
		    	}
		    	
		    	if(!Mainvalidation.isAlphaNumeric(username)) {
		    		
					
				
					
					   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
						List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
						int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
						model.addAttribute("notification",notifications);  
						model.addAttribute("alert",alert);
					
		    		model.addAttribute("Error", "usernamepattern");
					return "/formusers2";
		    	}
		    	
		    	if(!Mainvalidation.isAlphaNumeric(password)) {
		    		
			
		    		   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
						List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
						int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
						model.addAttribute("notification",notifications);  
						model.addAttribute("alert",alert);
					
		    		model.addAttribute("Error", "passwordpattern");
					return "/formusers2";
		    	}   	
		    	if(UserDAO.isuserExists(username)) {
		    		
					

		    		   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
						List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
						int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
						model.addAttribute("notification",notifications);  
						model.addAttribute("alert",alert);
					
		    		model.addAttribute("Error", "usernameexists");
					return "/formusers2";	
		    		
		    	}
		    	
		    	  int id = UserDAO.save(users,0);
		    	  user Users = UserDAO.getbyId(id,0);
		    	  
		   
		    	  Group_membersDAO.save(Users.getUsername(),group_id);
		    	  RoleDAO.setrole(Users.getUsername(),"ROLE_USER");
		      	 return "redirect:/edit-usermanage?id="+ id;  
		    	
		    }
			
	        @PreAuthorize("hasAuthority('ADD-UE')") 
		    @RequestMapping(value="/edit-usermanage" ,  method=RequestMethod.GET)    
		    public String editsuser(@RequestParam int id,ModelMap model,@RequestParam(value = "save",required=false) String save){
			    user users = UserDAO.getbyId(id, 0); 
			    
				
				   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
					int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
				
			    model.addAttribute("user", users);
			    
			    
			    List<user> userss = UserDAO.getall(0,0);
				model.addAttribute("usered", userss);
				model.addAttribute("save", save);
		        return "formuser2";    
		    }    
		  
	        
	        @PreAuthorize("hasAuthority('ADD-UE')") 
		    @RequestMapping(value="/transfer-usermanage" ,  method=RequestMethod.POST)    
		    public String transfer(
		    		@RequestParam int id,
		    		@RequestParam String user_names,
		    		@RequestParam String user_name
		    		){
	        	
	        	
	        	 Upload_pnDAO.changename(user_names,user_name);
	        	 Upload_pnDAO.changenameapp(user_names,user_name);
	        	 FileDAO.changefile_jd(user_names,user_name);
			  	 return "redirect:/edit-usermanage?id="+id+"&&save=success";  
  
		    }    
		  
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        @PreAuthorize("hasAuthority('EDIT-UE')") 
		    @RequestMapping(value="/resetpassword/{id}",method = RequestMethod.GET)    
		    public String resetpassword(@PathVariable int id,ModelMap model){  
	        	
	        	MainServices.addNotificationAttributes(model);
	        	user users = UserDAO.getbyId(id, 0); 
			    List<notification>  notifications = NotificationDAO.getallrefer(Userservice.getLoggedName());
				int alert =  NotificationDAO.countnotification(Userservice.getLoggedName(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
				
			    model.addAttribute("user", users);
		        return "formresetpassword";    
		    }   
	        
	        
	        
	        @PreAuthorize("hasAuthority('EDIT-UE')")
	  		    @RequestMapping(value="/resetpassword" , method=RequestMethod.POST) 
	  		  	public String resetpasswords(ModelMap model,@RequestParam String username,@RequestParam String password,@RequestParam int id) { 
	  		   
	  		  

	  		    	
	  		    	if(!Mainvalidation.checkNameLength(password)) {
	  		    		
	  		   	   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
					int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);;
	  					
	  		    		model.addAttribute("Error", "passwordlength");
	  					return "/formresetpassword";
	  		    	}

	  		    	
	  		    	if(!Mainvalidation.isAlphaNumeric(password)) {
	  		    		
	  		   	   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
					List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
					int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
					model.addAttribute("notification",notifications);  
					model.addAttribute("alert",alert);
	  					
	  		    		model.addAttribute("Error", "passwordpattern");
	  					return "/formresetpassword";
	  		    	}   	

	  		    	 UserDAO.resetpassword(id,password);
	  		      	 return "redirect:/user-usermanage";  
	  		    	
	  		    }
	        
	        
	        @PreAuthorize("hasAuthority('ADD-UE')") 
		    @RequestMapping(value="/edit-usermanage",method = RequestMethod.POST)    
		    public String editssave(user users){  
		    	UserDAO.updatefull(users,0,0);    
		    	return "redirect:/user-usermanage";      
		    }   

	        @PreAuthorize("hasAuthority('DELETE-UE')") 
		    @RequestMapping(value="/delete-usermanage/{id}",method = RequestMethod.GET)    
		    public String deleteuser(@PathVariable int id){    
	        	
	        	user users = UserDAO.getbyId2(id);
	        	Group_membersDAO.delete(users.getUsername());
		    	UserDAO.delete(id,0);
		    	return "redirect:/user-usermanage";  
		    }   

	        @PreAuthorize("hasAuthority('SET-UE')") 
		    @RequestMapping(value="/set-usermanage" ,  method=RequestMethod.GET)    
		    public String setuser(@RequestParam String username,ModelMap model){
		    	List<groups> groupss = GroupsDAO.getall(0);  
		    	group_members group_memberss = Group_membersDAO.getbyId(username);
		    	
		 	   user users2 = UserDAO.getbyusername2(Userservice.getLoggedName());  
				List<notification>  notifications = NotificationDAO.getallrefer(users2.getUser_name());
				int alert =  NotificationDAO.countnotification(users2.getUser_name(),0);
				model.addAttribute("notification",notifications);  
				model.addAttribute("alert",alert);
				
		    	model.addAttribute("group_members", group_memberss);
			    model.addAttribute("groups", groupss);
		        return "formset2";    
		    }     
		    
	        @PreAuthorize("hasAuthority('SET-UE')")  
		    @RequestMapping(value="/set-usermanage",method = RequestMethod.POST)    
		    public String setusersave(group_members group_memberss){  
		    	Group_membersDAO.update(group_memberss,0);    
		    	return "redirect:/user-usermanage";     
		    }   
           
	        
	   
		    
	        @PreAuthorize("hasAuthority('READ-UE')")
	 	   @RequestMapping(value="/attendance-usermanage" ,  method=RequestMethod.GET)    
	 	    public String allattendance(ModelMap model){
	        	MainServices.addNotificationAttributes(model);
	        	LocalDateTime localDate = LocalDateTime.now();//For reference
		    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		    	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM");
		    	 String year = localDate.format(formatter);
		    	 String month = localDate.format(formatter2);
	        	
	        	
	 		    List<attendance>  all_attendance = AttendanceDAO.getall(month,year);
	 		   List<user> userss = UserDAO.getall(0,0);
				model.addAttribute("user", userss);
	 			model.addAttribute("list_attendances",all_attendance);
	 	        return "all_attendance";    
	 	    }    
	        
	        
	        
	        
	        
	        
	        @PreAuthorize("hasAuthority('READ-UE')")
	 	   @RequestMapping(value="/attendance-usermanage" ,  method=RequestMethod.POST)    
	 	    public String allattendance(ModelMap model
	 	    		,@RequestParam(required = false) String dt_datestart
	 	    		,@RequestParam(required = false) String dt_dateend
	 	    		,@RequestParam(required = false) List<String> selectuser){
	 		   
	        	LocalDateTime localDate = LocalDateTime.now();//For reference
		    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		    	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM");
		    	 String year = localDate.format(formatter);
		    	 String month = localDate.format(formatter2);
		    	 MainServices.addNotificationAttributes(model);
	        	
	 		    List<attendance>  all_attendance = AttendanceDAO.getall(dt_datestart,dt_dateend,selectuser);
	 		   List<user> userss = UserDAO.getall(0,0);
	 		   
	 		   
	 		   	model.addAttribute("dt_datestart", dt_datestart);
	 		  	model.addAttribute("dt_dateend", dt_dateend);
	 		  	model.addAttribute("selectuser", selectuser);
				model.addAttribute("user", userss);
	 			model.addAttribute("list_attendances",all_attendance);
	 	        return "all_attendance";    
	 	    }    
	 	
	        
	        @RequestMapping(value="/gencode-usermanage",method = RequestMethod.GET)    
		    public String gencode(ModelMap model){  
		 	    	
		    	 List<UserCode> UserCode = UserDAO.findAllUserCode();
		    	 model.addAttribute("UserCode",UserCode);
		    	 MainServices.addNotificationAttributes(model);
		    	 return "users/gencode";
		    }    
	        
	        
	        @RequestMapping(value="/deleleUserCode-usermanage",method = RequestMethod.GET)
	    			public String deleleUserCode(@RequestParam int id) {
	        			UserDAO.deleleUserCode(id);
	    		    	 return "redirect:/gencode-usermanage";
	    		      
	    			}    
	        
	        
	        
	        @RequestMapping(value="/addUserCode-usermanage",method = RequestMethod.GET)
 			public String addUserCode(ModelMap model,document_no document_nos) {
	        		MainServices.addNotificationAttributes(model);
    	
    		List<department> departments =  DepartmentDAO.getall();
    		model.addAttribute("departments",departments);
 		    	 return "users/form_gencode";
 		      
 			}
    
	        @RequestMapping(value="/editUserCode-usermanage",method = RequestMethod.GET)
 			public String editUserCode(ModelMap model,@RequestParam int id) {
 		    	
	        	UserCode User_code = UserDAO.findUserCodeById(id);
    			 model.addAttribute("UserCode",User_code); 
    			

    			 List<department> departments =  DepartmentDAO.getall();
    				model.addAttribute("departments",departments);
    			MainServices.addNotificationAttributes(model);
    		  	 return "users/form_gencode";
 		      
 			}
       
	        
	        
	        
	        
		    @RequestMapping(value="/insertUserCode-usermanage",method = RequestMethod.POST)
				public String insertUserCode(UserCode userCode) {
			    	
		    	  	UserDAO.saveUserCode(userCode);
			    	 return "redirect:/gencode-usermanage";
			      
				}
		    @RequestMapping(value="/updateUserCode-usermanage",method = RequestMethod.POST)
		 			public String updateUserCode(UserCode userCode) {
		 		    	
		    			UserDAO.editUserCode(userCode);
		 		    	 return "redirect:/gencode-usermanage";
		 		      
		 			}  
	        
		    
}