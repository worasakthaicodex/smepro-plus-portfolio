package com.smepro.simplifyjava;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlServiceImpl implements SqlServices {

	@PersistenceContext
	private EntityManager entityManager;

   @Override
    public <T> List<T> getCustomSql(Class<T> type,String sql, int limit) {

        sql += " LIMIT " + limit;

        // สร้าง query object ด้วย EntityManager
       // Query query = entityManager.createQuery(sql, type);
        Query query = entityManager.createNativeQuery(sql, type);
        

       System.out.println("sql "+sql);

        // กำหนดค่า parameter
        //query.setParameter("criteria", criteria);

        List<T> resultList = query.getResultList();
        return resultList;
    }

}
