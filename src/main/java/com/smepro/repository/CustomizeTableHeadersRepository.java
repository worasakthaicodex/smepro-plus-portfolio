package com.smepro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smepro.entity.CustomizeTableHeaders;


public interface CustomizeTableHeadersRepository  extends JpaRepository<CustomizeTableHeaders, Long>{

	Optional<CustomizeTableHeaders> findById(int id);


}
