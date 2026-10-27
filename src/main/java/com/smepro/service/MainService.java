package com.smepro.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.smepro.dao.actionplanDAO;
import com.smepro.dao.cmDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.formDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.reportDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.condition;
import com.smepro.models.edata;
import com.smepro.models.notification;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.purchase;
import com.smepro.models.showupload_pn;
import com.smepro.models.transaction_send;
import com.smepro.models.user;
import com.smepro.models.workflow;

@Service
public class MainService {

	
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
	
	
	private  darDAO DarDAO;
	private  userDAO UserDAO;
	private  conditionDAO ConditionDAO;
	private  upload_pnDAO Upload_pnDAO;
	private  report_pnDAO Report_pnDAO;
	private  workflowDAO WorkflowDAO;
	
	
	
	
	
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
    		formDAO FormDAO,
    		eformservice eformservices
    		,filemanagerservice Filemanagerservice
    		,FlowServices flowService
    		,UploadPlanManager UploadPlanManagers
    		,PathManager pathManager

    		,darDAO DarDAO
    		,userDAO UserDAO
    		,conditionDAO ConditionDAO
    		,upload_pnDAO Upload_pnDAO
    		,report_pnDAO Report_pnDAO
    		,workflowDAO WorkflowDAO
    		) {
    	
    	

    	
    	
    	

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

        
        this.DarDAO =DarDAO; 
        this.UserDAO =UserDAO; 
        this.ConditionDAO =ConditionDAO; 
        this.Upload_pnDAO =Upload_pnDAO; 
        this.Report_pnDAO =Report_pnDAO; 
        this.WorkflowDAO =WorkflowDAO; 

    }

    

    
    
    
	private void System_idoc(ModelMap model) {
		

	 	user users = UserDAO.getbyusername2(Userservice.getLoggedName());   
	
    //	System.out.println(" users "+users);
    	
    	List<condition> conditions = ConditionDAO.getallGroup();


    	model.addAttribute("conditions",conditions); 

    	
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
		 
		 

	    	List<workflow> workflows = WorkflowDAO.getallApp();
	    //	System.out.println(" test.... ");
	    	
	    	
	    	String Policies=   Userservice.getPoliciesString();
	    	
	    //	System.out.println(" test2.... ");
	    	
	    	workflows.forEach(row->{
				 List<process> process =  EdataDAO.getAllProcess(row.getWorkflow_id());
				
				 

				// System.out.println(" process "+process.size());
				 process.removeIf(rows ->{
					 policies policies =   Userservice.getPolicies(rows.getId());
					  return policies.getId() == 0 && rows .getProgram_type() == 0; 
				 });
				 
				 process.stream().forEach(subrow->{
					 List<edata> rawedata = EdataDAO.getallProcesses(subrow.getId());
					 
					 
					 List<edata> draft = rawedata.stream()
							    .filter(d -> Objects.equals(d.getCurrent_step(), "Create") && d.getCreator() == users.getUser_id() && d.getAlert() == 0)
							    .collect(Collectors.toList());
					
				
					 List<edata> reviewed = rawedata.stream()
							    .filter(d -> (   Objects.equals(d.getCurrent_step(),"Review") 
							    		|| Objects.equals(d.getCurrent_step(),"Approval") 
							    		|| Objects.equals(d.getCurrent_step(), "Clear")  ) 
							    		&& d.getReceiver() == users.getUser_id() 
							    		&& d.getAlert() == 0)
							    .collect(Collectors.toList());
					 
					 
			
						List<transaction_send> transaction_sends = EdataDAO.getAllTransaction_send(subrow.getProcess_join(),"Send",Policies);

						List<transaction_send> transaction_sends2 = transaction_sends.stream()
								    .filter(d -> d.getAlert() == 0)
								    .collect(Collectors.toList());
	 
					// System.out.println("-- join "+subrow.getProcess_join());

					 subrow.setAlert(draft.size()+reviewed.size()+transaction_sends2.size());
					 
				 });
	 
		
			
				 row.setProcess(process);
	    	});
		 
	    	model.addAttribute("workflow",workflows); 
		 
		 
		 
		 
		 
    }


	
	
	public String addNotificationAttributes(ModelMap model) {
		
		
	    user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 	    
	    List<notification> notifications = NotificationDAO.getallrefer(usersed.getUser_name());
	    int alert = NotificationDAO.countnotification(usersed.getUser_name(), 0);

	    model.addAttribute("notification", notifications);  
	    model.addAttribute("alert", alert);
	    model.addAttribute("Member_name", usersed.getUser_name());
	    model.addAttribute("Member_id",usersed.getUser_id());
	    model.addAttribute("user_img", usersed.getUser_img());
	    model.addAttribute("USERNAME", usersed.getUsername());
    	//System_idoc(model);
    	
    	return null;
	}
	
	
	
}

