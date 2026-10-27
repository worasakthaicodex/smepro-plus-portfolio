package com.smepro.config;

import java.util.HashSet;
import java.util.Set;
import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

// import class websocket ของคุณ
import com.smepro.websocket.webmeterSocket;

public class WebSocketConfig implements ServerApplicationConfig {

	@Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
        // เราไม่ได้ใช้ Programmatic endpoint ให้ return null หรือ set ว่าง
        return null; 
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        // สร้าง Set เพื่อเก็บ Class WebSocket ของเรา
        Set<Class<?>> results = new HashSet<>();
        
        // --- สำคัญ: ใส่ Class WebSocket ของคุณลงไปตรงนี้ ---
        results.add(webmeterSocket.class); 
        
        System.out.println("Tomcat 9 WebSocket Registered: " + webmeterSocket.class.getName());
        
        return results;
    }
}