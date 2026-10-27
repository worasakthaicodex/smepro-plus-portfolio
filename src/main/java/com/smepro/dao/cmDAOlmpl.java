package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.backup;
import com.smepro.models.cm;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.cmmapper;


public class cmDAOlmpl implements cmDAO {

	private JdbcTemplate jdbcTemplate;
	
	public cmDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<cm> getall(int idrefer,String type) {
		String sql = "SELECT * from cm "
				+ " LEFT JOIN users  ON  users.user_id = cm.cm_idadd "
				+ "  where cm.type =? and cm_idrefer=? and  DATE(cm_date) < CURDATE()  order by cm.cm_id desc limit 10  ";
		List<cm> cms  =  jdbcTemplate.query(sql,new Object[]{type,idrefer},new cmmapper());
		return cms;
	}
	
	@Override
	public List<cm> getall(int idrefer,String type,String date) {
		String sql = "SELECT * from  cm  "
				+ " LEFT JOIN users  ON  users.user_id = cm.cm_idadd "
				+ " where cm.type =? and cm.cm_idrefer=? and cm.cm_date=?  order by cm.cm_id desc limit 10 ";
		List<cm> cms  =  jdbcTemplate.query(sql,new Object[]{type,idrefer,date},new cmmapper());
		return cms;
	}

	
	@Override
	public cm getbyid(int id) {
		String sql = "SELECT * from cm LEFT JOIN users  ON  users.user_id = cm.cm_idadd  where cm.cm_id=?  ";
		cm cms  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new cmmapper());
		return cms;
	}
	
	
	public int getnum(int idrefer) {
		String sql = "select count(*) from cm where cm_idrefer=?  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] { idrefer }, Integer.class);
	}
	
	@Override
	public int delete(int id,String type) {
		 String sql="delete from cm where type= ? and  cm_id = ? ";    
		    return jdbcTemplate.update(sql, new Object[] { type,id });
	}


	@Override
	public int save(cm cms) {
		Object[] cmupInfo = { 
				cms.getCm_title(),
				cms.getCm_date(),
				cms.getCm_idadd(),
				cms.getCm_idrefer(),
				cms.getType()
				};
				String sql = "INSERT INTO cm(cm_title,cm_date,cm_idadd,cm_idrefer,type) "
						+ "VALUES(?,?,?,?,?)";
				return jdbcTemplate.update(sql,cmupInfo);
	}




}
