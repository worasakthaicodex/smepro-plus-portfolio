package com.smepro.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


import com.smepro.Licenses.LicenseCheckListener;
import com.smepro.websocket.webmeterSocket;







public class mainconfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
	
		 AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		 context.register(Appconfig.class);
		 context.setServletContext(servletContext);
		 servletContext.addListener(new SessionListener());
		 //ServletRegistration.Dynamic servlet =servletContext.addServlet("dispacher",new DispatcherServlet(context));
		// servlet.setLoadOnStartup(1);
		// servlet.addMapping("/");
		 context.close();
	/*	
		 try {
			 LicenseCheckListener License  = new LicenseCheckListener();  
			License.installLicense();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.print("LicenseCheckListener "+e);
		}
		
	*/	
		 
		 System.out.println(">>> Starting Manual WebSocket Registration...");
	        
	        // 1. ดึง ServerContainer จาก ServletContext (Tomcat เก็บไว้ที่นี่)
	        ServerContainer serverContainer = (ServerContainer) servletContext.getAttribute("javax.websocket.server.ServerContainer");

	        if (serverContainer != null) {
	            try {
	                // 2. สั่งลงทะเบียน Class WebSocket ของคุณ
	                serverContainer.addEndpoint(webmeterSocket.class);
	                
	                System.out.println(">>> WebSocket Registered SUCCESS: " + webmeterSocket.class.getName());
	            } catch (DeploymentException e) {
	                System.err.println("!!! WebSocket Registration FAILED !!!");
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("!!! Warning: javax.websocket.server.ServerContainer is NULL !!!");
	            System.out.println("!!! กรุณาตรวจสอบ dependency 'tomcat-websocket' ใน pom.xml !!!");
	        }
	        // -----------------------------------------------------------
		
	 
	}

	
	public class SessionListener implements HttpSessionListener {

		public static final String COUNTER = "SESSIONCOUNTER";
		public List<String> sessions = new ArrayList<>();
		
	    @Override
	    public void sessionCreated(HttpSessionEvent event) {
	    	
	        HttpSession session = event.getSession();
	        sessions.add(session.getId());
	        
	        sessions = sessions.stream()
                    .distinct()
                    .collect(Collectors.toList());
	      
	        session.setAttribute(SessionListener.COUNTER, sessions.size());
	        event.getSession().setMaxInactiveInterval(28800);
           
	    }

	    @Override
	    public void sessionDestroyed(HttpSessionEvent event) {
	    	 HttpSession session = event.getSession();
	         sessions.remove(session.getId());
	         session.setAttribute(SessionListener.COUNTER, sessions.size());
	        
	    }
	    
	    public int getActiveSessionNumber() {
	        return sessions.size();
	    }
	}
	
	
}
