package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;


public class missed_uploadsmapper implements RowMapper<missed_uploads> {

	@Override
	public missed_uploads mapRow(ResultSet rs, int rowNum) throws SQLException {
		missed_uploads data = new missed_uploads();
		
		data.setId(rs.getInt("id"));
		data.setUser_name(rs.getString("user_name"));
		data.setGoal_id(rs.getInt("goal_id"));
		data.setMissed_due(rs.getString("missed_due"));
		return data;
	}

}
