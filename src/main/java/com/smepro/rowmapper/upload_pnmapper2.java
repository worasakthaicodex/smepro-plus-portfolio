package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.showupload_pn;

public class upload_pnmapper2 implements RowMapper<showupload_pn> {

	@Override
	public showupload_pn mapRow(ResultSet rs, int rowNum) throws SQLException {
		showupload_pn upload_pns = new showupload_pn();
		upload_pns.setUpload_pn_id(rs.getInt("upload_pn_id"));
		upload_pns.setUpload_pn_name(rs.getString("upload_pn_name"));
		upload_pns.setUpload_pn_name2(rs.getString("upload_pn_name2"));
		upload_pns.setUpload_pn_name3(rs.getString("upload_pn_name3"));
		upload_pns.setUpload_pn_name4(rs.getString("upload_pn_name4"));
		upload_pns.setUpload_pn_name5(rs.getString("upload_pn_name5"));
		upload_pns.setUpload_pn_note(rs.getString("upload_pn_note"));
		upload_pns.setUpload_pn_due(rs.getInt("upload_pn_due"));
		upload_pns.setUpload_pn_idadd(rs.getInt("upload_pn_idadd"));
		upload_pns.setUpload_pn_idrefer(rs.getInt("upload_pn_idrefer"));
		upload_pns.setUpload_pn_respon(rs.getString("upload_pn_respon"));
		upload_pns.setUpload_pn_app(rs.getString("upload_pn_app"));
		upload_pns.setUpload_pn_date(rs.getString("upload_pn_date"));
		upload_pns.setUpload_pn_end(rs.getString("upload_pn_end"));
		upload_pns.setFolder_name(rs.getString("folder_name"));
		upload_pns.setUpload_pn_status(rs.getInt("upload_pn_status"));
		upload_pns.setUpload_pn_department(rs.getString("upload_pn_department"));
		upload_pns.setUpload_pn_type(rs.getInt("upload_pn_type"));
		upload_pns.setUpload_pn_datedue(rs.getString("upload_pn_datedue"));
		upload_pns.setUpload_pn_namefile(rs.getInt("upload_pn_namefile"));
		upload_pns.setUpload_pn_state(rs.getInt("upload_pn_state"));
		upload_pns.setUpload_pn_monitor(rs.getLong("upload_pn_monitor"));
		upload_pns.setUpload_pn_setdue(rs.getString("upload_pn_setdue"));
		upload_pns.setUpload_pn_filecheck(rs.getInt("upload_pn_filecheck"));
		upload_pns.setUpload_pn_send1(rs.getInt("upload_pn_send1"));
		upload_pns.setUpload_pn_send2(rs.getInt("upload_pn_send2"));
		upload_pns.setUpload_pn_send3(rs.getInt("upload_pn_send3"));
		return upload_pns;
	}

}
