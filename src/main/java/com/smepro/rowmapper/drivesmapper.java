package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.drives;


public class drivesmapper implements RowMapper<drives> {

	@Override
	public drives mapRow(ResultSet rs, int rowNum) throws SQLException {
		drives drivess = new drives();
		
		  if (hasColumn(rs, "drives_id")) {
			  drivess.setDrives_id(rs.getInt("drives_id"));
	        } 
		  if (hasColumn(rs, "drives_name")) {
			  drivess.setDrives_name(rs.getString("drives_name"));
	        } 
		  if (hasColumn(rs, "drives_size")) {
			  drivess.setDrives_size(rs.getLong("drives_size"));
	        } 
		  if (hasColumn(rs, "drives_drive")) {
			  drivess.setDrives_drive(rs.getString("drives_drive"));
	        } 
		  if (hasColumn(rs, "drives_size2")) {
			  drivess.setDrives_size2(rs.getString("drives_size2"));
	        } 
		  if (hasColumn(rs, "drives_use")) {
			  drivess.setDrives_use(rs.getLong("drives_use"));
	        } 
		  if (hasColumn(rs, "drives_use2")) {
			  drivess.setDrives_use2(rs.getString("drives_use2"));
	        } 
		  if (hasColumn(rs, "drives_usedisplay")) {
			  drivess.setDrives_usedisplay(rs.getLong("drives_usedisplay"));
	        } 
		  if (hasColumn(rs, "drives_remain")) {
			  drivess.setDrives_remain(rs.getLong("drives_remain"));
	        } 
		  if (hasColumn(rs, "drives_remain2")) {
			  drivess.setDrives_remain2(rs.getString("drives_remain2"));
	        } 
		


		return drivess;
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
