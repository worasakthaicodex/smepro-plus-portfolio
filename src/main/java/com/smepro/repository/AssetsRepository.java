package com.smepro.repository;

import java.util.List;

import com.smepro.entity.assets;
import com.smepro.entity.ps_contract;

public interface AssetsRepository extends CPlatformRepository<assets, Long> {

	assets findByAssetNameAndAssetType(String Asset_name ,String Asset_type);

	List<assets> findTop500ByOrderByIdDesc();
	
	
	
}
