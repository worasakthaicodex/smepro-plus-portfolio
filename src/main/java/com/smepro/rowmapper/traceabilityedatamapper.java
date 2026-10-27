package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;


public class traceabilityedatamapper implements RowMapper<edata> {

	@Override
	public  edata mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		edata  edatas = new  edata();

		 if (hasColumn(rs, "data_id")) {
			 edatas.setData_id(rs.getInt("data_id"));
	        }
		 if (hasColumn(rs, "title")) {
			 edatas.setTitle(rs.getString("title"));
	        }
		 
		 if (hasColumn(rs, "process_step")) {
			 edatas.setTitle(rs.getString("process_step"));
	        }
		 if (hasColumn(rs, "machine_id")) {
			 edatas.setEquipment(rs.getString("machine_id"));
	        }
		 if (hasColumn(rs, "line")) {
			 edatas.setLine(rs.getString("line"));
	        }
		 
		 if (hasColumn(rs, "inspection")) {
			 edatas.setInspection_result(rs.getString("inspection"));
	        }
		 if (hasColumn(rs, "note")) {
			 edatas.setDeviations(rs.getString("note"));
	        }
		 if (hasColumn(rs, "start_date_time")) {
			 edatas.setStart(rs.getString("start_date_time"));
	        }
		 if (hasColumn(rs, "end_date_time")) {
			 edatas.setEnd(rs.getString("end_date_time"));
	        }
		 if (hasColumn(rs, "actual_output")) {
			 edatas.setGood_production_output(rs.getString("actual_output"));
	        }
		 if (hasColumn(rs, "trace_type")) {
			 edatas.setProduction_directive(rs.getString("trace_type"));
	        }
		 
		 if (hasColumn(rs, "reference_code")) {
			 edatas.setProduction_directive(rs.getString("reference_code"));
	        }
		 if (hasColumn(rs, "lot_batch")) {
			 edatas.setBatch(rs.getString("lot_batch"));
	        }
		 
		 if (hasColumn(rs, "data_nameadd")) {
			 edatas.setData_nameadd(rs.getString("data_nameadd"));
	        }
		 
		 if (hasColumn(rs, "operator")) {
			 
			 if(rs.getString("operator") != null) {
				 edatas.setData_nameadd(rs.getString("operator"));
			 }
			 
			
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
