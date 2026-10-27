package com.smepro.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.request_for_quotation;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.HasId;
import com.smepro.models.LoadApp;
import com.smepro.models.bom;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.repository.Edata_workruleRepository;
import com.smepro.repository.Hr_actionRepository;
import com.smepro.repository.Machine_activityRepository;
import com.smepro.repository.Maintenance_requestsRepository;
import com.smepro.repository.Ps_contractRepository;
import com.smepro.repository.Purchase_order_trackingRepository;
import com.smepro.repository.Request_for_quotationRepository;
import com.smepro.service.PlatformComponent;


@Component
public class Procurement_SupplyChain extends PlatformComponent  {

	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	
	private Ps_contractRepository Ps_contract;
	private Purchase_order_trackingRepository Purchase_order_tracking;
	private Request_for_quotationRepository Request_for_quotation;
	private edataDAO EdataDAO;
	private  SaveAppUnity_Component SaveAppUnity_Components;
	private  MessageSource messageSource;
	private  ApproveApp_Component ApproveApp_Components;

	
	  @Autowired
		public void setMyRepository(
				Ps_contractRepository Ps_contract,
				Purchase_order_trackingRepository Purchase_order_tracking,
				Request_for_quotationRepository Request_for_quotation,
				SaveAppUnity_Component SaveAppUnity_Components,
				MessageSource messageSource,
				edataDAO EdataDAO,
				ApproveApp_Component ApproveApp_Components
				) {
		  
		  this.Ps_contract = Ps_contract;
		  this.Purchase_order_tracking = Purchase_order_tracking;
		  this.Request_for_quotation = Request_for_quotation;
		  this.SaveAppUnity_Components = SaveAppUnity_Components;
		  this.EdataDAO = EdataDAO;
		  this.messageSource = messageSource;
		  this.ApproveApp_Components =ApproveApp_Components;

	  }
	  
	  
	  public void LoadTransactions(LoadApp LoadApp,Map<String, Object> modelData) {
			  
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
			case "edata_procurements." -> transactionexposeAs(EdataDAO.geteData_register(eformId),"edata_procurement",modelData);
			case "ps_contracts." -> transactionexposeAs(Ps_contract.findByEdataId(eformId),"ps_contract",modelData);
			case "purchase_order_trackings." -> transactionexposeAs(Purchase_order_tracking.findByEdataId(eformId),"purchase_order_tracking",modelData);
			case "request_for_quotations." -> transactionexposeAs(Request_for_quotation.findByEdataId(eformId),"request_for_quotation",modelData);
			case "others." -> transactionexposeAs(EdataDAO.getOther(eformId),"other",modelData);
		}	
			
	}	
		
	
	private void Continue(LoadApp LoadApp, Map<String, Object> modelData) {
		edata_procurement	edata_procurement =  EdataDAO.geteData_procurement(LoadApp.getEdata().getData_id());
		transactionexposeAs(edata_procurement,"edata_procurement",modelData);
  	    return;
	}


	private void Modify(LoadApp LoadApp, Map<String, Object> modelData) {

		edata_procurement	edata_procurement =  EdataDAO.geteData_procurement(LoadApp.getEdata().getData_id());
		LoadApp.setEdata_procurements(edata_procurement);
	    transactionexposeAs(edata_procurement,"edata_procurement","edata_procurement_batch_modify",modelData);
  	    return;
		
	}


	private void Processing(LoadApp LoadApp, Map<String, Object> modelData) {
	
		edata_register_processing	edata_register_processing =  EdataDAO.geteData_register_processing(LoadApp.getEdata().getData_id());
        LoadApp.setEdata_register_processing(edata_register_processing);
        transactionexposeAs(edata_register_processing,"edata_register_processing",modelData);
	}


	private void Register(LoadApp LoadApp, Map<String, Object> modelData) {
		
		edata_register register =  EdataDAO.geteData_register(LoadApp.getEdata().getData_id());
  	   transactionexposeAs(register,"edata_register",modelData);
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
	  
	  
	  
		private <T> void DisplayActionbatch(LoadApp LoadApp, String string,Map<String, Object> modelData) {

		  if (string.equals("procurement")) {
			  Procurement(LoadApp,modelData);
		  }
		  else  if (string.equals("procurement_clear")) {
			  Procurement_clear(LoadApp,modelData);
		  }
		  else  if (string.equals("other")) {
			  Other(LoadApp,modelData);
		  }
		  else  if (string.equals("ps_contract")) {
			  Ps_contract(LoadApp,modelData);
		  }
		  else  if (string.equals("purchase_order_tracking")) {
			  Purchase_order_tracking(LoadApp,modelData);
		  }
		  else  if (string.equals("request_for_quotation")) {
			  Request_for_quotation(LoadApp,modelData);
		  }
}

	
	private<T> void Ps_contract(LoadApp LoadApp, Map<String, Object> modelData) {
		  List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<ps_contract> SubDataDTOS = Ps_contract.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			  modelData.put("item",Actionbatch);
				 LoadApp.setActionbatch(Actionbatch);	
		    
	}
	private<T> void Purchase_order_tracking(LoadApp LoadApp, Map<String, Object> modelData) {
		  List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<purchase_order_tracking> SubDataDTOS = Purchase_order_tracking.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
		  modelData.put("item",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);	
		    
	}
	private<T> void Request_for_quotation(LoadApp LoadApp, Map<String, Object> modelData) {
		  List<T> Actionbatch  = new ArrayList<T>();
		  
		  List<request_for_quotation> SubDataDTOS = Request_for_quotation.findAllByTempId(LoadApp.getTempId());
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

private<T> void Procurement_clear(LoadApp LoadApp, Map<String, Object> modelData) {
			List<T> Actionbatch  = new ArrayList<T>();

		  System.out.println("getTempId == "+LoadApp.getTempId());
		  
		  
		  List<edata_procurement> SubDataDTOS = EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId(),"Clear");
		  if(SubDataDTOS.size() > 0) {
			 System.out.println("SubDataDTOSclear.size() == "+SubDataDTOS.get(0).getId());
			 
			 
			 SubDataDTOS.forEach(row->{
				 double total = row.getProcurement_unit_price() * row.getProcurement_quantity_ordered();
				    String formattedTotal = String.format("%.2f", total);
				    row.setProcurement_linetotal2(formattedTotal);
				    Actionbatch.add((T) row);
				    
			 });
			 
		
			 
			
			
		  }
		  
			 double grandtotal2 = SubDataDTOS.stream()
					    .mapToDouble(e -> e.getProcurement_unit_price() *   e.getProcurement_quantity_ordered()) // คำนวณ unit * price
					    .sum(); // รวมค่าทั้งหมด
		  
			 BigDecimal Grandtotal2 = BigDecimal.valueOf(grandtotal2)
					    .setScale(2, RoundingMode.HALF_UP); 
			 
			 
			  modelData.put("item2",Actionbatch);
			  modelData.put("grandtotal2",Actionbatch);
			 LoadApp.setActionbatch(Actionbatch);

		
	}

		private <T> void Procurement(LoadApp LoadApp, Map<String, Object> modelData) {
				
			      List<T> Actionbatch  = new ArrayList<T>();
				  List<edata_procurement> SubDataDTOS = EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId(),"Create");
				  if(SubDataDTOS.size() > 0) {
		
					 SubDataDTOS.forEach(row->{
						
					
						 
						 if(row.getId_refer_by() > 0) {
							 List<doc> docs = EdataDAO.getDoc(row.getId_refer_by());
							
							 if(docs.size() > 0) {
							 String rv ="";
							 if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
							 row.setDoc(docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv);
							 }else {
								 row.setDoc("ID"+row.getId_refer_by());
							 }
						 }
						 
						  if(row.getProcurement_quantityrequested() > 0.0) {
							
							    double total = row.getProcurement_quantityrequested() * row.getProcurement_estimatedprice();
							    String formattedTotal = String.format("%.2f", total);
							    row.setProcurement_linetotal(formattedTotal);
						
						  }
						  
						  if(row.getProcurement_quantity_ordered() > 0.0) {
							  
							    double total = row.getProcurement_quantity_ordered() * row.getProcurement_unit_price();
							    String formattedTotal = String.format("%.2f", total);
							    row.setProcurement_linetotal2(formattedTotal);
							    
							    if(row.getProcurement_quantity_ordered() > row.getProcurement_quantityrequested() ) {
							    	row.setCheck(1);
							    	System.out.println("-----------------quantity_ordered "+row.getProcurement_quantity_ordered());
							    	System.out.println("-----------------quantityrequested "+row.getProcurement_quantityrequested());
								  }
							    
						  }
		
						  if(row.getQuantity_received() > 0.0) {
							  
				
							    if(row.getQuantity_received() > row.getProcurement_quantity_ordered()  ) {
							    	row.setCheck(1);
							 
								  }
							    
						  }
						  
						  
						    
						    System.err.println("unit_price -->"+row.getProcurement_unit_price());
						    System.err.println("quantity_ordered -->"+ row.getProcurement_quantity_ordered());    
						 
						   
						
						    
						    Actionbatch.add((T) row);
					 });
					 
					 
				  }
				  
								  double grandtotal = SubDataDTOS.stream()
										    .mapToDouble(e -> {
										        double quantity = e.getProcurement_quantity_ordered() > 0.001
										                            ? e.getProcurement_quantity_ordered() 
										                            : e.getProcurement_quantityrequested();
										        double price = e.getProcurement_quantity_ordered() > 0.001 
										                        ? e.getProcurement_unit_price() 
										                        : e.getProcurement_estimatedprice();
										        return quantity * price;
										    })
										    .sum();
								 BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
										    .setScale(2, RoundingMode.HALF_UP); 
								 LoadApp.getModel().addAttribute("grandtotal",Grandtotal);	 
						
						
						  
				
					System.out.println(" grandtotal --->"+Grandtotal);
					 System.out.println(" item --->"+Actionbatch.size());
					 
			
				    
					  modelData.put("item",Actionbatch);
					  LoadApp.setActionbatch(Actionbatch);
			}


	
		

		  //this
		public void LoadhasChanges(LoadApp loadApp, String tag,Map<String, Object> modelData) {
			if (tag.equals("edata_procurement")) {
				 List<edata_procurement> list =  (List<edata_procurement>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("other")) {
				 List<other> list =  (List<other>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("ps_contract")) {
				 List<ps_contract> list =  (List<ps_contract>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("purchase_order_tracking")) {
				 List<purchase_order_tracking> list =  (List<purchase_order_tracking>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("request_for_quotation")) {
				 List<request_for_quotation> list =  (List<request_for_quotation>)loadApp.getActionbatch();
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
			 
			 

			  if (tag.equals("procurement")) {
				  
				  
				  if(LoadApp.getEdata_procurements().getId() == 0 ) {
						Random random = new Random();
				        int randomNegative = -random.nextInt(100);
						LoadApp.getEdata_procurements().setId(randomNegative);
					}    
				  

					if(LoadApp.getEdata_procurements().getProcurement_estimatedprice() > 0) {
						total = LoadApp.getEdata_procurements().getProcurement_estimatedprice() * LoadApp.getEdata_procurements().getProcurement_quantityrequested();
						formattedTotal = String.format("%.2f", total);
						LoadApp.getEdata_procurements().setProcurement_linetotal(formattedTotal);
						objects =  (T) LoadApp.getEdata_procurements();
						tempId = LoadApp.getEdata_procurements().getTempId();
						
					}else if(LoadApp.getEdata_procurements().getProcurement_quantity_ordered() > 0) {
						
						total = LoadApp.getEdata_procurements().getProcurement_quantity_ordered() * LoadApp.getEdata_procurements().getProcurement_unit_price();
						formattedTotal = String.format("%.2f", total);
						LoadApp.getEdata_procurements().setProcurement_linetotal2(formattedTotal);
						objects =  (T) LoadApp.getEdata_procurements();
						tempId = LoadApp.getEdata_procurements().getTempId();
					}	
				  
				 
					
					tempId = LoadApp.getTempId();
					edata_procurement  item = (edata_procurement) objects;
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
			  else  if (tag.equals("ps_contract")) {
				  if(LoadApp.getPs_contract().getId() == 0 ) {
						Random random = new Random();
				        int randomNegative = -random.nextInt(100);
						LoadApp.getPs_contract().setId(randomNegative);
					}    
				  
				  
					objects =  (T) LoadApp.getPs_contract();
			
					
					tempId = LoadApp.getTempId();
					ps_contract  item = (ps_contract) objects;
		            item.setTempId(tempId);
					
			  }
			  else  if (tag.equals("purchase_order_tracking")) {
				  
				  if(LoadApp.getPurchase_order_tracking().getId() == 0 ) {
						Random random = new Random();
				        int randomNegative = -random.nextInt(100);
						LoadApp.getPurchase_order_tracking().setId(randomNegative);
					}    
				  
				  
					objects =  (T) LoadApp.getPurchase_order_tracking();
					tempId = LoadApp.getPurchase_order_tracking().getTempId();
					
					tempId = LoadApp.getTempId();
					purchase_order_tracking  item = (purchase_order_tracking) objects;
		            item.setTempId(tempId);
			  }
			  else  if (tag.equals("request_for_quotation")) {
				  
				  if(LoadApp.getRequest_for_quotation().getId() == 0 ) {
						Random random = new Random();
				        int randomNegative = -random.nextInt(100);
						LoadApp.getRequest_for_quotation().setId(randomNegative);
					}    
				  
				  
					objects =  (T) LoadApp.getRequest_for_quotation();
					tempId = LoadApp.getTempId();
					request_for_quotation  item = (request_for_quotation) objects;
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
		
		
		
		private <T> void procurement_clear(LoadApp LoadApp, List<T> actionbatch) {
			
			 List<edata_procurement> SubDataDTOS = (List<edata_procurement>) actionbatch;
		 	 SubDataDTOS.forEach(row->{
				 double total = row.getProcurement_unit_price() * row.getProcurement_quantity_ordered();
				    String formattedTotal = String.format("%.2f", total);
				    row.setProcurement_linetotal2(formattedTotal);
				
				    
			 });
            
		 	actionbatch = (List<T>) SubDataDTOS;  
			
		}


		private <T> void procurement(LoadApp LoadApp, List<T> actionbatch) {
			
			 List<edata_procurement> SubDataDTOS = (List<edata_procurement>) actionbatch;
			 
			 SubDataDTOS.forEach(row->{
					
				 // System.out.println("id_refer -->"+row.getId_refer()); 
				 
					 if(row.getId_refer_by() > 0) {
						 List<doc> docs = EdataDAO.getDoc(row.getId_refer_by());
						
						 if(docs.size() > 0) {
						 String rv ="";
						 if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
						 row.setDoc(docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv);
						 }else {
							 row.setDoc("ID"+row.getId_refer_by());
						 }
					 }
				 
				 
				  if(row.getProcurement_quantityrequested() > 0.0) {
						
					    double total = row.getProcurement_quantityrequested() * row.getProcurement_estimatedprice();
					    String formattedTotal = String.format("%.2f", total);
					    row.setProcurement_linetotal(formattedTotal);
				
				  }
				  if(row.getProcurement_quantity_ordered() > 0.0) {
					  
					    double total = row.getProcurement_quantity_ordered() * row.getProcurement_unit_price();
					    String formattedTotal = String.format("%.2f", total);
					    row.setProcurement_linetotal2(formattedTotal);
					    
					    if(row.getProcurement_quantity_ordered() > row.getProcurement_quantityrequested() ) {
					    	row.setCheck(1);
						  }
					    
				  }
				  if(row.getQuantity_received() > 0.0) {
					  
						
					    if(row.getQuantity_received() > row.getProcurement_quantity_ordered()  ) {
					    	row.setCheck(1);
					 
						  }
					    
				  }
				  
				 
				
				    
			
			
			 });
			 

			 actionbatch = (List<T>) SubDataDTOS;  
			 double grandtotal = SubDataDTOS.stream()
					    .mapToDouble(e -> {
					        double quantity = e.getProcurement_quantity_ordered() > 0.001
					                            ? e.getProcurement_quantity_ordered() 
					                            : e.getProcurement_quantityrequested();
					        double price = e.getProcurement_quantity_ordered() > 0.001
					                        ? e.getProcurement_unit_price() 
					                        : e.getProcurement_estimatedprice();
					        return quantity * price;
					    })
					    .sum();

			 BigDecimal Grandtotal = BigDecimal.valueOf(grandtotal)
					    .setScale(2, RoundingMode.HALF_UP);
			 LoadApp.getModel().addAttribute("grandtotal",Grandtotal);	
		}


		public  void getAbility(LoadApp LoadApp, List<keymap> Keymap_alls) {
			 System.out.println("ability --->");	
				
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
			exists = mainkeys.containsAll(Arrays.asList("quality", "value", "delivery", "support", "user_experience"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.set_poin.toString());
				 return;
			}

			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("procurement_itemid", "quantity_received"));	
			
		
			 System.out.println("ability ---> toString " + LoadApp.getKeymap().toString());	
			
			if(exists) {
				 System.out.println("ability ---> exists ");	
				LoadApp.getAbility().add(AppAbility.set_income_stock.toString());
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
	        	Save_Action(LoadApp,Save_State);
	        	SaveActionbatchdata(LoadApp,Save_State); 
	        	Save_Registration_Request(LoadApp,Save_State);
	        	Save_Register_Processing(LoadApp,Save_State);
	            return Save_State;
			}

		
		


		private void SaveActionbatchdata(LoadApp LoadApp, State Save_State) {
			
			String type = LoadApp.getSub_processes().getStep();
		    String  sessionname=   type.equals("Clear") ? "SubDataclear": "SubData";
			
			
		    
		    
			HttpSession session = LoadApp.getSession();
			List<?> SubDataDTO = (List<?>) session.getAttribute(sessionname+LoadApp.getTempId());
			if (SubDataDTO == null) { return; }

			 Object firstItem = SubDataDTO.get(0);
			try {	 
					String tempId =  String.valueOf(LoadApp.getEdata().getData_id());
				  if (!SubDataDTO.isEmpty()) {	
				
				       	 
		   	         
					  
					  
							 if (firstItem instanceof edata_procurement) { 
						   	          	 System.out.println(" SaveBatchCreate*******> edata_procurement");   	        
						   	        	 
						   	        	EdataDAO.DelEdata_procurements_item(LoadApp.getEdata().getData_id(),type);
							       	    EdataDAO.SaveEdata_procurements_item((List<edata_procurement>) SubDataDTO,LoadApp.getEdata().getData_id());
							       	    Save_State =  State.SUCCESS;  // ✅ บันทึก
							 	}
							 else if (firstItem instanceof ps_contract) { 
				   	          	 System.out.println(" SaveBatchCreate*******> edata_procurement");   	        
				   	        	 
				   	             List<ps_contract> ps_contract = (List<ps_contract>) SubDataDTO;
				   	              ps_contract.forEach(row -> row.setTempId(tempId)); 
				   	              Ps_contract.saveAll(ps_contract);
				   
					       	    Save_State =  State.SUCCESS;  // ✅ บันทึก
							 }
							 else if (firstItem instanceof purchase_order_tracking) { 
				   	          	 System.out.println(" SaveBatchCreate*******> edata_procurement");   	        
				   	        	 
				   	          List<purchase_order_tracking> purchase_order_tracking   = (List<purchase_order_tracking>) SubDataDTO;
				   	           purchase_order_tracking.forEach(row -> row.setTempId(tempId)); 
				   	          
				   	        Purchase_order_tracking.saveAll(purchase_order_tracking);
							 } 
							 else if (firstItem instanceof request_for_quotation) { 
				   	          	 System.out.println(" SaveBatchCreate*******> edata_procurement");   	        
				   	        	 
				   	           List<request_for_quotation> request_for_quotation = (List<request_for_quotation>) SubDataDTO;
				   	            request_for_quotation.forEach(row -> row.setTempId(tempId)); 
				   	            Request_for_quotation.saveAll(request_for_quotation);
							 } 
							 
				   }else {
					   EdataDAO.DelEdata_procurements_item(LoadApp.getEdata().getData_id(),type);
					   Ps_contract.deleteAllByTempId(tempId);
					   Purchase_order_tracking.deleteAllByTempId(tempId);
					   Request_for_quotation.deleteAllByTempId(tempId);
					   
				   }
	       	 
			 }catch(Exception e) {
				 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					
			}
			
			
			   session.removeAttribute(sessionname+LoadApp.getTempId());
		}




		private void Save_Action(LoadApp LoadApp, State Save_State) {
			
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			
			
			 if(LoadApp.getEdata_procurements() != null) {		 
				  LoadApp.getEdata_procurements().setEdata_id(LoadApp.getEdata().getData_id());
		    	  EdataDAO.SaveProcurements(LoadApp.getEdata_procurements());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 	
		    	  
		    	  String title = messageSource.getMessage("title.requesttopurchase", null, locale);
		    
		    	  return;
			 }	
			 
			 if(LoadApp.getPs_contract() != null) {		 
				  LoadApp.getPs_contract().setEdataId(LoadApp.getEdata().getData_id());
				  Ps_contract.save(LoadApp.getPs_contract());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 	
		    	  
		    	  String title = messageSource.getMessage("title.requestcontract", null, locale);
		    	
		    	  return;
			 }	
			 if(LoadApp.getPurchase_order_tracking() != null) {		 
				  LoadApp.getPurchase_order_tracking().setEdataId(LoadApp.getEdata().getData_id());
				  Purchase_order_tracking.save(LoadApp.getPurchase_order_tracking());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 	
		    	  
		    	  String title = messageSource.getMessage("title.purchase_order_tracking", null, locale);
		    	  return;
			 }
			 if(LoadApp.getRequest_for_quotation() != null) {		 
				  LoadApp.getRequest_for_quotation().setEdataId(LoadApp.getEdata().getData_id());
				  Request_for_quotation.save(LoadApp.getRequest_for_quotation());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 	
		    	  String title = messageSource.getMessage("title.request_for_quotation", null, locale);
		    	 return;
			 }
		
		}


		private void Save_Register_Processing(LoadApp LoadApp, State Save_State) {
	
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			 String title = messageSource.getMessage("title.register_processing", null, locale);
			 
			
			
			System.err.println("SaveApp ->Procurement_SupplyChain->Procurement_SupplyChain ");
		   	 
		   	  
		   	  
		 	if(LoadApp.getEdata_register_processing() == null) {return; }
		 	  if(LoadApp.getEdata_register() == null) {return; }
		
			  
			  if(LoadApp.getEdata_register() != null) {  
				  
				  if(LoadApp.getEdata_register().getRegister_proceedings() == null) {return; }	  
				  
			  LoadApp.getEdata_register().setEdata_id(LoadApp.getEdata().getData_id());
			  System.err.println("SaveApp -> ");
			
			  //หาว่ามีไหม
			  if(SaveAppUnity_Components.HaveRegister(LoadApp)) {
				   EdataDAO.SaveRegister(LoadApp.getEdata_register());
				  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
				  
			 }else {
				
				  EdataDAO.SaveRegister(LoadApp.getEdata_register());
				 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง

			 }
		
		     
			  }else if(LoadApp.getEdata_register_processing() != null) {
				  
					System.err.println("SaveApp -> register_processing ");
					  LoadApp.getEdata_register_processing().setEdata_id(LoadApp.getEdata().getData_id());
					
					  if( EdataDAO.SaveRegister_processing(LoadApp.getEdata_register_processing()) > 0) {
						  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					  }else {
						 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					  }
					  
					   title = messageSource.getMessage("title.register_poin", null, locale);
			  }
		     
		     

			
		}


		private void Save_Registration_Request(LoadApp LoadApp, State Save_State) {
			
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			 String title = messageSource.getMessage("title.registration_request", null, locale);
			 
			 
			System.err.println("SaveApp ->Procurement_SupplyChain->Procurement_SupplyChain ");
			  if(LoadApp.getEdata_register() == null) {return; }
			  if(LoadApp.getEdata_register().getRegister_proceedings() != null) {return; }
			  
			  
			  LoadApp.getEdata_register().setEdata_id(LoadApp.getEdata().getData_id());
			  System.err.println("SaveApp -> ");
			 /* 
			  //หาว่าไม่มีซ้ำ
			  if(SaveAppUnity_Components.VerifyRegister(LoadApp)) {
				   EdataDAO.SaveRegister(LoadApp.getEdata_register());
				  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
				  
			 }else {
				 LoadApp.getEdata_register().setRegister_name("Rename ->"+LoadApp.getEdata_register().getRegister_name());
				  EdataDAO.SaveRegister(LoadApp.getEdata_register());
				 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง

			 }
		     */
		}

		public void ApproveData(LoadApp LoadApp) {
			ApproveApp_Components.runMethod(LoadApp.getEdata_procurements(),()-> EdataDAO.APPEdata_procurements(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getPs_contract(),()-> Ps_contract.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getPs_contract(),()-> Purchase_order_tracking.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getPs_contract(),()-> Request_for_quotation.updateDocByEdataId(LoadApp.getEdata()) );

		}



		
}
