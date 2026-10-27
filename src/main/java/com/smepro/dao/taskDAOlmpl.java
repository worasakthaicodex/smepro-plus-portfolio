package com.smepro.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import com.smepro.models.Keyactivity;
import com.smepro.models.Problem;
import com.smepro.models.Progress;
import com.smepro.models.backup;
import com.smepro.models.cm;
import com.smepro.models.task;
import com.smepro.models.user;
import com.smepro.rowmapper.Progressmapper;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.cmmapper;
import com.smepro.rowmapper.problemmapper;
import com.smepro.rowmapper.taskmapper;
import com.smepro.rowmapper.usermapper;


public class taskDAOlmpl implements taskDAO {

	private JdbcTemplate jdbcTemplate;
	
	public taskDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	

	
	@Override
	public List<task> getall(int idrefer,String name,int idadd) {
		String sql = "SELECT * FROM task "
				+ "where task_idrefer = ? and  task_status = 1  and  (find_in_set(?,task_iduser) or  task_idadd =?) "
				+ "order by task_datestart asc limit 15";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{idrefer,name,idadd},new taskmapper());
		return tasks;
	}
	
	
	@Override
	public List<task> getalloriginal(int idrefer) {
		String sql = "SELECT * FROM original_task "
				+ "where task_idrefer = ? ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{idrefer},new taskmapper());
		return tasks;
	}
	
	
	
	
	
	@Override
	public List<task> getalloriginal(int idrefer,String name,int idadd) {
		String sql = "SELECT * FROM original_task "
				+ "where task_idrefer = ? and  task_status = 1  and  (find_in_set(?,task_iduser) or  task_idadd =?) "
				+ "order by task_datestart asc limit 15";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{idrefer,name,idadd},new taskmapper());
		return tasks;
	}
	
	
	@Override
	public List<task> getall(int idrefer) {
		String sql = "SELECT * FROM task "
				+ "where  task_idrefer = ? ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{idrefer},new taskmapper());
		return tasks;
	}
	
	
	@Override
	public int getCountAllStatus(int idrefer) {
		String sql = "SELECT COUNT(*) FROM task "
				+ " where  keyactivity_id = ? and task_status != 2 ";
		return jdbcTemplate.queryForObject(sql,new Object[]{idrefer},Integer.class);
	}
	
	
	
	@Override
	public List<task> getallKeyactivityId(int keyactivity_id) {
		String sql = "SELECT * FROM task "
				+ "where  keyactivity_id = ? ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{keyactivity_id},new taskmapper());
		return tasks;
	}

	
	
	@Override
	public void updateBatchStatus(List<task> upload_pn) {

		String sql = "UPDATE task SET task_status=?   WHERE task_id =? ";
		
      jdbcTemplate.batchUpdate(sql,upload_pn,1000,
                new ParameterizedPreparedStatementSetter<task>() {
                    public void setValues(PreparedStatement ps, task argument) 
						throws SQLException {
                        ps.setInt(1, argument.getTask_status());
                        ps.setInt(2, argument.getTask_id());
                    }
                });
    }
	
	
	
	
	
	@Override
	public List<task> getallMasterBudgetId(int getallMasterBudgetId) {
		String sql = "SELECT * FROM task "
				+ "where  budget_source1 = ? ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{getallMasterBudgetId},new taskmapper());
		return tasks;
	}
	
	
	
	@Override
	public List<task> getallbyProject(user users) {
		String sql = "SELECT * FROM task "
				+ "where  task_status = 1 and task_datestart >= NOW()  and  (find_in_set(?,task_iduser) or  task_idadd =?) ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{users.getUser_name(),users.getUser_id()},new taskmapper());
		return tasks;
	}
	
	
	@Override
	public int getnum(int id_refer,int status,user users) {
		String sql = "SELECT COUNT(*) FROM task "
				+ "where  task_idrefer = ?  and task_status = ?  and  (find_in_set(?,task_iduser) or  task_idadd =?)  ";
		return jdbcTemplate.queryForObject(sql,new Object[]{id_refer,status,users.getUser_name(),users.getUser_id()},Integer.class);

	}
	
	@Override
	public int getnum(int id_refer,int status) {
		String sql = "SELECT COUNT(*) FROM task "
				+ "where  task_idrefer = ?  and task_status = ?    ";
		return jdbcTemplate.queryForObject(sql,new Object[]{id_refer,status},Integer.class);

	}
	@Override
	public int getnumnow(int id_refer,int status) {
		String sql = "SELECT COUNT(*) FROM task "
				+ "where  task_idrefer = ?  and task_status = ? and task_datestart <= NOW()     ";
		return jdbcTemplate.queryForObject(sql,new Object[]{id_refer,status},Integer.class);

	}
	
	@Override
	public int getnumnow(int id_refer,int status,user users) {
		String sql = "SELECT COUNT(*) FROM task "
				+ "where  task_idrefer = ?  and task_status = ? and task_datestart <= NOW()   and  (find_in_set(?,task_iduser) or  task_idadd =?)  ";
		return jdbcTemplate.queryForObject(sql,new Object[]{id_refer,status,users.getUser_name(),users.getUser_id()},Integer.class);

	}
	
	@Override
	public int getnumbyProject(user users) {
		String sql = "SELECT COUNT(*) FROM task "
				+ "where  task_status = 1 and task_datestart <= NOW()  and  (find_in_set(?,task_iduser) or task_iduser =? or   task_idadd =?) ";
		return jdbcTemplate.queryForObject(sql,new Object[]{users.getUser_name(),users.getUser_name(),users.getUser_id()},Integer.class);
	}
	
	
	@Override
	public int getnumbyProjectNear(user users) {
	    String sql = "SELECT COUNT(*) FROM task "
	            + "WHERE task_status = 1 "
	            + "AND task_datestart >= DATE_SUB(NOW(), INTERVAL 3 DAY) " // 3 วันก่อนกำหนด
	            + "AND task_datestart < NOW() " // ยังไม่ถึงกำหนดส่ง
	            + "AND (FIND_IN_SET(?, task_iduser) OR task_iduser = ? OR task_idadd = ?)";
	    return jdbcTemplate.queryForObject(sql, 
	        new Object[]{users.getUser_name(), users.getUser_name(), users.getUser_id()}, 
	        Integer.class);
	}

	@Override
	public List<task> getallresponandapp(String user_name,int idadd,String year) {
		String sql = "SELECT * FROM task "
				+ "where (YEAR(task_datestart) = ? or  YEAR(task_dateend) = ?) and  (find_in_set(?,task_iduser) or  task_idadd =?) ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{year,year,user_name,idadd},new taskmapper());
		return tasks;
	}
	

	@Override
	public List<task> getallstatus(int idrefer,int status,String name,int idadd) {
		String sql = "SELECT * FROM task where task_idrefer = ? and  task_status = ?  and  (find_in_set(?,task_iduser) or  task_idadd =?)  order by task_id desc ";
		List<task> tasks  =  jdbcTemplate.query(sql,new Object[]{idrefer,status,name,idadd},new taskmapper());
		return tasks;
	}

	
	@Override
	public int delete(int id) {
		 String sql="delete from task where  task_id = "+id;    
		    return jdbcTemplate.update(sql);
	}

	
	@Override
	public int save(task tasks) {
		Object[] taskInfo = { 
				tasks.getTask_name(),
				tasks.getTask_note(),
				tasks.getTask_idrefer(),
				tasks.getTask_idadd(),
				tasks.getTask_status(),
				tasks.getTask_date(),
				tasks.getTask_datestart(),
				tasks.getTask_dateend(),
				tasks.getTask_budget(),
				tasks.getTask_payment(),
				tasks.getKeyactivity_id(),
				};
				String sql = "INSERT INTO task(task_name,task_note,task_idrefer,task_idadd,task_status,task_date,task_datestart,task_dateend,task_budget,task_payment,keyactivity_id) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,taskInfo);
	}
	
	
	

	@Override
	public void saveoriginal_task(List<task> tasks) {	
		
		
		String sql = "INSERT INTO original_task(task_name,task_note,task_idrefer,task_idadd,task_status,task_date,task_datestart,task_dateend"
				+ ",task_budget,task_payment,task_iduser,task_expenses,task_real_payment,keyactivity_id,budget_source1,task_budget1,outcome,use_budget,plan_budget,activity_task,end_task) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {
			                ps.setString(1,tasks.get(i).getTask_name());
			                ps.setString(2,tasks.get(i).getTask_note());
			                ps.setInt(3,tasks.get(i).getTask_idrefer());
			                ps.setInt(4,tasks.get(i).getTask_idadd());
			                ps.setInt(5,tasks.get(i).getTask_status());
			                ps.setString(6,tasks.get(i).getTask_date());
			                ps.setString(7,tasks.get(i).getTask_datestart());
			                ps.setString(8,tasks.get(i).getTask_dateend());
			                ps.setString(9,tasks.get(i).getTask_budget());
			                ps.setString(10,tasks.get(i).getTask_payment());
			                ps.setString(11,tasks.get(i).getTask_iduser());
			                ps.setString(12,tasks.get(i).getTask_expenses());
			                ps.setString(13,tasks.get(i).getTask_real_payment());
			                ps.setInt(14,tasks.get(i).getKeyactivity_id());
			                ps.setInt(15,tasks.get(i).getBudget_source1());
			                ps.setDouble(16,tasks.get(i).getTask_budget1());
			                ps.setString(17,tasks.get(i).getOutcome());
			                ps.setDouble(18,tasks.get(i).getUse_budget());
			                ps.setDouble(19,tasks.get(i).getPlan_budget());
			                ps.setString(20,tasks.get(i).getActivity_task());
			                ps.setString(21,tasks.get(i).getEnd_task());
	            }
	            @Override
	            public int getBatchSize() {
	                return tasks.size();

	            }
	        });
	    
	
	}
	
	
	
	
	
	@Override
	public int update(task tasks) {
		Object[] taskInfo = { 
				tasks.getTask_name(),
				tasks.getTask_note(),
				tasks.getTask_datestart(),
				tasks.getTask_dateend(),
				tasks.getTask_budget(),
				tasks.getTask_payment(),
				tasks.getBudget_source1(),
				tasks.getBudget_source2(),
				tasks.getBudget_source3(),
				tasks.getTask_budget1(),
				tasks.getTask_budget2(),
				tasks.getTask_budget3(),
				tasks.getOutcome(),
				tasks.getKeyactivity_id(),
				tasks.getTask_id(),
				};
				String sql = "update task SET  task_name=?,task_note=?,task_datestart=?,task_dateend=?,task_budget =?,task_payment=?"
						+ ",budget_source1=?,budget_source2=?,budget_source3=?,task_budget1=?,task_budget2=?,task_budget3=?,outcome=?,keyactivity_id=? "
						+ "where task_id =? ";
				return jdbcTemplate.update(sql,taskInfo);
	}
	
	
	@Override
	public int increaseProgress(task tasks) {
		Object[] taskInfo = { 
				tasks.getActivity_task(),
				tasks.getEnd_task(),
				tasks.getTask_id(),
				};
				String sql = "update task SET  activity_task=?,end_task=? where task_id =? ";
				return jdbcTemplate.update(sql,taskInfo);
	}

	@Override
	public int deleteProblems(Problem Problems) {
		 String sql="delete from problem where  id = ? ";    
		    return jdbcTemplate.update(sql,new Object[]{Problems.getId()});
	}


	@Override
	public void saveProblems(Problem problem) {
			
		if(problem.getId() > 0) {
			
			Object[] problemInfo = { 
					problem.getTitle(),
					problem.getDescription(),
					problem.getMitigation(),
					problem.getStatus(),
					problem.getId()
					};
			
			String sql = "UPDATE  problem SET title =?,description = ? ,mitigation =?,status =? WHERE id = ? ";
			 jdbcTemplate.update(sql,problemInfo);
		}else {
			
			Object[] problemInfo = { 
					problem.getProject_id(),
					problem.getTask_id(),
					problem.getActionplan_id(),
					problem.getTitle(),
					problem.getDescription(),
					problem.getMitigation(),
					problem.getOwner(),
					problem.getOwner_id(),
					problem.getStatus(),
					problem.getType(),
					};
			
			String sql = "INSERT INTO  problem (project_id,task_id,actionplan_id,title,description,mitigation,owner,owner_id,status,type)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?) ";
			 jdbcTemplate.update(sql,problemInfo);
			
		}
		
			
		
	}
	
	
	
	
	@Override
	public int getCountProblemByProjectId(int project_id) {
		String sql = "SELECT COUNT(*) from problem  where project_id =?   ";
		return  jdbcTemplate.queryForObject(sql,new Object[]{project_id},Integer.class);
	}
	
	@Override
	public int getCountProblemByProjectId(int project_id,int status) {
		String sql = "SELECT COUNT(*) from problem  where project_id =?  AND status = ?  ";
		return  jdbcTemplate.queryForObject(sql,new Object[]{project_id,status},Integer.class);
	}
	
	
	@Override
	public List<Problem> getallProblemByTaskId(int actionplan_id) {
		String sql = "SELECT * from problem  where task_id =?   ";
		List<Problem>  list  =  jdbcTemplate.query(sql,new Object[]{actionplan_id},new problemmapper());
		return list;
	}
	
	
	@Override
	public List<Progress> getallProgressByTaskId(int actionplan_id) {
		String sql = "SELECT * from progress  where task_id =?  ORDER BY positon ASC  ";
		List<Progress>  list  =  jdbcTemplate.query(sql,new Object[]{actionplan_id},new Progressmapper());
		return list;
	}
	
	
	@Override
	public List<Problem> getallProblemByActionplanId(int actionplan_id) {
		String sql = "SELECT * from problem  where actionplan_id =?   ";
		List<Problem>  list  =  jdbcTemplate.query(sql,new Object[]{actionplan_id},new problemmapper());
		return list;
	}
	
	@Override
	public Problem getProblemById(int id) {
		String sql = "SELECT * from problem  where id =?   ";
		Problem  data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new problemmapper());
		return data;
	}
	
	
	
	
	@Override
	public int setteam(task tasks) {
		Object[] taskInfo = { 
				tasks.getTask_iduser(),
				tasks.getTask_idcheck(),
				tasks.getTask_id(),
				};
				String sql = "update task SET  task_iduser=?,task_idcheck=? where task_id =? ";
				return jdbcTemplate.update(sql,taskInfo);
	}
	
	@Override
	public int delProgress(int id,int task_id) {
		 String sql="delete from progress  where positon=? AND task_id = ?";    
		    return jdbcTemplate.update(sql,new Object[]{id,task_id});
	}
	
	@Override
	public void saveProgress(Progress Progress) {
		
		Object[] ProgressInfo = { 
				Progress.getDate_log(),
				Progress.getTitle(),
				Progress.getNote(),
				Progress.getType(),
				Progress.getTask_id(),
				Progress.getPositon()
				};
		
		
		String sqls = "SELECT COUNT(*) FROM progress WHERE task_id =? AND  positon =?  ";
		int count_progress =  jdbcTemplate.queryForObject(sqls,new Object[]{Progress.getTask_id(),Progress.getPositon()},Integer.class);
		
		if(count_progress  == 0) {
			
	
			
			String sql = "INSERT progress (date_log,title,note,type,task_id,positon)"
					+ " VALUES(?,?,?,?,?,?) ";
			jdbcTemplate.update(sql,ProgressInfo);
		}else {
			return;
			
			//String sql = "UPDATE progress SET date_log = ?,title =?,note =? ,type =? WHERE task_id = ?  AND positon= ? ";
			//jdbcTemplate.update(sql,ProgressInfo);
			
		}
	}
	
	  
	
	@Override
	public task getbyid(int id) {
		String sql = "SELECT * from task  where task_id =?   ";
		task tasks  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new taskmapper());
		return tasks;
	}
	
	
	@Override
	public void setTotalCost(int id,BigDecimal Usetotal) {
				String sql = "update task SET  use_budget=? where task_id =? ";
				jdbcTemplate.update(sql,new Object[]{Usetotal,id});
	}


	
	@Override
	public task getStart(int id) {

		
		  try {
				String sql = "SELECT * from task  where task_idrefer = ? order by task_datestart ASC  limit 1  ";
				task tasks  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new taskmapper());
				return tasks;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
		
	}
	
	@Override
	public task getEnd(int id) {

		
		  try {
				String sql = "SELECT * from task  where task_idrefer = ? order by task_dateend DESC  limit 1  ";
				task tasks  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new taskmapper());
				return tasks;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
		
	}
	
	
	
	@Override
	public int setstatus(int id,int status) {
		String sql = "UPDATE  task  SET task_status=?  where task_id =?   ";
		return jdbcTemplate.update(sql,new Object[]{status,id});	
	}
	
	
	@Override
	public int setcm(int num,int task_id) {
		String sql = "UPDATE  task  SET task_cm=?  where task_id =?   ";
		return jdbcTemplate.update(sql,new Object[]{num,task_id});	
	}
	@Override
	public int setalert(int num,int alert,int task_id) {
		String sql = "UPDATE  task  SET task_cm=?,task_alert=?  where task_id =?   ";
		return jdbcTemplate.update(sql,new Object[]{num,alert,task_id});	
	}






	

}
