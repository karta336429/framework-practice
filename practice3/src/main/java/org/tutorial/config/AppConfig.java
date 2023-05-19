package org.tutorial.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("org.tutorial")
@EnableTransactionManagement
public class AppConfig {
	
	// 設定 DataSource 相關資訊
	@Bean
	public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/practice?serverTimezone=Asia/Taipei");
        dataSource.setUsername("root");
        dataSource.setPassword("1qaz@WSX");
        return dataSource;
    }
	
	// 創建 JPA 的 EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		// 設定 DataSource
		emf.setDataSource(dataSource());
		// 設定 JPA 實作類及相關設定
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(jpaProperties());
		// 設定要掃描的實體類別所在的 package
		emf.setPackagesToScan("org.tutorial.model");
		return emf;
	}

	// 
	@Bean
	public Properties jpaProperties() {
		Properties properties = new Properties();
		// 設定 Hibernate 的 SQL 方言
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		// 設定 Hibernate 在執行 SQL 時會顯示實際的 SQL 語句
		properties.setProperty("hibernate.show_sql", "true");
		// 設定 Hibernate 會格式化顯示的 SQL 語句
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}