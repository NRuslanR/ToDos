package org.examples.todos.web.api.init;

import org.examples.todos.web.api.config.ApiConfig;
import org.examples.todos.web.config.MvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class StandardToDosApiAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] { MvcConfig.class, ApiConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/*" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		super.onStartup(servletContext);
		
		servletContext.setInitParameter("spring.profiles.active", "dev");
	}
}
