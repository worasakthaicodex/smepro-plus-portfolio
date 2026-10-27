package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.smepro.models.BuildIn;
import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.FieldConfig;
import com.smepro.models.LoadApp;
import com.smepro.models.condition;
import com.smepro.models.dar;
import com.smepro.models.eformfield;
import com.smepro.models.sub_processes;
import com.smepro.rowmapper.BuildInmapper;
import com.smepro.rowmapper.EdataReportbuildInmapper;
import com.smepro.rowmapper.FieldConfigmapper;
import com.smepro.rowmapper.conditionmapper;
import com.smepro.rowmapper.darmapper;
import com.smepro.rowmapper.eformfieldmapper;
import com.smepro.rowmapper.foldermapper;

public class eformfieldDAOlmpl implements eformfieldDAO {

	
	private JdbcTemplate jdbcTemplate;
	


	public  eformfieldDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	@Override
	public void save(eformfield eformfields) {
		
		
		Object[] eformfieldInfo = { 
				eformfields.getField_formid(),
				eformfields.getField_type(),
				eformfields.getField_label(),
				eformfields.getField_mainfield(),
				eformfields.getField_datarefertype(),
				eformfields.getField_datareferid(),
				eformfields.getField_datareferid2(),
				eformfields.getField_datareferid3(),
				eformfields.getField_datareferid4(),
				eformfields.getField_datareferid5(),
				eformfields.getField_datareferid6(),
				eformfields.getField_datareferid7(),
				eformfields.getField_dataport(),
				eformfields.getField_datarefercols(),
				};
		
		
				String sql = "INSERT INTO eformfield(field_formid,field_type,field_label,field_mainfield,field_datarefertype,field_datareferid"
						+ ",field_datareferid2,field_datareferid3,field_datareferid4,field_datareferid5,field_datareferid6,field_datareferid7,field_dataport,field_datarefercols) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				jdbcTemplate.update(sql,eformfieldInfo);
	}

	
	@Override
	public void copy(int id,int newid) {

				String sql = "INSERT INTO eformfield (field_formid,field_type,field_label,field_mainfield,field_datarefertype,field_datareferid,field_datarefercols)"
						+ " SELECT ?,field_type,field_label,field_mainfield,field_datarefertype,field_datareferid,field_datarefercols "
						+ " FROM eformfield WHERE field_formid = ?";
				jdbcTemplate.update(sql,new Object[]{newid,id});
	}


	
	
	
	@Override
	public int update(eformfield eformfields) {
		
		Object[] eformfieldInfo = { 
				eformfields.getField_type(),
				eformfields.getField_label(),
				eformfields.getField_mainfield(),
				eformfields.getField_datarefertype(),
				eformfields.getField_datareferid(),
				eformfields.getField_datarefercols(),
				eformfields.getField_formid(),
				eformfields.getField_formrv(),
				};

		String sql = "UPDATE eformfield  set field_type=?,field_label=?"
				+ ",field_mainfield=?,field_datarefertype=?,field_datareferid=?,field_datarefercols? "
				+ " WHERE field_formid =?  and  field_formrv=?";
		return jdbcTemplate.update(sql,eformfieldInfo);
	}
	
	
	
	@Override
	public int[][] update(List<eformfield> eformfields) {

		String sql = "UPDATE eformfield SET  field_cal_type=?,field_cal_type2=? WHERE field_id =? ";
		
        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,eformfields,1000,
                new ParameterizedPreparedStatementSetter<eformfield>() {
                    public void setValues(PreparedStatement ps, eformfield argument) 
						throws SQLException {
                        ps.setString(1, argument.getField_cal_type());
                        ps.setString(2, argument.getField_cal_type2());
                        ps.setInt(3, argument.getField_id());
                    }
                });
        return updateCounts;

    }
	
	
	@Override
	public void UpdateAll(String field_id,String input_size) {

		
		String[] field_ids = field_id.split(",");
		String[] input_sizes = input_size.split(",");

		
		
		String sql = "UPDATE  eformfield  set input_size = ? WHERE field_id = ? ";
	
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {
	             
	              ps.setInt(1,Integer.parseInt(input_sizes[i]));  
	              ps.setInt(2,Integer.parseInt(field_ids[i]));  
	            }

	            @Override
	            public int getBatchSize() {
	                return field_ids.length;
	            }
	        });
 

    }
	

	@Override
	public int[][] savebatch(List<eformfield> eformfields, int batchSize) {

		String sql = "INSERT INTO eformfield(field_type,field_label,field_cal_type,field_datarefertype"
				+ ",field_formid,field_formrv,field_postion,field_datareferid) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		
        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,eformfields,batchSize,
                new ParameterizedPreparedStatementSetter<eformfield>() {
                    public void setValues(PreparedStatement ps, eformfield argument) 
						throws SQLException {
                        ps.setString(1, argument.getField_type());
                        ps.setString(2, argument.getField_label());
                        ps.setString(3, argument.getField_cal_type());
                        ps.setString(4, argument.getField_datarefertype());
                        ps.setInt(5, argument.getField_formid());
                        ps.setInt(6, argument.getField_formrv());
                        ps.setInt(7, argument.getField_postion());
                        ps.setInt(8, argument.getField_datareferid());

                    }
                });
        return updateCounts;

    }

	
	@Override
	public int deleteall(int formid,int formrv) { 
		 String sql="delete from eformfield where field_formid= ? and field_formrv =? ";    
		    return jdbcTemplate.update(sql,new Object[]{formid,formrv});
	}
	@Override
	public int delete(int id) { 
		 String sql="delete from eformfield where field_formid="+id;    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public List<eformfield> getall(long id,int revision) {
		String sql = "select * from eformfield  WHERE field_formid =?  and  field_formrv=?   order by field_id  asc ";
		List<eformfield> eformfields  =  jdbcTemplate.query(sql,new Object[]{id,revision},new eformfieldmapper());
		return eformfields;
	}
	
	@Override
	public List<FieldConfig> getallFieldConfigByprocess_id(int process_id) {
		String sql = "select * from field_config  WHERE process_id =? ";
		List<FieldConfig> list  =  jdbcTemplate.query(sql,new Object[]{process_id},new FieldConfigmapper());
		return list;
	}
	
	
	
	@Override
	public List<eformfield> getallNoBatchInput(LoadApp loadApp) {
		
		int id = loadApp.getCondition().getCondition_id();
		int	revision =loadApp.getCondition().getCondition_rv();
		String Batch =  loadApp.getBatch_input();
		
		System.out.println("Batch ***** "+Batch );
		
		String sql = "select * from eformfield  WHERE field_formid =?  and  field_formrv=? and find_in_set(,?)   order by field_id  asc ";
		List<eformfield> eformfields  =  jdbcTemplate.query(sql,new Object[]{id,revision,Batch},new eformfieldmapper());
		return eformfields;
	}


	@Override
	public List<eformfield> getalls(int id,int revision) {
		String sql = "select * from eformfield  WHERE field_formid =?  and  field_formrv=?   order by field_id  asc  limit 10";
		List<eformfield> eformfields  =  jdbcTemplate.query(sql,new Object[]{id,revision},new eformfieldmapper());
		return eformfields;
	}

    @Override
	public eformfield getbyrefer(int formid,int type) {
              
		   String sql = null;
	    	if(type == 1) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid > 0  and field_datarefertype = 'cal'  order by field_id  desc limit 1  "; }
	    	else if(type == 2) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid2 > 0 and field_datarefertype = 'cal' order by field_id  desc  limit 1 "; }
	    	else if(type == 3) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid3 > 0  and field_datarefertype = 'cal' order by field_id  desc  limit 1 ";}
	    	else if(type == 4) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid4 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else if(type == 5) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid5 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else if(type == 6) {sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid6 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 "; }
	    	else  if(type == 7) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid7 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else  if(type == 15) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid15 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else  if(type == 16) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid16 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else  if(type == 19) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid19 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	
	    	
	    	else if(type == 8) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid8 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 "; }
	    	else if(type == 9) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid9 > 0  and field_datarefertype = 'cal' order by field_id  desc  limit 1 "; }
	    	else if(type == 10) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid10 > 0  and field_datarefertype = 'cal' order by field_id  desc  limit 1 ";}
	    	else if(type == 11) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid11 > 0  and field_datarefertype = 'cal' order by field_id  desc  limit 1 ";}
	    	else if(type == 12) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid12 > 0   and field_datarefertype = 'cal' order by field_id  desc limit 1 ";}
	    	else if(type == 13) {sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid13 > 0  and field_datarefertype = 'cal'  order by field_id  desc limit 1 "; }
	    	else  if(type == 14) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid14 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else  if(type == 17) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid17 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else  if(type == 18) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid18 > 0 and field_datarefertype = 'cal' order by field_id  desc   limit 1 ";}
	    	else  if(type == 20) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid20 > 0 and field_datarefertype = 'cal' order by field_id   desc   limit 1 ";}
		    try {
		    	eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{formid},new eformfieldmapper());
				return eformfields;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
			
			

	}
    @Override
   	public eformfield getbyrefers(int formid,int type) {
                 
   		   String sql = null;
   	    	if(type == 1) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid > 0  order by field_id  asc limit 1  "; }
   	    	else if(type == 2) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid2 > 0  order by field_id  asc  limit 1 "; }
   	    	else if(type == 3) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid3 > 0  order by field_id   asc  limit 1 ";}
   	    	else if(type == 4) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid4 > 0 order by field_id  asc   limit 1 ";}
   	    	else if(type == 5) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid5 > 0 order by field_id  asc   limit 1 ";}
   	    	else if(type == 6) {sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid6 > 0 order by field_id  asc   limit 1 "; }
   	    	else  if(type == 7) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid7 > 0 order by field_id  asc   limit 1 ";}
   	    	else  if(type == 15) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid15 > 0 order by field_id  asc   limit 1 ";}
   	    	else  if(type == 16) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid16 > 0 order by field_id  asc   limit 1 ";}
   	    	else  if(type == 19) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid19 > 0 order by field_id  asc   limit 1 ";}
   	    	
   	    	
   	    	else if(type == 8) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid8 > 0 order by field_id  asc   limit 1 "; }
   	    	else if(type == 9) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid9 > 0  order by field_id  asc  limit 1 "; }
   	    	else if(type == 10) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid10 > 0  order by field_id  asc  limit 1 ";}
   	    	else if(type == 11) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid11 > 0  order by field_id  asc  limit 1 ";}
   	    	else if(type == 12) { sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid12 > 0   order by field_id  asc limit 1 ";}
   	    	else if(type == 13) {sql = "select * from eformfield  WHERE  field_formid= ? and field_datareferid13 > 0   order by field_id  asc limit 1 "; }
   	    	else  if(type == 14) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid14 > 0 order by field_id  asc   limit 1 ";}
   	    	else  if(type == 17) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid17 > 0 order by field_id  asc   limit 1 ";}
   	    	else  if(type == 18) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid18 > 0 order by field_id  asc   limit 1 ";}
   	    	else  if(type == 20) { sql = "select * from eformfield  WHERE   field_formid= ? and field_datareferid20 > 0 order by field_id  asc   limit 1 ";}
   		    try {
   		    	eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{formid},new eformfieldmapper());
   				return eformfields;
   			 
   			 }catch(EmptyResultDataAccessException e) {
   				 
   				 return null;
   				 //throw new RuntimeException("file ID does not exist");
   			    
   			 }catch(IncorrectResultSizeDataAccessException e) {
   			       
   				 throw new RuntimeException("More than one users with the same Id .......");
   			   
   			 }  
   			
   			

   	}
   	
    @Override
   	public eformfield  getByField_cal_type(int id,int rv,String type) {

   	    try {

   	    	String sql = "select * from eformfield WHERE field_formid =?  and  field_formrv=?  and  field_cal_type =?   LIMIT  1 ";
   			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,type},new eformfieldmapper());
   			return eformfields;
   		 
   		 }catch(EmptyResultDataAccessException e) {
   			 
   			 return null;
   			 //throw new RuntimeException("file ID does not exist");
   		    
   		 }catch(IncorrectResultSizeDataAccessException e) {
   		       
   			 throw new RuntimeException("More than one users with the same Id .......");
   		   
   		 }  
   		
   		
   		
   	}
    
    
    @Override
   	public eformfield  getByField_cal_type2(int id,int rv,int id2,int rv2,String type) {

   	    try {

   	    	String sql = "select * from eformfield WHERE (field_formid =?  and  field_formrv=? OR field_formid =?  and  field_formrv=?)  and  field_cal_type2 =?    LIMIT  1 ";
   			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,id2,rv2,type},new eformfieldmapper());
   			return eformfields;
   		 
   		 }catch(EmptyResultDataAccessException e) {
   			 
   			 return null;
   			 //throw new RuntimeException("file ID does not exist");
   		    
   		 }catch(IncorrectResultSizeDataAccessException e) {
   		       
   			 throw new RuntimeException("More than one users with the same Id .......");
   		   
   		 }  
   		
   		
   		
   	}
    
    @Override
   	public eformfield  getkey(int id,int rv,String type) {

   	    try {
   	    	String sql = null;
   	    	
   	    	
   	    	if(type.equals("order")) {
   	    		sql = "select * from eformfield WHERE field_formid =?  and  field_formrv=?  and (field_datareferid3 > 0  or  field_datareferid10)    LIMIT  1 ";
   	    	
   	    	}else if(type.equals("diff")) {
   	   	    		sql = "select * from eformfield WHERE field_formid =?  and  field_formrv=?  and  field_cal_type ='diff'   LIMIT  1 ";
   	    	}else {
   	    		sql = "select * from eformfield  WHERE field_formid =?  and  field_formrv=? and ( field_datareferid16 > 0  or  field_datareferid18 > 0 or  field_datareferid5 > 0 or  field_datareferid6 > 0"
   	    				+ " or  field_datareferid7 > 0 or  field_datareferid12 > 0 or  field_datareferid13 > 0 or  field_datareferid12 > 0 or  field_datareferid14 > 0) "
   	    				+ "   LIMIT  1 ";
   	    	}
   	    	
   	    	
   			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv},new eformfieldmapper());
   			return eformfields;
   		 
   		 }catch(EmptyResultDataAccessException e) {
   			 
   			 return null;
   			 //throw new RuntimeException("file ID does not exist");
   		    
   		 }catch(IncorrectResultSizeDataAccessException e) {
   		       
   			 throw new RuntimeException("More than one users with the same Id .......");
   		   
   		 }  
   		
   		
   		
   	}
    
    
    
    @Override
   	public eformfield  getPostion(int id,int rv,String postion) {

   	    try {
   	    	String sql = null;
   	    	
   	    	
   	    	
   	    		sql = "select * from eformfield WHERE field_formid =?  and  field_formrv=?  and field_postion =?    LIMIT  1 ";
   	    	
   	   
   	    	
   	    	
   			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,postion},new eformfieldmapper());
   			return eformfields;
   		 
   		 }catch(EmptyResultDataAccessException e) {
   			 
   			 return null;
   			 //throw new RuntimeException("file ID does not exist");
   		    
   		 }catch(IncorrectResultSizeDataAccessException e) {
   		       
   			 throw new RuntimeException("More than one users with the same Id .......");
   		   
   		 }  
   		
   		
   		
   	}
    
    
    
    
    
    @Override
	public eformfield getbycal(int id ) {

	    try {
	    	String sql = "select * from eformfield  WHERE field_id =?    ";
			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new eformfieldmapper());
			return eformfields;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
		
		
		
	}
    
    

    
    @Override
	public eformfield getCalposition(String type ,int id,int rv ){

    	String subquery= "";
    	
    	if(type.equals("Remaining")) {
    		subquery = " AND ( field_datareferid5 > 0 OR field_datareferid6 > 0   OR field_datareferid7 > 0  ) ";
    	}
    	else if(type.equals("Add")) {
    		subquery = " AND field_datareferid6 > 0 ";
    	}
    	else if(type.equals("Del")) {
    		subquery = " AND field_datareferid7 > 0 ";
    	}
    	else if(type.equals("Product")) {
    		subquery = " AND field_datareferid > 0 ";
    	}
    	else if(type.equals("Lot")) {
    		subquery = " AND field_datareferid2 > 0 ";
    	}
    	else if(type.equals("Unit")) {
    		subquery = " AND field_cal_type = 'unit' ";
    	}
    	else {
    		subquery = " AND field_datareferid15 > 0 ";
    	}
    	
    	
	    try {
	    	String sql = "select * from eformfield  WHERE field_formid =? AND field_formrv =?  "+subquery+" LIMIT 1  ";
			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv},new eformfieldmapper());
			return eformfields;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
		
		
		
	}
    
    
    

	@Override
	public void updatecal(String type,int no,int id) {
		
		String sql = "UPDATE eformfield " +
                "SET field_cal_type=?,"
                + "field_field_no2 = IF(field_field_no > 0, ?, field_field_no2), " +
                "    field_field_no = IF(field_field_no > 0, field_field_no, ?) " +
                "WHERE field_id = ?";
		
				//String sql = "UPDATE  eformfield  SET field_cal_type=?,field_field_no=? WHERE field_id  = ?";
				jdbcTemplate.update(sql,new Object[]{type,no,no,id});
	}
	

	@Override
	public void updatecal2(String type,int no,int id) {
				String sql = "UPDATE  eformfield  SET field_cal_type=?,field_field_no2=? WHERE field_id  = ?";
				jdbcTemplate.update(sql,new Object[]{type,no,id});
	}
	
	
	
	
    @Override
	public eformfield findById(int id ) {

	    try {
	    	String sql = "select * from eformfield  WHERE field_id =?    ";
			eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new eformfieldmapper());
			return eformfields;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }  
		
		
		
	}
    
	
	
	
	 @Override
		public eformfield getlabel(String label,int planid){
	              
		
		     String  sql = "select * from eformfield  WHERE   field_cal_type= ? and field_formid = ?  order by field_id   desc   limit 1 ";
			    try {
			    	eformfield eformfields  =  jdbcTemplate.queryForObject(sql,new Object[]{label,planid},new eformfieldmapper());
					return eformfields;
				 
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }  
				
				

		}
	
		@Override
		public void setType(int id,String type,int no) {

			String sql = "UPDATE eformfield SET field_cal_type= ?,field_field_no= ? WHERE field_id = ?";
			jdbcTemplate.update(sql,new Object[]{type,no,id});
		}
		

		@Override
		public void applyGroupLabel(int field_id, int value) {
			String sql = "UPDATE eformfield SET group_label_id= ? WHERE field_id = ?";
			jdbcTemplate.update(sql,new Object[]{value,field_id});
			
		}
		
		
		
		@Override
		public List<eformfield> getall() {
			String sql = "select * from eformfield    ";
			List<eformfield> eformfields  =  jdbcTemplate.query(sql,new eformfieldmapper());
			return eformfields;
		}
		
		@Override
		public List<BuildIn> getallBuildIn(int id) {
			String sql = "select * from buildin where id_refer=?  ";
			List<BuildIn> list  =  jdbcTemplate.query(sql,new Object[]{id},new BuildInmapper());
			return list;
		}
		
		
		@Override
		public void editAddconfigDataset(String allow_adding_items, String ids, int process_id) {

		    if (allow_adding_items == null || ids == null) return;

		    String[] allows = allow_adding_items.split(",");
		    String[] datasetIds = ids.split(",");

		    int size = Math.min(allows.length, datasetIds.length);

		    String sql =
		        "INSERT INTO field_config (allow_adding_items, id_refer, process_id) " +
		        "VALUES (?, ?, ?) " +
		        "ON DUPLICATE KEY UPDATE allow_adding_items = VALUES(allow_adding_items)";

		    IntStream.range(0, size).forEach(i -> {
		        jdbcTemplate.update(
		            sql,
		            allows[i].trim(),
		            datasetIds[i].trim(),
		            process_id
		        );
		    });
		}
		
		
		@Override
		public void editAddconfigData_access_interface(String data_access_interface, String ids, int process_id) {

		    if (data_access_interface == null || ids == null) return;

		    String[] allows = data_access_interface.split(",");
		    String[] datasetIds = ids.split(",");

		    int size = Math.min(allows.length, datasetIds.length);

		    String sql =
		        "INSERT INTO field_config (data_access_interface, id_refer, process_id) " +
		        "VALUES (?, ?, ?) " +
		        "ON DUPLICATE KEY UPDATE data_access_interface = VALUES(data_access_interface)";

		    IntStream.range(0, size).forEach(i -> {
		        jdbcTemplate.update(
		            sql,
		            allows[i].trim(),
		            datasetIds[i].trim(),
		            process_id
		        );
		    });
		}

	
	
}
