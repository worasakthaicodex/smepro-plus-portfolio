package com.smepro.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.smepro.dao.App_Component;
import com.smepro.dao.ApproveApp_Component;
import com.smepro.dao.Finance_Accounting;
import com.smepro.dao.ForwardApp_Component;
import com.smepro.dao.HR_Workforce;
import com.smepro.dao.LoadApp_Component;
import com.smepro.dao.Machinery_Factory;
import com.smepro.dao.Procurement_SupplyChain;
import com.smepro.dao.Production;
import com.smepro.dao.ReceiveApp_Component;
import com.smepro.dao.RecordApp_Component;
import com.smepro.dao.Sales_Customer;
import com.smepro.dao.SaveApp_Component;
import com.smepro.dao.Stock_Warehouse;
import com.smepro.service.WorkSystem;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.fileDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.dto.MultipleModelsDTO;
import com.smepro.dto.driverDTO;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Dataset;
import com.smepro.enums.Save_status;
import com.smepro.enums.Stock_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Platform;
import com.smepro.models.SubData;
import com.smepro.models.Transition;
import com.smepro.models.WorkSystemdata;
import com.smepro.models.bom;
import com.smepro.models.condition;
import com.smepro.models.control_steps;
import com.smepro.models.datalink;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_stock;
import com.smepro.models.edatas;
import com.smepro.models.file;
import com.smepro.models.flow;
import com.smepro.models.loadmaindata;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.purchase;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.plugin.AbilityProcessor;
import com.smepro.models.keymap;

import com.smepro.service.FlowServices;
import com.smepro.service.ProgressService;
import com.smepro.service.userservice;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;




@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public  class PlatformService   {

	 private  Map<String, Object> modelData = new HashMap<>();
	 
	private  Main_Component Main_Components; 
    private  App_Component App_Components;
    private  SaveApp_Component SaveApp_Components;
    private  LoadApp_Component LoadApp_Components;
    private  ApproveApp_Component ApproveApp_Components;
    private  RecordApp_Component RecordApp_Components;
    private  ForwardApp_Component ForwardApp_Components;
    private  ReceiveApp_Component ReceiveApp_Components;

	private edataDAO EdataDAO;
	private userDAO UserDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private workflowDAO WorkflowDAO;
	private State States = State.NONE;
	private FlowServices flowService;
	private fileDAO FileDAO;
	private filemanagerservice Filemanagerservice;

	
	
	
	
	
	LoadApp LoadApp = new LoadApp();
	
	
	public enum State {
		  NONE,SUCCESS, INVALID_TYPE;
		
	}

	
	
	 @Autowired
	 private void setMyRepository(
	    		edataDAO EdataDAO,
	    		userDAO UserDAO,
	    		upload_pnDAO Upload_pnDAO,
	    		userservice Userservice,
	    		conditionDAO ConditionDAO,
	    	
	    		datasetDAO DatasetDAO,
	    		dataset_dataDAO Dataset_dataDAO,
	    		workflowDAO WorkflowDAO,
	    		
	    		LoadApp_Component LoadApp_Components,
	    		ApproveApp_Component ApproveApp_Components,
	    		RecordApp_Component RecordApp_Components,
	    		App_Component App_Components,
	    		SaveApp_Component SaveApp_Components,
	    		ForwardApp_Component ForwardApp_Components,
	    		ReceiveApp_Component ReceiveApp_Components,
	    		FlowServices flowService,
	    		fileDAO FileDAO,
	    		filemanagerservice Filemanagerservice,
	    		Main_Component Main_Components
	    		) { 
	
		 
		 
	        this.EdataDAO = EdataDAO;
	        this.UserDAO = UserDAO;

	        this.Userservice = Userservice;
	        this.ConditionDAO = ConditionDAO;
	        this.DatasetDAO = DatasetDAO;
	        this.WorkflowDAO = WorkflowDAO;
	        this.App_Components = App_Components;
	        this.LoadApp_Components = LoadApp_Components;
	        this.ApproveApp_Components = ApproveApp_Components;
	        this.RecordApp_Components = RecordApp_Components;
	        this.SaveApp_Components = SaveApp_Components;
	        this.ForwardApp_Components = ForwardApp_Components;
	        this.ReceiveApp_Components = ReceiveApp_Components;
	        this.flowService = flowService;
	        this.FileDAO = FileDAO;
	        this.Filemanagerservice = Filemanagerservice;
	        this.Main_Components = Main_Components;
	   
	    }

	  
		 /**
		  * Initializes and resets all related states or configurations of the {@code PlatformService}.
		  * <p>
		  * This method should be called **exactly once** at the start of the service lifecycle
		  * to ensure a clean initial state before any operations are performed.
		  * </p>
		  * <p>
		  * Calling this method multiple times is not recommended, as it is intended only
		  * for the first-time initialization (e.g., when the application starts or the service
		  * is freshly created).
		  * </p>
		 * @param loadApp 
		  *
		  * @return the current {@code PlatformService} instance after initialization
		  */
		public  Main_Component initiate(LoadApp loadApp){
			 this.modelData.clear();
			 return Main_Components.initiate(loadApp,this.modelData);
		 }
		 
		
		public Map<String, Object> getModelData() {
		        return modelData;
		    }


	}




