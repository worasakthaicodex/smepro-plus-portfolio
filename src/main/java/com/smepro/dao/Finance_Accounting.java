package com.smepro.dao;

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

import com.smepro.entity.account_ledger;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.fa_invoices;
import com.smepro.entity.fa_payments;
import com.smepro.entity.hr_action;
import com.smepro.entity.machine_activity;
import com.smepro.entity.maintenance_requests;
import com.smepro.entity.sales_followup;
import com.smepro.enums.AppAbility;
import com.smepro.models.LoadApp;
import com.smepro.models.customreport;
import com.smepro.models.edata_register;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.sub_processes;
import com.smepro.repository.Account_ledgerRepository;
import com.smepro.repository.Edata_annual_planRepository;
import com.smepro.repository.Edata_assetsRepository;
import com.smepro.repository.Fa_invoicesRepository;
import com.smepro.repository.Fa_paymentsRepository;
import com.smepro.repository.Machine_activityRepository;
import com.smepro.repository.Maintenance_requestsRepository;
import com.smepro.service.CustomReport;
import com.smepro.service.otherservice;



@Component
public class Finance_Accounting {

	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}
	
	
	
	private Account_ledgerRepository Account_ledger;
	private Fa_paymentsRepository Fa_payments;
	private Fa_invoicesRepository Fa_invoices;
	private Edata_assetsRepository Edata_assets;
	private Edata_annual_planRepository Edata_annual_plan;
	private edataDAO EdataDAO;
	private otherservice otherservices;
	private  SaveAppUnity_Component SaveAppUnity_Components;
	private  MessageSource messageSource;
	private  ApproveApp_Component ApproveApp_Components;
	
	  @Autowired
		public void setMyRepository(
				Account_ledgerRepository Account_ledger,
				Fa_paymentsRepository Fa_payments,
				Fa_invoicesRepository Fa_invoices,
				Edata_assetsRepository Edata_assets,
				Edata_annual_planRepository Edata_annual_plan,
				edataDAO EdataDAO,
				SaveAppUnity_Component SaveAppUnity_Components,
				MessageSource messageSource,
				otherservice otherservices,
				ApproveApp_Component ApproveApp_Components
				) {
		  
		  this.Account_ledger = Account_ledger;
		  this.Fa_payments = Fa_payments;
		  this.Fa_invoices = Fa_invoices;
		  this.Edata_assets = Edata_assets;
		  this.Edata_annual_plan = Edata_annual_plan;
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
			case "account_ledgers." -> transactionexposeAs(Account_ledger.findByEdataId(eformId),"account_ledger",modelData);
			case "fa_payments." -> transactionexposeAs(Fa_payments.findByEdataId(eformId),"fa_payment",modelData);
			case "fa_invoices." -> transactionexposeAs(Fa_invoices.findByEdataId(eformId),"fa_invoice",modelData);
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
		
		
		int eformId = LoadApp.getEdata().getData_id();
	  	switch (LoadApp.getApp_model()) {
			case "edata_assets." -> transactionexposeAs( Edata_assets.findByEdataId(eformId),"edata_asset",modelData);
			case "edata_annual_plans." -> transactionexposeAs(Edata_annual_plan.findByEdataId(eformId),"edata_annual_plan",modelData);
	  	}	

	}


	private void Register(LoadApp LoadApp, Map<String, Object> modelData) {
		
		
		int eformId = LoadApp.getEdata().getData_id();
	  	switch (LoadApp.getApp_model()) {
		case "edata_assets." -> transactionexposeAs(Edata_assets.findByEdataId(eformId),"edata_asset",modelData);
		case "edata_annual_plans." -> transactionexposeAs( Edata_annual_plan.findByEdataId(eformId),"edata_annual_plan",modelData);
	  	}	
		
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

		  if (string.equals("account_ledger")) {
			  Account_ledger(LoadApp);
		  }
		  else  if (string.equals("fa_payments")) {
			  Fa_payments(LoadApp);
		  }
		  else  if (string.equals("fa_invoices")) {
			  Fa_invoices(LoadApp);
		  }
		  else  if (string.equals("edata_assets")) {
			  Edata_assets(LoadApp);
		  }
		  else  if (string.equals("edata_annual_plan")) {
			  Edata_annual_plan(LoadApp);
		  }
		  else  if (string.equals("other")) {
			  Other(LoadApp);
		  }
	
	}
	
	
private <T> void Fa_payments(LoadApp LoadApp) {
		
		HttpSession session = LoadApp.getSession();
		
		System.out.println("-----------------getId_refer_by Sales_followup");
		  
		  List<T> Actionbatch  = new ArrayList<T>();
		  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<fa_payments> SubDataDTOS = Fa_payments.findAllByTempId(LoadApp.getTempId());
		
		  

		 Optional<fa_payments>  option1  = SubDataDTOS.stream().filter(
				 row->otherservices.DoubleValueOf(row.getFp_amount_paid()) > 0.0
				 && otherservices.DoubleValueOf(row.getFp_amount_received()) > 0.0
				 ).findFirst();
		  
		  if(option1.isPresent()) {

			  Double remaining = 0.0;
			  Double totalpaid = 0.0;
			  Double totalreceived = 0.0;
			  
			  for (fa_payments row : SubDataDTOS) {
				  Double paid = otherservices.DoubleValueOf(row.getFp_amount_paid());
				  Double received = otherservices.DoubleValueOf(row.getFp_amount_received());

				  remaining = remaining +received;
				  remaining = remaining -paid;


			      totalpaid += paid;
			      totalreceived += received;
			      
			      row.setFp_remaining(String.format("%.2f",remaining));

			      Actionbatch.add((T) row);
			  }

	
			  LoadApp.getModel().addAttribute("totalpaid",totalpaid);	 
			  LoadApp.getModel().addAttribute("totalreceived",totalreceived);	 
			  LoadApp.getModel().addAttribute("remaining",remaining);	 
			  session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			   LoadApp.getModel().addAttribute("item",Actionbatch);	
			   
			   return;
		  }

			
		  Optional<fa_payments>  option2  = SubDataDTOS.stream().filter(
				  row->otherservices.DoubleValueOf(row.getFp_amount_net()) > 0.0
			
				  ).findFirst();
		  if(option2.isPresent()) {
			  
			  Double totals = 0.0;
			  for (fa_payments row : SubDataDTOS) {
		
				  Double net = otherservices.DoubleValueOf(row.getFp_amount_net());
				  totals += net;
			      Actionbatch.add((T) row);
			  }
			  
			  LoadApp.getModel().addAttribute("grandtotal",String.format("%.2f",totals));	 
			  session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			  LoadApp.getModel().addAttribute("item",Actionbatch);	
			   return;
			  
		  }   
		
	}
	
	
	
private <T> void Fa_invoices(LoadApp LoadApp) {
		
		HttpSession session = LoadApp.getSession();
		
		System.out.println("-----------------getId_refer_by Sales_followup");
		  
		  List<T> Actionbatch  = new ArrayList<T>();
		  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<fa_invoices> SubDataDTOS = Fa_invoices.findAllByTempId(LoadApp.getTempId());
		
		  
			 Optional<fa_invoices>  option1  = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getFi_price()) > 0.0).findFirst();
			  
			  if(option1.isPresent()) {

				  Double discounts = 0.0;
				  Double totals = 0.0;
				  Optional<fa_invoices> vat = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getFi_price()) > 0.0).findFirst();
				  
	
				  for (fa_invoices row : SubDataDTOS) {
					  Double price = otherservices.DoubleValueOf(row.getFi_price());
					  Double count = otherservices.DoubleValueOf(row.getFi_quantity());
					  Double discount = otherservices.DoubleValueOf(row.getFi_discount());

				      discounts += discount;

				      Double lineTotal = price * count;
				      totals += lineTotal;

				      row.setFi_amount_total(String.format("%.2f", lineTotal - discount));

				      Actionbatch.add((T) row);
				  }

				  // STEP 1: grand total
				   Double adjustedtotal = totals - discounts;

			
				  // STEP 3: VAT & net amount
				   
				   Double vatRate = 0.0;
				   Double netAmount = adjustedtotal;
				   
				   if(vat.isPresent()) {
					   fa_invoices VatRates = vat.get();
					    vatRate = otherservices.DoubleValueOf(VatRates.getFi_amount_vat()); // เช่น 7.0
					   Double vatAmount = adjustedtotal * (vatRate / 100);
					   netAmount = adjustedtotal + vatAmount;
				   }
				 
				  
				  
				  
				  
				  LoadApp.getModel().addAttribute("grandtotal",totals);	 
				  LoadApp.getModel().addAttribute("vat",vatRate);	 
				  LoadApp.getModel().addAttribute("adjustedtotal",adjustedtotal);	 
				  LoadApp.getModel().addAttribute("net_amount",netAmount);	 
				  session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
				   LoadApp.getModel().addAttribute("item",Actionbatch);	
				   
				   return;
			  }


			
	}
	



private <T> void Account_ledger(LoadApp LoadApp) {
		
		HttpSession session = LoadApp.getSession();
		
		System.out.println("-----------------getId_refer_by Account_ledger");
		  
		  List<T> Actionbatch  = new ArrayList<T>();
		  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<account_ledger> SubDataDTOS = Account_ledger.findAllByTempId(LoadApp.getTempId());
		

			
		  Optional<account_ledger>  option  = SubDataDTOS.stream()
				  .filter(row->otherservices.DoubleValueOf(row.getAl_debit()) > 0.0  || otherservices.DoubleValueOf(row.getAl_credit()) > 0.0 )
				  .findFirst();
		  
		  if(option.isPresent()) {
			  
			  Double totaldebits = 0.0;
			  Double totalcredits = 0.0;
			  
			  
			  for (account_ledger row : SubDataDTOS) {
				  totaldebits +=  otherservices.DoubleValueOf(row.getAl_debit());
				  totalcredits +=  otherservices.DoubleValueOf(row.getAl_credit());
			      Actionbatch.add((T) row);
			  }
			  
			  
			  
			  LoadApp.getModel().addAttribute("debits",String.format("%.2f",totaldebits));	 
			  LoadApp.getModel().addAttribute("credits",String.format("%.2f",totalcredits));	 
			  session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			  LoadApp.getModel().addAttribute("item",Actionbatch);	
			   return;
			  
		  }
		   
		
	}
	
	
	  

	private<T> void Edata_assets(LoadApp LoadApp) {
		HttpSession session = LoadApp.getSession();
		  List<T> Actionbatch  = new ArrayList<T>();
		  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<edata_assets> SubDataDTOS = Edata_assets.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			 LoadApp.getModel().addAttribute("item",Actionbatch);	
		    
	}
	private<T> void Edata_annual_plan(LoadApp LoadApp) {
		HttpSession session = LoadApp.getSession();
		  List<T> Actionbatch  = new ArrayList<T>();
		  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<edata_annual_plan> SubDataDTOS = Edata_annual_plan.findAllByTempId(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			 LoadApp.getModel().addAttribute("item",Actionbatch);	
		    
	}
  
	
	  
	private<T> void Other(LoadApp LoadApp) {
		HttpSession session = LoadApp.getSession();
		  List<T> Actionbatch  = new ArrayList<T>();
		  session.removeAttribute("SubData"+LoadApp.getTempId());
		  
		  List<other> SubDataDTOS = EdataDAO.getAllEdata_other_item(LoadApp.getTempId());
		  Actionbatch.addAll((List<T>) SubDataDTOS); 
		  
			 System.out.println(" item --->"+Actionbatch.size());
			 
			session.setAttribute("SubData"+LoadApp.getTempId(),Actionbatch);
			 LoadApp.getModel().addAttribute("item",Actionbatch);	
		    
	}


	
	public void Batch_inserthasChanges(LoadApp LoadApp) {
		 Batch_process(LoadApp,LoadApp.getType());
	}


	
	private <T extends Identifiable> void Batch_process(LoadApp LoadApp, String tag) {
		
		
		
		HttpSession session = LoadApp.getSession();
		 T objects = null;
		 String object_type = "";
		 String tempId = "";
		 double total =0.0;
		 Double Doubletotal =0.0;
		 String formattedTotal ="0.0";	
		 int  item_ids = 0;
		 List<T> Actionbatch;	
		 
		 
		 
		  if (tag.equals("account_ledger")) {
			  
			  if(LoadApp.getAccount_ledger().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getAccount_ledger().setId(randomNegative);
				}    
			  
  
				objects =  (T) LoadApp.getAccount_ledger();
				tempId = LoadApp.getAccount_ledger().getTempId();

				account_ledger  item_id = (account_ledger) objects;
		        item_ids = item_id.getId();
		  }
		  else  if (tag.equals("fa_payments")) {
			  
			  if(LoadApp.getFa_payments().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getFa_payments().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getFa_payments();
				tempId = LoadApp.getFa_payments().getTempId();

				fa_payments  item_id = (fa_payments) objects;
		        item_ids = item_id.getId();
		  }
		  else  if (tag.equals("fa_invoices")) {
			  
			  if(LoadApp.getFa_invoices().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getFa_invoices().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getFa_invoices();
				tempId = LoadApp.getFa_invoices().getTempId();

				fa_invoices  item_id = (fa_invoices) objects;
		        item_ids = item_id.getId();
		  }
		  else  if (tag.equals("edata_assets")) {
			  
			  if(LoadApp.getEdata_assets().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getEdata_assets().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getEdata_assets();
				tempId = LoadApp.getEdata_assets().getTempId();

				edata_assets  item_id = (edata_assets) objects;
		        item_ids = item_id.getId();
		  }
		  else  if (tag.equals("edata_annual_plan")) {
			  
			  if(LoadApp.getEdata_annual_plan().getId() == 0 ) {
					Random random = new Random();
			        int randomNegative = -random.nextInt(100);
					LoadApp.getEdata_annual_plan().setId(randomNegative);
				}    
			  
			  
			  
				objects =  (T) LoadApp.getEdata_annual_plan();
				tempId = LoadApp.getEdata_annual_plan().getTempId();

				edata_annual_plan  item_id = (edata_annual_plan) objects;
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
          
          session.setAttribute("SubData"+tempId,Actionbatch);
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
		  		  
			  		if (objects instanceof account_ledger) {
			  			account_ledger(LoadApp,actionbatch);
			  		} 
			  		else if (objects instanceof fa_payments) {
			  			fa_payments(LoadApp,actionbatch);
			  		} 
			  		else if (objects instanceof fa_invoices) {
			  			fa_invoices(LoadApp,actionbatch);
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
	
	
	private <T> void account_ledger(LoadApp LoadApp, List<T> Actionbatch) {
		

		 List<account_ledger> SubDataDTOS = (List<account_ledger>) Actionbatch;
		 
		  Optional<account_ledger>  option  = SubDataDTOS.stream()
				  .filter(row->otherservices.DoubleValueOf(row.getAl_debit()) > 0.0  || otherservices.DoubleValueOf(row.getAl_credit()) > 0.0 )
				  .findFirst();
		  
			  if(option.isPresent()) {
				  
				  Double totaldebits = 0.0;
				  Double totalcredits = 0.0;
				  
				  
				  for (account_ledger row : SubDataDTOS) {
					  totaldebits +=  otherservices.DoubleValueOf(row.getAl_debit());
					  totalcredits +=  otherservices.DoubleValueOf(row.getAl_credit());
				      Actionbatch.add((T) row);
				  }
				  
				  
				  
				  LoadApp.getModel().addAttribute("debits",String.format("%.2f",totaldebits));	 
				  LoadApp.getModel().addAttribute("credits",String.format("%.2f",totalcredits));	 

		}
	}  
		  
	private <T> void fa_payments(LoadApp LoadApp, List<T> Actionbatch) {
		 List<fa_payments> SubDataDTOS = (List<fa_payments>) Actionbatch;
		 Optional<fa_payments>  option1  = SubDataDTOS.stream().filter(
				 row->otherservices.DoubleValueOf(row.getFp_amount_paid()) > 0.0
				 && otherservices.DoubleValueOf(row.getFp_amount_received()) > 0.0
				 ).findFirst();
		  
		  if(option1.isPresent()) {

			  Double remaining = 0.0;
			  Double totalpaid = 0.0;
			  Double totalreceived = 0.0;
			  
			  for (fa_payments row : SubDataDTOS) {
				  Double paid = otherservices.DoubleValueOf(row.getFp_amount_paid());
				  Double received = otherservices.DoubleValueOf(row.getFp_amount_received());

				  remaining = remaining +received;
				  remaining = remaining -paid;


			      totalpaid += paid;
			      totalreceived += received;
			      
			      row.setFp_remaining(String.format("%.2f",remaining));

			      Actionbatch.add((T) row);
			  }

	
			  LoadApp.getModel().addAttribute("totalpaid",totalpaid);	 
			  LoadApp.getModel().addAttribute("totalreceived",totalreceived);	 
			  LoadApp.getModel().addAttribute("remaining",remaining);	 
			   
			   return;
		  }

			
		  Optional<fa_payments>  option2  = SubDataDTOS.stream().filter(
				  row->otherservices.DoubleValueOf(row.getFp_amount_net()) > 0.0
			
				  ).findFirst();
		  if(option2.isPresent()) {
			  
			  Double totals = 0.0;
			  for (fa_payments row : SubDataDTOS) {
		
				  Double net = otherservices.DoubleValueOf(row.getFp_amount_net());
				  totals += net;
			      Actionbatch.add((T) row);
			  }
			  
			  LoadApp.getModel().addAttribute("grandtotal",String.format("%.2f",totals));	 	
			   return;
			  
		  }   
	}
	
	
	
	private <T> void fa_invoices(LoadApp LoadApp, List<T> Actionbatch) {
		
		 List<fa_invoices> SubDataDTOS = (List<fa_invoices>) Actionbatch;
		 Optional<fa_invoices>  option1  = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getFi_price()) > 0.0).findFirst();
		  
		  if(option1.isPresent()) {

			  Double discounts = 0.0;
			  Double totals = 0.0;
			  Optional<fa_invoices> vat = SubDataDTOS.stream().filter(row->otherservices.DoubleValueOf(row.getFi_price()) > 0.0).findFirst();
			  

			  for (fa_invoices row : SubDataDTOS) {
				  Double price = otherservices.DoubleValueOf(row.getFi_price());
				  Double count = otherservices.DoubleValueOf(row.getFi_quantity());
				  Double discount = otherservices.DoubleValueOf(row.getFi_discount());

			      discounts += discount;

			      Double lineTotal = price * count;
			      totals += lineTotal;

			      row.setFi_amount_total(String.format("%.2f", lineTotal - discount));

			      Actionbatch.add((T) row);
			  }

			  // STEP 1: grand total
			   Double adjustedtotal = totals - discounts;

		
			  // STEP 3: VAT & net amount
			   
			   Double vatRate = 0.0;
			   Double netAmount = adjustedtotal;
			   
			   if(vat.isPresent()) {
				   fa_invoices VatRates = vat.get();
				    vatRate = otherservices.DoubleValueOf(VatRates.getFi_amount_vat()); // เช่น 7.0
				   Double vatAmount = adjustedtotal * (vatRate / 100);
				   netAmount = adjustedtotal + vatAmount;
			   }
			 
			  
			  
			  
			  
			  LoadApp.getModel().addAttribute("grandtotal",totals);	 
			  LoadApp.getModel().addAttribute("vat",vatRate);	 
			  LoadApp.getModel().addAttribute("adjustedtotal",adjustedtotal);	 
			  LoadApp.getModel().addAttribute("net_amount",netAmount);	 
			
			   
			   return;
		  }
	}


	public  void getAbility(LoadApp LoadApp, List<keymap> keymap_alls) {
		
		List<String> mainkeys;
		boolean exists;
		
		mainkeys = keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("asset_proceedings", "asset_name"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.update_register_assets.toString());
			return;
		}
		

		mainkeys = keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("asset_name", "asset_group"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.register_assets.toString());
			return;
		}
		
		mainkeys = keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("annual_plan_proceedings", "annual_plan_code"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.update_register_annual_plan.toString());
			return;
		}
		

		mainkeys = keymap_alls.stream().map(keymap::getMainkey).collect(Collectors.toList());
		exists = mainkeys.containsAll(Arrays.asList("annual_plan_code", "annual_year"));	
	
		if(exists) {
			LoadApp.getAbility().add(AppAbility.register_annual_plan.toString());
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
		    String  sessionname=   type.equals("Clear") ? "SubDataclear": "SubData";
			
			
			HttpSession session = LoadApp.getSession();
			List<?>  SubDataDTO= (List<?>) session.getAttribute(sessionname+LoadApp.getTempId());
			if (SubDataDTO == null) { return; }

			 Object firstItem = SubDataDTO.get(0);
			try {	 
				
				String tempId =  String.valueOf(LoadApp.getEdata().getData_id());
				  if (!SubDataDTO.isEmpty()) {		
						
	
			    	     if (firstItem instanceof account_ledger) {     	
			    	    	  	 System.out.println(" SaveBatchCreate*******> account_ledger");   	        
				   	        	 
				   	             List<account_ledger> account_ledger = (List<account_ledger>) SubDataDTO;
				   	             account_ledger.forEach(row -> row.setTempId(tempId)); 
				   	             Account_ledger.saveAll(account_ledger);
				   
					       	    Save_State =  State.SUCCESS;  // ✅ บันทึก
			    	        }  

			    	     else if (firstItem instanceof fa_payments) {     	
			    	    	  	 System.out.println(" SaveBatchCreate*******> fa_payments");   	        
				   	        	 
				   	             List<fa_payments> fa_payments = (List<fa_payments>) SubDataDTO;
				   	             fa_payments.forEach(row -> row.setTempId(tempId)); 
				   	             Fa_payments.saveAll(fa_payments);
				   
					       	    Save_State =  State.SUCCESS;  // ✅ บันทึก
			    	        }  
						 
			    	     else if (firstItem instanceof fa_invoices) {     	
		    	    	  	 System.out.println(" SaveBatchCreate*******> hr_action");   	        
			   	        	 
			   	             List<fa_invoices> fa_invoices = (List<fa_invoices>) SubDataDTO;
			   	            fa_invoices.forEach(row -> row.setTempId(tempId)); 
			   	            Fa_invoices.saveAll(fa_invoices);
			   
				       	    Save_State =  State.SUCCESS;  // ✅ บันทึก
		    	        }  
						 
				  }else {
					  Account_ledger.deleteAllByTempId(tempId);
					  Fa_payments.deleteAllByTempId(tempId);
					  Fa_invoices.deleteAllByTempId(tempId);
				   }
	       	 
			 }catch(Exception e) {
				 Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					
			}
			
			
			   session.removeAttribute(sessionname+LoadApp.getTempId());
			
			   
		}
		
		
		private void Save_Action(LoadApp LoadApp, State Save_State) {
		
			  if(LoadApp.getAccount_ledger() != null) {		 
				  LoadApp.getAccount_ledger().setEdataId(LoadApp.getEdata().getData_id());
				  Account_ledger.save(LoadApp.getAccount_ledger());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	  
	
		    	  
		    	  return;
			 }	
			  if(LoadApp.getFa_payments() != null) {		 
				  LoadApp.getFa_payments().setEdataId(LoadApp.getEdata().getData_id());
				  Fa_payments.save(LoadApp.getFa_payments());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	  return;
			 }	
			  if(LoadApp.getFa_invoices() != null) {		 
				  LoadApp.getFa_invoices().setEdataId(LoadApp.getEdata().getData_id());
				  Fa_invoices.save(LoadApp.getFa_invoices());
		    	  Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
		    	  
		    	
		    	  
		    	  return;
			 }	
			
			
		}
		
		
		private void Save_Register_Processing(LoadApp LoadApp, State Save_State) {
	Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
			
			if(LoadApp.getEdata_assets() != null) {
				  if(LoadApp.getEdata_assets().getAsset_proceedings() == null) {return; }	  
					
					
				  LoadApp.getEdata_assets().setEdataId(LoadApp.getEdata().getData_id());
				  
					 if(SaveAppUnity_Components.HaveAsset(LoadApp)) {
						  Edata_assets.save(LoadApp.getEdata_assets());
						 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						 Edata_assets.save(LoadApp.getEdata_assets());
						Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					 }
				 	 String title = messageSource.getMessage("title.registration_request", null, locale);
				      Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
			}
			else 	if(LoadApp.getEdata_annual_plan() != null) {
				  if(LoadApp.getEdata_annual_plan().getAnnual_plan_proceedings() == null) {return; }	  
					
					
				  LoadApp.getEdata_annual_plan().setEdataId(LoadApp.getEdata().getData_id());
				  
					 if(SaveAppUnity_Components.HavAnnual_plan(LoadApp)) {
						 Edata_annual_plan.save(LoadApp.getEdata_annual_plan());
						 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						 Edata_annual_plan.save(LoadApp.getEdata_annual_plan());
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
			
			if(LoadApp.getEdata_assets() != null) {
				  LoadApp.getEdata_assets().setEdataId(LoadApp.getEdata().getData_id());
				  
					 if(SaveAppUnity_Components.VerifyAsset(LoadApp)) {
						 Edata_assets.save(LoadApp.getEdata_assets());
						 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						LoadApp.getEdata_assets().setAsset_name("Rename ->"+LoadApp.getEdata_assets().getAsset_name());
						Edata_assets.save(LoadApp.getEdata_assets());
						Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					 }
				 	
				       Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
				}
			
			if(LoadApp.getEdata_annual_plan() != null) {
				  LoadApp.getEdata_annual_plan().setEdataId(LoadApp.getEdata().getData_id());
				  
					 if(SaveAppUnity_Components.VerifyAnnual_plan(LoadApp)) {
						 Edata_annual_plan.save(LoadApp.getEdata_annual_plan());
						 Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
					 }else {
						LoadApp.getEdata_workrule().setRule_name("Rename ->"+LoadApp.getEdata_annual_plan().getTitle());
						Edata_annual_plan.save(LoadApp.getEdata_annual_plan());
						Save_State =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					 }
				 	
				     Save_State =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
				}
			
		}


		public void ApproveData(LoadApp LoadApp) {
			ApproveApp_Components.runMethod(LoadApp.getAccount_ledger(),()-> Account_ledger.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getFa_payments(),()-> Fa_payments.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getFa_invoices(),()-> Fa_invoices.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getEdata_assets(),()-> Edata_assets.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getEdata_annual_plan(),()-> Edata_annual_plan.updateDocByEdataId(LoadApp.getEdata()) );
			ApproveApp_Components.runMethod(LoadApp.getOther(),()-> EdataDAO.APPOther(LoadApp.getEdata()) );
			
		}

		
		

		
		
	

		

}	

