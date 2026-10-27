package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.log;

public class logmappers5 implements RowMapper<log> {

	@Override
	public log mapRow(ResultSet rs, int rowNum) throws SQLException {
		log logs = new log();
		logs.setLog_refer(rs.getString("log_refer"));	
		logs.setLog_note(rs.getString("log_note"));	
		logs.setLog_step(rs.getString("log_step"));	
		logs.setFile_name_original(rs.getString("file_name_original"));	
		logs.setFile_path_full(rs.getString("file_path_full"));	
		logs.setLog_date(rs.getString("log_date"));	
		logs.setLog_time(rs.getString("log_time"));	
		logs.setUser_name(rs.getString("user_name"));	
		logs.setGroup_level(rs.getString("group_level"));	
		return logs;
	}

}
