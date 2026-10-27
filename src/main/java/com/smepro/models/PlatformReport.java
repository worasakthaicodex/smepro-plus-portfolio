package com.smepro.models;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PlatformReport {
	private int app_sub_processes_id;
	private int app_processes_id;
	private int app_item_id;
	private int app_id_refer;
	private int app_step;
	private int app_workflow_id;
	private int process_refer;
	private String app_tempId;
	private String app_tempitemid;
	private edata edata;
	private String  app_entity_type;
	private String  name_file;
	private String  app_model;
	private FilterCriteria filterCriteria;
	
	private String  workflow_password;
	private int  workflow_step;
	private int  workflow_to;
	private int  workflow_send;
	private int workflow_appproved;
	private String  workflow_remark;
	private String  workflow_status_close;
	private String  workflow_status_close_from;
	
	private int  extension_process;
	private CommonsMultipartFile[] files;
	private folder  patch;
	private String  realPath;
	private String  edata_from;
	private Segmentation segmentation;
	private FilterChain  filterChain;
	private QuickQuery quickQuery;
	private int from;
	private String from_type;
	
	private String remove_string_id;
	private String process_refer_name;
	
	
	
	
	
	public int getProcess_refer() {
		return process_refer;
	}
	public void setProcess_refer(int process_refer) {
		this.process_refer = process_refer;
	}
	public folder getPatch() {
		return patch;
	}
	public void setPatch(folder patch) {
		this.patch = patch;
	}
	public int getApp_sub_processes_id() {
		return app_sub_processes_id;
	}
	public void setApp_sub_processes_id(int app_sub_processes_id) {
		this.app_sub_processes_id = app_sub_processes_id;
	}
	public int getApp_processes_id() {
		return app_processes_id;
	}
	public void setApp_processes_id(int app_processes_id) {
		this.app_processes_id = app_processes_id;
	}
	public String getApp_tempId() {
		return app_tempId;
	}
	public void setApp_tempId(String app_tempId) {
		this.app_tempId = app_tempId;
	}
	public edata getEdata() {
		return edata;
	}
	public void setEdata(edata edata) {
		this.edata = edata;
	}
	public int getApp_item_id() {
		return app_item_id;
	}
	public void setApp_item_id(int app_item_id) {
		this.app_item_id = app_item_id;
	}
	public String getApp_tempitemid() {
		return app_tempitemid;
	}
	public void setApp_tempitemid(String app_tempitemid) {
		this.app_tempitemid = app_tempitemid;
	}
	public String getApp_entity_type() {
		return app_entity_type;
	}
	public void setApp_entity_type(String app_entity_type) {
		this.app_entity_type = app_entity_type;
	}
	public String getName_file() {
		return name_file;
	}
	public void setName_file(String name_file) {
		this.name_file = name_file;
	}
	public int getApp_step() {
		return app_step;
	}
	public void setApp_step(int app_step) {
		this.app_step = app_step;
	}
	public int getApp_workflow_id() {
		return app_workflow_id;
	}
	public void setApp_workflow_id(int app_workflow_id) {
		this.app_workflow_id = app_workflow_id;
	}
	public String getWorkflow_password() {
		return workflow_password;
	}
	public void setWorkflow_password(String workflow_password) {
		this.workflow_password = workflow_password;
	}
	public int getWorkflow_to() {
		return workflow_to;
	}
	public void setWorkflow_to(int workflow_to) {
		this.workflow_to = workflow_to;
	}
	public int getWorkflow_send() {
		return workflow_send;
	}
	public void setWorkflow_send(int workflow_send) {
		this.workflow_send = workflow_send;
	}
	public int getWorkflow_step() {
		return workflow_step;
	}
	public void setWorkflow_step(int workflow_step) {
		this.workflow_step = workflow_step;
	}
	public int getWorkflow_appproved() {
		return workflow_appproved;
	}
	public void setWorkflow_appproved(int workflow_appproved) {
		this.workflow_appproved = workflow_appproved;
	}
	public String getWorkflow_remark() {
		return workflow_remark;
	}
	public void setWorkflow_remark(String workflow_remark) {
		this.workflow_remark = workflow_remark;
	}
	public int getExtension_process() {
		return extension_process;
	}
	public void setExtension_process(int extension_process) {
		this.extension_process = extension_process;
	}
	public CommonsMultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}
	public String getApp_model() {
		return app_model;
	}
	public void setApp_model(String app_model) {
		this.app_model = app_model;
	}
	public String getEdata_from() {
		return edata_from;
	}
	public void setEdata_from(String edata_from) {
		this.edata_from = edata_from;
	}
	public Segmentation getSegmentation() {
		return segmentation;
	}
	public void setSegmentation(Segmentation segmentation) {
		this.segmentation = segmentation;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public String getFrom_type() {
		return from_type;
	}
	public void setFrom_type(String from_type) {
		this.from_type = from_type;
	}
	public int getApp_id_refer() {
		return app_id_refer;
	}
	public void setApp_id_refer(int app_id_refer) {
		this.app_id_refer = app_id_refer;
	}
	public FilterCriteria getFilterCriteria() {
		return filterCriteria;
	}
	public void setFilterCriteria(FilterCriteria filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	public FilterChain getFilterChain() {
		return filterChain;
	}
	public void setFilterChain(FilterChain filterChain) {
		this.filterChain = filterChain;
	}
	public String getRemove_string_id() {
		return remove_string_id;
	}
	public void setRemove_string_id(String remove_string_id) {
		this.remove_string_id = remove_string_id;
	}
	public String getProcess_refer_name() {
		return process_refer_name;
	}
	public void setProcess_refer_name(String process_refer_name) {
		this.process_refer_name = process_refer_name;
	}
	public QuickQuery getQuickQuery() {
		return quickQuery;
	}
	public void setQuickQuery(QuickQuery quickQuery) {
		this.quickQuery = quickQuery;
	}
	public String getWorkflow_status_close() {
		return workflow_status_close;
	}
	public void setWorkflow_status_close(String workflow_status_close) {
		this.workflow_status_close = workflow_status_close;
	}
	public String getWorkflow_status_close_from() {
		return workflow_status_close_from;
	}
	public void setWorkflow_status_close_from(String workflow_status_close_from) {
		this.workflow_status_close_from = workflow_status_close_from;
	}
	
	
	
}
