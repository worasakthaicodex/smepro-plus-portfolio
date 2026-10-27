package com.smepro.dao;



import com.smepro.models.setting;




public interface settingDAO {
	
	int setholiday(int holiday_notsun);
	setting getbyid();
	int UpVersion();
}
