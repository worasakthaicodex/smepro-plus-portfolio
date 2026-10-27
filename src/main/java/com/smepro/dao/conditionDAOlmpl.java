package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Group_label;
import com.smepro.models.condition;
import com.smepro.models.control_steps;
import com.smepro.models.dar;
import com.smepro.models.edata;
import com.smepro.models.edata_product;
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
import com.smepro.rowmapper.EdataReportbuildInmapper;
import com.smepro.rowmapper.Group_labelmapper;
import com.smepro.rowmapper.conditionlinkmapper;
import com.smepro.rowmapper.conditionmapper;
import com.smepro.rowmapper.conditionmapper2;
import com.smepro.rowmapper.control_stepsmapper;
import com.smepro.rowmapper.darmapper;
import com.smepro.rowmapper.doc_pd_mappers;
import com.smepro.rowmapper.edatamapper3;
import com.smepro.rowmapper.edatamapper4;
import com.smepro.rowmapper.foldermapper;
import com.smepro.rowmapper.map_keymapper;
import com.smepro.rowmapper.product_mastermappers;
import com.smepro.rowmapper.production_listmappers;
import com.smepro.rowmapper.report_mappingmapper;
import com.smepro.rowmapper.reportsmapper;
import com.smepro.rowmapper.upload_pnmapper;

public class conditionDAOlmpl implements conditionDAO {

	
	private JdbcTemplate jdbcTemplate;
	


	public  conditionDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	@Override
	public void save(condition conditions) {
		Object[] conditionInfo = { 
	
				conditions.getCondition_idadd(),
				conditions.getCondition_nameadd(),
				conditions.getCondition_name(),
				conditions.getCondition_title(),
				conditions.getCondition_subtitle(),
				conditions.getCondition_industry(),
				conditions.getCondition_department(),
				conditions.getCondition_type(),
				conditions.getCalculation_port1(),
				conditions.getCalculation_port2(),
				conditions.getCalculation_port3(),
				conditions.getCalculation_port4(),
				conditions.getCalculation_port5(),

				};
				String sql = "INSERT INTO conditions(condition_idadd,condition_nameadd,condition_name,condition_title,condition_subtitle,condition_industry,condition_department"
						+ ",condition_type,calculation_port1,calculation_port2,calculation_port3,calculation_port4,calculation_port5) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				jdbcTemplate.update(sql,conditionInfo);
	}

	
	@Override
	public void copy(int id,int idadd) {

				String sql = "INSERT INTO conditions (condition_idadd,condition_nameadd,condition_name,condition_title,condition_subtitle,condition_industry,condition_department,condition_type"
						+ ",condition_status)"
						+ " SELECT ?,condition_nameadd,condition_name,condition_title,condition_subtitle,condition_industry,condition_department,condition_type,'-5'"
						+ " FROM conditions WHERE condition_id = ?";
				jdbcTemplate.update(sql,new Object[]{idadd,id});
	}

	
	
	@Override
	public int update(condition conditions) {
		  String sql=  "UPDATE conditions SET "
				+ "condition_name='"+conditions.getCondition_name()+"',"
				+ "condition_title='"+conditions.getCondition_title()+"',"
				+ "condition_subtitle='"+conditions.getCondition_subtitle()+"',"
		  		+ " WHERE  condition_id="+conditions.getCondition_id();
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	
	
	@Override
	public int[][] updateStatus(List<condition> conditions, int batchSize) {

		String sql = "UPDATE conditions set new_status=? "
				+ " WHERE condition_id =? ";
		
        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,conditions,batchSize,
                new ParameterizedPreparedStatementSetter<condition>() {
                    public void setValues(PreparedStatement ps, condition argument) 
						throws SQLException {
                        ps.setInt(1, argument.getNew_status());
                        ps.setInt(2, argument.getCondition_id());
          
                    }
                });
        return updateCounts;

    }
	
	
	
	
	
	
	
	
	@Override
	public int updatename(int id ,String name) {
		  String sql=  "UPDATE conditions SET "
				+ "condition_title='"+name+"'"
		  		+ " WHERE  condition_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	

	

	

	
	@Override
	public condition getbyid(int idadd,int status) {
		  String sql="select * from conditions where condition_idadd=? and condition_status =?   ";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{idadd,status},new conditionmapper()); 
	}
	@Override
	public int setnewid(int id) {
		  String sql=  "UPDATE conditions SET "
				+ "condition_status=0 "
		  		+ " WHERE  condition_id="+id;
		    return jdbcTemplate.update(sql);   
	}


	@Override
	public int setstatus(int status,int id,String name) {
		  String sql=  "UPDATE conditions SET "
				+ "condition_status='"+status+"'"
				+ ",condition_name='"+name+"'"
		  		+ " WHERE  condition_id="+id;
		    return jdbcTemplate.update(sql);   
	}	
	

	@Override
	public int setstatus(int status,int id,int rv,String name) {
		
		int newrv = rv+1;
		  String sql=  "UPDATE conditions SET "
				+ "condition_status='"+status+"'"
				+ ",condition_rv='"+newrv+"'"
				+ ",condition_name='"+name+"'"
		  		+ " WHERE  condition_id="+id;
		    return jdbcTemplate.update(sql);   
	}	
	
	
	public int numactivate() {
		String sql = "select count(*) from  conditions "
				+ " WHERE condition_status =1    ";
		return jdbcTemplate.queryForObject(
                sql, Integer.class);
	}
	
	
	
	@Override
	public int setChart(condition conditions) {
		  String sql=  "UPDATE conditions SET "
		  		+ "chartdiv1data='"+conditions.getChartdiv1data()+"',"
		  		+ "chartdiv2data='"+conditions.getChartdiv2data()+"',"
		  		+ "chartdiv3data='"+conditions.getChartdiv3data()+"',"
		  		+ "chartdiv1label='"+conditions.getChartdiv1label()+"',"
		  		+ "chartdiv2label='"+conditions.getChartdiv2label()+"',"
		  		+ "shows='"+conditions.getShows()+"'"
		  		+ " WHERE  condition_id="+conditions.getCondition_id();
		    return jdbcTemplate.update(sql);   
	}
	


	@Override
	public int setReport(condition conditions) {
		
		Object[] conditionInfo = { 
				conditions.getCondition_report(),
				conditions.getCondition_id(),
				};
		
		  String sql=  "UPDATE conditions SET "
		  		+ "condition_report= ? "
		  		+ " WHERE  condition_id =? ";
		    return jdbcTemplate.update(sql,conditionInfo);   
	}
	
	

	@Override
	public int delete(int id, int idadd) { 
		 String sql="delete from conditions where condition_id="+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public List<condition> getall() {
		String sql = "select * from conditions"
				+ " where  condition_status = 1 order by condition_id desc limit 3000";
		List<condition> conditions  =  jdbcTemplate.query(sql,new Object[]{},new conditionmapper());
		return conditions;
	}
	
	@Override
	public List<condition> getallGroup() {
		String sql = "select datalink.datalink_name AS condition_titlerefer,datalink.datalink_workflow AS condition_group,conditions.* from datalink LEFT JOIN conditions ON conditions.condition_id = datalink.datalink_idrefer "
				+ " where  conditions.condition_status = 1   order by conditions.condition_id desc limit 100";
		List<condition> conditions  =  jdbcTemplate.query(sql,new Object[]{},new conditionmapper());
		return conditions;
	}
	
	
	@Override
	public List<condition> getAllNotRefer() {
		String sql = "SELECT datalink.datalink_id AS join_type,conditions.* FROM conditions "
				+ " LEFT JOIN datalink ON conditions.condition_id = datalink.datalink_idrefer " +
	             "   ORDER BY conditions.condition_id DESC LIMIT 3000";
		List<condition> conditions  =  jdbcTemplate.query(sql,new conditionmapper());
		return conditions;
	}
	@Override
	public List<condition> getAllbylink(int id) {
		String sql = "SELECT datalink.datalink_name,datalink.datalink_idrefer,datalink.key1,datalink.key2,datalink.key3,datalink.datalink_id AS join_type,conditions.* FROM datalink "
				+ " LEFT JOIN conditions ON conditions.condition_id = datalink.datalink_idrefer " +
	             " WHERE datalink.datalink_workflow = ?  ORDER BY datalink.datalink_id  ASC LIMIT 3000";
		List<condition> conditions  =  jdbcTemplate.query(sql,new Object[]{id},new conditionlinkmapper());
		return conditions;
	}
	@Override
	public List<condition> getall(int id) {
		String sql = "select * from conditions where condition_idadd =?    order by condition_id desc limit 3000";
		List<condition> conditions  =  jdbcTemplate.query(sql,new Object[]{id},new conditionmapper());
		return conditions;
	}

	
	@Override
	public List<condition> getalls(int id) {
		String sql = "select c.condition_name AS condition_titlerefer,conditions.* from conditions "
				+ "LEFT JOIN conditions as c ON   c.condition_sub = conditions.condition_id "
				+ " where conditions.condition_idadd =?  and conditions.condition_status = 0 "
				+ "  order by conditions.condition_id desc limit 3000";
		List<condition> conditions  =  jdbcTemplate.query(sql,new Object[]{id},new conditionmapper());
		return conditions;
	}
	
	@Override
	public condition getbyid(int id) {
	 
		    try {
		    	 String sql="select * from conditions where condition_id=? limit 1    ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new conditionmapper()); 
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		    
		    
	}
	

	
	
	
	
	@Override
	public condition getbyidandplan(int id) {
	 
		    try {
		    	 String sql="select * from conditions LEFT JOIN upload_pn ON conditions.condition_id = upload_pn.upload_pn_monitor "
		    	 		+ " where conditions.condition_id=?  limit 1    ";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new conditionmapper2()); 
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		    
		    
	}
	
	
	@Override
	public condition getbysubandplan(int id) {
	
		    
		    try {
		  	  String sql="select * from conditions"
		  	  		+ " LEFT JOIN upload_pn ON conditions.condition_id = upload_pn.upload_pn_monitor "
		  	  		+ " where conditions.condition_sub=? or conditions.condition_sub2=? or conditions.condition_sub3=?  and  conditions.condition_status = 1   limit 1  ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id,id,id},new conditionmapper2()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}
	
	
	
	
	@Override
	public condition getbyids(int id) {
	
		    
		    try {
		  	  String sql="select * from conditions where condition_id=? and  condition_status = 1 ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new conditionmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}
	
	
	
	
	@Override
	public condition getbysub(int id) {
	
		    
		    try {
		  	  String sql="select * from conditions where condition_sub=? or condition_sub2=? or condition_sub3=?  and  condition_status = 1 ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id,id,id},new conditionmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}
	
	@Override
	public condition getbyport(int id,int port) {
		  
		
		String sql= null;
		
		if(port == 1) {
			 sql="select * from conditions where calculation_port1 = ?  ";    
			
		}
		else if(port == 2) {
			 sql="select * from conditions where calculation_port2 = ?  ";    
			
		}
		else if(port == 3) {
			 sql="select * from conditions where calculation_port3 = ?  ";    
			
		}
		else if(port == 4) {
			 sql="select * from conditions where calculation_port4 = ?  ";    
			
		}
		else if(port == 5) {
			 sql="select * from conditions where calculation_port5 = ?  ";    
			
		}
		
		    try {
		  	
			    return jdbcTemplate.queryForObject(sql, new Object[]{id},new conditionmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}

	@Override
	public condition getbyport(int id) {
		  
		
		String sql= null;
		try {
			 sql="select * from conditions where calculation_port1 = ?  or calculation_port2 = ? or calculation_port3 = ? or  calculation_port4 = ?  or calculation_port5 = ?  limit 1 ";    

		  	
			    return jdbcTemplate.queryForObject(sql, new Object[]{id,id,id,id,id},new conditionmapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		    
	}
	
	  @Override
			public List<condition> getTrace() {
			String sql = "SELECT * FROM conditions "
					+ " where (calculation_port1 > 0 or calculation_port2 > 0 or calculation_port3 > 0"
					+ " or calculation_port4 > 0  or calculation_port5 > 0)"
					+ " order by condition_id DESC limit 5000";
			List<condition> conditions  =  jdbcTemplate.query(sql,new conditionmapper());
			return conditions;
			}

	
	
	
	
	
	@Override
	public boolean checkmap(int id,int userid) {
	    String sql = "SELECT count(*) FROM report_mapping WHERE mapping_id_refer = ? AND mapping_id_add = ?";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { id,userid }, Integer.class);

	    return count > 0;
	}
	
	
	
	
	@Override
	public report_mapping getMap(int id,int userid) {
		
			    try {
				  	
				    String sql = "SELECT * FROM report_mapping WHERE mapping_id_refer = ? AND mapping_id_add = ?";
		
				    return jdbcTemplate.queryForObject(sql, new Object[]{id,userid},new reportsmapper());
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }
				
	 
	}
	
	@Override
	public void updateMap(report_mapping report_mappings) {
		Object[] report_mappingInfo = { 
	
				report_mappings.getMapping_key1(),
				report_mappings.getMapping_key2(),
				report_mappings.getMapping_key3(),
				report_mappings.getMapping_key4(),
				report_mappings.getMapping_key5(),
				report_mappings.getMapping_key6(),
				report_mappings.getMapping_key7(),
				report_mappings.getMapping_key8(),
				report_mappings.getMapping_key9(),
				report_mappings.getMapping_key10(),
				report_mappings.getMapping_id(),

				};
				String sql = "UPDATE report_mapping SET mapping_key1 = ?,mapping_key2 = ?,mapping_key3 = ?,mapping_key4 = ?,mapping_key5 = ?"
						+ ",mapping_key6 = ?,mapping_key7 = ?,mapping_key8 = ?,mapping_key9 = ?,mapping_key10 = ? WHERE mapping_id = ? ";
				jdbcTemplate.update(sql,report_mappingInfo);
	}

	@Override
	public void saveMap(report_mapping report_mappings) {
		Object[] report_mappingInfo = { 
	
				report_mappings.getMapping_id_add(),
				report_mappings.getMapping_id_refer(),
				report_mappings.getMapping_id_map(),
				};
				String sql = "INSERT INTO  report_mapping(mapping_id_add,mapping_id_refer,mapping_id_map) VALUES(?,?,?) ";
				jdbcTemplate.update(sql,report_mappingInfo);
	}

	
	
	
	
	
	
	 @Override
	  public void setGroup(String condition_group,String id) {
					String sql = "UPDATE conditions SET condition_group =? WHERE FIND_IN_SET(condition_id,?) ";
					jdbcTemplate.update(sql,new Object[]{condition_group,id});
		}
	 @Override
	  public void setGroup(String condition_group,int id1,int id2,int id3,int id4) {
					String sql = "UPDATE conditions SET condition_group =? WHERE (condition_id = ? or condition_id = ?  or condition_id = ? or condition_id = ?) ";
					jdbcTemplate.update(sql,new Object[]{condition_group,id1,id2,id3,id4});
		}


	

	@Override
	public map_key getMap_key(int field_formid, int sub) {
		
				  
				    try {
					  	
						String sql = "SELECT *  FROM   map_key WHERE map_key_id_main=? AND map_key_id_sub=?   ";
						  return  jdbcTemplate.queryForObject(sql,new Object[] {field_formid,sub},new map_keymapper());	
				 
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }
				  
				  
	}
	@Override
	public map_key getMap_key(int field_formid) {
			
				  
	    try {
					  	
	    	String sql = "SELECT *  FROM   map_key WHERE map_key_id_main=?    ";
			  return  jdbcTemplate.queryForObject(sql,new Object[] {field_formid},new map_keymapper());	
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }
				  
	}
	
	@Override
	public map_key getMap_keysub(int field_formid) {
			
		
	    try {
					  	
	    	String sql = "SELECT *  FROM   map_key WHERE map_key_id_sub=?    ";
			  return  jdbcTemplate.queryForObject(sql,new Object[] {field_formid},new map_keymapper());	
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }
				  
	}
	
	
	@Override
	public void setjointype(int id,int type) {
		  String sql=  "UPDATE conditions SET join_type = ?  WHERE  condition_id= ? ";
		     jdbcTemplate.update(sql,new Object[] {id,type});   
	}
	
	
	
	@Override
	public List<condition> getallString(workflow workflows) {
		
		if(workflows != null) {
			String sql = "select * from conditions where FIND_IN_SET(condition_id,?) ";
			List<condition> conditions  =  jdbcTemplate.query(sql,new Object[]{workflows.getWorkflow_key2()},new conditionmapper());
			return conditions;
		}else {
			return null;
		}
		
		
	}
	
	
	
	@Override
	public boolean getbycontrol_steps(String data,String type,int workflow) {
		
				int count =0;
				if(type.equals("product")) {
						
						String sql = " SELECT COUNT(*) FROM  product LEFT JOIN edata ON  product.edata_id = edata.data_id WHERE edata.product =?  AND product.step= ? ";
						   count =	jdbcTemplate.queryForObject(sql,new Object[] {data,workflow},Integer.class);
				}
				else if(type.equals("batch")) {
					String sql = " SELECT COUNT(*) FROM  order_management LEFT JOIN edata ON  order_management.edata_id = edata.data_id WHERE edata.batch =?  AND order_management.step= ? ";
					   count =	jdbcTemplate.queryForObject(sql,new Object[] {data,workflow},Integer.class);
					  
				}else if(type.equals("production_directive")) {
					
					String sql = " SELECT COUNT(*) FROM  order_management LEFT JOIN edata ON  order_management.edata_id = edata.data_id WHERE edata.production_directive =?  AND order_management.step= ?  ";
					   count =	jdbcTemplate.queryForObject(sql,new Object[] {data,workflow},Integer.class);
				}
				else if(type.equals("material")) {
					
					String sql = " SELECT COUNT(*) FROM  material LEFT JOIN edata ON  material.edata_id = edata.data_id WHERE edata.material =? AND material.step= ?  ";
					   count =	jdbcTemplate.queryForObject(sql,new Object[] {data,workflow},Integer.class);
				}
				else if(type.equals("customer")) {
									
					String sql = " SELECT COUNT(*) FROM  customer LEFT JOIN edata ON  customer.edata_id = edata.data_id WHERE edata.production_directive =? AND customer.step= ?  ";
					   count =	jdbcTemplate.queryForObject(sql,new Object[] {data,workflow},Integer.class);
				}
				else if(type.equals("part")) {
					
					String sql = " SELECT COUNT(*) FROM  part LEFT JOIN edata ON  part.edata_id = edata.data_id WHERE edata.part =? AND customer.step= ?  ";
					   count =	jdbcTemplate.queryForObject(sql,new Object[] {data,workflow},Integer.class);
				}
		  return count > 0;
	}
	
	@Override
	public List<control_steps> getAllOrder_managements() {
		
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT doc.*,production_directive.* FROM production_directive  LEFT JOIN doc ON  production_directive.edata_id = doc.doc_idrefer    ");
		  String sql = bulid.toString();
		  List<control_steps>  pd = jdbcTemplate.query(sql,new doc_pd_mappers());   
		  return pd;
	}
	
	@Override
	public List<control_steps> getAllOrder_management_batch() {
		
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT doc.*,production_directive.*  FROM production_directive  LEFT JOIN doc ON production_directive.edata_id = doc.doc_idrefer   ");
		  String sql = bulid.toString();
		  List<control_steps>  batch = jdbcTemplate.query(sql,new doc_pd_mappers());   
		  return batch;
	}
	
	
	

	
	
	
	@Override
	public control_steps getOrder_managements(edata_stock stock) {
		  
		  try {
			  StringBuilder bulid = new StringBuilder(); 
			  bulid.append( "SELECT * FROM  order_management  LEFT JOIN edata ON  order_management.edata_id = edata.data_id WHERE  order_management.id= ?   ");
			  String sql = bulid.toString();
			  control_steps  control_steps = jdbcTemplate.queryForObject(sql,new Object[] {stock.getStock_pd()},new control_stepsmapper());   
			  return control_steps;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
		  
		  
		  
	}
	
	
	
	

	
	
	@Override
	public List<production_list> getAllCustomer() {
		
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT * FROM  production_list WHERE customer_name IS NOT NULL GROUP BY customer_name ");
		  String sql = bulid.toString();
		  List<production_list>  pl = jdbcTemplate.query(sql,new production_listmappers());   
		  return pl;
	}
	
	@Override 
	public List<product_master> getAllProduct() {
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT * FROM  product_master  WHERE status = 'Approved' ");
		  String sql = bulid.toString();
		  List<product_master>  product = jdbcTemplate.query(sql,new product_mastermappers());   
		  return product;
	}
	
	@Override 
	public List<product_master> getAllProductCategory() {
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT * FROM  product_master  WHERE status = 'Approved' group by product_category ");
		  String sql = bulid.toString();
		  List<product_master>  product = jdbcTemplate.query(sql,new product_mastermappers());   
		  return product;
	}
	
	
	
	@Override 
	public control_steps getProduct(String product) {
		

		  
		  try {
			  StringBuilder bulid = new StringBuilder(); 
			  bulid.append( "SELECT * FROM  edata LEFT JOIN production_directive ON  production_directive.edata_id = edata.data_id WHERE  production_directive.lot_batch= ?   limit 1  ");
			  String sql = bulid.toString();
			  control_steps  control_steps = jdbcTemplate.queryForObject(sql,new Object[] {product},new control_stepsmapper());   
			  return control_steps;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
		  
		  
		  
		  
	}
	
	
	@Override 
	public control_steps getMaterial(String material) {
		

		  
		  
		  
		  try {
			  StringBuilder bulid = new StringBuilder(); 
			  bulid.append( "SELECT * FROM  edata LEFT JOIN material ON  material.edata_id = edata.data_id WHERE  edata.material= ? "
			  		+ "group by edata.material,edata.batch_material  limit 1  ");
			  String sql = bulid.toString();
			  control_steps  control_steps = jdbcTemplate.queryForObject(sql,new Object[] {material},new control_stepsmapper());   
			  return control_steps;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
		  
		  
	}

	
	
	@Override 
	public control_steps getCustomer(String product) {
		

		  
		  try {
			  StringBuilder bulid = new StringBuilder(); 
			  bulid.append( "SELECT * FROM  edata LEFT JOIN production_list ON  production_list.edata_id = edata.data_id AND  production_list.edata_id IS NOT NULL "
			  		+ "WHERE  production_list.lot_batch= ? "
			  		+ "AND  production_list.customer_code IS NOT NULL    limit 1  ");
			  String sql = bulid.toString();
			  control_steps  control_steps = jdbcTemplate.queryForObject(sql,new Object[] {product},new control_stepsmapper());   
			  return control_steps;
			  
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
		  
		  
		  
		  
	}
	
	
	
	
	@Override 
	public control_steps getPD(int id) {
		

		  
		  
		  try {
			  StringBuilder bulid = new StringBuilder(); 
			  bulid.append( "SELECT * FROM  edata LEFT JOIN order_management ON  order_management.edata_id = edata.data_id  "
			  		+ " WHERE  order_management.product_id= ?  limit 1  ");
			  String sql = bulid.toString();
			  control_steps  control_steps = jdbcTemplate.queryForObject(sql,new Object[] {id},new control_stepsmapper());   
			  return control_steps;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
		  
		  
	}
	
	
	
	@Override 
	public control_steps getByOrder_managementsId(int id) {
		

		  
		  
		  try {
			  StringBuilder bulid = new StringBuilder(); 
			  bulid.append( "SELECT * FROM  edata LEFT JOIN order_management ON  order_management.edata_id = edata.data_id  "
			  		+ " WHERE  order_management.id= ?  limit 1  ");
			  String sql = bulid.toString();
			  control_steps  control_steps = jdbcTemplate.queryForObject(sql,new Object[] {id},new control_stepsmapper());   
			  return control_steps;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
		  
		  
	}
	
	
	
	@Override
	public List<control_steps> getAllPart(int step) {
		
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT * FROM  part  LEFT JOIN edata ON  part.edata_id = edata.data_id WHERE  part.step= ? AND edata.data_id > 0  AND edata.part IS NOT NULL   "
		  		+ "group by edata.part ORDER BY id DESC limit 500  ");
		  String sql = bulid.toString();
		  List<control_steps>  control_steps = jdbcTemplate.query(sql,new Object[] {step},new control_stepsmapper());   
		  return control_steps;
	}
	
	@Override
	public List<control_steps> getAllMaterial(int step) {
		
		  StringBuilder bulid = new StringBuilder(); 
		  bulid.append( "SELECT * FROM  goods_receipt_sk  LEFT JOIN edata ON  goods_receipt_sk.edata_id = edata.data_id WHERE skg_tracking_number IS NOT NULL "
		  		+ "group by goods_receipt_sk.skg_tracking_number ORDER BY id DESC limit 500  ");
		  String sql = bulid.toString();
		  List<control_steps>  control_steps = jdbcTemplate.query(sql,new control_stepsmapper());   
		  return control_steps;
	}

	

	@Override
	public void setControl_steps(control_steps control_steps) {
		
		Object[] control_stepsInfo = { 
				control_steps.getStep(),
				control_steps.getStep_1(),
				control_steps.getStep_2(),
				control_steps.getStep_3(),
				};
					if(control_steps.getId() > 0) {
							String sql = " UPDATE  control_steps SET step=?,step_1 =?,step_2=?,step_3=? WHERE id = "+control_steps.getId();
							jdbcTemplate.update(sql,control_stepsInfo);
					}
					else  {
		
						String sql = " INSERT INTO control_steps (step,step_1,step_2,step_3) VALUES(?,?,?,?)";
						jdbcTemplate.update(sql,control_stepsInfo);
						
					}
					
					
	}
	@Override
	public void setControl_steps(control_steps control_steps,String type) {
		
		Object[] control_stepsInfo = { 
				control_steps.getStep(),
				control_steps.getStep_1(),
				control_steps.getStep_2(),
				control_steps.getStep_3(),
				};
					if(control_steps.getId() > 0) {
							String sql = " UPDATE  control_steps SET step=?,step_1 =?,step_2=?,step_3=? WHERE id = "+control_steps.getId();
							jdbcTemplate.update(sql,control_stepsInfo);
					}
					else  {
		
						String sql = " INSERT INTO control_steps (step,step_1,step_2,step_3) VALUES(?,?,?,?)";
						jdbcTemplate.update(sql,control_stepsInfo);
						
					}
					
					
	}
	@Override
	public control_steps getControl_steps(int id) {
							
	               		  try {
	               			String sql = " SELECT * FROM   control_steps   WHERE step = ? limit 1";
							control_steps data = 	jdbcTemplate.queryForObject(sql,new Object[] {id},new control_stepsmapper());
	                         return  data;
	               		 }catch(EmptyResultDataAccessException e) {
	               			 
	               			 return null;
	               			 //throw new RuntimeException("file ID does not exist");
	               		    
	               		 }catch(IncorrectResultSizeDataAccessException e) {
	               		       
	               			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
	               		   
	               		 }
	}
	
	
	@Override
	public void delControl_steps(int id,String type) {
		

					if(type.equals("product")) {
							
							String sql = " delete from product WHERE edata_id=? ";
							jdbcTemplate.update(sql,new Object[] {id});
					}
					else if(type.equals("order_management")) {
			
						String sql = " delete from order_management WHERE edata_id=? ";
						
						jdbcTemplate.update(sql,new Object[] {id});
						
					}
						else if(type.equals("material")) {
						
							String sql = " delete from material WHERE edata_id=? ";
						jdbcTemplate.update(sql,new Object[] {id});
					}
						else if(type.equals("customer")) {
							
							String sql = " delete from customer WHERE edata_id=? ";
							jdbcTemplate.update(sql,new Object[] {id});
						}
						else if(type.equals("part")) {
							
							String sql = " delete from part WHERE edata_id=? ";
							jdbcTemplate.update(sql,new Object[] {id});
						}
					
					
	}

	@Override
	public List<Group_label> getGroup_label() {
		  String sql =  "SELECT * FROM  group_label  ";
		  List<Group_label>  Group_labels = jdbcTemplate.query(sql,new Group_labelmapper());   
		  return Group_labels;
	}

	@Override
	public Group_label getGroup_labelbyId(int id) {
		  String sql =  "SELECT * FROM  group_label  WHERE id = ?  ";
		   Group_label  Group_labels = jdbcTemplate.queryForObject(sql,new Object[] {id},new Group_labelmapper());   
		  return Group_labels;
	}

	@Override
	public void saveGrouplabels(Group_label group_labels) {
		
		Object[] group_labelsInfo = { 
				group_labels.getGroup_label_name()
				};
					if(group_labels.getId() > 0) {
							String sql = " UPDATE  group_label  SET group_label_name=? WHERE id = "+group_labels.getId();
							jdbcTemplate.update(sql,group_labelsInfo);
					}else  {
						String sql = " INSERT INTO group_label (group_label_name) VALUES(?)";
						jdbcTemplate.update(sql,group_labelsInfo);
					}
	}
	
	
	@Override
	public void delGrouplabel(int id) {
		  String sql =  "DELETE FROM  group_label  WHERE id = ?  ";
		   jdbcTemplate.update(sql,new Object[] {id});   
	}



	
}
