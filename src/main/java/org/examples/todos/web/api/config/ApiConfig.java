package org.examples.todos.web.api.config;

import java.util.Arrays;
import java.util.List;

import org.examples.todos.application.config.ApplicationConfig;
import org.examples.todos.application.todos.accounting.commands.ToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.commands.standard.StandardToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.queries.ToDosAccountingQueryService;
import org.examples.todos.application.todos.accounting.queries.standard.StandardToDosAccountingQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "org.examples.todos.web.api.*" })
@EnableWebMvc
@Import({
	ApplicationConfig.class
})
public class ApiConfig implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		converters.add(jacksonHttpMessageConverter());
		
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
	
	@Bean
	public HttpMessageConverter<Object> jacksonHttpMessageConverter()
	{
		return new MappingJackson2HttpMessageConverter();
	}
	
	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		var configurer = new PropertySourcesPlaceholderConfigurer();
		
		var appRunningModes = Arrays.asList("dev", "prod");
		
		var appRunningModesPropsResources =
			appRunningModes
				.stream()
				.map(m -> "persistence/persistence-" + m + "-props.properties")
				.map(ClassPathResource::new)
				.toArray(ClassPathResource[]::new);
		
		configurer.setLocations(appRunningModesPropsResources);
		
		return configurer;
	}

	@Bean
	ToDosAccountingQueryService toDosAccountingQueryService()
	{
		return new StandardToDosAccountingQueryService();
	}

	@Bean
	ToDosAccountingCommandService toDosAccountingCommandService()
	{
		return new StandardToDosAccountingCommandService();
	}
}
