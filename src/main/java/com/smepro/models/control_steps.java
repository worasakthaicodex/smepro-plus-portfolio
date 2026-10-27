package com.smepro.models;

public class control_steps extends edata {

	
	private int id;
	private int id_add;
	private int id_app;
	private int edata_id;

	private int step;
	private String product_category;
	private String purchase_order;
	private String purchase_requisition;
	private String production_directive;
	private String order_quantity;
	private String total_order_quantity;
	private String delivery_date;
	private String lead_time;
	private String batch;
	private String production_output;
	private String good_production_output;
	private String defective_production_output;
	private String total_good_production;
	private String total_defective_production;
	private String total_production;
	private String customer;
	private String customer_info;
	private String material;
	private String manufacturer;
	private String lot_material;
	private String batch_material;
	private String incoming;
	private String deliver;
	private String material_cost;
	private String material_usage;
	private String total_material_usage;
	private String batch_usage;
	private String wip;
	private String batch_wip;
	private String fg;
	private String batch_fg;
	private String storage;
	private String storage_space;	
	private String voucher;
	private String due;
	private String balance;
	private String balance_before;
	private String balance_after;
	private String quality_control;
	private String inspection_result;
	private String supplier;
	private String purchase_date;
	private String delivery_status;
	private String unit;

	

	

	private int product_id;

	
	private String step_1;
	private String step_2;
	private String step_3;
	private String step_4;
	private String step_5;
	private String step_6;
	private String step_7;
	private String step_8;
	private String step_9;
	private String step_10;
	private String step_11;
	private String step_12;
	private String step_13;
	private String step_14;
	private String step_15;
	private String step_16;
	private String step_17;
	private String step_18;
	private String step_19;
	private String step_20;
	private int processes_id;
	private String Product_code;
	
	private int count;
	
	
	
	
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public int getId_add() {
		return id_add;
	}
	public void setId_add(int id_add) {
		this.id_add = id_add;
	}
	public int getId_app() {
		return id_app;
	}
	public void setId_app(int id_app) {
		this.id_app = id_app;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}

	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getPurchase_order() {
		return purchase_order;
	}
	public void setPurchase_order(String purchase_order) {
		this.purchase_order = purchase_order;
	}
	public String getPurchase_requisition() {
		return purchase_requisition;
	}
	public void setPurchase_requisition(String purchase_requisition) {
		this.purchase_requisition = purchase_requisition;
	}
	public String getProduction_directive() {
		return production_directive;
	}
	public void setProduction_directive(String production_directive) {
		this.production_directive = production_directive;
	}
	public String getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(String order_quantity) {
		this.order_quantity = order_quantity;
	}
	public String getTotal_order_quantity() {
		return total_order_quantity;
	}
	public void setTotal_order_quantity(String total_order_quantity) {
		this.total_order_quantity = total_order_quantity;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getLead_time() {
		return lead_time;
	}
	public void setLead_time(String lead_time) {
		this.lead_time = lead_time;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getProduction_output() {
		return production_output;
	}
	public void setProduction_output(String production_output) {
		this.production_output = production_output;
	}
	public String getGood_production_output() {
		return good_production_output;
	}
	public void setGood_production_output(String good_production_output) {
		this.good_production_output = good_production_output;
	}
	public String getDefective_production_output() {
		return defective_production_output;
	}
	public void setDefective_production_output(String defective_production_output) {
		this.defective_production_output = defective_production_output;
	}
	public String getTotal_good_production() {
		return total_good_production;
	}
	public void setTotal_good_production(String total_good_production) {
		this.total_good_production = total_good_production;
	}
	public String getTotal_defective_production() {
		return total_defective_production;
	}
	public void setTotal_defective_production(String total_defective_production) {
		this.total_defective_production = total_defective_production;
	}
	public String getTotal_production() {
		return total_production;
	}
	public void setTotal_production(String total_production) {
		this.total_production = total_production;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomer_info() {
		return customer_info;
	}
	public void setCustomer_info(String customer_info) {
		this.customer_info = customer_info;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getLot_material() {
		return lot_material;
	}
	public void setLot_material(String lot_material) {
		this.lot_material = lot_material;
	}
	public String getBatch_material() {
		return batch_material;
	}
	public void setBatch_material(String batch_material) {
		this.batch_material = batch_material;
	}
	public String getIncoming() {
		return incoming;
	}
	public void setIncoming(String incoming) {
		this.incoming = incoming;
	}
	public String getDeliver() {
		return deliver;
	}
	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}
	public String getMaterial_cost() {
		return material_cost;
	}
	public void setMaterial_cost(String material_cost) {
		this.material_cost = material_cost;
	}
	public String getMaterial_usage() {
		return material_usage;
	}
	public void setMaterial_usage(String material_usage) {
		this.material_usage = material_usage;
	}
	public String getTotal_material_usage() {
		return total_material_usage;
	}
	public void setTotal_material_usage(String total_material_usage) {
		this.total_material_usage = total_material_usage;
	}
	public String getBatch_usage() {
		return batch_usage;
	}
	public void setBatch_usage(String batch_usage) {
		this.batch_usage = batch_usage;
	}
	public String getWip() {
		return wip;
	}
	public void setWip(String wip) {
		this.wip = wip;
	}
	public String getBatch_wip() {
		return batch_wip;
	}
	public void setBatch_wip(String batch_wip) {
		this.batch_wip = batch_wip;
	}
	public String getFg() {
		return fg;
	}
	public void setFg(String fg) {
		this.fg = fg;
	}
	public String getBatch_fg() {
		return batch_fg;
	}
	public void setBatch_fg(String batch_fg) {
		this.batch_fg = batch_fg;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getStorage_space() {
		return storage_space;
	}
	public void setStorage_space(String storage_space) {
		this.storage_space = storage_space;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBalance_before() {
		return balance_before;
	}
	public void setBalance_before(String balance_before) {
		this.balance_before = balance_before;
	}
	public String getBalance_after() {
		return balance_after;
	}
	public void setBalance_after(String balance_after) {
		this.balance_after = balance_after;
	}
	public String getQuality_control() {
		return quality_control;
	}
	public void setQuality_control(String quality_control) {
		this.quality_control = quality_control;
	}
	public String getInspection_result() {
		return inspection_result;
	}
	public void setInspection_result(String inspection_result) {
		this.inspection_result = inspection_result;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getDelivery_status() {
		return delivery_status;
	}
	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStep_1() {
		return step_1;
	}
	public void setStep_1(String step_1) {
		this.step_1 = step_1;
	}
	public String getStep_2() {
		return step_2;
	}
	public void setStep_2(String step_2) {
		this.step_2 = step_2;
	}
	public String getStep_3() {
		return step_3;
	}
	public void setStep_3(String step_3) {
		this.step_3 = step_3;
	}
	public String getStep_4() {
		return step_4;
	}
	public void setStep_4(String step_4) {
		this.step_4 = step_4;
	}
	public String getStep_5() {
		return step_5;
	}
	public void setStep_5(String step_5) {
		this.step_5 = step_5;
	}
	public String getStep_6() {
		return step_6;
	}
	public void setStep_6(String step_6) {
		this.step_6 = step_6;
	}
	public String getStep_7() {
		return step_7;
	}
	public void setStep_7(String step_7) {
		this.step_7 = step_7;
	}
	public String getStep_8() {
		return step_8;
	}
	public void setStep_8(String step_8) {
		this.step_8 = step_8;
	}
	public String getStep_9() {
		return step_9;
	}
	public void setStep_9(String step_9) {
		this.step_9 = step_9;
	}
	public String getStep_10() {
		return step_10;
	}
	public void setStep_10(String step_10) {
		this.step_10 = step_10;
	}
	public String getStep_11() {
		return step_11;
	}
	public void setStep_11(String step_11) {
		this.step_11 = step_11;
	}
	public String getStep_12() {
		return step_12;
	}
	public void setStep_12(String step_12) {
		this.step_12 = step_12;
	}
	public String getStep_13() {
		return step_13;
	}
	public void setStep_13(String step_13) {
		this.step_13 = step_13;
	}
	public String getStep_14() {
		return step_14;
	}
	public void setStep_14(String step_14) {
		this.step_14 = step_14;
	}
	public String getStep_15() {
		return step_15;
	}
	public void setStep_15(String step_15) {
		this.step_15 = step_15;
	}
	public String getStep_16() {
		return step_16;
	}
	public void setStep_16(String step_16) {
		this.step_16 = step_16;
	}
	public String getStep_17() {
		return step_17;
	}
	public void setStep_17(String step_17) {
		this.step_17 = step_17;
	}
	public String getStep_18() {
		return step_18;
	}
	public void setStep_18(String step_18) {
		this.step_18 = step_18;
	}
	public String getStep_19() {
		return step_19;
	}
	public void setStep_19(String step_19) {
		this.step_19 = step_19;
	}
	public String getStep_20() {
		return step_20;
	}
	public void setStep_20(String step_20) {
		this.step_20 = step_20;
	}
	public int getProcesses_id() {
		return processes_id;
	}
	public void setProcesses_id(int processes_id) {
		this.processes_id = processes_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getProduct_code() {
		return Product_code;
	}
	public void setProduct_code(String product_code) {
		Product_code = product_code;
	}

	
	
	
	
	
}
