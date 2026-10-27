package com.smepro.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.smepro.dao.Sales_Customer.State;
import com.smepro.entity.edata_workrule;
import com.smepro.entity.hr_action;
import com.smepro.entity.machine_activity;
import com.smepro.entity.maintenance_requests;
import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.request_for_quotation;
import com.smepro.entity.sales_followup;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Edata_maindata;
import com.smepro.models.FieldChange;
import com.smepro.models.FullResult;
import com.smepro.models.HasId;
import com.smepro.models.LoadApp;
import com.smepro.models.Segmentation;
import com.smepro.models.UseExtension;
import com.smepro.models.bom;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.product_master;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.registers;
import com.smepro.models.sub_processes;
import com.smepro.repository.Edata_workruleRepository;
import com.smepro.repository.Hr_actionRepository;
import com.smepro.repository.Machine_activityRepository;
import com.smepro.repository.Maintenance_requestsRepository;
import com.smepro.repository.Ps_contractRepository;
import com.smepro.repository.Purchase_order_trackingRepository;
import com.smepro.repository.Request_for_quotationRepository;
import com.smepro.repository.Sales_followupRepository;
import com.smepro.service.LogService;
import com.smepro.service.PlatformComponent;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Production extends PlatformComponent  {

	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	
	private edataDAO EdataDAO;
	private  SaveAppUnity_Component SaveAppUnity_Components;
	private  ApproveApp_Component ApproveApp_Components;
	private LogService logService;
	private  IdocDAO idocDAO;
	private  Sales_followupRepository Sales_followup;
	private  reportDAO ReportDAO;
	
	
	
	
	  @Autowired
		public void setMyRepository(
				SaveAppUnity_Component SaveAppUnity_Components,
				edataDAO EdataDAO,
				ApproveApp_Component ApproveApp_Components,
				LogService logService,
				IdocDAO idocDAO,
				Sales_followupRepository Sales_followup,
				reportDAO ReportDAO
				) {
		  
		  this.SaveAppUnity_Components = SaveAppUnity_Components;
		  this.EdataDAO = EdataDAO;
		  this.ApproveApp_Components =ApproveApp_Components;
		  this.logService =logService;
		  this.idocDAO =idocDAO;
		  this.Sales_followup =Sales_followup;
		  this.ReportDAO =ReportDAO;
	  }
	  
	  
	  public void LoadTransactions(LoadApp LoadApp) {
			  
			  if(LoadApp.getRegister_count() > 0) {
				//  Register(LoadApp);
			  }
			  else if(LoadApp.getProcessing_count()  > 0) {
				  Processing(LoadApp);
			  }
			  else if(LoadApp.getModify_count() > 0) {
				  Modify(LoadApp);
			  }
			  else if(LoadApp.getContinue_count() > 0) {
				  Continue(LoadApp);
			  }
			  else  {
				  Action(LoadApp);
			  }

		  
	  }

	  public void UseSegmentation(edata edata,String type) {
			Edata_maindata  maindata  = EdataDAO.getEdatamainData(edata.getData_id());
			if(maindata == null) {return;}
			
			switch(maindata.getEntity()) {
				case "sales_followup" ->{
					sales_followup 	sales_followup = Sales_followup.findByEdataId(edata.getData_id());
					if(sales_followup == null) {return;}
							if(sales_followup.getSegmentation_profile_static_snapshot() != null) {
							ReportDAO.UseSegmentation(sales_followup.getSegmentation_profile_static_snapshot(),edata.getData_id(),type);
							}
					
				} 
			
			}	
		}
	  public sales_followup getSales_followupByEdata(int edataId) {
		sales_followup 	sales_followup = Sales_followup.findByEdataId(edataId);
		return sales_followup;
	  }
	  public sales_followup getSales_followupByid(int id) {
		sales_followup 	sales_followup = Sales_followup.findById(id);
		return sales_followup;
	  }
	  
	  
	  
	private void Action(LoadApp LoadApp) {


		
		other	other = EdataDAO.getOther(LoadApp.getEdata().getData_id());
		if(other != null) {
			 LoadApp.setOther(other);
	    	 LoadApp.getModel().addAttribute("transaction",other); 
	    	 LoadApp.getModel().addAttribute("edata_type","other"); 		
	    	 return;
		}
	}
	

	
	private void Continue(LoadApp LoadApp) {
	
		
	}


	private void Modify(LoadApp LoadApp) {


		
	}


	private void Processing(LoadApp LoadApp) {

	}


	public List<FieldChange> LoadCompare(LoadApp app) {
		
		
		System.out.println(" LoadCompare .............");

		Edata_maindata	edataMaindata= EdataDAO.getEdatamainData(app.getEdata().getData_id());		
		if(edataMaindata == null) {return null;}		
		
		if(edataMaindata.getEntity().equals("edata_register")) {
			
			System.out.println(" app.getEdata_register() .............");
			edata_register edata_register = EdataDAO.geteData_register(app.getEdata().getData_id());
			
			
			
			
			List<String> IGNORE_FIELDS = Arrays.asList("register_id", "edata_id","customerCode","id","processId","register_status","register_daterun");
			registers Profile =  getProfileByCode(edata_register.getCustomerCode());
			
			System.out.println(" Profile ............."+Profile);
			System.out.println(" edata_register ............."+edata_register);
			
			
			
			return compareChanges(Profile,edata_register,IGNORE_FIELDS);
			
			
			
		}
		else if(edataMaindata.getEntity().equals("edata_product")) {
			System.out.println(" app.getEdata_product() .............");
			edata_product edata_product = EdataDAO.geteData_product(app.getEdata().getData_id());
			
			
			
			
			List<String> IGNORE_FIELDS = Arrays.asList("product_id", "edata_id","productCode","id","processId","product_status","product_daterun");
			product_master product =  getProduct_masterCode(edata_product.getProductCode());
			
			System.out.println(" Product ............."+product);
			System.out.println(" edata_product ............."+edata_product.getProductCode());
			
			
			
			return compareChanges(product,edata_product,IGNORE_FIELDS);
		}
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
	
public List<FieldChange> LoadCompareVersioning(LoadApp app) {
		
		if(app.getPlatform() == null) {return null;}		
		if(app.getPlatform().getApp_item_id() == 0) {return null;}		
		if(app.getPlatform().getApp_item_id2()  == 0) {return null;}		
		
		
		
		if(app.getPlatform().getApp_entity_type().equals("register")) {
			
			System.out.println(" app.getEdata_register() .............");
			edata_register edata_register1 = EdataDAO.geteData_registerById(app.getPlatform().getApp_item_id());
			edata_register edata_register2 = EdataDAO.geteData_registerById(app.getPlatform().getApp_item_id2());
			
			
			
			List<String> IGNORE_FIELDS = Arrays.asList("register_id", "edata_id","customerCode","id","processId","register_daterun");
			return compareChanges(edata_register2,edata_register1,IGNORE_FIELDS);
			
			
			
		}
		else if(app.getPlatform().getApp_entity_type().equals("product")) {
			System.out.println(" app.getEdata_product() .............");
			edata_product edata_product1 = EdataDAO.geteData_productById(app.getPlatform().getApp_item_id());
			edata_product edata_product2 = EdataDAO.geteData_productById(app.getPlatform().getApp_item_id2());
			
			
			
			List<String> IGNORE_FIELDS = Arrays.asList("product_id", "edata_id","productCode","id","processId","product_daterun");

			
			return compareChanges(edata_product2,edata_product1,IGNORE_FIELDS);
		}
		
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	  //this
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
	  
	  
	  //this  
	private <T> void DisplayActionbatch(LoadApp LoadApp, String string,Map<String, Object> modelData) {

		  if (string.equals("production_list")) {
			  Production_list(LoadApp,modelData);
		  }
		  else  if (string.equals("production_directive")) {
			  Production_directive(LoadApp,modelData);
		  }
		  else  if (string.equals("other")) {
			  Other(LoadApp,modelData);
		  }
		  else  if (string.equals("bom")) {
			  Bom(LoadApp,modelData);
		  }
		  else  if (string.equals("production_standard")) {
			  Production_standard(LoadApp,modelData);
		  }
		  
	}
	
	
	

	


		private<T> void Bom(LoadApp LoadApp,Map<String, Object> modelData) {
			  
			

			  List<T> Actionbatch  = new ArrayList<T>();
			  
			  List<bom> SubDataDTOS = EdataDAO.getAllEdata_bom_item(LoadApp.getTempId());
			  Actionbatch.addAll((List<T>) SubDataDTOS); 
			  
			  System.out.println(" item  Bom--->"+Actionbatch.size());
				 
				 
				 
				 double grandtotal = SubDataDTOS.stream()
						    .mapToDouble(e -> ( e.getBom_base_quantity() + e.getBom_wastage()) *   e.getBom_price()) // คำนวณ unit * price
						    .sum(); // รวมค่าทั้งหมด
			  
				 BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
						    .setScale(2, RoundingMode.HALF_UP); 

				 modelData.put("grandtotal",Grandtotal);
				 modelData.put("item",Actionbatch);
				 

				 
				 LoadApp.setActionbatch(Actionbatch);
				  System.out.println(" item  Bom grandtotal--->"+Grandtotal);
				 System.out.println("app "+LoadApp.toString());
		}
		
		
		
		private<T> void Production_standard(LoadApp LoadApp,Map<String, Object> modelData) {
			  List<T> Actionbatch  = new ArrayList<T>();
			  List<production_standard> SubDataDTOS = EdataDAO.getAllEdata_production_standard_item(LoadApp.getTempId());
			  Actionbatch.addAll((List<T>) SubDataDTOS); 
			  
				 System.out.println(" item --->"+Actionbatch.size());
				 
				 modelData.put("item",Actionbatch);
				 LoadApp.setActionbatch(Actionbatch);
			
		}
	
		private<T> void Production_list(LoadApp LoadApp,Map<String, Object> modelData) {
			  List<T> Actionbatch  = new ArrayList<T>();
			  List<production_list> SubDataDTOS = EdataDAO.getAllEdata_pl_item(LoadApp.getTempId());
			  Actionbatch.addAll((List<T>) SubDataDTOS); 
			  
		
			  modelData.put("item",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);	
			    
		}
		private<T> void Production_directive(LoadApp LoadApp,Map<String, Object> modelData) {
			  List<T> Actionbatch  = new ArrayList<T>();
			  List<production_directive> SubDataDTOS = EdataDAO.getAllEdata_pd_item(LoadApp.getTempId());
			  Actionbatch.addAll((List<T>) SubDataDTOS); 
			  
			  modelData.put("item",Actionbatch);
			  LoadApp.setActionbatch(Actionbatch);	
			    
		}
		
	private<T> void Other(LoadApp LoadApp,Map<String, Object> modelData) {
			  List<T> Actionbatch  = new ArrayList<T>();
			  List<other> SubDataDTOS = EdataDAO.getAllEdata_other_item(LoadApp.getTempId());
			  Actionbatch.addAll((List<T>) SubDataDTOS); 
			  
			  modelData.put("item",Actionbatch);
			  LoadApp.setActionbatch(Actionbatch);	
			    
		}


	
	  //this
	public void LoadhasChanges(LoadApp loadApp, String tag,Map<String, Object> modelData) {
		if (tag.equals("bom")) {
			 List<bom> list =  (List<bom>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
		else if (tag.equals("production_standard")) {
			 List<production_standard> list =  (List<production_standard>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
		else if (tag.equals("other")) {
			 List<other> list =  (List<other>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
		else if (tag.equals("production_list")) {
			 List<production_list> list =  (List<production_list>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
		else if (tag.equals("production_directive")) {
			 List<production_directive> list =  (List<production_directive>)loadApp.getActionbatch();
			 modelData.put("item",list);
		  }
	}
	
	
	  //this
	
	public void Batch_inserthasChanges(LoadApp LoadApp, String tag,Map<String, Object> modelData) {
		Batch_process(LoadApp,tag,modelData);
	}
	
	  //this
	private <T extends Identifiable> void Batch_process(LoadApp LoadApp, String tag,Map<String, Object> modelData) {
		
		

		 T objects = null;
		 String object_type = "";
		 String tempId = "";
		 double total =0.0;
		 Double Doubletotal =0.0;
		 String formattedTotal ="0.0";	
		 int  item_ids = 0;
		 Object Actionbatch;	
		 
		 
		  if (tag.equals("production_list")) {
			  
				if(LoadApp.getProduction_lists().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getProduction_lists().setId(randomNegative);
				}  
			  
			  
			objects =  (T) LoadApp.getProduction_lists();
			tempId = LoadApp.getTempId();
			production_list  item = (production_list) objects;
			 item.setTempId(tempId);

		  }
		  else  if (tag.equals("production_directive")) {
			  
				if(LoadApp.getProduction_directives().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getProduction_directives().setId(randomNegative);
				}  
			  
			objects =  (T) LoadApp.getProduction_directives();
			tempId = LoadApp.getTempId();
            production_directive  item = (production_directive) objects;
            item.setTempId(tempId);

		  }
		  
		  
		  
		  else  if (tag.equals("other")) {
			  
			  
				if(LoadApp.getOther().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getOther().setId(randomNegative);
				}  
			  
			objects =  (T) LoadApp.getOther();
			tempId = LoadApp.getTempId();
			other  item = (other) objects;
	        item_ids = item.getId();
	        item.setTempId(tempId);
			 
		  }
		  else  if (tag.equals("bom")) {
				
			Doubletotal =  ( LoadApp.getBom().getBom_base_quantity() + LoadApp.getBom().getBom_wastage()) * LoadApp.getBom().getBom_price();
			Double formattedTotals = Math.round(Doubletotal * 100.0) / 100.0;
			LoadApp.getBom().setBom_total_cost(formattedTotals);
				
			objects =  (T) LoadApp.getBom();

			
			tempId = LoadApp.getTempId();
			bom  item = (bom) objects;
	        item_ids = item.getId();
	        item.setTempId(tempId);
		  }
		  else  if (tag.equals("production_standard")) {
			  
				if(LoadApp.getProduction_standard().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getProduction_standard().setId(randomNegative);
				}    
			  
			  
			objects =  (T) LoadApp.getProduction_standard();
			
			tempId = LoadApp.getTempId();
			production_standard  item = (production_standard) objects;
			item_ids = item.getId();
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
	
	
	
	
	


	public  void getAbility(LoadApp LoadApp, List<keymap> Keymap_alls) {
		
		 List<String>	mainkeys;
		boolean exists;
		
		mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		 exists = mainkeys.containsAll(Arrays.asList("register_proceedings", "register_name"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.update_register_list.toString());
			 return;
		}
		
		
		 mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("register_name", "register_type"));	
		
		if(exists) {
			LoadApp.getAbility().add(AppAbility.register_list.toString());	
			 return;
		}
		
		mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("product_proceedings", "product_names"));	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.update_register_product.toString());
			 return;
		}
		
		
		mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("product_names", "product_category"));	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.register_product.toString());
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
			
        	System.out.println("<----------Production--------> ");
        	State Save_State = State.NONE;
        	Save_Action(LoadApp,Save_State);
        	//Save_Registration_Request(LoadApp,Save_State);
        	Save_Register_Processing(LoadApp,Save_State);

            return Save_State;
		}


		public void SaveActionbatchdata(LoadApp LoadApp,String type,Map<String, Object> modelData){
			
				  if (LoadApp.getActionbatch() != null) {		
					
						List<?>  SubDataDTO = (List<?>) LoadApp.getActionbatch(); 
					
						 switch(type) {
						   case "bom"-> EdataDAO.SaveBom((List<bom>) SubDataDTO,LoadApp.getEdata().getData_id());
						   case "production_standard"-> EdataDAO.SaveProduction_standard((List<production_standard>) SubDataDTO,LoadApp.getEdata().getData_id());
						   case "other"-> EdataDAO.SaveOther((List<other>) SubDataDTO,LoadApp.getEdata().getData_id());
						   case "production_list"-> EdataDAO.SavePL((List<production_list>) SubDataDTO,LoadApp.getEdata().getData_id());
						   case "production_directive"-> EdataDAO.SavePD((List<production_directive>) SubDataDTO,LoadApp.getEdata().getData_id());
					   }  
						 System.out.println(" setSubTabledetails-----> ");
						 BuilderSubTabledetails(LoadApp,SubDataDTO);
				  }else {
						 switch(type) {
						   case "bom"-> EdataDAO.DelBoms(LoadApp.getEdata().getData_id());
						   case "production_standard"-> EdataDAO.DelPS(LoadApp.getEdata().getData_id());
						   case "other"-> EdataDAO.DelOther(LoadApp.getEdata().getData_id());
						   case "production_list"-> EdataDAO.DelPL(LoadApp.getEdata().getData_id());
						   case "production_directive"-> EdataDAO.DelPD(LoadApp.getEdata().getData_id());
					   }  	
		
						 BuilderSubTabledetails(LoadApp,null);
				   }
		}



		

		private void Save_Action(LoadApp LoadApp,State Save_State) {

		     
			if(LoadApp.getProduction_lists() != null) {
		   		
		    	   System.err.println("-> Production_lists ");
		    	    LoadApp.getProduction_lists().setEdata_id(LoadApp.getEdata().getData_id());
		    	   
		    	    
		    	    if(LoadApp.getProduction_lists().getLot_batch().equals("Auto-generated")  || LoadApp.getProduction_lists().getLot_batch().equals("Error : Auto-generated")) {
		    	    	
		    	    	Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	    }else if(SaveAppUnity_Components.VerifyImportantData(LoadApp)  ) {
		    	    	Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						 LoadApp.getProduction_lists().setLot_batch("Rename ->"+LoadApp.getProduction_lists().getLot_batch());
						 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					
					 }
		    	    
		    	    EdataDAO.SaveProduction_lists(LoadApp.getProduction_lists());
		    	   Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	    
		    	   
		       }	
		       else if(LoadApp.getProduction_directives() != null) {
			   		
		    	   System.err.println("->Production_directives ");
		    	   
		    	    LoadApp.getProduction_directives().setEdata_id(LoadApp.getEdata().getData_id());
		    	    EdataDAO.SaveProduction_directives(LoadApp.getProduction_directives());
		    	    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		 
		       }	
		       else if(LoadApp.getEdata_production_records() != null) {
			   		
		    	   System.err.println("->Edata_production_records ");
		    	   
		    	    LoadApp.getEdata_production_records().setEdata_id(LoadApp.getEdata().getData_id());
		    	    EdataDAO.SaveEdata_production_records(LoadApp.getEdata_production_records());
		    	   
		    	    
		    	    
		    	    
		    	    StringBuilder titleBuilder = new StringBuilder();

		    	    String step = LoadApp.getEdata_production_records().getProcess_step();
		    	    String department = LoadApp.getEdata_production_records().getDepartment();
		    	    String productName = LoadApp.getEdata_production_records().getProduct_name();
		    	    String lotBatch = LoadApp.getEdata_production_records().getLot_batch();

		    	    if (step != null && !step.equalsIgnoreCase("null")) {
		    	        titleBuilder.append(step).append(" ");
		    	    }
		    	    if (department != null && !department.equalsIgnoreCase("null")) {
		    	        titleBuilder.append(department).append(" ");
		    	    }
		    	    if (productName != null && !productName.equalsIgnoreCase("null")) {
		    	        titleBuilder.append(productName).append(" ");
		    	    }
		    	    if (lotBatch != null && !lotBatch.equalsIgnoreCase("null")) {
		    	        titleBuilder.append("[").append(lotBatch).append("]");
		    	    }

		    	   
		    	    
		    	    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    
		       }	
		       else if(LoadApp.getBom() != null) {
			   		
		    	   System.err.println("->bom ");
		    	   
		    	    LoadApp.getBom().setEdata_id(LoadApp.getEdata().getData_id());
		    	    EdataDAO.SaveBom(LoadApp.getBom());
		    	    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	    
		    	    StringBuilder  bulider = new StringBuilder();
		    	    String  Bom_name =  LoadApp.getBom().getBom_name() == null ?  "": LoadApp.getBom().getBom_name()+" ";
		    	    String  product_name =  LoadApp.getBom().getBom_product_name() == null ?  "": LoadApp.getBom().getBom_product_name()+" ";
		    	    
		    	    bulider.append(Bom_name).append(product_name);	    
		    	
		       }	
		       else if(LoadApp.getProduction_standard() != null) {
			   		
		    	   System.err.println("->production_standard ");
		    	   
		    	    LoadApp.getProduction_standard().setEdata_id(LoadApp.getEdata().getData_id());
		    	    EdataDAO.SaveProduction_standard(LoadApp.getProduction_standard());
		    	    
		    	   Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	
		       }	
		       else if(LoadApp.getQci() != null) {
			   		
		    	   System.err.println("->Qci ");
		    	   
		    	    LoadApp.getQci().setEdata_id(LoadApp.getEdata().getData_id());
		    	    EdataDAO.SaveQci( LoadApp.getQci());
		    	    
		    	    StringBuilder  bulider = new StringBuilder();
		    	    String  Product_name =  LoadApp.getQci().getProduct_name()== null ?  "": LoadApp.getQci().getProduct_name()+" ";
		    	    String  Inspection =  LoadApp.getQci().getInspection()== null ?  "": "["+LoadApp.getQci().getInspection()+"] ";
		    	    
		    	    bulider.append(Product_name).append(Inspection);
		    	    
		    	    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	
		       }	
		       
		       else if(LoadApp.getOther() != null) {
		    	   System.err.println("->Other ");
		    	    LoadApp.getOther().setEdata_id(LoadApp.getEdata().getData_id());
		    	    EdataDAO.SaveOther( LoadApp.getOther());
		    	    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	    
		    	    
		    	
		    	    
		       }	
		        
			
		}


		
		
		
		private void Save_Register_Processing(LoadApp LoadApp,State Save_State) {
			
		
			 if(LoadApp.getEdata_register() == null) { return;} 
			 if(LoadApp.getEdata_register().getRegister_proceedings() == null) { return;} 
						  
			LoadApp.getEdata_register().setEdata_id(LoadApp.getEdata().getData_id());
						 
			System.err.println("- Save_Register_Processing ....");
						 
			if(!SaveAppUnity_Components.HaveRegister(LoadApp)) {
					System.err.println("❌ ชนิดข้อมูลไม่ถูกต้อง");
					LoadApp.getEdata_register().setRegister_name("Rename ->"+LoadApp.getEdata_register().getRegister_name());
					EdataDAO.SaveRegister(LoadApp.getEdata_register());
					Save_State =  State.INVALID_TYPE;  // 
			}else {
				    EdataDAO.SaveRegister(LoadApp.getEdata_register());
				    Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
			}
						
			
				     	
		}


		public void Save_Registration_Request(LoadApp LoadApp,String type,Map<String, Object> modelData) {

				 switch(type) {
					 case "register"->RequestProfile(LoadApp,modelData);
					 case "product"-> RequestProduct(LoadApp,modelData);
				 }
		
		}

		
		
		
		
		private void RequestProfile(LoadApp LoadApp, Map<String, Object> modelData) {
			
				if(LoadApp.getEdata_register() == null) { return;} 
				LoadApp.getEdata_register().setEdata_id(LoadApp.getEdata().getData_id());		 
				
				
				if(LoadApp.getEdata().getEdata_index_action_type() == 0) {
					if( LoadApp.getEdata_register().getRegister_type() ==  null) {
						logService.notice(401,"Error (Validation / Missing field) : Register Type cannot be null", "Production/RequestProfile",LoadApp.getEdata().getData_id());
						EdataDAO.addProblem("Register Type cannot be null",LoadApp.getEdata().getData_id());
					}
				}

				switch(SaveAppUnity_Components.VerifyRegister(LoadApp)) {
					case 2 -> {
						logService.notice(403,
							    "Error  (Validation): Required Register not found in system",
							    "Production/RequestProfile",
							    LoadApp.getEdata().getData_id()
							);
						EdataDAO.addProblem("Required Register not found",LoadApp.getEdata().getData_id());
					}
					case 1 -> {
						
						logService.notice(403,
							    "Error  (Validation): Register already exists in system",
							    "Production/RequestProfile",
							    LoadApp.getEdata().getData_id()
							);
						EdataDAO.addProblem("Register already exist",LoadApp.getEdata().getData_id());
						
					}
					case 0 -> EdataDAO.removeProblem(LoadApp.getEdata().getData_id());
				}
				
				 EdataDAO.SaveRegister(LoadApp.getEdata_register());
				 edata_register edata_register = EdataDAO.geteData_register(LoadApp.getEdata().getData_id());
				 EdataDAO.saveEdatamainData(LoadApp.getEdata().getData_id(),edata_register.getId(),"edata_register");
				 
				 
				  // ---------------------------------------------------------
				  // ส่วน Logic หลัก (เอาไปแปะแทนของเดิม)
				  // ---------------------------------------------------------

				  if (LoadApp.getProcess().getRenamefile() == 1) {
				      
				      // แบบที่ 1: ProcessName + Customer + Service
				      String newname = joinNames(
				          LoadApp.getProcess().getName(),
				          edata_register.getRegister_name(),
				         null
				      );
				      
				      EdataDAO.setTitle(LoadApp.getEdata().getData_id(), newname);

				  } else if (LoadApp.getProcess().getRenamefile() == 2) {
				      
				      // แบบที่ 2: หัวชื่อเก่า + Customer
				      
				      // 1. แกะหัวชื่อเก่า (เช่น "ขอเพิ่ม_dfdddfd" -> เอาแค่ "ขอเพิ่ม")
				      String oldname = LoadApp.getEdata().getData_title();
				      String headerPrefix = "";
				      
				      if (oldname != null && oldname.contains("_")) {
				          headerPrefix = oldname.split("_")[0]; // เอาคำหน้าสุด
				      } else {
				          headerPrefix = oldname; // ถ้าไม่มี _ เลย ก็เอามาทั้งดุ้น
				      }
				      
				      // 2. เอาหัวมาต่อกับชื่อลูกค้า (ProcessName ไม่ใช้แล้ว ตามโจทย์)
				      String newname = joinNames(
				          headerPrefix,
				          edata_register.getRegister_name()
				      );
				      
				      EdataDAO.setTitle(LoadApp.getEdata().getData_id(), newname);
				  }
				     
				 
				 
		}

		
		
		
		private void RequestProduct(LoadApp LoadApp, Map<String, Object> modelData) {
			
				if(LoadApp.getEdata_product() == null) { return;} 
				LoadApp.getEdata_product().setEdata_id(LoadApp.getEdata().getData_id());
								 
				
				
				if(LoadApp.getEdata().getEdata_index_action_type() == 0) {
					if( LoadApp.getEdata_product().getProduct_type() ==  null) {
						logService.notice(401,"Error  (Validation / Missing field) : Register Type cannot be null", "Production/RequestProduct",LoadApp.getEdata().getData_id());
						EdataDAO.addProblem("Register Type cannot be null",LoadApp.getEdata().getData_id());
					}
				}

				switch(SaveAppUnity_Components.VerifyProduct(LoadApp)) {
					case 2 -> {
						logService.notice(403,
							    "Error (Validation): Required Product not found in system",
							    "Production/RequestProduct",
							    LoadApp.getEdata().getData_id()
							);
						EdataDAO.addProblem("Required Product not found",LoadApp.getEdata().getData_id());
					}
					case 1 -> {
						
						logService.notice(403,
							    "Error (Validation): Product already exists in system",
							    "Production/RequestProduct",
							    LoadApp.getEdata().getData_id()
							);
						EdataDAO.addProblem("Product already exist",LoadApp.getEdata().getData_id());
						
					}
					case 0 -> EdataDAO.removeProblem(LoadApp.getEdata().getData_id());
				}
				
				
				
				 EdataDAO.SaveProduct(LoadApp.getEdata_product());
				 edata_product edata_product = EdataDAO.geteData_product(LoadApp.getEdata().getData_id());
				 EdataDAO.saveEdatamainData(LoadApp.getEdata().getData_id(),edata_product.getId(),"edata_product");
				 
				 
				 
				  // ---------------------------------------------------------
				  // ส่วน Logic หลัก (เอาไปแปะแทนของเดิม)
				  // ---------------------------------------------------------

				  if (LoadApp.getProcess().getRenamefile() == 1) {
				      
				      // แบบที่ 1: ProcessName + Customer + Service
				      String newname = joinNames(
				          LoadApp.getProcess().getName(),
				          edata_product.getProduct_names(),
				         null
				      );
				      
				      EdataDAO.setTitle(LoadApp.getEdata().getData_id(), newname);

				  } else if (LoadApp.getProcess().getRenamefile() == 2) {
				      
				      // แบบที่ 2: หัวชื่อเก่า + Customer
				      
				      // 1. แกะหัวชื่อเก่า (เช่น "ขอเพิ่ม_dfdddfd" -> เอาแค่ "ขอเพิ่ม")
				      String oldname = LoadApp.getEdata().getData_title();
				      String headerPrefix = "";
				      
				      if (oldname != null && oldname.contains("_")) {
				          headerPrefix = oldname.split("_")[0]; // เอาคำหน้าสุด
				      } else {
				          headerPrefix = oldname; // ถ้าไม่มี _ เลย ก็เอามาทั้งดุ้น
				      }
				      
				      // 2. เอาหัวมาต่อกับชื่อลูกค้า (ProcessName ไม่ใช้แล้ว ตามโจทย์)
				      String newname = joinNames(
				          headerPrefix,
				          edata_product.getProduct_names()
				      );
				      
				      EdataDAO.setTitle(LoadApp.getEdata().getData_id(), newname);
				  }
				 
		}
		
		

		public void SaveMainRegister(LoadApp LoadApp,String type) {
			
			 switch(type) {
			 case "register"->SaveMainProfile(LoadApp);
			 case "product"-> SaveMainProduct(LoadApp);
		 }
			
		
				
			}
		
		public void SaveAllMainProfile(List<edata_register> Edata_register ,String Code ) {
				EdataDAO.SaveAllMainProfile(Edata_register,Code);
		}
		public void SaveAllMainProduct(List<edata_product> Edata_product ,String Code ) {
			EdataDAO.SaveAllMainProduct(Edata_product,Code);
		}

		private void SaveMainProfile(LoadApp LoadApp) {
			
			 edata_register	 Edata_register = EdataDAO.geteData_register(LoadApp.getEdata().getData_id());
			 LoadApp.setEdata_register(Edata_register);	
			 
			 if(LoadApp.getEdata().getEdata_index_action_type() > 0) {
				 UpdateMainProfile(LoadApp);
				 return;
			 }
			 
			 
			  String Code = null;
			  Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
						.filter(row->row.getStep().equals("Approval"))
						.filter(row->row.getCode_rid() != null)
						.findFirst();
						
				if(rid.isPresent()) {
					    	Code  = rid.get().getCode_rid();
				}    
				 

				int save = EdataDAO.SaveMainRegister(LoadApp.getEdata_register(),Code,LoadApp);
				idocDAO.commit(LoadApp.getEdata().getData_id(),"register","commit",save);

					if(save > 0) {
						LoadApp.setHttp_status_code(200);
						LoadApp.setCallback("Success..");		
					}else {
						LoadApp.setHttp_status_code(422);
						LoadApp.setCallback("Unprocessable Entity");
						logService.notice(422,"Error (Unprocessable Entity) : Business rule validation failed", "Production/SaveMainProfile",LoadApp.getEdata().getData_id());
					}
					
					
		}

		private void UpdateMainProfile(LoadApp LoadApp) {
			
			
				int save =0;
			 if(LoadApp.getEdata().getEdata_index_action_type() == 1) {
					save = EdataDAO.UpdateMainRegister(LoadApp.getEdata_register(),LoadApp,"ACTIVE");
				
			 }
			 else if(LoadApp.getEdata().getEdata_index_action_type() == 2) {
					save = EdataDAO.UpdateMainRegisterStatus(LoadApp.getEdata_register(),LoadApp,LoadApp.getEdata_register().getRegister_status());
				
			 }
			 else  {
					save = EdataDAO.UpdateMainRegisterStatus(LoadApp.getEdata_register(),LoadApp,"PENDING_DELETE");
				
			 }
			
			
			
				idocDAO.commit(LoadApp.getEdata().getData_id(),"register","commit",save);
				if(save > 0) {
					LoadApp.setHttp_status_code(200);
					LoadApp.setCallback("Success..");		
				}else {
					LoadApp.setHttp_status_code(422);
					LoadApp.setCallback("Unprocessable Entity");
					logService.notice(422,"Error(Unprocessable Entity) : Business rule validation failed", "Production/SaveMainProfile",LoadApp.getEdata().getData_id());
				}
			
		}


		private void SaveMainProduct(LoadApp LoadApp) {
			
			 edata_product	 Edata_product = EdataDAO.geteData_product(LoadApp.getEdata().getData_id());
			 LoadApp.setEdata_product(Edata_product);	
			
			
			String Code = null;
			
			 if(LoadApp.getEdata().getEdata_index_action_type() > 0) {
				 UpdateMainProduct(LoadApp);
				 return;
			 }
			
			
			Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
						.filter(row->row.getStep().equals("Approval"))
						.filter(row->row.getCode_rid() != null)
						.findFirst();
						
				if(rid.isPresent()) {
					    	Code  = rid.get().getCode_rid();
				}    
				LoadApp.setEdata_product(EdataDAO.geteData_product(LoadApp.getEdata().getData_id()));	    
				int save = EdataDAO.SaveMainProduct(LoadApp.getEdata_product(),Code,LoadApp);
				idocDAO.commit(LoadApp.getEdata().getData_id(),"product","commit",save);

					if(save > 0) {
						LoadApp.setHttp_status_code(200);
						LoadApp.setCallback("Success..");		
					}else {
						LoadApp.setHttp_status_code(422);
						LoadApp.setCallback("Unprocessable Entity");
						logService.notice(422,"Error (Unprocessable Entity) : Business rule validation failed", "Production/SaveMainProduct",LoadApp.getEdata().getData_id());
					}
		}


		
		
		private void UpdateMainProduct(LoadApp LoadApp) {
			int save =0;
			 if(LoadApp.getEdata().getEdata_index_action_type() == 1) {
					save = EdataDAO.UpdateMainProduct(LoadApp.getEdata_product(),LoadApp,"ACTIVE");
				
			 }
			 else if(LoadApp.getEdata().getEdata_index_action_type() == 2) {
					save = EdataDAO.UpdateMainProductStatus(LoadApp.getEdata_product(),LoadApp,LoadApp.getEdata_product().getProduct_status());
				
			 }
			 else  {
					save = EdataDAO.UpdateMainProductStatus(LoadApp.getEdata_product(),LoadApp,"PENDING_DELETE");
				
			 }
			
				idocDAO.commit(LoadApp.getEdata().getData_id(),"product","commit",save);
				if(save > 0) {
					LoadApp.setHttp_status_code(200);
					LoadApp.setCallback("Success..");		
				}else {
					LoadApp.setHttp_status_code(422);
					LoadApp.setCallback("Unprocessable Entity");
					logService.notice(422,"Error (Unprocessable Entity) : Business rule validation failed", "Production/SaveMainProduct",LoadApp.getEdata().getData_id());
				}
		}


		public void ApproveData(LoadApp LoadApp) {
			
			ApproveApp_Components.runMethod(LoadApp.getBom(),()-> EdataDAO.APPBom(LoadApp.getEdata().getData_id()) );
			ApproveApp_Components.runMethod(LoadApp.getProduction_standard(),()->EdataDAO.APPPS(LoadApp.getEdata().getData_id()) );
			ApproveApp_Components.runMethod(LoadApp.getProduction_directives(),()-> EdataDAO.APPPD(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getProduction_lists(),()->    EdataDAO.APPPL(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getOther(),()-> EdataDAO.APPOther(LoadApp.getEdata()) );
			
		}

		
		
		public void getAllRegisterSegmentation(LoadApp LoadApp, Map<String, Object> modelData,Segmentation segmentation) {
			

			int limit= LoadApp.getProcess().getShow_limit();

			
			if(LoadApp.getPlatform().getReportconfig() != null) {
				limit = LoadApp.getPlatform().getReportconfig().getSync_back_limit();
			}
			
			
			System.out.print(" segmentation.getSegmentation_member_id(); "+segmentation.getSegmentation_member_id());
			String rawIds = segmentation.getSegmentation_member_id();
			
			
		 
			
			Integer[] smi = Arrays.stream(rawIds.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty()) // กรองช่องว่างทิ้ง
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);
			
			
			if (LoadApp.getPlatform().getApp_item_id2() > 0) {
			int targetId = LoadApp.getPlatform().getApp_item_id2();
			    smi = Arrays.stream(smi)
			                .filter(id -> id.equals(targetId))
			                .toArray(Integer[]::new);
			}
			
			FullResult exampleData = EdataDAO.searchRegistersSegmentation(smi,limit);

		
			
			 modelData.put("exampleData",exampleData.getRegisters());
			 modelData.put("countData",exampleData.getTotalCount());
			 modelData.put("registerType","Profile");

		}
		

		
		public void getAllRegister(LoadApp LoadApp, Map<String, Object> modelData) {
			

			String type = LoadApp.getProcess().getRegister_type();

			int limit= LoadApp.getProcess().getShow_limit();
			int limithead= LoadApp.getProcess().getLimit_head();
			int random = 0;
			
			if(LoadApp.getPlatform().getReportconfig() != null) {
				limit = 200;
				random = LoadApp.getPlatform().getReportconfig().getSync_back_random();
			}
			
			FullResult exampleData = EdataDAO.searchRegisters(type,LoadApp.getPlatform(),limit,random,limithead);



			
			 modelData.put("exampleData",exampleData.getRegisters());
			 modelData.put("countData",exampleData.getTotalCount());
			 modelData.put("registerType","Profile");
			 
			 

			 
		}
		public void getAllProduct(LoadApp LoadApp, Map<String, Object> modelData) {
			
			String type = LoadApp.getProcess().getRegister_type();
			int limit= LoadApp.getProcess().getShow_limit();
			int limithead= LoadApp.getProcess().getLimit_head();
			
			FullResult exampleData = EdataDAO.getAllProducts(type,LoadApp.getPlatform().getSearch(),limit,limithead);
			
			 modelData.put("exampleData",exampleData.getProduct_master());
			 modelData.put("countData",exampleData.getTotalCount());
			modelData.put("registerType","Product");
			 
		}
		
		
		
		
	public void getAuditRegister(LoadApp LoadApp, Map<String, Object> modelData) {
			
			
			
			
			String type = LoadApp.getProcess().getRegister_type();
			int limit= LoadApp.getProcess().getShow_limit();
			
			
			FullResult exampleData = EdataDAO.auditRegisters(type,limit);


			 modelData.put("exampleData",exampleData.getRegisters());
			 modelData.put("countData",exampleData.getTotalCount());
			 modelData.put("registerType","Profile");
			 
			 

			 
		}
	
	
	public void getHelpDeskData(LoadApp LoadApp, Map<String, Object> modelData) {
		
		
		
		
		String type = LoadApp.getProcess().getRegister_type();
		int limit= LoadApp.getProcess().getShow_limit();
		
		
		FullResult exampleData = EdataDAO.helpDeskDataRegisters(type,limit);


		 modelData.put("exampleData",exampleData.getRegisters());
		 modelData.put("countData",exampleData.getTotalCount());
		 modelData.put("registerType","Profile");
		 
		 

		 
	}
	
	
	
		public void getAuditProduct(LoadApp LoadApp, Map<String, Object> modelData) {
			
			
			
			
			String type = LoadApp.getProcess().getRegister_type();
			int limit= LoadApp.getProcess().getShow_limit();
			
			
			FullResult exampleData = EdataDAO.auditProduct(type,limit);
	
	
			 modelData.put("exampleData",exampleData.getRegisters());
			 modelData.put("countData",exampleData.getTotalCount());
			 modelData.put("registerType","Profile");
			 
			 
	
			 
	}

		public void getGroupingRegister(LoadApp LoadApp, Map<String, Object> modelData) {
				String type = LoadApp.getProcess().getRegister_type();
				String grouping = LoadApp.getPlatform().getGrouping();
				int limit= LoadApp.getProcess().getShow_limit();
				FullResult exampleData = EdataDAO.groupingRegisters(type,limit,grouping);
				 modelData.put("exampleData",exampleData.getRegisters());
				 modelData.put("countData",exampleData.getTotalCount());
				 modelData.put("registerType","Profile"); 
			}
		
		
		
			public void getGroupingProduct(LoadApp LoadApp, Map<String, Object> modelData) {
				String type = LoadApp.getProcess().getRegister_type();
				String grouping = LoadApp.getPlatform().getGrouping();
				int limit= LoadApp.getProcess().getShow_limit();
				FullResult exampleData = EdataDAO.groupingProduct(type,limit,grouping);
				 modelData.put("exampleData",exampleData.getRegisters());
				 modelData.put("countData",exampleData.getTotalCount());
				 modelData.put("registerType","Profile");			 
		}
		
			
		
		
		
		
		
		
		
		
		

		public void getRegister(LoadApp LoadApp, Map<String, Object> modelData) {	
			int id = LoadApp.getPlatform().getApp_item_id();
		     registers item = EdataDAO.getRegister(id);
			 modelData.put("item",item);
		
		}

		public void getProduct(LoadApp LoadApp, Map<String, Object> modelData) {	
			int id = LoadApp.getPlatform().getApp_item_id();
		     product_master item = EdataDAO.getProduct_master(id);
			 modelData.put("item",item);
		
		}




		public void getProduction_recordbyUseextension(UseExtension useextension, Map<String, Object> modelData) {
			// TODO Auto-generated method stub
		
		}

		
		public void Save_Action(LoadApp LoadApp,String type,Map<String, Object> modelData) {
			switch(type) {
				case "Sales_followup" ->saveSales_followup(LoadApp);
			
			}
			
		}
		public void saveAllSales_followup(LoadApp LoadApp,String type,Map<String, Object> modelData) {
			switch(type) {
				case "Sales_followup" ->saveAllSales_followup(LoadApp);
			
			}
			
		}


		
		
		private void saveSales_followup(LoadApp LoadApp) {
			
			if(LoadApp.getSales_followup() == null) {
				System.out.println(" saveSales_followup /  Sales_followup()  = "+LoadApp.getSales_followup());
			}		 
			
			
			if(LoadApp.getSales_followup() != null) {		 
				  LoadApp.getSales_followup().setEdataId(LoadApp.getEdata().getData_id());

			
				
			sales_followup 	sales_followup = Sales_followup.findByEdataId(LoadApp.getEdata().getData_id());
			if(sales_followup != null ) {   LoadApp.getSales_followup().setId(sales_followup.getId());}
				
			  LoadApp.getSales_followup().setProcessId(LoadApp.getEdata().getProcessId());
			     sales_followup save = Sales_followup.save(LoadApp.getSales_followup());
			     int id_refer = 0;
			     String name = null;
			     registers regiters  = EdataDAO.getProfileByCode(  LoadApp.getSales_followup().getCustomerCode());
			     if(regiters != null) {
			    	 id_refer = regiters.getId();
			    	 name  = regiters.getRegister_name();
			     }			     
			  // ---------------------------------------------------------
			  // ส่วน Logic หลัก (เอาไปแปะแทนของเดิม)
			  // ---------------------------------------------------------

			  if (LoadApp.getProcess().getRenamefile() == 1) {
			      
			      // แบบที่ 1: ProcessName + Customer + Service
			      String newname = joinNames(
			          LoadApp.getProcess().getName(),
			          name,
			          LoadApp.getSales_followup().getServices_offered()
			      );
			      
			      EdataDAO.setTitleAndIdrefer(LoadApp.getEdata().getData_id(), newname,id_refer);

			  } else if (LoadApp.getProcess().getRenamefile() == 2) {
			      
			      // แบบที่ 2: หัวชื่อเก่า + Customer
			      
			      // 1. แกะหัวชื่อเก่า (เช่น "ขอเพิ่ม_dfdddfd" -> เอาแค่ "ขอเพิ่ม")
			      String oldname = LoadApp.getEdata().getData_title();
			      String headerPrefix = "";
			      
			      if (oldname != null && oldname.contains("_")) {
			          headerPrefix = oldname.split("_")[0]; // เอาคำหน้าสุด
			      } else {
			          headerPrefix = oldname; // ถ้าไม่มี _ เลย ก็เอามาทั้งดุ้น
			      }
			      
			      // 2. เอาหัวมาต่อกับชื่อลูกค้า (ProcessName ไม่ใช้แล้ว ตามโจทย์)
			      String newname = joinNames(
			          headerPrefix,
			          name
			      );
			      
			      EdataDAO.setTitleAndIdrefer(LoadApp.getEdata().getData_id(), newname,id_refer);
			  }
			     
			     

				  if( save != null) { 
					    
					 
			
					  
					  
					    addAudit(save,LoadApp);
					  	EdataDAO.saveEdatamainData(LoadApp.getEdata().getData_id(),save.getId(),"sales_followup");
					  
						LoadApp.setHttp_status_code(200);
						LoadApp.setCallback("Success..");		
					}else {
						LoadApp.setHttp_status_code(422);
						LoadApp.setCallback("Unprocessable Entity");
						logService.notice(422,"Error (Unprocessable Entity) : Business rule validation failed", "Production/Save_Action : Sales_followup",LoadApp.getEdata().getData_id());
					}
			}
			
			
			
		}
		
		
		// ---------------------------------------------------------
		// Helper Function: ช่วยต่อคำ (ใส่ไว้ใน Class เดียวกัน หรือเขียนสดก็ได้)
		// ฟังก์ชันนี้จะกรองพวก null, "null", "" ทิ้งให้เองอัตโนมัติ
		// ---------------------------------------------------------
		public String joinNames(String... parts) {
		    java.util.StringJoiner joiner = new java.util.StringJoiner("_");
		    for (String part : parts) {
		        if (part != null && !part.trim().isEmpty() && !part.equalsIgnoreCase("null") && !part.equalsIgnoreCase("undefined")) {
		            joiner.add(part.trim());
		        }
		    }
		    return joiner.toString();
		}

		// ---------------------------------------------------------
		// ส่วน Logic หลัก (เอาไปแปะแทนของเดิม)
		// ---------------------------------------------------------
		
		
		
		private void addAudit(sales_followup save, LoadApp loadApp) {
		    // 1. กัน NullPointerException ที่ตัว object หลักก่อนเลย
		    if (save == null) {
		        return; 
		    }

		    // 2. ดึง Data ID ออกมาเตรียมไว้และเช็ค Null (กันกรณี Session หลุดหรือ LoadApp พัง)
		    int dataId = loadApp.getEdata().getData_id();
		  

		    // --- ส่วนของ Product ---
		    if (save.getProductCode() != null && !save.getProductCode().isEmpty()) {
		        // ใช้ split อย่างระวัง (ถ้า string เป็น "_" ตัวเดียว array อาจว่างได้)
		        String[] parts = save.getProductCode().split("_");
		        String productCode = (parts.length > 0) ? parts[0] : save.getProductCode();
		        
		        EdataDAO.saveAudit(dataId, productCode, "Product");
		    }

		    // --- ส่วนของ Customer (จุดนี้บัคเยอะสุดในโค้ดเก่า) ---
		    if (save.getCustomerCode() != null && !save.getCustomerCode().isEmpty()) {
		        String[] parts = save.getCustomerCode().split("_");
		        String customerCode = (parts.length > 0) ? parts[0] : save.getCustomerCode();

		        // แก้ไข: ส่ง customerCode ที่ตัดแล้ว (ของเดิมส่งตัวเต็ม)
		        // แก้ไข: เปลี่ยน type จาก "Product" เป็น "Customer"
		        EdataDAO.saveAudit(dataId, customerCode, "Profile"); 
		    }
		}


		private void saveAllSales_followup(LoadApp LoadApp) {
		    try {
		        // 1. รับค่าที่ Return กลับมาใส่ตัวแปร (ตั้งชื่อว่า savedList)
		    	
		    	
		    	Sales_followup.deleteAllByTempId( String.valueOf(LoadApp.getEdata().getData_id()) );
		        var savedList = Sales_followup.saveAll(LoadApp.getListSales_followup());

			       
		        
		        
		        // 2. เช็คว่า savedList ไม่เป็น null และมีข้อมูล (แปลว่า Save ลง)
		        if (savedList != null && !savedList.isEmpty()) { 
		            
		            LoadApp.setHttp_status_code(200);
		            LoadApp.setCallback("Success..");       
		            
		            // (Optional) ถ้าอยากอัปเดต List ใน LoadApp ด้วยค่าที่ save แล้ว (เผื่อมี ID gen ใหม่)
		            // LoadApp.setListSales_followup(savedList);

		        } else {
		            // กรณี return null หรือ list ว่าง (ซึ่งปกติ JPA จะไม่ค่อยเกิดท่านี้นอกจาก custom)
		            handleError(LoadApp);
		        }

		    } catch (Exception e) {
		        // 3. สำคัญมาก: ปกติ saveAll ถ้าพัง (เช่น DB connection หลุด, Constraint violation) 
		        // มันจะ Throw Exception ออกมา ไม่ใช่ return null
		        // ต้องดัก catch เพื่อ set 422
		        e.printStackTrace(); // หรือ log error ตามระบบคุณ
		        handleError(LoadApp);
		    }
		}

		
		
		// แยกออกมาเพื่อให้เรียกใช้ซ้ำได้ง่ายใน catch และ else
		private void handleError(LoadApp LoadApp) {
		    LoadApp.setHttp_status_code(422);
		    LoadApp.setCallback("Unprocessable Entity");
		    logService.notice(422,"Error (Unprocessable Entity) : Business rule validation failed", 
		            "Production/Save_Action : Sales_followup", 
		            LoadApp.getEdata().getData_id());
		}
		public registers getProfileByCode(String code) {
			registers data = EdataDAO.getProfileByCode(code);
			return data;
		}

		public product_master getProduct_masterCode(String code) {
			product_master data = EdataDAO.getProduct_masterCode(code);
			return data;
		}

		
		
	
		
		 private  List<FieldChange> compareChanges(Object oldObj, Object newObj,List<String> IGNORE_FIELDS) {
			 
			 
			 System.out.println("compareChanges ...");
			 
		        List<FieldChange> changes = new ArrayList<>();
		        
		        if (oldObj == null || newObj == null) return changes;

		        
		        System.out.println("compareChanges .Obj..");
		        
		        // ดึงฟิลด์ทั้งหมดของ Class มา (ใช้ Class ของตัวใหม่เป็นหลัก)
		        Field[] fields = newObj.getClass().getDeclaredFields();

		        for (Field field : fields) {
		            String fieldName = field.getName();

		            // 1. ข้ามฟิลด์ที่ไม่ต้องการเช็ค
		            if (IGNORE_FIELDS.contains(fieldName)) continue;

		            try {
		                field.setAccessible(true); // อนุญาตให้เข้าถึง private field

		                // 2. ดึงค่าออกมา
		                // หมายเหตุ: ถ้า oldObj กับ newObj เป็นคนละ Class กัน 
		                // แต่ชื่อตัวแปรเหมือนกัน ต้องใช้ Reflection หา field ของ oldObj แยกอีกที
		                Object valOld = getVal(oldObj, fieldName); 
		                Object valNew = field.get(newObj);

		                // 3. แปลงเป็น String เพื่อเทียบ (ตัดปัญหาเรื่อง Type)
		                String strOld = (valOld != null) ? valOld.toString().trim() : "";
		                String strNew = (valNew != null) ? valNew.toString().trim() : "";

		                // 4. เทียบค่า (ถ้าไม่เท่ากัน ให้บันทึก)
		                if (!strOld.equals(strNew)) {
		                    changes.add(new FieldChange(fieldName, strOld, strNew));
		                }

		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		        return changes;
		    }

		    // Helper method กรณี oldObj กับ newObj เป็นคนละ Class กัน
		    private static Object getVal(Object obj, String fieldName) {
		        try {
		            Field f = obj.getClass().getDeclaredField(fieldName);
		            f.setAccessible(true);
		            return f.get(obj);
		        } catch (Exception e) {
		            return null; // หา field ไม่เจอในตัวเก่า
		        }
		    }


		
		
		
	   
		
	
	
	
}
