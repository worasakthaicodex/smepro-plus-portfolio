package com.smepro.repository;



import java.util.List;

import com.smepro.entity.ReportJoinConfig;



public interface ReportJoinConfigRepository extends CPlatformRepository<ReportJoinConfig, Long> {

	List<ReportJoinConfig> findByReportSettingId(long id);
	long countByReportSettingId(long id);

	
}
