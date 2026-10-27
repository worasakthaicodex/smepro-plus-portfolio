package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.setmail;

public class setmailmapper implements RowMapper<setmail> {

	@Override
	public setmail mapRow(ResultSet rs, int rowNum) throws SQLException {
		
	setmail setmails = new setmail();
	setmails.setId(rs.getInt("id"));
	setmails.setId_refer(rs.getInt("id_refer"));
	setmails.setUsername(rs.getString("username"));
	setmails.setPassword(rs.getString("password"));
	setmails.setHostname(rs.getString("hostname"));
	setmails.setStarttls_port(rs.getString("starttls_port"));
	setmails.setUrl(rs.getString("url"));
	setmails.setSupport(rs.getString("support"));
	setmails.setStarttls(rs.getBoolean("starttls"));
	setmails.setAuth(rs.getBoolean("auth"));
	setmails.setEmailtest(rs.getString("emailtest"));
	return setmails;
	}

}
