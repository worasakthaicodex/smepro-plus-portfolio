package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.comment;
import com.smepro.rowmapper.commentmapper;

public class commentDAOlmpl implements commentDAO {


	private JdbcTemplate jdbcTemplate;
	
	public commentDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<comment> getall(String type,int id) {
		String sql = "SELECT * FROM comment where comment_type=? and comment_idrefer=?  order by comment_id desc limit 300";
		List<comment> comments  =  jdbcTemplate.query(sql,new Object[]{type,id},new commentmapper());
		return comments;
	}

	@Override
	public comment get(String  id) {
		String sql = "SELECT * FROM comment where comment_id=? ";
		comment comments  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new commentmapper());
		return comments;
	}

	
	@Override
	public int delete(int id) {
		 String sql="delete from comment where  comment_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public int save(comment comments) {
		Object[] commentInfo = { 
				comments.getComment_date(),
				comments.getComment_type(),
				comments.getComment_comment(),
				comments.getComment_note(),
				comments.getComment_name(),
				comments.getComment_idrefer()
				};
				String sql = "INSERT INTO comment(comment_date,comment_type,comment_comment,comment_note,comment_name,comment_idrefer) "
						+ "VALUES(?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,commentInfo);
	}


	@Override
	public int edit(comment comments) {
		Object[] commentInfo = { 
				comments.getComment_date(),
				comments.getComment_type(),
				comments.getComment_comment(),
				comments.getComment_note(),
				comments.getComment_name(),
				comments.getComment_id()
				};
				String sql = " UPDATE comment set comment_date =? ,comment_type=?,comment_comment=?,comment_note=?,comment_name=?   where comment_id =? ";
				return jdbcTemplate.update(sql,commentInfo);
	}

}
