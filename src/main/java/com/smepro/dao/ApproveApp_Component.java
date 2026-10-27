package com.smepro.dao;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.smepro.enums.AppAbility;
import com.smepro.enums.Save_status;
import com.smepro.enums.Sys_status;
import com.smepro.models.Idoc;
import com.smepro.models.LoadApp;
import com.smepro.models.Myapp;
import com.smepro.models.batch_code;
import com.smepro.models.batchformat;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_stock;
import com.smepro.models.flow;
import com.smepro.models.keymap;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.production_list;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.service.FlowServices;
import com.smepro.service.otherservice;
import com.smepro.service.userservice;



@Component
public class ApproveApp_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
    private otherservice Otherservice;

	private Machinery_Factory Machinery_Factorys;
	private Sales_Customer Sales_Customers;
	private Finance_Accounting Finance_Accountings;
	private HR_Workforce HR_Workforces;
	private Procurement_SupplyChain Procurement_SupplyChains;
	private Production Productions;
	private Stock_Warehouse Stock_Warehouses;
  
    
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
	    		workflowDAO WorkflowDAO,
	    		otherservice Otherservice,
	    		Machinery_Factory Machinery_Factorys,
	    		Sales_Customer Sales_Customers,
	    		Finance_Accounting Finance_Accountings,
	    		HR_Workforce HR_Workforces,
	    		Procurement_SupplyChain Procurement_SupplyChains,
	    		Production Productions,
	    		Stock_Warehouse Stock_Warehouses
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
	        this.Otherservice = Otherservice;
	        this.Machinery_Factorys = Machinery_Factorys;
	        this.Sales_Customers = Sales_Customers;
	        this.Finance_Accountings = Finance_Accountings;
	        this.HR_Workforces = HR_Workforces;
	        this.Procurement_SupplyChains = Procurement_SupplyChains;
	        this.Productions = Productions;
	        this.Stock_Warehouses = Stock_Warehouses;
	    }

	
	
	
	private int id;
	private String db;
	private String subdb;
	private ModelMap model;
	private String maindata;
	private String filtermain;
	private String subdata;
	private String filtersub;
	private String maindatacolumn;
	private String autoiddatacolumn;
	private String subdatacolumn;
	private List<keymap> keymaps;
	private  Idoc docs;
	private user users;
	private sub_processes subprocesses;
	private List<String> ability = new ArrayList<String>();
	
	
	
	
	public void runMethod(Object obj ,Runnable Method) {
		
		  if(obj != null) {
			  Method.run();
		  }else {
	        System.out.println("Skip: " + Method); // หรือ log.debug("skip " + ...)
	    }
		  
	}
	

	
	public void IssueCode(LoadApp LoadApp) {
		
		
		
		sub_processes sub_processes = LoadApp.getSub_processes();
		
		System.err.println("ApproveApp->autoid "+sub_processes.getActivate_autoid());
		System.err.println("ApproveApp->ID "+sub_processes.getId());
		System.err.println("ApproveApp->Code_app "+sub_processes.getCode_app());
		
		
		
		if(sub_processes.getActivate_autoid() == 1) {
			String Code_app = sub_processes.getCode_app();
			String doc = EdataDAO.addDoc(LoadApp.getEdata().getData_id(),Code_app);
			LoadApp.getEdata().setDoc(doc);
		}
		
		
	    Optional<sub_processes> Create =  LoadApp.getProcess().getSub_processes().stream().filter(row ->row.getStep().equals("Create")).findFirst();
	    if(Create.isPresent()) {
	        sub_processes batch_gens = Create.get();
	    	if(Otherservice.haveValue(batch_gens.getBatch_code_generation()) == true 
	    			&& LoadApp.getProduction_lists() != null) { 
				System.out.println(" batch_gens ..... work");
				String genBatch = GenBatch(LoadApp.getProduction_lists(),batch_gens);
				EdataDAO.setLot_batch(genBatch,LoadApp.getProduction_lists().getId());
				LoadApp.getProduction_lists().setLot_batch(genBatch);
				LoadApp.getEdata().setData_title(LoadApp.getProduction_lists().getProduct_name()+" ["+LoadApp.getProduction_lists().getCustomer_name()+"] ["+LoadApp.getProduction_lists().getLot_batch()+"]");
			    EdataDAO.setTitle(LoadApp.getEdata().getData_id(),LoadApp.getProduction_lists().getProduct_name()+" ["+LoadApp.getProduction_lists().getCustomer_name()+"] ["+LoadApp.getProduction_lists().getLot_batch()+"]");
			}
			
	    }
		
	}



	public void Flow(LoadApp LoadApp) {
		
		Idoc flow =  flowServices.getDocument(LoadApp.getEdata().getData_id());
		
		List<sub_processes> Sub_processes = LoadApp.getProcess().getSub_processes();
		
		System.out.println(" Sub_processes ..........."+Sub_processes.size() );
		System.out.println(" Current_step_int ..........."+flow.getCurrent_step_int());

		if(Sub_processes.size() <= flow.getCurrent_step_int()) {return;}
		
		sub_processes Sub_processes_step = LoadApp.getProcess().getSub_processes().get(flow.getCurrent_step_int()+1);
		System.out.println(" Current_step_int ..........."+Sub_processes_step.getStep());
		
		
		
					switch (Sub_processes_step.getStep()) {
					case "Receive":
						AutoFlow(LoadApp,Sub_processes_step);
						 break;
					case "Forward":
						AutoFlow(LoadApp,Sub_processes_step);
						 break;
					case "Record":
						  AutoFlow(LoadApp,Sub_processes_step);
						break;
					case "Completion":
						  AutoFlow(LoadApp,Sub_processes_step);	  
						break;
			        default:
			        	 ManualFlow(LoadApp,Sub_processes_step);
			        	 
			
			      }	
	
	
	}
	
	
	public void BackFlow(LoadApp LoadApp) {
		
		System.err.println(" ---> BackFlow");
		
		
		Idoc flow =  flowServices.getDocument(LoadApp.getEdata().getData_id());
		flow log_flow =  WorkflowDAO.getLastFlow(LoadApp.getEdata().getData_id());
		
		
		
		
		List<sub_processes> subProcesses = LoadApp.getProcess().getSub_processes();
		
		OptionalInt indexOpt = IntStream.range(0, subProcesses.size())
		        .filter(i -> subProcesses.get(i).getStep().equals(log_flow.getFlow_step()))
		        .findFirst();
		
		if(indexOpt.isPresent()) {
			 int index = indexOpt.getAsInt();
			System.err.println(" index---> "+index);

				flowServices.backtransitionDocument(
						LoadApp.getEdata().getData_id()
						,LoadApp.getTransition().getTransition_from()
						,log_flow.getFlow_send()
						,log_flow.getFlow_step()
						,index
						);
				
			    	LogFlow(LoadApp,log_flow.getFlow_send()); 
		}

		}

	
	
	private void AutoFlow(LoadApp LoadApp,sub_processes sub_processes_step) {
		
		
		flowServices.transitionDocumentAuto(LoadApp.getEdata().getData_id(),sub_processes_step.getStep());

		System.out.println( " Step Sub_processes  "+LoadApp.getSub_processes().getStep());
		
			if(LoadApp.getSub_processes().getStep().equals("Approval")) {
				LogFlow(LoadApp,0);
				LoadApp.setSub_processes(sub_processes_step);
			}
			
		
	}
	

			private void ManualFlow(LoadApp loadApp, sub_processes currentStep) {
			    int flowNo = 0;
			    int stepId = 0;
			    double maxApproval = 0.0;
			    String activateAction = "-";
			
			    policies currentPolicy = flowServices.getPolicies(currentStep.getId());
			    List<sub_processes> allSubProcesses = loadApp.getProcess().getSub_processes();
			
			    // หา Activate Action จากขั้นตอน "Create"
			    Optional<sub_processes> createStep = allSubProcesses.stream()
			            .filter(row -> "Create".equals(row.getStep()))
			            .findFirst();
			    if (createStep.isPresent() && createStep.get().getAction() != null) {
			        activateAction = createStep.get().getAction();
			    }
			
			    Myapp app = loadApp.getMyapp();
			    System.out.println(" ----------------------------------  ");
			    System.out.println(" Activate_action --> " + activateAction);
			    System.out.println(" app limit --> " + app.getApproval_limit_type());
			    System.out.println(" ----------------------------------  ");
			
			    // แปลง max_approval_amount เป็น Double หากเป็นไปได้
			    System.out.println("getMax_approval_amount load...");
			    maxApproval = Otherservice.DoubleValueOf(currentPolicy.getMax_approval_amount());
			
			    // ตรวจสอบเงื่อนไขการอนุมัติ
			    if (!"-".equals(activateAction)
			            && app.getApproval_limit_type() == 1
			            && maxApproval > 0.0
			            && "Approval".equals(currentStep.getStep())) {
			
			        double grandTotal = getGrandtotal(loadApp);
			        if (maxApproval >= grandTotal) {
			            System.out.println("Flow pass");
			            Pair<Integer, Integer> result = Get_Flow_No(loadApp, currentStep.getId());
			            stepId = result.getLeft();
			            flowNo = result.getRight();
			        }
			    }
			
			    if (flowNo > 0) {
			        // กรณี flow ผ่านไปยังขั้นตอนใหม่
			        handleApprovedFlow(loadApp, stepId, flowNo);
			    } else {
			        // ไม่เข้าเงื่อนไข flow เฉพาะ
			        handleNormalFlow(loadApp, currentStep, currentPolicy);
			    }
			}

			
			
			
			
			
			  public void LogFlow(LoadApp LoadApp, int to) {
				
				System.out.println(" to  "+to);
				System.out.println(" from  "+LoadApp.getTransition().getTransition_from());
				

			 	 LocalDateTime localDate = LocalDateTime.now();//For reference
			   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			   	 String date = localDate.format(formatter);
		        
				flow flow =new flow();
				flow.setEdata_id(LoadApp.getEdata().getData_id());
				
				if(LoadApp.getApprove() != null) {
					flow.setFlow_comment(LoadApp.getApprove().getApprove_note());
					flow.setFlow_type(LoadApp.getApprove().getActiontype());
				}else {
					flow.setFlow_type("Flow");
				
					
				}
				flow.setFlow_send(LoadApp.getTransition().getTransition_from());
				flow.setFlow_step(LoadApp.getSub_processes().getStep());
				flow.setFlow_to(to);

				flow.setFlow_date(date);
				WorkflowDAO.Saveflow(flow);
				System.out.println(" LogFlow End  ");
				
			}

			  public void LogFlow(LoadApp LoadApp) {
				  
					 	 LocalDateTime localDate = LocalDateTime.now();//For reference
					   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
					   	 String date = localDate.format(formatter);
					
						flow flow = new flow();
						flow.setEdata_id(LoadApp.getEdata().getData_id());
						flow.setFlow_comment("-");
						flow.setFlow_date(date);
						flow.setFlow_send(LoadApp.getTransition().getTransition_from());
						flow.setFlow_step(LoadApp.getSub_processes().getStep());
						flow.setFlow_to(LoadApp.getTransition().getTransition_to());
						flow.setFlow_type("Flow");
						WorkflowDAO.Saveflow(flow);
				}  
			  
	  

	private Pair<Integer, Integer> Get_Flow_No(LoadApp LoadApp, int nextstpe_id) {
	    int i = 0;
	    sub_processes sub_processes = EdataDAO.GetNextFlow(nextstpe_id);
	    
	    System.out.println("sub_processes step == "+sub_processes.getStep());
	    
	    if (sub_processes != null) {
	        List<sub_processes> allsub_processes = LoadApp.getProcess().getSub_processes();
	        for (sub_processes data : allsub_processes) {
	          
	            if (data.getId() == sub_processes.getId()) {
	                return Pair.of(data.getId(), i);
	            }
	            i++;
	        }
	    }
	    return Pair.of(-1, i); // กรณีไม่พบค่า
	}



		private double getGrandtotal(LoadApp LoadApp) {
		
			
		try {	
			workflow workflow =  LoadApp.getWorkflow();
			switch (workflow.getWorkflow_maindb()) {
					      
						case "Stock_Warehouse":
							return 0.0;         
						case "Procurement_SupplyChain":
							double	Grandtotal = 0.0;  
							
							if( LoadApp.getEdata_procurements().getProcurement_grandtotal2() > 0) {
								Grandtotal =LoadApp.getEdata_procurements().getProcurement_grandtotal2();
							}else {
								Grandtotal =LoadApp.getEdata_procurements().getProcurement_grandtotal();
							}
							return Grandtotal;      
							
				        case "Production":
				        	return 0.0;  
				        default:
				        	return 0.0;
	        	
	    }	
		}catch(Exception e) {
			System.err.println("Exception : getGrandtotal ");
			return 0.0;
		}	

	
	}

		public <T> void ApproveBatchData(LoadApp LoadApp) {
			EdataDAO.APPEdata_procurements_item(LoadApp.getEdata().getData_id());
          
		}
	
		
		public <T> void ApproveData(LoadApp LoadApp) {
			
			System.out.println(" Edata "+LoadApp.getEdata().getData_id());			
			workflow workflow =  LoadApp.getWorkflow();
			switch (workflow.getWorkflow_maindb()) {
	      
			case "Stock_Warehouse":
				Stock_Warehouses.ApproveData(LoadApp);
	            break;    
			case "Procurement_SupplyChain":
				 Procurement_SupplyChains.ApproveData(LoadApp);
	            break;    
	        case "Production":
	        	Productions.ApproveData(LoadApp);
	            break;   
	        case "Machinery_Factory":
	        	Machinery_Factorys.ApproveData(LoadApp);
	            break; 
	        case "Sales_Customer":
	        	Sales_Customers.ApproveData(LoadApp);
	            break; 
	        case "Finance_Accounting":
	        	Finance_Accountings.ApproveData(LoadApp);
	            break; 
	        case "HR_Workforce":
	        	HR_Workforces.ApproveData(LoadApp);
	            break; 
	        default:
	    }	
			
		
				EdataDAO.setProcess_id(LoadApp.getEdata().getData_id(),LoadApp.getProcess().getId());
			
			
		}
	

		private String GenBatch(production_list production_list, sub_processes batch_gen) {
			
			  String batchCode = null;
	
			  if(production_list.getLot_batch().contains("Rename ->")) { production_list.setLot_batch("Auto-generated");}
			 
		
			  System.out.println(" Prefix "+batch_gen.getPrefix());
			  System.out.println(" Pattern "+batch_gen.getBatch_code_generation());
			  

			   	 DateTimeFormatter formattered = DateTimeFormatter.ofPattern("yyMMdd");
			     LocalDateTime localDate = LocalDateTime.now();
			   	 String date = localDate.format(formattered);
			  
			  
			   	Map<String, String> vars = new HashMap<>();
			   	vars.put("prefix", batch_gen.getPrefix());
			   	vars.put("product_code", production_list.getProduct_code());
			   	vars.put("date", date);  // สมมติเตรียมไว้แล้ว

			   	batchCode = generateBatchCode(batch_gen.getBatch_code_generation(), vars);
			   	 
		 return batchCode;
	   }

		
		
		
		public String generateBatchCode(String codeType, Map<String, String> variables) {
		    batchformat format = WorkflowDAO.getFormatByCode(codeType);
		    if (format == null) return "Error: Unknown code type";

		  
		    String autoIdKey = "-"; // default
		    String field = format.getAutoid_fields();

		    if (field != null && !field.trim().equals("-")) {
		        field = field.trim();
		        String value = variables.get(field);
		        if (Otherservice.haveValue(value)) {
		            autoIdKey = value;
		        }
		    }

		    // ✅ เรียก GenBatch และเพิ่ม auto_id ลงในตัวแปร
		    batch_code batchCodes = WorkflowDAO.GenBatch(autoIdKey);
		    variables.put("auto_id", String.valueOf(batchCodes.getAuto_id()));

		    // ✅ แทนค่าจาก format_string
		    String result = format.getFormat_string();
		    for (Map.Entry<String, String> entry : variables.entrySet()) {
		        result = result.replace("{" + entry.getKey() + "}", entry.getValue());
		    }

		    // ✅ ตรวจสอบค่าว่างที่ยังไม่ถูกแทน
		    if (result.contains("{")) {
		    	return "Error : Auto-generated";
		    }

		    System.out.println(" Auto-generated result "+result);
		    
		    
		    return result;
		}
		
		
		
		private void handleApprovedFlow(LoadApp loadApp, int stepId, int flowNo) {
		    policies newPolicy = flowServices.getPolicies(stepId);
		    sub_processes nextStep = EdataDAO.getSubProcess(stepId);

		    System.out.println(" policies: " + newPolicy.getName());
		    System.out.println(" Step_id: " + stepId);
		    System.out.println(" Data_id: " + loadApp.getEdata().getData_id());
		    System.out.println(" Flow_no: " + flowNo);
		    System.out.println(" sub_processes_load: " + nextStep.getId());

		    int to = resolveResponsibleUser(newPolicy, loadApp.getEdata().getData_id());

		    flowServices.transitionDocument(
		            loadApp.getEdata().getData_id(),
		            loadApp.getTransition().getTransition_from(),
		            to,
		            nextStep.getStep(),
		            flowNo
		    );

		    LogFlow(loadApp, to);
		}

		private void handleNormalFlow(LoadApp loadApp, sub_processes currentStep, policies policy) {
		    System.out.println("Flow else");

		    int to = resolveResponsibleUser(policy, loadApp.getEdata().getData_id());

		    flowServices.transitionDocument(
		            loadApp.getEdata().getData_id(),
		            loadApp.getTransition().getTransition_from(),
		            to,
		            currentStep.getStep(),
		            0
		    );

		    LogFlow(loadApp, to);
		}

		private int resolveResponsibleUser(policies policy, int dataId) {
		    if ("Creator".equals(policy.getRespon())) {
		        Idoc flow = flowServices.getDocument(dataId);
		        return flow != null ? flow.getCreator() : 0;
		    } else {
		        return Integer.parseInt(policy.getRespon().replace("user_", "").trim());
		    }
		}
		
		

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}









	
}
