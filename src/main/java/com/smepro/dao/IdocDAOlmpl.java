package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.Idoc;
import com.smepro.models.attendance;
import com.smepro.models.flow;
import com.smepro.models.sub_processes;
import com.smepro.models.user;
import com.smepro.rowmapper.attendancemapper;
import com.smepro.rowmapper.attendancemapper2;
import com.smepro.rowmapper.flowmapper;
import com.smepro.rowmapper.idocmapper;
import com.smepro.rowmapper.usermapper2;
import com.smepro.service.sqlservice;
import com.smepro.service.userservice;




public class IdocDAOlmpl implements IdocDAO {

	
 @Autowired
	userDAO UserDAO;
 
 @Autowired
  userservice Userservice;
 
	
	private JdbcTemplate jdbcTemplate;

	public IdocDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<Idoc> getALLReceiver(int eform_id,int document_id,int document_rv,String step,int userid) {
		String sql = "SELECT * FROM idoc  WHERE eform_id = ? AND document_id = ? AND document_rv = ? AND current_step=? AND receiver =? "
				+ " order by date DESC  limit 1000";
		List<Idoc> receivers  =  jdbcTemplate.query(sql ,new Object[]{eform_id,document_id,document_rv,step,userid}, new idocmapper());
		return receivers;
	}

	@Override
	public List<Idoc> getALLSender(int eform_id,int document_id,int document_rv,String step,int userid) {
		String sql = "SELECT * FROM idoc  WHERE eform_id = ? AND document_id = ? AND document_rv = ? AND current_step=? AND sender =? "
				+ " order by date DESC  limit 1000";
		List<Idoc> senders  =  jdbcTemplate.query(sql ,new Object[]{eform_id,document_id,document_rv,step,userid}, new idocmapper());
		return senders;
	}
	
	@Override
	public List<Idoc> getALLCreator(int eform_id,int document_id,int document_rv,String step,int userid) {
		String sql = "SELECT * FROM idoc  WHERE eform_id = ? AND document_id = ? AND document_rv = ? AND current_step=? AND creator =? "
				+ " order by date DESC  limit 1000";
		List<Idoc> creators  =  jdbcTemplate.query(sql ,new Object[]{eform_id,document_id,document_rv,step,userid}, new idocmapper());
		return creators;
	}
	
	@Override
	public List<Idoc> getALL(int eform_id,int document_id,int document_rv,String step,int userid) {
		String sql = "SELECT * FROM idoc  WHERE eform_id = ? AND document_id = ? AND document_rv = ? AND current_step=? AND "
				+ "( creator =? OR sender =?  OR receiver =? ) "
				+ " order by date DESC  limit 1000";
		List<Idoc> idocs  =  jdbcTemplate.query(sql ,new Object[]{eform_id,document_id,document_rv,step,userid,userid,userid}, new idocmapper());
		return idocs;
	}

	
	
	@Override
	public int delete(int eform_id) {
		 String sql="delete from  idoc "
		 		+ " where eform_id = ?  " ;    
		 return jdbcTemplate.update(sql,new Object[]{eform_id});   
	}


	
	
	@Override
	public int save(Idoc Idocs) {

					System.out.println("7.1 save Idocs getCreator " + Idocs.getCreator());
					user  add_user = UserDAO.getbyId2(Idocs.getCreator());
					
					
					System.out.println("7.2 save Idocs");
						String sql = "INSERT INTO idoc(eform_id,document_id,document_rv,current_step,date,creator,receiver,current_step_int,data_nameadd,refer_user)"
								+ " VALUES(?,?,?,?,?,?,?,?,?,?);";
		
				          KeyHolder keyHolder = new GeneratedKeyHolder();
				
				          jdbcTemplate.update(connection -> {
				         	 
				              PreparedStatement ps = connection
				                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				                ps.setInt(1, Idocs.getEform_id());
				                ps.setInt(2,  Idocs.getDocument_id());
				                ps.setInt(3,  Idocs.getDocument_rv());
				                ps.setString(4,Idocs.getCurrent_step());
				                ps.setString(5, Idocs.getDate());
				                ps.setInt(6,Idocs.getCreator());
				                ps.setInt(7,Idocs.getReceiver());
				                ps.setInt(8,Idocs.getCurrent_step_int());
				                ps.setString(9,add_user.getUser_name());
				                ps.setString(10,String.valueOf(Idocs.getCreator()));
				                return ps;
				             
				          }, keyHolder);			
				          //String key = keyHolder.getKey();
				          System.out.println( keyHolder.getKey().intValue());
				          return  keyHolder.getKey().intValue();
				     // System.out.println( keyHolder.getKey());
				         
	
				         
	}

	
	
	@Override
	public Idoc getDocument(int eform_id,int document_id,int document_rv) {
		
		  try {
				 String sql="select * from idoc where eform_id = ? and document_id = ? and document_rv = ? ";    
				 return jdbcTemplate.queryForObject(sql, new Object[]{eform_id,document_id,document_rv},new idocmapper());  
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }

		 
	
	}
	
	@Override
	public Idoc getDocument(int eform_id) {
		   System.err.println("LoadApp ->getEdata->Create -> LoadTransaction->getDocument "+eform_id); 
		  try {
				 String sql="select * from idoc where eform_id = ? ";    
				 return jdbcTemplate.queryForObject(sql, new Object[]{eform_id},new idocmapper());  
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		 }catch(IncorrectResultSizeDataAccessException e) {
			 throw new RuntimeException("More than one LoadTransaction with the same Id ......."+eform_id);
		 }
	}
	
	
	
	@Override
	public Idoc getDocumentById(int id) {
	
		  try {
				 String sql="select * from idoc where id = ? ";    
				 return jdbcTemplate.queryForObject(sql, new Object[]{id},new idocmapper());  
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }

		 
	
	}
	
	

	@Override
	public int transition(int eformId, int send, int to, String next, int flow) {

	    System.out.println("---> flow <---- " + flow);
	    System.out.println("---> eformId <---- " + eformId);

	    user  send_user = UserDAO.getbyId2(send);
	    user  send_to = UserDAO.getbyId2(to);
	    if(send_user == null) {
	        send_user =  UserDAO.getbyusername2(Userservice.getLoggedName());  
	    }
	    
	    String send_to_name ="";
	    if(send_to != null) {
	    	send_to_name  = send_to.getUser_name();
	    }
	 
	    
	    
	    
	    // ---------------------------------------------------------
	    // 1. ดึงข้อมูล "คนเก่า" และ "Creator" ออกมาจาก DB ก่อน
	    // ---------------------------------------------------------
	    String querySql = "SELECT sender, receiver, creator, refer_user FROM idoc WHERE eform_id = ?";
	    Map<String, Object> oldData = null;
	    try {
	        oldData = jdbcTemplate.queryForMap(querySql, eformId);
	    } catch (Exception e) {
	        // กรณีไม่เจอ Data (เผื่อไว้)
	        oldData = new HashMap<>();
	    }

	    // ---------------------------------------------------------
	    // 2. รวม ID ทั้งหมดลงใน Set (เพื่อให้ไม่ซ้ำ)
	    // ---------------------------------------------------------
	    Set<String> userIdSet = new HashSet<>();

	    // 2.1 ใส่คนใหม่ (Argument ที่ส่งเข้ามา)
	    userIdSet.add(String.valueOf(send));
	    userIdSet.add(String.valueOf(to));

	    // 2.2 ใส่คนเก่า (ที่ดึงมาจาก DB)
	    if (oldData.get("sender") != null) userIdSet.add(String.valueOf(oldData.get("sender")));
	    if (oldData.get("receiver") != null) userIdSet.add(String.valueOf(oldData.get("receiver")));
	    if (oldData.get("creator") != null) userIdSet.add(String.valueOf(oldData.get("creator")));

	    
	    // 2.3 **สำคัญ** เอา refer_user เดิมมารวมด้วย (ถ้าต้องการสะสมไปเรื่อยๆ ตั้งแต่เริ่ม flow)
	    String oldRefer = (String) oldData.get("refer_user");
	    if (oldRefer != null && !oldRefer.trim().isEmpty()) {
	        // แยกด้วย comma แล้วยัดลง Set
	        userIdSet.addAll(Arrays.asList(oldRefer.split(","))); 
	    }

	    // 2.4 แปลงกลับเป็น String (เช่น "15,14,20") โดยตัดค่า null หรือ 0 ทิ้งถ้ามี
	    String newReferUser = userIdSet.stream()
	            .filter(s -> s != null && !s.equals("0") && !s.equals("null"))
	            .collect(Collectors.joining(","));

	    // ---------------------------------------------------------
	    // 3. ทำการ Update (เพิ่ม refer_user ลงใน SQL)
	    // ---------------------------------------------------------
	    
	    
	    LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	   	 String date = localDate.format(formatter);
		   
	   	 
	    if (flow > 0) {
	        String sql = "UPDATE idoc SET current_step_int = ?, sender = ?, receiver = ?, current_step = ?, refer_user = ?,date_update=?,data_namesend=?,data_namereceiver=?"
	        		+ ", remarks_rejected = '' WHERE eform_id = ?";    
	        return jdbcTemplate.update(sql, new Object[]{flow, send, to, next, newReferUser,date
	        		,send_user.getUser_name(),send_to_name, eformId});  
	    } else {
	        String sql = "UPDATE idoc SET current_step_int = current_step_int + 1, sender = ?, receiver = ?, current_step = ?, remarks_rejected = '', refer_user = ?"
	        		+ ",date_update=?,data_namesend=?,data_namereceiver=? WHERE eform_id = ?";    
	        return jdbcTemplate.update(sql, new Object[]{send, to, next, newReferUser,date
	        		,send_to_name, eformId});  
	    }
	}
	
	@Override
	public int recallClosing_status(int eformId) {
	
			 String sql="update idoc set closing_status = NULL  where eform_id =? ";    
			 return jdbcTemplate.update(sql, new Object[]{eformId});  

	}
	
	
	
	@Override
	public int backtransition(int eformId,int send,int to,String next,int flow) {
	
		 String sql="update idoc set current_step_int =?,sender = ?,receiver = ?,current_step=?,remarks_rejected='Rejected' where eform_id =? ";    
		 return jdbcTemplate.update(sql, new Object[]{flow,send,to,next,eformId});  
	
	
	}
	@Override
	public int transition(int eformId,String next) {
	
			 String sql="update idoc set current_step_int = current_step_int+1,current_step=? where eform_id =? ";    
			 return jdbcTemplate.update(sql, new Object[]{next,eformId});  

	}
	@Override
	public void update(Idoc flow) {
			 String sql="update idoc set refer_user =?  where id =? ";    
			  jdbcTemplate.update(sql, new Object[]{flow.getRefer_user(),flow.getId()});  

	}
	
	
	
	
	@Override
	public int transition(int eformId,int cleared) {
				 String sql="update idoc set cleared=? where eform_id =? ";    
				 return jdbcTemplate.update(sql, new Object[]{cleared,eformId});  
	}
	
	
	@Override
	public int transition_del(int eformId) {
				 String sql="DELETE FROM idoc where eform_id =? ";    
				 return jdbcTemplate.update(sql, new Object[]{eformId});  
	}
	@Override
	public int setRemarks(int eformId,String note) {
				 String sql="update idoc set remarks_rejected = ? where eform_id =? ";    
				 return jdbcTemplate.update(sql, new Object[]{note,eformId});  
	}



	@Override
	public int transition(int eformId, int send, int to, String next) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public void commit(int data_id, String action_type, String action_to,int action_id){
		String sql = "UPDATE  idoc SET action_type= ? , action_to = ? , action_id =? WHERE eform_id =? ";
		jdbcTemplate.update(sql, new Object[]{action_type,action_to,action_id,data_id}); 
	         
	}
	
	@Override
	public void end(int eform_id, String label,String clear,String closing_status_rejected,String date_end){
		
	    System.out.println(" closing_status  ...."+label);
	    System.out.println("cleared ...."+clear);
	    System.out.println(" eform_id  ...."+eform_id);
		
	 	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	   	 String date = date_end.equals("-") ?   localDate.format(formatter)  :  date_end;
	    
	    
	    
		String sql = "UPDATE  idoc SET closing_status =?,cleared=?,closing_status_rejected=? ,date_end=?   WHERE eform_id =? ";
		jdbcTemplate.update(sql, new Object[]{label,clear,closing_status_rejected,date,eform_id}); 
		
		


	}

	
	
	
	
	
	@Override
	public void endAfterRevis(int eform_id, String closing_status,int current_step_int){
		
	    System.out.println(" eform_id  ...."+eform_id);
	 	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	   	 String date = localDate.format(formatter);

		String sql = "UPDATE  idoc SET closing_status =? ,date_end=?,current_step_int=? ,current_step='Completion',request_to_edit=0  WHERE eform_id =? ";
		jdbcTemplate.update(sql, new Object[]{closing_status,date,current_step_int,eform_id}); 
		
		


	}
	
	
	
	
	@Override
	public void updateRequestToEdit(int dataId, int flagValue,String closing_status,int to,int current_step_int) {
		
			
		 	 LocalDateTime localDate = LocalDateTime.now();//For reference
		   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		   	 String date = localDate.format(formatter);	
        String sql = "UPDATE idoc SET request_to_edit = ?, closing_status =?,receiver=?,current_step_int= ?,current_step= 'Create'   WHERE eform_id = ?";
        jdbcTemplate.update(sql, flagValue,closing_status, to,current_step_int, dataId);
    }
	
	

	@Override
	public void end(int eform_id, String label){
	
		String sql = "UPDATE  idoc SET closing_status =? WHERE eform_id =? ";
		jdbcTemplate.update(sql, new Object[]{label,eform_id}); 		

	}

	
	

	@Override
	public flow getFlow(int data_id, String flow_step) {
		
		try {
			String sql = "SELECT * FROM   flow  WHERE flow_type =?  AND edata_id =?  limit 1";
			flow data = jdbcTemplate.queryForObject(sql, new Object[]{flow_step,data_id}, new flowmapper()); 
			return data;
		}catch(Exception e) {
			
			return null;
		}
	
	}
	
	@Override
	public void SaveAllIdocExtension(final List<Idoc> idocList) {
        // 1. เตรียมคำสั่ง SQL (ลิสต์ชื่อคอลัมน์ให้ตรงตาม Table จริงใน DB นะครับ)
        String sql = "INSERT INTO idoc " +
                     "(eform_id, reference_oldsys, customer_oldsys, sale_oldsys, qttotal_oldsys, " +
                     "current_step, date, creator, sender, receiver, refer_user,  closing_status, remarks,document_id,document_rv"
                     + ",date_update,data_nameadd,data_namesend,data_namereceiver,current_step_int) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,0,0, ?, ?, ?, ?, ?)";

    
   
        
        /*document_id
         * ,document_rv,eform_id
         * eform_id
         * 
         * 
         * ต้องกลับมาเติ่ม
         * */
        
        
        // 2. ใช้ batchUpdate เพื่อความเร็วสูง
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Idoc doc = idocList.get(i);
                
                // 💉 Mapping ข้อมูลจาก Object เข้าสู่เครื่องหมาย ? (Index เริ่มที่ 1)
                ps.setInt(1, 0);
                ps.setString(2, doc.getReference_oldsys());
                ps.setString(3, doc.getCustomer_oldsys());
                ps.setString(4, doc.getSale_oldsys());
                ps.setString(5, doc.getQttotal_oldsys());
                ps.setString(6, doc.getCurrent_step());
                ps.setString(7, doc.getDate());
                ps.setInt(8, doc.getCreator());
                ps.setInt(9, doc.getSender());
                ps.setInt(10, doc.getReceiver());
                ps.setString(11, doc.getRefer_user()); // สมมติว่าเป็น int
                ps.setString(12, doc.getClosing_status());
                ps.setString(13, doc.getRemarks());
                ps.setString(14, doc.getDate_update());
                ps.setString(15, doc.getData_nameadd());
                ps.setString(16, doc.getData_namesend());
                ps.setString(17, doc.getData_namereceiver());
                ps.setInt(18, doc.getCurrent_step_int());
            }
            
 
            @Override
            public int getBatchSize() {
                return idocList.size(); // บอก JDBC ว่ามีทั้งหมดกี่แถว
            }
        });
        
        System.out.println("🚀 Batch Insert Completed: " + idocList.size() + " rows.");
    }
	
    @Override
	public void UpdateIdocBatch(List<Idoc> idocList) {
	    // 🎯 SQL สั่งอัปเดต 3 ฟิลด์ โดยมีเงื่อนไข WHERE reference_oldsys = ?
	    String sql = "UPDATE idoc SET eform_id = ?, document_id = ?, document_rv = ? WHERE reference_oldsys = ?";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        
	        @Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	            Idoc doc = idocList.get(i);
	            
	            // ฉีดข้อมูลเข้า ? ทั้ง 4 ตัว
	            ps.setInt(1, doc.getEform_id());        // ? ตัวที่ 1 (ค่าใหม่)
	            ps.setInt(2, doc.getDocument_id());     // ? ตัวที่ 2 (ค่าใหม่)
	            ps.setInt(3, doc.getDocument_rv());     // ? ตัวที่ 3 (ค่าใหม่)
	            ps.setString(4, doc.getReference_oldsys()); // 🎯 ? ตัวที่ 4 (เงื่อนไข WHERE ค้นหาเป้าหมาย)
	        }

	        @Override
	        public int getBatchSize() {
	            return idocList.size(); // จำนวนแถวที่ต้องอัปเดต
	        }
	    });

	    System.out.println("🚀 Batch Updated Idoc: " + idocList.size() + " records successfully mapped with oldsys!");
	}



	@Override
	public void setExpires(int data_id, String closing_status_expired, String sub_process_expired) {

	 	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	   	 String date = localDate.format(formatter);
	   	 
	   	 if(sub_process_expired != null) {
	   		 String[] subprocess_expired = sub_process_expired.split("_");
	   		 
	   		String sql = "UPDATE  idoc SET closing_status =? ,date_end=?,expired= 1,current_step_int=?,current_step=?   WHERE eform_id =? AND  current_step = 'Proceed'  ";
			jdbcTemplate.update(sql, new Object[]{closing_status_expired,date,subprocess_expired[0],subprocess_expired[1],data_id}); 
	   	 }else {
	   		String sql = "UPDATE  idoc SET closing_status =? ,date_end=?,expired= 1   WHERE eform_id =? AND  current_step = 'Proceed'  ";
			jdbcTemplate.update(sql, new Object[]{closing_status_expired,date,data_id}); 
	   		 
	   	 }
	   	 
	

		 
		
	}
	
	
}
