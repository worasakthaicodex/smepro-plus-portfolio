package com.smepro.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smepro.dao.actionplanDAO;
import com.smepro.dao.cmDAO;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.darDAO;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.edataDAO;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.formDAO;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.reportDAO;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.taskDAO;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.userDAO;
import com.smepro.dao.workflowDAO;
import com.smepro.models.Idoc;
import com.smepro.models.department;
import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.user;



@Service
public class userservice {

	private static BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();
	private PasswordEncoder passwordEncoder;
	
	private group_membersDAO Group_membersDAO;
	private groupsDAO GroupsDAO;
	private departmentDAO DepartmentDAO;
	private edataDAO EdataDAO;
	private userDAO UserDAO;

    @Autowired
    public void setMyRepository(
    		group_membersDAO Group_membersDAO
    		,groupsDAO GroupsDAO
    		,departmentDAO DepartmentDAO
    		,edataDAO EdataDAO
    		,userDAO UserDAO
    		,PasswordEncoder passwordEncoder
    		) {
    	
        this.Group_membersDAO = Group_membersDAO;
        this.GroupsDAO = GroupsDAO;
        this.DepartmentDAO = DepartmentDAO;
        this.EdataDAO = EdataDAO;
        this.UserDAO = UserDAO;
        this.passwordEncoder = passwordEncoder;
        
    }

	
	
	
	
	
		public policies getPolicies(int app) {
			
			  	user usersed = UserDAO.getbyusername2(getLoggedName()); 
		    	group_members group_memberss = Group_membersDAO.getbyId(getLoggedName());
		    	groups groupss = GroupsDAO.getbyIds(group_memberss.getGroup_id());
		    	department Department = DepartmentDAO.getbyName(groupss.getGroup_department());
		    	
		    	if(Department == null) {
		    		
		    		policies Policies  = new policies();
		    		Policies.setId(0);
		    		return Policies;
		    	}
		    	
		    	
		    	
		    	String user = "user_"+usersed.getUser_id();
		    	String groups = "group_"+groupss.getId();
		    	String department = "department_"+Department.getDepartment_id();
		    	policies Policies  =   EdataDAO.getMyPolicies(app,user,groups,department);
				return Policies;
				
				
		}
		
		
		
		public void setPoliciesAccessApp(process Process) {
		    user usersed = UserDAO.getbyusername2(getLoggedName()); 
		    group_members group_memberss = Group_membersDAO.getbyId(getLoggedName());
		    groups groupss = GroupsDAO.getbyIds(group_memberss.getGroup_id());
		    department Department = DepartmentDAO.getbyName(groupss.getGroup_department());

		    
		    
		    String userIdStr = "user_" + usersed.getUser_id();
		    String groupIdStr = "group_" + groupss.getId();
		    String deptIdStr ="__";
		    if(Department != null) {
			    deptIdStr = "department_" + Department.getDepartment_id();
	    	}
	    	
	

		    // เช็คสิทธิ์ Admin
	
		    
		    
		    if (Process.getAccess_admin() != null) {
			    
			    if (Process.getAccess_admin().equals("ALL")) {
			        setPermissionAccessApp("ADMIN",Process.getId()) ;
			    }else if (hasAccess(Process.getAccess_admin(), userIdStr, groupIdStr, deptIdStr)) {
		            setPermissionAccessApp("ADMIN",Process.getId()) ;
		        }
		    }

		    // เช็คสิทธิ์ User
		    if (Process.getAccess_user() != null) {
			    
			    if (Process.getAccess_admin().equals("ALL")) {
			        setPermissionAccessApp("USER",Process.getId()) ;
			    }
			    else if (hasAccess(Process.getAccess_user(), userIdStr, groupIdStr, deptIdStr)) {
		            setPermissionAccessApp("USER",Process.getId()) ;
		        }
		    }


		}

		// Helper Method สำหรับเช็คว่าข้อมูลตรงกันไหม
		private boolean hasAccess(String accessList, String user, String group, String dept) {
		    // แยก String ออกมาด้วย comma
		    String[] items = accessList.split(",");
		    for (String item : items) {
		        String trimmedItem = item.trim();
		        // ถ้าตรงกับอันไหนอันหนึ่ง ถือว่ามีสิทธิ์ทันที
		        if (trimmedItem.equals(user) || trimmedItem.equals(group) || trimmedItem.equals(dept)) {
		            return true;
		        }
		    }
		    return false;
		}
		
		
		
		
		
		
		public policies getPoliciesStep(int step) {
		  	user usersed = UserDAO.getbyusername2(getLoggedName()); 
	    	group_members group_memberss = Group_membersDAO.getbyId(getLoggedName());
	    	groups groupss = GroupsDAO.getbyIds(group_memberss.getGroup_id());
	    	department Department = DepartmentDAO.getbyName(groupss.getGroup_department());
	    	
	    	
	    	if(Department == null) {return null;}
	    	
	    	String user = "user_"+usersed.getUser_id();
	    	String groups = "group_"+groupss.getId();
	    	String department = "department_"+Department.getDepartment_id();
	    	policies Policies  =   EdataDAO.getPoliciesStep(step,user,groups,department);
			return Policies;
		}
		
	
		
		public String getPoliciesString() {
			

			  	user usersed = UserDAO.getbyusername2(getLoggedName()); 
			  	
		    	group_members group_memberss = Group_membersDAO.getbyId(getLoggedName());
		    	if(group_memberss == null) {return "";}
		    	
		    	
		    	groups groupss = GroupsDAO.getbyIds(group_memberss.getGroup_id());
		    	if(groupss == null) {return "";}
		    	
		    	department Department = DepartmentDAO.getbyName(groupss.getGroup_department());
		    	if(Department == null) {return "";}
		    	
		    	
		    	String user = "user_"+usersed.getUser_id();
		    	String user_id  = String.valueOf(usersed.getUser_id()) ;
		    	String groups = "group_"+groupss.getId();
		    	String department = "department_"+Department.getDepartment_id();
				return  String.join(",",user_id,user,groups,department);
			
			
			
		}
		
		
		
		
		
		
	
	public String getLoggedName() {
		
		
		
		
		
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
	
	public boolean islogin() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return true;
		}
		return false;
	}

	
	public List<String> getLoggedUserPermissionNames() {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    
	    if (principal instanceof UserDetails) {
	        return ((UserDetails) principal).getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());
	    }
	    return List.of();
	}
	
	
	
	   public Boolean doPasswordsMatch(String rawPassword,String encodedPassword) {
		      return passwordEcorder.matches(rawPassword, encodedPassword);
		   }


	   public void setPermissionAccessApp(String type,int id){
		    Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

		    if (currentAuth != null) {
		        Collection<? extends GrantedAuthority> existingAuthorities = currentAuth.getAuthorities();
		        Set<GrantedAuthority> combinedAuthorities = new HashSet<>(existingAuthorities);

		        // ลบสิทธิ์ EDIT-DOC-* ออกก่อนเสมอ
		        combinedAuthorities.removeIf(auth -> auth.getAuthority().startsWith("APP-"+type+"-"));

		        // เพิ่มสิทธิ์ใหม่
		        combinedAuthorities.add(new SimpleGrantedAuthority("APP-"+type+"-" + id));

		        Authentication newAuth = new UsernamePasswordAuthenticationToken(
		            currentAuth.getPrincipal(),
		            currentAuth.getCredentials(),
		            combinedAuthorities
		        );
		        SecurityContextHolder.getContext().setAuthentication(newAuth);
		    }
		}
	   
	   


	   public void setPermission(Idoc docs) {
		    user usersed = UserDAO.getbyusername2(getLoggedName());
		    Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

		    if (currentAuth != null) {
		        Collection<? extends GrantedAuthority> existingAuthorities = currentAuth.getAuthorities();
		        Set<GrantedAuthority> combinedAuthorities = new HashSet<>(existingAuthorities);

		        // ลบสิทธิ์ EDIT-DOC-* ออกก่อนเสมอ
		        combinedAuthorities.removeIf(auth -> auth.getAuthority().startsWith("EDIT-DOC-"));

		        // ถ้าไม่ใช่เจ้าของเอกสาร ให้เคลียร์แล้วจบ
		        if (!Objects.equals(docs.getReceiver(), usersed.getUser_id())) {
		            Authentication newAuth = new UsernamePasswordAuthenticationToken(
		                currentAuth.getPrincipal(),
		                currentAuth.getCredentials(),
		                combinedAuthorities
		            );
		            SecurityContextHolder.getContext().setAuthentication(newAuth);
		            return;
		        }

		        // เพิ่มสิทธิ์ใหม่
		        combinedAuthorities.add(new SimpleGrantedAuthority("EDIT-DOC-" + docs.getId()));

		        Authentication newAuth = new UsernamePasswordAuthenticationToken(
		            currentAuth.getPrincipal(),
		            currentAuth.getCredentials(),
		            combinedAuthorities
		        );
		        SecurityContextHolder.getContext().setAuthentication(newAuth);
		    }
		}
	   
	   
	   
	   public UserDetails getCurrentUser() {
		    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    if (principal instanceof UserDetails) {
		        return (UserDetails) principal;
		    }
		    return null;
		}
	   
	   public boolean verifyPassword(String inputPassword) {
		    UserDetails user = getCurrentUser();
		    if (user == null) return false;
		    System.out.println("user not null");

		    String encodedPassword = user.getPassword(); // bcrypt hash
		    return passwordEncoder.matches(inputPassword, encodedPassword);
		}
	
}
