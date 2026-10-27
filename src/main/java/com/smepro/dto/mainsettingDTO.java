package com.smepro.dto;

public class mainsettingDTO {
	
	
	
	private int mainsetting_id  = 1;
	private int mainsetting_vat ;
	private int mainsetting_vat_status;
	private String mainsetting_csv;
	private String mainsetting_system;
	private String mainsetting_filesuploads;
	private String mainsetting_csv_temporary;
	private String signingKey;
	private int mainsetting_type;
	private String mainsetting_pub;
	private String mainsetting_sub;
	private String mainsetting_pubTopic;
	private String mainsetting_subTopic;
	private String mainsetting_broker;
	private String mainsetting_port;
	private String mainsetting_userName;
	private String mainsetting_pass;
	private String license  ="license.lic";
	private String mainlink;
	
	
	
	public int getMainsetting_id() {
		return mainsetting_id;
	}
	public void setMainsetting_id(int mainsetting_id) {
		this.mainsetting_id = mainsetting_id;
	}
	public int getMainsetting_vat() {
		return mainsetting_vat;
	}
	public void setMainsetting_vat(int mainsetting_vat) {
		this.mainsetting_vat = mainsetting_vat;
	}
	public int getMainsetting_vat_status() {
		return mainsetting_vat_status;
	}
	public void setMainsetting_vat_status(int mainsetting_vat_status) {
		this.mainsetting_vat_status = mainsetting_vat_status;
	}
	public String getMainsetting_csv() {
		return mainsetting_csv;
	}
	public void setMainsetting_csv(String mainsetting_csv) {
		this.mainsetting_csv = mainsetting_csv;
	}
	public String getMainsetting_system() {
		return mainsetting_system;
	}
	public void setMainsetting_system(String mainsetting_system) {
		this.mainsetting_system = mainsetting_system;
	}
	public String getMainsetting_filesuploads() {
		return mainsetting_filesuploads;
	}
	public void setMainsetting_filesuploads(String mainsetting_filesuploads) {
		this.mainsetting_filesuploads = mainsetting_filesuploads;
	}
	public String getMainsetting_csv_temporary() {
		return mainsetting_csv_temporary;
	}
	public void setMainsetting_csv_temporary(String mainsetting_csv_temporary) {
		this.mainsetting_csv_temporary = mainsetting_csv_temporary;
	}
	public String getSigningKey() {
		return signingKey;
	}
	public void setSigningKey(String signingKey) {
		this.signingKey = signingKey;
	}
	public int getMainsetting_type() {
		return mainsetting_type;
	}
	public void setMainsetting_type(int mainsetting_type) {
		this.mainsetting_type = mainsetting_type;
	}
	public String getMainsetting_pub() {
		return mainsetting_pub;
	}
	public void setMainsetting_pub(String mainsetting_pub) {
		this.mainsetting_pub = mainsetting_pub;
	}
	public String getMainsetting_sub() {
		return mainsetting_sub;
	}
	public void setMainsetting_sub(String mainsetting_sub) {
		this.mainsetting_sub = mainsetting_sub;
	}
	public String getMainsetting_pubTopic() {
		return mainsetting_pubTopic;
	}
	public void setMainsetting_pubTopic(String mainsetting_pubTopic) {
		this.mainsetting_pubTopic = mainsetting_pubTopic;
	}
	public String getMainsetting_subTopic() {
		return mainsetting_subTopic;
	}
	public void setMainsetting_subTopic(String mainsetting_subTopic) {
		this.mainsetting_subTopic = mainsetting_subTopic;
	}
	public String getMainsetting_broker() {
		return mainsetting_broker;
	}
	public void setMainsetting_broker(String mainsetting_broker) {
		this.mainsetting_broker = mainsetting_broker;
	}
	public String getMainsetting_port() {
		return mainsetting_port;
	}
	public void setMainsetting_port(String mainsetting_port) {
		this.mainsetting_port = mainsetting_port;
	}
	public String getMainsetting_userName() {
		return mainsetting_userName;
	}
	public void setMainsetting_userName(String mainsetting_userName) {
		this.mainsetting_userName = mainsetting_userName;
	}
	public String getMainsetting_pass() {
		return mainsetting_pass;
	}
	public void setMainsetting_pass(String mainsetting_pass) {
		this.mainsetting_pass = mainsetting_pass;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getMainlink() {
		return mainlink;
	}
	public void setMainlink(String mainlink) {
		this.mainlink = mainlink;
	}
	@Override
	public String toString() {
		return String.format(
				"mainsettingDTO [mainsetting_id=%s, mainsetting_vat=%s, mainsetting_vat_status=%s, mainsetting_csv=%s, mainsetting_system=%s, mainsetting_filesuploads=%s, mainsetting_csv_temporary=%s, signingKey=%s, mainsetting_type=%s, mainsetting_pub=%s, mainsetting_sub=%s, mainsetting_pubTopic=%s, mainsetting_subTopic=%s, mainsetting_broker=%s, mainsetting_port=%s, mainsetting_userName=%s, mainsetting_pass=%s, license=%s, mainlink=%s]",
				mainsetting_id, mainsetting_vat, mainsetting_vat_status, mainsetting_csv, mainsetting_system,
				mainsetting_filesuploads, mainsetting_csv_temporary, signingKey, mainsetting_type, mainsetting_pub,
				mainsetting_sub, mainsetting_pubTopic, mainsetting_subTopic, mainsetting_broker, mainsetting_port,
				mainsetting_userName, mainsetting_pass, license, mainlink);
	}
	
	
	
}
