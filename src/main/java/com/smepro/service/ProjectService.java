package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.models.Keyactivity;



@Service
public class ProjectService {

	private upload_pnDAO Upload_pnDAO;
	private taskDAO TaskDAO;
	private dateservice Dateservice;
	
	
	 @Autowired
	    public void setMyRepository(
	    		upload_pnDAO Upload_pnDAO,
	    		taskDAO TaskDAO,
	    		dateservice Dateservice
	    		){
	    		this.Upload_pnDAO = Upload_pnDAO;
	    		this.TaskDAO = TaskDAO;
	    		this.Dateservice = Dateservice;
	    	}
	    

	public void check() {

		 LocalDateTime localDate = LocalDateTime.now();
	 	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
		
		List<Keyactivity> keyactivitys = Upload_pnDAO.getAllKeyactivity();
		keyactivitys.stream().forEach(row->{
			int  task =   TaskDAO.getCountAllStatus(row.getId());
			if(task == 0) {
				row.setStatus(2);
			}else {
				if(Dateservice.Criteria_Check(date, row.getDateend())) {
					row.setStatus(1);
				}else {
					row.setStatus(-1);
				}
			}
		});
		
		Upload_pnDAO.updatebatchKeyactivitys(keyactivitys);
		
	}
	
}
