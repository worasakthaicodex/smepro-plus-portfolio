package com.smepro.dao;

import java.util.List;

import com.smepro.models.central;

public interface centralDAO {

	  void save(central centrals);
	  void update(central centrals);
	  central getbyid(int id);
	  List<central> getall(int level ,int idrefer);
	  List<central> getall(int level);
	  int delete(int id);
	  void setotal(int id,int total);
	  int getsum(int id);
}
