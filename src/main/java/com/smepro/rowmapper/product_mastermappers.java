package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.smepro.models.bom;
import com.smepro.models.product_master;



public class product_mastermappers implements RowMapper<product_master> {

	@Override
	public product_master mapRow(ResultSet rs, int rowNum) throws SQLException {
		product_master  action = new  product_master();
		 
		
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "product_id")) {
			 action.setProduct_id(rs.getInt("product_id"));
	        }


		 if (hasColumn(rs, "product_code")) {
			 action.setProduct_code(rs.getString("product_code"));
	        } 
		 
		 if (hasColumn(rs, "product_names")) {
			 action.setProduct_names(rs.getString("product_names"));
	        }
		 
		 if (hasColumn(rs, "product_category")) {
			 action.setProduct_category(rs.getString("product_category"));
	        }
		  if (hasColumn(rs, "product_details")) {
			  action.setProduct_details(rs.getString("product_details"));
	      } 
		if (hasColumn(rs, "unit")) {
				  action.setUnit(rs.getString("unit"));
		} 
		if (hasColumn(rs, "product_status")) {
				  action.setProduct_status(rs.getString("product_status"));
		}   	 
		if (hasColumn(rs, "created_date")) {
				  action.setCreated_date(rs.getString("created_date"));
		}   	
		if (hasColumn(rs, "last_updated")) {
			  action.setLast_updated(rs.getString("last_updated"));
	}   		 
		if (hasColumn(rs, "product_daterun")) {
			  action.setProduct_daterun(rs.getString("product_daterun"));
	}   	
		
		
		if (hasColumn(rs, "product_type")) {
			  action.setProduct_type(rs.getString("product_type"));
	}   	
		
		if (hasColumn(rs, "product_dimensions")) {
			  action.setProduct_dimensions(rs.getString("product_dimensions"));
	}   	
		
		if (hasColumn(rs, "product_expiration_date")) {
			  action.setProduct_expiration_date(rs.getString("product_expiration_date"));
	}   	
		
		if (hasColumn(rs, "product_selling_price")) {
			  action.setProduct_selling_price(rs.getString("product_selling_price"));
	}   	
		
		if (hasColumn(rs, "product_cost_price")) {
			  action.setProduct_cost_price(rs.getString("product_cost_price"));
	}   	
		if (hasColumn(rs, "product_wholesale_price")) {
			  action.setProduct_wholesale_price(rs.getString("product_wholesale_price"));
	}   	
		
		if (hasColumn(rs, "created_at")) {
			  action.setCreated_at(rs.getString("created_at"));
	}   	
			  
		if (hasColumn(rs, "updated_at")) {
			  action.setUpdated_at(rs.getString("updated_at"));
	}   	
		if (hasColumn(rs, "revision")) {
			  action.setRevision(rs.getInt("revision"));
	}   		  
		if (hasColumn(rs, "product_status")) {
			  action.setProduct_status(rs.getString("product_status"));
	}   		  
		
		if (hasColumn(rs, "id_oldsys")) {
			action.setId_oldsys(rs.getString("id_oldsys"));
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
