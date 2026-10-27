package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.MasterBudget;
import com.smepro.models.Outcome;
import com.smepro.models.Risk;
import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class Budgetmapper implements RowMapper<MasterBudget> {

	@Override
	public MasterBudget mapRow(ResultSet rs, int rowNum) throws SQLException {
		MasterBudget data = new MasterBudget();
		data.setId(rs.getInt("id"));
		data.setProject_id(rs.getInt("project_id"));
		data.setDetail(rs.getString("detail"));
		data.setCategory(rs.getString("category"));
		data.setBudget(rs.getDouble("budget"));
		data.setActual(rs.getDouble("actual"));
		data.setVariance(rs.getDouble("variance"));

		return data;
	}

}


