package com.smepro.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smepro.entity.CampaignPromotion;
import com.smepro.entity.GamificationGoal;
import com.smepro.entity.account_ledger;
import com.smepro.models.edata;


public interface CampaignPromotionRepository extends  JpaRepository<CampaignPromotion, Long>  { 
	
	// รูปแบบที่ 1: ใช้ Spring Data JPA Naming Convention (ง่ายและรวดเร็ว)
    List<CampaignPromotion> findByTargetAudienceAndStatus(String targetAudience, String status);
}
