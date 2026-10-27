package com.smepro.models;

import java.util.List;

public class trace {
	
	private int id;
	private String trace_name;
	private String trace_description;
	private int trace_join;
	private int trace_app;
	private String trace_processes;
	private String trace_type;
	private String trace_key;
	private String[] key;
	private String[] key2;
	private List<edata> data;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrace_name() {
		return trace_name;
	}
	public void setTrace_name(String trace_name) {
		this.trace_name = trace_name;
	}
	public String getTrace_description() {
		return trace_description;
	}
	public void setTrace_description(String trace_description) {
		this.trace_description = trace_description;
	}
	public int getTrace_join() {
		return trace_join;
	}
	public void setTrace_join(int trace_join) {
		this.trace_join = trace_join;
	}
	public int getTrace_app() {
		return trace_app;
	}
	public void setTrace_app(int trace_app) {
		this.trace_app = trace_app;
	}
	public String getTrace_processes() {
		return trace_processes;
	}
	public void setTrace_processes(String trace_processes) {
		this.trace_processes = trace_processes;
	}
	public String getTrace_type() {
		return trace_type;
	}
	public void setTrace_type(String trace_type) {
		this.trace_type = trace_type;
	}
	public String[] getKey() {
		return key;
	}
	public void setKey(String[] key) {
		this.key = key;
	}
	public List<edata> getData() {
		return data;
	}
	public void setData(List<edata> data) {
		this.data = data;
	}
	public String getTrace_key() {
		return trace_key;
	}
	public void setTrace_key(String trace_key) {
		this.trace_key = trace_key;
	}
	public String[] getKey2() {
		return key2;
	}
	public void setKey2(String[] key2) {
		this.key2 = key2;
	}
	
	
	
	
}
