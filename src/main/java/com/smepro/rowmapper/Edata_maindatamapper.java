package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Relations;
import com.smepro.models.condition;


public class Edata_maindatamapper implements RowMapper<Edata_maindata> {

	@Override
	public 	Edata_maindata mapRow(ResultSet rs, int rowNum) throws SQLException {
		Edata_maindata data = new 	Edata_maindata();
		data.setEdata_id(rs.getInt("edata_id"));
		data.setMain_data_id(rs.getInt("main_data_id"));
		data.setEntity(rs.getString("entity"));

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

