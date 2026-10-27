package com.smepro.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smepro.dao.edataDAO;
import com.smepro.dao.holidayDAO;
import com.smepro.dto.GamificationWidgetDTO;
import com.smepro.entity.GamificationGoal;
import com.smepro.entity.QuestLog;
import com.smepro.models.holiday;
import com.smepro.repository.GamificationGoalRepository;
import com.smepro.repository.QuestLogRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GamificationService {

    private final edataDAO EdataDAO;
    private final holidayDAO HolidayDAO;
    private final GamificationGoalRepository gamificationGoalRepository;
    private final QuestLogRepository questLogRepository;

    @Autowired
    public GamificationService(
            edataDAO EdataDAO,
            holidayDAO HolidayDAO,
            GamificationGoalRepository gamificationGoalRepository,
            QuestLogRepository questLogRepository) {
        this.EdataDAO = EdataDAO;
        this.HolidayDAO = HolidayDAO;
        this.gamificationGoalRepository = gamificationGoalRepository;
        this.questLogRepository = questLogRepository;
    }

    @Transactional
    public void processDailyGamification(LocalDate lastCheckedDate) {
        LocalDate currentDate = LocalDate.now();
        String year = currentDate.format(DateTimeFormatter.ofPattern("yyyy"));

        // 1. ดึงวันหยุด
        List<holiday> holidays = HolidayDAO.getall(year);
        Set<LocalDate> holidayDates = holidays.stream()
                .map(h -> LocalDate.parse(h.getHoliday_date()))
                .collect(Collectors.toSet());

        // 2. ดึง GamificationGoal ทั้งหมดจาก JPA ทิ้ง Goal เก่าไปเลย
        List<GamificationGoal> activeGoals = gamificationGoalRepository.findAll();

        LocalDate dateToCheck = lastCheckedDate;

        // 3. ลูปเช็คตามวัน
        while (!dateToCheck.isAfter(currentDate)) {
            int dayOfWeek = dateToCheck.getDayOfWeek().getValue();

            if (dayOfWeek == 6 || dayOfWeek == 7 || holidayDates.contains(dateToCheck)) {
                System.out.println("Skip: " + dateToCheck + " (Holiday/Weekend)");
            } else {
                final LocalDate finalDate = dateToCheck;

                activeGoals.forEach(gamificationGoal -> { 
                    String[] dueDate = getDateRange(finalDate, gamificationGoal);
                 // เช็คกับ dueDate[1] ซึ่งก็คือ End Date ครับ
                 if (dueDate != null && dueDate[1].equals(finalDate.toString())) { 
                     evaluateGamificationGoal(dueDate, gamificationGoal);
                 }
                });
            }
            dateToCheck = dateToCheck.plusDays(1);
        }
    }
    
    
    
 // คืนค่าอาร์เรย์ String [startDate, endDate]
    private String[] getDateRange(LocalDate currentDate, GamificationGoal goal) {
        String freq = goal.getFrequency() != null ? goal.getFrequency().toUpperCase() : "EVERY_DAY";
        String setto = goal.getSetto();
        
        String startDate = currentDate.toString();
        String endDate = currentDate.toString();

        switch (freq) {
            case "EVERY_DAY":
                // นับของวันนี้วันเดียว
                break;
                
            case "EVERY_WEEK":
                // สมมติ setto = "ศ" (วันศุกร์) -> ตัดรอบศุกร์ถึงพฤหัส
                DayOfWeek targetDay = getTargetDayOfWeek(setto);
                LocalDate weekEnd = currentDate.with(TemporalAdjusters.nextOrSame(targetDay));
                LocalDate weekStart = weekEnd.minusDays(6);
                startDate = weekStart.toString();
                endDate = weekEnd.toString();
                break;
                
            case "EVERY_MONTH":
                // ** ลอจิกตัดรอบรายเดือน **
                if (setto == null || setto.equalsIgnoreCase("LAST")) {
                    // ถ้าเป็นรอบสิ้นเดือนปกติ (วันที่ 1 ถึง สิ้นเดือน)
                    startDate = currentDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
                    endDate = currentDate.with(TemporalAdjusters.lastDayOfMonth()).toString();
                } else {
                    int startDay = Integer.parseInt(setto);
                    
                    if (startDay == 1) {
                        // ป้องกันคนใส่ 1 (ให้เทียบเท่ากับรอบ 1-สิ้นเดือนปกติ)
                        startDate = currentDate.with(TemporalAdjusters.firstDayOfMonth()).toString();
                        endDate = currentDate.with(TemporalAdjusters.lastDayOfMonth()).toString();
                    } else {
                        // วันสิ้นสุดรอบ คือ วันเริ่มต้น - 1 (เช่น เลือก 26 จะไปจบ 25)
                        int endDay = startDay - 1;
                        
                        if (currentDate.getDayOfMonth() >= startDay) {
                            // ถ้าวันนี้เลยวันเริ่มรอบมาแล้ว (เช่น วันนี้ 28 เลือก 26) -> รอบคือ 26 เดือนนี้ ถึง 25 เดือนหน้า
                            LocalDate nextMonth = currentDate.plusMonths(1);
                            
                            // Math.min เผื่อกรณีเลือก 31 แต่เดือนนั้นมีแค่ 30 วัน
                            int actualStart = Math.min(startDay, currentDate.lengthOfMonth());
                            int actualEnd = Math.min(endDay, nextMonth.lengthOfMonth());
                            
                            startDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), actualStart).toString();
                            endDate = LocalDate.of(nextMonth.getYear(), nextMonth.getMonthValue(), actualEnd).toString();
                        } else {
                            // ถ้าวันนี้ยังไม่ถึงวันเริ่มรอบ (เช่น วันนี้ 15 เลือก 26) -> รอบคือ 26 เดือนที่แล้ว ถึง 25 เดือนนี้
                            LocalDate lastMonth = currentDate.minusMonths(1);
                            
                            int actualStart = Math.min(startDay, lastMonth.lengthOfMonth());
                            int actualEnd = Math.min(endDay, currentDate.lengthOfMonth());
                            
                            startDate = LocalDate.of(lastMonth.getYear(), lastMonth.getMonthValue(), actualStart).toString();
                            endDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), actualEnd).toString();
                        }
                    }
                }
                break;
                
            case "EVERY_YEAR":
                // ของทั้งปี
                startDate = LocalDate.of(currentDate.getYear(), 1, 1).toString();
                endDate = LocalDate.of(currentDate.getYear(), 12, 31).toString();
                break;
        }
        
        return new String[]{startDate, endDate};
    }

    // เมธอดช่วย (Helper) แปลงอักษรย่อเป็น DayOfWeek
    private DayOfWeek getTargetDayOfWeek(String dayText) {
        if (dayText == null) return DayOfWeek.FRIDAY;
        switch (dayText) {
            case "จ": return DayOfWeek.MONDAY;
            case "อ": return DayOfWeek.TUESDAY;
            case "พ": return DayOfWeek.WEDNESDAY;
            case "พฤ": return DayOfWeek.THURSDAY;
            case "ศ": return DayOfWeek.FRIDAY;
            case "ส": return DayOfWeek.SATURDAY;
            case "อา": return DayOfWeek.SUNDAY;
            default: return DayOfWeek.FRIDAY;
        }
    }

    private String calculateMonthly(LocalDate dateToCheck, GamificationGoal gamificationGoal) {
        String days = gamificationGoal.getSetto() == null ? "LAST" : gamificationGoal.getSetto();
        int year = dateToCheck.getYear();
        int month = dateToCheck.getMonthValue();

        if (days.equalsIgnoreCase("LAST")) {
            return LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()).toString();
        } else {
            int day = Math.min(Integer.parseInt(days), LocalDate.of(year, month, 1).lengthOfMonth());
            return LocalDate.of(year, month, day).toString();
        }
    }

 // --- ส่วนประเมินผลแจกแต้ม (ผูกกับ EdataDAO) ---
    private void evaluateGamificationGoal(String[] dueDate, GamificationGoal gamificationGoal) {
        // 1. ผู้รับผิดชอบอาจมีหลายคน (เช่น "user_1,user_2") ต้องแยกและวนลูปตรวจรายคน
        String responsibleStr = gamificationGoal.getResponsible();
        if (responsibleStr == null || responsibleStr.isEmpty()) return;

        String[] users = responsibleStr.split(",");

        for (String user : users) {
        	String userid = user.trim();
            String username = user.trim();
        	
        	if (user.contains("_")) {
                String[] parts = user.split("_");
                userid = parts[0].trim();
                username = parts.length > 1 ? parts[1].trim() : userid; // เอาส่วนที่ 2 มาตัด Space
            }
        	
            // 2. ป้องกันแจกแต้มซ้ำ (เช็คว่าวันนี้ คนนี้ ภารกิจนี้ เคยบันทึกไปแล้วหรือยัง)
            // หมายเหตุ: ต้องไปสร้างเมธอด existsByGoalIdAndUserNameAndActionDate ใน QuestLogRepository ด้วยนะครับ
            boolean alreadyLogged = questLogRepository.existsByGoalIdAndUserNameAndActionDate(gamificationGoal.getId(), username, dueDate[1]);
            if (alreadyLogged) {
                continue; // ถ้าเคยแจก/บันทึกไปแล้ว ข้ามคนนี้ไปเลย
            }

            double currentResult = 0;
            
            try {
                // 3. ดึงข้อมูลจาก EdataDAO (ปรับให้ส่ง isGlobal และ extensionId ไปด้วย)
                if ("COUNT".equals(gamificationGoal.getGoalType()) || "COUNT_DISTINCT".equals(gamificationGoal.getGoalType())) {
                    currentResult = EdataDAO.countGamificationResult(
                        gamificationGoal.getEformId(), 
                        gamificationGoal.getConditionStatus(), 
                        userid, 
                        dueDate[0],
                        dueDate[0],
                        gamificationGoal.getExtensionId(), // ส่งรหัสส่วนขยาย
                        gamificationGoal.getIsGlobal(),    // ส่ง Global Flag
                        gamificationGoal.getGoalType()     // ส่งเพื่อไปเช็คว่าต้อง COUNT หรือ COUNT DISTINCT
                    );
                } else if ("SUM".equals(gamificationGoal.getGoalType())) {
                    currentResult = EdataDAO.sumGamificationResult(
                        gamificationGoal.getEformId(), 
                        gamificationGoal.getTargetColumn(), 
                        gamificationGoal.getConditionStatus(), 
                        userid, 
                        dueDate[0],
                        dueDate[1],
                        gamificationGoal.getExtensionId(),
                        gamificationGoal.getIsGlobal()
                    );
                } else if ("EXPIRY".equals(gamificationGoal.getGoalType())) {
                    int expiredCount = EdataDAO.countExpiredDocs(gamificationGoal.getEformId(), userid, dueDate[0]);
                    if (expiredCount > 0) {
                        saveQuestLog(dueDate[0], gamificationGoal, userid, 0, "PENALTY", -10 * expiredCount, "หักแต้มเอกสารหมดอายุ " + expiredCount + " รายการ", expiredCount, 0);
                        continue;
                    }
                    
                    
                    
                    
                    currentResult = 1; // รอดจากการหมดอายุ ถือว่าสำเร็จ
                    gamificationGoal.setTargetValue(1.0);
                
                } else if ("SLA_24H".equals(gamificationGoal.getGoalType())) {
                    // นับงานที่ปิด/อัปเดต ทันภายใน 24 ชม.
                    currentResult = EdataDAO.countReceivedWithin24Hours(
                        gamificationGoal.getEformId(), 
                        gamificationGoal.getConditionStatus(), 
                        userid, 
                        dueDate[0], // startDate
                        dueDate[1],  // endDate
                        gamificationGoal.getIsGlobal()
                    );
                
                }else if ("COUNT_RECEIVED".equals(gamificationGoal.getGoalType())) {
                // โหมดผู้รับเอกสาร
                currentResult = EdataDAO.countReceivedDocs(gamificationGoal.getEformId(), gamificationGoal.getConditionStatus(), userid, dueDate[0], dueDate[1]);
                
            } else if ("CALENDAR_COMPLETED".equals(gamificationGoal.getGoalType())) {
                // นับงานในปฏิทินที่สำเร็จ (สมมติ status = 1 คือเสร็จ)
                currentResult = EdataDAO.countCalendarEvents(username, 1, dueDate[0]);
                
            } else if ("CALENDAR_MISSED".equals(gamificationGoal.getGoalType())) {
                // นับงานค้าง (สมมติ status = 0 คือค้าง) -> เอาไว้หักแต้มเหมือน EXPIRY
                int missedCount = (int) EdataDAO.countCalendarEvents(username, 0, dueDate[0]);
                if (missedCount > 0) {
                    // แจก Penalty ติดลบ
                    saveQuestLog(dueDate[0], gamificationGoal, userid, 0, "PENALTY", -10 * missedCount, "หักแต้มตามงานค้าง " + missedCount + " งาน", missedCount, 0);
                    continue;
                }
                currentResult = 1; // ไม่มีค้าง ถือว่าผ่าน
                gamificationGoal.setTargetValue(1.0);
                
            }
                
                
                
             // 4. คำนวณเปอร์เซ็นต์ตามประเภทเป้าหมาย
                double target = gamificationGoal.getTargetValue() != null ? gamificationGoal.getTargetValue() : 1.0;
                int percent = 0;

                // เช็คว่าเป็นกลุ่มเป้าหมายแบบไหน?
                if ("SLA_24H".equals(gamificationGoal.getGoalType()) || 
                    "CALENDAR_MISSED".equals(gamificationGoal.getGoalType()) || 
                    "EXPIRY".equals(gamificationGoal.getGoalType())) {
                    
                    // 🔻 กลุ่มที่ 1: "ยิ่งน้อยยิ่งดี" (ห้ามเกินเป้า/โควต้า)
                    if (currentResult <= target) {
                        percent = 100; // ทำพลาดน้อยกว่าหรือเท่ากับโควต้า = ถือว่ารอด! ได้ 100%
                    } else {
                        percent = 0;   // พลาดเยอะเกินโควต้า = ล้มเหลว 0%
                        
                        // (Optional) ถ้าทะลุเป้าแล้วอยากหักแต้มด้วย ให้เปิดคอมเมนต์บรรทัดล่างนี้ครับ
                        // int penalty = (int) (currentResult - target) * -10; 
                        // saveQuestLog(dueDate[1], gamificationGoal, userid, 0, "PENALTY", penalty, "ทำงานพลาดเกินโควต้าที่กำหนด", currentResult, target);
                        // continue; 
                    }
                    
                } else {
                    // 🔺 กลุ่มที่ 2: "ยิ่งมากยิ่งดี" (ต้องทำให้ถึงเป้า) แบบปกติ
                    target = target == 0 ? 1.0 : target; // กันเหนียวห้ามหารด้วย 0
                    percent = (int) Math.min(((currentResult / target) * 100), 100);
                }

                // 5. แจกแต้มและบันทึก Log
                if (percent >= 100) {
                    saveQuestLog(dueDate[1], gamificationGoal, userid, 100, "SUCCESS", gamificationGoal.getXpReward(), "สำเร็จภารกิจ 100%", currentResult, target);
                } else if (percent > 0) {
                    int partialXp = (gamificationGoal.getXpReward() * percent) / 100;
                    saveQuestLog(dueDate[1], gamificationGoal, userid, percent, "PARTIAL", partialXp, "สำเร็จภารกิจบางส่วน", currentResult, target);
                } else {
                    saveQuestLog(dueDate[1], gamificationGoal, userid, 0, "FAILED", 0, "ภารกิจล้มเหลว", currentResult, target);
                }

            } catch (Exception e) {
                System.err.println("Error evaluating goal: " + gamificationGoal.getId() + " for user: " + username + " - " + e.getMessage());
            }
        }
    }

			    // --- ส่วนบันทึกประวัติ (ใช้ QuestLog JPA) ---
			    private void saveQuestLog(String dateDue, GamificationGoal gamificationGoal, String username, int percent, String status, int xp, String remark, double currentResult, double targetValue) {
			        QuestLog log = new QuestLog();
			        log.setGoalId(gamificationGoal.getId());
			        log.setUserName(username); // บันทึกเป็นรายบุคคลแล้ว!
			        log.setActionDate(dateDue);
			        log.setCompletionPercentage(percent);
			        log.setStatus(status);
			        log.setXpEarned(xp);
			        log.setRemark(remark);
			        
			        // แนะนำให้เพิ่ม 2 ฟิลด์นี้ใน Entity QuestLog ด้วยครับ สำคัญมากตอนทำ Report
			        log.setActualResult(currentResult); 
			        log.setTargetValue(targetValue);
			        
			        questLogRepository.save(log);
			    }
			    
    
    
			 // ดึงข้อมูล Widget สดๆ สำหรับ 1 คน (แบบ Real-time)
			    public GamificationWidgetDTO getWidgetDataForUser(String user_name, String userDisplayName) {
			    	String userid = user_name.split("_")[0];
			        GamificationWidgetDTO dto = new GamificationWidgetDTO();
			        dto.setPlayerName(userDisplayName);
			        
			        // 1. ดึงแต้มรวมจาก Log เดิมที่เคยมียืนพื้นไว้ก่อน
			        Integer baseTotalXp = questLogRepository.getTotalXpByUsername(userid); 
			        int currentTotalXp = baseTotalXp != null ? baseTotalXp : 0;

			        // 2. ดึงเป้าหมายที่เปิดใช้งานอยู่ และคนนี้รับผิดชอบ
			        List<GamificationGoal> myGoals = gamificationGoalRepository.findAll().stream()
			                .filter(g -> g.getStatus() == 1 && g.getResponsible() != null && g.getResponsible().contains(userid))
			                .collect(Collectors.toList());

			        List<GamificationWidgetDTO.QuestDTO> questDTOs = new ArrayList<>();
			        int completedCount = 0;
			        int totalPercentSum = 0;
			        int pendingRewardXp = 0; // แต้มที่ทำได้วันนี้แต่ยังไม่ได้ลง Log

			        LocalDate today = LocalDate.now();

			        for (GamificationGoal goal : myGoals) {
			            GamificationWidgetDTO.QuestDTO qDto = new GamificationWidgetDTO.QuestDTO();
			            qDto.setGoalName(goal.getGoalName());
			            qDto.setRewardXp(goal.getXpReward() != null ? goal.getXpReward() : 0);
			            qDto.setGoalType(goal.getGoalType());
			            qDto.setFrequency(goal.getFrequency()); // 👈 เพิ่มบรรทัดนี้ลงไปครับ!

			            double currentResult = 0;
			            double target = goal.getTargetValue() != null ? goal.getTargetValue() : 1.0;

			            String[] dueDate = getDateRange(today, goal);
			            try {
			                // ดึงผลงาน "สดๆ" จาก Database ตอนนี้เลย
			                if ("COUNT".equals(goal.getGoalType()) || "COUNT_DISTINCT".equals(goal.getGoalType())) {
			                    currentResult = EdataDAO.countGamificationResult(goal.getEformId(), goal.getConditionStatus(), userid, dueDate[0], dueDate[1], goal.getExtensionId(), goal.getIsGlobal(), goal.getGoalType());
			                    
			                } else if ("SUM".equals(goal.getGoalType())) {
			                    currentResult = EdataDAO.sumGamificationResult(goal.getEformId(), goal.getTargetColumn(), goal.getConditionStatus(), userid, dueDate[0], dueDate[1], goal.getExtensionId(), goal.getIsGlobal());
			                    
			                } else if ("COUNT_RECEIVED".equals(goal.getGoalType())) {
			                    currentResult = EdataDAO.countReceivedDocs(goal.getEformId(), goal.getConditionStatus(), userid, dueDate[0], dueDate[1]);
			                    
			                } else if ("CALENDAR_COMPLETED".equals(goal.getGoalType())) {
			                    String usernameForCal = userDisplayName.split(" ")[0]; 
			                    currentResult = EdataDAO.countCalendarEvents(usernameForCal, 1, dueDate[0]);
			                    
			                } else if ("SLA_24H".equals(goal.getGoalType())) {
			                    // ดึงจำนวนงานที่ "ช้าเกิน 24 ชม." (อย่าลืมให้ SQL เช็คเงื่อนไข > 24 นะครับ)
			                    currentResult = EdataDAO.countReceivedWithin24Hours(goal.getEformId(), goal.getConditionStatus(), userid, dueDate[0], dueDate[1], goal.getIsGlobal());

			                } else if ("CALENDAR_MISSED".equals(goal.getGoalType())) {
			                    // ดึงจำนวนงานค้างจริงๆ มาเลย
			                    String usernameForCal = userDisplayName.split(" ")[0]; 
			                    currentResult = (double) EdataDAO.countCalendarEvents(usernameForCal, 0, dueDate[0]);
			                    
			                } else if ("EXPIRY".equals(goal.getGoalType())) {
			                    // นับเอกสารหมดอายุ
			                    currentResult = (double) EdataDAO.countExpiredDocs(goal.getEformId(), userid, dueDate[0]);
			                }
			                
			            } catch (Exception e) {
			                System.err.println("Exception "+e);
			                currentResult = 0;
			            }

			            qDto.setCurrentResult(currentResult);
			            qDto.setTargetValue(target);

			   
			            
			            
			            
			            
			         // --- ✨ ปรับปรุงการคำนวณเปอร์เซ็นต์ (เฉลี่ยตามความถี่) ---
			            int percent = 0;
			            double adjustedTarget = target; // ใช้ตัวแปรเป้าหมายที่ปรับปรุงแล้ว

			            // ถ้าเป็นเป้าหมายรายเดือน/รายสัปดาห์ และไม่ใช่กลุ่ม "จับผิด"
			            // เราจะเฉลี่ยเป้าหมายเพื่อให้พนักงานเห็น Progress ที่แท้จริงของวันนี้
			            if (!"EVERY_DAY".equals(goal.getFrequency()) && 
			                !"SLA_24H".equals(goal.getGoalType()) && 
			                !"CALENDAR_MISSED".equals(goal.getGoalType()) && 
			                !"EXPIRY".equals(goal.getGoalType())) {
			                
			                if ("EVERY_MONTH".equals(goal.getFrequency())) {
			                    // เฉลี่ยรายเดือน: (เป้าหมาย / จำนวนวันในเดือนนี้) * วันที่ปัจจุบัน
			                    int totalDaysInMonth = today.lengthOfMonth();
			                    int currentDay = today.getDayOfMonth();
			                    adjustedTarget = (target / totalDaysInMonth) * currentDay;
			                } else if ("EVERY_WEEK".equals(goal.getFrequency())) {
			                    // เฉลี่ยรายสัปดาห์: สมมติทำมาแล้วกี่วันในสัปดาห์นี้ (1-7)
			                    int dayInWeek = today.getDayOfWeek().getValue();
			                    adjustedTarget = (target / 7.0) * dayInWeek;
			                }
			            }

			            if ("SLA_24H".equals(goal.getGoalType()) || 
			                "CALENDAR_MISSED".equals(goal.getGoalType()) || 
			                "EXPIRY".equals(goal.getGoalType())) {
			                
			                // กลุ่มจับผิด: ห้ามเกินเป้าหมาย (ใช้เป้าหมายเต็ม ไม่ต้องเฉลี่ย)
			                percent = (currentResult <= target) ? 100 : 0;
			                
			            } else {
			                // กลุ่มปกติ: ใช้เป้าหมายที่เฉลี่ยแล้ว (adjustedTarget)
			                // เพื่อให้ถ้าวันนี้ควรได้ 2 แล้วทำได้ 2 หลอดจะขึ้น 100% (On Track)
			                double finalTarget = adjustedTarget <= 0 ? 1.0 : adjustedTarget;
			                percent = (int) Math.min(((currentResult / finalTarget) * 100), 100);
			            }
			            
			            
			            
			            
			            qDto.setPercentComplete(percent);
			            // ---------------------------------------------
			            
			            // --- ส่วนที่เพิ่มให้ Real-time ---
			            // เช็คก่อนว่าในวันนี้ ภารกิจนี้ เคยถูก Batch ตรวจและแจกแต้มไปหรือยัง?
			            boolean alreadyLoggedToday = questLogRepository.existsByGoalIdAndUserNameAndActionDate(goal.getId(), userid, dueDate[1]);
			            
			            if (!alreadyLoggedToday && percent > 0) {
			                // ถ้ายังไม่ลง Log ให้เอาแต้มที่ "น่าจะได้" มาบวกโชว์บน Widget ก่อน
			                if (percent >= 100) {
			                    pendingRewardXp += qDto.getRewardXp();
			                } else {
			                    pendingRewardXp += (qDto.getRewardXp() * percent) / 100;
			                }
			            }
			            
			            if (percent >= 100) completedCount++;
			            totalPercentSum += percent;

			            questDTOs.add(qDto);
			        }

			     // 1. คำนวณ XP รวมทั้งหมด (เอาแต้มจาก Log เดิม + แต้มที่กำลังทำได้สดๆ)
			        currentTotalXp = (baseTotalXp != null ? baseTotalXp : 0) + pendingRewardXp;
			        dto.setTotalXp(currentTotalXp);

			        // 2. กำหนด Level และฉายา (Hardcode ไปก่อน ง่ายและไว)
			        int level = 1;
			        String levelTitle = "ฝึกหัด";

			        if (currentTotalXp >= 5000) {
			            level = 5;
			            levelTitle = "ตำนาน";
			        } else if (currentTotalXp >= 3000) {
			            level = 4;
			            levelTitle = "ยอดเยี่ยม";
			        } else if (currentTotalXp >= 1500) {
			            level = 3;
			            levelTitle = "มืออาชีพ";
			        } else if (currentTotalXp >= 500) {
			            level = 2;
			            levelTitle = "มือใหม่";
			        } else {
			            level = 1;
			            levelTitle = "ฝึกหัด";
			        }

			        // เซ็ตค่าลง DTO เพื่อส่งไปให้หน้าจอ
			        dto.setLevel(level);
			        dto.setLevelTitle(levelTitle);

			        dto.setQuests(questDTOs);
			        dto.setTotalQuests(myGoals.size());
			        dto.setCompletedQuests(completedCount);
			        dto.setOverallPercent(myGoals.isEmpty() ? 0 : (totalPercentSum / myGoals.size()));

			        return dto;

			    }
	
				    // ฟังก์ชันช่วยหา Format วันที่สำหรับดึงข้อมูลปัจจุบัน
				    private String getCurrentDatePrefix(LocalDate date, String frequency) {
				        if (frequency == null) return date.toString(); // 2026-03-30
				        switch (frequency.toUpperCase()) {
				            case "EVERY_MONTH": return date.format(DateTimeFormatter.ofPattern("yyyy-MM")); // 2026-03
				            case "EVERY_YEAR": return date.format(DateTimeFormatter.ofPattern("yyyy"));     // 2026
				            default: return date.toString(); // EVERY_DAY หรือ EVERY_WEEK ใช้ของวันนี้
				        }
				    }

				    
				    
				    public Map<String, Object> getUserStatsOverview(String user_name) {
				    	
				     	String username = user_name.split("_")[0];
				        Map<String, Object> stats = new HashMap<>();
				        
				        // ย้อนหลัง 60 วัน
				        String startDate = LocalDate.now().minusDays(60).toString(); 

				        // 1. ข้อมูลรายวัน (Daily Average %)
				        List<Object[]> dailyData = questLogRepository.getDailyCompletionHistory(username, startDate);
				        List<String> labelsDay = new ArrayList<>();
				        List<Integer> dataDay = new ArrayList<>();
				        List<String> colorDay = new ArrayList<>(); // ทำสีเขียว/แดง
				        
				        for (Object[] row : dailyData) {
				            labelsDay.add((String) row[0]);
				            int percent = ((Number) row[1]).intValue();
				            dataDay.add(percent);
				            // ถ้าทำได้ 100% ให้สีเขียว ถ้าหลุดเป้าให้สีแดงส้ม
				            colorDay.add(percent >= 100 ? "#28C76F" : "#EA5455"); 
				        }
				        stats.put("dailyLabels", labelsDay);
				        stats.put("dailyData", dataDay);
				        stats.put("dailyColors", colorDay);

				        // 2. ข้อมูลแยกกิจกรรม (Task Average %)
				        List<Object[]> questData = questLogRepository.getQuestCompletionAverages(username, startDate);
				        List<String> labelsQuest = new ArrayList<>();
				        List<Integer> dataQuest = new ArrayList<>();
				        
				        for (Object[] row : questData) {
				            Long goalId = ((Number) row[0]).longValue();
				            gamificationGoalRepository.findById(goalId).ifPresent(goal -> {
				                labelsQuest.add(goal.getGoalName());
				            });
				            dataQuest.add(((Number) row[1]).intValue());
				        }
				        stats.put("questLabels", labelsQuest);
				        stats.put("questData", dataQuest);

				        return stats;
				    }
    
    
    
    
    
    
}