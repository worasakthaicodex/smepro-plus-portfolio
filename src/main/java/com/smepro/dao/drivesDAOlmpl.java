package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.drives;
import com.smepro.rowmapper.drivesmapper;

public class drivesDAOlmpl implements drivesDAO {

	private JdbcTemplate jdbcTemplate;
	
	public drivesDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<drives> getall() {
		String sql = "SELECT * FROM drives ";
		List<drives> drivess  =  jdbcTemplate.query(sql,new Object[]{},new drivesmapper());
		return drivess;
	}

	@Override
	public List<drives> getallbyjd(int jd) {
		String sql = "SELECT * FROM drives "
					+ "LEFT JOIN group_authorities ON concat('READ-',drives.drives_drive) = group_authorities.authority "
					+ "where group_authorities.group_id =? group by drives.drives_drive";
		List<drives> drivess  =  jdbcTemplate.query(sql,new Object[]{jd},new drivesmapper());
		return drivess;
	}

	
	
	
	@Override
	public void save(drives drivess,String type,String size) {		
		Object[] drivesInfo = { 
				drivess.getDrives_name(),
				drivess.getDrives_size(),
				type,
				size,
				0,
				0,
				0
				};
				String sql = "INSERT INTO drives(drives_name,drives_size,drives_drive,drives_size2,drives_use,drives_use2,drives_usedisplay) "
						+ "VALUES(?,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,drivesInfo);
	}
	
	@Override
	public drives getbyId(int id) {
	    String sql="select * from drives where drives_id=?  ";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{id},new drivesmapper()); 
	}

	@Override
	public int update(drives drivess) {
		  String sql=  "UPDATE drives SET drives_name='"+drivess.getDrives_name()+"',drives_size='"+drivess.getDrives_size()+"',drives_size2='"+drivess.getDrives_size2()+"' WHERE  drives_id="+drivess.getDrives_id();
			    return jdbcTemplate.update(sql);   
	}

	@Override
	public int setuse(int id,long imageFileSize3,String Size4,double percent){
		  String sql=  "UPDATE drives SET drives_use='"+imageFileSize3+"',drives_use2='"+Size4+"',drives_usedisplay='"+percent+"' WHERE  drives_id="+id;
			    return jdbcTemplate.update(sql);   
	}
	@Override
	public int setuse(int id,long imageFileSize1,String Size1 ,long imageFileSize2,String Size2,double percent){
		  String sql=  "UPDATE drives SET "
		  		+ "drives_use='"+imageFileSize1+"',drives_use2='"+Size1+"',"
		  		+ "drives_remain='"+imageFileSize2+"',drives_remain2='"+Size2+"'"
		  		+ ",drives_usedisplay='"+percent+"' WHERE  drives_id="+id;
			    return jdbcTemplate.update(sql);   
	}
	
	@Override
	public int delete(int id) {
		 String sql="delete from drives where drives_id="+id+"";    
		    return jdbcTemplate.update(sql);
	}
	
	@Override
	public drives getsumuse() {
		  String sql="select SUM(drives_size) as drives_size,SUM(drives_use) as drives_use,drives_id,drives_name,drives_drive"
		  		+ ",drives_size2,drives_use,drives_use2,drives_usedisplay from drives";     
		    return jdbcTemplate.queryForObject(sql, new Object[]{},new drivesmapper()); 
	
	
	}
	
}
