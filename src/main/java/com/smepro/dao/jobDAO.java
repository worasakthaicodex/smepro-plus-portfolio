package com.smepro.dao;

import java.util.List;

import com.smepro.models.attendance;
import com.smepro.models.check_log;
import com.smepro.models.missed_uploads;
import com.smepro.models.upload_log;





public interface jobDAO {
	
	  check_log CheckLog();
	  void SaveUploadedFiles(upload_log upload_log);
	  void SaveMissedUploads(missed_uploads missed_uploads);
	  void SaveLog();
	  void SavehandleUploadedFiles(upload_log upload_logs);
	  upload_log getResultGoal(int id);
	  

}
