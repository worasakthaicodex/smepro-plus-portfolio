package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.Outcome;
import com.smepro.models.Risk;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Riskmapper implements RowMapper<Risk> {

	@Override
	public Risk mapRow(ResultSet rs, int rowNum) throws SQLException {
		Risk data = new Risk();
		data.setId(rs.getInt("id"));
		data.setProject_id(rs.getInt("project_id"));
		data.setDescription(rs.getString("description"));
		data.setCategory(rs.getString("category"));
		data.setLikelihood(rs.getInt("likelihood"));
		data.setImpact(rs.getInt("impact"));
		data.setLevel(rs.getInt("level"));
		data.setMitigation(rs.getString("mitigation"));
		data.setOwner(rs.getString("owner"));
		data.setOwner_id(rs.getInt("owner_id"));
		data.setStatus(rs.getInt("status"));
		return data;
	}

}


