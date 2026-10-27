package com.smepro.dao;

import java.util.List;

import com.smepro.models.comment;


public interface commentDAO {
	List<comment> getall(String type , int id_refer);
	comment get(String id);
	int delete(int id);
	int save(comment comments);
	int edit(comment comments);
}
