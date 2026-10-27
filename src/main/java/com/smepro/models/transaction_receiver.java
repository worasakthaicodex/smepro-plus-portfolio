package com.smepro.models;

public class transaction_receiver {

	private int id;
	private int transaction_id;
	private int transaction_detail_id;
	private int receiver_id;
	private String receiver_role;
	private String receive_type;
	private String status;
	private String received_at;
	private String comment;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getTransaction_detail_id() {
		return transaction_detail_id;
	}
	public void setTransaction_detail_id(int transaction_detail_id) {
		this.transaction_detail_id = transaction_detail_id;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getReceiver_role() {
		return receiver_role;
	}
	public void setReceiver_role(String receiver_role) {
		this.receiver_role = receiver_role;
	}
	public String getReceive_type() {
		return receive_type;
	}
	public void setReceive_type(String receive_type) {
		this.receive_type = receive_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceived_at() {
		return received_at;
	}
	public void setReceived_at(String received_at) {
		this.received_at = received_at;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
	
	
	
}


