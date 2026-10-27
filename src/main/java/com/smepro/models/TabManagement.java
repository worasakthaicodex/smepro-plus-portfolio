package com.smepro.models;



public class TabManagement {

	private int id;
	private String tab_name;
	private int tab_count;
	private int tab_type_view;  //  0 เห็นทั้งหมด , 1  เห็นเฉพาะฉัน ,2 ที่เกี่ยวข้องกับฉัน 
	private String tab_columns;
	private String tab_table;
	private String tab_maintable;
	private int tab_child;
	private int tab_process_id;
	private int tab_management_id;
	private String tab_current_step;
	
	private int box_total;
	
	private int tab_current_step_int;
	private String tab_closing_status;
	
	private String tab_current_step2;
	private String tab_closing_status2;
	private int acknowledge2;
	private int acknowledge_user;
	private int acknowledge_user2;
	
	
	
	private String shortcut_profile_code;
	private int shortcut_profile_app;
	private String shortcut_profile_linkname;
	
	private String shortcut_newdoc_linkname;
	private String shortcut_newdoc_code;
	private String shortcut_newdoc_change_closing_status;
	
	
	private String shortcut_change_closing_status;
	private String shortcut_change_closing_linkname;
	
	
	private String shortcut_segment_linkname;
	private String shortcut_segment_code;
	private String shortcut_segment_column;
	
	private int grouping_row;
	private int grouping_row_check;
	private int grouping_row_date;
	

	private int followup;
	private int followup_plan;
	private int acknowledge;
	private int expired;
	
	
	private int request_to_edit;
	private int request_to_edit2;
	
	
	public int getFollowup() {
		return followup;
	}
	public void setFollowup(int followup) {
		this.followup = followup;
	}
	public int getAcknowledge() {
		return acknowledge;
	}
	public void setAcknowledge(int acknowledge) {
		this.acknowledge = acknowledge;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTab_name() {
		return tab_name;
	}
	public void setTab_name(String tab_name) {
		this.tab_name = tab_name;
	}
	public int getTab_count() {
		return tab_count;
	}
	public void setTab_count(int tab_count) {
		this.tab_count = tab_count;
	}
	public int getTab_type_view() {
		return tab_type_view;
	}
	public void setTab_type_view(int tab_type_view) {
		this.tab_type_view = tab_type_view;
	}
	public String getTab_columns() {
		return tab_columns;
	}
	public void setTab_columns(String tab_columns) {
		this.tab_columns = tab_columns;
	}
	public String getTab_table() {
		return tab_table;
	}
	public void setTab_table(String tab_table) {
		this.tab_table = tab_table;
	}
	public String getTab_maintable() {
		return tab_maintable;
	}
	public void setTab_maintable(String tab_maintable) {
		this.tab_maintable = tab_maintable;
	}
	public int getTab_child() {
		return tab_child;
	}
	public void setTab_child(int tab_child) {
		this.tab_child = tab_child;
	}
	public int getTab_process_id() {
		return tab_process_id;
	}
	public void setTab_process_id(int tab_process_id) {
		this.tab_process_id = tab_process_id;
	}
	public int getTab_management_id() {
		return tab_management_id;
	}
	public void setTab_management_id(int tab_management_id) {
		this.tab_management_id = tab_management_id;
	}
	public int getTab_current_step_int() {
		return tab_current_step_int;
	}
	public void setTab_current_step_int(int tab_current_step_int) {
		this.tab_current_step_int = tab_current_step_int;
	}
	public String getTab_closing_status() {
		return tab_closing_status;
	}
	public void setTab_closing_status(String tab_closing_status) {
		this.tab_closing_status = tab_closing_status;
	}
	public String getTab_current_step() {
		return tab_current_step;
	}
	public void setTab_current_step(String tab_current_step) {
		this.tab_current_step = tab_current_step;
	}
	public int getBox_total() {
		return box_total;
	}
	public void setBox_total(int box_total) {
		this.box_total = box_total;
	}
	public String getShortcut_profile_code() {
		return shortcut_profile_code;
	}
	public void setShortcut_profile_code(String shortcut_profile_code) {
		this.shortcut_profile_code = shortcut_profile_code;
	}
	public int getShortcut_profile_app() {
		return shortcut_profile_app;
	}
	public void setShortcut_profile_app(int shortcut_profile_app) {
		this.shortcut_profile_app = shortcut_profile_app;
	}
	public String getShortcut_profile_linkname() {
		return shortcut_profile_linkname;
	}
	public void setShortcut_profile_linkname(String shortcut_profile_linkname) {
		this.shortcut_profile_linkname = shortcut_profile_linkname;
	}
	public String getShortcut_newdoc_linkname() {
		return shortcut_newdoc_linkname;
	}
	public void setShortcut_newdoc_linkname(String shortcut_newdoc_linkname) {
		this.shortcut_newdoc_linkname = shortcut_newdoc_linkname;
	}
	public String getShortcut_newdoc_code() {
		return shortcut_newdoc_code;
	}
	public void setShortcut_newdoc_code(String shortcut_newdoc_code) {
		this.shortcut_newdoc_code = shortcut_newdoc_code;
	}
	public String getShortcut_newdoc_change_closing_status() {
		return shortcut_newdoc_change_closing_status;
	}
	public void setShortcut_newdoc_change_closing_status(String shortcut_newdoc_change_closing_status) {
		this.shortcut_newdoc_change_closing_status = shortcut_newdoc_change_closing_status;
	}
	public String getShortcut_change_closing_status() {
		return shortcut_change_closing_status;
	}
	public void setShortcut_change_closing_status(String shortcut_change_closing_status) {
		this.shortcut_change_closing_status = shortcut_change_closing_status;
	}
	public String getShortcut_change_closing_linkname() {
		return shortcut_change_closing_linkname;
	}
	public void setShortcut_change_closing_linkname(String shortcut_change_closing_linkname) {
		this.shortcut_change_closing_linkname = shortcut_change_closing_linkname;
	}
	public String getShortcut_segment_linkname() {
		return shortcut_segment_linkname;
	}
	public void setShortcut_segment_linkname(String shortcut_segment_linkname) {
		this.shortcut_segment_linkname = shortcut_segment_linkname;
	}
	public String getShortcut_segment_code() {
		return shortcut_segment_code;
	}
	public void setShortcut_segment_code(String shortcut_segment_code) {
		this.shortcut_segment_code = shortcut_segment_code;
	}
	public String getShortcut_segment_column() {
		return shortcut_segment_column;
	}
	public void setShortcut_segment_column(String shortcut_segment_column) {
		this.shortcut_segment_column = shortcut_segment_column;
	}
	public int getFollowup_plan() {
		return followup_plan;
	}
	public void setFollowup_plan(int followup_plan) {
		this.followup_plan = followup_plan;
	}
	public int getGrouping_row() {
		return grouping_row;
	}
	public void setGrouping_row(int grouping_row) {
		this.grouping_row = grouping_row;
	}
	public int getGrouping_row_check() {
		return grouping_row_check;
	}
	public void setGrouping_row_check(int grouping_row_check) {
		this.grouping_row_check = grouping_row_check;
	}
	public int getGrouping_row_date() {
		return grouping_row_date;
	}
	public void setGrouping_row_date(int grouping_row_date) {
		this.grouping_row_date = grouping_row_date;
	}
	public String getTab_current_step2() {
		return tab_current_step2;
	}
	public void setTab_current_step2(String tab_current_step2) {
		this.tab_current_step2 = tab_current_step2;
	}
	public String getTab_closing_status2() {
		return tab_closing_status2;
	}
	public void setTab_closing_status2(String tab_closing_status2) {
		this.tab_closing_status2 = tab_closing_status2;
	}
	public int getAcknowledge2() {
		return acknowledge2;
	}
	public void setAcknowledge2(int acknowledge2) {
		this.acknowledge2 = acknowledge2;
	}
	public int getAcknowledge_user() {
		return acknowledge_user;
	}
	public void setAcknowledge_user(int acknowledge_user) {
		this.acknowledge_user = acknowledge_user;
	}
	public int getAcknowledge_user2() {
		return acknowledge_user2;
	}
	public void setAcknowledge_user2(int acknowledge_user2) {
		this.acknowledge_user2 = acknowledge_user2;
	}
	public int getExpired() {
		return expired;
	}
	public void setExpired(int expired) {
		this.expired = expired;
	}
	public int getRequest_to_edit() {
		return request_to_edit;
	}
	public void setRequest_to_edit(int request_to_edit) {
		this.request_to_edit = request_to_edit;
	}
	public int getRequest_to_edit2() {
		return request_to_edit2;
	}
	public void setRequest_to_edit2(int request_to_edit2) {
		this.request_to_edit2 = request_to_edit2;
	}



	
}
