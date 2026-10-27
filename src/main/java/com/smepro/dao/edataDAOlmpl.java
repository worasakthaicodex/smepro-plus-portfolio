package com.smepro.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.sql.DataSource;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smepro.dto.edataDTO;
import com.smepro.dto.inventoryDTO;
import com.smepro.dto.materialDTO;
import com.smepro.dto.productionDTO;
import com.smepro.dto.search_workflowDTO;
import com.smepro.entity.HelpDeskTicket;
import com.smepro.entity.edata_annual_plan;
import com.smepro.entity.edata_assets;
import com.smepro.entity.edata_workrule;
import com.smepro.enums.RegisterActiontype;
import com.smepro.enums.Stock_status;
import com.smepro.models.Adjustment_fg;
import com.smepro.models.Adjustment_sk;
import com.smepro.models.Asset_log;
import com.smepro.models.Audit;
import com.smepro.models.BatchEdata;
import com.smepro.models.Calendar;
import com.smepro.models.DocumentEffect;
import com.smepro.models.EdataAppManagement;
import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.Edata_maindata;
import com.smepro.models.Extension;
import com.smepro.models.ExtensionParameter;
import com.smepro.models.Fg;
import com.smepro.models.FilterChain;
import com.smepro.models.FormPrint;
import com.smepro.models.FullResult;
import com.smepro.models.Goal;
import com.smepro.models.Goods_receipt_fg;
import com.smepro.models.Goods_receipt_sk;
import com.smepro.models.Group_label;
import com.smepro.models.InputHiddenVisibility;
import com.smepro.models.Inventory_transfer_fg;
import com.smepro.models.Inventory_transfer_sk;
import com.smepro.models.Issue_fg;
import com.smepro.models.Issue_sk;
import com.smepro.models.LoadApp;
import com.smepro.models.Looselink;
import com.smepro.models.Management;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Myapp;
import com.smepro.models.Platform;
import com.smepro.models.Relations;
import com.smepro.models.Segmentation;
import com.smepro.models.SetSegmentView;
import com.smepro.models.SubData;
import com.smepro.models.TabManagement;
import com.smepro.models.Tabform;
import com.smepro.models.UseExtension;
import com.smepro.models.UseformPrint;
import com.smepro.models.bom;
import com.smepro.models.customreport;
import com.smepro.models.department;
import com.smepro.models.doc;
import com.smepro.models.edata;
import com.smepro.models.edata_procurement;
import com.smepro.models.edata_product;
import com.smepro.models.edata_production_record;
import com.smepro.models.edata_register;
import com.smepro.models.edata_register_processing;
import com.smepro.models.edata_stock;
import com.smepro.models.edatas;
import com.smepro.models.eformfield;
import com.smepro.models.fieldindex;
import com.smepro.models.group_members;
import com.smepro.models.groups;
import com.smepro.models.keymap;
import com.smepro.models.keymap2;
import com.smepro.models.levenshtein;
import com.smepro.models.other;
import com.smepro.models.policies;
import com.smepro.models.process;
import com.smepro.models.product_log;
import com.smepro.models.product_master;
import com.smepro.models.production_directive;
import com.smepro.models.production_list;
import com.smepro.models.production_standard;
import com.smepro.models.purchase;
import com.smepro.models.qci;
import com.smepro.models.register_log;
import com.smepro.models.registerkey;
import com.smepro.models.registers;
import com.smepro.models.report_mapping;
import com.smepro.models.stock;
import com.smepro.models.stock_log;
import com.smepro.models.stock_lot;
import com.smepro.models.sub_processes;
import com.smepro.models.transaction_send;
import com.smepro.models.upload_pn;
import com.smepro.models.user;
import com.smepro.repository.Edata_annual_planRepository;
import com.smepro.repository.Edata_assetsRepository;
import com.smepro.repository.HelpDeskRepository;
import com.smepro.rowmapper.Adjustment_fgmappers;
import com.smepro.rowmapper.Adjustment_skmappers;
import com.smepro.rowmapper.Asset_logmapper;
import com.smepro.rowmapper.Auditmappers;
import com.smepro.rowmapper.DocumentEffectmappers;
import com.smepro.rowmapper.EdataAppManagementmapper;
import com.smepro.rowmapper.EdataReportbuildInmapper;
import com.smepro.rowmapper.Edata_maindatamapper;
import com.smepro.rowmapper.ExtensionParametermapper;
import com.smepro.rowmapper.Extensionmapper;
import com.smepro.rowmapper.FormPrintmapper;
import com.smepro.rowmapper.Goods_receipt_fgmappers;
import com.smepro.rowmapper.Goods_receipt_skmappers;
import com.smepro.rowmapper.Group_labelmapper;
import com.smepro.rowmapper.InputHiddenVisibilitymmapper;
import com.smepro.rowmapper.Inventory_transfer_fgmappers;
import com.smepro.rowmapper.Inventory_transfer_skmappers;
import com.smepro.rowmapper.Issue_fgmappers;
import com.smepro.rowmapper.Issue_skmappers;
import com.smepro.rowmapper.Looselinkmapper;
import com.smepro.rowmapper.ManagementParametermapper;
import com.smepro.rowmapper.Measurement_rulesmappers;
import com.smepro.rowmapper.Relationsmapper;
import com.smepro.rowmapper.SegmentationManagementmapper;
import com.smepro.rowmapper.SetSegmentViewmapper;
import com.smepro.rowmapper.TabManagementmapper;
import com.smepro.rowmapper.Tabformmapper;
import com.smepro.rowmapper.UseExtensionmapper;
import com.smepro.rowmapper.UseformPrintmapper;
import com.smepro.rowmapper.appmapper;
import com.smepro.rowmapper.appmappers;
import com.smepro.rowmapper.bommappers;
import com.smepro.rowmapper.data_stockmappers;
import com.smepro.rowmapper.docmappers;
import com.smepro.rowmapper.edata_logmappers;
import com.smepro.rowmapper.edata_procurementmappers;
import com.smepro.rowmapper.edata_production_recordmappers;
import com.smepro.rowmapper.edata_production_recordmappers2;
import com.smepro.rowmapper.edata_production_recordmappers3;
import com.smepro.rowmapper.edata_productmappers;
import com.smepro.rowmapper.edata_register_processingmappers;
import com.smepro.rowmapper.edata_registermappers;
import com.smepro.rowmapper.edatamapper;
import com.smepro.rowmapper.edatamapper2;
import com.smepro.rowmapper.edatamapper3;
import com.smepro.rowmapper.edatamapper4;
import com.smepro.rowmapper.edatamappers;
import com.smepro.rowmapper.edatamappers2;
import com.smepro.rowmapper.fgmappers;
import com.smepro.rowmapper.inventoryFGDTOmapper;
import com.smepro.rowmapper.keymapmapper;
import com.smepro.rowmapper.materialDTOmapper;
import com.smepro.rowmapper.myappmapper;
import com.smepro.rowmapper.othermappers;
import com.smepro.rowmapper.othermappers2;
import com.smepro.rowmapper.policiesmapper;
import com.smepro.rowmapper.processmapper;
import com.smepro.rowmapper.product_logmapper;
import com.smepro.rowmapper.product_mastermappers;
import com.smepro.rowmapper.productionDTOmapper;
import com.smepro.rowmapper.production_directivemappers;
import com.smepro.rowmapper.production_listmappers;
import com.smepro.rowmapper.production_standardmappers;
import com.smepro.rowmapper.purchasemappers;
import com.smepro.rowmapper.qcimappers;
import com.smepro.rowmapper.register_logmapper;
import com.smepro.rowmapper.registerkeymapper;
import com.smepro.rowmapper.registersmapper;
import com.smepro.rowmapper.stock_logmapper;
import com.smepro.rowmapper.stock_lotmappers;
import com.smepro.rowmapper.stockmappers;
import com.smepro.rowmapper.sub_processesmapper;
import com.smepro.rowmapper.subdatamappers;
import com.smepro.rowmapper.traceabilityedata_fgmapper;
import com.smepro.rowmapper.traceabilityedata_skmapper;
import com.smepro.rowmapper.traceabilityedatamapper;
import com.smepro.rowmapper.transaction_sendmapper;
import com.smepro.rowmapper.upload_pnmapper;
import com.smepro.service.otherservice;
import com.smepro.service.sqlservice;
import com.smepro.service.userservice;



public class edataDAOlmpl implements edataDAO {

	private JdbcTemplate jdbcTemplate;
	private final Semaphore idLock = new Semaphore(1);
	private static int Transaction = 0;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
		
		
	 @Autowired
	 sqlservice Sqlservice;
	 
	 @Autowired
	 dataset_dataDAO Dataset_dataDAO;
	 
	 @Autowired
	 otherservice Otherservice;
	 
	 @Autowired
	 HelpDeskRepository helpDeskRepository;
	 
	 @Autowired
	 userservice Userservice;
	 
	 @Autowired
	 userDAO UserDAO;
	 

	public edataDAOlmpl(DataSource dataSoruce) {
		this.jdbcTemplate = new JdbcTemplate(dataSoruce);
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<edata> getallTraceabilityStatus() {
		
		String sql = "SELECT * FROM edata WHERE data_status IS NOT NULL  group by data_eformid  order by data_id desc limit 10000";
		List<edata> edatas  =  jdbcTemplate.query(sql,new edatamappers());
		return edatas;
	}
	
	
	
	
	
	
	@Override
	public edata getStatus(int id,int rv,String pd,String batch) {
		
		
		  try {
				String sql = "SELECT * FROM edata WHERE  data_eformid =? "
						+ "and data_eformrv=? and production_directive=?   and batch=?  AND data_dateadd >= NOW() - INTERVAL 2 DAY   order by data_id desc limit 1";
				edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,pd,batch},new edatamappers());
				return edatas;
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
		
		
	
	}
	
	
	
	@Override
	public edata getStatus(int id, int rv, String dateTime) {
		
		System.out.println(" data *****"+id);
		System.out.println(" data *****"+rv);
		System.out.println(" data *****"+dateTime);

		  try {
			  
			  String date = dateTime.split(" ")[0];  
			  
				String sql = "SELECT * FROM edata WHERE  data_eformid =? "
						+ "and data_eformrv=? and DATE(data_dateadd) = ? AND data_dateadd >= NOW() - INTERVAL 2 DAY   order by data_id desc limit 1";
				edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,date},new edatamappers());
				return edatas;
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}

	}
	
	
	@Override
	public List<edata> getall_USER_GROUP(int plan_id,String USER_GROUP,int idadd) {
		
	
		if(USER_GROUP.equals("uploader")) {
			String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
					+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
					+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
					+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
					+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
					+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
					+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
					+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
					+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
					+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
			
			
			
			String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.*,idoc.* FROM edata "
					+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
					+ " LEFT JOIN file  ON file.file_edata = edata.data_id"
					+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
					+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
					+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
					+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
					+ " where  edata.data_planid = ? AND  edata.id_add =?  group by edata.data_id "
					+ " order by edata.data_flow asc limit 10000";
			List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{plan_id,idadd},new edatamappers());
			return edatas;
			
		}else {
			
			String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
					+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
					+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
					+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
					+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
					+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
					+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
					+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
					+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
					+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
			
			
			
			String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.*,idoc.* FROM edata "
					+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
					+ " LEFT JOIN file  ON file.file_edata = edata.data_id"
					+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
					+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
					+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
					+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
					+ " where  edata.data_planid = ?  group by edata.data_id "
					+ " order by edata.data_flow asc limit 10000";
			List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{plan_id},new edatamappers());
			return edatas;
		}
		

	}
	
	@Override
	public List<edata> getall(int plan_id) {
		
	
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.*,idoc.* FROM edata "
				+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
				+ " LEFT JOIN file  ON file.file_edata = edata.data_id"
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where  edata.data_planid = ?  group by edata.data_id "
				+ " order by edata.data_flow asc limit 10000";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{plan_id},new edatamappers());
		return edatas;
	}
	
	@Override
	public List<edata> getallCondition_id(int condition_id) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.*,idoc.* FROM edata "
				+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
				+ " LEFT JOIN file  ON file.file_edata = edata.data_id"
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where  edata.data_eformid = ?  group by edata.data_id "
				+ " order by edata.data_dateadd desc limit 50";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{condition_id},new edatamappers());
		return edatas;
	}
	
	
	
	@Override
	public List<edata> getall(long data_eformid,int data_eformrv) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.*,idoc.* FROM edata "
				+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
				+ " LEFT JOIN file  ON file.file_edata = edata.data_id"
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where edata.data_eformid = ? and edata.data_eformrv =?  group by edata.data_id  order by log.log_flow asc limit 10000";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamappers());
		return edatas;
	}
	
	
	
	@Override
	public List<edata> getallProcesses(long process_id) {
		
		String sql = "SELECT  idoc.*,edata.*" // ระบุเฉพาะที่ใช้
	           + ",doc.doc_rv,doc.doc_type,doc.doc_date,doc.doc_autoid "
	           + " FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  "
	           + " WHERE edata.process_id = ? "
	           + " ORDER BY edata.data_id DESC LIMIT 1000";
	
	List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
	return edatas;

	}
	
	
	@Override
	public List<edata> getallProcesses(long process_id, String column, String value) {

		
		System.out.println(" column "+column);
		System.out.println(" value "+value);
		
		
	    // 1. 🔥 เช็คก่อนเลย ถ้าข้อมูลไม่ครบ (ไม่มี column หรือ ไม่มี value)
	    // ให้ส่ง List ว่างกลับไปทันที ห้ามรัน SQL
	    if (column == null || column.trim().isEmpty() || value == null || value.trim().isEmpty()) {
	        return new ArrayList<edata>(); // ส่ง List เปล่ากลับไป
	    }

	    // 2. 🛡️ กัน SQL Injection (เช็คชื่อ column ว่าต้องเป็นตัวอักษร A-Z, 0-9 หรือ _ เท่านั้น)
	    // ถ้าชื่อ Column มีอักขระแปลกปลอม ก็ส่ง List ว่างกลับไปเหมือนกัน
	    if (!column.matches("^[a-zA-Z0-9_]+$")) {
	        return new ArrayList<edata>();
	    }

	    // ------------------------------------------------------------------
	    // 3. ถ้ามาถึงตรงนี้ แปลว่ามีครบทั้ง process_id, column และ value แน่นอน
	    // ------------------------------------------------------------------
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT idoc.* ");
	    sql.append(",edata.*  ");
	    sql.append(",doc.doc_rv, doc.doc_type, doc.doc_date, doc.doc_autoid ");
	    sql.append(" FROM idoc ");
	    sql.append(" LEFT JOIN edata ON edata.data_id = idoc.eform_id ");
	    sql.append(" LEFT JOIN doc ON doc.doc_idrefer = edata.data_id ");
	    
	    // ใส่เงื่อนไข process_id และ column ที่ส่งมาทันที (ไม่ต้อง if แล้ว เพราะเช็คไปแล้วด้านบน)
	    sql.append(" WHERE edata.process_id = ? ");
	    sql.append(" AND edata.").append(column).append(" = ? "); 
	    
	    sql.append(" ORDER BY edata.data_id DESC LIMIT 1000");

	    // 4. สร้าง Array พารามิเตอร์ (มี 2 ตัวแน่นอน คือ process_id กับ value)
	    Object[] params = new Object[] { process_id, value };

	    // 5. ยิง Query
	    List<edata> edatas = jdbcTemplate.query(sql.toString(), params, new appmappers());
	    
	    return edatas;
	}
	
	
	
	
	
	@Override
	public List<edata> getallProcessesDraft(long process_id,int user_id) {
		
		String sql = "SELECT  idoc.* "
				+ ",edata.data_id, edata.data_title, edata.acknowledge,edata.data_date_receiver" // ระบุเฉพาะที่ใช้
	           + ",doc.doc_rv,doc.doc_type,doc.doc_date,doc.doc_autoid ,doc.doc_id "
	           + " FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  "
	           + " WHERE   edata.process_id = ? AND  idoc.receiver =?   "
	           + "AND ( idoc.current_step != 'Completion'   AND  edata.acknowledge = 1 OR idoc.current_step = 'Create'  AND idoc.request_to_edit = 0)    "
	           + " ORDER BY edata.data_id DESC LIMIT 1000";
	
	List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id,user_id},new appmappers());
	return edatas;

	}
	
	@Override
	public List<edata> getallProcessesReviewed(long process_id,int user_id) {
		
		String sql = "SELECT idoc.* "
		        + ", edata.data_id, edata.data_title, edata.acknowledge, edata.data_date_receiver "
		        + ", doc.doc_rv, doc.doc_type, doc.doc_date, doc.doc_autoid, doc.doc_id "
		        + " FROM idoc "
		        + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
		        + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id "
		        + " WHERE  "
		        + "  edata.process_id = ? "
		        + " AND idoc.receiver = ? "
		        + " AND edata.acknowledge = 0 "
		        + " AND ( "
		        + "    (idoc.current_step NOT IN ('Create', 'Completion')) " // เคส 1: งานส่งต่อมาจากคนอื่น
		        + "    OR "
		        + "    (idoc.current_step = 'Create' AND idoc.request_to_edit = 1) " // เคส 2: งานตีกลับมาให้เราแก้
		        + " ) "
		        + " ORDER BY edata.data_id DESC LIMIT 1000";

		List<edata> edatas = jdbcTemplate.query(sql, new Object[]{process_id, user_id}, new appmappers());
		return edatas;

	}
	
	@Override
	public int  countReviewed(long process_id,int user_id) {
		
		String sql = "SELECT COUNT(*) FROM idoc "
		        + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
		        + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id "
		        + " WHERE  "
		        + "  edata.process_id = ? "
		        + " AND idoc.receiver = ? "
		        + " AND edata.acknowledge = 0 "
		        + " AND ( "
		        + "    (idoc.current_step NOT IN ('Create', 'Completion')) " // งานที่ส่งมาจากคนอื่น
		        + "    OR "
		        + "    (idoc.current_step = 'Create' AND idoc.request_to_edit = 1) " // งานที่ถูกส่งกลับมาให้แก้
		        + " ) ";

		return jdbcTemplate.queryForObject(sql, new Object[]{process_id, user_id}, Integer.class);
	}
	
	
	
	@Override
	public List<edata> getallProcessesPublished(long process_id,int user_id) {
		
		String sql = "SELECT  idoc.* "
				+ ",edata.data_id, edata.data_title, edata.acknowledge,edata.data_date_receiver" // ระบุเฉพาะที่ใช้
	           + ",doc.doc_rv,doc.doc_type,doc.doc_date,doc.doc_autoid ,doc.doc_id "
	           + " FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  "
	           + " WHERE edata.process_id = ?  AND  find_in_set(?,idoc.refer_user )   AND  idoc.current_step = 'Completion' "
	           + " ORDER BY edata.data_id DESC LIMIT 1000";
	
	List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id,user_id},new appmappers());
	return edatas;

	}
	
	@Override
	public List<edata> 	getallProcessesRefered(long process_id,int user_id) {
		
		String sql = "SELECT  idoc.* "
				+ ",edata.data_id, edata.data_title,edata.acknowledge,edata.data_date_receiver" // ระบุเฉพาะที่ใช้
	           + ",doc.doc_rv,doc.doc_type,doc.doc_date,doc.doc_autoid ,doc.doc_id "
	           + " FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  "
	           + " WHERE edata.process_id = ?  AND  find_in_set(?,idoc.refer_user )   AND  idoc.current_step != 'Completion' "
	           + " ORDER BY edata.data_id DESC LIMIT 1000";
	
	List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id,user_id},new appmappers());
	return edatas;

	}
	
	
	
	
	
	
	@Override
	public List<edata> getallProcessesSends(long process_id,int user_id) {
		process process  = getProcess((int)process_id);
		String sql = "SELECT  idoc.* "
				+ ",edata.data_id, edata.data_title, edata.acknowledge,edata.data_date_receiver" 
	           + ",doc.doc_rv,doc.doc_type,doc.doc_date,doc.doc_autoid,doc.doc_id "
	           + " FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  "
	           + " WHERE edata.process_id = ? " // ล็อค Process ให้อยู่หมัด
               // 👇 ใส่วงเล็บครอบเงื่อนไขพฤติกรรมทั้งหมดไว้
	           + " AND ( " 
	           + "      (idoc.sender = ? AND idoc.receiver != ? AND idoc.current_step != 'Completion' AND  edata.acknowledge != 1  ) "
	           + "      OR "
	           + "      (find_in_set(?, idoc.refer_user) AND idoc.current_step = 'Completion' AND FIND_IN_SET(idoc.closing_status, ?) AND NOT FIND_IN_SET(idoc.closing_status, ?)) "
	           + " ) " 
	           + " ORDER BY edata.data_id DESC LIMIT 1000";
	
	List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{
			process_id
			,user_id
			,user_id
			,user_id
			,process.getClosing_status()
			,process.getClosing_status_notsend()
			},new appmappers());
	return edatas;
	}
	
	
	
	@Override
	public int countSends(long process_id,int user_id) {
		
		process process  = getProcess((int)process_id);
		
		String sql = "SELECT count(*) FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  "
	           + " WHERE edata.process_id = ? " // ล็อค Process ให้อยู่หมัดเหมือนเดิม
               // 👇 ใส่วงเล็บครอบเงื่อนไข AND/OR ไว้ด้วยกัน
	           + " AND  ( "
	           + "      (idoc.sender = ? AND idoc.receiver != ? AND idoc.current_step != 'Completion'  AND  edata.acknowledge != 1 ) "
	           + "      OR "
	           + "      (find_in_set(?, idoc.refer_user) AND idoc.current_step = 'Completion' AND FIND_IN_SET(idoc.closing_status, ?) AND NOT FIND_IN_SET(idoc.closing_status, ?)) "
	           + " ) ";
	
		return jdbcTemplate.queryForObject(sql,new Object[]{
				process_id
				,user_id
				,user_id
				,user_id
				,process.getClosing_status()
				,process.getClosing_status_notsend()
		},Integer.class);
	}
	
	@Override
	public int  countDraft(long process_id,int user_id) {
		
		String sql = "SELECT  count(*)"
	           + " FROM idoc "
	           + " LEFT JOIN edata ON edata.data_id = idoc.eform_id "
	           + " WHERE    edata.process_id = ? AND  idoc.receiver =?   AND ( idoc.current_step != 'Completion'   AND  edata.acknowledge = 1 "
	           + "OR idoc.current_step = 'Create' AND idoc.request_to_edit = 0 )   ";
	
		return  jdbcTemplate.queryForObject(sql,new Object[]{process_id,user_id},Integer.class);


	}
	
	

	
    

	
	@Override
	public List<edata> getallProcesses(int process_id,String parentId,int limit) {
		
		

			String sql = "SELECT edata.*,idoc.* FROM edata "
					+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
					+ " LEFT JOIN process ON edata.process_id = process.id"
					+ " where edata.process_id = ? AND FIND_IN_SET(edata.reference,?)   order by edata.data_id desc limit ?";
			List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id,parentId,limit},new appmappers());
			return edatas;
	

	
	}
	
	
	
	
	
	
	@Override
	public List<edata> getallProcessesRelation(int process_id,String tag) {
		
		System.out.println(" process_id "+process_id);
		System.out.println(" tag "+tag);
		
		
			String sql = "SELECT edata.*,idoc.*,doc.* FROM idoc "
					+ " LEFT JOIN edata ON edata.data_id = idoc.eform_id"
					+ " LEFT JOIN doc ON edata.data_id = doc.doc_idrefer"
					+ " LEFT JOIN process ON edata.process_id = process.id"
					+ " where edata.process_id = ? AND FIND_IN_SET(idoc.closing_status,?)  limit 500 ";
			
			
			
			List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id,tag},new appmapper());
			return edatas;
	
	}
	
	@Override
	public List<edata> getallProcessesRelation(int process_id,String tag,user user) {
		

		System.out.println(" idoc.closing_status "+tag);
		
		
		String userAuths = user.getAuth(); 
		if (userAuths == null || userAuths.isEmpty()) {
		    userAuths = "IMPOSSIBLE_VALUE"; 
		}
		String regexPattern = userAuths.replace(",", "|");

			String sql = "SELECT "
					+ "idoc.*"
					+ ",edata.data_id,edata.data_title,edata.acknowledge"
					+ ",doc.* FROM idoc "
					+ " LEFT JOIN edata ON edata.data_id = idoc.eform_id"
					+ " LEFT JOIN doc ON edata.data_id = doc.doc_idrefer"
					+ " LEFT JOIN process ON edata.process_id = process.id"
					+ " where edata.process_id = ? AND FIND_IN_SET(idoc.closing_status,?) "
					+ "AND  ( idoc.cleared REGEXP ? OR idoc.cleared ='-' )   limit 500 ";
			
			
			List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{process_id,tag,regexPattern},new appmapper());
			return edatas;
	}
	

	@Override
	public List<transaction_send> getAllTransaction_send(int process_id,String parentId,int limit) {
		
		String sql = "SELECT * FROM transaction_send "
				+ " LEFT JOIN process ON transaction_send.process_id = process.id"
				+ " LEFT JOIN edata ON edata.process_id = process.id"
				+ " where transaction_send.process_id = ?  AND FIND_IN_SET(edata.reference,?)   order by transaction_send.id desc limit ?";
		List<transaction_send> data  =  jdbcTemplate.query(sql,new Object[]{process_id,parentId,limit},new transaction_sendmapper());
		return data;
		
	}
	
		@Override
		public List<transaction_send> getAllTransaction_send(int process_id,int limit) {
			
			String sql = "SELECT * FROM transaction_send "
					+ " LEFT JOIN process ON transaction_send.process_id = process.id"
					+ " where transaction_send.process_id = ?  order by transaction_send.id desc limit ?";
			List<transaction_send> data  =  jdbcTemplate.query(sql,new Object[]{process_id,limit},new transaction_sendmapper());
			return data;
			
		}
		
	
	
	
	@Override
	public List<edata> getallworkflow(int workflow_id) {
		
		String sql = "SELECT edata.*,idoc.*,process.name  FROM edata "
				+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
				+ " LEFT JOIN process ON edata.process_id = process.id"
				+ " where process.id_refer = ? AND idoc.creator > 0 order by edata.data_id desc limit 1000";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{workflow_id},new appmappers());
		return edatas;
	}
	
	
	
	@Override
	public List<edata> getallexample(long data_eformid,int data_eformrv) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.*,idoc.* FROM edata "
				+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
				+ " LEFT JOIN file  ON file.file_edata = edata.data_id"
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where edata.data_eformid = ? and edata.data_eformrv =?  group by edata.data_id  order by edata.data_due desc limit 50";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamappers());
		return edatas;
	}
	
	
	@Override
	public List<edata> getallgroupby(long data_eformid,int data_eformrv,String g1,String g2) {
		
	
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where edata.data_eformid = ? and edata.data_eformrv =?  group by edata.data_id,"+g1+","+g2+"  order by log.log_flow asc limit 10000";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamapper3());
		return edatas;
	}
	
	
	
	
	@Override
	public int CountandGroup(long data_eformid,int data_eformrv,String g1,String g2,String date) {
			String sql = "SELECT COUNT(*) AS data_value1,edata.* FROM edata WHERE data_eformid = ? and data_eformrv =? AND data_due =?  group by data_id,data_due,"+g1+","+g2+"  ";
			List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv,date},new edatamappers());
			 
			System.out.println("edatas "+edatas);
			int totalCount = edatas.stream()
		                .mapToInt(e -> {
		                    try {
		                        return Integer.parseInt(e.getData_value1()); 
		                    } catch (NumberFormatException ex) {
		                        return 0;
		                    }
		                })
		                .sum();
			  return totalCount; 
	}
	
	@Override
	public productionDTO Summary_production(long data_eformid,int data_eformrv
			,int Good,int Defective
			,String date) {
		
		   String SELECT = " ";
		   
		  if(Good > 0) {
			  SELECT = SELECT+ " SUM(data_value"+Good+") AS good_production_output1 ";
		   }else {
			   SELECT = SELECT+ " 0 AS good_production_output1 ";
		   }
		   if(Defective > 0) {
			   SELECT = SELECT+ ",SUM(data_value"+Defective+") AS defective_production_output1, ";
		   }else {
			   SELECT = SELECT+ ",0 AS defective_production_output1 ";
		   }

		   
		   
			String sql = "SELECT "+SELECT+" FROM edata WHERE data_eformid = ? and data_eformrv =? AND data_due =?   ";
			productionDTO edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{data_eformid,data_eformrv,date},new productionDTOmapper());
			 
			  return edatas; 
	}

	

	
	
	
	
	@Override
	public List<edata> production(long data_eformid,int data_eformrv,search_workflowDTO search) {
		
			List<edata> edatas  =new ArrayList<>();
		  StringBuilder queryBuilder = new StringBuilder();
		  queryBuilder.append("SELECT * FROM ").append(" edata ").append(" WHERE ").append(" data_eformid = ? and data_eformrv =?  ");

		   
		   int search_status = 0;
		   
		   if(search != null) {
			   
			   
				 if(!search.getSearch_product().equals("-")) {
					  if(!search.getSearch_product().equals("-") && !search.getSearch_product_category().equals("-")) {
						  
						  queryBuilder.append(" AND ").append("product").append(" = ").append(" '").append(search.getSearch_product()).append("' "); 
						  queryBuilder.append(" AND ").append("product_category").append(" = ").append(" '").append(search.getSearch_product_category()).append("' "); 
						  
					  }
					  else {
						  queryBuilder.append(" AND ").append("product").append(" = ").append(" '").append(search.getSearch_product()).append("' "); 
						 
					  }
					  search_status = 1;
				}
				
				 if(!search.getSearch_pd().equals("-")) {
					 queryBuilder.append(" AND find_in_set(").append("production_directive").append(",").append("'").append(search.getSearch_pd()).append("')"); 
					  search_status = 1;
				 }
				 
				if(!search.getSearch_batch().equals("-")) {			
					  queryBuilder.append(" AND ").append("batch").append(" = ").append(" '").append(search.getSearch_batch()).append("' "); 
					  search_status = 1;
				}
				
	
		
				
				System.out.println(" Search_date "+search.getSearch_date()); 
				System.out.println(" Search_date_end "+search.getSearch_date_end()); 
				
				if(search.getSearch_date() != null   && !search.getSearch_date().isBlank()  ) {
					queryBuilder.append(" AND ").append("data_due").append(" >= ").append(" '").append(search.getSearch_date()).append("' ");
					  search_status = 1;
					
				}
				
				if(search.getSearch_date_end() != null && !search.getSearch_date_end().isBlank()  ) {
						queryBuilder.append(" AND ").append("data_due").append(" <= ").append(" '").append(search.getSearch_date_end()).append("' ");
						  search_status = 1;
					}else if(search.getSearch_date_end() != null && !search.getSearch_date_end().isBlank()  ) {
							queryBuilder.append(" AND ").append("data_due").append(" <= ").append(" '").append(search.getSearch_batch_material()).append("' ");
							  search_status = 1;
					}
				
			}
			
		   if(search_status ==  0) {
				 return edatas; 
		   }
		   
		   
		   queryBuilder.append(" ORDER BY data_id LIMIT  "+search.getLimit());
				
				
			String sql = queryBuilder.toString();
			
			System.out.println(" sql "+sql +" "+data_eformid+" "+data_eformrv); 
			
			
			edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamappers());
			 
			return edatas; 
	}

	

	@Override
	public List<edata> material(long data_eformid,int data_eformrv,search_workflowDTO search) {
		
			List<edata> edatas  =new ArrayList<>();
		  StringBuilder queryBuilder = new StringBuilder();
		  queryBuilder.append("SELECT * FROM ").append(" edata ").append(" WHERE ").append(" data_eformid = ? and data_eformrv =?  ");

		   
		   int search_status = 0;
		   
		   if(search != null) {
			   
			   
				 if(!search.getSearch_product().equals("-")) {
					  if(!search.getSearch_product().equals("-") && !search.getSearch_product_category().equals("-")) {
						  
						  queryBuilder.append(" AND ").append("product").append(" = ").append(" '").append(search.getSearch_product()).append("' "); 
						  queryBuilder.append(" AND ").append("product_category").append(" = ").append(" '").append(search.getSearch_product_category()).append("' "); 
						  
					  }
					  else {
						  queryBuilder.append(" AND ").append("product").append(" = ").append(" '").append(search.getSearch_product()).append("' "); 
						 
					  }
					  search_status = 1;
				}
				
				 if(!search.getSearch_pd().equals("-")) {
					  queryBuilder.append(" AND ").append("production_directive").append(" = ").append(" '").append(search.getSearch_pd()).append("' "); 
					  search_status = 1;
				 }
				 
				if(!search.getSearch_batch().equals("-")) {			
					  queryBuilder.append(" AND ").append("batch").append(" = ").append(" '").append(search.getSearch_batch()).append("' "); 
					  search_status = 1;
				}
				
				 if(!search.getSearch_material().equals("-")) {
					  queryBuilder.append(" AND ").append("material").append(" = ").append(" '").append(search.getSearch_material()).append("' "); 
					  search_status = 1;
				 }
				 
				if(!search.getSearch_batch_material().equals("-")) {			
					  queryBuilder.append(" AND ").append("batch_material").append(" = ").append(" '").append(search.getSearch_batch_material()).append("' "); 
					  search_status = 1;
				}
				
				
				
				
				System.out.println(" Search_date "+search.getSearch_date()); 
				System.out.println(" Search_date_end "+search.getSearch_date_end()); 
				
				if(search.getSearch_date() != null   && !search.getSearch_date().isBlank()  ) {
					queryBuilder.append(" AND ").append("data_due").append(" >= ").append(" '").append(search.getSearch_date()).append("' ");
					  search_status = 1;
					
				}
				
				if(search.getSearch_date_end() != null && !search.getSearch_date_end().isBlank()  ) {
						queryBuilder.append(" AND ").append("data_due").append(" <= ").append(" '").append(search.getSearch_date_end()).append("' ");
						  search_status = 1;
					}else if(search.getSearch_date_end() != null && !search.getSearch_date_end().isBlank()  ) {
							queryBuilder.append(" AND ").append("data_due").append(" <= ").append(" '").append(search.getSearch_batch_material()).append("' ");
							  search_status = 1;
					}
				
			}
			
		   if(search_status ==  0) {
				 return edatas; 
		   }
		   
		   
		   queryBuilder.append(" ORDER BY data_id LIMIT  "+search.getLimit());
				
				
			String sql = queryBuilder.toString();
			
			System.out.println(" sql "+sql); 
			
			
			edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamappers());
			 
			return edatas; 
	}

	
	
	
	@Override
	public List<materialDTO> usematerial(long data_eformid,int data_eformrv,fieldindex row) {
		
		
		  StringBuilder queryBuilder = new StringBuilder();
		   queryBuilder.append(" SELECT data_department AS department,data_due AS date,");
		   
		   
		   if(row.getIncoming_or_deliver() > 0) {
			   queryBuilder.append("SUM").append("(").append("data_value").append(row.getIncoming_or_deliver()).append(")").append(" AS incoming_or_deliver,");     
		   }else {
			   queryBuilder.append(" 0 ").append(" AS incoming_or_deliver,"); 
		   }

		   if(row.getLot() > 0) {
			   queryBuilder.append("data_value").append(row.getLot()).append(" AS lot,");
		   }else {
			   queryBuilder.append(" NULL ").append(" AS lot,"); 
		   }
		   
		   if(row.getProduct() > 0) {
			   queryBuilder.append("data_value").append(row.getProduct()).append(" AS product,");
		   }else {
			   queryBuilder.append(" NULL ").append(" AS product,"); 
		   }
		   
		   if(row.getIncoming_or_deliver() > 0) {
			   queryBuilder.append("SUM").append("(").append("data_value").append(row.getIncoming_or_deliver()).append(")").append(" AS incoming_or_deliver,");     
		   }else {
			   queryBuilder.append(" 0 ").append(" AS incoming_or_deliver,"); 
		   }
		   
		   
		   
		   queryBuilder.append("data_value").append(row.getManufacturer()).append(" AS manufacturer,");
		   
		   
		   if(row.getUnit() > 0) {
			   queryBuilder.append("data_value").append(row.getUnit()).append(" AS unit,");
		
		   }else {
			   queryBuilder.append(" NULL AS unit,");
		   }
		   
		   
		   if(row.getLot_material() > 0) {
			   queryBuilder.append("data_value").append(row.getMaterial()).append(" AS material,");
			   queryBuilder.append("data_value").append(row.getLot_material()).append(" AS lot_material");
		   }else {
		
			   queryBuilder.append("data_value").append(row.getMaterial()).append(" AS material,");
			   queryBuilder.append(" NULL AS lot_material");
		   }
		 
		   queryBuilder.append(" FROM ").append(" edata ").append(" WHERE ").append(" data_eformid = ? and data_eformrv =?  ");

		   if(row.getLot() > 0) {
			   queryBuilder.append("GROUP BY ").append("data_value").append(row.getMaterial()).append(",");
			   queryBuilder.append("data_value").append(row.getManufacturer()).append(",");
			   queryBuilder.append("data_value").append(row.getLot_material());
		   }else {
			   queryBuilder.append("GROUP BY ").append("data_value").append(row.getMaterial()).append(",");
			   queryBuilder.append("data_value").append(row.getManufacturer());
		   }
		   
		   queryBuilder.append(",data_due");
		   queryBuilder.append(" Limit 100000 ");
			String sql = queryBuilder.toString();
			
			System.out.print(" sql usematerial "+sql);
			 
			 List<materialDTO> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new materialDTOmapper());
			 
			  return edatas; 
	}
	
	
	
	
	@Override
	public List<edata> getall(int data_eformid,int data_eformrv
			,String title1,String key1
			,String title2,String key2
			,String title3,String key3
			,String title4,String key4
			,String dateStart,String dateEnd
			) {
		
		String subquery = "";
		
		if(title1 != null && !title1.isEmpty()  && key1 != null && !key1.isEmpty()) {
			subquery +=" and "+title1+" = '"+key1+"' ";
		}
		if(title2 != null && !title2.isEmpty()  && key2 != null && !key2.isEmpty()) {
			subquery +=" and "+title2+" = '"+key2+"' ";
		}
		if(title3 != null && !title3.isEmpty()  && key3 != null && !key3.isEmpty()) {
			subquery +=" and "+title3+"= '"+key3+"' ";
		}
		
		if(title4 != null && !title4.isEmpty()  && key4 != null && !key4.isEmpty()) {
			subquery +=" and "+title4+" = '"+key4+"' ";
		}
		if(dateStart != null && !dateStart.isEmpty()  && dateEnd != null && !dateEnd.isEmpty()) {
			subquery +=" and DATE(edata.data_due) >= '"+dateStart+"' ";
			subquery +=" and DATE(edata.data_due) <= '"+dateEnd+"' ";
		}
		
		subquery +=" group by edata.data_id order by edata.data_due desc limit 5000 ";
			
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		

		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				
				+ " where edata.data_eformid = ? and edata.data_eformrv =? "+subquery;
		
	

		System.out.println("sql" + sql);
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamapper3());
		return edatas;
	}
	
	
	
	@Override
	public List<edata> getall(edataDTO edataDTO) {
		

		   
		    String  table ="edata";
		
			
			List<String>  faketable = edataDTO.getJointable();
			List<String>  Join = edataDTO.getJoin();
			
			
			String[]  value= {edataDTO.getPurchase_order(),edataDTO.getCustomer(),edataDTO.getProduct(),edataDTO.getLot()
					,edataDTO.getMaterial(),edataDTO.getLot_material(),edataDTO.getWip(),edataDTO.getLot_wip()};
		
			String[] keyword= {edataDTO.getKeyno_purchase_order(),edataDTO.getKeyno_customer(),edataDTO.getKeyno_product(),edataDTO.getKeyno_lot()
					,edataDTO.getKeyno_material(),edataDTO.getKeyno_lot_material(),edataDTO.getKeyno_wip(),edataDTO.getKeyno_lot_wip()		
			};
			
			
			String[] filter ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
			String[] ConditionString ={"data_eformid ","data_eformrv"};
			String[] SelectString ={"*"};
			String[] column ={"data_value","data_title"};
		

     	
			String sql = Sqlservice.SqlBuilderString()
					.SELECT().table(table).faketable(faketable)
					//.column(column[0]).SelectBuilder(filter)
					// .String(",")
					//.column(column[1]).SelectBuilder(filter)
					// .String(",")
					 .SelectStringBuilder(SelectString)
					.FROM()
					.WHERE().column(column[0])
					.ConditionObjectBuilder(ConditionString,"AND")
					.AND()
					.ConditionBuilder(keyword,value,"AND")
					.AND()
					.DateBetweenConditionBuilder(edataDTO.getDate_start(),edataDTO.getDate_end(),edataDTO.getKey5())
					.withConditionIf(edataDTO.getKey5(),"!=",null).defaultCondition("data_due").GROUP("data_id")
					.UNIONALL()
					.Join(Join)
					.build();
	
			List<Object> paramsList = new ArrayList<>();
			 for (int i = 0; i < faketable.size(); i++) {
				 paramsList.add(edataDTO.getData_eformid());
				 paramsList.add(edataDTO.getData_eformrv());
			 }
			 Object[] params = paramsList.toArray();
			
		
		System.out.println("sql-> " + sql);
		
		List<edata> edatas  =  jdbcTemplate.query(sql,params,new edatamappers());
		return edatas;
		
		
		
	}
	
	
	
	
	@Override
	public List<edata> getallplan(int data_eformid,int data_eformrv) {
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where edata.data_eformid = ? and edata.data_eformrv =? and edata.stock = 0 "
				+ "  group by edata.data_id   order by edata.data_id desc limit 5000";
		
		
		System.out.println(sub);
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamapper3());
		return edatas;
		
	
		
	}
	
	
	

	
	
	@Override
	public List<edatas> getallbyuser(String user) {
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where  ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon ) )  AND upload_pn.application = 0   "
				+ "  group by  upload_pn.upload_pn_id,edata.data_flow  order by upload_pn.upload_pn_id desc limit 5000";
		
		
		System.out.println(sub);
		
		List<edatas> edatas  =  jdbcTemplate.query(sql,new Object[]{user,user},new edatamappers2());
		return edatas;
	}
	
		
	
	@Override
	public  int getallCountbyuser(String user) {
		String sql = "SELECT COUNT(*) FROM edata "
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = edata.data_planid  "
				+ " where  ( upload_pn.upload_pn_app =? OR FIND_IN_SET(?,upload_pn.upload_pn_respon ) )  AND upload_pn.application = 0 AND edata.data_flow < 2  ";
		return  jdbcTemplate.queryForObject(sql,new Object[]{user,user},Integer.class);
	}
	
	
	
	
	
	
	@Override
	public List<edata> getalllog(int id) {
		String sql = "SELECT * FROM edata_log  where id_refer =? ";
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{id},new edata_logmappers());
		return edatas;
	}
	
	@Override
	public edata getMaxbalance(int id,int no) {
		
		String[] filter = {"data_value"+no};
		String[] filterAs= {"data_value1"};
		String select = Sqlservice.SqlBuilderString().SelectConditionMaxBuilder(filter,filterAs).build();
		String sql = "SELECT "+select+",edata_log.* FROM edata_log  where id_refer =? ";
		
		edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new edata_logmappers());
		return edatas;
	}
	
	
	
	@Override
	public int savelog(edata edatas) {
		Object[] edataInfo = { 
				edatas.getId_refer(),
				edatas.getData_nameadd(),
				edatas.getData_dateadd(),
				edatas.getData_title1(),
				edatas.getData_value1(),
				edatas.getData_title2(),
				edatas.getData_value2(),
				edatas.getData_title3(),
				edatas.getData_value3(),
				edatas.getData_title4(),
				edatas.getData_value4(),
				edatas.getData_title5(),
				edatas.getData_value5(),
				edatas.getData_title6(),
				edatas.getData_value6(),
				edatas.getData_title7(),
				edatas.getData_value7(),
				edatas.getData_title8(),
				edatas.getData_value8(),
				edatas.getData_title9(),
				edatas.getData_value9(),
				edatas.getData_title10(),
				edatas.getData_value10(),
				edatas.getData_title11(),
				edatas.getData_value11(),
				edatas.getData_title12(),
				edatas.getData_value12(),
				edatas.getData_title13(),
				edatas.getData_value13(),
				edatas.getData_title14(),
				edatas.getData_value14(),
				edatas.getData_title15(),
				edatas.getData_value15(),
				edatas.getData_title16(),
				edatas.getData_value16(),
				edatas.getData_title17(),
				edatas.getData_value17(),
				edatas.getData_title18(),
				edatas.getData_value18(),
				edatas.getData_title19(),
				edatas.getData_value19(),
				edatas.getData_title20(),
				edatas.getData_value20()

				};
		
				String sql = "INSERT INTO edata_log(id_refer,data_nameadd,data_dateadd"
						+ ",data_title1,data_value1,data_title2,data_value2,data_title3,data_value3,data_title4,data_value4,data_title5,data_value5"
						+ ",data_title6,data_value6,data_title7,data_value7,data_title8,data_value8,data_title9,data_value9,data_title10,data_value10"
						+ ",data_title11,data_value11,data_title12,data_value12,data_title13,data_value13,data_title14,data_value14,data_title15,data_value15"
						+ ",data_title16,data_value16,data_title17,data_value17,data_title18,data_value18,data_title19,data_value19,data_title20,data_value20"
						+ ") "
						+ "VALUES("
						+ "?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,edataInfo);
	}


	

	
	@Override
	public int updatelog(edata edatas) {
		Object[] edataInfo = { 
				
				edatas.getData_nameadd(),
				edatas.getData_dateadd(),
				edatas.getData_title1(),
				edatas.getData_value1(),
				edatas.getData_title2(),
				edatas.getData_value2(),
				edatas.getData_title3(),
				edatas.getData_value3(),
				edatas.getData_title4(),
				edatas.getData_value4(),
				edatas.getData_title5(),
				edatas.getData_value5(),
				edatas.getData_title6(),
				edatas.getData_value6(),
				edatas.getData_title7(),
				edatas.getData_value7(),
				edatas.getData_title8(),
				edatas.getData_value8(),
				edatas.getData_title9(),
				edatas.getData_value9(),
				edatas.getData_title10(),
				edatas.getData_value10(),
				edatas.getData_title11(),
				edatas.getData_value11(),
				edatas.getData_title12(),
				edatas.getData_value12(),
				edatas.getData_title13(),
				edatas.getData_value13(),
				edatas.getData_title14(),
				edatas.getData_value14(),
				edatas.getData_title15(),
				edatas.getData_value15(),
				edatas.getData_title16(),
				edatas.getData_value16(),
				edatas.getData_title17(),
				edatas.getData_value17(),
				edatas.getData_title18(),
				edatas.getData_value18(),
				edatas.getData_title19(),
				edatas.getData_value19(),
				edatas.getData_title20(),
				edatas.getData_value20(),
				edatas.getId_refer(),
				edatas.getData_dateadd(),
				};
		
				String sql = "UPDATE  edata_log SET data_nameadd=?,data_dateadd=?"
						+ ",data_title1=?,data_value1=?,data_title2=?,data_value2=?,data_title3=?,data_value3=?,data_title4=?,data_value4=?,data_title5=?,data_value5=?"
						+ ",data_title6=?,data_value6=?,data_title7=?,data_value7=?,data_title8=?,data_value8=?,data_title9=?,data_value9=?,data_title10=?,data_value10=?"
						+ ",data_title11=?,data_value11=?,data_title12=?,data_value12=?,data_title13=?,data_value13=?,data_title14=?,data_value14=?,data_title15=?,data_value15=?"
						+ ",data_title16=?,data_value16=?,data_title17=?,data_value17=?,data_title18=?,data_value18=?,data_title19=?,data_value19=?,data_title20=?,data_value20=?"
						+ " WHERE id_refer=? and DATE(data_dateadd) =? ";
				return jdbcTemplate.update(sql,edataInfo);
	}


	
	
	@Override
	public List<edata> getallplan(int data_eformid,int data_eformrv,int pnid) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id OR edatas.data_join = edata.data_id "
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where edata.data_eformid = ? and edata.data_eformrv =?"
				+ " and edata.data_planid =?  group by edata.data_id   order by log.log_flow asc limit 10000";
		
		
		System.out.println(sub);
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv,pnid},new edatamapper3());
		return edatas;
	}
	
	
	
	@Override
	public List<edata> getallreport(int planid,String start,String end) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where   edata.data_planid =? "
				+ " and DATE(edata.data_due) >= ? and  DATE(edata.data_due) <= ?  "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000";
		
		
	
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{planid,start,end},new edatamapper3());
		return edatas;
	}
	
	
	
	@Override
	public List<edata> getallreportmap(int planid,int planid2,String start,String end,List<eformfield> eformfields,List<eformfield> eformfields2,report_mapping mappings) {
		
		String sub ="";
		if( mappings.getMapping_key1()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key1())  == 1) {  sub = sub+ " edata.data_value1 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 2) {  sub = sub+  " edata.data_value1 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 3) {   sub = sub+ " edata.data_value1 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 4) {   sub = sub+ " edata.data_value1 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 5) {  sub = sub+  " edata.data_value1 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 6) {  sub = sub+  " edata.data_value1 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 7) {  sub = sub+  " edata.data_value1 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 8) {  sub = sub+  " edata.data_value1 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 9) {  sub = sub+  " edata.data_value1 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 10) { sub = sub+   " edata.data_value1 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 11) {  sub = sub+  " edata.data_value1 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 12) {  sub = sub+  " edata.data_value1 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 13) {  sub = sub+  " edata.data_value1 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 14) {  sub = sub+  " edata.data_value1 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 15) {  sub = sub+  " edata.data_value1 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 16) {  sub = sub+  " edata.data_value1 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 17) {  sub = sub+  " edata.data_value1 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 18) {  sub = sub+  " edata.data_value1 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 19) {  sub = sub+  " edata.data_value1 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key1())  == 20) {  sub = sub+  " edata.data_value1 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key1()) != 1) {  sub = sub+" '' as data_value1, "; }
		}
		
		if( mappings.getMapping_key2()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key2())  == 1) {  sub = sub+ " edata.data_value2 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 2) {  sub = sub+  " edata.data_value2 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 3) {   sub = sub+ " edata.data_value2 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 4) {   sub = sub+ " edata.data_value2 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 5) {  sub = sub+  " edata.data_value2 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 6) {  sub = sub+  " edata.data_value2 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 7) {  sub = sub+  " edata.data_value2 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 8) {  sub = sub+  " edata.data_value2 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 9) {  sub = sub+  " edata.data_value2 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 10) { sub = sub+   " edata.data_value2 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 11) {  sub = sub+  " edata.data_value2 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 12) {  sub = sub+  " edata.data_value2 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 13) {  sub = sub+  " edata.data_value2 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 14) {  sub = sub+  " edata.data_value2 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 15) {  sub = sub+  " edata.data_value2 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 16) {  sub = sub+  " edata.data_value2 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 17) {  sub = sub+  " edata.data_value2 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 18) {  sub = sub+  " edata.data_value2 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 19) {  sub = sub+  " edata.data_value2 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 20) {  sub = sub+  " edata.data_value2 as data_value20, "; }
			
			
			if(Integer.valueOf(mappings.getMapping_key2()) != 2) {  sub = sub+" '' as data_value2, "; }
		}
		if( mappings.getMapping_key3()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key3())  == 1) {  sub = sub+ " edata.data_value3 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 2) {  sub = sub+  " edata.data_value3 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 3) {   sub = sub+ " edata.data_value3 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 4) {   sub = sub+ " edata.data_value3 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 5) {  sub = sub+  " edata.data_value3 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 6) {  sub = sub+  " edata.data_value3 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 7) {  sub = sub+  " edata.data_value3 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 8) {  sub = sub+  " edata.data_value3 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 9) {  sub = sub+  " edata.data_value3 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 10) { sub = sub+   " edata.data_value3 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 11) {  sub = sub+  " edata.data_value3 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 12) {  sub = sub+  " edata.data_value3 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 13) {  sub = sub+  " edata.data_value3 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 14) {  sub = sub+  " edata.data_value3 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 15) {  sub = sub+  " edata.data_value3 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 16) {  sub = sub+  " edata.data_value3 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 17) {  sub = sub+  " edata.data_value3 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 18) {  sub = sub+  " edata.data_value3 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 19) {  sub = sub+  " edata.data_value3 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 20) {  sub = sub+  " edata.data_value3 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key3()) != 3) {  sub = sub+" '' as data_value3, "; }
			
		}
		if( mappings.getMapping_key4()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key4())  == 1) {  sub = sub+ " edata.data_value4 as data_value1,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 2) {  sub = sub+  " edata.data_value4 as data_value2,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 3) {   sub = sub+ " edata.data_value4 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 4) {   sub = sub+ " edata.data_value4 as data_value4,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 5) {  sub = sub+  " edata.data_value4 as data_value5,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 6) {  sub = sub+  " edata.data_value4 as data_value6,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 7) {  sub = sub+  " edata.data_value4 as data_value7,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 8) {  sub = sub+  " edata.data_value4 as data_value8,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 9) {  sub = sub+  " edata.data_value4 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 10) { sub = sub+   " edata.data_value4 as data_value10,  "; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 11) {  sub = sub+  " edata.data_value4 as data_value11,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 12) {  sub = sub+  " edata.data_value4 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 13) {  sub = sub+  " edata.data_value4 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 14) {  sub = sub+  " edata.data_value4 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 15) {  sub = sub+  " edata.data_value4 as data_value15,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 16) {  sub = sub+  " edata.data_value4 as data_value16,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 17) {  sub = sub+  " edata.data_value4 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 18) {  sub = sub+  " edata.data_value4 as data_value18,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 19) {  sub = sub+  " edata.data_value4 as data_value19,"; }
			if(Integer.valueOf(mappings.getMapping_key4())  == 20) {  sub = sub+  " edata.data_value4 as data_value20,"; }
			
			if(Integer.valueOf(mappings.getMapping_key4()) != 4) {  sub = sub+" '' as data_value4, "; }
		}
		if( mappings.getMapping_key5()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key5())  == 1) {  sub = sub+ " edata.data_value5 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 2) {  sub = sub+  " edata.data_value5 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 3) {   sub = sub+ " edata.data_value5 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 4) {   sub = sub+ " edata.data_value5 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 5) {  sub = sub+  " edata.data_value5 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 6) {  sub = sub+  " edata.data_value5 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 7) {  sub = sub+  " edata.data_value5 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 8) {  sub = sub+  " edata.data_value5 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 9) {  sub = sub+  " edata.data_value5 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 10) { sub = sub+   " edata.data_value5 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 11) {  sub = sub+  " edata.data_value5 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 12) {  sub = sub+  " edata.data_value5 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 13) {  sub = sub+  " edata.data_value5 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 14) {  sub = sub+  " edata.data_value5 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 15) {  sub = sub+  " edata.data_value5 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 16) {  sub = sub+  " edata.data_value5 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 17) {  sub = sub+  " edata.data_value5 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 18) {  sub = sub+  " edata.data_value5 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 19) {  sub = sub+  " edata.data_value5 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key5())  == 20) {  sub = sub+  " edata.data_value5 as data_value20, "; }
			
			
			if(Integer.valueOf(mappings.getMapping_key5()) != 5) {  sub = sub+" '' as data_value5, "; }
		}
		if( mappings.getMapping_key6()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key6())  == 1) {  sub = sub+ " edata.data_value6 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 2) {  sub = sub+  " edata.data_value6 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 3) {   sub = sub+ " edata.data_value6 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 4) {   sub = sub+ " edata.data_value6 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 5) {  sub = sub+  " edata.data_value6 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 6) {  sub = sub+  " edata.data_value6 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 7) {  sub = sub+  " edata.data_value6 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 8) {  sub = sub+  " edata.data_value6 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 9) {  sub = sub+  " edata.data_value6 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 10) { sub = sub+   " edata.data_value6 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 11) {  sub = sub+  " edata.data_value6 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 12) {  sub = sub+  " edata.data_value6 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 13) {  sub = sub+  " edata.data_value6 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 14) {  sub = sub+  " edata.data_value6 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 15) {  sub = sub+  " edata.data_value6 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 16) {  sub = sub+  " edata.data_value6 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 17) {  sub = sub+  " edata.data_value6 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 18) {  sub = sub+  " edata.data_value6 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 19) {  sub = sub+  " edata.data_value6 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 20) {  sub = sub+  " edata.data_value6 as data_value20, "; }
			
			
			if(Integer.valueOf(mappings.getMapping_key6()) != 6) {  sub = sub+" '' as data_value6, "; }
		}
		if( mappings.getMapping_key7()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key7())  == 1) {  sub = sub+ " edata.data_value7 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 2) {  sub = sub+  " edata.data_value7 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 3) {   sub = sub+ " edata.data_value7 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 4) {   sub = sub+ " edata.data_value7 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 5) {  sub = sub+  " edata.data_value7 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 6) {  sub = sub+  " edata.data_value7 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 7) {  sub = sub+  " edata.data_value7 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 8) {  sub = sub+  " edata.data_value7 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 9) {  sub = sub+  " edata.data_value7 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 10) { sub = sub+   " edata.data_value7 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 11) {  sub = sub+  " edata.data_value7 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 12) {  sub = sub+  " edata.data_value7 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 13) {  sub = sub+  " edata.data_value7 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 14) {  sub = sub+  " edata.data_value7 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 15) {  sub = sub+  " edata.data_value7 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 16) {  sub = sub+  " edata.data_value7 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 17) {  sub = sub+  " edata.data_value7 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 18) {  sub = sub+  " edata.data_value7 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 19) {  sub = sub+  " edata.data_value7 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 20) {  sub = sub+  " edata.data_value7 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key7()) != 6) {  sub = sub+" '' as data_value7, "; }
			
		}
		if( mappings.getMapping_key8()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key8())  == 1) {  sub = sub+ " edata.data_value8 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 2) {  sub = sub+  " edata.data_value8 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 3) {   sub = sub+ " edata.data_value8 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 4) {   sub = sub+ " edata.data_value8 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 5) {  sub = sub+  " edata.data_value8 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 6) {  sub = sub+  " edata.data_value8 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 7) {  sub = sub+  " edata.data_value8 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 8) {  sub = sub+  " edata.data_value8 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 9) {  sub = sub+  " edata.data_value8 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 10) { sub = sub+   " edata.data_value8 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 11) {  sub = sub+  " edata.data_value8 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 12) {  sub = sub+  " edata.data_value8 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 13) {  sub = sub+  " edata.data_value8 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 14) {  sub = sub+  " edata.data_value8 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 15) {  sub = sub+  " edata.data_value8 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 16) {  sub = sub+  " edata.data_value8 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 17) {  sub = sub+  " edata.data_value8 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 18) {  sub = sub+  " edata.data_value8 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 19) {  sub = sub+  " edata.data_value8 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 20) {  sub = sub+  " edata.data_value8 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key8()) != 8) {  sub = sub+" '' as data_value8, "; }
			
		}
		if( mappings.getMapping_key9()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key9())  == 1) {  sub = sub+ " edata.data_value9 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 2) {  sub = sub+  " edata.data_value9 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 3) {   sub = sub+ " edata.data_value9 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 4) {   sub = sub+ " edata.data_value9 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 5) {  sub = sub+  " edata.data_value9 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 6) {  sub = sub+  " edata.data_value9 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 7) {  sub = sub+  " edata.data_value9 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 8) {  sub = sub+  " edata.data_value9 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 9) {  sub = sub+  " edata.data_value9 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 10) { sub = sub+   " edata.data_value9 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 11) {  sub = sub+  " edata.data_value9 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 12) {  sub = sub+  " edata.data_value9 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 13) {  sub = sub+  " edata.data_value9 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 14) {  sub = sub+  " edata.data_value9 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 15) {  sub = sub+  " edata.data_value9 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 16) {  sub = sub+  " edata.data_value9 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 17) {  sub = sub+  " edata.data_value9 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 18) {  sub = sub+  " edata.data_value9 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 19) {  sub = sub+  " edata.data_value9 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 20) {  sub = sub+  " edata.data_value9 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key9()) != 9) {  sub = sub+" '' as data_value9, "; }
		}
		if( mappings.getMapping_key10()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key10())  == 1) {  sub = sub+ " edata.data_value10 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 2) {  sub = sub+  " edata.data_value10 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 3) {   sub = sub+ " edata.data_value10 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 4) {   sub = sub+ " edata.data_value10 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 5) {  sub = sub+  " edata.data_value10 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 6) {  sub = sub+  " edata.data_value10 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 7) {  sub = sub+  " edata.data_value10 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 8) {  sub = sub+  " edata.data_value10 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 9) {  sub = sub+  " edata.data_value10 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 10) { sub = sub+   " edata.data_value10 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 11) {  sub = sub+  " edata.data_value10 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 12) {  sub = sub+  " edata.data_value10 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 13) {  sub = sub+  " edata.data_value10 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 14) {  sub = sub+  " edata.data_value10 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 15) {  sub = sub+  " edata.data_value10 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 16) {  sub = sub+  " edata.data_value10 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 17) {  sub = sub+  " edata.data_value10 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 18) {  sub = sub+  " edata.data_value10 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 19) {  sub = sub+  " edata.data_value10 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 20) {  sub = sub+  " edata.data_value10 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key10()) != 10) {  sub = sub+" '' as data_value10, "; }
		}
		
		sub = sub+"edata.data_nameadd as data_nameadd";
		
		
	
		

		String sql = "  SELECT "+sub+",edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where   edata.data_planid =? "
				+ " and DATE(edata.data_due) >= ? and  DATE(edata.data_due) <= ?  "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000  ";
		
				
		String sql2 = "  SELECT edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where   edata.data_planid =? "
				+ " and DATE(edata.data_due) >= ? and  DATE(edata.data_due) <= ?  "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000  ";	
				
	
		List<edata> edatas2  =  jdbcTemplate.query(sql,new Object[]{planid2,start,end},new edatamapper2());
		List<edata> edatas  =  jdbcTemplate.query(sql2,new Object[]{planid,start,end},new edatamapper2());	
		
		edatas.addAll(edatas2);

		
		List<edata> sortedEdatas = edatas.stream()
			    .sorted((e1, e2) -> e1.getData_id() - e2.getData_id()) // Lambda Expression เปรียบเทียบตาม edata_id
			    .collect(Collectors.toList());
		
		return sortedEdatas;
	}
	
	
	
	
	
	
	@Override
	public List<edata> getallreport(int planid,int planid2,String start,String end) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where   ( edata.data_planid =?  or edata.data_planid =? )  "
				+ " and DATE(edata.data_due) >= ? and  DATE(edata.data_due) <= ?  "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000";
		
		
	
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{planid,planid2,start,end},new edatamapper3());
		return edatas;
	}
	
	
	@Override
	public List<edata> getallreport(String plan) {
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where  find_in_set(edata.data_id,?) "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000";
		
		
	
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{plan},new edatamapper3());
		return edatas;
	}
	
	
	
	
	
	@Override
	public List<edata> getallreportmap(String id,int plan1,int plan2,report_mapping mappings, int size) {
		
		

		
		String sub ="";
		if( mappings.getMapping_key1()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key1())  == 1) {  sub = sub+ " edata.data_value1 as data_value1, ";   if(size < 1 ) { sub = sub+ "edata.data_title1 as data_title1,"; }   }
			if(Integer.valueOf(mappings.getMapping_key1())  == 2) {  sub = sub+  " edata.data_value1 as data_value2, ";  if(size < 2 ) { sub = sub+ "edata.data_title1 as data_title2,"; }  }
			if(Integer.valueOf(mappings.getMapping_key1())  == 3) {   sub = sub+ " edata.data_value1 as data_value3, ";  if(size < 3 ) { sub = sub+ "edata.data_title1 as data_title3,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 4) {   sub = sub+ " edata.data_value1 as data_value4, ";  if(size < 4 ) { sub = sub+ "edata.data_title1 as data_title4,"; } }
			if(Integer.valueOf(mappings.getMapping_key1())  == 5) {  sub = sub+  " edata.data_value1 as data_value5, ";  if(size < 5 ) { sub = sub+ "edata.data_title1 as data_title5,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 6) {  sub = sub+  " edata.data_value1 as data_value6, "; if(size < 3 ) { sub = sub+ "edata.data_title1 as data_title6,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 7) {  sub = sub+  " edata.data_value1 as data_value7, "; if(size < 7 ) { sub = sub+ "edata.data_title1 as data_title7,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 8) {  sub = sub+  " edata.data_value1 as data_value8, "; if(size < 8 ) { sub = sub+ "edata.data_title1 as data_title8,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 9) {  sub = sub+  " edata.data_value1 as data_value9, "; if(size < 9 ) { sub = sub+ "edata.data_title1 as data_title9,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 10) { sub = sub+   " edata.data_value1 as data_value10, "; if(size < 10 ) { sub = sub+ "edata.data_title1 as data_title10,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 11) {  sub = sub+  " edata.data_value1 as data_value11, "; if(size < 11 ) { sub = sub+ "edata.data_title1 as data_title11,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 12) {  sub = sub+  " edata.data_value1 as data_value12,"; if(size < 12 ) { sub = sub+ "edata.data_title1 as data_title12,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 13) {  sub = sub+  " edata.data_value1 as data_value13, "; if(size < 13 ) { sub = sub+ "edata.data_title1 as data_title13,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 14) {  sub = sub+  " edata.data_value1 as data_value14, "; if(size < 14 ) { sub = sub+ "edata.data_title1 as data_title14,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 15) {  sub = sub+  " edata.data_value1 as data_value15, "; if(size < 15 ) { sub = sub+ "edata.data_title1 as data_title15,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 16) {  sub = sub+  " edata.data_value1 as data_value16, "; if(size < 16 ) { sub = sub+ "edata.data_title1 as data_title16,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 17) {  sub = sub+  " edata.data_value1 as data_value17, "; if(size < 17 ) { sub = sub+ "edata.data_title1 as data_title17,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 18) {  sub = sub+  " edata.data_value1 as data_value18, "; if(size < 18 ) { sub = sub+ "edata.data_title1 as data_title18,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 19) {  sub = sub+  " edata.data_value1 as data_value19, "; if(size < 19 ) { sub = sub+ "edata.data_title1 as data_title19,"; }}
			if(Integer.valueOf(mappings.getMapping_key1())  == 20) {  sub = sub+  " edata.data_value1 as data_value20, "; if(size < 20 ) { sub = sub+ "edata.data_title1 as data_title20,"; }}
			
			if(Integer.valueOf(mappings.getMapping_key1()) != 1) {  sub = sub+" '' as data_value1, "; }
		}
		
		if( mappings.getMapping_key2()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key2())  == 1) {  sub = sub+ " edata.data_value2 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 2) {  sub = sub+  " edata.data_value2 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 3) {   sub = sub+ " edata.data_value2 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 4) {   sub = sub+ " edata.data_value2 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 5) {  sub = sub+  " edata.data_value2 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 6) {  sub = sub+  " edata.data_value2 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 7) {  sub = sub+  " edata.data_value2 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 8) {  sub = sub+  " edata.data_value2 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 9) {  sub = sub+  " edata.data_value2 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 10) { sub = sub+   " edata.data_value2 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 11) {  sub = sub+  " edata.data_value2 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 12) {  sub = sub+  " edata.data_value2 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 13) {  sub = sub+  " edata.data_value2 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 14) {  sub = sub+  " edata.data_value2 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 15) {  sub = sub+  " edata.data_value2 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 16) {  sub = sub+  " edata.data_value2 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 17) {  sub = sub+  " edata.data_value2 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 18) {  sub = sub+  " edata.data_value2 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 19) {  sub = sub+  " edata.data_value2 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key2())  == 20) {  sub = sub+  " edata.data_value2 as data_value20, "; }
			
			
			if(Integer.valueOf(mappings.getMapping_key2()) != 2) {  sub = sub+" '' as data_value2, "; }
		}
		if( mappings.getMapping_key3()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key3())  == 1) {  sub = sub+ " edata.data_value3 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 2) {  sub = sub+  " edata.data_value3 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 3) {   sub = sub+ " edata.data_value3 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 4) {   sub = sub+ " edata.data_value3 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 5) {  sub = sub+  " edata.data_value3 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 6) {  sub = sub+  " edata.data_value3 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 7) {  sub = sub+  " edata.data_value3 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 8) {  sub = sub+  " edata.data_value3 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 9) {  sub = sub+  " edata.data_value3 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 10) { sub = sub+   " edata.data_value3 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 11) {  sub = sub+  " edata.data_value3 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 12) {  sub = sub+  " edata.data_value3 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 13) {  sub = sub+  " edata.data_value3 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 14) {  sub = sub+  " edata.data_value3 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 15) {  sub = sub+  " edata.data_value3 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 16) {  sub = sub+  " edata.data_value3 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 17) {  sub = sub+  " edata.data_value3 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 18) {  sub = sub+  " edata.data_value3 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 19) {  sub = sub+  " edata.data_value3 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key3())  == 20) {  sub = sub+  " edata.data_value3 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key3()) != 3) {  sub = sub+" '' as data_value3, "; }
			
		}
		if( mappings.getMapping_key4()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key4())  == 1) {  sub = sub+ " edata.data_value4 as data_value1,";  if(size < 1 ) { sub = sub+ "edata.data_title4 as data_title1,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 2) {  sub = sub+  " edata.data_value4 as data_value2,"; if(size < 2 ) { sub = sub+ "edata.data_title4 as data_title2,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 3) {   sub = sub+ " edata.data_value4 as data_value3, "; if(size < 3 ) { sub = sub+ "edata.data_title4 as data_title3,"; }  }
			if(Integer.valueOf(mappings.getMapping_key4())  == 4) {   sub = sub+ " edata.data_value4 as data_value4,"; if(size < 4 ) { sub = sub+ "edata.data_title4 as data_title4,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 5) {  sub = sub+  " edata.data_value4 as data_value5,"; if(size < 5 ) { sub = sub+ "edata.data_title4 as data_title5,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 6) {  sub = sub+  " edata.data_value4 as data_value6,"; if(size < 6 ) { sub = sub+ "edata.data_title4 as data_title6,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 7) {  sub = sub+  " edata.data_value4 as data_value7,"; if(size < 7 ) { sub = sub+ "edata.data_title4 as data_title7,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 8) {  sub = sub+  " edata.data_value4 as data_value8,"; if(size < 8 ) { sub = sub+ "edata.data_title4 as data_title8,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 9) {  sub = sub+  " edata.data_value4 as data_value9, "; if(size < 9 ) { sub = sub+ "edata.data_title4 as data_title9,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 10) { sub = sub+   " edata.data_value4 as data_value10,  "; if(size < 10 ) { sub = sub+ "edata.data_title4 as data_title10,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 11) {  sub = sub+  " edata.data_value4 as data_value11,"; if(size < 11 ) { sub = sub+ "edata.data_title4 as data_title11,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 12) {  sub = sub+  " edata.data_value4 as data_value12,"; if(size < 12 ) { sub = sub+ "edata.data_title4 as data_title12,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 13) {  sub = sub+  " edata.data_value4 as data_value13, "; if(size < 13 ) { sub = sub+ "edata.data_title4 as data_title13,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 14) {  sub = sub+  " edata.data_value4 as data_value14, "; if(size < 14 ) { sub = sub+ "edata.data_title4 as data_title14,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 15) {  sub = sub+  " edata.data_value4 as data_value15,"; if(size < 15 ) { sub = sub+ "edata.data_title4 as data_title15,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 16) {  sub = sub+  " edata.data_value4 as data_value16,"; if(size < 16 ) { sub = sub+ "edata.data_title4 as data_title16,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 17) {  sub = sub+  " edata.data_value4 as data_value17, "; if(size < 17 ) { sub = sub+ "edata.data_title4 as data_title17,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 18) {  sub = sub+  " edata.data_value4 as data_value18,"; if(size < 18 ) { sub = sub+ "edata.data_title4 as data_title18,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 19) {  sub = sub+  " edata.data_value4 as data_value19,"; if(size < 19 ) { sub = sub+ "edata.data_title4 as data_title19,"; } }
			if(Integer.valueOf(mappings.getMapping_key4())  == 20) {  sub = sub+  " edata.data_value4 as data_value20,"; if(size < 20 ) { sub = sub+ "edata.data_title4 as data_title20,"; } }
			
			if(Integer.valueOf(mappings.getMapping_key4()) != 4) {  sub = sub+" '' as data_value4, "; }
		}
		if( mappings.getMapping_key5()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key5())  == 1) {  sub = sub+ " edata.data_value5 as data_value1, ";  if(size < 1 ) { sub = sub+ "edata.data_title5 as data_title1,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 2) {  sub = sub+  " edata.data_value5 as data_value2, "; if(size < 2 ) { sub = sub+ "edata.data_title5 as data_title2,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 3) {   sub = sub+ " edata.data_value5 as data_value3, "; if(size < 3 ) { sub = sub+ "edata.data_title5 as data_title3,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 4) {   sub = sub+ " edata.data_value5 as data_value4, "; if(size < 4 ) { sub = sub+ "edata.data_title5 as data_title4,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 5) {  sub = sub+  " edata.data_value5 as data_value5, "; if(size < 5 ) { sub = sub+ "edata.data_title5 as data_title5,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 6) {  sub = sub+  " edata.data_value5 as data_value6, "; if(size < 6 ) { sub = sub+ "edata.data_title5 as data_title6,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 7) {  sub = sub+  " edata.data_value5 as data_value7, "; if(size < 7 ) { sub = sub+ "edata.data_title5 as data_title7,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 8) {  sub = sub+  " edata.data_value5 as data_value8, "; if(size < 8 ) { sub = sub+ "edata.data_title5 as data_title8,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 9) {  sub = sub+  " edata.data_value5 as data_value9, "; if(size < 9 ) { sub = sub+ "edata.data_title5 as data_title9,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 10) { sub = sub+   " edata.data_value5 as data_value10, "; if(size < 10 ) { sub = sub+ "edata.data_title5 as data_title10,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 11) {  sub = sub+  " edata.data_value5 as data_value11, "; if(size < 11 ) { sub = sub+ "edata.data_title5 as data_title11,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 12) {  sub = sub+  " edata.data_value5 as data_value12,"; if(size < 12 ) { sub = sub+ "edata.data_title5 as data_title12,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 13) {  sub = sub+  " edata.data_value5 as data_value13, ";if(size < 13 ) { sub = sub+ "edata.data_title5 as data_title13,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 14) {  sub = sub+  " edata.data_value5 as data_value14, "; if(size < 14 ) { sub = sub+ "edata.data_title5 as data_title14,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 15) {  sub = sub+  " edata.data_value5 as data_value15, ";if(size < 15 ) { sub = sub+ "edata.data_title5 as data_title15,"; } }
			if(Integer.valueOf(mappings.getMapping_key5())  == 16) {  sub = sub+  " edata.data_value5 as data_value16, "; if(size < 16 ) { sub = sub+ "edata.data_title5 as data_title16,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 17) {  sub = sub+  " edata.data_value5 as data_value17, "; if(size < 17 ) { sub = sub+ "edata.data_title5 as data_title17,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 18) {  sub = sub+  " edata.data_value5 as data_value18, "; if(size < 18 ) { sub = sub+ "edata.data_title5 as data_title18,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 19) {  sub = sub+  " edata.data_value5 as data_value19, "; if(size < 19 ) { sub = sub+ "edata.data_title5 as data_title19,"; }}
			if(Integer.valueOf(mappings.getMapping_key5())  == 20) {  sub = sub+  " edata.data_value5 as data_value20, "; if(size < 20 ) { sub = sub+ "edata.data_title5 as data_title20,"; }}
			
			
			if(Integer.valueOf(mappings.getMapping_key5()) != 5) {  sub = sub+" '' as data_value5, "; }
		}
		if( mappings.getMapping_key6()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key6())  == 1) {  sub = sub+ " edata.data_value6 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 2) {  sub = sub+  " edata.data_value6 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 3) {   sub = sub+ " edata.data_value6 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 4) {   sub = sub+ " edata.data_value6 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 5) {  sub = sub+  " edata.data_value6 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 6) {  sub = sub+  " edata.data_value6 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 7) {  sub = sub+  " edata.data_value6 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 8) {  sub = sub+  " edata.data_value6 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 9) {  sub = sub+  " edata.data_value6 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 10) { sub = sub+   " edata.data_value6 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 11) {  sub = sub+  " edata.data_value6 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 12) {  sub = sub+  " edata.data_value6 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 13) {  sub = sub+  " edata.data_value6 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 14) {  sub = sub+  " edata.data_value6 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 15) {  sub = sub+  " edata.data_value6 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 16) {  sub = sub+  " edata.data_value6 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 17) {  sub = sub+  " edata.data_value6 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 18) {  sub = sub+  " edata.data_value6 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 19) {  sub = sub+  " edata.data_value6 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key6())  == 20) {  sub = sub+  " edata.data_value6 as data_value20, "; }
			
			
			if(Integer.valueOf(mappings.getMapping_key6()) != 6) {  sub = sub+" '' as data_value6, "; }
		}
		if( mappings.getMapping_key7()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key7())  == 1) {  sub = sub+ " edata.data_value7 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 2) {  sub = sub+  " edata.data_value7 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 3) {   sub = sub+ " edata.data_value7 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 4) {   sub = sub+ " edata.data_value7 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 5) {  sub = sub+  " edata.data_value7 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 6) {  sub = sub+  " edata.data_value7 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 7) {  sub = sub+  " edata.data_value7 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 8) {  sub = sub+  " edata.data_value7 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 9) {  sub = sub+  " edata.data_value7 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 10) { sub = sub+   " edata.data_value7 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 11) {  sub = sub+  " edata.data_value7 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 12) {  sub = sub+  " edata.data_value7 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 13) {  sub = sub+  " edata.data_value7 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 14) {  sub = sub+  " edata.data_value7 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 15) {  sub = sub+  " edata.data_value7 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 16) {  sub = sub+  " edata.data_value7 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 17) {  sub = sub+  " edata.data_value7 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 18) {  sub = sub+  " edata.data_value7 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 19) {  sub = sub+  " edata.data_value7 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key7())  == 20) {  sub = sub+  " edata.data_value7 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key7()) != 6) {  sub = sub+" '' as data_value7, "; }
			
		}
		if( mappings.getMapping_key8()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key8())  == 1) {  sub = sub+ " edata.data_value8 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 2) {  sub = sub+  " edata.data_value8 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 3) {   sub = sub+ " edata.data_value8 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 4) {   sub = sub+ " edata.data_value8 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 5) {  sub = sub+  " edata.data_value8 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 6) {  sub = sub+  " edata.data_value8 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 7) {  sub = sub+  " edata.data_value8 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 8) {  sub = sub+  " edata.data_value8 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 9) {  sub = sub+  " edata.data_value8 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 10) { sub = sub+   " edata.data_value8 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 11) {  sub = sub+  " edata.data_value8 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 12) {  sub = sub+  " edata.data_value8 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 13) {  sub = sub+  " edata.data_value8 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 14) {  sub = sub+  " edata.data_value8 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 15) {  sub = sub+  " edata.data_value8 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 16) {  sub = sub+  " edata.data_value8 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 17) {  sub = sub+  " edata.data_value8 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 18) {  sub = sub+  " edata.data_value8 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 19) {  sub = sub+  " edata.data_value8 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key8())  == 20) {  sub = sub+  " edata.data_value8 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key8()) != 8) {  sub = sub+" '' as data_value8, "; }
			
		}
		if( mappings.getMapping_key9()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key9())  == 1) {  sub = sub+ " edata.data_value9 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 2) {  sub = sub+  " edata.data_value9 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 3) {   sub = sub+ " edata.data_value9 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 4) {   sub = sub+ " edata.data_value9 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 5) {  sub = sub+  " edata.data_value9 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 6) {  sub = sub+  " edata.data_value9 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 7) {  sub = sub+  " edata.data_value9 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 8) {  sub = sub+  " edata.data_value9 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 9) {  sub = sub+  " edata.data_value9 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 10) { sub = sub+   " edata.data_value9 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 11) {  sub = sub+  " edata.data_value9 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 12) {  sub = sub+  " edata.data_value9 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 13) {  sub = sub+  " edata.data_value9 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 14) {  sub = sub+  " edata.data_value9 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 15) {  sub = sub+  " edata.data_value9 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 16) {  sub = sub+  " edata.data_value9 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 17) {  sub = sub+  " edata.data_value9 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 18) {  sub = sub+  " edata.data_value9 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 19) {  sub = sub+  " edata.data_value9 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key9())  == 20) {  sub = sub+  " edata.data_value9 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key9()) != 9) {  sub = sub+" '' as data_value9, "; }
		}
		if( mappings.getMapping_key10()  != null ) {
			if(Integer.valueOf(mappings.getMapping_key10())  == 1) {  sub = sub+ " edata.data_value10 as data_value1, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 2) {  sub = sub+  " edata.data_value10 as data_value2, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 3) {   sub = sub+ " edata.data_value10 as data_value3, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 4) {   sub = sub+ " edata.data_value10 as data_value4, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 5) {  sub = sub+  " edata.data_value10 as data_value5, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 6) {  sub = sub+  " edata.data_value10 as data_value6, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 7) {  sub = sub+  " edata.data_value10 as data_value7, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 8) {  sub = sub+  " edata.data_value10 as data_value8, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 9) {  sub = sub+  " edata.data_value10 as data_value9, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 10) { sub = sub+   " edata.data_value10 as data_value10, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 11) {  sub = sub+  " edata.data_value10 as data_value11, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 12) {  sub = sub+  " edata.data_value10 as data_value12,"; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 13) {  sub = sub+  " edata.data_value10 as data_value13, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 14) {  sub = sub+  " edata.data_value10 as data_value14, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 15) {  sub = sub+  " edata.data_value10 as data_value15, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 16) {  sub = sub+  " edata.data_value10 as data_value16, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 17) {  sub = sub+  " edata.data_value10 as data_value17, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 18) {  sub = sub+  " edata.data_value10 as data_value18, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 19) {  sub = sub+  " edata.data_value10 as data_value19, "; }
			if(Integer.valueOf(mappings.getMapping_key10())  == 20) {  sub = sub+  " edata.data_value10 as data_value20, "; }
			
			if(Integer.valueOf(mappings.getMapping_key10()) != 10) {  sub = sub+" '' as data_value10, "; }
		}
		
		sub = sub+"edata.data_nameadd as data_nameadd";
		
		
	
		

		String sql = "  SELECT "+sub+",edata.*,log.*,upload_pn.*,report_pn.*,file.*  FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN file ON file.file_name = log.log_file  "
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where    edata.data_planid =?  AND  find_in_set(edata.data_id,?)   "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000  ";
		
				
		String sql2 = "  SELECT edata.*,log.*,upload_pn.*,report_pn.*,file.*  FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN file ON file.file_name = log.log_file  "
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
				+ " where   edata.data_planid =?  AND  find_in_set(edata.data_id,?)  "
				+ " group by edata.data_id   order by edata.data_due desc limit 5000  ";	
				
		
		List<edata> edatas2  =  jdbcTemplate.query(sql,new Object[]{plan2,id},new edatamapper2());
		List<edata> edatas  =  jdbcTemplate.query(sql2,new Object[]{plan1,id},new edatamapper2());	
		
		edatas.addAll(edatas2);

		
		List<edata> sortedEdatas = edatas.stream()
			    .sorted((e1, e2) -> e1.getData_id() - e2.getData_id()) // Lambda Expression เปรียบเทียบตาม edata_id
			    .collect(Collectors.toList());
		
		return sortedEdatas;

	}
	
	
	
	
	
	@Override
	public edata getbyfilepath(int id) {
	
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		
		

		  try {
				String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
						+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
						+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id = log.log_refer  "
						+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer  "
						+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id "
						+ "where edata.data_filepath = ? group by edata.data_id ";
				edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new edatamapper3());
				return edatas;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyfilepath with the same Id .......");
		   
		 }
	
		
		
	}

	
	
	

	@Override
	public void setOrder(long id,int product_no) {
	
		
		 String sql = "UPDATE edata SET ";
	 
	 
		if(product_no == 1) { sql = sql +  " data_order = data_value1  "; }
		else if(product_no == 2) { sql = sql +  "   data_order =  data_value2 "; }
		else if(product_no == 3) { sql = sql +  "  data_order =  data_value3"; }
		else if(product_no == 4) { sql = sql +  "  data_order =  data_value4 "; }
		else if(product_no == 5) { sql = sql +  "   data_order = data_value5  "; }
		else if(product_no == 6) { sql = sql +  "  data_order =  data_value6  "; }
		else if(product_no == 7) { sql = sql +  "  data_order =  data_value7 "; }
		else if(product_no == 8) { sql = sql +  "  data_order =  data_value8 "; }
		else if(product_no == 9) { sql = sql +  "  data_order =  data_value9  "; }
		else if(product_no == 10) { sql = sql +  "  data_order =  data_value10  "; }
		else if(product_no == 11) { sql = sql +  "  data_order =  data_value11 "; }
		else if(product_no == 12) { sql = sql +  "  data_order =  data_value12  "; }
		else if(product_no == 13) { sql = sql +  "  data_order =  data_value13 "; }
		else if(product_no == 14) { sql = sql +  "  data_order =  data_value14  "; }
		else if(product_no == 15) { sql = sql +  "  data_order =  data_value15  "; }
		else if(product_no == 16) { sql = sql +  "  data_order =  data_value16  "; }
		else if(product_no == 17) { sql = sql +  "  data_order =  data_value17 "; }
		else if(product_no == 18) { sql = sql +  "  data_order =  data_value18  "; }
		else if(product_no == 19) { sql = sql +  "  data_order =  data_value19  "; }
		else if(product_no == 20) { sql = sql +  "  data_order =  data_value20  "; }

	
		
		sql = sql + " WHERE data_id = ? ";


	   jdbcTemplate.update(sql,new Object[]{id});
		

		
	}
	
	
	
	
	
	

	@Override
	public void setcal_update(String cal_value,long product_no,int data_id) {
	
		
		 String sql = "UPDATE edata SET ";
	 
	 
		if(product_no == 1) { sql = sql +  "   data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  "   data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  "   data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  "   data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  "   data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  "   data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  "   data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  "   data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  "   data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  "   data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  "   data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  "   data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  "   data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  "   data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  "   data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  "   data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  "   data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  "   data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  "   data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  "   data_value20 = ? "; }

	
		
		sql = sql + " WHERE data_id = ? ";


	   jdbcTemplate.update(sql,new Object[]{cal_value,data_id});
		

		
	}
	
	
	
	

	@Override
	public void setcal_plus(String cal_value,long product_no,int data_id) {
	
		
		 String sql = "UPDATE edata SET ";
	 
	 
		if(product_no == 1) { sql = sql +  "   data_value1 =  data_value1 + ? "; }
		else if(product_no == 2) { sql = sql +  "   data_value2 = data_value2 + ? "; }
		else if(product_no == 3) { sql = sql +  "   data_value3 = data_value3 + ? "; }
		else if(product_no == 4) { sql = sql +  "   data_value4 = data_value4 + ? "; }
		else if(product_no == 5) { sql = sql +  "   data_value5 = data_value5 + ? "; }
		else if(product_no == 6) { sql = sql +  "   data_value6 = data_value6 + ? "; }
		else if(product_no == 7) { sql = sql +  "   data_value7 = data_value7 + ? "; }
		else if(product_no == 8) { sql = sql +  "   data_value8 = data_value8 + ? "; }
		else if(product_no == 9) { sql = sql +  "   data_value9 = data_value9 + ? "; }
		else if(product_no == 10) { sql = sql +  "   data_value10 = data_value10 + ? "; }
		else if(product_no == 11) { sql = sql +  "   data_value11 = data_value11 + ? "; }
		else if(product_no == 12) { sql = sql +  "   data_value12 = data_value12 + ? "; }
		else if(product_no == 13) { sql = sql +  "   data_value13 = data_value13 + ? "; }
		else if(product_no == 14) { sql = sql +  "   data_value14 = data_value14 + ? "; }
		else if(product_no == 15) { sql = sql +  "   data_value15 = data_value15 + ? "; }
		else if(product_no == 16) { sql = sql +  "   data_value16 = data_value16 + ? "; }
		else if(product_no == 17) { sql = sql +  "   data_value17 = data_value17 + ? "; }
		else if(product_no == 18) { sql = sql +  "   data_value18 = data_value18 + ? "; }
		else if(product_no == 19) { sql = sql +  "   data_value19 = data_value19 + ? "; }
		else if(product_no == 20) { sql = sql +  "   data_value20 = data_value20 + ? "; }

	
		
		sql = sql + " ,data_plus =  data_plus + ? WHERE data_id = ? ";
	   jdbcTemplate.update(sql,new Object[]{cal_value,cal_value,data_id});
		

		
	}
	
	
	
	@Override
	public void setTotal(String datetime,String cal_value,long product_no,int data_id) {
	
		
		 String sql = "UPDATE edata SET data_dateupdate= ? ";
	 
	 
		if(product_no == 1) { sql = sql +  "   ,data_value1 =  ? "; }
		else if(product_no == 2) { sql = sql +  "   ,data_value2 =  ? "; }
		else if(product_no == 3) { sql = sql +  "   ,data_value3 =  ? "; }
		else if(product_no == 4) { sql = sql +  "   ,data_value4 =  ? "; }
		else if(product_no == 5) { sql = sql +  "   ,data_value5 =  ? "; }
		else if(product_no == 6) { sql = sql +  "   ,data_value6 =  ? "; }
		else if(product_no == 7) { sql = sql +  "   ,data_value7 =  ? "; }
		else if(product_no == 8) { sql = sql +  "   ,data_value8 =  ? "; }
		else if(product_no == 9) { sql = sql +  "   ,data_value9 =  ? "; }
		else if(product_no == 10) { sql = sql +  "   ,data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  "   ,data_value11 =  ? "; }
		else if(product_no == 12) { sql = sql +  "   ,data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  "   ,data_value13 =  ? "; }
		else if(product_no == 14) { sql = sql +  "   ,data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  "   ,data_value15 =  ? "; }
		else if(product_no == 16) { sql = sql +  "   ,data_value16 =  ? "; }
		else if(product_no == 17) { sql = sql +  "   ,data_value17 =  ? "; }
		else if(product_no == 18) { sql = sql +  "   ,data_value18 =  ? "; }
		else if(product_no == 19) { sql = sql +  "   ,data_value19 =  ? "; }
		else if(product_no == 20) { sql = sql +  "   ,data_value20 = ? "; }

	
		
	
		 try {
	            // ล็อก Semaphore เป็นเวลา 1 นาที (60,000 มิลลิวินาที)
	            if (idLock.tryAcquire(1, 60000, TimeUnit.MILLISECONDS)) {
	            	sql = sql + " WHERE data_id = ? ";
	         	   jdbcTemplate.update(sql,new Object[]{datetime,cal_value,data_id});
	         		
	            } else {
	                // ไม่สามารถล็อกได้ภายใน 1 นาที
	                throw new RuntimeException("Timeout while waiting for the lock.");
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            throw new RuntimeException("Lock interrupted.");
	        } finally {
	            idLock.release();
	        }
		
		
		
	}
	
	@Override
	public edata getSum(int id,int rv,long product_no) {
	
		
		
		String sub = null;
		if(product_no == 1) { sub = " SUM(data_value1) as  data_value1 "; }
		else if(product_no == 2) { sub = "  SUM(data_value2) as data_value1";  }
		else if(product_no == 3) {  sub = "  SUM(data_value3) as data_value1"; }
		else if(product_no == 4) {  sub = "  SUM(data_value4) as data_value1"; }
		else if(product_no == 5) { sub = "  SUM(data_value5) as data_value1"; }
		else if(product_no == 6) {  sub = "  SUM(data_value6) as data_value1"; }
		else if(product_no == 7) {  sub = "  SUM(data_value7) as data_value1";}
		else if(product_no == 8) {  sub = "  SUM(data_value8) as data_value1"; }
		else if(product_no == 9) {  sub = "  SUM(data_value9) as data_value1"; }
		else if(product_no == 10) {  sub = "  SUM(data_value10) as data_value1"; }
		else if(product_no == 11) {  sub = "  SUM(data_value11) as data_value1"; }
		else if(product_no == 12) {  sub = "  SUM(data_value12) as data_value1"; }
		else if(product_no == 13) {  sub = "  SUM(data_value13) as data_value1"; }
		else if(product_no == 14) {  sub = "  SUM(data_value14) as data_value1";}
		else if(product_no == 15) {  sub = "  SUM(data_value15) as data_value1"; }
		else if(product_no == 16) {  sub = "  SUM(data_value16) as data_value1"; }
		else if(product_no == 17) {  sub = "  SUM(data_value17) as data_value1"; }
		else if(product_no == 18) {  sub = "  SUM(data_value18) as data_value1";}
		else if(product_no == 19) {  sub = "  SUM(data_value19) as data_value1"; }
		else if(product_no == 20) {  sub = "  SUM(data_value20) as data_value1"; }
		
	    String sql = "select "+sub+",edata.* FROM  edata WHERE data_eformid = ?  and data_eformrv =?  ";
		edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv},new edatamappers());
		return edatas;

		
	}


	@Override
	public edata getTotal(long product_no,int data_id) {
	
		
		
		String sub = null;
		if(product_no == 1) { sub = " data_value1  "; }
		else if(product_no == 2) { sub = " data_value2 as data_value1";  }
		else if(product_no == 3) {  sub = " data_value3 as data_value1"; }
		else if(product_no == 4) {  sub = " data_value4 as data_value1"; }
		else if(product_no == 5) { sub = " data_value5 as data_value1"; }
		else if(product_no == 6) {  sub = " data_value6 as data_value1"; }
		else if(product_no == 7) {  sub = " data_value7 as data_value1";}
		else if(product_no == 8) {  sub = " data_value8 as data_value1"; }
		else if(product_no == 9) {  sub = " data_value9 as data_value1"; }
		else if(product_no == 10) {  sub = " data_value10 as data_value1"; }
		else if(product_no == 11) {  sub = " data_value11 as data_value1"; }
		else if(product_no == 12) {  sub = " data_value12 as data_value1"; }
		else if(product_no == 13) {  sub = " data_value13 as data_value1"; }
		else if(product_no == 14) {  sub = " data_value14 as data_value1";}
		else if(product_no == 15) {  sub = " data_value15 as data_value1"; }
		else if(product_no == 16) {  sub = " data_value16 as data_value1"; }
		else if(product_no == 17) {  sub = " data_value17 as data_value1"; }
		else if(product_no == 18) {  sub = " data_value18 as data_value1";}
		else if(product_no == 19) {  sub = " data_value19 as data_value1"; }
		else if(product_no == 20) {  sub = " data_value20 as data_value1"; }
		
	    String sql = "select "+sub+",edata.* FROM  edata WHERE data_id = ? ";
		edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{data_id},new edatamappers());
		return edatas;

		
	}

	@Override
	public int setcal_minus(String cal_value,long product_no,int data_id) {
	
		System.out.println("cal_value" + cal_value);
		System.out.println("product_no" + product_no);
		System.out.println("data_id" + data_id);
		
		
		 String sql = "UPDATE edata SET ";
		 String data = " ";
	 
		if(product_no == 1) { sql = sql +  "   data_value1 =  data_value1 - ? ";   data = " data_value1 >= ? "; }
		else if(product_no == 2) { sql = sql +  "   data_value2 = data_value2 - ? "; data = " data_value2 >= ? ";  }
		else if(product_no == 3) { sql = sql +  "   data_value3 = data_value3 - ? ";  data = " data_value3 >= ? "; }
		else if(product_no == 4) { sql = sql +  "   data_value4 = data_value4 - ? ";  data = " data_value4 >= ? "; }
		else if(product_no == 5) { sql = sql +  "   data_value5 = data_value5 - ? ";  data = " data_value5 >= ? "; }
		else if(product_no == 6) { sql = sql +  "   data_value6 = data_value6 - ? ";  data = " data_value6 >= ? "; }
		else if(product_no == 7) { sql = sql +  "   data_value7 = data_value7 - ? "; data = " data_value7 >= ? "; }
		else if(product_no == 8) { sql = sql +  "   data_value8 = data_value8 - ? "; data = " data_value8 >= ?  "; }
		else if(product_no == 9) { sql = sql +  "   data_value9 = data_value9 - ? "; data = " data_value9 >= ? "; }
		else if(product_no == 10) { sql = sql +  "   data_value10 = data_value10 - ? "; data = " data_value10 >= ? "; }
		else if(product_no == 11) { sql = sql +  "   data_value11 = data_value11 - ? "; data = " data_value11 >= ? "; }
		else if(product_no == 12) { sql = sql +  "   data_value12 = data_value12 - ? "; data = " data_value12 >= ? "; }
		else if(product_no == 13) { sql = sql +  "   data_value13 = data_value13 - ? "; data = " data_value13 >= ? "; }
		else if(product_no == 14) { sql = sql +  "   data_value14 = data_value14 - ? "; data = " data_value14 >= ? "; }
		else if(product_no == 15) { sql = sql +  "   data_value15 = data_value15 - ? "; data = " data_value15 >= ? "; }
		else if(product_no == 16) { sql = sql +  "   data_value16 = data_value16 - ? "; data = " data_value16 >= ?"; }
		else if(product_no == 17) { sql = sql +  "   data_value17 = data_value17 - ? "; data = " data_value17 >= ?"; }
		else if(product_no == 18) { sql = sql +  "   data_value18 = data_value18 - ? "; data = " data_value18 >= ?"; }
		else if(product_no == 19) { sql = sql +  "   data_value19 = data_value19 - ? "; data = " data_value19 >= ?"; }
		else if(product_no == 20) { sql = sql +  "   data_value20 = data_value20 - ? "; data = " data_value20 >= ?"; }

	
		
		sql = sql + ",data_minus =  data_minus + ?  WHERE data_id = ?  and "+data;
		System.out.println("sql" + sql);

	  return  jdbcTemplate.update(sql,new Object[]{cal_value,cal_value,data_id,cal_value});
		

		
	}
	
	
	
	
	
	@Override
	public edata getbycal(String product,long product_no,String lot,long lot_no,long port,long rv) {
	
		
		 String sql = "SELECT * FROM edata  ";
	 		sql = sql + " WHERE data_eformid = ?  and data_eformrv =? ";
		
	 		
		if(product_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  data_value20 = ? "; }

	if(lot != null) {	
		if(lot_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(lot_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(lot_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(lot_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(lot_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(lot_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(lot_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(lot_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(lot_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(lot_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(lot_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(lot_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(lot_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(lot_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(lot_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(lot_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(lot_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(lot_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(lot_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(lot_no == 20) { sql = sql +  " and  data_value20 = ? "; }
	}
		

		  try {
			  
						  if(lot == null) {
							  
								edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{port,rv,product},new edatamappers());
								return edatas;
						  }else {
								edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{port,rv,product,lot},new edatamappers());
								return edatas;  
						  }

		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbycal with the same Id .......");
		   
		 }
	
		
		
	}
	
	
	@Override
	public edata getCal(int id,int rv,String value, long product_no,long data_no) {
	
		
		 String sub =null;

	
			if(data_no == 1) { sub = " data_value1 "; }
			else if(data_no == 2) { sub = " data_value2 as data_value1";  }
			else if(data_no == 3) {  sub = " data_value3 as data_value1"; }
			else if(data_no == 4) {  sub = " data_value4 as data_value1"; }
			else if(data_no == 5) { sub = " data_value5 as data_value1"; }
			else if(data_no == 6) {  sub = " data_value6 as data_value1"; }
			else if(data_no == 7) {  sub = " data_value7 as data_value1";}
			else if(data_no == 8) {  sub = " data_value8 as data_value1"; }
			else if(data_no == 9) {  sub = " data_value9 as data_value1"; }
			else if(data_no == 10) {  sub = " data_value10 as data_value1"; }
			else if(data_no == 11) {  sub = " data_value11 as data_value1"; }
			else if(data_no == 12) {  sub = " data_value12 as data_value1"; }
			else if(data_no == 13) {  sub = " data_value13 as data_value1"; }
			else if(data_no == 14) {  sub = " data_value14 as data_value1";}
			else if(data_no == 15) {  sub = " data_value15 as data_value1"; }
			else if(data_no == 16) {  sub = " data_value16 as data_value1"; }
			else if(data_no == 17) {  sub = " data_value17 as data_value1"; }
			else if(data_no == 18) {  sub = " data_value18 as data_value1";}
			else if(data_no == 19) {  sub = " data_value19 as data_value1"; }
			else if(data_no == 20) {  sub = " data_value20 as data_value1"; }

			

		 String sql = "SELECT "+sub+",edata.* FROM edata  ";
		
		
	
	 		sql = sql + " WHERE data_eformid = ? and  data_eformrv = ?   ";
		
	 		
		if(product_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  data_value20 = ? "; }

		  try {
			   System.out.println("sql "+sql);
			   System.out.println("id "+id);
			   System.out.println("rv "+rv);
			   System.out.println("value "+value);
			   
			   sql = sql + "  limit 1   ";
								edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,value},new edatamappers());
								return edatas;
					

		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getCal with the same Id .......");
		   
		 }
	
		
		
	}
	
	

	@Override
	public edata getReserve(int id,int rv,String value,long product_no,long data_no) {
	
		
		 String sub =null;
	
		 
	
			if(data_no == 1) { sub = " SUM(edata.data_value1) as data_value1 "; }
			else if(data_no == 2) { sub = " SUM(edata.data_value2) as data_value1";  }
			else if(data_no == 3) {  sub = " SUM(edata.data_value3) as data_value1"; }
			else if(data_no == 4) {  sub = " SUM(edata.data_value4) as data_value1"; }
			else if(data_no == 5) { sub = " SUM(edata.data_value5) as data_value1"; }
			else if(data_no == 6) {  sub = " SUM(edata.data_value6) as data_value1"; }
			else if(data_no == 7) {  sub = " SUM(edata.data_value7) as data_value1";}
			else if(data_no == 8) {  sub = " SUM(edata.data_value8) as data_value1"; }
			else if(data_no == 9) {  sub = " SUM(edata.data_value9) as data_value1"; }
			else if(data_no == 10) {  sub = " SUM(edata.data_value10) as data_value1"; }
			else if(data_no == 11) {  sub = " SUM(edata.data_value11) as data_value1"; }
			else if(data_no == 12) {  sub = " SUM(edata.data_value12) as data_value1"; }
			else if(data_no == 13) {  sub = " SUM(edata.data_value13) as data_value1"; }
			else if(data_no == 14) {  sub = " SUM(edata.data_value14) as data_value1";}
			else if(data_no == 15) {  sub = " SUM(edata.data_value15) as data_value1"; }
			else if(data_no == 16) {  sub = " SUM(edata.data_value16) as data_value1"; }
			else if(data_no == 17) {  sub = " SUM(edata.data_value17) as data_value1"; }
			else if(data_no == 18) {  sub = " SUM(edata.data_value18) as data_value1";}
			else if(data_no == 19) {  sub = " SUM(edata.data_value19) as data_value1"; }
			else if(data_no == 20) {  sub = " SUM(edata.data_value20) as data_value1"; }

			

		 String sql = "SELECT "+sub+",edata.* FROM edata  LEFT JOIN log ON edata.data_filepath = log.log_id ";
	 	 sql = sql + " WHERE edata.data_eformid = ? and  edata.data_eformrv = ?  and log.log_flow =0 ";
		
	 		
		if(product_no == 1) { sql = sql +  " and  edata.data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  " and  edata.data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  edata.data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  edata.data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  " and  edata.data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  edata.data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  edata.data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  " and  edata.data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  " and  edata.data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  " and  edata.data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  " and  edata.data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  " and  edata.data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  edata.data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  edata.data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  " and  edata.data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  " and  edata.data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  " and  edata.data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  " and  edata.data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  " and  edata.data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  edata.data_value20 = ? "; }

		  try {
			   System.out.println("sql "+sql);
			   System.out.println("id "+id);
			   System.out.println("rv "+rv);
			   System.out.println("value "+value);
			   
			   sql = sql + "  limit 1   ";
								edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,value},new edatamappers());
								return edatas;
					

		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getCal with the same Id .......");
		   
		 }
	
		
		
	}
	
	
	
	@Override
	public edata getReserve(int id,int rv,String value,String lot,long product_no,long lot_no,long data_no) {
	
		
		 String sub =null;

		 
	
			if(data_no == 1) { sub = " SUM(edata.data_value1) as data_value1 "; }
			else if(data_no == 2) { sub = " SUM(edata.data_value2) as data_value1";  }
			else if(data_no == 3) {  sub = " SUM(edata.data_value3) as data_value1"; }
			else if(data_no == 4) {  sub = " SUM(edata.data_value4) as data_value1"; }
			else if(data_no == 5) { sub = " SUM(edata.data_value5) as data_value1"; }
			else if(data_no == 6) {  sub = " SUM(edata.data_value6) as data_value1"; }
			else if(data_no == 7) {  sub = " SUM(edata.data_value7) as data_value1";}
			else if(data_no == 8) {  sub = " SUM(edata.data_value8) as data_value1"; }
			else if(data_no == 9) {  sub = " SUM(edata.data_value9) as data_value1"; }
			else if(data_no == 10) {  sub = " SUM(edata.data_value10) as data_value1"; }
			else if(data_no == 11) {  sub = " SUM(edata.data_value11) as data_value1"; }
			else if(data_no == 12) {  sub = " SUM(edata.data_value12) as data_value1"; }
			else if(data_no == 13) {  sub = " SUM(edata.data_value13) as data_value1"; }
			else if(data_no == 14) {  sub = " SUM(edata.data_value14) as data_value1";}
			else if(data_no == 15) {  sub = " SUM(edata.data_value15) as data_value1"; }
			else if(data_no == 16) {  sub = " SUM(edata.data_value16) as data_value1"; }
			else if(data_no == 17) {  sub = " SUM(edata.data_value17) as data_value1"; }
			else if(data_no == 18) {  sub = " SUM(edata.data_value18) as data_value1";}
			else if(data_no == 19) {  sub = " SUM(edata.data_value19) as data_value1"; }
			else if(data_no == 20) {  sub = " SUM(edata.data_value20) as data_value1"; }

			

		 String sql = "SELECT "+sub+",edata.* FROM edata  LEFT JOIN log ON edata.data_filepath = log.log_id ";
	 	 sql = sql + " WHERE edata.data_eformid = ? and  edata.data_eformrv = ?  and log.log_flow =0 ";
		
	 		
		if(product_no == 1) { sql = sql +  " and  edata.data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  " and  edata.data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  edata.data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  edata.data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  " and  edata.data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  edata.data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  edata.data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  " and  edata.data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  " and  edata.data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  " and  edata.data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  " and  edata.data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  " and  edata.data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  edata.data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  edata.data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  " and  edata.data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  " and  edata.data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  " and  edata.data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  " and  edata.data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  " and  edata.data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  edata.data_value20 = ? "; }

		if(lot_no == 1) { sql = sql +  " and  edata.data_value1 = ? "; }
		else if(lot_no == 2) { sql = sql +  " and  edata.data_value2 = ? "; }
		else if(lot_no == 3) { sql = sql +  " and  edata.data_value3 = ? "; }
		else if(lot_no == 4) { sql = sql +  " and  edata.data_value4 = ? "; }
		else if(lot_no == 5) { sql = sql +  " and  edata.data_value5 = ? "; }
		else if(lot_no == 6) { sql = sql +  " and  edata.data_value6 = ? "; }
		else if(lot_no == 7) { sql = sql +  " and  edata.data_value7 = ? "; }
		else if(lot_no == 8) { sql = sql +  " and  edata.data_value8 = ? "; }
		else if(lot_no == 9) { sql = sql +  " and  edata.data_value9 = ? "; }
		else if(lot_no == 10) { sql = sql +  " and  edata.data_value10 = ? "; }
		else if(lot_no == 11) { sql = sql +  " and  edata.data_value11 = ? "; }
		else if(lot_no == 12) { sql = sql +  " and  edata.data_value12 = ? "; }
		else if(lot_no == 13) { sql = sql +  " and  edata.data_value13 = ? "; }
		else if(lot_no == 14) { sql = sql +  " and  edata.data_value14 = ? "; }
		else if(lot_no == 15) { sql = sql +  " and  edata.data_value15 = ? "; }
		else if(lot_no == 16) { sql = sql +  " and  edata.data_value16 = ? "; }
		else if(lot_no == 17) { sql = sql +  " and  edata.data_value17 = ? "; }
		else if(lot_no == 18) { sql = sql +  " and  edata.data_value18 = ? "; }
		else if(lot_no == 19) { sql = sql +  " and  edata.data_value19 = ? "; }
		else if(lot_no == 20) { sql = sql +  " and  edata.data_value20 = ? "; }

		
		
		  try {
			   System.out.println("sql "+sql);
			   System.out.println("id "+id);
			   System.out.println("rv "+rv);
			   System.out.println("value "+value);
			   
			   sql = sql + "  limit 1   ";
								edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,value,lot},new edatamappers());
								return edatas;
					

		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getCal with the same Id .......");
		   
		 }
	
		
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public edata getCal(int id,int rv,String value, long product_no,String value2, long log_no,long data_no) {
	
		
		 String sub =null;

	
			if(data_no == 1) { sub = " data_value1 "; }
			else if(data_no == 2) { sub = " data_value2 as data_value1";  }
			else if(data_no == 3) {  sub = " data_value3 as data_value1"; }
			else if(data_no == 4) {  sub = " data_value4 as data_value1"; }
			else if(data_no == 5) { sub = " data_value5 as data_value1"; }
			else if(data_no == 6) {  sub = " data_value6 as data_value1"; }
			else if(data_no == 7) {  sub = " data_value7 as data_value1";}
			else if(data_no == 8) {  sub = " data_value8 as data_value1"; }
			else if(data_no == 9) {  sub = " data_value9 as data_value1"; }
			else if(data_no == 10) {  sub = " data_value10 as data_value1"; }
			else if(data_no == 11) {  sub = " data_value11 as data_value1"; }
			else if(data_no == 12) {  sub = " data_value12 as data_value1"; }
			else if(data_no == 13) {  sub = " data_value13 as data_value1"; }
			else if(data_no == 14) {  sub = " data_value14 as data_value1";}
			else if(data_no == 15) {  sub = " data_value15 as data_value1"; }
			else if(data_no == 16) {  sub = " data_value16 as data_value1"; }
			else if(data_no == 17) {  sub = " data_value17 as data_value1"; }
			else if(data_no == 18) {  sub = " data_value18 as data_value1";}
			else if(data_no == 19) {  sub = " data_value19 as data_value1"; }
			else if(data_no == 20) {  sub = " data_value20 as data_value1"; }

			
			
			

		 String sql = "SELECT "+sub+",edata.* FROM edata  ";
		
		
	
	 		sql = sql + " WHERE data_eformid = ? and  data_eformrv = ?   ";
		
	 		
		if(product_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  data_value20 = ? "; }

		
		
		if(log_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(log_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(log_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(log_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(log_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(log_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(log_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(log_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(log_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(log_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(log_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(log_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(log_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(log_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(log_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(log_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(log_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(log_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(log_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(log_no == 20) { sql = sql +  " and  data_value20 = ? "; }
		
		  try {
			  
			  System.out.println("getCal +++");	
								edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,value,value2},new edatamappers());
								return edatas;
					

		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getCal with the same Id .......");
		   
		 }
	
		
		
	}
	
	
	
	
	@Override
	public edata getAmount(int id,int rv,String value, long product_no,String value2, long log_no,long data_no) {

		
		   String sub =null;

	
			if(data_no == 1) { sub = " SUM(data_value1) as data_value1 "; }
			else if(data_no == 2) { sub = " SUM(data_value2) as data_value1";  }
			else if(data_no == 3) {  sub = " SUM(data_value3) as data_value1"; }
			else if(data_no == 4) {  sub = " SUM(data_value4) as data_value1"; }
			else if(data_no == 5) { sub = " SUM(data_value5) as data_value1"; }
			else if(data_no == 6) {  sub = " SUM(data_value6) as data_value1"; }
			else if(data_no == 7) {  sub = " SUM(data_value7) as data_value1";}
			else if(data_no == 8) {  sub = " SUM(data_value8) as data_value1"; }
			else if(data_no == 9) {  sub = " SUM(data_value9) as data_value1"; }
			else if(data_no == 10) {  sub = " SUM(data_value10) as data_value1"; }
			else if(data_no == 11) {  sub = " SUM(data_value11) as data_value1"; }
			else if(data_no == 12) {  sub = " SUM(data_value12) as data_value1"; }
			else if(data_no == 13) {  sub = " SUM(data_value13) as data_value1"; }
			else if(data_no == 14) {  sub = " SUM(data_value14) as data_value1";}
			else if(data_no == 15) {  sub = " SUM(data_value15) as data_value1"; }
			else if(data_no == 16) {  sub = " SUM(data_value16) as data_value1"; }
			else if(data_no == 17) {  sub = " SUM(data_value17) as data_value1"; }
			else if(data_no == 18) {  sub = " SUM(data_value18) as data_value1";}
			else if(data_no == 19) {  sub = " SUM(data_value19) as data_value1"; }
			else if(data_no == 20) {  sub = " SUM(data_value20) as data_value1"; }

			
			
			

		 String sql = "SELECT "+sub+",edata.* FROM edata  ";
		
		
	
	 		sql = sql + " WHERE data_eformid = ? and  data_eformrv = ?   ";
		
	 		
		if(product_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(product_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  data_value20 = ? "; }

		
		
		if(log_no == 1) { sql = sql +  " and  data_value1 = ? "; }
		else if(log_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(log_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(log_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(log_no == 5) { sql = sql +  " and  data_value5 = ? "; }
		else if(log_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(log_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(log_no == 8) { sql = sql +  " and  data_value8 = ? "; }
		else if(log_no == 9) { sql = sql +  " and  data_value9 = ? "; }
		else if(log_no == 10) { sql = sql +  " and  data_value10 = ? "; }
		else if(log_no == 11) { sql = sql +  " and  data_value11 = ? "; }
		else if(log_no == 12) { sql = sql +  " and  data_value12 = ? "; }
		else if(log_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(log_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(log_no == 15) { sql = sql +  " and  data_value15 = ? "; }
		else if(log_no == 16) { sql = sql +  " and  data_value16 = ? "; }
		else if(log_no == 17) { sql = sql +  " and  data_value17 = ? "; }
		else if(log_no == 18) { sql = sql +  " and  data_value18 = ? "; }
		else if(log_no == 19) { sql = sql +  " and  data_value19 = ? "; }
		else if(log_no == 20) { sql = sql +  " and  data_value20 = ? "; }
		
		
		sql = sql + " Limit 1   "; 
		
		  try {
			  
			  System.out.println("Amount +++");
			  
					if(log_no > 0) {
						edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,value,value2},new edatamappers());
						return edatas;
					}else {
						edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id,rv,value},new edatamappers());
						return edatas;
					}
							
					

		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getAmount with the same Id .......");
		   
		 }
	
		
		
	}
	
	@Override
	public int delete(int id) {
		 String sql="delete from edata where  data_id = ?  ";    
		    return jdbcTemplate.update(sql,new Object[]{id});
	}
	@Override
	public int softDelete(int id,String soft_delete) {
		 String sql="update edata  set  soft_delete = ?  where  data_id = ?  ";    
		    return jdbcTemplate.update(sql,new Object[]{soft_delete,id});
	}

	@Override
	public int cancelDelete(int id) {
		 String sql="update edata  set  soft_delete = NULL  where  data_id = ?  ";    
		   return jdbcTemplate.update(sql,new Object[]{id});
	}
	
	@Override
	public void setFollowUp(int data_id, int status) {
		 String sql="update edata  set  followUp = ?  where  data_id = ?  ";    
		    jdbcTemplate.update(sql,new Object[]{status,data_id});
	}
	
	@Override
	public void setAcknowledge(int data_id, int status, int acknowledge_user) {
		
		// 1. สร้าง Format วันเวลาปัจจุบันให้ออกมาเป็น String
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String currentDateStr = LocalDateTime.now().format(dtf);
		
		// 2. เพิ่มฟิลด์ data_date_receiver เข้าไปในคำสั่ง SQL
		String sql = "UPDATE edata SET acknowledge = ?, acknowledge_user = ?, data_date_receiver = ? WHERE data_id = ?";    
		
		// 3. ยัดค่า currentDateStr ลงไปใน Array ให้ตรงกับเครื่องหมาย ? ตัวที่ 3
		jdbcTemplate.update(sql, new Object[]{
				status, 
				acknowledge_user, 
				currentDateStr, // ส่งค่า String วันเวลาเข้าไป
				data_id
		});
	}
	
	@Override
	public void  setReferenceProcess(String reference, int id) {
		 String sql="update process  set  reference = ?  where  id = ?  ";    
		    jdbcTemplate.update(sql,new Object[]{reference,id});
	}
	
	
	
	@Override
	public int deletebyfilepath(int id) {
		 String sql="delete from edata where  data_filepath = ?  ";    
		    return jdbcTemplate.update(sql,new Object[]{id});
	}

	@Override
	public int copyEdataHeader(int id) {
			edata head = getbyid(id);
			head.setData_id(0);
	        int newId = save(head);
		    return newId;
	}
	
	
	@Override
	public void copyEdataChildren(int oldEdataId, int newEdataId) {
				
		List<edata> oldChildren = getAllSubData(oldEdataId);
		savesBatch(oldChildren,newEdataId);
	}
	
	
	
	@Override
	public int save(edata edatas) {
	
		
	
		System.out.println("Data_id " + 	edatas.getData_id());
		
		
		

		if(edatas.getData_id() > 0) {
			
			
			Object[] edataInfo = { 
				    edatas.getData_planid(),
				    edatas.getReport_id(),
				    edatas.getData_eformid(),
				    edatas.getData_eformrv(),
				    edatas.getData_idadd(),
				    edatas.getData_nameadd(),
				    edatas.getData_namerefer(),
				    edatas.getData_dateadd(),
				    edatas.getData_dateplan(),
				    edatas.getData_plantype(),
				    edatas.getData_filetype(),
				    edatas.getData_filepath(),
				    edatas.getData_idapp(),
				    edatas.getData_nameapp(),
				    edatas.getData_value1(),
				    edatas.getData_value2(),
				    edatas.getData_value3(),
				    edatas.getData_value4(),
				    edatas.getData_value5(),
				    edatas.getData_value6(),
				    edatas.getData_value7(),
				    edatas.getData_value8(),
				    edatas.getData_value9(),
				    edatas.getData_value10(),
				    edatas.getData_value11(),
				    edatas.getData_value12(),
				    edatas.getData_value13(),
				    edatas.getData_value14(),
				    edatas.getData_value15(),
				    edatas.getData_value16(),
				    edatas.getData_value17(),
				    edatas.getData_value18(),
				    edatas.getData_value19(),
				    edatas.getData_value20(),
				    edatas.getData_value21(), // เพิ่ม 21
				    edatas.getData_value22(),
				    edatas.getData_value23(),
				    edatas.getData_value24(),
				    edatas.getData_value25(),
				    edatas.getData_value26(),
				    edatas.getData_value27(),
				    edatas.getData_value28(),
				    edatas.getData_value29(),
				    edatas.getData_value30(), // ถึง 30
				    edatas.getData_due(),
				    edatas.getData_cal_type(),
				    edatas.getData_cal_no(),
				    edatas.getData_cal_no2(),
				    edatas.getData_cal_conditionid(),
				    edatas.getData_join(),
				    edatas.getData_cal(),
				    edatas.getData_department(),
				    edatas.getData_status(),
				    edatas.getDetails_clearing(),
				    edatas.getDetails(),
				    edatas.getData_title(),
				    edatas.getDate_clear(),
				    0, 
				};
			String sql = "UPDATE edata SET data_planid=?, report_id=?, data_eformid=?, data_eformrv=?, data_idadd=?, data_nameadd=?, data_namerefer=?, data_dateadd=?"
			        + ", data_dateplan=?, data_plantype=?, data_filetype=?, data_filepath=?, data_idapp=?, data_nameapp=?"
			        + ", data_value1=?, data_value2=?, data_value3=?, data_value4=?, data_value5=?"
			        + ", data_value6=?, data_value7=?, data_value8=?, data_value9=?, data_value10=?"
			        + ", data_value11=?, data_value12=?, data_value13=?, data_value14=?, data_value15=?"
			        + ", data_value16=?, data_value17=?, data_value18=?, data_value19=?, data_value20=?"
			        + ", data_value21=?, data_value22=?, data_value23=?, data_value24=?, data_value25=?"
			        + ", data_value26=?, data_value27=?, data_value28=?, data_value29=?, data_value30=?"
			        + ", data_due=?"
			        + ", data_cal_type=?, data_cal_no=?, data_cal_no2=?, data_cal_conditionid=?, data_join=?, data_cal=?, data_department=?, data_status=?"
			        + ", details_clearing=?, details=?, data_title=?, date_clear=?, data_flow=?"
			        + " WHERE data_id =" + edatas.getData_id();
			

			
			return jdbcTemplate.update(sql,edataInfo);
		}else {
			
			Object[] edataInfo = { 
				    edatas.getData_planid(),
				    edatas.getReport_id(),
				    edatas.getData_eformid(),
				    edatas.getData_eformrv(),
				    edatas.getData_idadd(),
				    edatas.getData_nameadd(),
				    edatas.getData_namerefer(),
				    edatas.getData_dateadd(),
				    edatas.getData_dateplan(),
				    edatas.getData_plantype(),
				    edatas.getData_filetype(),
				    edatas.getData_filepath(),
				    edatas.getData_idapp(),
				    edatas.getData_nameapp(),
				    edatas.getData_value1(),
				    edatas.getData_value2(),
				    edatas.getData_value3(),
				    edatas.getData_value4(),
				    edatas.getData_value5(),
				    edatas.getData_value6(),
				    edatas.getData_value7(),
				    edatas.getData_value8(),
				    edatas.getData_value9(),
				    edatas.getData_value10(),
				    edatas.getData_value11(),
				    edatas.getData_value12(),
				    edatas.getData_value13(),
				    edatas.getData_value14(),
				    edatas.getData_value15(),
				    edatas.getData_value16(),
				    edatas.getData_value17(),
				    edatas.getData_value18(),
				    edatas.getData_value19(),
				    edatas.getData_value20(),
				    edatas.getData_value21(), // เพิ่ม 21
				    edatas.getData_value22(),
				    edatas.getData_value23(),
				    edatas.getData_value24(),
				    edatas.getData_value25(),
				    edatas.getData_value26(),
				    edatas.getData_value27(),
				    edatas.getData_value28(),
				    edatas.getData_value29(),
				    edatas.getData_value30(), // ถึง 30
				    edatas.getData_due(),
				    edatas.getData_cal_type(),
				    edatas.getData_cal_no(),
				    edatas.getData_cal_no2(),
				    edatas.getData_cal_conditionid(),
				    edatas.getData_join(),
				    edatas.getData_cal(),
				    edatas.getData_department(),
				    edatas.getData_status(),
				    edatas.getDetails_clearing(),
				    edatas.getDetails(),
				    edatas.getData_title(),
				    edatas.getDate_clear(),
				    0, 
				    edatas.getProcesses_id()
				};
			
			String sql = "INSERT INTO edata("
			        + "data_planid, report_id, data_eformid, data_eformrv, data_idadd, data_nameadd, data_namerefer, data_dateadd"
			        + ", data_dateplan, data_plantype, data_filetype, data_filepath, data_idapp, data_nameapp"
			        + ", data_value1, data_value2, data_value3, data_value4, data_value5"
			        + ", data_value6, data_value7, data_value8, data_value9, data_value10"
			        + ", data_value11, data_value12, data_value13, data_value14, data_value15"
			        + ", data_value16, data_value17, data_value18, data_value19, data_value20"
			        + ", data_value21, data_value22, data_value23, data_value24, data_value25"
			        + ", data_value26, data_value27, data_value28, data_value29, data_value30"
			        + ", data_due"
			        + ", data_cal_type, data_cal_no, data_cal_no2, data_cal_conditionid, data_join, data_cal, data_department, data_status"
			        + ", details_clearing, details, data_title, date_clear, data_flow,process_id"
			        + " ) "
			        + "VALUES("
			        + "?,?,?,?,?,?,?,?,?,?," // 10
			        + "?,?,?,?,?,?,?,?,?,?," // 20
			        + "?,?,?,?,?,?,?,?,?,?," // 30
			        + "?,?,?,?,?,?,?,?,?,?," // 40
			        + "?,?,?,?,?,?,?,?,?,?," // 50
			        + "?,?,?,?,?,?,?,?,?"    // 9 (รวม 59 ตัวพอดี)
			        + ")";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
				System.out.println("length " + edataInfo.length);
			
			
			jdbcTemplate.update(connection -> {
			    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			    for (int i = 0; i < edataInfo.length; i++) {
			        ps.setObject(i + 1, edataInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
			    }
			    return ps;
			}, keyHolder);

			// คืนค่า id ที่ถูกสร้างขึ้น
			return keyHolder.getKey().intValue();
		}
		
		

		
	}

	
	@Override
	public void savesBatch(List<edata> toInsert,int  TempId) {
		

	    if (toInsert == null || toInsert.isEmpty()) {
	        return;
	    }
	    
	    
	    

	    // 1. เตรียมวันที่ (ทำครั้งเดียวใช้กับทุก row)
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String date = localDate.format(formatter);

	
	 
	        String insertSql = "INSERT INTO edata("
	                + "data_idadd,data_nameadd,data_dateadd"
	                + ",data_value1,data_value2,data_value3,data_value4,data_value5"
	                + ",data_value6,data_value7,data_value8,data_value9,data_value10"
	                + ",data_value11,data_value12,data_value13,data_value14,data_value15"
	                + ",data_value16,data_value17,data_value18,data_value19,data_value20"
	                + ",data_value21,data_value22,data_value23,data_value24,data_value25,data_value26,data_value27,data_value28,data_value29,data_value30"
	                + ",process_id,data_title"
	                + ",id_refer,reference,extension_id,data_eformid,data_eformrv,tempId"
	                + "	) "
	                + "VALUES("
	                + "?,?,?,?,?,?,?,?,?,?,"
	                + "?,?,?,?,?,?,?,?,?,?,"
	                + "?,?,?,?,?,?,?,?,?,?,"
	                + "?,?,?,?,?,?,?,?,?,?,?)";

	        jdbcTemplate.batchUpdate(insertSql, toInsert, 100,
	            (PreparedStatement ps, edata item) -> {
	                int i = 1;
	                ps.setObject(i++, item.getData_idadd());
	                ps.setObject(i++, item.getData_nameadd());
	                ps.setObject(i++, date); // data_dateadd
	                
	                // Value 1-20
	                ps.setObject(i++, item.getData_value1());
	                ps.setObject(i++, item.getData_value2());
	                ps.setObject(i++, item.getData_value3());
	                ps.setObject(i++, item.getData_value4());
	                ps.setObject(i++, item.getData_value5());
	                ps.setObject(i++, item.getData_value6());
	                ps.setObject(i++, item.getData_value7());
	                ps.setObject(i++, item.getData_value8());
	                ps.setObject(i++, item.getData_value9());
	                ps.setObject(i++, item.getData_value10());
	                ps.setObject(i++, item.getData_value11());
	                ps.setObject(i++, item.getData_value12());
	                ps.setObject(i++, item.getData_value13());
	                ps.setObject(i++, item.getData_value14());
	                ps.setObject(i++, item.getData_value15());
	                ps.setObject(i++, item.getData_value16());
	                ps.setObject(i++, item.getData_value17());
	                ps.setObject(i++, item.getData_value18());
	                ps.setObject(i++, item.getData_value19());
	                ps.setObject(i++, item.getData_value20());
	                // Value 21-30
	                ps.setObject(i++, item.getData_value21());
	                ps.setObject(i++, item.getData_value22());
	                ps.setObject(i++, item.getData_value23());
	                ps.setObject(i++, item.getData_value24());
	                ps.setObject(i++, item.getData_value25());
	                ps.setObject(i++, item.getData_value26());
	                ps.setObject(i++, item.getData_value27());
	                ps.setObject(i++, item.getData_value28());
	                ps.setObject(i++, item.getData_value29());
	                ps.setObject(i++, item.getData_value30());

	                ps.setObject(i++, item.getProcesses_id());
	                ps.setObject(i++, item.getData_title());
	                ps.setObject(i++, item.getId_refer());
	                ps.setObject(i++, item.getReference());
	                ps.setObject(i++, item.getExtension_id());
	                ps.setObject(i++, item.getData_eformid());
	                ps.setObject(i++, item.getData_eformrv());
	                ps.setObject(i++, TempId);
	            });
	    
	}
	
	
	
	@Override
	public void savesBatch(List<BatchEdata> toInsert,String TempId) {
		

		
	   	deleteAllByTempId(TempId);

	    if (toInsert == null || toInsert.isEmpty()) {
	        return;
	    }
	    
	    
	    

	    // 1. เตรียมวันที่ (ทำครั้งเดียวใช้กับทุก row)
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String date = localDate.format(formatter);

	
	 
	        String insertSql = "INSERT INTO edata("
	                + "data_idadd,data_nameadd,data_dateadd"
	                + ",data_value1,data_value2,data_value3,data_value4,data_value5"
	                + ",data_value6,data_value7,data_value8,data_value9,data_value10"
	                + ",data_value11,data_value12,data_value13,data_value14,data_value15"
	                + ",data_value16,data_value17,data_value18,data_value19,data_value20"
	                + ",data_value21,data_value22,data_value23,data_value24,data_value25,data_value26,data_value27,data_value28,data_value29,data_value30"
	                + ",process_id,data_title"
	                + ",id_refer,reference,extension_id,data_eformid,data_eformrv,tempId"
	                + "	) "
	                + "VALUES("
	                + "?,?,?,?,?,?,?,?,?,?,"
	                + "?,?,?,?,?,?,?,?,?,?,"
	                + "?,?,?,?,?,?,?,?,?,?,"
	                + "?,?,?,?,?,?,?,?,?,?,?)";

	        jdbcTemplate.batchUpdate(insertSql, toInsert, 100,
	            (PreparedStatement ps, BatchEdata item) -> {
	                int i = 1;
	                ps.setObject(i++, item.getData_idadd());
	                ps.setObject(i++, item.getData_nameadd());
	                ps.setObject(i++, date); // data_dateadd
	                
	                // Value 1-20
	                ps.setObject(i++, item.getData_value1());
	                ps.setObject(i++, item.getData_value2());
	                ps.setObject(i++, item.getData_value3());
	                ps.setObject(i++, item.getData_value4());
	                ps.setObject(i++, item.getData_value5());
	                ps.setObject(i++, item.getData_value6());
	                ps.setObject(i++, item.getData_value7());
	                ps.setObject(i++, item.getData_value8());
	                ps.setObject(i++, item.getData_value9());
	                ps.setObject(i++, item.getData_value10());
	                ps.setObject(i++, item.getData_value11());
	                ps.setObject(i++, item.getData_value12());
	                ps.setObject(i++, item.getData_value13());
	                ps.setObject(i++, item.getData_value14());
	                ps.setObject(i++, item.getData_value15());
	                ps.setObject(i++, item.getData_value16());
	                ps.setObject(i++, item.getData_value17());
	                ps.setObject(i++, item.getData_value18());
	                ps.setObject(i++, item.getData_value19());
	                ps.setObject(i++, item.getData_value20());
	                // Value 21-30
	                ps.setObject(i++, item.getData_value21());
	                ps.setObject(i++, item.getData_value22());
	                ps.setObject(i++, item.getData_value23());
	                ps.setObject(i++, item.getData_value24());
	                ps.setObject(i++, item.getData_value25());
	                ps.setObject(i++, item.getData_value26());
	                ps.setObject(i++, item.getData_value27());
	                ps.setObject(i++, item.getData_value28());
	                ps.setObject(i++, item.getData_value29());
	                ps.setObject(i++, item.getData_value30());

	                ps.setObject(i++, item.getProcesses_id());
	                ps.setObject(i++, item.getData_title());
	                ps.setObject(i++, item.getId_refer());
	                ps.setObject(i++, item.getReference());
	                ps.setObject(i++, item.getExtension_id());
	                ps.setObject(i++, item.getData_eformid());
	                ps.setObject(i++, item.getData_eformrv());
	                ps.setObject(i++, item.getTempId());
	            });
	    
	}
	
	
	
	
	
	@Override
	public void deleteAllByTempId(String tempId) {
		

		
		  String deleteSql = "DELETE FROM edata WHERE tempId = ? ";
		  jdbcTemplate.update(deleteSql, new Object[] { tempId});
          
	}



	@Override
	public int saves(edata edatas) {
		
	
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	   	 String date = localDate.format(formatter);
		
		

		if(edatas.getData_id() > 0) {
			
			Object[] edataInfo = { 
					edatas.getData_idadd(),
					edatas.getData_value1(),
					edatas.getData_value2(),
					edatas.getData_value3(),
					edatas.getData_value4(),
					edatas.getData_value5(),
					edatas.getData_value6(),
					edatas.getData_value7(),
					edatas.getData_value8(),
					edatas.getData_value9(),
					edatas.getData_value10(),
					edatas.getData_value11(),
					edatas.getData_value12(),
					edatas.getData_value13(),
					edatas.getData_value14(),
					edatas.getData_value15(),
					edatas.getData_value16(),
					edatas.getData_value17(),
					edatas.getData_value18(),
					edatas.getData_value19(),
					edatas.getData_value20(),
					
					edatas.getData_value21(),
					edatas.getData_value22(),
					edatas.getData_value23(),
					edatas.getData_value24(),
					edatas.getData_value25(),
					edatas.getData_value26(),
					edatas.getData_value27(),
					edatas.getData_value28(),
					edatas.getData_value29(),
					edatas.getData_value30(),
					edatas.getData_status(),
					date,
					edatas.getId_refer(),
					edatas.getReference(),
					edatas.getExtension_id(),
					edatas.getData_filepath(),
					edatas.getCustomer(),
					edatas.getData_nameadd()
					};
			
			
			
			
			String sql = "UPDATE edata SET "
					+ "data_idadd=?,data_value1=?,data_value2=?,data_value3=?,data_value4=?,data_value5=?"
					+ ",data_value6=?,data_value7=?,data_value8=?,data_value9=?,data_value10=?"
					+ ",data_value11=?,data_value12=?,data_value13=?,data_value14=?,data_value15=?"
					+ ",data_value16=?,data_value17=?,data_value18=?,data_value19=?,data_value20=?"
					+ ",data_value21=? ,data_value22=?,data_value23=?,data_value24=?,data_value25=?,data_value26=?,data_value27=?,data_value28=?,data_value29=?,data_value30=? "
					+ ",data_status=?,data_dateupdate=?"
					+ ",id_refer=?,reference=?,extension_id=?,data_filepath=?,customer=?,data_nameadd=?"
					+ "	 WHERE data_id ="+edatas.getData_id();
			 jdbcTemplate.update(sql,edataInfo);
			 return edatas.getData_id();
			 
			 
		}else {
			
			Object[] edataInfo = { 
					edatas.getData_idadd(),
					edatas.getData_nameadd(),
					date,
					edatas.getData_value1(),
					edatas.getData_value2(),
					edatas.getData_value3(),
					edatas.getData_value4(),
					edatas.getData_value5(),
					edatas.getData_value6(),
					edatas.getData_value7(),
					edatas.getData_value8(),
					edatas.getData_value9(),
					edatas.getData_value10(),
					edatas.getData_value11(),
					edatas.getData_value12(),
					edatas.getData_value13(),
					edatas.getData_value14(),
					edatas.getData_value15(),
					edatas.getData_value16(),
					edatas.getData_value17(),
					edatas.getData_value18(),
					edatas.getData_value19(),
					edatas.getData_value20(),
					edatas.getData_value21(),
					edatas.getData_value22(),
					edatas.getData_value23(),
					edatas.getData_value24(),
					edatas.getData_value25(),
					edatas.getData_value26(),
					edatas.getData_value27(),
					edatas.getData_value28(),
					edatas.getData_value29(),
					edatas.getData_value30(),
					edatas.getProcesses_id(),
					edatas.getData_title(),
					edatas.getId_refer(),
					edatas.getReference(),
					edatas.getExtension_id(),
					edatas.getData_eformid(),
					edatas.getData_eformrv(),
					edatas.getEdata_index_action_type(),	
					edatas.getCustomer(),
					};
			
			System.err.println("getProcesses_id()-> Proccee id  "+		edatas.getProcesses_id());
			
			
			String sql = "INSERT INTO edata("
					+ "data_idadd,data_nameadd,data_dateadd"
					+ ",data_value1,data_value2,data_value3,data_value4,data_value5"
					+ ",data_value6,data_value7,data_value8,data_value9,data_value10"
					+ ",data_value11,data_value12,data_value13,data_value14,data_value15"
					+ ",data_value16,data_value17,data_value18,data_value19,data_value20"
					+ ",data_value21,data_value22,data_value23,data_value24,data_value25,data_value26,data_value27,data_value28,data_value29,data_value30"
					+ ",process_id,data_title"
					+ ",id_refer,reference,extension_id,data_eformid,data_eformrv,edata_index_action_type,customer"
					+ "	) "
					+ "VALUES("
					+ "?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?)";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			System.out.println("length " + edataInfo.length);
			
			
			jdbcTemplate.update(connection -> {
			    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			    for (int i = 0; i < edataInfo.length; i++) {
			        ps.setObject(i + 1, edataInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
			    }
			    return ps;
			}, keyHolder);

			// คืนค่า id ที่ถูกสร้างขึ้น
			return keyHolder.getKey().intValue();
		}
		
		

		
	}
	
	@Override
	public int SaveProduct(edata_product edata_products) {
	    System.out.println("edata_product save logic started");

	    // 1. เตรียมข้อมูลสำหรับ Query
	    Object[] edata_productParams = {
	        edata_products.getProduct_names(),
	        edata_products.getProduct_category(),
	        edata_products.getProduct_details(),
	        edata_products.getUnit(),
	        edata_products.getProduct_type(),
	        edata_products.getProduct_dimensions(),
	        edata_products.getProduct_expiration_date(),
	        edata_products.getProduct_selling_price(),
	        edata_products.getProduct_cost_price(),
	        edata_products.getProduct_wholesale_price(),
	        edata_products.getEdata_id() // เอาไว้ใช้ใน WHERE clause หรือ INSERT
	    };

	    // --- ส่วนของ Similarity Logic (คงไว้ตามเดิมของคุณ) ---
	    String sqlmain = "SELECT * FROM product_master LIMIT 10000";
	    List<product_master> data = jdbcTemplate.query(sqlmain, new product_mastermappers());
	    List<levenshtein> levenshteins = new ArrayList<>();
	    int limit = 0;
	    for (product_master check : data) {
	        String name1 = edata_products.getProduct_names();
	        String name2 = check.getProduct_names();
	        int sim = Otherservice.calculateSimilarity(name1, name2);
	        if (sim > 50) {
	            levenshtein shteins = new levenshtein();
	            shteins.setDistance(sim);
	            shteins.setShtein_name1(name1);
	            shteins.setShtein_name2(name2);
	            shteins.setEdata_id(edata_products.getEdata_id());
	            levenshteins.add(shteins);
	            limit++;
	        }
	        if (limit > 10) break;
	    }
	    Dataset_dataDAO.SaveBatch(levenshteins, edata_products.getEdata_id());
	    // --------------------------------------------------

	    // 2. ตรวจสอบว่า edata_id นี้มีอยู่ใน Database หรือยัง
	    String checkSql = "SELECT COUNT(*) FROM edata_product WHERE edata_id = ?";
	    Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, edata_products.getEdata_id());

	    if (count != null && count > 0) {
	        // --- กรณีที่มี edata_id อยู่แล้ว -> ทำการ UPDATE ---
	        System.out.println("edata_id exists, performing UPDATE");
	        
	        String updateSql = "UPDATE edata_product SET "
	                + "product_names = ?, product_category = ?, product_details = ?, unit = ?, "
	                + "product_type = ?, product_dimensions = ?, product_expiration_date = ?, "
	                + "product_selling_price = ?, product_cost_price = ?, product_wholesale_price = ?, productCode = ?,product_status=? "
	                + "WHERE edata_id = ?";
	        
	        // ส่ง params เข้าไป (ตัดตัวแรกออกถ้าไม่ได้แก้ product_code) 
	        // หรือเรียงลำดับ object array ให้ตรงกับเครื่องหมาย ?
	        return jdbcTemplate.update(updateSql, 
	                edata_products.getProduct_names(),
	                edata_products.getProduct_category(),
	                edata_products.getProduct_details(),
	                edata_products.getUnit(),
	                edata_products.getProduct_type(),
	                edata_products.getProduct_dimensions(),
	                edata_products.getProduct_expiration_date(),
	                edata_products.getProduct_selling_price(),
	                edata_products.getProduct_cost_price(),
	                edata_products.getProduct_wholesale_price(),
	                edata_products.getProductCode(),
	                edata_products.getProduct_status(),
	                edata_products.getEdata_id());

	    } else if (edata_products.getEdata_id() > 0) {
	        // --- กรณีที่ยังไม่มี edata_id -> ทำการ INSERT ---
	        System.out.println("edata_id is new, performing INSERT");

	        String insertSql = "INSERT INTO edata_product(product_names, product_category, product_details, unit, "
	                + "product_type, product_dimensions, product_expiration_date, product_selling_price, "
	                + "product_cost_price, product_wholesale_price,product_status,productCode, edata_id) "
	                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, edata_products.getProduct_names());
	            ps.setString(2, edata_products.getProduct_category());
	            ps.setString(3, edata_products.getProduct_details());
	            ps.setString(4, edata_products.getUnit());
	            ps.setString(5, edata_products.getProduct_type());
	            ps.setString(6, edata_products.getProduct_dimensions());
	            ps.setObject(7, edata_products.getProduct_expiration_date());
	            ps.setString(8, edata_products.getProduct_selling_price());
	            ps.setString(9, edata_products.getProduct_cost_price());
	            ps.setString(10, edata_products.getProduct_wholesale_price());
	            ps.setString(11, edata_products.getProduct_status());
	            ps.setString(12, edata_products.getProductCode());
	            ps.setInt(13, edata_products.getEdata_id());
	            return ps;
	        }, keyHolder);

	        return (keyHolder.getKey() != null) ? keyHolder.getKey().intValue() : 1;
	    } else {
	        return 0;
	    }
	}
	
	
	
	@Override
	public int SaveMainProduct(edata_product edata_products, String Code,LoadApp LoadApp) {
		
		System.out.println(" **************SaveMainProduct ");
		
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	 	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	 	 String date = localDate.format(formatter);
	 	String date2 = localDate.format(formatter2);
	        // ค้นหา register_id ล่าสุดของ register_name และ register_type เดียวกัน
	        String getMaxIdSQL = "SELECT COALESCE(MAX(product_id), 0) + 1 FROM product_master WHERE product_code = ? ";
	        Integer nextRegisterId = jdbcTemplate.queryForObject(getMaxIdSQL,new Object[] {Code}, Integer.class);
	    

		Object[] edata_productInfo = { 
				nextRegisterId,
				Code,
				edata_products.getProduct_names(),
				edata_products.getProduct_type(),
				edata_products.getProduct_details(),
				edata_products.getUnit(),
			    "ACTIVE",
				date,
				date2,
				edata_products.getProduct_category(),
				edata_products.getProduct_dimensions(),
				edata_products.getProduct_expiration_date(),
				edata_products.getProduct_selling_price(),
				edata_products.getProduct_cost_price(),
				edata_products.getProduct_wholesale_price(),
				};
		

		

			String sql = "INSERT INTO product_master(product_id,product_code,product_names,product_category,product_details,unit,product_status,product_daterun,created_at"
					+ ",product_type,product_dimensions,product_expiration_date,product_selling_price,product_cost_price,product_wholesale_price) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					
					KeyHolder keyHolder = new GeneratedKeyHolder();
						System.out.println("length " + edata_productInfo.length);
			jdbcTemplate.update(connection -> {
					    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					    for (int i = 0; i < edata_productInfo.length; i++) {
					        ps.setObject(i + 1, edata_productInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
					    }
					    return ps;
					}, keyHolder);
		
			
			int id_refer = keyHolder.getKey().intValue();
			
			
		   	Object[] DataInfo = { 
					LoadApp.getWorkflow().getWorkflow_id(),
					LoadApp.getEdata().getData_idadd(),
					edata_products.getProduct_category(),
					date2,
					RegisterActiontype.CREATE_REQUEST.toString(),
					LoadApp.getEdata().getData_id(),
					LoadApp.getProcess().getId(),
					id_refer
					};
			
					String sqls = "INSERT INTO product_log(workflow_id,id_add,product_category,created_at,type,edata_id,process_id,id_refer) "
					+ "VALUES(?,?,?,?,?,?,?,?) ";
					jdbcTemplate.update(sqls,DataInfo);
			
					
			
					DocumentEffect Document_effect = new DocumentEffect();
					int id_refers = keyHolder.getKey().intValue();
					String codeid =Code+date+"-"+nextRegisterId+" "+edata_products.getProduct_names();
					
					Document_effect.setEdata_id(LoadApp.getEdata().getData_id());
					Document_effect.setTitle(codeid);
					Document_effect.setType("effect.INSERT");
					Document_effect.setData_type("product");
					Document_effect.setId_refer(id_refers);
			
					
					Document_effect.setId_new(edata_products.getId());
				    DocumentEffect old = getDocumentEffectByIdRefer("product",id_refers);
					if(old != null) {
						Document_effect.setId_old(old.getId_new());
					}
					SaveDocument_effect(Document_effect);
					// คืนค่า id ที่ถูกสร้างขึ้น
					return id_refer;
		
					
	}
	
	
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public List<Integer[]> SaveAllMainProduct(List<edata_product> edata_productList, String Code) {
	    
	    System.out.println(" **************SaveAllMainProduct (Clean Batch) ");
	    
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 🌟 ใช้ mm เล็กสำหรับนาที
	    String date = localDate.format(formatter);
	    String date2 = localDate.format(formatter2);

	    // 🌟 1. ดึงเลขสูงสุดแค่ "ครั้งเดียว" ก่อนเริ่มลูป
	    String getMaxIdSQL = "SELECT COALESCE(MAX(product_id), 0) FROM product_master WHERE product_code = ? ";
	    Integer currentMaxId = jdbcTemplate.queryForObject(getMaxIdSQL, new Object[]{Code}, Integer.class);
	    if (currentMaxId == null) currentMaxId = 0;

	    // 🌟 2. เตรียม SQL สำหรับ Product (16 คอลัมน์ รวม id_oldsys)
	    String sql = "INSERT INTO product_master(product_id, product_code, product_names, product_category, "
	               + "product_details, unit, product_status, product_daterun, created_at, "
	               + "product_type, product_dimensions, product_expiration_date, product_selling_price, "
	               + "product_cost_price, product_wholesale_price, id_oldsys) "
	               + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	    // List สำหรับเก็บ ID ที่ Insert สำเร็จ เพื่อส่งคืนกลับไป
	    List<Integer[]> savedIdsList = new ArrayList<>();

	    // 🌟 3. วนลูป Insert ทีละรายการ (ทำงานใน Transaction เดียวกัน)
	    for (edata_product edata_products : edata_productList) {
	        
	        // บวกเลขใน Memory ทันที
	        currentMaxId++;
	        final Integer nextProductId = currentMaxId; 

	        // 🌟 4. เตรียมข้อมูลสำหรับ Insert (16 ฟิลด์)
	        Object[] edata_productInfo = { 
	            nextProductId,
	            Code,
	            edata_products.getProduct_names(),
	            edata_products.getProduct_category(),
	            edata_products.getProduct_details(),
	            edata_products.getUnit(),
	            "ACTIVE", // ค่าคงที่ตามโค้ดเก่า
	            date,
	            date2,
	            edata_products.getProduct_type(),
	            edata_products.getProduct_dimensions(),
	            edata_products.getProduct_expiration_date(),
	            edata_products.getProduct_selling_price(),
	            edata_products.getProduct_cost_price(),
	            edata_products.getProduct_wholesale_price(),
	            edata_products.getId_oldsys() // 🌟 เพิ่ม id_oldsys ตรงนี้
	        };

	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        
	        // 🌟 5. ยิง Insert รับค่า Auto Increment (id_refer)
	        jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            for (int i = 0; i < edata_productInfo.length; i++) {
	                ps.setObject(i + 1, edata_productInfo[i]); 
	            }
	            return ps;
	        }, keyHolder);
	        
	        // ดึง Auto Increment ID (PK ของตาราง)
	        int id_refer = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
	        
	        // 🌟 6. เก็บลง Array แล้วยัดใส่ List (ช่องแรกคือ Running เลข, ช่องสองคือ PK)
	        Integer[] save = new Integer[2];
	        save[0] = nextProductId;
	        save[1] = id_refer;
	        savedIdsList.add(save);
	    }

	    // 🌟 7. คืนค่า List ของ ID ทั้งหมดกลับไปให้ Controller จัดการต่อ
	    return savedIdsList;
	}
	
	
	@Override
	public int UpdateMainProduct(edata_product edata_products,LoadApp LoadApp,String type) {
		
		System.out.println(" **************UpdateMainProduct ");
		
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	 	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	 	String date2 = localDate.format(formatter);
	  

		Object[] edata_productInfo = { 
				edata_products.getProduct_names(),
				edata_products.getProduct_category(),
				edata_products.getProduct_details(),
				edata_products.getUnit(),
				type,
				date2,
				edata_products.getProduct_type(),
				edata_products.getProduct_dimensions(),
				edata_products.getProduct_expiration_date(),
				edata_products.getProduct_selling_price(),
				edata_products.getProduct_cost_price(),
				edata_products.getProduct_wholesale_price(),
				edata_products.getProductCode(),
				};
		

		

			String sql = "UPDATE  product_master SET product_names =? ,product_category=?,product_details=?,unit=?,product_status=?,updated_at=?"
					+ ",product_type=?,product_dimensions=?,product_expiration_date=?,product_selling_price=?,product_cost_price=?,product_wholesale_price=?,revision = revision+1 "
					+ " WHERE concat(product_code,product_daterun,'-',product_id) = ?";
					
					KeyHolder keyHolder = new GeneratedKeyHolder();
						System.out.println("length " + edata_productInfo.length);
			jdbcTemplate.update(connection -> {
					    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					    for (int i = 0; i < edata_productInfo.length; i++) {
					        ps.setObject(i + 1, edata_productInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
					    }
					    return ps;
					}, keyHolder);
		
			
					int id_refer = 1;
			

					
			
					String effect ="effect.UPDATE";
				    if(type =="PENDING_DELETE") {
				    	  effect ="effect.PENDING_DELETE";
				    }
				    else if(type =="ACTIVE") {
				    	 effect ="effect.UPDATE";
				    }
				    else {
				    	  effect ="effect.CHANGE_STATUS";
				    } 

					DocumentEffect Document_effect = new DocumentEffect();
					int id_refers = keyHolder.getKey().intValue();
					String codeid =edata_products.getProductCode()+" "+edata_products.getProduct_names();
					Document_effect.setEdata_id(LoadApp.getEdata().getData_id());
					Document_effect.setTitle(codeid);
					Document_effect.setType(effect);
					Document_effect.setData_type("product");
					Document_effect.setId_refer(id_refers);
					SaveDocument_effect(Document_effect);
					

					Document_effect.setId_new(edata_products.getId());
				    DocumentEffect old = getDocumentEffectByIdRefer("product",id_refers);
					if(old != null) {
						Document_effect.setId_old(old.getId_new());
					}
		
					
					
					// คืนค่า id ที่ถูกสร้างขึ้น
					return id_refer;
		
					
	}
	
	
	
	
	



	
	
	
	@Override
	public int UpdateMainProductStatus(edata_product edata_products,LoadApp LoadApp,String type) {
		
		System.out.println(" **************UpdateMainProduct ");
		
		
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	 	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	 	String date2 = localDate.format(formatter);
	  

		Object[] edata_productInfo = { 
				type,
				date2,
				edata_products.getProductCode(),
				};
		

		

			String sql = "UPDATE  product_master SET product_status=?,updated_at=?"
					+ " WHERE concat(product_code,product_daterun,'-',product_id) = ?";
					
					KeyHolder keyHolder = new GeneratedKeyHolder();
						System.out.println("length " + edata_productInfo.length);
			jdbcTemplate.update(connection -> {
					    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					    for (int i = 0; i < edata_productInfo.length; i++) {
					        ps.setObject(i + 1, edata_productInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
					    }
					    return ps;
					}, keyHolder);
		
			
					int id_refer = 1;
			

					
			
					String effect ="effect.UPDATE";
				    if(type =="PENDING_DELETE") {
				    	  effect ="effect.PENDING_DELETE";
				    }
				    else if(type =="ACTIVE") {
				    	 effect ="effect.UPDATE";
				    }
				    else {
				    	  effect ="effect.CHANGE_STATUS";
				    } 

					DocumentEffect Document_effect = new DocumentEffect();
					int id_refers = keyHolder.getKey().intValue();
					
					Document_effect.setId_refer(id_refers);
					String codeid =edata_products.getProductCode()+" "+edata_products.getProduct_names();
					Document_effect.setEdata_id(LoadApp.getEdata().getData_id());
					Document_effect.setTitle(codeid);
					Document_effect.setType(effect);
					Document_effect.setData_type("product");
					Document_effect.setId_refer(id_refers);
					
					String sqls = "SELECT * FROM   product_master WHERE" 
	    	        		+ " WHERE concat(product_code,product_daterun,'-',product_id) = ? ";
					product_master product_rold = jdbcTemplate.queryForObject(sqls, new Object[] {edata_products.getProductCode()}, new  product_mastermappers());
					
					Document_effect.setId_new(product_rold.getId());
				    DocumentEffect old = getDocumentEffectByIdRefer("product",id_refers);
					if(old != null) {
						Document_effect.setId_old(old.getId_new());
					}
		
					
					
					
					SaveDocument_effect(Document_effect);
					

					// คืนค่า id ที่ถูกสร้างขึ้น
					return id_refer;
		
					
	}
	
	
	
	
	
	
	public void SaveDocument_effect(DocumentEffect Document_effect) {
		
	 	 user users = UserDAO.getbyusername2(Userservice.getLoggedName());
		
	 	
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	 	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 	 String date = localDate.format(formatter);
	 	 
		
	   	Object[] EffectInfo = { 
	   			Document_effect.getEdata_id(),
				Document_effect.getTitle(),
				Document_effect.getType(),
				Document_effect.getData_type(),
				Document_effect.getId_refer(),
				Document_effect.getId_new(),
				Document_effect.getId_old(),
				users.getUser_id(),
				date
				};
		
		String sqls = "INSERT INTO document_effect(edata_id,title,type,data_type,id_refer,id_new,id_old,id_add,date_add) "
				+ "VALUES(?,?,?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sqls,EffectInfo);
		
	}
	
	@Override
	public int updateExtensionRegister(registers register) {
	    System.out.println("Production--------> SaveRegister: UPDATE WITH EXTENSION COLUMNS");

	    // 1. สร้าง SQL UPDATE (เพิ่ม extension_column_1 ถึง 20)
	    String sql = "UPDATE register SET  "
	            + "extension_column_1=?, extension_column_2=?, extension_column_3=?, extension_column_4=?, extension_column_5=?, "
	            + "extension_column_6=?, extension_column_7=?, extension_column_8=?, extension_column_9=?, extension_column_10=?, "
	            + "extension_column_11=?, extension_column_12=?, extension_column_13=?, extension_column_14=?, extension_column_15=?, "
	            + "extension_column_16=?, extension_column_17=?, extension_column_18=?, extension_column_19=?, extension_column_20=? "
	            + "WHERE id = ?";

	    // 2. ส่ง Parameter เข้าไป (ตรวจสอบชื่อ Getter ให้ตรงกับ Class registers ของคุณ)
	    return jdbcTemplate.update(sql,

	            register.getExtension_column_1(),
	            register.getExtension_column_2(),
	            register.getExtension_column_3(),
	            register.getExtension_column_4(),
	            register.getExtension_column_5(),
	            register.getExtension_column_6(),
	            register.getExtension_column_7(),
	            register.getExtension_column_8(),
	            register.getExtension_column_9(),
	            register.getExtension_column_10(),
	            register.getExtension_column_11(),
	            register.getExtension_column_12(),
	            register.getExtension_column_13(),
	            register.getExtension_column_14(),
	            register.getExtension_column_15(),
	            register.getExtension_column_16(),
	            register.getExtension_column_17(),
	            register.getExtension_column_18(),
	            register.getExtension_column_19(),
	            register.getExtension_column_20(),
	            // --- WHERE Clause ---
	            register.getId() 
	    );
	}

	
	@Override
	public int SaveRegister(edata_register edata_registers) {
	    System.out.println("Processing SaveRegister for edata_id: " + edata_registers.getEdata_id());

	    // 1. ตรวจสอบว่า edata_id นี้มีอยู่ในฐานข้อมูลหรือยัง
	    String checkSql = "SELECT COUNT(*) FROM edata_register WHERE edata_id = ?";
	    Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, edata_registers.getEdata_id());

	    if (count != null && count > 0) {
	        // --- กรณีพบข้อมูลเดิม -> ทำการ UPDATE ---
	        System.out.println("Production--------> SaveRegister: UPDATE");

	        // 🌟 เพิ่มคอลัมน์ bank_account และ 15 คอลัมน์ใหม่ในคำสั่ง UPDATE
	        String sql = "UPDATE edata_register SET "
	                + "register_type=?, register_groups=?, register_name=?, register_address=?, "
	                + "register_note=?, register_other_conditions=?, register_status=?, "
	                + "contact_person=?, phone_number=?, mobile_number=?, website=?, social=?, "
	                + "tax_id=?, branch_code=?, business_type=?, payment_terms=?, credit_limit=?, "
	                + "bank_account=?, preferred_currency=?, customerCode=?, "
	                // --- คอลัมน์ใหม่ 15 ตัว ---
	                + "party_type=?, product_category=?, register_title=?, fax=?, "
	                + "person_position=?, person_number=?, person_email=?, "
	                + "contact_approvers=?, approvers_position=?, approvers_number=?, approvers_email=?, "
	                + "region=?, lat=?, lon=?, dbd=?,register_poin=?,date_insert_oldsys=?,register_email=?,merged_duplicate_ids=?, id_oldsys=?"
	                + " WHERE edata_id = ?";

	        
	
		 
	        
	        // เนื่องจากในโค้ดเดิมคุณส่งพารามิเตอร์แบบกระจาย (ไม่ใช่ Object[])
	        // ผมจะใช้วิธีจัดเรียงพารามิเตอร์ให้ตรงกับ SQL ด้านบนครับ
	        return jdbcTemplate.update(sql,
	                edata_registers.getRegister_type(),
	                edata_registers.getRegister_groups(),
	                edata_registers.getRegister_name(),
	                edata_registers.getRegister_address(),
	                edata_registers.getRegister_note(),
	                edata_registers.getRegister_other_conditions(),
	                edata_registers.getRegister_status(),
	                edata_registers.getContact_person(),
	                edata_registers.getPhone_number(),
	                edata_registers.getMobile_number(),
	                edata_registers.getWebsite(),
	                edata_registers.getSocial(),
	                edata_registers.getTax_id(),
	                edata_registers.getBranch_code(),
	                edata_registers.getBusiness_type(),
	                edata_registers.getPayment_terms(),
	                edata_registers.getCredit_limit(),
	                edata_registers.getBank_account(), // 🌟 เพิ่ม
	                edata_registers.getPreferred_currency(),
	                edata_registers.getCustomerCode(),
	                // --- ข้อมูลใหม่ 15 ตัว ---
	                edata_registers.getParty_type(),
	                edata_registers.getProduct_category(),
	                edata_registers.getRegister_title(),
	                edata_registers.getFax(),
	                edata_registers.getPerson_position(),
	                edata_registers.getPerson_number(),
	                edata_registers.getPerson_email(), // 🌟 เพิ่มจาก Model ล่าสุด
	                edata_registers.getContact_approvers(),
	                edata_registers.getApprovers_position(),
	                edata_registers.getApprovers_number(),
	                edata_registers.getApprovers_email(),
	                edata_registers.getRegion(),
	                edata_registers.getLat(),
	                edata_registers.getLon(),
	                edata_registers.getDbd(),
	                
	                edata_registers.getRegister_poin(),
	                edata_registers.getDate_insert_oldsys(),
	                edata_registers.getRegister_email(),
	                edata_registers.getMerged_duplicate_ids(),
	                edata_registers.getId_oldsys(),
	                
	                // --- สำหรับ WHERE clause ---
	                edata_registers.getEdata_id()
	        );


	        
	        
	        
	        
	        
	    } else if (edata_registers.getEdata_id() > 0) {
	        // --- กรณีไม่พบข้อมูลเดิม -> ทำการ INSERT ---
	        System.out.println("Production--------> SaveRegister: INSERT");

	        // 🌟 เพื่อความสะอาดและป้องกันการนับ index ของ PreparedStatement สลับกัน
	        // ผมขอปรับเป็นรูปแบบ Object[] เหมือนที่คุณทำใน Method อื่นๆ นะครับ
	        String sql = "INSERT INTO edata_register ("
	                + "edata_id, register_type, register_groups, register_name, register_address, "
	                + "register_note, register_other_conditions, register_status, contact_person, "
	                + "phone_number, mobile_number, website, social, tax_id, branch_code, "
	                + "business_type, payment_terms, credit_limit, bank_account, preferred_currency, customerCode, "
	                // --- คอลัมน์ใหม่ 15 ตัว ---
	                + "party_type, product_category, register_title, fax, person_position, person_number, person_email, "
	                + "contact_approvers, approvers_position, approvers_number, approvers_email, region, lat, lon, dbd"
	                + ",register_poin,date_insert_oldsys,register_email,merged_duplicate_ids,id_oldsys"
	                + ") VALUES ("
	                + "?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?, "
	                + "?,?,?,?, ?,?,?, ?,?,?,?, ?,?,?,?,?,?,?,?,?"
	                + ")"; // มีทั้งหมด 36 ฟิลด์ (?)

	        
	
	        
	        
	        Object[] insertData = {
	                edata_registers.getEdata_id(), // 1
	                edata_registers.getRegister_type(), // 2
	                edata_registers.getRegister_groups(), // 3
	                edata_registers.getRegister_name(), // 4
	                edata_registers.getRegister_address(), // 5
	                edata_registers.getRegister_note(), // 6
	                edata_registers.getRegister_other_conditions(), // 7
	                edata_registers.getRegister_status(), // 8
	                edata_registers.getContact_person(), // 9
	                edata_registers.getPhone_number(), // 10
	                edata_registers.getMobile_number(), // 11
	                edata_registers.getWebsite(), // 12
	                edata_registers.getSocial(), // 13
	                edata_registers.getTax_id(), // 14
	                edata_registers.getBranch_code(), // 15
	                edata_registers.getBusiness_type(), // 16
	                edata_registers.getPayment_terms(), // 17
	                edata_registers.getCredit_limit(), // 18
	                edata_registers.getBank_account(), // 19 (🌟 เพิ่ม)
	                edata_registers.getPreferred_currency(), // 20
	                edata_registers.getCustomerCode(), // 21
	                // --- ข้อมูลใหม่ 15 ตัว ---
	                edata_registers.getParty_type(), // 22
	                edata_registers.getProduct_category(), // 23
	                edata_registers.getRegister_title(), // 24
	                edata_registers.getFax(), // 25
	                edata_registers.getPerson_position(), // 26
	                edata_registers.getPerson_number(), // 27
	                edata_registers.getPerson_email(), // 28 (🌟 เพิ่ม)
	                edata_registers.getContact_approvers(), // 29
	                edata_registers.getApprovers_position(), // 30
	                edata_registers.getApprovers_number(), // 31
	                edata_registers.getApprovers_email(), // 32
	                edata_registers.getRegion(), // 33
	                edata_registers.getLat(), // 34
	                edata_registers.getLon(), // 35
	                edata_registers.getDbd(), // 36
	                edata_registers.getRegister_poin(),
	                edata_registers.getDate_insert_oldsys(),
	                edata_registers.getRegister_email(),
	                edata_registers.getMerged_duplicate_ids(),
	                edata_registers.getId_oldsys(),
	        };

	        KeyHolder keyHolder = new GeneratedKeyHolder();

	        jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            for (int i = 0; i < insertData.length; i++) {
	                ps.setObject(i + 1, insertData[i]);
	            }
	            return ps;
	        }, keyHolder);

	        // คืนค่า ID ที่ Generate ออกมา (หรือถ้าไม่ได้ใช้ Auto Increment PK ให้รีเทิร์น 1)
	        return (keyHolder.getKey() != null) ? keyHolder.getKey().intValue() : 1;

	    } else {
	        System.err.println("Production--------> SaveRegister: Error - Invalid edata_id");
	        return 0;
	    }
	}
	
	

    @Override
	public int SaveRegisterDirect(edata_register edata_registers) {


    // 🌟 เพื่อความสะอาดและป้องกันการนับ index ของ PreparedStatement สลับกัน
    // ผมขอปรับเป็นรูปแบบ Object[] เหมือนที่คุณทำใน Method อื่นๆ นะครับ
    String sql = "INSERT INTO edata_register ("
            + "edata_id, register_type, register_groups, register_name, register_address, "
            + "register_note, register_other_conditions, register_status, contact_person, "
            + "phone_number, mobile_number, website, social, tax_id, branch_code, "
            + "business_type, payment_terms, credit_limit, bank_account, preferred_currency, customerCode, "
            // --- คอลัมน์ใหม่ 15 ตัว ---
            + "party_type, product_category, register_title, fax, person_position, person_number, person_email, "
            + "contact_approvers, approvers_position, approvers_number, approvers_email, region, lat, lon, dbd"
            + ",register_poin,date_insert_oldsys,register_email,merged_duplicate_ids,id_oldsys"
            + ") VALUES ("
            + "?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?, "
            + "?,?,?,?, ?,?,?, ?,?,?,?, ?,?,?,?,?,?,?,?,?"
            + ")"; 

    

    
    
    Object[] insertData = {
            edata_registers.getEdata_id(), // 1
            edata_registers.getRegister_type(), // 2
            edata_registers.getRegister_groups(), // 3
            edata_registers.getRegister_name(), // 4
            edata_registers.getRegister_address(), // 5
            edata_registers.getRegister_note(), // 6
            edata_registers.getRegister_other_conditions(), // 7
            edata_registers.getRegister_status(), // 8
            edata_registers.getContact_person(), // 9
            edata_registers.getPhone_number(), // 10
            edata_registers.getMobile_number(), // 11
            edata_registers.getWebsite(), // 12
            edata_registers.getSocial(), // 13
            edata_registers.getTax_id(), // 14
            edata_registers.getBranch_code(), // 15
            edata_registers.getBusiness_type(), // 16
            edata_registers.getPayment_terms(), // 17
            edata_registers.getCredit_limit(), // 18
            edata_registers.getBank_account(), // 19 (🌟 เพิ่ม)
            edata_registers.getPreferred_currency(), // 20
            edata_registers.getCustomerCode(), // 21
            // --- ข้อมูลใหม่ 15 ตัว ---
            edata_registers.getParty_type(), // 22
            edata_registers.getProduct_category(), // 23
            edata_registers.getRegister_title(), // 24
            edata_registers.getFax(), // 25
            edata_registers.getPerson_position(), // 26
            edata_registers.getPerson_number(), // 27
            edata_registers.getPerson_email(), // 28 (🌟 เพิ่ม)
            edata_registers.getContact_approvers(), // 29
            edata_registers.getApprovers_position(), // 30
            edata_registers.getApprovers_number(), // 31
            edata_registers.getApprovers_email(), // 32
            edata_registers.getRegion(), // 33
            edata_registers.getLat(), // 34
            edata_registers.getLon(), // 35
            edata_registers.getDbd(), // 36
            edata_registers.getRegister_poin(),
            edata_registers.getDate_insert_oldsys(),
            edata_registers.getRegister_email(),
            edata_registers.getMerged_duplicate_ids(),
            edata_registers.getId_oldsys(),
    };

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
        for (int i = 0; i < insertData.length; i++) {
            ps.setObject(i + 1, insertData[i]);
        }
        return ps;
    }, keyHolder);



    
	String effect ="effect.UPDATE";
	DocumentEffect Document_effect = new DocumentEffect();
	String codeid =edata_registers.getRegister_name();
	Document_effect.setEdata_id(0);
	Document_effect.setTitle(codeid);
	Document_effect.setType(effect);
	Document_effect.setData_type("register");
	Document_effect.setId_refer(edata_registers.getId());
	Document_effect.setId_new(keyHolder.getKey().intValue());
    DocumentEffect old = getDocumentEffectByIdRefer("register",edata_registers.getId());
	if(old != null) {
		Document_effect.setId_old(old.getId_new());
	}

	
	SaveDocument_effect(Document_effect);
    
    // คืนค่า ID ที่ Generate ออกมา (หรือถ้าไม่ได้ใช้ Auto Increment PK ให้รีเทิร์น 1)
    return keyHolder.getKey().intValue();
    
    
    

} 
	
	
	@Override
	public int SaveStock(edata_stock edata_stocks,LoadApp LoadApp) {
	
		 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	 	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	 	 String date = localDate.format(formatter);
	 	String date2 = localDate.format(formatter2);
		
		Object[] edata_stocksInfo = { 
				edata_stocks.getEdata_id(),
				edata_stocks.getStock_list(),
				edata_stocks.getStock_unit(),
				edata_stocks.getStock_type(),
				edata_stocks.getStock_storage(),
				edata_stocks.getStock_note(),
				edata_stocks.getStock_date(),
				edata_stocks.getStock_pd(),
				edata_stocks.getStock_batch(),
				edata_stocks.getStock_lot(),
				edata_stocks.getStock_serial_number(),
				edata_stocks.getStock_status(),
				edata_stocks.getStock_max(),
				edata_stocks.getStock_min(),
				edata_stocks.getStock_expdate(),
				edata_stocks.getStock_received(),
				edata_stocks.getStock_stockid(),
				edata_stocks.getStock_support_dataflow(),
				};
		
		
		
		
		if(edata_stocks.getStock_id() > 0) {
			
			
			System.out.println("Stock Warehouse-------->  UPDATE");
			
			String sql = "UPDATE edata_stock SET edata_id =? ,stock_list=?,stock_unit=?,stock_type=?,stock_storage=?,stock_note=?"
					+ ",stock_date =? ,stock_pd =? ,stock_batch =? ,stock_lot =? ,stock_serial_number =? ,stock_status=?,stock_max=?,stock_min=?"
					+ ",stock_expdate=?,stock_received=?,stock_stockid=?,stock_support_dataflow=?   "
					
					
					+ " WHERE stock_id ="+edata_stocks.getStock_id();
			return jdbcTemplate.update(sql,edata_stocksInfo);
			
			
		}else if(edata_stocks.getEdata_id() > 0) {
					
			System.out.println("Stock Warehouse-------->  INSERT");
			
			String sql = "INSERT INTO edata_stock(edata_id,stock_list,stock_unit,stock_type,stock_storage,stock_note,stock_date,stock_pd,stock_batch,stock_lot,stock_serial_number"
					+ ",stock_status,stock_max,stock_min,stock_expdate,stock_received,stock_stockid,stock_support_dataflow) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					
					KeyHolder keyHolder = new GeneratedKeyHolder();
						System.out.println("length " + edata_stocksInfo.length);
					
					
					jdbcTemplate.update(connection -> {
					    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					    for (int i = 0; i < edata_stocksInfo.length; i++) {
					        ps.setObject(i + 1, edata_stocksInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
					    }
					    return ps;
					}, keyHolder);
		
					// คืนค่า id ที่ถูกสร้างขึ้น	
					int id_refer = keyHolder.getKey().intValue();
							// คืนค่า id ที่ถูกสร้างขึ้น
							return id_refer;
					
					
					
		}else{
			System.err.println("Stock Warehouse-------->  SaveStock : Error");
			
			return 0;
		}
		

		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int CheckDuplicateStock(edata_stock edata_stocks,int eformID) {
		
		try {
			String sql = "SELECT COUNT(*) FROM  stock WHERE stock_list=? AND stock_type=? AND stock_pd=?  AND stock_batch=? ";
			int num =  jdbcTemplate.queryForObject(sql,new Object[]{edata_stocks.getStock_list(),edata_stocks.getStock_type(),edata_stocks.getStock_pd(),edata_stocks.getStock_batch()},Integer.class);
			return num;
		}catch(Exception e) {
			return 0;
		}
		
		
	}
	
	
	@Override
	public int CheckDuplicateProduct(edata_product edata_products) {
		
		try {
			
			System.out.println("ProductCode "+edata_products.getProductCode());
			
			if(edata_products.getProductCode() != null) {
				
				String sql = "SELECT COUNT(*) FROM  product_master WHERE product_names=? AND product_category=? and concat(product_code,product_daterun,'-',product_id) != ?  ";
				int num =  jdbcTemplate.queryForObject(sql,new Object[]{edata_products.getProduct_names(),edata_products.getProduct_category(),edata_products.getProductCode() },Integer.class);
				System.err.println(" CheckDuplicateProduct "+num);
				return num;
			}else {
				
				String sql = "SELECT COUNT(*) FROM  product_master WHERE product_names=? AND product_category=?  ";
				int num =  jdbcTemplate.queryForObject(sql,new Object[]{edata_products.getProduct_names(),edata_products.getProduct_category()},Integer.class);
				System.err.println(" CheckDuplicateProduct "+num);
				return num;
			}
			
			
			
		
			
		

			}catch(Exception e) {
				System.out.println(e);
				return 0;
			}
	
		
	}
	
	
	@Override
	public int CheckDuplicatebatch(production_list production_lists) {
		
		try {
			
			String sql = "SELECT COUNT(*) FROM  production_list WHERE lot_batch=? AND id != ?  ";
		
			int num =  jdbcTemplate.queryForObject(sql,new Object[]{production_lists.getLot_batch(),production_lists.getId()},Integer.class);
			
			System.err.println(" CheckDuplicateProduct "+num);
			
			return num;

			}catch(Exception e) {
				System.out.println(e);
				return 0;
			}
	
		
	}
	
	
	
	@Override
	public int NumRegister(String type) {
		
			String sql = "SELECT COUNT(*) FROM  register WHERE  register_type=? ";
			int num =  jdbcTemplate.queryForObject(sql,new Object[]{type},Integer.class);
			return num;	
		
	}
	
	
	
	@Override
	public int CheckDuplicateRegister(edata_register edata_registers) {
	    System.err.println("Code : 001 Verify -> Create->VerifyRegiter->Stock_Warehouse->edata_register");
	    
	    List<String> unnecessaryWords = Arrays.asList("บริษัท", "จำกัด", "Ltd", "Co", "Inc", "บจก", ".");
	    int edata_id = edata_registers.getEdata_id();
	    String rawName = edata_registers.getRegister_name();
	    
	    if (rawName == null || edata_registers.getRegister_type() == null) {
	        return 1;
	    }

	    // 1. ล้างคำขยะออกก่อน (เช่น บริษัท, จำกัด)
	    String cleanedName = cleanName(rawName, unnecessaryWords).trim();
	    // 2. สร้างเวอร์ชัน "เอาสเปซออกให้หมด" ไว้ด้วย
	    String noSpaceName = cleanedName.replace(" ", ""); 

	    String regType = edata_registers.getRegister_type().trim();
	    String custCode = edata_registers.getCustomerCode();

	    // ==========================================
	    // STEP 1: ค้นหาแบบตรงตัว (Exact & No-Space) ก่อน
	    // ==========================================
	    StringBuilder sqlStep1 = new StringBuilder("SELECT * FROM register WHERE register_type = ? ");
	    List<Object> params1 = new ArrayList<>();
	    params1.add(regType);

	    if (custCode != null) {
	        sqlStep1.append(" AND concat(register_code, register_daterun, '-', register_id) != ? ");
	        params1.add(custCode);
	    }

	    sqlStep1.append(" AND ( register_name LIKE ? OR REPLACE(register_name, ' ', '') LIKE ? ) LIMIT 50");
	    params1.add("%" + cleanedName + "%"); // ค้นแบบมีสเปซ
	    params1.add("%" + noSpaceName + "%"); // ค้นแบบไม่มีสเปซ (DB บางตัวใช้ REPLACE ได้เลย)

	    // ยิง Query Step 1
	    List<registers> data = jdbcTemplate.query(sqlStep1.toString(), params1.toArray(), new registersmapper());

	    // ==========================================
	    // STEP 2: ถ้าหาแบบตรงๆ ไม่เจอเลย ค่อยใช้ท่า "ตัดคำ" (Split)
	    // ==========================================
	    if (data.isEmpty()) {
	        System.err.println("Step 1 Not Found. Proceeding to Step 2 (Split words)...");
	        
	        String[] words = cleanedName.split("\\s+"); // แยกด้วยช่องว่าง
	        StringBuilder sqlStep2 = new StringBuilder("SELECT * FROM register WHERE register_type = ? ");
	        List<Object> params2 = new ArrayList<>();
	        params2.add(regType);

	        if (custCode != null) {
	            sqlStep2.append(" AND concat(register_code, register_daterun, '-', register_id) != ? ");
	            params2.add(custCode);
	        }

	        sqlStep2.append(" AND ( ");
	        
	        // 🔥 จุดเปลี่ยนสำคัญ: ใช้ AND เพื่อบีบให้เจอ "ทุกคำ" แทนที่จะใช้ OR ที่หว่านแหกว้างไป
	        // เช่น ต้องเจอทั้ง "สหภัณฑ์" AND "กรุ๊ป"
	        for (int i = 0; i < words.length; i++) {
	            String normalized = normalizeWord(words[i]);
	            if (normalized.length() < 2) continue; // ข้ามคำสั้นๆ ป้องกันขยะ
	            
	            if (i > 0) sqlStep2.append(" AND "); // เปลี่ยนจาก OR เป็น AND
	            
	            sqlStep2.append(" register_name LIKE ? ");
	            params2.add("%" + normalized + "%");
	        }
	        sqlStep2.append(" ) LIMIT 100");

	        // ถ้ายาวไปจนเหลือคำค้นหาได้ ให้ยิง Query Step 2
	        if (params2.size() > 2) { 
	            data = jdbcTemplate.query(sqlStep2.toString(), params2.toArray(), new registersmapper());
	        }
	    }

	    // ==========================================
	    // ค่อยเอาผลลัพธ์ (ไม่ว่าจะจาก Step 1 หรือ 2) มาคำนวณ Levenshtein ต่อ
	    // ==========================================
	    int num = 0;
	    List<levenshtein> levenshteins = new ArrayList<>();
	    
	    for (registers check : data) {
	        String cleanedDbName = cleanName(check.getRegister_name(), unnecessaryWords);
	        int sim = Otherservice.calculateSimilarity(cleanedName, cleanedDbName);
	        
	        if (sim >= 50) { 
	            levenshtein shteins = new levenshtein();
	            shteins.setDistance(sim);    
	            shteins.setShtein_name1(rawName);
	            shteins.setShtein_name2(check.getRegister_name());
	            shteins.setEdata_id(edata_id);
	            levenshteins.add(shteins);
	            num++;
	        }  
	    }
	    
	    if (!levenshteins.isEmpty()) {
	        Dataset_dataDAO.SaveBatch(levenshteins, edata_id);
	    }
	    
	    System.err.println("Code : 001 Verify -> Duplicate Count: " + num);
	    return num;
	}
	
	
	
	
	@Override
	public int HaveRegister(edata_register edata_registers) {
			String sql = "SELECT COUNT(*) FROM register WHERE  concat(register_code,register_daterun,'-',register_id) = ? ";  
			return jdbcTemplate.queryForObject(sql,new Object[] {edata_registers.getCustomerCode()}, Integer.class);	  
	}
	
	@Override
	public int HaveProduct(edata_product edata_products) {
			String sql = "SELECT COUNT(*) FROM product_master WHERE  concat(product_code,product_daterun,'-',product_id) = ? ";  
			return jdbcTemplate.queryForObject(sql,new Object[] {edata_products.getProductCode()}, Integer.class);	  
	}
	
	
	@Override
	public void removeProblem(int id) {
			String sql = "UPDATE edata SET problem = NULL WHERE  data_id = ? ";  
			jdbcTemplate.update(sql,new Object[] {id});	  
	}
	
	
	@Override
	public void addProblem(String problem,int id) {
			String sql = "UPDATE edata SET problem = ? WHERE  data_id = ? ";  
			jdbcTemplate.update(sql,new Object[] {problem,id});	  
	}
	
	
	
	
    private String normalizeWord(String word) {
    	
  	  
    	Map<String, String> regexNormalizationMap = Map.ofEntries(
    		    Map.entry("ไท(?!ย)", "ไทย"),           // ไท ที่ไม่ใช่ ไทย → ไทย
    		    Map.entry("co\\.", "company"),         // co. → company
    		    Map.entry("กทม", "กรุงเทพ"),          // กทม → กรุงเทพ
    		    Map.entry("ออฟฟิต|ออฟฟิด", "ออฟฟิศ"),  // ออฟฟิต, ออฟฟิด → ออฟฟิศ
    		    Map.entry("เมเนจเมนท์|เมเนจเม้น", "management"), // เมเนจเม้น → management
    		    Map.entry("ซิสเตม|ซิสเต็ม", "system"),  // ซิสเตม → system
    		    Map.entry("คอมพานี|คอมปะนี", "company"), // คอมพานี → company
    		    Map.entry("ลิมิเต็ด", "limited")       // ลิมิเต็ด → limited
    		);
		  
    	 String result = word.toLowerCase();

    	    for (Map.Entry<String, String> entry : regexNormalizationMap.entrySet()) {
    	        result = result.replaceAll(entry.getKey().toLowerCase(), entry.getValue().toLowerCase());
    	    }

    	    return result.trim();
    
	}
    public  String[] smartSplit(String input, double frontPercent) {
        int totalLength = input.length();
        int splitIndex = (int) Math.round(totalLength * frontPercent); // เช่น 70% = 0.7

        // พยายามไม่ตัดกลางคำ (ถ้าใช้ภาษาไทยจริงอาจต้องใช้ lib ตัดคำ)
        // แต่ในที่นี้สมมุติว่าเราตัดตาม index เฉย ๆ
        String front = input.substring(0, splitIndex);
        String back = input.substring(splitIndex);

        return new String[]{front, back};
    }


	public  String cleanName(String name, List<String> unnecessaryWords) {
        for (String word : unnecessaryWords) {
            name = name.replace(word, "");
        }
        // ลบช่องว่างที่เกินออก
        return name;
    }
	
	
	
	
	
	
	@Override
	public int CheckDuplicateStock(edata_stock edata_stocks) {
		  System.err.println("Verify -> Create->VerifyRegiter->Stock_Warehouse->CheckDuplicateStock");
		try {
			
			if (edata_stocks.getStock_type() != null && edata_stocks.getStock_list() != null) {
			    int num = 1;
			    String sql = "SELECT COUNT(*) FROM stock WHERE stock_list=? AND stock_type=?";
			    
			    List<Object> params = new ArrayList<>();
			    params.add(edata_stocks.getStock_list());
			    params.add(edata_stocks.getStock_type());

			    if (edata_stocks.getStock_pd() != null) {
			        sql += " AND stock_pd=?";
			        params.add(edata_stocks.getStock_pd());
			    }

			    if (edata_stocks.getStock_batch() != null) {
			        sql += " AND stock_batch=?";
			        params.add(edata_stocks.getStock_batch());
			    }

			    num = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);

			    System.err.println("Verify -> Create->VerifyRegister->Stock_Warehouse->CheckDuplicateStock->num: " + num);
			    return num;
			} else {
			    return 1;
			}
			
		}catch(Exception e) {
			return 1;
		}
		
		
	}
	
	
	
	@Override
	public int CheckDuplicateWorkrule(edata_workrule edata_workrule) {
		  System.err.println("Verify -> Create->VerifyRegiter->edata_workrule->CheckDuplicateStock");
		try {
			
			if (edata_workrule.getRule_name() != null && edata_workrule.getRule_type() != null) {
			    int num = 1;
			    String sql = "SELECT COUNT(*) FROM workrule WHERE rule_name=? AND rule_type=? ";
			    
			    List<Object> params = new ArrayList<>();
			    params.add(edata_workrule.getRule_name());
			    params.add(edata_workrule.getRule_type());
			    num = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);
			    System.err.println("Verify -> Create->VerifyRegister->edata_workrule->CheckDuplicateStock->num: " + num);
			    return num;
			} else {
			    return 1;
			}
			
		}catch(Exception e) {
			return 1;
		}
		
		
	}
	


	
	
	@Override
	public int CheckDuplicateAnnual_plan(edata_annual_plan edata_annual_plan) {
		  System.err.println("Verify -> Create->VerifyRegiter->edata_annual_plan->CheckDuplicateStock");
		try {
			
			if (edata_annual_plan.getTitle() != null && edata_annual_plan.getAnnual_type() != null) {
			    int num = 1;
			    String sql = "SELECT COUNT(*) FROM annual_plan  WHERE title=? AND annual_type=? ";
			    
			    List<Object> params = new ArrayList<>();
			    params.add(edata_annual_plan.getTitle());
			    params.add(edata_annual_plan.getAnnual_type());
			    num = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);
			    System.err.println("Verify -> Create->VerifyRegister->edata_annual_plan->CheckDuplicateStock->num: " + num);
			    return num;
			} else {
			    return 1;
			}
			
		}catch(Exception e) {
			return 1;
		}
		
		
	}

	@Override
	public int CheckDuplicateAssets(edata_assets edata_assets) {
		  System.err.println("Verify -> Create->VerifyRegiter->edata_assets->CheckDuplicateStock");
		try {
			
			if (edata_assets.getAsset_name() != null && edata_assets.getAsset_type() != null) {
			    int num = 1;
			    String sql = "SELECT COUNT(*) FROM assets  WHERE asset_name=? AND asset_type=? ";
			    
			    List<Object> params = new ArrayList<>();
			    params.add(edata_assets.getAsset_name());
			    params.add(edata_assets.getAsset_type());
			    num = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);
			    System.err.println("Verify -> Create->VerifyRegister->edata_assets->CheckDuplicateStock->num: " + num);
			    return num;
			} else {
			    return 1;
			}
			
		}catch(Exception e) {
			return 1;
		}
		
		
	}
	
	
	
	
	@Override
	public int CheckStock(edata_stock edata_stocks) {
		
		try {
			String sql = "SELECT COUNT(*) FROM  stock WHERE   concat(stock_receipt_date,stock_id) =?  ";
			int num =  jdbcTemplate.queryForObject(sql,new Object[]{edata_stocks.getStock_stockid()},Integer.class);
			return num;
		}catch(Exception e) {
			return 0;
		}
		
		
	}
	
	@Override
	public stock getStock(edata_stock edata_stocks) {
		
		  System.out.println("getStock "+edata_stocks.getStock_stockid());
		
		try {
			
			
			
			
			String sql = "SELECT * FROM  stock WHERE   concat(stock_receipt_date,stock_id) =?  ";
			stock stocks =  jdbcTemplate.queryForObject(sql,new Object[]{edata_stocks.getStock_stockid()},new stockmappers());
			return stocks;
		}catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public stock getSk(int id) {
		
		try {
			String sql = "SELECT * FROM  stock WHERE  id =?  ";
			stock stocks =  jdbcTemplate.queryForObject(sql,new Object[]{id},new stockmappers());
			return stocks;
		}catch(Exception e) {
			return null;
		}
	}
	
	
	
	@Override
	public int SaveWorkRule(edata_workrule edata_workrule, String Code, LoadApp LoadApp) {

	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	    String date = localDate.format(formatter);
	    String date2 = localDate.format(formatter2);

	    // 1. หา rule_id ถัดไป
	    String getMaxRuleIdSQL = "SELECT COALESCE(MAX(ruleId), 0) + 1 FROM workrule";
	    Integer nextRuleId = jdbcTemplate.queryForObject(getMaxRuleIdSQL, Integer.class);

	    // 2. เตรียมข้อมูล
	    Object[] ruleData = {
	        nextRuleId,
	        Code,
	        date, // rule_datarun
	        edata_workrule.getRule_name(),
	        edata_workrule.getRule_type(),
	        edata_workrule.getPosition_name(),
	        "Approved",
	        edata_workrule.getAnnual_leave_days(),
	        edata_workrule.getSick_leave_days(),
	        edata_workrule.getMin_service_months(),
	        edata_workrule.getCarry_forward_allowed(),
	        edata_workrule.getCarry_forward_max_days(),
	        edata_workrule.getEvery_months(),
	        edata_workrule.getAdd_annual_days(),
	        edata_workrule.getAdd_sick_days(),
	        edata_workrule.getMax_annual_days(),
	        edata_workrule.getMax_sick_days(),
	        edata_workrule.getThreshold_minutes(),
	        edata_workrule.getDeduct_per_interval(),
	        edata_workrule.getInterval_minutes(),
	        edata_workrule.getMax_monthly_deduction(),
	        edata_workrule.getAbsent_deduction_per_day(),
	        edata_workrule.getWork_hours(),
	        edata_workrule.getBreak_hours(),
	        edata_workrule.getOvertime_start_after_hours(),
	        edata_workrule.getShift_start_time(),
	        edata_workrule.getShift_end_time(),
	        edata_workrule.getShift_bonus_per(),
	        edata_workrule.getRotation_interval_days(),
	        edata_workrule.getRotation_sequence(),
	        edata_workrule.getCourse_name(),
	        edata_workrule.getDuration_hours(),
	        edata_workrule.getDescription(),
	        edata_workrule.getMandatory(),
	        edata_workrule.getValidity_months(),
	        edata_workrule.getTraining_type()
	    };

	    // 3. SQL สำหรับ workrule
	    String sql = "INSERT INTO workrule(ruleId, rule_code, rule_datarun, rule_name, rule_type, position_name, workrule_status, " +
	                 "annual_leave_days, sick_leave_days, min_service_months, carry_forward_allowed, carry_forward_max_days, " +
	                 "every_months, add_annual_days, add_sick_days, max_annual_days, max_sick_days, threshold_minutes, " +
	                 "deduct_per_interval, interval_minutes, max_monthly_deduction, absent_deduction_per_day, work_hours, break_hours, " +
	                 "overtime_start_after_hours, shift_start_time, shift_end_time, shift_bonus_per, rotation_interval_days, " +
	                 "rotation_sequence, course_name, duration_hours, description, mandatory, validity_months, training_type) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    KeyHolder keyHolder = new GeneratedKeyHolder();

	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        for (int i = 0; i < ruleData.length; i++) {
	            ps.setObject(i + 1, ruleData[i]);
	        }
	        return ps;
	    }, keyHolder);

	    int id_refer = keyHolder.getKey().intValue();

	    // 4. บันทึก Log
	    Object[] logData = {
	        LoadApp.getWorkflow().getWorkflow_id(),
	        LoadApp.getTransition().getTransition_from(),
	        edata_workrule.getRule_type(),
	        edata_workrule.getPosition_name(),
	        date2,
	        LoadApp.getProcess().getId(),
	        id_refer
	    };

	    String logSql = "INSERT INTO workrule_log(workflow_id, id_add, rule_type, position_name, created_at, process_id, id_refer) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    jdbcTemplate.update(logSql, logData);

	    return id_refer;
	}

	
	
	
	
	@Override
	public int SaveMainAnnual_plan(edata_annual_plan edata_annual_plan, String Code, LoadApp LoadApp) {

	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // mm = นาที
	    String date = localDate.format(formatter);
	    String date2 = localDate.format(formatter2);

	    // 1. ค้นหา plan_id ถัดไป
	    String getMaxPlanIdSQL = "SELECT COALESCE(MAX(annualPlanId), 0) + 1 FROM company_annual_plan";
	    Integer nextPlanId = jdbcTemplate.queryForObject(getMaxPlanIdSQL, Integer.class);

	    // 2. เตรียมข้อมูลสำหรับ insert
	    Object[] planData = {
	        nextPlanId,
	        edata_annual_plan.getAnnual_plan_code(),
	        date2,
	        edata_annual_plan.getAnnual_year(),
	        edata_annual_plan.getDepartment(),
	        edata_annual_plan.getAnnual_budget(),
	        edata_annual_plan.getGeneral_target(),
	        edata_annual_plan.getMax_personnel(),
	        edata_annual_plan.getProduction_target_units(),
	        edata_annual_plan.getProduction_target_revenue(),
	        edata_annual_plan.getMax_downtime_minutes(),
	        edata_annual_plan.getMin_machine_speed(),
	        edata_annual_plan.getTotal_jobs(),
	        edata_annual_plan.getMachinery_id(),
	        edata_annual_plan.getNotes(),
	        edata_annual_plan.getTitle(),
	        edata_annual_plan.getAnnual_type(),
	        "Approved"
	    };

	    // 3. SQL Insert
	    String sql = "INSERT INTO company_annual_plan(" +
	                 "annualPlanId, annual_plan_code, annual_dateadd, annual_year, department, annual_budget, general_target, " +
	                 "max_personnel, production_target_units, production_target_revenue, max_downtime_minutes, " +
	                 "min_machine_speed, total_jobs, machinery_id, notes, plan_status,title,annual_type) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    KeyHolder keyHolder = new GeneratedKeyHolder();

	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        for (int i = 0; i < planData.length; i++) {
	            ps.setObject(i + 1, planData[i]);
	        }
	        return ps;
	    }, keyHolder);

	    int id_refer = keyHolder.getKey().intValue();

	    // 4. บันทึก log
	    Object[] logData = {
	        LoadApp.getWorkflow().getWorkflow_id(),
	        LoadApp.getTransition().getTransition_from(),
	        edata_annual_plan.getTitle(),
	        edata_annual_plan.getAnnual_type(),
	        date2,
	        LoadApp.getProcess().getId(),
	        id_refer
	    };

	    String logSql = "INSERT INTO company_annual_plan_log(workflow_id, id_add, title, annual_type, created_at, process_id, id_refer) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    jdbcTemplate.update(logSql, logData);

	    return id_refer;
	}
	
	
	
	
	@Override
	public int SaveMainAssets(edata_assets edata_assets, String Code, LoadApp LoadApp) {

	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Note: Corrected "MM" to "mm"
	    String date = localDate.format(formatter);
	    String date2 = localDate.format(formatter2);

	    // ค้นหา asset_id ล่าสุด
	    String getMaxIdSQL = "SELECT COALESCE(MAX(assetId), 0) + 1 FROM asset";
	    Integer nextAssetId = jdbcTemplate.queryForObject(getMaxIdSQL, Integer.class);

	    // เตรียมข้อมูลสำหรับ insert
	    Object[] assetData = {
	        nextAssetId,
	        Code,
	        date,
	        edata_assets.getAsset_name(),
	        edata_assets.getAsset_group(),
	        edata_assets.getAsset_type(),
	        edata_assets.getAsset_description(),
	        edata_assets.getAcquisition_cost(),
	        edata_assets.getUseful_life_years(),
	        edata_assets.getDepreciation_method(),
	        edata_assets.getBook_value(),
	        edata_assets.getDoc(),
	        "Approved"
	    };
	    
	  

	    String sql = "INSERT INTO asset (assetId, asset_code, assetDateAdd, asset_name, asset_group, asset_type, asset_description, acquisition_cost, " +
	                 "useful_life_years, depreciation_method, book_value, doc, asset_status) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    KeyHolder keyHolder = new GeneratedKeyHolder();

	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        for (int i = 0; i < assetData.length; i++) {
	            ps.setObject(i + 1, assetData[i]);
	        }
	        return ps;
	    }, keyHolder);

	    int id_refer = keyHolder.getKey().intValue();

	    // บันทึก log
	    Object[] logData = {
	        LoadApp.getWorkflow().getWorkflow_id(),
	        LoadApp.getTransition().getTransition_from(),
	        edata_assets.getAsset_type(),
	        edata_assets.getAsset_group(),
	        date2,
	        LoadApp.getProcess().getId(),
	        id_refer,
	        LoadApp.getEdata().getId(),
	    };

	    String logSql = "INSERT INTO asset_log(workflow_id, id_add, asset_type, asset_group, created_at, process_id, id_refer, edata_id) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    jdbcTemplate.update(logSql, logData);

	    return id_refer;
	}
	
	
	
	
	
	@Override
	public int SaveMainRegister(edata_register edata_registers, String Code,LoadApp LoadApp) {
 
		  LocalDateTime localDate = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	        String date = localDate.format(formatter);
	        String date2 = localDate.format(formatter2);
	        String type = "Insert";
	        
	        
	        Integer[] save =   SaveMainRegister(edata_registers,Code);
	        
	        int id_refer =   save[1];
	    	Object[] DataInfo = { 
					LoadApp.getWorkflow().getWorkflow_id(),
					LoadApp.getEdata().getData_idadd(),
					 edata_registers.getRegister_type(),
					 edata_registers.getRegister_groups(),
					 date2,
					LoadApp.getProcess().getId(),
					id_refer,
					LoadApp.getEdata().getData_id(),

					};
			
					String sqls = "INSERT INTO register_log(workflow_id,id_add,register_type,register_group,created_at,process_id,id_refer,edata_id) "
					+ "VALUES(?,?,?,?,?,?,?,?) ";
					jdbcTemplate.update(sqls,DataInfo);
	        
	        
					DocumentEffect Document_effect = new DocumentEffect();
					
					String codeid =Code+date+"-"+save[0]+" "+ edata_registers.getRegister_name();
					
					Document_effect.setEdata_id(LoadApp.getEdata().getData_id());
					Document_effect.setTitle(codeid);
					Document_effect.setType("effect.INSERT");
					Document_effect.setData_type("register");
					Document_effect.setId_refer(id_refer);
					
					Document_effect.setId_new(edata_registers.getId());
				    DocumentEffect old = getDocumentEffectByIdRefer("register",edata_registers.getId());
					if(old != null) {
						Document_effect.setId_old(old.getId_new());
					}

					
					
					
					SaveDocument_effect(Document_effect);

	        return id_refer;

	}

	
	@Override
	public void DeleteMainRegister(int id) {
		   String sql = "DELETE FROM  register where id =? ";
		   jdbcTemplate.update(sql,new Object[]{ id});
	}
	
	@Override
	public Integer[] SaveMainRegister(edata_register edata_registers, String Code) {
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    // 🌟 แก้ไข: ใช้ mm (พิมพ์เล็ก) สำหรับนาที และเพิ่ม ss สำหรับวินาที
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
	    String date = localDate.format(formatter);
	    String date2 = localDate.format(formatter2);

	    // ค้นหา register_id ล่าสุดของ register_name และ register_type เดียวกัน
	    String getMaxIdSQL = "SELECT COALESCE(MAX(register_id), 0) + 1 FROM register WHERE register_code = ? ";
	    Integer nextRegisterId = jdbcTemplate.queryForObject(getMaxIdSQL, new Object[]{Code}, Integer.class);

	    // สร้างข้อมูลสำหรับการ INSERT (ทั้งหมด 37 ฟิลด์)
	    Object[] edata_registerInfo = { 
	        nextRegisterId, // 1. register_id ที่ถูกเพิ่มขึ้น
	        edata_registers.getRegister_name(), // 2
	        Code, // 3
	        date, // 4
	        edata_registers.getRegister_type(), // 5
	        edata_registers.getRegister_groups(), // 6
	        edata_registers.getRegister_address(), // 7
	        edata_registers.getRegister_fulladdress(), // 7
	        edata_registers.getRegister_note(), // 8
	        edata_registers.getRegister_other_conditions(), // 9
	        edata_registers.getRegister_status() == null ? "ACTIVE" : edata_registers.getRegister_status(), // 10
	        edata_registers.getContact_person(), // 11
	        edata_registers.getPhone_number(), // 12
	        edata_registers.getMobile_number(), // 13
	        edata_registers.getWebsite(), // 14
	        edata_registers.getSocial(), // 15
	        edata_registers.getTax_id(), // 16
	        edata_registers.getBranch_code(), // 17
	        edata_registers.getBusiness_type(), // 18
	        edata_registers.getPayment_terms(), // 19
	        edata_registers.getCredit_limit(), // 20
	        edata_registers.getBank_account(), // 21
	        edata_registers.getPreferred_currency(), // 22
	        date2, // 23. created_at
	        
	        // 🌟 --- ข้อมูลที่เพิ่มใหม่ 14 ฟิลด์ --- 🌟
	        edata_registers.getParty_type(), // 24. บุคคลธรรมดา / นิติบุคคล
	        edata_registers.getProduct_category(), // 25
	        edata_registers.getRegister_title(), // 26. บจก / วิสาหกิจชุมชน
	        edata_registers.getFax(), // 27
	        edata_registers.getPerson_position(), // 28
	        edata_registers.getPerson_number(), // 29
	        edata_registers.getContact_approvers(), // 30
	        edata_registers.getApprovers_position(), // 31
	        edata_registers.getApprovers_number(), // 32
	        edata_registers.getApprovers_email(), // 33
	        edata_registers.getPerson_email(), // 33
	        edata_registers.getRegion(), // 34
	        edata_registers.getLat(), // 35
	        edata_registers.getLon(), // 36
	        edata_registers.getDbd(), // 37
	        edata_registers.getRegister_email() // 37
	    };

	    // คำสั่ง SQL เพิ่มคอลัมน์ใหม่ต่อท้าย และเพิ่ม ? ให้ครบ 37 ตัว
	    String sql = "INSERT INTO register (register_id, register_name, register_code, register_daterun, register_type, "
	               + "register_groups, register_address, register_fulladdress, register_note, register_other_conditions, register_status, "
	               + "contact_person, phone_number, mobile_number, website, social, tax_id, branch_code, "
	               + "business_type, payment_terms, credit_limit, bank_account, preferred_currency, created_at, "
	               // --- ชื่อคอลัมน์ใหม่ 14 คอลัมน์ ---
	               + "party_type, product_category, register_title, fax, person_position, person_number, "
	               + "contact_approvers, approvers_position, approvers_number, approvers_email, person_email, "
	               + "region, lat, lon, dbd,register_email) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	               // --- ? สำหรับคอลัมน์ใหม่ 14 ตัว ---
	               + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        for (int i = 0; i < edata_registerInfo.length; i++) {
	            ps.setObject(i + 1, edata_registerInfo[i]);
	        }
	        return ps;
	    }, keyHolder);
	    
	    // ดึงค่า ID ที่ Auto Increment ไว้คืนกลับไป
	    int id_refer = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
	    Integer[] save = new Integer[2];
	    save[0] = nextRegisterId;
	    save[1] = id_refer;
	    return save;
	}
	
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public List<Integer[]> SaveAllMainProfile(List<edata_register> edata_registerList, String Code) {
	    
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 🌟 ใช้ mm ถูกต้องแล้วครับ
	    String date = localDate.format(formatter);
	    String date2 = localDate.format(formatter2);

	    // 🌟 1. ดึงเลขสูงสุดแค่ "ครั้งเดียว" ก่อนเริ่มลูป
	    String getMaxIdSQL = "SELECT COALESCE(MAX(register_id), 0) FROM register WHERE register_code = ? ";
	    Integer currentMaxId = jdbcTemplate.queryForObject(getMaxIdSQL, new Object[]{Code}, Integer.class);
	    if (currentMaxId == null) currentMaxId = 0;

	    // 🌟 2. อัพเดท SQL เพิ่ม 14 คอลัมน์ใหม่ และเพิ่ม ? ให้ครบ 37 ตัว
	    String sql = "INSERT INTO register (register_id, register_name, register_code, register_daterun, register_type, "
	               + "register_groups, register_address, register_fulladdress, register_note, register_other_conditions, register_status, "
	               + "contact_person, phone_number, mobile_number, website, social, tax_id, branch_code, "
	               + "business_type, payment_terms, credit_limit, bank_account, preferred_currency, created_at, "
	               // --- ชื่อคอลัมน์ใหม่ 14 คอลัมน์ ---
	               + "party_type, product_category, register_title, fax, person_position, person_number, "
	               + "contact_approvers, approvers_position, approvers_number, approvers_email,person_email,"
	               + "region, lat, lon, dbd,date_insert_oldsys,merged_duplicate_ids,id_oldsys) "
	               + "VALUES ("
	               + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	               + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	               + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	               + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
	               + ")";

	    
	    // List สำหรับเก็บ ID ที่ Insert สำเร็จ เพื่อส่งคืนกลับไป
	    List<Integer[]> savedIdsList = new ArrayList<>();

	    // 🌟 3. วนลูป Insert ทีละรายการ (ทำงานใน Transaction เดียวกัน)
	    for (edata_register edata_registers : edata_registerList) {
	        
	        // บวกเลขใน Memory ทันที
	        currentMaxId++;
	        final Integer nextRegisterId = currentMaxId; 

	        // 🌟 4. เตรียมข้อมูลสำหรับ Insert (37 ฟิลด์)
	        Object[] edata_registerInfo = { 
	        		nextRegisterId,                              // 1
	        	    edata_registers.getRegister_name(),          // 2
	        	    Code,                                        // 3  <-- ตัวแปรแทรก
	        	    date,                                        // 4  <-- ตัวแปรแทรก
	        	    edata_registers.getRegister_type(),          // 5
	        	    edata_registers.getRegister_groups(),        // 6
	        	    edata_registers.getRegister_address(),       // 7
	        	    edata_registers.getRegister_fulladdress(),   // 8
	        	    edata_registers.getRegister_note(),          // 9
	        	    edata_registers.getRegister_other_conditions(), // 10
	        	    
	        	    edata_registers.getRegister_status() == null ? "ACTIVE" : edata_registers.getRegister_status(), // 11
	        	    edata_registers.getContact_person(),         // 12
	        	    edata_registers.getPhone_number(),           // 13
	        	    edata_registers.getMobile_number(),          // 14
	        	    edata_registers.getWebsite(),                // 15
	        	    edata_registers.getSocial(),                 // 16
	        	    edata_registers.getTax_id(),                 // 17
	        	    edata_registers.getBranch_code(),            // 18
	        	    edata_registers.getBusiness_type(),          // 19
	        	    edata_registers.getPayment_terms(),          // 20
	        	    
	        	    edata_registers.getCredit_limit(),           // 21
	        	    edata_registers.getBank_account(),           // 22
	        	    edata_registers.getPreferred_currency(),     // 23
	        	    date2,                                       // 24 <-- ตัวแปรแทรก (created_at)
	        	    edata_registers.getParty_type(),             // 25
	        	    edata_registers.getProduct_category(),       // 26
	        	    edata_registers.getRegister_title(),         // 27
	        	    edata_registers.getFax(),                    // 28
	        	    edata_registers.getPerson_position(),        // 29
	        	    edata_registers.getPerson_number(),          // 30
	        	    edata_registers.getContact_approvers(),      // 31
	        	    
	        	    edata_registers.getApprovers_position(),     // 32
	        	    edata_registers.getApprovers_number(),       // 33
	        	    edata_registers.getApprovers_email(),        // 34
	        	    edata_registers.getPerson_email(),           // 35
	        	    edata_registers.getRegion(),                 // 36
	        	    edata_registers.getLat(),                    // 37
	        	    edata_registers.getLon(),                    // 38
	        	    edata_registers.getDbd(),                    // 39
	        	    edata_registers.getDate_insert_oldsys(),     // 40
	        	    edata_registers.getMerged_duplicate_ids(),   // 41
	        	    edata_registers.getId_oldsys()               // 42
	        };


	        
	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        
	        jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            for (int i = 0; i < edata_registerInfo.length; i++) {
	                ps.setObject(i + 1, edata_registerInfo[i]);
	            }
	            return ps;
	        }, keyHolder);
	        
	        // ดึง Auto Increment ID (id_refer)
	        int id_refer = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
	        
	        // เก็บลง Array แล้วยัดใส่ List
	        Integer[] save = new Integer[2];
	        save[0] = nextRegisterId;
	        save[1] = id_refer;
	        savedIdsList.add(save);
	    }

	    // 🌟 5. คืนค่า List ของ ID ทั้งหมดกลับไป
	    return savedIdsList;
	}
     
     
     
     
	@Override
	public int UpdateMainRegister(edata_register edata_registers,LoadApp LoadApp,String type) {

		 			int id_refers =UpdateMainRegister(edata_registers,type);
	        
	        		String effect ="effect.UPDATE";
	                if(type =="PENDING_DELETE") {
	                	  effect ="effect.PENDING_DELETE";
	                }
	                else if(type =="ACTIVE") {
	                	 effect ="effect.UPDATE";
	                }
	                else {
	                	  effect ="effect.CHANGE_STATUS";
	                } 
	        
					DocumentEffect Document_effect = new DocumentEffect();
					
					String codeid =edata_registers.getCustomerCode()+" "+edata_registers.getRegister_name();
					Document_effect.setEdata_id(LoadApp.getEdata().getData_id());
					Document_effect.setTitle(codeid);
					Document_effect.setType(effect);
					Document_effect.setData_type("register");
					Document_effect.setId_refer(id_refers);
					
					Document_effect.setId_new(edata_registers.getId());
				    DocumentEffect old = getDocumentEffectByIdRefer("register",id_refers);
					if(old != null) {
						Document_effect.setId_old(old.getId_new());
					}

					
					
					
					SaveDocument_effect(Document_effect);

	        return 1;

	}

	
	
	@Override
	public int UpdateMainRegister(edata_register edata_registers, String type) {
	    LocalDateTime localDate = LocalDateTime.now();
	    // 🌟 แก้ไข: ใช้ mm (พิมพ์เล็ก) สำหรับนาที และเพิ่ม ss สำหรับวินาที
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String date2 = localDate.format(formatter);

	    // 🌟 สร้างข้อมูลสำหรับการ UPDATE (ทั้งหมด 35 พารามิเตอร์)
	    Object[] edata_registerInfo = { 
	        edata_registers.getRegister_name(), // 1
	        edata_registers.getRegister_type(), // 2
	        edata_registers.getRegister_groups(), // 3
	        edata_registers.getRegister_address(), // 4
	        edata_registers.getRegister_fulladdress(), // 4
	        edata_registers.getRegister_note(), // 5
	        edata_registers.getRegister_other_conditions(), // 6
	        edata_registers.getContact_person(), // 7
	        edata_registers.getPhone_number(), // 8
	        edata_registers.getMobile_number(), // 9
	        edata_registers.getWebsite(), // 10
	        edata_registers.getSocial(), // 11
	        edata_registers.getTax_id(), // 12
	        edata_registers.getBranch_code(), // 13
	        edata_registers.getBusiness_type(), // 14
	        edata_registers.getPayment_terms(), // 15
	        edata_registers.getCredit_limit(), // 16
	        edata_registers.getBank_account(), // 17 (🌟 เพิ่มให้ตรงกับ Insert)
	        edata_registers.getPreferred_currency(), // 18
	        
	        // --- 🌟 ข้อมูลที่เพิ่มใหม่ 14 ฟิลด์ ---
	        edata_registers.getParty_type(), // 19
	        edata_registers.getProduct_category(), // 20
	        edata_registers.getRegister_title(), // 21
	        edata_registers.getFax(), // 22
	        edata_registers.getPerson_position(), // 23
	        edata_registers.getPerson_number(), // 24
	        edata_registers.getContact_approvers(), // 25
	        edata_registers.getApprovers_position(), // 26
	        edata_registers.getApprovers_number(), // 27
	        edata_registers.getApprovers_email(), // 28
	        edata_registers.getPerson_email(), // 28
	        edata_registers.getRegion(), // 29
	        edata_registers.getLat(), // 30
	        edata_registers.getLon(), // 31
	        edata_registers.getDbd(), // 32
	        edata_registers.getRegister_email(),
	        
	        
	        // --- ข้อมูลสถานะและเงื่อนไข WHERE ---
	        date2, // 33. updated_at
	        type,  // 34. register_status
	        edata_registers.getCustomerCode() // 35. เงื่อนไขสำหรับ WHERE (concat)
	    };

	    // 🌟 คำสั่ง SQL อัพเดทฟิลด์ทั้งหมดให้ตรงกับ Array ด้านบน
	    String sql = "UPDATE register SET register_name=?, register_type=?, register_groups=?, register_address=?,register_fulladdress=?, "
	               + "register_note=?, register_other_conditions=?, contact_person=?, phone_number=?, mobile_number=?, "
	               + "website=?, social=?, tax_id=?, branch_code=?, business_type=?, payment_terms=?, credit_limit=?, "
	               + "bank_account=?, preferred_currency=?, "
	               // --- ชื่อคอลัมน์ใหม่ 14 คอลัมน์ ---
	               + "party_type=?, product_category=?, register_title=?, fax=?, person_position=?, person_number=?, "
	               + "contact_approvers=?, approvers_position=?, approvers_number=?, approvers_email=?,person_email=?, "
	               + "region=?, lat=?, lon=?, dbd=?,register_email=?, "
	               // --- ข้อมูลลงเวลาและสถานะ ---
	               + "updated_at=?, register_status=?, revision = revision+1 "
	               + "WHERE concat(register_code, register_daterun, '-', register_id) = ? ";

	    // เนื่องจากเป็นคำสั่ง UPDATE ไม่จำเป็นต้อง return key กลับไป (Method return เป็น void อยู่แล้ว)
	    // แต่เก็บโครงสร้างเดิมของคุณไว้เพื่อให้โค้ดสอดคล้องกันครับ
	    KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
	    jdbcTemplate.update(connection -> {
	        // ใช้ RETURN_GENERATED_KEYS เผื่อไว้แบบที่คุณเขียนมาครับ ไม่มีผลเสียอะไร
	        PreparedStatement ps = connection.prepareStatement(sql,  new String[]{"id"});
	        for (int i = 0; i < edata_registerInfo.length; i++) {
	            ps.setObject(i + 1, edata_registerInfo[i]);
	        }
	        return ps;
	    }, keyHolder);
	    
	    return  1;
	    
	}



	@Override
	public int UpdateMainRegisterStatus(edata_register edata_registers,LoadApp LoadApp,String type) {

	        LocalDateTime localDate = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
	        String date2 = localDate.format(formatter);


	        // สร้างข้อมูลสำหรับการ INSERT
	        Object[] edata_registerInfo = { 

				date2,
				type,
				edata_registers.getCustomerCode()
	        };

	        String sql = "UPDATE register SET updated_at=?,register_status=? WHERE" 
	        		+ " concat(register_code,register_daterun,'-',register_id) = ? ";

	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        
	        jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            for (int i = 0; i < edata_registerInfo.length; i++) {
	                ps.setObject(i + 1, edata_registerInfo[i]);
	            }
	            return ps;
	        }, keyHolder);
	        
	        		String effect ="effect.UPDATE";
	                if(type =="PENDING_DELETE") {
	                	  effect ="effect.PENDING_DELETE";
	                }
	                else if(type =="ACTIVE") {
	                	 effect ="effect.UPDATE";
	                }
	                else {
	                	  effect ="effect.CHANGE_STATUS";
	                } 
	        
	                String sqls = "SELECT * FROM   register WHERE" 
	    	        		+ " concat(register_code,register_daterun,'-',register_id) = ? ";
	                registers registerold = jdbcTemplate.queryForObject(sqls, new Object[] {edata_registers.getCustomerCode()}, new  registersmapper());
	                
					DocumentEffect Document_effect = new DocumentEffect();
					
					String codeid =edata_registers.getCustomerCode()+" "+edata_registers.getRegister_name();
					Document_effect.setEdata_id(LoadApp.getEdata().getData_id());
					Document_effect.setTitle(codeid);
					Document_effect.setType(effect);
					Document_effect.setData_type("register");
					Document_effect.setId_refer(registerold.getId());
			
					
					
					SaveDocument_effect(Document_effect);

	        return 1;

	}

	
	
	
	
	
	@Override
	public int SaveMainStock(edata_stock edata_stocks,String Code,LoadApp LoadApp) {
		
		
		
			   LocalDateTime localDate = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
		        String date = localDate.format(formatter);
		        String date2 = localDate.format(formatter2);
		
	   	 
	        // ค้นหา register_id ล่าสุดของ register_name และ register_type เดียวกัน
	        String getMaxIdSQL = "SELECT COALESCE(MAX(stock_id), 0) + 1 FROM stock ";
	        Integer nextRegisterId = jdbcTemplate.queryForObject(getMaxIdSQL, Integer.class);
	   	 
	   	 
		Object[] edata_stocksInfo = { 
				nextRegisterId,
				edata_stocks.getStock_list(),
				edata_stocks.getStock_note(),
				edata_stocks.getStock_type(),
				edata_stocks.getStock_group(),
				edata_stocks.getStock_unit(),
				date,
				Stock_status.OUT_OF_STOCK.toString(),
				edata_stocks.getStock_max(),
				edata_stocks.getStock_min(),
				edata_stocks.getStock_pd() == null ? "-":edata_stocks.getStock_pd(),
				edata_stocks.getStock_batch()== null ? "-":edata_stocks.getStock_batch(),
			    Code
				};
		
		

		
			String sql = "INSERT IGNORE INTO stock(stock_id,stock_list,stock_details,stock_type,stock_group,stock_unit,stock_receipt_date"
					+ ",stock_status,stock_max,stock_min,stock_pd,stock_batch,stock_code) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
				System.out.println("length " + edata_stocksInfo.length);
			
			
			jdbcTemplate.update(connection -> {
			    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			    for (int i = 0; i < edata_stocksInfo.length; i++) {
			        ps.setObject(i + 1, edata_stocksInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
			    }
			    return ps;
			}, keyHolder);

			

		
			int id_refer = keyHolder.getKey().intValue();
			
			
		   	Object[] DataInfo = { 
					LoadApp.getWorkflow().getWorkflow_id(),
					LoadApp.getTransition().getTransition_from(),
					edata_stocks.getStock_type(),
					edata_stocks.getStock_group(),
					date2,
					LoadApp.getEdata().getData_id(),
					LoadApp.getProcess().getId(),
					id_refer
					};
			
					String sqls = "INSERT INTO stock_log(workflow_id,id_add,stock_type,stock_group,created_at,edata_id,process_id,id_refer) "
					+ "VALUES(?,?,?,?,?,?,?,?) ";
					jdbcTemplate.update(sqls,DataInfo);
			System.out.println("test ..... ");
					
					
			// คืนค่า id ที่ถูกสร้างขึ้น
			return id_refer;
			
		//	 try {} catch (Exception e) { }
		//	 return 0;
	       
			
	}
	
	
	
	
	
	
	
	
	@Override
	public int SaveMainStock(edata_stock edata_stocks,int workflowID,String Status,LoadApp LoadApp) {
		
	 	 LocalDateTime localDate = LocalDateTime.now();//For reference
	   	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	   	 DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	   	 String date = localDate.format(formatter);
	     String date2 = localDate.format(formatter2);
	     
	     
		Object[] edata_stocksInfo = { 
				edata_stocks.getStock_list(),
				edata_stocks.getStock_note(),
				edata_stocks.getStock_type(),
				edata_stocks.getStock_unit(),
				date,
				edata_stocks.getEdata_id(),
				workflowID,
				Status,
				edata_stocks.getStock_max(),
				edata_stocks.getStock_min(),
				edata_stocks.getStock_pd(),
				edata_stocks.getStock_batch(),
				};
		
		

		
			String sql = "INSERT IGNORE INTO stock(stock_list,stock_details,stock_type,stock_unit,stock_receipt_date,ir_idrefer,stock_workflow"
					+ ",stock_status,stock_max,stock_min,stock_pd,stock_batch) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
				System.out.println("length " + edata_stocksInfo.length);
			
			
			jdbcTemplate.update(connection -> {
			    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			    for (int i = 0; i < edata_stocksInfo.length; i++) {
			        ps.setObject(i + 1, edata_stocksInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
			    }
			    return ps;
			}, keyHolder);


			
			int id_refer = keyHolder.getKey().intValue();
			
			
		   	Object[] DataInfo = { 
					LoadApp.getWorkflow().getWorkflow_id(),
					LoadApp.getTransition().getTransition_from(),
					edata_stocks.getStock_type(),
					edata_stocks.getStock_group(),
					date2,
					LoadApp.getEdata().getData_id(),
					LoadApp.getProcess().getId(),
					id_refer
					};
			
					String sqls = "INSERT INTO stock_log(workflow_id,id_add,stock_type,stock_group,created_at,edata_id,process_id,id_refer) "
					+ "VALUES(?,?,?,?,?,?,?,?) ";
					jdbcTemplate.update(sqls,DataInfo);
			

					// คืนค่า id ที่ถูกสร้างขึ้น
					return id_refer;
			
			
			
			
			
			
	}
	
	
	
	@Override
	public int UpdateStockOptimistic(edata_procurement edata_procurements) {
			int status = 0;
			
		if (edata_procurements.getQuantity_received() instanceof Double && Math.abs((Double) edata_procurements.getQuantity_received() - 0.0000001) < 1e-7) { return 1; }

		 try {  
			 System.out.println("stock update--- "+edata_procurements.getProcurement_itemid()+" --- "+edata_procurements.getQuantity_received() );
			 
			    String sql = "UPDATE stock " +
		                 "SET stock_price = COALESCE(stock_price, 0) + ?,stock_remaining = COALESCE(stock_remaining, 0) + ?, " +
		                 "    stock_status = CASE " +
		                 "        WHEN stock_remaining + ? <= stock_min THEN ? " +
		                 "        WHEN stock_remaining + ? > stock_min AND stock_remaining + ? < stock_max THEN ? " +
		                 "        WHEN stock_remaining + ? >= stock_max THEN ? " +
		                 "        ELSE stock_status " +
		                 "    END " +
		                 "WHERE concat(COALESCE(stock_code, ''), stock_receipt_date, stock_id) = ?";

				 status =  jdbcTemplate.update(sql, 
					edata_procurements.getProcurement_unit_price(),
		    		edata_procurements.getQuantity_received(), 
		    		edata_procurements.getQuantity_received(), Stock_status.OUT_OF_STOCK.name(), 
		    		edata_procurements.getQuantity_received(), edata_procurements.getQuantity_received(), Stock_status.IN_STOCK.name(), 
		    		edata_procurements.getQuantity_received(), Stock_status.HEIGHT_STOCK.name(), 
		    		edata_procurements.getProcurement_itemid()
		    );

			 	  } catch (Exception e) {
	        	throw new RuntimeException("Update error."); 
	           
	        } 
		
		
	
		 return  status;
	}
	
		
	
	@Override
	public int TransactionStockOptimistic(edata_stock edata_stocks) {


		 System.out.println("stock update--- "+edata_stocks.getIssued_amount()+" --- "+edata_stocks.getStock_stockid() );
		
		
        if(Transaction != edata_stocks.getEdata_id()) {
        	
     
		       	 String sql = "UPDATE stock SET stock_remaining =  stock_remaining - ?  WHERE concat(stock_receipt_date,stock_id) =?  ";
			        return jdbcTemplate.update(sql,new Object[]{edata_stocks.getIssued_amount(),edata_stocks.getStock_stockid()});
		        }else {
		       	 return 0;
		        }
        
        
	}
	@Override
	public List<DocumentEffect> getAllDocumentEffect(int id_refer) {

					    String sql = "SELECT * from document_effect WHERE id_refer = ?  ORDER BY ID DESC  limit 1000  ";
						 List<DocumentEffect> listData =  jdbcTemplate.query(sql,new Object[]{id_refer},new DocumentEffectmappers());
						 return listData;
	}
	

	@Override
	public DocumentEffect getDocumentEffect(int edata_id) {

				  try {
					    String sql = "SELECT * from document_effect WHERE edata_id = ?  limit 1 ";
						 DocumentEffect data =  jdbcTemplate.queryForObject(sql,new Object[]{edata_id},new DocumentEffectmappers());
						 return data;
				 
				 }catch(EmptyResultDataAccessException e) {
				
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one getbyid with the same Id .......");	 
				 }
	
	}
	@Override
	public DocumentEffect getDocumentEffectByIdRefer(String type,int id_refer) {

				  try {
					    String sql = "SELECT * from document_effect WHERE data_type=? AND id_refer = ? order by id_new DESC  limit 1 ";
						 DocumentEffect data =  jdbcTemplate.queryForObject(sql,new Object[]{type,id_refer},new DocumentEffectmappers());
						 return data;
				 
				 }catch(EmptyResultDataAccessException e) {
				
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one getbyid with the same Id .......");	 
				 }
	
	}
	
	
	
	

	public static int getTransaction() {
		return Transaction;
	}


	@Override
	public  void setTransaction(int transaction) {
		Transaction = transaction;
	}



	@Override
	public int savejoin(edata edatas) {
		Object[] edataInfo = { 
				edatas.getData_planid(),
				edatas.getData_eformid(),
				edatas.getData_eformrv(),
				edatas.getData_join(),
				edatas.getData_dateadd(),
				edatas.getData_filepath(),
				edatas.getData_nameadd(),
				edatas.getReport_id()
				};
		
				String sql = "INSERT INTO edata(data_planid,data_eformid,data_eformrv,data_join,data_dateadd,data_filepath,data_nameadd,report_id) "
						+ "VALUES(?,?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,edataInfo);
	}

	
	

	@Override
	public int setapp(int id,String app) {

				String sql = "UPDATE edata SET data_nameapp =?  WHERE data_id= ?";
				return jdbcTemplate.update(sql,new Object[]{app,id});
	}
	
	@Override
	public int setTurnFlow(int id) {
				String sql = "UPDATE edata SET data_flow =1  WHERE data_planid= ? and  data_flow <=  0 ";
				return jdbcTemplate.update(sql,new Object[]{id});
	}
	
	
	@Override
	public int setUnTurnFlow(int id) {
				String sql = "UPDATE edata SET data_flow =0  WHERE data_planid= ? and  data_flow =  1 ";
				return jdbcTemplate.update(sql,new Object[]{id});
	}
	
	
	
	
	@Override
	public int setapp(int id,String user_app,int flow,String Status) {

				String sql = "UPDATE edata SET data_nameapp =?,data_flow =?,data_status =?    WHERE data_id= ?";
				return jdbcTemplate.update(sql,new Object[]{user_app,flow,Status,id});
	}

	@Override
	public int setapp(int id,String user_app,int flow,String Status,String note,String date) {

				String sql = "UPDATE edata SET data_nameapp =?,data_flow =?,data_status =? ,data_comment =?,data_datecheck=?    WHERE data_id= ?";
				return jdbcTemplate.update(sql,new Object[]{user_app,flow,Status,note,date,id});
	}
	
	
	
	
	@Override
	public edata getbyid(int id) {
	

		  try { 
				String sql = "SELECT edata.*,doc.*  FROM edata  LEFT JOIN doc ON doc.doc_idrefer = edata.data_id  where edata.data_id= ?   ";
				edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new edatamappers());
				return edatas;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 edata edatas  =new edata();
			 edatas.setData_id(0);
			 return edatas;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getbyid with the same Id .......");
		   
		 }
	
		
		
	}

	
	
	@Override
	public int countLinK(String doc) {
	
		String sql = "SELECT COUNT(*) FROM relations where relations_doc_code = ? ";
		return jdbcTemplate.queryForObject(sql,new Object[]{doc},Integer.class);

	}
	
	


	@Override
	public int setdue(int order_postion,int balance_postion,int due_postion,int cal_condition) {
	

		 String sql = "UPDATE edata  SET";
	
	 
		if(due_postion == 1) { sql = sql +  "   data_value1 =    ";  }
		else if(due_postion == 2) { sql = sql +  "   data_value2 =  "; }
		else if(due_postion == 3) { sql = sql +  "   data_value3 =  "; }
		else if(due_postion == 4) { sql = sql +  "   data_value4 =  "; }
		else if(due_postion == 5) { sql = sql +  "   data_value5 =  "; }
		else if(due_postion == 6) { sql = sql +  "   data_value6 =  "; }
		else if(due_postion == 7) { sql = sql +  "   data_value7 =  "; }
		else if(due_postion == 8) { sql = sql +  "  and data_value8 =  "; }
		else if(due_postion == 9) { sql = sql +  "   data_value9 =  "; }
		else if(due_postion == 10) { sql = sql +  "   data_value10 =  "; }
		else if(due_postion == 11) { sql = sql +  "   data_value11 =  "; }
		else if(due_postion == 12) { sql = sql +  "   data_value12 =  "; }
		else if(due_postion == 13) { sql = sql +  "   data_value13 =  "; }
		else if(due_postion == 14) { sql = sql +  "   data_value14 =  "; }
		else if(due_postion == 15) { sql = sql +  "   data_value15 =  "; }
		else if(due_postion == 16) { sql = sql +  "   data_value16 =  "; }
		else if(due_postion == 17) { sql = sql +  "   data_value17 =  "; }
		else if(due_postion == 18) { sql = sql +  "   data_value18 =  "; }
		else if(due_postion == 19) { sql = sql +  "   data_value19 =  "; }
		else if(due_postion == 20) { sql = sql +  "   data_value20 =  "; }

		
		
		if(order_postion == 1) { sql = sql +  "   data_value1 -     ";  }
		else if(order_postion == 2) { sql = sql +  "   data_value2 -  "; }
		else if(order_postion == 3) { sql = sql +  "   data_value3 -  "; }
		else if(order_postion == 4) { sql = sql +  "   data_value4 -  "; }
		else if(order_postion == 5) { sql = sql +  "   data_value5 -  "; }
		else if(order_postion == 6) { sql = sql +  "   data_value6 -  "; }
		else if(order_postion == 7) { sql = sql +  "   data_value7 -  "; }
		else if(order_postion == 8) { sql = sql +  "  and data_value8 -  "; }
		else if(order_postion == 9) { sql = sql +  "   data_value9 -  "; }
		else if(order_postion == 10) { sql = sql +  "   data_value10 -  "; }
		else if(order_postion == 11) { sql = sql +  "   data_value11 -  "; }
		else if(order_postion == 12) { sql = sql +  "   data_value12 -  "; }
		else if(order_postion == 13) { sql = sql +  "   data_value13 -  "; }
		else if(order_postion == 14) { sql = sql +  "   data_value14 -  "; }
		else if(order_postion == 15) { sql = sql +  "   data_value15 -  "; }
		else if(order_postion == 16) { sql = sql +  "   data_value16 -  "; }
		else if(order_postion == 17) { sql = sql +  "   data_value17 -  "; }
		else if(order_postion == 18) { sql = sql +  "   data_value18 -  "; }
		else if(order_postion == 19) { sql = sql +  "   data_value19 -  "; }
		else if(order_postion == 20) { sql = sql +  "   data_value20 -  "; }
		
		
		
		
		if(balance_postion == 1) { sql = sql +  "   data_value1     ";  }
		else if(balance_postion == 2) { sql = sql +  "   data_value2   "; }
		else if(balance_postion == 3) { sql = sql +  "   data_value3   "; }
		else if(balance_postion == 4) { sql = sql +  "   data_value4   "; }
		else if(balance_postion == 5) { sql = sql +  "   data_value5   "; }
		else if(balance_postion == 6) { sql = sql +  "   data_value6  "; }
		else if(balance_postion == 7) { sql = sql +  "   data_value7   "; }
		else if(balance_postion == 8) { sql = sql +  "  and data_value8  "; }
		else if(balance_postion == 9) { sql = sql +  "   data_value9   "; }
		else if(balance_postion == 10) { sql = sql +  "   data_value10   "; }
		else if(balance_postion == 11) { sql = sql +  "   data_value11   "; }
		else if(balance_postion == 12) { sql = sql +  "   data_value12   "; }
		else if(balance_postion == 13) { sql = sql +  "   data_value13   "; }
		else if(balance_postion == 14) { sql = sql +  "   data_value14   "; }
		else if(balance_postion == 15) { sql = sql +  "   data_value15   "; }
		else if(balance_postion == 16) { sql = sql +  "   data_value16   "; }
		else if(balance_postion == 17) { sql = sql +  "   data_value17   "; }
		else if(balance_postion == 18) { sql = sql +  "   data_value18  "; }
		else if(balance_postion == 19) { sql = sql +  "   data_value19 "; }
		else if(balance_postion == 20) { sql = sql +  "   data_value20   "; }
		

		sql = sql + " WHERE data_id = ?  ";
		return jdbcTemplate.update(sql,new Object[]{cal_condition});
	}

	
	
	
	
	
	@Override
	public int update(edata edatas) {
		Object[] edataInfo = { 
			
				edatas.getData_title1(),
				edatas.getData_value1(),
				edatas.getData_title2(),
				edatas.getData_value2(),
				edatas.getData_title3(),
				edatas.getData_value3(),
				edatas.getData_title4(),
				edatas.getData_value4(),
				edatas.getData_title5(),
				edatas.getData_value5(),
				edatas.getData_title6(),
				edatas.getData_value6(),
				edatas.getData_title7(),
				edatas.getData_value7(),
				edatas.getData_title8(),
				edatas.getData_value8(),
				edatas.getData_title9(),
				edatas.getData_value9(),
				edatas.getData_title10(),
				edatas.getData_value10(),
				edatas.getData_title11(),
				edatas.getData_value11(),
				edatas.getData_title12(),
				edatas.getData_value12(),
				edatas.getData_title13(),
				edatas.getData_value13(),
				edatas.getData_title14(),
				edatas.getData_value14(),
				edatas.getData_title15(),
				edatas.getData_value15(),
				edatas.getData_title16(),
				edatas.getData_value16(),
				edatas.getData_title17(),
				edatas.getData_value17(),
				edatas.getData_title18(),
				edatas.getData_value18(),
				edatas.getData_title19(),
				edatas.getData_value19(),
				edatas.getData_title20(),
				edatas.getData_value20(),
				edatas.getData_due(),
				edatas.getLog(),
				edatas.getData_status(),
				edatas.getData_id(),
				};
		String sql = "UPDATE edata  set data_title1=?,data_value1=?,data_title2=?,data_value2=?,data_title3=?,data_value3=?,data_title4=?,data_value4=?,data_title5=?,data_value5=?"
				+ ",data_title6=?,data_value6=?,data_title7=?,data_value7=?,data_title8=?,data_value8=?,data_title9=?,data_value9=?,data_title10=?,data_value10=?"
				+ ",data_title11=?,data_value11=?,data_title12=?,data_value12=?,data_title13=?,data_value13=?,data_title14=?,data_value14=?,data_title15=?,data_value15=?"
				+ ",data_title16=?,data_value16=?,data_title17=?,data_value17=?,data_title18=?,data_value18=?,data_title19=?,data_value19=?,data_title20=?,data_value20=?,data_due=?,log=?"
				+ ",data_status=? "
				+ " WHERE data_id =?";
		return jdbcTemplate.update(sql,edataInfo);
	}
	
	

	@Override
	public List<edata>  getalllot(int data_eformid,int data_eformrv,String value,long product_no) {
	
		
		 String sql = "SELECT * FROM  edata  ";
		sql = sql + " WHERE data_eformid = ? and  data_eformrv = ? and  stock = 0 ";
	 
		if(product_no == 1) { sql = sql +  "  and data_value1 = ?   ";  }
		else if(product_no == 2) { sql = sql +  " and  data_value2 = ? "; }
		else if(product_no == 3) { sql = sql +  " and  data_value3 = ? "; }
		else if(product_no == 4) { sql = sql +  " and  data_value4 = ? "; }
		else if(product_no == 5) { sql = sql +  "  and data_value5 = ? "; }
		else if(product_no == 6) { sql = sql +  " and  data_value6 = ? "; }
		else if(product_no == 7) { sql = sql +  " and  data_value7 = ? "; }
		else if(product_no == 8) { sql = sql +  "  and data_value8 = ? "; }
		else if(product_no == 9) { sql = sql +  "  and data_value9 = ? "; }
		else if(product_no == 10) { sql = sql +  "  and data_value10 = ? "; }
		else if(product_no == 11) { sql = sql +  "  and data_value11 = ? "; }
		else if(product_no == 12) { sql = sql +  "  and data_value12 = ? "; }
		else if(product_no == 13) { sql = sql +  " and  data_value13 = ? "; }
		else if(product_no == 14) { sql = sql +  " and  data_value14 = ? "; }
		else if(product_no == 15) { sql = sql +  "  and data_value15 = ? "; }
		else if(product_no == 16) { sql = sql +  "  and data_value16 = ? "; }
		else if(product_no == 17) { sql = sql +  "  and data_value17 = ? "; }
		else if(product_no == 18) { sql = sql +  "  and data_value18 = ? "; }
		else if(product_no == 19) { sql = sql +  "  and data_value19 = ? "; }
		else if(product_no == 20) { sql = sql +  " and  data_value20 = ? "; }

		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv,value},new edatamappers());
		return edatas;

	}
	
	
	
	
	
	@Override
	public List<edata> getallGroupBy(int data_eformid,int data_eformrv,long product_no) {
	
		
		 String sql = "SELECT * FROM  edata  ";
		sql = sql + " WHERE data_eformid = ? and  data_eformrv = ? and  stock = 0 ";
	 
		if(product_no == 1) { sql = sql +  "   GROUP BY data_value1 ";  }
		else if(product_no == 2) { sql = sql +  "  GROUP BY data_value2  "; }
		else if(product_no == 3) { sql = sql +  "  GROUP BY data_value3  "; }
		else if(product_no == 4) { sql = sql +  "  GROUP BY data_value4  "; }
		else if(product_no == 5) { sql = sql +  "   GROUP BY data_value5  "; }
		else if(product_no == 6) { sql = sql +  "  GROUP BY data_value6  "; }
		else if(product_no == 7) { sql = sql +  "  GROUP BY data_value7  "; }
		else if(product_no == 8) { sql = sql +  "   GROUP BY data_value8  "; }
		else if(product_no == 9) { sql = sql +  "   GROUP BY data_value9  "; }
		else if(product_no == 10) { sql = sql +  "  GROUP BY data_value10  "; }
		else if(product_no == 11) { sql = sql +  "  GROUP BY data_value11  "; }
		else if(product_no == 12) { sql = sql +  "   GROUP BY data_value12  "; }
		else if(product_no == 13) { sql = sql +  "  GROUP BY data_value13  "; }
		else if(product_no == 14) { sql = sql +  " GROUP BY data_value14  "; }
		else if(product_no == 15) { sql = sql +  "  GROUP BY data_value15  "; }
		else if(product_no == 16) { sql = sql +  "   GROUP BY data_value16  "; }
		else if(product_no == 17) { sql = sql +  "   GROUP BY data_value17  "; }
		else if(product_no == 18) { sql = sql +  "  GROUP BY data_value18  "; }
		else if(product_no == 19) { sql = sql +  "   GROUP BY data_value19  "; }
		else if(product_no == 20) { sql = sql +  " GROUP BY data_value20  "; }

		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{data_eformid,data_eformrv},new edatamappers());
		return edatas;

	}
	
	
	
	
	
	@Override
	public List<edata> getallproduct(int planid,String product,String lot,int product_no,int lot_no) {
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
		String sql = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
				+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
				+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
				+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
				+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer    "
				+ " where   edata.data_planid =?  ";
			
		
			if(product_no == 1) { sql = sql +  "  and edata.data_value1 = '"+product+"' "; }
			else if(product_no == 2) { sql = sql +  " and  edata.data_value2 = '"+product+"' "; }
			else if(product_no == 3) { sql = sql +  " and  edata.data_value3 = '"+product+"' "; }
			else if(product_no == 4) { sql = sql +  " and  edata.data_value4 = '"+product+"' "; }
			else if(product_no == 5) { sql = sql +  "  and edata.data_value5 = '"+product+"' "; }
			else if(product_no == 6) { sql = sql +  " and  edata.data_value6 = '"+product+"' "; }
			else if(product_no == 7) { sql = sql +  " and  edata.data_value7 = '"+product+"' "; }
			else if(product_no == 8) { sql = sql +  "  and edata.data_value8 = '"+product+"' "; }
			else if(product_no == 9) { sql = sql +  "  and edata.data_value9 = '"+product+"' "; }
			else if(product_no == 10) { sql = sql +  "  and edata.data_value10 = '"+product+"' "; }
			else if(product_no == 11) { sql = sql +  "  and edata.data_value11 = '"+product+"' "; }
			else if(product_no == 12) { sql = sql +  "  and edata.data_value12 = '"+product+"' "; }
			else if(product_no == 13) { sql = sql +  " and  edata.data_value13 = '"+product+"' "; }
			else if(product_no == 14) { sql = sql +  " and  edata.data_value14 = '"+product+"' "; }
			else if(product_no == 15) { sql = sql +  "  and edata.data_value15 = '"+product+"' "; }
			else if(product_no == 16) { sql = sql +  "  and edata.data_value16 = '"+product+"' "; }
			else if(product_no == 17) { sql = sql +  "  and edata.data_value17 = '"+product+"' "; }
			else if(product_no == 18) { sql = sql +  "  and edata.data_value18 = '"+product+"' "; }
			else if(product_no == 19) { sql = sql +  "  and edata.data_value19 = '"+product+"' "; }
			else if(product_no == 20) { sql = sql +  " and  edata.data_value20 = '"+product+"' "; }
			
			
			if(lot_no == 1) { sql = sql +  "  and edata.data_value1 = '"+lot+"' "; }
			else if(lot_no == 2) { sql = sql +  " and  edata.data_value2 = '"+lot+"' "; }
			else if(lot_no == 3) { sql = sql +  " and  edata.data_value3 = '"+lot+"' "; }
			else if(lot_no == 4) { sql = sql +  " and  edata.data_value4 = '"+lot+"' "; }
			else if(lot_no == 5) { sql = sql +  "  and edata.data_value5 = '"+lot+"' "; }
			else if(lot_no == 6) { sql = sql +  " and  edata.data_value6 = '"+lot+"' "; }
			else if(lot_no == 7) { sql = sql +  " and  edata.data_value7 = '"+lot+"' "; }
			else if(lot_no == 8) { sql = sql +  "  and edata.data_value8 = '"+lot+"' "; }
			else if(lot_no == 9) { sql = sql +  "  and edata.data_value9 = '"+lot+"' "; }
			else if(lot_no == 10) { sql = sql +  "  and edata.data_value10 = '"+lot+"' "; }
			else if(lot_no == 11) { sql = sql +  "  and edata.data_value11 = '"+lot+"' "; }
			else if(lot_no == 12) { sql = sql +  "  and edata.data_value12 = '"+lot+"' "; }
			else if(lot_no == 13) { sql = sql +  " and  edata.data_value13 = '"+lot+"' "; }
			else if(lot_no == 14) { sql = sql +  " and  edata.data_value14 = '"+lot+"' "; }
			else if(lot_no == 15) { sql = sql +  "  and edata.data_value15 = '"+lot+"' "; }
			else if(lot_no == 16) { sql = sql +  "  and edata.data_value16 = '"+lot+"' "; }
			else if(lot_no == 17) { sql = sql +  "  and edata.data_value17 = '"+lot+"' "; }
			else if(lot_no == 18) { sql = sql +  "  and edata.data_value18 = '"+lot+"' "; }
			else if(lot_no == 19) { sql = sql +  "  and edata.data_value19 = '"+lot+"' "; }
			else if(lot_no == 20) { sql = sql +  " and  edata.data_value20 = '"+lot+"' "; }
		
		
		sql = sql +  " GROUP BY  edata.data_id  order by edata.data_due desc   limit 5000";
	
		System.out.println("sql "+planid+"-----"+sql);
		
		
		
		List<edata> edatas  =  jdbcTemplate.query(sql,new Object[]{planid},new edatamapper3());
		return edatas;
	}
	
	
	

	@Override
	public void closestock(int id) {

				String sql = "UPDATE edata SET stock =1  WHERE data_id= ?";
				jdbcTemplate.update(sql,new Object[]{id});
	}


	@Override
	public void openstock(int id) {
				String sql = "UPDATE edata SET stock =0  WHERE data_id= ?";
				jdbcTemplate.update(sql,new Object[]{id});
	}

	@Override
	public int getNumreport(int id) {
				String sql = "SELECT COUNT(*)  edata WHERE report_id= ?";
				return jdbcTemplate.update(sql,new Object[]{id},Integer.class);
	}

	
	@Override
	public int saveCheck(int user_id, String date) { 
		
		System.out.println(" date "+date);
		
	    String sql = "SELECT COUNT(*) FROM edata WHERE data_idadd = ? AND ABS(TIMESTAMPDIFF(SECOND, data_dateadd, ?)) <= 2";
	    return jdbcTemplate.queryForObject(sql, new Object[]{user_id, date}, Integer.class);
	}
	
	
	@Override
	public List<edata> getTrace() {
	String sql = "SELECT conditions.condition_title as data_namerefer,edata.* FROM edata "
			+ " LEFT JOIN conditions ON edata.data_eformid = conditions.condition_id "
			+ " where (conditions.calculation_port1 > 0 or conditions.calculation_port2 > 0 or conditions.calculation_port3 > 0"
			+ " or conditions.calculation_port4 > 0  or conditions.calculation_port5 > 0)"
			+ " order by edata.data_due desc limit 5000";
	List<edata> edatas  =  jdbcTemplate.query(sql,new edatamapper4());
	return edatas;
	}
	
	
	
	@Override
	public List<edata> getallTrace(
			String product,String lot
			,String date_start,String date_end,
			int port1,int portrv1,int port2,int portrv2
			,int product_no1, int lot_no1,int product_no2,int lot_no2
			) {
		
		
		System.out.println("port1 " +port1);
		System.out.println("portrv1 " +portrv1);
		System.out.println("product_no1 " +product_no1);
		System.out.println("lot_no1 " +lot_no1);
		
		
		String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
				+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
				+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
				+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
				+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
				+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
				+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
				+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
				+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
				+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
		
		
	
			
		    String sql = "";
		    int product_no =0;
		    int lot_no =0;
		    
      if(product != null) {
		    product_no = product_no1;
			if(product_no == 1) { sql = sql +  "     and edata.data_value1 = '"+product+"' ";  }
			else if(product_no == 2) { sql = sql +  " and  edata.data_value2 = '"+product+"' "; }
			else if(product_no == 3) { sql = sql +  " and  edata.data_value3 = '"+product+"' "; }
			else if(product_no == 4) { sql = sql +  " and  edata.data_value4 = '"+product+"' "; }
			else if(product_no == 5) { sql = sql +  "  and edata.data_value5 = '"+product+"' "; }
			else if(product_no == 6) { sql = sql +  " and  edata.data_value6 = '"+product+"' "; }
			else if(product_no == 7) { sql = sql +  " and  edata.data_value7 = '"+product+"' "; }
			else if(product_no == 8) { sql = sql +  "  and edata.data_value8 = '"+product+"' "; }
			else if(product_no == 9) { sql = sql +  "  and edata.data_value9 = '"+product+"' "; }
			else if(product_no == 10) { sql = sql +  "  and edata.data_value10 = '"+product+"' "; }
			else if(product_no == 11) { sql = sql +  "  and edata.data_value11 = '"+product+"' "; }
			else if(product_no == 12) { sql = sql +  "  and edata.data_value12 = '"+product+"' "; }
			else if(product_no == 13) { sql = sql +  " and  edata.data_value13 = '"+product+"' "; }
			else if(product_no == 14) { sql = sql +  " and  edata.data_value14 = '"+product+"' "; }
			else if(product_no == 15) { sql = sql +  "  and edata.data_value15 = '"+product+"' "; }
			else if(product_no == 16) { sql = sql +  "  and edata.data_value16 = '"+product+"' "; }
			else if(product_no == 17) { sql = sql +  "  and edata.data_value17 = '"+product+"' "; }
			else if(product_no == 18) { sql = sql +  "  and edata.data_value18 = '"+product+"' "; }
			else if(product_no == 19) { sql = sql +  "  and edata.data_value19 = '"+product+"' "; }
			else if(product_no == 20) { sql = sql +  " and  edata.data_value20 = '"+product+"' "; }
	
			
      }
      
      if(lot != null) {
    	    lot_no= lot_no1;
			if(lot_no == 1) { sql = sql +  "  and edata.data_value1 = '"+lot+"' "; }
			else if(lot_no == 2) { sql = sql +  " and  edata.data_value2 = '"+lot+"' "; }
			else if(lot_no == 3) { sql = sql +  " and  edata.data_value3 = '"+lot+"' "; }
			else if(lot_no == 4) { sql = sql +  " and  edata.data_value4 = '"+lot+"' "; }
			else if(lot_no == 5) { sql = sql +  "  and edata.data_value5 = '"+lot+"' "; }
			else if(lot_no == 6) { sql = sql +  " and  edata.data_value6 = '"+lot+"' "; }
			else if(lot_no == 7) { sql = sql +  " and  edata.data_value7 = '"+lot+"' "; }
			else if(lot_no == 8) { sql = sql +  "  and edata.data_value8 = '"+lot+"' "; }
			else if(lot_no == 9) { sql = sql +  "  and edata.data_value9 = '"+lot+"' "; }
			else if(lot_no == 10) { sql = sql +  "  and edata.data_value10 = '"+lot+"' "; }
			else if(lot_no == 11) { sql = sql +  "  and edata.data_value11 = '"+lot+"' "; }
			else if(lot_no == 12) { sql = sql +  "  and edata.data_value12 = '"+lot+"' "; }
			else if(lot_no == 13) { sql = sql +  " and  edata.data_value13 = '"+lot+"' "; }
			else if(lot_no == 14) { sql = sql +  " and  edata.data_value14 = '"+lot+"' "; }
			else if(lot_no == 15) { sql = sql +  "  and edata.data_value15 = '"+lot+"' "; }
			else if(lot_no == 16) { sql = sql +  "  and edata.data_value16 = '"+lot+"' "; }
			else if(lot_no == 17) { sql = sql +  "  and edata.data_value17 = '"+lot+"' "; }
			else if(lot_no == 18) { sql = sql +  "  and edata.data_value18 = '"+lot+"' "; }
			else if(lot_no == 19) { sql = sql +  "  and edata.data_value19 = '"+lot+"' "; }
			else if(lot_no == 20) { sql = sql +  " and  edata.data_value20 = '"+lot+"' "; }
      }
      
			String sub1 = sql;
		
			
			sql = "";
			
	 if(product != null) {		
		    product_no = product_no2;
			if(product_no == 1) { sql = sql +  "     and edata.data_value1 = '"+product+"' "; }
			else if(product_no == 2) { sql = sql +  " and  edata.data_value2 = '"+product+"' "; }
			else if(product_no == 3) { sql = sql +  " and  edata.data_value3 = '"+product+"' "; }
			else if(product_no == 4) { sql = sql +  " and  edata.data_value4 = '"+product+"' "; }
			else if(product_no == 5) { sql = sql +  "  and edata.data_value5 = '"+product+"' "; }
			else if(product_no == 6) { sql = sql +  " and  edata.data_value6 = '"+product+"' "; }
			else if(product_no == 7) { sql = sql +  " and  edata.data_value7 = '"+product+"' "; }
			else if(product_no == 8) { sql = sql +  "  and edata.data_value8 = '"+product+"' "; }
			else if(product_no == 9) { sql = sql +  "  and edata.data_value9 = '"+product+"' "; }
			else if(product_no == 10) { sql = sql +  "  and edata.data_value10 = '"+product+"' "; }
			else if(product_no == 11) { sql = sql +  "  and edata.data_value11 = '"+product+"' "; }
			else if(product_no == 12) { sql = sql +  "  and edata.data_value12 = '"+product+"' "; }
			else if(product_no == 13) { sql = sql +  " and  edata.data_value13 = '"+product+"' "; }
			else if(product_no == 14) { sql = sql +  " and  edata.data_value14 = '"+product+"' "; }
			else if(product_no == 15) { sql = sql +  "  and edata.data_value15 = '"+product+"' "; }
			else if(product_no == 16) { sql = sql +  "  and edata.data_value16 = '"+product+"' "; }
			else if(product_no == 17) { sql = sql +  "  and edata.data_value17 = '"+product+"' "; }
			else if(product_no == 18) { sql = sql +  "  and edata.data_value18 = '"+product+"' "; }
			else if(product_no == 19) { sql = sql +  "  and edata.data_value19 = '"+product+"' "; }
			else if(product_no == 20) { sql = sql +  " and  edata.data_value20 = '"+product+"' "; }
	 }	
	 
	   if(lot != null) {
			lot_no = lot_no2;
			if(lot_no == 1) { sql = sql +  "  and edata.data_value1 = '"+lot+"' "; }
			else if(lot_no == 2) { sql = sql +  " and  edata.data_value2 = '"+lot+"' "; }
			else if(lot_no == 3) { sql = sql +  " and  edata.data_value3 = '"+lot+"' "; }
			else if(lot_no == 4) { sql = sql +  " and  edata.data_value4 = '"+lot+"' "; }
			else if(lot_no == 5) { sql = sql +  "  and edata.data_value5 = '"+lot+"' "; }
			else if(lot_no == 6) { sql = sql +  " and  edata.data_value6 = '"+lot+"' "; }
			else if(lot_no == 7) { sql = sql +  " and  edata.data_value7 = '"+lot+"' "; }
			else if(lot_no == 8) { sql = sql +  "  and edata.data_value8 = '"+lot+"' "; }
			else if(lot_no == 9) { sql = sql +  "  and edata.data_value9 = '"+lot+"' "; }
			else if(lot_no == 10) { sql = sql +  "  and edata.data_value10 = '"+lot+"' "; }
			else if(lot_no == 11) { sql = sql +  "  and edata.data_value11 = '"+lot+"' "; }
			else if(lot_no == 12) { sql = sql +  "  and edata.data_value12 = '"+lot+"' "; }
			else if(lot_no == 13) { sql = sql +  " and  edata.data_value13 = '"+lot+"' "; }
			else if(lot_no == 14) { sql = sql +  " and  edata.data_value14 = '"+lot+"' "; }
			else if(lot_no == 15) { sql = sql +  "  and edata.data_value15 = '"+lot+"' "; }
			else if(lot_no == 16) { sql = sql +  "  and edata.data_value16 = '"+lot+"' "; }
			else if(lot_no == 17) { sql = sql +  "  and edata.data_value17 = '"+lot+"' "; }
			else if(lot_no == 18) { sql = sql +  "  and edata.data_value18 = '"+lot+"' "; }
			else if(lot_no == 19) { sql = sql +  "  and edata.data_value19 = '"+lot+"' "; }
			else if(lot_no == 20) { sql = sql +  " and  edata.data_value20 = '"+lot+"' "; }
			
			
	   }			
	   
	   
	   String datequery =""; 
	   if(!date_start.isBlank() ) {

		   datequery = datequery +  "  AND DATE(edata.data_dateadd) >= '"+date_start+"' ";
			if(date_end != null ) {  datequery = datequery +  "  AND DATE(edata.data_dateadd) <= '"+date_end+"' "; }
	   }
	   
	   
	   String sub2 = sql;
	   
			String sqls = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
					+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
					+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
					+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
					+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer    "
					+ " where   ( edata.data_eformid =?  and edata.data_eformrv =?  "+sub1+"   OR  edata.data_eformid =? and edata.data_eformrv =?  "+sub2+" ) "
							+datequery;
				
			sqls = sqls +  " GROUP BY  edata.data_id  order by edata.data_id asc   limit 5000";
			
			System.out.println("sqls getallTrace  " +sqls);
			
		List<edata> edatas  =  jdbcTemplate.query(sqls,new Object[]{port1,portrv1,port2,portrv2},new edatamapper3());
		return edatas;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public edata getTrace(String product,String lot,int product_no, int lot_no,int port1,int portrv1) {
		
		
		
		  try {
				String sub ="edatas.data_title1 as data_titles1,edatas.data_title2 as data_titles2,edatas.data_title3 as data_titles3,edatas.data_title4 as data_titles4,edatas.data_title5 as data_titles5,edatas.data_title6 as data_titles6"
						+ ",edatas.data_title7 as data_titles7,edatas.data_title8 as data_titles8,edatas.data_title9 as data_titles9,edatas.data_title10 as data_titles10,edatas.data_title11 as data_titles11,edatas.data_title12 as data_titles12"
						+ ",edatas.data_title13 as data_titles13,edatas.data_title14 as data_titles14,edatas.data_title15 as data_titles15,edatas.data_title16 as data_titles16,edatas.data_title17 as data_titles17,edatas.data_title18 as data_titles18"
						+ ",edatas.data_title19 as data_titles19,edatas.data_title20 as data_titles20"
						+ ",edatas.data_value1 as data_values1,edatas.data_value2 as data_values2,edatas.data_value3 as data_values3,edatas.data_value4 as data_values4,edatas.data_value5 as data_values5"
						+ ",edatas.data_value6 as data_values6,edatas.data_value7 as data_values7,edatas.data_value8 as data_values8,edatas.data_value9 as data_values9,edatas.data_value10 as data_values10"
						+ ",edatas.data_value11 as data_values11,edatas.data_value12 as data_values12,edatas.data_value13 as data_values13,edatas.data_value14 as data_values14,edatas.data_value15 as data_values15"
						+ ",edatas.data_value16 as data_values16,edatas.data_value17 as data_values17,edatas.data_value18 as data_values18,edatas.data_value19 as data_values19"
						+ ",edatas.data_value20 as data_values20,edatas.data_nameadd as data_nameadds,edatas.data_namerefer as data_namerefers,edatas.data_filetype as data_filetypes"
						+ ",edatas.data_filepath as data_filepaths,edatas.data_due as data_dues";
				
				
			
					
				 String sql = "";
			
		      if(product != null && product_no > 0) {

					if(product_no == 1) { sql = sql +  "     and edata.data_value1 = '"+product+"' ";  }
					else if(product_no == 2) { sql = sql +  " and  edata.data_value2 = '"+product+"' "; }
					else if(product_no == 3) { sql = sql +  " and  edata.data_value3 = '"+product+"' "; }
					else if(product_no == 4) { sql = sql +  " and  edata.data_value4 = '"+product+"' "; }
					else if(product_no == 5) { sql = sql +  "  and edata.data_value5 = '"+product+"' "; }
					else if(product_no == 6) { sql = sql +  " and  edata.data_value6 = '"+product+"' "; }
					else if(product_no == 7) { sql = sql +  " and  edata.data_value7 = '"+product+"' "; }
					else if(product_no == 8) { sql = sql +  "  and edata.data_value8 = '"+product+"' "; }
					else if(product_no == 9) { sql = sql +  "  and edata.data_value9 = '"+product+"' "; }
					else if(product_no == 10) { sql = sql +  "  and edata.data_value10 = '"+product+"' "; }
					else if(product_no == 11) { sql = sql +  "  and edata.data_value11 = '"+product+"' "; }
					else if(product_no == 12) { sql = sql +  "  and edata.data_value12 = '"+product+"' "; }
					else if(product_no == 13) { sql = sql +  " and  edata.data_value13 = '"+product+"' "; }
					else if(product_no == 14) { sql = sql +  " and  edata.data_value14 = '"+product+"' "; }
					else if(product_no == 15) { sql = sql +  "  and edata.data_value15 = '"+product+"' "; }
					else if(product_no == 16) { sql = sql +  "  and edata.data_value16 = '"+product+"' "; }
					else if(product_no == 17) { sql = sql +  "  and edata.data_value17 = '"+product+"' "; }
					else if(product_no == 18) { sql = sql +  "  and edata.data_value18 = '"+product+"' "; }
					else if(product_no == 19) { sql = sql +  "  and edata.data_value19 = '"+product+"' "; }
					else if(product_no == 20) { sql = sql +  " and  edata.data_value20 = '"+product+"' "; }
			
					
		      }
		      
		      if(lot != null && lot_no > 0) {

					if(lot_no == 1) { sql = sql +  "  and edata.data_value1 = '"+lot+"' "; }
					else if(lot_no == 2) { sql = sql +  " and  edata.data_value2 = '"+lot+"' "; }
					else if(lot_no == 3) { sql = sql +  " and  edata.data_value3 = '"+lot+"' "; }
					else if(lot_no == 4) { sql = sql +  " and  edata.data_value4 = '"+lot+"' "; }
					else if(lot_no == 5) { sql = sql +  "  and edata.data_value5 = '"+lot+"' "; }
					else if(lot_no == 6) { sql = sql +  " and  edata.data_value6 = '"+lot+"' "; }
					else if(lot_no == 7) { sql = sql +  " and  edata.data_value7 = '"+lot+"' "; }
					else if(lot_no == 8) { sql = sql +  "  and edata.data_value8 = '"+lot+"' "; }
					else if(lot_no == 9) { sql = sql +  "  and edata.data_value9 = '"+lot+"' "; }
					else if(lot_no == 10) { sql = sql +  "  and edata.data_value10 = '"+lot+"' "; }
					else if(lot_no == 11) { sql = sql +  "  and edata.data_value11 = '"+lot+"' "; }
					else if(lot_no == 12) { sql = sql +  "  and edata.data_value12 = '"+lot+"' "; }
					else if(lot_no == 13) { sql = sql +  " and  edata.data_value13 = '"+lot+"' "; }
					else if(lot_no == 14) { sql = sql +  " and  edata.data_value14 = '"+lot+"' "; }
					else if(lot_no == 15) { sql = sql +  "  and edata.data_value15 = '"+lot+"' "; }
					else if(lot_no == 16) { sql = sql +  "  and edata.data_value16 = '"+lot+"' "; }
					else if(lot_no == 17) { sql = sql +  "  and edata.data_value17 = '"+lot+"' "; }
					else if(lot_no == 18) { sql = sql +  "  and edata.data_value18 = '"+lot+"' "; }
					else if(lot_no == 19) { sql = sql +  "  and edata.data_value19 = '"+lot+"' "; }
					else if(lot_no == 20) { sql = sql +  " and  edata.data_value20 = '"+lot+"' "; }
		      }
		      
					String sub1 = sql;
				
					

			  
			   
					String sqls = "SELECT "+sub+" ,edata.*,log.*,upload_pn.*,report_pn.* FROM edata "
							+ " LEFT JOIN log ON edata.data_filepath = log.log_id"
							+ " LEFT JOIN edata as edatas ON edata.data_join = edatas.data_id"
							+ " LEFT JOIN upload_pn ON upload_pn.upload_pn_id  = edata.data_planid  "
							+ " LEFT JOIN report_pn ON upload_pn.upload_pn_id = report_pn.report_pn_idrefer    "
							+ " where   edata.data_eformid =?  and edata.data_eformrv =?  "+sub1+"   ";
					sqls = sqls +  "  Limit 1";
			
				edata edatas  =  jdbcTemplate.queryForObject(sqls,new Object[]{port1,portrv1},new edatamapper3());
				return edatas;
			
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getTrace with the same Id .......");
		   
		 }
		
	
	}
	
	@Override
	public boolean checklog(int id,String date) {
		String sql = "SELECT COUNT(*) FROM edata_log  where id_refer =? and DATE(data_dateadd) = ? ";
		int count =  jdbcTemplate.queryForObject(sql,new Object[]{id,date},Integer.class);
		   return count > 0;
	}
	
	@Override
	public int countclearance(int id,String _respon) {
				String sql = "SELECT COUNT(*) FROM   edata "
						+ " LEFT JOIN log ON edata.data_filepath = log.log_id "
						+ "WHERE edata.data_join= ? and edata.data_nameadd = ? and log_flow = -2  ";
				return jdbcTemplate.queryForObject(sql,new Object[]{id,_respon},Integer.class);
	}
	
	
	@Override
	public edata getclearance(int id,String _respon) {
		
				  try {
						String sql = "SELECT * FROM edata "
								+ " LEFT JOIN log ON edata.data_filepath = log.log_id "
								+ "WHERE edata.data_join= ? and edata.data_nameadd = ? and log_flow = -2 limit 1  ";
						edata edataed  =  jdbcTemplate.queryForObject(sql,new Object[]{id,_respon},new edatamapper3());
						return edataed;
			 
				  }catch(EmptyResultDataAccessException e) {
						 
						 return null;
						 //throw new RuntimeException("file ID does not exist");
					    
				}catch(IncorrectResultSizeDataAccessException e) {
					       
						 throw new RuntimeException("More than one getclearance with the same Id .......");
					   
				}
					
	 }	
	
	
	
	
	@Override
	public List<edata> getReceived(
			String product,String lot
			,int receiveproduct_no,int receivelot_no,int receive_no
			,int port,int portrv
			) {
		
		  System.out.println("product "+product);
		  System.out.println("lot "+lot);
		  System.out.println("port "+port);
		  System.out.println("portrv "+portrv); 
		  
		  
		String sub ="";
		if(receive_no > 0) {

						if(receive_no == 1) { sub = sub +  "   SUM(edata.data_value1) as data_value1  "; }
						else if(receive_no == 2) { sub = sub +  "   SUM(edata.data_value2) as data_value1  ";  }
						else if(receive_no == 3) { sub = sub +  "   SUM(edata.data_value3) as data_value1  ";  }
						else if(receive_no == 4) { sub = sub + "   SUM(edata.data_value4) as data_value1  ";  }
						else if(receive_no == 5) { sub = sub +  "   SUM(edata.data_value5) as data_value1  ";  }
						else if(receive_no == 6) { sub = sub + "   SUM(edata.data_value6) as data_value1  ";  }
						else if(receive_no == 7) { sub = sub +  "   SUM(edata.data_value7) as data_value1  ";  }
						else if(receive_no == 8) { sub = sub +  "   SUM(edata.data_value8) as data_value1  ";  }
						else if(receive_no == 9) { sub = sub +  "   SUM(edata.data_value9) as data_value1  ";  }
						else if(receive_no == 10) { sub = sub +  "   SUM(edata.data_value10) as data_value1  ";  }
						else if(receive_no == 11) { sub = sub +  "   SUM(edata.data_value11) as data_value1  ";  }
						else if(receive_no == 12) { sub = sub +  "   SUM(edata.data_value12) as data_value1  ";  }
						else if(receive_no == 13) { sub = sub +  "   SUM(edata.data_value13) as data_value1  ";  }
						else if(receive_no == 14) { sub = sub +  "   SUM(edata.data_value14) as data_value1  ";  }
						else if(receive_no == 15) { sub = sub +  "   SUM(edata.data_value15) as data_value1  ";  }
						else if(receive_no == 16) { sub = sub +  "   SUM(edata.data_value16) as data_value1  ";  }
						else if(receive_no == 17) { sub = sub +  "   SUM(edata.data_value17) as data_value1  ";  }
						else if(receive_no == 18) { sub = sub +  "   SUM(edata.data_value18) as data_value1  ";  }
						else if(receive_no == 19) { sub = sub +  "   SUM(edata.data_value19) as data_value1  ";  }
						else if(receive_no == 20) { sub = sub +  "   SUM(edata.data_value20) as data_value1  ";  }
			     
				 String sql = "";
			
		      if(product != null && receiveproduct_no > 0) {

					if(receiveproduct_no == 1) { sql = sql +  "     and edata.data_value1 = '"+product+"' ";  }
					else if(receiveproduct_no == 2) { sql = sql +  " and  edata.data_value2 = '"+product+"' "; }
					else if(receiveproduct_no == 3) { sql = sql +  " and  edata.data_value3 = '"+product+"' "; }
					else if(receiveproduct_no == 4) { sql = sql +  " and  edata.data_value4 = '"+product+"' "; }
					else if(receiveproduct_no == 5) { sql = sql +  "  and edata.data_value5 = '"+product+"' "; }
					else if(receiveproduct_no == 6) { sql = sql +  " and  edata.data_value6 = '"+product+"' "; }
					else if(receiveproduct_no == 7) { sql = sql +  " and  edata.data_value7 = '"+product+"' "; }
					else if(receiveproduct_no == 8) { sql = sql +  "  and edata.data_value8 = '"+product+"' "; }
					else if(receiveproduct_no == 9) { sql = sql +  "  and edata.data_value9 = '"+product+"' "; }
					else if(receiveproduct_no == 10) { sql = sql +  "  and edata.data_value10 = '"+product+"' "; }
					else if(receiveproduct_no == 11) { sql = sql +  "  and edata.data_value11 = '"+product+"' "; }
					else if(receiveproduct_no == 12) { sql = sql +  "  and edata.data_value12 = '"+product+"' "; }
					else if(receiveproduct_no == 13) { sql = sql +  " and  edata.data_value13 = '"+product+"' "; }
					else if(receiveproduct_no == 14) { sql = sql +  " and  edata.data_value14 = '"+product+"' "; }
					else if(receiveproduct_no == 15) { sql = sql +  "  and edata.data_value15 = '"+product+"' "; }
					else if(receiveproduct_no == 16) { sql = sql +  "  and edata.data_value16 = '"+product+"' "; }
					else if(receiveproduct_no == 17) { sql = sql +  "  and edata.data_value17 = '"+product+"' "; }
					else if(receiveproduct_no == 18) { sql = sql +  "  and edata.data_value18 = '"+product+"' "; }
					else if(receiveproduct_no == 19) { sql = sql +  "  and edata.data_value19 = '"+product+"' "; }
					else if(receiveproduct_no == 20) { sql = sql +  " and  edata.data_value20 = '"+product+"' "; }
			
					
		      }
		      
		      if(lot != null && receivelot_no > 0) {

					if(receivelot_no == 1) { sql = sql +  "  and edata.data_value1 = '"+lot+"' "; }
					else if(receivelot_no == 2) { sql = sql +  " and  edata.data_value2 = '"+lot+"' "; }
					else if(receivelot_no == 3) { sql = sql +  " and  edata.data_value3 = '"+lot+"' "; }
					else if(receivelot_no == 4) { sql = sql +  " and  edata.data_value4 = '"+lot+"' "; }
					else if(receivelot_no == 5) { sql = sql +  "  and edata.data_value5 = '"+lot+"' "; }
					else if(receivelot_no == 6) { sql = sql +  " and  edata.data_value6 = '"+lot+"' "; }
					else if(receivelot_no == 7) { sql = sql +  " and  edata.data_value7 = '"+lot+"' "; }
					else if(receivelot_no == 8) { sql = sql +  "  and edata.data_value8 = '"+lot+"' "; }
					else if(receivelot_no == 9) { sql = sql +  "  and edata.data_value9 = '"+lot+"' "; }
					else if(receivelot_no == 10) { sql = sql +  "  and edata.data_value10 = '"+lot+"' "; }
					else if(receivelot_no == 11) { sql = sql +  "  and edata.data_value11 = '"+lot+"' "; }
					else if(receivelot_no == 12) { sql = sql +  "  and edata.data_value12 = '"+lot+"' "; }
					else if(receivelot_no == 13) { sql = sql +  " and  edata.data_value13 = '"+lot+"' "; }
					else if(receivelot_no == 14) { sql = sql +  " and  edata.data_value14 = '"+lot+"' "; }
					else if(receivelot_no == 15) { sql = sql +  "  and edata.data_value15 = '"+lot+"' "; }
					else if(receivelot_no == 16) { sql = sql +  "  and edata.data_value16 = '"+lot+"' "; }
					else if(receivelot_no == 17) { sql = sql +  "  and edata.data_value17 = '"+lot+"' "; }
					else if(receivelot_no == 18) { sql = sql +  "  and edata.data_value18 = '"+lot+"' "; }
					else if(receivelot_no == 19) { sql = sql +  "  and edata.data_value19 = '"+lot+"' "; }
					else if(receivelot_no == 20) { sql = sql +  " and  edata.data_value20 = '"+lot+"' "; }
		      }
		
					String sub1 = sql;
				
					
				
			  
			   
					String sqls = "SELECT "+sub+",edata.* FROM edata "
							+ " where   edata.data_eformid =?  and edata.data_eformrv =?    "+sub1+" GROUP BY  edata.data_department   ";
			
					System.out.println("sqls "+sqls);
					
					List<edata> edatas  =  jdbcTemplate.query(sqls,new Object[]{port,portrv},new edatamappers());
					return edatas;
		}
					
			      return null;		
					
	
	}
	
	
	@Override
	public List<edata> getReceivedtransaction(
			String product,String lot
			,int receiveproduct_no,int receivelot_no,int receive_no
			,int port,int portrv
			) {
		
		  System.out.println("product "+product);
		  System.out.println("lot "+lot);
		  System.out.println("port "+port);
		  System.out.println("portrv "+portrv); 
		  
		  
		String sub ="";
		if(receive_no > 0) {

						if(receive_no == 1) { sub = sub +  "   edata.data_value1 as data_value1  "; }
						else if(receive_no == 2) { sub = sub +  "   edata.data_value2 as data_value1  ";  }
						else if(receive_no == 3) { sub = sub +  "   edata.data_value3 as data_value1  ";  }
						else if(receive_no == 4) { sub = sub + "   edata.data_value4 as data_value1  ";  }
						else if(receive_no == 5) { sub = sub +  "   edata.data_value5 as data_value1  ";  }
						else if(receive_no == 6) { sub = sub + "   edata.data_value6 as data_value1  ";  }
						else if(receive_no == 7) { sub = sub +  "  edata.data_value7 as data_value1  ";  }
						else if(receive_no == 8) { sub = sub +  "   edata.data_value8 as data_value1  ";  }
						else if(receive_no == 9) { sub = sub +  "   edata.data_value9 as data_value1  ";  }
						else if(receive_no == 10) { sub = sub +  "  edata.data_value10 as data_value1  ";  }
						else if(receive_no == 11) { sub = sub +  "  edata.data_value11 as data_value1  ";  }
						else if(receive_no == 12) { sub = sub +  "   edata.data_value12 as data_value1  ";  }
						else if(receive_no == 13) { sub = sub +  "   edata.data_value13 as data_value1  ";  }
						else if(receive_no == 14) { sub = sub +  "   edata.data_value14 as data_value1  ";  }
						else if(receive_no == 15) { sub = sub +  "   edata.data_value15 as data_value1  ";  }
						else if(receive_no == 16) { sub = sub +  "  edata.data_value16 as data_value1  ";  }
						else if(receive_no == 17) { sub = sub +  "  edata.data_value17 as data_value1  ";  }
						else if(receive_no == 18) { sub = sub +  "   edata.data_value18 as data_value1  ";  }
						else if(receive_no == 19) { sub = sub +  "  edata.data_value19 as data_value1  ";  }
						else if(receive_no == 20) { sub = sub +  "  edata.data_value20 as data_value1  ";  }
			     
				 String sql = "";
			
		      if(product != null && receiveproduct_no > 0) {

					if(receiveproduct_no == 1) { sql = sql +  "     and edata.data_value1 = '"+product+"' ";  }
					else if(receiveproduct_no == 2) { sql = sql +  " and  edata.data_value2 = '"+product+"' "; }
					else if(receiveproduct_no == 3) { sql = sql +  " and  edata.data_value3 = '"+product+"' "; }
					else if(receiveproduct_no == 4) { sql = sql +  " and  edata.data_value4 = '"+product+"' "; }
					else if(receiveproduct_no == 5) { sql = sql +  "  and edata.data_value5 = '"+product+"' "; }
					else if(receiveproduct_no == 6) { sql = sql +  " and  edata.data_value6 = '"+product+"' "; }
					else if(receiveproduct_no == 7) { sql = sql +  " and  edata.data_value7 = '"+product+"' "; }
					else if(receiveproduct_no == 8) { sql = sql +  "  and edata.data_value8 = '"+product+"' "; }
					else if(receiveproduct_no == 9) { sql = sql +  "  and edata.data_value9 = '"+product+"' "; }
					else if(receiveproduct_no == 10) { sql = sql +  "  and edata.data_value10 = '"+product+"' "; }
					else if(receiveproduct_no == 11) { sql = sql +  "  and edata.data_value11 = '"+product+"' "; }
					else if(receiveproduct_no == 12) { sql = sql +  "  and edata.data_value12 = '"+product+"' "; }
					else if(receiveproduct_no == 13) { sql = sql +  " and  edata.data_value13 = '"+product+"' "; }
					else if(receiveproduct_no == 14) { sql = sql +  " and  edata.data_value14 = '"+product+"' "; }
					else if(receiveproduct_no == 15) { sql = sql +  "  and edata.data_value15 = '"+product+"' "; }
					else if(receiveproduct_no == 16) { sql = sql +  "  and edata.data_value16 = '"+product+"' "; }
					else if(receiveproduct_no == 17) { sql = sql +  "  and edata.data_value17 = '"+product+"' "; }
					else if(receiveproduct_no == 18) { sql = sql +  "  and edata.data_value18 = '"+product+"' "; }
					else if(receiveproduct_no == 19) { sql = sql +  "  and edata.data_value19 = '"+product+"' "; }
					else if(receiveproduct_no == 20) { sql = sql +  " and  edata.data_value20 = '"+product+"' "; }
			
					
		      }
		      
		      if(lot != null && receivelot_no > 0) {

					if(receivelot_no == 1) { sql = sql +  "  and edata.data_value1 = '"+lot+"' "; }
					else if(receivelot_no == 2) { sql = sql +  " and  edata.data_value2 = '"+lot+"' "; }
					else if(receivelot_no == 3) { sql = sql +  " and  edata.data_value3 = '"+lot+"' "; }
					else if(receivelot_no == 4) { sql = sql +  " and  edata.data_value4 = '"+lot+"' "; }
					else if(receivelot_no == 5) { sql = sql +  "  and edata.data_value5 = '"+lot+"' "; }
					else if(receivelot_no == 6) { sql = sql +  " and  edata.data_value6 = '"+lot+"' "; }
					else if(receivelot_no == 7) { sql = sql +  " and  edata.data_value7 = '"+lot+"' "; }
					else if(receivelot_no == 8) { sql = sql +  "  and edata.data_value8 = '"+lot+"' "; }
					else if(receivelot_no == 9) { sql = sql +  "  and edata.data_value9 = '"+lot+"' "; }
					else if(receivelot_no == 10) { sql = sql +  "  and edata.data_value10 = '"+lot+"' "; }
					else if(receivelot_no == 11) { sql = sql +  "  and edata.data_value11 = '"+lot+"' "; }
					else if(receivelot_no == 12) { sql = sql +  "  and edata.data_value12 = '"+lot+"' "; }
					else if(receivelot_no == 13) { sql = sql +  " and  edata.data_value13 = '"+lot+"' "; }
					else if(receivelot_no == 14) { sql = sql +  " and  edata.data_value14 = '"+lot+"' "; }
					else if(receivelot_no == 15) { sql = sql +  "  and edata.data_value15 = '"+lot+"' "; }
					else if(receivelot_no == 16) { sql = sql +  "  and edata.data_value16 = '"+lot+"' "; }
					else if(receivelot_no == 17) { sql = sql +  "  and edata.data_value17 = '"+lot+"' "; }
					else if(receivelot_no == 18) { sql = sql +  "  and edata.data_value18 = '"+lot+"' "; }
					else if(receivelot_no == 19) { sql = sql +  "  and edata.data_value19 = '"+lot+"' "; }
					else if(receivelot_no == 20) { sql = sql +  " and  edata.data_value20 = '"+lot+"' "; }
		      }
		
					String sub1 = sql;
				
					
				
			  
			   
					String sqls = "SELECT "+sub+",edata.* FROM edata "
							+ " where   edata.data_eformid =?  and edata.data_eformrv =?    "+sub1+"  ";
			
					System.out.println("sqls "+sqls);
					
					List<edata> edatas  =  jdbcTemplate.query(sqls,new Object[]{port,portrv},new edatamappers());
					return edatas;
		}
					
			      return null;		
					
	
	}
	

	@Override
	public int[][] update(List<edata> edatas, int batchSize) {

		String sql = "UPDATE edata  set data_title1=?,data_value1=?,data_title2=?,data_value2=?,data_title3=?,data_value3=?,data_title4=?,data_value4=?,data_title5=?,data_value5=?"
				+ ",data_title6=?,data_value6=?,data_title7=?,data_value7=?,data_title8=?,data_value8=?,data_title9=?,data_value9=?,data_title10=?,data_value10=?"
				+ ",data_title11=?,data_value11=?,data_title12=?,data_value12=?,data_title13=?,data_value13=?,data_title14=?,data_value14=?,data_title15=?,data_value15=?"
				+ ",data_title16=?,data_value16=?,data_title17=?,data_value17=?,data_title18=?,data_value18=?,data_title19=?,data_value19=?,data_title20=?,data_value20=?,data_due=?"
				+ ",data_status=?,auto_read=? "
				+ " WHERE data_id =?";
		
        int[][] updateCounts = jdbcTemplate.batchUpdate(sql,edatas,batchSize,
                new ParameterizedPreparedStatementSetter<edata>() {
                    public void setValues(PreparedStatement ps, edata argument) 
						throws SQLException {
                        ps.setString(1, argument.getData_title1());
                        ps.setString(2, argument.getData_value1());
                        ps.setString(3, argument.getData_title2());
                        ps.setString(4, argument.getData_value2());
                        ps.setString(5, argument.getData_title3());
                        ps.setString(6, argument.getData_value3());
                        ps.setString(7, argument.getData_title4());
                        ps.setString(8, argument.getData_value4());
                        ps.setString(9, argument.getData_title5());
                        ps.setString(10, argument.getData_value5());
                        ps.setString(11, argument.getData_title6());
                        ps.setString(12, argument.getData_value6());
                        ps.setString(13, argument.getData_title7());
                        ps.setString(14, argument.getData_value7());
                        ps.setString(15, argument.getData_title8());
                        ps.setString(16, argument.getData_value8());
                        ps.setString(17, argument.getData_title9());
                        ps.setString(18, argument.getData_value9());
                        ps.setString(19, argument.getData_title10());
                        ps.setString(20, argument.getData_value10());
                        ps.setString(21, argument.getData_title11());
                        ps.setString(22, argument.getData_value11());
                        ps.setString(23, argument.getData_title12());
                        ps.setString(24, argument.getData_value12());
                        ps.setString(25, argument.getData_title13());
                        ps.setString(26, argument.getData_value13());
                        ps.setString(27, argument.getData_title14());
                        ps.setString(28, argument.getData_value14());
                        ps.setString(29, argument.getData_title15());
                        ps.setString(30, argument.getData_value15());
                        ps.setString(31, argument.getData_title16());
                        ps.setString(32, argument.getData_value16());
                        ps.setString(33, argument.getData_title17());
                        ps.setString(34, argument.getData_value17());
                        ps.setString(35, argument.getData_title18());
                        ps.setString(36, argument.getData_value18());
                        ps.setString(37, argument.getData_title19());
                        ps.setString(38, argument.getData_value19());
                        ps.setString(39, argument.getData_title20());
                        ps.setString(40, argument.getData_value20());
                        ps.setString(41, argument.getData_due());
                        ps.setString(42, argument.getData_status());
                        ps.setInt(43, argument.getAuto_read());
                        ps.setInt(44, argument.getData_id());
          
                    }
                });
        return updateCounts;

    }
	
	

	


	
	@Override
	public void saveSubdata(List<SubData> Subdata,int id) {
		

		

		String sql ="INSERT INTO sub_items (description,type,qty,unit,id_refer,actual_price,received_qty,issued_qty,issued_amount,status,id_cleared_by,id_stock,pd,batch,date_action,requester) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		  jdbcTemplate.batchUpdate(sql,Subdata,1000,
	                new ParameterizedPreparedStatementSetter<SubData>() {
	                    public void setValues(PreparedStatement ps, SubData argument) 
							throws SQLException {
	                        ps.setString(1, argument.getDescription());
	                        ps.setString(2, argument.getType());
	                        ps.setString(3, argument.getQty());
	                        ps.setString(4, argument.getUnit());
	                        ps.setInt(5,id);
	                        ps.setString(6, argument.getActual_price());
	                        ps.setString(7, argument.getReceived_qty());
	                        ps.setString(8, argument.getIssued_qty());
	                        ps.setString(9, argument.getIssued_amount());
	                        ps.setString(10, argument.getStatus());
	                        ps.setInt(11, argument.getId_cleared_by());
	                        ps.setInt(12, argument.getId_stock());
	                        ps.setString(13, argument.getPd());
	                        ps.setString(14, argument.getBatch());
	                        ps.setString(15, argument.getDate_action());
	                        ps.setString(16, argument.getRequester());
	                    }
	                });
		
		
	}

	@Override
	public List<SubData> getSubDataAll(int idrefer) {
				String sql = "SELECT * FROM sub_items WHERE id_refer =? ";
				List<SubData> SubData  =  jdbcTemplate.query(sql,new Object[]{idrefer},new subdatamappers());
				return SubData;	
	}
	
	

	@Override
	public void AppSubDataAll(int idrefer) {
				String sql = "Update sub_items SET status = 'APPROVED' WHERE id_refer =? ";
				jdbcTemplate.update(sql,new Object[]{idrefer});
			
	}
	



	@Override
	public edata getStatus(int plan_id) {
		
		  try {
			  String sql = "SELECT data_status, COUNT(*) AS count_status " +
			             "FROM edata " +
			             "WHERE data_eformid = ? " +
			             "AND data_dateadd >= NOW() - INTERVAL 2 DAY " +
			             "GROUP BY data_status " +
			             "ORDER BY count_status DESC " +
			             "LIMIT 1";
				edata edatas  =  jdbcTemplate.queryForObject(sql,new Object[]{plan_id},new edatamappers());
				return  edatas;
		  }catch(EmptyResultDataAccessException e) {
				 
			  edata edatas  = new edata();
			  edatas.setData_status("0");
			  return edatas;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
		
	}
	
	

	@Override
	 public void CreateTable(String sql) {

		            jdbcTemplate.execute(sql);
		            System.out.println("Table created successfully.");
		 
	    }
	
	
	
	
	
	@Override
	public edata_stock geteData_stock(int edaia_id) {

		  try {
				String sql = "SELECT * FROM edata_stock WHERE edata_id =? limit 1";
				edata_stock stock  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new data_stockmappers());
				return stock;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	
	
	
	@Override
	public edata_register geteData_registerById(int id) {

		  try {
				String sql = "SELECT * FROM edata_register WHERE id =? limit 1";
				edata_register register  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new edata_registermappers());
				return register;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	@Override
	public edata_register geteData_register(int edaia_id) {

		System.out.print("geteData_register edata_id "+edaia_id);
		
		
		  try {
				String sql = "SELECT * FROM edata_register WHERE edata_id =? limit 1";
				edata_register register  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new edata_registermappers());
				return register;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	@Override
	public edata_product geteData_product(int edaia_id) {

		System.out.print("geteData_register edata_id "+edaia_id);
		
		
		  try {
				String sql = "SELECT * FROM edata_product WHERE edata_id =? limit 1";
				edata_product register  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new edata_productmappers());
				return register;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	

	@Override
	public edata_product geteData_productById(int id) {


		  try {
				String sql = "SELECT * FROM edata_product WHERE id =? limit 1";
				edata_product register  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new edata_productmappers());
				return register;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	
	
	@Override
	public edata_procurement geteData_procurement(int edaia_id) {

		System.out.print("edata_id "+edaia_id);
		
		  try {
				String sql = "SELECT * FROM edata_procurement WHERE edata_id =? limit 1";
				edata_procurement action  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new edata_procurementmappers());
				return action;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	@Override
	public production_directive geteData_pd(int edaia_id) {

		System.out.print("edata_id "+edaia_id);
		
		  try {
				String sql = "SELECT * FROM production_directive WHERE edata_id =? limit 1";
				production_directive action  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new production_directivemappers());
				return action;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	@Override
	public other geteData_other(int edaia_id) {

		System.out.print("edata_id "+edaia_id);
		
		  try {
				String sql = "SELECT * FROM other WHERE edata_id =? limit 1";
				other action  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new othermappers());
				return action;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	
	@Override
	public edata_production_record geteEdata_production_record(int edaia_id) {

		System.out.print("edata_id "+edaia_id);
		
		  try {
				String sql = "SELECT * FROM edata_production_record WHERE edata_id =? limit 1";
				edata_production_record action  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new edata_production_recordmappers());
				return action;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	
	@Override
	public production_list geteData_pl(int edaia_id) {

		System.out.print("edata_id "+edaia_id);
		
		  try {
				String sql = "SELECT * FROM production_list WHERE edata_id =? limit 1";
				production_list action  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new production_listmappers());
				return action;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	
	
	
	
	
	@Override
	public edata_register_processing geteData_register_processing(int edaia_id) {

		System.out.print("edata_id "+edaia_id);
		
		  try {
				String sql = "SELECT * FROM edata_register_processing WHERE edata_id =? limit 1";
				edata_register_processing action  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new edata_register_processingmappers());
				return action;
				
		  }catch(EmptyResultDataAccessException e) {
				 
			  return null;
			    
		}catch(IncorrectResultSizeDataAccessException e) {
				 throw new RuntimeException("More than one getStatus with the same Id .......");
		}
	}
	
	
	@Override
	public List<stock> getStock_card(int edaia_id) {
	
				  try {
						String sql = "SELECT * FROM stock WHERE stock_eform =? ";
						List<stock> stock  =  jdbcTemplate.query(sql,new Object[]{edaia_id},new stockmappers());
						return stock;
				  }catch(EmptyResultDataAccessException e) {
						 
					  return null;
					    
				}catch(IncorrectResultSizeDataAccessException e) {
						 throw new RuntimeException("More than one getStatus with the same Id .......");
				}
		
	}

	

	
	
	@Override
	public List<stock> getStock_cardAll(String type) {
		
		
		System.err.println("getStock_cardAll Type" + type);
		
		
		
		if(type != null) {
			String sql = "SELECT * FROM stock WHERE FIND_IN_SET(stock_type,?)  AND ( stock_status !='DAMAGED' OR stock_status != 'EXPIRED' ) "
					+ "ORDER BY stock_id  DESC limit 500  ";
			List<stock> stock  =  jdbcTemplate.query(sql,new Object[]{type},new stockmappers());	
			return stock;	
		}else {
			String sql = "SELECT * FROM stock  WHERE  stock_status !='DAMAGED' OR stock_status != 'EXPIRED'     "
					+ "ORDER BY stock_id  DESC limit 500  ";
			List<stock> stock  =  jdbcTemplate.query(sql,new stockmappers());
			return stock;	
		}
		
		
				
	}	
	
	
	
	@Override
	public List<stock> getAllStock(String type) {
				String sql = "SELECT * FROM stock WHERE stock_type =? ";
				List<stock> stock  =  jdbcTemplate.query(sql,new Object[]{type},new stockmappers());
				return stock;	
	}
	
	@Override
	public List<stock> getAllStock(String type,int limit) {
				String sql = "SELECT * FROM stock WHERE stock_type =? limit ? ";
				List<stock> stock  =  jdbcTemplate.query(sql,new Object[]{type,limit},new stockmappers());
				return stock;	
	}





	@Override
	public void save_process(process processs) {
		
			Object[] processInfo = {
					processs.getName(),
					processs.getDescription(),
					processs.getRelatedpeople(),
					processs.getId_refer(),
					processs.getId_condition(),
					processs.getProcess_join(),
					processs.getProcess_logo(),
					processs.getAllow_loose_link(),
					processs.getProgram_type(),
					processs.getTabform(),
					processs.getUsed_valid(),
					processs.getUsed_attachment(),
					processs.getVersion(),
					processs.getProfile_column(),
					processs.getProduct_master_column()
					
			};

			if(processs.getId() > 0) {
				String sql ="Update process set name=?,description=?,relatedpeople=?,id_refer=?,id_condition=?"
						+ ",process_join=?,process_logo=?,allow_loose_link=?,program_type=?,tabform =?,used_valid=?,used_attachment=?,version=?"
						+ ",profile_column=?,product_master_column=?  WHERE id = "+processs.getId();
				jdbcTemplate.update(sql,processInfo);
			}else {
				String sql ="INSERT INTO process (name,description,relatedpeople,id_refer,id_condition,process_join,process_logo"
						+ ",allow_loose_link,program_type,tabform,used_valid,used_attachment,version,profile_column,product_master_colum) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,processInfo);
			}
	
	}
	
	@Override
	public UseExtension getActiveSegmentIssueConfig() {
	    try {
	
	        String sql = "SELECT * FROM use_extension " +
	                     "WHERE segment_issue_count = 1 " +
	                     "LIMIT 1";
	        
	        return jdbcTemplate.queryForObject(sql, new UseExtensionmapper());
	    } catch (EmptyResultDataAccessException e) {
	        return null; // กรณีไม่มีการตั้งค่าไว้
	    }
	}
	
	
	@Override
	public void save_extension(UseExtension extension) {
		
			Object[] extensionInfo = {
					extension.getName(),
					extension.getExtension_id(),
					extension.getProcess_id(),
					extension.getAccess_user(),
					extension.getAccess_admin(),
					extension.getEform_id(),
					extension.getUse_process_id(),
					extension.getAuto_update_parameter_column(),
					extension.getAuto_update_tocolumn(),
					extension.getAuto_update_segment(),
					extension.getIcon(),
					extension.getSegment_count(),
					
					extension.getSegment_issue_count(),
					extension.getSegment_issue_column(),
			};
			
			
	
			if(extension.getId() > 0) {
				String sql ="Update use_extension set name=?,extension_id=?,process_id=?,access_user=?"
						+ ",access_admin=?,eform_id=?,use_process_id=?"
						+ ",auto_update_parameter_column=?,auto_update_tocolumn=?,auto_update_segment=? ,icon=?,segment_count=?,segment_issue_count=?,segment_issue_column=?   WHERE id = "+extension.getId();
				jdbcTemplate.update(sql,extensionInfo);
			}else {
				String sql ="INSERT INTO use_extension (name,extension_id,process_id,access_user"
						+ ",access_admin,eform_id,use_process_id,auto_update_parameter_column,auto_update_tocolumn,auto_update_segment,icon,segment_count,segment_issue_count,segment_issue_column)"
						+ " VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,extensionInfo);
			}
	
	}
	
	
	@Override
	public void save_Measurement_rules(Measurement_rules rules) {
		
			
			if(rules.getId() > 0) {
				
				Object[] rulesInfo = {
						rules.getTitle(),
						rules.getWork_cycle(),
						rules.getRules(),
						rules.getThis_closing_status(),
						rules.getDestination_closing_status(),
						rules.getThis_target(),
						rules.getDestination_column_operation(),
						rules.getId(),
				};
				
				
				String sql ="UPDATE measurement_rules  "
						+ "SET title = ? ,work_cycle = ?  ,rules = ?  ,this_closing_status = ? "
						+ ",destination_closing_status = ?  ,this_target = ?  ,destination_column_operation= ?   WHERE id = ?";
				jdbcTemplate.update(sql,rulesInfo);
			}else {
				
				Object[] rulesInfo = {
						rules.getThis_process(),
						rules.getDestination_process(),
						rules.getTitle(),
						rules.getWork_cycle(),
						rules.getRules(),
				};
				
				
				String sql ="INSERT INTO measurement_rules (this_process,destination_process,title,work_cycle,rules) VALUE(?,?,?,?,?) ";
				jdbcTemplate.update(sql,rulesInfo);
			}
	
	}

	
	@Override
	public List<Measurement_rules> getAllMeasurementRulesByThisProcess(int id) {
		
		String sql ="select * from measurement_rules  WHERE this_process = ? ";
		List<Measurement_rules>  list = jdbcTemplate.query(sql,new Object[] {id},new Measurement_rulesmappers());
		return list;
	}
	@Override
	public List<Measurement_rules> getAllMeasurement_rules() {
		
		String sql ="select * from measurement_rules ";
		List<Measurement_rules>  list = jdbcTemplate.query(sql,new Measurement_rulesmappers());
		return list;
	}
	@Override
	public Measurement_rules getMeasurement_rules(int id) {
		
		String sql ="select * from measurement_rules where id =? ";
		Measurement_rules  data = jdbcTemplate.queryForObject(sql,new Object[] {id},new Measurement_rulesmappers());
		return data;
	}
	
	
	@Override
	public int deleteMeasurement_rules(int id) {
		
		String sql ="DELETE  from measurement_rules where id =? ";
		return jdbcTemplate.update(sql,new Object[] {id});

	}
	
	
	
	@Override
	public void save_management(Management management) {
		
			Object[] managementInfo = {
					management.getProcess_id(),
					management.getManagement_title(),
					management.getManagement_abbreviation(),
					management.getUser_access(),
					management.getAdmin_access(),
					management.getManagement_id(),
			};
			
			
			
			if(management.getId() > 0) {
				String sql ="Update management set process_id=?,management_title=?,management_abbreviation=?,user_access=?,admin_access=?,management_id=?"
						+ "  WHERE id = "+management.getId();
				jdbcTemplate.update(sql,managementInfo);
			}else {
				String sql ="INSERT INTO management (process_id,management_title,management_abbreviation,user_access,admin_access,management_id) VALUE(?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,managementInfo);
			}
	
	}
	
	
	@Override
	public void SaveExtensionParameter(ExtensionParameter Parameter) {
		
		  String deleteSql = "DELETE FROM extension_parameter WHERE use_extension_id = ?";
		  jdbcTemplate.update(deleteSql, Parameter.getUse_extension_id());
          
          
		String[] Parameter_column = Parameter.getParameter_column().split(",");
		String[] Parameter_keyname = Parameter.getParameter_keyname().split(",");
		String[] Parameter_type = Parameter.getParameter_type().split(",");
		String[] Parameter_columnismain = Parameter.getParameter_columnismain().split(",");
		String[] Extension_process= Parameter.getExtension_process().split(",");
		

		String sql ="INSERT INTO extension_parameter (parameter_column,parameter_keyname,parameter_type,parameter_columnismain,use_extension_id,extension_process) "
				+ "VALUE(?,?,?,?,?,?) ";
	
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {
	             
	              ps.setString(1,Parameter_column[i]);  
	              ps.setString(2,Parameter_keyname[i]);  
	              ps.setString(3,Parameter_type[i]);  
	              ps.setString(4,Parameter_columnismain[i]);  
	              ps.setInt(5,Parameter.getUse_extension_id());
	              ps.setString(6,Extension_process[i]);  
	            }

	            @Override
	            public int getBatchSize() {
	                return Parameter_column.length;
	            }
	        });
		
	}
	

	@Override
	public void setRelationship(int processes_id,int link_to,String relationship,String data_transfer,String integrity,String current_status) {

		
		if(link_to == 1) {
			String sql ="UPDATE process SET link_to=?,relationship=?,data_transfer=?,integrity=?,current_status=? WHERE id=? ";
			jdbcTemplate.update(sql,new Object[] {link_to,relationship,data_transfer,integrity,current_status,processes_id});
		}else {
			
			String sql ="UPDATE process SET link_to=?,relationship=?,data_transfer=?,integrity=?,current_status=? WHERE id=? ";
			jdbcTemplate.update(sql,new Object[] {link_to,null,null,null,null,processes_id});
		}
		
		
	}
	@Override
	public void saveprocessinfo(process process) {
			String sql ="UPDATE process SET enums=? WHERE id=? ";
			jdbcTemplate.update(sql,new Object[] {process.getEnums(),process.getId()});

	}
	
	
	
	
	@Override
	public void setFeels(process process) {

		
		Object[] processInfo = {
				process.getOutgoing_column1(),
				process.getFor_column1(),
				process.getFeed_type1(),
				process.getOutgoing_column2(),
				process.getFor_column2(),
				process.getFeed_type2(),
				process.getOutgoing_column3(),
				process.getFor_column3(),
				process.getFeed_type3(),
				process.getId()
		};
		
		String sql ="UPDATE process SET "
				+ "outgoing_column1=?,for_column1=?,feed_type1=?"
				+ ",outgoing_column2=?,for_column2=?,feed_type2=?"
				+ ",outgoing_column3=?,for_column3=?,feed_type3=? "
				+ "WHERE id=? ";
		jdbcTemplate.update(sql,processInfo);
		
		
	}


	
	
	
	
	@Override
	public void SaveReference(int processes_id,String selectedIds) {

		String sql ="UPDATE edata SET reference=? WHERE data_id=? ";
		jdbcTemplate.update(sql,new Object[] {selectedIds,processes_id});
	}
	
	@Override
	public List<process> getReadyForAutoGradeProcesses() {
        String sql = "SELECT * FROM process WHERE "
                   + "edata_idoc_grade_ref IS NOT NULL AND edata_idoc_grade_ref != '' "
                   + "AND applied_status_array IS NOT NULL AND applied_status_array != '' "
                   + "AND is_ex_movement_active IS NOT NULL AND is_ex_movement_active != '' "
                   + "AND cycle_duration > 0 "
                   + "AND grade_frequency_mapping IS NOT NULL AND grade_frequency_mapping != ''";

        return jdbcTemplate.query(sql, new processmapper()); 
    }
	
	@Override
	public void setAutomatedGrading(process process) {

		// 1. ปรับคำสั่ง SQL ให้ Update ฟิลด์ใหม่ทั้งหมด
		String sql = "UPDATE process SET "
				   + "edata_idoc_grade_ref = ?, "
				   + "applied_status_array = ?, "
				   + "is_ex_movement_active = ?, "
				   + "cycle_duration = ?, "
				   + "grade_frequency_mapping = ? "
				   + "WHERE id = ?";

		// 2. ส่งค่าพารามิเตอร์เข้าไปเรียงตามลำดับเครื่องหมาย ? ใน SQL
		// หมายเหตุ: ต้องแน่ใจว่าคลาส process มีการสร้าง Getter methods ไว้แล้ว
		jdbcTemplate.update(sql, new Object[] {
				process.getEdata_idoc_grade_ref(),
				process.getApplied_status_array(),
				process.getIs_ex_movement_active(),
				process.getCycle_duration(),
				process.getGrade_frequency_mapping(),
				process.getId()
		});
		
	}

	@Override
	public void SaveAttachdocuments(int edata_id,int doc_id,String type) {

		String sql ="UPDATE edata SET attachdocuments=? ,attach_type=? WHERE data_id=? ";
		jdbcTemplate.update(sql,new Object[] {doc_id,type,edata_id});
	}


	@Override
	public List<process> getAllProcess(int id_refer,int id) {
		String sql ="SELECT * FROM   process WHERE id_refer =? AND id != ? AND  link_to =  1 ";
		List<process> process = jdbcTemplate.query(sql,new Object[]{id_refer,id},new processmapper());
		return process;
	}
	

	@Override
	public List<process> getAllProcess(int id_refer) {

		String sql ="SELECT * FROM   process WHERE id_refer =? ";
		List<process> process = jdbcTemplate.query(sql,new Object[]{id_refer},new processmapper());
		return process;
	}
	
	
	
	
	@Override
	public List<process> getAllProcess() {

		String sql ="SELECT * FROM   process   ";
		List<process> process = jdbcTemplate.query(sql,new processmapper());
		return process;
	}
	
	@Override
	public List<process> getAllProcessBytype(int type) {

		String sql ="SELECT * FROM   process WHERE  program_type = ?";
		List<process> process = jdbcTemplate.query(sql,new Object[]{type},new processmapper());
		return process;
	}
	
	
	@Override
	public List<process> getAllProcessBytype(int id_refer,int type) {

		String sql ="SELECT * FROM   process WHERE id_refer =? AND program_type = ?";
		List<process> process = jdbcTemplate.query(sql,new Object[]{id_refer,type},new processmapper());
		return process;
	}
	
	@Override
	public List<UseExtension> getallUseExtension(int id) {

		String sql ="SELECT * FROM  use_extension WHERE  process_id = ?";
		List<UseExtension> list = jdbcTemplate.query(sql,new Object[]{id},new UseExtensionmapper());
		return list;
	}
	
	@Override
	public List<UseExtension> getallUseExtension() {

		String sql ="SELECT * FROM  use_extension ";
		List<UseExtension> list = jdbcTemplate.query(sql,new UseExtensionmapper());
		return list;
	}
	
	@Override
	public UseExtension getUseExtension(int id) {

		String sql ="SELECT * FROM  use_extension WHERE  id = ? ";
		UseExtension data = jdbcTemplate.queryForObject(sql,new Object[]{id},new UseExtensionmapper());
		return data;
	}
	
	@Override
	public Management getManagement(int id) {

		String sql ="SELECT * FROM  management WHERE  id = ? ";
		Management data = jdbcTemplate.queryForObject(sql,new Object[]{id},new ManagementParametermapper());
		return data;
	}
	
	
	
	@Override
	public List<ExtensionParameter> getallExtensionParameterByuseextension(int id) {

		String sql ="SELECT * FROM  extension_parameter WHERE  use_extension_id = ? ";
		List<ExtensionParameter> List = jdbcTemplate.query(sql,new Object[]{id},new ExtensionParametermapper());
		return List;
	}
	
	@Override
	public List<ExtensionParameter> getallExtensionParameterAndeformfieldByuseextension(int id) {

		String sql ="SELECT extension_parameter.*,eformfield.field_postion FROM extension_parameter   LEFT JOIN eformfield "
				+ "ON extension_parameter.parameter_column = eformfield.field_id  "
				+ "WHERE  extension_parameter.use_extension_id = ? ";
		List<ExtensionParameter> List = jdbcTemplate.query(sql,new Object[]{id},new ExtensionParametermapper());
		return List;
	}
	
	@Override
	public Extension getExtension(int id) {

		String sql ="SELECT * FROM  extension WHERE  extension_process = ? ";
		Extension data = jdbcTemplate.queryForObject(sql,new Object[]{id},new Extensionmapper());
		return data;
	}
	
	
	
	@Override
	public List<Extension> getAllExtensionBytype(String type) {

		String sql ="SELECT * FROM  extension WHERE  extension_type = ?";
		List<Extension> list = jdbcTemplate.query(sql,new Object[]{type},new Extensionmapper());
		return list;
	}
	
	
	@Override
	public List<process> findAllProcessAllowLooseLink() {

		String sql ="SELECT * FROM   process  WHERE allow_loose_link = 1 ";
		List<process> process = jdbcTemplate.query(sql,new processmapper());
		return process;
	}

	@Override
	public void removeUseExtension(int id) {

		String sql ="DELETE FROM use_extension  WHERE id =? ";
		 jdbcTemplate.update(sql,new Object[]{id});
	
	}

	@Override
	public void removeManagements(int id) {

		String sql ="DELETE FROM management  WHERE id =? ";
		 jdbcTemplate.update(sql,new Object[]{id});
	
	}

	
	@Override
	public List<process> getAllProcessJoin(int id) {

		String sql ="SELECT * FROM   process  LEFT JOIN workflow ON  process.id_refer =  workflow.workflow_id "
				+ "WHERE  FIND_IN_SET(?,workflow.workflow_join)";
		
		
		
		List<process> process = jdbcTemplate.query(sql,new Object[]{id},new processmapper());
		return process;
	}

	
	

	@Override
	public List<process> getAllProcess(String id_refer) {

		String sql ="SELECT * FROM   process WHERE find_in_set (id,?) ";
		List<process> process = jdbcTemplate.query(sql,new Object[]{id_refer},new processmapper());
		return process;
	}

	@Override
	public process getProcess(int id) {
		
		try {
			String sql ="SELECT * FROM   process WHERE id =? ";
			process process = jdbcTemplate.queryForObject(sql,new Object[]{id},new processmapper());
			return process;
			}catch(EmptyResultDataAccessException e) {
			 return null;
		  }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getPo with the same Id .......");
		   
		  }
		
	
	}
	@Override
	public void editIndexScope(process process) {
		
			String sql ="UPDATE process set register=?,register_type=?,access_user=?,access_admin=?,direct_data_edit_mode=? ,requestsystem=? WHERE id =? ";
			jdbcTemplate.update(sql,new Object[]{process.getRegister(),process.getRegister_type(),process.getAccess_user(),process.getAccess_admin()
					,process.getDirect_data_edit_mode(),process.getRequestsystem(),process.getId()});
			
	}
	@Override
	public void editViewIndexScope(process process) {
		
			String sql ="UPDATE process set show_limit=?,listview=?,listview2=? WHERE id =? ";
			jdbcTemplate.update(sql,new Object[]{process.getShow_limit(),process.getListview(),process.getListview2(),process.getId()});
	
	}
	
	@Override
	public void editClosing_status(process process) {
		
			String sql ="UPDATE process set closing_status=?"
					+ ",closing_status_expired=?,expires_in_days=?,is_acknowledge_required=?"
					+ ",closing_evidence_type=?,can_refuse_and_return=? ,is_attachment_required_to_close=?,sub_process_expired=?   WHERE id =? ";
			jdbcTemplate.update(sql,new Object[]{
					process.getClosing_status(),
					process.getClosing_status_expired(),
					process.getExpires_in_days(),
					process.getIs_acknowledge_required(),
					process.getClosing_evidence_type(),
					process.getCan_refuse_and_return(),
					process.getIs_attachment_required_to_close(),
					process.getSub_process_expired(),
					process.getId()});
	
	}
	
	

	@Override
	public List<edata_production_record> getTrackingProcess(String name,int id) {

		
		System.out.println("data_id  "+id);
		
		
		   String sql = "SELECT " +
	                "edata.data_dateadd AS start_date_time, " +
	                "edata.data_status AS status, " +
	                "edata_production_record.process_step,edata_production_record.edata_id,edata_production_record.id " +
	                "FROM edata_production_record " +
	                "LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id " +
	                "WHERE edata_production_record.process_step = ?  AND edata.process_id=? " +
	                "AND edata.data_dateadd >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";

	        List<edata_production_record> records= jdbcTemplate.query(sql,new Object[]{name,id},new edata_production_recordmappers2());	    

	        
	        
	        if(records.size() == 0) {
	        
	        String sqls = "SELECT " +
	                "edata.data_dateadd AS start_date_time, " +
	                "edata.data_status AS status, " +
	                "other.process_step,other.edata_id,other.id " +
	                "FROM other " +
	                "LEFT JOIN edata ON edata.data_id = other.edata_id " +
	                "WHERE other.process_step = ?  AND edata.process_id=?  " +
	                "AND edata.data_dateadd >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
	        
	        records= jdbcTemplate.query(sqls,new Object[]{name,id},new edata_production_recordmappers2());	   
	        }
	     
	        
	        return records;
	        
	        
	        
	}

	
	
	
	@Override
	public List<edata_production_record> getTrackingRecord(String id) {

		   String sql = "SELECT " +
	                "edata.data_dateadd AS start_date_time, " +
	                "edata.data_status AS status, " +
	                "edata_production_record.* " +
	                "FROM edata_production_record " +
	                "LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id " +
	                "WHERE  edata.process_id=?  " +
	                "AND edata.data_dateadd >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";

	        List<edata_production_record> records= jdbcTemplate.query(sql,new Object[]{id},new edata_production_recordmappers3());	    
	        return records;
     
	}
	
	@Override
	public List<other> getTrackingOther(String name) {

		  String sqls = "SELECT " +
		            "edata.data_dateadd AS start_date_time, " +
		            "edata.data_status AS status, " +
		            "other.* " +
		            "FROM other " +
		            "LEFT JOIN edata ON edata.data_id = other.edata_id " +
		            "WHERE  edata.process_id=?  " +
		            "AND edata.data_dateadd >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
		    
		    List<other> other= jdbcTemplate.query(sqls,new Object[]{name},new othermappers2());	   
		    return other;
	}


	

	@Override
	public void remove_process(int id) {
		String sql ="DELETE  FROM   process  WHERE id =? ";
		jdbcTemplate.update(sql,new Object[]{id});
		
	}
	
	@Override
	public void remove_process_all(int id_refer) {
		String sql ="DELETE  FROM   process  WHERE id_refer =? ";
		jdbcTemplate.update(sql,new Object[]{ id_refer});
		
	}



	@Override
	public void save_subprocess(sub_processes sub_processes) {
		
		Object[] processInfo = {
				sub_processes.getStep(),
				sub_processes.getDescription(),
				sub_processes.getProcesse_id(),
				sub_processes.getMaindatacolumn(),
				sub_processes.getFiltermain(),
				sub_processes.getSubdatacolumn(),
				sub_processes.getFiltersub(),
				sub_processes.getAutoiddatacolumn(),
				sub_processes.getCode_app(),
				sub_processes.getActivate_autoid(),
				sub_processes.getActivate_subdata(),
				sub_processes.getActivate_checking_list(),
				sub_processes.getChecking_list(),
				sub_processes.getActivate_editlist(),
				sub_processes.getEdit_list(),
				sub_processes.getActivate_autorid(),
				sub_processes.getCode_rid(),
				sub_processes.getAction(),
				sub_processes.getAction_batch_insert(),
				sub_processes.getAction_batch_modify(),
				sub_processes.getAction_batch_continue(),
				sub_processes.getActivate_clear(),
				sub_processes.getAction_custom_clear(),
				sub_processes.getAction_batch_clear(),
				sub_processes.getActivate_reference_only(),
				sub_processes.getActivate_hierarchical_reference(),
				sub_processes.getActivate_reference_modify(),
				sub_processes.getActivate_reference_continue(),
				sub_processes.getActivate_register_processing(),
				sub_processes.getActivate_document_reference(),
				sub_processes.getAction_batch_dreference(),
				sub_processes.getSubdatacolumn1(),
				sub_processes.getFiltersub1(),
				sub_processes.getSubdatacolumn2(),
				sub_processes.getFiltersub2(),
				sub_processes.getSubdatacolumn3(),
				sub_processes.getFiltersub3(),
				sub_processes.getSubdatacolumn4(),
				sub_processes.getFiltersub4(),
				sub_processes.getSubdatacolumn5(),
				sub_processes.getFiltersub5(),
				sub_processes.getBatch_code_generation(),
				sub_processes.getPrefix(),
				
				sub_processes.getEndaction_list(),
				sub_processes.getActivate_endaction(),
				sub_processes.getInput_db1(),
				sub_processes.getInput_db2(),
				sub_processes.getInput_db3(),
				sub_processes.getInput_db4(),
				sub_processes.getInput_db5(),
				sub_processes.getInput_db6(),
				sub_processes.getInput_db7(),
				sub_processes.getAction_type(),
				
				sub_processes.getAccepting(),
				sub_processes.getProhibited1(),
				sub_processes.getProhibited2(),
				sub_processes.getVisibility(),
	
		};
		
		
	
		
		System.out.println(" sub_processes setup .... " );

		
		
		if(sub_processes.getId() > 0 ) {
			String sql ="UPDATE sub_processes SET step=?,description=?,processe_id=?,maindatacolumn=?,filtermain=?,subdatacolumn=?"
					+ ",filtersub=?,autoiddatacolumn=?,code_app=?,activate_autoid=?,activate_subdata=?"
					+ "	,activate_checking_list=?, checking_list=? , activate_editlist=? , edit_list=?,activate_autorid=?,code_rid=?"
					+ ",action=?,action_batch_insert=?,action_batch_modify=?,action_batch_continue=?"
					+ ",activate_clear=?,action_custom_clear=?"
					+ ",action_batch_clear=?,activate_reference_only=?,activate_hierarchical_reference=?,activate_reference_modify=?,activate_reference_continue=?"
					+ ",activate_register_processing=?,activate_document_reference=?,action_batch_dreference=?"
					+ ",subdatacolumn1=?,filtersub1=?,subdatacolumn2=?,filtersub2=?,subdatacolumn3=?,filtersub3=?,subdatacolumn4=?,filtersub4=?,subdatacolumn5=?,filtersub5=?"
					+ ",batch_code_generation=?,prefix=?,endaction_list=?,activate_endaction=?,input_db1=?,input_db2=?,input_db3=?,input_db4=?,input_db5=?"
					+ ",input_db6=?,input_db7=?,action_type=?,accepting=?,prohibited1=?,prohibited2=?,visibility=? "
					+ " WHERE id ="+sub_processes.getId();
			jdbcTemplate.update(sql,processInfo);
		}else {
			String sql ="INSERT INTO sub_processes (step,description,processe_id,maindatacolumn,filtermain,subdatacolumn,filtersub,autoiddatacolumn,code_app"
					+ ",activate_autoid,activate_subdata,activate_checking_list,checking_list,activate_editlist,edit_list"
					+ ",activate_autorid,code_rid,action,action_batch_insert,action_batch_modify,action_batch_continue,activate_clear,action_custom_clear,action_batch_clear"
					+ ",activate_reference_only,activate_hierarchical_reference,activate_reference_modify,activate_reference_continue,activate_register_processing"
					+ ",activate_document_reference,action_batch_dreference"
					+ ",subdatacolumn1,filtersub1,subdatacolumn2,filtersub2,subdatacolumn3,filtersub3,subdatacolumn4,filtersub4,subdatacolumn5,filtersub5"
					+ ",batch_code_generation,prefix,endaction_list,activate_endaction,input_db1,input_db2,input_db3,input_db4,input_db5,input_db6,input_db7,action_type"
					+ ",accepting,prohibited1,prohibited2,visibility) "
					+ "VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			jdbcTemplate.update(sql,processInfo);
		}


		
	}



	@Override
	public List<sub_processes> getAllSubProcess(int id_refer) {
		String sql ="SELECT * FROM   sub_processes WHERE processe_id =? ORDER BY id ASC  ";
		List<sub_processes> sub_processes = jdbcTemplate.query(sql,new Object[]{id_refer},new sub_processesmapper());
		return sub_processes;
	}
	
	@Override
	public void getIdoc_mark(int id,int alert) {
		String sql ="UPDATE  idoc SET alert = ?  WHERE id=? ";
		jdbcTemplate.update(sql,new Object[]{alert,id});

	}

	
	
	
	@Override
	public void SetProcessVisibility(int processe_id,String visibility){
		
		if(visibility != null) {
			if(visibility.equals("-")) {return;}
		}
		
		String sql ="UPDATE  process SET visibility = ?  WHERE id=? ";
		jdbcTemplate.update(sql,new Object[]{visibility,processe_id});

	}

	


	@Override
	public sub_processes getSubProcess(int id) {
		try {
			String sql ="SELECT * FROM  sub_processes WHERE id =? ";
			sub_processes sub_processes = jdbcTemplate.queryForObject(sql,new Object[]{id},new sub_processesmapper());
			return sub_processes;
			
			}catch(EmptyResultDataAccessException e) {
			 return null;
		  }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getPo with the same Id .......");
		   
		  }
	}

	
	@Override
	public sub_processes getSubProcess_Action(int id) {
		try {
			String sql ="SELECT * FROM  sub_processes WHERE processe_id =? AND activate_action IS NOT NULL ";
			sub_processes sub_processes = jdbcTemplate.queryForObject(sql,new Object[]{id},new sub_processesmapper());
			return sub_processes;
			
			}catch(EmptyResultDataAccessException e) {
			 return null;
		  }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getPo with the same Id .......");
		   
		  }
	}

	
	

	@Override
	public sub_processes GetNextFlow(int id) {
		try {
			String sql ="SELECT * FROM  sub_processes WHERE id > ? and step != 'Approval' limit 1  ";
			sub_processes sub_processes = jdbcTemplate.queryForObject(sql,new Object[]{id},new sub_processesmapper());
			return sub_processes;
			
			}catch(EmptyResultDataAccessException e) {
			 return null;
		  }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one getPo with the same Id .......");
		   
		  }
	}

	
	

	@Override
	public void remove_subprocess(int id) {
		
		String sql ="DELETE  FROM   sub_processes  WHERE id =? ";
		jdbcTemplate.update(sql,new Object[]{id});

		String sql2 ="DELETE FROM  keymap   WHERE sub_processes_id =? ";
		jdbcTemplate.update(sql2,new Object[]{id});

	}



	@Override
	public void remove_subprocess_all(int id_refer) {
		String sql ="DELETE  FROM   sub_processes  WHERE processe_id =? ";
		jdbcTemplate.update(sql,new Object[]{ id_refer});
		
	}


	
	@Override
	public Myapp getMyapp(String app_name) {
		try {
			
			String sql ="SELECT * FROM  myapp  WHERE appname  =? ";
			Myapp myapp = jdbcTemplate.queryForObject(sql,new Object[]{app_name},new myappmapper());
			return myapp;
			
			
			}catch(EmptyResultDataAccessException e) {
			 return null;
		  }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one Myapp with the same Id .......");
		   
		  }
	}

	@Override
	public List<registerkey> getAllRegisterkey(int app_id,String type) {

			String sql ="SELECT * FROM  registerkey  WHERE app_id  =? AND action_type =? ";
			List<registerkey> key = jdbcTemplate.query(sql,new Object[]{app_id,type},new registerkeymapper());
			return key;
	}
	
	
	@Override
	public registerkey getRegisterkey(String id) {

	
			   try {
					String sql ="SELECT * FROM  registerkey  WHERE id=? ";
					registerkey data = jdbcTemplate.queryForObject(sql,new Object[]{id},new registerkeymapper());
					return data;
			    } catch (Exception e) {
			    	return null;
			    }
	}
	@Override
	public List<registerkey> getRegisterkeyByDoc(String doc) {

					String sql ="SELECT * FROM  registerkey  WHERE doc=? ";
					List<registerkey> list = jdbcTemplate.query(sql,new Object[]{doc},new registerkeymapper());
					return list;
		   
	}
	
	
	@Override
	public List<registerkey> getRegisterkeyByListview(String Listview) {

					String sql ="SELECT * FROM  registerkey  WHERE find_in_set(id,?) ORDER BY FIND_IN_SET(id, ?) ";
					List<registerkey> list = jdbcTemplate.query(sql,new Object[]{Listview,Listview},new registerkeymapper());
					return list;
		   
	}
	
	@Override
	public List<edata> getAllByExtension(int processes_id,int id_refer,int extension_id) {

					String sql ="SELECT edata.*,idoc.*  FROM   edata       LEFT JOIN idoc "
							+ "		            ON edata.data_id = idoc.eform_id "
							+ "WHERE edata.process_id=? AND edata.id_refer=? AND edata.extension_id = ?  ORDER BY edata.data_id DESC limit 200 ";
					List<edata> list = jdbcTemplate.query(sql,new Object[]{processes_id,id_refer,extension_id},new edatamappers());
					return list;
		   
	}
	
	@Override
	public List<edata> getAllByExtension(Platform platform) {

	    List<Integer> allIds = platform.getWithinId();
	    int extension_id = platform.getExtension_process();
	    int process_id = platform.getRelation_id();

	    if (allIds == null || allIds.isEmpty()) {
	        return new ArrayList<>();
	    }

	    // ⚡ เทคนิค 1: ยิงทีเดียว ไม่ต้องวน Loop (ลด Roundtrip จาก 8 เหลือ 1)
	    // Database ส่วนใหญ่รับ Parameter ได้เป็นหมื่นตัวสบายๆ ครับ
	    
	    // ⚡ เทคนิค 2: ระบุชื่อ Column แทน * (ถ้าทำได้)
	    // การใช้ * ถ้าตารางมี column เยอะ หรือมี text ยาวๆ มันจะเสียเวลาโหลดข้อมูลขยะมาด้วย
	    String sql = " SELECT id_refer, extension_id, process_id"
	    		+ ",data_value1, data_value2, data_value3, data_value4, data_value5"
	    		+ ",data_value6, data_value7, data_value8, data_value9, data_value10"
	    		+ ",data_value11, data_value12, data_value13, data_value14, data_value15"
	    		+ ",data_value16, data_value17, data_value18, data_value19, data_value20"
	    		+ ",data_value21, data_value22, data_value23, data_value24, data_value25"
	    		+ ",data_value26, data_value27, data_value28, data_value29, data_value30  FROM edata " +
	                 " WHERE id_refer IN (:ids) " + 
	                 " AND ( extension_id = :ext_id OR process_id = :pro_id ) ";
	    
	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("ids", allIds); // ส่งไปทั้งก้อน 8,000 ตัวเลย
	    params.addValue("ext_id", extension_id); 
	    params.addValue("pro_id", process_id); 

	    List<edata> finalResult = namedJdbcTemplate.query(sql, params, new edatamappers());
	    
	    System.out.println(" finalResult "+finalResult.size());
	    return finalResult;
	}
			
	
	@Override
	public List<edata> getAllByExtensionNper(Platform platform) {

	    List<Integer> allIds = platform.getWithinId();
	    int extension_id = platform.getExtension_process();
	    int process_id = platform.getRelation_id();

	    if (allIds == null || allIds.isEmpty()) {
	        return new ArrayList<>();
	    }

	    // ⚡ ท่าแก้ขัดสำหรับ MariaDB/MySQL เวอร์ชั่นเก่า (แจกบัตรคิวด้วยตัวแปร @)
	    String sql = "SELECT * FROM (" +
	            "  SELECT t.* " +
	            "  , @row_num := IF(@current_id = t.id_refer, @row_num + 1, 1) AS row_num " +
	            "  , @current_id := t.id_refer " +
	            "  FROM (" +
	            "    SELECT id_refer, extension_id, process_id " +
	            "    , data_value1, data_value2, data_value3, data_value4, data_value5 " +
	            "    , data_value6, data_value7, data_value8, data_value9, data_value10 " +
	            "    , data_value11, data_value12, data_value13, data_value14, data_value15 " +
	            "    , data_value16, data_value17, data_value18, data_value19, data_value20 " +
	            "    , data_value21, data_value22, data_value23, data_value24, data_value25 " +
	            "    , data_value26, data_value27, data_value28, data_value29, data_value30 " +
	            "    FROM edata " +
	            "    WHERE id_refer IN (:ids) " +
	            "    AND ( extension_id = :ext_id OR process_id = :pro_id ) " +
	            "    ORDER BY id_refer DESC " + // 🎯 ต้องเรียง id_refer ให้อยู่ติดกันก่อน เพื่อให้ตัวแปร @ นับเลขได้ถูกต้อง
	            "  ) t " +
	            "  CROSS JOIN (SELECT @row_num := 0, @current_id := NULL) AS vars " + 
	            ") ranked_data " +
	            "WHERE row_num <= 15"; // 🎯 เอาแค่ 5 แถวแรกของแต่ละ id_refer

	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("ids", allIds); 
	    params.addValue("ext_id", extension_id); 
	    params.addValue("pro_id", process_id); 

	    // ยิง Query แค่รอบเดียวพอครับ (ของเดิมมีก๊อปซ้อนกันมา 2 รอบ)
	    List<edata> finalResult = namedJdbcTemplate.query(sql, params, new edatamappers());
	    
	    System.out.println(" Total sample rows (up to 5 per ID): " + finalResult.size());
	    return finalResult;
	}
	
	@Override
	public List<registers> getAllRegister(String type) {

		
		    System.out.println(" type "+type );
		
			String sql ="SELECT * FROM  register  WHERE FIND_IN_SET(register_type,?) limit 20 ";
			List<registers> registers = jdbcTemplate.query(sql,new Object[]{type},new registersmapper());
			return registers;

	}
	
	@Override
	public List<registers> getAllOldSys( ){
			String sql ="SELECT * FROM  register  WHERE id_oldsys IS NOT NULL ";
			List<registers> registers = jdbcTemplate.query(sql,new registersmapper());
			return registers;
	}
	
	@Override
	public List<product_master> getAllProdOldSys( ){
			String sql ="SELECT * FROM  product_master  ";
			List<product_master> registers = jdbcTemplate.query(sql,new product_mastermappers());
			return registers;
	}
	
	@Override
	public registers getProfileByCode(String Code) {

	    try {
	    	String sql ="SELECT * FROM  register  WHERE concat(register_code,register_daterun,'-',register_id) = ? limit 1 ";
			registers registers = jdbcTemplate.queryForObject(sql,new Object[]{Code},new registersmapper());
			return registers;
	    } catch (Exception e) {
	    	return null;
	    }
	}
	@Override
	public product_master getProduct_masterCode(String Code) {

	    try {
	    	String sql ="SELECT * FROM  product_master  WHERE concat(product_code,product_daterun,'-',product_id) = ? limit 1 ";
	    	product_master data = jdbcTemplate.queryForObject(sql,new Object[]{Code},new product_mastermappers());
			return data;
	    } catch (Exception e) {
	    	return null;
	    }
	}
	
	
	@Override
	public List<registers> getAllRegister(String type,int limit) {

		
		    System.out.println(" type "+type );
		
			String sql ="SELECT * FROM  register  WHERE FIND_IN_SET(register_type,?) limit ? ";
			List<registers> registers = jdbcTemplate.query(sql,new Object[]{type,limit},new registersmapper());
			return registers;

	}
	
	
	
	@Override
	public Integer getMovementCount(String regId, String processId, String statusArray, int cycleDuration) {
	    // เพิ่ม INNER JOIN ไปที่ sales_followup เพื่อหา customer_code
	    String sqlCount = "SELECT COUNT(DISTINCT edata.data_id) FROM edata "
	                    + "INNER JOIN sales_followup sf ON edata.data_id = sf.edata_id "
	                    + "LEFT JOIN idoc ON edata.data_id = idoc.eform_id "
	                    + "WHERE sf.customer_code = ? "
	                    + "AND edata.process_id = ? "
	                    + "AND FIND_IN_SET(idoc.closing_status, ?) > 0 "
	                    + "AND idoc.date >= DATE_SUB(CURDATE(), INTERVAL ? YEAR)";
	    
	    // ใช้ jdbcTemplate Query โดยส่ง Parameter ตามลำดับ
	    Integer count = jdbcTemplate.queryForObject(sqlCount, Integer.class, 
	            regId, processId, statusArray, cycleDuration);
	            
	    return count == null ? 0 : count; // ป้องกันค่า Null
	}
	

	@Override
    public String GradeCheckExt(String extColumnName, int regId) {
        try {
            // หมายเหตุ: ชื่อคอลัมน์ไม่สามารถใช้ ? ได้ ต้องต่อ String ถูกต้องแล้วครับ
            String checkExtSql = "SELECT " + extColumnName + " FROM register WHERE id = ?";
            return jdbcTemplate.queryForObject(checkExtSql, String.class, regId);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            return null; // ถ้า Query แล้วไม่เจอข้อมูลเลย ให้ส่ง null กลับไป
        }
    }
	
	@Override
	public boolean hasEverClosedSale(String regId, String processId, String statusArray) {
	    String sqlCount = "SELECT COUNT(DISTINCT edata.data_id) FROM edata "
	                    + "INNER JOIN sales_followup sf ON edata.data_id = sf.edata_id "
	                    + "LEFT JOIN idoc ON edata.data_id = idoc.eform_id "
	                    + "WHERE sf.customer_code = ? "
	                    + "AND edata.process_id = ? "
	                    + "AND FIND_IN_SET(idoc.closing_status, ?) > 0 ";
	    
	    Integer count = jdbcTemplate.queryForObject(sqlCount, Integer.class, 
	            regId, processId, statusArray);
	            
	    return count != null && count > 0;
	}

	@Override
	public boolean hasExtension8(Integer regId) {
	    // ใช้ String.valueOf(regId) เผื่อกรณีที่ id_refer ใน Database เก็บเป็น Varchar (String)
	    String sql = "SELECT COUNT(data_id) FROM edata WHERE extension_id = 8 AND id_refer = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, String.valueOf(regId));
	    
	    return count != null && count > 0; // ถ้ามากกว่า 0 แปลว่า "มี"
	}
	
	@Override
	public void updateRegGradeAndPointBatch(List<Object[]> batchArgs) {
	    // อัปเดตทั้งเกรดและคะแนนในคำสั่งเดียว
	    String sql = "UPDATE register SET register_grade = ?, register_poin = ? WHERE id = ?";
	    jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	

	@Override
    public void updateRegGrade(String newGrade, int regId) {
        String updateRegSql = "UPDATE register SET register_grade = ? WHERE id = ?";
        jdbcTemplate.update(updateRegSql, newGrade, regId);
    }
	
	@Override
    public void  renewfile(String name_file, int id){
        String updateRegSql = "UPDATE edata SET data_title = ? WHERE data_id = ?";
        jdbcTemplate.update(updateRegSql, name_file, id);
    }

	
	@Override
	public List<registers> getAllRegisterByType(String type, int limit, int offset) {

		System.out.println(" type: " + type + " | limit: " + limit + " | offset: " + offset);
		
		// 1. เพิ่มคำสั่ง OFFSET ต่อท้าย LIMIT
		String sql = "SELECT * FROM register WHERE FIND_IN_SET(register_type, ?) AND register_status ='ACTIVE' ORDER BY id ASC LIMIT ? OFFSET ?";
		
		// 2. เพิ่มตัวแปร offset เข้าไปใน Object Array ให้ตรงกับเครื่องหมาย ? ตัวที่ 3
		List<registers> registers = jdbcTemplate.query(sql, new Object[]{type, limit, offset}, new registersmapper());
		
		return registers;

	}
	
		@Override
		public FullResult searchRegistersSegmentation(Integer[] smid, int limit) {
		    FullResult result = new FullResult();
		    
		    
		    if(limit == 0) {limit = 200;}
		    
		    // สร้างเครื่องหมายคำถามตามจำนวน ID: ?,?,?
		    String placeholders = String.join(",", Collections.nCopies(smid.length, "?"));
		    
		    // 1. ดึงข้อมูล
		    String sql = "SELECT * FROM register WHERE id IN (" + placeholders + ") LIMIT ?";
		    
		    // รวมพารามิเตอร์: [id1, id2, ..., id7, limit]
		    Object[] paramsWithLimit = new Object[smid.length + 1];
		    System.arraycopy(smid, 0, paramsWithLimit, 0, smid.length);
		    paramsWithLimit[smid.length] = limit;
		    
		    List<registers> registers = jdbcTemplate.query(sql, paramsWithLimit, new registersmapper());
	
		    // 2. นับจำนวน (ไม่ต้องใส่ limit)
		    String sqlCount = "SELECT COUNT(*) FROM register WHERE id IN (" + placeholders + ")";
		    Integer countRegister = jdbcTemplate.queryForObject(sqlCount, smid, Integer.class);
	

		    
		    result.setTotalCount(countRegister != null ? countRegister : 0);
		    result.setRegisters(registers);
		    return result;
		}
		
	
	@Override
	public FullResult searchRegisters(String type, Platform platforms, int limit, int random,int limithead) {

	
		
		
	    FullResult result = new FullResult();

	    /* =========================
	     * 1. ORDER BY (ไม่มีคำว่า ORDER BY)
	     * ========================= */
	    String orderColumns = (random > 0)
	            ? "RAND()"
	            : "updated_at DESC, created_at DESC";

	    /* =========================
	     * 2. NOT IN ids (กัน list ว่าง)
	     * ========================= */
	    List<Integer> ids = platforms.getNotWithinId();
	    if (ids == null || ids.isEmpty()) {
	        ids = Collections.singletonList(0);
	    }

	    /* =========================
	     * 3. Search text
	     * ========================= */
	    String searchText = platforms.getSearch();
	    String search = (searchText != null) ? searchText.trim() : "";
	    boolean hasSearch = !search.isEmpty();

	    String likeSearch  = "%" + search.replace(" ", "%") + "%";
	    String startSearch = search + "%";

	    /* =========================
	     * 4. NamedParameter
	     * ========================= */
	    NamedParameterJdbcTemplate namedTemplate =
	            new NamedParameterJdbcTemplate(jdbcTemplate);

	    MapSqlParameterSource params = new MapSqlParameterSource();
	    params.addValue("type", type);
	    params.addValue("ids", ids);
	    params.addValue("search", search);
	    params.addValue("likeSearch", likeSearch);
	    params.addValue("startSearch", startSearch);
	    params.addValue("limit", limit);

	    /* =========================
	     * 5. WHERE clause
	     * ========================= */
	    StringBuilder whereSql = new StringBuilder();
	    whereSql.append(" WHERE FIND_IN_SET(register_type, :type) ");
	    whereSql.append(" AND id NOT IN (:ids) ");

	    if (hasSearch) {
	    	whereSql.append(" AND ( ");
	        whereSql.append("   CONCAT(register_code, register_daterun, '-', register_id) = :search ");
	        whereSql.append("   OR register_groups = :search ");
	        whereSql.append("   OR register_id = :search ");
	        whereSql.append("   OR register_name LIKE :likeSearch ");
	        whereSql.append("   OR register_address LIKE :likeSearch ");
	        whereSql.append("   OR contact_person LIKE :likeSearch ");
	        whereSql.append("   OR phone_number LIKE :likeSearch ");
	        whereSql.append("   OR mobile_number LIKE :likeSearch ");
	        whereSql.append("   OR tax_id LIKE :likeSearch ");
	        whereSql.append(" ) ");
	    }

	    /* =========================
	     * 6. COUNT
	     * ========================= */
	    String countSql =
	            "SELECT COUNT(1) FROM register " + whereSql;

	    int totalCount = namedTemplate.queryForObject(
	            countSql, params, Integer.class
	    );

	    /* =========================
	     * 7. DATA query
	     * ========================= */
	    
	    String query ="*";
	    
	    if(limithead == 1) {
	    	query ="register_code,register_daterun,register_id,register_name";
	    }

	    
	    StringBuilder dataSql = new StringBuilder();
	    dataSql.append("SELECT "+query+ " FROM register ");
	    dataSql.append(whereSql);

	    if (hasSearch) {
	        dataSql.append(" ORDER BY CASE ");
	        dataSql.append("   WHEN register_id = :search THEN 1 ");
	        dataSql.append("   WHEN register_groups = :search THEN 2 ");
	        dataSql.append("   WHEN register_name LIKE :startSearch THEN 3 ");
	        dataSql.append("   ELSE 4 ");
	        dataSql.append(" END, ");
	    } else {
	        dataSql.append(" ORDER BY ");
	    }

	    dataSql.append(orderColumns);
	    dataSql.append(" LIMIT :limit");

	    List<registers> list =
	            namedTemplate.query(dataSql.toString(), params, new registersmapper());

	    /* =========================
	     * 8. Result
	     * ========================= */
	    result.setTotalCount(totalCount);
	    result.setRegisters(list);

	    return result;
	}

	@Override
	public boolean hasClosedSaleInCurrentQuarter(String regId, String processId, String statusArray) {
	    String sqlCount = "SELECT COUNT(DISTINCT edata.data_id) FROM edata "
	                    + "INNER JOIN sales_followup sf ON edata.data_id = sf.edata_id "
	                    + "LEFT JOIN idoc ON edata.data_id = idoc.eform_id "
	                    + "WHERE sf.customer_code = ? "
	                    + "AND edata.process_id = ? "
	                    + "AND FIND_IN_SET(idoc.closing_status, ?) > 0 "
	                    + "AND QUARTER(idoc.date) = QUARTER(CURDATE()) " // 🌟 เช็คว่าตรงกับไตรมาสปัจจุบันไหม
	                    + "AND YEAR(idoc.date) < YEAR(CURDATE())";       // 🌟 แก้ตรงนี้: เช็คเฉพาะ "ปีก่อนๆ" (ไม่ใช้ปีปัจจุบัน)
	    
	    Integer count = jdbcTemplate.queryForObject(sqlCount, Integer.class, 
	            regId, processId, statusArray);
	            
	    return count != null && count > 0;
	}
	
	
	
	
	@Override
	public FullResult groupingRegisters(String type, int limit, String grouping) {
	    FullResult result = new FullResult();

	    
		System.out.println("Test 55555");
	    
	    
	    // หมายเหตุ: ผมแก้ product_... เป็น register_... ให้ตรงกับตาราง register นะครับ
	    String sqls = "SELECT count(*) FROM register " +
	                 "WHERE FIND_IN_SET(register_type, ?) " +
	                 "AND  register_status =? ";

	   int count = jdbcTemplate.queryForObject(sqls, new Object[]{type,grouping},Integer.class);
	   result.setTotalCount(count);
	    
	    // หมายเหตุ: ผมแก้ product_... เป็น register_... ให้ตรงกับตาราง register นะครับ
	    String sql = "SELECT * FROM register " +
	                 "WHERE FIND_IN_SET(register_type, ?) " +
	                 "AND  register_status =? " +
	                 "ORDER BY updated_at DESC " +                 
	                 "LIMIT ?";

	    List<registers> list = jdbcTemplate.query(sql, new Object[]{type,grouping,limit}, new registersmapper());
	    
	    result.setRegisters(list);
	    return result;
	}
	@Override
	public FullResult  groupingProduct(String type, int limit, String grouping) {
	    FullResult result = new FullResult();

	    // หมายเหตุ: ผมแก้ product_... เป็น register_... ให้ตรงกับตาราง register นะครับ
	    String sqls = "SELECT count(*) FROM product_master " +
	                 "WHERE FIND_IN_SET(product_type, ?) " +
	                 "AND  product_status =? ";

	   int count = jdbcTemplate.queryForObject(sqls, new Object[]{type,grouping},Integer.class);
	   result.setTotalCount(count);
	    
	    
	    // หมายเหตุ: ผมแก้ product_... เป็น register_... ให้ตรงกับตาราง register นะครับ
	    String sql = "SELECT * FROM product_master " +
	                 "WHERE FIND_IN_SET(product_type, ?) " +
	                 "AND product_status = ? " +
	                 "ORDER BY updated_at DESC " +    
	                 "LIMIT ?";

	    List<product_master> list = jdbcTemplate.query(sql, new Object[]{type, limit}, new product_mastermappers());
	    
	    result.setProduct_master(list);
	    return result;
	}
	
	
	
	@Override
	public FullResult auditRegisters(String type, int limit) {
	    FullResult result = new FullResult();

	    
	    
	    
	    // หมายเหตุ: ผมแก้ product_... เป็น register_... ให้ตรงกับตาราง register นะครับ
	    String sql = "SELECT register.* FROM register " +
	                 "JOIN audit ON audit.audit_code = concat(register.register_code, register.register_daterun, '-', register.register_id) " +
	                 "WHERE FIND_IN_SET(register.register_type, ?) " +
	                 "AND audit.audit_type = 'Profile' " +
	                 
	                 // 1. ยุบรวมแถวซ้ำ: ให้เหลือ 1 id ต่อ 1 แถว
	                 "GROUP BY register.register_id " + 
	                 
	                 // 2. เรียงลำดับ: เอาเวลา audit ที่ 'มากที่สุด' (ล่าสุด) ของคนนั้นมาเรียง
	                 "ORDER BY MAX(audit.created_at) DESC " + 
	                 
	                 "LIMIT ?";

	    List<registers> list = jdbcTemplate.query(sql, new Object[]{type, limit}, new registersmapper());
	    
	    result.setRegisters(list);
	    return result;
	}
	
	@Override
	public FullResult helpDeskDataRegisters(String type, int limit) {
	    FullResult result = new FullResult();
	    limit = 300;

	    // 1. ดึง Ticket ที่สถานะ OPEN ทั้งหมด
	    List<HelpDeskTicket> tickets = helpDeskRepository.findByStatus("OPEN");

	    if (tickets == null || tickets.isEmpty()) {
	        result.setRegisters(new ArrayList<>());
	        return result;
	    }

	    // 2. สร้าง Map เพื่อเก็บข้อมูล (Key = ref_id, Value = ข้อความที่จะใส่ใน extension_column_20)
	    // ใช้ Map จะทำให้ตอนหาข้อมูลมาใส่ Register ทำได้รวดเร็วมาก (O(1))
	    Map<Integer, String> ticketDataMap = new HashMap<>();
	    Set<Integer> customerIds = new HashSet<>();

	    for (HelpDeskTicket t : tickets) {
	        if (t.getRefId() != null && t.getRefId() > 0) {
	            customerIds.add(t.getRefId());
	            
	            // เตรียมข้อความ: "หัวข้อ: ... | รายละเอียด: ..."
	            String info = "Topic: " + t.getRequestTopic() + "\nDesc: " + t.getDescription();
	            
	            // กรณีลูกค้าคนเดียวมีหลาย Ticket:
	            // ถ้ามีอยู่แล้วให้ต่อท้าย (Append) หรือถ้ายังไม่มีให้ใส่ใหม่
	            ticketDataMap.merge(t.getRefId(), info, (oldValue, newValue) -> oldValue + "\n---\n" + newValue);
	        }
	    }

	    if (customerIds.isEmpty()) {
	        result.setRegisters(new ArrayList<>());
	        return result;
	    }

	    // 3. แปลง Set เป็น String สำหรับ SQL IN clause
	    String idListStr = customerIds.toString().replace("[", "").replace("]", "");

	    // 4. SQL Query
	    String sql = "SELECT * FROM register " +
	                 "WHERE id IN (" + idListStr + ") " +
	                 "AND FIND_IN_SET(register_type, ?) " +
	                 "ORDER BY id DESC " + 
	                 "LIMIT ?";

	    try {
	        List<registers> list = jdbcTemplate.query(sql, new Object[]{type, limit}, new registersmapper());

	        // 5. นำข้อมูลจาก Map มายัดใส่ extension_column_20
	        for (registers reg : list) {
	            // ดึงข้อมูลจาก Map ตาม ID ของ Register
	            String ticketInfo = ticketDataMap.get(reg.getId()); // สมมติว่า register id เก็บใน getId()
	            if (ticketInfo != null) {
	                reg.setExtension_column_20(ticketInfo);
	            }
	        }

	        result.setRegisters(list);
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.setRegisters(new ArrayList<>());
	    }

	    return result;
	}
	
	
	
	
	@Override
	public FullResult auditProduct(String type, int limit) {
	    FullResult result = new FullResult();

	    // หมายเหตุ: ผมแก้ product_... เป็น register_... ให้ตรงกับตาราง register นะครับ
	    String sql = "SELECT product_master.* FROM product_master " +
	                 "JOIN audit ON audit.audit_code = concat(product_master.product_code, product_master.product_daterun, '-', product_master.product_id) " +
	                 "WHERE FIND_IN_SET(product_master.product_type, ?) " +
	                 "AND audit.audit_type = 'Product' " +
	                 
	                 // 1. ยุบรวมแถวซ้ำ: ให้เหลือ 1 id ต่อ 1 แถว
	                 "GROUP BY product_master.product_id " + 
	                 
	                 // 2. เรียงลำดับ: เอาเวลา audit ที่ 'มากที่สุด' (ล่าสุด) ของคนนั้นมาเรียง
	                 "ORDER BY MAX(audit.created_at) DESC " + 
	                 
	                 "LIMIT ?";

	    List<product_master> list = jdbcTemplate.query(sql, new Object[]{type, limit}, new product_mastermappers());
	    
	    result.setProduct_master(list);
	    return result;
	}
	
	
	@Override
	public  void SaveAllExtension(List<edata> edataList, int extensionId, int processesId) {
	    
	    // 1. เตรียมคำสั่ง SQL
	    String sql = "INSERT INTO edata " // เปลี่ยนชื่อตารางเป็นของคุณ
	               + "(extension_id, process_id, id_refer, reference, reference_oldsys, data_dateadd, data_nameadd, "
	               + "data_value1, data_value2, data_value3, data_value4, data_value5, "
	               + "data_value6, data_value7, data_value8, data_value9, data_value10, "
	               + "data_value11, data_value12, data_value13, data_value14, data_value15, "
	               + "data_value16, data_value17, data_value18, data_value19, data_value20) "
	               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    // 2. กำหนดขนาดของแต่ละรอบ (หั่นส่งทีละ 1,000 แถว ป้องกันแรมเต็ม)
	    int batchSize = 1000;

	    // 3. ใช้ batchUpdate แบบ Parameterized (รองรับข้อมูลหลักแสนได้อย่างเสถียร)
	    jdbcTemplate.batchUpdate(sql, edataList, batchSize,
	        new ParameterizedPreparedStatementSetter<edata>() {
	            @Override
	            public void setValues(PreparedStatement ps, edata e) throws SQLException {
	                ps.setInt(1, extensionId);
	                ps.setInt(2, processesId);
	                ps.setInt(3, e.getId_refer()); 
	                ps.setString(4, e.getReference());
	                ps.setString(5, e.getReference_oldsys());
	                ps.setString(6, e.getData_dateadd());
	                ps.setString(7, e.getData_nameadds());
	                ps.setString(8, e.getData_value1());
	                ps.setString(9, e.getData_value2());
	                ps.setString(10, e.getData_value3());
	                ps.setString(11, e.getData_value4());
	                ps.setString(12, e.getData_value5());
	                ps.setString(13, e.getData_value6());
	                ps.setString(14, e.getData_value7());
	                ps.setString(15, e.getData_value8());
	                ps.setString(16, e.getData_value9());
	                ps.setString(17, e.getData_value10());
	                ps.setString(18, e.getData_value11());
	                ps.setString(19, e.getData_value12());
	                ps.setString(20, e.getData_value13());
	                ps.setString(21, e.getData_value14());
	                ps.setString(22, e.getData_value15());
	                ps.setString(23, e.getData_value16());
	                ps.setString(24, e.getData_value17());
	                ps.setString(25, e.getData_value18());
	                ps.setString(26, e.getData_value19());
	                ps.setString(27, e.getData_value20());
	            }
	        });
	        
	    System.out.println("✅ Batch Insert " + edataList.size() + " rows completed via JdbcTemplate!");
	}
	
	
	
	@Override
	public FullResult getAllProducts(String type, String searchText, int limit,int limithead) {
	    
		FullResult result = new FullResult();
	    
 	    String query ="*";
	    
	    if(limithead == 1) {
	    	query ="product_code,product_daterun,product_id,product_names";
	    }

		
	    // --- กรณีไม่มีคำค้นหา (Load แรก หรือ Reset) ---
	    if (searchText == null) {
	         String whereSql = "SELECT count(1) FROM product_master WHERE FIND_IN_SET(product_type,?) ";
	         int totalCount = jdbcTemplate.queryForObject(whereSql, new Object[]{type}, Integer.class);

	         String sql = "SELECT "+query+" FROM product_master WHERE FIND_IN_SET(product_type,?) ORDER BY updated_at,created_at DESC  LIMIT ?";
	         List<product_master> list = jdbcTemplate.query(sql, new Object[]{type, limit}, new product_mastermappers()); // อย่าลืมเปลี่ยน Mapper
	         
	         result.setTotalCount(totalCount);
	         result.setProduct_master(list);
	         return result;
	    }
	    
	    // --- 1. เตรียม Variables ---
	    String search = (searchText != null) ? searchText.trim() : "";
	    String likeSearch = "%" + search.replace(" ", "%") + "%";
	    String startSearch = search.replace(" ", "") + "%";

	    
	    
	    
	    // --- 2. แยกชิ้นส่วน SQL (Where Clause) ---
	    String whereSql = "WHERE FIND_IN_SET(product_type, ?) " +
	                      "AND ( " +
	                      "    concat(product_code, product_daterun, '-', product_id) = ? OR " + // code เต็ม
	                      "    product_category = ? OR " +
	                      "    product_id = ? OR " +
	                      "    product_type = ? OR " +
	                      "    product_names LIKE ? OR " +
	                      "    product_code LIKE ? OR " +     // เพิ่มค้นหาด้วย code แบบบางส่วน
	                      "    product_details LIKE ? " +     // ค้นหาในรายละเอียด
	                      ") ";

	    // --- STEP A: หาจำนวนทั้งหมด (Count Query) ---
	    String countSql = "SELECT count(1) FROM product_master " + whereSql;

	    Object[] countParams = new Object[] {
	        type,           // 1. FIND_IN_SET
	        
	        search,         // 2. concat id (code full)
	        search,         // 3. category
	        search,         // 4. id
	        search,         // 5. type
	        
	        likeSearch,     // 6. names (LIKE)
	        likeSearch,     // 7. code (LIKE)
	        likeSearch      // 8. details (LIKE)
	    };

	    // ยิง Query แรกเพื่อเอาจำนวน
	    int totalCount = jdbcTemplate.queryForObject(countSql, countParams, Integer.class);

	    
	    
	    
	    // --- STEP B: หาข้อมูลจริง (Data Query) ---
	    String dataSql = "SELECT "+query+" FROM product_master " + whereSql +
	                     "ORDER BY " +
	                     "  CASE " +
	                     "    WHEN product_id = ? THEN 1 " +
	                     "    WHEN product_type = ? THEN 2 " +
	                     "    WHEN product_category = ? THEN 3 " +
	                     "    WHEN concat(product_code, product_daterun, '-', product_id) = ? THEN 4 " +
	                     "    WHEN product_names LIKE ? THEN 5 " + // ชื่อขึ้นต้นด้วยคำค้น
	                     "    ELSE 6 " +
	                     "  END, " +
	                     "  product_id DESC " +
	                     "LIMIT ?";

	    Object[] dataParams = new Object[] {
	        // --- ชุด WHERE (ก๊อปปี้จาก countParams มาเลย) ---
	        type, 
	        search, 
	        search, 
	        search, 
	        search, 
	        likeSearch, 
	        likeSearch, 
	        likeSearch,
	        
	        // --- ชุด ORDER BY ---
	        search,         // id
	        search,         // type
	        search,         // category
	        search,         // code full
	        startSearch,    // name start with...
	        
	        // --- LIMIT ---
	        limit
	    };

	    // ยิง Query ที่สองเพื่อเอาข้อมูล
	    // ** อย่าลืมแก้ตรง new product_master_mapper() ให้เป็น Mapper ของ Product **
	    List<product_master> list = jdbcTemplate.query(dataSql, dataParams, new product_mastermappers());
	    
	    result.setTotalCount(totalCount);
	    result.setProduct_master(list);
	    
	    // 3. ส่งกลับ
	    return result;
	}
	
	
	@Override
	public registers getRegister(int id) {

		System.out.println(" Register id "+ id);
		
		    try {
				String sql ="SELECT * FROM  register  WHERE id =? ";
				registers registers = jdbcTemplate.queryForObject(sql,new Object[]{id},new registersmapper());
				return registers;
		    } catch (Exception e) {
		    	return null;
		    }

			
	}
	
	@Override
	public void  setEx20(String date,int id) {
				String sql ="update  register SET  extension_column_20 = ?  WHERE id =? ";
				jdbcTemplate.update(sql,new Object[]{date,id});
	}
	
	
	
	
	
	@Override
	public List<product_master> getAllProduct(String type) {

			String sql ="SELECT * FROM  product_master  WHERE FIND_IN_SET(product_category,?) limit 20 ";
			List<product_master> registers = jdbcTemplate.query(sql,new Object[]{type},new product_mastermappers());
			return registers;
	}
	
	@Override
	public List<product_master> getAllProduct(int limit) {

			String sql ="SELECT * FROM  product_master  ORDER BY id DESC  limit ? ";
			List<product_master> registers = jdbcTemplate.query(sql,new Object[]{limit},new product_mastermappers());
			return registers;
	}
	
	
	@Override
	public List<product_master> getAllProduct(String type,int limit) {

		  System.out.println("product_type  "+type);
			String sql ="SELECT * FROM  product_master  WHERE FIND_IN_SET(product_type,?) limit ? ";
			List<product_master> registers = jdbcTemplate.query(sql,new Object[]{type,limit},new product_mastermappers());
			
			  System.out.println("registers  "+registers.size());
			  
			return registers;
			
		
	}
	@Override
	public List<bom> getAllBom() {

			String sql ="SELECT * FROM  bom  WHERE status='Approved' ";
			List<bom> boms = jdbcTemplate.query(sql,new bommappers());
			return boms;
	}
	
	@Override
	public List<bom> getAllBom(int limit) {

			String sql ="SELECT * FROM  bom  WHERE status='Approved' limit ?  ";
			List<bom> boms = jdbcTemplate.query(sql,new Object[]{limit},new bommappers());
			return boms;
	}
	@Override
	public List<production_standard> getAllPs(int limit) {

			String sql ="SELECT * FROM  production_standard  WHERE status='Approved' limit ? ";
			List<production_standard> ps = jdbcTemplate.query(sql,new Object[]{limit},new production_standardmappers());
			return ps;
	}
	
	@Override
	public List<production_standard> getAllPs() {

			String sql ="SELECT * FROM  production_standard  WHERE status='Approved' ";
			List<production_standard> ps = jdbcTemplate.query(sql,new production_standardmappers());
			return ps;
	}
	@Override
	public List<Fg> getAllFg(int limit) {

			String sql ="SELECT * FROM  fg   ORDER BY id DESC limit ?";
			List<Fg> data = jdbcTemplate.query(sql,new Object[]{limit},new fgmappers());
			return data;
			
	}
	@Override
	public List<Fg> getAllFg() {

			String sql ="SELECT * FROM  fg   ORDER BY id DESC limit  100 ";
			List<Fg> data = jdbcTemplate.query(sql,new fgmappers());
			return data;
			
	}
	@Override
	public List<Fg> getAllFg(String code) {

			String sql ="SELECT * FROM  fg  WHERE product_code = ?  ORDER BY id DESC limit  100 ";
			List<Fg> data = jdbcTemplate.query(sql,new Object[]{code},new fgmappers());
			return data;
			
	}
	
	@Override
	public List<production_list> getAllPL(String code) {

			String sql ="SELECT * FROM  production_list  WHERE product_code = ? AND tempId IS NULL AND order_quantity  > 0   ORDER BY id DESC limit  100 ";
			List<production_list> data = jdbcTemplate.query(sql,new Object[]{code},new production_listmappers());
			return data;
			
	}
	
	
	
	@Override
	public List<stock_lot> getAllSk(String stock_code) {

			String sql ="SELECT * FROM  stock_lot WHERE stock_code =?  ORDER BY stock_id  DESC limit  100 ";
			List<stock_lot> data = jdbcTemplate.query(sql,new Object[]{stock_code},new stock_lotmappers());
			return data;
			
	}
	@Override
	public List<stock_lot> getAllSk() {

			String sql ="SELECT * FROM  stock_lot   ORDER BY stock_id DESC limit  100000 ";
			List<stock_lot> data = jdbcTemplate.query(sql,new stock_lotmappers());
			return data;
			
	}
	
	
	@Override
	public keymap getKeyMapByType(int id,String type) {

		
		    try {
		    	String sql ="SELECT * FROM  keymap  WHERE processes_id=? AND FIND_IN_SET(mainkey,?) limit 1 ";
		    	keymap keymaps = jdbcTemplate.queryForObject(sql,new Object[]{id,type},new keymapmapper());
				return keymaps;
		    } catch (Exception e) {
		    	return null;
		    }

	}
	
	@Override
	public keymap getKeyMapById(int id) {

		
		    try {
		    	String sql ="SELECT * FROM  keymap  WHERE id=? ";
		    	keymap keymaps = jdbcTemplate.queryForObject(sql,new Object[]{id},new keymapmapper());
				return keymaps;
		    } catch (Exception e) {
		    	return null;
		    }

	}
	
	
	@Override
	public void save_keymap(keymap keymap) {
	    
	    if (keymap.getMainkey() == null || keymap.getMainkey().trim().isEmpty()) { 
	        return; 
	    }
	    
	    String[] Mainkey = keymap.getMainkey().split(",");
	    String[] Eformkey = keymap.getEformkey().split(",");
	    String[] Change_format = keymap.getChange_format().split(",");
	    String[] Enum_id = keymap.getEnum_id().split(",");
	    String[] Filter_enum = keymap.getFilter_enum().split(",");
	    String type = keymap.getType();
	    String[] registerkey_id = keymap.getRegisterkey_id().split(",");

	    // ใช้คำสั่ง ON DUPLICATE KEY UPDATE เพื่ออัปเดตข้อมูลเดิม 
	    String sql = "INSERT INTO keymap (sub_processes_id, processes_id, mainkey, eformkey, change_format, enum_id, filter_enum, type, registerkey_id) "
	            + "VALUES(?,?,?,?,?,?,?,?,?) "
	            + "ON DUPLICATE KEY UPDATE "
	            + "processes_id = VALUES(processes_id), "
	            + "eformkey = VALUES(eformkey), "
	            + "change_format = VALUES(change_format), "
	            + "enum_id = VALUES(enum_id), "
	            + "filter_enum = VALUES(filter_enum), "
	            + "type = VALUES(type), "
	            + "registerkey_id = VALUES(registerkey_id)";

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	            
	        @Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	            ps.setInt(1, keymap.getSub_processes_id());  
	            ps.setInt(2, keymap.getProcesses_id());  
	            ps.setString(3, Mainkey[i].trim()); // trim ป้องกันช่องว่างแฝง
	            
	            // ดักจับ Array Out of Bounds ให้ปลอดภัยเสมอ
	            ps.setString(4, i < Eformkey.length ? Eformkey[i].trim() : "");
	            ps.setString(5, i < Change_format.length ? Change_format[i].trim() : "");
	            ps.setString(6, i < Enum_id.length ? Enum_id[i].trim() : "");
	            ps.setString(7, i < Filter_enum.length ? Filter_enum[i].trim() : "-");
	            ps.setString(8, type);
	            ps.setString(9, i < registerkey_id.length ? registerkey_id[i].trim() : "0");
	        }

	        @Override
	        public int getBatchSize() {
	            return Mainkey.length;
	        }
	    });
	}
	
	@Override
	public void save_keymap(keymap2 keymap) {
		
		if(keymap.getMainkey2() == null ) {return; }
		
		
		String[] Mainkey = keymap.getMainkey2().split(",");
		String[] Eformkey = keymap.getEformkey2().split(",");
		String[] Change_format = keymap.getChange_format2().split(",");
		String[] Enum_id = keymap.getEnum_id2().split(",");
		String[] Filter_enum = keymap.getFilter_enum2().split(",");
		String[] type = keymap.getType2().split(",");
		String[] registerkey_id = keymap.getRegisterkey_id().split(",");

		
		 System.out.println("------keymap2--------");
		 System.out.println("Mainkey "+Mainkey.length);
		 System.out.println("Eformkey "+Eformkey.length);
		 System.out.println("Change_format "+Change_format.length);
		 System.out.println("Enum_id "+Enum_id.length);
		 System.out.println("Filter_enum "+Filter_enum.length);
		 System.out.println("Registerkey_id "+registerkey_id.length);
		 System.out.println("------keymap2--------");
		 
		 
		
		String sql ="INSERT INTO keymap (sub_processes_id,processes_id,mainkey,eformkey,change_format,enum_id,filter_enum,type,registerkey_id) VALUE(?,?,?,?,?,?,?,?,?) ";
	
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				
	            @Override
	            public void setValues(PreparedStatement ps, int i )
	                throws SQLException {
	             
	              ps.setInt(1,keymap.getSub_processes_id());  
	              ps.setInt(2,keymap.getProcesses_id());  
	              ps.setString(3,Mainkey[i]);
	              ps.setString(4,Eformkey[i]);
	              ps.setString(5,Change_format[i]);
	              ps.setString(6,Enum_id[i]);
	              ps.setString(7,Filter_enum[i].replace("|", ","));
	              ps.setString(8,type[i]);
	              ps.setString(9,registerkey_id[i]);
	            }

	            @Override
	            public int getBatchSize() {
	                return Mainkey.length;
	            }
	        });
		
	}
	
	@Override
	public List<keymap> getAllKeyMap(int sub_processes_id) {
		String sql ="SELECT * FROM   keymap WHERE sub_processes_id =? ";
		List<keymap> keymaps = jdbcTemplate.query(sql,new Object[]{sub_processes_id},new keymapmapper());
		return keymaps;
	}
	@Override
	public List<keymap> getAllKeyMaps(int processes_id) {
		String sql ="SELECT eformfield.field_label AS title, keymap.* "
				+ "FROM   keymap LEFT JOIN eformfield ON eformfield.field_id = keymap.eformkey"
				+ "   WHERE keymap.processes_id =? ";
		List<keymap> keymaps = jdbcTemplate.query(sql,new Object[]{processes_id},new keymapmapper());
		return keymaps;
	}
	
	@Override
	public void remove_keyMap_all(int sub_processes_id) {
		String sql ="DELETE  FROM   keymap  WHERE sub_processes_id =? ";
		jdbcTemplate.update(sql,new Object[]{ sub_processes_id});
		
	}
	@Override
	public void remove_keyMap_alls(int processes_id) {
		String sql ="DELETE  FROM   keymap  WHERE processes_id =? ";
		jdbcTemplate.update(sql,new Object[]{ processes_id});
		
	}
	

	

	
	@Override
	public String addDoc(int id_refer, String type) {
	    LocalDateTime localDate = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	    String date = localDate.format(formatter);

	    System.err.println("ApproveApp->addDoc "+type);
	    
	    try { 
	        // ตรวจสอบว่ามีเอกสารเดิมที่มี type และ id_refer นี้อยู่แล้วหรือไม่
	        String checkSql = "SELECT doc_autoid, doc_rv,doc_type,doc_date FROM doc WHERE doc_type = ? AND doc_idrefer = ?";
	        List<Map<String, Object>> existingDoc = jdbcTemplate.queryForList(checkSql, new Object[]{type, id_refer});
	        
	        if (!existingDoc.isEmpty()) {
	            // กรณีมีข้อมูลอยู่แล้ว - อัพเดต doc_rv โดยเพิ่มค่า +1
	            int currentRv = (Integer) existingDoc.get(0).get("doc_rv");
	            int newRv = currentRv + 1;
	            
	            String updateSql = "UPDATE doc SET doc_date = ?, doc_rv = ? WHERE doc_type = ? AND doc_idrefer = ?";
	            jdbcTemplate.update(updateSql, new Object[]{date, newRv, type, id_refer});
	            
	            return  (String) existingDoc.get(0).get("doc_type") + existingDoc.get(0).get("doc_date") + existingDoc.get(0).get("doc_autoid")+"-"+newRv;
	        } else {
	            // กรณีไม่มีข้อมูล - สร้างใหม่ตาม logic เดิม
	            // ค้นหา doc_autoid ล่าสุดของ type นั้น
	            String findSql = "SELECT MAX(doc_autoid) FROM doc WHERE doc_type = ?";
	            Integer lastAutoId = jdbcTemplate.queryForObject(findSql, new Object[]{type}, Integer.class);

	            // ถ้าไม่มีค่าให้เริ่มที่ 1
	            int newAutoId = (lastAutoId != null) ? lastAutoId + 1 : 1;

	            // ทำการ INSERT ข้อมูลใหม่
	            String insertSql = "INSERT INTO doc (doc_date, doc_idrefer, doc_autoid, doc_type, doc_rv) VALUES (?, ?, ?, ?, ?)";
	            jdbcTemplate.update(insertSql, new Object[]{date, id_refer, newAutoId, type, 0});
	            
	            return   type+date+newAutoId;
	            
	        }
	        
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	    }
		return null;

	}

	
	
	
	
	@Override
	public void revision(int edata_old, int edata_new) {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
		String date = localDate.format(formatter);

		// 1. ตรวจสอบว่ามีเอกสารเดิมที่มี type และ id_refer นี้อยู่แล้วหรือไม่
		String checkSql = "SELECT doc_autoid, doc_rv, doc_type, doc_date FROM doc WHERE doc_idrefer = ? ORDER BY doc_rv DESC limit 1";
		
		try {
			// ใช้ Try-Catch ดักไว้ เผื่อ queryForObject ไม่พบข้อมูล (ป้องกัน EmptyResultDataAccessException)
			doc oldDoc = jdbcTemplate.queryForObject(checkSql, new Object[]{edata_old}, new docmappers());
			
			if (oldDoc != null) {
				// กรณีมีข้อมูลอยู่แล้ว - อัพเดต doc_rv โดยเพิ่มค่า +1
				int currentRv = oldDoc.getDoc_rv() + 1;
				int doc_autoid = oldDoc.getDoc_autoid();
				String doc_type = oldDoc.getDoc_type();
				
				String insertSql = "INSERT INTO doc (doc_date, doc_idrefer, doc_autoid, doc_type, doc_rv) VALUES (?, ?, ?, ?, ?)";
				jdbcTemplate.update(insertSql, new Object[]{date, edata_new, doc_autoid, doc_type, currentRv});
			}
		} catch (Exception e) {
			System.out.println("No previous revision history found for edata_old: " + edata_old);
		}

		// =========================================================
		// 2. จัดการคัดลอกลิงก์ (Relations) จากเวอร์ชันเก่ามาเวอร์ชันใหม่
		// =========================================================
		
		// กรณีที่ 1: เอกสารที่ถูก Revision เป็น "เอกสารหลัก" (คัดลอกลิงก์ลูกๆ ตามมาด้วย)
		String copyMainRelationsSql = "INSERT INTO relations "
				+ "(main_doc_code, relations_doc_code, main_doc_name, relations_doc_name, main_id, relations_id, parent, tree) "
				+ "SELECT main_doc_code, relations_doc_code, main_doc_name, relations_doc_name, ?, relations_id, parent, tree "
				+ "FROM relations WHERE main_id = ?";
		
		int copiedLinks = jdbcTemplate.update(copyMainRelationsSql, edata_new, edata_old);
		System.out.println("Copied " + copiedLinks + " linked documents to the new revision.");

		/* 
		  [ทางเลือกเสริม] กรณีที่ 2: ถ้าเอกสารนี้เคยไปเป็น "เอกสารแนบ" (ลูก) ของเอกสารอื่น 
		  แล้วคุณต้องการให้เอกสารแม่ อัปเดตมาลิงก์กับเวอร์ชันใหม่(edata_new) อัตโนมัติด้วย สามารถเปิดใช้โค้ดด้านล่างนี้ได้ครับ 
		*/
		// String copyChildRelationsSql = "INSERT INTO relations "
		// 		+ "(main_doc_code, relations_doc_code, main_doc_name, relations_doc_name, main_id, relations_id, parent, tree) "
		// 		+ "SELECT main_doc_code, relations_doc_code, main_doc_name, relations_doc_name, main_id, ?, parent, tree "
		// 		+ "FROM relations WHERE relations_id = ?";
		// jdbcTemplate.update(copyChildRelationsSql, edata_new, edata_old);
	}
	
	
	
	@Override
	public List<doc> getSameDoc(String document,int id_refer) {
		
				String sql =" SELECT *  FROM doc LEFT JOIN edata  ON doc.doc_idrefer = edata.data_id "
						+ "  WHERE edata.reference =? AND edata.data_id != ? order by doc.doc_id desc   limit 10  ";
				List<doc> doc  =  jdbcTemplate.query(sql,new Object[]{document,id_refer},new docmappers());
				return doc;
	}
	
	
	@Override
	public List<doc> getDoc(int id_refer) {
		
				String sql =" SELECT * FROM doc WHERE doc_idrefer =? order by doc_id asc  ";
				List<doc> doc  =  jdbcTemplate.query(sql,new Object[]{id_refer},new docmappers());
				return doc;
	 
		 
	}
	
	@Override
	public doc findDoc(int id_refer) {

		
	     try {
				String sql =" SELECT * FROM doc WHERE doc_idrefer =? order by doc_id asc  ";
				doc doc  =  jdbcTemplate.queryForObject(sql,new Object[]{id_refer},new docmappers());
				return doc;
	        } catch (Exception e) {
	          return null;
	        }
	
		 
	}
	
	@Override
	public List<doc> getDoc(String doc_type) {
		
				String sql =" SELECT * FROM doc WHERE"
						+ " concat(doc_type,doc_date,doc_autoid)  =? "
						+ "OR concat(doc_type,doc_date,doc_autoid,doc_rv)  =?  "
						+ "order by doc_id asc  ";
				List<doc> doc  =  jdbcTemplate.query(sql,new Object[]{doc_type,doc_type},new docmappers());
				return doc;
	 
		 
	}
	
	@Override
	public List<doc> getDoc_in_set(String doc_type) {
		
				String sql =" SELECT * FROM doc WHERE"
						+ " FIND_IN_SET(concat(doc_type,doc_date,doc_autoid),?) "
						+ "OR FIND_IN_SET( concat(doc_type,doc_date,doc_autoid,doc_rv),?)  "
						+ "order by doc_id asc  ";
				List<doc> doc  =  jdbcTemplate.query(sql,new Object[]{doc_type,doc_type},new docmappers());
				return doc;
	}

	
	@Override
	public doc findDoc_in_set(String doc_type) {
		
			     try {
			    	 String sql =" SELECT * FROM doc WHERE"
								+ " FIND_IN_SET(concat(doc_type,doc_date,doc_autoid),?) "
								+ "OR FIND_IN_SET( concat(doc_type,doc_date,doc_autoid,doc_rv),?)  "
								+ "order by doc_id asc  ";
						doc doc  =  jdbcTemplate.queryForObject(sql,new Object[]{doc_type,doc_type},new docmappers());
						return doc;
			        } catch (Exception e) {
			          return null;
			        }
	}

	
	
	@Override
	public List<process> getProcess_join(int id) {
		
		System.out.println("getProcess_join ---> process "+id);
				String sql =" SELECT * FROM process WHERE process_join =?  ";
				List<process> join  =  jdbcTemplate.query(sql,new Object[]{id},new processmapper());
				return join;
	 
		 
	}
	
	@Override
	public List<production_directive> geteAllPD(String pl) {
		
				   if(pl.equals("Looselink")) {
			        	
						String sql =" SELECT * FROM production_directive LEFT JOIN edata ON edata.data_id = production_directive.edata_id    ";
						List<production_directive> pd  =  jdbcTemplate.query(sql,new production_directivemappers());
						return pd;
						
				        }else {
				        	
				        	String sql =" SELECT * FROM production_directive LEFT JOIN edata ON edata.data_id = production_directive.edata_id WHERE edata.reference =?   ";
							List<production_directive> pd  =  jdbcTemplate.query(sql,new Object[]{pl},new production_directivemappers());
							return pd;
				        }


	}
	
	@Override
	public List<qci> geteAllQCI(String qci) {
		
				
				
			     if(qci.equals("Looselink")) {
			        	
			    	 String sql =" SELECT * FROM qci LEFT JOIN edata ON edata.data_id = qci.edata_id   ";
						List<qci> qcis  =  jdbcTemplate.query(sql,new qcimappers());
						return qcis;
			        }else {
			        	
			        	String sql =" SELECT * FROM qci LEFT JOIN edata ON edata.data_id = qci.edata_id WHERE edata.reference =?   ";
						List<qci> qcis  =  jdbcTemplate.query(sql,new Object[]{qci},new qcimappers());
						return qcis;
			        }

	}
	@Override
	public List<other> geteAllOther(String other) {
		
				
				
			     if(other.equals("Looselink")) {
			        	
			    	 String sql =" SELECT * FROM other LEFT JOIN edata ON edata.data_id = other.edata_id  ";
						List<other> others  =  jdbcTemplate.query(sql,new othermappers());
						return others;
			        }else {
			        	
			        	String sql =" SELECT * FROM other LEFT JOIN edata ON edata.data_id = other.edata_id WHERE edata.reference =?   ";
						List<other> others  =  jdbcTemplate.query(sql,new Object[]{other},new othermappers());
						return others;
			        }
			
				

	}
	@Override
	public List<other> geteAllOtherByProcess(int process_id) {
		
				String sql =" SELECT * FROM other LEFT JOIN edata ON edata.data_id = other.edata_id WHERE edata.process_id =?   ";
				List<other> others  =  jdbcTemplate.query(sql,new Object[]{process_id},new othermappers());
				return others;

	}
	@Override
	public List<Adjustment_sk> geteAllSKByProcess(int process_id) {
		
				String sql =" SELECT * FROM adjustment_sk LEFT JOIN edata ON edata.data_id = adjustment_sk.edata_id WHERE edata.process_id =?   ";
				List<Adjustment_sk> data  =  jdbcTemplate.query(sql,new Object[]{process_id},new Adjustment_skmappers());
				return data;

	}
	
	
	
				@Override
				public List<Adjustment_sk> geteAllAdjustment_sk(String refer) {
					
						
							   if(refer.equals("Looselink")) {
						        	
									String sql =" SELECT * FROM adjustment_sk LEFT JOIN edata ON edata.data_id = adjustment_sk.edata_id    ";
									List<Adjustment_sk> data  =  jdbcTemplate.query(sql,new Adjustment_skmappers());
									return data;
							        }else {
							        	
							        	String sql =" SELECT * FROM adjustment_sk LEFT JOIN edata ON edata.data_id = adjustment_sk.edata_id WHERE edata.reference =?   ";
										List<Adjustment_sk> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Adjustment_skmappers());
										return data;
							        }
							
							
			
				}
	
	
				@Override
				public List<Adjustment_fg> geteAllAdjustment_fg(String refer) {
					
							
							
							   if(refer.equals("Looselink")) {
						        	
								   String sql =" SELECT * FROM adjustment_fg LEFT JOIN edata ON edata.data_id = adjustment_fg.edata_id    ";
									List<Adjustment_fg> data  =  jdbcTemplate.query(sql,new Adjustment_fgmappers());
									return data;
							        }else {
							        	
							        	String sql =" SELECT * FROM adjustment_fg LEFT JOIN edata ON edata.data_id = adjustment_fg.edata_id WHERE edata.reference =?   ";
										List<Adjustment_fg> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Adjustment_fgmappers());
										return data;
							        }
			
				}
				
				
				@Override
				public List<Inventory_transfer_sk> geteAllInventory_transfer_sk(String refer) {
					
						
							   if(refer.equals("Looselink")) {
						        	
									String sql =" SELECT * FROM inventory_transfer_sk LEFT JOIN edata ON edata.data_id = inventory_transfer_sk.edata_id   ";
									List<Inventory_transfer_sk> data  =  jdbcTemplate.query(sql,new Inventory_transfer_skmappers());
									return data;
							        }else {
							        	
							        	String sql =" SELECT * FROM inventory_transfer_sk LEFT JOIN edata ON edata.data_id = inventory_transfer_sk.edata_id WHERE edata.reference =?   ";
										List<Inventory_transfer_sk> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Inventory_transfer_skmappers());
										return data;
							        }
			
				}
				
				@Override
				public List<Inventory_transfer_fg> geteAllInventory_transfer_fg(String refer) {
					
					
					   if(refer.equals("Looselink")) {
				        	
						   String sql =" SELECT * FROM inventory_transfer_fg LEFT JOIN edata ON edata.data_id = inventory_transfer_fg.edata_id    ";
							List<Inventory_transfer_fg> data  =  jdbcTemplate.query(sql,new Inventory_transfer_fgmappers());
							return data;
			
					        }else {
					        	
					        	String sql =" SELECT * FROM inventory_transfer_fg LEFT JOIN edata ON edata.data_id = inventory_transfer_fg.edata_id WHERE edata.reference =?   ";
								List<Inventory_transfer_fg> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Inventory_transfer_fgmappers());
								return data;
				
					        }
							
				}
				
				
				@Override
				public List<Issue_sk> geteAllIssue_sk(String refer) {
					
						
							
							   if(refer.equals("Looselink")) {
						        	
									String sql =" SELECT * FROM issue_sk LEFT JOIN edata ON edata.data_id = issue_sk.edata_id   ";
									List<Issue_sk> data  =  jdbcTemplate.query(sql,new Issue_skmappers());
									return data;
					
							        }else {
							        	
							        	String sql =" SELECT * FROM issue_sk LEFT JOIN edata ON edata.data_id = issue_sk.edata_id WHERE edata.reference =?   ";
										List<Issue_sk> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Issue_skmappers());
										return data;
						
							        }
			
				}
				@Override
				public List<Issue_fg> geteAllIssue_fg(String refer) {
					
				
							
							   if(refer.equals("Looselink")) {
						        	
									String sql =" SELECT * FROM issue_fg LEFT JOIN edata ON edata.data_id = issue_fg.edata_id   ";
									List<Issue_fg> data  =  jdbcTemplate.query(sql,new Issue_fgmappers());
									return data;
							        }else {
							        	
										String sql =" SELECT * FROM issue_fg LEFT JOIN edata ON edata.data_id = issue_fg.edata_id WHERE edata.reference =?   ";
										List<Issue_fg> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Issue_fgmappers());
										return data;
						
							        }
			
				}
				
				@Override
				public List<Goods_receipt_sk> geteAllGoods_receipt_sk(String refer) {
					
					 
					if(refer.equals("Looselink")) {
				        	
							String sql =" SELECT * FROM goods_receipt_sk LEFT JOIN edata ON edata.data_id = goods_receipt_sk.edata_id    ";
							List<Goods_receipt_sk> data  =  jdbcTemplate.query(sql,new Goods_receipt_skmappers());
							return data;
					        }else {
					        	
					        	String sql =" SELECT * FROM goods_receipt_sk LEFT JOIN edata ON edata.data_id = goods_receipt_sk.edata_id WHERE edata.reference =?   ";
								List<Goods_receipt_sk> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Goods_receipt_skmappers());
								return data;
				
					        }
					
					
						
			
				}
				@Override
				public List<Goods_receipt_fg> geteAllGoods_receipt_fg(String refer) {
					
						
							if(refer.equals("Looselink")) {
					        	
								String sql =" SELECT * FROM goods_receipt_fg LEFT JOIN edata ON edata.data_id = goods_receipt_fg.edata_id   ";
								List<Goods_receipt_fg> data  =  jdbcTemplate.query(sql,new Goods_receipt_fgmappers());
								return data;
						        }else {
						        	
						        	String sql =" SELECT * FROM goods_receipt_fg LEFT JOIN edata ON edata.data_id = goods_receipt_fg.edata_id WHERE edata.reference =?   ";
									List<Goods_receipt_fg> data  =  jdbcTemplate.query(sql,new Object[]{refer},new Goods_receipt_fgmappers());
									return data;
					
						        }
			
				}
				
	
	
	
	
	
	
	@Override
	public List<production_directive> geteAllPDByProcess(int process_id) {
		
				String sql =" SELECT * FROM production_directive LEFT JOIN edata ON edata.data_id = production_directive.edata_id WHERE edata.process_id =?   ";
				List<production_directive> data  =  jdbcTemplate.query(sql,new Object[]{process_id},new production_directivemappers());
				return data;
				

	}
	
	
	
	@Override
	public List<edata_production_record> geteAllproduction_records(String pd) {
		
		System.out.println("getgeteAllproduction_records");
		      
				if(pd.equals("Looselink")) {
		        	
					String sql =" SELECT * FROM edata_production_record LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id    ";
					List<edata_production_record> production_record  =  jdbcTemplate.query(sql,new edata_production_recordmappers());
					return production_record;
		        }else {
		        	
					String sql =" SELECT * FROM edata_production_record LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id WHERE edata.reference =?   ";
					List<edata_production_record> production_record  =  jdbcTemplate.query(sql,new Object[]{pd},new edata_production_recordmappers());
					return production_record;
		        }
		

	 
		 
	}
	
	
	@Override
	public List<edata> geteAllbyUseextension(UseExtension useextension) {

		String key1 = useextension.getKey1();
		String key2= useextension.getKey2();
		String keyvalue1= useextension.getKeyvalue1();
		String keyvalue2=  useextension.getKeyvalue2();
		String tables=  useextension.getTables();
		
		
		StringBuilder  Bulider = new StringBuilder();
		
		Bulider.append(" SELECT * FROM edata LEFT JOIN ").append(tables).append(" ON edata.data_id = ").append(tables).append(".edata_id");
		
		if(key1 != null && keyvalue1 != null) {
			Bulider.append(" WHERE ").append("edata_production_record.").append(key1).append(" = ").append(keyvalue1);
		}
		if(key2 != null && keyvalue2 != null) {
			Bulider.append(" AND ").append("edata_production_record.").append(key2).append(" = ").append(keyvalue2);
		}
		
		String sql = Bulider.toString();
		
		List<edata> list  =  jdbcTemplate.query(sql,new edatamappers());
		return list;
	}
	
	
	
	
	
	@Override
	public List<edata_production_record> getAllEdata_production_records(int process_id,customreport customreports) {
				
		
		    
		
		
		
		        String sql =" SELECT ";
		      	String date = customreports.getFilter_date();
				sql = BulidReport(customreports,sql,date,"edata_production_record");
		        sql = sql +" FROM edata_production_record "
		        		+ "LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id"   	
		        		+ " WHERE edata.process_id =?   ";
				sql = GroupReport(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				
				System.out.println(" sql " +sql);
				
				List<edata_production_record> production_record  =  jdbcTemplate.query(sql,new Object[]{process_id},new edata_production_recordmappers());
				return production_record; 
	}
	
	

	@Override
	public List<edata> getallEPR(int process_id,customreport customreports) {
		
			    String sql =" SELECT ";
			   	String date = customreports.getFilter_date();
				System.out.println("Analysis "+customreports.getAnalysis());
				sql = BulidReport2(customreports,sql,date);
				sql =sql+"   FROM edata_production_record LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id WHERE edata.process_id =? ";
				sql = GroupReport2(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
				return edata;
	 
		 
	}
	
	@Override
	public List<edata> getallEdataQCI(int process_id,customreport customreports) {
		
	    String sql =" SELECT ";
	   	String date = customreports.getFilter_date();
		System.out.println("Analysis "+customreports.getAnalysis());
		sql = BulidReport2(customreports,sql,date);
		sql =sql+"   FROM qci LEFT JOIN edata ON edata.data_id = qci.edata_id WHERE edata.process_id =? ";
		sql = GroupReport2(customreports,sql,date);
		sql = sql +" order by edata.data_id desc  limit 50000";
		List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
		return edata;

 
	}
	
	@Override
	public List<qci> getAllQCI(int process_id,customreport customreports) {
				String sql =" SELECT ";
		      	String date = customreports.getFilter_date();
				sql = BulidReport(customreports,sql,date,"qci");
		        sql = sql +"  ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc "
		        		+ "FROM qci "
		        		+ "LEFT JOIN edata ON edata.data_id = qci.edata_id "
		        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
		        		+ "WHERE edata.process_id =?   ";
				sql = GroupReport(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				
				System.out.println(" sql " +sql);
				System.out.println(" process_id " +process_id);
				
				List<qci> qci  =  jdbcTemplate.query(sql,new Object[]{process_id},new qcimappers());
				return qci; 
	}
	
	

	

	@Override
	public List<edata> getallEdataOther(int process_id,customreport customreports) {
		
	    String sql =" SELECT ";
	   	String date = customreports.getFilter_date();
		System.out.println("Analysis "+customreports.getAnalysis());
		sql = BulidReport2(customreports,sql,date);
		sql =sql+" FROM other "
				+ "LEFT JOIN edata ON edata.data_id = other.edata_id "
				+ "WHERE edata.process_id =? ";
		sql = GroupReport2(customreports,sql,date);
		sql = sql +" order by edata.data_id desc  limit 50000";
		List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
		return edata;

 
	}
	
	@Override
	public List<other> getAllOther(int process_id,customreport customreports) {
				String sql =" SELECT ";
		      	String date = customreports.getFilter_date();
				sql = BulidReport(customreports,sql,date,"other");
		        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc  FROM other "
		        		+ "LEFT JOIN edata ON edata.data_id = other.edata_id  OR  edata.data_id = other.tempId  "
		        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
		        		+ " WHERE edata.process_id =?   ";
				sql = GroupReport(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				
				System.out.println(" sql " +sql);
				
				List<other> other  =  jdbcTemplate.query(sql,new Object[]{process_id},new othermappers());
				return other; 
	}
	
	
	
	

	@Override
	public List<edata> getallEdataPL(int process_id,customreport customreports) {
		
	    String sql =" SELECT ";
	   	String date = customreports.getFilter_date();
		System.out.println("Analysis "+customreports.getAnalysis());
		sql = BulidReport2(customreports,sql,date);
		sql =sql+"   FROM production_list LEFT JOIN edata ON edata.data_id = production_list.edata_id WHERE edata.process_id =? ";
		sql = GroupReport2(customreports,sql,date);
		sql = sql +" order by edata.data_id desc  limit 50000";
		List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
		return edata;

 
	}
	
	@Override
	public List<production_list> getAllPL(int process_id,customreport customreports) {
				String sql =" SELECT ";
		      	String date = customreports.getFilter_date();
				sql = BulidReport(customreports,sql,date,"production_list");
		        sql = sql +",concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc "
		        		+ "FROM production_list "
		        		+ "LEFT JOIN edata ON edata.data_id = production_list.edata_id "
		           		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
		        		+ "WHERE edata.process_id =?   ";
				sql = GroupReport(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				
				System.out.println(" sql " +sql);
				
				List<production_list> PL  =  jdbcTemplate.query(sql,new Object[]{process_id},new production_listmappers());
				return PL; 
	}
	
	
	
	@Override
	public List<edata> getallEdataPD(int process_id,customreport customreports) {
		
	    String sql =" SELECT ";
	   	String date = customreports.getFilter_date();
		System.out.println("Analysis "+customreports.getAnalysis());
		sql = BulidReport2(customreports,sql,date);
		sql =sql+"   FROM production_directive LEFT JOIN edata ON edata.data_id = production_directive.edata_id WHERE edata.process_id =? ";
		sql = GroupReport2(customreports,sql,date);
		sql = sql +" order by edata.data_id desc  limit 50000";
		List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
		return edata;

 
	}
	
	@Override
	public List<production_directive> getAllPD(int process_id,customreport customreports) {
				String sql =" SELECT ";
		      	String date = customreports.getFilter_date();
				sql = BulidReport(customreports,sql,date,"production_directive");
		        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc "
		        		+ " FROM production_directive "
		        		+ " LEFT JOIN edata ON edata.data_id = production_directive.edata_id"
		           		+ " LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
		        		+ " WHERE edata.process_id =?   ";
				sql = GroupReport(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				
				System.out.println(" sql " +sql);
				
				List<production_directive> PL  =  jdbcTemplate.query(sql,new Object[]{process_id},new production_directivemappers());
				return PL; 
	}
	
	
	@Override
	public List<production_directive> getAllPD(String PL) {
				String sql =" SELECT * FROM production_directive  WHERE pl_code =?  ";
				List<production_directive> data  =  jdbcTemplate.query(sql,new Object[]{PL},new production_directivemappers());
				return data; 
	}
	
	
	
	@Override
	public List<edata> getallEdataPR(int process_id,customreport customreports) {
		
	    String sql =" SELECT ";
	   	String date = customreports.getFilter_date();
		System.out.println("Analysis "+customreports.getAnalysis());
		sql = BulidReport2(customreports,sql,date);
		sql =sql+"   FROM edata_procurement LEFT JOIN edata ON edata.data_id = edata_procurement.edata_id OR  edata.data_id = edata_procurement.tempId    WHERE edata.process_id =? ";
		sql = GroupReport2(customreports,sql,date);
		sql = sql +" order by edata.data_id desc  limit 50000";
		List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
		return edata;

 
	}
	
	@Override
	public List<edata_procurement> getAllPR(int process_id,customreport customreports) {
				String sql =" SELECT ";
		      	String date = customreports.getFilter_date();
				sql = BulidReport(customreports,sql,date,"edata_procurement");
				
				
		        sql = sql +",concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc  "
		        		+ " FROM edata_procurement "
		        		+ "LEFT JOIN edata ON edata.data_id = edata_procurement.edata_id  OR  edata.data_id = edata_procurement.tempId  "
		        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
		        		+ "WHERE edata.process_id =? ";
				sql = GroupReport(customreports,sql,date);
				sql = sql +" order by edata.data_id desc  limit 50000";
				
				System.out.println(" sql " +sql);
				System.out.println(" process_id " +process_id);
				List<edata_procurement> PL  =  jdbcTemplate.query(sql,new Object[]{process_id},new edata_procurementmappers());
				return PL; 
	}
	
	
	
	
		@Override
		public List<edata> getallEdataBOM(int process_id,customreport customreports) {
			
		    String sql =" SELECT ";
		   	String date = customreports.getFilter_date();
			System.out.println("Analysis "+customreports.getAnalysis());
			sql = BulidReport2(customreports,sql,date);
			sql =sql+"   FROM bom LEFT JOIN edata ON edata.data_id = bom.edata_id WHERE edata.process_id =? ";
			sql = GroupReport2(customreports,sql,date);
			sql = sql +" order by edata.data_id desc  limit 50000";
			List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
			return edata;
	
	 
		}
		
		@Override
		public List<bom> getAllBOM(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"bom");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM bom "
			        		+ "LEFT JOIN edata ON edata.data_id = bom.edata_id "
			          		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<bom> bom  =  jdbcTemplate.query(sql,new Object[]{process_id},new bommappers());
					return bom; 
		}
		

		
		

		@Override
		public List<edata> getallEdataPS(int process_id,customreport customreports) {
			
		    String sql =" SELECT ";
		   	String date = customreports.getFilter_date();
			System.out.println("Analysis "+customreports.getAnalysis());
			sql = BulidReport2(customreports,sql,date);
			sql =sql+"   FROM production_standard LEFT JOIN edata ON edata.data_id = production_standard.edata_id WHERE edata.process_id =? ";
			sql = GroupReport2(customreports,sql,date);
			sql = sql +" order by edata.data_id desc  limit 50000";
			List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
			return edata;
	
	 
		}
		
		@Override
		public List<edata> getallEdataALLType(int process_id,customreport customreports,String type) {
			
		    String sql =" SELECT ";
		   	String date = customreports.getFilter_date();
			System.out.println("Analysis "+customreports.getAnalysis());
			sql = BulidReport2(customreports,sql,date);
			sql =sql+"   FROM "+type+" LEFT JOIN edata ON edata.data_id = "+type+".edata_id WHERE edata.process_id =? ";
			sql = GroupReport2(customreports,sql,date);
			sql = sql +" order by edata.data_id desc  limit 50000";
			List<edata> edata  =  jdbcTemplate.query(sql,new Object[]{process_id},new appmappers());
			return edata;
	
	 
		}
		
		
		
		@Override
		public List<production_standard> getAllPS(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"production_standard");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM production_standard "
			        		+ "LEFT JOIN edata ON edata.data_id = production_standard.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<production_standard> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new production_standardmappers());
					return ps; 
		}
		
		
	
		
		@Override
		public List<Goods_receipt_fg> getAllGFG(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"goods_receipt_fg");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM goods_receipt_fg "
			        		+ "LEFT JOIN edata ON edata.data_id = goods_receipt_fg.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Goods_receipt_fg> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Goods_receipt_fgmappers());
					return ps; 
		}
		@Override
		public List<Issue_fg> getAllIFG(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"issue_fg");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM issue_fg "
			        		+ "LEFT JOIN edata ON edata.data_id = issue_fg.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Issue_fg> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Issue_fgmappers());
					return ps; 
		}
		@Override
		public List<Adjustment_fg> getAllAFG(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"adjustment_fg");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM adjustment_fg "
			        		+ "LEFT JOIN edata ON edata.data_id = adjustment_fg.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Adjustment_fg> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Adjustment_fgmappers());
					return ps; 
		}
		@Override
		public List<Inventory_transfer_fg> getAllITFG(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"inventory_transfer_fg");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM inventory_transfer_fg "
			        		+ "LEFT JOIN edata ON edata.data_id = inventory_transfer_fg.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Inventory_transfer_fg> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Inventory_transfer_fgmappers());
					return ps; 
		}
		@Override
		public List<Issue_sk> getAllISK(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"issue_sk");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM issue_sk "
			        		+ "LEFT JOIN edata ON edata.data_id = issue_sk.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Issue_sk> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Issue_skmappers());
					return ps; 
		}
		@Override
		public List<Adjustment_sk> getAllASK(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"adjustment_sk");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM adjustment_sk "
			        		+ "LEFT JOIN edata ON edata.data_id = adjustment_sk.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Adjustment_sk> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Adjustment_skmappers());
					return ps; 
		}
		@Override
		public List<Inventory_transfer_sk> getAllITSK(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"inventory_transfer_sk");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM inventory_transfer_sk "
			        		+ "LEFT JOIN edata ON edata.data_id = inventory_transfer_sk.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Inventory_transfer_sk> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Inventory_transfer_skmappers());
					return ps; 
		}

		@Override
		public List<Goods_receipt_sk> getAllGSK(int process_id,customreport customreports) {
					String sql =" SELECT ";
			      	String date = customreports.getFilter_date();
					sql = BulidReport(customreports,sql,date,"goods_receipt_sk");
			        sql = sql +" ,concat(doc.doc_type,doc.doc_date,doc.doc_autoid) AS doc FROM goods_receipt_sk "
			        		+ "LEFT JOIN edata ON edata.data_id = goods_receipt_sk.edata_id "
			        		+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
			        		+ "WHERE edata.process_id =?   ";
					sql = GroupReport(customreports,sql,date);
					sql = sql +" order by edata.data_id desc  limit 50000";
					
					System.out.println(" sql " +sql);
					
					List<Goods_receipt_sk> ps  =  jdbcTemplate.query(sql,new Object[]{process_id},new Goods_receipt_skmappers());
					return ps; 
		}

		@Override
		public List<edata> getDateFollow(int id_add) {

			       String sql = " SELECT COUNT(*) AS alert,edata.*   FROM edata "
			        		+ "WHERE data_idadd =? AND data_dateplan IS NOT NULL GROUP BY data_dateplan limit 100    ";
					List<edata> ps  =  jdbcTemplate.query(sql,new Object[]{id_add},new edatamappers());
					return ps; 
		}

		@Override
		public void SetDatePlanByDataId(int data_id, String data_dateplan) {
			     String sql = " UPDATE edata SET data_dateplan= ? WHERE data_id =?   ";
				jdbcTemplate.update(sql,new Object[]{data_dateplan,data_id});
				
				System.out.print(" UPDATE DatePlan ");
				
		}
		@Override
		public void setLoadAll(int data_id, int loadall) {
			     String sql = " UPDATE edata SET loadall= ? WHERE data_id =?   ";
				jdbcTemplate.update(sql,new Object[]{loadall,data_id});
				
				System.out.print(" UPDATE DatePlan ");
				
		}
		@Override
		public void setProcess_id(int data_id, int process_id) {
			     String sql = " UPDATE edata SET process_id = ? WHERE data_id =?   ";
				jdbcTemplate.update(sql,new Object[]{process_id,data_id});
				
				System.out.print(" UPDATE DatePlan ");
				
		}

		
		
	private String GroupReport2(customreport customreports, String sql, String date) {
		if( customreports.getFilter() != null ) {
			sql = sql +" GROUP BY  "+customreports.getFilter();
        	if(date != null && !customreports.getDate_group().equals("-")) {
        		if(customreports.getDate_group().equals("DAY")) {
        			sql = sql +" ,DATE("+date+") ";
        		}else if(customreports.getDate_group().equals("MONTH")){
        			sql = sql +" ,DATE_FORMAT("+date+", '%Y-%m') ";
        		}else if(customreports.getDate_group().equals("YEAR")){
        			sql = sql +" ,YEAR("+date+") ";
        		}
        	}
			
		}
		return sql;
	}

	private String BulidReport2(customreport customreports, String sql, String date) {

		

        if( Otherservice.checkAllHaveValidValue(customreports.getFilter(),customreports.getAnalysis(),customreports.getFilter_data()) ) {
        	
        	System.out.println(" BulidReport2 ");
        	
        	String[]  Axis_y  = customreports.getFilter_data().split(",");
         	System.out.println(" BulidReport2 ");
        	
           	int e = 1;
        	for(String data  : Axis_y) {
        		
	        		List<eformfield> eformfields = customreports.getEformfield();
	        		int i  = Integer.valueOf(data) - 1 ;
	        		String key = null;
	        		
	        		
	        		if(eformfields.get(i).getKeymap() != null) {
	        			key = eformfields.get(i).getKeymap().getMainkey();
	        		}else {
	        			
	        			
	        			key =  "data_value"+data;		
	        		}
        		
	        		
	        	   	if(customreports.getAnalysis().equals("Sum")) {
		        		sql = sql +" SUM("+key+") AS "+key+",";
		        	}
		        	else if(customreports.getAnalysis().equals("Average")) {
		        		sql = sql +" AVG("+key+") AS "+key+",";
		        		
		        	}
		        	else if(customreports.getAnalysis().equals("Maximum")) {
		        		sql = sql +" MAX("+key+") AS "+key+",";
		        	}
		        	else if(customreports.getAnalysis().equals("Minimum")) {
		        		sql = sql +" MIN("+key+") AS "+key+",";
		        		
		        	}
		        	else if(customreports.getAnalysis().equals("Count")) {
		        		
		        		if(e  == 1) {
		        			sql = sql +" COUNT("+key+") AS count,";
		        		}else {
		        			sql = sql +" COUNT("+key+") AS "+key+",";
		        		}
		        	
		        		
		        	}	
		        	else if(customreports.getAnalysis().equals("Standard Deviation")) {
		        		sql = sql +" STDDEV("+key+") AS "+key+",";
		        		
		        	}	
		        	else if(customreports.getAnalysis().equals("Variance")) {
		        		sql = sql +" VARIANCE("+key+") AS "+key+",";
		        		
		        	}	   	
	        	   	
	        	e++;
	        	   	
        	}
        	
  
        	if(date != null && !customreports.getDate_group().equals("-")) {
        		if(customreports.getDate_group().equals("DAY")) {
        			sql = sql +" DATE("+date+") AS "+date+",";
        		}else if(customreports.getDate_group().equals("MONTH")){
        			sql = sql +" DATE_FORMAT("+date+", '%Y-%m') AS "+date+",";
        		}else if(customreports.getDate_group().equals("YEAR")){
        			sql = sql +" YEAR("+date+") AS "+date+",";
        		}
        	}

        	sql = sql +" edata.* ";	
        }else {

              System.out.println(" customreports.getAnalysis()  "+customreports.getAnalysis());
        	
        	if(customreports.getAnalysis() != null && customreports.getAnalysis().equals("Count")) {
        		
        		  
        		
        		sql = sql +" COUNT(*) AS count,";
        		sql = sql +" edata.* ";	
        		
        		
        		 System.out.println(" customreports.getAnalysis() sql "+sql);
        		
    		}else {
    			sql = sql +" * ";
    		}
        	
        
        }
        
        return sql;
	}



	private String GroupReport(customreport customreports, String sql, String date) {
		
		

		
		sql = Filter(customreports,sql,date);
		
	
		
		if( customreports.getFilter() != null ) {
			sql = sql +" GROUP BY  "+customreports.getFilter();
        	if(date != null && !customreports.getDate_group().equals("-")) {
        		if(customreports.getDate_group().equals("DAY")) {
        			sql = sql +" ,DATE("+date+") ";
        		}else if(customreports.getDate_group().equals("MONTH")){
        			sql = sql +" ,DATE_FORMAT("+date+", '%Y-%m') ";
        		}else if(customreports.getDate_group().equals("YEAR")){
        			sql = sql +" ,YEAR("+date+") ";
        		}
        	}
			
		}
		return sql;
	}

	
	private String Filter(customreport customreports, String sql, String date) {
		
			 
		if(customreports.getCondition_choice1() ==  null ) { return sql; }
		if(customreports.getOperation1() ==  null ) { return sql;  }
		if(customreports.getCondition_value1() ==  null ) {return sql;  }
		if(customreports.getFilter_column1() ==  null ) {return sql;  }
	
		if( customreports.getCondition_choice1().equals("-") ) { return sql; }
		
		StringBuilder Bulider = new StringBuilder();
		
		
		
		
		Bulider.append(" AND ( ");

		if(customreports.getOperation1().equals("FIND_IN_SET") ) {
			Bulider.append(customreports.getCondition_value1()).append(" ( ");
			Bulider.append(customreports.getFilter_column1()).append(",").append(" '").append(customreports.getCondition_value1()).append("' ").append(" ) ");	
		}
		
		else if(customreports.getOperation1().equals("NOT BLANK") ) {
			Bulider.append(customreports.getFilter_column1()).append(" ");
			Bulider.append("!=").append(" ''");
		}
		else if(customreports.getOperation1().equals("BLANK") ) {
			Bulider.append(customreports.getFilter_column1()).append(" ");
			Bulider.append("=").append(" ''");
		}
		else {
			
			Bulider.append(customreports.getFilter_column1()).append(" ");
			Bulider.append(customreports.getOperation1()).append(" ");
			if(!customreports.getOperation1().equals("IS NULL") && !customreports.getOperation1().equals("IS NOT NULL")) {
				Bulider.append(" '").append(customreports.getCondition_value1()).append("' ");
			}
		}

		
	
		

		if(customreports.getCondition_choice2() ==  null 
			||	customreports.getOperation2() ==  null
			|| customreports.getFilter_column2() ==  null
			) {
			
			Bulider.append(" ) ");
			return sql+Bulider.toString(); 
		}
		
		if( customreports.getCondition_choice2().equals("-") ) {
			Bulider.append(" ) ");
			return sql+Bulider.toString(); 
		}
		
		
		
		Bulider.append(" ").append(customreports.getCondition_choice2()).append(" ");
		if(customreports.getOperation2().equals("FIND_IN_SET") ) {
			Bulider.append(customreports.getCondition_value2()).append(" ( ");
			Bulider.append(customreports.getFilter_column2()).append(",").append(" '").append(customreports.getCondition_value2()).append("' ").append(" ) ");	
		}
		
		else if(customreports.getOperation2().equals("NOT BLANK") ) {
			Bulider.append(customreports.getFilter_column2()).append(" ");
			Bulider.append("!=").append(" ''");
		}
		else if(customreports.getOperation2().equals("BLANK") ) {
			Bulider.append(customreports.getFilter_column2()).append(" ");
			Bulider.append("=").append(" ''");
		}
		else {
			
			Bulider.append(customreports.getFilter_column2()).append(" ");
			Bulider.append(customreports.getOperation2()).append(" ");
			if(!customreports.getOperation2().equals("IS NULL") && !customreports.getOperation2().equals("IS NOT NULL")) {
				Bulider.append(" '").append(customreports.getCondition_value2()).append("' ");
			}
		}
		
		
		
		if(customreports.getCondition_choice3() ==  null 
				||	customreports.getOperation3() ==  null
				|| customreports.getFilter_column3() ==  null
				) {
				
				Bulider.append(" ) ");
				return sql+Bulider.toString(); 
			}
		
		if( customreports.getCondition_choice3().equals("-") ) {
			Bulider.append(" ) ");
			return sql+Bulider.toString(); 
		}
		
		
		Bulider.append(" ").append(customreports.getCondition_choice3()).append(" ");
		if(customreports.getOperation3().equals("FIND_IN_SET") ) {
			Bulider.append(customreports.getCondition_value3()).append(" ( ");
			Bulider.append(customreports.getFilter_column3()).append(",").append(" '").append(customreports.getCondition_value3()).append("' ").append(" ) ");	
		}
		
		else if(customreports.getOperation3().equals("NOT BLANK") ) {
			Bulider.append(customreports.getFilter_column3()).append(" ");
			Bulider.append("!=").append(" ''");
		}
		else if(customreports.getOperation3().equals("BLANK") ) {
			Bulider.append(customreports.getFilter_column3()).append(" ");
			Bulider.append("=").append(" ''");
		}
		else {
			
			Bulider.append(customreports.getFilter_column3()).append(" ");
			Bulider.append(customreports.getOperation3()).append(" ");
			if(!customreports.getOperation3().equals("IS NULL") && !customreports.getOperation3().equals("IS NOT NULL")) {
				Bulider.append(" '").append(customreports.getCondition_value3()).append("' ");
			}
		}
		Bulider.append(" ) ");
		
		
		return  sql+Bulider.toString(); 
	}
	
	
	


	private String BulidReport(customreport customreports, String sql, String date, String table) {

		
		
        if(customreports == null ) { return sql = sql +" * ";}
 
        if(customreports.getFilter() != null && !customreports.getAnalysis().equals("-") ) {
        	
        	
        	
        	if(customreports.getFilter_data() != null) {
        		String[]  Axis_y  = customreports.getFilter_data().split(",");
            	
            	
            	int e = 1;
            	for(String data  : Axis_y) {
            		
    	        		List<eformfield> eformfields = customreports.getEformfield();
    	        		int i  = Integer.valueOf(data) - 1 ;
    	        		String key = null;
    	        		
    	        		
    	        		if(eformfields.get(i).getKeymap() != null) {
    	        			key = eformfields.get(i).getKeymap().getMainkey();
    	        		}else {
    	        			key =  "data_value"+data;
    	        		}
            		
    	        		
    	        	   	if(customreports.getAnalysis().equals("Sum")) {
    	        	   		sql = sql + " ROUND(SUM(COALESCE(" + key + ", 0)), 2) AS " + key + ",";
    		        		
    		        		if( table.equals("edata_procurement")  ) {
    		        			  sql += "ROUND(SUM(edata_procurement.procurement_quantityrequested * edata_procurement.procurement_estimatedprice), 2) AS procurement_linetotal,";
    		        			  sql += "ROUND(SUM(edata_procurement.procurement_quantity_ordered * edata_procurement.procurement_unit_price), 2) AS procurement_linetotal2,";
    		        		}
    		        	}
    		        	else if(customreports.getAnalysis().equals("Average")) {
    		      
    		        		
    		        		sql += " ROUND(AVG(CASE WHEN " + key + " >= 0.001 THEN " + key + " ELSE NULL END), 2) AS " + key + ",";
    		        		
    		        	}
    		        	else if(customreports.getAnalysis().equals("Maximum")) {
    		        	
    		        		
    		        		sql += " ROUND(MAX(CASE WHEN COALESCE(" + key + ", 0) >= 0.001 THEN " + key + " ELSE NULL END), 2) AS " + key + ",";
    		        	}
    	        	   	
    		        	else if(customreports.getAnalysis().equals("Minimum")) {
    		        		sql += " ROUND(MIN(CASE WHEN COALESCE(" + key + ", 0) >= 0.001 THEN " + key + " ELSE NULL END), 2) AS " + key + ",";
    		        	}
    		        	else if(customreports.getAnalysis().equals("Count")) {
    		        		
    		        		if(e  == 1) {
    		        			sql += " COUNT(CASE WHEN COALESCE(" + key + ", 0) >= 0.001 THEN 1 ELSE NULL END) AS count,";
    		        		}else {
    		        			sql += " COUNT(CASE WHEN COALESCE(" + key + ", 0) >= 0.001 THEN 1 ELSE NULL END) AS " + key + ",";
    		        		}
    		        		
    		        	
    		        		
    		        		
    		        	}	
    		        	else if(customreports.getAnalysis().equals("Standard Deviation")) {
    		        		sql += " ROUND(STDDEV(CASE WHEN COALESCE(" + key + ", 0) >= 0.001 THEN " + key + " ELSE NULL END), 2) AS " + key + ",";
    		        	}	
    		        	else if(customreports.getAnalysis().equals("Variance")) {
    		          		sql += " ROUND(VARIANCE(CASE WHEN COALESCE(" + key + ", 0) >= 0.001 THEN " + key + " ELSE NULL END), 2) AS " + key + ",";
    		          		
    		        		
    		        	}	   	
    	        	   	
    	        	   	
    	        	   	e++;
        	}
        		
        	}else {
        		
        		if(customreports.getAnalysis() != null && customreports.getAnalysis().equals("Count")) {
            		sql = sql +" COUNT(*) AS count,";
        		}
        		
        		if( table.equals("edata_procurement")  ) {
        			  sql += "(edata_procurement.procurement_quantityrequested * edata_procurement.procurement_estimatedprice) AS procurement_linetotal,";
        			  sql += "(edata_procurement.procurement_quantity_ordered * edata_procurement.procurement_unit_price) AS procurement_linetotal2,";
        			
        		}
        
	        	
	        	   	
        	}
        	
  
        	if(date != null && !customreports.getDate_group().equals("-")) {
        		if(customreports.getDate_group().equals("DAY")) {
        			sql = sql +" DATE("+date+") AS "+date+",";
        		}else if(customreports.getDate_group().equals("MONTH")){
        			sql = sql +" DATE_FORMAT("+date+", '%Y-%m') AS "+date+",";
        		}else if(customreports.getDate_group().equals("YEAR")){
        			sql = sql +" YEAR("+date+") AS "+date+",";
        		}
        	}
        	
        	
      
        	
        	sql = sql +" "+table+".* ";
     
        	
        	
        }else {
        	
        	
        
    			sql = sql +" * ";
    		
        }

        
          return sql;
		
	}


	
	
	@Override
	public edata_production_record getSumProduction_records(String pd) {
		
		
				
				if(pd.equals("Looselink")) {
		        	
					String sql =" SELECT SUM(actual_output) AS total_output,SUM(defective_output) AS total_defective"
							+ " FROM edata_production_record LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id   ";
					edata_production_record production_record  =  jdbcTemplate.queryForObject(sql,new edata_production_recordmappers());
					return production_record;
			        }else {
			        	
			        	String sql =" SELECT SUM(actual_output) AS total_output,SUM(defective_output) AS total_defective"
								+ " FROM edata_production_record LEFT JOIN edata ON edata.data_id = edata_production_record.edata_id WHERE edata.reference =?   ";
						edata_production_record production_record  =  jdbcTemplate.queryForObject(sql,new Object[]{pd},new edata_production_recordmappers());
						return production_record;
		
			        }
	 
		 
	}
	
	@Override
	public int SaveProcurements(edata_procurement edata_procurements) {
		

		
		Object[] edata_procurementInfo = { 
				edata_procurements.getEdata_id(),
				edata_procurements.getProcurement_itemid(),
				edata_procurements.getProcurement_itemname(),
				edata_procurements.getProcurement_quantityrequested(),
				edata_procurements.getProcurement_estimatedprice(),
				edata_procurements.getProcurement_unit(),
				edata_procurements.getProcurement_requesteddate(),
				edata_procurements.getProcurement_grandtotal(),
				edata_procurements.getProcurement_linetotal(),
				
				
				edata_procurements.getProcurement_quantity_ordered(),
				edata_procurements.getProcurement_unit_price(),
				edata_procurements.getProcurement_payment_status(),
				edata_procurements.getProcurement_delivery_date(),
				edata_procurements.getProcurement_supplier_name(),
				edata_procurements.getProcurement_supplier_address(),
				edata_procurements.getProcurement_supplier_id(),
				edata_procurements.getProcurement_grandtotal2(),
				edata_procurements.getProcurement_linetotal2(),
				
				edata_procurements.getReceived_date(),
				edata_procurements.getTracking_number(),
				edata_procurements.getInspection_status(),
				edata_procurements.getDamage_report(),
				edata_procurements.getStorage_location(),
				edata_procurements.getQuantity_received(),
				
				
				};
	
		
	

		
		
		

	//	private String procurement_documenttype;
		//private String procurement_documentnumber;

		System.out.println("SaveProcurements->Edata_id  : " + edata_procurements.getEdata_id());
		
		
		
		
		
		if(edata_procurements.getId() > 0) {
			
		
			
			String sql = "UPDATE edata_procurement SET edata_id=?,procurement_itemid=?,procurement_itemname=?,procurement_quantityrequested=?,procurement_estimatedprice=?"
					+ ",procurement_unit=?,procurement_requesteddate=?,procurement_grandtotal=?,procurement_linetotal=?"
					+ ",procurement_quantity_ordered=?,procurement_unit_price=?,procurement_payment_status=?,procurement_delivery_date=?"
					+ ",procurement_supplier_name=?,procurement_supplier_address=?,procurement_supplier_id=?,procurement_grandtotal2=?,procurement_linetotal2=?"
					+ ",received_date=?,tracking_number=?,inspection_status=?,damage_report=?,storage_location=?,quantity_received=? "			
					+ " WHERE id ="+edata_procurements.getId();
			
			
			return jdbcTemplate.update(sql,edata_procurementInfo);
			
			
		}else if(edata_procurements.getEdata_id() > 0) {
					

			String sql = "INSERT INTO edata_procurement(edata_id,procurement_itemid,procurement_itemname,procurement_quantityrequested,procurement_estimatedprice,procurement_unit"
					+ ",procurement_requesteddate,procurement_grandtotal,procurement_linetotal"
					+ ",procurement_quantity_ordered,procurement_unit_price,procurement_payment_status,procurement_delivery_date,procurement_supplier_name"
					+ ",procurement_supplier_address,procurement_supplier_id,procurement_grandtotal2,procurement_linetotal2"
					+ ",received_date,tracking_number,inspection_status,damage_report,storage_location,quantity_received) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					
					KeyHolder keyHolder = new GeneratedKeyHolder();
						
					
					
					jdbcTemplate.update(connection -> {
					    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					    for (int i = 0; i < edata_procurementInfo.length; i++) {
					        ps.setObject(i + 1, edata_procurementInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
					    }
					    return ps;
					}, keyHolder);
		
					// คืนค่า id ที่ถูกสร้างขึ้น
					return keyHolder.getKey().intValue();
					
					
		}else{
			return 0;
		}
		

		
	}
	
	
	
	
	@Override
	public int SaveRating(edata_register_processing edata_register_processings,String type) {
		
		
	
		switch (type) {
		case "supplier":
			String sql = "SELECT FLOOR( "
			           + "    (COALESCE(quality, 0) + COALESCE(value, 0) + COALESCE(delivery, 0) + "
			           + "     COALESCE(support, 0) + COALESCE(reliability, 0) + COALESCE(flexibility, 0) + "
			           + "     COALESCE(user_experience, 0) + COALESCE(sustainability, 0) + COALESCE(partnership, 0)) "
			           + "    / "
			           + "    NULLIF( "
			           + "        (CASE WHEN quality IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN value IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN delivery IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN support IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN reliability IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN flexibility IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN user_experience IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN sustainability IS NOT NULL THEN 1 ELSE 0 END + "
			           + "         CASE WHEN partnership IS NOT NULL THEN 1 ELSE 0 END), 0) "
			           + ") AS register_poin "
			           + "FROM edata_register_processing "
			           + "WHERE supplier_id = ?;";
			
			registers registers = jdbcTemplate.queryForObject(sql,new Object[]{edata_register_processings.getSupplier_id()},new registersmapper());
			String sqls = "UPDATE  register SET register_poin= ?  WHERE concat(COALESCE(register_code, ''),register_daterun,register_id) = ? ;";
			jdbcTemplate.update(sqls,new Object[]{registers.getRegister_poin(),edata_register_processings.getSupplier_id()});
			return 1;    
			default:
		}	
		return 1;

	}
	
	
	
	
	
	
	@Override
	public int SaveRegister_processing(edata_register_processing edata_register_processings) {
		

		
		Object[] edata_register_processingsInfo = { 
				edata_register_processings.getEdata_id(),
				edata_register_processings.getSupplier_id(),
				edata_register_processings.getSupplier_name(),
				edata_register_processings.getDoc_refer(),
				edata_register_processings.getQuality(),
				edata_register_processings.getValue(),
				edata_register_processings.getDelivery(),
				edata_register_processings.getSupport(),
				edata_register_processings.getReliability(),
				edata_register_processings.getFlexibility(),
				edata_register_processings.getUser_experience(),
				edata_register_processings.getSustainability(),
				edata_register_processings.getPartnership(),
				};
	

		if(edata_register_processings.getId() > 0) {

			String sql = "UPDATE edata_register_processing SET edata_id=?,supplier_id=?,supplier_name=?,doc_refer=?,quality=?"
					+ ",value =?,delivery =?,support =?,reliability =?,flexibility =? ,user_experience=? ,sustainability=? ,partnership=? "			
					+ " WHERE id ="+edata_register_processings.getId();
			return jdbcTemplate.update(sql,edata_register_processingsInfo);

		}else if(edata_register_processings.getEdata_id() > 0) {
					
			String sql = "INSERT INTO edata_register_processing(edata_id,supplier_id,supplier_name,doc_refer,quality,value,delivery,support,reliability"
					+ ",flexibility,user_experience,sustainability,partnership) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					KeyHolder keyHolder = new GeneratedKeyHolder();

					jdbcTemplate.update(connection -> {
					    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					    for (int i = 0; i < edata_register_processingsInfo.length; i++) {
					        ps.setObject(i + 1, edata_register_processingsInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
					    }
					    return ps;
					}, keyHolder);
		
					// คืนค่า id ที่ถูกสร้างขึ้น
					return keyHolder.getKey().intValue();
					
					
		}else{
			return 0;
		}
		
}
	
	
	
	
	

		@Override
		public void CopyEdata_procurement(int edata_id,String ids) {
			String sql ="SELECT * FROM   edata_procurement  WHERE FIND_IN_SET(id,?)";
			List<edata_procurement> edata_procurements = jdbcTemplate.query(sql,new Object[]{ids},new edata_procurementmappers());
			
			edata_procurements.forEach(row->{
				row.setId_refer(row.getId());
				row.setProcurement_quantity_ordered(row.getProcurement_quantityrequested());
				row.setProcurement_unit_price(row.getProcurement_estimatedprice());
				row.setProcurement_status("Pending");
				row.setQuantity_received(row.getProcurement_quantityrequested());
			});
			
			SaveEdata_procurements_item(edata_procurements,edata_id);
			
			
		}

		
		@Override
		public void CopyProduction_directivet(int edata_id,String ids) {
			
			
			String sql ="SELECT * FROM   production_list  WHERE id = ? ";
			production_list subitem = jdbcTemplate.queryForObject(sql,new Object[]{ids},new production_listmappers());
						
			
			System.out.println(" subitem"+subitem.toString() );
			
			
			String sql2 ="SELECT * FROM   production_list  WHERE edata_id = ? ";
			production_list pl = jdbcTemplate.queryForObject(sql2,new Object[]{subitem.getTempId()},new production_listmappers());
	

			
			
			for (Method getter : production_list.class.getMethods()) {
			    if (getter.getName().startsWith("get") && getter.getParameterCount() == 0) {
			        try {
			            Object value = getter.invoke(subitem);
			            if (value != null) {
			                String setterName = getter.getName().replaceFirst("get", "set");
			                Method setter = production_list.class.getMethod(setterName, getter.getReturnType());
			                setter.invoke(pl, value);
			            }
			        } catch (Exception e) {
			            e.printStackTrace(); // หรือจัดการ error ตามต้องการ
			        }
			    }
			}
	
			
		    System.out.println("--->Import_production_directive edata_id "+edata_id);
			
	        
			production_directive pd = geteData_pd(edata_id);
			pd.setLot_batch(pl.getLot_batch());
			pd.setProduct_name(pl.getProduct_name());
			pd.setProduct_code(pl.getProduct_code());
			pd.setQuantity(pl.getOrder_quantity());
			pd.setLot_batch(pl.getLot_batch());
			pd.setProcess_step(pl.getProcesses());
			pd.setOrder_quantity(pl.getSub_order_quantity());
			pd.setItem_code(pl.getItem_code());
			pd.setResponsible_person(pl.getResponsible_person());
			pd.setStart_date(pl.getStart_date());
			pd.setEnd_date(pl.getEnd_date());
			pd.setUnit(pl.getSub_unit());
			pd.setUnit(pl.getUnit());
			pd.setDepartment(pl.getDepartment());
			
		    SaveProduction_directives(pd);
			
			
			
			
		}

		
		
		

		
		@Override
		public void SaveEdata_procurements_item(List<edata_procurement> Subdata,int id) {
			
			String sql = "INSERT INTO edata_procurement(tempId,procurement_itemid,procurement_itemname,procurement_quantityrequested,procurement_estimatedprice,procurement_unit,procurement_requesteddate"
					+ ",procurement_quantity_ordered,procurement_unit_price,procurement_payment_status,procurement_delivery_date,procurement_supplier_name,procurement_supplier_address,procurement_supplier_id"
					+ ",edata_id,procurement_type,procurement_status,id_refer,received_date,tracking_number,inspection_status,damage_report,storage_location,quantity_received) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  jdbcTemplate.batchUpdate(sql,Subdata,1000,
		                new ParameterizedPreparedStatementSetter<edata_procurement>() {
		                    public void setValues(PreparedStatement ps, edata_procurement argument) 
								throws SQLException {
		                        ps.setInt(1,id);
		                        ps.setString(2, argument.getProcurement_itemid());
		                        ps.setString(3, argument.getProcurement_itemname());
		                        ps.setDouble(4, argument.getProcurement_quantityrequested());
		                        ps.setDouble(5,argument.getProcurement_estimatedprice());
		                        ps.setString(6, argument.getProcurement_unit());
		                        ps.setString(7,argument.getProcurement_requesteddate());
		                        ps.setDouble(8,argument.getProcurement_quantity_ordered());
		                        ps.setDouble(9,argument.getProcurement_unit_price());
		                        ps.setString(10,argument.getProcurement_payment_status());
		                        ps.setString(11,argument.getProcurement_delivery_date());
		                        ps.setString(12,argument.getProcurement_supplier_name());
		                        ps.setString(13,argument.getProcurement_supplier_address());
		                        ps.setString(14,argument.getProcurement_supplier_id());
		                        ps.setInt(15,0);
		                        ps.setString(16,argument.getProcurement_type());
		                        ps.setString(17,"Pending");
		                        ps.setInt(18,argument.getId_refer());    
		                        
		                        ps.setString(19,argument.getReceived_date());   
		                        ps.setString(20,argument.getTracking_number());   
		                        ps.setString(21,argument.getInspection_status());   
		                        ps.setString(22,argument.getDamage_report());   
		                        ps.setString(23,argument.getStorage_location());   
		                        ps.setDouble(24,argument.getQuantity_received());   
		                    }
		                });
			 
		}
		
		
	
		
		@Override
		public void SaveBom(List<bom> Subdata,int id) {
			
			System.out.println(" bom size " +Subdata.size());
			
		    String idUse =  Subdata.stream().map(row-> String.valueOf(row.getId())).collect(Collectors.joining(","));

		    System.out.println(" idUse "+idUse);
		    
		    
		    String sqls ="DELETE  FROM   bom  WHERE tempId =? AND not find_in_set(id,?) AND edata_id IS NULL  ";
			jdbcTemplate.update(sqls,new Object[]{ id,idUse});
		    
			String sql = """
					INSERT INTO bom (
					    tempId,bom_name,bom_description,bom_base_quantity,bom_base_unit,
					    bom_note,status,bom_category,bom_total_cost,bom_price,bom_wastage,
					    bom_product_code,bom_product_name,id
					)
					VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)
					ON DUPLICATE KEY UPDATE
					    bom_name = VALUES(bom_name),
					    bom_description = VALUES(bom_description),
					    bom_base_quantity = VALUES(bom_base_quantity),
					    bom_base_unit = VALUES(bom_base_unit),
					    bom_note = VALUES(bom_note),
					    status = VALUES(status),
					    bom_category = VALUES(bom_category),
					    bom_total_cost = VALUES(bom_total_cost),
					    bom_price = VALUES(bom_price),
					    bom_wastage = VALUES(bom_wastage),
					    bom_product_code = VALUES(bom_product_code),
					    bom_product_name = VALUES(bom_product_name);
					""";
			  jdbcTemplate.batchUpdate(sql,Subdata,1000,
		                new ParameterizedPreparedStatementSetter<bom>() {
		                    public void setValues(PreparedStatement ps, bom argument) 
								throws SQLException {
		                        ps.setString(1, argument.getTempId());
		                        ps.setString(2, argument.getBom_name());
		                        ps.setString(3, argument.getBom_description());
		                        ps.setDouble(4,argument.getBom_base_quantity());
		                        ps.setString(5, argument.getBom_base_unit());
		                        ps.setString(6,argument.getBom_note());
		                        ps.setString(7,"Pending");
		                        ps.setString(8,argument.getBom_category());
		                        ps.setDouble(9,argument.getBom_total_cost());
		                        ps.setDouble(10,argument.getBom_price());
		                        ps.setDouble(11,argument.getBom_wastage());
		                        ps.setString(12,argument.getBom_product_code());
		                        ps.setString(13,argument.getBom_product_name());
		                        ps.setObject(14, argument.getId() == 0 ? null : argument.getId()); 
		                    }
		                });
			  
			
			  
			//  List<bom> Subdata
			  
		}
		

		@Override
		public void SaveProduction_standard(List<production_standard> Subdata,int id) {
			
			
			System.out.println(" bom size " +Subdata.size());
			
		    String idUse =  Subdata.stream().map(row-> String.valueOf(row.getId())).collect(Collectors.joining(","));

		    System.out.println(" idUse "+idUse);
		    
		    
		    String sqls ="DELETE  FROM   production_standard  WHERE tempId =? AND not find_in_set(id,?) AND edata_id IS NULL  ";
			jdbcTemplate.update(sqls,new Object[]{ id,idUse});
			
			
			String sql = "INSERT INTO production_standard(tempId,title,process_step,bom_product_code,bom_product_name,title_string1"
					+ ",title_string2,title_string3,title_string4,title_string5,title_number1,title_number2"
					+ ",title_number3,title_number4,title_number5,note,status,id) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "tempId = VALUES(tempId),"
					+ "title = VALUES(title),"
					+ "process_step = process_step(tempId),"
					+ "bom_product_code = VALUES(bom_product_code),"
					+ "bom_product_name = VALUES(bom_product_name),"
					+ "title_string1 = VALUES(title_string1),"
					+ "title_string2 = VALUES(title_string2),"
					+ "title_string3 = VALUES(title_string3),"
					+ "title_string4 = VALUES(title_string4),"
					+ "title_string5 = VALUES(title_string5),"
					+ "title_number1 = VALUES(title_number1),"
					+ "title_number2 = VALUES(title_number2),"
					+ "title_number3 = VALUES(title_number3),"
					+ "title_number4 = VALUES(title_number4),"
					+ "title_number5 = VALUES(title_number5),"
					+ "note = VALUES(note),"
					+ "status = VALUES(status)"
					;
			
			
			  jdbcTemplate.batchUpdate(sql,Subdata,1000,
		                new ParameterizedPreparedStatementSetter<production_standard>() {
		                    public void setValues(PreparedStatement ps, production_standard argument) 
								throws SQLException {
		                        ps.setString(1, argument.getTempId());
		                        ps.setString(2, argument.getTitle());
		                        ps.setString(3, argument.getProcess_step());
		                        ps.setString(4,argument.getProduct_code());
		                        ps.setString(5, argument.getProduct_name());
		                        ps.setString(6,argument.getTitle_string1());
		                        ps.setString(7,argument.getTitle_string2());
		                        ps.setString(8,argument.getTitle_string3());
		                        ps.setString(9,argument.getTitle_string4());
		                        ps.setString(10,argument.getTitle_string5());
		                        ps.setString(11,argument.getTitle_number1());
		                        ps.setString(12,argument.getTitle_number2());
		                        ps.setString(13,argument.getTitle_number3());
		                        ps.setString(14,argument.getTitle_number4());
		                        ps.setString(15,argument.getTitle_number5());
		                        ps.setString(16,argument.getNote());
		                        ps.setString(17,"Pending");
		                        ps.setObject(18, argument.getId() == 0 ? null : argument.getId()); 

		                    }
		                });
			 
		}
		
		
		
		
		@Override
		public void SaveOther(List<other> subData, int id) {

			
			   String idUse =  subData.stream().map(row-> String.valueOf(row.getId())).collect(Collectors.joining(","));
			    String sqls ="DELETE  FROM   other  WHERE tempId =? AND not find_in_set(id,?) AND edata_id IS NULL  ";
				jdbcTemplate.update(sqls,new Object[]{ id,idUse});
			
			
		    String sql = "INSERT INTO other(tempId, doc_code, product_code, product_name, lot_batch, process_step, department,list,employee,inspection,equipment,date_start,id) "
		               + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"
		               + "ON DUPLICATE KEY UPDATE "
		               + "tempId = VALUES(tempId),"
		               + "doc_code = VALUES(doc_code),"
		               + "product_code = VALUES(product_code),"
		               + "product_name = VALUES(product_name),"
		               + "lot_batch = VALUES(lot_batch),"
		               + "process_step = VALUES(process_step),"
		               + "department = VALUES(department),"
		               + "list = VALUES(list),"
		               + "employee = VALUES(employee),"
		               + "inspection = VALUES(inspection),"
		               + "equipment = VALUES(equipment),"
		               + "date_start = VALUES(date_start)"
		               ;

		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<other>() {
		            public void setValues(PreparedStatement ps, other argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getProduct_code());
		                ps.setString(4, argument.getProduct_name());
		                ps.setString(5, argument.getLot_batch());
		                ps.setString(6, argument.getProcess_step());
		                ps.setString(7, argument.getDepartment());
		                
		                ps.setString(8, argument.getList());
		                ps.setString(9, argument.getEmployee());
		                ps.setString(10, argument.getInspection());
		                ps.setString(11, argument.getEquipment());
		                ps.setString(12, argument.getDate_start());
		                ps.setObject(13, argument.getId() == 0 ? null : argument.getId()); 
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		
		
		@Override
		public void SaveGoods_receipt_sk(List<Goods_receipt_sk> subData, int id) {

			  String sql = "INSERT INTO goods_receipt_sk (edata_id, doc_code, tempId, title, skg_received_date, skg_storage_location, skg_received_by, "
	                   + "skg_remark, skg_pd_code, skg_lot_batch, skg_tracking_number, skg_damage_report, skg_supplier_code, skg_supplier_name, "
	                   + "skg_quantity_received, skg_unit, skg_expiration_date,skg_price,skg_stock_code,skg_stock_name) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";


		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Goods_receipt_sk>() {
		            public void setValues(PreparedStatement ps, Goods_receipt_sk argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getSkg_received_date());
		                ps.setString(5, argument.getSkg_storage_location());
		                ps.setString(6, argument.getSkg_received_by());
		                ps.setString(7, argument.getSkg_remark());
		                ps.setString(8, argument.getSkg_pd_code());
		                ps.setString(9, argument.getSkg_lot_batch());
		                ps.setString(10, argument.getSkg_tracking_number());
		                ps.setString(11, argument.getSkg_damage_report());
		                ps.setString(12, argument.getSkg_supplier_code());
		                ps.setString(13, argument.getSkg_supplier_name());
		                ps.setDouble(14, argument.getSkg_quantity_received());
		                ps.setString(15, argument.getSkg_unit());
		                ps.setString(16, argument.getSkg_expiration_date());
		                ps.setDouble(17, argument.getSkg_price());
		                ps.setString(18, argument.getSkg_stock_code());
		                ps.setString(19, argument.getSkg_stock_name());
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		@Override
		public void SaveGoods_receipt_fg(List<Goods_receipt_fg> subData, int id) {

			  String sql = "INSERT INTO goods_receipt_fg (edata_id, doc_code, tempId, title, status, receipt_date, "
	                   + "work_order_no, location, received_by, remark, goods_receipt_pd_code, goods_receipt_lot_batch, goods_receipt_product_code, goods_receipt_product_name, "
	                   + "goods_receipt_quantity, goods_receipt_unit,goods_receipt_customer_code,goods_receipt_customer_name) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Goods_receipt_fg>() {
		            public void setValues(PreparedStatement ps, Goods_receipt_fg argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getStatus());
		                ps.setString(5, argument.getReceipt_date());
		                ps.setString(6, argument.getWork_order_no());
		                ps.setString(7, argument.getLocation());
		                ps.setString(8, argument.getReceived_by());
		                ps.setString(9, argument.getRemark());
		                ps.setString(10, argument.getGoods_receipt_pd_code());
		                ps.setString(11, argument.getGoods_receipt_lot_batch());
		                ps.setString(12, argument.getGoods_receipt_product_code());
		                ps.setString(13, argument.getGoods_receipt_product_name());
		                ps.setDouble(14, argument.getGoods_receipt_quantity());
		                ps.setString(15, argument.getGoods_receipt_unit());
		                ps.setString(16, argument.getGoods_receipt_customer_code());
		                ps.setString(17, argument.getGoods_receipt_customer_name());

		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
	
		

		@Override
		public void SaveInventory_transfer_sk(List<Inventory_transfer_sk> subData, int id) {

		       String sql = "INSERT INTO inventory_transfer_sk (edata_id, doc_code, tempId, title, skt_date, skt_from_location, "
		                + "skt_to_location, skt_transferred_by, skt_remark, skt_pd_code, skt_lot_batch, skt_stock_code, "
		                + "skt_stock_name, skt_quantity, skt_unit,skt_status) "
		                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Inventory_transfer_sk>() {
		            public void setValues(PreparedStatement ps, Inventory_transfer_sk argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getSkt_date());
		                ps.setString(5, argument.getSkt_from_location());
		                ps.setString(6, argument.getSkt_to_location());
		                ps.setString(7, argument.getSkt_transferred_by());
		                ps.setString(8, argument.getSkt_remark());
		                ps.setString(9, argument.getSkt_pd_code());
		                ps.setString(10, argument.getSkt_lot_batch());
		                ps.setString(11, argument.getSkt_stock_code());
		                ps.setString(12, argument.getSkt_stock_name());
		                ps.setDouble(13, argument.getSkt_quantity());
		                ps.setString(14, argument.getSkt_unit());
		                ps.setString(15, argument.getSkt_status());
		
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		@Override
		public void SaveInventory_transfer_fg(List<Inventory_transfer_fg> subData, int id) {


			  String sql = "INSERT INTO inventory_transfer_fg (edata_id, doc_code, tempId, title, status, transfer_date, transfer_from_location, "
						                + "transfer_to_location, transferred_by, transfer_remark, transfer_pd_code, transfer_lot_batch, "
						                + "transfer_product_code, transfer_product_name, transfer_quantity, transfer_unit,fg_status) "
						                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Inventory_transfer_fg>() {
		            public void setValues(PreparedStatement ps, Inventory_transfer_fg argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getStatus());
		                ps.setString(5, argument.getTransfer_date());
		                ps.setString(6, argument.getTransfer_from_location());
		                ps.setString(7, argument.getTransfer_to_location());
		                ps.setString(8, argument.getTransferred_by());
		                ps.setString(9, argument.getTransfer_remark());
		                ps.setString(10, argument.getTransfer_pd_code());
		                ps.setString(11, argument.getTransfer_lot_batch());
		                ps.setString(12, argument.getTransfer_product_code());
		                ps.setString(13, argument.getTransfer_product_name());
		                ps.setDouble(14, argument.getTransfer_quantity());
		                ps.setString(15, argument.getTransfer_unit());
		                ps.setString(16, argument.getFg_status());
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		
				
		

		@Override
		public void SaveIssue_sk(List<Issue_sk> subData, int id) {


		      String sql = "INSERT INTO issue_sk (edata_id, doc_code, tempId, title, ski_date, ski_type, ski_destination, "
				                + "ski_by, ski_approved_by, ski_remark, ski_pd_code, ski_lot_batch, ski_lot_batch2, ski_stock_code, ski_stock_name, "
				                + "ski_tracking_number, ski_supplier_code, ski_quantity,ski_withdrawal, ski_unit, ski_supplier_name) "
				                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";




		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Issue_sk>() {
		            public void setValues(PreparedStatement ps, Issue_sk argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getSki_date());
		                ps.setString(5, argument.getSki_type());
		                ps.setString(6, argument.getSki_destination());
		                ps.setString(7, argument.getSki_by());
		                ps.setString(8, argument.getSki_approved_by());
		                ps.setString(9, argument.getSki_remark());
		                ps.setString(10, argument.getSki_pd_code());
		                ps.setString(11, argument.getSki_lot_batch());
		                ps.setString(12, argument.getSki_lot_batch2());
		                ps.setString(13, argument.getSki_stock_code());
		                ps.setString(14, argument.getSki_stock_name());
		                ps.setString(15, argument.getSki_tracking_number());
		                ps.setString(16, argument.getSki_supplier_code());
		                ps.setDouble(17, argument.getSki_quantity());
		                ps.setDouble(18, argument.getSki_withdrawal());
		                ps.setString(19, argument.getSki_unit());
		                ps.setString(20, argument.getSki_supplier_name());
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		
		
		@Override
		public void SaveIssue_fg(List<Issue_fg> subData, int id) {


		       String sql = "INSERT INTO issue_fg (edata_id, doc_code, tempId, title, status, issue_date, issue_type, "
		                + "issue_destination, issued_by, approved_by, issued_remark, issued_pd_code, issued_lot_batch, "
		                + "issued_product_code, issued_product_name, issued_quantity, issued_unit, issued_customer_code, issued_customer_name) "
		                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Issue_fg>() {
		            public void setValues(PreparedStatement ps, Issue_fg argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getStatus());
		                ps.setString(5, argument.getIssue_date());
		                ps.setString(6, argument.getIssue_type());
		                ps.setString(7, argument.getIssue_destination());
		                ps.setString(8, argument.getIssued_by());
		                ps.setString(9, argument.getApproved_by());
		                ps.setString(10, argument.getIssued_remark());
		                ps.setString(11, argument.getIssued_pd_code());
		                ps.setString(12, argument.getIssued_lot_batch());
		                ps.setString(13, argument.getIssued_product_code());
		                ps.setString(14, argument.getIssued_product_name());
		                ps.setDouble(15, argument.getIssued_quantity());
		                ps.setString(16, argument.getIssued_unit());
		                ps.setString(17, argument.getIssued_customer_code());
		                ps.setString(18, argument.getIssued_customer_name());
		     
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		
   


		@Override
		public void SaveAdjustment_sk(List<Adjustment_sk> subData, int id) {


		     String sql = "INSERT INTO adjustment_sk (edata_id, doc_code, tempId, title, ska_date, ska_reason, "
	                   + "ska_remark, ska_type, ska_location, ska_stock_code, ska_stock_name, "
	                   + "ska_tracking_number, ska_lot_batch, ska_quantity, ska_unit,ska_pd) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";


		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Adjustment_sk>() {
		            public void setValues(PreparedStatement ps, Adjustment_sk argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getSka_date());
		                ps.setString(5, argument.getSka_reason());
		                ps.setString(6, argument.getSka_remark());
		                ps.setString(7, argument.getSka_type());
		                ps.setString(8, argument.getSka_location());
		                ps.setString(9, argument.getSka_stock_code());
		                ps.setString(10, argument.getSka_stock_name());
		                ps.setString(11, argument.getSka_tracking_number());
		                ps.setString(12, argument.getSka_lot_batch());
		                ps.setDouble(13, argument.getSka_quantity());
		                ps.setString(14, argument.getSka_unit());
		                ps.setString(15, argument.getSka_pd());
		              
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		

		@Override
		public void SaveAdjustment_fg(List<Adjustment_fg> subData, int id) {


	        String sql = "INSERT INTO adjustment_fg (edata_id, doc_code, tempId, title, adjustment_date, adjustment_reason, "
	                   + "adjustment_remark, adjustment_type, adjustment_location, adjustment_pd_code, "
	                   + "adjustment_lot_batch, adjustment_product_code, adjustment_product_name, "
	                   + "adjustment_quantity, adjustment_unit) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<Adjustment_fg>() {
		            public void setValues(PreparedStatement ps, Adjustment_fg argument) throws SQLException {
		                ps.setString(1, argument.getTempId());
		                ps.setString(2, argument.getDoc_code());
		                ps.setString(3, argument.getTitle());
		                ps.setString(4, argument.getAdjustment_date());
		                ps.setString(5, argument.getAdjustment_reason());
		                ps.setString(6, argument.getAdjustment_remark());
		                ps.setString(7, argument.getAdjustment_type());
		                ps.setString(8, argument.getAdjustment_location());
		                ps.setString(9, argument.getAdjustment_pd_code());
		                ps.setString(10, argument.getAdjustment_lot_batch());
		                ps.setString(11, argument.getAdjustment_product_code());
		                ps.setString(12, argument.getAdjustment_product_name());
		                ps.setDouble(13, argument.getAdjustment_quantity());
		                ps.setString(14, argument.getAdjustment_unit());
		        
		              
		                // เพิ่มเติมฟิลด์อื่น ๆ ถ้ามี
		            }
		        });
		}
		
		
		


		
		
		
		@Override
		public void SavePL(List<production_list> subData, int id) {

			
			System.out.println(" SavePL subData "+subData.size());
			
			
			   String idUse =  subData.stream().map(row-> String.valueOf(row.getId())).collect(Collectors.joining(","));
			    String sqls ="DELETE  FROM   production_list  WHERE tempId =? AND not find_in_set(id,?) AND edata_id IS NULL  ";
				jdbcTemplate.update(sqls,new Object[]{ id,idUse});
			
			
			
			  String sql = "INSERT INTO production_list(" +
			            "tempId, pl_code, lot_batch, product_code, product_name, " +
			            "order_quantity, start_date, end_date, processes, status, " +
			            "responsible_person, production_location, customer_code, customer_name, " +
			            "unit, item_code,department,sub_order_quantity,sub_unit,id) " +
			            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
			             + "ON DUPLICATE KEY UPDATE "
			               + "tempId = VALUES(tempId),"
			               + "pl_code = VALUES(pl_code),"
			               + "lot_batch = VALUES(lot_batch),"
			               + "product_code = VALUES(product_code),"
			               + "product_name = VALUES(product_name),"
			               + "order_quantity = VALUES(order_quantity),"
			               + "start_date = VALUES(start_date),"
			               + "end_date = VALUES(end_date),"
			               + "processes = VALUES(processes),"
			               + "responsible_person = VALUES(responsible_person),"
			               + "production_location = VALUES(production_location),"
			               + "customer_code = VALUES(customer_code),"
			               + "customer_name = VALUES(customer_name),"
			               + "unit = VALUES(unit),"
			               + "item_code = VALUES(item_code),"
			               + "department = VALUES(department),"
			               + "sub_order_quantity = VALUES(sub_order_quantity),"
			               + "sub_order_quantity = VALUES(sub_order_quantity),"
			               + "sub_unit = VALUES(sub_unit)"
			            ;

			    jdbcTemplate.batchUpdate(sql, subData, 1000,
			        new ParameterizedPreparedStatementSetter<production_list>() {
			            @Override
			            public void setValues(PreparedStatement ps, production_list item) throws SQLException {
			                ps.setInt(1, id); // edata_id จากพารามิเตอร์
			                ps.setString(2, item.getPl_code());
			                ps.setString(3, item.getLot_batch());
			                ps.setString(4, item.getProduct_code());
			                ps.setString(5, item.getProduct_name());
			                ps.setInt(6, item.getOrder_quantity());
			                ps.setString(7, item.getStart_date());
			                ps.setString(8, item.getEnd_date());
			                ps.setString(9, item.getProcesses());
			                ps.setString(10, item.getStatus());
			                ps.setString(11, item.getResponsible_person());
			                ps.setString(12, item.getProduction_location());
			                ps.setString(13, item.getCustomer_code());
			                ps.setString(14, item.getCustomer_name());
			                ps.setString(15, item.getUnit());
			                ps.setString(16, item.getItem_code());
			                
			                ps.setString(17, item.getDepartment());
			                ps.setInt(18, item.getSub_order_quantity());
			                ps.setString(19, item.getSub_unit());
			                ps.setObject(20, item.getId() == 0 ? null : item.getId()); 
			            }
			        });
		}
		
		
		
	
		@Override
		public void SavePD(List<production_directive> subData, int id) {

			
			   String idUse =  subData.stream().map(row-> String.valueOf(row.getId())).collect(Collectors.joining(","));
			    String sqls ="DELETE  FROM   production_directive  WHERE tempId =? AND not find_in_set(id,?) AND edata_id IS NULL  ";
				jdbcTemplate.update(sqls,new Object[]{ id,idUse});
			
			
		    String sql = "INSERT INTO production_directive(" +
		            "tempId, pl_code, lot_batch, product_code, product_name, " +
		            "process_step, department, quantity, start_date, end_date, " +
		            "status, responsible_person, remarks, line, actual_output, " +
		            "defective_output, item_code,id) " +
		            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
		            + "ON DUPLICATE KEY UPDATE "
		               + "tempId = VALUES(tempId),"
		               + "pl_code = VALUES(pl_code),"
		               + "lot_batch = VALUES(lot_batch),"
		               + "product_code = VALUES(product_code),"
		               + "product_name = VALUES(product_name),"
		               + "process_step = VALUES(process_step),"
		               + "department = VALUES(department),"
		               + "quantity = VALUES(quantity),"
		               + "start_date = VALUES(start_date),"
		               + "end_date = VALUES(end_date),"
		               + "status = VALUES(status),"
		               + "responsible_person = VALUES(responsible_person),"
		               + "remarks = VALUES(remarks),"
		               + "line = VALUES(line),"
		               + "actual_output = VALUES(actual_output),"
		               + "defective_output = VALUES(defective_output),"
		               + "item_code = VALUES(item_code)";

		    jdbcTemplate.batchUpdate(sql, subData, 1000,
		        new ParameterizedPreparedStatementSetter<production_directive>() {
		            @Override
		            public void setValues(PreparedStatement ps, production_directive item) throws SQLException {
		                ps.setInt(1, id); // edata_id จาก parameter
		                ps.setString(2, item.getPl_code());
		                ps.setString(3, item.getLot_batch());
		                ps.setString(4, item.getProduct_code());
		                ps.setString(5, item.getProduct_name());
		                ps.setString(6, item.getProcess_step());
		                ps.setString(7, item.getDepartment());
		                ps.setInt(8, item.getQuantity());
		                ps.setString(9, item.getStart_date());
		                ps.setString(10, item.getEnd_date());
		                ps.setString(11, item.getStatus());
		                ps.setString(12, item.getResponsible_person());
		                ps.setString(13, item.getRemarks());
		                ps.setString(14, item.getLine());
		                ps.setString(15, item.getActual_output());
		                ps.setString(16, item.getDefective_output());
		                ps.setString(17, item.getItem_code());
		                ps.setObject(18, item.getId() == 0 ? null : item.getId()); 
		            }
		        });
		}
		
		
		@Override
		public List<edata_procurement> getAllEdata_procurements_item(String tempId) {
			
			
			String sql ="SELECT  edata_procurements.tempId AS id_refer_by,edata_procurement.*  FROM edata_procurement "
					+ " LEFT JOIN edata_procurement AS edata_procurements ON edata_procurement.id = edata_procurements.id_refer  "
					+ " WHERE edata_procurement.tempId =? and edata_procurement.processed_flag = FALSE   ";

			List<edata_procurement> edata_procurements = jdbcTemplate.query(sql,new Object[]{tempId},new edata_procurementmappers());
			return edata_procurements;
		}
		
		@Override	
		public void markAsProcessed(String itemId) {
		        String sql = "UPDATE edata_procurement SET processed_flag = TRUE WHERE  tempId =?";
		        jdbcTemplate.update(sql, itemId);
		}

		@Override
		public List<bom> getAllEdata_bom_item(String tempId) {
			String sql ="SELECT  *  FROM bom  WHERE tempId =? AND edata_id IS NULL   ";
			List<bom> boms = jdbcTemplate.query(sql,new Object[]{tempId},new bommappers());
			return boms;
		}
		
		
		@Override
		public List<bom> getAllEdata_bom_item_category(String tempId,String category) {
			String sql ="SELECT  *  FROM bom  WHERE tempId =? AND bom_category =? AND edata_id IS NULL   ";
			List<bom> boms = jdbcTemplate.query(sql,new Object[]{tempId,category},new bommappers());
			return boms;
		}
		
		
		
		@Override
		public List<bom> getAllEdata_bom_item_group(int tempId) {
			String sql ="SELECT   SUM( (bom_base_quantity + bom_wastage) * bom_price ) AS bom_price"
					+ ",bom.*  FROM bom  WHERE tempId =? AND edata_id IS NULL GROUP BY bom_category    ";
			List<bom> boms = jdbcTemplate.query(sql,new Object[]{tempId},new bommappers());
			return boms;
		}
		
		
		
		
		@Override
		public List<production_standard> getAllEdata_production_standard_item(String tempId) {
			String sql ="SELECT  *  FROM production_standard  WHERE tempId =?  AND edata_id IS NULL ";
			List<production_standard> ps = jdbcTemplate.query(sql,new Object[]{tempId},new production_standardmappers());
			return ps;
		}
		
		@Override
		public void setLot_batch(String batch,int id) {
			String sql ="UPDATE production_list SET lot_batch = ? WHERE id = ? ";
			jdbcTemplate.update(sql,new Object[]{batch,id});
			
		}
		
		
		
		@Override
		public List<production_list> getAllEdata_pl_item(String tempId) {
			String sql ="SELECT  *  FROM production_list  WHERE tempId =?  AND edata_id IS NULL ";
			List<production_list> data = jdbcTemplate.query(sql,new Object[]{tempId},new production_listmappers());
			return data;
		}
		
		@Override
		public List<production_directive> getAllEdata_pd_item(String tempId) {
			String sql ="SELECT  *  FROM production_directive  WHERE tempId =?  AND edata_id IS NULL ";
			List<production_directive> data = jdbcTemplate.query(sql,new Object[]{tempId},new production_directivemappers());
			return data;
		}
		@Override
		public List<other> getAllEdata_other_item(String tempId) {
			String sql ="SELECT  *  FROM other  WHERE tempId =?  AND edata_id IS NULL ";
			List<other> data = jdbcTemplate.query(sql,new Object[]{tempId},new othermappers());
			return data;
		}
		
		
		@Override
		public List<edata_procurement> getAllEdata_procurements_item(String tempId,String type) {
			
			
			String sql ="SELECT  edata_procurements.tempId AS id_refer_by,edata_procurement.*  FROM edata_procurement "
					+ " LEFT JOIN edata_procurement AS edata_procurements ON edata_procurement.id = edata_procurements.id_refer  "
					+ " WHERE edata_procurement.tempId =? AND edata_procurement.procurement_type =?  AND edata_procurement.edata_id  IS NULL GROUP BY edata_procurement.id ";

			List<edata_procurement> edata_procurements = jdbcTemplate.query(sql,new Object[]{tempId,type},new edata_procurementmappers());
			return edata_procurements;
		}
		
		
		
		
		
		
		
		
		
		
	@Override	
	public List<edata_procurement> getAllEdata_procurements_itemstatus(String tempId,String status) {
			
			
			String sql ="SELECT  edata_procurements.tempId AS id_refer_by,edata_procurement.*  FROM edata_procurement "
					+ " LEFT JOIN edata_procurement AS edata_procurements ON edata_procurement.id = edata_procurements.id_refer  "
					+ " WHERE edata_procurement.tempId =? AND edata_procurement.procurement_status =? ";

			List<edata_procurement> edata_procurements = jdbcTemplate.query(sql,new Object[]{tempId,status},new edata_procurementmappers());
			return edata_procurements;
		}
		

		
		@Override
		public void DelEdata_procurements_item(int tempId,String type) {
			String sql ="DELETE  FROM   edata_procurement  WHERE tempId =? AND procurement_type =? AND procurement_status ='Pending' ";
			jdbcTemplate.update(sql,new Object[]{ tempId,type});
			
		}
		@Override
		public void DelBoms(int tempId) {
			
			System.out.println(" DelBoms tempId ");
			
			String sql ="DELETE  FROM   bom  WHERE tempId =? AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelPS(int tempId) {
			String sql ="DELETE  FROM   production_standard  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelOther(int tempId) {
			String sql ="DELETE  FROM   other  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		
		
		
		@Override
		public void DelGoods_receipt_sk(int tempId) {
			String sql ="DELETE  FROM   goods_receipt_sk  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelInventory_transfer_sk(int tempId) {
			String sql ="DELETE  FROM   inventory_transfer_sk  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelIssue_sk(int tempId) {
			String sql ="DELETE  FROM   issue_sk  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelAdjustment_sk(int tempId) {
			String sql ="DELETE  FROM   adjustment_sk  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		
		
		
		@Override
		public void DelGoods_receipt_fg(int tempId) {
			String sql ="DELETE  FROM   goods_receipt_fg  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelInventory_transfer_fg(int tempId) {
			String sql ="DELETE  FROM   inventory_transfer_fg  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelIssue_fg(int tempId) {
			String sql ="DELETE  FROM   issue_fg  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		@Override
		public void DelAdjustment_fg(int tempId) {
			String sql ="DELETE  FROM   adjustment_fg  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		
		
		
		
		@Override
		public void DelPL(int tempId) {
			String sql ="DELETE  FROM   production_list  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		
		@Override
		public void DelPD(int tempId) {
			String sql ="DELETE  FROM   production_directive  WHERE tempId =?  AND edata_id IS NULL  ";
			jdbcTemplate.update(sql,new Object[]{ tempId});
			
		}
		
		
		
		
		
		@Override
		public void APPEdata_procurements_item(int tempId) {
			
			
			System.err.println("APP Edata");
			System.err.println("APP Edata->tempId "+tempId);
			
			String sql ="UPDATE   edata_procurement SET  procurement_status ='Approved' WHERE tempId =?  AND   procurement_status ='Pending'   ";
			jdbcTemplate.update(sql,new Object[]{tempId});
			
		}
		@Override
		public void APPPD(edata edatas) {
			
			
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE   production_directive  SET  pd_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPOther(edata edatas) {
			
			
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE   other  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		
		@Override
		public void APPEdata_procurements(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  edata_procurements  SET  doc =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		
		@Override
		public void APPGoods_receipt_sk(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  goods_receipt_sk  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPInventory_transfer_sk(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  inventory_transfer_sk  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPIssue_sk(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  issue_sk  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPAdjustment_sk(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  adjustment_sk  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		
		
		
		
		
		@Override
		public void APPGoods_receipt_fg(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  goods_receipt_fg  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPInventory_transfer_fg(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  inventory_transfer_fg  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPIssue_fg(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  issue_fg  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		@Override
		public void APPAdjustment_fg(edata edatas) {
			System.err.println("APPPD");
			System.err.println("APPPD->ID"+edatas.getData_id());
			
			String sql ="UPDATE  adjustment_fg  SET  doc_code =? WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		
		
		
		
		
		
		
		
		@Override
		public void APPPL(edata edatas) {
			
			
			System.err.println("APPPL");
			System.err.println("APPPL->ID"+edatas.getData_id());
			
			String sql ="UPDATE   production_list  SET  pl_code = ?  WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{edatas.getDoc(),edatas.getData_id()});
			
		}
		
		
		@Override
		public void APPBom(int Id) {
			
			
			System.err.println("APPBom");
			System.err.println("APPBom->tempId "+Id);
			
			String sql ="UPDATE   bom SET  status ='Approved' WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{Id});
			
		}
		
		@Override
		public void APPPS(int Id) {
			
			
			System.err.println("APP Edata");
			System.err.println("APP Edata->tempId "+Id);
			
			String sql ="UPDATE   production_standard SET  status ='Approved' WHERE edata_id = ?   ";
			jdbcTemplate.update(sql,new Object[]{Id});
			
		}
		
		
		@Override
		public void SendEdata_procurements_item(String tempId,String type) {
			String sql ="UPDATE   edata_procurement SET  procurement_status ='Send' WHERE tempId =?  AND  procurement_type =? AND procurement_status ='Approved'  ";
			jdbcTemplate.update(sql,new Object[]{ tempId,type});
			
		}
		
		@Override
		public void ReceiveEdata_procurements_item(String id_refer,String app ) {
			
			
			System.out.println("ReceiveEdata");
			System.out.println("ReceiveEdata->id_refer "+id_refer);

			
			
			String sql ="UPDATE   edata_procurement SET  procurement_status ='Receive' WHERE  FIND_IN_SET(id,?)  AND   procurement_status !='Pending'   ";
			jdbcTemplate.update(sql,new Object[]{ id_refer});
			
			 String sqls = "select * from transaction_send where status ='Send' AND send_type =? ";
			List<transaction_send> transaction_send = jdbcTemplate.query(sqls,new Object[]{app},new transaction_sendmapper());

			transaction_send.forEach(row->{
				
				
				System.out.println("transaction_send "+row.getId());
				System.out.println("row.getEdata_id() "+row.getEdata_id());
				
				String sqled = "SELECT COUNT(*) FROM edata_procurement  where procurement_status ='Send' AND tempId =?   ";
				int count =  jdbcTemplate.queryForObject(sqled,new Object[]{row.getEdata_id()},Integer.class);
				System.out.println("count "+count);
				if(count == 0) {
					String sqleds ="UPDATE   transaction_send SET  status ='Receive'  WHERE id=?  ";
					jdbcTemplate.update(sqleds,new Object[]{row.getId()});
				}
				
			});
			
			
		}
		
		
		
		@Override
		public void ReceiveTransaction_send(int edata_id) {
			String sqleds ="UPDATE   transaction_send SET  status ='Receive'  WHERE edata_id=?  ";
			jdbcTemplate.update(sqleds,new Object[]{edata_id});
			
		}
		
		@Override
		public void save_policies(policies policies) {
		    
		    String name = policies.getName();
		    
		    // Pass -1 to prevent Java from dropping trailing empty values
		    String[] sub_processes = policies.getSub_processes_string().split(",", -1);
		    int process = policies.getProcess();
		    String[] respon = policies.getRespon().split(",", -1);
		    String[] max_approval_amount = policies.getMax_approval_amount().split(",", -1);
		    String group_id = policies.getGroup_id();
		    
		    System.out.println("---------------");
		    System.out.println("name " + name);
		    System.out.println("sub_processes length " + sub_processes.length);
		    System.out.println("respon length " + respon.length);
		    System.out.println("max_approval_amount length " + max_approval_amount.length);
		    System.out.println("---------------");

		    // Safety check to prevent the BatchUpdate from failing silently or crashing
		    if (sub_processes.length != respon.length || respon.length != max_approval_amount.length) {
		        throw new IllegalArgumentException("Array lengths do not match. Check form submission data.");
		    }

		    String sql ="INSERT INTO policies (name, sub_processes, process, respon, max_approval_amount, group_id) VALUES(?,?,?,?,?,?)";

		    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
		            
		        @Override
		        public void setValues(PreparedStatement ps, int i) throws SQLException {
		            ps.setString(1, name);  
		            
		            // Handle potential empty strings parsing to Integer safely
		            String subProc = sub_processes[i].trim();
		            ps.setInt(2, subProc.isEmpty() ? 0 : Integer.parseInt(subProc));  
		            
		            ps.setInt(3, process);
		            ps.setString(4, respon[i].replace("|", ","));
		            ps.setString(5, max_approval_amount[i].replace("|", ","));
		            ps.setString(6, group_id);
		        }

		        @Override
		        public int getBatchSize() {
		            return respon.length;
		        }
		    });
		}
		
		
		
		
		
		
		@Override
		public policies getMyPolicies(int process,String user,String groups,String department) {
			
			
		//System.out.println("user "+user);
			//System.out.println("user "+groups);
			//System.out.println("user "+department);
			
			  try {
					
					String sql ="SELECT * FROM  policies WHERE process =?"
							+ " AND (FIND_IN_SET(respon,? ) OR FIND_IN_SET(respon,? )  OR FIND_IN_SET(respon,? )  OR respon = 'ALL'  )  limit 1 ";
					policies policies = jdbcTemplate.queryForObject(sql,new Object[]{process,user,groups,department},new policiesmapper());
					return policies;
		 
			  }catch(EmptyResultDataAccessException e) {
				  		policies policies = new policies();
				  		return policies;
					 //throw new RuntimeException("file ID does not exist");
				    
			}catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one getPo with the same Id .......");
				   
			}
		
		}		
	
		@Override
		public policies getPoliciesStep(int sub_processes_id,String user,String groups,String department) {
			
			  try {
					
					String sql ="SELECT * FROM  policies WHERE sub_processes =?"
							+ " AND (FIND_IN_SET(respon,? ) OR FIND_IN_SET(respon,? )  OR FIND_IN_SET(respon,? )  OR respon = 'ALL'  )  AND process > 0 limit 1 ";
					policies policies = jdbcTemplate.queryForObject(sql,new Object[]{sub_processes_id,user,groups,department},new policiesmapper());
					return policies;
		 
			  }catch(EmptyResultDataAccessException e) {
				  		policies policies = new policies();
				  		return policies;
					 //throw new RuntimeException("file ID does not exist");
				    
			}catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one getPo with the same Id .......");
				   
			}
		
		}		
		
		@Override
		public policies getPolicies(int sub_processes_id) {
			
			  try {
					
					String sql ="SELECT * FROM   policies WHERE sub_processes =? limit 1 ";
					policies policies = jdbcTemplate.queryForObject(sql,new Object[]{sub_processes_id},new policiesmapper());
					return policies;
		 
			  }catch(EmptyResultDataAccessException e) {
				  		policies policies = new policies();
				  		return policies;
					 //throw new RuntimeException("file ID does not exist");
				    
			}catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one getPo with the same Id .......");
				   
			}
		
		}		
	
		
		@Override
		public List<policies> getAllPolicies(int sub_processes_id) {
			String sql ="SELECT * FROM   policies WHERE sub_processes =? ";
			List<policies> policies = jdbcTemplate.query(sql,new Object[]{sub_processes_id},new policiesmapper());
			return policies;
		}
		@Override
		public List<policies> getAllMainPolicies(int processes_id) {
			String sql ="SELECT * FROM   policies WHERE process =? GROUP BY  group_id ";
			List<policies> policies = jdbcTemplate.query(sql,new Object[]{processes_id},new policiesmapper());
			return policies;
		}
		
		@Override
		public List<policies> getAllGroupPolicies(String processes_id) {
			String sql ="SELECT * FROM   policies WHERE group_id =?  ";
			List<policies> policies = jdbcTemplate.query(sql,new Object[]{processes_id},new policiesmapper());
			return policies;
		}
		
		
		@Override
		public void DelPolicies_item(String group_id) {
			String sql ="DELETE  FROM   policies  WHERE group_id =? ";
			jdbcTemplate.update(sql,new Object[]{ group_id});
			
		}
		
		
		
	
		 
		@Override
		public int save_transaction_send(transaction_send transaction_sends) {
		    System.out.println("Send ----------------------> ");
		    
		    Object[] transaction_sendInfo = {
		            transaction_sends.getSend_to(),
		            transaction_sends.getSend_type(),
		            transaction_sends.getReceiver_type(),
		            transaction_sends.getCreated_by(),
		            transaction_sends.getCreated_at(),
		            transaction_sends.getStatus(),
		            transaction_sends.getComment(),
		            transaction_sends.getProcess_id(),
		            transaction_sends.getEdata_id(),
		            
		            transaction_sends.getData_nameadd(),
		            transaction_sends.getData_value1(),
		            transaction_sends.getData_value2(),
		            transaction_sends.getData_value3(),
		    };

		    
		    
		    
		    
		 
		    
		    
		    
		    transaction_send transaction_send = null;
		    
		    try {
		        String sqls = "select * from transaction_send where edata_id = ?";
		        transaction_send = jdbcTemplate.queryForObject(sqls, new Object[]{transaction_sends.getEdata_id()}, new transaction_sendmapper());
		    } catch (EmptyResultDataAccessException e) {
		        // Handle empty result case
		    } catch (IncorrectResultSizeDataAccessException e) {
		        // Handle incorrect result size case
		    }

		    if (transaction_send != null) {
		        return transaction_send.getId();
		    } else {
		        // Remove the redundant update call
		        String sql = "INSERT INTO transaction_send (send_to, send_type, receiver_type, created_by, created_at, status, comment, process_id"
		        		+ ", edata_id,data_nameadd,data_value1,data_value2,data_value3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        
		        KeyHolder keyHolder = new GeneratedKeyHolder();
		        
		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < transaction_sendInfo.length; i++) {
		                ps.setObject(i + 1, transaction_sendInfo[i]); // Set parameters
		            }
		            return ps;
		        }, keyHolder);

		        // Return the generated id
		        return keyHolder.getKey().intValue();
		    }
		}
		
		@Override
		public List<product_log> getAllProduct_log(int workflow_id) {
			String sql ="SELECT * FROM   product_log WHERE workflow_id =? GROUP BY  product_category  ";
			List<product_log> product_log = jdbcTemplate.query(sql,new Object[]{workflow_id},new product_logmapper());
			return product_log;
		}
		@Override
		public List<product_log> getAllProduct_log() {
			String sql ="SELECT * FROM   product_log  GROUP BY  product_category  ";
			List<product_log> product_log = jdbcTemplate.query(sql,new product_logmapper());
			return product_log;
		}

		@Override
		public product_log getProduct_log(int id_refer) {

		
			
			
			  try {
					String sql ="SELECT * FROM   product_log WHERE id_refer =?   ";
					product_log log = jdbcTemplate.queryForObject(sql,new Object[]{id_refer},new product_logmapper());
					return log;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
		}
		
		
		
		
		
		
		@Override
		public register_log getRegister_log(int id_refer) {
			
			  try {
					String sql ="SELECT * FROM   register_log WHERE id_refer =?   ";
					register_log log = jdbcTemplate.queryForObject(sql,new Object[]{id_refer},new register_logmapper());
					return log;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
		}
		
		
		
		@Override
		public Asset_log getAssets_log(int id_refer) {

			  try {
					String sql ="SELECT * FROM   assets_log WHERE id_refer =?   ";
					Asset_log log = jdbcTemplate.queryForObject(sql,new Object[]{id_refer},new Asset_logmapper());
					return log;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}	
		
		@Override
		public stock_log getStock_log(int id_refer) {

		
			
			
			  try {
					String sql ="SELECT * FROM   stock_log WHERE id_refer =?   ";
					stock_log log = jdbcTemplate.queryForObject(sql,new Object[]{id_refer},new stock_logmapper());
					return log;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
		}
		@Override
		public List<stock_log> getAllStock_log() {
			
			String sql ="SELECT * FROM   stock_log  GROUP BY  stock_group  limit 1000 ";
			List<stock_log> log = jdbcTemplate.query(sql,new stock_logmapper());
			return log;
		}
		
		@Override
		public List<stock_log> getAllStock_log(int workflow_id,String allowed_id) {
			
			
			
			String sql ="SELECT * FROM   stock_log WHERE workflow_id =?  OR FIND_IN_SET(workflow_id,?) GROUP BY  stock_group  ";
			List<stock_log> log = jdbcTemplate.query(sql,new Object[]{workflow_id,allowed_id},new stock_logmapper());
			return log;
		}
		
		@Override
		public List<product_log> getAllProduct_log(int workflow_id,String allowed_id) {
			
			
			
			String sql ="SELECT * FROM   product_log WHERE workflow_id =?  OR FIND_IN_SET(workflow_id,?) GROUP BY  product_category  ";
			List<product_log> product_log = jdbcTemplate.query(sql,new Object[]{workflow_id,allowed_id},new product_logmapper());
			return product_log;
		}
		
		
		
		@Override
		public List<register_log> getAllRegister_log(int workflow_id,String allowed_id) {
			String sql ="SELECT * FROM   register_log WHERE workflow_id =?  OR FIND_IN_SET(workflow_id,?)  GROUP BY  register_type  ";
			List<register_log> register_log = jdbcTemplate.query(sql,new Object[]{workflow_id,allowed_id},new register_logmapper());
			return register_log;
		}
		
		
		
		@Override
		public List<register_log> getAllRegister_log(int workflow_id) {
			String sql ="SELECT * FROM   register_log WHERE workflow_id =? GROUP BY  register_type  ";
			List<register_log> register_log = jdbcTemplate.query(sql,new Object[]{workflow_id},new register_logmapper());
			return register_log;
		}
		
		
		@Override
		public List<transaction_send> getAllTransaction_send(int process_id,String status,String send_to) {
			
		//	System.out.println(" transaction_send "+process_id);
		//	System.out.println(" status "+status);
		//	System.out.println(" send_to "+send_to);
			
			String sql ="SELECT * FROM   transaction_send  WHERE process_id =?  AND status =?  AND (  FIND_IN_SET(send_to,?) OR send_to = 'ALL' )  ORDER BY id DESC  ";
			List<transaction_send> transaction_sends = jdbcTemplate.query(sql,new Object[]{process_id,status,send_to},new transaction_sendmapper());
			return transaction_sends;
		}
		
		@Override
		public void getTransaction_send_mark(int id,int alert) {
			
			String sql ="update  transaction_send set alert= ?  WHERE id =?  ";
			jdbcTemplate.update(sql,new Object[]{alert,id});
		}
		
		@Override
		public int SaveProduction_lists(production_list production_list) {
			

			 
			Object[] production_listInfo = { 
					production_list.getEdata_id(),
					production_list.getPl_code(),
					production_list.getLot_batch(),
					production_list.getProduct_code(),
					production_list.getProduct_name(),
					production_list.getOrder_quantity(),
					production_list.getStart_date(),
					production_list.getEnd_date(),
					production_list.getProcesses(),
					production_list.getStatus(),
					production_list.getResponsible_person(),
					production_list.getProduction_location(),
					production_list.getCustomer_code(),
					production_list.getCustomer_name(),
					production_list.getUnit(),
					production_list.getItem_code(),
					
					production_list.getDepartment(),
					production_list.getSub_order_quantity(),
					production_list.getSub_unit(),
					};
		
			

	
			
			

		//	private String procurement_documenttype;
			//private String procurement_documentnumber;

			System.out.println("SaveProcurements->Edata_id  : " + production_list.getEdata_id());
			
			
			
			
			
			if(production_list.getId() > 0) {
				
			
				
				String sql = "UPDATE production_list SET  edata_id=?,pl_code=?,lot_batch=?,product_code=?,product_name=?,order_quantity=?"
						+ ",start_date=?,end_date=?,processes=?,status=?,responsible_person=?,production_location=?,customer_code=?"
						+ ",customer_name=?,unit=?,item_code=?,department=?,sub_order_quantity=?,sub_unit=? "			
						+ " WHERE id ="+production_list.getId();
				
				
				return jdbcTemplate.update(sql,production_listInfo);
				
				
			}else if(production_list.getEdata_id() > 0) {
						

				String sql = "INSERT INTO production_list( edata_id,pl_code,lot_batch,product_code,product_name,order_quantity,start_date,end_date,processes,status"
						+ ",responsible_person,production_location,customer_code,customer_name,unit,item_code,department,sub_order_quantity,sub_unit) "
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						KeyHolder keyHolder = new GeneratedKeyHolder();
							
					
						
						jdbcTemplate.update(connection -> {
						    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						    for (int i = 0; i < production_listInfo.length; i++) {
						        ps.setObject(i + 1, production_listInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
						    }
						    return ps;
						}, keyHolder);
			
						// คืนค่า id ที่ถูกสร้างขึ้น
						return keyHolder.getKey().intValue();
						
						
			}else{
				return 0;
			}
			

			
		}
		
		
		
		@Override
		public int SaveProduction_directives(production_directive production_directive) {
			


			
			
			Object[] production_directiveInfo = { 
					production_directive.getEdata_id(),
					production_directive.getPl_code(),
					production_directive.getLot_batch(),
					production_directive.getProduct_code(),
					production_directive.getProduct_name(),
					production_directive.getProcess_step(),
					production_directive.getDepartment(),
					production_directive.getQuantity(),
					production_directive.getStart_date(),
					production_directive.getEnd_date(),
					production_directive.getStatus(),
					production_directive.getResponsible_person(),
					production_directive.getRemarks(),
					production_directive.getLine(),
					production_directive.getActual_output(),
					production_directive.getDefective_output(),
					production_directive.getItem_code(),
					production_directive.getOrder_quantity(),
					production_directive.getPlan_time(),
					
					};
		
			
			
			

		//	private String procurement_documenttype;
			//private String procurement_documentnumber;

			System.out.println("SaveProcurements->Edata_id  : " + production_directive.getEdata_id());
			
			
			
			
			
			if(production_directive.getId() > 0) {
				
			
				
				String sql = "UPDATE production_directive SET edata_id=?,pl_code=?,lot_batch=?,product_code=?,product_name=?,process_step=?"
						+ ",department=?,quantity=?,start_date=?,end_date=?,status=?,responsible_person=?,remarks=?,line=?,actual_output=?,defective_output=?,item_code=?,order_quantity=?,plan_time=?"			
						+ " WHERE id ="+production_directive.getId();
				
				
				return jdbcTemplate.update(sql,production_directiveInfo);
				
				
			}else if(production_directive.getEdata_id() > 0) {
						

				String sql = "INSERT INTO production_directive(edata_id,pl_code,lot_batch,product_code,product_name,process_step"
						+ ",department,quantity,start_date,end_date,status,responsible_person,remarks,line,actual_output,defective_output,item_code,order_quantity,plan_time) "
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						KeyHolder keyHolder = new GeneratedKeyHolder();
							
						
						
						jdbcTemplate.update(connection -> {
						    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						    for (int i = 0; i < production_directiveInfo.length; i++) {
						        ps.setObject(i + 1, production_directiveInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
						    }
						    return ps;
						}, keyHolder);
			
						// คืนค่า id ที่ถูกสร้างขึ้น
						return keyHolder.getKey().intValue();
						
						
			}else{
				return 0;
			}
			

			
		}
		
		
		
		
		@Override
		public int SaveBom(bom boms) {
			
	
			
			Object[] bomsInfo = { 
					boms.getEdata_id(),
					boms.getTempId(),
					boms.getBom_name(),
					boms.getBom_description(),
					boms.getBom_base_quantity(),
					boms.getBom_base_unit(),
					boms.getBom_note(),
					"Pending",
					boms.getBom_category(),
					boms.getBom_total_cost(),
					boms.getBom_price(),
					boms.getBom_code(),
					boms.getBom_wastage(),
					
					boms.getBom_product_code(),
					boms.getBom_product_name(),
					};
		    
		
	
 
			
			
			if(boms.getId() > 0) {
				
			
				
				String sql = "UPDATE bom SET edata_id=?,tempId=?,bom_name=?,bom_description=?,bom_base_quantity=?,bom_base_unit=?,bom_note=?"
						+ ",status=?,bom_category=?,bom_total_cost=?,bom_price=?,bom_code=?,bom_wastage=?,bom_product_code=?,bom_product_name=? "			
						+ " WHERE id ="+boms.getId();
				
				
				return jdbcTemplate.update(sql,bomsInfo);
				
				
			}else if(boms.getEdata_id() > 0) {
						
				System.err.println("Save bom -> ");
				
				

				String sql = "INSERT INTO bom(edata_id,tempId,bom_name,bom_description,bom_base_quantity,bom_base_unit,bom_note,status,bom_category,bom_total_cost,bom_price,bom_code"
						+ ",bom_wastage,bom_product_code,bom_product_name)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						KeyHolder keyHolder = new GeneratedKeyHolder();
							
			
						
						jdbcTemplate.update(connection -> {
						    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						    for (int i = 0; i < bomsInfo.length; i++) {
						        ps.setObject(i + 1, bomsInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
						    }
						    return ps;
						}, keyHolder);
			
						// คืนค่า id ที่ถูกสร้างขึ้น
						return keyHolder.getKey().intValue();
						
						
			}else{
				return 0;
			}
			

			
		}
		
		
		@Override
		public int SaveProduction_standard(production_standard production_standards) {
			
			
			System.out.print("------> Production_standard ");
			
			
			Object[] production_standardInfo = { 
					production_standards.getEdata_id(),
					production_standards.getTempId(),
					production_standards.getTitle(),
					production_standards.getProcess_step(),
					production_standards.getProduct_code(),
					production_standards.getProduct_name(),
					production_standards.getTitle_string1(),
					production_standards.getTitle_string2(),
					production_standards.getTitle_string3(),
					production_standards.getTitle_string4(),
					production_standards.getTitle_string5(),
					production_standards.getTitle_number1(),
					production_standards.getTitle_number2(),
					production_standards.getTitle_number3(),
					production_standards.getTitle_number4(),
					production_standards.getTitle_number5(),
					production_standards.getNote(),
					"Pending",
					production_standards.getPs_code(),
					};
		    
			
			if(production_standards.getId() > 0) {
				
			
				
				String sql = "UPDATE production_standard SET edata_id=?,tempId=?,title=?,process_step=?,product_code=?,product_name=?,title_string1=?"
						+ ",title_string2=?,title_string3=?,title_string4=?,title_string5=?,title_number1=?,title_number2=?"
						+ ",title_number3=?,title_number4=?,title_number5=?,note=?,status=?,ps_code=?"			
						+ " WHERE id ="+production_standards.getId();
				
				
				return jdbcTemplate.update(sql,production_standardInfo);
				
				
			}else if(production_standards.getEdata_id() > 0) {
						

				String sql = "INSERT INTO production_standard(edata_id,tempId,title,process_step,product_code,product_name,title_string1"
						+ ",title_string2,title_string3,title_string4,title_string5,title_number1,title_number2"
						+ ",title_number3,title_number4,title_number5,note,status,ps_code) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						KeyHolder keyHolder = new GeneratedKeyHolder();
							
						
						
						jdbcTemplate.update(connection -> {
						    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						    for (int i = 0; i < production_standardInfo.length; i++) {
						        ps.setObject(i + 1, production_standardInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
						    }
						    return ps;
						}, keyHolder);
			
						// คืนค่า id ที่ถูกสร้างขึ้น
						return keyHolder.getKey().intValue();
						
						
			}else{
				return 0;
			}
			

			
		}
		

		@Override
		public int SaveOther(other other_info) {

		    System.out.print("------> Save OTHER");

		    Object[] otherData = {
		        other_info.getEdata_id(),
		        other_info.getDoc_code(),
		        other_info.getProduct_code(),
		        other_info.getProduct_name(),
		        other_info.getProcess_step(),
		        other_info.getLot_batch(),
		        other_info.getDepartment(),
		        other_info.getList(),
		        other_info.getEmployee(),
		        other_info.getInspection(),
		        other_info.getInspection2(),
		        other_info.getInspection3(),
		        other_info.getInspection4(),
		        other_info.getInspection5(),
		        other_info.getInspection6(),
		        other_info.getEquipment(),
		        other_info.getDate_start(),
		        
		        other_info.getGrouping(),
		        other_info.getClassification(),
		        
		        other_info.getOrder_quantity(),
		        other_info.getUnit(),
		        other_info.getReceived_today(),
		        other_info.getReceived_accumulated(),
		        other_info.getPending_received(),
		        other_info.getPaid_today(),
		        other_info.getPaid_accumulated(),
		        other_info.getPending_paid(),
		        other_info.getReference_code(),
		        other_info.getLot(),
		        // เพิ่ม field อื่น ๆ ตามที่ class other มี
		    };

	
	
		    
		    if (other_info.getId() > 0) {
		        String sql = "UPDATE other SET edata_id=?, doc_code=?, product_code=?, product_name=?, process_step=?, lot_batch=?, department=?"
		        		+ ",list=?,employee=?"
		        		+ ",inspection=?,inspection2=?,inspection3=?,inspection4=?,inspection5=?,inspection6=?"
		        		+ ",equipment=?,date_start=?,grouping=?,classification=? "
		        		+ ",order_quantity=?,unit=?,received_today=?,received_accumulated=?,pending_received=?,paid_today=?,paid_accumulated=?,pending_paid=?,reference_code=?,lot=? "
		                   + "WHERE id = " + other_info.getId();

		        return jdbcTemplate.update(sql, otherData);

		    } else if (other_info.getEdata_id() > 0) {
		        String sql = "INSERT INTO other(edata_id, doc_code, product_code, product_name, process_step, lot_batch, department,list,employee"
		        		+ ",inspection,inspection2,inspection3,inspection4,inspection5,inspection6"
		        		+ ",equipment,date_start,grouping,classification"
		        		+ ",order_quantity,unit,received_today,received_accumulated,pending_received,paid_today,paid_accumulated,pending_paid,reference_code,lot) "
		                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
		                   + ",?,?,?,?,?,?,?,?,?,?)";
		        

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < otherData.length; i++) {
		                ps.setObject(i + 1, otherData[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();

		    } else {
		        return 0;
		    }
		}
		
		@Override
		public int SaveQci(qci qci_info) {

		    System.out.print("------> Save QCI");

		    Object[] qciData = {
		        qci_info.getEdata_id(),
		        qci_info.getQci_code(),
		        qci_info.getProduct_code(),
		        qci_info.getProduct_name(),
		        qci_info.getProcess_step(),
		        qci_info.getQuantity(),
		        qci_info.getStart_date_time(),
		        qci_info.getInspection(),
		        qci_info.getNote(),
		        
		        qci_info.getResults_string1(),
		        qci_info.getResults_string2(),
		        qci_info.getResults_string3(),
		        qci_info.getResults_string4(),
		        qci_info.getResults_string5(),
		        qci_info.getResults_number1(),
		        qci_info.getResults_number2(),
		        qci_info.getResults_number3(),
		        qci_info.getResults_number4(),
		        qci_info.getResults_number5(),
		        
		        
		        qci_info.getStock_code(),
		        qci_info.getStock_list(),
		    };
		    

		    
		    
		    
		    
		    if (qci_info.getId() > 0) {
		        String sql = "UPDATE qci SET edata_id=?, qci_code=?, product_code=?, product_name=?, process_step=?, quantity=?, start_date_time=?"
		        		+ ", inspection=?, note=?,results_string1=?,results_string2=?,results_string3=?,results_string4=?,results_string5=?"
		        		+ ",results_number1=?,results_number2=?,results_number3=?,results_number4=?,results_number5=?,stock_code=?,stock_list=? "
		                   + "WHERE id = " + qci_info.getId();

		        return jdbcTemplate.update(sql, qciData);

		    } else if (qci_info.getEdata_id() > 0) {
		        String sql = "INSERT INTO qci(edata_id, qci_code, product_code, product_name, process_step, quantity, start_date_time, inspection, note"
		        		+ ",results_string1,results_string2,results_string3,results_string4,results_string5,results_number1,results_number2,results_number3,results_number4,results_number5,stock_code,stock_list) "
		                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < qciData.length; i++) {
		                ps.setObject(i + 1, qciData[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();

		    } else {
		        return 0;
		    }
		}
		
		
		
		@Override
		public int SaveEdata_production_records(edata_production_record edata_production_records) {
			
	
			
			Object[] edata_production_recordsInfo = { 
					edata_production_records.getEdata_id(),
					edata_production_records.getLot_batch(),
					edata_production_records.getProduct_code(),
					edata_production_records.getProduct_name(),
					edata_production_records.getProcess_step(),
					edata_production_records.getDepartment(),
					edata_production_records.getOrder_quantity(),
					edata_production_records.getActual_output(),
					edata_production_records.getDefective_output(),
					edata_production_records.getRecord_date(),
					edata_production_records.getRecorder(),
					edata_production_records.getOperator(),
					edata_production_records.getLine(),
					edata_production_records.getInspection_result(),
					edata_production_records.getRemarks(),
					edata_production_records.getStatus(),
					edata_production_records.getShift(),
					edata_production_records.getUnit_of_measure(),
					edata_production_records.getStart_date_time(),
					edata_production_records.getEnd_date_time(),
					edata_production_records.getMachine_id(),
					edata_production_records.getItem_code(),
					
					
					edata_production_records.getDefective_output_issues(),
					edata_production_records.getDowntime_title(),
					edata_production_records.getSpeed(),
					edata_production_records.getSetup_time(),
					edata_production_records.getDowntime(),
					edata_production_records.getReference_code(),
					};
		    
		
			 
	

		//	private String procurement_documenttype;
			//private String procurement_documentnumber;

			System.out.println("SaveProcurements->Edata_id  : " + edata_production_records.getEdata_id());
			
			
			
			
			
			if(edata_production_records.getId() > 0) {
				
			
				
				String sql = "UPDATE edata_production_record SET edata_id=?,lot_batch=?,product_code=?,product_name=?,process_step=?,department=?,order_quantity=?,"
						+ "actual_output=?,defective_output=?,record_date=?,recorder=?"
						+ "	,operator=?,line=?,inspection_result=?,remarks=?,status=?,shift=?,unit_of_measure=?,"
						+ "start_date_time=?,end_date_time=?,machine_id=?,item_code=?"
						+ ",defective_output_issues=?,downtime_title=?,speed=?,setup_time=?,downtime=?,reference_code=? "			
						+ " WHERE id ="+edata_production_records.getId();
				
				
				return jdbcTemplate.update(sql,edata_production_recordsInfo);
				
				
			}else if(edata_production_records.getEdata_id() > 0) {
						

				String sql = "INSERT INTO edata_production_record(edata_id,lot_batch,product_code,product_name,process_step,department,order_quantity,"
						+ "	actual_output,defective_output,record_date,recorder"
						+ ",operator,line,inspection_result,remarks,status,shift,unit_of_measure,"
						+ "start_date_time,end_date_time,machine_id,item_code,defective_output_issues,downtime_title,speed,setup_time,downtime,reference_code)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						KeyHolder keyHolder = new GeneratedKeyHolder();
							
						
						
						jdbcTemplate.update(connection -> {
						    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						    for (int i = 0; i < edata_production_recordsInfo.length; i++) {
						        ps.setObject(i + 1, edata_production_recordsInfo[i]); // ตั้งค่าพารามิเตอร์ตามลำดับ
						    }
						    return ps;
						}, keyHolder);
			
						// คืนค่า id ที่ถูกสร้างขึ้น
						return keyHolder.getKey().intValue();
						
						
			}else{
				return 0;
			}
			

			
		}
		
		
		@Override
		public Issue_sk getIssue_sk(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM issue_sk WHERE edata_id =? limit 1";
					Issue_sk data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Issue_skmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		@Override
		public Issue_fg getIssue_fg(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM issue_fg WHERE edata_id =? limit 1";
					Issue_fg data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Issue_fgmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		@Override
		public Inventory_transfer_fg getInventory_transfer_fg(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM inventory_transfer_fg WHERE edata_id =? limit 1";
					Inventory_transfer_fg data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Inventory_transfer_fgmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		@Override
		public Inventory_transfer_sk getInventory_transfer_sk(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM inventory_transfer_sk WHERE edata_id =? limit 1";
					Inventory_transfer_sk data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Inventory_transfer_skmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		@Override
		public Goods_receipt_fg getGoods_receipt_fg(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			  try {
					String sql = "SELECT * FROM goods_receipt_fg WHERE edata_id =? limit 1";
					Goods_receipt_fg data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Goods_receipt_fgmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		@Override
		public Goods_receipt_sk getGoods_receipt_sk(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			  try {
					String sql = "SELECT * FROM goods_receipt_sk WHERE edata_id =? limit 1";
					Goods_receipt_sk data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Goods_receipt_skmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		@Override
		public Adjustment_sk getAdjustment_sk(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			  try {
					String sql = "SELECT * FROM adjustment_sk WHERE edata_id =? limit 1";
					Adjustment_sk data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Adjustment_skmappers());
					return data;
			  }catch(EmptyResultDataAccessException e) {
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		@Override
		public Adjustment_fg getAdjustment_fg(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			  try {
					String sql = "SELECT * FROM adjustment_fg WHERE edata_id =? limit 1";
					Adjustment_fg data  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new Adjustment_fgmappers());
					return data;
			  }catch(EmptyResultDataAccessException e) {
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		
		@Override
		public production_list getProduction_lists(int edaia_id) {

			System.out.print("geteData_register edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM production_list WHERE edata_id =? limit 1";
					production_list register  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new production_listmappers());
					return register;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		@Override
		public production_directive getProduction_directives(int edaia_id) {

			System.out.println("geteData_register edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM production_directive WHERE edata_id =? limit 1";
					production_directive register  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new production_directivemappers());
					return register;
					
			  }catch(EmptyResultDataAccessException e) {

				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		@Override
		public edata_production_record getEdata_production_records(int edaia_id) {

			System.out.println("edata_production_record edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM edata_production_record WHERE edata_id =? limit 1";
					edata_production_record register  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new edata_production_recordmappers());
					return register;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
	
		
		
		
		
		
		@Override
		public product_master getProduct_master(int id) {

	
			
			  try {
					String sql = "SELECT * FROM product_master WHERE id =? limit 1";
					product_master data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new product_mastermappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		
		
		
		@Override
		public bom getBomId(int id) {

	
			
			  try {
					String sql = "SELECT * FROM bom WHERE id =? limit 1";
					bom bom  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new bommappers());
					return bom;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		@Override
		public production_standard getPsId(int id) {

	
			
			  try {
					String sql = "SELECT * FROM production_standard WHERE id =? limit 1";
					production_standard ps  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new production_standardmappers());
					return ps;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		@Override
		public bom getBom(int edaia_id) {

			System.out.print("getBom edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM bom WHERE edata_id =? limit 1";
					bom bom  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new bommappers());
					return bom;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		@Override
		public production_standard getProduction_standard(int edaia_id) {

		
			System.out.print("getProduction_standard edata_id "+edaia_id);
			
			  try {
					String sql = "SELECT * FROM production_standard WHERE edata_id =? limit 1";
					production_standard ps  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new production_standardmappers());
					return ps;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		@Override
		public qci getQci(int edaia_id) {

			System.out.print("qci edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM qci WHERE edata_id =? limit 1";
					qci ps  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new qcimappers());
					return ps;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		@Override
		public other getOther(int edaia_id) {

			System.out.print("getOther edata_id "+edaia_id);
			
			
			  try {
					String sql = "SELECT * FROM other WHERE edata_id =? limit 1";
					other ps  =  jdbcTemplate.queryForObject(sql,new Object[]{edaia_id},new othermappers());
					return ps;
					
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
		}
		
		
		
		@Override
		public List<edata> getAllTraceability(String sub_process,int process_id, search_workflowDTO search) {

			System.out.println(" getAllTraceability sub_process  "+sub_process);
			
			String start = Otherservice.DefaultValueString(search.getSearch_date());
			String end = Otherservice.DefaultValueString(search.getSearch_date_end());
			String production_directive = Otherservice.DefaultValueString(search.getSearch_pd()); 
			String batch_material = Otherservice.DefaultValueString(search.getSearch_batch_material());
			String batch =  Otherservice.DefaultValueString(search.getSearch_batch());
			
			
			
			
			List<edata> edata = new ArrayList<edata>();
			
			
			List<String> subProcessList = Arrays.asList(sub_process.split(","));
			
			
			
			if(subProcessList.contains("RECEIPT_SK")) {
				
				String sqls ="SELECT goods_receipt_sk.*,edata.*  FROM   edata LEFT JOIN goods_receipt_sk "
						+ "ON edata.data_id =  goods_receipt_sk.edata_id WHERE  goods_receipt_sk.id > 0     "
						+ "order by id desc limit 100 ";
				List<edata> edata4 = jdbcTemplate.query(sqls,new traceabilityedata_skmapper());
				edata4.forEach(item ->{ 
				     item.setTitle("RECEIPT_SK");
					item.setIssue_type("รับเข้า");
				});
				
				
				List<edata> raw =  edata4.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
				  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
                  .filter(row -> {
                	    if (start.equals("-")) return true;
                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
                	    LocalDate searchDate = LocalDate.parse(start);

                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
                	})
                  .filter(row -> {
                	    if (end.equals("-")) return true;

                	    String endTarget = row.getEnd();
                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
                	        endTarget = row.getStart();
                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
                	    }

                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
                	    LocalDate searchDate = LocalDate.parse(end);

                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
                	})
		
			    .collect(Collectors.toList());
				
				edata.addAll(raw);
			
				
				
			}
			
			if(subProcessList.contains("TRANSFER_SK")) {
				
				String sqls ="SELECT inventory_transfer_sk.*,edata.*   FROM   edata LEFT JOIN inventory_transfer_sk "
						+ "ON edata.data_id =  inventory_transfer_sk.edata_id WHERE  inventory_transfer_sk.id > 0    "
						+ "order by id desc limit 100 ";
				
				List<edata>  edata5 = jdbcTemplate.query(sqls,new traceabilityedata_skmapper());
				edata5.forEach(item ->{
					item.setTitle("TRANSFER_SK");
					item.setIssue_type("โอนย้าย");
				} );
				List<edata> raw =  edata5.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());
						
						edata.addAll(raw);
				
				
				
				
				
				
				
				
			}
		   if(subProcessList.contains("ADJUST_SK")) {
				
				String sqls ="SELECT adjustment_sk.*,edata.*   FROM   edata LEFT JOIN adjustment_sk "
						+ "ON edata.data_id =  adjustment_sk.edata_id  WHERE  adjustment_sk.id > 0   "
						+ "order by id desc limit 100 ";
				List<edata>  edata6 = jdbcTemplate.query(sqls,new traceabilityedata_skmapper());
				edata6.forEach(
						item ->{
							item.setTitle("ADJUST_SK");
							item.setIssue_type("ปรับยอด");
						} 
						);
				
				List<edata> raw =  edata6.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());
						
						edata.addAll(raw);
		
				
			}	
			if(subProcessList.contains("ISSUE_SK")) {
				
				String sqls ="SELECT issue_sk.*,edata.* FROM   edata LEFT JOIN issue_sk "
						+ "ON edata.data_id =  issue_sk.edata_id  WHERE  issue_sk.id > 0 "
						+ "order by id desc limit 100 ";
	
				List<edata>  edata7 = jdbcTemplate.query(sqls,new traceabilityedata_skmapper());
				edata7.forEach(item -> item.setTitle("ISSUE_SK"));
				List<edata> raw =  edata7.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());
						
						edata.addAll(raw);
				
				System.out.println(" ISSUE_SK  "+edata7.size());
			}
			
			
			List<String> blacklist = Arrays.asList("RECEIPT_SK", "ISSUE_SK", "ADJUST_SK", "TRANSFER_SK","RECEIPT_FG", "ISSUE_FG", "ADJUST_FG", "TRANSFER_FG");

			List<String> ProcessList = Arrays.stream(sub_process.split(","))
			    .map(String::trim) // ลบช่องว่างซ้ายขวา ถ้ามี
			    .filter(s -> !blacklist.contains(s))
			    .collect(Collectors.toList());
			
		
			
			
			for(String Process_List : ProcessList){
				List<edata> ProcessListedata = new ArrayList<edata>();
				edata run_edata;
			
			String sql ="SELECT edata_production_record.*,edata.* FROM   edata LEFT JOIN edata_production_record "
					+ "ON edata.data_id =  edata_production_record.edata_id   WHERE edata.process_id = ?  AND FIND_IN_SET(edata_production_record.process_step,?)  "
					+ " order by id desc limit 100 ";
			List<edata> edata1 = jdbcTemplate.query(sql,new Object[]{process_id,Process_List},new traceabilityedatamapper());
			
			String sql2 ="SELECT qci.*,edata.* FROM   edata LEFT JOIN qci "
						+ "ON edata.data_id =  qci.edata_id   WHERE edata.process_id = ?  AND FIND_IN_SET(qci.process_step,?) "
						+ " order by id desc limit 100  ";
		    List<edata> edata2 = jdbcTemplate.query(sql2,new Object[]{process_id,Process_List},new traceabilityedatamapper());
		

			String sql3 ="SELECT other.*,edata.*  FROM   edata LEFT JOIN other "
					+ "ON edata.data_id =  other.edata_id   WHERE edata.process_id = ?  AND FIND_IN_SET(other.process_step,?)  "
					+ "order by id desc limit 100 ";
			List<edata> edata3 = jdbcTemplate.query(sql3,new Object[]{process_id,Process_List},new traceabilityedatamapper());
			
			
			
			ProcessListedata.addAll(edata1);
			ProcessListedata.addAll(edata2);
			ProcessListedata.addAll(edata3);
			
			
			List<edata> raw =  ProcessListedata.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
					  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
	                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
	                  .filter(row -> {
	                	    if (start.equals("-")) return true;
	                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

	                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
	                	    LocalDate searchDate = LocalDate.parse(start);

	                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
	                	})
	                  .filter(row -> {
	                	    if (end.equals("-")) return true;

	                	    String endTarget = row.getEnd();
	                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
	                	        endTarget = row.getStart();
	                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
	                	    }

	                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
	                	    LocalDate searchDate = LocalDate.parse(end);

	                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
	                	})
			
				    .collect(Collectors.toList());
			
			
			
			if(raw.size() == 0 ) {
				run_edata = new edata();
				run_edata.setTitle(Process_List);
				run_edata.setData_nameadd("-");
				raw.add(run_edata);
			}else{
					
					Double total = ProcessListedata.stream()
						    .map(row ->Otherservice.DoubleValueOf( row.getGood_production_output()))
						    .filter(Objects::nonNull)
						    .reduce(0.0, Double::sum);

					
	
					run_edata = new edata();
					run_edata.setTitle(Process_List);
					run_edata.setGood_production_output(" Total "+String.format("%.2f",  total));
					raw.add(run_edata);
				}
				
			
			
				edata.addAll(raw);

			}
			
			
			
			if(subProcessList.contains("RECEIPT_FG")) {
				
				edata run_edata;
				
				String sqls ="SELECT goods_receipt_fg.*,edata.*  FROM   edata LEFT JOIN goods_receipt_fg "
						+ "ON edata.data_id =  goods_receipt_fg.edata_id WHERE  goods_receipt_fg.id > 0     "
						+ "order by id desc limit 100 ";
				List<edata> edata4 = jdbcTemplate.query(sqls,new traceabilityedata_fgmapper());
				edata4.forEach(item ->{ 
					item.setTitle("RECEIPT_FG");
					item.setIssue_type("รับเข้า");
				});
				
			
				
				
				List<edata> raw =  edata4.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());
						
				
				Double sum = raw.stream()
					    .map(row ->Otherservice.DoubleValueOf( row.getQuantity()))
					    .filter(Objects::nonNull)
					    .reduce(0.0, Double::sum);	
				run_edata = new edata();
				run_edata.setTitle("RECEIPT_FG");
				run_edata.setQuantity(" Total "+String.format("%.2f", sum));
				raw.add(run_edata);
				
				
						edata.addAll(raw);
			
				
				
			}
			
			if(subProcessList.contains("TRANSFER_FG")) {
				edata run_edata;
				String sqls ="SELECT inventory_transfer_fg.*,edata.*   FROM   edata LEFT JOIN inventory_transfer_fg "
						+ "ON edata.data_id =  inventory_transfer_fg.edata_id WHERE  inventory_transfer_fg.id > 0    "
						+ "order by id desc limit 100 ";
				
				List<edata>  edata5 = jdbcTemplate.query(sqls,new traceabilityedata_fgmapper());
				edata5.forEach(item ->{
					item.setTitle("TRANSFER_FG");
					item.setIssue_type("โอนย้าย");
				} );
				
		

				List<edata> raw =  edata5.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());
				
				Double sum = raw.stream()
					    .map(row ->Otherservice.DoubleValueOf( row.getQuantity()))
					    .filter(Objects::nonNull)
					    .reduce(0.0, Double::sum);	
				run_edata = new edata();
				run_edata.setTitle("TRANSFER_FG");
				run_edata.setQuantity(" Total "+String.format("%.2f", sum));
				raw.add(run_edata);
				
				
				
						edata.addAll(raw);
				
			}
		   if(subProcessList.contains("ADJUST_FG")) {
				
				edata run_edata;
			   
				String sqls ="SELECT adjustment_fg.*,edata.*   FROM   edata LEFT JOIN adjustment_fg "
						+ "ON edata.data_id =  adjustment_fg.edata_id  WHERE  adjustment_fg.id > 0   "
						+ "order by id desc limit 100 ";
				List<edata>  edata6 = jdbcTemplate.query(sqls,new traceabilityedata_fgmapper());
				edata6.forEach(
						item ->{
							item.setTitle("ADJUST_FG");
							item.setIssue_type("ปรับยอด");
						} 
						);
				
		
				List<edata> raw =  edata6.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());

		
				Double sum = raw.stream()
					    .map(row ->Otherservice.DoubleValueOf( row.getQuantity()))
					    .filter(Objects::nonNull)
					    .reduce(0.0, Double::sum);	
				run_edata = new edata();
				run_edata.setTitle("ADJUST_FG");
				run_edata.setQuantity(" Total "+String.format("%.2f", sum));
				raw.add(run_edata);
				
						edata.addAll(raw);
				
				
				
	
				
			}	
			if(subProcessList.contains("ISSUE_FG")) {
				
				edata run_edata;
				
				String sqls ="SELECT issue_fg.*,edata.* FROM   edata LEFT JOIN issue_fg "
						+ "ON edata.data_id =  issue_fg.edata_id  WHERE  issue_fg.id > 0 "
						+ "order by id desc limit 100 ";
	
				List<edata>  edata7 = jdbcTemplate.query(sqls,new traceabilityedata_fgmapper());
				edata7.forEach(item -> item.setTitle("ISSUE_FG"));
				
				
				
	
			
				List<edata> raw =  edata7.stream().filter(row -> !batch.isEmpty() && batch.equals(row.getBatch()))
						  .filter(row -> production_directive.equals("-") || production_directive.equals(row.getProduction_directive()))
		                  .filter(row ->  batch_material.equals("-")  || batch_material.equals(row.getBatch_material()))
		                  .filter(row -> {
		                	    if (start.equals("-")) return true;
		                	    if (Otherservice.IsStringDate(row.getStart()) == false) return true;

		                	    LocalDate rowDate = LocalDate.parse(row.getStart().substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(start);

		                	    return !rowDate.isBefore(searchDate); // rowDate >= searchDate
		                	})
		                  .filter(row -> {
		                	    if (end.equals("-")) return true;

		                	    String endTarget = row.getEnd();
		                	    if (endTarget == null || !Otherservice.IsStringDate(endTarget)) {
		                	        endTarget = row.getStart();
		                	        if (endTarget == null || !Otherservice.IsStringDate(endTarget)) return true;
		                	    }

		                	    LocalDate rowDate = LocalDate.parse(endTarget.substring(0, 10));
		                	    LocalDate searchDate = LocalDate.parse(end);

		                	    return !rowDate.isAfter(searchDate); // rowDate <= searchDate
		                	})
				
					    .collect(Collectors.toList());
				
				Double sum = raw.stream()
					    .map(row ->Otherservice.DoubleValueOf( row.getQuantity()))
					    .filter(Objects::nonNull)
					    .reduce(0.0, Double::sum);	

				
				run_edata = new edata();
				run_edata.setTitle("ISSUE_FG");
				run_edata.setQuantity(" Total "+String.format("%.2f", sum));
				raw.add(run_edata);
				
			   edata.addAll(raw);
				
				
				
			}
			
			
			
		
			return edata;
		}

		
		
		@Override
		public int CheckGoal(String date_due, Goal row) {
		    System.out.println("Stat   -----------CheckGoal--------------");
			
			String maindate = row.getStart_date().substring(0, 10);
			String maindate2 = row.getEnd_date() == null ? row.getStart_date().substring(0, 10) : row.getEnd_date().substring(0, 10);
			
	        String date_start = null;
	        String date_end = null;
	        LocalDate dueDate = LocalDate.parse(date_due);
	        LocalDate startDate = LocalDate.parse(maindate);

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


	        
	        
			switch (row.getFrequency()) {
			    case "EVERY_DAY":
			    	
			    	   System.out.println("Stat   -----------CheckGoal EVERY_DAY --------------");
			        // The date_start and date_end are the same as date_due
			        date_start = date_due;
			        date_end = date_due;
			        break;

			    case "EVERY_MONTH":
			        // Ensure date_start is no earlier than maindate, and date_end is within the same month
			
				    System.out.println("Stat   -----------CheckGoal EVERY_MONTH --------------");
			        LocalDate firstOfMonth = dueDate.withDayOfMonth(1); // Start of the current month
			        LocalDate endOfMonth = dueDate.withDayOfMonth(dueDate.lengthOfMonth()); // End of the current month

			        // Ensure the start date is no earlier than maindate and no later than date_due
			        if (startDate.isBefore(firstOfMonth)) {
			            startDate = firstOfMonth;
			        }
			        if (startDate.isAfter(dueDate)) {
			            startDate = dueDate;
			        }

			        // Ensure the end date is no later than date_due
			        LocalDate date_start_month = startDate;
			        LocalDate date_end_month = endOfMonth.isBefore(startDate) ? startDate : endOfMonth;
			        
				   	date_start = date_start_month.format(formatter);
				    date_end = date_end_month.format(formatter);
				    System.out.println("Stat   -----------CheckGoal EVERY_MONTH  END--------------");
			        break;

			    case "EVERY_WEEK":
			        // Ensure date_start is no earlier than maindate, and date_end is within the same week
			        LocalDate weekStart = dueDate.minusDays(dueDate.getDayOfWeek().getValue() - 1); // Start of the current week
			        LocalDate weekEnd = weekStart.plusDays(6); // End of the current week

			        if (startDate.isBefore(weekStart)) {
			            startDate = weekStart;
			        }
			        if (startDate.isAfter(dueDate)) {
			            startDate = dueDate;
			        }

			        LocalDate date_start_week = startDate;
			        LocalDate date_end_week = weekEnd.isBefore(dueDate) ? weekEnd : dueDate;

			        
			      	date_start = date_start_week.format(formatter);
				    date_end = date_end_week.format(formatter);
			        break;

			    case "EVERY_YEAR":
			        // Ensure date_start is no earlier than maindate, and date_end is within the same year
			        LocalDate yearStart = dueDate.withDayOfYear(1); // Start of the current year
			        LocalDate yearEnd = dueDate.withDayOfYear(dueDate.lengthOfYear()); // End of the current year

			        if (startDate.isBefore(yearStart)) {
			            startDate = yearStart;
			        }
			        if (startDate.isAfter(dueDate)) {
			            startDate = dueDate;
			        }

			        LocalDate date_start_year = startDate;
			        LocalDate date_end_year = yearEnd.isBefore(dueDate) ? yearEnd : dueDate;

			        
			      	date_start = date_start_year.format(formatter);
				    date_end = date_end_year.format(formatter);
			        
			        break;

			    default:
			        date_start = maindate;
			        date_end = maindate2;
			        break;
			}
			

			
			

	
	    	if(row.getTaskBehaviortypes().equals("FIXED_REPEAT")) {
				//Count condition 
				// 1 condition
				// ComPlete File tag
				//In Range Date
				//ComPlete filde (Group)				
	    		
	    	 return	UploadPer(date_start,date_end,row,"FIXED_REPEAT");
	    	 
	    	}else {

				// 2 condition
				// ComPlete File tag
				//In Range Date
				//ComPlete filde 
	    	 return	UploadPer(date_start,date_end,row,"NON_REPEAT_RANDOM");
	    	}
		}



		private int UploadPer(String date_start, String date_end, Goal row,String condition) {
			
			  System.out.println("Stat   -----------UploadPer--------------");
			  
			  
			String sql = "";
			StringBuilder Bulider = new StringBuilder();
			StringBuilder Bulider2 = new StringBuilder();
			String HaveColumns = "";
			
		
			String Start_time = row.getStart_time() == null ? "00:00" : row.getStart_time();
			String End_time   = row.getEnd_time()   == null ? "23:59" : row.getEnd_time();
			
			date_start  = date_start+" "+Start_time;
			date_end  = date_end+" "+End_time;
			
			String by_user  ="";
			if(row.getResponsible() != null) {
				by_user =" AND edata.data_idadd = ? ";
			  }
			
			switch (row.getTables().toLowerCase()) {
			    case "other":
			        HaveColumns = "product_name,lot_batch,process_step,department,line,stock_list,employee,register_name";
			        Bulider.append(" SELECT  id FROM other LEFT JOIN edata ON edata.data_id =  other.edata_id  WHERE  edata.process_id =?  "
			        		+ "AND  edata.data_dateupdate >=? AND  edata.data_dateupdate <=?  AND edata.data_file_ck = 1  "+by_user);
			        break;
			    case "product":
			        HaveColumns = "product_name";
			        Bulider.append(" SELECT  id FROM edata_product LEFT JOIN edata ON edata.data_id =  edata_product.edata_id  WHERE  edata.process_id =? "
			        		+ " AND  edata.data_dateupdate >=? AND  edata.data_dateupdate <=?  AND edata.data_file_ck = 1  "+by_user);
			        
			        break;    
			    case "register":
			        HaveColumns = "register_name";
			        Bulider.append(" SELECT  id  FROM edata_register LEFT JOIN edata ON edata.data_id =  edata_register.edata_id  WHERE  edata.process_id =?  "
			        		+ "AND  edata.data_dateupdate >=? AND  edata.data_dateupdate <=?  AND edata.data_file_ck = 1   "+by_user);
			        
			        break;    
			    case "qci":
			        HaveColumns = "product_code,product_name,stock_list,stock_code";
			        Bulider.append(" SELECT  id  FROM qci LEFT JOIN edata ON edata.data_id =  qci.edata_id  WHERE  edata.process_id =?  "
			        		+ "AND  edata.data_dateupdate >=? AND  edata.data_dateupdate <=?  AND edata.data_file_ck = 1   "+by_user);
			        
			        break;    
			    case "production_record":
			        HaveColumns = "product_name,lot_batch,process_step,department,line,stock_list";
			        Bulider.append(" SELECT  id  FROM edata_production_record LEFT JOIN edata ON edata.data_id =  edata_production_record.edata_id  WHERE  edata.process_id =?  "
			        		+ "AND  edata.data_dateupdate >=? AND  edata.data_dateupdate <=?  AND edata.data_file_ck = 1   "+by_user);
			        
			        break;        
			    case "upload_pn":
			        HaveColumns = "";
			        Bulider.append(" SELECT *  FROM report_pn  WHERE   report_pn_idrefer =?  "
			        		+ "AND  report_pn_date >=? AND  report_pn_date <=?  AND  report_pn_add =?  ");
			        
			        break;        
			        
			   
			        
			    default:
			       return 0;

			}

	
			// Validate columns before building query
			String ref1 = row.getReference_category();
			String ref2 = row.getReference_category2();
			String ref3 = row.getReference_category3();

			// Reference_category (ref1)
			if (ref1 != null && hasColumn(ref1, HaveColumns)) {
			    Bulider.append(" AND ").append(ref1).append(" IS NOT NULL");
			    
			    if(condition.equals("FIXED_REPEAT")) {   Bulider2.append(" GROUP BY ").append(ref1);}
			 
			}

			// Reference_category2 (ref2) requires ref1
			if (ref2 != null && ref1 != null && hasColumn(ref2, HaveColumns)) {
			    Bulider.append(" AND ").append(ref2).append(" IS NOT NULL");
			  
			    if(condition.equals("FIXED_REPEAT")) {  Bulider2.append(",").append(ref2); }
			}

			// Reference_category3 (ref3) requires ref2
			if (ref3 != null && ref2 != null && hasColumn(ref3, HaveColumns)) {
			    Bulider.append(" AND ").append(ref3).append(" IS NOT NULL");
	
			    if(condition.equals("FIXED_REPEAT")) {  Bulider2.append(",").append(ref3); }
			}
			

			  int data = 0;
			  if(row.getResponsible() != null) {
					sql = "SELECT COUNT(*) FROM ("+Bulider.toString()+Bulider2.toString()+") AS temp ";
					data = jdbcTemplate.queryForObject(sql,new Object[]{row.getProcesses_id(),date_start,date_end,row.getResponsible()},Integer.class);
			  }else {
					sql = "SELECT COUNT(*) FROM ("+Bulider.toString()+Bulider2.toString()+") AS temp ";
				  	data = jdbcTemplate.queryForObject(sql,new Object[]{row.getProcesses_id(),date_start,date_end},Integer.class);
			  }
			  
			
			  System.out.println("sql "+sql);
			  System.out.println("getProcesses_id "+row.getProcesses_id());
			  System.out.println("date_start "+date_start);
			  System.out.println("date_end "+date_end);
			
			  System.out.println("data "+data);
			
			
				if(data >  0 ) {				
					   if (data <= 0 || row.getQuantity_per_action() <= 0) {
					        return 0;
					    }
					    double progress = ((double) data / row.getQuantity_per_action()) * 100;

					    // ถ้ามากกว่า 100 ก็ให้สูงสุดแค่ 100
					    if (progress > 100) {
					        return 100;
					    }

					    return (int) progress;
				}else {
					return 0;
				}
		}

		

		@Override
		public void setTitle(int id,String title) {
			
			System.out.println(" UPDATE Title "+id);
			
			String sql = "UPDATE edata SET data_title = ?  WHERE data_id = ? ";
			 jdbcTemplate.update(sql,new Object[] {title,id});
		}
		
		@Override
		public void setTitleAndIdrefer(int id,String title,int id_refer) {
			
			System.out.println(" UPDATE Title "+id);
			
			String sql = "UPDATE edata SET data_title = ?,id_refer=?  WHERE data_id = ? ";
			 jdbcTemplate.update(sql,new Object[] {title,id_refer,id});
		}
		
		
		@Override
		public void setCheckFile(int id,int data_file_ck) {
			String sql ="UPDATE edata SET data_file_ck =?   WHERE data_id =? ";
			jdbcTemplate.update(sql,new Object[]{data_file_ck,id});
			
		}
		
		
		
		@Override
		public List<register_log> getAllRegister_logByidAdd(int process_id,int id_add) {
			String sql ="SELECT register.*,register_log.* FROM   register_log  LEFT JOIN register ON register.id = register_log.id_refer   "
					+ " WHERE register_log.process_id =? AND  register_log.id_add =?  ";
			List<register_log> register_log = jdbcTemplate.query(sql,new Object[]{process_id,id_add},new register_logmapper());
			return register_log;
		}
		
		
		
		
		@Override
		public List<product_log> getAllProduct_logByidAdd(int process_id,int id_add) {
			String sql ="SELECT product_master.*,product_log.* FROM   product_log  LEFT JOIN product_master ON product_master.id = product_log.id_refer   "
					+ " WHERE product_log.process_id =? AND  product_log.id_add =?  ";
			List<product_log> product_log = jdbcTemplate.query(sql,new Object[]{process_id,id_add},new product_logmapper());
			return product_log;
		}
		
		
		
		
		
		@Override
		public int Adjustment_FG(Adjustment_fg adjustment_fg,int edata_id) {

		   
			if (adjustment_fg == null) {
				System.out.print(" Adjustment_fg null");
		        return 0;
		    }
		    
			System.out.println(" f product_code "+adjustment_fg.getAdjustment_product_code());
	    	System.out.println("f  lot_batch "+adjustment_fg.getAdjustment_lot_batch());
	    	
			
		    Fg fg =  getFG(adjustment_fg.getAdjustment_product_code());
		    if (fg == null) {
		    	
		    	System.out.println(" product_code "+adjustment_fg.getAdjustment_product_code());
		    	System.out.println(" lot_batch "+adjustment_fg.getAdjustment_lot_batch());
		    	
		    	System.out.print(" fg null");
		        return -1;
		    }
		    
		    Object[] adjustment_fgInfo = {
		    	edata_id,
		        adjustment_fg.getDoc_code(),
		        adjustment_fg.getTempId(),
		        adjustment_fg.getTitle(),
		        adjustment_fg.getAdjustment_date(),
		        adjustment_fg.getAdjustment_reason(),
		        adjustment_fg.getAdjustment_remark(),
		        adjustment_fg.getAdjustment_type(),
		        adjustment_fg.getAdjustment_location(),
		        adjustment_fg.getAdjustment_pd_code(),
		        adjustment_fg.getAdjustment_lot_batch(),
		        adjustment_fg.getAdjustment_product_code(),
		        adjustment_fg.getAdjustment_product_name(),
		        adjustment_fg.getAdjustment_quantity(),
		        adjustment_fg.getAdjustment_unit()
		    };

		    if (adjustment_fg.getId() > 0) {
		        String sql = "UPDATE adjustment_fg SET "
		                   + "edata_id=?, doc_code=?, tempId=?, title=?, adjustment_date=?, adjustment_reason=?, "
		                   + "adjustment_remark=?, adjustment_type=?, adjustment_location=?, adjustment_pd_code=?, "
		                   + "adjustment_lot_batch=?, adjustment_product_code=?, adjustment_product_name=?, "
		                   + "adjustment_quantity=?, adjustment_unit=? "
		                   + "WHERE id=" + adjustment_fg.getId();

		        return jdbcTemplate.update(sql, adjustment_fgInfo);

		    } else  if (edata_id > 0) {
		        String sql = "INSERT INTO adjustment_fg (edata_id, doc_code, tempId, title, adjustment_date, adjustment_reason, "
		                   + "adjustment_remark, adjustment_type, adjustment_location, adjustment_pd_code, "
		                   + "adjustment_lot_batch, adjustment_product_code, adjustment_product_name, "
		                   + "adjustment_quantity, adjustment_unit) "
		                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < adjustment_fgInfo.length; i++) {
		                ps.setObject(i + 1, adjustment_fgInfo[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();

		    } else {
		        return 0;
		    }
		}
		
		
		@Override
		public int Adjustment_SK(Adjustment_sk adjustment_sk, int edata_id) {

		    if (adjustment_sk == null) {
		       	System.out.println(" adjustment_sk  null ");
		        return 0;
		    }

		    System.out.println(" stock_code "+adjustment_sk.getSka_stock_code());
		    System.out.println(" lot_batch "+adjustment_sk.getSka_lot_batch());
		    
		    
		    // สมมติว่ามีเมธอด getSK() แบบเดียวกับ getFG()
		    stock_lot sk = getSk(adjustment_sk.getSka_stock_code());
		    if (sk == null ) {  
		    	
		    	// ไม่มีสต็อกนี้ในระบบ
		    	
		    	System.out.println("ไม่มีสต็อกนี้ในระบบ ");
		    	
		        return -1;
		    }

		    Object[] adjustmentSkInfo = {
		        edata_id,
		        adjustment_sk.getDoc_code(),
		        adjustment_sk.getTempId(),
		        adjustment_sk.getTitle(),
		        adjustment_sk.getSka_date(),
		        adjustment_sk.getSka_reason(),
		        adjustment_sk.getSka_remark(),
		        adjustment_sk.getSka_type(),
		        adjustment_sk.getSka_location(),
		        adjustment_sk.getSka_stock_code(),
		        adjustment_sk.getSka_stock_name(),
		        adjustment_sk.getSka_tracking_number(),
		        adjustment_sk.getSka_lot_batch(),
		        adjustment_sk.getSka_quantity(),
		        adjustment_sk.getSka_unit(),
		        adjustment_sk.getSka_pd(),
		        
		    };

		    if (adjustment_sk.getId() > 0) {   // UPDATE
		        String sql = "UPDATE adjustment_sk SET "
		                   + "edata_id=?, doc_code=?, tempId=?, title=?, ska_date=?, ska_reason=?, "
		                   + "ska_remark=?, ska_type=?, ska_location=?, ska_stock_code=?, ska_stock_name=?, "
		                   + "ska_tracking_number=?, ska_lot_batch=?, ska_quantity=?, ska_unit=?,ska_pd=? "
		                   + "WHERE id=" + adjustment_sk.getId();

		        return jdbcTemplate.update(sql, adjustmentSkInfo);

		    } else if (edata_id > 0) {         // INSERT
		        String sql = "INSERT INTO adjustment_sk (edata_id, doc_code, tempId, title, ska_date, ska_reason, "
		                   + "ska_remark, ska_type, ska_location, ska_stock_code, ska_stock_name, "
		                   + "ska_tracking_number, ska_lot_batch, ska_quantity, ska_unit,ska_pd) "
		                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < adjustmentSkInfo.length; i++) {
		                ps.setObject(i + 1, adjustmentSkInfo[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();
		    }

		    return 0;
		}
		
		
		
	@Override
	public stock_lot getSk(String stock_code) {
		
			try {
				String sql = "SELECT * FROM  stock_lot WHERE ( stock_code =? OR CONCAT(stock_code,'-',stock_id) =? ) AND stock_status ='IN_STOCK'  ";
				stock_lot stocks =  jdbcTemplate.queryForObject(sql,new Object[]{stock_code,stock_code},new stock_lotmappers());
				return stocks;
			}catch(Exception e) {
				return null;
			}
		}
		
		
		
		
		@Override
		public int Receipt_FG(Goods_receipt_fg goods_receipt_fg, int edata_id) {

			    if (goods_receipt_fg == null) {
			    	System.out.println(" Goods_receipt_fg  null ");
			        return 0;
			    }

			    Object[] goodsReceiptInfo = {
			        edata_id,
			        goods_receipt_fg.getDoc_code(),
			        goods_receipt_fg.getTempId(),
			        goods_receipt_fg.getTitle(),
			        goods_receipt_fg.getStatus(),
			        goods_receipt_fg.getReceipt_date(),
			        goods_receipt_fg.getWork_order_no(),
			        goods_receipt_fg.getLocation(),
			        goods_receipt_fg.getReceived_by(),
			        goods_receipt_fg.getRemark(),
			        goods_receipt_fg.getGoods_receipt_pd_code(),
			        goods_receipt_fg.getGoods_receipt_lot_batch(),
			        goods_receipt_fg.getGoods_receipt_product_code(),
			        goods_receipt_fg.getGoods_receipt_product_name(),
			        goods_receipt_fg.getGoods_receipt_quantity(),
			        goods_receipt_fg.getGoods_receipt_unit(),
			        goods_receipt_fg.getGoods_receipt_customer_code(),
			        goods_receipt_fg.getGoods_receipt_customer_name(),
			    };

			    if (goods_receipt_fg.getId() > 0) {
			        String sql = "UPDATE goods_receipt_fg SET "
			                + "edata_id=?, doc_code=?, tempId=?, title=?, status=?, receipt_date=?, work_order_no=?, "
			                + "location=?, received_by=?, remark=?, goods_receipt_pd_code=?, goods_receipt_lot_batch=?, goods_receipt_product_code=?, goods_receipt_product_name=?, "
			                + "goods_receipt_quantity=?, goods_receipt_unit=?,goods_receipt_customer_code=?,goods_receipt_customer_name=? "
			                + "WHERE id=" + goods_receipt_fg.getId();

			        return jdbcTemplate.update(sql, goodsReceiptInfo);

			    } else {
			        String sql = "INSERT INTO goods_receipt_fg (edata_id, doc_code, tempId, title, status, receipt_date, "
			                   + "work_order_no, location, received_by, remark, goods_receipt_pd_code, goods_receipt_lot_batch, goods_receipt_product_code, goods_receipt_product_name, "
			                   + "goods_receipt_quantity, goods_receipt_unit,goods_receipt_customer_code,goods_receipt_customer_name) "
			                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			        KeyHolder keyHolder = new GeneratedKeyHolder();

			        jdbcTemplate.update(connection -> {
			            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			            for (int i = 0; i < goodsReceiptInfo.length; i++) {
			                ps.setObject(i + 1, goodsReceiptInfo[i]);
			            }
			            return ps;
			        }, keyHolder);

			        return keyHolder.getKey().intValue();
			    }
			}
		
		
		@Override
		public int Receipt_SK(Goods_receipt_sk goods_receipt_sk, int edata_id) {
		   
			   System.out.println(" goods_receipt_sk  .... ");
			
			if (goods_receipt_sk == null) {
				  System.out.println(" goods_receipt_sk  null .... ");
		        return 0;
		    }

			   System.out.println(" goods_receipt_sk run .... ");
		    
		    Object[] goodsReceiptInfo = {
		        edata_id,
		        goods_receipt_sk.getDoc_code(),
		        goods_receipt_sk.getTempId(),
		        goods_receipt_sk.getTitle(),
		        goods_receipt_sk.getSkg_received_date(),
		        goods_receipt_sk.getSkg_storage_location(),
		        goods_receipt_sk.getSkg_received_by(),
		        goods_receipt_sk.getSkg_remark(),
		        goods_receipt_sk.getSkg_pd_code(),
		        goods_receipt_sk.getSkg_lot_batch(),
		        goods_receipt_sk.getSkg_tracking_number(),
		        goods_receipt_sk.getSkg_damage_report(),
		        goods_receipt_sk.getSkg_supplier_code(),
		        goods_receipt_sk.getSkg_supplier_name(),
		        goods_receipt_sk.getSkg_quantity_received(),
		        goods_receipt_sk.getSkg_unit(),
		        goods_receipt_sk.getSkg_expiration_date(),
		        goods_receipt_sk.getSkg_price(),
		        goods_receipt_sk.getSkg_stock_code(),
		        goods_receipt_sk.getSkg_stock_name(),
		    };
	
		    if (goods_receipt_sk.getId() > 0) {
		        String sql = "UPDATE goods_receipt_sk SET "
		                + "edata_id=?, doc_code=?, tempId=?, title=?, skg_received_date=?, skg_storage_location=?, skg_received_by=?, "
		                + "skg_remark=?, skg_pd_code=?, skg_lot_batch=?, skg_tracking_number=?, skg_damage_report=?, "
		                + "skg_supplier_code=?, skg_supplier_name=?, skg_quantity_received=?, skg_unit=?, skg_expiration_date=?,skg_price=?,skg_stock_code=?,skg_stock_name=? "
		                + "WHERE id=" + goods_receipt_sk.getId();

		        return jdbcTemplate.update(sql, goodsReceiptInfo);

		    } else {
		        String sql = "INSERT INTO goods_receipt_sk (edata_id, doc_code, tempId, title, skg_received_date, skg_storage_location, skg_received_by, "
		                   + "skg_remark, skg_pd_code, skg_lot_batch, skg_tracking_number, skg_damage_report, skg_supplier_code, skg_supplier_name, "
		                   + "skg_quantity_received, skg_unit, skg_expiration_date,skg_price,skg_stock_code,skg_stock_name) "
		                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < goodsReceiptInfo.length; i++) {
		                ps.setObject(i + 1, goodsReceiptInfo[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();
		    }
		}
		
		
		
		
		
		@Override
		public int Issue_FG(Issue_fg issue_fg, int edata_id) {

			    if (issue_fg == null) {
			    	System.out.println(" issue_fg  null ");
			        return 0;
			    }

			    
			    Fg fg =  getFG( issue_fg.getIssued_product_code());
			    if(fg != null ) {
			    	
				    if(fg.getQuantity() <  issue_fg.getIssued_quantity() ) {
				    	 return -1;
				    }
			    } 
			    

			    List<Issue_fg> SubData = getAllIssued_fg_item(String.valueOf(edata_id) );			

				 // 1. รวมยอดตาม stock_code + lot_batch
				    Map<String, Double> summedQuantityMap = SubData.stream()
				        .collect(Collectors.groupingBy(
				            row -> row.getIssued_product_code() + "_" + row.getIssued_lot_batch(),
				            Collectors.summingDouble(Issue_fg::getIssued_quantity)
				        ));

				    // 2. ตรวจสอบแต่ละ sk ว่ามีพอไหม
				    for (Map.Entry<String, Double> entry : summedQuantityMap.entrySet()) {
				        String[] parts = entry.getKey().split("_");
				        String stockCode = parts[0];
				        String lotBatch = parts[1];
				        double totalRequested = entry.getValue();

				        fg = getFG(stockCode);
				        if (totalRequested > fg.getQuantity()) {
				            return -1; // ปริมาณขอเกิน stock
				        }
				    }
			  

				  
			    Object[] issueFgInfo = {
			        edata_id,
			        issue_fg.getDoc_code(),
			        issue_fg.getTempId(),
			        issue_fg.getTitle(),
			        issue_fg.getStatus(),
			        issue_fg.getIssue_date(),
			        issue_fg.getIssue_type(),
			        issue_fg.getIssue_destination(),
			        issue_fg.getIssued_by(),
			        issue_fg.getApproved_by(),
			        issue_fg.getIssued_remark(),
			        issue_fg.getIssued_pd_code(),
			        issue_fg.getIssued_lot_batch(),
			        issue_fg.getIssued_product_code(),
			        issue_fg.getIssued_product_name(),
			        issue_fg.getIssued_quantity(),
			        issue_fg.getIssued_unit(),
			        issue_fg.getIssued_customer_code(),
			        issue_fg.getIssued_customer_name(),
			    };

			    if (issue_fg.getId() > 0) {
			        String sql = "UPDATE issue_fg SET "
			                + "edata_id=?, doc_code=?, tempId=?, title=?, status=?, issue_date=?, issue_type=?, "
			                + "issue_destination=?, issued_by=?, approved_by=?, issued_remark=?, issued_pd_code=?, "
			                + "issued_lot_batch=?, issued_product_code=?, issued_product_name=?, issued_quantity=?, issued_unit=?,issued_customer_code=?,issued_customer_name=? "
			                + "WHERE id=" + issue_fg.getId();

			        return jdbcTemplate.update(sql, issueFgInfo);

			    } else {
			        String sql = "INSERT INTO issue_fg (edata_id, doc_code, tempId, title, status, issue_date, issue_type, "
			                + "issue_destination, issued_by, approved_by, issued_remark, issued_pd_code, issued_lot_batch, "
			                + "issued_product_code, issued_product_name, issued_quantity, issued_unit, issued_customer_code, issued_customer_name) "
			                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			        KeyHolder keyHolder = new GeneratedKeyHolder();

			        jdbcTemplate.update(connection -> {
			            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			            for (int i = 0; i < issueFgInfo.length; i++) {
			                ps.setObject(i + 1, issueFgInfo[i]);
			            }
			            return ps;
			        }, keyHolder);

			        return keyHolder.getKey().intValue();
			    }
			}

		
		@Override
		public int Issue_SK(Issue_sk issue_sk) {
			
		    Object[] issueSkInfo = {
			        issue_sk.getDoc_code(),
			        issue_sk.getTempId(),
			        issue_sk.getTitle(),
			        issue_sk.getSki_date(),
			        issue_sk.getSki_type(),
			        issue_sk.getSki_destination(),
			        issue_sk.getSki_by(),
			        issue_sk.getSki_approved_by(),
			        issue_sk.getSki_remark(),
			        issue_sk.getSki_pd_code(),
			        issue_sk.getSki_lot_batch(),
			        issue_sk.getSki_lot_batch2(),
			        issue_sk.getSki_stock_code(),
			        issue_sk.getSki_stock_name(),
			        issue_sk.getSki_tracking_number(),
			        issue_sk.getSki_supplier_code(),
			        issue_sk.getSki_quantity(),
			        issue_sk.getSki_withdrawal(),
			        issue_sk.getSki_unit(),
			        issue_sk.getSki_supplier_name()
			    };
		    String sql = "UPDATE issue_sk SET "
	                + " doc_code=?, tempId=?, title=?, ski_date=?, ski_type=?, ski_destination=?, "
	                + "ski_by=?, ski_approved_by=?, ski_remark=?, ski_pd_code=?, ski_lot_batch=?, ski_lot_batch2=?, ski_stock_code=?, "
	                + "ski_stock_name=?, ski_tracking_number=?, ski_supplier_code=?, ski_quantity=?,ski_withdrawal=?,ski_unit=?, ski_supplier_name=? "
	                + "WHERE id=" + issue_sk.getId();

	        return jdbcTemplate.update(sql, issueSkInfo);
		}
		
		
		
		@Override
		public int Issue_SK(Issue_sk issue_sk, int edata_id) {


				
			if (issue_sk == null) {
				  System.out.println(" Issue_sk  null .... ");
		        return 0;
		    }

			  System.out.println(" getSki_pd_code  .... "+ issue_sk.getSki_pd_code());
			   System.out.println(" getSki_lot_batch  .... "+ issue_sk.getSki_lot_batch());
			
			
			   System.out.println(" Issue_sk run .... ");
		    
		    
		    
		    // สมมติมี method getSK() แบบเดียวกับ getFG()
		    stock_lot sk = getSk(issue_sk.getSki_stock_code());
		    if (sk == null) { return -1;  } 
		        if ( issue_sk.getSki_quantity() > sk.getStock_remaining()) {
		            return -1;
		        }
		  
		    

		    List<Issue_sk> subData = getAllIssued_sk_item(String.valueOf(edata_id));		

			 // 1. รวมยอดตาม stock_code + lot_batch
			    Map<String, Double> summedQuantityMap = subData.stream()
			        .collect(Collectors.groupingBy(
			            row -> row.getSki_stock_code() + "_" + row.getSki_lot_batch(),
			            Collectors.summingDouble(Issue_sk::getSki_quantity)
			        ));

			    // 2. ตรวจสอบแต่ละ sk ว่ามีพอไหม
			    for (Map.Entry<String, Double> entry : summedQuantityMap.entrySet()) {
			        String[] parts = entry.getKey().split("_");
			        String stockCode = parts[0];
			        String lotBatch = parts[1];
			        double totalRequested = entry.getValue();

			        sk = getSk(stockCode);
				    if (sk == null) { return -1; }
			        if (totalRequested > sk.getStock_remaining()) {
			            return -1; // ปริมาณขอเกิน stock
			        }
			    }
		    
		    
		    

		    Object[] issueSkInfo = {
		        edata_id,
		        issue_sk.getDoc_code(),
		        issue_sk.getTempId(),
		        issue_sk.getTitle(),
		        issue_sk.getSki_date(),
		        issue_sk.getSki_type(),
		        issue_sk.getSki_destination(),
		        issue_sk.getSki_by(),
		        issue_sk.getSki_approved_by(),
		        issue_sk.getSki_remark(),
		        issue_sk.getSki_pd_code(),
		        issue_sk.getSki_lot_batch(),
		        issue_sk.getSki_lot_batch2(),
		        issue_sk.getSki_stock_code(),
		        issue_sk.getSki_stock_name(),
		        issue_sk.getSki_tracking_number(),
		        issue_sk.getSki_supplier_code(),
		        issue_sk.getSki_quantity(),
		        issue_sk.getSki_withdrawal(),
		        issue_sk.getSki_unit(),
		        issue_sk.getSki_supplier_name()
		    };

		    if (issue_sk.getId() > 0) {
		        String sql = "UPDATE issue_sk SET "
		                + "edata_id=?, doc_code=?, tempId=?, title=?, ski_date=?, ski_type=?, ski_destination=?, "
		                + "ski_by=?, ski_approved_by=?, ski_remark=?, ski_pd_code=?, ski_lot_batch=?, ski_lot_batch2=?, ski_stock_code=?, "
		                + "ski_stock_name=?, ski_tracking_number=?, ski_supplier_code=?, ski_quantity=?,ski_withdrawal=?,ski_unit=?, ski_supplier_name=? "
		                + "WHERE id=" + issue_sk.getId();

		        return jdbcTemplate.update(sql, issueSkInfo);

		    } else {
		        String sql = "INSERT INTO issue_sk (edata_id, doc_code, tempId, title, ski_date, ski_type, ski_destination, "
		                + "ski_by, ski_approved_by, ski_remark, ski_pd_code, ski_lot_batch, ski_lot_batch2, ski_stock_code, ski_stock_name, "
		                + "ski_tracking_number, ski_supplier_code, ski_quantity,ski_withdrawal, ski_unit, ski_supplier_name) "
		                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < issueSkInfo.length; i++) {
		                ps.setObject(i + 1, issueSkInfo[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();
		    }
		}
		
		
		
		
		
		
		
		
		
		@Override
		public Fg getFG(String product_code) {

			  try {
				  String sql ="SELECT * FROM fg WHERE CONCAT(product_code,'-',id) = ?  ";
				  Fg data  =  jdbcTemplate.queryForObject(sql,new Object[]{product_code},new fgmappers());
					return data;
					
			  }catch(EmptyResultDataAccessException e) {
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
			
		}
		
		
		
		@Override
		public int Transfer_FG(Inventory_transfer_fg inventory_transfer_fg, int edata_id) {

			    if (inventory_transfer_fg == null) {
			    	System.out.println(" inventory_transfer_fg  null ");
			        return 0;
			    }
	    
			    Fg fg =  getFG( inventory_transfer_fg.getTransfer_product_code());
			    if(  inventory_transfer_fg.getTransfer_quantity() > fg.getQuantity() ) {
			    	 return -1;
			    }

			    
				List<Inventory_transfer_fg> SubData = getAllInventory_transfer_fg_item(String.valueOf(edata_id) );			
			

			 // 1. รวมยอดตาม stock_code + lot_batch
			    Map<String, Double> summedQuantityMap = SubData.stream()
			        .collect(Collectors.groupingBy(
			            row -> row.getTransfer_product_code() + "_" + row.getTransfer_lot_batch(),
			            Collectors.summingDouble(Inventory_transfer_fg::getTransfer_quantity)
			        ));

			    // 2. ตรวจสอบแต่ละ sk ว่ามีพอไหม
			    for (Map.Entry<String, Double> entry : summedQuantityMap.entrySet()) {
			        String[] parts = entry.getKey().split("_");
			        String stockCode = parts[0];
			        String lotBatch = parts[1];
			        double totalRequested = entry.getValue();

			        fg = getFG(stockCode);
			        if (totalRequested > fg.getQuantity()) {
			            return -1; // ปริมาณขอเกิน stock
			        }
			    }
			    
			    

			    
			    Object[] inventoryTransferFgInfo = {
			        edata_id,
			        inventory_transfer_fg.getDoc_code(),
			        inventory_transfer_fg.getTempId(),
			        inventory_transfer_fg.getTitle(),
			        inventory_transfer_fg.getStatus(),
			        inventory_transfer_fg.getTransfer_date(),
			        inventory_transfer_fg.getTransfer_from_location(),
			        inventory_transfer_fg.getTransfer_to_location(),
			        inventory_transfer_fg.getTransferred_by(),
			        inventory_transfer_fg.getTransfer_remark(),
			        inventory_transfer_fg.getTransfer_pd_code(),
			        inventory_transfer_fg.getTransfer_lot_batch(),
			        inventory_transfer_fg.getTransfer_product_code(),
			        inventory_transfer_fg.getTransfer_product_name(),
			        
			        inventory_transfer_fg.getTransfer_quantity(),
			        inventory_transfer_fg.getTransfer_unit(),
			        inventory_transfer_fg.getFg_status()
			    };

			    
			    
			    
			    if (inventory_transfer_fg.getId() > 0) {
			        String sql = "UPDATE inventory_transfer_fg SET "
			                + "edata_id=?, doc_code=?, tempId=?, title=?, status=?, transfer_date=?, transfer_from_location=?, "
			                + "transfer_to_location=?, transferred_by=?, transfer_remark=?, transfer_pd_code=?, "
			                + "transfer_lot_batch=?, transfer_product_code=?, transfer_product_name=?, transfer_quantity=?, transfer_unit=? , fg_status=? "
			                + "WHERE id=" + inventory_transfer_fg.getId();

			        return jdbcTemplate.update(sql, inventoryTransferFgInfo);

			    } else {
			        String sql = "INSERT INTO inventory_transfer_fg (edata_id, doc_code, tempId, title, status, transfer_date, transfer_from_location, "
			                + "transfer_to_location, transferred_by, transfer_remark, transfer_pd_code, transfer_lot_batch, "
			                + "transfer_product_code, transfer_product_name, transfer_quantity, transfer_unit,fg_status) "
			                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			        KeyHolder keyHolder = new GeneratedKeyHolder();

			        jdbcTemplate.update(connection -> {
			            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			            for (int i = 0; i < inventoryTransferFgInfo.length; i++) {
			                ps.setObject(i + 1, inventoryTransferFgInfo[i]);
			            }
			            return ps;
			        }, keyHolder);

			        return keyHolder.getKey().intValue();
			    }
			}
		
			
		@Override
		public int Transfer_SK(Inventory_transfer_sk inventory_transfer_sk, int edata_id) {

		    if (inventory_transfer_sk == null) {
		        return 0;
		    }

		    // สมมติว่ามี method getSK() แบบเดียวกับ getFG()
		    stock_lot sk = getSk(inventory_transfer_sk.getSkt_stock_code());
		    if (sk == null) { return -1; }
		    
		    
		    List<Inventory_transfer_sk> subData = getAllInventory_transfer_sk_item(String.valueOf(edata_id));
		
		    System.out.println(" edata_id "+edata_id);
		    System.out.println(" subData "+subData.size());
		    System.out.println(" remaining "+sk.getStock_remaining());
		    
		    
		   
			    if ( inventory_transfer_sk.getSkt_quantity() > sk.getStock_remaining() ) {
			        return -1;
			    }
		 
		    
		    
		 // 1. รวมยอดตาม stock_code + lot_batch
		    Map<String, Double> summedQuantityMap = subData.stream()
		        .collect(Collectors.groupingBy(
		            row -> row.getSkt_stock_code() + "_" + row.getSkt_lot_batch(),
		            Collectors.summingDouble(Inventory_transfer_sk::getSkt_quantity)
		        ));

		    // 2. ตรวจสอบแต่ละ sk ว่ามีพอไหม
		    for (Map.Entry<String, Double> entry : summedQuantityMap.entrySet()) {
		        String[] parts = entry.getKey().split("_");
		        String stockCode = parts[0];
		        String lotBatch = parts[1];
		        double totalRequested = entry.getValue();

		        sk = getSk(stockCode);
		        if ( sk == null) {  return -1; }
			    if (    totalRequested > sk.getStock_remaining()) {
			            return -1; // ปริมาณขอเกิน stock
			        }
		      
		    }
		 
		    
		    
		    
		  

		    Object[] inventoryTransferSkInfo = {
		        edata_id,
		        inventory_transfer_sk.getDoc_code(),
		        inventory_transfer_sk.getTempId(),
		        inventory_transfer_sk.getTitle(),
		        inventory_transfer_sk.getSkt_date(),
		        inventory_transfer_sk.getSkt_from_location(),
		        inventory_transfer_sk.getSkt_to_location(),
		        inventory_transfer_sk.getSkt_transferred_by(),
		        inventory_transfer_sk.getSkt_remark(),
		        inventory_transfer_sk.getSkt_pd_code(),
		        inventory_transfer_sk.getSkt_lot_batch(),
		        inventory_transfer_sk.getSkt_stock_code(),
		        inventory_transfer_sk.getSkt_stock_name(),
		        inventory_transfer_sk.getSkt_quantity(),
		        inventory_transfer_sk.getSkt_unit(),
		        inventory_transfer_sk.getSkt_status()
		        
		    };

		    if (inventory_transfer_sk.getId() > 0) {
		        String sql = "UPDATE inventory_transfer_sk SET "
		                + "edata_id=?, doc_code=?, tempId=?, title=?, skt_date=?, skt_from_location=?, "
		                + "skt_to_location=?, skt_transferred_by=?, skt_remark=?, skt_pd_code=?, skt_lot_batch=?, "
		                + "skt_stock_code=?, skt_stock_name=?, skt_quantity=?, skt_unit=?, skt_status=? "
		                + "WHERE id=" + inventory_transfer_sk.getId();

		        return jdbcTemplate.update(sql, inventoryTransferSkInfo);

		    } else {
		        String sql = "INSERT INTO inventory_transfer_sk (edata_id, doc_code, tempId, title, skt_date, skt_from_location, "
		                + "skt_to_location, skt_transferred_by, skt_remark, skt_pd_code, skt_lot_batch, skt_stock_code, "
		                + "skt_stock_name, skt_quantity, skt_unit,skt_status) "
		                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		        KeyHolder keyHolder = new GeneratedKeyHolder();

		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < inventoryTransferSkInfo.length; i++) {
		                ps.setObject(i + 1, inventoryTransferSkInfo[i]);
		            }
		            return ps;
		        }, keyHolder);

		        return keyHolder.getKey().intValue();
		    }
		}
		
		@Override
		public int UpdateSKOptimistic(Goods_receipt_sk goods_receipt_sk) {
		    int status = 0;

		    if (goods_receipt_sk.getSkg_quantity_received() instanceof Double &&
		        Math.abs((Double) goods_receipt_sk.getSkg_quantity_received() - 0.0000001) < 1e-7) {
		        return 1;
		    }

		    try {
		        String Code = goods_receipt_sk.getSkg_stock_code();
		        String lotBatch = goods_receipt_sk.getSkg_lot_batch();
		        String tracking_number = goods_receipt_sk.getSkg_tracking_number();
		        Integer count = null;

		        if (Otherservice.haveValue(lotBatch)   == false) {
		            // กรณี lot_batch เป็น null
		            String checkSql = "SELECT COUNT(*) FROM stock_lot WHERE  ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? ) AND tracking_number =? AND lot_batch    IS NULL";
		            count = jdbcTemplate.queryForObject(checkSql, Integer.class,Code, Code,tracking_number);
		        } else {
		            // กรณี lot_batch มีค่า
		            String checkSql = "SELECT COUNT(*) FROM stock_lot WHERE  ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? ) AND tracking_number =? AND lot_batch = ?";
		            count = jdbcTemplate.queryForObject(checkSql, Integer.class, Code, Code,tracking_number,lotBatch);
		        }

		        if (count != null && count > 0) {
		            // update
		        	if (lotBatch == null) {
		        	    if (goods_receipt_sk.getSkg_price() > 0) {
		        	        // อัพเดต stock_remaining และ price
		        	        String updateSql = "UPDATE stock_lot SET stock_remaining = COALESCE(stock_remaining, 0) + ?, price = ? "
		        	        		+ "WHERE ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? )  AND tracking_number =? AND lot_batch IS NULL";
		        	        status = jdbcTemplate.update(updateSql,
		        	            goods_receipt_sk.getSkg_quantity_received(),
		        	            goods_receipt_sk.getSkg_price(),
		        	            Code,
		        	            Code
		        	            ,tracking_number
		        	            );
		        	    } else {
		        	        // อัพเดตเฉพาะ stock_remaining ไม่แตะ price
		        	        String updateSql = "UPDATE stock_lot SET stock_remaining = COALESCE(stock_remaining, 0) + ? "
		        	        		+ "WHERE ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? )  AND tracking_number =? AND lot_batch IS NULL";
		        	        status = jdbcTemplate.update(updateSql,
		        	            goods_receipt_sk.getSkg_quantity_received(),
		        	            Code,
		        	            Code,
		        	            tracking_number
		        	            );
		        	    }
		        	} else {
		        	    if (goods_receipt_sk.getSkg_price() > 0) {
		        	        // อัพเดต stock_remaining และ price
		        	        String updateSql = "UPDATE stock_lot SET stock_remaining = COALESCE(stock_remaining, 0) + ?, price = ? "
		        	        		+ "WHERE ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? ) AND tracking_number =?  AND lot_batch = ?";
		        	        status = jdbcTemplate.update(updateSql,
		        	            goods_receipt_sk.getSkg_quantity_received(),
		        	            goods_receipt_sk.getSkg_price(),
		        	            Code,
		        	            Code,
		        	            tracking_number,
		        	            lotBatch);
		        	    } else {
		        	        // อัพเดตเฉพาะ stock_remaining ไม่แตะ price
		        	        String updateSql = "UPDATE stock_lot SET stock_remaining = COALESCE(stock_remaining, 0) + ? "
		        	        		+ "WHERE ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? ) AND tracking_number =?  AND lot_batch = ?";
		        	        status = jdbcTemplate.update(updateSql,
		        	            goods_receipt_sk.getSkg_quantity_received(),
		        	            Code,
		        	            Code,
		        	            tracking_number,
		        	            lotBatch);
		        	    }
		        	}
		        } else {	
			
		            // insert
		            String insertSql = "INSERT INTO stock_lot(stock_code,stock_name, supplier_code, supplier_name, lot_batch, stock_remaining, price, stock_status,tracking_number)"
		            		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		            status = jdbcTemplate.update(insertSql,
		            		goods_receipt_sk.getSkg_stock_code(),
		            		goods_receipt_sk.getSkg_stock_name(),
		            		goods_receipt_sk.getSkg_supplier_code(),
		            		goods_receipt_sk.getSkg_supplier_name(),
		            		lotBatch,
		            		goods_receipt_sk.getSkg_quantity_received(),
		        			goods_receipt_sk.getSkg_price(),
		        			"IN_STOCK",
		        			 tracking_number
		        			);
		        }

		        
		        
		        
		        
		        String sql =" SELECT  SUM(stock_remaining) AS stock_remaining FROM stock_lot  WHERE  ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? )  AND stock_status = 'IN_STOCK'  ";
				stock_lot stocks =  jdbcTemplate.queryForObject(sql,new Object[]{goods_receipt_sk.getSkg_stock_code(),goods_receipt_sk.getSkg_stock_code()},new stock_lotmappers());

				if(stocks != null) {
				
					 String baseCode = goods_receipt_sk.getSkg_stock_code().split("-")[0];		
					
				
			    /* -------- 3. ล็อกแถวใน stock รวม แล้วคำนวณสถานะใหม่ -------- */
			    String lockStockSql =
			        "SELECT stock_remaining, stock_min, stock_max " +
			        "FROM stock " +
			        "WHERE CONCAT(COALESCE(stock_code, ''), COALESCE(stock_receipt_date, ''), stock_id) = ? FOR UPDATE";

			    Map<String,Object> stockRow = jdbcTemplate.queryForMap(
			        lockStockSql,baseCode);

			    Double newRemaining =   stocks.getStock_remaining();
			    
			    
			    Double stockMinVal = stockRow.get("stock_min") == null
			        ? null : ((Number) stockRow.get("stock_min")).doubleValue();
			    Double stockMaxVal = stockRow.get("stock_max") == null
			        ? null : ((Number) stockRow.get("stock_max")).doubleValue();

			    String newStatus;
			    if (newRemaining == 0) {
			        newStatus = "OUT_OF_STOCK";
			    } else if (stockMinVal != null && stockMinVal > 0 && newRemaining < stockMinVal) {
			        newStatus = "LOW_STOCK";
			    } else if (stockMaxVal != null && stockMaxVal > 0 && newRemaining > stockMaxVal) {
			        newStatus = "HEIGHT_STOCK";
			    } else {
			        newStatus = "IN_STOCK";
			    }

			    String updateStockSql =
			        "UPDATE stock SET  stock_status = ? " +
			        "WHERE CONCAT(COALESCE(stock_code, ''), COALESCE(stock_receipt_date, ''), stock_id) = ?  ";

			    
			   
			    
			   jdbcTemplate.update(updateStockSql,
			        newStatus,
			        baseCode);
				}
		        
		        
		        
		        
		    } catch (Exception e) {
		        throw new RuntimeException("Update error: " + e.getMessage());
		    }

		    return status;
		}
		
		
		
		@Override
		public int UpdateFGOptimistic(Goods_receipt_fg goods_receipt_fg) {
		    int status = 0;

		    if (goods_receipt_fg.getGoods_receipt_quantity() instanceof Double &&
		        Math.abs((Double) goods_receipt_fg.getGoods_receipt_quantity() - 0.0000001) < 1e-7) {
		        return 1;
		    }

		    try {
		        String productCode = goods_receipt_fg.getGoods_receipt_product_code();
		        String lotBatch = goods_receipt_fg.getGoods_receipt_lot_batch();

		  
		        List<Map<String, Object>> destRows;
		        if ( Otherservice.haveValue(lotBatch) == false) {
		            // กรณี lot_batch เป็น null
		            String checkSql = "SELECT * FROM fg WHERE  product_code = ?  AND lot_batch IS NULL AND  ( fg_status ='READY_TO_SHIP' OR fg_status ='IN_PRODUCTION' ) limit 1  ";
		            destRows = jdbcTemplate.queryForList(checkSql,productCode);
		        } else {
		            // กรณี lot_batch มีค่า
		            String checkSql = "SELECT * FROM fg WHERE  product_code = ?  AND lot_batch = ?  limit 1 ";
	        	   destRows = jdbcTemplate.queryForList(checkSql,productCode,lotBatch);
		            
		        }

		        if (!destRows.isEmpty()) {     	
		        	Map<String, Object> row = destRows.get(0);
		            Integer destId = (Integer) row.get("id");
		    
		                String updateSql = "UPDATE fg SET quantity = COALESCE(quantity, 0) + ? WHERE id = ? ";
		                status = jdbcTemplate.update(updateSql,
		                    goods_receipt_fg.getGoods_receipt_quantity(),
		                    destId);
		           
		        } else {
		            // insert
		        	
		        	String Stockstatus = Stock_status.IN_PRODUCTION.name();
		        	if(goods_receipt_fg.getGoods_receipt_quantity() > 0) {
		        		Stockstatus = Stock_status.READY_TO_SHIP.name();
		        	}
		        	
		        	
		            String insertSql = "INSERT INTO fg(product_code, product_name, customer_code, customer_name, lot_batch, quantity,work_order,fg_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		            status = jdbcTemplate.update(insertSql,
		                productCode,
		                goods_receipt_fg.getGoods_receipt_product_name(),
		                goods_receipt_fg.getGoods_receipt_customer_code(),
		                goods_receipt_fg.getGoods_receipt_customer_name(),
		                lotBatch,
		                goods_receipt_fg.getGoods_receipt_quantity(),
		                goods_receipt_fg.getWork_order_no(),
		                Stockstatus
		            		);
		        }

		    } catch (Exception e) {
		        throw new RuntimeException("Update error: " + e.getMessage());
		    }

		    return status;
		}
		
		@Override
		public List<Inventory_transfer_sk> getAllInventory_transfer_sk_item(String tempId) {
			String sql ="SELECT  inventory_transfer_sk.tempId AS id_refer_by,inventory_transfer_sk.*  FROM inventory_transfer_sk "
					+ " LEFT JOIN inventory_transfer_sk AS inventory_transfer_sks ON inventory_transfer_sk.tempId = inventory_transfer_sks.edata_id  "
					+ " WHERE inventory_transfer_sk.tempId =?   AND  ( inventory_transfer_sk.edata_id = 0 OR  inventory_transfer_sk.edata_id IS NULL )  ";

			List<Inventory_transfer_sk> data = jdbcTemplate.query(sql,new Object[]{tempId},new Inventory_transfer_skmappers());
			return data;
		}
		
		
		@Override
		public List<Inventory_transfer_fg> getAllInventory_transfer_fg_item(String tempId) {
			String sql ="SELECT  inventory_transfer_fg.tempId AS id_refer_by,inventory_transfer_fg.*  FROM inventory_transfer_fg "
					+ " LEFT JOIN inventory_transfer_fg AS inventory_transfer_fgs ON inventory_transfer_fg.tempId = inventory_transfer_fgs.edata_id  "
					+ " WHERE inventory_transfer_fg.tempId =?  AND  ( inventory_transfer_fg.edata_id = 0 OR  inventory_transfer_fg.edata_id IS NULL )  ";

			List<Inventory_transfer_fg> data = jdbcTemplate.query(sql,new Object[]{tempId},new Inventory_transfer_fgmappers());
			return data;
		}
		
		
		@Override
		public List<Goods_receipt_sk> getAllGoods_receipt_sk_item(String tempId) {
			String sql ="SELECT  goods_receipt_sk.tempId AS id_refer_by,goods_receipt_sk.*  FROM goods_receipt_sk "
					+ " LEFT JOIN goods_receipt_sk AS goods_receipt_sks ON goods_receipt_sk.tempId = goods_receipt_sks.edata_id  "
					+ " WHERE goods_receipt_sk.tempId =?  AND  ( goods_receipt_sk.edata_id = 0 OR  goods_receipt_sk.edata_id IS NULL )    ";

			List<Goods_receipt_sk> data = jdbcTemplate.query(sql,new Object[]{tempId},new Goods_receipt_skmappers());
			return data;
		}
		
		
		
		@Override
		public List<Goods_receipt_fg> getAllGoods_receipt_fg_item(String tempId) {
			String sql ="SELECT  goods_receipt_fg.tempId AS id_refer_by,goods_receipt_fg.*  FROM goods_receipt_fg "
					+ " LEFT JOIN goods_receipt_fg AS goods_receipt_fgs ON goods_receipt_fg.tempId = goods_receipt_fgs.edata_id  "
					+ " WHERE goods_receipt_fg.tempId =?   AND  ( goods_receipt_fg.edata_id = 0 OR  goods_receipt_fg.edata_id IS NULL )  ";

			List<Goods_receipt_fg> data = jdbcTemplate.query(sql,new Object[]{tempId},new Goods_receipt_fgmappers());
			return data;
		}
		
		
		
		@Override
		public int UpdateInventory_transfer_sk(Inventory_transfer_sk inventory_transfer_sk) {
		    int status = 0;

		    try {
		    	
		    	
		    	
		    	
		        String stockCode = inventory_transfer_sk.getSkt_stock_code();
		        String lotBatch = inventory_transfer_sk.getSkt_lot_batch();
		        String name = inventory_transfer_sk.getSkt_stock_name();
		        Double transferQty = inventory_transfer_sk.getSkt_quantity();
		        String stockStorageTo = inventory_transfer_sk.getSkt_to_location();
		        String stockStatus = inventory_transfer_sk.getSkt_status();
		        String tracking_number = inventory_transfer_sk.getSkt_tracking_number();
		        String baseCode = stockCode.split("-")[0];
	
		        	   // 1. ตรวจสอบปลายทางว่ามี stock_status เดียวกันไหม
		        
		        List<Map<String, Object>> destRows;
		        String checkDestSql;
		            
		           
		            if(Otherservice.haveValue(tracking_number) == true) {
		            	
		            	checkDestSql = "SELECT stock_id, stock_remaining FROM stock_lot " +
				                    "WHERE stock_code =?  AND stock_status = ? AND stock_storage = ? AND tracking_number = ?  AND  CONCAT(stock_code, '-', stock_id) != ?  limit 1  ";
		            	
		            	   destRows = jdbcTemplate.queryForList(
				                    checkDestSql, baseCode, stockStatus, stockStorageTo, tracking_number,stockCode
				            );

		            }else {
		            	 checkDestSql = "SELECT stock_id, stock_remaining FROM stock_lot " +
			                    "WHERE stock_code =?  AND stock_status = ? AND stock_storage = ? AND  CONCAT(stock_code, '-', stock_id) != ? limit 1  ";
			            
		            	   destRows = jdbcTemplate.queryForList(
				                    checkDestSql, baseCode, stockStatus, stockStorageTo,stockCode
				            );
		            }
		            
		         
		        
		            if (!destRows.isEmpty()) {
		             
		            	
		            	System.out.println("  // มี stock_status เดียวกัน → อัปเดตยอด");
		            	
		                Map<String, Object> row = destRows.get(0);
		                Integer destId = (Integer) row.get("stock_id");
		                Double existingQty = ((Number) row.get("stock_remaining")).doubleValue();
		                Double newQty = existingQty + transferQty;

		                String updateDestSql = "UPDATE stock_lot SET stock_remaining = stock_remaining+ ? WHERE stock_id = ?";
		                jdbcTemplate.update(updateDestSql, newQty, destId);
		                
		                
		   
		                
		                System.out.println(" // มี stock_status เดียวกัน → อัปเดตยอด");
		                
		            } else {
		            	
		            	
		            	  System.out.println(" // ไม่มี → INSERT แถวใหม่");
		            	
		                // ไม่มี → INSERT แถวใหม่
		                String insertSql = "INSERT INTO stock_lot (stock_code, lot_batch, stock_status, stock_storage, stock_remaining,tracking_number,stock_name) " +
		                        "VALUES (?,?, ?, ?, ?, ?, ?)";
		                jdbcTemplate.update(insertSql,baseCode,lotBatch, stockStatus, stockStorageTo, transferQty,tracking_number,name);
		                
		                System.out.println("  // 2. ตัดยอดจากต้นทาง");
			            

		                
		            }

		            // 2. ตัดยอดจากต้นทาง
		            String deductSql = "UPDATE stock_lot SET stock_remaining = stock_remaining - ? WHERE CONCAT(stock_code, '-', stock_id) = ? ";
		            jdbcTemplate.update(deductSql, transferQty, stockCode);
		            
		            
		            
		            // 3. ลบแถวต้นทางถ้า stock_remaining == 0
		            String checkSourceSql = "SELECT stock_id, stock_remaining, stock_status FROM stock_lot WHERE CONCAT(stock_code, '-', stock_id) = ? ";
		            List<Map<String, Object>> sourceRows = jdbcTemplate.queryForList(checkSourceSql, stockCode);

		            for (Map<String, Object> row : sourceRows) {
		                Double remaining = ((Number) row.get("stock_remaining")).doubleValue();
		            

		                if (remaining <= 0 ) {
		                    Integer id = (Integer) row.get("stock_id");
		                    String deleteSql = "DELETE FROM stock_lot WHERE stock_id = ?";
		                    jdbcTemplate.update(deleteSql, id);
		                }
		            }
		       
		   

		 
		    } catch (Exception e) {
		        throw new RuntimeException("Update error: " + e.getMessage(), e);
		    }

		    return status;
		}

		
		
		
			@Override
			public int UpdateInventory_transfer_fg(Inventory_transfer_fg inventory_transfer_fg) {
			    int status = 0;

			    try {
			        String productCode = inventory_transfer_fg.getTransfer_product_code();
			        String productname = inventory_transfer_fg.getTransfer_product_name();
			        String lotBatch = inventory_transfer_fg.getTransfer_lot_batch();
			        Double transferQty = inventory_transfer_fg.getTransfer_quantity();
			        String transferTo = inventory_transfer_fg.getTransfer_to_location();
			        String tranunit = inventory_transfer_fg.getTransfer_unit();
	        	    String fgStatus = inventory_transfer_fg.getFg_status();
	        	    
	        	    
	        	    
	        	    
	        	    String baseCode = productCode.split("-")[0];
	        	    
			        System.out.println("productCode " + productCode);
			        System.out.println("lotBatch " + lotBatch);
			        System.out.println("transferQty " + transferQty);
			        System.out.println("location " + inventory_transfer_fg.getTransfer_to_location());

			     
			        	
			        	  System.out.println("// 1. ตรวจสอบปลายทางว่ามี fg_status เดียวกันไหม ");
			        	    
			        	    String checkDestSql = "SELECT id, quantity FROM fg WHERE product_code = ?   AND CONCAT(product_code,'-',id) != ?  AND fg_status = ?";
			        	    List<Map<String, Object>> destRows = jdbcTemplate.queryForList(checkDestSql,baseCode,productCode,fgStatus);

			        	    if (!destRows.isEmpty()) {
			        	       
			        	    	
			        	      	  System.out.println(" // มี fg_status เดียวกันแล้ว → อัปเดต ");
			        	    	
			        	        Map<String, Object> row = destRows.get(0);
			        	        Integer destId = (Integer) row.get("id");
			        	        Double existingQty = ((Number) row.get("quantity")).doubleValue();
			        	        Double newQty = existingQty + transferQty;

			        	        String updateDestSql = "UPDATE fg SET quantity = quantity +? WHERE id = ?";
			        	        jdbcTemplate.update(updateDestSql, newQty, destId);
			        	    } else {
			        	    	
			        	    	 System.out.println(" // ไม่มี fg_status เดียวกัน → INSERT ใหม่ ");
			        	    	
			        	        
			        	        String insertSql = "INSERT INTO fg (product_code,product_name, fg_status, location, quantity,lot_batch,unit) VALUES (?, ?, ?, ?,?,?,?)";
			        	        jdbcTemplate.update(insertSql, baseCode, productname, fgStatus, transferTo, transferQty,lotBatch,tranunit);
			        	    }

			        	    System.out.println(" // 2. ตัดยอดจากต้นทาง ");
			        	    
			        	    
			        	    String deductSql = "UPDATE fg SET quantity = quantity - ? WHERE CONCAT(product_code,'-',id) = ? ";
			        	    jdbcTemplate.update(deductSql, transferQty,productCode);

			        	    
			        	    System.out.println(" 	    // 3. ตรวจสอบว่าต้องลบแถวต้นทางหรือไม่ ");
			        
			        	    String checkSourceSql = "SELECT id, quantity, fg_status FROM fg WHERE CONCAT(product_code,'-',id)  = ?  ";
			        	    List<Map<String, Object>> sourceRows = jdbcTemplate.queryForList(checkSourceSql,productCode);

			        	    for (Map<String, Object> row : sourceRows) {
			        	        Double qty = ((Number) row.get("quantity")).doubleValue();
			        	        String fgstatus = (String) row.get("fg_status");
			        	        if (qty <= 0  ) {
			        	            Integer id = (Integer) row.get("id");
			        	            String deleteSql = "DELETE FROM fg WHERE id = ?";
			        	            jdbcTemplate.update(deleteSql, id);
			        	        }
			        	    }
			        
			        
			
			 
			        
			       } catch (Exception e) {
			        throw new RuntimeException("Update error: " + e.getMessage(), e);
			    }

			    return status;
			}
		
		
		@Override
		public int UpdateAdjustment_fg(Adjustment_fg adjustment_fg) {
				int status = 0;
				
			if (adjustment_fg.getAdjustment_quantity() instanceof Double && Math.abs((Double) adjustment_fg.getAdjustment_quantity() - 0.0000001) < 1e-7) { return 1; }

			String sql = "UPDATE fg SET " +
				    "quantity = ?, " +
				    "fg_status = CASE WHEN ? > 0 THEN 'READY_TO_SHIP' ELSE 'OUT_OF_STOCK' END " +
				    "WHERE (product_code = ? OR CONCAT(product_code,'-',id) = ?)";

				List<Object> paramList = new ArrayList<>();
				Double qty = adjustment_fg.getAdjustment_quantity();
				paramList.add(qty); // SET quantity = ?
				paramList.add(qty); // ใช้ใน CASE WHEN ? > 0
				paramList.add(adjustment_fg.getAdjustment_product_code());
				paramList.add(adjustment_fg.getAdjustment_product_code());

				if (adjustment_fg.getAdjustment_lot_batch() == null) {
				    sql += " AND lot_batch IS NULL";
				} else {
				    sql += " AND lot_batch = ?";
				    paramList.add(adjustment_fg.getAdjustment_lot_batch());
				}

				Object[] params = paramList.toArray();
				 status = jdbcTemplate.update(sql, params);
				
				
				    String fglcall = "select * from  fg   WHERE (product_code = ? OR CONCAT(product_code, '-', id) = ?)   ";
				    Fg  call_fg =  jdbcTemplate.queryForObject(fglcall,new Object[]{ adjustment_fg.getAdjustment_product_code(),adjustment_fg.getAdjustment_product_code()},new fgmappers());   
				    
				    String issuecall = "select SUM(issued_quantity) as issued_quantity  from  issued_fg   WHERE issued_product_code = ?   ";
				    Issue_fg  call_issued =  jdbcTemplate.queryForObject(issuecall,new Object[]{adjustment_fg.getAdjustment_product_code()},new Issue_fgmappers());   
				    
				  
				    if(call_issued.getIssued_quantity() >= call_fg.getWork_order()) {
				    	String returnvalue = "UPDATE fg SET fg_status = 'SHIPPED'   WHERE (product_code = ? OR CONCAT(product_code, '-', id) = ?)   ";
						jdbcTemplate.update(returnvalue,new Object[]{ adjustment_fg.getAdjustment_product_code(),adjustment_fg.getAdjustment_product_code()});   
		 
				    }
				    
				    Double lotQty  = call_fg.getWork_order() -call_issued.getIssued_quantity();
				    String returnvalue = "UPDATE adjustment_fg SET adjustment_remaining = ? WHERE id =?   ";
					jdbcTemplate.update(returnvalue,new Object[]{lotQty,adjustment_fg.getId()});  
				 
				 
		
			 return  status;
		}
		
		
		@Override
		public int UpdateAdjustment_sk(Adjustment_sk adjustment_sk) {
		    int status = 0;

		    // ป้องกันค่าใกล้ 0 ที่เป็น noise (แทบไม่มีผล)
		    if (adjustment_sk.getSka_quantity() != null && Math.abs(adjustment_sk.getSka_quantity()) < 1e-7) {
		        return 1;
		    }
		    
			String sql;
			Object[] params;


		    try {
		    	
			   	 String sqls = "SELECT * from stock_lot  WHERE  ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? )  limit 1  ";
			   	stock_lot data =  jdbcTemplate.queryForObject(sqls,new Object[]{ adjustment_sk.getSka_stock_code(), adjustment_sk.getSka_stock_code()}, new stock_lotmappers());   
 
		    	
		    	
		    	if ( Otherservice.haveValue(adjustment_sk.getSka_lot_batch())  == false) {
		    		sql = "UPDATE stock_lot SET " +
		    			      "stock_remaining = ?, " +
		    			      "stock_status = CASE WHEN ? > 0 THEN 'IN_STOCK' ELSE 'OUT_OF_STOCK' END " +
		    			      "WHERE  ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? ) AND lot_batch IS NULL";
		    			params = new Object[] {
		    			    adjustment_sk.getSka_quantity(),   // stock_remaining = ?
		    			    adjustment_sk.getSka_quantity(),   // ใช้ใน CASE WHEN ? > 0
		    			    adjustment_sk.getSka_stock_code(),
		    			    adjustment_sk.getSka_stock_code()
		    			};
				} else {
					
					sql = "UPDATE stock_lot SET " +
						      "stock_remaining = ?, " +
						      "stock_status = CASE WHEN ? > 0 THEN 'IN_STOCK' ELSE 'OUT_OF_STOCK' END " +
						      "WHERE  ( stock_code = ? OR CONCAT(stock_code,'-',stock_id) = ? ) AND lot_batch =?";
						params = new Object[] {
						    adjustment_sk.getSka_quantity(),   // stock_remaining = ?
						    adjustment_sk.getSka_quantity(),   // ใช้ใน CASE WHEN ? > 0
						    adjustment_sk.getSka_stock_code(),
						    adjustment_sk.getSka_stock_code(),
						     adjustment_sk.getSka_lot_batch()
						};
					
			
				}
	
		    	
		    	
		    	
		   	 status = jdbcTemplate.update(sql, params);
		    	
	
		   	 
	    	 String returnvalue = "UPDATE adjustment_sk  SET ska_remaining = ? WHERE id =?   ";
			jdbcTemplate.update(returnvalue,new Object[]{data.getStock_remaining(),adjustment_sk.getId()});      
		        
		        

		    } catch (Exception e) {
		        throw new RuntimeException("Update error: " + e.getMessage(), e);
		    }

		    return status;
		}
		
		
		
		@Override
		public List<Adjustment_fg> getAllAdjustment_fg_item(String tempId) {
			String sql ="SELECT  adjustment_fg.tempId AS id_refer_by,adjustment_fg.*  FROM adjustment_fg "
					+ " LEFT JOIN adjustment_fg AS adjustment_fgs ON adjustment_fg.tempId = adjustment_fgs.edata_id  "
					+ " WHERE adjustment_fg.tempId =?    AND  ( adjustment_fg.edata_id = 0 OR  adjustment_fg.edata_id IS NULL )  ";

			List<Adjustment_fg> data = jdbcTemplate.query(sql,new Object[]{tempId},new Adjustment_fgmappers());
			return data;
		}
		@Override
		public List<Adjustment_sk> getAllAdjustment_sk_item(String tempId) {
			String sql ="SELECT  adjustment_sk.tempId AS id_refer_by,adjustment_sk.*  FROM adjustment_sk "
					+ " LEFT JOIN adjustment_sk AS adjustment_sks ON adjustment_sk.tempId = adjustment_sks.edata_id  "
					+ " WHERE adjustment_sk.tempId =?   AND  ( adjustment_sk.edata_id = 0 OR  adjustment_sk.edata_id IS NULL )    ";

			List<Adjustment_sk> data = jdbcTemplate.query(sql,new Object[]{tempId},new Adjustment_skmappers());
			return data;
		}
		
		@Override
		public List<Issue_fg> getAllIssued_fg_item(String tempId) {
			String sql ="SELECT  issue_fg.tempId AS id_refer_by,issue_fg.*  FROM issue_fg "
					+ " LEFT JOIN issue_fg AS issue_fgs ON issue_fg.tempId = issue_fgs.edata_id  "
					+ " WHERE issue_fg.tempId =?  AND  ( issue_fg.edata_id = 0 OR  issue_fg.edata_id IS NULL )    ";

			List<Issue_fg> issue_fg = jdbcTemplate.query(sql,new Object[]{tempId},new Issue_fgmappers());
			return issue_fg;
		}
		
		@Override
		public List<Issue_sk> getAllIssued_sk_item(String tempId) {
			String sql ="SELECT  issue_sk.tempId AS id_refer_by,issue_sk.*  FROM issue_sk "
					+ " LEFT JOIN issue_sk AS issue_sks ON issue_sk.tempId = issue_sks.edata_id  "
					+ " WHERE issue_sk.tempId =?  AND  ( issue_sk.edata_id = 0 OR  issue_sk.edata_id IS NULL ) ";

			List<Issue_sk> issue_sk = jdbcTemplate.query(sql,new Object[]{tempId},new Issue_skmappers());
			return issue_sk;
		}
		
		
		
		
		@Override
		public int UpdateIssued_fg(Issue_fg issue_fg) {
		    int status = 0;

		    System.out.println(" UpdateIssued_fg ");

		    if (issue_fg.getIssued_quantity() instanceof Double &&
		        Math.abs((Double) issue_fg.getIssued_quantity() - 0.0000001) < 1e-7) {
		        return 1;
		    }

		    try {
	
		    	   System.out.println("// 1. ตรวจสอบของคงเหลือ ");
		    	
		        // 1. ตรวจสอบของคงเหลือ
		        String checkSql = "SELECT quantity FROM fg WHERE CONCAT(product_code,'-',id) = ?    FOR UPDATE";
		        Double currentQty = jdbcTemplate.queryForObject(checkSql, Double.class, issue_fg.getIssued_product_code());

		        if (currentQty == null || currentQty < issue_fg.getIssued_quantity()) {
		            throw new RuntimeException("Stock not enough. Cannot issue.");
		        }

		      
		        System.out.println("  // 2. ตัดสต๊อก ");
		        String updateSql = "UPDATE fg SET quantity = quantity - ?,fg_status ='READY_TO_SHIP'  WHERE CONCAT(product_code,'-',id) = ?  ";

		        status = jdbcTemplate.update(updateSql,
		                issue_fg.getIssued_quantity(),
		                issue_fg.getIssued_product_code()
		            );

		    } catch (Exception e) {
		        throw new RuntimeException("Update error: " + e.getMessage());
		    }

		    
		    String fglcall = "select * from  fg  CONCAT(product_code,'-',id) = ?    ";
		    Fg  call_fg =  jdbcTemplate.queryForObject(fglcall,new Object[]{ issue_fg.getIssued_product_code()},new fgmappers());   
		    
		    String issuecall = "select SUM(issued_quantity) as issued_quantity  from  issued_fg   WHERE issued_product_code = ?   ";
		    Issue_fg  call_issued =  jdbcTemplate.queryForObject(issuecall,new Object[]{issue_fg.getIssued_product_code()},new Issue_fgmappers());   
		    
		  
		    if(call_issued.getIssued_quantity() >= call_fg.getWork_order()) {
		    	
		    	System.out.println(" getIssued_quantity "+call_issued.getIssued_quantity());
		    	System.out.println(" Work_order "+call_fg.getWork_order());
		    	
		    	String returnvalue = "UPDATE fg SET fg_status = 'SHIPPED'   WHERE CONCAT(product_code,'-',id) = ?  )   ";
				jdbcTemplate.update(returnvalue,new Object[]{ issue_fg.getIssued_product_code()});   
 
		    }
		    
		    Double issuedQty = call_issued.getIssued_quantity() != null ? call_issued.getIssued_quantity() : 0.0;
		    Double lotQty = call_fg.getWork_order() - issuedQty;
		    
		    String updateRemaining  = "UPDATE issue_fg SET issued_remaining = ? WHERE id =?   ";
			jdbcTemplate.update(updateRemaining ,new Object[]{lotQty,issue_fg.getId()});  
		    
		    return status;
		}
		
		
		
		@Override
		public int UpdateIssued_sk(Issue_sk issue_sk) {


			   try {
						System.out.println(" UpdateIssued_sk ");
					    /* -------- 0. validation -------- */
					    if (issue_sk.getSki_quantity() != null &&
					        Math.abs(issue_sk.getSki_quantity()) < 1e-7) {
					        return 1;                         // ไม่ทำอะไรถ้าปริมาณเล็กน้อย
					    }
			
					    /* -------- 1. กำหนดเงื่อนไขล็อต -------- */
					    final boolean lotIsNull = Otherservice.haveValue(issue_sk.getSki_lot_batch())  == false;
					    final String lotCond = lotIsNull ? "lot_batch IS NULL" : "lot_batch = ?";
			
					    /* -------- 2. ล็อกแถวใน stock_lot เพื่อตัดยอดเป็นอันดับแรก -------- */
					    String lockLotSql =
					        "SELECT stock_id, stock_remaining " +
					        "FROM stock_lot " +
					        "WHERE   CONCAT(stock_code,'-',stock_id) = ?    AND " + lotCond + " FOR UPDATE";
			    
					    
					    Object[] lockLotParams = lotIsNull
					        ? new Object[]{ issue_sk.getSki_stock_code()}
					        : new Object[]{ issue_sk.getSki_stock_code(), issue_sk.getSki_lot_batch() };		    
					    
					    System.out.println(" lockLotSql "+lockLotSql);
					    System.out.println(" Ski_stock_code "+ issue_sk.getSki_stock_code());
					    
					    
					    
					    Map<String,Object> lotRow = jdbcTemplate.queryForMap(lockLotSql, lockLotParams);
					    Double lotQty =   Otherservice.DoubleValueOf(lotRow.get("stock_remaining"));
			
					    System.out.println(" lotQty "+lotRow.get("stock_remaining") );
					    System.out.println(" Ski_quantity "+issue_sk.getSki_quantity());
					    
					    
					    if (lotQty < issue_sk.getSki_quantity()) {
					        throw new RuntimeException("Stock-lot not enough. Cannot issue.");
					    }
			
					    
					    
					    Double newLotQty =   lotQty - Otherservice.DoubleValueOf(issue_sk.getSki_quantity());
						  
					    
			
					    System.out.println(" newLotQty "+newLotQty );
					    System.out.println(" stock_lot "+lotRow.get("stock_lot"));
					    System.out.println(" stock_code "+lotRow.get("stock_code"));
					    
					    
					    if( Otherservice.haveValue(issue_sk.getSki_lot_batch())  == false) {
						    
						    String updateLotSql =
						        "UPDATE stock_lot SET stock_remaining =  ? " +
						        "WHERE  CONCAT(stock_code,'-',stock_id) = ? AND lot_batch IS NULL ";
			
						       jdbcTemplate.update(updateLotSql,new Object[]{newLotQty, issue_sk.getSki_stock_code()});
					    }else {
						    
						    String updateLotSql =
						        "UPDATE stock_lot SET stock_remaining =  ? " +
						        "WHERE CONCAT(stock_code,'-',stock_id) = ?   AND lot_batch = ? ";
						    jdbcTemplate.update(updateLotSql,new Object[]{newLotQty,issue_sk.getSki_stock_code(), issue_sk.getSki_lot_batch()});
						
					    }
			
					   
					  
					    
					     
					   String updateLotSql1 = "UPDATE stock_lot SET  stock_status ='IN_STOCK' WHERE   CONCAT(stock_code,'-',stock_id) = ?   AND stock_remaining > 0 ";
						jdbcTemplate.update(updateLotSql1,new Object[]{issue_sk.getSki_stock_code()});
						       
						String updateLotSql2 = "UPDATE stock_lot SET  stock_status ='OUT_OF_STOCK' WHERE   CONCAT(stock_code,'-',stock_id) = ?  AND stock_remaining < 1 ";
						jdbcTemplate.update(updateLotSql2,new Object[]{issue_sk.getSki_stock_code()});
						
						 String returnvalue = "UPDATE issue_sk SET ski_remaining = ? WHERE id =?   ";
						jdbcTemplate.update(returnvalue,new Object[]{lotQty,issue_sk.getId()});      
				
					   
						System.out.println(" Step 1 ");
					    String stockCode = issue_sk.getSki_stock_code();
					    String baseCode = stockCode.split("-")[0];
						
						System.out.println("baseCode "+baseCode);
						
					    String sql =" SELECT  SUM(stock_remaining) AS stock_remaining,stock_lot.* FROM stock_lot  WHERE    stock_code = ?  AND stock_status ='IN_STOCK'   ";
						stock_lot stocks =  jdbcTemplate.queryForObject(sql,new Object[]{baseCode},new stock_lotmappers());
				
			
						if(stocks != null) {
							
							System.out.println(" Step 2 "+stocks.getStock_remaining());
							System.out.println(" issue_sk "+issue_sk.getSki_stock_code());
							System.out.println(" Step 3 ");
						
					    /* -------- 3. ล็อกแถวใน stock รวม แล้วคำนวณสถานะใหม่ -------- */
					    String lockStockSql =
					        "SELECT stock_remaining, stock_min, stock_max " +
					        "FROM stock " +
					        "WHERE CONCAT(COALESCE(stock_code, ''), COALESCE(stock_receipt_date, ''), stock_id) = ? FOR UPDATE";
			
					
					    
					    Map<String,Object> stockRow = jdbcTemplate.queryForMap(
					        lockStockSql,baseCode);
			
					    Double newRemaining =   stocks.getStock_remaining();
					    
					    
					    Double stockMinVal = stockRow.get("stock_min") == null
					        ? null : ((Number) stockRow.get("stock_min")).doubleValue();
					    Double stockMaxVal = stockRow.get("stock_max") == null
					        ? null : ((Number) stockRow.get("stock_max")).doubleValue();
			
					    String newStatus;
					    if (newRemaining == 0) {
					        newStatus = "OUT_OF_STOCK";
					    } else if (stockMinVal != null && stockMinVal > 0 && newRemaining < stockMinVal) {
					        newStatus = "LOW_STOCK";
					    } else if (stockMaxVal != null && stockMaxVal > 0 && newRemaining > stockMaxVal) {
					        newStatus = "HEIGHT_STOCK";
					    } else {
					        newStatus = "IN_STOCK";
					    }
			
					    String updateStockSql =
					        "UPDATE stock SET  stock_status = ? " +
					        "WHERE CONCAT(COALESCE(stock_code, ''), COALESCE(stock_receipt_date, ''), stock_id) = ?  ";
			
					    return jdbcTemplate.update(updateStockSql,
					        newStatus,
					        baseCode);
						}else {
							
							return 1;
						}

			}catch(Exception e) {
				   return 0;
			}
		    
		}
		
		
		@Override
		public List<Goods_receipt_sk> GetGSKSearch(String search,String stock_code) {
			
			if(!Otherservice.haveValue(stock_code)) {
				
				String sql ="SELECT  edata.data_nameadd AS skg_received_by , goods_receipt_sk.* FROM goods_receipt_sk "
						+ "LEFT JOIN edata ON  goods_receipt_sk.edata_id = edata.data_id  "
						+ " WHERE DATE(goods_receipt_sk.skg_received_date) =?  OR  DATE_FORMAT(goods_receipt_sk.skg_received_date, '%Y-%m') = ?    ";
				List<Goods_receipt_sk> data = jdbcTemplate.query(sql,new Object[]{search,search},new Goods_receipt_skmappers());
				return data;
			}else {
				String sql ="SELECT  edata.data_nameadd AS skg_received_by , goods_receipt_sk.* FROM goods_receipt_sk "
						+ "LEFT JOIN edata ON  goods_receipt_sk.edata_id = edata.data_id  "
						+ " WHERE ( DATE(goods_receipt_sk.skg_received_date) =?  OR  DATE_FORMAT(goods_receipt_sk.skg_received_date, '%Y-%m') = ?  ) AND  goods_receipt_sk.skg_stock_code = ?  ";
				
				
				System.out.println(" stock_code "+stock_code);
				System.out.println(" search "+search);

				
				List<Goods_receipt_sk> data = jdbcTemplate.query(sql,new Object[]{search,search,stock_code},new Goods_receipt_skmappers());
				
				System.out.println(" GetGSKSearch "+data.size());
				
				return data;
			}
			
		}
			
		
		
		
		
		@Override
		public List<Goods_receipt_fg> GetGFGSearch(String search,String product_code) {
			
			if(!Otherservice.haveValue(product_code)) {
				
				String sql ="SELECT  edata.data_nameadd AS received_by , goods_receipt_fg.* FROM goods_receipt_fg "
						+ "LEFT JOIN edata ON  goods_receipt_fg.edata_id = edata.data_id  "
						+ " WHERE DATE(goods_receipt_fg.receipt_date) =?  OR  DATE_FORMAT(goods_receipt_fg.receipt_date, '%Y-%m') = ?    ";
				List<Goods_receipt_fg> data = jdbcTemplate.query(sql,new Object[]{search,search},new Goods_receipt_fgmappers());
				return data;
			}else {
				String sql ="SELECT  edata.data_nameadd AS received_by , goods_receipt_fg.* FROM goods_receipt_fg "
						+ "LEFT JOIN edata ON  goods_receipt_fg.edata_id = edata.data_id  "
						+ " WHERE ( DATE(goods_receipt_fg.receipt_date) =?  OR  DATE_FORMAT(goods_receipt_fg.receipt_date, '%Y-%m') = ?  ) AND  goods_receipt_fg.goods_receipt_product_code = ?  ";
				
				
				System.out.println(" product_code "+product_code);
				System.out.println(" search "+search);

				
				List<Goods_receipt_fg> data = jdbcTemplate.query(sql,new Object[]{search,search,product_code},new Goods_receipt_fgmappers());
				
				System.out.println(" GetGSKSearch "+data.size());
				
				return data;
			}
			
		}
			
		
		
		
		
		
				@Override
				public List<Issue_sk> GetISKSearch(String search) {
					
					
					String sql ="SELECT edata.data_nameadd AS ski_by ,users.user_name AS ski_approved_by , issue_sk.*  FROM issue_sk"
							+ " LEFT JOIN edata ON  issue_sk.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ "WHERE issue_sk.ski_tracking_number  = ?     ";
					List<Issue_sk> data = jdbcTemplate.query(sql,new Object[]{search},new Issue_skmappers());
					return data;
					
			}
				@Override
				public List<Fg> GetFGSearch(String search) {
					String sql ="SELECT * FROM fg WHERE   FIND_IN_SET(lot_batch,?)    ";
					List<Fg> data = jdbcTemplate.query(sql,new Object[]{search},new fgmappers());
					return data;
			}
				
				
				@Override
				public List<Issue_sk> GetTracking_numberSK() {
					String sql ="SELECT * FROM issue_sk WHERE ski_tracking_number IS NOT NULL group by  ski_tracking_number    ";
					List<Issue_sk> data = jdbcTemplate.query(sql,new Issue_skmappers());
					return data;
			}
				

				@Override
				public List<Issue_fg> GetIFGSearch(String search) {
					
					String sql ="SELECT edata.data_nameadd AS fg_by ,users.user_name AS fg_approved_by , issue_fg.*  FROM issue_fg"
							+ " LEFT JOIN edata ON  issue_fg.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ "WHERE FIND_IN_SET(issue_fg.issued_lot_batch,?)     ";
					List<Issue_fg> data = jdbcTemplate.query(sql,new Object[]{search},new Issue_fgmappers());
					return data;
					
			}
				
				
			@Override
			public List<Inventory_transfer_sk> GetTSKSearch(String search,String stock_code) {
				
				if(!Otherservice.haveValue(stock_code)) {
					
					String sql ="SELECT  edata.data_nameadd AS skt_transferred_by,users.user_name AS skt_approved_by , inventory_transfer_sk.* FROM inventory_transfer_sk "
							+ "LEFT JOIN edata ON  inventory_transfer_sk.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ " WHERE DATE(inventory_transfer_sk.skt_date) =?  OR  DATE_FORMAT(inventory_transfer_sk.skt_date, '%Y-%m') = ?    ";
					List<Inventory_transfer_sk> data = jdbcTemplate.query(sql,new Object[]{search,search},new Inventory_transfer_skmappers());
					return data;
				}else {
					String sql ="SELECT  edata.data_nameadd AS skt_transferred_by,users.user_name AS skt_approved_by, inventory_transfer_sk.* FROM inventory_transfer_sk "
							+ "LEFT JOIN edata ON  inventory_transfer_sk.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ " WHERE DATE(inventory_transfer_sk.skt_date) =?  OR  DATE_FORMAT(inventory_transfer_sk.skt_date, '%Y-%m') = ? AND  inventory_transfer_sk.skt_stock_code = ?    ";
					List<Inventory_transfer_sk> data = jdbcTemplate.query(sql,new Object[]{search,search,stock_code},new Inventory_transfer_skmappers());
					return data;
			
				}
		}
			
			
			@Override
			public List<Inventory_transfer_fg> GetTFGSearch(String search,String product_code) {
				
				if(!Otherservice.haveValue(product_code)) {
					
					String sql ="SELECT  edata.data_nameadd AS transferred_by,users.user_name AS fg_approved_by , inventory_transfer_fg.* FROM inventory_transfer_fg "
							+ "LEFT JOIN edata ON  inventory_transfer_fg.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ " WHERE DATE(inventory_transfer_fg.transfer_date) =?  OR  DATE_FORMAT(inventory_transfer_fg.transfer_date, '%Y-%m') = ?    ";
					List<Inventory_transfer_fg> data = jdbcTemplate.query(sql,new Object[]{search,search},new Inventory_transfer_fgmappers());
					return data;
				}else {
					String sql ="SELECT  edata.data_nameadd AS transferred_by,users.user_name AS fg_approved_by, inventory_transfer_fg.* FROM inventory_transfer_fg "
							+ "LEFT JOIN edata ON  inventory_transfer_fg.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ " WHERE DATE(inventory_transfer_fg.transfer_date) =?  OR  DATE_FORMAT(inventory_transfer_fg.transfer_date, '%Y-%m') = ? AND  inventory_transfer_fg.transfer_product_code = ?    ";
					List<Inventory_transfer_fg> data = jdbcTemplate.query(sql,new Object[]{search,search,product_code},new Inventory_transfer_fgmappers());
					return data;
			
				}
		}
			
			
			
			
			
			@Override
			public List<Adjustment_sk> GetASKSearch(String stock_code) {
				
			
					String sql ="SELECT  edata.data_nameadd AS data_nameadd,users.user_name AS ska_approved_by, adjustment_sk.* FROM adjustment_sk "
							+ "LEFT JOIN edata ON  adjustment_sk.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ " WHERE  adjustment_sk.ska_stock_code = ? ORDER BY  id DESC  limit 100  ";
					List<Adjustment_sk> data = jdbcTemplate.query(sql,new Object[]{stock_code},new Adjustment_skmappers());
					return data;
			
		}

			
			@Override
			public List<Adjustment_fg> GetAFGSearch(String product_code) {
				
			
					String sql ="SELECT  edata.data_nameadd AS data_nameadd,users.user_name AS adjustment_approved_by, adjustment_fg.* FROM adjustment_fg "
							+ "LEFT JOIN edata ON  adjustment_fg.edata_id = edata.data_id  "
							+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
							+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
							+ " WHERE  adjustment_fg.adjustment_product_code = ? ORDER BY  id DESC  limit 100  ";
					List<Adjustment_fg> data = jdbcTemplate.query(sql,new Object[]{product_code},new Adjustment_fgmappers());
					return data;
			
		}

			
		@Override
		public void saveLooseLink(Looselink Looselinks) {
			
			Object[] LooselinkObject =   {
					Looselinks.getLoose_link_table(),
					Looselinks.getLoose_link_note(),
					Looselinks.getLoose_link_scope1(),
					Looselinks.getLoose_link_scope2(),
					Looselinks.getLoose_link_to(),
					Looselinks.getLoose_link_type(),
					Looselinks.getLoose_link_key1(),
					Looselinks.getLoose_link_key2(),
			};
			
			
			String sql  = "INSERT INTO loose_link (loose_link_table,loose_link_note,loose_link_scope1,loose_link_scope2,loose_link_to,loose_link_type,loose_link_key1,loose_link_key2) "
					+ "VALUES(?,?,?,?,?,?,?,?) ";
			jdbcTemplate.update(sql, LooselinkObject);
			
		}

			
			

		@Override
		public List<Issue_sk> GetISkSearch(String search,String stock_code) {
			
		if(!Otherservice.haveValue(stock_code)) {
				
			String sql ="SELECT edata.data_nameadd AS ski_by ,users.user_name AS ski_approved_by , issue_sk.*  FROM issue_sk"
					+ " LEFT JOIN edata ON  issue_sk.edata_id = edata.data_id  "
					+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
					+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
					+ "WHERE DATE(issue_sk.ski_date) =?  OR  DATE_FORMAT(issue_sk.ski_date, '%Y-%m') = ?     ";
			List<Issue_sk> data = jdbcTemplate.query(sql,new Object[]{search,search},new Issue_skmappers());
			return data;
			}else {
				String sql ="SELECT edata.data_nameadd AS ski_by ,users.user_name AS ski_approved_by , issue_sk.*  FROM issue_sk"
						+ " LEFT JOIN edata ON  issue_sk.edata_id = edata.data_id  "
						+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
						+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
						+ "WHERE   ( DATE(issue_sk.ski_date) =?  OR  DATE_FORMAT(issue_sk.ski_date, '%Y-%m') = ? ) AND  issue_sk.ski_stock_code =?   ";
				List<Issue_sk> data = jdbcTemplate.query(sql,new Object[]{search,search,stock_code},new Issue_skmappers());
				return data;
			}
		}

		
		@Override
		public List<Looselink> findAllLooseLinkByTypeAndTo(String type,int to) {

			String sql ="SELECT process.name, loose_link.* FROM   loose_link LEFT JOIN process ON process.id  = loose_link.loose_link_table  "
					+ " WHERE  loose_link.loose_link_type =?  AND  loose_link.loose_link_to = ?   ";
			List<Looselink> List = jdbcTemplate.query(sql,new Object[]{type,to},new Looselinkmapper());
			return List;
		}

		@Override
		public void RemoveLooselink(int id) {

			String sql ="DELETE FROM  loose_link WHERE loose_link_id = ?   ";
			 jdbcTemplate.update(sql,new Object[] {id});
		}

		@Override
		public Looselink getLooselink(int id) {

			String sql ="SELECT * FROM  loose_link WHERE loose_link.loose_link_id = ?   ";
			Looselink Looselink = jdbcTemplate.queryForObject(sql,new Object[]{id},new Looselinkmapper());
			return Looselink;
		}

		
		@Override
		public void setSubTabledetails(String details,int id) {
			System.out.println(" setSubTabledetails ");
			
			String sql ="UPDATE edata SET details = ? WHERE data_id =? ";
			jdbcTemplate.update(sql,new Object[]{details,id});
		}
		

		@Override
		public List<Issue_fg> GetIFGSearch(String search,String product_code) {
			
		if(!Otherservice.haveValue(product_code)) {
				
			String sql ="SELECT edata.data_nameadd AS received_by ,users.user_name AS approved_by , issue_fg.*  FROM issue_fg"
					+ " LEFT JOIN edata ON  issue_fg.edata_id = edata.data_id  "
					+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
					+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
					+ "WHERE DATE(issue_fg.issue_date) =?  OR  DATE_FORMAT(issue_fg.issue_date, '%Y-%m') = ?     ";
			List<Issue_fg> data = jdbcTemplate.query(sql,new Object[]{search,search},new Issue_fgmappers());
			return data;
			}else {
				String sql ="SELECT edata.data_nameadd AS received_by ,users.user_name AS approved_by , issue_fg.*  FROM issue_fg"
						+ " LEFT JOIN edata ON  issue_fg.edata_id = edata.data_id  "
						+ " LEFT JOIN flow ON  flow.edata_id = edata.data_id AND flow.flow_step ='Approval'  "
						+ " LEFT JOIN users ON  users.user_id = flow.flow_send   "
						+ "WHERE   ( DATE(issue_fg.issue_date) =?  OR  DATE_FORMAT(issue_fg.issue_date, '%Y-%m') = ? ) AND  issue_fg.issued_product_code =?   ";
				List<Issue_fg> data = jdbcTemplate.query(sql,new Object[]{search,search,product_code},new Issue_fgmappers());
				return data;
			}
		}

		@Override
		public void saveCalendar(Calendar calendar) {
	
		
			Object[] calendarinFo = new Object[]{
					calendar.getYear(),
					calendar.getMonth(),
					calendar.getDay(),
					calendar.getEvent(),
					calendar.getStartTime(),
					calendar.getEndTime(),
					calendar.getIndex_id(),
					calendar.getEform_id(),
					calendar.getTo_name(),
					calendar.getBy_name(),
					calendar.getBy_NameId(),
					calendar.getStatus(),
					calendar.getProcess_id(),
					};
			
			
			String sql ="INSERT INTO  calendar (year,month,day,event,start_time,end_time,index_id,eform_id,to_name,by_name,by_name_id,status,process_id) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			jdbcTemplate.update(sql,calendarinFo);
		
			
		}
		
		@Override
		public void deleteCalendar(int eform_id) {
			
			 String sqls ="DELETE FROM  calendar WHERE eform_id = ?   ";
			 jdbcTemplate.update(sqls,new Object[] {eform_id});
			
		}
		

		
		@Override
		public List<Management> getallManagement(int id) {

			String sql ="SELECT *  FROM management  WHERE  management_id = ?  ";
			List<Management> List = jdbcTemplate.query(sql,new Object[]{id},new ManagementParametermapper());
			return List;
		}
		
		
		@Override
		public List<TabManagement> getallTabManagement(int id) {

			String sql ="SELECT *  FROM tabmanagement WHERE  tab_management_id = ? ";
			List<TabManagement> List = jdbcTemplate.query(sql,new Object[]{id},new TabManagementmapper());
			return List;
		}
		
		
		@Override
		public List<TabManagement> getallTabManagementByTabProcessId(int process_id) {

			String sql ="SELECT *  FROM tabmanagement WHERE  tab_process_id = ? ";
			List<TabManagement> List = jdbcTemplate.query(sql,new Object[]{process_id},new TabManagementmapper());
			return List;
		}
		
		
		@Override
		public TabManagement getTabManagement(int id) {

			String sql ="SELECT *  FROM tabmanagement WHERE  id = ? ";
			TabManagement data = jdbcTemplate.queryForObject(sql,new Object[]{id},new TabManagementmapper());
			return data;
		}
		
		
		
		@Override
		public List<Tabform> getTabForm(int form_id, int form_rv, List<eformfield> eformfields) {

	
		    
		    String sql = "SELECT * FROM tabform WHERE form_id = ? AND form_rv = ?";
		    List<Tabform> tabForms = jdbcTemplate.query(sql, new Object[]{form_id, form_rv}, new Tabformmapper());
		    /*
		    // 2. สร้าง Master Map เอาไว้ค้นหาข้อมูลได้ไวๆ (จะได้ไม่ต้องวนลูปซ้อนลูปเยอะๆ)
		    // 2.1 Map ของ Group (Key: id, Value: Group_label object)
		    Map<Integer, Group_label> masterGroupMap = allGroupLabels.stream()
		            .collect(Collectors.toMap(Group_label::getId, g -> g));

		    // 2.2 Map ของ Field (Key: field_id, Value: eformfield object)
		    Map<String, eformfield> masterFieldMap = eformfields.stream()
		            .collect(Collectors.toMap(
		                    f -> String.valueOf(f.getField_id()), 
		                    f -> f, 
		                    (existing, replacement) -> existing // กัน Error กรณี ID ซ้ำ
		            ));

		    // 3. เริ่มกระบวนการ "ยัดไส้" ข้อมูลเข้า Tabform ทีละแท็บ
		    for (Tabform tab : tabForms) {
		        String rawColumn = tab.getForm_column(); // ตัวอย่าง: "4000, 222"

		        if (rawColumn != null && !rawColumn.trim().isEmpty()) {
		            
		            // Step A: หา eformfields ทั้งหมดที่อยู่ใน Tab นี้
		            List<eformfield> fieldsInThisTab = Arrays.stream(rawColumn.split(","))
		                    .map(String::trim)
		                    .map(masterFieldMap::get) // ค้นหา object eformfield ตัวเป็นๆ ออกมา
		                    .filter(Objects::nonNull) // เอาเฉพาะตัวที่หาเจอใน List
		                    .collect(Collectors.toList());

		            // (รักษาโค้ดเดิมของคุณ) สร้าง title ที่เป็นชื่อ label ต่อกันด้วย comma
		            String replacedColumnTitle = fieldsInThisTab.stream()
		                    .map(eformfield::getField_label)
		                    .collect(Collectors.joining(","));
		            tab.setForm_column_title(replacedColumnTitle);

		            // Step B: จับ eformfields ที่อยู่ใน Tab นี้ มา "จัดกลุ่ม" ตาม group_label_id
		            Map<Integer, List<eformfield>> fieldsGroupedById = fieldsInThisTab.stream()
		                    .collect(Collectors.groupingBy(eformfield::getGroup_label_id));

		            // Step C: สร้าง List ของ Group_labels เฉพาะที่มีฟิลด์อยู่ใน Tab นี้
		            List<Group_label> groupsForThisTab = new ArrayList<>();

		            for (Map.Entry<Integer, List<eformfield>> entry : fieldsGroupedById.entrySet()) {
		                Integer groupId = entry.getKey();
		                List<eformfield> fieldsForThisGroup = entry.getValue(); // ฟิลด์ที่ดึงมาแล้วว่ามีใน Tab และตรงกลุ่ม

		                Group_label originalGroup = masterGroupMap.get(groupId);
		                
		                if (originalGroup != null) {
		                    // **จุดสำคัญ**: เราต้อง "สร้าง Object Group_label ขึ้นมาใหม่ (Clone)"
		                    // เพราะ Group_label 1 อันอาจจะถูกใช้หลาย Tab แต่มีฟิลด์ข้างในไม่เหมือนกัน
		                    // ถ้าใช้ originalGroup ตรงๆ ฟิลด์มันจะโดนเขียนทับมั่วไปหมด
		                    Group_label newGroupForTab = new Group_label();
		                    newGroupForTab.setId(originalGroup.getId());
		                    newGroupForTab.setGroup_label_name(originalGroup.getGroup_label_name());
		                    // ... (ถ้ามี property อื่นใน Group_label อย่าลืม copy มาด้วย) ...

		                    // ยัดฟิลด์เข้า Group
		                    newGroupForTab.setEformfields(fieldsForThisGroup); 
		                    
		                    groupsForThisTab.add(newGroupForTab);
		                }
		            }

		            // Step D: ยัดกลุ่มทั้งหมด เข้าไปใน Tabform
		            tab.setGroup_label(groupsForThisTab);
		        }
		    }
  */
		    return tabForms;
		}
		
		@Override
		public List<InputHiddenVisibility> getInputHiddenVisibility(int process_id,List<eformfield> eformfields) {

			String sql ="SELECT *  FROM input_hidden_visibility  WHERE  process_id = ?  ";
			List<InputHiddenVisibility> List = jdbcTemplate.query(sql,new Object[]{process_id},new InputHiddenVisibilitymmapper());
			
			
			// 1. สร้าง Map เพื่อให้ค้นหา Field Label จาก ID ได้ง่ายๆ (Key=ID, Value=Label)
		    // สมมติว่า field_id เป็น int, เราแปลงเป็น String เลยเพื่อความง่ายตอน map กับข้อมูลใน CSV
		    Map<String, String> fieldMap = eformfields.stream()
		            .collect(Collectors.toMap(
		                    f -> String.valueOf(f.getField_id()), // Key
		                    eformfield::getField_label,           // Value
		                    (existing, replacement) -> existing   // กรณี ID ซ้ำให้เอาตัวเดิม (ป้องกัน Error)
		            ));

		    // 2. วนลูปเพื่อเปลี่ยนค่า
		    List.forEach(row -> {
		        String rawColumn = row.getForm_column(); // ได้ค่ามาเป็น "4000,222"

		        if (rawColumn != null && !rawColumn.isEmpty()) {
		            // ตัดคำด้วย comma -> แปลง ID เป็น Label -> รวมกลับด้วย comma
		            String replacedColumn = Arrays.stream(rawColumn.split(","))
		                    .map(String::trim) // ลบช่องว่างเผื่อมี "4000, 222"
		                    .map(id -> fieldMap.getOrDefault(id, id)) // ค้นหาชื่อ ถ้าไม่เจอให้ใช้ ID เดิม
		                    .collect(Collectors.joining(","));

		            // เซ็ตค่ากลับเข้าไป (จะได้เป็น "name,telephone")
		            row.setForm_column_title(replacedColumn);
		        }
		    });
		    
			return List;
		}
		
		
		
		
		@Override
		public void saveInputHiddenVisibility(InputHiddenVisibility  visibility) {

	
			if(	visibility.getId() > 0) {
				Object[] tabforminFo = new Object[]{
						visibility.getForm_column(),
						visibility.getStep(),
						visibility.getAction_type(),
						visibility.getId()
						};
				
				String sql ="UPDATE   input_hidden_visibility set form_column=?,step=?,action_type=? WHERE id =? ";
				jdbcTemplate.update(sql,tabforminFo);
			}else {
				
				Object[] tabforminFo = new Object[]{
						"-",
						visibility.getForm_id(),
						visibility.getForm_rv(),
						visibility.getProcess_id(),
						visibility.getForm_column(),
						visibility.getStep(),
						visibility.getAction_type(),
						};
				
				String sql ="INSERT INTO  input_hidden_visibility (title,form_id,form_rv,process_id,form_column,step,action_type) VALUES(?,?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,tabforminFo);
			}
			
		

		}
		
		
		
		
		
		@Override
		public void saveTabForm(Tabform tabform) {

		
			
			if(	tabform.getId() > 0) {
				Object[] tabforminFo = new Object[]{
						tabform.getTitle(),
						tabform.getForm_column(),
						tabform.getTab_step(),
						tabform.getTab_access(),
						tabform.getId()
						};
				
				String sql ="UPDATE   tabform set title=?,form_column=?,tab_step=?,tab_access=? WHERE id =? ";
				jdbcTemplate.update(sql,tabforminFo);
			}else {
				
				Object[] tabforminFo = new Object[]{
						tabform.getForm_id(),
						tabform.getForm_rv(),
						tabform.getTitle(),
						tabform.getForm_column(),
						tabform.getTab_step(),
						tabform.getTab_access(),
						};
				
				String sql ="INSERT INTO  tabform (form_id,form_rv,title,form_column,tab_step,tab_access) VALUES(?,?,?,?,?,?) ";
				jdbcTemplate.update(sql,tabforminFo);
			}
			
		

		}

		@Override
		public Tabform getTabFormById(int id) {

		    try {
		    	String sql ="SELECT *  FROM tabform WHERE  id  =? ";
				Tabform data = jdbcTemplate.queryForObject(sql,new Object[]{id},new Tabformmapper());
				return data;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		}
		
		

		@Override
		public InputHiddenVisibility getInputHiddenVisibilityById(int id) {

		    try {
		    	String sql ="SELECT *  FROM input_hidden_visibility WHERE  id  =? ";
		    	InputHiddenVisibility data = jdbcTemplate.queryForObject(sql,new Object[]{id},new InputHiddenVisibilitymmapper());
				return data;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		}
		
		
		
		
		
		
		@Override
		public void RemoveTabFormById(String id) {
			System.out.println("id "+id);
			
			String sql ="DELETE FROM tabform WHERE  FIND_IN_SET(id,?)  ";
			 jdbcTemplate.update(sql,new Object[] {id});
		}

		@Override
		public void RemoveInputHiddenVisibilityById(String id) {
			System.out.println("id "+id);
			
			String sql ="DELETE FROM input_hidden_visibility WHERE  FIND_IN_SET(id,?)  ";
			 jdbcTemplate.update(sql,new Object[] {id});
		}

		
		
		@Override
		public int getAllApp_ManagementCount(int process_id, TabManagement tabManagement, user usersed) {

		    usersed.setAuth(Userservice.getPoliciesString());

		    String tabCurrentStep = 
		        tabManagement.getTab_current_step() == null ? "Create" : tabManagement.getTab_current_step();

		    // เช็คว่ามีเงื่อนไขชุดที่ 2 ส่งมาด้วยไหม
		    String tabCurrentStep2 = tabManagement.getTab_current_step2(); 
		    boolean hasGroup2 = (tabCurrentStep2 != null && !tabCurrentStep2.trim().isEmpty());

		    StringBuilder sql = new StringBuilder();
		    List<Object> params = new ArrayList<>();

		    // ---------- SELECT (แก้ไขเป็น COUNT) ----------
		    // ตัด AND FIND_IN_SET(idoc.current_step,?) ออกไปจัดกลุ่มด้านล่าง
		    sql.append("""
		        SELECT COUNT(*)
		        FROM edata
		        LEFT JOIN edata_report_buildin 
		            ON edata_report_buildin.buildin_edata = edata.data_id
		            AND edata_report_buildin.buildin_id_refer = ?
		        LEFT JOIN doc 
		            ON edata.data_id = doc.doc_idrefer
		        LEFT JOIN idoc 
		            ON edata.data_id = idoc.eform_id
		        WHERE edata.process_id = ? 
		    """);
		    
		    // สังเกตว่าพารามิเตอร์ตรงนี้ของเดิมรับ tabManagement.getId() มาใช้กับ edata_report_buildin.buildin_id_refer
		    params.add(tabManagement.getId()); 
		    params.add(process_id);


		    // =========================================================
		    // 🔥 เริ่มสร้างกลุ่มเงื่อนไข (Group 1) OR (Group 2) 🔥
		    // =========================================================
		    List<String> g1Conditions = new ArrayList<>();
		    List<Object> g1Params = new ArrayList<>();

		    // --- สร้างเงื่อนไข กลุ่มที่ 1 ---
		    g1Conditions.add("FIND_IN_SET(idoc.current_step, ?)");
		    g1Params.add(tabCurrentStep);

		    
		    if ("Completion".equals(tabCurrentStep) && tabManagement.getTab_closing_status() != null) {
		        g1Conditions.add("FIND_IN_SET(idoc.closing_status, ?)");
		        g1Params.add(tabManagement.getTab_closing_status());
		    }

		    if (tabManagement.getExpired() != -1) {
		        g1Conditions.add("idoc.expired = ?");
		        g1Params.add(tabManagement.getExpired());
		    }

		    if (tabManagement.getRequest_to_edit() > 0) {
		        g1Conditions.add("idoc.request_to_edit = ?");
		        g1Params.add(tabManagement.getRequest_to_edit());
		    }
		    
		    
		    
		    int ackValue = tabManagement.getAcknowledge();
		    if (ackValue != 4) { 
		        if (ackValue == 2) {
		            g1Conditions.add("edata.acknowledge IN (1, -1)");
		        } else if (ackValue == 3) {
		            g1Conditions.add("edata.acknowledge IN (0, -1)");
		        } else {
		            g1Conditions.add("edata.acknowledge = ?");
		            g1Params.add(ackValue);
		        }
		    }

		    // ⭐ เพิ่มเงื่อนไข Acknowledge User (Group 1) ⭐
		    int ackUserValue = tabManagement.getAcknowledge_user();
		    if (ackUserValue == 1) {
		        g1Conditions.add("edata.acknowledge_user = ?");
		        g1Params.add(usersed.getUser_id());
		    } else if (ackUserValue == 2) {
		        g1Conditions.add("(edata.acknowledge_user != ? OR edata.acknowledge_user IS NULL)");
		        g1Params.add(usersed.getUser_id());
		    }

		    // --- ประกอบร่าง AND ( (Group 1) OR (Group 2) ) ---
		    sql.append(" AND ( "); // เปิดวงเล็บใหญ่ครอบ OR
		    sql.append(" (").append(String.join(" AND ", g1Conditions)).append(") ");
		    params.addAll(g1Params); 

		    // ถ้ามีข้อมูลชุดที่ 2 ให้ทำ OR ต่อท้าย
		    if (hasGroup2) {
		        List<String> g2Conditions = new ArrayList<>();
		        List<Object> g2Params = new ArrayList<>();

		        g2Conditions.add("FIND_IN_SET(idoc.current_step, ?)");
		        g2Params.add(tabCurrentStep2);

		        if ("Completion".equals(tabCurrentStep2) && tabManagement.getTab_closing_status2() != null) {
		            g2Conditions.add("FIND_IN_SET(idoc.closing_status, ?)");
		            g2Params.add(tabManagement.getTab_closing_status2());
		        }

			    if (tabManagement.getRequest_to_edit2() > 0) {
			    	g2Conditions.add("idoc.request_to_edit = ?");
			    	g2Params.add(tabManagement.getRequest_to_edit2());
			    }
			    
		        int ackValue2 = tabManagement.getAcknowledge2(); 
		        if (ackValue2 != 4) { 
		            if (ackValue2 == 2) {
		                g2Conditions.add("edata.acknowledge IN (1, -1)");
		            } else if (ackValue2 == 3) {
		                g2Conditions.add("edata.acknowledge IN (0, -1)");
		            } else {
		                g2Conditions.add("edata.acknowledge = ?");
		                g2Params.add(ackValue2);
		            }
		        }

		        // ⭐ เพิ่มเงื่อนไข Acknowledge User 2 (Group 2) ⭐
		        int ackUserValue2 = tabManagement.getAcknowledge_user2();
		        if (ackUserValue2 == 1) {
		            g2Conditions.add("edata.acknowledge_user = ?");
		            g2Params.add(usersed.getUser_id());
		        } else if (ackUserValue2 == 2) {
		            g2Conditions.add("(edata.acknowledge_user != ? OR edata.acknowledge_user IS NULL)");
		            g2Params.add(usersed.getUser_id());
		        }

		        if (!g2Conditions.isEmpty()) {
		            sql.append(" OR (").append(String.join(" AND ", g2Conditions)).append(") ");
		            params.addAll(g2Params); 
		        }
		    }
		    
		    sql.append(" ) "); // ปิดวงเล็บใหญ่ครอบ OR
		    // =========================================================


		    // ---------- เงื่อนไขอื่นๆ ทำงานเหมือนเดิม ----------
		    if (tabManagement.getFollowup() >= 0) {
		        sql.append(" AND edata.followup = ? ");
		        params.add(tabManagement.getFollowup());
		    }
		    
		    // ---------- Calendar Plan กรองตามแผนงาน ----------
		    int planStatus = tabManagement.getFollowup_plan(); 
		    if (planStatus > 0) {
		        switch (planStatus) {
		            case 1: 
		                sql.append(" AND NOT EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id) ");
		                break;
		            case 2: 
		                sql.append(" AND EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id AND c.status = 0 AND c.alert = 0) ");
		                break;
		            case 3: 
		                sql.append(" AND EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id AND c.status = 0 AND c.alert IN (1, 2)) ");
		                break;
		            case 4: 
		                sql.append(" AND EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id AND c.status = 1) ");
		                break;
		        }
		    }

		    // ---------- VIEW CONDITIONS ----------
		    switch (tabManagement.getTab_type_view()) {
		        case 1: // ผู้รับเอกสาร
		            sql.append(" AND (  idoc.receiver = ?  OR  FIND_IN_SET(idoc.cleared , ?) ) ");
		            params.add(usersed.getUser_id());
		            params.add(usersed.getAuth());
		            break;

		        case 2: // ตามสิทธิ์ Auth (Regex)
		            String authStr = usersed.getAuth(); 
		            String authRegex = authStr.replace(",", "|");
		            String finalRegex = "(^|,)(" + authRegex + ")(,|$)";
		            sql.append(" AND ( FIND_IN_SET(?, idoc.refer_user) > 0 OR idoc.cleared REGEXP ? ) ");
		            params.add(usersed.getUser_id());
		            params.add(finalRegex);
		            break;

		        case 3: // ผู้สร้างเอกสาร
		            sql.append(" AND idoc.created_by = ? ");
		            params.add(usersed.getUser_id());
		            break;
		    }
		    
		    // System.out.println(" sql  "+  sql.toString());

		    // ---------- EXECUTE ----------
		    return jdbcTemplate.queryForObject(
		            sql.toString(),
		            params.toArray(),
		            Integer.class
		    );
		}
		
		@Override
		public List<EdataAppManagement> getAllApp_Management(
		        int id,
		        int rv,
		        TabManagement tabManagement,
		        user usersed) {

		    usersed.setAuth(Userservice.getPoliciesString());

		    String tabCurrentStep = 
		        tabManagement.getTab_current_step() == null ? "Create" : tabManagement.getTab_current_step();

		    // เช็คว่ามีเงื่อนไขชุดที่ 2 ส่งมาด้วยไหม (เพื่อเอาไปทำ OR)
		    String tabCurrentStep2 = tabManagement.getTab_current_step2(); 
		    boolean hasGroup2 = (tabCurrentStep2 != null && !tabCurrentStep2.trim().isEmpty());

		    System.out.println("Tab_current_step2 :  " + tabManagement.getTab_current_step2());
		    System.out.println("Acknowledge2 :  " + tabManagement.getAcknowledge2());
		    System.out.println("Tab_closing_status2 :  " + tabManagement.getTab_closing_status2());
		    
		    StringBuilder sql = new StringBuilder();
		    List<Object> params = new ArrayList<>();

		    // ---------- SELECT ----------
		    sql.append("""
		        SELECT edata.*, edata_report_buildin.*, idoc.*, doc.*
		        FROM edata
		        LEFT JOIN edata_report_buildin 
		            ON edata_report_buildin.buildin_edata = edata.data_id 
		            AND edata_report_buildin.buildin_id_refer = ?
		        LEFT JOIN doc 
		            ON edata.data_id = doc.doc_idrefer
		        LEFT JOIN idoc 
		            ON edata.data_id = idoc.eform_id
		        WHERE edata.data_eformid = ?
		          AND edata.data_eformrv = ?
		    """);

		    System.out.println(" tabManagement--> " + tabManagement.getTab_management_id());

		    params.add(tabManagement.getTab_management_id());
		    params.add(id);
		    params.add(rv);

		    // =========================================================
		    // 🔥 เริ่มสร้างกลุ่มเงื่อนไข (Group 1) OR (Group 2) 🔥
		    // =========================================================
		    List<String> g1Conditions = new ArrayList<>();
		    List<Object> g1Params = new ArrayList<>();

		    // --- สร้างเงื่อนไข กลุ่มที่ 1 ---
		    g1Conditions.add("FIND_IN_SET(idoc.current_step, ?)");
		    g1Params.add(tabCurrentStep);

		    if ("Completion".equals(tabCurrentStep) && tabManagement.getTab_closing_status() != null) {
		        g1Conditions.add("FIND_IN_SET(idoc.closing_status, ?)");
		        g1Params.add(tabManagement.getTab_closing_status());
		    }

		    if (tabManagement.getExpired() != -1) {
		        g1Conditions.add("idoc.expired = ?");
		        g1Params.add(tabManagement.getExpired());
		    }

		    
		    
		    
		    int ackValue = tabManagement.getAcknowledge();
		    if (ackValue != 4) { 
		        if (ackValue == 2) {
		            g1Conditions.add("edata.acknowledge IN (1, -1)");
		        } else if (ackValue == 3) {
		            g1Conditions.add("edata.acknowledge IN (0, -1)");
		        } else {
		            g1Conditions.add("edata.acknowledge = ?");
		            g1Params.add(ackValue);
		        }
		    }

		    if (tabManagement.getRequest_to_edit() > 0) {
		        g1Conditions.add("idoc.request_to_edit = ?");
		        g1Params.add(tabManagement.getRequest_to_edit());
		    }
		    
		    
		    // ⭐ เพิ่มเงื่อนไข Acknowledge User (Group 1) ⭐
		    int ackUserValue = tabManagement.getAcknowledge_user();
		    if (ackUserValue == 1) {
		        g1Conditions.add("edata.acknowledge_user = ?");
		        g1Params.add(usersed.getUser_id());
		    } else if (ackUserValue == 2) {
		        // ใช้ IS NULL ด้วย เพื่อกันกรณีที่ยังไม่มีคนรับงานแล้วมันหายไปจากหน้าจอ
		        g1Conditions.add("(edata.acknowledge_user != ? OR edata.acknowledge_user IS NULL)");
		        g1Params.add(usersed.getUser_id());
		    }

		    // --- ประกอบร่าง AND ( (Group 1) OR (Group 2) ) ---
		    sql.append(" AND ( "); // เปิดวงเล็บใหญ่ครอบ OR
		    sql.append(" (").append(String.join(" AND ", g1Conditions)).append(") ");
		    params.addAll(g1Params); // ยัดพารามิเตอร์กลุ่ม 1 เข้าไป

		    // ถ้ามีข้อมูลชุดที่ 2 ให้ทำ OR ต่อท้าย
		    if (hasGroup2) {
		        List<String> g2Conditions = new ArrayList<>();
		        List<Object> g2Params = new ArrayList<>();

		        g2Conditions.add("FIND_IN_SET(idoc.current_step, ?)");
		        g2Params.add(tabCurrentStep2);

		        if ("Completion".equals(tabCurrentStep2) && tabManagement.getTab_closing_status2() != null) {
		            g2Conditions.add("FIND_IN_SET(idoc.closing_status, ?)");
		            g2Params.add(tabManagement.getTab_closing_status2());
		        }
		        if (tabManagement.getRequest_to_edit2() > 0) {
		        	g2Conditions.add("idoc.request_to_edit = ?");
		        	g2Params.add(tabManagement.getRequest_to_edit2());
			    }
			    

		        int ackValue2 = tabManagement.getAcknowledge2(); 
		        if (ackValue2 != 4) { 
		            if (ackValue2 == 2) {
		                g2Conditions.add("edata.acknowledge IN (1, -1)");
		            } else if (ackValue2 == 3) {
		                g2Conditions.add("edata.acknowledge IN (0, -1)");
		            } else {
		                g2Conditions.add("edata.acknowledge = ?");
		                g2Params.add(ackValue2);
		            }
		        }

		        // ⭐ เพิ่มเงื่อนไข Acknowledge User 2 (Group 2) ⭐
		        // หมายเหตุ: ต้องเพิ่ม getAcknowledge_user2() ใน DTO TabManagement ด้วยนะครับ
		        int ackUserValue2 = tabManagement.getAcknowledge_user2();
		        if (ackUserValue2 == 1) {
		            g2Conditions.add("edata.acknowledge_user = ?");
		            g2Params.add(usersed.getUser_id());
		        } else if (ackUserValue2 == 2) {
		            g2Conditions.add("(edata.acknowledge_user != ? OR edata.acknowledge_user IS NULL)");
		            g2Params.add(usersed.getUser_id());
		        }

		        // เพิ่มคำว่า OR และยัดเงื่อนไขกลุ่ม 2 ลงไป
		        if (!g2Conditions.isEmpty()) {
		            sql.append(" OR (").append(String.join(" AND ", g2Conditions)).append(") ");
		            params.addAll(g2Params); // ยัดพารามิเตอร์กลุ่ม 2 ตามเข้าไปติดๆ
		        }
		    }
		    
		    sql.append(" ) "); // ปิดวงเล็บใหญ่ครอบ OR
		    // =========================================================

		    // ---------- เส้นอื่นๆ ทำงานเหมือนเดิม ----------
		    if (tabManagement.getFollowup() >= 0) {
		        sql.append(" AND edata.followup = ? ");
		        params.add(tabManagement.getFollowup());
		    }

		    // 🔥🔥🔥 ส่วนกรอง Calendar Plan 🔥🔥🔥
		    int planStatus = tabManagement.getFollowup_plan(); 
		    if (planStatus > 0) {
		        switch (planStatus) {
		            case 1: 
		                sql.append(" AND NOT EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id) ");
		                break;
		            case 2: 
		                sql.append(" AND EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id AND c.status = 0 AND c.alert = 0) ");
		                break;
		            case 3: 
		                sql.append(" AND EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id AND c.status = 0 AND c.alert IN (1, 2)) ");
		                break;
		            case 4: 
		                sql.append(" AND EXISTS (SELECT 1 FROM calendar c WHERE c.eform_id = edata.data_id AND c.status = 1) ");
		                break;
		        }
		    }

		    // ---------- VIEW CONDITIONS ----------
		    switch (tabManagement.getTab_type_view()) {
		        case 1:
		            sql.append(" AND (  idoc.receiver = ?  OR  FIND_IN_SET(idoc.cleared , ?) ) ");
		            params.add(usersed.getUser_id());
		            params.add(usersed.getAuth());
		            break;

		        case 2:
		            String authStr = usersed.getAuth(); 
		            String authRegex = authStr.replace(",", "|");
		            String finalRegex = "(^|,)(" + authRegex + ")(,|$)";

		            sql.append(" AND ( FIND_IN_SET(?, idoc.refer_user) > 0 OR idoc.cleared REGEXP ? ) ");
		            params.add(usersed.getUser_id()); 
		            params.add(finalRegex);
		            break;

		        case 3:
		            sql.append(" AND idoc.created_by = ? ");
		            params.add(usersed.getUser_id());
		            break;

		        default:
		            break;
		    }

		    sql.append(" ORDER BY edata.data_dateupdate  limit 400 ");

		    System.out.println(" SQL "+sql.toString());
		    
		    return jdbcTemplate.query(
		            sql.toString(),
		            params.toArray(),
		            new EdataAppManagementmapper()
		    );
		}
		
		

		@Override
		public int getCountApp_Management(int id,int rv, TabManagement tabManagement) {


			
			String  tab_current_step = tabManagement.getTab_current_step() == null ?  "Create" : tabManagement.getTab_current_step();
			
			
			//System.out.println("getAllApp_Management tabManagement "+tabManagement.getTab_current_step());
			
			
			if(tabManagement.getTab_closing_status() != null && tab_current_step.equals("Completion")) {
				
				String  tab_closing_status = tabManagement.getTab_closing_status();
				String sql ="SELECT count(*) "
						+ "  FROM edata"
						+ " LEFT JOIN  edata_report_buildin ON  edata_report_buildin.buildin_edata  = edata.data_id  "
						+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
						+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id "
						+ " WHERE  edata.data_eformid = ?  AND edata.data_eformrv = ?  AND idoc.current_step = ?  AND idoc.closing_status = ? ";
				return jdbcTemplate.queryForObject(sql,new Object[]{id,rv,tab_current_step,tab_closing_status}, Integer.class);
			
			}else {
				
			
				
				String sql ="SELECT  count(*) "
						+ "  FROM edata"
						+ " LEFT JOIN  edata_report_buildin ON  edata_report_buildin.buildin_edata  = edata.data_id "
						+ "LEFT JOIN doc ON edata.data_id = doc.doc_idrefer  "
						+ " LEFT JOIN idoc ON edata.data_id = idoc.eform_id"
						+ " WHERE  edata.data_eformid = ?  AND edata.data_eformrv = ? AND idoc.current_step = ?  ";
				return jdbcTemplate.queryForObject(sql,new Object[]{id,rv,tab_current_step},Integer.class);
				
			}
			
			
			
		}
		
		
		
		
		
		


		
		
		
		
		
		@Override
		public int SaveSegmentation(Segmentation Segmentations) { // เปลี่ยน return type เป็น int หรือ Long
		    
		    if (Segmentations.getId() > 0) {
		        // --- กรณี UPDATE ---
		        Object[] SegmentationsinFo = new Object[]{
		            Segmentations.getName(),
		            Segmentations.getId_edit(),
		            Segmentations.getDate_edit(),
		            Segmentations.getPromotion(),
		            Segmentations.getEdata_id(),
		            Segmentations.getId()
		        };
		        
		        String sql = "UPDATE segmentation SET name=?, id_edit=?, date_edit=?,promotion=? ,edata_id=?  WHERE id=?";
		        jdbcTemplate.update(sql, SegmentationsinFo);
		        
		        return Segmentations.getId(); // คืนค่า ID เดิมที่ส่งมาอัปเดต
		        
		    } else {
		        // --- กรณี INSERT และต้องการเอา ID ที่สร้างใหม่ ---
		        String sql = "INSERT INTO segmentation (name, status, date_add, id_add, type, process_id,promotion,edata_id) VALUES (?, ?, ?, ?, ?, ?,?,?)";
		        
		        org.springframework.jdbc.support.KeyHolder keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();
		        
		        jdbcTemplate.update(connection -> {
		            java.sql.PreparedStatement ps = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
		            ps.setString(1, Segmentations.getName());
		            ps.setInt(2, Segmentations.getStatus());
		            ps.setObject(3, Segmentations.getDate_add()); // ใช้ setObject สำหรับ Date/Timestamp
		            ps.setInt(4, Segmentations.getId_add());
		            ps.setInt(5, Segmentations.getType());
		            ps.setInt(6, Segmentations.getProcess_id());
		            ps.setInt(7, Segmentations.getPromotion());
		            ps.setInt(8, Segmentations.getEdata_id());
		            return ps;
		        }, keyHolder);

		        // ดึง ID ที่เกิดขึ้นใหม่จาก KeyHolder
		        return keyHolder.getKey().intValue(); 
		    }
		}
		
		
		@Override
		public List<Segmentation> getAllSegmentation(int process_id) {

			String sql ="SELECT segmentation.*,users.* from segmentation "
					   + " LEFT JOIN users  ON segmentation.id_add = users.user_id "
					+ " WHERE segmentation.process_id =? ";
			List<Segmentation> list = jdbcTemplate.query(sql,new Object[]{process_id},new SegmentationManagementmapper());
			return  list;
		}
		

		
		@Override
		public SetSegmentView getSetSegmentView(int process_id, user usersed) {

		    String sql = "SELECT * FROM set_segment_view WHERE process_id=? AND id_add=?";
		    
		    try {
		        System.out.println(" process_id " + process_id);
		        System.out.println(" id_add " + usersed.getUser_id());
		        
		        // 1. ลองค้นหาข้อมูลดูก่อน
		        SetSegmentView data = jdbcTemplate.queryForObject(
		            sql, 
		            new Object[]{process_id, usersed.getUser_id()}, 
		            new SetSegmentViewmapper()
		        );
		        return data;

		    } catch (EmptyResultDataAccessException e) {
		        // 2. ถ้าหาไม่เจอ (Exception ทำงาน) ให้ทำการ สร้างใหม่ (Insert)
		        System.out.println("Not found. Creating new record for process_id: " + process_id);
		        
		        // *** แก้ไขชื่อ Column ในคำสั่ง INSERT ให้ตรงกับตารางของคุณด้วยนะครับ ***
		        String insertSql = "INSERT INTO set_segment_view (process_id, id_add) VALUES (?, ?)";
		        jdbcTemplate.update(insertSql, process_id, usersed.getUser_id());
		        
		        // 3. พอบันทึกเสร็จ ก็ใช้คำสั่ง SELECT เดิมดึงข้อมูลที่เพิ่งสร้างกลับคืนไป
		        return jdbcTemplate.queryForObject(
		            sql, 
		            new Object[]{process_id, usersed.getUser_id()}, 
		            new SetSegmentViewmapper()
		        );

		    } catch (IncorrectResultSizeDataAccessException e) {
		        // กรณีมีข้อมูลซ้ำกันมากกว่า 1 ตัว (ขัดแย้งกับเงื่อนไข)
		        throw new RuntimeException("More than one users with the same Id .......");
		    }  
		}
		
		@Override
		public List<Segmentation> getAllSegmentation(process process, user usersed) {
		
			if(process.getSetSegmentView() == null) {
				return getAllSegmentation(process.getId());
			}
			
			
			// 1. เตรียม SQL พื้นฐาน
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT segmentation.*, users.* FROM segmentation ");
		    sql.append("LEFT JOIN users ON segmentation.id_add = users.user_id ");
		    sql.append("WHERE segmentation.process_id = ? ");

		    // เตรียม List สำหรับเก็บพารามิเตอร์
		    List<Object> params = new ArrayList<>();
		    params.add(process.getId()); // สมมติว่าใช้ getId() หรือ process_id จาก object

		    // 2. เงื่อนไข FIND_IN_SET สำหรับ Status
		    String status = process.getSetSegmentView().getSegmentStatus();
		    
	    	System.out.println(" status  "+status);
		    
		    if (status != null && !status.isEmpty() && !status.equals("-")) {
		    	

		    	
		        sql.append("AND FIND_IN_SET(segmentation.status, ?) ");
		        params.add(status);
		    }

		    // 3. เงื่อนไขตรวจสอบ SegmentView == 1
		    if (process.getSetSegmentView() != null && process.getSetSegmentView().getSegmentView() == 1) {
		        sql.append("AND segmentation.id_add = ? ");
		        params.add(usersed.getUser_id());
		    }

		    // 4. Execute Query
		    List<Segmentation> list = jdbcTemplate.query(
		        sql.toString(), 
		        params.toArray(), 
		        new SegmentationManagementmapper()
		    );

		    return list;
		}
		
		@Override
		public List<Segmentation> getallSegmentationByTypeAndNotUse(int type,int edata_id) {
			String sql ="SELECT * from segmentation WHERE   type = ?  AND status = 1 OR    edata_id_reserve = ?   ";
			List<Segmentation> list = jdbcTemplate.query(sql,new Object[]{type,edata_id},new SegmentationManagementmapper());
			return  list;
		}
		@Override
		public List<Segmentation> getallSegmentationByid_addAndNotUse(int id_add) {
			String sql ="SELECT * from segmentation WHERE   id_add = ?  AND status = 1   ";
			List<Segmentation> list = jdbcTemplate.query(sql,new Object[]{id_add},new SegmentationManagementmapper());
			return  list;
		}
	
		
		
		@Override
		public List<Segmentation> getallSegmentation() {
			String sql ="SELECT * from segmentation WHERE   status = 1   ";
			List<Segmentation> list = jdbcTemplate.query(sql,new SegmentationManagementmapper());
			return  list;
		}

		
		
		@Override
		public Segmentation getSegmentationById(int process_id) {
		
		    try {
		    	String sql ="SELECT * from segmentation WHERE id =? ";
				Segmentation data = jdbcTemplate.queryForObject(sql,new Object[]{process_id},new SegmentationManagementmapper());
				return  data;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		}
		
		@Override
		public Segmentation getSegmentationByEdataid(int edata_id) {
		
		    try {
		    	String sql ="SELECT * from segmentation WHERE edata_id =?  AND status = 1  limit 1  ";
				Segmentation data = jdbcTemplate.queryForObject(sql,new Object[]{	edata_id},new SegmentationManagementmapper());
				return  data;
			 
			 }catch(EmptyResultDataAccessException e) {
				 
				 return null;
				 //throw new RuntimeException("file ID does not exist");
			    
			 }catch(IncorrectResultSizeDataAccessException e) {
			       
				 throw new RuntimeException("More than one users with the same Id .......");
			   
			 }  
		}


		
		
		@Override
		public int countScheduledCalls(String memberIdsStr, String dateAdd) { // หรือปรับ dateAdd เป็น Timestamp/Date ตาม Type จริงของคุณ
		    
		    // ตรวจสอบข้อมูลว่าง
		    if (memberIdsStr == null || memberIdsStr.trim().isEmpty()) {
		        return 0;
		    }

		    // 1. แยก String เป็น Array ของ ID
		    String[] ids = memberIdsStr.split(",");
		    
		    // 2. สร้างเครื่องหมาย ?, ?, ? สำหรับ IN clause
		    String inSql = String.join(",", Collections.nCopies(ids.length, "?"));

		    // 3. สร้างคำสั่ง SQL
		    String sql = "SELECT COUNT(*) FROM calendar "
		               + "WHERE index_id IN (" + inSql + ") "
		               + "AND created_at >= ? "
		               + "AND process_id = 0";

		    // 4. เตรียม Parameter
		    List<Object> params = new ArrayList<>();
		    
		    // วนลูปใส่ ID ลงไปใน Parameter (แปลงเป็น Integer เพื่อความชัวร์)
		    for (String id : ids) {
		        params.add(Integer.parseInt(id.trim()));
		    }
		    
		    // ใส่ parameter วันที่ต่อท้าย (สำหรับ created_at >= ?)
		    params.add(dateAdd); 

		    // 5. Query คืนค่ากลับมาเป็น int
		    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
		    
		    return count != null ? count : 0;
		}
		
		
		@Override
		public Map<String, Integer> getProblemStatistics(int refer, int extensionId, String groupByColumn) {
		    
		    // 1. ป้องกัน SQL Injection (อนุญาตเฉพาะคอลัมน์ data_value1 ถึง data_value20 หรือปรับตามโครงสร้างจริงของคุณ)
		    if (!groupByColumn.matches("(?i)^data_value[1-9]$|^data_value1[0-9]$|^data_value20$")) {
		        System.out.println("⚠️ Invalid or unsafe column name: " + groupByColumn);
		        return new HashMap<>(); // คืนค่าตารางว่างเพื่อความปลอดภัย
		    }
		    
		    
		    System.out.println("groupByColumn  "+groupByColumn);
		    System.out.println("reference  "+refer);
		    System.out.println("extensionId  "+extensionId);
		    // 2. สร้าง SQL แบบ Dynamic
		    String sql = "SELECT " + groupByColumn + " AS category_name, COUNT(*) AS total_count "
		               + "FROM edata "
		               + "WHERE extension_id = ? "
		               + "AND reference = ? " // เปลี่ยนมาค้นหาจาก reference = refer ตรงๆ
		               + "AND " + groupByColumn + " IS NOT NULL AND " + groupByColumn + " != '' " // กรองค่าว่างทิ้ง
		               + "GROUP BY " + groupByColumn;

		    // 3. เตรียม Parameter
		    List<Object> params = new ArrayList<>();
		    params.add(extensionId);
		    params.add(refer); // ใส่ refer ID ที่รับเข้ามา

		    // 4. Query และแปลงผลลัพธ์
		    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params.toArray());
		    
		    Map<String, Integer> resultMap = new HashMap<>();
		    
		    for (Map<String, Object> row : rows) {
		        String categoryName = (String) row.get("category_name");
		        
		        // ใช้ Number เพื่อรองรับทั้ง Long และ Integer ที่ Spring JDBC อาจคืนค่ามาให้
		        Integer totalCount = ((Number) row.get("total_count")).intValue(); 
		        
		        resultMap.put(categoryName, totalCount);
		    }
		    
		    return resultMap;
		}
		
		@Override
		public void updateSegmentationSummary(int segmentationId, String jsonSummary) {
		    String sql = "UPDATE segmentation SET summary_data = ? WHERE id = ?";
		    jdbcTemplate.update(sql, jsonSummary, segmentationId);
		}
		
		
		@Override
		public void RemoveSegmentationById(int id) {
			System.out.println("id "+id);
			
			String sql ="DELETE FROM segmentation WHERE id = ?  ";
			 jdbcTemplate.update(sql,new Object[] {id});
		}

		
		
		
		
		@Override
		public EdataReportbuildIn getallEdataReportbuildIn(int id) {
		 
			    try {
			    	 String sql="select * from segmentation where buildin_id_refer=? limit 1    ";    
					    return jdbcTemplate.queryForObject(sql, new Object[]{id},new EdataReportbuildInmapper()); 
				 
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }  
			    
			    
		}
		

		
	
		
		@Override
		public Relations getRelationsParent(int id) {
		 
			    try {
			    	 String sql="select * from relations where relations_id=? limit 1    ";    
					 return jdbcTemplate.queryForObject(sql, new Object[]{id},new Relationsmapper()); 
				 
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }  
			    
			    
		}
		
		
		@Override
		public int LinK(edata main,edata link) {
			
	
			Relations isFirst =  getRelations(link.getData_id());
			
			int parent = 1;
			int tree = link.getData_id();
			if(isFirst != null) {
				 parent = 0;
				 tree = isFirst.getTree();
			}
			
			
			Object[] edataInfo = {
					main.getDoc(),
					link.getDoc(),
					main.getData_title(),
					link.getData_title(),
					main.getData_id(),
					link.getData_id(),
					parent,
					tree
			};
			
			String sql = "INSERT INTO relations (main_doc_code,relations_doc_code,main_doc_name,relations_doc_name,main_id,relations_id,parent,tree) "
					+ " VALUES(?,?,?,?,?,?,?,?)   ";
			return jdbcTemplate.update(sql,edataInfo);
			
			
			

		}
		
		@Override
		public int LinKName(edata main,edata link,int id) {

			Object[] edataInfo = {
					main.getDoc(),
					link.getDoc(),
					main.getData_title(),
					link.getData_title(),
					id,
			};
			
			System.out.println(" Data "+id);
			
			String sql = "UPDATE   relations SET main_doc_code = ? ,relations_doc_code = ? ,main_doc_name =? ,relations_doc_name=?   where id = ?    ";
			return jdbcTemplate.update(sql,edataInfo);
		}
		
		
		@Override
		public Relations getRelations(int id) {
		 
			    try {
			    	 String sql="select * from relations where main_id=? ORDER BY parent DESC  limit 1    ";    
					  return jdbcTemplate.queryForObject(sql, new Object[]{id},new Relationsmapper()); 
				 
				 }catch(EmptyResultDataAccessException e) {
					 
					 return null;
					 //throw new RuntimeException("file ID does not exist");
				    
				 }catch(IncorrectResultSizeDataAccessException e) {
				       
					 throw new RuntimeException("More than one users with the same Id .......");
				   
				 }  
			    
			    
		}
		
		
		@Override
		public Relations getTopRoot(int id) {
		
			    // 1. สร้าง List เพื่อเก็บ ID สำหรับทำ data_refer
			    List<String> idList = new ArrayList<>();
			    idList.add(String.valueOf(id)); // เก็บตัวมันเองก่อน

			    Relations current = null;
			    try {
			        current = jdbcTemplate.queryForObject(
			            "select * from relations where main_id=? limit 1", 
			            new Object[]{id}, new Relationsmapper()
			        );
			    } catch (Exception e) {
			        return current; 
			    }

			    // 2. วนลูปขึ้นไปจนกว่าจะถึงชั้นบนสุด
			    while (current != null && current.getRelations_id() > 0) {
			        // เก็บ ID ของ Parent ในแต่ละชั้นเข้า List
			        idList.add(String.valueOf(current.getRelations_id()));

			        try {
			            Relations parent = jdbcTemplate.queryForObject(
			                "select * from relations where main_id=? limit 1", 
			                new Object[]{current.getRelations_id()}, new Relationsmapper()
			            );
			            current = parent;
			        } catch (EmptyResultDataAccessException e) {
			            break; // ไม่เจอแม่แล้ว ตัวนี้คือ Top สุด
			        }
			    }

			    // --- ส่วนที่เพิ่มเข้ามา: อัปเดตข้อมูลโดยไม่กระทบค่าที่จะ Return ---
			    try {
			        if (!idList.isEmpty()) {
			            // รวม List เป็น String "55,11,44,6"
			            String dataRefer = String.join(",", idList);
			            // อัปเดตลงตาราง edata
			            jdbcTemplate.update("UPDATE edata SET data_refer = ? WHERE data_id = ?", dataRefer, id);
			        }
			    } catch (Exception e) {
			        // Log error ไว้แต่ไม่ต้องหยุดการทำงาน เพื่อให้ระบบยัง Return ค่า current ได้
			        System.err.println("Update edata failed: " + e.getMessage());
			    }
			    // ------------------------------------------------------

			    // 3. คืนค่าเดิม (ตัว Top สุด) ตาม Logic เดิมเป๊ะๆ
			    return current;
			
		}
	
		
		@Override
		public List<Relations> getRelationsMain(int mainId, List<Integer> relationsIds) {
		    
			
			System.out.println(" mainId "+mainId);
			System.out.println(" relationsIds "+relationsIds);
			
		    if (relationsIds == null || relationsIds.isEmpty()) {
		        return new ArrayList<>();
		    }

		    // 1. สร้างเครื่องหมาย ? ตามจำนวนข้อมูล (เช่น ?,?,?)
		    String inSql = String.join(",", Collections.nCopies(relationsIds.size(), "?"));

		    String sql = "SELECT relations.*, edata.process_id, edata.data_title, idoc.closing_status " // แก้ตรงนี้: เอา FROM ออก, ระบุคอลัมน์เลย
		            + "FROM relations "                                              // แก้ตรงนี้: เพิ่ม FROM relations เข้ามา
		            + "LEFT JOIN edata ON edata.data_id = relations.relations_id "
		            + "LEFT JOIN idoc ON edata.data_id = idoc.eform_id "             // แก้ตรงนี้: เติมเว้นวรรคหลัง eform_id
		            + "WHERE relations.main_id = ? "                                 // เช็คด้วยว่า main_id อยู่ตารางไหนแน่ (ปกติ relations.main_id)
		            + "AND edata.process_id IN (" + inSql + ")";

		    // 3. เตรียม Parameter (เอา mainId รวมกับ relationsIds)
		    List<Object> params = new ArrayList<>();
		    params.add(mainId);
		    params.addAll(relationsIds);

		    // 4. Query
		    return jdbcTemplate.query(
		        sql, 
		        params.toArray(), 
		        new Relationsmapper()
		    );
		}
		
		@Override
		public List<Relations> getRelationsRecursiveMain(int myId, List<Integer> targetProcessIds) {
		    
		    System.out.println(" My ID (ตัวเอง): " + myId);
		    System.out.println(" Target Process IDs (เป้าหมายที่ต้องการ): " + targetProcessIds);
		    
		    if (targetProcessIds == null || targetProcessIds.isEmpty()) {
		        return new ArrayList<>();
		    }

		    // 1. สร้างเครื่องหมาย ? ตามจำนวน process เป้าหมาย
		    String inSql = String.join(",", Collections.nCopies(targetProcessIds.size(), "?"));

		    // 2. ใช้ WITH RECURSIVE ดึงตัวถัดไป 5 ท่อน โดย copy main_id ตัวเองติดไปด้วย
		    String sql = 
		        "WITH RECURSIVE relation_tree AS ( " +
		        "    -- ท่อนที่ 1: ดึงตัวเอง (main_id) และ ตัวถัดไป (relations_id) พร้อมนับ Level 1 " +
		        "    SELECT main_id, relations_id, 1 AS lvl " +
		        "    FROM relations WHERE main_id = ? " +
		        "    UNION ALL " +
		        "    -- ท่อนที่ 2 ถึง 5: เอา relations_id ของชั้นก่อนหน้า ไปหาตัวถัดไป " +
		        "    SELECT t.main_id, r.relations_id, t.lvl + 1 " +
		        "    FROM relation_tree t " +
		        "    JOIN relations r ON t.relations_id = r.main_id " + // เชื่อมความสัมพันธ์ท่อนถัดไป
		        "    WHERE t.lvl < 5 " + // จำกัดความลึกแค่ 5 ท่อน
		        ") " +
		        "SELECT " +
		        "    rt.main_id, " +         // จะเป็นค่าเดิมเสมอ (เช่น 4)
		        "    rt.relations_id, " +    // จะไล่ขึ้นไปเป็น 3, 2, 1
		        "    edata.process_id, " +
		        "    edata.data_title, " +
		        "    idoc.closing_status " +
		        "FROM relation_tree rt " +
		        // Join เพื่อเช็คข้อมูลของ "ตัวถัดไป" (relations_id)
		        "INNER JOIN edata ON edata.data_id = rt.relations_id " + 
		        "LEFT JOIN idoc ON edata.data_id = idoc.eform_id " +
		        "WHERE edata.process_id IN (" + inSql + ")";

		    // 3. เตรียม Parameter
		    List<Object> params = new ArrayList<>();
		    params.add(myId); // สำหรับ WHERE main_id = ? (เริ่มจากตัวเอง)
		    params.addAll(targetProcessIds); // สำหรับเป้าหมายใน IN (...)

		    // 4. Query
		    return jdbcTemplate.query(
		        sql, 
		        params.toArray(), 
		        new Relationsmapper()
		    );
		}
		
		
		@Override
		public int measurementRulesCount(int processing_id, int data_id, String closing_status_list) {

			
			System.out.println(" processing_id "+processing_id);  //60
			System.out.println(" data_id "+data_id); //991
			System.out.println(" closing_status_list "+closing_status_list);
			
			
		    // 1. เริ่มต้น SQL หลัก
		    StringBuilder sql = new StringBuilder();
		    sql.append("SELECT COUNT(*) FROM relations ");
		    sql.append("LEFT JOIN edata ON edata.data_id = relations.main_id ");
		    sql.append("LEFT JOIN idoc ON idoc.eform_id = edata.data_id ");
		    sql.append("WHERE relations.relations_id = ? ");
		    sql.append("AND edata.process_id = ? ");

		    // 2. เตรียม Parameter List (ใส่ตัวยืนพื้นไปก่อน)
		    List<Object> params = new ArrayList<>();
		    params.add(data_id);       // map กับ relations.main_id
		    params.add(processing_id); // map กับ edata.processing_id

		    // 3. เช็คว่า closing_status_list เป็น null หรือไม่
		    // ถ้าไม่ null และไม่ว่าง -> ให้เติมเงื่อนไข SQL และเพิ่ม param
		    if (closing_status_list != null && !closing_status_list.isEmpty()) {
		        sql.append("AND FIND_IN_SET(idoc.closing_status, ?) > 0 ");
		        params.add(closing_status_list);
		    }
		    // ถ้า null -> จะไม่เติม AND... ทำให้ SQL ข้ามเงื่อนไขนี้ไปเลย (เอาหมด)

		    // 4. Query (แปลง params list เป็น array)
		    Integer count = jdbcTemplate.queryForObject(
		        sql.toString(), 
		        params.toArray(), 
		        Integer.class
		    );

		    return count != null ? count : 0;
		}
		
		
		
		
		
		
		
		
		
		@Override
		public double measurementRulesOperation(int processing_id, int data_id, String closing_status_list, String column, int opertion) {

		    // Debug
		    System.out.println(" processing_id: " + processing_id);
		    System.out.println(" data_id: " + data_id);
		    System.out.println(" closing_status_list: " + closing_status_list);
		    System.out.println(" operation: " + opertion + " (Target Column: " + column + ")");
		    
		    // 1. กำหนด SQL Function ตาม case ที่ส่งมา
		    // 1=Sum, 2=Avg, 3=Max, 4=Min
		    String sqlFunction = "SUM"; // Default เป็น SUM เผื่อหลุด case
		    switch (opertion) {
		        case 1: sqlFunction = "SUM"; break;
		        case 2: sqlFunction = "AVG"; break;
		        case 3: sqlFunction = "MAX"; break;
		        case 4: sqlFunction = "MIN"; break;
		    }

		    StringBuilder sql = new StringBuilder();
		    
		    // 2. สร้าง SQL Dynamic ตาม Function ที่เลือก
		    sql.append("SELECT ").append(sqlFunction).append("(edata.").append(column).append(") FROM relations ");
		    sql.append("LEFT JOIN edata ON edata.data_id = relations.main_id ");
		    sql.append("LEFT JOIN idoc ON idoc.eform_id = edata.data_id ");
		    sql.append("WHERE relations.relations_id = ? ");
		    sql.append("AND edata.process_id = ? ");

		    List<Object> params = new ArrayList<>();
		    params.add(data_id);
		    params.add(processing_id);

		    // 3. ใส่เงื่อนไขสถานะ (ถ้ามี)
		    if (closing_status_list != null && !closing_status_list.isEmpty()) {
		        sql.append("AND FIND_IN_SET(idoc.closing_status, ?) > 0 ");
		        params.add(closing_status_list);
		    }

		    // 4. Query และคืนค่า Double
		    try {
		        Double result = jdbcTemplate.queryForObject(
		            sql.toString(), 
		            params.toArray(), 
		            Double.class
		        );
		        // ถ้าผลลัพธ์เป็น null (ไม่มีข้อมูล) ให้คืนค่า 0.0
		        return result != null ? result : 0.0;
		        
		    } catch (Exception e) {
		        // กันเหนียว กรณี Data type ใน DB มีปัญหาหรือไม่ใช่ตัวเลข
		        System.err.println("Error calculating " + sqlFunction + ": " + e.getMessage());
		        return 0.0;
		    }
		}
		
		
		
		
		
		@Override
		public List<Relations> getRelationschild(int id) {

			String sql="select * from relations where relations_id=? ";    
			  List<Relations> list= jdbcTemplate.query(sql, new Object[]{id},new Relationsmapper());     
			  return list;
		}
		
		
		
		@Override
		public List<edata> getAllSubData(int tempId) {
			String sql ="SELECT * FROM   edata WHERE tempId =? ORDER BY data_id ASC  ";
			List<edata> list = jdbcTemplate.query(sql,new Object[]{tempId},new edatamappers());
			return list;
		}
		
		
		
		@Override
		public void saveAudit(int edata_id,String Code, String Type){
				String sql ="INSERT IGNORE INTO audit (edata_id,audit_code,audit_type)  VALUES(?,?,?) ";
				jdbcTemplate.update(sql,new Object[] {edata_id,Code,Type});
		}
		
		@Override
		public List<Audit>  getAllAudit(String Code){
				String sql ="SELECT * FROM audit WHERE  audit_code = ?   ORDER BY id DESC  limit 1000" ;
				List<Audit> list = jdbcTemplate.query(sql,new Object[] {Code},new Auditmappers());
				return list;
		}
		
		@Override
		public void saveEdatamainDataBatch(List<Object[]> batchArgs) {
		    String sql = "INSERT IGNORE INTO edata_maindata (edata_id, main_data_id, entity) VALUES (?, ?, ?)";
		    
		    // ใช้ batchUpdate เร็วกว่าวนลูป insert เอง 100 เท่า
		    jdbcTemplate.batchUpdate(sql, batchArgs);
		}
		@Override
		public void saveEdatamainData(int data_id, int main_id, String entity) {
		    // ใช้ INSERT IGNORE: ถ้ามี key ซ้ำ มันจะข้ามไป ไม่ error และไม่บันทึกซ้ำ
		    String sql = "INSERT IGNORE INTO edata_maindata(edata_id,main_data_id,entity) VALUES(?,?,?)";
		    jdbcTemplate.update(sql, new Object[]{data_id, main_id, entity});
		}
		
		@Override
		public Edata_maindata getEdatamainData(int data_id) {
			
			try {
			    // ใช้ INSERT IGNORE: ถ้ามี key ซ้ำ มันจะข้ามไป ไม่ error และไม่บันทึกซ้ำ
			    String sql = "SELECT  * from  edata_maindata WHERE edata_id=?  ";
			    Edata_maindata data = jdbcTemplate.queryForObject(sql, new Object[]{data_id},new Edata_maindatamapper());
			    return data;
			}catch(Exception e) {
				 return null;
			}	
		
		}
		
		
		
		
			
		// Helper method to check if a column exists in HaveColumns
		boolean hasColumn(String columnName, String haveCols) {
		    if (columnName == null) return false;
		    // Ensure we only match whole column names
		    String[] cols = haveCols.split(",");
		    for (String col : cols) {
		        if (col.trim().equalsIgnoreCase(columnName.trim())) {
		            return true;
		        }
		    }
		    return false;
		}



		@Override
		public int countExtensionAndIdrefer(int extension_id, int id_refer, int  group) {
		    // ใช้ INSERT IGNORE: ถ้ามี key ซ้ำ มันจะข้ามไป ไม่ error และไม่บันทึกซ้ำ
			if(group == 1) {
			    String sql = "SELECT  count(*) from  edata   WHERE extension_id=?  and reference=?  group by  reference ";
			    int count = jdbcTemplate.queryForObject(sql, new Object[]{extension_id,id_refer},Integer.class);
				return count;
			}else {
			    String sql = "SELECT  count(*) from  edata   WHERE extension_id=?  and reference=?  ";
			    int count = jdbcTemplate.queryForObject(sql, new Object[]{extension_id,id_refer},Integer.class);
				return count;
				
			}
		}

		@Override
		public void updateSegment(String segment, int total_call, int total_call_unique) {
			int segment_id;

			try {
			    // พยายามแปลงเป็นตัวเลข
			    segment_id = Integer.parseInt(segment); 
			    // (ใช้ parseInt จะเร็วกว่า valueOf เล็กน้อยในกรณีนี้ครับ)
			    
			} catch (NumberFormatException e) {
			    // ถ้าไม่ใช่ตัวเลข (แปลงไม่ผ่าน) ให้ทำงานในบล็อกนี้
			    System.out.println("ข้อมูลไม่ใช่ตัวเลข: " + segment); // (Optional) พ่น Log แจ้งเตือน
			    return; // 🚀 สั่ง "ออกไป" จาก Method ทันที
			}
			
			String sql  = "UPDATE segmentation SET call_total = ? , call_unique_count = ? WHERE  id =? ";
			jdbcTemplate.update(sql,new Object[] {total_call,total_call_unique,segment_id});
		}
		
		@Override
		public void saveConfigSubTable(process process) {
			String sql  = "UPDATE process SET subtable_editmode = ? , subtable_viewmode = ? WHERE  id =? ";
			jdbcTemplate.update(sql,new Object[] {process.getSubtable_editmode(),process.getSubtable_viewmode(),process.getId()});
		}
		
		@Override
		public void saveConfigRequestRevision(process process) {
			String sql  = "UPDATE process SET canReviseConfirmed = ? , closingStatusCanRevise = ?,closingStatusAfterRevise = ? ,closingStatusAfterAdd = ?  WHERE  id =? ";
			jdbcTemplate.update(sql,new Object[] {process.getCanReviseConfirmed()
					,process.getClosingStatusCanRevise(),process.getClosingStatusAfterRevise(),process.getClosingStatusAfterAdd(),process.getId()});
		}
		@Override
		public void saveConfigRenamefile(process process) {
			String sql  = "UPDATE process SET renamefile = ?  WHERE  id =? ";
			jdbcTemplate.update(sql,new Object[] {process.getRenamefile(),process.getId()});
		}
		
		@Override
		public void saveconfigExtension(process process) {
			String sql  = "UPDATE process SET use_index_app = ?,use_ex=?,use_ex_icon=? ,use_ex_name=?   WHERE  id =? ";
			jdbcTemplate.update(sql,new Object[] {process.getUse_index_app(),process.getUse_ex(),process.getUse_ex_icon(),process.getUse_ex_name(),process.getId()});
		}
		
		 
		@Override
		public void saveSegmentationView(SetSegmentView process) {

		    String sql = """
		        INSERT INTO set_segment_view 
		            (process_id, id_add, segment_view, segment_status, query_file)
		        VALUES (?, ?, ?, ?, ?)
		        ON DUPLICATE KEY UPDATE
		            segment_view = VALUES(segment_view),
		            segment_status = VALUES(segment_status),
		             query_file = VALUES(query_file)
		        """;

		    jdbcTemplate.update(sql,
		        process.getProcess_id(),
		        process.getId_add(),
		        process.getSegmentView(),
		        process.getSegmentStatus(),
		        process.getQuery_file()
		    );
		}
		
		@Override
		public void removeFormPrint(int id,int user_id) {
			String sql  = "DELETE FROM   form_print    WHERE  id =? AND id_add =? ";
			jdbcTemplate.update(sql,new Object[] {id,user_id});
		}
		
		@Override
		public void saveFormPrint(FormPrint formPrint) {
			
			if( formPrint.getForm_link() == null || formPrint.getForm_link().isBlank() )return;
			
			 Object[] formPrintInfo = {
					 formPrint.getForm_name(),
					 formPrint.getForm_link(),
					 formPrint.getId_add(),
					 formPrint.getProcess_id(),
			 };
			
			String sql  = "INSERT INTO  form_print  (form_name,form_link,id_add,process_id) VALUES(?,?,?,?)  ";
			jdbcTemplate.update(sql,formPrintInfo);
		}
		
		@Override
		public void saveUseformPrint(UseformPrint useformPrint){

			
			System.out.println("form_id "+useformPrint.getForm_id());
			System.out.println("user_id_select "+useformPrint.getUser_id_select());
			
			
		    String sql = "INSERT INTO use_form_print " +
		            "(form_id, process_id, use_status, user_id_select, payment_terms_column, promotion_column, register_id) " +
		            "VALUES (?, ?, ?, ?, ?, ?, ?) " +
		            "ON DUPLICATE KEY UPDATE " +
		            "form_id = VALUES(form_id), " +
		            "use_status = VALUES(use_status), " +
		            "payment_terms_column = VALUES(payment_terms_column), " +
		            "promotion_column = VALUES(promotion_column), " +
		            "register_id = VALUES(register_id)";

		    
		    
		    
		    
		    Object[] params = {
		            useformPrint.getForm_id(),
		            useformPrint.getProcess_id(),
		            useformPrint.getUse_status(),
		            useformPrint.getUser_id_select(),
		            useformPrint.getPayment_terms_column(),
		            useformPrint.getPromotion_column(),
		            useformPrint.getRegister_id()
		    };

		    jdbcTemplate.update(sql, params);
		}

		
		
		@Override
		public List<FormPrint> getAllFormPrint(int process_id){
			String sql  = "SELECT  * FROM  form_print  WHERE  process_id = ?  ";
			List<FormPrint> listData =   jdbcTemplate.query(sql,new Object[] {process_id},new FormPrintmapper());
			return listData;
		}
		
		@Override
		public FormPrint getFormPrint(int process_id){

			  try {
					String sql  = "SELECT  * FROM  form_print  WHERE  id = ?  ";
					FormPrint Data =   jdbcTemplate.queryForObject(sql,new Object[] {process_id},new FormPrintmapper());
					return Data;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
			
		}
		
		@Override
		public UseformPrint getUseformPrint(int process_id, int user_id){
		
			  try {
					String sql  = "SELECT *   FROM use_form_print  WHERE  process_id = ? AND user_id_select = ?  ORDER BY  user_id_select DESC  limit 1 ";
					UseformPrint data =   jdbcTemplate.queryForObject(sql,new Object[] {process_id,user_id},new UseformPrintmapper());
					return data;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
			
			
		}
		
		@Override
		public UseformPrint getUseformPrint(int id){
		
			  try {
					String sql  = "SELECT *   FROM use_form_print  WHERE id =?  ";
					UseformPrint data =   jdbcTemplate.queryForObject(sql,new Object[] {id},new UseformPrintmapper());
					return data;
			  }catch(EmptyResultDataAccessException e) {
					 
				  return null;
				    
			}catch(IncorrectResultSizeDataAccessException e) {
					 throw new RuntimeException("More than one getStatus with the same Id .......");
			}
			
			
			
		}
		
		
		@Override
		@Transactional(isolation = Isolation.SERIALIZABLE)
		public List<Integer> SaveAllEdataAndGetIds(List<edata> edataList, int processId) {
		    List<Integer> generatedIds = new ArrayList<>();
		    
		 // 🌟 อัปเดต SQL ให้ครบ 38 ช่องเป๊ะๆ (จัดกลุ่มให้ดูง่ายๆ จะได้ไม่หลงครับ)
		    String sql = "INSERT INTO edata (data_eformid,data_eformrv,data_idadd,data_nameadd,process_id, id_oldsys, id_product_oldsys, id_refer_oldsys, "
		               + "data_value1, data_value2, data_value3, data_value4, data_value5, "
		               + "data_value6, data_value7, data_value8, data_value9, data_value10, "
		               + "data_value11, data_value12, data_value13, data_value14, data_value15, "
		               + "data_value16, data_value17, data_value18, data_value19, data_value20, "
		               + "data_value21, data_value22, data_value23, data_value24, data_value25, "
		               + "data_value26, data_value27, data_value28, data_value29, data_value30, data_title,id_customer_oldsys) "
		               + "VALUES ("
		               + "?, ?, ?, ?, ?, ?, ?, ?, " // 8 ตัวแรก (พวก data_eformid จนถึง id_refer_oldsys)
		               + "?, ?, ?, ?, ?, "          // 1-5
		               + "?, ?, ?, ?, ?, "          // 6-10
		               + "?, ?, ?, ?, ?, "          // 11-15
		               + "?, ?, ?, ?, ?, "          // 16-20
		               + "?, ?, ?, ?, ?, "          // 21-25
		               + "?, ?, ?, ?, ?, ?, ?"            // 26-30
		               + ")";                       // รวม 38 ตัวเป๊ะ!

		    for (edata e : edataList) {
		        
		        // แพ็กของลง Array เพื่อให้ลูปยัดค่าง่ายๆ โค้ดจะได้ไม่ยาว
		        Object[] edataInfo = {
		        		 e.getData_eformid(),   e.getData_eformrv(),  e.getData_idadd(),  e.getData_nameadd(), processId, 
		            e.getId_oldsys(), e.getId_product_oldsys(), e.getId_refer_oldsys(),
		            e.getData_value1(), e.getData_value2(), e.getData_value3(), e.getData_value4(), e.getData_value5(),
		            e.getData_value6(), e.getData_value7(), e.getData_value8(), e.getData_value9(), e.getData_value10(),
		            e.getData_value11(), e.getData_value12(), e.getData_value13(), e.getData_value14(), e.getData_value15(),
		            e.getData_value16(), e.getData_value17(), e.getData_value18(), e.getData_value19(), e.getData_value20(),
		            e.getData_value21(), e.getData_value22(), e.getData_value23(), e.getData_value24(), e.getData_value25(),
		            e.getData_value26(), e.getData_value27(), e.getData_value28(), e.getData_value29(), e.getData_value30(), e.getData_title(), e.getId_customer_oldsys()
		        };

		        KeyHolder keyHolder = new GeneratedKeyHolder();
		        
		        jdbcTemplate.update(connection -> {
		            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            for (int i = 0; i < edataInfo.length; i++) {
		                ps.setObject(i + 1, edataInfo[i]); 
		            }
		            return ps;
		        }, keyHolder);

		        // ดึง ID ที่เพิ่งรันมาเก็บใส่ List (มันจะเรียงตามลำดับ edataList หน้าบ้านเป๊ะๆ!)
		        if (keyHolder.getKey() != null) {
		            generatedIds.add(keyHolder.getKey().intValue());
		        } else {
		            generatedIds.add(0); // กันพังกรณีไม่ได้ ID กลับมา
		        }
		    }
		    
		    return generatedIds;
		}

		
		@Override
		public List<edata> getEdataByOldSysIds(List<String> oldSysIds) {
		    // 🛡️ กันพัง ถ้าไม่มีคีย์แม่ส่งมาเลย ก็คืน List ว่างกลับไป
		    if (oldSysIds == null || oldSysIds.isEmpty()) {
		        return new ArrayList<>();
		    }

		    // 🌟 สร้าง String ?,?,? ให้มีจำนวนเท่ากับขนาดของ List (เช่น มี 5 ID ก็จะได้ "?,?,?,?,?")
		    String inSql = String.join(",", Collections.nCopies(oldSysIds.size(), "?"));
		    
		    // ประกอบเป็น SQL โคตรเท่
		    String sql = "SELECT * FROM edata WHERE id_oldsys IN (" + inSql + ")";

		    // ใช้ RowMapper ปั้นกลับมาเป็น Object edata
		    return jdbcTemplate.query(sql, oldSysIds.toArray(), (rs, rowNum) -> {
		        edata e = new edata();
		        
		        // 🌟 ดึงเฉพาะค่าที่ต้องใช้สำหรับไปทำฟังก์ชัน LinK (ประหยัด RAM)
		        e.setData_id(rs.getInt("data_id"));        // สำคัญที่สุด PK ตัวแม่
		        e.setId_oldsys(rs.getString("id_oldsys")); // เอาไว้แมปปิ้ง
		        e.setData_title(rs.getString("data_title")); // ใช้ตอน LinK
		        
		        // (ถ้าพี่ต้องใช้ฟิลด์อื่นของแม่ด้วย ก็ดึงเพิ่มตรงนี้นะครับ rs.getString("..."))
		        
		        return e;
		    });
		}
		
		@Override
		public void LinkBatch(List<edata> mainList, List<edata> linkList) {
		    
		    String sql = "INSERT INTO relations (main_doc_name, relations_doc_name, main_id, relations_id, parent, tree) "
		               + "VALUES(?,?,?,?,?,?)";

		    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
		        
		        @Override
		        public void setValues(PreparedStatement ps, int i) throws SQLException {
		            edata main = mainList.get(i);
		            edata link = linkList.get(i);

		            // ลอจิกเดิมของพี่: เนื่องจากเป็นข้อมูล Import ใหม่เอี่ยม 
		            // จึงอนุมานได้เลยว่าเป็น Record แรก (parent=1, tree=รหัสตัวเอง) 
		            // ไม่ต้องเสียเวลาไป Select getRelations() ให้เปลือง DB ครับ
		            int parent = 1;
		            int tree = link.getData_id();

		            // ฉีดข้อมูลเข้า ? ทั้ง 8 ตัว
		            ps.setString(1, main.getData_title());
		            ps.setString(2, link.getData_title());
		            ps.setInt(3, main.getData_id());
		            ps.setInt(4, link.getData_id());
		            ps.setInt(5, parent);
		            ps.setInt(6, tree);
		        }

		        @Override
		        public int getBatchSize() {
		            return mainList.size(); // จำนวนแถวทั้งหมดที่ต้อง Insert
		        }
		    });
		    
		    System.out.println("🚀 Batch Inserted Relations (Main & Link): " + mainList.size() + " records.");
		}
		
		
		@Override
		public void SaveAllEdataOnly(List<edata> edataList, int processId) {
		    
		    // 🌟 SQL สำหรับ Revision: เพิ่ม temp_id และ revision เข้าไป
		    // (⚠️ ตรวจสอบชื่อคอลัมน์ใน DB พี่ด้วยนะครับว่าชื่อ "temp_id" และ "revision" หรือเปล่า)
		    String sql = "INSERT INTO edata ("
		               + "data_eformid, data_eformrv, data_idadd, data_nameadd, process_id, "
		               + "id_oldsys, id_product_oldsys, id_refer_oldsys, "
		               + "tempId, revision, " // 🎯 2 ช่องพิเศษสำหรับบอสตัวนี้
		               + "data_value1, data_value2, data_value3, data_value4, data_value5, "
		               + "data_value6, data_value7, data_value8, data_value9, data_value10, "
		               + "data_value11, data_value12, data_value13, data_value14, data_value15, "
		               + "data_value16, data_value17, data_value18, data_value19, data_value20, "
		               + "data_value21, data_value22, data_value23, data_value24, data_value25, "
		               + "data_value26, data_value27, data_value28, data_value29, data_value30) "
		               
		               + "VALUES ("
		               + "?, ?, ?, ?, ?, "    // 1-5
		               + "?, ?, ?, "          // 6-8 (Oldsys Keys)
		               + "?, ?, "             // 9-10 (TempId, Revision)
		               + "?, ?, ?, ?, ?, "    // 11-15 (Values)
		               + "?, ?, ?, ?, ?, "    // 16-20
		               + "?, ?, ?, ?, ?, "    // 21-25
		               + "?, ?, ?, ?, ?, "    // 26-30
		               + "?, ?, ?, ?, ?, "    // 31-35
		               + "?, ?, ?, ?, ?"      // 36-40
		               + ")";                 // รวม 40 ตัวเป๊ะ

		    // ใช้ BatchPreparedStatementSetter เพื่อความไวสูงสุด (ยิงรวดเดียวจบ)
		    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
		        
		        @Override
		        public void setValues(PreparedStatement ps, int i) throws SQLException {
		            edata e = edataList.get(i);
		            
		            // Group 1: System Info
		            ps.setInt(1, e.getData_eformid());
		            ps.setInt(2, e.getData_eformrv());
		            ps.setInt(3, e.getData_idadd());       // ถ้าเป็น 0 หรือ null เช็คดีๆ นะครับ
		            ps.setString(4, e.getData_nameadd());
		            ps.setInt(5, processId);

		            // Group 2: Old Keys
		            ps.setString(6, e.getId_oldsys());
		            ps.setString(7, e.getId_product_oldsys());
		            ps.setString(8, e.getId_refer_oldsys()); // อันนี้คือ sub_data_id ที่เราฝากไว้

		            // Group 3: Revision Specific 🎯
		            ps.setString(9, e.getTempId());    // ID แม่ที่เราไปค้นเจอจาก DB
		            ps.setInt(10, e.getRevision()); // เลข Revision จาก CSV

		            // Group 4: Data Values (1-30)
		            ps.setString(11, e.getData_value1());
		            ps.setString(12, e.getData_value2());
		            ps.setString(13, e.getData_value3());
		            ps.setString(14, e.getData_value4());
		            ps.setString(15, e.getData_value5());
		            ps.setString(16, e.getData_value6());
		            ps.setString(17, e.getData_value7());
		            ps.setString(18, e.getData_value8());
		            ps.setString(19, e.getData_value9());
		            ps.setString(20, e.getData_value10());
		            ps.setString(21, e.getData_value11());
		            ps.setString(22, e.getData_value12());
		            ps.setString(23, e.getData_value13());
		            ps.setString(24, e.getData_value14());
		            ps.setString(25, e.getData_value15());
		            ps.setString(26, e.getData_value16());
		            ps.setString(27, e.getData_value17());
		            ps.setString(28, e.getData_value18());
		            ps.setString(29, e.getData_value19());
		            ps.setString(30, e.getData_value20());
		            ps.setString(31, e.getData_value21());
		            ps.setString(32, e.getData_value22());
		            ps.setString(33, e.getData_value23());
		            ps.setString(34, e.getData_value24());
		            ps.setString(35, e.getData_value25());
		            ps.setString(36, e.getData_value26());
		            ps.setString(37, e.getData_value27());
		            ps.setString(38, e.getData_value28());
		            ps.setString(39, e.getData_value29());
		            ps.setString(40, e.getData_value30());
		        }

		        @Override
		        public int getBatchSize() {
		            return edataList.size();
		        }
		    });

		    System.out.println("🚀 Batch Inserted Revision Edata: " + edataList.size() + " records.");
		}
		
		
		@Override
		public int countDoc(String customerCode, int process_id, String source_param, int year) {
		    
		    // 1. 🛡️ Security Check (สำคัญมาก!)
		    // ป้องกัน SQL Injection ตรวจสอบว่าชื่อคอลัมน์ต้องเป็นตัวอักษร ตัวเลข หรือ _ เท่านั้น (เช่น data_value1)
		    if (source_param == null || !source_param.matches("^[a-zA-Z0-9_]+$")) {
		        System.err.println("Invalid column name: " + source_param);
		        return 0; // ถ้าคอลัมน์แปลกปลอมมา ให้คืนค่า 0 ไปเลย
		    }

		    // 2. 📝 สร้าง SQL Query
		    StringBuilder sql = new StringBuilder();
		    
		    // สมมติว่านับเฉพาะในตาราง edata ก็พอ (เพื่อความเร็ว) 
		    // ถ้าต้องการ join idoc แบบตัวอย่างเดิม ก็เพิ่ม LEFT JOIN ได้เลยครับ
		    sql.append("SELECT COUNT(*) FROM edata ");
		    sql.append("WHERE process_id = ? ");
		    
		    // เอาชื่อคอลัมน์มาต่อ String ตรงๆ
		    sql.append("AND ").append(source_param).append(" = ? ");
		    
		    // ⏱️ เงื่อนไขเวลาย้อนหลัง X ปี (แปลง String เป็น Date ก่อนเทียบ)
		    // DATE_SUB(CURDATE(), INTERVAL ? YEAR) คือ ถอยหลังไป X ปีจากวันนี้
		    sql.append("AND STR_TO_DATE(date_add, '%Y-%m-%d') >= DATE_SUB(CURDATE(), INTERVAL ? YEAR)");

		    // 3. 🎯 เตรียม Parameters (เรียงลำดับให้ตรงกับเครื่องหมาย ?)
		    Object[] params = new Object[]{
		        process_id, 
		        customerCode, 
		        year 
		    };

		    // 4. 🚀 Execute
		    try {
		        Integer count = jdbcTemplate.queryForObject(sql.toString(), params, Integer.class);
		        return count != null ? count : 0;
		    } catch (Exception e) {
		        System.err.println("Error counting docs: " + e.getMessage());
		        return 0; // ดัก Exception ไว้ เผื่อตารางพังหรือข้อมูลแปลกๆ หน้าเว็บจะได้ไม่ค้าง
		    }
		}
		
		
		@Override
		public void updateLatestData(int eformId) {
		    // ใช้ NOW() เพื่อดึงวันและเวลาปัจจุบันจาก Database Server โดยตรง (แม่นยำกว่าดึงจาก Java)
		    // ⚠️ หมายเหตุ: เช็คชื่อตาราง (edata) และ Primary Key (data_id) ให้ตรงกับ Database ของคุณนะครับ
		    String sql = "UPDATE edata SET data_dateupdate = NOW() WHERE data_id = ?";
		    
		    try {
		        int rowsAffected = jdbcTemplate.update(sql, eformId);
		        
		        if (rowsAffected > 0) {
		            System.out.println("✅ Updated data_dateupdate success for eformId: " + eformId);
		        } else {
		            System.out.println("⚠️ No record found to update for eformId: " + eformId);
		        }
		    } catch (Exception e) {
		        System.err.println("❌ Error updating data_dateupdate: " + e.getMessage());
		    }
		}


		
		

		// --------------------------------------------------------
	    // 1. เมธอดสำหรับนับจำนวน (COUNT & COUNT DISTINCT)
	    // --------------------------------------------------------
		@Override
	    public double countGamificationResult(int eformId, String conditionStatus, String userid,String startDate, String endDate, int extensionId, int isGlobal, String goalType) {
	        StringBuilder sql = new StringBuilder();
	        List<Object> params = new ArrayList<>();

	        // เช็คว่าเป็น COUNT_DISTINCT หรือไม่ (นับแบบไม่ซ้ำ)
	        if ("COUNT_DISTINCT".equals(goalType)) {
	            sql.append("SELECT COUNT(DISTINCT edata.data_id) ");
	        } else {
	            sql.append("SELECT COUNT(edata.data_id) ");
	        }

	        sql.append("FROM edata LEFT JOIN idoc ON edata.data_id = idoc.eform_id WHERE 1=1 ");

	        
	     // แยกเช็คตามประเภท: ส่วนขยาย VS เอกสารหลัก
	        if (extensionId > 0) {
	            // 📍 กรณีเป็น "ส่วนขยาย" 
	            // เจาะจงฟอร์มหลัก (eformId) และรหัสส่วนขยาย (extensionId)
	            sql.append(" AND edata.extension_id = ? ");
	            params.add(extensionId);
	            
	        } else {
	            // 📍 กรณีเป็น "เอกสารหลัก" (extensionId เป็น 0)
	            if (isGlobal == 1) {
	                // โหมด Global: กวาดทุกฟอร์มที่เข้ากระบวนการแล้ว
	                sql.append("AND edata.process > 0 AND edata.extension_id = 0");
	            } else {
	                // โหมดปกติ: เจาะจงแค่ฟอร์มหลักที่เลือก (เอาแค่นี้เลยตามที่คุณบอก!)
	                sql.append("AND edata.data_eformid = ? ");
	                params.add(eformId);
	            }
	        }
	        
	        

	        // สถานะ (เช่น ปิดการขายได้)
	        if (conditionStatus != null && !conditionStatus.trim().isEmpty() && !conditionStatus.equals("-")) {
	        	
	            sql.append("AND idoc.sender = ?  ");
		        params.add(userid);
	        	
	            sql.append("AND idoc.closing_status = ? "); // เช็คจาก idoc ตามที่คุณออกแบบไว้
	            params.add(conditionStatus);
	            
		        sql.append("AND idoc.date_end >= ? AND idoc.date_end <= ? ");
		        params.add(startDate + " 00:00:00");
		        params.add(endDate + " 23:59:59"); // ใส่เวลาให้ครอบคลุมทั้งวัน
	        }else {
	        	
	            // เงื่อนไขพนักงาน และ วันที่
	        	  sql.append("AND edata.data_idadd = ?  ");
			        params.add(userid);
		        
			        sql.append("AND edata.data_dateadd >= ? AND edata.data_dateadd <= ? ");
			        params.add(startDate + " 00:00:00");
			        params.add(endDate + " 23:59:59"); // ใส่เวลาให้ครอบคลุมทั้งวัน
	        }
	        
	        
	        System.out.println("sql   "+sql.toString());
	        System.out.println("conditionStatus  "+conditionStatus);
	        System.out.println("extensionId  "+extensionId);
	        System.out.println(" startDate  = "+startDate);
	        System.out.println(" endDate = "+endDate);
	        System.out.println("eformId   "+eformId);
	        
	        
	        
	        Double result = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Double.class);
	        
	        System.out.println("result  "+result);
	        
	        return result != null ? result : 0.0;
	    }


	 // --------------------------------------------------------
	    // 2. เมธอดสำหรับหาผลรวม (SUM) - ป้องกัน Error หากดึงผิดคอลัมน์
	    // --------------------------------------------------------
		@Override
		public double sumGamificationResult(int eformId, String targetColumn, String conditionStatus, String userid, String startDate, String endDate, int extensionId, int isGlobal) {
		    StringBuilder sql = new StringBuilder();
		    List<Object> params = new ArrayList<>();

		    // จัดการชื่อคอลัมน์เป้าหมายให้ถูกต้อง (เช่น เติม data_value ให้ครบ)
		    String colName = targetColumn;
		    if (!colName.startsWith("data_value")) {
		        colName = "data_value" + colName.replace("value", ""); // ป้องกันคำว่า value ซ้ำ
		    }

		    // ป้องกัน Error จากการบวกข้อความ โดยใช้ CAST(NULLIF(...)) เพื่อแปลงเป็นตัวเลข 
		    // ถ้าค่าเป็นข้อความว่าง ('') จะถูกแปลงเป็น NULL และ SUM() จะข้ามไป ไม่พัง
		    sql.append("SELECT SUM(CAST(NULLIF(edata.").append(colName).append(", '') AS DECIMAL(15,2))) ");
		    sql.append("FROM edata LEFT JOIN idoc ON edata.data_id = idoc.eform_id WHERE 1=1 ");

		    // --- ยกโครงสร้าง Global และ Extension มาจาก COUNT ---
		    if (extensionId > 0) {
		        // 📍 กรณีเป็น "ส่วนขยาย" 
		        sql.append(" AND edata.extension_id = ? ");
		        params.add(extensionId);
		    } else {
		        // 📍 กรณีเป็น "เอกสารหลัก" (extensionId เป็น 0)
		        if (isGlobal == 1) {
		            // โหมด Global: กวาดทุกฟอร์มที่เข้ากระบวนการแล้ว
		            sql.append("AND edata.process > 0 AND edata.extension_id = 0 ");
		        } else {
		            // โหมดปกติ: เจาะจงแค่ฟอร์มหลักที่เลือก
		            sql.append("AND edata.data_eformid = ? ");
		            params.add(eformId);
		        }
		    }

		    // --- ยกโครงสร้างแยกเส้นทาง idoc / edata มาจาก COUNT ---
		    if (conditionStatus != null && !conditionStatus.trim().isEmpty() && !conditionStatus.equals("-")) {
		        // โหมดมีสถานะ: เช็คจาก idoc (ปิดการขาย, จบงาน ฯลฯ)
		        sql.append("AND idoc.sender = ?  ");
		        params.add(userid);
		        
		        sql.append("AND idoc.closing_status = ? ");
		        params.add(conditionStatus);
		        
		        sql.append("AND idoc.date_end >= ? AND idoc.date_end <= ? ");
		        params.add(startDate + " 00:00:00");
		        params.add(endDate + " 23:59:59");
		        
		    } else {
		        // โหมดไม่มีสถานะ: เช็คจาก edata โดยตรง (แค่สร้างเอกสารก็รวมยอดเลย)
		        sql.append("AND edata.data_idadd = ?  ");
		        params.add(userid);
		        
		        sql.append("AND edata.data_dateadd >= ? AND edata.data_dateadd <= ? ");
		        params.add(startDate + " 00:00:00");
		        params.add(endDate + " 23:59:59");
		    }

		    try {
		        Double result = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Double.class);
		        return result != null ? result : 0.0;
		    } catch (Exception e) {
		        System.err.println("Warning: Gamification SUM Error (อาจมีการเลือกเป้าหมายเป็นคอลัมน์ข้อความ) -> " + e.getMessage());
		        return 0.0; // คืนค่า 0 แทนการแสดงหน้า Error ขาวๆ ให้ User
		    }
		}

	    // --------------------------------------------------------
	    // 3. เมธอดสำหรับนับเอกสารหมดอายุ (EXPIRY)
	    // --------------------------------------------------------
		@Override
	    public int countExpiredDocs(int eformId, String username, String dateDue) {
	        // ลอจิกนี้สมมติว่าคุณมีคอลัมน์ที่เก็บวันหมดอายุ (เช่น idoc.expire_date หรือ data_valueX)
	        // คุณอาจจะต้องเปลี่ยน `idoc.expire_date` ให้ตรงกับคอลัมน์จริงใน Database ของคุณนะครับ
	        
	        String sql = "SELECT COUNT(edata.data_id) FROM edata " +
	                     "LEFT JOIN idoc ON edata.data_id = idoc.eform_id " +
	                     "WHERE edata.data_eformid = ? " +
	                     "AND edata.data_idadd = ? " +
	                     "AND idoc.closing_status != 'Closed' " + // ไม่นับเอกสารที่ปิดงานไปแล้ว
	                     "AND idoc.expire_date < ?"; // หมดอายุก่อนวันที่เช็ค

	        Integer result = jdbcTemplate.queryForObject(sql, new Object[]{eformId, username, dateDue}, Integer.class);
	        return result != null ? result : 0;
	    }



	    
	 // --------------------------------------------------------
	 // มิติที่ 1: นับเอกสารที่ "ได้รับมอบหมาย" (Receiver)
	 // --------------------------------------------------------
		@Override
	 public double countReceivedDocs(int eformId, String conditionStatus, String targetUserid,String startDate, String endDate) {
	     StringBuilder sql = new StringBuilder();
	     List<Object> params = new ArrayList<>();

	     // สมมติว่าคอลัมน์ที่เก็บชื่อ "ผู้รับ" คือ idoc.to_name (ปรับให้ตรงกับ DB คุณนะครับ)
	     sql.append("SELECT COUNT(edata.data_id) FROM edata ");
	     sql.append("LEFT JOIN idoc ON edata.data_id = idoc.eform_id WHERE 1=1 ");
	     
	     sql.append("AND edata.data_eformid = ? ");
	     params.add(eformId);

	     // เช็คจากชื่อ "ผู้รับ" แทน "คนสร้าง"
	     sql.append("AND idoc.receiver = ?  ");
	     params.add(targetUserid);
	      sql.append("AND idoc.date_update >= ? AND idoc.date_update <= ? ");
	        params.add(startDate + " 00:00:00");
	        params.add(endDate + " 23:59:59"); // ใส่เวลาให้ครอบคลุมทั้งวัน

	     if (conditionStatus != null && !conditionStatus.trim().isEmpty() && !conditionStatus.equals("-")) {
	         sql.append("AND idoc.closing_status = ? ");
	         params.add(conditionStatus);
	     }

	     Double result = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Double.class);
	     return result != null ? result : 0.0;
	 }

	 // --------------------------------------------------------
	 // มิติที่ 2: นับงานในปฏิทิน (Calendar Follow-up)
	 // --------------------------------------------------------
		@Override
	 public double countCalendarEvents(String username, int targetStatus, String datePrefix) {
	     // datePrefix มักจะมาเป็น "2026-03" (รายเดือน) หรือ "2026-03-31" (รายวัน)
	     // เราต้องแยก Year กับ Month ไปเทียบกับตาราง Calendar
	     String[] dateParts = datePrefix.split("-");
	     String year = dateParts[0];
	     String month = dateParts.length > 1 ? dateParts[1] : "";
	     // ถ้ามี 0 นำหน้าเดือน ให้ตัดออกเพราะ Calendar เก็บเป็น int (เช่น 03 -> 3)
	     if (month.startsWith("0")) month = month.substring(1); 

	     String sql = "SELECT COUNT(*) FROM calendar WHERE by_name = ? AND status = ? AND year = ?";
	     List<Object> params = new ArrayList<>();
	     params.add(username);
	     params.add(targetStatus);
	     params.add(year);

	     if (!month.isEmpty()) {
	         sql += " AND month = ?";
	         params.add(month);
	     }

	     // ถ้ามีการส่งเป็นรายวัน (มี Day) ให้เช็ค Day ด้วย (เขียนเพิ่มเติมได้ถ้าต้องการ)

	     Integer result = jdbcTemplate.queryForObject(sql, params.toArray(), Integer.class);
	     return result != null ? result.doubleValue() : 0.0;
	 }
	    
	    
		@Override
		public double countReceivedWithin24Hours(int eformId, String conditionStatus, String userid, String startDate, String endDate, int isGlobal) {
		    StringBuilder sql = new StringBuilder();
		    List<Object> params = new ArrayList<>();

		    sql.append("SELECT COUNT(edata.data_id) FROM edata ");
		    sql.append("LEFT JOIN idoc ON edata.data_id = idoc.eform_id WHERE 1=1 ");

		    // 🌍 แยกเงื่อนไขแค่ว่าเป็น Global หรือ เจาะจงฟอร์ม
		    if (isGlobal == 1) {
		        // โหมด Global: ไม่สน eformId แต่ต้องเข้า Process แล้ว
		        sql.append("AND edata.process_id > 0 ");
		    } else {
		        // โหมดปกติ: เจาะจง eformId ตามที่เลือก
		        sql.append("AND edata.data_eformid = ? ");
		        params.add(eformId);
		    }

		    // 📌 กฎเหล็ก: ไม่ว่าโหมดไหน ต้องเป็นเอกสารหลักเท่านั้น (ไม่ใช่ส่วนขยาย)
		    sql.append("AND (edata.extension_id IS NULL OR edata.extension_id = 0) ");

		    // เช็คผู้รับ
		    sql.append("AND idoc.receiver = ? ");
		    params.add(userid);

		    // เช็คช่วงวันที่
		    sql.append("AND idoc.date_update >= ? AND idoc.date_update <= ? ");
		    params.add(startDate + " 00:00:00");
		    params.add(endDate + " 23:59:59");

		    // ✨ เช็ค SLA ไม่เกิน 24 ชม.
		    sql.append("AND edata.data_date_receiver IS NOT NULL ");
		    sql.append("AND TIMESTAMPDIFF(HOUR, edata.data_date_receiver, COALESCE(idoc.date_update, NOW())) > 24 ");
		    
		    
		    // สถานะ
		    if (conditionStatus != null && !conditionStatus.trim().isEmpty() && !conditionStatus.equals("-")) {
		        sql.append("AND idoc.closing_status = ? ");
		        params.add(conditionStatus);
		    }

		    try {
		        Double result = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Double.class);
		        return result != null ? result : 0.0;
		    } catch (Exception e) {
		        System.err.println("Error in SLA 24H query: " + e.getMessage());
		        return 0.0;
		    }
		}
		
		
		
		@Override
		public List<Map<String, Object>> getContactFrequencySummary(int processId, int extensionId, String dateColumn, boolean isOldCustomer) {
		    
		    // ป้องกัน SQL Injection สำหรับชื่อคอลัมน์
		    if (dateColumn == null || !dateColumn.startsWith("data_value")) {
		        dateColumn = "data_value1";
		    }

		    // เพิ่มเงื่อนไขสำหรับลูกค้าเก่า (เกรด A, B, C)
		    String gradeCondition = "";
		    if (isOldCustomer) {
		        gradeCondition = " AND r.register_grade IN ('A', 'B', 'C') ";
		    }

		    String sql = "SELECT " +
		                 "  CASE " +
		                 "    WHEN contact_count = 0 THEN '0' " +
		                 "    WHEN contact_count = 1 THEN '1' " +
		                 "    WHEN contact_count = 2 THEN '2' " +
		                 "    ELSE '3' " +
		                 "  END AS frequency_group, " +
		                 "  COUNT(1) AS total_customers, " +
		                 "  SUM(contact_count) AS exact_contacts " + 
		                 "FROM (" +
		                 "  SELECT r.id, COUNT(e.data_id) AS contact_count " +
		                 "  FROM register r " +
		                 "  LEFT JOIN edata e " +
		                 "    ON r.id = e.id_refer " +
		                 "    AND e.process_id = ? " +          // ใช้ ? แทน
		                 "    AND e.extension_id = ? " +        // ใช้ ? แทน
		                 "    AND YEAR(e." + dateColumn + ") = YEAR(CURRENT_DATE) " + 
		                 "  WHERE r.register_type = 'ลูกค้า' " + gradeCondition +
		                 "  GROUP BY r.id " +
		                 ") sub " +
		                 "GROUP BY " +
		                 "  CASE " +
		                 "    WHEN contact_count = 0 THEN '0' " +
		                 "    WHEN contact_count = 1 THEN '1' " +
		                 "    WHEN contact_count = 2 THEN '2' " +
		                 "    ELSE '3' " +
		                 "  END";

		    // ส่งค่า processId และ extensionId ผ่าน Parameter
		    return jdbcTemplate.queryForList(sql, processId, extensionId); 
		}
		
		
		
		
		@Override
		public FullResult searchRegistersDirect(registers filter, int limit, int random) {

		    FullResult result = new FullResult();
		    NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		    MapSqlParameterSource params = new MapSqlParameterSource();

		    // ตัวแปรเก็บโครงสร้าง SQL
		    StringBuilder selectSql = new StringBuilder("SELECT r.* ");
		    StringBuilder fromSql = new StringBuilder(" FROM register r ");
		    StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");

		    /* ==========================================================
		     * 1. เรียก Helper Method: จัดการ Query พิเศษ (Insight Dashboard)
		     * ========================================================== */
		    applyInsightModes(filter, selectSql, fromSql, whereSql);

		    /* ==========================================================
		     * 2. เรียก Helper Method: จัดการเงื่อนไขค้นหาทั่วไป (Standard Filters)
		     * ========================================================== */
		    applyStandardFilters(filter, whereSql, params);

		    /* ==========================================================
		     * 3. ประกอบร่าง SQL สำหรับนับจำนวน (COUNT)
		     * ========================================================== */
		    String countSql = "SELECT COUNT(1) " + fromSql.toString() + whereSql.toString();
		    params.addValue("limit", limit);
		    int totalCount = namedTemplate.queryForObject(countSql, params, Integer.class);

		    /* ==========================================================
		     * 4. ประกอบร่าง SQL สำหรับดึงข้อมูล (DATA) และ ORDER BY
		     * ========================================================== */
		    String orderColumns = (random > 0) ? "RAND()" : "r.updated_at DESC, r.created_at DESC";
		    
		    StringBuilder dataSql = new StringBuilder();
		    dataSql.append(selectSql.toString())
		           .append(fromSql.toString())
		           .append(whereSql.toString())
		           .append(" ORDER BY ").append(orderColumns)
		           .append(" LIMIT :limit");

		    List<registers> list = namedTemplate.query(dataSql.toString(), params, new registersmapper());

		    /* ==========================================================
		     * 5. คืนค่า Result
		     * ========================================================== */
		    result.setTotalCount(totalCount);
		    result.setRegisters(list);

		    return result;
		}

		
		
		
		
		
		
		
		
		
		
		/**
		 * 1. เมธอดจัดการ Dashboard Insights (ดักจับ Search Mode พิเศษ)
		 */
		private void applyInsightModes(registers filter, StringBuilder selectSql, StringBuilder fromSql, StringBuilder whereSql) {
		    String searchMode = filter.getSearchMode() != null ? filter.getSearchMode().toUpperCase() : "";

		 // 1.1 ประวัติการดำเนินการ (Register Processing) -> เกรดขึ้น/ลง
		    if (searchMode.equals("GRADE_UP") || searchMode.equals("GRADE_DOWN")) {
		        selectSql.setLength(0); // ล้างค่าเริ่มต้น
		        
		        // 🌟 1. เอาข้อความประวัติเกรด ไปฝากไว้ที่ extension_column_18 ก่อน
		        selectSql.append("SELECT CONCAT(TRIM(h.old_grade), ' -> ', TRIM(h.new_grade)) AS extension_column_19, r.* ");
		        
		        fromSql.append(" INNER JOIN register_grade_history h ON r.id = h.register_id ")
		               // 🌟 2. จอยหาประวัติล่าสุดเพื่อแก้ปัญหาข้อมูลเบิ้ล
		               .append(" INNER JOIN ( ")
		               .append("   SELECT MAX(id) AS latest_id FROM register_grade_history ")
		               .append("   WHERE YEAR(calculate_date) = YEAR(CURRENT_DATE) ")
		               .append("   GROUP BY register_id ")
		               .append(" ) latest ON h.id = latest.latest_id ")
		               .append(" AND TRIM(h.old_grade) IN ('A','B','C','NEW','NONE') ")
		               .append(" AND TRIM(h.new_grade) IN ('A','B','C','NEW','NONE') ");
		               
		        if (searchMode.equals("GRADE_UP")) {
		            whereSql.append(" AND FIELD(TRIM(h.new_grade), 'NONE', 'NEW', 'C', 'B', 'A') > FIELD(TRIM(h.old_grade), 'NONE', 'NEW', 'C', 'B', 'A') ");
		        } else {
		            whereSql.append(" AND FIELD(TRIM(h.new_grade), 'NONE', 'NEW', 'C', 'B', 'A') < FIELD(TRIM(h.old_grade), 'NONE', 'NEW', 'C', 'B', 'A') ");
		        }
		    }
		    // 1.2 โหมดการติดต่อ (Contact Modes)
		    else if (searchMode.startsWith("CONTACT_") || searchMode.startsWith("OLD_CONTACT_")) {
		        String reqProcessId = filter.getReqProcessId();
		        String reqExtId = filter.getReqExtId();
		        String reqDateCol = filter.getReqDateCol();
		        String reqDetailCol = "data_value2"; 

		        selectSql.setLength(0);
		        selectSql.append("SELECT ")
		                 .append("(SELECT GROUP_CONCAT(e.").append(reqDetailCol).append(" SEPARATOR ' | ') ")
		                 .append(" FROM edata e WHERE e.id_refer = CAST(r.id AS CHAR) ")
		                 .append(" AND e.process_id = '").append(reqProcessId).append("' ")
		                 .append(" AND e.extension_id = '").append(reqExtId).append("' ")
		                 .append(" AND YEAR(e.").append(reqDateCol).append(") = YEAR(CURRENT_DATE)) AS extension_column_20, ")
		                 .append(" r.* ");

		        String countSubquery = "(SELECT COUNT(1) FROM edata e WHERE e.id_refer = CAST(r.id AS CHAR) " +
		                               "AND e.process_id = '" + reqProcessId + "' " +
		                               "AND e.extension_id = '" + reqExtId + "' " +
		                               "AND YEAR(e." + reqDateCol + ") = YEAR(CURRENT_DATE))";

		        if (searchMode.endsWith("_0")) {
		            whereSql.append(" AND ").append(countSubquery).append(" = 0 ");
		        } else if (searchMode.endsWith("_1")) {
		            whereSql.append(" AND ").append(countSubquery).append(" = 1 ");
		        } else if (searchMode.endsWith("_2")) {
		            whereSql.append(" AND ").append(countSubquery).append(" = 2 ");
		        } else if (searchMode.endsWith("_3")) {
		            whereSql.append(" AND ").append(countSubquery).append(" >= 3 ");
		        }

		        if (searchMode.startsWith("OLD_CONTACT_")) {
		            whereSql.append(" AND r.register_grade IN ('A', 'B', 'C') ");
		        }
		    }
		    
		 // 1.3 Customer Aging & Document Status
		    else if (searchMode.startsWith("AGING_")) {
		        int reqProcessId = 55; 
		        int reqExtId = 5;
		        
		        // 🌟 ข้อควรระวัง: รอบก่อนเราแก้เป็น data_value3 นะครับ อย่าลืมใช้ 3 เผื่อวางโค้ดทับ
		        String reqDateCol = "data_value3"; 
		        
		        int foProcessId = 58; 
		        int qtProcessId = 60; 

		        // ตัวแปรสำหรับสร้างรหัสลูกค้าแบบต่อ String (เพื่อไม่ให้โค้ดรก)
		        String rCustCode = "CONCAT(IFNULL(r.register_code,''), IFNULL(r.register_daterun,''), '-', IFNULL(r.register_id,''))";

		        selectSql.setLength(0);
		        selectSql.append("SELECT ")
		                 .append("CONCAT('FO=', ")
		                 // ดึงยอด FO แค่เฉพาะคนนี้
		                 .append("  COALESCE((SELECT COUNT(1) FROM edata e INNER JOIN sales_followup sf ON e.data_id = sf.edata_id ")
		                 .append("  WHERE e.process_id = ").append(foProcessId).append(" AND e.extension_id = 0 AND sf.customer_code = ").append(rCustCode).append("), 0), ")
		                 .append("  ', QT=', ")
		                 // ดึงยอด QT แค่เฉพาะคนนี้
		                 .append("  COALESCE((SELECT COUNT(1) FROM edata e INNER JOIN sales_followup sf ON e.data_id = sf.edata_id ")
		                 .append("  WHERE e.process_id = ").append(qtProcessId).append(" AND e.extension_id = 0 AND sf.customer_code = ").append(rCustCode).append("), 0) ")
		                 .append(") AS extension_column_19, ")
		                 .append("r.* ");

		        // 🌟 เหลือ LEFT JOIN ไว้แค่ตาราง c (ความเก่า) เพราะต้องเอาไปใช้ดักเงื่อนไข WHERE ต่อ
		        fromSql.append(" LEFT JOIN (")
		               .append("    SELECT id_refer, MAX(STR_TO_DATE(").append(reqDateCol).append(", '%Y-%m-%d')) as last_contact ")
		               .append("    FROM edata WHERE process_id = ").append(reqProcessId).append(" AND extension_id = ").append(reqExtId)
		               .append("    GROUP BY id_refer ")
		               .append(" ) c ON c.id_refer = CAST(r.id AS CHAR) "); 

		        // ❌ ตาราง d (การนับ FO/QT ก้อนใหญ่) ถูกลบทิ้งไปแล้ว! ❌

		        // ---------------- กรองความเก่าของข้อมูลลูกค้า ----------------
		        if (searchMode.equals("AGING_3M")) {
		            whereSql.append(" AND DATEDIFF(CURRENT_DATE, c.last_contact) <= 90 ");
		        } else if (searchMode.equals("AGING_3_6M")) {
		            whereSql.append(" AND DATEDIFF(CURRENT_DATE, c.last_contact) > 90 AND DATEDIFF(CURRENT_DATE, c.last_contact) <= 180 ");
		        } else if (searchMode.equals("AGING_6_9M")) {
		            whereSql.append(" AND DATEDIFF(CURRENT_DATE, c.last_contact) > 180 AND DATEDIFF(CURRENT_DATE, c.last_contact) <= 270 ");
		        } else if (searchMode.equals("AGING_9_12M")) {
		            whereSql.append(" AND DATEDIFF(CURRENT_DATE, c.last_contact) > 270 AND DATEDIFF(CURRENT_DATE, c.last_contact) <= 365 ");
		        } else if (searchMode.equals("AGING_24M")) {
		            whereSql.append(" AND (c.last_contact IS NULL OR DATEDIFF(CURRENT_DATE, c.last_contact) > 365) ");
		        }
		    }
		}

		/**
		 * 2. เมธอดจัดการเงื่อนไขค้นหาทั่วไปที่ส่งมาจากหน้าจอกรอกข้อมูล
		 */
		private void applyStandardFilters(registers filter, StringBuilder whereSql, MapSqlParameterSource params) {
		    String searchMode = filter.getSearchMode() != null ? filter.getSearchMode().toUpperCase() : "";

		    // 🌟 แก้จุดที่ 2: เพิ่มเงื่อนไข !searchMode.startsWith("AGING_") เพื่อป้องกันดรอปดาวน์เกรดบนหน้าจอเข้าไปขัดขวางโหมดสถิติ AGING
		    if (filter.getRegister_grade() != null && !filter.getRegister_grade().isEmpty() 
		        && !searchMode.equals("GRADE_UP") && !searchMode.equals("GRADE_DOWN")
		        && !searchMode.startsWith("AGING_")) {
		        whereSql.append(" AND r.register_grade IN (:register_grade) ");
		        params.addValue("register_grade", filter.getRegister_grade());
		    }

		    // --- ส่วนเงื่อนไขอื่นๆ 2.2 - 2.12 ปล่อยไว้ตามเดิมได้เลยครับ ---
		    if (filter.getSearch_id() != null && !filter.getSearch_id().isEmpty()) {
		        String cleanSearchIds = filter.getSearch_id().replaceAll("\\s+", "");
		        whereSql.append(" AND FIND_IN_SET(CONCAT(r.register_code, r.register_daterun, '-', r.register_id), :search_id) > 0 ");
		        params.addValue("search_id", cleanSearchIds);
		    }
		    if (filter.getRegister_name() != null && !filter.getRegister_name().isEmpty()) {
		        whereSql.append(" AND r.register_name LIKE :register_name ");
		        params.addValue("register_name", "%" + filter.getRegister_name().trim() + "%");
		    }
		    if (filter.getRegister_status() != null && !filter.getRegister_status().isEmpty()) {
		        whereSql.append(" AND r.register_status IN (:register_status) ");
		        params.addValue("register_status", filter.getRegister_status());
		    }
		    if (filter.getRegister_type() != null && !filter.getRegister_type().isEmpty()) {
		        whereSql.append(" AND r.register_type IN (:register_type) ");
		        params.addValue("register_type", filter.getRegister_type());
		    }
		    if (filter.getRegister_groups() != null && !filter.getRegister_groups().isEmpty()) {
		        whereSql.append(" AND r.register_groups IN (:register_groups) ");
		        params.addValue("register_groups", filter.getRegister_groups());
		    }
		    if (filter.getBusiness_type() != null && !filter.getBusiness_type().isEmpty()) {
		        whereSql.append(" AND r.business_type IN (:business_type) ");
		        params.addValue("business_type", filter.getBusiness_type());
		    }
		    if (filter.getRegion() != null && !filter.getRegion().isEmpty()) {
		        whereSql.append(" AND r.region IN (:region) ");
		        params.addValue("region", filter.getRegion());
		    }
		    if (filter.getMobile_number() != null && !filter.getMobile_number().isEmpty()) {
		        whereSql.append(" AND r.mobile_number = :mobile_number ");
		        params.addValue("mobile_number", filter.getMobile_number().trim());
		    }
		    if (filter.getRegister_email() != null && !filter.getRegister_email().isEmpty()) {
		        whereSql.append(" AND r.register_email = :register_email ");
		        params.addValue("register_email", filter.getRegister_email().trim());
		    }
		    if (filter.getContact_person() != null && !filter.getContact_person().isEmpty()) {
		        whereSql.append(" AND r.contact_person LIKE :contact_person ");
		        params.addValue("contact_person", "%" + filter.getContact_person().trim() + "%");
		    }
		    if (filter.getContact_approvers() != null && !filter.getContact_approvers().isEmpty()) {
		        whereSql.append(" AND r.contact_approvers LIKE :contact_approvers ");
		        params.addValue("contact_approvers", "%" + filter.getContact_approvers().trim() + "%");
		    }
		}
		
		
		
		
		
		
		@Override
		public List<Map<String, Object>> getCustomerGradeSummary() {
		    // ดึงค่า register_grade มาตรงๆ และ Group By ตรงๆ ได้เลย
		    String sql = "SELECT " +
		                 "  register_grade AS grade, " +
		                 "  COUNT(1) AS total_count " +
		                 "FROM register " +
		                 "WHERE register_type = 'ลูกค้า' AND  register_status = 'ACTIVE' " +
		                 "GROUP BY register_grade";

		    return jdbcTemplate.queryForList(sql); 
		}
		
		@Override
		public List<Map<String, Object>> getGradeMovementSummary() {
		    String sql = "SELECT " +
		                 "  CASE " +
		                 "    WHEN FIELD(TRIM(h.new_grade), 'NONE', 'NEW', 'C', 'B', 'A') > FIELD(TRIM(h.old_grade), 'NONE', 'NEW', 'C', 'B', 'A') THEN 'UP' " +
		                 "    WHEN FIELD(TRIM(h.new_grade), 'NONE', 'NEW', 'C', 'B', 'A') < FIELD(TRIM(h.old_grade), 'NONE', 'NEW', 'C', 'B', 'A') THEN 'DOWN' " +
		                 "  END AS movement_type, " +
		                 "  COUNT(1) AS total_count " +
		                 "FROM register_grade_history h " +
		                 // 🌟 1. ดึงเฉพาะประวัติการเปลี่ยนเกรด "ล่าสุด" ของลูกค้าแต่ละคน
		                 "INNER JOIN (" +
		                 "  SELECT MAX(id) AS latest_id " +
		                 "  FROM register_grade_history " +
		                 "  WHERE YEAR(calculate_date) = YEAR(CURRENT_DATE) " +
		                 "  GROUP BY register_id " +
		                 ") latest ON h.id = latest.latest_id " +
		                 // --------------------------------------------------------
		                 "WHERE TRIM(h.old_grade) IN ('A', 'B', 'C', 'NEW', 'NONE') " +
		                 "  AND TRIM(h.new_grade) IN ('A', 'B', 'C', 'NEW', 'NONE') " +
		                 "  AND TRIM(h.old_grade) != TRIM(h.new_grade) " +
		                 "GROUP BY movement_type";

		    return jdbcTemplate.queryForList(sql); 
		}
		
		
		
		
		@Override
		public List<Map<String, Object>> getCustomerAgingAndDocsSummary(int contactProcessId, int contactExtId, String contactDateCol, int foProcessId, int qtProcessId) {
		    
		    if (contactDateCol == null || !contactDateCol.matches("^[a-zA-Z0-9_]+$")) {
		        contactDateCol = "data_value3";
		    }

		    // 💡 ข้อสังเกตการปรับแก้:
		    // 1. ดึง Group By ไปไว้ชั้นนอกสุดเลย ไม่ต้องทำ Nested ซ้อนกันหลายชั้น
		    // 2. ปรับจาก DATEDIFF มาใช้ DATE_SUB (ลบวันจากปัจจุบัน) แทน ช่วยลดการคำนวณ
		    // 3. ปรับ MAX(STR_TO_DATE) เป็น STR_TO_DATE(MAX) ทำงานเร็วกว่าหลายสิบเท่า

		    String sql = "SELECT " +
		                 "  CASE " +
		                 "    WHEN c.last_contact IS NULL THEN '>24M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY) THEN '<3M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY) THEN '3-6M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 270 DAY) THEN '6-9M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 365 DAY) THEN '9-12M' " +
		                 "    ELSE '>24M' " +
		                 "  END AS aging_bucket, " +
		                 "  COUNT(r.id) AS total_customers, " +
		                 "  COALESCE(SUM(d.fo_count), 0) AS total_fo, " +
		                 "  COALESCE(SUM(d.qt_count), 0) AS total_qt " +
		                 "FROM register r " +
		                 " " +
		                 "LEFT JOIN (" +
		                 "  SELECT id_refer, STR_TO_DATE(MAX(" + contactDateCol + "), '%Y-%m-%d') as last_contact " +
		                 "  FROM edata " +
		                 "  WHERE process_id = ? AND extension_id = ? " +
		                 "  GROUP BY id_refer" +
		                 ") c ON c.id_refer = CAST(r.id AS CHAR) " +
		                 " " +
		                 "LEFT JOIN (" +
		                 "  SELECT sf.customer_code, " +
		                 "    SUM(CASE WHEN e.process_id = ? THEN 1 ELSE 0 END) AS fo_count, " +
		                 "    SUM(CASE WHEN e.process_id = ? THEN 1 ELSE 0 END) AS qt_count " +
		                 "  FROM edata e " +
		                 "  INNER JOIN sales_followup sf ON e.data_id = sf.edata_id " +
		                 "  WHERE e.process_id IN (?, ?) AND e.extension_id = 0 " +
		                 "  GROUP BY sf.customer_code " +
		                 ") d ON d.customer_code = CONCAT(IFNULL(r.register_code,''), IFNULL(r.register_daterun,''), '-', IFNULL(r.register_id,'')) " +
		                 " " +
		                 "WHERE r.register_type = 'ลูกค้า' " +
		                 "GROUP BY " +
		                 "  CASE " +
		                 "    WHEN c.last_contact IS NULL THEN '>24M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY) THEN '<3M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY) THEN '3-6M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 270 DAY) THEN '6-9M' " +
		                 "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 365 DAY) THEN '9-12M' " +
		                 "    ELSE '>24M' " +
		                 "  END";

		    return jdbcTemplate.queryForList(sql, 
		            contactProcessId, contactExtId, 
		            foProcessId, qtProcessId, foProcessId, qtProcessId);
		}
		
		
		
		@Override
		public List<Map<String, Object>> executeDynamicQuery(String dynamicSql) {
		    // 1. ถ้า SQL ว่างเปล่า ให้ส่ง List เปล่าๆ กลับไป
		    if (dynamicSql == null || dynamicSql.trim().isEmpty()) {
		        return new ArrayList<>();
		    }

		    // 2. ยิง Query ตรงๆ เข้า Database
		    try {
		        return jdbcTemplate.queryForList(dynamicSql);
		    } catch (Exception e) {
		        System.err.println("SQL Execution Error: " + e.getMessage());
		        return new ArrayList<>();
		    }
		}
		
		@Override
		public void updateRegGradeBatch(List<Object[]> batchArgs) {
		    String sql = "UPDATE register SET register_grade = ? WHERE id = ?";
		    
		    // batchArgs จะมีโครงสร้างเป็น [ [newGrade, id], [newGrade, id], ... ]
		    jdbcTemplate.batchUpdate(sql, batchArgs);
		}
		
		
		

		

/**
 * ดึงสรุปลูกค้าที่เคยเปิด FO แยกตาม register_groups, ปีของ FO, และช่วงเดือนที่ผ่านไปนับจากติดต่อล่าสุด
 *
 * @param foDocumentId  document_id ของ FO (เช่น 110)
 * @param contactExtId  extension_id ของการติดต่อ (เช่น 5)
 * @param yearCount     จำนวนปีย้อนหลัง (เช่น 5)
 */
		@Override
public List<Map<String, Object>> getFoCustomerSummaryByGroupAndYear(
        int foDocumentId,
        int contactExtId,
        int yearCount) {

    // ป้องกันค่าผิดพลาด
    if (yearCount <= 0 || yearCount > 20) yearCount = 5;

    String sql =
        // ================================================================
        // ชั้นนอกสุด: GROUP BY กลุ่ม + ปี + aging bucket → นับจำนวนลูกค้า
        // ================================================================
        "SELECT " +
        "  r.register_groups, " +
        "  YEAR(i.`date`) AS fo_year, " +
        "  CASE " +
        "    WHEN c.last_contact IS NULL                                          THEN '>24M' " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 90  DAY)     THEN '<3M'  " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY)     THEN '3-6M' " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 270 DAY)     THEN '6-9M' " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 365 DAY)     THEN '9-12M' " +
        "    ELSE '>24M' " +
        "  END AS aging_bucket, " +
        "  COUNT(DISTINCT r.id) AS total_customers " +

        // ================================================================
        // ตาราง register → เป็นตารางหลัก (ลูกค้า)
        // ================================================================
        "FROM register r " +

        // ================================================================
        // JOIN idoc → เอกสาร FO (document_id = 110) ในช่วง N ปีล่าสุด
        // เชื่อมผ่าน edata.data_value2 = CONCAT(register_code, register_daterun, '-', register_id)
        // ================================================================
        "INNER JOIN edata e " +
        "  ON e.data_value2 = CONCAT(IFNULL(r.register_code,''), IFNULL(r.register_daterun,''), '-', IFNULL(r.register_id,'')) " +
        "INNER JOIN idoc i " +
        "  ON i.eform_id = e.data_id " +
        "  AND i.document_id = ? " +
        "  AND YEAR(i.`date`) >= YEAR(CURRENT_DATE) - ? " +

        // ================================================================
        // LEFT JOIN → หาวันที่ติดต่อล่าสุด (extension_id = 5, id_refer = register.id)
        // ================================================================
        "LEFT JOIN ( " +
        "  SELECT " +
        "    id_refer, " +
        "    DATE(MAX(data_value2)) AS last_contact " +  // ✅ DATE() รับได้ทั้ง '2024-03-15' และ '2024-03-15 09:30:00'
        "  FROM edata " +
        "  WHERE extension_id = ? " +
        "    AND data_value2 IS NOT NULL " +
        "    AND data_value2 != '' " +
        "  GROUP BY id_refer " +
        ") c ON c.id_refer = CAST(r.id AS CHAR) " +

        // ================================================================
        // กรองเฉพาะ register_type = 'ลูกค้า'
        // ================================================================
        "WHERE r.register_type = 'ลูกค้า' " +
        "  AND r.register_status = 'ACTIVE' " +

        // ================================================================
        // GROUP BY: กลุ่ม + ปี FO + aging bucket
        // ================================================================
        "GROUP BY " +
        "  r.register_groups, " +
        "  YEAR(i.`date`), " +
        "  CASE " +
        "    WHEN c.last_contact IS NULL                                          THEN '>24M' " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 90  DAY)     THEN '<3M'  " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY)     THEN '3-6M' " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 270 DAY)     THEN '6-9M' " +
        "    WHEN c.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 365 DAY)     THEN '9-12M' " +
        "    ELSE '>24M' " +
        "  END " +

        // ================================================================
        // เรียงตาม: กลุ่ม → ปี (ล่าสุดก่อน) → aging bucket
        // ================================================================
        "ORDER BY r.register_groups, fo_year DESC, aging_bucket";

    return jdbcTemplate.queryForList(sql,
            foDocumentId,   // document_id = 110
            yearCount,      // YEAR() - N ปีย้อนหลัง
            contactExtId);  // extension_id = 5
}



/**
 * ดึงรายชื่อลูกค้าจริงๆ ตาม register_groups + fo_year + aging_bucket
 * สำหรับ export CSV
 *
 * @param group        register_groups
 * @param foYear       ปีที่เปิด FO (เช่น 2025)
 * @param agingBucket  aging bucket (<3M, 3-6M, 6-9M, 9-12M, >24M)
 * @param foDocumentId document_id ของ FO (110)
 * @param contactExtId extension_id ของการติดต่อ (5)
 */
		@Override
public List<Map<String, Object>> getFoCustomerDetailForExport(
        String group,
        int foYear,
        String agingBucket,
        int foDocumentId,
        int contactExtId) {

    // ================================================================
    // สร้าง HAVING condition ตาม aging_bucket ที่ส่งมา
    // ================================================================
    String agingCondition;
    switch (agingBucket) {
        case "<3M":
            agingCondition = "DATE(MAX(ec.data_value2)) >= DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY)";
            break;
        case "3-6M":
            agingCondition = "DATE(MAX(ec.data_value2)) >= DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY) " +
                             "AND DATE(MAX(ec.data_value2)) < DATE_SUB(CURRENT_DATE, INTERVAL 90 DAY)";
            break;
        case "6-9M":
            agingCondition = "DATE(MAX(ec.data_value2)) >= DATE_SUB(CURRENT_DATE, INTERVAL 270 DAY) " +
                             "AND DATE(MAX(ec.data_value2)) < DATE_SUB(CURRENT_DATE, INTERVAL 180 DAY)";
            break;
        case "9-12M":
            agingCondition = "DATE(MAX(ec.data_value2)) >= DATE_SUB(CURRENT_DATE, INTERVAL 365 DAY) " +
                             "AND DATE(MAX(ec.data_value2)) < DATE_SUB(CURRENT_DATE, INTERVAL 270 DAY)";
            break;
        default: // >24M หรือ null
            agingCondition = "DATE(MAX(ec.data_value2)) < DATE_SUB(CURRENT_DATE, INTERVAL 365 DAY) " +
                             "OR MAX(ec.data_value2) IS NULL";
            break;
    }

    String sql =
        "SELECT " +
        "  CONCAT(IFNULL(r.register_code,''), IFNULL(r.register_daterun,''), '-', IFNULL(r.register_id,'')) AS code, " +
        "  r.register_name   AS name, " +
        "  r.register_groups  AS `group`, " +
        "  r.register_status AS status, " +
        // ดึงประวัติการสื่อสารล่าสุด (extension_column_1 style = วันที่ล่าสุด)
        "  DATE(MAX(ec.data_value2)) AS history " +

        "FROM register r " +

        // JOIN edata + idoc → กรอง FO ตามปี
        "INNER JOIN edata e " +
        "  ON e.data_value2 = CONCAT(IFNULL(r.register_code,''), IFNULL(r.register_daterun,''), '-', IFNULL(r.register_id,'')) " +
        "INNER JOIN idoc i " +
        "  ON i.eform_id = e.data_id " +
        "  AND i.document_id = ? " +
        "  AND YEAR(i.`date`) = ? " +

        // LEFT JOIN edata การติดต่อ (extension_id = 5)
        "LEFT JOIN edata ec " +
        "  ON ec.id_refer = CAST(r.id AS CHAR) " +
        "  AND ec.extension_id = ? " +
        "  AND ec.data_value2 IS NOT NULL " +
        "  AND ec.data_value2 != '' " +

        "WHERE r.register_type   = 'ลูกค้า' " +
        "  AND r.register_status = 'ACTIVE' " +
        "  AND r.register_groups  = ? " +

        "GROUP BY r.id, r.register_code, r.register_daterun, r.register_id, " +
        "         r.register_name, r.register_groups, r.register_status " +

        // กรอง aging bucket ด้วย HAVING
        "HAVING " + agingCondition + " " +

        "ORDER BY r.register_name";

    return jdbcTemplate.queryForList(sql,
            foDocumentId,   // document_id = 110
            foYear,         // ปี FO
            contactExtId,   // extension_id = 5
            group);         // register_groups
}



		@Override
		public List<Map<String, Object>> getFoQtCustomerSummaryByGroupAndYear(
		        int foDocumentId,
		        int qtDocumentId,
		        int contactExtId,
		        int yearCount) {

		    if (yearCount <= 0 || yearCount > 20) yearCount = 5;

		    // 🟢 1. Query ย่อย: ดึงประวัติการติดต่อล่าสุด
		    String contactSub =
		        "SELECT id_refer, DATE(MAX(data_value2)) AS last_contact " +
		        "FROM edata " +
		        "WHERE extension_id = ? " +
		        "  AND data_value2 IS NOT NULL AND data_value2 != '' " +
		        "GROUP BY id_refer";

		    // 🟢 2. Query ย่อย: จัดเตรียมกลุ่มลูกค้าตั้งต้น
		    String baseCustomerSub =
		        "SELECT r.id, r.register_groups, " +
		        "  CONCAT(IFNULL(r.register_code,''), IFNULL(r.register_daterun,''), '-', IFNULL(r.register_id,'')) AS cust_code, " +
		        "  CASE " +
		        "    WHEN lc.last_contact IS NULL THEN '>24M' " +
		        "    WHEN lc.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 6 MONTH) THEN '>3M' " +
		        "    WHEN lc.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 9 MONTH) THEN '>6M' " +
		        "    WHEN lc.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 12 MONTH) THEN '>9M' " +
		        "    WHEN lc.last_contact >= DATE_SUB(CURRENT_DATE, INTERVAL 24 MONTH) THEN '>12M' " +
		        "    ELSE '>24M' " +
		        "  END AS aging_bucket " +
		        "FROM register r " +
		        "LEFT JOIN (" + contactSub + ") lc ON lc.id_refer = CAST(r.id AS CHAR) " +
		        "WHERE r.register_type = 'ลูกค้า' AND r.register_status = 'ACTIVE' " +
		        "  AND (lc.last_contact <= DATE_SUB(CURRENT_DATE, INTERVAL 3 MONTH) OR lc.last_contact IS NULL) " +
		        "  AND NOT EXISTS ( " +
		        "    SELECT 1 FROM segmentation s " +
		        "    WHERE s.status != 0 AND FIND_IN_SET(r.id, s.segmentation_member_id) > 0 " +
		        "  )";

		    // 🟢 3. FO Query (🚀 ลบเงื่อนไขเตะ QT ทิ้งแล้ว! ใครมี FO ดึงมาให้หมด)
		    String foSql =
		        "SELECT c.register_groups, YEAR(i.`date`) AS doc_year, 'FO' AS doc_type, '-' AS closing_status, c.aging_bucket, c.id " +
		        "FROM (" + baseCustomerSub + ") c " +
		        "INNER JOIN idoc i " +
		        "  ON i.document_id = ? " +
		        "  AND i.`date` >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL ? YEAR), '%Y-01-01') " +
		        "INNER JOIN edata e " +
		        "  ON i.eform_id = e.data_id AND e.data_value2 = c.cust_code";

		    // 🟢 4. QT Win Query 
		    String qtWinSql =
		        "SELECT c.register_groups, YEAR(i.`date_end`) AS doc_year, 'QT' AS doc_type, 'ปิดได้' AS closing_status, c.aging_bucket, c.id " +
		        "FROM (" + baseCustomerSub + ") c " +
		        "INNER JOIN idoc i " +
		        "  ON i.document_id = ? " +
		        "  AND i.`date_end` >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL ? YEAR), '%Y-01-01') " +
		        "  AND i.closing_status IN ('ปิดการขายได้', 'ปิดการขายได้ยืนยันคำสั่งซื้อเเล้ว') " +
		        "INNER JOIN edata e " +
		        "  ON i.eform_id = e.data_id AND e.data_value1 = c.cust_code";

		    // 🟢 5. QT Lose Query
		    String qtLoseSql =
		        "SELECT c.register_groups, YEAR(i.`date_end`) AS doc_year, 'QT' AS doc_type, 'ปิดไม่ได้' AS closing_status, c.aging_bucket, c.id " +
		        "FROM (" + baseCustomerSub + ") c " +
		        "INNER JOIN idoc i " +
		        "  ON i.document_id = ? " +
		        "  AND i.`date_end` >= DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL ? YEAR), '%Y-01-01') " +
		        "  AND (i.closing_status NOT IN ('ปิดการขายได้', 'ปิดการขายได้ยืนยันคำสั่งซื้อเเล้ว') OR i.closing_status IS NULL) " +
		        "INNER JOIN edata e " +
		        "  ON i.eform_id = e.data_id AND e.data_value5 = c.cust_code";

		    // 🟢 6. ประกอบร่าง Query หลักและรวมข้อมูล
		    String sql =
		        "SELECT register_groups, doc_year, doc_type, closing_status, aging_bucket, COUNT(DISTINCT id) AS total_customers " +
		        "FROM ( " +
		        "  (" + foSql + ") " +
		        "  UNION ALL " +
		        "  (" + qtWinSql + ") " +
		        "  UNION ALL " +
		        "  (" + qtLoseSql + ") " +
		        ") AS combined " +
		        "GROUP BY register_groups, doc_year, doc_type, closing_status, aging_bucket " +
		        "ORDER BY register_groups, doc_year DESC, doc_type, closing_status, aging_bucket";

		    // 🟢 7. ยิงเข้า Database (🚀 ตัด qtDocumentId ที่ใช้ดัก QT ใน FO ออกไป 2 ตัว)
		    return jdbcTemplate.queryForList(sql,
		        
		        // --- 📍 Parameters สำหรับก้อน FO Sql ---
		        contactExtId,  // สำหรับเช็ค extension_id ใน baseCustomerSub
		        foDocumentId,  // สำหรับเช็คเอกสาร FO
		        yearCount,     // สำหรับปีเอกสาร FO
		        
		        // --- 📍 Parameters สำหรับก้อน QT Win Sql ---
		        contactExtId,  // สำหรับเช็ค extension_id ใน baseCustomerSub รอบสอง
		        qtDocumentId,  // สำหรับเช็คเอกสาร QT Win
		        yearCount,     // สำหรับปีเอกสาร QT Win
		        
		        // --- 📍 Parameters สำหรับก้อน QT Lose Sql ---
		        contactExtId,  // สำหรับเช็ค extension_id ใน baseCustomerSub รอบสาม
		        qtDocumentId,  // สำหรับเช็คเอกสาร QT Lose
		        yearCount      // สำหรับปีเอกสาร QT Lose
		    );
		}
		
		
		@Override
		public void insertCallLog(String employeeName, String phoneNumber,
			 long durationSeconds, String startTime, int idRefer) {
					long minutes = (durationSeconds + 59) / 60;
					String value1 = "ติดตาม " + phoneNumber;
					
					String sql =
					"INSERT INTO edata " +
					"  (extension_id, data_idadd, data_value1, data_value2, data_value5, id_refer) " +
					"VALUES (6, ?, ?, ?, ?, ?)";
					
					jdbcTemplate.update(sql,
					employeeName,
					value1,
					minutes,
					startTime,
					idRefer
					);
		}
		
		public int findIdByPhone(String phone) {
		    if (phone == null || phone.trim().isEmpty()) {
		        return 0;
		    }

		    // 1. ทำความสะอาดเบอร์โทรตั้งต้น: ลบทุกอย่างที่ไม่ใช่ตัวเลขออก
		    String cleanPhone = phone.replaceAll("[^0-9]", "");
		    
		    if (cleanPhone.isEmpty()) {
		        return 0;
		    }

		    // 2. ดึงแค่ 9 ตัวหลังสุด (ตัดปัญหา 0 นำหน้า หรือรหัสประเทศ +66)
		    if (cleanPhone.length() > 9) {
		        cleanPhone = cleanPhone.substring(cleanPhone.length() - 9);
		    }
		    
		    // 🌟 3. อัปเกรด: ใส่ % ทั้งหน้าและหลัง! 
		    // หมายความว่า "ขอแค่มีเลข 9 ตัวนี้ติดกัน ซ่อนอยู่ตรงไหนในคอลัมน์ก็ได้"
		    // จะช่วยแก้ปัญหาเบอร์ที่ต่อท้ายด้วยลูกน้ำ (,) หรือคำว่า "ต่อ 8" ได้ทั้งหมด
		    String searchParam = "%" + cleanPhone + "%";

		    // 4. ฝั่ง SQL: ถอดขีด (-) และช่องว่าง ( ) ออกก่อนค้นหา
		    // ถึงใน DB จะเก็บ "081-234-5678 ต่อ 8" มันจะถูกแปลงชั่วคราวเป็น "0812345678ต่อ8" แล้วควานหาเจอทันที
		    String sql =
		        "SELECT id FROM register " +
		        "WHERE REPLACE(REPLACE(phone_number, '-', ''), ' ', '') LIKE ? " +
		        "   OR REPLACE(REPLACE(mobile_number, '-', ''), ' ', '') LIKE ? " +
		        "   OR REPLACE(REPLACE(approvers_number, '-', ''), ' ', '') LIKE ? " +
		        "   OR REPLACE(REPLACE(person_number, '-', ''), ' ', '') LIKE ? " +
		        "LIMIT 1";

		    List<Map<String, Object>> rows = jdbcTemplate.queryForList(
		        sql, searchParam, searchParam, searchParam, searchParam
		    );

		    if (rows.isEmpty()) {
		        return 0;
		    }

		    Object id = rows.get(0).get("id");
		    return id != null ? Integer.parseInt(id.toString()) : null;
		}
} 



