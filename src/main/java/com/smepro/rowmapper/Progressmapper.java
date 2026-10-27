package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Problem;
import com.smepro.models.Progress;
import com.smepro.models.backup;
import com.smepro.models.task;

public class Progressmapper implements RowMapper<Progress> {

	@Override
	public Progress mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Progress data = new Progress();
		
		data.setId(rs.getInt("id"));
		data.setTitle(rs.getString("title"));
		data.setNote(rs.getString("note"));
		data.setDate_log(rs.getString("date_log"));
		data.setType(rs.getInt("type"));
		data.setId_refer(rs.getString("id_refer"));
		data.setTask_id(rs.getInt("task_id"));
		data.setPositon(rs.getInt("positon"));
		data.setId_add(rs.getInt("id_add"));
		data.setUser_add(rs.getString("user_add"));
		return data;
	}

}


