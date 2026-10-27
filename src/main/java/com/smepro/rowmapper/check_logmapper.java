package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.check_log;


public class check_logmapper implements RowMapper<check_log> {

	@Override
	public check_log mapRow(ResultSet rs, int rowNum) throws SQLException {
		check_log check_log = new check_log();
		
		check_log.setId(rs.getInt("id"));
		check_log.setChecked_date(rs.getString("checked_date"));
		check_log.setChecked_at(rs.getString("checked_at"));

		return check_log;
	}

}
