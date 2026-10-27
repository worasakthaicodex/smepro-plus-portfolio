package com.smepro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smepro.entity.QuestLog;
import com.smepro.entity.RegisterGradeHistory;

public interface RegisterGradeHistoryRepository  extends JpaRepository<RegisterGradeHistory, Long>{

}
