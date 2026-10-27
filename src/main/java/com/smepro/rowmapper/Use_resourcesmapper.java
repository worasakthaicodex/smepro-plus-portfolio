package com.smepro.rowmapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Resource;
import com.smepro.models.Taskview;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.Use_resources;
import com.smepro.models.upload_pn;

public class Use_resourcesmapper implements RowMapper<Use_resources> {

	@Override
	public Use_resources mapRow(ResultSet rs, int rowNum) throws SQLException {
		Use_resources data = new Use_resources();
	
		data.setId(rs.getInt("id"));
		data.setResources_id(rs.getInt("resources_id"));
		data.setTask_id(rs.getInt("task_id"));


		
		 if (hasColumn(rs, "name")) {
				data.setName(rs.getString("name"));
		 }
		 if (hasColumn(rs, "actual")) {
			 data.setActual(rs.getInt("actual"));
		 }
		 if (hasColumn(rs, "planned_qty")) {
			 data.setPlannedQty(rs.getInt("planned_qty"));
		 }
		 
		 if (hasColumn(rs, "description")) {
			 data.setDescription(rs.getString("description"));
		 }
		 if (hasColumn(rs, "resource_type")) {
			 data.setResourceType(rs.getString("resource_type"));
		 }
		 if (hasColumn(rs, "category")) {
				data.setCategory(rs.getString("category"));
		 }
		 if (hasColumn(rs, "unit")) {
				data.setUnit(rs.getString("unit"));
		 }
	
		
		 if (hasColumn(rs, "actual_cost")) {
				String costStr = rs.getString("actual_cost");
				if (costStr != null) {
				    data.setActualCost(new BigDecimal(costStr));
				} else {
				    data.setActualCost(BigDecimal.ZERO); // หรือ null ตาม logic ของคุณ
				}
		 } 
		 if (hasColumn(rs, "estimated_cost")) {
				String costStr = rs.getString("estimated_cost");
				if (costStr != null) {
				    data.setEstimatedCost(new BigDecimal(costStr));
				} else {
				    data.setEstimatedCost(BigDecimal.ZERO); // หรือ null ตาม logic ของคุณ
				}
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
