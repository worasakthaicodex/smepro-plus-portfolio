package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class Goods_receipt_fg implements Identifiable {
	
	
		private int id;
	 	private int edata_id;
	 	private String doc_code;
	 	private String tempId;
		private String title;
		private String  status;
		private int count;
		
	 	private String receipt_date;
	 	private String work_order_no;
	 	private String location;
	 	private String received_by;
	 	private String approved_by;
	 	
	 	private String remark;

	 	private String goods_receipt_pd_code;
		private String goods_receipt_lot_batch;
		private String goods_receipt_product_code;
		private String goods_receipt_product_name;
		private String goods_receipt_customer_code;
		private String goods_receipt_customer_name;	
	 	private Double goods_receipt_quantity;	
		private String goods_receipt_unit;
		private Double goods_receipt_remaining = 0.0;

		

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
		public String getReceipt_date() {
			return receipt_date;
		}
		public void setReceipt_date(String receipt_date) {
			this.receipt_date = receipt_date;
		}
		public String getWork_order_no() {
			return work_order_no;
		}
		public void setWork_order_no(String work_order_no) {
			this.work_order_no = work_order_no;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getReceived_by() {
			return received_by;
		}
		public void setReceived_by(String received_by) {
			this.received_by = received_by;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getGoods_receipt_pd_code() {
			return goods_receipt_pd_code;
		}
		public void setGoods_receipt_pd_code(String goods_receipt_pd_code) {
			this.goods_receipt_pd_code = goods_receipt_pd_code;
		}
		public String getGoods_receipt_lot_batch() {
			return goods_receipt_lot_batch;
		}
		public void setGoods_receipt_lot_batch(String goods_receipt_lot_batch) {
			this.goods_receipt_lot_batch = goods_receipt_lot_batch;
		}
		public String getGoods_receipt_product_code() {
			return goods_receipt_product_code;
		}
		public void setGoods_receipt_product_code(String goods_receipt_product_code) {
			this.goods_receipt_product_code = goods_receipt_product_code;
		}
		public String getGoods_receipt_product_name() {
			return goods_receipt_product_name;
		}
		public void setGoods_receipt_product_name(String goods_receipt_product_name) {
			this.goods_receipt_product_name = goods_receipt_product_name;
		}
		public Double getGoods_receipt_quantity() {
			return goods_receipt_quantity;
		}
		public void setGoods_receipt_quantity(Double goods_receipt_quantity) {
			this.goods_receipt_quantity = goods_receipt_quantity;
		}
		public String getGoods_receipt_unit() {
			return goods_receipt_unit;
		}
		public void setGoods_receipt_unit(String goods_receipt_unit) {
			this.goods_receipt_unit = goods_receipt_unit;
		}
		public String getGoods_receipt_customer_code() {
			return goods_receipt_customer_code;
		}
		public void setGoods_receipt_customer_code(String goods_receipt_customer_code) {
			this.goods_receipt_customer_code = goods_receipt_customer_code;
		}
		public String getGoods_receipt_customer_name() {
			return goods_receipt_customer_name;
		}
		public void setGoods_receipt_customer_name(String goods_receipt_customer_name) {
			this.goods_receipt_customer_name = goods_receipt_customer_name;
		}
		public String getApproved_by() {
			return approved_by;
		}
		public void setApproved_by(String approved_by) {
			this.approved_by = approved_by;
		}
		public Double getGoods_receipt_remaining() {
			return goods_receipt_remaining;
		}
		public void setGoods_receipt_remaining(Double goods_receipt_remaining) {
			this.goods_receipt_remaining = goods_receipt_remaining;
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
