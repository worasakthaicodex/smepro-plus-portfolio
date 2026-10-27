package com.smepro.dao;

import java.util.List;

import com.smepro.models.drives;


public interface drivesDAO {
	List<drives> getall();
	List<drives> getallbyjd(int jd);
	void save (drives drivess,String type,String size);
	drives getbyId(int id);
	int setuse(int id,long imageFileSize3,String Size4,double percent);
	int update( drives drivess);
	int delete(int id);
	drives getsumuse();
	int setuse(int id, long imageFileSize1, String Size1, long imageFileSize2, String Size2, double percent);

}
