package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.dar;
import com.smepro.models.darsum;
import com.smepro.models.dataset;
import com.smepro.models.document_no;
import com.smepro.models.showdar;
import com.smepro.rowmapper.darmapper;
import com.smepro.rowmapper.darmapper2;
import com.smepro.rowmapper.darmapper3;
import com.smepro.rowmapper.darsummapper;
import com.smepro.rowmapper.darsummapper2;
import com.smepro.rowmapper.document_nomapper;
import com.smepro.rowmapper.upload_pnmapper;

public class darDAOlmpl implements darDAO {

	
	private JdbcTemplate jdbcTemplate;
	
	public darDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	@Override
	public List<dar> getall() {
		String sql = "SELECT * FROM dar  order by dar_date_add desc limit 3000";
		List<dar> dars  =  jdbcTemplate.query(sql,new Object[]{},new darmapper());
		return dars;
	}
	
	
	@Override
	public List<dar> getallPending() {
		String sql = "SELECT * FROM dar where dar_flow != 3  order by dar_date_add desc limit 3000";
		List<dar> dars  =  jdbcTemplate.query(sql,new Object[]{},new darmapper());
		return dars;
	}
	
	
	@Override
	public List<showdar> getallbyfile(String fileno) {
		String sql = "SELECT * FROM dar LEFT JOIN users ON dar.dar_id_add = users.user_id  where dar_doc_id=? and dar_flow > 0 or dar_docold_id=? and dar_flow > 0  order by dar_id desc limit 3000";
		List<showdar> dars  =  jdbcTemplate.query(sql,new Object[]{fileno,fileno},new darmapper2());
		return dars;
	}
	
	@Override
	public List<showdar> getallbyfile2(String fileno,int darid) {
		String sql = "SELECT * FROM dar LEFT JOIN users ON dar.dar_id_add = users.user_id  where dar_doc_id=? and dar_id != ? and dar_flow =3  order by dar_id desc limit 3000";
		List<showdar> dars  =  jdbcTemplate.query(sql,new Object[]{fileno,darid},new darmapper2());
		return dars;
	}
	
	
	@Override
	public List<dar> getall2(int id) {
		String sql = "SELECT * FROM dar where dar_id_add=? and dar_flow =0 or dar_id_add=? and dar_flow =-1  order by dar_id desc limit 3000";
		List<dar> dars  =  jdbcTemplate.query(sql,new Object[]{id,id},new darmapper());
		return dars;
	}
	
	
	
	
	@Override
	public int CountFlow1(int id) {
		String sql = "SELECT COUNT(*) FROM dar where (dar_flow =0 or dar_flow =-1)  and ( dar_step=? or dar_id_add=?)  ";
		return  jdbcTemplate.queryForObject(sql,new Object[]{id,id}, Integer.class);
	}
	@Override
	public int CountFlow2(int id) {
		String sql = "SELECT COUNT(*) FROM dar where dar_flow =1 and dar_step=?  ";
		return  jdbcTemplate.queryForObject(sql,new Object[]{id}, Integer.class);
	}
	
	@Override
	public int CountFlow3(int id) {
		String sql = "SELECT COUNT(*) FROM dar where dar_flow= 2  and dar_step=?  ";
		return  jdbcTemplate.queryForObject(sql,new Object[]{id}, Integer.class);
	}
	
	
	
	
	
	@Override
	public List<dar> getall3(int id) {
		String sql = "SELECT * FROM dar where dar_step=? and dar_flow =1 or dar_step=? and dar_flow =-2  order by dar_id desc limit 3000";
		List<dar> dars  =  jdbcTemplate.query(sql,new Object[]{id,id},new darmapper());
		return dars;
	}
	@Override
	public List<dar> getall4(int id) {
		String sql = "SELECT * FROM dar where dar_step=? and dar_flow =2  order by dar_id desc limit 3000";
		List<dar> dars  =  jdbcTemplate.query(sql,new Object[]{id},new darmapper());
		return dars;
	}
	
	
	
	@Override
	public int save(dar dars, int id) {
	
				String sql = "INSERT INTO dar(dar_request,dar_type,dar_docold_id"
						+ ",dar_doc_id,dar_doc_name,dar_doc_page"
						+ ",dar_doc_date,dar_doc_end,dar_doc_rev,dar_docold_date"
						+ ",dar_docold_end,dar_docold_rev,dar_doc_remark,dar_id_add"
						+ ",dar_flow,dar_step,dar_date_add,dar_jd,dar_doc_remarkfull,dar_nameadd,eform,oldeform,dar_department)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		          KeyHolder keyHolder = new GeneratedKeyHolder();
		
		          jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection
		                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1, dars.getDar_request());
		                ps.setString(2, dars.getDar_type());
		                ps.setString(3, dars.getDar_docold_id());
		                ps.setString(4, dars.getDar_doc_id());
		                ps.setString(5, dars.getDar_doc_name());
		                ps.setString(6, dars.getDar_doc_page());
		                ps.setString(7, dars.getDar_doc_date() == "" ? null : dars.getDar_doc_date() );
		                ps.setString(8, dars.getDar_doc_end() == "" ? null : dars.getDar_doc_end());
		                ps.setString(9, dars.getDar_doc_rev());
		                ps.setString(10, dars.getDar_docold_date() == "" ? null :  dars.getDar_docold_date());
		                ps.setString(11, dars.getDar_docold_end() == "" ? null : dars.getDar_docold_end() );
		                ps.setString(12, dars.getDar_docold_rev());
		                ps.setString(13, dars.getDar_doc_remark());
		                ps.setInt(14, id);
		                ps.setInt(15, 0);
		                ps.setInt(16, 0);
		                ps.setString(17, dars.getDar_date_add());
		                ps.setString(18, dars.getDar_jd());
		                ps.setString(19, dars.getDar_doc_remarkfull());
		                ps.setString(20, dars.getDar_nameadd());
		                ps.setString(21, dars.getEform());
		                ps.setString(22, dars.getOldeform());
		                ps.setInt(23, dars.getDar_department());
		                return ps;
		             
		          }, keyHolder);			
		          //String key = keyHolder.getKey();
		          System.out.println( keyHolder.getKey().intValue());
		         return  keyHolder.getKey().intValue();
		     // System.out.println( keyHolder.getKey());

	}

	@Override
	public dar getbyId(int id) {
		 String sql="select * from dar where dar_id=?  ";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{id},new darmapper()); 
	}


	@Override
	public int update(dar dars) {
		
		
	    String date1 = dars.getDar_doc_date() == "" ?  null :  "'" +dars.getDar_doc_date()+ "'";
        String date2 =  dars.getDar_doc_end() == "" ? null : "'" +dars.getDar_doc_end()+ "'";
        String date3 = dars.getDar_docold_date() == "" ? null :  dars.getDar_docold_date();
        String date4 =  dars.getDar_docold_end() == "" ? null : dars.getDar_docold_end();
    

        
        
		  String sql=  "UPDATE dar SET dar_request='"+dars.getDar_request()+"',"
		  		        + "dar_type='"+dars.getDar_type()+"' ,"
		  				+ "dar_docold_id='"+dars.getDar_docold_id()+"' ,"
		  				+ "dar_doc_id='"+dars.getDar_doc_id()+"' ,"		
                        + "dar_doc_name='"+dars.getDar_doc_name()+"' ,"
		                + "dar_doc_page='"+dars.getDar_doc_page()+"' ,"
				        + "dar_doc_date="+date1+","
				        + "dar_doc_end="+date2+","
						+ "dar_doc_rev='"+dars.getDar_doc_rev()+"' ,"
						+ "dar_docold_date='"+date3+"' ,"
						+ "dar_docold_end='"+date4+"' ,"
						+ "dar_docold_rev='"+dars.getDar_docold_rev()+"' ,"
						+ "dar_jd='"+dars.getDar_jd()+"' ,"
						+ "dar_doc_remarkfull='"+dars.getDar_doc_remarkfull()+"' ,"
						+ "dar_doc_remark='"+dars.getDar_doc_remark()+"', "
						+ "dar_department='"+dars.getDar_department()+"', "
						+ "eform='"+dars.getEform()+"' "
		  		+ "WHERE  dar_id="+dars.getDar_id();
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	@Override
	public int updateresponsible(int id,String nameadd,String namecheck,String nameapp) {
		
		  String sql=  "UPDATE dar SET dar_nameadd='"+nameadd+"',"
		  		        + "dar_namecheck='"+namecheck+"' ,"
		  				+ "dar_nameapp='"+nameapp+"' "
		  		+ "WHERE  dar_id="+id;
		    return jdbcTemplate.update(sql);   
	}
	
	
	
	@Override
	public int flow( int id ,int flow,int step) {
		  String sql=  "UPDATE dar SET dar_flow = '"+ flow +"'"
			  		+ ",dar_step ='"+step+"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	
	@Override
	public int check( int id ,int flow,int step,int by) {
		  String sql=  "UPDATE dar SET dar_flow = '"+ flow +"'"
			  		+ ",dar_step ='"+step+"'"
			  		+ ",dar_id_check ='"+by+"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public int app( int id ,int flow,int step,int by) {
		  String sql=  "UPDATE dar SET dar_flow = '"+ flow +"'"
				  + ",dar_step ='"+step+"'"
			  		+ ",dar_id_app ='"+by+"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public int uncheck( int id ,int flow,int step,int by) {
		  String sql=  "UPDATE dar SET dar_flow = '"+ flow +"'"
				    + ",dar_step ='"+step+"'"
			  		+ ",dar_id_check ='"+by+"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public int unapp( int id ,int flow,int step,int by) {
		  String sql=  "UPDATE dar SET dar_flow = '"+ flow +"'"
				  + ",dar_step ='"+step+"'"
			  		+ ",dar_id_app ='"+by+"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
		
	@Override
	public int updatenotecheck( int id,String note) {
		  String sql=  "UPDATE dar SET dar_note1 = '"+ note +"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public int updatenoteapp( int id,String note) {
		  String sql=  "UPDATE dar SET dar_note2 = '"+ note +"'"
			  		+"  WHERE  dar_id ="+id;
			    return jdbcTemplate.update(sql); 
	}	
	
	
	@Override
	public int delete(int id,int idadd) {
		 String sql="delete from dar where  dar_id_add = '"+idadd+"' and dar_flow <=0 and  dar_id="+id+"";    
		    return jdbcTemplate.update(sql);
	}

	@Override
	public int gettotal(){
	    String sql = "SELECT count(*) FROM dar  ";

	    int count = jdbcTemplate.queryForObject(sql, Integer.class);

	    return count;
	}
	
	
	@Override
	public int gettotalunfinished(){
	    String sql = "SELECT count(*) FROM dar  where dar_flow != 3 ";

	    int count = jdbcTemplate.queryForObject(sql, Integer.class);

	    return count;
	}
	
	@Override
	public List<dar> getdarunfinished() {
		String sql = "SELECT count(dar.dar_id_add) as dar_step,users.user_name FROM dar   "
				+ "LEFT  JOIN users  ON dar.dar_id_add = users.user_id   where dar.dar_flow != 3  "
				+ "group by dar.dar_id_add ";
		List<dar> dars  =  jdbcTemplate.query(sql , new darmapper3());
		return dars;
	}
	
	
	@Override
	public darsum getsubdar(String request,String years) {
		 String sql="select "
		 		+ " SUM(MONTH(dar_date_add) = 1 )  as  month1, "
		 		+ " SUM(MONTH(dar_date_add) = 2 )  as  month2, "
		 		+ " SUM(MONTH(dar_date_add) = 3 )  as  month3, "
		 		+ " SUM(MONTH(dar_date_add) = 4 )  as  month4, "
		 		+ " SUM(MONTH(dar_date_add) = 5 )  as  month5, "
		 		+ " SUM(MONTH(dar_date_add) = 6 )  as  month6, "
		 		+ " SUM(MONTH(dar_date_add) = 7 )  as  month7, "
		 		+ " SUM(MONTH(dar_date_add) = 8 )  as  month8, "
		 		+ " SUM(MONTH(dar_date_add) = 9 )  as  month9, "
		 		+ " SUM(MONTH(dar_date_add) = 10 )  as  month10, "
		 		+ " SUM(MONTH(dar_date_add) = 11 )  as  month11, "
		 		+ " SUM(MONTH(dar_date_add) = 12 )  as  month12 "
		 		+ "from dar where dar_request = ? and YEAR(dar_date_add) = ?   ";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{request,years},new darsummapper()); 
	}
	@Override
	public darsum getsubdar(String request,String years1,String years2,String years3) {
		 String sql="select "
		 		+ " SUM(YEAR(dar_date_add) = ? )  as  year1, "
		 		+ " SUM(YEAR(dar_date_add) = ? )  as  year2, "
		 		+ " SUM(YEAR(dar_date_add) = ? )  as  year3 "
		 		+ "from dar where dar_request = ? and YEAR(dar_date_add) >= ?   and YEAR(dar_date_add) <= ?  ";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{years1,years2,years3,request,years3,years1},new darsummapper2()); 
	}
	@Override
	public int getmaindar(int month,String years){
	    String sql = "SELECT count(*) FROM dar where MONTH(dar_date_add) = ? and    YEAR(dar_date_add) = ?    ";

	    int count = jdbcTemplate.queryForObject(sql, new Object[]{month,years}, Integer.class);

	    return count;
	}
	
	@Override
	public int getmaindar(String years){
	    String sql = "SELECT count(*) FROM dar where   YEAR(dar_date_add) = ?    ";

	    int count = jdbcTemplate.queryForObject(sql, new Object[]{years}, Integer.class);

	    return count;
	}
	
	
	
	@Override
	public List<dar> getalltotalunfinished() {
		String sql = "SELECT count(dar.dar_id_add) as dar_step,users.user_name,users.user_img FROM dar   "
				+ "LEFT  JOIN users  ON dar.dar_id_add = users.user_id   where dar.dar_flow != 3  "
				+ "group by dar.dar_id_add order by count(dar.dar_id_add) DESC  limit 5 ";
		List<dar> dars  =  jdbcTemplate.query(sql , new darmapper3());
		return dars;
	}
	
	
	
    @Override
    public int gettotalbytype(String years,String type) {
		String sql = "SELECT count(*) FROM dar  where dar_flow = 3 and YEAR(dar_date_add) =?  and dar_request =?  ";
			    int count = jdbcTemplate.queryForObject(sql, new Object[]{years,type}, Integer.class);
			    return count;
	}

    
    
    @Override
	public List<document_no> getalldocument() {
		String sql = "SELECT * FROM document_no LEFT JOIN  department ON document_no.document_department = department.department_id "
				+ "  order by document_no_pattern desc limit 3000";
		List<document_no> document_no  =  jdbcTemplate.query(sql,new Object[]{},new document_nomapper());
		return document_no;
	}
	
	@Override
	public document_no getdocumentbyId(int id) {
		 String sql="select * from document_no where document_no_id=?  ";    
		    return jdbcTemplate.queryForObject(sql, new Object[]{id},new document_nomapper()); 
	}

	
	@Override
	public document_no getdocumentNO(int department,String doc) {
		
		    
		    try {
		    	 String sql="select * from document_no where document_department=? AND document_doc =? limit 1  ";    
				 return jdbcTemplate.queryForObject(sql, new Object[]{department,doc},new document_nomapper()); 
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 String sql="select * from document_no where  document_doc =? limit 1  ";    
			 return jdbcTemplate.queryForObject(sql, new Object[]{doc},new document_nomapper()); 
			 
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	}
	
	
	@Override
	public void setAutoid( int id,int step) {
		  String sql=  "UPDATE document_no SET autoid = ?  WHERE  document_no_id =  ? ";
			     jdbcTemplate.update(sql, new Object[]{id,step}); 
	}
	
	@Override
	public void deldocument( int id) {
		  String sql=  "DELETE FROM  document_no  WHERE document_no_id =? ";
			     jdbcTemplate.update(sql, new Object[]{id}); 
	}
	@Override
	public void editdocument( document_no document_nos) {
		
		Object[] datasetInfo = { 
				document_nos.getDocument_no_pattern(),
				document_nos.getDocument_no_note(),
				document_nos.getDocument_department(),
				document_nos.getDocument_doc(),
				document_nos.getDocument_no_id(),
				};
		
		  String sql=  "UPDATE document_no SET document_no_pattern = ?,document_no_note=?,document_department=?,document_doc=?  WHERE  document_no_id =? ";
			     jdbcTemplate.update(sql,datasetInfo); 
	}
	
	@Override
	public void savedocument(document_no document_nos) {
		Object[] datasetInfos = { 
				document_nos.getDocument_no_pattern(),
				document_nos.getDocument_no_note(),
				document_nos.getDocument_department(),
				document_nos.getDocument_doc(),
				};
				String sql = "INSERT INTO document_no(document_no_pattern,document_no_note,document_department,document_doc) "
						+ "VALUES(?,?,?,?)";
				 jdbcTemplate.update(sql,datasetInfos);

	}

    
}
