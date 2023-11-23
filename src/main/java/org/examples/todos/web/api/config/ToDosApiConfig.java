package org.examples.todos.web.api.config;

import java.util.List;

import org.examples.todos.application.config.TodosApplicationConfig;
import org.examples.todos.application.todos.accounting.commands.ToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.commands.standard.StandardToDosAccountingCommandService;
import org.examples.todos.application.todos.accounting.queries.ToDosAccountingQueryService;
import org.examples.todos.application.todos.accounting.queries.standard.StandardToDosAccountingQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = { "org.examples.todos.web.api.*" })
@EnableWebMvc
@Import({
	TodosApplicationConfig.class
})
public class ToDosApiConfig implements WebMvcConfigurer {

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
