package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Taskview;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Taskviewmapper implements RowMapper<Taskview> {

	@Override
	public Taskview mapRow(ResultSet rs, int rowNum) throws SQLException {
		Taskview data = new Taskview();
		data.setId(rs.getInt("id"));
		data.setViews(rs.getInt("views"));
		data.setUser_id(rs.getInt("user_id"));
		data.setTitle(rs.getString("title"));
		data.setProject_id(rs.getInt("project_id"));
		
		return data;
	}

}
