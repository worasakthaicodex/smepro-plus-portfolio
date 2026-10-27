package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.backup;
import com.smepro.rowmapper.backupmapper;


public class backupDAOlmpl implements backupDAO {

	private JdbcTemplate jdbcTemplate;
	
	public backupDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<backup> getall() {
		String sql = "SELECT * FROM backup  order by backup_id desc limit 300";
		List<backup> backups  =  jdbcTemplate.query(sql,new Object[]{},new backupmapper());
		return backups;
	}

	@Override
	public int delete(int id) {
		 String sql="delete from backup where  backup_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	
	
	@Override
	public int save(backup backups) {
		Object[] backupInfo = { 
				backups.getBackup_date(),
				backups.getBackup_service(),
				backups.getBackup_note(),
				backups.getBackup_name()
				};
				String sql = "INSERT INTO backup(backup_date,backup_service,backup_note,backup_name) "
						+ "VALUES(?,?,?,?)";
				return jdbcTemplate.update(sql,backupInfo);
	}

}
