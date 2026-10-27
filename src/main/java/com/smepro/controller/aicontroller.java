package com.smepro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

@Controller
@SessionAttributes({"Member_name","name","Member_id"})
public class aicontroller {

	
	@PostMapping(value = "/ask-ai", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> askAi(@RequestBody Map<String, String> payload) {
        String prompt = payload.get("prompt");
        String model = "gemini-3.1-flash-lite-preview"; // ใช้พลัง 500 ครั้งของเจ้านาย!
        String apiKey = System.getenv().getOrDefault("GOOGLE_AI_API_KEY", "");
        if (apiKey.isBlank()) {
            return ResponseEntity.status(503).body("{\"error\":\"AI service is not configured\"}");
        }
        
        
        // สร้าง URL และส่ง Request ด้วย RestTemplate หรือ WebClient
        String url = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        
        // สร้าง Request Body (โครงสร้างเดียวกับที่เราคุยกันใน JS)
        Map<String, Object> requestBody = Map.of(
            "contents", List.of(Map.of("parts", List.of(Map.of("text", prompt))))
        );

        // ยิงไปหา Google และส่งคำตอบกลับไปที่ Frontend
        String response = restTemplate.postForObject(url, requestBody, String.class);
        return ResponseEntity.ok(response);
    }
	
	
	@PostMapping("/api/chat")
    public ResponseEntity<String> forwardToAi(@RequestBody Map<String, Object> payload) {
        RestTemplate restTemplate = new RestTemplate();
        // 8083 ยิงไปหา 8081 หลังบ้าน
        String aiUrl = "http://localhost:8081/api/chat";
        
        // ส่งต่อแบบตรงๆ
        ResponseEntity<String> response = restTemplate.postForEntity(aiUrl, payload, String.class);
        return response; // โยนกลับให้หน้าเว็บ
    }
	
	
	@GetMapping("/api/ai-auto-compare/{promoId}")
	public ResponseEntity<String> forwardToAiAutoCompare(@PathVariable Long promoId) {
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // ชี้เป้าไปที่ Endpoint ของ AI Server (พอร์ต 8081)
	    String aiUrl = "http://localhost:8081/ai-auto-compare/" + promoId;
	    
	    try {
	        System.out.println("🌉 [Bridge] กำลังส่งคำสั่ง GET รหัสโปรโมชั่น " + promoId + " ไปให้ AI Server...");
	        
	        // ใช้ getForEntity ส่งไปแบบตัวเบาๆ
	        ResponseEntity<String> response = restTemplate.getForEntity(aiUrl, String.class);
	        
	        System.out.println("✅ [Bridge] ได้รับข้อมูล JSON จาก AI กลับมาแล้ว ส่งต่อให้หน้าเว็บ...");
	        return response; 
	        
	    } catch (Exception e) {
	        System.out.println("❌ [Bridge Error] ไม่สามารถเชื่อมต่อเซิร์ฟเวอร์ AI (8081) ได้: " + e.getMessage());
	        return ResponseEntity.status(500).body("{\"error\": \"ไม่สามารถเชื่อมต่อกับระบบ AI ได้ในขณะนี้\"}");
	    }
	}

	
	
	
	@GetMapping("/api/chat/history/{sessionId}")
    public ResponseEntity<String> forwardHistoryToAi(@PathVariable String sessionId) {
        RestTemplate restTemplate = new RestTemplate();
        
        // 1. ประกอบร่าง URL โดยเอา sessionId ที่หน้าเว็บส่งมา ไปต่อท้าย URL ของ 8081
        String aiUrl = "http://localhost:8081/api/chat/history/" + sessionId;
        
        try {
            // 2. ใช้ getForEntity เพราะเป็นการดึงข้อมูล (GET Request)
            ResponseEntity<String> response = restTemplate.getForEntity(aiUrl, String.class);
            return response; // โยนก้อน JSON ประวัติแชทกลับไปให้หน้าเว็บ JS โหลด
            
        } catch (Exception e) {
            // ดัก Error ไว้หน่อย เผื่อ 8081 ดับ หรือหา Session ไม่เจอ
            System.out.println("❌ [PROXY ERROR] ไม่สามารถดึงประวัติแชทจาก 8081 ได้: " + e.getMessage());
            return ResponseEntity.status(500).body("[]"); // ส่ง Array ว่างกลับไป หน้าเว็บจะได้ไม่พัง
        }
    }
	
}

