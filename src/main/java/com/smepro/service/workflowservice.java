package com.smepro.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smepro.dao.IdocDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.condition;
import com.smepro.models.edata;
import com.smepro.models.eformfield;
import com.smepro.models.fieldindex;
import com.smepro.models.flow;
import com.smepro.models.map_key;
import com.smepro.models.notification;
import com.smepro.models.sub_processes;
import com.smepro.models.workflow;
import com.smepro.websocket.webmeterSocket;

@Service
public class workflowservice {

	private conditionDAO ConditionDAO;
	private eformfieldDAO EformfieldDAO;
	private workflowDAO WorkflowDAO;
	private edataDAO EdataDAO;
	private upload_pnDAO Upload_pnDAO;
	private  LogService logService;
	private  IdocDAO idocDAO;
	private notificationDAO NotificationDAO;
	
	@Autowired
	public void setMyRepository(
	    		conditionDAO ConditionDAO
	    		,eformfieldDAO EformfieldDAO
	    		,workflowDAO WorkflowDAO
	    		,edataDAO EdataDAO
	    		,upload_pnDAO Upload_pnDAO
	    		,LogService logService
	    		,IdocDAO idocDAO
	    		,notificationDAO NotificationDAO
	    		){
	        this.ConditionDAO = ConditionDAO;
	        this.EformfieldDAO = EformfieldDAO;
	        this.WorkflowDAO = WorkflowDAO;
	        this.EdataDAO = EdataDAO;
	        this.Upload_pnDAO = Upload_pnDAO;
	        this.logService = logService;
	        this.idocDAO = idocDAO;
	        this.NotificationDAO = NotificationDAO;
	    }
		
	
	

	public void backward(LoadApp LoadApp) {
	    
	    if(LoadApp == null) { return; }
	    
	    Idoc from = idocDAO.getDocument(LoadApp.getEdata().getData_id(), LoadApp.getCondition().getCondition_id(), LoadApp.getCondition().getCondition_rv()); 
	    if(from == null) { return; }

	    // 1. ดึง List ของ sub_processes ทั้งหมดออกมา
	    List<sub_processes> sub_process_list = LoadApp.getProcess().getSub_processes(); 
	    
	    sub_processes back = null;
	    int currentId = from.getCurrent_step_int();

	    // 2. Scan หา ID ที่น้อยกว่า currentId
	    for (sub_processes sp : sub_process_list) {
	        if (sp.getId() < currentId) {
	            // เราต้องการตัวที่ "ใกล้ที่สุด" (คือตัวที่มากที่สุดในบรรดาตัวที่น้อยกว่า currentId)
	            if (back == null || sp.getId() > back.getId()) {
	                back = sp;
	            }
	        }
	    }

	    // 3. ถ้าไม่มี ID ที่น้อยกว่า (back == null) ไม่ต้องทำต่อ
	    if (back == null) {
	        logService.notice(404, "No previous step found to backward", "Workflow_Component/backward", LoadApp.getEdata().getData_id());
	        return;
	    }

	    // 4. ดำเนินการ backtransition โดยใช้ค่าจาก back ที่หาได้
	    int status = idocDAO.backtransition(
	            LoadApp.getEdata().getData_id(),
	            from.getReceiver(), 
	            from.getSender(),
	            back.getStep(), // ชื่อ Step ของตัวก่อนหน้า
	            back.getId());  // ID ของตัวก่อนหน้า

	    if(status == 1) {
	        // บันทึก Log การตีกลับ (Reject/Return ตามที่คุณกำหนดไว้)
	        LogFlow(LoadApp, from, "Reject"); 
	    } else {
	        logService.notice(401, "Error (Validation / Missing field) : Flow not working", "Workflow_Component/setFlow", LoadApp.getEdata().getData_id());
	    }
	}
	
	
	public void LogFlow(LoadApp LoadApp, Idoc from, String type) {
		  
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
	
	/**
	 * Method สำหรับสร้าง ห่วงโซ่สำหรับ workflow
	 * 
	 * 
	 * @param workflow,
     * @return workflow
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	public workflow chain (workflow workflows,int type) {
	  int id = Integer.valueOf(Optional.ofNullable(workflows.getWorkflow_note()).orElse("0"));
	  
	  System.out.println("workflows "+workflows.getWorkflow_note());
	  
			   if(id > 0 ) {
				   condition conditions = ConditionDAO.getbyid(id);
				
		        	workflows.setWorkflow_key1(conditions.getCondition_name());
		        	
		        	
		        	if(conditions != null) {
				        	map_key map_key = ConditionDAO.getMap_keysub(conditions.getCondition_id());
				        	
				        	
				        	if(map_key == null) {
				      		  return null;
				        	}
				        	
				        	 condition conditions2 = ConditionDAO.getbyid(map_key.getMap_key_id_main());
				            StringBuilder key = new StringBuilder();
				            StringBuilder key2 = new StringBuilder();
				        	key.append(conditions.getCondition_name()+" > ");
				        	key.append(conditions2.getCondition_name()+" > ");
				        	
				          	key2.append(conditions.getCondition_id()+",");
				           	key2.append(conditions2.getCondition_id()+",");
				          	
				          	
				        	List<Integer> id_eform = new ArrayList<Integer>();
				        	id_eform.add(conditions.getCondition_id());
				        	
				        	int level = 0;
				        	map_key map_key2 = ConditionDAO.getMap_keysub(map_key == null ? 0 : map_key.getMap_key_id_main());
				           
				        	
				        	while (map_key != null && level <= 20) {
				            	
				            	 level++;
				            	 System.out.println("level" +level);
				        		
				        		
				        		if(map_key2 != null) {
						        		 conditions2 = ConditionDAO.getbyid(map_key2.getMap_key_id_main());	
						        		key.append(conditions2.getCondition_name()+" > ");
						        		key2.append(conditions2.getCondition_id()+",");
						        		id_eform.add(conditions2.getCondition_id());
						        		map_key2 = ConditionDAO.getMap_keysub(map_key2.getMap_key_id_main());
						        		
				        		}
				        	}
				            
 
				        	  List<String> keys1 = Arrays.asList(key.toString().split(" > "));
				              Collections.reverse(keys1);
				              String result1 = String.join(" > ", keys1);
				              List<String> keys2 = Arrays.asList(key2.toString().split(","));
				              Collections.reverse(keys2);
				              String result2 = String.join(",", keys2);
				              
				              
				              
				            workflows.setWorkflow_key1(result1);
				            workflows.setWorkflow_key2(result2);
				            if(id_eform.size() > 0 ) {
				            	
				            	 List<String> id_eformStrings = id_eform.stream()
		                                 .map(String::valueOf)
		                                 .collect(Collectors.toList());
				            	 
				            ConditionDAO.setGroup(workflows.getWorkflow_name(),String.join(",",id_eformStrings));
				            Upload_pnDAO.setGroup(workflows.getWorkflow_name(),String.join(",",id_eformStrings),type);
				                
				            }
				  	
				        }
			   }
			   
			  return  workflows;
			}
	
	
	
	
			/**
			 * Method สำหรับสร้าง ห่วงโซ่สำหรับ workflow
			 * 
			 * 
			 * @param workflow,
		     * @return workflow
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			public void SetPropertyAuto(workflow workflows,int ids) {
				
				
				System.out.println("note " + workflows.getWorkflow_note());
				 fieldindex fieldindexs = new fieldindex();
				 
				  condition conditions = ConditionDAO.getbyid(ids);
					System.out.println("SetPropertyAuto *****1");
				  eformfield good_output =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"good_output");
				  eformfield defective_output =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"defective_output");
				  eformfield product =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"product");
				  eformfield lot =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"lot"); 
				  eformfield material =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"material");
				  eformfield lot_material =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"lot_material");
				  eformfield unit =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"unit");
				  eformfield incoming_or_deliver =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"incoming_or_deliver");
				  eformfield date =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"date");
				  eformfield po =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"po");
				  eformfield timestart =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"timestart");
				  eformfield timeend =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"timeend");
				  eformfield customer =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"customer");
				  
				  eformfield pd =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"production_directive");
				  eformfield pr =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"purchase_requisition");
				  eformfield batch =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"batch");
				  eformfield vc =  EformfieldDAO.getByField_cal_type(conditions.getCondition_id(),conditions.getCondition_rv(),"voucher");
				  
				  System.out.println("SetPropertyAuto *****2");
				  fieldindexs.setFieldindex_type(Optional.ofNullable(conditions.getCondition_formtype()).orElse("general"));
				  fieldindexs.setField_index_idcondition2(conditions.getCondition_main());
				  fieldindexs.setGood_output(good_output == null ? 0 :good_output.getField_field_no());
				  fieldindexs.setDefective_output(defective_output == null ? 0 :defective_output.getField_field_no());
				  fieldindexs.setProduct(product == null ? 0 :product.getField_field_no());
				  fieldindexs.setLot(lot == null ? 0 :lot.getField_field_no());
				  fieldindexs.setMaterial(material == null ? 0 :material.getField_field_no());
				  fieldindexs.setLot_material(lot_material == null ? 0 :lot_material.getField_field_no());
				  fieldindexs.setUnit(unit == null ? 0 :unit.getField_field_no());
				  fieldindexs.setIncoming_or_deliver(incoming_or_deliver == null ? 0 :incoming_or_deliver.getField_field_no());
				  fieldindexs.setDate(date == null ? 0 :date.getField_field_no());
				  fieldindexs.setPo(po == null ? 0 :po.getField_field_no());
				  fieldindexs.setTimestart(timestart == null ? 0 :timestart.getField_field_no());
				  fieldindexs.setTimeend(timeend == null ? 0 :timeend.getField_field_no());
				  fieldindexs.setCustomer(customer == null ? 0 :customer.getField_field_no());
				  
				  fieldindexs.setPd(pd == null ? 0 :pd.getField_field_no());
				  fieldindexs.setPr(pr == null ? 0 :pr.getField_field_no());
				  fieldindexs.setVc(vc == null ? 0 :vc.getField_field_no());
				  fieldindexs.setBatch(batch == null ? 0 :batch.getField_field_no());
				  
				  
				  
				  fieldindexs.setFieldindex_idrefer(workflows.getWorkflow_id());
				  fieldindexs.setField_index_idcondition(conditions.getCondition_id());
				  System.out.println("SetPropertyAuto *****3");
				  
				  
				  WorkflowDAO.savefieldindexs(fieldindexs);
				  
		
			}
			
			
			
			
			/**
			 * Method สำหรับอัพเดต Status ของ workflow
			 * อ่านค่าจาก getallTraceabilityStatus()
			 * 
			 * @param 
			 * @see workflowDAO,edataDAO
		     * @return void
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			public void processAndSaveStatus() {
				

				System.out.println(" -----------setTraceabilityStatus------  process -------");
			
				 LocalDateTime localDate2 = LocalDateTime.now();//For reference	 
			   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			   	 String date = localDate2.format(formatter2);
				
			   	System.out.println(" -----------setTraceabilityStatus------  process1 -------");
			 	List<workflow> workflows = WorkflowDAO.getall();
			 	System.out.println("workflows "+workflows.size());
			 	
			 	
			 	List<edata> data = EdataDAO.getallTraceabilityStatus();
				System.out.println("data "+data.size());
			 	
			 	System.out.println(" -----------setTraceabilityStatus------  process2 -------");
	
			    if (workflows.isEmpty()) {
		            System.out.println(" -----------No workflows data available for  processing. -----------");
		            return; // ออกจากฟังก์ชันถ้าไม่มีข้อมูล
		        }
			 	
			    if (data.isEmpty()) {
		            System.out.println(" -----------No data available for processing. -----------");
		            return; // ออกจากฟังก์ชันถ้าไม่มีข้อมูล
		        }
			    
			    
			    System.out.println("processing. -----------1");
			 	
			 	  
			       List<condition> conditionbatch = new ArrayList<>();  
			 // อัพเดต workflow
			    workflows.forEach(workflow -> {
			        // แยกค่าจาก workflow_key1
			        List<Integer> key1List = List.of(workflow.getWorkflow_key2().split(","))
			                                      .stream()
			                                      .map(String::trim)
			                                      .map(Integer::parseInt)
			                                      .collect(Collectors.toList());

			        // กรอง data ตาม key1List
			        List<edata> filteredData = data.stream()
			            .filter(edata -> key1List.contains(edata.getData_eformid()) && edata.getData_status() != null && !edata.getData_status().trim().isEmpty())
			            .collect(Collectors.toList());

			        
			  	   System.out.println("key1List  size--------------"+key1List.size()); 
			        
			  	  List<String> status = new ArrayList<>();
		
			      key1List.forEach(ID->{
			        	
			    	  
			    	   System.out.println("key1List  work--------------"+ID);
			    	  
			        	  List<edata> filteredsingleData = filteredData.stream()
						            .filter(singleedata ->  ID.equals(singleedata.getData_eformid())  && singleedata.getData_status() != null && !singleedata.getData_status().trim().isEmpty())
						            .collect(Collectors.toList());
			        	
			       	   System.out.println("filteredsingleData  size--------------"+filteredsingleData.size());
			        	  
			       	  if(filteredsingleData.size() > 0) { 
			       		  
				       	   System.out.println("filteredsingleData  size > 0 ");
						        	  long totalCount = filteredsingleData.size();
						        	  
						        	    Map<String, Long> statusCounts = filteredsingleData.stream()
									            .collect(Collectors.groupingBy(edata::getData_status, Collectors.counting()));
						        	    
						        	    Map.Entry<String, Long> maxEntry = statusCounts.entrySet().stream()
									            .max(Map.Entry.comparingByValue())
									            .orElseThrow(() -> new IllegalStateException("No status found in filtered data"));
			
			
									    String dominantStatus = maxEntry.getKey();
									   long count = maxEntry.getValue();
									   //double percentage = (double) count / totalCount * 100;
									   //int score = getScore(dominantStatus, percentage);
									   int score = getScore(dominantStatus);  
									   
									   condition conditions = new condition();
									   conditions.setNew_status(score);
									   conditions.setCondition_id(ID);
									   conditionbatch.add(conditions);
									   status.add(String.valueOf(score));
									   
			       	  }else {

						   status.add("0");
			       	  }
						   
			        });
			      
			      
			      workflow.setWorkflow_key4(String.join(",",status));
			        
			 /*       
			        long totalCount = filteredData.size();
			        
			        
			       // คำนวณสถานะจากข้อมูลที่กรองแล้ว
			        Map<String, Long> statusCounts = filteredData.stream()
			            .collect(Collectors.groupingBy(edata::getData_status, Collectors.counting()));

			        // หาสถานะที่มีจำนวนมากที่สุด
			        Map.Entry<String, Long> maxEntry = statusCounts.entrySet().stream()
			            .max(Map.Entry.comparingByValue())
			            .orElseThrow(() -> new IllegalStateException("No status found in filtered data"));

			        // ใช้สถานะที่มีจำนวนมากที่สุดในการคำนวณเปอร์เซ็นต์และคะแนน
			        String dominantStatus = maxEntry.getKey();
			        long count = maxEntry.getValue();
			       // double percentage = (double) count / totalCount * 100;
			       // int score = getScore(dominantStatus, percentage);
			        int score = getScore(dominantStatus);  
			        
			        System.out.println("maxEntry : " + maxEntry.getKey()+" "+ totalCount+" "+ count);
			     	 System.out.println("score : " + score);
			        
			        
			        // อัพเดต `data_value1` ให้กับทุก `edata` ใน `filteredData`
			        filteredData.forEach(edata -> {
			            edata.setData_value1(String.valueOf(score));
			        });

			        
			        
			        // หลังจากคำนวณและอัพเดต data_value1 แล้ว ค่อยสร้าง dataMap จาก filteredData
			        Map<Integer, String> dataMap = filteredData.stream()
			            .collect(Collectors.toMap(edata::getData_eformid, edata::getData_value1));
              
			        
			   
			        
			        // อัพเดต workflow_key4 ด้วยค่าจาก dataMap ตาม data_idrefer ของข้อมูลที่กรองแล้ว
			        key1List.forEach(key -> {
			            if (dataMap.containsKey(key)) {
			            	// System.out.println("setWorkflow_key4 status : " + dataMap.get(key));
			              
			            }
			        });
			          */
			    });
		         
			       
			    WorkflowDAO.update(workflows,1000);
			    ConditionDAO.updateStatus(conditionbatch,1000);
			  // data.forEach(edata -> edata.setAuto_read(1));
			  //  EdataDAO.update(data,1000);
			    
			   System.out.println("End -----------setTraceabilityStatus--------------");
				
				
			}
			
			
			//process taging
			
		    private static int getScore(String status, double percentage) {
		        if ("Good".equals(status)) {
		            if (percentage > 80) return 9; 
		            if (percentage > 70) return 8;
		            if (percentage >= 50) return 7;
		        } else if ("Normal".equals(status)) {
		            if (percentage > 80) return 6;
		            if (percentage > 70) return 5;
		            if (percentage >= 50) return 4;
		        } else if ("Bad".equals(status)) {
		            if (percentage > 80) return 1;
		            if (percentage > 70) return 2;
		            if (percentage <= 70) return 3;
		        }
		        return 0; // ค่าเริ่มต้นถ้าไม่ตรงเงื่อนไข
		    }
		    
		    
		    
	//process taging
			
		    private static int getScore(String status) {
		        if ("Good".equals(status)) {
		            return 2; 
		        } else if ("Normal".equals(status)) {
		        	 return 1; 
				  
		        } else if ("Bad".equals(status)) {
		        	 return -1; 
		        }
		        return 0; // ค่าเริ่มต้นถ้าไม่ตรงเงื่อนไข
		    }
			
}









