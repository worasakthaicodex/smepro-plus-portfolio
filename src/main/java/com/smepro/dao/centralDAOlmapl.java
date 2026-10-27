package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.backup;
import com.smepro.models.central;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.centralmapper;

public class centralDAOlmapl implements centralDAO {

	private JdbcTemplate jdbcTemplate;
	
	public centralDAOlmapl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public void save(central centrals) {
		Object[] centralInfo = { 
				centrals.getCentral_name1(),
				centrals.getCentral_name2(),
				centrals.getCentral_type1(),
				centrals.getCentral_type2(),
				centrals.getCentral_nameadd(),
				centrals.getCentral_dateadd(),
				centrals.getCentral_level(),
				centrals.getCentral_idrefer(),
				centrals.getCentral_total(),
				};
				String sql = "INSERT INTO central(central_name1,central_name2,central_type1,central_type2,central_nameadd,central_dateadd,central_level,central_idrefer,central_total) "
						+ "VALUES(?,?,?,?,?,?,?,?,?)";
				 jdbcTemplate.update(sql,centralInfo);

	}

	@Override
	public void update(central centrals) {
		Object[] centralInfo = { 
				centrals.getCentral_name1(),
				centrals.getCentral_name2(),
				centrals.getCentral_type1(),
				centrals.getCentral_type2(),
				centrals.getCentral_id(),
				};
		
		String sql = "UPDATE central set central_name1 =?,central_name2=?,central_type1=?,central_type2=?  where central_id =? ";
		   jdbcTemplate.update(sql,centralInfo);

	}

	@Override
	public central getbyid(int id) {
		String sql = "SELECT * FROM central  WHERE  central_id =?";
		central centrals  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new centralmapper());
		return centrals;
	}

	
	public int getsum(int id) {
		String sql = "select count(*) from  central where  central_idrefer =? ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {id  }, Integer.class);
	}
	
	@Override
	public List<central> getall(int level, int idrefer) {
		String sql = "SELECT * FROM central  WHERE  central_level =? and  central_idrefer =? ";
		List<central> centrals  =  jdbcTemplate.query(sql,new Object[]{level,idrefer},new centralmapper());
		return centrals;
	}
	
	@Override
	public List<central> getall(int level) {
		String sql = "SELECT * FROM central  WHERE  central_level =?  ";
		List<central> centrals  =  jdbcTemplate.query(sql,new Object[]{level},new centralmapper());
		return centrals;
	}
	
	@Override
	public int delete(int id) {
		 String sql="delete from central where  central_id = ? ";    
		    return jdbcTemplate.update(sql,new Object[]{id});
	}

	@Override
	public void setotal(int id,int total) {
	
		String sql = "UPDATE central set central_total=? where central_id =? ";
		   jdbcTemplate.update(sql,new Object[]{id,total});

	}

	
	

}
