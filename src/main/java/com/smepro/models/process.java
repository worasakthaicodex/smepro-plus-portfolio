package com.smepro.models;

import java.util.List;

import com.smepro.entity.GamificationGoal;

public class process {
	
	
	private int id;
	private String  version;
	private int logflow;


	
	private String note;
	private String process_logo;
	
	
	private edata edata;
	private edata edatanot;
	private upload_pn upload_pn;
	private log log;
	private condition condition;
	private user user;
	private String table;
 
	private String name;
	private String description;
	private String relatedpeople;
	private int id_refer;
	private int id_condition;
	private List<policies> policies;
	private  List<sub_processes> sub_processes;
	private List<process> processs;
	private List<eformfield> eformfields;
	private int process_join;
	private String reference;
	private String data_dateupdate; 
	private String data_status;
	private List<StatusSummary>  statusSummary;
	private int alert;
	private String visibility;
	private int allow_loose_link = 0;
	
	private int link_to;
	private String relationship;
	
	
	private int outgoing_column1;
	private int for_column1;
	private String feed_type1;
	private int outgoing_column2;
	private int for_column2;
	private String feed_type2;
	private int outgoing_column3;
	private int for_column3;
	private String feed_type3;
	private String  enums;
	private int direct_data_edit_mode;
	
	private List<Row_calculation> rowcalculation; 
	private List<Footer_calculation> footercalculation;
	private List<Links> links;
	private List<Links> inlinks;
	private List<UseExtension> extension;
	private List<Management> managements; 
	private int program_type;
	
	
	

	private String  register;
	private String  register_type;
	private String  access_user;
	private String  access_admin;
	private int  show_limit;
	private int  limit_head;
	
	private String  listview;
	private String  listview2;
	
	
	private int  fieldset;
	private int  tabform;
	private int  used_attachment;
	private int  used_valid;
	private int  ALLOW_ADD;
	private int  ALLOW_DELETE;
	
	
	private String  closing_status;
	
	private String  data_transfer;
	private String  integrity;
	private String  current_status;
	private String shortcut_newdoc_change_closing_status;
	private String closing_status_notsend;
	private String subtable_viewmode;
	private String subtable_editmode;
	
	
	private int canReviseConfirmed;
	private String closingStatusCanRevise;
	private String closingStatusAfterRevise;
	private String  closingStatusAfterAdd;
	
	private SetSegmentView  setSegmentView;
	
	
	
	
	private int  requestsystem;
	private int renamefile;
	
	private String  use_ex;
	private int use_index_app;
	
	private String use_ex_name;
	private String use_ex_icon;

	private String closing_status_expired;
	private int  expires_in_days;
	private int  is_acknowledge_required; 
	
	private String profile_column;
	private String product_master_column;
	
	
	private String closing_evidence_type;
	private String is_attachment_required_to_close;
	private int  can_refuse_and_return; 
	
	
	private String edata_idoc_grade_ref;
	private String applied_status_array;
	private String is_ex_movement_active;
	private int  cycle_duration; 
	private String grade_frequency_mapping;
	private String sub_process_expired;
	
	
	
	
	
	
	public int getAlert() {
		return alert;
	}
	public void setAlert(int alert) {
		this.alert = alert;
	}
	private List<edata_production_record> edata_production_record;
	private List<other> other;
	private List<GamificationGoal> goal;
	
	public List<sub_processes> getSub_processes() {
		return sub_processes;
	}
	public void setSub_processes(List<sub_processes> sub_processes) {
		this.sub_processes = sub_processes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLogflow() {
		return logflow;
	}
	public void setLogflow(int logflow) {
		this.logflow = logflow;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public edata getEdata() {
		return edata;
	}
	public void setEdata(edata edata) {
		this.edata = edata;
	}
	public edata getEdatanot() {
		return edatanot;
	}
	public void setEdatanot(edata edatanot) {
		this.edatanot = edatanot;
	}
	public upload_pn getUpload_pn() {
		return upload_pn;
	}
	public void setUpload_pn(upload_pn upload_pn) {
		this.upload_pn = upload_pn;
	}
	public log getLog() {
		return log;
	}
	public void setLog(log log) {
		this.log = log;
	}
	public condition getCondition() {
		return condition;
	}
	public void setCondition(condition condition) {
		this.condition = condition;
	}
	public user getUser() {
		return user;
	}
	public void setUser(user user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRelatedpeople() {
		return relatedpeople;
	}
	public void setRelatedpeople(String relatedpeople) {
		this.relatedpeople = relatedpeople;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public int getId_condition() {
		return id_condition;
	}
	public void setId_condition(int id_condition) {
		this.id_condition = id_condition;
	}
	public List<policies> getPolicies() {
		return policies;
	}
	public void setPolicies(List<policies> policies) {
		this.policies = policies;
	}
	public List<eformfield> getEformfields() {
		return eformfields;
	}
	public void setEformfields(List<eformfield> eformfields) {
		this.eformfields = eformfields;
	}
	public int getProcess_join() {
		return process_join;
	}
	public void setProcess_join(int process_join) {
		this.process_join = process_join;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getProcess_logo() {
		return process_logo;
	}
	public void setProcess_logo(String process_logo) {
		this.process_logo = process_logo;
	}
	public String getData_dateupdate() {
		return data_dateupdate;
	}
	public void setData_dateupdate(String data_dateupdate) {
		this.data_dateupdate = data_dateupdate;
	}
	public String getData_status() {
		return data_status;
	}
	public void setData_status(String data_status) {
		this.data_status = data_status;
	}
	public List<process> getProcesss() {
		return processs;
	}
	public void setProcesss(List<process> processs) {
		this.processs = processs;
	}
	public List<StatusSummary> getStatusSummary() {
		return statusSummary;
	}
	public void setStatusSummary(List<StatusSummary> statusSummary) {
		this.statusSummary = statusSummary;
	}
	public List<edata_production_record> getEdata_production_record() {
		return edata_production_record;
	}
	public void setEdata_production_record(List<edata_production_record> edata_production_record) {
		this.edata_production_record = edata_production_record;
	}
	public List<other> getOther() {
		return other;
	}
	public void setOther(List<other> other) {
		this.other = other;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}

	public List<GamificationGoal> getGoal() {
		return goal;
	}
	public void setGoal(List<GamificationGoal> goal) {
		this.goal = goal;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public int getAllow_loose_link() {
		return allow_loose_link;
	}
	public void setAllow_loose_link(int allow_loose_link) {
		this.allow_loose_link = allow_loose_link;
	}
	public List<Row_calculation> getRowcalculation() {
		return rowcalculation;
	}
	public void setRowcalculation(List<Row_calculation> rowcalculation) {
		this.rowcalculation = rowcalculation;
	}
	public List<Footer_calculation> getFootercalculation() {
		return footercalculation;
	}
	public void setFootercalculation(List<Footer_calculation> footercalculation) {
		this.footercalculation = footercalculation;
	}
	public int getLink_to() {
		return link_to;
	}
	public void setLink_to(int link_to) {
		this.link_to = link_to;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public List<Links> getLinks() {
		return links;
	}
	public void setLinks(List<Links> links) {
		this.links = links;
	}
	public int getOutgoing_column1() {
		return outgoing_column1;
	}
	public void setOutgoing_column1(int outgoing_column1) {
		this.outgoing_column1 = outgoing_column1;
	}
	public int getFor_column1() {
		return for_column1;
	}
	public void setFor_column1(int for_column1) {
		this.for_column1 = for_column1;
	}
	public String getFeed_type1() {
		return feed_type1;
	}
	public void setFeed_type1(String feed_type1) {
		this.feed_type1 = feed_type1;
	}
	public int getOutgoing_column2() {
		return outgoing_column2;
	}
	public void setOutgoing_column2(int outgoing_column2) {
		this.outgoing_column2 = outgoing_column2;
	}
	public int getFor_column2() {
		return for_column2;
	}
	public void setFor_column2(int for_column2) {
		this.for_column2 = for_column2;
	}
	public String getFeed_type2() {
		return feed_type2;
	}
	public void setFeed_type2(String feed_type2) {
		this.feed_type2 = feed_type2;
	}
	public int getOutgoing_column3() {
		return outgoing_column3;
	}
	public void setOutgoing_column3(int outgoing_column3) {
		this.outgoing_column3 = outgoing_column3;
	}
	public int getFor_column3() {
		return for_column3;
	}
	public void setFor_column3(int for_column3) {
		this.for_column3 = for_column3;
	}
	public String getFeed_type3() {
		return feed_type3;
	}
	public void setFeed_type3(String feed_type3) {
		this.feed_type3 = feed_type3;
	}
	public List<Links> getInlinks() {
		return inlinks;
	}
	public void setInlinks(List<Links> inlinks) {
		this.inlinks = inlinks;
	}
	public String getEnums() {
		return enums;
	}
	public void setEnums(String enums) {
		this.enums = enums;
	}
	public int getProgram_type() {
		return program_type;
	}
	public void setProgram_type(int program_type) {
		this.program_type = program_type;
	}
	public List<UseExtension> getExtension() {
		return extension;
	}
	public void setExtension(List<UseExtension> extension) {
		this.extension = extension;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public String getRegister_type() {
		return register_type;
	}
	public void setRegister_type(String register_type) {
		this.register_type = register_type;
	}
	public String getAccess_user() {
		return access_user;
	}
	public void setAccess_user(String access_user) {
		this.access_user = access_user;
	}
	public String getAccess_admin() {
		return access_admin;
	}
	public void setAccess_admin(String access_admin) {
		this.access_admin = access_admin;
	}
	public int getShow_limit() {
		return show_limit;
	}
	public void setShow_limit(int show_limit) {
		this.show_limit = show_limit;
	}
	public String getListview() {
		return listview;
	}
	public void setListview(String listview) {
		this.listview = listview;
	}
	public List<Management> getManagements() {
		return managements;
	}
	public void setManagements(List<Management> managements) {
		this.managements = managements;
	}
	public int getFieldset() {
		return fieldset;
	}
	public void setFieldset(int fieldset) {
		this.fieldset = fieldset;
	}
	public int getTabform() {
		return tabform;
	}
	public void setTabform(int tabform) {
		this.tabform = tabform;
	}
	public String getClosing_status() {
		return closing_status;
	}
	public void setClosing_status(String closing_status) {
		this.closing_status = closing_status;
	}
	public int getUsed_attachment() {
		return used_attachment;
	}
	public void setUsed_attachment(int used_attachment) {
		this.used_attachment = used_attachment;
	}
	public int getUsed_valid() {
		return used_valid;
	}
	public void setUsed_valid(int used_valid) {
		this.used_valid = used_valid;
	}
	public String getData_transfer() {
		return data_transfer;
	}
	public void setData_transfer(String data_transfer) {
		this.data_transfer = data_transfer;
	}
	public String getIntegrity() {
		return integrity;
	}
	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public int getALLOW_ADD() {
		return ALLOW_ADD;
	}
	public void setALLOW_ADD(int aLLOW_ADD) {
		ALLOW_ADD = aLLOW_ADD;
	}
	public int getALLOW_DELETE() {
		return ALLOW_DELETE;
	}
	public void setALLOW_DELETE(int aLLOW_DELETE) {
		ALLOW_DELETE = aLLOW_DELETE;
	}
	public String getListview2() {
		return listview2;
	}
	public void setListview2(String listview2) {
		this.listview2 = listview2;
	}
	public String getSubtable_viewmode() {
		return subtable_viewmode;
	}
	public void setSubtable_viewmode(String subtable_viewmode) {
		this.subtable_viewmode = subtable_viewmode;
	}
	public String getSubtable_editmode() {
		return subtable_editmode;
	}
	public void setSubtable_editmode(String subtable_editmode) {
		this.subtable_editmode = subtable_editmode;
	}
	public int getCanReviseConfirmed() {
		return canReviseConfirmed;
	}
	public void setCanReviseConfirmed(int canReviseConfirmed) {
		this.canReviseConfirmed = canReviseConfirmed;
	}
	public String getClosingStatusCanRevise() {
		return closingStatusCanRevise;
	}
	public void setClosingStatusCanRevise(String closingStatusCanRevise) {
		this.closingStatusCanRevise = closingStatusCanRevise;
	}
	public String getClosingStatusAfterRevise() {
		return closingStatusAfterRevise;
	}
	public void setClosingStatusAfterRevise(String closingStatusAfterRevise) {
		this.closingStatusAfterRevise = closingStatusAfterRevise;
	}
	public int getDirect_data_edit_mode() {
		return direct_data_edit_mode;
	}
	public void setDirect_data_edit_mode(int direct_data_edit_mode) {
		this.direct_data_edit_mode = direct_data_edit_mode;
	}
	public SetSegmentView getSetSegmentView() {
		return setSegmentView;
	}
	public void setSetSegmentView(SetSegmentView setSegmentView) {
		this.setSegmentView = setSegmentView;
	}
	public int getRequestsystem() {
		return requestsystem;
	}
	public void setRequestsystem(int requestsystem) {
		this.requestsystem = requestsystem;
	}
	public int getRenamefile() {
		return renamefile;
	}
	public void setRenamefile(int renamefile) {
		this.renamefile = renamefile;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getLimit_head() {
		return limit_head;
	}
	public void setLimit_head(int limit_head) {
		this.limit_head = limit_head;
	}
	public String getUse_ex() {
		return use_ex;
	}
	public void setUse_ex(String use_ex) {
		this.use_ex = use_ex;
	}
	public int getUse_index_app() {
		return use_index_app;
	}
	public void setUse_index_app(int use_index_app) {
		this.use_index_app = use_index_app;
	}
	public String getUse_ex_name() {
		return use_ex_name;
	}
	public void setUse_ex_name(String use_ex_name) {
		this.use_ex_name = use_ex_name;
	}
	public String getUse_ex_icon() {
		return use_ex_icon;
	}
	public void setUse_ex_icon(String use_ex_icon) {
		this.use_ex_icon = use_ex_icon;
	}
	public String getShortcut_newdoc_change_closing_status() {
		return shortcut_newdoc_change_closing_status;
	}
	public void setShortcut_newdoc_change_closing_status(String shortcut_newdoc_change_closing_status) {
		this.shortcut_newdoc_change_closing_status = shortcut_newdoc_change_closing_status;
	}
	public String getClosing_status_notsend() {
		return closing_status_notsend;
	}
	public void setClosing_status_notsend(String closing_status_notsend) {
		this.closing_status_notsend = closing_status_notsend;
	}
	public String getClosing_status_expired() {
		return closing_status_expired;
	}
	public void setClosing_status_expired(String closing_status_expired) {
		this.closing_status_expired = closing_status_expired;
	}
	public int getExpires_in_days() {
		return expires_in_days;
	}
	public void setExpires_in_days(int expires_in_days) {
		this.expires_in_days = expires_in_days;
	}
	public int getIs_acknowledge_required() {
		return is_acknowledge_required;
	}
	public void setIs_acknowledge_required(int is_acknowledge_required) {
		this.is_acknowledge_required = is_acknowledge_required;
	}
	public String getClosingStatusAfterAdd() {
		return closingStatusAfterAdd;
	}
	public void setClosingStatusAfterAdd(String closingStatusAfterAdd) {
		this.closingStatusAfterAdd = closingStatusAfterAdd;
	}
	public String getProfile_column() {
		return profile_column;
	}
	public void setProfile_column(String profile_column) {
		this.profile_column = profile_column;
	}
	public String getProduct_master_column() {
		return product_master_column;
	}
	public void setProduct_master_column(String product_master_column) {
		this.product_master_column = product_master_column;
	}
	public String getClosing_evidence_type() {
		return closing_evidence_type;
	}
	public void setClosing_evidence_type(String closing_evidence_type) {
		this.closing_evidence_type = closing_evidence_type;
	}
	public String getIs_attachment_required_to_close() {
		return is_attachment_required_to_close;
	}
	public void setIs_attachment_required_to_close(String is_attachment_required_to_close) {
		this.is_attachment_required_to_close = is_attachment_required_to_close;
	}
	public int getCan_refuse_and_return() {
		return can_refuse_and_return;
	}
	public void setCan_refuse_and_return(int can_refuse_and_return) {
		this.can_refuse_and_return = can_refuse_and_return;
	}
	public String getEdata_idoc_grade_ref() {
		return edata_idoc_grade_ref;
	}
	public void setEdata_idoc_grade_ref(String edata_idoc_grade_ref) {
		this.edata_idoc_grade_ref = edata_idoc_grade_ref;
	}
	public String getApplied_status_array() {
		return applied_status_array;
	}
	public void setApplied_status_array(String applied_status_array) {
		this.applied_status_array = applied_status_array;
	}
	public String getIs_ex_movement_active() {
		return is_ex_movement_active;
	}
	public void setIs_ex_movement_active(String is_ex_movement_active) {
		this.is_ex_movement_active = is_ex_movement_active;
	}
	public int getCycle_duration() {
		return cycle_duration;
	}
	public void setCycle_duration(int cycle_duration) {
		this.cycle_duration = cycle_duration;
	}
	public String getGrade_frequency_mapping() {
		return grade_frequency_mapping;
	}
	public void setGrade_frequency_mapping(String grade_frequency_mapping) {
		this.grade_frequency_mapping = grade_frequency_mapping;
	}
	public String getSub_process_expired() {
		return sub_process_expired;
	}
	public void setSub_process_expired(String sub_process_expired) {
		this.sub_process_expired = sub_process_expired;
	}




	
}
