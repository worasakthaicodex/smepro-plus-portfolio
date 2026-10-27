package com.smepro.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


import com.smepro.entity.hr_action;
import com.smepro.entity.machine_activity;
import com.smepro.entity.maintenance_requests;
import com.smepro.entity.sales_followup;
import com.smepro.enums.AppAbility;
import com.smepro.models.LoadApp;
import com.smepro.models.bom;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_register;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.repository.Machine_activityRepository;
import com.smepro.repository.Maintenance_requestsRepository;
import com.smepro.repository.Sales_followupRepository;
import com.smepro.service.otherservice;


@Component
public class Sales_Customer {

	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	
	private Sales_followupRepository Sales_followup;
	private edataDAO EdataDAO;
	private otherservice otherservices;
	private  SaveAppUnity_Component SaveAppUnity_Components;
	private  MessageSource messageSource;
	private  ApproveApp_Component ApproveApp_Components;
	
	
	  @Autowired
		public void setMyRepository(
				Sales_followupRepository Sales_followup,
				edataDAO EdataDAO,
				SaveAppUnity_Component SaveAppUnity_Components,
				MessageSource messageSource,
				otherservice otherservices,
				ApproveApp_Component ApproveApp_Components
				) {
		  
		  this.Sales_followup = Sales_followup;
		  this.EdataDAO = EdataDAO;
		  this.SaveAppUnity_Components = SaveAppUnity_Components;
		  this.otherservices = otherservices;
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
			case "sales_followups." -> transactionexposeAs(Sales_followup.findByEdataId(eformId),"sales_followup",modelData);
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
	  	edata_register register =  EdataDAO.geteData_register(LoadApp.getEdata().getData_id());
	 	transactionexposeAs(register,"edata_register",modelData);
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
	public void LoadBatch_insert(LoadApp LoadApp) {
		 DisplayActionbatch(LoadApp,LoadApp.getType());
	}
	  
	  
	  
	private <T> void DisplayActionbatch(LoadApp LoadApp, String string) {

		  if (string.equals("sales_followup")) {
			  Sales_followup(LoadApp);
		  }
		  else  if (string.equals("other")) {
			  Other(LoadApp);
		  }
	
	}
	
	
	
	
	
private <T> void Sales_followup(LoadApp LoadApp) {
		
	//	HttpSession session = LoadApp.getSession();
		
		System.out.println("-----------------getId_refer_by Sales_followup");
		  
		  List<T> Actionbatch  = new ArrayList<T>();
		//  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<sales_followup> SubDataDTOS = Sales_followup.findAllByTempId(LoadApp.getTempId());
		

		   Optional<sales_followup>  Customer_code  = SubDataDTOS.stream().filter(row->row.getCustomerCode() != null).findFirst();
		   if(Customer_code.isPresent()) {
			   sales_followup  code = Customer_code.get();
			   List<sales_followup> item2 = Sales_followup.findAllByProcessIdAndCustomerCode(code.getProcessId(),code.getCustomerCode());
			   LoadApp.getModel().addAttribute("item2",item2);	
		   }
		  
		  
		 Optional<sales_followup>  option1  = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getPrice_per_unit()) > 0.0).findFirst();
		  
		  if(option1.isPresent()) {

			  Double discounts = 0.0;
			  Double totals = 0.0;
			  Optional<sales_followup> vat = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getVat()) > 0.0).findFirst();
			  
			  
			  
			  for (sales_followup row : SubDataDTOS) {
				  Double price = otherservices.DoubleValueOf(row.getPrice_per_unit());
				  Double count = otherservices.DoubleValueOf(row.getUnit_count());
				  Double discount = otherservices.DoubleValueOf(row.getDiscount());

			      discounts += discount;

			      Double lineTotal = price * count;
			      totals += lineTotal;

			      row.setTotal(String.format("%.2f", lineTotal - discount));

			      Actionbatch.add((T) row);
			  }

			  // STEP 1: grand total
			   Double adjustedtotal = totals - discounts;

		
			  // STEP 3: VAT & net amount
			   
			   Double vatRate = 0.0;
			   Double netAmount = adjustedtotal;
			   
			   if(vat.isPresent()) {
				   sales_followup VatRates = vat.get();
				    vatRate = otherservices.DoubleValueOf(VatRates.getVat()); // เช่น 7.0
				   Double vatAmount = adjustedtotal * (vatRate / 100);
				   netAmount = adjustedtotal + vatAmount;
			   }
			 
	
			  
			  
			  LoadApp.getModel().addAttribute("grandtotal",totals);	 
			  LoadApp.getModel().addAttribute("vat",vatRate);	 
			  LoadApp.getModel().addAttribute("adjustedtotal",adjustedtotal);	 
			  LoadApp.getModel().addAttribute("net_amount",netAmount);	 
			//  session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			   LoadApp.getModel().addAttribute("item",Actionbatch);	
			   
			   return;
		  }

			
		  Optional<sales_followup>  option2  = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getPayment_amount()) > 0.0).findFirst();
		  if(option2.isPresent()) {
			  
			  Double totals = 0.0;
			  for (sales_followup row : SubDataDTOS) {
		
				  Double Payment_amount = otherservices.DoubleValueOf(row.getPayment_amount());
				  totals += Payment_amount;
			      row.setTotal(String.format("%.2f",Payment_amount));
			      Actionbatch.add((T) row);
			  }
			  
			  LoadApp.getModel().addAttribute("grandtotal",totals);	 
			//  session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			  LoadApp.getModel().addAttribute("item",Actionbatch);	
			   return;
			  
		  }
		  
		  
		  
	
		    
		
	}

 
	private<T> void Other(LoadApp LoadApp) {
	//	HttpSession session = LoadApp.getSession();
		  List<T> Actionbatch  = new ArrayList<T>();
		//  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<other> SubDataDTOS = EdataDAO.getAllEdata_other_item(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
		//	session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			 LoadApp.getModel().addAttribute("item",Actionbatch);	
		    
	}


	/**
	 * ==========================================================
	 * 📥 Load Sub-Table Batch Data (Not yet save)
	 * ==========================================================
	 * Loads and displays the data batch for the sub-table (child list)
	 * based on the provided type from the LoadApp context.
	 *
	 * @param LoadApp   The application context containing user/session data
	 * ----------------------------------------------------------
	 *  ✅ Typical use: Loading detail rows for a selected master record
	 * ==========================================================
	 */
	public void Batch_inserthasChanges(LoadApp LoadApp) {
		 Batch_process(LoadApp,LoadApp.getType());
	}


	
	private <T extends Identifiable> void Batch_process(LoadApp LoadApp, String tag) {
		
		
		
		//HttpSession session = LoadApp.getSession();
		 T objects = null;
		 String object_type = "";
		 String tempId = "";
		 double total =0.0;
		 Double Doubletotal =0.0;
		 String formattedTotal ="0.0";	
		 int  item_ids = 0;
		 List<T> Actionbatch;	
		 
		  if (tag.equals("sales_followup")) {
			  
			  if(LoadApp.getSales_followup().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getSales_followup().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getSales_followup();
				tempId = LoadApp.getSales_followup().getTempId();

				sales_followup  item_id = (sales_followup) objects;
		        item_ids = item_id.getId();
		        
		  }
		  else  if (tag.equals("other")) {
			  
			  if(LoadApp.getProduction_standard().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getProduction_standard().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getOther();
				tempId = LoadApp.getOther().getTempId();

				other  item_id = (other) objects;
		        item_ids = item_id.getId();
		  }
		 
		 

	
		  Actionbatch = Actionbatch(tempId,LoadApp);
	      if(item_ids == 0) {return;}
          hasChanges(objects,LoadApp,item_ids,Actionbatch);
          
       //   session.setAttribute("SubData"+tempId,Actionbatch);
          LoadApp.getModel().addAttribute("item",Actionbatch);	
	}
	
	


	private <T extends Identifiable> void hasChanges(T objects,LoadApp LoadApp,int id,List<T> actionbatch) {
		
		  int indexToRemove =-1;
			  System.out.println("Actionbatch id "+id);
		   
		       for (int i = 0; i < actionbatch.size(); i++) {
		          if (actionbatch.get(i).getId() == id) {
		              indexToRemove = i;
		              break;
		          }
		      }
		       actionbatch.removeIf(subData -> subData.getId() == id ); 
		      
		  		  if(indexToRemove > -1 ) {
						 actionbatch.add(indexToRemove,(T) objects); 
				  }else {
						 actionbatch.add((T) objects); 
				  }
		  		  
		  		if (objects instanceof sales_followup) {
		  			sales_followup(LoadApp,actionbatch);
		  		} 
		  		  
	}
	

	private  <T> List<T> Actionbatch(String tempId,LoadApp LoadApp) {
	//	HttpSession session = LoadApp.getSession();
	//	List<T> Actionbatch = (List<T>) session.getAttribute("SubData"+tempId);
	//	if (Actionbatch == null) {  
	//		Actionbatch = new ArrayList<T>();
	//	}
	//	return Actionbatch;
		return null;
	}

	  
	
	private <T> void sales_followup(LoadApp LoadApp, List<T> Actionbatch) {
		  
		 List<sales_followup> SubDataDTOS = (List<sales_followup>) Actionbatch;
		
		  
		 Optional<sales_followup>  option1  = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getPrice_per_unit()) > 0.0).findFirst();
		  
		  if(option1.isPresent()) {

			  Double discounts = 0.0;
			  Double totals = 0.0;
			  Optional<sales_followup> vat = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getVat()) > 0.0).findFirst();
			  
			  
			  
			  for (sales_followup row : SubDataDTOS) {
				  Double price = otherservices.DoubleValueOf(row.getPrice_per_unit());
				  Double count = otherservices.DoubleValueOf(row.getUnit_count());
				  Double discount = otherservices.DoubleValueOf(row.getDiscount());

			      discounts += discount;

			      Double lineTotal = price * count;
			      totals += lineTotal;

			      row.setTotal(String.format("%.2f", lineTotal - discount));

			  }

			  // STEP 1: grand total
			   Double adjustedtotal = totals - discounts;

		
			  // STEP 3: VAT & net amount
			   
			   Double vatRate = 0.0;
			   Double netAmount = adjustedtotal;
			   
			   if(vat.isPresent()) {
				   sales_followup VatRates = vat.get();
				    vatRate = otherservices.DoubleValueOf(VatRates.getVat()); // เช่น 7.0
				   Double vatAmount = adjustedtotal * (vatRate / 100);
				   netAmount = adjustedtotal + vatAmount;
			   }
			 
			  
			  
			  
			  
			  LoadApp.getModel().addAttribute("grandtotal",totals);	 
			  LoadApp.getModel().addAttribute("vat",vatRate);	 
			  LoadApp.getModel().addAttribute("adjustedtotal",adjustedtotal);	 
			  LoadApp.getModel().addAttribute("net_amount",netAmount);	 
		
		  }
		
	
	
}


	public  void getAbility(LoadApp LoadApp, List<keymap> keymap_alls) {
		
		List<String> mainkeys;
		boolean exists;
		
		
		mainkeys = keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		 exists = mainkeys.containsAll(Arrays.asList("register_proceedings", "register_name"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.update_register_list.toString());
			return;
		}
		
		
		mainkeys = keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("register_name", "register_type"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.register_list.toString());
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
		    	SaveActionbatchdata(LoadApp,Save_State); 
		        return Save_State;
		}
		
		
		private void SaveActionbatchdata(LoadApp LoadApp, State Save_State) {
			
			String type = LoadApp.getSub_processes().getStep();
		  //  String  sessionname=   type.equals("Clear") ? "SubDataclear": "SubData";
			
			/*
		//	HttpSession session = LoadApp.getSession();
		//	List<?>  SubDataDTO= (List<?>) session.getAttribute(sessionname+LoadApp.getTempId());
			if (SubDataDTO == null) { return; }

			 Object firstItem = SubDataDTO.get(0);
			try {	 
				
				String tempId =  String.valueOf(LoadApp.getEdata().getData_id());
				  if (!SubDataDTO.isEmpty()) {		
						
	
			    	       if (firstItem instanceof other) {     	
			    				EdataDAO.DelOther(LoadApp.getEdata().getData_id());
					       	    EdataDAO.SaveOther((List<other>) SubDataDTO,LoadApp.getEdata().getData_id());
					       	    Save_State =  State.SUCCESS;  
			    	        }    
			    	       else if (firstItem instanceof sales_followup) {     	
			    	    	  	 System.out.println(" SaveBatchCreate*******> sales_followup");   	        
				   	        	 
				   	             List<sales_followup> sales_followups = (List<sales_followup>) SubDataDTO;
				   	          sales_followups.forEach(row -> row.setTempId(tempId)); 
				   	          Sales_followup.saveAll(sales_followups);
				   
					       	    Save_State =  State.SUCCESS;  // ✅ บันทึก
			    	        }  
			    	      
						 
						 
						 
				  }else {
						EdataDAO.DelOther(LoadApp.getEdata().getData_id());
						Sales_followup.deleteAllByTempId(tempId);
		    	    	
		    	    	
				   }
	       	 
			 }catch(Exception e) {
				 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					
			}
			
			
			   session.removeAttribute(sessionname+LoadApp.getTempId());
			*/
		}
		
		
		private void Save_Action(LoadApp LoadApp, State Save_State) {
			 

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
		      
		      if(LoadApp.getSales_followup() != null) {		 
				  LoadApp.getSales_followup().setEdataId(LoadApp.getEdata().getData_id());
				  Sales_followup.save(LoadApp.getSales_followup());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	  return;
			 }	
		
			
		}
		
		
		private void Save_Register_Processing(LoadApp LoadApp, State Save_State) {
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			 String title = messageSource.getMessage("title.register_processing", null, locale);
			 
			
			
			System.err.println("SaveApp ->Save_Register_Processing ");

		 	 if(LoadApp.getEdata_register_processing() == null   
		 			 && LoadApp.getEdata_register() == null
		 			 ) {return; }


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
		   
			
		}
		}
		
		private void Save_Registration_Request(LoadApp LoadApp, State Save_State) {
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			 String title = messageSource.getMessage("title.registration_request", null, locale);
			 
			 
			System.err.println("SaveApp ->Save_Registration_Request ");
			  if(LoadApp.getEdata_register() == null) {return; }
			  
			  
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
			ApproveApp_Components.runMethod(LoadApp.getSales_followup(),()-> Sales_followup.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getOther(),()-> EdataDAO.APPOther(LoadApp.getEdata()) );
			
		}

	
}




