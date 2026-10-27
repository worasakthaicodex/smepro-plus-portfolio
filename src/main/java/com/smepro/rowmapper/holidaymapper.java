package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.holiday;

public class holidaymapper implements RowMapper<holiday> {

	@Override
	public holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
		holiday holidays = new holiday();
		holidays.setHoliday_id(rs.getInt("holiday_id"));
		holidays.setHoliday_year(rs.getInt("holiday_year"));
		holidays.setHoliday_type(rs.getString("holiday_type"));
		holidays.setHoliday_date(rs.getString("holiday_date"));
		holidays.setHoliday_name(rs.getString("holiday_name"));
		return holidays;
	}

}
