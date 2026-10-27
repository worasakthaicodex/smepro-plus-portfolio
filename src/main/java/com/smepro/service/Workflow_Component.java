package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smepro.dao.IdocDAO;
import com.smepro.dao.Production;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.entity.sales_followup;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.Transition;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.department;
import com.smepro.models.edata;
import com.smepro.models.flow;
import com.smepro.models.policies;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.repository.Sales_followupRepository;
import com.smepro.websocket.webmeterSocket;
import com.smepro.models.groups;
import com.smepro.models.notification;




@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Workflow_Component  {

	
	
	private userDAO UserDAO;
	private edataDAO EdataDAO;
	private departmentDAO DepartmentDAO;
	private groupsDAO GroupsDAO;
	private userservice Userservice;
	private  IdocDAO idocDAO;
	private  workflowDAO WorkflowDAO;
	private  LogService logService;
	private  Production Productions;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private MeasurementRulesService measurementRulesService;
	private notificationDAO NotificationDAO;
	private  Sales_followupRepository Sales_followup;
	private workflowservice Workflowservice;
	
	
	 @Autowired
	 private void setMyRepository(
			 userDAO UserDAO,
			 edataDAO EdataDAO,
			 departmentDAO DepartmentDAO,
			 groupsDAO GroupsDAO,
			 userservice Userservice,
			 IdocDAO idocDAO,
			 workflowDAO WorkflowDAO,
			 LogService logService,
			 Production Productions,
			 datasetDAO DatasetDAO,
			 dataset_dataDAO Dataset_dataDAO,
			 MeasurementRulesService measurementRulesService,
			 notificationDAO NotificationDAO,
			 Sales_followupRepository Sales_followup,
			 workflowservice Workflowservice
		
			 ) {
	        	this.UserDAO = UserDAO;
	        	this.EdataDAO = EdataDAO;
	        	this.DepartmentDAO = DepartmentDAO;
	        	this.GroupsDAO = GroupsDAO;
	        	this.Userservice = Userservice;
	        	this.idocDAO = idocDAO;
	          	this.WorkflowDAO = WorkflowDAO;
	           	this.logService = logService;
	        	this.Productions = Productions;
	        	this.DatasetDAO = DatasetDAO;
	        	this.Dataset_dataDAO = Dataset_dataDAO;
	        	this.measurementRulesService = measurementRulesService;
	        	this.NotificationDAO = NotificationDAO;
	        	this.Sales_followup = Sales_followup;
	        	this.Workflowservice = Workflowservice;
	 		}
	
	 
	 
	 
	
	LoadApp LoadApp = new LoadApp();
	 private  Map<String, Object> modelData;
	 
	public  Workflow_Component initiate(LoadApp LoadApp,Map<String, Object> modelData){
		 this.LoadApp = LoadApp;
		 this.modelData = modelData;
		 return this;
	 }
	
	
	public LoadApp  load(){
		
		 Idoc flow =  idocDAO.getDocument(LoadApp.getEdata().getData_id());
		 modelData.put("flow", flow);
		
		 System.out.println(" flow "+flow.getReceiver());
		 
		 
		 user send = UserDAO.getbyId2(flow.getReceiver());
		 modelData.put("send", send);

		 user Creator = UserDAO.getbyId2(flow.getCreator());
		 modelData.put("Creator", Creator);
		 
		 
		 
		 LoadApp.getProcess().getSub_processes().stream().forEach(row->{
			 policies Policies =  EdataDAO.getPolicies(row.getId());
			
			 StringBuilder bulider_user = new StringBuilder();
			 StringBuilder bulider_department = new StringBuilder();
			 StringBuilder bulider_position = new StringBuilder();
			 
			 
			 if(Policies.getRespon() == null) {
					logService.notice(422,"Error (Unprocessable Policies Respon) : Business rule validation failed", "Workflow_Component/load",LoadApp.getProcess().getId());
					return; // มีค่าเท่ากับ continue ใน loop ปกติ
			 }
			 
			 if(!Policies.getRespon().equals("0") && !Policies.getRespon().equals("ALL")) {
				
				String[] separate = Policies.getRespon().split(",");
					for(String access :  separate) {
						String[] access_separate = access.split("_");
						String type = access_separate[0];
						String id = access_separate[1];
						
						if(type.equals("user")) {
							bulider_user.append(id).append(",");
						}
						else if(type.equals("department")) {
							bulider_department.append(id).append(",");
						}
						else if(type.equals("position")) {
							bulider_position.append(id).append(",");
						}
						
					
					}
					List<user> user_all = UserDAO.findInAll(bulider_user.toString());
					List<department> department_all = DepartmentDAO.findInAll(bulider_department.toString());
					List<groups> position_all = GroupsDAO.findInAll(bulider_position.toString());
					
					row.setUser_all(user_all);
					row.setDepartment_all(department_all);
					row.setPosition_all(position_all);
					
					System.out.println(" Policies ++++"+user_all.size());
					
			
			 }
			 
			 
			 
				 row.setPolicies(Policies);
		 });
		 
		 String flowstep = 	 LoadApp.getProcess().getSub_processes().stream()
				 .map(row->String.valueOf(row.getId()) ).collect(Collectors.joining(","));
	
		 List<sub_processes> list = LoadApp.getProcess().getSub_processes();
		 int index = IntStream.range(0, list.size())
			        .filter(i -> list.get(i).getId() == flow.getCurrent_step_int() )
			        .findFirst()
			        .orElse(-1);

		 LoadApp.getProcess().getSub_processes().stream()
				 .filter(row -> row.getId() == flow.getCurrent_step_int() ).findFirst().ifPresent(row-> modelData.put("getstep",row) );

		 
		 
		 
		 sub_processes next = (index != -1 && index + 1 < list.size())
			        ? list.get(index + 1)
			        : null;
		
		// 2. หา Step แรกสุด (สำหรับการตีกลับ / ขอแก้ไข)
			sub_processes first = (list != null && !list.isEmpty())
				        ? list.get(0)
				        : null;
		 

		 isFinalDisposition(next);
		 
		 
		 modelData.put("flowstep", flowstep);
		 modelData.put("next", next);
		 modelData.put("first", first);
		 
		 
		 
		 modelData.put("listsub_processes", LoadApp.getProcess().getSub_processes());
		return this.LoadApp;
	}
	
	
	
	


	@Transactional(rollbackFor = Exception.class)
	public LoadApp  send(){
		 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName());
		 Idoc from =  idocDAO.getDocumentById(LoadApp.getPlatform().getApp_workflow_id());

		 if(LoadApp.getPlatform().getWorkflow_ticket_edit() == 1) { 
			 			returnToEdit(from);
						 EdataDAO.setAcknowledge(LoadApp.getEdata().getData_id(), 0,usersed.getUser_id());
				     this.LoadApp.setHttp_status_code(200);
				     this.LoadApp.setCallback("Success..");
					 return this.LoadApp;
			 }
		
	     if(checkCurrent(from) == false ) { return this.LoadApp;}
	     
	     sub_processes next = nextFlow(from);
	     if(Flow(from,next) == false ) { return this.LoadApp;};	 
	     
	     
	
	     
	     measurementRulesService.IsRules(LoadApp.getEdata());
	     Productions.UseSegmentation(LoadApp.getEdata(),"Flow");
	     
	     
		 if(usersed.getUser_id() != LoadApp.getPlatform().getWorkflow_to()) {
			 EdataDAO.setAcknowledge(LoadApp.getEdata().getData_id(), 0,usersed.getUser_id());
		 }
		 
	     this.LoadApp.setHttp_status_code(200);
	     this.LoadApp.setCallback("Success..");
		 
			return this.LoadApp;
	}
	
	


private void returnToEdit(Idoc from) {
		
        // 1. หาคนที่เราจะตีกลับไปให้ (คุณระบุว่าใช้ from.getReceiver())
        int returnToUser = from.getCreator();
        
        System.out.println(">> ทำการตีกลับเอกสาร (Rework) ไปหาผู้รับ: " + returnToUser);

   	 List<sub_processes> list = LoadApp.getProcess().getSub_processes();
        
        
        // 3. 💥 ปักธงขอแก้ไข (คุณต้องไปเพิ่มเมธอดนี้ใน idocDAO นะครับ)
        idocDAO.updateRequestToEdit(LoadApp.getEdata().getData_id(), 1,this.LoadApp.getPlatform().getWorkflow_closing_status(),returnToUser,list.get(0).getId());

        
        // 4. บันทึก Log Flow ว่าเกิดการ "ตีกลับ"
        LogFlow(from, "REWORK"); 

        // 5. แจ้งเตือน Notification เลียนแบบ forward
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String datetime = localDate.format(formatter);
        
        String note = "เอกสาร   :  " + LoadApp.getEdata().getData_title();
        note += "<br> ระบบ  :  " + LoadApp.getProcess().getName();
        note += "<br> กระบวนการ  :  ตีกลับเพื่อแก้ไข (Rework)";
        note += "<br> เหตุผลที่ขอแก้ไข :  " + this.LoadApp.getPlatform().getWorkflow_remarks_rejected();
        note += "<br> สถานะเอกสาร   :  " + this.LoadApp.getPlatform().getWorkflow_closing_status();
        
        notification notifications = new notification();
        notifications.setNotification_iduser(String.valueOf(returnToUser) ); // ส่งกลับไปหาคนรับตั้งต้น
        notifications.setNotification_iduser2(null);
        notifications.setNotification_alert("⚠️ เอกสารถูกตีกลับเพื่อขอแก้ไข");
        notifications.setNotification_date(datetime);
        notifications.setNotification_read(0);
        notifications.setNotification_type("Action Required");
        notifications.setNotification_process_id(LoadApp.getProcess().getId());
        notifications.setNotification_note(note);
        NotificationDAO.save(notifications);
        
        // 6. ส่ง WebSocket แจ้งเตือนแบบ Real-time
        Map<String, Object> data = new HashMap<>();
        data.put("type", "ACTION_REQUIRED");
        data.put("message", "เอกสารถูกตีกลับเพื่อแก้ไข");
        data.put("process", LoadApp.getProcess().getId()); 
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(data);
            
            // ยิงแจ้งเตือนไปหา returnToUser
            webmeterSocket.sendMessageToOne(String.valueOf(returnToUser), jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}


	@Transactional(rollbackFor = Exception.class)
	public LoadApp  recall(){
		 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		 Idoc from =  idocDAO.getDocumentById(LoadApp.getPlatform().getApp_workflow_id());
		
		 
		 if(from.getSender() != usersed.getUser_id()) { return this.LoadApp;}
		 this.LoadApp.getPlatform().setWorkflow_send(from.getReceiver());
		 this.LoadApp.getPlatform().setWorkflow_to(from.getSender());
		 sub_processes back = backFlow(from);
	     recall(from,back); 
	     
	     measurementRulesService.IsRules(LoadApp.getEdata());
	     
	     this.LoadApp.setHttp_status_code(200);
	     this.LoadApp.setCallback("Success..");
		 
			return this.LoadApp;
	}
	
	
	
		 private boolean Flow(Idoc from, sub_processes next) {
		//TODO Auto-generated method stub
		     if(from == null || next == null) { return false;}	 

		
		     if(checkAccessRecipient(next) == false) {
		    	 this.LoadApp.setHttp_status_code(400);
				 this.LoadApp.setCallback("The recipient doesn't actually exist...");
				 return false;
		     }

		     action(from,next);
		     setFlow(from,next);
		     
		     
			 return true;
		 }


		private void setFlow(Idoc from, sub_processes next) {
			
			if(from.getCurrent_step().equals("Approval")) {
				 Approval(from,next);
				//skipFlow(next);
				return; 
			}
			
		
			forward(from,next);

			
		}


		private void Approval(Idoc from, sub_processes next) {
			//canAccessBudget(from,next);
			if(LoadApp.getPlatform().getWorkflow_appproved() == 1) {
				
				forward(from,next);
				generatedDocCode();
				
			}else {
				sub_processes back = backFlow(from);
				if(back != null) {
					 this.LoadApp.getPlatform().setWorkflow_send(from.getReceiver());
					 this.LoadApp.getPlatform().setWorkflow_to(from.getSender());
					backward(from,back);
				}
				
			}
		}


	
		private void recall(Idoc from, sub_processes back) {
			
			System.out.println( " back " +back.getStep() );
			
			int status = idocDAO.transition(
					LoadApp.getEdata().getData_id(),
					LoadApp.getPlatform().getWorkflow_send()
					,LoadApp.getPlatform().getWorkflow_to()
					,back.getStep()
					,back.getId()
					); 
	
			idocDAO.recallClosing_status(	LoadApp.getEdata().getData_id());
			
			
			if(status == 1) {
				LogFlow(from,"Unsend");
			}else {
				logService.notice(401,"Error  (Validation / Missing field) : Flow not working", "Workflow_Component/setFlow",LoadApp.getEdata().getData_id());
			}
			
		}

		private void forward(Idoc from, sub_processes next) {
			
			if(from.getCurrent_step().equals("Completion") ) { return; }
			
			int status = idocDAO.transition(
					LoadApp.getEdata().getData_id(),
					LoadApp.getPlatform().getWorkflow_send()
					,LoadApp.getPlatform().getWorkflow_to()
					,next.getStep()
					,next.getId()
					); 

			if(status == 1) {
				LogFlow(from,"FLOW");
				
		
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			   	 String datetime = localDate.format(formatter);
				
			 	String note = "เอกสาร   :  "+LoadApp.getEdata().getData_title();
		  		note += "<br> ระบบ  :  "+LoadApp.getProcess().getName();
				note += "<br>  กระบวนการ  :  ส่งต่อเอกสาร";
		  		note += "<br> ขั้นตอนเอกสาร   :  "+next.getDescription();
		  		note += "<br> สถานะเอกสาร   :  "+this.LoadApp.getPlatform().getWorkflow_closing_status();
			   	 
			   	 
			        String to = 	String.valueOf(LoadApp.getPlatform().getWorkflow_to());
			   	 		notification  notifications = new notification();
			 			notifications.setNotification_iduser(to );
						notifications.setNotification_iduser2(null);
						notifications.setNotification_alert("คุณมีเอกสารรอการดำเนินการ");
						notifications.setNotification_date(datetime);
						notifications.setNotification_read(0);
						notifications.setNotification_type("Action Required");
						notifications.setNotification_process_id(LoadApp.getProcess().getId());
						notifications.setNotification_note(note);
						NotificationDAO.save(notifications);
				
				
						
						Map<String, Object> data = new HashMap<>();
				  		data.put("type", "ACTION_REQUIRED");
				  		data.put("message", "คุณมีเอกสารรอการดำเนินการ");
				  		data.put("process", LoadApp.getProcess().getId()); 
				  		try {
				  		    // 2. แปลง Map เป็น JSON String
				  		    ObjectMapper mapper = new ObjectMapper();
				  		    String jsonString = mapper.writeValueAsString(data);
				  		    
				  		    // จะได้ออกมาเป็น: {"type":"ACTION_REQUIRED", "message":"...", "appId":123}
				  		    
				  		    // 3. ส่งก้อน JSON นี้ไปเลย
				  			webmeterSocket.sendMessageToOne(to,jsonString);

				  		} catch (Exception e) {
				  		    e.printStackTrace();
				  		}
						
				
				
			}else {
				logService.notice(401,"Error (Validation / Missing field) : Flow not working", "Workflow_Component/setFlow",LoadApp.getEdata().getData_id());
			}
			
		}
		
		
		private void backward(Idoc from, sub_processes back) {
			
			int status = idocDAO.backtransition(
					LoadApp.getEdata().getData_id(),
					LoadApp.getPlatform().getWorkflow_send(),LoadApp.getPlatform().getWorkflow_to()
					,back.getStep()
					,back.getId()); 
			if(status == 1) {
				LogFlow(from,"Reject");
				
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			   	 String datetime = localDate.format(formatter);
				
				 	String note = "เอกสาร   :  "+LoadApp.getEdata().getData_title();
			  		note += "<br> ระบบ  :  "+LoadApp.getProcess().getName();
					note += "<br> กระบวนการ  :  ตีกลับเอกสาร";
			  		note += "<br> ขั้นตอนเอกสาร   :  "+back.getDescription();
			  		note += "<br> สถานะเอกสาร   :  "+this.LoadApp.getPlatform().getWorkflow_closing_status();
			   	 
			   	 		String to = 	String.valueOf(LoadApp.getPlatform().getWorkflow_to());
			   	 		notification  notifications = new notification();
			 			notifications.setNotification_iduser(to);
						notifications.setNotification_iduser2(null);
						notifications.setNotification_alert("คุณมีเอกสารรอการดำเนินการ");
						notifications.setNotification_date(datetime);
						notifications.setNotification_read(0);
						notifications.setNotification_type("Action Required");
						notifications.setNotification_process_id(LoadApp.getProcess().getId());
						notifications.setNotification_note(note);
						NotificationDAO.save(notifications);
				      
						
						Map<String, Object> data = new HashMap<>();
				  		data.put("type", "ACTION_REQUIRED");
				  		data.put("message", "คุณมีเอกสารรอการดำเนินการ");
				  		data.put("process", LoadApp.getProcess().getId()); 
				  		try {
				  		    // 2. แปลง Map เป็น JSON String
				  		    ObjectMapper mapper = new ObjectMapper();
				  		    String jsonString = mapper.writeValueAsString(data);
				  		    
				  		    // จะได้ออกมาเป็น: {"type":"ACTION_REQUIRED", "message":"...", "appId":123}
				  		    
				  		    // 3. ส่งก้อน JSON นี้ไปเลย
				  			webmeterSocket.sendMessageToOne(to,jsonString);

				  		} catch (Exception e) {
				  		    e.printStackTrace();
				  		}
						
				
			}else {
				logService.notice(401,"Error  (Validation / Missing field) : Flow not working", "Workflow_Component/setFlow",LoadApp.getEdata().getData_id());
			}
		}

		  



		private boolean action(Idoc from, sub_processes next) {
			// TODO Auto-generated method stub
			 if(next == null ) {  return false;}
			 if(!next.getStep().equals("Completion")) {  return true;}
			 if(next.getEndaction_list() == null) {  return true;}
			 
			 System.out.println("Workflow_Component  / action / Endaction_list =  "+next.getEndaction_list());
			 
			 
			  	switch (next.getEndaction_list()) {
			  		case "commit" ->   commit();
			  		case "end" ->   end(from);
			  		case "release" -> release();
			  		case "issued" -> issued();
			  		default->logService.notice(401,"Error  (Validation / Missing field) : End action_list not found", "Workflow_Component/action",LoadApp.getEdata().getData_id());
			  	}	
			 
			 return true;
		}


		
		
		private boolean checkAccessRecipient(sub_processes next) {
					
			 if(next == null ) {  return false;}
			 if(next.getStep().equals("Create") || next.getStep().equals("Completion")) {  return true;}
			 if(next.getPolicies() == null ) {  return false;}
			
			 if(next.getPolicies().getRespon() == null) {
					logService.notice(422,"Error (Unprocessable Policies Respon) : Business rule validation failed", "Workflow_Component/load",LoadApp.getProcess().getId());
					return false;
			 }
			 
			 
			 if(next.getPolicies().getRespon().equals("0") || next.getPolicies().equals("ALL") ) {
				 return false;
			 }
					
			 int  Recipient = LoadApp.getPlatform().getWorkflow_to();
			 if(Recipient == 0 ) {  return false;}
			 
			 
					String[] separate = next.getPolicies().getRespon().split(",");
						for(String access :  separate) {
							
							if(access == null ) {  continue;}
							
							String[] access_separate = access.split("_");
							String id = access_separate[1];
							
							if(id == null ) {  continue;}
							if( !id.matches("\\d+")) {  continue;}
							
							if(Recipient == Integer.valueOf(id)) { return true; }
							
						}
			

			 
			 return false;
			
		}


		private boolean checkCurrent(Idoc flow) {
			
			
			if(flow == null) {
				
				this.LoadApp.setHttp_status_code(400);
			 	this.LoadApp.setCallback("The value sent is not current..(flow is null)");
			 	 return false;
			}
			
			
			 if (flow.getCurrent_step_int() != LoadApp.getPlatform().getWorkflow_step()) {
				 	this.LoadApp.setHttp_status_code(400);
				 	this.LoadApp.setCallback("The value sent is not current..");
				 	 return false;
			    }
			 
			 if (LoadApp.getPlatform().getWorkflow_send() == 0) {
					this.LoadApp.setHttp_status_code(400);
				 	this.LoadApp.setCallback("The value sent is not current..(User Send is null)");
				 	 return false;
			 }
			 
			 return true;
		 }


		private sub_processes nextFlow(Idoc flow) {
			 
			 List<sub_processes> list = LoadApp.getProcess().getSub_processes();
			 int index = IntStream.range(0, list.size())
				        .filter(i -> list.get(i).getId() == flow.getCurrent_step_int() )
				        .findFirst()
				        .orElse(-1);

			 sub_processes next = (index != -1 && index + 1 < list.size())
				        ? list.get(index + 1)
				        : null;
			 
			 
			 policies Policies =  EdataDAO.getPolicies(next.getId());
			 next.setPolicies(Policies);
			 
			 return next;

		 }
		
		private sub_processes backFlow(Idoc flow) {
			 List<sub_processes> list = LoadApp.getProcess().getSub_processes();

			    int index = IntStream.range(0, list.size())
			            .filter(i -> list.get(i).getId() == flow.getCurrent_step_int())
			            .findFirst()
			            .orElse(-1);

			    // Go BACK (index - 1)
			    sub_processes back = (index > 0)
			            ? list.get(index - 1)
			            : null;

			    if (back == null) return null;

			    policies Policies = EdataDAO.getPolicies(back.getId());
			    back.setPolicies(Policies);

			 
			 return back;
		}


		public void LogFlow(Idoc from, String type) {
			  
		 	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		   	 String date = localDate.format(formatter);
		
		   	System.out.println("Workflow_send  "+LoadApp.getPlatform().getWorkflow_send());
		   	
		   	 String comment ="";
		   	 if(	LoadApp.getPlatform().getWorkflow_closing_status() != null) {
		   		 String subclose = "["+LoadApp.getPlatform().getWorkflow_remarks_rejected() +"]";
		   		comment = "Status "+LoadApp.getPlatform().getWorkflow_closing_status()+subclose+", Comment "+LoadApp.getPlatform().getWorkflow_remark();
		   	 }else {
		   		comment = LoadApp.getPlatform().getWorkflow_remark();
		   	 }

			flow flow = new flow();
			flow.setEdata_id(LoadApp.getEdata().getData_id());
			flow.setFlow_comment(comment);
			flow.setFlow_date(date);
			flow.setFlow_send(LoadApp.getPlatform().getWorkflow_send());
			flow.setFlow_step(from.getCurrent_step());
			flow.setFlow_to(LoadApp.getPlatform().getWorkflow_to());
			flow.setFlow_type(type);
			WorkflowDAO.Saveflow(flow);
	} 
	
	
    public Idoc checkAndCreateWorkflow(int sub_process_id,int eformId, int documentId, int documentrv,int currentUserId) {
    	
    	Idoc document = idocDAO.getDocument(eformId,documentId,documentrv); 
    	  

        LocalDateTime localDate = LocalDateTime.now();//For reference
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	String date = localDate.format(formatter);
    	
    	
    	 	if (document == null) {
    	 		Idoc doc = new Idoc();
    	 		doc.setCurrent_step("Create");
    	 		doc.setCurrent_step_int(sub_process_id);
    	 		doc.setEform_id(eformId);
    	 		doc.setDocument_id(documentId);
    	 		doc.setDocument_rv(documentrv);
    	 		doc.setReceiver(currentUserId);
    	 		doc.setCreator(currentUserId);
    	 		doc.setDate(date);
    	 		idocDAO.save(doc);
    	 		document = idocDAO.getDocument(eformId,documentId,documentrv); 
    	  }
    	return document;
    }
    
    

    
    
	private void commit() {

		
		
		if(this.LoadApp.getApp_model() == null) {
			logService.notice(400,"Error (Missing Config) : App model not found", "Workflow_Component/commit",LoadApp.getEdata().getData_id());
			return;
		}
		// TODO Auto-generated method stub
	  	switch (this.LoadApp.getApp_model()) {
				case "edata_registers." -> Productions.SaveMainRegister(this.LoadApp,"register");			
				case "edata_products." -> Productions.SaveMainRegister(this.LoadApp,"product");	
				default->logService.notice(404,"Error : App model["+this.LoadApp.getApp_model()+"] not match", "Workflow_Component/commit",LoadApp.getEdata().getData_id());
		  }	

	
	  	
	}
	
	
	private void end(Idoc from) {
		
		    System.out.println(" end  ....");
		
		
			 LocalDateTime localDate = LocalDateTime.now();//For reference	 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		   	 String datetime = localDate.format(formatter);
		
				idocDAO.end(LoadApp.getEdata().getData_id()
						,this.LoadApp.getPlatform().getWorkflow_closing_status()
						,this.LoadApp.getPlatform().getWorkflow_clear()
						,this.LoadApp.getPlatform().getWorkflow_remarks_rejected()
						,this.LoadApp.getPlatform().getWorkflow_date_end()
						); 
		  		Productions.UseSegmentation(LoadApp.getEdata(),"End");

		  		Map<String, Object> data = new HashMap<>();
				 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		  		EdataDAO.setAcknowledge(LoadApp.getEdata().getData_id(), 0,usersed.getUser_id());
		  		
		  		data.put("type", "ACTION_REQUIRED_ALL");
		  		data.put("message", "คุณมีเอกสารรอการดำเนินการ");
		  		data.put("process", LoadApp.getProcess().getId()); 
		  		data.put("access", this.LoadApp.getPlatform().getWorkflow_clear());

		  		
		  		String note = "เอกสาร   :  "+LoadApp.getEdata().getData_title();
		  		note += "<br> ระบบ  :  "+LoadApp.getProcess().getName();
		  		note += "<br> กระบวนการ  :  ปิดเอกสาร";
		  		note += "<br> ขั้นตอนเอกสาร   :  เสร็จสิ้นเเล้ว";
		  		note += "<br> สถานะเอกสาร   :  "+this.LoadApp.getPlatform().getWorkflow_closing_status();

		  		

		  		
	   	 		notification  notifications = new notification();
	 			notifications.setNotification_iduser("0");
				notifications.setNotification_iduser2(this.LoadApp.getPlatform().getWorkflow_clear());
				notifications.setNotification_alert("คุณมีเอกสารรอการดำเนินการ");
				notifications.setNotification_date(datetime);
				notifications.setNotification_read(0);
				notifications.setNotification_note(note);
				
				notifications.setNotification_type("Action Required");
				notifications.setNotification_process_id(LoadApp.getProcess().getId());
				NotificationDAO.save(notifications);
				LogFlow(from,"Completion");
		  		
				
				
		  		try {
		  		    // 2. แปลง Map เป็น JSON String
		  		    ObjectMapper mapper = new ObjectMapper();
		  		    String jsonString = mapper.writeValueAsString(data);
		  		    
		  		    // จะได้ออกมาเป็น: {"type":"ACTION_REQUIRED", "message":"...", "appId":123}
		  		    
		  		    // 3. ส่งก้อน JSON นี้ไปเลย
		  		  webmeterSocket.sendMessageToAll(jsonString);

		  		} catch (Exception e) {
		  		    e.printStackTrace();
		  		}
				
	}

	private void release() {

		// TODO Auto-generated method stub
	  	switch (this.LoadApp.getApp_model()) {
				//case "boms." -> Productions.Batch_inserthasChanges(this.LoadApp,"bom",this.modelData);

			
		  	}	
		
	}
	private void issued() {

		// TODO Auto-generated method stub
	  	switch (this.LoadApp.getApp_model()) {
				//case "boms." -> Productions.Batch_inserthasChanges(this.LoadApp,"bom",this.modelData);

			
		  	}	
		
	}
	private void isFinalDisposition(sub_processes next) {
		
		if(next == null ) { return; }
		if(next.getEndaction_list() == null ) { return; }
		
			switch(next.getEndaction_list()) {
					case "end" ->{
						dataset enumMain = DatasetDAO.getMain("Workflow_status");
						List<dataset_data> enumData =  Dataset_dataDAO.getall(enumMain.getDataset_id());
						filterClosing_status(enumData);
						next.setClosingStatus(enumData);
						
						dataset enumMain2 = DatasetDAO.getMain("Workflow_status_reject");
						List<dataset_data> enumData2 =  Dataset_dataDAO.getall(enumMain2.getDataset_id());
						next.setClosingStatusReject(enumData2);
					}
			}
		
		
	}


	private void filterClosing_status(List<dataset_data> enumData) {
		if(this.LoadApp.getProcess() == null) { return;}
		if(this.LoadApp.getProcess().getClosing_status() == null) { return;}
		
		// 2. Prepare Allowed List (แปลง String ยาวๆ ให้เป็น Set ของคำที่อนุญาต)
	    // .split(",") : ตัดคำด้วยคอมม่า
	    // .map(String::trim) : ตัดช่องว่างหน้า-หลัง (เผื่อ user พิมพ์วรรคมาด้วย)
	    Set<String> allowedStatuses = Arrays.stream(this.LoadApp.getProcess().getClosing_status().split(","))
	            .map(String::trim)
	            .collect(Collectors.toSet());

	    // 3. Filter Data (ลบตัวที่ 'ไม่มี' ใน allowedStatuses ออกไป)
	    enumData.removeIf(row -> !allowedStatuses.contains(row.getDataset_data_title()));
		
	}

	private void generatedDocCode() {

		sub_processes sub_processes = LoadApp.getSub_processes();
		
		
		System.err.println("ApproveApp->autoid "+sub_processes.getId());
		System.err.println("ApproveApp->autoid "+sub_processes.getActivate_autoid());
		System.err.println("ApproveApp->ID "+sub_processes.getId());
		System.err.println("ApproveApp->Code_app "+sub_processes.getCode_app());
		
		
		
		if(sub_processes.getActivate_autoid() == 1 ) {

			if(LoadApp.getEdata().getDoc() != null) {return;}
			String Code_app = sub_processes.getCode_app();
			String doc = EdataDAO.addDoc(LoadApp.getEdata().getData_id(),Code_app);
			LoadApp.getEdata().setDoc(doc);
		}

	}


	public void Acknowledge() {
		
		if(LoadApp.getPlatform().getApp_item_id2() == 1) {
				generatedDocCode();
		}
		
		
		 Idoc flow =  idocDAO.getDocument(LoadApp.getPlatform().getApp_item_id());
		 Edata_maindata data =  EdataDAO.getEdatamainData(LoadApp.getPlatform().getApp_item_id());
		 if(data != null) {
			 
				switch( data.getEntity()) {
				case "sales_followup" -> {
				    sales_followup followup = Sales_followup.findById(data.getMain_data_id());
				    String sale = followup.getSalesperson(); // ค่าที่ได้คือ "155_นายปึก"
				    
				    if (sale != null && sale.contains("_")) {
				        // 1. ตัดเอาแค่ตัวเลขหน้าเครื่องหมาย _
				        String saleId = sale.split("_")[0]; 
				        
				        // 2. ดึง Refer_user เดิมออกมา (สมมติว่าเป็น "114,113")
				        String currentRefer = flow.getRefer_user(); 
				        
				        // 3. ตรวจสอบและต่อ String (Append)
				        String updatedRefer;
				        if (currentRefer == null || currentRefer.isEmpty()) {
				            updatedRefer = saleId;
				        } else {
				            updatedRefer = currentRefer + "," + saleId;
				        }
				        
				        // 4. Update ค่ากลับเข้าไปใน Object flow
				        flow.setRefer_user(updatedRefer);
				        
				        // เรียก DAO เพื่อบันทึกการเปลี่ยนแปลงของ flow
				        idocDAO.update(flow); 
				    }
				}
				}
				 
				
				 LocalDateTime localDate = LocalDateTime.now();//For reference	 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			   	 String datetime = localDate.format(formatter);
			
			   	 if(LoadApp.getPlatform().getApp_item_id2() == -1) {
						  		Map<String, Object> dataput = new HashMap<>();
						  		dataput.put("type", "ACTION_REQUIRED");
						  		dataput.put("message", "เอกสารไม่ผ่านการตรวจรับ  กรุณาดึงกลับไปแก้ไข");
						  		dataput.put("process", LoadApp.getProcess().getId()); 
						  		dataput.put("access", this.LoadApp.getPlatform().getWorkflow_clear());
				
						  		
						  		String note = "เอกสาร   :  "+LoadApp.getEdata().getData_title();
						  		note += "<br> ระบบ  :  "+LoadApp.getProcess().getName();
						  		note += "<br> กระบวนการ  :  รับเอกสาร";
						  		note += "<br> ขั้นตอนเอกสาร   :  จัดทำ / ดำเนินการ";
						  		note += "<br> สถานะเอกสาร   :  ไม่รับ";
				
						  		
						  		
				
						        String to = 	String.valueOf(flow.getSender());
						        
						        
						        System.out.println(" to "+to);
						         
					   	 		notification  notifications = new notification();
					 			notifications.setNotification_iduser(to);
								notifications.setNotification_iduser2(null);
								notifications.setNotification_alert("เอกสารไม่ผ่านการตรวจรับ  กรุณาดึงกลับไปแก้ไข");
								notifications.setNotification_date(datetime);
								notifications.setNotification_read(0);
								notifications.setNotification_note(note);
								
								notifications.setNotification_type("Action Required");
								notifications.setNotification_process_id(LoadApp.getProcess().getId());
								NotificationDAO.save(notifications);
				
						  		try {
						  		    // 2. แปลง Map เป็น JSON String
						  		    ObjectMapper mapper = new ObjectMapper();
						  		    String jsonString = mapper.writeValueAsString(dataput);
						  		    
						  		    // จะได้ออกมาเป็น: {"type":"ACTION_REQUIRED", "message":"...", "appId":123}
						  		    
						  		    // 3. ส่งก้อน JSON นี้ไปเลย
						  			webmeterSocket.sendMessageToOne(to,jsonString);
				
						  		} catch (Exception e) {
						  		    e.printStackTrace();
						  		}

							  		if(LoadApp.getProcess().getCan_refuse_and_return() == 1) {
							  			Workflowservice.backward(LoadApp);
							  		}
						  		
			   	 }
		 }
		 
		 
		 user usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
        EdataDAO.setAcknowledge(LoadApp.getPlatform().getApp_item_id(),LoadApp.getPlatform().getApp_item_id2(),usersed.getUser_id());
	}


	public void setDocExpires(int data_id, String closing_status_expired, String sub_process_expired) {
					idocDAO.setExpires(data_id,closing_status_expired,sub_process_expired);
	}
	
	



	
}
