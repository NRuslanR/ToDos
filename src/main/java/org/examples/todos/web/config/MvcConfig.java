package org.examples.todos.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = { "org.examples.todos.web.*" },
		excludeFilters = {
				@ComponentScan.Filter(
						type = FilterType.ASPECTJ, 
						pattern = { "org.examples.todos.web.api.*" }
				)
		})
public class MvcConfig implements WebMvcConfigurer {
	
	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver()
	{
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine()
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver()
	{
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		
		viewResolver.setTemplateEngine(thymeleafTemplateEngine());

		return viewResolver;
	}
	
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    	
    	WebMvcConfigurer.super.configureViewResolvers(registry);
    	
    	registry.viewResolver(thymeleafViewResolver());
    }
}
