package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Measurement_rules;
import com.smepro.models.Measurement_values;
import com.smepro.models.actionplan;
import com.smepro.models.edata;
import com.smepro.models.purchase;
import com.smepro.models.stock;


public class Measurement_valuesmappers implements RowMapper<Measurement_values> {

	@Override
	public  Measurement_values mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Measurement_values  data = new  Measurement_values();
			data.setId(rs.getInt("id"));
			data.setMeasurement_rules_id(rs.getInt("measurement_rules_id"));
			data.setEdata_id(rs.getInt("edata_id"));
			data.setPercentage_value0(rs.getString("percentage_value0"));
			data.setPercentage_value10(rs.getString("percentage_value10"));
			data.setPercentage_value20(rs.getString("percentage_value20"));
			data.setPercentage_value30(rs.getString("percentage_value30"));
			data.setPercentage_value40(rs.getString("percentage_value40"));
			data.setPercentage_value50(rs.getString("percentage_value50"));
			data.setPercentage_value60(rs.getString("percentage_value60"));
			data.setPercentage_value70(rs.getString("percentage_value70"));
			data.setPercentage_value80(rs.getString("percentage_value80"));
			data.setPercentage_value90(rs.getString("percentage_value90"));
			data.setPercentage_value100(rs.getString("percentage_value100"));
			data.setCurrent_value(rs.getString("current_value"));
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
