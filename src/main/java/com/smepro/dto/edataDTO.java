package com.smepro.dto;

import java.util.List;

public class edataDTO {
	

	private int data_eformid;
	private int data_eformrv;
	
	private String title1;
	private String title2;
	private String title3;
	private String title4;
	private String title5;
	
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String key5;
	
	
	private String data1;
	private String data2;
	private String data3;
	private String data4;
	private String data5;
	
	private String date_start;
	private String date_end;
	
	private String purchase_order;
	private String keyno_purchase_order;
	private String customer;
	private String keyno_customer;
	private String product;
	private String keyno_product;
	private String lot;
	private String keyno_lot;
	private String material;
	private String keyno_material;
	private String lot_material;
	private String keyno_lot_material;
	private String wip;
	private String keyno_wip;
	private String lot_wip;
	private String keyno_lot_wip;
	
	private List<String> jointable;
	private List<String> join;
	
	
	
	
	
	
	public void reset() {
		this.data_eformid = 0;
		this.data_eformrv = 0;
		this.title1 = null;
		this.title2 = null;
		this.title3 = null;
		this.title4 = null;
		this.title5 = null;
		this.key1 = null;
		this.key2 = null;
		this.key3 = null;
		this.key4 = null;
		this.key5 = null;
		this.data1 = null;
		this.data2 = null;
		this.data3 = null;
		this.data4 = null;
		this.data5 = null;
		this.date_start = null;
		this.date_end = null;
	}
	
	
	public int getData_eformid() {
		return data_eformid;
	}
	public void setData_eformid(int data_eformid) {
		this.data_eformid = data_eformid;
	}
	public int getData_eformrv() {
		return data_eformrv;
	}
	public void setData_eformrv(int data_eformrv) {
		this.data_eformrv = data_eformrv;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getTitle4() {
		return title4;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public String getTitle5() {
		return title5;
	}
	public void setTitle5(String title5) {
		this.title5 = title5;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	public String getKey4() {
		return key4;
	}
	public void setKey4(String key4) {
		this.key4 = key4;
	}
	public String getKey5() {
		return key5;
	}
	public void setKey5(String key5) {
		this.key5 = key5;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData4() {
		return data4;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public String getData5() {
		return data5;
	}
	public void setData5(String data5) {
		this.data5 = data5;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}


	public String getPurchase_order() {
		return purchase_order;
	}


	public void setPurchase_order(String purchase_order) {
		this.purchase_order = purchase_order;
	}


	public String getKeyno_purchase_order() {
		return keyno_purchase_order;
	}


	public void setKeyno_purchase_order(String keyno_purchase_order) {
		this.keyno_purchase_order = keyno_purchase_order;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getKeyno_customer() {
		return keyno_customer;
	}


	public void setKeyno_customer(String keyno_customer) {
		this.keyno_customer = keyno_customer;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public String getKeyno_product() {
		return keyno_product;
	}


	public void setKeyno_product(String keyno_product) {
		this.keyno_product = keyno_product;
	}


	public String getLot() {
		return lot;
	}


	public void setLot(String lot) {
		this.lot = lot;
	}


	public String getKeyno_lot() {
		return keyno_lot;
	}


	public void setKeyno_lot(String keyno_lot) {
		this.keyno_lot = keyno_lot;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public String getKeyno_material() {
		return keyno_material;
	}


	public void setKeyno_material(String keyno_material) {
		this.keyno_material = keyno_material;
	}


	public String getLot_material() {
		return lot_material;
	}


	public void setLot_material(String lot_material) {
		this.lot_material = lot_material;
	}


	public String getKeyno_lot_material() {
		return keyno_lot_material;
	}


	public void setKeyno_lot_material(String keyno_lot_material) {
		this.keyno_lot_material = keyno_lot_material;
	}


	public String getWip() {
		return wip;
	}


	public void setWip(String wip) {
		this.wip = wip;
	}


	public String getKeyno_wip() {
		return keyno_wip;
	}


	public void setKeyno_wip(String keyno_wip) {
		this.keyno_wip = keyno_wip;
	}


	public String getLot_wip() {
		return lot_wip;
	}


	public void setLot_wip(String lot_wip) {
		this.lot_wip = lot_wip;
	}


	public String getKeyno_lot_wip() {
		return keyno_lot_wip;
	}


	public void setKeyno_lot_wip(String keyno_lot_wip) {
		this.keyno_lot_wip = keyno_lot_wip;
	}


	public List<String> getJoin() {
		return join;
	}

	public void setJoin(List<String> join) {
		this.join = join;
	}
	
	public List<String> getJointable() {
		return jointable;
	}

	public void setJointable(List<String> jointable) {
		this.jointable = jointable;
	}

	@Override
	public String toString() {
		return String.format(
				"edataDTO [data_eformid=%s, data_eformrv=%s, title1=%s, title2=%s, title3=%s, title4=%s, title5=%s, key1=%s, key2=%s, key3=%s, key4=%s, key5=%s, data1=%s, data2=%s, data3=%s, data4=%s, data5=%s, date_start=%s, date_end=%s, purchase_order=%s, keyno_purchase_order=%s, customer=%s, keyno_customer=%s, product=%s, keyno_product=%s, lot=%s, keyno_lot=%s, material=%s, keyno_material=%s, lot_material=%s, keyno_lot_material=%s, wip=%s, keyno_wip=%s, lot_wip=%s, keyno_lot_wip=%s]",
				data_eformid, data_eformrv, title1, title2, title3, title4, title5, key1, key2, key3, key4, key5, data1,
				data2, data3, data4, data5, date_start, date_end, purchase_order, keyno_purchase_order, customer,
				keyno_customer, product, keyno_product, lot, keyno_lot, material, keyno_material, lot_material,
				keyno_lot_material, wip, keyno_wip, lot_wip, keyno_lot_wip);
	}
	
	
	
	
	
	
	
	
	
}

