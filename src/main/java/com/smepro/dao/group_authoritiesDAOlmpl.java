package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smepro.models.group_authorities;
import com.smepro.models.groups;
import com.smepro.rowmapper.group_authoritiesmapper;
import com.smepro.rowmapper.groupsmapper;

public class group_authoritiesDAOlmpl implements group_authoritiesDAO {
	

	private JdbcTemplate jdbcTemplate;
	
	public group_authoritiesDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<group_authorities> getall(int id, int idrefer) {
		String sql = "SELECT *  FROM group_authorities where group_id =?  and type =? and main ='0'";
		List<group_authorities> group_authoritiess  =  jdbcTemplate.query(sql , new Object[]{id,idrefer},new group_authoritiesmapper());
		return group_authoritiess;
	}

	
	@Override
	public List<group_authorities> getallfoldertype(String type) {
		
		String add ="ADD-"+type;
		String edit = "EDIT-"+type;
		String check  = "CHECK-"+type;
		String app = "APP-"+type;
		String read = "READ-"+type;
		String upload = "UPLOAD-"+type;
		String set = "SET-"+type;
		String delete = "DELETE-"+type;
		String download = "DOWNLOAD-"+type;
		String deletettem = "DELETEITEM-"+type;
		String types = add+","+edit+","+check+","+app+","+read+","+upload+","+set+","+delete+","+download+","+deletettem;
		
		String sql = "SELECT id,concat(authority,'-',group_id) as authority,group_id,type,main "
				+ "FROM group_authorities where find_in_set(authority,?) and main ='0'   ";
		List<group_authorities> group_authoritiess  =  jdbcTemplate.query(sql , new Object[]{types},new group_authoritiesmapper());
		return group_authoritiess;
	}

	
	
	@Override
	public List<group_authorities> getalltype(String type) {
		
		String add ="ADD-"+type;
		String edit = "EDIT-"+type;
		String check  = "CHECK-"+type;
		String app = "APP-"+type;
		String read = "READ-"+type;
		String upload = "UPLOAD-"+type;
		String set = "SET-"+type;
		String delete = "DELETE-"+type;
		String download = "DOWNLOAD-"+type;
		String deletettem = "DELETEITEM-"+type;
		String types = add+","+edit+","+check+","+app+","+read+","+upload+","+set+","+delete+","+download+","+deletettem;
		
		String sql = "SELECT group_authorities.id,groups.group_name as authority,group_id,group_authorities.type,group_authorities.main"
				+ " FROM group_authorities "
				+ " LEFT JOIN groups ON  groups.id = group_authorities.group_id "
				+ " where find_in_set(group_authorities.authority,?) and group_authorities.main ='0' group by groups.group_name limit 20  ";
		List<group_authorities> group_authoritiess  =  jdbcTemplate.query(sql , new Object[]{types},new group_authoritiesmapper());
		return group_authoritiess;
	}

	
	
	
	@Override
	public void save(String authority, int group_id	) {
		Object[] authorityInfo = { authority,group_id};
		String sql = "INSERT INTO group_authorities(authority,group_id,type,main) VALUES(?,?,0,0);";
		jdbcTemplate.update(sql,authorityInfo);

	}

	@Override
	public group_authorities getbyId(int id, int idrefer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(group_authorities group_authorities, int idrefer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id, int idrefer) {
		   String sql="delete from group_authorities where  type=? AND  group_id =? AND authority NOT LIKE '%-Folder%' " ;    
		    return jdbcTemplate.update(sql,new Object[]{idrefer,id});    
	}
	
	@Override
	public void set(List<String> authoritys,group_authorities group_authorities, int idrefer) {	
		
		
	    String sql = " INSERT INTO "
	            + "group_authorities "
	            + "(authority,group_id,type,main) "
	            + "VALUES " + "(?,?,?,?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {

	                ps.setString(1, authoritys.get(i));
	                ps.setInt(2, group_authorities.getGroup_id());
	                ps.setInt(3, idrefer);
	                ps.setInt(4, 0);
	            }

	            @Override
	            public int getBatchSize() {
	                return authoritys.size();
	            }
	        });

	
	}
	
	
	@Override
	public int deleteauthoritiesbyfolder(String Group_id,String type) {
		
		String add ="ADD-"+type;
		String edit = "EDIT-"+type;
		String check  = "CHECK-"+type;
		String app = "APP-"+type;
		String read = "READ-"+type;
		String upload = "UPLOAD-"+type;
		String set = "SET-"+type;
		String delete = "DELETE-"+type;
		String download = "DOWNLOAD-"+type;
		String deletettem = "DELETEITEM-"+type;
		String types = add+","+edit+","+check+","+app+","+read+","+upload+","+set+","+delete+","+download+","+deletettem;
		
		   String sql="delete from group_authorities where  find_in_set(authority,'"+types+"')  and  find_in_set(group_id,'"+Group_id+"')   ";    
		    return jdbcTemplate.update(sql);    
	}
	
	@Override
	public int deleteforfoldertype(String type) {
		
		String add ="ADD-"+type;
		String edit = "EDIT-"+type;
		String check  = "CHECK-"+type;
		String app = "APP-"+type;
		String read = "READ-"+type;
		String upload = "UPLOAD-"+type;
		String set = "SET-"+type;
		String delete = "DELETE-"+type;
		String download = "DOWNLOAD-"+type;
		String deletettem = "DELETEITEM-"+type;
		String types = add+","+edit+","+check+","+app+","+read+","+upload+","+set+","+delete+","+download+","+deletettem;
		
		   String sql="delete from group_authorities where  find_in_set(authority,'"+types+"')   ";    
		    return jdbcTemplate.update(sql);    
	}
	
	
	
	@Override
	public void setauthoritiesbyfolder(List<String> data) {	
		
	
	    String sql = " INSERT INTO "
	            + "group_authorities "
	            + "(authority,group_id,type,main) "
	            + "VALUES " + "(?,?,?,?)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {

	                ps.setString(1, data.get(i).split("_")[0]);
	                ps.setInt(2, Integer.valueOf(data.get(i).split("_")[1]));
	                ps.setInt(3, 0);
	                ps.setInt(4, 0);
	            }

	            @Override
	            public int getBatchSize() {
	                return data.size();
	            }
	        });

	
	}
	

}
