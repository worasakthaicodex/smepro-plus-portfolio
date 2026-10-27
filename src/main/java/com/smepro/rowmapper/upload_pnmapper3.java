package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.showupload_pn;

public class upload_pnmapper3 implements RowMapper<showupload_pn> {

	@Override
	public showupload_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		showupload_pn upload_pns = new showupload_pn();
		upload_pns.setUpload_pn_respon(rs.getString("upload_pn_respon"));
		upload_pns.setUpload_pn_app(rs.getString("upload_pn_app"));
		upload_pns.setUpload_pn_status(rs.getInt("upload_pn_status"));
		upload_pns.setUpload_pn_note(rs.getString("upload_pn_note"));
		return upload_pns;
	}

}
