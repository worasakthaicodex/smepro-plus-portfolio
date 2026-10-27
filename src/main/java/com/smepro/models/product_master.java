package com.smepro.models;

import java.util.List;

import com.smepro.dao.Identifiable;

public class product_master implements Identifiable {

	
	private int id;
	private int product_id;
	private String product_code;
	private String product_names;
	private String product_category;
	private String product_details;
	private String unit;
    private String product_status;
    private String created_date;
    private String last_updated;
    private String product_daterun;
    
    
    private String product_type;
    private String product_dimensions;
    private String product_expiration_date;
    private String product_selling_price;
    private String product_cost_price;
    private String product_wholesale_price;
  
    private String created_at;
    private String updated_at;
    private int revision;
    private String  id_oldsys;
    
    
	

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
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_names() {
		return product_names;
	}
	public void setProduct_names(String product_names) {
		this.product_names = product_names;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_details() {
		return product_details;
	}
	public void setProduct_details(String product_details) {
		this.product_details = product_details;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_daterun() {
		return product_daterun;
	}
	public void setProduct_daterun(String product_daterun) {
		this.product_daterun = product_daterun;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_dimensions() {
		return product_dimensions;
	}
	public void setProduct_dimensions(String product_dimensions) {
		this.product_dimensions = product_dimensions;
	}
	public String getProduct_expiration_date() {
		return product_expiration_date;
	}
	public void setProduct_expiration_date(String product_expiration_date) {
		this.product_expiration_date = product_expiration_date;
	}
	public String getProduct_selling_price() {
		return product_selling_price;
	}
	public void setProduct_selling_price(String product_selling_price) {
		this.product_selling_price = product_selling_price;
	}
	public String getProduct_cost_price() {
		return product_cost_price;
	}
	public void setProduct_cost_price(String product_cost_price) {
		this.product_cost_price = product_cost_price;
	}
	public String getProduct_wholesale_price() {
		return product_wholesale_price;
	}
	public void setProduct_wholesale_price(String product_wholesale_price) {
		this.product_wholesale_price = product_wholesale_price;
	}

	public String getProduct_status() {
		return product_status;
	}

	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getId_oldsys() {
		return id_oldsys;
	}

	public void setId_oldsys(String id_oldsys) {
		this.id_oldsys = id_oldsys;
	}
    
    
}


