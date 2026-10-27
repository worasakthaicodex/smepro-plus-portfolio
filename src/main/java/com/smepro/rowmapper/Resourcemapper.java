package com.smepro.rowmapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Resource;
import com.smepro.models.Taskview;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Resourcemapper implements RowMapper<Resource> {

	@Override
	public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
		Resource data = new Resource();
		data.setId(rs.getLong("id"));
		data.setName(rs.getString("name"));
		data.setDescription(rs.getString("description"));
		data.setResourceType(rs.getString("resource_type"));
		data.setCategory(rs.getString("category"));
		data.setUnit(rs.getString("unit"));
		data.setTaskId(rs.getLong("task_id"));
		
		String costStr = rs.getString("estimated_cost");
		if (costStr != null) {
		    data.setEstimatedCost(new BigDecimal(costStr));
		} else {
		    data.setEstimatedCost(BigDecimal.ZERO); // หรือ null ตาม logic ของคุณ
		}
		
		 if (hasColumn(rs, "actual_cost")) {
				String costStr2 = rs.getString("actual_cost");
				if (costStr2 != null) {
				    data.setActualCost(new BigDecimal(costStr2));
				} else {
				    data.setActualCost(BigDecimal.ZERO); // หรือ null ตาม logic ของคุณ
				}
		 } 
		 
		 if (hasColumn(rs, "actual")) {
			 data.setActual(rs.getInt("actual"));
		 }
		 if (hasColumn(rs, "planned_qty")) {
			 data.setPlannedQty(rs.getInt("planned_qty"));
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
