package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
import com.smepro.rowmapper.CustomizeTableHeadersmapper;
import com.smepro.rowmapper.Feedmapper;
import com.smepro.rowmapper.Footer_calculationmapper;
import com.smepro.rowmapper.Group_labelmapper;
import com.smepro.rowmapper.LinksSettingmapper;
import com.smepro.rowmapper.Linksmapper;
import com.smepro.rowmapper.Rollup_calculationmapper;
import com.smepro.rowmapper.Row_calculationmapper;
import com.smepro.rowmapper.batch_codemappers;
import com.smepro.rowmapper.batch_formatmappers;
import com.smepro.rowmapper.conditionmapper;
import com.smepro.rowmapper.conditionmapper2;
import com.smepro.rowmapper.customreportmappers;
import com.smepro.rowmapper.datalinkmapper;
import com.smepro.rowmapper.edatamapper;
import com.smepro.rowmapper.fieldindexmapper;
import com.smepro.rowmapper.flowmapper;
import com.smepro.rowmapper.goalmapper;
import com.smepro.rowmapper.groupsmapper;
import com.smepro.rowmapper.missed_uploadsmapper;
import com.smepro.rowmapper.processmapper;
import com.smepro.rowmapper.report_pnmapper;
import com.smepro.rowmapper.reportsettingmappers;
import com.smepro.rowmapper.tracemappers;
import com.smepro.rowmapper.upload_logmapper;
import com.smepro.rowmapper.workflowmapper;


public class workflowDAOlmpl implements workflowDAO {

	private JdbcTemplate jdbcTemplate;
	
	public workflowDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	


	@Override
	public List<workflow> getall() {
		String sql = "SELECT * FROM workflow  order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new workflowmapper());
		return workflows;
	}

	
	
	@Override
	public List<workflow> getalls(int id) {
		String sql = "SELECT * FROM workflow  WHERE workflow_id != ?  order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new Object[] {id},new workflowmapper());
		return workflows;
	}
	
	@Override
	public List<workflow> getallTraceability() {
		String sql = "SELECT * FROM workflow  WHERE traceability_status= 1 order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new workflowmapper());
		return workflows;
	}
	
	@Override
	public List<workflow> getall(int type) {
		String sql = "SELECT * FROM workflow  WHERE workflow_type =? order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new Object[]{type},new workflowmapper());
		return workflows;
	}
	
	@Override
	public List<workflow> getallEform() {
		String sql = "SELECT * FROM workflow    order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new workflowmapper());
		return workflows;
	}
	
	@Override
	public List<workflow> getallEformTracking() {
		String sql = "SELECT * FROM workflow  WHERE tracking_status =1   order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new workflowmapper());
		return workflows;
	}
	
	
	@Override
	public List<workflow> getallApp() {
		String sql = "SELECT * FROM workflow WHERE workflow_type= 1  order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new workflowmapper());
		return workflows;
	}
	
	@Override
	public List<workflow> getallProject() {
		String sql = "SELECT * FROM workflow WHERE workflow_type= 2  order by workflow_id DESC";
		List<workflow> workflows  =  jdbcTemplate.query(sql,new workflowmapper());
		return workflows;
	}
	
	
	
	@Override
	public workflow getbyid(int id) {
		String sql = "SELECT * FROM workflow   where  workflow_id = ? limit 1  ";
		workflow workflows  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new workflowmapper());
		return workflows;
	}
	
	
	@Override
	public int delete(int id) {
		 String sql="delete from workflow where  workflow_id = ?";    
		    return jdbcTemplate.update(sql,new Object[]{id});
	}

	@Override
	public int save(workflow workflows) {
				String sql = "INSERT INTO workflow(workflow_name,workflow_note,workflow_dateadd,workflow_key1,workflow_key2,workflow_key3,workflow_key4,workflow_type,workflow_app,workflow_idadd) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
				
		          KeyHolder keyHolder = new GeneratedKeyHolder();
		
		          jdbcTemplate.update(connection -> {
		         	 
		              PreparedStatement ps = connection
		                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1, workflows.getWorkflow_name());
		                ps.setString(2, workflows.getWorkflow_note());
		                ps.setString(3, workflows.getWorkflow_dateadd());
		                ps.setString(4, workflows.getWorkflow_key1());
		                ps.setString(5, workflows.getWorkflow_key2());
		                ps.setString(6, workflows.getWorkflow_key3());
		                ps.setString(7, workflows.getWorkflow_key4());
		                ps.setInt(8, workflows.getWorkflow_type());
		                ps.setString(9, workflows.getWorkflow_app());
		                ps.setInt(10, workflows.getWorkflow_idadd());
		                return ps;
		             
		          }, keyHolder);			

		          System.out.println( keyHolder.getKey().intValue());
		         return  keyHolder.getKey().intValue();
		  
	}


	@Override
	public int update(workflow workflows) {
		Object[] workflowInfo = { 
				workflows.getWorkflow_name(),
				workflows.getWorkflow_note(),
				workflows.getWorkflow_dateadd(),
				workflows.getWorkflow_key1(),
				workflows.getWorkflow_key2(),
				workflows.getWorkflow_type(),
				workflows.getWorkflow_idadmin(),
				workflows.getWorkflow_folder(),
				workflows.getWorkflow_location(),
				workflows.getWorkflow_maindb(),
				workflows.getWorkflow_subdb(),		
				workflows.getWorkflow_subdb2(),	
				workflows.getWorkflow_subdb3(),		
				workflows.getTracking_step(),	
				workflows.getTracking_status(),	
				workflows.getTracking_type(),	
				workflows.getTracking_process_type(),	
				workflows.getTraceability_status(),	
				workflows.getTracking_main_step(),	
				workflows.getTracking_main_status(),	
				workflows.getTracking_main_type(),	
				workflows.getWorkflow_join(),	
				workflows.getWorkflow_id(),
				
				};
			String sql = "UPDATE workflow set workflow_name =?,workflow_note=?,workflow_dateadd=?,workflow_key1=?,workflow_key2=?,workflow_type=?"
					+ ",workflow_idadmin=? ,workflow_folder=?,workflow_location=?,workflow_maindb=?,workflow_subdb=?,workflow_subdb2=?,workflow_subdb3=?"
					+ ",tracking_step=?,tracking_status=?,tracking_type=?,tracking_process_type=?,traceability_status=?"
					+ ",tracking_main_step=?,tracking_main_status=?,tracking_main_type=?,workflow_join=?"
				+ " WHERE workflow_id =? ";
				return jdbcTemplate.update(sql,workflowInfo);
	}

	
	
	
	
	
	@Override
	public	List<fieldindex> getallindex(int id,String type){
		String sql = "SELECT * FROM eformfield_index  WHERE fieldindex_idrefer=? AND 	fieldindex_type=? order by field_index_idcondition ASC ";
		List<fieldindex> fieldindex  =  jdbcTemplate.query(sql,new Object[]{id,type},new fieldindexmapper());
		return fieldindex;
	}
	
	@Override
	public	List<fieldindex> getallindex(int id,int id2,String type){
		String sql = "SELECT * FROM eformfield_index  WHERE fieldindex_idrefer=? AND  field_index_idcondition2=? AND	fieldindex_type=? ";
		List<fieldindex> fieldindex  =  jdbcTemplate.query(sql,new Object[]{id,id2,type},new fieldindexmapper());
		return fieldindex;
	}
	
	@Override
	public	boolean getUsed(String type){
		String sql = "SELECT COUNT(*) FROM workflow  WHERE workflow_folder=? ";
		int count  =  jdbcTemplate.queryForObject(sql,new Object[]{type},Integer.class);
		return count > 0;
	}
	
	@Override
	public	List<workflow> getByFolder(String type){
		String sql = "SELECT * FROM workflow  WHERE workflow_folder=? ";
		List<workflow> data  = jdbcTemplate.query(sql,new Object[]{type},new workflowmapper());
		return data;
	}
	
	
	
	
	@Override
	public int updatefieldindexs(fieldindex fieldindexs) {
		Object[] fieldindexsInfo = { 
				fieldindexs.getFieldindex_type(),
				fieldindexs.getField_index_idcondition2(),
				fieldindexs.getGood_output(),
				fieldindexs.getDefective_output(),
				fieldindexs.getProduct(),
				fieldindexs.getLot(),
				fieldindexs.getManufacturer(),
				fieldindexs.getMaterial(),
				fieldindexs.getLot_material(),
				fieldindexs.getUnit(),
				fieldindexs.getIncoming_or_deliver(),
				fieldindexs.getDate(),
				fieldindexs.getPo(),
				fieldindexs.getTimestart(),
				fieldindexs.getTimeend(),
				fieldindexs.getCustomer(),
				fieldindexs.getWip(),
				fieldindexs.getLot_wip(),	
				fieldindexs.getPr(),
				fieldindexs.getVc(),
				fieldindexs.getPd(),
				fieldindexs.getBatch(),
				fieldindexs.getRegister(),
				fieldindexs.getFieldindex_id()

				};
			String sql = "UPDATE eformfield_index set "
					+ "fieldindex_type =?,field_index_idcondition2=?,good_output=?,defective_output=?,product=?"
					+ ",lot =?,manufacturer=?,material=?,lot_material=?,unit=? "
					+ ",incoming_or_deliver =?,date=?,po=?,timestart=?,timeend=?,customer=?,wip=?,lot_wip=?"
					+ ",pr=?,pd=?,vc=?,batch=?,register=?"
				+ " WHERE fieldindex_id =? ";
			
			
				return jdbcTemplate.update(sql,fieldindexsInfo);
				

	}
	
	
	@Override
	public void savefieldindexs(fieldindex fieldindexs) {
		Object[] fieldindexsInfo = { 
				fieldindexs.getFieldindex_type(),
				fieldindexs.getField_index_idcondition2(),
				fieldindexs.getGood_output(),
				fieldindexs.getDefective_output(),
				fieldindexs.getProduct(),
				fieldindexs.getLot(),
				fieldindexs.getManufacturer(),
				fieldindexs.getMaterial(),
				fieldindexs.getLot_material(),
				fieldindexs.getUnit(),
				fieldindexs.getIncoming_or_deliver(),
				fieldindexs.getDate(),
				fieldindexs.getPo(),
				fieldindexs.getTimestart(),
				fieldindexs.getTimeend(),
				fieldindexs.getCustomer(),
				fieldindexs.getFieldindex_idrefer(),
				fieldindexs.getField_index_idcondition(),
				fieldindexs.getPr(),
				fieldindexs.getVc(),
				fieldindexs.getPd(),
				fieldindexs.getBatch(),
				};
			String sql = "INSERT INTO eformfield_index  "
					+ "( fieldindex_type,field_index_idcondition2,good_output,defective_output,product"
					+ ",lot,manufacturer,material,lot_material,unit"
					+ ",incoming_or_deliver,date,po,timestart,timeend,customer,fieldindex_idrefer,field_index_idcondition,pr,pd,vc,batch) "
				+ " VALUES("
				+ "?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?,?,?"
				+ ") ";
				 jdbcTemplate.update(sql,fieldindexsInfo);
				
				 
	}
	
	
	
	@Override
	public int[][] update(List<workflow> workflows, int batchSize) {

		String sql = "UPDATE workflow set workflow_name =?,workflow_note=?,workflow_dateadd=?,workflow_key1=?,workflow_key2=?,workflow_key3=?,workflow_key4=? "
				+ " WHERE workflow_id =? ";
		
        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,workflows,batchSize,
                new ParameterizedPreparedStatementSetter<workflow>() {
                    public void setValues(PreparedStatement ps, workflow argument) 
						throws SQLException {
                        ps.setString(1, argument.getWorkflow_name());
                        ps.setString(2, argument.getWorkflow_note());
                        ps.setString(3, argument.getWorkflow_dateadd());
                        ps.setString(4, argument.getWorkflow_key1());
                        ps.setString(5, argument.getWorkflow_key2());
                        ps.setString(6, argument.getWorkflow_key3());
                        ps.setString(7, argument.getWorkflow_key4());
                        ps.setInt(8, argument.getWorkflow_id());
          
                    }
                });
        return updateCounts;

    }
	
	
	@Override
	public	fieldindex geteformfield(int workflow_id,int idcondition){
	
		
		   try {
			   System.out.println("fieldindex workflow_id  " + workflow_id);
			   System.out.println(" fieldindex idcondition " + idcondition);
			   
				String sql = "SELECT datalink.datalink_name AS fieldindex_type,eformfield_index.* "
						+ "FROM eformfield_index  LEFT JOIN datalink ON datalink.datalink_workflow = workflow.workflow_id"
						+ "  WHERE eformfield_index.fieldindex_idrefer=? AND  eformfield_index.field_index_idcondition=? limit 1 ";
				fieldindex fieldindex  =  jdbcTemplate.queryForObject(sql,new Object[]{workflow_id,idcondition},new fieldindexmapper());
				return fieldindex;
			 
			 }catch(EmptyResultDataAccessException e) {
				 fieldindex fieldindex  = new fieldindex();
				 return fieldindex;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		    
		
	}
	
	
	
	
	@Override
	public fieldindex getKey(int workflow_id,String Key) {
		String sql = "";
		if(Key.equals("po")) {
			sql = "SELECT * FROM eformfield_index   where  fieldindex_idrefer=? AND po > 0  order by field_index_idcondition DESC limit 1   ";
		}if(Key.equals("product_lot")) {
			sql = "SELECT * FROM eformfield_index   where  fieldindex_idrefer=? AND product > 0   order by field_index_idcondition DESC  limit 1  ";
		}else {
			sql = "SELECT * FROM eformfield_index   where  fieldindex_idrefer=? AND pd > 0   order by field_index_idcondition DESC  limit 1  ";
		}
		
		  try {
				fieldindex fieldindexs  =  jdbcTemplate.queryForObject(sql,new Object[]{workflow_id},new fieldindexmapper());
				return fieldindexs;
			 
			 }catch(EmptyResultDataAccessException e) {
				 fieldindex fieldindex  = new fieldindex();
				 return fieldindex;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
	}
	
	
	@Override
	public workflow getApp(int id) {
		
		System.out.println("id---> "+id);
		

		try {
			String sql = "SELECT * FROM workflow LEFT JOIN datalink ON datalink.datalink_workflow = workflow.workflow_id"
					+ "   WHERE datalink.datalink_idrefer =?   limit 1   ";
			workflow workflows  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new workflowmapper());
			return workflows;
			
		}catch(EmptyResultDataAccessException e) {
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
	}
	
	
	@Override
	public	datalink getDataLinkByIdrefer(int id){
	
		
		try {
			String sql = "SELECT * FROM datalink  WHERE datalink_workflow =? order by datalink_id  DESC limit 1   ";
			datalink datalinks  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new datalinkmapper());
			return datalinks;
			
		}catch(EmptyResultDataAccessException e) {
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
		
		
	}
	
	@Override
	public	datalink getDataLinkById(int id){
	
		
		try {
			String sql = "SELECT * FROM datalink  WHERE datalink_idrefer =? order by datalink_id  DESC limit 1   ";
			datalink datalinks  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new datalinkmapper());
			return datalinks;
			
		}catch(EmptyResultDataAccessException e) {
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
		
		
	}
	
	
	
	
		@Override
		public	void save(datalink datalinks){
		
			Object[] datalinksinfo =  {
				datalinks.getDatalink_idrefer(),
				datalinks.getDatalink_workflow(),
				datalinks.getDatalink_name(),
				datalinks.getKey1(),
				datalinks.getKey2(),
				datalinks.getKey3(),
			};
			
			
			String sql ="";
					if(datalinks.getDatalink_id() > 0 ) {
						sql = "UPDATE  datalink SET datalink_idrefer=?,datalink_workflow=?,datalink_name=?,key1=?,key2=?,key3=?   WHERE datalink_id =  "+datalinks.getDatalink_id();
					}else {
						sql = "INSERT INTO  datalink (datalink_idrefer,datalink_workflow,datalink_name,key1,key2,key3)  VALUES(?,?,?,?,?,?) ";
					}
			jdbcTemplate.update(sql,datalinksinfo);

	}
	
		@Override
		public	void delink(int id){
		
			String sql = "delete from datalink where datalink_idrefer=? ";
			jdbcTemplate.update(sql,new Object[]{id});

		}
		@Override
		public	void delinkall(int id){
		
			String sql = "delete from datalink where datalink_workflow=? ";
			jdbcTemplate.update(sql,new Object[]{id});

		}
		
		
		@Override
		public void saveLinkAll(String datalink_name,String datalink_idrefer,int datalink_workflow,String key1,String key2,String key3) {

			
			String[] datalink_names = datalink_name.split(",");
			String[] key1s = key1.split(",");
			String[] key2s = key2.split(",");
			String[] key3s = key3.split(",");
			String[] datalink_idrefers = datalink_idrefer.split(",");
			
			
			String sql = "INSERT INTO  datalink (datalink_idrefer,datalink_workflow,datalink_name,key1,key2,key3)  VALUES(?,?,?,?,?,?) ";
		
				jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
					
		            @Override
		            public void setValues(PreparedStatement ps, int i )
		                throws SQLException {
		             
		              ps.setInt(1,Integer.parseInt(datalink_idrefers[i]));  
		              ps.setInt(2,datalink_workflow);  
		              ps.setString(3,datalink_names[i]);
		              ps.setString(4,key1s[i]);
		              ps.setString(5,key1s[i]);
		              ps.setString(6,key1s[i]);
		            }

		            @Override
		            public int getBatchSize() {
		                return datalink_names.length;
		            }
		        });
	 

	    }
	
		
		
		@Override
		public List<datalink> getalldatalink(int id) {
			String sql = "SELECT * FROM datalink   WHERE datalink_workflow= ?  ";
			List<datalink> datalinks  =  jdbcTemplate.query(sql,new Object[]{id},new datalinkmapper());
			return datalinks;
		}
		
		

		
		@Override
		public	void Saveflow(flow flows){
		
				Object[] flowinfo =  {
						flows.getEdata_id(),
						 flows.getFlow_send(),
						 flows.getFlow_to(),
						 flows.getFlow_comment(),
						 flows.getFlow_date(),
						 flows.getFlow_step(),
						 flows.getFlow_type(),
				};
				

				String sql = "INSERT INTO  flow (edata_id,flow_send,flow_to,flow_comment,flow_date,flow_step,flow_type)  VALUES(?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,flowinfo);

		}
		
		
		@Override
		public List<flow> getAllFlow(int edata_id) {
			String sql = "SELECT * FROM flow   WHERE edata_id= ?  ";
			List<flow> flows  =  jdbcTemplate.query(sql,new Object[]{edata_id},new flowmapper());
			return flows;
		}
		
		@Override
		public flow getLastFlow(int edata_id) {
			
			
		
			
			try {
				System.out.println("edata_id "+edata_id);

				String sql = "SELECT * FROM flow   WHERE edata_id= ? AND flow_type ='Flow' Order by id DESC  limit 1  ";
				flow flows  =  jdbcTemplate.queryForObject(sql,new Object[]{edata_id},new flowmapper());
				return flows;
				
			}catch(EmptyResultDataAccessException e) {
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		}
		
		

		@Override
		public void saveTrace(trace traces) {
			
			Object[] tracesinfo =  {
					traces.getTrace_name(),
					traces.getTrace_description(),
					traces.getTrace_join(),
					traces.getTrace_app(),
					traces.getTrace_processes(),
					traces.getTrace_type(),	
					traces.getTrace_key(),	
			};

			if(traces.getId() > 0) {
				
			String sql = "UPDATE trace SET  trace_name=?,trace_description=?,trace_join=?,trace_app=?,trace_processes=?,trace_type=?,trace_key=? "
					+ "WHERE id = "+traces.getId();
			jdbcTemplate.update(sql,tracesinfo);
			
			}else {
				
				String sql = "INSERT INTO  trace (trace_name,trace_description,trace_join,trace_app,trace_processes,trace_type,trace_key) "
						+ " VALUES (?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,tracesinfo);
				
			}
			

		}
		
		
		/*
		@Override
		public void saveReportsetting(reportsetting reportsetting) {
			
			
		}
		*/
		
		
		
		
		@Override
		public List<trace> getAllTrace(int app_id) {
			String sql = "SELECT * FROM trace WHERE trace_app =? ";
			List<trace> traces  =  jdbcTemplate.query(sql,new Object[]{app_id},new tracemappers());
			return traces;
		}
		
		
		@Override
		public trace getTrace(int id) {
			String sql = "SELECT * FROM trace WHERE id =  ?  ";
			trace traces  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new tracemappers());
			return traces;
		}
		
		
		@Override
		public void DelTrace(int id) {
			String sql = "DELETE  FROM trace WHERE id =  ?  ";
			jdbcTemplate.update(sql,new Object[]{id});
			
		}
		
		
		
		
		@Override
		public void saveCustomreport(customreport customreports) {
			
			Object[] reportInfo = {
					customreports.getReport_name(),
					customreports.getReport_description(),
					customreports.getReport_table(),
					customreports.getReport_app(),
					customreports.getType(),
					customreports.getLevel(),
					customreports.getAnalysis(),
					customreports.getFilter(),
					customreports.getAxis_x(),
					customreports.getAxis_y(),
					customreports.getStatus(),
					customreports.getFilter_data(),
					customreports.getDate_group(),
					customreports.getFilter_date(), 
					
					customreports.getCondition_choice1(),  
					customreports.getFilter_column1(),  
					customreports.getOperation1(),  
					customreports.getCondition_value1(),  
				    
					customreports.getCondition_choice2(),  
					customreports.getFilter_column2(),  
					customreports.getOperation2(),  
					customreports.getCondition_value2(),  
					
					customreports.getCondition_choice3(),  
					customreports.getFilter_column3(),  
					customreports.getOperation3(),  
					customreports.getCondition_value3(),  
					
					customreports.getBinning(),  
					customreports.getBinning_range(),  
					customreports.getBinning_title(),  
					customreports.getBinning_group(),  
					customreports.getBinning_date(),  
					customreports.getBinning_operation(),  
					customreports.getBinning_value(),  
					customreports.getTheme(),  
				};
			
			
		
			
			

				if (customreports.getId() > 0) {
				    String sql = "UPDATE customreport SET report_name=?, report_description=?, report_table=?, report_app=?, type=?, level=?, analysis=?, filter=?, axis_x=?, axis_y=?"
				    		+ ", status=?,filter_data=?,date_group=?,filter_date=?"
				    		+ ",condition_choice1=?,filter_column1=?,operation1=?,condition_value1=?"
				    		+ ",condition_choice2=?,filter_column2=?,operation2=?,condition_value2=?"
				    		+ ",condition_choice3=?,filter_column3=?,operation3=?,condition_value3=?"
				    		+ ",binning=?,binning_range=?,binning_title=?,binning_group=?,binning_date=?,binning_operation=?,binning_value=?,theme=? "
				               + "WHERE id = " + customreports.getId();
				    jdbcTemplate.update(sql, reportInfo);
				} else {
				    String sql = "INSERT INTO customreport (report_name, report_description, report_table, report_app, type, level, analysis, filter, axis_x, axis_y"
				    		+ ", status,filter_data,date_group,filter_date"
				    		+ ",condition_choice1,filter_column1,operation1,condition_value1"
				    		+ ",condition_choice2,filter_column2,operation2,condition_value2"
				    		+ ",condition_choice3,filter_column3,operation3,condition_value3"
				    		+ ",binning,binning_range,binning_title,binning_group,binning_date,binning_operation,binning_value,theme) "
				               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				               + ", ?, ?, ?, ?"
				               + ", ?, ?, ?, ?"
				               + ", ?, ?, ?, ?"
				               + ",?,?,?,?,?,?,?,?)";
				    jdbcTemplate.update(sql, reportInfo);
				}
		}
		
		
		
		@Override
		public void UpdateCustomreport(customreport customreports) {
			
			Object[] reportInfo = {
					customreports.getReport_name(),
					customreports.getReport_description(),
					customreports.getReport_table(),
					customreports.getReport_app(),
					customreports.getStatus(),
				};
			
				    String sql = "UPDATE customreport SET report_name=?, report_description=?, report_table=?, report_app=?,status=? WHERE id = " + customreports.getId();
				    jdbcTemplate.update(sql, reportInfo);
				} 
			
		@Override
		public void UpdateCustomreports(customreport customreports) {
			
			Object[] reportInfo = {
					customreports.getPlanId(),
					customreports.getDepartment(),
					customreports.getMachineryId(),
		
				};
				    String sql = "UPDATE customreport SET planId=?, department=?, machineryId=?  WHERE id = " + customreports.getId();
				    jdbcTemplate.update(sql, reportInfo);
				} 

		
		@Override
		public List<customreport> getAllCustomreport(int app_id) {
			String sql = "SELECT * FROM customreport WHERE report_app =? ";
			List<customreport> report  =  jdbcTemplate.query(sql,new Object[]{app_id},new customreportmappers());
			return report;
		}
		
		
		@Override
		public customreport getCustomreport(int id) {
			String sql = "SELECT * FROM customreport WHERE id =  ?  ";
			customreport report  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new customreportmappers());
			return report;
		}
		
		
		@Override
		public void DelCustomreport(int id) {
			String sql = "DELETE  FROM customreport WHERE id =  ?  ";
			jdbcTemplate.update(sql,new Object[]{id});
			
		}
		
		
		@Override
		public List<Goal>  getAllGoal() {
			String sql = "SELECT * FROM goal  where status = 0  or status IS NULL  ";
			List<Goal> Goal  =  jdbcTemplate.query(sql,new goalmapper());
			return Goal;
		}
		
		@Override
		public List<Goal>  getAllGoalByprocesses(int processes_id) {
			String sql = "SELECT * FROM goal  where processes_id = ?  ";
			List<Goal> Goal  =  jdbcTemplate.query(sql,new Object[] {processes_id},new goalmapper());
			return Goal;
		}
		
		
		@Override
		public Goal getGoal(int id) {
			String sql = "SELECT * FROM goal WHERE id =  ?   ";
			Goal Goal  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new goalmapper());
			return Goal;
		}
		
		
		@Override
		public Goal getFindGoal(int processes_id,String responsible) {
		
			
		    try {
		    	String sql = "SELECT * FROM goal WHERE processes_id =  ? AND ( responsible = ?  OR responsible IS NULL  ) limit 1    ";
				Goal Goal  =  jdbcTemplate.queryForObject(sql,new Object[]{processes_id,responsible},new goalmapper());
				return Goal;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		    
			
			
		}
		@Override
		public Goal getFindGoalPN(int processes_id,String responsible) {
		
			
		    try {
		    	String sql = "SELECT * FROM goal WHERE processes_id =  ? AND ( responsible=?  OR responsible IS NULL  ) AND tables = 'upload_pn'  limit 1    ";
				Goal Goal  =  jdbcTemplate.queryForObject(sql,new Object[]{processes_id,responsible},new goalmapper());
				return Goal;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		    
			
			
		}
		
		
		@Override
		public void DelGoal(int id) {
			String sql = "DELETE  FROM goal WHERE id =  ?  ";
			jdbcTemplate.update(sql,new Object[]{id});
			
		}
		
		@Override
		public void SetActiveGoal(int id,int status) {
	        String sql = "UPDATE goal SET status  = ?   WHERE id = ? ";
		        jdbcTemplate.update(sql,new Object[]{status,id});
		}
		
		
		
		@Override
		public void saveGoal(Goal goal) { 
		    // Prepare the goal information to update or insert into the database
		    Object[] goalInfo = {
		        goal.getName(),
		        goal.getProcesses_id(),
		        goal.getReference_category(),
		        goal.getReference_category2(),
		        goal.getReference_category3(),
		        goal.getActivity_type(),
		        goal.getQuantity_per_action(),
		        goal.getTaskBehaviortypes(),
		        goal.getFrequency(),
		        goal.getAlert_before_days(),
		        goal.getSetto(),
		        goal.getFile_tag(),
		        goal.getStart_time(),
		        goal.getEnd_time(),
		        goal.getResponsible(),
		        goal.getTables()
		    };
		    
		    // Check if the goal already exists (if the ID is greater than 0)
		    if (goal.getId() > 0) {
		        // Update the existing goal in the database
		        String sql = "UPDATE goal SET name=?, processes_id=?, reference_category=?, reference_category2=?, reference_category3=?, "
		            + "activity_type=?, quantity_per_action=?, taskBehaviortypes=?, frequency=?, alert_before_days=?, setto=?, file_tag=?, "
		            + "start_time=?, end_time=?, responsible=?, tables=? WHERE id = "+goal.getId();
		        jdbcTemplate.update(sql, goalInfo);
		    } else {
		        // Insert a new goal into the database
		        String sql = "INSERT INTO goal (name, processes_id, reference_category, reference_category2, reference_category3, "
		            + "activity_type, quantity_per_action, taskBehaviortypes, frequency, alert_before_days, setto, file_tag, "
		            + "start_time, end_time, responsible, tables) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        jdbcTemplate.update(sql, goalInfo);
		    }
		}



		@Override
		public batchformat getFormatByCode(String codeType) {

			String sql = "SELECT * FROM batch_format WHERE code_type =? ";
			batchformat batch_formats  =  jdbcTemplate.queryForObject(sql,new Object[]{codeType},new batch_formatmappers());
			return batch_formats;
		}



		@Override
		public batch_code GenBatch(String autoIdKey) {
			
		    // กรณีไม่มีข้อมูล - สร้างใหม่ตาม logic เดิม
            // ค้นหา doc_autoid ล่าสุดของ type นั้น
            String findSql = "SELECT MAX(auto_id) FROM batch_code WHERE prefix = ?";
            Integer lastAutoId = jdbcTemplate.queryForObject(findSql, new Object[]{autoIdKey}, Integer.class);

            // ถ้าไม่มีค่าให้เริ่มที่ 1
            int newAutoId = (lastAutoId != null) ? lastAutoId + 1 : 1;


	        
	        String insertSql = "INSERT INTO batch_code (prefix,auto_id) VALUES (?, ?)";
	          KeyHolder keyHolder = new GeneratedKeyHolder();
	
	          jdbcTemplate.update(connection -> {
	              PreparedStatement ps = connection
	                .prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, autoIdKey);
	                ps.setInt(2, newAutoId);
	                return ps;
	             
	          }, keyHolder);			

	         int newid =   keyHolder.getKey().intValue();
			 String sql = "SELECT * FROM batch_code WHERE id =? ";
	         batch_code batch_codes  =  jdbcTemplate.queryForObject(sql,new Object[]{newid},new batch_codemappers());
			return batch_codes;

		}



		@Override
		public void saveReportsetting(reportsetting reportsetting) {
			
		    Object[] reportsettingInfo = {
		    		
		    		reportsetting.getReportsetting_id_refer(),
		    		reportsetting.getReportsetting_pd(),
		    		reportsetting.getReportsetting_batch(),
		    		reportsetting.getReportsetting_department(),
		    		reportsetting.getReportsetting_list(),
		    		reportsetting.getReportsetting_employee(),
			        reportsetting.getReportsetting_machine(),
			        reportsetting.getReportsetting_date_wip(),
			        reportsetting.getReportsetting_orders(),
			        reportsetting.getReportsetting_received_today(),
			        reportsetting.getReportsetting_paid_today(),
			        reportsetting.getReportsetting_form_name(),
			        reportsetting.getReportsetting_type(),
			        reportsetting.getReportsetting_tables(),
			        reportsetting.getReportsetting_data(),
			        reportsetting.getReportsetting_line(),
			        
			        reportsetting.getReportsetting_downtime(),
			        reportsetting.getReportsetting_planned_time(),
			        reportsetting.getReportsetting_speed(),
			        reportsetting.getReportsetting_total_count(),
			        reportsetting.getReportsetting_performance(),
			        reportsetting.getReportsetting_good_count(),
			        
			
			        reportsetting.getReportsetting_stock_name(),
			        reportsetting.getReportsetting_stock_code(),
			        reportsetting.getReportsetting_total_system(),
			        reportsetting.getReportsetting_total_real(),
			        reportsetting.getReportsetting_drawer(),
			        reportsetting.getReportsetting_payer(),
			    };
		

			
		    
			    // Check if the goal already exists (if the ID is greater than 0)
			    if (reportsetting.getReportsetting_id() > 0) {
			        // Update the existing goal in the database
			        String sql = "UPDATE reportsetting    SET   reportsetting_id_refer=?,reportsetting_pd=?,reportsetting_batch=?,reportsetting_department=?,reportsetting_list=?"
			        		+ ",reportsetting_employee=?,reportsetting_machine=?,reportsetting_date_wip=?,reportsetting_orders=?,reportsetting_received_today=?"
			        		+ ",reportsetting_paid_today=?,reportsetting_form_name=?,reportsetting_type=?,reportsetting_tables=?,reportsetting_data=?,reportsetting_line=?"
			        		+ " ,reportsetting_downtime=?,reportsetting_planned_time=?,reportsetting_speed=?,reportsetting_total_count=?,reportsetting_performance=?,reportsetting_good_count=?"
			        		+ ",reportsetting_stock_name=?,reportsetting_stock_code=?,reportsetting_total_system=?,reportsetting_total_real=?,reportsetting_drawer=?,reportsetting_payer=?"
			        		+ " WHERE reportsetting_id = "+reportsetting.getReportsetting_id();
			        jdbcTemplate.update(sql, reportsettingInfo);
			    } else {
			        // Insert a new goal into the database
			        String sql = "INSERT INTO reportsetting (    reportsetting_id_refer,reportsetting_pd,reportsetting_batch,reportsetting_department,reportsetting_list,reportsetting_employee"
			        		+ ",reportsetting_machine,reportsetting_date_wip,reportsetting_orders,reportsetting_received_today,reportsetting_paid_today,reportsetting_form_name"
			        		+ ",reportsetting_type,reportsetting_tables,reportsetting_data,reportsetting_line"
			        		+ ",reportsetting_downtime,reportsetting_planned_time,reportsetting_speed,reportsetting_total_count,reportsetting_performance,reportsetting_good_count"
			        		+ ",	reportsetting_stock_name,reportsetting_stock_code,reportsetting_total_system,reportsetting_total_real,reportsetting_drawer,reportsetting_payer) "
			            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			        jdbcTemplate.update(sql, reportsettingInfo);
			    }
			
		}



		@Override
		public reportsetting getReportsetting(int id) {
			String sql = "SELECT * FROM reportsetting WHERE reportsetting_id =? ";
			reportsetting data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new reportsettingmappers());
			return data;
		}



		@Override
		public void DelReportsetting(int id) {
			String sql = "DELETE  FROM reportsetting WHERE reportsetting_id =  ?  ";
			jdbcTemplate.update(sql,new Object[]{id});
			
		}



		@Override
		public List<reportsetting> getAllReportsetting(String tables, String type) {
			String sql = "SELECT * FROM reportsetting  where reportsetting_tables = ? and reportsetting_type=?  ";
			List<reportsetting> data  =  jdbcTemplate.query(sql,new Object[] {tables,type},new reportsettingmappers());
			return data;
		}
		
		@Override
		public void StopGoal(int id) {
			String sql2 = "UPDATE goal SET  status = 0   WHERE  processes_id =  ? AND tables= 'upload_pn' ";
			jdbcTemplate.update(sql2,new Object[]{id});
		}
		
		@Override
		public void delGoalAll(int id) {
			String sql = "DELETE  FROM goal  WHERE  processes_id =  ? AND tables= 'upload_pn'  ";
			jdbcTemplate.update(sql,new Object[]{id});
			
		}

		@Override
		public List<Goal> getAllGoalByprocessesAnaTable(int processes_id,String tables) {
			String sql = "SELECT *   FROM goal  WHERE  processes_id =  ? AND tables= ?  ";
		    List<Goal> GoalList = 	jdbcTemplate.query(sql,new Object[]{processes_id,tables},new goalmapper());
			return GoalList;
			
		}

		@Override
		public int countGoalByprocessesAnaTableByMissed_uploads(int processes_id,String tables) {
			String sql = "SELECT  COUNT(missed_uploads.goal_id)   FROM missed_uploads LEFT JOIN goal ON missed_uploads.goal_id = goal.id"
					+ "  WHERE  goal.processes_id =  ? AND goal.tables= ?  ";
			return 	jdbcTemplate.queryForObject(sql,new Object[]{processes_id,tables},Integer.class);

		}
		
		
		@Override
		public int[][] saveGoalBatch(List<Goal> goalAll, int batchSize) {

	        String sql = "INSERT INTO goal (name, processes_id, reference_category, reference_category2, reference_category3, "
		            + "activity_type, quantity_per_action, taskBehaviortypes, frequency, alert_before_days, setto, file_tag, "
		            + "start_time, end_time, responsible, tables) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
	        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,goalAll,batchSize,
	                new ParameterizedPreparedStatementSetter<Goal>() {
	                    public void setValues(PreparedStatement ps, Goal argument) 
							throws SQLException {
	                        ps.setString(1, argument.getName());
	                        ps.setLong(2, argument.getProcesses_id());
	                        ps.setString(3, argument.getReference_category());
	                        ps.setString(4, argument.getReference_category2());
	                        ps.setString(5, argument.getReference_category3());
	                        ps.setString(6, argument.getActivity_type());
	                        ps.setLong(7, argument.getQuantity_per_action());
	                        ps.setString(8, argument.getTaskBehaviortypes());
	                        ps.setString(9, argument.getFrequency());
	                        ps.setLong(10, argument.getAlert_before_days());
	                        ps.setString(11, argument.getSetto());
	                        ps.setString(12, argument.getFile_tag());
	                        ps.setString(13, argument.getStart_time());
	                        ps.setString(14, argument.getEnd_time());
	                        ps.setString(15, argument.getResponsible());
	                        ps.setString(16, argument.getTables());
	                    }
	                });
	        return updateCounts;

	    }
		
		@Override
		public int  countUpload_log(int id,String Responsible) {
			String sql = "SELECT COUNT(*)   FROM upload_log  WHERE  goal_id =  ? AND user_name= ?  ";
			return	jdbcTemplate.queryForObject(sql,new Object[]{id,Responsible}, Integer.class);
	
			
		}
		@Override
		public int  countMissed_uploads(int id,String Responsible) {
			String sql = "SELECT COUNT(*)   FROM missed_uploads  WHERE  goal_id =  ? AND user_name= ?  ";
			return	jdbcTemplate.queryForObject(sql,new Object[]{id,Responsible},Integer.class);
			
			
		}
	
		
		@Override
		public List<upload_log>  findAllUpload_log(int id,String Responsible) {
			String sql = "SELECT *   FROM upload_log  WHERE  goal_id =  ? AND user_name= ?  ";
			 List<upload_log> upload_logList =	jdbcTemplate.query(sql,new Object[]{id,Responsible},  new upload_logmapper());
			 return upload_logList;
			
		}
		@Override
		public List<missed_uploads>   findAllMissed_uploads(int id,String Responsible) {
			String sql = "SELECT *  FROM missed_uploads  WHERE  goal_id =  ? AND user_name= ?  ";
			List<missed_uploads>	missed_uploadsList = jdbcTemplate.query(sql,new Object[]{id,Responsible}, new missed_uploadsmapper());

			 return missed_uploadsList;
		}
		
		@Override
		public List<edata>    findAllEdata(int idrefer,String Responsible) {
			
		    	String sql = " SELECT * FROM edata  WHERE   data_planid =?   AND  data_nameadd =?  ";
		    	List<edata>	edataList =  jdbcTemplate.query(sql,new Object[]{idrefer,Responsible}, new edatamapper());
				 return edataList;

		}
		
		
		
		@Override
		public int  countEdata(int idrefer,String Responsible) {
			
			
		    try {
		    	String sql = " SELECT COUNT(*) FROM edata  WHERE  data_planid =?   AND  data_nameadd =?  ";
				return	jdbcTemplate.queryForObject(sql,new Object[]{idrefer,Responsible},Integer.class);
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return 0;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 return 0;
			   
			 }catch(Exception e) {
			       
				 return 0;
			   
			 }    
		    
			
			
		}
		
		
		
		
		

		@Override
		public void saveRollupCalculation(Rollup_calculation rollup) {

		    Object[] rollupInfo = {
		        rollup.getId_refer(),
		        rollup.getRollup_name(),

		        rollup.getCondition_column1(),
		        rollup.getCondition_operation1(),
		        rollup.getCondition_as_to1(),
		        
		        rollup.getCondition2(),
		        rollup.getCondition_column2(),
		        rollup.getCondition_operation2(),
		        rollup.getCondition_as_to2(),
		        
		        rollup.getCondition3(),
		        rollup.getCondition_column3(),
		        rollup.getCondition_operation3(),
		        rollup.getCondition_as_to3(),
		        
		        rollup.getCondition4(),
		        rollup.getCondition_column4(),
		        rollup.getCondition_operation4(),
		        rollup.getCondition_as_to4(),
		        
		        rollup.getColumn1(),
		        rollup.getOperation1(),
		        rollup.getAs_to1(),

		        rollup.getColumn2(),
		        rollup.getOperation2(),
		        rollup.getAs_to2(),

		        rollup.getColumn3(),
		        rollup.getOperation3(),
		        rollup.getAs_to3(),

		        rollup.getNumber_variable1(),
		        rollup.getNumber_variable2(),
		        rollup.getNumber_variable3(),
		        rollup.getNumber_variable4(),
		        rollup.getNumber_variable5(),

		        rollup.getText_variable1(),
		        rollup.getText_variable2(),
		        rollup.getText_variable3(),
		        rollup.getText_variable4()
		    };

		    if (rollup.getId() > 0) {
		        // Update existing record
		        String sql = "UPDATE rollup_calculation SET "
		            + "id_refer=?, rollup_name=?, "
		            + "condition_column1=?, condition_operation1=?, condition_as_to1=?, "
		            + "condition2 =?,condition_column2=?, condition_operation2=?, condition_as_to2=?, "
		            + "condition3 =?,condition_column3=?, condition_operation3=?, condition_as_to3=?, "
		            + "condition4 =?,condition_column4=?, condition_operation4=?, condition_as_to4=?, "
		            + "column1=?, operation1=?, as_to1=?, "
		            + "column2=?, operation2=?, as_to2=?, "
		            + "column3=?, operation3=?, as_to3=?, "
		            + "number_variable1=?, number_variable2=?, number_variable3=?, number_variable4=?, number_variable5=?, "
		            + "text_variable1=?, text_variable2=?, text_variable3=?, text_variable4=?, "
		            + "updated_at = CURRENT_TIMESTAMP "
		            + "WHERE id = " + rollup.getId();

		        jdbcTemplate.update(sql, rollupInfo);

		    } else {
		        // Insert new record
		        String sql = "INSERT INTO rollup_calculation ("
		            + "id_refer, rollup_name, "
		            + "condition_column1, condition_operation1, condition_as_to1, "
		            + "condition2,condition_column2, condition_operation2, condition_as_to2, "
		            + "condition3,condition_column3, condition_operation3, condition_as_to3, "
		            + "condition4,condition_column4, condition_operation4, condition_as_to4, "
		            + "column1, operation1, as_to1, "
		            + "column2, operation2, as_to2, "
		            + "column3, operation3, as_to3, "
		            + "number_variable1, number_variable2, number_variable3, number_variable4, number_variable5, "
		            + "text_variable1, text_variable2, text_variable3, text_variable4"
		            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		        jdbcTemplate.update(sql, rollupInfo);
		    }
		}


		@Override
		public void DelRollup_calculation(Rollup_calculation Rollup_calculation) {
			String sql = "DELETE  FROM rollup_calculation  WHERE  id =  ?  ";
			jdbcTemplate.update(sql,new Object[]{Rollup_calculation.getId()});
			
		}
		
		@Override
		public Rollup_calculation   getRollup_calculationByid(int id) {
			
			 
			 try {
				 String sql = "SELECT *  FROM rollup_calculation  WHERE  id =  ?   ";
					Rollup_calculation data  =	 jdbcTemplate.queryForObject(sql,new Object[]{id}, new Rollup_calculationmapper());
					 return data;
				 }catch(Exception e) {
				       
					 return null;
				   
				 }  
			 
		}
		@Override
		public List<Rollup_calculation>   getRollup_calculationByidrefer(int id_refer) {

				 String sql = "SELECT *  FROM rollup_calculation  WHERE  id_refer =  ?   ";
				 List<Rollup_calculation>  list  =	 jdbcTemplate.query(sql,new Object[]{id_refer}, new Rollup_calculationmapper());
				 return list;
			
			 
		}
		
		
		
		
		@Override
		public void saveLinksSetting(LinksSetting setting) {

		    Object[] linksSettingInfo = {
		        setting.getId_refer(),
		        setting.getAllowed_link_relation(),
		        setting.getAllowed_use_data()
		    };

		    if (setting.getId() > 0) {
		        // Update existing record
		        String sql = "UPDATE links_setting SET "
		            + "id_refer=?, allowed_link_relation=?, allowed_use_data=?, "
		            + "updated_at = CURRENT_TIMESTAMP "
		            + "WHERE id = " + setting.getId();

		        jdbcTemplate.update(sql, linksSettingInfo);

		    } else {
		        // Insert new record
		        String sql = "INSERT INTO links_setting ("
		            + "id_refer, allowed_link_relation, allowed_use_data"
		            + ") VALUES (?, ?, ?)";

		        jdbcTemplate.update(sql, linksSettingInfo);
		    }
		}
		
		
		
		
		@Override
		public void DelLinksSetting(LinksSetting linksSetting) {
			String sql = "DELETE  FROM links_setting  WHERE  id =  ?  ";
			jdbcTemplate.update(sql,new Object[]{linksSetting.getId()});
			
		}
		
		@Override
		public LinksSetting   getLinksSettingByidrefer(int id_refer) {
		
			 
			 
			   try {
					String sql = "SELECT *  FROM links_setting  WHERE  id_refer =  ?   ";
					LinksSetting data  =	 jdbcTemplate.queryForObject(sql,new Object[]{id_refer}, new LinksSettingmapper());
					 return data;
				 }catch(Exception e) {
				       
					 return null;
				   
				 }  
		}
		
			
			@Override
			public void saveRow_calculation(Row_calculation calc) {
			
				  // เตรียมข้อมูล (จัดการ null สำหรับ Double)
			    Object[] params = {
			        calc.getId_refer(),
			        calc.getRow_name(),
			        
			        calc.getNumber_variable1(),
			        calc.getNumber_variable2(),
			        calc.getNumber_variable3(),
			        calc.getNumber_variable4(),
			        calc.getNumber_variable5(),
			        
			        calc.getStart_column1(),
			        calc.getColumn1(),
			        calc.getOperation1(),
			        calc.getAs_to1(),
			        
			        calc.getStart_column2(),
			        calc.getColumn2(),
			        calc.getOperation2(),
			        calc.getAs_to2(),
			        
			        calc.getStart_column3(),
			        calc.getColumn3(),
			        calc.getOperation3()
			        // as_to3 ไม่มีในโครงสร้างล่าสุด → ถ้ามีค่อยเพิ่ม
			    };

			    if (calc.getId() > 0) {
			        // UPDATE
			        String sql = "UPDATE row_calculation SET " +
			            "id_refer = ?, " +
			            "row_name = ?, " +
			            "number_variable1 = ?, number_variable2 = ?, number_variable3 = ?, " +
			            "number_variable4 = ?, number_variable5 = ?, " +
			            "start_column1 = ?, column1 = ?, operation1 = ?, as_to1 = ?, " +
			            "start_column2 = ?, column2 = ?, operation2 = ?, as_to2 = ?, " +
			            "start_column3 = ?, column3 = ?, operation3 = ?, " +
			            "updated_at = CURRENT_TIMESTAMP " +
			            "WHERE id = ?";

			        // เพิ่ม id ไว้ท้ายสุดสำหรับ WHERE
			        Object[] updateParams = Arrays.copyOf(params, params.length + 1);
			        updateParams[updateParams.length - 1] = calc.getId();

			        jdbcTemplate.update(sql, updateParams);

			    } else {
			        // INSERT
			        String sql = "INSERT INTO row_calculation (" +
			            "id_refer, row_name, " +
			            "number_variable1, number_variable2, number_variable3, number_variable4, number_variable5, " +
			            "start_column1, column1, operation1, as_to1, " +
			            "start_column2, column2, operation2, as_to2, " +
			            "start_column3, column3, operation3" +
			            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			        jdbcTemplate.update(sql, params);
			    }
			    
			}
			    
			    
			
			@Override
			public void DelRow_calculation(Row_calculation list) {
				String sql = "DELETE  FROM row_calculation  WHERE  id =  ?  ";
				jdbcTemplate.update(sql,new Object[]{list.getId()});
				
			}
			
			@Override
			public Row_calculation   getRow_calculationByid(int id) {
				
				 
				 
				    try {
				    	String sql = "SELECT *  FROM row_calculation  WHERE  id=  ?   ";
						Row_calculation data  =	 jdbcTemplate.queryForObject(sql,new Object[]{id}, new Row_calculationmapper());
						 return data;
					 }catch(Exception e) {
					       
						 return null;
					   
					 }    
				 
			}
			
			@Override
			public List<Row_calculation>   getRow_calculationByidrefer(int id_refer) {
				
		
				    	String sql = "SELECT *  FROM row_calculation  WHERE  id_refer =  ?   ";
				    	List<Row_calculation>  data  =	 jdbcTemplate.query(sql,new Object[]{id_refer}, new Row_calculationmapper());
						 return data;
				 
				 
			}
			
			
			
			
			@Override
			public void saveFooterCalculation(Footer_calculation footer) {
			
			    Object[] footerInfo = {
			        footer.getId_refer(),
			        footer.getFooter_name(),
			
			        footer.getFooter_title1(),
			        footer.getFooter_title2(),
			        footer.getFooter_title3(),
			
			        footer.getNumber_variable1(),
			        footer.getNumber_variable2(),
			        footer.getNumber_variable3(),
			        footer.getNumber_variable4(),
			        footer.getNumber_variable5(),
			
			        footer.getText_variable1(),
			        footer.getText_variable2(),
			        footer.getText_variable3(),
			        footer.getText_variable4(),
			        footer.getText_variable5(),
			        footer.getText_variable6(),
			        
			
			        footer.getStart_column1(),
			        footer.getColumn1(),
			        footer.getOperation1(),
			        footer.getAs_to1(),
			
			        footer.getStart_column2(),
			        footer.getColumn2(),
			        footer.getOperation2(),
			        footer.getAs_to2(),
			
			        footer.getStart_column3(),
			        footer.getColumn3(),
			        footer.getOperation3(),
			        footer.getAs_to3()
			    };
			
			    if (footer.getId() > 0) {
			        // Update existing record
			        String sql = "UPDATE footer_calculation SET "
			            + "id_refer=?, footer_name=?, "
			            + "footer_title1=?, footer_title2=?, footer_title3=?, "
			            + "number_variable1=?, number_variable2=?, number_variable3=?, number_variable4=?, number_variable5=?, "
			            + "text_variable1=?, text_variable2=?, text_variable3=?, text_variable4=?, text_variable5=?, text_variable6=?, "
			            + "start_column1=?, column1=?, operation1=?, as_to1=?, "
			            + "start_column2=?, column2=?, operation2=?, as_to2=?, "
			            + "start_column3=?, column3=?, operation3=?, as_to3=?, "
			            + "updated_at = CURRENT_TIMESTAMP "
			            + "WHERE id = " + footer.getId();
			
			        jdbcTemplate.update(sql, footerInfo);
			
			    } else {
			        // Insert new record
			        String sql = "INSERT INTO footer_calculation ("
			            + "id_refer, footer_name, "
			            + "footer_title1, footer_title2, footer_title3, "
			            + "number_variable1, number_variable2, number_variable3, number_variable4, number_variable5, "
			            + "text_variable1, text_variable2, text_variable3, text_variable4, text_variable5, text_variable6, "
			            + "start_column1, column1, operation1, as_to1, "
			            + "start_column2, column2, operation2, as_to2, "
			            + "start_column3, column3, operation3, as_to3"
			            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			        jdbcTemplate.update(sql, footerInfo);
			    }
			    
			}
			    
			    
			
			@Override
			public void DelFooter_calculation(Footer_calculation list) {
				String sql = "DELETE  FROM footer_calculation  WHERE  id =  ?  ";
				jdbcTemplate.update(sql,new Object[]{list.getId()});
				
			}
			
			@Override
			public Footer_calculation   getFooter_calculationByid(int id_refer) {
			
				 

				    try {
				    	String sql = "SELECT *  FROM footer_calculation  WHERE  id =  ?   ";
						Footer_calculation data  =	 jdbcTemplate.queryForObject(sql,new Object[]{id_refer}, new Footer_calculationmapper());
						 return data;
					 }catch(Exception e) {
					       
						 return null;
					   
					 }   
				 
			}
			@Override
			public List<Footer_calculation>   getFooter_calculationByidrefer(int id_refer) {
			

				    	String sql = "SELECT *  FROM footer_calculation  WHERE  id_refer =  ?   ";
				    	List<Footer_calculation> list  =	 jdbcTemplate.query(sql,new Object[]{id_refer}, new Footer_calculationmapper());
						 return list;
				
				 
			}
			
			
			
			
			
			
			
			
			    
			@Override
			public void saveLinks(Links link) {

			    Object[] linkInfo = {
			        link.getLink_name(),
			        link.getLink_to(),
			        link.getRelationship(),
			        link.getId_refer()
			    };

			    if (link.getId() > 0) {
			        // Update existing record
			        String sql = "UPDATE links SET "
			            + "link_name=?, link_to=?, relationship=?, id_refer=?, "
			            + "updated_at = CURRENT_TIMESTAMP "
			            + "WHERE id = " + link.getId();

			        jdbcTemplate.update(sql, linkInfo);

			    } else {
			        // Insert new record
			        String sql = "INSERT INTO links (link_name, link_to, relationship, id_refer) "
			            + "VALUES (?, ?, ?, ?)";

			        jdbcTemplate.update(sql, linkInfo);
			    }
			}

			    
			@Override
			public void DelLinks(Links list) {
				String sql = "DELETE  FROM links  WHERE  id =  ?  ";
				jdbcTemplate.update(sql,new Object[]{list.getId()});
				
			}
			
			@Override
			public Links   getLinksByid(int id) {
			
				 
				   try {
						String sql = "SELECT *  FROM links  WHERE  id=  ?   ";
						Links data  =	 jdbcTemplate.queryForObject(sql,new Object[]{id}, new Linksmapper());
						 return data;
					 }catch(Exception e) {
					       
						 return null;
					   
					 }   
			}

			
			@Override
			public List<Links>   getLinksByidrefer(int id_refer) {
			
						String sql = "SELECT *  FROM links  WHERE  id_refer =  ?   ";
						List<Links> list  =	 jdbcTemplate.query(sql,new Object[]{id_refer}, new Linksmapper());
						 return list;
				 
			}

			@Override
			public List<Links>   getLinksBylink_to(int id_refer) {
			
						String sql = "SELECT *  FROM links  WHERE  link_to =  ?   ";
						List<Links> list  =	 jdbcTemplate.query(sql,new Object[]{id_refer}, new Linksmapper());
						 return list;
				 
			}
			
			
			@Override
			public process  getProcessByIndex(String Maindatacolumn) {
			
				 System.out.println(" Maindatacolumn "+Maindatacolumn);

				
				 try {
					 
				
					 
					// 1. ดึงข้อมูลที่ register ตรงกันมาก่อน (ยังไม่ต้องกรอง type ใน SQL)
					    // เปลี่ยนจาก queryForObject เป็น query ธรรมดา เพื่อหลีกเลี่ยง Error กรณีไม่เจอข้อมูล
					    String sql = "SELECT * FROM process WHERE register = ? limit 1";
					    process candidates = jdbcTemplate.queryForObject(sql, new Object[]{Maindatacolumn}, new processmapper());



					    // ถ้าวนลูปจนครบแล้วยังไม่เจอที่ตรงกันเลย
					    return candidates;
					 }catch(Exception e) {
					       
						 return null;
					   
					 }  
			}
			
			
			@Override
			public void savebatchFeed(List<Feed> feed) {
					
					
			}

			
			@Override
			public void DelAllFeedByidrefer(int id_refer) {
				String sql = "DELETE  FROM feed  WHERE  id_refer =  ?  ";
				jdbcTemplate.update(sql,new Object[]{id_refer});
				
			}
			
			@Override
			public List<Feed>   getAllFeedByidrefer(int id_refer) {
				String sql = "SELECT *  FROM links  WHERE  id_refer =  ?   ";
				List<Feed> list  =	 jdbcTemplate.query(sql,new Object[]{id_refer}, new Feedmapper());
				 return list;
			}
			
			
			@Override
			public List<CustomizeTableHeaders>   getCustomizeTableHeadersBytype(String type) {
				String sql = "SELECT *  FROM customize_table_headers  WHERE  type =  ? ORDER BY id ASC   ";
				List<CustomizeTableHeaders> list  =	 jdbcTemplate.query(sql,new Object[]{type}, new CustomizeTableHeadersmapper());
				 return list;
			}
			
			
			@Override
			public List<Group_label>   allGroupLabels() {
			    // 1. ดึงข้อมูลพื้นฐานจาก Database
			    String sqls = "SELECT * FROM group_label";
			    List<Group_label> allGroupLabels = jdbcTemplate.query(sqls, new Group_labelmapper());
				return allGroupLabels;   
			}
			
}




