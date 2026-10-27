package com.smepro.repository;

import com.smepro.entity.Register_list;
import com.smepro.entity.Register_stock;
import com.smepro.entity.assets;
import com.smepro.entity.ps_contract;

public interface RegisterRepository extends CPlatformRepository<Register_list, Long> {

	Register_list findByRegisterNameAndRegisterType(String register_name, String register_type);

}
