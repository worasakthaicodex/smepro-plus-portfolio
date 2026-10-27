package com.smepro.repository;

import com.smepro.entity.BudgetLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BudgetLineRepository extends JpaRepository<BudgetLine, Long> {
    List<BudgetLine> findByProjectIdOrderByCostTypeAsc(Long projectId);

    @Query("SELECT SUM(b.plannedAmount) FROM BudgetLine b WHERE b.project.id = :projectId")
    BigDecimal sumPlannedByProject(@Param("projectId") Long projectId);

    @Query("SELECT SUM(b.actualAmount) FROM BudgetLine b WHERE b.project.id = :projectId")
    BigDecimal sumActualByProject(@Param("projectId") Long projectId);

    @Query("SELECT SUM(b.commitmentAmount) FROM BudgetLine b WHERE b.project.id = :projectId")
    BigDecimal sumCommitmentByProject(@Param("projectId") Long projectId);
}
