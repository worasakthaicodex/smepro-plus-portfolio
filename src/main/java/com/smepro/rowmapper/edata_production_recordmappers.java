package com.smepro.rowmapper;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.edata_production_record;
import com.smepro.models.production_directive;



public class edata_production_recordmappers implements RowMapper<edata_production_record> {

	@Override
	public  edata_production_record mapRow(ResultSet rs, int rowNum) throws SQLException {
		edata_production_record  production = new  edata_production_record();
		 
	
		 if (hasColumn(rs, "id")) {
			 production.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "edata_id")) {
			 production.setEdata_id(rs.getInt("edata_id"));
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
		  if (hasColumn(rs, "department")) {
			  production.setDepartment(rs.getString("department"));
	        }  
		  if (hasColumn(rs, "order_quantity")) {
			  production.setOrder_quantity(rs.getInt("order_quantity"));
	        }  
		  if (hasColumn(rs, "actual_output")) {
			  production.setActual_output(rs.getInt("actual_output"));
	        }  
		  if (hasColumn(rs, "defective_output")) {
			  production.setDefective_output(rs.getInt("defective_output"));
	        }  
		  if (hasColumn(rs, "record_date")) {
			  production.setRecord_date(rs.getString("record_date"));
	        }    
		  if (hasColumn(rs, "recorder")) {
			  production.setRecorder(rs.getString("recorder"));
	        }  
		  
		  if (hasColumn(rs, "inspection_result")) {
			  production.setInspection_result(rs.getString("inspection_result"));
	        } 
		  if (hasColumn(rs, "remarks")) {
			  production.setRemarks(rs.getString("remarks"));
	        }   
		  if (hasColumn(rs, "operator")) {
			  production.setOperator(rs.getString("operator"));
	        } 
		  if (hasColumn(rs, "line")) {
			  production.setLine(rs.getString("line"));
	        } 
		  
		  if (hasColumn(rs, "status")) {
			  production.setStatus(rs.getString("status"));
	        } 
		  
		  if (hasColumn(rs, "shift")) {
			  production.setShift(rs.getString("shift"));
	        } 
		  
		  if (hasColumn(rs, "unit_of_measure")) {
			  production.setUnit_of_measure(rs.getString("unit_of_measure"));
	        } 
		  
		  if (hasColumn(rs, "start_date_time")) {
			  production.setStart_date_time(rs.getString("start_date_time"));
	        } 
		  
		  if (hasColumn(rs, "end_date_time")) {
			  production.setEnd_date_time(rs.getString("end_date_time"));
	        } 
		  
		  if (hasColumn(rs, "machine_id")) {
			  production.setMachine_id(rs.getString("machine_id"));
	        } 
		
		  
		 
		  
		  if (hasColumn(rs, "total_output")) {
			  production.setTotal_output(rs.getInt("total_output"));
	        } 
		  if (hasColumn(rs, "total_defective")) {
			  production.setTotal_defective(rs.getInt("total_defective"));
	        } 
		  if (hasColumn(rs, "outstanding")) {
			  production.setOutstanding(rs.getInt("outstanding"));
	        } 
		  if (hasColumn(rs, "amount_output")) {
			  production.setAmount_output(rs.getInt("amount_output"));
	        } 
		  
		  if (hasColumn(rs, "defective_output_issues")) {
			  production.setDefective_output_issues(rs.getString("defective_output_issues"));
	        } 
		  if (hasColumn(rs, "downtime_title")) {
			  production.setDowntime_title(rs.getString("downtime_title"));
	        } 
		  if (hasColumn(rs, "speed")) {
			  production.setSpeed(rs.getDouble("speed"));
	        } 
		  if (hasColumn(rs, "setup_time")) {
			  production.setSetup_time(rs.getDouble("setup_time"));
	        } 
		  if (hasColumn(rs, "downtime")) {
			  production.setDowntime(rs.getDouble("downtime"));
	        }  
	
		  if (hasColumn(rs, "count")) {
			  production.setCount(rs.getInt("count"));
	        }  
	 
		  if (hasColumn(rs, "reference_code")) {
			  production.setReference_code(rs.getString("reference_code"));
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
