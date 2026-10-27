package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Myapp;



public class myappmapper implements RowMapper<Myapp> {

	@Override
	public Myapp mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Myapp app = new Myapp();

		 
		 if (hasColumn(rs, "id")) {
			 app.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "movement")) {
			 app.setMovement(rs.getString("movement"));
	        } 
		 
		 if (hasColumn(rs, "register")) {
			 app.setRegister(rs.getString("register"));
	        } 
		 
		 if (hasColumn(rs, "appname")) {
			 app.setAppname(rs.getString("appname"));
	        }
		 if (hasColumn(rs, "enum_type")) {
			 app.setEnum_type(rs.getString("enum_type"));
	        }
		 if (hasColumn(rs, "commitment")) {
			 app.setCommitment(rs.getString("commitment"));
	        }
		 
		 if (hasColumn(rs, "approval_limit_type")) {
			 app.setApproval_limit_type(rs.getInt("approval_limit_type"));
	        }
		 if (hasColumn(rs, "clear")) {
			 app.setClear(rs.getString("clear"));
	        }
		 if (hasColumn(rs, "receive")) {
			 app.setReceive(rs.getString("receive"));
	        }
		 if (hasColumn(rs, "forward")) {
			 app.setForward(rs.getString("forward"));
	        }
		 
		 
		 if (hasColumn(rs, "reference_only")) {
			 app.setReference_only(rs.getString("reference_only"));
	        }
		 
		 if (hasColumn(rs, "hierarchical_reference")) {
			 app.setHierarchical_reference(rs.getString("hierarchical_reference"));
	        }
		 
		 if (hasColumn(rs, "reference_modify")) {
			 app.setReference_modify(rs.getString("reference_modify"));
	        }
		 
		 if (hasColumn(rs, "reference_continue")) {
			 app.setReference_continue(rs.getString("reference_continue"));
	        }
		 
		 if (hasColumn(rs, "clear_register")) {
			 app.setClear_register(rs.getString("clear_register"));
	        } 
		 if (hasColumn(rs, "document_reference")) {
			 app.setDocument_reference(rs.getString("document_reference"));
	        }
		 
		 
		 
		 if (hasColumn(rs, "activate_reference_only")) {
			 app.setActivate_reference_only(rs.getString("activate_reference_only"));
	        }
		 
		 if (hasColumn(rs, "activate_reference_modify")) {
			 app.setActivate_reference_modify(rs.getString("activate_reference_modify"));
	        }
		 
		 if (hasColumn(rs, "activate_hierarchical_reference")) {
			 app.setActivate_hierarchical_reference(rs.getString("activate_hierarchical_reference"));
	        }
		 
		 if (hasColumn(rs, "activate_reference_continue")) {
			 app.setActivate_reference_continue(rs.getString("activate_reference_continue"));
	        }

		 if (hasColumn(rs, "activate_clear_register")) {
			 app.setActivate_clear_register(rs.getString("activate_clear_register"));
	        }
		 

		return app;
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
