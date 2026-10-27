package com.smepro.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.smepro.enums.AppAbility;
import com.smepro.enums.DB;
import com.smepro.enums.SendStatus;
import com.smepro.enums.Stock_status;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.service.FlowServices;
import com.smepro.service.userservice;



@Component
public class ReceiveApp_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	
	
	private RecordState State = RecordState.NONE;
	

	public enum RecordState {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}

	
	
	
	
	


	@Autowired
	public void setMyRepository(
	    		edataDAO EdataDAO,
	    		userDAO UserDAO,
	    		upload_pnDAO Upload_pnDAO,
	    		userservice Userservice,
	    		conditionDAO ConditionDAO,
	    		FlowServices flowServices,
	    		datasetDAO DatasetDAO,
	    		dataset_dataDAO Dataset_dataDAO,
	    		workflowDAO WorkflowDAO
	    		) { 
	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;
	        this.Upload_pnDAO = Upload_pnDAO;
	        this.flowServices = flowServices;
	        this.Userservice = Userservice;
	        this.ConditionDAO = ConditionDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.Dataset_dataDAO = Dataset_dataDAO;
	        this.WorkflowDAO = WorkflowDAO;
	    }

	
	

	public void ReceiveTransaction(LoadApp LoadApp) {
		
	if(LoadApp.getWorkflow() == null) {return;}

	workflow workflow = LoadApp.getWorkflow();
				switch (workflow.getWorkflow_maindb()) {
				case "Stock_Warehouse":
					break;
				case "Procurement_SupplyChain":
					Transaction_procurement_receive(LoadApp);
					break;
		        default:	

		}
	}


	private void Transaction_procurement_receive(LoadApp LoadApp) {


		System.out.println(" Transaction_procurement_receive ");
		System.out.println(" Transaction_procurement_receive->TempId "+LoadApp.getTempId());

		if(LoadApp.getContinue_count() > 0|| LoadApp.getModify_count() > 0) {
			
			
			List<edata_procurement> receive =  EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId(), "Create");
			System.out.println(" Transaction_procurement_receive->receive  "+receive.size());
			if(receive.size() == 0) { return;} 
			
			String id_refer = receive.stream()
				    .filter(row -> !("Pending").equals(row.getProcurement_status())) // กรองเฉพาะที่เป็น Approved
				    .filter(row -> row.getId_refer() > 0) // กรองค่า null และค่า <= 0 ออก
				    .map(row -> String.valueOf(row.getId_refer())) // แปลงเป็น String
				    .collect(Collectors.joining(",")); 
		
			if(id_refer.isBlank()) {
				 throw new NullPointerException("id_refer");
			}
			
			System.out.println(" Transaction_procurement_receive->TempId->id_refer "+id_refer);
			EdataDAO.ReceiveEdata_procurements_item(id_refer,LoadApp.getWorkflow().getWorkflow_maindb());
			
			
		}else {

			EdataDAO.ReceiveTransaction_send(LoadApp.getEdata().getData_id());
			
		}
		
		
		 
		 

			
		 
	}







	
}
