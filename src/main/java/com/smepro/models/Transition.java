package com.smepro.models;

import javax.servlet.http.HttpSession;

public class Transition {
	
	
	private String name;
    private String from;
    private String to;
	private String note;
    private String clear;
    private HttpSession session;
    private int Transition_from;
    private int Transition_to;
    
    
    

	public Transition() {
	}
	public Transition(String name, String from, String to) {
		super();
		this.name = name;
		this.from = from;
		this.to = to;
	}
	public Transition(String transition, String note) {
		this.name = transition;
		this.note = note;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getClear() {
		return clear;
	}
	public void setClear(String clear) {
		this.clear = clear;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public int getTransition_from() {
		return Transition_from;
	}
	public void setTransition_from(int transition_from) {
		Transition_from = transition_from;
	}
	public int getTransition_to() {
		return Transition_to;
	}
	public void setTransition_to(int transition_to) {
		Transition_to = transition_to;
	}
    
    
    
}
