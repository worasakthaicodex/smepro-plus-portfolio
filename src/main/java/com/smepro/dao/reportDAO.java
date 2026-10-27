package com.smepro.dao;

import java.util.List;

import com.smepro.models.BuildIn;
import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.FilterChain;
import com.smepro.models.FilterCriteria;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Measurement_values;
import com.smepro.models.PlatformReport;
import com.smepro.models.PrepareReport;
import com.smepro.models.QuickQuery;
import com.smepro.models.Segmentation;
import com.smepro.models.TabManagement;
import com.smepro.models.cm;
import com.smepro.models.report;


public interface reportDAO {
	List<report> getall(int idrefer);
	List<report> getalls(int idrefer);
	int numfors(String fors,int id);
	int delete(int id);
	int save(report cms);
	int update(report reports);
	report getbyid(int id);
	report getbyidrefer(int id);
	int setStatus(int report_id);
	
	
	
	
	PrepareReport getPrepare(PlatformReport Report);
	PrepareReport getPrepareReportById(int id);
	List<FilterChain> getPrepareQueryChain(PrepareReport config);
	void saveQueryChain(FilterChain chain);
	void saveFilterCriteria(FilterCriteria Filter);
	FilterCriteria getFilter_criteria(int id);
	FilterChain getPrepareQueryChainById(int id);
	
	
	void deleteQueryChain(String chain);
	void setEnabledActiveQueryChain(int id);
	void saveQuickQuery(QuickQuery quickQuery);
	List<QuickQuery> getQuickQuery(int id);
	
	
	void saveSyncBackSegmentation(Segmentation Segmentations);
	void UseSegmentation(String segmentation_profile_static_snapshot,int edata_id, String type);
	List<Measurement_rules> getMeasurement_rules(int data_id);
	int saveMeasurement_values(Measurement_values value);
	Measurement_values getMeasurement_values(int id, int data_id);

	
	void saveTabManagement(TabManagement tab);
	TabManagement getTabManagement(int id);
	void deleteTabManagement(int id);
	void saveBuildIn(BuildIn buildIn);
	void deleteBuildIn(int id);
	BuildIn getBuildIn(int id);
	List<Measurement_rules> getMeasurement_rules();
	BuildIn getBuildInByPprocessIdAndManagementId(int processId, int management_id);
	
	
	EdataReportbuildIn getedataReportBuildin(int id_refer, int id);
	void saveEdataReportbuildIn(EdataReportbuildIn report);
	void updatePropertyReport(PrepareReport prepareReport);
	List<BuildIn> getAllBuildInByPprocessIdAndManagementId(int processId);
	void doUnSyncBackSegmentation(Segmentation segmentation);
	void copySegmentation(Segmentation segmentation);
	TabManagement getTabManagementChange_closing(int id);
	

	
	
}
