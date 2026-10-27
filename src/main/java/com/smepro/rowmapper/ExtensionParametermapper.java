package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.ExtensionParameter;
import com.smepro.models.UseExtension;
import com.smepro.models.process;


public class ExtensionParametermapper implements RowMapper<ExtensionParameter> {

	@Override
	public ExtensionParameter mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ExtensionParameter data = new ExtensionParameter();

		 
		 if (hasColumn(rs, "id")) {
			 data.setId(rs.getInt("id"));
	        }
		 if (hasColumn(rs, "extension_process")) {
			 data.setExtension_process(rs.getString("extension_process"));
	        } 
		 
		 if (hasColumn(rs, "parameter_keyname")) {
			 data.setParameter_keyname(rs.getString("parameter_keyname"));
	        } 
		 
		 if (hasColumn(rs, "parameter_column")) {
			 data.setParameter_column(rs.getString("parameter_column"));
	        } 
		 
		 if (hasColumn(rs, "parameter_type")) {
			 data.setParameter_type(rs.getString("parameter_type"));
	        }
		 if (hasColumn(rs, "parameter_columnismain")) {
			 data.setParameter_columnismain(rs.getString("parameter_columnismain"));
	        }
		 if (hasColumn(rs, "use_extension_id")) {
			 data.setUse_extension_id(rs.getInt("use_extension_id"));
	        }
		 if (hasColumn(rs, "field_postion")) {
			 data.setField_postion(rs.getInt("field_postion"));
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
