package com.smepro.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.smepro.dto.MultipleModelsDTO;
import com.smepro.enums.Actiontype;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Idoc;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Issue_fg;
import com.smepro.models.LoadApp;
import com.smepro.models.batch_code;
import com.smepro.models.batchformat;
import com.smepro.models.bom;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.keymap;
import com.smepro.models.other;
import com.smepro.models.process;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.qci;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.service.FlowServices;
import com.smepro.service.PlatformService;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class SaveAppUnity_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	private otherservice Otherservice;
	
	private Machinery_Factory Machinery_Factorys;
	private Sales_Customer Sales_Customers;
	private Finance_Accounting Finance_Accountings;
	private HR_Workforce HR_Workforces;
	private Procurement_SupplyChain Procurement_SupplyChains;
	private Production Productions;
	private Stock_Warehouse Stock_Warehouses;
	
	private SaveState State = SaveState.NONE;
	LoadApp LoadApp = new LoadApp();
	public enum SaveState {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}


	
	  @Autowired
	  private void setMyRepository(
	    		edataDAO EdataDAO,
	    		userDAO UserDAO,
	    		upload_pnDAO Upload_pnDAO,
	    		userservice Userservice,
	    		conditionDAO ConditionDAO,
	    		FlowServices flowServices,
	    		datasetDAO DatasetDAO,
	    		dataset_dataDAO Dataset_dataDAO,
	    		workflowDAO WorkflowDAO,
	    		otherservice Otherservice,
	    		Machinery_Factory Machinery_Factorys,
	    		Sales_Customer Sales_Customers,
	    		Finance_Accounting Finance_Accountings,
	    		HR_Workforce HR_Workforces,
	    		Procurement_SupplyChain Procurement_SupplyChains,
	    		Production Productions,
	    		Stock_Warehouse Stock_Warehouses
	    		) { 
	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;
	        this.Upload_pnDAO = Upload_pnDAO;
	        this.flowServices = flowServices;
	        this.Userservice = Userservice;
	        this.ConditionDAO = ConditionDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.Dataset_dataDAO = Dataset_dataDAO;
	        this.WorkflowDAO = WorkflowDAO;
	        this.Otherservice = Otherservice;
	        this.Machinery_Factorys = Machinery_Factorys;
	        this.Sales_Customers = Sales_Customers;
	        this.Finance_Accountings = Finance_Accountings;
	        this.HR_Workforces = HR_Workforces;
	        this.Procurement_SupplyChains = Procurement_SupplyChains;
	        this.Productions = Productions;
	        this.Stock_Warehouses = Stock_Warehouses;
	    }

	
	
	
	private int id;
	private String db;
	private String subdb;
	private ModelMap model;
	private String maindata;
	private String filtermain;
	private String subdata;
	private String filtersub;
	private String maindatacolumn;
	private String autoiddatacolumn;
	private String subdatacolumn;
	private List<keymap> keymaps;
	private  Idoc docs;
	private user users;
	private sub_processes subprocesses;



	



			
		
		public boolean VerifyImportantData(LoadApp loadApp) {
			
			// TODO Auto-generated method stub
			  System.err.println("Verify -> Create->VerifyImportantData");
			  
			switch (loadApp.getWorkflow().getWorkflow_maindb()) {
			

				case "Production":
		
		
					if(EdataDAO.CheckDuplicatebatch(loadApp.getProduction_lists()) > 0) { 
			
							loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Register_Duplicate.name());	
							loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_BATCH_Duplicate.getLabel());	
							return false; 
						}
					
					return true;  
					
		       default:
		      	    return false;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
			}
			
		}

		
	
		
		
		public int VerifyProduct(LoadApp loadApp) {
	
			if(loadApp.getEdata().getEdata_index_action_type() > 0) {
				
				//เช็คว่าต้องมีซ้ำถึงเเก้ได้
				if (EdataDAO.HaveProduct(loadApp.getEdata_product()) == 0) {
					return  2;
				}
				//เช็คว่าต้องไม่มีซ้ำกรณีเปลี่ยนชื่อปรือประเภทด้วย
				if (EdataDAO.CheckDuplicateProduct(loadApp.getEdata_product()) > 0) {
					return  1;
				}
				return  0;
			}else {
				
				//เช็คว่าต้องไม่มีซ้ำ
				if (EdataDAO.CheckDuplicateProduct(loadApp.getEdata_product()) > 0) {
						return  1;
				}
					return  0;
				}		
		}
		
		
		
		public boolean HaveRegister(LoadApp loadApp) {
			
	
				return true;
			}	
			
			
			
		public int VerifyRegister(LoadApp loadApp) {

			if(loadApp.getEdata().getEdata_index_action_type() > 0) {
				
				//เช็คว่าต้องมีซ้ำถึงเเก้ได้
				if (EdataDAO.HaveRegister(loadApp.getEdata_register()) == 0) {
					return  2;
				}
				//เช็คว่าต้องไม่มีซ้ำกรณีเปลี่ยนชื่อปรือประเภทด้วย
				if (EdataDAO.CheckDuplicateRegister(loadApp.getEdata_register()) > 0) {
					return  1;
				}
				return  0;
			}else {
				
				//เช็คว่าต้องไม่มีซ้ำ
				if (EdataDAO.CheckDuplicateRegister(loadApp.getEdata_register()) > 0) {
						return  1;
				}
					return  0;
				}		
		}
				
		
		public boolean HaveStock(LoadApp loadApp) {
			
			if(VerifyStock(loadApp)) { 
			//	loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Stock_Duplicate.name());	
			//	loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Stock_Duplicate.getLabel());	
				return false; 
			}
		
		return true;   
		}
		
		public boolean VerifyStock(LoadApp loadApp) {
			
			if(EdataDAO.CheckDuplicateStock(loadApp.getEdata_stock()) > 0) { 
			//	loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Stock_Duplicate.name());	
			//	loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Stock_Duplicate.getLabel());	
				return false; 
			}
		
			return true;   
		}
		
		
		
		
		public boolean HaveWorkrule(LoadApp loadApp) {
			
			if(VerifyWorkrule(loadApp)) { 
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Workrule.name());	
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Workrule.getLabel());	
				return false; 
			}
		
		return true;   
		}
		
		public boolean VerifyWorkrule(LoadApp loadApp) {
			
			if(EdataDAO.CheckDuplicateWorkrule(loadApp.getEdata_workrule()) > 0) { 
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Workrule_Duplicate.name());	
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Workrule_Duplicate.getLabel());	
				return false; 
			}
		
			return true;   
		}
		
		
		
		
		
		
		public boolean HavAnnual_plan(LoadApp loadApp) {
			
			if(VerifyAnnual_plan(loadApp)) { 
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Annual_plan.name());	
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Annual_plan.getLabel());	
				return false; 
			}
		
		return true;   
		}
		
		public boolean VerifyAnnual_plan(LoadApp loadApp) {
			
			if(EdataDAO.CheckDuplicateAnnual_plan(loadApp.getEdata_annual_plan()) > 0) { 
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Annual_plan_Duplicate.name());	
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Annual_plan_Duplicate.getLabel());	
				return false; 
			}
		
			return true;   
		}
		
		
		
		public boolean HaveAsset(LoadApp loadApp) {
			
			if(VerifyAsset(loadApp)) { 
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Assets.name());	
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Assets.getLabel());	
				return false; 
			}
		
		return true;   
		}
		
		public boolean VerifyAsset(LoadApp loadApp) {
			
			if(EdataDAO.CheckDuplicateAssets(loadApp.getEdata_assets()) > 0) { 
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error.name(),Save_status.Sys_Failed_Assets_Duplicate.name());	
				loadApp.getRedirectAttributes().addFlashAttribute(Sys_status.Sys_Error_TYPE.name(),Save_status.Sys_Failed_Assets_Duplicate.getLabel());	
				return false; 
			}
		
			return true;   
		}
		
	
		
		
		

		public String getState() {
			return State.toString();
		}


		public void setState(SaveState state) {
			State = state;
		}

	
}
