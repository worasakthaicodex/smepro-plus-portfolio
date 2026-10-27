package com.smepro.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Transition;
import com.smepro.models.WorkSystemdata;
import com.smepro.models.loadmaindata;

public interface WorkSystem {

	
	
		void  LoadApp(LoadApp LoadApp);
		void  LoadApphasChanges(LoadApp LoadApp);
		void  LoadAppViewMode(LoadApp LoadApp);
		void  LoadSubData(LoadApp LoadApp);
		
		
		
		
		void  SaveApp(LoadApp LoadApp);
		void  FlowApp(LoadApp LoadApp);
		void  ApproveApp(LoadApp LoadApp);
		void  RecordApp(LoadApp LoadApp) throws Exception;
		void  CompletionApp(LoadApp LoadApp);
		void  ReceiveApp(LoadApp LoadApp);
		void  ForwardApp(LoadApp LoadApp);
		void  ClearApp(LoadApp LoadApp);
		void  Follow_upApp(LoadApp LoadApp);
		
		
		String getState();

		

}
