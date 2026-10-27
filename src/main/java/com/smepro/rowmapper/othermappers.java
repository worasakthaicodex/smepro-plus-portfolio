package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.file;
import com.smepro.models.other;


public class othermappers implements RowMapper<other> {

	@Override
	public  other mapRow(ResultSet rs, int rowNum) throws SQLException {
		other  action = new  other();
		 
			  
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "edata_id")) {
			 action.setEdata_id(rs.getInt("edata_id"));
	        }
		 if (hasColumn(rs, "tempId")) {
			 action.setTempId(rs.getString("tempId"));
	        }
		  if (hasColumn(rs, "product_code")) {
			  action.setProduct_code(rs.getString("product_code"));
	        } 
		 if (hasColumn(rs, "product_name")) {
				  action.setProduct_name(rs.getString("product_name"));
		  } 
	     if (hasColumn(rs, "lot_batch")) {
				  action.setLot_batch(rs.getString("lot_batch"));
		}   	 
	     
	     if (hasColumn(rs, "lot")) {
			  action.setLot(rs.getString("lot"));
	}   	
	     
		if (hasColumn(rs, "process_step")) {
				  action.setProcess_step(rs.getString("process_step"));
		 }   	
		if (hasColumn(rs, "department")) {
				  action.setDepartment(rs.getString("department"));
		 }   	
		if (hasColumn(rs, "doc_code")) {
			  action.setDoc_code(rs.getString("doc_code"));
	 }   	
		
		
		if (hasColumn(rs, "list")) {
			  action.setList(rs.getString("list"));
	 }   	
		
		if (hasColumn(rs, "employee")) {
			  action.setEmployee(rs.getString("employee"));
	 }   	
		if (hasColumn(rs, "inspection")) {
			  action.setInspection(rs.getString("inspection"));
	 }   	
			
		if (hasColumn(rs, "inspection2")) {
			  action.setInspection2(rs.getString("inspection2"));
	 }   	
		if (hasColumn(rs, "inspection3")) {
			  action.setInspection3(rs.getString("inspection3"));
	 }   	
		if (hasColumn(rs, "inspection4")) {
			  action.setInspection4(rs.getString("inspection4"));
	 }   	
		if (hasColumn(rs, "inspection5")) {
			  action.setInspection5(rs.getString("inspection5"));
	 }   	
		if (hasColumn(rs, "inspection6")) {
			  action.setInspection6(rs.getString("inspection6"));
	 }   	
		
		
		
		
		if (hasColumn(rs, "equipment")) {
			  action.setEquipment(rs.getString("equipment"));
	 }   	
		
		if (hasColumn(rs, "date_start")) {
			  action.setDate_start(rs.getString("date_start"));
	 }   	
		
		  if (hasColumn(rs, "count")) {
			  action.setCount(rs.getInt("count"));
	        }  	
	
		  if (hasColumn(rs, "grouping")) {
			  action.setGrouping(rs.getString("grouping"));
	        } 
		  if (hasColumn(rs, "classification")) {
			  action.setClassification(rs.getString("classification"));
	        } 
		  
		  
		  if (hasColumn(rs, "order_quantity")) {
			  action.setOrder_quantity(rs.getDouble("order_quantity"));
	        } 
		  if (hasColumn(rs, "unit")) {
			  action.setUnit(rs.getString("unit"));
	        } 
		  if (hasColumn(rs, "received_today")) {
			  action.setReceived_today(rs.getDouble("received_today"));
	        } 
		  if (hasColumn(rs, "received_accumulated")) {
			  action.setReceived_accumulated(rs.getDouble("received_accumulated"));
	        } 
		  if (hasColumn(rs, "pending_received")) {
			  action.setPending_received(rs.getDouble("pending_received"));
	        } 
		  if (hasColumn(rs, "paid_today")) {
			  action.setPaid_today(rs.getDouble("paid_today"));
	        } 
		  if (hasColumn(rs, "paid_accumulated")) {
			  action.setPaid_accumulated(rs.getDouble("paid_accumulated"));
	        } 
		  if (hasColumn(rs, "pending_paid")) {
			  action.setPending_paid(rs.getDouble("pending_paid"));
	        } 
		  if (hasColumn(rs, "reference_code")) {
			  action.setReference_code(rs.getString("reference_code"));
	        } 
		  
		  if (hasColumn(rs, "data_nameadd")) {
				 action.setData_nameadd(rs.getString("data_nameadd"));
		        }  
		  

		  
		
		return action;
		
	
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
