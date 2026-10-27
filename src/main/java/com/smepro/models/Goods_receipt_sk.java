package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class Goods_receipt_sk implements Identifiable {
	
	
		private int id;
	 	private int edata_id;
	 	private String doc_code;
	 	private String tempId;
		private String title;
		private String  status;
		private int count;
		
	 	private String skg_inspection_status;
	 	private String skg_received_date;
	 	private String skg_storage_location;
	 	private String skg_received_by;
	 	private String skg_remark;
	 	private Double skg_price;
	 	
	 	
	 	private String skg_pd_code;
		private String skg_lot_batch;
		private String skg_tracking_number;
		private String skg_damage_report;
		private String skg_supplier_code;
		private String skg_supplier_name;
		private String skg_stock_code;
		private String skg_stock_name;
		
	 	private Double skg_quantity_received;	
		private String skg_unit;
		private String skg_expiration_date;
		private Double skg_remaining;
		
		
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
		public String getDoc_code() {
			return doc_code;
		}
		public void setDoc_code(String doc_code) {
			this.doc_code = doc_code;
		}
		public String getTempId() {
			return tempId;
		}
		public void setTempId(String tempId) {
			this.tempId = tempId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getSkg_inspection_status() {
			return skg_inspection_status;
		}
		public void setSkg_inspection_status(String skg_inspection_status) {
			this.skg_inspection_status = skg_inspection_status;
		}
		public String getSkg_received_date() {
			return skg_received_date;
		}
		public void setSkg_received_date(String skg_received_date) {
			this.skg_received_date = skg_received_date;
		}
		public String getSkg_storage_location() {
			return skg_storage_location;
		}
		public void setSkg_storage_location(String skg_storage_location) {
			this.skg_storage_location = skg_storage_location;
		}
		public String getSkg_received_by() {
			return skg_received_by;
		}
		public void setSkg_received_by(String skg_received_by) {
			this.skg_received_by = skg_received_by;
		}
		public String getSkg_remark() {
			return skg_remark;
		}
		public void setSkg_remark(String skg_remark) {
			this.skg_remark = skg_remark;
		}
		public String getSkg_pd_code() {
			return skg_pd_code;
		}
		public void setSkg_pd_code(String skg_pd_code) {
			this.skg_pd_code = skg_pd_code;
		}
		public String getSkg_lot_batch() {
			return skg_lot_batch;
		}
		public void setSkg_lot_batch(String skg_lot_batch) {
			this.skg_lot_batch = skg_lot_batch;
		}
		public String getSkg_tracking_number() {
			return skg_tracking_number;
		}
		public void setSkg_tracking_number(String skg_tracking_number) {
			this.skg_tracking_number = skg_tracking_number;
		}
		public String getSkg_damage_report() {
			return skg_damage_report;
		}
		public void setSkg_damage_report(String skg_damage_report) {
			this.skg_damage_report = skg_damage_report;
		}
		public String getSkg_supplier_code() {
			return skg_supplier_code;
		}
		public void setSkg_supplier_code(String skg_supplier_code) {
			this.skg_supplier_code = skg_supplier_code;
		}
		public String getSkg_supplier_name() {
			return skg_supplier_name;
		}
		public void setSkg_supplier_name(String skg_supplier_name) {
			this.skg_supplier_name = skg_supplier_name;
		}
		public Double getSkg_quantity_received() {
			return skg_quantity_received;
		}
		public void setSkg_quantity_received(Double skg_quantity_received) {
			this.skg_quantity_received = skg_quantity_received;
		}
		public String getSkg_unit() {
			return skg_unit;
		}
		public void setSkg_unit(String skg_unit) {
			this.skg_unit = skg_unit;
		}
		public String getSkg_expiration_date() {
			return skg_expiration_date;
		}
		public void setSkg_expiration_date(String skg_expiration_date) {
			this.skg_expiration_date = skg_expiration_date;
		}
		public Double getSkg_price() {
			return skg_price;
		}
		public void setSkg_price(Double skg_price) {
			this.skg_price = skg_price;
		}
		public String getSkg_stock_code() {
			return skg_stock_code;
		}
		public void setSkg_stock_code(String skg_stock_code) {
			this.skg_stock_code = skg_stock_code;
		}
		public String getSkg_stock_name() {
			return skg_stock_name;
		}
		public void setSkg_stock_name(String skg_stock_name) {
			this.skg_stock_name = skg_stock_name;
		}
		public Double getSkg_remaining() {
			return skg_remaining;
		}
		public void setSkg_remaining(Double skg_remaining) {
			this.skg_remaining = skg_remaining;
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
