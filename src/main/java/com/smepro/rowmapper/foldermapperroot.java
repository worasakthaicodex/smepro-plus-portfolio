package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.folder;


public class foldermapperroot implements RowMapper<folder> {

	@Override
	public folder mapRow(ResultSet rs, int rowNum) throws SQLException {
		folder folders = new folder();
		folders.setFolder_id(rs.getInt("folder_id"));
		folders.setFolder_name(rs.getString("folder_name"));
		folders.setFolder_type(rs.getString("folder_type"));
		folders.setFolder_idrefer(rs.getInt("folder_idrefer"));
		folders.setFolder_doc(rs.getLong("folder_doc"));
		folders.setFolder_work(rs.getLong("folder_work"));
		return folders;
	}

}
