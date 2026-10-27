package com.smepro.models;

import java.util.List;

public class user extends groups {
	private int user_id;
	private String username;
	private String password;
	private int enabled;
	private String user_name;
	private String user_address;
	private String user_tel;
	private String user_email;
	private String user_line;
	private String user_zip;
	private String user_province;
	private String user_district;	
	private String user_road;
	private String user_alley;
	private String user_village;
	private String user_housenumber;
	private String user_building;
	private String user_mobile;
	private String user_passport;
	private String user_datepassport;
	private String user_countrypassport;
	private String user_districtpassport;
	private String user_idcard;
	private String user_birthday;
	private String auth;
	private String user_img;
	
	private List<showlog> logDay; 
	private List<showlog> logMonth; 
	private List<showlog> logYear; 
	

	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
	public String getUser_line() {
		return user_line;
	}
	public void setUser_line(String user_line) {
		this.user_line = user_line;
	}
	public String getUser_zip() {
		return user_zip;
	}
	public void setUser_zip(String user_zip) {
		this.user_zip = user_zip;
	}
	public String getUser_province() {
		return user_province;
	}
	public void setUser_province(String user_province) {
		this.user_province = user_province;
	}
	public String getUser_district() {
		return user_district;
	}
	public void setUser_district(String user_district) {
		this.user_district = user_district;
	}
	public String getUser_road() {
		return user_road;
	}
	public void setUser_road(String user_road) {
		this.user_road = user_road;
	}
	public String getUser_alley() {
		return user_alley;
	}
	public void setUser_alley(String user_alley) {
		this.user_alley = user_alley;
	}
	public String getUser_village() {
		return user_village;
	}
	public void setUser_village(String user_village) {
		this.user_village = user_village;
	}
	public String getUser_housenumber() {
		return user_housenumber;
	}
	public void setUser_housenumber(String user_housenumber) {
		this.user_housenumber = user_housenumber;
	}
	public String getUser_building() {
		return user_building;
	}
	public void setUser_building(String user_building) {
		this.user_building = user_building;
	}
	public String getUser_mobile() {
		return user_mobile;
	}
	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}
	public String getUser_passport() {
		return user_passport;
	}
	public void setUser_passport(String user_passport) {
		this.user_passport = user_passport;
	}
	public String getUser_datepassport() {
		return user_datepassport;
	}
	public void setUser_datepassport(String user_datepassport) {
		this.user_datepassport = user_datepassport;
	}
	public String getUser_countrypassport() {
		return user_countrypassport;
	}
	public void setUser_countrypassport(String user_countrypassport) {
		this.user_countrypassport = user_countrypassport;
	}
	public String getUser_districtpassport() {
		return user_districtpassport;
	}
	public void setUser_districtpassport(String user_districtpassport) {
		this.user_districtpassport = user_districtpassport;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public List<showlog> getLogDay() {
		return logDay;
	}
	public void setLogDay(List<showlog> logDay) {
		this.logDay = logDay;
	}
	public List<showlog> getLogMonth() {
		return logMonth;
	}
	public void setLogMonth(List<showlog> logMonth) {
		this.logMonth = logMonth;
	}
	public List<showlog> getLogYear() {
		return logYear;
	}
	public void setLogYear(List<showlog> logYear) {
		this.logYear = logYear;
	}
	@Override
	public String toString() {
		return String.format(
				"user [user_id=%s, username=%s, password=%s, enabled=%s, user_name=%s, user_address=%s, user_tel=%s, user_email=%s, user_line=%s, user_zip=%s, user_province=%s, user_district=%s, user_road=%s, user_alley=%s, user_village=%s, user_housenumber=%s, user_building=%s, user_mobile=%s, user_passport=%s, user_datepassport=%s, user_countrypassport=%s, user_districtpassport=%s, user_idcard=%s, user_birthday=%s]",
				user_id, username, password, enabled, user_name, user_address, user_tel, user_email, user_line,
				user_zip, user_province, user_district, user_road, user_alley, user_village, user_housenumber,
				user_building, user_mobile, user_passport, user_datepassport, user_countrypassport,
				user_districtpassport, user_idcard, user_birthday);
	}


    
	
	
}
