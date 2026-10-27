package com.smepro.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.smepro.enums.Stock_status;
import com.smepro.models.Idoc;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.LoadApp;
import com.smepro.models.doc;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_register;
import com.smepro.models.edata_stock;
import com.smepro.models.keymap;
import com.smepro.models.process;
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
import com.smepro.service.FlowServices;
import com.smepro.service.userservice;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Inventory_transfer_sk;

import  com.smepro.entity.Register_list;
import  com.smepro.entity.Register_stock;
import  com.smepro.entity.Register_product;
@Component
public class RecordApp_Component {

	
	
	
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
	    		ProductRepository ProductRepository
	    		
	    		
	    		
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
	
	
	
	
	
	public void UpdateRegister_stock(LoadApp LoadApp) {
		
		  if (LoadApp.getEdata() == null) {  return;  }
		   edata_stock  edata_stock = EdataDAO.geteData_stock(LoadApp.getEdata().getData_id());
			if (edata_stock == null) { return;  }
			if (edata_stock.getStock_list() == null) {return; }
			if (edata_stock.getStock_type() == null) {return;   }
			
			Register_stock stock = StockRepository.findByStockListAndStockType(edata_stock.getStock_list(),edata_stock.getStock_type());
			if (stock == null) { return; }
			 BeanUtils.copyProperties(edata_stock, stock, "stockId","stockCode", "stockReceiptDate", "id");
			 StockRepository.save(stock);
		
	}
	public void UpdateRegister_list(LoadApp LoadApp) {
		
		  if (LoadApp.getEdata() == null) {  return;  }
		    edata_register  edata_register = EdataDAO.geteData_register(LoadApp.getEdata().getData_id());
		    if (edata_register == null) {   }
			if (edata_register.getRegister_name()== null) {return;  }
			if (edata_register.getRegister_type() == null) {return;  }
			
			
			Register_list register_list = RegisterRepository.findByRegisterNameAndRegisterType(edata_register.getRegister_name(),edata_register.getRegister_type());
			if (register_list == null) { return; }
			 BeanUtils.copyProperties(edata_register, register_list, "registerId", "registerCode", "registerDateadd", "id");
			 RegisterRepository.save(register_list);
		
	}
	public void UpdateRegister_product(LoadApp LoadApp) {
		  if (LoadApp.getEdata() == null) {  return;  }
		    edata_product edata_register = EdataDAO.geteData_product(LoadApp.getEdata().getData_id());
			if (edata_register == null) {   }
			if (edata_register.getProduct_names()== null) { return;  }
			if (edata_register.getProduct_category() == null) { return;  }
			
			
			Register_product product = ProductRepository.findByProductNamesAndProductCategory(edata_register.getProduct_names(),edata_register.getProduct_category());
			if (product == null) { return; }
			 BeanUtils.copyProperties(edata_register, product, "productId", "productCode", "vproductDateadd", "id");
			 ProductRepository.save(product);
		
	}
	
	
	
	public void UpdateRegister_assets(LoadApp LoadApp) {
		  if (LoadApp.getEdata() == null) {  return;  }
		  edata_assets edata_assets = Edata_assets.findByEdataId( String.valueOf(LoadApp.getEdata().getData_id()));
			if (edata_assets == null) {  }
			if (edata_assets.getAsset_name()== null) { return;  }
			if (edata_assets.getAsset_type() == null) { return; }
			
			
			assets assets = AssetsRepository.findByAssetNameAndAssetType(edata_assets.getAsset_name(),edata_assets.getAsset_type());
			if (assets == null) { return; }
			 BeanUtils.copyProperties(edata_assets, assets, "assetId", "assetCode", "assetDateAdd", "id");
			 AssetsRepository.save(assets);
		  
			
		
	}
	public void UpdateRegister_annual_plan(LoadApp LoadApp) {
		  if (LoadApp.getEdata() == null) {  return;  }
		  edata_annual_plan edata_annual_plan = Edata_annual_plan.findByEdataId(LoadApp.getEdata().getData_id());
			if (edata_annual_plan == null) {return;   }
			if (edata_annual_plan.getTitle()== null) { return; }
			if (edata_annual_plan.getAnnual_type()== null) {return;   }
			
			
			company_annual_plan annual_plan = Annual_planRepository.findByTitleAndAnnualType(edata_annual_plan.getTitle(),edata_annual_plan.getAnnual_type());
			if (annual_plan == null) { return; }
			 BeanUtils.copyProperties(edata_annual_plan, annual_plan, "annualPlanId", "annual_plan_code", "annual_dateadd", "id");
			 Annual_planRepository.save(annual_plan);
		
			 
		
			 
	}
	public void UpdateRegister_workrule(LoadApp LoadApp) {
		  if (LoadApp.getEdata() == null) {  return;  }
		  edata_workrule edata_workrule = Edata_workrule.findByEdataId(LoadApp.getEdata().getData_id());
			if (edata_workrule == null) { return;  }
			if (edata_workrule.getRule_name()== null) { return; }
			if (edata_workrule.getRule_type()== null) { return;  }			
			
			workrule workrule = WorkruleRepository.findByRuleNameAndRuleType(edata_workrule.getRule_name(),edata_workrule.getRule_type());
			if (workrule == null) { return; }
			 BeanUtils.copyProperties(edata_workrule, workrule, "ruleId", "rule_code", "rule_datarun", "id");
			 WorkruleRepository.save(workrule);
	}
	
	
	

	public void Register_stock(LoadApp LoadApp) {
	
		    if (LoadApp.getEdata() == null) { States = State.INVALID_TYPE;  System.out.println("Register_stock 1");   return;  }
		   
		    edata_stock  edata_stock = EdataDAO.geteData_stock(LoadApp.getEdata().getData_id());
		    
		    
		    
			if (edata_stock == null) { States = State.INVALID_TYPE;  System.out.println("Register_stock 2"); return;  }
			if (edata_stock.getStock_list() == null) { States = State.INVALID_TYPE;  System.out.println("Register_stock 3"); return; }
			if (edata_stock.getStock_type() == null) { States = State.INVALID_TYPE;  System.out.println("Register_stock 4");  return; }
			
			String Code =null;
			
		   Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
			.filter(row->row.getStep().equals("Approval"))
			.filter(row->row.getCode_rid() != null)
			.findFirst();
			
		    if(rid.isPresent()) {
		    	Code  = rid.get().getCode_rid();
		     }
			
			
		     int save = EdataDAO.SaveMainStock(edata_stock,Code,LoadApp);
		     if(save > 0) {
		    	 
		    	 System.out.println("Register_stock 5");
		    	 States = State.SUCCESS;  	
		    	 // Log
		    	 
		    	 return; 
		     }
		     States = State.NONE;  return; 
	}


	
		public void Set_poin(LoadApp LoadApp) {
			
			workflow workflow =  LoadApp.getWorkflow();
			switch (workflow.getWorkflow_maindb()) {
	      
					case "Procurement_SupplyChain":
						System.err.println("SaveApp ->Procurement_SupplyChain->Procurement_SupplyChain ");
						if(LoadApp.getEdata_register_processing().getSupplier_id()  == null) {return; }
						LoadApp.getEdata_register_processing().setEdata_id(LoadApp.getEdata().getData_id());
						
						  
						  
						  if( EdataDAO.SaveRating(LoadApp.getEdata_register_processing(),"supplier") == 1) {
						
								System.err.println("SaveApp ->SaveRating SUCCESS");
							  States =  State.SUCCESS;  // ✅ บันทึกสำเร็จ 
							  return;
						 }
						 States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
			            break;    
			        case "Production":
			            break;   
			        default:
			        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
			    }	
			
		}

		
		
		
	public void Income_stock(LoadApp LoadApp) {
			
			workflow workflow =  LoadApp.getWorkflow();
		
			switch (workflow.getWorkflow_maindb()) {
	      
					case "Procurement_SupplyChain":
						
						System.err.println("SaveApp ->Procurement_SupplyChain->Procurement_SupplyChain ");
						
						System.err.println("itemid "+LoadApp.getEdata_procurements().getProcurement_itemid());
						System.err.println("received "+LoadApp.getEdata_procurements().getQuantity_received());

						try { 
								LoadApp.getEdata_procurements().setEdata_id(LoadApp.getEdata().getData_id());	
								EdataDAO.UpdateStockOptimistic(LoadApp.getEdata_procurements());
								List<edata_procurement> SubData = EdataDAO.getAllEdata_procurements_item(LoadApp.getTempId());			
							
								System.err.println("TempId "+LoadApp.getTempId());
								System.err.println("SubData  "+SubData.size());
								
								SubData.forEach(row->{
									
									System.err.println("Procurement_itemid "+row.getProcurement_itemid());
									System.err.println("Quantity_received "+row.getQuantity_received());
									
									
									EdataDAO.UpdateStockOptimistic(row);
								});		
								
								
								
									} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
							    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
							    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
						}
						
						EdataDAO.markAsProcessed(LoadApp.getTempId());
						States =  State.SUCCESS;
			            break;    
			        case "Production":
			            break;   
			        default:
			        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
			    }	
			
		}
	
	
	
	public void Register_list(LoadApp LoadApp) {
		
		
		
	    if (LoadApp.getEdata() == null) { States = State.INVALID_TYPE;  System.out.println("Register_stock 1");   return;  }
	   
	    edata_register  edata_register = EdataDAO.geteData_register(LoadApp.getEdata().getData_id());
	    
	    
		if (edata_register == null) {   }
		if (edata_register.getRegister_name()== null) { }
		if (edata_register.getRegister_type() == null) { }
		
		
	
		String Code = null;
		  Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
					.filter(row->row.getStep().equals("Approval"))
					.filter(row->row.getCode_rid() != null)
					.findFirst();
					
				    if(rid.isPresent()) {
				    	Code  = rid.get().getCode_rid();
				     }
	   
	    
	     
	     try {
	    	  int save = EdataDAO.SaveMainRegister(edata_register,Code,LoadApp);
	    	    if(save > 0) {
	   	    	 States = State.SUCCESS;  
	   	    	 // Log
	   	    	 return; 
	   	     }
	     } catch (Exception e) {
		     States = State.INVALID_TYPE;  
		     throw new IllegalArgumentException(e);
	     }
	     
	     
	 

	     return; 
}

	
	
	
	
	
					
					public void Register_assets(LoadApp LoadApp) {
						
					    if (LoadApp.getEdata() == null) { }
					   
					    edata_assets edata_assets = Edata_assets.findByEdataId( String.valueOf(LoadApp.getEdata().getData_id()));
					    
						if (edata_assets == null) {  }
						if (edata_assets.getAsset_name()== null) {  }
						if (edata_assets.getAsset_type() == null) { }
					    
				
						
						String Code = null;
						  Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
									.filter(row->row.getStep().equals("Approval"))
									.filter(row->row.getCode_rid() != null)
									.findFirst();
									
								    if(rid.isPresent()) {
								    	Code  = rid.get().getCode_rid();
								     }

					     int save = EdataDAO.SaveMainAssets(edata_assets,Code,LoadApp);
					     if(save > 0) {
					    	 return; 
					     }
				
				}
							
			
				
				
				public void Register_annual_plan(LoadApp LoadApp) {
									
					
				    if (LoadApp.getEdata() == null) {  }
				   
				    edata_annual_plan edata_annual_plan = Edata_annual_plan.findByEdataId(LoadApp.getEdata().getData_id());
				    
				    
					if (edata_annual_plan == null) {  }
					if (edata_annual_plan.getTitle()== null) { }
					if (edata_annual_plan.getAnnual_type()== null) {  }
					
					String Code = null;
					  Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
								.filter(row->row.getStep().equals("Approval"))
								.filter(row->row.getCode_rid() != null)
								.findFirst();
								
							    if(rid.isPresent()) {
							    	Code  = rid.get().getCode_rid();
							     }
			
				     int save = EdataDAO.SaveMainAnnual_plan(edata_annual_plan,Code,LoadApp);
				     if(save > 0) {
				    	 
				    	 States = State.SUCCESS;  
				    	 // Log
				    	 
				    	 return; 
				     }
				     States = State.NONE;  return; 
			}
				
				
				public void Register_workrule(LoadApp LoadApp) {
									
					
				    if (LoadApp.getEdata() == null) {  }
				   
				    edata_workrule edata_workrule = Edata_workrule.findByEdataId(LoadApp.getEdata().getData_id());
				    
				    
					if (edata_workrule == null) {  }
					if (edata_workrule.getRule_name()== null) { }
					if (edata_workrule.getRule_type()== null) {  }
					
					String Code = null;
					  Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
								.filter(row->row.getStep().equals("Approval"))
								.filter(row->row.getCode_rid() != null)
								.findFirst();
								
							    if(rid.isPresent()) {
							    	Code  = rid.get().getCode_rid();
							     }
			
				     int save = EdataDAO.SaveWorkRule(edata_workrule,Code,LoadApp);
				     if(save > 0) {
				    	 
				    	 States = State.SUCCESS;  
				    	 // Log
				    	 
				    	 return; 
				     }
				     States = State.NONE;  return; 
			}
				
				
				
				
				
			
	
	
				public void Register_product(LoadApp LoadApp) {
						
						
						
					    if (LoadApp.getEdata() == null) {}
					   
					    edata_product edata_register = EdataDAO.geteData_product(LoadApp.getEdata().getData_id());
					    
					    
						if (edata_register == null) {   }
						if (edata_register.getProduct_names()== null) {  }
						if (edata_register.getProduct_category() == null) {  }
						
						String Code = null;
						  Optional<sub_processes> rid = LoadApp.getProcess().getSub_processes().stream()
									.filter(row->row.getStep().equals("Approval"))
									.filter(row->row.getCode_rid() != null)
									.findFirst();
									
								    if(rid.isPresent()) {
								    	Code  = rid.get().getCode_rid();
								     }

					     int save = EdataDAO.SaveMainProduct(edata_register,Code,LoadApp);
					     if(save > 0) {
					    	 
					    	 States = State.SUCCESS;  
					    	 // Log
					    	 
					    	 return; 
					     }
					     States = State.NONE;  return; 
				}

	
	
				public void Issued_sk(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
				
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
						

							
										LoadApp.getIssue_sk().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateIssued_sk(LoadApp.getIssue_sk());
										List<Issue_sk> SubData = EdataDAO.getAllIssued_sk_item(LoadApp.getTempId());			
									
										SubData.forEach(row->{
											EdataDAO.UpdateIssued_sk(row);
										});		
										
										
										
										try { } catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								}
								
						
								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
				
				
				public void Issued_fg(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
				
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
						

								try { 
										LoadApp.getIssue_fg().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateIssued_fg(LoadApp.getIssue_fg());
										List<Issue_fg> SubData = EdataDAO.getAllIssued_fg_item(LoadApp.getTempId());			
									
										SubData.forEach(row->{
											EdataDAO.UpdateIssued_fg(row);
										});		
										
										
										
											} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								}
								
						
								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
			
				
				
				
				
				
				public void Adjustment_fg(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
				
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
			

								try { 
										LoadApp.getAdjustment_fg().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateAdjustment_fg(LoadApp.getAdjustment_fg());
										List<Adjustment_fg> SubData = EdataDAO.getAllAdjustment_fg_item(LoadApp.getTempId());			
									

										SubData.forEach(row->{
	
											EdataDAO.UpdateAdjustment_fg(row);
										});		
										
										
										
											} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								}
								
							
								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
			
				
				public void Adjustment_sk(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
				
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
			

									try { 
							
										LoadApp.getAdjustment_sk().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateAdjustment_sk(LoadApp.getAdjustment_sk());
										List<Adjustment_sk> SubData = EdataDAO.getAllAdjustment_sk_item(LoadApp.getTempId());			
									

										SubData.forEach(row->{
	
											EdataDAO.UpdateAdjustment_sk(row);
										});		
										
										
										} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								}
								
							
								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
			
				
				
				public void Goods_receipt_fg(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
				
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
								System.err.println("SaveApp ->Stock_Warehouse ");
							

				
										LoadApp.getGoods_receipt_fg().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateFGOptimistic(LoadApp.getGoods_receipt_fg());
										List<Goods_receipt_fg> SubData = EdataDAO.getAllGoods_receipt_fg_item(LoadApp.getTempId());			
									
										
										SubData.forEach(row->{
											EdataDAO.UpdateFGOptimistic(row);
										});		
										
										
										
										try { 	} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								}

								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
			
				
				
				public void Goods_receipt_sk(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
				
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
								System.err.println("SaveApp ->Stock_Warehouse ");
							

				
										LoadApp.getGoods_receipt_sk().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateSKOptimistic(LoadApp.getGoods_receipt_sk());
										List<Goods_receipt_sk> SubData = EdataDAO.getAllGoods_receipt_sk_item(LoadApp.getTempId());			
									
										
										SubData.forEach(row->{
											EdataDAO.UpdateSKOptimistic(row);
										});		
										
										
										
										try { 	} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								}

								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
			
				  public void Inventory_transfer_sk(LoadApp LoadApp) {
						
						workflow workflow =  LoadApp.getWorkflow();
					
						switch (workflow.getWorkflow_maindb()) {
				      
								case "Stock_Warehouse":
									
									System.err.println("SaveApp ->Stock_Warehouse ");
								

								
										
										
										
										
											LoadApp.getInventory_transfer_sk().setEdata_id(LoadApp.getEdata().getData_id());	
											EdataDAO.UpdateInventory_transfer_sk(LoadApp.getInventory_transfer_sk());
											List<Inventory_transfer_sk> SubData = EdataDAO.getAllInventory_transfer_sk_item(LoadApp.getTempId());			
										
			
											SubData.forEach(row->{
												EdataDAO.UpdateInventory_transfer_sk(row);
											});		
											
											
											
											try {	} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
										    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
										    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
									}

									States =  State.SUCCESS;
						            break;    
						        case "Production":
						            break;   
						        default:
						        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
						    }	
						
					}
					
					

           public void Inventory_transfer_fg(LoadApp LoadApp) {
					
					workflow workflow =  LoadApp.getWorkflow();
					System.err.println(" Inventory_transfer_fg ");
					
					
					switch (workflow.getWorkflow_maindb()) {
			      
							case "Stock_Warehouse":
								
								System.err.println(" Inventory_transfer_fg ");
							

								try { 
										LoadApp.getInventory_transfer_fg().setEdata_id(LoadApp.getEdata().getData_id());	
										EdataDAO.UpdateInventory_transfer_fg(LoadApp.getInventory_transfer_fg());
										List<Inventory_transfer_fg> SubData = EdataDAO.getAllInventory_transfer_fg_item(LoadApp.getTempId());			
									
		
										SubData.forEach(row->{
											EdataDAO.UpdateInventory_transfer_fg(row);
										});		
										
										
										
										} catch (RuntimeException e) { // ✅ ดัก RuntimeException ที่ถูกโยน
									    States = State.INVALID_TYPE; // ✅ เปลี่ยน State แทนโหลด Error Page
									    return; // ❌ หยุดทำงาน ไม่ต้องให้ไปต่อ
								    }

								States =  State.SUCCESS;
					            break;    
					        case "Production":
					            break;   
					        default:
					        	States =  State.INVALID_TYPE;  // ❌ ชนิดข้อมูลไม่ถูกต้อง
					    }	
					
				}
				
				
			
	

	  public String getState() {
		return States.toString();
	}




	public void setState(State state) {
		States = state;
	}










	
}
