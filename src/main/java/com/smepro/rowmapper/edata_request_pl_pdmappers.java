package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_register;
import com.smepro.models.edata_request_pl_pd;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.production_list;


public class edata_request_pl_pdmappers implements RowMapper<edata_request_pl_pd> {

	@Override
	public  edata_request_pl_pd mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_request_pl_pd  production = new  edata_request_pl_pd();
		 

		
		
		 if (hasColumn(rs, "id")) {
			 production.setId(rs.getInt("id"));
	        }
		  if (hasColumn(rs, "edata_id")) {
			  production.setEdata_id(rs.getInt("edata_id"));
	        } 
		  if (hasColumn(rs, "request_type")) {
			  production.setRequest_type(rs.getString("request_type"));
	        } 
		  if (hasColumn(rs, "product_name")) {
			  production.setProduct_name(rs.getString("product_name"));
	        } 
		  if (hasColumn(rs, "order_quantity")) {
			  production.setOrder_quantity(rs.getInt("order_quantity"));
	        } 
		  if (hasColumn(rs, "lot_batch")) {
			  production.setLot_batch(rs.getString("lot_batch"));
	        } 
		  if (hasColumn(rs, "requested_processes")) {
			  production.setRequested_processes(rs.getString("requested_processes"));
	        } 
		  if (hasColumn(rs, "related_departments")) {
			  production.setRelated_departments(rs.getString("related_departments"));
	        } 
		  if (hasColumn(rs, "request_date")) {
			  production.setRequest_date(rs.getString("request_date"));
	        } 
		  if (hasColumn(rs, "requester")) {
			  production.setRequester(rs.getString("requester"));
	        } 
		  if (hasColumn(rs, "status")) {
			  production.setStatus(rs.getString("status"));
	        } 
		  if (hasColumn(rs, "remarks")) {
			  production.setRemarks(rs.getString("remarks"));
	        } 
		  if (hasColumn(rs, "item_code")) {
			  production.setItem_code(rs.getString("item_code"));
	        } 
		return production;
	
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
