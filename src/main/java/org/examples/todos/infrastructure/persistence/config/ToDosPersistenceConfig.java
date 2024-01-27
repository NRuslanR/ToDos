package org.examples.todos.infrastructure.persistence.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.examples.todos.infrastructure.persistence.config.common.PersistenceComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
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
@PropertySources({
	@PropertySource("classpath:persistence/persistence-props.properties"),
})
@PersistenceComponentScan
@EnableTransactionManagement
@Import({ RepositoriesConfig.class })
public class ToDosPersistenceConfig {
	
	@Bean
	public DataSourceConfig dataSourceConfig()
	{
		var cfg = new DataSourceConfig();
		
		return cfg;
	}
	
	@Bean
	@Profile("dev")
	public JpaVendorConfig devJpaVendorConfig()
	{
		return new DevJpaVendorConfig();
	}
	
	@Bean
	@Profile("prod")
	public JpaVendorConfig prodJpaVendorConfig()
	{
		return new ProdJpaVendorConfig();
	}
	
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    	DataSourceConfig dataSourceConfig,
    	JpaVendorConfig jpaVendorConfig
    )
    {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(pooledDataSource(dataSourceConfig));
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter(jpaVendorConfig));
        factoryBean.setJpaDialect(jpaDialect());
        factoryBean.setJpaProperties(jpaProperties(jpaVendorConfig));
        factoryBean.setPackagesToScan("org.examples.todos.infrastructure.persistence");

        return factoryBean;
    }

    @Bean
    public DataSource pooledDataSource(DataSourceConfig dataSourceConfig) 
    {
        var hikariConfig = new HikariConfig();

        hikariConfig.setMaximumPoolSize(maxConnectionPoolSize());
        hikariConfig.setDataSource(dataSource(dataSourceConfig));
        
        var pooledDataSource = new HikariDataSource(hikariConfig);

        return pooledDataSource;
    }

    private int maxConnectionPoolSize() 
    {
        var cpuCount = Runtime.getRuntime().availableProcessors();
       
        return (cpuCount * 2) + 1 /* storage device (hard disk, ssd, RAID's item) count */;
    }

    @Bean
    public DataSource dataSource(DataSourceConfig dataSourceConfig) 
    { 
        var targetDataSource = new DriverManagerDataSource();

        targetDataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        targetDataSource.setUrl(dataSourceConfig.getUrl()); 
        targetDataSource.setUsername(dataSourceConfig.getUsername());
        targetDataSource.setPassword(dataSourceConfig.getPassword());
        
        return targetDataSource;
    }

    private JpaVendorAdapter jpaVendorAdapter(JpaVendorConfig jpaVendorConfig) 
    {
        var vendorAdapter = new HibernateJpaVendorAdapter();
        
        vendorAdapter.setDatabase(Database.valueOf(jpaVendorConfig.getDatabase()));
        vendorAdapter.setDatabasePlatform(jpaVendorConfig.getDatabasePlatform());
        vendorAdapter.setShowSql(jpaVendorConfig.showSql());
        
        return vendorAdapter;
    }

    private JpaDialect jpaDialect() 
    {
        var dialect = new HibernateJpaDialect();
        
        return dialect;
    }

    private Properties jpaProperties(JpaVendorConfig jpaVendorConfig) 
    { 
        var properties = new Properties();
        
        jpaVendorConfig.getProperties().forEach((k, v) -> properties.setProperty(k, v));
   
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
    	DataSourceConfig dataSourceConfig,
		JpaVendorConfig jpaVendorConfig
    )
    {
        var manager = new JpaTransactionManager();

        manager.setEntityManagerFactory(
        	entityManagerFactory(dataSourceConfig, jpaVendorConfig).getObject()
        );

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
