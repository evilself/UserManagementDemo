package com.westernacher.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan( basePackages = {""}, excludeFilters={ @Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class) })
public class DemoRootConfig {
	@Bean
	@Primary
	public JndiObjectFactoryBean dataSource(@Value("${demo.config.datasource.jndi}") String dataSourceJNDI) {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName(dataSourceJNDI);
		jndiBean.setResourceRef(true);
		jndiBean.setProxyInterface(DataSource.class);
		return jndiBean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
		hibernateAdapter.setDatabase(Database.MYSQL);
		hibernateAdapter.setShowSql(true);
		hibernateAdapter.setGenerateDdl(false);
		hibernateAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");	
		return hibernateAdapter;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource datasource) {
		return new JdbcTemplate(datasource);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter, 
																			@Value("${demo.config.data.domain.model}") String packageToScan) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan(packageToScan);
		return emfb;		
	}
	
	@Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);
      return transactionManager;
   }
	
	//The Bean below allows Spring to throw its own rendition of SQL or DB related exceptions.
	//Spring Exceptions are lot more verbose and consistent
	@Bean
	public BeanPostProcessor persistanceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}	
}