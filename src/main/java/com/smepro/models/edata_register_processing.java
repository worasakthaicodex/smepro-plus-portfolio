package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class edata_register_processing implements Identifiable {

	private int id;

	private int edata_id;
	private String processing_id;
	private String supplier_id;
	private String supplier_name;
	private String doc_refer;
	
	
	// rating
	private String quality;
	private String value;
	private String delivery;
	private String support;
	private String reliability;
	private String flexibility;
	private String user_experience;
	private String sustainability;
	private String partnership;
	

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
	public String getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getDoc_refer() {
		return doc_refer;
	}
	public void setDoc_refer(String doc_refer) {
		this.doc_refer = doc_refer;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getSupport() {
		return support;
	}
	public void setSupport(String support) {
		this.support = support;
	}
	public String getReliability() {
		return reliability;
	}
	public void setReliability(String reliability) {
		this.reliability = reliability;
	}
	public String getFlexibility() {
		return flexibility;
	}
	public void setFlexibility(String flexibility) {
		this.flexibility = flexibility;
	}
	public String getUser_experience() {
		return user_experience;
	}
	public void setUser_experience(String user_experience) {
		this.user_experience = user_experience;
	}
	public String getSustainability() {
		return sustainability;
	}
	public void setSustainability(String sustainability) {
		this.sustainability = sustainability;
	}
	public String getPartnership() {
		return partnership;
	}
	public void setPartnership(String partnership) {
		this.partnership = partnership;
	}
	public String getProcessing_id() {
		return processing_id;
	}
	public void setProcessing_id(String processing_id) {
		this.processing_id = processing_id;
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