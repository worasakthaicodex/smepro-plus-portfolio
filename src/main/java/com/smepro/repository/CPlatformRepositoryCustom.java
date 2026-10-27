package com.smepro.repository;

import java.util.List;

import com.smepro.models.edata;

public interface CPlatformRepositoryCustom<T> {
	
    List<T> findAllByEdataId(String edata_id);
    T findByEdataId(int edata_id);
    T findById(int id);
    List<T> findAllByTempId(String TempId);

	

}
