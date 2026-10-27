package com.smepro.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableJpaRepositories(basePackages = "com.smepro.repository")
@EnableTransactionManagement
public class JpaConfig {
	
	
	
	
		@Bean(name = "entityManagerFactory") // << สำคัญ
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds) {
	        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	        emf.setDataSource(ds);
	        emf.setPackagesToScan("com.smepro.entity");
	        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

	        Properties props = new Properties();
	        props.setProperty("hibernate.hbm2ddl.auto", "update");
	        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
	        props.setProperty("hibernate.show_sql", "true");

	        emf.setJpaProperties(props);
	        return emf;
	    }

	    @Bean(name = "transactionManager") // << สำคัญ
	    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	        return new JpaTransactionManager(emf);
	    }
}
