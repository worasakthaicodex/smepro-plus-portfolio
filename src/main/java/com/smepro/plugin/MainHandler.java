package com.smepro.plugin;

import java.util.List;

import com.smepro.dao.RecordApp_Component;
import com.smepro.enums.AppAbility;
import com.smepro.models.LoadApp;

public class MainHandler implements AbilityHandler {

	  @Override
	    public List<String> supportedAbilities() {
	        return List.of(
	        		
	            AppAbility.register_stock.toString(),
	            AppAbility.register_list.toString(),
	            AppAbility.register_product.toString(),
	            AppAbility.register_assets.toString(),
	            AppAbility.register_annual_plan.toString(),
	            AppAbility.register_workrule.toString(),
	            AppAbility.update_register_stock.toString(),
	            AppAbility.update_register_list.toString(),
	            AppAbility.update_register_product.toString(),
	            AppAbility.update_register_annual_plan.toString(),
	            AppAbility.update_register_annual_plan.toString(),
	            AppAbility.update_register_workrule.toString(),
	            AppAbility.set_poin.toString(),
	            AppAbility.set_income_stock.toString(),
	            AppAbility.issued_fg.toString(),
	            AppAbility.adjustment_fg.toString(),
	            AppAbility.goods_receipt_fg.toString(),
	            AppAbility.inventory_transfer_fg.toString(),
	            AppAbility.issued_sk.toString(),
	            AppAbility.adjustment_sk.toString(),
	            AppAbility.goods_receipt_sk.toString(),
	            AppAbility.inventory_transfer_sk.toString()
	        );
	    }

	    @Override
	    public void handle(LoadApp loadApp,RecordApp_Component RecordApp_Components) {
	    	
	    	 System.out.println(" ---------> handle ");
	    	
	        for (String ability : loadApp.getAbility()) {
	        	
	       	 System.out.println(" handle ability "+ability);
	        	
	            switch (ability) {
	                case "register_stock" -> RecordApp_Components.Register_stock(loadApp);
	                case "register_list" -> RecordApp_Components.Register_list(loadApp);
	                case "register_product" -> RecordApp_Components.Register_product(loadApp);
	                case "register_assets" -> RecordApp_Components.Register_assets(loadApp);
	                case "register_annual_plan" -> RecordApp_Components.Register_annual_plan(loadApp);
	                case "register_workrule" -> RecordApp_Components.Register_workrule(loadApp);
	                case "update_register_stock" -> RecordApp_Components.UpdateRegister_stock(loadApp);
	                case "update_register_list" -> RecordApp_Components.UpdateRegister_list(loadApp);
	                case "update_register_product" -> RecordApp_Components.UpdateRegister_product(loadApp);
	                case "update_register_assets" -> RecordApp_Components.UpdateRegister_assets(loadApp);
	                case "update_register_annual_plan" -> RecordApp_Components.UpdateRegister_annual_plan(loadApp);
	                case "update_register_workrule" -> RecordApp_Components.UpdateRegister_workrule(loadApp);
	                case "set_poin" -> RecordApp_Components.Set_poin(loadApp);
	                case "set_income_stock" -> RecordApp_Components.Income_stock(loadApp);
	                case "issued_fg" -> RecordApp_Components.Issued_fg(loadApp);
	                case "adjustment_fg" -> RecordApp_Components.Adjustment_fg(loadApp);
	                case "goods_receipt_fg" -> RecordApp_Components.Goods_receipt_fg(loadApp);
	                case "inventory_transfer_fg" -> RecordApp_Components.Inventory_transfer_fg(loadApp);
	                case "issued_sk" -> RecordApp_Components.Issued_sk(loadApp);
	                case "adjustment_sk" -> RecordApp_Components.Adjustment_sk(loadApp);
	                case "goods_receipt_sk" -> RecordApp_Components.Goods_receipt_sk(loadApp);
	                case "inventory_transfer_sk" -> RecordApp_Components.Inventory_transfer_sk(loadApp);
	            }
	        }
	    }
	}
	
