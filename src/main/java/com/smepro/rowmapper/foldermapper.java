package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.folder;


public class foldermapper implements RowMapper<folder> {

	@Override
	public folder mapRow(ResultSet rs, int rowNum) throws SQLException {
		folder folders = new folder();
		folders.setFolder_id(rs.getInt("folder_id"));
		folders.setFolder_name(rs.getString("folder_name"));
		folders.setFolder_type(rs.getString("folder_type"));
		folders.setFolder_idrefer(rs.getInt("folder_idrefer"));
		folders.setFolder_use(rs.getLong("folder_use"));
		folders.setFolder_use2(rs.getString("folder_use2"));
		folders.setFolder_type2(rs.getInt("folder_type2"));
		return folders;
	}

}
