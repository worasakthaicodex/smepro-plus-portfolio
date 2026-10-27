package com.smepro.dao;

import java.util.List;

import com.smepro.models.actionplan;


public interface actionplanDAO {
	List<actionplan> getall(int idrefer);
	List<actionplan> getalls(int idrefer);
	actionplan getByid(int id);
	actionplan getByids(int id);
	int delete(int id);
	int deletealls(int id);
	int deleteall(int id);
	int save(actionplan actionplans);
	int save2(actionplan actionplans);
	int update(actionplan actionplans);
	int setPoin(String poin,int id);
	int setcm(int result,int id);
	int numbyidrefer(int id);
	int numbyidrefer2(int id);
	
	
	
	List<actionplan> getAll(int idrefer);
	actionplan Total(int id);
	
	



	int setSubmission(int id, int Submission);
	actionplan getByIdreferAndUser_id(int idrefer, int id_add);
}
