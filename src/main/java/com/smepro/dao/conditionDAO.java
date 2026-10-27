package com.smepro.dao;

import java.util.List;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Group_label;
import com.smepro.models.condition;
import com.smepro.models.control_steps;
import com.smepro.models.edata_stock;
import com.smepro.models.map_key;
import com.smepro.models.map_key2;
import com.smepro.models.map_key3;
import com.smepro.models.product_master;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.report_mapping;
import com.smepro.models.stock;
import com.smepro.models.workflow;


public interface conditionDAO {

	
	void save(condition conditions);
	void copy(int id,int idadd); 
	
	
	int update(condition conditions);
	int updatename(int id,String name);
	
	
	
	

	
	int delete(int id,int idadd);
	
	List<condition> getall();
	List<condition> getAllNotRefer();

	List<condition> getall(int id);
	List<condition> getalls(int id);
	
	
	condition getbyid(int id);
	condition getbyidandplan(int id);
	void setGroup(String condition_group,String id);
	void setGroup(String condition_group,int id1,int id2,int id3,int id4);
	
	
	condition getbysubandplan(int id);
	condition getbyids(int id);
	condition getbysub(int id);

	condition getbyid(int idadd,int status);
	int setnewid(int id);
	int setChart(condition conditions);
	int setReport(condition conditions);
	int setstatus(int status,int id,String name);
	int setstatus(int status,int id,int rv,String name);
	int numactivate();
	
	condition getbyport(int id,int port);
	condition getbyport(int id);
	
	
	boolean checkmap(int id,int userid);
	report_mapping getMap(int id,int userid);
	void saveMap(report_mapping report_mappings); 
	void updateMap(report_mapping report_mappings);
	

	

	
	map_key getMap_key(int field_formid,int sub);
	map_key getMap_key(int field_formid);
	map_key getMap_keysub(int field_formid);
	
	
	List<condition> getTrace();
	void setjointype(int id,int type);
	
	
	
	/**
	 * Method ดึงค่าที่เชื่อมโยง
	 * FIND_IN_SET(condition_id,?)
	 * 
	 * @param workflow,workflow_key2
     * @return List<condition>
     * @throws NULL
     * @since V1.00
     * @author Worasak
	 * 
	 */
	 List<condition> getallString(workflow workflows);
	 
	 
	 /**
		 * Method บัยทึก new_status
		 * ทำงานเเยย batch
		 * 
		 * 
		 * @param List<condition> conditions, int batchSize
	     * @return int[][] 
	     * @throws NULL
	     * @since V1.00
	     * @author Worasak
		 * 
		 */
	 int[][] updateStatus(List<condition> conditions, int batchSize);
	 
	 
	 
	 
	 /**
		 * Method ดึง condition ที่จัดเป็นระบบเเล้ว
		 * 
		 * 
		 * @param 
	     * @return List<condition>
	     * @throws NULL
	     * @since V1.00
	     * @author Worasak
		 * 
		 */	 
	 List<condition> getallGroup();
	 
	 
	 /**
		 * Method ดึง control_steps  ที่ควรมี
		 * 
		 * 
		 * @param  control_steps
	     * @return boolean
	     * @throws NULL
	     * @since V1.00
	     * @author Worasak
		 * 
		 */	 
	 	boolean getbycontrol_steps(String data,String type,int workflow);
	 
	 
	 	
	
	 	 /**
		 * Method setControl_steps  ที่เกี่ยวข้อง
		 * 
		 * 
		 * @param  control_steps control_steps
	     * @return 
	     * @throws NULL
	     * @since V1.00
	     * @author Worasak
		 * 
		 */	 
	 	void setControl_steps(control_steps control_steps,String type);
		List<condition> getAllbylink(int id);
		List<product_master> getAllProduct();
		control_steps getProduct(String product);

		List<control_steps> getAllPart(int step);
		List<control_steps> getAllMaterial(int step);

		void delControl_steps(int id, String type);
		control_steps getPD(int id);
		control_steps getMaterial(String material);
		control_steps getCustomer(String customer);

		control_steps getOrder_managements(edata_stock step);
		control_steps getByOrder_managementsId(int id);


		
		List<product_master> getAllProductCategory();
		List<control_steps> getAllOrder_management_batch();
		List<control_steps> getAllOrder_managements();
		List<production_list> getAllCustomer();
		control_steps getControl_steps(int id);
		void setControl_steps(control_steps control_steps);
		
		
		List<Group_label> getGroup_label();
		Group_label getGroup_labelbyId(int id);
		void saveGrouplabels(Group_label group_labels);
		void delGrouplabel(int id);

}










