package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.user;

public class usermappers implements RowMapper<user> {

	@Override
	public user mapRow(ResultSet rs, int rowNum) throws SQLException {
		user users = new user();
		users.setUser_id(rs.getInt("user_id"));
		users.setUsername(rs.getString("username"));
		users.setUser_name(rs.getString("user_name"));
		users.setAuth(rs.getString("auth"));
		users.setUser_img(rs.getString("user_img"));
		return users;
	}

}
