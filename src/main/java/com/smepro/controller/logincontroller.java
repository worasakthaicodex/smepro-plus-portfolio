package com.smepro.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smepro.dao.logDAO;
import com.smepro.dao.userDAO;
import com.smepro.dto.memberDTO;
import com.smepro.Licenses.LicenseCheckListener;
import com.smepro.Licenses.LicenseManagerHolder;
import com.smepro.Licenses.LicenseVerify;
import com.smepro.models.log;
import com.smepro.models.user;
import com.smepro.service.loginservice;
import com.smepro.service.userservice;

import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;






@Controller
@SessionAttributes({"name","userrole","Member_id"})
public class logincontroller {
	
	
	@Autowired
	loginservice Loginservice;
	
	@Autowired
	userservice Userservice;
	
	@Autowired
	logDAO LogDAO;
	
	@Autowired
	userDAO UserDAO;
	
	
	memberDTO memberDTOs = new  memberDTO();
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/error" , method = RequestMethod.GET)
	@ResponseBody
	public String error() {
		 return "Please log in again.";
	}
	
	
	@RequestMapping(value="/login" , method = RequestMethod.GET)
	//@ResponseBody
	public String loginpage(ModelMap model,HttpServletRequest request) throws IOException {
		

	 Locale locale = LocaleContextHolder.getLocale(); // ดึงภาษาปัจจุบันจาก context
	    String title = messageSource.getMessage("tables.due", null, locale);
		System.out.println(" title "+title);
	
		        String verif = "false";
		        String cstatus = "Error";
		        
		        try {
			     LicenseVerify licenseVerify = new LicenseVerify();
			     boolean verifyResult = licenseVerify.verify();
		            if(verifyResult){
					    	 verif = "true";
					    	 cstatus ="Ok";
					        }else {
					        	
					       	 LicenseCheckListener License  = new LicenseCheckListener();  
							 License.installLicense();
							 
						     boolean verifyResults = licenseVerify.verify();
						     if(verifyResults){
						    	 verif = "true";
						    	 cstatus ="Ok";
						        }
							 
					        }
		            
		            
		            
		         
					} catch (Exception e) {
				    	 LicenseCheckListener License  = new LicenseCheckListener();  
						 License.installLicense();
					     LicenseVerify licenseVerify = new LicenseVerify();
					     boolean verifyResult = licenseVerify.verify();
				            if(verifyResult){
							    	 verif = "true";
							    	 cstatus ="Ok";
							        }
					}
		        
		        
		        verif = "true";
		    	 cstatus ="Ok";
	
		        model.addAttribute("cstatus",cstatus);
				model.addAttribute("verif",verif);
		        return "login";
	}
	

	
	//@RequestMapping(value="/home")
	//public String homepage() {
		//return "index";
	//}
	
	
	

	@RequestMapping(value="/tls" , method = RequestMethod.GET)
	//@ResponseBody
	public void tls() {
	        try {
	            // กำหนดข้อมูลสำหรับ SSL Server
	            int serverPort = 9999;
	            SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
	            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(serverPort);

	            System.out.println("TLS Server started. Waiting for clients...");

	            // รอการเชื่อมต่อจาก client
	            SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
	            System.out.println("Client connected.");

	            // สร้าง InputStream และ OutputStream เพื่อรับส่งข้อมูล
	            InputStream inputStream = sslSocket.getInputStream();
	            OutputStream outputStream = sslSocket.getOutputStream();

	            // ในที่นี้ให้ใส่โค้ดการรับ-ส่งข้อมูลต่อไป

	            // ปิดการเชื่อมต่อ
	            sslSocket.close();
	            sslServerSocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	
	
	
	
	
	
}
