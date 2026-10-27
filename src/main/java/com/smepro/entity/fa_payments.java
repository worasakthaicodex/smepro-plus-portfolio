package com.smepro.entity;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smepro.dao.Identifiable;
import com.smepro.models.edatas;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString
@Table(name = "fa_payments")
public class fa_payments implements Identifiable  {

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(name = "fp_payment_date")
	private String fpPaymentDate;
	
	private String fp_payment_type;
	private String fp_invoice_id;
	private String fp_payee_id;
	private String fp_payee_name;
	private String fp_list;
	private String fp_amount_net;
	private String fp_payment_method;
	private String fp_reference_number;
	private String  fp_descriptions;
	private String fp_data_nameadd;
	private String fp_approved_by;
	private String fp_confirmation_date;
	private String fp_request_additional;
	private String fp_refund;
	private String fp_actual_usage;
	private String fp_usage_details;
	private String fp_amount_paid;
	private String fp_amount_received;
	private String fp_remaining;
	private String fp_disburser;
	private String fp_confirmer;
	private String fp_additional_date;
	private String fp_second_disbursement;
	private String fp_refund_receipt;
	private String fp_additional_operator;
	private String title;
	
	
	

	
	@Column(name = "edata_id")
	private int edataId;
	
	@ManyToOne
	@JoinColumn(name = "edata_id", insertable = false, updatable = false) // กำหนด readonly ถ้าต้องการใช้ edataId เอง
	private Edata edata;
	
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

			
			
			public String getFp_payment_type() {
			return fp_payment_type;
		}



		public void setFp_payment_type(String fp_payment_type) {
			this.fp_payment_type = fp_payment_type;
		}



		public String getFp_invoice_id() {
			return fp_invoice_id;
		}



		public void setFp_invoice_id(String fp_invoice_id) {
			this.fp_invoice_id = fp_invoice_id;
		}

		public String getFpPaymentDate() {
			return fpPaymentDate;
		}

		public void setFpPaymentDate(String fpPaymentDate) {
			this.fpPaymentDate = fpPaymentDate;
		}
		public Edata getEdata() {
			return edata;
		}



		public void setEdata(Edata edata) {
			this.edata = edata;
		}



		public String getFp_payee_id() {
			return fp_payee_id;
		}



		public void setFp_payee_id(String fp_payee_id) {
			this.fp_payee_id = fp_payee_id;
		}



		public String getFp_payee_name() {
			return fp_payee_name;
		}



		public void setFp_payee_name(String fp_payee_name) {
			this.fp_payee_name = fp_payee_name;
		}



		public String getFp_list() {
			return fp_list;
		}



		public void setFp_list(String fp_list) {
			this.fp_list = fp_list;
		}



		public String getFp_amount_net() {
			return fp_amount_net;
		}



		public void setFp_amount_net(String fp_amount_net) {
			this.fp_amount_net = fp_amount_net;
		}



		public String getFp_payment_method() {
			return fp_payment_method;
		}



		public void setFp_payment_method(String fp_payment_method) {
			this.fp_payment_method = fp_payment_method;
		}



		public String getFp_reference_number() {
			return fp_reference_number;
		}



		public void setFp_reference_number(String fp_reference_number) {
			this.fp_reference_number = fp_reference_number;
		}



		public String getFp_descriptions() {
			return fp_descriptions;
		}



		public void setFp_descriptions(String fp_descriptions) {
			this.fp_descriptions = fp_descriptions;
		}



		public String getFp_data_nameadd() {
			return fp_data_nameadd;
		}



		public void setFp_data_nameadd(String fp_data_nameadd) {
			this.fp_data_nameadd = fp_data_nameadd;
		}



		public String getFp_approved_by() {
			return fp_approved_by;
		}



		public void setFp_approved_by(String fp_approved_by) {
			this.fp_approved_by = fp_approved_by;
		}



		public String getFp_confirmation_date() {
			return fp_confirmation_date;
		}



		public void setFp_confirmation_date(String fp_confirmation_date) {
			this.fp_confirmation_date = fp_confirmation_date;
		}



		public String getFp_request_additional() {
			return fp_request_additional;
		}



		public void setFp_request_additional(String fp_request_additional) {
			this.fp_request_additional = fp_request_additional;
		}



		public String getFp_refund() {
			return fp_refund;
		}



		public void setFp_refund(String fp_refund) {
			this.fp_refund = fp_refund;
		}



		public String getFp_actual_usage() {
			return fp_actual_usage;
		}



		public void setFp_actual_usage(String fp_actual_usage) {
			this.fp_actual_usage = fp_actual_usage;
		}



		public String getFp_usage_details() {
			return fp_usage_details;
		}



		public void setFp_usage_details(String fp_usage_details) {
			this.fp_usage_details = fp_usage_details;
		}



		public String getFp_amount_paid() {
			return fp_amount_paid;
		}



		public void setFp_amount_paid(String fp_amount_paid) {
			this.fp_amount_paid = fp_amount_paid;
		}



		public String getFp_disburser() {
			return fp_disburser;
		}



		public void setFp_disburser(String fp_disburser) {
			this.fp_disburser = fp_disburser;
		}



		public String getFp_confirmer() {
			return fp_confirmer;
		}



		public void setFp_confirmer(String fp_confirmer) {
			this.fp_confirmer = fp_confirmer;
		}



		public String getFp_additional_date() {
			return fp_additional_date;
		}



		public void setFp_additional_date(String fp_additional_date) {
			this.fp_additional_date = fp_additional_date;
		}



		public String getFp_second_disbursement() {
			return fp_second_disbursement;
		}



		public void setFp_second_disbursement(String fp_second_disbursement) {
			this.fp_second_disbursement = fp_second_disbursement;
		}



		public String getFp_refund_receipt() {
			return fp_refund_receipt;
		}



		public void setFp_refund_receipt(String fp_refund_receipt) {
			this.fp_refund_receipt = fp_refund_receipt;
		}



		public String getFp_additional_operator() {
			return fp_additional_operator;
		}



		public void setFp_additional_operator(String fp_additional_operator) {
			this.fp_additional_operator = fp_additional_operator;
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



			public String getFp_amount_received() {
			return fp_amount_received;
		}



		public void setFp_amount_received(String fp_amount_received) {
			this.fp_amount_received = fp_amount_received;
		}



		public String getFp_remaining() {
			return fp_remaining;
		}



		public void setFp_remaining(String fp_remaining) {
			this.fp_remaining = fp_remaining;
		}



		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
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
