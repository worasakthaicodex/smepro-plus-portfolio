package com.smepro.models;

import javax.servlet.http.HttpSession;

public class WorkSystemdata {
	private edata_stock edata_stock;
	private  String datalink_name;
	private SubData subdata;
	
	private HttpSession session;
	
	
	public edata_stock getEdata_stock() {
		return edata_stock;
	}

	public void setEdata_stock(edata_stock edata_stock) {
		this.edata_stock = edata_stock;
	}

	public String getDatalink_name() {
		return datalink_name;
	}

	public void setDatalink_name(String datalink_name) {
		this.datalink_name = datalink_name;
	}

	public SubData getSubdata() {
		return subdata;
	}

	public void setSubdata(SubData subdata) {
		this.subdata = subdata;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
	
}
