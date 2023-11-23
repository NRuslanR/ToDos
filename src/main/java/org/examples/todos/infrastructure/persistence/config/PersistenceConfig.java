package org.examples.todos.infrastructure.persistence.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(pooledDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaDialect(jpaDialect());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.setPackagesToScan("org.examples.todos.infrastructure.persistence");

        return factoryBean;
    }

    @Bean
    public DataSource pooledDataSource() {

        var hikariConfig = new HikariConfig();

        hikariConfig.setMaximumPoolSize(maxConnectionPoolSize());
        hikariConfig.setDataSource(dataSource());
        
        var pooledDataSource = new HikariDataSource(hikariConfig);

        return pooledDataSource;
    }

    private int maxConnectionPoolSize() {
        
        var cpuCount = Runtime.getRuntime().availableProcessors();
       
        return (cpuCount * 2) + 1 /* storage device (hard disk, ssd, RAID's item) count */;
    }

    @Bean
    public DataSource dataSource() {
        
        var targetDataSource = new DriverManagerDataSource();

        // refactor: read from external config source

        targetDataSource.setDriverClassName(Driver.class.getName());
        targetDataSource.setUrl("jdbc:postgresql://localhost:5432/todos"); 
        targetDataSource.setUsername("todos_user");
        targetDataSource.setPassword("123456");

        return targetDataSource;
    }

    private JpaVendorAdapter jpaVendorAdapter() {

        var vendorAdapter = new HibernateJpaVendorAdapter();

        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setShowSql(true);

        return vendorAdapter;
    }

    private JpaDialect jpaDialect() 
    {
        var dialect = new HibernateJpaDialect();
            
        return dialect;
    }

    private Properties jpaProperties() {
        
        var properties = new Properties();

        properties.setProperty("hibernate.hbm2dll.auto", "create-update");
        
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager()
    {
        var manager = new JpaTransactionManager();

        manager.setEntityManagerFactory(entityManagerFactory().getObject());

        return manager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public TransactionDefinition transactionDefinition()
    {
        var transactionDefinition = new DefaultTransactionDefinition();

        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);

        return transactionDefinition;
    }
}
