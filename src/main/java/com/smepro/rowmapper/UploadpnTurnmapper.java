package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.UploadpnTurn;
import com.smepro.models.upload_pn;

public class UploadpnTurnmapper implements RowMapper<UploadpnTurn> {

	@Override
	public UploadpnTurn mapRow(ResultSet rs, int rowNum) throws SQLException {
		UploadpnTurn upload_pns = new UploadpnTurn();
		upload_pns.setId(rs.getInt("id"));
		upload_pns.setId_refer(rs.getInt("id_refer"));
		
		upload_pns.setUser_name(rs.getString("user_name"));
		upload_pns.setTurn(rs.getInt("turn"));
		
		return upload_pns;
	}

}
