package com.smepro.models;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smepro.dto.MultipleModelsDTO;
import com.smepro.entity.account_ledger;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.entity.employee_compensation;
import com.smepro.entity.employee_discipline;
import com.smepro.entity.employee_shift;
import com.smepro.entity.fa_invoices;
import com.smepro.entity.fa_payments;
import com.smepro.entity.hr_action;
import com.smepro.entity.machine_activity;
import com.smepro.entity.ps_contract;
import com.smepro.entity.purchase_order_tracking;
import com.smepro.entity.request_for_quotation;
import com.smepro.entity.sales_followup;
import com.smepro.entity.maintenance_requests;




public class LoadApp implements Cloneable {

	
	private int id;

	private int http_status_code;
	private String callback;
	
	
	private String type_refer;
	private String workflow_refer;
	private String doc_join;
	private String Subaction;;
	private String tempId;
	private String app_model;
	
	private int flow;
	private String nextstpe;
	private int nextstpe_id;
	private Myapp myapp;
	
	private HttpSession session;
	private  process process;
	private  edata edata;
	private  edata subedata;
	private workflow workflow;
	private sub_processes sub_processes;
	private ModelMap model;
	private edata_stock edata_stock;

	private edata_procurement edata_procurements;	
	private edata_register_processing edata_register_processing;	
	private user user;
	private qci qci;
	private bom bom;
	private production_standard production_standard;
	private List<file> file;
	
	private List<keymap> keymap;
	private keymap map;
	
	
	private RedirectAttributes redirectAttributes;
	private List<String> ability = new ArrayList<String>();
	private Approve approve;
	private Transition Transition;
	private Object transitionObject;
	
	
	private String spending_amount;
	private String approval_limit;
	
	private sub_processes custom_clear;
	private sub_processes batch_insert;
	private sub_processes batch_clear;
	private sub_processes batch_modify;
	private sub_processes batch_continue;
	

	private edata_production_record edata_production_records; 
	private production_directive production_directives; 
	private production_list production_lists; 
	private condition condition; 
	private Looselink Looselink; 
	private Management thisapp; 
	private TabManagement tab_id; 
	private List<InputHiddenVisibility> inputHiddenVisibility; 

	
	
	
	private edata_register edata_register;
	private edata_product edata_product;
	
	
	private long register_count;
	private long action_count;
	private long continue_count;
	private long modify_count;
	private long hierarchical_count;
	private long reference_only;
	private long  processing_count;
	
	private String activate_action;
	private List<String> input_db;
	private List<Tabform>  tabForm;
	
	
	 
	
	
	
	private Adjustment_fg adjustment_fg;
	private Goods_receipt_fg goods_receipt_fg; 
	private Issue_fg issue_fg; 
	private Inventory_transfer_fg inventory_transfer_fg; 
	
	private Adjustment_sk adjustment_sk;
	private Goods_receipt_sk goods_receipt_sk; 
	private Issue_sk issue_sk; 
	private Inventory_transfer_sk inventory_transfer_sk; 
		
	
	
	private other other;
	private String type;
	
	
	private account_ledger account_ledger;
	private fa_payments fa_payments;
	private hr_action hr_action;
	private fa_invoices fa_invoices;
	private employee_shift employee_shift;
	private employee_discipline employee_discipline;
	private employee_compensation employee_compensation;
	private edata_workrule edata_workrule;
	private edata_assets edata_assets;
	private edata_annual_plan edata_annual_plan;
	private machine_activity machine_activity;
	private ps_contract ps_contract;
	private purchase_order_tracking purchase_order_tracking;
	private sales_followup sales_followup;
	private request_for_quotation request_for_quotation;
	private maintenance_requests maintenance_requests;
	
	
	private BatchEdata batchEdata;
	private List<BatchEdata> batchEdatas;
	private List<sales_followup> listSales_followup;
	private List<other> listOther;
	private List<account_ledger> listAccount_ledger;
	private List<fa_payments> listFa_payments;
	private List<hr_action> listHr_action;
	private List<fa_invoices> listFa_invoices;
	private List<employee_shift> listEmployee_shift;
	private List<employee_discipline> listEmployee_discipline;
	private List<employee_compensation> listEmployee_compensation;
	private List<edata_workrule> listEdata_workrule;
	private List<edata_assets> listEdata_assets;
	private List<edata_annual_plan> listEdata_annual_plan;
	private List<machine_activity> listMachine_activity;
	private List<ps_contract> listPs_contract;
	private List<purchase_order_tracking> listPurchase_order_tracking;
	private List<request_for_quotation> listRequest_for_quotation;
	private List<maintenance_requests> listMaintenance_requests;

	private List<edata_register> listEdata_register;
	private List<edata_product> listedata_product;
	
	
	
	private String  app_action;
	private String  processing_type;
	private String  batch_input;
	private int  relation_id;
	private String  item_id;
	private Object Actionbatch;
	
	private PlatformConfig Config;
	private Platform platform;
	
	private int feed;
	
	
	private PlatformUserSettings platformUserSettings;
	private PlatformReport PlatformReport;
	
	private process process_refer;
	private int saveId;
	
	
	
	
	List<registerkey> heads;
	
	
	
	
	public List<BatchEdata> getBatchEdatas() {
		return batchEdatas;
	}
	
	
	public List<InputHiddenVisibility> getInputHiddenVisibility() {
		return inputHiddenVisibility;
	}


	public void setInputHiddenVisibility(List<InputHiddenVisibility> inputHiddenVisibility) {
		this.inputHiddenVisibility = inputHiddenVisibility;
	}


	public void setBatchEdatas(List<BatchEdata> batchEdatas) {
		this.batchEdatas = batchEdatas;
	}
	public List<registerkey> getHeads() {
		return heads;
	}
	public void setHeads(List<registerkey> heads) {
		this.heads = heads;
	}
	public PlatformReport getPlatformReport() {
		return PlatformReport;
	}
	public void setPlatformReport(PlatformReport platformReport) {
		PlatformReport = platformReport;
	}
	public int getHttp_status_code() {
		return http_status_code;
	}
	public void setHttp_status_code(int http_status_code) {
		this.http_status_code = http_status_code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public process getProcess() {
		return process;
	}
	public void setProcess(process process) {
		this.process = process;
	}
	public edata getEdata() {
		return edata;
	}
	public void setEdata(edata edata) {
		this.edata = edata;
	}
	public workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(workflow workflow) {
		this.workflow = workflow;
	}
	public sub_processes getSub_processes() {
		return sub_processes;
	}
	public void setSub_processes(sub_processes sub_processes) {
		this.sub_processes = sub_processes;
	}
	public ModelMap getModel() {
		return model;
	}
	public void setModel(ModelMap model) {
		this.model = model;
	}
	public edata_stock getEdata_stock() {
		return edata_stock;
	}
	public void setEdata_stock(edata_stock edata_stock) {
		this.edata_stock = edata_stock;
	}
	public List<keymap> getKeymap() {
		return keymap;
	}
	public void setKeymap(List<keymap> keymap) {
		this.keymap = keymap;
	}
	public RedirectAttributes getRedirectAttributes() {
		return redirectAttributes;
	}
	public void setRedirectAttributes(RedirectAttributes redirectAttributes) {
		this.redirectAttributes = redirectAttributes;
	}
	public String getNextstpe() {
		return nextstpe;
	}
	public void setNextstpe(String nextstpe) {
		this.nextstpe = nextstpe;
	}
	public Approve getApprove() {
		return approve;
	}
	public void setApprove(Approve approve) {
		this.approve = approve;
	}
	public List<String> getAbility() {
		return ability;
	}
	public void setAbility(List<String> ability) {
		this.ability = ability;
	}
	public Transition getTransition() {
		return Transition;
	}
	public void setTransition(Transition transition) {
		Transition = transition;
	}
	public edata_register getEdata_register() {
		return edata_register;
	}
	public void setEdata_register(edata_register edata_register) {
		this.edata_register = edata_register;
	}
	public edata_procurement getEdata_procurements() {
		return edata_procurements;
	}
	public void setEdata_procurements(edata_procurement edata_procurements) {
		this.edata_procurements = edata_procurements;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public int getNextstpe_id() {
		return nextstpe_id;
	}
	public void setNextstpe_id(int nextstpe_id) {
		this.nextstpe_id = nextstpe_id;
	}
	
	
	public void setSpending_amount(String spending_amount) {
		this.spending_amount = spending_amount;
	}
	public String getApproval_limit() {
		return approval_limit;
	}
	public void setApproval_limit(String approval_limit) {
		this.approval_limit = approval_limit;
	}
	public Myapp getMyapp() {
		return myapp;
	}
	public void setMyapp(Myapp myapp) {
		this.myapp = myapp;
	}
	public sub_processes getCustom_clear() {
		return custom_clear;
	}
	public void setCustom_clear(sub_processes custom_clear) {
		this.custom_clear = custom_clear;
	}
	public sub_processes getBatch_insert() {
		return batch_insert;
	}
	public void setBatch_insert(sub_processes batch_insert) {
		this.batch_insert = batch_insert;
	}
	public String getSpending_amount() {
		return spending_amount;
	}
	public sub_processes getBatch_clear() {
		return batch_clear;
	}
	public void setBatch_clear(sub_processes batch_clear) {
		this.batch_clear = batch_clear;
	}
	public long getRegister_count() {
		return register_count;
	}
	public void setRegister_count(long register_count) {
		this.register_count = register_count;
	}
	public long getAction_count() {
		return action_count;
	}
	public void setAction_count(long action_count) {
		this.action_count = action_count;
	}
	public long getContinue_count() {
		return continue_count;
	}
	public void setContinue_count(long continue_count) {
		this.continue_count = continue_count;
	}
	public long getModify_count() {
		return modify_count;
	}
	public void setModify_count(long modify_count) {
		this.modify_count = modify_count;
	}
	public long getHierarchical_count() {
		return hierarchical_count;
	}
	public void setHierarchical_count(long hierarchical_count) {
		this.hierarchical_count = hierarchical_count;
	}
	public long getReference_only() {
		return reference_only;
	}
	public void setReference_only(long reference_only) {
		this.reference_only = reference_only;
	}
	public sub_processes getBatch_modify() {
		return batch_modify;
	}
	public void setBatch_modify(sub_processes batch_modify) {
		this.batch_modify = batch_modify;
	}
	public sub_processes getBatch_continue() {
		return batch_continue;
	}
	public void setBatch_continue(sub_processes batch_continue) {
		this.batch_continue = batch_continue;
	}
	public edata_register_processing getEdata_register_processing() {
		return edata_register_processing;
	}
	public void setEdata_register_processing(edata_register_processing edata_register_processing) {
		this.edata_register_processing = edata_register_processing;
	}
	public long getProcessing_count() {
		return processing_count;
	}
	public void setProcessing_count(long processing_count) {
		this.processing_count = processing_count;
	}

	public edata_product getEdata_product() {
		return edata_product;
	}
	public void setEdata_product(edata_product edata_product) {
		this.edata_product = edata_product;
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
	public String getType_refer() {
		return type_refer;
	}
	public void setType_refer(String type_refer) {
		this.type_refer = type_refer;
	}
	public String getWorkflow_refer() {
		return workflow_refer;
	}
	public void setWorkflow_refer(String workflow_refer) {
		this.workflow_refer = workflow_refer;
	}
	public String getDoc_join() {
		return doc_join;
	}
	public void setDoc_join(String doc_join) {
		this.doc_join = doc_join;
	}
	public bom getBom() {
		return bom;
	}
	public void setBom(bom bom) {
		this.bom = bom;
	}
	public production_standard getProduction_standard() {
		return production_standard;
	}
	public void setProduction_standard(production_standard production_standard) {
		this.production_standard = production_standard;
	}
	public edata getSubedata() {
		return subedata;
	}
	public void setSubedata(edata subedata) {
		this.subedata = subedata;
	}
	public qci getQci() {
		return qci;
	}
	public void setQci(qci qci) {
		this.qci = qci;
	}
	public String getActivate_action() {
		return activate_action;
	}
	public void setActivate_action(String activate_action) {
		this.activate_action = activate_action;
	}
	public other getOther() {
		return other;
	}
	public void setOther(other other) {
		this.other = other;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public condition getCondition() {
		return condition;
	}
	public void setCondition(condition condition) {
		this.condition = condition;
	}
	public List<file> getFile() {
		return file;
	}
	public void setFile(List<file> file) {
		this.file = file;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public List<String> getInput_db() {
		return input_db;
	}
	public void setInput_db(List<String> input_db) {
		this.input_db = input_db;
	}
	
	
	
	
	public String getSubaction() {
		return Subaction;
	}
	public void setSubaction(String subaction) {
		Subaction = subaction;
	}
	
	
	
	
	public Adjustment_fg getAdjustment_fg() {
		return adjustment_fg;
	}
	public void setAdjustment_fg(Adjustment_fg adjustment_fg) {
		this.adjustment_fg = adjustment_fg;
	}
	public Goods_receipt_fg getGoods_receipt_fg() {
		return goods_receipt_fg;
	}
	public void setGoods_receipt_fg(Goods_receipt_fg goods_receipt_fg) {
		this.goods_receipt_fg = goods_receipt_fg;
	}
	public Inventory_transfer_fg getInventory_transfer_fg() {
		return inventory_transfer_fg;
	}
	public void setInventory_transfer_fg(Inventory_transfer_fg inventory_transfer_fg) {
		this.inventory_transfer_fg = inventory_transfer_fg;
	}
	public Issue_fg getIssue_fg() {
		return issue_fg;
	}
	public void setIssue_fg(Issue_fg issue_fg) {
		this.issue_fg = issue_fg;
	}

	public Adjustment_sk getAdjustment_sk() {
		return adjustment_sk;
	}
	public void setAdjustment_sk(Adjustment_sk adjustment_sk) {
		this.adjustment_sk = adjustment_sk;
	}
	public Goods_receipt_sk getGoods_receipt_sk() {
		return goods_receipt_sk;
	}
	public void setGoods_receipt_sk(Goods_receipt_sk goods_receipt_sk) {
		this.goods_receipt_sk = goods_receipt_sk;
	}
	public Issue_sk getIssue_sk() {
		return issue_sk;
	}
	public void setIssue_sk(Issue_sk issue_sk) {
		this.issue_sk = issue_sk;
	}
	public Inventory_transfer_sk getInventory_transfer_sk() {
		return inventory_transfer_sk;
	}
	public void setInventory_transfer_sk(Inventory_transfer_sk inventory_transfer_sk) {
		this.inventory_transfer_sk = inventory_transfer_sk;
	}
	
	
	public account_ledger getAccount_ledger() {
		return account_ledger;
	}
	public void setAccount_ledger(account_ledger account_ledger) {
		this.account_ledger = account_ledger;
	}
	public fa_payments getFa_payments() {
		return fa_payments;
	}
	public void setFa_payments(fa_payments fa_payments) {
		this.fa_payments = fa_payments;
	}
	public hr_action getHr_action() {
		return hr_action;
	}
	public void setHr_action(hr_action hr_action) {
		this.hr_action = hr_action;
	}
	public fa_invoices getFa_invoices() {
		return fa_invoices;
	}
	public void setFa_invoices(fa_invoices fa_invoices) {
		this.fa_invoices = fa_invoices;
	}
	public employee_shift getEmployee_shift() {
		return employee_shift;
	}
	public void setEmployee_shift(employee_shift employee_shift) {
		this.employee_shift = employee_shift;
	}
	public employee_discipline getEmployee_discipline() {
		return employee_discipline;
	}
	public void setEmployee_discipline(employee_discipline employee_discipline) {
		this.employee_discipline = employee_discipline;
	}
	public employee_compensation getEmployee_compensation() {
		return employee_compensation;
	}
	public void setEmployee_compensation(employee_compensation employee_compensation) {
		this.employee_compensation = employee_compensation;
	}
	public edata_workrule getEdata_workrule() {
		return edata_workrule;
	}
	public void setEdata_workrule(edata_workrule edata_workrule) {
		this.edata_workrule = edata_workrule;
	}
	public edata_assets getEdata_assets() {
		return edata_assets;
	}
	public void setEdata_assets(edata_assets edata_assets) {
		this.edata_assets = edata_assets;
	}
	public edata_annual_plan getEdata_annual_plan() {
		return edata_annual_plan;
	}
	public void setEdata_annual_plan(edata_annual_plan edata_annual_plan) {
		this.edata_annual_plan = edata_annual_plan;
	}
	public machine_activity getMachine_activity() {
		return machine_activity;
	}
	public void setMachine_activity(machine_activity machine_activity) {
		this.machine_activity = machine_activity;
	}
	public ps_contract getPs_contract() {
		return ps_contract;
	}
	public void setPs_contract(ps_contract ps_contract) {
		this.ps_contract = ps_contract;
	}
	public purchase_order_tracking getPurchase_order_tracking() {
		return purchase_order_tracking;
	}
	public void setPurchase_order_tracking(purchase_order_tracking purchase_order_tracking) {
		this.purchase_order_tracking = purchase_order_tracking;
	}
	public sales_followup getSales_followup() {
		return sales_followup;
	}
	public void setSales_followup(sales_followup sales_followup) {
		this.sales_followup = sales_followup;
	}
	public request_for_quotation getRequest_for_quotation() {
		return request_for_quotation;
	}
	public void setRequest_for_quotation(request_for_quotation request_for_quotation) {
		this.request_for_quotation = request_for_quotation;
	}
	public maintenance_requests getMaintenance_requests() {
		return maintenance_requests;
	}
	public void setMaintenance_requests(maintenance_requests maintenance_requests) {
		this.maintenance_requests = maintenance_requests;
	}
	public Looselink getLooselink() {
		return Looselink;
	}
	public void setLooselink(Looselink looselink) {
		Looselink = looselink;
	}
	
	
	public Object getActionbatch() {
		return Actionbatch;
	}
	public void setActionbatch(Object actionbatch) {
		Actionbatch = actionbatch;
	}

	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getProcessing_type() {
		return processing_type;
	}
	public PlatformConfig getConfig() {
		return Config;
	}
	public void setConfig(PlatformConfig config) {
		Config = config;
	}
	
	
	
	
	public List<Tabform> getTabForm() {
		return tabForm;
	}
	public void setTabForm(List<Tabform> tabForm) {
		this.tabForm = tabForm;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public int getFeed() {
		return feed;
	}
	public void setFeed(int feed) {
		this.feed = feed;
	}
	@Override
	public String toString() {
		return String.format(
				"LoadApp [id=%s, type_refer=%s, workflow_refer=%s, doc_join=%s, Subaction=%s, tempId=%s, flow=%s, nextstpe=%s, nextstpe_id=%s, myapp=%s, session=%s, process=%s, edata=%s, subedata=%s, workflow=%s, sub_processes=%s, model=%s, edata_stock=%s, edata_register=%s, edata_procurements=%s, edata_register_processing=%s, user=%s, qci=%s, bom=%s, production_standard=%s, file=%s, keymap=%s, redirectAttributes=%s, ability=%s, approve=%s, Transition=%s, spending_amount=%s, approval_limit=%s, custom_clear=%s, batch_insert=%s, batch_clear=%s, batch_modify=%s, batch_continue=%s, edata_products=%s, edata_production_records=%s, production_directives=%s, production_lists=%s, condition=%s, Looselink=%s, register_count=%s, action_count=%s, continue_count=%s, modify_count=%s, hierarchical_count=%s, reference_only=%s, processing_count=%s, activate_action=%s, input_db=%s, adjustment_fg=%s, goods_receipt_fg=%s, issue_fg=%s, inventory_transfer_fg=%s, adjustment_sk=%s, goods_receipt_sk=%s, issue_sk=%s, inventory_transfer_sk=%s, other=%s, type=%s, account_ledger=%s, fa_payments=%s, hr_action=%s, fa_invoices=%s, employee_shift=%s, employee_discipline=%s, employee_compensation=%s, edata_workrule=%s, edata_assets=%s, edata_annual_plan=%s, machine_activity=%s, ps_contract=%s, purchase_order_tracking=%s, sales_followup=%s, request_for_quotation=%s, maintenance_requests=%s, processing_type=%s, batch_input=%s, relation_id=%s, Actionbatch=%s]",
				id, type_refer, workflow_refer, doc_join, Subaction, tempId, flow, nextstpe, nextstpe_id, myapp,
				session, process, edata, subedata, workflow, sub_processes, model, edata_stock, edata_register,
				edata_procurements, edata_register_processing, user, qci, bom, production_standard, file, keymap,
				redirectAttributes, ability, approve, Transition, spending_amount, approval_limit, custom_clear,
				batch_insert, batch_clear, batch_modify, batch_continue, edata_product, edata_production_records,
				production_directives, production_lists, condition, Looselink, register_count, action_count,
				continue_count, modify_count, hierarchical_count, reference_only, processing_count, activate_action,
				input_db, adjustment_fg, goods_receipt_fg, issue_fg, inventory_transfer_fg, adjustment_sk,
				goods_receipt_sk, issue_sk, inventory_transfer_sk, other, type, account_ledger, fa_payments, hr_action,
				fa_invoices, employee_shift, employee_discipline, employee_compensation, edata_workrule, edata_assets,
				edata_annual_plan, machine_activity, ps_contract, purchase_order_tracking, sales_followup,
				request_for_quotation, maintenance_requests, processing_type, batch_input, relation_id, Actionbatch);
	}
	public void setProcessing_type(String processing_type) {
		this.processing_type = processing_type;
	}
	public int getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}
	
	
	public String getBatch_input() {
		return batch_input;
	}
	public void setBatch_input(String batch_input) {
		this.batch_input = batch_input;
	}
	
	public String getApp_model() {
		return app_model;
	}
	public void setApp_model(String app_model) {
		this.app_model = app_model;
	}
	
	
	
	
	
	public TabManagement getTab_id() {
		return tab_id;
	}
	public void setTab_id(TabManagement tab_id) {
		this.tab_id = tab_id;
	}
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public keymap getMap() {
		return map;
	}
	public void setMap(keymap map) {
		this.map = map;
	}

	public BatchEdata getBatchEdata() {
		return batchEdata;
	}
	public void setBatchEdata(BatchEdata batchEdata) {
		this.batchEdata = batchEdata;
	}
	public List<sales_followup> getListSales_followup() {
		return listSales_followup;
	}
	public void setListSales_followup(List<sales_followup> listSales_followup) {
		this.listSales_followup = listSales_followup;
	}
	public List<other> getListOther() {
		return listOther;
	}
	public void setListOther(List<other> listOther) {
		this.listOther = listOther;
	}
	public List<account_ledger> getListAccount_ledger() {
		return listAccount_ledger;
	}
	public void setListAccount_ledger(List<account_ledger> listAccount_ledger) {
		this.listAccount_ledger = listAccount_ledger;
	}
	public List<fa_payments> getListFa_payments() {
		return listFa_payments;
	}
	public void setListFa_payments(List<fa_payments> listFa_payments) {
		this.listFa_payments = listFa_payments;
	}
	public List<hr_action> getListHr_action() {
		return listHr_action;
	}
	public void setListHr_action(List<hr_action> listHr_action) {
		this.listHr_action = listHr_action;
	}
	public List<fa_invoices> getListFa_invoices() {
		return listFa_invoices;
	}
	public void setListFa_invoices(List<fa_invoices> listFa_invoices) {
		this.listFa_invoices = listFa_invoices;
	}
	public List<employee_shift> getListEmployee_shift() {
		return listEmployee_shift;
	}
	public void setListEmployee_shift(List<employee_shift> listEmployee_shift) {
		this.listEmployee_shift = listEmployee_shift;
	}
	public List<employee_discipline> getListEmployee_discipline() {
		return listEmployee_discipline;
	}
	public void setListEmployee_discipline(List<employee_discipline> listEmployee_discipline) {
		this.listEmployee_discipline = listEmployee_discipline;
	}
	public List<employee_compensation> getListEmployee_compensation() {
		return listEmployee_compensation;
	}
	public void setListEmployee_compensation(List<employee_compensation> listEmployee_compensation) {
		this.listEmployee_compensation = listEmployee_compensation;
	}
	public List<edata_workrule> getListEdata_workrule() {
		return listEdata_workrule;
	}
	public void setListEdata_workrule(List<edata_workrule> listEdata_workrule) {
		this.listEdata_workrule = listEdata_workrule;
	}
	public List<edata_assets> getListEdata_assets() {
		return listEdata_assets;
	}
	public void setListEdata_assets(List<edata_assets> listEdata_assets) {
		this.listEdata_assets = listEdata_assets;
	}
	public List<edata_annual_plan> getListEdata_annual_plan() {
		return listEdata_annual_plan;
	}
	public void setListEdata_annual_plan(List<edata_annual_plan> listEdata_annual_plan) {
		this.listEdata_annual_plan = listEdata_annual_plan;
	}
	public List<machine_activity> getListMachine_activity() {
		return listMachine_activity;
	}
	public void setListMachine_activity(List<machine_activity> listMachine_activity) {
		this.listMachine_activity = listMachine_activity;
	}
	public List<ps_contract> getListPs_contract() {
		return listPs_contract;
	}
	public void setListPs_contract(List<ps_contract> listPs_contract) {
		this.listPs_contract = listPs_contract;
	}
	public List<purchase_order_tracking> getListPurchase_order_tracking() {
		return listPurchase_order_tracking;
	}
	public void setListPurchase_order_tracking(List<purchase_order_tracking> listPurchase_order_tracking) {
		this.listPurchase_order_tracking = listPurchase_order_tracking;
	}
	public List<request_for_quotation> getListRequest_for_quotation() {
		return listRequest_for_quotation;
	}
	public void setListRequest_for_quotation(List<request_for_quotation> listRequest_for_quotation) {
		this.listRequest_for_quotation = listRequest_for_quotation;
	}
	public List<maintenance_requests> getListMaintenance_requests() {
		return listMaintenance_requests;
	}
	public void setListMaintenance_requests(List<maintenance_requests> listMaintenance_requests) {
		this.listMaintenance_requests = listMaintenance_requests;
	}
	public process getProcess_refer() {
		return process_refer;
	}
	public void setProcess_refer(process process_refer) {
		this.process_refer = process_refer;
	}
	public PlatformUserSettings getPlatformUserSettings() {
		return platformUserSettings;
	}
	public void setPlatformUserSettings(PlatformUserSettings platformUserSettings) {
		this.platformUserSettings = platformUserSettings;
	}
	public Management getThisapp() {
		return thisapp;
	}
	public void setThisapp(Management thisapp) {
		this.thisapp = thisapp;
	}
	public String getApp_action() {
		return app_action;
	}
	public void setApp_action(String app_action) {
		this.app_action = app_action;
	}
	
	
	
	
	
	
	
	
	
	public List<edata_register> getListEdata_register() {
		return listEdata_register;
	}


	public void setListEdata_register(List<edata_register> listEdata_register) {
		this.listEdata_register = listEdata_register;
	}


	public List<edata_product> getListedata_product() {
		return listedata_product;
	}


	public void setListedata_product(List<edata_product> listedata_product) {
		this.listedata_product = listedata_product;
	}


	public void reset(){

		this.id = 0;
		this.type_refer = null;
		this.workflow_refer = null;
		this.doc_join = null;
		this.tempId = null;
		this.flow = 0;
		this.nextstpe = null;
		this.nextstpe_id = 0;
		this.myapp = null;
		this.session = null;
		this.process = null;
		
		this.thisapp = null;
		this.tab_id = null;
		this.app_model = null;

		
		if(this.thisapp != null) {
			this.thisapp = null;
		}
		
		if(this.tab_id != null) {
			this.tab_id = null;
		}
		
		

		if(this.edata != null) {
			this.edata = null;
		}
		
		if(this.adjustment_fg != null) {
			this.adjustment_fg.reset();
			this.adjustment_fg = null;
		}
		if(this.goods_receipt_fg != null) {
			this.goods_receipt_fg.reset();
			this.goods_receipt_fg = null;
		}
		if(this.issue_fg != null) {
			this.issue_fg.reset();
			this.issue_fg = null;
		}
		if(this.inventory_transfer_fg != null) {
			this.inventory_transfer_fg.reset();
			this.inventory_transfer_fg = null;
		}
		if(this.adjustment_sk != null) {
			this.adjustment_sk.reset();
			this.adjustment_sk = null;
		}
		if(this.goods_receipt_sk != null) {
			this.goods_receipt_sk.reset();
			this.goods_receipt_sk = null;
		}
		if(this.issue_sk != null) {
			this.issue_sk.reset();
			this.issue_sk = null;
		}
		if(this.inventory_transfer_sk != null) {
			this.inventory_transfer_sk.reset();
			this.inventory_transfer_sk = null;
		}
		if(this.other != null) {
			this.other.reset();
			this.other = null;
		}
		if(this.account_ledger != null) {
			this.account_ledger.reset();
			this.account_ledger = null;
		}
		
		if(this.fa_payments != null) {
			this.fa_payments.reset();
			this.fa_payments = null;
		}
		
		if(this.hr_action != null) {
			this.hr_action.reset();
			this.hr_action = null;
		}
		if(this.fa_invoices != null) {
			this.fa_invoices.reset();
			this.fa_invoices = null;
		}
		if(this.employee_shift != null) {
			this.employee_shift.reset();
			this.employee_shift = null;
		}

		if(this.employee_discipline != null) {
			this.employee_discipline.reset();
			this.employee_discipline = null;
		}
	
		if(this.employee_compensation != null) {
			this.employee_compensation.reset();
			this.employee_compensation = null;
		}
		if(this.edata_workrule != null) {
			this.edata_workrule.reset();
			this.edata_workrule = null;
		}
		if(this.edata_assets != null) {
			this.edata_assets.reset();
			this.edata_assets = null;
		}
		if(this.edata_annual_plan != null) {
			this.edata_annual_plan.reset();
			this.edata_annual_plan = null;
		}
		if(this.machine_activity != null) {
			this.machine_activity.reset();
			this.machine_activity = null;
		}
		if(this.ps_contract != null) {
			this.ps_contract.reset();
			this.ps_contract = null;
		}
		if(this.purchase_order_tracking != null) {
			this.purchase_order_tracking.reset();
			this.purchase_order_tracking = null;
		}
		if(this.sales_followup != null) {
			this.sales_followup.reset();
			this.sales_followup = null;
		}
		if(this.request_for_quotation != null) {
			this.request_for_quotation.reset();
			this.request_for_quotation = null;
		}
		if(this.maintenance_requests != null) {
			this.maintenance_requests.reset();
			this.maintenance_requests = null;
		}
		if(this.edata_stock != null) {
			this.edata_stock.reset();
			this.edata_stock = null;
		}
		if(this.edata_register != null) {
			this.edata_register.reset();
			this.edata_register = null;
		}
		if(this.edata_procurements != null) {
			this.edata_procurements.reset();
			this.edata_procurements = null;
		}
		if(this.edata_register_processing != null) {
			this.edata_register_processing.reset();
			this.edata_register_processing = null;
		}
		if(this.qci != null) {
			this.qci.reset();
			this.qci = null;
		}
		if(this.bom != null) {
			this.bom.reset();
			this.bom = null;
		}
		if(this.production_standard != null) {
			this.production_standard.reset();
			this.production_standard = null;
		}
	
		if(this.edata_product != null) {
			this.edata_product.reset();
			this.edata_product = null;
		}
		if(this.edata_production_records != null) {
			this.edata_production_records.reset();
			this.edata_production_records = null;
		}
		if(this.production_directives != null) {
			this.production_directives.reset();
			this.production_directives = null;
		}
		if(this.production_lists != null) {
			this.production_lists.reset();
			this.production_lists = null;
		}


		/*
		this.subedata = null;
		this.workflow = null;

		this.model = null;
	
		this.user = null;

		this.redirectAttributes = null;
		this.ability = null;
		this.approve = null;
		this.spending_amount = null;
		this.approval_limit = null;
		this.custom_clear = null;
		this.batch_insert = null;
		this.batch_clear = null;
		this.batch_modify = null;
		this.batch_continue = null;

		this.register_count = 0;
		this.action_count = 0;
		this.continue_count = 0;
		this.modify_count = 0;
		this.hierarchical_count = 0;
		this.reference_only = 0;
		this.processing_count = 0;
		this.activate_action = null;
		this.input_db = null;
		
		*/
	}

	
	 public Object getTransitionObject() {
		return transitionObject;
	}


	public void setTransitionObject(Object transitionObject) {
		this.transitionObject = transitionObject;
	}


	public int getSaveId() {
		return saveId;
	}


	public void setSaveId(int saveId) {
		this.saveId = saveId;
	}


	@Override
	 public LoadApp clone() {
	        try {
	            return (LoadApp) super.clone();
	        } catch (CloneNotSupportedException e) {
	            throw new RuntimeException(e);
	        }
	 }
	

	
}
