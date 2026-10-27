package com.smepro.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.drives;
import com.smepro.models.file;
import com.smepro.models.folder;
import com.smepro.rowmapper.drivesmapper;
import com.smepro.rowmapper.filemapper;
import com.smepro.rowmapper.foldermapper;
import com.smepro.rowmapper.foldermapper2;
import com.smepro.rowmapper.foldermapperroot;
import com.smepro.rowmapper.foldermapperroot2;



public class folderDAOlmpl implements folderDAO {


	private JdbcTemplate jdbcTemplate;
	
	public folderDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<folder> getall(int idrefer) {
		String sql = "SELECT * FROM folder where folder_idrefer =? ";
		List<folder> folders  =  jdbcTemplate.query(sql , new Object[]{idrefer},new foldermapper());
		return folders;
	}
	@Override
	public List<folder> getalls(int idrefer) {
	    String sql = "SELECT COALESCE(COUNT(DISTINCT file.file_id), 0) as folder_doc"
	    		+ ", COALESCE(COUNT(DISTINCT  upload_pn.upload_pn_id ), 0) as folder_work"
	    		+ ", folder.folder_id, folder.folder_name, folder.folder_type"
	    		+ ", folder.folder_idrefer, folder.folder_type2 " +
	                 "FROM folder " +
	                 "LEFT JOIN file ON folder.folder_type = file.file_path AND file.file_no IS NOT NULL  " +
	                 "LEFT JOIN upload_pn ON upload_pn.upload_pn_idrefer = folder.folder_id AND upload_pn.upload_pn_state = 0 WHERE folder.folder_idrefer =? " +
	                 "GROUP BY folder.folder_id, folder.folder_name, folder.folder_type, folder.folder_idrefer  ";
	    List<folder> folders = jdbcTemplate.query(sql, new Object[]{idrefer}, new foldermapperroot2());
	    return folders;
	}

	
	
	
	@Override
	public List<folder> getallbyjd(int jd) {
		String sql = "SELECT * FROM folder "
				+ "LEFT JOIN group_authorities ON concat('UPLOAD-',folder.folder_type) = group_authorities.authority "
				+ "where group_authorities.group_id =? group by folder.folder_type";
		List<folder> folders  =  jdbcTemplate.query(sql , new Object[]{jd},new foldermapper());
		return folders;
	}
	
	
	@Override
	public List<folder> getall2() {
		String sql = "SELECT * FROM folder ";
		List<folder> folders  =  jdbcTemplate.query(sql,new foldermapper());
		return folders;
	}

	@Override
	public List<folder> getalls2() {
	    String sql = "SELECT COALESCE(COUNT(file.file_id), 0) as folder_doc, COALESCE(COUNT(upload_pn.upload_pn_id ), 0) as folder_work"
	    		+ ", folder.folder_id, folder.folder_name, folder.folder_type, folder.folder_idrefer " +
	                 "FROM folder " +
	                 "LEFT JOIN file ON folder.folder_type = file.file_path AND file.file_no IS NOT NULL  " +
	                 "LEFT JOIN upload_pn ON upload_pn.upload_pn_idrefer = folder.folder_id AND upload_pn.upload_pn_state = 0 " +
	                 "GROUP BY folder.folder_id, folder.folder_name, folder.folder_type, folder.folder_idrefer";
	    List<folder> folders = jdbcTemplate.query(sql, new foldermapperroot());
	    return folders;
	}

	@Override
	public folder getsum(int  idrefer) {
		String sql = "SELECT folder_idrefer,SUM(folder_use) as folder_use FROM folder where folder_idrefer=?  ";
		 return jdbcTemplate.queryForObject(sql, new Object[]{idrefer},new foldermapper2()); 
	}
	
	@Override
	public folder getsumuse() {
		  String sql="SELECT folder_idrefer,SUM(folder_use) as folder_use FROM folder ";     
		    return jdbcTemplate.queryForObject(sql, new Object[]{},new foldermapper2()); 
	
	
	}
	@Override
	public int numfolder(int  idrefer) {
	    String sql = "SELECT count(*) FROM folder WHERE  folder_idrefer=? ";
	   int count = jdbcTemplate.queryForObject(sql, new Object[] { idrefer }, Integer.class);
	   return count;
	
	}
	
	
	@Override
	public void save(folder folders,int idrefer,String type) {
		Object[] folderInfo = { 
				folders.getFolder_name(),
				type,
				idrefer,
				0,
				"-",
				folders.getFolder_type2()
				};
				String sql = "INSERT INTO folder(folder_name,folder_type,folder_idrefer,folder_use,folder_use2,folder_type2) VALUES(?,?,?,?,?,?);";
				jdbcTemplate.update(sql,folderInfo);

	}

	@Override
	public folder getbyId(int id) {   
		 try {
			   String sql="select * from folder where folder_id=?  ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new foldermapper()); 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	}
	
	@Override
	public folder getbytype(String type) {
	    String sql="select * from folder where folder_type=?  limit 1 ";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{type},new foldermapper()); 
	}
	

	@Override
	public int update(folder folders) {
		
		Object[] folderInfo = { 
				folders.getFolder_name(),
				folders.getFolder_type2(),
				folders.getFolder_id()
				};
		
		  String sql=  "UPDATE folder SET folder_name=?,folder_type2=?  WHERE  folder_id = ? ";
		    return jdbcTemplate.update(sql,folderInfo);   
	}

	@Override
	public int setuse(int id,long size,String size2) {
		  String sql=  "UPDATE folder SET folder_use='"+size+"',folder_use2='"+size2+"' WHERE  folder_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	
	
	@Override
	public int delete(int id) {
		 String sql="delete from folder where folder_id="+id+"";    
		    return jdbcTemplate.update(sql);
	}
	
	@Override
	public List<folder> getuse() {
		String sql = "SELECT * FROM folder where folder_idrefer > 100000 ";
		List<folder> folders  =  jdbcTemplate.query(sql , new Object[]{},new foldermapper());
		return folders;
	}

}
