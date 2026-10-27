package com.smepro.controller;




import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.folderDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.logDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dto.memberDTO;
import com.smepro.models.condition;
import com.smepro.models.dar;
import com.smepro.models.department;
import com.smepro.models.document_no;
import com.smepro.models.drives;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.models.log;
import com.smepro.models.notification;
import com.smepro.models.purchase;
import com.smepro.models.showdar;
import com.smepro.models.showupload_pn;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.service.DarService;
import com.smepro.service.MainService;
import com.smepro.service.fileservice;
import com.smepro.service.msofficeservice;
import com.smepro.service.userservice;








@Controller
@SessionAttributes({"name","userrole","Member_name","user_img"})
public class darcontroller {
	
	
	@Autowired
	userservice Userservice;
	
	@Autowired
	darDAO DarDAO;	
	
	@Autowired
	userDAO UserDAO;
	
	@Autowired
	fileservice Fileservice;
	
	@Autowired
	fileDAO FileDAO;
	
	@Autowired
	drivesDAO DrivesDAO;	
	
	@Autowired
	folderDAO FolderDAO;
	
	@Autowired
	groupsDAO GroupsDAO;
	
	@Autowired
	notificationDAO NotificationDAO;
	
	@Autowired
	msofficeservice msofficeservices;
	
	@Autowired
	logDAO LogDAO;
	
	@Autowired
	conditionDAO ConditionDAO;
	
	@Autowired
	upload_pnDAO Upload_pnDAO;
	
	@Autowired
	workflowDAO WorkflowDAO;
	
	@Autowired
	edataDAO EdataDAO;
	
	@Autowired
	report_pnDAO Report_pnDAO;
	
	@Autowired
	taskDAO TaskDAO;
	
	@Autowired
	MainService MainServices;
	
	@Autowired
	group_membersDAO Group_membersDAO;
	
	@Autowired
	departmentDAO DepartmentDAO;
	
	@Autowired
	DarService darService;
	
	
	memberDTO memberDTOs = new  memberDTO();
	
	


	
	
	@RequestMapping(value="/dar")
	public String dar(ModelMap model,@RequestParam(value = "type",required=false,defaultValue="-") String type) {	
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		memberDTOs.setId(users.getUser_id());
		
		if(type.equals("pending")) {
			List<dar> dars = DarDAO.getallPending();
			model.addAttribute("dar",dars); 
		}
		else {
			List<dar> dars = DarDAO.getall();
			model.addAttribute("dar",dars); 
		}


	
		model.addAttribute("userid",memberDTOs.getId()); 
		model.addAttribute("type","All"); 
	
		model.addAttribute("nav_dar","nav_dar1"); 
		model.addAttribute("Member_name",users.getUser_name());
		  
		MainServices.addNotificationAttributes(model);
		 
		 
		return "dar";
	}

	
	@RequestMapping(value="/daradd-dar")
	public String daradd(ModelMap model) {	
		
		MainServices.addNotificationAttributes(model);
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		memberDTOs.setId(users.getUser_id());
		List<dar> dars = DarDAO.getall2(users.getUser_id());
		

		
		
		
		model.addAttribute("userid",users.getUser_id()); 
		model.addAttribute("type","Review and add"); 
		model.addAttribute("dar",dars); 
		model.addAttribute("nav_dar","nav_dar2"); 
		model.addAttribute("Member_name",users.getUser_name());
		
		return "dar";
	}

	@RequestMapping(value="/darcheck-dar")
	public String darcheck(ModelMap model) {	
		
		MainServices.addNotificationAttributes(model);
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		memberDTOs.setId(users.getUser_id());
		List<dar> dars = DarDAO.getall3(users.getUser_id());
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
		model.addAttribute("userid",users.getUser_id()); 
		model.addAttribute("type","Approval"); 
		model.addAttribute("dar",dars); 
		model.addAttribute("nav_dar","nav_dar3"); 
		 model.addAttribute("Member_name",usersed.getUser_name());
		return "dar";
	}
	
	
	@RequestMapping(value="/darapp-dar")
	public String darapp(ModelMap model) {	
		
		MainServices.addNotificationAttributes(model);
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		memberDTOs.setId(users.getUser_id());
		List<dar> dars = DarDAO.getall4(users.getUser_id());
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		
		model.addAttribute("userid",users.getUser_id()); 
		model.addAttribute("type","Distribution"); 
		model.addAttribute("dar",dars); 
		model.addAttribute("nav_dar","nav_dar4"); 
		 model.addAttribute("Member_name",usersed.getUser_name());
		return "dar";
	}
	
	
	
	
	@RequestMapping(value="/add-dar")
	public String adddar(ModelMap model) {
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		List<user> responsible =  UserDAO.getall();
		
		List<condition> conditions = ConditionDAO.getalls(usersed.getUser_id());
		model.addAttribute("conditions",conditions); 
		
		
		List<document_no> document_no =  DarDAO.getalldocument();
		model.addAttribute("document_no",document_no);
		
		List<department> departments =  DepartmentDAO.getall();
		model.addAttribute("departments",departments);
		
		model.addAttribute("users",responsible);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		model.addAttribute("User_id",usersed.getUser_id());
		model.addAttribute("nav_dar","nav_dar2"); 
		
		
	 	List<file> files1 = FileDAO.getallfile_code("Quality Manual (QM)");
	 	model.addAttribute("files1",files1);
		
	 	List<file> files2 = FileDAO.getallfile_code("Quality Procedure (QP)");
	 	model.addAttribute("files2",files2);
	 	
		List<file> files3 = FileDAO.getallfile_code("Work Instruction (WI)");
		model.addAttribute("files3",files3);
		
		List<file> files4 = FileDAO.getallfile_code("Supporting Data (SD)");
		model.addAttribute("files4",files4);
		
		List<file> files5 = FileDAO.getallfile_code("Form (FO)");
		model.addAttribute("files5",files5);
		
		
		return "dar/formdar";
	}
	
	
	
	@RequestMapping(value="/edit-dar",method = RequestMethod.GET)
	public String editdar(@RequestParam int id,ModelMap model) {
		dar dars = DarDAO.getbyId(id);
		List<user> responsible =  UserDAO.getall();
		model.addAttribute("users",responsible);
		
		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<condition> conditions = ConditionDAO.getalls(usersed.getUser_id());
		model.addAttribute("conditions",conditions); 
		
	 	List<file> files1 = FileDAO.getallfile_code("Quality Manual (QM)");
	 	model.addAttribute("files1",files1);
		
	 	List<file> files2 = FileDAO.getallfile_code("Quality Procedure (QP)");
	 	model.addAttribute("files2",files2);
	 	
		List<file> files3 = FileDAO.getallfile_code("Work Instruction (WI)");
		model.addAttribute("files3",files3);
		
		List<file> files4 = FileDAO.getallfile_code("Supporting Data (SD)");
		model.addAttribute("files4",files4);
		
		List<file> files5 = FileDAO.getallfile_code("Form (FO)");
		model.addAttribute("files5",files5);
	 
		
		List<department> departments =  DepartmentDAO.getall();
		model.addAttribute("departments",departments);
		
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
		model.addAttribute("nav_dar","nav_dar2"); 
		 model.addAttribute("dar",dars); 
		return "dar/formdar";
	}
	
	
	
	@RequestMapping(value="/edit-dar",method = RequestMethod.POST)
	public String editdarsave(dar dars,ModelMap model) {
		
       if(dars.getDar_request().equals("request1")) {
    	   
     	   if(dars.getDar_doc_id() == null || dars.getDar_doc_id().equals("")) {
   			model.addAttribute("error","error 12"); 
      	        return "error";
   	   }   
     		
    	   
   		if(FileDAO.checkdoc(dars.getDar_doc_id().trim())) {
   			model.addAttribute("error","error 09"); 
	        return "error";
		}  
   		
   		
   		
       }else if(dars.getDar_request().equals("request2")) {
    	   
    	   if(dars.getDar_docold_id() == null) {
    			model.addAttribute("error","error 11"); 
       	        return "error";
    	   }   
    		   
    		   
        	   if(!FileDAO.checkdoc(dars.getDar_docold_id().trim())) {
       			model.addAttribute("error","error 10"); 
       	        return "error";
      		}  
        	     dar darfile =   DarDAO.getbyId(dars.getDar_id());
		         dars.setDar_docold_date(darfile.getDar_docold_date());
		         dars.setDar_docold_end(darfile.getDar_docold_end());
		       	 dars.setDar_docold_rev(darfile.getDar_docold_rev());
		       	 dars.setDar_doc_id(darfile.getDar_doc_id());
		         dars.setDar_doc_name(darfile.getDar_doc_name());  
		         dars.setDar_type(darfile.getDar_type());
		         int rev = Integer.valueOf(darfile.getDar_doc_rev());
		         dars.setDar_doc_rev(String.valueOf(rev));

       }else if(dars.getDar_request().equals("request3") || dars.getDar_request().equals("request4")) {
    	   
    	  
    	   if(dars.getDar_docold_id() == null) {
   			model.addAttribute("error","error 11"); 
      	        return "error";
   	   }   
    	   
    	  
    	   if(!FileDAO.checkdoc(dars.getDar_docold_id().trim())) {
    			model.addAttribute("error","error 10"); 
    	        return "error";
   		}  
    	   
    	   dar darfile =   DarDAO.getbyId(dars.getDar_id());
	         dars.setDar_docold_date(darfile.getDar_docold_date());
	         dars.setDar_docold_end(darfile.getDar_docold_end());
	       	 dars.setDar_docold_rev(darfile.getDar_docold_rev());
	       	 dars.setDar_doc_id(darfile.getDar_doc_id());
	         dars.setDar_doc_name(darfile.getDar_doc_name());  
	         dars.setDar_type(darfile.getDar_type());
	         dars.setDar_doc_rev(darfile.getDar_doc_rev());
    	   
       }

	       
	       long year = 1;
	       if(dars.getDar_doc_end2() == 1) {
	    	   year = 1;
	       }else  if(dars.getDar_doc_end2() == 2) {
	    	   year = 2;
		  }else  if(dars.getDar_doc_end2() == 3) {
			  year = 3;	   
		  }else  if(dars.getDar_doc_end2() == 4) {
			  year = 4;
		  }else  if(dars.getDar_doc_end2() == 5) {
			  year = 5;  
		  }
	       
       
       
       LocalDateTime localDate2 = LocalDateTime.now().plusYears(year);//For reference
   	 	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   	 	String dateend = localDate2.format(formatter);
       

        dars.setDar_doc_end(dateend);
       
       
         String newjd =  String.join(",",dars.getDar_jd());
         dars.setDar_jd(newjd);
       
		 DarDAO.update(dars);
		 dar dar2 = DarDAO.getbyId(dars.getDar_id());
		 model.addAttribute("dar",dar2); 
		 return "redirect:/show-dar?id="+dar2.getDar_id();
	}
	
	
	@PreAuthorize("hasAuthority('ADD-DAR')")
	@RequestMapping(value="/add-dar",method = RequestMethod.POST)
	public String adddarsave(dar dars,ModelMap model) {
		
	       if(dars.getDar_request().equals("request1")) {
	    	   
	    	   
	    	   System.out.println("request getDar_doc_id "+dars.getDar_doc_id());
	    	   
	       	   if(dars.getDar_doc_id() == null || dars.getDar_doc_id().equals("")) {
	    			model.addAttribute("error","error 12"); 
	       	        return "error";
	    	   }   
	      		
	    
	      		if(FileDAO.checkdoc(dars.getDar_doc_id().trim())) {
	      			model.addAttribute("error","error 09"); 
	    	        return "error";
	      		}  
	      		
	  
	      		
	          }else if(dars.getDar_request().equals("request2")) {
	        	  
	       	   if(!FileDAO.checkdoc(dars.getDar_docold_id().trim())) {
	       		model.addAttribute("error","error 10"); 
		        return "error";
	      		}  
	       	   
	       	 file oldfile =   FileDAO.getbyOld_id(dars.getDar_docold_id());
	       	 
	       	 
		         dars.setDar_docold_end(oldfile.getFile_dateend());
		         dars.setDar_docold_date(oldfile.getFile_dateadd());
		       	 dars.setDar_docold_rev(oldfile.getFile_rev());
		       	 dars.setDar_doc_id(oldfile.getFile_no());
		         dars.setDar_doc_name(oldfile.getFile_name_original());  
		         dars.setDar_type(oldfile.getFile_code());
		         
		         int rev = Integer.valueOf(oldfile.getFile_rev())+1;
		         dars.setDar_doc_rev(String.valueOf(rev));
	       	
	          }else if(dars.getDar_request().equals("request3") || dars.getDar_request().equals("request4")) {
	        	  
	      
	        	  
	        	  
	       	   if(!FileDAO.checkdoc(dars.getDar_docold_id().trim())) {
	       		model.addAttribute("error","error 10"); 
		        return "error";
	      		}  
	       	   
	       	   
	         	 file oldfile =   FileDAO.getbyOld_id(dars.getDar_docold_id());
		         dars.setDar_docold_end(oldfile.getFile_dateend());
		         dars.setDar_docold_date(oldfile.getFile_dateadd());
		       	 dars.setDar_docold_rev(oldfile.getFile_rev());
		       	 dars.setDar_doc_id(oldfile.getFile_no());
		         dars.setDar_doc_name(oldfile.getFile_name_original());  
		         dars.setDar_type(oldfile.getFile_code());
		         dars.setDar_doc_rev(oldfile.getFile_rev()); 
	          }
	       
	       
	       long year = 1;
	       if(dars.getDar_doc_end2() == 1) {
	    	   year = 1;
	       }else  if(dars.getDar_doc_end2() == 2) {
	    	   year = 2;
		  }else  if(dars.getDar_doc_end2() == 3) {
			  year = 3;	   
		  }else  if(dars.getDar_doc_end2() == 4) {
			  year = 4;
		  }else  if(dars.getDar_doc_end2() == 5) {
			  year = 5;  
		  }
	       
	       
	       
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 LocalDateTime localDate2 = LocalDateTime.now().plusYears(year);//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
	   	 String date = localDate.format(formatter);
		 String dateend = localDate2.format(formatter);
		 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());    
	   	 
	   	 
			
		
		 
		 
		   dars.setDar_id_add(String.valueOf(usersed.getUser_id()));
	       dars.setDar_date_add(date);
	       dars.setDar_doc_end(dateend);
	       dars.setDar_nameadd(usersed.getUser_name());
	         String newjd =  String.join(",",dars.getDar_jd());
	         dars.setDar_jd(newjd);   
		int dar = DarDAO.save(dars,memberDTOs.getId());
		
		
		
		
	
		
		
		
		
		return "redirect:/show-dar?id="+dar;
	}
	
	
	@RequestMapping(value="/show-dar",method = RequestMethod.GET)
	public String adddarsave(@RequestParam int id,ModelMap model,@RequestParam(required = false) String nav) {	
		
	
		
		
		System.out.println("1");
		 user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		 memberDTOs.setId(users.getUser_id());
		 System.out.println("2"); 
		
		 NotificationDAO.setviewByDar(id,users.getUser_name());
		 

		 
		 dar dars = DarDAO.getbyId(id);
		 List<showdar> olddar = DarDAO.getallbyfile2(dars.getDar_doc_id(),dars.getDar_id());
		 
		 System.out.println("3"); 
		 if(dars.getDar_request().equals("request1") &&  dars.getDar_flow() <  3) {
					 file files = FileDAO.getbyIdrefer(id);	
					 model.addAttribute("file",files); 
			    System.out.println("3.1"); 
		 }
		if(dars.getDar_request().equals("request2") &&  dars.getDar_flow() <  3) {
		 			
			
			        file files = FileDAO.getbyIdrefer(id);
		 			 model.addAttribute("file",files); 
		 			 
		 		
		 		System.out.println("3.2"); 
		 		System.out.println("docold " + dars.getDar_docold_id());  
		 			file files2 = FileDAO.getbyOld_id(dars.getDar_docold_id().trim());
		 			 model.addAttribute("file2",files2); 
		 			System.out.println("Eform " + files2.getEform()); 
		 			
		 		if(files2.getEform() != null && !files2.getEform().isEmpty() ) {	
		 			
		 			if(Integer.valueOf(files2.getEform()) > 0) {
				 		condition  conditionsed = ConditionDAO.getbyid(Integer.valueOf(files2.getEform()));
					 	model.addAttribute("conditionsed",conditionsed); 
		 			}
		 			
		 		}
			 	
			 	System.out.println("3.3"); 
		}
		if(dars.getDar_request().equals("request3") &&  dars.getDar_flow() <  3   ) {
			file files2 = FileDAO.getbyOld_id(dars.getDar_docold_id().trim());
			model.addAttribute("file2",files2); 
			
			
		System.out.println(files2.getDar_doc_date());
			
			if(Integer.valueOf(files2.getEform()) > 0) {
		 		condition  conditionsed = ConditionDAO.getbyid(Integer.valueOf(files2.getEform()));
			 	model.addAttribute("conditionsed",conditionsed); 
 			}
		}
		
		
		List<user> user;
		if(dars.getDar_flow() == 0 ) {
			user = UserDAO.getallgroup("CHECK-DAR");
		}
		else if(dars.getDar_flow() == -1 ) {
			user = UserDAO.getallgroup("CHECK-DAR");
		}
		else if(dars.getDar_flow() == 1 ) {
			user = UserDAO.getallgroup("APP-DAR");
		}
		else {
			user = UserDAO.getallgroup("APP-DAR");
		}
		 
		
		
		
		System.out.println("4"); 
		 
		
 		List<drives> drivess = DrivesDAO.getall();
 		List<folder> folders = FolderDAO.getall2();	
 		int id_add = Integer.parseInt(dars.getDar_id_add());
 		user useradd = UserDAO.getbyId2(id_add);
		
 		System.out.println("5"); 

		String userchecks =null;
		String userapps =null;	
		String useradds=null;
		
 		if(useradd != null) {
 			useradds = useradd.getUser_name();
 		}
		

 		System.out.println("6"); 
		
 		if(dars.getDar_request().equals("request4") &&  dars.getDar_flow() < 3 ) {
 			file files2 = FileDAO.getbyOld_id(dars.getDar_docold_id());
 			model.addAttribute("file2",files2); 
 		}
 		
 		if(dars.getDar_id_check() != null) {
 		int id_check = Integer.parseInt(dars.getDar_id_check());
 		user usercheck = UserDAO.getbyId2(id_check);
 		
 		if(usercheck != null){
	 		userchecks = usercheck.getUser_name();
	 		model.addAttribute("usercheck",usercheck);
 		}else {
 			
 			if(dars.getDar_flow() == 1) {
 				usercheck = UserDAO.getbyId2(dars.getDar_step());
 				userchecks = usercheck.getUser_name();
 		 		model.addAttribute("usercheck",userchecks);
 			}
 			
 			
 			
 		}
 		
 		
 		}
 		
 		if(dars.getDar_id_app() != null	) {
 		int id_app = Integer.parseInt(dars.getDar_id_app());
 		user userapp = UserDAO.getbyId2(id_app);
 		
 		
 		if(userapp != null){
 	 		userapps = userapp.getUser_name();
 	 		model.addAttribute("userapp",userapp);
 		}else {
 			if(dars.getDar_flow() == 2) {
 				userapp = UserDAO.getbyId2(dars.getDar_step());
 				userapps = userapp.getUser_name();
 		 		model.addAttribute("userapp",userapp);
 			}
 			
 		}
 		

 		
 		
 		}
 		
 		user step = UserDAO.getbyId2(dars.getDar_step());
 		model.addAttribute("step",step);
 		
 		
 		if(dars.getDar_flow() == 2 ) {
 		int id_app2 = dars.getDar_step();
 	 	user userapp2 = UserDAO.getbyId2(id_app2);	
 	 	userapps = userapp2.getUser_name();
 		DarDAO.updateresponsible(id,useradds,userchecks,userapps);
 		dars = DarDAO.getbyId(id);
 		}
 		
 		
 		user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		List<notification>  notifications = NotificationDAO.getallrefer(usersed.getUser_name());
		int alert =  NotificationDAO.countnotification(usersed.getUser_name(),0);
		
		int eformid = Integer.valueOf(dars.getEform());
		
        if(eformid > 0 ) {
        	condition conditions = ConditionDAO.getbyid(eformid);
        	model.addAttribute("conditions",conditions);
        }
		
		
        		department department = DepartmentDAO.getbyId(dars.getDar_department());
        		model.addAttribute("department",department);
        
        
 		System.out.println("6"); 
		
		model.addAttribute("notification",notifications);  
		model.addAttribute("alert",alert);
 		
 		
 		model.addAttribute("memberid",users.getUser_id());	
 		model.addAttribute("useradd",useradd);	
 		model.addAttribute("drivess",drivess);
 		model.addAttribute("folders",folders);
         model.addAttribute("flow",user);
   
		model.addAttribute("nav_dar",nav); 
		 model.addAttribute("dar",dars); 
		 model.addAttribute("olddar",olddar); 
		return "dar/dardata";
	}
	
	
	 @RequestMapping(value="/savefile-dar",method=RequestMethod.POST)  
	    public String saveimage( @RequestParam CommonsMultipartFile file,  
	           HttpSession session,@RequestParam int id ,@RequestParam String dartype) throws Exception{  
		 
		String UPLOAD_DIRECTORY ="/filesuploads/temporary"; 
	    ServletContext context = session.getServletContext();  
	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
	    String filenameoriginal = file.getOriginalFilename();  
	    String filename = Fileservice.assertEquals(file.getContentType());
	    long imageFileSize = file.getSize();
	    String Size = Fileservice.printFileSize(imageFileSize);
	    
	   System.out.print(file.getContentType());
	    
     if(filename != null) {
 	    byte[] bytes = file.getBytes();  
 	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
 	         new File(path + File.separator + filename)));  
 	    stream.write(bytes);  
 	    stream.flush();  
 	    stream.close();  
 	   
 	   FileDAO.save(filename, filenameoriginal, file.getContentType(), "temporary", imageFileSize,Size, id,dartype);       
 	   return "redirect:/show-dar?id="+ id; 	   
    }else {

	    
	    byte[] bytes = file.getBytes();  
	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
	         new File(path + File.separator + filename)));  
	    stream.write(bytes);  
	    stream.flush();  
	    stream.close();  
	           
	    return "redirect:/show-dar?id="+ id;
	    
    }
    
    }  
	
	 @RequestMapping(value="/deletefile-dar/{id}",method = RequestMethod.GET)    
	    public String deletesfile( HttpSession session,@PathVariable int id) throws Exception{ 
		 
		     file files = FileDAO.getbyId(id);
		     ServletContext context = session.getServletContext(); 
		     String path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
		    
		     if(files != null) {
			     File fileToDelete = new File(path + File.separator + files.getFile_name());
			     boolean success = fileToDelete.delete();
			 
			     
			     if(success) {
			    	 FileDAO.delete(id);
			     }
		     }
		     
		     
		     return "redirect:/show-dar?id="+ files.getFile_id_refer();
				
	    }     
	 
		@RequestMapping(value="/app-dar",method = RequestMethod.POST)
		public String appdarsave(@RequestParam int id,@RequestParam int flow,
				@RequestParam int app,@RequestParam(value =  "note",required=false) String note
				,@RequestParam(value =  "result",required=false,defaultValue = "0") int result
				,HttpSession session
				,@RequestParam(value =  "mainfolder",required=false) String mainfolder
				,@RequestParam(value =  "subfolder",required=false) List<String> subfolder
				,log logs
				,ModelMap model
				) throws IOException {	 
		
			
	  		String url = null;

	  	
	  	
    	
			
			 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		   	 String datetime = localDate2.format(formatter3);
		   	user by = UserDAO.getbyusername2(Userservice.getLoggedName()); 
			
		 	  String Pathfulls = null;
	    	  String step = null;
	    	  String notes= null;
	         
	    	  dar dars =  DarDAO.getbyId(id);
          	  user usersadd = UserDAO.getbyId2(Integer.valueOf(dars.getDar_id_add()));
          	

          
	    	  
	    	  
            if(flow == 0  || flow == -1) {
            	step ="CREATE";
            	notes="ได้ทำการสร้างใบ DAR ID : "+id;
            	DarDAO.flow(id,1,app);
            	user usersck = UserDAO.getbyId2(app);
            	NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
            	
	 			notification  notifications = new notification();
	 			notifications.setNotification_iduser(usersck.getUser_name());
				notifications.setNotification_iduser2(null);
				notifications.setNotification_alert("คุณมีเอกสารใบ DAR รออนุมัติ");
				notifications.setNotification_note("ผู้ยืนเอกสาร : "+usersadd.getUser_name());
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_refer(Integer.toString(dars.getDar_id()));
				notifications.setNotification_type("Dar workflow (CREATE)");
				NotificationDAO.save(notifications);   
            	
		 		url ="/daradd-dar";
				
				
            }else if(flow == 1 || flow == -2){
            	
            	if(result == 2) {
            		
                  	step ="approver passed";
                	notes="ได้อนุมัติใบ DAR ID : "+id+" ซึ่งผลคือผ่าน";
                	user userapp = UserDAO.getbyId2(app);
                	
            		DarDAO.check(id,result,app,by.getUser_id());
            		
            		NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
            		
            		
    	 			notification  notifications = new notification();
    	 			notifications.setNotification_iduser(userapp.getUser_name());
    				notifications.setNotification_iduser2(null);
    				notifications.setNotification_alert("คุณมีเอกสารใบ DAR รอเเจกจ่ายเอกสาร");
    				notifications.setNotification_note("ผู้ยืนเอกสาร : "+usersadd.getUser_name());
    				notifications.setNotification_date(datetime);
    				notifications.setNotification_read(0);
    				notifications.setNotification_refer(Integer.toString(dars.getDar_id()));
    				notifications.setNotification_type("Dar workflow (passed)");
    				NotificationDAO.save(notifications);  
            		
    				
    				
            		
            	}else {
                  	step ="approver failed";
                	notes="ได้อนุมัติใบ DAR ID : "+id+" ซึ่งผลคือไม่ผ่าน";
                	app = Integer.valueOf(dars.getDar_id_add());
            		DarDAO.uncheck(id,result,app,by.getUser_id());
            	
            		user usersck = UserDAO.getbyId2(Integer.valueOf(by.getUser_id()));
            		
            		NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
            		
    	 			notification  notifications = new notification();
    	 			notifications.setNotification_iduser(usersadd.getUser_name());
    				notifications.setNotification_iduser2(null);
    				notifications.setNotification_alert("คุณมีเอกสารใบ DAR รอตรวจทานเนื่องจากไม่ผ่านการตรวจสอบ");
    				notifications.setNotification_note("ผู้ตรวจสอบ : "+usersck.getUser_name());
    				notifications.setNotification_date(datetime);
    				notifications.setNotification_read(0);
    				notifications.setNotification_refer(Integer.toString(dars.getDar_id()));
    				notifications.setNotification_type("Dar workflow (failed)");
    				NotificationDAO.save(notifications);  
            		
            		
            	}
            	DarDAO.updatenotecheck(id,note);
            	url ="/darcheck-dar";
            	
            	
            	
            }else if(flow == 2){
            	
            	
            	
            	
            	
               	    if(result == 3) {
               	    	

               	    	
               	  
               	    	
               	    	 if(dars.getDar_request().equals("request1")) {
               	    		 

                	  
               	    		if(mainfolder == null  &&  Integer.valueOf(dars.getEform()) == 0) {
            	       			model.addAttribute("error","error 13"); 
            	    	        return "error";
            	    		}  
                	    	 
               	          	
               	      		if(FileDAO.checkdoc(dars.getDar_doc_id().trim())) {
               	       			model.addAttribute("error","error 09"); 
               	    	        return "error";
               	    		}  
               	       		
               	    		 
               	    		 
               	        	step ="distribute(Issue New Document)";
    	                	notes="ได้เเจกจ่ายเอกสารอ้างอิงใบ DAR ID : "+id+"";
    	                	
    	                	
    	                    if(Integer.valueOf(dars.getEform()) == 0 ) {  
    	                    	
    	                    	
    	                    	
               	            file files = FileDAO.getbyIdrefer(dars.getDar_id());
               	            
		               	 	String UPLOAD_DIRECTORY ="/filesuploads/temporary"; 
		            	    ServletContext context = session.getServletContext();  
		            	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
		            	    String path2 = context.getRealPath("/filesuploads/" + mainfolder);  
		            	    folder folders = FolderDAO.getbytype(mainfolder);
		            	    Path fileToMovePath = Paths.get(path + File.separator + files.getFile_name());
		            	    Path targetPath = Paths.get(path2 + File.separator + files.getFile_name());
		            	    String Pathfull;
		            	    drives drivess;
		            	    
		            	    	
		            	    folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
		            	  
		            	    
	                        System.out.println("distribute step1");
		            	    
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
		            	    	throw new RuntimeException("error 02");
		            	    	
		            	    }
		            	
		            	    
		            	    System.out.println("distribute step2");
		           
		            	    String filetype = files.getFile_type();
		            	    String readfile = files.getFile_name();
		            	    
	                        Files.move(fileToMovePath, targetPath);       	    
	                    
	                 	    if(files.getFile_type().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") 
	                 	    		|| files.getFile_type().equals("application/vnd.ms-excel" )
	                 	    				|| files.getFile_type().equals("application/msword") || files.getFile_type().equals("application/octet-stream") 
	                 	    				|| files.getFile_type().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") 
	                 	    				|| files.getFile_type().equals("application/vnd.ms-powerpoint")
	                 	    				|| files.getFile_type().equals("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
	                 	    		{
	                 	               readfile = files.getFile_name().split("\\.")[0]+"_read.pdf";
	                 	               filetype = "application/pdf";
	                 	              boolean cover = msofficeservices.msofficetopdf(path2 + File.separator + files.getFile_name(),path2 + File.separator + readfile,files.getFile_type());
	                 	              
	                 	              
	                 	              //ถ้าเเปลงไม่ได้ส่งต้นฉบับไป
	                 	              if(!cover) { 
	                 	            	  readfile = files.getFile_name();
	                 	            	  
	                 	            		notification  notifications = new notification();
	                 	    	 			notifications.setNotification_iduser(usersadd.getUser_name());
	                 	    				notifications.setNotification_iduser2(null);
	                 	    				notifications.setNotification_alert("ข้อผิดพลาดการเเจกจ่ายเอกสาร ไม่พบการตั้งค่า vbs script ระบบจึงไม่สามารถเเปลงไฟล์เป็น PDF ได้");
	                 	    				notifications.setNotification_note("");
	                 	    				notifications.setNotification_date(datetime);
	                 	    				notifications.setNotification_read(0);
	                 	    				notifications.setNotification_refer(Integer.toString(dars.getDar_id()));
	                 	    				notifications.setNotification_type("Dar workflow (failed)");
	                 	    				NotificationDAO.save(notifications);  
	                 	              
	                 	              	}
	                 	              
	                 	    		} 
	                 	   
	                 	    	
		            	    
	                 	    System.out.println("distribute step3");  
	                        
		            	    
	                 	    if(dars.getDar_doc_id().equals("#AUTO_ID")) {
	                 	    	darService.AutoID(dars);
	                 	    }
	                 	    
	                 	    
		            	 
		            		FileDAO.set(files.getFile_id()
		            				,mainfolder,folders.getFolder_id()
		            				,dars.getDar_doc_id()
		            				,"0"
		            				,dars.getDar_doc_date()
		            				,dars.getDar_doc_end()
		            				,dars.getDar_doc_name()
		            				,Pathfull
		            				,dars.getDar_jd()
		            				);
		            		
		            	    
		            	    
		            	    
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
            	    
		            	   
		            	    System.out.println("distribute step4");
		            	    
		            	    
		            	    
		            	    if(subfolder != null) {
		            	    	
		            	    	
		            	         LocalDateTime localDate = LocalDateTime.now();//For reference
		            		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            		   	 String date = localDate.format(formatter);
		            		     System.out.println("distribute step5");
		            		   	 
		            		  
		            		   	  drives drivessed;
				                  
				                  int drives_ids;
		

				   
				                  System.out.println("distribute step6");
		            		   	 
		            		   	 
                            	for (String foldername : subfolder) {
                            		
                            		folder folderid = FolderDAO.getbytype(foldername);
                            		folder mainfoldersShortcut = FolderDAO.getbyId(folderid.getFolder_idrefer());
                            		 System.out.println("distribute step7" +foldername);
                             	    
				            	    if(mainfoldersShortcut != null) {
				            	    System.out.println("distribute step8" +mainfoldersShortcut.getFolder_idrefer());
				            	    drives_ids= mainfoldersShortcut.getFolder_idrefer();	
				            	    drivessed  = DrivesDAO.getbyId(drives_ids);
				            	    Pathfulls = drivessed.getDrives_name()+"/"+mainfoldersShortcut.getFolder_name()+"/"+folderid.getFolder_name();
				            	    System.out.println("distribute step9");	
				            	    }else {
				            	    	
				            	    System.out.println("distribute step10" +folderid.getFolder_idrefer());	
				            	    drives_ids = folderid.getFolder_idrefer();
				            	    drivessed  = DrivesDAO.getbyId(drives_ids);
				            	    Pathfulls =drivessed.getDrives_name()+"/"+folderid.getFolder_name();	
				            	    System.out.println("distribute step11");	
				            	    
				            	    
				            	    }
                            		
                            		
                            		  FileDAO.saveShortcut(readfile,dars.getDar_doc_id()+" "+dars.getDar_doc_name(),filetype,foldername, imageFileSize
                            				  , folderid.getFolder_id(),mainfolder,Pathfulls,files.getFile_id());       
                            		  System.out.println("distribute step12");	                      	    
                            	}
                              
                            	
                            	
                            }
		            	    
		            	    }
		            	    else {  	
                               
		            	    	file filesave  = new file();
		            	    	
		            	    	filesave.setFile_name_original(dars.getDar_doc_name());
		            	    	filesave.setFile_no(dars.getDar_doc_id());
		            	    	filesave.setFile_rev("0");
		            	    	filesave.setFile_id_refer(dars.getDar_id());
		            	    	filesave.setFile_dateadd(dars.getDar_date_add());
		            	    	filesave.setFile_dateend(dars.getDar_doc_end());
		            	    	filesave.setFile_by(dars.getDar_id_add());
		            	    	filesave.setFile_jd(dars.getDar_jd());
		            	    	filesave.setEform(dars.getEform());
		            	    	filesave.setFile_code(dars.getDar_type());
		            	   		FileDAO.saveeform(filesave);
		            	   	
		            	    	ConditionDAO.setstatus(1,Integer.valueOf(dars.getEform()),dars.getDar_doc_name());
		            	    }
		            	    DarDAO.app(id,result,memberDTOs.getId(),by.getUser_id());
		            		
		            		
               	    	}else if(dars.getDar_request().equals("request2")) {
               	    		
               	    		step ="distribute(Edit)";
    	                	notes="ได้เเจกจ่ายเอกสารอ้างอิงใบ DAR ID : "+id;
    	                 	NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
    	                	file files2 = FileDAO.getbyOld_id(dars.getDar_docold_id());
    	               
    	               if(Integer.valueOf(dars.getEform()) == 0) {
    	            	   
             	            file files = FileDAO.getbyIdrefer(dars.getDar_id());
             	            mainfolder = files2.getFile_path();
             	            
    		               	 	String UPLOAD_DIRECTORY ="/filesuploads/temporary"; 
    		            	    ServletContext context = session.getServletContext();  
    		            	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
    		            	    String path2 = context.getRealPath("/filesuploads/" + mainfolder);  
    		            	    folder folders = FolderDAO.getbytype(mainfolder);
    		            	    Path fileToMovePath = Paths.get(path + File.separator + files.getFile_name());
    		            	    Path targetPath = Paths.get(path2 + File.separator + files.getFile_name());
    		            	   

    		            		 folder mainfolders = FolderDAO.getbyId(folders.getFolder_idrefer());
    			            	    int drives_id;
    			            	    drives_id = folders.getFolder_idrefer();
    			            	    if(mainfolders != null) {
    			            	    drives_id= mainfolders.getFolder_idrefer();	
    			            	    }
    			            	    drives drivess  = DrivesDAO.getbyId(drives_id);
    	          	                
    			            	    long totals = drivess.getDrives_size() - drivess.getDrives_use();
    			            	    if(files.getFile_size() > totals) {
    			            	    	throw new RuntimeException("error 02");
    			            	    	
    			            	    }
    			            	    
    			 
    			            	    String readfile = files.getFile_name();
    	
    			            	    String filetype = files.getFile_type();
    			            	
    			            	    
    			            	    
    		                        Files.move(fileToMovePath, targetPath);       	    
    		                      
    			            	   
    		                        
    		                 	    if(files.getFile_type().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") 
    		                 	    		|| files.getFile_type().equals("application/vnd.ms-excel" )
    		                 	    				|| files.getFile_type().equals("application/msword") || files.getFile_type().equals("application/octet-stream") 
    		                 	    				|| files.getFile_type().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") 
    		                 	    				|| files.getFile_type().equals("application/vnd.ms-powerpoint")
    		                 	    				|| files.getFile_type().equals("application/vnd.openxmlformats-officedocument.presentationml.presentation"))
    		                 	    		{
    		                 	    		
    		                 	    			readfile = files.getFile_name().split("\\.")[0]+"_read.pdf";
    		                 	              msofficeservices.msofficetopdf(path2 + File.separator + files.getFile_name(),path2 + File.separator + readfile,files.getFile_type());
    		                 	             filetype = "application/pdf";
    		    			            		
    		    			            	
    		                 	    		} 
    		                 	   

    		                        
    			            	   
    			          
    			            	    
    			            		FileDAO.set(files.getFile_id(),mainfolder,folders.getFolder_id()
    			            				,dars.getDar_doc_id()
    			            				,dars.getDar_doc_rev()
    			            				,dars.getDar_doc_date()
    			            				,dars.getDar_doc_end()
    			            				,files2.getFile_name_original()
    			            				,files2.getFile_path_full()
    			            				,dars.getDar_jd()
    			            				);
    			            		
    			            		
    			            		
    			            		String newname = files2.getFile_no()+" rev "+dars.getDar_doc_rev()+" "+files2.getFile_name_original();
    			            		
    			            		System.out.println("id " + files.getFile_id());
    			            		System.out.println("file " + readfile);
    			            		System.out.println("newname" + newname);
    			            		System.out.println("filetype " + filetype);
    			            		
    			            		
    			            		FileDAO.setShortcut(files.getFile_id(),readfile,newname,filetype,files2.getFile_id());  
  			            		
    			            		
    			            		
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
		
    		                        FileDAO.cancel3(files2.getFile_id());
    		                
    		                        
               	    	}else { 
               	    		        
    		                		file filesave  = new file();
    		                		int rv = 0;
    		                		if(files2.getFile_rev() != null && !files2.getFile_rev().isEmpty()) {
    		                			rv = Integer.valueOf(files2.getFile_rev())+1;
    		                		}
    		                		
   
    		            	    	filesave.setFile_name_original(dars.getDar_doc_name());
    		            	    	filesave.setFile_no(dars.getDar_doc_id());
    		            	    	filesave.setFile_rev(String.valueOf(rv));
    		            	    	filesave.setFile_id_refer(dars.getDar_id());
    		            	    	filesave.setFile_dateadd(dars.getDar_date_add());
    		            	    	filesave.setFile_dateend(dars.getDar_doc_end());
    		            	    	filesave.setFile_by(dars.getDar_id_add());
    		            	    	filesave.setFile_jd(dars.getDar_jd());
    		            	    	filesave.setEform(dars.getEform());

    		            	   		FileDAO.saveeform(filesave);
    		            	   	    FileDAO.cancel3(files2.getFile_id());
    		            	   		
    		            	   		
    		            	   		condition Conditionold = ConditionDAO.getbyid(Integer.valueOf(files2.getEform()));
    		            	   		
    		                	    ConditionDAO.setstatus(1,Integer.valueOf(dars.getEform()),Conditionold.getCondition_rv(),dars.getDar_doc_name());
    		                	    ConditionDAO.setstatus(-1,Integer.valueOf(files2.getEform()),dars.getDar_doc_name());
    		                	    
    		                	    
    		                        }
    		                	   DarDAO.app(id,result,memberDTOs.getId(),by.getUser_id());
    		                
    		                
    		                
               	    	}else if(dars.getDar_request().equals("request3") ) {
               	    		
               	    		step ="distribute(Cancel Documents)";
    	                	notes="ได้ยกเลิกเอกสารโดยอ้างอิงใบ DAR ID : "+id;
    	                 	NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
    	                	
    	                	System.out.println("dars : "+dars.getDar_docold_id());
    	                	file old = FileDAO.getbyOld_id(dars.getDar_docold_id());
    	                	
    	                	if( Integer.valueOf(old.getEform()) >  0) {
		            	    	ConditionDAO.setstatus(-1,Integer.valueOf(old.getEform()),dars.getDar_doc_name());
               	    		}
    	                	
               	    		FileDAO.cancel(dars.getDar_docold_id());
               	    		
               	    		String darname =  dars.getDar_docold_id()+" "+dars.getDar_doc_name();
               	    		FileDAO.cancel_shotcut(darname);
               	    		DarDAO.app(id,result,memberDTOs.getId(),by.getUser_id());
               	  
	
               	    	}else if(dars.getDar_request().equals("request4")) {
               	    		
               	    		
           	    		
           	    		step ="distribute(Move Documents)";
	                	notes="ได้ย้ายตำเเหน่งโดยเอกสารอ้างอิงใบ DAR ID : "+id;
	                 	NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
	                	String darname =  dars.getDar_docold_id()+" "+dars.getDar_doc_name();	
	                	Path fileToMovePath2 = null;
	                	Path targetPath2 = null;
	                	
			           	    	file files = FileDAO.getbyOld_id(dars.getDar_docold_id());
			           	    	file files2 = FileDAO.getbynamefileshortcut(darname);
			           	    	
			           	    	String UPLOAD_DIRECTORY ="/filesuploads/"+files.getFile_path(); 
			           	 	    ServletContext context = session.getServletContext();  
			           	 	    String path = context.getRealPath(UPLOAD_DIRECTORY);  
			           	 	    String path2 = context.getRealPath("/filesuploads/" + mainfolder);  
			           	 	    
			           	 	    
			           	 	    folder folders = FolderDAO.getbytype(mainfolder);
			           	 	    Path fileToMovePath = Paths.get(path + File.separator + files.getFile_name());
			           	 	    Path targetPath = Paths.get(path2 + File.separator + files.getFile_name());
			           	 	
			           	 	    if(files2 != null) {
				           	 	       fileToMovePath2 = Paths.get(path + File.separator + files2.getFile_name());
					        	 	   targetPath2 = Paths.get(path2 + File.separator + files2.getFile_name());
			           	 	    }   
	
			           	 	    
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
			           	 	    	throw new RuntimeException("error 02");
			           	 	    	
			           	 	    }
			           	 	
			           	 	    
			           	 	    
			
			           	         Files.move(fileToMovePath, targetPath);       	    
			           	        FileDAO.move(files.getFile_id(),mainfolder,Pathfull,folders.getFolder_id());

				           	 	 
			           	  
			           	 		
			           	 		
			           	 	    
				           	 	   if(files2 != null) {
			           	 	    		Files.move(fileToMovePath2, targetPath2);  
			           	 	    		FileDAO.setShortcutnewpath(files2.getFile_name(),mainfolder);
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
			           	 	    
           	 	    
           	 	DarDAO.app(id,result,memberDTOs.getId(),by.getUser_id());
           	 	    
           	 	    
           	    	}
		            		
               	   
               	    	 
		             }else {
		            	 
		            	 NotificationDAO.setviewByDar(dars.getDar_id(),by.getUser_name());
		            	 
		            	 
		                 	step ="Not distribute";
	                	  notes="ไม่เเจกจ่ายเอกสารที่อ้างอิงใบ DAR ID : "+id;
	                	    app = Integer.valueOf(dars.getDar_id_check());
		            		DarDAO.unapp(id,result,app,by.getUser_id());
		            		System.out.println("result"+result+" app"+app);
		            		user userapp = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		            		user usersck = UserDAO.getbyId2(Integer.valueOf(dars.getDar_id_check()));
		            		
		    	 			notification  notifications = new notification();
		    	 			notifications.setNotification_iduser(usersck.getUser_name());
		    				notifications.setNotification_iduser2(null);
		    				notifications.setNotification_alert("คุณมีเอกสารใบ DAR รอตรวจทานเนื่องจากไม่ผ่านการอนุมัติ");
		    				notifications.setNotification_note("ผู้อนุมัติ : "+userapp.getUser_name());
		    				notifications.setNotification_date(datetime);
		    				notifications.setNotification_read(0);
		    				notifications.setNotification_refer(Integer.toString(dars.getDar_id()));
		    				notifications.setNotification_type("Dar workflow (Disapproved)");
		    				NotificationDAO.save(notifications);  
		            		
		            		
		            		
            	    }
            	DarDAO.updatenoteapp(id,note);
            	url ="/darapp-dar";
            	
            }
            
            
            
    	     LocalDateTime localDate = LocalDateTime.now();//For reference
    	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
    	   	 String date = localDate.format(formatter);
    	   	 String time = localDate.format(formatter2);
    	   	 
    	   	 

    	   	user users = UserDAO.getbyusername2(Userservice.getLoggedName()); 
    	   	
    		
    	   	
        	logs.setLog_id_add(users.getUser_id());
    		logs.setLog_step(step);
    		logs.setLog_date(date);
    		logs.setLog_time(time);
    		logs.setLog_note(notes);
    		logs.setLog_refer("0");
    		logs.setLog_darrefer(id);
    		logs.setLog_folder(Pathfulls);
    		LogDAO.save(logs);
            
  
			 return "redirect:"+url;
		}
		

		
		
	    @RequestMapping(value="/delete-dar/{id}",method = RequestMethod.GET)    
	    public String deletesdar(@PathVariable int id,HttpSession session){  
	 	    	
	    	     dar dars = DarDAO.getbyId(id);
	    	
	    	     System.out.println("file check : " + FileDAO.checkdoc2(id));
			if(FileDAO.checkdoc2(id) == true) {
				
		
	    	     file files = FileDAO.getbyIdrefer(id);
			     ServletContext context = session.getServletContext(); 
			     String path = context.getRealPath("/filesuploads/" + files.getFile_path()); 
			     File fileToDelete = new File(path + File.separator + files.getFile_name());
			   
			     if(files != null) {   
				     boolean success = fileToDelete.delete();
				     if(success) {
				    	 FileDAO.delete(files.getFile_id());
				     }
			     }
			     
			     
			}    
			
			
	    	 DarDAO.delete(id,memberDTOs.getId());    
	    	 return "redirect:/daradd-dar";
				
	    }    
	    
	    
	    
	    
	    @RequestMapping(value="/autoid-dar",method = RequestMethod.POST)
		public String autoid(@RequestParam int id,ModelMap model) {
	    	
	    	document_no document_nos= DarDAO.getdocumentbyId(id);
	    	String filecode = null;
	    	for(int i= 0; i < 99;i++) {
	    		
	    		int auto = document_nos.getAutoid() + i;
	    		String formatted = String.format("%02d", auto);
	    		filecode =  document_nos.getDocument_no_pattern()+"-"+formatted;
	    		List<file> files  =  FileDAO.searchdocold(filecode);
	    		
	    		if(files.size() < 1 ) {
	    			i = 99;
	    			}
	    		else {
	    			filecode = null;
	    			DarDAO.setAutoid(document_nos.getAutoid(),i);
	    		}
	    		
	    	}
	    	model.addAttribute("filecode",filecode);  
	    	return "ajax/autoid";
	      
		}
	    
	    
	    @RequestMapping(value="/group-dar",method = RequestMethod.GET)    
	    public String groupdar(ModelMap model){  
	 	    	
	    	 List<document_no> document_nos = DarDAO.getalldocument();
	    	 model.addAttribute("document_nos",document_nos);
	    	 
	    	MainServices.addNotificationAttributes(model);
	    	 return "dar/group_dar";
	    }    
	    
	    @RequestMapping(value="/searchdocold-dar",method = RequestMethod.POST)    
	    public String searchdocold( @RequestParam String data,ModelMap model) throws Exception
	 {
		
		  group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
		 List<file> files  =  FileDAO.searchdocold(data);
		 model.addAttribute("file",files);
		 model.addAttribute("groups",group_memberss.getGroup_id());
		 return "dar/doc_check";
	 }
	    
		 @RequestMapping(value="/searchdocold2-dar",method = RequestMethod.POST)    
		    public String searchdocold2( @RequestParam String data,ModelMap model) throws Exception
		 {
			 
			   group_members group_memberss = Group_membersDAO.getbyId(Userservice.getLoggedName());
			 List<file> files  =  FileDAO.searchdocold(data);
			 model.addAttribute("file",files);
			 model.addAttribute("groups",group_memberss.getGroup_id());
			 return "dar/doc_works_with";
		 }
		 
	    
	    
	    
	    @RequestMapping(value="/addgroup-dar",method = RequestMethod.GET)
	 			public String addgroup(ModelMap model,document_no document_nos) {
	    	MainServices.addNotificationAttributes(model);
	    	
	    	List<department> departments =  DepartmentDAO.getall();
			model.addAttribute("departments",departments);
	 		    	 return "dar/form_group";
	 		      
	 			}
	    
	    @RequestMapping(value="/editgroup-dar",method = RequestMethod.GET)
	 			public String editgroup(ModelMap model,@RequestParam int id) {
	 		    	
	    			 document_no document_nos = DarDAO.getdocumentbyId(id);
	    			 model.addAttribute("document_nos",document_nos); 
	    			

	    			 List<department> departments =  DepartmentDAO.getall();
	    				model.addAttribute("departments",departments);
	    			MainServices.addNotificationAttributes(model);
	 		    	 return "dar/form_group";
	 		      
	 			}
	    
	    
	    @RequestMapping(value="/addgroup2-dar",method = RequestMethod.POST)
			public String addgroup2(document_no document_nos) {
		    	
	    	  System.out.println("document_nos" + document_nos);
		    	 DarDAO.savedocument(document_nos);
		    	 return "redirect:/group-dar";
		      
			}
	    @RequestMapping(value="/editgroup2-dar",method = RequestMethod.POST)
	 			public String editgroup2(document_no document_nos) {
	 		    	
	 		    	 DarDAO.editdocument(document_nos);
	 		    	 return "redirect:/group-dar";
	 		      
	 			}  
	    @RequestMapping(value="/delgroup-dar",method = RequestMethod.GET)
			public String addgroup(@RequestParam int id) {
		    	 DarDAO.deldocument(id);
		    	 
		    
		    	 return "redirect:/group-dar";
		      
			}    
}