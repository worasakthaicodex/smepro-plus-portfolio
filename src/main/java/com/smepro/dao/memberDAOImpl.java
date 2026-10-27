package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.smepro.models.member;
import com.smepro.rowmapper.membermapper;

public class memberDAOImpl implements memberDAO {

	private JdbcTemplate jdbcTemplate;
	final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	public memberDAOImpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	@Override
	public List<member> getAllmember() {
		String sql = "SELECT * FROM member";
		List<member> members  =  jdbcTemplate.query(sql , new membermapper());
		return members;
	}
	@Override
	public void  savemember(member members) {
		
		Object[] memberInfo = { 
				//passwordEncoder.encode(members.getName()),
				members.getName(),
				members.getDate(),
				
				};
				String sql = "INSERT INTO member(member_name,member_date) VALUES(?,?);";
		 
	               //jdbcTemplate.update(sql,memberInfo);
	                 KeyHolder keyHolder = new GeneratedKeyHolder();

	                 jdbcTemplate.update(connection -> {
	                	 
	                     PreparedStatement ps = connection
	                       .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	                       ps.setString(1, members.getName());
	                       ps.setString(2, members.getDate());
	                       return ps;
	                    
	                 }, keyHolder);

	                 //String key = keyHolder.getKey();
	                 System.out.println( keyHolder.getKey().intValue());
	               
	            // System.out.println( keyHolder.getKey());
	
	 
	}
	
	@Override
	public member getmemberById(int id){    
	    String sql="select * from member where member_id=?";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{id},new membermapper());    
	}  
	
	@Override
	public int update(member members){  
		
    String sql=  "UPDATE member SET member_name='"+members.getName()+"' WHERE member_id="+members.getId();
	    return jdbcTemplate.update(sql);    
	}   
	
	@Override
	public int delete(int id){    
	    String sql="delete from member where member_id="+id+"";    
	    return jdbcTemplate.update(sql);    
	}    
	
}
