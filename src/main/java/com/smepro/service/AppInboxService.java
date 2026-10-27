package com.smepro.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.smepro.dao.Production;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.Links;
import com.smepro.models.UseExtension;
import com.smepro.models.condition;
import com.smepro.models.edata;
import com.smepro.models.eformfield;
import com.smepro.models.keymap;
import com.smepro.models.process;
import com.smepro.models.registerkey;
import com.smepro.models.user;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class AppInboxService {
	
	private  Map<String, Object> modelData = new HashMap<>();
	private int Service_step  = 0;
	private int process_id  = 0;
	
	private int draft_count  = 0;
	private int reviewed_count  = 0;
	private int refer_count  = 0;
	private int send_count  = 0;
	private int published_count  = 0;

	
	private List<edata> rawedatas = new ArrayList<edata>();
	List<edata> draft = new ArrayList<edata>();
	List<edata> reviewed = new ArrayList<edata>();
	List<edata> sends = new ArrayList<edata>();
	List<edata> published = new ArrayList<edata>();
	List<edata> refered = new ArrayList<edata>();
	List<edata> joined = new ArrayList<edata>();
	
    process APP_JOIN;
	user usersed;
	
	
	private edataDAO EdataDAO;
	private userDAO UserDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private eformfieldDAO EformfieldDAO;
	private Production Productions;
	private workflowDAO WorkflowDAO;
	
	
	
    @Autowired
    public void setMyRepository(
    		 edataDAO EdataDAO
    		 ,userDAO UserDAO
    		 ,userservice Userservice
    		 ,conditionDAO ConditionDAO
    		 ,eformfieldDAO EformfieldDAO
    		 ,Production Productions
    		 ,workflowDAO WorkflowDAO
    		) {
        this.EdataDAO =EdataDAO; 
        this.UserDAO =UserDAO; 
        this.Userservice =Userservice; 
        this.ConditionDAO =ConditionDAO; 
        this.EformfieldDAO =EformfieldDAO; 
        this.Productions =Productions; 
        this.WorkflowDAO =WorkflowDAO; 
    }
	
	
	
	public AppInboxService initiate(){
		
		
		
		rawedatas.clear();
		draft.clear();
		sends.clear();
		reviewed.clear();
		published.clear();
		refered.clear();
		joined.clear();
		
		
		usersed = UserDAO.getbyusername2(Userservice.getLoggedName()); 
		Service_step  = 0;
		process_id = 0;
		
		draft_count = 0;
		reviewed_count = 0;
		refer_count = 0;
		send_count = 0;
		published_count = 0;
		 process APP_JOIN = new  process();
		
		return this;
	}
	
	
	public AppInboxService loadProcesses(int process_id){
	   // rawedatas = EdataDAO.getallProcesses(process_id);
		this.process_id = process_id;
		
		
		     String Policies = Userservice.getPoliciesString(); 
			usersed.setAuth(Policies);
		
			draft_count = EdataDAO.countDraft(this.process_id, usersed.getUser_id() );
			reviewed_count = EdataDAO.countReviewed(this.process_id, usersed.getUser_id() );
			send_count= EdataDAO.countSends(this.process_id, usersed.getUser_id() );
			refer_count= EdataDAO.countSends(this.process_id, usersed.getUser_id() );
			
			List<Links> listLinks = WorkflowDAO.getLinksByidrefer(process_id);
				Optional<Links> Relationship =  listLinks.stream().filter(row->row.getRelationship() != null).findFirst();
				 if(Relationship.isPresent()) {
					 Links datas =  Relationship.get(); 
				        APP_JOIN = EdataDAO.getProcess(Integer.valueOf(datas.getLink_to()) );
						modelData.put("APP_JOIN", APP_JOIN);
						
				 }
			 if(APP_JOIN != null) {
				 	List<edata>  relationList = EdataDAO.getallProcessesRelation(APP_JOIN.getId(),APP_JOIN.getCurrent_status(),usersed);
				 	refer_count=relationList.size();
			 }
			
			

			published_count = 0;
			Service_step = 1;
		
			
			
	
		
		
		
		return this;
	}
	
	
	public AppInboxService loadProcessesBylink(UseExtension  useextension, List<ExtensionParameter> extensionParameter,String code){
		
		
		if(useextension.getExtension_id() != 2 ) {return this;}
		
		process process = EdataDAO.getProcess(useextension.getUse_process_id());
		condition conditions = ConditionDAO.getbyid(process.getId_condition());
		List<eformfield> eformfields = EformfieldDAO.getall(conditions.getCondition_id(),conditions.getCondition_rv());
	  	List<keymap> keymaps = EdataDAO.getAllKeyMaps(process.getId());
		
	  	
	
	  	
	  	eformfields.stream().forEach(row->{
	  		
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
	  	
	    int type =useextension.getExtension_id();
	    
	    
		
	    
	
	    
	    switch (type) {
		        case 55 -> Productions.getProduction_recordbyUseextension(useextension, modelData);
		        case 2->{
		        	
		        	
		        	// 1. หา Parameter ตัวที่ชื่อ "log_key1" ก่อน
		        	extensionParameter.stream()
		        	    .filter(row -> "log_key1".equals(row.getParameter_keyname()))
		        	    .findFirst()
		        	    .ifPresent(paramRow -> {
		        	        
		        	        // ได้ ID ของ Field มา (แปลงเป็น int รอไว้เลย)
		        	        String columnStr = paramRow.getParameter_column();
		        	        
		        	        if (columnStr != null && !columnStr.isEmpty()) {
		        	            try {
		        	                int targetFieldId = Integer.parseInt(columnStr);

		        	                // 2. ไปหา Field นั้นใน eformfields (ใช้ filter + findFirst เพื่อหยุดทันทีที่เจอ)
		        	                eformfields.stream()
		        	                    .filter(field -> field.getField_id() == targetFieldId)
		        	                    .findFirst() // <--- พระเอกอยู่ตรงนี้ เจอแล้วหยุดวน Loop ทันที
		        	                    .ifPresent(field -> {
		        	                        
		        	                        // 3. เจอของครบแล้ว ค่อย Query DB
		        	                        String keynamecolumn = "data_value" + field.getField_postion();
		        	                        
		        	                        // เรียก DB ครั้งเดียวจบ
		        	                        List<edata> resultList = EdataDAO.getallProcesses(
		        	                            useextension.getUse_process_id(), 
		        	                            keynamecolumn, 
		        	                            code
		        	                        );

		        	                        // update rawedatas (ถ้าจำเป็นต้องใช้ข้างนอก)
		        	                        // rawedatas = resultList; 

		        	                        modelData.put("box", resultList);
		        	                        modelData.put("Extension_id", 2);
		        	                        modelData.put("draft_count", resultList.size());
		        	                    });

		        	            } catch (NumberFormatException e) {
		        	                System.err.println("Error parsing column ID: " + columnStr);
		        	            }
		        	        }
		        	    });
		   
				  
		        }
		        default ->{
		    		rawedatas = EdataDAO.getallProcesses(useextension.getUse_process_id());
		    		modelData.put("box", rawedatas);
		    		modelData.put("draft_count", rawedatas.size());
		        }
	    }

		return this;
	}
	
	
	
	
	public AppInboxService filterMap(String map){
		 switch (map) {
         case "reviewed" -> filterInbox();
         case "sends" -> filterSends();
		 }
		
		return this;
	}


	public AppInboxService filterDraft(){
		  draft = EdataDAO.getallProcessesDraft(this.process_id, usersed.getUser_id() );
		  modelData.put("box", draft);
		return this;
	}
	
	public AppInboxService filterInbox(){
		
		reviewed = EdataDAO.getallProcessesReviewed(this.process_id, usersed.getUser_id() );
		  modelData.put("box", reviewed);

		return this;
	}
	
	
	public AppInboxService filterSends(){
		
		sends = EdataDAO.getallProcessesSends(this.process_id, usersed.getUser_id() );
		  modelData.put("box", sends);
		
		  
		return this;
	}
	public AppInboxService filterPublished(){
		
		published = EdataDAO.getallProcessesPublished(this.process_id, usersed.getUser_id() );
		  modelData.put("box", published);
		
		
		published = rawedatas.stream()
			    .filter(d -> Objects.equals(d.getCurrent_step(), "Completion") 
			            && d.getRefer_user() != null 
			            && Arrays.asList(d.getRefer_user().split(",")).contains(String.valueOf(usersed.getUser_id())))
			    .collect(Collectors.toList());
		  
		return this;
	}
	
	public AppInboxService filterIn_refer(){
		
		 if(APP_JOIN == null) {return this;}
		   joined= EdataDAO.getallProcessesRelation(APP_JOIN.getId(),APP_JOIN.getCurrent_status(),usersed);
			modelData.put("box", joined);
		  
		return this;
	}
	
	
	public AppInboxService filterRefer(){
		
		  refered = EdataDAO.getallProcessesRefered(this.process_id, usersed.getUser_id() );
		  modelData.put("box", refered);
		
	return this;
}
	

    
    public AppInboxService count() {
    		modelData.put("draft_count", draft_count);
    		modelData.put("reviewed_count", reviewed_count);
    		modelData.put("send_count", send_count);
    		modelData.put("refer_count", refer_count);
    		modelData.put("published_count", published_count);
    	    return this;
    }
    

	
	public Map<String, Object> getModelData() {
	    return modelData;
	}
		
	
}
