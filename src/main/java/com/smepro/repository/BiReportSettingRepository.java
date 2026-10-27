package com.smepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smepro.entity.BiReportSetting;

public interface BiReportSettingRepository  extends JpaRepository<BiReportSetting, Long>{
	List<BiReportSetting> findTop10ByCategoryOrderByIdDesc(String category);
}
