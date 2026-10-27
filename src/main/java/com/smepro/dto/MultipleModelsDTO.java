package com.smepro.dto;


import com.smepro.entity.account_ledger;
import com.smepro.entity.fa_payments;
import com.smepro.entity.hr_action;
import com.smepro.entity.fa_invoices;
import com.smepro.entity.employee_shift;
import com.smepro.entity.employee_discipline;
import com.smepro.entity.employee_compensation;
import com.smepro.entity.edata_workrule;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.machine_activity;
import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.sales_followup;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.bom;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_production_record;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.edata_stock;
import com.smepro.models.other;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.qci;
import com.smepro.entity.request_for_quotation;
import com.smepro.entity.maintenance_requests;

public class MultipleModelsDTO {
	

	private account_ledger account_ledgers;
	private fa_payments fa_payments;
	private fa_invoices fa_invoices;
	private edata_assets edata_assets;
	private edata_annual_plan edata_annual_plans;
	private edata_workrule edata_workrules;
	private hr_action hr_actions;
	private ps_contract ps_contracts;
	private purchase_order_tracking purchase_order_trackings;
	private request_for_quotation request_for_quotations;
	private sales_followup sales_followups;
	private machine_activity machine_activitys;
	private maintenance_requests maintenance_requests;
	private edata_register_processing edata_register_processings; 
	private edata_stock edata_stocks; 
	private edata_register edata_registers; 
	private edata_procurement edata_procurements; 
	private edata_product edata_products; 
	private edata_production_record edata_production_records; 
	private production_directive production_directives; 
	private production_list production_lists; 
	private qci qcis;
	private production_standard production_standards; 
	private bom boms; 
	private other others; 
	private Goods_receipt_fg Goods_receipt_fgs; 
	private Adjustment_fg Adjustment_fgs; 
	private Inventory_transfer_fg Inventory_transfer_fgs;
	private Issue_fg Issue_fgs; 
	private Goods_receipt_sk Goods_receipt_sks;
	private Adjustment_sk Adjustment_sks; 
	private Inventory_transfer_sk Inventory_transfer_sks;
	private Issue_sk Issue_sks;   
	
	


	public fa_payments getFa_payments() {
		return fa_payments;
	}
	public void setFa_payments(fa_payments fa_payments) {
		this.fa_payments = fa_payments;
	}
	public fa_invoices getFa_invoices() {
		return fa_invoices;
	}
	public void setFa_invoices(fa_invoices fa_invoices) {
		this.fa_invoices = fa_invoices;
	}
	public edata_assets getEdata_assets() {
		return edata_assets;
	}
	public void setEdata_assets(edata_assets edata_assets) {
		this.edata_assets = edata_assets;
	}
	public maintenance_requests getMaintenance_requests() {
		return maintenance_requests;
	}
	public void setMaintenance_requests(maintenance_requests maintenance_requests) {
		this.maintenance_requests = maintenance_requests;
	}
	public edata_register_processing getEdata_register_processings() {
		return edata_register_processings;
	}
	public void setEdata_register_processings(edata_register_processing edata_register_processings) {
		this.edata_register_processings = edata_register_processings;
	}
	public edata_stock getEdata_stocks() {
		return edata_stocks;
	}
	public void setEdata_stocks(edata_stock edata_stocks) {
		this.edata_stocks = edata_stocks;
	}
	public edata_register getEdata_registers() {
		return edata_registers;
	}
	
	
	
	public void setEdata_registers(edata_register edata_registers) {
		this.edata_registers = edata_registers;
	}
	public edata_procurement getEdata_procurements() {
		return edata_procurements;
	}
	public void setEdata_procurements(edata_procurement edata_procurements) {
		this.edata_procurements = edata_procurements;
	}
	public edata_product getEdata_products() {
		return edata_products;
	}
	public void setEdata_products(edata_product edata_products) {
		this.edata_products = edata_products;
	}
	public edata_production_record getEdata_production_records() {
		return edata_production_records;
	}
	public void setEdata_production_records(edata_production_record edata_production_records) {
		this.edata_production_records = edata_production_records;
	}
	public production_directive getProduction_directives() {
		return production_directives;
	}
	public void setProduction_directives(production_directive production_directives) {
		this.production_directives = production_directives;
	}
	public production_list getProduction_lists() {
		return production_lists;
	}
	public void setProduction_lists(production_list production_lists) {
		this.production_lists = production_lists;
	}

	public production_standard getProduction_standards() {
		return production_standards;
	}
	public void setProduction_standards(production_standard production_standards) {
		this.production_standards = production_standards;
	}
	public bom getBoms() {
		return boms;
	}
	public void setBoms(bom boms) {
		this.boms = boms;
	}
	public other getOthers() {
		return others;
	}
	public void setOthers(other others) {
		this.others = others;
	}
	public account_ledger getAccount_ledgers() {
		return account_ledgers;
	}
	public void setAccount_ledgers(account_ledger account_ledgers) {
		this.account_ledgers = account_ledgers;
	}
	public edata_annual_plan getEdata_annual_plans() {
		return edata_annual_plans;
	}
	public void setEdata_annual_plans(edata_annual_plan edata_annual_plans) {
		this.edata_annual_plans = edata_annual_plans;
	}
	public edata_workrule getEdata_workrules() {
		return edata_workrules;
	}
	public void setEdata_workrules(edata_workrule edata_workrules) {
		this.edata_workrules = edata_workrules;
	}
	public hr_action getHr_actions() {
		return hr_actions;
	}
	public void setHr_actions(hr_action hr_actions) {
		this.hr_actions = hr_actions;
	}
	public ps_contract getPs_contracts() {
		return ps_contracts;
	}
	public void setPs_contracts(ps_contract ps_contracts) {
		this.ps_contracts = ps_contracts;
	}
	public purchase_order_tracking getPurchase_order_trackings() {
		return purchase_order_trackings;
	}
	public void setPurchase_order_trackings(purchase_order_tracking purchase_order_trackings) {
		this.purchase_order_trackings = purchase_order_trackings;
	}
	public request_for_quotation getRequest_for_quotations() {
		return request_for_quotations;
	}
	public void setRequest_for_quotations(request_for_quotation request_for_quotations) {
		this.request_for_quotations = request_for_quotations;
	}
	public sales_followup getSales_followups() {
		return sales_followups;
	}
	public void setSales_followups(sales_followup sales_followups) {
		this.sales_followups = sales_followups;
	}
	public machine_activity getMachine_activitys() {
		return machine_activitys;
	}
	public void setMachine_activitys(machine_activity machine_activitys) {
		this.machine_activitys = machine_activitys;
	}
	public qci getQcis() {
		return qcis;
	}
	public void setQcis(qci qcis) {
		this.qcis = qcis;
	}
	public Goods_receipt_fg getGoods_receipt_fgs() {
		return Goods_receipt_fgs;
	}
	public void setGoods_receipt_fgs(Goods_receipt_fg goods_receipt_fgs) {
		Goods_receipt_fgs = goods_receipt_fgs;
	}
	public Adjustment_fg getAdjustment_fgs() {
		return Adjustment_fgs;
	}
	public void setAdjustment_fgs(Adjustment_fg adjustment_fgs) {
		Adjustment_fgs = adjustment_fgs;
	}
	public Inventory_transfer_fg getInventory_transfer_fgs() {
		return Inventory_transfer_fgs;
	}
	public void setInventory_transfer_fgs(Inventory_transfer_fg inventory_transfer_fgs) {
		Inventory_transfer_fgs = inventory_transfer_fgs;
	}
	public Issue_fg getIssue_fgs() {
		return Issue_fgs;
	}
	public void setIssue_fgs(Issue_fg issue_fgs) {
		Issue_fgs = issue_fgs;
	}
	public Goods_receipt_sk getGoods_receipt_sks() {
		return Goods_receipt_sks;
	}
	public void setGoods_receipt_sks(Goods_receipt_sk goods_receipt_sks) {
		Goods_receipt_sks = goods_receipt_sks;
	}
	public Adjustment_sk getAdjustment_sks() {
		return Adjustment_sks;
	}
	public void setAdjustment_sks(Adjustment_sk adjustment_sks) {
		Adjustment_sks = adjustment_sks;
	}
	public Inventory_transfer_sk getInventory_transfer_sks() {
		return Inventory_transfer_sks;
	}
	public void setInventory_transfer_sks(Inventory_transfer_sk inventory_transfer_sks) {
		Inventory_transfer_sks = inventory_transfer_sks;
	}
	public Issue_sk getIssue_sks() {
		return Issue_sks;
	}
	public void setIssue_sks(Issue_sk issue_sks) {
		Issue_sks = issue_sks;
	}
	
	
	

}
