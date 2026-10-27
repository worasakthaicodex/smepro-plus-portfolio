package com.smepro.models;

public class setmail {
	private int id;
	private int id_refer;
	private String username;
	private String password;
	private String hostname;
	private String starttls_port;
	private String url;
	private String support;
	private boolean starttls;
	private boolean auth;
	private String emailtest;
	private String attachment;
	private String attachment2;
	private String attachment3;
	private int user_id;
	private String user_name;
	private String user_address;
	private String user_tel;
	private String user_email;
	private String token;
	private int type;
	
	private String dormitory_name;
	private String due;
	private String note;
	private String note2;
	private double amount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getStarttls_port() {
		return starttls_port;
	}
	public void setStarttls_port(String starttls_port) {
		this.starttls_port = starttls_port;
	}
	public boolean isStarttls() {
		return starttls;
	}
	public void setStarttls(boolean starttls) {
		this.starttls = starttls;
	}
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public String getEmailtest() {
		return emailtest;
	}
	public void setEmailtest(String emailtest) {
		this.emailtest = emailtest;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getAttachment2() {
		return attachment2;
	}
	public void setAttachment2(String attachment2) {
		this.attachment2 = attachment2;
	}
	public String getAttachment3() {
		return attachment3;
	}
	public void setAttachment3(String attachment3) {
		this.attachment3 = attachment3;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDormitory_name() {
		return dormitory_name;
	}
	public void setDormitory_name(String dormitory_name) {
		this.dormitory_name = dormitory_name;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	
	@Override
	public String toString() {
		return String.format(
				"setmail [id=%s, id_refer=%s, username=%s, password=%s, hostname=%s, starttls_port=%s, url=%s, support=%s, starttls=%s, auth=%s, emailtest=%s, attachment=%s, attachment2=%s, attachment3=%s, user_id=%s, user_name=%s, user_address=%s, user_tel=%s, user_email=%s, token=%s, type=%s, dormitory_name=%s, due=%s, note=%s, note2=%s, amount=%s]",
				id, id_refer, username, password, hostname, starttls_port, url, support, starttls, auth, emailtest,
				attachment, attachment2, attachment3, user_id, user_name, user_address, user_tel, user_email, token,
				type, dormitory_name, due, note, note2, amount);
	}
	
	
	
}

