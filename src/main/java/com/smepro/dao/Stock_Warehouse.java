package com.smepro.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
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
import com.smepro.enums.AppAbility;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.LoadApp;
import com.smepro.models.bom;
import com.smepro.models.edata_procurement;
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
public class Stock_Warehouse  extends PlatformComponent{

	
	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	private edataDAO EdataDAO;
	private  SaveAppUnity_Component SaveAppUnity_Components;
	private  MessageSource messageSource;
	private  ApproveApp_Component ApproveApp_Components;
	
	  @Autowired
		public void setMyRepository(
				SaveAppUnity_Component SaveAppUnity_Components,
				MessageSource messageSource,
				edataDAO EdataDAO,
				ApproveApp_Component ApproveApp_Components
				) {
		  this.SaveAppUnity_Components = SaveAppUnity_Components;
		  this.EdataDAO = EdataDAO;
		  this.messageSource = messageSource;
		  this.ApproveApp_Components =ApproveApp_Components;
	  }
	  
	  
	  public void LoadTransactions(LoadApp LoadApp) {
			  
			  if(LoadApp.getRegister_count() > 0) {
				  Register(LoadApp);
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
		// TODO Auto-generated method stub
		
	}


	private void Modify(LoadApp LoadApp) {
		// TODO Auto-generated method stub
		
	}


	private void Processing(LoadApp LoadApp) {

	}


	private void Register(LoadApp LoadApp) {

		
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
	  
	
	
	private <T> void DisplayActionbatch(LoadApp LoadApp, String type,Map<String, Object> modelData) {

		
		  Map<String, Function<LoadApp, List<?>>> actionDataLoaders = Map.of(
			    "other", loadApp -> EdataDAO.getAllEdata_other_item(loadApp.getTempId()),
			    "goods_receipt_sk", loadApp -> EdataDAO.getAllGoods_receipt_sk_item(loadApp.getTempId()),
			    "inventory_transfer_sk", loadApp -> EdataDAO.getAllInventory_transfer_sk_item(loadApp.getTempId()),
			    "issue_sk", loadApp -> EdataDAO.getAllIssued_sk_item(loadApp.getTempId()),
			    "adjustment_sk", loadApp -> EdataDAO.getAllAdjustment_sk_item(loadApp.getTempId()),
			    "goods_receipt_fg", loadApp -> EdataDAO.getAllGoods_receipt_fg_item(loadApp.getTempId()),
			    "inventory_transfer_fg", loadApp -> EdataDAO.getAllInventory_transfer_fg_item(loadApp.getTempId()),
			    "issue_fg", loadApp -> EdataDAO.getAllIssued_fg_item(loadApp.getTempId()),
			    "adjustment_fg", loadApp -> EdataDAO.getAllAdjustment_fg_item(loadApp.getTempId())
			);
		  
		  CommonActionbatch(LoadApp,type,actionDataLoaders,modelData);
		
		
		
	}
	  
	private <T> void CommonActionbatch(LoadApp LoadApp, String type,Map<String, Function<LoadApp, List<?>>> actionDataLoaders, Map<String, Object> modelData) {
		
		 Function<LoadApp, List<?>> loader = actionDataLoaders.get(type);
		
	    if (loader != null) {
	        List<?> data = loader.apply(LoadApp);
	        modelData.put("item",data);
			LoadApp.setActionbatch(data);	

	    } else {
	        System.out.println("Unknown type: " + type);
	    }
	}


	
	
	 //this
		public void LoadhasChanges(LoadApp loadApp, String tag,Map<String, Object> modelData) {
			if (tag.equals("goods_receipt_sk")) {
				 List<Goods_receipt_sk> list =  (List<Goods_receipt_sk>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("inventory_transfer_sk")) {
				 List<Inventory_transfer_sk> list =  (List<Inventory_transfer_sk>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("issue_sk")) {
				 List<Issue_sk> list =  (List<Issue_sk>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("adjustment_sk")) {
				 List<Adjustment_sk> list =  (List<Adjustment_sk>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			
			if (tag.equals("goods_receipt_fg")) {
				 List<Goods_receipt_fg> list =  (List<Goods_receipt_fg>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("inventory_transfer_fg")) {
				 List<Inventory_transfer_fg> list =  (List<Inventory_transfer_fg>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("issue_fg")) {
				 List<Issue_fg> list =  (List<Issue_fg>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			else if (tag.equals("adjustment_fg")) {
				 List<Adjustment_fg> list =  (List<Adjustment_fg>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
			
			else if (tag.equals("other")) {
				 List<other> list =  (List<other>)loadApp.getActionbatch();
				 modelData.put("item",list);
			  }
		
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
		 
		 
		 	if (tag.equals("other")) {
			  
			  if(LoadApp.getOther().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getOther().setId(randomNegative);
				}    
				objects =  (T) LoadApp.getOther();
				tempId = LoadApp.getTempId();
				other  item = (other) objects;
				 item.setTempId(tempId);
		  }
		 else if (tag.equals("goods_receipt_sk")) {
				  
				  if(LoadApp.getGoods_receipt_sk().getId() == 0 ) {
						Random random = new Random();
				        int randomNegative = -random.nextInt(100);
						LoadApp.getGoods_receipt_sk().setId(randomNegative);
					}    
					objects =  (T) LoadApp.getGoods_receipt_sk();
					

					Goods_receipt_sk  item = (Goods_receipt_sk) objects;
					tempId = LoadApp.getTempId();
					 item.setTempId(tempId);
			  }
		 else if (tag.equals("inventory_transfer_sk")) {
			  
			  if(LoadApp.getInventory_transfer_sk().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getInventory_transfer_sk().setId(randomNegative);
				}    
				objects =  (T) LoadApp.getInventory_transfer_sk();
				Inventory_transfer_sk  item = (Inventory_transfer_sk) objects;
				tempId = LoadApp.getTempId();
				 item.setTempId(tempId);
		  }
		 else if (tag.equals("issue_sk")) {
			  
			  if(LoadApp.getIssue_sk().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getIssue_sk().setId(randomNegative);
				}    
				objects =  (T) LoadApp.getIssue_sk();
				Issue_sk  item = (Issue_sk) objects;
				tempId = LoadApp.getTempId();
				 item.setTempId(tempId);
		  }
		 else if (tag.equals("adjustment_sk")) {
			  
			  if(LoadApp.getAdjustment_sk().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getAdjustment_sk().setId(randomNegative);
				}    
				objects =  (T) LoadApp.getAdjustment_sk();
				Adjustment_sk  item = (Adjustment_sk) objects;
				tempId = LoadApp.getTempId();
				 item.setTempId(tempId);
		  }
		 else if (tag.equals("goods_receipt_fg")) {
			  
			  if(LoadApp.getGoods_receipt_fg().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getGoods_receipt_fg().setId(randomNegative);
				}    
				objects =  (T) LoadApp.getGoods_receipt_fg();
				Goods_receipt_fg  item = (Goods_receipt_fg) objects;
				tempId = LoadApp.getTempId();
				 item.setTempId(tempId);
		  }
	 else if (tag.equals("inventory_transfer_fg")) {
		  
		  if(LoadApp.getInventory_transfer_fg().getId() == 0 ) {
				Random random = new Random();
		        int randomNegative = -random.nextInt(100);
				LoadApp.getInventory_transfer_fg().setId(randomNegative);
			}    
			objects =  (T) LoadApp.getInventory_transfer_fg();
			Inventory_transfer_fg  item = (Inventory_transfer_fg) objects;
			tempId = LoadApp.getTempId();
			 item.setTempId(tempId);
	  }
	 else if (tag.equals("issue_fg")) {
		  
		  if(LoadApp.getIssue_fg().getId() == 0 ) {
				Random random = new Random();
		        int randomNegative = -random.nextInt(100);
				LoadApp.getIssue_sk().setId(randomNegative);
			}    
			objects =  (T) LoadApp.getIssue_fg();
			Issue_fg  item = (Issue_fg) objects;
			tempId = LoadApp.getTempId();
			 item.setTempId(tempId);
	  }
	 else if (tag.equals("adjustment_fg")) {
		  
		  if(LoadApp.getAdjustment_fg().getId() == 0 ) {
				Random random = new Random();
		        int randomNegative = -random.nextInt(100);
				LoadApp.getAdjustment_fg().setId(randomNegative);
			}    
			objects =  (T) LoadApp.getAdjustment_fg();
			Adjustment_fg  item = (Adjustment_fg) objects;
			tempId = LoadApp.getTempId();
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

	
	

	public void getAbility(LoadApp LoadApp, List<keymap> Keymap_alls) {
		
		 System.out.println(" ---------> Have Ability Stock_Warehouse ");
		 
		 List<String>	mainkeys;
			boolean exists;
		 
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("stock_proceedings", "stock_list"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.update_register_stock.toString());
				 return;
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("stock_list", "stock_type"));
			
			if(exists) {
				LoadApp.getAbility().add(AppAbility.register_stock.toString());
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
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("issued_product_code", "issued_quantity"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.issued_fg.toString());
				 return;
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("adjustment_product_code", "adjustment_quantity"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.adjustment_fg.toString());
				 return;
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("goods_receipt_quantity", "goods_receipt_product_code"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.goods_receipt_fg.toString());
				 return;
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("transfer_product_code", "transfer_to_location"));	
			if(exists) {
				System.out.println(" ---------> Have Ability inventory_transfer_fg  ");
				
				LoadApp.getAbility().add(AppAbility.inventory_transfer_fg.toString());
				 return;
			}
			
			
			
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("ski_stock_code", "ski_quantity"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.issued_sk.toString());
				 return;
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("ska_stock_code", "ska_quantity"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.adjustment_sk.toString());
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("skg_stock_code", "skg_quantity_received"));	
			if(exists) {
				LoadApp.getAbility().add(AppAbility.goods_receipt_sk.toString());
			}
			
			
			mainkeys = Keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
			exists = mainkeys.containsAll(Arrays.asList("skt_stock_code", "skt_to_location"));	
			if(exists) {
				System.out.println(" ---------> Have Ability inventory_transfer_fg  ");
				
				LoadApp.getAbility().add(AppAbility.inventory_transfer_sk.toString());
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
			
	    	System.out.println("<------------Stock Warehouse--------> ");
	    	State Save_State = State.NONE;
	    	Save_Action(LoadApp,Save_State);
	    	SaveActionbatchdata(LoadApp,Save_State); 
	    	Save_Registration_Request(LoadApp,Save_State);
	    	Save_Register_Processing(LoadApp,Save_State);
	        return Save_State;
	   }


		private void SaveActionbatchdata(LoadApp LoadApp, State Save_State) {
			// TODO Auto-generated method stub
			String type = LoadApp.getSub_processes().getStep();
		    String  sessionname=   type.equals("Clear") ? "SubDataclear": "SubData";
			
			
		    
		    
			HttpSession session = LoadApp.getSession();
			List<?> SubDataDTO = (List<?>) session.getAttribute(sessionname+LoadApp.getTempId());
			if (SubDataDTO == null) { return; }

			 Object firstItem = SubDataDTO.get(0);
			try {	 
			
				  if (!SubDataDTO.isEmpty()) {	
				
						if (firstItem instanceof other) { 
					 		EdataDAO.DelOther(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveOther((List<other>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Goods_receipt_sk) { 
					 		EdataDAO.DelGoods_receipt_sk(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveGoods_receipt_sk((List<Goods_receipt_sk>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Inventory_transfer_sk) { 
					 		EdataDAO.DelInventory_transfer_sk(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveInventory_transfer_sk((List<Inventory_transfer_sk>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Issue_sk) { 
					 		EdataDAO.DelIssue_sk(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveIssue_sk((List<Issue_sk>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Adjustment_sk) { 
					 		EdataDAO.DelAdjustment_sk(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveAdjustment_sk((List<Adjustment_sk>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Goods_receipt_fg) { 
					 		EdataDAO.DelGoods_receipt_fg(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveGoods_receipt_fg((List<Goods_receipt_fg>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Inventory_transfer_fg) { 
					 		EdataDAO.DelInventory_transfer_fg(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveInventory_transfer_fg((List<Inventory_transfer_fg>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Issue_fg) { 
					 		EdataDAO.DelIssue_fg(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveIssue_fg((List<Issue_fg>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
					 	}
						else if (firstItem instanceof Adjustment_fg) { 
					 		EdataDAO.DelAdjustment_fg(LoadApp.getEdata().getData_id());
				       	    EdataDAO.SaveAdjustment_fg((List<Adjustment_fg>) SubDataDTO,LoadApp.getEdata().getData_id());
				       	    Save_State =  State.SUCCESS;  
				       	    
				       	    
					 	}
						
				   }else {
						EdataDAO.DelOther(LoadApp.getEdata().getData_id());
						EdataDAO.DelGoods_receipt_sk(LoadApp.getEdata().getData_id());
						EdataDAO.DelInventory_transfer_sk(LoadApp.getEdata().getData_id());
						EdataDAO.DelIssue_sk(LoadApp.getEdata().getData_id());
						EdataDAO.DelAdjustment_sk(LoadApp.getEdata().getData_id());
						EdataDAO.DelGoods_receipt_fg(LoadApp.getEdata().getData_id());
						EdataDAO.DelInventory_transfer_fg(LoadApp.getEdata().getData_id());
						EdataDAO.DelIssue_fg(LoadApp.getEdata().getData_id());
						EdataDAO.DelAdjustment_fg(LoadApp.getEdata().getData_id());
				   }
	       	 
			 }catch(Exception e) {
				 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					
			}
			
			
			   session.removeAttribute(sessionname+LoadApp.getTempId());
		}
		
		
		private void Save_Action(LoadApp LoadApp, State Save_State) {
			// TODO Auto-generated method stub
			
		}
		
		
		private void Save_Register_Processing(LoadApp LoadApp, State Save_State) {
			
			
				Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			
				int resultReceipt = EdataDAO.Receipt_SK(LoadApp.getGoods_receipt_sk(), LoadApp.getEdata().getData_id());
				if(resultReceipt > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					
					 String title = messageSource.getMessage("title.receipt_sk", null, locale);
			    	
					return;
				}
				int resultTransfer = EdataDAO.Transfer_SK(LoadApp.getInventory_transfer_sk(), LoadApp.getEdata().getData_id());
				if(resultTransfer > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.transfer_sk", null, locale);
			    	return;
				}
				
				
				int resultIssue = EdataDAO.Issue_SK(LoadApp.getIssue_sk(), LoadApp.getEdata().getData_id());
				if(resultIssue > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.issue_sk", null, locale);
			    	return;
				}
				
				int resultAdjustment = EdataDAO.Adjustment_SK(LoadApp.getAdjustment_sk(), LoadApp.getEdata().getData_id());
				if(resultAdjustment > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.adjustment_sk", null, locale);
			    	return;
				}
				
				
				int resultReceipt2 = EdataDAO.Receipt_FG(LoadApp.getGoods_receipt_fg(), LoadApp.getEdata().getData_id());
				if(resultReceipt2 > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.receipt_fg", null, locale);
			    	return;
				}
				
				int resultTransfer2 = EdataDAO.Transfer_FG(LoadApp.getInventory_transfer_fg(), LoadApp.getEdata().getData_id());
				if(resultTransfer2 > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.transfer_fg", null, locale);
			    	return;
				}
				
				int resultIssue2 = EdataDAO.Issue_FG(LoadApp.getIssue_fg(), LoadApp.getEdata().getData_id());
				if(resultIssue2 > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.issue_fg", null, locale);
			    	return;
				}
				
				int resultAdjustment2 = EdataDAO.Adjustment_FG(LoadApp.getAdjustment_fg(), LoadApp.getEdata().getData_id());
				if(resultAdjustment2 > 0) {
					Save_State = State.SUCCESS; // ✅ สำเร็จ ถ้ามีสักอันผ่าน
					
					 String title = messageSource.getMessage("title.adjustment_fg", null, locale);
			    	return;
				}
				
				

				boolean anyStockTotalError = resultReceipt == -1 || resultTransfer == -1 || resultIssue == -1 || resultAdjustment == -1
						|| resultReceipt2 == -1 || resultTransfer2 == -1 || resultIssue2 == -1 || resultAdjustment2 == -1;
	
			
				    if (anyStockTotalError) {
				        LoadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(), Save_status.Sys_Failed_Stock_total.name());
				        LoadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(), Save_status.Sys_Failed_Stock_total.getLabel());
				        Save_State = State.INVALID_TYPE; // ❌ ล้มเหลวทั้งหมด
				        
				    }
				    
				    
					if(LoadApp.getEdata_register() != null) {
						  if(LoadApp.getEdata_register().getRegister_proceedings() == null) {return; }	  
						
						
						  LoadApp.getEdata_register().setEdata_id(LoadApp.getEdata().getData_id());
						  
							 if(SaveAppUnity_Components.HaveRegister(LoadApp)) {
								 EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
								 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
							 }else {
							
								EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
								Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
							 }
						 	 String title = messageSource.getMessage("title.registration_request", null, locale);
						     Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
						      
					
					 
						      
						}else  if(LoadApp.getEdata_product().getProduct_names() != null) {
							if(LoadApp.getEdata_product().getProduct_proceedings() == null) {return; }	  
							  
							  LoadApp.getEdata_product().setEdata_id(LoadApp.getEdata().getData_id());
							  System.err.println("SaveApp -> ");
							  /*
							 if(SaveAppUnity_Components.HaveProduct(LoadApp)) {
								  EdataDAO.SaveProduct(LoadApp.getEdata_product());
								 Save_State =  State.SUCCESS;      
							 }else {
								
								  EdataDAO.SaveProduct(LoadApp.getEdata_product());
								 Save_State =  State.INVALID_TYPE;  
							 }
							*/
							 
							 String title = messageSource.getMessage("title.registration_request_products", null, locale);
							
							 }else if(LoadApp.getEdata_stock().getStock_list() != null) {
								 
									if(LoadApp.getEdata_stock().getStock_proceedings() == null) {return; }	  
								 
								  LoadApp.getEdata_stock().setEdata_id(LoadApp.getEdata().getData_id());
									  
									  System.err.println("SaveApp -> ");
									 if(SaveAppUnity_Components.HaveStock(LoadApp)) {
										  EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
										 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
									 }else {
									
										  EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
										 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					
									 }
									
									 
									 String title = messageSource.getMessage("title.registration_request_stock", null, locale);
				
								    
								     Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
								      
							
							 }	    
				    
				  
			
			
		}
		
		
		private void Save_Registration_Request(LoadApp LoadApp, State Save_State) {
			
			Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			 String title = messageSource.getMessage("title.register_processing", null, locale);
			 
			 
			if(LoadApp.getEdata_stock() != null) {
			  LoadApp.getEdata_stock().setEdata_id(LoadApp.getEdata().getData_id());
			  
				/* if(SaveAppUnity_Components.VerifyRegister(LoadApp)) {
					 EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
					 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
				 }else {
					LoadApp.getEdata_register().setRegister_name("Rename ->"+LoadApp.getEdata_register().getRegister_name());
					EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
					Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
				 }
			 	
			     Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
			      */
		
		 
			      
			}else  if(LoadApp.getEdata_product().getProduct_names() != null) {
				
				  
				  LoadApp.getEdata_product().setEdata_id(LoadApp.getEdata().getData_id());
				  System.err.println("SaveApp -> ");
				  /*
				 if(SaveAppUnity_Components.VerifyProduct(LoadApp)) {
					  EdataDAO.SaveProduct(LoadApp.getEdata_product());
					 Save_State =  State.SUCCESS;      
				 }else {
					 LoadApp.getEdata_product().setProduct_names("Rename ->"+LoadApp.getEdata_product().getProduct_names());
					  EdataDAO.SaveProduct(LoadApp.getEdata_product());
					 Save_State =  State.INVALID_TYPE;  
				 }
				*/
				 
				 
				   
				 }else if(LoadApp.getEdata_stock().getStock_list() != null) {
					 
					  LoadApp.getEdata_stock().setEdata_id(LoadApp.getEdata().getData_id());
						  
						  System.err.println("SaveApp -> ");
						 if(SaveAppUnity_Components.VerifyStock(LoadApp)) {
							 
							  EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
							 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
						 }else {
							 LoadApp.getEdata_stock().setStock_list("Rename ->"+LoadApp.getEdata_stock().getStock_list());
							  EdataDAO.SaveStock(LoadApp.getEdata_stock(),LoadApp);
							 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
		
						 }
						
						 
						
	
					    
					      Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					      
				
				 }
			
			System.out.println("<------------End Stock Warehouse--------> ");
			      
		}


		public void ApproveData(LoadApp LoadApp) {
			ApproveApp_Components.runMethod(LoadApp.getOther(),()-> EdataDAO.APPOther(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getGoods_receipt_sk(),()-> EdataDAO.APPGoods_receipt_sk(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getInventory_transfer_sk(),()-> EdataDAO.APPInventory_transfer_sk(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getIssue_sk(),()-> EdataDAO.APPIssue_sk(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getAdjustment_sk(),()-> EdataDAO.APPAdjustment_sk(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getGoods_receipt_fg(),()-> EdataDAO.APPGoods_receipt_fg(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getInventory_transfer_fg(),()-> EdataDAO.APPInventory_transfer_fg(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getIssue_fg(),()-> EdataDAO.APPIssue_fg(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getAdjustment_fg(),()-> EdataDAO.APPAdjustment_fg(LoadApp.getEdata()) );
		}


		
		
	
		
		

	  
}
