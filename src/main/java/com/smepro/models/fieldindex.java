package com.smepro.models;

public class fieldindex {
	
	private int product;
	private int lot;
	
	private int wip;
	private int lot_wip;
	private int  register = 0;
	
	private int fieldindex_id ;
	private int fieldindex_idrefer;
	private int field_index_idcondition;
	private int field_index_idcondition2;
	private String fieldindex_type;
	private int fieldindex_group1;
	private int fieldindex_group2;
	private int good_output;
	private int defective_output;
	

	private int receivede_fg;
	private int handover_fg;
	

	private int use_wip;
	private int receivede_wip;
	private int handover_wip;
	

	private int use_material;
	private int receivede_material;
	private int handover_material;
	
	
	private int manufacturer;
	private int material;
	private int lot_material;
	private int unit;
	private int incoming_or_deliver;
	
	private String department;	
	private int target;

	
	private int po;
	private int date;
	
	private int timestart;
	private int timeend;
	private int customer;
	
	private int pr;
	private int pd;
	private int vc;
	private int batch;
	
	
	
	public int getFieldindex_id() {
		return fieldindex_id;
	}
	public void setFieldindex_id(int fieldindex_id) {
		this.fieldindex_id = fieldindex_id;
	}
	public int getFieldindex_idrefer() {
		return fieldindex_idrefer;
	}
	public void setFieldindex_idrefer(int fieldindex_idrefer) {
		this.fieldindex_idrefer = fieldindex_idrefer;
	}
	public int getField_index_idcondition() {
		return field_index_idcondition;
	}
	public void setField_index_idcondition(int field_index_idcondition) {
		this.field_index_idcondition = field_index_idcondition;
	}
	public String getFieldindex_type() {
		return fieldindex_type;
	}
	public void setFieldindex_type(String fieldindex_type) {
		this.fieldindex_type = fieldindex_type;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	
	public int getFieldindex_group1() {
		return fieldindex_group1;
	}
	public void setFieldindex_group1(int fieldindex_group1) {
		this.fieldindex_group1 = fieldindex_group1;
	}
	public int getFieldindex_group2() {
		return fieldindex_group2;
	}
	public void setFieldindex_group2(int fieldindex_group2) {
		this.fieldindex_group2 = fieldindex_group2;
	}
	public int getGood_output() {
		return good_output;
	}
	public void setGood_output(int good_output) {
		this.good_output = good_output;
	}
	public int getDefective_output() {
		return defective_output;
	}
	public void setDefective_output(int defective_output) {
		this.defective_output = defective_output;
	}
	public int getReceivede_fg() {
		return receivede_fg;
	}
	public void setReceivede_fg(int receivede_fg) {
		this.receivede_fg = receivede_fg;
	}
	public int getHandover_fg() {
		return handover_fg;
	}
	public void setHandover_fg(int handover_fg) {
		this.handover_fg = handover_fg;
	}
	public int getUse_wip() {
		return use_wip;
	}
	public void setUse_wip(int use_wip) {
		this.use_wip = use_wip;
	}
	public int getReceivede_wip() {
		return receivede_wip;
	}
	public void setReceivede_wip(int receivede_wip) {
		this.receivede_wip = receivede_wip;
	}
	public int getHandover_wip() {
		return handover_wip;
	}
	public void setHandover_wip(int handover_wip) {
		this.handover_wip = handover_wip;
	}
	public int getUse_material() {
		return use_material;
	}
	public void setUse_material(int use_material) {
		this.use_material = use_material;
	}
	public int getReceivede_material() {
		return receivede_material;
	}
	public void setReceivede_material(int receivede_material) {
		this.receivede_material = receivede_material;
	}
	public int getHandover_material() {
		return handover_material;
	}
	public void setHandover_material(int handover_material) {
		this.handover_material = handover_material;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getLot() {
		return lot;
	}
	public void setLot(int lot) {
		this.lot = lot;
	}
	public int getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}
	public int getMaterial() {
		return material;
	}
	public void setMaterial(int material) {
		this.material = material;
	}
	public int getLot_material() {
		return lot_material;
	}
	public void setLot_material(int lot_material) {
		this.lot_material = lot_material;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getIncoming_or_deliver() {
		return incoming_or_deliver;
	}
	public void setIncoming_or_deliver(int incoming_or_deliver) {
		this.incoming_or_deliver = incoming_or_deliver;
	}
	public int getPo() {
		return po;
	}
	public void setPo(int po) {
		this.po = po;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getTimestart() {
		return timestart;
	}
	public void setTimestart(int timestart) {
		this.timestart = timestart;
	}
	public int getTimeend() {
		return timeend;
	}
	public void setTimeend(int timeend) {
		this.timeend = timeend;
	}
	public int getField_index_idcondition2() {
		return field_index_idcondition2;
	}
	public void setField_index_idcondition2(int field_index_idcondition2) {
		this.field_index_idcondition2 = field_index_idcondition2;
	}
	public int getCustomer() {
		return customer;
	}
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	public int getWip() {
		return wip;
	}
	public void setWip(int wip) {
		this.wip = wip;
	}
	public int getLot_wip() {
		return lot_wip;
	}
	public void setLot_wip(int lot_wip) {
		this.lot_wip = lot_wip;
	}
	public int getPr() {
		return pr;
	}
	public void setPr(int pr) {
		this.pr = pr;
	}
	public int getPd() {
		return pd;
	}
	public void setPd(int pd) {
		this.pd = pd;
	}
	public int getVc() {
		return vc;
	}
	public void setVc(int vc) {
		this.vc = vc;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public int getRegister() {
		return register;
	}
	public void setRegister(int register) {
		this.register = register;
	}

	
	
	
	
}
