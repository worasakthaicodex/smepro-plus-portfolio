package com.smepro.models;

import java.util.List;

public class Tabform {
	
	private int id;
	private int form_id;
	private int form_rv;
	private String title;
	private String form_column;
	private String form_column_title;
	private List<Group_label> group_label;
	private int tab_step;
	private int tab_access;
	private int tab_show;
	
	
	private List<eformfield> eformfields;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getForm_id() {
		return form_id;
	}
	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}
	public int getForm_rv() {
		return form_rv;
	}
	public void setForm_rv(int form_rv) {
		this.form_rv = form_rv;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getForm_column() {
		return form_column;
	}
	public void setForm_column(String form_column) {
		this.form_column = form_column;
	}
	public List<eformfield> getEformfields() {
		return eformfields;
	}
	public void setEformfields(List<eformfield> eformfields) {
		this.eformfields = eformfields;
	}
	public int getTab_step() {
		return tab_step;
	}
	public void setTab_step(int tab_step) {
		this.tab_step = tab_step;
	}
	public int getTab_access() {
		return tab_access;
	}
	public void setTab_access(int tab_access) {
		this.tab_access = tab_access;
	}
	public String getForm_column_title() {
		return form_column_title;
	}
	public void setForm_column_title(String form_column_title) {
		this.form_column_title = form_column_title;
	}
	public int getTab_show() {
		return tab_show;
	}
	public void setTab_show(int tab_show) {
		this.tab_show = tab_show;
	}
	public List<Group_label> getGroup_label() {
		return group_label;
	}
	public void setGroup_label(List<Group_label> group_label) {
		this.group_label = group_label;
	}

	
	
}
