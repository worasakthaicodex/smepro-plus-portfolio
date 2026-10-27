package com.smepro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.smepro.dao.Finance_Accounting;
import com.smepro.dao.HR_Workforce;
import com.smepro.dao.Machinery_Factory;
import com.smepro.dao.Procurement_SupplyChain;
import com.smepro.dao.Production;
import com.smepro.dao.Sales_Customer;
import com.smepro.dao.Stock_Warehouse;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.keymap;
import com.smepro.models.registerkey;


@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class AppComponent {

	
	private LogService logService;
	
	  @Autowired
		public void setMyRepository(
		    		LogService logService
		    		) {
		  			this.logService = logService;
		  
	  		}
	
	
	
	public String getAppModel(registerkey registerkey, keymap keymap) {
		 
		if(registerkey == null ) { return keymap.getMainkey(); }
		 String lable =registerkey.getAction_type()+"_"+registerkey.getDoc();	 
		 System.out.println(" AppModel "+lable);
		 String appmodel = "";
				 
				 
		  switch (lable) {
		            case "register_Profile" -> appmodel = "edata_registers.";
		            case "register_stock" -> appmodel = "edata_stocks.";
		            case "register_Product" -> appmodel = "edata_products.";       
		            case "action_production_record" -> appmodel = "edata_production_records.";
		            case "action_PL" -> appmodel = "production_lists.";
		            case "action_PD" -> appmodel = "production_directives.";     
		            case "action_OTHER" -> appmodel = "others.";
		            case "action_QCI" -> appmodel = "qcis.";
		            case "document_reference_BOM" -> appmodel = "boms.";
		            case "document_reference_PS" -> appmodel = "production_standards.";
		            case "clear_register_Goods_receipt_fg" -> appmodel = "Goods_receipt_fgs.";
		            case "clear_register_Issue_fg" -> appmodel = "Issue_fgs.";
		            case "clear_register_Inventory_transfer_fg" -> appmodel = "Inventory_transfer_fgs.";
		            case "clear_register_Adjustment_fg" -> appmodel = "Adjustment_fgs.";
		            case "clear_register_Goods_receipt_sk" -> appmodel = "Goods_receipt_sks.";
		            case "clear_register_Inventory_transfer_sk" -> appmodel = "Inventory_transfer_sks.";
		            case "clear_register_Adjustment_sk" -> appmodel = "Adjustment_sks.";
		            case "action_Sales followup" -> appmodel = "sales_followups.";
		            default-> logService.notice(404,"App Model not found","App_Component/getAppModel");
		  }
		        
			return appmodel;
		}

	

}
