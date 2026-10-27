package com.smepro.dao;

import java.util.List;
import java.util.Map;

public interface AiDao {

	List<Map<String, Object>> getPendingSalesData(int batchSize);
	void updateAiVector(Object object, String vectorJson);

}
