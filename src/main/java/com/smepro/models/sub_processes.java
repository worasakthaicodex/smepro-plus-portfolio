package com.smepro.models;

import java.util.List;



public class sub_processes {

	private int  id;
	private int  processe_id;
	private String step;
	private int notify;
	private int record;
	private String description;
	private int id_condition;
	
	
	private String action;
	private int action_type;
	
	
	
	
	
	private int activate_subdata;
	private String maindatacolumn;
	private String filtermain;
	private String subdatacolumn;
	private String filtersub;
	private String subdatacolumn1;
	private String filtersub1;
	private String subdatacolumn2;
	private String filtersub2;
	private String subdatacolumn3;
	private String filtersub3;
	private String subdatacolumn4;
	private String filtersub4;
	private String subdatacolumn5;
	private String filtersub5;
	private String subdatacolumn6;
	private String filtersub6;
	private String subdatacolumn7;
	private String filtersub7;
	
	
	private String input_db1;
	private String input_db2;
	private String input_db3;
	private String input_db4;
	private String input_db5;
	private String input_db6;
	private String input_db7;
	private String input_db8;
	private String input_db9;
	
	
	private int activate_autoid;
	private String autoiddatacolumn;
	private String code_app;
	
	

	
	private int activate_checking_list;
	private String checking_list;
	
	private int activate_editlist;
	private String edit_list;
	

	private int activate_autorid;
	private String code_rid;
	

	private String action_batch_insert;  

	
	private  policies policies;  

	
	private String activate_clear;  
	private String action_custom_clear;  
	private String action_batch_clear;  
	private String action_batch_modify;  
	private String action_batch_continue;  
	private String action_batch_hierarchical;  
	private String action_batch_dreference;
	
	private String activate_reference_only;  
	private String activate_hierarchical_reference;  
	private String activate_reference_modify;  
	private String activate_reference_continue;  
	private String activate_clear_register;  
	private String activate_register_processing;
	private String activate_document_reference;

	

	private String prefix;
	private String batch_code_generation;
	
	
	
	private int  activate_endaction;
	private String  endaction_list;
	
	private int accepting;
	private String prohibited1;
	private String prohibited2;
	private String visibility;
	private String enums;
	
	
	private List<user> user_all;
	private List<department> department_all;
	private List<groups> position_all;
	List<dataset_data> closingStatus;
	List<dataset_data> closingStatusReject;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProcesse_id() {
		return processe_id;
	}
	public void setProcesse_id(int processe_id) {
		this.processe_id = processe_id;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public int getNotify() {
		return notify;
	}
	public void setNotify(int notify) {
		this.notify = notify;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId_condition() {
		return id_condition;
	}
	public void setId_condition(int id_condition) {
		this.id_condition = id_condition;
	}
	public String getMaindatacolumn() {
		return maindatacolumn;
	}
	public void setMaindatacolumn(String maindatacolumn) {
		this.maindatacolumn = maindatacolumn;
	}
	public String getFiltermain() {
		return filtermain;
	}
	public void setFiltermain(String filtermain) {
		this.filtermain = filtermain;
	}
	public String getSubdatacolumn() {
		return subdatacolumn;
	}
	public void setSubdatacolumn(String subdatacolumn) {
		this.subdatacolumn = subdatacolumn;
	}
	public String getFiltersub() {
		return filtersub;
	}
	public void setFiltersub(String filtersub) {
		this.filtersub = filtersub;
	}
	public String getSubdatacolumn2() {
		return subdatacolumn2;
	}
	public void setSubdatacolumn2(String subdatacolumn2) {
		this.subdatacolumn2 = subdatacolumn2;
	}
	public String getFiltersub2() {
		return filtersub2;
	}
	public void setFiltersub2(String filtersub2) {
		this.filtersub2 = filtersub2;
	}
	public String getSubdatacolumn3() {
		return subdatacolumn3;
	}
	public void setSubdatacolumn3(String subdatacolumn3) {
		this.subdatacolumn3 = subdatacolumn3;
	}
	public String getFiltersub3() {
		return filtersub3;
	}
	public void setFiltersub3(String filtersub3) {
		this.filtersub3 = filtersub3;
	}
	public String getAutoiddatacolumn() {
		return autoiddatacolumn;
	}
	public void setAutoiddatacolumn(String autoiddatacolumn) {
		this.autoiddatacolumn = autoiddatacolumn;
	}
	public String getCode_app() {
		return code_app;
	}
	public void setCode_app(String code_app) {
		this.code_app = code_app;
	}
	public int getActivate_subdata() {
		return activate_subdata;
	}
	public void setActivate_subdata(int activate_subdata) {
		this.activate_subdata = activate_subdata;
	}
	public int getActivate_autoid() {
		return activate_autoid;
	}
	public void setActivate_autoid(int activate_autoid) {
		this.activate_autoid = activate_autoid;
	}

	public int getActivate_checking_list() {
		return activate_checking_list;
	}
	public void setActivate_checking_list(int activate_checking_list) {
		this.activate_checking_list = activate_checking_list;
	}
	public String getChecking_list() {
		return checking_list;
	}
	public void setChecking_list(String checking_list) {
		this.checking_list = checking_list;
	}
	public int getActivate_editlist() {
		return activate_editlist;
	}
	public void setActivate_editlist(int activate_editlist) {
		this.activate_editlist = activate_editlist;
	}
	public String getEdit_list() {
		return edit_list;
	}
	public void setEdit_list(String edit_list) {
		this.edit_list = edit_list;
	}
	public int getActivate_autorid() {
		return activate_autorid;
	}
	public void setActivate_autorid(int activate_autorid) {
		this.activate_autorid = activate_autorid;
	}
	public String getCode_rid() {
		return code_rid;
	}
	public void setCode_rid(String code_rid) {
		this.code_rid = code_rid;
	}

	public String getAction_batch_insert() {
		return action_batch_insert;
	}
	public void setAction_batch_insert(String action_batch_insert) {
		this.action_batch_insert = action_batch_insert;
	}
	public policies getPolicies() {
		return policies;
	}
	public void setPolicies(policies policies) {
		this.policies = policies;
	}
	public String getActivate_clear() {
		return activate_clear;
	}
	public void setActivate_clear(String activate_clear) {
		this.activate_clear = activate_clear;
	}
	public String getAction_custom_clear() {
		return action_custom_clear;
	}
	public void setAction_custom_clear(String action_custom_clear) {
		this.action_custom_clear = action_custom_clear;
	}
	public String getAction_batch_clear() {
		return action_batch_clear;
	}
	public void setAction_batch_clear(String action_batch_clear) {
		this.action_batch_clear = action_batch_clear;
	}
	public String getActivate_reference_only() {
		return activate_reference_only;
	}
	public void setActivate_reference_only(String activate_reference_only) {
		this.activate_reference_only = activate_reference_only;
	}
	public String getActivate_hierarchical_reference() {
		return activate_hierarchical_reference;
	}
	public void setActivate_hierarchical_reference(String activate_hierarchical_reference) {
		this.activate_hierarchical_reference = activate_hierarchical_reference;
	}
	public String getActivate_reference_modify() {
		return activate_reference_modify;
	}
	public void setActivate_reference_modify(String activate_reference_modify) {
		this.activate_reference_modify = activate_reference_modify;
	}
	public String getActivate_reference_continue() {
		return activate_reference_continue;
	}
	public void setActivate_reference_continue(String activate_reference_continue) {
		this.activate_reference_continue = activate_reference_continue;
	}
	public String getAction_batch_modify() {
		return action_batch_modify;
	}
	public void setAction_batch_modify(String action_batch_modify) {
		this.action_batch_modify = action_batch_modify;
	}
	public String getAction_batch_continue() {
		return action_batch_continue;
	}
	public void setAction_batch_continue(String action_batch_continue) {
		this.action_batch_continue = action_batch_continue;
	}
	public String getActivate_clear_register() {
		return activate_clear_register;
	}
	public void setActivate_clear_register(String activate_clear_register) {
		this.activate_clear_register = activate_clear_register;
	}
	public String getActivate_register_processing() {
		return activate_register_processing;
	}
	public void setActivate_register_processing(String activate_register_processing) {
		this.activate_register_processing = activate_register_processing;
	}
	public String getAction_batch_hierarchical() {
		return action_batch_hierarchical;
	}
	public void setAction_batch_hierarchical(String action_batch_hierarchical) {
		this.action_batch_hierarchical = action_batch_hierarchical;
	}
	public String getActivate_document_reference() {
		return activate_document_reference;
	}
	public void setActivate_document_reference(String activate_document_reference) {
		this.activate_document_reference = activate_document_reference;
	}
	public String getAction_batch_dreference() {
		return action_batch_dreference;
	}
	public void setAction_batch_dreference(String action_batch_dreference) {
		this.action_batch_dreference = action_batch_dreference;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getBatch_code_generation() {
		return batch_code_generation;
	}
	public void setBatch_code_generation(String batch_code_generation) {
		this.batch_code_generation = batch_code_generation;
	}
	public String getSubdatacolumn4() {
		return subdatacolumn4;
	}
	public void setSubdatacolumn4(String subdatacolumn4) {
		this.subdatacolumn4 = subdatacolumn4;
	}
	public String getFiltersub4() {
		return filtersub4;
	}
	public void setFiltersub4(String filtersub4) {
		this.filtersub4 = filtersub4;
	}
	public String getSubdatacolumn5() {
		return subdatacolumn5;
	}
	public void setSubdatacolumn5(String subdatacolumn5) {
		this.subdatacolumn5 = subdatacolumn5;
	}
	public String getFiltersub5() {
		return filtersub5;
	}
	public void setFiltersub5(String filtersub5) {
		this.filtersub5 = filtersub5;
	}
	public int getActivate_endaction() {
		return activate_endaction;
	}
	public void setActivate_endaction(int activate_endaction) {
		this.activate_endaction = activate_endaction;
	}
	public String getEndaction_list() {
		return endaction_list;
	}
	public void setEndaction_list(String endaction_list) {
		this.endaction_list = endaction_list;
	}
	public String getSubdatacolumn1() {
		return subdatacolumn1;
	}
	public void setSubdatacolumn1(String subdatacolumn1) {
		this.subdatacolumn1 = subdatacolumn1;
	}
	public String getFiltersub1() {
		return filtersub1;
	}
	public void setFiltersub1(String filtersub1) {
		this.filtersub1 = filtersub1;
	}
	public String getSubdatacolumn6() {
		return subdatacolumn6;
	}
	public void setSubdatacolumn6(String subdatacolumn6) {
		this.subdatacolumn6 = subdatacolumn6;
	}
	public String getFiltersub6() {
		return filtersub6;
	}
	public void setFiltersub6(String filtersub6) {
		this.filtersub6 = filtersub6;
	}
	public String getSubdatacolumn7() {
		return subdatacolumn7;
	}
	public void setSubdatacolumn7(String subdatacolumn7) {
		this.subdatacolumn7 = subdatacolumn7;
	}
	public String getFiltersub7() {
		return filtersub7;
	}
	public void setFiltersub7(String filtersub7) {
		this.filtersub7 = filtersub7;
	}
	public String getInput_db1() {
		return input_db1;
	}
	public void setInput_db1(String input_db1) {
		this.input_db1 = input_db1;
	}
	public String getInput_db2() {
		return input_db2;
	}
	public void setInput_db2(String input_db2) {
		this.input_db2 = input_db2;
	}
	public String getInput_db3() {
		return input_db3;
	}
	public void setInput_db3(String input_db3) {
		this.input_db3 = input_db3;
	}
	public String getInput_db4() {
		return input_db4;
	}
	public void setInput_db4(String input_db4) {
		this.input_db4 = input_db4;
	}
	public String getInput_db5() {
		return input_db5;
	}
	public void setInput_db5(String input_db5) {
		this.input_db5 = input_db5;
	}
	public String getInput_db6() {
		return input_db6;
	}
	public void setInput_db6(String input_db6) {
		this.input_db6 = input_db6;
	}
	public String getInput_db7() {
		return input_db7;
	}
	public void setInput_db7(String input_db7) {
		this.input_db7 = input_db7;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getAction_type() {
		return action_type;
	}
	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}
	public String getInput_db8() {
		return input_db8;
	}
	public void setInput_db8(String input_db8) {
		this.input_db8 = input_db8;
	}
	public String getInput_db9() {
		return input_db9;
	}
	public void setInput_db9(String input_db9) {
		this.input_db9 = input_db9;
	}
	public int getAccepting() {
		return accepting;
	}
	public void setAccepting(int accepting) {
		this.accepting = accepting;
	}
	public String getProhibited1() {
		return prohibited1;
	}
	public void setProhibited1(String prohibited1) {
		this.prohibited1 = prohibited1;
	}
	public String getProhibited2() {
		return prohibited2;
	}
	public void setProhibited2(String prohibited2) {
		this.prohibited2 = prohibited2;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getEnums() {
		return enums;
	}
	public void setEnums(String enums) {
		this.enums = enums;
	}
	public List<user> getUser_all() {
		return user_all;
	}
	public void setUser_all(List<user> user_all) {
		this.user_all = user_all;
	}
	public List<department> getDepartment_all() {
		return department_all;
	}
	public void setDepartment_all(List<department> department_all) {
		this.department_all = department_all;
	}
	public List<groups> getPosition_all() {
		return position_all;
	}
	public void setPosition_all(List<groups> position_all) {
		this.position_all = position_all;
	}
	public List<dataset_data> getClosingStatus() {
		return closingStatus;
	}
	public void setClosingStatus(List<dataset_data> closingStatus) {
		this.closingStatus = closingStatus;
	}
	public List<dataset_data> getClosingStatusReject() {
		return closingStatusReject;
	}
	public void setClosingStatusReject(List<dataset_data> closingStatusReject) {
		this.closingStatusReject = closingStatusReject;
	}

	
}
