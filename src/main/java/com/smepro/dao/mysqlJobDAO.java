package com.smepro.dao;

import java.util.List;

import com.smepro.models.actionplan;


public interface mysqlJobDAO {
	void createAutoPurgeEventEdataSoftDelete();
	void removeAutoPurgeEvent(String eventName);
	String getEventListAsString();
}
