package com.smepro.dao;

import java.util.List;

import com.smepro.models.comment;
import com.smepro.models.form;


public interface formDAO {
	List<form> getall();
	form getbyid(int id);
	form getbyids(int  id);
	int delete(int id);
	int save(form forms);
	int edit(form forms);
}
