package com.smepro.repository;

import com.smepro.entity.ProjectResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectResourceRepository extends JpaRepository<ProjectResource, Long> {
    List<ProjectResource> findByProjectIdOrderByResourceNameAsc(Long projectId);
    List<ProjectResource> findByProjectIdAndResourceType(Long projectId, String resourceType);
}
