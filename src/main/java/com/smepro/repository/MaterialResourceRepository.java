package com.smepro.repository;

import com.smepro.entity.MaterialResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaterialResourceRepository extends JpaRepository<MaterialResource, Long> {
    List<MaterialResource> findByProjectIdOrderByMaterialNameAsc(Long projectId);
    List<MaterialResource> findByWbsElementIdOrderByMaterialNameAsc(Long wbsId);
    List<MaterialResource> findByProjectIdAndCategory(Long projectId, String category);
}
