package com.smepro.service;

import java.io.File;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.smepro.models.setmail;
import com.smepro.models.user;



@Service
public class GmailTextMsgSendWithoutSSL {

	Properties properties;
	Session session;
	MimeMessage mimeMessage;
	HttpSession sessions;

	public static String sendGmailendWithoutSSL(setmail setmails,user users,int type) throws MessagingException {
		
		
		
		String EmailSubject = null;
		String EmailBody = null;
		String ToAddress = users.getUser_email();
		String Url = setmails.getUrl();
		String note = setmails.getNote();
		String note2 = setmails.getNote2();
		String support = setmails.getSupport();
		String token = "token";
		
		if(type == 1) {
			EmailSubject =  "Subject:Verify Email";
			EmailBody = "<table style=\"font-family: Montserrat,Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n"
				+ "                    <tbody><tr>\n"
				+ "                      <td class=\"sm-px-24\" style=\"--bg-opacity: 1; background-color: #ffffff; background-color: rgba(255, 255, 255, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 14px; line-height: 24px; padding: 48px; text-align: left; --text-opacity: 1; color: #626262; color: rgba(98, 98, 98, var(--text-opacity));\" bgcolor=\"rgba(255, 255, 255, var(--bg-opacity))\" align=\"left\">\n"
				+ "                        <p style=\"font-weight: 600; font-size: 18px; margin-bottom: 0;\">Hey</p>\n"
				+ "                        <p style=\"font-weight: 700; font-size: 20px; margin-top: 0; --text-opacity: 1; color: #ff5850; color: rgba(255, 88, 80, var(--text-opacity));\">"+ users.getUser_name() +"!</p>\n"
				+ "                        <p class=\"sm-leading-32\" style=\"font-weight: 600; font-size: 20px; margin: 0 0 16px; --text-opacity: 1; color: #263238; color: rgba(38, 50, 56, var(--text-opacity));\">\n"
				+ "                          Thanks for signing up! 👋\n"
				+ "                        </p>\n"
				+ "                        <p style=\"margin: 0 0 24px;\">\n"
				+ "                          Please verify your email address by clicking the below button and join our creative community,\n"
				+ "                          start exploring the resources or showcasing your work.\n"
				+ "                        </p>\n"
				+ "                        <p style=\"margin: 0 0 24px;\">\n"
				+ "                          If you did not sign up to PixInvent, please ignore this email or contact us at\n"
				+ "                          <a href=\"mailto:"+ support +"\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">"+ support +"</a>\n"
				+ "                        </p>\n"
				+ "          \n"
				+ "                        <table style=\"font-family: Montserrat,Arial,sans-serif;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n"
				+ "                          <tbody><tr>\n"
				+ "                            <td style=\"mso-padding-alt: 16px 24px; --bg-opacity: 1;  background-color: rgba(115, 103, 240, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif;\" bgcolor=\"rgba(115, 103, 240, var(--bg-opacity))\">\n"
				+ "                              <a href=\""+ Url +"/verification_url?id="+ users.getUser_id() +"&token="+ token +"\" style=\"display: block; font-weight: 600; font-size: 14px; line-height: 100%; padding: 16px 24px; --text-opacity: 1; color: #ffffff; color: rgba(255, 255, 255, var(--text-opacity)); text-decoration: none;\">Verify Email Now →</a>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody></table>\n"
				+ "                        <table style=\"font-family: Montserrat,Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n"
				+ "                          <tbody><tr>\n"
				+ "                            <td style=\"font-family: Montserrat,Arial,sans-serif; padding-top: 32px; padding-bottom: 32px;\">\n"
				+ "                              <div style=\"--bg-opacity: 1; height: 1px; line-height: 1px;\">&zwnj;</div>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody></table>\n"
				+ "                        <p style=\"margin: 0 0 16px;\">\n"
				+ "                          Not sure why you received this email? Please\n"
				+ "                          <a href=\"mailto:"+ support +"\" class=\"hover-underline\" style=\"--text-opacity: 1; color: #7367f0; color: rgba(115, 103, 240, var(--text-opacity)); text-decoration: none;\">let us know</a>.\n"
				+ "                        </p>\n"
				+ "                        <p style=\"margin: 0 0 16px;\">Thanks, </p>\n<a href=\""+ Url +"\">"+ Url +"</a>"
				+ "                      </td>\n"
				+ "                    </tr>\n"
				+ "                    <tr>\n"
				+ "                      <td style=\"font-family: Montserrat,Arial,sans-serif; height: 20px;\" height=\"20\"></td>\n"
				+ "                    </tr>\n"
				+ "                    <tr>\n"
				+ "                      <td style=\"font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 12px; padding-left: 48px; padding-right: 48px; --text-opacity: 1; color: #eceff1; color: rgba(236, 239, 241, var(--text-opacity));\">\n"
				+ "                       \n"
				+ "                       \n"
				+ "                      </td>\n"
				+ "                    </tr>\n"
				+ "                    <tr>\n"
				+ "                      <td style=\"font-family: Montserrat,Arial,sans-serif; height: 16px;\" height=\"16\"></td>\n"
				+ "                    </tr>\n"
				+ "                  </tbody></table>";
		}else if(type == 0) {
			EmailSubject =  "Subject:Test Email";
			EmailBody = "Test email by web : "+ Url;
		}else if(type == 2) {
			EmailSubject =  "Subject:การเเจ้งเตือนการอัพโหลดเอกสารบันทึก";
			EmailBody = "  <table style=\"font-family: Montserrat,Arial,sans-serif; width: 100%;\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n"
					+ "                    <tbody><tr>\n"
					+ "                      <td class=\"sm-px-24\" style=\"--bg-opacity: 1; background-color: #ffffff; background-color: rgba(255, 255, 255, var(--bg-opacity)); border-radius: 4px; font-family: Montserrat, -apple-system, 'Segoe UI', sans-serif; font-size: 14px; line-height: 24px; padding: 48px; text-align: left; --text-opacity: 1; color: #626262; color: rgba(98, 98, 98, var(--text-opacity));\" bgcolor=\"rgba(255, 255, 255, var(--bg-opacity))\" align=\"left\">\n"
					+ "                        <p style=\"font-weight: 600; font-size: 18px; margin-bottom: 0;\">เรียนคุณ</p>\n"
					+ "                        <p style=\"font-weight: 700; font-size: 20px; margin-top: 0; --text-opacity: 1; color: #ff5850; color: rgba(255, 88, 80, var(--text-opacity));\">"+users.getUser_name()+"!</p>\n"
					+ "                        <p style=\"margin: 0 0 24px;\">\n"
					+ "                        "+note+"\n"+note2+"\n"
					+ "                        </p>\n"
					+ "                     \n"
					+ "                      \n"
					+ "                            </td>\n"
					+ "                          </tr>\n"
					+ "                        </tbody></table>\n"
					+ "                        <p style=\"font-size: 14px; line-height: 24px; margin-top: 6px; margin-bottom: 20px;\">\n"
					+ "                         ตรวจสอบรายละเอียดได้ที่\n"
					+ "                          <a href=\""+Url+"\"/support>support team</a> for help.\n"
					+ "                        </p>\n"
					+ "                        <p style=\"font-size: 14px; line-height: 24px; margin-top: 6px; margin-bottom: 20px;\">\n"
					+ "                          ส่งอัตโนมัติโดยระบบ,\n"
					+ "                          <br>\n"
					+ "                        </p>\n"
					+ "                      </td>\n"
					+ "                    </tr>\n"
					+ "                  </tbody></table>";
			
		}
		
	
		GmailTextMsgSendWithoutSSL gmailTextMsgSend = new GmailTextMsgSendWithoutSSL();
		return gmailTextMsgSend.sendGmail(EmailSubject, EmailBody, ToAddress,setmails);
	}

	public String sendGmail(String EmailSubject, String EmailBody, String ToAddress,setmail setmails) {
		
		String USERNAME = setmails.getUsername();
		String PASSWORD = setmails.getPassword();
		String HOSTNAME = setmails.getHostname();
		String STARTTLS_PORT = setmails.getStarttls_port();
		boolean STARTTLS = setmails.isStarttls();
		boolean AUTH = setmails.isAuth();

		
	
			properties = new Properties();
			
			//properties = System.getProperties();
			properties.put("mail.smtp.host", HOSTNAME);
			properties.put("mail.smtp.port", STARTTLS_PORT);
			properties.put("mail.smtp.starttls.enable", STARTTLS);
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
				
				if(AUTH) {
						// Authenticating
						// AUTH enabled
						properties.put("mail.smtp.auth", AUTH);
						Authenticator auth = new Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(USERNAME, PASSWORD);
							}
						};
						// creating session
						 session = Session.getDefaultInstance(properties, auth);
						 //session.setDebug(true);
						 
				}else {
						// creating session
				    	//session= Session.getDefaultInstance(properties);
						session= Session.getInstance(properties);
				}
			
			
				try {
					
					
			      // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(USERNAME));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(ToAddress));

	            // Set Subject: header field
	            message.setSubject(EmailSubject);

	            // Now set the actual message
	            //message.setText("This is actual message");
	        	Multipart multipart = new MimeMultipart();				
				MimeBodyPart textBodyPart = new MimeBodyPart();
			    textBodyPart.setContent(EmailBody, "text/html; charset=utf-8");
			    multipart.addBodyPart(textBodyPart);  // add the text part
			    
			    
			    if(setmails.getAttachment() != null) {
					 DataSource source = null;
					 File f = new File("/filesuploads/"+ setmails.getAttachment());
					 String UPLOAD_DIRECTORY ="src/main/webapp/filesuploads/"+ setmails.getAttachment();
		             String path = new File(UPLOAD_DIRECTORY).getCanonicalPath();  
		             MimeBodyPart attachmentBodyPart= new MimeBodyPart();  
					 source = new FileDataSource(path);
					 attachmentBodyPart.setDataHandler(new DataHandler(source));
					 attachmentBodyPart.setFileName(setmails.getAttachment()); // ex : "test.pdf"
					 multipart.addBodyPart(attachmentBodyPart); // add the attachement part
				}
			    
			    
			    message.setContent(multipart);
	            
	            System.out.println("sending...");
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	     
        /*
			// create mimemessage
			mimeMessage = new MimeMessage(session);
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(ToAddress));
			mimeMessage.setSubject(EmailSubject);
			Multipart multipart = new MimeMultipart();
			
			// setting text message body
			//mimeMessage.setText(EmailBody);  text 
			
			
			
			MimeBodyPart textBodyPart = new MimeBodyPart();
		    textBodyPart.setContent(EmailBody, "text/html; charset=utf-8");
		    multipart.addBodyPart(textBodyPart);  // add the text part
	
		    
		    
		    if(setmails.getAttachment() != null) {
				 DataSource source = null;
				 File f = new File("/filesuploads/"+ setmails.getAttachment());
				 
				 String UPLOAD_DIRECTORY ="src/main/webapp/filesuploads/"+ setmails.getAttachment();
	             String path = new File(UPLOAD_DIRECTORY).getCanonicalPath();  
	             MimeBodyPart attachmentBodyPart= new MimeBodyPart();  
				 source = new FileDataSource(path);
				 attachmentBodyPart.setDataHandler(new DataHandler(source));
				 attachmentBodyPart.setFileName(setmails.getAttachment()); // ex : "test.pdf"
				
				  multipart.addBodyPart(attachmentBodyPart); // add the attachement part
			}
		
			 
		      

		    mimeMessage.setContent(multipart);
			
		
			
			Transport.send(mimeMessage);
			
			System.out.println("Mail Send Successfully");
			*/
			return "Successfully";
           
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error";
	}

}
