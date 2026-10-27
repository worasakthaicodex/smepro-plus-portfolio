package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;


public class traceabilityedata_fgmapper implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		edata  edatas = new  edata();

		
		 if (hasColumn(rs, "data_id")) {
			 edatas.setData_id(rs.getInt("data_id"));
	        }
		 if (hasColumn(rs, "title")) {
			 edatas.setTitle(rs.getString("title"));
	        }
		 
		 if (hasColumn(rs, "data_nameadd")) {
			 edatas.setData_nameadd(rs.getString("data_nameadd"));
	        }
		 
		 

		 if (hasColumn(rs, "issued_pd_code")) {
			 if(rs.getString("issued_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("issued_pd_code"));}
	        }
		 if (hasColumn(rs, "issued_lot_batch")) {
			 if(rs.getString("issued_lot_batch") != null) {	 edatas.setBatch(rs.getString("issued_lot_batch"));}
	        }
		 if (hasColumn(rs, "issued_product_name")) {
			 if(rs.getString("issued_product_name") != null) {	 edatas.setProduct(rs.getString("issued_product_name"));}
	        }

		 if (hasColumn(rs, "issued_customer_name")) {
			 if(rs.getString("issued_customer_name") != null) {	 edatas.setCustomer(rs.getString("issued_customer_name"));}
	        }
		 if (hasColumn(rs, "issue_type")) {

			 if(rs.getString("issue_type") != null) {	 edatas.setIssue_type(rs.getString("issue_type"));}
	        }
		 if (hasColumn(rs, "issued_quantity")) {
			 if(rs.getString("issued_quantity") != null) {	 edatas.setQuantity(rs.getString("issued_quantity"));}
	        }
		 if (hasColumn(rs, "issued_by")) {
			 if(rs.getString("issued_by") != null) {	 edatas.setData_nameadd(rs.getString("issued_by"));}
	        }
		 if (hasColumn(rs, "issued_unit")) {
			 if(rs.getString("issued_unit") != null) {	 edatas.setUnit(rs.getString("issued_unit"));}
	        }
		 if (hasColumn(rs, "issue_date")) {

			 if(rs.getString("issue_date") != null) {	 edatas.setStart(rs.getString("issue_date"));}
	        } 
		
	
		 
		 if (hasColumn(rs, "adjustment_pd_code")) {
			 if(rs.getString("adjustment_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("adjustment_pd_code"));}
	        }
		 if (hasColumn(rs, "adjustment_lot_batch")) {
			 if(rs.getString("adjustment_lot_batch") != null) {	 edatas.setBatch(rs.getString("adjustment_lot_batch"));}
	        }
		 if (hasColumn(rs, "adjustment_product_name")) {
			 if(rs.getString("adjustment_product_name") != null) {	 edatas.setProduct(rs.getString("adjustment_product_name"));}
	        }
		 if (hasColumn(rs, "adjustment_quantity")) {
			 if(rs.getString("adjustment_quantity") != null) {	 edatas.setQuantity(rs.getString("adjustment_quantity"));}
	        }
	
		 if (hasColumn(rs, "adjustment_unit")) {
			 if(rs.getString("adjustment_unit") != null) {	 edatas.setUnit(rs.getString("adjustment_unit"));}
	        }
		 if (hasColumn(rs, "adjustment_date")) {
			 if(rs.getString("adjustment_date") != null) {	 edatas.setStart(rs.getString("adjustment_date"));}
	        } 
		 
		 

		 
		 if (hasColumn(rs, "goods_receipt_pd_code")) {
			 if(rs.getString("goods_receipt_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("goods_receipt_pd_code"));}
	        }
		 if (hasColumn(rs, "goods_receipt_lot_batch")) {

			 if(rs.getString("goods_receipt_lot_batch") != null) {	 edatas.setBatch(rs.getString("goods_receipt_lot_batch"));}
	        }
		 if (hasColumn(rs, "goods_receipt_product_name")) {

			 if(rs.getString("goods_receipt_product_name") != null) {	 edatas.setProduct(rs.getString("goods_receipt_product_name"));}
	        }

		 if (hasColumn(rs, "goods_receipt_customer_name")) {
			 if(rs.getString("goods_receipt_customer_name") != null) {	 edatas.setCustomer(rs.getString("goods_receipt_customer_name"));}
	        }
		 if (hasColumn(rs, "goods_receipt_quantity")) {

			 if(rs.getString("goods_receipt_quantity") != null) {	 edatas.setQuantity(rs.getString("goods_receipt_quantity"));}
	        }
		 if (hasColumn(rs, "received_by")) {
			 if(rs.getString("received_by") != null) {	 edatas.setData_nameadd(rs.getString("received_by"));}
		
	        }
		 if (hasColumn(rs, "goods_receipt_unit")) {

			 if(rs.getString("goods_receipt_unit") != null) {	 edatas.setUnit(rs.getString("goods_receipt_unit"));}
	        }
		 if (hasColumn(rs, "receipt_date")) {

			 if(rs.getString("receipt_date") != null) {	 edatas.setStart(rs.getString("receipt_date"));}
	        } 
		 
		 
		 
		 
		 
	 
		 if (hasColumn(rs, "transfer_pd_code")) {
			 if(rs.getString("transfer_pd_code") != null) {	 edatas.setProduction_directive(rs.getString("transfer_pd_code"));}
	        }
		 if (hasColumn(rs, "transfer_lot_batch")) {
			 if(rs.getString("transfer_lot_batch") != null) {	 edatas.setBatch(rs.getString("transfer_lot_batch"));}
	        }
		 if (hasColumn(rs, "transfer_product_name")) {

			 if(rs.getString("transfer_product_name") != null) {	 edatas.setProduct(rs.getString("transfer_product_name"));}
	        }
		 if (hasColumn(rs, "transfer_quantity")) {

			 if(rs.getString("transfer_quantity") != null) {	 edatas.setQuantity(rs.getString("transfer_quantity"));}
	        }
		 if (hasColumn(rs, "transferred_by")) {
			 if(rs.getString("transferred_by") != null) {	
				 edatas.setData_nameadd(rs.getString("transferred_by"));}
	        }
		 if (hasColumn(rs, "transfer_unit")) {

			 if(rs.getString("transfer_unit") != null) {	 edatas.setUnit(rs.getString("transfer_unit"));}
	        }
		 if (hasColumn(rs, "transfer_date")) {

			 if(rs.getString("transfer_date") != null) {	 edatas.setStart(rs.getString("transfer_date"));}
	        } 
		 
		 if (hasColumn(rs, "transfer_from_location")) {
			 if(rs.getString("transfer_from_location") != null) {	 edatas.setFrom_location(rs.getString("transfer_from_location"));}
	        }
		 if (hasColumn(rs, "transfer_to_location")) {
			 if(rs.getString("transfer_to_location") != null) {	 edatas.setTo_location(rs.getString("transfer_to_location"));}
	        }
		 
		 
		 
		 
		 
		return edatas;
		
	}

	   private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	        ResultSetMetaData rsMetaData = rs.getMetaData();
	        int columnCount = rsMetaData.getColumnCount();
	        for (int i = 1; i <= columnCount; i++) {
	            if (columnName.equalsIgnoreCase(rsMetaData.getColumnName(i))) {
	                return true;
	            }
	        }
	        return false;
	    }
	
	
}
