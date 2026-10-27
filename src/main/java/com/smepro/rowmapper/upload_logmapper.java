package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.actionplan;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;
import com.smepro.models.upload_log;


public class upload_logmapper implements RowMapper<upload_log> {

	@Override
	public upload_log mapRow(ResultSet rs, int rowNum) throws SQLException {
		upload_log data = new upload_log();
		data.setId(rs.getInt("id"));
		data.setUser_name(rs.getString("user_name"));
		data.setGoal_id(rs.getInt("goal_id"));
		data.setEdata_id(rs.getInt("edata_id"));
		data.setUpload_date(rs.getString("upload_date"));
		data.setUpload_time(rs.getString("upload_time"));
		data.setStatus(rs.getInt("status"));
		return data;
	}

}
