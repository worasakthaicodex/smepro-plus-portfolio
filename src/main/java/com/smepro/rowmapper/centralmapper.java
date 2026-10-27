package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.backup;
import com.smepro.models.central;

public class centralmapper implements RowMapper<central> {

	@Override
	public central mapRow(ResultSet rs, int rowNum) throws SQLException {
		central centrals = new central();
		centrals.setCentral_id(rs.getInt("central_id"));
		centrals.setCentral_level(rs.getInt("central_level"));
		centrals.setCentral_idrefer(rs.getInt("central_idrefer"));
		centrals.setCentral_total(rs.getInt("central_total"));
		centrals.setCentral_name1(rs.getString("central_name1"));
		centrals.setCentral_name2(rs.getString("central_name2"));
		centrals.setCentral_type1(rs.getString("central_type1"));
		centrals.setCentral_type2(rs.getString("central_type2"));
		centrals.setCentral_nameadd(rs.getString("central_nameadd"));
		centrals.setCentral_dateadd(rs.getString("central_dateadd"));
		return centrals;
	}

}
