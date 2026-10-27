package com.smepro.rowmapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Calendar;
import com.smepro.models.Resource;
import com.smepro.models.Taskview;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Calendarmapper implements RowMapper<Calendar> {

	@Override
	public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
		Calendar data = new Calendar();
		data.setId(rs.getInt("id"));
		data.setYear(rs.getInt("year"));
		data.setMonth(rs.getInt("month"));
		data.setDay(rs.getInt("day"));
		data.setEvent(rs.getString("event"));
		data.setStartTime(rs.getString("start_time"));
		data.setEndTime(rs.getString("end_time"));
		data.setIndex_id(rs.getInt("index_id"));
		data.setEform_id(rs.getInt("eform_id"));
		data.setBy_name(rs.getString("by_name"));
		data.setTo_name(rs.getString("to_name"));
		data.setStatus(rs.getInt("status"));
		
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
