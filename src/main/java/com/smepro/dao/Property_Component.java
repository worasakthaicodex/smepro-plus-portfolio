package com.smepro.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.smepro.entity.assets;
import com.smepro.entity.company_annual_plan;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.entity.workrule;
import com.smepro.enums.AppAbility;
import com.smepro.enums.Step;
import com.smepro.enums.Stock_status;
import com.smepro.models.Idoc;
import com.smepro.models.InputHiddenVisibility;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.Links;
import com.smepro.models.LoadApp;
import com.smepro.models.Myapp;
import com.smepro.models.PlatformConfig;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.Tabform;
import com.smepro.models.commitment;
import com.smepro.models.dataset;
import com.smepro.models.dataset_data;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.eformfield;
import com.smepro.models.keymap;
import com.smepro.models.process;
import com.smepro.models.registerkey;
import com.smepro.models.stock;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.models.workflow;
import com.smepro.repository.AssetsRepository;
import com.smepro.repository.Company_annual_planRepository;
import com.smepro.repository.Edata_annual_planRepository;
import com.smepro.repository.Edata_assetsRepository;
import com.smepro.repository.Edata_workruleRepository;
import com.smepro.repository.ProductRepository;
import com.smepro.repository.RegisterRepository;
import com.smepro.repository.StockRepository;
import com.smepro.repository.WorkruleRepository;
import com.smepro.service.AppComponent;
import com.smepro.service.FlowServices;
import com.smepro.service.userservice;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.dto.SourceDTO;
import  com.smepro.entity.Register_list;
import  com.smepro.entity.Register_stock;
import  com.smepro.entity.Register_product;
@Component
public class Property_Component {

	
	
	
	private edataDAO EdataDAO;
	private FlowServices flowServices;
	private userDAO UserDAO;
	private upload_pnDAO Upload_pnDAO;
	private userservice Userservice;
	private conditionDAO ConditionDAO;
	private datasetDAO DatasetDAO;
	private dataset_dataDAO Dataset_dataDAO;
	private workflowDAO WorkflowDAO;
	private Edata_assetsRepository Edata_assets;
	private Edata_annual_planRepository Edata_annual_plan;
	private Edata_workruleRepository Edata_workrule;
	
	
	private AssetsRepository AssetsRepository;
	private Company_annual_planRepository Annual_planRepository;
	private WorkruleRepository WorkruleRepository;
	

	private StockRepository StockRepository;
	private RegisterRepository RegisterRepository;
	private ProductRepository ProductRepository;
	private AppComponent appComponent;
	private eformfieldDAO EformfieldDAO;
	
	
	private State States = State.NONE;
	

	public enum State {
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
	    		workflowDAO WorkflowDAO,
	    		Edata_assetsRepository Edata_assets,
	    		Edata_annual_planRepository Edata_annual_plan,
	    		Edata_workruleRepository Edata_workrule,
	    		
	    		AssetsRepository AssetsRepository,
	    		Company_annual_planRepository Annual_planRepository,
	    		WorkruleRepository WorkruleRepository,
	    		
	    		StockRepository StockRepository,
	    		RegisterRepository RegisterRepository,
	    		ProductRepository ProductRepository,
	    		AppComponent appComponent,
	    		eformfieldDAO EformfieldDAO
	    		
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
	        this.Edata_annual_plan = Edata_annual_plan;
	        this.Edata_workrule = Edata_workrule;
	        
	        this.AssetsRepository = AssetsRepository;
	        this.Annual_planRepository = Annual_planRepository;
	        this.WorkruleRepository = WorkruleRepository;
	        this.StockRepository = StockRepository;
	        this.RegisterRepository = RegisterRepository;
	        this.ProductRepository = ProductRepository;
	        this.appComponent = appComponent;
	        this.EformfieldDAO = EformfieldDAO;
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
	
	private  Map<String, Object> modelData ;
	LoadApp LoadApp = new LoadApp();
	private int step;
	
	
	public Property_Component initiate(LoadApp loadApp, Map<String, Object> modelData) {
		this.modelData = modelData;
		this.LoadApp = loadApp;
		this.step = 0;
		return this;
	}

	

	public Property_Component action() {
		step = 1;
		
		System.out.println("Property_Component "+this.LoadApp.getMap());
		
		
		if(this.LoadApp.getMap() != null) { 
			
			System.out.println("Property_Component Production");
			
			  Myapp app =  EdataDAO.getMyapp("Production");
			  PrepareKeymap(this.LoadApp.getMap(),app,this.LoadApp); 
		
		
		}
		return this;
	}

	public Property_Component source() {
		step = 2;
		return this;
	}

	public Property_Component workflow() {
		step = 3;
		return this;
	}

	public Property_Component display() {
		step = 4;
		
		this.LoadApp.getSub_processes().setAccepting(this.LoadApp.getConfig().getAccepting());
		this.LoadApp.getSub_processes().setProhibited1(this.LoadApp.getConfig().getProhibited1());
		this.LoadApp.getSub_processes().setProhibited2(this.LoadApp.getConfig().getProhibited2());
		this.LoadApp.getSub_processes().setVisibility(this.LoadApp.getConfig().getVisibility());
		
		if(this.LoadApp.getConfig().getActivate_autoid() != null) {
			if(this.LoadApp.getConfig().getActivate_autoid().equals("-")  ) {
				int autoid = Integer.valueOf(this.LoadApp.getConfig().getActivate_autoid());
				this.LoadApp.getSub_processes().setActivate_autoid(autoid);
				this.LoadApp.getSub_processes().setAutoiddatacolumn(this.LoadApp.getConfig().getAutoiddatacolumn());
				this.LoadApp.getSub_processes().setCode_app(this.LoadApp.getConfig().getCode_app());
			}
		}
		
		
		this.LoadApp.getProcess().setEnums(this.LoadApp.getConfig().getEnums());
		
		return this;
	}
	
	public Property_Component tabForm() {
		step = 5;
		return this;
	}
	public void saveInputHiddenVisibility() {
		 EdataDAO.saveInputHiddenVisibility(this.LoadApp.getPlatformUserSettings().getInputHiddenVisibility());
	}
	public void deleteInputHiddenVisibility() {
		 EdataDAO.RemoveInputHiddenVisibilityById(this.LoadApp.getPlatformUserSettings().getRemove_id());
	}
	
	public Property_Component tabFormItem() {
		step = 5;
		return this;
	}
	public Property_Component inputHiddenVisibilityItem() {
		step = 6;
		return this;
	}
	
	
	
	
	public Property_Component useTabForm() {
		step =6;
		  this.LoadApp.getProcess().setTabform(this.LoadApp.getPlatformUserSettings().getAction_id());
		return this;
	}

	
	public Property_Component useValid() {
		step =6;
		  this.LoadApp.getProcess().setUsed_valid(this.LoadApp.getPlatformUserSettings().getAction_id());
		return this;
	}
	public Property_Component useAttachment() {
		step =6;
		  this.LoadApp.getProcess().setUsed_attachment(this.LoadApp.getPlatformUserSettings().getAction_id());
		return this;
	}
	
	
	
	
	public void saveClosing_status() {
			this.LoadApp.getProcess().setClosing_status(this.LoadApp.getConfig().getConfig());
			
			this.LoadApp.getProcess().setClosing_status_expired(this.LoadApp.getConfig().getClosing_status_expired());
			this.LoadApp.getProcess().setExpires_in_days(this.LoadApp.getConfig().getExpires_in_days());
			this.LoadApp.getProcess().setIs_acknowledge_required(this.LoadApp.getConfig().getIs_acknowledge_required());
			
	
			this.LoadApp.getProcess().setCan_refuse_and_return(this.LoadApp.getConfig().getCan_refuse_and_return());
			this.LoadApp.getProcess().setIs_attachment_required_to_close(this.LoadApp.getConfig().getIs_attachment_required_to_close());
			this.LoadApp.getProcess().setClosing_evidence_type(this.LoadApp.getConfig().getClosing_evidence_type());
			this.LoadApp.getProcess().setSub_process_expired(this.LoadApp.getConfig().getSub_process_expired());
		
			
			
			
			EdataDAO.editClosing_status(this.LoadApp.getProcess());
	}


	public void saveAddconfigDataset() {
		EformfieldDAO.editAddconfigDataset(this.LoadApp.getConfig().getConfig(),this.LoadApp.getConfig().getConfig_id(),this.LoadApp.getProcess().getId());
	}
	public void saveAddDataAccessInterface() {
		EformfieldDAO.editAddconfigData_access_interface(this.LoadApp.getConfig().getConfig(),this.LoadApp.getConfig().getConfig_id(),this.LoadApp.getProcess().getId());
	}

	
	
	
	public void load() {
		
		if(step == 1) {
			
			
			
			
			  Myapp app =  EdataDAO.getMyapp("Production");
			 List<registerkey> registerkeys =  EdataDAO.getAllRegisterkey(app.getId(),"register"); 
			 List<registerkey> actionkeys =  EdataDAO.getAllRegisterkey(app.getId(),"action"); 
			 List<keymap> keymap = EdataDAO.getAllKeyMap(this.LoadApp.getSub_processes().getId());
			 prepare(registerkeys,keymap);
			 Requestaction(actionkeys,app,keymap);
			 register(registerkeys,app,keymap);
			 
		
			 Optional<keymap> data  = keymap.stream().findFirst();
			
			 
			 
			 
		
			 
			 
			 if(data.isPresent()) {
				 keymap keymaps = data.get();
				 registerkey Registerkey = EdataDAO.getRegisterkey(keymaps.getRegisterkey_id());
				 String appmodel = appComponent.getAppModel(Registerkey,keymaps);
				 enums(appmodel);
			 }
			 
			 
		}
		else if(step == 2) {
			
			  Myapp app =  EdataDAO.getMyapp("Production");
			  List<registerkey> registerkeys =  EdataDAO.getAllRegisterkey(app.getId(),"register"); 
			  List<keymap> keymap = EdataDAO.getAllKeyMap(this.LoadApp.getSub_processes().getId());
			  List<commitment>  commitment =  register(registerkeys,app,keymap);
			  Preparesource(commitment,app);
		}
		// TODO Auto-generated method stub
		else if(step == 3) {
			
			 List<process> process =  EdataDAO.getAllProcessBytype(this.LoadApp.getWorkflow().getWorkflow_id(),0);
			 process.stream().forEach(row->{
				 List<sub_processes> sub_processes =  EdataDAO.getAllSubProcess(row.getId());
				 row.setSub_processes(sub_processes);
				 

				List<Row_calculation> listRowcalculation = WorkflowDAO.getRow_calculationByidrefer(row.getId());
				row.setRowcalculation(listRowcalculation);
				 
				List<Footer_calculation> listFootercalculation = WorkflowDAO.getFooter_calculationByidrefer(row.getId());
				row.setFootercalculation(listFootercalculation); 
				
				List<Links> listLinks = WorkflowDAO.getLinksByidrefer(row.getId());
				row.setLinks(listLinks); 
				
				List<Links> listInLinks = WorkflowDAO.getLinksBylink_to(row.getId());
				
				
				listInLinks.stream().forEach(rows->{
					List<Rollup_calculation> listRollupcalculation = WorkflowDAO.getRollup_calculationByidrefer(rows.getId());
					rows.setRollup_calculation(listRollupcalculation);
					
				});
				
				
				row.setInlinks(listInLinks);
				
				
				
			 });
			 this.modelData.put("process", process);
			 
			 
			 List<Step> Steptype = Arrays.asList(Step.values());
			this.modelData.put("Steptype", Steptype);
			 
		}
		
		else if(step == 5) {
			  Tabform TabFormData =  EdataDAO.getTabFormById(this.LoadApp.getPlatformUserSettings().getAction_id());
	
			 this.modelData.put("TabFormData", TabFormData);
		}

		else if(step == 6) {
			InputHiddenVisibility InputHiddenVisibilityData =  EdataDAO.getInputHiddenVisibilityById(this.LoadApp.getPlatformUserSettings().getAction_id());
	
			 this.modelData.put("InputHiddenVisibilityData", InputHiddenVisibilityData);
		}

		// TODO Auto-generated method stub
	}




	public boolean delete() {
		if(step == 5) {
			
			 EdataDAO.RemoveTabFormById(this.LoadApp.getPlatformUserSettings().getRemove_id());
		}
		return false;
	}
	 

	public boolean save() {

			this.LoadApp.getSub_processes().setAction_batch_insert(this.LoadApp.getConfig().getAction_batch_insert());
			
			if (step == 1) {
			    // เซ็ตค่าแค่รอบเดียวพอครับ
			    this.LoadApp.getSub_processes().setAction_batch_insert(this.LoadApp.getConfig().getAction_batch_insert());
			    
			    // ==========================================
			    // 1. ดึงแยก eformkey ของ customerCode และ productCode 
			    // ==========================================
			    keymap currentKeymap = this.LoadApp.getMap();
			    String customerEformKey = "";
			    String productEformKey = "";
			    
			    if (currentKeymap != null && currentKeymap.getMainkey() != null && currentKeymap.getEformkey() != null) {
			        String[] mainKeys = currentKeymap.getMainkey().split(",");
			        String[] eformKeys = currentKeymap.getEformkey().split(",");
			        
			        for (int i = 0; i < mainKeys.length; i++) {
			            String currentMainKey = mainKeys[i].trim();
			            
			            if ("customerCode".equalsIgnoreCase(currentMainKey) && i < eformKeys.length) {
			                try {
			                    String keyVal = eformKeys[i].trim();
			                    // ดักไม่ให้แปลงตัวเลขถ้าค่าเป็นว่างหรือขีด
			                    if (!keyVal.isEmpty() && !keyVal.equals("-")) { 
			                        eformfield field = EformfieldDAO.findById(Integer.valueOf(keyVal));
			                        if (field != null) { // ดักหาไม่เจอใน DB
			                            customerEformKey = "data_value" + field.getField_postion();
			                        }
			                    }
			                } catch (Exception e) {
			                    System.err.println("Error parsing customerCode eformkey: " + e.getMessage());
			                }
			                
			            } else if ("productCode".equalsIgnoreCase(currentMainKey) && i < eformKeys.length) {
			                try {
			                    String keyVal = eformKeys[i].trim();
			                    if (!keyVal.isEmpty() && !keyVal.equals("-")) {
			                        eformfield field = EformfieldDAO.findById(Integer.valueOf(keyVal));
			                        if (field != null) {
			                            productEformKey = "data_value" + field.getField_postion();
			                        }
			                    }
			                } catch (Exception e) {
			                    System.err.println("Error parsing productCode eformkey: " + e.getMessage());
			                }
			            }
			        }
			    }
			    
			    // เซ็ตค่าลง Process 
			    this.LoadApp.getProcess().setProfile_column(customerEformKey);
			    this.LoadApp.getProcess().setProduct_master_column(productEformKey);

			    // ==========================================
			    // 2. บันทึกลง Database
			    // ==========================================
			    EdataDAO.save_subprocess(this.LoadApp.getSub_processes());
			    EdataDAO.save_keymap(currentKeymap);
			    EdataDAO.save_process(this.LoadApp.getProcess());
			}

		else if(step == 2) {
			 Preparesource_to_Sub_processes();
			 EdataDAO.save_subprocess(this.LoadApp.getSub_processes());
		}
		else if(step == 4) {
			 EdataDAO.save_subprocess(this.LoadApp.getSub_processes());
			 EdataDAO.saveprocessinfo(this.LoadApp.getProcess());
		}
		else if(step == 5) {
			 EdataDAO.saveTabForm(this.LoadApp.getPlatformUserSettings().getTabform());
		}
		else if(step == 6) {
			  EdataDAO.save_process(this.LoadApp.getProcess());
		}
		return false;
	}
	 
	




	private void PrepareKeymap(keymap map, Myapp app,LoadApp loadApp) {
	
		
		System.out.println(" map ***************> ");
		
		//activate_register
		if(map.getRegisterkey_id() == null) { return;}

		String action = "register";
		if(!loadApp.getConfig().getActivate_register().equals("-")) { action = "register"; }
		else if(!loadApp.getConfig().getActivate_action().equals("-")) { action = "action"; }
	

		 List<registerkey> registerkeys =  EdataDAO.getAllRegisterkey(app.getId(),action); 
		 String[] Registerkey = map.getRegisterkey_id().split(",");

		 
		 StringBuilder   BuliderMainkey = new StringBuilder();
		 StringBuilder   BuliderChange_format = new StringBuilder();
		 StringBuilder   BuliderFilter_enum = new StringBuilder();
		 StringBuilder   BuliderFilter_enumid = new StringBuilder();
		 int i = 0;
		for(String row  : Registerkey) {
				i++;
				int id = Integer.valueOf(row);
				String spite = i== Registerkey.length ?  "": "," ;
				
				Optional<registerkey> object = registerkeys.stream().filter(rows->rows.getId() == id).findFirst();
				if(object.isPresent()) {
					registerkey key = object.get();
					BuliderMainkey.append(key.getMainkey()).append(spite);
					BuliderChange_format.append(key.getType()).append(spite);
					BuliderFilter_enum.append(key.getSource()).append(spite);
					
					
					 dataset dataset = DatasetDAO.getMain(key.getSource());
						if(dataset != null) {
							
							System.out.println(" getDataset_id ->"+dataset.getDataset_id());
							  BuliderFilter_enumid.append(dataset.getDataset_id()).append(spite);
						}else {
							 BuliderFilter_enumid.append(0).append(spite);
						}
					
					
				}else {
					BuliderMainkey.append(0).append(spite);
					BuliderChange_format.append(0).append(spite);
					BuliderFilter_enum.append(0).append(spite);
					BuliderFilter_enumid.append(0).append(spite);
				}
				System.out.println(" row ->"+row);
		}	
		map.setMainkey(BuliderMainkey.toString());
		map.setChange_format(BuliderChange_format.toString());
		map.setFilter_enum(BuliderFilter_enum.toString());
		map.setEnum_id(BuliderFilter_enumid.toString());
		System.out.println(" map "+map.toString());
		
	}


	
	
	private List<commitment>  register(List<registerkey> registers, Myapp app, List<keymap> keymap) {
		try { 
			String[] register = app.getRegister().split(",");
			List<commitment> Registers = new ArrayList<commitment>();
			
		  
		
			
			
			for(String  datas: register) {
				
				commitment commit = new commitment();
				
				List<registerkey> key = registers.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
				

			
				
				for(registerkey row : key) {
					//System.out.println(" key-> "+row.getAction_type());
					
					 Optional<keymap> data =  keymap.stream()
							 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
							 .filter(sub-> sub.getType().equals("create"))
							 .findFirst();
						if(data.isPresent()) {
						
							
							 keymap  keymaps = data.get();
							 row.setKeymap(keymaps);
							System.out.print(" key->data "+keymaps.getEformkey());
							
						
						}	 
					
					 dataset dataset = DatasetDAO.getMain(row.getSource());
					if(dataset != null) {
						 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
						  row.setTag(dataset_data);
						  row.setEnum_id(dataset.getDataset_id());
					}
				
				 }
				 System.out.println(" commit  Name "+datas);
				 System.out.println(" commit Actionkeys "+key);
				 commit.setName(datas);
				 commit.setActionkeys(key);
				 Registers.add(commit);
			}

			

			this.modelData.put("Register", app.getRegister());
			this.modelData.put("Registers",Registers);
			this.modelData.put("registerkey_id",registers.get(0).getId());
		     
			return  Registers;
	}catch(Exception e) {
		 System.out.println(e);
	}
		return null;  
		
	}



	private void Requestaction(List<registerkey> actionkeys, Myapp app, List<keymap> keymap) {
	
			String[] commitment = app.getCommitment().split(",");
			List<commitment> commitments = new ArrayList<commitment>();
			for(String  datas: commitment) {
				
				commitment commit = new commitment();
				
				List<registerkey> key = actionkeys.stream().filter(row->row.getDoc().equals(datas)).collect(Collectors.toList());
				
				
				key.forEach(row->{

					 dataset dataset = DatasetDAO.getMain(row.getSource());
					if(dataset != null) {
						 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
						 Optional<keymap> data =  keymap.stream()
								 .filter(sub-> sub.getMainkey().equals(row.getMainkey()))
								 .filter(sub-> sub.getType().equals("create"))
								 .findFirst();
						if(data.isPresent()) {
							 keymap  keymaps = data.get();
							 row.setKeymap(keymaps);
						}	 
						  row.setTag(dataset_data);
						  row.setEnum_id(dataset.getDataset_id());
					}
				
				 });
				 
				 commit.setName(datas);
				 commit.setActionkeys(key);
				 commitments.add(commit);
			}
			
			

			 this.modelData.put("commitment",app.getCommitment());
			 this.modelData.put("commitments",commitments);
			 
			 
				try { 		}catch(Exception e) {
		System.out.println(" Exception "+e);
			} 
		
	}



	private void prepare(List<registerkey> registerkeys, List<keymap> keymap) {
		 registerkeys.stream().forEach(row->{
			 dataset dataset = DatasetDAO.getMain(row.getSource());
			if(dataset != null) {
				 List<dataset_data> dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
				 Optional<keymap> data =  keymap.stream().filter(sub-> sub.getMainkey().equals(row.getMainkey())).findFirst();
				if(data.isPresent()) {
					 keymap  keymaps = data.get();
					 row.setKeymap(keymaps);
				}	 
				  row.setTag(dataset_data);
				  row.setEnum_id(dataset.getDataset_id());
			}
		
		 });
		 
	}



	private void Preparesource(List<commitment> commitment, Myapp app) {
		
		String[] source_register = app.getRegister().split(",");
		List<String> source_registers = new ArrayList<String>( Arrays.asList(source_register));
		String[] source_enum_type = app.getEnum_type().split(",");
		
		List<SourceDTO> source = new ArrayList<SourceDTO>();
		
	
		int e = 0;
	
			for (String row : source_registers) {
				
				String enum_type =  source_enum_type[e];
				dataset app_dataset = DatasetDAO.getMain(enum_type); 
				List<dataset_data> app_dataset_data = Dataset_dataDAO.getall(app_dataset.getDataset_id());
				
				if(app_dataset_data.size() > 0) {
				SourceDTO Source_data = new SourceDTO(e,row,app_dataset_data);
				source.add(Source_data);
				}else {
					SourceDTO Source_data = new SourceDTO(e,row,null);
					source.add(Source_data);
					
				}
				
				e++;
				
			
			}

		// TODO Auto-generated method stub enum_type
		
			 this.modelData.put("source",source);		
			
	}


		private void Preparesource_to_Sub_processes() {
			
			PlatformConfig Config =  this.LoadApp.getConfig();
			
			int activate_subdata = 0;
			
			
			System.out.println(" Config "+Config);
			
			
			if(Config.getDatacolumn1() != null) {		
				this.LoadApp.getSub_processes().setMaindatacolumn(Config.getDatacolumn1());
				this.LoadApp.getSub_processes().setFiltermain(Config.getFilter1());
				this.LoadApp.getSub_processes().setInput_db1(Config.getInput_db1());
				
				activate_subdata = 1;
			}
			
			if(Config.getDatacolumn2() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn(Config.getDatacolumn2());
				this.LoadApp.getSub_processes().setFiltersub(Config.getFilter2());
				this.LoadApp.getSub_processes().setInput_db2(Config.getInput_db2());
				
				activate_subdata = 1;
			}
			
			if(Config.getDatacolumn3() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn1(Config.getDatacolumn3());
				this.LoadApp.getSub_processes().setFiltersub1(Config.getFilter3());
				this.LoadApp.getSub_processes().setInput_db3(Config.getInput_db3());
				
				activate_subdata = 1;
			}
			
			if(Config.getDatacolumn4() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn2(Config.getDatacolumn4());
				this.LoadApp.getSub_processes().setFiltersub2(Config.getFilter4());
				this.LoadApp.getSub_processes().setInput_db4(Config.getInput_db4());
				
				activate_subdata = 1;
			}
			
			if(Config.getDatacolumn5() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn3(Config.getDatacolumn5());
				this.LoadApp.getSub_processes().setFiltersub3(Config.getFilter5());
				this.LoadApp.getSub_processes().setInput_db5(Config.getInput_db5());
				
				activate_subdata = 1;
			}
			
			if(Config.getDatacolumn6() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn4(Config.getDatacolumn6());
				this.LoadApp.getSub_processes().setFiltersub4(Config.getFilter6());
				this.LoadApp.getSub_processes().setInput_db6(Config.getInput_db6());
				
				activate_subdata = 1;
			}

			if(Config.getDatacolumn7() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn5(Config.getDatacolumn7());
				this.LoadApp.getSub_processes().setFiltersub5(Config.getFilter7());
				this.LoadApp.getSub_processes().setInput_db7(Config.getInput_db7());
				
				activate_subdata = 1;
			}

			if(Config.getDatacolumn8() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn6(Config.getDatacolumn8());
				this.LoadApp.getSub_processes().setFiltersub6(Config.getFilter8());
				this.LoadApp.getSub_processes().setInput_db8(Config.getInput_db8());
				
				activate_subdata = 1;
			}
			
			if(Config.getDatacolumn9() != null) {		
				this.LoadApp.getSub_processes().setSubdatacolumn7(Config.getDatacolumn9());
				this.LoadApp.getSub_processes().setFiltersub7(Config.getFilter9());
				this.LoadApp.getSub_processes().setInput_db9(Config.getInput_db9());
				
				activate_subdata = 1;
			}
			
			this.LoadApp.getSub_processes().setActivate_subdata(activate_subdata);
			
		}



		private void enums(String appmodel) {
			// TODO Auto-generated method stub
			  switch (appmodel) {
	            case "edata_registers." -> getEnums("Register_Type");
			  }
		}



		private void getEnums(String Type) {
			 dataset dataset = DatasetDAO.getMain(Type);
			 List<dataset_data> app_dataset_data = Dataset_dataDAO.getall(dataset.getDataset_id());
			 this.modelData.put("enums",app_dataset_data);
		}



		public void saveConfigSubTable() {
			if (this.LoadApp.getPlatformUserSettings().getProcess() == null) { return; }
			EdataDAO.saveConfigSubTable(this.LoadApp.getPlatformUserSettings().getProcess());
		}



		public void saveConfigRequestRevision() {
			// TODO Auto-generated method stub
			EdataDAO.saveConfigRequestRevision(this.LoadApp.getConfig().getProcess());
		}
		public void saveRenamefile() {
			// TODO Auto-generated method stub
			EdataDAO.saveConfigRenamefile(this.LoadApp.getConfig().getProcess());
		}



		public void saveSegmentationView() {
			// TODO Auto-generated method stub
			EdataDAO.saveSegmentationView(this.LoadApp.getConfig().getProcess().getSetSegmentView());
		}








	
}
