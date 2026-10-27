package com.smepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smepro.entity.GamificationGoal;

public interface GamificationGoalRepository extends JpaRepository<GamificationGoal, Long> {

	@Query("SELECT g FROM GamificationGoal g WHERE g.processId = :processId")
    List<GamificationGoal> getAllGoalByprocesses(@Param("processId") int processId);

}
