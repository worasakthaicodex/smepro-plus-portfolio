
package com.smepro.repository;

import com.smepro.entity.ProjectClosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectClosureRepository extends JpaRepository<ProjectClosure, Long> {
    Optional<ProjectClosure> findByProjectId(Long projectId);
}
