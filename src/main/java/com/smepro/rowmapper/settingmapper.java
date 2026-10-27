package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



import com.smepro.models.setting;

public class settingmapper implements RowMapper<setting> {

	@Override
	public setting mapRow(ResultSet rs, int rowNum) throws SQLException {
		setting settings = new setting();
		settings.setSetting_id(rs.getInt("setting_id"));
		settings.setHoliday_notsun(rs.getInt("holiday_notsun"));
		settings.setVersion(rs.getInt("version"));
		return settings;
	}

}
