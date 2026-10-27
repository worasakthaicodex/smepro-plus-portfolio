package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.smepro.models.UserCode;
import com.smepro.models.document_no;
import com.smepro.models.user;
import com.smepro.rowmapper.UserCodemapper;
import com.smepro.rowmapper.filemapper;
import com.smepro.rowmapper.membermapper;
import com.smepro.rowmapper.usermapper;
import com.smepro.rowmapper.usermapper2;
import com.smepro.rowmapper.usermappers;

public class userDAOlmpl implements userDAO {

	private JdbcTemplate jdbcTemplate;
	final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	public userDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	

	
	
	
	@Override
	public List<user> getall(int id,int idrefer) {
		String sql = "SELECT * FROM users"
				+ " LEFT JOIN group_members ON group_members.username = users.username"
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id   "
				+ "where  users.type=? and users.main=?   order by users.user_id desc ";
		List<user> users  =  jdbcTemplate.query(sql , new Object[]{id,idrefer}, new usermapper2());
		return users;
	}

	@Override
	public List<user> findAll() {
		String sql = "SELECT * FROM users"
				+ " LEFT JOIN group_members ON group_members.username = users.username"
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id   "
				+ "where  users.main= 0   order by users.user_id desc  limit  50";
		List<user> users  =  jdbcTemplate.query(sql , new usermapper2());
		return users;
	}
	
	
	
	@Override
	public List<user> findInAll(String idString) {
		        if (idString == null || idString.trim().isEmpty()) {
		            return Collections.emptyList();
		        }

		        // 1️⃣ Split string → array, remove empty, trim, parse to Integer
		        List<Integer> idList = Arrays.stream(idString.split(","))
		                .map(String::trim)
		                .filter(s -> !s.isEmpty())
		                .map(Integer::parseInt)
		                .collect(Collectors.toList());

		        if (idList.isEmpty()) {
		            return Collections.emptyList();
		        }

		        // 2️⃣ สร้าง placeholders เท่ากับจำนวน id
		        String placeholders = idList.stream()
		                .map(i -> "?")
		                .collect(Collectors.joining(","));

		        // 3️⃣ สร้าง SQL dynamic
		        String sql = "SELECT * FROM users WHERE user_id IN (" + placeholders + ") LIMIT 500";

		        // 4️⃣ Query
		        return jdbcTemplate.query(sql, idList.toArray(), new usermappers());
		    }
	
	
	
	
	@Override
	public List<user> getSearch(String name, String position, String department) {
	    StringBuilder sql = new StringBuilder("SELECT * FROM users "
	            + "LEFT JOIN group_members ON group_members.username = users.username "
	            + "LEFT JOIN groups ON groups.id = group_members.group_id "
	            + "WHERE users.main = 0");

	    List<Object> params = new ArrayList<>();

	    if (name != null && !name.isBlank() && !name.equals("-")) {
	        sql.append(" AND users.user_name LIKE ?");
	        params.add("%" + name.trim() + "%");
	    }

	    if (position != null && !position.equals("-")) {
	        sql.append(" AND groups.group_name = ?");
	        params.add(position);
	    }

	    if (department != null && !department.equals("-")) {
	        sql.append(" AND groups.group_department = ?");
	        params.add(department);
	    }

	    sql.append(" ORDER BY users.user_id DESC LIMIT 50");

	    return jdbcTemplate.query(sql.toString(), params.toArray(), new usermapper2());
	}
	
	
	
	@Override
	public List<user> getallStringid(String id) {
		String sql = "SELECT * FROM users"
				+ " LEFT JOIN group_members ON group_members.username = users.username"
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id   "
				+ "where find_in_set(users.user_id,?)  order by users.user_id desc ";
		List<user> users  =  jdbcTemplate.query(sql , new Object[]{id}, new usermapper2());
		return users;
	}


	
	
	@Override
	public List<user> getall() {
		String sql = "SELECT * FROM users LEFT JOIN group_members ON group_members.username = users.username"
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id   "
				+ " where users.main = 0  order by users.user_id desc ";
		List<user> users  =  jdbcTemplate.query(sql , new Object[]{}, new usermapper2());
		return users;
	}
	
	@Override
	public List<user> getallsort() {
		String sql = "SELECT * FROM users LEFT JOIN group_members ON group_members.username = users.username"
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id   "
				+ " where users.user_id != 2  order by groups.group_name desc  ";
		List<user> users  =  jdbcTemplate.query(sql , new Object[]{}, new usermapper2());
		return users;
	}
	
	
	@Override
	public List<user> getRandom() {
		String sql = "SELECT * FROM users LEFT JOIN group_members ON group_members.username = users.username"
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id   "
				+ " where users.main = 0  order by RAND() limit 7 ";
		List<user> users  =  jdbcTemplate.query(sql , new Object[]{}, new usermapper2());
		return users;
	}
	
	
	
	@Override
	public List<user> getallgroup(String authorities) {
		String sql = "SELECT * FROM users LEFT JOIN group_members"
				+ " ON group_members.username = users.username LEFT JOIN groups"
				+ " ON groups.id  = group_members.group_id   LEFT JOIN group_authorities"
				+ " ON groups.id  = group_authorities.group_id   where group_authorities.authority=?  and users.main != 1 ";
		List<user> users  =  jdbcTemplate.query(sql , new Object[]{authorities}, new usermapper2());
		return users;
	}
	
	
	
	@Override
	public int save(user users ,int idrefer) {
		Object[] userInfo = { 
				users.getUsername(),
				passwordEncoder.encode(users.getPassword())
				};
						String sql = "INSERT INTO users(username,password,enabled,user_name,type,main) VALUES(?,?,?,?,?,?);";
						//String sql = "INSERT INTO member(member_name,member_date) VALUES(?,?);";
						  
				        //jdbcTemplate.update(sql,memberInfo);
				          KeyHolder keyHolder = new GeneratedKeyHolder();
				
				          jdbcTemplate.update(connection -> {
				         	 
				              PreparedStatement ps = connection
				                .prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				                ps.setString(1, users.getUsername());
				                ps.setString(2, passwordEncoder.encode(users.getPassword()));
				                ps.setInt(3, 1);
				                ps.setString(4, "");
				                ps.setInt(5, idrefer);
				                ps.setInt(6, 0);
				                return ps;
				             
				          }, keyHolder);			
				          //String key = keyHolder.getKey();
				         // System.out.println( keyHolder.getKey().intValue());
				         return  keyHolder.getKey().intValue();
				     // System.out.println( keyHolder.getKey());
		
	}

	
	
	@Override
	public int resetpassword(int id, String password) {
		
		String passwords = 	passwordEncoder.encode(password);
		  String sql=  "UPDATE users SET password ='"+passwords+"'  WHERE  user_id ="+id;
			    return jdbcTemplate.update(sql); 
	}
	
	
	
	@Override
	public user getbyId(int id , int idrefer) {
	
	    
		  try {
				String sql="select * from users where user_id=?  and type =?  and main = 0 ";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{id,idrefer},new usermapper());
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	    
	    
	}
	
	
	@Override
	public user getbyId2(int id) {

			  try {
					String sql="SELECT * FROM users LEFT JOIN group_members"
							+ " ON group_members.username = users.username "
							+ "LEFT JOIN groups ON groups.id  = group_members.group_id   where users.user_id=?  and  user_id > 2";    
				    return jdbcTemplate.queryForObject(sql, new Object[]{id},new usermapper2());
				
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }
		
			
	
	}
	
	
	@Override
	public int updatefull(user users ,int id,int idrefer) {
		

		String date1 =users.getUser_datepassport() == null  || users.getUser_datepassport().equals("") ? null : "'" + users.getUser_datepassport() + "'";
		String date2 =users.getUser_birthday() == null  || users.getUser_birthday().equals("") ? null : "'" + users.getUser_birthday() + "'";
				
	

		
		  String sql=  "UPDATE users SET user_name ='"+users.getUser_name()+"' "
			  		+ ",user_address ='"+users.getUser_address()+"'"
					+ ",user_tel ='"+users.getUser_tel() +"'"
			  		+ ",user_idcard ='"+users.getUser_idcard() +"'"
			  		+ ",user_districtpassport ='"+users.getUser_districtpassport() +"'"
			  		+ ",user_countrypassport ='"+users.getUser_countrypassport() +"'"
			  				+ ",user_email ='"+users.getUser_email() +"'"
			  				+ ",user_line ='"+users.getUser_line() +"'"
			  		+ ",user_datepassport = "+ date1 
			  		+ ",user_birthday =" + date2
			  		+"  WHERE  user_id ='"+users.getUser_id()+"' and  type  ='"+id+"' and main  ='"+idrefer+"' ";
			    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public user getbyusername(String username) {
	    String sql="select * FROM users LEFT JOIN group_members ON group_members.username = users.username LEFT JOIN groups ON groups.id  = group_members.group_id  where users.username=?";    
	    return jdbcTemplate.queryForObject(sql, new Object[]{username},new usermapper2());
	}
	
	
	
	@Override
	public user getbyname(String name) {

	
		
		  try {
			    String sql="select * FROM users LEFT JOIN group_members ON group_members.username = users.username LEFT JOIN groups ON groups.id  = group_members.group_id  where users.user_name=?";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{name},new usermapper2());
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the getbyname Id .......");
		   
		 }
	
	}
	
	
	
	@Override
	public user getbyusername2(String username) {
		
		
		  try {
			    String sql="select * from users where username=?";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{username},new usermappers());
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
	

	}
	
	
	@Override
	public int update(user users ,String username) {
		
		String date1 =users.getUser_datepassport() == null  || users.getUser_datepassport().equals("") ? null : "'" + users.getUser_datepassport() + "'";
		String date2 =users.getUser_birthday() == null  || users.getUser_birthday().equals("") ? null : "'" + users.getUser_birthday() + "'";
				
		
		  String sql=  "UPDATE users SET user_name ='"+users.getUser_name()+"' "
		  		+ ",user_address ='"+users.getUser_address()+"'"
				+ ",user_tel ='"+users.getUser_tel() +"'"
		  		+ ",user_idcard ='"+users.getUser_idcard() +"'"
		  		+ ",user_districtpassport ='"+users.getUser_districtpassport() +"'"
		  		+ ",user_countrypassport ='"+users.getUser_countrypassport() +"'"
		  		+ ",user_email ='"+users.getUser_email() +"'"
		  		+ ",user_line ='"+users.getUser_line() +"'"
		  		+ ",user_datepassport = "+ date1 
		  		+ ",user_birthday =" + date2
		  		+ "   WHERE username  ='"+username+"' ";
		    return jdbcTemplate.update(sql);  
	}

	
	@Override
	public int updateusername(user users ,String username) {
		  String sql=  "UPDATE users SET username ='"+users.getUsername()+"' "
		  		+" WHERE username  ='"+username+"' ";
		    return jdbcTemplate.update(sql);  
	}
	
	
	@Override
	public int delete(int id,int idrefer) {
		    String sql="delete from users where type='"+idrefer+"' and  user_id ='"+id+"' and main = 0 ";    
		    return jdbcTemplate.update(sql); 
	}
	
	@Override
	public boolean isuserExists(String username) {
	    String sql = "SELECT count(*) FROM users WHERE username = ?";

	    int count = jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);

	    return count > 0;
	}

	
	public int getsum() {
		String sql = "select count(*) from  users where user_id != 2 ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {  }, Integer.class);
	}
	
	
	@Override
	public void setnewpassword(String newpassword,int id) {
		
		String password = passwordEncoder.encode(newpassword);
		  String sql=  "UPDATE users SET password ='"+password+"' "
		  		+" WHERE user_id  ="+id;
		    jdbcTemplate.update(sql);  
	}
	
	
	public int countlevel(String level) {
		String sql = "select count(*) from  users "
				+ " LEFT JOIN group_members ON group_members.username = users.username "
				+ " LEFT JOIN groups ON groups.id  = group_members.group_id "
				+ " WHERE groups.group_level =?    ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {level  }, Integer.class);
	}
	
	@Override
	public user getbyAuth(String Auth) {
		
		
		  try {

			    String sql="select * FROM users "
			    		+ "LEFT JOIN group_members ON group_members.username = users.username"
			    		+ " LEFT JOIN groups ON groups.id  = group_members.group_id  where users.auth=?";    
			    return jdbcTemplate.queryForObject(sql, new Object[]{Auth},new usermapper2());
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
		
	}
	
	
	@Override
	public void setbyAuth(String Auth,String username) {
		
		  String sql=  "UPDATE users SET auth = ?  WHERE user_name =? ";
		    jdbcTemplate.update(sql, new Object[] {Auth,username });  
	}
	
	@Override
	public void resetprofileimage(String username) {
		  String sql=  "UPDATE users SET user_img = null  WHERE username =? ";
		    jdbcTemplate.update(sql, new Object[] {username });  
	}
	
	@Override
	public void setprofileimage(String username,String img) {
		  String sql=  "UPDATE users SET user_img = ?  WHERE username =? ";
		  
	//	  System.out.println(img);
		//  System.out.println(username);
		//  System.out.println(sql);
		  
		    jdbcTemplate.update(sql, new Object[] {img,username });  
	}
	
	@Override
	public UserCode findUserCodeById(int id) {
		
		  String sql=  "SELECT * FROM  user_code WHERE user_code_id =?  ";
		  UserCode row =   jdbcTemplate.queryForObject(sql, new Object[]{id},new UserCodemapper());  
		  return row;
	}

	
	@Override
	public UserCode findUserCodeDepartment(int id) {
		
	
		  
		  try {

			  String sql=  "SELECT * FROM  user_code WHERE user_code_department =?   limit 1  ";
			  UserCode row =   jdbcTemplate.queryForObject(sql, new Object[]{id},new UserCodemapper());  
			  return row;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			  String sql=  "SELECT * FROM  user_code WHERE user_code_department = 0  limit 1 ";
			  UserCode row =   jdbcTemplate.queryForObject(sql,new UserCodemapper());  
			  return row;
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		  
	}
	
	
	@Override
	public List<UserCode> findAllUserCode() {
		
		  String sql=  "SELECT * FROM  user_code  ";
		  List<UserCode> row =   jdbcTemplate.query(sql,new UserCodemapper());  
		  return row;
	}
	
	@Override
	public void deleleUserCode( int id) {
		  String sql=  "DELETE FROM  user_code  WHERE user_code_id =? ";
			     jdbcTemplate.update(sql, new Object[]{id}); 
	}
	

	@Override
	public void editUserCode( UserCode UserCodes) {
		
		Object[] UserCodeInfo = { 
				UserCodes.getUser_code_pattern(),
				UserCodes.getUser_code_note(),
				UserCodes.getUser_code_department(),
				UserCodes.getUser_code_daterun(),
				UserCodes.getDefaultpassword(),
				UserCodes.getUser_code_id(),
				};
		
		  String sql=  "UPDATE user_code SET user_code_pattern = ?,user_code_note=?,user_code_department=?,user_code_daterun=?,defaultpassword=?  WHERE  user_code_id =? ";
			     jdbcTemplate.update(sql,UserCodeInfo); 
	}
	
	@Override
	public void saveUserCode(UserCode UserCodes) {
		Object[] UserCodeInfo = { 
				UserCodes.getUser_code_pattern(),
				UserCodes.getUser_code_note(),
				UserCodes.getUser_code_department(),
				UserCodes.getUser_code_daterun(),
				UserCodes.getDefaultpassword(),
				};
				String sql = "INSERT INTO user_code(user_code_pattern,user_code_note,user_code_department,user_code_daterun,defaultpassword) "
						+ "VALUES(?,?,?,?,?)";
				 jdbcTemplate.update(sql,UserCodeInfo);

	}
	
	
}
