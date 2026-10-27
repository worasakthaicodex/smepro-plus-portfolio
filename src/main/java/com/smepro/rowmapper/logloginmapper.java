package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.Log_login;

public class logloginmapper implements RowMapper<Log_login> {

	@Override
	public Log_login mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Log_login logs = new Log_login();
		logs.setTotal1(rs.getInt("total1"));
		logs.setTotal2(rs.getInt("total2"));
		logs.setTotal3(rs.getInt("total3"));
		logs.setTotal4(rs.getInt("total4"));
		logs.setTotal5(rs.getInt("total5"));
		logs.setTotal(rs.getInt("total"));
		
		return logs;
	}

}
