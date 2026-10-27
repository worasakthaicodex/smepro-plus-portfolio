package com.smepro.repository;

import com.smepro.entity.ProjectPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectPhaseRepository extends JpaRepository<ProjectPhase, Long> {
    List<ProjectPhase> findByProjectIdOrderBySequenceNoAsc(Long projectId);
    List<ProjectPhase> findByProjectIdAndGateStatus(Long projectId, String gateStatus);
}
