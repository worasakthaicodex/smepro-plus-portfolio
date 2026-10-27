package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;



public class register_logmapper implements RowMapper<register_log> {

	@Override
	public register_log mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		register_log register_logs = new register_log();

		 
		 if (hasColumn(rs, "id")) {
			 register_logs.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "workflow_id")) {
			 register_logs.setWorkflow_id(rs.getInt("workflow_id"));
	        } 
		 if (hasColumn(rs, "edata_id")) {
			 register_logs.setEdata_id(rs.getInt("edata_id"));
	        } 
		 if (hasColumn(rs, "id_add")) {
			 register_logs.setId_add(rs.getInt("id_add"));
	        } 
		 
		 if (hasColumn(rs, "register_type")) {
			 register_logs.setRegister_type(rs.getString("register_type"));
	        }
		 if (hasColumn(rs, "created_at")) {
			 register_logs.setCreated_at(rs.getString("created_at"));
	        }
		 if (hasColumn(rs, "register_group")) {
			 register_logs.setRegister_group(rs.getString("register_group"));
	        }
	
		 
		 
		 if (hasColumn(rs, "register_id")) {
			 register_logs.setRegister_id(rs.getInt("register_id"));
	        }
		 if (hasColumn(rs, "register_name")) {
			 register_logs.setRegister_name(rs.getString("register_name"));
	        } 
	
		 if (hasColumn(rs, "register_groups")) {
			 register_logs.setRegister_groups(rs.getString("register_groups"));
	        }
		 if (hasColumn(rs, "register_address")) {
			 register_logs.setRegister_address(rs.getString("register_address"));
	        }
		 if (hasColumn(rs, "register_note")) {
			 register_logs.setRegister_note(rs.getString("register_note"));
	        } 
		 if (hasColumn(rs, "register_code")) {
			 register_logs.setRegister_code(rs.getString("register_code"));
	        }
		 if (hasColumn(rs, "register_daterun")) {
			 register_logs.setRegister_daterun(rs.getString("register_daterun"));
	        }
		 if (hasColumn(rs, "register_poin")) {
			 register_logs.setRegister_poin(rs.getInt("register_poin"));
	        }
		 
		return register_logs;
		
		
		
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
