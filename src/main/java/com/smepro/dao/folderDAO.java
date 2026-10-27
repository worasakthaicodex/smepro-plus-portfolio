package com.smepro.dao;

import java.util.List;


import com.smepro.models.folder;


public interface folderDAO {
	List<folder> getall(int idrefer);
	List<folder> getalls(int idrefer);
	List<folder> getallbyjd(int jd);
	List<folder> getall2();
	List<folder> getalls2();
	List<folder> getuse();
	void save (folder folders,int idrefer,String type);
	folder getbyId(int id);
	folder getsum(int id);
	folder getbytype(String  type);
	int update( folder folders);
	int setuse( int id,long size,String size2);
	int delete(int id);
	folder getsumuse();
	int numfolder( int id);
}
