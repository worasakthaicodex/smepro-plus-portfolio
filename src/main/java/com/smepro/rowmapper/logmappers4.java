package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.log;

public class logmappers4 implements RowMapper<log> {

	@Override
	public log mapRow(ResultSet rs, int rowNum) throws SQLException {
		log logs = new log();
		logs.setLog_note(rs.getString("log_note"));	
		logs.setLog_note(rs.getString("log_note"));	
		logs.setLog_datestart(rs.getString("log_datestart"));	
		logs.setLog_dateend(rs.getString("log_dateend"));	
		logs.setLog_timestart(rs.getString("log_timestart"));	
		logs.setLog_timeend(rs.getString("log_timeend"));	
		logs.setLog_step(rs.getString("log_step"));	
		logs.setLog_id_add(rs.getInt("log_id_add"));	
		return logs;
	}

}
