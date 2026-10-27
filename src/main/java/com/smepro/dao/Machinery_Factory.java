package com.smepro.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


import com.smepro.entity.machine_activity;
import com.smepro.entity.maintenance_requests;
import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.request_for_quotation;
import com.smepro.models.LoadApp;
import com.smepro.models.bom;
import com.smepro.models.edata_procurement;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.repository.Machine_activityRepository;
import com.smepro.repository.Maintenance_requestsRepository;
import com.smepro.service.PlatformComponent;


@Component
public class Machinery_Factory  extends PlatformComponent {

	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	
	
	private Machine_activityRepository Machine_activity;
	private Maintenance_requestsRepository Maintenance_requests;
	private edataDAO EdataDAO;
	private  SaveApp_Component SaveApp_Components;
	private  MessageSource messageSource;
	private  ApproveApp_Component ApproveApp_Components;
	
	
	
	  @Autowired
		public void setMyRepository(
				Machine_activityRepository machine_activity,
				Maintenance_requestsRepository maintenance_requests,
				SaveApp_Component SaveApp_Components,
				MessageSource messageSource,
				ApproveApp_Component ApproveApp_Components,
				edataDAO EdataDAO
				) {
		  
		  this.Machine_activity = Machine_activity;
		  this.Maintenance_requests = Maintenance_requests;
		  this.SaveApp_Components = SaveApp_Components;
		  this.EdataDAO = EdataDAO;
		  this.messageSource = messageSource;
		  this.ApproveApp_Components = ApproveApp_Components;
	  }
	  
	  
	  public void LoadTransactions(LoadApp LoadApp, Map<String, Object> modelData) {
			  
			  if(LoadApp.getRegister_count() > 0) {
				  Register(LoadApp,modelData);
			  }
			  else if(LoadApp.getProcessing_count()  > 0) {
				  Processing(LoadApp,modelData);
			  }
			  else if(LoadApp.getModify_count() > 0) {
				  Modify(LoadApp,modelData);
			  }
			  else if(LoadApp.getContinue_count() > 0) {
				  Continue(LoadApp,modelData);
			  }
			  else  {
				  Action(LoadApp,modelData);
			  }

		  
	  }


	private void Action(LoadApp LoadApp, Map<String, Object> modelData) {
		int eformId = LoadApp.getEdata().getData_id();
	  	switch (LoadApp.getApp_model()) {
			case "machine_activitys." -> transactionexposeAs(Machine_activity.findByEdataId(eformId),"machine_activity",modelData);
			case "maintenance_requests." -> transactionexposeAs(Maintenance_requests.findByEdataId(eformId),"maintenance_request",modelData);
			case "others." -> transactionexposeAs(EdataDAO.getOther(eformId),"other",modelData);
	  	}	
	}


	private void Continue(LoadApp LoadApp, Map<String, Object> modelData) {
		// TODO Auto-generated method stub
		
	}


	private void Modify(LoadApp LoadApp, Map<String, Object> modelData) {
		// TODO Auto-generated method stub
		
	}


	private void Processing(LoadApp LoadApp, Map<String, Object> modelData) {
		// TODO Auto-generated method stub
		
	}


	private void Register(LoadApp LoadApp, Map<String, Object> modelData) {
		// TODO Auto-generated method stub
		
	}


	
	 private void transactionexposeAs(Object object, String type, Map<String, Object> modelData) {
		 modelData.put("transaction", object);
		 modelData.put("edata_type", type);
		 modelData.put("refer_type", type);	
	}
	 private void transactionexposeAs(Object object, String type, String type2, Map<String, Object> modelData) {
		 modelData.put("transaction", object);
		 modelData.put("edata_type", type);
		 modelData.put("refer_type", type2);	
	}
	
	
	
	
	 /**
		 * ==========================================================
		 * 📥 Load Sub-Table Batch Data
		 * ==========================================================
		 * Loads and displays the data batch for the sub-table (child list)
		 * based on the provided type from the LoadApp context.
		 *
		 * @param LoadApp   The application context containing user/session data
		 * ----------------------------------------------------------
		 *  ✅ Typical use: Loading detail rows for a selected master record
		 * ==========================================================
		 * @param modelData 
		 */
		public void LoadBatch_insert(LoadApp LoadApp,String type, Map<String, Object> modelData) {
			 DisplayActionbatch(LoadApp,type,modelData);
		}
		  
	  
	  
	  
	private <T> void DisplayActionbatch(LoadApp LoadApp, String string, Map<String, Object> modelData) {

		  if (string.equals("machine_activity")) {
			  Machine_activity(LoadApp,modelData);
		  }
		  else  if (string.equals("maintenance_requests")) {
			  Maintenance_requests(LoadApp,modelData);
		  }
		  else  if (string.equals("other")) {
			  Other(LoadApp,modelData);
		  }
	
	}
	
	
	private<T> void Machine_activity(LoadApp LoadApp, Map<String, Object> modelData) {
		 List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<machine_activity> SubDataDTOS = Machine_activity.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			 modelData.put("item",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);
		    
	}
  
	
	private<T> void Maintenance_requests(LoadApp LoadApp, Map<String, Object> modelData) {
		 List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<maintenance_requests> SubDataDTOS = Maintenance_requests.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			 modelData.put("item",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);
		    
	}
  
	
	private<T> void Other(LoadApp LoadApp, Map<String, Object> modelData) {
		  List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<other> SubDataDTOS = EdataDAO.getAllEdata_other_item(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			 modelData.put("item",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);
		    
	}


	
	
	public void LoadhasChanges(LoadApp loadApp, String tag,Map<String, Object> modelData) {
		if (tag.equals("machine_activity")) {
			 List<machine_activity> list =  (List<machine_activity>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
		else if (tag.equals("maintenance_request")) {
			 List<maintenance_requests> list =  (List<maintenance_requests>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
		else if (tag.equals("other")) {
			 List<other> list =  (List<other>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }

	}
	
	
	
	public void Batch_inserthasChanges(LoadApp LoadApp, String tag,Map<String, Object> modelData) {
		Batch_process(LoadApp,tag,modelData);
	}

	private <T extends Identifiable> void Batch_process(LoadApp LoadApp, String tag,Map<String, Object> modelData) {
		
		
		 T objects = null;
		 String object_type = "";
		 String tempId = "";
		 double total =0.0;
		 Double Doubletotal =0.0;
		 String formattedTotal ="0.0";	
		 int  item_ids = 0;
		 Object Actionbatch;	
		 
		 
		  if (tag.equals("machine_activity")) {
			  
			  if(LoadApp.getMachine_activity().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getMachine_activity().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getMachine_activity();
				tempId = LoadApp.getTempId();
				machine_activity  item = (machine_activity) objects;
				item.setTempId(tempId);
		        
		  }
		  else  if (tag.equals("maintenance_request")) {
			  if(LoadApp.getMaintenance_requests().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getMaintenance_requests().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getMaintenance_requests();
				tempId = LoadApp.getTempId();
				maintenance_requests  item = (maintenance_requests) objects;
				item.setTempId(tempId);
		  }
		  else  if (tag.equals("other")) {
			  
			  if(LoadApp.getProduction_standard().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getProduction_standard().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getOther();
				tempId = LoadApp.getTempId();
				other  item = (other) objects;
				item.setTempId(tempId);
		  }
		 
		 
		 
		 
				 
	
		  Actionbatch = LoadApp.getActionbatch();
          hasChanges(objects,LoadApp,item_ids,Actionbatch);
          modelData.put("item", Actionbatch);
          LoadApp.setActionbatch(Actionbatch);
	}
	
	

	private <T extends Identifiable> void hasChanges(T objects,LoadApp LoadApp,int id,Object actionbatch) {
		
		List<T> batch = (List<T>)actionbatch;
		
		
  		System.out.print("Item_id "+LoadApp.getItem_id());
		
		  		  if(LoadApp.getItem_id() != null ) {
		  			  
		  		  
		  			int indexToRemove = Integer.valueOf( LoadApp.getItem_id());
		  			batch.remove(indexToRemove); 
		  			batch.add(indexToRemove,(T) objects); 
				  }else {
					  batch.add((T) objects); 
				  }
		  		  
		  		
	}
	
	
	private  <T> List<T> Actionbatch(String tempId,LoadApp LoadApp) {
		HttpSession session = LoadApp.getSession();
		List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);
		if (Actionbatch == null) {  
			Actionbatch = new ArrayList<T>();
		}
		return Actionbatch;
	}


	public  void getAbility(LoadApp loadApp, List<keymap> keymap_alls) {
		// TODO Auto-generated method stub
		
	}


	
	
	/**
	 * ==========================================================
	 * 🛠️ Main Save Entry Point
	 * ==========================================================
	 * This method coordinates all steps of the data-saving process
	 * in the following order:
	 *
	 *  1. Registration Request
	 *  2. Registration Processing
	 *  3. Action Logging
	 *  4. Batch Action Persistence
	 *
	 *  @param LoadApp      The main application context
	 *  @return State       The final state after saving process
	 * ----------------------------------------------------------
	 *  🚨 NOTE: This is the central coordinator for saving logic.
	 *          Do NOT perform data logic here directly.
	 * ==========================================================
	 */
	public State Save(LoadApp LoadApp) {
		
    	System.out.println("Production--------> ");
    	State Save_State = State.NONE;
    	Save_Registration_Request(LoadApp,Save_State);
    	Save_Register_Processing(LoadApp,Save_State);
    	Save_Action(LoadApp,Save_State);
        return Save_State;
	}


		private void SaveActionbatchdata(LoadApp LoadApp,String type,Map<String, Object> modelData){
			
			
			  if (LoadApp.getActionbatch() != null) {		
					
					List<?>  SubDataDTO = (List<?>) LoadApp.getActionbatch(); 
				
					 switch(type) {
					   case "machine_activity"-> Machine_activity.saveAll((List<machine_activity>) SubDataDTO);
					   case "maintenance_request"->  Maintenance_requests.saveAll((List<maintenance_requests>) SubDataDTO);
					   case "other"-> EdataDAO.SaveOther((List<other>) SubDataDTO,LoadApp.getEdata().getData_id());
				   }  
					 
					 System.out.println(" setSubTabledetails-----> ");
					 
					 BuilderSubTabledetails(LoadApp,SubDataDTO);
							 
			  }else {
					 switch(type) {
					   case "maintenance_requests"->  Maintenance_requests.deleteAllByTempId(String.valueOf( LoadApp.getEdata().getData_id()));
					   case "machine_activity"->  Machine_activity.deleteAllByTempId(String.valueOf( LoadApp.getEdata().getData_id()));
					   case "other"-> EdataDAO.DelOther(LoadApp.getEdata().getData_id());
				   }  	
	
					 BuilderSubTabledetails(LoadApp,null);
			   }

		}
		

	private void Save_Action(LoadApp LoadApp, State Save_State) {
		Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
		
		
		 if(LoadApp.getMachine_activity() != null) {		 
			  LoadApp.getMachine_activity().setEdataId(LoadApp.getEdata().getData_id());
			  Machine_activity.save(LoadApp.getMachine_activity());
	    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 	

	    	  return;
		 }	
		 if(LoadApp.getMaintenance_requests() != null) {		 
			  LoadApp.getMaintenance_requests().setEdataId(LoadApp.getEdata().getData_id());
			  Maintenance_requests.save(LoadApp.getMaintenance_requests());
	    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 	
	    	  
	    	  String title = messageSource.getMessage("title.maintenance_requests", null, locale);
	    	  return;
		 }	
		 
	      if(LoadApp.getOther() != null) {
	    	   System.err.println("->Other ");
	    	    LoadApp.getOther().setEdata_id(LoadApp.getEdata().getData_id());
	    	    EdataDAO.SaveOther( LoadApp.getOther());
	    	    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
	    	    
	    	    
	    	    StringBuilder  bulider = new StringBuilder();

	    	    String  department =  LoadApp.getOther().getDepartment() == null ?  "": LoadApp.getOther().getDepartment()+" ";
	    	    String  list =  LoadApp.getOther().getList() == null ?  "": LoadApp.getOther().getList()+" ";
	    	    String  equipment =  LoadApp.getOther().getEquipment() == null ?  "": LoadApp.getOther().getEquipment()+" ";
	    	    String  product_name =  LoadApp.getOther().getProduct_name() == null ?  "": LoadApp.getOther().getProduct_name()+" ";
	    	    
	    	    bulider.append(product_name).append(list).append(equipment).append(department);
	    	   
	    	    return;
	    	    
	       }	
		
	}
	
	
	private void Save_Register_Processing(LoadApp LoadApp, State Save_State) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void Save_Registration_Request(LoadApp LoadApp, State Save_State) {
		// TODO Auto-generated method stub
		
	}


	public void ApproveData(LoadApp LoadApp) {
		ApproveApp_Components.runMethod(LoadApp.getMachine_activity(),()-> Machine_activity.updateDocByEdataId(LoadApp.getEdata()) );
		ApproveApp_Components.runMethod(LoadApp.getMaintenance_requests(),()-> Maintenance_requests.updateDocByEdataId(LoadApp.getEdata()) );
	}


	
	
	
}
