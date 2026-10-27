package com.smepro.models;

import java.util.List;

public class Links {
	private int id;
	private String link_name;
	private String link_to;
	private String relationship;
	private int id_refer;
	private List<Rollup_calculation> Rollup_calculation;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink_name() {
		return link_name;
	}
	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}
	public String getLink_to() {
		return link_to;
	}
	public void setLink_to(String link_to) {
		this.link_to = link_to;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public List<Rollup_calculation> getRollup_calculation() {
		return Rollup_calculation;
	}
	public void setRollup_calculation(List<Rollup_calculation> rollup_calculation) {
		Rollup_calculation = rollup_calculation;
	}

	
	
}
