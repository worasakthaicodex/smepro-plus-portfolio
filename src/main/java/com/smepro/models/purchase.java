package com.smepro.models;

import java.util.List;

public class purchase {

	private int id;
	private String description;
	private String unit;
	private Double qty = 0.0;
	private Double estimated =0.0;
	private Double cost =0.0;
	private Double received=0.0;
	
	private Double amount_received =0.0;
	private Double amount_paid=0.0;
	
	
	private int  pr_id; 
	private String pr_date;
	private int  pr_idrefer;
	
	private int po_id ;
	private int item_id;
	private String po_date;
	private int  po_idrefer;
	private int clear_id;
	
	private int distributor_id;
	private int shipping_id;
	
	private String distributor_name;
	private String distributor_address;
	
	private String shipping_name;
	private String requester;	
	private String shipping_address;
	private int po_recipient;
	private int gr_id;
	private String gr_date;
	
	
	private int drawer_id= 0;
	private String drawer_name;
	private String drawer_info;
	
	
	
	private int r_id;
	private String r_date;
	private String r_id_refer;
	
	
	private List<purchase> sub_purchases;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty == null ? 0.0 : qty;
	}
	public Double getEstimated() {
		return estimated;
	}
	public void setEstimated(Double estimated) {
		this.estimated = estimated  == null ? 0.0 : estimated;
	}
	public int getPr_id() {
		return pr_id;
	}
	public void setPr_id(int pr_id) {
		this.pr_id = pr_id;
	}
	public String getPr_date() {
		return pr_date;
	}
	public void setPr_date(String pr_date) {
		this.pr_date = pr_date;
	}
	public int getPr_idrefer() {
		return pr_idrefer;
	}
	public void setPr_idrefer(int pr_idrefer) {
		this.pr_idrefer = pr_idrefer;
	}
	public int getPo_id() {
		return po_id;
	}
	public void setPo_id(int po_id) {
		this.po_id = po_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getPo_date() {
		return po_date;
	}
	public void setPo_date(String po_date) {
		this.po_date = po_date;
	}
	public int getPo_idrefer() {
		return po_idrefer;
	}
	public void setPo_idrefer(int po_idrefer) {
		this.po_idrefer = po_idrefer;
	}
	public int getClear_id() {
		return clear_id;
	}
	public void setClear_id(int clear_id) {
		this.clear_id = clear_id;
	}
	public List<purchase> getSub_purchases() {
		return sub_purchases;
	}
	public void setSub_purchases(List<purchase> sub_purchases) {
		this.sub_purchases = sub_purchases;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost  == null ? 0.0 : cost;
	}
	public int getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}
	public int getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(int shipping_id) {
		this.shipping_id = shipping_id;
	}
	public String getDistributor_name() {
		return distributor_name;
	}
	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}
	public String getDistributor_address() {
		return distributor_address;
	}
	public void setDistributor_address(String distributor_address) {
		this.distributor_address = distributor_address;
	}
	public String getShipping_name() {
		return shipping_name;
	}
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	public int getPo_recipient() {
		return po_recipient;
	}
	public void setPo_recipient(int po_recipient) {
		this.po_recipient = po_recipient;
	}
	public Double getReceived() {
		return received;
	}
	public void setReceived(Double received) {
		this.received = received == null ? 0.0 : received;
	}
	public int getGr_id() {
		return gr_id;
	}
	public void setGr_id(int gr_id) {
		this.gr_id = gr_id;
	}
	public String getGr_date() {
		return gr_date;
	}
	public void setGr_date(String gr_date) {
		this.gr_date = gr_date;
	}
	public Double getAmount_received() {
		return amount_received;
	}
	public void setAmount_received(Double amount_received) {
		this.amount_received = amount_received == null ? 0.0 : amount_received;
	}
	public Double getAmount_paid() {
		return amount_paid;
	}
	public void setAmount_paid(Double amount_paid) {
		this.amount_paid = amount_paid == null ? 0.0 : amount_paid;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	public int getDrawer_id() {
		return drawer_id;
	}
	public void setDrawer_id(int drawer_id) {
		this.drawer_id = drawer_id;
	}
	public String getDrawer_name() {
		return drawer_name;
	}
	public void setDrawer_name(String drawer_name) {
		this.drawer_name = drawer_name;
	}
	public String getDrawer_info() {
		return drawer_info;
	}
	public void setDrawer_info(String drawer_info) {
		this.drawer_info = drawer_info;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public String getR_id_refer() {
		return r_id_refer;
	}
	public void setR_id_refer(String r_id_refer) {
		this.r_id_refer = r_id_refer;
	}
	@Override
	public String toString() {
		return String.format(
				"purchase [id=%s, description=%s, unit=%s, qty=%s, estimated=%s, cost=%s, received=%s, amount_received=%s, amount_paid=%s, pr_id=%s, pr_date=%s, pr_idrefer=%s, po_id=%s, item_id=%s, po_date=%s, po_idrefer=%s, clear_id=%s, distributor_id=%s, shipping_id=%s, distributor_name=%s, distributor_address=%s, shipping_name=%s, requester=%s, shipping_address=%s, po_recipient=%s, gr_id=%s, gr_date=%s, drawer_id=%s, drawer_name=%s, drawer_info=%s, r_id=%s, r_date=%s, r_id_refer=%s, sub_purchases=%s, getId()=%s, getDescription()=%s, getUnit()=%s, getQty()=%s, getEstimated()=%s, getPr_id()=%s, getPr_date()=%s, getPr_idrefer()=%s, getPo_id()=%s, getItem_id()=%s, getPo_date()=%s, getPo_idrefer()=%s, getClear_id()=%s, getSub_purchases()=%s, getCost()=%s, getDistributor_id()=%s, getShipping_id()=%s, getDistributor_name()=%s, getDistributor_address()=%s, getShipping_name()=%s, getShipping_address()=%s, getPo_recipient()=%s, getReceived()=%s, getGr_id()=%s, getGr_date()=%s, getAmount_received()=%s, getAmount_paid()=%s, getRequester()=%s, getDrawer_id()=%s, getDrawer_name()=%s, getDrawer_info()=%s, getR_id()=%s, getR_date()=%s, getR_id_refer()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				id, description, unit, qty, estimated, cost, received, amount_received, amount_paid, pr_id, pr_date,
				pr_idrefer, po_id, item_id, po_date, po_idrefer, clear_id, distributor_id, shipping_id,
				distributor_name, distributor_address, shipping_name, requester, shipping_address, po_recipient, gr_id,
				gr_date, drawer_id, drawer_name, drawer_info, r_id, r_date, r_id_refer, sub_purchases, getId(),
				getDescription(), getUnit(), getQty(), getEstimated(), getPr_id(), getPr_date(), getPr_idrefer(),
				getPo_id(), getItem_id(), getPo_date(), getPo_idrefer(), getClear_id(), getSub_purchases(), getCost(),
				getDistributor_id(), getShipping_id(), getDistributor_name(), getDistributor_address(),
				getShipping_name(), getShipping_address(), getPo_recipient(), getReceived(), getGr_id(), getGr_date(),
				getAmount_received(), getAmount_paid(), getRequester(), getDrawer_id(), getDrawer_name(),
				getDrawer_info(), getR_id(), getR_date(), getR_id_refer(), getClass(), hashCode(), super.toString());
	}
	
	
	
	
}
