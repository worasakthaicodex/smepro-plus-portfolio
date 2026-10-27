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
public class ForwardApp_Component {

	
	
	
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

	
	
	

	
	

	public void SendTransaction(LoadApp LoadApp) {
		
	if(LoadApp.getWorkflow() == null) {return;}
	
	System.out.println("002 .....................SendTransaction");
	
	
	int Letter = CreateLetter(LoadApp);		
	
	workflow workflow = LoadApp.getWorkflow();
				switch (workflow.getWorkflow_maindb()) {
				case "Stock_Warehouse":
					break;
				case "Procurement_SupplyChain":
					Transaction_procurement_send(LoadApp,Letter);
					break;
		        default:	

		}
	}

	public int CreateLetter(LoadApp LoadApp){
		
		
		System.out.println("002 .....................CreateLetter");
		
		
		String Receiver_type = "-";
		
		
		Optional<sub_processes>  Sub_processes =  LoadApp.getProcess().getSub_processes().stream().filter(row-> row.getAction() != null).findFirst();
		if(Sub_processes.isPresent()) { 
				sub_processes data = Sub_processes.get();  
				Receiver_type = data.getAction();
		}

		if(Receiver_type.equals("-")) {
			Sub_processes =  LoadApp.getProcess().getSub_processes().stream().filter(row-> row.getAction_batch_continue() != null).findFirst();
			if(Sub_processes.isPresent()) { 
				sub_processes data = Sub_processes.get();  
				Receiver_type = data.getAction();
			}
		}
		
	
		
		
		user users = UserDAO.getbyusername2(Userservice.getLoggedName());  
		
		
		policies policies = flowServices.getPolicies(LoadApp.getNextstpe_id());
		
		
		LocalDateTime localDate = LocalDateTime.now();//For reference
	   	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	   	String date = localDate.format(formatter);
		
		List<doc> docs = EdataDAO.getDoc(LoadApp.getEdata().getData_id());

		
		String Respon = policies.getRespon();
        if(policies.getRespon().equals("Creator")) {
        	Respon =  "user_"+users.getUser_id();
        }
		
	   	
		transaction_send transaction_sends = new transaction_send();
		transaction_sends.setSend_to(Respon);
		transaction_sends.setSend_type(LoadApp.getWorkflow().getWorkflow_maindb());
		transaction_sends.setReceiver_type(Receiver_type);
		transaction_sends.setCreated_by(users.getUser_id());
		transaction_sends.setCreated_at(date);
		transaction_sends.setStatus("Send");
		transaction_sends.setComment("-");
		transaction_sends.setProcess_id(LoadApp.getProcess().getId());
		transaction_sends.setEdata_id(LoadApp.getEdata().getData_id());
		transaction_sends.setData_nameadd(users.getUser_name());
		
		
		
		if(docs.size() > 0) {
			String rv ="";
			if(docs.get(0).getDoc_rv() > 0) { rv ="-R"+docs.get(0).getDoc_rv();  }
			transaction_sends.setData_value1(docs.get(0).getDoc_type()+docs.get(0).getDoc_date()+docs.get(0).getDoc_autoid()+rv);
		}
		
		transaction_sends.setData_value2(LoadApp.getEdata().getData_title());
		int  Letter = EdataDAO.save_transaction_send(transaction_sends);
		
		
		return Letter;
		
	}

	private void Transaction_procurement_send(LoadApp LoadApp, int letter) {
		
		// TODO Auto-generated method stub
		String type = "Create";
		List<edata_procurement> Clear =  EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId(), "Clear");
		if(Clear.size() > 0) {
			type = "Clear";
		}
		 EdataDAO.SendEdata_procurements_item(LoadApp.getTempId(),type);
	}







	
}
