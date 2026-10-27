package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.smepro.models.backup;

public class backupmapper implements RowMapper<backup> {

	@Override
	public backup mapRow(ResultSet rs, int rowNum) throws SQLException {
		backup backups = new backup();
		backups.setBackup_id(rs.getInt("backup_id"));
		backups.setBackup_date(rs.getString("backup_date"));
		backups.setBackup_service(rs.getString("backup_service"));
		backups.setBackup_note(rs.getString("backup_note"));
		backups.setBackup_name(rs.getString("backup_name"));
		return backups;
	}

}
