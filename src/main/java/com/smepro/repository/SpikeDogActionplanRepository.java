package com.smepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smepro.entity.SpikeDogActionplan;

@Repository
public interface SpikeDogActionplanRepository extends JpaRepository<SpikeDogActionplan, Integer> {

	List<SpikeDogActionplan> findByIdreport(Integer idreport);
	
	// ✅ เปลี่ยนชื่อเป็น OrderByIdDesc เพื่อเรียงลำดับ ID จากมากไปน้อย (ใหม่สุดขึ้นก่อน)
    List<SpikeDogActionplan> findByIdreportOrderByIdDesc(Integer idreport);
    
    
}