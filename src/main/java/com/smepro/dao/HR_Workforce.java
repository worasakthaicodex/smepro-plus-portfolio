package com.smepro.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


import com.smepro.entity.edata_workrule;
import com.smepro.entity.hr_action;
import com.smepro.entity.machine_activity;
import com.smepro.entity.maintenance_requests;
import com.smepro.entity.sales_followup;
import com.smepro.enums.AppAbility;
import com.smepro.models.LoadApp;
import com.smepro.models.bom;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.repository.Edata_workruleRepository;
import com.smepro.repository.Hr_actionRepository;
import com.smepro.repository.Machine_activityRepository;
import com.smepro.repository.Maintenance_requestsRepository;
import com.smepro.service.PlatformComponent;


@Component
public class HR_Workforce extends PlatformComponent  {

	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	
	private Edata_workruleRepository Edata_workrule;
	private Hr_actionRepository Hr_action;
	private edataDAO EdataDAO;
	private  SaveAppUnity_Component SaveAppUnity_Components;
	private  MessageSource messageSource;
	private  ApproveApp_Component ApproveApp_Components;
	
	
	  @Autowired
		public void setMyRepository(
				Edata_workruleRepository edata_workrule,
				Hr_actionRepository hr_action,
				SaveAppUnity_Component SaveAppUnity_Components,
				MessageSource messageSource,
				edataDAO EdataDAO,
				ApproveApp_Component ApproveApp_Components
				) {
		  
		  this.Edata_workrule = Edata_workrule;
		  this.Hr_action = Hr_action;
		  this.SaveAppUnity_Components = SaveAppUnity_Components;
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
		case "hr_actions." -> transactionexposeAs(Hr_action.findByEdataId(eformId),"hr_action",modelData);
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
		edata_workrule transaction  = Edata_workrule.findByEdataId(LoadApp.getEdata().getData_id());
		transactionexposeAs(transaction,"edata_workrule",modelData);
		return;
		
	}


	private void Register(LoadApp LoadApp, Map<String, Object> modelData) {
		edata_workrule transaction  = Edata_workrule.findByEdataId(LoadApp.getEdata().getData_id());
		transactionexposeAs(transaction,"edata_workrule",modelData);
		return;
		
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
	 */

	public void LoadBatch_insert(LoadApp LoadApp,String type, Map<String, Object> modelData) {
			 DisplayActionbatch(LoadApp,type,modelData);
	}
	  
	  
	private <T> void DisplayActionbatch(LoadApp LoadApp, String string, Map<String, Object> modelData) {

		  if (string.equals("Hr_action")) {
			  Hr_action(LoadApp,modelData);
		  }
		  else  if (string.equals("other")) {
			  Other(LoadApp,modelData);
		  }
	
	}
	  
	
	private<T> void Hr_action(LoadApp LoadApp, Map<String, Object> modelData) {
		  List<T> Actionbatch  = new ArrayList<T>();
		  List<hr_action> SubDataDTOS = Hr_action.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 

		 modelData.put("item",Actionbatch);
		 LoadApp.setActionbatch(Actionbatch);	
			    
		    
	}
	
	
	
	private<T> void Other(LoadApp LoadApp, Map<String, Object> modelData) {
		  List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<other> SubDataDTOS = EdataDAO.getAllEdata_other_item(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 modelData.put("item",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);
		    
	}


	  //this
		public void LoadhasChanges(LoadApp loadApp, String tag,Map<String, Object> modelData) {
			if (tag.equals("hr_action")) {
				 List<hr_action> list =  (List<hr_action>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("other")) {
				 List<other> list =  (List<other>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
		}
		
		
		  //this
		
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
		 
		 
		  if (tag.equals("hr_action")) {
			  
			  if(LoadApp.getHr_action().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getHr_action().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getHr_action();
				tempId = LoadApp.getTempId();
				hr_action  item = (hr_action) objects;
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


	public  void getAbility(LoadApp LoadApp, List<keymap> Keymap_alls) {
		// TODO Auto-generated method stub
		
		
		 List<String>	mainkeys;
		boolean exists;

		mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("workrule_proceedings", "rule_name"));
		
		if(exists) {
			LoadApp.getAbility().add(AppAbility.update_register_workrule.toString());
			 return;
		}
		
		mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("rule_name", "rule_type"));	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.register_workrule.toString());
			 return;
		}
		
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
		
		
		public void SaveActionbatchdata(LoadApp LoadApp,String type,Map<String, Object> modelData){
			
			  if (LoadApp.getActionbatch() != null) {		
					
					List<?>  SubDataDTO = (List<?>) LoadApp.getActionbatch(); 
				
					 switch(type) {
					   case "hr_action"-> Hr_action.saveAll((List<hr_action>) SubDataDTO);
					   case "other"-> EdataDAO.SaveOther((List<other>) SubDataDTO,LoadApp.getEdata().getData_id());

				   }  
					 System.out.println(" setSubTabledetails-----> ");
					 BuilderSubTabledetails(LoadApp,SubDataDTO);
			  }else {
					 switch(type) {
					   case "hr_action"-> Hr_action.deleteAllByTempId( String.valueOf(LoadApp.getEdata().getData_id()));
					   case "other"-> EdataDAO.DelOther(LoadApp.getEdata().getData_id());

				   }  	
	
					 BuilderSubTabledetails(LoadApp,null);
			   }
		}
		
		
		private void Save_Action(LoadApp LoadApp, State Save_State) {
			// TODO Auto-generated method stub
			
			  if(LoadApp.getHr_action() != null) {		 
				  LoadApp.getHr_action().setEdataId(LoadApp.getEdata().getData_id());
				  Hr_action.save(LoadApp.getHr_action());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	  return;
			 }	
			
		}
		
		
		private void Save_Register_Processing(LoadApp LoadApp, State Save_State) {
			
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			
			if(LoadApp.getEdata_workrule() != null) {
				  if(LoadApp.getEdata_stock().getStock_proceedings() == null) {return; }	  
					
					
				  LoadApp.getEdata_workrule().setEdataId(LoadApp.getEdata().getData_id());
				  
					 if(SaveAppUnity_Components.HaveWorkrule(LoadApp)) {
						 Edata_workrule.save(LoadApp.getEdata_workrule());
						 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						 Edata_workrule.save(LoadApp.getEdata_workrule());
						Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					 }
				 	 String title = messageSource.getMessage("title.registration_request", null, locale);
				      Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
			}
			
		}
		
		
		private void Save_Registration_Request(LoadApp LoadApp, State Save_State) {
			
			
			// TODO Auto-generated method stub
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			String title = messageSource.getMessage("title.registration_request", null, locale);
			
			if(LoadApp.getEdata_workrule() != null) {
				  LoadApp.getEdata_workrule().setEdataId(LoadApp.getEdata().getData_id());
				  
					 if(SaveAppUnity_Components.VerifyWorkrule(LoadApp)) {
						 Edata_workrule.save(LoadApp.getEdata_workrule());
						 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						LoadApp.getEdata_workrule().setRule_name("Rename ->"+LoadApp.getEdata_workrule().getRule_name());
						 Edata_workrule.save(LoadApp.getEdata_workrule());
						Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					 }
				 	
				      Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
				}
			
			
		}


		public void ApproveData(LoadApp LoadApp) {
			ApproveApp_Components.runMethod(LoadApp.getEdata_workrule(),()-> Edata_workrule.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getHr_action(),()-> Hr_action.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getOther(),()-> EdataDAO.APPOther(LoadApp.getEdata()) );
		}

	
	


	  
}
