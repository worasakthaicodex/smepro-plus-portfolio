package com.smepro.dao;

import java.util.List;


import com.smepro.models.dataset_data;
import com.smepro.models.levenshtein;

public interface dataset_dataDAO {

	void save(dataset_data dataset_datas);
	int update(dataset_data dataset_datas);
	int delete(int id);
	List<dataset_data> getall(int idrefer);
	
	List<dataset_data> getall(String idrefer,String key);
	
	
	dataset_data getbyid(int id);
	void SaveBatch(List<levenshtein> levenshteins, int id);
	List<levenshtein> getAllLevenshteins(int id);
	List<dataset_data> getallSearch(int idrefer, String search);
	
}
