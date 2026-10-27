package com.smepro.dao;

import java.util.List;
import java.util.stream.IntStream;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.dataset;
import com.smepro.rowmapper.conditionmapper2;
import com.smepro.rowmapper.datasetmapper;

public class datasetDAOlmpl implements datasetDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	
	public datasetDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public void save(dataset datasets) {
		Object[] datasetInfo = { 
				datasets.getDataset_name(),
				datasets.getDataset_type(),
				datasets.getDataset_idadd(),
				datasets.getDataset_dateadd(),
				datasets.getDataset_1(),
				datasets.getDataset_2(),
				datasets.getDataset_3(),
				datasets.getDataset_4(),
				datasets.getDataset_5(),
				datasets.getSetgroup(),
				};
				String sql = "INSERT INTO dataset(dataset_name,dataset_type,dataset_idadd,dataset_dateadd,dataset_1,dataset_2,dataset_3,dataset_4,dataset_5,setgroup) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
				 jdbcTemplate.update(sql,datasetInfo);

	}

	
	
	
	
	
	@Override
	public int update(dataset datasets) {
		Object[] commentInfo = { 
				datasets.getDataset_name(),
				datasets.getDataset_type(),
				datasets.getDataset_1(),
				datasets.getDataset_2(),
				datasets.getDataset_3(),
				datasets.getDataset_4(),
				datasets.getDataset_5(),
				datasets.getDataset_id(),
				};
				String sql = " UPDATE dataset set dataset_name =? ,dataset_type=?,dataset_1=?,dataset_2=?,dataset_3=?,dataset_4=?,dataset_5=?    where dataset_id =? ";
				return jdbcTemplate.update(sql,commentInfo);
	}

	@Override
	public int delete(int id) {
		 String sql="delete from dataset where  dataset_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public List<dataset> getall() {
		String sql = "SELECT GROUP_CONCAT(DISTINCT dataset_data.dataset_data_title) as dataset_1,dataset.*,dataset_data.* FROM dataset "
				+ " LEFT JOIN dataset_data ON dataset.dataset_id = dataset_data.dataset_data_id_refer  "
				+ " group by dataset_data.dataset_data_id_refer  order by dataset.dataset_id desc limit 300";
		List<dataset> datasets  =  jdbcTemplate.query(sql,new Object[]{},new datasetmapper());
		return datasets;
	}

	
	@Override
	public List<dataset> getalls() {
		String sql = "SELECT  * from  dataset   order by dataset_id desc limit 300";
		List<dataset> datasets  =  jdbcTemplate.query(sql,new Object[]{},new datasetmapper());
		return datasets;
	}
	@Override
	public List<dataset> getalls(int level) {
		String sql = "SELECT  * from  dataset where setgroup = ?  order by dataset_id desc limit 300";
		List<dataset> datasets  =  jdbcTemplate.query(sql,new Object[]{level},new datasetmapper());
		return datasets;
	}
	
	@Override
	public List<dataset> getallmain() {
		String sql = "SELECT * FROM dataset where  dataset_type = '1' order by dataset_id desc limit 300";
		List<dataset> datasets  =  jdbcTemplate.query(sql,new Object[]{},new datasetmapper());
		return datasets;
	}
	@Override
	public List<dataset> getallNotmain() {
		String sql = "SELECT * FROM dataset where  dataset_type IS NULL order by dataset_id desc limit 300";
		List<dataset> datasets  =  jdbcTemplate.query(sql,new Object[]{},new datasetmapper());
		return datasets;
	}
	
	@Override
	public dataset getbyid(int id) {
	
		
		 try {
				String sql = "SELECT * FROM dataset where dataset_id=? ";
				dataset datasets  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new datasetmapper());
				return datasets;
				
			 
			 }catch(EmptyResultDataAccessException e) {
				 dataset datasets  = new dataset();
				 datasets.setDataset_id(0);
				 return datasets;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		
	}
	
	@Override
	public dataset getMain(String lable) {

		
	    try {
			String sql = "SELECT * FROM dataset where dataset_type = 'Main' AND  dataset_name=? limit 1 ";
			dataset datasets  =  jdbcTemplate.queryForObject(sql,new Object[]{lable},new datasetmapper());
			return datasets;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 dataset datasets  = new dataset();
			 datasets.setDataset_id(0);
			 return datasets;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
	}
	
	
	
	

	
	

}
