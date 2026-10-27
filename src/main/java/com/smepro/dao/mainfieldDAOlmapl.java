package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.backup;
import com.smepro.models.central;
import com.smepro.models.mainfield;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.centralmapper;
import com.smepro.rowmapper.mainfieldmapper;

public class mainfieldDAOlmapl implements mainfieldDAO {

	private JdbcTemplate jdbcTemplate;
	
	public mainfieldDAOlmapl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public void save(mainfield mainfields) {
		Object[] mainfieldInfo = { 
				mainfields.getField_inputtype(),
				mainfields.getField_dataset(),
				mainfields.getField_inputname(),
				mainfields.getField_name1(),
				mainfields.getField_name2(),
				mainfields.getField_idrefer(),
				mainfields.getField_idadd(),
				mainfields.getField_dateadd(),
				mainfields.getField_type(),
				};
				String sql = "INSERT INTO mainfield(field_inputtype,field_dataset,field_inputname"
						+ ",field_name1,field_name2,field_idrefer,field_idadd,field_dateadd,field_type) "
						+ "VALUES(?,?,?,?,?,?,?,?,?)";
				 jdbcTemplate.update(sql,mainfieldInfo);

	}

	@Override
	public void update(mainfield mainfields) {
		Object[] centralInfo = { 
				mainfields.getField_inputtype(),
				mainfields.getField_dataset(),
				mainfields.getField_inputname(),
				mainfields.getField_name1(),
				mainfields.getField_name2(),
				mainfields.getField_idrefer(),
				mainfields.getField_type(),
				};
		
		String sql = "INSERT INTO mainfield(field_inputtype =?,field_dataset =?,field_inputname=?"
				+ ",field_name1=?,field_name2=?,field_idrefer=?,field_type=?) "
				+ "VALUES(?,?,?,?,?,?,?)";
		   jdbcTemplate.update(sql,centralInfo);

	}

	@Override
	public mainfield getbyid(int id) {
		String sql = "SELECT * FROM mainfield  WHERE  field_id =?";
		mainfield mainfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new mainfieldmapper());
		return mainfields;
	}

	
	public int getsum(int id) {
		String sql = "select count(*) from  mainfield where  field_idrefer =? ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {id  }, Integer.class);
	}
	
	@Override
	public List<mainfield> getall(int idrefer,String type,String inputtype) {
		String sql = "SELECT * FROM mainfield  WHERE  field_idrefer =? and  field_type =?  and  field_inputtype =? ";
		List<mainfield> mainfields  =  jdbcTemplate.query(sql,new Object[]{idrefer,type,inputtype},new mainfieldmapper());
		return mainfields;
	}
	@Override
	public List<mainfield> getall(int idrefer) {
		String sql = "SELECT * FROM mainfield  WHERE  field_idrefer =?  ";
		List<mainfield> mainfields  =  jdbcTemplate.query(sql,new Object[]{idrefer},new mainfieldmapper());
		return mainfields;
	}
	
	@Override
	public int delete(int id) {
		 String sql="delete from mainfield where  field_id = ? ";    
		    return jdbcTemplate.update(sql,new Object[]{id});
	}


	
	public int getsum(long idrefer,String type) {
		String sql = "select count(*) from  mainfield where field_idrefer =? and  field_type =? ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {idrefer,type  }, Integer.class);
	}
	
	

}
