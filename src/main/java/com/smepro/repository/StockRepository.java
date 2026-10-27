package com.smepro.repository;

import com.smepro.entity.Register_stock;
import com.smepro.entity.assets;
import com.smepro.entity.ps_contract;

public interface StockRepository extends CPlatformRepository<Register_stock, Long> {

	Register_stock findByStockListAndStockType(String stock_list, String stock_type);


	
}
