package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.department;



public class departmentmapper implements RowMapper<department> {

	@Override
	public department mapRow(ResultSet rs, int rowNum) throws SQLException {
		department departments = new department();
		departments.setDepartment_id(rs.getInt("department_id"));
		departments.setDepartment_name(rs.getString("department_name"));
		return departments;
	}

}
