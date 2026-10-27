package com.smepro.entity;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smepro.dao.Identifiable;


@Entity
@Table(name = "sales_followup")
public class sales_followup implements Identifiable  {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String doc_status;
	private String telesale;
	private String  salesperson;
	private String   adminperson;
	private String  segmentation_profile_static_snapshot;
	
	private String  followup_date;                 
	private String   note;    
	private String followup_status;
	private String  fo_requested;          
	private String  qt_requested;       
	private String  so_requested;     
	private String   customer_grade;
	
	

	private String  customer_name;
	private String   customer_province;
	private String   customer_type;
	private String  customer_contact;
	private String   customer_request_change;
	private String   last_followup_date;
	private String service_type;
	private String   service_name;
	private String  service_description;
	private String  urgency_level;
	private String   has_team;
	private String   problem_found;
	private String   missing_equipment;
	private String    assigned_consultant;
	private String responsible_company;
	private String  expected_price;
	private String  suggested_price;
	private String  competitor_price;
	private int closing_chance;
	private String  buying_behavior;
	private String promotion_info;
	
	@Column(name = "promotion_code") // บังคับให้เป็น snake_case ตามมาตรฐานฐานข้อมูล
	private String promotionCode;
	
	
	private String  lists;
	private String register_email;
	
	private String price_per_unit;
	private String  unit_count;
	private String  unit_type;
	private String  discount;
	private String  total;
	
	
	private String  grand_total;
	private String grand_discount;
	private String  grand_total2;
	private String  vat;
	private String  net_amount;
	
	private String  unit_total;
	private String  main_unit_type;
	private String  main_type_service;
	
	
	private String  payment_terms;
	private String additional_note;
	private String  estimated_days;
	private String  estimated_consultant_count;
	private String  payment_date;
	private String payment_amount;
	private String services_offered;
	private String product_type;

	
	
	private String customer_characteristics;
	private String strategy;
	
	@Column(name = "contactRole", columnDefinition = "TEXT")
	private String contactRole;
	
	@Column(name = "serviceSchedule", columnDefinition = "TEXT")
	private String serviceSchedule;
	
	@Column(name = "customer_code")
	private String customerCode;
	@Column(name = "product_code")
	private String productCode;
	private String  product_name;
	
	
	
	@Column(name = "edata_id")
	private int edataId;
	
	private String tempId; 
		private String  doc;
		
		@Transient
		private String  check;
		@Transient
		private String  id_refer;
		@Transient
		private int count;
		
		@Column(name = "created_at", updatable = false, insertable = false)
		private Timestamp createdAt;

		@Column(name = "updated_at", insertable = false)
		private Timestamp updatedAt;
		
		
		@Transient
		private List<Object> join1;
		@Transient
		private List<Object> join2;
    

	  @Column(name = "processId", columnDefinition = "int default 0")
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
		        return this.id;
		 
		  }


		public String getDoc_status() {
			return doc_status;
		}


		public void setDoc_status(String doc_status) {
			this.doc_status = doc_status;
		}


		public String getTelesale() {
			return telesale;
		}


		public void setTelesale(String telesale) {
			this.telesale = telesale;
		}


		public String getSalesperson() {
			return salesperson;
		}


		public void setSalesperson(String salesperson) {
			this.salesperson = salesperson;
		}


		public String getAdminperson() {
			return adminperson;
		}


		public void setAdminperson(String adminperson) {
			this.adminperson = adminperson;
		}


		public String getFollowup_date() {
			return followup_date;
		}


		public void setFollowup_date(String followup_date) {
			this.followup_date = followup_date;
		}


		public String getNote() {
			return note;
		}


		public void setNote(String note) {
			this.note = note;
		}


		public String getFollowup_status() {
			return followup_status;
		}


		public void setFollowup_status(String followup_status) {
			this.followup_status = followup_status;
		}


		public String getFo_requested() {
			return fo_requested;
		}


		public void setFo_requested(String fo_requested) {
			this.fo_requested = fo_requested;
		}


		public String getQt_requested() {
			return qt_requested;
		}


		public void setQt_requested(String qt_requested) {
			this.qt_requested = qt_requested;
		}


		public String getSo_requested() {
			return so_requested;
		}


		public void setSo_requested(String so_requested) {
			this.so_requested = so_requested;
		}


		public String getCustomer_grade() {
			return customer_grade;
		}


		public void setCustomer_grade(String customer_grade) {
			this.customer_grade = customer_grade;
		}


		public String getCustomer_name() {
			return customer_name;
		}


		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}


		public String getCustomer_province() {
			return customer_province;
		}


		public void setCustomer_province(String customer_province) {
			this.customer_province = customer_province;
		}


		public String getCustomer_type() {
			return customer_type;
		}


		public void setCustomer_type(String customer_type) {
			this.customer_type = customer_type;
		}


		public String getCustomer_contact() {
			return customer_contact;
		}


		public void setCustomer_contact(String customer_contact) {
			this.customer_contact = customer_contact;
		}


		public String getCustomer_request_change() {
			return customer_request_change;
		}


		public void setCustomer_request_change(String customer_request_change) {
			this.customer_request_change = customer_request_change;
		}


		public String getLast_followup_date() {
			return last_followup_date;
		}


		public void setLast_followup_date(String last_followup_date) {
			this.last_followup_date = last_followup_date;
		}


		public String getService_type() {
			return service_type;
		}


		public void setService_type(String service_type) {
			this.service_type = service_type;
		}


		public String getService_name() {
			return service_name;
		}


		public void setService_name(String service_name) {
			this.service_name = service_name;
		}


		public String getService_description() {
			return service_description;
		}


		public void setService_description(String service_description) {
			this.service_description = service_description;
		}


		public String getUrgency_level() {
			return urgency_level;
		}


		public void setUrgency_level(String urgency_level) {
			this.urgency_level = urgency_level;
		}


		public String getHas_team() {
			return has_team;
		}


		public void setHas_team(String has_team) {
			this.has_team = has_team;
		}


		public String getProblem_found() {
			return problem_found;
		}


		public void setProblem_found(String problem_found) {
			this.problem_found = problem_found;
		}


		public String getMissing_equipment() {
			return missing_equipment;
		}


		public void setMissing_equipment(String missing_equipment) {
			this.missing_equipment = missing_equipment;
		}


		public String getAssigned_consultant() {
			return assigned_consultant;
		}


		public void setAssigned_consultant(String assigned_consultant) {
			this.assigned_consultant = assigned_consultant;
		}


		public String getResponsible_company() {
			return responsible_company;
		}


		public void setResponsible_company(String responsible_company) {
			this.responsible_company = responsible_company;
		}


		public String getExpected_price() {
			return expected_price;
		}


		public void setExpected_price(String expected_price) {
			this.expected_price = expected_price;
		}


		public String getSuggested_price() {
			return suggested_price;
		}


		public void setSuggested_price(String suggested_price) {
			this.suggested_price = suggested_price;
		}


		public String getCompetitor_price() {
			return competitor_price;
		}


		public void setCompetitor_price(String competitor_price) {
			this.competitor_price = competitor_price;
		}


		public int getClosing_chance() {
			return closing_chance;
		}


		public void setClosing_chance(int closing_chance) {
			this.closing_chance = closing_chance;
		}


		public String getBuying_behavior() {
			return buying_behavior;
		}
		
		
	
		
		
		
		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getProduct_name() {
			return product_name;
		}

		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}


		public void setBuying_behavior(String buying_behavior) {
			this.buying_behavior = buying_behavior;
		}


		public String getPromotion_info() {
			return promotion_info;
		}


		public void setPromotion_info(String promotion_info) {
			this.promotion_info = promotion_info;
		}


		public String getLists() {
			return lists;
		}


		public void setLists(String lists) {
			this.lists = lists;
		}


		public String getPrice_per_unit() {
			return price_per_unit;
		}


		public void setPrice_per_unit(String price_per_unit) {
			this.price_per_unit = price_per_unit;
		}


		public String getUnit_count() {
			return unit_count;
		}


		public void setUnit_count(String unit_count) {
			this.unit_count = unit_count;
		}


		public String getUnit_type() {
			return unit_type;
		}


		public void setUnit_type(String unit_type) {
			this.unit_type = unit_type;
		}


		public String getDiscount() {
			return discount;
		}


		public void setDiscount(String discount) {
			this.discount = discount;
		}


		public String getPayment_terms() {
			return payment_terms;
		}


		public void setPayment_terms(String payment_terms) {
			this.payment_terms = payment_terms;
		}


		public String getAdditional_note() {
			return additional_note;
		}


		public void setAdditional_note(String additional_note) {
			this.additional_note = additional_note;
		}


		public String getEstimated_days() {
			return estimated_days;
		}


		public void setEstimated_days(String estimated_days) {
			this.estimated_days = estimated_days;
		}


		public String getEstimated_consultant_count() {
			return estimated_consultant_count;
		}


		public void setEstimated_consultant_count(String estimated_consultant_count) {
			this.estimated_consultant_count = estimated_consultant_count;
		}


		public String getPayment_date() {
			return payment_date;
		}


		public void setPayment_date(String payment_date) {
			this.payment_date = payment_date;
		}


		public String getPayment_amount() {
			return payment_amount;
		}


		public void setPayment_amount(String payment_amount) {
			this.payment_amount = payment_amount;
		}





		public int getEdataId() {
			return edataId;
		}


		public void setEdataId(int edataId) {
			this.edataId = edataId;
		}


		public String getTempId() {
			return tempId;
		}


		public void setTempId(String tempId) {
			this.tempId = tempId;
		}


		public String getDoc() {
			return doc;
		}


		public void setDoc(String doc) {
			this.doc = doc;
		}


		public String getCheck() {
			return check;
		}


		public void setCheck(String check) {
			this.check = check;
		}


		public String getId_refer() {
			return id_refer;
		}


		public void setId_refer(String id_refer) {
			this.id_refer = id_refer;
		}


		public int getCount() {
			return count;
		}


		public void setCount(int count) {
			this.count = count;
		}


		public Timestamp getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt;
		}


		public Timestamp getUpdatedAt() {
			return updatedAt;
		}


		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getTotal() {
			return total;
		}


		public void setTotal(String total) {
			this.total = total;
		}


		public String getGrand_total() {
			return grand_total;
		}


		public void setGrand_total(String grand_total) {
			this.grand_total = grand_total;
		}


		public String getNet_amount() {
			return net_amount;
		}


		public void setNet_amount(String net_amount) {
			this.net_amount = net_amount;
		}


		public String getVat() {
			return vat;
		}


		public void setVat(String vat) {
			this.vat = vat;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}

		public String getCustomerCode() {
			return customerCode;
		}

		public void setCustomerCode(String customerCode) {
			this.customerCode = customerCode;
		}
		
		

		public String getSegmentation_profile_static_snapshot() {
			return segmentation_profile_static_snapshot;
		}

		public void setSegmentation_profile_static_snapshot(String segmentation_profile_static_snapshot) {
			this.segmentation_profile_static_snapshot = segmentation_profile_static_snapshot;
		}
		public String getGrand_total2() {
			return grand_total2;
		}

		public void setGrand_total2(String grand_total2) {
			this.grand_total2 = grand_total2;
		}
		
		
		

		public String getServices_offered() {
			return services_offered;
		}

		public void setServices_offered(String services_offered) {
			this.services_offered = services_offered;
		}

		public String getUnit_total() {
			return unit_total;
		}

		public void setUnit_total(String unit_total) {
			this.unit_total = unit_total;
		}

		public String getMain_unit_type() {
			return main_unit_type;
		}

		public void setMain_unit_type(String main_unit_type) {
			this.main_unit_type = main_unit_type;
		}

		public String getMain_type_service() {
			return main_type_service;
		}

		public void setMain_type_service(String main_type_service) {
			this.main_type_service = main_type_service;
		}

		public String getGrand_discount() {
			return grand_discount;
		}

		public void setGrand_discount(String grand_discount) {
			this.grand_discount = grand_discount;
		}

		public String getProduct_type() {
			return product_type;
		}

		public void setProduct_type(String product_type) {
			this.product_type = product_type;
		}

		public String getRegister_email() {
			return register_email;
		}

		public void setRegister_email(String register_email) {
			this.register_email = register_email;
		}

		
		
		public String getPromotionCode() {
			return promotionCode;
		}

		public void setPromotionCode(String promotionCode) {
			this.promotionCode = promotionCode;
		}

		public String getCustomer_characteristics() {
			return customer_characteristics;
		}

		public void setCustomer_characteristics(String customer_characteristics) {
			this.customer_characteristics = customer_characteristics;
		}

		public String getStrategy() {
			return strategy;
		}

		public void setStrategy(String strategy) {
			this.strategy = strategy;
		}
		
		
		

		public String getContactRole() {
			return contactRole;
		}

		public void setContactRole(String contactRole) {
			this.contactRole = contactRole;
		}

		public String getServiceSchedule() {
			return serviceSchedule;
		}

		public void setServiceSchedule(String serviceSchedule) {
			this.serviceSchedule = serviceSchedule;
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
		
		
		@PrePersist
		@PreUpdate
		public void cleanEmptyStringsBeforeSave() {
		    // ถ้าพบว่าเป็น String ว่างๆ ให้เปลี่ยนเป็น null ทันที
		    if (this.segmentation_profile_static_snapshot != null && this.segmentation_profile_static_snapshot.trim().isEmpty()) {
		        this.segmentation_profile_static_snapshot = null;
		    }
		}
		  
		  
}
