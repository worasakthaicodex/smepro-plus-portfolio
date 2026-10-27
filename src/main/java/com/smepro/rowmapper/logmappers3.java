package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.log;

public class logmappers3 implements RowMapper<log> {

	@Override
	public log mapRow(ResultSet rs, int rowNum) throws SQLException {
		log logs = new log();
		logs.setLog_note(rs.getString("log_note"));	
		logs.setLog_time(rs.getString("log_time"));	
		logs.setLog_refer(rs.getString("log_refer"));	
		logs.setLog_id_add(rs.getInt("log_id_add"));	
		logs.setLog_datestart(rs.getString("log_datestart"));	
		logs.setLog_dateend(rs.getString("log_dateend"));	
		return logs;
	}

}
