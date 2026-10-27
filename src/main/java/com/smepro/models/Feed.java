package com.smepro.models;

public class Feed {
	
	private int id;
	private int links_id;
	private String outgoing_column;
	private String for_column;
	private String feed_type;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLinks_id() {
		return links_id;
	}
	public void setLinks_id(int links_id) {
		this.links_id = links_id;
	}
	public String getOutgoing_column() {
		return outgoing_column;
	}
	public void setOutgoing_column(String outgoing_column) {
		this.outgoing_column = outgoing_column;
	}
	public String getFor_column() {
		return for_column;
	}
	public void setFor_column(String for_column) {
		this.for_column = for_column;
	}
	public String getFeed_type() {
		return feed_type;
	}
	public void setFeed_type(String feed_type) {
		this.feed_type = feed_type;
	}
	
	

	
	
}
