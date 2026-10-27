package com.smepro.models;

public class registers {
	
	 private String  search_id;
	 private String  searchMode;
	 private int register_id;
	 private String register_code;
	 private String register_daterun;
	 private int revision; 
	 
	 
	 private String register_name; 
	 private String register_type; 
	 private String register_groups; 
	 private String register_address; 
	 private String register_fulladdress; 
	 private String register_note;
	 private String contact_person;
	 private String phone_number;
	 private String mobile_number;
	 private String website;
	 private String social;
	 private String tax_id;
	 private String branch_code;
	 private String business_type;
	 private String payment_terms;	 
	 private String credit_limit;	 	 
	 private String bank_account;		  
	 private String preferred_currency;	
		 
	 private int id;
	 private String updated_at;	
	 private String created_at;	
	 private String register_other_conditions;	
	 private String categorization;	 
	 private String behavior;	 	  
	 private String register_status; // 0 ปกติ 1 รอลบออก 2บัญชีดำ 3ไม่มีประโยชน์ทางbusiness
	 private String register_grade;
	 private String  person_email;
	 private String  register_email;
	 
	 private String  register_capital;
	 private String  register_horsepower;
	 private String  register_headcount;

	 
	 private int register_poin;
	 
	 
	  private String party_type;	//บุคคลธรรมดา / /นิติบุคคล
	  private String product_category;   //Other Food //Other Catergories
	 private String register_title; //บจก วิสาหกิจชุมชน
	 private String fax; 
	 private String person_position;
	 private String person_number;
	 private String contact_approvers;
	 private String approvers_position;
	 private String approvers_number;
	 private String approvers_email;
	 private String region;
	 private String lat;
	 private String lon;
	 private String dbd;
	 
	 
	 private String date_insert_oldsys;
	 private String merged_duplicate_ids;
	 private String id_oldsys;
	 
	 
	 private String extension_column_1; 
	 private String extension_column_2; 
	 private String extension_column_3; 
	 private String extension_column_4; 
	 private String extension_column_5; 
	 private String extension_column_6; 
	 private String extension_column_7; 
	 private String extension_column_8; 
	 private String extension_column_9; 
	 private String extension_column_10; 
	 private String extension_column_11; 
	 private String extension_column_12; 
	 private String extension_column_13; 
	 private String extension_column_14; 
	 private String extension_column_15; 
	 private String extension_column_16; 
	 private String extension_column_17; 
	 private String extension_column_18; 
	 private String extension_column_19; 
	 private String extension_column_20; 

	 
	 
	 private  String reqProcessId ;
	 private  String reqExtId;
	 private String reqDateCol;
	 
	 
	public int getRegister_id() {
		return register_id;
	}
	public void setRegister_id(int register_id) {
		this.register_id = register_id;
	}
	public String getRegister_name() {
		return register_name;
	}
	public void setRegister_name(String register_name) {
		this.register_name = register_name;
	}
	public String getRegister_type() {
		return register_type;
	}
	public void setRegister_type(String register_type) {
		this.register_type = register_type;
	}
	public String getRegister_groups() {
		return register_groups;
	}
	public void setRegister_groups(String register_groups) {
		this.register_groups = register_groups;
	}
	public String getRegister_address() {
		return register_address;
	}
	public void setRegister_address(String register_address) {
		this.register_address = register_address;
	}
	public String getRegister_note() {
		return register_note;
	}
	public void setRegister_note(String register_note) {
		this.register_note = register_note;
	}
	public String getRegister_code() {
		return register_code;
	}
	public void setRegister_code(String register_code) {
		this.register_code = register_code;
	}
	public String getRegister_daterun() {
		return register_daterun;
	}
	public void setRegister_daterun(String register_daterun) {
		this.register_daterun = register_daterun;
	}
	public int getRegister_poin() {
		return register_poin;
	}
	public void setRegister_poin(int register_poin) {
		this.register_poin = register_poin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSocial() {
		return social;
	}
	public void setSocial(String social) {
		this.social = social;
	}
	public String getTax_id() {
		return tax_id;
	}
	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public String getPayment_terms() {
		return payment_terms;
	}
	public void setPayment_terms(String payment_terms) {
		this.payment_terms = payment_terms;
	}
	public String getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(String credit_limit) {
		this.credit_limit = credit_limit;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getPreferred_currency() {
		return preferred_currency;
	}
	public void setPreferred_currency(String preferred_currency) {
		this.preferred_currency = preferred_currency;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getRegister_other_conditions() {
		return register_other_conditions;
	}
	public void setRegister_other_conditions(String register_other_conditions) {
		this.register_other_conditions = register_other_conditions;
	}
	public String getCategorization() {
		return categorization;
	}
	public void setCategorization(String categorization) {
		this.categorization = categorization;
	}
	public String getBehavior() {
		return behavior;
	}
	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}
	public String getRegister_status() {
		return register_status;
	}
	public void setRegister_status(String register_status) {
		this.register_status = register_status;
	}
	public String getRegister_grade() {
		return register_grade;
	}
	public void setRegister_grade(String register_grade) {
		this.register_grade = register_grade;
	}
	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getExtension_column_1() {
		return extension_column_1;
	}
	public void setExtension_column_1(String extension_column_1) {
		this.extension_column_1 = extension_column_1;
	}
	public String getExtension_column_2() {
		return extension_column_2;
	}
	public void setExtension_column_2(String extension_column_2) {
		this.extension_column_2 = extension_column_2;
	}
	public String getExtension_column_3() {
		return extension_column_3;
	}
	public void setExtension_column_3(String extension_column_3) {
		this.extension_column_3 = extension_column_3;
	}
	public String getExtension_column_4() {
		return extension_column_4;
	}
	public void setExtension_column_4(String extension_column_4) {
		this.extension_column_4 = extension_column_4;
	}
	public String getExtension_column_5() {
		return extension_column_5;
	}
	public void setExtension_column_5(String extension_column_5) {
		this.extension_column_5 = extension_column_5;
	}
	public String getExtension_column_6() {
		return extension_column_6;
	}
	public void setExtension_column_6(String extension_column_6) {
		this.extension_column_6 = extension_column_6;
	}
	public String getExtension_column_7() {
		return extension_column_7;
	}
	public void setExtension_column_7(String extension_column_7) {
		this.extension_column_7 = extension_column_7;
	}
	public String getExtension_column_8() {
		return extension_column_8;
	}
	public void setExtension_column_8(String extension_column_8) {
		this.extension_column_8 = extension_column_8;
	}
	public String getExtension_column_9() {
		return extension_column_9;
	}
	public void setExtension_column_9(String extension_column_9) {
		this.extension_column_9 = extension_column_9;
	}
	public String getExtension_column_10() {
		return extension_column_10;
	}
	public void setExtension_column_10(String extension_column_10) {
		this.extension_column_10 = extension_column_10;
	}
	public String getExtension_column_11() {
		return extension_column_11;
	}
	public void setExtension_column_11(String extension_column_11) {
		this.extension_column_11 = extension_column_11;
	}
	public String getExtension_column_12() {
		return extension_column_12;
	}
	public void setExtension_column_12(String extension_column_12) {
		this.extension_column_12 = extension_column_12;
	}
	public String getExtension_column_13() {
		return extension_column_13;
	}
	public void setExtension_column_13(String extension_column_13) {
		this.extension_column_13 = extension_column_13;
	}
	public String getExtension_column_14() {
		return extension_column_14;
	}
	public void setExtension_column_14(String extension_column_14) {
		this.extension_column_14 = extension_column_14;
	}
	public String getExtension_column_15() {
		return extension_column_15;
	}
	public void setExtension_column_15(String extension_column_15) {
		this.extension_column_15 = extension_column_15;
	}
	public String getExtension_column_16() {
		return extension_column_16;
	}
	public void setExtension_column_16(String extension_column_16) {
		this.extension_column_16 = extension_column_16;
	}
	public String getExtension_column_17() {
		return extension_column_17;
	}
	public void setExtension_column_17(String extension_column_17) {
		this.extension_column_17 = extension_column_17;
	}
	public String getExtension_column_18() {
		return extension_column_18;
	}
	public void setExtension_column_18(String extension_column_18) {
		this.extension_column_18 = extension_column_18;
	}
	public String getExtension_column_19() {
		return extension_column_19;
	}
	public void setExtension_column_19(String extension_column_19) {
		this.extension_column_19 = extension_column_19;
	}
	public String getExtension_column_20() {
		return extension_column_20;
	}
	public void setExtension_column_20(String extension_column_20) {
		this.extension_column_20 = extension_column_20;
	}
	public String getPerson_email() {
		return person_email;
	}
	public void setPerson_email(String person_email) {
		this.person_email = person_email;
	}
	public String getRegister_email() {
		return register_email;
	}
	public void setRegister_email(String register_email) {
		this.register_email = register_email;
	}
	public String getParty_type() {
		return party_type;
	}
	public void setParty_type(String party_type) {
		this.party_type = party_type;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getRegister_title() {
		return register_title;
	}
	public void setRegister_title(String register_title) {
		this.register_title = register_title;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPerson_position() {
		return person_position;
	}
	public void setPerson_position(String person_position) {
		this.person_position = person_position;
	}
	public String getPerson_number() {
		return person_number;
	}
	public void setPerson_number(String person_number) {
		this.person_number = person_number;
	}
	public String getContact_approvers() {
		return contact_approvers;
	}
	public void setContact_approvers(String contact_approvers) {
		this.contact_approvers = contact_approvers;
	}
	public String getApprovers_position() {
		return approvers_position;
	}
	public void setApprovers_position(String approvers_position) {
		this.approvers_position = approvers_position;
	}
	public String getApprovers_number() {
		return approvers_number;
	}
	public void setApprovers_number(String approvers_number) {
		this.approvers_number = approvers_number;
	}
	public String getApprovers_email() {
		return approvers_email;
	}
	public void setApprovers_email(String approvers_email) {
		this.approvers_email = approvers_email;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getDbd() {
		return dbd;
	}
	public void setDbd(String dbd) {
		this.dbd = dbd;
	}
	public String getRegister_fulladdress() {
		return register_fulladdress;
	}
	public void setRegister_fulladdress(String register_fulladdress) {
		this.register_fulladdress = register_fulladdress;
	}
	public String getDate_insert_oldsys() {
		return date_insert_oldsys;
	}
	public void setDate_insert_oldsys(String date_insert_oldsys) {
		this.date_insert_oldsys = date_insert_oldsys;
	}
	public String getMerged_duplicate_ids() {
		return merged_duplicate_ids;
	}
	public void setMerged_duplicate_ids(String merged_duplicate_ids) {
		this.merged_duplicate_ids = merged_duplicate_ids;
	}
	public String getId_oldsys() {
		return id_oldsys;
	}
	public void setId_oldsys(String id_oldsys) {
		this.id_oldsys = id_oldsys;
	}
	
	
	
	
	
	
	public int getHeadcount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getHorsepower() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setHighSpender(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public int getCapital() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setController(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public void setDataOriented(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public boolean isHighSpender() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getSearch_id() {
		return search_id;
	}
	public void setSearch_id(String search_id) {
		this.search_id = search_id;
	}
	public String getRegister_capital() {
		return register_capital;
	}
	public void setRegister_capital(String register_capital) {
		this.register_capital = register_capital;
	}
	public String getRegister_horsepower() {
		return register_horsepower;
	}
	public void setRegister_horsepower(String register_horsepower) {
		this.register_horsepower = register_horsepower;
	}
	public String getRegister_headcount() {
		return register_headcount;
	}
	public void setRegister_headcount(String register_headcount) {
		this.register_headcount = register_headcount;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getReqProcessId() {
		return reqProcessId;
	}
	public void setReqProcessId(String reqProcessId) {
		this.reqProcessId = reqProcessId;
	}
	public String getReqExtId() {
		return reqExtId;
	}
	public void setReqExtId(String reqExtId) {
		this.reqExtId = reqExtId;
	}
	public String getReqDateCol() {
		return reqDateCol;
	}
	public void setReqDateCol(String reqDateCol) {
		this.reqDateCol = reqDateCol;
	}
	
	 
	
}

