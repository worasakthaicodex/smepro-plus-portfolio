package com.smepro.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.smepro.config.SpringContext;

@ServerEndpoint(value = "/Socket_alert/{SocketId}")
public class webmeterSocket {
	   private static final AtomicInteger OnlineCount = new AtomicInteger(0);
	   private static Map<String, Session> userMap = new ConcurrentHashMap<>();
	    private static Map<String, Long> sessionExpiryMap = new ConcurrentHashMap<>();
	    private static final long SESSION_TIMEOUT = 3600000; // 1 hour in milliseconds
	    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    private String SocketId;
	
	   @PostConstruct
	    public  void init() {
	        System.out.println("websocket run....");
	        // เริ่มการตรวจสอบเซสชันที่หมดอายุ
	        scheduler.scheduleAtFixedRate(this::removeExpiredSessions, 1, 1, TimeUnit.MINUTES);
	    }
	   

	    @OnOpen
	    public  void onOpen(Session session, @PathParam("SocketId") String SocketId) throws IOException {
	        this.SocketId = SocketId;
	        userMap.put(SocketId, session);
	        sessionExpiryMap.put(SocketId, System.currentTimeMillis() + SESSION_TIMEOUT);
	    }

	    @OnMessage
	    public  void onMessage(String message, Session session) throws IOException, InterruptedException {
	    	int cnt = userMap.size();
	        session.getBasicRemote().sendText("จำนวนคนออนไลท์คือ  : " + cnt);
	        session.getBasicRemote().sendText("ID " + SocketId + " ยินดีต้อนรับ！ ");
	        sendMessageFromOneToAll(SocketId, message);
	        
	        // Update expiry time เมื่อได้รับข้อความใหม่
	        sessionExpiryMap.put(SocketId, System.currentTimeMillis() + SESSION_TIMEOUT);

	    }

	    @OnClose
	    public void onClose() {
	        userMap.remove(SocketId);
	        sessionExpiryMap.remove(SocketId);
	    }

	    @OnError
	    public void onError(Throwable error) {
	        System.out.println("Web Socket Error" + error.getMessage());
	    }

	    // ลบเซสชันที่หมดอายุ
	    private void removeExpiredSessions() {
	        long currentTime = System.currentTimeMillis();
	        sessionExpiryMap.forEach((socketId, expiryTime) -> {
	            if (currentTime > expiryTime) {
	                Session session = userMap.remove(socketId);
	                sessionExpiryMap.remove(socketId);
	                if (session != null) {
	                    try {
	                        session.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	                System.out.println("Session for SocketId " + socketId + " expired and closed.");
	            }
	        });
	    }
	    
	    
	  
	    

	    public static  void sendMessageToOne(String SocketId, String message) {
	    	
	    	  System.out.println("sendMessageToOne  for SocketId "+ SocketId);
	    	
	    	
	    	  try {
	    		  	userMap.get(SocketId).getBasicRemote().sendText(message);
	    	  } catch (IOException e) {
	                //e.printStackTrace();
	            }catch (Exception e) {
	                //e.printStackTrace();
	            }
	    }

	    


	    
	    
	    public static void sendMessageToAll(String message) {
	        userMap.forEach((id, session) -> {
	            try {
	                session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                //e.printStackTrace();
	            }catch (Exception e) {
	                //e.printStackTrace();
	            }
	        });
	    }


	    public  static void sendMessageFromOneToAll(String SocketId, String message) {
	        userMap.forEach((id, session) -> {
	            try {
	                //session.getBasicRemote().sendText("คุณ " + userId + "นี้เป็นข้อความถึงคุณ ：" + message);
	                session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                //e.printStackTrace();
	            }catch (Exception e) {
	                //e.printStackTrace();
	            }
	        });
	    }

	   
	   
}
