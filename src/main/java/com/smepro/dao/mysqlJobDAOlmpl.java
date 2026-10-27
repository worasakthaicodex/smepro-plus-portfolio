package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smepro.models.actionplan;
import com.smepro.models.backup;
import com.smepro.rowmapper.actionplanmapper;
import com.smepro.rowmapper.actionplanmapper2;
import com.smepro.rowmapper.attendancemapper;
import com.smepro.rowmapper.backupmapper;


public class mysqlJobDAOlmpl implements mysqlJobDAO {

	private JdbcTemplate jdbcTemplate;
	
	public mysqlJobDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}

	@Override
	public void createAutoPurgeEventEdataSoftDelete() {
		try {
            // 1. เปิดใช้งาน Event Scheduler ของ MySQL ก่อน (กันเหนียว)
            jdbcTemplate.execute("SET GLOBAL event_scheduler = ON");

            // 2. สร้าง SQL (ใช้ IF NOT EXISTS เพื่อกัน Error ถ้ารันซ้ำ)
            // หมายเหตุ: ผมเปลี่ยน STARTS เป็น CURRENT_TIMESTAMP + 1 DAY เพื่อให้เริ่มพรุ่งนี้เสมอ ไม่ว่าจะรันโค้ดวันไหน
            String sql = """
                CREATE EVENT IF NOT EXISTS AutoCleanup_Edata
                ON SCHEDULE EVERY 1 DAY
                STARTS CURRENT_TIMESTAMP + INTERVAL 1 DAY
                DO
                  DELETE FROM edata 
                  WHERE soft_delete IS NOT NULL 
                  AND soft_delete < DATE_SUB(NOW(), INTERVAL 1 YEAR)
            """;

            // 3. สั่งรันคำสั่ง
            jdbcTemplate.execute(sql);
            
            System.out.println("✅ สร้าง MySQL Event 'AutoCleanup_Edata' เรียบร้อยแล้ว");

        } catch (Exception e) {
            System.err.println("❌ เกิดข้อผิดพลาดในการสร้าง Event: " + e.getMessage());
            e.printStackTrace();
        }
		
	}
	
	@Override
    public void removeAutoPurgeEvent(String eventName) {
        try {
            // "AutoCleanup_Edata";
            // คำสั่ง SQL ลบ Event (IF EXISTS กัน Error ถ้าไม่มีอยู่แล้ว)
            String sql = "DROP EVENT IF EXISTS " + eventName;

            jdbcTemplate.execute(sql);
            
            System.out.println("✅ ลบ MySQL Event '" + eventName + "' เรียบร้อยแล้ว (หยุดทำงานอัตโนมัติ)");

        } catch (Exception e) {
            System.err.println("❌ ลบไม่สำเร็จ: " + e.getMessage());
            e.printStackTrace();
        }

    }
	
	
	@Override
    public String getEventListAsString() {
        try {
            // 1. ดึงข้อมูลจาก MySQL System Table
            String sql = """
                SELECT EVENT_NAME, STATUS, INTERVAL_VALUE, INTERVAL_FIELD, LAST_EXECUTED, STARTS
                FROM information_schema.EVENTS 
                WHERE EVENT_SCHEMA = DATABASE()
            """;

            List<Map<String, Object>> events = jdbcTemplate.queryForList(sql);

            // 2. ถ้าไม่มีงานเลย ให้ตอบกลับสั้นๆ
            if (events.isEmpty()) {
                return "✅ ระบบปกติ: ไม่มีการตั้งเวลางานอัตโนมัติไว้ (No Scheduled Events)";
            }

            // 3. ใช้ StringBuilder ต่อข้อความ (ประหยัดแรมกว่า String + String)
            StringBuilder sb = new StringBuilder();
            sb.append("=== 📅 รายการงานอัตโนมัติ (MySQL Events) ===\n");
            
            for (Map<String, Object> event : events) {
                String name = (String) event.get("EVENT_NAME");
                String status = (String) event.get("STATUS"); // ENABLED / DISABLED
                String frequency = event.get("INTERVAL_VALUE") + " " + event.get("INTERVAL_FIELD");
                Object lastRun = event.get("LAST_EXECUTED");

                // จัดรูปแบบข้อความให้อ่านง่าย
                sb.append("--------------------------------------\n");
                sb.append("🔹 ชื่องาน:   ").append(name).append("\n");
                sb.append("   สถานะ:    ").append(status.equals("ENABLED") ? "🟢 เปิดใช้งาน" : "🔴 ปิดอยู่").append("\n");
                sb.append("   ความถี่:   ทำทุกๆ ").append(frequency).append("\n");
                sb.append("   เริ่มเมื่อ:  ").append(event.get("STARTS")).append("\n");
                sb.append("   ทำล่าสุด:  ").append(lastRun == null ? "(รอรอบแรก)" : lastRun).append("\n");
            }
            sb.append("--------------------------------------");

            // 4. ส่งค่ากลับไป
            return sb.toString();

        } catch (Exception e) {
            return "❌ เกิดข้อผิดพลาด: " + e.getMessage();
        }
    }
	
}
