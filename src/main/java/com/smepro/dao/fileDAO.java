package com.smepro.dao;

import java.util.List;

import com.smepro.dto.savefileDTO;
import com.smepro.models.FileInfo;
import com.smepro.models.docexpire;
import com.smepro.models.edata;
import com.smepro.models.file;
import com.smepro.models.user;




public interface fileDAO {
	
	
	List<file> getall(int idrefer);
	List<file> getallfile_code(String file_code);
	List<file> getalltask(int id);
	List<file> getallreport(int id);
	List<file> getallactionplan(int id);
	file getbyactionplan(int id);
	file getbyactionplans(int id);
	List<file> getallsubactionplan(int id);
	
	List<file> getall2();
	List<file> getall2(String data);
	List<file> getall2(String data,String datestart,String dateend);

	
	List<file> getallidoc(int group_id);
	List<file> searchdocold(String data);
	List<file> search(String data);
	List<file> search(String data,String datestart,String dateend);
	List<file> search2(String data);
	List<file> search2(String data,String datestart,String dateend);
	List<file> search3(String data);
	List<file> search3(String data,String datestart,String dateend);
	
	
	
	
	List<file> getall3(String date);
	void save (String file_name,String file_name_original,String file_type,String file_path,long file_size,String size,int file_id_refer,String code);
	
	
	void save2 (String file_name,String file_name_original,String file_type,String file_path
			,long file_size,String size,int file_folder,String shotcut,String file_idpn,String file_dateadd,String file_path_full,String file_by,String dateend);
	
	void savetask(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int task_id,String file_dateadd,String file_path_full,String file_by,String dateend);
	
	
	void savereport(String file_name,String file_name_original
				,String file_type,String file_path,long file_size
				,String size,int file_folder,String shotcut,int report_id,String file_dateadd,String file_path_full,String file_by,String dateend);
	
	
	void savesubtask(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int file_actionplan,String file_dateadd,String file_path_full,String file_by,String dateend);
	
	
	void savesubtask2(String file_name,String file_name_original
			,String file_type,String file_path,long file_size
			,String size,int file_folder,String shotcut,int file_actionplan,String file_dateadd,String file_path_full,String file_by,String dateend);
	
	
	void saveShortcut(String file_name,String file_name_original,String file_type,String file_path
			,long file_size,int file_folder,String shotcut,String file_path_full,int parentfile);
	
	List<file> getallexpiredbyfileno(String fileno);
	List<file> getallshortcut(int fileid);
	List<file> getallexpired();
	List<file> getallbydateold(String date);
	file getbyId(int id);
	file getbyIdrefer(int id);
	file getbyOld_id(String id);
	file getbynamefile(String file);
	file getbynamefileshortcut(String file);
	file getsum(String path);
	int cancel(String id);
	int cancel_shotcut(String id);
	
	int move(int id,String path,String Pathfull,int file_foder);
	int cancel2(String id);
	int cancel3(int id);
	boolean checkdoc(String id);
	boolean checkdoc2(int id);
	int set( int id,String floder,int floderid,String no,String rev,String date_add,String date_end,String doc_name,String Pathfull,String file_jd);
	
	
	
	int setShortcut( int newparentfile,String newfile,String newname,String filetype,int parentfile);
	int setShortcutnewpath( String namefile,String newpath);
	int update( file files);
	int expired( String date);
	int delete(int id);
	int deleteforfilename(String filename);
	int delete2(int id);
	int getsumfile(String type);
	int getexpired(String type);
	int renew( int id,String renewDate);
	
	docexpire docexpireyear(String years);
	
	
    void saveeform(file files);
    void editeform(file files);
    int changefile_jd(String user_names,String user_name);
    
    
    List<file> getallLog(String idall);
	
    
    
    
    void save(savefileDTO savefile);
    List<file> getallEdata(int idrefer);
	List<file> getallfile_edata(int idrefer);
	int saveByFileInfo(FileInfo fileInfos);
	void setPop(String patchString, user users, int file_id);
	List<file> getallFileIndexapp(int id);
	void saveAllFilesBatch(List<file> fileList);
	List<file> getallFile_ticketid(int id);
	List<file> getallEdata(edata edatas);

}
