package com.smepro.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CPlatformPartialRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, CPlatformPartialRepositoryCustom<T> {

    <S extends T> S save(S entity);
    <S extends T> List<S> saveAll(Iterable<S> entities);
	void delete(T entity);
}
