package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Feed;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Links;
import com.smepro.models.LinksSetting;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class Row_calculationmapper implements RowMapper<Row_calculation> {

	   @Override
	    public Row_calculation mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Row_calculation row = new Row_calculation();

	        // Basic info
	        row.setId(rs.getInt("id"));
	        row.setId_refer(rs.getInt("id_refer"));
	        row.setRow_name(rs.getString("row_name"));

	        // Number variables (null-safe)
	        row.setNumber_variable1(getDoubleOrNull(rs, "number_variable1"));
	        row.setNumber_variable2(getDoubleOrNull(rs, "number_variable2"));
	        row.setNumber_variable3(getDoubleOrNull(rs, "number_variable3"));
	        row.setNumber_variable4(getDoubleOrNull(rs, "number_variable4"));
	        row.setNumber_variable5(getDoubleOrNull(rs, "number_variable5"));

	        // Calculation Block 1
	        row.setStart_column1(rs.getString("start_column1"));
	        row.setColumn1(rs.getString("column1"));
	        row.setOperation1(rs.getString("operation1"));
	        row.setAs_to1(rs.getString("as_to1"));

	        // Calculation Block 2
	        row.setStart_column2(rs.getString("start_column2"));
	        row.setColumn2(rs.getString("column2"));
	        row.setOperation2(rs.getString("operation2"));
	        row.setAs_to2(rs.getString("as_to2"));

	        // Calculation Block 3
	        row.setStart_column3(rs.getString("start_column3"));
	        row.setColumn3(rs.getString("column3"));
	        row.setOperation3(rs.getString("operation3"));
	        row.setAs_to3(rs.getString("as_to3"));

	        return row;
	    }

	    // Helper: รับ Double จาก DB แบบ null-safe
	    private Double getDoubleOrNull(ResultSet rs, String columnName) throws SQLException {
	        double value = rs.getDouble(columnName);
	        return rs.wasNull() ? null : value;
	    }
}
