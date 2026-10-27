package com.smepro.ai;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smepro.dao.AiDao;

@Service
public class AiBrainBuilderService {

    @Autowired
    private AiDao aiDao; 

    private static final String API_KEY = System.getenv().getOrDefault("GOOGLE_AI_API_KEY", "");
 // เปลี่ยนจาก v1beta เป็น v1 และใช้ text-embedding-004
 // บางครั้งในบางพื้นที่ต้องใช้ชื่อนี้ครับ
 // ใช้ v1beta และโมเดล gemini-embedding-001 ตามที่เจ้านายเช็คมาครับ
    private final String EMBED_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-embedding-001:embedContent?key=" + API_KEY;

    public void buildBrain(int batchSize) {
        if (API_KEY.isBlank()) {
            throw new IllegalStateException("GOOGLE_AI_API_KEY is not configured");
        }
        System.out.println("🤖 เริ่มงาน: กำลังดึงข้อมูลจาก DB...");
        
        // 1. ดึงข้อมูลจาก DAO
        List<Map<String, Object>> rows = aiDao.getPendingSalesData(batchSize);

        if (rows == null || rows.isEmpty()) {
            System.out.println("🤖 ไม่พบข้อมูลที่ค้างทำ Vector (หรือทำครบหมดแล้ว) ครับเจ้านาย!");
            return;
        }

        System.out.println("🤖 พบข้อมูลทั้งหมด: " + rows.size() + " รายการที่ต้องจัดการ");
        RestTemplate restTemplate = new RestTemplate();

        for (Map<String, Object> row : rows) {
            // ใช้ deal_id ตามที่เจ้านายบอกครับ
            Object dealId = row.get("deal_id"); 
            
            try {
                System.out.println("🚀 กำลังประมวลผลดีล ID: " + dealId);
                
                // 2. ปรับแต่งข้อความ (Method อยู่ด้านล่างครับ)
                String textToEmbed = formatData(row);
                System.out.println("📝 ข้อความที่จะส่งไป Google: " + textToEmbed);
                
                // 3. ขอ Vector จาก Google
                double[] vector = fetchVector(restTemplate, textToEmbed);
                
                if (vector != null) {
                    // 4. สั่ง DAO บันทึกลง Database
                    String vectorJson = new JSONArray(vector).toString();
                    aiDao.updateAiVector(dealId, vectorJson);
                    
                    System.out.println("✅ บันทึกสมอง ID: " + dealId + " ลง DB เรียบร้อย!");
                    Thread.sleep(500); // พักกัน Google ด่า
                } else {
                    System.err.println("⚠️ ID: " + dealId + " ยิง API ผ่านแต่ไม่ได้ค่า Vector กลับมา");
                }
            } catch (Exception e) {
                System.err.println("❌ พลาดที่ ID: " + dealId + " สาเหตุ: " + e.getMessage());
                e.printStackTrace(); // พ่น Error ออกมาดูบรรทัดที่พังเลยครับ
            }
        }
    }

    /**
     * 🛠️ เมธอดสกัดข้อมูล (ที่เจ้านายถามหา)
     * ต้องระวังชื่อคอลัมน์ใน row.get("...") ต้องตรงกับใน Database เป๊ะๆ นะครับ
     */
    private String formatData(Map<String, Object> row) {
        try {
            return String.format(
                "หมวดหมู่: %s, หลักสูตร: %s, ราคา: %s, กลุ่ม: %s, เกรด: %s, พนักงาน: %s คน, เป้าหมาย: %s, ประวัติคุย: %s, สถานะ: %s, เหตุผลที่แพ้: %s",
                getString(row, "category"),
                getString(row, "course_name"),
                getString(row, "total_price"),
                getString(row, "customer_group"),
                getString(row, "customer_grade"),
                getString(row, "number_of_employees"),
                getString(row, "main_objective"),
                getString(row, "chat_history"),
                getString(row, "status"),
                getString(row, "loss_reason")
            );
        } catch (Exception e) {
            System.err.println("🚨 Error ใน formatData: " + e.getMessage());
            return "ข้อมูลไม่สมบูรณ์"; 
        }
    }

    // ฟังก์ชันช่วยดึงค่าจาก Map ป้องกันค่า Null แล้วพัง
    private String getString(Map<String, Object> row, String columnName) {
        Object val = row.get(columnName);
        return (val == null) ? "ไม่ระบุ" : val.toString();
    }

    private double[] fetchVector(RestTemplate restTemplate, String text) {
        try {
            JSONObject request = new JSONObject();
            JSONObject content = new JSONObject();
            content.put("parts", new JSONArray().put(new JSONObject().put("text", text)));
            request.put("content", content);

            String response = restTemplate.postForObject(EMBED_URL, request.toString(), String.class);
            JSONObject json = new JSONObject(response);
            
            // แกะเอาค่าตัวเลขออกมา
            JSONArray values = json.getJSONObject("embedding").getJSONArray("values");
            double[] vector = new double[values.length()];
            for (int i = 0; i < values.length(); i++) {
                vector[i] = values.getDouble(i);
            }
            return vector;
        } catch (Exception e) {
            System.err.println("🚨 Google API Error: " + e.getMessage());
            return null;
        }
    }
}
