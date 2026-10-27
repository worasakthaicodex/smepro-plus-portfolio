package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.comment;
import com.smepro.models.form;
import com.smepro.rowmapper.commentmapper;
import com.smepro.rowmapper.conditionmapper;
import com.smepro.rowmapper.formmapper;

public class formDAOlmpl implements formDAO {


	private JdbcTemplate jdbcTemplate;
	
	public formDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<form> getall() {
		String sql = "SELECT * FROM form   order by form_id desc limit 300";
		List<form> forms  =  jdbcTemplate.query(sql,new formmapper());
		return forms;
	}

	@Override
	public form getbyids(int  id) {

	
	    try {
			String sql = "SELECT * FROM form where form_condition=?  order by form_id desc limit 1 ";
			form forms  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new formmapper());
			return forms;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
	    
		
		
	}

	@Override
	public form getbyid(int  id) {

	
	    try {
			String sql = "SELECT * FROM form where form_id=?  order by form_id desc limit 1 ";
			form forms  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new formmapper());
			return forms;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
	    
		
		
	}
	@Override
	public int delete(int id) {
		 String sql="delete from form where  form_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public int save(form forms) {
		Object[] formInfo = { 
				forms.getForm_name(),
				forms.getForm_condition(),
				forms.getForm_tag1(),
				forms.getForm_tag2(),
				forms.getForm_tag3(),
				forms.getForm_tag4(),
				forms.getForm_tag5(),
				forms.getForm_tag6(),
				forms.getForm_tag7(),
				forms.getForm_tag8(),
				forms.getForm_tag9(),
				forms.getForm_tag10(),
				forms.getForm_tag11(),
				forms.getForm_tag12(),
				forms.getForm_tag13(),
				forms.getForm_tag14(),
				forms.getForm_tag15(),
				forms.getForm_tag16(),
				forms.getForm_tag17(),
				forms.getForm_tag18(),
				forms.getForm_tag19(),
				forms.getForm_tag20(),
				forms.getForm_tag21(),
				forms.getForm_tag22(),
				forms.getForm_tag23(),
				forms.getForm_tag24(),
				forms.getForm_tag25(),
				forms.getForm_tag26(),
				forms.getForm_tag27(),
				forms.getForm_tag28(),
				forms.getForm_tag29(),
				forms.getForm_tag30(),
				forms.getForm_tag31(),
				forms.getForm_tag32(),
				forms.getForm_tag33(),
				forms.getForm_tag34(),
				forms.getForm_tag35(),
				forms.getForm_tag36(),
				forms.getForm_tag37(),
				forms.getForm_tag38(),
				forms.getForm_tag39(),
				forms.getForm_tag40()
				};
				String sql = "INSERT INTO form(form_name,form_condition,form_tag1,form_tag2,form_tag3,form_tag4,form_tag5,form_tag6,form_tag7,form_tag8,form_tag9,form_tag10,form_tag11,form_tag12,form_tag13,form_tag14,form_tag15,form_tag16,form_tag17,form_tag18,form_tag19,form_tag20,form_tag21,form_tag22,form_tag23,form_tag24,form_tag25,form_tag26,form_tag27,form_tag28,form_tag29,form_tag30,form_tag31,form_tag32,form_tag33,form_tag34,form_tag35,form_tag36,form_tag37,form_tag38,form_tag39,form_tag40) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,formInfo);
	}


	@Override
	public int edit(form forms) {
		Object[] formInfo = { 
				forms.getForm_condition(),
				forms.getForm_tag1(),
				forms.getForm_tag2(),
				forms.getForm_tag3(),
				forms.getForm_tag4(),
				forms.getForm_tag5(),
				forms.getForm_tag6(),
				forms.getForm_tag7(),
				forms.getForm_tag8(),
				forms.getForm_tag9(),
				forms.getForm_tag10(),
				forms.getForm_tag11(),
				forms.getForm_tag12(),
				forms.getForm_tag13(),
				forms.getForm_tag14(),
				forms.getForm_tag15(),
				forms.getForm_tag16(),
				forms.getForm_tag17(),
				forms.getForm_tag18(),
				forms.getForm_tag19(),
				forms.getForm_tag20(),
				forms.getForm_tag21(),
				forms.getForm_tag22(),
				forms.getForm_tag23(),
				forms.getForm_tag24(),
				forms.getForm_tag25(),
				forms.getForm_tag26(),
				forms.getForm_tag27(),
				forms.getForm_tag28(),
				forms.getForm_tag29(),
				forms.getForm_tag30(),
				forms.getForm_tag31(),
				forms.getForm_tag32(),
				forms.getForm_tag33(),
				forms.getForm_tag34(),
				forms.getForm_tag35(),
				forms.getForm_tag36(),
				forms.getForm_tag37(),
				forms.getForm_tag38(),
				forms.getForm_tag39(),
				forms.getForm_tag40(),
				forms.getForm_id()
				};
				String sql = " UPDATE form set "
						+ "form_condition= ? ,form_tag1= ? ,form_tag2= ? ,form_tag3= ? ,form_tag4= ? ,form_tag5= ? ,form_tag6= ? ,form_tag7= ? ,form_tag8= ? ,form_tag9= ? ,form_tag10= ? ,form_tag11= ? ,form_tag12= ? ,form_tag13= ? ,form_tag14= ? ,form_tag15= ? ,form_tag16= ? ,form_tag17= ? ,form_tag18= ? ,form_tag19= ? ,form_tag20= ? ,form_tag21= ? ,form_tag22= ? ,form_tag23= ? ,form_tag24= ? ,form_tag25= ? ,form_tag26= ? ,form_tag27= ? ,form_tag28= ? ,form_tag29= ? ,form_tag30= ? ,form_tag31= ? ,form_tag32= ? ,form_tag33= ? ,form_tag34= ? ,form_tag35= ? ,form_tag36= ? ,form_tag37= ? ,form_tag38= ? ,form_tag39= ? ,form_tag40 =?"
						+ " where form_id =? ";
				return jdbcTemplate.update(sql,formInfo);
	}

}
