package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.file;

public class filemapper implements RowMapper<file> {

	@Override
	public file mapRow(ResultSet rs, int rowNum) throws SQLException {
		file files = new file();
		files.setFile_id(rs.getInt("file_id"));
		files.setFile_name(rs.getString("file_name"));
		files.setFile_name_original(rs.getString("file_name_original"));
		files.setFile_type(rs.getString("file_type"));
		files.setFile_path(rs.getString("file_path"));
		files.setFile_type2(rs.getInt("file_type2"));
		files.setFile_size(rs.getInt("file_size"));
		files.setFile_id_refer(rs.getInt("file_id_refer"));
		files.setFile_folder(rs.getInt("file_folder"));
		files.setFile_size2(rs.getString("file_size2"));
		files.setFile_no(rs.getString("file_no"));
		files.setFile_rev(rs.getString("file_rev"));
		files.setFile_dateadd(rs.getString("file_dateadd"));
		files.setFile_dateend(rs.getString("file_dateend"));
		files.setFile_code(rs.getString("file_code"));
		files.setFile_shotcut(rs.getString("file_shotcut"));
		files.setFile_path_full(rs.getString("file_path_full"));
		files.setFile_idpn(rs.getString("file_idpn"));
		files.setFile_by(rs.getString("file_by"));
		files.setFile_jd(rs.getString("file_jd"));
		files.setFile_parent(rs.getInt("file_parent"));
		files.setEform(rs.getString("eform"));
		
		files.setFile_edata(rs.getInt("file_edata"));
		return files;
	}

}
