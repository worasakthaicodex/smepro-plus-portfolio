package com.smepro.dao;

import java.util.List;

import com.smepro.models.backup;


public interface backupDAO {
	List<backup> getall();
	int delete(int id);
	int save(backup backups);
}
