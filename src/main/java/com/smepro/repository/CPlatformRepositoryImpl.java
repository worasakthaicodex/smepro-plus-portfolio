package com.smepro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;

import com.smepro.entity.sales_followup;
import com.smepro.models.edata;

public class CPlatformRepositoryImpl<T> implements CPlatformRepositoryCustom<T> {

    @PersistenceContext
    private EntityManager em;

    private final Class<T> domainClass;

    public CPlatformRepositoryImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    
    
    @Override
    public List<T> findAllByEdataId(String edataId) {
    	
        String jpql = "SELECT t FROM " + domainClass.getSimpleName() + " t WHERE t.edataId = :edata_id";
        return em.createQuery(jpql, domainClass)
                 .setParameter("edata_id", edataId)
                 .getResultList();
        
    }
    @Override
    public List<T> findAllByTempId(String tempId) {
    	
        String jpql = "SELECT t FROM " + domainClass.getSimpleName() + " t WHERE t.tempId = :tempId";
        return em.createQuery(jpql, domainClass)
                 .setParameter("tempId", tempId)
                 .getResultList();
        
    }
    
    @Override
    public T findByEdataId(int edataId) {
        String jpql = "SELECT t FROM " + domainClass.getSimpleName() + " t WHERE t.edataId = :edata_id";
        try {
            return em.createQuery(jpql, domainClass)
                     .setParameter("edata_id", edataId)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;  // ถ้าไม่เจอผลลัพธ์
        }
    }



	@Override
	public T findById(int id) {
	    String jpql = "SELECT t FROM " + domainClass.getSimpleName() + " t WHERE t.Id = :id";
        try {
            return em.createQuery(jpql, domainClass)
                     .setParameter("id", id)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;  // ถ้าไม่เจอผลลัพธ์
        }
	}

    
}
