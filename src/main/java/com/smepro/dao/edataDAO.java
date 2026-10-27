package com.smepro.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.dto.edataDTO;
import com.smepro.dto.inventoryDTO;
import com.smepro.dto.materialDTO;
import com.smepro.dto.productionDTO;
import com.smepro.dto.search_workflowDTO;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.models.actionplan;
import com.smepro.models.bom;
import com.smepro.models.customreport;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_production_record;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.edata_stock;
import com.smepro.models.edatas;
import com.smepro.models.eformfield;
import com.smepro.models.fieldindex;
import com.smepro.models.keymap;
import com.smepro.models.keymap2;
import com.smepro.models.other;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.product_log;
import com.smepro.models.product_master;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.purchase;
import com.smepro.models.qci;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.report_mapping;
import com.smepro.models.stock;
import com.smepro.models.stock_log;
import com.smepro.models.stock_lot;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Asset_log;
import com.smepro.models.Audit;
import com.smepro.models.BatchEdata;
import com.smepro.models.Calendar;
import com.smepro.models.DocumentEffect;
import com.smepro.models.EdataAppManagement;
import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Extension;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.Fg;
import com.smepro.models.FilterChain;
import com.smepro.models.FormPrint;
import com.smepro.models.FullResult;
import com.smepro.models.Goal;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.InputHiddenVisibility;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Management;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Myapp;
import com.smepro.models.Platform;
import com.smepro.models.Relations;
import com.smepro.models.Segmentation;
import com.smepro.models.SetSegmentView;
import com.smepro.models.SubData;
import com.smepro.models.TabManagement;
import com.smepro.models.Tabform;
import com.smepro.models.UseExtension;
import com.smepro.models.UseformPrint;



public interface edataDAO {
	List<edata> getall(long data_eformid,int data_eformrv);
	List<edata> getall(int plan_id);
	
	List<edata> getallgroupby(long data_eformid,int data_eformrv,String g1,String g2);
	int  CountandGroup(long data_eformid,int data_eformrv,String g1,String g2,String date);
	
	productionDTO  Summary_production(long data_eformid,int data_eformrv
			,int Good,int Defective,String date);
	
	List<edata> production(long data_eformid,int data_eformrv,search_workflowDTO search);
	List<edata> material(long data_eformid,int data_eformrv,search_workflowDTO search);


    List<materialDTO> usematerial(long data_eformid,int data_eformrv,fieldindex row);
	 
	 
	List<edata> getall(int data_eformid,int data_eformrv
			,String title1,String key1
			,String title2,String key2
			,String title3,String key3
			,String title4,String key4
			,String dateStart,String dateEnd);
	
	List<edata> getall(edataDTO edataDTO);

	int getNumreport(int id);
	
	edata getbyid(int id);
	List<edata> getallplan(int data_eformid,int data_eformrv,int pnid);
	List<edata> getallplan(int data_eformid,int data_eformrv);
	List<edata> getallreport(int plan,String start,String end);
	
	List<edata> getallreport(int planid,int planid2,String start,String end);
	List<edata> getallreportmap(int planid,int planid2,String start,String end,List<eformfield> eformfields,List<eformfield> eformfields2,report_mapping mappings);
	
	
	List<edata> getallreport(String plan);
	 List<edata> getallreportmap(String id,int plan1,int plan2,report_mapping mappings,int size);
	int delete(int id);
	int deletebyfilepath(int id);
	int save(edata edatas);
	int savejoin(edata edatas);
	int setapp(int id,String app);
	int update(edata edatas);
	edata getbyfilepath(int id);
	void setOrder(long id,int product_no);
	
	
	edata getbycal(String product,long product_no,String lot,long lot_no,long port,long rv);
	edata getCal(int id,int rv,String value, long product_no,String value2, long log_no,long data_no);
	edata getAmount(int id,int rv,String value, long product_no,String value2, long log_no,long data_no);
	
	void setcal_update(String cal_value,long product_no,int cal_condition);
	void setcal_plus(String cal_value,long product_no,int cal_condition);
	int setcal_minus(String cal_value,long product_no,int cal_condition);
	
	
	
	void setTotal(String datetime,String cal_value,long product_no,int data_id); 
	
	List<edata> getalllot(int data_eformid,int data_eformrv,String value,long product_no);
	List<edata> getallGroupBy(int data_eformid,int data_eformrv,long product_no);
	
	
	
	edata getCal(int id,int rv,String value, long product_no,long data_no);
	edata getTotal(long product_no,int data_id);
	edata getReserve(int id,int rv,String value,long product_no,long data_no);
	edata getReserve(int id,int rv,String value,String lot,long product_no,long lot_no,long data_no);
	
	List<edata> getallproduct(int planid,String product,String lot,int product_no,int lot_no);
	
	
	void closestock(int id);
	void openstock(int id);
	List<edata> getTrace();
	
	List<edata> getallTrace(
			String product,String lot
			,String date_start,String date_end,
			int port1,int rv1,int port2,int rv2
			,int product_no1, int lot_no1
			,int product_no2,int lot_no2
			);
	edata getTrace(String product,String lot,int product_no, int lot_no,int port1,int rv1);
	
	List<edata> getalllog(int id);
	int savelog(edata edatas);
	int updatelog(edata edatas);
	
	
	boolean checklog(int id,String date);
	int countclearance(int id,String _respon);
	edata getclearance(int id,String _respon);
	List<edata> getReceived(
			String product,String lot
			,int receiveproduct_no,int receivelot_no,int receive_no
			,int port,int portrv
			);
	List<edata> getReceivedtransaction(
			String product,String lot
			,int receiveproduct_no,int receivelot_no,int receive_no
			,int port,int portrv
			);
	
	edata getSum(int id,int rv,long product_no);
	int setdue(int order_postion,int balance_postion,int due_postion,int cal_condition);
	
	
	
	edata getMaxbalance(int id,int no);
	
	
	
	/**
	 * Method สำหรับสร้าง ดึง edata มาเช็คสถานะเเล้วสรุป <br>
	 * อ่านครั้งละ 10000 
	 * 
	 * @param 
     * @return List<edata>
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	List<edata> getallTraceabilityStatus();	
	
	
	
	/**
	 * Method สำหรับ update edata<br>
	 * ทำงานเเบบ Batch
	 * 
	 * @param List<edata>,
     * @return int[][]
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	int[][] update(List<edata> edatas,int size);	
	
	
	

		
			int saveCheck(int user_id, String date);
	
			List<edatas> getallbyuser(String user);
			int setapp(int id, String user_app, int flow,String Status);
			edata getStatus(int plan_id);
			edata getStatus(int id, int rv, String pd, String batch);
			int setTurnFlow(int id);
			int setUnTurnFlow(int id);
		
	
	
			List<edata> getallCondition_id(int condition_id);
		

			edata getStatus(int id, int rv, String date);
			List<edata> getallexample(long data_eformid, int data_eformrv);
			int getallCountbyuser(String user);
			int setapp(int id, String user_app, int flow, String Status, String note,String date);
			
			
			
			void CreateTable(String sql);
			int SaveStock(edata_stock edata_stocks,LoadApp LoadApp);
			edata_stock geteData_stock(int edaia_id);
		

			int CheckDuplicateStock(edata_stock edata_stocks,int eformID);
			List<stock> getStock_card(int edaia_id);
			int CheckStock(edata_stock edata_stocks);
		

			int SaveMainStock(edata_stock edata_stocks, int workflowID, String Status,LoadApp LoadApp);
			
			
			stock getStock(edata_stock edata_stocks);
			int UpdateStockOptimistic(edata_procurement edata_procurement);
			
			
		
			
			
			
			
			
			
			/**
			 * Method สำหรับสร้าง saveSubdata ตารางรอง <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void saveSubdata(List<SubData> Subdata,int id);
			
		
			
			/**
			 * Method สำหรับสร้าง getSubDataAll ตารางรอง <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			List<SubData> getSubDataAll(int id);
			int TransactionStockOptimistic(edata_stock edata_stocks);
			
			
			
			/**
			 * Method สำหรับอัพเดต SubData ตารางรอง <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void AppSubDataAll(int idrefer);
			
			
			void  setTransaction(int data_id);
			
			
			
			
			
			
			
			
			
			
			
			
			
			/**
			 * Method สำหรับสร้าง save process <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void save_process(process processs);
			
			
			/**
			 * Method สำหรับสร้าง save purchase <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			List<process> getAllProcess(int id_refer);
			
			
			/**
			 * Method สำหรับสร้าง getProcess <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			process  getProcess(int id);
		
			
			
			/**
			 * Method สำหรับสร้าง getProcess <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void   remove_process(int id);
			/**
			 * Method สำหรับสร้าง remove_process_all <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void remove_process_all(int id_refer);
			
			
			
			
			
			
			
			/**
			 * Method สำหรับสร้าง save process <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void save_subprocess(sub_processes sub_processes);
			
			
			/**
			 * Method สำหรับสร้าง  getAllSubProcess <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			List<sub_processes> getAllSubProcess(int id_refer);
			
			
			/**
			 * Method สำหรับสร้าง getProcess <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			sub_processes  getSubProcess(int id);
		
			
			
			/**
			 * Method สำหรับสร้าง getProcess <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void   remove_subprocess(int id);
			
			
			/**
			 * Method สำหรับสร้าง remove_process_all <br>
			 * 
			 * @param 
		     * @return 
		     * @throws NULL
		     * @since V1.00
		     * @author Worasak
			 * 
			 */
			void remove_subprocess_all(int id_refer);
			

			Myapp getMyapp(String app_name);
			List<registerkey> getAllRegisterkey(int app_id,String type);
			void save_keymap(keymap keymap);
			List<keymap> getAllKeyMap(int sub_processes_id);
			List<keymap> getAllKeyMaps(int processes_id);
			
			
			void remove_keyMap_all(int sub_processes_id);
			void remove_keyMap_alls(int processes_id);
			List<process> getAllProcess(String id_refer);
			List<stock> getStock_cardAll(String type);
			int saves(edata edatas);
			int CheckDuplicateStock(edata_stock edata_stocks);
			List<edata> getallProcesses(long process_id);
			
			
			String addDoc(int id_refer,String type);
			List<doc> getDoc(int id_refer);
		
			int SaveRegister(edata_register edata_registers);
			edata_register geteData_register(int edaia_id);
			int CheckDuplicateRegister(edata_register edata_registers);
			
			
			int SaveMainRegister(edata_register edata_registers, String Code,LoadApp LoadApp);
			int SaveMainStock(edata_stock edata_stocks,String Code,LoadApp LoadApp);
			int SaveProcurements(edata_procurement edata_procurements);
			edata_procurement geteData_procurement(int edaia_id);
			void SaveEdata_procurements_item(List<edata_procurement> Subdata, int id);
			List<edata_procurement> getAllEdata_procurements_item(String tempId,String type);
			void DelEdata_procurements_item(int tempId,String type);
			
			
			void save_policies(policies policies);
			List<policies> getAllMainPolicies(int processes_id);
			List<policies> getAllPolicies(int sub_processes_id);
			void DelPolicies_item(String group_id);
			List<policies> getAllGroupPolicies(String processes_id);
			policies getPolicies(int sub_processes_id);
			policies getMyPolicies(int sub_processes_id, String user, String groups, String department);
			policies getPoliciesStep(int sub_processes_id, String user, String groups, String department);
			sub_processes GetNextFlow(int id);
			
			
			void APPEdata_procurements_item(int tempId);
			int save_transaction_send(transaction_send transaction_sends);
			void SendEdata_procurements_item(String tempId, String type);
	
			keymap getKeyMapByType(int id, String type);
			keymap getKeyMapById(int id);
			List<stock> getAllStock(String type);
			List<register_log> getAllRegister_log(int workflow_id);
			List<transaction_send> getAllTransaction_send(int process_id, String status, String send_to);
			void SaveReference(int processes_id, String selectedIds);
			List<doc> getDoc(String doc_type);
		
			void CopyEdata_procurement(int edata_id,String ids);
			void ReceiveEdata_procurements_item(String id_refer,String app);
			List<doc> getDoc_in_set(String doc_type);
			List<edata_procurement> getAllEdata_procurements_itemstatus(String tempId, String status);
			int SaveRegister_processing(edata_register_processing edata_register_processings);
			int SaveRating(edata_register_processing edata_register_processings, String type);
			edata_register_processing geteData_register_processing(int edaia_id);
			List<edata_procurement> getAllEdata_procurements_item(String tempId);
			void markAsProcessed(String itemId);
			void ReceiveTransaction_send(int edata_id);
			int CheckDuplicateProduct(edata_product edata_products);
			
			int SaveProduct(edata_product edata_products);
			int SaveMainProduct(edata_product edata_products, String Code,LoadApp LoadApp);
			edata_product geteData_product(int edaia_id);
			List<product_log> getAllProduct_log(int workflow_id);
			List<product_master> getAllProduct(String type);
		
			
			
			int SaveProduction_lists(production_list production_list);
			int SaveProduction_directives(production_directive production_directive);
			int SaveEdata_production_records(edata_production_record edata_production_records);
			production_list getProduction_lists(int edaia_id);
			production_directive getProduction_directives(int edaia_id);
			edata_production_record getEdata_production_records(int edaia_id);
			List<process> getProcess_join(int id);
			List<production_directive> geteAllPD(String id);
			List<edata_production_record> geteAllproduction_records(String pd);
			
			
			production_directive geteData_pd(int edaia_id);
			production_list geteData_pl(int edaia_id);
			edata_production_record geteEdata_production_record(int edaia_id);
			edata_production_record getSumProduction_records(String pd);
			List<bom> getAllEdata_bom_item(String tempId);
			List<production_standard> getAllEdata_production_standard_item(String tempId);
			void DelBoms(int tempId);
			void DelPS(int tempId);
			void SaveBom(List<bom> Subdata, int id);
			void SaveProduction_standard(List<production_standard> Subdata, int id);
			int SaveBom(bom boms);
			int SaveProduction_standard(production_standard production_standards);
			bom getBom(int edaia_id);
			production_standard getProduction_standard(int edaia_id);
			void APPBom(int Id);
			void APPPS(int Id);
			List<bom> getAllBom();
			void SaveAttachdocuments(int edata_id, int doc_id, String type);
			bom getBomId(int id);
			product_master getProduct_master(int id);
			List<bom> getAllEdata_bom_item_group(int tempId);
			List<bom> getAllEdata_bom_item_category(String tempId, String category);
			int SaveQci(qci qci_info);
			qci getQci(int edaia_id);
			List<qci> geteAllQCI(String qci);
			List<production_standard> getAllPs();
			production_standard getPsId(int id);
			int SaveOther(other other_info);
			void DelOther(int tempId);
			void SaveOther(List<other> subData, int id);
			other getOther(int edaia_id);
			List<edata> getallworkflow(int workflow_id);
			 List<edata_production_record> getTrackingProcess(String name,int id);
			List<edata_production_record> getTrackingRecord(String name);
			List<other> getTrackingOther(String name);
			List<edata> getAllTraceability(String sub_process, int process_id, search_workflowDTO search);
			
			
			
			
			List<edata_production_record> getAllEdata_production_records(int process_id, customreport customreports);
			List<edata> getallEPR(int process_id, customreport customreports);
			List<edata> getallEdataQCI(int process_id, customreport customreports);
			List<qci> getAllQCI(int process_id, customreport customreports);
			List<other> getAllOther(int process_id, customreport customreports);
			List<edata> getallEdataOther(int process_id, customreport customreports);
			List<production_list> getAllPL(int process_id, customreport customreports);
			List<edata> getallEdataPL(int process_id, customreport customreports);
			List<production_directive> getAllPD(int process_id, customreport customreports);
			List<edata> getallEdataPD(int process_id, customreport customreports);
			List<edata> getallEdataPR(int process_id, customreport customreports);
			List<edata_procurement> getAllPR(int process_id, customreport customreports);
			List<edata> getallEdataBOM(int process_id, customreport customreports);
			List<bom> getAllBOM(int process_id, customreport customreports);
			List<edata> getallEdataPS(int process_id, customreport customreports);
			List<production_standard> getAllPS(int process_id, customreport customreports);
			int NumRegister(String type);
			registers getRegister(int id);
			void SavePL(List<production_list> subData, int id);
			void SavePD(List<production_directive> subData, int id);
			void DelPL(int tempId);
			void DelPD(int tempId);
			List<production_list> getAllEdata_pl_item(String tempId);
			List<production_directive> getAllEdata_pd_item(String tempId);
			List<other> getAllEdata_other_item(String tempId);
			
			
		
			int CheckGoal(String date_due, Goal row);
			void setCheckFile(int id,int data_file_ck);
			void setTitle(int id, String title);
			List<register_log> getAllRegister_logByidAdd(int process_id, int id_add);
			List<product_log> getAllProduct_logByidAdd(int process_id, int id_add);
			int CheckDuplicatebatch(production_list production_lists);
			void getTransaction_send_mark(int id, int alert);
			void getIdoc_mark(int id, int alert);
			void setLot_batch(String batch, int id);
			void SetProcessVisibility(int processe_id, String visibility);
			void CopyProduction_directivet(int edata_id, String ids);
			List<edata> getallProcesses(int process_id, String parentId, int limit);
			List<transaction_send> getAllTransaction_send(int process_id, String parentId, int limit);
			List<transaction_send> getAllTransaction_send(int process_id, int limit);
			other geteData_other(int edaia_id);
			List<other> geteAllOther(String other);
			sub_processes getSubProcess_Action(int id);
			List<other> geteAllOtherByProcess(int process_id);
			List<production_directive> geteAllPDByProcess(int process_id);
			
			
			
			int Receipt_FG(Goods_receipt_fg goods_receipt_fg, int edata_id);
			int Adjustment_FG(Adjustment_fg adjustment_fg, int edata_id);
			int Issue_FG(Issue_fg issue_fg, int edata_id);
			int Transfer_FG(Inventory_transfer_fg inventory_transfer_fg, int edata_id);
			Fg getFG(String product_code);
			int UpdateIssued_fg(Issue_fg issue_fg);
			List<Issue_fg> getAllIssued_fg_item(String tempId);
			int UpdateAdjustment_fg(Adjustment_fg adjustment_fg);
			List<Adjustment_fg> getAllAdjustment_fg_item(String tempId);
			int UpdateFGOptimistic(Goods_receipt_fg goods_receipt_fg);
			List<Goods_receipt_fg> getAllGoods_receipt_fg_item(String tempId);
			int UpdateInventory_transfer_fg(Inventory_transfer_fg inventory_transfer_fg);
			List<Inventory_transfer_fg> getAllInventory_transfer_fg_item(String tempId);
			void save_keymap(keymap2 keymap);

			List<process> getAllProcessJoin(int id);
			List<product_log> getAllProduct_log(int workflow_id, String allowed_id);
			List<register_log> getAllRegister_log(int workflow_id, String allowed_id);
		
			
			Adjustment_fg getAdjustment_fg(int edaia_id);
			Goods_receipt_fg getGoods_receipt_fg(int edaia_id);
			Inventory_transfer_fg getInventory_transfer_fg(int edaia_id);
			Issue_fg getIssue_fg(int edaia_id);
			List<Adjustment_fg> geteAllAdjustment_fg(String refer);
			List<Inventory_transfer_fg> geteAllInventory_transfer_fg(String refer);
			List<Issue_fg> geteAllIssue_fg(String refer);
			List<Goods_receipt_fg> geteAllGoods_receipt_fg(String refer);
			List<Fg> getAllFg();
		
			
			List<stock_log> getAllStock_log(int workflow_id, String allowed_id);
			
			
			
			int Receipt_SK(Goods_receipt_sk goods_receipt_sk, int edata_id);
			int Transfer_SK(Inventory_transfer_sk inventory_transfer_sk, int edata_id);
			int Issue_SK(Issue_sk issue_sk, int edata_id);
			int Adjustment_SK(Adjustment_sk adjustment_sk, int edata_id);
			stock_lot getSk(String stock_code);
			List<Issue_sk> getAllIssued_sk_item(String tempId);
			List<Adjustment_sk> getAllAdjustment_sk_item(String tempId);
		
			
			int UpdateAdjustment_sk(Adjustment_sk adjustment_sk);
			int UpdateIssued_sk(Issue_sk issue_sk);
			int UpdateInventory_transfer_sk(Inventory_transfer_sk inventory_transfer_sk);
			List<Goods_receipt_sk> getAllGoods_receipt_sk_item(String tempId);
			List<Inventory_transfer_sk> getAllInventory_transfer_sk_item(String tempId);
			
			
			Issue_sk getIssue_sk(int edaia_id);
			Inventory_transfer_sk getInventory_transfer_sk(int edaia_id);
			Goods_receipt_sk getGoods_receipt_sk(int edaia_id);
			Adjustment_sk getAdjustment_sk(int edaia_id);
			
			
			List<Goods_receipt_sk> geteAllGoods_receipt_sk(String refer);
			List<Issue_sk> geteAllIssue_sk(String refer);
			List<Inventory_transfer_sk> geteAllInventory_transfer_sk(String refer);
			List<Adjustment_sk> geteAllAdjustment_sk(String refer);
			int UpdateSKOptimistic(Goods_receipt_sk goods_receipt_sk);
			List<stock_lot> getAllSk();
	
			
			List<Goods_receipt_sk> GetGSKSearch(String search,String stock_code);
			List<Issue_sk> GetISkSearch(String search,String stock_code);
			
			
			
			List<Adjustment_sk> geteAllSKByProcess(int process_id);
			List<registers> getAllRegister(String type, int limit);
			List<product_master> getAllProduct(String type, int limit);
			List<stock> getAllStock(String type, int limit);
			List<bom> getAllBom(int limit);
			List<Fg> getAllFg(int limit);
			List<production_standard> getAllPs(int limit);
			stock getSk(int id);
			stock_log getStock_log(int id_refer);
			List<stock_lot> getAllSk(String stock_code);
			List<Inventory_transfer_sk> GetTSKSearch(String search, String stock_code);
			List<Adjustment_sk> GetASKSearch(String stock_code);
			List<Issue_sk> GetISKSearch(String search);
			List<Fg> GetFGSearch(String search);
			List<Issue_fg> GetIFGSearch(String search);
			List<Issue_sk> GetTracking_numberSK();
			List<Fg> getAllFg(String code);
			List<product_master> getAllProduct(int limit);
			List<product_log> getAllProduct_log();
			List<stock_log> getAllStock_log();
			product_log getProduct_log(int id_refer);
			List<Adjustment_fg> GetAFGSearch(String stock_code);
			List<Inventory_transfer_fg> GetTFGSearch(String search, String product_code);
			List<Goods_receipt_fg> GetGFGSearch(String search, String product_code);
			List<Issue_fg> GetIFGSearch(String search, String product_code);
			List<production_list> getAllPL(String code);
			List<production_directive> getAllPD(String PL);
			void APPPD(edata edatas);
			void APPPL(edata edatas);
			int Issue_SK(Issue_sk issue_sk);
		
			void DelAdjustment_sk(int tempId);
			void DelIssue_sk(int tempId);
			void DelInventory_transfer_sk(int tempId);
			void DelGoods_receipt_sk(int tempId);
			void DelAdjustment_fg(int tempId);
			void DelIssue_fg(int tempId);
			void DelInventory_transfer_fg(int tempId);
			void DelGoods_receipt_fg(int tempId);
			
			
			void SaveGoods_receipt_sk(List<Goods_receipt_sk> subData, int id);
			void SaveInventory_transfer_sk(List<Inventory_transfer_sk> subData, int id);
			void SaveIssue_sk(List<Issue_sk> subData, int id);
			void SaveAdjustment_sk(List<Adjustment_sk> subData, int id);
			
			void SaveIssue_fg(List<Issue_fg> subData, int id);
			void SaveGoods_receipt_fg(List<Goods_receipt_fg> subData, int id);
			void SaveInventory_transfer_fg(List<Inventory_transfer_fg> subData, int id);
			void SaveAdjustment_fg(List<Adjustment_fg> subData, int id);
			int CheckDuplicateWorkrule(edata_workrule edata_workrule);
		
			int CheckDuplicateAnnual_plan(edata_annual_plan edata_annual_plan);
			int CheckDuplicateAssets(edata_assets edata_assets);
			
			
			void APPOther(edata edatas);
			void APPGoods_receipt_sk(edata edatas);
			void APPAdjustment_sk(edata edatas);
			void APPIssue_sk(edata edatas);
			void APPInventory_transfer_sk(edata edatas);
			
			void APPGoods_receipt_fg(edata edatas);
			void APPAdjustment_fg(edata edatas);
			void APPIssue_fg(edata edatas);
			void APPInventory_transfer_fg(edata edatas);
			void APPEdata_procurements(edata edatas);
			
			
			int SaveMainAssets(edata_assets edata_assets, String Code, LoadApp LoadApp);
			int SaveMainAnnual_plan(edata_annual_plan edata_annual_plan, String Code, LoadApp LoadApp);
			int SaveWorkRule(edata_workrule edata_workrule, String Code, LoadApp LoadApp);
			register_log getRegister_log(int id_refer);
			Asset_log getAssets_log(int id_refer);
			List<edata> getallEdataALLType(int process_id, customreport customreports, String type);
			
			
			List<Goods_receipt_fg> getAllGFG(int process_id, customreport customreports);
			List<Issue_fg> getAllIFG(int process_id, customreport customreports);
			List<Adjustment_fg> getAllAFG(int process_id, customreport customreports);
			List<Inventory_transfer_fg> getAllITFG(int process_id, customreport customreports);
			List<Issue_sk> getAllISK(int process_id, customreport customreports);
			List<Adjustment_sk> getAllASK(int process_id, customreport customreports);
			List<Inventory_transfer_sk> getAllITSK(int process_id, customreport customreports);
			List<Goods_receipt_sk> getAllGSK(int process_id, customreport customreports);
			
			
			
			List<edata> getDateFollow(int id_add);
			void SetDatePlanByDataId(int edata_id, String data_dateplan);
			void setLoadAll(int data_id, int loadall);
			List<edata> getall_USER_GROUP(int plan_id, String USER_GROUP,int idadd);
			void setProcess_id(int data_id, int process_id);
			
			
			List<process> findAllProcessAllowLooseLink();
			void saveLooseLink(Looselink Looselinks);
	
			
	
			List<Looselink> findAllLooseLinkByTypeAndTo(String to, int id);
			void RemoveLooselink(int id);
			Looselink  getLooselink(int id);
			registerkey getRegisterkey(String string);
			doc findDoc_in_set(String doc_type);
			doc findDoc(int id_refer);
			List<doc> getSameDoc(String document, int id_refer);
			void setSubTabledetails(String details,int id);
			
			
			void setRelationship(int processes_id, int link_to, String relationship, String data_transfer, String integrity, String current_status);
			List<process> getAllProcess(int id_refer, int id);
			void setFeels(process process);
			void saveprocessinfo(process process);
			List<process> getAllProcessBytype(int id_refer, int type);
			
			
			
			void save_extension(UseExtension extension);
			List<UseExtension> getallUseExtension(int id);
			List<Extension> getAllExtensionBytype(String type);
			void removeUseExtension(int id);
			UseExtension getUseExtension(int id);
			Extension getExtension(int id);
			void SaveExtensionParameter(ExtensionParameter Parameter);
			List<ExtensionParameter> getallExtensionParameterByuseextension(int id);
			void editIndexScope(process process);
			void editViewIndexScope(process process);
			List<registerkey> getRegisterkeyByDoc(String doc);
			List<registerkey> getRegisterkeyByListview(String Listview);
			List<edata> getAllByExtension(int processes_id, int id_refer, int extension_id);
			List<edata> geteAllbyUseextension(UseExtension useextension);
			List<ExtensionParameter> getallExtensionParameterAndeformfieldByuseextension(int id);
			void saveCalendar(Calendar calendar);
			void deleteCalendar(int eform_id);
			List<Management> getallManagement(int id);
			void save_management(Management management);
			Management getManagement(int id);
			void removeManagements(int id);
			List<TabManagement> getallTabManagement(int id);
			EdataReportbuildIn getallEdataReportbuildIn(int id);
			List<EdataAppManagement> getAllApp_Management(int id, int rv, TabManagement tabManagement,user users);
			List<Tabform> getTabForm(int form_id, int form_rv,List<eformfield> eformfields);
			void saveTabForm(Tabform tabform);
			Tabform getTabFormById(int id);
			void RemoveTabFormById(String id);
			

			int SaveSegmentation(Segmentation segmentation);
			List<Segmentation> getAllSegmentation(int process_id);
			Segmentation getSegmentationById(int process_id);
			void RemoveSegmentationById(int id);
			List<edata> getAllByExtension(Platform platform);
			
			
			List<Segmentation> getallSegmentationByTypeAndNotUse(int type,int edata_id);
			void editClosing_status(process process);
			DocumentEffect getDocumentEffect(int edata_id);
			registers getProfileByCode(String Code);
			List<edata> getallProcessesRelation(int process_id, String tag);
			
			
			
			int countLinK(String doc);
			int LinK(edata main, edata link);
			
			
			
			void saveEdatamainData(int data_id, int main_id, String entity);
	
			Relations getRelationsParent(int data_id);
			Relations getRelations(int data_id);
			List<Relations> getRelationschild(int data_id);
			void savesBatch(List<BatchEdata> batchEdatas,String tempId);
			List<edata> getAllSubData(int tempId);
			void deleteAllByTempId(String tempId);
			FullResult searchRegisters(String type,Platform Platform, int limit,int random,int limithead);
			

			
			void saveAudit(int dataId, String productCode, String string);
			FullResult getAllProducts(String type, String search, int limit,int limithead);
			FullResult auditRegisters(String type, int limit);
			FullResult auditProduct(String type, int limit);
			TabManagement getTabManagement(int id);
			void save_Measurement_rules(Measurement_rules rules);
			List<Measurement_rules> getAllMeasurement_rules();
			Measurement_rules getMeasurement_rules(int id);
			List<process> getAllProcess();
			List<Relations> getRelationsMain(int id, List<Integer> targetIds);
			int measurementRulesCount(int processing_id, int data_id, String closing_status);
			
			double measurementRulesOperation(int processing_id, int data_id, String closing_status_list, String column,
					int operation);
			List<TabManagement> getallTabManagementByTabProcessId(int process_id);
			Edata_maindata getEdatamainData(int data_id);
			int HaveRegister(edata_register edata_registers);
			
			
			void addProblem(String string, int data_id);
			void removeProblem(int data_id);
			int UpdateMainRegister(edata_register edata_registers, LoadApp LoadApp,String type);
		
			List<InputHiddenVisibility> getInputHiddenVisibility(int process_id, List<eformfield> eformfields);
			InputHiddenVisibility getInputHiddenVisibilityById(int id);
			void saveInputHiddenVisibility(InputHiddenVisibility visibility);
			void RemoveInputHiddenVisibilityById(String id);
			int HaveProduct(edata_product edata_products);

	
			int UpdateMainProduct(edata_product edata_products, LoadApp LoadApp, String type);
			product_master getProduct_masterCode(String Code);
			int UpdateMainProductStatus(edata_product edata_products, LoadApp LoadApp, String type);
			int UpdateMainRegisterStatus(edata_register edata_registers, LoadApp LoadApp, String type);
			Integer[] SaveMainRegister(edata_register edata_registers, String Code);
			int UpdateMainRegister(edata_register edata_registers, String type);
			void DeleteMainRegister(int id);
			FullResult groupingRegisters(String type, int limit, String grouping);
			FullResult groupingProduct(String type, int limit, String grouping);
			List<Segmentation> getallSegmentation();
			int getCountApp_Management(int id, int rv, TabManagement tabManagement);
			int deleteMeasurement_rules(int id);
			int updateExtensionRegister(registers register);

			FullResult searchRegistersSegmentation(Integer[] smid, int limit);
			
			
		
			void updateSegment(String segment, int total_call, int total_call_unique);
			int countExtensionAndIdrefer(int extension_id, int id_refer, int group);
			void saveConfigSubTable(process process);
			int copyEdataHeader(int oldEdataId);
			void copyEdataChildren(int oldEdataId, int newEdataId);
			void savesBatch(List<edata> toInsert, int TempId);
			void revision(int oldEdataId, int newEdataId);
			void saveConfigRequestRevision(process process);
			void removeFormPrint(int remove_id, int user_id);
			void saveFormPrint(FormPrint formPrint);
			
			
			List<FormPrint> getAllFormPrint(int process_id);
			UseformPrint getUseformPrint(int id_refer, int user_id);
			void saveUseformPrint(UseformPrint useformPrint);
			UseformPrint getUseformPrint(int id);
			FormPrint getFormPrint(int process_id);
			DocumentEffect getDocumentEffectByIdRefer(String type,int id_refer);
			List<Audit> getAllAudit(String Code);
			List<DocumentEffect> getAllDocumentEffect(int id_refer);
			List<Segmentation> getAllSegmentation(process process,user usersed);
			void saveSegmentationView(SetSegmentView process);
			SetSegmentView getSetSegmentView(int process_id, user usersed);
		
			
			int softDelete(int id, String soft_delete);
			int cancelDelete(int id);
			
			
			void setFollowUp(int process_id, int status);
			void setAcknowledge(int process_id, int status,int user_id);
			List<edata> getallProcessesRelation(int process_id, String tag, user user);
			void setReferenceProcess(String idapp, int id);
			List<Integer[]> SaveAllMainProfile(List<edata_register> edata_registerList, String Code);
		
			
			List<registers> getAllOldSys();
			List<registers> getAllRegister(String type);
			
			
			void SaveAllExtension(List<edata> edataList, int extensionId, int processesId);
			List<process> getAllProcessBytype(int type);
			List<Integer[]> SaveAllMainProduct(List<edata_product> edata_productList, String Code);
			List<Integer> SaveAllEdataAndGetIds(List<edata> edataList, int processId);
			List<edata> getEdataByOldSysIds(List<String> oldSysIds);
			void LinkBatch(List<edata> mainList, List<edata> linkList);
			List<product_master> getAllProdOldSys();
			void SaveAllEdataOnly(List<edata> edataList, int processId);
			
			
			
			List<edata> getallProcessesDraft(long process_id, int user_id);
			int countDraft(long process_id, int user_id);
			List<edata> getallProcessesReviewed(long process_id, int user_id);
			int countReviewed(long process_id, int user_id);
			List<edata> getallProcessesSends(long process_id, int user_id);
			int countSends(long process_id, int user_id);
			List<edata> getallProcessesPublished(long process_id, int user_id);
			List<edata> getallProcessesRefered(long process_id, int user_id);
			FullResult helpDeskDataRegisters(String type, int limit);
			int SaveRegisterDirect(edata_register edata_registers);
			
			edata_register geteData_registerById(int id);
			edata_product geteData_productById(int id);
			void saveConfigRenamefile(process process);
			List<edata> getallProcesses(long process_id, String column, String value);
			void saveEdatamainDataBatch(List<Object[]> batchArgs);
			void setTitleAndIdrefer(int id, String title, int id_refer);
			int getAllApp_ManagementCount(int process_id,TabManagement tabManagement, user usersed);
			int countDoc(String customerCode, int process_id, String source_param, int i);
			void updateLatestData(int eformId);
			void setEx20(String date, int id);
			List<edata> getAllByExtensionNper(Platform platform);
			int LinKName(edata main, edata link,int  id);
			Relations getTopRoot(int  id);
			void saveconfigExtension(process process);
			
			
	
			
			
			double countGamificationResult(int eformId, String conditionStatus, String userid,String startDate, String endDate,
					int extensionId, int isGlobal, String goalType);
			
			double sumGamificationResult(int eformId, String targetColumn, String conditionStatus, String userid,String startDate, String endDate, int extensionId, int isGlobal);
		
			int countExpiredDocs(int eformId, String username, String dateDue);
			List<UseExtension> getallUseExtension();
			
			double countCalendarEvents(String username, int targetStatus, String datePrefix);

			double countReceivedWithin24Hours(int eformId, String conditionStatus, String userid, String startDate,
					String endDate, int isGlobal);
			
			double countReceivedDocs(int eformId, String conditionStatus, String targetUserid, String startDate,
					String endDate);
			
			
			List<Map<String, Object>> executeDynamicQuery(String dynamicSql);
			List<Segmentation> getallSegmentationByid_addAndNotUse(int id_add);
			List<Measurement_rules> getAllMeasurementRulesByThisProcess(int id);
			List<Relations> getRelationsRecursiveMain(int myId, List<Integer> targetProcessIds);
			int countScheduledCalls(String memberIdsStr, String dateAdd);
			Map<String, Integer> getProblemStatistics(int segmentation_id, int extensionId, String groupByColumn);
			void updateSegmentationSummary(int segmentationId, String jsonSummary);
			UseExtension getActiveSegmentIssueConfig();
			Segmentation getSegmentationByEdataid(int edata_id);
			void setAutomatedGrading(process process);
			List<process> getReadyForAutoGradeProcesses();
			List<registers> getAllRegisterByType(String type, int limit, int offset);
		

			String GradeCheckExt(String extColumnName, int regId);
			void updateRegGrade(String newGrade, int regId);
			void renewfile(String name_file, int id);
			
			
			FullResult searchRegistersDirect(registers filter, int limit, int random);
			List<Map<String, Object>> getCustomerGradeSummary();
			List<Map<String, Object>> getGradeMovementSummary();

			List<Map<String, Object>> getCustomerAgingAndDocsSummary(int contactProcessId, int contactExtId,
					String contactDateCol, int foProcessId, int qtProcessId);
			Integer getMovementCount(String regId, String processId, String statusArray, int cycleDuration);
			List<Map<String, Object>> getContactFrequencySummary(int processId, int extensionId, String dateColumn,
					boolean isOldCustomer);
			void updateRegGradeBatch(List<Object[]> batchArgs);
			boolean hasEverClosedSale(String regId, String processId, String statusArray);
			void updateRegGradeAndPointBatch(List<Object[]> batchArgs);
			boolean hasExtension8(Integer regId);
			boolean hasClosedSaleInCurrentQuarter(String regId, String processId, String statusArray);
		
			
			/**
			 * ดึงสรุปลูกค้าที่เคยเปิด FO แยกตาม register_group, ปีของ FO, และช่วงเดือนที่ผ่านไปนับจากติดต่อล่าสุด
			 *
			 * @param foDocumentId  document_id ของ FO (เช่น 110)
			 * @param contactExtId  extension_id ของการติดต่อ (เช่น 5)
			 * @param yearCount     จำนวนปีย้อนหลัง (เช่น 5)
			 */
			List<Map<String, Object>> getFoCustomerSummaryByGroupAndYear(int foDocumentId, int contactExtId,
					int yearCount);
			/**
			 * ดึงรายชื่อลูกค้าจริงๆ ตาม register_group + fo_year + aging_bucket
			 * สำหรับ export CSV
			 *
			 * @param group        register_group
			 * @param foYear       ปีที่เปิด FO (เช่น 2025)
			 * @param agingBucket  aging bucket (<3M, 3-6M, 6-9M, 9-12M, >24M)
			 * @param foDocumentId document_id ของ FO (110)
			 * @param contactExtId extension_id ของการติดต่อ (5)
			 */
			List<Map<String, Object>> getFoCustomerDetailForExport(String group, int foYear, String agingBucket,
					int foDocumentId, int contactExtId);
			/**
			 * ดึงสรุปลูกค้า FO + QT แยกตาม register_groups, ปี, aging_bucket
			 * FO  → doc_type = 'FO', closing_status = '-'
			 * QT  → doc_type = 'QT', closing_status = 'ปิดได้' / 'ปิดไม่ได้'
			 *
			 * @param foDocumentId  document_id ของ FO (110)
			 * @param qtDocumentId  document_id ของ QT (113)
			 * @param contactExtId  extension_id ของการติดต่อ (5)
			 * @param yearCount     จำนวนปีย้อนหลัง (5)
			 */
			List<Map<String, Object>> getFoQtCustomerSummaryByGroupAndYear(int foDocumentId, int qtDocumentId,
					int contactExtId, int yearCount);
			
			
			void insertCallLog(String employee_name, String phone_number, long duration_seconds, String start_time,
					int idRefer);
			int findIdByPhone(String phone_number);
			
			
			
		

}


 


