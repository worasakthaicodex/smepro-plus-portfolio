package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.cm;

public class cmmapper implements RowMapper<cm> {

	@Override
	public cm mapRow(ResultSet rs, int rowNum) throws SQLException {
		cm cms = new cm();
		cms.setCm_id(rs.getInt("cm_id"));
		cms.setCm_title(rs.getString("cm_title"));
		cms.setCm_date(rs.getString("cm_date"));
		cms.setCm_idadd(rs.getInt("cm_idadd"));
		cms.setCm_idrefer(rs.getInt("cm_idrefer"));
		cms.setCm_user_name(rs.getString("user_name"));
		cms.setType(rs.getString("type"));
		return cms;
	}

}
