package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.smepro.models.bom;
import com.smepro.models.product_master;
import com.smepro.models.production_standard;



public class production_standardmappers implements RowMapper<production_standard> {

	@Override
	public production_standard mapRow(ResultSet rs, int rowNum) throws SQLException {
		production_standard  action = new  production_standard();
		 
	
		 if (hasColumn(rs, "id")) {
			 action.setId(rs.getInt("id"));
	        }

		 if (hasColumn(rs, "title")) {
			 action.setTitle(rs.getString("title"));
	        } 
		 
		 if (hasColumn(rs, "ps_code")) {
			 action.setPs_code(rs.getString("ps_code"));
	        }
		 
		 if (hasColumn(rs, "process_step")) {
			 action.setProcess_step(rs.getString("process_step"));
	        }
		 
		 if (hasColumn(rs, "product_code")) {
			 action.setProduct_code(rs.getString("product_code"));
	        }
		  if (hasColumn(rs, "product_name")) {
			  action.setProduct_name(rs.getString("product_name"));
	      } 
		if (hasColumn(rs, "title_string1")) {
				  action.setTitle_string1(rs.getString("title_string1"));
		} 
		if (hasColumn(rs, "target_string1")) {
				  action.setTarget_string1(rs.getString("target_string1"));
		}   
		if (hasColumn(rs, "title_string2")) {
			  action.setTitle_string2(rs.getString("title_string2"));
	} 
		if (hasColumn(rs, "target_string2")) {
				  action.setTarget_string2(rs.getString("target_string2"));
		}   
			if (hasColumn(rs, "title_string3")) {
				  action.setTitle_string3(rs.getString("title_string3"));
		} 
		if (hasColumn(rs, "target_string3")) {
				  action.setTarget_string3(rs.getString("target_string3"));
		}   
		if (hasColumn(rs, "title_string4")) {
			  action.setTitle_string4(rs.getString("title_string4"));
		} 
		if (hasColumn(rs, "target_string4")) {
			  action.setTarget_string4(rs.getString("target_string4"));
		}   
		if (hasColumn(rs, "title_string5")) {
			  action.setTitle_string5(rs.getString("title_string5"));
		} 
		if (hasColumn(rs, "target_string5")) {
			  action.setTarget_string5(rs.getString("target_string5"));
		}   
		
		
		
		if (hasColumn(rs, "title_number1")) {
				  action.setTitle_number1(rs.getString("title_number1"));
		}   	
			if (hasColumn(rs, "target_number1")) {
				  action.setTarget_number1(rs.getString("target_number1"));
		}   	
			
			if (hasColumn(rs, "title_number2")) {
					  action.setTitle_number2(rs.getString("title_number2"));
			}   	
			if (hasColumn(rs, "target_number2")) {
					  action.setTarget_number2(rs.getString("target_number2"));
			}   
	
			if (hasColumn(rs, "title_number3")) {
						  action.setTitle_number3(rs.getString("title_number3"));
			}   	
			if (hasColumn(rs, "target_number3")) {
						  action.setTarget_number3(rs.getString("target_number3"));
			}   
	
			if (hasColumn(rs, "title_number4")) {
							  action.setTitle_number4(rs.getString("title_number4"));
			}   	
			if (hasColumn(rs, "target_number4")) {
							  action.setTarget_number4(rs.getString("target_number4"));
			}   
		
			if (hasColumn(rs, "title_number5")) {
								  action.setTitle_number5(rs.getString("title_number5"));
			}   	
			if (hasColumn(rs, "target_number5")) {
								  action.setTarget_number5(rs.getString("target_number5"));
			}   
			if (hasColumn(rs, "tempId")) {
				  action.setTempId(rs.getString("tempId"));
		}   		 
	 
			
			
			if (hasColumn(rs, "note")) {
				  action.setNote(rs.getString("note"));
		}   		 
			if (hasColumn(rs, "edata_id")) {
				  action.setEdata_id(rs.getInt("edata_id"));
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
