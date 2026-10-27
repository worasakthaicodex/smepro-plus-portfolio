package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Asset_log;
import com.smepro.models.Looselink;
import com.smepro.models.Myapp;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;



public class Looselinkmapper implements RowMapper<Looselink> {

	@Override
	public Looselink mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Looselink data = new Looselink();

		 
		 if (hasColumn(rs, "loose_link_id")) {
			 data.setLoose_link_id(rs.getInt("loose_link_id"));
	        }
		 if (hasColumn(rs, "loose_link_table")) {
			 data.setLoose_link_table(rs.getString("loose_link_table"));
	        }

		 if (hasColumn(rs, "loose_link_note")) {
			 data.setLoose_link_note(rs.getString("loose_link_note"));
	        }

		 if (hasColumn(rs, "loose_link_scope1")) {
			 data.setLoose_link_scope1(rs.getInt("loose_link_scope1"));
	        }

		 if (hasColumn(rs, "loose_link_scope2")) {
			 data.setLoose_link_scope2(rs.getInt("loose_link_scope2"));
	        }

		 if (hasColumn(rs, "loose_link_to")) {
			 data.setLoose_link_to(rs.getInt("loose_link_to"));
	        }

		 if (hasColumn(rs, "loose_link_type")) {
			 data.setLoose_link_type(rs.getString("loose_link_type"));
	        }

		 if (hasColumn(rs, "loose_link_key1")) {
			 data.setLoose_link_key1(rs.getString("loose_link_key1"));
	        }
		 if (hasColumn(rs, "loose_link_key2")) {
			 data.setLoose_link_key2(rs.getString("loose_link_key2"));
	        }
		 if (hasColumn(rs, "name")) {
			 data.setLoose_link_tablename(rs.getString("name"));
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
