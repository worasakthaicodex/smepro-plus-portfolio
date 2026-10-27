// ===== WbsElementRepository.java =====
package com.smepro.repository;

import com.smepro.entity.WbsElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WbsElementRepository extends JpaRepository<WbsElement, Long> {

    /** ดึง Root WBS ทั้งหมดของโครงการ (parent = null) เรียงตาม sortOrder */
    List<WbsElement> findByProjectIdAndParentIsNullOrderBySortOrderAsc(Long projectId);

    /** ดึง Task ที่ assign ให้ user คนนี้ */
    List<WbsElement> findByProjectIdAndAssigneeAndElementType(Long projectId, String assignee, String elementType);

    /** ดึงทุก Task ที่ยังไม่เสร็จ ของ user (ทุก project ใน appId) */
    @Query("SELECT w FROM WbsElement w JOIN w.project p " +
           "WHERE p.appId = :appId AND w.assignee = :assignee " +
           "AND w.elementType = 'TASK' AND w.status <> 'COMPLETED' " +
           "ORDER BY w.endDate ASC")
    List<WbsElement> findMyPendingTasks(@Param("appId") Integer appId, @Param("assignee") String assignee);

    /** ดึงทุก child ของ parent นี้ */
    List<WbsElement> findByParentIdOrderBySortOrderAsc(Long parentId);

    /** ดึง WBS ทั้งหมดของ phase */
    List<WbsElement> findByPhaseIdOrderBySortOrderAsc(Long phaseId);

    /** นับ Task แยกตาม status ของโครงการ */
    @Query("SELECT w.status, COUNT(w) FROM WbsElement w " +
           "WHERE w.project.id = :projectId AND w.elementType = 'TASK' GROUP BY w.status")
    List<Object[]> countTaskByStatus(@Param("projectId") Long projectId);

    /** คำนวณ progress เฉลี่ยของโครงการ */
    @Query("SELECT AVG(w.progress) FROM WbsElement w " +
           "WHERE w.project.id = :projectId AND w.elementType = 'TASK'")
    Double avgProgressByProject(@Param("projectId") Long projectId);
}
