package com.smepro.models;

import java.util.List;

public class datalink {

	

	private  int  datalink_id;
	private  int  datalink_idrefer;
	private  int  datalink_workflow;
	private  String  datalink_name;
	private  int  key1;
	private  int  key2;
	private  int  key3;
	private  List<edata>  edatas;
	private  String  condition;
	

	
	
	
	public int getDatalink_id() {
		return datalink_id;
	}
	public void setDatalink_id(int datalink_id) {
		this.datalink_id = datalink_id;
	}
	public int getDatalink_idrefer() {
		return datalink_idrefer;
	}
	public void setDatalink_idrefer(int datalink_idrefer) {
		this.datalink_idrefer = datalink_idrefer;
	}
	public int getDatalink_workflow() {
		return datalink_workflow;
	}
	public void setDatalink_workflow(int datalink_workflow) {
		this.datalink_workflow = datalink_workflow;
	}
	public String getDatalink_name() {
		return datalink_name;
	}
	public void setDatalink_name(String datalink_name) {
		this.datalink_name = datalink_name;
	}
	public int getKey1() {
		return key1;
	}
	public void setKey1(int key1) {
		this.key1 = key1;
	}
	public int getKey2() {
		return key2;
	}
	public void setKey2(int key2) {
		this.key2 = key2;
	}
	public int getKey3() {
		return key3;
	}
	public void setKey3(int key3) {
		this.key3 = key3;
	}
	public List<edata> getEdatas() {
		return edatas;
	}
	public void setEdatas(List<edata> edatas) {
		this.edatas = edatas;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	
	
}
