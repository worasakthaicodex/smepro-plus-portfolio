package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.BuildIn;
import com.smepro.models.condition;
import com.smepro.models.eformfield;


public class BuildInmapper implements RowMapper<BuildIn> {

	@Override
	public BuildIn mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuildIn data = new BuildIn();
		data.setId(rs.getInt("id"));
		data.setId_refer(rs.getInt("id_refer"));
		data.setProcess_id(rs.getInt("process_id"));
		data.setBuildin_title(rs.getString("buildin_title"));
		data.setBuildin_note(rs.getString("buildin_note"));
		
		data.setSource_type(rs.getInt("source_type"));
		data.setCurrent_step(rs.getString("current_step"));
		data.setClosing_status_stop(rs.getString("closing_status_stop"));
		
		data.setSource_param(rs.getString("source_param"));
		data.setTocolumn(rs.getInt("tocolumn"));
		data.setMeasurement_rules_id(rs.getInt("measurement_rules_id"));
		
		return data;
	}

}





