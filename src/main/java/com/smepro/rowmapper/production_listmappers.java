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
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.production_list;


public class production_listmappers implements RowMapper<production_list> {

	@Override
	public  production_list mapRow(ResultSet rs, int rowNum) throws SQLException {
		production_list  production = new  production_list();
		 
		 
		 if (hasColumn(rs, "id")) {
			 production.setId(rs.getInt("id"));
	        }
		 
		 if (hasColumn(rs, "tempId")) {
			 production.setTempId(rs.getString("tempId"));
	        }
		 
		  if (hasColumn(rs, "pl_code")) {
			  production.setPl_code(rs.getString("pl_code"));
	        } 
		  if (hasColumn(rs, "lot_batch")) {
			  production.setLot_batch(rs.getString("lot_batch"));
	        } 
		  if (hasColumn(rs, "product_code")) {
			  production.setProduct_code(rs.getString("product_code"));
	        } 
		  if (hasColumn(rs, "product_name")) {
			  production.setProduct_name(rs.getString("product_name"));
	        } 
		  if (hasColumn(rs, "order_quantity")) {
			  production.setOrder_quantity(rs.getInt("order_quantity"));
	        } 
		  if (hasColumn(rs, "start_date")) {
			  production.setStart_date(rs.getString("start_date"));
	        } 
		  if (hasColumn(rs, "end_date")) {
			  production.setEnd_date(rs.getString("end_date"));
	        }    
		  if (hasColumn(rs, "processes")) {
			  production.setProcesses(rs.getString("processes"));
	        } 
		  if (hasColumn(rs, "status")) {
			  production.setStatus(rs.getString("status"));
	        } 
		  if (hasColumn(rs, "customer_code")) {
			  production.setCustomer_code(rs.getString("customer_code"));
	        } 
		  if (hasColumn(rs, "customer_name")) {
			  production.setCustomer_name(rs.getString("customer_name"));
	        } 
		  if (hasColumn(rs, "item_code")) {
			  production.setItem_code(rs.getString("item_code"));
	        } 
		  
		  if (hasColumn(rs, "po_code")) {
			  production.setPo_code(rs.getString("po_code"));
	        } 
		  
		  if (hasColumn(rs, "count")) {
			  production.setCount(rs.getInt("count"));
	        }  	
		  
		  if (hasColumn(rs, "sub_order_quantity")) {
			  production.setSub_order_quantity(rs.getInt("sub_order_quantity"));
	        }  	
		  
		  if (hasColumn(rs, "department")) {
			  production.setDepartment(rs.getString("department"));
	        }  	
		  
		  if (hasColumn(rs, "sub_unit")) {
			  production.setSub_unit(rs.getString("sub_unit"));
	        }  	
		  if (hasColumn(rs, "unit")) {
			  production.setUnit(rs.getString("unit"));
	        }  	
		  if (hasColumn(rs, "edata_id")) {
			  production.setEdata_id(rs.getInt("edata_id"));
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
