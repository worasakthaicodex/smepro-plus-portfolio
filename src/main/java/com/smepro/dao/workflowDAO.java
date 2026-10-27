package com.smepro.dao;

import java.util.List;

import com.smepro.entity.CustomizeTableHeaders;
import com.smepro.models.Feed;
import com.smepro.models.Footer_calculation;
import com.smepro.models.Goal;
import com.smepro.models.Group_label;
import com.smepro.models.Links;
import com.smepro.models.LinksSetting;
import com.smepro.models.Rollup_calculation;
import com.smepro.models.Row_calculation;
import com.smepro.models.batch_code;
import com.smepro.models.batchformat;
import com.smepro.models.customreport;
import com.smepro.models.datalink;
import com.smepro.models.edata;
import com.smepro.models.fieldindex;
import com.smepro.models.flow;
import com.smepro.models.missed_uploads;
import com.smepro.models.process;
import com.smepro.models.report_pn;
import com.smepro.models.reportsetting;
import com.smepro.models.trace;
import com.smepro.models.upload_log;
import com.smepro.models.workflow;



public interface workflowDAO {
	
	List<workflow> getall();
	workflow getbyid(int id); 
	int delete(int id);
	int save(workflow workflows);
	int update(workflow workflows);
	List<fieldindex> getallindex(int id,String type);
	List<fieldindex> getallindex(int id,int id2,String type);
	
	batchformat getFormatByCode(String codeType);
	batch_code GenBatch(String autoIdKey);
	
	
	/**
	 * Method สำหรับupdate fieldindexs
	 * 
	 * 
	 * @param fieldindex,
     * @return int
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	int updatefieldindexs(fieldindex fieldindexs);
	
	
	
	/**
	 * Method สำหรับ save fieldindexs
	 * 
	 * 
	 * @param fieldindex,
     * @return void
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	void savefieldindexs(fieldindex fieldindexs);
	
	
	/**
	 * Method สำหรับ update workflows<br>
	 * ทำงานเเบบ Batch
	 * 
	 * @param List<workflow>,
     * @return  int[][]
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	 int[][] update(List<workflow> workflows, int batchSize);
	 
	 
	 /**
		 * Method สำหรับ ดึงค่า workflows<br>
		 * 
		 * @param int id,int id2
	     * @return  fieldindex
	     * @throws NULL
	     * @since V1.00
	     * @author Worasak
		 * 
		 */
	 fieldindex geteformfield(int workflow_id,int idcondition);
	 
	 
	 
	 
	 /**
		 * Method สำหรับ ดึงค่า Key<br>
		 * เช่น po,product_lot
		 * @param int workflow_id,String Key
	     * @return  fieldindex
	     * @throws NULL
	     * @since V1.00
	     * @author Worasak
		 * 
		 */
	 fieldindex getKey(int workflow_id,String Key);
	 
	 
	 
	 
	 /**
		 * Method สำหรับเช็คว่า eformid มีการ APP ไหม<br>
		 * @param int  id
	     * @return  workflow_id 
	     * @throws Empty workflow 
	     * @since V1.00
	     * @author Worasak
		 * 
		 */
	  workflow  getApp(int id);
	  
 	List<workflow> getallApp();
	datalink getDataLinkByIdrefer(int id);
	void save(datalink datalinks);
	void delink(int id);
	void delinkall(int id);
	void saveLinkAll(String datalink_name, String datalink_idrefer, int datalink_workflow, String key1, String key2,
			String key3);
	List<workflow> getallEform();
	List<datalink> getalldatalink(int id);
	datalink getDataLinkById(int id);
	List<workflow> getallProject();
	List<workflow> getall(int type);
	void Saveflow(flow flows);
	flow getLastFlow(int edata_id);
	List<flow> getAllFlow(int edata_id);
	List<workflow> getallEformTracking();
	void saveTrace(trace traces);
	trace getTrace(int id);
	void DelTrace(int id);
	List<trace> getAllTrace(int app_id);
	List<workflow> getallTraceability();
	void saveCustomreport(customreport customreports);
	List<customreport> getAllCustomreport(int app_id);
	customreport getCustomreport(int id);
	void DelCustomreport(int id);
	List<Goal> getAllGoal();
	void saveGoal(Goal goal);
	void DelGoal(int id);
	Goal getGoal(int id);
	List<Goal> getAllGoalByprocesses(int processes_id);
	void SetActiveGoal(int id, int status);
	Goal getFindGoal(int processes_id, String responsible);
	void UpdateCustomreport(customreport customreports);
	void saveReportsetting(reportsetting reportsetting);
	reportsetting getReportsetting(int id);
	void DelReportsetting(int id);
	List<reportsetting> getAllReportsetting(String string, String string2);
	List<workflow> getalls(int id);
	boolean getUsed(String type);
	List<workflow> getByFolder(String type);
	void UpdateCustomreports(customreport customreports);
	
	int[][] saveGoalBatch(List<Goal> goalAll, int batchSize);
	void StopGoal(int id);
	void delGoalAll(int id);
	List<Goal> getAllGoalByprocessesAnaTable(int processes_id, String tables);
	
	
	int countUpload_log(int id, String Responsible);
	int countMissed_uploads(int id, String Responsible);
	int countEdata(int idrefer, String Responsible);
	List<edata> findAllEdata(int idrefer, String Responsible);
	List<missed_uploads> findAllMissed_uploads(int id, String Responsible);
	List<upload_log> findAllUpload_log(int id, String Responsible);
	Goal getFindGoalPN(int processes_id, String responsible);
	int countGoalByprocessesAnaTableByMissed_uploads(int processes_id, String tables);

	
	
	void saveLinksSetting(LinksSetting linksSetting);
	void DelLinksSetting(LinksSetting linksSetting);
	LinksSetting getLinksSettingByidrefer(int id_refer);
	 
	
	void saveRow_calculation(Row_calculation Row_calculation);
	void DelRow_calculation(Row_calculation Row_calculation);
	Row_calculation getRow_calculationByid(int id_refer);
	List<Row_calculation> getRow_calculationByidrefer(int id_refer);
	
	
	void saveFooterCalculation(Footer_calculation Footer_calculation);
	void DelFooter_calculation(Footer_calculation Footer_calculation);
	Footer_calculation getFooter_calculationByid(int id_refer);
	List<Footer_calculation> getFooter_calculationByidrefer(int id_refer);
	
	void saveRollupCalculation(Rollup_calculation Rollup_calculation);
	void DelRollup_calculation(Rollup_calculation Rollup_calculation);
	Rollup_calculation getRollup_calculationByid(int id);
	List<Rollup_calculation> getRollup_calculationByidrefer(int id_refer);
	
	void saveLinks(Links Links);
	void DelLinks(Links Links);
	Links getLinksByid(int id);
	List<Links> getLinksByidrefer(int id_refer);
	
	
	void savebatchFeed(List<Feed> Feed);
	void DelAllFeedByidrefer(int id_refer);
	List<Feed> getAllFeedByidrefer(int id_refer);
	List<Links> getLinksBylink_to(int id_refer);
	process  getProcessByIndex(String Maindatacolumn);
	List<CustomizeTableHeaders> getCustomizeTableHeadersBytype(String string);
	List<Group_label> allGroupLabels();
	
	
	
	
	
	
}
