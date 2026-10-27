package com.smepro.repository;

import com.smepro.entity.ApprovalInbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApprovalInboxRepository extends JpaRepository<ApprovalInbox, Long> {

    /** ดึง Inbox ที่รอ Approve ของ user นี้ */
    List<ApprovalInbox> findByApproverAndStatusOrderByCreatedAtDesc(String approver, String status);

    /** นับรายการ PENDING ของ approver */
    long countByApproverAndStatus(String approver, String status);

    /** ดึงตาม appId (ผ่าน project) */
    @Query("SELECT a FROM ApprovalInbox a JOIN a.project p WHERE p.appId = :appId " +
           "AND a.approver = :approver ORDER BY a.createdAt DESC")
    List<ApprovalInbox> findByAppIdAndApprover(@Param("appId") Integer appId,
                                               @Param("approver") String approver);
}