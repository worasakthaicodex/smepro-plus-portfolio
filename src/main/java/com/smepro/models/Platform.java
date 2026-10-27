package com.smepro.models;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Platform {
	private int app_sub_processes_id;
	private int app_processes_id;
	private int app_item_id;
	private int app_item_id2;
	private int app_step;
	private int app_workflow_id;
	private String grouping;
	private int relation_id;
	private String search;
	private int tab_id;
	
	private int audit;
	private PrepareReport reportconfig;
	private UseformPrint useformPrint;
	private String workflow_date_end;
	
	
	private String app_tempId;
	private String app_tempitemid;
	private String  appCode;
	private edata edata;
	private String  app_entity_type;
	private String  name_file;
	private String  app_model;
	
	
	private String  workflow_password;
	private String  workflow_clear;
	private int  workflow_step;
	private int  workflow_to;

	private int  workflow_send;
	private int  workflow_ticket_edit;
	private int workflow_appproved;
	private String  workflow_closing_status;
	
	private BatchEdata batchEdata;
	
	private String  workflow_remark;
	private String  workflow_remarks_rejected;
	
	
	
	private int  extension_process;
	private CommonsMultipartFile[] files;
	private MultipartFile file;
	
	
	private folder  patch;
	private String  realPath;
	private String  edata_from;
	private Segmentation segmentation;
	private List<FilterChain> queryChains;
	private FilterChain filterChain;
	private List<Integer> withinId;
	private List<Integer> notWithinId;
	private String config;
	
	
	
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
		this.audit = audit;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
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
	public List<FilterChain> getQueryChains() {
		return queryChains;
	}
	public void setQueryChains(List<FilterChain> queryChains) {
		this.queryChains = queryChains;
	}
	public FilterChain getFilterChain() {
		return filterChain;
	}
	public void setFilterChain(FilterChain filterChain) {
		this.filterChain = filterChain;
	}
	public List<Integer> getWithinId() {
		return withinId;
	}
	public void setWithinId(List<Integer> withinId) {
		this.withinId = withinId;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getWorkflow_closing_status() {
		return workflow_closing_status;
	}
	public void setWorkflow_closing_status(String workflow_closing_status) {
		this.workflow_closing_status = workflow_closing_status;
	}
	public int getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}
	public BatchEdata getBatchEdata() {
		return batchEdata;
	}
	public void setBatchEdata(BatchEdata batchEdata) {
		this.batchEdata = batchEdata;
	}
	public String getGrouping() {
		return grouping;
	}
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	public PrepareReport getReportconfig() {
		return reportconfig;
	}
	public void setReportconfig(PrepareReport reportconfig) {
		this.reportconfig = reportconfig;
	}
	public List<Integer> getNotWithinId() {
		return notWithinId;
	}
	public void setNotWithinId(List<Integer> notWithinId) {
		this.notWithinId = notWithinId;
	}
	public int getTab_id() {
		return tab_id;
	}
	public void setTab_id(int tab_id) {
		this.tab_id = tab_id;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public int getApp_item_id2() {
		return app_item_id2;
	}
	public void setApp_item_id2(int app_item_id2) {
		this.app_item_id2 = app_item_id2;
	}
	public UseformPrint getUseformPrint() {
		return useformPrint;
	}
	public void setUseformPrint(UseformPrint useformPrint) {
		this.useformPrint = useformPrint;
	}
	public String getWorkflow_clear() {
		return workflow_clear;
	}
	public void setWorkflow_clear(String workflow_clear) {
		this.workflow_clear = workflow_clear;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getWorkflow_remarks_rejected() {
		return workflow_remarks_rejected;
	}
	public void setWorkflow_remarks_rejected(String workflow_remarks_rejected) {
		this.workflow_remarks_rejected = workflow_remarks_rejected;
	}
	public int getWorkflow_ticket_edit() {
		return workflow_ticket_edit;
	}
	public void setWorkflow_ticket_edit(int workflow_ticket_edit) {
		this.workflow_ticket_edit = workflow_ticket_edit;
	}
	public String getWorkflow_date_end() {
		return workflow_date_end;
	}
	public void setWorkflow_date_end(String workflow_date_end) {
		this.workflow_date_end = workflow_date_end;
	}
	
	
	
}
