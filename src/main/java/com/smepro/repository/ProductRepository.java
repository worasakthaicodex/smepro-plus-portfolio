package com.smepro.repository;

import com.smepro.entity.Register_list;
import com.smepro.entity.Register_product;
import com.smepro.entity.Register_stock;
import com.smepro.entity.assets;
import com.smepro.entity.ps_contract;

public interface ProductRepository extends CPlatformRepository<Register_product, Long> {

	Register_product findByProductNamesAndProductCategory(String product_names, String product_category);


	
}
