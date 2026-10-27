package com.smepro.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AiDaolmpl implements AiDao {


	private JdbcTemplate jdbcTemplate;

	public AiDaolmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	

    // 1. ดึงข้อมูลที่ยังไม่ได้แปลงเป็น Vector
    public List<Map<String, Object>> getPendingSalesData(int limit) {
        String sql = "SELECT * FROM sales_prediction_data WHERE ai_vector IS NULL LIMIT ?";
        return jdbcTemplate.queryForList(sql, limit);
    }

    // 2. บันทึกค่า Vector ที่แปลงเสร็จแล้วลง Database
    public void updateAiVector(Object id, String vectorJson) {
    	
        String sql = "UPDATE sales_prediction_data SET ai_vector = ?, updated_at = CURRENT_TIMESTAMP WHERE deal_id = ?";
        jdbcTemplate.update(sql, vectorJson, id);
    }
}
