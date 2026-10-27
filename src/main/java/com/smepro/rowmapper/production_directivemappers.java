package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.production_directive;



public class production_directivemappers implements RowMapper<production_directive> {

	@Override
	public  production_directive mapRow(ResultSet rs, int rowNum) throws SQLException {
		production_directive  production = new  production_directive();
		 
	
		 if (hasColumn(rs, "id")) {
			 production.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "tempId")) {
			 production.setTempId(rs.getString("tempId"));
	        }
		 
		 
		 if (hasColumn(rs, "edata_id")) {
			 production.setEdata_id(rs.getInt("edata_id"));
	        }
		 
		 if (hasColumn(rs, "pd_code")) {
			 production.setPd_code(rs.getString("pd_code"));
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
		  if (hasColumn(rs, "process_step")) {
			  production.setProcess_step(rs.getString("process_step"));
	        } 
		  if (hasColumn(rs, "quantity")) {
			  production.setQuantity(rs.getInt("quantity"));
	        } 
		  if (hasColumn(rs, "start_date")) {
			  production.setStart_date(rs.getString("start_date"));
	        }  
		  if (hasColumn(rs, "end_date")) {
			  production.setEnd_date(rs.getString("end_date"));
	        }  
		  if (hasColumn(rs, "status")) {
			  production.setStatus(rs.getString("status"));
	        }  
		  if (hasColumn(rs, "responsible_person")) {
			  production.setResponsible_person(rs.getString("responsible_person"));
	        } 
		  if (hasColumn(rs, "remarks")) {
			  production.setRemarks(rs.getString("remarks"));
	        } 
		  
		  if (hasColumn(rs, "line")) {
			  production.setLine(rs.getString("line"));
	        } 
		  if (hasColumn(rs, "actual_output")) {
			  production.setActual_output(rs.getString("actual_output"));
	        } 
		  if (hasColumn(rs, "defective_output")) {
			  production.setDefective_output(rs.getString("defective_output"));
	        } 
		  if (hasColumn(rs, "item_code")) {
			  production.setItem_code(rs.getString("item_code"));
	        } 
		  
		  if (hasColumn(rs, "department")) {
			  production.setDepartment(rs.getString("department"));
	        } 
		  if (hasColumn(rs, "count")) {
			  production.setCount(rs.getInt("count"));
	        }  	
		  if (hasColumn(rs, "order_quantity")) {
			  production.setOrder_quantity(rs.getInt("order_quantity"));
	        }  	
		  if (hasColumn(rs, "plan_time")) {
			  production.setPlan_time(rs.getString("plan_time"));
	        }  	
		  
		  if (hasColumn(rs, "sub_unit")) {
			  production.setSub_unit(rs.getString("sub_unit"));
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
