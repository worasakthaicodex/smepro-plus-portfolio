package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Footer_calculation;
import com.smepro.models.LinksSetting;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class Footer_calculationmapper implements RowMapper<Footer_calculation> {

	  @Override
	    public Footer_calculation mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Footer_calculation data = new Footer_calculation();

	        // Basic fields
	        data.setId(rs.getInt("id"));
	        data.setId_refer(rs.getInt("id_refer"));
	        data.setFooter_name(rs.getString("footer_name"));

	        // Footer titles
	        data.setFooter_title1(rs.getString("footer_title1"));
	        data.setFooter_title2(rs.getString("footer_title2"));
	        data.setFooter_title3(rs.getString("footer_title3"));

	        // Number variables (จัดการ NULL อย่างปลอดภัย)
	        data.setNumber_variable1(getDoubleOrNull(rs, "number_variable1"));
	        data.setNumber_variable2(getDoubleOrNull(rs, "number_variable2"));
	        data.setNumber_variable3(getDoubleOrNull(rs, "number_variable3"));
	        data.setNumber_variable4(getDoubleOrNull(rs, "number_variable4"));
	        data.setNumber_variable5(getDoubleOrNull(rs, "number_variable5"));

	        // Text variables
	        data.setText_variable1(rs.getString("text_variable1"));
	        data.setText_variable2(rs.getString("text_variable2"));
	        data.setText_variable3(rs.getString("text_variable3"));
	        data.setText_variable4(rs.getString("text_variable4"));

	        // Calculation Block 1
	        data.setStart_column1(rs.getString("start_column1"));
	        data.setColumn1(rs.getString("column1"));
	        data.setOperation1(rs.getString("operation1"));
	        data.setAs_to1(rs.getString("as_to1"));

	        // Calculation Block 2
	        data.setStart_column2(rs.getString("start_column2"));
	        data.setColumn2(rs.getString("column2"));
	        data.setOperation2(rs.getString("operation2"));
	        data.setAs_to2(rs.getString("as_to2"));

	        // Calculation Block 3
	        data.setStart_column3(rs.getString("start_column3"));
	        data.setColumn3(rs.getString("column3"));
	        data.setOperation3(rs.getString("operation3"));
	        data.setAs_to3(rs.getString("as_to3"));

	        return data;
	    }

	    // เมธอดช่วย: แปลง double → Double (null-safe)
	    private Double getDoubleOrNull(ResultSet rs, String columnName) throws SQLException {
	        double value = rs.getDouble(columnName);
	        return rs.wasNull() ? null : value;
	    }


}
