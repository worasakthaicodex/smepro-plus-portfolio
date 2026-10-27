package com.smepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.QuestLog;

public interface QuestLogRepository extends JpaRepository<QuestLog, Long> {

		// 1. หา XP รวมทั้งหมดของ User (ใช้ COALESCE เพื่อเปลี่ยน Null เป็น 0)
	    @Query("SELECT COALESCE(SUM(q.xpEarned), 0) FROM QuestLog q WHERE q.userName = :username")
	    Integer getTotalXpByUsername(@Param("username") String username);

	    // 2. หา XP ประจำเดือนของ User (เผื่อเอาไปทำตาราง Leaderboard รายเดือน)
	    // ส่ง actionDate เป็น "2026-03%" เพื่อดึงเฉพาะของเดือนมีนาคม
	    @Query("SELECT COALESCE(SUM(q.xpEarned), 0) FROM QuestLog q WHERE q.userName = :username AND q.actionDate LIKE :monthPrefix%")
	    Integer getMonthlyXp(@Param("username") String username, @Param("monthPrefix") String monthPrefix);

	    
	 // 1. ดึงสถิติรายวันย้อนหลัง X วัน (เพื่อทำกราฟ 1-60 วัน)
	    @Query("SELECT q.actionDate, SUM(q.xpEarned) FROM QuestLog q WHERE q.userName = :username AND q.actionDate >= :startDate GROUP BY q.actionDate ORDER BY q.actionDate ASC")
	    List<Object[]> getDailyXpHistory(@Param("username") String username, @Param("startDate") String startDate);

	    // 2. ดึงสถิติแยกตามภารกิจในเดือนนี้ (เพื่อทำกราฟวงกลม/โดนัท)
	    // หมายเหตุ: ถ้าใช้ Join ไม่ได้ ให้ดึงแบบง่ายๆ โดยจัดกลุ่มตาม goalId แทน
	    @Query("SELECT q.goalId, SUM(q.xpEarned) FROM QuestLog q WHERE q.userName = :username AND q.actionDate LIKE :monthPrefix% GROUP BY q.goalId")
	    List<Object[]> getMonthlyXpByQuest(@Param("username") String username, @Param("monthPrefix") String monthPrefix);
	
	    
	 // 1. ดึง % ความสำเร็จเฉลี่ยรายวัน ย้อนหลัง 60 วัน
	    @Query("SELECT q.actionDate, AVG(q.completionPercentage) FROM QuestLog q WHERE q.userName = :username AND q.actionDate >= :startDate GROUP BY q.actionDate ORDER BY q.actionDate ASC")
	    List<Object[]> getDailyCompletionHistory(@Param("username") String username, @Param("startDate") String startDate);

	    // 2. ดึง % ความสำเร็จเฉลี่ย แยกตามภารกิจ ย้อนหลัง 60 วัน (ดูว่างานไหนสอบตกบ่อย)
	    @Query("SELECT q.goalId, AVG(q.completionPercentage) FROM QuestLog q WHERE q.userName = :username AND q.actionDate >= :startDate GROUP BY q.goalId ORDER BY AVG(q.completionPercentage) ASC") // ORDER BY ASC เอาอันที่ห่วยสุดขึ้นก่อน
	    List<Object[]> getQuestCompletionAverages(@Param("username") String username, @Param("startDate") String startDate);
	    
	    // 3. ป้องกันการแจกแต้มเบิ้ลซ้ำซ้อนในวันเดียวกัน (ที่เราคุยกันไว้ก่อนหน้า)
	    boolean existsByGoalIdAndUserNameAndActionDate(Long goalId, String userName, String actionDate);
}
