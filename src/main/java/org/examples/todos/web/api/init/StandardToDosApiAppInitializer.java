package org.examples.todos.web.api.init;

import org.examples.todos.web.api.config.ToDosApiConfig;
import org.examples.todos.web.config.ToDosMvcConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class StandardToDosApiAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] { ToDosMvcConfig.class, ToDosApiConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/*" };
	}

}
