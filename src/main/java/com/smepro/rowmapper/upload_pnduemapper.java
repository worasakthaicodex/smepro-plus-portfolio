package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.upload_pn;

public class upload_pnduemapper implements RowMapper<upload_pn> {

	@Override
	public upload_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		upload_pn upload_pns = new upload_pn();
		upload_pns.setUpload_pn_send(rs.getInt("upload_pn_send"));
		upload_pns.setUpload_pn_unsend(rs.getInt("upload_pn_unsend"));
		
		upload_pns.setUpload_pn_ontime(rs.getInt("upload_pn_ontime"));
		upload_pns.setUpload_pn_late(rs.getInt("upload_pn_late"));
		
		return upload_pns;
	}

}
