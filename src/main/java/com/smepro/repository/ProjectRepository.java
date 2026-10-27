// ===== ProjectRepository.java =====
package com.smepro.repository;

import com.smepro.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    /** ดึงโครงการทั้งหมดของ appId นี้ เรียงตามวันสร้างล่าสุด */
    List<Project> findByAppIdOrderByCreatedAtDesc(Integer appId);

    /** ดึงตาม status */
    List<Project> findByAppIdAndStatusOrderByCreatedAtDesc(Integer appId, String status);

    /** ดึง Pipeline (status = PIPELINE) */
    List<Project> findByAppIdAndStatusIn(Integer appId, List<String> statuses);

    /** ค้นหาตามชื่อหรือ code */
    @Query("SELECT p FROM Project p WHERE p.appId = :appId " +
           "AND (LOWER(p.projectName) LIKE LOWER(CONCAT('%',:keyword,'%')) " +
           "OR LOWER(p.projectCode) LIKE LOWER(CONCAT('%',:keyword,'%')))")
    List<Project> searchByKeyword(@Param("appId") Integer appId, @Param("keyword") String keyword);

    /** นับโครงการแยกตาม status */
    @Query("SELECT p.status, COUNT(p) FROM Project p WHERE p.appId = :appId GROUP BY p.status")
    List<Object[]> countByStatus(@Param("appId") Integer appId);

    /** ดึงเฉพาะ ARCHIVED */
    List<Project> findByAppIdAndStatusOrderByUpdatedAtDesc(Integer appId, String status);
}
