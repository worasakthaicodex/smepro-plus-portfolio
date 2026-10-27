package com.smepro.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smepro.dao.edataDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dto.GamificationWidgetDTO;
import com.smepro.entity.GamificationGoal;
import com.smepro.entity.QuestLog;
import com.smepro.entity.RegisterGradeHistory;
import com.smepro.models.holiday;
import com.smepro.models.process;
import com.smepro.models.registers;
import com.smepro.repository.GamificationGoalRepository;
import com.smepro.repository.QuestLogRepository;
import com.smepro.repository.RegisterGradeHistoryRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class AutoGradeService {

    private final edataDAO EdataDAO;
    private final holidayDAO HolidayDAO;
    private final RegisterGradeHistoryRepository registerGradeHistoryRepository;
    private final QuestLogRepository questLogRepository;

    @Autowired
    public AutoGradeService(
            edataDAO EdataDAO,
            holidayDAO HolidayDAO,
            RegisterGradeHistoryRepository registerGradeHistoryRepository,
            QuestLogRepository questLogRepository) {
        this.EdataDAO = EdataDAO;
        this.HolidayDAO = HolidayDAO;
        this.registerGradeHistoryRepository = registerGradeHistoryRepository;
        this.questLogRepository = questLogRepository;
    }


    @Transactional
    public void processDaily(LocalDate lastCheckedDate) {
        System.out.println(">>> เริ่มต้นการคำนวณ Auto Grade ประจำวัน...");

        List<process> activeProcesses = EdataDAO.getReadyForAutoGradeProcesses();
        
        if (activeProcesses == null || activeProcesses.isEmpty()) {
            System.out.println("ไม่พบ Process ที่พร้อมตัดเกรด ข้ามการทำงาน");
            return;
        }

        for (process proc : activeProcesses) {
            String regType = proc.getRegister_type(); 
            System.out.println(">>> เริ่มประมวลผล Process ID: " + proc.getId() + " สำหรับทะเบียนประเภท: " + regType);

            Map<Integer, String> gradeRules = new TreeMap<>(Collections.reverseOrder());
            if (proc.getGrade_frequency_mapping() != null && !proc.getGrade_frequency_mapping().isEmpty()) {
                String[] ruleParts = proc.getGrade_frequency_mapping().split(",");
                for (String part : ruleParts) {
                    String[] mapping = part.trim().split("=");
                    if (mapping.length == 2) {
                        gradeRules.put(Integer.parseInt(mapping[1].trim()), mapping[0].trim());
                    }
                }
            }

            int limit = 1000;
            int offset = 0;
            boolean hasMoreData = true;

            while (hasMoreData) {
                List<registers> registersBatch = EdataDAO.getAllRegisterByType(regType, limit, offset);

                if (registersBatch == null || registersBatch.isEmpty()) {
                    hasMoreData = false;
                    break;
                }

                System.out.println("กำลังคำนวณเกรดและคะแนน... Offset " + offset + " ถึง " + (offset + registersBatch.size()));

                List<RegisterGradeHistory> historyBatchToSave = new ArrayList<>();
                List<Object[]> gradesAndPointsToUpdateBatch = new ArrayList<>();

                // ==========================================
                // 4. วนลูปรายชื่อในก้อนนี้ เพื่อตัดเกรดและคำนวณ Point
                // ==========================================

             for (registers reg : registersBatch) {
              
                 String regCode = reg.getRegister_code() + reg.getRegister_daterun() + "-" + reg.getRegister_id();
              
                 Integer moveCount = EdataDAO.getMovementCount(
                         regCode, 
                         proc.getEdata_idoc_grade_ref(), 
                         proc.getApplied_status_array(), 
                         proc.getCycle_duration()
                 );

                 if (moveCount == null) moveCount = 0;

                 String oldGrade = reg.getRegister_grade(); // ของเดิมใน DB (อาจจะเป็น NULL)
              
                 String newGrade = "NONE"; // ค่าเริ่มต้น
                 
                 // 4.1 เช็คเกรดตามความถี่ (A, B, C)
                 for (Map.Entry<Integer, String> entry : gradeRules.entrySet()) {
                     if (moveCount >= entry.getKey()) { 
                         newGrade = entry.getValue();
                         break; 
                     }
                 }

                 // 4.2 ถ้าไม่เข้าเกณฑ์ A,B,C ให้มาเช็ค NEW หรือ NONE
                 if (moveCount == 0 || "NONE".equals(newGrade)) {
                     String extColumnName = proc.getIs_ex_movement_active();
                     String extValue = EdataDAO.GradeCheckExt(extColumnName, reg.getId());

                     if (extValue == null || extValue.trim().isEmpty()) {
                         // ไม่เคยติดต่อเลย
                         if (oldGrade == null) {
                             newGrade = "NEW"; // ของเดิมเป็น NULL ให้เป็น NEW
                         } else {
                             continue; // มีเกรดเดิมอยู่แล้ว ให้ข้าม (รักษาสถานะเดิม)
                         }
                     } else {
                         // "เคยติดต่อ แต่ไม่มีการขาย" (extValue มีค่า แต่ moveCount เป็น 0)
                         newGrade = "NONE";
                     }
                 }

              // ==========================================
                 // 🌟 5. คำนวณ register_poin ตามเกรดใหม่
                 // ==========================================
                 int registerPoint = 0;

                 if ("A".equals(newGrade)) registerPoint = 80;
                 else if ("B".equals(newGrade)) registerPoint = 75;
                 else if ("C".equals(newGrade)) registerPoint = 65;
                 else if ("NEW".equals(newGrade)) {
                     registerPoint = 20;
                     String phone = reg.getPhone_number();
                     String mobile = reg.getMobile_number(); 
                     if ((phone != null && !phone.trim().isEmpty()) || (mobile != null && !mobile.trim().isEmpty())) {
                         registerPoint += 5;
                     }

                     String group = reg.getRegister_groups();
                     if ("อาหาร".equals(group)) registerPoint += 10;
                     else if ("ยา".equals(group)) registerPoint += 5;
                     else if ("เครื่องสำอาง".equals(group)) registerPoint += 2;
                 } 
                 else if ("NONE".equals(newGrade)) {
                     registerPoint = 10;
                     boolean everClosed = EdataDAO.hasEverClosedSale(
                             regCode, 
                             proc.getEdata_idoc_grade_ref(), 
                             proc.getApplied_status_array()
                     );
                     if (everClosed) {
                         registerPoint += 20;
                     }
                 }

                 // 🌟 [เพิ่มใหม่] เช็คเงื่อนไข: ปิดการขายได้ในไตรมาสปัจจุบัน (เช่น เดือน 5 จะอยู่ในช่วงเดือน 4-6)
                 boolean hasQuarterSale = EdataDAO.hasClosedSaleInCurrentQuarter(
                         regCode, 
                         proc.getEdata_idoc_grade_ref(), 
                         proc.getApplied_status_array()
                 );
                 if (hasQuarterSale) {
                     registerPoint += 20; // บวกโบนัสไปเลย 20 คะแนน
                 }

                 // 🌟 [ของเดิม] เช็คเงื่อนไขพิเศษ: ถ้าเกรดเป็น NEW หรือ NONE ให้เช็ค extension_id = 8
                 if ("NEW".equals(newGrade) || "NONE".equals(newGrade)) {
                     boolean hasExt8 = EdataDAO.hasExtension8(reg.getId());
                     if (hasExt8) {
                         registerPoint += 10; 
                     }
                 }
                 
                 // 🌟 [เพิ่มใหม่] ควบคุมเพดานคะแนนสูงสุดไม่ให้เกิน 80
                 if (registerPoint > 80) {
                     registerPoint = 80;
                 }

                 // ==========================================
                 // 6. เตรียมจับใส่ Batch
                 // ==========================================
                    
                 // เช็คว่าเกรดมีการเปลี่ยนแปลงหรือไม่
                 boolean gradeChanged = (oldGrade == null) || (!newGrade.equals(oldGrade));
                    
                 // 🌟 ปรับวิธีเช็คคะแนน: ดึงคะแนนเดิมมาเทียบ ถ้ายอดขยับ (เพราะเพิ่งปิดการขายได้) ให้บันทึกด้วย!
                 // (แก้ reg.getRegister_poin() เป็น Integer เพื่อดัก NULL)
                 Integer currentPoint = reg.getRegister_poin();
                 int oldPoint = (currentPoint != null) ? currentPoint : 0;
                 boolean pointChanged = (oldPoint != registerPoint); 

                 // 🌟 ถ้าเกรดเปลี่ยน "หรือ" คะแนนเปลี่ยน (แม้เกรดจะเท่าเดิม) บังคับให้อัปเดต!
                 if (gradeChanged || pointChanged) {
                        
                     // 1. เพิ่มเข้า Batch ไว้ไปอัปเดต DB
                     gradesAndPointsToUpdateBatch.add(new Object[]{newGrade, registerPoint, reg.getId()});

                     // 2. บันทึก History (บันทึกเฉพาะตอน "เกรดเปลี่ยน" เท่านั้น จะได้ไม่รกตารางประวัติ)
                     if (gradeChanged) {
                         RegisterGradeHistory history = new RegisterGradeHistory();
                         history.setRegisterId(reg.getId());
                         history.setOldGrade((oldGrade != null) ? oldGrade : "NULL"); 
                         history.setNewGrade(newGrade);
                         history.setProcessId(proc.getId());
                         history.setCalculateDate(LocalDateTime.now());
                         
                         historyBatchToSave.add(history); 
                     }
                 }
             }
                // 🚀 ยิง Database ทีเดียวรวด!
                if (!gradesAndPointsToUpdateBatch.isEmpty()) {
                    // เรียก Method ใหม่ที่เพิ่มพารามิเตอร์ Point เข้าไป
                    EdataDAO.updateRegGradeAndPointBatch(gradesAndPointsToUpdateBatch); 
                }
                if (!historyBatchToSave.isEmpty()) {
                    registerGradeHistoryRepository.saveAll(historyBatchToSave); 
                }

                offset += limit; 
            }
            
            
            
        }
        
        System.out.println(">>> ประมวลผล Auto Grade เสร็จสิ้นสมบูรณ์");
    }
}
