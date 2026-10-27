package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.dataeform;
import com.smepro.models.dataset;
import com.smepro.rowmapper.dataeformmapper;
import com.smepro.rowmapper.dataeformmapper2;
import com.smepro.rowmapper.datasetmapper;

public class dataeformDAOlmpl implements dataeformDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	
	public dataeformDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public void save(dataeform dataeforms) {
		Object[] dataeformInfo = { 
				dataeforms.getDataeform_idadd(),
				dataeforms.getDataeform_idapp(),
				dataeforms.getDataeform_idrefer(),
				dataeforms.getDataeform_condition_id(),
				dataeforms.getDataeform_type(),
				dataeforms.getDataeform_date(),
				
				dataeforms.getCondition_support4(),
				dataeforms.getCondition_support5_1(),
				dataeforms.getCondition_support5_2(),
				dataeforms.getCondition_1_note1(),
				dataeforms.getCondition_1_note2(),
				dataeforms.getCondition_1_note3(),
				dataeforms.getCondition_1_total(),

				dataeforms.getCondition_2_note1(),
				dataeforms.getCondition_2_note2(),
				dataeforms.getCondition_2_note3(),
				dataeforms.getCondition_2_total(),
				
				dataeforms.getCondition_3_note1(),
				dataeforms.getCondition_3_note2(),
				dataeforms.getCondition_3_note3(),
				dataeforms.getCondition_3_total(),
				
				dataeforms.getCondition_4_note1(),
				dataeforms.getCondition_4_note2(),
				dataeforms.getCondition_4_note3(),
				dataeforms.getCondition_4_total(),
				
				dataeforms.getCondition_5_note1(),
				dataeforms.getCondition_5_note2(),
				dataeforms.getCondition_5_note3(),
				dataeforms.getCondition_5_total(),
				
				
				dataeforms.getCondition_6_note1(),
				dataeforms.getCondition_6_note2(),
				dataeforms.getCondition_6_note3(),
				dataeforms.getCondition_6_total(),
				
				dataeforms.getCondition_7_note1(),
				dataeforms.getCondition_7_note2(),
				dataeforms.getCondition_7_note3(),
				dataeforms.getCondition_7_total(),
				
				dataeforms.getCondition_8_note1(),
				dataeforms.getCondition_8_note2(),
				dataeforms.getCondition_8_note3(),
				dataeforms.getCondition_8_total(),
				
				dataeforms.getCondition_9_note1(),
				dataeforms.getCondition_9_note2(),
				dataeforms.getCondition_9_note3(),
				dataeforms.getCondition_9_total(),
				
				dataeforms.getCondition_10_note1(),
				dataeforms.getCondition_10_note2(),
				dataeforms.getCondition_10_note3(),
				dataeforms.getCondition_10_total(),
				
				dataeforms.getCondition_11_note1(),
				dataeforms.getCondition_11_note2(),
				dataeforms.getCondition_11_note3(),
				dataeforms.getCondition_11_total(),
				
				dataeforms.getCondition_12_note1(),
				dataeforms.getCondition_12_note2(),
				dataeforms.getCondition_12_note3(),
				dataeforms.getCondition_12_total(),
				
				dataeforms.getCondition_13_note1(),
				dataeforms.getCondition_13_note2(),
				dataeforms.getCondition_13_note3(),
				dataeforms.getCondition_13_total(),
				
				dataeforms.getCondition_14_note1(),
				dataeforms.getCondition_14_note2(),
				dataeforms.getCondition_14_note3(),
				dataeforms.getCondition_14_total(),
				
				dataeforms.getCondition_15_note1(),
				dataeforms.getCondition_15_note2(),
				dataeforms.getCondition_15_note3(),
				dataeforms.getCondition_15_total(),
				
	
				};
		
		String selects = "dataeform_idadd,dataeform_idapp,dataeform_idrefer,dataeform_condition_id,dataeform_type,dataeform_date,"
				+ "condition_support4,condition_support5_1,condition_support5_2,condition_1_note1,condition_1_note2,condition_1_note3,condition_1_total,"
				+ "condition_2_note1,condition_2_note2,condition_2_note3,condition_2_total,"
				+ "condition_3_note1,condition_3_note2,condition_3_note3,condition_3_total,"
				+ "condition_4_note1,condition_4_note2,condition_4_note3,condition_4_total,"
				+ "condition_5_note1,condition_5_note2,condition_5_note3,condition_5_total,"
				+ "condition_6_note1,condition_6_note2,condition_6_note3,condition_6_total,"
				+ "condition_7_note1,condition_7_note2,condition_7_note3,condition_7_total,"
				+ "condition_8_note1,condition_8_note2,condition_8_note3,condition_8_total,"
				+ "condition_9_note1,condition_9_note2,condition_9_note3,condition_9_total,"
				+ "condition_10_note1,condition_10_note2,condition_10_note3,condition_10_total,"
				+ "condition_11_note1,condition_11_note2,condition_11_note3,condition_11_total,"
				+ "condition_12_note1,condition_12_note2,condition_12_note3,condition_12_total,"
				+ "condition_13_note1,condition_13_note2,condition_13_note3,condition_13_total,"
				+ "condition_14_note1,condition_14_note2,condition_14_note3,condition_14_total,"
				+ "condition_15_note1,condition_15_note2,condition_15_note3,condition_15_total";
		
		
		
				String sql = "INSERT INTO dataeform("+selects+") "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				 jdbcTemplate.update(sql,dataeformInfo);

	}

	@Override
	public int update(dataeform dataeforms) {
		Object[] dataeformInfo = {
				dataeforms.getDataeform_id(),
				dataeforms.getDataeform_idadd(),
				dataeforms.getDataeform_idapp(),
				dataeforms.getDataeform_idrefer(),
				dataeforms.getDataeform_condition_id(),
				dataeforms.getDataeform_type(),
				dataeforms.getDataeform_date(),
				
				dataeforms.getCondition_support4(),
				dataeforms.getCondition_support5_1(),
				dataeforms.getCondition_support5_2(),
				dataeforms.getCondition_1_note1(),
				dataeforms.getCondition_1_note2(),
				dataeforms.getCondition_1_note3(),
				dataeforms.getCondition_1_total(),

				dataeforms.getCondition_2_note1(),
				dataeforms.getCondition_2_note2(),
				dataeforms.getCondition_2_note3(),
				dataeforms.getCondition_2_total(),
				
				dataeforms.getCondition_3_note1(),
				dataeforms.getCondition_3_note2(),
				dataeforms.getCondition_3_note3(),
				dataeforms.getCondition_3_total(),
				
				dataeforms.getCondition_4_note1(),
				dataeforms.getCondition_4_note2(),
				dataeforms.getCondition_4_note3(),
				dataeforms.getCondition_4_total(),
				
				dataeforms.getCondition_5_note1(),
				dataeforms.getCondition_5_note2(),
				dataeforms.getCondition_5_note3(),
				dataeforms.getCondition_5_total(),
				
				
				dataeforms.getCondition_6_note1(),
				dataeforms.getCondition_6_note2(),
				dataeforms.getCondition_6_note3(),
				dataeforms.getCondition_6_total(),
				
				dataeforms.getCondition_7_note1(),
				dataeforms.getCondition_7_note2(),
				dataeforms.getCondition_7_note3(),
				dataeforms.getCondition_7_total(),
				
				dataeforms.getCondition_8_note1(),
				dataeforms.getCondition_8_note2(),
				dataeforms.getCondition_8_note3(),
				dataeforms.getCondition_8_total(),
				
				dataeforms.getCondition_9_note1(),
				dataeforms.getCondition_9_note2(),
				dataeforms.getCondition_9_note3(),
				dataeforms.getCondition_9_total(),
				
				dataeforms.getCondition_10_note1(),
				dataeforms.getCondition_10_note2(),
				dataeforms.getCondition_10_note3(),
				dataeforms.getCondition_10_total(),
				
				dataeforms.getCondition_11_note1(),
				dataeforms.getCondition_11_note2(),
				dataeforms.getCondition_11_note3(),
				dataeforms.getCondition_11_total(),
				
				dataeforms.getCondition_12_note1(),
				dataeforms.getCondition_12_note2(),
				dataeforms.getCondition_12_note3(),
				dataeforms.getCondition_12_total(),
				
				dataeforms.getCondition_13_note1(),
				dataeforms.getCondition_13_note2(),
				dataeforms.getCondition_13_note3(),
				dataeforms.getCondition_13_total(),
				
				dataeforms.getCondition_14_note1(),
				dataeforms.getCondition_14_note2(),
				dataeforms.getCondition_14_note3(),
				dataeforms.getCondition_14_total(),
				
				dataeforms.getCondition_15_note1(),
				dataeforms.getCondition_15_note2(),
				dataeforms.getCondition_15_note3(),
				dataeforms.getCondition_15_total(),
				};
		
		String sql = "UPDATE dataeform set dataeform_idadd=?,dataeform_idapp=?,dataeform_idrefer=?,dataeform_condition_id=?,dataeform_type=?,dataeform_date=?,"
				+ "condition_support4=?,condition_support5_1=?,condition_support5_2=?,condition_1_note1=?,condition_1_note2=?,condition_1_note3=?,condition_1_total=?,"
				+ "condition_2_note1=?,condition_2_note2=?,condition_2_note3=?,condition_2_total=?,"
				+ "condition_3_note1=?,condition_3_note2=?,condition_3_note3=?,condition_3_total=?,"
				+ "condition_4_note1=?,condition_4_note2=?,condition_4_note3=?,condition_4_total=?,"
				+ "condition_5_note1=?,condition_5_note2=?,condition_5_note3=?,condition_5_total=?,"
				+ "condition_6_note1=?,condition_6_note2=?,condition_6_note3=?,condition_6_total=?,"
				+ "condition_7_note1=?,condition_7_note2=?,condition_7_note3=?,condition_7_total=?,"
				+ "condition_8_note1=?,condition_8_note2=?,condition_8_note3=?,condition_8_total=?,"
				+ "condition_9_note1=?,condition_9_note2=?,condition_9_note3=?,condition_9_total=?,"
				+ "condition_10_note1=?,condition_10_note2=?,condition_10_note3=?,condition_10_total=?,"
				+ "condition_11_note1=?,condition_11_note2=?,condition_11_note3=?,condition_11_total=?,"
				+ "condition_12_note1=?,condition_12_note2=?,condition_12_note3=?,condition_12_total=?,"
				+ "condition_13_note1=?,condition_13_note2=?,condition_13_note3=?,condition_13_total=?,"
				+ "condition_14_note1=?,condition_14_note2=?,condition_14_note3=?,condition_14_total=?,"
				+ "condition_15_note1=?,condition_15_note2=?,condition_15_note3=?,condition_15_total=? WHERE dataeform_id = ? ";

				return jdbcTemplate.update(sql,dataeformInfo);
	}

	@Override
	public int delete(int id) {
		 String sql="delete from dataeform where  dataeform_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public List<dataeform> getall() {
		String sql = "SELECT * FROM dataeform  order by dataeform_id desc limit 300";
		List<dataeform> dataeform  =  jdbcTemplate.query(sql,new Object[]{},new dataeformmapper());
		return dataeform;
	}

	@Override
	public dataeform getbyid(int id) {
		String sql = "SELECT * FROM dataeform where dataeform_id=? ";
		dataeform dataeform  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new dataeformmapper());
		return dataeform;
		
	}
	
	@Override
	public dataeform getbycondition(int condition_id,int logid) {
		String sql = "SELECT * FROM dataeform where dataeform_condition_id =? and dataeform_idrefer=?  ";
		dataeform dataeform  =  jdbcTemplate.queryForObject(sql,new Object[]{condition_id,logid},new dataeformmapper());
		return dataeform;
		
	}
	

	@Override
	public dataeform getbydate(String datestart,String dateend,int id) {
		
		
		String selects = "dataeform_id,dataeform_idadd,dataeform_idapp,dataeform_idrefer,dataeform_condition_id,dataeform_type,dataeform_date,"
				+ "condition_support4,condition_support5_1,condition_support5_2,condition_1_note1,condition_1_note2,condition_1_note3,condition_1_total,"
				+ "SUM(condition_2_note1) as condition_2_note1,condition_2_note2,condition_2_note3,condition_2_total,"
				+ "condition_3_note1,condition_3_note2,condition_3_note3,condition_3_total,"
				+ "condition_4_note1,condition_4_note2,condition_4_note3,condition_4_total,"
				+ "condition_5_note1,condition_5_note2,condition_5_note3,condition_5_total,"
				+ "condition_6_note1,condition_6_note2,condition_6_note3,condition_6_total,"
				+ "condition_7_note1,condition_7_note2,condition_7_note3,condition_7_total,"
				+ "condition_8_note1,condition_8_note2,condition_8_note3,condition_8_total,"
				+ "condition_9_note1,condition_9_note2,condition_9_note3,condition_9_total,"
				+ "condition_10_note1,condition_10_note2,condition_10_note3,condition_10_total,"
				+ "condition_11_note1,condition_11_note2,condition_11_note3,condition_11_total,"
				+ "condition_12_note1,condition_12_note2,condition_12_note3,condition_12_total,"
				+ "condition_13_note1,condition_13_note2,condition_13_note3,condition_13_total,"
				+ "condition_14_note1,condition_14_note2,condition_14_note3,condition_14_total,"
				+ "condition_15_note1,condition_15_note2,condition_15_note3,condition_15_total"
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_1_total  END) AS month1_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_2_total  END) AS month1_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_3_total  END) AS month1_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_4_total  END) AS month1_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_5_total  END) AS month1_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_6_total  END) AS month1_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_7_total  END) AS month1_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_8_total  END) AS month1_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_9_total  END) AS month1_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_10_total  END) AS month1_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_11_total  END) AS month1_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_12_total  END) AS month1_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_13_total  END) AS month1_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_14_total  END) AS month1_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 1 THEN condition_15_total  END) AS month1_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_1_total  END) AS month2_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_2_total  END) AS month2_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_3_total  END) AS month2_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_4_total  END) AS month2_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_5_total  END) AS month2_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_6_total  END) AS month2_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_7_total  END) AS month2_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_8_total  END) AS month2_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_9_total  END) AS month2_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_10_total  END) AS month2_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_11_total  END) AS month2_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_12_total  END) AS month2_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_13_total  END) AS month2_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_14_total  END) AS month2_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 2 THEN condition_15_total  END) AS month2_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_1_total  END) AS month3_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_2_total  END) AS month3_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_3_total  END) AS month3_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_4_total  END) AS month3_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_5_total  END) AS month3_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_6_total  END) AS month3_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_7_total  END) AS month3_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_8_total  END) AS month3_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_9_total  END) AS month3_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_10_total  END) AS month3_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_11_total  END) AS month3_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_12_total  END) AS month3_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_13_total  END) AS month3_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_14_total  END) AS month3_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 3 THEN condition_15_total  END) AS month3_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_1_total  END) AS month4_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_2_total  END) AS month4_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_3_total  END) AS month4_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_4_total  END) AS month4_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_5_total  END) AS month4_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_6_total  END) AS month4_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_7_total  END) AS month4_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_8_total  END) AS month4_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_9_total  END) AS month4_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_10_total  END) AS month4_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_11_total  END) AS month4_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_12_total  END) AS month4_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_13_total  END) AS month4_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_14_total  END) AS month4_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 4 THEN condition_15_total  END) AS month4_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_1_total  END) AS month5_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_2_total  END) AS month5_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_3_total  END) AS month5_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_4_total  END) AS month5_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_5_total  END) AS month5_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_6_total  END) AS month5_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_7_total  END) AS month5_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_8_total  END) AS month5_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_9_total  END) AS month5_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_10_total  END) AS month5_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_11_total  END) AS month5_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_12_total  END) AS month5_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_13_total  END) AS month5_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_14_total  END) AS month5_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 5 THEN condition_15_total  END) AS month5_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_1_total  END) AS month6_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_2_total  END) AS month6_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_3_total  END) AS month6_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_4_total  END) AS month6_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_5_total  END) AS month6_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_6_total  END) AS month6_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_7_total  END) AS month6_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_8_total  END) AS month6_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_9_total  END) AS month6_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_10_total  END) AS month6_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_11_total  END) AS month6_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_12_total  END) AS month6_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_13_total  END) AS month6_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_14_total  END) AS month6_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 6 THEN condition_15_total  END) AS month6_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_1_total  END) AS month7_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_2_total  END) AS month7_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_3_total  END) AS month7_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_4_total  END) AS month7_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_5_total  END) AS month7_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_6_total  END) AS month7_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_7_total  END) AS month7_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_8_total  END) AS month7_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_9_total  END) AS month7_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_10_total  END) AS month7_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_11_total  END) AS month7_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_12_total  END) AS month7_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_13_total  END) AS month7_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_14_total  END) AS month7_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 7 THEN condition_15_total  END) AS month7_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_1_total  END) AS month8_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_2_total  END) AS month8_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_3_total  END) AS month8_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_4_total  END) AS month8_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_5_total  END) AS month8_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_6_total  END) AS month8_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_7_total  END) AS month8_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_8_total  END) AS month8_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_9_total  END) AS month8_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_10_total  END) AS month8_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_11_total  END) AS month8_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_12_total  END) AS month8_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_13_total  END) AS month8_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_14_total  END) AS month8_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 8 THEN condition_15_total  END) AS month8_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_1_total  END) AS month9_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_2_total  END) AS month9_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_3_total  END) AS month9_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_4_total  END) AS month9_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_5_total  END) AS month9_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_6_total  END) AS month9_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_7_total  END) AS month9_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_8_total  END) AS month9_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_9_total  END) AS month9_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_10_total  END) AS month9_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_11_total  END) AS month9_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_12_total  END) AS month9_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_13_total  END) AS month9_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_14_total  END) AS month9_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 9 THEN condition_15_total  END) AS month9_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_1_total  END) AS month10_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_2_total  END) AS month10_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_3_total  END) AS month10_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_4_total  END) AS month10_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_5_total  END) AS month10_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_6_total  END) AS month10_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_7_total  END) AS month10_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_8_total  END) AS month10_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_9_total  END) AS month10_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_10_total  END) AS month10_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_11_total  END) AS month10_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_12_total  END) AS month10_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_13_total  END) AS month10_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_14_total  END) AS month10_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 10 THEN condition_15_total  END) AS month10_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_1_total  END) AS month11_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_2_total  END) AS month11_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_3_total  END) AS month11_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_4_total  END) AS month11_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_5_total  END) AS month11_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_6_total  END) AS month11_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_7_total  END) AS month11_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_8_total  END) AS month11_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_9_total  END) AS month11_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_10_total  END) AS month11_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_11_total  END) AS month11_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_12_total  END) AS month11_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_13_total  END) AS month11_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_14_total  END) AS month11_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 11 THEN condition_15_total  END) AS month11_15 "
				
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_1_total  END) AS month12_1 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_2_total  END) AS month12_2 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_3_total  END) AS month12_3 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_4_total  END) AS month12_4 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_5_total  END) AS month12_5 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_6_total  END) AS month12_6 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_7_total  END) AS month12_7 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_8_total  END) AS month12_8 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_9_total  END) AS month12_9 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_10_total  END) AS month12_10 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_11_total  END) AS month12_11 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_12_total  END) AS month12_12 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_13_total  END) AS month12_13 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_14_total  END) AS month12_14 "
				+ ",SUM(CASE WHEN MONTH(dataeform_date) = 12 THEN condition_15_total  END) AS month12_15 ";
		
		String sql = "SELECT "+selects+" FROM dataeform where DATE(dataeform_date) >= ? and DATE(dataeform_date) <= ? and  dataeform_condition_id=? ";
		dataeform dataeform  =  jdbcTemplate.queryForObject(sql,new Object[]{datestart,dateend,id},new dataeformmapper2());
		return dataeform;
	}
	
	
	
}
