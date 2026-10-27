package com.smepro.repository;

import java.util.List;

import com.smepro.entity.company_annual_plan;
import com.smepro.entity.deduction_record;

public interface Company_annual_planRepository extends CPlatformRepository<company_annual_plan, Long>{

	company_annual_plan findByTitleAndAnnualType(String title, String annual_type);

	List<company_annual_plan> findTop500ByOrderByIdDesc();

	List<company_annual_plan> findAllByTempIdAndTotalJobsIsNotNull(int edataId);

	List<company_annual_plan> findAllByTempIdAndMaxPersonnelIsNotNull(int edataId);


	List<company_annual_plan> findAllByTempIdAndProductionTargetUnitsIsNotNull(int edataId);

	List<company_annual_plan> findAllByTempIdAndMachineryIdIsNotNull(int edataId);

	List<company_annual_plan> findAllByTempIdAndGeneralTargetIsNotNull(int edataId);

	List<company_annual_plan> findAllByTempIdAndAnnualBudgetIsNotNull(int edataId);

}
