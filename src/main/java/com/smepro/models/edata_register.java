package com.smepro.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.smepro.dao.Identifiable;

public class edata_register implements Identifiable   {

	private int id;
	private int register_id;
	private String register_proceedings;
	private int edata_id;
	private String action_type;
	private String customerCode;
	private String register_type;
	private String register_groups;
	private String register_name;
	private String register_address;
	private String register_note;
	private String register_other_conditions;
	private String register_status;
	private String register_ids;

	

	 
	private String register_fulladdress;
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
	 private String register_capital;
	  private String register_horsepower;
	 private String register_headcount;	 
	 
	 
	  private String party_type;	//บุคคลธรรมดา / /นิติบุคคล
	  private String product_category;   //Other Food //Other Catergories
	 private String register_title; //บจก วิสาหกิจชุมชน
	 private String fax; 

	 private String person_position;
	 private String person_number;
	 private String person_email;
	 private String contact_approvers;
	 private String approvers_position;
	 private String approvers_number;
	 private String approvers_email;
	 private String region;
	 private String lat;
	 private String lon;
	 private String dbd;
	 
	 private String register_poin;
	 private String date_insert_oldsys;
	 private String register_email;
	 private String merged_duplicate_ids;
	 private String id_oldsys;

	 
		private List<Object> join1;
		private List<Object> join2;
 

		private int processId;


		@Override
		public void setJoin1(List<Object> join1) {
			this.join1 = join1;
		}

		public List<Object> getJoin1() {
			return join1;
		}
		@Override
		public void setJoin2(List<Object> join2) {
			this.join2 = join2;
		}

		public List<Object> getJoin2() {
			return join2;
		}
		


	  @Override
	    public int getProcessId() {
	        return this.processId;
	 }

	    public void setProcessId(int processId) {
	        this.processId = processId;
	 }
		
	 
	@Override
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
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
	public String getRegister_name() {
		return register_name;
	}
	public void setRegister_name(String register_name) {
		this.register_name = register_name;
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
	public String getRegister_other_conditions() {
		return register_other_conditions;
	}
	public void setRegister_other_conditions(String register_other_conditions) {
		this.register_other_conditions = register_other_conditions;
	}
	public String getRegister_status() {
		return register_status;
	}
	public void setRegister_status(String register_status) {
		this.register_status = register_status;
	}
	public int getRegister_id() {
		return register_id;
	}
	public void setRegister_id(int register_id) {
		this.register_id = register_id;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getRegister_ids() {
		return register_ids;
	}
	public void setRegister_ids(String register_ids) {
		this.register_ids = register_ids;
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
	public String getRegister_proceedings() {
		return register_proceedings;
	}
	public void setRegister_proceedings(String register_proceedings) {
		this.register_proceedings = register_proceedings;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	public String getPerson_email() {
		return person_email;
	}

	public void setPerson_email(String person_email) {
		this.person_email = person_email;
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
	public String getRegister_poin() {
		return register_poin;
	}

	public void setRegister_poin(String register_poin) {
		this.register_poin = register_poin;
	}

	public String getDate_insert_oldsys() {
		return date_insert_oldsys;
	}

	public void setDate_insert_oldsys(String date_insert_oldsys) {
		this.date_insert_oldsys = date_insert_oldsys;
	}

	public String getRegister_email() {
		return register_email;
	}

	public void setRegister_email(String register_email) {
		this.register_email = register_email;
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

	@Override
	public String toString() {
		return String.format(
				"edata_register [id=%s, register_id=%s, register_proceedings=%s, edata_id=%s, action_type=%s, customerCode=%s, register_type=%s, register_groups=%s, register_name=%s, register_address=%s, register_note=%s, register_other_conditions=%s, register_status=%s, register_ids=%s, contact_person=%s, phone_number=%s, mobile_number=%s, website=%s, social=%s, tax_id=%s, branch_code=%s, business_type=%s, payment_terms=%s, credit_limit=%s, bank_account=%s, preferred_currency=%s, join1=%s, join2=%s, processId=%s, getJoin1()=%s, getJoin2()=%s, getProcessId()=%s, getId()=%s, getEdata_id()=%s, getRegister_type()=%s, getRegister_groups()=%s, getRegister_name()=%s, getRegister_address()=%s, getRegister_note()=%s, getRegister_other_conditions()=%s, getRegister_status()=%s, getRegister_id()=%s, getAction_type()=%s, getRegister_ids()=%s, getContact_person()=%s, getPhone_number()=%s, getMobile_number()=%s, getWebsite()=%s, getSocial()=%s, getTax_id()=%s, getBranch_code()=%s, getBusiness_type()=%s, getPayment_terms()=%s, getCredit_limit()=%s, getBank_account()=%s, getPreferred_currency()=%s, getRegister_proceedings()=%s, getCustomerCode()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				id, register_id, register_proceedings, edata_id, action_type, customerCode, register_type,
				register_groups, register_name, register_address, register_note, register_other_conditions,
				register_status, register_ids, contact_person, phone_number, mobile_number, website, social, tax_id,
				branch_code, business_type, payment_terms, credit_limit, bank_account, preferred_currency, join1, join2,
				processId, getJoin1(), getJoin2(), getProcessId(), getId(), getEdata_id(), getRegister_type(),
				getRegister_groups(), getRegister_name(), getRegister_address(), getRegister_note(),
				getRegister_other_conditions(), getRegister_status(), getRegister_id(), getAction_type(),
				getRegister_ids(), getContact_person(), getPhone_number(), getMobile_number(), getWebsite(),
				getSocial(), getTax_id(), getBranch_code(), getBusiness_type(), getPayment_terms(), getCredit_limit(),
				getBank_account(), getPreferred_currency(), getRegister_proceedings(), getCustomerCode(), getClass(),
				hashCode(), super.toString());
	}
	
	
	
	public List<String> head() {
		    List<String> columnNames = new ArrayList<>();
		    // ดึงเฉพาะฟิลด์ที่ประกาศไว้ในคลาสนี้
		    Field[] fields = this.getClass().getDeclaredFields();
		    
		    for (Field field : fields) {
		        columnNames.add(field.getName());
		    }
		    return columnNames;
	}
	
	
	
	public void reset() {
	    for (Field field : this.getClass().getDeclaredFields()) {
	        field.setAccessible(true);
	        Class<?> type = field.getType();
	        try {
	            if (type.isPrimitive()) {
	                if (type == boolean.class) field.setBoolean(this, false);
	                else if (type == byte.class) field.setByte(this, (byte)0);
	                else if (type == short.class) field.setShort(this, (short)0);
	                else if (type == int.class) field.setInt(this, 0);
	                else if (type == long.class) field.setLong(this, 0L);
	                else if (type == float.class) field.setFloat(this, 0f);
	                else if (type == double.class) field.setDouble(this, 0d);
	                else if (type == char.class) field.setChar(this, '\u0000');
	            } else {
	                field.set(this, null);
	            }
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }
	}


	
}


