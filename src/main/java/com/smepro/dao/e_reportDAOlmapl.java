package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.backup;
import com.smepro.models.central;
import com.smepro.models.e_report;
import com.smepro.models.edata;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.centralmapper;
import com.smepro.rowmapper.e_reportmapper;
import com.smepro.rowmapper.edatamapper2;

public class e_reportDAOlmapl implements e_reportDAO {

	private JdbcTemplate jdbcTemplate;
	
	public e_reportDAOlmapl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public void save(e_report e_reports) {
		Object[] e_reportInfo = { 
				e_reports.getE_report_idadd(),
				e_reports.getE_report_conditionid(),
				e_reports.getLine_label(),
				e_reports.getLine_data(),
				e_reports.getLine_target(),
				e_reports.getColumn_label(),
				e_reports.getColumn_data(),
				e_reports.getColumn_target(),
				e_reports.getPie_label(),
				e_reports.getPie_data(),
				};
				String sql = "INSERT INTO e_report(e_report_idadd,e_report_conditionid,line_label,line_data,line_target"
						+ ",column_label,column_data,column_target,pie_label,pie_data) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
				 jdbcTemplate.update(sql,e_reportInfo);

	}


	@Override
	public void update(e_report e_reports) {
		Object[] e_reportInfo = { 
				e_reports.getLine_label(),
				e_reports.getLine_data(),
				e_reports.getLine_target(),
				e_reports.getColumn_label(),
				e_reports.getColumn_data(),
				e_reports.getColumn_target(),
				e_reports.getPie_label(),
				e_reports.getPie_data(),
				e_reports.getStatus(),
				e_reports.getE_report_id(),
				};
		
		String sql = "UPDATE  e_report  SET line_label=?,line_data=?,line_target=?,column_label=?,column_data=?,column_target=?,pie_label=?,pie_data=?,status=?  where e_report_id =?  ";
		   jdbcTemplate.update(sql,e_reportInfo);

	}

	@Override
	public e_report getby(int id,int condition) {
	
		  try {
				String sql = "SELECT * FROM e_report  where e_report_idadd = ? and e_report_conditionid =? limit 1 ";
				e_report e_reports  =  jdbcTemplate.queryForObject(sql,new Object[]{id,condition},new e_reportmapper());
				return e_reports;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;

		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	
		
		
	}
	

}
