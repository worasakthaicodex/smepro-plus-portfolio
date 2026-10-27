package com.smepro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;

import com.smepro.entity.sales_followup;

public class Sales_followupRepositoryImpl implements SalesFollowupCopyRepository {

    @PersistenceContext
    private EntityManager em;


	@Override
	public void copySalesFollowupHeader(int oldEdataId, int newEdataId) {
	    // 1. ค้นหาแบบปลอดภัยป้องกัน Exception
	    List<sales_followup> results = em.createQuery(
	        "SELECT s FROM sales_followup s WHERE s.edataId = :oldId AND s.tempId = '0'", 
	        sales_followup.class)
	        .setParameter("oldId", oldEdataId)
	        .getResultList();

	    if (!results.isEmpty()) {
	        sales_followup oldHeader = results.get(0);
	        sales_followup newHeader = new sales_followup();
	        
	        // ก๊อปปี้ข้อมูล ยกเว้น ID และฟิลด์ที่ต้องเริ่มต้นใหม่ (เช่น status, dates)
	        BeanUtils.copyProperties(oldHeader, newHeader, "id", "createdDate", "updatedDate"); 

	        newHeader.setEdataId(newEdataId);
	        newHeader.setTempId("0");
	        
	        // สามารถตั้งค่าเริ่มต้นใหม่ที่นี่ได้ เช่น
	        // newHeader.setApproveStatus("PENDING");

	        em.persist(newHeader);
	    }
	}

	
	
	@Override
	public void copySalesFollowupChildren(int oldEdataId, int newEdataId) {
	    List<sales_followup> oldChildren = em.createQuery(
	        "SELECT s FROM sales_followup s WHERE s.tempId = :oldIdStr", 
	        sales_followup.class)
	        .setParameter("oldIdStr", String.valueOf(oldEdataId))
	        .getResultList();

	    int batchSize = 0;
	    for (sales_followup oldChild : oldChildren) {
	        sales_followup newChild = new sales_followup();
	        BeanUtils.copyProperties(oldChild, newChild, "id"); 

	        newChild.setTempId(String.valueOf(newEdataId));
	        newChild.setEdataId(0); 

	        em.persist(newChild);
	        
	        // ช่วย JPA บริหารจัดการ Memory (ถ้าข้อมูลเยอะ)
	        if (++batchSize % 20 == 0) {
	            em.flush();
	            em.clear();
	        }
	    }
	}
  
}
