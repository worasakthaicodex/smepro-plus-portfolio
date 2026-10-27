package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Problem;
import com.smepro.models.backup;
import com.smepro.models.task;

public class problemmapper implements RowMapper<Problem> {

	@Override
	public Problem mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Problem data = new Problem();
		
		data.setId(rs.getInt("id"));
		data.setProject_id(rs.getInt("project_id"));
		data.setTask_id(rs.getInt("task_id"));
		data.setActionplan_id(rs.getInt("actionplan_id"));
		data.setTitle(rs.getString("title"));
		data.setDescription(rs.getString("description"));
		data.setMitigation(rs.getString("mitigation"));
		data.setOwner(rs.getString("owner"));
		data.setOwner_id(rs.getInt("owner_id"));
		data.setStatus(rs.getInt("status"));
		data.setType(rs.getInt("type"));
		
		
		return data;
	}

}



