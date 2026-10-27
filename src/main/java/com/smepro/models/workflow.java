package com.smepro.models;

import java.util.List;

public class workflow {

	
	private int workflow_id;
	private String workflow_name;
	private String workflow_note;
	private String workflow_dateadd;
	private String workflow_key1;
	private String workflow_key2;
	private String workflow_key3;
	private String workflow_key4;
	private int workflow_type;
	private int workflow_idadmin;
	private int workflow_idadd;
	private String workflow_app;
	private String workflow_folder;
	private String workflow_location;
	private String workflow_maindb;
	private String workflow_subdb;
	private String workflow_subdb2;
	private String workflow_subdb3;
	
	
	private List<upload_pn> upload_pn;
	private List<process> process;
	
	
	private String tracking_step;
	private int tracking_status;
	private String tracking_type;
	private String tracking_process_type;
	private int traceability_status;
	
	
	private String tracking_main_step;
	private int tracking_main_status;
	private String tracking_main_type;
	private String workflow_join;
	
	
	public workflow() {
		super();
		this.workflow_id = 0;
		this.workflow_name = "";
		this.workflow_note= "";
		this.workflow_dateadd= "";
		this.workflow_key1 = "";
		this.workflow_key2 = "";
		this.workflow_key3 = "";
		this.workflow_key4 = "";
		this.workflow_type = 0;
	}
	
	
	public int getWorkflow_id() {
		return workflow_id;
	}
	public void setWorkflow_id(int workflow_id) {
		this.workflow_id = workflow_id;
	}
	public String getWorkflow_name() {
		return workflow_name;
	}
	public void setWorkflow_name(String workflow_name) {
		this.workflow_name = workflow_name;
	}
	public String getWorkflow_note() {
		return workflow_note;
	}
	public void setWorkflow_note(String workflow_note) {
		this.workflow_note = workflow_note;
	}
	public String getWorkflow_dateadd() {
		return workflow_dateadd;
	}
	public void setWorkflow_dateadd(String workflow_dateadd) {
		this.workflow_dateadd = workflow_dateadd;
	}
	public String getWorkflow_key1() {
		return workflow_key1;
	}
	public void setWorkflow_key1(String workflow_key1) {
		this.workflow_key1 = workflow_key1;
	}
	public String getWorkflow_key2() {
		return workflow_key2;
	}
	public void setWorkflow_key2(String workflow_key2) {
		this.workflow_key2 = workflow_key2;
	}
	public String getWorkflow_key3() {
		return workflow_key3;
	}
	public void setWorkflow_key3(String workflow_key3) {
		this.workflow_key3 = workflow_key3;
	}
	public String getWorkflow_key4() {
		return workflow_key4;
	}
	public void setWorkflow_key4(String workflow_key4) {
		this.workflow_key4 = workflow_key4;
	}
	public int getWorkflow_type() {
		return workflow_type;
	}
	public void setWorkflow_type(int workflow_type) {
		this.workflow_type = workflow_type;
	}


	public List<upload_pn> getUpload_pn() {
		return upload_pn;
	}


	public void setUpload_pn(List<upload_pn> upload_pn) {
		this.upload_pn = upload_pn;
	}


	public int getWorkflow_idadmin() {
		return workflow_idadmin;
	}


	public void setWorkflow_idadmin(int workflow_idadmin) {
		this.workflow_idadmin = workflow_idadmin;
	}


	public int getWorkflow_idadd() {
		return workflow_idadd;
	}


	public void setWorkflow_idadd(int workflow_idadd) {
		this.workflow_idadd = workflow_idadd;
	}


	public String getWorkflow_app() {
		return workflow_app;
	}


	public void setWorkflow_app(String workflow_app) {
		this.workflow_app = workflow_app;
	}


	public String getWorkflow_folder() {
		return workflow_folder;
	}


	public void setWorkflow_folder(String workflow_folder) {
		this.workflow_folder = workflow_folder;
	}


	public String getWorkflow_location() {
		return workflow_location;
	}


	public void setWorkflow_location(String workflow_location) {
		this.workflow_location = workflow_location;
	}


	public String getWorkflow_maindb() {
		return workflow_maindb;
	}


	public void setWorkflow_maindb(String workflow_maindb) {
		this.workflow_maindb = workflow_maindb;
	}


	public String getWorkflow_subdb() {
		return workflow_subdb;
	}


	public void setWorkflow_subdb(String workflow_subdb) {
		this.workflow_subdb = workflow_subdb;
	}


	public List<process> getProcess() {
		return process;
	}


	public void setProcess(List<process> process) {
		this.process = process;
	}


	public String getWorkflow_subdb2() {
		return workflow_subdb2;
	}


	public void setWorkflow_subdb2(String workflow_subdb2) {
		this.workflow_subdb2 = workflow_subdb2;
	}


	public String getWorkflow_subdb3() {
		return workflow_subdb3;
	}


	public void setWorkflow_subdb3(String workflow_subdb3) {
		this.workflow_subdb3 = workflow_subdb3;
	}


	public String getTracking_step() {
		return tracking_step;
	}


	public void setTracking_step(String tracking_step) {
		this.tracking_step = tracking_step;
	}


	public int getTracking_status() {
		return tracking_status;
	}


	public void setTracking_status(int tracking_status) {
		this.tracking_status = tracking_status;
	}


	public String getTracking_type() {
		return tracking_type;
	}


	public void setTracking_type(String tracking_type) {
		this.tracking_type = tracking_type;
	}


	public String getTracking_process_type() {
		return tracking_process_type;
	}


	public void setTracking_process_type(String tracking_process_type) {
		this.tracking_process_type = tracking_process_type;
	}


	public int getTraceability_status() {
		return traceability_status;
	}


	public void setTraceability_status(int traceability_status) {
		this.traceability_status = traceability_status;
	}


	public String getTracking_main_step() {
		return tracking_main_step;
	}


	public void setTracking_main_step(String tracking_main_step) {
		this.tracking_main_step = tracking_main_step;
	}


	public int getTracking_main_status() {
		return tracking_main_status;
	}


	public void setTracking_main_status(int tracking_main_status) {
		this.tracking_main_status = tracking_main_status;
	}


	public String getTracking_main_type() {
		return tracking_main_type;
	}


	public void setTracking_main_type(String tracking_main_type) {
		this.tracking_main_type = tracking_main_type;
	}


	public String getWorkflow_join() {
		return workflow_join;
	}


	public void setWorkflow_join(String workflow_join) {
		this.workflow_join = workflow_join;
	}
	
	
	
}

