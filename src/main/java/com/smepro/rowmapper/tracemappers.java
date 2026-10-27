package com.smepro.rowmapper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.trace;


public class tracemappers implements RowMapper<trace> {


	
	@Override
	public  trace mapRow(ResultSet rs, int rowNum) throws SQLException {
		trace  traces = new  trace();
		
		 if (hasColumn(rs, "id")) {
			 traces.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "trace_name")) {
			 traces.setTrace_name(rs.getString("trace_name"));
	        }

		 if (hasColumn(rs, "trace_description")) {
			 traces.setTrace_description(rs.getString("trace_description"));
	        }
		 if (hasColumn(rs, "trace_join")) {
			 traces.setTrace_join(rs.getInt("trace_join"));
	        }
		 if (hasColumn(rs, "trace_app")) {
			 traces.setTrace_app(rs.getInt("trace_app"));
	        }
		 if (hasColumn(rs, "trace_processes")) {
			 traces.setTrace_processes(rs.getString("trace_processes"));
	        }
		 if (hasColumn(rs, "trace_type")) {
			 traces.setTrace_type(rs.getString("trace_type"));
	        }
		 if (hasColumn(rs, "trace_key")) {
			 traces.setTrace_key(rs.getString("trace_key"));
	        }
		return traces;
	
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
