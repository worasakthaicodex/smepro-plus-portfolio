package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.file;

public class filemapper2 implements RowMapper<file> {

	@Override
	public file mapRow(ResultSet rs, int rowNum) throws SQLException {
		file files = new file();
		files.setFile_path(rs.getString("file_path"));
		files.setFile_size(rs.getInt("file_size"));
		return files;
	}

}
