package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.dto.savefileDTO;
import com.smepro.models.FileInfo;
import com.smepro.models.docexpire;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.user;
import com.smepro.rowmapper.docexpiremapper;
import com.smepro.rowmapper.fileanddarmapper;
import com.smepro.rowmapper.filemapper;
import com.smepro.rowmapper.filemapper2;
import com.smepro.rowmapper.filemapper3;
import com.smepro.rowmapper.filemapper4;
import com.smepro.rowmapper.fileserchmapper;




public class fileDAOlmpl implements fileDAO {

	private JdbcTemplate jdbcTemplate;
	
	public fileDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public List<file> getall(int idrefer) {
		String sql = "SELECT * FROM file where file_folder=? and  file_type2 = 0   ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{idrefer},new filemapper());
		return files;
	}
	
	
	@Override
	public List<file> getallfile_edata(int idrefer) {
		String sql = "SELECT * FROM file where file_edata=?   ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{idrefer},new filemapper());
		return files;
	}
	
	
	@Override
	public List<file> getallfile_code(String file_code) {
		String sql = "SELECT * FROM file where file_code=?  AND  (file_no IS NOT NULL OR file_type ='eform')   order by file_id desc limit 5 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{file_code},new filemapper());
		return files;
	}
	
	@Override
	public List<file> getalltask(int id) {
		String sql = "SELECT * FROM file where file_task=? ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper());
		return files;
	}
	
	@Override
	public List<file> getallreport(int id) {
		String sql = "SELECT * FROM file where file_report=? ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper());
		return files;
	}
	
	
	

	@Override
	public file getbyactionplan(int id) { 
		
		try {
			    String sql="SELECT * FROM file where file_actionplan=?  limit 1  ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new filemapper());  
			     
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	    
	}
	
	@Override
	public file getbyactionplans(int id) { 
		
		try {
			    String sql="SELECT * FROM file where file_subactionplan=?  limit 1 ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new filemapper());  
			     
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	    
	}
	
	
	
	
	@Override
	public List<file> getallactionplan(int id) {
		String sql = "SELECT * FROM file where file_actionplan=? ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper());
		return files;
	}
	
	@Override
	public List<file> getallsubactionplan(int id) {
		String sql = "SELECT * FROM file where file_subactionplan=? ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper());
		return files;
	}
	
	@Override
	public docexpire docexpireyear(String year) {
		String sql = "SELECT"
		 		+ " SUM(file_code = 'Quality Manual (QM)' )  as  total_QM, "
		 		+ " SUM(file_code = 'Quality Procedure (QP)' )  as  total_QP, "
		 		+ " SUM(file_code = 'Work Instruction (WI)' )  as  total_WI, "
		 		+ " SUM(file_code = 'Supporting Data (SD)' )  as  total_SD, "
		 		+ " SUM(file_code = 'Form (FO)' )  as  total_FO "
				+ "FROM file  where  YEAR(file_dateend) =?  and  file_size > 0 and file_code != ''  and file_path != 'temporary'  ";
		docexpire files  =  jdbcTemplate.queryForObject(sql,new Object[]{year},new docexpiremapper());
		return files;
	}
	
	
	@Override
	public List<file> getallexpired() {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON "
				+ "folder.folder_type = file.file_path  where  file.file_type2 < 0 "
				+ "and  file.file_size > 0 and file.file_code != ''  "
				+ "and file.file_path != 'temporary' or file.file_type2 < 0 and  file.eform > 0  ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{},new filemapper3());
		return files;
	}
	
	@Override
	public List<file> getallexpiredbyfileno(String fileno) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ " where  file.file_type2 < 0 and  file.file_size > 0 and file.file_code != '' "
				+ " and file.file_path != 'temporary'  and file.file_no=? ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{fileno},new filemapper3());
		return files;
	}
	
	
	
	@Override
	public List<file> getallbydateold(String date) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ " where  file.file_dateend <= ?  and  file.file_no IS NULL  limit 10";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{date},new filemapper3());
		return files;
	}
	
	
	@Override
	public List<file> getallshortcut(int fileid) {
		String sql = "SELECT *  FROM file  where file_parent=? ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{fileid},new filemapper());
		return files;
	}
	
	
	
	
	@Override
	public List<file> getall2() {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path  "
				+ "where  file.file_type2 = 0 and  file.file_size > 0 and file.file_code != '' "
				+ " and  file.file_path != 'temporary'  or  file.file_type2 = 0  and file.eform > 0 order by file.file_id  desc ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{},new filemapper3());
		return files;
	}
	
     @Override
	public List<file> getall2(String data) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path  "
				+ "where"
				+ " file_name_original like ? AND "
				+ " ( file.file_type2 = 0 and  file.file_size > 0 and file.file_code != ''  and  file.file_path != 'temporary'  or file.file_type2 = 0  and file.eform > 0 )   "
				+ " OR file_no like ? AND "
				+ " ( file.file_type2 = 0 and  file.file_size > 0 and file.file_code != ''  and  file.file_path != 'temporary'  or file.file_type2 = 0  and file.eform > 0 )   "
				+ "   order by file.file_id  desc ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%","%"+data+"%"},new filemapper3());
		return files;
	}
	
	@Override
	public List<file> getall2(String data,String start,String end) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path  "
				+ "where"
				+ " file_name_original like ? AND (file_dateadd >= ? and file_dateadd <= ? OR file_dateend >= ? and file_dateend <= ?) AND "
				+ " ( file.file_type2 = 0 and  file.file_size > 0 and file.file_code != ''  and  file.file_path != 'temporary'  or file.file_type2 = 0  and file.eform > 0 )   "
				+ " OR file_no like ? AND (file_dateadd >= ? and file_dateadd <= ? OR file_dateend >= ? and file_dateend <= ?)  AND "
				+ " ( file.file_type2 = 0 and  file.file_size > 0 and file.file_code != ''  and  file.file_path != 'temporary'  or file.file_type2 = 0  and file.eform > 0 )   "
				+ "   order by file.file_id  desc ";
		List<file> files  =  jdbcTemplate.query(sql,
				new Object[]{"%"+data+"%","%"+start+"%","%"+end+"%","%"+start+"%","%"+end+"%"
						,"%"+data+"%","%"+start+"%","%"+end+"%","%"+start+"%","%"+end+"%"
						},new filemapper3());
		return files;
	}
	
	
	
	
 
	
	

	
	@Override
	public List<file> getallidoc(int group_id) {
			String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
					+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
					+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
					+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
					+ " where file.file_shotcut IS NOT NULL and  file.file_type2 =0 and group_authorities.group_id =?  group by file.file_id   order by file.file_id  desc limit 20 ";
			List<file> files  =  jdbcTemplate.query(sql,new Object[]{group_id},new filemapper4());
			return files;
		}
	
	
	@Override
	public List<file> getall3( String date) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path  where file.file_dateend < ?  and file.file_dateend != null  and  file.file_type2 = 0 and  file.file_size > 0 and file.file_code != ''  and file.file_path != 'temporary'  ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{date},new filemapper3());
		return files;
	}
	
	@Override
	public file getsum(String path) {
		String sql = "SELECT file_path,SUM(file_size) as file_size FROM file where file_path=?  ";
		 return jdbcTemplate.queryForObject(sql, new Object[]{path},new filemapper2()); 
	}
	
	public int getsumfile(String type) {
		String sql = "select count(*) from file where file_code=? and file_type2=0 and file_no IS NOT NULL  ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] { type }, Integer.class);
	}
	

	@Override
	public void save(String file_name,String file_name_original,String file_type,String file_path,long file_size,String size,int file_id_refer,String code) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_size,
				size,
				file_id_refer,
				code,
				null
				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type"
						+ ",file_path,file_size,file_size2,file_id_refer,file_folder,file_type2,file_code,file_dateend)"
						+ " VALUES(?,?,?,?,?,?,?,0,0,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	@Override
	public void save2(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,String file_idpn,String file_dateadd,String file_path_full,String file_by,String dateend) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_size,
				size,
				file_folder,
				dateend,
				shotcut,
				file_idpn,
				file_dateadd,
				file_path_full,
				file_by

				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_size2,file_id_refer,file_folder,file_type2,file_dateend,file_shotcut,file_idpn,file_dateadd,file_path_full,file_by)"
						+ " VALUES(?,?,?,?,?,?,0,?,0,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	
	@Override
	public void savetask(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int task_id,String file_dateadd,String file_path_full,String file_by,String dateend) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_size,
				size,
				file_folder,
				dateend,
				shotcut,
				task_id,
				file_dateadd,
				file_path_full,
				file_by

				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_size2,file_id_refer,file_folder,file_type2,file_dateend,file_shotcut,file_task,file_dateadd,file_path_full,file_by)"
						+ " VALUES(?,?,?,?,?,?,0,?,0,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	@Override
	public void savereport(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int report_id,String file_dateadd,String file_path_full,String file_by,String dateend) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_size,
				size,
				file_folder,
				dateend,
				shotcut,
				report_id,
				file_dateadd,
				file_path_full,
				file_by

				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_size2,file_id_refer,file_folder,file_type2,file_dateend,file_shotcut,file_report,file_dateadd,file_path_full,file_by)"
						+ " VALUES(?,?,?,?,?,?,0,?,0,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	
	
	
	
	
	
	
	@Override
	public void savesubtask(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int file_actionplan,String file_dateadd,String file_path_full,String file_by,String dateend) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_actionplan,
				size,
				file_folder,
				dateend,
				shotcut,
				file_actionplan,
				file_dateadd,
				file_path_full,
				file_by

				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_size2,file_id_refer,file_folder,file_type2,file_dateend,file_shotcut,file_actionplan,file_dateadd,file_path_full,file_by)"
						+ " VALUES(?,?,?,?,?,?,0,?,0,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	@Override
	public void savesubtask2(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int file_actionplan,String file_dateadd,String file_path_full,String file_by,String dateend) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_actionplan,
				size,
				file_folder,
				dateend,
				shotcut,
				file_actionplan,
				file_dateadd,
				file_path_full,
				file_by

				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_size2,file_id_refer,file_folder,file_type2,file_dateend,file_shotcut,file_subactionplan,file_dateadd,file_path_full,file_by)"
						+ " VALUES(?,?,?,?,?,?,0,?,0,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	
	@Override
	public void saveShortcut(String file_name,String file_name_original,String file_type,String file_path,long file_size
			,int file_folder,String shotcut,String file_path_full,int parentfile) {
		Object[] fileInfo = { 
				file_name,
				file_name_original,
				file_type,
				file_path,
				file_size,
				file_folder,
				shotcut,
				file_path_full,
				parentfile
				};
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_folder,file_shotcut,file_path_full,file_parent)"
						+ " VALUES(?,?,?,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	
	@Override
	public void saveeform(file files) {
		Object[] fileInfo = { 
				files.getFile_name_original(),
				files.getFile_no(),
				files.getFile_rev(),
				files.getFile_id_refer(),
				files.getFile_dateadd(),
				files.getFile_dateend(),
				files.getFile_by(),
				files.getFile_jd(),
				files.getEform(),
				"eform",
				files.getFile_code(),
				};
				String sql = "INSERT INTO file(file_name_original,file_no,file_rev,file_id_refer"
						+ ",file_dateadd,file_dateend,file_by,file_jd,eform,file_type,file_code)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				jdbcTemplate.update(sql,fileInfo);

	}
	@Override
	public void editeform(file files) {
		Object[] fileInfo = { 
				files.getFile_name_original(),
				files.getFile_no(),
				files.getFile_rev(),
				files.getFile_id_refer(),
				files.getFile_dateadd(),
				files.getFile_dateend(),
				files.getFile_by(),
				files.getFile_jd(),
				files.getEform(),
				"eform",
				files.getFile_code(),
				files.getFile_id(),
	
				};
				String sql = "update  file set  file_name_original=?,file_no=?,file_rev=?,file_id_refer=?,file_dateadd=?"
						+ ",file_dateend=?,file_by=?,file_jd=?,eform=?,file_type=?,file_code=?  where file_id =? ";
				jdbcTemplate.update(sql,fileInfo);

	}
	
	@Override
	public file getbyId(int id) { 
	    String sql="select * from file where file_id=?  ";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{id},new filemapper());  
	}
	
	@Override
	public file  getbynamefile(String file){ 
	
	    
		 try {
			    String sql="select * from file where file_name=?  and file_idpn IS NOT NULL ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{file},new filemapper());  
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	    
	    
	}
	
	@Override
	public file  getbynamefileshortcut(String file){ 
		
		 try {
			    String sql="select * from file where file_name=?  and file_idpn IS NOT NULL  limit 1";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{file},new filemapper());  
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	
	}
	

	@Override
	public file getbyOld_id(String id) { 
	    String sql="select * from file where file_no=? and file_type2 ='0'  ";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{id},new filemapper());  
	}

	
	
	@Override
	public boolean checkdoc(String id) { 
	    String sql="select count(*) from file where file_no=?  and file_type2 != -1  ";    
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id}, Integer.class);
	    return count > 0; 
	}
	@Override
	public boolean checkdoc2(int id) { 
	    String sql="select count(*) from file where file_id_refer=? ";    
	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id}, Integer.class);
	    return count > 0; 
	}
	
	@Override
	public file getbyIdrefer(int id) {
		 try {
			    String sql="select * from file where file_id_refer=?   ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new filemapper());  
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }

	}

	@Override
	public int cancel(String id) {
		  String sql=  "UPDATE file SET file_type2=-1 where  file_no='"+id+"' and  file_type2 != -1 ";
		    return jdbcTemplate.update(sql);   
	}
	
	
	@Override
	public int setShortcutnewpath(String namefile,String newpath) {
		  String sql=  "UPDATE file SET file_shotcut='"+newpath+"' where  file_name='"+namefile+"' ";
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	@Override
	public int cancel_shotcut(String id) {
		  String sql=  "UPDATE file SET file_type2=-1 where  file_name_original='"+id+"' and  file_type2 != -1 ";
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	@Override
	public int cancel2(String id) {
		  String sql=  "UPDATE file SET file_type2=-2 where  file_no='"+id+"' and  file_type2 != -1 ";
		    return jdbcTemplate.update(sql);   
	}
	
	@Override
	public int cancel3(int id) {
		  String sql=  "UPDATE file SET file_type2=-3 where  file_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	
	@Override
	public int move(int id,String path,String Pathfull,int file_foder) {
		  String sql=  "UPDATE file SET file_path='"+path+"',file_path_full='"+Pathfull+"',file_folder='"+file_foder+"' where  file_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	@Override
	public int set(int id,String floder,int floderid,String no,String rev,String date_add,String date_end,String doc_name,String Pathfull,String file_jd) {
		
	    if(date_add == "" || date_add== null) {
	    	date_add =null;
        }else {
        	date_add = "'"+date_add+"'";
        }
        if(date_end == "" || date_end== null ) {
        	date_end =null;
        }else {
        	date_end = "'"+date_end+"'";
        }

		String sql=  "UPDATE file SET file_path='"+floder+"',"
		  		+ "file_no='"+no+"' ,"
		  		+ "file_rev='"+rev+"' ,"
		  		+ "file_dateadd="+date_add+" ,"
		  		+ "file_dateend="+date_end+" ,"
		  		+ "file_folder='"+floderid+"' ,"
		  		+ "file_name_original='"+doc_name+"' ,"
		  		+ "file_path_full='"+Pathfull+"', "
		  		+ "file_jd='"+file_jd+"' "
		  		+ " WHERE  file_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	

	
	
	
	@Override
	public int setShortcut( int newparentfile,String newfile,String newname,String filetype,int parentfile) {
		String sql=  "UPDATE file SET file_name='"+newfile+"',"
				+ "file_type='"+filetype+"', "
				+ "file_name_original='"+newname+"', "
				+ "file_parent='"+newparentfile+"' "
		  		+ " WHERE  file_parent='"+parentfile+"' ";
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	
	@Override
	public int expired(String date) {
		  String sql=  "UPDATE file SET file_type2='-2'"
		  		+ " WHERE  file_dateend < '"+date+ "' and file_type2 = 0";
		    return jdbcTemplate.update(sql);   
	}
	
	@Override
	public int changefile_jd(String user_names,String user_name) {
		  String sql=  "UPDATE file SET file_jd= ? "
		  		+ " WHERE  file_jd =? ";
		    return jdbcTemplate.update(sql,new Object[] {user_names,user_name});   
	}
	
	
	
	@Override
	public int update(file files) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		     String sql="delete from file where file_id="+id;    
		    return jdbcTemplate.update(sql);
	}
	
	@Override
	public int deleteforfilename(String filename) {
		     String sql="delete from file where file_name='"+filename+"' ";    
		    return jdbcTemplate.update(sql);
	}
	
	
	@Override
	public int delete2(int id) {
		     String sql="delete from file where file_code IS NULL  and  file_id="+id;    
		    return jdbcTemplate.update(sql);
	}
	
	
	@Override
	public List<file> searchdocold(String data) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN dar ON dar.dar_docold_id = file.file_no  or  dar.dar_doc_id = file.file_no  "
			
				+ " where  file.file_no = ? and file.file_type2 = 0  and file.file_type2 = 0 and file.file_no IS NOT NULL   group by file.file_no   order by dar.dar_id  desc limit 1 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{data},new fileanddarmapper());
		return files;
	}
	
	@Override
	public List<file> search(String data,String datestart,String dateend) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " where  report_pn.upload_pn_note like ? and  file.file_dateadd >= ? and  file.file_dateadd <= ?  and log.log_plan > 0 "
				+ "  or file.file_name_original like ? and  file.file_dateadd >= ? and  file.file_dateadd <= ? and log.log_plan > 0 "
				+ " group by file.file_id   order by file.file_id  desc limit 20 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%",datestart,dateend,"%"+data+"%",datestart,dateend},new fileserchmapper());
		return files;
	}
	
	
	@Override
	public List<file> search(String data) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " where ( upload_pn.upload_pn_note like ?    or file.file_name_original like ? )  and  log.log_plan > 0  group by file.file_id   order by file.file_id  desc limit 20 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%","%"+data+"%"},new fileserchmapper());
		return files;
	}
	
	
	
	@Override
	public List<file> search2(String data,String datestart,String dateend) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " where  file.file_no like ? and  file.file_dateadd >= ? and  file.file_dateadd <= ?  and file.file_parent > 0 "
				+ "  or file.file_name_original like ? and  file.file_dateadd >= ? and  file.file_dateadd <= ? and file.file_parent > 0 "
				+ " group by file.file_id   order by file.file_id  desc limit 20 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%",datestart,dateend,"%"+data+"%",datestart,dateend},new fileserchmapper());
		return files;
	}
	
	
	@Override
	public List<file> search2(String data) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " where ( file.file_no like ?    or file.file_name_original like ? )  and  file.file_parent > 0  group by file.file_id   order by file.file_id  desc limit 20 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%","%"+data+"%"},new fileserchmapper());
		return files;
	}
	
	
	@Override
	public List<file> search3(String data,String datestart,String dateend) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " where  file.file_name_original like ? and  file.file_dateadd >= ? and  file.file_dateadd <= ? "
				+ " and file.file_parent = 0   and  file.file_idpn = 0  and file.file_code IS NULL "
				+ " group by file.file_id   order by file.file_id  desc limit 20 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%",datestart,dateend},new fileserchmapper());
		return files;
	}
	
	
	@Override
	public List<file> search3(String data) {
		String sql = "SELECT *  FROM file LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ "LEFT JOIN group_authorities ON concat('READ-',file.file_path) = group_authorities.authority "
				+ "LEFT JOIN report_pn ON file.file_idpn = report_pn.report_pn_id "
				+ "LEFT JOIN upload_pn ON report_pn.report_pn_idrefer = upload_pn.upload_pn_id "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " where  file.file_name_original like ?  "
				+ "and  file.file_parent = 0  and  file.file_idpn = 0  and file.file_code IS NULL   "
				+ " group by file.file_id   order by file.file_id  desc limit 20 ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{"%"+data+"%"},new fileserchmapper());
		return files;
	}
	
	
	@Override
	public int getexpired(String type) {
		String sql = "SELECT  count(*) from file  where  file_type2 < 0 "
				+ "and  file_size > 0 and file_code != ''  "
				+ "and file_path != 'temporary' "
				+ "and file_type2 = '-2'  and file_code =?   ";
		return jdbcTemplate.queryForObject(
	            sql, new Object[] { type }, Integer.class);
	}
	
	
	@Override
	public int renew( int id,String renewDate) {
		  String sql=  "UPDATE file SET file_type2=0,file_dateend='"+renewDate+"'  where  file_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	
	@Override
	public List<file> getallLog(String idall) {
		String sql = "SELECT *  FROM file "
				+ "LEFT JOIN log ON file.file_name = log.log_file "
				+ " LEFT JOIN folder ON folder.folder_type = file.file_path "
				+ " where  find_in_set(log.log_id,?)  ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{idall},new filemapper3());
		return files;
	}
	
	@Override
	public List<file> getallEdata(int id) {
		String sql = "SELECT *  FROM file where  file_edata = ?  ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper3());
		return files;
	}
	@Override
	public List<file> getallEdata(edata edatas) {
		String sql = "SELECT *  FROM file where  file_edata = ?   OR  FIND_IN_SET(file_edata ,?)   ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{edatas.getData_id(),edatas.getData_refer()},new filemapper3());
		return files;
	}
	@Override
	public List<file> getallFileIndexapp(int id) {
		String sql = "SELECT *  FROM file where  file_indexapp = ?  ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper3());
		return files;
	}
	@Override
	public List<file> getallFile_ticketid(int id) {
		String sql = "SELECT *  FROM file where  file_ticketid = ?  ";
		List<file> files  =  jdbcTemplate.query(sql,new Object[]{id},new filemapper3());
		return files;
	}
	
	
	@Override
	public void saveAllFilesBatch(List<file> fileList) {
	    // ⚠️ file_size ต้องเป็นชนิดตัวเลขใน DB (BIGINT)
	    String sql = "INSERT INTO file (file_indexapp, file_tag, file_name, file_name_original, file_type, file_size,file_path,file_path_external) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        @Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	            file f = fileList.get(i);
	            
	            ps.setInt(1, f.getFile_indexapp());
	            ps.setString(2, f.getFile_tag());
	            ps.setString(3, f.getFile_name());
	            ps.setString(4, f.getFile_name_original());
	            ps.setString(5, f.getFile_type());
	            
	            ps.setLong(6, f.getFile_size()); // 🌟 ใช้ setLong
	            
	            ps.setString(7, f.getFile_path());
	            ps.setString(8, f.getFile_path_external());
	        }

	        @Override
	        public int getBatchSize() {
	            return fileList.size();
	        }
	    });
	    System.out.println("🚀 Batch Inserted Files: " + fileList.size());
	}
	
	
	
	
	@Override
	public void save(savefileDTO savefile) {
		
		Object[] fileInfo = { 
			 	savefile.getFilename(),
			 	savefile.getNewfilename(),
			 	savefile.getTypereal(),
			 	savefile.getFolder_type(),
				savefile.getSize(),
				savefile.getSize_string(),
			 	savefile.getFolder_id(),
				savefile.getDateend(),
				null,
				savefile.getReport_pnid(),
				savefile.getDate(),
				savefile.getPathfull(),
				savefile.getUsername(),
				savefile.getFile_step(),
				savefile.getFile_edata(),
				savefile.getFile_indexapp(),
				savefile.getFile_ticketid(),
				savefile.getFile_tag()
				};
		
				String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_path"
						+ ",file_size,file_size2,file_id_refer,file_folder,file_type2,file_dateend,file_shotcut,file_idpn,file_dateadd,file_path_full,file_by,file_step,file_edata,file_indexapp,file_ticketid,file_tag)"
						+ " VALUES(?,?,?,?,?,?,0,?,0,?,?,?,?,?,?,?,?,?,?,?);";
				jdbcTemplate.update(sql,fileInfo);

	}
	

	@Override
	public int saveByFileInfo(FileInfo fileInfos) {
		
		
		Object[] fileInfo = { 
				fileInfos.getFile_name(),
				fileInfos.getFile_name_original(),
				fileInfos.getFile_type(),
				fileInfos.getFile_size(),
				fileInfos.getFile_size_display(),
				fileInfos.getFile_path(),
				fileInfos.getFile_date()
				};
		
		String sql = "INSERT INTO file(file_name,file_name_original,file_type,file_size,file_size2,file_path,file_dateadd)"
				+ " VALUES(?,?,?,?,?,?,?);";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		System.out.println("length " + fileInfo.length);
		
		
		jdbcTemplate.update(connection -> {
		    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    for (int i = 0; i < fileInfo.length; i++) {
		        ps.setObject(i + 1, fileInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
		    }
		    return ps;
		}, keyHolder);

		// คืนค่า id ที่ถูกสร้างขึ้น
		return keyHolder.getKey().intValue();
	}

	@Override
	public void setPop(String patchString, user users, int file_id) {
		String sql = "UPDATE file set file_path_full = ?,file_by =? WHERE file_id =? ";
		jdbcTemplate.update(sql,new Object[]{patchString,users.getUser_name(),file_id});
		
	}
 	

	
	
}
