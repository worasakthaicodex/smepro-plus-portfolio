package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.smepro.models.bom;



public class bommappers implements RowMapper<bom> {

	@Override
	public bom mapRow(ResultSet rs, int rowNum) throws SQLException {
		bom  action = new  bom();
		 
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }

		 if (hasColumn(rs, "bom_refer")) {
			 action.setBom_refer(rs.getString("bom_refer"));
	        } 
		 
		 if (hasColumn(rs, "bom_code")) {
			 action.setBom_code(rs.getString("bom_code"));
	        }

		 if (hasColumn(rs, "bom_description")) {
			 action.setBom_description(rs.getString("bom_description"));
	        }
		 
		 if (hasColumn(rs, "bom_base_quantity")) {
			 action.setBom_base_quantity(rs.getDouble("bom_base_quantity"));
	        }
		  if (hasColumn(rs, "bom_base_unit")) {
			  action.setBom_base_unit(rs.getString("bom_base_unit"));
	      } 
		if (hasColumn(rs, "bom_version")) {
				  action.setBom_version(rs.getInt("bom_version"));
		} 
		if (hasColumn(rs, "bom_note")) {
				  action.setBom_note(rs.getString("bom_note"));
		}   	 
		if (hasColumn(rs, "bom_status")) {
				  action.setBom_status(rs.getString("bom_status"));
		}   	
			 
		if (hasColumn(rs, "tempId")) {
			  action.setTempId(rs.getString("tempId"));
	}   	
	
		if (hasColumn(rs, "bom_category")) {
			  action.setBom_category(rs.getString("bom_category"));
	}   	
		
		if (hasColumn(rs, "bom_total_cost")) {
			  action.setBom_total_cost(rs.getDouble("bom_total_cost"));
	  }   	
		
		if (hasColumn(rs, "bom_price")) {
			  action.setBom_price(rs.getDouble("bom_price"));
	 }   	
		
		if (hasColumn(rs, "edata_id")) {
			  action.setEdata_id(rs.getInt("edata_id"));
	}   	
		
		if (hasColumn(rs, "bom_name")) {
			  action.setBom_name(rs.getString("bom_name"));
	}   
		if (hasColumn(rs, "bom_wastage")) {
			  action.setBom_wastage(rs.getDouble("bom_wastage"));
	}   
	
		
		if (hasColumn(rs, "bom_product_name")) {
			  action.setBom_product_name(rs.getString("bom_product_name"));
	}   
		if (hasColumn(rs, "bom_product_code")) {
			  action.setBom_product_code(rs.getString("bom_product_code"));
	}   
		
		  if (hasColumn(rs, "count")) {
			  action.setCount(rs.getInt("count"));
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
