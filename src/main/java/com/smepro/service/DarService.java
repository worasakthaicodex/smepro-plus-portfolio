package com.smepro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.dar;
import com.smepro.models.document_no;
import com.smepro.models.file;


@Service
public class DarService {

	private darDAO DarDAO;
	private fileDAO FileDAO;
	
	@Autowired
	public void setMyRepository(
			darDAO DarDAO
	    		,eformfieldDAO EformfieldDAO
	    		,fileDAO FileDAO
	    		){
	        this.DarDAO = DarDAO;
	        this.FileDAO = FileDAO;
	    }
	
	 public void  AutoID(dar dar) {
		 
			
			document_no document_nos= DarDAO.getdocumentNO(dar.getDar_department(),dar.getDar_type());
			String filecode = null;
			
			if(document_nos == null) { return;}
			
			for(int i= document_nos.getAutoid(); i < 999;i++) {
				
				int auto = document_nos.getAutoid() + i;
				String formatted = String.format("%02d", auto);
				filecode =  document_nos.getDocument_no_pattern()+"-"+formatted;
				List<file> files  =  FileDAO.searchdocold(filecode);
				
				if(files.size() < 1 ) {
					i = 999;
					}
				else {
					filecode = null;
					DarDAO.setAutoid(document_nos.getAutoid(),i);
					dar.setDar_doc_id(filecode);
				}
				
			}
	 }

	
}
