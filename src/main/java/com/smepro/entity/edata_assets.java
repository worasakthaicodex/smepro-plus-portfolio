package com.smepro.entity;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smepro.dao.Identifiable;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name = "edata_assets")
public class edata_assets implements Identifiable  {

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	
	private String asset_proceedings;
	private String doc_code;
	private String asset_name;
	private String asset_group;
	private String asset_type;
	private String asset_description;
	private String acquisition_cost;
	private String useful_life_years;
	private String depreciation_method;
	

	
	
	
	private String book_value;
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


			public String getAsset_proceedings() {
				return asset_proceedings;
			}


			public void setAsset_proceedings(String asset_proceedings) {
				this.asset_proceedings = asset_proceedings;
			}


			public String getDoc_code() {
				return doc_code;
			}


			public void setDoc_code(String doc_code) {
				this.doc_code = doc_code;
			}


			public String getAsset_name() {
				return asset_name;
			}


			public void setAsset_name(String asset_name) {
				this.asset_name = asset_name;
			}


			public String getAsset_group() {
				return asset_group;
			}


			public void setAsset_group(String asset_group) {
				this.asset_group = asset_group;
			}


			public String getAsset_type() {
				return asset_type;
			}


			public void setAsset_type(String asset_type) {
				this.asset_type = asset_type;
			}


			public String getAsset_description() {
				return asset_description;
			}


			public void setAsset_description(String asset_description) {
				this.asset_description = asset_description;
			}


			public String getAcquisition_cost() {
				return acquisition_cost;
			}


			public void setAcquisition_cost(String acquisition_cost) {
				this.acquisition_cost = acquisition_cost;
			}


			public String getUseful_life_years() {
				return useful_life_years;
			}


			public void setUseful_life_years(String useful_life_years) {
				this.useful_life_years = useful_life_years;
			}


			public String getDepreciation_method() {
				return depreciation_method;
			}


			public void setDepreciation_method(String depreciation_method) {
				this.depreciation_method = depreciation_method;
			}


			public String getBook_value() {
				return book_value;
			}


			public void setBook_value(String book_value) {
				this.book_value = book_value;
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
