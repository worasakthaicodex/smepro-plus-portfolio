package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.log;

public class logmappers implements RowMapper<log> {

	@Override
	public log mapRow(ResultSet rs, int rowNum) throws SQLException {
		log logs = new log();
		logs.setLog_refer(rs.getString("log_refer"));	
		logs.setLog_note(rs.getString("log_note"));	
		logs.setFile_name_original(rs.getString("file_name_original"));	
		logs.setFile_path_full(rs.getString("file_path_full"));	
		return logs;
	}

}
