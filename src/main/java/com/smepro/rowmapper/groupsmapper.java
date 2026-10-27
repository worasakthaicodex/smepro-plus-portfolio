package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.groups;

public class groupsmapper implements RowMapper<groups> {

	@Override
	public groups mapRow(ResultSet rs, int rowNum) throws SQLException {
		groups groupss = new groups();
		groupss.setId(rs.getInt("id"));
		groupss.setGroup_name(rs.getString("group_name"));
		groupss.setGroup_level(rs.getString("group_level"));
		groupss.setGroup_department(rs.getString("group_department"));
		groupss.setGroup_department_id(rs.getInt("group_department_id"));
		groupss.setType(rs.getInt("type"));
		return groupss;
	}

}
