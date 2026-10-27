package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.showlog;

public class logmapper2 implements RowMapper<showlog> {

	@Override
	public showlog mapRow(ResultSet rs, int rowNum) throws SQLException {
		showlog logs = new showlog();
		logs.setLog_id(rs.getInt("log_id"));
		logs.setLog_id_add(rs.getInt("log_id_add"));
		logs.setLog_date(rs.getString("log_date"));
		logs.setLog_time(rs.getString("log_time"));
		logs.setLog_step(rs.getString("log_step"));
		logs.setLog_refer(rs.getString("log_refer"));
		logs.setLog_folder(rs.getString("log_folder"));
		logs.setLog_note(rs.getString("log_note"));
		logs.setLog_file(rs.getString("log_file"));
		logs.setLog_dive(rs.getString("log_dive"));
		logs.setLog_comment(rs.getString("log_comment"));
		logs.setLog_due(rs.getString("log_due"));
		logs.setLog_flow(rs.getInt("log_flow"));
		
		logs.setUser_name(rs.getString("user_name"));
		logs.setLog_readtime(rs.getString("log_readtime"));
		
		logs.setLog_datestart(rs.getString("log_datestart"));
		logs.setLog_timestart(rs.getString("log_timestart"));
		
		return logs;
	}

}
