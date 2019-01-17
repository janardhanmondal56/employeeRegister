package com.emp.config;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = ("com.emp.repository"))
public class DatabaseConfig {

	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.emp.entity";

	private static final String ENTITYMANAGER_DATABASE_SCHEMA_PROPERTY_KEY = "spring.database.schema.name";

	@Resource
	private Environment environment;
//
//	@Primary
//	@Bean(name = "dataSource")
//	@ConfigurationProperties(prefix = "spring.datasource")
//	public DataSource dataSource() {
//		DataSource ds = DataSourceBuilder.create().build();
//		// Assume we make use of Apache Tomcat connection pooling (default in Spring Boot)
//		org.apache.tomcat.jdbc.pool.DataSource tds = (org.apache.tomcat.jdbc.pool.DataSource) ds;
//		tds.setInitialSize(Integer.valueOf(environment.getProperty("spring.datasource.tomcat.initial-size")));
////		tds.setTestWhileIdle(Boolean.valueOf(environment.getProperty("spring.datasource.tomcat.test-while-idle").toUpperCase()));
////		tds.setTimeBetweenEvictionRunsMillis(Integer.valueOf(environment.getProperty("spring.datasource.tomcat.time-between-eviction-runs-millis")));
////		tds.setMinEvictableIdleTimeMillis(Integer.valueOf(environment.getProperty("spring.datasource.tomcat.min-evictable-idle-time-millis")));
//		return tds;
//	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages(ENTITYMANAGER_PACKAGES_TO_SCAN)
				.persistenceUnit(environment.getProperty(ENTITYMANAGER_DATABASE_SCHEMA_PROPERTY_KEY)).build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
