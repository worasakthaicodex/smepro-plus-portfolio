package com.smepro.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smepro.models.BuildIn;
import com.smepro.models.EdataReportbuildIn;
import com.smepro.models.FilterChain;
import com.smepro.models.FilterCriteria;
import com.smepro.models.LoadApp;
import com.smepro.models.Measurement_rules;
import com.smepro.models.Measurement_values;
import com.smepro.models.PlatformReport;
import com.smepro.models.PrepareReport;
import com.smepro.models.QuickQuery;
import com.smepro.models.Segmentation;
import com.smepro.models.TabManagement;
import com.smepro.models.backup;
import com.smepro.models.cm;
import com.smepro.models.edata;
import com.smepro.models.report;
import com.smepro.rowmapper.BuildInmapper;
import com.smepro.rowmapper.EdataReportbuildInmapper;
import com.smepro.rowmapper.FilterChainmapper;
import com.smepro.rowmapper.FilterCriteriamapper;
import com.smepro.rowmapper.Measurement_rulesmappers;
import com.smepro.rowmapper.Measurement_valuesmappers;
import com.smepro.rowmapper.PrepareReportmapper;
import com.smepro.rowmapper.QuickQuerymapper;
import com.smepro.rowmapper.TabManagementmapper;
import com.smepro.rowmapper.backupmapper;
import com.smepro.rowmapper.cmmapper;
import com.smepro.rowmapper.edatamapper3;
import com.smepro.rowmapper.reportmapper;



public class reportDAOlmpl implements reportDAO {

	private JdbcTemplate jdbcTemplate;
	
	public reportDAOlmpl(DataSource dataSoruce) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
	}
	
	
	
	@Override
	public List<report> getall(int idrefer) {
		String sql = "SELECT * from report  where report_id_refer=?   order by report_for desc  limit 32  ";
		List<report> reports  =  jdbcTemplate.query(sql,new Object[]{idrefer},new reportmapper());
		return reports;
	}
	@Override
	public List<report> getalls(int idrefer) {
		String sql = "SELECT * from report  where report_id_refer=?   order by report_for desc  limit 500  ";
		List<report> reports  =  jdbcTemplate.query(sql,new Object[]{idrefer},new reportmapper());
		return reports;
	}
	
	public int numfors(String fors,int id) {
		String sql = "select count(*) from  report where report_for = ? and report_id_refer=?    ";
		return jdbcTemplate.queryForObject(
                sql, new Object[] {fors,id }, Integer.class);
	}
	
	
	@Override
	public report getbyid(int id) {
		String sql = "SELECT * from report  where report_id=?  ";
		report report  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new reportmapper());
		return report;
	}
	

	@Override
	public report getbyidrefer(int id) {
		  try {
				String sql = "SELECT * from report  where report_id_refer=?  limit 1 ";
				report report  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new reportmapper());
				return report;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }

	}
	
	
	
	
	@Override
	public int delete(int id) {
		 String sql="delete from report where report_id = "+id;    
		    return jdbcTemplate.update(sql);
	}


	@Override
	public int save(report reports) {
		Object[] reportInfo = { 
				reports.getReport_name(),
				reports.getReport_id_refer(),
				reports.getReport_date(),
				reports.getReport_for(),
				reports.getReport_by(),
				reports.getReport_month(),
				reports.getReport_start(),
				reports.getReport_end(),
				reports.getReport_data(),
				reports.getReport_report(),
				};
				String sql = "INSERT INTO report(report_name,report_id_refer,report_date,report_for,report_by,report_month,report_start,report_end,report_data,report_report) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql,reportInfo);
	}

	@Override
	public int update(report reports) {
		Object[] reportInfo = { 
				reports.getTitle1(),
				reports.getTitle2(),
				reports.getTitle3(),
				reports.getTitle4(),
				reports.getReport_report(),
				reports.getFoot(),
				reports.getReport_id()
				};
				String sql = "UPDATE report set title1 = ? , title2 = ?, title3 = ? , title4 = ?,report_report =?,foot =?  WHERE  report_id =?  ";
				return jdbcTemplate.update(sql,reportInfo);
	}



	@Override
	public int setStatus(int report_id) {
				String sql = "UPDATE report set report_status = 1 WHERE  report_id =?  ";
				return jdbcTemplate.update(sql,new Object[]{report_id});
	}



	@Override
	public PrepareReport getPrepare(PlatformReport Report) {
		

		
		  try {
				String sql = "SELECT * from appreport  where source_id=? AND source_type =? limit 1  ";
				PrepareReport data  =  jdbcTemplate.queryForObject(sql,new Object[]{Report.getFrom(),Report.getFrom_type()},new PrepareReportmapper());
				return data;
		 
		 }catch(EmptyResultDataAccessException e) {
			 
			 int sourceTypeMethod = 0;
			 
			 if(Report.getFrom_type().equals("Profile") ) { sourceTypeMethod = 1;}
			 
			 Object[] reportInfo = { 
					 	Report.getFrom(),
					 	Report.getFrom_type(),
					 	sourceTypeMethod,
					 	Report.getProcess_refer(),
					 	Report.getProcess_refer_name()
						};
						String sql = "INSERT INTO appreport(source_id,source_type,source_type_method,process_id,source_name) "
								+ "VALUES(?,?,?,?,?)";
						jdbcTemplate.update(sql,reportInfo);
			 
						sql = "SELECT * from appreport  where source_id=? AND source_type =? limit 1  ";
						PrepareReport data  =  jdbcTemplate.queryForObject(sql,new Object[]{Report.getFrom(),Report.getFrom_type()},new PrepareReportmapper());
			 
			 return data;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	}
	
	
	
	@Override
	public PrepareReport getPrepareReportById(int id) {
	
		  try {
				String sql = "SELECT * from appreport  where id =? limit 1  ";
				PrepareReport data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new PrepareReportmapper());
				return data;
		 
		 }catch(EmptyResultDataAccessException e) {
		
			 return null;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	}

	
	@Override
	public FilterChain getPrepareQueryChainById(int id) {
			String sql = "SELECT * from filter_chain  where id =?   ";
			FilterChain data  =  jdbcTemplate.queryForObject(sql,new Object[]{id},new FilterChainmapper());
			return data;
	}
	
	@Override
	public void setEnabledActiveQueryChain(int id) {
		String sql = "UPDATE filter_chain SET status = 1 - status WHERE id = ?";
	    jdbcTemplate.update(sql, id);
			
	}
	
	
	@Override
	public void saveQuickQuery(QuickQuery quickQuery) {
	    
		
		   if(quickQuery.getId_refer() == null ) {return;}
		   if(quickQuery.getField() == null ) {return;}
		   if(quickQuery.getType() == null ) {return;}
		   
		
	    // 1. แยก String ออกเป็น Array ด้วย comma
	    // ใช้ trim() เผื่อมีเว้นวรรคหลัง comma
	    String[] ids = quickQuery.getId_refer().split(",");
	    String[] fields = quickQuery.getField().split(",");
	    String[] types = quickQuery.getType().split(",");
		
	    
	    
		String sqldelete = "DELETE from  quick_query where FIND_IN_SET(id_refer,?) ";
		jdbcTemplate.update(sqldelete,new Object[]{quickQuery.getId_refer()});
		
	    String sql = "INSERT INTO quick_query (id_refer, field, type) VALUES (?, ?, ?)";

	

	    // *ควรตรวจสอบว่าความยาวของทั้ง 3 array เท่ากันหรือไม่ เพื่อป้องกัน error

	    // 2. เตรียมข้อมูลสำหรับ Batch
	    List<Object[]> batchArgs = new ArrayList<>();

	    for (int i = 0; i < ids.length; i++) {
	        // สร้าง Object array สำหรับแถวนั้นๆ (เรียงตามลำดับ ? ใน SQL)
	        Object[] values = new Object[] {
	            ids[i],    // id_refer
	            fields[i], // field
	            types[i]  // type
	        };
	        batchArgs.add(values);
	    }

	    // 3. สั่ง Execute Batch ทีเดียว
	    jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	
	@Override
	public List<QuickQuery> getQuickQuery(int id) {		
		String sql = "SELECT * from quick_query  where id_refer =?   ";
		 List<QuickQuery> data  =  jdbcTemplate.query(sql,new Object[]{id},new QuickQuerymapper());
		 return data;
	} 
	
	
	@Override
	public List<FilterChain> getPrepareQueryChain(PrepareReport config) {
			String sql = "SELECT * from filter_chain  where id_refer =?  and  status = 1 ";
			List<FilterChain> data  =  jdbcTemplate.query(sql,new Object[]{config.getId()},new FilterChainmapper());
			
				if(data.size() < 1) {
					FilterChain  Chain = new FilterChain();
					Chain.setId_refer(config.getId());
					Chain.setTables(config.getProcess_id());
					Chain.setType(config.getSource_name());
					Chain.setGroups(config.getSource_type());
					Chain.setQuery_text("-");
					saveQueryChain(Chain);
					
					data  =  jdbcTemplate.query(sql,new Object[]{config.getId()},new FilterChainmapper());
				}
				
				return data;
		}


	@Override
	public void saveQueryChain(FilterChain chain) {
		Object[] chainInfo = { 
				chain.getId_refer(),
				chain.getTables(),
				chain.getType(),
				chain.getQuery_text(),
				chain.getGroups(),
				1
				};
				String sql = "INSERT INTO filter_chain (id_refer,tables,type,query_text,groups,status) VALUES (?,?,?,?,?,?)";
				jdbcTemplate.update(sql,chainInfo);
		}


	@Override
	public void deleteQueryChain(String chain) {

				String sql = "DELETE from filter_chain WHERE FIND_IN_SET(id,?)";
				jdbcTemplate.update(sql,new Object[]{chain});
		}
	
	
	@Override
	public void saveFilterCriteria(FilterCriteria Filter) {
	
		
				if(Filter.getId()  > 0) {
					String sql = "UPDATE  filter_criteria SET json_out_put = ? WHERE id= ? ";
					jdbcTemplate.update(sql,new Object[]{Filter.getJson_out_put(),Filter.getId()});
				}else {
					Object[] FilterInfo = { 
							Filter.getId_refer(),
							Filter.getJson_out_put(),
					
							};
					String sql = "INSERT INTO filter_criteria (id_refer,json_out_put) VALUES (?,?)";
					jdbcTemplate.update(sql,FilterInfo);
				}
				
		}
	
	

	@Override
	public FilterCriteria getFilter_criteria(int id_refer) {
		  try {
				String sql = "SELECT * from filter_criteria  where id_refer=?  limit 1  ";
				FilterCriteria data  =  jdbcTemplate.queryForObject(sql,new Object[]{id_refer},new FilterCriteriamapper());
				return data;
		 
		 }catch(EmptyResultDataAccessException e) {
				return null;
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }
		
	}
	
	
	@Override
	public void saveSyncBackSegmentation(Segmentation segmentation) {
	    System.out.println("Processing ID: " + segmentation.getId());

	    // 1. ดึงข้อมูลประวัติเก่าออกมาจาก DB โดยใช้ "segmentation_select"
	    String selectSql = "SELECT segmentation_member, segmentation_member_id, segmentation_select FROM segmentation WHERE id = ?";
	    List<Map<String, Object>> existingData = jdbcTemplate.queryForList(selectSql, segmentation.getId());

	    String oldHistory = "";
	    int currentRound = 1;
	    String oldMemberId = "";
	    String oldMemberJson = "[]";

	    if (!existingData.isEmpty()) {
	        Map<String, Object> row = existingData.get(0);
	        oldHistory = (String) row.getOrDefault("segmentation_select", "");
	        oldMemberId = (String) row.get("segmentation_member_id");
	        oldMemberJson = (String) row.get("segmentation_member");

	        // นับจำนวนรอบที่มีอยู่เดิม (นับจากคำว่า 🟢 รอบ)
	        if (oldHistory != null && !oldHistory.contains("รอบ")) {
	            currentRound = 1;
	        } else if (oldHistory != null) {
	            currentRound = oldHistory.split("🟢 รอบ").length; 
	        }
	    }

	    // ==========================================
	    // 🚀 2. จัด Format ข้อความให้อ่านง่ายใน Textarea
	    // ==========================================
	    String rawDesc = segmentation.getSegmentation_select();
	    if (rawDesc == null || rawDesc.trim().isEmpty()) {
	        rawDesc = "ไม่มีเงื่อนไข (ดึงข้อมูลทั้งหมด)";
	    }

	    // เปลี่ยนคำเชื่อม " และ " / " หรือ " ให้กลายเป็นขึ้นบรรทัดใหม่
	    String formattedDesc = "  - " + rawDesc
	            .replace(" และ ", "\n  - ")
	            .replace(" หรือ ", "\n  🔹 หรือ ");

	    // สร้างกล่องข้อความประวัติ 1 รอบ (เคาะบรรทัดจัดเต็มให้อ่านง่าย)
	    String newEntry = String.format("🟢 รอบ %d (ดึงมา %d ราย)\n%s\n---------------------------------------", 
	                        currentRound, 
	                        segmentation.getTotal_data_select(),
	                        formattedDesc);

	    // นำประวัติใหม่ไปต่อท้ายประวัติเก่า (เคาะ 1 บรรทัดให้ห่างกัน)
	    String updatedHistory = (oldHistory == null || oldHistory.trim().isEmpty()) 
	                            ? newEntry 
	                            : oldHistory + "\n\n" + newEntry;

	    // ==========================================
	    // 3. จัดการผสาน (Merge) ID และ JSON 
	    // ==========================================
	    Set<String> mergedIds = new LinkedHashSet<>();
	    if (oldMemberId != null && !oldMemberId.trim().isEmpty()) mergedIds.addAll(Arrays.asList(oldMemberId.split(",")));
	    if (segmentation.getSegmentation_member_id() != null && !segmentation.getSegmentation_member_id().trim().isEmpty()) {
	        mergedIds.addAll(Arrays.asList(segmentation.getSegmentation_member_id().split(",")));
	    }
	    String newMergedIds = String.join(",", mergedIds);

	    String mergedJson = "[]";
	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        List<Map<String, Object>> oldList = new ArrayList<>();
	        if (oldMemberJson != null && !oldMemberJson.trim().isEmpty()) oldList = mapper.readValue(oldMemberJson, new TypeReference<List<Map<String, Object>>>() {});
	        
	        List<Map<String, Object>> newList = new ArrayList<>();
	        if (segmentation.getSegmentation_member() != null && !segmentation.getSegmentation_member().trim().isEmpty()) {
	            newList = mapper.readValue(segmentation.getSegmentation_member(), new TypeReference<List<Map<String, Object>>>() {});
	        }

	        Map<String, Map<String, Object>> mergedMap = new LinkedHashMap<>();
	        for (Map<String, Object> item : oldList) mergedMap.put(String.valueOf(item.get("id")), item);
	        for (Map<String, Object> item : newList) mergedMap.put(String.valueOf(item.get("id")), item);
	        
	        mergedJson = mapper.writeValueAsString(mergedMap.values());
	    } catch (Exception e) { e.printStackTrace(); }

	    int newTotalDataSelect = mergedIds.isEmpty() ? 0 : mergedIds.size();

	    // ==========================================
	    // 4. อัปเดตข้อมูลกลับลง Database
	    // ==========================================
	    // ใช้คอลัมน์ segmentation_select ในการเก็บประวัติ
	    String updateSql = "UPDATE segmentation SET segmentation_select = ?, segmentation_member = ?, segmentation_member_id = ?, total_data = ?, total_data_select = ?, date_edit = ? WHERE id = ?";
	    
	    jdbcTemplate.update(updateSql, new Object[]{
	            updatedHistory,      // <--- ตัวแปรที่จัด Format สวยงามแล้ว
	            mergedJson, 
	            newMergedIds, 
	            segmentation.getTotal_data(),
	            newTotalDataSelect, 
	            segmentation.getDate_edit(), 
	            segmentation.getId()
	    });
	    
	    System.out.println("✅ บันทึกรอบที่ " + currentRound + " สำเร็จ!");
	}
	
	
	@Override
	public void doUnSyncBackSegmentation(Segmentation segmentation) {

				String sql = "UPDATE segmentation SET segmentation_member  =  NULL  , segmentation_select  =  NULL, segmentation_member_id  =  NULL,total_data=0,diff_data=0,total_same=0,total_data_select=0  WHERE id =? ";
				jdbcTemplate.update(sql,new Object[]{segmentation.getId()});
	}
	
	
	

	@Override
	public void copySegmentation(Segmentation segmentation) {
	    int oldSegmentationId = segmentation.getId();

	    // =========================================================
	    // 1. Copy Segmentation และรับ ID ใหม่
	    // =========================================================
	    String sqlSeg = "INSERT INTO segmentation ("
	            + "name, status, date_add, date_edit, id_add, id_edit, type, "
	            + "total_data, total_data_select, diff_data, total_same, "
	            + "process_id, edata_id_reserve, segmentation_member, "
	            + "segmentation_member_id, call_total, call_unique_count"
	            + ") SELECT "
	            + "CONCAT(name, '_copy'), status, NOW(), NOW(), id_add, id_edit, type, "
	            + "0, 0, 0, 0, "
	            + "process_id, edata_id_reserve, NULL, "
	            + "NULL, call_total, call_unique_count "
	            + "FROM segmentation WHERE id = ?";

	    KeyHolder segKeyHolder = new GeneratedKeyHolder();
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(sqlSeg, Statement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, oldSegmentationId);
	        return ps;
	    }, segKeyHolder);
	    
	    // ได้ ID ใหม่ของ Segmentation
	    int newSegmentationId = segKeyHolder.getKey().intValue();

	    // =========================================================
	    // 2. ค้นหา appreport เดิม และ Copy ไปสร้างใหม่
	    // =========================================================
	    // สมมติว่า 1 segmentation มี 1 appreport (ผูกกันด้วย source_id)
	    String findAppReportSql = "SELECT id FROM appreport WHERE source_id = ?";
	    List<Integer> oldAppReportIds = jdbcTemplate.queryForList(findAppReportSql, Integer.class, oldSegmentationId);

	    if (!oldAppReportIds.isEmpty()) {
	        int oldAppReportId = oldAppReportIds.get(0);

	        String sqlAppReport = "INSERT INTO appreport ("
	                + "source_type_method, source_id, process_id, source_type, source_name, querychains_enabled, "
	                + "sync_back_limit, sync_back_random, sync_back_not_unique_active, "
	                + "data_info_show_row, data_info_showsample_total, data_info_showtotal_row) "
	                + "SELECT "
	                + "source_type_method, ?, process_id, source_type, source_name, querychains_enabled, " // เปลี่ยน source_id เป็น ?
	                + "sync_back_limit, sync_back_random, sync_back_not_unique_active, "
	                + "data_info_show_row, data_info_showsample_total, data_info_showtotal_row "
	                + "FROM appreport WHERE id = ?";

	        KeyHolder appReportKeyHolder = new GeneratedKeyHolder();
	        jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(sqlAppReport, Statement.RETURN_GENERATED_KEYS);
	            ps.setInt(1, newSegmentationId); // ใส่ ID ใหม่ของ segmentation
	            ps.setInt(2, oldAppReportId);    // อ้างอิง ID เดิมเพื่อดึงข้อมูล
	            return ps;
	        }, appReportKeyHolder);

	        // ได้ ID ใหม่ของ AppReport
	        int newAppReportId = appReportKeyHolder.getKey().intValue();

	        // =========================================================
	        // 3. ค้นหา filter_chain เดิม (มีหลายแถว) แล้ววน Loop Copy
	        // =========================================================
	        String findFilterChainSql = "SELECT id FROM filter_chain WHERE id_refer = ?";
	        List<Integer> oldFilterChainIds = jdbcTemplate.queryForList(findFilterChainSql, Integer.class, oldAppReportId);

	        for (Integer oldFilterChainId : oldFilterChainIds) {
	            String sqlFilterChain = "INSERT INTO filter_chain ("
	                    + "id_refer, tables, type, query_text, groups,status) "
	                    + "SELECT "
	                    + "?, tables, type, query_text, groups, status " // เปลี่ยน id_refer เป็น ?
	                    + "FROM filter_chain WHERE id = ?";

	            KeyHolder fcKeyHolder = new GeneratedKeyHolder();
	            jdbcTemplate.update(connection -> {
	                PreparedStatement ps = connection.prepareStatement(sqlFilterChain, Statement.RETURN_GENERATED_KEYS);
	                ps.setInt(1, newAppReportId); // ใส่ ID ใหม่ของ appreport
	                ps.setInt(2, oldFilterChainId);
	                return ps;
	            }, fcKeyHolder);

	            // ได้ ID ใหม่ของ Filter Chain
	            int newFilterChainId = fcKeyHolder.getKey().intValue();

	            // =========================================================
	            // 4. Copy filter_criteria (จุดหมายสุดท้าย)
	            // =========================================================
	            // เนื่องจากไม่ต้องเอา ID ไปส่งต่อแล้ว เราใช้ jdbcTemplate.update ปกติได้เลย
	            String sqlFilterCriteria = "INSERT INTO filter_criteria (id_refer, json_out_put) "
	                    + "SELECT ?, json_out_put FROM filter_criteria WHERE id_refer = ?";
	            
	            jdbcTemplate.update(sqlFilterCriteria, newFilterChainId, oldFilterChainId);
	        }
	    }
	}
	
	
	@Override
	public void UseSegmentation(String segmentation_profile_static_snapshot,int edata_id, String type) {

				int status = 1;
				if(type.equals("Flow")) {
					status = 2;
				}
				else if(type.equals("End")) {
					status = 0;
				}
		
				System.out.println(" segmentation  status "+status);
				System.out.println(" segmentation  id "+segmentation_profile_static_snapshot);
				
				String sql = "UPDATE segmentation SET status =?,edata_id_reserve= ?  WHERE id =?  AND status != 0 ";
				jdbcTemplate.update(sql,new Object[]{status,edata_id,segmentation_profile_static_snapshot});
	}

	
	
	@Override
	public List<Measurement_rules> getMeasurement_rules() {
		String sql = "SELECT * from measurement_rules  ";
		List<Measurement_rules> list =  jdbcTemplate.query(sql,new Measurement_rulesmappers());
		return list;

	}

	@Override
	public List<Measurement_rules> getMeasurement_rules(int data_id) {
		String sql = "SELECT * from measurement_rules  WHERE destination_process =? ";
		List<Measurement_rules> list =  jdbcTemplate.query(sql,new Object[]{data_id},new Measurement_rulesmappers());
		return list;

	}
	@Override
	public Measurement_values getMeasurement_values(int rules_id, int data_id) {
		
		
		  try {
			  String sql = "SELECT * from measurement_values  WHERE measurement_rules_id =? AND edata_id =? ";
				Measurement_values data =  jdbcTemplate.queryForObject(sql,new Object[]{rules_id,data_id},new Measurement_valuesmappers());
				return data;
		 
		 }catch(EmptyResultDataAccessException e) {
			 Measurement_values value = new Measurement_values();
			 return value;
			 //throw new RuntimeException("file ID does not exist");
		    
		 }catch(IncorrectResultSizeDataAccessException e) {
		       
			 throw new RuntimeException("More than one users with the same Id .......");
		   
		 }


	}
	
	
	@Override
	public void saveTabManagement(TabManagement tab) {
		
	    Object[] tabInfo = { 
	    		tab.getTab_name(),
	    		tab.getTab_count(),
	    		tab.getTab_type_view(),
	    		tab.getTab_columns(),
	    		tab.getTab_table(),
	    		tab.getTab_maintable(),
	    		tab.getTab_child(),
	    		tab.getTab_process_id(),
	    		tab.getTab_management_id(),
	    		tab.getTab_current_step(),
	    		tab.getTab_current_step_int(),
	    		tab.getTab_closing_status(),  // <--- เติมตัวนี้
	    		
	    		tab.getShortcut_profile_app(),  // <--- เติมตัวนี้
	    		tab.getShortcut_profile_code(),
	    		tab.getShortcut_profile_linkname(),
	    		tab.getShortcut_newdoc_linkname(),  // <--- เติมตัวนี้
	    		tab.getShortcut_newdoc_code(),
	    		tab.getShortcut_newdoc_change_closing_status(),

	    		tab.getShortcut_change_closing_status(),
	    		tab.getShortcut_change_closing_linkname(),
	    	
	    	
	    		tab.getShortcut_segment_linkname(),
	    		tab.getShortcut_segment_code(),
	    		tab.getShortcut_segment_column(),
	    		
	    		tab.getAcknowledge(),
	    		tab.getFollowup(),
	     		tab.getFollowup_plan(),
	     		
	     		tab.getGrouping_row(),
	     		tab.getGrouping_row_check(),
	     		tab.getGrouping_row_date(),
	     		
	     		tab.getTab_current_step2(),
	     		tab.getTab_closing_status2(),
	     		tab.getAcknowledge2(),
	     		tab.getAcknowledge_user(),
	    		tab.getAcknowledge_user2(),
	    		tab.getExpired(),
	    		
	      		tab.getRequest_to_edit(),
	      		tab.getRequest_to_edit2(),
	    };

	    
	    
        if(tab.getId() > 0) {
        	  String sql = "UPDATE  tabmanagement  SET tab_name =?,tab_count=?,tab_type_view=?,tab_columns=?,tab_table=?,tab_maintable=?,tab_child=?"
      		  		+ ",tab_process_id=?,tab_management_id=?,tab_current_step=?,tab_current_step_int=?,tab_closing_status=?,shortcut_profile_app=?,shortcut_profile_code=?"
      		  		+ ",shortcut_profile_linkname=?,shortcut_newdoc_linkname=?,shortcut_newdoc_code=?"
      		  		+ ",shortcut_newdoc_change_closing_status=?,shortcut_change_closing_status=?"
      		  		+ ",shortcut_change_closing_linkname=?,shortcut_segment_linkname=?,shortcut_segment_code=?,shortcut_segment_column=?"
      		  		+ ",acknowledge=?,followup=?"
      		  		+ ",followup_plan=?, grouping_row=?,grouping_row_check=?,grouping_row_date=?,tab_current_step2=?,tab_closing_status2=?,acknowledge2=?,acknowledge_user=?"
      		  		+ ",acknowledge_user2=?,expired=?,request_to_edit=?,request_to_edit2=?"
      		  		+ "	   WHERE id =  "+tab.getId();
      			jdbcTemplate.update(sql,tabInfo);
        }else {
        	  String sql = "INSERT INTO tabmanagement (tab_name,tab_count,tab_type_view,tab_columns,tab_table,tab_maintable,tab_child"
      		  		+ ",tab_process_id,tab_management_id,tab_current_step,tab_current_step_int,tab_closing_status,shortcut_profile_app,shortcut_profile_code"
      		  		+ ",shortcut_profile_linkname,shortcut_newdoc_linkname,shortcut_newdoc_code,shortcut_newdoc_change_closing_status"
      		  		+ ",shortcut_change_closing_status,shortcut_change_closing_linkname,shortcut_segment_linkname,shortcut_segment_code,shortcut_segment_column,acknowledge"
      		  		+ ",followup,followup_plan,grouping_row,grouping_row_check,grouping_row_date,tab_current_step2,tab_closing_status2,acknowledge2,acknowledge_user,acknowledge_user2"
      		  		+ ",expired,request_to_edit,request_to_edit2)  "
      		  		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      			jdbcTemplate.update(sql,tabInfo);
        }
        
	}



	
	@Override
	public void saveBuildIn(BuildIn buildIn){
		
	 

	    
        if(buildIn.getId() > 0) {
        	
        	   Object[] tabInfo = { 
       	    		buildIn.getId_refer(),
       	    		buildIn.getBuildin_title(),
       	    		buildIn.getBuildin_note(),
       	    		buildIn.getSource_type(),
       	    		buildIn.getCurrent_step(),
       	    		buildIn.getClosing_status_stop(),
       	    		buildIn.getSource_param(),
       	    		buildIn.getTocolumn(),

       	    };
        	  String sql = "UPDATE  buildin  SET id_refer =?,buildin_title =?,buildin_note =?,source_type =?"
        	  		+ ",current_step =?,closing_status_stop =?,source_param =?,tocolumn =?  WHERE id =  "+buildIn.getId();
      			jdbcTemplate.update(sql,tabInfo);
        }else {
        	
        	   Object[] tabInfo = { 
       	    		buildIn.getId_refer(),
       	    		buildIn.getProcess_id(),
       	    		buildIn.getBuildin_title(),
       	    		buildIn.getBuildin_note(),
       	    		buildIn.getSource_type(),
       	    		buildIn.getCurrent_step(),
       	    		buildIn.getClosing_status_stop(),
       	    		buildIn.getSource_param(),
       	    		buildIn.getTocolumn(),

       	    };
        	  String sql = "INSERT INTO buildin (id_refer,process_id,buildin_title,buildin_note,source_type,current_step,closing_status_stop,source_param,tocolumn)  "
      		  		+ "VALUES(?,?,?,?,?,?,?,?,?)";
      			jdbcTemplate.update(sql,tabInfo);
        }
		
	}
	@Override
	public void deleteBuildIn(int id) {
	  	String sql = "DELETE from buildin WHERE id =? ";
      	jdbcTemplate.update(sql,new Object[] {id});
	}

	@Override
	public BuildIn getBuildIn(int id) {
		
		try {
			String sql = "SELECT *  from buildin WHERE id =? ";
			BuildIn data = jdbcTemplate.queryForObject(sql,new Object[] {id},new BuildInmapper());
			return data;
		}catch(Exception e) {
			
			return null;
		}
	}
	
	
	
	@Override
	public int saveMeasurement_values(Measurement_values value) {
	    
	    // 1. ปรับ Array ให้ครบ (สังเกตว่าเดิม 90 หายไป ผมเติมให้แล้วครับ)
	    Object[] valueInfo = { 
	            value.getMeasurement_rules_id(),
	            value.getEdata_id(),
	            value.getPercentage_value0(),
	            value.getPercentage_value10(),
	            value.getPercentage_value20(),
	            value.getPercentage_value30(),
	            value.getPercentage_value40(),
	            value.getPercentage_value50(),
	            value.getPercentage_value60(),
	            value.getPercentage_value70(),
	            value.getPercentage_value80(),
	            value.getPercentage_value90(),  // <--- เติมตัวนี้
	            value.getPercentage_value100(),
	            value.getCurrent_value()
	            // ตัดเลข 1 ท้ายสุดออก ถ้าไม่ได้ใช้จริงใน column ไหน
	    };

	    // 2. ใช้ ON DUPLICATE KEY UPDATE
	    // ถ้า Insert แล้วชน Index เดิม (rules_id + edata_id ซ้ำ) 
	    // มันจะกระโดดไปทำคำสั่ง UPDATE ด้านหลังแทนครับ
	    String sql = "INSERT INTO measurement_values "
	            + "(measurement_rules_id, edata_id, "
	            + "percentage_value0, percentage_value10, percentage_value20, percentage_value30, percentage_value40, "
	            + "percentage_value50, percentage_value60, percentage_value70, percentage_value80, percentage_value90, percentage_value100, "
	            + "current_value) "
	            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) " // เช็คว่ามี ? ครบ 14 ตัว
	            + "ON DUPLICATE KEY UPDATE "
	            + "percentage_value0 = VALUES(percentage_value0), "
	            + "percentage_value10 = VALUES(percentage_value10), "
	            + "percentage_value20 = VALUES(percentage_value20), "
	            + "percentage_value30 = VALUES(percentage_value30), "
	            + "percentage_value40 = VALUES(percentage_value40), "
	            + "percentage_value50 = VALUES(percentage_value50), "
	            + "percentage_value60 = VALUES(percentage_value60), "
	            + "percentage_value70 = VALUES(percentage_value70), "
	            + "percentage_value80 = VALUES(percentage_value80), "
	            + "percentage_value90 = VALUES(percentage_value90), "
	            + "percentage_value100 = VALUES(percentage_value100), "
	            + "current_value = VALUES(current_value)";

	    return jdbcTemplate.update(sql, valueInfo) > 0 ? 1 : 0;
	}


	
		@Override
		public TabManagement getTabManagement(int id) {
			
			try {
				String sql = "SELECT *  from tabmanagement WHERE id =? ";
				TabManagement data = jdbcTemplate.queryForObject(sql,new Object[] {id},new TabManagementmapper());
				return data;
			}catch(Exception e) {
				
				return null;
			}
		}
		
		
		@Override
		public TabManagement getTabManagementChange_closing(int id) {
			
			try {
				String sql = "SELECT *  from tabmanagement WHERE tab_process_id =? "
						+ "AND  ( shortcut_newdoc_change_closing_status IS NOT NULL OR  shortcut_newdoc_change_closing_status != '' ) limit 1 ";
				TabManagement data = jdbcTemplate.queryForObject(sql,new Object[] {id},new TabManagementmapper());
				return data;
			}catch(Exception e) {
				
				return null;
			}
		}
		
		
		
		@Override
		public EdataReportbuildIn getedataReportBuildin(int id_refer,int id) {
			
			try {
				String sql = "SELECT *  from edata_report_buildin WHERE buildin_id_refer=? AND buildin_edata =? ";
				EdataReportbuildIn data = jdbcTemplate.queryForObject(sql,new Object[] {id_refer,id},new EdataReportbuildInmapper());
				return data;
			}catch(Exception e) {
				EdataReportbuildIn data = new EdataReportbuildIn();
				
				data.setBuildin_id_refer(id_refer);
				data.setBuildin_edata(id);
				
				return data;
			}
		}
		
		
		@Override
		public void saveEdataReportbuildIn(EdataReportbuildIn buildIn) {
		    
		    // โค้ดนี้ใช้เทคนิค UPSERT (Insert หรือ Update ถ้ามีข้อมูลซ้ำ)
		    String sql = "INSERT INTO edata_report_buildin "
		            + "(buildin_id_refer, buildin_edata, "
		            + "columns_value1, columns_value2, columns_value3, columns_value4, columns_value5, "
		            + "columns_value6, columns_value7, columns_value8, columns_value9, columns_value10, "
		            + "columns_value11, columns_value12, columns_value13, columns_value14, columns_value15, "
		            + "columns_value16, columns_value17, columns_value18, columns_value19, columns_value20) "
		            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
		            + "ON DUPLICATE KEY UPDATE "
		            + "columns_value1 = VALUES(columns_value1), columns_value2 = VALUES(columns_value2), "
		            + "columns_value3 = VALUES(columns_value3), columns_value4 = VALUES(columns_value4), "
		            + "columns_value5 = VALUES(columns_value5), columns_value6 = VALUES(columns_value6), "
		            + "columns_value7 = VALUES(columns_value7), columns_value8 = VALUES(columns_value8), "
		            + "columns_value9 = VALUES(columns_value9), columns_value10 = VALUES(columns_value10), "
		            + "columns_value11 = VALUES(columns_value11), columns_value12 = VALUES(columns_value12), "
		            + "columns_value13 = VALUES(columns_value13), columns_value14 = VALUES(columns_value14), "
		            + "columns_value15 = VALUES(columns_value15), columns_value16 = VALUES(columns_value16), "
		            + "columns_value17 = VALUES(columns_value17), columns_value18 = VALUES(columns_value18), "
		            + "columns_value19 = VALUES(columns_value19), columns_value20 = VALUES(columns_value20)";

		    Object[] valueInfo = { 
		            buildIn.getBuildin_id_refer(),
		            buildIn.getBuildin_edata(),
		            buildIn.getColumns_value1(), buildIn.getColumns_value2(), buildIn.getColumns_value3(), 
		            buildIn.getColumns_value4(), buildIn.getColumns_value5(), buildIn.getColumns_value6(), 
		            buildIn.getColumns_value7(), buildIn.getColumns_value8(), buildIn.getColumns_value9(), 
		            buildIn.getColumns_value10(), buildIn.getColumns_value11(), buildIn.getColumns_value12(), 
		            buildIn.getColumns_value13(), buildIn.getColumns_value14(), buildIn.getColumns_value15(), 
		            buildIn.getColumns_value16(), buildIn.getColumns_value17(), buildIn.getColumns_value18(), 
		            buildIn.getColumns_value19(), buildIn.getColumns_value20()
		    };

		    // ยิงแค่คำสั่งเดียว Database จัดการให้หมด ทั้งเร็วและไม่เกิดข้อมูลซ้ำแน่นอน
		    jdbcTemplate.update(sql, valueInfo);
		}
		
		
		
		@Override
		public void deleteTabManagement(int id) {
		  	String sql = "DELETE from tabmanagement WHERE id =? ";
	      	jdbcTemplate.update(sql,new Object[] {id});
		}



		@Override
		public BuildIn getBuildInByPprocessIdAndManagementId(int processId, int management_id) {
			try {
				String sql = "SELECT *  from buildin WHERE process_id=? AND id_refer =? ";
				BuildIn data = jdbcTemplate.queryForObject(sql,new Object[] {processId,management_id},new BuildInmapper());
				return data;
			}catch(Exception e) {
				
				return null;
			}
		}
		@Override
		public List<BuildIn> getAllBuildInByPprocessIdAndManagementId(int processId) {
			try {
				String sql = "SELECT *  from buildin WHERE process_id=? ";
				List<BuildIn> data = jdbcTemplate.query(sql,new Object[] {processId},new BuildInmapper());
				return data;
			}catch(Exception e) {
				
				return null;
			}
		}



		@Override
		public void updatePropertyReport(PrepareReport prepareReport) {
			
			Object[] prepareReportInfo = {
					prepareReport.getSync_back_limit(),
					prepareReport.getSync_back_random(),
					prepareReport.getData_info_show_row(),
					prepareReport.getData_info_showsample_total(),
					prepareReport.getData_info_showtotal_row(),
					prepareReport.getSync_back_not_unique_active(),
					prepareReport.getId(),
					
			};
			String sql = "UPDATE appreport SET sync_back_limit=?,sync_back_random=?,data_info_show_row=?,data_info_showsample_total=?"
					+ ",data_info_showtotal_row=?,sync_back_not_unique_active=?  WHERE id =? ";
			jdbcTemplate.update(sql,prepareReportInfo);
		}

		

}	