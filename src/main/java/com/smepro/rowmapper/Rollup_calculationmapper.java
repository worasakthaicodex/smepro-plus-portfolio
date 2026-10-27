package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Rollup_calculation;
import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class Rollup_calculationmapper implements RowMapper<Rollup_calculation> {

	@Override
	public Rollup_calculation mapRow(ResultSet rs, int rowNum) throws SQLException {
	    Rollup_calculation data = new Rollup_calculation();
	    
	    data.setId(rs.getInt("id"));
	    data.setId_refer(rs.getInt("id_refer"));
	    data.setRollup_name(rs.getString("rollup_name"));
	    
	    // Condition 1
	    data.setCondition_column1(rs.getString("condition_column1"));
	    data.setCondition_operation1(rs.getString("condition_operation1"));
	    data.setCondition_as_to1(rs.getString("condition_as_to1"));
	    
	    // Condition 2
	    data.setCondition2(rs.getString("condition2"));
	    data.setCondition_column2(rs.getString("condition_column2"));
	    data.setCondition_operation2(rs.getString("condition_operation2"));
	    data.setCondition_as_to2(rs.getString("condition_as_to2"));
	    
	    // Condition 3
	    data.setCondition3(rs.getString("condition3"));
	    data.setCondition_column3(rs.getString("condition_column3"));
	    data.setCondition_operation3(rs.getString("condition_operation3"));
	    data.setCondition_as_to3(rs.getString("condition_as_to3"));
	   
	    // Condition 4
	    data.setCondition4(rs.getString("condition4"));
	    data.setCondition_column4(rs.getString("condition_column4"));
	    data.setCondition_operation4(rs.getString("condition_operation4"));
	    data.setCondition_as_to4(rs.getString("condition_as_to4"));
	    
	    
	    // Calculation columns
	    data.setColumn1(rs.getString("column1"));
	    data.setOperation1(rs.getString("operation1"));
	    data.setAs_to1(rs.getString("as_to1"));
	    
	    data.setColumn2(rs.getString("column2"));
	    data.setOperation2(rs.getString("operation2"));
	    data.setAs_to2(rs.getString("as_to2"));
	    
	    data.setColumn3(rs.getString("column3"));
	    data.setOperation3(rs.getString("operation3"));
	    data.setAs_to3(rs.getString("as_to3"));
	    
	    // Number variables (ใช้ getDouble และจัดการ null ด้วย wasNull)
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
	    
	    return data;
	}

	// เมธอดช่วยสำหรับจัดการ null ใน Double
	private Double getDoubleOrNull(ResultSet rs, String columnName) throws SQLException {
	    double value = rs.getDouble(columnName);
	    return rs.wasNull() ? null : value;
	}

}
