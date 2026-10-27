package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.edata;
import com.smepro.models.levenshtein;
import com.smepro.rowmapper.dataset_datamapper;
import com.smepro.rowmapper.datasetmapper;
import com.smepro.rowmapper.levenshteinmapper;

public class dataset_dataDAOlmpl implements dataset_dataDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	
	public dataset_dataDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public void save(dataset_data dataset_datas) {
	
		if(dataset_datas.getDataset_data_id() > 0) {
			Object[] commentInfo = { 
					dataset_datas.getDataset_data_title(),
					dataset_datas.getDataset_data_subtitle1(),
					dataset_datas.getDataset_data_subtitle2(),
					dataset_datas.getDataset_data_subtitle3(),
					dataset_datas.getDataset_data_subtitle4(),
					dataset_datas.getDataset_data_id(),
					};
					String sql = " UPDATE dataset_data set dataset_data_title =?,dataset_data_subtitle1=?,dataset_data_subtitle2=?,dataset_data_subtitle3=?,dataset_data_subtitle4=?   "
							+ " where dataset_data_id =? ";
					 jdbcTemplate.update(sql,commentInfo);
		}else {
			
			Object[] datasetInfo = { 
					dataset_datas.getDataset_data_id_refer(),
					dataset_datas.getDataset_data_title(),
					dataset_datas.getDataset_data_subtitle1(),
					dataset_datas.getDataset_data_subtitle2(),
					dataset_datas.getDataset_data_subtitle3(),
					dataset_datas.getDataset_data_subtitle4(),
					};
			
					String sql = "INSERT INTO dataset_data(dataset_data_id_refer,dataset_data_title,dataset_data_subtitle1,dataset_data_subtitle2,dataset_data_subtitle3,dataset_data_subtitle4) "
							+ "VALUES(?,?,?,?,?,?)";
					 jdbcTemplate.update(sql,datasetInfo);
		}
		
		

	}

	@Override
	public int update(dataset_data dataset_datas) {
		Object[] commentInfo = { 
				dataset_datas.getDataset_data_title(),
				dataset_datas.getDataset_data_subtitle1(),
				dataset_datas.getDataset_data_subtitle2(),
				dataset_datas.getDataset_data_subtitle3(),
				dataset_datas.getDataset_data_subtitle4(),
				dataset_datas.getDataset_data_id(),
				};
				String sql = " UPDATE dataset_data set dataset_data_title =?,dataset_data_subtitle1=?,dataset_data_subtitle2=?,dataset_data_subtitle3=?,dataset_data_subtitle4=?   "
						+ " where dataset_data_id =? ";
				return jdbcTemplate.update(sql,commentInfo);
	}

	
	
	@Override
	public int delete(int id) {
		 String sql="delete from dataset_data where  dataset_data_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public List<dataset_data> getall(int idrefer) {
		String sql = "SELECT * FROM dataset_data where  dataset_data_id_refer =? order by dataset_data_id desc limit 1000";
		List<dataset_data> dataset_datas  =  jdbcTemplate.query(sql,new Object[]{idrefer},new dataset_datamapper());
		return dataset_datas;
	}
	
	@Override
	public List<dataset_data> getallSearch(int idrefer, String search) {
	    // 1. ใช้ StringBuilder เพื่อให้ต่อ String SQL ได้แบบไดนามิก
	    StringBuilder sql = new StringBuilder("SELECT * FROM dataset_data WHERE dataset_data_id_refer = ? ");
	    
	    // 2. สร้าง List มาเก็บ Parameter ที่จะส่งให้ jdbcTemplate
	    List<Object> params = new ArrayList<>();
	    params.add(idrefer); // ตัวแรกคือ idrefer เสมอ

	    // 3. ตรวจสอบว่ามีการพิมพ์คำค้นหามาหรือไม่
	    if (search != null && !search.trim().isEmpty()) {
	        // แยกคำค้นหาด้วยช่องว่าง (รองรับการเคาะ Spacebar หลายที)
	        String[] keywords = search.trim().split("\\s+");
	        
	        for (String keyword : keywords) {
	            sql.append("AND dataset_data_title LIKE ? ");
	            params.add("%" + keyword + "%"); // ใส่ % หน้าหลังคำค้นหา
	        }
	    }
	    
	    // 4. ปิดท้ายด้วยการเรียงลำดับและจำกัดจำนวน
	    sql.append("ORDER BY dataset_data_id DESC LIMIT 1000");

	    // 5. โยนเข้า jdbcTemplate.query
	    return jdbcTemplate.query(
	        sql.toString(), 
	        params.toArray(), 
	        new dataset_datamapper()
	    );
	}

	@Override
	public List<dataset_data> getall(String idrefer,String filter) {
		
		    System.out.println(" idrefer "+idrefer);
		    System.out.println(" filter" +filter);
		    
		    
			if(filter == null) {
					String sql = "SELECT * FROM dataset_data where  dataset_data_id_refer =? order by dataset_data_id desc limit 500";
					List<dataset_data> dataset_datas  =  jdbcTemplate.query(sql,new Object[]{idrefer},new dataset_datamapper());
					return dataset_datas;
					
				
					}else {
					String sql = "SELECT * FROM dataset_data where "
							+ " dataset_data_id_refer =? AND FIND_IN_SET(dataset_data_title,?) "
							+ "order by dataset_data_id desc limit 500";
					List<dataset_data> dataset_datas  =  jdbcTemplate.query(sql,new Object[]{idrefer,filter},new dataset_datamapper());
					return dataset_datas;
				}
					/**/
					
					
		}		
	
		
		
		
		
		

	
	

	@Override
	public dataset_data getbyid(int id) {
		String sql = "SELECT * FROM dataset_data where dataset_data_id=? ";
		dataset_data dataset_datas  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new dataset_datamapper());
		return dataset_datas;
	}


	@Override
	public   List<levenshtein> getAllLevenshteins(int id) {
		 String sql= "SELECT * FROM levenshtein where edata_id=? ";
		List<levenshtein> levenshteins =  jdbcTemplate.query(sql,new Object[]{id},new levenshteinmapper());
		return levenshteins;
	}


	@Override
	public void SaveBatch(List<levenshtein> levenshteins, int id) {

         System.out.print("levenshteins id "+id);
		
		 String sqls="delete from levenshtein where  edata_id = "+id;    
		 jdbcTemplate.update(sqls);

		String sql = "INSERT INTO  levenshtein  (edata_id,distance,shtein_name1,shtein_name2) VALUES (?,?,?,?) ";
		
        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,levenshteins,100,
                new ParameterizedPreparedStatementSetter<levenshtein>() {
                    public void setValues(PreparedStatement ps, levenshtein argument) 
						throws SQLException {
                        ps.setInt(1, argument.getEdata_id());           // edata_id
                        ps.setInt(2, argument.getDistance());           // distance
                        ps.setString(3, argument.getShtein_name1());    // shtein_name1
                        ps.setString(4, argument.getShtein_name2()); 
                    }
                });


    }
	
  


}
