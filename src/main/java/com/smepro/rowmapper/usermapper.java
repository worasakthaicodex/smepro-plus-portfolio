package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.user;

public class usermapper implements RowMapper<user> {

	@Override
	public user mapRow(ResultSet rs, int rowNum) throws SQLException {
		user users = new user();
		users.setUser_id(rs.getInt("user_id"));
		users.setUsername(rs.getString("username"));
		users.setPassword(rs.getString("password"));	
		users.setEnabled(rs.getInt("enabled"));
		users.setUser_name(rs.getString("user_name"));
		users.setUser_address(rs.getString("user_address"));
		users.setUser_tel(rs.getString("user_tel"));
		users.setUser_email(rs.getString("user_email"));
		users.setUser_line(rs.getString("user_line"));
		users.setUser_zip(rs.getString("user_zip"));
		users.setUser_province(rs.getString("user_province"));
		users.setUser_district(rs.getString("user_district"));
		users.setUser_road(rs.getString("user_road"));
		users.setUser_alley(rs.getString("user_alley"));
		users.setUser_village(rs.getString("user_village"));
		users.setUser_housenumber(rs.getString("user_housenumber"));
		users.setUser_building(rs.getString("user_building"));
		users.setUser_mobile(rs.getString("user_mobile"));
		users.setUser_passport(rs.getString("user_passport"));
		users.setUser_datepassport(rs.getString("user_datepassport"));
		users.setUser_countrypassport(rs.getString("user_countrypassport"));
		users.setUser_districtpassport(rs.getString("user_districtpassport"));
		users.setUser_idcard(rs.getString("user_idcard"));
		users.setUser_birthday(rs.getString("user_birthday"));
		users.setAuth(rs.getString("auth"));
		users.setUser_img(rs.getString("user_img"));
		return users;
	}

}
