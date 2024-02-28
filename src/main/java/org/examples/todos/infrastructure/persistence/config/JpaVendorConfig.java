package org.examples.todos.infrastructure.persistence.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Configuration
@PropertySource("classpath:persistence/persistence-${spring.profiles.active}-props.properties")
@ComponentScan
public abstract class JpaVendorConfig 
{
	@Autowired
	protected Environment env;
	
	@Value("${spring.jpa.database}")
	private String database;
	
	@Value("${spring.jpa.database-platform}")
	private String databasePlatform;
	
	@Value("${spring.jpa.show-sql}")
	@Accessors(fluent = true)
	private boolean showSql;
	
	private Map<String, String> properties = new HashMap<String, String>();
	
	@PostConstruct
	protected void initProperties()
	{
		properties =
			getPropertyNames()
			.stream()
			.collect(Collectors.toMap(p -> p, p -> env.getProperty(p)));
	}
	
	protected Collection<String> getPropertyNames()
	{
		return Arrays.asList("spring.jpa.hibernate.hbm2ddl.auto");
	}
	
}
