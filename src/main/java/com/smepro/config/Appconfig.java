package com.smepro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.smepro.dao.AiDao;
import com.smepro.dao.AiDaolmpl;
import com.smepro.dao.IdocDAO;
import com.smepro.dao.IdocDAOlmpl;


import com.smepro.dao.actionplanDAO;
import com.smepro.dao.actionplanDAOlmpl;
import com.smepro.dao.attendanceDAO;
import com.smepro.dao.attendanceDAOlmpl;
import com.smepro.dao.backupDAO;
import com.smepro.dao.backupDAOlmpl;
import com.smepro.dao.centralDAO;
import com.smepro.dao.centralDAOlmapl;
import com.smepro.dao.cmDAO;
import com.smepro.dao.cmDAOlmpl;
import com.smepro.dao.commentDAO;
import com.smepro.dao.commentDAOlmpl;
import com.smepro.dao.conditionDAO;
import com.smepro.dao.conditionDAOlmpl;
import com.smepro.dao.darDAO;
import com.smepro.dao.darDAOlmpl;
import com.smepro.dao.dataeformDAO;
import com.smepro.dao.dataeformDAOlmpl;
import com.smepro.dao.datasetDAO;
import com.smepro.dao.datasetDAOlmpl;
import com.smepro.dao.dataset_dataDAO;
import com.smepro.dao.dataset_dataDAOlmpl;
import com.smepro.dao.departmentDAO;
import com.smepro.dao.departmentDAOlmpl;
import com.smepro.dao.drivesDAO;
import com.smepro.dao.drivesDAOlmpl;
import com.smepro.dao.e_reportDAO;
import com.smepro.dao.e_reportDAOlmapl;
import com.smepro.dao.edataDAO;
import com.smepro.dao.edataDAOlmpl;
import com.smepro.dao.eflowDAO;
import com.smepro.dao.eflowDAOlmpl;
import com.smepro.dao.eformfieldDAO;
import com.smepro.dao.eformfieldDAOlmpl;
import com.smepro.dao.fileDAO;
import com.smepro.dao.fileDAOlmpl;
import com.smepro.dao.folderDAO;
import com.smepro.dao.folderDAOlmpl;
import com.smepro.dao.formDAO;
import com.smepro.dao.formDAOlmpl;
import com.smepro.dao.group_authoritiesDAO;
import com.smepro.dao.group_authoritiesDAOlmpl;
import com.smepro.dao.group_membersDAO;
import com.smepro.dao.group_membersDAOlmpl;
import com.smepro.dao.groupsDAO;
import com.smepro.dao.groupsDAOlmpl;
import com.smepro.dao.holidayDAO;
import com.smepro.dao.holidayDAOlmpl;
import com.smepro.dao.jobDAO;
import com.smepro.dao.jobDAOlmpl;
import com.smepro.dao.logDAO;
import com.smepro.dao.logDAOlmpl;
import com.smepro.dao.mainfieldDAO;
import com.smepro.dao.mainfieldDAOlmapl;
import com.smepro.dao.memberDAO;
import com.smepro.dao.memberDAOImpl;
import com.smepro.dao.mysqlJobDAO;
import com.smepro.dao.mysqlJobDAOlmpl;
import com.smepro.dao.notificationDAO;
import com.smepro.dao.notificationDAOlmpl;
import com.smepro.dao.reportDAO;
import com.smepro.dao.reportDAOlmpl;
import com.smepro.dao.report_pnDAO;
import com.smepro.dao.report_pnDAOlmpl;
import com.smepro.dao.roleDAO;
import com.smepro.dao.roleDAOlmpl;
import com.smepro.dao.setmailDAO;
import com.smepro.dao.setmailDAOlmpl;
import com.smepro.dao.settingDAO;
import com.smepro.dao.settingDAOlmpl;
import com.smepro.dao.taskDAO;
import com.smepro.dao.taskDAOlmpl;
import com.smepro.dao.typeDAO;
import com.smepro.dao.typeDAOlmpl;
import com.smepro.dao.upload_pnDAO;
import com.smepro.dao.upload_pnDAOlmpl;
import com.smepro.dao.userDAO;
import com.smepro.dao.userDAOlmpl;
import com.smepro.dao.workflowDAO;
import com.smepro.dao.workflowDAOlmpl;
import com.zaxxer.hikari.HikariDataSource;



@EnableWebMvc
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.smepro.controller"})
@EnableTransactionManagement 
@EnableRetry
@PropertySources({@PropertySource("classpath:ds/datasource.properties")})
public class Appconfig implements WebMvcConfigurer, SchedulingConfigurer{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
    private Environment env;
	
	
	
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ivr = new InternalResourceViewResolver();
		ivr.setPrefix("/WEB-INF/views/");
		ivr.setSuffix(".jsp");
		ivr.setOrder(0);
		return ivr;
		
	}
	/*
	@Bean
	DriverManagerDataSource getDataSource() {
	
		//DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));
		
		return dataSource;
	}
	*/
	@Bean
	public DataSource getDataSource() {
	    HikariDataSource dataSource = new HikariDataSource();
	    dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
	    dataSource.setJdbcUrl(env.getProperty("ds.url"));
	    dataSource.setUsername(env.getProperty("ds.username"));
	    dataSource.setPassword(env.getProperty("ds.password"));
	    
	    
	    
	    String poolSizeStr = env.getProperty("ds.pool");
	    int poolSize = 10; // ค่าเริ่มต้นสำรอง

	    if (poolSizeStr != null) {
	        try {
	            poolSize = Integer.parseInt(poolSizeStr);
	        } catch (NumberFormatException e) {
	            // ถ้าแปลงไม่ได้ ใช้ค่าเริ่มต้น
	            e.printStackTrace();
	        }
	    }


	    dataSource.setMaximumPoolSize(poolSize);
	    return dataSource;
	}

	// ==========================================================
    // 🌟 2. เพิ่ม Method นี้เพื่อสร้าง Background Thread ให้ Scheduler
    // ==========================================================
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5); // อนุญาตให้รัน Cronjob พร้อมกันได้สูงสุด 5 ตัว
        scheduler.setThreadNamePrefix("ERP-BackgroundJob-"); // ตั้งชื่อ Thread จะได้ดู Log ง่ายๆ
        scheduler.initialize();
        
        taskRegistrar.setTaskScheduler(scheduler);
    }
	
	

	@Bean
	public memberDAO getUserDao() {
		return new memberDAOImpl(getDataSource());
	}

	@Bean
	public userDAO getuserDAO() {
		return new userDAOlmpl(getDataSource());
	}

	@Bean
	public groupsDAO getgroupsDAO() {
		return new groupsDAOlmpl(getDataSource());
	}
	
	@Bean
	public group_authoritiesDAO getgroup_authoritiesDAO() {
		return new group_authoritiesDAOlmpl(getDataSource());
	}
	
	@Bean
	public group_membersDAO getgroup_membersDAO() {
		return new group_membersDAOlmpl(getDataSource());
	}

	@Bean
	public drivesDAO getdrivesDAO() {
		return new drivesDAOlmpl(getDataSource());
	}

	@Bean
	public folderDAO getfolderDAO() {
		return new folderDAOlmpl(getDataSource());
	}	
	@Bean
	public typeDAO gettypeDAO() {
		return new typeDAOlmpl(getDataSource());
	}	
	
	@Bean
	public darDAO getdarDAO() {
		return new darDAOlmpl(getDataSource());
	}	
	@Bean
	public fileDAO getfileDAO() {
		return new fileDAOlmpl(getDataSource());
	}	
	
	@Bean
	public upload_pnDAO getupload_pnDAO() {
		return new upload_pnDAOlmpl(getDataSource());
	}	
	@Bean
	public logDAO getlogDAO() {
		return new logDAOlmpl(getDataSource());
	}	
	
	@Bean
	public roleDAO getroleDAO() {
		return new roleDAOlmpl(getDataSource());
	}	
	
	@Bean
	public notificationDAO getnotificationDAO() {
		return new notificationDAOlmpl(getDataSource());
	}	
	
	@Bean
	public report_pnDAO getreport_pnDAO() {
		return new report_pnDAOlmpl(getDataSource());
	}	
	
	@Bean
	public departmentDAO getdepartmentDAO() {
		return new departmentDAOlmpl(getDataSource());
	}	
	
	@Bean
	public setmailDAO getsetmailDAO() {
		return new setmailDAOlmpl(getDataSource());
	}	
	
	@Bean
	public backupDAO getbackupDAO() {
		return new backupDAOlmpl(getDataSource());
	}	
	@Bean
	public commentDAO getcommentDAO() {
		return new commentDAOlmpl(getDataSource());
	}	
	@Bean
	public conditionDAO getconditionDAO() {
		return new conditionDAOlmpl(getDataSource());
	}
	
	@Bean
	public datasetDAO getdatasetDAO() {
		return new datasetDAOlmpl(getDataSource());
	}
	
	@Bean
	public dataeformDAO getdataeformDAO() {
		return new dataeformDAOlmpl(getDataSource());
	}

	@Bean
	public actionplanDAO getactionplanDAO() {
		return new actionplanDAOlmpl(getDataSource());
	}
	
	@Bean
	public centralDAO getcentralDAO() {
		return new centralDAOlmapl(getDataSource());
	}
	@Bean
	public mainfieldDAO getmainfieldDAO() {
		return new mainfieldDAOlmapl(getDataSource());
	}
	@Bean
	public eformfieldDAO geteformfieldDAO() {
		return new eformfieldDAOlmpl(getDataSource());
	}
	@Bean
	public edataDAO getedataDAO() {
		return new edataDAOlmpl(getDataSource());
	}
	
	@Bean
	public dataset_dataDAO getdataset_dataDAO() {
		return new dataset_dataDAOlmpl(getDataSource());
	}
	@Bean
	public e_reportDAO gete_reportDAO() {
		return new e_reportDAOlmapl(getDataSource());
	}
	
	@Bean
	public cmDAO getcmDAO() {
		return new cmDAOlmpl(getDataSource());
	}
	
	@Bean
	public workflowDAO getworkflowDAO() {
		return new workflowDAOlmpl(getDataSource());
	}
	
	@Bean
	public eflowDAO geteflowDAO() {
		return new eflowDAOlmpl(getDataSource());
	}
	
	@Bean
	public taskDAO gettaskDAO() {
		return new taskDAOlmpl(getDataSource());
	}
	
	@Bean
	public attendanceDAO getattendanceDAO() {
		return new attendanceDAOlmpl(getDataSource());
	}
	@Bean
	public holidayDAO getholidayDAO() {
		return new holidayDAOlmpl(getDataSource());
	}
	@Bean
	public settingDAO getsettingDAO() {
		return new settingDAOlmpl(getDataSource());
	}
	@Bean
	public reportDAO getreportDAO() {
		return new reportDAOlmpl(getDataSource());
	}
	
	@Bean
	public formDAO getformDAO() {
		return new formDAOlmpl(getDataSource());
	}
	@Bean
	public IdocDAO getIdocDAO() {
		return new IdocDAOlmpl(getDataSource());
	}
	
	
	@Bean
	public jobDAO getJobDAO() {
		return new jobDAOlmpl(getDataSource());
	}
	
	@Bean
	public mysqlJobDAO getmysqlJobDAO() {
		return new mysqlJobDAOlmpl(getDataSource());
	}
	
	@Bean
	public AiDao getAiDao() {
		return new AiDaolmpl(getDataSource());
	}
	
	

	/*
	
	 @Bean(name = "Inventory")
	    public WorkSystem getInventory() {
	        return new InventoryRegistrationServiceImpl();
	}

	 @Bean(name = "Requisition")
	    public WorkSystem getRequisition() {
	        return new RequisitionServicelmpl();
	}
	
	@Bean(name = "Purchase")
	    public WorkSystem getPurchase() {
	        return new PurchaseRequisitionServiceImpl();
	}
	*/
	 @Bean
	 public PlatformTransactionManager transactionManager() {
	        return new DataSourceTransactionManager(getDataSource());
	  }
	 
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
}
