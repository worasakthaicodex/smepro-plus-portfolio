package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.folder;


public class foldermapper2 implements RowMapper<folder> {

	@Override
	public folder mapRow(ResultSet rs, int rowNum) throws SQLException {
		folder folders = new folder();
		folders.setFolder_idrefer(rs.getInt("folder_idrefer"));
		folders.setFolder_use(rs.getLong("folder_use"));
		return folders;
	}

}
