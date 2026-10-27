package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Goal;
import com.smepro.models.actionplan;
import com.smepro.models.customreport;
import com.smepro.models.edata;
import com.smepro.models.file;


public class goalmapper implements RowMapper<Goal> {

	@Override
	public  Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goal  data = new  Goal();

		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "name")) {
			 data.setName(rs.getString("name"));
	        }
		 if (hasColumn(rs, "processes_id")) {
			 data.setProcesses_id(rs.getInt("processes_id"));
	        }
		 if (hasColumn(rs, "reference_category")) {
			 data.setReference_category(rs.getString("reference_category"));
	        }
		 if (hasColumn(rs, "reference_category2")) {
			 data.setReference_category2(rs.getString("reference_category2"));
	        }
		 if (hasColumn(rs, "reference_category3")) {
			 data.setReference_category3(rs.getString("reference_category3"));
	        }
		 if (hasColumn(rs, "activity_type")) {
			 data.setActivity_type(rs.getString("activity_type"));
	        }
		 if (hasColumn(rs, "quantity_per_action")) {
			 data.setQuantity_per_action(rs.getInt("quantity_per_action"));
	        }
		 if (hasColumn(rs, "taskBehaviortypes")) {
			 data.setTaskBehaviortypes(rs.getString("taskBehaviortypes"));
	        }
		 if (hasColumn(rs, "frequency")) {
			 data.setFrequency(rs.getString("frequency"));
	        }
		 if (hasColumn(rs, "alert_before_days")) {
			 data.setAlert_before_days(rs.getInt("alert_before_days"));
	        }
		 if (hasColumn(rs, "setto")) {
			 data.setSetto(rs.getString("setto"));
	        }
		 if (hasColumn(rs, "file_tag")) {
			 data.setFile_tag(rs.getString("file_tag"));
	        }
		 
		 if (hasColumn(rs, "start_time")) {
			 data.setStart_time(rs.getString("start_time"));
	        }
		 
		 if (hasColumn(rs, "end_time")) {
			 data.setEnd_time(rs.getString("end_time"));
	        }
		 
		 
		 if (hasColumn(rs, "responsible")) {
			 data.setResponsible(rs.getString("responsible"));
	        }
		 if (hasColumn(rs, "tables")) {
			 data.setTables(rs.getString("tables"));
	        }
		 
		 
		 if (hasColumn(rs, "status")) {
			 data.setStatus(rs.getInt("status"));
	        }
		 
		 if (hasColumn(rs, "updated_at")) {
			 data.setStart_date(rs.getString("updated_at"));
	        }	 
		 
		return data;
	
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
