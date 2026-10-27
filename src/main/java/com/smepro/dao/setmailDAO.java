package com.smepro.dao;

import com.smepro.models.setmail;

public interface setmailDAO {
	setmail getbyId(int id);
	int update( setmail setmails,int idrefer);
	int ismail(int id);
	void set(int id);


	
	
}
